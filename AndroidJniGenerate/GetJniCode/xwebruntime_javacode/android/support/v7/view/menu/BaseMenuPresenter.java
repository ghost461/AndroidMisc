package android.support.v7.view.menu;

import android.content.Context;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.util.ArrayList;

@RestrictTo(value={Scope.LIBRARY_GROUP}) public abstract class BaseMenuPresenter implements MenuPresenter {
    private Callback mCallback;
    protected Context mContext;
    private int mId;
    protected LayoutInflater mInflater;
    private int mItemLayoutRes;
    protected MenuBuilder mMenu;
    private int mMenuLayoutRes;
    protected MenuView mMenuView;
    protected Context mSystemContext;
    protected LayoutInflater mSystemInflater;

    public BaseMenuPresenter(Context arg1, int arg2, int arg3) {
        super();
        this.mSystemContext = arg1;
        this.mSystemInflater = LayoutInflater.from(arg1);
        this.mMenuLayoutRes = arg2;
        this.mItemLayoutRes = arg3;
    }

    protected void addItemView(View arg2, int arg3) {
        ViewParent v0 = arg2.getParent();
        if(v0 != null) {
            ((ViewGroup)v0).removeView(arg2);
        }

        this.mMenuView.addView(arg2, arg3);
    }

    public abstract void bindItemView(MenuItemImpl arg1, ItemView arg2);

    public boolean collapseItemActionView(MenuBuilder arg1, MenuItemImpl arg2) {
        return 0;
    }

    public ItemView createItemView(ViewGroup arg4) {
        return this.mSystemInflater.inflate(this.mItemLayoutRes, arg4, false);
    }

    public boolean expandItemActionView(MenuBuilder arg1, MenuItemImpl arg2) {
        return 0;
    }

    protected boolean filterLeftoverView(ViewGroup arg1, int arg2) {
        arg1.removeViewAt(arg2);
        return 1;
    }

    public boolean flagActionItems() {
        return 0;
    }

    public Callback getCallback() {
        return this.mCallback;
    }

    public int getId() {
        return this.mId;
    }

    public View getItemView(MenuItemImpl arg2, View arg3, ViewGroup arg4) {
        ItemView v3;
        if((arg3 instanceof ItemView)) {
        }
        else {
            v3 = this.createItemView(arg4);
        }

        this.bindItemView(arg2, v3);
        return ((View)v3);
    }

    public MenuView getMenuView(ViewGroup arg4) {
        if(this.mMenuView == null) {
            this.mMenuView = this.mSystemInflater.inflate(this.mMenuLayoutRes, arg4, false);
            this.mMenuView.initialize(this.mMenu);
            this.updateMenuView(true);
        }

        return this.mMenuView;
    }

    public void initForMenu(Context arg1, MenuBuilder arg2) {
        this.mContext = arg1;
        this.mInflater = LayoutInflater.from(this.mContext);
        this.mMenu = arg2;
    }

    public void onCloseMenu(MenuBuilder arg2, boolean arg3) {
        if(this.mCallback != null) {
            this.mCallback.onCloseMenu(arg2, arg3);
        }
    }

    public boolean onSubMenuSelected(SubMenuBuilder arg2) {
        if(this.mCallback != null) {
            return this.mCallback.onOpenSubMenu(((MenuBuilder)arg2));
        }

        return 0;
    }

    public void setCallback(Callback arg1) {
        this.mCallback = arg1;
    }

    public void setId(int arg1) {
        this.mId = arg1;
    }

    public boolean shouldIncludeItem(int arg1, MenuItemImpl arg2) {
        return 1;
    }

    public void updateMenuView(boolean arg10) {
        MenuView v10 = this.mMenuView;
        if(v10 == null) {
            return;
        }

        int v1 = 0;
        if(this.mMenu != null) {
            this.mMenu.flagActionItems();
            ArrayList v0 = this.mMenu.getVisibleItems();
            int v2 = v0.size();
            int v3 = 0;
            int v4 = 0;
            while(v3 < v2) {
                Object v5 = v0.get(v3);
                if(this.shouldIncludeItem(v4, ((MenuItemImpl)v5))) {
                    View v6 = ((ViewGroup)v10).getChildAt(v4);
                    if((v6 instanceof ItemView)) {
                        MenuItemImpl v7 = v6.getItemData();
                    }
                    else {
                        Object v7_1 = null;
                    }

                    View v8 = this.getItemView(((MenuItemImpl)v5), v6, ((ViewGroup)v10));
                    if(v5 != v7) {
                        v8.setPressed(false);
                        v8.jumpDrawablesToCurrentState();
                    }

                    if(v8 != v6) {
                        this.addItemView(v8, v4);
                    }

                    ++v4;
                }

                ++v3;
            }

            v1 = v4;
        }

        while(v1 < ((ViewGroup)v10).getChildCount()) {
            if(this.filterLeftoverView(((ViewGroup)v10), v1)) {
                continue;
            }

            ++v1;
        }
    }
}

