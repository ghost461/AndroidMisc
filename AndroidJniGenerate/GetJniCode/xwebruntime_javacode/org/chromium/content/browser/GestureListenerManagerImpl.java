package org.chromium.content.browser;

import android.view.View;
import org.chromium.base.ObserverList$RewindableIterator;
import org.chromium.base.ObserverList;
import org.chromium.base.TraceEvent;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.content.browser.input.ImeAdapterImpl;
import org.chromium.content.browser.selection.SelectionPopupControllerImpl;
import org.chromium.content.browser.webcontents.WebContentsImpl;
import org.chromium.content.browser.webcontents.WebContentsUserData;
import org.chromium.content_public.browser.ContentViewCore$InternalAccessDelegate;
import org.chromium.content_public.browser.GestureListenerManager;
import org.chromium.content_public.browser.GestureStateListener;
import org.chromium.content_public.browser.WebContents$UserDataFactory;
import org.chromium.content_public.browser.WebContents;

@JNINamespace(value="content") public class GestureListenerManagerImpl implements WindowEventObserver, GestureListenerManager {
    final class UserDataFactoryLazyHolder {
        private static final UserDataFactory INSTANCE;

        static {
            UserDataFactoryLazyHolder.INSTANCE = GestureListenerManagerImpl$UserDataFactoryLazyHolder$$Lambda$0.$instance;
        }

        private UserDataFactoryLazyHolder() {
            super();
        }

        static UserDataFactory access$000() {
            return UserDataFactoryLazyHolder.INSTANCE;
        }
    }

    private View mContainerView;
    private boolean mIsTouchScrollInProgress;
    private final RewindableIterator mIterator;
    private final ObserverList mListeners;
    private long mNativeGestureListenerManager;
    private int mPotentiallyActiveFlingCount;
    private InternalAccessDelegate mScrollDelegate;
    private final WebContentsImpl mWebContents;

    public GestureListenerManagerImpl(WebContents arg3) {
        super();
        this.mWebContents = ((WebContentsImpl)arg3);
        this.mListeners = new ObserverList();
        this.mIterator = this.mListeners.rewindableIterator();
        this.mNativeGestureListenerManager = this.nativeInit(this.mWebContents);
    }

    public void addListener(GestureStateListener arg2) {
        this.mListeners.addObserver(arg2);
    }

    @CalledByNative private boolean filterTapOrPressEvent(int arg2, int arg3, int arg4) {
        if(arg2 == 5 && (this.offerLongPressToEmbedder())) {
            return 1;
        }

        TapDisambiguator v2 = TapDisambiguator.fromWebContents(this.mWebContents);
        if(!v2.isShowing()) {
            v2.setLastTouch(((float)arg3), ((float)arg4));
        }

        return 0;
    }

    public static GestureListenerManagerImpl fromWebContents(WebContents arg2) {
        return WebContentsUserData.fromWebContents(arg2, GestureListenerManagerImpl.class, UserDataFactoryLazyHolder.access$000());
    }

    private SelectionPopupControllerImpl getSelectionPopupController() {
        return SelectionPopupControllerImpl.fromWebContents(this.mWebContents);
    }

    public boolean hasPotentiallyActiveFling() {
        boolean v0 = this.mPotentiallyActiveFlingCount > 0 ? true : false;
        return v0;
    }

    public boolean isScrollInProgress() {
        boolean v0 = (this.mIsTouchScrollInProgress) || (this.hasPotentiallyActiveFling()) ? true : false;
        return v0;
    }

    private native long nativeInit(WebContentsImpl arg1) {
    }

    private native void nativeReset(long arg1) {
    }

    private boolean offerLongPressToEmbedder() {
        return this.mContainerView.performLongClick();
    }

    public void onAttachedToWindow() {
        WindowEventObserver$$CC.onAttachedToWindow(((WindowEventObserver)this));
    }

    @CalledByNative private void onDestroy() {
        this.mIterator.rewind();
        while(this.mIterator.hasNext()) {
            this.mIterator.next().onDestroyed();
        }

        this.mListeners.clear();
        this.mNativeGestureListenerManager = 0;
    }

