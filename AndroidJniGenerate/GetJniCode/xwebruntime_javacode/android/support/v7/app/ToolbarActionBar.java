package android.support.v7.app;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.view.WindowCallbackWrapper;
import android.support.v7.view.menu.ListMenuPresenter;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPresenter$Callback;
import android.support.v7.widget.DecorToolbar;
import android.support.v7.widget.Toolbar$OnMenuItemClickListener;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.ToolbarWidgetWrapper;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup;
import android.view.Window$Callback;
import android.widget.SpinnerAdapter;
import java.util.ArrayList;

class ToolbarActionBar extends ActionBar {
    class android.support.v7.app.ToolbarActionBar$1 implements Runnable {
        android.support.v7.app.ToolbarActionBar$1(ToolbarActionBar arg1) {
            ToolbarActionBar.this = arg1;
            super();
        }

        public void run() {
            ToolbarActionBar.this.populateOptionsMenu();
        }
    }

    class android.support.v7.app.ToolbarActionBar$2 implements OnMenuItemClickListener {
        android.support.v7.app.ToolbarActionBar$2(ToolbarActionBar arg1) {
            ToolbarActionBar.this = arg1;
            super();
        }

        public boolean onMenuItemClick(MenuItem arg3) {
            return ToolbarActionBar.this.mWindowCallback.onMenuItemSelected(0, arg3);
        }
    }

    final class ActionMenuPresenterCallback implements Callback {
        private boolean mClosingActionMenu;

        ActionMenuPresenterCallback(ToolbarActionBar arg1) {
            ToolbarActionBar.this = arg1;
            super();
        }

        public void onCloseMenu(MenuBuilder arg2, boolean arg3) {
            if(this.mClosingActionMenu) {
                return;
            }

            this.mClosingActionMenu = true;
            ToolbarActionBar.this.mDecorToolbar.dismissPopupMenus();
            if(ToolbarActionBar.this.mWindowCallback != null) {
                ToolbarActionBar.this.mWindowCallback.onPanelClosed(108, ((Menu)arg2));
            }

            this.mClosingActionMenu = false;
        }

        public boolean onOpenSubMenu(MenuBuilder arg3) {
            if(ToolbarActionBar.this.mWindowCallback != null) {
                ToolbarActionBar.this.mWindowCallback.onMenuOpened(108, ((Menu)arg3));
                return 1;
            }

            return 0;
        }
    }

    final class MenuBuilderCallback implements android.support.v7.view.menu.MenuBuilder$Callback {
        MenuBuilderCallback(ToolbarActionBar arg1) {
            ToolbarActionBar.this = arg1;
            super();
        }

        public boolean onMenuItemSelected(MenuBuilder arg1, MenuItem arg2) {
            return 0;
        }

        public void onMenuModeChange(MenuBuilder arg5) {
            if(ToolbarActionBar.this.mWindowCallback != null) {
                int v1 = 108;
                if(ToolbarActionBar.this.mDecorToolbar.isOverflowMenuShowing()) {
                    ToolbarActionBar.this.mWindowCallback.onPanelClosed(v1, ((Menu)arg5));
                }
                else if(ToolbarActionBar.this.mWindowCallback.onPreparePanel(0, null, ((Menu)arg5))) {
                    ToolbarActionBar.this.mWindowCallback.onMenuOpened(v1, ((Menu)arg5));
                }
            }
        }
    }

    class ToolbarCallbackWrapper extends WindowCallbackWrapper {
        public ToolbarCallbackWrapper(ToolbarActionBar arg1, Window$Callback arg2) {
            ToolbarActionBar.this = arg1;
            super(arg2);
        }

        public View onCreatePanelView(int arg2) {
            if(arg2 == 0) {
                return new View(ToolbarActionBar.this.mDecorToolbar.getContext());
            }

            return super.onCreatePanelView(arg2);
        }

        public boolean onPreparePanel(int arg1, View arg2, Menu arg3) {
            boolean v1 = super.onPreparePanel(arg1, arg2, arg3);
            if((v1) && !ToolbarActionBar.this.mToolbarMenuPrepared) {
                ToolbarActionBar.this.mDecorToolbar.setMenuPrepared();
                ToolbarActionBar.this.mToolbarMenuPrepared = true;
            }

            return v1;
        }
    }

