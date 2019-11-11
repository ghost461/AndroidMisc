package android.support.v7.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build$VERSION;
import android.os.Handler;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R$dimen;
import android.support.v7.appcompat.R$layout;
import android.support.v7.widget.MenuItemHoverListener;
import android.support.v7.widget.MenuPopupWindow;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View$OnAttachStateChangeListener;
import android.view.View$OnKeyListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.view.ViewTreeObserver;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow$OnDismissListener;
import android.widget.TextView;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

final class CascadingMenuPopup extends MenuPopup implements MenuPresenter, View$OnKeyListener, PopupWindow$OnDismissListener {
    class android.support.v7.view.menu.CascadingMenuPopup$1 implements ViewTreeObserver$OnGlobalLayoutListener {
        android.support.v7.view.menu.CascadingMenuPopup$1(CascadingMenuPopup arg1) {
            CascadingMenuPopup.this = arg1;
            super();
        }

        public void onGlobalLayout() {
            if((CascadingMenuPopup.this.isShowing()) && CascadingMenuPopup.this.mShowingMenus.size() > 0 && !CascadingMenuPopup.this.mShowingMenus.get(0).window.isModal()) {
                View v0 = CascadingMenuPopup.this.mShownAnchorView;
                if(v0 != null) {
                    if(!v0.isShown()) {
                    }
                    else {
                        Iterator v0_1 = CascadingMenuPopup.this.mShowingMenus.iterator();
                        while(true) {
                            if(v0_1.hasNext()) {
                                v0_1.next().window.show();
                                continue;
                            }
                            else {
                                return;
                            }
                        }
                    }
                }

                CascadingMenuPopup.this.dismiss();
            }
        }
    }

    class android.support.v7.view.menu.CascadingMenuPopup$2 implements View$OnAttachStateChangeListener {
        android.support.v7.view.menu.CascadingMenuPopup$2(CascadingMenuPopup arg1) {
            CascadingMenuPopup.this = arg1;
            super();
        }

        public void onViewAttachedToWindow(View arg1) {
        }

        public void onViewDetachedFromWindow(View arg3) {
            if(CascadingMenuPopup.this.mTreeObserver != null) {
                if(!CascadingMenuPopup.this.mTreeObserver.isAlive()) {
                    CascadingMenuPopup.this.mTreeObserver = arg3.getViewTreeObserver();
                }

                CascadingMenuPopup.this.mTreeObserver.removeGlobalOnLayoutListener(CascadingMenuPopup.this.mGlobalLayoutListener);
            }

            arg3.removeOnAttachStateChangeListener(((View$OnAttachStateChangeListener)this));
        }
    }

    class android.support.v7.view.menu.CascadingMenuPopup$3 implements MenuItemHoverListener {
        android.support.v7.view.menu.CascadingMenuPopup$3(CascadingMenuPopup arg1) {
            CascadingMenuPopup.this = arg1;
            super();
        }

        public void onItemHoverEnter(@NonNull MenuBuilder arg8, @NonNull MenuItem arg9) {
            Object v1 = null;
            CascadingMenuPopup.this.mSubMenuHoverHandler.removeCallbacksAndMessages(v1);
            int v0 = CascadingMenuPopup.this.mShowingMenus.size();
            int v2 = 0;
            while(true) {
                int v3 = -1;
                if(v2 >= v0) {
                    break;
                }
                else if(arg8 == CascadingMenuPopup.this.mShowingMenus.get(v2).menu) {
                }
                else {
                    ++v2;
                    continue;
                }

                goto label_19;
            }

            v2 = -1;
        label_19:
            if(v2 == v3) {
                return;
            }

            ++v2;
            if(v2 < CascadingMenuPopup.this.mShowingMenus.size()) {
                v1 = CascadingMenuPopup.this.mShowingMenus.get(v2);
            }

            CascadingMenuPopup.this.mSubMenuHoverHandler.postAtTime(new Runnable(((CascadingMenuInfo)v1), arg9, arg8) {
                public void run() {
                    if(this.val$nextInfo != null) {
                        this.this$1.this$0.mShouldCloseImmediately = true;
                        this.val$nextInfo.menu.close(false);
                        this.this$1.this$0.mShouldCloseImmediately = false;
                    }

                    if((this.val$item.isEnabled()) && (this.val$item.hasSubMenu())) {
                        this.val$menu.performItemAction(this.val$item, 4);
                    }
                }
            }, arg8, SystemClock.uptimeMillis() + 200);
        }

