package org.chromium.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap$Config;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Build$VERSION;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode$ThreadPolicy;
import android.os.StrictMode;
import android.text.TextUtils;
import android.view.SurfaceView;
import android.view.View$MeasureSpec;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView$LayoutParams;
import android.widget.ListAdapter;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import org.chromium.base.ApiCompatibilityUtils;
import org.chromium.base.Log;

public class UiUtils {
    public interface KeyboardShowingDelegate {
        boolean disableKeyboardCheck(Context arg1, View arg2);
    }

    public interface PhotoPickerDelegate {
        void onPhotoPickerDismissed();

        void showPhotoPicker(Context arg1, PhotoPickerListener arg2, boolean arg3, List arg4);
    }

    public static final String EXTERNAL_IMAGE_FILE_PATH = "browser-images";
    public static final String IMAGE_FILE_PATH = "images";
    private static final float KEYBOARD_DETECT_BOTTOM_THRESHOLD_DP = 100f;
    private static final int KEYBOARD_RETRY_ATTEMPTS = 10;
    private static final long KEYBOARD_RETRY_DELAY_MS = 100;
    private static final String TAG = "UiUtils";
    private static KeyboardShowingDelegate sKeyboardShowingDelegate;
    private static PhotoPickerDelegate sPhotoPickerDelegate;

    private UiUtils() {
        super();
    }

    public static int computeMaxWidthOfListAdapterItems(ListAdapter arg9) {
        View v6_1;
        int v0 = 0;
        int v1 = View$MeasureSpec.makeMeasureSpec(0, 0);
        int v2 = View$MeasureSpec.makeMeasureSpec(0, 0);
        AbsListView$LayoutParams v3 = new AbsListView$LayoutParams(-2, -2);
        View[] v4 = new View[arg9.getViewTypeCount()];
        int v5 = 0;
        while(v0 < arg9.getCount()) {
            int v6 = arg9.getItemViewType(v0);
            View v7 = null;
            if(v6 < 0) {
                v6_1 = arg9.getView(v0, v7, ((ViewGroup)v7));
            }
            else {
                v4[v6] = arg9.getView(v0, v4[v6], ((ViewGroup)v7));
                v6_1 = v4[v6];
            }

            v6_1.setLayoutParams(((ViewGroup$LayoutParams)v3));
            v6_1.measure(v1, v2);
            v5 = Math.max(v5, v6_1.getMeasuredWidth());
            ++v0;
        }

        return v5;
    }

    public static Typeface createRobotoMediumTypeface() {
        if(Build$VERSION.SDK_INT >= 21) {
            return Typeface.create("sans-serif-medium", 0);
        }

        return Typeface.create("sans-serif", 1);
    }

    public static Bitmap generateScaledScreenshot(View arg13, int arg14, Bitmap$Config arg15) {
        Bitmap v15;
        Bitmap v14_1;
        boolean v0 = arg13.isDrawingCacheEnabled();
        Bitmap v3 = null;
        try {
            UiUtils.prepareViewHierarchyForScreenshot(arg13, true);
            if(!v0) {
                arg13.setDrawingCacheEnabled(true);
            }

            Bitmap v4 = arg13.getDrawingCache();
            if(v4 != null) {
                double v5 = ((double)v4.getHeight());
                double v7 = ((double)v4.getWidth());
                int v9 = ((int)v7);
                int v10 = ((int)v5);
                if(arg14 > 0) {
                    double v9_1 = (((double)arg14)) / Math.max(v7, v5);
                    arg14 = ((int)Math.round(v7 * v9_1));
                    v10 = ((int)Math.round(v5 * v9_1));
                }
                else {
                    arg14 = v9;
                }

                v14_1 = Bitmap.createScaledBitmap(v4, arg14, v10, true);
                if(v14_1.getConfig() == arg15) {
                    goto label_68;
                }

                v15 = v14_1.copy(arg15, false);
            }
            else {
                goto label_37;
            }
        }
        catch(OutOfMemoryError v14) {
            goto label_76;
        }

        try {
            v14_1.recycle();
            v3 = v15;
            goto label_69;
        }
        catch(OutOfMemoryError v14) {
            v3 = v15;
            goto label_76;
        }

        try {
        label_37:
            if(arg13.getMeasuredHeight() <= 0) {
                goto label_69;
            }

            if(arg13.getMeasuredWidth() <= 0) {
                goto label_69;
            }

            double v4_1 = ((double)arg13.getMeasuredHeight());
            double v6 = ((double)arg13.getMeasuredWidth());
            int v1 = ((int)v6);
            int v8 = ((int)v4_1);
            if(arg14 > 0) {
                double v8_1 = (((double)arg14)) / Math.max(v6, v4_1);
                v1 = ((int)Math.round(v6 * v8_1));
                v8 = ((int)Math.round(v8_1 * v4_1));
            }

            v14_1 = Bitmap.createBitmap(v1, v8, arg15);
            Canvas v15_1 = new Canvas(v14_1);
            v15_1.scale(((float)((((double)v1)) / v6)), ((float)((((double)v8)) / v4_1)));
            arg13.draw(v15_1);
        }
        catch(Throwable v14_2) {
        label_88:
            if(!v0) {
                arg13.setDrawingCacheEnabled(false);
            }

            UiUtils.prepareViewHierarchyForScreenshot(arg13, false);
            throw v14_2;
        }
        catch(OutOfMemoryError v14) {
            try {
            label_76:
                Log.d("UiUtils", "Unable to capture screenshot and scale it down." + v14.getMessage());
                if(v0) {
                    goto label_71;
                }
            }
            catch(Throwable v14_2) {
                goto label_88;
            }

            goto label_70;
        }

    label_68:
        v3 = v14_1;
    label_69:
        if(!v0) {
        label_70:
            arg13.setDrawingCacheEnabled(false);
        }

    label_71:
        UiUtils.prepareViewHierarchyForScreenshot(arg13, false);
        return v3;
    }

