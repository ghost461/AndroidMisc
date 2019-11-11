package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ActionProvider$SubUiVisibilityListener;
import android.support.v4.view.ActionProvider;
import android.support.v7.appcompat.R$attr;
import android.support.v7.appcompat.R$layout;
import android.support.v7.view.ActionBarPolicy;
import android.support.v7.view.menu.ActionMenuItemView$PopupCallback;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.view.menu.BaseMenuPresenter;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuPopup;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.view.menu.MenuPresenter$Callback;
import android.support.v7.view.menu.MenuView$ItemView;
import android.support.v7.view.menu.MenuView;
import android.support.v7.view.menu.ShowableListMenu;
import android.support.v7.view.menu.SubMenuBuilder;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View$MeasureSpec;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.util.ArrayList;

class ActionMenuPresenter extends BaseMenuPresenter implements SubUiVisibilityListener {
    class ActionButtonSubmenu extends MenuPopupHelper {
        public ActionButtonSubmenu(ActionMenuPresenter arg7, Context arg8, SubMenuBuilder arg9, View arg10) {
            OverflowMenuButton v8_1;
            ActionMenuPresenter.this = arg7;
            super(arg8, arg9, arg10, false, attr.actionOverflowMenuStyle);
            if(!arg9.getItem().isActionButton()) {
                if(arg7.mOverflowButton == null) {
                    MenuView v8 = arg7.mMenuView;
                }
                else {
                    v8_1 = arg7.mOverflowButton;
                }

                this.setAnchorView(((View)v8_1));
            }

            this.setPresenterCallback(arg7.mPopupPresenterCallback);
        }

        protected void onDismiss() {
            ActionMenuPresenter.this.mActionButtonPopup = null;
            ActionMenuPresenter.this.mOpenSubMenuId = 0;
            super.onDismiss();
        }
    }

    class ActionMenuPopupCallback extends PopupCallback {
        ActionMenuPopupCallback(ActionMenuPresenter arg1) {
            ActionMenuPresenter.this = arg1;
            super();
        }

        public ShowableListMenu getPopup() {
            MenuPopup v0;
            if(ActionMenuPresenter.this.mActionButtonPopup != null) {
                v0 = ActionMenuPresenter.this.mActionButtonPopup.getPopup();
            }
            else {
                ShowableListMenu v0_1 = null;
            }

            return ((ShowableListMenu)v0);
        }
    }

    class OpenOverflowRunnable implements Runnable {
        private OverflowPopup mPopup;

        public OpenOverflowRunnable(ActionMenuPresenter arg1, OverflowPopup arg2) {
            ActionMenuPresenter.this = arg1;
            super();
            this.mPopup = arg2;
        }

        public void run() {
            if(ActionMenuPresenter.this.mMenu != null) {
                ActionMenuPresenter.this.mMenu.changeMenuMode();
            }

            MenuView v0 = ActionMenuPresenter.this.mMenuView;
            if(v0 != null && ((View)v0).getWindowToken() != null && (this.mPopup.tryShow())) {
                ActionMenuPresenter.this.mOverflowPopup = this.mPopup;
            }

            ActionMenuPresenter.this.mPostedOpenRunnable = null;
        }
    }

    class OverflowMenuButton extends AppCompatImageView implements ActionMenuChildView {
        private final float[] mTempPts;

        public OverflowMenuButton(ActionMenuPresenter arg3, Context arg4) {
            ActionMenuPresenter.this = arg3;
            super(arg4, null, attr.actionOverflowButtonStyle);
            this.mTempPts = new float[2];
            this.setClickable(true);
            this.setFocusable(true);
            this.setVisibility(0);
            this.setEnabled(true);
            TooltipCompat.setTooltipText(((View)this), this.getContentDescription());
            this.setOnTouchListener(new ForwardingListener(((View)this), arg3) {
                public ShowableListMenu getPopup() {
                    if(this.this$1.this$0.mOverflowPopup == null) {
                        return null;
                    }

                    return this.this$1.this$0.mOverflowPopup.getPopup();
                }

                public boolean onForwardingStarted() {
                    this.this$1.this$0.showOverflowMenu();
                    return 1;
                }

                public boolean onForwardingStopped() {
                    if(this.this$1.this$0.mPostedOpenRunnable != null) {
                        return 0;
                    }

                    this.this$1.this$0.hideOverflowMenu();
                    return 1;
                }
            });
        }

