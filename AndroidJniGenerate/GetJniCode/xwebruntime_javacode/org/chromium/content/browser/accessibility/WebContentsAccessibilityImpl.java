package org.chromium.content.browser.accessibility;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.Settings$Secure;
import android.provider.Settings$System;
import android.text.SpannableString;
import android.text.style.URLSpan;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewStructure;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager$AccessibilityStateChangeListener;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.chromium.base.VisibleForTesting;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.content.browser.RenderCoordinates;
import org.chromium.content.browser.WindowEventObserver$$CC;
import org.chromium.content.browser.WindowEventObserver;
import org.chromium.content.browser.accessibility.captioning.CaptioningController;
import org.chromium.content.browser.webcontents.WebContentsImpl;
import org.chromium.content_public.browser.AccessibilitySnapshotCallback;
import org.chromium.content_public.browser.AccessibilitySnapshotNode;
import org.chromium.content_public.browser.WebContents$UserDataFactory;
import org.chromium.content_public.browser.WebContents;
import org.chromium.content_public.browser.WebContentsAccessibility;

@JNINamespace(value="content") public class WebContentsAccessibilityImpl extends AccessibilityNodeProvider implements AccessibilityManager$AccessibilityStateChangeListener, WindowEventObserver, WebContentsAccessibility {
    class Factory implements UserDataFactory {
        Factory(org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl$1 arg1) {
            this();
        }

        private Factory() {
            super();
        }

        public Object create(WebContents arg1) {
            return this.create(arg1);
        }

        public WebContentsAccessibilityImpl create(WebContents arg3) {
            if(Build$VERSION.SDK_INT >= 26) {
                return new OWebContentsAccessibility(arg3);
            }

            if(Build$VERSION.SDK_INT >= 21) {
                return new LollipopWebContentsAccessibility(arg3);
            }

            if(Build$VERSION.SDK_INT >= 19) {
                return new KitKatWebContentsAccessibility(arg3);
            }

            return new WebContentsAccessibilityImpl(arg3);
        }
    }

    final class UserDataFactoryLazyHolder {
        private static final UserDataFactory INSTANCE;

        static {
            UserDataFactoryLazyHolder.INSTANCE = new Factory(null);
        }

        private UserDataFactoryLazyHolder() {
            super();
        }

        static UserDataFactory access$100() {
            return UserDataFactoryLazyHolder.INSTANCE;
        }
    }

    private static final String ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE = "ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE";
    private static final int ACTION_COLLAPSE = 0x80000;
    protected static final int ACTION_CONTEXT_CLICK = 0x102003C;
    private static final int ACTION_EXPAND = 0x40000;
    protected static final int ACTION_SCROLL_DOWN = 0x102003A;
    protected static final int ACTION_SCROLL_LEFT = 0x1020039;
    protected static final int ACTION_SCROLL_RIGHT = 0x102003B;
    protected static final int ACTION_SCROLL_UP = 0x1020038;
    private static final int ACTION_SET_TEXT = 0x200000;
    protected static final int ACTION_SHOW_ON_SCREEN = 0x1020036;
    private static final int NO_GRANULARITY_SELECTED = 0;
    private static final int WINDOW_CONTENT_CHANGED_DELAY_MS = 500;
    private boolean mAccessibilityEnabledForTesting;
    protected int mAccessibilityFocusId;
    private Rect mAccessibilityFocusRect;
    protected AccessibilityManager mAccessibilityManager;
    private View mAutofillPopupView;
    private CaptioningController mCaptioningController;
    protected Context mContext;
    protected int mCurrentRootId;
    private boolean mInitialized;
    private boolean mIsHovering;
    private boolean mIsObscuredByAnotherView;
    private int mLastHoverId;
    private boolean mNativeAccessibilityAllowed;
    protected long mNativeObj;
    private boolean mNotifyFrameInfoInitializedCalled;
    private boolean mPendingScrollToMakeNodeVisible;
    private String mProductVersion;
    private int mSelectionEndIndex;
    private int mSelectionGranularity;
    protected int mSelectionNodeId;
    private int mSelectionStartIndex;
    private Runnable mSendWindowContentChangedRunnable;
    private boolean mShouldFocusOnPageLoad;
    private int[] mTempLocation;
    private boolean mTouchExplorationEnabled;
    private boolean mUserHasTouchExplored;
    protected ViewGroup mView;
    private final WebContentsImpl mWebContents;

    static {
    }

    protected WebContentsAccessibilityImpl(WebContents arg2) {
        super();
        this.mLastHoverId = -1;
        this.mTempLocation = new int[2];
        this.mWebContents = ((WebContentsImpl)arg2);
    }

    static String access$200(WebContentsAccessibilityImpl arg0) {
        return arg0.mProductVersion;
    }

    static void access$300(WebContentsAccessibilityImpl arg0, ViewStructure arg1, AccessibilitySnapshotNode arg2, boolean arg3) {
        arg0.createVirtualStructure(arg1, arg2, arg3);
    }

    static void access$400(WebContentsAccessibilityImpl arg0) {
        arg0.sendWindowContentChangedOnView();
    }

    @CalledByNative private void addAccessibilityNodeInfoActions(AccessibilityNodeInfo arg5, int arg6, boolean arg7, boolean arg8, boolean arg9, boolean arg10, boolean arg11, boolean arg12, boolean arg13, boolean arg14, boolean arg15, boolean arg16, boolean arg17, boolean arg18, boolean arg19, boolean arg20) {
        WebContentsAccessibilityImpl v0 = this;
        AccessibilityNodeInfo v1 = arg5;
        v0.addAction(v1, 0x400);
        v0.addAction(v1, 0x800);
        v0.addAction(v1, 0x100);
        v0.addAction(v1, 0x200);
        v0.addAction(v1, 0x1020036);
        v0.addAction(v1, 0x102003C);
        if((arg14) && (arg15)) {
            v0.addAction(v1, 0x200000);
            v0.addAction(v1, 0x8000);
            if(arg20) {
                v0.addAction(v1, 0x20000);
                v0.addAction(v1, 0x10000);
                v0.addAction(v1, 0x4000);
            }
        }

        if(arg7) {
            v0.addAction(v1, 0x1000);
        }

        if(arg8) {
            v0.addAction(v1, 0x2000);
        }

        if(arg9) {
            v0.addAction(v1, 0x1020038);
        }

        if(arg10) {
            v0.addAction(v1, 0x102003A);
        }

        if(arg11) {
            v0.addAction(v1, 0x1020039);
        }

        if(arg12) {
            v0.addAction(v1, 0x102003B);
        }

        if(arg16) {
            if(arg17) {
                v0.addAction(v1, 2);
            }
            else {
                v0.addAction(v1, 1);
            }
        }

        if(v0.mAccessibilityFocusId == arg6) {
            v0.addAction(v1, 0x80);
        }
        else {
            v0.addAction(v1, 0x40);
        }

        if(arg13) {
            v0.addAction(v1, 16);
        }

        if(arg18) {
            v0.addAction(v1, 0x40000);
        }

        if(arg19) {
            v0.addAction(v1, 0x80000);
        }
    }

