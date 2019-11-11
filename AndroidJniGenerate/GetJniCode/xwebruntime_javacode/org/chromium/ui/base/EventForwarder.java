package org.chromium.ui.base;

import android.annotation.TargetApi;
import android.content.ClipData;
import android.content.ClipDescription;
import android.os.Build$VERSION;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;
import org.chromium.base.TraceEvent;
import org.chromium.base.VisibleForTesting;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.content.browser.ExtendCanvasCallback;
import org.chromium.content.browser.ExtendPluginCallback;
import org.chromium.content.browser.ExtendPluginManager;

@JNINamespace(value="ui") public class EventForwarder {
    public class CustomMotionEvent {
        public int mEmbedId;
        public String mEmbedType;
        ExtendCanvasCallback mExtendCanvasCallback;
        ExtendPluginCallback mExtendPluginCallback;
        public int mHash;
        public int mHeight;
        public MotionEvent mMotionEvent;
        View mView;
        public int mWidth;
        public int mX;
        public int mY;

        public CustomMotionEvent(EventForwarder arg1, MotionEvent arg2, int arg3) {
            EventForwarder.this = arg1;
            super();
            this.mMotionEvent = arg2;
            this.mHash = arg3;
        }

        public void update(int arg1, int arg2, int arg3, int arg4, ExtendPluginCallback arg5, ExtendCanvasCallback arg6, View arg7) {
            this.mX = arg1;
            this.mY = arg2;
            this.mWidth = arg3;
            this.mHeight = arg4;
            this.mExtendPluginCallback = arg5;
            this.mExtendCanvasCallback = arg6;
            this.mView = arg7;
        }

        public void update(String arg10, int arg11, int arg12, int arg13, int arg14, int arg15, ExtendPluginCallback arg16, ExtendCanvasCallback arg17, View arg18) {
            this.mEmbedType = arg10;
            this.mEmbedId = arg11;
            this.update(arg12, arg13, arg14, arg15, arg16, arg17, arg18);
        }
    }

    private static final int MAX_CACHE_EVENT = 40;
    private final String TAG;
    private float mCurrentTouchOffsetX;
    private float mCurrentTouchOffsetY;
    private final boolean mIsDragDropEnabled;
    private CustomMotionEvent mLastMotionEvent;
    private int mLastMouseButtonState;
    private ArrayList mMotionEventCacheList;
    private long mNativeEventForwarder;

    static {
    }

    private EventForwarder(long arg3, boolean arg5) {
        super();
        this.TAG = "EventForwarder";
        this.mMotionEventCacheList = new ArrayList(40);
        this.mNativeEventForwarder = arg3;
        this.mIsDragDropEnabled = arg5;
    }

    public void cancelFling(long arg6) {
        if(this.mNativeEventForwarder == 0) {
            return;
        }

        this.nativeCancelFling(this.mNativeEventForwarder, arg6);
    }

    public boolean checkMotionEvent(MotionEvent arg7, boolean arg8) {
        if(this.mLastMotionEvent != null && this.mLastMotionEvent.mMotionEvent.getDownTime() == arg7.getDownTime()) {
            if(arg8) {
                arg7.offsetLocation(((float)(this.mLastMotionEvent.mX * -1)), ((float)(this.mLastMotionEvent.mY * -1)));
                ExtendPluginManager.onPluginTouch(this.mLastMotionEvent.mEmbedType, this.mLastMotionEvent.mEmbedId, arg7, this.mLastMotionEvent.mExtendPluginCallback, this.mLastMotionEvent.mExtendCanvasCallback, this.mLastMotionEvent.mView);
            }

            return 1;
        }

        return 0;
    }

    @CalledByNative private static EventForwarder create(long arg1, boolean arg3) {
        return new EventForwarder(arg1, arg3);
    }

    private MotionEvent createOffsetMotionEvent(MotionEvent arg3) {
        arg3 = MotionEvent.obtain(arg3);
        arg3.offsetLocation(this.mCurrentTouchOffsetX, this.mCurrentTouchOffsetY);
        return arg3;
    }

