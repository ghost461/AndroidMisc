package android.support.v4.widget;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.SparseArrayCompat;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewParentCompat;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeProviderCompat;
import android.support.v4.view.accessibility.AccessibilityRecordCompat;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityRecord;
import java.util.ArrayList;
import java.util.List;

public abstract class ExploreByTouchHelper extends AccessibilityDelegateCompat {
    final class android.support.v4.widget.ExploreByTouchHelper$1 implements BoundsAdapter {
        android.support.v4.widget.ExploreByTouchHelper$1() {
            super();
        }

        public void obtainBounds(AccessibilityNodeInfoCompat arg1, Rect arg2) {
            arg1.getBoundsInParent(arg2);
        }

        public void obtainBounds(Object arg1, Rect arg2) {
            this.obtainBounds(((AccessibilityNodeInfoCompat)arg1), arg2);
        }
    }

    final class android.support.v4.widget.ExploreByTouchHelper$2 implements CollectionAdapter {
        android.support.v4.widget.ExploreByTouchHelper$2() {
            super();
        }

        public AccessibilityNodeInfoCompat get(SparseArrayCompat arg1, int arg2) {
            return arg1.valueAt(arg2);
        }

        public Object get(Object arg1, int arg2) {
            return this.get(((SparseArrayCompat)arg1), arg2);
        }

        public int size(SparseArrayCompat arg1) {
            return arg1.size();
        }

        public int size(Object arg1) {
            return this.size(((SparseArrayCompat)arg1));
        }
    }

    class MyNodeProvider extends AccessibilityNodeProviderCompat {
        MyNodeProvider(ExploreByTouchHelper arg1) {
            ExploreByTouchHelper.this = arg1;
            super();
        }

        public AccessibilityNodeInfoCompat createAccessibilityNodeInfo(int arg2) {
            return AccessibilityNodeInfoCompat.obtain(ExploreByTouchHelper.this.obtainAccessibilityNodeInfo(arg2));
        }

        public AccessibilityNodeInfoCompat findFocus(int arg2) {
            arg2 = arg2 == 2 ? ExploreByTouchHelper.this.mAccessibilityFocusedVirtualViewId : ExploreByTouchHelper.this.mKeyboardFocusedVirtualViewId;
            if(arg2 == 0x80000000) {
                return null;
            }

            return this.createAccessibilityNodeInfo(arg2);
        }

        public boolean performAction(int arg2, int arg3, Bundle arg4) {
            return ExploreByTouchHelper.this.performAction(arg2, arg3, arg4);
        }
    }

    private static final String DEFAULT_CLASS_NAME = "android.view.View";
    public static final int HOST_ID = -1;
    public static final int INVALID_ID = 0x80000000;
    private static final Rect INVALID_PARENT_BOUNDS;
    private static final BoundsAdapter NODE_ADAPTER;
    private static final CollectionAdapter SPARSE_VALUES_ADAPTER;
    private int mAccessibilityFocusedVirtualViewId;
    private final View mHost;
    private int mHoveredVirtualViewId;
    private int mKeyboardFocusedVirtualViewId;
    private final AccessibilityManager mManager;
    private MyNodeProvider mNodeProvider;
    private final int[] mTempGlobalRect;
    private final Rect mTempParentRect;
    private final Rect mTempScreenRect;
    private final Rect mTempVisibleRect;

    static {
        ExploreByTouchHelper.INVALID_PARENT_BOUNDS = new Rect(0x7FFFFFFF, 0x7FFFFFFF, 0x80000000, 0x80000000);
        ExploreByTouchHelper.NODE_ADAPTER = new android.support.v4.widget.ExploreByTouchHelper$1();
        ExploreByTouchHelper.SPARSE_VALUES_ADAPTER = new android.support.v4.widget.ExploreByTouchHelper$2();
    }

    public ExploreByTouchHelper(View arg3) {
        super();
        this.mTempScreenRect = new Rect();
        this.mTempParentRect = new Rect();
        this.mTempVisibleRect = new Rect();
        this.mTempGlobalRect = new int[2];
        this.mAccessibilityFocusedVirtualViewId = 0x80000000;
        this.mKeyboardFocusedVirtualViewId = 0x80000000;
        this.mHoveredVirtualViewId = 0x80000000;
        if(arg3 == null) {
            throw new IllegalArgumentException("View may not be null");
        }

        this.mHost = arg3;
        this.mManager = arg3.getContext().getSystemService("accessibility");
        arg3.setFocusable(true);
        if(ViewCompat.getImportantForAccessibility(arg3) == 0) {
            ViewCompat.setImportantForAccessibility(arg3, 1);
        }
    }

