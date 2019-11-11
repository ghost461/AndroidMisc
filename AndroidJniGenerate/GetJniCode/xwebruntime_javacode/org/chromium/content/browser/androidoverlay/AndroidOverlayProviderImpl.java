package org.chromium.content.browser.androidoverlay;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.media.mojom.AndroidOverlayClient;
import org.chromium.media.mojom.AndroidOverlayConfig;
import org.chromium.media.mojom.AndroidOverlayProvider;
import org.chromium.mojo.bindings.Interface;
import org.chromium.mojo.bindings.InterfaceRequest;
import org.chromium.mojo.system.MojoException;
import org.chromium.services.service_manager.InterfaceFactory;

@JNINamespace(value="content") public class AndroidOverlayProviderImpl implements AndroidOverlayProvider {
    class org.chromium.content.browser.androidoverlay.AndroidOverlayProviderImpl$1 implements Runnable {
        org.chromium.content.browser.androidoverlay.AndroidOverlayProviderImpl$1(AndroidOverlayProviderImpl arg1) {
            AndroidOverlayProviderImpl.this = arg1;
            super();
        }

        public void run() {
            AndroidOverlayProviderImpl.this.notifyReleased();
        }
    }

    public class Factory implements InterfaceFactory {
        private static AndroidOverlayProviderImpl sImpl;

        public Factory(Context arg1) {
            super();
        }

        public AndroidOverlayProvider createImpl() {
            if(Factory.sImpl == null) {
                Factory.sImpl = new AndroidOverlayProviderImpl();
            }

            return Factory.sImpl;
        }

        public Interface createImpl() {
            return this.createImpl();
        }
    }

    private static final int MAX_OVERLAYS = 1;
    private static final String TAG = "AndroidOverlayProvider";
    private Handler mHandler;
    private Runnable mNotifyReleasedRunnable;
    private int mNumOverlays;
    private HandlerThread mOverlayUiThread;

    static {
    }

    public AndroidOverlayProviderImpl() {
        super();
        this.mNotifyReleasedRunnable = new org.chromium.content.browser.androidoverlay.AndroidOverlayProviderImpl$1(this);
    }

    static void access$000(AndroidOverlayProviderImpl arg0) {
        arg0.notifyReleased();
    }

    @CalledByNative private static boolean areOverlaysSupported() {
        return 1;
    }

    public void close() {
    }

    public void createOverlay(InterfaceRequest arg8, AndroidOverlayClient arg9, AndroidOverlayConfig arg10) {
        ThreadUtils.assertOnUiThread();
        if(this.mNumOverlays >= 1) {
            arg9.onDestroyed();
            arg9.close();
            return;
        }

        this.startThreadIfNeeded();
        ++this.mNumOverlays;
        DialogOverlayImpl.MANAGER.bind(new DialogOverlayImpl(arg9, arg10, this.mHandler, this.mNotifyReleasedRunnable, false), arg8);
    }

    private void notifyReleased() {
        ThreadUtils.assertOnUiThread();
        --this.mNumOverlays;
    }

    public void onConnectionError(MojoException arg1) {
    }

    private void startThreadIfNeeded() {
        if(this.mOverlayUiThread != null) {
            return;
        }

        this.mOverlayUiThread = new HandlerThread("AndroidOverlayThread");
        this.mOverlayUiThread.start();
        this.mHandler = new Handler(this.mOverlayUiThread.getLooper());
    }
}

