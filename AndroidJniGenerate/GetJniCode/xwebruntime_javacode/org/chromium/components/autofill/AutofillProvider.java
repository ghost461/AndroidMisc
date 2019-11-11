package org.chromium.components.autofill;

import android.util.SparseArray;
import android.view.ViewGroup;
import android.view.ViewStructure;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.content_public.browser.WebContents;

@JNINamespace(value="autofill") public abstract class AutofillProvider {
    public AutofillProvider() {
        super();
    }

    protected void autofill(long arg1, FormData arg3) {
        this.nativeOnAutofillAvailable(arg1, arg3);
    }

    public abstract void autofill(SparseArray arg1);

    private native void nativeOnAutofillAvailable(long arg1, FormData arg2) {
    }

    public abstract void onContainerViewChanged(ViewGroup arg1);

    @CalledByNative protected abstract void onDidFillAutofillFormData();

    @CalledByNative protected abstract void onFocusChanged(boolean arg1, int arg2, float arg3, float arg4, float arg5, float arg6);

    @CalledByNative protected abstract void onFormFieldDidChange(int arg1, float arg2, float arg3, float arg4, float arg5);

    @CalledByNative protected abstract void onFormSubmitted(int arg1);

    public abstract void onProvideAutoFillVirtualStructure(ViewStructure arg1, int arg2);

    @CalledByNative protected abstract void onTextFieldDidScroll(int arg1, float arg2, float arg3, float arg4, float arg5);

    public abstract void queryAutofillSuggestion();

    @CalledByNative protected abstract void reset();

    @CalledByNative protected abstract void setNativeAutofillProvider(long arg1);

    public abstract void setWebContents(WebContents arg1);

    public abstract boolean shouldQueryAutofillSuggestion();

    @CalledByNative protected abstract void startAutofillSession(FormData arg1, int arg2, float arg3, float arg4, float arg5, float arg6);
}