    static int access$000(ExploreByTouchHelper arg0) {
        return arg0.mAccessibilityFocusedVirtualViewId;
    }

    static int access$100(ExploreByTouchHelper arg0) {
        return arg0.mKeyboardFocusedVirtualViewId;
    }

    private boolean clearAccessibilityFocus(int arg2) {
        if(this.mAccessibilityFocusedVirtualViewId == arg2) {
            this.mAccessibilityFocusedVirtualViewId = 0x80000000;
            this.mHost.invalidate();
            this.sendEventForVirtualView(arg2, 0x10000);
            return 1;
        }

        return 0;
    }

    public final boolean clearKeyboardFocusForVirtualView(int arg3) {
        if(this.mKeyboardFocusedVirtualViewId != arg3) {
            return 0;
        }

        this.mKeyboardFocusedVirtualViewId = 0x80000000;
        this.onVirtualViewKeyboardFocusChanged(arg3, false);
        this.sendEventForVirtualView(arg3, 8);
        return 1;
    }

    private boolean clickKeyboardFocusedVirtualView() {
        boolean v0 = this.mKeyboardFocusedVirtualViewId == 0x80000000 || !this.onPerformActionForVirtualView(this.mKeyboardFocusedVirtualViewId, 16, null) ? false : true;
        return v0;
    }

    private AccessibilityEvent createEvent(int arg2, int arg3) {
        if(arg2 != -1) {
            return this.createEventForChild(arg2, arg3);
        }

        return this.createEventForHost(arg3);
    }

    private AccessibilityEvent createEventForChild(int arg4, int arg5) {
        AccessibilityEvent v5 = AccessibilityEvent.obtain(arg5);
        AccessibilityNodeInfoCompat v0 = this.obtainAccessibilityNodeInfo(arg4);
        v5.getText().add(v0.getText());
        v5.setContentDescription(v0.getContentDescription());
        v5.setScrollable(v0.isScrollable());
        v5.setPassword(v0.isPassword());
        v5.setEnabled(v0.isEnabled());
        v5.setChecked(v0.isChecked());
        this.onPopulateEventForVirtualView(arg4, v5);
        if((v5.getText().isEmpty()) && v5.getContentDescription() == null) {
            throw new RuntimeException("Callbacks must add text or a content description in populateEventForVirtualViewId()");
        }

        v5.setClassName(v0.getClassName());
        AccessibilityRecordCompat.setSource(((AccessibilityRecord)v5), this.mHost, arg4);
        v5.setPackageName(this.mHost.getContext().getPackageName());
        return v5;
    }

    private AccessibilityEvent createEventForHost(int arg2) {
        AccessibilityEvent v2 = AccessibilityEvent.obtain(arg2);
        this.mHost.onInitializeAccessibilityEvent(v2);
        return v2;
    }

