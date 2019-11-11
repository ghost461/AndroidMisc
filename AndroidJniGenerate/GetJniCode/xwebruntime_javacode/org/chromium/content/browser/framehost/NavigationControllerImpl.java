package org.chromium.content.browser.framehost;

import android.graphics.Bitmap;
import org.chromium.base.VisibleForTesting;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.content_public.browser.NavigationController;
import org.chromium.content_public.browser.NavigationEntry;
import org.chromium.content_public.browser.NavigationHistory;
import org.chromium.content_public.common.ResourceRequestBody;

@JNINamespace(value="content") class NavigationControllerImpl implements NavigationController {
    private long mNativeNavigationControllerAndroid;

    private NavigationControllerImpl(long arg1) {
        super();
        this.mNativeNavigationControllerAndroid = arg1;
    }

    @CalledByNative private static void addToNavigationHistory(Object arg0, Object arg1) {
        ((NavigationHistory)arg0).addEntry(((NavigationEntry)arg1));
    }

    public boolean canCopyStateOver() {
        boolean v0 = this.mNativeNavigationControllerAndroid == 0 || !this.nativeCanCopyStateOver(this.mNativeNavigationControllerAndroid) ? false : true;
        return v0;
    }

    public boolean canGoBack() {
        boolean v0 = this.mNativeNavigationControllerAndroid == 0 || !this.nativeCanGoBack(this.mNativeNavigationControllerAndroid) ? false : true;
        return v0;
    }

    public boolean canGoForward() {
        boolean v0 = this.mNativeNavigationControllerAndroid == 0 || !this.nativeCanGoForward(this.mNativeNavigationControllerAndroid) ? false : true;
        return v0;
    }

    @VisibleForTesting public boolean canGoToOffset(int arg6) {
        boolean v6 = this.mNativeNavigationControllerAndroid == 0 || !this.nativeCanGoToOffset(this.mNativeNavigationControllerAndroid, arg6) ? false : true;
        return v6;
    }

    public boolean canPruneAllButLastCommitted() {
        boolean v0 = this.mNativeNavigationControllerAndroid == 0 || !this.nativeCanPruneAllButLastCommitted(this.mNativeNavigationControllerAndroid) ? false : true;
        return v0;
    }

    public void cancelPendingReload() {
        if(this.mNativeNavigationControllerAndroid != 0) {
            this.nativeCancelPendingReload(this.mNativeNavigationControllerAndroid);
        }
    }

    @VisibleForTesting public void clearHistory() {
        if(this.mNativeNavigationControllerAndroid != 0) {
            this.nativeClearHistory(this.mNativeNavigationControllerAndroid);
        }
    }

    public void clearSslPreferences() {
        if(this.mNativeNavigationControllerAndroid != 0) {
            this.nativeClearSslPreferences(this.mNativeNavigationControllerAndroid);
        }
    }

    public void continuePendingReload() {
        if(this.mNativeNavigationControllerAndroid != 0) {
            this.nativeContinuePendingReload(this.mNativeNavigationControllerAndroid);
        }
    }

    public void copyStateFrom(NavigationController arg12, boolean arg13) {
        long v2 = 0;
        if(this.mNativeNavigationControllerAndroid == v2) {
            return;
        }

        if(((NavigationControllerImpl)arg12).mNativeNavigationControllerAndroid == v2) {
            return;
        }

        this.nativeCopyStateFrom(this.mNativeNavigationControllerAndroid, ((NavigationControllerImpl)arg12).mNativeNavigationControllerAndroid, arg13);
    }

    public void copyStateFromAndPrune(NavigationController arg12, boolean arg13) {
        long v2 = 0;
        if(this.mNativeNavigationControllerAndroid == v2) {
            return;
        }

        if(((NavigationControllerImpl)arg12).mNativeNavigationControllerAndroid == v2) {
            return;
        }

        this.nativeCopyStateFromAndPrune(this.mNativeNavigationControllerAndroid, ((NavigationControllerImpl)arg12).mNativeNavigationControllerAndroid, arg13);
    }

    @CalledByNative private static NavigationControllerImpl create(long arg1) {
        return new NavigationControllerImpl(arg1);
    }

    @CalledByNative private static NavigationEntry createNavigationEntry(int arg9, String arg10, String arg11, String arg12, String arg13, Bitmap arg14, int arg15) {
        return new NavigationEntry(arg9, arg10, arg11, arg12, arg13, arg14, arg15);
    }

