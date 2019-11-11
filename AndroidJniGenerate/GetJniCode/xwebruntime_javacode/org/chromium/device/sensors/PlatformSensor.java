package org.chromium.device.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Build$VERSION;
import java.util.List;
import org.chromium.base.Log;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="device") public class PlatformSensor implements SensorEventListener {
    private static final double MICROSECONDS_IN_SECOND = 1000000;
    private static final double SECONDS_IN_MICROSECOND = 0.000001;
    private static final double SECONDS_IN_NANOSECOND = 1.000000E-09;
    private static final double SENSOR_FREQUENCY_NORMAL = 5;
    private static final String TAG = "PlatformSensor";
    private double mCurrentPollingFrequency;
    private final int mMinDelayUsec;
    private long mNativePlatformSensorAndroid;
    private final PlatformSensorProvider mProvider;
    private final int mReadingCount;
    private final Sensor mSensor;

    static {
    }

    protected PlatformSensor(Sensor arg1, int arg2, PlatformSensorProvider arg3) {
        super();
        this.mReadingCount = arg2;
        this.mProvider = arg3;
        this.mSensor = arg1;
        this.mMinDelayUsec = this.mSensor.getMinDelay();
    }

    @CalledByNative protected boolean checkSensorConfiguration(double arg2) {
        boolean v2 = this.mMinDelayUsec <= this.getSamplingPeriod(arg2) ? true : false;
        return v2;
    }

    public static PlatformSensor create(int arg2, int arg3, PlatformSensorProvider arg4) {
        List v2 = arg4.getSensorManager().getSensorList(arg2);
        if(v2.isEmpty()) {
            return null;
        }

        return new PlatformSensor(v2.get(0), arg3, arg4);
    }

    @CalledByNative protected double getDefaultConfiguration() {
        return 5;
    }

    @CalledByNative protected double getMaximumSupportedFrequency() {
        if(this.mMinDelayUsec == 0) {
            return this.getDefaultConfiguration();
        }

        return 1 / ((((double)this.mMinDelayUsec)) * 0.000001);
    }

    @CalledByNative protected int getReportingMode() {
        int v1 = 1;
        if(Build$VERSION.SDK_INT >= 21) {
            if(this.mSensor.getReportingMode() == 0) {
            }
            else {
                v1 = 0;
            }

            return v1;
        }

        return 1;
    }

    private int getSamplingPeriod(double arg3) {
        return ((int)(1 / arg3 * 1000000));
    }

    @CalledByNative protected void initPlatformSensorAndroid(long arg1) {
        this.mNativePlatformSensorAndroid = arg1;
    }

    private native void nativeNotifyPlatformSensorError(long arg1) {
    }

    private native void nativeUpdatePlatformSensorReading(long arg1, double arg2, double arg3, double arg4, double arg5, double arg6) {
    }

    public void onAccuracyChanged(Sensor arg1, int arg2) {
    }

    public void onSensorChanged(SensorEvent arg17) {
        PlatformSensor v11 = this;
        SensorEvent v0 = arg17;
        if(Long.compare(v11.mNativePlatformSensorAndroid, 0) == 0) {
            Log.w("PlatformSensor", "Should not get sensor events after PlatformSensorAndroid is destroyed.", new Object[0]);
            return;
        }

        if(v0.values.length < v11.mReadingCount) {
            this.sensorError();
            this.stopSensor();
            return;
        }

        double v2 = (((double)v0.timestamp)) * 1.000000E-09;
        int v5 = 2;
        switch(v0.values.length) {
            case 1: {
                v11.updateSensorReading(v2, ((double)v0.values[0]), 0, 0, 0);
                break;
            }
            case 2: {
                v11.updateSensorReading(v2, ((double)v0.values[0]), ((double)v0.values[1]), 0, 0);
                break;
            }
            case 3: {
                v11.updateSensorReading(v2, ((double)v0.values[0]), ((double)v0.values[1]), ((double)v0.values[v5]), 0);
                break;
            }
            default: {
                v11.updateSensorReading(v2, ((double)v0.values[0]), ((double)v0.values[1]), ((double)v0.values[v5]), ((double)v0.values[3]));
                break;
            }
        }
    }

    @CalledByNative protected void sensorDestroyed() {
        this.stopSensor();
        this.mNativePlatformSensorAndroid = 0;
    }

    protected void sensorError() {
        this.nativeNotifyPlatformSensorError(this.mNativePlatformSensorAndroid);
    }

    @CalledByNative protected boolean startSensor(double arg5) {
        if(this.mCurrentPollingFrequency == arg5) {
            return 1;
        }

        this.unregisterListener();
        this.mProvider.sensorStarted(this);
        boolean v0 = this.mProvider.getSensorManager().registerListener(((SensorEventListener)this), this.mSensor, this.getSamplingPeriod(arg5), this.mProvider.getHandler());
        if(!v0) {
            this.stopSensor();
            return v0;
        }

        this.mCurrentPollingFrequency = arg5;
        return v0;
    }

    @CalledByNative protected void stopSensor() {
        this.unregisterListener();
        this.mProvider.sensorStopped(this);
        this.mCurrentPollingFrequency = 0;
    }

    private void unregisterListener() {
        if(this.mCurrentPollingFrequency == 0) {
            return;
        }

        this.mProvider.getSensorManager().unregisterListener(((SensorEventListener)this), this.mSensor);
    }

    protected void updateSensorReading(double arg15, double arg17, double arg19, double arg21, double arg23) {
        this.nativeUpdatePlatformSensorReading(this.mNativePlatformSensorAndroid, arg15, arg17, arg19, arg21, arg23);
    }
}