    public void onDetachedFromWindow() {
        WindowEventObserver$$CC.onDetachedFromWindow(((WindowEventObserver)this));
    }

    @CalledByNative private void onFlingEnd() {
        if(this.mPotentiallyActiveFlingCount > 0) {
            --this.mPotentiallyActiveFlingCount;
        }

        this.setTouchScrollInProgress(false);
        this.mIterator.rewind();
        while(this.mIterator.hasNext()) {
            this.mIterator.next().onFlingEndGesture(this.verticalScrollOffset(), this.verticalScrollExtent());
        }
    }

    @CalledByNative private void onFlingStartEventConsumed() {
        ++this.mPotentiallyActiveFlingCount;
        this.setTouchScrollInProgress(false);
        this.mIterator.rewind();
        while(this.mIterator.hasNext()) {
            this.mIterator.next().onFlingStartGesture(this.verticalScrollOffset(), this.verticalScrollExtent());
        }
    }

    @CalledByNative private void onLongPressAck() {
        this.mContainerView.performHapticFeedback(0);
        this.mIterator.rewind();
        while(this.mIterator.hasNext()) {
            this.mIterator.next().onLongPress();
        }
    }

    @CalledByNative private void onPinchBeginEventAck() {
        this.mIterator.rewind();
        while(this.mIterator.hasNext()) {
            this.mIterator.next().onPinchStarted();
        }
    }

    @CalledByNative private void onPinchEndEventAck() {
        this.mIterator.rewind();
        while(this.mIterator.hasNext()) {
            this.mIterator.next().onPinchEnded();
        }
    }

    @CalledByNative private void onScrollBeginEventAck() {
        this.setTouchScrollInProgress(true);
        this.mIterator.rewind();
        while(this.mIterator.hasNext()) {
            this.mIterator.next().onScrollStarted(this.verticalScrollOffset(), this.verticalScrollExtent());
        }
    }

    @CalledByNative private void onScrollEndEventAck() {
        this.updateOnScrollEnd();
    }

    @CalledByNative private void onScrollUpdateGestureConsumed() {
        SelectionPopupControllerImpl v0 = this.getSelectionPopupController();
        if(v0 != null) {
            v0.destroyPastePopup();
        }

        this.mIterator.rewind();
        while(this.mIterator.hasNext()) {
            this.mIterator.next().onScrollUpdateGestureConsumed();
        }
    }

    @CalledByNative private void onSingleTapEventAck(boolean arg2) {
        SelectionPopupControllerImpl v0 = this.getSelectionPopupController();
        if(v0 != null) {
            v0.destroyPastePopup();
        }

        this.mIterator.rewind();
        while(this.mIterator.hasNext()) {
            this.mIterator.next().onSingleTap(arg2);
        }
    }

    public void onWindowFocusChanged(boolean arg2) {
        this.mIterator.rewind();
        while(this.mIterator.hasNext()) {
            this.mIterator.next().onWindowFocusChanged(arg2);
        }
    }

    public void removeListener(GestureStateListener arg2) {
        this.mListeners.removeObserver(arg2);
    }

    public void reset() {
        if(this.mNativeGestureListenerManager != 0) {
            this.nativeReset(this.mNativeGestureListenerManager);
        }
    }

    public void resetFlingGesture() {
        if(this.mPotentiallyActiveFlingCount > 0) {
            this.onFlingEnd();
            this.mPotentiallyActiveFlingCount = 0;
        }
    }

    @CalledByNative private void resetPopupsAndInput(boolean arg2) {
        PopupController.hidePopupsAndClearSelection(this.mWebContents);
        this.resetScrollInProgress();
        if(arg2) {
            ImeAdapterImpl v2 = ImeAdapterImpl.fromWebContents(this.mWebContents);
            if(v2 != null) {
                v2.resetAndHideKeyboard();
            }
        }
    }

