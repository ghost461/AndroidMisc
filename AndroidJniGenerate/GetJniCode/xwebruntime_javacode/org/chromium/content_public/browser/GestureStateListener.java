package org.chromium.content_public.browser;

public interface GestureStateListener {
    void onDestroyed();

    void onFlingEndGesture(int arg1, int arg2);

    void onFlingStartGesture(int arg1, int arg2);

    void onLongPress();

    void onPinchEnded();

    void onPinchStarted();

    void onScaleLimitsChanged(float arg1, float arg2);

    void onScrollEnded(int arg1, int arg2);

    void onScrollOffsetOrExtentChanged(int arg1, int arg2);

    void onScrollStarted(int arg1, int arg2);

    void onScrollUpdateGestureConsumed();

    void onShowUnhandledTapUIIfNeeded(int arg1, int arg2);

    void onSingleTap(boolean arg1);

    void onTouchDown();

    void onWindowFocusChanged(boolean arg1);
}

