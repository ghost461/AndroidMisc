package org.chromium.content.browser.androidoverlay;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.IBinder;
import android.view.Surface;
import android.view.SurfaceHolder$Callback2;
import android.view.SurfaceHolder;
import android.view.WindowManager$LayoutParams;
import org.chromium.base.Log;
import org.chromium.gfx.mojom.Rect;
import org.chromium.media.mojom.AndroidOverlayConfig;

class DialogOverlayCore {
    class org.chromium.content.browser.androidoverlay.DialogOverlayCore$1 {
    }

    class Callbacks implements SurfaceHolder$Callback2 {
        Callbacks(DialogOverlayCore arg1, org.chromium.content.browser.androidoverlay.DialogOverlayCore$1 arg2) {
            this(arg1);
        }

        private Callbacks(DialogOverlayCore arg1) {
            DialogOverlayCore.this = arg1;
            super();
        }

        public void surfaceChanged(SurfaceHolder arg1, int arg2, int arg3, int arg4) {
        }

        public void surfaceCreated(SurfaceHolder arg2) {
            if(DialogOverlayCore.this.mDialog == null) {
                return;
            }

            if(DialogOverlayCore.this.mHost != null) {
                DialogOverlayCore.this.mHost.onSurfaceReady(arg2.getSurface());
            }
        }

        public void surfaceDestroyed(SurfaceHolder arg2) {
            if(DialogOverlayCore.this.mDialog != null) {
                if(DialogOverlayCore.this.mHost == null) {
                }
                else {
                    DialogOverlayCore.this.mHost.onOverlayDestroyed();
                    DialogOverlayCore.this.mHost.waitForClose();
                    DialogOverlayCore.this.mHost.enforceClose();
                    DialogOverlayCore.this.mHost = null;
                    return;
                }
            }
        }

        public void surfaceRedrawNeeded(SurfaceHolder arg1) {
        }
    }

    public interface Host {
        void enforceClose();

        void onOverlayDestroyed();

        void onSurfaceReady(Surface arg1);

        void waitForClose();
    }

    private static final String TAG = "DSCore";
    private boolean mAsPanel;
    private Dialog mDialog;
    private Callbacks mDialogCallbacks;
    private Host mHost;
    private WindowManager$LayoutParams mLayoutParams;

    public DialogOverlayCore() {
        super();
    }

    static Dialog access$000(DialogOverlayCore arg0) {
        return arg0.mDialog;
    }

    static Host access$100(DialogOverlayCore arg0) {
        return arg0.mHost;
    }

    static Host access$102(DialogOverlayCore arg0, Host arg1) {
        arg0.mHost = arg1;
        return arg1;
    }

    private void copyRectToLayoutParams(Rect arg3) {
        this.mLayoutParams.x = arg3.x;
        this.mLayoutParams.y = arg3.y;
        this.mLayoutParams.width = arg3.width;
        this.mLayoutParams.height = arg3.height;
    }

    @SuppressLint(value={"RtlHardcoded"}) private WindowManager$LayoutParams createLayoutParams(boolean arg4) {
        WindowManager$LayoutParams v0 = new WindowManager$LayoutParams();
        v0.gravity = 51;
        int v1 = this.mAsPanel ? 1000 : 1001;
        v0.type = v1;
        v0.flags = 568;
        if(arg4) {
            v0.flags |= 0x2000;
        }

        try {
            v0.getClass().getField("privateFlags").set(v0, Integer.valueOf(v0.getClass().getField("privateFlags").get(v0).intValue() | 0x40));
            return v0;
        }
        catch(ExceptionInInitializerError ) {
            return v0;
        }
    }

    private void dismissDialogQuietly() {
        if(this.mDialog != null && (this.mDialog.isShowing())) {
            try {
                this.mDialog.dismiss();
            }
            catch(Exception ) {
                Log.w("DSCore", "Failed to dismiss overlay dialog.  \"WindowLeaked\" is ignorable.", new Object[0]);
            }
        }

        this.mDialog = null;
        this.mDialogCallbacks = null;
    }

    Dialog getDialog() {
        return this.mDialog;
    }

    public void initialize(Context arg1, AndroidOverlayConfig arg2, Host arg3, boolean arg4) {
        this.mHost = arg3;
        this.mAsPanel = arg4;
        this.mDialog = new Dialog(arg1, 0x1030055);
        this.mDialog.requestWindowFeature(1);
        this.mDialog.setCancelable(false);
        this.mLayoutParams = this.createLayoutParams(arg2.secure);
        this.copyRectToLayoutParams(arg2.rect);
    }

    public void layoutSurface(Rect arg2) {
        if(this.mDialog != null) {
            if(this.mLayoutParams.token == null) {
            }
            else {
                this.copyRectToLayoutParams(arg2);
                this.mDialog.getWindow().setAttributes(this.mLayoutParams);
                return;
            }
        }
    }

    public void onWindowToken(IBinder arg3) {
        if(this.mDialog != null) {
            if(this.mHost == null) {
            }
            else {
                org.chromium.content.browser.androidoverlay.DialogOverlayCore$1 v0 = null;
                if(arg3 != null && (this.mLayoutParams.token == null || arg3 == this.mLayoutParams.token)) {
                    if(this.mLayoutParams.token == arg3) {
                        return;
                    }

                    this.mLayoutParams.token = arg3;
                    this.mDialog.getWindow().setAttributes(this.mLayoutParams);
                    this.mDialogCallbacks = new Callbacks(this, v0);
                    this.mDialog.getWindow().takeSurface(this.mDialogCallbacks);
                    this.mDialog.show();
                    return;
                }

                this.mHost.onOverlayDestroyed();
                this.mHost = ((Host)v0);
                this.dismissDialogQuietly();
                return;
            }
        }
    }

    public void release() {
        this.dismissDialogQuietly();
        this.mLayoutParams.token = null;
        this.mHost = null;
    }
}