    @CalledByNative private void destroy() {
        this.mNativeNavigationControllerAndroid = 0;
    }

    public NavigationHistory getDirectedNavigationHistory(boolean arg8, int arg9) {
        if(this.mNativeNavigationControllerAndroid == 0) {
            return null;
        }

        NavigationHistory v6 = new NavigationHistory();
        this.nativeGetDirectedNavigationHistory(this.mNativeNavigationControllerAndroid, v6, arg8, arg9);
        return v6;
    }

    public NavigationEntry getEntryAtIndex(int arg6) {
        if(this.mNativeNavigationControllerAndroid != 0) {
            return this.nativeGetEntryAtIndex(this.mNativeNavigationControllerAndroid, arg6);
        }

        return null;
    }

    public String getEntryExtraData(int arg6, String arg7) {
        if(this.mNativeNavigationControllerAndroid == 0) {
            return null;
        }

        return this.nativeGetEntryExtraData(this.mNativeNavigationControllerAndroid, arg6, arg7);
    }

    public int getLastCommittedEntryIndex() {
        if(this.mNativeNavigationControllerAndroid != 0) {
            return this.nativeGetLastCommittedEntryIndex(this.mNativeNavigationControllerAndroid);
        }

        return -1;
    }

    public NavigationHistory getNavigationHistory() {
        if(this.mNativeNavigationControllerAndroid == 0) {
            return null;
        }

        NavigationHistory v0 = new NavigationHistory();
        v0.setCurrentEntryIndex(this.nativeGetNavigationHistory(this.mNativeNavigationControllerAndroid, v0));
        return v0;
    }

    public String getOriginalUrlForVisibleNavigationEntry() {
        if(this.mNativeNavigationControllerAndroid == 0) {
            return null;
        }

        return this.nativeGetOriginalUrlForVisibleNavigationEntry(this.mNativeNavigationControllerAndroid);
    }

    public NavigationEntry getPendingEntry() {
        if(this.mNativeNavigationControllerAndroid != 0) {
            return this.nativeGetPendingEntry(this.mNativeNavigationControllerAndroid);
        }

        return null;
    }

    public boolean getUseDesktopUserAgent() {
        if(this.mNativeNavigationControllerAndroid == 0) {
            return 0;
        }

        return this.nativeGetUseDesktopUserAgent(this.mNativeNavigationControllerAndroid);
    }

    public void goBack() {
        if(this.mNativeNavigationControllerAndroid != 0) {
            this.nativeGoBack(this.mNativeNavigationControllerAndroid);
        }
    }

    public void goForward() {
        if(this.mNativeNavigationControllerAndroid != 0) {
            this.nativeGoForward(this.mNativeNavigationControllerAndroid);
        }
    }

    public void goToNavigationIndex(int arg6) {
        if(this.mNativeNavigationControllerAndroid != 0) {
            this.nativeGoToNavigationIndex(this.mNativeNavigationControllerAndroid, arg6);
        }
    }

    public void goToOffset(int arg6) {
        if(this.mNativeNavigationControllerAndroid != 0) {
            this.nativeGoToOffset(this.mNativeNavigationControllerAndroid, arg6);
        }
    }

    public boolean isInitialNavigation() {
        boolean v0 = this.mNativeNavigationControllerAndroid == 0 || !this.nativeIsInitialNavigation(this.mNativeNavigationControllerAndroid) ? false : true;
        return v0;
    }

    public void loadIfNecessary() {
        if(this.mNativeNavigationControllerAndroid != 0) {
            this.nativeLoadIfNecessary(this.mNativeNavigationControllerAndroid);
        }
    }

    public void loadUrl(LoadUrlParams arg19) {
        NavigationControllerImpl v15 = this;
        if(v15.mNativeNavigationControllerAndroid != 0) {
            long v1 = v15.mNativeNavigationControllerAndroid;
            String v3 = arg19.getUrl();
            int v4 = arg19.getLoadUrlType();
            int v5 = arg19.getTransitionType();
            String v0 = arg19.getReferrer() != null ? arg19.getReferrer().getUrl() : null;
            String v6 = v0;
            int v7 = arg19.getReferrer() != null ? arg19.getReferrer().getPolicy() : 0;
            v15.nativeLoadUrl(v1, v3, v4, v5, v6, v7, arg19.getUserAgentOverrideOption(), arg19.getExtraHeadersString(), arg19.getPostData(), arg19.getBaseUrl(), arg19.getVirtualUrlForDataUrl(), arg19.getDataUrlAsString(), arg19.getCanLoadLocalResources(), arg19.getIsRendererInitiated(), arg19.getShouldReplaceCurrentEntry());
        }
    }

