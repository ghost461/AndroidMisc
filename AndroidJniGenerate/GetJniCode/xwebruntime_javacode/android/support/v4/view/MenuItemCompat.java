package android.support.v4.view;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff$Mode;
import android.os.Build$VERSION;
import android.support.annotation.RequiresApi;
import android.support.v4.internal.view.SupportMenuItem;
import android.util.Log;
import android.view.MenuItem$OnActionExpandListener;
import android.view.MenuItem;
import android.view.View;

public final class MenuItemCompat {
    @RequiresApi(value=26) class MenuItemCompatApi26Impl extends MenuItemCompatBaseImpl {
        MenuItemCompatApi26Impl() {
            super();
        }

        public int getAlphabeticModifiers(MenuItem arg1) {
            return arg1.getAlphabeticModifiers();
        }

        public CharSequence getContentDescription(MenuItem arg1) {
            return arg1.getContentDescription();
        }

        public ColorStateList getIconTintList(MenuItem arg1) {
            return arg1.getIconTintList();
        }

        public PorterDuff$Mode getIconTintMode(MenuItem arg1) {
            return arg1.getIconTintMode();
        }

        public int getNumericModifiers(MenuItem arg1) {
            return arg1.getNumericModifiers();
        }

        public CharSequence getTooltipText(MenuItem arg1) {
            return arg1.getTooltipText();
        }

        public void setAlphabeticShortcut(MenuItem arg1, char arg2, int arg3) {
            arg1.setAlphabeticShortcut(arg2, arg3);
        }

        public void setContentDescription(MenuItem arg1, CharSequence arg2) {
            arg1.setContentDescription(arg2);
        }

        public void setIconTintList(MenuItem arg1, ColorStateList arg2) {
            arg1.setIconTintList(arg2);
        }

        public void setIconTintMode(MenuItem arg1, PorterDuff$Mode arg2) {
            arg1.setIconTintMode(arg2);
        }

        public void setNumericShortcut(MenuItem arg1, char arg2, int arg3) {
            arg1.setNumericShortcut(arg2, arg3);
        }

        public void setShortcut(MenuItem arg1, char arg2, char arg3, int arg4, int arg5) {
            arg1.setShortcut(arg2, arg3, arg4, arg5);
        }

        public void setTooltipText(MenuItem arg1, CharSequence arg2) {
            arg1.setTooltipText(arg2);
        }
    }

    class MenuItemCompatBaseImpl implements MenuVersionImpl {
        MenuItemCompatBaseImpl() {
            super();
        }

        public int getAlphabeticModifiers(MenuItem arg1) {
            return 0;
        }

        public CharSequence getContentDescription(MenuItem arg1) {
            return null;
        }

        public ColorStateList getIconTintList(MenuItem arg1) {
            return null;
        }

        public PorterDuff$Mode getIconTintMode(MenuItem arg1) {
            return null;
        }

        public int getNumericModifiers(MenuItem arg1) {
            return 0;
        }

        public CharSequence getTooltipText(MenuItem arg1) {
            return null;
        }

        public void setAlphabeticShortcut(MenuItem arg1, char arg2, int arg3) {
        }

        public void setContentDescription(MenuItem arg1, CharSequence arg2) {
        }

        public void setIconTintList(MenuItem arg1, ColorStateList arg2) {
        }

        public void setIconTintMode(MenuItem arg1, PorterDuff$Mode arg2) {
        }

        public void setNumericShortcut(MenuItem arg1, char arg2, int arg3) {
        }

        public void setShortcut(MenuItem arg1, char arg2, char arg3, int arg4, int arg5) {
        }

        public void setTooltipText(MenuItem arg1, CharSequence arg2) {
        }
    }

    interface MenuVersionImpl {
        int getAlphabeticModifiers(MenuItem arg1);

        CharSequence getContentDescription(MenuItem arg1);

        ColorStateList getIconTintList(MenuItem arg1);

