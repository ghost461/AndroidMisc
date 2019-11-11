package android.support.v7.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources$Theme;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable$ClassLoaderCreator;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NavUtils;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.v4.view.WindowInsetsCompat;
import android.support.v4.widget.PopupWindowCompat;
import android.support.v7.appcompat.R$attr;
import android.support.v7.appcompat.R$color;
import android.support.v7.appcompat.R$id;
import android.support.v7.appcompat.R$layout;
import android.support.v7.appcompat.R$style;
import android.support.v7.appcompat.R$styleable;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.view.ActionMode;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.view.StandaloneActionMode;
import android.support.v7.view.menu.ListMenuPresenter;
import android.support.v7.view.menu.MenuBuilder$Callback;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.ActionBarContextView;
import android.support.v7.widget.AppCompatDrawableManager;
import android.support.v7.widget.ContentFrameLayout$OnAttachListener;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.DecorContentParent;
import android.support.v7.widget.FitWindowsViewGroup$OnFitSystemWindowsListener;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.VectorEnabledTintResources;
import android.support.v7.widget.ViewStubCompat;
import android.support.v7.widget.ViewUtils;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.LayoutInflater$Factory2;
import android.view.LayoutInflater$Factory;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup$MarginLayoutParams;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window$Callback;
import android.view.Window;
import android.view.WindowManager$LayoutParams;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import org.xmlpull.v1.XmlPullParser;

@RequiresApi(value=14) class AppCompatDelegateImplV9 extends AppCompatDelegateImplBase implements Callback, LayoutInflater$Factory2 {
    class android.support.v7.app.AppCompatDelegateImplV9$1 implements Runnable {
        android.support.v7.app.AppCompatDelegateImplV9$1(AppCompatDelegateImplV9 arg1) {
            AppCompatDelegateImplV9.this = arg1;
            super();
        }

        public void run() {
            if((AppCompatDelegateImplV9.this.mInvalidatePanelMenuFeatures & 1) != 0) {
                AppCompatDelegateImplV9.this.doInvalidatePanelMenu(0);
            }

            if((AppCompatDelegateImplV9.this.mInvalidatePanelMenuFeatures & 0x1000) != 0) {
                AppCompatDelegateImplV9.this.doInvalidatePanelMenu(108);
            }

            AppCompatDelegateImplV9.this.mInvalidatePanelMenuPosted = false;
            AppCompatDelegateImplV9.this.mInvalidatePanelMenuFeatures = 0;
        }
    }

    final class ActionMenuPresenterCallback implements android.support.v7.view.menu.MenuPresenter$Callback {
        ActionMenuPresenterCallback(AppCompatDelegateImplV9 arg1) {
            AppCompatDelegateImplV9.this = arg1;
            super();
        }

        public void onCloseMenu(MenuBuilder arg1, boolean arg2) {
            AppCompatDelegateImplV9.this.checkCloseActionMenu(arg1);
        }

        public boolean onOpenSubMenu(MenuBuilder arg3) {
            Window$Callback v0 = AppCompatDelegateImplV9.this.getWindowCallback();
            if(v0 != null) {
                v0.onMenuOpened(108, ((Menu)arg3));
            }

            return 1;
        }
    }

    class ActionModeCallbackWrapperV9 implements android.support.v7.view.ActionMode$Callback {
        private android.support.v7.view.ActionMode$Callback mWrapped;

        public ActionModeCallbackWrapperV9(AppCompatDelegateImplV9 arg1, android.support.v7.view.ActionMode$Callback arg2) {
            AppCompatDelegateImplV9.this = arg1;
            super();
            this.mWrapped = arg2;
        }

        public boolean onActionItemClicked(ActionMode arg2, MenuItem arg3) {
            return this.mWrapped.onActionItemClicked(arg2, arg3);
        }

        public boolean onCreateActionMode(ActionMode arg2, Menu arg3) {
            return this.mWrapped.onCreateActionMode(arg2, arg3);
        }

        public void onDestroyActionMode(ActionMode arg3) {
            this.mWrapped.onDestroyActionMode(arg3);
            if(AppCompatDelegateImplV9.this.mActionModePopup != null) {
                AppCompatDelegateImplV9.this.mWindow.getDecorView().removeCallbacks(AppCompatDelegateImplV9.this.mShowActionModePopup);
            }

            if(AppCompatDelegateImplV9.this.mActionModeView != null) {
                AppCompatDelegateImplV9.this.endOnGoingFadeAnimation();
                AppCompatDelegateImplV9.this.mFadeAnim = ViewCompat.animate(AppCompatDelegateImplV9.this.mActionModeView).alpha(0f);
                AppCompatDelegateImplV9.this.mFadeAnim.setListener(new ViewPropertyAnimatorListenerAdapter() {
                    public void onAnimationEnd(View arg2) {
                        this.this$1.this$0.mActionModeView.setVisibility(8);
                        if(this.this$1.this$0.mActionModePopup != null) {
                            this.this$1.this$0.mActionModePopup.dismiss();
                        }
                        else if((this.this$1.this$0.mActionModeView.getParent() instanceof View)) {
                            ViewCompat.requestApplyInsets(this.this$1.this$0.mActionModeView.getParent());
                        }

                        this.this$1.this$0.mActionModeView.removeAllViews();
                        this.this$1.this$0.mFadeAnim.setListener(null);
                        this.this$1.this$0.mFadeAnim = null;
                    }
                });
            }

            if(AppCompatDelegateImplV9.this.mAppCompatCallback != null) {
                AppCompatDelegateImplV9.this.mAppCompatCallback.onSupportActionModeFinished(AppCompatDelegateImplV9.this.mActionMode);
            }

            AppCompatDelegateImplV9.this.mActionMode = null;
        }

        public boolean onPrepareActionMode(ActionMode arg2, Menu arg3) {
            return this.mWrapped.onPrepareActionMode(arg2, arg3);
        }
    }

    class ListMenuDecorView extends ContentFrameLayout {
        public ListMenuDecorView(AppCompatDelegateImplV9 arg1, Context arg2) {
            AppCompatDelegateImplV9.this = arg1;
            super(arg2);
        }

        public boolean dispatchKeyEvent(KeyEvent arg2) {
            boolean v2 = (AppCompatDelegateImplV9.this.dispatchKeyEvent(arg2)) || (super.dispatchKeyEvent(arg2)) ? true : false;
            return v2;
        }

        private boolean isOutOfBounds(int arg2, int arg3) {
            int v0 = -5;
            boolean v2 = arg2 < v0 || arg3 < v0 || arg2 > this.getWidth() + 5 || arg3 > this.getHeight() + 5 ? true : false;
            return v2;
        }

        public boolean onInterceptTouchEvent(MotionEvent arg3) {
            if(arg3.getAction() == 0 && (this.isOutOfBounds(((int)arg3.getX()), ((int)arg3.getY())))) {
                AppCompatDelegateImplV9.this.closePanel(0);
                return 1;
            }

            return super.onInterceptTouchEvent(arg3);
        }

        public void setBackgroundResource(int arg2) {
            this.setBackgroundDrawable(AppCompatResources.getDrawable(this.getContext(), arg2));
        }
    }

    public final class PanelFeatureState {
        class SavedState implements Parcelable {
            final class android.support.v7.app.AppCompatDelegateImplV9$PanelFeatureState$SavedState$1 implements Parcelable$ClassLoaderCreator {
                android.support.v7.app.AppCompatDelegateImplV9$PanelFeatureState$SavedState$1() {
                    super();
                }

                public SavedState createFromParcel(Parcel arg2) {
                    return SavedState.readFromParcel(arg2, null);
                }

                public SavedState createFromParcel(Parcel arg1, ClassLoader arg2) {
                    return SavedState.readFromParcel(arg1, arg2);
                }

                public Object createFromParcel(Parcel arg1) {
                    return this.createFromParcel(arg1);
                }

                public Object createFromParcel(Parcel arg1, ClassLoader arg2) {
                    return this.createFromParcel(arg1, arg2);
                }

                public SavedState[] newArray(int arg1) {
                    return new SavedState[arg1];
                }

                public Object[] newArray(int arg1) {
                    return this.newArray(arg1);
                }
            }

            public static final Parcelable$Creator CREATOR;
            int featureId;
            boolean isOpen;
            Bundle menuState;

            static {
                SavedState.CREATOR = new android.support.v7.app.AppCompatDelegateImplV9$PanelFeatureState$SavedState$1();
            }

            SavedState() {
                super();
            }

            public int describeContents() {
                return 0;
            }

