package org.chromium.media;

import android.os.Build$VERSION;
import org.chromium.base.ContextUtils;
import org.chromium.base.Log;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="media") class VideoCaptureFactory {
    class ChromiumCameraInfo {
        private static final String TAG = "cr.media";
        private static int sNumberOfSystemCameras = -1;

        static {
        }

        ChromiumCameraInfo() {
            super();
        }

        static int access$100() {
            return ChromiumCameraInfo.getNumberOfCameras();
        }

        private static int getNumberOfCameras() {
            if(ChromiumCameraInfo.sNumberOfSystemCameras == -1) {
                if(Build$VERSION.SDK_INT < 23 && ContextUtils.getApplicationContext().getPackageManager().checkPermission("android.permission.CAMERA", ContextUtils.getApplicationContext().getPackageName()) != 0) {
                    ChromiumCameraInfo.sNumberOfSystemCameras = 0;
                    Log.w("cr.media", "Missing android.permission.CAMERA permission, no system camera available.", new Object[0]);
                    goto label_27;
                }

                if(VideoCaptureFactory.isLReleaseOrLater()) {
                    ChromiumCameraInfo.sNumberOfSystemCameras = VideoCaptureCamera2.getNumberOfCameras();
                    goto label_27;
                }

                ChromiumCameraInfo.sNumberOfSystemCameras = VideoCaptureCamera.getNumberOfCameras();
            }

        label_27:
            return ChromiumCameraInfo.sNumberOfSystemCameras;
        }
    }

    VideoCaptureFactory() {
        super();
    }

    static boolean access$000() {
        return VideoCaptureFactory.isLReleaseOrLater();
    }

    @CalledByNative static VideoCapture createVideoCapture(int arg1, long arg2) {
        if((VideoCaptureFactory.isLReleaseOrLater()) && !VideoCaptureCamera2.isLegacyDevice(arg1)) {
            return new VideoCaptureCamera2(arg1, arg2);
        }

        return new VideoCaptureCamera(arg1, arg2);
    }

    @CalledByNative static int getCaptureApiType(int arg1) {
        if(VideoCaptureFactory.isLReleaseOrLater()) {
            return VideoCaptureCamera2.getCaptureApiType(arg1);
        }

        return VideoCaptureCamera.getCaptureApiType(arg1);
    }

    @CalledByNative static int getCaptureFormatFramerate(VideoCaptureFormat arg0) {
        return arg0.getFramerate();
    }

    @CalledByNative static int getCaptureFormatHeight(VideoCaptureFormat arg0) {
        return arg0.getHeight();
    }

    @CalledByNative static int getCaptureFormatPixelFormat(VideoCaptureFormat arg0) {
        return arg0.getPixelFormat();
    }

    @CalledByNative static int getCaptureFormatWidth(VideoCaptureFormat arg0) {
        return arg0.getWidth();
    }

    @CalledByNative static String getDeviceName(int arg1) {
        if((VideoCaptureFactory.isLReleaseOrLater()) && !VideoCaptureCamera2.isLegacyDevice(arg1)) {
            return VideoCaptureCamera2.getName(arg1);
        }

        return VideoCaptureCamera.getName(arg1);
    }

    @CalledByNative static VideoCaptureFormat[] getDeviceSupportedFormats(int arg1) {
        if((VideoCaptureFactory.isLReleaseOrLater()) && !VideoCaptureCamera2.isLegacyDevice(arg1)) {
            return VideoCaptureCamera2.getDeviceSupportedFormats(arg1);
        }

        return VideoCaptureCamera.getDeviceSupportedFormats(arg1);
    }

    @CalledByNative static int getNumberOfCameras() {
        return ChromiumCameraInfo.access$100();
    }

    private static boolean isLReleaseOrLater() {
        boolean v0 = Build$VERSION.SDK_INT >= 21 ? true : false;
        return v0;
    }

    @CalledByNative static boolean isLegacyOrDeprecatedDevice(int arg1) {
        boolean v1 = !VideoCaptureFactory.isLReleaseOrLater() || (VideoCaptureCamera2.isLegacyDevice(arg1)) ? true : false;
        return v1;
    }
}

