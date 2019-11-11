package org.chromium.device.gamepad;

import android.os.SystemClock;
import android.view.InputDevice$MotionRange;
import android.view.InputDevice;
import android.view.KeyEvent;
import android.view.MotionEvent;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.chromium.base.VisibleForTesting;

class GamepadDevice {
    @VisibleForTesting static final int MAX_RAW_AXIS_VALUES = 0x100;
    @VisibleForTesting static final int MAX_RAW_BUTTON_VALUES = 0x100;
    private int[] mAxes;
    private final float[] mAxisValues;
    private final float[] mButtonsValues;
    private int mDeviceId;
    private int mDeviceIndex;
    private String mDeviceName;
    private GamepadMappings mMappings;
    private final float[] mRawAxes;
    private final float[] mRawButtons;
    private long mTimestamp;

    static {
    }

    GamepadDevice(int arg5, InputDevice arg6) {
        super();
        this.mAxisValues = new float[4];
        this.mButtonsValues = new float[17];
        this.mRawButtons = new float[0x100];
        this.mRawAxes = new float[0x100];
        this.mDeviceIndex = arg5;
        this.mDeviceId = arg6.getId();
        this.mDeviceName = arg6.getName();
        this.mTimestamp = SystemClock.uptimeMillis();
        List v5 = arg6.getMotionRanges();
        this.mAxes = new int[v5.size()];
        Iterator v5_1 = v5.iterator();
        int v0;
        for(v0 = 0; v5_1.hasNext(); ++v0) {
            Object v1 = v5_1.next();
            if((((InputDevice$MotionRange)v1).getSource() & 16) == 0) {
                continue;
            }

            this.mAxes[v0] = ((InputDevice$MotionRange)v1).getAxis();
        }

        this.mMappings = GamepadMappings.getMappings(arg6, this.mAxes);
    }

    public void clearData() {
        Arrays.fill(this.mAxisValues, 0f);
        Arrays.fill(this.mRawAxes, 0f);
        Arrays.fill(this.mButtonsValues, 0f);
        Arrays.fill(this.mRawButtons, 0f);
    }

    public float[] getAxes() {
        return this.mAxisValues;
    }

    public float[] getButtons() {
        return this.mButtonsValues;
    }

    public int getId() {
        return this.mDeviceId;
    }

    public int getIndex() {
        return this.mDeviceIndex;
    }

    public String getName() {
        return this.mDeviceName;
    }

    public long getTimestamp() {
        return this.mTimestamp;
    }

    public boolean handleKeyEvent(KeyEvent arg5) {
        if(!GamepadList.isGamepadEvent(arg5)) {
            return 0;
        }

        int v0 = arg5.getKeyCode();
        if(arg5.getAction() == 0) {
            this.mRawButtons[v0] = 1f;
        }
        else if(arg5.getAction() == 1) {
            this.mRawButtons[v0] = 0f;
        }

        this.mTimestamp = arg5.getEventTime();
        return 1;
    }

    public boolean handleMotionEvent(MotionEvent arg5) {
        int v1 = 0;
        if(!GamepadList.isGamepadEvent(arg5)) {
            return 0;
        }

        while(v1 < this.mAxes.length) {
            this.mRawAxes[this.mAxes[v1]] = arg5.getAxisValue(this.mAxes[v1]);
            ++v1;
        }

        this.mTimestamp = arg5.getEventTime();
        return 1;
    }

    public boolean isStandardGamepad() {
        return this.mMappings.isStandard();
    }

    public void updateButtonsAndAxesMapping() {
        this.mMappings.mapToStandardGamepad(this.mAxisValues, this.mButtonsValues, this.mRawAxes, this.mRawButtons);
    }
}

