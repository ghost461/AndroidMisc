package org.chromium.media;

import android.annotation.TargetApi;
import android.graphics.Rect;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession$CaptureCallback;
import android.hardware.camera2.CameraCaptureSession$StateCallback;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice$StateCallback;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest$Builder;
import android.hardware.camera2.CaptureRequest$Key;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.MeteringRectangle;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.Image;
import android.media.ImageReader$OnImageAvailableListener;
import android.media.ImageReader;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Range;
import android.util.Size;
import android.util.SparseIntArray;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.chromium.base.ContextUtils;
import org.chromium.base.Log;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.JNINamespace;

@TargetApi(value=23) @JNINamespace(value="media") public class VideoCaptureCamera2 extends VideoCapture {
    class org.chromium.media.VideoCaptureCamera2$1 implements Runnable {
        static {
        }

        org.chromium.media.VideoCaptureCamera2$1(VideoCaptureCamera2 arg1) {
            VideoCaptureCamera2.this = arg1;
            super();
        }

        public void run() {
            ThreadUtils.assertOnUiThread();
            VideoCaptureCamera2.this.configureCommonCaptureSettings(VideoCaptureCamera2.this.mPreviewRequestBuilder);
            try {
                VideoCaptureCamera2.this.mPreviewSession.setRepeatingRequest(VideoCaptureCamera2.this.mPreviewRequestBuilder.build(), null, null);
            }
            catch(IllegalArgumentException v0) {
                Log.e("VideoCapture", "setRepeatingRequest: ", new Object[]{v0});
            }
        }
    }

    enum CameraState {
        public static final enum CameraState CONFIGURING;
        public static final enum CameraState OPENING;
        public static final enum CameraState STARTED;
        public static final enum CameraState STOPPED;

        static {
            CameraState.OPENING = new CameraState("OPENING", 0);
            CameraState.CONFIGURING = new CameraState("CONFIGURING", 1);
            CameraState.STARTED = new CameraState("STARTED", 2);
            CameraState.STOPPED = new CameraState("STOPPED", 3);
            CameraState.$VALUES = new CameraState[]{CameraState.OPENING, CameraState.CONFIGURING, CameraState.STARTED, CameraState.STOPPED};
        }

        private CameraState(String arg1, int arg2) {
            super(arg1, arg2);
        }

        public static CameraState valueOf(String arg1) {
            return Enum.valueOf(CameraState.class, arg1);
        }

        public static CameraState[] values() {
            return CameraState.$VALUES.clone();
        }
    }

    class CrPhotoReaderListener implements ImageReader$OnImageAvailableListener {
        private final long mCallbackId;

        CrPhotoReaderListener(VideoCaptureCamera2 arg1, long arg2) {
            VideoCaptureCamera2.this = arg1;
            super();
            this.mCallbackId = arg2;
        }

        public void onImageAvailable(ImageReader arg8) {
            Throwable v0;
            Image v8;
            try {
                v8 = arg8.acquireLatestImage();
                v0 = null;
                if(v8 != null) {
                    goto label_10;
                }
            }
            catch(IllegalStateException ) {
                goto label_53;
            }

            try {
                throw new IllegalStateException();
            label_10:
                if(v8.getFormat() != 0x100) {
                    Log.e("VideoCapture", "Unexpected image format: %d", new Object[]{Integer.valueOf(v8.getFormat())});
                    throw new IllegalStateException();
                }

                VideoCaptureCamera2.this.nativeOnPhotoTaken(VideoCaptureCamera2.this.mNativeVideoCaptureDeviceAndroid, this.mCallbackId, this.readCapturedData(v8));
                if(v8 != null) {
                    goto label_32;
                }

                goto label_33;
            }
            catch(Throwable v1) {
                goto label_44;
            }
            catch(Throwable v0) {
                goto label_9;
            }

            try {
            label_32:
                v8.close();
            }
            catch(IllegalStateException ) {
                goto label_53;
            }

        label_33:
            if(VideoCaptureCamera2.this.createPreviewObjectsAndStartPreview()) {
                return;
            }

            VideoCaptureCamera2.this.nativeOnError(VideoCaptureCamera2.this.mNativeVideoCaptureDeviceAndroid, "Error restarting preview");
            return;
        label_44:
            if(v8 == null) {
                goto label_52;
            }

            if(v0 == null) {
                goto label_51;
            }

            try {
                v8.close();
                goto label_52;
            }
            catch(IllegalStateException ) {
            label_53:
                VideoCaptureCamera2.this.notifyTakePhotoError(this.mCallbackId);
                return;
            }
            catch(Throwable v8_1) {
                try {
                    ThrowableExtension.addSuppressed(v0, v8_1);
                    goto label_52;
                label_51:
                    v8.close();
                label_52:
                    throw v1;
                }
                catch(IllegalStateException ) {
                    goto label_53;
                }

            label_9:
                try {
                    throw v0;
                }
                catch(Throwable v1) {
                    goto label_44;
                }
            }
        }

        private byte[] readCapturedData(Image arg4) {
            byte[] v0;
            ByteBuffer v4;
            byte[] v1 = null;
            try {
                return arg4.getPlanes()[0].getBuffer().array();
            }
            catch(Throwable ) {
                return v1;
            }
            catch(UnsupportedOperationException ) {
                try {
                    v4 = arg4.getPlanes()[0].getBuffer();
                    v0 = new byte[v4.remaining()];
                }
                catch(Throwable ) {
                    return v1;
                }

                try {
                    v4.get(v0);
                    return v0;
                }
                catch(Throwable ) {
                    return v0;
                }
            }
        }
    }

    class CrPhotoSessionListener extends CameraCaptureSession$StateCallback {
        private final long mCallbackId;
        private final CaptureRequest mPhotoRequest;

        CrPhotoSessionListener(VideoCaptureCamera2 arg1, CaptureRequest arg2, long arg3) {
            VideoCaptureCamera2.this = arg1;
            super();
            this.mPhotoRequest = arg2;
            this.mCallbackId = arg3;
        }

        public void onConfigureFailed(CameraCaptureSession arg3) {
            Log.e("VideoCapture", "failed configuring capture session", new Object[0]);
            VideoCaptureCamera2.this.notifyTakePhotoError(this.mCallbackId);
        }

        public void onConfigured(CameraCaptureSession arg3) {
            Log.d("VideoCapture", "CrPhotoSessionListener.onConfigured");
            try {
                arg3.capture(this.mPhotoRequest, null, null);
                return;
            }
            catch(CameraAccessException ) {
                Log.e("VideoCapture", "capture() error", new Object[0]);
                VideoCaptureCamera2.this.notifyTakePhotoError(this.mCallbackId);
                return;
            }
        }
    }

    class CrPreviewReaderListener implements ImageReader$OnImageAvailableListener {
        CrPreviewReaderListener(VideoCaptureCamera2 arg1, org.chromium.media.VideoCaptureCamera2$1 arg2) {
            this(arg1);
        }