        public boolean needsDividerAfter() {
            return 0;
        }

        public boolean needsDividerBefore() {
            return 0;
        }

        public boolean performClick() {
            if(super.performClick()) {
                return 1;
            }

            this.playSoundEffect(0);
            ActionMenuPresenter.this.showOverflowMenu();
            return 1;
        }

        protected boolean setFrame(int arg5, int arg6, int arg7, int arg8) {
            boolean v5 = super.setFrame(arg5, arg6, arg7, arg8);
            Drawable v6 = this.getDrawable();
            Drawable v7 = this.getBackground();
            if(v6 != null && v7 != null) {
                arg6 = this.getWidth();
                arg8 = this.getHeight();
                int v0 = Math.max(arg6, arg8) / 2;
                arg6 = (arg6 + (this.getPaddingLeft() - this.getPaddingRight())) / 2;
                arg8 = (arg8 + (this.getPaddingTop() - this.getPaddingBottom())) / 2;
                DrawableCompat.setHotspotBounds(v7, arg6 - v0, arg8 - v0, arg6 + v0, arg8 + v0);
            }

            return v5;
        }
    }

    class OverflowPopup extends MenuPopupHelper {
        public OverflowPopup(ActionMenuPresenter arg7, Context arg8, MenuBuilder arg9, View arg10, boolean arg11) {
            ActionMenuPresenter.this = arg7;
            super(arg8, arg9, arg10, arg11, attr.actionOverflowMenuStyle);
            this.setGravity(0x800005);
            this.setPresenterCallback(arg7.mPopupPresenterCallback);
        }

        protected void onDismiss() {
            if(ActionMenuPresenter.this.mMenu != null) {
                ActionMenuPresenter.this.mMenu.close();
            }

            ActionMenuPresenter.this.mOverflowPopup = null;
            super.onDismiss();
        }
    }

    class PopupPresenterCallback implements Callback {
        PopupPresenterCallback(ActionMenuPresenter arg1) {
            ActionMenuPresenter.this = arg1;
            super();
        }

        public void onCloseMenu(MenuBuilder arg3, boolean arg4) {
            if((arg3 instanceof SubMenuBuilder)) {
                arg3.getRootMenu().close(false);
            }

            Callback v0 = ActionMenuPresenter.this.getCallback();
            if(v0 != null) {
                v0.onCloseMenu(arg3, arg4);
            }
        }

        public boolean onOpenSubMenu(MenuBuilder arg4) {
            boolean v0 = false;
            if(arg4 == null) {
                return 0;
            }

            ActionMenuPresenter.this.mOpenSubMenuId = arg4.getItem().getItemId();
            Callback v1 = ActionMenuPresenter.this.getCallback();
            if(v1 != null) {
                v0 = v1.onOpenSubMenu(arg4);
            }

            return v0;
        }
    }

    class SavedState implements Parcelable {
        final class android.support.v7.widget.ActionMenuPresenter$SavedState$1 implements Parcelable$Creator {
            android.support.v7.widget.ActionMenuPresenter$SavedState$1() {
                super();
            }

            public SavedState createFromParcel(Parcel arg2) {
                return new SavedState(arg2);
            }

            public Object createFromParcel(Parcel arg1) {
                return this.createFromParcel(arg1);
            }

            public SavedState[] newArray(int arg1) {
                return new SavedState[arg1];
            }

