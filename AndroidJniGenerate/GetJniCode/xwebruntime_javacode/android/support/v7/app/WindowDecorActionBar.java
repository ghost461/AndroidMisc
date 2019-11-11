package android.support.v7.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.v4.view.ViewPropertyAnimatorUpdateListener;
import android.support.v7.appcompat.R$attr;
import android.support.v7.appcompat.R$id;
import android.support.v7.appcompat.R$styleable;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.view.ActionBarPolicy;
import android.support.v7.view.ActionMode;
import android.support.v7.view.SupportMenuInflater;
import android.support.v7.view.ViewPropertyAnimatorCompatSet;
import android.support.v7.view.menu.MenuBuilder$Callback;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.view.menu.SubMenuBuilder;
import android.support.v7.widget.ActionBarContainer;
import android.support.v7.widget.ActionBarContextView;
import android.support.v7.widget.ActionBarOverlayLayout$ActionBarVisibilityCallback;
import android.support.v7.widget.ActionBarOverlayLayout;
import android.support.v7.widget.DecorToolbar;
import android.support.v7.widget.ScrollingTabContainerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.SpinnerAdapter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

@RestrictTo(value={Scope.LIBRARY_GROUP}) public class WindowDecorActionBar extends ActionBar implements ActionBarVisibilityCallback {
    class android.support.v7.app.WindowDecorActionBar$1 extends ViewPropertyAnimatorListenerAdapter {
        android.support.v7.app.WindowDecorActionBar$1(WindowDecorActionBar arg1) {
            WindowDecorActionBar.this = arg1;
            super();
        }

        public void onAnimationEnd(View arg2) {
            if((WindowDecorActionBar.this.mContentAnimations) && WindowDecorActionBar.this.mContentView != null) {
                WindowDecorActionBar.this.mContentView.setTranslationY(0f);
                WindowDecorActionBar.this.mContainerView.setTranslationY(0f);
            }

            WindowDecorActionBar.this.mContainerView.setVisibility(8);
            WindowDecorActionBar.this.mContainerView.setTransitioning(false);
            WindowDecorActionBar.this.mCurrentShowAnim = null;
            WindowDecorActionBar.this.completeDeferredDestroyActionMode();
            if(WindowDecorActionBar.this.mOverlayLayout != null) {
                ViewCompat.requestApplyInsets(WindowDecorActionBar.this.mOverlayLayout);
            }
        }
    }

    class android.support.v7.app.WindowDecorActionBar$2 extends ViewPropertyAnimatorListenerAdapter {
        android.support.v7.app.WindowDecorActionBar$2(WindowDecorActionBar arg1) {
            WindowDecorActionBar.this = arg1;
            super();
        }

        public void onAnimationEnd(View arg2) {
            WindowDecorActionBar.this.mCurrentShowAnim = null;
            WindowDecorActionBar.this.mContainerView.requestLayout();
        }
    }

    class android.support.v7.app.WindowDecorActionBar$3 implements ViewPropertyAnimatorUpdateListener {
        android.support.v7.app.WindowDecorActionBar$3(WindowDecorActionBar arg1) {
            WindowDecorActionBar.this = arg1;
            super();
        }

