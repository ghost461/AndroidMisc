package android.support.v4.graphics;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.os.Process;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.util.Log;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel$MapMode;
import java.nio.channels.FileChannel;

@RestrictTo(value={Scope.LIBRARY_GROUP}) public class TypefaceCompatUtil {
    private static final String CACHE_FILE_PREFIX = ".font";
    private static final String TAG = "TypefaceCompatUtil";

    private TypefaceCompatUtil() {
        super();
    }

    public static void closeQuietly(Closeable arg0) {
        if(arg0 != null) {
            try {
                arg0.close();
                return;
            }
            catch(IOException ) {
                return;
            }
        }
    }

    @RequiresApi(value=19) public static ByteBuffer copyToDirectBuffer(Context arg1, Resources arg2, int arg3) {
        ByteBuffer v2_1;
        File v1 = TypefaceCompatUtil.getTempFile(arg1);
        ByteBuffer v0 = null;
        if(v1 == null) {
            return v0;
        }

        try {
            if(TypefaceCompatUtil.copyToFile(v1, arg2, arg3)) {
                goto label_8;
            }
        }
        catch(Throwable v2) {
            goto label_12;
        }

        v1.delete();
        return v0;
        try {
        label_8:
            v2_1 = TypefaceCompatUtil.mmap(v1);
        }
        catch(Throwable v2) {
        label_12:
            v1.delete();
            throw v2;
        }

        v1.delete();
        return v2_1;
    }

    public static boolean copyToFile(File arg0, Resources arg1, int arg2) {
        boolean v0_1;
        InputStream v1;
        try {
            v1 = arg1.openRawResource(arg2);
        }
        catch(Throwable v0) {
            Closeable v1_1 = null;
            goto label_8;
        }

        try {
            v0_1 = TypefaceCompatUtil.copyToFile(arg0, v1);
            goto label_2;
        }
        catch(Throwable v0) {
        }

    label_8:
        TypefaceCompatUtil.closeQuietly(((Closeable)v1));
        throw v0;
    label_2:
        TypefaceCompatUtil.closeQuietly(((Closeable)v1));
        return v0_1;
    }

    public static boolean copyToFile(File arg4, InputStream arg5) {
        FileOutputStream v1_2;
        int v4_2;
        FileOutputStream v2;
        Closeable v1 = null;
        try {
            v2 = new FileOutputStream(arg4, false);
            v4_2 = 0x400;
            goto label_5;
        }
        catch(Throwable v4) {
        }
        catch(IOException v4_1) {
            goto label_23;
            try {
            label_5:
                byte[] v4_3 = new byte[v4_2];
                while(true) {
                    int v1_1 = arg5.read(v4_3);
                    if(v1_1 == -1) {
                        break;
                    }

                    v2.write(v4_3, 0, v1_1);
                }
            }
            catch(IOException v4_1) {
                goto label_18;
            }
            catch(Throwable v4) {
                goto label_15;
            }

            TypefaceCompatUtil.closeQuietly(((Closeable)v2));
            return 1;
        label_18:
            v1_2 = v2;
            try {
            label_23:
                Log.e("TypefaceCompatUtil", "Error copying resource contents to temp file: " + v4_1.getMessage());
            }
            catch(Throwable v4) {
                goto label_34;
            }
        }

        TypefaceCompatUtil.closeQuietly(((Closeable)v1_2));
        return 0;
    label_15:
        v1_2 = v2;
    label_34:
        TypefaceCompatUtil.closeQuietly(((Closeable)v1_2));
        throw v4;
    }

    public static File getTempFile(Context arg5) {
        String v0_1 = ".font" + Process.myPid() + "-" + Process.myTid() + "-";
        int v1;
        for(v1 = 0; v1 < 100; ++v1) {
            File v3 = arg5.getCacheDir();
            StringBuilder v4 = new StringBuilder();
            v4.append(v0_1);
            v4.append(v1);
            File v2 = new File(v3, v4.toString());
            try {
                if(!v2.createNewFile()) {
                    goto label_27;
                }
            }
            catch(IOException ) {
                goto label_27;
            }

            return v2;
        label_27:
        }

        return null;
    }

