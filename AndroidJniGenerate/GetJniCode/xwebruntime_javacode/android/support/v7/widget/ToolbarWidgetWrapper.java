package android.support.v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.v7.appcompat.R$attr;
import android.support.v7.appcompat.R$drawable;
import android.support.v7.appcompat.R$id;
import android.support.v7.appcompat.R$string;
import android.support.v7.appcompat.R$styleable;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.view.menu.ActionMenuItem;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPresenter$Callback;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View$OnClickListener;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup;
import android.view.Window$Callback;
import android.widget.AdapterView$OnItemSelectedListener;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

@RestrictTo(value={Scope.LIBRARY_GROUP}) public class ToolbarWidgetWrapper implements DecorToolbar {
    private static final int AFFECTS_LOGO_MASK = 3;
    private static final long DEFAULT_FADE_DURATION_MS = 200;
    private static final String TAG = "ToolbarWidgetWrapper";
    private ActionMenuPresenter mActionMenuPresenter;
    private View mCustomView;
    private int mDefaultNavigationContentDescription;
    private Drawable mDefaultNavigationIcon;
    private int mDisplayOpts;
    private CharSequence mHomeDescription;
    private Drawable mIcon;
    private Drawable mLogo;
    boolean mMenuPrepared;
    private Drawable mNavIcon;
    private int mNavigationMode;
    private Spinner mSpinner;
    private CharSequence mSubtitle;
    private View mTabView;
    CharSequence mTitle;
    private boolean mTitleSet;
    Toolbar mToolbar;
    Window$Callback mWindowCallback;

    public ToolbarWidgetWrapper(Toolbar arg3, boolean arg4) {
        this(arg3, arg4, string.abc_action_bar_up_description, drawable.abc_ic_ab_back_material);
    }

    public ToolbarWidgetWrapper(Toolbar arg4, boolean arg5, int arg6, int arg7) {
        super();
        this.mNavigationMode = 0;
        this.mDefaultNavigationContentDescription = 0;
        this.mToolbar = arg4;
        this.mTitle = arg4.getTitle();
        this.mSubtitle = arg4.getSubtitle();
        boolean v0 = this.mTitle != null ? true : false;
        this.mTitleSet = v0;
        this.mNavIcon = arg4.getNavigationIcon();
        TintTypedArray v4 = TintTypedArray.obtainStyledAttributes(arg4.getContext(), null, styleable.ActionBar, attr.actionBarStyle, 0);
        this.mDefaultNavigationIcon = v4.getDrawable(styleable.ActionBar_homeAsUpIndicator);
        if(arg5) {
            CharSequence v5 = v4.getText(styleable.ActionBar_title);
            if(!TextUtils.isEmpty(v5)) {
                this.setTitle(v5);
            }

            v5 = v4.getText(styleable.ActionBar_subtitle);
            if(!TextUtils.isEmpty(v5)) {
                this.setSubtitle(v5);
            }

            Drawable v5_1 = v4.getDrawable(styleable.ActionBar_logo);
            if(v5_1 != null) {
                this.setLogo(v5_1);
            }

            v5_1 = v4.getDrawable(styleable.ActionBar_icon);
            if(v5_1 != null) {
                this.setIcon(v5_1);
            }

            if(this.mNavIcon == null && this.mDefaultNavigationIcon != null) {
                this.setNavigationIcon(this.mDefaultNavigationIcon);
            }

            this.setDisplayOptions(v4.getInt(styleable.ActionBar_displayOptions, 0));
            int v5_2 = v4.getResourceId(styleable.ActionBar_customNavigationLayout, 0);
            if(v5_2 != 0) {
                this.setCustomView(LayoutInflater.from(this.mToolbar.getContext()).inflate(v5_2, this.mToolbar, false));
                this.setDisplayOptions(this.mDisplayOpts | 16);
            }

            v5_2 = v4.getLayoutDimension(styleable.ActionBar_height, 0);
            if(v5_2 > 0) {
                ViewGroup$LayoutParams v0_1 = this.mToolbar.getLayoutParams();
                v0_1.height = v5_2;
                this.mToolbar.setLayoutParams(v0_1);
            }

            v5_2 = v4.getDimensionPixelOffset(styleable.ActionBar_contentInsetStart, -1);
            int v0_2 = v4.getDimensionPixelOffset(styleable.ActionBar_contentInsetEnd, -1);
            if(v5_2 >= 0 || v0_2 >= 0) {
                this.mToolbar.setContentInsetsRelative(Math.max(v5_2, 0), Math.max(v0_2, 0));
            }

            v5_2 = v4.getResourceId(styleable.ActionBar_titleTextStyle, 0);
            if(v5_2 != 0) {
                this.mToolbar.setTitleTextAppearance(this.mToolbar.getContext(), v5_2);
            }

            v5_2 = v4.getResourceId(styleable.ActionBar_subtitleTextStyle, 0);
            if(v5_2 != 0) {
                this.mToolbar.setSubtitleTextAppearance(this.mToolbar.getContext(), v5_2);
            }

            v5_2 = v4.getResourceId(styleable.ActionBar_popupTheme, 0);
            if(v5_2 == 0) {
                goto label_106;
            }

            this.mToolbar.setPopupTheme(v5_2);
        }
        else {
            this.mDisplayOpts = this.detectDisplayOptions();
        }

    label_106:
        v4.recycle();
        this.setDefaultNavigationContentDescription(arg6);
        this.mHomeDescription = this.mToolbar.getNavigationContentDescription();
        this.mToolbar.setNavigationOnClickListener(new View$OnClickListener() {
            final ActionMenuItem mNavItem;

            public void onClick(View arg3) {
                if(ToolbarWidgetWrapper.this.mWindowCallback != null && (ToolbarWidgetWrapper.this.mMenuPrepared)) {
                    ToolbarWidgetWrapper.this.mWindowCallback.onMenuItemSelected(0, this.mNavItem);
                }
            }
        });
    }

