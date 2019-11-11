package org.xwalk.core.internal;

import android.content.Context;
import android.view.View;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.components.autofill.AutofillDelegate;
import org.chromium.components.autofill.AutofillPopup;
import org.chromium.components.autofill.AutofillSuggestion;
import org.chromium.content_public.browser.ContentViewCore;
import org.chromium.ui.base.WindowAndroid;

@JNINamespace(value="xwalk") public class XWalkAutofillClientAndroid {
    private static final String TAG = "XWalkAutofillClientAndroid";
    private AutofillPopup mAutofillPopup;
    private ContentViewCore mContentViewCore;
    private Context mContext;
    private final long mNativeXWalkAutofillClientAndroid;

    private XWalkAutofillClientAndroid(long arg1) {
        super();
        this.mNativeXWalkAutofillClientAndroid = arg1;
    }

    static long access$000(XWalkAutofillClientAndroid arg2) {
        return arg2.mNativeXWalkAutofillClientAndroid;
    }

    static void access$100(XWalkAutofillClientAndroid arg0, long arg1, int arg3) {
        arg0.nativeSuggestionSelected(arg1, arg3);
    }

    @CalledByNative private static void addToAutofillSuggestionArray(AutofillSuggestion[] arg10, int arg11, String arg12, String arg13, int arg14) {
        arg10[arg11] = new AutofillSuggestion(arg12, arg13, 0, false, arg14, false, false, false);
    }

    @CalledByNative public static XWalkAutofillClientAndroid create(long arg1) {
        return new XWalkAutofillClientAndroid(arg1);
    }

    @CalledByNative private static AutofillSuggestion[] createAutofillSuggestionArray(int arg0) {
        return new AutofillSuggestion[arg0];
    }

    @CalledByNative public void hideAutofillPopup() {
        if(this.mAutofillPopup == null) {
            return;
        }

        this.mAutofillPopup.dismiss();
        this.mAutofillPopup = null;
    }

    public void init(ContentViewCore arg1, Context arg2) {
        this.mContentViewCore = arg1;
        this.mContext = arg2;
    }

    private native void nativeSuggestionSelected(long arg1, int arg2) {
    }

    @CalledByNative private void showAutofillPopup(float arg8, float arg9, float arg10, float arg11, boolean arg12, AutofillSuggestion[] arg13) {
        if(this.mContentViewCore == null) {
            return;
        }

        if(this.mAutofillPopup == null) {
            View v8 = this.mContentViewCore.getViewAndroidDelegate().acquireView();
            if(v8 == null) {
                Log.e("XWalkAutofillClientAndroid", "showAutofillPopup acquireView = null");
                return;
            }
            else {
                this.mAutofillPopup = new AutofillPopup(this.mContext, v8, new AutofillDelegate() {
                    public void accessibilityFocusCleared() {
                    }

                    public void deleteSuggestion(int arg1) {
                    }

                    public void dismissed() {
                    }

                    public void suggestionSelected(int arg4) {
                        XWalkAutofillClientAndroid.this.nativeSuggestionSelected(XWalkAutofillClientAndroid.this.mNativeXWalkAutofillClientAndroid, arg4);
                    }
                });
            }
        }

        this.mAutofillPopup.filterAndShow(arg13, arg12, 0, 0, 0, 0);
    }

    @CalledByNative private void showAutofillPopup(View arg11, boolean arg12, AutofillSuggestion[] arg13) {
        if(this.mContentViewCore == null) {
            return;
        }

        if(this.mAutofillPopup == null) {
            if(WindowAndroid.activityFromContext(this.mContext) == null) {
                return;
            }
            else {
                this.mAutofillPopup = new AutofillPopup(this.mContext, arg11, new AutofillDelegate() {
                    public void accessibilityFocusCleared() {
                    }

                    public void deleteSuggestion(int arg1) {
                    }

                    public void dismissed() {
                    }

                    public void suggestionSelected(int arg4) {
                        XWalkAutofillClientAndroid.this.nativeSuggestionSelected(XWalkAutofillClientAndroid.this.mNativeXWalkAutofillClientAndroid, arg4);
                    }
                });
            }
        }

        this.mAutofillPopup.filterAndShow(arg13, arg12, 0, 0, 0, 0);
    }
}

