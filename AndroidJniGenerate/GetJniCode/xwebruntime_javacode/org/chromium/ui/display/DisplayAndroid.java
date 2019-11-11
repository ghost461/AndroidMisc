package org.chromium.ui.display;

import android.content.Context;
import android.graphics.Point;
import java.util.WeakHashMap;

public class DisplayAndroid {
    public interface DisplayAndroidObserver {
        void onDIPScaleChanged(float arg1);

        void onRotationChanged(int arg1);
    }

    private static final DisplayAndroidObserver[] EMPTY_OBSERVER_ARRAY;
    private int mBitsPerComponent;
    private int mBitsPerPixel;
    private float mDipScale;
    private final int mDisplayId;
    protected boolean mIsDisplayServerWideColorGamut;
    protected boolean mIsDisplayWideColorGamut;
    private final WeakHashMap mObservers;
    private int mRotation;
    private Point mSize;

    static {
        DisplayAndroid.EMPTY_OBSERVER_ARRAY = new DisplayAndroidObserver[0];
    }

    protected DisplayAndroid(int arg1) {
        super();
        this.mDisplayId = arg1;
        this.mObservers = new WeakHashMap();
        this.mSize = new Point();
    }

    public void addObserver(DisplayAndroidObserver arg3) {
        this.mObservers.put(arg3, null);
    }

    public float getAndroidUIScaling() {
        return 1f;
    }

    int getBitsPerComponent() {
        return this.mBitsPerComponent;
    }

    int getBitsPerPixel() {
        return this.mBitsPerPixel;
    }

    public float getDipScale() {
        return this.mDipScale;
    }

    public int getDisplayHeight() {
        return this.mSize.y;
    }

    public int getDisplayId() {
        return this.mDisplayId;
    }

    public int getDisplayWidth() {
        return this.mSize.x;
    }

    public boolean getIsWideColorGamut() {
        boolean v0 = !this.mIsDisplayWideColorGamut || !this.mIsDisplayServerWideColorGamut ? false : true;
        return v0;
    }

    protected static DisplayAndroidManager getManager() {
        return DisplayAndroidManager.getInstance();
    }

    public static DisplayAndroid getNonMultiDisplay(Context arg1) {
        return DisplayAndroid.getManager().getDisplayAndroid(DisplayAndroidManager.getDefaultDisplayForContext(arg1));
    }

    private DisplayAndroidObserver[] getObservers() {
        return this.mObservers.keySet().toArray(DisplayAndroid.EMPTY_OBSERVER_ARRAY);
    }

    public int getRotation() {
        return this.mRotation;
    }

    int getRotationDegrees() {
        switch(this.getRotation()) {
            case 0: {
                return 0;
            }
            case 1: {
                return 90;
            }
            case 2: {
                return 180;
            }
            case 3: {
                return 270;
            }
        }

        return 0;
    }

    public void removeObserver(DisplayAndroidObserver arg2) {
        this.mObservers.remove(arg2);
    }

    public static void startAccurateListening() {
        DisplayAndroid.getManager().startAccurateListening();
    }

    public static void stopAccurateListening() {
        DisplayAndroid.getManager().stopAccurateListening();
    }

    protected void update(Point arg18, Float arg19, Integer arg20, Integer arg21, Integer arg22, Boolean arg23, Boolean arg24) {
        DisplayAndroidObserver[] v1_1;
        DisplayAndroid v0 = this;
        Point v1 = arg18;
        int v5 = v1 == null || (v0.mSize.equals(v1)) ? 0 : 1;
        int v7 = arg19 == null || v0.mDipScale == arg19.floatValue() ? 0 : 1;
        int v9 = arg20 == null || v0.mBitsPerPixel == arg20.intValue() ? 0 : 1;
        int v11 = arg21 == null || v0.mBitsPerComponent == arg21.intValue() ? 0 : 1;
        int v13 = arg22 == null || v0.mRotation == arg22.intValue() ? 0 : 1;
        int v15 = arg23 == null || v0.mIsDisplayWideColorGamut == arg23.booleanValue() ? 0 : 1;
        int v2 = arg24 == null || v0.mIsDisplayServerWideColorGamut == arg24.booleanValue() ? 0 : 1;
        int v16 = v5 != 0 || v7 != 0 || v9 != 0 || v11 != 0 || v13 != 0 || v15 != 0 || v2 != 0 ? 1 : 0;
        if(v16 == 0) {
            return;
        }

        if(v5 != 0) {
            v0.mSize = v1;
        }

        if(v7 != 0) {
            v0.mDipScale = arg19.floatValue();
        }

        if(v9 != 0) {
            v0.mBitsPerPixel = arg20.intValue();
        }

        if(v11 != 0) {
            v0.mBitsPerComponent = arg21.intValue();
        }

        if(v13 != 0) {
            v0.mRotation = arg22.intValue();
        }

        if(v15 != 0) {
            v0.mIsDisplayWideColorGamut = arg23.booleanValue();
        }

        if(v2 != 0) {
            v0.mIsDisplayServerWideColorGamut = arg24.booleanValue();
        }

        DisplayAndroid.getManager().updateDisplayOnNativeSide(v0);
        if(v13 != 0) {
            v1_1 = this.getObservers();
            v2 = v1_1.length;
            int v3;
            for(v3 = 0; v3 < v2; ++v3) {
                v1_1[v3].onRotationChanged(v0.mRotation);
            }
        }

        if(v7 != 0) {
            v1_1 = this.getObservers();
            v2 = v1_1.length;
            for(v3 = 0; v3 < v2; ++v3) {
                v1_1[v3].onDIPScaleChanged(v0.mDipScale);
            }
        }
    }

    public void updateIsDisplayServerWideColorGamut(Boolean arg9) {
        this.update(null, null, null, null, null, null, arg9);
    }
}

