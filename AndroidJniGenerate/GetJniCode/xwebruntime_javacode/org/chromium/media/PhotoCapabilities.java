package org.chromium.media;

import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="media") class PhotoCapabilities {
    public class Builder {
        public int currentColorTemperature;
        public double currentExposureCompensation;
        public int currentHeight;
        public int currentIso;
        public int currentWidth;
        public double currentZoom;
        public int exposureMode;
        public int[] exposureModes;
        public int[] fillLightModes;
        public int focusMode;
        public int[] focusModes;
        public int maxColorTemperature;
        public double maxExposureCompensation;
        public int maxHeight;
        public int maxIso;
        public int maxWidth;
        public double maxZoom;
        public int minColorTemperature;
        public double minExposureCompensation;
        public int minHeight;
        public int minIso;
        public int minWidth;
        public double minZoom;
        public boolean redEyeReduction;
        public int stepColorTemperature;
        public double stepExposureCompensation;
        public int stepHeight;
        public int stepIso;
        public int stepWidth;
        public double stepZoom;
        public boolean supportsTorch;
        public boolean torch;
        public int whiteBalanceMode;
        public int[] whiteBalanceModes;

        public Builder() {
            super();
        }

        public PhotoCapabilities build() {
            return new PhotoCapabilities(this.maxIso, this.minIso, this.currentIso, this.stepIso, this.maxHeight, this.minHeight, this.currentHeight, this.stepHeight, this.maxWidth, this.minWidth, this.currentWidth, this.stepWidth, this.maxZoom, this.minZoom, this.currentZoom, this.stepZoom, this.focusMode, this.focusModes, this.exposureMode, this.exposureModes, this.maxExposureCompensation, this.minExposureCompensation, this.currentExposureCompensation, this.stepExposureCompensation, this.whiteBalanceMode, this.whiteBalanceModes, this.fillLightModes, this.supportsTorch, this.torch, this.redEyeReduction, this.maxColorTemperature, this.minColorTemperature, this.currentColorTemperature, this.stepColorTemperature);
        }

        public Builder setCurrentColorTemperature(int arg1) {
            this.currentColorTemperature = arg1;
            return this;
        }

        public Builder setCurrentExposureCompensation(double arg1) {
            this.currentExposureCompensation = arg1;
            return this;
        }

        public Builder setCurrentHeight(int arg1) {
            this.currentHeight = arg1;
            return this;
        }

        public Builder setCurrentIso(int arg1) {
            this.currentIso = arg1;
            return this;
        }

        public Builder setCurrentWidth(int arg1) {
            this.currentWidth = arg1;
            return this;
        }

        public Builder setCurrentZoom(double arg1) {
            this.currentZoom = arg1;
            return this;
        }

        public Builder setExposureMode(int arg1) {
            this.exposureMode = arg1;
            return this;
        }

        public Builder setExposureModes(int[] arg1) {
            this.exposureModes = arg1.clone();
            return this;
        }

        public Builder setFillLightModes(int[] arg1) {
            this.fillLightModes = arg1.clone();
            return this;
        }

        public Builder setFocusMode(int arg1) {
            this.focusMode = arg1;
            return this;
        }

        public Builder setFocusModes(int[] arg1) {
            this.focusModes = arg1.clone();
            return this;
        }

        public Builder setMaxColorTemperature(int arg1) {
            this.maxColorTemperature = arg1;
            return this;
        }

        public Builder setMaxExposureCompensation(double arg1) {
            this.maxExposureCompensation = arg1;
            return this;
        }

        public Builder setMaxHeight(int arg1) {
            this.maxHeight = arg1;
            return this;
        }

        public Builder setMaxIso(int arg1) {
            this.maxIso = arg1;
            return this;
        }

        public Builder setMaxWidth(int arg1) {
            this.maxWidth = arg1;
            return this;
        }

        public Builder setMaxZoom(double arg1) {
            this.maxZoom = arg1;
            return this;
        }

        public Builder setMinColorTemperature(int arg1) {
            this.minColorTemperature = arg1;
            return this;
        }

        public Builder setMinExposureCompensation(double arg1) {
            this.minExposureCompensation = arg1;
            return this;
        }

        public Builder setMinHeight(int arg1) {
            this.minHeight = arg1;
            return this;
        }

        public Builder setMinIso(int arg1) {
            this.minIso = arg1;
            return this;
        }

        public Builder setMinWidth(int arg1) {
            this.minWidth = arg1;
            return this;
        }

        public Builder setMinZoom(double arg1) {
            this.minZoom = arg1;
            return this;
        }

        public Builder setRedEyeReduction(boolean arg1) {
            this.redEyeReduction = arg1;
            return this;
        }

        public Builder setStepColorTemperature(int arg1) {
            this.stepColorTemperature = arg1;
            return this;
        }

        public Builder setStepExposureCompensation(double arg1) {
            this.stepExposureCompensation = arg1;
            return this;
        }

        public Builder setStepHeight(int arg1) {
            this.stepHeight = arg1;
            return this;
        }

        public Builder setStepIso(int arg1) {
            this.stepIso = arg1;
            return this;
        }

        public Builder setStepWidth(int arg1) {
            this.stepWidth = arg1;
            return this;
        }

        public Builder setStepZoom(double arg1) {
            this.stepZoom = arg1;
            return this;
        }

        public Builder setSupportsTorch(boolean arg1) {
            this.supportsTorch = arg1;
            return this;
        }

        public Builder setTorch(boolean arg1) {
            this.torch = arg1;
            return this;
        }

        public Builder setWhiteBalanceMode(int arg1) {
            this.whiteBalanceMode = arg1;
            return this;
        }

        public Builder setWhiteBalanceModes(int[] arg1) {
            this.whiteBalanceModes = arg1.clone();
            return this;
        }
    }

    public final int currentColorTemperature;
    public final double currentExposureCompensation;
    public final int currentHeight;
    public final int currentIso;
    public final int currentWidth;
    public final double currentZoom;
    public final int exposureMode;
    public final int[] exposureModes;
    public final int[] fillLightModes;
    public final int focusMode;
    public final int[] focusModes;
    public final int maxColorTemperature;
    public final double maxExposureCompensation;
    public final int maxHeight;
    public final int maxIso;
    public final int maxWidth;
    public final double maxZoom;
    public final int minColorTemperature;
    public final double minExposureCompensation;
    public final int minHeight;
    public final int minIso;
    public final int minWidth;
    public final double minZoom;
    public final boolean redEyeReduction;
    public final int stepColorTemperature;
    public final double stepExposureCompensation;
    public final int stepHeight;
    public final int stepIso;
    public final int stepWidth;
    public final double stepZoom;
    public final boolean supportsTorch;
    public final boolean torch;
    public final int whiteBalanceMode;
    public final int[] whiteBalanceModes;

    PhotoCapabilities(int arg4, int arg5, int arg6, int arg7, int arg8, int arg9, int arg10, int arg11, int arg12, int arg13, int arg14, int arg15, double arg16, double arg18, double arg20, double arg22, int arg24, int[] arg25, int arg26, int[] arg27, double arg28, double arg30, double arg32, double arg34, int arg36, int[] arg37, int[] arg38, boolean arg39, boolean arg40, boolean arg41, int arg42, int arg43, int arg44, int arg45) {
        super();
        this.maxIso = arg4;
        this.minIso = arg5;
        this.currentIso = arg6;
        this.stepIso = arg7;
        this.maxHeight = arg8;
        this.minHeight = arg9;
        this.currentHeight = arg10;
        this.stepHeight = arg11;
        this.maxWidth = arg12;
        this.minWidth = arg13;
        this.currentWidth = arg14;
        this.stepWidth = arg15;
        this.maxZoom = arg16;
        this.minZoom = arg18;
        this.currentZoom = arg20;
        this.stepZoom = arg22;
        this.focusMode = arg24;
        this.focusModes = arg25;
        this.exposureMode = arg26;
        this.exposureModes = arg27;
        this.maxExposureCompensation = arg28;
        this.minExposureCompensation = arg30;
        this.currentExposureCompensation = arg32;
        this.stepExposureCompensation = arg34;
        this.whiteBalanceMode = arg36;
        this.whiteBalanceModes = arg37;
        this.fillLightModes = arg38;
        this.supportsTorch = arg39;
        this.torch = arg40;
        this.redEyeReduction = arg41;
        this.maxColorTemperature = arg42;
        this.minColorTemperature = arg43;
        this.currentColorTemperature = arg44;
        this.stepColorTemperature = arg45;
    }

    @CalledByNative public int getCurrentColorTemperature() {
        return this.currentColorTemperature;
    }

    @CalledByNative public double getCurrentExposureCompensation() {
        return this.currentExposureCompensation;
    }

    @CalledByNative public int getCurrentHeight() {
        return this.currentHeight;
    }

    @CalledByNative public int getCurrentIso() {
        return this.currentIso;
    }

    @CalledByNative public int getCurrentWidth() {
        return this.currentWidth;
    }

    @CalledByNative public double getCurrentZoom() {
        return this.currentZoom;
    }

    @CalledByNative public int getExposureMode() {
        return this.exposureMode;
    }

    @CalledByNative public int[] getExposureModes() {
        int[] v0_1;
        if(this.exposureModes != null) {
            Object v0 = this.exposureModes.clone();
        }
        else {
            v0_1 = new int[0];
        }

        return v0_1;
    }

    @CalledByNative public int[] getFillLightModes() {
        int[] v0_1;
        if(this.fillLightModes != null) {
            Object v0 = this.fillLightModes.clone();
        }
        else {
            v0_1 = new int[0];
        }

        return v0_1;
    }

    @CalledByNative public int getFocusMode() {
        return this.focusMode;
    }

    @CalledByNative public int[] getFocusModes() {
        int[] v0_1;
        if(this.focusModes != null) {
            Object v0 = this.focusModes.clone();
        }
        else {
            v0_1 = new int[0];
        }

        return v0_1;
    }

    @CalledByNative public int getMaxColorTemperature() {
        return this.maxColorTemperature;
    }

    @CalledByNative public double getMaxExposureCompensation() {
        return this.maxExposureCompensation;
    }

    @CalledByNative public int getMaxHeight() {
        return this.maxHeight;
    }

    @CalledByNative public int getMaxIso() {
        return this.maxIso;
    }

    @CalledByNative public int getMaxWidth() {
        return this.maxWidth;
    }

    @CalledByNative public double getMaxZoom() {
        return this.maxZoom;
    }

    @CalledByNative public int getMinColorTemperature() {
        return this.minColorTemperature;
    }

    @CalledByNative public double getMinExposureCompensation() {
        return this.minExposureCompensation;
    }

    @CalledByNative public int getMinHeight() {
        return this.minHeight;
    }

    @CalledByNative public int getMinIso() {
        return this.minIso;
    }

    @CalledByNative public int getMinWidth() {
        return this.minWidth;
    }

    @CalledByNative public double getMinZoom() {
        return this.minZoom;
    }

    @CalledByNative public boolean getRedEyeReduction() {
        return this.redEyeReduction;
    }

    @CalledByNative public int getStepColorTemperature() {
        return this.stepColorTemperature;
    }

    @CalledByNative public double getStepExposureCompensation() {
        return this.stepExposureCompensation;
    }

    @CalledByNative public int getStepHeight() {
        return this.stepHeight;
    }

    @CalledByNative public int getStepIso() {
        return this.stepIso;
    }

    @CalledByNative public int getStepWidth() {
        return this.stepWidth;
    }

    @CalledByNative public double getStepZoom() {
        return this.stepZoom;
    }

    @CalledByNative public boolean getSupportsTorch() {
        return this.supportsTorch;
    }

    @CalledByNative public boolean getTorch() {
        return this.torch;
    }

    @CalledByNative public int getWhiteBalanceMode() {
        return this.whiteBalanceMode;
    }

    @CalledByNative public int[] getWhiteBalanceModes() {
        Object v0;
        if(this.whiteBalanceModes != null) {
            v0 = this.whiteBalanceModes.clone();
        }
        else {
            int[] v0_1 = new int[0];
        }

        return ((int[])v0);
    }
}