    public void animateToVisibility(int arg3) {
        ViewPropertyAnimatorCompat v3 = this.setupAnimatorToVisibility(arg3, 200);
        if(v3 != null) {
            v3.start();
        }
    }

    public boolean canShowOverflowMenu() {
        return this.mToolbar.canShowOverflowMenu();
    }

    public void collapseActionView() {
        this.mToolbar.collapseActionView();
    }

    private int detectDisplayOptions() {
        int v0;
        if(this.mToolbar.getNavigationIcon() != null) {
            v0 = 15;
            this.mDefaultNavigationIcon = this.mToolbar.getNavigationIcon();
        }
        else {
            v0 = 11;
        }

        return v0;
    }

    public void dismissPopupMenus() {
        this.mToolbar.dismissPopupMenus();
    }

    private void ensureSpinner() {
        if(this.mSpinner == null) {
            this.mSpinner = new AppCompatSpinner(this.getContext(), null, attr.actionDropDownStyle);
            this.mSpinner.setLayoutParams(new LayoutParams(-2, -2, 0x800013));
        }
    }

    public Context getContext() {
        return this.mToolbar.getContext();
    }

    public View getCustomView() {
        return this.mCustomView;
    }

    public int getDisplayOptions() {
        return this.mDisplayOpts;
    }

    public int getDropdownItemCount() {
        int v0 = this.mSpinner != null ? this.mSpinner.getCount() : 0;
        return v0;
    }

    public int getDropdownSelectedPosition() {
        int v0 = this.mSpinner != null ? this.mSpinner.getSelectedItemPosition() : 0;
        return v0;
    }

    public int getHeight() {
        return this.mToolbar.getHeight();
    }

    public Menu getMenu() {
        return this.mToolbar.getMenu();
    }

    public int getNavigationMode() {
        return this.mNavigationMode;
    }

    public CharSequence getSubtitle() {
        return this.mToolbar.getSubtitle();
    }