    @CalledByNative private void destroy() {
        this.mNativeEventForwarder = 0;
        this.mMotionEventCacheList.clear();
    }

    @VisibleForTesting public void doubleTapForTest(long arg9, int arg11, int arg12) {
        if(this.mNativeEventForwarder == 0) {
            return;
        }

        this.nativeDoubleTap(this.mNativeEventForwarder, arg9, arg11, arg12);
    }

    public float getEventSourceScaling() {
        return this.nativeGetJavaWindowAndroid(this.mNativeEventForwarder).getDisplay().getAndroidUIScaling();
    }

    @TargetApi(value=23) private int getMouseEventActionButton(MotionEvent arg3) {
        if(Build$VERSION.SDK_INT >= 23) {
            return arg3.getActionButton();
        }

        return 0;
    }

    private static boolean isValidTouchEventActionForNative(int arg2) {
        boolean v0 = true;
        if(arg2 != 0 && arg2 != 1 && arg2 != 3 && arg2 != 2 && arg2 != 5) {
            if(arg2 == 6) {
            }
            else {
                v0 = false;
            }
        }

        return v0;
    }

    private native void nativeCancelFling(long arg1, long arg2) {
    }

    private native void nativeDoubleTap(long arg1, long arg2, int arg3, int arg4) {
    }

    private native WindowAndroid nativeGetJavaWindowAndroid(long arg1) {
    }

    private native void nativeOnDragEvent(long arg1, int arg2, int arg3, int arg4, int arg5, int arg6, String[] arg7, String arg8) {
    }

    private native boolean nativeOnGestureEvent(long arg1, int arg2, long arg3, float arg4) {
    }

    private native void nativeOnMouseEvent(long arg1, long arg2, int arg3, float arg4, float arg5, int arg6, float arg7, float arg8, float arg9, int arg10, int arg11, int arg12, int arg13) {
    }

    private native void nativeOnMouseWheelEvent(long arg1, long arg2, float arg3, float arg4, float arg5, float arg6) {
    }

    private native boolean nativeOnTouchEvent(long arg1, MotionEvent arg2, int arg3, long arg4, int arg5, int arg6, int arg7, int arg8, float arg9, float arg10, float arg11, float arg12, int arg13, int arg14, float arg15, float arg16, float arg17, float arg18, float arg19, float arg20, float arg21, float arg22, float arg23, float arg24, int arg25, int arg26, int arg27, int arg28, boolean arg29) {
    }

    private native void nativeScroll(long arg1, long arg2, float arg3, float arg4) {
    }

    private native void nativeStartFling(long arg1, long arg2, float arg3, float arg4, boolean arg5) {
    }

    @TargetApi(value=24) public boolean onDragEvent(DragEvent arg14, View arg15) {
        int v3;
        if(Long.compare(this.mNativeEventForwarder, 0) != 0) {
            if(Build$VERSION.SDK_INT <= 23) {
            }
            else {
                ClipDescription v1 = arg14.getClipDescription();
                String[] v1_1 = v1 == null ? new String[0] : v1.filterMimeTypes("text/*");
                String[] v10 = v1_1;
                boolean v12 = true;
                if(arg14.getAction() == 1) {
                    if(v10 == null || v10.length <= 0 || !this.mIsDragDropEnabled) {
                        v12 = false;
                    }
                    else {
                    }

                    return v12;
                }

                StringBuilder v1_2 = new StringBuilder("");
                if(arg14.getAction() == 3) {
                    ClipData v2 = arg14.getClipData();
                    v3 = v2.getItemCount();
                    int v4;
                    for(v4 = 0; v4 < v3; ++v4) {
                        v1_2.append(v2.getItemAt(v4).coerceToStyledText(arg15.getContext()));
                    }
                }

                int[] v2_1 = new int[2];
                arg15.getLocationOnScreen(v2_1);
                int v15 = ((int)(arg14.getX() + this.mCurrentTouchOffsetX));
                v3 = ((int)(arg14.getY() + this.mCurrentTouchOffsetY));
                int v0 = v2_1[0] + v15;
                int v2_2 = v2_1[1] + v3;
                float v4_1 = this.getEventSourceScaling();
                this.nativeOnDragEvent(this.mNativeEventForwarder, arg14.getAction(), ((int)((((float)v15)) / v4_1)), ((int)((((float)v3)) / v4_1)), ((int)((((float)v0)) / v4_1)), ((int)((((float)v2_2)) / v4_1)), v10, v1_2.toString());
                return 1;
            }
        }

        return 0;
    }

