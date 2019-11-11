package android.support.v7.view.menu;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.v7.appcompat.R$layout;
import android.util.SparseArray;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import java.util.ArrayList;

@RestrictTo(value={Scope.LIBRARY_GROUP}) public class ListMenuPresenter implements MenuPresenter, AdapterView$OnItemClickListener {
    class MenuAdapter extends BaseAdapter {
        private int mExpandedIndex;

        public MenuAdapter(ListMenuPresenter arg1) {
            ListMenuPresenter.this = arg1;
            super();
            this.mExpandedIndex = -1;
            this.findExpandedIndex();
        }

        void findExpandedIndex() {
            MenuItemImpl v0 = ListMenuPresenter.this.mMenu.getExpandedItem();
            if(v0 != null) {
                ArrayList v1 = ListMenuPresenter.this.mMenu.getNonActionItems();
                int v2 = v1.size();
                int v3 = 0;
                while(v3 < v2) {
                    if(v1.get(v3) == v0) {
                        this.mExpandedIndex = v3;
                        return;
                    }
                    else {
                        ++v3;
                        continue;
                    }

                    break;
                }
            }

            this.mExpandedIndex = -1;
        }

        public int getCount() {
            int v0 = ListMenuPresenter.this.mMenu.getNonActionItems().size() - ListMenuPresenter.this.mItemIndexOffset;
            if(this.mExpandedIndex < 0) {
                return v0;
            }

            return v0 - 1;
        }

        public MenuItemImpl getItem(int arg3) {
            ArrayList v0 = ListMenuPresenter.this.mMenu.getNonActionItems();
            arg3 += ListMenuPresenter.this.mItemIndexOffset;
            if(this.mExpandedIndex >= 0 && arg3 >= this.mExpandedIndex) {
                ++arg3;
            }

            return v0.get(arg3);
        }

        public Object getItem(int arg1) {
            return this.getItem(arg1);
        }

        public long getItemId(int arg3) {
            return ((long)arg3);
        }

        public View getView(int arg3, View arg4, ViewGroup arg5) {
            if(arg4 == null) {
                arg4 = ListMenuPresenter.this.mInflater.inflate(ListMenuPresenter.this.mItemLayoutRes, arg5, false);
            }

            arg4.initialize(this.getItem(arg3), 0);
            return arg4;
        }

        public void notifyDataSetChanged() {
            this.findExpandedIndex();
            super.notifyDataSetChanged();
        }
    }

    private static final String TAG = "ListMenuPresenter";
    public static final String VIEWS_TAG = "android:menu:list";
    MenuAdapter mAdapter;
    private Callback mCallback;
    Context mContext;
    private int mId;
    LayoutInflater mInflater;
    int mItemIndexOffset;
    int mItemLayoutRes;
    MenuBuilder mMenu;
    ExpandedMenuView mMenuView;
    int mThemeRes;

    public ListMenuPresenter(int arg1, int arg2) {
        super();
        this.mItemLayoutRes = arg1;
        this.mThemeRes = arg2;
    }

    public ListMenuPresenter(Context arg2, int arg3) {
        this(arg3, 0);
        this.mContext = arg2;
        this.mInflater = LayoutInflater.from(this.mContext);
    }

    public boolean collapseItemActionView(MenuBuilder arg1, MenuItemImpl arg2) {
        return 0;
    }

    public boolean expandItemActionView(MenuBuilder arg1, MenuItemImpl arg2) {
        return 0;
    }

    public boolean flagActionItems() {
        return 0;
    }

    public ListAdapter getAdapter() {
        if(this.mAdapter == null) {
            this.mAdapter = new MenuAdapter(this);
        }

        return this.mAdapter;
    }

    public int getId() {
        return this.mId;
    }

    int getItemIndexOffset() {
        return this.mItemIndexOffset;
    }

    public MenuView getMenuView(ViewGroup arg4) {
        if(this.mMenuView == null) {
            this.mMenuView = this.mInflater.inflate(layout.abc_expanded_menu_layout, arg4, false);
            if(this.mAdapter == null) {
                this.mAdapter = new MenuAdapter(this);
            }

            this.mMenuView.setAdapter(this.mAdapter);
            this.mMenuView.setOnItemClickListener(((AdapterView$OnItemClickListener)this));
        }

        return this.mMenuView;
    }

    public void initForMenu(Context arg3, MenuBuilder arg4) {
        if(this.mThemeRes != 0) {
            this.mContext = new ContextThemeWrapper(arg3, this.mThemeRes);
            this.mInflater = LayoutInflater.from(this.mContext);
        }
        else if(this.mContext != null) {
            this.mContext = arg3;
            if(this.mInflater == null) {
                this.mInflater = LayoutInflater.from(this.mContext);
            }
        }

        this.mMenu = arg4;
        if(this.mAdapter != null) {
            this.mAdapter.notifyDataSetChanged();
        }
    }

    public void onCloseMenu(MenuBuilder arg2, boolean arg3) {
        if(this.mCallback != null) {
            this.mCallback.onCloseMenu(arg2, arg3);
        }
    }

    public void onItemClick(AdapterView arg1, View arg2, int arg3, long arg4) {
        this.mMenu.performItemAction(this.mAdapter.getItem(arg3), ((MenuPresenter)this), 0);
    }

    public void onRestoreInstanceState(Parcelable arg1) {
        this.restoreHierarchyState(((Bundle)arg1));
    }

    public Parcelable onSaveInstanceState() {
        if(this.mMenuView == null) {
            return null;
        }

        Bundle v0 = new Bundle();
        this.saveHierarchyState(v0);
        return ((Parcelable)v0);
    }

    public boolean onSubMenuSelected(SubMenuBuilder arg3) {
        if(!arg3.hasVisibleItems()) {
            return 0;
        }

        new MenuDialogHelper(((MenuBuilder)arg3)).show(null);
        if(this.mCallback != null) {
            this.mCallback.onOpenSubMenu(((MenuBuilder)arg3));
        }

        return 1;
    }

    public void restoreHierarchyState(Bundle arg2) {
        SparseArray v2 = arg2.getSparseParcelableArray("android:menu:list");
        if(v2 != null) {
            this.mMenuView.restoreHierarchyState(v2);
        }
    }

    public void saveHierarchyState(Bundle arg3) {
        SparseArray v0 = new SparseArray();
        if(this.mMenuView != null) {
            this.mMenuView.saveHierarchyState(v0);
        }

        arg3.putSparseParcelableArray("android:menu:list", v0);
    }

    public void setCallback(Callback arg1) {
        this.mCallback = arg1;
    }

    public void setId(int arg1) {
        this.mId = arg1;
    }

    public void setItemIndexOffset(int arg1) {
        this.mItemIndexOffset = arg1;
        if(this.mMenuView != null) {
            this.updateMenuView(false);
        }
    }

    public void updateMenuView(boolean arg1) {
        if(this.mAdapter != null) {
            this.mAdapter.notifyDataSetChanged();
        }
    }
}

