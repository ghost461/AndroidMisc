package org.chromium.media;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.chromium.base.ContextUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="media") public abstract class VideoCapture {
    public class FramerateRange {
        public int max;
        public int min;

        public FramerateRange(int arg1, int arg2) {
            super();
            this.min = arg1;
            this.max = arg2;
        }
    }

    protected int mCameraNativeOrientation;
    protected VideoCaptureFormat mCaptureFormat;
    protected final int mId;
    protected boolean mInvertDeviceOrientationReadings;
    protected final long mNativeVideoCaptureDeviceAndroid;
    protected boolean mUseBackgroundThreadForTesting;

    VideoCapture(int arg1, long arg2) {
        super();
        this.mId = arg1;
        this.mNativeVideoCaptureDeviceAndroid = arg2;
    }

    @CalledByNative public abstract boolean allocate(int arg1, int arg2, int arg3);

    @CalledByNative public abstract void deallocate();

    protected final int getCameraRotation() {
        int v0 = this.mInvertDeviceOrientationReadings ? 360 - this.getDeviceRotation() : this.getDeviceRotation();
        return (this.mCameraNativeOrientation + v0) % 360;
    }

    protected static FramerateRange getClosestFramerateRange(List arg1, int arg2) {
        return Collections.min(((Collection)arg1), new Comparator(arg2) {
            private static final int MAX_FPS_DIFF_THRESHOLD = 5000;
            private static final int MAX_FPS_HIGH_DIFF_WEIGHT = 3;
            private static final int MAX_FPS_LOW_DIFF_WEIGHT = 1;
            private static final int MIN_FPS_HIGH_VALUE_WEIGHT = 4;
            private static final int MIN_FPS_LOW_VALUE_WEIGHT = 1;
            private static final int MIN_FPS_THRESHOLD = 8000;

            public int compare(Object arg1, Object arg2) {
                return this.compare(((FramerateRange)arg1), ((FramerateRange)arg2));
            }

            public int compare(FramerateRange arg1, FramerateRange arg2) {
                return this.diff(arg1) - this.diff(arg2);
            }

            int diff(FramerateRange arg5) {
                return this.progressivePenalty(arg5.min, 8000, 1, 4) + this.progressivePenalty(Math.abs(this.val$targetFramerate - arg5.max), 5000, 1, 3);
            }

            private int progressivePenalty(int arg1, int arg2, int arg3, int arg4) {
                if(arg1 < arg2) {
                    arg1 *= arg3;
                }
                else {
                    arg1 = (arg1 - arg2) * arg4 + arg3 * arg2;
                }

                return arg1;
            }
        });
    }

    @CalledByNative public final int getColorspace() {
        int v0 = this.mCaptureFormat.mPixelFormat;
        int v1 = 17;
        if(v0 != v1) {
            v1 = 35;
            if(v0 != v1) {
                v1 = 842094169;
                if(v0 != v1) {
                    return 0;
                }

                return v1;
            }

            return v1;
        }

        return v1;
    }

    protected final int getDeviceRotation() {
        int v0;
        switch(ContextUtils.getApplicationContext().getSystemService("window").getDefaultDisplay().getRotation()) {
            case 1: {
                v0 = 90;
                break;
            }
            case 2: {
                v0 = 180;
                break;
            }
            case 3: {
                v0 = 270;
                break;
            }
            default: {
                v0 = 0;
                break;
            }
        }

        return v0;
    }

    @CalledByNative public abstract PhotoCapabilities getPhotoCapabilities();

    protected static int[] integerArrayListToArray(ArrayList arg3) {
        int[] v0 = new int[arg3.size()];
        int v1;
        for(v1 = 0; v1 < arg3.size(); ++v1) {
            v0[v1] = arg3.get(v1).intValue();
        }

        return v0;
    }

    public native void nativeOnError(long arg1, String arg2) {
    }

    public native void nativeOnFrameAvailable(long arg1, byte[] arg2, int arg3, int arg4) {
    }

    public native void nativeOnI420FrameAvailable(long arg1, ByteBuffer arg2, int arg3, ByteBuffer arg4, ByteBuffer arg5, int arg6, int arg7, int arg8, int arg9, int arg10, long arg11) {
    }

    public native void nativeOnPhotoTaken(long arg1, long arg2, byte[] arg3) {
    }

    public native void nativeOnStarted(long arg1) {
    }

    @CalledByNative public final int queryFrameRate() {
        return this.mCaptureFormat.mFramerate;
    }

    @CalledByNative public final int queryHeight() {
        return this.mCaptureFormat.mHeight;
    }

    @CalledByNative public final int queryWidth() {
        return this.mCaptureFormat.mWidth;
    }

    @CalledByNative public abstract void setPhotoOptions(double arg1, int arg2, int arg3, double arg4, double arg5, float[] arg6, boolean arg7, double arg8, int arg9, double arg10, boolean arg11, boolean arg12, int arg13, boolean arg14, boolean arg15, double arg16);

    @CalledByNative public final void setTestMode() {
        this.mUseBackgroundThreadForTesting = true;
    }

    @CalledByNative public abstract boolean startCapture();

    @CalledByNative public abstract boolean stopCapture();

    @CalledByNative public abstract boolean takePhoto(long arg1);
}

