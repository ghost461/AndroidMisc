package android.support.v7.view.menu;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff$Mode;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.internal.view.SupportMenuItem;
import android.support.v4.view.ActionProvider$VisibilityListener;
import android.support.v4.view.ActionProvider;
import android.support.v7.content.res.AppCompatResources;
import android.util.Log;
import android.view.ContextMenu$ContextMenuInfo;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem$OnActionExpandListener;
import android.view.MenuItem$OnMenuItemClickListener;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewDebug$CapturedViewProperty;
import android.widget.LinearLayout;

@RestrictTo(value={Scope.LIBRARY_GROUP}) public final class MenuItemImpl implements SupportMenuItem {
    private static final int CHECKABLE = 1;
    private static final int CHECKED = 2;
    private static final int ENABLED = 16;
    private static final int EXCLUSIVE = 4;
    private static final int HIDDEN = 8;
    private static final int IS_ACTION = 0x20;
    static final int NO_ICON = 0;
    private static final int SHOW_AS_ACTION_MASK = 3;
    private static final String TAG = "MenuItemImpl";
    private ActionProvider mActionProvider;
    private View mActionView;
    private final int mCategoryOrder;
    private MenuItem$OnMenuItemClickListener mClickListener;
    private CharSequence mContentDescription;
    private int mFlags;
    private final int mGroup;
    private boolean mHasIconTint;
    private boolean mHasIconTintMode;
    private Drawable mIconDrawable;
    private int mIconResId;
    private ColorStateList mIconTintList;
    private PorterDuff$Mode mIconTintMode;
    private final int mId;
    private Intent mIntent;
    private boolean mIsActionViewExpanded;
    private Runnable mItemCallback;
    MenuBuilder mMenu;
    private ContextMenu$ContextMenuInfo mMenuInfo;
    private boolean mNeedToApplyIconTint;
    private MenuItem$OnActionExpandListener mOnActionExpandListener;
    private final int mOrdering;
    private char mShortcutAlphabeticChar;
    private int mShortcutAlphabeticModifiers;
    private char mShortcutNumericChar;
    private int mShortcutNumericModifiers;
    private int mShowAsAction;
    private SubMenuBuilder mSubMenu;
    private CharSequence mTitle;
    private CharSequence mTitleCondensed;
    private CharSequence mTooltipText;
    private static String sDeleteShortcutLabel;
    private static String sEnterShortcutLabel;
    private static String sPrependShortcutLabel;
    private static String sSpaceShortcutLabel;

    MenuItemImpl(MenuBuilder arg3, int arg4, int arg5, int arg6, int arg7, CharSequence arg8, int arg9) {
        super();
        this.mShortcutNumericModifiers = 0x1000;
        this.mShortcutAlphabeticModifiers = 0x1000;
        this.mIconResId = 0;
        this.mIconTintList = null;
        this.mIconTintMode = null;
        this.mHasIconTint = false;
        this.mHasIconTintMode = false;
        this.mNeedToApplyIconTint = false;
        this.mFlags = 16;
        this.mShowAsAction = 0;
        this.mIsActionViewExpanded = false;
        this.mMenu = arg3;
        this.mId = arg5;
        this.mGroup = arg4;
        this.mCategoryOrder = arg6;
        this.mOrdering = arg7;
        this.mTitle = arg8;
        this.mShowAsAction = arg9;
    }

    public void actionFormatChanged() {
        this.mMenu.onItemActionRequestChanged(this);
    }

    private Drawable applyIconTintIfNecessary(Drawable arg2) {
        if(arg2 != null && (this.mNeedToApplyIconTint) && ((this.mHasIconTint) || (this.mHasIconTintMode))) {
            arg2 = DrawableCompat.wrap(arg2).mutate();
            if(this.mHasIconTint) {
                DrawableCompat.setTintList(arg2, this.mIconTintList);
            }

            if(this.mHasIconTintMode) {
                DrawableCompat.setTintMode(arg2, this.mIconTintMode);
            }

            this.mNeedToApplyIconTint = false;
        }

        return arg2;
    }