            public Object[] newArray(int arg1) {
                return this.newArray(arg1);
            }
        }

        public static final Parcelable$Creator CREATOR;
        public int openSubMenuId;

        static {
            SavedState.CREATOR = new android.support.v7.widget.ActionMenuPresenter$SavedState$1();
        }

        SavedState() {
            super();
        }

        SavedState(Parcel arg1) {
            super();
            this.openSubMenuId = arg1.readInt();
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel arg1, int arg2) {
            arg1.writeInt(this.openSubMenuId);
        }
    }

    private static final String TAG = "ActionMenuPresenter";
    private final SparseBooleanArray mActionButtonGroups;
    ActionButtonSubmenu mActionButtonPopup;
    private int mActionItemWidthLimit;
    private boolean mExpandedActionViewsExclusive;
    private int mMaxItems;
    private boolean mMaxItemsSet;
    private int mMinCellSize;
    int mOpenSubMenuId;
    OverflowMenuButton mOverflowButton;
    OverflowPopup mOverflowPopup;
    private Drawable mPendingOverflowIcon;
    private boolean mPendingOverflowIconSet;
    private ActionMenuPopupCallback mPopupCallback;
    final PopupPresenterCallback mPopupPresenterCallback;
    OpenOverflowRunnable mPostedOpenRunnable;
    private boolean mReserveOverflow;
    private boolean mReserveOverflowSet;
    private View mScrapActionButtonView;
    private boolean mStrictWidthLimit;
    private int mWidthLimit;
    private boolean mWidthLimitSet;

    public ActionMenuPresenter(Context arg3) {
        super(arg3, layout.abc_action_menu_layout, layout.abc_action_menu_item_layout);
        this.mActionButtonGroups = new SparseBooleanArray();
        this.mPopupPresenterCallback = new PopupPresenterCallback(this);
    }

    static MenuBuilder access$000(ActionMenuPresenter arg0) {
        return arg0.mMenu;
    }

    static MenuBuilder access$100(ActionMenuPresenter arg0) {
        return arg0.mMenu;
    }

    static MenuView access$200(ActionMenuPresenter arg0) {
        return arg0.mMenuView;
    }

    static MenuBuilder access$300(ActionMenuPresenter arg0) {
        return arg0.mMenu;
    }

    static MenuBuilder access$400(ActionMenuPresenter arg0) {
        return arg0.mMenu;
    }

    static MenuView access$500(ActionMenuPresenter arg0) {
        return arg0.mMenuView;
    }

    public void bindItemView(MenuItemImpl arg2, ItemView arg3) {
        arg3.initialize(arg2, 0);
        ((ActionMenuItemView)arg3).setItemInvoker(this.mMenuView);
        if(this.mPopupCallback == null) {
            this.mPopupCallback = new ActionMenuPopupCallback(this);
        }

        ((ActionMenuItemView)arg3).setPopupCallback(this.mPopupCallback);
    }

    public boolean dismissPopupMenus() {
        return this.hideOverflowMenu() | this.hideSubMenus();
    }

    public boolean filterLeftoverView(ViewGroup arg3, int arg4) {
        if(arg3.getChildAt(arg4) == this.mOverflowButton) {
            return 0;
        }

        return super.filterLeftoverView(arg3, arg4);
    }

    private View findViewForItem(MenuItem arg7) {
        MenuView v0 = this.mMenuView;
        View v1 = null;
        if(v0 == null) {
            return v1;
        }

        int v2 = ((ViewGroup)v0).getChildCount();
        int v3;
        for(v3 = 0; v3 < v2; ++v3) {
            View v4 = ((ViewGroup)v0).getChildAt(v3);
            if(((v4 instanceof ItemView)) && v4.getItemData() == arg7) {
                return v4;
            }
        }

        return v1;
    }

