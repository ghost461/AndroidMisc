package org.chromium.content.browser;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager$NameNotFoundException;
import javax.annotation.Nullable;
import org.chromium.base.Log;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.content_public.browser.ScreenOrientationDelegate;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.display.DisplayAndroid;

@JNINamespace(value="content") public class ScreenOrientationProvider {
    private static final String TAG = "cr.ScreenOrientation";
    private static ScreenOrientationDelegate sDelegate;

    private ScreenOrientationProvider() {
        super();
    }

    private static int getOrientationFromWebScreenOrientations(byte arg3, @Nullable WindowAndroid arg4, Context arg5) {
        int v0 = -1;
        switch(arg3) {
            case 0: {
                return v0;
            }
            case 1: {
                return 1;
            }
            case 2: {
                return 9;
            }
            case 3: {
                return 0;
            }
            case 4: {
                return 8;
            }
            case 5: {
                return 10;
            }
            case 6: {
                return 6;
            }
            case 7: {
                return 7;
            }
            case 8: {
                goto label_9;
            }
        }

        Log.w("cr.ScreenOrientation", "Trying to lock to unsupported orientation!", new Object[0]);
        return v0;
    label_9:
        DisplayAndroid v3 = arg4 != null ? arg4.getDisplay() : DisplayAndroid.getNonMultiDisplay(arg5);
        int v4 = v3.getRotation();
        if(v4 != 0) {
            if(v4 == 2) {
            }
            else if(v3.getDisplayHeight() < v3.getDisplayWidth()) {
                return 1;
            }
            else {
                return 0;
            }
        }

        if(v3.getDisplayHeight() >= v3.getDisplayWidth()) {
            return 1;
        }

        return 0;
    }

    @CalledByNative static boolean isOrientationLockEnabled() {
        boolean v0 = ScreenOrientationProvider.sDelegate == null || (ScreenOrientationProvider.sDelegate.canLockOrientation()) ? true : false;
        return v0;
    }

    @CalledByNative public static void lockOrientation(@Nullable WindowAndroid arg1, byte arg2) {
        if(ScreenOrientationProvider.sDelegate != null && !ScreenOrientationProvider.sDelegate.canLockOrientation()) {
            return;
        }

        if(arg1 == null) {
            return;
        }

        Object v0 = arg1.getActivity().get();
        if(v0 == null) {
            return;
        }

        int v1 = ScreenOrientationProvider.getOrientationFromWebScreenOrientations(arg2, arg1, ((Context)v0));
        if(v1 == -1) {
            return;
        }

        ((Activity)v0).setRequestedOrientation(v1);
    }

    public static void setOrientationDelegate(ScreenOrientationDelegate arg0) {
        ScreenOrientationProvider.sDelegate = arg0;
    }

    @CalledByNative public static void unlockOrientation(@Nullable WindowAndroid arg4) {
        if(arg4 == null) {
            return;
        }

        Object v0 = arg4.getActivity().get();
        if(v0 == null) {
            return;
        }

        int v4 = ScreenOrientationProvider.getOrientationFromWebScreenOrientations(((byte)((Activity)v0).getIntent().getIntExtra("org.chromium.content_public.common.orientation", 0)), arg4, ((Context)v0));
        if(v4 == -1) {
            try {
                v4 = ((Activity)v0).getPackageManager().getActivityInfo(((Activity)v0).getComponentName(), 0x80).screenOrientation;
            }
            catch(Throwable v1) {
                if(ScreenOrientationProvider.sDelegate == null || (ScreenOrientationProvider.sDelegate.canUnlockOrientation(((Activity)v0), v4))) {
                    ((Activity)v0).setRequestedOrientation(v4);
                }

                throw v1;
            }
            catch(PackageManager$NameNotFoundException ) {
                if(ScreenOrientationProvider.sDelegate == null) {
                    goto label_40;
                }
                else if(ScreenOrientationProvider.sDelegate.canUnlockOrientation(((Activity)v0), v4)) {
                    goto label_40;
                }
                else {
                    return;
                }
            }
        }

        if(ScreenOrientationProvider.sDelegate == null || (ScreenOrientationProvider.sDelegate.canUnlockOrientation(((Activity)v0), v4))) {
        label_40:
            ((Activity)v0).setRequestedOrientation(v4);
        }
    }
}