    @CalledByNative private void addAccessibilityNodeInfoChild(AccessibilityNodeInfo arg2, int arg3) {
        arg2.addChild(this.mView, arg3);
    }

    protected void addAction(AccessibilityNodeInfo arg2, int arg3) {
        if(arg3 > 0x200000) {
            return;
        }

        arg2.addAction(arg3);
    }

    @CalledByNative private void announceLiveRegionText(String arg2) {
        this.mView.announceForAccessibility(((CharSequence)arg2));
    }

    private AccessibilityEvent buildAccessibilityEvent(int arg9, int arg10) {
        AccessibilityEvent v1 = null;
        if(this.isAccessibilityEnabled()) {
            if(!this.isFrameInfoInitialized()) {
            }
            else {
                this.mView.postInvalidate();
                AccessibilityEvent v0 = AccessibilityEvent.obtain(arg10);
                v0.setPackageName(this.mContext.getPackageName());
                v0.setSource(this.mView, arg9);
                if(!this.nativePopulateAccessibilityEvent(this.mNativeObj, v0, arg9, arg10)) {
                    v0.recycle();
                    return v1;
                }
                else {
                    return v0;
                }
            }
        }

        return v1;
    }

    protected CharSequence computeText(String arg2, boolean arg3, String arg4) {
        if(arg3) {
            SpannableString v3 = new SpannableString(((CharSequence)arg2));
            v3.setSpan(new URLSpan(""), 0, v3.length(), 0);
            return ((CharSequence)v3);
        }

        return arg2;
    }

    protected void convertWebRectToAndroidCoordinates(Rect arg6) {
        RenderCoordinates v0 = this.mWebContents.getRenderCoordinates();
        arg6.offset(-(((int)v0.getScrollX())), -(((int)v0.getScrollY())));
        arg6.left = ((int)v0.fromLocalCssToPix(((float)arg6.left)));
        arg6.top = ((int)v0.fromLocalCssToPix(((float)arg6.top)));
        arg6.bottom = ((int)v0.fromLocalCssToPix(((float)arg6.bottom)));
        arg6.right = ((int)v0.fromLocalCssToPix(((float)arg6.right)));
        arg6.offset(0, ((int)v0.getContentOffsetYPix()));
        int[] v1 = new int[2];
        this.mView.getLocationOnScreen(v1);
        arg6.offset(v1[0], v1[1]);
        int v1_1 = v1[1] + (((int)v0.getContentOffsetYPix()));
        int v0_1 = this.mView.getHeight() + v1_1;
        if(arg6.top < v1_1) {
            arg6.top = v1_1;
        }

        if(arg6.bottom > v0_1) {
            arg6.bottom = v0_1;
        }
    }

    public static WebContentsAccessibilityImpl create(Context arg2, ViewGroup arg3, WebContents arg4, String arg5) {
        Object v4 = arg4.getOrSetUserData(WebContentsAccessibilityImpl.class, UserDataFactoryLazyHolder.access$100());
        ((WebContentsAccessibilityImpl)v4).init(arg2, arg3, arg5);
        return ((WebContentsAccessibilityImpl)v4);
    }

    public AccessibilityNodeInfo createAccessibilityNodeInfo(int arg6) {
        AccessibilityNodeInfo v1 = null;
        if(!this.isAccessibilityEnabled()) {
            return v1;
        }

        int v0 = this.nativeGetRootId(this.mNativeObj);
        if(arg6 == -1) {
            return this.createNodeForHost(v0);
        }

        if(!this.isFrameInfoInitialized()) {
            return v1;
        }

        AccessibilityNodeInfo v2 = AccessibilityNodeInfo.obtain(this.mView);
        v2.setPackageName(this.mContext.getPackageName());
        v2.setSource(this.mView, arg6);
        if(arg6 == v0) {
            v2.setParent(this.mView);
        }

        if(this.nativePopulateAccessibilityNodeInfo(this.mNativeObj, v2, arg6)) {
            return v2;
        }

        v2.recycle();
        return v1;
    }

    private AccessibilityNodeInfo createNodeForHost(int arg5) {
        AccessibilityNodeInfo v0 = AccessibilityNodeInfo.obtain(this.mView);
        AccessibilityNodeInfo v1 = AccessibilityNodeInfo.obtain(this.mView);
        this.mView.onInitializeAccessibilityNodeInfo(v1);
        Rect v2 = new Rect();
        v1.getBoundsInParent(v2);
        v0.setBoundsInParent(v2);
        v1.getBoundsInScreen(v2);
        v0.setBoundsInScreen(v2);
        ViewParent v2_1 = this.mView.getParentForAccessibility();
        if((v2_1 instanceof View)) {
            v0.setParent(((View)v2_1));
        }

        v0.setVisibleToUser(v1.isVisibleToUser());
        v0.setEnabled(v1.isEnabled());
        v0.setPackageName(v1.getPackageName());
        v0.setClassName(v1.getClassName());
        if(this.isFrameInfoInitialized()) {
            v0.addChild(this.mView, arg5);
        }

        return v0;
    }

