package android.support.v7.view.menu;

import android.graphics.drawable.Drawable;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;

@RestrictTo(value={Scope.LIBRARY_GROUP}) public interface MenuView {
    public interface ItemView {
        MenuItemImpl getItemData();

        void initialize(MenuItemImpl arg1, int arg2);

        boolean prefersCondensedTitle();

        void setCheckable(boolean arg1);

        void setChecked(boolean arg1);

        void setEnabled(boolean arg1);

        void setIcon(Drawable arg1);

        void setShortcut(boolean arg1, char arg2);

        void setTitle(CharSequence arg1);

        boolean showsIcon();
    }

    int getWindowAnimations();

    void initialize(MenuBuilder arg1);
}

