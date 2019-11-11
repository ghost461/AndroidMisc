package org.chromium.components.web_contents_delegate_android;

import android.content.Context;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.ui.base.WindowAndroid;

@JNINamespace(value="web_contents_delegate_android") public class ColorChooserAndroid {
    private final ColorPickerDialog mDialog;
    private final long mNativeColorChooserAndroid;

    private ColorChooserAndroid(long arg2, Context arg4, int arg5, ColorSuggestion[] arg6) {
        super();
        org.chromium.components.web_contents_delegate_android.ColorChooserAndroid$1 v0 = new OnColorChangedListener() {
            public void onColorChanged(int arg4) {
                ColorChooserAndroid.access$000(ColorChooserAndroid.this).dismiss();
                ColorChooserAndroid.access$200(ColorChooserAndroid.this, ColorChooserAndroid.access$100(ColorChooserAndroid.this), arg4);
            }
        };
        this.mNativeColorChooserAndroid = arg2;
        this.mDialog = new ColorPickerDialog(arg4, ((OnColorChangedListener)v0), arg5, arg6);
    }

    static ColorPickerDialog access$000(ColorChooserAndroid arg0) {
        return arg0.mDialog;
    }

    static long access$100(ColorChooserAndroid arg2) {
        return arg2.mNativeColorChooserAndroid;
    }

    static void access$200(ColorChooserAndroid arg0, long arg1, int arg3) {
        arg0.nativeOnColorChosen(arg1, arg3);
    }

    @CalledByNative private static void addToColorSuggestionArray(ColorSuggestion[] arg1, int arg2, int arg3, String arg4) {
        arg1[arg2] = new ColorSuggestion(arg3, arg4);
    }

    @CalledByNative public void closeColorChooser() {
        this.mDialog.dismiss();
    }

    @CalledByNative public static ColorChooserAndroid createColorChooserAndroid(long arg7, WindowAndroid arg9, int arg10, ColorSuggestion[] arg11) {
        ColorChooserAndroid v0 = null;
        if(arg9 == null) {
            return v0;
        }

        Object v4 = arg9.getContext().get();
        if(WindowAndroid.activityFromContext(((Context)v4)) == null) {
            return v0;
        }

        ColorChooserAndroid v9 = new ColorChooserAndroid(arg7, ((Context)v4), arg10, arg11);
        v9.openColorChooser();
        return v9;
    }

    @CalledByNative private static ColorSuggestion[] createColorSuggestionArray(int arg0) {
        return new ColorSuggestion[arg0];
    }

    private native void nativeOnColorChosen(long arg1, int arg2) {
    }

    private void openColorChooser() {
        this.mDialog.show();
    }
}

