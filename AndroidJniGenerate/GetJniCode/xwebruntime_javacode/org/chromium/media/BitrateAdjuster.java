package org.chromium.media;

abstract enum BitrateAdjuster {
    enum org.chromium.media.BitrateAdjuster$1 extends BitrateAdjuster {
        private static final int MAXIMUM_INITIAL_FPS = 30;

        org.chromium.media.BitrateAdjuster$1(String arg2, int arg3) {
            super(arg2, arg3, null);
        }

        public int getInitialFrameRate(int arg2) {
            return Math.min(arg2, 30);
        }

        public int getTargetBitrate(int arg1, int arg2) {
            return arg1;
        }
    }

    enum org.chromium.media.BitrateAdjuster$2 extends BitrateAdjuster {
        private static final int BITRATE_ADJUSTMENT_FPS = 30;

        org.chromium.media.BitrateAdjuster$2(String arg2, int arg3) {
            super(arg2, arg3, null);
        }

        public int getInitialFrameRate(int arg1) {
            return 30;
        }

        public int getTargetBitrate(int arg1, int arg2) {
            if(arg2 == 0) {
                return arg1;
            }

            return arg1 * 30 / arg2;
        }
    }

    public static final enum BitrateAdjuster FRAMERATE_ADJUSTMENT;
    public static final enum BitrateAdjuster NO_ADJUSTMENT;

    static {
        BitrateAdjuster.NO_ADJUSTMENT = new org.chromium.media.BitrateAdjuster$1("NO_ADJUSTMENT", 0);
        BitrateAdjuster.FRAMERATE_ADJUSTMENT = new org.chromium.media.BitrateAdjuster$2("FRAMERATE_ADJUSTMENT", 1);
        BitrateAdjuster.$VALUES = new BitrateAdjuster[]{BitrateAdjuster.NO_ADJUSTMENT, BitrateAdjuster.FRAMERATE_ADJUSTMENT};
    }

    private BitrateAdjuster(String arg1, int arg2) {
        super(arg1, arg2);
    }

    BitrateAdjuster(String arg1, int arg2, org.chromium.media.BitrateAdjuster$1 arg3) {
        this(arg1, arg2);
    }

    public abstract int getInitialFrameRate(int arg1);

    public abstract int getTargetBitrate(int arg1, int arg2);

    public static BitrateAdjuster valueOf(String arg1) {
        return Enum.valueOf(BitrateAdjuster.class, arg1);
    }

    public static BitrateAdjuster[] values() {
        return BitrateAdjuster.$VALUES.clone();
    }
}