    public boolean onGestureEvent(int arg8, long arg9, float arg11) {
        return this.nativeOnGestureEvent(this.mNativeEventForwarder, arg8, arg9, arg11);
    }

    public boolean onHoverEvent(MotionEvent arg2) {
        boolean v2_1;
        TraceEvent.begin("onHoverEvent");
        try {
            v2_1 = this.sendNativeMouseEvent(arg2);
        }
        catch(Throwable v2) {
            TraceEvent.end("onHoverEvent");
            throw v2;
        }

        TraceEvent.end("onHoverEvent");
        return v2_1;
    }

    public boolean onMouseEvent(MotionEvent arg2) {
        boolean v2_1;
        TraceEvent.begin("sendMouseEvent");
        try {
            v2_1 = this.sendNativeMouseEvent(arg2);
        }
        catch(Throwable v2) {
            TraceEvent.end("sendMouseEvent");
            throw v2;
        }

        TraceEvent.end("sendMouseEvent");
        return v2_1;
    }

    public boolean onMouseWheelEvent(long arg12, float arg14, float arg15, float arg16, float arg17) {
        float v0 = this.getEventSourceScaling();
        this.nativeOnMouseWheelEvent(this.mNativeEventForwarder, arg12, arg14 / v0, arg15 / v0, arg16, arg17);
        return 1;
    }

    public boolean onTouchEvent(MotionEvent arg6) {
        if(arg6.getToolType(0) == 3) {
            int v1 = Build$VERSION.SDK_INT;
            int v3 = 1;
            if(arg6.getButtonState() != 0) {
            label_16:
                v3 = 0;
            }
            else if(arg6.getActionMasked() != 0 && arg6.getActionMasked() != 2) {
                if(arg6.getActionMasked() == 1) {
                }
                else {
                    goto label_16;
                }
            }

            if(v1 < 23) {
                goto label_22;
            }

            if(v3 != 0) {
                goto label_22;
            }

            return this.onMouseEvent(arg6);
        }

    label_22:
        return this.sendTouchEvent(arg6, false);
    }

    public boolean onTouchHandleEvent(MotionEvent arg2) {
        return this.sendTouchEvent(arg2, true);
    }

    private void refreshLastMotionEvent(String arg11, int arg12, int arg13, int arg14, int arg15, int arg16, ExtendPluginCallback arg17, ExtendCanvasCallback arg18, View arg19) {
        EventForwarder v0 = this;
        String v1 = arg11;
        if(v0.mLastMotionEvent != null) {
            if(v1 != null || v0.mLastMotionEvent.mEmbedType != null) {
                if(v1 == null) {
                }
                else if(v1.equals(v0.mLastMotionEvent.mEmbedType)) {
                    goto label_13;
                }

                return;
            }

        label_13:
            if(arg12 != v0.mLastMotionEvent.mEmbedId) {
                return;
            }

            v0.mLastMotionEvent.update(arg13, arg14, arg15, arg16, arg17, arg18, arg19);
        }
    }

    public void scroll(long arg9, float arg11, float arg12) {
        if(this.mNativeEventForwarder == 0) {
            return;
        }

        this.nativeScroll(this.mNativeEventForwarder, arg9, arg11, arg12);
    }