        PorterDuff$Mode getIconTintMode(MenuItem arg1);

        int getNumericModifiers(MenuItem arg1);

        CharSequence getTooltipText(MenuItem arg1);

        void setAlphabeticShortcut(MenuItem arg1, char arg2, int arg3);

        void setContentDescription(MenuItem arg1, CharSequence arg2);

        void setIconTintList(MenuItem arg1, ColorStateList arg2);

        void setIconTintMode(MenuItem arg1, PorterDuff$Mode arg2);

        void setNumericShortcut(MenuItem arg1, char arg2, int arg3);

        void setShortcut(MenuItem arg1, char arg2, char arg3, int arg4, int arg5);

        void setTooltipText(MenuItem arg1, CharSequence arg2);
    }

    @Deprecated public interface OnActionExpandListener {
        boolean onMenuItemActionCollapse(MenuItem arg1);

        boolean onMenuItemActionExpand(MenuItem arg1);
    }

    static final MenuVersionImpl IMPL = null;
    @Deprecated public static final int SHOW_AS_ACTION_ALWAYS = 2;
    @Deprecated public static final int SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW = 8;
    @Deprecated public static final int SHOW_AS_ACTION_IF_ROOM = 1;
    @Deprecated public static final int SHOW_AS_ACTION_NEVER = 0;
    @Deprecated public static final int SHOW_AS_ACTION_WITH_TEXT = 4;
    private static final String TAG = "MenuItemCompat";

    static {
        MenuItemCompat.IMPL = Build$VERSION.SDK_INT >= 26 ? new MenuItemCompatApi26Impl() : new MenuItemCompatBaseImpl();
    }

    private MenuItemCompat() {
        super();
    }

    @Deprecated public static boolean collapseActionView(MenuItem arg0) {
        return arg0.collapseActionView();
    }

    @Deprecated public static boolean expandActionView(MenuItem arg0) {
        return arg0.expandActionView();
    }

    public static ActionProvider getActionProvider(MenuItem arg1) {
        if((arg1 instanceof SupportMenuItem)) {
            return ((SupportMenuItem)arg1).getSupportActionProvider();
        }

        Log.w("MenuItemCompat", "getActionProvider: item does not implement SupportMenuItem; returning null");
        return null;
    }

    @Deprecated public static View getActionView(MenuItem arg0) {
        return arg0.getActionView();
    }

    public static int getAlphabeticModifiers(MenuItem arg1) {
        if((arg1 instanceof SupportMenuItem)) {
            return ((SupportMenuItem)arg1).getAlphabeticModifiers();
        }

        return MenuItemCompat.IMPL.getAlphabeticModifiers(arg1);
    }

    public static CharSequence getContentDescription(MenuItem arg1) {
        if((arg1 instanceof SupportMenuItem)) {
            return ((SupportMenuItem)arg1).getContentDescription();
        }

        return MenuItemCompat.IMPL.getContentDescription(arg1);
    }

    public static ColorStateList getIconTintList(MenuItem arg1) {
        if((arg1 instanceof SupportMenuItem)) {
            return ((SupportMenuItem)arg1).getIconTintList();
        }

        return MenuItemCompat.IMPL.getIconTintList(arg1);
    }

    public static PorterDuff$Mode getIconTintMode(MenuItem arg1) {
        if((arg1 instanceof SupportMenuItem)) {
            return ((SupportMenuItem)arg1).getIconTintMode();
        }

        return MenuItemCompat.IMPL.getIconTintMode(arg1);
    }

    public static int getNumericModifiers(MenuItem arg1) {
        if((arg1 instanceof SupportMenuItem)) {
            return ((SupportMenuItem)arg1).getNumericModifiers();
        }

        return MenuItemCompat.IMPL.getNumericModifiers(arg1);
    }

    public static CharSequence getTooltipText(MenuItem arg1) {
        if((arg1 instanceof SupportMenuItem)) {
            return ((SupportMenuItem)arg1).getTooltipText();
        }

        return MenuItemCompat.IMPL.getTooltipText(arg1);
    }

