package android.support.v7.view.menu;

import android.content.Context;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ListView;

@RestrictTo(value={Scope.LIBRARY_GROUP}) public final class ExpandedMenuView extends ListView implements ItemInvoker, MenuView, AdapterView$OnItemClickListener {
    private static final int[] TINT_ATTRS;
    private int mAnimations;
    private MenuBuilder mMenu;

    static {
        ExpandedMenuView.TINT_ATTRS = new int[]{0x10100D4, 0x1010129};
    }

    public ExpandedMenuView(Context arg2, AttributeSet arg3) {
        this(arg2, arg3, 0x1010074);
    }

    public ExpandedMenuView(Context arg3, AttributeSet arg4, int arg5) {
        super(arg3, arg4);
        this.setOnItemClickListener(((AdapterView$OnItemClickListener)this));
        TintTypedArray v3 = TintTypedArray.obtainStyledAttributes(arg3, arg4, ExpandedMenuView.TINT_ATTRS, arg5, 0);
        if(v3.hasValue(0)) {
            this.setBackgroundDrawable(v3.getDrawable(0));
        }

        if(v3.hasValue(1)) {
            this.setDivider(v3.getDrawable(1));
        }

        v3.recycle();
    }

    public int getWindowAnimations() {
        return this.mAnimations;
    }

    public void initialize(MenuBuilder arg1) {
        this.mMenu = arg1;
    }

    public boolean invokeItem(MenuItemImpl arg3) {
        return this.mMenu.performItemAction(((MenuItem)arg3), 0);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.setChildrenDrawingCacheEnabled(false);
    }

    public void onItemClick(AdapterView arg1, View arg2, int arg3, long arg4) {
        this.invokeItem(this.getAdapter().getItem(arg3));
    }
}