    public CharSequence getTitle() {
        return this.mToolbar.getTitle();
    }

    public ViewGroup getViewGroup() {
        return this.mToolbar;
    }

    public int getVisibility() {
        return this.mToolbar.getVisibility();
    }

    public boolean hasEmbeddedTabs() {
        boolean v0 = this.mTabView != null ? true : false;
        return v0;
    }

    public boolean hasExpandedActionView() {
        return this.mToolbar.hasExpandedActionView();
    }

    public boolean hasIcon() {
        boolean v0 = this.mIcon != null ? true : false;
        return v0;
    }

    public boolean hasLogo() {
        boolean v0 = this.mLogo != null ? true : false;
        return v0;
    }

    public boolean hideOverflowMenu() {
        return this.mToolbar.hideOverflowMenu();
    }

    public void initIndeterminateProgress() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    public void initProgress() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    public boolean isOverflowMenuShowPending() {
        return this.mToolbar.isOverflowMenuShowPending();
    }

    public boolean isOverflowMenuShowing() {
        return this.mToolbar.isOverflowMenuShowing();
    }

    public boolean isTitleTruncated() {
        return this.mToolbar.isTitleTruncated();
    }

    public void restoreHierarchyState(SparseArray arg2) {
        this.mToolbar.restoreHierarchyState(arg2);
    }

    public void saveHierarchyState(SparseArray arg2) {
        this.mToolbar.saveHierarchyState(arg2);
    }

    public void setBackgroundDrawable(Drawable arg2) {
        ViewCompat.setBackground(this.mToolbar, arg2);
    }

    public void setCollapsible(boolean arg2) {
        this.mToolbar.setCollapsible(arg2);
    }

    public void setCustomView(View arg3) {
        if(this.mCustomView != null && (this.mDisplayOpts & 16) != 0) {
            this.mToolbar.removeView(this.mCustomView);
        }

        this.mCustomView = arg3;
        if(arg3 != null && (this.mDisplayOpts & 16) != 0) {
            this.mToolbar.addView(this.mCustomView);
        }
    }

    public void setDefaultNavigationContentDescription(int arg2) {
        if(arg2 == this.mDefaultNavigationContentDescription) {
            return;
        }

        this.mDefaultNavigationContentDescription = arg2;
        if(TextUtils.isEmpty(this.mToolbar.getNavigationContentDescription())) {
            this.setNavigationContentDescription(this.mDefaultNavigationContentDescription);
        }
    }

    public void setDefaultNavigationIcon(Drawable arg2) {
        if(this.mDefaultNavigationIcon != arg2) {
            this.mDefaultNavigationIcon = arg2;
            this.updateNavigationIcon();
        }
    }

    public void setDisplayOptions(int arg4) {
        int v0 = this.mDisplayOpts ^ arg4;
        this.mDisplayOpts = arg4;
        if(v0 != 0) {
            if((v0 & 4) != 0) {
                if((arg4 & 4) != 0) {
                    this.updateHomeAccessibility();
                }

                this.updateNavigationIcon();
            }

            if((v0 & 3) != 0) {
                this.updateToolbarLogo();
            }

            if((v0 & 8) != 0) {
                if((arg4 & 8) != 0) {
                    this.mToolbar.setTitle(this.mTitle);
                    this.mToolbar.setSubtitle(this.mSubtitle);
                }
                else {
                    this.mToolbar.setTitle(null);
                    this.mToolbar.setSubtitle(null);
                }
            }

            if((v0 & 16) == 0) {
                return;
            }

            if(this.mCustomView == null) {
                return;
            }

            if((arg4 & 16) != 0) {
                this.mToolbar.addView(this.mCustomView);
                return;
            }

            this.mToolbar.removeView(this.mCustomView);
        }
    }

    public void setDropdownParams(SpinnerAdapter arg2, AdapterView$OnItemSelectedListener arg3) {
        this.ensureSpinner();
        this.mSpinner.setAdapter(arg2);
        this.mSpinner.setOnItemSelectedListener(arg3);
    }