        private CrPreviewReaderListener(VideoCaptureCamera2 arg1) {
            VideoCaptureCamera2.this = arg1;
            super();
        }

        public void onImageAvailable(ImageReader arg23) {
            Throwable v5;
            Image v4;
            CrPreviewReaderListener v1 = this;
            try {
                v4 = arg23.acquireLatestImage();
                v5 = null;
                if(v4 != null) {
                    goto label_9;
                }

                if(v4 != null) {
                    v4.close();
                }
            }
            catch(IllegalStateException v0) {
                goto label_124;
            }

            return;
            try {
            label_9:
                if(v4.getFormat() == 35) {
                    if(v4.getPlanes().length != 3) {
                    }
                    else {
                        if(arg23.getWidth() == v4.getWidth()) {
                            if(arg23.getHeight() != v4.getHeight()) {
                            }
                            else {
                                v1.this$0.nativeOnI420FrameAvailable(v1.this$0.mNativeVideoCaptureDeviceAndroid, v4.getPlanes()[0].getBuffer(), v4.getPlanes()[0].getRowStride(), v4.getPlanes()[1].getBuffer(), v4.getPlanes()[2].getBuffer(), v4.getPlanes()[1].getRowStride(), v4.getPlanes()[1].getPixelStride(), v4.getWidth(), v4.getHeight(), v1.this$0.getCameraRotation(), v4.getTimestamp());
                                if(v4 != null) {
                                    goto label_53;
                                }
                                else {
                                    return;
                                }
                            }
                        }

                        goto label_55;
                    }
                }

                goto label_83;
            }
            catch(Throwable v0_1) {
                goto label_103;
            }
            catch(Throwable v0_1) {
                goto label_107;
            }

            try {
            label_53:
                v4.close();
                return;
            }
            catch(IllegalStateException v0) {
                goto label_124;
            }

            try {
            label_55:
                VideoCaptureCamera2 v6 = v1.this$0;
                long v7 = v1.this$0.mNativeVideoCaptureDeviceAndroid;
                v6.nativeOnError(v7, "ImageReader size (" + arg23.getWidth() + "x" + arg23.getHeight() + ") did not match Image size (" + v4.getWidth() + "x" + v4.getHeight() + ")");
                throw new IllegalStateException();
            label_83:
                v6 = v1.this$0;
                v7 = v1.this$0.mNativeVideoCaptureDeviceAndroid;
                v6.nativeOnError(v7, "Unexpected image format: " + v4.getFormat() + " or #planes: " + v4.getPlanes().length);
                throw new IllegalStateException();
            }
            catch(Throwable v0_1) {
            }
            catch(Throwable v0_1) {
            label_107:
                v5 = v0_1;
                try {
                    throw v5;
                }
                catch(Throwable v0_1) {
                label_103:
                    Throwable v6_1 = v5;
                    v5 = v0_1;
                    if(v4 == null) {
                        goto label_117;
                    }

                    if(v6_1 == null) {
                        goto label_116;
                    }

                    try {
                        v4.close();
                        goto label_117;
                    }
                    catch(IllegalStateException v0) {
                    }
                    catch(Throwable v0_1) {
                        try {
                            ThrowableExtension.addSuppressed(v6_1, v0_1);
                            goto label_117;
                        label_116:
                            v4.close();
                        label_117:
                            throw v5;
                        }
                        catch(IllegalStateException v0) {
                        label_124:
                            Log.e("VideoCapture", "acquireLatestImage():", new Object[]{v0});
                        }
                    }
                }
            }
        }
    }

    class CrPreviewSessionListener extends CameraCaptureSession$StateCallback {
        private final CaptureRequest mPreviewRequest;

        CrPreviewSessionListener(VideoCaptureCamera2 arg1, CaptureRequest arg2) {
            VideoCaptureCamera2.this = arg1;
            super();
            this.mPreviewRequest = arg2;
        }

        public void onConfigureFailed(CameraCaptureSession arg4) {
            VideoCaptureCamera2.this.changeCameraStateAndNotify(CameraState.STOPPED);
            VideoCaptureCamera2.this.nativeOnError(VideoCaptureCamera2.this.mNativeVideoCaptureDeviceAndroid, "Camera session configuration error");
        }

        public void onConfigured(CameraCaptureSession arg5) {
            Log.d("VideoCapture", "CrPreviewSessionListener.onConfigured");
            VideoCaptureCamera2.this.mPreviewSession = arg5;
            try {
                VideoCaptureCamera2.this.mPreviewSession.setRepeatingRequest(this.mPreviewRequest, new CameraCaptureSession$CaptureCallback() {
                    public void onCaptureCompleted(CameraCaptureSession arg3, CaptureRequest arg4, TotalCaptureResult arg5) {
                        Object v3 = arg5.get(CaptureResult.SENSOR_EXPOSURE_TIME);
                        if(v3 == null) {
                            return;
                        }

                        this.this$1.this$0.mLastExposureTimeNs = ((Long)v3).longValue();
                    }
                }, null);
            }
            catch(IllegalArgumentException v5) {
                Log.e("VideoCapture", "setRepeatingRequest: ", new Object[]{v5});
                return;
            }

            VideoCaptureCamera2.this.nativeOnStarted(VideoCaptureCamera2.this.mNativeVideoCaptureDeviceAndroid);
            VideoCaptureCamera2.this.changeCameraStateAndNotify(CameraState.STARTED);
        }
    }

    class CrStateListener extends CameraDevice$StateCallback {
        CrStateListener(VideoCaptureCamera2 arg1, org.chromium.media.VideoCaptureCamera2$1 arg2) {
            this(arg1);
        }

        private CrStateListener(VideoCaptureCamera2 arg1) {
            VideoCaptureCamera2.this = arg1;
            super();
        }

        public void onDisconnected(CameraDevice arg2) {
            arg2.close();
            VideoCaptureCamera2.this.mCameraDevice = null;
            VideoCaptureCamera2.this.changeCameraStateAndNotify(CameraState.STOPPED);
        }

        public void onError(CameraDevice arg5, int arg6) {
            arg5.close();
            VideoCaptureCamera2.this.mCameraDevice = null;
            VideoCaptureCamera2.this.changeCameraStateAndNotify(CameraState.STOPPED);
            VideoCaptureCamera2 v5 = VideoCaptureCamera2.this;
            long v0 = VideoCaptureCamera2.this.mNativeVideoCaptureDeviceAndroid;
            v5.nativeOnError(v0, "Camera device error " + Integer.toString(arg6));
        }

        public void onOpened(CameraDevice arg4) {
            VideoCaptureCamera2.this.mCameraDevice = arg4;
            VideoCaptureCamera2.this.changeCameraStateAndNotify(CameraState.CONFIGURING);
            if(VideoCaptureCamera2.this.createPreviewObjectsAndStartPreview()) {
                return;
            }

            VideoCaptureCamera2.this.changeCameraStateAndNotify(CameraState.STOPPED);
            VideoCaptureCamera2.this.nativeOnError(VideoCaptureCamera2.this.mNativeVideoCaptureDeviceAndroid, "Error configuring camera");
        }
    }

