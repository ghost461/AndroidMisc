package org.chromium.media;

import android.annotation.TargetApi;
import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.graphics.SurfaceTexture$OnFrameAvailableListener;
import android.graphics.SurfaceTexture;
import android.hardware.Camera$Area;
import android.hardware.Camera$AutoFocusCallback;
import android.hardware.Camera$CameraInfo;
import android.hardware.Camera$ErrorCallback;
import android.hardware.Camera$Parameters;
import android.hardware.Camera$PictureCallback;
import android.hardware.Camera$PreviewCallback;
import android.hardware.Camera$ShutterCallback;
import android.hardware.Camera$Size;
import android.hardware.Camera;
import android.opengl.GLES20;
import android.os.Build;
import android.util.SparseArray;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import org.chromium.base.Log;
import org.chromium.base.annotations.JNINamespace;

@TargetApi(value=15) @JNINamespace(value="media") public class VideoCaptureCamera extends VideoCapture implements Camera$PreviewCallback {
    class BuggyDeviceHack {
        private static final String[] COLORSPACE_BUGGY_DEVICE_LIST;

        static {
            BuggyDeviceHack.COLORSPACE_BUGGY_DEVICE_LIST = new String[]{"SAMSUNG-SGH-I747", "ODROID-U2", "XT1092", "XT1095", "XT1096", "XT1097"};
        }

        private BuggyDeviceHack() {
            super();
        }

        static int getImageFormat() {
            String[] v0 = BuggyDeviceHack.COLORSPACE_BUGGY_DEVICE_LIST;
            int v1 = v0.length;
            int v2;
            for(v2 = 0; v2 < v1; ++v2) {
                if(v0[v2].contentEquals(Build.MODEL)) {
                    return 17;
                }
            }

            return 842094169;
        }
    }

    class CrErrorCallback implements Camera$ErrorCallback {
        CrErrorCallback(VideoCaptureCamera arg1, org.chromium.media.VideoCaptureCamera$1 arg2) {
            this(arg1);
        }

        private CrErrorCallback(VideoCaptureCamera arg1) {
            VideoCaptureCamera.this = arg1;
            super();
        }

        public void onError(int arg11, Camera arg12) {
            VideoCaptureCamera v12 = VideoCaptureCamera.this;
            long v0 = VideoCaptureCamera.this.mNativeVideoCaptureDeviceAndroid;
            v12.nativeOnError(v0, "Error id: " + arg11);
            Object v11 = VideoCaptureCamera.this.mPhotoTakenCallbackLock;
            __monitor_enter(v11);
            try {
                long v2_1 = 0;
                if(VideoCaptureCamera.this.mPhotoTakenCallbackId == v2_1) {
                    __monitor_exit(v11);
                    return;
                }

                VideoCaptureCamera.this.nativeOnPhotoTaken(VideoCaptureCamera.this.mNativeVideoCaptureDeviceAndroid, VideoCaptureCamera.this.mPhotoTakenCallbackId, new byte[0]);
                VideoCaptureCamera.this.mPhotoTakenCallbackId = v2_1;
                __monitor_exit(v11);
                return;
            label_32:
                __monitor_exit(v11);
            }
            catch(Throwable v12_1) {
                goto label_32;
            }

            throw v12_1;
        }
    }

    class CrPictureCallback implements Camera$PictureCallback {
        CrPictureCallback(VideoCaptureCamera arg1, org.chromium.media.VideoCaptureCamera$1 arg2) {
            this(arg1);
        }

        private CrPictureCallback(VideoCaptureCamera arg1) {
            VideoCaptureCamera.this = arg1;
            super();
        }

        public void onPictureTaken(byte[] arg12, Camera arg13) {
            try {
                Log.d("VideoCapture", "|mPreviewParameters|: %s", VideoCaptureCamera.this.mPreviewParameters.flatten());
                arg13.setParameters(VideoCaptureCamera.this.mPreviewParameters);
            }
            catch(RuntimeException v1) {
                Log.e("VideoCapture", "onPictureTaken, setParameters() " + v1, new Object[0]);
            }

            try {
                arg13.startPreview();
            }
            catch(RuntimeException v13) {
                Log.e("VideoCapture", "onPictureTaken, startPreview() " + v13, new Object[0]);
            }

            Object v13_1 = VideoCaptureCamera.this.mPhotoTakenCallbackLock;
            __monitor_enter(v13_1);
            try {
                long v2_1 = 0;
                if(VideoCaptureCamera.this.mPhotoTakenCallbackId != v2_1) {
                    VideoCaptureCamera.this.nativeOnPhotoTaken(VideoCaptureCamera.this.mNativeVideoCaptureDeviceAndroid, VideoCaptureCamera.this.mPhotoTakenCallbackId, arg12);
                }

                VideoCaptureCamera.this.mPhotoTakenCallbackId = v2_1;
                __monitor_exit(v13_1);
                return;
            label_52:
                __monitor_exit(v13_1);
            }
            catch(Throwable v12) {
                goto label_52;
            }

            throw v12;
        }
    }

