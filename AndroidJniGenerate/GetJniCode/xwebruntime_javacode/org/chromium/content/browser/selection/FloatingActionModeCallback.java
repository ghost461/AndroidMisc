package org.chromium.content.browser.selection;

import android.annotation.TargetApi;
import android.graphics.Rect;
import android.view.ActionMode$Callback2;
import android.view.ActionMode$Callback;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import org.chromium.content_public.browser.ActionModeCallbackHelper;

@TargetApi(value=23) class FloatingActionModeCallback extends ActionMode$Callback2 {
    private final ActionMode$Callback mCallback;
    private final ActionModeCallbackHelper mHelper;

    public FloatingActionModeCallback(ActionModeCallbackHelper arg1, ActionMode$Callback arg2) {
        super();
        this.mHelper = arg1;
        this.mCallback = arg2;
    }

    public boolean onActionItemClicked(ActionMode arg2, MenuItem arg3) {
        return this.mCallback.onActionItemClicked(arg2, arg3);
    }

    public boolean onCreateActionMode(ActionMode arg3, Menu arg4) {
        if(arg3.getType() != 1) {
            return 0;
        }

        return this.mCallback.onCreateActionMode(arg3, arg4);
    }

    public void onDestroyActionMode(ActionMode arg2) {
        this.mCallback.onDestroyActionMode(arg2);
    }

    public void onGetContentRect(ActionMode arg2, View arg3, Rect arg4) {
        this.mHelper.onGetContentRect(arg2, arg3, arg4);
    }

    public boolean onPrepareActionMode(ActionMode arg2, Menu arg3) {
        return this.mCallback.onPrepareActionMode(arg2, arg3);
    }
}