    private static final SparseIntArray COLOR_TEMPERATURES_MAP = null;
    private static final String TAG = "VideoCapture";
    private static final double kNanosecondsPerSecond = 1000000000;
    private Range mAeFpsRange;
    private MeteringRectangle mAreaOfInterest;
    private CameraDevice mCameraDevice;
    private CameraState mCameraState;
    private final Object mCameraStateLock;
    private int mColorTemperature;
    private Rect mCropRect;
    private int mExposureCompensation;
    private int mExposureMode;
    private int mFillLightMode;
    private int mFocusMode;
    private ImageReader mImageReader;
    private int mIso;
    private long mLastExposureTimeNs;
    private final Looper mLooper;
    private Handler mMainHandler;
    private float mMaxZoom;
    private int mPhotoHeight;
    private int mPhotoWidth;
    private CaptureRequest mPreviewRequest;
    private CaptureRequest$Builder mPreviewRequestBuilder;
    private CameraCaptureSession mPreviewSession;
    private final Runnable mReconfigureCaptureTask;
    private boolean mRedEyeReduction;
    private boolean mTorch;
    private int mWhiteBalanceMode;

    static {
        VideoCaptureCamera2.COLOR_TEMPERATURES_MAP = new SparseIntArray();
        VideoCaptureCamera2.COLOR_TEMPERATURES_MAP.append(2850, 2);
        VideoCaptureCamera2.COLOR_TEMPERATURES_MAP.append(2950, 4);
        VideoCaptureCamera2.COLOR_TEMPERATURES_MAP.append(4250, 3);
        VideoCaptureCamera2.COLOR_TEMPERATURES_MAP.append(4600, 7);
        VideoCaptureCamera2.COLOR_TEMPERATURES_MAP.append(5000, 5);
        VideoCaptureCamera2.COLOR_TEMPERATURES_MAP.append(6000, 6);
        VideoCaptureCamera2.COLOR_TEMPERATURES_MAP.append(7000, 8);
    }

    VideoCaptureCamera2(int arg1, long arg2) {
        super(arg1, arg2);
        this.mReconfigureCaptureTask = new org.chromium.media.VideoCaptureCamera2$1(this);
        this.mCameraStateLock = new Object();
        this.mImageReader = null;
        this.mCameraState = CameraState.STOPPED;
        this.mMaxZoom = 1f;
        this.mCropRect = new Rect();
        this.mFocusMode = 4;
        this.mExposureMode = 4;
        this.mWhiteBalanceMode = 4;
        this.mColorTemperature = -1;
        this.mFillLightMode = 1;
        this.mLooper = Looper.myLooper();
        CameraCharacteristics v1 = VideoCaptureCamera2.getCameraCharacteristics(arg1);
        if(v1 != null) {
            this.mMaxZoom = v1.get(CameraCharacteristics.SCALER_AVAILABLE_MAX_DIGITAL_ZOOM).floatValue();
        }
    }

    static CameraDevice access$002(VideoCaptureCamera2 arg0, CameraDevice arg1) {
        arg0.mCameraDevice = arg1;
        return arg1;
    }

    static void access$100(VideoCaptureCamera2 arg0, CameraState arg1) {
        arg0.changeCameraStateAndNotify(arg1);
    }

    static boolean access$200(VideoCaptureCamera2 arg0) {
        return arg0.createPreviewObjectsAndStartPreview();
    }

    static CameraCaptureSession access$300(VideoCaptureCamera2 arg0) {
        return arg0.mPreviewSession;
    }

    static CameraCaptureSession access$302(VideoCaptureCamera2 arg0, CameraCaptureSession arg1) {
        arg0.mPreviewSession = arg1;
        return arg1;
    }

    static long access$402(VideoCaptureCamera2 arg0, long arg1) {
        arg0.mLastExposureTimeNs = arg1;
        return arg1;
    }

    static void access$500(VideoCaptureCamera2 arg0, long arg1) {
        arg0.notifyTakePhotoError(arg1);
    }

    static CaptureRequest$Builder access$600(VideoCaptureCamera2 arg0) {
        return arg0.mPreviewRequestBuilder;
    }

    static void access$700(VideoCaptureCamera2 arg0, CaptureRequest$Builder arg1) {
        arg0.configureCommonCaptureSettings(arg1);
    }

