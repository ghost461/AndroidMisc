package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.annotation.StyleRes;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.view.menu.MenuBuilder$ItemInvoker;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuPresenter$Callback;
import android.support.v7.view.menu.MenuView;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View$MeasureSpec;
import android.view.View;
import android.view.ViewDebug$ExportedProperty;
import android.view.ViewGroup$LayoutParams;
import android.view.accessibility.AccessibilityEvent;

public class ActionMenuView extends LinearLayoutCompat implements ItemInvoker, MenuView {
    @RestrictTo(value={Scope.LIBRARY_GROUP}) public interface ActionMenuChildView {
        boolean needsDividerAfter();

        boolean needsDividerBefore();
    }

    class ActionMenuPresenterCallback implements Callback {
        ActionMenuPresenterCallback() {
            super();
        }

        public void onCloseMenu(MenuBuilder arg1, boolean arg2) {
        }

        public boolean onOpenSubMenu(MenuBuilder arg1) {
            return 0;
        }
    }

    public class LayoutParams extends android.support.v7.widget.LinearLayoutCompat$LayoutParams {
        @ViewDebug$ExportedProperty public int cellsUsed;
        @ViewDebug$ExportedProperty public boolean expandable;
        boolean expanded;
        @ViewDebug$ExportedProperty public int extraPixels;
        @ViewDebug$ExportedProperty public boolean isOverflowButton;
        @ViewDebug$ExportedProperty public boolean preventEdgeOffset;

        public LayoutParams(int arg1, int arg2) {
            super(arg1, arg2);
            this.isOverflowButton = false;
        }

        public LayoutParams(Context arg1, AttributeSet arg2) {
            super(arg1, arg2);
        }

        public LayoutParams(LayoutParams arg1) {
            super(((ViewGroup$LayoutParams)arg1));
            this.isOverflowButton = arg1.isOverflowButton;
        }

        public LayoutParams(ViewGroup$LayoutParams arg1) {
            super(arg1);
        }

        LayoutParams(int arg1, int arg2, boolean arg3) {
            super(arg1, arg2);
            this.isOverflowButton = arg3;
        }
    }

    class MenuBuilderCallback implements android.support.v7.view.menu.MenuBuilder$Callback {
        MenuBuilderCallback(ActionMenuView arg1) {
            ActionMenuView.this = arg1;
            super();
        }

        public boolean onMenuItemSelected(MenuBuilder arg1, MenuItem arg2) {
            boolean v1 = ActionMenuView.this.mOnMenuItemClickListener == null || !ActionMenuView.this.mOnMenuItemClickListener.onMenuItemClick(arg2) ? false : true;
            return v1;
        }

        public void onMenuModeChange(MenuBuilder arg2) {
            if(ActionMenuView.this.mMenuBuilderCallback != null) {
                ActionMenuView.this.mMenuBuilderCallback.onMenuModeChange(arg2);
            }
        }
    }

    public interface OnMenuItemClickListener {
        boolean onMenuItemClick(MenuItem arg1);
    }

    static final int GENERATED_ITEM_PADDING = 4;
    static final int MIN_CELL_SIZE = 56;
    private static final String TAG = "ActionMenuView";
    private Callback mActionMenuPresenterCallback;
    private boolean mFormatItems;
    private int mFormatItemsWidth;
    private int mGeneratedItemPadding;
    private MenuBuilder mMenu;
    android.support.v7.view.menu.MenuBuilder$Callback mMenuBuilderCallback;
    private int mMinCellSize;
    OnMenuItemClickListener mOnMenuItemClickListener;
    private Context mPopupContext;
    private int mPopupTheme;
    private ActionMenuPresenter mPresenter;
    private boolean mReserveOverflow;

    public ActionMenuView(Context arg2) {
        this(arg2, null);
    }

