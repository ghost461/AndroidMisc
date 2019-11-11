package org.chromium.device.gamepad;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.input.InputManager$InputDeviceListener;
import android.hardware.input.InputManager;
import android.view.InputDevice;
import android.view.InputEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="device") public class GamepadList {
    class LazyHolder {
        private static final GamepadList INSTANCE;

        static {
            LazyHolder.INSTANCE = new GamepadList(null);
        }

        private LazyHolder() {
            super();
        }

        static GamepadList access$300() {
            return LazyHolder.INSTANCE;
        }
    }

    private static final int MAX_GAMEPADS = 4;
    private int mAttachedToWindowCounter;
    private final GamepadDevice[] mGamepadDevices;
    private InputManager$InputDeviceListener mInputDeviceListener;
    private InputManager mInputManager;
    private boolean mIsGamepadAPIActive;
    private final Object mLock;

    static {
    }

    private GamepadList() {
        super();
        this.mLock = new Object();
        this.mGamepadDevices = new GamepadDevice[4];
        this.mInputDeviceListener = new InputManager$InputDeviceListener() {
            public void onInputDeviceAdded(int arg2) {
                GamepadList.access$200(GamepadList.this, arg2);
            }

            public void onInputDeviceChanged(int arg2) {
                GamepadList.access$000(GamepadList.this, arg2);
            }

            public void onInputDeviceRemoved(int arg2) {
                GamepadList.access$100(GamepadList.this, arg2);
            }
        };
    }

    GamepadList(org.chromium.device.gamepad.GamepadList$1 arg1) {
        this();
    }

    static void access$000(GamepadList arg0, int arg1) {
        arg0.onInputDeviceChangedImpl(arg1);
    }

    static void access$100(GamepadList arg0, int arg1) {
        arg0.onInputDeviceRemovedImpl(arg1);
    }

    static void access$200(GamepadList arg0, int arg1) {
        arg0.onInputDeviceAddedImpl(arg1);
    }

    private void attachedToWindow(Context arg3) {
        int v0 = this.mAttachedToWindowCounter;
        this.mAttachedToWindowCounter = v0 + 1;
        if(v0 == 0) {
            this.mInputManager = arg3.getSystemService("input");
            Object v3 = this.mLock;
            __monitor_enter(v3);
            try {
                this.initializeDevices();
                __monitor_exit(v3);
            }
            catch(Throwable v0_1) {
                try {
                label_17:
                    __monitor_exit(v3);
                }
                catch(Throwable v0_1) {
                    goto label_17;
                }

                throw v0_1;
            }

            this.mInputManager.registerInputDeviceListener(this.mInputDeviceListener, null);
        }
    }

    private void detachedFromWindow() {
        GamepadDevice v3;
        int v0 = this.mAttachedToWindowCounter - 1;
        this.mAttachedToWindowCounter = v0;
        if(v0 == 0) {
            Object v0_1 = this.mLock;
            __monitor_enter(v0_1);
            int v1 = 0;
            while(true) {
                v3 = null;
                if(v1 < 4) {
                    try {
                        this.mGamepadDevices[v1] = v3;
                        ++v1;
                        continue;
                    label_16:
                        __monitor_exit(v0_1);
                        break;
                    }
                    catch(Throwable v1_1) {
                        goto label_22;
                    }
                }
                else {
                    goto label_16;
                }

                return;
            }

            this.mInputManager.unregisterInputDeviceListener(this.mInputDeviceListener);
            this.mInputManager = ((InputManager)v3);
            return;
            try {
            label_22:
                __monitor_exit(v0_1);
            }
            catch(Throwable v1_1) {
                goto label_22;
            }

            throw v1_1;
        }
    }

    public static boolean dispatchKeyEvent(KeyEvent arg1) {
        if(!GamepadList.isGamepadEvent(arg1)) {
            return 0;
        }

        return GamepadList.getInstance().handleKeyEvent(arg1);
    }

    private GamepadDevice getDevice(int arg2) {
        return this.mGamepadDevices[arg2];
    }

    private GamepadDevice getDeviceById(int arg4) {
        int v0;
        for(v0 = 0; v0 < 4; ++v0) {
            GamepadDevice v1 = this.mGamepadDevices[v0];
            if(v1 != null && v1.getId() == arg4) {
                return v1;
            }
        }

        return null;
    }

    private int getDeviceCount() {
        int v0 = 0;
        int v1 = 0;
        while(v0 < 4) {
            if(this.getDevice(v0) != null) {
                ++v1;
            }

            ++v0;
        }

        return v1;
    }

    private GamepadDevice getGamepadForEvent(InputEvent arg1) {
        return this.getDeviceById(arg1.getDeviceId());
    }

    private static GamepadList getInstance() {
        return LazyHolder.access$300();
    }

    private int getNextAvailableIndex() {
        int v0;
        for(v0 = 0; v0 < 4; ++v0) {
            if(this.getDevice(v0) == null) {
                return v0;
            }
        }

        return -1;
    }

    private void grabGamepadData(long arg14) {
        Object v0 = this.mLock;
        __monitor_enter(v0);
        int v1 = 0;
        while(true) {
            if(v1 >= 4) {
                goto label_33;
            }

            try {
                GamepadDevice v2 = this.getDevice(v1);
                if(v2 != null) {
                    v2.updateButtonsAndAxesMapping();
                    this.nativeSetGamepadData(arg14, v1, v2.isStandardGamepad(), true, v2.getName(), v2.getTimestamp(), v2.getAxes(), v2.getButtons());
                }
                else {
                    this.nativeSetGamepadData(arg14, v1, false, false, null, 0, null, null);
                }

                ++v1;
                continue;
            label_33:
                __monitor_exit(v0);
                return;
            label_35:
                __monitor_exit(v0);
                break;
            }
            catch(Throwable v14) {
                goto label_35;
            }
        }

        throw v14;
    }

    private boolean handleKeyEvent(KeyEvent arg4) {
        Object v0 = this.mLock;
        __monitor_enter(v0);
        try {
            if(!this.mIsGamepadAPIActive) {
                __monitor_exit(v0);
                return 0;
            }

            GamepadDevice v1 = this.getGamepadForEvent(((InputEvent)arg4));
            if(v1 == null) {
                __monitor_exit(v0);
                return 0;
            }

            __monitor_exit(v0);
            return v1.handleKeyEvent(arg4);
        label_15:
            __monitor_exit(v0);
        }
        catch(Throwable v4) {
            goto label_15;
        }

        throw v4;
    }

    private boolean handleMotionEvent(MotionEvent arg4) {
        Object v0 = this.mLock;
        __monitor_enter(v0);
        try {
            if(!this.mIsGamepadAPIActive) {
                __monitor_exit(v0);
                return 0;
            }

            GamepadDevice v1 = this.getGamepadForEvent(((InputEvent)arg4));
            if(v1 == null) {
                __monitor_exit(v0);
                return 0;
            }

            __monitor_exit(v0);
            return v1.handleMotionEvent(arg4);
        label_15:
            __monitor_exit(v0);
        }
        catch(Throwable v4) {
            goto label_15;
        }

        throw v4;
    }

    private void initializeDevices() {
        int[] v0 = this.mInputManager.getInputDeviceIds();
        int v1;
        for(v1 = 0; v1 < v0.length; ++v1) {
            InputDevice v2 = InputDevice.getDevice(v0[v1]);
            if(GamepadList.isGamepadDevice(v2)) {
                this.registerGamepad(v2);
            }
        }
    }

    private boolean isDeviceConnected(int arg2) {
        if(arg2 < 4 && this.getDevice(arg2) != null) {
            return 1;
        }

        return 0;
    }

    public static boolean isGamepadAPIActive() {
        return GamepadList.getInstance().mIsGamepadAPIActive;
    }

    private static boolean isGamepadDevice(InputDevice arg2) {
        boolean v0 = false;
        if(arg2 == null) {
            return 0;
        }

        if((arg2.getSources() & 0x1000010) == 0x1000010) {
            v0 = true;
        }

        return v0;
    }

    public static boolean isGamepadEvent(KeyEvent arg0) {
        int v0 = arg0.getKeyCode();
        switch(v0) {
            case 19: 
            case 20: 
            case 21: 
            case 22: {
                return 1;
            }
        }

        return KeyEvent.isGamepadButton(v0);
    }

    public static boolean isGamepadEvent(MotionEvent arg1) {
        boolean v1 = (arg1.getSource() & 0x1000010) == 0x1000010 ? true : false;
        return v1;
    }

    private native void nativeSetGamepadData(long arg1, int arg2, boolean arg3, boolean arg4, String arg5, long arg6, float[] arg7, float[] arg8) {
    }

    public static void onAttachedToWindow(Context arg1) {
        GamepadList.getInstance().attachedToWindow(arg1);
    }

    @SuppressLint(value={"MissingSuperCall"}) public static void onDetachedFromWindow() {
        GamepadList.getInstance().detachedFromWindow();
    }

    public static boolean onGenericMotionEvent(MotionEvent arg1) {
        if(!GamepadList.isGamepadEvent(arg1)) {
            return 0;
        }

        return GamepadList.getInstance().handleMotionEvent(arg1);
    }

    private void onInputDeviceAddedImpl(int arg2) {
        InputDevice v2 = InputDevice.getDevice(arg2);
        if(!GamepadList.isGamepadDevice(v2)) {
            return;
        }

        Object v0 = this.mLock;
        __monitor_enter(v0);
        try {
            this.registerGamepad(v2);
            __monitor_exit(v0);
            return;
        label_10:
            __monitor_exit(v0);
        }
        catch(Throwable v2_1) {
            goto label_10;
        }

        throw v2_1;
    }

    private void onInputDeviceChangedImpl(int arg1) {
    }

    private void onInputDeviceRemovedImpl(int arg2) {
        Object v0 = this.mLock;
        __monitor_enter(v0);
        try {
            this.unregisterGamepad(arg2);
            __monitor_exit(v0);
            return;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v2) {
            goto label_6;
        }

        throw v2;
    }

    private boolean registerGamepad(InputDevice arg3) {
        int v0 = this.getNextAvailableIndex();
        if(v0 == -1) {
            return 0;
        }

        this.mGamepadDevices[v0] = new GamepadDevice(v0, arg3);
        return 1;
    }

    @CalledByNative static void setGamepadAPIActive(boolean arg1) {
        GamepadList.getInstance().setIsGamepadActive(arg1);
    }

    private void setIsGamepadActive(boolean arg3) {
        Object v0 = this.mLock;
        __monitor_enter(v0);
        try {
            this.mIsGamepadAPIActive = arg3;
            if(arg3) {
                int v3_1;
                for(v3_1 = 0; v3_1 < 4; ++v3_1) {
                    GamepadDevice v1 = this.getDevice(v3_1);
                    if(v1 == null) {
                    }
                    else {
                        v1.clearData();
                    }
                }
            }

            __monitor_exit(v0);
            return;
        label_16:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_16;
        }

        throw v3;
    }

    private void unregisterGamepad(int arg3) {
        GamepadDevice v3 = this.getDeviceById(arg3);
        if(v3 == null) {
            return;
        }

        this.mGamepadDevices[v3.getIndex()] = null;
    }

    @CalledByNative static void updateGamepadData(long arg1) {
        GamepadList.getInstance().grabGamepadData(arg1);
    }
}