    public boolean flagActionItems() {
        int v17;
        boolean v15_1;
        int v12;
        int v3;
        ArrayList v1;
        ActionMenuPresenter v0 = this;
        int v2 = 0;
        if(v0.mMenu != null) {
            v1 = v0.mMenu.getVisibleItems();
            v3 = v1.size();
        }
        else {
            v1 = null;
            v3 = 0;
        }

        int v4 = v0.mMaxItems;
        int v5 = v0.mActionItemWidthLimit;
        int v6 = View$MeasureSpec.makeMeasureSpec(0, 0);
        MenuView v7 = v0.mMenuView;
        int v11 = v4;
        v4 = 0;
        int v8 = 0;
        int v9 = 0;
        int v10 = 0;
        while(v4 < v3) {
            Object v13 = v1.get(v4);
            if(((MenuItemImpl)v13).requiresActionButton()) {
                ++v8;
            }
            else if(((MenuItemImpl)v13).requestsActionButton()) {
                ++v10;
            }
            else {
                v9 = 1;
            }

            if((v0.mExpandedActionViewsExclusive) && (((MenuItemImpl)v13).isActionViewExpanded())) {
                v11 = 0;
            }

            ++v4;
        }

        if((v0.mReserveOverflow) && (v9 != 0 || v10 + v8 > v11)) {
            --v11;
        }

        v11 -= v8;
        SparseBooleanArray v4_1 = v0.mActionButtonGroups;
        v4_1.clear();
        if(v0.mStrictWidthLimit) {
            v8 = v5 / v0.mMinCellSize;
            v9 = v5 % v0.mMinCellSize / v8 + v0.mMinCellSize;
        }
        else {
            v8 = 0;
            v9 = 0;
        }

        v10 = v5;
        v5 = 0;
        int v13_1 = 0;
        while(v5 < v3) {
            Object v14 = v1.get(v5);
            if(((MenuItemImpl)v14).requiresActionButton()) {
                View v15 = v0.getItemView(((MenuItemImpl)v14), v0.mScrapActionButtonView, ((ViewGroup)v7));
                if(v0.mScrapActionButtonView == null) {
                    v0.mScrapActionButtonView = v15;
                }

                if(v0.mStrictWidthLimit) {
                    v8 -= ActionMenuView.measureChildForCells(v15, v9, v8, v6, v2);
                }
                else {
                    v15.measure(v6, v6);
                }

                v12 = v15.getMeasuredWidth();
                v10 -= v12;
                if(v13_1 == 0) {
                    v13_1 = v12;
                }

                v12 = ((MenuItemImpl)v14).getGroupId();
                if(v12 != 0) {
                    v15_1 = true;
                    v4_1.put(v12, true);
                }
                else {
                    v15_1 = true;
                }

                ((MenuItemImpl)v14).setIsActionButton(v15_1);
                v17 = v3;
            }
            else {
                if(((MenuItemImpl)v14).requestsActionButton()) {
                    v12 = ((MenuItemImpl)v14).getGroupId();
                    v15_1 = v4_1.get(v12);
                    if(v11 <= 0 && !v15_1) {
                        goto label_102;
                    }
                    else if(v10 > 0) {
                        if((v0.mStrictWidthLimit) && v8 <= 0) {
                            goto label_102;
                        }

                        v2 = 1;
                    }
                    else {
                    label_102:
                        v2 = 0;
                    }

                    if(v2 != 0) {
                        int v16 = v2;
                        View v2_1 = v0.getItemView(((MenuItemImpl)v14), v0.mScrapActionButtonView, ((ViewGroup)v7));
                        v17 = v3;
                        if(v0.mScrapActionButtonView == null) {
                            v0.mScrapActionButtonView = v2_1;
                        }

                        if(v0.mStrictWidthLimit) {
                            int v18 = ActionMenuView.measureChildForCells(v2_1, v9, v8, v6, 0);
                            v8 -= v18;
                            if(v18 == 0) {
                                v16 = 0;
                            }
                        }
                        else {
                            v2_1.measure(v6, v6);
                        }

                        v2 = v2_1.getMeasuredWidth();
                        v10 -= v2;
                        if(v13_1 == 0) {
                            v13_1 = v2;
                        }

                        if(v0.mStrictWidthLimit) {
                            v2 = v10 >= 0 ? 1 : 0;
                            v2 &= v16;
                            goto label_141;
                        }

                        v2 = v10 + v13_1 > 0 ? 1 : 0;
                        v2 &= v16;
                    }
                    else {
                        v17 = v3;
                    }

                label_141:
                    if(v2 != 0 && v12 != 0) {
                        v4_1.put(v12, true);
                    }
                    else if(v15_1) {
                        v4_1.put(v12, false);
                        for(v3 = 0; v3 < v5; ++v3) {
                            Object v15_2 = v1.get(v3);
                            if(((MenuItemImpl)v15_2).getGroupId() == v12) {
                                if(((MenuItemImpl)v15_2).isActionButton()) {
                                    ++v11;
                                }

                                ((MenuItemImpl)v15_2).setIsActionButton(false);
                            }
                        }
                    }

                    if(v2 != 0) {
                        --v11;
                    }

                    ((MenuItemImpl)v14).setIsActionButton(((boolean)v2));
                    goto label_169;
                }

                v17 = v3;
                ((MenuItemImpl)v14).setIsActionButton(false);
            }

        label_169:
            ++v5;
            v3 = v17;
            v0 = this;
            v2 = 0;
        }

        return 1;
    }

