package org.chromium.content_public.browser;

import android.content.Context;
import org.chromium.content.browser.input.ImeAdapterImpl;

public abstract class ImeAdapter$$CC {
    public static InputMethodManagerWrapper createDefaultInputMethodManagerWrapper$$STATIC$$(Context arg0) {
        return ImeAdapterImpl.createDefaultInputMethodManagerWrapper(arg0);
    }

    public static ImeAdapter fromWebContents$$STATIC$$(WebContents arg0) {
        return ImeAdapterImpl.fromWebContents(arg0);
    }
}