    @TargetApi(value=23) private void createVirtualStructure(ViewStructure arg12, AccessibilitySnapshotNode arg13, boolean arg14) {
        arg12.setClassName(arg13.className);
        if(arg13.hasSelection) {
            arg12.setText(arg13.text, arg13.startSelection, arg13.endSelection);
        }
        else {
            arg12.setText(arg13.text);
        }

        RenderCoordinates v0 = this.mWebContents.getRenderCoordinates();
        int v1 = ((int)v0.fromLocalCssToPix(((float)arg13.x)));
        int v2 = ((int)v0.fromLocalCssToPix(((float)arg13.y)));
        int v9 = ((int)v0.fromLocalCssToPix(((float)arg13.width)));
        int v10 = ((int)v0.fromLocalCssToPix(((float)arg13.height)));
        Rect v3 = new Rect(v1, v2, v1 + v9, v2 + v10);
        v2 = 0;
        if(arg13.isRootNode) {
            v3.offset(0, ((int)v0.getContentOffsetYPix()));
            if(!arg14) {
                v3.offset(-(((int)v0.getScrollXPix())), -(((int)v0.getScrollYPix())));
            }
        }

        arg12.setDimens(v3.left, v3.top, 0, 0, v9, v10);
        arg12.setChildCount(arg13.children.size());
        if(arg13.hasStyle) {
            float v14 = v0.fromLocalCssToPix(arg13.textSize);
            boolean v0_1 = arg13.bold;
            v1 = arg13.italic ? 2 : 0;
            int v0_2 = (((int)v0_1)) | v1;
            v1 = arg13.underline ? 4 : 0;
            v0_2 |= v1;
            v1 = arg13.lineThrough ? 8 : 0;
            arg12.setTextStyle(v14, arg13.color, arg13.bgcolor, v0_2 | v1);
        }

        while(v2 < arg13.children.size()) {
            this.createVirtualStructure(arg12.asyncNewChild(v2), arg13.children.get(v2), true);
            ++v2;
        }

        arg12.asyncCommit();
    }

    public List findAccessibilityNodeInfosByText(String arg1, int arg2) {
        return new ArrayList();
    }

    @CalledByNative private void finishGranularityMove(String arg9, boolean arg10, int arg11, int arg12, boolean arg13) {
        AccessibilityEvent v0 = this.buildAccessibilityEvent(this.mSelectionNodeId, 0x2000);
        if(v0 == null) {
            return;
        }

        AccessibilityEvent v1 = this.buildAccessibilityEvent(this.mSelectionNodeId, 0x20000);
        if(v1 == null) {
            v0.recycle();
            return;
        }

        this.mSelectionEndIndex = arg13 ? arg12 : arg11;
        if(!arg10) {
            this.mSelectionStartIndex = this.mSelectionEndIndex;
        }

        if((this.nativeIsEditableText(this.mNativeObj, this.mSelectionNodeId)) && (this.nativeIsFocused(this.mNativeObj, this.mSelectionNodeId))) {
            this.nativeSetSelection(this.mNativeObj, this.mSelectionNodeId, this.mSelectionStartIndex, this.mSelectionEndIndex);
        }

        v0.setFromIndex(this.mSelectionStartIndex);
        v0.setToIndex(this.mSelectionStartIndex);
        v0.setItemCount(arg9.length());
        if(!arg13 || !this.nativeIsEditableText(this.mNativeObj, this.mSelectionNodeId)) {
            v1.setFromIndex(arg11);
            v1.setToIndex(arg12);
        }
        else {
            v1.setFromIndex(arg11 - 1);
            v1.setToIndex(arg12 - 1);
        }

        v1.setItemCount(arg9.length());
        v1.setMovementGranularity(this.mSelectionGranularity);
        v1.setContentDescription(((CharSequence)arg9));
        if(arg13) {
            v1.setAction(0x100);
        }
        else {
            v1.setAction(0x200);
        }

        this.mView.requestSendAccessibilityEvent(this.mView, v0);
        this.mView.requestSendAccessibilityEvent(this.mView, v1);
    }

    public static WebContentsAccessibilityImpl fromWebContents(WebContents arg2) {
        return arg2.getOrSetUserData(WebContentsAccessibilityImpl.class, null);
    }

    public AccessibilityNodeProvider getAccessibilityNodeProvider() {
        AccessibilityNodeProvider v1 = null;
        if(this.mIsObscuredByAnotherView) {
            return v1;
        }

        if(!this.isNativeInitialized()) {
            if(!this.mNativeAccessibilityAllowed) {
                return v1;
            }
            else {
                this.mNativeObj = this.nativeInit(this.mWebContents);
                this.onNativeInit();
            }
        }

        if(!this.isEnabled()) {
            this.nativeEnable(this.mNativeObj);
            return v1;
        }

        return this;
    }

    @CalledByNative protected int getAccessibilityServiceCapabilitiesMask() {
        return 0;
    }

    @CalledByNative private int getAccessibilityServiceEventTypeMask() {
        Iterator v0 = this.mAccessibilityManager.getEnabledAccessibilityServiceList(-1).iterator();
        int v1;
        for(v1 = 0; v0.hasNext(); v1 |= v0.next().eventTypes) {
        }

        return v1;
    }

    @CalledByNative private int getAccessibilityServiceFeedbackTypeMask() {
        Iterator v0 = this.mAccessibilityManager.getEnabledAccessibilityServiceList(-1).iterator();
        int v1;
        for(v1 = 0; v0.hasNext(); v1 |= v0.next().feedbackType) {
        }

        return v1;
    }

    @CalledByNative private int getAccessibilityServiceFlagsMask() {
        Iterator v0 = this.mAccessibilityManager.getEnabledAccessibilityServiceList(-1).iterator();
        int v1;
        for(v1 = 0; v0.hasNext(); v1 |= v0.next().flags) {
        }

        return v1;
    }

    private Bundle getOrCreateBundleForAccessibilityEvent(AccessibilityEvent arg2) {
        Parcelable v0 = arg2.getParcelableData();
        if(v0 == null) {
            Bundle v0_1 = new Bundle();
            arg2.setParcelableData(((Parcelable)v0_1));
        }

        return ((Bundle)v0);
    }

    @CalledByNative private void handleCheckStateChanged(int arg2) {
        this.sendAccessibilityEvent(arg2, 1);
    }

    @CalledByNative private void handleClicked(int arg2) {
        this.sendAccessibilityEvent(arg2, 1);
    }

    @CalledByNative private void handleContentChanged(int arg3) {
        int v0 = this.nativeGetRootId(this.mNativeObj);
        if(v0 != this.mCurrentRootId) {
            this.mCurrentRootId = v0;
            this.sendWindowContentChangedOnView();
        }
        else {
            this.sendWindowContentChangedOnVirtualView(arg3);
        }
    }

    @CalledByNative private void handleEditableTextChanged(int arg2) {
        this.sendAccessibilityEvent(arg2, 16);
    }

    @CalledByNative private void handleFocusChanged(int arg3) {
        if(!this.mShouldFocusOnPageLoad && this.mAccessibilityFocusId == -1) {
            return;
        }

        this.sendAccessibilityEvent(arg3, 8);
        this.moveAccessibilityFocusToId(arg3);
    }

