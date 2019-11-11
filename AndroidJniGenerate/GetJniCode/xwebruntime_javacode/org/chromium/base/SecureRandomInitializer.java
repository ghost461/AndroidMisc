package org.chromium.base;

import android.annotation.SuppressLint;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.SecureRandom;

@SuppressLint(value={"SecureRandom"}) public class SecureRandomInitializer {
    private static final int NUM_RANDOM_BYTES = 16;

    public SecureRandomInitializer() {
        super();
    }

    public static void initialize(SecureRandom arg5) throws IOException {
        FileInputStream v0 = new FileInputStream("/dev/urandom");
        int v1 = 16;
        Throwable v2 = null;
        try {
            byte[] v1_1 = new byte[v1];
            if(v0.read(v1_1) != v1_1.length) {
                throw new IOException("Failed to get enough random data.");
            }

            arg5.setSeed(v1_1);
            if(v0 == null) {
                return;
            }
        }
        catch(Throwable v5) {
        }
        catch(Throwable v5) {
            v2 = v5;
            try {
                throw v2;
            }
            catch(Throwable v5) {
                if(v0 != null) {
                    if(v2 != null) {
                        try {
                            v0.close();
                        }
                        catch(Throwable v0_1) {
                            ThrowableExtension.addSuppressed(v2, v0_1);
                        }
                    }
                    else {
                        v0.close();
                    }

                    goto label_30;
                }
                else {
                label_30:
                    throw v5;
                }

                return;
            }
        }

        v0.close();
    }
}

