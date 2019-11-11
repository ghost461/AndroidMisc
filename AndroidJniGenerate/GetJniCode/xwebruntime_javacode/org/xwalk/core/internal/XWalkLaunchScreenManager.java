package org.xwalk.core.internal;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface$OnDismissListener;
import android.content.DialogInterface$OnKeyListener;
import android.content.DialogInterface$OnShowListener;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Shader$TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.KeyEvent;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.widget.ImageView$ScaleType;
import android.widget.ImageView;
import android.widget.RelativeLayout$LayoutParams;
import android.widget.RelativeLayout;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.util.ArrayList;
import org.chromium.content.browser.ContentViewRenderView$FirstRenderedFrameListener;

public class XWalkLaunchScreenManager implements DialogInterface$OnDismissListener, DialogInterface$OnShowListener, FirstRenderedFrameListener, PageLoadListener {
    enum BorderModeType {
        public static final enum BorderModeType NONE;
        public static final enum BorderModeType REPEAT;
        public static final enum BorderModeType ROUND;
        public static final enum BorderModeType STRETCH;

        static {
            BorderModeType.REPEAT = new BorderModeType("REPEAT", 0);
            BorderModeType.STRETCH = new BorderModeType("STRETCH", 1);
            BorderModeType.ROUND = new BorderModeType("ROUND", 2);
            BorderModeType.NONE = new BorderModeType("NONE", 3);
            BorderModeType.$VALUES = new BorderModeType[]{BorderModeType.REPEAT, BorderModeType.STRETCH, BorderModeType.ROUND, BorderModeType.NONE};
        }

        private BorderModeType(String arg1, int arg2) {
            super(arg1, arg2);
        }

        public static BorderModeType valueOf(String arg1) {
            return Enum.valueOf(BorderModeType.class, arg1);
        }

        public static BorderModeType[] values() {
            return BorderModeType.$VALUES.clone();
        }
    }

    enum ReadyWhenType {
        public static final enum ReadyWhenType COMPLETE;
        public static final enum ReadyWhenType CUSTOM;
        public static final enum ReadyWhenType FIRST_PAINT;
        public static final enum ReadyWhenType USER_INTERACTIVE;

        static {
            ReadyWhenType.FIRST_PAINT = new ReadyWhenType("FIRST_PAINT", 0);
            ReadyWhenType.USER_INTERACTIVE = new ReadyWhenType("USER_INTERACTIVE", 1);
            ReadyWhenType.COMPLETE = new ReadyWhenType("COMPLETE", 2);
            ReadyWhenType.CUSTOM = new ReadyWhenType("CUSTOM", 3);
            ReadyWhenType.$VALUES = new ReadyWhenType[]{ReadyWhenType.FIRST_PAINT, ReadyWhenType.USER_INTERACTIVE, ReadyWhenType.COMPLETE, ReadyWhenType.CUSTOM};
        }

        private ReadyWhenType(String arg1, int arg2) {
            super(arg1, arg2);
        }

        public static ReadyWhenType valueOf(String arg1) {
            return Enum.valueOf(ReadyWhenType.class, arg1);
        }

        public static ReadyWhenType[] values() {
            return ReadyWhenType.$VALUES.clone();
        }
    }

    private static final String BORDER_MODE_REPEAT = "repeat";
    private static final String BORDER_MODE_ROUND = "round";
    private static final String BORDER_MODE_STRETCH = "stretch";
    private Activity mActivity;
    private Context mContext;
    private int mCurrentOrientation;
    private boolean mCustomHideLaunchScreen;
    private boolean mFirstFrameReceived;
    private static String mIntentFilterStr;
    private Dialog mLaunchScreenDialog;
    private BroadcastReceiver mLaunchScreenReadyWhenReceiver;
    private OrientationEventListener mOrientationListener;
    private boolean mPageLoadFinished;
    private ReadyWhenType mReadyWhen;
    private XWalkViewInternal mXWalkView;

