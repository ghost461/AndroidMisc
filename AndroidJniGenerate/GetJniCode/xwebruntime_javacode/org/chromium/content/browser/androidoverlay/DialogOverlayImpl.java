package org.chromium.content.browser.androidoverlay;

import android.content.Context;
import android.os.Handler;
import android.os.IBinder;
import android.view.Surface;
import org.chromium.base.ContextUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.gfx.mojom.Rect;
import org.chromium.media.mojom.AndroidOverlay;
import org.chromium.media.mojom.AndroidOverlayClient;
import org.chromium.media.mojom.AndroidOverlayConfig;
import org.chromium.mojo.system.MojoException;

@JNINamespace(value="content") public class DialogOverlayImpl implements Host, AndroidOverlay {
    private static final String TAG = "DialogOverlayImpl";
    private AndroidOverlayClient mClient;
    private boolean mClosed;
    private final int[] mCompositorOffset;
    private DialogOverlayCore mDialogCore;
    private final ThreadHoppingHost mHoppingHost;
    private long mNativeHandle;
    private Handler mOverlayHandler;
    private Runnable mReleaseCoreRunnable;
    private Runnable mReleasedRunnable;
    private int mSurfaceId;

    static {
    }

    public DialogOverlayImpl(AndroidOverlayClient arg7, AndroidOverlayConfig arg8, Handler arg9, Runnable arg10, boolean arg11) {
        super();
        this.mCompositorOffset = new int[2];
        ThreadUtils.assertOnUiThread();
        this.mClient = arg7;
        this.mReleasedRunnable = arg10;
        this.mOverlayHandler = arg9;
        this.mDialogCore = new DialogOverlayCore();
        this.mHoppingHost = new ThreadHoppingHost(((Host)this));
        this.mNativeHandle = this.nativeInit(arg8.routingToken.high, arg8.routingToken.low, arg8.powerEfficient);
        if(this.mNativeHandle == 0) {
            this.mClient.onDestroyed();
            this.cleanup();
            return;
        }

        DialogOverlayCore v7 = this.mDialogCore;
        Context v3 = ContextUtils.getApplicationContext();
        this.nativeGetCompositorOffset(this.mNativeHandle, arg8.rect);
        this.mOverlayHandler.post(new Runnable(v7, v3, arg8, arg11) {
            public void run() {
                this.val$dialogCore.initialize(this.val$context, this.val$config, DialogOverlayImpl.access$000(DialogOverlayImpl.this), this.val$asPanel);
                ThreadUtils.postOnUiThread(new Runnable() {
                    public void run() {
                        if(DialogOverlayImpl.access$100(this.this$1.this$0) != 0) {
                            DialogOverlayImpl.access$200(this.this$1.this$0, DialogOverlayImpl.access$100(this.this$1.this$0));
                        }
                    }
                });
            }
        });
        this.mReleaseCoreRunnable = new Runnable(v7) {
            public void run() {
                this.val$dialogCore.release();
            }
        };
    }

    static ThreadHoppingHost access$000(DialogOverlayImpl arg0) {
        return arg0.mHoppingHost;
    }

    static long access$100(DialogOverlayImpl arg2) {
        return arg2.mNativeHandle;
    }

    static void access$200(DialogOverlayImpl arg0, long arg1) {
        arg0.nativeCompleteInit(arg1);
    }

    private void cleanup() {
        ThreadUtils.assertOnUiThread();
        if(this.mSurfaceId != 0) {
            DialogOverlayImpl.nativeUnregisterSurface(this.mSurfaceId);
            this.mSurfaceId = 0;
        }

        long v2 = 0;
        if(this.mNativeHandle != v2) {
            this.nativeDestroy(this.mNativeHandle);
            this.mNativeHandle = v2;
        }

        DialogOverlayCore v0 = null;
        this.mDialogCore = v0;
        if(this.mClient != null) {
            this.mClient.close();
        }

        this.mClient = ((AndroidOverlayClient)v0);
    }

    public void close() {
        ThreadUtils.assertOnUiThread();
        if(this.mClosed) {
            return;
        }

        this.mClosed = true;
        this.mHoppingHost.onClose();
        if(this.mReleaseCoreRunnable != null) {
            this.mOverlayHandler.post(this.mReleaseCoreRunnable);
            this.mReleaseCoreRunnable = null;
            this.cleanup();
        }

        this.mReleasedRunnable.run();
    }

    public void enforceClose() {
        this.close();
    }

    private native void nativeCompleteInit(long arg1) {
    }

    private native void nativeDestroy(long arg1) {
    }

    private native void nativeGetCompositorOffset(long arg1, Rect arg2) {
    }

    private native long nativeInit(long arg1, long arg2, boolean arg3) {
    }

    static native Surface nativeLookupSurfaceForTesting(int arg0) {
    }

    private static native int nativeRegisterSurface(Surface arg0) {
    }

    private static native void nativeUnregisterSurface(int arg0) {
    }

    public void onConnectionError(MojoException arg1) {
        ThreadUtils.assertOnUiThread();
        this.close();
    }

    @CalledByNative public void onDismissed() {
        ThreadUtils.assertOnUiThread();
        if(this.mClient != null) {
            this.mClient.onDestroyed();
        }

        this.sendWindowTokenToCore(null);
        this.cleanup();
    }

    public void onOverlayDestroyed() {
        ThreadUtils.assertOnUiThread();
        if(this.mDialogCore == null) {
            return;
        }

        if(this.mClient != null) {
            this.mClient.onDestroyed();
        }

        this.cleanup();
    }

    @CalledByNative private void onPowerEfficientState(boolean arg2) {
        ThreadUtils.assertOnUiThread();
        if(this.mDialogCore == null) {
            return;
        }

        if(this.mClient == null) {
            return;
        }

        this.mClient.onPowerEfficientState(arg2);
    }

    public void onSurfaceReady(Surface arg3) {
        ThreadUtils.assertOnUiThread();
        if(this.mDialogCore != null) {
            if(this.mClient == null) {
            }
            else {
                this.mSurfaceId = DialogOverlayImpl.nativeRegisterSurface(arg3);
                this.mClient.onSurfaceReady(((long)this.mSurfaceId));
                return;
            }
        }
    }

    @CalledByNative public void onWindowToken(IBinder arg2) {
        ThreadUtils.assertOnUiThread();
        if(this.mDialogCore == null) {
            return;
        }

        this.sendWindowTokenToCore(arg2);
    }

    @CalledByNative private static void receiveCompositorOffset(Rect arg1, int arg2, int arg3) {
        arg1.x += arg2;
        arg1.y += arg3;
    }

    public void scheduleLayout(Rect arg4) {
        ThreadUtils.assertOnUiThread();
        if(this.mDialogCore == null) {
            return;
        }

        this.nativeGetCompositorOffset(this.mNativeHandle, arg4);
        this.mOverlayHandler.post(new Runnable(this.mDialogCore, arg4) {
            public void run() {
                this.val$dialogCore.layoutSurface(this.val$rect);
            }
        });
    }

    private void sendWindowTokenToCore(IBinder arg4) {
        ThreadUtils.assertOnUiThread();
        if(this.mDialogCore != null) {
            this.mOverlayHandler.post(new Runnable(this.mDialogCore, arg4) {
                public void run() {
                    this.val$dialogCore.onWindowToken(this.val$token);
                }
            });
        }
    }

    public void waitForClose() {
    }
}

