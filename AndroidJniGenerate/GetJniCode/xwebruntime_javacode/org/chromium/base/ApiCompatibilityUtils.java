package org.chromium.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager$TaskDescription;
import android.app.ActivityOptions;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources$NotFoundException;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.Process;
import android.os.StatFs;
import android.os.StrictMode$ThreadPolicy;
import android.os.StrictMode;
import android.provider.Settings$Global;
import android.provider.Settings$Secure;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.view.ViewGroup$MarginLayoutParams;
import android.view.Window;
import android.view.inputmethod.InputMethodSubtype;
import android.view.textclassifier.TextClassifier;
import android.widget.TextView;
import java.io.File;
import java.io.UnsupportedEncodingException;

@TargetApi(value=21) public class ApiCompatibilityUtils {
    class FinishAndRemoveTaskWithRetry implements Runnable {
        private static final long MAX_TRY_COUNT = 3;
        private static final long RETRY_DELAY_MS = 500;
        private final Activity mActivity;
        private int mTryCount;

        FinishAndRemoveTaskWithRetry(Activity arg1) {
            super();
            this.mActivity = arg1;
        }

        public void run() {
            this.mActivity.finishAndRemoveTask();
            ++this.mTryCount;
            if(!this.mActivity.isFinishing()) {
                if((((long)this.mTryCount)) < 3) {
                    ThreadUtils.postOnUiThreadDelayed(((Runnable)this), 500);
                }
                else {
                    this.mActivity.finish();
                }
            }
        }
    }

    static {
    }

    private ApiCompatibilityUtils() {
        super();
    }

    public static int checkPermission(Context arg0, String arg1, int arg2, int arg3) {
        try {
            return arg0.checkPermission(arg1, arg2, arg3);
        }
        catch(RuntimeException ) {
            return -1;
        }
    }

    public static int compareBoolean(boolean arg0, boolean arg1) {
        int v0;
        if(arg0 == arg1) {
            v0 = 0;
        }
        else if(arg0) {
            v0 = 1;
        }
        else {
            v0 = -1;
        }

        return v0;
    }

    public static int compareLong(long arg1, long arg3) {
        int v1;
        if(arg1 < arg3) {
            v1 = -1;
        }
        else if(arg1 == arg3) {
            v1 = 0;
        }
        else {
            v1 = 1;
        }

        return v1;
    }

    public static Bundle createLaunchDisplayIdActivityOptions(int arg2) {
        if(Build$VERSION.SDK_INT < 26) {
            return null;
        }

        ActivityOptions v0 = ActivityOptions.makeBasic();
        v0.setLaunchDisplayId(arg2);
        return v0.toBundle();
    }

    @TargetApi(value=26) public static void disableSmartSelectionTextClassifier(TextView arg2) {
        if(Build$VERSION.SDK_INT < 26) {
            return;
        }

        arg2.setTextClassifier(TextClassifier.NO_OP);
    }

    public static void finishAfterTransition(Activity arg2) {
        if(Build$VERSION.SDK_INT >= 21) {
            arg2.finishAfterTransition();
        }
        else {
            arg2.finish();
        }
    }

    public static void finishAndRemoveTask(Activity arg2) {
        int v1 = 21;
        if(Build$VERSION.SDK_INT > v1) {
            arg2.finishAndRemoveTask();
        }
        else if(Build$VERSION.SDK_INT == v1) {
            new FinishAndRemoveTaskWithRetry(arg2).run();
        }
        else {
            arg2.finish();
        }
    }

    public static int getActivityNewDocumentFlag() {
        int v1 = 0x80000;
        if(Build$VERSION.SDK_INT >= 21) {
            return v1;
        }

        return v1;
    }

    public static long getAvailableBlocks(StatFs arg2) {
        if(Build$VERSION.SDK_INT >= 19) {
            return arg2.getAvailableBlocksLong();
        }

        return ((long)arg2.getAvailableBlocks());
    }

    public static long getBlockCount(StatFs arg2) {
        if(Build$VERSION.SDK_INT >= 19) {
            return arg2.getBlockCountLong();
        }

        return ((long)arg2.getBlockCount());
    }

    public static long getBlockSize(StatFs arg2) {
        if(Build$VERSION.SDK_INT >= 19) {
            return arg2.getBlockSizeLong();
        }

        return ((long)arg2.getBlockSize());
    }

