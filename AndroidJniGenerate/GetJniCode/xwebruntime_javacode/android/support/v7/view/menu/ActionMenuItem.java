package android.support.v7.view.menu;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff$Mode;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.internal.view.SupportMenuItem;
import android.view.ActionProvider;
import android.view.ContextMenu$ContextMenuInfo;
import android.view.KeyEvent;
import android.view.MenuItem$OnActionExpandListener;
import android.view.MenuItem$OnMenuItemClickListener;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

@RestrictTo(value={Scope.LIBRARY_GROUP}) public class ActionMenuItem implements SupportMenuItem {
    private static final int CHECKABLE = 1;
    private static final int CHECKED = 2;
    private static final int ENABLED = 16;
    private static final int EXCLUSIVE = 4;
    private static final int HIDDEN = 8;
    private static final int NO_ICON;
    private final int mCategoryOrder;
    private MenuItem$OnMenuItemClickListener mClickListener;
    private CharSequence mContentDescription;
    private Context mContext;
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
    private final int mOrdering;
    private char mShortcutAlphabeticChar;
    private int mShortcutAlphabeticModifiers;
    private char mShortcutNumericChar;
    private int mShortcutNumericModifiers;
    private CharSequence mTitle;
    private CharSequence mTitleCondensed;
    private CharSequence mTooltipText;

    public ActionMenuItem(Context arg3, int arg4, int arg5, int arg6, int arg7, CharSequence arg8) {
        super();
        this.mShortcutNumericModifiers = 0x1000;
        this.mShortcutAlphabeticModifiers = 0x1000;
        this.mIconResId = 0;
        this.mIconTintList = null;
        this.mIconTintMode = null;
        this.mHasIconTint = false;
        this.mHasIconTintMode = false;
        this.mFlags = 16;
        this.mContext = arg3;
        this.mId = arg5;
        this.mGroup = arg4;
        this.mCategoryOrder = arg6;
        this.mOrdering = arg7;
        this.mTitle = arg8;
    }

    private void applyIconTint() {
        if(this.mIconDrawable != null && ((this.mHasIconTint) || (this.mHasIconTintMode))) {
            this.mIconDrawable = DrawableCompat.wrap(this.mIconDrawable);
            this.mIconDrawable = this.mIconDrawable.mutate();
            if(this.mHasIconTint) {
                DrawableCompat.setTintList(this.mIconDrawable, this.mIconTintList);
            }

            if(!this.mHasIconTintMode) {
                return;
            }

            DrawableCompat.setTintMode(this.mIconDrawable, this.mIconTintMode);
        }
    }

    public boolean collapseActionView() {
        return 0;
    }

    public boolean expandActionView() {
        return 0;
    }

    public ActionProvider getActionProvider() {
        throw new UnsupportedOperationException();
    }

    public View getActionView() {
        return null;
    }

    public int getAlphabeticModifiers() {
        return this.mShortcutAlphabeticModifiers;
    }

    public char getAlphabeticShortcut() {
        return this.mShortcutAlphabeticChar;
    }

    public CharSequence getContentDescription() {
        return this.mContentDescription;
    }

    public int getGroupId() {
        return this.mGroup;
    }

