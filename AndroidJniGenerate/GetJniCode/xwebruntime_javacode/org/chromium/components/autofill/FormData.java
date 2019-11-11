package org.chromium.components.autofill;

import java.util.ArrayList;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="autofill") public class FormData {
    public final ArrayList mFields;
    public final String mHost;
    public final String mName;
    private long mNativeObj;

    static {
    }

    private FormData(long arg1, String arg3, String arg4, int arg5) {
        super();
        this.mNativeObj = arg1;
        this.mName = arg3;
        this.mHost = arg4;
        this.mFields = new ArrayList(arg5);
        this.popupFormFields(arg5);
    }

    @CalledByNative private static FormData createFormData(long arg7, String arg9, String arg10, int arg11) {
        return new FormData(arg7, arg9, arg10, arg11);
    }

    private native FormFieldData nativeGetNextFormFieldData(long arg1) {
    }

    @CalledByNative private void onNativeDestroyed() {
        this.mNativeObj = 0;
    }

    private void popupFormFields(int arg3) {
        FormFieldData v3;
        for(v3 = this.nativeGetNextFormFieldData(this.mNativeObj); v3 != null; v3 = this.nativeGetNextFormFieldData(this.mNativeObj)) {
            this.mFields.add(v3);
        }
    }
}