    public XWalkLaunchScreenManager(Context arg1, XWalkViewInternal arg2) {
        super();
        this.mXWalkView = arg2;
        this.mContext = arg1;
        try {
            this.mActivity = this.mContext;
            goto label_5;
        }
        catch(ClassCastException ) {
        label_5:
            XWalkLaunchScreenManager.mIntentFilterStr = this.mContext.getPackageName() + ".hideLaunchScreen";
            return;
        }
    }

    static Context access$000(XWalkLaunchScreenManager arg0) {
        return arg0.mContext;
    }

    static Dialog access$100(XWalkLaunchScreenManager arg0) {
        return arg0.mLaunchScreenDialog;
    }

    static void access$1000(XWalkLaunchScreenManager arg0) {
        arg0.hideLaunchScreenWhenReady();
    }

    static Dialog access$102(XWalkLaunchScreenManager arg0, Dialog arg1) {
        arg0.mLaunchScreenDialog = arg1;
        return arg1;
    }

    static void access$200(XWalkLaunchScreenManager arg0) {
        arg0.performHideLaunchScreen();
    }

    static Activity access$300(XWalkLaunchScreenManager arg0) {
        return arg0.mActivity;
    }

    static RelativeLayout access$400(XWalkLaunchScreenManager arg0, String arg1) {
        return arg0.getLaunchScreenLayout(arg1);
    }

    static OrientationEventListener access$500(XWalkLaunchScreenManager arg0) {
        return arg0.mOrientationListener;
    }

    static OrientationEventListener access$502(XWalkLaunchScreenManager arg0, OrientationEventListener arg1) {
        arg0.mOrientationListener = arg1;
        return arg1;
    }

    static int access$600(XWalkLaunchScreenManager arg0) {
        return arg0.mCurrentOrientation;
    }

    static ReadyWhenType access$700(XWalkLaunchScreenManager arg0) {
        return arg0.mReadyWhen;
    }

    static void access$800(XWalkLaunchScreenManager arg0) {
        arg0.registerBroadcastReceiver();
    }

    static boolean access$902(XWalkLaunchScreenManager arg0, boolean arg1) {
        arg0.mCustomHideLaunchScreen = arg1;
        return arg1;
    }

    public void displayLaunchScreen(String arg2, String arg3) {
        if(this.mXWalkView != null) {
            if(this.mActivity == null) {
            }
            else {
                this.setReadyWhen(arg2);
                this.mActivity.runOnUiThread(new Runnable(arg3) {
                    public void run() {
                        Drawable v0_2;
                        int v0 = XWalkLaunchScreenManager.this.mContext.getResources().getIdentifier("launchscreen_bg", "drawable", XWalkLaunchScreenManager.this.mContext.getPackageName());
                        if(v0 == 0) {
                            return;
                        }

                        Drawable v1 = null;
                        try {
                            v0_2 = XWalkLaunchScreenManager.this.mContext.getResources().getDrawable(v0);
                        }
                        catch(OutOfMemoryError v0_1) {
                            ThrowableExtension.printStackTrace(((Throwable)v0_1));
                            v0_2 = v1;
                        }

                        if(v0_2 == null) {
                            return;
                        }

                        XWalkLaunchScreenManager.this.mLaunchScreenDialog = new Dialog(XWalkLaunchScreenManager.this.mContext, 0x10300F0);
                        XWalkLaunchScreenManager.this.mLaunchScreenDialog.setOnKeyListener(new DialogInterface$OnKeyListener() {
                            public boolean onKey(DialogInterface arg1, int arg2, KeyEvent arg3) {
                                if(arg2 == 4) {
                                    this.this$1.this$0.performHideLaunchScreen();
                                    this.this$1.this$0.mActivity.onBackPressed();
                                }

                                return 1;
                            }
                        });
                        XWalkLaunchScreenManager.this.mLaunchScreenDialog.setOnShowListener(XWalkLaunchScreenManager.this);
                        XWalkLaunchScreenManager.this.mLaunchScreenDialog.setOnDismissListener(XWalkLaunchScreenManager.this);
                        XWalkLaunchScreenManager.this.mLaunchScreenDialog.getWindow().setBackgroundDrawable(v0_2);
                        RelativeLayout v0_3 = XWalkLaunchScreenManager.this.getLaunchScreenLayout(this.val$imageBorderList);
                        if(v0_3 != null) {
                            XWalkLaunchScreenManager.this.mLaunchScreenDialog.setContentView(((View)v0_3));
                        }

                        XWalkLaunchScreenManager.this.mLaunchScreenDialog.show();
                        XWalkLaunchScreenManager.this.mOrientationListener = new OrientationEventListener(XWalkLaunchScreenManager.this.mContext, 3) {
                            public void onOrientationChanged(int arg2) {
                                if(this.this$1.this$0.mLaunchScreenDialog != null) {
                                    if(!this.this$1.this$0.mLaunchScreenDialog.isShowing()) {
                                    }
                                    else {
                                        if(this.this$1.this$0.getScreenOrientation() != this.this$1.this$0.mCurrentOrientation) {
                                            RelativeLayout v2 = this.this$1.this$0.getLaunchScreenLayout(this.this$1.val$imageBorderList);
                                            if(v2 == null) {
                                                return;
                                            }
                                            else {
                                                this.this$1.this$0.mLaunchScreenDialog.setContentView(((View)v2));
                                            }
                                        }

                                        return;
                                    }
                                }
                            }
                        };
                        XWalkLaunchScreenManager.this.mOrientationListener.enable();
                        if(XWalkLaunchScreenManager.this.mReadyWhen == ReadyWhenType.CUSTOM) {
                            XWalkLaunchScreenManager.this.registerBroadcastReceiver();
                        }
                    }
                });
                return;
            }
        }
    }