    public View getItemView(MenuItemImpl arg3, View arg4, ViewGroup arg5) {
        View v0 = arg3.getActionView();
        if(v0 == null || (arg3.hasCollapsibleActionView())) {
            v0 = super.getItemView(arg3, arg4, arg5);
        }

        int v3 = arg3.isActionViewExpanded() ? 8 : 0;
        v0.setVisibility(v3);
        ViewGroup$LayoutParams v3_1 = v0.getLayoutParams();
        if(!((ActionMenuView)arg5).checkLayoutParams(v3_1)) {
            v0.setLayoutParams(((ActionMenuView)arg5).generateLayoutParams(v3_1));
        }

        return v0;
    }

    public MenuView getMenuView(ViewGroup arg2) {
        MenuView v0 = this.mMenuView;
        MenuView v2 = super.getMenuView(arg2);
        if(v0 != v2) {
            v2.setPresenter(this);
        }

        return v2;
    }

    public Drawable getOverflowIcon() {
        if(this.mOverflowButton != null) {
            return this.mOverflowButton.getDrawable();
        }

        if(this.mPendingOverflowIconSet) {
            return this.mPendingOverflowIcon;
        }

        return null;
    }

    public boolean hideOverflowMenu() {
        if(this.mPostedOpenRunnable != null && this.mMenuView != null) {
            this.mMenuView.removeCallbacks(this.mPostedOpenRunnable);
            this.mPostedOpenRunnable = null;
            return 1;
        }

        OverflowPopup v0 = this.mOverflowPopup;
        if(v0 != null) {
            ((MenuPopupHelper)v0).dismiss();
            return 1;
        }

        return 0;
    }

    public boolean hideSubMenus() {
        if(this.mActionButtonPopup != null) {
            this.mActionButtonPopup.dismiss();
            return 1;
        }

        return 0;
    }