    public boolean collapseActionView() {
        if((this.mShowAsAction & 8) == 0) {
            return 0;
        }

        if(this.mActionView == null) {
            return 1;
        }

        if(this.mOnActionExpandListener != null) {
            if(this.mOnActionExpandListener.onMenuItemActionCollapse(((MenuItem)this))) {
            }
            else {
                return 0;
            }
        }

        return this.mMenu.collapseItemActionView(this);
    }

    public boolean expandActionView() {
        if(!this.hasCollapsibleActionView()) {
            return 0;
        }

        if(this.mOnActionExpandListener != null) {
            if(this.mOnActionExpandListener.onMenuItemActionExpand(((MenuItem)this))) {
            }
            else {
                return 0;
            }
        }

        return this.mMenu.expandItemActionView(this);
    }

    public android.view.ActionProvider getActionProvider() {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.getActionProvider()");
    }

    public View getActionView() {
        if(this.mActionView != null) {
            return this.mActionView;
        }

        if(this.mActionProvider != null) {
            this.mActionView = this.mActionProvider.onCreateActionView(((MenuItem)this));
            return this.mActionView;
        }

        return null;
    }

    public int getAlphabeticModifiers() {
        return this.mShortcutAlphabeticModifiers;
    }

    public char getAlphabeticShortcut() {
        return this.mShortcutAlphabeticChar;
    }

    Runnable getCallback() {
        return this.mItemCallback;
    }

    public CharSequence getContentDescription() {
        return this.mContentDescription;
    }

    public int getGroupId() {
        return this.mGroup;
    }

    public Drawable getIcon() {
        if(this.mIconDrawable != null) {
            return this.applyIconTintIfNecessary(this.mIconDrawable);
        }

        if(this.mIconResId != 0) {
            Drawable v0 = AppCompatResources.getDrawable(this.mMenu.getContext(), this.mIconResId);
            this.mIconResId = 0;
            this.mIconDrawable = v0;
            return this.applyIconTintIfNecessary(v0);
        }

        return null;
    }

    public ColorStateList getIconTintList() {
        return this.mIconTintList;
    }

    public PorterDuff$Mode getIconTintMode() {
        return this.mIconTintMode;
    }

    public Intent getIntent() {
        return this.mIntent;
    }

    @ViewDebug$CapturedViewProperty public int getItemId() {
        return this.mId;
    }

    public ContextMenu$ContextMenuInfo getMenuInfo() {
        return this.mMenuInfo;
    }

    public int getNumericModifiers() {
        return this.mShortcutNumericModifiers;
    }

    public char getNumericShortcut() {
        return this.mShortcutNumericChar;
    }

    public int getOrder() {
        return this.mCategoryOrder;
    }

    public int getOrdering() {
        return this.mOrdering;
    }

    char getShortcut() {
        char v0 = this.mMenu.isQwertyMode() ? this.mShortcutAlphabeticChar : this.mShortcutNumericChar;
        return v0;
    }

    String getShortcutLabel() {
        char v0 = this.getShortcut();
        if(v0 == 0) {
            return "";
        }

        StringBuilder v1 = new StringBuilder(MenuItemImpl.sPrependShortcutLabel);
        if(v0 == 8) {
            v1.append(MenuItemImpl.sDeleteShortcutLabel);
        }
        else if(v0 == 10) {
            v1.append(MenuItemImpl.sEnterShortcutLabel);
        }
        else if(v0 != 0x20) {
            v1.append(v0);
        }
        else {
            v1.append(MenuItemImpl.sSpaceShortcutLabel);
        }

        return v1.toString();
    }

    public SubMenu getSubMenu() {
        return this.mSubMenu;
    }

    public ActionProvider getSupportActionProvider() {
        return this.mActionProvider;
    }

    @ViewDebug$CapturedViewProperty public CharSequence getTitle() {
        return this.mTitle;
    }

    public CharSequence getTitleCondensed() {
        CharSequence v0 = this.mTitleCondensed != null ? this.mTitleCondensed : this.mTitle;
        if(Build$VERSION.SDK_INT < 18 && v0 != null && !(v0 instanceof String)) {
            return v0.toString();
        }

        return v0;
    }

