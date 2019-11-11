package org.chromium.media;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value=RetentionPolicy.SOURCE) @public interface CipherMode {
    public static final int AES_CBC = 2;
    public static final int AES_CTR = 1;
    public static final int MAX = 2;
    public static final int UNENCRYPTED;

}