        public void onItemHoverExit(@NonNull MenuBuilder arg1, @NonNull MenuItem arg2) {
            CascadingMenuPopup.this.mSubMenuHoverHandler.removeCallbacksAndMessages(arg1);
        }
    }

    class CascadingMenuInfo {
        public final MenuBuilder menu;
        public final int position;
        public final MenuPopupWindow window;

        public CascadingMenuInfo(@NonNull MenuPopupWindow arg1, @NonNull MenuBuilder arg2, int arg3) {
            super();
            this.window = arg1;
            this.menu = arg2;
            this.position = arg3;
        }

        public ListView getListView() {
            return this.window.getListView();
        }
    }

    @Retention(value=RetentionPolicy.SOURCE) @public interface HorizPosition {
    }

    static final int HORIZ_POSITION_LEFT = 0;
    static final int HORIZ_POSITION_RIGHT = 1;
    static final int SUBMENU_TIMEOUT_MS = 200;
    private View mAnchorView;
    private final View$OnAttachStateChangeListener mAttachStateChangeListener;
    private final Context mContext;
    private int mDropDownGravity;
    private boolean mForceShowIcon;
    private final ViewTreeObserver$OnGlobalLayoutListener mGlobalLayoutListener;
    private boolean mHasXOffset;
    private boolean mHasYOffset;
    private int mLastPosition;
    private final MenuItemHoverListener mMenuItemHoverListener;
    private final int mMenuMaxWidth;
    private PopupWindow$OnDismissListener mOnDismissListener;
    private final boolean mOverflowOnly;
    private final List mPendingMenus;
    private final int mPopupStyleAttr;
    private final int mPopupStyleRes;
    private Callback mPresenterCallback;
    private int mRawDropDownGravity;
    boolean mShouldCloseImmediately;
    private boolean mShowTitle;
    final List mShowingMenus;
    View mShownAnchorView;
    final Handler mSubMenuHoverHandler;
    private ViewTreeObserver mTreeObserver;
    private int mXOffset;
    private int mYOffset;

    public CascadingMenuPopup(@NonNull Context arg2, @NonNull View arg3, @AttrRes int arg4, @StyleRes int arg5, boolean arg6) {
        super();
        this.mPendingMenus = new LinkedList();
        this.mShowingMenus = new ArrayList();
        this.mGlobalLayoutListener = new android.support.v7.view.menu.CascadingMenuPopup$1(this);
        this.mAttachStateChangeListener = new android.support.v7.view.menu.CascadingMenuPopup$2(this);
        this.mMenuItemHoverListener = new android.support.v7.view.menu.CascadingMenuPopup$3(this);
        this.mRawDropDownGravity = 0;
        this.mDropDownGravity = 0;
        this.mContext = arg2;
        this.mAnchorView = arg3;
        this.mPopupStyleAttr = arg4;
        this.mPopupStyleRes = arg5;
        this.mOverflowOnly = arg6;
        this.mForceShowIcon = false;
        this.mLastPosition = this.getInitialMenuPosition();
        Resources v2 = arg2.getResources();
        this.mMenuMaxWidth = Math.max(v2.getDisplayMetrics().widthPixels / 2, v2.getDimensionPixelSize(dimen.abc_config_prefDialogWidth));
        this.mSubMenuHoverHandler = new Handler();
    }

    static ViewTreeObserver access$000(CascadingMenuPopup arg0) {
        return arg0.mTreeObserver;
    }

    static ViewTreeObserver access$002(CascadingMenuPopup arg0, ViewTreeObserver arg1) {
        arg0.mTreeObserver = arg1;
        return arg1;
    }

    static ViewTreeObserver$OnGlobalLayoutListener access$100(CascadingMenuPopup arg0) {
        return arg0.mGlobalLayoutListener;
    }

    public void addMenu(MenuBuilder arg2) {
        arg2.addMenuPresenter(((MenuPresenter)this), this.mContext);
        if(this.isShowing()) {
            this.showMenu(arg2);
        }
        else {
            this.mPendingMenus.add(arg2);
        }
    }

    protected boolean closeMenuOnSubMenuOpened() {
        return 0;
    }