            static SavedState readFromParcel(Parcel arg3, ClassLoader arg4) {
                SavedState v0 = new SavedState();
                v0.featureId = arg3.readInt();
                boolean v2 = true;
                if(arg3.readInt() == 1) {
                }
                else {
                    v2 = false;
                }

                v0.isOpen = v2;
                if(v0.isOpen) {
                    v0.menuState = arg3.readBundle(arg4);
                }

                return v0;
            }

            public void writeToParcel(Parcel arg1, int arg2) {
                arg1.writeInt(this.featureId);
                arg1.writeInt(this.isOpen);
                if(this.isOpen) {
                    arg1.writeBundle(this.menuState);
                }
            }
        }

        int background;
        View createdPanelView;
        ViewGroup decorView;
        int featureId;
        Bundle frozenActionViewState;
        Bundle frozenMenuState;
        int gravity;
        boolean isHandled;
        boolean isOpen;
        boolean isPrepared;
        ListMenuPresenter listMenuPresenter;
        Context listPresenterContext;
        MenuBuilder menu;
        public boolean qwertyMode;
        boolean refreshDecorView;
        boolean refreshMenuContent;
        View shownPanelView;
        boolean wasLastOpen;
        int windowAnimations;
        int x;
        int y;

        PanelFeatureState(int arg1) {
            super();
            this.featureId = arg1;
            this.refreshDecorView = false;
        }

        void applyFrozenState() {
            if(this.menu != null && this.frozenMenuState != null) {
                this.menu.restorePresenterStates(this.frozenMenuState);
                this.frozenMenuState = null;
            }
        }

        public void clearMenuPresenters() {
            if(this.menu != null) {
                this.menu.removeMenuPresenter(this.listMenuPresenter);
            }

            this.listMenuPresenter = null;
        }

        MenuView getListMenuView(android.support.v7.view.menu.MenuPresenter$Callback arg4) {
            if(this.menu == null) {
                return null;
            }

            if(this.listMenuPresenter == null) {
                this.listMenuPresenter = new ListMenuPresenter(this.listPresenterContext, layout.abc_list_menu_item_layout);
                this.listMenuPresenter.setCallback(arg4);
                this.menu.addMenuPresenter(this.listMenuPresenter);
            }

            return this.listMenuPresenter.getMenuView(this.decorView);
        }

        public boolean hasPanelItems() {
            boolean v1 = false;
            if(this.shownPanelView == null) {
                return 0;
            }

            if(this.createdPanelView != null) {
                return 1;
            }

            if(this.listMenuPresenter.getAdapter().getCount() > 0) {
                v1 = true;
            }

            return v1;
        }

        void onRestoreInstanceState(Parcelable arg2) {
            this.featureId = ((SavedState)arg2).featureId;
            this.wasLastOpen = ((SavedState)arg2).isOpen;
            this.frozenMenuState = ((SavedState)arg2).menuState;
            this.shownPanelView = null;
            this.decorView = null;
        }

        Parcelable onSaveInstanceState() {
            SavedState v0 = new SavedState();
            v0.featureId = this.featureId;
            v0.isOpen = this.isOpen;
            if(this.menu != null) {
                v0.menuState = new Bundle();
                this.menu.savePresenterStates(v0.menuState);
            }

            return ((Parcelable)v0);
        }

        void setMenu(MenuBuilder arg3) {
            if(arg3 == this.menu) {
                return;
            }

            if(this.menu != null) {
                this.menu.removeMenuPresenter(this.listMenuPresenter);
            }

            this.menu = arg3;
            if(arg3 != null && this.listMenuPresenter != null) {
                arg3.addMenuPresenter(this.listMenuPresenter);
            }
        }

        void setStyle(Context arg5) {
            TypedValue v0 = new TypedValue();
            Resources$Theme v1 = arg5.getResources().newTheme();
            v1.setTo(arg5.getTheme());
            v1.resolveAttribute(attr.actionBarPopupTheme, v0, true);
            if(v0.resourceId != 0) {
                v1.applyStyle(v0.resourceId, true);
            }

            v1.resolveAttribute(attr.panelMenuListTheme, v0, true);
            if(v0.resourceId != 0) {
                v1.applyStyle(v0.resourceId, true);
            }
            else {
                v1.applyStyle(style.Theme_AppCompat_CompactMenu, true);
            }

            ContextThemeWrapper v0_1 = new ContextThemeWrapper(arg5, 0);
            ((Context)v0_1).getTheme().setTo(v1);
            this.listPresenterContext = ((Context)v0_1);
            TypedArray v5 = ((Context)v0_1).obtainStyledAttributes(styleable.AppCompatTheme);
            this.background = v5.getResourceId(styleable.AppCompatTheme_panelBackground, 0);
            this.windowAnimations = v5.getResourceId(styleable.AppCompatTheme_android_windowAnimationStyle, 0);
            v5.recycle();
        }
    }

    final class PanelMenuPresenterCallback implements android.support.v7.view.menu.MenuPresenter$Callback {
        PanelMenuPresenterCallback(AppCompatDelegateImplV9 arg1) {
            AppCompatDelegateImplV9.this = arg1;
            super();
        }

        public void onCloseMenu(MenuBuilder arg5, boolean arg6) {
            MenuBuilder v0 = arg5.getRootMenu();
            int v2 = v0 != arg5 ? 1 : 0;
            AppCompatDelegateImplV9 v3 = AppCompatDelegateImplV9.this;
            if(v2 != 0) {
                arg5 = v0;
            }

            PanelFeatureState v5 = v3.findMenuPanel(((Menu)arg5));
            if(v5 != null) {
                if(v2 != 0) {
                    AppCompatDelegateImplV9.this.callOnPanelClosed(v5.featureId, v5, ((Menu)v0));
                    AppCompatDelegateImplV9.this.closePanel(v5, true);
                }
                else {
                    AppCompatDelegateImplV9.this.closePanel(v5, arg6);
                }
            }
        }

        public boolean onOpenSubMenu(MenuBuilder arg3) {
            if(arg3 == null && (AppCompatDelegateImplV9.this.mHasActionBar)) {
                Window$Callback v0 = AppCompatDelegateImplV9.this.getWindowCallback();
                if(v0 != null && !AppCompatDelegateImplV9.this.isDestroyed()) {
                    v0.onMenuOpened(108, ((Menu)arg3));
                }
            }

            return 1;
        }
    }

    private static final boolean IS_PRE_LOLLIPOP;
    private ActionMenuPresenterCallback mActionMenuPresenterCallback;
    ActionMode mActionMode;
    PopupWindow mActionModePopup;
    ActionBarContextView mActionModeView;
    private AppCompatViewInflater mAppCompatViewInflater;
    private boolean mClosingActionMenu;
    private DecorContentParent mDecorContentParent;
    private boolean mEnableDefaultActionBarUp;
    ViewPropertyAnimatorCompat mFadeAnim;
    private boolean mFeatureIndeterminateProgress;
    private boolean mFeatureProgress;
    int mInvalidatePanelMenuFeatures;
    boolean mInvalidatePanelMenuPosted;
    private final Runnable mInvalidatePanelMenuRunnable;
    private boolean mLongPressBackDown;
    private PanelMenuPresenterCallback mPanelMenuPresenterCallback;
    private PanelFeatureState[] mPanels;
    private PanelFeatureState mPreparedPanel;
    Runnable mShowActionModePopup;
    private View mStatusGuard;
    private ViewGroup mSubDecor;
    private boolean mSubDecorInstalled;
    private Rect mTempRect1;
    private Rect mTempRect2;
    private TextView mTitleView;

    static {
        boolean v0 = Build$VERSION.SDK_INT < 21 ? true : false;
        AppCompatDelegateImplV9.IS_PRE_LOLLIPOP = v0;
    }

    AppCompatDelegateImplV9(Context arg1, Window arg2, AppCompatCallback arg3) {
        super(arg1, arg2, arg3);
        this.mFadeAnim = null;
        this.mInvalidatePanelMenuRunnable = new android.support.v7.app.AppCompatDelegateImplV9$1(this);
    }

    public void addContentView(View arg3, ViewGroup$LayoutParams arg4) {
        this.ensureSubDecor();
        this.mSubDecor.findViewById(0x1020002).addView(arg3, arg4);
        this.mOriginalWindowCallback.onContentChanged();
    }

