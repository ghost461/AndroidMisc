package org.chromium.content_public.browser;

import android.content.Intent;
import android.graphics.Rect;
import android.view.ActionMode$Callback;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import org.chromium.content.browser.selection.SelectionPopupControllerImpl;

public abstract class ActionModeCallbackHelper {
    class org.chromium.content_public.browser.ActionModeCallbackHelper$1 {
    }

    class EmptyActionCallback implements ActionMode$Callback {
        EmptyActionCallback(org.chromium.content_public.browser.ActionModeCallbackHelper$1 arg1) {
            this();
        }

        private EmptyActionCallback() {
            super();
        }

        public boolean onActionItemClicked(ActionMode arg1, MenuItem arg2) {
            return 0;
        }

        public boolean onCreateActionMode(ActionMode arg1, Menu arg2) {
            return 0;
        }

        public void onDestroyActionMode(ActionMode arg1) {
        }

        public boolean onPrepareActionMode(ActionMode arg1, Menu arg2) {
            return 0;
        }
    }

    public static final EmptyActionCallback EMPTY_CALLBACK = null;
    public static final int MAX_SEARCH_QUERY_LENGTH = 1000;
    public static final int MENU_ITEM_PROCESS_TEXT = 4;
    public static final int MENU_ITEM_SHARE = 1;
    public static final int MENU_ITEM_WEB_SEARCH = 2;
    private static final String TAG = "ActionModeHelper";

    static {
        ActionModeCallbackHelper.EMPTY_CALLBACK = new EmptyActionCallback(null);
    }

    public ActionModeCallbackHelper() {
        super();
    }

    public abstract void finishActionMode();

    public abstract String getSelectedText();

    public abstract String getWeChatPickedWord();

    public abstract boolean isActionModeValid();

    public abstract boolean onActionItemClicked(ActionMode arg1, MenuItem arg2);

    public abstract void onCreateActionMode(ActionMode arg1, Menu arg2);

    public abstract void onDestroyActionMode();

    public abstract void onGetContentRect(ActionMode arg1, View arg2, Rect arg3);

    public abstract boolean onPrepareActionMode(ActionMode arg1, Menu arg2);

    public abstract void onReceivedProcessTextResult(int arg1, Intent arg2);

    public static String sanitizeQuery(String arg0, int arg1) {
        return SelectionPopupControllerImpl.sanitizeQuery(arg0, arg1);
    }

    public abstract void setAllowedMenuItems(int arg1);

    public abstract boolean supportsFloatingActionMode();
}