    public static byte[] getBytesUtf8(String arg2) {
        try {
            return arg2.getBytes("UTF-8");
        }
        catch(UnsupportedEncodingException v2) {
            throw new IllegalStateException("UTF-8 encoding not available.", ((Throwable)v2));
        }
    }

    public static int getColor(Resources arg2, int arg3) throws Resources$NotFoundException {
        if(Build$VERSION.SDK_INT >= 23) {
            return arg2.getColor(arg3, null);
        }

        return arg2.getColor(arg3);
    }

    public static ColorFilter getColorFilter(Drawable arg2) {
        if(Build$VERSION.SDK_INT >= 21) {
            return arg2.getColorFilter();
        }

        return null;
    }

    public static ColorStateList getColorStateList(Resources arg2, int arg3) throws Resources$NotFoundException {
        if(Build$VERSION.SDK_INT >= 23) {
            return arg2.getColorStateList(arg3, null);
        }

        return arg2.getColorStateList(arg3);
    }

    public static String getCreatorPackage(PendingIntent arg2) {
        if(Build$VERSION.SDK_INT >= 17) {
            return arg2.getCreatorPackage();
        }

        return arg2.getTargetPackage();
    }

    public static Drawable getDrawable(Resources arg3, int arg4) throws Resources$NotFoundException {
        Drawable v3_1;
        StrictMode$ThreadPolicy v0 = StrictMode.allowThreadDiskReads();
        try {
            if(Build$VERSION.SDK_INT < 21) {
                goto label_8;
            }

            v3_1 = arg3.getDrawable(arg4, null);
        }
        catch(Throwable v3) {
            goto label_12;
        }

        StrictMode.setThreadPolicy(v0);
        return v3_1;
        try {
        label_8:
            v3_1 = arg3.getDrawable(arg4);
        }
        catch(Throwable v3) {
        label_12:
            StrictMode.setThreadPolicy(v0);
            throw v3;
        }

        StrictMode.setThreadPolicy(v0);
        return v3_1;
    }

    public static Drawable getDrawableForDensity(Resources arg2, int arg3, int arg4) {
        if(Build$VERSION.SDK_INT >= 21) {
            return arg2.getDrawableForDensity(arg3, arg4, null);
        }

        return arg2.getDrawableForDensity(arg3, arg4);
    }

    public static int getLayoutDirection(Configuration arg2) {
        if(Build$VERSION.SDK_INT >= 17) {
            return arg2.getLayoutDirection();
        }

        return 0;
    }

    public static String getLocale(InputMethodSubtype arg2) {
        if(Build$VERSION.SDK_INT >= 24) {
            return arg2.getLanguageTag();
        }

        return arg2.getLocale();
    }

    public static int getMarginEnd(ViewGroup$MarginLayoutParams arg2) {
        if(Build$VERSION.SDK_INT >= 17) {
            return arg2.getMarginEnd();
        }

        return arg2.rightMargin;
    }

    public static int getMarginStart(ViewGroup$MarginLayoutParams arg2) {
        if(Build$VERSION.SDK_INT >= 17) {
            return arg2.getMarginStart();
        }

        return arg2.leftMargin;
    }

    public static int getPaddingEnd(View arg2) {
        if(Build$VERSION.SDK_INT >= 17) {
            return arg2.getPaddingEnd();
        }

        return arg2.getPaddingRight();
    }

    public static int getPaddingStart(View arg2) {
        if(Build$VERSION.SDK_INT >= 17) {
            return arg2.getPaddingStart();
        }

        return arg2.getPaddingLeft();
    }

    public static Uri getUriForDownloadedFile(File arg2) {
        Uri v2 = Build$VERSION.SDK_INT > 23 ? FileUtils.getUriForFile(arg2) : Uri.fromFile(arg2);
        return v2;
    }

    public static Uri getUriForImageCaptureFile(File arg2) {
        Uri v2 = Build$VERSION.SDK_INT >= 18 ? ContentUriUtils.getContentUriFromFile(arg2) : Uri.fromFile(arg2);
        return v2;
    }

    public static Drawable getUserBadgedDrawableForDensity(Context arg2, Drawable arg3, Rect arg4, int arg5) {
        if(Build$VERSION.SDK_INT >= 21) {
            return arg2.getPackageManager().getUserBadgedDrawableForDensity(arg3, Process.myUserHandle(), arg4, arg5);
        }

        return arg3;
    }