    private void applyFixedSizeWindow() {
        View v0 = this.mSubDecor.findViewById(0x1020002);
        View v1 = this.mWindow.getDecorView();
        ((ContentFrameLayout)v0).setDecorPadding(v1.getPaddingLeft(), v1.getPaddingTop(), v1.getPaddingRight(), v1.getPaddingBottom());
        TypedArray v1_1 = this.mContext.obtainStyledAttributes(styleable.AppCompatTheme);
        v1_1.getValue(styleable.AppCompatTheme_windowMinWidthMajor, ((ContentFrameLayout)v0).getMinWidthMajor());
        v1_1.getValue(styleable.AppCompatTheme_windowMinWidthMinor, ((ContentFrameLayout)v0).getMinWidthMinor());
        if(v1_1.hasValue(styleable.AppCompatTheme_windowFixedWidthMajor)) {
            v1_1.getValue(styleable.AppCompatTheme_windowFixedWidthMajor, ((ContentFrameLayout)v0).getFixedWidthMajor());
        }

        if(v1_1.hasValue(styleable.AppCompatTheme_windowFixedWidthMinor)) {
            v1_1.getValue(styleable.AppCompatTheme_windowFixedWidthMinor, ((ContentFrameLayout)v0).getFixedWidthMinor());
        }

        if(v1_1.hasValue(styleable.AppCompatTheme_windowFixedHeightMajor)) {
            v1_1.getValue(styleable.AppCompatTheme_windowFixedHeightMajor, ((ContentFrameLayout)v0).getFixedHeightMajor());
        }

        if(v1_1.hasValue(styleable.AppCompatTheme_windowFixedHeightMinor)) {
            v1_1.getValue(styleable.AppCompatTheme_windowFixedHeightMinor, ((ContentFrameLayout)v0).getFixedHeightMinor());
        }

        v1_1.recycle();
        ((ContentFrameLayout)v0).requestLayout();
    }

    View callActivityOnCreateView(View arg1, String arg2, Context arg3, AttributeSet arg4) {
        if((this.mOriginalWindowCallback instanceof LayoutInflater$Factory)) {
            arg1 = this.mOriginalWindowCallback.onCreateView(arg2, arg3, arg4);
            if(arg1 != null) {
                return arg1;
            }
        }

        return null;
    }

    void callOnPanelClosed(int arg2, PanelFeatureState arg3, Menu arg4) {
        MenuBuilder v4;
        if(arg4 == null) {
            if(arg3 == null && arg2 >= 0 && arg2 < this.mPanels.length) {
                arg3 = this.mPanels[arg2];
            }

            if(arg3 == null) {
                goto label_10;
            }

            v4 = arg3.menu;
        }

    label_10:
        if(arg3 != null && !arg3.isOpen) {
            return;
        }

        if(!this.isDestroyed()) {
            this.mOriginalWindowCallback.onPanelClosed(arg2, ((Menu)v4));
        }
    }

    void checkCloseActionMenu(MenuBuilder arg3) {
        if(this.mClosingActionMenu) {
            return;
        }

        this.mClosingActionMenu = true;
        this.mDecorContentParent.dismissPopups();
        Window$Callback v0 = this.getWindowCallback();
        if(v0 != null && !this.isDestroyed()) {
            v0.onPanelClosed(108, ((Menu)arg3));
        }

        this.mClosingActionMenu = false;
    }

    void closePanel(PanelFeatureState arg4, boolean arg5) {
        if((arg5) && arg4.featureId == 0 && this.mDecorContentParent != null && (this.mDecorContentParent.isOverflowMenuShowing())) {
            this.checkCloseActionMenu(arg4.menu);
            return;
        }

        Object v0 = this.mContext.getSystemService("window");
        Menu v1 = null;
        if(v0 != null && (arg4.isOpen) && arg4.decorView != null) {
            ((WindowManager)v0).removeView(arg4.decorView);
            if(arg5) {
                this.callOnPanelClosed(arg4.featureId, arg4, v1);
            }
        }

        arg4.isPrepared = false;
        arg4.isHandled = false;
        arg4.isOpen = false;
        arg4.shownPanelView = ((View)v1);
        arg4.refreshDecorView = true;
        if(this.mPreparedPanel == arg4) {
            this.mPreparedPanel = ((PanelFeatureState)v1);
        }
    }

    void closePanel(int arg2) {
        this.closePanel(this.getPanelState(arg2, true), true);
    }

    private ViewGroup createSubDecor() {
        Context v1_1;
        View v0_2;
        TypedArray v0 = this.mContext.obtainStyledAttributes(styleable.AppCompatTheme);
        if(!v0.hasValue(styleable.AppCompatTheme_windowActionBar)) {
            v0.recycle();
            throw new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
        }

        if(v0.getBoolean(styleable.AppCompatTheme_windowNoTitle, false)) {
            this.requestWindowFeature(1);
        }
        else if(v0.getBoolean(styleable.AppCompatTheme_windowActionBar, false)) {
            this.requestWindowFeature(108);
        }

        int v4 = 109;
        if(v0.getBoolean(styleable.AppCompatTheme_windowActionBarOverlay, false)) {
            this.requestWindowFeature(v4);
        }

        if(v0.getBoolean(styleable.AppCompatTheme_windowActionModeOverlay, false)) {
            this.requestWindowFeature(10);
        }

        this.mIsFloating = v0.getBoolean(styleable.AppCompatTheme_android_windowIsFloating, false);
        v0.recycle();
        this.mWindow.getDecorView();
        LayoutInflater v0_1 = LayoutInflater.from(this.mContext);
        ViewGroup v5 = null;
        if(this.mWindowNoTitle) {
            v0_2 = this.mOverlayActionMode ? v0_1.inflate(layout.abc_screen_simple_overlay_action_mode, v5) : v0_1.inflate(layout.abc_screen_simple, v5);
            if(Build$VERSION.SDK_INT >= 21) {
                ViewCompat.setOnApplyWindowInsetsListener(v0_2, new OnApplyWindowInsetsListener() {
                    public WindowInsetsCompat onApplyWindowInsets(View arg5, WindowInsetsCompat arg6) {
                        int v0 = arg6.getSystemWindowInsetTop();
                        int v1 = AppCompatDelegateImplV9.this.updateStatusGuard(v0);
                        if(v0 != v1) {
                            arg6 = arg6.replaceSystemWindowInsets(arg6.getSystemWindowInsetLeft(), v1, arg6.getSystemWindowInsetRight(), arg6.getSystemWindowInsetBottom());
                        }

                        return ViewCompat.onApplyWindowInsets(arg5, arg6);
                    }
                });
                goto label_111;
            }

            v0_2.setOnFitSystemWindowsListener(new OnFitSystemWindowsListener() {
                public void onFitSystemWindows(Rect arg3) {
                    arg3.top = AppCompatDelegateImplV9.this.updateStatusGuard(arg3.top);
                }
            });
        }
        else if(this.mIsFloating) {
            v0_2 = v0_1.inflate(layout.abc_dialog_title_material, v5);
            this.mOverlayActionBar = false;
            this.mHasActionBar = false;
        }
        else if(this.mHasActionBar) {
            TypedValue v0_3 = new TypedValue();
            this.mContext.getTheme().resolveAttribute(attr.actionBarTheme, v0_3, true);
            if(v0_3.resourceId != 0) {
                ContextThemeWrapper v1 = new ContextThemeWrapper(this.mContext, v0_3.resourceId);
            }
            else {
                v1_1 = this.mContext;
            }

            v0_2 = LayoutInflater.from(v1_1).inflate(layout.abc_screen_toolbar, v5);
            this.mDecorContentParent = ((ViewGroup)v0_2).findViewById(id.decor_content_parent);
            this.mDecorContentParent.setWindowCallback(this.getWindowCallback());
            if(this.mOverlayActionBar) {
                this.mDecorContentParent.initFeature(v4);
            }

            if(this.mFeatureProgress) {
                this.mDecorContentParent.initFeature(2);
            }

            if(!this.mFeatureIndeterminateProgress) {
                goto label_111;
            }

            this.mDecorContentParent.initFeature(5);
        }
        else {
            v0_2 = ((View)v5);
        }

    label_111:
        if(v0_2 == null) {
            StringBuilder v1_2 = new StringBuilder();
            v1_2.append("AppCompat does not support the current theme features: { windowActionBar: ");
            v1_2.append(this.mHasActionBar);
            v1_2.append(", windowActionBarOverlay: ");
            v1_2.append(this.mOverlayActionBar);
            v1_2.append(", android:windowIsFloating: ");
            v1_2.append(this.mIsFloating);
            v1_2.append(", windowActionModeOverlay: ");
            v1_2.append(this.mOverlayActionMode);
            v1_2.append(", windowNoTitle: ");
            v1_2.append(this.mWindowNoTitle);
            v1_2.append(" }");
            throw new IllegalArgumentException(v1_2.toString());
        }

        if(this.mDecorContentParent == null) {
            this.mTitleView = ((ViewGroup)v0_2).findViewById(id.title);
        }

        ViewUtils.makeOptionalFitsSystemWindows(v0_2);
        View v1_3 = ((ViewGroup)v0_2).findViewById(id.action_bar_activity_content);
        v4 = 0x1020002;
        View v3 = this.mWindow.findViewById(v4);
        if(v3 != null) {
            while(((ViewGroup)v3).getChildCount() > 0) {
                View v6 = ((ViewGroup)v3).getChildAt(0);
                ((ViewGroup)v3).removeViewAt(0);
                ((ContentFrameLayout)v1_3).addView(v6);
            }

            ((ViewGroup)v3).setId(-1);
            ((ContentFrameLayout)v1_3).setId(v4);
            if((v3 instanceof FrameLayout)) {
                ((FrameLayout)v3).setForeground(((Drawable)v5));
            }
        }

        this.mWindow.setContentView(v0_2);
        ((ContentFrameLayout)v1_3).setAttachListener(new OnAttachListener() {
            public void onAttachedFromWindow() {
            }

            public void onDetachedFromWindow() {
                AppCompatDelegateImplV9.this.dismissPopups();
            }
        });
        return ((ViewGroup)v0_2);
    }

