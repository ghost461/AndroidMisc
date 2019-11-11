package org.xwalk.core.internal;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.ActionMode$Callback;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import org.chromium.base.metrics.RecordUserAction;
import org.chromium.content_public.browser.ActionModeCallbackHelper;

public class XWalkActionModeCallback implements ActionMode$Callback {
    private int mAllowedMenuItems;
    private final Context mContext;
    private final ActionModeCallbackHelper mHelper;
    private final XWalkContent mXwContents;

    static {
    }

    public XWalkActionModeCallback(Context arg1, XWalkContent arg2, ActionModeCallbackHelper arg3) {
        super();
        this.mContext = arg1;
        this.mXwContents = arg2;
        this.mHelper = arg3;
        this.mHelper.setAllowedMenuItems(0);
    }

    private int getAllowedMenu(int arg6) {
        if(arg6 == 2 && (this.isWebSearchAvailable())) {
            if(this.mHelper.getWeChatPickedWord() != null && !this.mHelper.getWeChatPickedWord().equals(this.mHelper.getSelectedText())) {
                this.mXwContents.onShowSos(false);
                return 0;
            }

            this.mXwContents.onShowSos(true);
        }

        return 0;
    }

    private boolean isWebSearchAvailable() {
        return this.mXwContents.isSearchable();
    }

    public boolean onActionItemClicked(ActionMode arg4, MenuItem arg5) {
        if(!this.mHelper.isActionModeValid()) {
            return 1;
        }

        if(arg5.getGroupId() == 0x7F07009A) {
            return 1;
        }

        return this.mHelper.onActionItemClicked(arg4, arg5);
    }

    public boolean onCreateActionMode(ActionMode arg3, Menu arg4) {
        int v0 = this.getAllowedMenu(2);
        if(v0 != this.mAllowedMenuItems) {
            this.mHelper.setAllowedMenuItems(v0);
            this.mAllowedMenuItems = v0;
        }

        this.mHelper.onCreateActionMode(arg3, arg4);
        return 1;
    }

    public void onDestroyActionMode(ActionMode arg1) {
        this.mHelper.onDestroyActionMode();
    }

    public boolean onPrepareActionMode(ActionMode arg2, Menu arg3) {
        return this.mHelper.onPrepareActionMode(arg2, arg3);
    }

    private void processText(Intent arg3) {
        RecordUserAction.record("MobileActionMode.ProcessTextIntent");
        String v0 = ActionModeCallbackHelper.sanitizeQuery(this.mHelper.getSelectedText(), 1000);
        if(TextUtils.isEmpty(((CharSequence)v0))) {
            return;
        }

        arg3.putExtra("android.intent.extra.PROCESS_TEXT", v0);
    }
}

