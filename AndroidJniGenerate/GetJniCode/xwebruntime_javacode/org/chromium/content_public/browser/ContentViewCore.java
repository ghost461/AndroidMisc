package org.chromium.content_public.browser;

import android.content.res.Configuration;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.ViewGroup;
import org.chromium.base.VisibleForTesting;
import org.chromium.ui.base.ViewAndroidDelegate;
import org.chromium.ui.base.WindowAndroid;

public interface ContentViewCore {
    public interface InternalAccessDelegate {
        void onScrollChanged(int arg1, int arg2, int arg3, int arg4);

        boolean super_dispatchKeyEvent(KeyEvent arg1);

        void super_onConfigurationChanged(Configuration arg1);

        boolean super_onGenericMotionEvent(MotionEvent arg1);

        boolean super_onKeyUp(int arg1, KeyEvent arg2);
    }

    void SetHorizontalScrollBarEnable(boolean arg1);

    void SetVerticalScrollBarEnable(boolean arg1);

    int computeHorizontalScrollExtent();

    int computeHorizontalScrollOffset();

    int computeHorizontalScrollRange();

    int computeVerticalScrollExtent();

    int computeVerticalScrollOffset();

    int computeVerticalScrollRange();

    void destroy();

    boolean dispatchKeyEvent(KeyEvent arg1);

    ViewGroup getContainerView();

    @VisibleForTesting int getTopControlsShrinkBlinkHeightForTesting();

    ViewAndroidDelegate getViewAndroidDelegate();

    WebContents getWebContents();

    boolean isAlive();

    @VisibleForTesting boolean isSelectPopupVisibleForTest();

    void onAttachedToWindow();

    void onConfigurationChanged(Configuration arg1);

    void onDetachedFromWindow();

    boolean onGenericMotionEvent(MotionEvent arg1);

    boolean onKeyUp(int arg1, KeyEvent arg2);

    void onPause();

    void onResume();

    void onViewFocusChanged(boolean arg1);

    void onWindowFocusChanged(boolean arg1);

    void scrollBy(float arg1, float arg2);

    void scrollTo(float arg1, float arg2);

    void setContainerView(ViewGroup arg1);

    void setContainerViewInternals(InternalAccessDelegate arg1);

    void setHideKeyboardOnBlur(boolean arg1);

    void updateDoubleTapSupport(boolean arg1);

    void updateMultiTouchZoomSupport(boolean arg1);

    void updateWindowAndroid(WindowAndroid arg1);
}