    public static String getHideLaunchScreenFilterStr() {
        return XWalkLaunchScreenManager.mIntentFilterStr;
    }

    private RelativeLayout getLaunchScreenLayout(String arg7) {
        String[] v7 = arg7.split(";");
        if(v7.length < 1) {
            return this.parseImageBorder("");
        }

        int v0 = this.getScreenOrientation();
        this.mCurrentOrientation = v0;
        int v4 = 2;
        if(v7.length >= v4 && v0 == v4) {
            if(v7[1].equals("empty")) {
                return this.parseImageBorder("");
            }
            else if(v7[1].isEmpty()) {
                return this.parseImageBorder(v7[0]);
            }
            else {
                return this.parseImageBorder(v7[1]);
            }
        }

        if(v7.length == 3 && v0 == 1) {
            if(v7[v4].equals("empty")) {
                return this.parseImageBorder("");
            }
            else if(v7[v4].isEmpty()) {
                return this.parseImageBorder(v7[0]);
            }
            else {
                return this.parseImageBorder(v7[v4]);
            }
        }

        return this.parseImageBorder(v7[0]);
    }

    public int getScreenOrientation() {
        Display v0 = this.mActivity.getWindowManager().getDefaultDisplay();
        Point v1 = new Point();
        v0.getSize(v1);
        int v0_1 = v1.x < v1.y ? 1 : 2;
        return v0_1;
    }

    private int getStatusBarHeight() {
        int v0 = this.mContext.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if(v0 > 0) {
            return this.mContext.getResources().getDimensionPixelSize(v0);
        }

        return 25;
    }

