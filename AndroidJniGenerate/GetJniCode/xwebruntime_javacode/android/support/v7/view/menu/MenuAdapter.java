package android.support.v7.view.menu;

import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.v7.appcompat.R$layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;

@RestrictTo(value={Scope.LIBRARY_GROUP}) public class MenuAdapter extends BaseAdapter {
    static final int ITEM_LAYOUT;
    MenuBuilder mAdapterMenu;
    private int mExpandedIndex;
    private boolean mForceShowIcon;
    private final LayoutInflater mInflater;
    private final boolean mOverflowOnly;

    static {
        MenuAdapter.ITEM_LAYOUT = layout.abc_popup_menu_item_layout;
    }

    public MenuAdapter(MenuBuilder arg2, LayoutInflater arg3, boolean arg4) {
        super();
        this.mExpandedIndex = -1;
        this.mOverflowOnly = arg4;
        this.mInflater = arg3;
        this.mAdapterMenu = arg2;
        this.findExpandedIndex();
    }

    void findExpandedIndex() {
        MenuItemImpl v0 = this.mAdapterMenu.getExpandedItem();
        if(v0 != null) {
            ArrayList v1 = this.mAdapterMenu.getNonActionItems();
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

    public MenuBuilder getAdapterMenu() {
        return this.mAdapterMenu;
    }

    public int getCount() {
        ArrayList v0 = this.mOverflowOnly ? this.mAdapterMenu.getNonActionItems() : this.mAdapterMenu.getVisibleItems();
        if(this.mExpandedIndex < 0) {
            return v0.size();
        }

        return v0.size() - 1;
    }

    public boolean getForceShowIcon() {
        return this.mForceShowIcon;
    }

    public MenuItemImpl getItem(int arg3) {
        ArrayList v0 = this.mOverflowOnly ? this.mAdapterMenu.getNonActionItems() : this.mAdapterMenu.getVisibleItems();
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

    public View getView(int arg4, View arg5, ViewGroup arg6) {
        if(arg5 == null) {
            arg5 = this.mInflater.inflate(MenuAdapter.ITEM_LAYOUT, arg6, false);
        }

        View v6 = arg5;
        if(this.mForceShowIcon) {
            arg5.setForceShowIcon(true);
        }

        ((ItemView)v6).initialize(this.getItem(arg4), 0);
        return arg5;
    }

    public void notifyDataSetChanged() {
        this.findExpandedIndex();
        super.notifyDataSetChanged();
    }

    public void setForceShowIcon(boolean arg1) {
        this.mForceShowIcon = arg1;
    }
}