    @CalledByNative private void handleHover(int arg3) {
        if(this.mLastHoverId == arg3) {
            return;
        }

        if(!this.mIsHovering) {
            return;
        }

        this.sendAccessibilityEvent(arg3, 0x80);
        if(this.mLastHoverId != -1) {
            this.sendAccessibilityEvent(this.mLastHoverId, 0x100);
        }

        this.mLastHoverId = arg3;
    }

    @CalledByNative private void handleNavigate() {
        this.mAccessibilityFocusId = -1;
        this.mAccessibilityFocusRect = null;
        this.mUserHasTouchExplored = false;
        this.sendWindowContentChangedOnView();
    }

    @CalledByNative private void handlePageLoaded(int arg2) {
        if(!this.mShouldFocusOnPageLoad) {
            return;
        }

        if(this.mUserHasTouchExplored) {
            return;
        }

        this.moveAccessibilityFocusToIdAndRefocusIfNeeded(arg2);
    }

    @CalledByNative private void handleScrollPositionChanged(int arg2) {
        this.sendAccessibilityEvent(arg2, 0x1000);
    }

    @CalledByNative private void handleScrolledToAnchor(int arg1) {
        this.moveAccessibilityFocusToId(arg1);
    }

    @CalledByNative private void handleSliderChanged(int arg2) {
        this.sendAccessibilityEvent(arg2, 0x1000);
    }

    @CalledByNative private void handleTextSelectionChanged(int arg2) {
        this.sendAccessibilityEvent(arg2, 0x2000);
    }

    private void init(Context arg1, ViewGroup arg2, String arg3) {
        this.mContext = arg1;
        this.mView = arg2;
        this.mProductVersion = arg3;
        this.mAccessibilityManager = this.mContext.getSystemService("accessibility");
        this.mCaptioningController = new CaptioningController(this.mWebContents, this.mContext);
        this.mInitialized = true;
    }

    private boolean initialized() {
        return this.mInitialized;
    }

    public boolean isAccessibilityEnabled() {
        boolean v0;
        if(this.isNativeInitialized()) {
            if(!this.mAccessibilityEnabledForTesting && !this.mAccessibilityManager.isEnabled()) {
                goto label_9;
            }

            v0 = true;
        }
        else {
        label_9:
            v0 = false;
        }

        return v0;
    }

    private boolean isEnabled() {
        boolean v0 = this.isNativeInitialized() ? this.nativeIsEnabled(this.mNativeObj) : false;
        return v0;
    }

    private boolean isFrameInfoInitialized() {
        boolean v1 = true;
        if(this.mWebContents == null) {
            return 1;
        }

        RenderCoordinates v0 = this.mWebContents.getRenderCoordinates();
        double v4 = 0;
        if((((double)v0.getContentWidthCss())) == v4) {
            if((((double)v0.getContentHeightCss())) != v4) {
            }
            else {
                v1 = false;
            }
        }

        return v1;
    }

    protected boolean isNativeInitialized() {
        boolean v0 = this.mNativeObj != 0 ? true : false;
        return v0;
    }

    public boolean isTouchExplorationEnabled() {
        return this.mTouchExplorationEnabled;
    }

    private static boolean isValidMovementGranularity(int arg1) {
        if(arg1 != 4) {
            switch(arg1) {
                case 1: 
                case 2: {
                    return 1;
                }
                default: {
                    return 0;
                }
            }
        }

        return 1;
    }

    private boolean jumpToElementType(int arg7, String arg8, boolean arg9) {
        arg7 = this.nativeFindElementType(this.mNativeObj, arg7, arg8, arg9);
        if(arg7 == 0) {
            return 0;
        }

        this.moveAccessibilityFocusToId(arg7);
        this.nativeScrollToMakeNodeVisible(this.mNativeObj, this.mAccessibilityFocusId);
        return 1;
    }

    private boolean moveAccessibilityFocusToId(int arg4) {
        if(arg4 == this.mAccessibilityFocusId) {
            return 0;
        }

        this.mAccessibilityFocusId = arg4;
        this.mAccessibilityFocusRect = null;
        this.mSelectionNodeId = this.mAccessibilityFocusId;
        this.mSelectionGranularity = 0;
        int v0 = -1;
        this.mSelectionStartIndex = v0;
        this.mSelectionEndIndex = this.nativeGetTextLength(this.mNativeObj, arg4);
        if(this.mAccessibilityFocusId == this.mCurrentRootId) {
            this.nativeSetAccessibilityFocus(this.mNativeObj, v0);
        }
        else if(this.nativeIsAutofillPopupNode(this.mNativeObj, this.mAccessibilityFocusId)) {
            this.mAutofillPopupView.requestFocus();
        }
        else {
            this.nativeSetAccessibilityFocus(this.mNativeObj, this.mAccessibilityFocusId);
        }

        this.sendAccessibilityEvent(this.mAccessibilityFocusId, 0x8000);
        return 1;
    }

    private void moveAccessibilityFocusToIdAndRefocusIfNeeded(int arg2) {
        if(arg2 == this.mAccessibilityFocusId) {
            this.sendAccessibilityEvent(arg2, 0x10000);
            this.mAccessibilityFocusId = -1;
        }

        this.moveAccessibilityFocusToId(arg2);
    }

    private native boolean nativeAdjustSlider(long arg1, int arg2, boolean arg3) {
    }

    protected native boolean nativeAreInlineTextBoxesLoaded(long arg1, int arg2) {
    }

    private native void nativeBlur(long arg1) {
    }

    private native void nativeClick(long arg1, int arg2) {
    }

    private native void nativeEnable(long arg1) {
    }

    private native int nativeFindElementType(long arg1, int arg2, String arg3, boolean arg4) {
    }

    private native void nativeFocus(long arg1, int arg2) {
    }

    protected native int[] nativeGetCharacterBoundingBoxes(long arg1, int arg2, int arg3, int arg4) {
    }

    private native int nativeGetEditableTextSelectionEnd(long arg1, int arg2) {
    }

    private native int nativeGetEditableTextSelectionStart(long arg1, int arg2) {
    }

    private native int nativeGetIdForElementAfterElementHostingAutofillPopup(long arg1) {
    }

    private native int nativeGetRootId(long arg1) {
    }

    protected native String nativeGetSupportedHtmlElementTypes(long arg1) {
    }

    private native int nativeGetTextLength(long arg1, int arg2) {
    }

    private native long nativeInit(WebContents arg1) {
    }

    private native boolean nativeIsAutofillPopupNode(long arg1, int arg2) {
    }

    private native boolean nativeIsEditableText(long arg1, int arg2) {
    }