    private static final SparseArray COLOR_TEMPERATURES_MAP = null;
    private static final int GL_TEXTURE_EXTERNAL_OES = 0x8D65;
    private static final int NUM_CAPTURE_BUFFERS = 3;
    private static final String TAG = "VideoCapture";
    private Camera$Area mAreaOfInterest;
    private Camera mCamera;
    private int mExpectedFrameSize;
    private int[] mGlTextures;
    private boolean mIsRunning;
    private int mPhotoHeight;
    private long mPhotoTakenCallbackId;
    private final Object mPhotoTakenCallbackLock;
    private int mPhotoWidth;
    private ReentrantLock mPreviewBufferLock;
    private Camera$Parameters mPreviewParameters;
    private SurfaceTexture mSurfaceTexture;

    static {
        VideoCaptureCamera.COLOR_TEMPERATURES_MAP = new SparseArray();
        VideoCaptureCamera.COLOR_TEMPERATURES_MAP.append(2850, "incandescent");
        VideoCaptureCamera.COLOR_TEMPERATURES_MAP.append(2950, "warm-fluorescent");
        VideoCaptureCamera.COLOR_TEMPERATURES_MAP.append(4250, "fluorescent");
        VideoCaptureCamera.COLOR_TEMPERATURES_MAP.append(4600, "twilight");
        VideoCaptureCamera.COLOR_TEMPERATURES_MAP.append(5500, "daylight");
        VideoCaptureCamera.COLOR_TEMPERATURES_MAP.append(6000, "cloudy-daylight");
        VideoCaptureCamera.COLOR_TEMPERATURES_MAP.append(7000, "shade");
    }

    VideoCaptureCamera(int arg1, long arg2) {
        super(arg1, arg2);
        this.mPhotoTakenCallbackLock = new Object();
        this.mPreviewBufferLock = new ReentrantLock();
    }

    static Object access$000(VideoCaptureCamera arg0) {
        return arg0.mPhotoTakenCallbackLock;
    }

    static long access$100(VideoCaptureCamera arg2) {
        return arg2.mPhotoTakenCallbackId;
    }

    static long access$102(VideoCaptureCamera arg0, long arg1) {
        arg0.mPhotoTakenCallbackId = arg1;
        return arg1;
    }

    static Camera$Parameters access$200(VideoCaptureCamera arg0) {
        return arg0.mPreviewParameters;
    }