    public ActionMenuView(Context arg3, AttributeSet arg4) {
        super(arg3, arg4);
        this.setBaselineAligned(false);
        float v0 = arg3.getResources().getDisplayMetrics().density;
        this.mMinCellSize = ((int)(56f * v0));
        this.mGeneratedItemPadding = ((int)(v0 * 4f));
        this.mPopupContext = arg3;
        this.mPopupTheme = 0;
    }

    protected boolean checkLayoutParams(ViewGroup$LayoutParams arg1) {
        boolean v1 = arg1 == null || !(arg1 instanceof LayoutParams) ? false : true;
        return v1;
    }

    public void dismissPopupMenus() {
        if(this.mPresenter != null) {
            this.mPresenter.dismissPopupMenus();
        }
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent arg1) {
        return 0;
    }

    protected LayoutParams generateDefaultLayoutParams() {
        LayoutParams v0 = new LayoutParams(-2, -2);
        v0.gravity = 16;
        return v0;
    }

    protected android.support.v7.widget.LinearLayoutCompat$LayoutParams generateDefaultLayoutParams() {
        return this.generateDefaultLayoutParams();
    }

    protected ViewGroup$LayoutParams generateDefaultLayoutParams() {
        return this.generateDefaultLayoutParams();
    }

    public LayoutParams generateLayoutParams(AttributeSet arg3) {
        return new LayoutParams(this.getContext(), arg3);
    }

    protected LayoutParams generateLayoutParams(ViewGroup$LayoutParams arg2) {
        if(arg2 != null) {
            LayoutParams v0 = (arg2 instanceof LayoutParams) ? new LayoutParams(((LayoutParams)arg2)) : new LayoutParams(arg2);
            if(v0.gravity <= 0) {
                v0.gravity = 16;
            }

            return v0;
        }

        return this.generateDefaultLayoutParams();
    }

    public android.support.v7.widget.LinearLayoutCompat$LayoutParams generateLayoutParams(AttributeSet arg1) {
        return this.generateLayoutParams(arg1);
    }

    protected android.support.v7.widget.LinearLayoutCompat$LayoutParams generateLayoutParams(ViewGroup$LayoutParams arg1) {
        return this.generateLayoutParams(arg1);
    }

    public ViewGroup$LayoutParams generateLayoutParams(AttributeSet arg1) {
        return this.generateLayoutParams(arg1);
    }

