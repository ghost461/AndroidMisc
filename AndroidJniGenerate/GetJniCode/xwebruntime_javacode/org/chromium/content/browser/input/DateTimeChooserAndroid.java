package org.chromium.content.browser.input;

import android.content.Context;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.content.browser.picker.DateTimeSuggestion;
import org.chromium.content.browser.picker.InputDialogContainer$InputActionDelegate;
import org.chromium.content.browser.picker.InputDialogContainer;
import org.chromium.ui.base.WindowAndroid;

@JNINamespace(value="content") class DateTimeChooserAndroid {
    private final InputDialogContainer mInputDialogContainer;
    private final long mNativeDateTimeChooserAndroid;

    private DateTimeChooserAndroid(Context arg1, long arg2) {
        super();
        this.mNativeDateTimeChooserAndroid = arg2;
        this.mInputDialogContainer = new InputDialogContainer(arg1, new InputActionDelegate() {
            public void cancelDateTimeDialog() {
                DateTimeChooserAndroid.access$200(DateTimeChooserAndroid.this, DateTimeChooserAndroid.access$000(DateTimeChooserAndroid.this));
            }

            public void replaceDateTime(double arg4) {
                DateTimeChooserAndroid.access$100(DateTimeChooserAndroid.this, DateTimeChooserAndroid.access$000(DateTimeChooserAndroid.this), arg4);
            }
        });
    }

    static long access$000(DateTimeChooserAndroid arg2) {
        return arg2.mNativeDateTimeChooserAndroid;
    }

    static void access$100(DateTimeChooserAndroid arg0, long arg1, double arg3) {
        arg0.nativeReplaceDateTime(arg1, arg3);
    }

    static void access$200(DateTimeChooserAndroid arg0, long arg1) {
        arg0.nativeCancelDialog(arg1);
    }

    @CalledByNative private static DateTimeChooserAndroid createDateTimeChooser(WindowAndroid arg13, long arg14, int arg16, double arg17, double arg19, double arg21, double arg23, DateTimeSuggestion[] arg25) {
        Object v0 = arg13.getContext().get();
        if(WindowAndroid.activityFromContext(((Context)v0)) == null) {
            return null;
        }

        DateTimeChooserAndroid v12 = new DateTimeChooserAndroid(((Context)v0), arg14);
        v12.showDialog(arg16, arg17, arg19, arg21, arg23, arg25);
        return v12;
    }

    @CalledByNative private static DateTimeSuggestion[] createSuggestionsArray(int arg0) {
        return new DateTimeSuggestion[arg0];
    }

    private native void nativeCancelDialog(long arg1) {
    }

    private native void nativeReplaceDateTime(long arg1, double arg2) {
    }

    @CalledByNative private static void setDateTimeSuggestionAt(DateTimeSuggestion[] arg1, int arg2, double arg3, String arg5, String arg6) {
        arg1[arg2] = new DateTimeSuggestion(arg3, arg5, arg6);
    }

    private void showDialog(int arg13, double arg14, double arg16, double arg18, double arg20, DateTimeSuggestion[] arg22) {
        this.mInputDialogContainer.showDialog(arg13, arg14, arg16, arg18, arg20, arg22);
    }
}

