package android.support.v7.view.menu;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.view.View$MeasureSpec;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.PopupWindow$OnDismissListener;

abstract class MenuPopup implements MenuPresenter, ShowableListMenu, AdapterView$OnItemClickListener {
    private Rect mEpicenterBounds;

    MenuPopup() {
        super();
    }

    public abstract void addMenu(MenuBuilder arg1);

    protected boolean closeMenuOnSubMenuOpened() {
        return 1;
    }

    public boolean collapseItemActionView(MenuBuilder arg1, MenuItemImpl arg2) {
        return 0;
    }

    public boolean expandItemActionView(MenuBuilder arg1, MenuItemImpl arg2) {
        return 0;
    }

    public Rect getEpicenterBounds() {
        return this.mEpicenterBounds;
    }

    public int getId() {
        return 0;
    }

    public MenuView getMenuView(ViewGroup arg2) {
        throw new UnsupportedOperationException("MenuPopups manage their own views");
    }

    public void initForMenu(@NonNull Context arg1, @Nullable MenuBuilder arg2) {
    }

    protected static int measureIndividualMenuWidth(ListAdapter arg9, ViewGroup arg10, Context arg11, int arg12) {
        FrameLayout v6_1;
        int v0 = 0;
        int v1 = View$MeasureSpec.makeMeasureSpec(0, 0);
        int v2 = View$MeasureSpec.makeMeasureSpec(0, 0);
        int v3 = arg9.getCount();
        View v4 = null;
        ViewGroup v6 = arg10;
        View v7 = v4;
        int v10 = 0;
        int v5 = 0;
        while(v0 < v3) {
            int v8 = arg9.getItemViewType(v0);
            if(v8 != v10) {
                v7 = v4;
                v10 = v8;
            }

            if(v6 == null) {
                v6_1 = new FrameLayout(arg11);
            }

            v7 = arg9.getView(v0, v7, ((ViewGroup)v6_1));
            v7.measure(v1, v2);
            v8 = v7.getMeasuredWidth();
            if(v8 >= arg12) {
                return arg12;
            }

            if(v8 > v5) {
                v5 = v8;
            }

            ++v0;
        }

        return v5;
    }

    public void onItemClick(AdapterView arg1, View arg2, int arg3, long arg4) {
        Adapter v1 = arg1.getAdapter();
        MenuBuilder v2 = MenuPopup.toMenuAdapter(((ListAdapter)v1)).mAdapterMenu;
        Object v1_1 = ((ListAdapter)v1).getItem(arg3);
        arg3 = this.closeMenuOnSubMenuOpened() ? 0 : 4;
        v2.performItemAction(((MenuItem)v1_1), ((MenuPresenter)this), arg3);
    }

    public abstract void setAnchorView(View arg1);

    public void setEpicenterBounds(Rect arg1) {
        this.mEpicenterBounds = arg1;
    }

    public abstract void setForceShowIcon(boolean arg1);

    public abstract void setGravity(int arg1);

    public abstract void setHorizontalOffset(int arg1);

    public abstract void setOnDismissListener(PopupWindow$OnDismissListener arg1);

    public abstract void setShowTitle(boolean arg1);

    public abstract void setVerticalOffset(int arg1);

    protected static boolean shouldPreserveIconSpacing(MenuBuilder arg5) {
        int v0 = arg5.size();
        boolean v1 = false;
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            MenuItem v3 = arg5.getItem(v2);
            if((v3.isVisible()) && v3.getIcon() != null) {
                return true;
            }
        }

        return v1;
    }

    protected static MenuAdapter toMenuAdapter(ListAdapter arg1) {
        if((arg1 instanceof HeaderViewListAdapter)) {
            return ((HeaderViewListAdapter)arg1).getWrappedAdapter();
        }

        return arg1;
    }
}

