package android.support.v4.view;

import android.animation.ValueAnimator;
import android.content.ClipData;
import android.content.res.ColorStateList;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff$Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.support.annotation.FloatRange;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeProviderCompat;
import android.util.Log;
import android.view.Display;
import android.view.PointerIcon;
import android.view.View$AccessibilityDelegate;
import android.view.View$DragShadowBuilder;
import android.view.View$OnApplyWindowInsetsListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowInsets;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeProvider;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.WeakHashMap;

public class ViewCompat {
    @Retention(value=RetentionPolicy.SOURCE) @interface AccessibilityLiveRegion {
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) @Retention(value=RetentionPolicy.SOURCE) @public interface FocusDirection {
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) @Retention(value=RetentionPolicy.SOURCE) @public interface FocusRealDirection {
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) @Retention(value=RetentionPolicy.SOURCE) @public interface FocusRelativeDirection {
    }

    @Retention(value=RetentionPolicy.SOURCE) @interface ImportantForAccessibility {
    }

    @Retention(value=RetentionPolicy.SOURCE) @interface LayerType {
    }

    @Retention(value=RetentionPolicy.SOURCE) @interface LayoutDirectionMode {
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) @Retention(value=RetentionPolicy.SOURCE) @public interface NestedScrollType {
    }

    @Retention(value=RetentionPolicy.SOURCE) @interface OverScroll {
    }

    @Retention(value=RetentionPolicy.SOURCE) @interface ResolvedLayoutDirectionMode {
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) @Retention(value=RetentionPolicy.SOURCE) @public interface ScrollAxis {
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) @Retention(value=RetentionPolicy.SOURCE) @public interface ScrollIndicators {
    }

    @RequiresApi(value=15) class ViewCompatApi15Impl extends ViewCompatBaseImpl {
        ViewCompatApi15Impl() {
            super();
        }

        public boolean hasOnClickListeners(View arg1) {
            return arg1.hasOnClickListeners();
        }
    }

    @RequiresApi(value=16) class ViewCompatApi16Impl extends ViewCompatApi15Impl {
        ViewCompatApi16Impl() {
            super();
        }

        public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View arg2) {
            AccessibilityNodeProvider v2 = arg2.getAccessibilityNodeProvider();
            if(v2 != null) {
                return new AccessibilityNodeProviderCompat(v2);
            }

            return null;
        }

        public boolean getFitsSystemWindows(View arg1) {
            return arg1.getFitsSystemWindows();
        }

        public int getImportantForAccessibility(View arg1) {
            return arg1.getImportantForAccessibility();
        }

        public int getMinimumHeight(View arg1) {
            return arg1.getMinimumHeight();
        }

        public int getMinimumWidth(View arg1) {
            return arg1.getMinimumWidth();
        }

        public ViewParent getParentForAccessibility(View arg1) {
            return arg1.getParentForAccessibility();
        }

        public boolean hasOverlappingRendering(View arg1) {
            return arg1.hasOverlappingRendering();
        }

        public boolean hasTransientState(View arg1) {
            return arg1.hasTransientState();
        }

        public boolean performAccessibilityAction(View arg1, int arg2, Bundle arg3) {
            return arg1.performAccessibilityAction(arg2, arg3);
        }

        public void postInvalidateOnAnimation(View arg1) {
            arg1.postInvalidateOnAnimation();
        }

        public void postInvalidateOnAnimation(View arg1, int arg2, int arg3, int arg4, int arg5) {
            arg1.postInvalidateOnAnimation(arg2, arg3, arg4, arg5);
        }

        public void postOnAnimation(View arg1, Runnable arg2) {
            arg1.postOnAnimation(arg2);
        }

        public void postOnAnimationDelayed(View arg1, Runnable arg2, long arg3) {
            arg1.postOnAnimationDelayed(arg2, arg3);
        }

        public void requestApplyInsets(View arg1) {
            arg1.requestFitSystemWindows();
        }

        public void setBackground(View arg1, Drawable arg2) {
            arg1.setBackground(arg2);
        }

        public void setHasTransientState(View arg1, boolean arg2) {
            arg1.setHasTransientState(arg2);
        }

