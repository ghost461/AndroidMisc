package org.chromium.ui.base;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager$OnPrimaryClipChangedListener;
import android.content.ClipboardManager;
import android.content.Context;
import android.text.Spanned;
import android.text.style.CharacterStyle;
import android.text.style.ParagraphStyle;
import android.text.style.UpdateAppearance;
import org.chromium.base.ApiCompatibilityUtils;
import org.chromium.base.ContextUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.base.metrics.RecordUserAction;
import org.chromium.ui.widget.Toast;

@JNINamespace(value="ui") public class Clipboard implements ClipboardManager$OnPrimaryClipChangedListener {
    private final ClipboardManager mClipboardManager;
    private final Context mContext;
    @SuppressLint(value={"StaticFieldLeak"}) private static Clipboard sInstance;

    private Clipboard() {
        super();
        this.mContext = ContextUtils.getApplicationContext();
        this.mClipboardManager = ContextUtils.getApplicationContext().getSystemService("clipboard");
        this.mClipboardManager.addPrimaryClipChangedListener(((ClipboardManager$OnPrimaryClipChangedListener)this));
    }

    @CalledByNative private void clear() {
        this.setPrimaryClipNoException(ClipData.newPlainText(null, null));
    }

    public String clipDataToHtmlText(ClipData arg4) {
        ClipDescription v0 = arg4.getDescription();
        if(v0.hasMimeType("text/html")) {
            return arg4.getItemAt(0).getHtmlText();
        }

        String v1 = null;
        if(v0.hasMimeType("text/plain")) {
            CharSequence v4 = arg4.getItemAt(0).getText();
            if(!(v4 instanceof Spanned)) {
                return v1;
            }
            else if(this.hasStyleSpan(((Spanned)v4))) {
                return ApiCompatibilityUtils.toHtml(((Spanned)v4), 0);
            }
        }

        return v1;
    }

    @CalledByNative private String getCoercedText() {
        try {
            return this.mClipboardManager.getPrimaryClip().getItemAt(0).coerceToText(this.mContext).toString();
        }
        catch(Exception ) {
            return null;
        }
    }

    @CalledByNative private String getHTMLText() {
        try {
            return this.clipDataToHtmlText(this.mClipboardManager.getPrimaryClip());
        }
        catch(Exception ) {
            return null;
        }
    }

    @CalledByNative public static Clipboard getInstance() {
        if(Clipboard.sInstance == null) {
            Clipboard.sInstance = new Clipboard();
        }

        return Clipboard.sInstance;
    }

    private boolean hasStyleSpan(Spanned arg9) {
        Class[] v0 = new Class[]{CharacterStyle.class, ParagraphStyle.class, UpdateAppearance.class};
        int v1 = v0.length;
        int v4;
        for(v4 = 0; v4 < v1; ++v4) {
            if(arg9.nextSpanTransition(-1, arg9.length(), v0[v4]) < arg9.length()) {
                return 1;
            }
        }

        return 0;
    }

    private native long nativeInit() {
    }

    private native void nativeOnPrimaryClipChanged(long arg1) {
    }

    public void onPrimaryClipChanged() {
        RecordUserAction.record("MobileClipboardChanged");
        long v0 = this.nativeInit();
        if(v0 != 0) {
            this.nativeOnPrimaryClipChanged(v0);
        }
    }

    @CalledByNative private void setHTMLText(String arg2, String arg3) {
        this.setPrimaryClipNoException(ClipData.newHtmlText("html", ((CharSequence)arg3), arg2));
    }

    public void setPrimaryClipNoException(ClipData arg3) {
        try {
            this.mClipboardManager.setPrimaryClip(arg3);
        }
        catch(Exception ) {
            Toast.makeText(this.mContext, this.mContext.getString(0x7F0C003F), 0).show();
        }
    }

    @CalledByNative public void setText(String arg2) {
        this.setPrimaryClipNoException(ClipData.newPlainText("text", ((CharSequence)arg2)));
    }
}

