package org.chromium.components.autofill;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="autofill") public class FormFieldData {
    @Retention(value=RetentionPolicy.SOURCE) @public interface ControlType {
    }

    public static final int TYPE_LIST = 2;
    public static final int TYPE_TEXT = 0;
    public static final int TYPE_TOGGLE = 1;
    public final String mAutocompleteAttr;
    public final int mControlType;
    public final String mId;
    private boolean mIsChecked;
    public final String mLabel;
    public final String mName;
    public final String[] mOptionContents;
    public final String[] mOptionValues;
    public final String mPlaceholder;
    public final boolean mShouldAutocomplete;
    public final String mType;
    private String mValue;

    private FormFieldData(String arg1, String arg2, String arg3, String arg4, boolean arg5, String arg6, String arg7, String arg8, String[] arg9, String[] arg10, boolean arg11, boolean arg12) {
        super();
        this.mName = arg1;
        this.mLabel = arg2;
        this.mValue = arg3;
        this.mAutocompleteAttr = arg4;
        this.mShouldAutocomplete = arg5;
        this.mPlaceholder = arg6;
        this.mType = arg7;
        this.mId = arg8;
        this.mOptionValues = arg9;
        this.mOptionContents = arg10;
        this.mIsChecked = arg12;
        if(this.mOptionValues != null && this.mOptionValues.length != 0) {
            this.mControlType = 2;
        }
        else if(arg11) {
            this.mControlType = 1;
        }
        else {
            this.mControlType = 0;
        }
    }

    @CalledByNative private static FormFieldData createFormFieldData(String arg14, String arg15, String arg16, String arg17, boolean arg18, String arg19, String arg20, String arg21, String[] arg22, String[] arg23, boolean arg24, boolean arg25) {
        return new FormFieldData(arg14, arg15, arg16, arg17, arg18, arg19, arg20, arg21, arg22, arg23, arg24, arg25);
    }

    public int getControlType() {
        return this.mControlType;
    }

    @CalledByNative public String getValue() {
        return this.mValue;
    }

    @CalledByNative public boolean isChecked() {
        return this.mIsChecked;
    }

    public void setChecked(boolean arg1) {
        this.mIsChecked = arg1;
    }

    @CalledByNative public void updateValue(String arg1) {
        this.mValue = arg1;
    }
}