        public void setImportantForAccessibility(View arg2, int arg3) {
            if(arg3 == 4) {
                arg3 = 2;
            }

            arg2.setImportantForAccessibility(arg3);
        }
    }

    @RequiresApi(value=17) class ViewCompatApi17Impl extends ViewCompatApi16Impl {
        ViewCompatApi17Impl() {
            super();
        }

        public Display getDisplay(View arg1) {
            return arg1.getDisplay();
        }

        public int getLabelFor(View arg1) {
            return arg1.getLabelFor();
        }

        public int getLayoutDirection(View arg1) {
            return arg1.getLayoutDirection();
        }

        public int getPaddingEnd(View arg1) {
            return arg1.getPaddingEnd();
        }

        public int getPaddingStart(View arg1) {
            return arg1.getPaddingStart();
        }

        public int getWindowSystemUiVisibility(View arg1) {
            return arg1.getWindowSystemUiVisibility();
        }

        public boolean isPaddingRelative(View arg1) {
            return arg1.isPaddingRelative();
        }

        public void setLabelFor(View arg1, int arg2) {
            arg1.setLabelFor(arg2);
        }

        public void setLayerPaint(View arg1, Paint arg2) {
            arg1.setLayerPaint(arg2);
        }

        public void setLayoutDirection(View arg1, int arg2) {
            arg1.setLayoutDirection(arg2);
        }

        public void setPaddingRelative(View arg1, int arg2, int arg3, int arg4, int arg5) {
            arg1.setPaddingRelative(arg2, arg3, arg4, arg5);
        }
    }

    @RequiresApi(value=18) class ViewCompatApi18Impl extends ViewCompatApi17Impl {
        ViewCompatApi18Impl() {
            super();
        }

        public Rect getClipBounds(View arg1) {
            return arg1.getClipBounds();
        }

        public boolean isInLayout(View arg1) {
            return arg1.isInLayout();
        }

        public void setClipBounds(View arg1, Rect arg2) {
            arg1.setClipBounds(arg2);
        }
    }

    @RequiresApi(value=19) class ViewCompatApi19Impl extends ViewCompatApi18Impl {
        ViewCompatApi19Impl() {
            super();
        }

        public int getAccessibilityLiveRegion(View arg1) {
            return arg1.getAccessibilityLiveRegion();
        }

        public boolean isAttachedToWindow(View arg1) {
            return arg1.isAttachedToWindow();
        }

        public boolean isLaidOut(View arg1) {
            return arg1.isLaidOut();
        }

        public boolean isLayoutDirectionResolved(View arg1) {
            return arg1.isLayoutDirectionResolved();
        }

        public void setAccessibilityLiveRegion(View arg1, int arg2) {
            arg1.setAccessibilityLiveRegion(arg2);
        }

        public void setImportantForAccessibility(View arg1, int arg2) {
            arg1.setImportantForAccessibility(arg2);
        }
    }

    @RequiresApi(value=21) class ViewCompatApi21Impl extends ViewCompatApi19Impl {
        private static ThreadLocal sThreadLocalRect;

        ViewCompatApi21Impl() {
            super();
        }

        public WindowInsetsCompat dispatchApplyWindowInsets(View arg1, WindowInsetsCompat arg2) {
            Object v2 = WindowInsetsCompat.unwrap(arg2);
            WindowInsets v1 = arg1.dispatchApplyWindowInsets(((WindowInsets)v2));
            if(v1 != v2) {
                WindowInsets v2_1 = new WindowInsets(v1);
            }

            return WindowInsetsCompat.wrap(v2);
        }

        public boolean dispatchNestedFling(View arg1, float arg2, float arg3, boolean arg4) {
            return arg1.dispatchNestedFling(arg2, arg3, arg4);
        }

        public boolean dispatchNestedPreFling(View arg1, float arg2, float arg3) {
            return arg1.dispatchNestedPreFling(arg2, arg3);
        }

        public boolean dispatchNestedPreScroll(View arg1, int arg2, int arg3, int[] arg4, int[] arg5) {
            return arg1.dispatchNestedPreScroll(arg2, arg3, arg4, arg5);
        }

        public boolean dispatchNestedScroll(View arg1, int arg2, int arg3, int arg4, int arg5, int[] arg6) {
            return arg1.dispatchNestedScroll(arg2, arg3, arg4, arg5, arg6);
        }

        public ColorStateList getBackgroundTintList(View arg1) {
            return arg1.getBackgroundTintList();
        }

        public PorterDuff$Mode getBackgroundTintMode(View arg1) {
            return arg1.getBackgroundTintMode();
        }

        public float getElevation(View arg1) {
            return arg1.getElevation();
        }

        private static Rect getEmptyTempRect() {
            if(ViewCompatApi21Impl.sThreadLocalRect == null) {
                ViewCompatApi21Impl.sThreadLocalRect = new ThreadLocal();
            }

            Object v0 = ViewCompatApi21Impl.sThreadLocalRect.get();
            if(v0 == null) {
                Rect v0_1 = new Rect();
                ViewCompatApi21Impl.sThreadLocalRect.set(v0_1);
            }

            ((Rect)v0).setEmpty();
            return ((Rect)v0);
        }

        public String getTransitionName(View arg1) {
            return arg1.getTransitionName();
        }

        public float getTranslationZ(View arg1) {
            return arg1.getTranslationZ();
        }

        public float getZ(View arg1) {
            return arg1.getZ();
        }

        public boolean hasNestedScrollingParent(View arg1) {
            return arg1.hasNestedScrollingParent();
        }

        public boolean isImportantForAccessibility(View arg1) {
            return arg1.isImportantForAccessibility();
        }

        public boolean isNestedScrollingEnabled(View arg1) {
            return arg1.isNestedScrollingEnabled();
        }

        public void offsetLeftAndRight(View arg7, int arg8) {
            int v2;
            Rect v0 = ViewCompatApi21Impl.getEmptyTempRect();
            ViewParent v1 = arg7.getParent();
            if((v1 instanceof View)) {
                v0.set(v1.getLeft(), v1.getTop(), v1.getRight(), v1.getBottom());
                v2 = v0.intersects(arg7.getLeft(), arg7.getTop(), arg7.getRight(), arg7.getBottom()) ^ 1;
            }
            else {
                v2 = 0;
            }

            super.offsetLeftAndRight(arg7, arg8);
            if(v2 != 0 && (v0.intersect(arg7.getLeft(), arg7.getTop(), arg7.getRight(), arg7.getBottom()))) {
                ((View)v1).invalidate(v0);
            }
        }

        public void offsetTopAndBottom(View arg7, int arg8) {
            int v2;
            Rect v0 = ViewCompatApi21Impl.getEmptyTempRect();
            ViewParent v1 = arg7.getParent();
            if((v1 instanceof View)) {
                v0.set(v1.getLeft(), v1.getTop(), v1.getRight(), v1.getBottom());
                v2 = v0.intersects(arg7.getLeft(), arg7.getTop(), arg7.getRight(), arg7.getBottom()) ^ 1;
            }
            else {
                v2 = 0;
            }

            super.offsetTopAndBottom(arg7, arg8);
            if(v2 != 0 && (v0.intersect(arg7.getLeft(), arg7.getTop(), arg7.getRight(), arg7.getBottom()))) {
                ((View)v1).invalidate(v0);
            }
        }

        public WindowInsetsCompat onApplyWindowInsets(View arg1, WindowInsetsCompat arg2) {
            Object v2 = WindowInsetsCompat.unwrap(arg2);
            WindowInsets v1 = arg1.onApplyWindowInsets(((WindowInsets)v2));
            if(v1 != v2) {
                WindowInsets v2_1 = new WindowInsets(v1);
            }

            return WindowInsetsCompat.wrap(v2);
        }

        public void requestApplyInsets(View arg1) {
            arg1.requestApplyInsets();
        }

        public void setBackgroundTintList(View arg2, ColorStateList arg3) {
            arg2.setBackgroundTintList(arg3);
            if(Build$VERSION.SDK_INT == 21) {
                Drawable v3 = arg2.getBackground();
                int v0 = arg2.getBackgroundTintList() == null || arg2.getBackgroundTintMode() == null ? 0 : 1;
                if(v3 == null) {
                    return;
                }

                if(v0 == 0) {
                    return;
                }

                if(v3.isStateful()) {
                    v3.setState(arg2.getDrawableState());
                }

                arg2.setBackground(v3);
            }
        }

        public void setBackgroundTintMode(View arg2, PorterDuff$Mode arg3) {
            arg2.setBackgroundTintMode(arg3);
            if(Build$VERSION.SDK_INT == 21) {
                Drawable v3 = arg2.getBackground();
                int v0 = arg2.getBackgroundTintList() == null || arg2.getBackgroundTintMode() == null ? 0 : 1;
                if(v3 == null) {
                    return;
                }

                if(v0 == 0) {
                    return;
                }

                if(v3.isStateful()) {
                    v3.setState(arg2.getDrawableState());
                }

                arg2.setBackground(v3);
            }
        }

        public void setElevation(View arg1, float arg2) {
            arg1.setElevation(arg2);
        }

        public void setNestedScrollingEnabled(View arg1, boolean arg2) {
            arg1.setNestedScrollingEnabled(arg2);
        }

        public void setOnApplyWindowInsetsListener(View arg2, OnApplyWindowInsetsListener arg3) {
            if(arg3 == null) {
                arg2.setOnApplyWindowInsetsListener(null);
                return;
            }

            arg2.setOnApplyWindowInsetsListener(new View$OnApplyWindowInsetsListener(arg3) {
                public WindowInsets onApplyWindowInsets(View arg2, WindowInsets arg3) {
                    return WindowInsetsCompat.unwrap(this.val$listener.onApplyWindowInsets(arg2, WindowInsetsCompat.wrap(arg3)));
                }
            });
        }

        public void setTransitionName(View arg1, String arg2) {
            arg1.setTransitionName(arg2);
        }

        public void setTranslationZ(View arg1, float arg2) {
            arg1.setTranslationZ(arg2);
        }

        public void setZ(View arg1, float arg2) {
            arg1.setZ(arg2);
        }

        public boolean startNestedScroll(View arg1, int arg2) {
            return arg1.startNestedScroll(arg2);
        }

        public void stopNestedScroll(View arg1) {
            arg1.stopNestedScroll();
        }
    }

    @RequiresApi(value=23) class ViewCompatApi23Impl extends ViewCompatApi21Impl {
        ViewCompatApi23Impl() {
            super();
        }

        public int getScrollIndicators(View arg1) {
            return arg1.getScrollIndicators();
        }

        public void offsetLeftAndRight(View arg1, int arg2) {
            arg1.offsetLeftAndRight(arg2);
        }

        public void offsetTopAndBottom(View arg1, int arg2) {
            arg1.offsetTopAndBottom(arg2);
        }

        public void setScrollIndicators(View arg1, int arg2) {
            arg1.setScrollIndicators(arg2);
        }

        public void setScrollIndicators(View arg1, int arg2, int arg3) {
            arg1.setScrollIndicators(arg2, arg3);
        }
    }

    @RequiresApi(value=24) class ViewCompatApi24Impl extends ViewCompatApi23Impl {
        ViewCompatApi24Impl() {
            super();
        }

        public void cancelDragAndDrop(View arg1) {
            arg1.cancelDragAndDrop();
        }

        public void dispatchFinishTemporaryDetach(View arg1) {
            arg1.dispatchFinishTemporaryDetach();
        }

        public void dispatchStartTemporaryDetach(View arg1) {
            arg1.dispatchStartTemporaryDetach();
        }

        public void setPointerIcon(View arg1, PointerIconCompat arg2) {
            Object v2;
            if(arg2 != null) {
                v2 = arg2.getPointerIcon();
            }
            else {
                PointerIcon v2_1 = null;
            }

            arg1.setPointerIcon(((PointerIcon)v2));
        }

        public boolean startDragAndDrop(View arg1, ClipData arg2, View$DragShadowBuilder arg3, Object arg4, int arg5) {
            return arg1.startDragAndDrop(arg2, arg3, arg4, arg5);
        }

        public void updateDragShadow(View arg1, View$DragShadowBuilder arg2) {
            arg1.updateDragShadow(arg2);
        }
    }

    @RequiresApi(value=26) class ViewCompatApi26Impl extends ViewCompatApi24Impl {
        ViewCompatApi26Impl() {
            super();
        }

        public void addKeyboardNavigationClusters(@NonNull View arg1, @NonNull Collection arg2, int arg3) {
            arg1.addKeyboardNavigationClusters(arg2, arg3);
        }

        public int getNextClusterForwardId(@NonNull View arg1) {
            return arg1.getNextClusterForwardId();
        }

        public boolean hasExplicitFocusable(@NonNull View arg1) {
            return arg1.hasExplicitFocusable();
        }

        public boolean isFocusedByDefault(@NonNull View arg1) {
            return arg1.isFocusedByDefault();
        }

        public boolean isKeyboardNavigationCluster(@NonNull View arg1) {
            return arg1.isKeyboardNavigationCluster();
        }

        public View keyboardNavigationClusterSearch(@NonNull View arg1, View arg2, int arg3) {
            return arg1.keyboardNavigationClusterSearch(arg2, arg3);
        }

        public boolean restoreDefaultFocus(@NonNull View arg1) {
            return arg1.restoreDefaultFocus();
        }

        public void setFocusedByDefault(@NonNull View arg1, boolean arg2) {
            arg1.setFocusedByDefault(arg2);
        }

        public void setKeyboardNavigationCluster(@NonNull View arg1, boolean arg2) {
            arg1.setKeyboardNavigationCluster(arg2);
        }

        public void setNextClusterForwardId(@NonNull View arg1, int arg2) {
            arg1.setNextClusterForwardId(arg2);
        }

        public void setTooltipText(View arg1, CharSequence arg2) {
            arg1.setTooltipText(arg2);
        }
    }

    class ViewCompatBaseImpl {
        private Method mDispatchFinishTemporaryDetach;
        private Method mDispatchStartTemporaryDetach;
        private boolean mTempDetachBound;
        WeakHashMap mViewPropertyAnimatorCompatMap;
        static boolean sAccessibilityDelegateCheckFailed = false;
        static Field sAccessibilityDelegateField;
        private static Method sChildrenDrawingOrderMethod;
        private static Field sMinHeightField;
        private static boolean sMinHeightFieldFetched;
        private static Field sMinWidthField;
        private static boolean sMinWidthFieldFetched;
        private static WeakHashMap sTransitionNameMap;

        static {
        }

        ViewCompatBaseImpl() {
            super();
            this.mViewPropertyAnimatorCompatMap = null;
        }

        public void addKeyboardNavigationClusters(@NonNull View arg1, @NonNull Collection arg2, int arg3) {
        }

        public ViewPropertyAnimatorCompat animate(View arg3) {
            if(this.mViewPropertyAnimatorCompatMap == null) {
                this.mViewPropertyAnimatorCompatMap = new WeakHashMap();
            }

            Object v0 = this.mViewPropertyAnimatorCompatMap.get(arg3);
            if(v0 == null) {
                ViewPropertyAnimatorCompat v0_1 = new ViewPropertyAnimatorCompat(arg3);
                this.mViewPropertyAnimatorCompatMap.put(arg3, v0_1);
            }

            return ((ViewPropertyAnimatorCompat)v0);
        }

        private void bindTempDetach() {
            try {
                this.mDispatchStartTemporaryDetach = View.class.getDeclaredMethod("dispatchStartTemporaryDetach");
                this.mDispatchFinishTemporaryDetach = View.class.getDeclaredMethod("dispatchFinishTemporaryDetach");
            }
            catch(NoSuchMethodException v0) {
                Log.e("ViewCompat", "Couldn\'t find method", ((Throwable)v0));
            }

            this.mTempDetachBound = true;
        }

        public void cancelDragAndDrop(View arg1) {
        }

        public WindowInsetsCompat dispatchApplyWindowInsets(View arg1, WindowInsetsCompat arg2) {
            return arg2;
        }

        public void dispatchFinishTemporaryDetach(View arg3) {
            if(!this.mTempDetachBound) {
                this.bindTempDetach();
            }

            if(this.mDispatchFinishTemporaryDetach != null) {
                try {
                    this.mDispatchFinishTemporaryDetach.invoke(arg3);
                }
                catch(Exception v3) {
                    Log.d("ViewCompat", "Error calling dispatchFinishTemporaryDetach", ((Throwable)v3));
                }
            }
            else {
                arg3.onFinishTemporaryDetach();
            }
        }

        public boolean dispatchNestedFling(View arg2, float arg3, float arg4, boolean arg5) {
            if((arg2 instanceof NestedScrollingChild)) {
                return ((NestedScrollingChild)arg2).dispatchNestedFling(arg3, arg4, arg5);
            }

            return 0;
        }

        public boolean dispatchNestedPreFling(View arg2, float arg3, float arg4) {
            if((arg2 instanceof NestedScrollingChild)) {
                return ((NestedScrollingChild)arg2).dispatchNestedPreFling(arg3, arg4);
            }

            return 0;
        }

        public boolean dispatchNestedPreScroll(View arg2, int arg3, int arg4, int[] arg5, int[] arg6) {
            if((arg2 instanceof NestedScrollingChild)) {
                return ((NestedScrollingChild)arg2).dispatchNestedPreScroll(arg3, arg4, arg5, arg6);
            }

            return 0;
        }

        public boolean dispatchNestedScroll(View arg8, int arg9, int arg10, int arg11, int arg12, int[] arg13) {
            if((arg8 instanceof NestedScrollingChild)) {
                return arg8.dispatchNestedScroll(arg9, arg10, arg11, arg12, arg13);
            }

            return 0;
        }

        public void dispatchStartTemporaryDetach(View arg3) {
            if(!this.mTempDetachBound) {
                this.bindTempDetach();
            }

            if(this.mDispatchStartTemporaryDetach != null) {
                try {
                    this.mDispatchStartTemporaryDetach.invoke(arg3);
                }
                catch(Exception v3) {
                    Log.d("ViewCompat", "Error calling dispatchStartTemporaryDetach", ((Throwable)v3));
                }
            }
            else {
                arg3.onStartTemporaryDetach();
            }
        }

        public int getAccessibilityLiveRegion(View arg1) {
            return 0;
        }

        public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View arg1) {
            return null;
        }

        public ColorStateList getBackgroundTintList(View arg2) {
            ColorStateList v2 = (arg2 instanceof TintableBackgroundView) ? ((TintableBackgroundView)arg2).getSupportBackgroundTintList() : null;
            return v2;
        }

        public PorterDuff$Mode getBackgroundTintMode(View arg2) {
            PorterDuff$Mode v2 = (arg2 instanceof TintableBackgroundView) ? ((TintableBackgroundView)arg2).getSupportBackgroundTintMode() : null;
            return v2;
        }

        public Rect getClipBounds(View arg1) {
            return null;
        }

        public Display getDisplay(View arg2) {
            if(this.isAttachedToWindow(arg2)) {
                return arg2.getContext().getSystemService("window").getDefaultDisplay();
            }

            return null;
        }

        public float getElevation(View arg1) {
            return 0;
        }

        public boolean getFitsSystemWindows(View arg1) {
            return 0;
        }

        long getFrameTime() {
            return ValueAnimator.getFrameDelay();
        }

        public int getImportantForAccessibility(View arg1) {
            return 0;
        }

        public int getLabelFor(View arg1) {
            return 0;
        }

        public int getLayoutDirection(View arg1) {
            return 0;
        }

        public int getMinimumHeight(View arg4) {
            if(!ViewCompatBaseImpl.sMinHeightFieldFetched) {
                try {
                    ViewCompatBaseImpl.sMinHeightField = View.class.getDeclaredField("mMinHeight");
                    ViewCompatBaseImpl.sMinHeightField.setAccessible(true);
                    goto label_9;
                }
                catch(NoSuchFieldException ) {
                label_9:
                    ViewCompatBaseImpl.sMinHeightFieldFetched = true;
                }
            }

            if(ViewCompatBaseImpl.sMinHeightField == null) {
                return 0;
            }

            try {
                return ViewCompatBaseImpl.sMinHeightField.get(arg4).intValue();
            }
            catch(Exception ) {
                return 0;
            }
        }

        public int getMinimumWidth(View arg4) {
            if(!ViewCompatBaseImpl.sMinWidthFieldFetched) {
                try {
                    ViewCompatBaseImpl.sMinWidthField = View.class.getDeclaredField("mMinWidth");
                    ViewCompatBaseImpl.sMinWidthField.setAccessible(true);
                    goto label_9;
                }
                catch(NoSuchFieldException ) {
                label_9:
                    ViewCompatBaseImpl.sMinWidthFieldFetched = true;
                }
            }

            if(ViewCompatBaseImpl.sMinWidthField == null) {
                return 0;
            }

            try {
                return ViewCompatBaseImpl.sMinWidthField.get(arg4).intValue();
            }
            catch(Exception ) {
                return 0;
            }
        }

        public int getNextClusterForwardId(@NonNull View arg1) {
            return -1;
        }

        public int getPaddingEnd(View arg1) {
            return arg1.getPaddingRight();
        }

        public int getPaddingStart(View arg1) {
            return arg1.getPaddingLeft();
        }

        public ViewParent getParentForAccessibility(View arg1) {
            return arg1.getParent();
        }

        public int getScrollIndicators(View arg1) {
            return 0;
        }

        public String getTransitionName(View arg2) {
            if(ViewCompatBaseImpl.sTransitionNameMap == null) {
                return null;
            }

            return ViewCompatBaseImpl.sTransitionNameMap.get(arg2);
        }

        public float getTranslationZ(View arg1) {
            return 0;
        }

        public int getWindowSystemUiVisibility(View arg1) {
            return 0;
        }

        public float getZ(View arg2) {
            return this.getTranslationZ(arg2) + this.getElevation(arg2);
        }

        public boolean hasAccessibilityDelegate(View arg5) {
            boolean v1 = false;
            if(ViewCompatBaseImpl.sAccessibilityDelegateCheckFailed) {
                return 0;
            }

            if(ViewCompatBaseImpl.sAccessibilityDelegateField == null) {
                try {
                    ViewCompatBaseImpl.sAccessibilityDelegateField = View.class.getDeclaredField("mAccessibilityDelegate");
                    ViewCompatBaseImpl.sAccessibilityDelegateField.setAccessible(true);
                }
                catch(Throwable ) {
                    ViewCompatBaseImpl.sAccessibilityDelegateCheckFailed = true;
                    return 0;
                }
            }

            try {
                if(ViewCompatBaseImpl.sAccessibilityDelegateField.get(arg5) == null) {
                    return v1;
                }
            }
            catch(Throwable ) {
                ViewCompatBaseImpl.sAccessibilityDelegateCheckFailed = true;
                return 0;
            }

            return true;
        }

        public boolean hasExplicitFocusable(@NonNull View arg1) {
            return arg1.hasFocusable();
        }

        public boolean hasNestedScrollingParent(View arg2) {
            if((arg2 instanceof NestedScrollingChild)) {
                return ((NestedScrollingChild)arg2).hasNestedScrollingParent();
            }

            return 0;
        }

        public boolean hasOnClickListeners(View arg1) {
            return 0;
        }

        public boolean hasOverlappingRendering(View arg1) {
            return 1;
        }

        public boolean hasTransientState(View arg1) {
            return 0;
        }

        public boolean isAttachedToWindow(View arg1) {
            boolean v1 = arg1.getWindowToken() != null ? true : false;
            return v1;
        }

        public boolean isFocusedByDefault(@NonNull View arg1) {
            return 0;
        }

        public boolean isImportantForAccessibility(View arg1) {
            return 1;
        }

        public boolean isInLayout(View arg1) {
            return 0;
        }

        public boolean isKeyboardNavigationCluster(@NonNull View arg1) {
            return 0;
        }

        public boolean isLaidOut(View arg2) {
            boolean v2 = arg2.getWidth() <= 0 || arg2.getHeight() <= 0 ? false : true;
            return v2;
        }

        public boolean isLayoutDirectionResolved(View arg1) {
            return 0;
        }

        public boolean isNestedScrollingEnabled(View arg2) {
            if((arg2 instanceof NestedScrollingChild)) {
                return ((NestedScrollingChild)arg2).isNestedScrollingEnabled();
            }

            return 0;
        }

        public boolean isPaddingRelative(View arg1) {
            return 0;
        }

        public View keyboardNavigationClusterSearch(@NonNull View arg1, View arg2, int arg3) {
            return null;
        }

        public void offsetLeftAndRight(View arg1, int arg2) {
            arg1.offsetLeftAndRight(arg2);
            if(arg1.getVisibility() == 0) {
                ViewCompatBaseImpl.tickleInvalidationFlag(arg1);
                ViewParent v1 = arg1.getParent();
                if((v1 instanceof View)) {
                    ViewCompatBaseImpl.tickleInvalidationFlag(((View)v1));
                }
            }
        }

        public void offsetTopAndBottom(View arg1, int arg2) {
            arg1.offsetTopAndBottom(arg2);
            if(arg1.getVisibility() == 0) {
                ViewCompatBaseImpl.tickleInvalidationFlag(arg1);
                ViewParent v1 = arg1.getParent();
                if((v1 instanceof View)) {
                    ViewCompatBaseImpl.tickleInvalidationFlag(((View)v1));
                }
            }
        }

        public WindowInsetsCompat onApplyWindowInsets(View arg1, WindowInsetsCompat arg2) {
            return arg2;
        }

        public void onInitializeAccessibilityNodeInfo(View arg1, AccessibilityNodeInfoCompat arg2) {
            arg1.onInitializeAccessibilityNodeInfo(arg2.unwrap());
        }

        public boolean performAccessibilityAction(View arg1, int arg2, Bundle arg3) {
            return 0;
        }

        public void postInvalidateOnAnimation(View arg1) {
            arg1.postInvalidate();
        }

        public void postInvalidateOnAnimation(View arg1, int arg2, int arg3, int arg4, int arg5) {
            arg1.postInvalidate(arg2, arg3, arg4, arg5);
        }

        public void postOnAnimation(View arg3, Runnable arg4) {
            arg3.postDelayed(arg4, this.getFrameTime());
        }

        public void postOnAnimationDelayed(View arg5, Runnable arg6, long arg7) {
            arg5.postDelayed(arg6, this.getFrameTime() + arg7);
        }

        public void requestApplyInsets(View arg1) {
        }

        public boolean restoreDefaultFocus(@NonNull View arg1) {
            return arg1.requestFocus();
        }

        public void setAccessibilityDelegate(View arg1, @Nullable AccessibilityDelegateCompat arg2) {
            View$AccessibilityDelegate v2 = arg2 == null ? null : arg2.getBridge();
            arg1.setAccessibilityDelegate(v2);
        }

        public void setAccessibilityLiveRegion(View arg1, int arg2) {
        }

        public void setBackground(View arg1, Drawable arg2) {
            arg1.setBackgroundDrawable(arg2);
        }

        public void setBackgroundTintList(View arg2, ColorStateList arg3) {
            if((arg2 instanceof TintableBackgroundView)) {
                ((TintableBackgroundView)arg2).setSupportBackgroundTintList(arg3);
            }
        }

        public void setBackgroundTintMode(View arg2, PorterDuff$Mode arg3) {
            if((arg2 instanceof TintableBackgroundView)) {
                ((TintableBackgroundView)arg2).setSupportBackgroundTintMode(arg3);
            }
        }

        public void setChildrenDrawingOrderEnabled(ViewGroup arg7, boolean arg8) {
            if(ViewCompatBaseImpl.sChildrenDrawingOrderMethod != null) {
                goto label_18;
            }

            try {
                ViewCompatBaseImpl.sChildrenDrawingOrderMethod = ViewGroup.class.getDeclaredMethod("setChildrenDrawingOrderEnabled", Boolean.TYPE);
            }
            catch(NoSuchMethodException v0) {
                Log.e("ViewCompat", "Unable to find childrenDrawingOrderEnabled", ((Throwable)v0));
            }

            ViewCompatBaseImpl.sChildrenDrawingOrderMethod.setAccessible(true);
            try {
            label_18:
                ViewCompatBaseImpl.sChildrenDrawingOrderMethod.invoke(arg7, Boolean.valueOf(arg8));
            }
            catch(InvocationTargetException v7) {
                Log.e("ViewCompat", "Unable to invoke childrenDrawingOrderEnabled", ((Throwable)v7));
            }
            catch(IllegalArgumentException v7_1) {
                Log.e("ViewCompat", "Unable to invoke childrenDrawingOrderEnabled", ((Throwable)v7_1));
            }
            catch(IllegalAccessException v7_2) {
                Log.e("ViewCompat", "Unable to invoke childrenDrawingOrderEnabled", ((Throwable)v7_2));
            }
        }

        public void setClipBounds(View arg1, Rect arg2) {
        }

        public void setElevation(View arg1, float arg2) {
        }

        public void setFocusedByDefault(@NonNull View arg1, boolean arg2) {
        }

        public void setHasTransientState(View arg1, boolean arg2) {
        }

        public void setImportantForAccessibility(View arg1, int arg2) {
        }

        public void setKeyboardNavigationCluster(@NonNull View arg1, boolean arg2) {
        }

        public void setLabelFor(View arg1, int arg2) {
        }

        public void setLayerPaint(View arg2, Paint arg3) {
            arg2.setLayerType(arg2.getLayerType(), arg3);
            arg2.invalidate();
        }

        public void setLayoutDirection(View arg1, int arg2) {
        }

        public void setNestedScrollingEnabled(View arg2, boolean arg3) {
            if((arg2 instanceof NestedScrollingChild)) {
                ((NestedScrollingChild)arg2).setNestedScrollingEnabled(arg3);
            }
        }

        public void setNextClusterForwardId(@NonNull View arg1, int arg2) {
        }

        public void setOnApplyWindowInsetsListener(View arg1, OnApplyWindowInsetsListener arg2) {
        }

        public void setPaddingRelative(View arg1, int arg2, int arg3, int arg4, int arg5) {
            arg1.setPadding(arg2, arg3, arg4, arg5);
        }

        public void setPointerIcon(View arg1, PointerIconCompat arg2) {
        }

        public void setScrollIndicators(View arg1, int arg2) {
        }

        public void setScrollIndicators(View arg1, int arg2, int arg3) {
        }

        public void setTooltipText(View arg1, CharSequence arg2) {
        }

        public void setTransitionName(View arg2, String arg3) {
            if(ViewCompatBaseImpl.sTransitionNameMap == null) {
                ViewCompatBaseImpl.sTransitionNameMap = new WeakHashMap();
            }

            ViewCompatBaseImpl.sTransitionNameMap.put(arg2, arg3);
        }

        public void setTranslationZ(View arg1, float arg2) {
        }

        public void setZ(View arg1, float arg2) {
        }

        public boolean startDragAndDrop(View arg1, ClipData arg2, View$DragShadowBuilder arg3, Object arg4, int arg5) {
            return arg1.startDrag(arg2, arg3, arg4, arg5);
        }

        public boolean startNestedScroll(View arg2, int arg3) {
            if((arg2 instanceof NestedScrollingChild)) {
                return ((NestedScrollingChild)arg2).startNestedScroll(arg3);
            }

            return 0;
        }

        public void stopNestedScroll(View arg2) {
            if((arg2 instanceof NestedScrollingChild)) {
                ((NestedScrollingChild)arg2).stopNestedScroll();
            }
        }

        private static void tickleInvalidationFlag(View arg2) {
            float v0 = arg2.getTranslationY();
            arg2.setTranslationY(1f + v0);
            arg2.setTranslationY(v0);
        }

        public void updateDragShadow(View arg1, View$DragShadowBuilder arg2) {
        }
    }

    public static final int ACCESSIBILITY_LIVE_REGION_ASSERTIVE = 2;
    public static final int ACCESSIBILITY_LIVE_REGION_NONE = 0;
    public static final int ACCESSIBILITY_LIVE_REGION_POLITE = 1;
    static final ViewCompatBaseImpl IMPL = null;
    public static final int IMPORTANT_FOR_ACCESSIBILITY_AUTO = 0;
    public static final int IMPORTANT_FOR_ACCESSIBILITY_NO = 2;
    public static final int IMPORTANT_FOR_ACCESSIBILITY_NO_HIDE_DESCENDANTS = 4;
    public static final int IMPORTANT_FOR_ACCESSIBILITY_YES = 1;
    @Deprecated public static final int LAYER_TYPE_HARDWARE = 2;
    @Deprecated public static final int LAYER_TYPE_NONE = 0;
    @Deprecated public static final int LAYER_TYPE_SOFTWARE = 1;
    public static final int LAYOUT_DIRECTION_INHERIT = 2;
    public static final int LAYOUT_DIRECTION_LOCALE = 3;
    public static final int LAYOUT_DIRECTION_LTR = 0;
    public static final int LAYOUT_DIRECTION_RTL = 1;
    @Deprecated public static final int MEASURED_HEIGHT_STATE_SHIFT = 16;
    @Deprecated public static final int MEASURED_SIZE_MASK = 0xFFFFFF;
    @Deprecated public static final int MEASURED_STATE_MASK = 0xFF000000;
    @Deprecated public static final int MEASURED_STATE_TOO_SMALL = 0x1000000;
    @Deprecated public static final int OVER_SCROLL_ALWAYS = 0;
    @Deprecated public static final int OVER_SCROLL_IF_CONTENT_SCROLLS = 1;
    @Deprecated public static final int OVER_SCROLL_NEVER = 2;
    public static final int SCROLL_AXIS_HORIZONTAL = 1;
    public static final int SCROLL_AXIS_NONE = 0;
    public static final int SCROLL_AXIS_VERTICAL = 2;
    public static final int SCROLL_INDICATOR_BOTTOM = 2;
    public static final int SCROLL_INDICATOR_END = 0x20;
    public static final int SCROLL_INDICATOR_LEFT = 4;
    public static final int SCROLL_INDICATOR_RIGHT = 8;
    public static final int SCROLL_INDICATOR_START = 16;
    public static final int SCROLL_INDICATOR_TOP = 1;
    private static final String TAG = "ViewCompat";
    public static final int TYPE_NON_TOUCH = 1;
    public static final int TYPE_TOUCH;

    static {
        if(Build$VERSION.SDK_INT >= 26) {
            ViewCompat.IMPL = new ViewCompatApi26Impl();
        }
        else if(Build$VERSION.SDK_INT >= 24) {
            ViewCompat.IMPL = new ViewCompatApi24Impl();
        }
        else if(Build$VERSION.SDK_INT >= 23) {
            ViewCompat.IMPL = new ViewCompatApi23Impl();
        }
        else if(Build$VERSION.SDK_INT >= 21) {
            ViewCompat.IMPL = new ViewCompatApi21Impl();
        }
        else if(Build$VERSION.SDK_INT >= 19) {
            ViewCompat.IMPL = new ViewCompatApi19Impl();
        }
        else if(Build$VERSION.SDK_INT >= 18) {
            ViewCompat.IMPL = new ViewCompatApi18Impl();
        }
        else if(Build$VERSION.SDK_INT >= 17) {
            ViewCompat.IMPL = new ViewCompatApi17Impl();
        }
        else if(Build$VERSION.SDK_INT >= 16) {
            ViewCompat.IMPL = new ViewCompatApi16Impl();
        }
        else if(Build$VERSION.SDK_INT >= 15) {
            ViewCompat.IMPL = new ViewCompatApi15Impl();
        }
        else {
            ViewCompat.IMPL = new ViewCompatBaseImpl();
        }
    }

    protected ViewCompat() {
        super();
    }

    public static void addKeyboardNavigationClusters(@NonNull View arg1, @NonNull Collection arg2, int arg3) {
        ViewCompat.IMPL.addKeyboardNavigationClusters(arg1, arg2, arg3);
    }

    public static ViewPropertyAnimatorCompat animate(View arg1) {
        return ViewCompat.IMPL.animate(arg1);
    }

    @Deprecated public static boolean canScrollHorizontally(View arg0, int arg1) {
        return arg0.canScrollHorizontally(arg1);
    }

    @Deprecated public static boolean canScrollVertically(View arg0, int arg1) {
        return arg0.canScrollVertically(arg1);
    }

    public static void cancelDragAndDrop(View arg1) {
        ViewCompat.IMPL.cancelDragAndDrop(arg1);
    }

    @Deprecated public static int combineMeasuredStates(int arg0, int arg1) {
        return View.combineMeasuredStates(arg0, arg1);
    }

    public static WindowInsetsCompat dispatchApplyWindowInsets(View arg1, WindowInsetsCompat arg2) {
        return ViewCompat.IMPL.dispatchApplyWindowInsets(arg1, arg2);
    }

    public static void dispatchFinishTemporaryDetach(View arg1) {
        ViewCompat.IMPL.dispatchFinishTemporaryDetach(arg1);
    }

    public static boolean dispatchNestedFling(@NonNull View arg1, float arg2, float arg3, boolean arg4) {
        return ViewCompat.IMPL.dispatchNestedFling(arg1, arg2, arg3, arg4);
    }

    public static boolean dispatchNestedPreFling(@NonNull View arg1, float arg2, float arg3) {
        return ViewCompat.IMPL.dispatchNestedPreFling(arg1, arg2, arg3);
    }

    public static boolean dispatchNestedPreScroll(@NonNull View arg6, int arg7, int arg8, @Nullable int[] arg9, @Nullable int[] arg10) {
        return ViewCompat.IMPL.dispatchNestedPreScroll(arg6, arg7, arg8, arg9, arg10);
    }

    public static boolean dispatchNestedPreScroll(@NonNull View arg7, int arg8, int arg9, @Nullable int[] arg10, @Nullable int[] arg11, int arg12) {
        if((arg7 instanceof NestedScrollingChild2)) {
            return arg7.dispatchNestedPreScroll(arg8, arg9, arg10, arg11, arg12);
        }

        if(arg12 == 0) {
            return ViewCompat.IMPL.dispatchNestedPreScroll(arg7, arg8, arg9, arg10, arg11);
        }

        return 0;
    }

    public static boolean dispatchNestedScroll(@NonNull View arg7, int arg8, int arg9, int arg10, int arg11, @Nullable int[] arg12) {
        return ViewCompat.IMPL.dispatchNestedScroll(arg7, arg8, arg9, arg10, arg11, arg12);
    }

    public static boolean dispatchNestedScroll(@NonNull View arg8, int arg9, int arg10, int arg11, int arg12, @Nullable int[] arg13, int arg14) {
        if((arg8 instanceof NestedScrollingChild2)) {
            return arg8.dispatchNestedScroll(arg9, arg10, arg11, arg12, arg13, arg14);
        }

        if(arg14 == 0) {
            return ViewCompat.IMPL.dispatchNestedScroll(arg8, arg9, arg10, arg11, arg12, arg13);
        }

        return 0;
    }

    public static void dispatchStartTemporaryDetach(View arg1) {
        ViewCompat.IMPL.dispatchStartTemporaryDetach(arg1);
    }

    public static int getAccessibilityLiveRegion(View arg1) {
        return ViewCompat.IMPL.getAccessibilityLiveRegion(arg1);
    }

    public static AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View arg1) {
        return ViewCompat.IMPL.getAccessibilityNodeProvider(arg1);
    }

    @Deprecated public static float getAlpha(View arg0) {
        return arg0.getAlpha();
    }

    public static ColorStateList getBackgroundTintList(View arg1) {
        return ViewCompat.IMPL.getBackgroundTintList(arg1);
    }

    public static PorterDuff$Mode getBackgroundTintMode(View arg1) {
        return ViewCompat.IMPL.getBackgroundTintMode(arg1);
    }

    public static Rect getClipBounds(View arg1) {
        return ViewCompat.IMPL.getClipBounds(arg1);
    }

    public static Display getDisplay(@NonNull View arg1) {
        return ViewCompat.IMPL.getDisplay(arg1);
    }

    public static float getElevation(View arg1) {
        return ViewCompat.IMPL.getElevation(arg1);
    }

    public static boolean getFitsSystemWindows(View arg1) {
        return ViewCompat.IMPL.getFitsSystemWindows(arg1);
    }

    public static int getImportantForAccessibility(View arg1) {
        return ViewCompat.IMPL.getImportantForAccessibility(arg1);
    }

    public static int getLabelFor(View arg1) {
        return ViewCompat.IMPL.getLabelFor(arg1);
    }

    @Deprecated public static int getLayerType(View arg0) {
        return arg0.getLayerType();
    }

    public static int getLayoutDirection(View arg1) {
        return ViewCompat.IMPL.getLayoutDirection(arg1);
    }

    @Nullable @Deprecated public static Matrix getMatrix(View arg0) {
        return arg0.getMatrix();
    }

    @Deprecated public static int getMeasuredHeightAndState(View arg0) {
        return arg0.getMeasuredHeightAndState();
    }

    @Deprecated public static int getMeasuredState(View arg0) {
        return arg0.getMeasuredState();
    }

    @Deprecated public static int getMeasuredWidthAndState(View arg0) {
        return arg0.getMeasuredWidthAndState();
    }

    public static int getMinimumHeight(View arg1) {
        return ViewCompat.IMPL.getMinimumHeight(arg1);
    }

    public static int getMinimumWidth(View arg1) {
        return ViewCompat.IMPL.getMinimumWidth(arg1);
    }

    public static int getNextClusterForwardId(@NonNull View arg1) {
        return ViewCompat.IMPL.getNextClusterForwardId(arg1);
    }

    @Deprecated public static int getOverScrollMode(View arg0) {
        return arg0.getOverScrollMode();
    }

    public static int getPaddingEnd(View arg1) {
        return ViewCompat.IMPL.getPaddingEnd(arg1);
    }

    public static int getPaddingStart(View arg1) {
        return ViewCompat.IMPL.getPaddingStart(arg1);
    }

    public static ViewParent getParentForAccessibility(View arg1) {
        return ViewCompat.IMPL.getParentForAccessibility(arg1);
    }

    @Deprecated public static float getPivotX(View arg0) {
        return arg0.getPivotX();
    }

    @Deprecated public static float getPivotY(View arg0) {
        return arg0.getPivotY();
    }

    @Deprecated public static float getRotation(View arg0) {
        return arg0.getRotation();
    }

    @Deprecated public static float getRotationX(View arg0) {
        return arg0.getRotationX();
    }

    @Deprecated public static float getRotationY(View arg0) {
        return arg0.getRotationY();
    }

    @Deprecated public static float getScaleX(View arg0) {
        return arg0.getScaleX();
    }

    @Deprecated public static float getScaleY(View arg0) {
        return arg0.getScaleY();
    }

    public static int getScrollIndicators(@NonNull View arg1) {
        return ViewCompat.IMPL.getScrollIndicators(arg1);
    }

    public static String getTransitionName(View arg1) {
        return ViewCompat.IMPL.getTransitionName(arg1);
    }

    @Deprecated public static float getTranslationX(View arg0) {
        return arg0.getTranslationX();
    }

    @Deprecated public static float getTranslationY(View arg0) {
        return arg0.getTranslationY();
    }

    public static float getTranslationZ(View arg1) {
        return ViewCompat.IMPL.getTranslationZ(arg1);
    }

    public static int getWindowSystemUiVisibility(View arg1) {
        return ViewCompat.IMPL.getWindowSystemUiVisibility(arg1);
    }

    @Deprecated public static float getX(View arg0) {
        return arg0.getX();
    }

    @Deprecated public static float getY(View arg0) {
        return arg0.getY();
    }

    public static float getZ(View arg1) {
        return ViewCompat.IMPL.getZ(arg1);
    }

    public static boolean hasAccessibilityDelegate(View arg1) {
        return ViewCompat.IMPL.hasAccessibilityDelegate(arg1);
    }

    public static boolean hasExplicitFocusable(@NonNull View arg1) {
        return ViewCompat.IMPL.hasExplicitFocusable(arg1);
    }

    public static boolean hasNestedScrollingParent(@NonNull View arg1) {
        return ViewCompat.IMPL.hasNestedScrollingParent(arg1);
    }

    public static boolean hasNestedScrollingParent(@NonNull View arg1, int arg2) {
        if((arg1 instanceof NestedScrollingChild2)) {
            ((NestedScrollingChild2)arg1).hasNestedScrollingParent(arg2);
        }
        else if(arg2 == 0) {
            return ViewCompat.IMPL.hasNestedScrollingParent(arg1);
        }

        return 0;
    }

    public static boolean hasOnClickListeners(View arg1) {
        return ViewCompat.IMPL.hasOnClickListeners(arg1);
    }

    public static boolean hasOverlappingRendering(View arg1) {
        return ViewCompat.IMPL.hasOverlappingRendering(arg1);
    }

    public static boolean hasTransientState(View arg1) {
        return ViewCompat.IMPL.hasTransientState(arg1);
    }

    public static boolean isAttachedToWindow(View arg1) {
        return ViewCompat.IMPL.isAttachedToWindow(arg1);
    }

    public static boolean isFocusedByDefault(@NonNull View arg1) {
        return ViewCompat.IMPL.isFocusedByDefault(arg1);
    }

    public static boolean isImportantForAccessibility(View arg1) {
        return ViewCompat.IMPL.isImportantForAccessibility(arg1);
    }

    public static boolean isInLayout(View arg1) {
        return ViewCompat.IMPL.isInLayout(arg1);
    }

    public static boolean isKeyboardNavigationCluster(@NonNull View arg1) {
        return ViewCompat.IMPL.isKeyboardNavigationCluster(arg1);
    }

    public static boolean isLaidOut(View arg1) {
        return ViewCompat.IMPL.isLaidOut(arg1);
    }

    public static boolean isLayoutDirectionResolved(View arg1) {
        return ViewCompat.IMPL.isLayoutDirectionResolved(arg1);
    }

    public static boolean isNestedScrollingEnabled(@NonNull View arg1) {
        return ViewCompat.IMPL.isNestedScrollingEnabled(arg1);
    }

    @Deprecated public static boolean isOpaque(View arg0) {
        return arg0.isOpaque();
    }

    public static boolean isPaddingRelative(View arg1) {
        return ViewCompat.IMPL.isPaddingRelative(arg1);
    }

    @Deprecated public static void jumpDrawablesToCurrentState(View arg0) {
        arg0.jumpDrawablesToCurrentState();
    }

    public static View keyboardNavigationClusterSearch(@NonNull View arg1, View arg2, int arg3) {
        return ViewCompat.IMPL.keyboardNavigationClusterSearch(arg1, arg2, arg3);
    }

    public static void offsetLeftAndRight(View arg1, int arg2) {
        ViewCompat.IMPL.offsetLeftAndRight(arg1, arg2);
    }

    public static void offsetTopAndBottom(View arg1, int arg2) {
        ViewCompat.IMPL.offsetTopAndBottom(arg1, arg2);
    }

    public static WindowInsetsCompat onApplyWindowInsets(View arg1, WindowInsetsCompat arg2) {
        return ViewCompat.IMPL.onApplyWindowInsets(arg1, arg2);
    }

    @Deprecated public static void onInitializeAccessibilityEvent(View arg0, AccessibilityEvent arg1) {
        arg0.onInitializeAccessibilityEvent(arg1);
    }

    public static void onInitializeAccessibilityNodeInfo(View arg1, AccessibilityNodeInfoCompat arg2) {
        ViewCompat.IMPL.onInitializeAccessibilityNodeInfo(arg1, arg2);
    }

    @Deprecated public static void onPopulateAccessibilityEvent(View arg0, AccessibilityEvent arg1) {
        arg0.onPopulateAccessibilityEvent(arg1);
    }

    public static boolean performAccessibilityAction(View arg1, int arg2, Bundle arg3) {
        return ViewCompat.IMPL.performAccessibilityAction(arg1, arg2, arg3);
    }

    public static void postInvalidateOnAnimation(View arg1) {
        ViewCompat.IMPL.postInvalidateOnAnimation(arg1);
    }

    public static void postInvalidateOnAnimation(View arg6, int arg7, int arg8, int arg9, int arg10) {
        ViewCompat.IMPL.postInvalidateOnAnimation(arg6, arg7, arg8, arg9, arg10);
    }

    public static void postOnAnimation(View arg1, Runnable arg2) {
        ViewCompat.IMPL.postOnAnimation(arg1, arg2);
    }

    public static void postOnAnimationDelayed(View arg1, Runnable arg2, long arg3) {
        ViewCompat.IMPL.postOnAnimationDelayed(arg1, arg2, arg3);
    }

    public static void requestApplyInsets(View arg1) {
        ViewCompat.IMPL.requestApplyInsets(arg1);
    }

    @Deprecated public static int resolveSizeAndState(int arg0, int arg1, int arg2) {
        return View.resolveSizeAndState(arg0, arg1, arg2);
    }

    public static boolean restoreDefaultFocus(@NonNull View arg1) {
        return ViewCompat.IMPL.restoreDefaultFocus(arg1);
    }

    public static void setAccessibilityDelegate(View arg1, AccessibilityDelegateCompat arg2) {
        ViewCompat.IMPL.setAccessibilityDelegate(arg1, arg2);
    }

    public static void setAccessibilityLiveRegion(View arg1, int arg2) {
        ViewCompat.IMPL.setAccessibilityLiveRegion(arg1, arg2);
    }

    @Deprecated public static void setActivated(View arg0, boolean arg1) {
        arg0.setActivated(arg1);
    }

    @Deprecated public static void setAlpha(View arg0, @FloatRange(from=0, to=1) float arg1) {
        arg0.setAlpha(arg1);
    }

    public static void setBackground(View arg1, Drawable arg2) {
        ViewCompat.IMPL.setBackground(arg1, arg2);
    }

    public static void setBackgroundTintList(View arg1, ColorStateList arg2) {
        ViewCompat.IMPL.setBackgroundTintList(arg1, arg2);
    }

    public static void setBackgroundTintMode(View arg1, PorterDuff$Mode arg2) {
        ViewCompat.IMPL.setBackgroundTintMode(arg1, arg2);
    }

    public static void setChildrenDrawingOrderEnabled(ViewGroup arg1, boolean arg2) {
        ViewCompat.IMPL.setChildrenDrawingOrderEnabled(arg1, arg2);
    }

    public static void setClipBounds(View arg1, Rect arg2) {
        ViewCompat.IMPL.setClipBounds(arg1, arg2);
    }

    public static void setElevation(View arg1, float arg2) {
        ViewCompat.IMPL.setElevation(arg1, arg2);
    }

    @Deprecated public static void setFitsSystemWindows(View arg0, boolean arg1) {
        arg0.setFitsSystemWindows(arg1);
    }

    public static void setFocusedByDefault(@NonNull View arg1, boolean arg2) {
        ViewCompat.IMPL.setFocusedByDefault(arg1, arg2);
    }

    public static void setHasTransientState(View arg1, boolean arg2) {
        ViewCompat.IMPL.setHasTransientState(arg1, arg2);
    }

    public static void setImportantForAccessibility(View arg1, int arg2) {
        ViewCompat.IMPL.setImportantForAccessibility(arg1, arg2);
    }

    public static void setKeyboardNavigationCluster(@NonNull View arg1, boolean arg2) {
        ViewCompat.IMPL.setKeyboardNavigationCluster(arg1, arg2);
    }

    public static void setLabelFor(View arg1, @IdRes int arg2) {
        ViewCompat.IMPL.setLabelFor(arg1, arg2);
    }

    public static void setLayerPaint(View arg1, Paint arg2) {
        ViewCompat.IMPL.setLayerPaint(arg1, arg2);
    }

    @Deprecated public static void setLayerType(View arg0, int arg1, Paint arg2) {
        arg0.setLayerType(arg1, arg2);
    }

    public static void setLayoutDirection(View arg1, int arg2) {
        ViewCompat.IMPL.setLayoutDirection(arg1, arg2);
    }

    public static void setNestedScrollingEnabled(@NonNull View arg1, boolean arg2) {
        ViewCompat.IMPL.setNestedScrollingEnabled(arg1, arg2);
    }

    public static void setNextClusterForwardId(@NonNull View arg1, int arg2) {
        ViewCompat.IMPL.setNextClusterForwardId(arg1, arg2);
    }

    public static void setOnApplyWindowInsetsListener(View arg1, OnApplyWindowInsetsListener arg2) {
        ViewCompat.IMPL.setOnApplyWindowInsetsListener(arg1, arg2);
    }

    @Deprecated public static void setOverScrollMode(View arg0, int arg1) {
        arg0.setOverScrollMode(arg1);
    }

    public static void setPaddingRelative(View arg6, int arg7, int arg8, int arg9, int arg10) {
        ViewCompat.IMPL.setPaddingRelative(arg6, arg7, arg8, arg9, arg10);
    }

    @Deprecated public static void setPivotX(View arg0, float arg1) {
        arg0.setPivotX(arg1);
    }

    @Deprecated public static void setPivotY(View arg0, float arg1) {
        arg0.setPivotY(arg1);
    }

    public static void setPointerIcon(@NonNull View arg1, PointerIconCompat arg2) {
        ViewCompat.IMPL.setPointerIcon(arg1, arg2);
    }

    @Deprecated public static void setRotation(View arg0, float arg1) {
        arg0.setRotation(arg1);
    }

    @Deprecated public static void setRotationX(View arg0, float arg1) {
        arg0.setRotationX(arg1);
    }

    @Deprecated public static void setRotationY(View arg0, float arg1) {
        arg0.setRotationY(arg1);
    }

    @Deprecated public static void setSaveFromParentEnabled(View arg0, boolean arg1) {
        arg0.setSaveFromParentEnabled(arg1);
    }

    @Deprecated public static void setScaleX(View arg0, float arg1) {
        arg0.setScaleX(arg1);
    }

    @Deprecated public static void setScaleY(View arg0, float arg1) {
        arg0.setScaleY(arg1);
    }

    public static void setScrollIndicators(@NonNull View arg1, int arg2) {
        ViewCompat.IMPL.setScrollIndicators(arg1, arg2);
    }

    public static void setScrollIndicators(@NonNull View arg1, int arg2, int arg3) {
        ViewCompat.IMPL.setScrollIndicators(arg1, arg2, arg3);
    }

    public static void setTooltipText(@NonNull View arg1, @Nullable CharSequence arg2) {
        ViewCompat.IMPL.setTooltipText(arg1, arg2);
    }

    public static void setTransitionName(View arg1, String arg2) {
        ViewCompat.IMPL.setTransitionName(arg1, arg2);
    }

    @Deprecated public static void setTranslationX(View arg0, float arg1) {
        arg0.setTranslationX(arg1);
    }

    @Deprecated public static void setTranslationY(View arg0, float arg1) {
        arg0.setTranslationY(arg1);
    }

    public static void setTranslationZ(View arg1, float arg2) {
        ViewCompat.IMPL.setTranslationZ(arg1, arg2);
    }

    @Deprecated public static void setX(View arg0, float arg1) {
        arg0.setX(arg1);
    }

    @Deprecated public static void setY(View arg0, float arg1) {
        arg0.setY(arg1);
    }

    public static void setZ(View arg1, float arg2) {
        ViewCompat.IMPL.setZ(arg1, arg2);
    }

    public static boolean startDragAndDrop(View arg6, ClipData arg7, View$DragShadowBuilder arg8, Object arg9, int arg10) {
        return ViewCompat.IMPL.startDragAndDrop(arg6, arg7, arg8, arg9, arg10);
    }

    public static boolean startNestedScroll(@NonNull View arg1, int arg2) {
        return ViewCompat.IMPL.startNestedScroll(arg1, arg2);
    }

    public static boolean startNestedScroll(@NonNull View arg1, int arg2, int arg3) {
        if((arg1 instanceof NestedScrollingChild2)) {
            return ((NestedScrollingChild2)arg1).startNestedScroll(arg2, arg3);
        }

        if(arg3 == 0) {
            return ViewCompat.IMPL.startNestedScroll(arg1, arg2);
        }

        return 0;
    }

    public static void stopNestedScroll(@NonNull View arg1) {
        ViewCompat.IMPL.stopNestedScroll(arg1);
    }

    public static void stopNestedScroll(@NonNull View arg1, int arg2) {
        if((arg1 instanceof NestedScrollingChild2)) {
            ((NestedScrollingChild2)arg1).stopNestedScroll(arg2);
        }
        else if(arg2 == 0) {
            ViewCompat.IMPL.stopNestedScroll(arg1);
        }
    }

    public static void updateDragShadow(View arg1, View$DragShadowBuilder arg2) {
        ViewCompat.IMPL.updateDragShadow(arg1, arg2);
    }
}

