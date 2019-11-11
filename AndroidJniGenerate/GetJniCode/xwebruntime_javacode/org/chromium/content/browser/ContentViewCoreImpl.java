package org.chromium.content.browser;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build$VERSION;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import java.util.Iterator;
import org.chromium.base.ObserverList;
import org.chromium.base.TraceEvent;
import org.chromium.base.VisibleForTesting;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl;
import org.chromium.content.browser.input.ImeAdapterImpl;
import org.chromium.content.browser.input.SelectPopup;
import org.chromium.content.browser.input.TextSuggestionHost;
import org.chromium.content.browser.selection.SelectionPopupControllerImpl;
import org.chromium.content.browser.webcontents.WebContentsImpl;
import org.chromium.content_public.browser.ActionModeCallbackHelper;
import org.chromium.content_public.browser.ContentViewCore$InternalAccessDelegate;
import org.chromium.content_public.browser.ContentViewCore;
import org.chromium.content_public.browser.ImeEventObserver;
import org.chromium.content_public.browser.WebContents$UserDataFactory;
import org.chromium.content_public.browser.WebContents;
import org.chromium.device.gamepad.GamepadList;
import org.chromium.ui.base.EventForwarder;
import org.chromium.ui.base.ViewAndroidDelegate;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.display.DisplayAndroid$DisplayAndroidObserver;
import org.chromium.ui.display.DisplayAndroid;

@JNINamespace(value="content") public class ContentViewCoreImpl implements ContentViewCore, DisplayAndroidObserver {
    final class UserDataFactoryLazyHolder {
        private static final UserDataFactory INSTANCE;

        static {
            UserDataFactoryLazyHolder.INSTANCE = ContentViewCoreImpl$UserDataFactoryLazyHolder$$Lambda$0.$instance;
        }

        private UserDataFactoryLazyHolder() {
            super();
        }

        static UserDataFactory access$000() {
            return UserDataFactoryLazyHolder.INSTANCE;
        }
    }

    public static final int INVALID_RENDER_PROCESS_PID = 0;
    private static final String TAG = "cr_ContentViewCore";
    private boolean mAttachedToWindow;
    private ViewGroup mContainerView;
    private InternalAccessDelegate mContainerViewInternals;
    private Context mContext;
    private Boolean mHasInputFocus;
    private Boolean mHasViewFocus;
    private boolean mHideKeyboardOnBlur;
    private boolean mInitialized;
    private long mNativeContentViewCore;
    private boolean mPaused;
    private RenderCoordinates mRenderCoordinates;
    private ViewAndroidDelegate mViewAndroidDelegate;
    private WebContentsImpl mWebContents;
    private WindowAndroid mWindowAndroid;
    private final ObserverList mWindowAndroidChangedObservers;
    private final ObserverList mWindowEventObservers;

    static {
    }

    public ContentViewCoreImpl(WebContents arg2) {
        super();
        this.mWindowEventObservers = new ObserverList();
        this.mWindowAndroidChangedObservers = new ObserverList();
        this.mWebContents = ((WebContentsImpl)arg2);
    }

    public void SetHorizontalScrollBarEnable(boolean arg6) {
        if(this.mNativeContentViewCore == 0) {
            return;
        }

        this.nativeSetHorizontalScrollBarEnable(this.mNativeContentViewCore, arg6);
    }

    public void SetVerticalScrollBarEnable(boolean arg6) {
        if(this.mNativeContentViewCore == 0) {
            return;
        }

        this.nativeSetVerticalScrollbarEnable(this.mNativeContentViewCore, arg6);
    }

    private void addDisplayAndroidObserverIfNeeded() {
        if(!this.mAttachedToWindow) {
            return;
        }

        WindowAndroid v0 = this.getWindowAndroid();
        if(v0 != null) {
            DisplayAndroid v0_1 = v0.getDisplay();
            v0_1.addObserver(((DisplayAndroidObserver)this));
            this.onRotationChanged(v0_1.getRotation());
            this.onDIPScaleChanged(v0_1.getDipScale());
        }
    }

