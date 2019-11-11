package org.chromium.ui.events.devices;

import android.hardware.input.InputManager$InputDeviceListener;
import android.hardware.input.InputManager;
import org.chromium.base.ContextUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="ui") public class InputDeviceObserver implements InputManager$InputDeviceListener {
    private static final InputDeviceObserver INSTANCE;
    private InputManager$InputDeviceListener mInputDeviceListener;
    private InputManager mInputManager;
    private int mObserversCounter;

    static {
        InputDeviceObserver.INSTANCE = new InputDeviceObserver();
    }

    public InputDeviceObserver() {
        super();
    }

    @CalledByNative public static void addObserver() {
        InputDeviceObserver.INSTANCE.attachObserver();
    }

    private void attachObserver() {
        int v0 = this.mObserversCounter;
        this.mObserversCounter = v0 + 1;
        if(v0 == 0) {
            this.mInputManager = ContextUtils.getApplicationContext().getSystemService("input");
            this.mInputManager.registerInputDeviceListener(((InputManager$InputDeviceListener)this), null);
        }
    }

    private void detachObserver() {
        int v0 = this.mObserversCounter - 1;
        this.mObserversCounter = v0;
        if(v0 == 0) {
            this.mInputManager.unregisterInputDeviceListener(((InputManager$InputDeviceListener)this));
            this.mInputManager = null;
        }
    }

    private native void nativeInputConfigurationChanged() {
    }

    public void onInputDeviceAdded(int arg1) {
        this.nativeInputConfigurationChanged();
    }

    public void onInputDeviceChanged(int arg1) {
        this.nativeInputConfigurationChanged();
    }

    public void onInputDeviceRemoved(int arg1) {
        this.nativeInputConfigurationChanged();
    }

    @CalledByNative public static void removeObserver() {
        InputDeviceObserver.INSTANCE.detachObserver();
    }
}