    public boolean allocate(int arg19, int arg20, int arg21) {
        Throwable v2_1;
        boolean v6;
        VideoCaptureCamera2 v1 = this;
        int v2 = arg21;
        Log.d("VideoCapture", "allocate: requested (%d x %d) @%dfps", Integer.valueOf(arg19), Integer.valueOf(arg20), Integer.valueOf(arg21));
        Object v3 = v1.mCameraStateLock;
        __monitor_enter(v3);
        try {
            v6 = false;
            if(v1.mCameraState != CameraState.OPENING) {
                if(v1.mCameraState == CameraState.CONFIGURING) {
                }
                else {
                    __monitor_exit(v3);
                    goto label_19;
                }
            }

            goto label_106;
        }
        catch(Throwable v0) {
            goto label_113;
        }

    label_19:
        CameraCharacteristics v3_1 = VideoCaptureCamera2.getCameraCharacteristics(v1.mId);
        int v5 = 35;
        Size v4 = VideoCaptureCamera2.findClosestSizeInArray(v3_1.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP).getOutputSizes(v5), arg19, arg20);
        if(v4 == null) {
            Log.e("VideoCapture", "No supported resolutions.", new Object[0]);
            return 0;
        }
        else {
            List v7 = Arrays.asList(v3_1.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES));
            if(v7.isEmpty()) {
                Log.e("VideoCapture", "No supported framerate ranges.", new Object[0]);
                return 0;
            }
            else {
                ArrayList v8 = new ArrayList(v7.size());
                int v10 = 1000;
                if(v7.get(0).getUpper().intValue() > v10) {
                    v10 = 1;
                }

                Iterator v7_1 = v7.iterator();
                while(v7_1.hasNext()) {
                    Object v9 = v7_1.next();
                    ((List)v8).add(new FramerateRange(((Range)v9).getLower().intValue() * v10, ((Range)v9).getUpper().intValue() * v10));
                }

                FramerateRange v7_2 = VideoCaptureCamera2.getClosestFramerateRange(((List)v8), v2 * 1000);
                v1.mAeFpsRange = new Range(Integer.valueOf(v7_2.min / v10), Integer.valueOf(v7_2.max / v10));
                Log.d("VideoCapture", "allocate: matched (%d x %d) @[%d - %d]", Integer.valueOf(v4.getWidth()), Integer.valueOf(v4.getHeight()), v1.mAeFpsRange.getLower(), v1.mAeFpsRange.getUpper());
                v1.mCaptureFormat = new VideoCaptureFormat(v4.getWidth(), v4.getHeight(), v2, v5);
                v1.mCameraNativeOrientation = v3_1.get(CameraCharacteristics.SENSOR_ORIENTATION).intValue();
                if(v3_1.get(CameraCharacteristics.LENS_FACING).intValue() == 1) {
                    v6 = true;
                }

                v1.mInvertDeviceOrientationReadings = v6;
                return 1;
            }
        }

        try {
        label_106:
            Log.e("VideoCapture", "allocate() invoked while Camera is busy opening/configuring.", new Object[0]);
            __monitor_exit(v3);
            return 0;
        label_113:
            v2_1 = v0;
            __monitor_exit(v3);
        }
        catch(Throwable v0) {
            goto label_113;
        }

        throw v2_1;
    }

    private void changeCameraStateAndNotify(CameraState arg2) {
        Object v0 = this.mCameraStateLock;
        __monitor_enter(v0);
        try {
            this.mCameraState = arg2;
            this.mCameraStateLock.notifyAll();
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

    private void configureCommonCaptureSettings(CaptureRequest$Builder arg18) {
        int v8;
        CaptureRequest$Key v3_1;
        VideoCaptureCamera2 v0 = this;
        CaptureRequest$Builder v1 = arg18;
        CameraCharacteristics v2 = VideoCaptureCamera2.getCameraCharacteristics(v0.mId);
        int v4 = 2;
        int v5 = 4;
        if(v0.mFocusMode == v5) {
            v1.set(CaptureRequest.CONTROL_AF_MODE, Integer.valueOf(v5));
            v1.set(CaptureRequest.CONTROL_AF_TRIGGER, Integer.valueOf(0));
        }
        else if(v0.mFocusMode == v4) {
            v1.set(CaptureRequest.CONTROL_AF_MODE, Integer.valueOf(0));
            v1.set(CaptureRequest.CONTROL_AF_TRIGGER, Integer.valueOf(0));
        }

        if(v0.mExposureMode == 1 || v0.mExposureMode == v4) {
            v1.set(CaptureRequest.CONTROL_AE_MODE, Integer.valueOf(0));
            if(v0.mLastExposureTimeNs != 0) {
                v1.set(CaptureRequest.SENSOR_EXPOSURE_TIME, Long.valueOf(v0.mLastExposureTimeNs));
            }
            else {
                Object v3 = v2.get(CameraCharacteristics.SENSOR_INFO_EXPOSURE_TIME_RANGE);
                v1.set(CaptureRequest.SENSOR_EXPOSURE_TIME, Long.valueOf(((Range)v3).getLower().longValue() + (((Range)v3).getUpper().longValue() + ((Range)v3).getLower().longValue()) / 2));
            }
        }
        else {
            v1.set(CaptureRequest.CONTROL_MODE, Integer.valueOf(1));
            v1.set(CaptureRequest.CONTROL_AE_MODE, Integer.valueOf(1));
            v1.set(CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE, v0.mAeFpsRange);
        }

        if(v0.mTorch) {
            v3_1 = CaptureRequest.CONTROL_AE_MODE;
            v8 = v0.mExposureMode == v5 ? 1 : 0;
            v1.set(v3_1, Integer.valueOf(v8));
            v1.set(CaptureRequest.FLASH_MODE, Integer.valueOf(v4));
        }
        else {
            switch(v0.mFillLightMode) {
                case 1: {
                    v1.set(CaptureRequest.FLASH_MODE, Integer.valueOf(0));
                    break;
                }
                case 2: {
                    v3_1 = CaptureRequest.CONTROL_AE_MODE;
                    v8 = v0.mRedEyeReduction ? 4 : 2;
                    v1.set(v3_1, Integer.valueOf(v8));
                    break;
                }
                case 3: {
                    v1.set(CaptureRequest.CONTROL_AE_MODE, Integer.valueOf(3));
                    v1.set(CaptureRequest.FLASH_MODE, Integer.valueOf(1));
                    break;
                }
                default: {
                    break;
                }
            }

            v1.set(CaptureRequest.CONTROL_AE_PRECAPTURE_TRIGGER, Integer.valueOf(0));
        }

        v1.set(CaptureRequest.CONTROL_AE_EXPOSURE_COMPENSATION, Integer.valueOf(v0.mExposureCompensation));
        if(v0.mWhiteBalanceMode == v5) {
            v1.set(CaptureRequest.CONTROL_AWB_LOCK, Boolean.valueOf(false));
            v1.set(CaptureRequest.CONTROL_AWB_MODE, Integer.valueOf(1));
        }
        else if(v0.mWhiteBalanceMode == 1) {
            v1.set(CaptureRequest.CONTROL_AWB_LOCK, Boolean.valueOf(false));
            v1.set(CaptureRequest.CONTROL_AWB_MODE, Integer.valueOf(0));
        }
        else if(v0.mWhiteBalanceMode == v4) {
            v1.set(CaptureRequest.CONTROL_AWB_LOCK, Boolean.valueOf(true));
        }

        if(v0.mColorTemperature > 0) {
            int v2_1 = v0.getClosestWhiteBalance(v0.mColorTemperature, v2.get(CameraCharacteristics.CONTROL_AWB_AVAILABLE_MODES));
            Log.d("VideoCapture", " Color temperature (%d ==> %d)", Integer.valueOf(v0.mColorTemperature), Integer.valueOf(v2_1));
            if(v2_1 != -1) {
                v1.set(CaptureRequest.CONTROL_AWB_MODE, Integer.valueOf(v2_1));
            }
        }

        if(v0.mAreaOfInterest != null) {
            MeteringRectangle[] v2_2 = new MeteringRectangle[]{v0.mAreaOfInterest};
            Log.d("VideoCapture", "Area of interest %s", v0.mAreaOfInterest.toString());
            v1.set(CaptureRequest.CONTROL_AF_REGIONS, v2_2);
            v1.set(CaptureRequest.CONTROL_AE_REGIONS, v2_2);
            v1.set(CaptureRequest.CONTROL_AWB_REGIONS, v2_2);
        }

        if(!v0.mCropRect.isEmpty()) {
            v1.set(CaptureRequest.SCALER_CROP_REGION, v0.mCropRect);
        }

        if(v0.mIso > 0) {
            v1.set(CaptureRequest.SENSOR_SENSITIVITY, Integer.valueOf(v0.mIso));
        }
    }

    private boolean createPreviewObjectsAndStartPreview() {
        if(this.mCameraDevice == null) {
            return 0;
        }

        this.mImageReader = ImageReader.newInstance(this.mCaptureFormat.getWidth(), this.mCaptureFormat.getHeight(), this.mCaptureFormat.getPixelFormat(), 2);
        HandlerThread v0 = new HandlerThread("CameraPreview");
        v0.start();
        org.chromium.media.VideoCaptureCamera2$1 v3 = null;
        this.mImageReader.setOnImageAvailableListener(new CrPreviewReaderListener(this, v3), new Handler(v0.getLooper()));
        try {
            this.mPreviewRequestBuilder = this.mCameraDevice.createCaptureRequest(1);
        }
        catch(SecurityException v2) {
            Log.e("VideoCapture", "createCaptureRequest: ", new Object[]{v2});
            return 0;
        }

        if(this.mPreviewRequestBuilder == null) {
            Log.e("VideoCapture", "mPreviewRequestBuilder error", new Object[0]);
            return 0;
        }

        this.mPreviewRequestBuilder.addTarget(this.mImageReader.getSurface());
        this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_MODE, Integer.valueOf(1));
        this.mPreviewRequestBuilder.set(CaptureRequest.NOISE_REDUCTION_MODE, Integer.valueOf(1));
        this.mPreviewRequestBuilder.set(CaptureRequest.EDGE_MODE, Integer.valueOf(1));
        Object v2_1 = VideoCaptureCamera2.getCameraCharacteristics(this.mId).get(CameraCharacteristics.CONTROL_AVAILABLE_VIDEO_STABILIZATION_MODES);
        int v4 = v2_1.length;
        int v5 = 0;
        while(v5 < v4) {
            if(v2_1[v5] == 1) {
                this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_VIDEO_STABILIZATION_MODE, Integer.valueOf(1));
            }
            else {
                ++v5;
                continue;
            }

            break;
        }

        this.configureCommonCaptureSettings(this.mPreviewRequestBuilder);
        ArrayList v2_2 = new ArrayList(1);
        ((List)v2_2).add(this.mImageReader.getSurface());
        this.mPreviewRequest = this.mPreviewRequestBuilder.build();
        try {
            this.mCameraDevice.createCaptureSession(((List)v2_2), new CrPreviewSessionListener(this, this.mPreviewRequest), ((Handler)v3));
            return 1;
        }
        catch(SecurityException v2) {
            Log.e("VideoCapture", "createCaptureSession: ", new Object[]{v2});
            return 0;
        }
    }

    public void deallocate() {
        Log.d("VideoCapture", "deallocate");
    }

    private static Size findClosestSizeInArray(Size[] arg10, int arg11, int arg12) {
        Size v0 = null;
        if(arg10 == null) {
            return v0;
        }

        int v1 = arg10.length;
        int v2 = 0x7FFFFFFF;
        Size v6 = v0;
        int v4 = 0;
        int v5 = 0x7FFFFFFF;
        while(v4 < v1) {
            Size v7 = arg10[v4];
            int v8 = arg11 > 0 ? Math.abs(v7.getWidth() - arg11) : 0;
            int v9 = arg12 > 0 ? Math.abs(v7.getHeight() - arg12) : 0;
            v8 += v9;
            if(v8 < v5) {
                v6 = v7;
                v5 = v8;
            }

            ++v4;
        }

        if(v5 == v2) {
            Log.e("VideoCapture", "Couldn\'t find resolution close to (%dx%d)", new Object[]{Integer.valueOf(arg11), Integer.valueOf(arg12)});
            return v0;
        }

        return v6;
    }

    private static int findInIntArray(int[] arg2, int arg3) {
        int v0;
        for(v0 = 0; v0 < arg2.length; ++v0) {
            if(arg3 == arg2[v0]) {
                return v0;
            }
        }

        return -1;
    }

    private static CameraCharacteristics getCameraCharacteristics(int arg4) {
        Object v0 = ContextUtils.getApplicationContext().getSystemService("camera");
        try {
            return ((CameraManager)v0).getCameraCharacteristics(Integer.toString(arg4));
        }
        catch(IllegalArgumentException v4) {
            Log.e("VideoCapture", "getCameraCharacteristics: ", new Object[]{v4});
            return null;
        }
    }

    static int getCaptureApiType(int arg1) {
        CameraCharacteristics v1 = VideoCaptureCamera2.getCameraCharacteristics(arg1);
        if(v1 == null) {
            return 10;
        }

        int v0 = 7;
        switch(v1.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL).intValue()) {
            case 0: {
                return 9;
            }
            case 1: {
                return 8;
            }
            case 2: {
                return v0;
            }
        }

        return v0;
    }

    private int getClosestWhiteBalance(int arg6, int[] arg7) {
        int v0 = -1;
        int v2 = 0;
        int v1 = -1;
        int v3 = 0x7FFFFFFF;
        while(v2 < VideoCaptureCamera2.COLOR_TEMPERATURES_MAP.size()) {
            if(VideoCaptureCamera2.findInIntArray(arg7, VideoCaptureCamera2.COLOR_TEMPERATURES_MAP.valueAt(v2)) == v0) {
            }
            else {
                int v4 = Math.abs(arg6 - VideoCaptureCamera2.COLOR_TEMPERATURES_MAP.keyAt(v2));
                if(v4 >= v3) {
                }
                else {
                    v1 = VideoCaptureCamera2.COLOR_TEMPERATURES_MAP.valueAt(v2);
                    v3 = v4;
                }
            }

            ++v2;
        }

        return v1;
    }

    static VideoCaptureFormat[] getDeviceSupportedFormats(int arg19) {
        int v5;
        CameraCharacteristics v0 = VideoCaptureCamera2.getCameraCharacteristics(arg19);
        if(v0 == null) {
            return null;
        }

        Object v1 = v0.get(CameraCharacteristics.REQUEST_AVAILABLE_CAPABILITIES);
        int v2 = v1.length;
        int v4 = 0;
        while(true) {
            v5 = 1;
            if(v4 >= v2) {
                break;
            }
            else if(v1[v4] == 1) {
            }
            else {
                ++v4;
                continue;
            }

            goto label_17;
        }

        v5 = 0;
    label_17:
        ArrayList v1_1 = new ArrayList();
        Object v0_1 = v0.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
        int[] v2_1 = ((StreamConfigurationMap)v0_1).getOutputFormats();
        v4 = v2_1.length;
        int v6;
        for(v6 = 0; v6 < v4; ++v6) {
            int v7 = v2_1[v6];
            Size[] v8 = ((StreamConfigurationMap)v0_1).getOutputSizes(v7);
            if(v8 == null) {
            }
            else {
                int v9 = v8.length;
                int v10;
                for(v10 = 0; v10 < v9; ++v10) {
                    Size v11 = v8[v10];
                    double v12 = 0;
                    if(v5 != 0) {
                        long v14 = ((StreamConfigurationMap)v0_1).getOutputMinFrameDuration(v7, v11);
                        if(v14 == 0) {
                        }
                        else {
                            v12 = 1000000000 / (((double)v14));
                        }
                    }

                    v1_1.add(new VideoCaptureFormat(v11.getWidth(), v11.getHeight(), ((int)v12), v7));
                }
            }
        }

        return v1_1.toArray(new VideoCaptureFormat[v1_1.size()]);
    }

    static String getName(int arg3) {
        CameraCharacteristics v0 = VideoCaptureCamera2.getCameraCharacteristics(arg3);
        if(v0 == null) {
            return null;
        }

        int v0_1 = v0.get(CameraCharacteristics.LENS_FACING).intValue();
        StringBuilder v1 = new StringBuilder();
        v1.append("camera2 ");
        v1.append(arg3);
        v1.append(", facing ");
        String v3 = v0_1 == 0 ? "front" : "back";
        v1.append(v3);
        return v1.toString();
    }

    static int getNumberOfCameras() {
        Object v2_1;
        try {
            v2_1 = ContextUtils.getApplicationContext().getSystemService("camera");
            if(v2_1 != null) {
                goto label_7;
            }
        }
        catch(IllegalArgumentException v2) {
            Log.e("VideoCapture", "getSystemService(Context.CAMERA_SERVICE): ", new Object[]{v2});
            return 0;
        }

        return 0;
        try {
        label_7:
            return ((CameraManager)v2_1).getCameraIdList().length;
        }
        catch(AssertionError v2_2) {
            Log.e("VideoCapture", "getNumberOfCameras: getCameraIdList(): ", new Object[]{v2_2});
            return 0;
        }
    }

    public PhotoCapabilities getPhotoCapabilities() {
        int v11_1;
        int v2_1;
        int v4;
        CameraCharacteristics v0 = VideoCaptureCamera2.getCameraCharacteristics(this.mId);
        Builder v1 = new Builder();
        Object v2 = v0.get(CameraCharacteristics.SENSOR_INFO_SENSITIVITY_RANGE);
        int v3 = 0;
        if(v2 != null) {
            v4 = ((Range)v2).getLower().intValue();
            v2_1 = ((Range)v2).getUpper().intValue();
        }
        else {
            v2_1 = 0;
            v4 = 0;
        }

        v1.setMinIso(v4).setMaxIso(v2_1).setStepIso(1);
        if(this.mPreviewRequestBuilder.get(CaptureRequest.SENSOR_SENSITIVITY) != null) {
            v1.setCurrentIso(this.mPreviewRequest.get(CaptureRequest.SENSOR_SENSITIVITY).intValue());
        }

        Size[] v2_2 = v0.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP).getOutputSizes(0x100);
        int v5 = v2_2.length;
        int v6 = 0;
        int v7 = 0x7FFFFFFF;
        int v8 = 0x7FFFFFFF;
        int v9 = 0;
        int v10 = 0;
        while(v6 < v5) {
            Size v11 = v2_2[v6];
            if(v11.getWidth() < v7) {
                v7 = v11.getWidth();
            }

            if(v11.getHeight() < v8) {
                v8 = v11.getHeight();
            }

            if(v11.getWidth() > v10) {
                v10 = v11.getWidth();
            }

            if(v11.getHeight() > v9) {
                v9 = v11.getHeight();
            }

            ++v6;
        }

        v1.setMinHeight(v8).setMaxHeight(v9).setStepHeight(1);
        v1.setMinWidth(v7).setMaxWidth(v10).setStepWidth(1);
        v2_1 = this.mPhotoHeight > 0 ? this.mPhotoHeight : this.mCaptureFormat.getHeight();
        v1.setCurrentHeight(v2_1);
        v2_1 = this.mPhotoWidth > 0 ? this.mPhotoWidth : this.mCaptureFormat.getWidth();
        v1.setCurrentWidth(v2_1);
        float v2_3 = (((float)v0.get(CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE).width())) / (((float)this.mPreviewRequest.get(CaptureRequest.SCALER_CROP_REGION).width()));
        v1.setMinZoom(1).setMaxZoom(((double)this.mMaxZoom));
        v1.setCurrentZoom(((double)v2_3)).setStepZoom(0.1);
        v2 = v0.get(CameraCharacteristics.CONTROL_AF_AVAILABLE_MODES);
        v6 = 3;
        ArrayList v5_1 = new ArrayList(v6);
        v7 = v2.length;
        for(v8 = 0; true; ++v8) {
            v9 = 4;
            v10 = 2;
            if(v8 >= v7) {
                break;
            }

            v11_1 = v2[v8];
            if(v11_1 == 0) {
                v5_1.add(Integer.valueOf(v10));
            }
            else {
                if(v11_1 != 1) {
                    if(v11_1 == v10) {
                    }
                    else {
                        if(v11_1 != v6 && v11_1 != v9 && v11_1 != 5) {
                            goto label_127;
                        }

                        if(v5_1.contains(Integer.valueOf(v9))) {
                            goto label_127;
                        }

                        v5_1.add(Integer.valueOf(v9));
                        goto label_127;
                    }
                }

                if(v5_1.contains(Integer.valueOf(v6))) {
                    goto label_127;
                }

                v5_1.add(Integer.valueOf(v6));
            }

        label_127:
        }

        v1.setFocusModes(VideoCaptureCamera2.integerArrayListToArray(v5_1));
        v2_1 = this.mPreviewRequest.get(CaptureRequest.CONTROL_AF_MODE).intValue();
        if(v2_1 == v6 || v2_1 == v9) {
            v2_1 = 4;
        }
        else {
            if(v2_1 != 1) {
                if(v2_1 == v10) {
                }
                else {
                    v2_1 = v2_1 == 0 ? 2 : 1;
                    goto label_149;
                }
            }

            v2_1 = 3;
        }

    label_149:
        v1.setFocusMode(v2_1);
        v2 = v0.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_MODES);
        v5_1 = new ArrayList(1);
        v7 = v2.length;
        v8 = 0;
        while(true) {
            if(v8 < v7) {
                v11_1 = v2[v8];
                if(v11_1 != 1 && v11_1 != v10 && v11_1 != v6) {
                    if(v11_1 == v9) {
                    }
                    else {
                        ++v8;
                        continue;
                    }
                }

                break;
            }

            goto label_167;
        }

        v5_1.add(Integer.valueOf(v9));
        try {
        label_167:
            if(v0.get(CameraCharacteristics.CONTROL_AE_LOCK_AVAILABLE).booleanValue()) {
                v5_1.add(Integer.valueOf(v10));
            }

            goto label_173;
        }
        catch(NoSuchFieldError ) {
        label_173:
            v1.setExposureModes(VideoCaptureCamera2.integerArrayListToArray(v5_1));
            v2_1 = this.mPreviewRequest.get(CaptureRequest.CONTROL_AE_MODE).intValue() == 0 ? 1 : 4;
            if(this.mPreviewRequest.get(CaptureRequest.CONTROL_AE_LOCK).booleanValue()) {
                v2_1 = 2;
            }

            v1.setExposureMode(v2_1);
            v2_3 = v0.get(CameraCharacteristics.CONTROL_AE_COMPENSATION_STEP).floatValue();
            v1.setStepExposureCompensation(((double)v2_3));
            Object v5_2 = v0.get(CameraCharacteristics.CONTROL_AE_COMPENSATION_RANGE);
            v1.setMinExposureCompensation(((double)((((float)((Range)v5_2).getLower().intValue())) * v2_3)));
            v1.setMaxExposureCompensation(((double)((((float)((Range)v5_2).getUpper().intValue())) * v2_3)));
            v1.setCurrentExposureCompensation(((double)((((float)this.mPreviewRequest.get(CaptureRequest.CONTROL_AE_EXPOSURE_COMPENSATION).intValue())) * v2_3)));
            v2 = v0.get(CameraCharacteristics.CONTROL_AWB_AVAILABLE_MODES);
            v5_1 = new ArrayList(1);
            v7 = v2.length;
            for(v8 = 0; true; ++v8) {
                if(v8 < v7) {
                    if(v2[v8] == 1) {
                        v5_1.add(Integer.valueOf(v9));
                    }
                    else {
                        goto label_229;
                    }
                }

                try {
                    if(!v0.get(CameraCharacteristics.CONTROL_AWB_LOCK_AVAILABLE).booleanValue()) {
                        goto label_237;
                    }

                    break;
                }
                catch(NoSuchFieldError ) {
                    goto label_237;
                }

            label_229:
            }

            try {
                v5_1.add(Integer.valueOf(v10));
                goto label_237;
            }
            catch(NoSuchFieldError ) {
            label_237:
                v1.setWhiteBalanceModes(VideoCaptureCamera2.integerArrayListToArray(v5_1));
                v2_1 = this.mPreviewRequest.get(CaptureRequest.CONTROL_AWB_MODE).intValue();
                if(v2_1 == 0) {
                    v1.setWhiteBalanceMode(1);
                }
                else if(v2_1 == 1) {
                    v1.setWhiteBalanceMode(v9);
                }
                else {
                    v1.setWhiteBalanceMode(v10);
                }

                v1.setMinColorTemperature(VideoCaptureCamera2.COLOR_TEMPERATURES_MAP.keyAt(0));
                v1.setMaxColorTemperature(VideoCaptureCamera2.COLOR_TEMPERATURES_MAP.keyAt(VideoCaptureCamera2.COLOR_TEMPERATURES_MAP.size() - 1));
                v2_1 = VideoCaptureCamera2.COLOR_TEMPERATURES_MAP.indexOfValue(v2_1);
                if(v2_1 >= 0) {
                    v1.setCurrentColorTemperature(VideoCaptureCamera2.COLOR_TEMPERATURES_MAP.keyAt(v2_1));
                }

                v1.setStepColorTemperature(50);
                if(!v0.get(CameraCharacteristics.FLASH_INFO_AVAILABLE).booleanValue()) {
                    v1.setSupportsTorch(false);
                    v1.setRedEyeReduction(false);
                }
                else {
                    v1.setSupportsTorch(true);
                    boolean v2_4 = this.mPreviewRequest.get(CaptureRequest.FLASH_MODE).intValue() == v10 ? true : false;
                    v1.setTorch(v2_4);
                    v1.setRedEyeReduction(true);
                    Object v0_1 = v0.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_MODES);
                    ArrayList v2_5 = new ArrayList(0);
                    v5 = v0_1.length;
                    while(v3 < v5) {
                        v7 = v0_1[v3];
                        if(v7 == 0) {
                            v2_5.add(Integer.valueOf(1));
                        }
                        else if(v7 == v10) {
                            v2_5.add(Integer.valueOf(v10));
                        }
                        else if(v7 == v6) {
                            v2_5.add(Integer.valueOf(v6));
                        }

                        ++v3;
                    }

                    v1.setFillLightModes(VideoCaptureCamera2.integerArrayListToArray(v2_5));
                }

                return v1.build();
            }
        }
    }

    static boolean isLegacyDevice(int arg1) {
        CameraCharacteristics v1 = VideoCaptureCamera2.getCameraCharacteristics(arg1);
        boolean v1_1 = v1 == null || v1.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL).intValue() != 2 ? false : true;
        return v1_1;
    }

    private void notifyTakePhotoError(long arg7) {
        this.nativeOnPhotoTaken(this.mNativeVideoCaptureDeviceAndroid, arg7, new byte[0]);
    }

    public void setPhotoOptions(double arg23, int arg25, int arg26, double arg27, double arg29, float[] arg31, boolean arg32, double arg33, int arg35, double arg36, boolean arg38, boolean arg39, int arg40, boolean arg41, boolean arg42, double arg43) {
        Object v1_2;
        VideoCaptureCamera2 v0 = this;
        double v1 = arg23;
        int v3 = arg25;
        int v4 = arg26;
        float[] v5 = arg31;
        int v6 = arg35;
        CameraCharacteristics v8 = VideoCaptureCamera2.getCameraCharacteristics(v0.mId);
        Object v9 = v8.get(CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE);
        if(v1 != 0) {
            float v12 = Math.max(1f, Math.min(((float)v1), v0.mMaxZoom));
            float v14 = (v12 - 1f) / (2f * v12);
            float v13 = 1f - v14;
            v0.mCropRect = new Rect(Math.round((((float)((Rect)v9).width())) * v14), Math.round((((float)((Rect)v9).height())) * v14), Math.round((((float)((Rect)v9).width())) * v13), Math.round((((float)((Rect)v9).height())) * v13));
            Log.d("VideoCapture", "zoom level %f, rectangle: %s", Float.valueOf(v12), v0.mCropRect.toString());
        }

        if(v3 != 0) {
            v0.mFocusMode = v3;
        }

        if(v4 != 0) {
            v0.mExposureMode = v4;
        }

        if(v6 != 0) {
            v0.mWhiteBalanceMode = v6;
        }

        double v6_1 = 0;
        if(arg27 > v6_1) {
            v0.mPhotoWidth = ((int)Math.round(arg27));
        }

        if(arg29 > v6_1) {
            v0.mPhotoHeight = ((int)Math.round(arg29));
        }

        MeteringRectangle v4_1 = null;
        if(v0.mAreaOfInterest != null && !v0.mAreaOfInterest.getRect().isEmpty() && v1 > 0) {
            v0.mAreaOfInterest = v4_1;
        }

        if(v0.mFocusMode == 1 || v0.mExposureMode == 1) {
            v0.mAreaOfInterest = v4_1;
        }

        int v1_1 = v8.get(CameraCharacteristics.CONTROL_MAX_REGIONS_AF).intValue() > 0 || v8.get(CameraCharacteristics.CONTROL_MAX_REGIONS_AE).intValue() > 0 || v8.get(CameraCharacteristics.CONTROL_MAX_REGIONS_AWB).intValue() > 0 ? 1 : 0;
        if(v1_1 != 0 && v5.length > 0) {
            if(v0.mCropRect.isEmpty()) {
                v1_2 = v9;
            }
            else {
                Rect v1_3 = v0.mCropRect;
            }

            v4 = Math.round(v5[0] * (((float)((Rect)v1_2).width())));
            v6 = Math.round(v5[1] * (((float)((Rect)v1_2).height())));
            if(((Rect)v1_2).equals(v0.mCropRect)) {
                v4 += (((Rect)v9).width() - ((Rect)v1_2).width()) / 2;
                v6 += (((Rect)v9).height() - ((Rect)v1_2).height()) / 2;
            }

            int v13_1 = ((Rect)v1_2).width() / 8;
            int v14_1 = ((Rect)v1_2).height() / 8;
            v0.mAreaOfInterest = new MeteringRectangle(Math.max(0, v4 - v13_1 / 2), Math.max(0, v6 - v14_1 / 2), v13_1, v14_1, 1000);
            Log.d("VideoCapture", "Calculating (%.2fx%.2f) wrt to %s (canvas being %s)", Float.valueOf(v5[0]), Float.valueOf(v5[1]), ((Rect)v1_2).toString(), ((Rect)v9).toString());
            Log.d("VideoCapture", "Area of interest %s", v0.mAreaOfInterest.toString());
        }

        if(arg32) {
            v0.mExposureCompensation = ((int)Math.round(arg33 / (((double)v8.get(CameraCharacteristics.CONTROL_AE_COMPENSATION_STEP).floatValue()))));
        }

        double v3_1 = 0;
        if(arg36 > v3_1) {
            v0.mIso = ((int)Math.round(arg36));
        }

        if(arg43 > v3_1) {
            v0.mColorTemperature = ((int)Math.round(arg43));
        }

        if(arg38) {
            v0.mRedEyeReduction = arg39;
        }

        v1_1 = arg40;
        if(v1_1 != 0) {
            v0.mFillLightMode = v1_1;
        }

        if(arg41) {
            v0.mTorch = arg42;
        }

        Handler v1_4 = new Handler(ContextUtils.getApplicationContext().getMainLooper());
        v1_4.removeCallbacks(v0.mReconfigureCaptureTask);
        v1_4.post(v0.mReconfigureCaptureTask);
    }

    public boolean startCapture() {
        this.changeCameraStateAndNotify(CameraState.OPENING);
        Object v0 = ContextUtils.getApplicationContext().getSystemService("camera");
        if(!this.mUseBackgroundThreadForTesting) {
            this.mMainHandler = new Handler(ContextUtils.getApplicationContext().getMainLooper());
        }
        else {
            HandlerThread v1 = new HandlerThread("CameraPicture");
            v1.start();
            this.mMainHandler = new Handler(v1.getLooper());
        }

        CrStateListener v1_1 = new CrStateListener(this, null);
        try {
            ((CameraManager)v0).openCamera(Integer.toString(this.mId), ((CameraDevice$StateCallback)v1_1), this.mMainHandler);
            return 1;
        }
        catch(SecurityException v0_1) {
            Log.e("VideoCapture", "allocate: manager.openCamera: ", new Object[]{v0_1});
            return 0;
        }
    }

    public boolean stopCapture() {
        Object v0 = this.mCameraStateLock;
        __monitor_enter(v0);
        try {
            while(true) {
                if(this.mCameraState != CameraState.STARTED && this.mCameraState != CameraState.STOPPED) {
                    try {
                        this.mCameraStateLock.wait();
                        continue;
                    }
                    catch(InterruptedException v1_1) {
                        try {
                            Log.e("VideoCapture", "CaptureStartedEvent: ", new Object[]{v1_1});
                            continue;
                        label_20:
                            if(this.mCameraState == CameraState.STOPPED) {
                                __monitor_exit(v0);
                                return 1;
                            }

                            __monitor_exit(v0);
                            break;
                        }
                        catch(Throwable v1) {
                            goto label_52;
                        }
                    }
                }

                goto label_20;
            }
        }
        catch(Throwable v1) {
            goto label_52;
        }

        try {
            this.mPreviewSession.abortCaptures();
        }
        catch(IllegalStateException v0_1) {
            Log.w("VideoCapture", "abortCaptures: ", new Object[]{v0_1});
        }

        if(this.mCameraDevice == null) {
            return 0;
        }

        this.mCameraDevice.close();
        if(this.mUseBackgroundThreadForTesting) {
            this.mMainHandler.getLooper().quit();
        }

        this.changeCameraStateAndNotify(CameraState.STOPPED);
        this.mCropRect = new Rect();
        return 1;
        try {
        label_52:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_52;
        }

        throw v1;
    }

    public boolean takePhoto(long arg8) {
        CaptureRequest$Builder v5;
        if(this.mCameraDevice != null) {
            if(this.mCameraState != CameraState.STARTED) {
            }
            else {
                int v2 = 0x100;
                Size v0 = VideoCaptureCamera2.findClosestSizeInArray(VideoCaptureCamera2.getCameraCharacteristics(this.mId).get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP).getOutputSizes(v2), this.mPhotoWidth, this.mPhotoHeight);
                Log.d("VideoCapture", "requested resolution: (%dx%d)", Integer.valueOf(this.mPhotoWidth), Integer.valueOf(this.mPhotoHeight));
                if(v0 != null) {
                    Log.d("VideoCapture", " matched (%dx%d)", Integer.valueOf(v0.getWidth()), Integer.valueOf(v0.getHeight()));
                }

                int v3 = v0 != null ? v0.getWidth() : this.mCaptureFormat.getWidth();
                int v0_1 = v0 != null ? v0.getHeight() : this.mCaptureFormat.getHeight();
                ImageReader v0_2 = ImageReader.newInstance(v3, v0_1, v2, 1);
                HandlerThread v2_1 = new HandlerThread("CameraPicture");
                v2_1.start();
                Handler v3_1 = new Handler(v2_1.getLooper());
                v0_2.setOnImageAvailableListener(new CrPhotoReaderListener(this, arg8), v3_1);
                ArrayList v2_2 = new ArrayList(1);
                ((List)v2_2).add(v0_2.getSurface());
                try {
                    v5 = this.mCameraDevice.createCaptureRequest(2);
                    if(v5 != null) {
                        goto label_66;
                    }
                }
                catch(CameraAccessException v8) {
                    Log.e("VideoCapture", "createCaptureRequest() error ", new Object[]{v8});
                    return 0;
                }

                Log.e("VideoCapture", "photoRequestBuilder error", new Object[0]);
                return 0;
            label_66:
                v5.addTarget(v0_2.getSurface());
                v5.set(CaptureRequest.JPEG_ORIENTATION, Integer.valueOf(this.getCameraRotation()));
                this.configureCommonCaptureSettings(v5);
                CrPhotoSessionListener v5_1 = new CrPhotoSessionListener(this, v5.build(), arg8);
                try {
                    this.mCameraDevice.createCaptureSession(((List)v2_2), ((CameraCaptureSession$StateCallback)v5_1), v3_1);
                    return 1;
                }
                catch(SecurityException v8_1) {
                    Log.e("VideoCapture", "createCaptureSession: " + v8_1, new Object[0]);
                    return 0;
                }
            }
        }

        return 0;
    }
}

