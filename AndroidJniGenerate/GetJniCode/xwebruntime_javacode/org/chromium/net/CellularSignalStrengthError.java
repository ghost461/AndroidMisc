package org.chromium.net;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value=RetentionPolicy.SOURCE) @public interface CellularSignalStrengthError {
    public static final int ERROR_NOT_SUPPORTED = 0x80000000;

}