    public Drawable getIcon() {
        return this.mIconDrawable;
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

    public int getItemId() {
        return this.mId;
    }

    public ContextMenu$ContextMenuInfo getMenuInfo() {
        return null;
    }

    public int getNumericModifiers() {
        return this.mShortcutNumericModifiers;
    }

    public char getNumericShortcut() {
        return this.mShortcutNumericChar;
    }

    public int getOrder() {
        return this.mOrdering;
    }

    public SubMenu getSubMenu() {
        return null;
    }

    public android.support.v4.view.ActionProvider getSupportActionProvider() {
        return null;
    }

    public CharSequence getTitle() {
        return this.mTitle;
    }

    public CharSequence getTitleCondensed() {
        CharSequence v0 = this.mTitleCondensed != null ? this.mTitleCondensed : this.mTitle;
        return v0;
    }

    public CharSequence getTooltipText() {
        return this.mTooltipText;
    }

    public boolean hasSubMenu() {
        return 0;
    }

    public boolean invoke() {
        if(this.mClickListener != null && (this.mClickListener.onMenuItemClick(((MenuItem)this)))) {
            return 1;
        }

        if(this.mIntent != null) {
            this.mContext.startActivity(this.mIntent);
            return 1;
        }

        return 0;
    }

    public boolean isActionViewExpanded() {
        return 0;
    }

    public boolean isCheckable() {
        boolean v1 = true;
        if((this.mFlags & 1) != 0) {
        }
        else {
            v1 = false;
        }

        return v1;
    }

    public boolean isChecked() {
        boolean v0 = (this.mFlags & 2) != 0 ? true : false;
        return v0;
    }

    public boolean isEnabled() {
        boolean v0 = (this.mFlags & 16) != 0 ? true : false;
        return v0;
    }

    public boolean isVisible() {
        boolean v0 = (this.mFlags & 8) == 0 ? true : false;
        return v0;
    }

    public MenuItem setActionProvider(ActionProvider arg1) {
        throw new UnsupportedOperationException();
    }

    public SupportMenuItem setActionView(int arg1) {
        throw new UnsupportedOperationException();
    }

    public SupportMenuItem setActionView(View arg1) {
        throw new UnsupportedOperationException();
    }

    public MenuItem setActionView(int arg1) {
        return this.setActionView(arg1);
    }

    public MenuItem setActionView(View arg1) {
        return this.setActionView(arg1);
    }

    public MenuItem setAlphabeticShortcut(char arg1) {
        this.mShortcutAlphabeticChar = Character.toLowerCase(arg1);
        return this;
    }

    public MenuItem setAlphabeticShortcut(char arg1, int arg2) {
        this.mShortcutAlphabeticChar = Character.toLowerCase(arg1);
        this.mShortcutAlphabeticModifiers = KeyEvent.normalizeMetaState(arg2);
        return this;
    }

    public MenuItem setCheckable(boolean arg2) {
        this.mFlags = (((int)arg2)) | this.mFlags & -2;
        return this;
    }

    public MenuItem setChecked(boolean arg2) {
        int v0 = this.mFlags & -3;
        int v2 = arg2 ? 2 : 0;
        this.mFlags = v2 | v0;
        return this;
    }

    public SupportMenuItem setContentDescription(CharSequence arg1) {
        this.mContentDescription = arg1;
        return this;
    }

    public MenuItem setContentDescription(CharSequence arg1) {
        return this.setContentDescription(arg1);
    }

    public MenuItem setEnabled(boolean arg2) {
        int v0 = this.mFlags & -17;
        int v2 = arg2 ? 16 : 0;
        this.mFlags = v2 | v0;
        return this;
    }

    public ActionMenuItem setExclusiveCheckable(boolean arg2) {
        int v0 = this.mFlags & -5;
        int v2 = arg2 ? 4 : 0;
        this.mFlags = v2 | v0;
        return this;
    }

    public MenuItem setIcon(int arg2) {
        this.mIconResId = arg2;
        this.mIconDrawable = ContextCompat.getDrawable(this.mContext, arg2);
        this.applyIconTint();
        return this;
    }

    public MenuItem setIcon(Drawable arg1) {
        this.mIconDrawable = arg1;
        this.mIconResId = 0;
        this.applyIconTint();
        return this;
    }

    public MenuItem setIconTintList(@Nullable ColorStateList arg1) {
        this.mIconTintList = arg1;
        this.mHasIconTint = true;
        this.applyIconTint();
        return this;
    }

    public MenuItem setIconTintMode(PorterDuff$Mode arg1) {
        this.mIconTintMode = arg1;
        this.mHasIconTintMode = true;
        this.applyIconTint();
        return this;
    }

    public MenuItem setIntent(Intent arg1) {
        this.mIntent = arg1;
        return this;
    }

    public MenuItem setNumericShortcut(char arg1) {
        this.mShortcutNumericChar = arg1;
        return this;
    }

    public MenuItem setNumericShortcut(char arg1, int arg2) {
        this.mShortcutNumericChar = arg1;
        this.mShortcutNumericModifiers = KeyEvent.normalizeMetaState(arg2);
        return this;
    }

    public MenuItem setOnActionExpandListener(MenuItem$OnActionExpandListener arg1) {
        throw new UnsupportedOperationException();
    }

    public MenuItem setOnMenuItemClickListener(MenuItem$OnMenuItemClickListener arg1) {
        this.mClickListener = arg1;
        return this;
    }

    public MenuItem setShortcut(char arg1, char arg2) {
        this.mShortcutNumericChar = arg1;
        this.mShortcutAlphabeticChar = Character.toLowerCase(arg2);
        return this;
    }

    public MenuItem setShortcut(char arg1, char arg2, int arg3, int arg4) {
        this.mShortcutNumericChar = arg1;
        this.mShortcutNumericModifiers = KeyEvent.normalizeMetaState(arg3);
        this.mShortcutAlphabeticChar = Character.toLowerCase(arg2);
        this.mShortcutAlphabeticModifiers = KeyEvent.normalizeMetaState(arg4);
        return this;
    }

    public void setShowAsAction(int arg1) {
    }

    public SupportMenuItem setShowAsActionFlags(int arg1) {
        this.setShowAsAction(arg1);
        return this;
    }

    public MenuItem setShowAsActionFlags(int arg1) {
        return this.setShowAsActionFlags(arg1);
    }

    public SupportMenuItem setSupportActionProvider(android.support.v4.view.ActionProvider arg1) {
        throw new UnsupportedOperationException();
    }

    public MenuItem setTitle(int arg2) {
        this.mTitle = this.mContext.getResources().getString(arg2);
        return this;
    }

    public MenuItem setTitle(CharSequence arg1) {
        this.mTitle = arg1;
        return this;
    }

    public MenuItem setTitleCondensed(CharSequence arg1) {
        this.mTitleCondensed = arg1;
        return this;
    }

    public SupportMenuItem setTooltipText(CharSequence arg1) {
        this.mTooltipText = arg1;
        return this;
    }

    public MenuItem setTooltipText(CharSequence arg1) {
        return this.setTooltipText(arg1);
    }

    public MenuItem setVisible(boolean arg3) {
        int v1 = 8;
        int v0 = this.mFlags & v1;
        if(arg3) {
            v1 = 0;
        }

        this.mFlags = v0 | v1;
        return this;
    }
}

