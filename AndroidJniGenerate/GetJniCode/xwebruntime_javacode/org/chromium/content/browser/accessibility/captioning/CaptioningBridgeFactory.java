package org.chromium.content.browser.accessibility.captioning;

import android.content.Context;
import android.os.Build$VERSION;

public class CaptioningBridgeFactory {
    public CaptioningBridgeFactory() {
        super();
    }

    public static SystemCaptioningBridge getSystemCaptioningBridge(Context arg2) {
        if(Build$VERSION.SDK_INT >= 19) {
            return KitKatCaptioningBridge.getInstance(arg2);
        }

        return new EmptyCaptioningBridge();
    }
}

