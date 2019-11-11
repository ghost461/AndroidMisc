package org.chromium.media;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.hardware.display.VirtualDisplay;
import android.media.Image;
import android.media.ImageReader$OnImageAvailableListener;
import android.media.ImageReader;
import android.media.projection.MediaProjection$Callback;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.os.Build$VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Surface;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.nio.ByteBuffer;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.ContextUtils;
import org.chromium.base.Log;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@TargetApi(value=21) @JNINamespace(value="media") public class ScreenCapture extends Fragment {
    class org.chromium.media.ScreenCapture$1 {
    }

    enum CaptureState {
        public static final enum CaptureState ALLOWED;
        public static final enum CaptureState ATTACHED;
        public static final enum CaptureState STARTED;
        public static final enum CaptureState STOPPED;
        public static final enum CaptureState STOPPING;

        static {
            CaptureState.ATTACHED = new CaptureState("ATTACHED", 0);
            CaptureState.ALLOWED = new CaptureState("ALLOWED", 1);
            CaptureState.STARTED = new CaptureState("STARTED", 2);
            CaptureState.STOPPING = new CaptureState("STOPPING", 3);
            CaptureState.STOPPED = new CaptureState("STOPPED", 4);
            CaptureState.$VALUES = new CaptureState[]{CaptureState.ATTACHED, CaptureState.ALLOWED, CaptureState.STARTED, CaptureState.STOPPING, CaptureState.STOPPED};
        }

        private CaptureState(String arg1, int arg2) {
            super(arg1, arg2);
        }

        public static CaptureState valueOf(String arg1) {
            return Enum.valueOf(CaptureState.class, arg1);
        }

        public static CaptureState[] values() {
            return CaptureState.$VALUES.clone();
        }
    }

    class CrImageReaderListener implements ImageReader$OnImageAvailableListener {
        CrImageReaderListener(ScreenCapture arg1, org.chromium.media.ScreenCapture$1 arg2) {
            this(arg1);
        }

        private CrImageReaderListener(ScreenCapture arg1) {
            ScreenCapture.this = arg1;
            super();
        }

        public void onImageAvailable(ImageReader arg25) {
            Throwable v2_2;
            Throwable v6_1;
            Image v4;
            CrImageReaderListener v1 = this;
            Object v2 = v1.this$0.mCaptureStateLock;
            __monitor_enter(v2);
            try {
                if(v1.this$0.mCaptureState != CaptureState.STARTED) {
                    Log.e("cr_ScreenCapture", "Get captured frame in unexpected state.", new Object[0]);
                    __monitor_exit(v2);
                    return;
                }

                __monitor_exit(v2);
            }
            catch(Throwable v0) {
            label_226:
                Throwable v3 = v0;
                try {
                    __monitor_exit(v2);
                }
                catch(Throwable v0) {
                    goto label_226;
                }

                throw v3;
            }

            if(v1.this$0.maybeDoRotation()) {
                v1.this$0.createImageReaderWithFormat();
                v1.this$0.createVirtualDisplay();
                return;
            }

            int v2_1 = 35;
            try {
                v4 = arg25.acquireLatestImage();
                if(v4 != null) {
                    goto label_31;
                }

                if(v4 != null) {
                    v4.close();
                }
            }
            catch(IllegalStateException v0_1) {
                goto label_217;
            }
            catch(UnsupportedOperationException v0_2) {
                goto label_195;
            }

            return;
            try {
            label_31:
                if(arg25.getWidth() == v4.getWidth()) {
                    if(arg25.getHeight() != v4.getHeight()) {
                    }
                    else {
                        int v7 = v4.getFormat();
                        if(v7 != 1) {
                            if(v7 != v2_1) {
                                Log.e("cr_ScreenCapture", "Unexpected image format: " + v4.getFormat(), new Object[0]);
                                throw new IllegalStateException();
                            }
                            else if(v4.getPlanes().length != 3) {
                                Log.e("cr_ScreenCapture", "Unexpected image planes for YUV_420_888 format: " + v4.getPlanes().length, new Object[0]);
                                throw new IllegalStateException();
                            }
                            else {
                                v1.this$0.nativeOnI420FrameAvailable(v1.this$0.mNativeScreenCaptureMachineAndroid, v4.getPlanes()[0].getBuffer(), v4.getPlanes()[0].getRowStride(), v4.getPlanes()[1].getBuffer(), v4.getPlanes()[2].getBuffer(), v4.getPlanes()[1].getRowStride(), v4.getPlanes()[1].getPixelStride(), v4.getCropRect().left, v4.getCropRect().top, v4.getCropRect().width(), v4.getCropRect().height(), v4.getTimestamp());
                            }
                        }
                        else if(v4.getPlanes().length != 1) {
                            Log.e("cr_ScreenCapture", "Unexpected image planes for RGBA_8888 format: " + v4.getPlanes().length, new Object[0]);
                            throw new IllegalStateException();
                        }
                        else {
                            v1.this$0.nativeOnRGBAFrameAvailable(v1.this$0.mNativeScreenCaptureMachineAndroid, v4.getPlanes()[0].getBuffer(), v4.getPlanes()[0].getRowStride(), v4.getCropRect().left, v4.getCropRect().top, v4.getCropRect().width(), v4.getCropRect().height(), v4.getTimestamp());
                        }

                        goto label_143;
                    }
                }

                goto label_146;
            }
            catch(Throwable v0) {
                goto label_178;
            }
            catch(Throwable v0) {
                goto label_174;
            }

        label_143:
            if(v4 != null) {
                try {
                    v4.close();
                    return;
                }
                catch(IllegalStateException v0_1) {
                    goto label_217;
                }
                catch(UnsupportedOperationException v0_2) {
                    goto label_195;
                }

                try {
                label_146:
                    Log.e("cr_ScreenCapture", "ImageReader size (" + arg25.getWidth() + "x" + arg25.getHeight() + ") did not match Image size (" + v4.getWidth() + "x" + v4.getHeight() + ")", new Object[0]);
                    throw new IllegalStateException();
                }
                catch(Throwable v0) {
                label_174:
                    v2_2 = v0;
                    v6_1 = null;
                }
                catch(Throwable v0) {
                label_178:
                    v6_1 = v0;
                    try {
                        throw v6_1;
                    }
                    catch(Throwable v0) {
                        v2_2 = v0;
                    }
                }

                if(v4 == null) {
                    goto label_190;
                }

                if(v6_1 == null) {
                    goto label_189;
                }

                try {
                    v4.close();
                    goto label_190;
                }
                catch(UnsupportedOperationException v0_2) {
                }
                catch(IllegalStateException v0_1) {
                }
                catch(Throwable v0) {
                    try {
                        ThrowableExtension.addSuppressed(v6_1, v0);
                        goto label_190;
                    label_189:
                        v4.close();
                    label_190:
                        throw v2_2;
                    }
                    catch(UnsupportedOperationException v0_2) {
                    label_195:
                        Log.i("cr_ScreenCapture", "acquireLatestImage():" + v0_2, new Object[0]);
                        if(v1.this$0.mFormat != 35) {
                            return;
                        }

                        v1.this$0.mFormat = 1;
                        v1.this$0.createImageReaderWithFormat();
                        v1.this$0.createVirtualDisplay();
                    }
                    catch(IllegalStateException v0_1) {
                    label_217:
                        Log.e("cr_ScreenCapture", "acquireLatestImage():" + v0_1, new Object[0]);
                    }
                }
            }
        }
    }

    enum DeviceOrientation {
        public static final enum DeviceOrientation LANDSCAPE;
        public static final enum DeviceOrientation PORTRAIT;

        static {
            DeviceOrientation.PORTRAIT = new DeviceOrientation("PORTRAIT", 0);
            DeviceOrientation.LANDSCAPE = new DeviceOrientation("LANDSCAPE", 1);
            DeviceOrientation.$VALUES = new DeviceOrientation[]{DeviceOrientation.PORTRAIT, DeviceOrientation.LANDSCAPE};
        }

        private DeviceOrientation(String arg1, int arg2) {
            super(arg1, arg2);
        }

        public static DeviceOrientation valueOf(String arg1) {
            return Enum.valueOf(DeviceOrientation.class, arg1);
        }

        public static DeviceOrientation[] values() {
            return DeviceOrientation.$VALUES.clone();
        }
    }

    class MediaProjectionCallback extends MediaProjection$Callback {
        MediaProjectionCallback(ScreenCapture arg1, org.chromium.media.ScreenCapture$1 arg2) {
            this(arg1);
        }

        private MediaProjectionCallback(ScreenCapture arg1) {
            ScreenCapture.this = arg1;
            super();
        }

        public void onStop() {
            ScreenCapture.this.changeCaptureStateAndNotify(CaptureState.STOPPED);
            MediaProjection v1 = null;
            ScreenCapture.this.mMediaProjection = v1;
            if(ScreenCapture.this.mVirtualDisplay == null) {
                return;
            }

            ScreenCapture.this.mVirtualDisplay.release();
            ScreenCapture.this.mVirtualDisplay = ((VirtualDisplay)v1);
        }
    }

    private static final int REQUEST_MEDIA_PROJECTION = 1;
    private static final String TAG = "cr_ScreenCapture";
    private Handler mBackgroundHandler;
    private CaptureState mCaptureState;
    private final Object mCaptureStateLock;
    private DeviceOrientation mCurrentOrientation;
    private Display mDisplay;
    private int mFormat;
    private int mHeight;
    private ImageReader mImageReader;
    private MediaProjection mMediaProjection;
    private MediaProjectionManager mMediaProjectionManager;
    private final long mNativeScreenCaptureMachineAndroid;
    private int mResultCode;
    private Intent mResultData;
    private int mScreenDensity;
    private Surface mSurface;
    private HandlerThread mThread;
    private VirtualDisplay mVirtualDisplay;
    private int mWidth;

    static {
    }

    ScreenCapture(long arg2) {
        super();
        this.mCaptureStateLock = new Object();
        this.mCaptureState = CaptureState.STOPPED;
        this.mNativeScreenCaptureMachineAndroid = arg2;
    }

    static Object access$000(ScreenCapture arg0) {
        return arg0.mCaptureStateLock;
    }

    static CaptureState access$100(ScreenCapture arg0) {
        return arg0.mCaptureState;
    }

    static MediaProjection access$1002(ScreenCapture arg0, MediaProjection arg1) {
        arg0.mMediaProjection = arg1;
        return arg1;
    }

    static VirtualDisplay access$1100(ScreenCapture arg0) {
        return arg0.mVirtualDisplay;
    }

    static VirtualDisplay access$1102(ScreenCapture arg0, VirtualDisplay arg1) {
        arg0.mVirtualDisplay = arg1;
        return arg1;
    }

    static boolean access$200(ScreenCapture arg0) {
        return arg0.maybeDoRotation();
    }

    static void access$300(ScreenCapture arg0) {
        arg0.createImageReaderWithFormat();
    }

    static void access$400(ScreenCapture arg0) {
        arg0.createVirtualDisplay();
    }

    static long access$500(ScreenCapture arg2) {
        return arg2.mNativeScreenCaptureMachineAndroid;
    }

    static void access$600(ScreenCapture arg0, long arg1, ByteBuffer arg3, int arg4, int arg5, int arg6, int arg7, int arg8, long arg9) {
        arg0.nativeOnRGBAFrameAvailable(arg1, arg3, arg4, arg5, arg6, arg7, arg8, arg9);
    }

    static void access$700(ScreenCapture arg0, long arg1, ByteBuffer arg3, int arg4, ByteBuffer arg5, ByteBuffer arg6, int arg7, int arg8, int arg9, int arg10, int arg11, int arg12, long arg13) {
        arg0.nativeOnI420FrameAvailable(arg1, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13);
    }

    static int access$800(ScreenCapture arg0) {
        return arg0.mFormat;
    }

    static int access$802(ScreenCapture arg0, int arg1) {
        arg0.mFormat = arg1;
        return arg1;
    }

    static void access$900(ScreenCapture arg0, CaptureState arg1) {
        arg0.changeCaptureStateAndNotify(arg1);
    }

    @CalledByNative public boolean allocate(int arg3, int arg4) {
        this.mWidth = arg3;
        this.mHeight = arg4;
        this.mMediaProjectionManager = ContextUtils.getApplicationContext().getSystemService("media_projection");
        if(this.mMediaProjectionManager == null) {
            Log.e("cr_ScreenCapture", "mMediaProjectionManager is null", new Object[0]);
            return 0;
        }

        this.mDisplay = ContextUtils.getApplicationContext().getSystemService("window").getDefaultDisplay();
        DisplayMetrics v3 = new DisplayMetrics();
        this.mDisplay.getMetrics(v3);
        this.mScreenDensity = v3.densityDpi;
        return 1;
    }

    private void changeCaptureStateAndNotify(CaptureState arg2) {
        Object v0 = this.mCaptureStateLock;
        __monitor_enter(v0);
        try {
            this.mCaptureState = arg2;
            this.mCaptureStateLock.notifyAll();
            __monitor_exit(v0);
            return;
        label_8:
            __monitor_exit(v0);
        }
        catch(Throwable v2) {
            goto label_8;
        }

        throw v2;
    }

    private void createImageReaderWithFormat() {
        if(this.mImageReader != null) {
            this.mImageReader.close();
        }

        this.mImageReader = ImageReader.newInstance(this.mWidth, this.mHeight, this.mFormat, 2);
        this.mSurface = this.mImageReader.getSurface();
        this.mImageReader.setOnImageAvailableListener(new CrImageReaderListener(this, null), this.mBackgroundHandler);
    }

    @CalledByNative static ScreenCapture createScreenCaptureMachine(long arg2) {
        if(Build$VERSION.SDK_INT >= 21) {
            return new ScreenCapture(arg2);
        }

        return null;
    }

    private void createVirtualDisplay() {
        if(this.mVirtualDisplay != null) {
            this.mVirtualDisplay.release();
        }

        this.mVirtualDisplay = this.mMediaProjection.createVirtualDisplay("ScreenCapture", this.mWidth, this.mHeight, this.mScreenDensity, 16, this.mSurface, null, null);
    }

    private DeviceOrientation getDeviceOrientation(int arg2) {
        if(arg2 != 0) {
            if(arg2 != 90) {
                if(arg2 == 180) {
                    goto label_11;
                }
                else if(arg2 != 270) {
                    return DeviceOrientation.LANDSCAPE;
                }
            }

            return DeviceOrientation.LANDSCAPE;
        }

    label_11:
        return DeviceOrientation.PORTRAIT;
    }

    private int getDeviceRotation() {
        switch(this.mDisplay.getRotation()) {
            case 0: {
                return 0;
            }
            case 1: {
                return 90;
            }
            case 2: {
                return 180;
            }
            case 3: {
                return 270;
            }
        }

        return 0;
    }

    private boolean maybeDoRotation() {
        int v0 = this.getDeviceRotation();
        DeviceOrientation v1 = this.getDeviceOrientation(v0);
        if(v1 == this.mCurrentOrientation) {
            return 0;
        }

        this.mCurrentOrientation = v1;
        this.rotateCaptureOrientation(v1);
        this.nativeOnOrientationChange(this.mNativeScreenCaptureMachineAndroid, v0);
        return 1;
    }

    private native void nativeOnActivityResult(long arg1, boolean arg2) {
    }

    private native void nativeOnI420FrameAvailable(long arg1, ByteBuffer arg2, int arg3, ByteBuffer arg4, ByteBuffer arg5, int arg6, int arg7, int arg8, int arg9, int arg10, int arg11, long arg12) {
    }

    private native void nativeOnOrientationChange(long arg1, int arg2) {
    }

    private native void nativeOnRGBAFrameAvailable(long arg1, ByteBuffer arg2, int arg3, int arg4, int arg5, int arg6, int arg7, long arg8) {
    }

    public void onActivityResult(int arg4, int arg5, Intent arg6) {
        boolean v0 = true;
        if(arg4 != 1) {
            return;
        }

        arg4 = -1;
        if(arg5 == arg4) {
            this.mResultCode = arg5;
            this.mResultData = arg6;
            this.changeCaptureStateAndNotify(CaptureState.ALLOWED);
        }

        long v1 = this.mNativeScreenCaptureMachineAndroid;
        if(arg5 == arg4) {
        }
        else {
            v0 = false;
        }

        this.nativeOnActivityResult(v1, v0);
    }

    public void onAttach(Activity arg2) {
        super.onAttach(arg2);
        Log.d("cr_ScreenCapture", "onAttach");
        this.changeCaptureStateAndNotify(CaptureState.ATTACHED);
    }

    public void onAttach(Context arg2) {
        super.onAttach(arg2);
        Log.d("cr_ScreenCapture", "onAttach");
        this.changeCaptureStateAndNotify(CaptureState.ATTACHED);
    }

    public void onDetach() {
        super.onDetach();
        Log.d("cr_ScreenCapture", "onDetach");
        this.stopCapture();
    }

    private void rotateCaptureOrientation(DeviceOrientation arg3) {
        if(arg3 == DeviceOrientation.LANDSCAPE && this.mWidth < this.mHeight || arg3 == DeviceOrientation.PORTRAIT && this.mHeight < this.mWidth) {
            int v3 = this.mWidth;
            int v0 = this.mHeight;
            int v1 = this.mWidth;
            this.mHeight = v1;
            this.mWidth = v3 + (v0 - v1);
        }
    }

    @CalledByNative public boolean startCapture() {
        Log.d("cr_ScreenCapture", "startCapture");
        Object v0 = this.mCaptureStateLock;
        __monitor_enter(v0);
        try {
            if(this.mCaptureState != CaptureState.ALLOWED) {
                Log.e("cr_ScreenCapture", "startCapture() invoked without user permission.", new Object[0]);
                __monitor_exit(v0);
                return 0;
            }

            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            try {
            label_53:
                __monitor_exit(v0);
            }
            catch(Throwable v1) {
                goto label_53;
            }

            throw v1;
        }

        this.mMediaProjection = this.mMediaProjectionManager.getMediaProjection(this.mResultCode, this.mResultData);
        if(this.mMediaProjection == null) {
            Log.e("cr_ScreenCapture", "mMediaProjection is null", new Object[0]);
            return 0;
        }

        this.mMediaProjection.registerCallback(new MediaProjectionCallback(this, null), null);
        this.mThread = new HandlerThread("ScreenCapture");
        this.mThread.start();
        this.mBackgroundHandler = new Handler(this.mThread.getLooper());
        this.mFormat = 1;
        this.maybeDoRotation();
        this.createImageReaderWithFormat();
        this.createVirtualDisplay();
        this.changeCaptureStateAndNotify(CaptureState.STARTED);
        return 1;
    }

    @CalledByNative public boolean startPrompt() {
        Log.d("cr_ScreenCapture", "startPrompt");
        Activity v0 = ApplicationStatus.getLastTrackedFocusedActivity();
        if(v0 == null) {
            Log.e("cr_ScreenCapture", "activity is null", new Object[0]);
            return 0;
        }

        FragmentTransaction v0_1 = v0.getFragmentManager().beginTransaction();
        v0_1.add(((Fragment)this), "screencapture");
        try {
            v0_1.commit();
        }
        catch(RuntimeException v0_2) {
            Log.e("cr_ScreenCapture", "ScreenCaptureExcaption " + v0_2, new Object[0]);
            return 0;
        }

        Object v0_3 = this.mCaptureStateLock;
        __monitor_enter(v0_3);
        try {
            while(true) {
                if(this.mCaptureState == CaptureState.ATTACHED) {
                    goto label_35;
                }

                try {
                    this.mCaptureStateLock.wait();
                    continue;
                }
                catch(InterruptedException v2) {
                    try {
                        Log.e("cr_ScreenCapture", "ScreenCaptureException: " + v2, new Object[0]);
                        continue;
                    label_35:
                        __monitor_exit(v0_3);
                        break;
                    }
                    catch(Throwable v1) {
                        goto label_53;
                    }
                }
            }
        }
        catch(Throwable v1) {
            goto label_53;
        }

        try {
            this.startActivityForResult(this.mMediaProjectionManager.createScreenCaptureIntent(), 1);
            return 1;
        }
        catch(ActivityNotFoundException v0_4) {
            Log.e("cr_ScreenCapture", "ScreenCaptureException " + v0_4, new Object[0]);
            return 0;
        }

        try {
        label_53:
            __monitor_exit(v0_3);
        }
        catch(Throwable v1) {
            goto label_53;
        }

        throw v1;
    }

    @CalledByNative public void stopCapture() {
        Log.d("cr_ScreenCapture", "stopCapture");
        Object v0 = this.mCaptureStateLock;
        __monitor_enter(v0);
        try {
            if(this.mMediaProjection != null && this.mCaptureState == CaptureState.STARTED) {
                this.mMediaProjection.stop();
                this.changeCaptureStateAndNotify(CaptureState.STOPPING);
                while(true) {
                    if(this.mCaptureState != CaptureState.STOPPED) {
                        try {
                            this.mCaptureStateLock.wait();
                            continue;
                        }
                        catch(InterruptedException v1_1) {
                            try {
                                Log.e("cr_ScreenCapture", "ScreenCaptureEvent: " + v1_1, new Object[0]);
                                continue;
                            label_32:
                                this.changeCaptureStateAndNotify(CaptureState.STOPPED);
                            label_34:
                                __monitor_exit(v0);
                                return;
                            label_37:
                                __monitor_exit(v0);
                                goto label_38;
                            }
                            catch(Throwable v1) {
                                goto label_37;
                            }
                        }
                    }
                    else {
                        goto label_34;
                    }

                    goto label_32;
                }
            }

            goto label_32;
        }
        catch(Throwable v1) {
            goto label_37;
        }

    label_38:
        throw v1;
    }
}

