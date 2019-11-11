package android.support.v7.view.menu;

import android.content.Context;
import android.os.Build$VERSION;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.internal.view.SupportMenuItem;
import android.support.v4.internal.view.SupportSubMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

@RestrictTo(value={Scope.LIBRARY_GROUP}) public final class MenuWrapperFactory {
    private MenuWrapperFactory() {
        super();
    }

    public static Menu wrapSupportMenu(Context arg1, SupportMenu arg2) {
        return new MenuWrapperICS(arg1, arg2);
    }

    public static MenuItem wrapSupportMenuItem(Context arg2, SupportMenuItem arg3) {
        if(Build$VERSION.SDK_INT >= 16) {
            return new MenuItemWrapperJB(arg2, arg3);
        }

        return new MenuItemWrapperICS(arg2, arg3);
    }

    public static SubMenu wrapSupportSubMenu(Context arg1, SupportSubMenu arg2) {
        return new SubMenuWrapperICS(arg1, arg2);
    }
}

