package org.chromium.content.browser.selection;

import android.os.Build$VERSION;
import android.text.TextUtils;
import android.view.textclassifier.TextClassifier;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Locale;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.content_public.browser.SelectionClient$Result;
import org.chromium.content_public.browser.SelectionClient$ResultCallback;
import org.chromium.content_public.browser.SelectionClient;
import org.chromium.content_public.browser.SelectionMetricsLogger;
import org.chromium.content_public.browser.SelectionPopupController;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

@JNINamespace(value="content") public class SmartSelectionClient implements SelectionClient {
    @Retention(value=RetentionPolicy.SOURCE) @interface RequestType {
    }

    private static final int CLASSIFY = 0;
    private static final int NUM_EXTRA_CHARS = 0xF0;
    private static final int SUGGEST_AND_CLASSIFY = 1;
    private ResultCallback mCallback;
    private long mNativeSmartSelectionClient;
    private SmartSelectionProvider mProvider;
    private SmartSelectionMetricsLogger mSmartSelectionMetricLogger;

    static {
    }

    private SmartSelectionClient(ResultCallback arg2, WebContents arg3, WindowAndroid arg4) {
        super();
        this.mProvider = new SmartSelectionProvider(arg2, arg4);
        this.mCallback = arg2;
        this.mSmartSelectionMetricLogger = SmartSelectionMetricsLogger.create(arg4.getContext().get());
        this.mNativeSmartSelectionClient = this.nativeInit(arg3);
    }

    public void cancelAllRequests() {
        if(this.mNativeSmartSelectionClient != 0) {
            this.nativeCancelAllRequests(this.mNativeSmartSelectionClient);
        }

        this.mProvider.cancelAllRequests();
    }

    public static SmartSelectionClient create(ResultCallback arg3, WebContents arg4) {
        WindowAndroid v0 = arg4.getTopLevelNativeWindow();
        if(Build$VERSION.SDK_INT >= 26) {
            if(v0 == null) {
            }
            else {
                return new SmartSelectionClient(arg3, arg4, v0);
            }
        }

        return null;
    }

    public TextClassifier getCustomTextClassifier() {
        return this.mProvider.getCustomTextClassifier();
    }

    public SelectionMetricsLogger getSelectionMetricsLogger() {
        return this.mSmartSelectionMetricLogger;
    }

    public TextClassifier getTextClassifier() {
        return this.mProvider.getTextClassifier();
    }

    public boolean isSearchable() {
        return 0;
    }

    private native void nativeCancelAllRequests(long arg1) {
    }

    private native long nativeInit(WebContents arg1) {
    }

    private native void nativeRequestSurroundingText(long arg1, int arg2, int arg3) {
    }

    @CalledByNative private void onNativeSideDestroyed(long arg1) {
        this.mNativeSmartSelectionClient = 0;
        this.mProvider.cancelAllRequests();
    }

    public boolean onSearchWord(String arg1, String arg2, String arg3) {
        return 0;
    }

    public void onSelectionChanged(String arg1) {
    }

    public void onSelectionEvent(int arg1, float arg2, float arg3) {
    }

    public void onShowSos(boolean arg1) {
    }

    @CalledByNative private void onSurroundingTextReceived(int arg2, String arg3, int arg4, int arg5) {
        if(!this.textHasValidSelection(arg3, arg4, arg5)) {
            this.mCallback.onClassified(new Result());
            return;
        }

        Locale[] v0 = null;
        switch(arg2) {
            case 0: {
                this.mProvider.sendClassifyRequest(((CharSequence)arg3), arg4, arg5, v0);
                break;
            }
            case 1: {
                this.mProvider.sendSuggestAndClassifyRequest(((CharSequence)arg3), arg4, arg5, v0);
                break;
            }
            default: {
                break;
            }
        }
    }

    public void onWeChatSelectionInfoChanged(SelectionPopupController arg1, String arg2, String arg3, String arg4, int arg5, int arg6, int arg7, String arg8, String arg9, String arg10, int arg11, int arg12, int arg13, long arg14) {
    }

    public boolean requestSelectionPopupUpdates(boolean arg1) {
        this.requestSurroundingText(((int)arg1));
        return 1;
    }

    private void requestSurroundingText(int arg6) {
        if(this.mNativeSmartSelectionClient == 0) {
            this.onSurroundingTextReceived(arg6, "", 0, 0);
            return;
        }

        this.nativeRequestSurroundingText(this.mNativeSmartSelectionClient, 0xF0, arg6);
    }

    public void selectWordAroundCaretAck(boolean arg1, int arg2, int arg3) {
    }

    public void setTextClassifier(TextClassifier arg2) {
        this.mProvider.setTextClassifier(arg2);
    }

    private boolean textHasValidSelection(String arg2, int arg3, int arg4) {
        boolean v2 = (TextUtils.isEmpty(((CharSequence)arg2))) || arg3 < 0 || arg3 >= arg4 || arg4 > arg2.length() ? false : true;
        return v2;
    }
}