    private native boolean nativeIsEnabled(long arg1) {
    }

    private native boolean nativeIsFocused(long arg1, int arg2) {
    }

    private native boolean nativeIsNodeValid(long arg1, int arg2) {
    }

    private native boolean nativeIsSlider(long arg1, int arg2) {
    }

    protected native void nativeLoadInlineTextBoxes(long arg1, int arg2) {
    }

    private native boolean nativeNextAtGranularity(long arg1, int arg2, boolean arg3, int arg4, int arg5) {
    }

    private native void nativeOnAutofillPopupDismissed(long arg1) {
    }

    private native void nativeOnAutofillPopupDisplayed(long arg1) {
    }

    private native boolean nativePopulateAccessibilityEvent(long arg1, AccessibilityEvent arg2, int arg3, int arg4) {
    }

    private native boolean nativePopulateAccessibilityNodeInfo(long arg1, AccessibilityNodeInfo arg2, int arg3) {
    }

    private native boolean nativePreviousAtGranularity(long arg1, int arg2, boolean arg3, int arg4, int arg5) {
    }

    private native boolean nativeScroll(long arg1, int arg2, int arg3) {
    }

    private native void nativeScrollToMakeNodeVisible(long arg1, int arg2) {
    }

    private native void nativeSetAccessibilityFocus(long arg1, int arg2) {
    }

    private native void nativeSetSelection(long arg1, int arg2, int arg3, int arg4) {
    }

    private native void nativeSetTextFieldValue(long arg1, int arg2, String arg3) {
    }

    private native void nativeShowContextMenu(long arg1, int arg2) {
    }

    private boolean nextAtGranularity(int arg8, boolean arg9, int arg10) {
        if(arg10 != this.mSelectionNodeId) {
            return 0;
        }

        this.setGranularityAndUpdateSelection(arg8);
        return this.nativeNextAtGranularity(this.mNativeObj, this.mSelectionGranularity, arg9, arg10, this.mSelectionStartIndex);
    }

    @CalledByNative private void notifyFrameInfoInitialized() {
        if(this.mNotifyFrameInfoInitializedCalled) {
            return;
        }

        this.mNotifyFrameInfoInitializedCalled = true;
        this.sendWindowContentChangedOnView();
        if(!this.mShouldFocusOnPageLoad) {
            return;
        }

        if(this.mAccessibilityFocusId != -1) {
            this.moveAccessibilityFocusToIdAndRefocusIfNeeded(this.mAccessibilityFocusId);
        }
    }

    public void onAccessibilityStateChanged(boolean arg1) {
        this.setState(arg1);
    }

    public void onAttachedToWindow() {
        this.mAccessibilityManager.addAccessibilityStateChangeListener(((AccessibilityManager$AccessibilityStateChangeListener)this));
        this.refreshState();
        this.mCaptioningController.startListening();
    }

    public void onAutofillPopupAccessibilityFocusCleared() {
        if(this.isAccessibilityEnabled()) {
            int v0 = this.nativeGetIdForElementAfterElementHostingAutofillPopup(this.mNativeObj);
            if(v0 == 0) {
                return;
            }
            else {
                this.moveAccessibilityFocusToId(v0);
                this.nativeScrollToMakeNodeVisible(this.mNativeObj, this.mAccessibilityFocusId);
            }
        }
    }

    public void onAutofillPopupDismissed() {
        if(this.isAccessibilityEnabled()) {
            this.nativeOnAutofillPopupDismissed(this.mNativeObj);
            this.mAutofillPopupView = null;
        }
    }

    public void onAutofillPopupDisplayed(View arg3) {
        if(this.isAccessibilityEnabled()) {
            this.mAutofillPopupView = arg3;
            this.nativeOnAutofillPopupDisplayed(this.mNativeObj);
        }
    }

    public void onDetachedFromWindow() {
        this.mAccessibilityManager.removeAccessibilityStateChangeListener(((AccessibilityManager$AccessibilityStateChangeListener)this));
        this.mCaptioningController.stopListening();
    }

    @CalledByNative private boolean onHoverEvent(int arg6) {
        if(!this.isAccessibilityEnabled()) {
            return 0;
        }

        if(arg6 == 10) {
            this.mIsHovering = false;
            int v0 = -1;
            if(this.mLastHoverId != v0) {
                this.sendAccessibilityEvent(this.mLastHoverId, 0x100);
                this.mLastHoverId = v0;
            }

            if(this.mPendingScrollToMakeNodeVisible) {
                this.nativeScrollToMakeNodeVisible(this.mNativeObj, this.mAccessibilityFocusId);
            }

            this.mPendingScrollToMakeNodeVisible = false;
            return 1;
        }

        this.mIsHovering = true;
        this.mUserHasTouchExplored = true;
        return 1;
    }

    protected void onNativeInit() {
        this.mAccessibilityFocusId = -1;
        this.mSelectionNodeId = -1;
        this.mIsHovering = false;
        this.mCurrentRootId = -1;
    }

    @CalledByNative protected void onNativeObjectDestroyed() {
        this.mNativeObj = 0;
    }

    @TargetApi(value=23) public void onProvideVirtualStructure(ViewStructure arg3, boolean arg4) {
        if(this.mWebContents.isIncognito()) {
            arg3.setChildCount(0);
            return;
        }

        arg3.setChildCount(1);
        this.mWebContents.requestAccessibilitySnapshot(new AccessibilitySnapshotCallback(arg3.asyncNewChild(0), arg4) {
            public void onAccessibilitySnapshot(AccessibilitySnapshotNode arg4) {
                this.val$viewRoot.setClassName("");
                this.val$viewRoot.setHint(WebContentsAccessibilityImpl.this.mProductVersion);
                if(arg4 == null) {
                    this.val$viewRoot.asyncCommit();
                    return;
                }

                WebContentsAccessibilityImpl.this.createVirtualStructure(this.val$viewRoot, arg4, this.val$ignoreScrollOffset);
            }
        });
    }

    public void onWindowFocusChanged(boolean arg1) {
        WindowEventObserver$$CC.onWindowFocusChanged(((WindowEventObserver)this), arg1);
    }

