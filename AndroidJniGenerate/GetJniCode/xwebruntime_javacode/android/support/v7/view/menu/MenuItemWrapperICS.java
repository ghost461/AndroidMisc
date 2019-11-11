package android.support.v7.view.menu;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff$Mode;
import android.graphics.drawable.Drawable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.v4.internal.view.SupportMenuItem;
import android.support.v4.view.ActionProvider;
import android.support.v7.view.CollapsibleActionView;
import android.util.Log;
import android.view.ContextMenu$ContextMenuInfo;
import android.view.MenuItem$OnActionExpandListener;
import android.view.MenuItem$OnMenuItemClickListener;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.FrameLayout;
import java.lang.reflect.Method;

@RequiresApi(value=14) @RestrictTo(value={Scope.LIBRARY_GROUP}) public class MenuItemWrapperICS extends BaseMenuWrapper implements MenuItem {
    class ActionProviderWrapper extends ActionProvider {
        final android.view.ActionProvider mInner;

        public ActionProviderWrapper(MenuItemWrapperICS arg1, Context arg2, android.view.ActionProvider arg3) {
            MenuItemWrapperICS.this = arg1;
            super(arg2);
            this.mInner = arg3;
        }

        public boolean hasSubMenu() {
            return this.mInner.hasSubMenu();
        }

        public View onCreateActionView() {
            return this.mInner.onCreateActionView();
        }

        public boolean onPerformDefaultAction() {
            return this.mInner.onPerformDefaultAction();
        }

        public void onPrepareSubMenu(SubMenu arg3) {
            this.mInner.onPrepareSubMenu(MenuItemWrapperICS.this.getSubMenuWrapper(arg3));
        }
    }

    class CollapsibleActionViewWrapper extends FrameLayout implements CollapsibleActionView {
        final android.view.CollapsibleActionView mWrappedView;

        CollapsibleActionViewWrapper(View arg2) {
            super(arg2.getContext());
            this.mWrappedView = arg2;
            this.addView(arg2);
        }

        View getWrappedView() {
            return this.mWrappedView;
        }

        public void onActionViewCollapsed() {
            this.mWrappedView.onActionViewCollapsed();
        }

        public void onActionViewExpanded() {
            this.mWrappedView.onActionViewExpanded();
        }
    }

    class OnActionExpandListenerWrapper extends BaseWrapper implements MenuItem$OnActionExpandListener {
        OnActionExpandListenerWrapper(MenuItemWrapperICS arg1, MenuItem$OnActionExpandListener arg2) {
            MenuItemWrapperICS.this = arg1;
            super(arg2);
        }

        public boolean onMenuItemActionCollapse(MenuItem arg3) {
            return this.mWrappedObject.onMenuItemActionCollapse(MenuItemWrapperICS.this.getMenuItemWrapper(arg3));
        }

        public boolean onMenuItemActionExpand(MenuItem arg3) {
            return this.mWrappedObject.onMenuItemActionExpand(MenuItemWrapperICS.this.getMenuItemWrapper(arg3));
        }
    }

    class OnMenuItemClickListenerWrapper extends BaseWrapper implements MenuItem$OnMenuItemClickListener {
        OnMenuItemClickListenerWrapper(MenuItemWrapperICS arg1, MenuItem$OnMenuItemClickListener arg2) {
            MenuItemWrapperICS.this = arg1;
            super(arg2);
        }

        public boolean onMenuItemClick(MenuItem arg3) {
            return this.mWrappedObject.onMenuItemClick(MenuItemWrapperICS.this.getMenuItemWrapper(arg3));
        }
    }

    static final String LOG_TAG = "MenuItemWrapper";
    private Method mSetExclusiveCheckableMethod;

    MenuItemWrapperICS(Context arg1, SupportMenuItem arg2) {
        super(arg1, arg2);
    }

    public boolean collapseActionView() {
        return this.mWrappedObject.collapseActionView();
    }

    ActionProviderWrapper createActionProviderWrapper(android.view.ActionProvider arg3) {
        return new ActionProviderWrapper(this, this.mContext, arg3);
    }

    public boolean expandActionView() {
        return this.mWrappedObject.expandActionView();
    }