        public void onAnimationUpdate(View arg1) {
            WindowDecorActionBar.this.mContainerView.getParent().invalidate();
        }
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public class ActionModeImpl extends ActionMode implements Callback {
        private final Context mActionModeContext;
        private android.support.v7.view.ActionMode$Callback mCallback;
        private WeakReference mCustomView;
        private final MenuBuilder mMenu;

        public ActionModeImpl(WindowDecorActionBar arg1, Context arg2, android.support.v7.view.ActionMode$Callback arg3) {
            WindowDecorActionBar.this = arg1;
            super();
            this.mActionModeContext = arg2;
            this.mCallback = arg3;
            this.mMenu = new MenuBuilder(arg2).setDefaultShowAsAction(1);
            this.mMenu.setCallback(((Callback)this));
        }

        public boolean dispatchOnCreate() {
            boolean v0_1;
            this.mMenu.stopDispatchingItemsChanged();
            try {
                v0_1 = this.mCallback.onCreateActionMode(((ActionMode)this), this.mMenu);
            }
            catch(Throwable v0) {
                this.mMenu.startDispatchingItemsChanged();
                throw v0;
            }

            this.mMenu.startDispatchingItemsChanged();
            return v0_1;
        }

        public void finish() {
            if(WindowDecorActionBar.this.mActionMode != this) {
                return;
            }

            if(!WindowDecorActionBar.checkShowingFlags(WindowDecorActionBar.this.mHiddenByApp, WindowDecorActionBar.this.mHiddenBySystem, false)) {
                WindowDecorActionBar.this.mDeferredDestroyActionMode = ((ActionMode)this);
                WindowDecorActionBar.this.mDeferredModeDestroyCallback = this.mCallback;
            }
            else {
                this.mCallback.onDestroyActionMode(((ActionMode)this));
            }

            this.mCallback = null;
            WindowDecorActionBar.this.animateToMode(false);
            WindowDecorActionBar.this.mContextView.closeMode();
            WindowDecorActionBar.this.mDecorToolbar.getViewGroup().sendAccessibilityEvent(0x20);
            WindowDecorActionBar.this.mOverlayLayout.setHideOnContentScrollEnabled(WindowDecorActionBar.this.mHideOnContentScroll);
            WindowDecorActionBar.this.mActionMode = null;
        }

        public View getCustomView() {
            Object v0;
            if(this.mCustomView != null) {
                v0 = this.mCustomView.get();
            }
            else {
                View v0_1 = null;
            }

            return ((View)v0);
        }

        public Menu getMenu() {
            return this.mMenu;
        }

        public MenuInflater getMenuInflater() {
            return new SupportMenuInflater(this.mActionModeContext);
        }

        public CharSequence getSubtitle() {
            return WindowDecorActionBar.this.mContextView.getSubtitle();
        }

        public CharSequence getTitle() {
            return WindowDecorActionBar.this.mContextView.getTitle();
        }

        public void invalidate() {
            if(WindowDecorActionBar.this.mActionMode != this) {
                return;
            }

            this.mMenu.stopDispatchingItemsChanged();
            try {
                this.mCallback.onPrepareActionMode(((ActionMode)this), this.mMenu);
            }
            catch(Throwable v0) {
                this.mMenu.startDispatchingItemsChanged();
                throw v0;
            }

            this.mMenu.startDispatchingItemsChanged();
        }

        public boolean isTitleOptional() {
            return WindowDecorActionBar.this.mContextView.isTitleOptional();
        }

        public void onCloseMenu(MenuBuilder arg1, boolean arg2) {
        }

        public void onCloseSubMenu(SubMenuBuilder arg1) {
        }

        public boolean onMenuItemSelected(MenuBuilder arg1, MenuItem arg2) {
            if(this.mCallback != null) {
                return this.mCallback.onActionItemClicked(((ActionMode)this), arg2);
            }

            return 0;
        }

        public void onMenuModeChange(MenuBuilder arg1) {
            if(this.mCallback == null) {
                return;
            }

            this.invalidate();
            WindowDecorActionBar.this.mContextView.showOverflowMenu();
        }

        public boolean onSubMenuSelected(SubMenuBuilder arg4) {
            if(this.mCallback == null) {
                return 0;
            }

            if(!arg4.hasVisibleItems()) {
                return 1;
            }

            new MenuPopupHelper(WindowDecorActionBar.this.getThemedContext(), ((MenuBuilder)arg4)).show();
            return 1;
        }

        public void setCustomView(View arg2) {
            WindowDecorActionBar.this.mContextView.setCustomView(arg2);
            this.mCustomView = new WeakReference(arg2);
        }

        public void setSubtitle(int arg2) {
            this.setSubtitle(WindowDecorActionBar.this.mContext.getResources().getString(arg2));
        }

        public void setSubtitle(CharSequence arg2) {
            WindowDecorActionBar.this.mContextView.setSubtitle(arg2);
        }

        public void setTitle(int arg2) {
            this.setTitle(WindowDecorActionBar.this.mContext.getResources().getString(arg2));
        }

        public void setTitle(CharSequence arg2) {
            WindowDecorActionBar.this.mContextView.setTitle(arg2);
        }

        public void setTitleOptionalHint(boolean arg2) {
            super.setTitleOptionalHint(arg2);
            WindowDecorActionBar.this.mContextView.setTitleOptional(arg2);
        }
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public class TabImpl extends Tab {
        private TabListener mCallback;
        private CharSequence mContentDesc;
        private View mCustomView;
        private Drawable mIcon;
        private int mPosition;
        private Object mTag;
        private CharSequence mText;

        public TabImpl(WindowDecorActionBar arg1) {
            WindowDecorActionBar.this = arg1;
            super();
            this.mPosition = -1;
        }

        public TabListener getCallback() {
            return this.mCallback;
        }

        public CharSequence getContentDescription() {
            return this.mContentDesc;
        }

        public View getCustomView() {
            return this.mCustomView;
        }

        public Drawable getIcon() {
            return this.mIcon;
        }

        public int getPosition() {
            return this.mPosition;
        }

        public Object getTag() {
            return this.mTag;
        }

        public CharSequence getText() {
            return this.mText;
        }

        public void select() {
            WindowDecorActionBar.this.selectTab(((Tab)this));
        }

        public Tab setContentDescription(int arg2) {
            return this.setContentDescription(WindowDecorActionBar.this.mContext.getResources().getText(arg2));
        }

        public Tab setContentDescription(CharSequence arg2) {
            this.mContentDesc = arg2;
            if(this.mPosition >= 0) {
                WindowDecorActionBar.this.mTabScrollView.updateTab(this.mPosition);
            }

            return this;
        }

        public Tab setCustomView(int arg3) {
            return this.setCustomView(LayoutInflater.from(WindowDecorActionBar.this.getThemedContext()).inflate(arg3, null));
        }

        public Tab setCustomView(View arg2) {
            this.mCustomView = arg2;
            if(this.mPosition >= 0) {
                WindowDecorActionBar.this.mTabScrollView.updateTab(this.mPosition);
            }

            return this;
        }

        public Tab setIcon(int arg2) {
            return this.setIcon(AppCompatResources.getDrawable(WindowDecorActionBar.this.mContext, arg2));
        }

        public Tab setIcon(Drawable arg2) {
            this.mIcon = arg2;
            if(this.mPosition >= 0) {
                WindowDecorActionBar.this.mTabScrollView.updateTab(this.mPosition);
            }

            return this;
        }

        public void setPosition(int arg1) {
            this.mPosition = arg1;
        }

        public Tab setTabListener(TabListener arg1) {
            this.mCallback = arg1;
            return this;
        }

        public Tab setTag(Object arg1) {
            this.mTag = arg1;
            return this;
        }

        public Tab setText(int arg2) {
            return this.setText(WindowDecorActionBar.this.mContext.getResources().getText(arg2));
        }

        public Tab setText(CharSequence arg2) {
            this.mText = arg2;
            if(this.mPosition >= 0) {
                WindowDecorActionBar.this.mTabScrollView.updateTab(this.mPosition);
            }

            return this;
        }
    }

    private static final long FADE_IN_DURATION_MS = 200;
    private static final long FADE_OUT_DURATION_MS = 100;
    private static final int INVALID_POSITION = -1;
    private static final String TAG = "WindowDecorActionBar";
    ActionModeImpl mActionMode;
    private Activity mActivity;
    ActionBarContainer mContainerView;
    boolean mContentAnimations;
    View mContentView;
    Context mContext;
    ActionBarContextView mContextView;
    private int mCurWindowVisibility;
    ViewPropertyAnimatorCompatSet mCurrentShowAnim;
    DecorToolbar mDecorToolbar;
    ActionMode mDeferredDestroyActionMode;
    android.support.v7.view.ActionMode$Callback mDeferredModeDestroyCallback;
    private Dialog mDialog;
    private boolean mDisplayHomeAsUpSet;
    private boolean mHasEmbeddedTabs;
    boolean mHiddenByApp;
    boolean mHiddenBySystem;
    final ViewPropertyAnimatorListener mHideListener;
    boolean mHideOnContentScroll;
    private boolean mLastMenuVisibility;
    private ArrayList mMenuVisibilityListeners;
    private boolean mNowShowing;
    ActionBarOverlayLayout mOverlayLayout;
    private int mSavedTabPosition;
    private TabImpl mSelectedTab;
    private boolean mShowHideAnimationEnabled;
    final ViewPropertyAnimatorListener mShowListener;
    private boolean mShowingForMode;
    ScrollingTabContainerView mTabScrollView;
    private ArrayList mTabs;
    private Context mThemedContext;
    final ViewPropertyAnimatorUpdateListener mUpdateListener;
    private static final Interpolator sHideInterpolator;
    private static final Interpolator sShowInterpolator;

    static {
        WindowDecorActionBar.sHideInterpolator = new AccelerateInterpolator();
        WindowDecorActionBar.sShowInterpolator = new DecelerateInterpolator();
    }

    public WindowDecorActionBar(Activity arg2, boolean arg3) {
        super();
        this.mTabs = new ArrayList();
        this.mSavedTabPosition = -1;
        this.mMenuVisibilityListeners = new ArrayList();
        this.mCurWindowVisibility = 0;
        this.mContentAnimations = true;
        this.mNowShowing = true;
        this.mHideListener = new android.support.v7.app.WindowDecorActionBar$1(this);
        this.mShowListener = new android.support.v7.app.WindowDecorActionBar$2(this);
        this.mUpdateListener = new android.support.v7.app.WindowDecorActionBar$3(this);
        this.mActivity = arg2;
        View v2 = arg2.getWindow().getDecorView();
        this.init(v2);
        if(!arg3) {
            this.mContentView = v2.findViewById(0x1020002);
        }
    }

    public WindowDecorActionBar(Dialog arg2) {
        super();
        this.mTabs = new ArrayList();
        this.mSavedTabPosition = -1;
        this.mMenuVisibilityListeners = new ArrayList();
        this.mCurWindowVisibility = 0;
        this.mContentAnimations = true;
        this.mNowShowing = true;
        this.mHideListener = new android.support.v7.app.WindowDecorActionBar$1(this);
        this.mShowListener = new android.support.v7.app.WindowDecorActionBar$2(this);
        this.mUpdateListener = new android.support.v7.app.WindowDecorActionBar$3(this);
        this.mDialog = arg2;
        this.init(arg2.getWindow().getDecorView());
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public WindowDecorActionBar(View arg2) {
        super();
        this.mTabs = new ArrayList();
        this.mSavedTabPosition = -1;
        this.mMenuVisibilityListeners = new ArrayList();
        this.mCurWindowVisibility = 0;
        this.mContentAnimations = true;
        this.mNowShowing = true;
        this.mHideListener = new android.support.v7.app.WindowDecorActionBar$1(this);
        this.mShowListener = new android.support.v7.app.WindowDecorActionBar$2(this);
        this.mUpdateListener = new android.support.v7.app.WindowDecorActionBar$3(this);
        this.init(arg2);
    }

    public void addOnMenuVisibilityListener(OnMenuVisibilityListener arg2) {
        this.mMenuVisibilityListeners.add(arg2);
    }

    public void addTab(Tab arg2) {
        this.addTab(arg2, this.mTabs.isEmpty());
    }

    public void addTab(Tab arg2, boolean arg3) {
        this.ensureTabsExist();
        this.mTabScrollView.addTab(arg2, arg3);
        this.configureTab(arg2, this.mTabs.size());
        if(arg3) {
            this.selectTab(arg2);
        }
    }

    public void addTab(Tab arg2, int arg3) {
        this.addTab(arg2, arg3, this.mTabs.isEmpty());
    }

    public void addTab(Tab arg2, int arg3, boolean arg4) {
        this.ensureTabsExist();
        this.mTabScrollView.addTab(arg2, arg3, arg4);
        this.configureTab(arg2, arg3);
        if(arg4) {
            this.selectTab(arg2);
        }
    }

    public void animateToMode(boolean arg9) {
        ViewPropertyAnimatorCompat v0;
        ViewPropertyAnimatorCompat v9;
        if(arg9) {
            this.showForActionMode();
        }
        else {
            this.hideForActionMode();
        }

        int v1 = 8;
        int v2 = 4;
        if(this.shouldAnimateContextView()) {
            long v4 = 200;
            long v6 = 100;
            if(arg9) {
                v9 = this.mDecorToolbar.setupAnimatorToVisibility(v2, v6);
                v0 = this.mContextView.setupAnimatorToVisibility(0, v4);
            }
            else {
                v0 = this.mDecorToolbar.setupAnimatorToVisibility(0, v4);
                v9 = this.mContextView.setupAnimatorToVisibility(v1, v6);
            }

            ViewPropertyAnimatorCompatSet v1_1 = new ViewPropertyAnimatorCompatSet();
            v1_1.playSequentially(v9, v0);
            v1_1.start();
        }
        else {
            if(arg9) {
                this.mDecorToolbar.setVisibility(v2);
                this.mContextView.setVisibility(0);
                return;
            }

            this.mDecorToolbar.setVisibility(0);
            this.mContextView.setVisibility(v1);
        }
    }

    static boolean checkShowingFlags(boolean arg1, boolean arg2, boolean arg3) {
        if(arg3) {
            return 1;
        }

        if(!arg1) {
            if(arg2) {
            }
            else {
                return 1;
            }
        }

        return 0;
    }

    private void cleanupTabs() {
        if(this.mSelectedTab != null) {
            this.selectTab(null);
        }

        this.mTabs.clear();
        if(this.mTabScrollView != null) {
            this.mTabScrollView.removeAllTabs();
        }

        this.mSavedTabPosition = -1;
    }

    public boolean collapseActionView() {
        if(this.mDecorToolbar != null && (this.mDecorToolbar.hasExpandedActionView())) {
            this.mDecorToolbar.collapseActionView();
            return 1;
        }

        return 0;
    }

    void completeDeferredDestroyActionMode() {
        if(this.mDeferredModeDestroyCallback != null) {
            this.mDeferredModeDestroyCallback.onDestroyActionMode(this.mDeferredDestroyActionMode);
            this.mDeferredDestroyActionMode = null;
            this.mDeferredModeDestroyCallback = null;
        }
    }

    private void configureTab(Tab arg2, int arg3) {
        if(((TabImpl)arg2).getCallback() == null) {
            throw new IllegalStateException("Action Bar Tab must have a Callback");
        }

        ((TabImpl)arg2).setPosition(arg3);
        this.mTabs.add(arg3, arg2);
        int v2 = this.mTabs.size();
        while(true) {
            ++arg3;
            if(arg3 >= v2) {
                return;
            }

            this.mTabs.get(arg3).setPosition(arg3);
        }
    }

    public void dispatchMenuVisibilityChanged(boolean arg4) {
        if(arg4 == this.mLastMenuVisibility) {
            return;
        }

        this.mLastMenuVisibility = arg4;
        int v0 = this.mMenuVisibilityListeners.size();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            this.mMenuVisibilityListeners.get(v1).onMenuVisibilityChanged(arg4);
        }
    }

    public void doHide(boolean arg5) {
        if(this.mCurrentShowAnim != null) {
            this.mCurrentShowAnim.cancel();
        }

        if(this.mCurWindowVisibility == 0) {
            if(!this.mShowHideAnimationEnabled && !arg5) {
                goto label_52;
            }

            this.mContainerView.setAlpha(1f);
            this.mContainerView.setTransitioning(true);
            ViewPropertyAnimatorCompatSet v0 = new ViewPropertyAnimatorCompatSet();
            float v2 = ((float)(-this.mContainerView.getHeight()));
            if(arg5) {
                int[] v5 = new int[]{0, 0};
                this.mContainerView.getLocationInWindow(v5);
                v2 -= ((float)v5[1]);
            }

            ViewPropertyAnimatorCompat v5_1 = ViewCompat.animate(this.mContainerView).translationY(v2);
            v5_1.setUpdateListener(this.mUpdateListener);
            v0.play(v5_1);
            if((this.mContentAnimations) && this.mContentView != null) {
                v0.play(ViewCompat.animate(this.mContentView).translationY(v2));
            }

            v0.setInterpolator(WindowDecorActionBar.sHideInterpolator);
            v0.setDuration(0xFA);
            v0.setListener(this.mHideListener);
            this.mCurrentShowAnim = v0;
            v0.start();
        }
        else {
        label_52:
            this.mHideListener.onAnimationEnd(null);
        }
    }

    public void doShow(boolean arg5) {
        if(this.mCurrentShowAnim != null) {
            this.mCurrentShowAnim.cancel();
        }

        this.mContainerView.setVisibility(0);
        if(this.mCurWindowVisibility == 0) {
            if(!this.mShowHideAnimationEnabled && !arg5) {
                goto label_57;
            }

            this.mContainerView.setTranslationY(0f);
            float v0 = ((float)(-this.mContainerView.getHeight()));
            if(arg5) {
                int[] v5 = new int[]{0, 0};
                this.mContainerView.getLocationInWindow(v5);
                v0 -= ((float)v5[1]);
            }

            this.mContainerView.setTranslationY(v0);
            ViewPropertyAnimatorCompatSet v5_1 = new ViewPropertyAnimatorCompatSet();
            ViewPropertyAnimatorCompat v2 = ViewCompat.animate(this.mContainerView).translationY(0f);
            v2.setUpdateListener(this.mUpdateListener);
            v5_1.play(v2);
            if((this.mContentAnimations) && this.mContentView != null) {
                this.mContentView.setTranslationY(v0);
                v5_1.play(ViewCompat.animate(this.mContentView).translationY(0f));
            }

            v5_1.setInterpolator(WindowDecorActionBar.sShowInterpolator);
            v5_1.setDuration(0xFA);
            v5_1.setListener(this.mShowListener);
            this.mCurrentShowAnim = v5_1;
            v5_1.start();
        }
        else {
        label_57:
            this.mContainerView.setAlpha(1f);
            this.mContainerView.setTranslationY(0f);
            if((this.mContentAnimations) && this.mContentView != null) {
                this.mContentView.setTranslationY(0f);
            }

            this.mShowListener.onAnimationEnd(null);
        }

        if(this.mOverlayLayout != null) {
            ViewCompat.requestApplyInsets(this.mOverlayLayout);
        }
    }

    public void enableContentAnimations(boolean arg1) {
        this.mContentAnimations = arg1;
    }

    private void ensureTabsExist() {
        if(this.mTabScrollView != null) {
            return;
        }

        ScrollingTabContainerView v0 = new ScrollingTabContainerView(this.mContext);
        if(this.mHasEmbeddedTabs) {
            v0.setVisibility(0);
            this.mDecorToolbar.setEmbeddedTabView(v0);
        }
        else {
            if(this.getNavigationMode() == 2) {
                v0.setVisibility(0);
                if(this.mOverlayLayout != null) {
                    ViewCompat.requestApplyInsets(this.mOverlayLayout);
                }
            }
            else {
                v0.setVisibility(8);
            }

            this.mContainerView.setTabContainer(v0);
        }

        this.mTabScrollView = v0;
    }

    public View getCustomView() {
        return this.mDecorToolbar.getCustomView();
    }

    private DecorToolbar getDecorToolbar(View arg4) {
        if((arg4 instanceof DecorToolbar)) {
            return arg4;
        }

        if((arg4 instanceof Toolbar)) {
            return ((Toolbar)arg4).getWrapper();
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("Can\'t make a decor toolbar out of ");
        v1.append(arg4);
        String v4 = v1.toString() != null ? arg4.getClass().getSimpleName() : "null";
        throw new IllegalStateException(v4);
    }

    public int getDisplayOptions() {
        return this.mDecorToolbar.getDisplayOptions();
    }

    public float getElevation() {
        return ViewCompat.getElevation(this.mContainerView);
    }

    public int getHeight() {
        return this.mContainerView.getHeight();
    }

    public int getHideOffset() {
        return this.mOverlayLayout.getActionBarHideOffset();
    }

    public int getNavigationItemCount() {
        switch(this.mDecorToolbar.getNavigationMode()) {
            case 1: {
                goto label_8;
            }
            case 2: {
                goto label_5;
            }
        }

        return 0;
    label_5:
        return this.mTabs.size();
    label_8:
        return this.mDecorToolbar.getDropdownItemCount();
    }

    public int getNavigationMode() {
        return this.mDecorToolbar.getNavigationMode();
    }

    public int getSelectedNavigationIndex() {
        int v1 = -1;
        switch(this.mDecorToolbar.getNavigationMode()) {
            case 1: {
                goto label_10;
            }
            case 2: {
                goto label_5;
            }
        }

        return v1;
    label_5:
        if(this.mSelectedTab != null) {
            v1 = this.mSelectedTab.getPosition();
        }

        return v1;
    label_10:
        return this.mDecorToolbar.getDropdownSelectedPosition();
    }

    public Tab getSelectedTab() {
        return this.mSelectedTab;
    }

    public CharSequence getSubtitle() {
        return this.mDecorToolbar.getSubtitle();
    }

    public Tab getTabAt(int arg2) {
        return this.mTabs.get(arg2);
    }

    public int getTabCount() {
        return this.mTabs.size();
    }

    public Context getThemedContext() {
        if(this.mThemedContext == null) {
            TypedValue v0 = new TypedValue();
            this.mContext.getTheme().resolveAttribute(attr.actionBarWidgetTheme, v0, true);
            int v0_1 = v0.resourceId;
            this.mThemedContext = v0_1 != 0 ? new ContextThemeWrapper(this.mContext, v0_1) : this.mContext;
        }

        return this.mThemedContext;
    }

    public CharSequence getTitle() {
        return this.mDecorToolbar.getTitle();
    }

    public boolean hasIcon() {
        return this.mDecorToolbar.hasIcon();
    }

    public boolean hasLogo() {
        return this.mDecorToolbar.hasLogo();
    }

    public void hide() {
        if(!this.mHiddenByApp) {
            this.mHiddenByApp = true;
            this.updateVisibility(false);
        }
    }

    private void hideForActionMode() {
        if(this.mShowingForMode) {
            this.mShowingForMode = false;
            if(this.mOverlayLayout != null) {
                this.mOverlayLayout.setShowingForActionMode(false);
            }

            this.updateVisibility(false);
        }
    }

    public void hideForSystem() {
        if(!this.mHiddenBySystem) {
            this.mHiddenBySystem = true;
            this.updateVisibility(true);
        }
    }

    private void init(View arg6) {
        this.mOverlayLayout = arg6.findViewById(id.decor_content_parent);
        if(this.mOverlayLayout != null) {
            this.mOverlayLayout.setActionBarVisibilityCallback(((ActionBarVisibilityCallback)this));
        }

        this.mDecorToolbar = this.getDecorToolbar(arg6.findViewById(id.action_bar));
        this.mContextView = arg6.findViewById(id.action_context_bar);
        this.mContainerView = arg6.findViewById(id.action_bar_container);
        if(this.mDecorToolbar != null && this.mContextView != null) {
            if(this.mContainerView == null) {
            }
            else {
                this.mContext = this.mDecorToolbar.getContext();
                int v6 = (this.mDecorToolbar.getDisplayOptions() & 4) != 0 ? 1 : 0;
                if(v6 != 0) {
                    this.mDisplayHomeAsUpSet = true;
                }

                ActionBarPolicy v2 = ActionBarPolicy.get(this.mContext);
                boolean v6_1 = (v2.enableHomeButtonByDefault()) || v6 != 0 ? true : false;
                this.setHomeButtonEnabled(v6_1);
                this.setHasEmbeddedTabs(v2.hasEmbeddedTabs());
                TypedArray v6_2 = this.mContext.obtainStyledAttributes(null, styleable.ActionBar, attr.actionBarStyle, 0);
                if(v6_2.getBoolean(styleable.ActionBar_hideOnContentScroll, false)) {
                    this.setHideOnContentScrollEnabled(true);
                }

                int v0 = v6_2.getDimensionPixelSize(styleable.ActionBar_elevation, 0);
                if(v0 != 0) {
                    this.setElevation(((float)v0));
                }

                v6_2.recycle();
                return;
            }
        }

        StringBuilder v0_1 = new StringBuilder();
        v0_1.append(this.getClass().getSimpleName());
        v0_1.append(" can only be used ");
        v0_1.append("with a compatible window decor layout");
        throw new IllegalStateException(v0_1.toString());
    }

    public boolean isHideOnContentScrollEnabled() {
        return this.mOverlayLayout.isHideOnContentScrollEnabled();
    }

    public boolean isShowing() {
        boolean v0_1;
        int v0 = this.getHeight();
        if(this.mNowShowing) {
            if(v0 != 0 && this.getHideOffset() >= v0) {
                goto label_8;
            }

            v0_1 = true;
        }
        else {
        label_8:
            v0_1 = false;
        }

        return v0_1;
    }

    public boolean isTitleTruncated() {
        boolean v0 = this.mDecorToolbar == null || !this.mDecorToolbar.isTitleTruncated() ? false : true;
        return v0;
    }

    public Tab newTab() {
        return new TabImpl(this);
    }

    public void onConfigurationChanged(Configuration arg1) {
        this.setHasEmbeddedTabs(ActionBarPolicy.get(this.mContext).hasEmbeddedTabs());
    }

    public void onContentScrollStarted() {
        if(this.mCurrentShowAnim != null) {
            this.mCurrentShowAnim.cancel();
            this.mCurrentShowAnim = null;
        }
    }

    public void onContentScrollStopped() {
    }

    public boolean onKeyShortcut(int arg5, KeyEvent arg6) {
        if(this.mActionMode == null) {
            return 0;
        }

        Menu v0 = this.mActionMode.getMenu();
        if(v0 != null) {
            int v2 = arg6 != null ? arg6.getDeviceId() : -1;
            boolean v3 = true;
            if(KeyCharacterMap.load(v2).getKeyboardType() != 1) {
            }
            else {
                v3 = false;
            }

            v0.setQwertyMode(v3);
            return v0.performShortcut(arg5, arg6, 0);
        }

        return 0;
    }

    public void onWindowVisibilityChanged(int arg1) {
        this.mCurWindowVisibility = arg1;
    }

    public void removeAllTabs() {
        this.cleanupTabs();
    }

    public void removeOnMenuVisibilityListener(OnMenuVisibilityListener arg2) {
        this.mMenuVisibilityListeners.remove(arg2);
    }

    public void removeTab(Tab arg1) {
        this.removeTabAt(arg1.getPosition());
    }

    public void removeTabAt(int arg5) {
        Object v5_1;
        if(this.mTabScrollView == null) {
            return;
        }

        int v0 = this.mSelectedTab != null ? this.mSelectedTab.getPosition() : this.mSavedTabPosition;
        this.mTabScrollView.removeTabAt(arg5);
        Object v1 = this.mTabs.remove(arg5);
        if(v1 != null) {
            ((TabImpl)v1).setPosition(-1);
        }

        int v1_1 = this.mTabs.size();
        int v2;
        for(v2 = arg5; v2 < v1_1; ++v2) {
            this.mTabs.get(v2).setPosition(v2);
        }

        if(v0 == arg5) {
            if(this.mTabs.isEmpty()) {
                Tab v5 = null;
            }
            else {
                v5_1 = this.mTabs.get(Math.max(0, arg5 - 1));
            }

            this.selectTab(((Tab)v5_1));
        }
    }

    public boolean requestFocus() {
        ViewGroup v0 = this.mDecorToolbar.getViewGroup();
        if(v0 != null && !v0.hasFocus()) {
            v0.requestFocus();
            return 1;
        }

        return 0;
    }

    public void selectTab(Tab arg4) {
        int v1 = -1;
        if(this.getNavigationMode() != 2) {
            if(arg4 != null) {
                v1 = arg4.getPosition();
            }

            this.mSavedTabPosition = v1;
            return;
        }

        FragmentTransaction v0 = !(this.mActivity instanceof FragmentActivity) || (this.mDecorToolbar.getViewGroup().isInEditMode()) ? null : this.mActivity.getSupportFragmentManager().beginTransaction().disallowAddToBackStack();
        if(this.mSelectedTab != arg4) {
            ScrollingTabContainerView v2 = this.mTabScrollView;
            if(arg4 != null) {
                v1 = arg4.getPosition();
            }

            v2.setTabSelected(v1);
            if(this.mSelectedTab != null) {
                this.mSelectedTab.getCallback().onTabUnselected(this.mSelectedTab, v0);
            }

            this.mSelectedTab = ((TabImpl)arg4);
            if(this.mSelectedTab == null) {
                goto label_50;
            }

            this.mSelectedTab.getCallback().onTabSelected(this.mSelectedTab, v0);
        }
        else if(this.mSelectedTab != null) {
            this.mSelectedTab.getCallback().onTabReselected(this.mSelectedTab, v0);
            this.mTabScrollView.animateToTab(arg4.getPosition());
        }

    label_50:
        if(v0 != null && !v0.isEmpty()) {
            v0.commit();
        }
    }

    public void setBackgroundDrawable(Drawable arg2) {
        this.mContainerView.setPrimaryBackground(arg2);
    }

    public void setCustomView(int arg4) {
        this.setCustomView(LayoutInflater.from(this.getThemedContext()).inflate(arg4, this.mDecorToolbar.getViewGroup(), false));
    }

    public void setCustomView(View arg2) {
        this.mDecorToolbar.setCustomView(arg2);
    }

    public void setCustomView(View arg1, LayoutParams arg2) {
        arg1.setLayoutParams(((ViewGroup$LayoutParams)arg2));
        this.mDecorToolbar.setCustomView(arg1);
    }

    public void setDefaultDisplayHomeAsUpEnabled(boolean arg2) {
        if(!this.mDisplayHomeAsUpSet) {
            this.setDisplayHomeAsUpEnabled(arg2);
        }
    }

    public void setDisplayHomeAsUpEnabled(boolean arg2) {
        int v0 = 4;
        int v2 = arg2 ? 4 : 0;
        this.setDisplayOptions(v2, v0);
    }

    public void setDisplayOptions(int arg3, int arg4) {
        int v0 = this.mDecorToolbar.getDisplayOptions();
        if((arg4 & 4) != 0) {
            this.mDisplayHomeAsUpSet = true;
        }

        this.mDecorToolbar.setDisplayOptions(arg3 & arg4 | (arg4 ^ -1) & v0);
    }

    public void setDisplayOptions(int arg2) {
        if((arg2 & 4) != 0) {
            this.mDisplayHomeAsUpSet = true;
        }

        this.mDecorToolbar.setDisplayOptions(arg2);
    }

    public void setDisplayShowCustomEnabled(boolean arg2) {
        int v0 = 16;
        int v2 = arg2 ? 16 : 0;
        this.setDisplayOptions(v2, v0);
    }

    public void setDisplayShowHomeEnabled(boolean arg2) {
        int v0 = 2;
        int v2 = arg2 ? 2 : 0;
        this.setDisplayOptions(v2, v0);
    }

    public void setDisplayShowTitleEnabled(boolean arg2) {
        int v0 = 8;
        int v2 = arg2 ? 8 : 0;
        this.setDisplayOptions(v2, v0);
    }

    public void setDisplayUseLogoEnabled(boolean arg2) {
        this.setDisplayOptions(((int)arg2), 1);
    }

    public void setElevation(float arg2) {
        ViewCompat.setElevation(this.mContainerView, arg2);
    }

    private void setHasEmbeddedTabs(boolean arg5) {
        this.mHasEmbeddedTabs = arg5;
        ScrollingTabContainerView v0 = null;
        if(!this.mHasEmbeddedTabs) {
            this.mDecorToolbar.setEmbeddedTabView(v0);
            this.mContainerView.setTabContainer(this.mTabScrollView);
        }
        else {
            this.mContainerView.setTabContainer(v0);
            this.mDecorToolbar.setEmbeddedTabView(this.mTabScrollView);
        }

        boolean v1 = true;
        int v5 = this.getNavigationMode() == 2 ? 1 : 0;
        if(this.mTabScrollView != null) {
            if(v5 != 0) {
                this.mTabScrollView.setVisibility(0);
                if(this.mOverlayLayout != null) {
                    ViewCompat.requestApplyInsets(this.mOverlayLayout);
                }
            }
            else {
                this.mTabScrollView.setVisibility(8);
            }
        }

        DecorToolbar v0_1 = this.mDecorToolbar;
        boolean v3 = (this.mHasEmbeddedTabs) || v5 == 0 ? false : true;
        v0_1.setCollapsible(v3);
        ActionBarOverlayLayout v0_2 = this.mOverlayLayout;
        if((this.mHasEmbeddedTabs) || v5 == 0) {
            v1 = false;
        }
        else {
        }

        v0_2.setHasNonEmbeddedTabs(v1);
    }

    public void setHideOffset(int arg2) {
        if(arg2 != 0 && !this.mOverlayLayout.isInOverlayMode()) {
            throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to set a non-zero hide offset");
        }

        this.mOverlayLayout.setActionBarHideOffset(arg2);
    }

    public void setHideOnContentScrollEnabled(boolean arg2) {
        if((arg2) && !this.mOverlayLayout.isInOverlayMode()) {
            throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to enable hide on content scroll");
        }

        this.mHideOnContentScroll = arg2;
        this.mOverlayLayout.setHideOnContentScrollEnabled(arg2);
    }

    public void setHomeActionContentDescription(int arg2) {
        this.mDecorToolbar.setNavigationContentDescription(arg2);
    }

    public void setHomeActionContentDescription(CharSequence arg2) {
        this.mDecorToolbar.setNavigationContentDescription(arg2);
    }

    public void setHomeAsUpIndicator(int arg2) {
        this.mDecorToolbar.setNavigationIcon(arg2);
    }

    public void setHomeAsUpIndicator(Drawable arg2) {
        this.mDecorToolbar.setNavigationIcon(arg2);
    }

    public void setHomeButtonEnabled(boolean arg2) {
        this.mDecorToolbar.setHomeButtonEnabled(arg2);
    }

    public void setIcon(int arg2) {
        this.mDecorToolbar.setIcon(arg2);
    }

    public void setIcon(Drawable arg2) {
        this.mDecorToolbar.setIcon(arg2);
    }

    public void setListNavigationCallbacks(SpinnerAdapter arg3, OnNavigationListener arg4) {
        this.mDecorToolbar.setDropdownParams(arg3, new NavItemSelectedListener(arg4));
    }

    public void setLogo(int arg2) {
        this.mDecorToolbar.setLogo(arg2);
    }

    public void setLogo(Drawable arg2) {
        this.mDecorToolbar.setLogo(arg2);
    }

    public void setNavigationMode(int arg6) {
        int v0 = this.mDecorToolbar.getNavigationMode();
        int v1 = 2;
        if(v0 != v1) {
        }
        else {
            this.mSavedTabPosition = this.getSelectedNavigationIndex();
            this.selectTab(null);
            this.mTabScrollView.setVisibility(8);
        }

        if(v0 != arg6 && !this.mHasEmbeddedTabs && this.mOverlayLayout != null) {
            ViewCompat.requestApplyInsets(this.mOverlayLayout);
        }

        this.mDecorToolbar.setNavigationMode(arg6);
        boolean v0_1 = false;
        if(arg6 != v1) {
        }
        else {
            this.ensureTabsExist();
            this.mTabScrollView.setVisibility(0);
            int v3 = -1;
            if(this.mSavedTabPosition != v3) {
                this.setSelectedNavigationItem(this.mSavedTabPosition);
                this.mSavedTabPosition = v3;
            }
        }

        DecorToolbar v2 = this.mDecorToolbar;
        boolean v4 = arg6 != v1 || (this.mHasEmbeddedTabs) ? false : true;
        v2.setCollapsible(v4);
        ActionBarOverlayLayout v2_1 = this.mOverlayLayout;
        if(arg6 == v1 && !this.mHasEmbeddedTabs) {
            v0_1 = true;
        }

        v2_1.setHasNonEmbeddedTabs(v0_1);
    }

    public void setSelectedNavigationItem(int arg2) {
        switch(this.mDecorToolbar.getNavigationMode()) {
            case 1: {
                goto label_11;
            }
            case 2: {
                goto label_7;
            }
        }

        throw new IllegalStateException("setSelectedNavigationIndex not valid for current navigation mode");
    label_7:
        this.selectTab(this.mTabs.get(arg2));
        return;
    label_11:
        this.mDecorToolbar.setDropdownSelectedPosition(arg2);
    }

    public void setShowHideAnimationEnabled(boolean arg1) {
        this.mShowHideAnimationEnabled = arg1;
        if(!arg1 && this.mCurrentShowAnim != null) {
            this.mCurrentShowAnim.cancel();
        }
    }

    public void setSplitBackgroundDrawable(Drawable arg1) {
    }

    public void setStackedBackgroundDrawable(Drawable arg2) {
        this.mContainerView.setStackedBackground(arg2);
    }

    public void setSubtitle(int arg2) {
        this.setSubtitle(this.mContext.getString(arg2));
    }

    public void setSubtitle(CharSequence arg2) {
        this.mDecorToolbar.setSubtitle(arg2);
    }

    public void setTitle(int arg2) {
        this.setTitle(this.mContext.getString(arg2));
    }

    public void setTitle(CharSequence arg2) {
        this.mDecorToolbar.setTitle(arg2);
    }

    public void setWindowTitle(CharSequence arg2) {
        this.mDecorToolbar.setWindowTitle(arg2);
    }

    private boolean shouldAnimateContextView() {
        return ViewCompat.isLaidOut(this.mContainerView);
    }

    public void show() {
        if(this.mHiddenByApp) {
            this.mHiddenByApp = false;
            this.updateVisibility(false);
        }
    }

    private void showForActionMode() {
        if(!this.mShowingForMode) {
            this.mShowingForMode = true;
            if(this.mOverlayLayout != null) {
                this.mOverlayLayout.setShowingForActionMode(true);
            }

            this.updateVisibility(false);
        }
    }

    public void showForSystem() {
        if(this.mHiddenBySystem) {
            this.mHiddenBySystem = false;
            this.updateVisibility(true);
        }
    }

    public ActionMode startActionMode(android.support.v7.view.ActionMode$Callback arg3) {
        if(this.mActionMode != null) {
            this.mActionMode.finish();
        }

        this.mOverlayLayout.setHideOnContentScrollEnabled(false);
        this.mContextView.killMode();
        ActionModeImpl v0 = new ActionModeImpl(this, this.mContextView.getContext(), arg3);
        if(v0.dispatchOnCreate()) {
            this.mActionMode = v0;
            v0.invalidate();
            this.mContextView.initForMode(((ActionMode)v0));
            this.animateToMode(true);
            this.mContextView.sendAccessibilityEvent(0x20);
            return ((ActionMode)v0);
        }

        return null;
    }

    private void updateVisibility(boolean arg4) {
        if(WindowDecorActionBar.checkShowingFlags(this.mHiddenByApp, this.mHiddenBySystem, this.mShowingForMode)) {
            if(!this.mNowShowing) {
                this.mNowShowing = true;
                this.doShow(arg4);
            }
        }
        else if(this.mNowShowing) {
            this.mNowShowing = false;
            this.doHide(arg4);
        }
    }
}