    private boolean sendNativeMouseEvent(MotionEvent arg29) {
        int v13_1;
        MotionEvent v27;
        MotionEvent v13;
        long v3;
        float v2_2;
        int v1_3;
        Throwable v1_2;
        int v15_1;
        int v14;
        float v12_1;
        MotionEvent v26;
        float v11_1;
        int v25;
        float v10_1;
        int v9;
        EventForwarder v1_1;
        int v22;
        float v18;
        int v16;
        float v8;
        float v7;
        int v6;
        long v4;
        long v2_1;
        MotionEvent v2;
        int v11;
        int v17;
        MotionEvent v12;
        EventForwarder v15 = this;
        if(Float.compare(v15.mCurrentTouchOffsetX, 0f) != 0 || v15.mCurrentTouchOffsetY != 0f) {
            v12 = this.createOffsetMotionEvent(arg29);
            v17 = 1;
        }
        else {
            v12 = arg29;
            v17 = 0;
        }

        try {
            v11 = v12.getActionMasked();
            if(v11 == 11 || v11 == 12) {
                v15.mLastMouseButtonState = v12.getButtonState();
            }
        }
        catch(Throwable v0) {
            v2 = v12;
            goto label_142;
        }

        int v10 = 25;
        if(v11 == 9) {
            try {
                if(v15.mLastMouseButtonState == 1) {
                    float v1 = this.getEventSourceScaling();
                    v2_1 = v15.mNativeEventForwarder;
                    v4 = v12.getEventTime();
                    v6 = 12;
                    v7 = v12.getX() / v1;
                    v8 = v12.getY() / v1;
                    v16 = v12.getPointerId(0);
                    v18 = v12.getPressure(0);
                    float v19 = v12.getOrientation(0);
                    float v20 = v12.getAxisValue(v10, 0);
                    v22 = v12.getButtonState();
                    int v23 = v12.getMetaState();
                    int v24 = v12.getToolType(0);
                    v1_1 = v15;
                    v9 = v16;
                    v10_1 = v18;
                    v25 = v11;
                    v11_1 = v19;
                    v26 = v12;
                    v12_1 = v20;
                    v14 = v22;
                    v15_1 = v23;
                    v16 = v24;
                }
                else {
                    goto label_59;
                }
            }
            catch(Throwable v0) {
                v1_2 = v0;
                v2 = ((MotionEvent)v12_1);
                goto label_143;
            }

            try {
                v1_1.nativeOnMouseEvent(v2_1, v4, v6, v7, v8, v9, v10_1, v11_1, v12_1, 1, v14, v15_1, v16);
                v1_3 = 0;
                v15 = this;
                goto label_62;
            label_59:
                v25 = v11;
                v26 = v12;
                v1_3 = 0;
            label_62:
                v15.mLastMouseButtonState = v1_3;
                v6 = v25;
            }
            catch(Throwable v0) {
                v1_2 = v0;
                v2 = v26;
                goto label_143;
            }
        }
        else {
            v26 = v12;
            v1_3 = 0;
            v6 = v11;
        }

        if(v6 != 9) {
            if(v6 == 10) {
            }
            else {
                if(v6 != 0) {
                    if(v6 == 1) {
                    }
                    else {
                        try {
                            v2_2 = this.getEventSourceScaling();
                            v3 = v15.mNativeEventForwarder;
                            v13 = v26;
                        }
                        catch(Throwable v0) {
                            v2 = v26;
                            goto label_142;
                        }

                        try {
                            long v7_1 = v13.getEventTime();
                            float v9_1 = v13.getX() / v2_2;
                            v10_1 = v13.getY() / v2_2;
                            v11 = v13.getPointerId(v1_3);
                            v12_1 = v13.getPressure(v1_3);
                            float v16_1 = v13.getOrientation(v1_3);
                            v18 = v13.getAxisValue(25, v1_3);
                            int v19_1 = v15.getMouseEventActionButton(v13);
                            int v20_1 = v13.getButtonState();
                            int v21 = v13.getMetaState();
                            v22 = v13.getToolType(v1_3);
                            v1_1 = v15;
                            v2_1 = v3;
                            v4 = v7_1;
                            v7 = v9_1;
                            v8 = v10_1;
                            v9 = v11;
                            v10_1 = v12_1;
                            v11_1 = v16_1;
                            v12_1 = v18;
                            v27 = v13;
                            v13_1 = v19_1;
                            v14 = v20_1;
                            v15_1 = v21;
                            v16 = v22;
                        }
                        catch(Throwable v0) {
                            v2 = ((MotionEvent)v13_1);
                            goto label_142;
                        }

                        try {
                            v1_1.nativeOnMouseEvent(v2_1, v4, v6, v7, v8, v9, v10_1, v11_1, v12_1, v13_1, v14, v15_1, v16);
                            if(v17 == 0) {
                                return 1;
                            }

                            goto label_119;
                        }
                        catch(Throwable v0) {
                            v2 = v27;
                        }

                    label_142:
                        v1_2 = v0;
                    label_143:
                        if(v17 != 0) {
                            v2.recycle();
                        }

                        throw v1_2;
                    label_119:
                        v27.recycle();
                        return 1;
                    }
                }

                v2 = v26;
                if(v17 != 0) {
                    v2.recycle();
                }

                return 1;
            }
        }

        v2 = v26;
        if(v17 != 0) {
            v2.recycle();
        }

        return ((boolean)v1_3);
    }