    public void addWindowAndroidChangedObserver(WindowAndroidChangedObserver arg2) {
        this.mWindowAndroidChangedObservers.addObserver(arg2);
    }

    public int computeHorizontalScrollExtent() {
        return this.mRenderCoordinates.getLastFrameViewportWidthPixInt();
    }

    public int computeHorizontalScrollOffset() {
        return this.mRenderCoordinates.getScrollXPixInt();
    }

    public int computeHorizontalScrollRange() {
        return this.mRenderCoordinates.getContentWidthPixInt();
    }

    public int computeVerticalScrollExtent() {
        return this.mRenderCoordinates.getLastFrameViewportHeightPixInt();
    }

    public int computeVerticalScrollOffset() {
        return this.mRenderCoordinates.getScrollYPixInt();
    }

    public int computeVerticalScrollRange() {
        return this.mRenderCoordinates.getContentHeightPixInt();
    }

    public static ContentViewCoreImpl create(Context arg6, String arg7, WebContents arg8, ViewAndroidDelegate arg9, InternalAccessDelegate arg10, WindowAndroid arg11) {
        Object v8 = arg8.getOrSetUserData(ContentViewCoreImpl.class, UserDataFactoryLazyHolder.access$000());
        v8.initialize(arg6, arg7, arg9, arg10, arg11);
        return ((ContentViewCoreImpl)v8);
    }

    public void destroy() {
        this.removeDisplayAndroidObserver();
        long v2 = 0;
        if(this.mNativeContentViewCore != v2) {
            this.nativeOnJavaContentViewCoreDestroyed(this.mNativeContentViewCore);
        }

        ImeAdapterImpl v0 = this.getImeAdapter();
        v0.resetAndHideKeyboard();
        v0.removeEventObserver(this.getSelectionPopupController());
        v0.removeEventObserver(this.getJoystick());
        v0.removeEventObserver(this.getTapDisambiguator());
        this.getGestureListenerManager().reset();
        this.removeWindowAndroidChangedObserver(this.getTextSuggestionHost());
        this.mWindowEventObservers.clear();
        this.hidePopupsAndPreserveSelection();
        this.mWebContents = null;
        this.mNativeContentViewCore = v2;
    }

    private void destroyPastePopup() {
        this.getSelectionPopupController().destroyPastePopup();
    }

    public boolean dispatchKeyEvent(KeyEvent arg3) {
        if(GamepadList.dispatchKeyEvent(arg3)) {
            return 1;
        }

        if(!ContentViewCoreImpl.shouldPropagateKeyEvent(arg3)) {
            return this.mContainerViewInternals.super_dispatchKeyEvent(arg3);
        }

        if(this.getImeAdapter().dispatchKeyEvent(arg3)) {
            return 1;
        }

        return this.mContainerViewInternals.super_dispatchKeyEvent(arg3);
    }

    public static ContentViewCoreImpl fromWebContents(WebContents arg2) {
        return arg2.getOrSetUserData(ContentViewCoreImpl.class, null);
    }

    public ViewGroup getContainerView() {
        return this.mContainerView;
    }

    public Context getContext() {
        return this.mContext;
    }

    private EventForwarder getEventForwarder() {
        return this.getWebContents().getEventForwarder();
    }

    private GestureListenerManagerImpl getGestureListenerManager() {
        return GestureListenerManagerImpl.fromWebContents(this.mWebContents);
    }

    private ImeAdapterImpl getImeAdapter() {
        return ImeAdapterImpl.fromWebContents(this.mWebContents);
    }

    private JoystickHandler getJoystick() {
        return JoystickHandler.fromWebContents(this.mWebContents);
    }

    private SelectPopup getSelectPopup() {
        return SelectPopup.fromWebContents(this.mWebContents);
    }

    private SelectionPopupControllerImpl getSelectionPopupController() {
        return SelectionPopupControllerImpl.fromWebContents(this.mWebContents);
    }

    private TapDisambiguator getTapDisambiguator() {
        return TapDisambiguator.fromWebContents(this.mWebContents);
    }