    public View createView(View arg12, String arg13, @NonNull Context arg14, @NonNull AttributeSet arg15) {
        boolean v7;
        if(this.mAppCompatViewInflater == null) {
            this.mAppCompatViewInflater = new AppCompatViewInflater();
        }

        boolean v1 = false;
        if(AppCompatDelegateImplV9.IS_PRE_LOLLIPOP) {
            if(!(arg15 instanceof XmlPullParser)) {
                v1 = this.shouldInheritContext(arg12);
            }
            else if(arg15.getDepth() > 1) {
                v1 = true;
            }

            v7 = v1;
        }
        else {
            v7 = false;
        }

        return this.mAppCompatViewInflater.createView(arg12, arg13, arg14, arg15, v7, AppCompatDelegateImplV9.IS_PRE_LOLLIPOP, true, VectorEnabledTintResources.shouldBeUsed());
    }

    void dismissPopups() {
        if(this.mDecorContentParent != null) {
            this.mDecorContentParent.dismissPopups();
        }

        if(this.mActionModePopup != null) {
            this.mWindow.getDecorView().removeCallbacks(this.mShowActionModePopup);
            if(this.mActionModePopup.isShowing()) {
                try {
                    this.mActionModePopup.dismiss();
                    goto label_15;
                }
                catch(IllegalArgumentException ) {
                label_15:
                    this.mActionModePopup = null;
                    goto label_17;
                }
            }

            goto label_15;
        }

    label_17:
        this.endOnGoingFadeAnimation();
        PanelFeatureState v0 = this.getPanelState(0, false);
        if(v0 != null && v0.menu != null) {
            v0.menu.close();
        }
    }

    boolean dispatchKeyEvent(KeyEvent arg4) {
        int v1 = 1;
        if(arg4.getKeyCode() == 82 && (this.mOriginalWindowCallback.dispatchKeyEvent(arg4))) {
            return 1;
        }

        int v0 = arg4.getKeyCode();
        if(arg4.getAction() == 0) {
        }
        else {
            v1 = 0;
        }

        boolean v4 = v1 != 0 ? this.onKeyDown(v0, arg4) : this.onKeyUp(v0, arg4);
        return v4;
    }

    void doInvalidatePanelMenu(int arg5) {
        PanelFeatureState v1 = this.getPanelState(arg5, true);
        if(v1.menu != null) {
            Bundle v2 = new Bundle();
            v1.menu.saveActionViewStates(v2);
            if(v2.size() > 0) {
                v1.frozenActionViewState = v2;
            }

            v1.menu.stopDispatchingItemsChanged();
            v1.menu.clear();
        }

        v1.refreshMenuContent = true;
        v1.refreshDecorView = true;
        if((arg5 == 108 || arg5 == 0) && this.mDecorContentParent != null) {
            PanelFeatureState v0 = this.getPanelState(0, false);
            if(v0 != null) {
                v0.isPrepared = false;
                this.preparePanel(v0, null);
            }
        }
    }

    void endOnGoingFadeAnimation() {
        if(this.mFadeAnim != null) {
            this.mFadeAnim.cancel();
        }
    }

    private void ensureSubDecor() {
        if(!this.mSubDecorInstalled) {
            this.mSubDecor = this.createSubDecor();
            CharSequence v0 = this.getTitle();
            if(!TextUtils.isEmpty(v0)) {
                this.onTitleChanged(v0);
            }

            this.applyFixedSizeWindow();
            this.onSubDecorInstalled(this.mSubDecor);
            this.mSubDecorInstalled = true;
            PanelFeatureState v0_1 = this.getPanelState(0, false);
            if(this.isDestroyed()) {
                return;
            }

            if(v0_1 != null && v0_1.menu != null) {
                return;
            }

            this.invalidatePanelMenu(108);
        }
    }

    PanelFeatureState findMenuPanel(Menu arg6) {
        PanelFeatureState[] v0 = this.mPanels;
        int v1 = 0;
        int v2 = v0 != null ? v0.length : 0;
        while(v1 < v2) {
            PanelFeatureState v3 = v0[v1];
            if(v3 != null && v3.menu == arg6) {
                return v3;
            }

            ++v1;
        }

        return null;
    }

    @Nullable public View findViewById(@IdRes int arg2) {
        this.ensureSubDecor();
        return this.mWindow.findViewById(arg2);
    }

    protected PanelFeatureState getPanelState(int arg4, boolean arg5) {
        PanelFeatureState[] v5 = this.mPanels;
        if(v5 == null || v5.length <= arg4) {
            PanelFeatureState[] v0 = new PanelFeatureState[arg4 + 1];
            if(v5 != null) {
                System.arraycopy(v5, 0, v0, 0, v5.length);
            }

            this.mPanels = v0;
            v5 = v0;
        }

        PanelFeatureState v0_1 = v5[arg4];
        if(v0_1 == null) {
            v0_1 = new PanelFeatureState(arg4);
            v5[arg4] = v0_1;
        }

        return v0_1;
    }

    ViewGroup getSubDecor() {
        return this.mSubDecor;
    }

    public boolean hasWindowFeature(int arg1) {
        switch(this.sanitizeWindowFeatureId(arg1)) {
            case 1: {
                goto label_14;
            }
            case 2: {
                goto label_12;
            }
            case 5: {
                goto label_10;
            }
            case 10: {
                goto label_8;
            }
            case 108: {
                goto label_6;
            }
            case 109: {
                goto label_4;
            }
        }

        return 0;
    label_4:
        return this.mOverlayActionBar;
    label_6:
        return this.mHasActionBar;
    label_8:
        return this.mOverlayActionMode;
    label_10:
        return this.mFeatureIndeterminateProgress;
    label_12:
        return this.mFeatureProgress;
    label_14:
        return this.mWindowNoTitle;
    }

    public void initWindowDecorActionBar() {
        this.ensureSubDecor();
        if(this.mHasActionBar) {
            if(this.mActionBar != null) {
            }
            else {
                if((this.mOriginalWindowCallback instanceof Activity)) {
                    this.mActionBar = new WindowDecorActionBar(this.mOriginalWindowCallback, this.mOverlayActionBar);
                }
                else if((this.mOriginalWindowCallback instanceof Dialog)) {
                    this.mActionBar = new WindowDecorActionBar(this.mOriginalWindowCallback);
                }

                if(this.mActionBar != null) {
                    this.mActionBar.setDefaultDisplayHomeAsUpEnabled(this.mEnableDefaultActionBarUp);
                }

                return;
            }
        }
    }

    private boolean initializePanelContent(PanelFeatureState arg4) {
        boolean v1 = true;
        if(arg4.createdPanelView != null) {
            arg4.shownPanelView = arg4.createdPanelView;
            return 1;
        }

        if(arg4.menu == null) {
            return 0;
        }

        if(this.mPanelMenuPresenterCallback == null) {
            this.mPanelMenuPresenterCallback = new PanelMenuPresenterCallback(this);
        }

        arg4.shownPanelView = arg4.getListMenuView(this.mPanelMenuPresenterCallback);
        if(arg4.shownPanelView != null) {
        }
        else {
            v1 = false;
        }

        return v1;
    }

    private boolean initializePanelDecor(PanelFeatureState arg3) {
        arg3.setStyle(this.getActionBarThemedContext());
        arg3.decorView = new ListMenuDecorView(this, arg3.listPresenterContext);
        arg3.gravity = 81;
        return 1;
    }

