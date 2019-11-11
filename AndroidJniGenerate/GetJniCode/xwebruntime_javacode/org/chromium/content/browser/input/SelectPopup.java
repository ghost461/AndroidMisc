package org.chromium.content.browser.input;

import android.content.Context;
import android.view.View;
import java.util.ArrayList;
import java.util.List;
import org.chromium.base.VisibleForTesting;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.content.browser.PopupController$HideablePopup;
import org.chromium.content.browser.PopupController;
import org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl;
import org.chromium.content.browser.webcontents.WebContentsImpl;
import org.chromium.content_public.browser.WebContents$UserDataFactory;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.ui.base.WindowAndroid;

@JNINamespace(value="content") public class SelectPopup implements HideablePopup {
    public interface Ui {
        void hide(boolean arg1);

        void show();
    }

    final class UserDataFactoryLazyHolder {
        private static final UserDataFactory INSTANCE;

        static {
            UserDataFactoryLazyHolder.INSTANCE = SelectPopup$UserDataFactoryLazyHolder$$Lambda$0.$instance;
        }

        private UserDataFactoryLazyHolder() {
            super();
        }

        static UserDataFactory access$000() {
            return UserDataFactoryLazyHolder.INSTANCE;
        }
    }

    private View mContainerView;
    private Context mContext;
    private boolean mInitialized;
    private long mNativeSelectPopup;
    private long mNativeSelectPopupSourceFrame;
    private Ui mPopupView;
    private final WebContentsImpl mWebContents;

    static {
    }

    public SelectPopup(WebContents arg1) {
        super();
        this.mWebContents = ((WebContentsImpl)arg1);
    }

    public void close() {
        this.mPopupView = null;
    }

    public static SelectPopup create(Context arg2, WebContents arg3, View arg4) {
        Object v3 = arg3.getOrSetUserData(SelectPopup.class, UserDataFactoryLazyHolder.access$000());
        ((SelectPopup)v3).init(arg2, arg4);
        return ((SelectPopup)v3);
    }

    @CalledByNative private void destroy() {
        this.mNativeSelectPopup = 0;
    }

    public static SelectPopup fromWebContents(WebContents arg2) {
        return arg2.getOrSetUserData(SelectPopup.class, null);
    }

    private WindowAndroid getWindowAndroid() {
        WindowAndroid v0 = this.mNativeSelectPopup != 0 ? this.nativeGetWindowAndroid(this.mNativeSelectPopup) : null;
        return v0;
    }

    public void hide() {
        if(this.mPopupView != null) {
            this.mPopupView.hide(true);
        }
    }

    @CalledByNative public void hideWithoutCancel() {
        if(this.mPopupView == null) {
            return;
        }

        this.mPopupView.hide(false);
        this.mPopupView = null;
        this.mNativeSelectPopupSourceFrame = 0;
    }

    private void init(Context arg1, View arg2) {
        this.mContext = arg1;
        this.mContainerView = arg2;
        this.mNativeSelectPopup = this.nativeInit(this.mWebContents);
        PopupController.register(this.mWebContents, ((HideablePopup)this));
        this.mInitialized = true;
    }

    private boolean initialized() {
        return this.mInitialized;
    }

    @VisibleForTesting public boolean isVisibleForTesting() {
        boolean v0 = this.mPopupView != null ? true : false;
        return v0;
    }

    private native WindowAndroid nativeGetWindowAndroid(long arg1) {
    }

    private native long nativeInit(WebContents arg1) {
    }

    private native void nativeSelectMenuItems(long arg1, long arg2, int[] arg3) {
    }

    public void selectMenuItems(int[] arg12) {
        long v2 = 0;
        if(this.mNativeSelectPopup != v2) {
            this.nativeSelectMenuItems(this.mNativeSelectPopup, this.mNativeSelectPopupSourceFrame, arg12);
        }

        this.mNativeSelectPopupSourceFrame = v2;
        this.mPopupView = null;
    }

    public void setContainerView(View arg1) {
        this.mContainerView = arg1;
    }

    @CalledByNative private void show(View arg12, long arg13, String[] arg15, int[] arg16, boolean arg17, int[] arg18, boolean arg19) {
        SelectPopup v7 = this;
        long v8 = arg13;
        String[] v0 = arg15;
        if(v7.mContainerView.getParent() != null) {
            if(v7.mContainerView.getVisibility() != 0) {
            }
            else {
                PopupController.hidePopupsAndClearSelection(v7.mWebContents);
                ArrayList v4 = new ArrayList();
                int v1;
                for(v1 = 0; v1 < v0.length; ++v1) {
                    ((List)v4).add(new SelectPopupItem(v0[v1], arg16[v1]));
                }

                WebContentsAccessibilityImpl v0_1 = WebContentsAccessibilityImpl.fromWebContents(v7.mWebContents);
                if(!DeviceFormFactor.isTablet() || (arg17) || (v0_1.isTouchExplorationEnabled())) {
                    WindowAndroid v0_2 = v7.getWindowAndroid();
                    if(v0_2 == null) {
                        return;
                    }
                    else {
                        Object v2 = v0_2.getContext().get();
                        if(v2 == null) {
                            return;
                        }
                        else {
                            v7.mPopupView = new SelectPopupDialog(v7, ((Context)v2), v4, arg17, arg18);
                        }
                    }
                }
                else {
                    v7.mPopupView = new SelectPopupDropdown(v7, v7.mContext, arg12, ((List)v4), arg18, arg19);
                }

                v7.mNativeSelectPopupSourceFrame = v8;
                v7.mPopupView.show();
                return;
            }
        }

        v7.mNativeSelectPopupSourceFrame = v8;
        v7.selectMenuItems(null);
    }
}