    public void initForMenu(@NonNull Context arg5, @Nullable MenuBuilder arg6) {
        super.initForMenu(arg5, arg6);
        Resources v6 = arg5.getResources();
        ActionBarPolicy v5 = ActionBarPolicy.get(arg5);
        if(!this.mReserveOverflowSet) {
            this.mReserveOverflow = v5.showsOverflowMenuButton();
        }

        if(!this.mWidthLimitSet) {
            this.mWidthLimit = v5.getEmbeddedMenuWidthLimit();
        }

        if(!this.mMaxItemsSet) {
            this.mMaxItems = v5.getMaxActionButtons();
        }

        int v5_1 = this.mWidthLimit;
        Drawable v1 = null;
        if(this.mReserveOverflow) {
            if(this.mOverflowButton == null) {
                this.mOverflowButton = new OverflowMenuButton(this, this.mSystemContext);
                if(this.mPendingOverflowIconSet) {
                    this.mOverflowButton.setImageDrawable(this.mPendingOverflowIcon);
                    this.mPendingOverflowIcon = v1;
                    this.mPendingOverflowIconSet = false;
                }

                int v0 = View$MeasureSpec.makeMeasureSpec(0, 0);
                this.mOverflowButton.measure(v0, v0);
            }

            v5_1 -= this.mOverflowButton.getMeasuredWidth();
        }
        else {
            this.mOverflowButton = ((OverflowMenuButton)v1);
        }

        this.mActionItemWidthLimit = v5_1;
        this.mMinCellSize = ((int)(v6.getDisplayMetrics().density * 56f));
        this.mScrapActionButtonView = ((View)v1);
    }

    public boolean isOverflowMenuShowPending() {
        boolean v0 = this.mPostedOpenRunnable != null || (this.isOverflowMenuShowing()) ? true : false;
        return v0;
    }

    public boolean isOverflowMenuShowing() {
        boolean v0 = this.mOverflowPopup == null || !this.mOverflowPopup.isShowing() ? false : true;
        return v0;
    }

    public boolean isOverflowReserved() {
        return this.mReserveOverflow;
    }

    public void onCloseMenu(MenuBuilder arg1, boolean arg2) {
        this.dismissPopupMenus();
        super.onCloseMenu(arg1, arg2);
    }

    public void onConfigurationChanged(Configuration arg2) {
        if(!this.mMaxItemsSet) {
            this.mMaxItems = ActionBarPolicy.get(this.mContext).getMaxActionButtons();
        }

        if(this.mMenu != null) {
            this.mMenu.onItemsChanged(true);
        }
    }

    public void onRestoreInstanceState(Parcelable arg2) {
        if(!(arg2 instanceof SavedState)) {
            return;
        }

        if(((SavedState)arg2).openSubMenuId > 0) {
            MenuItem v2 = this.mMenu.findItem(((SavedState)arg2).openSubMenuId);
            if(v2 != null) {
                this.onSubMenuSelected(v2.getSubMenu());
            }
        }
    }

    public Parcelable onSaveInstanceState() {
        SavedState v0 = new SavedState();
        v0.openSubMenuId = this.mOpenSubMenuId;
        return ((Parcelable)v0);
    }

    public boolean onSubMenuSelected(SubMenuBuilder arg8) {
        Menu v0_1;
        boolean v1 = false;
        if(!arg8.hasVisibleItems()) {
            return 0;
        }

        SubMenuBuilder v0 = arg8;
        while(v0.getParentMenu() != this.mMenu) {
            v0_1 = ((SubMenuBuilder)v0_1).getParentMenu();
        }

        View v0_2 = this.findViewForItem(((SubMenuBuilder)v0_1).getItem());
        if(v0_2 == null) {
            return 0;
        }

        this.mOpenSubMenuId = arg8.getItem().getItemId();
        int v2 = arg8.size();
        int v3;
        for(v3 = 0; v3 < v2; ++v3) {
            MenuItem v5 = arg8.getItem(v3);
            if((v5.isVisible()) && v5.getIcon() != null) {
                v1 = true;
                break;
            }
        }

        this.mActionButtonPopup = new ActionButtonSubmenu(this, this.mContext, arg8, v0_2);
        this.mActionButtonPopup.setForceShowIcon(v1);
        this.mActionButtonPopup.show();
        super.onSubMenuSelected(arg8);
        return 1;
    }