    @RequiresApi(value=19) private static ByteBuffer mmap(File arg9) {
        MappedByteBuffer v9_1;
        FileInputStream v1;
        ByteBuffer v0 = null;
        try {
            v1 = new FileInputStream(arg9);
        }
        catch(IOException ) {
            return v0;
        }

        try {
            FileChannel v2 = v1.getChannel();
            v9_1 = v2.map(FileChannel$MapMode.READ_ONLY, 0, v2.size());
            if(v1 == null) {
                goto label_10;
            }

            goto label_9;
        }
        catch(Throwable v9) {
            v2_1 = ((Throwable)v0);
        }
        catch(Throwable v9) {
            try {
                throw v9;
            }
            catch(Throwable v2_1) {
                Throwable v8 = v2_1;
                v2_1 = v9;
                v9 = v8;
            }
        }

        if(v1 == null) {
            goto label_28;
        }
        else if(v2_1 != null) {
            try {
                v1.close();
                goto label_28;
            }
            catch(IOException ) {
            }
            catch(Throwable v1_1) {
                try {
                    ThrowableExtension.addSuppressed(v2_1, v1_1);
                    goto label_28;
                label_27:
                    v1.close();
                label_28:
                    throw v9;
                label_9:
                    v1.close();
                }
                catch(IOException ) {
                    return v0;
                }
            }
        }
        else {
            goto label_27;
        }

    label_10:
        return ((ByteBuffer)v9_1);
    }

    @RequiresApi(value=19) public static ByteBuffer mmap(Context arg8, CancellationSignal arg9, Uri arg10) {
        Throwable v7;
        MappedByteBuffer v10_1;
        FileInputStream v9_1;
        ParcelFileDescriptor v8_1;
        ContentResolver v8 = arg8.getContentResolver();
        ByteBuffer v0 = null;
        try {
            v8_1 = v8.openFileDescriptor(arg10, "r", arg9);
        }
        catch(IOException ) {
            return v0;
        }

        try {
            v9_1 = new FileInputStream(v8_1.getFileDescriptor());
        }
        catch(Throwable v9) {
            goto label_36;
        }
        catch(Throwable v9) {
            goto label_39;
        }

        try {
            FileChannel v1 = v9_1.getChannel();
            v10_1 = v1.map(FileChannel$MapMode.READ_ONLY, 0, v1.size());
            if(v9_1 == null) {
                goto label_14;
            }

            goto label_13;
        }
        catch(Throwable v10) {
            v1_1 = ((Throwable)v0);
        }
        catch(Throwable v10) {
            try {
                throw v10;
            }
            catch(Throwable v1_1) {
                v7 = v1_1;
                v1_1 = v10;
                v10 = v7;
            }
        }

        if(v9_1 == null) {
            goto label_34;
        label_44:
            if(v8_1 == null) {
                goto label_52;
            }
            else if(v10 != null) {
                try {
                    v8_1.close();
                    goto label_52;
                }
                catch(IOException ) {
                }
                catch(Throwable v8_2) {
                    try {
                        ThrowableExtension.addSuppressed(v10, v8_2);
                        goto label_52;
                    label_51:
                        v8_1.close();
                    label_52:
                        throw v9;
                    }
                    catch(IOException ) {
                        return v0;
                    }

                label_14:
                    if(v8_1 != null) {
                        try {
                            v8_1.close();
                            goto label_16;
                        }
                        catch(IOException ) {
                            return v0;
                        }
                    }

                    goto label_16;
                }
            }
            else {
                goto label_51;
            }
        }
        else if(v1_1 != null) {
            try {
                v9_1.close();
                goto label_34;
            }
            catch(Throwable v9) {
            }
            catch(Throwable v9) {
                try {
                    ThrowableExtension.addSuppressed(v1_1, v9);
                    goto label_34;
                label_33:
                    v9_1.close();
                label_34:
                    throw v10;
                label_13:
                    v9_1.close();
                    goto label_14;
                }
                catch(Throwable v9) {
                label_36:
                    v10 = ((Throwable)v0);
                    goto label_44;
                }
                catch(Throwable v9) {
                    try {
                    label_39:
                        throw v9;
                    }
                    catch(Throwable v10) {
                        v7 = v10;
                        v10 = v9;
                        v9 = v7;
                        goto label_44;
                    }
                }
            }
        }
        else {
            goto label_33;
        }

        goto label_14;
    label_16:
        return ((ByteBuffer)v10_1);
    }
}