    private TextSuggestionHost getTextSuggestionHost() {
        return TextSuggestionHost.fromWebContents(this.mWebContents);
    }

    @VisibleForTesting public int getTopControlsShrinkBlinkHeightForTesting() {
        if(this.mNativeContentViewCore == 0) {
            return 0;
        }

        return this.nativeGetTopControlsShrinkBlinkHeightPixForTesting(this.mNativeContentViewCore);
    }

    public ViewAndroidDelegate getViewAndroidDelegate() {
        return this.mViewAndroidDelegate;
    }

    public WebContents getWebContents() {
        return this.mWebContents;
    }

    private WebContentsAccessibilityImpl getWebContentsAccessibility() {
        return WebContentsAccessibilityImpl.fromWebContents(this.mWebContents);
    }

    private WindowAndroid getWindowAndroid() {
        return this.mWindowAndroid;
    }

    private void hidePopupsAndClearSelection() {
        this.getSelectionPopupController().destroyActionModeAndUnselect();
        this.mWebContents.dismissTextHandles();
        PopupController.hideAll(this.mWebContents);
    }

    private void hidePopupsAndPreserveSelection() {
        this.getSelectionPopupController().hidePopupsAndPreserveSelection();
    }

    private void initialize(Context arg3, String arg4, ViewAndroidDelegate arg5, InternalAccessDelegate arg6, WindowAndroid arg7) {
        this.mContext = arg3;
        this.mViewAndroidDelegate = arg5;
        this.mWindowAndroid = arg7;
        float v3 = arg7.getDisplay().getDipScale();
        this.mNativeContentViewCore = this.nativeInit(this.mWebContents, this.mViewAndroidDelegate, arg7, v3);
        ViewGroup v5 = arg5.getContainerView();
        SelectionPopupControllerImpl v0 = SelectionPopupControllerImpl.create(this.mContext, arg7, this.mWebContents, ((View)v5));
        v0.setActionModeCallback(ActionModeCallbackHelper.EMPTY_CALLBACK);
        this.addWindowAndroidChangedObserver(((WindowAndroidChangedObserver)v0));
        this.setContainerView(v5);
        this.mRenderCoordinates = this.mWebContents.getRenderCoordinates();
        this.mRenderCoordinates.setDeviceScaleFactor(v3);
        WebContentsAccessibilityImpl v3_1 = WebContentsAccessibilityImpl.create(this.mContext, v5, this.mWebContents, arg4);
        this.setContainerViewInternals(arg6);
        ImeAdapterImpl v4 = ImeAdapterImpl.create(this.mWebContents, this.mContainerView, ImeAdapterImpl.createDefaultInputMethodManagerWrapper(this.mContext));
        v4.addEventObserver(((ImeEventObserver)v0));
        v4.addEventObserver(this.getJoystick());
        v4.addEventObserver(TapDisambiguator.create(this.mContext, this.mWebContents, v5));
        TextSuggestionHost v6 = TextSuggestionHost.create(this.mContext, this.mWebContents, arg7, ((View)v5));
        this.addWindowAndroidChangedObserver(((WindowAndroidChangedObserver)v6));
        SelectPopup.create(this.mContext, this.mWebContents, ((View)v5));
        this.mWindowEventObservers.addObserver(v0);
        this.mWindowEventObservers.addObserver(this.getGestureListenerManager());
        this.mWindowEventObservers.addObserver(v6);
        this.mWindowEventObservers.addObserver(v4);
        this.mWindowEventObservers.addObserver(v3_1);
        this.mInitialized = true;
    }

    public boolean initialized() {
        return this.mInitialized;
    }

    public boolean isAlive() {
        boolean v0 = this.mNativeContentViewCore != 0 ? true : false;
        return v0;
    }

    @VisibleForTesting public boolean isSelectPopupVisibleForTest() {
        return this.getSelectPopup().isVisibleForTesting();
    }

    private native int nativeGetTopControlsShrinkBlinkHeightPixForTesting(long arg1) {
    }