    public boolean allocate(int arg17, int arg18, int arg19) {
        VideoCaptureCamera v1 = this;
        Log.d("VideoCapture", "allocate: requested (%d x %d) @%dfps", Integer.valueOf(arg17), Integer.valueOf(arg18), Integer.valueOf(arg19));
        try {
            v1.mCamera = Camera.open(v1.mId);
        }
        catch(RuntimeException v0) {
            Log.e("VideoCapture", "allocate: Camera.open: " + v0, new Object[0]);
            return 0;
        }

        Camera$CameraInfo v3 = VideoCaptureCamera.getCameraInfo(v1.mId);
        Camera v4_1 = null;
        if(v3 == null) {
            v1.mCamera.release();
            v1.mCamera = v4_1;
            return 0;
        }

        v1.mCameraNativeOrientation = v3.orientation;
        boolean v3_1 = v3.facing == 0 ? true : false;
        v1.mInvertDeviceOrientationReadings = v3_1;
        Log.d("VideoCapture", "allocate: Rotation dev=%d, cam=%d, facing back? %s", Integer.valueOf(this.getDeviceRotation()), Integer.valueOf(v1.mCameraNativeOrientation), Boolean.valueOf(v1.mInvertDeviceOrientationReadings));
        Camera$Parameters v3_2 = VideoCaptureCamera.getCameraParameters(v1.mCamera);
        if(v3_2 == null) {
            v1.mCamera = v4_1;
            return 0;
        }

        List v6 = v3_2.getSupportedPreviewFpsRange();
        if(v6 != null) {
            if(v6.size() == 0) {
            }
            else {
                ArrayList v7 = new ArrayList(v6.size());
                Iterator v6_1 = v6.iterator();
                while(v6_1.hasNext()) {
                    Object v8 = v6_1.next();
                    v7.add(new FramerateRange(v8[0], v8[1]));
                }

                FramerateRange v6_2 = VideoCaptureCamera.getClosestFramerateRange(((List)v7), arg19 * 1000);
                int[] v7_1 = new int[]{v6_2.min, v6_2.max};
                Log.d("VideoCapture", "allocate: fps set to [%d-%d]", Integer.valueOf(v7_1[0]), Integer.valueOf(v7_1[1]));
                v6_1 = v3_2.getSupportedPreviewSizes().iterator();
                int v10 = arg17;
                int v11 = arg18;
                int v9;
                for(v9 = 0x7FFFFFFF; v6_1.hasNext(); v9 = v13) {
                    Object v12 = v6_1.next();
                    int v13 = Math.abs(((Camera$Size)v12).width - arg17) + Math.abs(((Camera$Size)v12).height - arg18);
                    Log.d("VideoCapture", "allocate: supported (%d, %d), diff=%d", Integer.valueOf(((Camera$Size)v12).width), Integer.valueOf(((Camera$Size)v12).height), Integer.valueOf(v13));
                    if(v13 >= v9) {
                        continue;
                    }

                    if(((Camera$Size)v12).width % 0x20 != 0) {
                        continue;
                    }

                    v10 = ((Camera$Size)v12).width;
                    v11 = ((Camera$Size)v12).height;
                }

                if(v9 == 0x7FFFFFFF) {
                    Log.e("VideoCapture", "allocate: can not find a multiple-of-32 resolution", new Object[0]);
                    return 0;
                }
                else {
                    Log.d("VideoCapture", "allocate: matched (%d x %d)", Integer.valueOf(v10), Integer.valueOf(v11));
                    if(v3_2.isVideoStabilizationSupported()) {
                        Log.d("VideoCapture", "Image stabilization supported, currently: " + v3_2.getVideoStabilization() + ", setting it.");
                        v3_2.setVideoStabilization(true);
                    }
                    else {
                        Log.d("VideoCapture", "Image stabilization not supported.");
                    }

                    if(v3_2.getSupportedFocusModes().contains("continuous-video")) {
                        v3_2.setFocusMode("continuous-video");
                    }
                    else {
                        Log.d("VideoCapture", "Continuous focus mode not supported.");
                    }

                    v1.mCaptureFormat = new VideoCaptureFormat(v10, v11, v7_1[1] / 1000, BuggyDeviceHack.getImageFormat());
                    v3_2.setPictureSize(v10, v11);
                    v3_2.setPreviewSize(v10, v11);
                    v3_2.setPreviewFpsRange(v7_1[0], v7_1[1]);
                    v3_2.setPreviewFormat(v1.mCaptureFormat.mPixelFormat);
                    try {
                        v1.mCamera.setParameters(v3_2);
                    }
                    catch(RuntimeException v0) {
                        Log.e("VideoCapture", "setParameters: " + v0, new Object[0]);
                        return 0;
                    }

                    v1.mGlTextures = new int[1];
                    GLES20.glGenTextures(1, v1.mGlTextures, 0);
                    GLES20.glBindTexture(0x8D65, v1.mGlTextures[0]);
                    GLES20.glTexParameterf(0x8D65, 0x2801, 9729f);
                    GLES20.glTexParameterf(0x8D65, 0x2800, 9729f);
                    GLES20.glTexParameteri(0x8D65, 0x2802, 0x812F);
                    GLES20.glTexParameteri(0x8D65, 0x2803, 0x812F);
                    v1.mSurfaceTexture = new SurfaceTexture(v1.mGlTextures[0]);
                    SurfaceTexture$OnFrameAvailableListener v3_3 = null;
                    v1.mSurfaceTexture.setOnFrameAvailableListener(v3_3);
                    try {
                        v1.mCamera.setPreviewTexture(v1.mSurfaceTexture);
                    }
                    catch(IOException v0_1) {
                        Log.e("VideoCapture", "allocate: " + v0_1, new Object[0]);
                        return 0;
                    }

                    v1.mCamera.setErrorCallback(new CrErrorCallback(v1, ((org.chromium.media.VideoCaptureCamera$1)v3_3)));
                    v1.mExpectedFrameSize = v1.mCaptureFormat.mWidth * v1.mCaptureFormat.mHeight * ImageFormat.getBitsPerPixel(v1.mCaptureFormat.mPixelFormat) / 8;
                    int v2;
                    for(v2 = 0; v2 < 3; ++v2) {
                        v1.mCamera.addCallbackBuffer(new byte[v1.mExpectedFrameSize]);
                    }

                    return 1;
                }
            }
        }

        Log.e("VideoCapture", "allocate: no fps range found", new Object[0]);
        return 0;
    }

    public void deallocate() {
        if(this.mCamera == null) {
            return;
        }

        this.stopCapture();
        try {
            SurfaceTexture v2 = null;
            this.mCamera.setPreviewTexture(v2);
            if(this.mGlTextures != null) {
                GLES20.glDeleteTextures(1, this.mGlTextures, 0);
            }

            this.mCaptureFormat = ((VideoCaptureFormat)v2);
            this.mCamera.release();
            this.mCamera = ((Camera)v2);
            return;
        }
        catch(IOException v1) {
            Log.e("VideoCapture", "deallocate: failed to deallocate camera, " + v1, new Object[0]);
            return;
        }
    }

