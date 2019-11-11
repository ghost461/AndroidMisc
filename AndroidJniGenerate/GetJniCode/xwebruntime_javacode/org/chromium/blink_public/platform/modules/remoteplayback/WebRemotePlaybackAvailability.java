package org.chromium.blink_public.platform.modules.remoteplayback;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value=RetentionPolicy.SOURCE) @public interface WebRemotePlaybackAvailability {
    public static final int DEVICE_AVAILABLE = 3;
    public static final int DEVICE_NOT_AVAILABLE = 1;
    public static final int LAST = 3;
    public static final int SOURCE_NOT_COMPATIBLE = 2;
    public static final int UNKNOWN;

}