    private native long nativeInit(WebContents arg1, ViewAndroidDelegate arg2, WindowAndroid arg3, float arg4) {
    }

    private native void nativeOnJavaContentViewCoreDestroyed(long arg1) {
    }

    private native void nativeResetGestureDetection(long arg1) {
    }

    private native void nativeSendOrientationChangeEvent(long arg1, int arg2) {
    }

    private native void nativeSetDIPScale(long arg1, float arg2) {
    }

    private native void nativeSetDoubleTapSupportEnabled(long arg1, boolean arg2) {
    }

    private native void nativeSetFocus(long arg1, boolean arg2) {
    }

    private native void nativeSetHorizontalScrollBarEnable(long arg1, boolean arg2) {
    }

    private native void nativeSetMultiTouchZoomSupportEnabled(long arg1, boolean arg2) {
    }

    private native void nativeSetVerticalScrollbarEnable(long arg1, boolean arg2) {
    }

    private native void nativeUpdateWindowAndroid(long arg1, WindowAndroid arg2) {
    }

    public void onAttachedToWindow() {
        this.mAttachedToWindow = true;
        Iterator v0 = this.mWindowEventObservers.iterator();
        while(v0.hasNext()) {
            v0.next().onAttachedToWindow();
        }

        this.addDisplayAndroidObserverIfNeeded();
        GamepadList.onAttachedToWindow(this.mContext);
    }

    public void onConfigurationChanged(Configuration arg2) {
        try {
            TraceEvent.begin("ContentViewCore.onConfigurationChanged");
            this.getImeAdapter().onKeyboardConfigurationChanged(arg2);
            this.mContainerViewInternals.super_onConfigurationChanged(arg2);
            this.mContainerView.requestLayout();
        }
        catch(Throwable v2) {
            TraceEvent.end("ContentViewCore.onConfigurationChanged");
            throw v2;
        }

        TraceEvent.end("ContentViewCore.onConfigurationChanged");
    }

    public void onDIPScaleChanged(float arg6) {
        if(this.getWindowAndroid() != null) {
            if(this.mNativeContentViewCore == 0) {
            }
            else {
                this.mRenderCoordinates.setDeviceScaleFactor(arg6);
                this.nativeSetDIPScale(this.mNativeContentViewCore, arg6);
                return;
            }
        }
    }

    @SuppressLint(value={"MissingSuperCall"}) public void onDetachedFromWindow() {
        this.mAttachedToWindow = false;
        Iterator v0 = this.mWindowEventObservers.iterator();
        while(v0.hasNext()) {
            v0.next().onDetachedFromWindow();
        }

        this.removeDisplayAndroidObserver();
        GamepadList.onDetachedFromWindow();
    }

    private void onFocusChanged() {
        if(this.mHasViewFocus == null) {
            return;
        }

        boolean v1 = true;
        boolean v0 = !this.mHasViewFocus.booleanValue() || (this.mPaused) ? false : true;
        if(this.mHasInputFocus != null && this.mHasInputFocus.booleanValue() == v0) {
            return;
        }

        this.mHasInputFocus = Boolean.valueOf(v0);
        if(this.mWebContents == null) {
            return;
        }

        this.getImeAdapter().onViewFocusChanged(this.mHasInputFocus.booleanValue(), this.mHideKeyboardOnBlur);
        JoystickHandler v0_1 = this.getJoystick();
        if(!this.mHasInputFocus.booleanValue() || (this.getSelectionPopupController().isFocusedNodeEditable())) {
            v1 = false;
        }
        else {
        }

        v0_1.setScrollEnabled(v1);
        SelectionPopupControllerImpl v0_2 = this.getSelectionPopupController();
        if(this.mHasInputFocus.booleanValue()) {
            v0_2.restoreSelectionPopupsIfNecessary();
        }
        else {
            this.getImeAdapter().cancelRequestToScrollFocusedEditableNodeIntoView();
            if(v0_2.getPreserveSelectionOnNextLossOfFocus()) {
                v0_2.setPreserveSelectionOnNextLossOfFocus(false);
                this.hidePopupsAndPreserveSelection();
            }
            else {
                this.hidePopupsAndClearSelection();
                v0_2.clearSelection();
            }
        }

        if(this.mNativeContentViewCore != 0) {
            this.nativeSetFocus(this.mNativeContentViewCore, this.mHasInputFocus.booleanValue());
        }
    }