    public static int getChildIndexInParent(View arg4) {
        int v1 = -1;
        if(arg4.getParent() == null) {
            return v1;
        }

        ViewParent v0 = arg4.getParent();
        int v2 = 0;
        while(v2 < ((ViewGroup)v0).getChildCount()) {
            if(((ViewGroup)v0).getChildAt(v2) == arg4) {
                v1 = v2;
            }
            else {
                ++v2;
                continue;
            }

            return v1;
        }

        return v1;
    }

    public static File getDirectoryForImageCapture(Context arg4) throws IOException {
        File v4_1;
        File v1;
        StrictMode$ThreadPolicy v0 = StrictMode.allowThreadDiskReads();
        try {
            if(Build$VERSION.SDK_INT >= 18) {
                v1 = new File(arg4.getFilesDir(), "images");
                if(!v1.exists() && !v1.mkdir()) {
                    throw new IOException("Folder cannot be created.");
                }
            }
            else {
                v1 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
                StringBuilder v2 = new StringBuilder();
                v2.append(v1.getAbsolutePath());
                v2.append(File.separator);
                v2.append("browser-images");
                v4_1 = new File(v2.toString());
                if(!v4_1.exists()) {
                    if(v4_1.mkdirs()) {
                        goto label_34;
                    }

                    goto label_33;
                }

                goto label_34;
            }

            goto label_35;
        }
        catch(Throwable v4) {
            StrictMode.setThreadPolicy(v0);
            throw v4;
        }

    label_33:
        goto label_35;
    label_34:
        v1 = v4_1;
    label_35:
        StrictMode.setThreadPolicy(v0);
        return v1;
    }

    public static Set getIMELocales(Context arg8) {
        LinkedHashSet v0 = new LinkedHashSet();
        Object v8 = arg8.getSystemService("input_method");
        List v1 = ((InputMethodManager)v8).getEnabledInputMethodList();
        int v3;
        for(v3 = 0; v3 < v1.size(); ++v3) {
            List v4 = ((InputMethodManager)v8).getEnabledInputMethodSubtypeList(v1.get(v3), true);
            if(v4 == null) {
            }
            else {
                int v5;
                for(v5 = 0; v5 < v4.size(); ++v5) {
                    String v6 = ApiCompatibilityUtils.getLocale(v4.get(v5));
                    if(!TextUtils.isEmpty(((CharSequence)v6))) {
                        v0.add(v6);
                    }
                }
            }
        }

        return ((Set)v0);
    }

    public static boolean hideKeyboard(View arg2) {
        return arg2.getContext().getSystemService("input_method").hideSoftInputFromWindow(arg2.getWindowToken(), 0);
    }

    public static int insertAfter(ViewGroup arg1, View arg2, View arg3) {
        return UiUtils.insertView(arg1, arg2, arg3, true);
    }

    public static int insertBefore(ViewGroup arg1, View arg2, View arg3) {
        return UiUtils.insertView(arg1, arg2, arg3, false);
    }