    private boolean sendTouchEvent(MotionEvent arg42, boolean arg43) {
        boolean v1_3;
        float v12;
        int v33;
        MotionEvent v14;
        int v8;
        long v6;
        long v3;
        MotionEvent v1;
        EventForwarder v15 = this;
        TraceEvent.begin("sendTouchEvent");
        try {
            if(arg42.getHistorySize() > 0) {
                v1 = arg42;
                v3 = v1.getHistoricalEventTime(0);
            }
            else {
                v1 = arg42;
                v3 = arg42.getEventTime();
            }

            v6 = v3;
            v8 = SPenSupport.convertSPenEventAction(arg42.getActionMasked());
            if(EventForwarder.isValidTouchEventActionForNative(v8)) {
                goto label_19;
            }
        }
        catch(Throwable v0) {
            goto label_178;
        }

        TraceEvent.end("sendTouchEvent");
        return 0;
        try {
        label_19:
            if(Float.compare(v15.mCurrentTouchOffsetX, 0f) != 0 || v15.mCurrentTouchOffsetY != 0f) {
                v14 = this.createOffsetMotionEvent(arg42);
                v33 = 1;
            }
            else {
                v14 = v1;
                v33 = 0;
            }

            int v9 = v14.getPointerCount();
            int v1_1 = 2;
            float[] v3_1 = new float[v1_1];
            v3_1[0] = v14.getTouchMajor();
            float v10 = v9 > 1 ? v14.getTouchMajor(1) : 0f;
            v3_1[1] = v10;
            float[] v10_1 = new float[v1_1];
            v10_1[0] = v14.getTouchMinor();
            float v11 = v9 > 1 ? v14.getTouchMinor(1) : 0f;
            v10_1[1] = v11;
            int v11_1;
            for(v11_1 = 0; v11_1 < v1_1; ++v11_1) {
                if(v3_1[v11_1] < v10_1[v11_1]) {
                    v12 = v3_1[v11_1];
                    v3_1[v11_1] = v10_1[v11_1];
                    v10_1[v11_1] = v12;
                }
            }

            float v1_2 = v9 > 1 ? v14.getX(1) : 0f;
            v11 = v9 > 1 ? v14.getY(1) : 0f;
            v12 = this.getEventSourceScaling();
            MotionEvent v13 = MotionEvent.obtain(v14);
            if(v15.mMotionEventCacheList.size() >= 40) {
                v15.mMotionEventCacheList.remove(0);
            }

            int v5 = v13.hashCode();
            if(!v15.checkMotionEvent(v13, true)) {
                v15.mMotionEventCacheList.add(new CustomMotionEvent(v15, v13, v5));
            }

            long v37 = v6;
            v6 = v15.mNativeEventForwarder;
            int v13_1 = v14.getHistorySize();
            int v16 = v14.getActionIndex();
            float v17 = v14.getX() / v12;
            float v18 = v14.getY() / v12;
            float v19 = v1_2 / v12;
            float v20 = v11 / v12;
            int v21 = v14.getPointerId(0);
            int v22 = v9 > 1 ? v14.getPointerId(1) : -1;
            float v23 = v3_1[0] / v12;
            float v24 = v3_1[1] / v12;
            float v25 = v10_1[0] / v12;
            float v26 = v10_1[1] / v12;
            float v27 = v14.getOrientation();
            float v28 = v9 > 1 ? v14.getOrientation(1) : 0f;
            v1_1 = 25;
            float v29 = v14.getAxisValue(v1_1);
            float v34 = v9 > 1 ? v14.getAxisValue(v1_1, 1) : 0f;
            float v30 = v14.getRawX() / v12;
            float v31 = v14.getRawY() / v12;
            int v32 = v14.getToolType(0);
            int v35 = v9 > 1 ? v14.getToolType(1) : 0;
            MotionEvent v40 = v14;
            v1_3 = v15.nativeOnTouchEvent(v6, v14, v5, v37, v8, v9, v13_1, v16, v17, v18, v19, v20, v21, v22, v23, v24, v25, v26, v27, v28, v29, v34, v30, v31, v32, v35, v14.getButtonState(), v14.getMetaState(), arg43);
            if(v33 != 0) {
                v40.recycle();
            }
        }
        catch(Throwable v0) {
            goto label_178;
        }

        TraceEvent.end("sendTouchEvent");
        return v1_3;
    label_178:
        TraceEvent.end("sendTouchEvent");
        throw v0;
    }