    public boolean performAction(int arg10, int arg11, Bundle arg12) {
        int v6;
        int v7;
        if(this.isAccessibilityEnabled()) {
            if(!this.nativeIsNodeValid(this.mNativeObj, arg10)) {
            }
            else {
                switch(arg11) {
                    case 1: {
                        goto label_168;
                    }
                    case 2: {
                        goto label_165;
                    }
                    case 16: {
                        goto label_157;
                    }
                    case 64: {
                        goto label_146;
                    }
                    case 128: {
                        goto label_137;
                    }
                    case 256: {
                        goto label_126;
                    }
                    case 512: {
                        goto label_115;
                    }
                    case 1024: {
                        goto label_105;
                    }
                    case 2048: {
                        goto label_95;
                    }
                    case 4096: {
                        goto label_93;
                    }
                    case 8192: {
                        goto label_91;
                    }
                    case 16384: {
                        goto label_85;
                    }
                    case 32768: {
                        goto label_79;
                    }
                    case 65536: {
                        goto label_73;
                    }
                    case 131072: {
                        goto label_54;
                    }
                    case 262144: 
                    case 524288: {
                        goto label_51;
                    }
                    case 2097152: {
                        goto label_32;
                    }
                    case 16908342: {
                        goto label_29;
                    }
                    case 16908344: {
                        goto label_25;
                    }
                    case 16908345: {
                        goto label_21;
                    }
                    case 16908346: {
                        goto label_17;
                    }
                    case 16908347: {
                        goto label_13;
                    }
                    case 16908348: {
                        goto label_10;
                    }
                }

                return 0;
            label_165:
                this.nativeBlur(this.mNativeObj);
                return 1;
            label_168:
                if(!this.mView.hasFocus()) {
                    this.mView.requestFocus();
                }

                this.nativeFocus(this.mNativeObj, arg10);
                return 1;
            label_105:
                if(arg12 == null) {
                    return 0;
                }

                String v11 = arg12.getString("ACTION_ARGUMENT_HTML_ELEMENT_STRING");
                if(v11 == null) {
                    return 0;
                }

                return this.jumpToElementType(arg10, v11.toUpperCase(Locale.US), true);
            label_115:
                if(arg12 == null) {
                    return 0;
                }

                arg11 = arg12.getInt("ACTION_ARGUMENT_MOVEMENT_GRANULARITY_INT");
                boolean v12 = arg12.getBoolean("ACTION_ARGUMENT_EXTEND_SELECTION_BOOLEAN");
                if(!WebContentsAccessibilityImpl.isValidMovementGranularity(arg11)) {
                    return 0;
                }

                return this.previousAtGranularity(arg11, v12, arg10);
            label_51:
                this.nativeClick(this.mNativeObj, arg10);
                return 1;
            label_54:
                if(!this.nativeIsEditableText(this.mNativeObj, arg10)) {
                    return 0;
                }

                if(arg12 != null) {
                    int v1 = arg12.getInt("ACTION_ARGUMENT_SELECTION_START_INT");
                    v7 = arg12.getInt("ACTION_ARGUMENT_SELECTION_END_INT");
                    v6 = v1;
                }
                else {
                    v6 = 0;
                    v7 = 0;
                }

                this.nativeSetSelection(this.mNativeObj, arg10, v6, v7);
                return 1;
            label_126:
                if(arg12 == null) {
                    return 0;
                }

                arg11 = arg12.getInt("ACTION_ARGUMENT_MOVEMENT_GRANULARITY_INT");
                v12 = arg12.getBoolean("ACTION_ARGUMENT_EXTEND_SELECTION_BOOLEAN");
                if(!WebContentsAccessibilityImpl.isValidMovementGranularity(arg11)) {
                    return 0;
                }

                return this.nextAtGranularity(arg11, v12, arg10);
            label_137:
                this.sendAccessibilityEvent(arg10, 0x10000);
                if(this.mAccessibilityFocusId == arg10) {
                    this.mAccessibilityFocusId = -1;
                    this.mAccessibilityFocusRect = null;
                }

                return 1;
            label_73:
                if(this.mWebContents != null) {
                    this.mWebContents.cut();
                    return 1;
                }

                return 0;
            label_10:
                this.nativeShowContextMenu(this.mNativeObj, arg10);
                return 1;
            label_13:
                return this.nativeScroll(this.mNativeObj, arg10, 5);
            label_79:
                if(this.mWebContents != null) {
                    this.mWebContents.paste();
                    return 1;
                }

                return 0;
            label_17:
                return this.nativeScroll(this.mNativeObj, arg10, 3);
            label_146:
                if(!this.moveAccessibilityFocusToId(arg10)) {
                    return 1;
                }

                if(!this.mIsHovering) {
                    this.nativeScrollToMakeNodeVisible(this.mNativeObj, this.mAccessibilityFocusId);
                }
                else {
                    this.mPendingScrollToMakeNodeVisible = true;
                }

                return 1;
            label_85:
                if(this.mWebContents != null) {
                    this.mWebContents.copy();
                    return 1;
                }

                return 0;
            label_21:
                return this.nativeScroll(this.mNativeObj, arg10, 4);
            label_25:
                return this.nativeScroll(this.mNativeObj, arg10, 2);
            label_91:
                return this.scrollBackward(arg10);
            label_93:
                return this.scrollForward(arg10);
            label_157:
                if(!this.mView.hasFocus()) {
                    this.mView.requestFocus();
                }

                this.nativeClick(this.mNativeObj, arg10);
                return 1;
            label_29:
                this.nativeScrollToMakeNodeVisible(this.mNativeObj, arg10);
                return 1;
            label_95:
                if(arg12 == null) {
                    return 0;
                }

                v11 = arg12.getString("ACTION_ARGUMENT_HTML_ELEMENT_STRING");
                if(v11 == null) {
                    return 0;
                }

                return this.jumpToElementType(arg10, v11.toUpperCase(Locale.US), false);
            label_32:
                if(!this.nativeIsEditableText(this.mNativeObj, arg10)) {
                    return 0;
                }

                if(arg12 == null) {
                    return 0;
                }

                v11 = arg12.getString("ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE");
                if(v11 == null) {
                    return 0;
                }

                this.nativeSetTextFieldValue(this.mNativeObj, arg10, v11);
                this.nativeSetSelection(this.mNativeObj, arg10, v11.length(), v11.length());
                return 1;
            }
        }

        return 0;
    }

    public boolean performAction(int arg1, Bundle arg2) {
        return 0;
    }

    private boolean previousAtGranularity(int arg8, boolean arg9, int arg10) {
        if(arg10 != this.mSelectionNodeId) {
            return 0;
        }

        this.setGranularityAndUpdateSelection(arg8);
        return this.nativePreviousAtGranularity(this.mNativeObj, this.mSelectionGranularity, arg9, arg10, this.mSelectionEndIndex);
    }

    public void refreshState() {
        this.setState(this.mAccessibilityManager.isEnabled());
    }

