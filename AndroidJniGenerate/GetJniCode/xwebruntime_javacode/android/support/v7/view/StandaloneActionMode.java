package android.support.v7.view;

import android.content.Context;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.v7.view.menu.MenuBuilder$Callback;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.view.menu.SubMenuBuilder;
import android.support.v7.widget.ActionBarContextView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import java.lang.ref.WeakReference;

@RestrictTo(value={Scope.LIBRARY_GROUP}) public class StandaloneActionMode extends ActionMode implements Callback {
    private android.support.v7.view.ActionMode$Callback mCallback;
    private Context mContext;
    private ActionBarContextView mContextView;
    private WeakReference mCustomView;
    private boolean mFinished;
    private boolean mFocusable;
    private MenuBuilder mMenu;

    public StandaloneActionMode(Context arg1, ActionBarContextView arg2, android.support.v7.view.ActionMode$Callback arg3, boolean arg4) {
        super();
        this.mContext = arg1;
        this.mContextView = arg2;
        this.mCallback = arg3;
        this.mMenu = new MenuBuilder(arg2.getContext()).setDefaultShowAsAction(1);
        this.mMenu.setCallback(((Callback)this));
        this.mFocusable = arg4;
    }

    public void finish() {
        if(this.mFinished) {
            return;
        }

        this.mFinished = true;
        this.mContextView.sendAccessibilityEvent(0x20);
        this.mCallback.onDestroyActionMode(((ActionMode)this));
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
        return new SupportMenuInflater(this.mContextView.getContext());
    }

    public CharSequence getSubtitle() {
        return this.mContextView.getSubtitle();
    }

    public CharSequence getTitle() {
        return this.mContextView.getTitle();
    }

    public void invalidate() {
        this.mCallback.onPrepareActionMode(((ActionMode)this), this.mMenu);
    }

    public boolean isTitleOptional() {
        return this.mContextView.isTitleOptional();
    }

    public boolean isUiFocusable() {
        return this.mFocusable;
    }

    public void onCloseMenu(MenuBuilder arg1, boolean arg2) {
    }

    public void onCloseSubMenu(SubMenuBuilder arg1) {
    }

    public boolean onMenuItemSelected(MenuBuilder arg1, MenuItem arg2) {
        return this.mCallback.onActionItemClicked(((ActionMode)this), arg2);
    }

    public void onMenuModeChange(MenuBuilder arg1) {
        this.invalidate();
        this.mContextView.showOverflowMenu();
    }

    public boolean onSubMenuSelected(SubMenuBuilder arg4) {
        if(!arg4.hasVisibleItems()) {
            return 1;
        }

        new MenuPopupHelper(this.mContextView.getContext(), ((MenuBuilder)arg4)).show();
        return 1;
    }

    public void setCustomView(View arg2) {
        this.mContextView.setCustomView(arg2);
        WeakReference v0 = arg2 != null ? new WeakReference(arg2) : null;
        this.mCustomView = v0;
    }

    public void setSubtitle(int arg2) {
        this.setSubtitle(this.mContext.getString(arg2));
    }

    public void setSubtitle(CharSequence arg2) {
        this.mContextView.setSubtitle(arg2);
    }

    public void setTitle(int arg2) {
        this.setTitle(this.mContext.getString(arg2));
    }

    public void setTitle(CharSequence arg2) {
        this.mContextView.setTitle(arg2);
    }

    public void setTitleOptionalHint(boolean arg2) {
        super.setTitleOptionalHint(arg2);
        this.mContextView.setTitleOptional(arg2);
    }
}

