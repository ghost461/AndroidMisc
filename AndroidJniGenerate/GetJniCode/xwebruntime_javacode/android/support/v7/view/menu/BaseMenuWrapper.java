package android.support.v7.view.menu;

import android.content.Context;
import android.support.v4.internal.view.SupportMenuItem;
import android.support.v4.internal.view.SupportSubMenu;
import android.support.v4.util.ArrayMap;
import android.view.MenuItem;
import android.view.SubMenu;
import java.util.Iterator;
import java.util.Map;

abstract class BaseMenuWrapper extends BaseWrapper {
    final Context mContext;
    private Map mMenuItems;
    private Map mSubMenus;

    BaseMenuWrapper(Context arg1, Object arg2) {
        super(arg2);
        this.mContext = arg1;
    }

    final MenuItem getMenuItemWrapper(MenuItem arg3) {
        if((arg3 instanceof SupportMenuItem)) {
            MenuItem v0 = arg3;
            if(this.mMenuItems == null) {
                this.mMenuItems = new ArrayMap();
            }

            Object v3 = this.mMenuItems.get(arg3);
            if(v3 == null) {
                arg3 = MenuWrapperFactory.wrapSupportMenuItem(this.mContext, ((SupportMenuItem)v0));
                this.mMenuItems.put(v0, arg3);
            }

            return arg3;
        }

        return arg3;
    }

    final SubMenu getSubMenuWrapper(SubMenu arg3) {
        if((arg3 instanceof SupportSubMenu)) {
            if(this.mSubMenus == null) {
                this.mSubMenus = new ArrayMap();
            }

            Object v0 = this.mSubMenus.get(arg3);
            if(v0 == null) {
                SubMenu v0_1 = MenuWrapperFactory.wrapSupportSubMenu(this.mContext, ((SupportSubMenu)arg3));
                this.mSubMenus.put(arg3, v0_1);
            }

            return ((SubMenu)v0);
        }

        return arg3;
    }

    final void internalClear() {
        if(this.mMenuItems != null) {
            this.mMenuItems.clear();
        }

        if(this.mSubMenus != null) {
            this.mSubMenus.clear();
        }
    }

    final void internalRemoveGroup(int arg3) {
        if(this.mMenuItems == null) {
            return;
        }

        Iterator v0 = this.mMenuItems.keySet().iterator();
        while(v0.hasNext()) {
            if(arg3 != v0.next().getGroupId()) {
                continue;
            }

            v0.remove();
        }
    }

    final void internalRemoveItem(int arg3) {
        if(this.mMenuItems == null) {
            return;
        }

        Iterator v0 = this.mMenuItems.keySet().iterator();
        do {
            if(v0.hasNext()) {
                if(arg3 != v0.next().getItemId()) {
                    continue;
                }

                break;
            }

            return;
        }
        while(true);

        v0.remove();
    }
}