    private MenuPopupWindow createPopupWindow() {
        MenuPopupWindow v0 = new MenuPopupWindow(this.mContext, null, this.mPopupStyleAttr, this.mPopupStyleRes);
        v0.setHoverListener(this.mMenuItemHoverListener);
        v0.setOnItemClickListener(((AdapterView$OnItemClickListener)this));
        v0.setOnDismissListener(((PopupWindow$OnDismissListener)this));
        v0.setAnchorView(this.mAnchorView);
        v0.setDropDownGravity(this.mDropDownGravity);
        v0.setModal(true);
        v0.setInputMethodMode(2);
        return v0;
    }

    public void dismiss() {
        int v0 = this.mShowingMenus.size();
        if(v0 > 0) {
            Object[] v1 = this.mShowingMenus.toArray(new CascadingMenuInfo[v0]);
            --v0;
            while(v0 >= 0) {
                Object v2 = v1[v0];
                if(((CascadingMenuInfo)v2).window.isShowing()) {
                    ((CascadingMenuInfo)v2).window.dismiss();
                }

                --v0;
            }
        }
    }

    private int findIndexOfAddedMenu(@NonNull MenuBuilder arg4) {
        int v0 = this.mShowingMenus.size();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            if(arg4 == this.mShowingMenus.get(v1).menu) {
                return v1;
            }
        }

