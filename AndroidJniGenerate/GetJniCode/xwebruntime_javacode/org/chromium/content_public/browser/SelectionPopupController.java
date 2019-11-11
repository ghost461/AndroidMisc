package org.chromium.content_public.browser;

import android.content.Intent;
import android.view.ActionMode$Callback;
import android.view.textclassifier.TextClassifier;

public interface SelectionPopupController {
    void adjustSelectPosition(long arg1, String arg2, int arg3, int arg4);

    void clearSelection();

    void destroySelectActionMode();

    void didReceivedWeChatSelectionInfo(String arg1, int arg2, int arg3, String arg4, String arg5, int arg6);

    ActionModeCallbackHelper getActionModeCallbackHelper();

    TextClassifier getCustomTextClassifier();

    ResultCallback getResultCallback();

    String getSelectedText();

    TextClassifier getTextClassifier();

    boolean hasSelection();

    boolean isFocusedNodeEditable();

    boolean isSelectActionBarShowing();

    void onReceivedProcessTextResult(int arg1, Intent arg2);

    void setActionModeCallback(ActionMode$Callback arg1);

    void setNonSelectionActionModeCallback(ActionMode$Callback arg1);

    void setPreserveSelectionOnNextLossOfFocus(boolean arg1);

    void setSelectionClient(SelectionClient arg1);

    void setTextClassifier(TextClassifier arg1);

    void updateTextSelectionUI(boolean arg1);
}

