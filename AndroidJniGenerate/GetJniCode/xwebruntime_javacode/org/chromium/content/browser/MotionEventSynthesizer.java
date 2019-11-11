package org.chromium.content.browser;

import android.view.MotionEvent$PointerCoords;
import android.view.MotionEvent$PointerProperties;
import android.view.MotionEvent;
import android.view.View;

public class MotionEventSynthesizer {
    private static final int MAX_NUM_POINTERS = 16;
    private long mDownTimeInMs;
    private final MotionEvent$PointerCoords[] mPointerCoords;
    private final MotionEvent$PointerProperties[] mPointerProperties;
    private final View mTarget;

    static {
    }

    public MotionEventSynthesizer(View arg2) {
        super();
        this.mTarget = arg2;
        this.mPointerProperties = new MotionEvent$PointerProperties[16];
        this.mPointerCoords = new MotionEvent$PointerCoords[16];
    }

    public void inject(int arg24, int arg25, long arg26) {
        MotionEvent v1;
        MotionEventSynthesizer v0 = this;
        int v6 = arg25;
        switch(arg24) {
            case 0: {
                long v3 = arg26;
                v0.mDownTimeInMs = v3;
                MotionEvent v2 = MotionEvent.obtain(v0.mDownTimeInMs, v3, 0, 1, v0.mPointerProperties, v0.mPointerCoords, 0, 0, 1f, 1f, 0, 0, 0, 0);
                v0.mTarget.dispatchTouchEvent(v2);
                v2.recycle();
                if(v6 <= 1) {
                    return;
                }

                v1 = MotionEvent.obtain(v0.mDownTimeInMs, v3, 0x105, v6, v0.mPointerProperties, v0.mPointerCoords, 0, 0, 1f, 1f, 0, 0, 0, 0);
                v0.mTarget.dispatchTouchEvent(v1);
                v1.recycle();
                break;
            }
            case 1: {
                v1 = MotionEvent.obtain(v0.mDownTimeInMs, arg26, 2, v6, v0.mPointerProperties, v0.mPointerCoords, 0, 0, 1f, 1f, 0, 0, 0, 0);
                v0.mTarget.dispatchTouchEvent(v1);
                v1.recycle();
                break;
            }
            case 2: {
                v1 = MotionEvent.obtain(v0.mDownTimeInMs, arg26, 3, 1, v0.mPointerProperties, v0.mPointerCoords, 0, 0, 1f, 1f, 0, 0, 0, 0);
                v0.mTarget.dispatchTouchEvent(v1);
                v1.recycle();
                break;
            }
            case 3: {
                if(v6 > 1) {
                    v1 = MotionEvent.obtain(v0.mDownTimeInMs, arg26, 0x106, v6, v0.mPointerProperties, v0.mPointerCoords, 0, 0, 1f, 1f, 0, 0, 0, 0);
                    v0.mTarget.dispatchTouchEvent(v1);
                    v1.recycle();
                }

                v1 = MotionEvent.obtain(v0.mDownTimeInMs, arg26, 1, 1, v0.mPointerProperties, v0.mPointerCoords, 0, 0, 1f, 1f, 0, 0, 0, 0);
                v0.mTarget.dispatchTouchEvent(v1);
                v1.recycle();
                break;
            }
            case 4: {
                v1 = MotionEvent.obtain(v0.mDownTimeInMs, arg26, 8, v6, v0.mPointerProperties, v0.mPointerCoords, 0, 0, 1f, 1f, 0, 0, 2, 0);
                v0.mTarget.dispatchGenericMotionEvent(v1);
                v1.recycle();
                break;
            }
            case 5: 
            case 6: 
            case 7: {
                this.injectHover(arg24, arg25, arg26);
                break;
            }
            default: {
                break;
            }
        }
    }

    private void injectHover(int arg21, int arg22, long arg23) {
        MotionEventSynthesizer v0 = this;
        int v1 = arg21;
        int v2 = 9;
        if(6 == v1) {
            v2 = 10;
        }

        int v8 = 7 == v1 ? 7 : v2;
        MotionEvent v1_1 = MotionEvent.obtain(v0.mDownTimeInMs, arg23, v8, arg22, v0.mPointerProperties, v0.mPointerCoords, 0, 0, 1f, 1f, 0, 0, 2, 0);
        v0.mTarget.dispatchGenericMotionEvent(v1_1);
        v1_1.recycle();
    }

    public void setPointer(int arg7, int arg8, int arg9, int arg10) {
        this.setPointer(arg7, arg8, arg9, arg10, 0);
    }

    public void setPointer(int arg2, int arg3, int arg4, int arg5, int arg6) {
        MotionEvent$PointerCoords v0 = new MotionEvent$PointerCoords();
        v0.x = ((float)arg3);
        v0.y = ((float)arg4);
        v0.pressure = 1f;
        this.mPointerCoords[arg2] = v0;
        MotionEvent$PointerProperties v3 = new MotionEvent$PointerProperties();
        v3.id = arg5;
        v3.toolType = arg6;
        this.mPointerProperties[arg2] = v3;
    }

    public void setScrollDeltas(int arg2, int arg3, int arg4, int arg5) {
        this.setPointer(0, arg2, arg3, 0);
        this.mPointerCoords[0].setAxisValue(10, ((float)arg4));
        this.mPointerCoords[0].setAxisValue(9, ((float)arg5));
    }
}

