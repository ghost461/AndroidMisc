package org.chromium.base;

import android.content.res.AssetFileDescriptor;
import android.util.Log;
import java.io.IOException;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="base::android") public class ApkAssets {
    private static final String LOGTAG = "ApkAssets";

    public ApkAssets() {
        super();
    }

    @CalledByNative public static long[] open(String arg7) {
        long[] v7_1;
        long[] v1_2;
        IOException v1_1;
        AssetFileDescriptor v2_1;
        int v0 = 3;
        AssetFileDescriptor v1 = null;
        try {
            v2_1 = ContextUtils.getApplicationContext().getAssets().openNonAssetFd(arg7);
        }
        catch(Throwable v7) {
            v2_1 = v1;
            goto label_62;
        }
        catch(IOException v2) {
            IOException v6 = v2;
            v2_1 = v1;
            v1_1 = v6;
            goto label_34;
        }

        try {
            v1_2 = new long[v0];
            v1_2[0] = ((long)v2_1.getParcelFileDescriptor().detachFd());
            v1_2[1] = v2_1.getStartOffset();
            v1_2[2] = v2_1.getLength();
            if(v2_1 == null) {
                return v1_2;
            }

            goto label_18;
        }
        catch(Throwable v7) {
        }
        catch(IOException v1_1) {
            try {
            label_34:
                if(!v1_1.getMessage().equals("") && !v1_1.getMessage().equals(arg7)) {
                    Log.e("ApkAssets", "Error while loading asset " + arg7 + ": " + v1_1);
                }

                v7_1 = new long[v0];
                if(v2_1 == null) {
                    return v7_1;
                }
            }
            catch(Throwable v7) {
                goto label_62;
            }

            try {
                v2_1.close();
            }
            catch(IOException v0_1) {
                Log.e("ApkAssets", "Unable to close AssetFileDescriptor", ((Throwable)v0_1));
            }

            return v7_1;
        }

    label_62:
        if(v2_1 != null) {
            try {
                v2_1.close();
            }
            catch(IOException v0_1) {
                Log.e("ApkAssets", "Unable to close AssetFileDescriptor", ((Throwable)v0_1));
            }
        }

        throw v7;
        try {
        label_18:
            v2_1.close();
        }
        catch(IOException v7_2) {
            Log.e("ApkAssets", "Unable to close AssetFileDescriptor", ((Throwable)v7_2));
        }

        return v1_2;
    }
}

