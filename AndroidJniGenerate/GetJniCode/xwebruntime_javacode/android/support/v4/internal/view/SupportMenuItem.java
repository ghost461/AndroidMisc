package android.support.v4.internal.view;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff$Mode;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.v4.view.ActionProvider;
import android.view.MenuItem;
import android.view.View;

@RestrictTo(value={Scope.LIBRARY_GROUP}) public interface SupportMenuItem extends MenuItem {
    public static final int SHOW_AS_ACTION_ALWAYS = 2;
    public static final int SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW = 8;
    public static final int SHOW_AS_ACTION_IF_ROOM = 1;
    public static final int SHOW_AS_ACTION_NEVER = 0;
    public static final int SHOW_AS_ACTION_WITH_TEXT = 4;

    boolean collapseActionView();

    boolean expandActionView();

    View getActionView();

    int getAlphabeticModifiers();

    CharSequence getContentDescription();

    ColorStateList getIconTintList();

    PorterDuff$Mode getIconTintMode();

    int getNumericModifiers();

    ActionProvider getSupportActionProvider();

    CharSequence getTooltipText();

    boolean isActionViewExpanded();

    MenuItem setActionView(int arg1);

    MenuItem setActionView(View arg1);

    MenuItem setAlphabeticShortcut(char arg1, int arg2);

    SupportMenuItem setContentDescription(CharSequence arg1);

    MenuItem setIconTintList(ColorStateList arg1);

    MenuItem setIconTintMode(PorterDuff$Mode arg1);

    MenuItem setNumericShortcut(char arg1, int arg2);

    MenuItem setShortcut(char arg1, char arg2, int arg3, int arg4);

    void setShowAsAction(int arg1);

    MenuItem setShowAsActionFlags(int arg1);

    SupportMenuItem setSupportActionProvider(ActionProvider arg1);

    SupportMenuItem setTooltipText(CharSequence arg1);
}