    CharSequence getTitleForItemView(ItemView arg1) {
        CharSequence v1 = arg1 == null || !arg1.prefersCondensedTitle() ? this.getTitle() : this.getTitleCondensed();
        return v1;
    }

    public CharSequence getTooltipText() {
        return this.mTooltipText;
    }

    public boolean hasCollapsibleActionView() {
        boolean v1 = false;
        if((this.mShowAsAction & 8) != 0) {
            if(this.mActionView == null && this.mActionProvider != null) {
                this.mActionView = this.mActionProvider.onCreateActionView(((MenuItem)this));
            }

            if(this.mActionView != null) {
                v1 = true;
            }

            return v1;
        }

        return 0;
    }

    public boolean hasSubMenu() {
        boolean v0 = this.mSubMenu != null ? true : false;
        return v0;
    }

    public boolean invoke() {
        if(this.mClickListener != null && (this.mClickListener.onMenuItemClick(((MenuItem)this)))) {
            return 1;
        }

        if(this.mMenu.dispatchMenuItemSelected(this.mMenu, ((MenuItem)this))) {
            return 1;
        }

        if(this.mItemCallback != null) {
            this.mItemCallback.run();
            return 1;
        }

        if(this.mIntent != null) {
            try {
                this.mMenu.getContext().startActivity(this.mIntent);
                return 1;
            }
            catch(ActivityNotFoundException v0) {
                Log.e("MenuItemImpl", "Can\'t find activity to handle intent; ignoring", ((Throwable)v0));
            }
        }

        if(this.mActionProvider != null && (this.mActionProvider.onPerformDefaultAction())) {
            return 1;
        }

        return 0;
    }

    public boolean isActionButton() {
        boolean v0 = (this.mFlags & 0x20) == 0x20 ? true : false;
        return v0;
    }

    public boolean isActionViewExpanded() {
        return this.mIsActionViewExpanded;
    }

    public boolean isCheckable() {
        boolean v1 = true;
        if((this.mFlags & 1) == 1) {
        }
        else {
            v1 = false;
        }

        return v1;
    }

    public boolean isChecked() {
        boolean v0 = (this.mFlags & 2) == 2 ? true : false;
        return v0;
    }

    public boolean isEnabled() {
        boolean v0 = (this.mFlags & 16) != 0 ? true : false;
        return v0;
    }

    public boolean isExclusiveCheckable() {
        boolean v0 = (this.mFlags & 4) != 0 ? true : false;
        return v0;
    }

    public boolean isVisible() {
        boolean v1 = false;
        if(this.mActionProvider != null && (this.mActionProvider.overridesItemVisibility())) {
            if((this.mFlags & 8) == 0 && (this.mActionProvider.isVisible())) {
                v1 = true;
            }

            return v1;
        }

        if((this.mFlags & 8) == 0) {
            v1 = true;
        }

        return v1;
    }

    public boolean requestsActionButton() {
        boolean v1 = true;
        if((this.mShowAsAction & 1) == 1) {
        }
        else {
            v1 = false;
        }

        return v1;
    }

    public boolean requiresActionButton() {
        boolean v0 = (this.mShowAsAction & 2) == 2 ? true : false;
        return v0;
    }

    public MenuItem setActionProvider(android.view.ActionProvider arg2) {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.setActionProvider()");
    }

    public SupportMenuItem setActionView(int arg4) {
        Context v0 = this.mMenu.getContext();
        this.setActionView(LayoutInflater.from(v0).inflate(arg4, new LinearLayout(v0), false));
        return this;
    }

    public SupportMenuItem setActionView(View arg3) {
        this.mActionView = arg3;
        this.mActionProvider = null;
        if(arg3 != null && arg3.getId() == -1 && this.mId > 0) {
            arg3.setId(this.mId);
        }

        this.mMenu.onItemActionRequestChanged(this);
        return this;
    }

    public MenuItem setActionView(int arg1) {
        return this.setActionView(arg1);
    }

    public MenuItem setActionView(View arg1) {
        return this.setActionView(arg1);
    }