    void resetScrollInProgress() {
        if(!this.isScrollInProgress()) {
            return;
        }

        boolean v0 = this.mIsTouchScrollInProgress;
        this.setTouchScrollInProgress(false);
        if(v0) {
            this.updateOnScrollEnd();
        }

        this.resetFlingGesture();
    }

    public void setContainerView(View arg1) {
        this.mContainerView = arg1;
    }

    public void setScrollDelegate(InternalAccessDelegate arg1) {
        this.mScrollDelegate = arg1;
    }

    void setTouchScrollInProgress(boolean arg2) {
        this.mIsTouchScrollInProgress = arg2;
        this.getSelectionPopupController().setScrollInProgress(arg2);
    }

    public void updateOnScaleLimitsChanged(float arg2, float arg3) {
        this.mIterator.rewind();
        while(this.mIterator.hasNext()) {
            this.mIterator.next().onScaleLimitsChanged(arg2, arg3);
        }
    }

    public void updateOnScrollChanged(int arg2, int arg3) {
        this.mIterator.rewind();
        while(this.mIterator.hasNext()) {
            this.mIterator.next().onScrollOffsetOrExtentChanged(arg2, arg3);
        }
    }

    public void updateOnScrollEnd() {
        this.setTouchScrollInProgress(false);
        this.mIterator.rewind();
        while(this.mIterator.hasNext()) {
            this.mIterator.next().onScrollEnded(this.verticalScrollOffset(), this.verticalScrollExtent());
        }
    }

    @CalledByNative private void updateOnTouchDown() {
        this.mIterator.rewind();
        while(this.mIterator.hasNext()) {
            this.mIterator.next().onTouchDown();
        }
    }

    @CalledByNative private void updateScrollInfo(float arg17, float arg18, float arg19, float arg20, float arg21, float arg22, float arg23, float arg24, float arg25, float arg26, boolean arg27) {
        GestureListenerManagerImpl v0 = this;
        float v2 = arg17;
        float v3 = arg18;
        float v12 = arg20;
        float v13 = arg21;
        TraceEvent.begin("GestureListenerManagerImpl:updateScrollInfo");
        RenderCoordinates v1 = v0.mWebContents.getRenderCoordinates();
        float v4 = v1.getDeviceScaleFactor() * arg19;
        float v5 = Math.max(arg22, (((float)v0.mContainerView.getWidth())) / v4);
        float v6 = Math.max(arg23, (((float)v0.mContainerView.getHeight())) / v4);
        int v4_1 = Float.compare(v5, v1.getContentWidthCss()) != 0 || v6 != v1.getContentHeightCss() ? 1 : 0;
        int v14 = v12 != v1.getMinPageScaleFactor() || v13 != v1.getMaxPageScaleFactor() ? 1 : 0;
        int v10 = arg19 != v1.getPageScaleFactor() ? 1 : 0;
        int v15 = v10 != 0 || v2 != v1.getScrollX() || v3 != v1.getScrollY() ? 1 : 0;
        if(v4_1 != 0 || v15 != 0) {
            TapDisambiguator.fromWebContents(v0.mWebContents).hidePopup(true);
        }

        if(v15 != 0) {
            v0.mScrollDelegate.onScrollChanged(((int)v1.fromLocalCssToPix(v2)), ((int)v1.fromLocalCssToPix(v3)), ((int)v1.getScrollXPix()), ((int)v1.getScrollYPix()));
        }

        v1.updateFrameInfo(v2, v3, v5, v6, arg24, arg25, arg19, v12, v13, arg26);
        if(v15 != 0 || (arg27)) {
            v0.updateOnScrollChanged(this.verticalScrollOffset(), this.verticalScrollExtent());
        }

        if(v14 != 0) {
            v0.updateOnScaleLimitsChanged(v12, v13);
        }

        TraceEvent.end("GestureListenerManagerImpl:updateScrollInfo");
    }

    private int verticalScrollExtent() {
        return this.mWebContents.getRenderCoordinates().getLastFrameViewportHeightPixInt();
    }

    private int verticalScrollOffset() {
        return this.mWebContents.getRenderCoordinates().getScrollYPixInt();
    }
}