    public void setDropdownSelectedPosition(int arg2) {
        if(this.mSpinner == null) {
            throw new IllegalStateException("Can\'t set dropdown selected position without an adapter");
        }

        this.mSpinner.setSelection(arg2);
    }

    public void setEmbeddedTabView(ScrollingTabContainerView arg4) {
        if(this.mTabView != null && this.mTabView.getParent() == this.mToolbar) {
            this.mToolbar.removeView(this.mTabView);
        }

        this.mTabView = ((View)arg4);
        if(arg4 != null && this.mNavigationMode == 2) {
            this.mToolbar.addView(this.mTabView, 0);
            ViewGroup$LayoutParams v0 = this.mTabView.getLayoutParams();
            ((LayoutParams)v0).width = -2;
            ((LayoutParams)v0).height = -2;
            ((LayoutParams)v0).gravity = 0x800053;
            arg4.setAllowCollapse(true);
        }
    }

    public void setHomeButtonEnabled(boolean arg1) {
    }

    public void setIcon(Drawable arg1) {
        this.mIcon = arg1;
        this.updateToolbarLogo();
    }

    public void setIcon(int arg2) {
        Drawable v2 = arg2 != 0 ? AppCompatResources.getDrawable(this.getContext(), arg2) : null;
        this.setIcon(v2);
    }

    public void setLogo(Drawable arg1) {
        this.mLogo = arg1;
        this.updateToolbarLogo();
    }

    public void setLogo(int arg2) {
        Drawable v2 = arg2 != 0 ? AppCompatResources.getDrawable(this.getContext(), arg2) : null;
        this.setLogo(v2);
    }

    public void setMenu(Menu arg3, Callback arg4) {
        if(this.mActionMenuPresenter == null) {
            this.mActionMenuPresenter = new ActionMenuPresenter(this.mToolbar.getContext());
            this.mActionMenuPresenter.setId(id.action_menu_presenter);
        }

        this.mActionMenuPresenter.setCallback(arg4);
        this.mToolbar.setMenu(((MenuBuilder)arg3), this.mActionMenuPresenter);
    }

    public void setMenuCallbacks(Callback arg2, android.support.v7.view.menu.MenuBuilder$Callback arg3) {
        this.mToolbar.setMenuCallbacks(arg2, arg3);
    }

    public void setMenuPrepared() {
        this.mMenuPrepared = true;
    }

    public void setNavigationContentDescription(int arg2) {
        String v2_1;
        if(arg2 == 0) {
            CharSequence v2 = null;
        }
        else {
            v2_1 = this.getContext().getString(arg2);
        }

        this.setNavigationContentDescription(((CharSequence)v2_1));
    }

    public void setNavigationContentDescription(CharSequence arg1) {
        this.mHomeDescription = arg1;
        this.updateHomeAccessibility();
    }

    public void setNavigationIcon(Drawable arg1) {
        this.mNavIcon = arg1;
        this.updateNavigationIcon();
    }

    public void setNavigationIcon(int arg2) {
        Drawable v2 = arg2 != 0 ? AppCompatResources.getDrawable(this.getContext(), arg2) : null;
        this.setNavigationIcon(v2);
    }

