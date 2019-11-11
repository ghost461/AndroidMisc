package android.support.v7.widget;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.v7.view.menu.MenuBuilder;
import android.view.MenuItem;

@RestrictTo(value={Scope.LIBRARY_GROUP}) public interface MenuItemHoverListener {
    void onItemHoverEnter(@NonNull MenuBuilder arg1, @NonNull MenuItem arg2);

    void onItemHoverExit(@NonNull MenuBuilder arg1, @NonNull MenuItem arg2);
}