    private boolean initializePanelMenu(PanelFeatureState arg7) {
        ContextThemeWrapper v0_1;
        Context v0 = this.mContext;
        if((arg7.featureId == 0 || arg7.featureId == 108) && this.mDecorContentParent != null) {
            TypedValue v1 = new TypedValue();
            Resources$Theme v3 = v0.getTheme();
            v3.resolveAttribute(attr.actionBarTheme, v1, true);
            Resources$Theme v4 = null;
            if(v1.resourceId != 0) {
                v4 = v0.getResources().newTheme();
                v4.setTo(v3);
                v4.applyStyle(v1.resourceId, true);
                v4.resolveAttribute(attr.actionBarWidgetTheme, v1, true);
            }
            else {
                v3.resolveAttribute(attr.actionBarWidgetTheme, v1, true);
            }

            if(v1.resourceId != 0) {
                if(v4 == null) {
                    v4 = v0.getResources().newTheme();
                    v4.setTo(v3);
                }

                v4.applyStyle(v1.resourceId, true);
            }

            if(v4 == null) {
                goto label_42;
            }

            ContextThemeWrapper v1_1 = new ContextThemeWrapper(v0, 0);
            ((Context)v1_1).getTheme().setTo(v4);
            v0_1 = v1_1;
        }

    label_42:
        MenuBuilder v1_2 = new MenuBuilder(((Context)v0_1));
        v1_2.setCallback(((Callback)this));
        arg7.setMenu(v1_2);
        return 1;
    }

    public void installViewFactory() {
        LayoutInflater v0 = LayoutInflater.from(this.mContext);
        if(v0.getFactory() == null) {
            LayoutInflaterCompat.setFactory2(v0, ((LayoutInflater$Factory2)this));
        }
        else if(!(v0.getFactory2() instanceof AppCompatDelegateImplV9)) {
            Log.i("AppCompatDelegate", "The Activity\'s LayoutInflater already has a Factory installed so we can not install AppCompat\'s");
        }
    }

    public void invalidateOptionsMenu() {
        ActionBar v0 = this.getSupportActionBar();
        if(v0 != null && (v0.invalidateOptionsMenu())) {
            return;
        }

        this.invalidatePanelMenu(0);
    }

    private void invalidatePanelMenu(int arg3) {
        this.mInvalidatePanelMenuFeatures |= 1 << arg3;
        if(!this.mInvalidatePanelMenuPosted) {
            ViewCompat.postOnAnimation(this.mWindow.getDecorView(), this.mInvalidatePanelMenuRunnable);
            this.mInvalidatePanelMenuPosted = true;
        }
    }

    boolean onBackPressed() {
        if(this.mActionMode != null) {
            this.mActionMode.finish();
            return 1;
        }

        ActionBar v0 = this.getSupportActionBar();
        if(v0 != null && (v0.collapseActionView())) {
            return 1;
        }

        return 0;
    }

    public void onConfigurationChanged(Configuration arg2) {
        if((this.mHasActionBar) && (this.mSubDecorInstalled)) {
            ActionBar v0 = this.getSupportActionBar();
            if(v0 != null) {
                v0.onConfigurationChanged(arg2);
            }
        }

        AppCompatDrawableManager.get().onConfigurationChanged(this.mContext);
        this.applyDayNight();
    }

    public void onCreate(Bundle arg2) {
        if(((this.mOriginalWindowCallback instanceof Activity)) && NavUtils.getParentActivityName(this.mOriginalWindowCallback) != null) {
            ActionBar v2 = this.peekSupportActionBar();
            if(v2 == null) {
                this.mEnableDefaultActionBarUp = true;
            }
            else {
                v2.setDefaultDisplayHomeAsUpEnabled(true);
            }
        }
    }

    public final View onCreateView(View arg2, String arg3, Context arg4, AttributeSet arg5) {
        View v0 = this.callActivityOnCreateView(arg2, arg3, arg4, arg5);
        if(v0 != null) {
            return v0;
        }

        return this.createView(arg2, arg3, arg4, arg5);
    }

    public View onCreateView(String arg2, Context arg3, AttributeSet arg4) {
        return this.onCreateView(null, arg2, arg3, arg4);
    }

    public void onDestroy() {
        if(this.mInvalidatePanelMenuPosted) {
            this.mWindow.getDecorView().removeCallbacks(this.mInvalidatePanelMenuRunnable);
        }

        super.onDestroy();
        if(this.mActionBar != null) {
            this.mActionBar.onDestroy();
        }
    }

    boolean onKeyDown(int arg4, KeyEvent arg5) {
        boolean v1 = true;
        if(arg4 == 4) {
            if((arg5.getFlags() & 0x80) != 0) {
            }
            else {
                v1 = false;
            }

            this.mLongPressBackDown = v1;
        }
        else if(arg4 != 82) {
        }
        else {
            this.onKeyDownPanel(0, arg5);
            return 1;
        }

        if(Build$VERSION.SDK_INT < 11) {
            this.onKeyShortcut(arg4, arg5);
        }

        return 0;
    }

    private boolean onKeyDownPanel(int arg2, KeyEvent arg3) {
        if(arg3.getRepeatCount() == 0) {
            PanelFeatureState v2 = this.getPanelState(arg2, true);
            if(!v2.isOpen) {
                return this.preparePanel(v2, arg3);
            }
        }

        return 0;
    }

    boolean onKeyShortcut(int arg4, KeyEvent arg5) {
        ActionBar v0 = this.getSupportActionBar();
        if(v0 != null && (v0.onKeyShortcut(arg4, arg5))) {
            return 1;
        }

        if(this.mPreparedPanel != null && (this.performPanelShortcut(this.mPreparedPanel, arg5.getKeyCode(), arg5, 1))) {
            if(this.mPreparedPanel != null) {
                this.mPreparedPanel.isHandled = true;
            }

            return 1;
        }

        if(this.mPreparedPanel == null) {
            PanelFeatureState v4 = this.getPanelState(0, true);
            this.preparePanel(v4, arg5);
            boolean v5 = this.performPanelShortcut(v4, arg5.getKeyCode(), arg5, 1);
            v4.isPrepared = false;
            if(v5) {
                return 1;
            }
        }

        return 0;
    }

    boolean onKeyUp(int arg4, KeyEvent arg5) {
        if(arg4 == 4) {
            boolean v4 = this.mLongPressBackDown;
            this.mLongPressBackDown = false;
            PanelFeatureState v5 = this.getPanelState(0, false);
            if(v5 != null && (v5.isOpen)) {
                if(!v4) {
                    this.closePanel(v5, true);
                }

                return 1;
            }

            if(!this.onBackPressed()) {
                return 0;
            }

            return 1;
        }
        else if(arg4 != 82) {
        }
        else {
            this.onKeyUpPanel(0, arg5);
            return 1;
        }

        return 0;
    }

    private boolean onKeyUpPanel(int arg4, KeyEvent arg5) {
        boolean v4;
        if(this.mActionMode != null) {
            return 0;
        }

        PanelFeatureState v2 = this.getPanelState(arg4, true);
        if(arg4 != 0 || this.mDecorContentParent == null || !this.mDecorContentParent.canShowOverflowMenu() || (ViewConfiguration.get(this.mContext).hasPermanentMenuKey())) {
            if(!v2.isOpen) {
                if(v2.isHandled) {
                }
                else {
                    if(v2.isPrepared) {
                        if(v2.refreshMenuContent) {
                            v2.isPrepared = false;
                            v4 = this.preparePanel(v2, arg5);
                        }
                        else {
                            v4 = true;
                        }

                        if(!v4) {
                            goto label_46;
                        }

                        this.openPanel(v2, arg5);
                        v4 = true;
                    }
                    else {
                    label_46:
                        v4 = false;
                    }

                    goto label_50;
                }
            }

            v4 = v2.isOpen;
            this.closePanel(v2, true);
        }
        else if(this.mDecorContentParent.isOverflowMenuShowing()) {
            v4 = this.mDecorContentParent.hideOverflowMenu();
        }
        else if(this.isDestroyed()) {
            goto label_46;
        }
        else if(this.preparePanel(v2, arg5)) {
            v4 = this.mDecorContentParent.showOverflowMenu();
        }
        else {
            goto label_46;
        }

    label_50:
        if(v4) {
            Object v5 = this.mContext.getSystemService("audio");
            if(v5 != null) {
                ((AudioManager)v5).playSoundEffect(0);
            }
            else {
                Log.w("AppCompatDelegate", "Couldn\'t get audio manager");
            }
        }

        return v4;
    }

    public boolean onMenuItemSelected(MenuBuilder arg3, MenuItem arg4) {
        Window$Callback v0 = this.getWindowCallback();
        if(v0 != null && !this.isDestroyed()) {
            PanelFeatureState v3 = this.findMenuPanel(arg3.getRootMenu());
            if(v3 != null) {
                return v0.onMenuItemSelected(v3.featureId, arg4);
            }
        }

        return 0;
    }

