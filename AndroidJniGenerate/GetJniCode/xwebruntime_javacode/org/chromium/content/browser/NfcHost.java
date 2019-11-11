package org.chromium.content.browser;

import android.util.SparseArray;
import org.chromium.base.Callback;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.content_public.browser.WebContents;
import org.chromium.content_public.browser.WebContentsObserver;
import org.chromium.ui.base.WindowAndroid;

class NfcHost extends WebContentsObserver implements WindowAndroidChangedObserver {
    private Callback mCallback;
    private final ContentViewCoreImpl mContentViewCore;
    private final int mContextId;
    private final WebContents mWebContents;
    private static final SparseArray sContextHostsMap;

    static {
        NfcHost.sContextHostsMap = new SparseArray();
    }

    NfcHost(WebContents arg1, int arg2) {
        super(arg1);
        this.mWebContents = arg1;
        this.mContentViewCore = ContentViewCoreImpl.fromWebContents(this.mWebContents);
        this.mContextId = arg2;
        NfcHost.sContextHostsMap.put(this.mContextId, this);
    }

    @CalledByNative private static NfcHost create(WebContents arg1, int arg2) {
        return new NfcHost(arg1, arg2);
    }

    public void destroy() {
        this.stopTrackingActivityChanges();
        NfcHost.sContextHostsMap.remove(this.mContextId);
        super.destroy();
    }

    public static NfcHost fromContextId(int arg1) {
        return NfcHost.sContextHostsMap.get(arg1);
    }

    public void onWindowAndroidChanged(WindowAndroid arg2) {
        Object v2 = arg2 != null ? arg2.getActivity().get() : null;
        this.mCallback.onResult(v2);
    }

    public void stopTrackingActivityChanges() {
        this.mCallback = null;
        this.mContentViewCore.removeWindowAndroidChangedObserver(((WindowAndroidChangedObserver)this));
    }

    public void trackActivityChanges(Callback arg2) {
        this.mCallback = arg2;
        this.mContentViewCore.addWindowAndroidChangedObserver(((WindowAndroidChangedObserver)this));
        WindowAndroid v2 = this.mWebContents.getTopLevelNativeWindow();
        Callback v0 = this.mCallback;
        Object v2_1 = v2 != null ? v2.getActivity().get() : null;
        v0.onResult(v2_1);
    }
}