    public void setCurrentTouchEventOffsets(float arg1, float arg2) {
        this.mCurrentTouchOffsetX = arg1;
        this.mCurrentTouchOffsetY = arg2;
    }

    public void setLastMotionEventExtendPlugin(String arg28, int arg29, int arg30, int arg31, int arg32, int arg33, int arg34, ExtendPluginCallback arg35, ExtendCanvasCallback arg36, View arg37) {
        EventForwarder v10 = this;
        int v11 = -1;
        boolean v12 = false;
        int v13 = 0;
        int v14 = -1;
        int v15 = -1;
        while(v13 < v10.mMotionEventCacheList.size()) {
            Object v0 = v10.mMotionEventCacheList.get(v13);
            if(((CustomMotionEvent)v0).mHash == arg30) {
                if(((CustomMotionEvent)v0).mMotionEvent.getActionMasked() == 0) {
                    v10.mLastMotionEvent = ((CustomMotionEvent)v0);
                    v10.mLastMotionEvent.update(arg28, arg29, arg31, arg32, arg33, arg34, arg35, arg36, arg37);
                    v10.checkMotionEvent(v10.mLastMotionEvent.mMotionEvent, true);
                    v15 = v13;
                    v12 = true;
                    v14 = 0;
                }
                else if(v10.checkMotionEvent(((CustomMotionEvent)v0).mMotionEvent, v12)) {
                    v12 = true;
                    v10.refreshLastMotionEvent(arg28, arg29, arg31, arg32, arg33, arg34, arg35, arg36, arg37);
                }
                else {
                    v12 = true;
                }

                if(v15 == v11) {
                    goto label_68;
                }

                int v0_1;
                for(v0_1 = v15 + 1; v0_1 < v10.mMotionEventCacheList.size(); ++v0_1) {
                    if(v10.checkMotionEvent(v10.mMotionEventCacheList.get(v0_1).mMotionEvent, v12)) {
                        v14 = v0_1;
                    }
                }
            }

        label_68:
            ++v13;
            v12 = false;
        }

        if(v14 != v11) {
            for(v0_1 = 0; v0_1 <= v14; ++v0_1) {
                v10.mMotionEventCacheList.remove(0);
            }
        }
    }

    public void startFling(long arg10, float arg12, float arg13, boolean arg14) {
        if(this.mNativeEventForwarder == 0) {
            return;
        }

        this.nativeStartFling(this.mNativeEventForwarder, arg10, arg12, arg13, arg14);
    }
}