    private boolean scrollBackward(int arg4) {
        if(this.nativeIsSlider(this.mNativeObj, arg4)) {
            return this.nativeAdjustSlider(this.mNativeObj, arg4, false);
        }

        return this.nativeScroll(this.mNativeObj, arg4, 1);
    }

    private boolean scrollForward(int arg4) {
        if(this.nativeIsSlider(this.mNativeObj, arg4)) {
            return this.nativeAdjustSlider(this.mNativeObj, arg4, true);
        }

        return this.nativeScroll(this.mNativeObj, arg4, 0);
    }

    private void sendAccessibilityEvent(int arg2, int arg3) {
        if(arg2 == -1) {
            this.mView.sendAccessibilityEvent(arg3);
            return;
        }

        AccessibilityEvent v2 = this.buildAccessibilityEvent(arg2, arg3);
        if(v2 != null) {
            this.mView.requestSendAccessibilityEvent(this.mView, v2);
        }
    }

    @CalledByNative private void sendDelayedWindowContentChangedEvent() {
        if(this.mSendWindowContentChangedRunnable != null) {
            return;
        }

        this.mSendWindowContentChangedRunnable = new Runnable() {
            public void run() {
                WebContentsAccessibilityImpl.this.sendWindowContentChangedOnView();
            }
        };
        this.mView.postDelayed(this.mSendWindowContentChangedRunnable, 500);
    }

    private void sendWindowContentChangedOnView() {
        if(this.mSendWindowContentChangedRunnable != null) {
            this.mView.removeCallbacks(this.mSendWindowContentChangedRunnable);
            this.mSendWindowContentChangedRunnable = null;
        }

        this.mView.sendAccessibilityEvent(0x800);
    }

    private void sendWindowContentChangedOnVirtualView(int arg2) {
        this.sendAccessibilityEvent(arg2, 0x800);
    }

    @VisibleForTesting public void setAccessibilityEnabledForTesting() {
        this.mAccessibilityEnabledForTesting = true;
    }

    @CalledByNative private void setAccessibilityEventBooleanAttributes(AccessibilityEvent arg1, boolean arg2, boolean arg3, boolean arg4, boolean arg5) {
        arg1.setChecked(arg2);
        arg1.setEnabled(arg3);
        arg1.setPassword(arg4);
        arg1.setScrollable(arg5);
    }

    @CalledByNative private void setAccessibilityEventClassName(AccessibilityEvent arg1, String arg2) {
        arg1.setClassName(((CharSequence)arg2));
    }

    @CalledByNative protected void setAccessibilityEventCollectionInfo(AccessibilityEvent arg2, int arg3, int arg4, boolean arg5) {
        Bundle v2 = this.getOrCreateBundleForAccessibilityEvent(arg2);
        v2.putInt("AccessibilityNodeInfo.CollectionInfo.rowCount", arg3);
        v2.putInt("AccessibilityNodeInfo.CollectionInfo.columnCount", arg4);
        v2.putBoolean("AccessibilityNodeInfo.CollectionInfo.hierarchical", arg5);
    }

    @CalledByNative protected void setAccessibilityEventCollectionItemInfo(AccessibilityEvent arg2, int arg3, int arg4, int arg5, int arg6) {
        Bundle v2 = this.getOrCreateBundleForAccessibilityEvent(arg2);
        v2.putInt("AccessibilityNodeInfo.CollectionItemInfo.rowIndex", arg3);
        v2.putInt("AccessibilityNodeInfo.CollectionItemInfo.rowSpan", arg4);
        v2.putInt("AccessibilityNodeInfo.CollectionItemInfo.columnIndex", arg5);
        v2.putInt("AccessibilityNodeInfo.CollectionItemInfo.columnSpan", arg6);
    }

    @CalledByNative protected void setAccessibilityEventHeadingFlag(AccessibilityEvent arg2, boolean arg3) {
        this.getOrCreateBundleForAccessibilityEvent(arg2).putBoolean("AccessibilityNodeInfo.CollectionItemInfo.heading", arg3);
    }

    @CalledByNative private void setAccessibilityEventListAttributes(AccessibilityEvent arg1, int arg2, int arg3) {
        arg1.setCurrentItemIndex(arg2);
        arg1.setItemCount(arg3);
    }

    @CalledByNative protected void setAccessibilityEventLollipopAttributes(AccessibilityEvent arg2, boolean arg3, boolean arg4, boolean arg5, boolean arg6, int arg7, int arg8) {
        Bundle v2 = this.getOrCreateBundleForAccessibilityEvent(arg2);
        v2.putBoolean("AccessibilityNodeInfo.canOpenPopup", arg3);
        v2.putBoolean("AccessibilityNodeInfo.contentInvalid", arg4);
        v2.putBoolean("AccessibilityNodeInfo.dismissable", arg5);
        v2.putBoolean("AccessibilityNodeInfo.multiLine", arg6);
        v2.putInt("AccessibilityNodeInfo.inputType", arg7);
        v2.putInt("AccessibilityNodeInfo.liveRegion", arg8);
    }

    @CalledByNative protected void setAccessibilityEventRangeInfo(AccessibilityEvent arg2, int arg3, float arg4, float arg5, float arg6) {
        Bundle v2 = this.getOrCreateBundleForAccessibilityEvent(arg2);
        v2.putInt("AccessibilityNodeInfo.RangeInfo.type", arg3);
        v2.putFloat("AccessibilityNodeInfo.RangeInfo.min", arg4);
        v2.putFloat("AccessibilityNodeInfo.RangeInfo.max", arg5);
        v2.putFloat("AccessibilityNodeInfo.RangeInfo.current", arg6);
    }

    @CalledByNative private void setAccessibilityEventScrollAttributes(AccessibilityEvent arg1, int arg2, int arg3, int arg4, int arg5) {
        arg1.setScrollX(arg2);
        arg1.setScrollY(arg3);
        arg1.setMaxScrollX(arg4);
        arg1.setMaxScrollY(arg5);
    }

    @CalledByNative private void setAccessibilityEventSelectionAttrs(AccessibilityEvent arg1, int arg2, int arg3, int arg4, String arg5) {
        arg1.setFromIndex(arg2);
        arg1.setToIndex(arg3);
        arg1.setItemCount(arg4);
        arg1.getText().add(arg5);
    }