    @NonNull private AccessibilityNodeInfoCompat createNodeForChild(int arg8) {
        AccessibilityNodeInfoCompat v0 = AccessibilityNodeInfoCompat.obtain();
        v0.setEnabled(true);
        v0.setFocusable(true);
        v0.setClassName("android.view.View");
        v0.setBoundsInParent(ExploreByTouchHelper.INVALID_PARENT_BOUNDS);
        v0.setBoundsInScreen(ExploreByTouchHelper.INVALID_PARENT_BOUNDS);
        v0.setParent(this.mHost);
        this.onPopulateNodeForVirtualView(arg8, v0);
        if(v0.getText() == null && v0.getContentDescription() == null) {
            throw new RuntimeException("Callbacks must add text or a content description in populateNodeForVirtualViewId()");
        }

        v0.getBoundsInParent(this.mTempParentRect);
        if(this.mTempParentRect.equals(ExploreByTouchHelper.INVALID_PARENT_BOUNDS)) {
            throw new RuntimeException("Callbacks must set parent bounds in populateNodeForVirtualViewId()");
        }

        int v2 = v0.getActions();
        if((v2 & 0x40) != 0) {
            throw new RuntimeException("Callbacks must not add ACTION_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
        }

        int v3 = 0x80;
        if((v2 & v3) != 0) {
            throw new RuntimeException("Callbacks must not add ACTION_CLEAR_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
        }

        v0.setPackageName(this.mHost.getContext().getPackageName());
        v0.setSource(this.mHost, arg8);
        if(this.mAccessibilityFocusedVirtualViewId == arg8) {
            v0.setAccessibilityFocused(true);
            v0.addAction(v3);
        }
        else {
            v0.setAccessibilityFocused(false);
            v0.addAction(0x40);
        }

        boolean v8 = this.mKeyboardFocusedVirtualViewId == arg8 ? true : false;
        if(v8) {
            v0.addAction(2);
        }
        else if(v0.isFocusable()) {
            v0.addAction(1);
        }

        v0.setFocused(v8);
        this.mHost.getLocationOnScreen(this.mTempGlobalRect);
        v0.getBoundsInScreen(this.mTempScreenRect);
        if(this.mTempScreenRect.equals(ExploreByTouchHelper.INVALID_PARENT_BOUNDS)) {
            v0.getBoundsInParent(this.mTempScreenRect);
            v2 = -1;
            if(v0.mParentVirtualDescendantId != v2) {
                AccessibilityNodeInfoCompat v8_1 = AccessibilityNodeInfoCompat.obtain();
                for(v3 = v0.mParentVirtualDescendantId; v3 != v2; v3 = v8_1.mParentVirtualDescendantId) {
                    v8_1.setParent(this.mHost, v2);
                    v8_1.setBoundsInParent(ExploreByTouchHelper.INVALID_PARENT_BOUNDS);
                    this.onPopulateNodeForVirtualView(v3, v8_1);
                    v8_1.getBoundsInParent(this.mTempParentRect);
                    this.mTempScreenRect.offset(this.mTempParentRect.left, this.mTempParentRect.top);
                }

                v8_1.recycle();
            }

            this.mTempScreenRect.offset(this.mTempGlobalRect[0] - this.mHost.getScrollX(), this.mTempGlobalRect[1] - this.mHost.getScrollY());
        }

        if(this.mHost.getLocalVisibleRect(this.mTempVisibleRect)) {
            this.mTempVisibleRect.offset(this.mTempGlobalRect[0] - this.mHost.getScrollX(), this.mTempGlobalRect[1] - this.mHost.getScrollY());
            if(this.mTempScreenRect.intersect(this.mTempVisibleRect)) {
                v0.setBoundsInScreen(this.mTempScreenRect);
                if(this.isVisibleToUser(this.mTempScreenRect)) {
                    v0.setVisibleToUser(true);
                }
            }
        }

        return v0;
    }

    @NonNull private AccessibilityNodeInfoCompat createNodeForHost() {
        AccessibilityNodeInfoCompat v0 = AccessibilityNodeInfoCompat.obtain(this.mHost);
        ViewCompat.onInitializeAccessibilityNodeInfo(this.mHost, v0);
        ArrayList v1 = new ArrayList();
        this.getVisibleVirtualViews(((List)v1));
        if(v0.getChildCount() > 0 && v1.size() > 0) {
            throw new RuntimeException("Views cannot have both real and virtual children");
        }

        int v2 = 0;
        int v3 = v1.size();
        while(v2 < v3) {
            v0.addChild(this.mHost, v1.get(v2).intValue());
            ++v2;
        }

        return v0;
    }

    public final boolean dispatchHoverEvent(@NonNull MotionEvent arg6) {
        boolean v1 = false;
        if(this.mManager.isEnabled()) {
            if(!this.mManager.isTouchExplorationEnabled()) {
            }
            else {
                int v0 = arg6.getAction();
                int v4 = 0x80000000;
                if(v0 != 7) {
                    switch(v0) {
                        case 9: {
                            goto label_20;
                        }
                        case 10: {
                            goto label_15;
                        }
                    }

                    return 0;
                label_15:
                    if(this.mAccessibilityFocusedVirtualViewId != v4) {
                        this.updateHoveredVirtualView(v4);
                        return 1;
                    }
                    else {
                        return 0;
                    }
                }

            label_20:
                int v6 = this.getVirtualViewAt(arg6.getX(), arg6.getY());
                this.updateHoveredVirtualView(v6);
                if(v6 != v4) {
                    v1 = true;
                }

                return v1;
            }
        }

        return 0;
    }

    public final boolean dispatchKeyEvent(@NonNull KeyEvent arg7) {
        boolean v1_1;
        int v1 = 0;
        if(arg7.getAction() != 1) {
            int v0 = arg7.getKeyCode();
            Rect v4 = null;
            if(v0 != 61) {
                if(v0 != 66) {
                    switch(v0) {
                        case 19: 
                        case 20: 
                        case 21: 
                        case 22: {
                            goto label_12;
                        }
                        case 23: {
                            goto label_26;
                        }
                    }

                    goto label_41;
                label_12:
                    if(arg7.hasNoModifiers()) {
                        v0 = ExploreByTouchHelper.keyToDirection(v0);
                        int v7 = arg7.getRepeatCount() + 1;
                        boolean v3;
                        for(v3 = false; v1 < v7; v3 = true) {
                            if(!this.moveFocus(v0, v4)) {
                                break;
                            }

                            ++v1;
                        }

                        v1_1 = v3;
                    }
                    else {
                    }

                    goto label_41;
                }

            label_26:
                if(!arg7.hasNoModifiers()) {
                    goto label_41;
                }

                if(arg7.getRepeatCount() != 0) {
                    goto label_41;
                }

                this.clickKeyboardFocusedVirtualView();
                v1_1 = true;
            }
            else {
                if(arg7.hasNoModifiers()) {
                    v1_1 = this.moveFocus(2, v4);
                    goto label_41;
                }

                if(!arg7.hasModifiers(1)) {
                    goto label_41;
                }

                v1_1 = this.moveFocus(1, v4);
            }
        }

    label_41:
        return ((boolean)v1);
    }

    public final int getAccessibilityFocusedVirtualViewId() {
        return this.mAccessibilityFocusedVirtualViewId;
    }

    public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View arg1) {
        if(this.mNodeProvider == null) {
            this.mNodeProvider = new MyNodeProvider(this);
        }

        return this.mNodeProvider;
    }

    private SparseArrayCompat getAllNodes() {
        ArrayList v0 = new ArrayList();
        this.getVisibleVirtualViews(((List)v0));
        SparseArrayCompat v1 = new SparseArrayCompat();
        int v2;
        for(v2 = 0; v2 < ((List)v0).size(); ++v2) {
            v1.put(v2, this.createNodeForChild(v2));
        }

        return v1;
    }

    private void getBoundsInParent(int arg1, Rect arg2) {
        this.obtainAccessibilityNodeInfo(arg1).getBoundsInParent(arg2);
    }

    @Deprecated public int getFocusedVirtualView() {
        return this.getAccessibilityFocusedVirtualViewId();
    }

    public final int getKeyboardFocusedVirtualViewId() {
        return this.mKeyboardFocusedVirtualViewId;
    }

    protected abstract int getVirtualViewAt(float arg1, float arg2);

    protected abstract void getVisibleVirtualViews(List arg1);

    private static Rect guessPreviouslyFocusedRect(@NonNull View arg4, int arg5, @NonNull Rect arg6) {
        int v0 = arg4.getWidth();
        int v4 = arg4.getHeight();
        if(arg5 == 17) {
            arg6.set(v0, 0, v0, v4);
        }
        else if(arg5 != 33) {
            int v3 = -1;
            if(arg5 == 66) {
                arg6.set(v3, 0, v3, v4);
            }
            else if(arg5 != 130) {
                throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
            }
            else {
                arg6.set(0, v3, v0, v3);
            }
        }
        else {
            arg6.set(0, v4, v0, v4);
        }

        return arg6;
    }

    public final void invalidateRoot() {
        this.invalidateVirtualView(-1, 1);
    }

    public final void invalidateVirtualView(int arg3, int arg4) {
        if(arg3 != 0x80000000 && (this.mManager.isEnabled())) {
            ViewParent v0 = this.mHost.getParent();
            if(v0 != null) {
                AccessibilityEvent v3 = this.createEvent(arg3, 0x800);
                AccessibilityEventCompat.setContentChangeTypes(v3, arg4);
                ViewParentCompat.requestSendAccessibilityEvent(v0, this.mHost, v3);
            }
        }
    }

    public final void invalidateVirtualView(int arg2) {
        this.invalidateVirtualView(arg2, 0);
    }

    private boolean isVisibleToUser(Rect arg4) {
        boolean v0 = false;
        if(arg4 != null) {
            if(arg4.isEmpty()) {
            }
            else if(this.mHost.getWindowVisibility() != 0) {
                return 0;
            }
            else {
                ViewParent v4 = this.mHost.getParent();
                while(true) {
                    if((v4 instanceof View)) {
                        if(((View)v4).getAlpha() > 0f) {
                            if(((View)v4).getVisibility() != 0) {
                            }
                            else {
                                v4 = ((View)v4).getParent();
                                continue;
                            }
                        }

                        return 0;
                    }
                    else {
                        goto label_22;
                    }
                }

                return 0;
            label_22:
                if(v4 != null) {
                    v0 = true;
                }

                return v0;
            }
        }

        return 0;
    }

    private static int keyToDirection(int arg0) {
        switch(arg0) {
            case 19: {
                return 33;
            }
            case 21: {
                return 17;
            }
            case 22: {
                return 66;
            }
        }

        return 130;
    }

    private boolean moveFocus(int arg10, @Nullable Rect arg11) {
        Object v10;
        SparseArrayCompat v7 = this.getAllNodes();
        int v0 = this.mKeyboardFocusedVirtualViewId;
        int v8 = 0x80000000;
        Object v0_1 = v0 == v8 ? null : v7.get(v0);
        Object v3 = v0_1;
        if(arg10 == 17) {
            goto label_37;
        }
        else if(arg10 == 33) {
            goto label_37;
        }
        else if(arg10 == 66) {
            goto label_37;
        }
        else if(arg10 != 130) {
            switch(arg10) {
                case 1: 
                case 2: {
                    boolean v5 = ViewCompat.getLayoutDirection(this.mHost) == 1 ? true : false;
                    v10 = FocusStrategy.findNextFocusInRelativeDirection(v7, ExploreByTouchHelper.SPARSE_VALUES_ADAPTER, ExploreByTouchHelper.NODE_ADAPTER, v3, arg10, v5, false);
                    goto label_54;
                label_37:
                    Rect v4 = new Rect();
                    if(this.mKeyboardFocusedVirtualViewId != v8) {
                        this.getBoundsInParent(this.mKeyboardFocusedVirtualViewId, v4);
                    }
                    else if(arg11 != null) {
                        v4.set(arg11);
                    }
                    else {
                        ExploreByTouchHelper.guessPreviouslyFocusedRect(this.mHost, arg10, v4);
                    }

                    v10 = FocusStrategy.findNextFocusInAbsoluteDirection(v7, ExploreByTouchHelper.SPARSE_VALUES_ADAPTER, ExploreByTouchHelper.NODE_ADAPTER, v3, v4, arg10);
                    goto label_54;
                }
                default: {
                    throw new IllegalArgumentException("direction must be one of {FOCUS_FORWARD, FOCUS_BACKWARD, FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
                }
            }
        }
        else {
            goto label_37;
        }

    label_54:
        if(v10 == null) {
        }
        else {
            v8 = v7.keyAt(v7.indexOfValue(v10));
        }

        return this.requestKeyboardFocusForVirtualView(v8);
    }

    @NonNull AccessibilityNodeInfoCompat obtainAccessibilityNodeInfo(int arg2) {
        if(arg2 == -1) {
            return this.createNodeForHost();
        }

        return this.createNodeForChild(arg2);
    }

    public final void onFocusChanged(boolean arg3, int arg4, @Nullable Rect arg5) {
        if(this.mKeyboardFocusedVirtualViewId != 0x80000000) {
            this.clearKeyboardFocusForVirtualView(this.mKeyboardFocusedVirtualViewId);
        }

        if(arg3) {
            this.moveFocus(arg4, arg5);
        }
    }

    public void onInitializeAccessibilityEvent(View arg1, AccessibilityEvent arg2) {
        super.onInitializeAccessibilityEvent(arg1, arg2);
        this.onPopulateEventForHost(arg2);
    }

    public void onInitializeAccessibilityNodeInfo(View arg1, AccessibilityNodeInfoCompat arg2) {
        super.onInitializeAccessibilityNodeInfo(arg1, arg2);
        this.onPopulateNodeForHost(arg2);
    }

    protected abstract boolean onPerformActionForVirtualView(int arg1, int arg2, Bundle arg3);

    protected void onPopulateEventForHost(AccessibilityEvent arg1) {
    }

    protected void onPopulateEventForVirtualView(int arg1, AccessibilityEvent arg2) {
    }

    protected void onPopulateNodeForHost(AccessibilityNodeInfoCompat arg1) {
    }

    protected abstract void onPopulateNodeForVirtualView(int arg1, AccessibilityNodeInfoCompat arg2);

    protected void onVirtualViewKeyboardFocusChanged(int arg1, boolean arg2) {
    }

    boolean performAction(int arg2, int arg3, Bundle arg4) {
        if(arg2 != -1) {
            return this.performActionForChild(arg2, arg3, arg4);
        }

        return this.performActionForHost(arg3, arg4);
    }

    private boolean performActionForChild(int arg2, int arg3, Bundle arg4) {
        if(arg3 == 0x40) {
            goto label_13;
        }

        if(arg3 == 0x80) {
            goto label_11;
        }

        switch(arg3) {
            case 1: {
                goto label_9;
            }
            case 2: {
                goto label_7;
            }
        }

        return this.onPerformActionForVirtualView(arg2, arg3, arg4);
    label_7:
        return this.clearKeyboardFocusForVirtualView(arg2);
    label_9:
        return this.requestKeyboardFocusForVirtualView(arg2);
    label_11:
        return this.clearAccessibilityFocus(arg2);
    label_13:
        return this.requestAccessibilityFocus(arg2);
    }

    private boolean performActionForHost(int arg2, Bundle arg3) {
        return ViewCompat.performAccessibilityAction(this.mHost, arg2, arg3);
    }

    private boolean requestAccessibilityFocus(int arg3) {
        if(this.mManager.isEnabled()) {
            if(!this.mManager.isTouchExplorationEnabled()) {
            }
            else if(this.mAccessibilityFocusedVirtualViewId != arg3) {
                if(this.mAccessibilityFocusedVirtualViewId != 0x80000000) {
                    this.clearAccessibilityFocus(this.mAccessibilityFocusedVirtualViewId);
                }

                this.mAccessibilityFocusedVirtualViewId = arg3;
                this.mHost.invalidate();
                this.sendEventForVirtualView(arg3, 0x8000);
                return 1;
            }
            else {
                return 0;
            }
        }

        return 0;
    }

    public final boolean requestKeyboardFocusForVirtualView(int arg3) {
        if(!this.mHost.isFocused() && !this.mHost.requestFocus()) {
            return 0;
        }

        if(this.mKeyboardFocusedVirtualViewId == arg3) {
            return 0;
        }

        if(this.mKeyboardFocusedVirtualViewId != 0x80000000) {
            this.clearKeyboardFocusForVirtualView(this.mKeyboardFocusedVirtualViewId);
        }

        this.mKeyboardFocusedVirtualViewId = arg3;
        this.onVirtualViewKeyboardFocusChanged(arg3, true);
        this.sendEventForVirtualView(arg3, 8);
        return 1;
    }

    public final boolean sendEventForVirtualView(int arg3, int arg4) {
        if(arg3 != 0x80000000) {
            if(!this.mManager.isEnabled()) {
            }
            else {
                ViewParent v1 = this.mHost.getParent();
                if(v1 == null) {
                    return 0;
                }
                else {
                    return ViewParentCompat.requestSendAccessibilityEvent(v1, this.mHost, this.createEvent(arg3, arg4));
                }
            }
        }

        return 0;
    }

    private void updateHoveredVirtualView(int arg3) {
        if(this.mHoveredVirtualViewId == arg3) {
            return;
        }

        int v0 = this.mHoveredVirtualViewId;
        this.mHoveredVirtualViewId = arg3;
        this.sendEventForVirtualView(arg3, 0x80);
        this.sendEventForVirtualView(v0, 0x100);
    }
}

