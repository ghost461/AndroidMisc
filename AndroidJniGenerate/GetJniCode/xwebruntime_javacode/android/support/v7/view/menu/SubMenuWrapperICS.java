package android.support.v7.view.menu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.internal.view.SupportSubMenu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

@RequiresApi(value=14) @RestrictTo(value={Scope.LIBRARY_GROUP}) class SubMenuWrapperICS extends MenuWrapperICS implements SubMenu {
    SubMenuWrapperICS(Context arg1, SupportSubMenu arg2) {
        super(arg1, ((SupportMenu)arg2));
    }

    public void clearHeader() {
        this.getWrappedObject().clearHeader();
    }

    public MenuItem getItem() {
        return this.getMenuItemWrapper(this.getWrappedObject().getItem());
    }

    public SupportSubMenu getWrappedObject() {
        return this.mWrappedObject;
    }

    public Object getWrappedObject() {
        return this.getWrappedObject();
    }

    public SubMenu setHeaderIcon(int arg2) {
        this.getWrappedObject().setHeaderIcon(arg2);
        return this;
    }

    public SubMenu setHeaderIcon(Drawable arg2) {
        this.getWrappedObject().setHeaderIcon(arg2);
        return this;
    }

    public SubMenu setHeaderTitle(int arg2) {
        this.getWrappedObject().setHeaderTitle(arg2);
        return this;
    }

    public SubMenu setHeaderTitle(CharSequence arg2) {
        this.getWrappedObject().setHeaderTitle(arg2);
        return this;
    }

    public SubMenu setHeaderView(View arg2) {
        this.getWrappedObject().setHeaderView(arg2);
        return this;
    }

    public SubMenu setIcon(int arg2) {
        this.getWrappedObject().setIcon(arg2);
        return this;
    }

    public SubMenu setIcon(Drawable arg2) {
        this.getWrappedObject().setIcon(arg2);
        return this;
    }
}