    public static Drawable getUserBadgedIcon(Context arg2, int arg3) {
        Drawable v3 = ApiCompatibilityUtils.getDrawable(arg2.getResources(), arg3);
        if(Build$VERSION.SDK_INT >= 21) {
            v3 = arg2.getPackageManager().getUserBadgedIcon(v3, Process.myUserHandle());
        }

        return v3;
    }

    public static boolean isDemoUser(Context arg2) {
        if(Build$VERSION.SDK_INT < 25) {
            return 0;
        }

        return arg2.getSystemService("user").isDemoUser();
    }

    @TargetApi(value=17) public static boolean isDeviceProvisioned(Context arg3) {
        boolean v1 = true;
        if(Build$VERSION.SDK_INT < 17) {
            return 1;
        }

        if(arg3 == null) {
            return 1;
        }

        if(arg3.getContentResolver() == null) {
            return 1;
        }

        if(Settings$Global.getInt(arg3.getContentResolver(), "device_provisioned", 0) != 0) {
        }
        else {
            v1 = false;
        }

        return v1;
    }

    public static boolean isElevationSupported() {
        boolean v0 = Build$VERSION.SDK_INT >= 21 ? true : false;
        return v0;
    }

    public static boolean isInMultiWindowMode(Activity arg2) {
        if(Build$VERSION.SDK_INT < 24) {
            return 0;
        }

        return arg2.isInMultiWindowMode();
    }

    public static boolean isInteractive(Context arg2) {
        Object v2 = arg2.getSystemService("power");
        if(Build$VERSION.SDK_INT >= 20) {
            return ((PowerManager)v2).isInteractive();
        }

        return ((PowerManager)v2).isScreenOn();
    }

    public static boolean isLayoutRtl(View arg3) {
        if(Build$VERSION.SDK_INT >= 17) {
            boolean v0 = true;
            if(arg3.getLayoutDirection() == 1) {
            }
            else {
                v0 = false;
            }

            return v0;
        }

        return 0;
    }

    public static boolean isPrintingSupported() {
        boolean v0 = Build$VERSION.SDK_INT >= 19 ? true : false;
        return v0;
    }

    public static void setCompoundDrawablesRelative(TextView arg2, Drawable arg3, Drawable arg4, Drawable arg5, Drawable arg6) {
        int v1 = 17;
        if(Build$VERSION.SDK_INT == v1) {
            boolean v0 = ApiCompatibilityUtils.isLayoutRtl(((View)arg2));
            Drawable v1_1 = v0 ? arg5 : arg3;
            if(v0) {
            }
            else {
                arg3 = arg5;
            }

            arg2.setCompoundDrawables(v1_1, arg4, arg3, arg6);
        }
        else {
            if(Build$VERSION.SDK_INT > v1) {
                arg2.setCompoundDrawablesRelative(arg3, arg4, arg5, arg6);
                return;
            }

            arg2.setCompoundDrawables(arg3, arg4, arg5, arg6);
        }
    }

    public static void setCompoundDrawablesRelativeWithIntrinsicBounds(TextView arg2, int arg3, int arg4, int arg5, int arg6) {
        int v1 = 17;
        if(Build$VERSION.SDK_INT == v1) {
            boolean v0 = ApiCompatibilityUtils.isLayoutRtl(((View)arg2));
            v1 = v0 ? arg5 : arg3;
            if(v0) {
            }
            else {
                arg3 = arg5;
            }

            arg2.setCompoundDrawablesWithIntrinsicBounds(v1, arg4, arg3, arg6);
        }
        else {
            if(Build$VERSION.SDK_INT > v1) {
                arg2.setCompoundDrawablesRelativeWithIntrinsicBounds(arg3, arg4, arg5, arg6);
                return;
            }

            arg2.setCompoundDrawablesWithIntrinsicBounds(arg3, arg4, arg5, arg6);
        }
    }

