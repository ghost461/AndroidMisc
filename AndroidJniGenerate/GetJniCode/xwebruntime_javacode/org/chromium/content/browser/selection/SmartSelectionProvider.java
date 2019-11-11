package org.chromium.content.browser.selection;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.LocaleList;
import android.view.textclassifier.TextClassification;
import android.view.textclassifier.TextClassifier;
import android.view.textclassifier.TextSelection;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Locale;
import org.chromium.content_public.browser.SelectionClient$Result;
import org.chromium.content_public.browser.SelectionClient$ResultCallback;
import org.chromium.ui.base.WindowAndroid;

public class SmartSelectionProvider {
    @TargetApi(value=26) class ClassificationTask extends AsyncTask {
        private final Locale[] mLocales;
        private final int mOriginalEnd;
        private final int mOriginalStart;
        private final int mRequestType;
        private final CharSequence mText;
        private final TextClassifier mTextClassifier;

        ClassificationTask(SmartSelectionProvider arg1, TextClassifier arg2, int arg3, CharSequence arg4, int arg5, int arg6, Locale[] arg7) {
            SmartSelectionProvider.this = arg1;
            super();
            this.mTextClassifier = arg2;
            this.mRequestType = arg3;
            this.mText = arg4;
            this.mOriginalStart = arg5;
            this.mOriginalEnd = arg6;
            this.mLocales = arg7;
        }

        protected Object doInBackground(Object[] arg1) {
            return this.doInBackground(((Void[])arg1));
        }

        protected Result doInBackground(Void[] arg7) {
            TextSelection v1_1;
            int v7 = this.mOriginalStart;
            int v0 = this.mOriginalEnd;
            if(this.mRequestType == 1) {
                TextSelection v7_1 = this.mTextClassifier.suggestSelection(this.mText, v7, v0, this.makeLocaleList(this.mLocales));
                v0 = Math.max(0, v7_1.getSelectionStartIndex());
                int v1 = Math.min(this.mText.length(), v7_1.getSelectionEndIndex());
                if(this.isCancelled()) {
                    return new Result();
                }
                else {
                    int v5 = v1;
                    v1_1 = v7_1;
                    v7 = v0;
                    v0 = v5;
                }
            }
            else {
                v1_1 = null;
            }

            return this.makeResult(v7, v0, this.mTextClassifier.classifyText(this.mText, v7, v0, this.makeLocaleList(this.mLocales)), v1_1);
        }

        @SuppressLint(value={"NewApi"}) private LocaleList makeLocaleList(Locale[] arg2) {
            if(arg2 != null) {
                if(arg2.length == 0) {
                }
                else {
                    return new LocaleList(arg2);
                }
            }

            return null;
        }

        private Result makeResult(int arg3, int arg4, TextClassification arg5, TextSelection arg6) {
            Result v0 = new Result();
            v0.startAdjust = arg3 - this.mOriginalStart;
            v0.endAdjust = arg4 - this.mOriginalEnd;
            v0.label = arg5.getLabel();
            v0.icon = arg5.getIcon();
            v0.intent = arg5.getIntent();
            v0.onClickListener = arg5.getOnClickListener();
            v0.textSelection = arg6;
            v0.textClassification = arg5;
            return v0;
        }

        protected void onPostExecute(Object arg1) {
            this.onPostExecute(((Result)arg1));
        }

        protected void onPostExecute(Result arg2) {
            SmartSelectionProvider.this.mResultCallback.onClassified(arg2);
        }
    }

    @Retention(value=RetentionPolicy.SOURCE) @interface RequestType {
    }

    private static final int CLASSIFY = 0;
    private static final int SUGGEST_AND_CLASSIFY = 1;
    private static final String TAG = "SmartSelProvider";
    private ClassificationTask mClassificationTask;
    private Runnable mFailureResponseRunnable;
    private Handler mHandler;
    private ResultCallback mResultCallback;
    private TextClassifier mTextClassifier;
    private WindowAndroid mWindowAndroid;

    public SmartSelectionProvider(ResultCallback arg1, WindowAndroid arg2) {
        super();
        this.mResultCallback = arg1;
        this.mWindowAndroid = arg2;
        this.mHandler = new Handler();
        this.mFailureResponseRunnable = new Runnable() {
            public void run() {
                SmartSelectionProvider.access$000(SmartSelectionProvider.this).onClassified(new Result());
            }
        };
    }

    static ResultCallback access$000(SmartSelectionProvider arg0) {
        return arg0.mResultCallback;
    }

    public void cancelAllRequests() {
        if(this.mClassificationTask != null) {
            this.mClassificationTask.cancel(false);
            this.mClassificationTask = null;
        }
    }

    public TextClassifier getCustomTextClassifier() {
        return this.mTextClassifier;
    }

    @SuppressLint(value={"WrongConstant"}) @TargetApi(value=26) public TextClassifier getTextClassifier() {
        if(this.mTextClassifier != null) {
            return this.mTextClassifier;
        }

        Object v0 = this.mWindowAndroid.getContext().get();
        if(v0 == null) {
            return null;
        }

        return ((Context)v0).getSystemService("textclassification").getTextClassifier();
    }

    public void sendClassifyRequest(CharSequence arg7, int arg8, int arg9, Locale[] arg10) {
        this.sendSmartSelectionRequest(0, arg7, arg8, arg9, arg10);
    }

    @TargetApi(value=26) private void sendSmartSelectionRequest(int arg11, CharSequence arg12, int arg13, int arg14, Locale[] arg15) {
        TextClassifier v2 = this.getTextClassifier();
        if(v2 != null) {
            if(v2 == TextClassifier.NO_OP) {
            }
            else {
                if(this.mClassificationTask != null) {
                    this.mClassificationTask.cancel(false);
                    this.mClassificationTask = null;
                }

                this.mClassificationTask = new ClassificationTask(this, v2, arg11, arg12, arg13, arg14, arg15);
                this.mClassificationTask.execute(new Void[0]);
                return;
            }
        }

        this.mHandler.post(this.mFailureResponseRunnable);
    }

    public void sendSuggestAndClassifyRequest(CharSequence arg7, int arg8, int arg9, Locale[] arg10) {
        this.sendSmartSelectionRequest(1, arg7, arg8, arg9, arg10);
    }

    public void setTextClassifier(TextClassifier arg1) {
        this.mTextClassifier = arg1;
    }
}

