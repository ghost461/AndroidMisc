package android.support.v7.view.menu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

@RestrictTo(value={Scope.LIBRARY_GROUP}) public class SubMenuBuilder extends MenuBuilder implements SubMenu {
    private MenuItemImpl mItem;
    private MenuBuilder mParentMenu;

    public SubMenuBuilder(Context arg1, MenuBuilder arg2, MenuItemImpl arg3) {
        super(arg1);
        this.mParentMenu = arg2;
        this.mItem = arg3;
    }

    public boolean collapseItemActionView(MenuItemImpl arg2) {
        return this.mParentMenu.collapseItemActionView(arg2);
    }

    boolean dispatchMenuItemSelected(MenuBuilder arg2, MenuItem arg3) {
        boolean v2 = (super.dispatchMenuItemSelected(arg2, arg3)) || (this.mParentMenu.dispatchMenuItemSelected(arg2, arg3)) ? true : false;
        return v2;
    }

    public boolean expandItemActionView(MenuItemImpl arg2) {
        return this.mParentMenu.expandItemActionView(arg2);
    }

    public String getActionViewStatesKey() {
        int v0 = this.mItem != null ? this.mItem.getItemId() : 0;
        if(v0 == 0) {
            return null;
        }

        return super.getActionViewStatesKey() + ":" + v0;
    }

    public MenuItem getItem() {
        return this.mItem;
    }

    public Menu getParentMenu() {
        return this.mParentMenu;
    }

    public MenuBuilder getRootMenu() {
        return this.mParentMenu.getRootMenu();
    }

    public boolean isQwertyMode() {
        return this.mParentMenu.isQwertyMode();
    }

    public boolean isShortcutsVisible() {
        return this.mParentMenu.isShortcutsVisible();
    }

    public void setCallback(Callback arg2) {
        this.mParentMenu.setCallback(arg2);
    }

    public SubMenu setHeaderIcon(int arg1) {
        return super.setHeaderIconInt(arg1);
    }

    public SubMenu setHeaderIcon(Drawable arg1) {
        return super.setHeaderIconInt(arg1);
    }

    public SubMenu setHeaderTitle(CharSequence arg1) {
        return super.setHeaderTitleInt(arg1);
    }

    public SubMenu setHeaderTitle(int arg1) {
        return super.setHeaderTitleInt(arg1);
    }

    public SubMenu setHeaderView(View arg1) {
        return super.setHeaderViewInt(arg1);
    }

    public SubMenu setIcon(int arg2) {
        this.mItem.setIcon(arg2);
        return this;
    }

    public SubMenu setIcon(Drawable arg2) {
        this.mItem.setIcon(arg2);
        return this;
    }

    public void setQwertyMode(boolean arg2) {
        this.mParentMenu.setQwertyMode(arg2);
    }

    public void setShortcutsVisible(boolean arg2) {
        this.mParentMenu.setShortcutsVisible(arg2);
    }
}