    public void onMenuModeChange(MenuBuilder arg2) {
        this.reopenMenu(arg2, true);
    }

    boolean onMenuOpened(int arg1, Menu arg2) {
        if(arg1 == 108) {
            ActionBar v1 = this.getSupportActionBar();
            if(v1 != null) {
                v1.dispatchMenuVisibilityChanged(true);
            }

            return 1;
        }

        return 0;
    }

    void onPanelClosed(int arg2, Menu arg3) {
        if(arg2 == 108) {
            ActionBar v2 = this.getSupportActionBar();
            if(v2 != null) {
                v2.dispatchMenuVisibilityChanged(false);
            }
        }
        else if(arg2 == 0) {
            PanelFeatureState v2_1 = this.getPanelState(arg2, true);
            if(v2_1.isOpen) {
                this.closePanel(v2_1, false);
            }
        }
    }

    public void onPostCreate(Bundle arg1) {
        this.ensureSubDecor();
    }

    public void onPostResume() {
        ActionBar v0 = this.getSupportActionBar();
        if(v0 != null) {
            v0.setShowHideAnimationEnabled(true);
        }
    }

    public void onStop() {
        ActionBar v0 = this.getSupportActionBar();
        if(v0 != null) {
            v0.setShowHideAnimationEnabled(false);
        }
    }

    void onSubDecorInstalled(ViewGroup arg1) {
    }

    void onTitleChanged(CharSequence arg2) {
        if(this.mDecorContentParent != null) {
            this.mDecorContentParent.setWindowTitle(arg2);
        }
        else if(this.peekSupportActionBar() != null) {
            this.peekSupportActionBar().setWindowTitle(arg2);
        }
        else if(this.mTitleView != null) {
            this.mTitleView.setText(arg2);
        }
    }

    private void openPanel(PanelFeatureState arg14, KeyEvent arg15) {
        int v6;
        ViewGroup$LayoutParams v15;
        int v3;
        if(!arg14.isOpen) {
            if(this.isDestroyed()) {
            }
            else {
                if(arg14.featureId == 0) {
                    Context v0 = this.mContext;
                    v3 = (v0.getResources().getConfiguration().screenLayout & 15) == 4 ? 1 : 0;
                    int v0_1 = v0.getApplicationInfo().targetSdkVersion >= 11 ? 1 : 0;
                    if(v3 == 0) {
                        goto label_29;
                    }

                    if(v0_1 == 0) {
                        goto label_29;
                    }

                    return;
                }

            label_29:
                Window$Callback v0_2 = this.getWindowCallback();
                if(v0_2 != null && !v0_2.onMenuOpened(arg14.featureId, arg14.menu)) {
                    this.closePanel(arg14, true);
                    return;
                }

                Object v0_3 = this.mContext.getSystemService("window");
                if(v0_3 == null) {
                    return;
                }

                if(!this.preparePanel(arg14, arg15)) {
                    return;
                }

                v3 = -1;
                int v4 = -2;
                if(arg14.decorView == null || (arg14.refreshDecorView)) {
                    if(arg14.decorView == null) {
                        if((this.initializePanelDecor(arg14)) && arg14.decorView != null) {
                            goto label_75;
                        }

                        return;
                    }
                    else {
                        if(!arg14.refreshDecorView) {
                            goto label_75;
                        }

                        if(arg14.decorView.getChildCount() <= 0) {
                            goto label_75;
                        }

                        arg14.decorView.removeAllViews();
                    }

                label_75:
                    if(this.initializePanelContent(arg14)) {
                        if(!arg14.hasPanelItems()) {
                        }
                        else {
                            v15 = arg14.shownPanelView.getLayoutParams();
                            if(v15 == null) {
                                v15 = new ViewGroup$LayoutParams(v4, v4);
                            }

                            arg14.decorView.setBackgroundResource(arg14.background);
                            ViewParent v3_1 = arg14.shownPanelView.getParent();
                            if(v3_1 != null && ((v3_1 instanceof ViewGroup))) {
                                ((ViewGroup)v3_1).removeView(arg14.shownPanelView);
                            }

                            arg14.decorView.addView(arg14.shownPanelView, v15);
                            if(!arg14.shownPanelView.hasFocus()) {
                                arg14.shownPanelView.requestFocus();
                            }

                        label_103:
                            v6 = -2;
                            goto label_104;
                        }
                    }

                    return;
                }
                else if(arg14.createdPanelView != null) {
                    v15 = arg14.createdPanelView.getLayoutParams();
                    if(v15 == null) {
                        goto label_103;
                    }
                    else if(v15.width == v3) {
                        v6 = -1;
                    }
                    else {
                        goto label_103;
                    }
                }
                else {
                    goto label_103;
                }

            label_104:
                arg14.isHandled = false;
                WindowManager$LayoutParams v15_1 = new WindowManager$LayoutParams(v6, -2, arg14.x, arg14.y, 1002, 0x820000, -3);
                v15_1.gravity = arg14.gravity;
                v15_1.windowAnimations = arg14.windowAnimations;
                ((WindowManager)v0_3).addView(arg14.decorView, ((ViewGroup$LayoutParams)v15_1));
                arg14.isOpen = true;
                return;
            }
        }
    }

    private boolean performPanelShortcut(PanelFeatureState arg3, int arg4, KeyEvent arg5, int arg6) {
        boolean v1 = false;
        if(arg5.isSystem()) {
            return 0;
        }

        if(((arg3.isPrepared) || (this.preparePanel(arg3, arg5))) && arg3.menu != null) {
            v1 = arg3.menu.performShortcut(arg4, arg5, arg6);
        }

        if((v1) && (arg6 & 1) == 0 && this.mDecorContentParent == null) {
            this.closePanel(arg3, true);
        }

        return v1;
    }

    private boolean preparePanel(PanelFeatureState arg9, KeyEvent arg10) {
        if(this.isDestroyed()) {
            return 0;
        }

        if(arg9.isPrepared) {
            return 1;
        }

        if(this.mPreparedPanel != null && this.mPreparedPanel != arg9) {
            this.closePanel(this.mPreparedPanel, false);
        }

        Window$Callback v0 = this.getWindowCallback();
        if(v0 != null) {
            arg9.createdPanelView = v0.onCreatePanelView(arg9.featureId);
        }

        int v3 = arg9.featureId == 0 || arg9.featureId == 108 ? 1 : 0;
        if(v3 != 0 && this.mDecorContentParent != null) {
            this.mDecorContentParent.setMenuPrepared();
        }

        if(arg9.createdPanelView == null && (v3 == 0 || !(this.peekSupportActionBar() instanceof ToolbarActionBar))) {
            MenuBuilder v5 = null;
            if(arg9.menu == null || (arg9.refreshMenuContent)) {
                if(arg9.menu == null && (!this.initializePanelMenu(arg9) || arg9.menu == null)) {
                    return 0;
                }

                if(v3 != 0 && this.mDecorContentParent != null) {
                    if(this.mActionMenuPresenterCallback == null) {
                        this.mActionMenuPresenterCallback = new ActionMenuPresenterCallback(this);
                    }

                    this.mDecorContentParent.setMenu(arg9.menu, this.mActionMenuPresenterCallback);
                }

                arg9.menu.stopDispatchingItemsChanged();
                if(!v0.onCreatePanelMenu(arg9.featureId, arg9.menu)) {
                    arg9.setMenu(v5);
                    if(v3 != 0 && this.mDecorContentParent != null) {
                        this.mDecorContentParent.setMenu(((Menu)v5), this.mActionMenuPresenterCallback);
                    }

                    return 0;
                }

                arg9.refreshMenuContent = false;
            }

            arg9.menu.stopDispatchingItemsChanged();
            if(arg9.frozenActionViewState != null) {
                arg9.menu.restoreActionViewStates(arg9.frozenActionViewState);
                arg9.frozenActionViewState = ((Bundle)v5);
            }

            if(!v0.onPreparePanel(0, arg9.createdPanelView, arg9.menu)) {
                if(v3 != 0 && this.mDecorContentParent != null) {
                    this.mDecorContentParent.setMenu(((Menu)v5), this.mActionMenuPresenterCallback);
                }

                arg9.menu.startDispatchingItemsChanged();
                return 0;
            }

            int v10 = arg10 != null ? arg10.getDeviceId() : -1;
            boolean v10_1 = KeyCharacterMap.load(v10).getKeyboardType() != 1 ? true : false;
            arg9.qwertyMode = v10_1;
            arg9.menu.setQwertyMode(arg9.qwertyMode);
            arg9.menu.startDispatchingItemsChanged();
        }

        arg9.isPrepared = true;
        arg9.isHandled = false;
        this.mPreparedPanel = arg9;
        return 1;
    }