    public android.view.ActionProvider getActionProvider() {
        ActionProvider v0 = this.mWrappedObject.getSupportActionProvider();
        if((v0 instanceof ActionProviderWrapper)) {
            return ((ActionProviderWrapper)v0).mInner;
        }

        return null;
    }

    public View getActionView() {
        View v0 = this.mWrappedObject.getActionView();
        if((v0 instanceof CollapsibleActionViewWrapper)) {
            return ((CollapsibleActionViewWrapper)v0).getWrappedView();
        }

        return v0;
    }

    public int getAlphabeticModifiers() {
        return this.mWrappedObject.getAlphabeticModifiers();
    }

    public char getAlphabeticShortcut() {
        return this.mWrappedObject.getAlphabeticShortcut();
    }

    public CharSequence getContentDescription() {
        return this.mWrappedObject.getContentDescription();
    }

    public int getGroupId() {
        return this.mWrappedObject.getGroupId();
    }

    public Drawable getIcon() {
        return this.mWrappedObject.getIcon();
    }

    public ColorStateList getIconTintList() {
        return this.mWrappedObject.getIconTintList();
    }

    public PorterDuff$Mode getIconTintMode() {
        return this.mWrappedObject.getIconTintMode();
    }

    public Intent getIntent() {
        return this.mWrappedObject.getIntent();
    }

    public int getItemId() {
        return this.mWrappedObject.getItemId();
    }

    public ContextMenu$ContextMenuInfo getMenuInfo() {
        return this.mWrappedObject.getMenuInfo();
    }

    public int getNumericModifiers() {
        return this.mWrappedObject.getNumericModifiers();
    }

    public char getNumericShortcut() {
        return this.mWrappedObject.getNumericShortcut();
    }

    public int getOrder() {
        return this.mWrappedObject.getOrder();
    }

    public SubMenu getSubMenu() {
        return this.getSubMenuWrapper(this.mWrappedObject.getSubMenu());
    }

    public CharSequence getTitle() {
        return this.mWrappedObject.getTitle();
    }

    public CharSequence getTitleCondensed() {
        return this.mWrappedObject.getTitleCondensed();
    }

    public CharSequence getTooltipText() {
        return this.mWrappedObject.getTooltipText();
    }

    public boolean hasSubMenu() {
        return this.mWrappedObject.hasSubMenu();
    }

    public boolean isActionViewExpanded() {
        return this.mWrappedObject.isActionViewExpanded();
    }

    public boolean isCheckable() {
        return this.mWrappedObject.isCheckable();
    }

    public boolean isChecked() {
        return this.mWrappedObject.isChecked();
    }

    public boolean isEnabled() {
        return this.mWrappedObject.isEnabled();
    }

    public boolean isVisible() {
        return this.mWrappedObject.isVisible();
    }

    public MenuItem setActionProvider(android.view.ActionProvider arg2) {
        ActionProvider v2_1;
        Object v0 = this.mWrappedObject;
        if(arg2 != null) {
            ActionProviderWrapper v2 = this.createActionProviderWrapper(arg2);
        }
        else {
            v2_1 = null;
        }

        ((SupportMenuItem)v0).setSupportActionProvider(v2_1);
        return this;
    }

    public MenuItem setActionView(int arg3) {
        this.mWrappedObject.setActionView(arg3);
        View v3 = this.mWrappedObject.getActionView();
        if((v3 instanceof android.view.CollapsibleActionView)) {
            this.mWrappedObject.setActionView(new CollapsibleActionViewWrapper(v3));
        }

        return this;
    }

    public MenuItem setActionView(View arg2) {
        CollapsibleActionViewWrapper v2;
        if((arg2 instanceof android.view.CollapsibleActionView)) {
            v2 = new CollapsibleActionViewWrapper(arg2);
        }

        this.mWrappedObject.setActionView(((View)v2));
        return this;
    }

    public MenuItem setAlphabeticShortcut(char arg2) {
        this.mWrappedObject.setAlphabeticShortcut(arg2);
        return this;
    }

    public MenuItem setAlphabeticShortcut(char arg2, int arg3) {
        this.mWrappedObject.setAlphabeticShortcut(arg2, arg3);
        return this;
    }

    public MenuItem setCheckable(boolean arg2) {
        this.mWrappedObject.setCheckable(arg2);
        return this;
    }