        return -1;
    }

    private MenuItem findMenuItemForSubmenu(@NonNull MenuBuilder arg5, @NonNull MenuBuilder arg6) {
        int v0 = arg5.size();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            MenuItem v2 = arg5.getItem(v1);
            if((v2.hasSubMenu()) && arg6 == v2.getSubMenu()) {
                return v2;
            }
        }

        return null;
    }

    @Nullable private View findParentViewForSubmenu(@NonNull CascadingMenuInfo arg8, @NonNull MenuBuilder arg9) {
        int v2;
        MenuItem v9 = this.findMenuItemForSubmenu(arg8.menu, arg9);
        View v0 = null;
        if(v9 == null) {
            return v0;
        }

        ListView v8 = arg8.getListView();
        ListAdapter v1 = v8.getAdapter();
        int v3 = 0;
        if((v1 instanceof HeaderViewListAdapter)) {
            v2 = ((HeaderViewListAdapter)v1).getHeadersCount();
            v1 = ((HeaderViewListAdapter)v1).getWrappedAdapter();
        }
        else {
            v2 = 0;
        }

        int v4 = ((MenuAdapter)v1).getCount();
        while(true) {
            int v5 = -1;
            if(v3 >= v4) {
                break;
            }
            else if(v9 == ((MenuAdapter)v1).getItem(v3)) {
            }
            else {
                ++v3;
                continue;
            }

            goto label_23;
        }

        v3 = -1;
    label_23:
        if(v3 == v5) {
            return v0;
        }

        v3 = v3 + v2 - v8.getFirstVisiblePosition();
        if(v3 >= 0) {
            if(v3 >= v8.getChildCount()) {
            }
            else {
                return v8.getChildAt(v3);
            }
        }

        return v0;
    }

    public boolean flagActionItems() {
        return 0;
    }

    private int getInitialMenuPosition() {
        int v1 = 1;
        if(ViewCompat.getLayoutDirection(this.mAnchorView) == 1) {
            v1 = 0;
        }

        return v1;
    }

    public ListView getListView() {
        ListView v0 = this.mShowingMenus.isEmpty() ? null : this.mShowingMenus.get(this.mShowingMenus.size() - 1).getListView();
        return v0;
    }

    private int getNextMenuPosition(int arg7) {
        ListView v0 = this.mShowingMenus.get(this.mShowingMenus.size() - 1).getListView();
        int[] v1 = new int[2];
        v0.getLocationOnScreen(v1);
        Rect v3 = new Rect();
        this.mShownAnchorView.getWindowVisibleDisplayFrame(v3);
        if(this.mLastPosition == 1) {
            if(v1[0] + v0.getWidth() + arg7 > v3.right) {
                return 0;
            }

            return 1;
        }

        if(v1[0] - arg7 < 0) {
            return 1;
        }

        return 0;
    }

    public boolean isShowing() {
        boolean v1 = false;
        if(this.mShowingMenus.size() > 0 && (this.mShowingMenus.get(0).window.isShowing())) {
            v1 = true;
        }

        return v1;
    }

    public void onCloseMenu(MenuBuilder arg6, boolean arg7) {
        int v0 = this.findIndexOfAddedMenu(arg6);
        if(v0 < 0) {
            return;
        }

        int v1 = v0 + 1;
        if(v1 < this.mShowingMenus.size()) {
            this.mShowingMenus.get(v1).menu.close(false);
        }

        Object v0_1 = this.mShowingMenus.remove(v0);
        ((CascadingMenuInfo)v0_1).menu.removeMenuPresenter(((MenuPresenter)this));
        Object v2 = null;
        if(this.mShouldCloseImmediately) {
            ((CascadingMenuInfo)v0_1).window.setExitTransition(v2);
            ((CascadingMenuInfo)v0_1).window.setAnimationStyle(0);
        }

        ((CascadingMenuInfo)v0_1).window.dismiss();
        v0 = this.mShowingMenus.size();
        this.mLastPosition = v0 > 0 ? this.mShowingMenus.get(v0 - 1).position : this.getInitialMenuPosition();
        if(v0 == 0) {
            this.dismiss();
            if(this.mPresenterCallback != null) {
                this.mPresenterCallback.onCloseMenu(arg6, true);
            }

            if(this.mTreeObserver != null) {
                if(this.mTreeObserver.isAlive()) {
                    this.mTreeObserver.removeGlobalOnLayoutListener(this.mGlobalLayoutListener);
                }

                this.mTreeObserver = ((ViewTreeObserver)v2);
            }

            this.mShownAnchorView.removeOnAttachStateChangeListener(this.mAttachStateChangeListener);
            this.mOnDismissListener.onDismiss();
        }
        else {
            if(!arg7) {
                return;
            }

            this.mShowingMenus.get(0).menu.close(false);
        }
    }

    public void onDismiss() {
        Object v3;
        int v0 = this.mShowingMenus.size();
        int v2 = 0;
        while(true) {
            if(v2 < v0) {
                v3 = this.mShowingMenus.get(v2);
                if(!((CascadingMenuInfo)v3).window.isShowing()) {
                }
                else {
                    ++v2;
                    continue;
                }
            }
            else {
                break;
            }

            goto label_14;
        }

        v3 = null;
    label_14:
        if(v3 != null) {
            ((CascadingMenuInfo)v3).menu.close(false);
        }
    }

    public boolean onKey(View arg1, int arg2, KeyEvent arg3) {
        if(arg3.getAction() == 1 && arg2 == 82) {
            this.dismiss();
            return 1;
        }

        return 0;
    }

    public void onRestoreInstanceState(Parcelable arg1) {
    }

    public Parcelable onSaveInstanceState() {
        return null;
    }

    public boolean onSubMenuSelected(SubMenuBuilder arg5) {
        Object v1;
        Iterator v0 = this.mShowingMenus.iterator();
        do {
            if(!v0.hasNext()) {
                goto label_11;
            }

            v1 = v0.next();
        }
        while(arg5 != ((CascadingMenuInfo)v1).menu);

        ((CascadingMenuInfo)v1).getListView().requestFocus();
        return 1;
    label_11:
        if(arg5.hasVisibleItems()) {
            this.addMenu(((MenuBuilder)arg5));
            if(this.mPresenterCallback != null) {
                this.mPresenterCallback.onOpenSubMenu(((MenuBuilder)arg5));
            }

            return 1;
        }

        return 0;
    }

    public void setAnchorView(@NonNull View arg2) {
        if(this.mAnchorView != arg2) {
            this.mAnchorView = arg2;
            this.mDropDownGravity = GravityCompat.getAbsoluteGravity(this.mRawDropDownGravity, ViewCompat.getLayoutDirection(this.mAnchorView));
        }
    }

    public void setCallback(Callback arg1) {
        this.mPresenterCallback = arg1;
    }

    public void setForceShowIcon(boolean arg1) {
        this.mForceShowIcon = arg1;
    }

    public void setGravity(int arg2) {
        if(this.mRawDropDownGravity != arg2) {
            this.mRawDropDownGravity = arg2;
            this.mDropDownGravity = GravityCompat.getAbsoluteGravity(arg2, ViewCompat.getLayoutDirection(this.mAnchorView));
        }
    }

    public void setHorizontalOffset(int arg2) {
        this.mHasXOffset = true;
        this.mXOffset = arg2;
    }

    public void setOnDismissListener(PopupWindow$OnDismissListener arg1) {
        this.mOnDismissListener = arg1;
    }

    public void setShowTitle(boolean arg1) {
        this.mShowTitle = arg1;
    }

    public void setVerticalOffset(int arg2) {
        this.mHasYOffset = true;
        this.mYOffset = arg2;
    }

    public void show() {
        if(this.isShowing()) {
            return;
        }

        Iterator v0 = this.mPendingMenus.iterator();
        while(v0.hasNext()) {
            this.showMenu(v0.next());
        }

        this.mPendingMenus.clear();
        this.mShownAnchorView = this.mAnchorView;
        if(this.mShownAnchorView != null) {
            int v0_1 = this.mTreeObserver == null ? 1 : 0;
            this.mTreeObserver = this.mShownAnchorView.getViewTreeObserver();
            if(v0_1 != 0) {
                this.mTreeObserver.addOnGlobalLayoutListener(this.mGlobalLayoutListener);
            }

            this.mShownAnchorView.addOnAttachStateChangeListener(this.mAttachStateChangeListener);
        }
    }

    private void showMenu(@NonNull MenuBuilder arg14) {
        int v11;
        View v6;
        Object v1_1;
        LayoutInflater v0 = LayoutInflater.from(this.mContext);
        MenuAdapter v1 = new MenuAdapter(arg14, v0, this.mOverflowOnly);
        if(!this.isShowing() && (this.mForceShowIcon)) {
            v1.setForceShowIcon(true);
        }
        else if(this.isShowing()) {
            v1.setForceShowIcon(MenuPopup.shouldPreserveIconSpacing(arg14));
        }

        ViewGroup v5 = null;
        int v2 = CascadingMenuPopup.measureIndividualMenuWidth(((ListAdapter)v1), v5, this.mContext, this.mMenuMaxWidth);
        MenuPopupWindow v4 = this.createPopupWindow();
        v4.setAdapter(((ListAdapter)v1));
        v4.setContentWidth(v2);
        v4.setDropDownGravity(this.mDropDownGravity);
        if(this.mShowingMenus.size() > 0) {
            v1_1 = this.mShowingMenus.get(this.mShowingMenus.size() - 1);
            v6 = this.findParentViewForSubmenu(((CascadingMenuInfo)v1_1), arg14);
        }
        else {
            v1_1 = v5;
            v6 = ((View)v1_1);
        }

        if(v6 != null) {
            v4.setTouchModal(false);
            v4.setEnterTransition(v5);
            int v8 = this.getNextMenuPosition(v2);
            int v9 = v8 == 1 ? 1 : 0;
            this.mLastPosition = v8;
            if(Build$VERSION.SDK_INT >= 26) {
                v4.setAnchorView(v6);
                v8 = 0;
                v11 = 0;
            }
            else {
                int[] v10 = new int[2];
                this.mAnchorView.getLocationOnScreen(v10);
                int[] v8_1 = new int[2];
                v6.getLocationOnScreen(v8_1);
                v11 = v8_1[0] - v10[0];
                v8 = v8_1[1] - v10[1];
            }

            if((this.mDropDownGravity & 5) == 5) {
                if(v9 != 0) {
                    v11 += v2;
                }
                else {
                    v11 -= v6.getWidth();
                }
            }
            else if(v9 != 0) {
                v11 += v6.getWidth();
            }
            else {
                v11 -= v2;
            }

            v4.setHorizontalOffset(v11);
            v4.setOverlapAnchor(true);
            v4.setVerticalOffset(v8);
        }
        else {
            if(this.mHasXOffset) {
                v4.setHorizontalOffset(this.mXOffset);
            }

            if(this.mHasYOffset) {
                v4.setVerticalOffset(this.mYOffset);
            }

            v4.setEpicenterBounds(this.getEpicenterBounds());
        }

        this.mShowingMenus.add(new CascadingMenuInfo(v4, arg14, this.mLastPosition));
        v4.show();
        ListView v2_1 = v4.getListView();
        v2_1.setOnKeyListener(((View$OnKeyListener)this));
        if(v1_1 == null && (this.mShowTitle) && arg14.getHeaderTitle() != null) {
            View v0_1 = v0.inflate(layout.abc_popup_menu_header_item_layout, ((ViewGroup)v2_1), false);
            View v1_2 = ((FrameLayout)v0_1).findViewById(0x1020016);
            ((FrameLayout)v0_1).setEnabled(false);
            ((TextView)v1_2).setText(arg14.getHeaderTitle());
            v2_1.addHeaderView(v0_1, v5, false);
            v4.show();
        }
    }

    public void updateMenuView(boolean arg2) {
        Iterator v2 = this.mShowingMenus.iterator();
        while(v2.hasNext()) {
            CascadingMenuPopup.toMenuAdapter(v2.next().getListView().getAdapter()).notifyDataSetChanged();
        }
    }
}

