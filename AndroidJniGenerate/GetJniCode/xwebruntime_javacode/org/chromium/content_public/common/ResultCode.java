package org.chromium.content_public.common;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value=RetentionPolicy.SOURCE) @public interface ResultCode {
    public static final int GPU_DEAD_ON_ARRIVAL = 4;
    public static final int HUNG = 2;
    public static final int KILLED = 1;
    public static final int KILLED_BAD_MESSAGE = 3;
    public static final int LAST_CODE = 5;
    public static final int NORMAL_EXIT;

}