    public boolean onGenericMotionEvent(MotionEvent arg10) {
        if(GamepadList.onGenericMotionEvent(arg10)) {
            return 1;
        }

        if(this.getJoystick().onGenericMotionEvent(arg10)) {
            return 1;
        }

        if((arg10.getSource() & 2) != 0) {
            int v0 = arg10.getActionMasked();
            if(v0 != 8) {
                switch(v0) {
                    case 11: 
                    case 12: {
                        if(arg10.getToolType(0) == 3) {
                            return this.getEventForwarder().onMouseEvent(arg10);
                        label_23:
                            this.getEventForwarder().onMouseWheelEvent(arg10.getEventTime(), arg10.getX(), arg10.getY(), arg10.getAxisValue(10), arg10.getAxisValue(9));
                            return 1;
                        }

                        goto label_33;
                    }
                    default: {
                        goto label_33;
                    }
                }
            }
            else {
                goto label_23;
            }
        }

    label_33:
        return this.mContainerViewInternals.super_onGenericMotionEvent(arg10);
    }

    public boolean onKeyUp(int arg3, KeyEvent arg4) {
        TapDisambiguator v0 = this.getTapDisambiguator();
        if((v0.isShowing()) && arg3 == 4) {
            v0.backButtonPressed();
            return 1;
        }

        return this.mContainerViewInternals.super_onKeyUp(arg3, arg4);
    }

    @CalledByNative private void onNativeContentViewCoreDestroyed(long arg1) {
        this.mNativeContentViewCore = 0;
    }

    public void onPause() {
        if(this.mPaused) {
            return;
        }

        this.mPaused = true;
        this.onFocusChanged();
    }

    public void onResume() {
        if(!this.mPaused) {
            return;
        }

        this.mPaused = false;
        this.onFocusChanged();
    }

    public void onRotationChanged(int arg4) {
        if(this.mWebContents != null) {
            SelectionPopupControllerImpl v0 = this.getSelectionPopupController();
            if(Build$VERSION.SDK_INT >= 23 && v0 != null && (v0.isActionModeValid())) {
                this.hidePopupsAndPreserveSelection();
                v0.showActionModeOrClearOnFailure();
            }

            TextSuggestionHost v0_1 = this.getTextSuggestionHost();
            if(v0_1 == null) {
                goto label_14;
            }

            v0_1.hidePopups();
        }

    label_14:
        switch(arg4) {
            case 0: {
                goto label_25;
            }
            case 1: {
                goto label_23;
            }
            case 2: {
                goto label_21;
            }
            case 3: {
                goto label_19;
            }
        }

        throw new IllegalStateException("Display.getRotation() shouldn\'t return that value");
    label_19:
        arg4 = -90;
        goto label_26;
    label_21:
        arg4 = 180;
        goto label_26;
    label_23:
        arg4 = 90;
        goto label_26;
    label_25:
        arg4 = 0;
    label_26:
        this.sendOrientationChangeEvent(arg4);
    }

    public void onViewFocusChanged(boolean arg2) {
        if(this.mHasViewFocus != null && this.mHasViewFocus.booleanValue() == arg2) {
            return;
        }

        this.mHasViewFocus = Boolean.valueOf(arg2);
        this.onFocusChanged();
    }

    public void onWindowFocusChanged(boolean arg3) {
        if(!arg3) {
            this.resetGestureDetection();
        }

        Iterator v0 = this.mWindowEventObservers.iterator();
        while(v0.hasNext()) {
            v0.next().onWindowFocusChanged(arg3);
        }
    }

