package android.support.v7.view.menu;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.RequiresApi;
import android.support.v4.internal.view.SupportMenu;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

@RequiresApi(value=14) class MenuWrapperICS extends BaseMenuWrapper implements Menu {
    MenuWrapperICS(Context arg1, SupportMenu arg2) {
        super(arg1, arg2);
    }

    public MenuItem add(int arg2) {
        return this.getMenuItemWrapper(this.mWrappedObject.add(arg2));
    }

    public MenuItem add(int arg2, int arg3, int arg4, int arg5) {
        return this.getMenuItemWrapper(this.mWrappedObject.add(arg2, arg3, arg4, arg5));
    }

    public MenuItem add(int arg2, int arg3, int arg4, CharSequence arg5) {
        return this.getMenuItemWrapper(this.mWrappedObject.add(arg2, arg3, arg4, arg5));
    }

    public MenuItem add(CharSequence arg2) {
        return this.getMenuItemWrapper(this.mWrappedObject.add(arg2));
    }

    public int addIntentOptions(int arg13, int arg14, int arg15, ComponentName arg16, Intent[] arg17, Intent arg18, int arg19, MenuItem[] arg20) {
        MenuWrapperICS v0 = this;
        MenuItem[] v1 = arg20;
        MenuItem[] v2 = v1 != null ? new MenuItem[v1.length] : null;
        int v3 = v0.mWrappedObject.addIntentOptions(arg13, arg14, arg15, arg16, arg17, arg18, arg19, v2);
        if(v2 != null) {
            int v4 = 0;
            int v5 = v2.length;
            while(v4 < v5) {
                v1[v4] = v0.getMenuItemWrapper(v2[v4]);
                ++v4;
            }
        }

        return v3;
    }

    public SubMenu addSubMenu(int arg2) {
        return this.getSubMenuWrapper(this.mWrappedObject.addSubMenu(arg2));
    }

    public SubMenu addSubMenu(int arg2, int arg3, int arg4, int arg5) {
        return this.getSubMenuWrapper(this.mWrappedObject.addSubMenu(arg2, arg3, arg4, arg5));
    }

    public SubMenu addSubMenu(int arg2, int arg3, int arg4, CharSequence arg5) {
        return this.getSubMenuWrapper(this.mWrappedObject.addSubMenu(arg2, arg3, arg4, arg5));
    }

    public SubMenu addSubMenu(CharSequence arg2) {
        return this.getSubMenuWrapper(this.mWrappedObject.addSubMenu(arg2));
    }

    public void clear() {
        this.internalClear();
        this.mWrappedObject.clear();
    }

    public void close() {
        this.mWrappedObject.close();
    }

    public MenuItem findItem(int arg2) {
        return this.getMenuItemWrapper(this.mWrappedObject.findItem(arg2));
    }

    public MenuItem getItem(int arg2) {
        return this.getMenuItemWrapper(this.mWrappedObject.getItem(arg2));
    }

    public boolean hasVisibleItems() {
        return this.mWrappedObject.hasVisibleItems();
    }

    public boolean isShortcutKey(int arg2, KeyEvent arg3) {
        return this.mWrappedObject.isShortcutKey(arg2, arg3);
    }

    public boolean performIdentifierAction(int arg2, int arg3) {
        return this.mWrappedObject.performIdentifierAction(arg2, arg3);
    }

    public boolean performShortcut(int arg2, KeyEvent arg3, int arg4) {
        return this.mWrappedObject.performShortcut(arg2, arg3, arg4);
    }

    public void removeGroup(int arg2) {
        this.internalRemoveGroup(arg2);
        this.mWrappedObject.removeGroup(arg2);
    }

    public void removeItem(int arg2) {
        this.internalRemoveItem(arg2);
        this.mWrappedObject.removeItem(arg2);
    }

    public void setGroupCheckable(int arg2, boolean arg3, boolean arg4) {
        this.mWrappedObject.setGroupCheckable(arg2, arg3, arg4);
    }

    public void setGroupEnabled(int arg2, boolean arg3) {
        this.mWrappedObject.setGroupEnabled(arg2, arg3);
    }

    public void setGroupVisible(int arg2, boolean arg3) {
        this.mWrappedObject.setGroupVisible(arg2, arg3);
    }

    public void setQwertyMode(boolean arg2) {
        this.mWrappedObject.setQwertyMode(arg2);
    }

    public int size() {
        return this.mWrappedObject.size();
    }
}