    public void setNavigationMode(int arg4) {
        int v0 = this.mNavigationMode;
        if(arg4 != v0) {
            switch(v0) {
                case 1: {
                    if(this.mSpinner == null) {
                    }
                    else if(this.mSpinner.getParent() == this.mToolbar) {
                        this.mToolbar.removeView(this.mSpinner);
                    }
                    else {
                    }

                    break;
                }
                case 2: {
                    if(this.mTabView == null) {
                    }
                    else if(this.mTabView.getParent() == this.mToolbar) {
                        this.mToolbar.removeView(this.mTabView);
                    }
                    else {
                    }

                    break;
                }
                default: {
                    break;
                }
            }

            this.mNavigationMode = arg4;
            switch(arg4) {
                case 0: {
                    return;
                }
                case 1: {
                    goto label_48;
                }
                case 2: {
                    goto label_35;
                }
            }

            StringBuilder v1 = new StringBuilder();
            v1.append("Invalid navigation mode ");
            v1.append(arg4);
            throw new IllegalArgumentException(v1.toString());
        label_35:
            if(this.mTabView != null) {
                this.mToolbar.addView(this.mTabView, 0);
                ViewGroup$LayoutParams v4 = this.mTabView.getLayoutParams();
                ((LayoutParams)v4).width = -2;
                ((LayoutParams)v4).height = -2;
                ((LayoutParams)v4).gravity = 0x800053;
                return;
            label_48:
                this.ensureSpinner();
                this.mToolbar.addView(this.mSpinner, 0);
            }
        }
    }

    public void setSubtitle(CharSequence arg2) {
        this.mSubtitle = arg2;
        if((this.mDisplayOpts & 8) != 0) {
            this.mToolbar.setSubtitle(arg2);
        }
    }

    public void setTitle(CharSequence arg2) {
        this.mTitleSet = true;
        this.setTitleInt(arg2);
    }

    private void setTitleInt(CharSequence arg2) {
        this.mTitle = arg2;
        if((this.mDisplayOpts & 8) != 0) {
            this.mToolbar.setTitle(arg2);
        }
    }

    public void setVisibility(int arg2) {
        this.mToolbar.setVisibility(arg2);
    }

    public void setWindowCallback(Window$Callback arg1) {
        this.mWindowCallback = arg1;
    }

    public void setWindowTitle(CharSequence arg2) {
        if(!this.mTitleSet) {
            this.setTitleInt(arg2);
        }
    }

    public ViewPropertyAnimatorCompat setupAnimatorToVisibility(int arg3, long arg4) {
        ViewPropertyAnimatorCompat v0 = ViewCompat.animate(this.mToolbar);
        float v1 = arg3 == 0 ? 1f : 0f;
        return v0.alpha(v1).setDuration(arg4).setListener(new ViewPropertyAnimatorListenerAdapter(arg3) {
            private boolean mCanceled;

            public void onAnimationCancel(View arg1) {
                this.mCanceled = true;
            }

            public void onAnimationEnd(View arg2) {
                if(!this.mCanceled) {
                    ToolbarWidgetWrapper.this.mToolbar.setVisibility(this.val$visibility);
                }
            }

            public void onAnimationStart(View arg2) {
                ToolbarWidgetWrapper.this.mToolbar.setVisibility(0);
            }
        });
    }

    public boolean showOverflowMenu() {
        return this.mToolbar.showOverflowMenu();
    }

    private void updateHomeAccessibility() {
        if((this.mDisplayOpts & 4) != 0) {
            if(TextUtils.isEmpty(this.mHomeDescription)) {
                this.mToolbar.setNavigationContentDescription(this.mDefaultNavigationContentDescription);
            }
            else {
                this.mToolbar.setNavigationContentDescription(this.mHomeDescription);
            }
        }
    }

    private void updateNavigationIcon() {
        if((this.mDisplayOpts & 4) != 0) {
            Toolbar v0 = this.mToolbar;
            Drawable v1 = this.mNavIcon != null ? this.mNavIcon : this.mDefaultNavigationIcon;
            v0.setNavigationIcon(v1);
        }
        else {
            this.mToolbar.setNavigationIcon(null);
        }
    }

    private void updateToolbarLogo() {
        Drawable v0;
        if((this.mDisplayOpts & 2) == 0) {
            v0 = null;
        }
        else if((this.mDisplayOpts & 1) == 0) {
            v0 = this.mIcon;
        }
        else if(this.mLogo != null) {
            v0 = this.mLogo;
        }
        else {
            v0 = this.mIcon;
        }

        this.mToolbar.setLogo(v0);
    }
}

