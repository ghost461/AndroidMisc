package org.chromium.content.browser.selection;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Rect;
import android.view.ActionMode$Callback2;
import android.view.ActionMode$Callback;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import org.chromium.ui.base.DeviceFormFactor;

@TargetApi(value=23) public class FloatingPastePopupMenu implements PastePopupMenu {
    class org.chromium.content.browser.selection.FloatingPastePopupMenu$1 {
    }

    class ActionModeCallback extends ActionMode$Callback2 {
        ActionModeCallback(FloatingPastePopupMenu arg1, org.chromium.content.browser.selection.FloatingPastePopupMenu$1 arg2) {
            this(arg1);
        }

        private ActionModeCallback(FloatingPastePopupMenu arg1) {
            FloatingPastePopupMenu.this = arg1;
            super();
        }

        private void createPasteMenu(ActionMode arg4, Menu arg5) {
            CharSequence v0_1;
            CharSequence v1 = null;
            if(DeviceFormFactor.isNonMultiDisplayContextOnTablet(FloatingPastePopupMenu.this.mContext)) {
                String v0 = FloatingPastePopupMenu.this.mContext.getString(0x7F0C002A);
            }
            else {
                v0_1 = v1;
            }

            arg4.setTitle(v0_1);
            arg4.setSubtitle(v1);
            SelectionPopupControllerImpl.initializeMenu(FloatingPastePopupMenu.this.mContext, arg4, arg5);
            if(!FloatingPastePopupMenu.this.mDelegate.canPaste()) {
                arg5.removeItem(0x7F070096);
            }

            if(!FloatingPastePopupMenu.this.mDelegate.canSelectAll()) {
                arg5.removeItem(0x7F070098);
            }

            if(!FloatingPastePopupMenu.this.mDelegate.canPasteAsPlainText()) {
                arg5.removeItem(0x7F070097);
            }

            SelectionPopupControllerImpl.setPasteAsPlainTextMenuItemTitle(arg5);
            arg5.removeItem(0x7F070094);
            arg5.removeItem(0x7F070093);
            arg5.removeItem(0x7F070099);
            arg5.removeItem(0x7F07009B);
        }

        public boolean onActionItemClicked(ActionMode arg3, MenuItem arg4) {
            boolean v3;
            int v0 = arg4.getItemId();
            if(v0 == 0x7F070096) {
                FloatingPastePopupMenu.this.mDelegate.paste();
                arg3.finish();
                goto label_29;
            }
            else if(v0 == 0x7F070097) {
                FloatingPastePopupMenu.this.mDelegate.pasteAsPlainText();
                arg3.finish();
                goto label_29;
            }
            else if(v0 == 0x7F070098) {
                FloatingPastePopupMenu.this.mDelegate.selectAll();
                arg3.finish();
                goto label_29;
            }
            else if(FloatingPastePopupMenu.this.mExternalCallback != null) {
                v3 = FloatingPastePopupMenu.this.mExternalCallback.onActionItemClicked(arg3, arg4);
            }
            else {
            label_29:
                v3 = true;
            }

            return v3;
        }

        public boolean onCreateActionMode(ActionMode arg2, Menu arg3) {
            this.createPasteMenu(arg2, arg3);
            if(FloatingPastePopupMenu.this.mExternalCallback != null) {
                FloatingPastePopupMenu.this.mExternalCallback.onCreateActionMode(arg2, arg3);
            }

            return 1;
        }

        public void onDestroyActionMode(ActionMode arg2) {
            if(FloatingPastePopupMenu.this.mExternalCallback != null) {
                FloatingPastePopupMenu.this.mExternalCallback.onDestroyActionMode(arg2);
            }

            FloatingPastePopupMenu.this.mActionMode = null;
        }

        public void onGetContentRect(ActionMode arg1, View arg2, Rect arg3) {
            arg3.set(FloatingPastePopupMenu.this.mSelectionRect);
        }

        public boolean onPrepareActionMode(ActionMode arg2, Menu arg3) {
            boolean v2 = FloatingPastePopupMenu.this.mExternalCallback != null ? FloatingPastePopupMenu.this.mExternalCallback.onPrepareActionMode(arg2, arg3) : false;
            return v2;
        }
    }

    private ActionMode mActionMode;
    private final Context mContext;
    private final PastePopupMenuDelegate mDelegate;
    private ActionMode$Callback mExternalCallback;
    private final View mParent;
    private Rect mSelectionRect;

    static {
    }

    public FloatingPastePopupMenu(Context arg1, View arg2, PastePopupMenuDelegate arg3, ActionMode$Callback arg4) {
        super();
        this.mParent = arg2;
        this.mDelegate = arg3;
        this.mContext = arg1;
        this.mExternalCallback = arg4;
    }

    static ActionMode$Callback access$100(FloatingPastePopupMenu arg0) {
        return arg0.mExternalCallback;
    }

    static Context access$200(FloatingPastePopupMenu arg0) {
        return arg0.mContext;
    }

    static PastePopupMenuDelegate access$300(FloatingPastePopupMenu arg0) {
        return arg0.mDelegate;
    }

    static ActionMode access$402(FloatingPastePopupMenu arg0, ActionMode arg1) {
        arg0.mActionMode = arg1;
        return arg1;
    }

    static Rect access$500(FloatingPastePopupMenu arg0) {
        return arg0.mSelectionRect;
    }

    private void ensureActionMode() {
        if(this.mActionMode != null) {
            return;
        }

        ActionMode v0 = this.mParent.startActionMode(new ActionModeCallback(this, null), 1);
        if(v0 != null) {
            LGEmailActionModeWorkaround.runIfNecessary(this.mContext, v0);
            this.mActionMode = v0;
        }
    }

    public void hide() {
        if(this.mActionMode != null) {
            this.mActionMode.finish();
            this.mActionMode = null;
        }
    }

    public void show(Rect arg1) {
        this.mSelectionRect = arg1;
        if(this.mActionMode != null) {
            this.mActionMode.invalidateContentRect();
            return;
        }

        this.ensureActionMode();
    }
}

