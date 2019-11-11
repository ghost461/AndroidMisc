package org.chromium.content_public.browser;

import android.view.textclassifier.TextClassifier;
import org.chromium.content.browser.selection.SmartSelectionClient;

public abstract class SelectionClient$$CC {
    public static SelectionClient createSmartSelectionClient$$STATIC$$(WebContents arg1) {
        return SmartSelectionClient.create(SelectionPopupController$$CC.fromWebContents$$STATIC$$(arg1).getResultCallback(), arg1);
    }

    public static TextClassifier getCustomTextClassifier(SelectionClient arg0) {
        return null;
    }

    public static SelectionMetricsLogger getSelectionMetricsLogger(SelectionClient arg0) {
        return null;
    }

    public static TextClassifier getTextClassifier(SelectionClient arg0) {
        return null;
    }

    public static void setTextClassifier(SelectionClient arg0, TextClassifier arg1) {
    }
}