    private static int insertView(ViewGroup arg1, View arg2, View arg3, boolean arg4) {
        int v0 = arg1.indexOfChild(arg2);
        if(v0 >= 0) {
            return v0;
        }

        int v3 = arg1.indexOfChild(arg3);
        if(v3 < 0) {
            return -1;
        }

        if(arg4) {
            ++v3;
        }

        arg1.addView(arg2, v3);
        return v3;
    }

    @SuppressLint(value={"NewApi"}) public static boolean isKeyboardShowing(Context arg5, View arg6) {
        boolean v1 = false;
        if(UiUtils.sKeyboardShowingDelegate != null && (UiUtils.sKeyboardShowingDelegate.disableKeyboardCheck(arg5, arg6))) {
            return 0;
        }

        arg6 = arg6.getRootView();
        if(arg6 == null) {
            return 0;
        }

        Rect v0 = new Rect();
        arg6.getWindowVisibleDisplayFrame(v0);
        int v3 = arg6.getHeight() - (v0.height() + v0.top);
        if(v3 <= 0) {
            return 0;
        }

        int v0_1 = v0.width() != arg6.getWidth() ? 1 : 0;
        if(v0_1 == 0) {
            if(Build$VERSION.SDK_INT >= 23) {
                v3 -= arg6.getRootWindowInsets().getStableInsetBottom();
            }
            else {
                v3 = ((int)((((float)v3)) - arg5.getResources().getDisplayMetrics().density * 100f));
            }
        }

        if(v3 > 0) {
            v1 = true;
        }

        return v1;
    }

    public static void onPhotoPickerDismissed() {
        if(UiUtils.sPhotoPickerDelegate == null) {
            return;
        }

        UiUtils.sPhotoPickerDelegate.onPhotoPickerDismissed();
    }

    private static void prepareViewHierarchyForScreenshot(View arg2, boolean arg3) {
        if((arg2 instanceof ViewGroup)) {
            int v0;
            for(v0 = 0; v0 < ((ViewGroup)arg2).getChildCount(); ++v0) {
                UiUtils.prepareViewHierarchyForScreenshot(((ViewGroup)arg2).getChildAt(v0), arg3);
            }
        }
        else if((arg2 instanceof SurfaceView)) {
            arg2.setWillNotDraw((((int)arg3)) ^ 1);
        }
    }

    public static void removeViewFromParent(View arg1) {
        ViewParent v0 = arg1.getParent();
        if(v0 == null) {
            return;
        }

        ((ViewGroup)v0).removeView(arg1);
    }

    public static void setKeyboardShowingDelegate(KeyboardShowingDelegate arg0) {
        UiUtils.sKeyboardShowingDelegate = arg0;
    }

    public static void setPhotoPickerDelegate(PhotoPickerDelegate arg0) {
        UiUtils.sPhotoPickerDelegate = arg0;
    }

    public static boolean shouldShowPhotoPicker() {
        boolean v0 = UiUtils.sPhotoPickerDelegate != null ? true : false;
        return v0;
    }

    public static void showKeyboard(View arg3) {
        new Runnable(arg3, new AtomicInteger(), new Handler()) {
            public void run() {
                Object v0 = this.val$view.getContext().getSystemService("input_method");
                StrictMode$ThreadPolicy v1 = StrictMode.allowThreadDiskWrites();
                try {
                    ((InputMethodManager)v0).showSoftInput(this.val$view, 0);
                }
                catch(Throwable v0_1) {
                }
                catch(IllegalArgumentException v0_2) {
                    try {
                        if(this.val$attempt.incrementAndGet() <= 10) {
                            this.val$handler.postDelayed(((Runnable)this), 100);
                            goto label_8;
                        }

                        Log.e("UiUtils", "Unable to open keyboard.  Giving up.", new Object[]{v0_2});
                    }
                    catch(Throwable v0_1) {
                        StrictMode.setThreadPolicy(v1);
                        throw v0_1;
                    }
                }

            label_8:
                StrictMode.setThreadPolicy(v1);
            }
        }.run();
    }

    public static boolean showPhotoPicker(Context arg1, PhotoPickerListener arg2, boolean arg3, List arg4) {
        if(UiUtils.sPhotoPickerDelegate == null) {
            return 0;
        }

        UiUtils.sPhotoPickerDelegate.showPhotoPicker(arg1, arg2, arg3, arg4);
        return 1;
    }
}

