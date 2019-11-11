package android.support.v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v7.view.menu.MenuPresenter$Callback;
import android.util.SparseArray;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window$Callback;
import android.widget.AdapterView$OnItemSelectedListener;
import android.widget.SpinnerAdapter;

@RestrictTo(value={Scope.LIBRARY_GROUP}) public interface DecorToolbar {
    void animateToVisibility(int arg1);

    boolean canShowOverflowMenu();

    void collapseActionView();

    void dismissPopupMenus();

    Context getContext();

    View getCustomView();

    int getDisplayOptions();

    int getDropdownItemCount();

    int getDropdownSelectedPosition();

    int getHeight();

    Menu getMenu();

    int getNavigationMode();

    CharSequence getSubtitle();

    CharSequence getTitle();

    ViewGroup getViewGroup();

    int getVisibility();

    boolean hasEmbeddedTabs();

    boolean hasExpandedActionView();

    boolean hasIcon();

    boolean hasLogo();

    boolean hideOverflowMenu();

    void initIndeterminateProgress();

    void initProgress();

    boolean isOverflowMenuShowPending();

    boolean isOverflowMenuShowing();

    boolean isTitleTruncated();

    void restoreHierarchyState(SparseArray arg1);

    void saveHierarchyState(SparseArray arg1);

    void setBackgroundDrawable(Drawable arg1);

    void setCollapsible(boolean arg1);

    void setCustomView(View arg1);

    void setDefaultNavigationContentDescription(int arg1);

    void setDefaultNavigationIcon(Drawable arg1);

    void setDisplayOptions(int arg1);

    void setDropdownParams(SpinnerAdapter arg1, AdapterView$OnItemSelectedListener arg2);

    void setDropdownSelectedPosition(int arg1);

    void setEmbeddedTabView(ScrollingTabContainerView arg1);

    void setHomeButtonEnabled(boolean arg1);

    void setIcon(int arg1);

    void setIcon(Drawable arg1);

    void setLogo(int arg1);

    void setLogo(Drawable arg1);

    void setMenu(Menu arg1, Callback arg2);

    void setMenuCallbacks(Callback arg1, android.support.v7.view.menu.MenuBuilder$Callback arg2);

    void setMenuPrepared();

    void setNavigationContentDescription(int arg1);

    void setNavigationContentDescription(CharSequence arg1);

    void setNavigationIcon(int arg1);

    void setNavigationIcon(Drawable arg1);

    void setNavigationMode(int arg1);

    void setSubtitle(CharSequence arg1);

    void setTitle(CharSequence arg1);

    void setVisibility(int arg1);

    void setWindowCallback(Window$Callback arg1);

    void setWindowTitle(CharSequence arg1);

    ViewPropertyAnimatorCompat setupAnimatorToVisibility(int arg1, long arg2);

    boolean showOverflowMenu();
}

