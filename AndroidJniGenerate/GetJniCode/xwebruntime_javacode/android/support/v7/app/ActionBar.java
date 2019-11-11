package android.support.v7.app;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.annotation.StringRes;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.appcompat.R$styleable;
import android.support.v7.view.ActionMode$Callback;
import android.support.v7.view.ActionMode;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup$MarginLayoutParams;
import android.widget.SpinnerAdapter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public abstract class ActionBar {
    @RestrictTo(value={Scope.LIBRARY_GROUP}) @Retention(value=RetentionPolicy.SOURCE) @public interface DisplayOptions {
    }

    public class LayoutParams extends ViewGroup$MarginLayoutParams {
        public int gravity;

        public LayoutParams(int arg3) {
            this(-2, -1, arg3);
        }

        public LayoutParams(int arg1, int arg2, int arg3) {
            super(arg1, arg2);
            this.gravity = 0;
            this.gravity = arg3;
        }

        public LayoutParams(int arg1, int arg2) {
            super(arg1, arg2);
            this.gravity = 0;
            this.gravity = 0x800013;
        }

        public LayoutParams(@NonNull Context arg3, AttributeSet arg4) {
            super(arg3, arg4);
            this.gravity = 0;
            TypedArray v3 = arg3.obtainStyledAttributes(arg4, styleable.ActionBarLayout);
            this.gravity = v3.getInt(styleable.ActionBarLayout_android_layout_gravity, 0);
            v3.recycle();
        }

        public LayoutParams(LayoutParams arg2) {
            super(((ViewGroup$MarginLayoutParams)arg2));
            this.gravity = 0;
            this.gravity = arg2.gravity;
        }

        public LayoutParams(ViewGroup$LayoutParams arg1) {
            super(arg1);
            this.gravity = 0;
        }
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) @Retention(value=RetentionPolicy.SOURCE) @public interface NavigationMode {
    }

    public interface OnMenuVisibilityListener {
        void onMenuVisibilityChanged(boolean arg1);
    }

    @Deprecated public interface OnNavigationListener {
        boolean onNavigationItemSelected(int arg1, long arg2);
    }

    @Deprecated public abstract class Tab {
        public static final int INVALID_POSITION = -1;

        public Tab() {
            super();
        }

        public abstract CharSequence getContentDescription();

        public abstract View getCustomView();

        public abstract Drawable getIcon();

        public abstract int getPosition();

        public abstract Object getTag();

        public abstract CharSequence getText();

        public abstract void select();

        public abstract Tab setContentDescription(@StringRes int arg1);

        public abstract Tab setContentDescription(CharSequence arg1);

        public abstract Tab setCustomView(int arg1);

        public abstract Tab setCustomView(View arg1);

        public abstract Tab setIcon(@DrawableRes int arg1);

        public abstract Tab setIcon(Drawable arg1);

        public abstract Tab setTabListener(TabListener arg1);

        public abstract Tab setTag(Object arg1);

        public abstract Tab setText(int arg1);

        public abstract Tab setText(CharSequence arg1);
    }

    @Deprecated public interface TabListener {
        void onTabReselected(Tab arg1, FragmentTransaction arg2);

        void onTabSelected(Tab arg1, FragmentTransaction arg2);

        void onTabUnselected(Tab arg1, FragmentTransaction arg2);
    }

    public static final int DISPLAY_HOME_AS_UP = 4;
    public static final int DISPLAY_SHOW_CUSTOM = 16;
    public static final int DISPLAY_SHOW_HOME = 2;
    public static final int DISPLAY_SHOW_TITLE = 8;
    public static final int DISPLAY_USE_LOGO = 1;
    @Deprecated public static final int NAVIGATION_MODE_LIST = 1;
    @Deprecated public static final int NAVIGATION_MODE_STANDARD = 0;
    @Deprecated public static final int NAVIGATION_MODE_TABS = 2;

    public ActionBar() {
        super();
    }

    public abstract void addOnMenuVisibilityListener(OnMenuVisibilityListener arg1);

    @Deprecated public abstract void addTab(Tab arg1);

    @Deprecated public abstract void addTab(Tab arg1, int arg2);

    @Deprecated public abstract void addTab(Tab arg1, int arg2, boolean arg3);

    @Deprecated public abstract void addTab(Tab arg1, boolean arg2);

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public boolean closeOptionsMenu() {
        return 0;
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public boolean collapseActionView() {
        return 0;
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public void dispatchMenuVisibilityChanged(boolean arg1) {
    }

    public abstract View getCustomView();

    public abstract int getDisplayOptions();

    public float getElevation() {
        return 0;
    }

    public abstract int getHeight();

    public int getHideOffset() {
        return 0;
    }

    @Deprecated public abstract int getNavigationItemCount();

    @Deprecated public abstract int getNavigationMode();

    @Deprecated public abstract int getSelectedNavigationIndex();

    @Nullable @Deprecated public abstract Tab getSelectedTab();

    @Nullable public abstract CharSequence getSubtitle();

    @Deprecated public abstract Tab getTabAt(int arg1);

    @Deprecated public abstract int getTabCount();

    public Context getThemedContext() {
        return null;
    }

    @Nullable public abstract CharSequence getTitle();

    public abstract void hide();

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public boolean invalidateOptionsMenu() {
        return 0;
    }

    public boolean isHideOnContentScrollEnabled() {
        return 0;
    }

    public abstract boolean isShowing();

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public boolean isTitleTruncated() {
        return 0;
    }

    @Deprecated public abstract Tab newTab();

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public void onConfigurationChanged(Configuration arg1) {
    }

    void onDestroy() {
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public boolean onKeyShortcut(int arg1, KeyEvent arg2) {
        return 0;
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public boolean onMenuKeyEvent(KeyEvent arg1) {
        return 0;
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public boolean openOptionsMenu() {
        return 0;
    }

    @Deprecated public abstract void removeAllTabs();

    public abstract void removeOnMenuVisibilityListener(OnMenuVisibilityListener arg1);

    @Deprecated public abstract void removeTab(Tab arg1);

    @Deprecated public abstract void removeTabAt(int arg1);

    @RestrictTo(value={Scope.LIBRARY_GROUP}) boolean requestFocus() {
        return 0;
    }

    @Deprecated public abstract void selectTab(Tab arg1);

    public abstract void setBackgroundDrawable(@Nullable Drawable arg1);

    public abstract void setCustomView(int arg1);

    public abstract void setCustomView(View arg1);

    public abstract void setCustomView(View arg1, LayoutParams arg2);

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public void setDefaultDisplayHomeAsUpEnabled(boolean arg1) {
    }

    public abstract void setDisplayHomeAsUpEnabled(boolean arg1);

    public abstract void setDisplayOptions(int arg1);

    public abstract void setDisplayOptions(int arg1, int arg2);

    public abstract void setDisplayShowCustomEnabled(boolean arg1);

    public abstract void setDisplayShowHomeEnabled(boolean arg1);

    public abstract void setDisplayShowTitleEnabled(boolean arg1);

    public abstract void setDisplayUseLogoEnabled(boolean arg1);

    public void setElevation(float arg2) {
        if(arg2 != 0f) {
            throw new UnsupportedOperationException("Setting a non-zero elevation is not supported in this action bar configuration.");
        }
    }

    public void setHideOffset(int arg2) {
        if(arg2 != 0) {
            throw new UnsupportedOperationException("Setting an explicit action bar hide offset is not supported in this action bar configuration.");
        }
    }

    public void setHideOnContentScrollEnabled(boolean arg2) {
        if(arg2) {
            throw new UnsupportedOperationException("Hide on content scroll is not supported in this action bar configuration.");
        }
    }

    public void setHomeActionContentDescription(@StringRes int arg1) {
    }

    public void setHomeActionContentDescription(@Nullable CharSequence arg1) {
    }

    public void setHomeAsUpIndicator(@DrawableRes int arg1) {
    }

    public void setHomeAsUpIndicator(@Nullable Drawable arg1) {
    }

    public void setHomeButtonEnabled(boolean arg1) {
    }

    public abstract void setIcon(@DrawableRes int arg1);

    public abstract void setIcon(Drawable arg1);

    @Deprecated public abstract void setListNavigationCallbacks(SpinnerAdapter arg1, OnNavigationListener arg2);

    public abstract void setLogo(@DrawableRes int arg1);

    public abstract void setLogo(Drawable arg1);

    @Deprecated public abstract void setNavigationMode(int arg1);

    @Deprecated public abstract void setSelectedNavigationItem(int arg1);

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public void setShowHideAnimationEnabled(boolean arg1) {
    }

    public void setSplitBackgroundDrawable(Drawable arg1) {
    }

    public void setStackedBackgroundDrawable(Drawable arg1) {
    }

    public abstract void setSubtitle(int arg1);

    public abstract void setSubtitle(CharSequence arg1);

    public abstract void setTitle(@StringRes int arg1);

    public abstract void setTitle(CharSequence arg1);

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public void setWindowTitle(CharSequence arg1) {
    }

    public abstract void show();

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public ActionMode startActionMode(Callback arg1) {
        return null;
    }
}