    protected ViewGroup$LayoutParams generateLayoutParams(ViewGroup$LayoutParams arg1) {
        return this.generateLayoutParams(arg1);
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public LayoutParams generateOverflowButtonLayoutParams() {
        LayoutParams v0 = this.generateDefaultLayoutParams();
        v0.isOverflowButton = true;
        return v0;
    }

    public Menu getMenu() {
        ActionMenuPresenterCallback v1_1;
        if(this.mMenu == null) {
            Context v0 = this.getContext();
            this.mMenu = new MenuBuilder(v0);
            this.mMenu.setCallback(new MenuBuilderCallback(this));
            this.mPresenter = new ActionMenuPresenter(v0);
            this.mPresenter.setReserveOverflow(true);
            ActionMenuPresenter v0_1 = this.mPresenter;
            if(this.mActionMenuPresenterCallback != null) {
                Callback v1 = this.mActionMenuPresenterCallback;
            }
            else {
                v1_1 = new ActionMenuPresenterCallback();
            }

            v0_1.setCallback(((Callback)v1_1));
            this.mMenu.addMenuPresenter(this.mPresenter, this.mPopupContext);
            this.mPresenter.setMenuView(this);
        }

        return this.mMenu;
    }

    @Nullable public Drawable getOverflowIcon() {
        this.getMenu();
        return this.mPresenter.getOverflowIcon();
    }

    public int getPopupTheme() {
        return this.mPopupTheme;
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public int getWindowAnimations() {
        return 0;
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) protected boolean hasSupportDividerBeforeChildAt(int arg5) {
        int v0 = 0;
        if(arg5 == 0) {
            return 0;
        }

        View v1 = this.getChildAt(arg5 - 1);
        View v2 = this.getChildAt(arg5);
        if(arg5 < this.getChildCount() && ((v1 instanceof ActionMenuChildView))) {
            v0 = 0 | ((ActionMenuChildView)v1).needsDividerAfter();
        }

        if(arg5 > 0 && ((v2 instanceof ActionMenuChildView))) {
            v0 |= ((ActionMenuChildView)v2).needsDividerBefore();
        }

        return ((boolean)v0);
    }

    public boolean hideOverflowMenu() {
        boolean v0 = this.mPresenter == null || !this.mPresenter.hideOverflowMenu() ? false : true;
        return v0;
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public void initialize(MenuBuilder arg1) {
        this.mMenu = arg1;
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public boolean invokeItem(MenuItemImpl arg3) {
        return this.mMenu.performItemAction(((MenuItem)arg3), 0);
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public boolean isOverflowMenuShowPending() {
        boolean v0 = this.mPresenter == null || !this.mPresenter.isOverflowMenuShowPending() ? false : true;
        return v0;
    }

    public boolean isOverflowMenuShowing() {
        boolean v0 = this.mPresenter == null || !this.mPresenter.isOverflowMenuShowing() ? false : true;
        return v0;
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public boolean isOverflowReserved() {
        return this.mReserveOverflow;
    }

    static int measureChildForCells(View arg5, int arg6, int arg7, int arg8, int arg9) {
        ViewGroup$LayoutParams v0 = arg5.getLayoutParams();
        arg8 = View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(arg8) - arg9, View$MeasureSpec.getMode(arg8));
        View v9 = (arg5 instanceof ActionMenuItemView) ? arg5 : null;
        boolean v1 = false;
        arg9 = v9 == null || !((ActionMenuItemView)v9).hasText() ? 0 : 1;
        int v3 = 2;
        if(arg7 > 0) {
            if(arg9 != 0 && arg7 < v3) {
                goto label_36;
            }

            arg5.measure(View$MeasureSpec.makeMeasureSpec(arg7 * arg6, 0x80000000), arg8);
            arg7 = arg5.getMeasuredWidth();
            int v4 = arg7 / arg6;
            if(arg7 % arg6 != 0) {
                ++v4;
            }

            if(arg9 != 0 && v4 < v3) {
                goto label_37;
            }

            v3 = v4;
        }
        else {
        label_36:
            v3 = 0;
        }

    label_37:
        if(!((LayoutParams)v0).isOverflowButton && arg9 != 0) {
            v1 = true;
        }

        ((LayoutParams)v0).expandable = v1;
        ((LayoutParams)v0).cellsUsed = v3;
        arg5.measure(View$MeasureSpec.makeMeasureSpec(arg6 * v3, 0x40000000), arg8);
        return v3;
    }

    public void onConfigurationChanged(Configuration arg2) {
        super.onConfigurationChanged(arg2);
        if(this.mPresenter != null) {
            this.mPresenter.updateMenuView(false);
            if(this.mPresenter.isOverflowMenuShowing()) {
                this.mPresenter.hideOverflowMenu();
                this.mPresenter.showOverflowMenu();
            }
        }
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.dismissPopupMenus();
    }

    protected void onLayout(boolean arg19, int arg20, int arg21, int arg22, int arg23) {
        ViewGroup$LayoutParams v7_1;
        View v6_1;
        int v7;
        int v15;
        ActionMenuView v0 = this;
        if(!v0.mFormatItems) {
            super.onLayout(arg19, arg20, arg21, arg22, arg23);
            return;
        }

        int v1 = this.getChildCount();
        int v2 = (arg23 - arg21) / 2;
        int v3 = this.getDividerWidth();
        int v4 = arg22 - arg20;
        int v5 = v4 - this.getPaddingRight() - this.getPaddingLeft();
        boolean v6 = ViewUtils.isLayoutRtl(((View)this));
        int v10 = v5;
        v5 = 0;
        int v8 = 0;
        int v9 = 0;
        while(true) {
            int v11 = 8;
            if(v5 >= v1) {
                break;
            }

            View v13 = v0.getChildAt(v5);
            if(v13.getVisibility() == v11) {
            }
            else {
                ViewGroup$LayoutParams v11_1 = v13.getLayoutParams();
                if(((LayoutParams)v11_1).isOverflowButton) {
                    v8 = v13.getMeasuredWidth();
                    if(v0.hasSupportDividerBeforeChildAt(v5)) {
                        v8 += v3;
                    }

                    int v14 = v13.getMeasuredHeight();
                    if(v6) {
                        v15 = this.getPaddingLeft() + ((LayoutParams)v11_1).leftMargin;
                        v11 = v15 + v8;
                    }
                    else {
                        v11 = this.getWidth() - this.getPaddingRight() - ((LayoutParams)v11_1).rightMargin;
                        v15 = v11 - v8;
                    }

                    v7 = v2 - v14 / 2;
                    v13.layout(v15, v7, v11, v14 + v7);
                    v10 -= v8;
                    v8 = 1;
                }
                else {
                    v10 -= v13.getMeasuredWidth() + ((LayoutParams)v11_1).leftMargin + ((LayoutParams)v11_1).rightMargin;
                    v0.hasSupportDividerBeforeChildAt(v5);
                    ++v9;
                }
            }

            ++v5;
        }

        if(v1 == 1 && v8 == 0) {
            View v1_1 = v0.getChildAt(0);
            v3 = v1_1.getMeasuredWidth();
            v5 = v1_1.getMeasuredHeight();
            v4 = v4 / 2 - v3 / 2;
            v2 -= v5 / 2;
            v1_1.layout(v4, v2, v3 + v4, v5 + v2);
            return;
        }

        v9 -= v8 ^ 1;
        if(v9 > 0) {
            v7 = v10 / v9;
            v3 = 0;
        }
        else {
            v3 = 0;
            v7 = 0;
        }

        v4 = Math.max(v3, v7);
        if(v6) {
            v5 = this.getWidth() - this.getPaddingRight();
            goto label_91;
        }
        else {
            v5 = this.getPaddingLeft();
            while(true) {
                if(v3 < v1) {
                    v6_1 = v0.getChildAt(v3);
                    v7_1 = v6_1.getLayoutParams();
                    if(v6_1.getVisibility() != v11) {
                        if(((LayoutParams)v7_1).isOverflowButton) {
                        }
                        else {
                            v5 += ((LayoutParams)v7_1).leftMargin;
                            v8 = v6_1.getMeasuredWidth();
                            v9 = v6_1.getMeasuredHeight();
                            v10 = v2 - v9 / 2;
                            v6_1.layout(v5, v10, v5 + v8, v9 + v10);
                            v5 += v8 + ((LayoutParams)v7_1).rightMargin + v4;
                        }
                    }

                    ++v3;
                    continue;
                }

                return;
            }

        label_91:
            while(v3 < v1) {
                v6_1 = v0.getChildAt(v3);
                v7_1 = v6_1.getLayoutParams();
                if(v6_1.getVisibility() != v11) {
                    if(((LayoutParams)v7_1).isOverflowButton) {
                    }
                    else {
                        v5 -= ((LayoutParams)v7_1).rightMargin;
                        v8 = v6_1.getMeasuredWidth();
                        v9 = v6_1.getMeasuredHeight();
                        v10 = v2 - v9 / 2;
                        v6_1.layout(v5 - v8, v10, v5, v9 + v10);
                        v5 -= v8 + ((LayoutParams)v7_1).leftMargin + v4;
                    }
                }

                ++v3;
            }
        }
    }

    protected void onMeasure(int arg6, int arg7) {
        boolean v0 = this.mFormatItems;
        boolean v1 = View$MeasureSpec.getMode(arg6) == 0x40000000 ? true : false;
        this.mFormatItems = v1;
        if(v0 != this.mFormatItems) {
            this.mFormatItemsWidth = 0;
        }

        int v0_1 = View$MeasureSpec.getSize(arg6);
        if((this.mFormatItems) && this.mMenu != null && v0_1 != this.mFormatItemsWidth) {
            this.mFormatItemsWidth = v0_1;
            this.mMenu.onItemsChanged(true);
        }

        v0_1 = this.getChildCount();
        if(!this.mFormatItems || v0_1 <= 0) {
            int v1_1;
            for(v1_1 = 0; v1_1 < v0_1; ++v1_1) {
                ViewGroup$LayoutParams v2 = this.getChildAt(v1_1).getLayoutParams();
                ((LayoutParams)v2).rightMargin = 0;
                ((LayoutParams)v2).leftMargin = 0;
            }

            super.onMeasure(arg6, arg7);
        }
        else {
            this.onMeasureExactFormat(arg6, arg7);
        }
    }

    private void onMeasureExactFormat(int arg35, int arg36) {
        int v31;
        int v33;
        int v32;
        long v4_1;
        int v28;
        int v27;
        int v11_1;
        int v8_1;
        boolean v2_1;
        int v22;
        int v23;
        int v21;
        ActionMenuView v0 = this;
        int v1 = View$MeasureSpec.getMode(arg36);
        int v2 = View$MeasureSpec.getSize(arg35);
        int v3 = View$MeasureSpec.getSize(arg36);
        int v4 = this.getPaddingLeft() + this.getPaddingRight();
        int v5 = this.getPaddingTop() + this.getPaddingBottom();
        int v6 = ActionMenuView.getChildMeasureSpec(arg36, v5, -2);
        v2 -= v4;
        v4 = v2 / v0.mMinCellSize;
        int v7 = v2 % v0.mMinCellSize;
        if(v4 == 0) {
            v0.setMeasuredDimension(v2, 0);
            return;
        }

        int v9 = v0.mMinCellSize + v7 / v4;
        v7 = this.getChildCount();
        int v14 = v4;
        v4 = 0;
        int v10 = 0;
        int v12 = 0;
        int v13 = 0;
        int v15 = 0;
        int v16 = 0;
        long v19 = 0;
        while(v4 < v7) {
            View v11 = v0.getChildAt(v4);
            v21 = v3;
            if(v11.getVisibility() == 8) {
                v23 = v2;
            }
            else {
                boolean v3_1 = v11 instanceof ActionMenuItemView;
                ++v13;
                if(v3_1) {
                    v22 = v13;
                    v23 = v2;
                    v2_1 = false;
                    v11.setPadding(v0.mGeneratedItemPadding, 0, v0.mGeneratedItemPadding, 0);
                }
                else {
                    v23 = v2;
                    v22 = v13;
                    v2_1 = false;
                }

                ViewGroup$LayoutParams v8 = v11.getLayoutParams();
                ((LayoutParams)v8).expanded = v2_1;
                ((LayoutParams)v8).extraPixels = ((int)v2_1);
                ((LayoutParams)v8).cellsUsed = ((int)v2_1);
                ((LayoutParams)v8).expandable = v2_1;
                ((LayoutParams)v8).leftMargin = ((int)v2_1);
                ((LayoutParams)v8).rightMargin = ((int)v2_1);
                v2_1 = !v3_1 || !v11.hasText() ? false : true;
                ((LayoutParams)v8).preventEdgeOffset = v2_1;
                v2 = ((LayoutParams)v8).isOverflowButton ? 1 : v14;
                v2 = ActionMenuView.measureChildForCells(v11, v9, v2, v6, v5);
                v3 = Math.max(v15, v2);
                if(((LayoutParams)v8).expandable) {
                    ++v16;
                }

                if(((LayoutParams)v8).isOverflowButton) {
                    v12 = 1;
                }

                v14 -= v2;
                v10 = Math.max(v10, v11.getMeasuredHeight());
                if(v2 == 1) {
                    v13 = v22;
                    v15 = v3;
                    v19 |= ((long)(1 << v4));
                    goto label_99;
                }

                v13 = v22;
                v15 = v3;
            }

        label_99:
            ++v4;
            v3 = v21;
            v2 = v23;
        }

        v23 = v2;
        v21 = v3;
        v2 = 2;
        v3 = v12 == 0 || v13 != v2 ? 0 : 1;
        v4 = 0;
        while(true) {
            if(v16 <= 0) {
                break;
            }
            else if(v14 > 0) {
                v5 = 0;
                v8_1 = 0;
                v11_1 = 0x7FFFFFFF;
                long v24 = 0;
                while(v5 < v7) {
                    ViewGroup$LayoutParams v2_2 = v0.getChildAt(v5).getLayoutParams();
                    v27 = v4;
                    if(!((LayoutParams)v2_2).expandable) {
                        v28 = v5;
                    }
                    else if(((LayoutParams)v2_2).cellsUsed < v11_1) {
                        v28 = v5;
                        v11_1 = ((LayoutParams)v2_2).cellsUsed;
                        v24 = ((long)(1 << v5));
                        v8_1 = 1;
                    }
                    else {
                        v28 = v5;
                        if(((LayoutParams)v2_2).cellsUsed == v11_1) {
                            ++v8_1;
                            v24 |= ((long)(1 << v28));
                        }
                    }

                    v5 = v28 + 1;
                    v4 = v27;
                }

                v27 = v4;
                v4_1 = v19 | v24;
                if(v8_1 > v14) {
                    v32 = v6;
                    v33 = v7;
                    v31 = v10;
                    goto label_209;
                }

                ++v11_1;
                v19 = v4_1;
                v2 = 0;
                while(v2 < v7) {
                    View v4_2 = v0.getChildAt(v2);
                    ViewGroup$LayoutParams v5_1 = v4_2.getLayoutParams();
                    v31 = v10;
                    v32 = v6;
                    v33 = v7;
                    long v6_1 = ((long)(1 << v2));
                    if((v24 & v6_1) != 0) {
                        if(v3 == 0 || !((LayoutParams)v5_1).preventEdgeOffset) {
                            v6 = 1;
                        }
                        else {
                            v6 = 1;
                            if(v14 == 1) {
                                v4_2.setPadding(v0.mGeneratedItemPadding + v9, 0, v0.mGeneratedItemPadding, 0);
                            }
                        }

                        ((LayoutParams)v5_1).cellsUsed += v6;
                        ((LayoutParams)v5_1).expanded = ((boolean)v6);
                        --v14;
                    }
                    else if(((LayoutParams)v5_1).cellsUsed == v11_1) {
                        v19 |= v6_1;
                    }

                    ++v2;
                    v10 = v31;
                    v6 = v32;
                    v7 = v33;
                }

                v4 = 1;
                continue;
            }
            else {
                break;
            }
        }

        v27 = v4;
        v32 = v6;
        v33 = v7;
        v31 = v10;
        v4_1 = v19;
    label_209:
        if(v12 == 0) {
            v2 = 1;
            if(v13 == 1) {
                v3 = 1;
            }
            else {
                goto label_215;
            }
        }
        else {
            v2 = 1;
        label_215:
            v3 = 0;
        }

        if(v14 <= 0 || v4_1 == 0) {
        label_314:
            v2 = v33;
            v3 = 0;
            v11_1 = v27;
        }
        else {
            if(v14 >= v13 - v2 && v3 == 0 && v15 <= v2) {
                goto label_314;
            }

            float v2_3 = ((float)Long.bitCount(v4_1));
            if(v3 == 0) {
                float v6_2 = 0.5f;
                if(Long.compare(v4_1 & 1, 0) != 0) {
                    v3 = 0;
                    if(!v0.getChildAt(0).getLayoutParams().preventEdgeOffset) {
                        v2_3 -= v6_2;
                    }
                }
                else {
                    v3 = 0;
                }

                v7 = v33 - 1;
                if((v4_1 & (((long)(1 << v7)))) == 0) {
                    goto label_254;
                }

                if(v0.getChildAt(v7).getLayoutParams().preventEdgeOffset) {
                    goto label_254;
                }

                v2_3 -= v6_2;
            }
            else {
                v3 = 0;
            }

        label_254:
            v8_1 = v2_3 > 0f ? ((int)((((float)(v14 * v9))) / v2_3)) : 0;
            v11_1 = v27;
            v2 = v33;
            for(v6 = 0; v6 < v2; ++v6) {
                if((v4_1 & (((long)(1 << v6)))) == 0) {
                }
                else {
                    View v7_1 = v0.getChildAt(v6);
                    ViewGroup$LayoutParams v10_1 = v7_1.getLayoutParams();
                    if((v7_1 instanceof ActionMenuItemView)) {
                        ((LayoutParams)v10_1).extraPixels = v8_1;
                        ((LayoutParams)v10_1).expanded = true;
                        if(v6 == 0 && !((LayoutParams)v10_1).preventEdgeOffset) {
                            ((LayoutParams)v10_1).leftMargin = -v8_1 / 2;
                        }
                    }
                    else {
                        v14 = 2;
                        if(((LayoutParams)v10_1).isOverflowButton) {
                            ((LayoutParams)v10_1).extraPixels = v8_1;
                            ((LayoutParams)v10_1).expanded = true;
                            ((LayoutParams)v10_1).rightMargin = -v8_1 / v14;
                        }
                        else {
                            goto label_304;
                        }
                    }

                    v11_1 = 1;
                    goto label_312;
                label_304:
                    if(v6 != 0) {
                        ((LayoutParams)v10_1).leftMargin = v8_1 / 2;
                    }

                    if(v6 == v2 - 1) {
                        goto label_312;
                    }

                    ((LayoutParams)v10_1).rightMargin = v8_1 / 2;
                }

            label_312:
            }
        }

        v4 = 0x40000000;
        if(v11_1 != 0) {
            while(v3 < v2) {
                View v5_2 = v0.getChildAt(v3);
                ViewGroup$LayoutParams v6_3 = v5_2.getLayoutParams();
                if(!((LayoutParams)v6_3).expanded) {
                    v7 = v32;
                }
                else {
                    v7 = v32;
                    v5_2.measure(View$MeasureSpec.makeMeasureSpec(((LayoutParams)v6_3).cellsUsed * v9 + ((LayoutParams)v6_3).extraPixels, v4), v7);
                }

                ++v3;
                v32 = v7;
            }
        }

        if(v1 != v4) {
            v2 = v23;
            v1 = v31;
        }
        else {
            v1 = v21;
            v2 = v23;
        }

        v0.setMeasuredDimension(v2, v1);
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public MenuBuilder peekMenu() {
        return this.mMenu;
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public void setExpandedActionViewsExclusive(boolean arg2) {
        this.mPresenter.setExpandedActionViewsExclusive(arg2);
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public void setMenuCallbacks(Callback arg1, android.support.v7.view.menu.MenuBuilder$Callback arg2) {
        this.mActionMenuPresenterCallback = arg1;
        this.mMenuBuilderCallback = arg2;
    }

    public void setOnMenuItemClickListener(OnMenuItemClickListener arg1) {
        this.mOnMenuItemClickListener = arg1;
    }

    public void setOverflowIcon(@Nullable Drawable arg2) {
        this.getMenu();
        this.mPresenter.setOverflowIcon(arg2);
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public void setOverflowReserved(boolean arg1) {
        this.mReserveOverflow = arg1;
    }

    public void setPopupTheme(@StyleRes int arg3) {
        if(this.mPopupTheme != arg3) {
            this.mPopupTheme = arg3;
            this.mPopupContext = arg3 == 0 ? this.getContext() : new ContextThemeWrapper(this.getContext(), arg3);
        }
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public void setPresenter(ActionMenuPresenter arg1) {
        this.mPresenter = arg1;
        this.mPresenter.setMenuView(this);
    }

    public boolean showOverflowMenu() {
        boolean v0 = this.mPresenter == null || !this.mPresenter.showOverflowMenu() ? false : true;
        return v0;
    }
}