    DecorToolbar mDecorToolbar;
    private boolean mLastMenuVisibility;
    private ListMenuPresenter mListMenuPresenter;
    private boolean mMenuCallbackSet;
    private final OnMenuItemClickListener mMenuClicker;
    private final Runnable mMenuInvalidator;
    private ArrayList mMenuVisibilityListeners;
    boolean mToolbarMenuPrepared;
    Window$Callback mWindowCallback;

    ToolbarActionBar(Toolbar arg3, CharSequence arg4, Window$Callback arg5) {
        super();
        this.mMenuVisibilityListeners = new ArrayList();
        this.mMenuInvalidator = new android.support.v7.app.ToolbarActionBar$1(this);
        this.mMenuClicker = new android.support.v7.app.ToolbarActionBar$2(this);
        this.mDecorToolbar = new ToolbarWidgetWrapper(arg3, false);
        this.mWindowCallback = new ToolbarCallbackWrapper(this, arg5);
        this.mDecorToolbar.setWindowCallback(this.mWindowCallback);
        arg3.setOnMenuItemClickListener(this.mMenuClicker);
        this.mDecorToolbar.setWindowTitle(arg4);
    }

    public void addOnMenuVisibilityListener(OnMenuVisibilityListener arg2) {
        this.mMenuVisibilityListeners.add(arg2);
    }