    private void reopenMenu(MenuBuilder arg5, boolean arg6) {
        if(this.mDecorContentParent != null && (this.mDecorContentParent.canShowOverflowMenu()) && (!ViewConfiguration.get(this.mContext).hasPermanentMenuKey() || (this.mDecorContentParent.isOverflowMenuShowPending()))) {
            Window$Callback v5 = this.getWindowCallback();
            int v3 = 108;
            if((this.mDecorContentParent.isOverflowMenuShowing()) && (arg6)) {
                this.mDecorContentParent.hideOverflowMenu();
                if(!this.isDestroyed()) {
                    v5.onPanelClosed(v3, this.getPanelState(0, true).menu);
                }
            }
            else if(v5 != null && !this.isDestroyed()) {
                if((this.mInvalidatePanelMenuPosted) && (this.mInvalidatePanelMenuFeatures & 1) != 0) {
                    this.mWindow.getDecorView().removeCallbacks(this.mInvalidatePanelMenuRunnable);
                    this.mInvalidatePanelMenuRunnable.run();
                }

                PanelFeatureState v6 = this.getPanelState(0, true);
                if(v6.menu == null) {
                    return;
                }

                if(v6.refreshMenuContent) {
                    return;
                }

                if(!v5.onPreparePanel(0, v6.createdPanelView, v6.menu)) {
                    return;
                }

                v5.onMenuOpened(v3, v6.menu);
                this.mDecorContentParent.showOverflowMenu();
            }

            return;
        }

        PanelFeatureState v5_1 = this.getPanelState(0, true);
        v5_1.refreshDecorView = true;
        this.closePanel(v5_1, false);
        this.openPanel(v5_1, null);
    }

    public boolean requestWindowFeature(int arg4) {
        arg4 = this.sanitizeWindowFeatureId(arg4);
        if((this.mWindowNoTitle) && arg4 == 108) {
            return 0;
        }

        if((this.mHasActionBar) && arg4 == 1) {
            this.mHasActionBar = false;
        }

        switch(arg4) {
            case 1: {
                goto label_31;
            }
            case 2: {
                goto label_28;
            }
            case 5: {
                goto label_25;
            }
            case 10: {
                goto label_22;
            }
            case 108: {
                goto label_19;
            }
            case 109: {
                goto label_16;
            }
        }

        return this.mWindow.requestFeature(arg4);
    label_19:
        this.throwFeatureRequestIfSubDecorInstalled();
        this.mHasActionBar = true;
        return 1;
    label_22:
        this.throwFeatureRequestIfSubDecorInstalled();
        this.mOverlayActionMode = true;
        return 1;
    label_25:
        this.throwFeatureRequestIfSubDecorInstalled();
        this.mFeatureIndeterminateProgress = true;
        return 1;
    label_28:
        this.throwFeatureRequestIfSubDecorInstalled();
        this.mFeatureProgress = true;
        return 1;
    label_31:
        this.throwFeatureRequestIfSubDecorInstalled();
        this.mWindowNoTitle = true;
        return 1;
    label_16:
        this.throwFeatureRequestIfSubDecorInstalled();
        this.mOverlayActionBar = true;
        return 1;
    }

    private int sanitizeWindowFeatureId(int arg2) {
        if(arg2 == 8) {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR id when requesting this feature.");
            return 108;
        }

        if(arg2 == 9) {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY id when requesting this feature.");
            return 109;
        }

        return arg2;
    }

    public void setContentView(int arg3) {
        this.ensureSubDecor();
        View v0 = this.mSubDecor.findViewById(0x1020002);
        ((ViewGroup)v0).removeAllViews();
        LayoutInflater.from(this.mContext).inflate(arg3, ((ViewGroup)v0));
        this.mOriginalWindowCallback.onContentChanged();
    }

    public void setContentView(View arg3) {
        this.ensureSubDecor();
        View v0 = this.mSubDecor.findViewById(0x1020002);
        ((ViewGroup)v0).removeAllViews();
        ((ViewGroup)v0).addView(arg3);
        this.mOriginalWindowCallback.onContentChanged();
    }

    public void setContentView(View arg3, ViewGroup$LayoutParams arg4) {
        this.ensureSubDecor();
        View v0 = this.mSubDecor.findViewById(0x1020002);
        ((ViewGroup)v0).removeAllViews();
        ((ViewGroup)v0).addView(arg3, arg4);
        this.mOriginalWindowCallback.onContentChanged();
    }

    public void setSupportActionBar(Toolbar arg4) {
        if(!(this.mOriginalWindowCallback instanceof Activity)) {
            return;
        }

        ActionBar v0 = this.getSupportActionBar();
        if((v0 instanceof WindowDecorActionBar)) {
            throw new IllegalStateException("This Activity already has an action bar supplied by the window decor. Do not request Window.FEATURE_SUPPORT_ACTION_BAR and set windowActionBar to false in your theme to use a Toolbar instead.");
        }

        MenuInflater v1 = null;
        this.mMenuInflater = v1;
        if(v0 != null) {
            v0.onDestroy();
        }

        if(arg4 != null) {
            ToolbarActionBar v0_1 = new ToolbarActionBar(arg4, this.mOriginalWindowCallback.getTitle(), this.mAppCompatWindowCallback);
            this.mActionBar = ((ActionBar)v0_1);
            this.mWindow.setCallback(v0_1.getWrappedWindowCallback());
        }
        else {
            this.mActionBar = ((ActionBar)v1);
            this.mWindow.setCallback(this.mAppCompatWindowCallback);
        }

        this.invalidateOptionsMenu();
    }

    final boolean shouldAnimateActionModeView() {
        boolean v0 = !this.mSubDecorInstalled || this.mSubDecor == null || !ViewCompat.isLaidOut(this.mSubDecor) ? false : true;
        return v0;
    }

    private boolean shouldInheritContext(ViewParent arg4) {
        if(arg4 == null) {
            return 0;
        }

        View v1 = this.mWindow.getDecorView();
        while(true) {
            if(arg4 == null) {
                return 1;
            }

            if(arg4 != (((ViewParent)v1)) && ((arg4 instanceof View))) {
                if(ViewCompat.isAttachedToWindow(arg4)) {
                }
                else {
                    arg4 = arg4.getParent();
                    continue;
                }
            }

            return 0;
        }

        return 0;
    }

    public ActionMode startSupportActionMode(@NonNull android.support.v7.view.ActionMode$Callback arg3) {
        if(arg3 == null) {
            throw new IllegalArgumentException("ActionMode callback can not be null.");
        }

        if(this.mActionMode != null) {
            this.mActionMode.finish();
        }

        ActionModeCallbackWrapperV9 v0 = new ActionModeCallbackWrapperV9(this, arg3);
        ActionBar v3 = this.getSupportActionBar();
        if(v3 != null) {
            this.mActionMode = v3.startActionMode(((android.support.v7.view.ActionMode$Callback)v0));
            if(this.mActionMode != null && this.mAppCompatCallback != null) {
                this.mAppCompatCallback.onSupportActionModeStarted(this.mActionMode);
            }
        }

        if(this.mActionMode == null) {
            this.mActionMode = this.startSupportActionModeFromWindow(((android.support.v7.view.ActionMode$Callback)v0));
        }

        return this.mActionMode;
    }

