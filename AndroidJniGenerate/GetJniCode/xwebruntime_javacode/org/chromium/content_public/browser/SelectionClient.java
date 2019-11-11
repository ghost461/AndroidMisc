package org.chromium.content_public.browser;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View$OnClickListener;
import android.view.textclassifier.TextClassification;
import android.view.textclassifier.TextClassifier;
import android.view.textclassifier.TextSelection;

public interface SelectionClient {
    public class Result {
        public int endAdjust;
        public Drawable icon;
        public Intent intent;
        public CharSequence label;
        public View$OnClickListener onClickListener;
        public int startAdjust;
        public TextClassification textClassification;
        public TextSelection textSelection;

        public Result() {
            super();
        }

        public boolean hasNamedAction() {
            boolean v0;
            if(this.label != null || this.icon != null) {
                if(this.intent == null) {
                    if(this.onClickListener != null) {
                    }
                    else {
                    label_9:
                        v0 = false;
                        return v0;
                    }
                }

                v0 = true;
            }
            else {
                goto label_9;
            }

            return v0;
        }
    }

    public interface ResultCallback {
        void onClassified(Result arg1);
    }

    void cancelAllRequests();

    TextClassifier getCustomTextClassifier();

    SelectionMetricsLogger getSelectionMetricsLogger();

    TextClassifier getTextClassifier();

    boolean isSearchable();

    boolean onSearchWord(String arg1, String arg2, String arg3);

    void onSelectionChanged(String arg1);

    void onSelectionEvent(int arg1, float arg2, float arg3);

    void onShowSos(boolean arg1);

    void onWeChatSelectionInfoChanged(SelectionPopupController arg1, String arg2, String arg3, String arg4, int arg5, int arg6, int arg7, String arg8, String arg9, String arg10, int arg11, int arg12, int arg13, long arg14);

    boolean requestSelectionPopupUpdates(boolean arg1);

    void selectWordAroundCaretAck(boolean arg1, int arg2, int arg3);

    void setTextClassifier(TextClassifier arg1);
}