    private ImageView getSubImageView(Bitmap arg6, int arg7, int arg8, int arg9, int arg10, BorderModeType arg11, int arg12, int arg13) {
        ImageView v0 = null;
        if(arg6 == null) {
            return v0;
        }

        if(arg9 > 0) {
            if(arg10 <= 0) {
            }
            else if(!new Rect(0, 0, arg6.getWidth(), arg6.getHeight()).contains(new Rect(arg7, arg8, arg7 + arg9, arg8 + arg10))) {
                return v0;
            }
            else {
                arg6 = Bitmap.createBitmap(arg6, arg7, arg8, arg9, arg10);
                ImageView v7 = new ImageView(this.mContext);
                if(arg11 == BorderModeType.ROUND) {
                    arg8 = arg6.getWidth();
                    arg9 = arg6.getHeight();
                    if(arg12 > 0) {
                        arg8 = this.getSuitableSize(arg12, arg8);
                    }

                    if(arg13 > 0) {
                        arg9 = this.getSuitableSize(arg13, arg9);
                    }

                    arg6 = Bitmap.createScaledBitmap(arg6, arg8, arg9, true);
                    arg11 = BorderModeType.REPEAT;
                }

                if(arg11 == BorderModeType.REPEAT) {
                    BitmapDrawable v8 = new BitmapDrawable(this.mContext.getResources(), arg6);
                    v8.setTileModeXY(Shader$TileMode.REPEAT, Shader$TileMode.REPEAT);
                    v7.setImageDrawable(((Drawable)v8));
                    v7.setScaleType(ImageView$ScaleType.FIT_XY);
                }
                else if(arg11 == BorderModeType.STRETCH) {
                    v7.setImageBitmap(arg6);
                    v7.setScaleType(ImageView$ScaleType.FIT_XY);
                }
                else {
                    v7.setImageBitmap(arg6);
                }

                return v7;
            }
        }

        return v0;
    }

    private int getSuitableSize(int arg5, int arg6) {
        float v1 = ((float)arg6);
        int v0 = arg6;
        while(true) {
            if(arg6 > 1) {
                int v2 = arg5 % arg6;
                if(v2 == 0) {
                }
                else {
                    float v2_1 = ((float)v2);
                    if(v2_1 < v1) {
                        v0 = arg6;
                        v1 = v2_1;
                    }

                    --arg6;
                    continue;
                }
            }
            else {
                return v0;
            }

            return arg6;
        }

        return v0;
    }

    private void hideLaunchScreenWhenReady() {
        if(this.mLaunchScreenDialog != null) {
            if(!this.mFirstFrameReceived) {
            }
            else {
                if(this.mReadyWhen == ReadyWhenType.FIRST_PAINT) {
                    this.performHideLaunchScreen();
                }
                else if(this.mReadyWhen == ReadyWhenType.USER_INTERACTIVE) {
                    this.performHideLaunchScreen();
                }
                else if(this.mReadyWhen == ReadyWhenType.COMPLETE) {
                    if(this.mPageLoadFinished) {
                        this.performHideLaunchScreen();
                    }
                }
                else if(this.mReadyWhen == ReadyWhenType.CUSTOM && (this.mCustomHideLaunchScreen)) {
                    this.performHideLaunchScreen();
                }

                return;
            }
        }
    }

    public void onDismiss(DialogInterface arg1) {
        this.mOrientationListener.disable();
        this.mOrientationListener = null;
    }

    public void onFirstFrameReceived() {
        this.mFirstFrameReceived = true;
        this.hideLaunchScreenWhenReady();
    }

    public void onPageFinished(String arg1) {
        this.mPageLoadFinished = true;
        this.hideLaunchScreenWhenReady();
    }

    public void onShow(DialogInterface arg2) {
        this.mActivity.getWindow().setBackgroundDrawable(null);
        if(this.mFirstFrameReceived) {
            this.hideLaunchScreenWhenReady();
        }
    }

