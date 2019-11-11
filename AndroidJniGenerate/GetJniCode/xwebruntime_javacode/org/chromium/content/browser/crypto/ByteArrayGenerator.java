package org.chromium.content.browser.crypto;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;

public class ByteArrayGenerator {
    public ByteArrayGenerator() {
        super();
    }

    public byte[] getBytes(int arg4) throws IOException, GeneralSecurityException {
        byte[] v4_1;
        FileInputStream v1;
        FileInputStream v0 = null;
        try {
            v1 = new FileInputStream("/dev/urandom");
        }
        catch(Throwable v4) {
            v1 = v0;
            goto label_19;
        }

        try {
            v4_1 = new byte[arg4];
            if(v4_1.length == v1.read(v4_1)) {
                goto label_12;
            }

            throw new GeneralSecurityException("Not enough random data available");
        }
        catch(Throwable v4) {
        }

    label_19:
        if(v1 != null) {
            v1.close();
        }

        throw v4;
    label_12:
        if(v1 != null) {
            v1.close();
        }

        return v4_1;
    }
}

