package org.chromium.net;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value=RetentionPolicy.SOURCE) @public interface PrivateKeyType {
    public static final int ECDSA = 2;
    public static final int INVALID = 0xFF;
    public static final int RSA;

}

