package org.chromium.content_public.browser;

import android.os.Handler;
import org.chromium.base.annotations.UsedByReflection;

@UsedByReflection(value="ExternalOemSupport") public interface SmartClipProvider {
    @UsedByReflection(value="ExternalOemSupport") void extractSmartClipData(int arg1, int arg2, int arg3, int arg4);

    @UsedByReflection(value="ExternalOemSupport") void setSmartClipResultHandler(Handler arg1);
}

