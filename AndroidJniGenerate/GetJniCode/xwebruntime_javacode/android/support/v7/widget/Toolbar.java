package android.support.v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.os.Parcel;
import android.os.Parcelable$ClassLoaderCreator;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.MenuRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MarginLayoutParamsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R$attr;
import android.support.v7.appcompat.R$styleable;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.view.CollapsibleActionView;
import android.support.v7.view.SupportMenuInflater;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuPresenter$Callback;
import android.support.v7.view.menu.MenuPresenter;
import android.support.v7.view.menu.MenuView;
import android.support.v7.view.menu.SubMenuBuilder;
import android.text.Layout;
import android.text.TextUtils$TruncateAt;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View$MeasureSpec;
import android.view.View$OnClickListener;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup$MarginLayoutParams;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class Toolbar extends ViewGroup {
    class android.support.v7.widget.Toolbar$1 implements OnMenuItemClickListener {
        android.support.v7.widget.Toolbar$1(Toolbar arg1) {
            Toolbar.this = arg1;
            super();
        }

        public boolean onMenuItemClick(MenuItem arg2) {
            if(Toolbar.this.mOnMenuItemClickListener != null) {
                return Toolbar.this.mOnMenuItemClickListener.onMenuItemClick(arg2);
            }

            return 0;
        }
    }

    class android.support.v7.widget.Toolbar$2 implements Runnable {
        android.support.v7.widget.Toolbar$2(Toolbar arg1) {
            Toolbar.this = arg1;
            super();
        }

        public void run() {
            Toolbar.this.showOverflowMenu();
        }
    }

    class ExpandedActionViewMenuPresenter implements MenuPresenter {
        MenuItemImpl mCurrentExpandedItem;
        MenuBuilder mMenu;

        ExpandedActionViewMenuPresenter(Toolbar arg1) {
            Toolbar.this = arg1;
            super();
        }

        public boolean collapseItemActionView(MenuBuilder arg2, MenuItemImpl arg3) {
            if((Toolbar.this.mExpandedActionView instanceof CollapsibleActionView)) {
                Toolbar.this.mExpandedActionView.onActionViewCollapsed();
            }

            Toolbar.this.removeView(Toolbar.this.mExpandedActionView);
            Toolbar.this.removeView(Toolbar.this.mCollapseButtonView);
            Toolbar.this.mExpandedActionView = null;
            Toolbar.this.addChildrenForExpandedActionView();
            this.mCurrentExpandedItem = null;
            Toolbar.this.requestLayout();
            arg3.setActionViewExpanded(false);
            return 1;
        }

        public boolean expandItemActionView(MenuBuilder arg3, MenuItemImpl arg4) {
            Toolbar.this.ensureCollapseButtonView();
            if(Toolbar.this.mCollapseButtonView.getParent() != Toolbar.this) {
                Toolbar.this.addView(Toolbar.this.mCollapseButtonView);
            }

            Toolbar.this.mExpandedActionView = arg4.getActionView();
            this.mCurrentExpandedItem = arg4;
            if(Toolbar.this.mExpandedActionView.getParent() != Toolbar.this) {
                LayoutParams v3 = Toolbar.this.generateDefaultLayoutParams();
                v3.gravity = 0x800003 | Toolbar.this.mButtonGravity & 0x70;
                v3.mViewType = 2;
                Toolbar.this.mExpandedActionView.setLayoutParams(((ViewGroup$LayoutParams)v3));
                Toolbar.this.addView(Toolbar.this.mExpandedActionView);
            }

            Toolbar.this.removeChildrenForExpandedActionView();
            Toolbar.this.requestLayout();
            arg4.setActionViewExpanded(true);
            if((Toolbar.this.mExpandedActionView instanceof CollapsibleActionView)) {
                Toolbar.this.mExpandedActionView.onActionViewExpanded();
            }

            return 1;
        }

        public boolean flagActionItems() {
            return 0;
        }

        public int getId() {
            return 0;
        }

        public MenuView getMenuView(ViewGroup arg1) {
            return null;
        }

        public void initForMenu(Context arg2, MenuBuilder arg3) {
            if(this.mMenu != null && this.mCurrentExpandedItem != null) {
                this.mMenu.collapseItemActionView(this.mCurrentExpandedItem);
            }

            this.mMenu = arg3;
        }

        public void onCloseMenu(MenuBuilder arg1, boolean arg2) {
        }

        public void onRestoreInstanceState(Parcelable arg1) {
        }

        public Parcelable onSaveInstanceState() {
            return null;
        }

        public boolean onSubMenuSelected(SubMenuBuilder arg1) {
            return 0;
        }

        public void setCallback(Callback arg1) {
        }

        public void updateMenuView(boolean arg5) {
            if(this.mCurrentExpandedItem != null) {
                int v0 = 0;
                if(this.mMenu != null) {
                    int v5 = this.mMenu.size();
                    int v1 = 0;
                    while(v1 < v5) {
                        if(this.mMenu.getItem(v1) == this.mCurrentExpandedItem) {
                            v0 = 1;
                        }
                        else {
                            ++v1;
                            continue;
                        }

                        break;
                    }
                }

                if(v0 != 0) {
                    return;
                }

                this.collapseItemActionView(this.mMenu, this.mCurrentExpandedItem);
            }
        }
    }

    public class LayoutParams extends android.support.v7.app.ActionBar$LayoutParams {
        static final int CUSTOM = 0;
        static final int EXPANDED = 2;
        static final int SYSTEM = 1;
        int mViewType;

        public LayoutParams(int arg1, int arg2) {
            super(arg1, arg2);
            this.mViewType = 0;
            this.gravity = 0x800013;
        }

        public LayoutParams(@NonNull Context arg1, AttributeSet arg2) {
            super(arg1, arg2);
            this.mViewType = 0;
        }

        public LayoutParams(LayoutParams arg2) {
            super(((android.support.v7.app.ActionBar$LayoutParams)arg2));
            this.mViewType = 0;
            this.mViewType = arg2.mViewType;
        }

        public LayoutParams(android.support.v7.app.ActionBar$LayoutParams arg1) {
            super(arg1);
            this.mViewType = 0;
        }

        public LayoutParams(ViewGroup$MarginLayoutParams arg2) {
            super(((ViewGroup$LayoutParams)arg2));
            this.mViewType = 0;
            this.copyMarginsFromCompat(arg2);
        }

        public LayoutParams(ViewGroup$LayoutParams arg1) {
            super(arg1);
            this.mViewType = 0;
        }

        public LayoutParams(int arg3) {
            this(-2, -1, arg3);
        }

        public LayoutParams(int arg1, int arg2, int arg3) {
            super(arg1, arg2);
            this.mViewType = 0;
            this.gravity = arg3;
        }

        void copyMarginsFromCompat(ViewGroup$MarginLayoutParams arg2) {
            this.leftMargin = arg2.leftMargin;
            this.topMargin = arg2.topMargin;
            this.rightMargin = arg2.rightMargin;
            this.bottomMargin = arg2.bottomMargin;
        }
    }

    public interface android.support.v7.widget.Toolbar$OnMenuItemClickListener {
        boolean onMenuItemClick(MenuItem arg1);
    }

    public class SavedState extends AbsSavedState {
        final class android.support.v7.widget.Toolbar$SavedState$1 implements Parcelable$ClassLoaderCreator {
            android.support.v7.widget.Toolbar$SavedState$1() {
                super();
            }

            public SavedState createFromParcel(Parcel arg3) {
                return new SavedState(arg3, null);
            }

            public SavedState createFromParcel(Parcel arg2, ClassLoader arg3) {
                return new SavedState(arg2, arg3);
            }

            public Object createFromParcel(Parcel arg1) {
                return this.createFromParcel(arg1);
            }

            public Object createFromParcel(Parcel arg1, ClassLoader arg2) {
                return this.createFromParcel(arg1, arg2);
            }

            public SavedState[] newArray(int arg1) {
                return new SavedState[arg1];
            }

            public Object[] newArray(int arg1) {
                return this.newArray(arg1);
            }
        }

        public static final Parcelable$Creator CREATOR;
        int expandedMenuItemId;
        boolean isOverflowOpen;

        static {
            SavedState.CREATOR = new android.support.v7.widget.Toolbar$SavedState$1();
        }

        public SavedState(Parcelable arg1) {
            super(arg1);
        }

        public SavedState(Parcel arg2) {
            this(arg2, null);
        }

        public SavedState(Parcel arg1, ClassLoader arg2) {
            super(arg1, arg2);
            this.expandedMenuItemId = arg1.readInt();
            boolean v1 = arg1.readInt() != 0 ? true : false;
            this.isOverflowOpen = v1;
        }

        public void writeToParcel(Parcel arg1, int arg2) {
            super.writeToParcel(arg1, arg2);
            arg1.writeInt(this.expandedMenuItemId);
            arg1.writeInt(this.isOverflowOpen);
        }
    }

    private static final String TAG = "Toolbar";
    private Callback mActionMenuPresenterCallback;
    int mButtonGravity;
    ImageButton mCollapseButtonView;
    private CharSequence mCollapseDescription;
    private Drawable mCollapseIcon;
    private boolean mCollapsible;
    private int mContentInsetEndWithActions;
    private int mContentInsetStartWithNavigation;
    private RtlSpacingHelper mContentInsets;
    private boolean mEatingHover;
    private boolean mEatingTouch;
    View mExpandedActionView;
    private ExpandedActionViewMenuPresenter mExpandedMenuPresenter;
    private int mGravity;
    private final ArrayList mHiddenViews;
    private ImageView mLogoView;
    private int mMaxButtonHeight;
    private android.support.v7.view.menu.MenuBuilder$Callback mMenuBuilderCallback;
    private ActionMenuView mMenuView;
    private final OnMenuItemClickListener mMenuViewItemClickListener;
    private ImageButton mNavButtonView;
    android.support.v7.widget.Toolbar$OnMenuItemClickListener mOnMenuItemClickListener;
    private ActionMenuPresenter mOuterActionMenuPresenter;
    private Context mPopupContext;
    private int mPopupTheme;
    private final Runnable mShowOverflowMenuRunnable;
    private CharSequence mSubtitleText;
    private int mSubtitleTextAppearance;
    private int mSubtitleTextColor;
    private TextView mSubtitleTextView;
    private final int[] mTempMargins;
    private final ArrayList mTempViews;
    private int mTitleMarginBottom;
    private int mTitleMarginEnd;
    private int mTitleMarginStart;
    private int mTitleMarginTop;
    private CharSequence mTitleText;
    private int mTitleTextAppearance;
    private int mTitleTextColor;
    private TextView mTitleTextView;
    private ToolbarWidgetWrapper mWrapper;

    public Toolbar(Context arg2) {
        this(arg2, null);
    }

    public Toolbar(Context arg2, @Nullable AttributeSet arg3) {
        this(arg2, arg3, attr.toolbarStyle);
    }

    public Toolbar(Context arg7, @Nullable AttributeSet arg8, int arg9) {
        super(arg7, arg8, arg9);
        this.mGravity = 0x800013;
        this.mTempViews = new ArrayList();
        this.mHiddenViews = new ArrayList();
        this.mTempMargins = new int[2];
        this.mMenuViewItemClickListener = new android.support.v7.widget.Toolbar$1(this);
        this.mShowOverflowMenuRunnable = new android.support.v7.widget.Toolbar$2(this);
        TintTypedArray v7 = TintTypedArray.obtainStyledAttributes(this.getContext(), arg8, styleable.Toolbar, arg9, 0);
        this.mTitleTextAppearance = v7.getResourceId(styleable.Toolbar_titleTextAppearance, 0);
        this.mSubtitleTextAppearance = v7.getResourceId(styleable.Toolbar_subtitleTextAppearance, 0);
        this.mGravity = v7.getInteger(styleable.Toolbar_android_gravity, this.mGravity);
        this.mButtonGravity = v7.getInteger(styleable.Toolbar_buttonGravity, 0x30);
        int v8 = v7.getDimensionPixelOffset(styleable.Toolbar_titleMargin, 0);
        if(v7.hasValue(styleable.Toolbar_titleMargins)) {
            v8 = v7.getDimensionPixelOffset(styleable.Toolbar_titleMargins, v8);
        }

        this.mTitleMarginBottom = v8;
        this.mTitleMarginTop = v8;
        this.mTitleMarginEnd = v8;
        this.mTitleMarginStart = v8;
        arg9 = -1;
        v8 = v7.getDimensionPixelOffset(styleable.Toolbar_titleMarginStart, arg9);
        if(v8 >= 0) {
            this.mTitleMarginStart = v8;
        }

        v8 = v7.getDimensionPixelOffset(styleable.Toolbar_titleMarginEnd, arg9);
        if(v8 >= 0) {
            this.mTitleMarginEnd = v8;
        }

        v8 = v7.getDimensionPixelOffset(styleable.Toolbar_titleMarginTop, arg9);
        if(v8 >= 0) {
            this.mTitleMarginTop = v8;
        }

        v8 = v7.getDimensionPixelOffset(styleable.Toolbar_titleMarginBottom, arg9);
        if(v8 >= 0) {
            this.mTitleMarginBottom = v8;
        }

        this.mMaxButtonHeight = v7.getDimensionPixelSize(styleable.Toolbar_maxButtonHeight, arg9);
        int v0 = 0x80000000;
        v8 = v7.getDimensionPixelOffset(styleable.Toolbar_contentInsetStart, v0);
        int v2 = v7.getDimensionPixelOffset(styleable.Toolbar_contentInsetEnd, v0);
        int v3 = v7.getDimensionPixelSize(styleable.Toolbar_contentInsetLeft, 0);
        int v4 = v7.getDimensionPixelSize(styleable.Toolbar_contentInsetRight, 0);
        this.ensureContentInsets();
        this.mContentInsets.setAbsolute(v3, v4);
        if(v8 != v0 || v2 != v0) {
            this.mContentInsets.setRelative(v8, v2);
        }

        this.mContentInsetStartWithNavigation = v7.getDimensionPixelOffset(styleable.Toolbar_contentInsetStartWithNavigation, v0);
        this.mContentInsetEndWithActions = v7.getDimensionPixelOffset(styleable.Toolbar_contentInsetEndWithActions, v0);
        this.mCollapseIcon = v7.getDrawable(styleable.Toolbar_collapseIcon);
        this.mCollapseDescription = v7.getText(styleable.Toolbar_collapseContentDescription);
        CharSequence v8_1 = v7.getText(styleable.Toolbar_title);
        if(!TextUtils.isEmpty(v8_1)) {
            this.setTitle(v8_1);
        }

        v8_1 = v7.getText(styleable.Toolbar_subtitle);
        if(!TextUtils.isEmpty(v8_1)) {
            this.setSubtitle(v8_1);
        }

        this.mPopupContext = this.getContext();
        this.setPopupTheme(v7.getResourceId(styleable.Toolbar_popupTheme, 0));
        Drawable v8_2 = v7.getDrawable(styleable.Toolbar_navigationIcon);
        if(v8_2 != null) {
            this.setNavigationIcon(v8_2);
        }

        v8_1 = v7.getText(styleable.Toolbar_navigationContentDescription);
        if(!TextUtils.isEmpty(v8_1)) {
            this.setNavigationContentDescription(v8_1);
        }

        v8_2 = v7.getDrawable(styleable.Toolbar_logo);
        if(v8_2 != null) {
            this.setLogo(v8_2);
        }

        v8_1 = v7.getText(styleable.Toolbar_logoDescription);
        if(!TextUtils.isEmpty(v8_1)) {
            this.setLogoDescription(v8_1);
        }

        if(v7.hasValue(styleable.Toolbar_titleTextColor)) {
            this.setTitleTextColor(v7.getColor(styleable.Toolbar_titleTextColor, arg9));
        }

        if(v7.hasValue(styleable.Toolbar_subtitleTextColor)) {
            this.setSubtitleTextColor(v7.getColor(styleable.Toolbar_subtitleTextColor, arg9));
        }

        v7.recycle();
    }

    void addChildrenForExpandedActionView() {
        int v0;
        for(v0 = this.mHiddenViews.size() - 1; v0 >= 0; --v0) {
            this.addView(this.mHiddenViews.get(v0));
        }

        this.mHiddenViews.clear();
    }

    private void addCustomViewsWithGravity(List arg6, int arg7) {
        View v0_1;
        int v1 = 0;
        int v0 = ViewCompat.getLayoutDirection(((View)this)) == 1 ? 1 : 0;
        int v3 = this.getChildCount();
        arg7 = GravityCompat.getAbsoluteGravity(arg7, ViewCompat.getLayoutDirection(((View)this)));
        arg6.clear();
        if(v0 != 0) {
            --v3;
            while(v3 >= 0) {
                v0_1 = this.getChildAt(v3);
                ViewGroup$LayoutParams v1_1 = v0_1.getLayoutParams();
                if(((LayoutParams)v1_1).mViewType == 0 && (this.shouldLayout(v0_1)) && this.getChildHorizontalGravity(((LayoutParams)v1_1).gravity) == arg7) {
                    arg6.add(v0_1);
                }

                --v3;
            }
        }
        else {
            while(v1 < v3) {
                v0_1 = this.getChildAt(v1);
                ViewGroup$LayoutParams v2 = v0_1.getLayoutParams();
                if(((LayoutParams)v2).mViewType == 0 && (this.shouldLayout(v0_1)) && this.getChildHorizontalGravity(((LayoutParams)v2).gravity) == arg7) {
                    arg6.add(v0_1);
                }

                ++v1;
            }
        }
    }

    private void addSystemView(View arg3, boolean arg4) {
        LayoutParams v0_1;
        ViewGroup$LayoutParams v0 = arg3.getLayoutParams();
        if(v0 == null) {
            v0_1 = this.generateDefaultLayoutParams();
        }
        else if(!this.checkLayoutParams(v0)) {
            v0_1 = this.generateLayoutParams(v0);
        }

        v0_1.mViewType = 1;
        if(!arg4 || this.mExpandedActionView == null) {
            this.addView(arg3, ((ViewGroup$LayoutParams)v0_1));
        }
        else {
            arg3.setLayoutParams(((ViewGroup$LayoutParams)v0_1));
            this.mHiddenViews.add(arg3);
        }
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public boolean canShowOverflowMenu() {
        boolean v0 = this.getVisibility() != 0 || this.mMenuView == null || !this.mMenuView.isOverflowReserved() ? false : true;
        return v0;
    }

    protected boolean checkLayoutParams(ViewGroup$LayoutParams arg2) {
        boolean v2 = !super.checkLayoutParams(arg2) || !(arg2 instanceof LayoutParams) ? false : true;
        return v2;
    }

    public void collapseActionView() {
        MenuItemImpl v0 = this.mExpandedMenuPresenter == null ? null : this.mExpandedMenuPresenter.mCurrentExpandedItem;
        if(v0 != null) {
            v0.collapseActionView();
        }
    }

    public void dismissPopupMenus() {
        if(this.mMenuView != null) {
            this.mMenuView.dismissPopupMenus();
        }
    }

    void ensureCollapseButtonView() {
        if(this.mCollapseButtonView == null) {
            this.mCollapseButtonView = new AppCompatImageButton(this.getContext(), null, attr.toolbarNavigationButtonStyle);
            this.mCollapseButtonView.setImageDrawable(this.mCollapseIcon);
            this.mCollapseButtonView.setContentDescription(this.mCollapseDescription);
            LayoutParams v0 = this.generateDefaultLayoutParams();
            v0.gravity = 0x800003 | this.mButtonGravity & 0x70;
            v0.mViewType = 2;
            this.mCollapseButtonView.setLayoutParams(((ViewGroup$LayoutParams)v0));
            this.mCollapseButtonView.setOnClickListener(new View$OnClickListener() {
                public void onClick(View arg1) {
                    Toolbar.this.collapseActionView();
                }
            });
        }
    }

    private void ensureContentInsets() {
        if(this.mContentInsets == null) {
            this.mContentInsets = new RtlSpacingHelper();
        }
    }

    private void ensureLogoView() {
        if(this.mLogoView == null) {
            this.mLogoView = new AppCompatImageView(this.getContext());
        }
    }

    private void ensureMenu() {
        this.ensureMenuView();
        if(this.mMenuView.peekMenu() == null) {
            Menu v0 = this.mMenuView.getMenu();
            if(this.mExpandedMenuPresenter == null) {
                this.mExpandedMenuPresenter = new ExpandedActionViewMenuPresenter(this);
            }

            this.mMenuView.setExpandedActionViewsExclusive(true);
            ((MenuBuilder)v0).addMenuPresenter(this.mExpandedMenuPresenter, this.mPopupContext);
        }
    }

    private void ensureMenuView() {
        if(this.mMenuView == null) {
            this.mMenuView = new ActionMenuView(this.getContext());
            this.mMenuView.setPopupTheme(this.mPopupTheme);
            this.mMenuView.setOnMenuItemClickListener(this.mMenuViewItemClickListener);
            this.mMenuView.setMenuCallbacks(this.mActionMenuPresenterCallback, this.mMenuBuilderCallback);
            LayoutParams v0 = this.generateDefaultLayoutParams();
            v0.gravity = 0x800005 | this.mButtonGravity & 0x70;
            this.mMenuView.setLayoutParams(((ViewGroup$LayoutParams)v0));
            this.addSystemView(this.mMenuView, false);
        }
    }

    private void ensureNavButtonView() {
        if(this.mNavButtonView == null) {
            this.mNavButtonView = new AppCompatImageButton(this.getContext(), null, attr.toolbarNavigationButtonStyle);
            LayoutParams v0 = this.generateDefaultLayoutParams();
            v0.gravity = 0x800003 | this.mButtonGravity & 0x70;
            this.mNavButtonView.setLayoutParams(((ViewGroup$LayoutParams)v0));
        }
    }

    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    protected ViewGroup$LayoutParams generateDefaultLayoutParams() {
        return this.generateDefaultLayoutParams();
    }

    protected LayoutParams generateLayoutParams(ViewGroup$LayoutParams arg2) {
        if((arg2 instanceof LayoutParams)) {
            return new LayoutParams(((LayoutParams)arg2));
        }

        if((arg2 instanceof android.support.v7.app.ActionBar$LayoutParams)) {
            return new LayoutParams(((android.support.v7.app.ActionBar$LayoutParams)arg2));
        }

        if((arg2 instanceof ViewGroup$MarginLayoutParams)) {
            return new LayoutParams(((ViewGroup$MarginLayoutParams)arg2));
        }

        return new LayoutParams(arg2);
    }

    public LayoutParams generateLayoutParams(AttributeSet arg3) {
        return new LayoutParams(this.getContext(), arg3);
    }

    public ViewGroup$LayoutParams generateLayoutParams(AttributeSet arg1) {
        return this.generateLayoutParams(arg1);
    }

    protected ViewGroup$LayoutParams generateLayoutParams(ViewGroup$LayoutParams arg1) {
        return this.generateLayoutParams(arg1);
    }

    private int getChildHorizontalGravity(int arg5) {
        int v0 = ViewCompat.getLayoutDirection(((View)this));
        arg5 = GravityCompat.getAbsoluteGravity(arg5, v0) & 7;
        if(arg5 != 1) {
            int v2 = 3;
            if(arg5 != v2 && arg5 != 5) {
                if(v0 == 1) {
                    v2 = 5;
                }

                return v2;
            }
        }

        return arg5;
    }

    private int getChildTop(View arg7, int arg8) {
        ViewGroup$LayoutParams v0 = arg7.getLayoutParams();
        int v7 = arg7.getMeasuredHeight();
        arg8 = arg8 > 0 ? (v7 - arg8) / 2 : 0;
        int v2 = this.getChildVerticalGravity(((LayoutParams)v0).gravity);
        if(v2 != 0x30) {
            if(v2 != 80) {
                arg8 = this.getPaddingTop();
                v2 = this.getPaddingBottom();
                int v3 = this.getHeight();
                int v4 = (v3 - arg8 - v2 - v7) / 2;
                if(v4 < ((LayoutParams)v0).topMargin) {
                    v4 = ((LayoutParams)v0).topMargin;
                }
                else {
                    v3 = v3 - v2 - v7 - v4 - arg8;
                    if(v3 < ((LayoutParams)v0).bottomMargin) {
                        v4 = Math.max(0, v4 - (((LayoutParams)v0).bottomMargin - v3));
                    }
                }

                return arg8 + v4;
            }

            return this.getHeight() - this.getPaddingBottom() - v7 - ((LayoutParams)v0).bottomMargin - arg8;
        }

        return this.getPaddingTop() - arg8;
    }

    private int getChildVerticalGravity(int arg2) {
        arg2 &= 0x70;
        if(arg2 != 16 && arg2 != 0x30 && arg2 != 80) {
            return this.mGravity & 0x70;
        }

        return arg2;
    }

    public int getContentInsetEnd() {
        int v0 = this.mContentInsets != null ? this.mContentInsets.getEnd() : 0;
        return v0;
    }

    public int getContentInsetEndWithActions() {
        int v0 = this.mContentInsetEndWithActions != 0x80000000 ? this.mContentInsetEndWithActions : this.getContentInsetEnd();
        return v0;
    }

    public int getContentInsetLeft() {
        int v0 = this.mContentInsets != null ? this.mContentInsets.getLeft() : 0;
        return v0;
    }

    public int getContentInsetRight() {
        int v0 = this.mContentInsets != null ? this.mContentInsets.getRight() : 0;
        return v0;
    }

    public int getContentInsetStart() {
        int v0 = this.mContentInsets != null ? this.mContentInsets.getStart() : 0;
        return v0;
    }

    public int getContentInsetStartWithNavigation() {
        int v0 = this.mContentInsetStartWithNavigation != 0x80000000 ? this.mContentInsetStartWithNavigation : this.getContentInsetStart();
        return v0;
    }

    public int getCurrentContentInsetEnd() {
        int v0_1;
        if(this.mMenuView != null) {
            MenuBuilder v0 = this.mMenuView.peekMenu();
            if(v0 == null) {
                goto label_10;
            }
            else if(v0.hasVisibleItems()) {
                v0_1 = 1;
            }
            else {
                goto label_10;
            }
        }
        else {
        label_10:
            v0_1 = 0;
        }

        return v0_1 != 0 ? Math.max(this.getContentInsetEnd(), Math.max(this.mContentInsetEndWithActions, 0)) : this.getContentInsetEnd();
    }

    public int getCurrentContentInsetLeft() {
        int v0 = ViewCompat.getLayoutDirection(((View)this)) == 1 ? this.getCurrentContentInsetEnd() : this.getCurrentContentInsetStart();
        return v0;
    }

    public int getCurrentContentInsetRight() {
        int v0 = ViewCompat.getLayoutDirection(((View)this)) == 1 ? this.getCurrentContentInsetStart() : this.getCurrentContentInsetEnd();
        return v0;
    }

    public int getCurrentContentInsetStart() {
        int v0 = this.getNavigationIcon() != null ? Math.max(this.getContentInsetStart(), Math.max(this.mContentInsetStartWithNavigation, 0)) : this.getContentInsetStart();
        return v0;
    }

    private int getHorizontalMargins(View arg2) {
        ViewGroup$LayoutParams v2 = arg2.getLayoutParams();
        return MarginLayoutParamsCompat.getMarginStart(((ViewGroup$MarginLayoutParams)v2)) + MarginLayoutParamsCompat.getMarginEnd(((ViewGroup$MarginLayoutParams)v2));
    }

    public Drawable getLogo() {
        Drawable v0 = this.mLogoView != null ? this.mLogoView.getDrawable() : null;
        return v0;
    }

    public CharSequence getLogoDescription() {
        CharSequence v0 = this.mLogoView != null ? this.mLogoView.getContentDescription() : null;
        return v0;
    }

    public Menu getMenu() {
        this.ensureMenu();
        return this.mMenuView.getMenu();
    }

    private MenuInflater getMenuInflater() {
        return new SupportMenuInflater(this.getContext());
    }

    @Nullable public CharSequence getNavigationContentDescription() {
        CharSequence v0 = this.mNavButtonView != null ? this.mNavButtonView.getContentDescription() : null;
        return v0;
    }

    @Nullable public Drawable getNavigationIcon() {
        Drawable v0 = this.mNavButtonView != null ? this.mNavButtonView.getDrawable() : null;
        return v0;
    }

    ActionMenuPresenter getOuterActionMenuPresenter() {
        return this.mOuterActionMenuPresenter;
    }

    @Nullable public Drawable getOverflowIcon() {
        this.ensureMenu();
        return this.mMenuView.getOverflowIcon();
    }

    Context getPopupContext() {
        return this.mPopupContext;
    }

    public int getPopupTheme() {
        return this.mPopupTheme;
    }

    public CharSequence getSubtitle() {
        return this.mSubtitleText;
    }

    public CharSequence getTitle() {
        return this.mTitleText;
    }

    public int getTitleMarginBottom() {
        return this.mTitleMarginBottom;
    }

    public int getTitleMarginEnd() {
        return this.mTitleMarginEnd;
    }

    public int getTitleMarginStart() {
        return this.mTitleMarginStart;
    }

    public int getTitleMarginTop() {
        return this.mTitleMarginTop;
    }

    private int getVerticalMargins(View arg2) {
        ViewGroup$LayoutParams v2 = arg2.getLayoutParams();
        return ((ViewGroup$MarginLayoutParams)v2).topMargin + ((ViewGroup$MarginLayoutParams)v2).bottomMargin;
    }

    private int getViewListMeasuredWidth(List arg9, int[] arg10) {
        int v1 = arg10[0];
        int v10 = arg10[1];
        int v2 = arg9.size();
        int v3 = v10;
        v10 = 0;
        int v4 = 0;
        while(v10 < v2) {
            Object v5 = arg9.get(v10);
            ViewGroup$LayoutParams v6 = ((View)v5).getLayoutParams();
            int v7 = ((LayoutParams)v6).leftMargin - v1;
            v1 = ((LayoutParams)v6).rightMargin - v3;
            v3 = Math.max(0, v7);
            int v6_1 = Math.max(0, v1);
            v7 = Math.max(0, -v7);
            v1 = Math.max(0, -v1);
            v4 += v3 + ((View)v5).getMeasuredWidth() + v6_1;
            ++v10;
            v3 = v1;
            v1 = v7;
        }

        return v4;
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public DecorToolbar getWrapper() {
        if(this.mWrapper == null) {
            this.mWrapper = new ToolbarWidgetWrapper(this, true);
        }

        return this.mWrapper;
    }

    public boolean hasExpandedActionView() {
        boolean v0 = this.mExpandedMenuPresenter == null || this.mExpandedMenuPresenter.mCurrentExpandedItem == null ? false : true;
        return v0;
    }

    public boolean hideOverflowMenu() {
        boolean v0 = this.mMenuView == null || !this.mMenuView.hideOverflowMenu() ? false : true;
        return v0;
    }

    public void inflateMenu(@MenuRes int arg3) {
        this.getMenuInflater().inflate(arg3, this.getMenu());
    }

    private boolean isChildOrHidden(View arg2) {
        boolean v2 = arg2.getParent() == this || (this.mHiddenViews.contains(arg2)) ? true : false;
        return v2;
    }

    private static boolean isCustomView(View arg0) {
        boolean v0 = arg0.getLayoutParams().mViewType == 0 ? true : false;
        return v0;
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public boolean isOverflowMenuShowPending() {
        boolean v0 = this.mMenuView == null || !this.mMenuView.isOverflowMenuShowPending() ? false : true;
        return v0;
    }

    public boolean isOverflowMenuShowing() {
        boolean v0 = this.mMenuView == null || !this.mMenuView.isOverflowMenuShowing() ? false : true;
        return v0;
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public boolean isTitleTruncated() {
        if(this.mTitleTextView == null) {
            return 0;
        }

        Layout v0 = this.mTitleTextView.getLayout();
        if(v0 == null) {
            return 0;
        }

        int v2 = v0.getLineCount();
        int v3;
        for(v3 = 0; v3 < v2; ++v3) {
            if(v0.getEllipsisCount(v3) > 0) {
                return 1;
            }
        }

        return 0;
    }

    private int layoutChildLeft(View arg5, int arg6, int[] arg7, int arg8) {
        ViewGroup$LayoutParams v0 = arg5.getLayoutParams();
        int v1 = ((LayoutParams)v0).leftMargin - arg7[0];
        arg6 += Math.max(0, v1);
        arg7[0] = Math.max(0, -v1);
        int v7 = this.getChildTop(arg5, arg8);
        arg8 = arg5.getMeasuredWidth();
        arg5.layout(arg6, v7, arg6 + arg8, arg5.getMeasuredHeight() + v7);
        return arg6 + (arg8 + ((LayoutParams)v0).rightMargin);
    }

    private int layoutChildRight(View arg6, int arg7, int[] arg8, int arg9) {
        ViewGroup$LayoutParams v0 = arg6.getLayoutParams();
        int v1 = ((LayoutParams)v0).rightMargin - arg8[1];
        arg7 -= Math.max(0, v1);
        arg8[1] = Math.max(0, -v1);
        int v8 = this.getChildTop(arg6, arg9);
        arg9 = arg6.getMeasuredWidth();
        arg6.layout(arg7 - arg9, v8, arg7, arg6.getMeasuredHeight() + v8);
        return arg7 - (arg9 + ((LayoutParams)v0).leftMargin);
    }

    private int measureChildCollapseMargins(View arg8, int arg9, int arg10, int arg11, int arg12, int[] arg13) {
        ViewGroup$LayoutParams v0 = arg8.getLayoutParams();
        int v1 = ((ViewGroup$MarginLayoutParams)v0).leftMargin - arg13[0];
        int v3 = ((ViewGroup$MarginLayoutParams)v0).rightMargin - arg13[1];
        int v5 = Math.max(0, v1) + Math.max(0, v3);
        arg13[0] = Math.max(0, -v1);
        arg13[1] = Math.max(0, -v3);
        arg8.measure(Toolbar.getChildMeasureSpec(arg9, this.getPaddingLeft() + this.getPaddingRight() + v5 + arg10, ((ViewGroup$MarginLayoutParams)v0).width), Toolbar.getChildMeasureSpec(arg11, this.getPaddingTop() + this.getPaddingBottom() + ((ViewGroup$MarginLayoutParams)v0).topMargin + ((ViewGroup$MarginLayoutParams)v0).bottomMargin + arg12, ((ViewGroup$MarginLayoutParams)v0).height));
        return arg8.getMeasuredWidth() + v5;
    }

    private void measureChildConstrained(View arg4, int arg5, int arg6, int arg7, int arg8, int arg9) {
        ViewGroup$LayoutParams v0 = arg4.getLayoutParams();
        arg5 = Toolbar.getChildMeasureSpec(arg5, this.getPaddingLeft() + this.getPaddingRight() + ((ViewGroup$MarginLayoutParams)v0).leftMargin + ((ViewGroup$MarginLayoutParams)v0).rightMargin + arg6, ((ViewGroup$MarginLayoutParams)v0).width);
        arg6 = Toolbar.getChildMeasureSpec(arg7, this.getPaddingTop() + this.getPaddingBottom() + ((ViewGroup$MarginLayoutParams)v0).topMargin + ((ViewGroup$MarginLayoutParams)v0).bottomMargin + arg8, ((ViewGroup$MarginLayoutParams)v0).height);
        arg7 = View$MeasureSpec.getMode(arg6);
        arg8 = 0x40000000;
        if(arg7 != arg8 && arg9 >= 0) {
            if(arg7 != 0) {
                arg9 = Math.min(View$MeasureSpec.getSize(arg6), arg9);
            }

            arg6 = View$MeasureSpec.makeMeasureSpec(arg9, arg8);
        }

        arg4.measure(arg5, arg6);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.removeCallbacks(this.mShowOverflowMenuRunnable);
    }

    public boolean onHoverEvent(MotionEvent arg6) {
        int v0 = arg6.getActionMasked();
        int v2 = 9;
        if(v0 == v2) {
            this.mEatingHover = false;
        }

        if(!this.mEatingHover) {
            boolean v6 = super.onHoverEvent(arg6);
            if(v0 == v2 && !v6) {
                this.mEatingHover = true;
            }
        }

        if(v0 == 10 || v0 == 3) {
            this.mEatingHover = false;
        }

        return 1;
    }

    protected void onLayout(boolean arg25, int arg26, int arg27, int arg28, int arg29) {
        int v22;
        int v21;
        int v18;
        ViewGroup$LayoutParams v1_1;
        int v23;
        int v20;
        ViewGroup$LayoutParams v7_1;
        int v3;
        int v19;
        int v14;
        int v13;
        Toolbar v0 = this;
        int v1 = ViewCompat.getLayoutDirection(((View)this)) == 1 ? 1 : 0;
        int v4 = this.getWidth();
        int v5 = this.getHeight();
        int v6 = this.getPaddingLeft();
        int v7 = this.getPaddingRight();
        int v8 = this.getPaddingTop();
        int v9 = this.getPaddingBottom();
        int v10 = v4 - v7;
        int[] v11 = v0.mTempMargins;
        v11[1] = 0;
        v11[0] = 0;
        int v12 = ViewCompat.getMinimumHeight(((View)this));
        v12 = v12 >= 0 ? Math.min(v12, arg29 - arg27) : 0;
        if(!v0.shouldLayout(v0.mNavButtonView)) {
            v14 = v6;
        label_37:
            v13 = v10;
        }
        else if(v1 != 0) {
            v13 = v0.layoutChildRight(v0.mNavButtonView, v10, v11, v12);
            v14 = v6;
        }
        else {
            v14 = v0.layoutChildLeft(v0.mNavButtonView, v6, v11, v12);
            goto label_37;
        }

        if(v0.shouldLayout(v0.mCollapseButtonView)) {
            if(v1 != 0) {
                v13 = v0.layoutChildRight(v0.mCollapseButtonView, v13, v11, v12);
            }
            else {
                v14 = v0.layoutChildLeft(v0.mCollapseButtonView, v14, v11, v12);
            }
        }

        if(v0.shouldLayout(v0.mMenuView)) {
            if(v1 != 0) {
                v14 = v0.layoutChildLeft(v0.mMenuView, v14, v11, v12);
            }
            else {
                v13 = v0.layoutChildRight(v0.mMenuView, v13, v11, v12);
            }
        }

        int v15 = this.getCurrentContentInsetLeft();
        int v16 = this.getCurrentContentInsetRight();
        v11[0] = Math.max(0, v15 - v14);
        v11[1] = Math.max(0, v16 - (v10 - v13));
        int v2 = Math.max(v14, v15);
        v10 = Math.min(v13, v10 - v16);
        if(v0.shouldLayout(v0.mExpandedActionView)) {
            if(v1 != 0) {
                v10 = v0.layoutChildRight(v0.mExpandedActionView, v10, v11, v12);
            }
            else {
                v2 = v0.layoutChildLeft(v0.mExpandedActionView, v2, v11, v12);
            }
        }

        if(v0.shouldLayout(v0.mLogoView)) {
            if(v1 != 0) {
                v10 = v0.layoutChildRight(v0.mLogoView, v10, v11, v12);
            }
            else {
                v2 = v0.layoutChildLeft(v0.mLogoView, v2, v11, v12);
            }
        }

        boolean v13_1 = v0.shouldLayout(v0.mTitleTextView);
        boolean v14_1 = v0.shouldLayout(v0.mSubtitleTextView);
        if(v13_1) {
            ViewGroup$LayoutParams v15_1 = v0.mTitleTextView.getLayoutParams();
            v19 = v7;
            v3 = ((LayoutParams)v15_1).topMargin + v0.mTitleTextView.getMeasuredHeight() + ((LayoutParams)v15_1).bottomMargin;
        }
        else {
            v19 = v7;
            v3 = 0;
        }

        if(v14_1) {
            v7_1 = v0.mSubtitleTextView.getLayoutParams();
            v20 = v4;
            v3 += ((LayoutParams)v7_1).topMargin + v0.mSubtitleTextView.getMeasuredHeight() + ((LayoutParams)v7_1).bottomMargin;
        }
        else {
            v20 = v4;
        }

        if((v13_1) || (v14_1)) {
            TextView v4_1 = v13_1 ? v0.mTitleTextView : v0.mSubtitleTextView;
            TextView v7_2 = v14_1 ? v0.mSubtitleTextView : v0.mTitleTextView;
            ViewGroup$LayoutParams v4_2 = ((View)v4_1).getLayoutParams();
            v7_1 = ((View)v7_2).getLayoutParams();
            if(!v13_1 || v0.mTitleTextView.getMeasuredWidth() <= 0) {
                if((v14_1) && v0.mSubtitleTextView.getMeasuredWidth() > 0) {
                label_144:
                    v21 = v6;
                    v15 = 1;
                    goto label_149;
                }

                v21 = v6;
                v15 = 0;
            }
            else {
                goto label_144;
            }

        label_149:
            v6 = v0.mGravity & 0x70;
            v22 = v12;
            if(v6 == 0x30) {
                v23 = v2;
                v8 = this.getPaddingTop() + ((LayoutParams)v4_2).topMargin + v0.mTitleMarginTop;
            }
            else if(v6 != 80) {
                v6 = (v5 - v8 - v9 - v3) / 2;
                v23 = v2;
                if(v6 < ((LayoutParams)v4_2).topMargin + v0.mTitleMarginTop) {
                    v6 = ((LayoutParams)v4_2).topMargin + v0.mTitleMarginTop;
                }
                else {
                    v5 = v5 - v9 - v3 - v6 - v8;
                    if(v5 < ((LayoutParams)v4_2).bottomMargin + v0.mTitleMarginBottom) {
                        v6 = Math.max(0, v6 - (((LayoutParams)v7_1).bottomMargin + v0.mTitleMarginBottom - v5));
                    }
                }

                v8 += v6;
            }
            else {
                v23 = v2;
                v8 = v5 - v9 - ((LayoutParams)v7_1).bottomMargin - v0.mTitleMarginBottom - v3;
            }

            if(v1 != 0) {
                if(v15 != 0) {
                    v3 = v0.mTitleMarginStart;
                    v1 = 1;
                }
                else {
                    v1 = 1;
                    v3 = 0;
                }

                v3 -= v11[v1];
                v10 -= Math.max(0, v3);
                v11[v1] = Math.max(0, -v3);
                if(v13_1) {
                    v1_1 = v0.mTitleTextView.getLayoutParams();
                    v2 = v10 - v0.mTitleTextView.getMeasuredWidth();
                    v3 = v0.mTitleTextView.getMeasuredHeight() + v8;
                    v0.mTitleTextView.layout(v2, v8, v10, v3);
                    v2 -= v0.mTitleMarginEnd;
                    v8 = v3 + ((LayoutParams)v1_1).bottomMargin;
                }
                else {
                    v2 = v10;
                }

                if(v14_1) {
                    v8 += v0.mSubtitleTextView.getLayoutParams().topMargin;
                    v0.mSubtitleTextView.layout(v10 - v0.mSubtitleTextView.getMeasuredWidth(), v8, v10, v0.mSubtitleTextView.getMeasuredHeight() + v8);
                    v3 = v10 - v0.mTitleMarginEnd;
                }
                else {
                    v3 = v10;
                }

                if(v15 != 0) {
                    v10 = Math.min(v2, v3);
                }

                v2 = v23;
            label_124:
                v7 = 0;
                goto label_306;
            }

            if(v15 != 0) {
                v18 = v0.mTitleMarginStart;
                v7 = 0;
            }
            else {
                v7 = 0;
                v18 = 0;
            }

            v1 = v18 - v11[v7];
            v2 = v23 + Math.max(v7, v1);
            v11[v7] = Math.max(v7, -v1);
            if(v13_1) {
                v1_1 = v0.mTitleTextView.getLayoutParams();
                v3 = v0.mTitleTextView.getMeasuredWidth() + v2;
                v4 = v0.mTitleTextView.getMeasuredHeight() + v8;
                v0.mTitleTextView.layout(v2, v8, v3, v4);
                v3 += v0.mTitleMarginEnd;
                v8 = v4 + ((LayoutParams)v1_1).bottomMargin;
            }
            else {
                v3 = v2;
            }

            if(v14_1) {
                v8 += v0.mSubtitleTextView.getLayoutParams().topMargin;
                v4 = v0.mSubtitleTextView.getMeasuredWidth() + v2;
                v0.mSubtitleTextView.layout(v2, v8, v4, v0.mSubtitleTextView.getMeasuredHeight() + v8);
                v4 += v0.mTitleMarginEnd;
            }
            else {
                v4 = v2;
            }

            if(v15 == 0) {
                goto label_306;
            }

            v2 = Math.max(v3, v4);
        }
        else {
            v21 = v6;
            v22 = v12;
            goto label_124;
        }

    label_306:
        v0.addCustomViewsWithGravity(v0.mTempViews, 3);
        v1 = v0.mTempViews.size();
        v3 = v2;
        for(v2 = 0; v2 < v1; ++v2) {
            v3 = v0.layoutChildLeft(v0.mTempViews.get(v2), v3, v11, v22);
        }

        v12 = v22;
        v0.addCustomViewsWithGravity(v0.mTempViews, 5);
        v1 = v0.mTempViews.size();
        for(v2 = 0; v2 < v1; ++v2) {
            v10 = v0.layoutChildRight(v0.mTempViews.get(v2), v10, v11, v12);
        }

        v0.addCustomViewsWithGravity(v0.mTempViews, 1);
        v1 = v0.getViewListMeasuredWidth(v0.mTempViews, v11);
        v2 = v21 + (v20 - v21 - v19) / 2 - v1 / 2;
        v1 += v2;
        if(v2 < v3) {
        }
        else if(v1 > v10) {
            v3 = v2 - (v1 - v10);
        }
        else {
            v3 = v2;
        }

        v1 = v0.mTempViews.size();
        while(v7 < v1) {
            v3 = v0.layoutChildLeft(v0.mTempViews.get(v7), v3, v11, v12);
            ++v7;
        }

        v0.mTempViews.clear();
    }

    protected void onMeasure(int arg17, int arg18) {
        int v6;
        int v15_1;
        int v13;
        int v12;
        int v0;
        int v11;
        int v10;
        Toolbar v7 = this;
        int[] v8 = v7.mTempMargins;
        if(ViewUtils.isLayoutRtl(((View)this))) {
            v10 = 1;
            v11 = 0;
        }
        else {
            v10 = 0;
            v11 = 1;
        }

        if(v7.shouldLayout(v7.mNavButtonView)) {
            v7.measureChildConstrained(v7.mNavButtonView, arg17, 0, arg18, 0, v7.mMaxButtonHeight);
            v0 = v7.mNavButtonView.getMeasuredWidth() + v7.getHorizontalMargins(v7.mNavButtonView);
            v12 = Math.max(0, v7.mNavButtonView.getMeasuredHeight() + v7.getVerticalMargins(v7.mNavButtonView));
            v13 = View.combineMeasuredStates(0, v7.mNavButtonView.getMeasuredState());
        }
        else {
            v0 = 0;
            v12 = 0;
            v13 = 0;
        }

        if(v7.shouldLayout(v7.mCollapseButtonView)) {
            v7.measureChildConstrained(v7.mCollapseButtonView, arg17, 0, arg18, 0, v7.mMaxButtonHeight);
            v0 = v7.mCollapseButtonView.getMeasuredWidth() + v7.getHorizontalMargins(v7.mCollapseButtonView);
            v12 = Math.max(v12, v7.mCollapseButtonView.getMeasuredHeight() + v7.getVerticalMargins(v7.mCollapseButtonView));
            v13 = View.combineMeasuredStates(v13, v7.mCollapseButtonView.getMeasuredState());
        }

        int v1 = this.getCurrentContentInsetStart();
        int v14 = Math.max(v1, v0);
        v8[v10] = Math.max(0, v1 - v0);
        if(v7.shouldLayout(v7.mMenuView)) {
            v7.measureChildConstrained(v7.mMenuView, arg17, v14, arg18, 0, v7.mMaxButtonHeight);
            v0 = v7.mMenuView.getMeasuredWidth() + v7.getHorizontalMargins(v7.mMenuView);
            v12 = Math.max(v12, v7.mMenuView.getMeasuredHeight() + v7.getVerticalMargins(v7.mMenuView));
            v13 = View.combineMeasuredStates(v13, v7.mMenuView.getMeasuredState());
        }
        else {
            v0 = 0;
        }

        v1 = this.getCurrentContentInsetEnd();
        v10 = v14 + Math.max(v1, v0);
        v8[v11] = Math.max(0, v1 - v0);
        if(v7.shouldLayout(v7.mExpandedActionView)) {
            v10 += v7.measureChildCollapseMargins(v7.mExpandedActionView, arg17, v10, arg18, 0, v8);
            v12 = Math.max(v12, v7.mExpandedActionView.getMeasuredHeight() + v7.getVerticalMargins(v7.mExpandedActionView));
            v13 = View.combineMeasuredStates(v13, v7.mExpandedActionView.getMeasuredState());
        }

        if(v7.shouldLayout(v7.mLogoView)) {
            v10 += v7.measureChildCollapseMargins(v7.mLogoView, arg17, v10, arg18, 0, v8);
            v12 = Math.max(v12, v7.mLogoView.getMeasuredHeight() + v7.getVerticalMargins(v7.mLogoView));
            v13 = View.combineMeasuredStates(v13, v7.mLogoView.getMeasuredState());
        }

        v11 = this.getChildCount();
        v14 = v12;
        v12 = v10;
        for(v10 = 0; v10 < v11; ++v10) {
            View v15 = v7.getChildAt(v10);
            if(v15.getLayoutParams().mViewType == 0) {
                if(!v7.shouldLayout(v15)) {
                }
                else {
                    v12 += v7.measureChildCollapseMargins(v15, arg17, v12, arg18, 0, v8);
                    v14 = Math.max(v14, v15.getMeasuredHeight() + v7.getVerticalMargins(v15));
                    v13 = View.combineMeasuredStates(v13, v15.getMeasuredState());
                }
            }
        }

        v10 = v7.mTitleMarginTop + v7.mTitleMarginBottom;
        v11 = v7.mTitleMarginStart + v7.mTitleMarginEnd;
        if(v7.shouldLayout(v7.mTitleTextView)) {
            v7.measureChildCollapseMargins(v7.mTitleTextView, arg17, v12 + v11, arg18, v10, v8);
            v0 = v7.mTitleTextView.getMeasuredWidth() + v7.getHorizontalMargins(v7.mTitleTextView);
            v15_1 = v7.mTitleTextView.getMeasuredHeight() + v7.getVerticalMargins(v7.mTitleTextView);
            v6 = View.combineMeasuredStates(v13, v7.mTitleTextView.getMeasuredState());
            v13 = v0;
        }
        else {
            v6 = v13;
            v13 = 0;
            v15_1 = 0;
        }

        if(v7.shouldLayout(v7.mSubtitleTextView)) {
            v13 = Math.max(v13, v7.measureChildCollapseMargins(v7.mSubtitleTextView, arg17, v12 + v11, arg18, v15_1 + v10, v8));
            v15_1 += v7.mSubtitleTextView.getMeasuredHeight() + v7.getVerticalMargins(v7.mSubtitleTextView);
            v6 = View.combineMeasuredStates(v6, v7.mSubtitleTextView.getMeasuredState());
        }

        v0 = Math.max(v14, v15_1);
        v12 = v12 + v13 + (this.getPaddingLeft() + this.getPaddingRight());
        v0 += this.getPaddingTop() + this.getPaddingBottom();
        v1 = View.resolveSizeAndState(Math.max(v12, this.getSuggestedMinimumWidth()), arg17, 0xFF000000 & v6);
        v0 = View.resolveSizeAndState(Math.max(v0, this.getSuggestedMinimumHeight()), arg18, v6 << 16);
        if(this.shouldCollapse()) {
            v0 = 0;
        }

        v7.setMeasuredDimension(v1, v0);
    }

    protected void onRestoreInstanceState(Parcelable arg3) {
        if(!(arg3 instanceof SavedState)) {
            super.onRestoreInstanceState(arg3);
            return;
        }

        super.onRestoreInstanceState(((SavedState)arg3).getSuperState());
        MenuBuilder v0 = this.mMenuView != null ? this.mMenuView.peekMenu() : null;
        if(((SavedState)arg3).expandedMenuItemId != 0 && this.mExpandedMenuPresenter != null && v0 != null) {
            MenuItem v0_1 = ((Menu)v0).findItem(((SavedState)arg3).expandedMenuItemId);
            if(v0_1 != null) {
                v0_1.expandActionView();
            }
        }

        if(((SavedState)arg3).isOverflowOpen) {
            this.postShowOverflowMenu();
        }
    }

    public void onRtlPropertiesChanged(int arg3) {
        if(Build$VERSION.SDK_INT >= 17) {
            super.onRtlPropertiesChanged(arg3);
        }

        this.ensureContentInsets();
        RtlSpacingHelper v0 = this.mContentInsets;
        boolean v1 = true;
        if(arg3 == 1) {
        }
        else {
            v1 = false;
        }

        v0.setDirection(v1);
    }

    protected Parcelable onSaveInstanceState() {
        SavedState v0 = new SavedState(super.onSaveInstanceState());
        if(this.mExpandedMenuPresenter != null && this.mExpandedMenuPresenter.mCurrentExpandedItem != null) {
            v0.expandedMenuItemId = this.mExpandedMenuPresenter.mCurrentExpandedItem.getItemId();
        }

        v0.isOverflowOpen = this.isOverflowMenuShowing();
        return ((Parcelable)v0);
    }

    public boolean onTouchEvent(MotionEvent arg5) {
        int v0 = arg5.getActionMasked();
        if(v0 == 0) {
            this.mEatingTouch = false;
        }

        if(!this.mEatingTouch) {
            boolean v5 = super.onTouchEvent(arg5);
            if(v0 == 0 && !v5) {
                this.mEatingTouch = true;
            }
        }

        if(v0 == 1 || v0 == 3) {
            this.mEatingTouch = false;
        }

        return 1;
    }

    private void postShowOverflowMenu() {
        this.removeCallbacks(this.mShowOverflowMenuRunnable);
        this.post(this.mShowOverflowMenuRunnable);
    }

    void removeChildrenForExpandedActionView() {
        int v0;
        for(v0 = this.getChildCount() - 1; v0 >= 0; --v0) {
            View v1 = this.getChildAt(v0);
            if(v1.getLayoutParams().mViewType != 2 && v1 != this.mMenuView) {
                this.removeViewAt(v0);
                this.mHiddenViews.add(v1);
            }
        }
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public void setCollapsible(boolean arg1) {
        this.mCollapsible = arg1;
        this.requestLayout();
    }

    public void setContentInsetEndWithActions(int arg2) {
        if(arg2 < 0) {
            arg2 = 0x80000000;
        }

        if(arg2 != this.mContentInsetEndWithActions) {
            this.mContentInsetEndWithActions = arg2;
            if(this.getNavigationIcon() != null) {
                this.requestLayout();
            }
        }
    }

    public void setContentInsetStartWithNavigation(int arg2) {
        if(arg2 < 0) {
            arg2 = 0x80000000;
        }

        if(arg2 != this.mContentInsetStartWithNavigation) {
            this.mContentInsetStartWithNavigation = arg2;
            if(this.getNavigationIcon() != null) {
                this.requestLayout();
            }
        }
    }

    public void setContentInsetsAbsolute(int arg2, int arg3) {
        this.ensureContentInsets();
        this.mContentInsets.setAbsolute(arg2, arg3);
    }

    public void setContentInsetsRelative(int arg2, int arg3) {
        this.ensureContentInsets();
        this.mContentInsets.setRelative(arg2, arg3);
    }

    public void setLogo(Drawable arg3) {
        if(arg3 != null) {
            this.ensureLogoView();
            if(!this.isChildOrHidden(this.mLogoView)) {
                this.addSystemView(this.mLogoView, true);
            }
        }
        else if(this.mLogoView != null && (this.isChildOrHidden(this.mLogoView))) {
            this.removeView(this.mLogoView);
            this.mHiddenViews.remove(this.mLogoView);
        }

        if(this.mLogoView != null) {
            this.mLogoView.setImageDrawable(arg3);
        }
    }

    public void setLogo(@DrawableRes int arg2) {
        this.setLogo(AppCompatResources.getDrawable(this.getContext(), arg2));
    }

    public void setLogoDescription(CharSequence arg2) {
        if(!TextUtils.isEmpty(arg2)) {
            this.ensureLogoView();
        }

        if(this.mLogoView != null) {
            this.mLogoView.setContentDescription(arg2);
        }
    }

    public void setLogoDescription(@StringRes int arg2) {
        this.setLogoDescription(this.getContext().getText(arg2));
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public void setMenu(MenuBuilder arg4, ActionMenuPresenter arg5) {
        if(arg4 == null && this.mMenuView == null) {
            return;
        }

        this.ensureMenuView();
        MenuBuilder v0 = this.mMenuView.peekMenu();
        if(v0 == arg4) {
            return;
        }

        if(v0 != null) {
            v0.removeMenuPresenter(this.mOuterActionMenuPresenter);
            v0.removeMenuPresenter(this.mExpandedMenuPresenter);
        }

        if(this.mExpandedMenuPresenter == null) {
            this.mExpandedMenuPresenter = new ExpandedActionViewMenuPresenter(this);
        }

        arg5.setExpandedActionViewsExclusive(true);
        if(arg4 != null) {
            arg4.addMenuPresenter(((MenuPresenter)arg5), this.mPopupContext);
            arg4.addMenuPresenter(this.mExpandedMenuPresenter, this.mPopupContext);
        }
        else {
            arg5.initForMenu(this.mPopupContext, null);
            this.mExpandedMenuPresenter.initForMenu(this.mPopupContext, null);
            arg5.updateMenuView(true);
            this.mExpandedMenuPresenter.updateMenuView(true);
        }

        this.mMenuView.setPopupTheme(this.mPopupTheme);
        this.mMenuView.setPresenter(arg5);
        this.mOuterActionMenuPresenter = arg5;
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public void setMenuCallbacks(Callback arg2, android.support.v7.view.menu.MenuBuilder$Callback arg3) {
        this.mActionMenuPresenterCallback = arg2;
        this.mMenuBuilderCallback = arg3;
        if(this.mMenuView != null) {
            this.mMenuView.setMenuCallbacks(arg2, arg3);
        }
    }

    public void setNavigationContentDescription(@Nullable CharSequence arg2) {
        if(!TextUtils.isEmpty(arg2)) {
            this.ensureNavButtonView();
        }

        if(this.mNavButtonView != null) {
            this.mNavButtonView.setContentDescription(arg2);
        }
    }

    public void setNavigationContentDescription(@StringRes int arg2) {
        CharSequence v2 = arg2 != 0 ? this.getContext().getText(arg2) : null;
        this.setNavigationContentDescription(v2);
    }

    public void setNavigationIcon(@Nullable Drawable arg3) {
        if(arg3 != null) {
            this.ensureNavButtonView();
            if(!this.isChildOrHidden(this.mNavButtonView)) {
                this.addSystemView(this.mNavButtonView, true);
            }
        }
        else if(this.mNavButtonView != null && (this.isChildOrHidden(this.mNavButtonView))) {
            this.removeView(this.mNavButtonView);
            this.mHiddenViews.remove(this.mNavButtonView);
        }

        if(this.mNavButtonView != null) {
            this.mNavButtonView.setImageDrawable(arg3);
        }
    }

    public void setNavigationIcon(@DrawableRes int arg2) {
        this.setNavigationIcon(AppCompatResources.getDrawable(this.getContext(), arg2));
    }

    public void setNavigationOnClickListener(View$OnClickListener arg2) {
        this.ensureNavButtonView();
        this.mNavButtonView.setOnClickListener(arg2);
    }

    public void setOnMenuItemClickListener(android.support.v7.widget.Toolbar$OnMenuItemClickListener arg1) {
        this.mOnMenuItemClickListener = arg1;
    }

    public void setOverflowIcon(@Nullable Drawable arg2) {
        this.ensureMenu();
        this.mMenuView.setOverflowIcon(arg2);
    }

    public void setPopupTheme(@StyleRes int arg3) {
        if(this.mPopupTheme != arg3) {
            this.mPopupTheme = arg3;
            this.mPopupContext = arg3 == 0 ? this.getContext() : new ContextThemeWrapper(this.getContext(), arg3);
        }
    }

    public void setSubtitle(CharSequence arg4) {
        if(!TextUtils.isEmpty(arg4)) {
            if(this.mSubtitleTextView == null) {
                Context v0 = this.getContext();
                this.mSubtitleTextView = new AppCompatTextView(v0);
                this.mSubtitleTextView.setSingleLine();
                this.mSubtitleTextView.setEllipsize(TextUtils$TruncateAt.END);
                if(this.mSubtitleTextAppearance != 0) {
                    this.mSubtitleTextView.setTextAppearance(v0, this.mSubtitleTextAppearance);
                }

                if(this.mSubtitleTextColor == 0) {
                    goto label_23;
                }

                this.mSubtitleTextView.setTextColor(this.mSubtitleTextColor);
            }

        label_23:
            if(this.isChildOrHidden(this.mSubtitleTextView)) {
                goto label_40;
            }

            this.addSystemView(this.mSubtitleTextView, true);
        }
        else {
            if(this.mSubtitleTextView == null) {
                goto label_40;
            }

            if(!this.isChildOrHidden(this.mSubtitleTextView)) {
                goto label_40;
            }

            this.removeView(this.mSubtitleTextView);
            this.mHiddenViews.remove(this.mSubtitleTextView);
        }

    label_40:
        if(this.mSubtitleTextView != null) {
            this.mSubtitleTextView.setText(arg4);
        }

        this.mSubtitleText = arg4;
    }

    public void setSubtitle(@StringRes int arg2) {
        this.setSubtitle(this.getContext().getText(arg2));
    }

    public void setSubtitleTextAppearance(Context arg2, @StyleRes int arg3) {
        this.mSubtitleTextAppearance = arg3;
        if(this.mSubtitleTextView != null) {
            this.mSubtitleTextView.setTextAppearance(arg2, arg3);
        }
    }

    public void setSubtitleTextColor(@ColorInt int arg2) {
        this.mSubtitleTextColor = arg2;
        if(this.mSubtitleTextView != null) {
            this.mSubtitleTextView.setTextColor(arg2);
        }
    }

    public void setTitle(CharSequence arg4) {
        if(!TextUtils.isEmpty(arg4)) {
            if(this.mTitleTextView == null) {
                Context v0 = this.getContext();
                this.mTitleTextView = new AppCompatTextView(v0);
                this.mTitleTextView.setSingleLine();
                this.mTitleTextView.setEllipsize(TextUtils$TruncateAt.END);
                if(this.mTitleTextAppearance != 0) {
                    this.mTitleTextView.setTextAppearance(v0, this.mTitleTextAppearance);
                }

                if(this.mTitleTextColor == 0) {
                    goto label_23;
                }

                this.mTitleTextView.setTextColor(this.mTitleTextColor);
            }

        label_23:
            if(this.isChildOrHidden(this.mTitleTextView)) {
                goto label_40;
            }

            this.addSystemView(this.mTitleTextView, true);
        }
        else {
            if(this.mTitleTextView == null) {
                goto label_40;
            }

            if(!this.isChildOrHidden(this.mTitleTextView)) {
                goto label_40;
            }

            this.removeView(this.mTitleTextView);
            this.mHiddenViews.remove(this.mTitleTextView);
        }

    label_40:
        if(this.mTitleTextView != null) {
            this.mTitleTextView.setText(arg4);
        }

        this.mTitleText = arg4;
    }

    public void setTitle(@StringRes int arg2) {
        this.setTitle(this.getContext().getText(arg2));
    }

    public void setTitleMargin(int arg1, int arg2, int arg3, int arg4) {
        this.mTitleMarginStart = arg1;
        this.mTitleMarginTop = arg2;
        this.mTitleMarginEnd = arg3;
        this.mTitleMarginBottom = arg4;
        this.requestLayout();
    }

    public void setTitleMarginBottom(int arg1) {
        this.mTitleMarginBottom = arg1;
        this.requestLayout();
    }

    public void setTitleMarginEnd(int arg1) {
        this.mTitleMarginEnd = arg1;
        this.requestLayout();
    }

    public void setTitleMarginStart(int arg1) {
        this.mTitleMarginStart = arg1;
        this.requestLayout();
    }

    public void setTitleMarginTop(int arg1) {
        this.mTitleMarginTop = arg1;
        this.requestLayout();
    }

    public void setTitleTextAppearance(Context arg2, @StyleRes int arg3) {
        this.mTitleTextAppearance = arg3;
        if(this.mTitleTextView != null) {
            this.mTitleTextView.setTextAppearance(arg2, arg3);
        }
    }

    public void setTitleTextColor(@ColorInt int arg2) {
        this.mTitleTextColor = arg2;
        if(this.mTitleTextView != null) {
            this.mTitleTextView.setTextColor(arg2);
        }
    }

    private boolean shouldCollapse() {
        if(!this.mCollapsible) {
            return 0;
        }

        int v0 = this.getChildCount();
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            View v3 = this.getChildAt(v2);
            if((this.shouldLayout(v3)) && v3.getMeasuredWidth() > 0 && v3.getMeasuredHeight() > 0) {
                return 0;
            }
        }

        return 1;
    }

    private boolean shouldLayout(View arg2) {
        boolean v2 = arg2 == null || arg2.getParent() != this || arg2.getVisibility() == 8 ? false : true;
        return v2;
    }

    public boolean showOverflowMenu() {
        boolean v0 = this.mMenuView == null || !this.mMenuView.showOverflowMenu() ? false : true;
        return v0;
    }
}

