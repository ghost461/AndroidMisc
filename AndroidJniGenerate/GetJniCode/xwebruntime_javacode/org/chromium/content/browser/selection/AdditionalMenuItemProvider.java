package org.chromium.content.browser.selection;

import android.content.Context;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.textclassifier.TextClassification;

public interface AdditionalMenuItemProvider {
    void addMenuItems(Context arg1, Menu arg2, TextClassification arg3);

    void clearMenuItemListeners();

    void performAction(MenuItem arg1, View arg2);
}