    private RelativeLayout parseImageBorder(String arg34) {
        RelativeLayout v13_1;
        RelativeLayout v8_2;
        RelativeLayout v6_2;
        RelativeLayout v5_1;
        RelativeLayout v4_2;
        RelativeLayout$LayoutParams v1_3;
        ImageView v0_3;
        Object v1_1;
        Object v15;
        Object v12_1;
        int v11;
        int v10;
        int v8;
        int v7_1;
        XWalkLaunchScreenManager v9 = this;
        BorderModeType v0 = BorderModeType.STRETCH;
        BorderModeType v1 = BorderModeType.STRETCH;
        String v3 = arg34;
        String v2 = v3.equals("empty") ? "" : v3;
        String[] v2_1 = v2.split(" ");
        ArrayList v3_1 = new ArrayList();
        ArrayList v4 = new ArrayList();
        int v6;
        for(v6 = 0; v6 < v2_1.length; ++v6) {
            String v7 = v2_1[v6];
            if(v7.endsWith("px")) {
                v3_1.add(v7.replaceAll("px", ""));
            }
            else if(v7.equals("repeat")) {
                v4.add(BorderModeType.REPEAT);
            }
            else if(v7.equals("stretch")) {
                v4.add(BorderModeType.STRETCH);
            }
            else if(v7.equals("round")) {
                v4.add(BorderModeType.ROUND);
            }
        }

        int v2_2 = 2;
        try {
            if(v3_1.size() == 1) {
                v7_1 = Integer.valueOf(v3_1.get(0)).intValue();
                v8 = v7_1;
                v10 = v8;
                v11 = v10;
            }
            else if(v3_1.size() == v2_2) {
                v7_1 = Integer.valueOf(v3_1.get(0)).intValue();
                v8 = Integer.valueOf(v3_1.get(1)).intValue();
                v10 = v7_1;
                v11 = v8;
            }
            else {
                v8 = 3;
                if(v3_1.size() == v8) {
                    v7_1 = Integer.valueOf(v3_1.get(1)).intValue();
                    v8 = Integer.valueOf(v3_1.get(0)).intValue();
                    v10 = Integer.valueOf(v3_1.get(v2_2)).intValue();
                    v11 = v7_1;
                    v7_1 = v8;
                    v8 = v11;
                }
                else if(v3_1.size() == 4) {
                    v7_1 = Integer.valueOf(v3_1.get(0)).intValue();
                    v10 = Integer.valueOf(v3_1.get(1)).intValue();
                    v11 = Integer.valueOf(v3_1.get(v2_2)).intValue();
                    int v32 = v10;
                    v10 = Integer.valueOf(v3_1.get(v8)).intValue();
                    v8 = v32;
                }
                else {
                    goto label_105;
                }
            }
        }
        catch(NumberFormatException ) {
        label_105:
            v7_1 = 0;
            v8 = 0;
            v10 = 0;
            v11 = 0;
        }

        DisplayMetrics v12 = v9.mContext.getResources().getDisplayMetrics();
        int v13 = ((int)TypedValue.applyDimension(1, ((float)v7_1), v12));
        int v14 = ((int)TypedValue.applyDimension(1, ((float)v8), v12));
        v11 = ((int)TypedValue.applyDimension(1, ((float)v11), v12));
        v10 = ((int)TypedValue.applyDimension(1, ((float)v10), v12));
        if(v4.size() == 1) {
            v12_1 = v4.get(0);
            v15 = v12_1;
        }
        else {
            if(v4.size() == v2_2) {
                Object v0_1 = v4.get(0);
                v1_1 = v4.get(1);
            }

            BorderModeType v12_2 = v0;
            BorderModeType v15_1 = ((BorderModeType)v1_1);
        }

        int v0_2 = v9.mContext.getResources().getIdentifier("launchscreen_img", "drawable", v9.mContext.getPackageName());
        RelativeLayout v1_2 = null;
        if(v0_2 == 0) {
            return v1_2;
        }

        Bitmap v8_1 = BitmapFactory.decodeResource(v9.mContext.getResources(), v0_2);
        if(v8_1 == null) {
            return v1_2;
        }

        RelativeLayout v7_2 = new RelativeLayout(v9.mContext);
        v6 = -1;
        v7_2.setLayoutParams(new RelativeLayout$LayoutParams(v6, v6));
        int v5 = 13;
        int v4_1 = -2;
        if(v3_1.size() == 0) {
            v0_3 = new ImageView(v9.mContext);
            v0_3.setImageBitmap(v8_1);
            v1_3 = new RelativeLayout$LayoutParams(v4_1, v4_1);
            v1_3.addRule(v5, v6);
            v7_2.addView(((View)v0_3), ((ViewGroup$LayoutParams)v1_3));
            return v7_2;
        }

        Display v0_4 = v9.mActivity.getWindowManager().getDefaultDisplay();
        Point v3_2 = new Point();
        v0_4.getSize(v3_2);
        if((v9.mActivity.getWindow().getAttributes().flags & 0x400) == 0) {
            v3_2.y -= this.getStatusBarHeight();
        }

        Point v20 = v3_2;
        RelativeLayout v21 = v7_2;
        Object v22 = v15;
        Bitmap v15_2 = v8_1;
        v0_3 = v9.getSubImageView(v8_1, 0, 0, v11, v13, BorderModeType.NONE, 0, 0);
        v8 = 10;
        v7_1 = 9;
        if(v0_3 != null) {
            v1_3 = new RelativeLayout$LayoutParams(-2, -2);
            v1_3.addRule(v7_1, -1);
            v1_3.addRule(v8, -1);
            v4_2 = v21;
            v4_2.addView(((View)v0_3), ((ViewGroup$LayoutParams)v1_3));
        }
        else {
            v4_2 = v21;
        }

        Point v23 = v20;
        RelativeLayout v24 = v4_2;
        Object v6_1 = v12_1;
        Object v25 = v12_1;
        int v12_3 = 10;
        v0_3 = v9.getSubImageView(v15_2, v11, 0, v15_2.getWidth() - v11 - v14, v13, ((BorderModeType)v6_1), v20.x - v11 - v14, 0);
        v8 = 14;
        if(v0_3 != null) {
            v1_3 = new RelativeLayout$LayoutParams(-1, -2);
            v1_3.addRule(v12_3, -1);
            v1_3.addRule(v8, -1);
            v1_3.leftMargin = v11;
            v1_3.rightMargin = v14;
            v5_1 = v24;
            v5_1.addView(((View)v0_3), ((ViewGroup$LayoutParams)v1_3));
        }
        else {
            v5_1 = v24;
        }

        RelativeLayout v26 = v5_1;
        v12_3 = -2;
        v0_3 = v9.getSubImageView(v15_2, v15_2.getWidth() - v14, 0, v14, v13, BorderModeType.NONE, 0, 0);
        v8 = 11;
        if(v0_3 != null) {
            v1_3 = new RelativeLayout$LayoutParams(v12_3, v12_3);
            v1_3.addRule(v8, -1);
            v1_3.addRule(10, -1);
            v6_2 = v26;
            v6_2.addView(((View)v0_3), ((ViewGroup$LayoutParams)v1_3));
        }
        else {
            v6_2 = v26;
        }

        Point v27 = v23;
        RelativeLayout v28 = v6_2;
        v12_3 = -1;
        v0_3 = v9.getSubImageView(v15_2, 0, v13, v11, v15_2.getHeight() - v13 - v10, v22, 0, v23.y - v13 - v10);
        if(v0_3 != null) {
            v1_3 = new RelativeLayout$LayoutParams(-2, v12_3);
            v1_3.addRule(9, v12_3);
            v1_3.addRule(13, v12_3);
            v1_3.topMargin = v13;
            v1_3.bottomMargin = v10;
            v6_2 = v28;
            v6_2.addView(((View)v0_3), ((ViewGroup$LayoutParams)v1_3));
        }
        else {
            v6_2 = v28;
        }

        RelativeLayout v29 = v6_2;
        v0_3 = v9.getSubImageView(v15_2, v11, v13, v15_2.getWidth() - v11 - v14, v15_2.getHeight() - v13 - v10, BorderModeType.NONE, 0, 0);
        if(v0_3 != null) {
            v0_3.setScaleType(ImageView$ScaleType.FIT_XY);
            v1_3 = new RelativeLayout$LayoutParams(v12_3, v12_3);
            v1_3.leftMargin = v11;
            v1_3.topMargin = v13;
            v1_3.rightMargin = v14;
            v1_3.bottomMargin = v10;
            v8_2 = v29;
            v8_2.addView(((View)v0_3), ((ViewGroup$LayoutParams)v1_3));
        }
        else {
            v8_2 = v29;
        }

        Point v30 = v27;
        RelativeLayout v31 = v8_2;
        v0_3 = v9.getSubImageView(v15_2, v15_2.getWidth() - v14, v13, v14, v15_2.getHeight() - v13 - v10, v22, 0, v27.y - v13 - v10);
        if(v0_3 != null) {
            v1_3 = new RelativeLayout$LayoutParams(-2, v12_3);
            v1_3.addRule(13, v12_3);
            v1_3.addRule(11, v12_3);
            v1_3.topMargin = v13;
            v1_3.bottomMargin = v10;
            v13_1 = v31;
            v13_1.addView(((View)v0_3), ((ViewGroup$LayoutParams)v1_3));
        }
        else {
            v13_1 = v31;
        }

        v0_3 = v9.getSubImageView(v15_2, 0, v15_2.getHeight() - v10, v11, v10, BorderModeType.NONE, 0, 0);
        v8 = 12;
        if(v0_3 != null) {
            v1_3 = new RelativeLayout$LayoutParams(-2, -2);
            v1_3.addRule(9, v12_3);
            v1_3.addRule(v8, v12_3);
            v13_1.addView(((View)v0_3), ((ViewGroup$LayoutParams)v1_3));
        }

        v0_3 = v9.getSubImageView(v15_2, v11, v15_2.getHeight() - v10, v15_2.getWidth() - v11 - v14, v10, v25, v30.x - v11 - v14, 0);
        if(v0_3 != null) {
            v1_3 = new RelativeLayout$LayoutParams(v12_3, -2);
            v1_3.addRule(14, v12_3);
            v1_3.addRule(12, v12_3);
            v1_3.leftMargin = v11;
            v1_3.rightMargin = v14;
            v13_1.addView(((View)v0_3), ((ViewGroup$LayoutParams)v1_3));
        }

        v2_2 = v15_2.getWidth() - v14;
        int v3_3 = v15_2.getHeight() - v10;
        v5 = v10;
        v10 = 12;
        v0_3 = v9.getSubImageView(v15_2, v2_2, v3_3, v14, v5, BorderModeType.NONE, 0, 0);
        if(v0_3 != null) {
            v1_3 = new RelativeLayout$LayoutParams(-2, -2);
            v1_3.addRule(11, v12_3);
            v1_3.addRule(v10, v12_3);
            v13_1.addView(((View)v0_3), ((ViewGroup$LayoutParams)v1_3));
        }

        return v13_1;
    }