    ActionMode startSupportActionModeFromWindow(@NonNull android.support.v7.view.ActionMode$Callback arg8) {
        Context v4_2;
        ActionMode v0;
        ActionModeCallbackWrapperV9 v8;
        this.endOnGoingFadeAnimation();
        if(this.mActionMode != null) {
            this.mActionMode.finish();
        }

        if(!(arg8 instanceof ActionModeCallbackWrapperV9)) {
            v8 = new ActionModeCallbackWrapperV9(this, arg8);
        }

        AttributeSet v1 = null;
        if(this.mAppCompatCallback == null) {
            goto label_18;
        }
        else if(!this.isDestroyed()) {
            try {
                v0 = this.mAppCompatCallback.onWindowStartingSupportActionMode(((android.support.v7.view.ActionMode$Callback)v8));
            }
            catch(AbstractMethodError ) {
            label_18:
                v0 = ((ActionMode)v1);
            }
        }
        else {
            goto label_18;
        }

        if(v0 != null) {
            this.mActionMode = v0;
        }
        else {
            boolean v3 = true;
            if(this.mActionModeView == null) {
                if(this.mIsFloating) {
                    TypedValue v0_1 = new TypedValue();
                    Resources$Theme v4 = this.mContext.getTheme();
                    v4.resolveAttribute(attr.actionBarTheme, v0_1, true);
                    if(v0_1.resourceId != 0) {
                        Resources$Theme v5 = this.mContext.getResources().newTheme();
                        v5.setTo(v4);
                        v5.applyStyle(v0_1.resourceId, true);
                        ContextThemeWrapper v4_1 = new ContextThemeWrapper(this.mContext, 0);
                        ((Context)v4_1).getTheme().setTo(v5);
                    }
                    else {
                        v4_2 = this.mContext;
                    }

                    this.mActionModeView = new ActionBarContextView(v4_2);
                    this.mActionModePopup = new PopupWindow(v4_2, v1, attr.actionModePopupWindowStyle);
                    PopupWindowCompat.setWindowLayoutType(this.mActionModePopup, 2);
                    this.mActionModePopup.setContentView(this.mActionModeView);
                    this.mActionModePopup.setWidth(-1);
                    v4_2.getTheme().resolveAttribute(attr.actionBarSize, v0_1, true);
                    this.mActionModeView.setContentHeight(TypedValue.complexToDimensionPixelSize(v0_1.data, v4_2.getResources().getDisplayMetrics()));
                    this.mActionModePopup.setHeight(-2);
                    this.mShowActionModePopup = new Runnable() {
                        public void run() {
                            AppCompatDelegateImplV9.this.mActionModePopup.showAtLocation(AppCompatDelegateImplV9.this.mActionModeView, 55, 0, 0);
                            AppCompatDelegateImplV9.this.endOnGoingFadeAnimation();
                            float v1 = 1f;
                            if(AppCompatDelegateImplV9.this.shouldAnimateActionModeView()) {
                                AppCompatDelegateImplV9.this.mActionModeView.setAlpha(0f);
                                AppCompatDelegateImplV9.this.mFadeAnim = ViewCompat.animate(AppCompatDelegateImplV9.this.mActionModeView).alpha(v1);
                                AppCompatDelegateImplV9.this.mFadeAnim.setListener(new ViewPropertyAnimatorListenerAdapter() {
                                    public void onAnimationEnd(View arg2) {
                                        this.this$1.this$0.mActionModeView.setAlpha(1f);
                                        this.this$1.this$0.mFadeAnim.setListener(null);
                                        this.this$1.this$0.mFadeAnim = null;
                                    }

                                    public void onAnimationStart(View arg2) {
                                        this.this$1.this$0.mActionModeView.setVisibility(0);
                                    }
                                });
                            }
                            else {
                                AppCompatDelegateImplV9.this.mActionModeView.setAlpha(v1);
                                AppCompatDelegateImplV9.this.mActionModeView.setVisibility(0);
                            }
                        }
                    };
                }
                else {
                    View v0_2 = this.mSubDecor.findViewById(id.action_mode_bar_stub);
                    if(v0_2 == null) {
                        goto label_90;
                    }

                    ((ViewStubCompat)v0_2).setLayoutInflater(LayoutInflater.from(this.getActionBarThemedContext()));
                    this.mActionModeView = ((ViewStubCompat)v0_2).inflate();
                }
            }

        label_90:
            if(this.mActionModeView == null) {
                goto label_148;
            }

            this.endOnGoingFadeAnimation();
            this.mActionModeView.killMode();
            v4_2 = this.mActionModeView.getContext();
            ActionBarContextView v5_1 = this.mActionModeView;
            if(this.mActionModePopup == null) {
            }
            else {
                v3 = false;
            }

            StandaloneActionMode v0_3 = new StandaloneActionMode(v4_2, v5_1, ((android.support.v7.view.ActionMode$Callback)v8), v3);
            if(((android.support.v7.view.ActionMode$Callback)v8).onCreateActionMode(((ActionMode)v0_3), ((ActionMode)v0_3).getMenu())) {
                ((ActionMode)v0_3).invalidate();
                this.mActionModeView.initForMode(((ActionMode)v0_3));
                this.mActionMode = ((ActionMode)v0_3);
                float v0_4 = 1f;
                if(this.shouldAnimateActionModeView()) {
                    this.mActionModeView.setAlpha(0f);
                    this.mFadeAnim = ViewCompat.animate(this.mActionModeView).alpha(v0_4);
                    this.mFadeAnim.setListener(new ViewPropertyAnimatorListenerAdapter() {
                        public void onAnimationEnd(View arg2) {
                            AppCompatDelegateImplV9.this.mActionModeView.setAlpha(1f);
                            AppCompatDelegateImplV9.this.mFadeAnim.setListener(null);
                            AppCompatDelegateImplV9.this.mFadeAnim = null;
                        }

                        public void onAnimationStart(View arg2) {
                            AppCompatDelegateImplV9.this.mActionModeView.setVisibility(0);
                            AppCompatDelegateImplV9.this.mActionModeView.sendAccessibilityEvent(0x20);
                            if((AppCompatDelegateImplV9.this.mActionModeView.getParent() instanceof View)) {
                                ViewCompat.requestApplyInsets(AppCompatDelegateImplV9.this.mActionModeView.getParent());
                            }
                        }
                    });
                }
                else {
                    this.mActionModeView.setAlpha(v0_4);
                    this.mActionModeView.setVisibility(0);
                    this.mActionModeView.sendAccessibilityEvent(0x20);
                    if((this.mActionModeView.getParent() instanceof View)) {
                        ViewCompat.requestApplyInsets(this.mActionModeView.getParent());
                    }
                }

                if(this.mActionModePopup == null) {
                    goto label_148;
                }

                this.mWindow.getDecorView().post(this.mShowActionModePopup);
                goto label_148;
            }

            this.mActionMode = ((ActionMode)v1);
        }

    label_148:
        if(this.mActionMode != null && this.mAppCompatCallback != null) {
            this.mAppCompatCallback.onSupportActionModeStarted(this.mActionMode);
        }

        return this.mActionMode;
    }

    private void throwFeatureRequestIfSubDecorInstalled() {
        if(this.mSubDecorInstalled) {
            throw new AndroidRuntimeException("Window feature must be requested before adding content");
        }
    }

    int updateStatusGuard(int arg8) {
        int v2_1;
        int v3;
        int v1 = 0;
        if(this.mActionModeView == null || !(this.mActionModeView.getLayoutParams() instanceof ViewGroup$MarginLayoutParams)) {
            v3 = 0;
        }
        else {
            ViewGroup$LayoutParams v0 = this.mActionModeView.getLayoutParams();
            v3 = 1;
            if(this.mActionModeView.isShown()) {
                if(this.mTempRect1 == null) {
                    this.mTempRect1 = new Rect();
                    this.mTempRect2 = new Rect();
                }

                Rect v2 = this.mTempRect1;
                Rect v4 = this.mTempRect2;
                v2.set(0, arg8, 0, 0);
                ViewUtils.computeFitSystemWindows(this.mSubDecor, v2, v4);
                v2_1 = v4.top == 0 ? arg8 : 0;
                if(((ViewGroup$MarginLayoutParams)v0).topMargin != v2_1) {
                    ((ViewGroup$MarginLayoutParams)v0).topMargin = arg8;
                    if(this.mStatusGuard == null) {
                        this.mStatusGuard = new View(this.mContext);
                        this.mStatusGuard.setBackgroundColor(this.mContext.getResources().getColor(color.abc_input_method_navigation_guard));
                        this.mSubDecor.addView(this.mStatusGuard, -1, new ViewGroup$LayoutParams(-1, arg8));
                    }
                    else {
                        ViewGroup$LayoutParams v2_2 = this.mStatusGuard.getLayoutParams();
                        if(v2_2.height != arg8) {
                            v2_2.height = arg8;
                            this.mStatusGuard.setLayoutParams(v2_2);
                        }
                    }

                    v2_1 = 1;
                }
                else {
                    v2_1 = 0;
                }

                if(this.mStatusGuard != null) {
                    goto label_79;
                }

                v3 = 0;
            }
            else {
                if(((ViewGroup$MarginLayoutParams)v0).topMargin != 0) {
                    ((ViewGroup$MarginLayoutParams)v0).topMargin = 0;
                    v2_1 = 1;
                }
                else {
                    v2_1 = 0;
                }

                v3 = 0;
            }

        label_79:
            if(v2_1 == 0) {
                goto label_84;
            }

            this.mActionModeView.setLayoutParams(v0);
        }

    label_84:
        if(this.mStatusGuard != null) {
            View v0_1 = this.mStatusGuard;
            if(v3 != 0) {
            }
            else {
                v1 = 8;
            }

            v0_1.setVisibility(v1);
        }

        return 0;
    }
}