    private static Camera$CameraInfo getCameraInfo(int arg3) {
        Camera$CameraInfo v0 = new Camera$CameraInfo();
        try {
            Camera.getCameraInfo(arg3, v0);
            return v0;
        }
        catch(RuntimeException v3) {
            Log.e("VideoCapture", "getCameraInfo: Camera.getCameraInfo: " + v3, new Object[0]);
            return null;
        }
    }

    private static Camera$Parameters getCameraParameters(Camera arg4) {
        try {
            return arg4.getParameters();
        }
        catch(RuntimeException v0) {
            Log.e("VideoCapture", "getCameraParameters: android.hardware.Camera.getParameters: " + v0, new Object[0]);
            if(arg4 != null) {
                arg4.release();
            }

            return null;
        }
    }

    static int getCaptureApiType(int arg0) {
        if(VideoCaptureCamera.getCameraInfo(arg0) == null) {
            return 10;
        }

        return 6;
    }

    private String getClosestWhiteBalance(int arg5, List arg6) {
        Object v1_1;
        int v0 = 0x7FFFFFFF;
        String v1 = null;
        int v2;
        for(v2 = 0; v2 < VideoCaptureCamera.COLOR_TEMPERATURES_MAP.size(); ++v2) {
            if(!arg6.contains(VideoCaptureCamera.COLOR_TEMPERATURES_MAP.valueAt(v2))) {
            }
            else {
                int v3 = Math.abs(arg5 - VideoCaptureCamera.COLOR_TEMPERATURES_MAP.keyAt(v2));
                if(v3 >= v0) {
                }
                else {
                    v1_1 = VideoCaptureCamera.COLOR_TEMPERATURES_MAP.valueAt(v2);
                    v0 = v3;
                }
            }
        }

        return ((String)v1_1);
    }

    static VideoCaptureFormat[] getDeviceSupportedFormats(int arg14) {
        ArrayList v9_1;
        ArrayList v6_3;
        List v6_2;
        int v8;
        List v5_1;
        Camera v14_1;
        VideoCaptureFormat[] v1 = null;
        try {
            v14_1 = Camera.open(arg14);
        }
        catch(RuntimeException v14) {
            Log.e("VideoCapture", "Camera.open: ", new Object[]{v14});
            return v1;
        }

        Camera$Parameters v3 = VideoCaptureCamera.getCameraParameters(v14_1);
        if(v3 == null) {
            return v1;
        }

        ArrayList v4 = new ArrayList();
        try {
            v5_1 = v3.getSupportedPreviewFormats();
        }
        catch(NullPointerException v5) {
            Log.e("VideoCapture", "Camera.Parameters.getSupportedPreviewFormats: ", new Object[]{v5});
            v5_1 = ((List)v1);
        }

        if(v5_1 == null) {
            ArrayList v5_2 = new ArrayList();
        }

        if(v5_1.size() == 0) {
            v5_1.add(Integer.valueOf(0));
        }

        Iterator v5_3 = v5_1.iterator();
        while(true) {
        label_26:
            if(!v5_3.hasNext()) {
                goto label_82;
            }

            Object v6 = v5_3.next();
            v8 = 842094169;
            if(((Integer)v6).intValue() == v8) {
            }
            else if(((Integer)v6).intValue() == 17) {
                continue;
            }
            else {
                break;
            }

            goto label_38;
        }

        v8 = 0;
        try {
        label_38:
            v6_2 = v3.getSupportedPreviewFpsRange();
        }
        catch(StringIndexOutOfBoundsException v6_1) {
            Log.e("VideoCapture", "Camera.Parameters.getSupportedPreviewFpsRange: ", new Object[]{v6_1});
            v6_2 = ((List)v1);
        }

        if(v6_2 == null) {
            v6_3 = new ArrayList();
        }

        if(((List)v6_3).size() == 0) {
            ((List)v6_3).add(new int[]{0, 0});
        }

        Iterator v6_4 = ((List)v6_3).iterator();
        goto label_56;
    label_82:
        v14_1.release();
        return v4.toArray(new VideoCaptureFormat[v4.size()]);
    label_56:
        if(!v6_4.hasNext()) {
            goto label_26;
        }

        Object v7 = v6_4.next();
        List v9 = v3.getSupportedPreviewSizes();
        if(v9 == null) {
            v9_1 = new ArrayList();
        }

        if(((List)v9_1).size() == 0) {
            v14_1.getClass();
            ((List)v9_1).add(new Camera$Size(v14_1, 0, 0));
        }

        Iterator v9_2 = ((List)v9_1).iterator();
        while(true) {
            if(!v9_2.hasNext()) {
                goto label_56;
            }

            Object v10 = v9_2.next();
            v4.add(new VideoCaptureFormat(((Camera$Size)v10).width, ((Camera$Size)v10).height, (v7[1] + 999) / 1000, v8));
        }
    }