    @Deprecated public static boolean isActionViewExpanded(MenuItem arg0) {
        return arg0.isActionViewExpanded();
    }

    public static MenuItem setActionProvider(MenuItem arg1, ActionProvider arg2) {
        if((arg1 instanceof SupportMenuItem)) {
            return ((SupportMenuItem)arg1).setSupportActionProvider(arg2);
        }

        Log.w("MenuItemCompat", "setActionProvider: item does not implement SupportMenuItem; ignoring");
        return arg1;
    }

    @Deprecated public static MenuItem setActionView(MenuItem arg0, int arg1) {
        return arg0.setActionView(arg1);
    }

    @Deprecated public static MenuItem setActionView(MenuItem arg0, View arg1) {
        return arg0.setActionView(arg1);
    }

    public static void setAlphabeticShortcut(MenuItem arg1, char arg2, int arg3) {
        if((arg1 instanceof SupportMenuItem)) {
            ((SupportMenuItem)arg1).setAlphabeticShortcut(arg2, arg3);
        }
        else {
            MenuItemCompat.IMPL.setAlphabeticShortcut(arg1, arg2, arg3);
        }
    }

    public static void setContentDescription(MenuItem arg1, CharSequence arg2) {
        if((arg1 instanceof SupportMenuItem)) {
            ((SupportMenuItem)arg1).setContentDescription(arg2);
        }
        else {
            MenuItemCompat.IMPL.setContentDescription(arg1, arg2);
        }
    }

    public static void setIconTintList(MenuItem arg1, ColorStateList arg2) {
        if((arg1 instanceof SupportMenuItem)) {
            ((SupportMenuItem)arg1).setIconTintList(arg2);
        }
        else {
            MenuItemCompat.IMPL.setIconTintList(arg1, arg2);
        }
    }

    public static void setIconTintMode(MenuItem arg1, PorterDuff$Mode arg2) {
        if((arg1 instanceof SupportMenuItem)) {
            ((SupportMenuItem)arg1).setIconTintMode(arg2);
        }
        else {
            MenuItemCompat.IMPL.setIconTintMode(arg1, arg2);
        }
    }

    public static void setNumericShortcut(MenuItem arg1, char arg2, int arg3) {
        if((arg1 instanceof SupportMenuItem)) {
            ((SupportMenuItem)arg1).setNumericShortcut(arg2, arg3);
        }
        else {
            MenuItemCompat.IMPL.setNumericShortcut(arg1, arg2, arg3);
        }
    }

    @Deprecated public static MenuItem setOnActionExpandListener(MenuItem arg1, OnActionExpandListener arg2) {
        return arg1.setOnActionExpandListener(new MenuItem$OnActionExpandListener(arg2) {
            public boolean onMenuItemActionCollapse(MenuItem arg2) {
                return this.val$listener.onMenuItemActionCollapse(arg2);
            }

            public boolean onMenuItemActionExpand(MenuItem arg2) {
                return this.val$listener.onMenuItemActionExpand(arg2);
            }
        });
    }

    public static void setShortcut(MenuItem arg6, char arg7, char arg8, int arg9, int arg10) {
        if((arg6 instanceof SupportMenuItem)) {
            ((SupportMenuItem)arg6).setShortcut(arg7, arg8, arg9, arg10);
        }
        else {
            MenuItemCompat.IMPL.setShortcut(arg6, arg7, arg8, arg9, arg10);
        }
    }

    @Deprecated public static void setShowAsAction(MenuItem arg0, int arg1) {
        arg0.setShowAsAction(arg1);
    }

    public static void setTooltipText(MenuItem arg1, CharSequence arg2) {
        if((arg1 instanceof SupportMenuItem)) {
            ((SupportMenuItem)arg1).setTooltipText(arg2);
        }
        else {
            MenuItemCompat.IMPL.setTooltipText(arg1, arg2);
        }
    }
}