    public void onSubUiVisibilityChanged(boolean arg2) {
        if(arg2) {
            super.onSubMenuSelected(null);
        }
        else if(this.mMenu != null) {
            this.mMenu.close(false);
        }
    }

    public void setExpandedActionViewsExclusive(boolean arg1) {
        this.mExpandedActionViewsExclusive = arg1;
    }

    public void setItemLimit(int arg1) {
        this.mMaxItems = arg1;
        this.mMaxItemsSet = true;
    }

    public void setMenuView(ActionMenuView arg2) {
        this.mMenuView = ((MenuView)arg2);
        arg2.initialize(this.mMenu);
    }

    public void setOverflowIcon(Drawable arg2) {
        if(this.mOverflowButton != null) {
            this.mOverflowButton.setImageDrawable(arg2);
        }
        else {
            this.mPendingOverflowIconSet = true;
            this.mPendingOverflowIcon = arg2;
        }
    }

    public void setReserveOverflow(boolean arg1) {
        this.mReserveOverflow = arg1;
        this.mReserveOverflowSet = true;
    }

    public void setWidthLimit(int arg1, boolean arg2) {
        this.mWidthLimit = arg1;
        this.mStrictWidthLimit = arg2;
        this.mWidthLimitSet = true;
    }

    public boolean shouldIncludeItem(int arg1, MenuItemImpl arg2) {
        return arg2.isActionButton();
    }

    public boolean showOverflowMenu() {
        if((this.mReserveOverflow) && !this.isOverflowMenuShowing() && this.mMenu != null && this.mMenuView != null && this.mPostedOpenRunnable == null && !this.mMenu.getNonActionItems().isEmpty()) {
            this.mPostedOpenRunnable = new OpenOverflowRunnable(this, new OverflowPopup(this, this.mContext, this.mMenu, this.mOverflowButton, true));
            this.mMenuView.post(this.mPostedOpenRunnable);
            super.onSubMenuSelected(null);
            return 1;
        }

        return 0;
    }

    public void updateMenuView(boolean arg5) {
        int v1;
        ArrayList v5;
        super.updateMenuView(arg5);
        this.mMenuView.requestLayout();
        int v0 = 0;
        if(this.mMenu != null) {
            v5 = this.mMenu.getActionItems();
            v1 = v5.size();
            int v2;
            for(v2 = 0; v2 < v1; ++v2) {
                ActionProvider v3 = v5.get(v2).getSupportActionProvider();
                if(v3 != null) {
                    v3.setSubUiVisibilityListener(((SubUiVisibilityListener)this));
                }
            }
        }

        v5 = this.mMenu != null ? this.mMenu.getNonActionItems() : null;
        if((this.mReserveOverflow) && v5 != null) {
            v1 = v5.size();
            if(v1 == 1) {
                v0 = v5.get(0).isActionViewExpanded() ^ 1;
            }
            else if(v1 > 0) {
                v0 = 1;
            }
        }

        if(v0 != 0) {
            if(this.mOverflowButton == null) {
                this.mOverflowButton = new OverflowMenuButton(this, this.mSystemContext);
            }

            ViewParent v5_1 = this.mOverflowButton.getParent();
            if(v5_1 == this.mMenuView) {
                goto label_63;
            }

            if(v5_1 != null) {
                ((ViewGroup)v5_1).removeView(this.mOverflowButton);
            }

            this.mMenuView.addView(this.mOverflowButton, this.mMenuView.generateOverflowButtonLayoutParams());
        }
        else {
            if(this.mOverflowButton == null) {
                goto label_63;
            }

            if(this.mOverflowButton.getParent() != this.mMenuView) {
                goto label_63;
            }

            this.mMenuView.removeView(this.mOverflowButton);
        }

    label_63:
        this.mMenuView.setOverflowReserved(this.mReserveOverflow);
    }
}

