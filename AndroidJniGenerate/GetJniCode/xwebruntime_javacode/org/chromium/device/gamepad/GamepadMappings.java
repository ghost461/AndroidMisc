package org.chromium.device.gamepad;

import android.annotation.TargetApi;
import android.os.Build$VERSION;
import android.view.InputDevice;
import org.chromium.base.VisibleForTesting;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="content") abstract class GamepadMappings {
    class org.chromium.device.gamepad.GamepadMappings$1 {
    }

    class AmazonFireGamepadMappings extends GamepadMappings {
        AmazonFireGamepadMappings(org.chromium.device.gamepad.GamepadMappings$1 arg1) {
            this();
        }

        private AmazonFireGamepadMappings() {
            super();
        }

        public void mapToStandardGamepad(float[] arg1, float[] arg2, float[] arg3, float[] arg4) {
            GamepadMappings.mapCommonXYABButtons(arg2, arg4);
            GamepadMappings.mapTriggerButtonsToTopShoulder(arg2, arg4);
            GamepadMappings.mapCommonThumbstickButtons(arg2, arg4);
            GamepadMappings.mapCommonStartSelectMetaButtons(arg2, arg4);
            GamepadMappings.mapPedalAxesToBottomShoulder(arg2, arg3);
            GamepadMappings.mapHatAxisToDpadButtons(arg2, arg3);
            GamepadMappings.mapXYAxes(arg1, arg3);
            GamepadMappings.mapZAndRZAxesToRightStick(arg1, arg3);
        }
    }

    class PS3SixAxisGamepadMappings extends GamepadMappings {
        PS3SixAxisGamepadMappings(org.chromium.device.gamepad.GamepadMappings$1 arg1) {
            this();
        }

        private PS3SixAxisGamepadMappings() {
            super();
        }

        public void mapToStandardGamepad(float[] arg6, float[] arg7, float[] arg8, float[] arg9) {
            float v0 = arg9[0x60];
            float v1 = arg9[97];
            float v2 = arg9[99];
            float v3 = arg9[100];
            arg7[0] = v2;
            arg7[1] = v3;
            arg7[2] = v0;
            arg7[3] = v1;
            GamepadMappings.mapTriggerButtonsToTopShoulder(arg7, arg9);
            GamepadMappings.mapCommonThumbstickButtons(arg7, arg9);
            GamepadMappings.mapCommonDpadButtons(arg7, arg9);
            GamepadMappings.mapCommonStartSelectMetaButtons(arg7, arg9);
            GamepadMappings.mapTriggerAxesToBottomShoulder(arg7, arg8);
            GamepadMappings.mapXYAxes(arg6, arg8);
            GamepadMappings.mapZAndRZAxesToRightStick(arg6, arg8);
        }
    }

    class PS4GamepadMappings extends GamepadMappings {
        PS4GamepadMappings() {
            super();
        }

        public void mapToStandardGamepad(float[] arg6, float[] arg7, float[] arg8, float[] arg9) {
            float v0 = arg9[0x60];
            float v1 = arg9[97];
            float v2 = arg9[98];
            float v3 = arg9[99];
            arg7[0] = v1;
            arg7[1] = v2;
            arg7[2] = v0;
            arg7[3] = v3;
            v0 = arg9[100];
            v1 = arg9[101];
            arg7[4] = v0;
            arg7[5] = v1;
            v0 = arg8[12];
            v1 = arg8[13];
            arg7[6] = PS4GamepadMappings.scaleRxRy(v0);
            arg7[7] = PS4GamepadMappings.scaleRxRy(v1);
            v0 = arg9[104];
            v1 = arg9[105];
            arg7[8] = v0;
            arg7[9] = v1;
            v0 = arg9[109];
            v1 = arg9[108];
            arg7[10] = v0;
            arg7[11] = v1;
            arg7[16] = arg9[110];
            GamepadMappings.mapHatAxisToDpadButtons(arg7, arg8);
            GamepadMappings.mapXYAxes(arg6, arg8);
            GamepadMappings.mapZAndRZAxesToRightStick(arg6, arg8);
        }

        private static float scaleRxRy(float arg2) {
            return 1f - (1f - arg2) / 2f;
        }
    }

    class SamsungEIGP20GamepadMappings extends GamepadMappings {
        SamsungEIGP20GamepadMappings(org.chromium.device.gamepad.GamepadMappings$1 arg1) {
            this();
        }

        private SamsungEIGP20GamepadMappings() {
            super();
        }

        public void mapToStandardGamepad(float[] arg1, float[] arg2, float[] arg3, float[] arg4) {
            GamepadMappings.mapCommonXYABButtons(arg2, arg4);
            GamepadMappings.mapUpperTriggerButtonsToBottomShoulder(arg2, arg4);
            GamepadMappings.mapCommonThumbstickButtons(arg2, arg4);
            GamepadMappings.mapCommonStartSelectMetaButtons(arg2, arg4);
            GamepadMappings.mapHatAxisToDpadButtons(arg2, arg3);
            GamepadMappings.mapXYAxes(arg1, arg3);
            GamepadMappings.mapRXAndRYAxesToRightStick(arg1, arg3);
        }
    }

    class UnknownGamepadMappings extends GamepadMappings {
        private int mLeftTriggerAxis;
        private int mRightStickXAxis;
        private int mRightStickYAxis;
        private int mRightTriggerAxis;
        private boolean mUseHatAxes;

        UnknownGamepadMappings(int[] arg5) {
            super();
            this.mLeftTriggerAxis = -1;
            this.mRightTriggerAxis = -1;
            this.mRightStickXAxis = -1;
            this.mRightStickYAxis = -1;
            int v0 = arg5.length;
            int v1 = 0;
            int v2 = 0;
            while(v1 < v0) {
                int v3 = arg5[v1];
                switch(v3) {
                    case 11: 
                    case 12: {
                        this.mRightStickXAxis = v3;
                        break;
                    }
                    case 13: 
                    case 14: {
                        this.mRightStickYAxis = v3;
                        break;
                    }
                    case 15: {
                        ++v2;
                        break;
                    }
                    case 16: {
                        ++v2;
                        break;
                    }
                    case 18: 
                    case 19: 
                    case 22: {
                        this.mRightTriggerAxis = v3;
                        break;
                    }
                    case 17: 
                    case 23: {
                        this.mLeftTriggerAxis = v3;
                        break;
                    }
                    default: {
                        break;
                    }
                }

                ++v1;
            }

            if(v2 == 2) {
                this.mUseHatAxes = true;
            }
        }

        public boolean isStandard() {
            return 0;
        }

        public void mapToStandardGamepad(float[] arg5, float[] arg6, float[] arg7, float[] arg8) {
            float v0;
            GamepadMappings.mapCommonXYABButtons(arg6, arg8);
            GamepadMappings.mapTriggerButtonsToTopShoulder(arg6, arg8);
            GamepadMappings.mapCommonThumbstickButtons(arg6, arg8);
            GamepadMappings.mapCommonStartSelectMetaButtons(arg6, arg8);
            GamepadMappings.mapXYAxes(arg5, arg7);
            int v1 = -1;
            if(this.mLeftTriggerAxis == v1 || this.mRightTriggerAxis == v1) {
                GamepadMappings.mapLowerTriggerButtonsToBottomShoulder(arg6, arg8);
            }
            else {
                v0 = arg7[this.mLeftTriggerAxis];
                float v2 = arg7[this.mRightTriggerAxis];
                arg6[6] = v0;
                arg6[7] = v2;
            }

            if(this.mRightStickXAxis != v1 && this.mRightStickYAxis != v1) {
                v0 = arg7[this.mRightStickXAxis];
                float v1_1 = arg7[this.mRightStickYAxis];
                arg5[2] = v0;
                arg5[3] = v1_1;
            }

            if(this.mUseHatAxes) {
                GamepadMappings.mapHatAxisToDpadButtons(arg6, arg7);
            }
            else {
                GamepadMappings.mapCommonDpadButtons(arg6, arg8);
            }
        }
    }

    class XboxCompatibleGamepadMappings extends GamepadMappings {
        XboxCompatibleGamepadMappings(org.chromium.device.gamepad.GamepadMappings$1 arg1) {
            this();
        }

        private XboxCompatibleGamepadMappings() {
            super();
        }

        public void mapToStandardGamepad(float[] arg1, float[] arg2, float[] arg3, float[] arg4) {
            GamepadMappings.mapCommonXYABButtons(arg2, arg4);
            GamepadMappings.mapTriggerButtonsToTopShoulder(arg2, arg4);
            GamepadMappings.mapCommonThumbstickButtons(arg2, arg4);
            GamepadMappings.mapCommonStartSelectMetaButtons(arg2, arg4);
            GamepadMappings.mapTriggerAxesToBottomShoulder(arg2, arg3);
            GamepadMappings.mapHatAxisToDpadButtons(arg2, arg3);
            GamepadMappings.mapXYAxes(arg1, arg3);
            GamepadMappings.mapZAndRZAxesToRightStick(arg1, arg3);
        }
    }

    class XboxOneS2016FirmwareMappings extends GamepadMappings {
        private boolean mLeftTriggerActivated;
        private boolean mRightTriggerActivated;

        XboxOneS2016FirmwareMappings(org.chromium.device.gamepad.GamepadMappings$1 arg1) {
            this();
        }

        private XboxOneS2016FirmwareMappings() {
            super();
            this.mLeftTriggerActivated = false;
            this.mRightTriggerActivated = false;
        }

        public void mapToStandardGamepad(float[] arg7, float[] arg8, float[] arg9, float[] arg10) {
            arg8[0] = arg10[0x60];
            arg8[1] = arg10[97];
            arg8[2] = arg10[98];
            arg8[3] = arg10[99];
            arg8[4] = arg10[100];
            arg8[5] = arg10[101];
            arg8[8] = arg10[102];
            arg8[9] = arg10[103];
            arg8[10] = arg10[104];
            int v0 = 11;
            arg8[v0] = arg10[105];
            if(arg9[v0] != 0f) {
                this.mLeftTriggerActivated = true;
            }

            int v10 = 14;
            if(arg9[v10] != 0f) {
                this.mRightTriggerActivated = true;
            }

            float v3 = 2f;
            float v4 = 1f;
            int v5 = 6;
            arg8[v5] = this.mLeftTriggerActivated ? (arg9[v0] + v4) / v3 : 0f;
            int v1 = 7;
            arg8[v1] = this.mRightTriggerActivated ? (arg9[v10] + v4) / v3 : 0f;
            GamepadMappings.mapHatAxisToDpadButtons(arg8, arg9);
            GamepadMappings.mapXYAxes(arg7, arg9);
            GamepadMappings.mapRXAndRYAxesToRightStick(arg7, arg9);
        }
    }

    @VisibleForTesting static final String AMAZON_FIRE_DEVICE_NAME = "Amazon Fire Game Controller";
    @VisibleForTesting static final String MICROSOFT_XBOX_PAD_DEVICE_NAME = "Microsoft X-Box 360 pad";
    @VisibleForTesting static final String NVIDIA_SHIELD_DEVICE_NAME_PREFIX = "NVIDIA Corporation NVIDIA Controller";
    @VisibleForTesting static final String PS3_SIXAXIS_DEVICE_NAME = "Sony PLAYSTATION(R)3 Controller";
    @VisibleForTesting static final int PS_DUALSHOCK_4_PRODUCT_ID = 0x5C4;
    @VisibleForTesting static final int PS_DUALSHOCK_4_SLIM_PRODUCT_ID = 2508;
    @VisibleForTesting static final int PS_DUALSHOCK_4_USB_RECEIVER_PRODUCT_ID = 0xBA0;
    @VisibleForTesting static final int PS_DUALSHOCK_4_VENDOR_ID = 0x54C;
    @VisibleForTesting static final String SAMSUNG_EI_GP20_DEVICE_NAME = "Samsung Game Pad EI-GP20";
    @VisibleForTesting static final int XBOX_ONE_S_2016_FIRMWARE_PRODUCT_ID = 0x2E0;
    @VisibleForTesting static final int XBOX_ONE_S_2016_FIRMWARE_VENDOR_ID = 0x45E;

    GamepadMappings() {
        super();
    }

    static void access$1000(float[] arg0, float[] arg1) {
        GamepadMappings.mapHatAxisToDpadButtons(arg0, arg1);
    }

    static void access$1100(float[] arg0, float[] arg1) {
        GamepadMappings.mapXYAxes(arg0, arg1);
    }

    static void access$1200(float[] arg0, float[] arg1) {
        GamepadMappings.mapZAndRZAxesToRightStick(arg0, arg1);
    }

    static void access$1300(float[] arg0, float[] arg1) {
        GamepadMappings.mapTriggerAxesToBottomShoulder(arg0, arg1);
    }

    static void access$1400(float[] arg0, float[] arg1) {
        GamepadMappings.mapRXAndRYAxesToRightStick(arg0, arg1);
    }

    static void access$1500(float[] arg0, float[] arg1) {
        GamepadMappings.mapCommonDpadButtons(arg0, arg1);
    }

    static void access$1600(float[] arg0, float[] arg1) {
        GamepadMappings.mapUpperTriggerButtonsToBottomShoulder(arg0, arg1);
    }

    static void access$1700(float[] arg0, float[] arg1) {
        GamepadMappings.mapLowerTriggerButtonsToBottomShoulder(arg0, arg1);
    }

    static void access$500(float[] arg0, float[] arg1) {
        GamepadMappings.mapCommonXYABButtons(arg0, arg1);
    }

    static void access$600(float[] arg0, float[] arg1) {
        GamepadMappings.mapTriggerButtonsToTopShoulder(arg0, arg1);
    }

    static void access$700(float[] arg0, float[] arg1) {
        GamepadMappings.mapCommonThumbstickButtons(arg0, arg1);
    }

    static void access$800(float[] arg0, float[] arg1) {
        GamepadMappings.mapCommonStartSelectMetaButtons(arg0, arg1);
    }

    static void access$900(float[] arg0, float[] arg1) {
        GamepadMappings.mapPedalAxesToBottomShoulder(arg0, arg1);
    }

    public static GamepadMappings getMappings(InputDevice arg2, int[] arg3) {
        GamepadMappings v0 = Build$VERSION.SDK_INT >= 19 ? GamepadMappings.getMappings(arg2.getProductId(), arg2.getVendorId()) : null;
        if(v0 == null) {
            v0 = GamepadMappings.getMappings(arg2.getName());
        }

        if(v0 == null) {
            UnknownGamepadMappings v0_1 = new UnknownGamepadMappings(arg3);
        }

        return v0;
    }

    @TargetApi(value=19) @VisibleForTesting static GamepadMappings getMappings(int arg2, int arg3) {
        if(arg3 == 0x54C && (arg2 == 0x5C4 || arg2 == 2508 || arg2 == 0xBA0)) {
            return new PS4GamepadMappings();
        }

        org.chromium.device.gamepad.GamepadMappings$1 v1 = null;
        if(arg3 == 0x45E && arg2 == 0x2E0) {
            return new XboxOneS2016FirmwareMappings(v1);
        }

        return ((GamepadMappings)v1);
    }

    @VisibleForTesting static GamepadMappings getMappings(String arg2) {
        org.chromium.device.gamepad.GamepadMappings$1 v1 = null;
        if(!arg2.startsWith("NVIDIA Corporation NVIDIA Controller")) {
            if(arg2.equals("Microsoft X-Box 360 pad")) {
            }
            else if(arg2.equals("Sony PLAYSTATION(R)3 Controller")) {
                return new PS3SixAxisGamepadMappings(v1);
            }
            else if(arg2.equals("Samsung Game Pad EI-GP20")) {
                return new SamsungEIGP20GamepadMappings(v1);
            }
            else if(arg2.equals("Amazon Fire Game Controller")) {
                return new AmazonFireGamepadMappings(v1);
            }
            else {
                return ((GamepadMappings)v1);
            }
        }

        return new XboxCompatibleGamepadMappings(v1);
    }

    @VisibleForTesting static GamepadMappings getUnknownGamepadMappings(int[] arg1) {
        return new UnknownGamepadMappings(arg1);
    }

    public boolean isStandard() {
        return 1;
    }

    private static void mapCommonDpadButtons(float[] arg4, float[] arg5) {
        float v0 = arg5[20];
        float v1 = arg5[19];
        float v2 = arg5[21];
        float v5 = arg5[22];
        arg4[13] = v0;
        arg4[12] = v1;
        arg4[14] = v2;
        arg4[15] = v5;
    }

    private static void mapCommonStartSelectMetaButtons(float[] arg3, float[] arg4) {
        float v0 = arg4[108];
        float v1 = arg4[109];
        float v4 = arg4[110];
        arg3[9] = v0;
        arg3[8] = v1;
        arg3[16] = v4;
    }

    private static void mapCommonThumbstickButtons(float[] arg2, float[] arg3) {
        float v0 = arg3[106];
        float v3 = arg3[107];
        arg2[10] = v0;
        arg2[11] = v3;
    }

    private static void mapCommonXYABButtons(float[] arg4, float[] arg5) {
        float v0 = arg5[0x60];
        float v1 = arg5[97];
        float v2 = arg5[99];
        float v5 = arg5[100];
        arg4[0] = v0;
        arg4[1] = v1;
        arg4[2] = v2;
        arg4[3] = v5;
    }

    private static void mapHatAxisToDpadButtons(float[] arg4, float[] arg5) {
        float v1 = arg5[15];
        float v5 = arg5[16];
        arg4[14] = GamepadMappings.negativeAxisValueAsButton(v1);
        arg4[15] = GamepadMappings.positiveAxisValueAsButton(v1);
        arg4[12] = GamepadMappings.negativeAxisValueAsButton(v5);
        arg4[13] = GamepadMappings.positiveAxisValueAsButton(v5);
    }

    private static void mapLowerTriggerButtonsToBottomShoulder(float[] arg2, float[] arg3) {
        float v0 = arg3[104];
        float v3 = arg3[105];
        arg2[6] = v0;
        arg2[7] = v3;
    }

    private static void mapPedalAxesToBottomShoulder(float[] arg2, float[] arg3) {
        float v0 = arg3[23];
        float v3 = arg3[22];
        arg2[6] = v0;
        arg2[7] = v3;
    }

    private static void mapRXAndRYAxesToRightStick(float[] arg2, float[] arg3) {
        arg2[2] = arg3[12];
        arg2[3] = arg3[13];
    }

    public abstract void mapToStandardGamepad(float[] arg1, float[] arg2, float[] arg3, float[] arg4);

    private static void mapTriggerAxesToBottomShoulder(float[] arg2, float[] arg3) {
        float v0 = arg3[17];
        float v3 = arg3[18];
        arg2[6] = v0;
        arg2[7] = v3;
    }

    private static void mapTriggerButtonsToTopShoulder(float[] arg2, float[] arg3) {
        float v0 = arg3[102];
        float v3 = arg3[103];
        arg2[4] = v0;
        arg2[5] = v3;
    }

    private static void mapUpperTriggerButtonsToBottomShoulder(float[] arg2, float[] arg3) {
        float v0 = arg3[102];
        float v3 = arg3[103];
        arg2[6] = v0;
        arg2[7] = v3;
    }

    private static void mapXYAxes(float[] arg2, float[] arg3) {
        arg2[0] = arg3[0];
        arg2[1] = arg3[1];
    }

    private static void mapZAndRZAxesToRightStick(float[] arg2, float[] arg3) {
        arg2[2] = arg3[11];
        arg2[3] = arg3[14];
    }

    @VisibleForTesting static float negativeAxisValueAsButton(float arg1) {
        return arg1 < -0.5f ? 1f : 0f;
    }

    @VisibleForTesting static float positiveAxisValueAsButton(float arg1) {
        return arg1 > 0.5f ? 1f : 0f;
    }
}

