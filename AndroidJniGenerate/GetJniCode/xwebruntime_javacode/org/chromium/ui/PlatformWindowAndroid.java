package org.chromium.ui;

import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder$Callback;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import org.chromium.base.ContextUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="ui") public class PlatformWindowAndroid extends SurfaceView {
    private final PlatformImeControllerAndroid mImeController;
    private long mNativeMojoViewport;
    private final SurfaceHolder$Callback mSurfaceCallback;

    static {
    }

    private PlatformWindowAndroid(long arg2, long arg4) {
        super(ContextUtils.getApplicationContext());
        this.setFocusable(true);
        this.setFocusableInTouchMode(true);
        this.mNativeMojoViewport = arg2;
        this.mSurfaceCallback = new SurfaceHolder$Callback(ContextUtils.getApplicationContext().getResources().getDisplayMetrics().density) {
            public void surfaceChanged(SurfaceHolder arg2, int arg3, int arg4, int arg5) {
                PlatformWindowAndroid.access$100(PlatformWindowAndroid.access$000(PlatformWindowAndroid.this), arg4, arg5, this.val$density);
            }

            public void surfaceCreated(SurfaceHolder arg4) {
                PlatformWindowAndroid.access$200(PlatformWindowAndroid.access$000(PlatformWindowAndroid.this), arg4.getSurface(), this.val$density);
            }

            public void surfaceDestroyed(SurfaceHolder arg3) {
                PlatformWindowAndroid.access$300(PlatformWindowAndroid.access$000(PlatformWindowAndroid.this));
            }
        };
        this.getHolder().addCallback(this.mSurfaceCallback);
        this.mImeController = new PlatformImeControllerAndroid(this, arg4);
    }

    static long access$000(PlatformWindowAndroid arg2) {
        return arg2.mNativeMojoViewport;
    }

    static void access$100(long arg0, int arg2, int arg3, float arg4) {
        PlatformWindowAndroid.nativeSurfaceSetSize(arg0, arg2, arg3, arg4);
    }

    static void access$200(long arg0, Surface arg2, float arg3) {
        PlatformWindowAndroid.nativeSurfaceCreated(arg0, arg2, arg3);
    }

    static void access$300(long arg0) {
        PlatformWindowAndroid.nativeSurfaceDestroyed(arg0);
    }

    @CalledByNative public static PlatformWindowAndroid createForActivity(long arg1, long arg3) {
        PlatformWindowAndroid v0 = new PlatformWindowAndroid(arg1, arg3);
        ContextUtils.getApplicationContext().setContentView(((View)v0));
        return v0;
    }

    @CalledByNative public void detach() {
        this.getHolder().removeCallback(this.mSurfaceCallback);
        this.mNativeMojoViewport = 0;
    }

    public boolean dispatchKeyEvent(KeyEvent arg2) {
        if(this.privateDispatchKeyEvent(arg2)) {
            return 1;
        }

        return super.dispatchKeyEvent(arg2);
    }

    public boolean dispatchKeyEventPreIme(KeyEvent arg2) {
        if(this.privateDispatchKeyEvent(arg2)) {
            return 1;
        }

        return super.dispatchKeyEventPreIme(arg2);
    }

    public boolean dispatchKeyShortcutEvent(KeyEvent arg2) {
        if(this.privateDispatchKeyEvent(arg2)) {
            return 1;
        }

        return super.dispatchKeyShortcutEvent(arg2);
    }

    private static native void nativeDestroy(long arg0) {
    }

    private static native boolean nativeKeyEvent(long arg0, boolean arg1, int arg2, int arg3) {
    }

    private static native void nativeSurfaceCreated(long arg0, Surface arg1, float arg2) {
    }

    private static native void nativeSurfaceDestroyed(long arg0) {
    }

    private static native void nativeSurfaceSetSize(long arg0, int arg1, int arg2, float arg3) {
    }

    private static native boolean nativeTouchEvent(long arg0, long arg1, int arg2, int arg3, float arg4, float arg5, float arg6, float arg7, float arg8, float arg9, float arg10, float arg11) {
    }

    private boolean notifyTouchEventAtIndex(MotionEvent arg19, int arg20) {
        float v12;
        float v13;
        MotionEvent v0 = arg19;
        int v1 = arg20;
        float v2 = arg19.getTouchMajor(arg20);
        float v3 = arg19.getTouchMinor(arg20);
        if(v2 < v3) {
            v13 = v2;
            v12 = v3;
        }
        else {
            v12 = v2;
            v13 = v3;
        }

        return PlatformWindowAndroid.nativeTouchEvent(this.mNativeMojoViewport, arg19.getEventTime(), arg19.getActionMasked(), arg19.getPointerId(arg20), arg19.getX(arg20), arg19.getY(arg20), arg19.getPressure(arg20), v12, v13, arg19.getOrientation(arg20), v0.getAxisValue(10, v1), v0.getAxisValue(9, v1));
    }

    public boolean onCheckIsTextEditor() {
        return this.mImeController.isTextEditorType();
    }

    public InputConnection onCreateInputConnection(EditorInfo arg2) {
        return this.mImeController.onCreateInputConnection(arg2);
    }

    public boolean onTouchEvent(MotionEvent arg5) {
        int v0 = arg5.getActionMasked();
        if(v0 != 5) {
            if(v0 == 6) {
            }
            else {
                v0 = arg5.getPointerCount();
                int v1 = 0;
                int v2 = 0;
                while(v1 < v0) {
                    v2 |= this.notifyTouchEventAtIndex(arg5, v1);
                    ++v1;
                }

                return ((boolean)v2);
            }
        }

        return this.notifyTouchEventAtIndex(arg5, arg5.getActionIndex());
    }

    protected void onWindowVisibilityChanged(int arg1) {
        super.onWindowVisibilityChanged(arg1);
        if(arg1 == 0) {
            this.requestFocusFromTouch();
            this.requestFocus();
        }
    }

    private boolean privateDispatchKeyEvent(KeyEvent arg9) {
        int v3;
        int v0;
        boolean v1 = true;
        if(arg9.getAction() == 2) {
            if(arg9.getKeyCode() != 0 || arg9.getCharacters() == null) {
                v0 = 0;
                v3 = 0;
                while(v0 < arg9.getRepeatCount()) {
                    v3 = v3 | PlatformWindowAndroid.nativeKeyEvent(this.mNativeMojoViewport, true, arg9.getKeyCode(), arg9.getUnicodeChar()) | PlatformWindowAndroid.nativeKeyEvent(this.mNativeMojoViewport, false, arg9.getKeyCode(), arg9.getUnicodeChar());
                    ++v0;
                }
            }
            else {
                String v9 = arg9.getCharacters();
                v0 = 0;
                v3 = 0;
                while(v0 < v9.length()) {
                    char v4 = v9.charAt(v0);
                    if(v4 >= 0xD800 && v4 < 0xE000) {
                        ++v0;
                        int v4_1 = Character.toCodePoint(v4, v9.charAt(v0));
                    }

                    v3 = v3 | PlatformWindowAndroid.nativeKeyEvent(this.mNativeMojoViewport, true, 0, v4) | PlatformWindowAndroid.nativeKeyEvent(this.mNativeMojoViewport, false, 0, v4);
                    ++v0;
                }
            }

            return ((boolean)v3);
        }

        long v3_1 = this.mNativeMojoViewport;
        if(arg9.getAction() == 0) {
        }
        else {
            v1 = false;
        }

        return PlatformWindowAndroid.nativeKeyEvent(v3_1, v1, arg9.getKeyCode(), arg9.getUnicodeChar());
    }
}

