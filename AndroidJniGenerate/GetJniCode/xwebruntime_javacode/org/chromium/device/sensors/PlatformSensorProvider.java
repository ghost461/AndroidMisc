package org.chromium.device.sensors;

import android.content.Context;
import android.hardware.SensorManager;
import android.os.Build$VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import java.util.HashSet;
import java.util.Set;
import org.chromium.base.ContextUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="device") class PlatformSensorProvider {
    private final Set mActiveSensors;
    private Handler mHandler;
    private SensorManager mSensorManager;
    private HandlerThread mSensorsThread;

    protected PlatformSensorProvider(Context arg2) {
        super();
        this.mActiveSensors = new HashSet();
        this.mSensorManager = arg2.getSystemService("sensor");
    }

    @CalledByNative protected static PlatformSensorProvider create() {
        return new PlatformSensorProvider(ContextUtils.getApplicationContext());
    }

    protected static PlatformSensorProvider createForTest(Context arg1) {
        return new PlatformSensorProvider(arg1);
    }

    @CalledByNative protected PlatformSensor createSensor(int arg6) {
        PlatformSensor v1 = null;
        if(this.mSensorManager == null) {
            return v1;
        }

        if(arg6 == 1) {
            goto label_29;
        }

        int v3 = 11;
        int v4 = 4;
        if(arg6 == 9) {
            goto label_27;
        }

        if(arg6 == v3) {
            goto label_24;
        }

        int v2 = 3;
        switch(arg6) {
            case 3: {
                goto label_22;
            }
            case 4: {
                goto label_19;
            }
            case 5: {
                goto label_17;
            }
            case 6: {
                goto label_14;
            }
        }

        return v1;
    label_17:
        return PlatformSensor.create(v4, v2, this);
    label_19:
        return PlatformSensor.create(10, v2, this);
    label_22:
        return PlatformSensor.create(1, v2, this);
    label_14:
        return PlatformSensor.create(2, v2, this);
    label_24:
        return PlatformSensor.create(15, v4, this);
    label_27:
        return PlatformSensor.create(v3, v4, this);
    label_29:
        return PlatformSensor.create(5, 1, this);
    }

    public Handler getHandler() {
        return this.mHandler;
    }

    public SensorManager getSensorManager() {
        return this.mSensorManager;
    }

    @CalledByNative protected boolean hasSensorType(int arg5) {
        if(this.mSensorManager == null) {
            return 0;
        }

        int v0 = 11;
        if(arg5 == 1) {
            v0 = 5;
        }
        else if(arg5 != 9) {
            if(arg5 != v0) {
                switch(arg5) {
                    case 3: {
                        goto label_18;
                    }
                    case 4: {
                        goto label_16;
                    }
                    case 5: {
                        goto label_14;
                    }
                    case 6: {
                        goto label_12;
                    }
                }

                return 0;
            label_18:
                v0 = 1;
                goto label_23;
            label_12:
                v0 = 2;
                goto label_23;
            label_14:
                v0 = 4;
                goto label_23;
            label_16:
                v0 = 10;
            }
            else {
                v0 = 15;
            }
        }

    label_23:
        return this.mSensorManager.getSensorList(v0).isEmpty() ^ 1;
    }

    public void sensorStarted(PlatformSensor arg3) {
        Set v0 = this.mActiveSensors;
        __monitor_enter(v0);
        try {
            if(this.mActiveSensors.isEmpty()) {
                this.startSensorThread();
            }

            this.mActiveSensors.add(arg3);
            __monitor_exit(v0);
            return;
        label_11:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_11;
        }

        throw v3;
    }

    public void sensorStopped(PlatformSensor arg3) {
        Set v0 = this.mActiveSensors;
        __monitor_enter(v0);
        try {
            this.mActiveSensors.remove(arg3);
            if(this.mActiveSensors.isEmpty()) {
                this.stopSensorThread();
            }

            __monitor_exit(v0);
            return;
        label_11:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_11;
        }

        throw v3;
    }

    @CalledByNative protected void setSensorManagerToNullForTesting() {
        this.mSensorManager = null;
    }

    protected void startSensorThread() {
        if(this.mSensorsThread == null) {
            this.mSensorsThread = new HandlerThread("SensorsHandlerThread");
            this.mSensorsThread.start();
            this.mHandler = new Handler(this.mSensorsThread.getLooper());
        }
    }

    protected void stopSensorThread() {
        if(this.mSensorsThread != null) {
            if(Build$VERSION.SDK_INT >= 18) {
                this.mSensorsThread.quitSafely();
            }
            else {
                this.mSensorsThread.quit();
            }

            this.mSensorsThread = null;
            this.mHandler = null;
        }
    }
}

