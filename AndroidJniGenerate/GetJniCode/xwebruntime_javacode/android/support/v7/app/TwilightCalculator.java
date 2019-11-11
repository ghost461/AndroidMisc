package android.support.v7.app;

class TwilightCalculator {
    private static final float ALTIDUTE_CORRECTION_CIVIL_TWILIGHT = -0.10472f;
    private static final float C1 = 0.03342f;
    private static final float C2 = 0.000349f;
    private static final float C3 = 0.000005f;
    public static final int DAY = 0;
    private static final float DEGREES_TO_RADIANS = 0.017453f;
    private static final float J0 = 0.0009f;
    public static final int NIGHT = 1;
    private static final float OBLIQUITY = 0.40928f;
    private static final long UTC_2000 = 946728000000L;
    private static TwilightCalculator sInstance;
    public int state;
    public long sunrise;
    public long sunset;

    TwilightCalculator() {
        super();
    }

    public void calculateTwilight(long arg18, double arg20, double arg22) {
        TwilightCalculator v0 = this;
        long v2 = 946728000000L;
        float v4 = (((float)(arg18 - v2))) / 86400000f;
        float v5 = 0.017202f * v4 + 6.24006f;
        double v6 = ((double)v5);
        double v8 = Math.sin(v6) * 0.03342 + v6 + Math.sin(((double)(2f * v5))) * 0.000349 + Math.sin(((double)(v5 * 3f))) * 0.000005 + 1.796593 + 3.141593;
        double v10 = -arg22 / 360;
        double v4_1 = (((double)((((float)Math.round((((double)(v4 - 0.0009f))) - v10))) + 0.0009f))) + v10 + Math.sin(v6) * 0.0053 + Math.sin(2 * v8) * -0.0069;
        v6 = Math.asin(Math.sin(v8) * Math.sin(0.40928));
        v8 = 0.017453 * arg20;
        v10 = (Math.sin(-0.10472) - Math.sin(v8) * Math.sin(v6)) / (Math.cos(v8) * Math.cos(v6));
        long v12 = -1;
        if(Double.compare(v10, 1) >= 0) {
            v0.state = 1;
            v0.sunset = v12;
            v0.sunrise = v12;
            return;
        }

        if(Double.compare(v10, -1) <= 0) {
            v0.state = 0;
            v0.sunset = v12;
            v0.sunrise = v12;
            return;
        }

        v8 = ((double)(((float)(Math.acos(v10) / 6.283185))));
        v0.sunset = Math.round((v4_1 + v8) * 86400000) + v2;
        v0.sunrise = Math.round((v4_1 - v8) * 86400000) + v2;
        v0.state = v0.sunrise >= arg18 || v0.sunset <= arg18 ? 1 : 0;
    }

    static TwilightCalculator getInstance() {
        if(TwilightCalculator.sInstance == null) {
            TwilightCalculator.sInstance = new TwilightCalculator();
        }

        return TwilightCalculator.sInstance;
    }
}