    private void performHideLaunchScreen() {
        if(this.mLaunchScreenDialog != null) {
            this.mLaunchScreenDialog.dismiss();
            this.mLaunchScreenDialog = null;
        }

        if(this.mReadyWhen == ReadyWhenType.CUSTOM) {
            this.mContext.unregisterReceiver(this.mLaunchScreenReadyWhenReceiver);
        }
    }

    private void registerBroadcastReceiver() {
        IntentFilter v0 = new IntentFilter(XWalkLaunchScreenManager.mIntentFilterStr);
        this.mLaunchScreenReadyWhenReceiver = new BroadcastReceiver() {
            public void onReceive(Context arg1, Intent arg2) {
                XWalkLaunchScreenManager.this.mCustomHideLaunchScreen = true;
                XWalkLaunchScreenManager.this.hideLaunchScreenWhenReady();
            }
        };
        this.mContext.registerReceiver(this.mLaunchScreenReadyWhenReceiver, v0);
    }

    private void setReadyWhen(String arg2) {
        if(arg2.equals("first-paint")) {
            this.mReadyWhen = ReadyWhenType.FIRST_PAINT;
        }
        else if(arg2.equals("user-interactive")) {
            this.mReadyWhen = ReadyWhenType.USER_INTERACTIVE;
        }
        else if(arg2.equals("complete")) {
            this.mReadyWhen = ReadyWhenType.COMPLETE;
        }
        else if(arg2.equals("custom")) {
            this.mReadyWhen = ReadyWhenType.CUSTOM;
        }
        else {
            this.mReadyWhen = ReadyWhenType.FIRST_PAINT;
        }
    }
}