    public MenuItem setChecked(boolean arg2) {
        this.mWrappedObject.setChecked(arg2);
        return this;
    }

    public MenuItem setContentDescription(CharSequence arg2) {
        this.mWrappedObject.setContentDescription(arg2);
        return this;
    }

    public MenuItem setEnabled(boolean arg2) {
        this.mWrappedObject.setEnabled(arg2);
        return this;
    }

    public void setExclusiveCheckable(boolean arg7) {
        try {
            if(this.mSetExclusiveCheckableMethod == null) {
                this.mSetExclusiveCheckableMethod = this.mWrappedObject.getClass().getDeclaredMethod("setExclusiveCheckable", Boolean.TYPE);
            }

            this.mSetExclusiveCheckableMethod.invoke(this.mWrappedObject, Boolean.valueOf(arg7));
        }
        catch(Exception v7) {
            Log.w("MenuItemWrapper", "Error while calling setExclusiveCheckable", ((Throwable)v7));
        }
    }

    public MenuItem setIcon(int arg2) {
        this.mWrappedObject.setIcon(arg2);
        return this;
    }

    public MenuItem setIcon(Drawable arg2) {
        this.mWrappedObject.setIcon(arg2);
        return this;
    }

    public MenuItem setIconTintList(ColorStateList arg2) {
        this.mWrappedObject.setIconTintList(arg2);
        return this;
    }

    public MenuItem setIconTintMode(PorterDuff$Mode arg2) {
        this.mWrappedObject.setIconTintMode(arg2);
        return this;
    }

    public MenuItem setIntent(Intent arg2) {
        this.mWrappedObject.setIntent(arg2);
        return this;
    }

    public MenuItem setNumericShortcut(char arg2) {
        this.mWrappedObject.setNumericShortcut(arg2);
        return this;
    }

    public MenuItem setNumericShortcut(char arg2, int arg3) {
        this.mWrappedObject.setNumericShortcut(arg2, arg3);
        return this;
    }

    public MenuItem setOnActionExpandListener(MenuItem$OnActionExpandListener arg3) {
        MenuItem$OnActionExpandListener v1_1;
        Object v0 = this.mWrappedObject;
        if(arg3 != null) {
            OnActionExpandListenerWrapper v1 = new OnActionExpandListenerWrapper(this, arg3);
        }
        else {
            v1_1 = null;
        }

        ((SupportMenuItem)v0).setOnActionExpandListener(v1_1);
        return this;
    }

    public MenuItem setOnMenuItemClickListener(MenuItem$OnMenuItemClickListener arg3) {
        OnMenuItemClickListenerWrapper v1;
        Object v0 = this.mWrappedObject;
        if(arg3 != null) {
            v1 = new OnMenuItemClickListenerWrapper(this, arg3);
        }
        else {
            MenuItem$OnMenuItemClickListener v1_1 = null;
        }

        ((SupportMenuItem)v0).setOnMenuItemClickListener(((MenuItem$OnMenuItemClickListener)v1));
        return this;
    }

    public MenuItem setShortcut(char arg2, char arg3) {
        this.mWrappedObject.setShortcut(arg2, arg3);
        return this;
    }

    public MenuItem setShortcut(char arg2, char arg3, int arg4, int arg5) {
        this.mWrappedObject.setShortcut(arg2, arg3, arg4, arg5);
        return this;
    }

    public void setShowAsAction(int arg2) {
        this.mWrappedObject.setShowAsAction(arg2);
    }

    public MenuItem setShowAsActionFlags(int arg2) {
        this.mWrappedObject.setShowAsActionFlags(arg2);
        return this;
    }

    public MenuItem setTitle(int arg2) {
        this.mWrappedObject.setTitle(arg2);
        return this;
    }

    public MenuItem setTitle(CharSequence arg2) {
        this.mWrappedObject.setTitle(arg2);
        return this;
    }

    public MenuItem setTitleCondensed(CharSequence arg2) {
        this.mWrappedObject.setTitleCondensed(arg2);
        return this;
    }

    public MenuItem setTooltipText(CharSequence arg2) {
        this.mWrappedObject.setTooltipText(arg2);
        return this;
    }

    public MenuItem setVisible(boolean arg2) {
        return this.mWrappedObject.setVisible(arg2);
    }
}

