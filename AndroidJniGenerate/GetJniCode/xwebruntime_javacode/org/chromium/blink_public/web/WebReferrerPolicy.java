package org.chromium.blink_public.web;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value=RetentionPolicy.SOURCE) @public interface WebReferrerPolicy {
    public static final int ALWAYS = 0;
    public static final int DEFAULT = 1;
    public static final int LAST = 8;
    public static final int NEVER = 3;
    public static final int NO_REFERRER_WHEN_DOWNGRADE = 2;
    public static final int NO_REFERRER_WHEN_DOWNGRADE_ORIGIN_WHEN_CROSS_ORIGIN = 6;
    public static final int ORIGIN = 4;
    public static final int ORIGIN_WHEN_CROSS_ORIGIN = 5;
    public static final int SAME_ORIGIN = 7;
    public static final int STRICT_ORIGIN = 8;

}

