package org.chromium.content.browser.accessibility.captioning;

import android.annotation.TargetApi;
import android.content.Context;
import android.view.accessibility.CaptioningManager$CaptionStyle;
import android.view.accessibility.CaptioningManager$CaptioningChangeListener;
import android.view.accessibility.CaptioningManager;
import java.util.Locale;

@TargetApi(value=19) public class KitKatCaptioningBridge implements SystemCaptioningBridge {
    class org.chromium.content.browser.accessibility.captioning.KitKatCaptioningBridge$1 {
    }

    class KitKatCaptioningChangeListener extends CaptioningManager$CaptioningChangeListener {
        KitKatCaptioningChangeListener(KitKatCaptioningBridge arg1, org.chromium.content.browser.accessibility.captioning.KitKatCaptioningBridge$1 arg2) {
            this(arg1);
        }

        private KitKatCaptioningChangeListener(KitKatCaptioningBridge arg1) {
            KitKatCaptioningBridge.this = arg1;
            super();
        }

        public void onEnabledChanged(boolean arg2) {
            KitKatCaptioningBridge.this.mCaptioningChangeDelegate.onEnabledChanged(arg2);
        }

        public void onFontScaleChanged(float arg2) {
            KitKatCaptioningBridge.this.mCaptioningChangeDelegate.onFontScaleChanged(arg2);
        }

        public void onLocaleChanged(Locale arg2) {
            KitKatCaptioningBridge.this.mCaptioningChangeDelegate.onLocaleChanged(arg2);
        }

        public void onUserStyleChanged(CaptioningManager$CaptionStyle arg2) {
            KitKatCaptioningBridge.this.mCaptioningChangeDelegate.onUserStyleChanged(KitKatCaptioningBridge.this.getCaptioningStyleFrom(arg2));
        }
    }

    private final CaptioningChangeDelegate mCaptioningChangeDelegate;
    private final CaptioningManager$CaptioningChangeListener mCaptioningChangeListener;
    private final CaptioningManager mCaptioningManager;
    private static KitKatCaptioningBridge sKitKatCaptioningBridge;

    private KitKatCaptioningBridge(Context arg3) {
        super();
        this.mCaptioningChangeListener = new KitKatCaptioningChangeListener(this, null);
        this.mCaptioningChangeDelegate = new CaptioningChangeDelegate();
        this.mCaptioningManager = arg3.getApplicationContext().getSystemService("captioning");
    }

    static CaptioningChangeDelegate access$100(KitKatCaptioningBridge arg0) {
        return arg0.mCaptioningChangeDelegate;
    }

    static CaptioningStyle access$200(KitKatCaptioningBridge arg0, CaptioningManager$CaptionStyle arg1) {
        return arg0.getCaptioningStyleFrom(arg1);
    }

    public void addListener(SystemCaptioningBridgeListener arg3) {
        if(!this.mCaptioningChangeDelegate.hasActiveListener()) {
            this.mCaptioningManager.addCaptioningChangeListener(this.mCaptioningChangeListener);
            this.syncToDelegate();
        }

        this.mCaptioningChangeDelegate.addListener(arg3);
        this.mCaptioningChangeDelegate.notifyListener(arg3);
    }

    private CaptioningStyle getCaptioningStyleFrom(CaptioningManager$CaptionStyle arg1) {
        return CaptioningStyle.createFrom(arg1);
    }

    public static KitKatCaptioningBridge getInstance(Context arg1) {
        if(KitKatCaptioningBridge.sKitKatCaptioningBridge == null) {
            KitKatCaptioningBridge.sKitKatCaptioningBridge = new KitKatCaptioningBridge(arg1);
        }

        return KitKatCaptioningBridge.sKitKatCaptioningBridge;
    }

    public void removeListener(SystemCaptioningBridgeListener arg2) {
        this.mCaptioningChangeDelegate.removeListener(arg2);
        if(!this.mCaptioningChangeDelegate.hasActiveListener()) {
            this.mCaptioningManager.removeCaptioningChangeListener(this.mCaptioningChangeListener);
        }
    }

    private void syncToDelegate() {
        this.mCaptioningChangeDelegate.onEnabledChanged(this.mCaptioningManager.isEnabled());
        this.mCaptioningChangeDelegate.onFontScaleChanged(this.mCaptioningManager.getFontScale());
        this.mCaptioningChangeDelegate.onLocaleChanged(this.mCaptioningManager.getLocale());
        this.mCaptioningChangeDelegate.onUserStyleChanged(this.getCaptioningStyleFrom(this.mCaptioningManager.getUserStyle()));
    }

    public void syncToListener(SystemCaptioningBridgeListener arg2) {
        if(!this.mCaptioningChangeDelegate.hasActiveListener()) {
            this.syncToDelegate();
        }

        this.mCaptioningChangeDelegate.notifyListener(arg2);
    }
}