    public static void setCompoundDrawablesRelativeWithIntrinsicBounds(TextView arg2, Drawable arg3, Drawable arg4, Drawable arg5, Drawable arg6) {
        int v1 = 17;
        if(Build$VERSION.SDK_INT == v1) {
            boolean v0 = ApiCompatibilityUtils.isLayoutRtl(((View)arg2));
            Drawable v1_1 = v0 ? arg5 : arg3;
            if(v0) {
            }
            else {
                arg3 = arg5;
            }

            arg2.setCompoundDrawablesWithIntrinsicBounds(v1_1, arg4, arg3, arg6);
        }
        else {
            if(Build$VERSION.SDK_INT > v1) {
                arg2.setCompoundDrawablesRelativeWithIntrinsicBounds(arg3, arg4, arg5, arg6);
                return;
            }

            arg2.setCompoundDrawablesWithIntrinsicBounds(arg3, arg4, arg5, arg6);
        }
    }

    @TargetApi(value=21) public static boolean setElevation(View arg1, float arg2) {
        if(!ApiCompatibilityUtils.isElevationSupported()) {
            return 0;
        }

        arg1.setElevation(arg2);
        return 1;
    }

    public static void setLabelFor(View arg2, int arg3) {
        if(Build$VERSION.SDK_INT >= 17) {
            arg2.setLabelFor(arg3);
        }
    }

    public static void setLayoutDirection(View arg2, int arg3) {
        if(Build$VERSION.SDK_INT >= 17) {
            arg2.setLayoutDirection(arg3);
        }
    }

    public static void setMarginEnd(ViewGroup$MarginLayoutParams arg2, int arg3) {
        if(Build$VERSION.SDK_INT >= 17) {
            arg2.setMarginEnd(arg3);
        }
        else {
            arg2.rightMargin = arg3;
        }
    }

    public static void setMarginStart(ViewGroup$MarginLayoutParams arg2, int arg3) {
        if(Build$VERSION.SDK_INT >= 17) {
            arg2.setMarginStart(arg3);
        }
        else {
            arg2.leftMargin = arg3;
        }
    }

    public static void setPaddingRelative(View arg2, int arg3, int arg4, int arg5, int arg6) {
        if(Build$VERSION.SDK_INT >= 17) {
            arg2.setPaddingRelative(arg3, arg4, arg5, arg6);
        }
        else {
            arg2.setPadding(arg3, arg4, arg5, arg6);
        }
    }

    public static void setStatusBarColor(Window arg2, int arg3) {
        if(Build$VERSION.SDK_INT < 21) {
            return;
        }

        if(Build$VERSION.SDK_INT < 26) {
            int v0 = 0xFF000000;
            if(arg3 != v0) {
                goto label_12;
            }
            else if(arg2.getNavigationBarColor() == v0) {
            }
            else {
                goto label_12;
            }
        }
        else {
        label_12:
            arg2.addFlags(0x80000000);
        }

        arg2.setStatusBarColor(arg3);
    }

    public static void setTaskDescription(Activity arg2, String arg3, Bitmap arg4, int arg5) {
        if(Build$VERSION.SDK_INT >= 21) {
            arg2.setTaskDescription(new ActivityManager$TaskDescription(arg3, arg4, arg5));
        }
    }

    public static void setTextAlignment(View arg2, int arg3) {
        if(Build$VERSION.SDK_INT >= 17) {
            arg2.setTextAlignment(arg3);
        }
    }

    public static void setTextAppearance(TextView arg2, int arg3) {
        if(Build$VERSION.SDK_INT >= 23) {
            arg2.setTextAppearance(arg3);
        }
        else {
            arg2.setTextAppearance(arg2.getContext(), arg3);
        }
    }

    public static void setTextDirection(View arg2, int arg3) {
        if(Build$VERSION.SDK_INT >= 17) {
            arg2.setTextDirection(arg3);
        }
    }

    public static void setWindowIndeterminateProgress(Window arg2) {
        if(Build$VERSION.SDK_INT < 21) {
            arg2.setFeatureInt(5, -2);
        }
    }

    public static boolean shouldSkipFirstUseHints(ContentResolver arg3) {
        boolean v1 = false;
        if(Build$VERSION.SDK_INT >= 21) {
            if(Settings$Secure.getInt(arg3, "skip_first_use_hints", 0) != 0) {
                v1 = true;
            }

            return v1;
        }

        return 0;
    }

    public static String toHtml(Spanned arg2, int arg3) {
        if(Build$VERSION.SDK_INT >= 24) {
            return Html.toHtml(arg2, arg3);
        }

        return Html.toHtml(arg2);
    }
}