    public void setActionViewExpanded(boolean arg2) {
        this.mIsActionViewExpanded = arg2;
        this.mMenu.onItemsChanged(false);
    }

    public MenuItem setAlphabeticShortcut(char arg2) {
        if(this.mShortcutAlphabeticChar == arg2) {
            return this;
        }

        this.mShortcutAlphabeticChar = Character.toLowerCase(arg2);
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setAlphabeticShortcut(char arg2, int arg3) {
        if(this.mShortcutAlphabeticChar == arg2 && this.mShortcutAlphabeticModifiers == arg3) {
            return this;
        }

        this.mShortcutAlphabeticChar = Character.toLowerCase(arg2);
        this.mShortcutAlphabeticModifiers = KeyEvent.normalizeMetaState(arg3);
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setCallback(Runnable arg1) {
        this.mItemCallback = arg1;
        return this;
    }

    public MenuItem setCheckable(boolean arg3) {
        int v0 = this.mFlags;
        this.mFlags = (((int)arg3)) | this.mFlags & -2;
        if(v0 != this.mFlags) {
            this.mMenu.onItemsChanged(false);
        }

        return this;
    }

    public MenuItem setChecked(boolean arg2) {
        if((this.mFlags & 4) != 0) {
            this.mMenu.setExclusiveItemChecked(((MenuItem)this));
        }
        else {
            this.setCheckedInt(arg2);
        }

        return this;
    }

    void setCheckedInt(boolean arg4) {
        int v0 = this.mFlags;
        int v1 = this.mFlags & -3;
        int v4 = arg4 ? 2 : 0;
        this.mFlags = v4 | v1;
        if(v0 != this.mFlags) {
            this.mMenu.onItemsChanged(false);
        }
    }

    public SupportMenuItem setContentDescription(CharSequence arg2) {
        this.mContentDescription = arg2;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setContentDescription(CharSequence arg1) {
        return this.setContentDescription(arg1);
    }

    public MenuItem setEnabled(boolean arg2) {
        if(arg2) {
            this.mFlags |= 16;
        }
        else {
            this.mFlags &= -17;
        }

        this.mMenu.onItemsChanged(false);
        return this;
    }

    public void setExclusiveCheckable(boolean arg2) {
        int v0 = this.mFlags & -5;
        int v2 = arg2 ? 4 : 0;
        this.mFlags = v2 | v0;
    }

    public MenuItem setIcon(int arg2) {
        this.mIconDrawable = null;
        this.mIconResId = arg2;
        this.mNeedToApplyIconTint = true;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setIcon(Drawable arg2) {
        this.mIconResId = 0;
        this.mIconDrawable = arg2;
        this.mNeedToApplyIconTint = true;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setIconTintList(@Nullable ColorStateList arg2) {
        this.mIconTintList = arg2;
        this.mHasIconTint = true;
        this.mNeedToApplyIconTint = true;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setIconTintMode(PorterDuff$Mode arg2) {
        this.mIconTintMode = arg2;
        this.mHasIconTintMode = true;
        this.mNeedToApplyIconTint = true;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setIntent(Intent arg1) {
        this.mIntent = arg1;
        return this;
    }

    public void setIsActionButton(boolean arg1) {
        if(arg1) {
            this.mFlags |= 0x20;
        }
        else {
            this.mFlags &= -33;
        }
    }

    void setMenuInfo(ContextMenu$ContextMenuInfo arg1) {
        this.mMenuInfo = arg1;
    }

    public MenuItem setNumericShortcut(char arg2) {
        if(this.mShortcutNumericChar == arg2) {
            return this;
        }

        this.mShortcutNumericChar = arg2;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setNumericShortcut(char arg2, int arg3) {
        if(this.mShortcutNumericChar == arg2 && this.mShortcutNumericModifiers == arg3) {
            return this;
        }

        this.mShortcutNumericChar = arg2;
        this.mShortcutNumericModifiers = KeyEvent.normalizeMetaState(arg3);
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setOnActionExpandListener(MenuItem$OnActionExpandListener arg1) {
        this.mOnActionExpandListener = arg1;
        return this;
    }

    public MenuItem setOnMenuItemClickListener(MenuItem$OnMenuItemClickListener arg1) {
        this.mClickListener = arg1;
        return this;
    }

    public MenuItem setShortcut(char arg1, char arg2) {
        this.mShortcutNumericChar = arg1;
        this.mShortcutAlphabeticChar = Character.toLowerCase(arg2);
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setShortcut(char arg1, char arg2, int arg3, int arg4) {
        this.mShortcutNumericChar = arg1;
        this.mShortcutNumericModifiers = KeyEvent.normalizeMetaState(arg3);
        this.mShortcutAlphabeticChar = Character.toLowerCase(arg2);
        this.mShortcutAlphabeticModifiers = KeyEvent.normalizeMetaState(arg4);
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public void setShowAsAction(int arg2) {
        switch(arg2 & 3) {
            case 0: 
            case 1: 
            case 2: {
                goto label_6;
            }
        }

        throw new IllegalArgumentException("SHOW_AS_ACTION_ALWAYS, SHOW_AS_ACTION_IF_ROOM, and SHOW_AS_ACTION_NEVER are mutually exclusive.");
    label_6:
        this.mShowAsAction = arg2;
        this.mMenu.onItemActionRequestChanged(this);
    }

    public SupportMenuItem setShowAsActionFlags(int arg1) {
        this.setShowAsAction(arg1);
        return this;
    }

    public MenuItem setShowAsActionFlags(int arg1) {
        return this.setShowAsActionFlags(arg1);
    }

    public void setSubMenu(SubMenuBuilder arg2) {
        this.mSubMenu = arg2;
        arg2.setHeaderTitle(this.getTitle());
    }

    public SupportMenuItem setSupportActionProvider(ActionProvider arg2) {
        if(this.mActionProvider != null) {
            this.mActionProvider.reset();
        }

        this.mActionView = null;
        this.mActionProvider = arg2;
        this.mMenu.onItemsChanged(true);
        if(this.mActionProvider != null) {
            this.mActionProvider.setVisibilityListener(new VisibilityListener() {
                public void onActionProviderVisibilityChanged(boolean arg2) {
                    MenuItemImpl.this.mMenu.onItemVisibleChanged(MenuItemImpl.this);
                }
            });
        }

        return this;
    }

    public MenuItem setTitle(int arg2) {
        return this.setTitle(this.mMenu.getContext().getString(arg2));
    }

    public MenuItem setTitle(CharSequence arg3) {
        this.mTitle = arg3;
        this.mMenu.onItemsChanged(false);
        if(this.mSubMenu != null) {
            this.mSubMenu.setHeaderTitle(arg3);
        }

        return this;
    }

    public MenuItem setTitleCondensed(CharSequence arg2) {
        this.mTitleCondensed = arg2;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public SupportMenuItem setTooltipText(CharSequence arg2) {
        this.mTooltipText = arg2;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setTooltipText(CharSequence arg1) {
        return this.setTooltipText(arg1);
    }

    public MenuItem setVisible(boolean arg1) {
        if(this.setVisibleInt(arg1)) {
            this.mMenu.onItemVisibleChanged(this);
        }

        return this;
    }

    boolean setVisibleInt(boolean arg4) {
        int v0 = this.mFlags;
        int v1 = this.mFlags & -9;
        boolean v2 = false;
        int v4 = arg4 ? 0 : 8;
        this.mFlags = v4 | v1;
        if(v0 != this.mFlags) {
            v2 = true;
        }

        return v2;
    }

    public boolean shouldShowIcon() {
        return this.mMenu.getOptionalIconsVisible();
    }

    boolean shouldShowShortcut() {
        boolean v0 = !this.mMenu.isShortcutsVisible() || this.getShortcut() == 0 ? false : true;
        return v0;
    }

    public boolean showsTextAsAction() {
        boolean v0 = (this.mShowAsAction & 4) == 4 ? true : false;
        return v0;
    }

    public String toString() {
        String v0 = this.mTitle != null ? this.mTitle.toString() : null;
        return v0;
    }
}

