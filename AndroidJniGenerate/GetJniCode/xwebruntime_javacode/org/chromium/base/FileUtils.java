package org.chromium.base;

import android.content.Context;
import android.net.Uri;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class FileUtils {
    private static final String TAG = "FileUtils";

    public FileUtils() {
        super();
    }

    public static void batchDeleteFiles(List arg2) {
        ThreadUtils.assertOnBackgroundThread();
        Iterator v2 = arg2.iterator();
        while(v2.hasNext()) {
            Object v0 = v2.next();
            if(!((File)v0).exists()) {
                continue;
            }

            FileUtils.recursivelyDeleteFile(((File)v0));
        }
    }

    public static boolean extractAsset(Context arg3, String arg4, File arg5) {
        int v5;
        BufferedOutputStream v4;
        InputStream v3;
        BufferedOutputStream v1 = null;
        try {
            v3 = arg3.getAssets().open(arg4);
        }
        catch(IOException ) {
            v3 = ((InputStream)v1);
            goto label_22;
        }

        try {
            v4 = new BufferedOutputStream(new FileOutputStream(arg5));
            v5 = 0x2000;
        }
        catch(IOException ) {
            goto label_22;
        }

        try {
            byte[] v5_1 = new byte[v5];
            while(true) {
                int v1_1 = v3.read(v5_1);
                if(v1_1 == -1) {
                    break;
                }

                ((OutputStream)v4).write(v5_1, 0, v1_1);
            }

            v3.close();
            ((OutputStream)v4).close();
            v3 = null;
            return 1;
        }
        catch(IOException ) {
            v1 = v4;
        }

    label_22:
        if(v3 != null) {
            try {
                v3.close();
                goto label_24;
            }
            catch(IOException ) {
            label_24:
                if(v1 != null) {
                    try {
                        ((OutputStream)v1).close();
                        return 0;
                    }
                    catch(IOException ) {
                        return 0;
                    }
                }

                return 0;
            }
        }

        goto label_24;
    }

    public static String getExtension(String arg2) {
        int v0 = arg2.lastIndexOf(46);
        if(v0 == -1) {
            return "";
        }

        return arg2.substring(v0 + 1).toLowerCase(Locale.US);
    }

    public static Uri getUriForFile(File arg4) {
        Uri v0_1;
        try {
            v0_1 = ContentUriUtils.getContentUriFromFile(arg4);
        }
        catch(IllegalArgumentException v0) {
            Log.e("FileUtils", "Could not create content uri: " + v0, new Object[0]);
            v0_1 = null;
        }

        if(v0_1 == null) {
            v0_1 = Uri.fromFile(arg4);
        }

        return v0_1;
    }

    public static void recursivelyDeleteFile(File arg5) {
        ThreadUtils.assertOnBackgroundThread();
        if(arg5.isDirectory()) {
            File[] v0 = arg5.listFiles();
            if(v0 != null) {
                int v2 = v0.length;
                int v3;
                for(v3 = 0; v3 < v2; ++v3) {
                    FileUtils.recursivelyDeleteFile(v0[v3]);
                }
            }
        }

        if(!arg5.delete()) {
            Log.e("FileUtils", "Failed to delete: " + arg5, new Object[0]);
        }
    }
}