    public void addTab(Tab arg2) {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    public void addTab(Tab arg1, int arg2) {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    public void addTab(Tab arg1, int arg2, boolean arg3) {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    public void addTab(Tab arg1, boolean arg2) {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    public boolean closeOptionsMenu() {
        return this.mDecorToolbar.hideOverflowMenu();
    }

    public boolean collapseActionView() {
        if(this.mDecorToolbar.hasExpandedActionView()) {
            this.mDecorToolbar.collapseActionView();
            return 1;
        }

        return 0;
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

    public View getCustomView() {
        return this.mDecorToolbar.getCustomView();
    }

    public int getDisplayOptions() {
        return this.mDecorToolbar.getDisplayOptions();
    }

    public float getElevation() {
        return ViewCompat.getElevation(this.mDecorToolbar.getViewGroup());
    }

    public int getHeight() {
        return this.mDecorToolbar.getHeight();
    }

    private Menu getMenu() {
        if(!this.mMenuCallbackSet) {
            this.mDecorToolbar.setMenuCallbacks(new ActionMenuPresenterCallback(this), new MenuBuilderCallback(this));
            this.mMenuCallbackSet = true;
        }

        return this.mDecorToolbar.getMenu();
    }

    public int getNavigationItemCount() {
        return 0;
    }

    public int getNavigationMode() {
        return 0;
    }

    public int getSelectedNavigationIndex() {
        return -1;
    }

    public Tab getSelectedTab() {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    public CharSequence getSubtitle() {
        return this.mDecorToolbar.getSubtitle();
    }

    public Tab getTabAt(int arg2) {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    public int getTabCount() {
        return 0;
    }

    public Context getThemedContext() {
        return this.mDecorToolbar.getContext();
    }

    public CharSequence getTitle() {
        return this.mDecorToolbar.getTitle();
    }

    public Window$Callback getWrappedWindowCallback() {
        return this.mWindowCallback;
    }

    public void hide() {
        this.mDecorToolbar.setVisibility(8);
    }

    public boolean invalidateOptionsMenu() {
        this.mDecorToolbar.getViewGroup().removeCallbacks(this.mMenuInvalidator);
        ViewCompat.postOnAnimation(this.mDecorToolbar.getViewGroup(), this.mMenuInvalidator);
        return 1;
    }

    public boolean isShowing() {
        boolean v0 = this.mDecorToolbar.getVisibility() == 0 ? true : false;
        return v0;
    }

    public boolean isTitleTruncated() {
        return super.isTitleTruncated();
    }

    public Tab newTab() {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    public void onConfigurationChanged(Configuration arg1) {
        super.onConfigurationChanged(arg1);
    }

    void onDestroy() {
        this.mDecorToolbar.getViewGroup().removeCallbacks(this.mMenuInvalidator);
    }

    public boolean onKeyShortcut(int arg5, KeyEvent arg6) {
        Menu v0 = this.getMenu();
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

    public boolean onMenuKeyEvent(KeyEvent arg2) {
        if(arg2.getAction() == 1) {
            this.openOptionsMenu();
        }

        return 1;
    }

    public boolean openOptionsMenu() {
        return this.mDecorToolbar.showOverflowMenu();
    }

    void populateOptionsMenu() {
        Menu v0 = this.getMenu();
        View v2 = null;
        Menu v1 = (v0 instanceof MenuBuilder) ? v0 : ((Menu)v2);
        if(v1 != null) {
            ((MenuBuilder)v1).stopDispatchingItemsChanged();
        }

        try {
            v0.clear();
            if(!this.mWindowCallback.onCreatePanelMenu(0, v0) || !this.mWindowCallback.onPreparePanel(0, v2, v0)) {
                v0.clear();
            }
        }
        catch(Throwable v0_1) {
            if(v1 != null) {
                ((MenuBuilder)v1).startDispatchingItemsChanged();
            }

            throw v0_1;
        }

        if(v1 != null) {
            ((MenuBuilder)v1).startDispatchingItemsChanged();
        }
    }

    public void removeAllTabs() {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    public void removeOnMenuVisibilityListener(OnMenuVisibilityListener arg2) {
        this.mMenuVisibilityListeners.remove(arg2);
    }

    public void removeTab(Tab arg2) {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    public void removeTabAt(int arg2) {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    public boolean requestFocus() {
        ViewGroup v0 = this.mDecorToolbar.getViewGroup();
        if(v0 != null && !v0.hasFocus()) {
            v0.requestFocus();
            return 1;
        }

        return 0;
    }

    public void selectTab(Tab arg2) {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    public void setBackgroundDrawable(@Nullable Drawable arg2) {
        this.mDecorToolbar.setBackgroundDrawable(arg2);
    }

    public void setCustomView(int arg4) {
        this.setCustomView(LayoutInflater.from(this.mDecorToolbar.getContext()).inflate(arg4, this.mDecorToolbar.getViewGroup(), false));
    }

    public void setCustomView(View arg3) {
        this.setCustomView(arg3, new LayoutParams(-2, -2));
    }

    public void setCustomView(View arg1, LayoutParams arg2) {
        if(arg1 != null) {
            arg1.setLayoutParams(((ViewGroup$LayoutParams)arg2));
        }

        this.mDecorToolbar.setCustomView(arg1);
    }

    public void setDefaultDisplayHomeAsUpEnabled(boolean arg1) {
    }

    public void setDisplayHomeAsUpEnabled(boolean arg2) {
        int v0 = 4;
        int v2 = arg2 ? 4 : 0;
        this.setDisplayOptions(v2, v0);
    }

    public void setDisplayOptions(int arg3, int arg4) {
        this.mDecorToolbar.setDisplayOptions(arg3 & arg4 | (arg4 ^ -1) & this.mDecorToolbar.getDisplayOptions());
    }

    public void setDisplayOptions(int arg2) {
        this.setDisplayOptions(arg2, -1);
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
        ViewCompat.setElevation(this.mDecorToolbar.getViewGroup(), arg2);
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

    public void setHomeButtonEnabled(boolean arg1) {
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

    public void setNavigationMode(int arg2) {
        if(arg2 == 2) {
            throw new IllegalArgumentException("Tabs not supported in this configuration");
        }

        this.mDecorToolbar.setNavigationMode(arg2);
    }

    public void setSelectedNavigationItem(int arg3) {
        if(this.mDecorToolbar.getNavigationMode() != 1) {
            throw new IllegalStateException("setSelectedNavigationIndex not valid for current navigation mode");
        }

        this.mDecorToolbar.setDropdownSelectedPosition(arg3);
    }

    public void setShowHideAnimationEnabled(boolean arg1) {
    }

    public void setSplitBackgroundDrawable(Drawable arg1) {
    }

    public void setStackedBackgroundDrawable(Drawable arg1) {
    }

    public void setSubtitle(int arg3) {
        DecorToolbar v0 = this.mDecorToolbar;
        CharSequence v3 = arg3 != 0 ? this.mDecorToolbar.getContext().getText(arg3) : null;
        v0.setSubtitle(v3);
    }

    public void setSubtitle(CharSequence arg2) {
        this.mDecorToolbar.setSubtitle(arg2);
    }

    public void setTitle(int arg3) {
        DecorToolbar v0 = this.mDecorToolbar;
        CharSequence v3 = arg3 != 0 ? this.mDecorToolbar.getContext().getText(arg3) : null;
        v0.setTitle(v3);
    }

    public void setTitle(CharSequence arg2) {
        this.mDecorToolbar.setTitle(arg2);
    }

    public void setWindowTitle(CharSequence arg2) {
        this.mDecorToolbar.setWindowTitle(arg2);
    }

    public void show() {
        this.mDecorToolbar.setVisibility(0);
    }
}