    private native boolean nativeCanCopyStateOver(long arg1) {
    }

    private native boolean nativeCanGoBack(long arg1) {
    }

    private native boolean nativeCanGoForward(long arg1) {
    }

    private native boolean nativeCanGoToOffset(long arg1, int arg2) {
    }

    private native boolean nativeCanPruneAllButLastCommitted(long arg1) {
    }

    private native void nativeCancelPendingReload(long arg1) {
    }

    private native void nativeClearHistory(long arg1) {
    }

    private native void nativeClearSslPreferences(long arg1) {
    }

    private native void nativeContinuePendingReload(long arg1) {
    }

    private native void nativeCopyStateFrom(long arg1, long arg2, boolean arg3) {
    }

    private native void nativeCopyStateFromAndPrune(long arg1, long arg2, boolean arg3) {
    }

    private native void nativeGetDirectedNavigationHistory(long arg1, NavigationHistory arg2, boolean arg3, int arg4) {
    }

    private native NavigationEntry nativeGetEntryAtIndex(long arg1, int arg2) {
    }

    private native String nativeGetEntryExtraData(long arg1, int arg2, String arg3) {
    }

    private native int nativeGetLastCommittedEntryIndex(long arg1) {
    }

    private native int nativeGetNavigationHistory(long arg1, Object arg2) {
    }

    private native String nativeGetOriginalUrlForVisibleNavigationEntry(long arg1) {
    }

    private native NavigationEntry nativeGetPendingEntry(long arg1) {
    }

    private native boolean nativeGetUseDesktopUserAgent(long arg1) {
    }

    private native void nativeGoBack(long arg1) {
    }

    private native void nativeGoForward(long arg1) {
    }

    private native void nativeGoToNavigationIndex(long arg1, int arg2) {
    }

    private native void nativeGoToOffset(long arg1, int arg2) {
    }

    private native boolean nativeIsInitialNavigation(long arg1) {
    }

    private native void nativeLoadIfNecessary(long arg1) {
    }

    private native void nativeLoadUrl(long arg1, String arg2, int arg3, int arg4, String arg5, int arg6, int arg7, String arg8, ResourceRequestBody arg9, String arg10, String arg11, String arg12, boolean arg13, boolean arg14, boolean arg15) {
    }

    private native void nativeReload(long arg1, boolean arg2) {
    }

    private native void nativeReloadBypassingCache(long arg1, boolean arg2) {
    }

    private native boolean nativeRemoveEntryAtIndex(long arg1, int arg2) {
    }

    private native void nativeRequestRestoreLoad(long arg1) {
    }

    private native void nativeSetEntryExtraData(long arg1, int arg2, String arg3, String arg4) {
    }

    private native void nativeSetUseDesktopUserAgent(long arg1, boolean arg2, boolean arg3) {
    }

    public void reload(boolean arg6) {
        if(this.mNativeNavigationControllerAndroid != 0) {
            this.nativeReload(this.mNativeNavigationControllerAndroid, arg6);
        }
    }

    public void reloadBypassingCache(boolean arg6) {
        if(this.mNativeNavigationControllerAndroid != 0) {
            this.nativeReloadBypassingCache(this.mNativeNavigationControllerAndroid, arg6);
        }
    }

    public boolean removeEntryAtIndex(int arg6) {
        if(this.mNativeNavigationControllerAndroid != 0) {
            return this.nativeRemoveEntryAtIndex(this.mNativeNavigationControllerAndroid, arg6);
        }

        return 0;
    }

    public void requestRestoreLoad() {
        if(this.mNativeNavigationControllerAndroid != 0) {
            this.nativeRequestRestoreLoad(this.mNativeNavigationControllerAndroid);
        }
    }

    public void setEntryExtraData(int arg12, String arg13, String arg14) {
        if(this.mNativeNavigationControllerAndroid == 0) {
            return;
        }

        this.nativeSetEntryExtraData(this.mNativeNavigationControllerAndroid, arg12, arg13, arg14);
    }

    public void setUseDesktopUserAgent(boolean arg6, boolean arg7) {
        if(this.mNativeNavigationControllerAndroid != 0) {
            this.nativeSetUseDesktopUserAgent(this.mNativeNavigationControllerAndroid, arg6, arg7);
        }
    }
}