    private void removeDisplayAndroidObserver() {
        WindowAndroid v0 = this.getWindowAndroid();
        if(v0 != null) {
            v0.getDisplay().removeObserver(((DisplayAndroidObserver)this));
        }
    }

    public void removeWindowAndroidChangedObserver(WindowAndroidChangedObserver arg2) {
        this.mWindowAndroidChangedObservers.removeObserver(arg2);
    }

    private void resetGestureDetection() {
        if(this.mNativeContentViewCore == 0) {
            return;
        }

        this.nativeResetGestureDetection(this.mNativeContentViewCore);
    }

    public void scrollBy(float arg4, float arg5) {
        if(arg4 == 0f && arg5 == 0f) {
            return;
        }

        long v0 = SystemClock.uptimeMillis();
        if(this.getGestureListenerManager().hasPotentiallyActiveFling()) {
            this.getEventForwarder().cancelFling(v0);
        }

        this.getEventForwarder().scroll(v0, arg4, arg5);
    }

    public void scrollTo(float arg3, float arg4) {
        this.scrollBy(arg3 - this.mRenderCoordinates.getScrollXPix(), arg4 - this.mRenderCoordinates.getScrollYPix());
    }

    @VisibleForTesting private void sendOrientationChangeEvent(int arg6) {
        if(this.mNativeContentViewCore == 0) {
            return;
        }

        this.nativeSendOrientationChangeEvent(this.mNativeContentViewCore, arg6);
    }

    public void setContainerView(ViewGroup arg3) {
        try {
            TraceEvent.begin("ContentViewCore.setContainerView");
            if(this.mContainerView != null) {
                this.getSelectPopup().hide();
                this.getImeAdapter().setContainerView(((View)arg3));
                this.getTextSuggestionHost().setContainerView(((View)arg3));
                this.getSelectPopup().setContainerView(((View)arg3));
            }

            this.mContainerView = arg3;
            this.mContainerView.setClickable(true);
            this.getSelectionPopupController().setContainerView(((View)arg3));
            this.getGestureListenerManager().setContainerView(((View)arg3));
        }
        catch(Throwable v3) {
            TraceEvent.end("ContentViewCore.setContainerView");
            throw v3;
        }

        TraceEvent.end("ContentViewCore.setContainerView");
    }

    public void setContainerViewInternals(InternalAccessDelegate arg2) {
        this.mContainerViewInternals = arg2;
        this.getGestureListenerManager().setScrollDelegate(arg2);
    }

    public void setHideKeyboardOnBlur(boolean arg1) {
        this.mHideKeyboardOnBlur = arg1;
    }

    private static boolean shouldPropagateKeyEvent(KeyEvent arg1) {
        int v1 = arg1.getKeyCode();
        if(v1 != 82 && v1 != 3 && v1 != 4 && v1 != 5 && v1 != 6 && v1 != 26 && v1 != 0x4F && v1 != 27 && v1 != 80 && v1 != 25 && v1 != 0xA4) {
            if(v1 == 24) {
            }
            else {
                return 1;
            }
        }

        return 0;
    }

    public void updateDoubleTapSupport(boolean arg6) {
        if(this.mNativeContentViewCore == 0) {
            return;
        }

        this.nativeSetDoubleTapSupportEnabled(this.mNativeContentViewCore, arg6);
    }

    public void updateMultiTouchZoomSupport(boolean arg6) {
        if(this.mNativeContentViewCore == 0) {
            return;
        }

        this.nativeSetMultiTouchZoomSupportEnabled(this.mNativeContentViewCore, arg6);
    }

    public void updateWindowAndroid(WindowAndroid arg3) {
        this.removeDisplayAndroidObserver();
        this.mWindowAndroid = arg3;
        this.nativeUpdateWindowAndroid(this.mNativeContentViewCore, arg3);
        this.getSelectPopup().close();
        this.destroyPastePopup();
        this.addDisplayAndroidObserverIfNeeded();
        Iterator v0 = this.mWindowAndroidChangedObservers.iterator();
        while(v0.hasNext()) {
            v0.next().onWindowAndroidChanged(arg3);
        }
    }
}

