package org.chromium.media;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value=RetentionPolicy.SOURCE) @public interface MediaCodecStatus {
    public static final int ERROR = 5;
    public static final int NO_KEY = 4;
    public static final int OK = 0;
    public static final int OUTPUT_BUFFERS_CHANGED = 2;
    public static final int OUTPUT_FORMAT_CHANGED = 3;
    public static final int TRY_AGAIN_LATER = 1;

}