    static String getName(int arg3) {
        Camera$CameraInfo v0 = VideoCaptureCamera.getCameraInfo(arg3);
        if(v0 == null) {
            return null;
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("camera ");
        v1.append(arg3);
        v1.append(", facing ");
        String v3 = v0.facing == 1 ? "front" : "back";
        v1.append(v3);
        return v1.toString();
    }

    static int getNumberOfCameras() {
        return Camera.getNumberOfCameras();
    }

    public PhotoCapabilities getPhotoCapabilities() {
        int v2_2;
        Camera$Parameters v0 = VideoCaptureCamera.getCameraParameters(this.mCamera);
        Builder v1 = new Builder();
        Log.i("VideoCapture", " CAM params: %s", new Object[]{v0.flatten()});
        v1.setMinIso(0).setMaxIso(0).setCurrentIso(0).setStepIso(0);
        Iterator v2 = v0.getSupportedPictureSizes().iterator();
        int v3 = 0x7FFFFFFF;
        int v5 = 0x7FFFFFFF;
        int v6 = 0;
        int v8 = 0;
        while(v2.hasNext()) {
            Object v9 = v2.next();
            if(((Camera$Size)v9).width < v3) {
                v3 = ((Camera$Size)v9).width;
            }

            if(((Camera$Size)v9).height < v5) {
                v5 = ((Camera$Size)v9).height;
            }

            if(((Camera$Size)v9).width > v8) {
                v8 = ((Camera$Size)v9).width;
            }

            if(((Camera$Size)v9).height <= v6) {
                continue;
            }

            v6 = ((Camera$Size)v9).height;
        }

        v1.setMinHeight(v5).setMaxHeight(v6).setStepHeight(1);
        v1.setMinWidth(v3).setMaxWidth(v8).setStepWidth(1);
        Camera$Size v2_1 = v0.getPreviewSize();
        v1.setCurrentHeight(v2_1.height).setCurrentWidth(v2_1.width);
        if(v0.isZoomSupported()) {
            v2_2 = v0.getZoomRatios().get(v0.getMaxZoom()).intValue();
            v3 = v0.getZoomRatios().get(v0.getZoom()).intValue();
            v5 = v0.getZoomRatios().get(0).intValue();
            if(v0.getZoomRatios().size() > 1) {
                v6 = v0.getZoomRatios().get(1).intValue() - v0.getZoomRatios().get(0).intValue();
            }
            else {
                goto label_76;
            }
        }
        else {
            v2_2 = 0;
            v3 = 0;
            v5 = 0;
        label_76:
            v6 = 0;
        }

        v1.setMinZoom(((double)v5)).setMaxZoom(((double)v2_2));
        v1.setCurrentZoom(((double)v3)).setStepZoom(((double)v6));
        List v2_3 = v0.getSupportedFocusModes();
        v5 = 3;
        ArrayList v3_1 = new ArrayList(v5);
        v8 = 4;
        if((v2_3.contains("continuous-video")) || (v2_3.contains("continuous-picture")) || (v2_3.contains("edof"))) {
            v3_1.add(Integer.valueOf(v8));
        }

        if((v2_3.contains("auto")) || (v2_3.contains("macro"))) {
            v3_1.add(Integer.valueOf(v5));
        }

        int v9_1 = 2;
        if((v2_3.contains("infinity")) || (v2_3.contains("fixed"))) {
            v3_1.add(Integer.valueOf(v9_1));
        }

        v1.setFocusModes(VideoCaptureCamera.integerArrayListToArray(v3_1));
        String v2_4 = v0.getFocusMode();
        if((v2_4.equals("continuous-video")) || (v2_4.equals("continuous-picture")) || (v2_4.equals("edof"))) {
            v2_2 = 4;
        }
        else {
            if(!v2_4.equals("auto")) {
                if(v2_4.equals("macro")) {
                }
                else {
                    if(!v2_4.equals("infinity")) {
                        if(v2_4.equals("fixed")) {
                        }
                        else {
                            v2_2 = 1;
                            goto label_152;
                        }
                    }

                    v2_2 = 2;
                    goto label_152;
                }
            }

            v2_2 = 3;
        }

    label_152:
        v1.setFocusMode(v2_2);
        ArrayList v2_5 = new ArrayList(v9_1);
        v2_5.add(Integer.valueOf(v8));
        if(v0.isAutoExposureLockSupported()) {
            v2_5.add(Integer.valueOf(v9_1));
        }

        v1.setExposureModes(VideoCaptureCamera.integerArrayListToArray(v2_5));
        v2_2 = !v0.isAutoExposureLockSupported() || !v0.getAutoExposureLock() ? 4 : 2;
        v1.setExposureMode(v2_2);
        float v2_6 = v0.getExposureCompensationStep();
        v1.setStepExposureCompensation(((double)v2_6));
        v1.setMinExposureCompensation(((double)((((float)v0.getMinExposureCompensation())) * v2_6)));
        v1.setMaxExposureCompensation(((double)((((float)v0.getMaxExposureCompensation())) * v2_6)));
        v1.setCurrentExposureCompensation(((double)((((float)v0.getExposureCompensation())) * v2_6)));
        v2_5 = new ArrayList(v9_1);
        List v3_2 = v0.getSupportedWhiteBalance();
        if(v3_2 != null) {
            if(!v3_2.isEmpty()) {
                v2_5.add(Integer.valueOf(v8));
            }

            if(!v0.isAutoWhiteBalanceLockSupported()) {
                goto label_201;
            }

            v2_5.add(Integer.valueOf(v9_1));
        }

    label_201:
        v1.setWhiteBalanceModes(VideoCaptureCamera.integerArrayListToArray(v2_5));
        if((v0.isAutoWhiteBalanceLockSupported()) && (v0.getAutoWhiteBalanceLock())) {
            v8 = 2;
        }

        v1.setWhiteBalanceMode(v8);
        v1.setMinColorTemperature(VideoCaptureCamera.COLOR_TEMPERATURES_MAP.keyAt(0));
        v1.setMaxColorTemperature(VideoCaptureCamera.COLOR_TEMPERATURES_MAP.keyAt(VideoCaptureCamera.COLOR_TEMPERATURES_MAP.size() - 1));
        if(v8 == v9_1) {
            v2_2 = VideoCaptureCamera.COLOR_TEMPERATURES_MAP.indexOfValue(v0.getWhiteBalance());
            if(v2_2 >= 0) {
                v1.setCurrentColorTemperature(VideoCaptureCamera.COLOR_TEMPERATURES_MAP.keyAt(v2_2));
            }
        }

        v1.setStepColorTemperature(50);
        v2_3 = v0.getSupportedFlashModes();
        if(v2_3 != null) {
            v1.setSupportsTorch(v2_3.contains("torch"));
            v1.setTorch("torch".equals(v0.getFlashMode()));
            v1.setRedEyeReduction(v2_3.contains("red-eye"));
            ArrayList v0_1 = new ArrayList(0);
            if(v2_3.contains("off")) {
                v0_1.add(Integer.valueOf(1));
            }

            if(v2_3.contains("auto")) {
                v0_1.add(Integer.valueOf(v9_1));
            }

            if(v2_3.contains("on")) {
                v0_1.add(Integer.valueOf(v5));
            }

            v1.setFillLightModes(VideoCaptureCamera.integerArrayListToArray(v0_1));
        }

        return v1.build();
    }

    public void onPreviewFrame(byte[] arg9, Camera arg10) {
        this.mPreviewBufferLock.lock();
        try {
            if(this.mIsRunning) {
                goto label_9;
            }
        }
        catch(Throwable v0) {
            goto label_25;
        }

        this.mPreviewBufferLock.unlock();
        if(arg10 != null) {
            arg10.addCallbackBuffer(arg9);
        }

        return;
        try {
        label_9:
            if(arg9.length == this.mExpectedFrameSize) {
                this.nativeOnFrameAvailable(this.mNativeVideoCaptureDeviceAndroid, arg9, this.mExpectedFrameSize, this.getCameraRotation());
            }
        }
        catch(Throwable v0) {
        label_25:
            this.mPreviewBufferLock.unlock();
            if(arg10 != null) {
                arg10.addCallbackBuffer(arg9);
            }

            throw v0;
        }

        this.mPreviewBufferLock.unlock();
        if(arg10 != null) {
            arg10.addCallbackBuffer(arg9);
        }
    }

    public void setPhotoOptions(double arg22, int arg24, int arg25, double arg26, double arg28, float[] arg30, boolean arg31, double arg32, int arg34, double arg35, boolean arg37, boolean arg38, int arg39, boolean arg40, boolean arg41, double arg42) {
        VideoCaptureCamera v1 = this;
        int v4 = arg24;
        int v5 = arg25;
        float[] v6 = arg30;
        int v7 = arg34;
        Camera$Parameters v11 = VideoCaptureCamera.getCameraParameters(v1.mCamera);
        double v13 = 0;
        if((v11.isZoomSupported()) && arg22 > v13) {
            List v12 = v11.getZoomRatios();
            int v13_1 = 1;
            while(v13_1 < v12.size()) {
                if(arg22 < (((double)v12.get(v13_1).intValue()))) {
                }
                else {
                    ++v13_1;
                    continue;
                }

                break;
            }

            v11.setZoom(v13_1 - 1);
        }

        int v8 = 4;
        int v9 = 3;
        int v10 = 2;
        if(v4 == v10) {
            v11.setFocusMode("fixed");
        }
        else if(v4 == v9) {
            v11.setFocusMode("auto");
        }
        else if(v4 == v8) {
            v11.setFocusMode("continuous-picture");
        }

        if(v11.isAutoExposureLockSupported()) {
            if(v5 == v10) {
                v11.setAutoExposureLock(true);
            }
            else if(v5 != 1) {
                v11.setAutoExposureLock(false);
            }
        }

        double v18 = 0;
        if(arg26 > v18) {
            v1.mPhotoWidth = ((int)Math.round(arg26));
        }

        if(arg28 > v18) {
            v1.mPhotoHeight = ((int)Math.round(arg28));
        }

        Camera$Area v10_1 = null;
        if(v1.mAreaOfInterest != null && !v1.mAreaOfInterest.rect.isEmpty() && arg22 > 0) {
            v1.mAreaOfInterest = v10_1;
        }

        if(v4 == 1 || v5 == 1) {
            v1.mAreaOfInterest = v10_1;
        }

        int v2 = v11.getMaxNumMeteringAreas() > 0 || v11.getMaxNumFocusAreas() > 0 ? 1 : 0;
        if(v2 != 0 && v6.length > 0) {
            v2 = Math.round(v6[0] * 2000f) - 1000;
            int v3 = Math.round(v6[1] * 2000f) - 1000;
            v1.mAreaOfInterest = new Camera$Area(new Rect(Math.max(-1000, v2 - 0x7D), Math.max(-1000, v3 - 0x7D), Math.min(1000, v2 + 0x7D), Math.min(1000, v3 + 0x7D)), 1000);
            Log.d("VideoCapture", "Area of interest %s", v1.mAreaOfInterest.rect.toString());
        }

        if(v1.mAreaOfInterest != null) {
            v11.setFocusAreas(Arrays.asList(new Camera$Area[]{v1.mAreaOfInterest}));
            v11.setMeteringAreas(Arrays.asList(new Camera$Area[]{v1.mAreaOfInterest}));
        }

        if(arg31) {
            v11.setExposureCompensation(((int)Math.round(arg32 / (((double)v11.getExposureCompensationStep())))));
        }

        if(v7 == v8 && v11.getSupportedWhiteBalance() != null) {
            v11.setWhiteBalance("auto");
        }
        else if(v7 == 2 && (v11.isAutoWhiteBalanceLockSupported())) {
            v11.setAutoWhiteBalanceLock(true);
        }

        double v2_1 = arg42;
        if(v2_1 > 0) {
            String v5_1 = v1.getClosestWhiteBalance(((int)v2_1), v11.getSupportedWhiteBalance());
            Log.d("VideoCapture", " Color temperature (%f ==> %s)", Double.valueOf(arg42), v5_1);
            if(v5_1 != null) {
                v11.setWhiteBalance(v5_1);
            }
        }

        if(v11.getSupportedFlashModes() != null) {
            if((arg40) && (arg41)) {
                v11.setFlashMode("torch");
                goto label_176;
            }

            if(arg39 == 0) {
                goto label_176;
            }

            switch(arg39) {
                case 1: {
                    goto label_174;
                }
                case 2: {
                    goto label_167;
                }
                case 3: {
                    goto label_164;
                }
            }

            goto label_176;
        label_164:
            v11.setFlashMode("on");
            goto label_176;
        label_167:
            String v2_2 = !arg37 || !arg38 ? "auto" : "red-eye";
            v11.setFlashMode(v2_2);
            goto label_176;
        label_174:
            v11.setFlashMode("off");
        }

        try {
        label_176:
            v1.mCamera.setParameters(v11);
            if(v4 == 3) {
                goto label_181;
            }
        }
        catch(RuntimeException v0) {
            Log.e("VideoCapture", "setParameters: ", new Object[]{v0});
            return;
        }

        return;
    label_181:
        v1.mCamera.autoFocus(new Camera$AutoFocusCallback() {
            public void onAutoFocus(boolean arg2, Camera arg3) {
                String v3 = "VideoCapture";
                String v0 = "onAutoFocus() finished: %s ";
                String v2 = arg2 ? "success" : "failed";
                Log.d(v3, v0, v2);
            }
        });
    }

    private void setPreviewCallback(Camera$PreviewCallback arg2) {
        this.mCamera.setPreviewCallbackWithBuffer(arg2);
    }

    public boolean startCapture() {
        if(this.mCamera == null) {
            Log.e("VideoCapture", "startCapture: mCamera is null", new Object[0]);
            return 0;
        }

        this.mPreviewBufferLock.lock();
        try {
            if(!this.mIsRunning) {
                goto label_16;
            }
        }
        catch(Throwable v0) {
            this.mPreviewBufferLock.unlock();
            throw v0;
        }

        this.mPreviewBufferLock.unlock();
        return 1;
    label_16:
        this.mPreviewBufferLock.unlock();
        this.setPreviewCallback(((Camera$PreviewCallback)this));
        try {
            this.mCamera.startPreview();
        }
        catch(RuntimeException v0_1) {
            Log.e("VideoCapture", "startCapture: Camera.startPreview: " + v0_1, new Object[0]);
            return 0;
        }

        this.mPreviewBufferLock.lock();
        try {
            this.nativeOnStarted(this.mNativeVideoCaptureDeviceAndroid);
            this.mIsRunning = true;
        }
        catch(Throwable v0) {
            this.mPreviewBufferLock.unlock();
            throw v0;
        }

        this.mPreviewBufferLock.unlock();
        return 1;
    }

    public boolean stopCapture() {
        if(this.mCamera == null) {
            Log.e("VideoCapture", "stopCapture: mCamera is null", new Object[0]);
            return 1;
        }

        this.mPreviewBufferLock.lock();
        try {
            if(this.mIsRunning) {
                goto label_16;
            }
        }
        catch(Throwable v0) {
            goto label_26;
        }

        this.mPreviewBufferLock.unlock();
        return 1;
        try {
        label_16:
            this.mIsRunning = false;
        }
        catch(Throwable v0) {
        label_26:
            this.mPreviewBufferLock.unlock();
            throw v0;
        }

        this.mPreviewBufferLock.unlock();
        this.mCamera.stopPreview();
        this.setPreviewCallback(null);
        return 1;
    }

    public boolean takePhoto(long arg12) {
        if(this.mCamera != null) {
            if(!this.mIsRunning) {
            }
            else {
                Object v0 = this.mPhotoTakenCallbackLock;
                __monitor_enter(v0);
                try {
                    if(this.mPhotoTakenCallbackId != 0) {
                        __monitor_exit(v0);
                        return 0;
                    }
                    else {
                        this.mPhotoTakenCallbackId = arg12;
                        __monitor_exit(v0);
                        goto label_15;
                    }

                    goto label_97;
                }
                catch(Throwable v12) {
                    try {
                    label_95:
                        __monitor_exit(v0);
                    }
                    catch(Throwable v12) {
                        goto label_95;
                    }

                    throw v12;
                }

            label_15:
                this.mPreviewParameters = VideoCaptureCamera.getCameraParameters(this.mCamera);
                Camera$Parameters v12_1 = VideoCaptureCamera.getCameraParameters(this.mCamera);
                v12_1.setRotation(this.getCameraRotation());
                org.chromium.media.VideoCaptureCamera$1 v0_1 = null;
                if(this.mPhotoWidth > 0 || this.mPhotoHeight > 0) {
                    Iterator v13 = v12_1.getSupportedPictureSizes().iterator();
                    int v2 = 0x7FFFFFFF;
                    Camera$Size v4 = ((Camera$Size)v0_1);
                    int v3;
                    for(v3 = 0x7FFFFFFF; v13.hasNext(); v3 = v6) {
                        Object v5 = v13.next();
                        int v6 = this.mPhotoWidth > 0 ? Math.abs(((Camera$Size)v5).width - this.mPhotoWidth) : 0;
                        int v7 = this.mPhotoHeight > 0 ? Math.abs(((Camera$Size)v5).height - this.mPhotoHeight) : 0;
                        v6 += v7;
                        if(v6 >= v3) {
                            continue;
                        }

                        Object v4_1 = v5;
                    }

                    if(v3 == v2) {
                        goto label_71;
                    }

                    Log.d("VideoCapture", "requested resolution: (%dx%d); matched (%dx%d)", Integer.valueOf(this.mPhotoWidth), Integer.valueOf(this.mPhotoHeight), Integer.valueOf(v4.width), Integer.valueOf(v4.height));
                    v12_1.setPictureSize(v4.width, v4.height);
                }

                try {
                label_71:
                    Log.d("VideoCapture", "|photoParameters|: %s", v12_1.flatten());
                    this.mCamera.setParameters(v12_1);
                }
                catch(RuntimeException v12_2) {
                    Log.e("VideoCapture", "setParameters " + v12_2, new Object[0]);
                    return 0;
                }

                this.mCamera.takePicture(((Camera$ShutterCallback)v0_1), ((Camera$PictureCallback)v0_1), ((Camera$PictureCallback)v0_1), new CrPictureCallback(this, v0_1));
                return 1;
            }
        }

    label_97:
        Log.e("VideoCapture", "takePhoto: mCamera is null or is not running", new Object[0]);
        return 0;
    }
}

