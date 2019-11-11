package android.support.v4.graphics;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Typeface;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.v4.provider.FontsContractCompat$FontInfo;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RequiresApi(value=21) @RestrictTo(value={Scope.LIBRARY_GROUP}) class TypefaceCompatApi21Impl extends TypefaceCompatBaseImpl {
    private static final String TAG = "TypefaceCompatApi21Impl";

    TypefaceCompatApi21Impl() {
        super();
    }

    public Typeface createFromFontInfo(Context arg5, CancellationSignal arg6, @NonNull FontInfo[] arg7, int arg8) {
        Throwable v3;
        FileInputStream v7_2;
        Typeface v5_1;
        ParcelFileDescriptor v6;
        Typeface v1 = null;
        if(arg7.length < 1) {
            return v1;
        }

        FontInfo v7 = this.findBestInfo(arg7, arg8);
        ContentResolver v8 = arg5.getContentResolver();
        try {
            v6 = v8.openFileDescriptor(v7.getUri(), "r", arg6);
        }
        catch(IOException ) {
            return v1;
        }

        try {
            File v7_1 = this.getFile(v6);
            if(v7_1 != null) {
                if(!v7_1.canRead()) {
                }
                else {
                    v5_1 = Typeface.createFromFile(v7_1);
                    if(v6 != null) {
                        goto label_17;
                    }

                    return v5_1;
                }
            }

            goto label_19;
        }
        catch(Throwable v5) {
            goto label_50;
        }
        catch(Throwable v5) {
            goto label_47;
        }

        try {
        label_17:
            v6.close();
        }
        catch(IOException ) {
            return v1;
        }

        return v5_1;
        try {
        label_19:
            v7_2 = new FileInputStream(v6.getFileDescriptor());
        }
        catch(Throwable v5) {
            goto label_50;
        }
        catch(Throwable v5) {
            goto label_47;
        }

        try {
            v5_1 = super.createFromInputStream(arg5, ((InputStream)v7_2));
            if(v7_2 == null) {
                goto label_25;
            }

            goto label_24;
        }
        catch(Throwable v5) {
            v8_1 = ((Throwable)v1);
        }
        catch(Throwable v5) {
            try {
                throw v5;
            }
            catch(Throwable v8_1) {
                v3 = v8_1;
                v8_1 = v5;
                v5 = v3;
            }
        }

        if(v7_2 == null) {
            goto label_45;
        label_55:
            if(v6 == null) {
                goto label_63;
            }
            else if(v7_3 != null) {
                try {
                    v6.close();
                    goto label_63;
                }
                catch(IOException ) {
                }
                catch(Throwable v6_1) {
                    try {
                        ThrowableExtension.addSuppressed(v7_3, v6_1);
                        goto label_63;
                    label_62:
                        v6.close();
                    label_63:
                        throw v5;
                    }
                    catch(IOException ) {
                        return v1;
                    }

                label_25:
                    if(v6 != null) {
                        try {
                            v6.close();
                            return v5_1;
                        }
                        catch(IOException ) {
                            return v1;
                        }
                    }

                    return v5_1;
                }
            }
            else {
                goto label_62;
            }
        }
        else if(v8_1 != null) {
            try {
                v7_2.close();
                goto label_45;
            }
            catch(Throwable v5) {
            }
            catch(Throwable v7_3) {
                try {
                    ThrowableExtension.addSuppressed(v8_1, v7_3);
                    goto label_45;
                label_44:
                    v7_2.close();
                label_45:
                    throw v5;
                label_24:
                    v7_2.close();
                    goto label_25;
                }
                catch(Throwable v5) {
                label_47:
                    v7_3 = ((Throwable)v1);
                    goto label_55;
                }
                catch(Throwable v5) {
                    try {
                    label_50:
                        throw v5;
                    }
                    catch(Throwable v7_3) {
                        v3 = v7_3;
                        v7_3 = v5;
                        v5 = v3;
                        goto label_55;
                    }
                }
            }
        }
        else {
            goto label_44;
        }

        goto label_25;
        return v5_1;
    }

    private File getFile(ParcelFileDescriptor arg4) {
        File v0 = null;
        try {
            StringBuilder v1 = new StringBuilder();
            v1.append("/proc/self/fd/");
            v1.append(arg4.getFd());
            String v4 = Os.readlink(v1.toString());
            if(!OsConstants.S_ISREG(Os.stat(v4).st_mode)) {
                return v0;
            }

            return new File(v4);
        }
        catch(ErrnoException ) {
            return v0;
        }

        return v0;
    }
}