    @CalledByNative private void setAccessibilityEventTextChangedAttrs(AccessibilityEvent arg1, int arg2, int arg3, int arg4, String arg5, String arg6) {
        arg1.setFromIndex(arg2);
        arg1.setAddedCount(arg3);
        arg1.setRemovedCount(arg4);
        arg1.setBeforeText(((CharSequence)arg5));
        arg1.getText().add(arg6);
    }

    @CalledByNative private void setAccessibilityNodeInfoBooleanAttributes(AccessibilityNodeInfo arg1, int arg2, boolean arg3, boolean arg4, boolean arg5, boolean arg6, boolean arg7, boolean arg8, boolean arg9, boolean arg10, boolean arg11, boolean arg12) {
        arg1.setCheckable(arg3);
        arg1.setChecked(arg4);
        arg1.setClickable(arg5);
        arg1.setEnabled(arg6);
        arg1.setFocusable(arg7);
        arg1.setFocused(arg8);
        arg1.setPassword(arg9);
        arg1.setScrollable(arg10);
        arg1.setSelected(arg11);
        arg1.setVisibleToUser(arg12);
        arg1.setMovementGranularities(7);
        if(this.mAccessibilityFocusId == arg2) {
            arg1.setAccessibilityFocused(true);
        }
        else {
            arg1.setAccessibilityFocused(false);
        }
    }

    @CalledByNative private void setAccessibilityNodeInfoClassName(AccessibilityNodeInfo arg1, String arg2) {
        arg1.setClassName(((CharSequence)arg2));
    }

    @CalledByNative protected void setAccessibilityNodeInfoCollectionInfo(AccessibilityNodeInfo arg1, int arg2, int arg3, boolean arg4) {
    }

    @CalledByNative protected void setAccessibilityNodeInfoCollectionItemInfo(AccessibilityNodeInfo arg1, int arg2, int arg3, int arg4, int arg5, boolean arg6) {
    }

    @CalledByNative protected void setAccessibilityNodeInfoKitKatAttributes(AccessibilityNodeInfo arg1, boolean arg2, boolean arg3, String arg4, String arg5, String arg6, int arg7, int arg8, boolean arg9) {
    }

    @CalledByNative private void setAccessibilityNodeInfoLocation(AccessibilityNodeInfo arg4, int arg5, int arg6, int arg7, int arg8, int arg9, int arg10, int arg11, boolean arg12) {
        Rect v0 = new Rect(arg8, arg9, arg8 + arg10, arg9 + arg11);
        if(arg12) {
            v0.offset(0, ((int)this.mWebContents.getRenderCoordinates().getContentOffsetYPix()));
        }

        arg4.setBoundsInParent(v0);
        Rect v8 = new Rect(arg6, arg7, arg10 + arg6, arg11 + arg7);
        this.convertWebRectToAndroidCoordinates(v8);
        arg4.setBoundsInScreen(v8);
        if(arg5 == this.mAccessibilityFocusId && arg5 != this.mCurrentRootId) {
            if(this.mAccessibilityFocusRect == null) {
                this.mAccessibilityFocusRect = v8;
            }
            else if(!this.mAccessibilityFocusRect.equals(v8)) {
                this.mAccessibilityFocusRect = v8;
                this.moveAccessibilityFocusToIdAndRefocusIfNeeded(arg5);
            }
        }
    }

    @CalledByNative protected void setAccessibilityNodeInfoLollipopAttributes(AccessibilityNodeInfo arg1, boolean arg2, boolean arg3, boolean arg4, boolean arg5, int arg6, int arg7) {
    }

    @CalledByNative protected void setAccessibilityNodeInfoOAttributes(AccessibilityNodeInfo arg1, boolean arg2) {
    }

    @CalledByNative private void setAccessibilityNodeInfoParent(AccessibilityNodeInfo arg2, int arg3) {
        arg2.setParent(this.mView, arg3);
    }

    @CalledByNative protected void setAccessibilityNodeInfoRangeInfo(AccessibilityNodeInfo arg1, int arg2, float arg3, float arg4, float arg5) {
    }

    @SuppressLint(value={"NewApi"}) @CalledByNative private void setAccessibilityNodeInfoText(AccessibilityNodeInfo arg1, String arg2, boolean arg3, boolean arg4, String arg5) {
        arg1.setText(this.computeText(arg2, arg4, arg5));
    }

    @CalledByNative protected void setAccessibilityNodeInfoViewIdResourceName(AccessibilityNodeInfo arg1, String arg2) {
    }

    private void setGranularityAndUpdateSelection(int arg3) {
        this.mSelectionGranularity = arg3;
        if(this.mSelectionGranularity == 0) {
            this.mSelectionStartIndex = -1;
            this.mSelectionEndIndex = -1;
        }

        if((this.nativeIsEditableText(this.mNativeObj, this.mAccessibilityFocusId)) && (this.nativeIsFocused(this.mNativeObj, this.mAccessibilityFocusId))) {
            this.mSelectionStartIndex = this.nativeGetEditableTextSelectionStart(this.mNativeObj, this.mAccessibilityFocusId);
            this.mSelectionEndIndex = this.nativeGetEditableTextSelectionEnd(this.mNativeObj, this.mAccessibilityFocusId);
        }
    }

    public void setObscuredByAnotherView(boolean arg2) {
        if(arg2 != this.mIsObscuredByAnotherView) {
            this.mIsObscuredByAnotherView = arg2;
            this.mView.sendAccessibilityEvent(0x800);
        }
    }

    public void setShouldFocusOnPageLoad(boolean arg1) {
        this.mShouldFocusOnPageLoad = arg1;
    }

    public void setState(boolean arg1) {
        if(!arg1) {
            this.mNativeAccessibilityAllowed = false;
            this.mTouchExplorationEnabled = false;
        }
        else {
            this.mNativeAccessibilityAllowed = true;
            this.mTouchExplorationEnabled = this.mAccessibilityManager.isTouchExplorationEnabled();
        }
    }

    @CalledByNative boolean shouldExposePasswordText() {
        ContentResolver v0 = this.mContext.getContentResolver();
        boolean v2 = false;
        if(Build$VERSION.SDK_INT >= 26) {
            if(Settings$System.getInt(v0, "show_password", 1) == 1) {
                v2 = true;
            }

            return v2;
        }

        if(Settings$Secure.getInt(v0, "speak_password", 0) == 1) {
            v2 = true;
        }

        return v2;
    }

    @CalledByNative boolean shouldRespectDisplayedPasswordText() {
        boolean v0 = Build$VERSION.SDK_INT >= 26 ? true : false;
        return v0;
    }

    public boolean supportsAction(int arg1) {
        return 0;
    }
}

