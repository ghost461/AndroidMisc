package org.chromium.base;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.DocumentsContract;
import android.util.Log;
import android.webkit.MimeTypeMap;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.chromium.base.annotations.CalledByNative;

public abstract class ContentUriUtils {
    public interface FileProviderUtil {
        Uri getContentUriFromFile(File arg1);
    }

    private static final String TAG = "ContentUriUtils";
    private static FileProviderUtil sFileProviderUtil;
    private static final Object sLock;

    static {
        ContentUriUtils.sLock = new Object();
    }

    private ContentUriUtils() {
        super();
    }

    @CalledByNative public static boolean contentUriExists(String arg1) {
        AssetFileDescriptor v1_1;
        try {
            v1_1 = ContentUriUtils.getAssetFileDescriptor(arg1);
            if(v1_1 == null) {
                goto label_4;
            }
        }
        catch(Throwable v1) {
            throw v1;
        }

        boolean v0 = true;
        goto label_5;
    label_4:
        v0 = false;
    label_5:
        if(v1_1 != null) {
            try {
                v1_1.close();
                return v0;
            }
            catch(IOException ) {
                return v0;
            }
        }

        return v0;
    }

    private static AssetFileDescriptor getAssetFileDescriptor(String arg10) {
        AssetFileDescriptor v4;
        ContentResolver v0 = ContextUtils.getApplicationContext().getContentResolver();
        Uri v1 = Uri.parse(arg10);
        Bundle v2 = null;
        try {
            if(ContentUriUtils.isVirtualDocument(v1)) {
                String[] v3 = v0.getStreamTypes(v1, "*/*");
                if(v3 == null) {
                    goto label_63;
                }

                if(v3.length <= 0) {
                    goto label_63;
                }

                AssetFileDescriptor v0_4 = v0.openTypedAssetFileDescriptor(v1, v3[0], v2);
                if(v0_4 != null && v0_4.getStartOffset() != 0) {
                    try {
                        v0_4.close();
                        goto label_19;
                    }
                    catch(IOException ) {
                    label_19:
                        throw new SecurityException("Cannot open files with non-zero offset type.");
                    }
                }

                return v0_4;
            }

            ParcelFileDescriptor v5 = v0.openFileDescriptor(v1, "r");
            if(v5 == null) {
                goto label_63;
            }

            v4 = new AssetFileDescriptor(v5, 0, -1);
        }
        catch(Exception v0_1) {
            Log.w("ContentUriUtils", "Unknown content uri: " + arg10, ((Throwable)v0_1));
            goto label_63;
        }
        catch(SecurityException v0_2) {
            Log.w("ContentUriUtils", "Cannot open content uri: " + arg10, ((Throwable)v0_2));
            goto label_63;
        }
        catch(FileNotFoundException v0_3) {
            Log.w("ContentUriUtils", "Cannot find content uri: " + arg10, ((Throwable)v0_3));
            goto label_63;
        }

        return v4;
    label_63:
        return ((AssetFileDescriptor)v2);
    }

    public static Uri getContentUriFromFile(File arg2) {
        Object v0 = ContentUriUtils.sLock;
        __monitor_enter(v0);
        try {
            if(ContentUriUtils.sFileProviderUtil != null) {
                __monitor_exit(v0);
                return ContentUriUtils.sFileProviderUtil.getContentUriFromFile(arg2);
            }

            __monitor_exit(v0);
            return null;
        label_12:
            __monitor_exit(v0);
        }
        catch(Throwable v2) {
            goto label_12;
        }

        throw v2;
    }

    public static String getDisplayName(Uri arg6, Context arg7, String arg8) {
        String v6_1;
        int v8;
        Throwable v1_1;
        Cursor v0_1;
        if(arg6 == null) {
            return "";
        }

        ContentResolver v7 = arg7.getContentResolver();
        String[] v2 = null;
        String v3 = null;
        String[] v4 = null;
        String v5 = null;
        ContentResolver v0 = v7;
        Uri v1 = arg6;
        try {
            v0_1 = v0.query(v1, v2, v3, v4, v5);
            v1_1 = null;
            if(v0_1 != null) {
                goto label_13;
            }

            goto label_61;
        }
        catch(NullPointerException ) {
            return "";
        }

        try {
        label_13:
            if(v0_1.getCount() >= 1) {
                v0_1.moveToFirst();
                v8 = v0_1.getColumnIndex(arg8);
                if(v8 == -1) {
                    v6_1 = "";
                    if(v0_1 != null) {
                        goto label_22;
                    }

                    return v6_1;
                }
                else {
                    goto label_24;
                }
            }

            goto label_61;
        }
        catch(Throwable v6) {
            goto label_50;
        }
        catch(Throwable v6) {
            goto label_52;
        }

        try {
        label_22:
            v0_1.close();
        }
        catch(NullPointerException ) {
            return "";
        }

        return v6_1;
        try {
        label_24:
            arg8 = v0_1.getString(v8);
            if(ContentUriUtils.hasVirtualFlag(v0_1)) {
                String[] v6_2 = v7.getStreamTypes(arg6, "*/*");
                if(v6_2 != null && v6_2.length > 0) {
                    v6_1 = MimeTypeMap.getSingleton().getExtensionFromMimeType(v6_2[0]);
                    if(v6_1 != null) {
                        arg8 = arg8 + "." + v6_1;
                    }
                }
            }

            goto label_44;
        }
        catch(Throwable v6) {
        }
        catch(Throwable v6) {
        label_50:
            v1_1 = v6;
            try {
                throw v1_1;
            }
            catch(Throwable v6) {
            label_52:
                if(v0_1 == null) {
                    goto label_60;
                }
                else if(v1_1 != null) {
                    try {
                        v0_1.close();
                        goto label_60;
                    }
                    catch(NullPointerException ) {
                    }
                    catch(Throwable v7_2) {
                        try {
                            ThrowableExtension.addSuppressed(v1_1, v7_2);
                            goto label_60;
                        label_59:
                            v0_1.close();
                        label_60:
                            throw v6;
                        }
                        catch(NullPointerException ) {
                            return "";
                        }

                    label_44:
                        if(v0_1 != null) {
                            try {
                                v0_1.close();
                            }
                            catch(NullPointerException ) {
                                return "";
                            }
                        }

                        return arg8;
                        try {
                        label_61:
                            if(v0_1 != null) {
                                v0_1.close();
                            }

                            return "";
                        }
                        catch(NullPointerException ) {
                            return "";
                        }
                    }
                }
                else {
                    goto label_59;
                }

                goto label_44;
            }
        }

        return "";
    }

    @CalledByNative public static String getMimeType(String arg2) {
        ContentResolver v0 = ContextUtils.getApplicationContext().getContentResolver();
        Uri v2 = Uri.parse(arg2);
        if(ContentUriUtils.isVirtualDocument(v2)) {
            String[] v2_1 = v0.getStreamTypes(v2, "*/*");
            return v2_1 == null || v2_1.length <= 0 ? null : v2_1[0];
        }

        return v0.getType(v2);
    }

    private static boolean hasVirtualFlag(Cursor arg8) {
        boolean v1 = false;
        if(Build$VERSION.SDK_INT < 24) {
            return 0;
        }

        int v0 = arg8.getColumnIndex("flags");
        if(v0 > -1 && (arg8.getLong(v0) & 0x200) != 0) {
            v1 = true;
        }

        return v1;
    }

    private static boolean isVirtualDocument(Uri arg8) {
        boolean v2_2;
        Throwable v0;
        Cursor v8;
        if(Build$VERSION.SDK_INT < 19) {
            return 0;
        }

        if(arg8 == null) {
            return 0;
        }

        if(!DocumentsContract.isDocumentUri(ContextUtils.getApplicationContext(), arg8)) {
            return 0;
        }

        ContentResolver v2 = ContextUtils.getApplicationContext().getContentResolver();
        String[] v4 = null;
        String v5 = null;
        String[] v6 = null;
        String v7 = null;
        Uri v3 = arg8;
        try {
            v8 = v2.query(v3, v4, v5, v6, v7);
            v0 = null;
            if(v8 != null) {
                goto label_21;
            }

            goto label_42;
        }
        catch(NullPointerException ) {
            return 0;
        }

        try {
        label_21:
            if(v8.getCount() >= 1) {
                v8.moveToFirst();
                v2_2 = ContentUriUtils.hasVirtualFlag(v8);
                if(v8 == null) {
                    return v2_2;
                }

                goto label_27;
            }

            goto label_42;
        }
        catch(Throwable v2_1) {
        }
        catch(Throwable v0) {
            try {
                throw v0;
            }
            catch(Throwable v2_1) {
                if(v8 == null) {
                    goto label_41;
                }
                else if(v0 != null) {
                    try {
                        v8.close();
                        goto label_41;
                    }
                    catch(NullPointerException ) {
                    }
                    catch(Throwable v8_1) {
                        try {
                            ThrowableExtension.addSuppressed(v0, v8_1);
                            goto label_41;
                        label_40:
                            v8.close();
                        label_41:
                            throw v2_1;
                        label_27:
                            v8.close();
                        }
                        catch(NullPointerException ) {
                            return 0;
                        }

                        return v2_2;
                        try {
                        label_42:
                            if(v8 != null) {
                                v8.close();
                            }

                            return 0;
                        }
                        catch(NullPointerException ) {
                            return 0;
                        }
                    }
                }
                else {
                    goto label_40;
                }

                return v2_2;
            }
        }

        return 0;
    }

    @CalledByNative public static int openContentUriForRead(String arg0) {
        AssetFileDescriptor v0 = ContentUriUtils.getAssetFileDescriptor(arg0);
        if(v0 != null) {
            return v0.getParcelFileDescriptor().detachFd();
        }

        return -1;
    }

    public static void setFileProviderUtil(FileProviderUtil arg1) {
        Object v0 = ContentUriUtils.sLock;
        __monitor_enter(v0);
        try {
            ContentUriUtils.sFileProviderUtil = arg1;
            __monitor_exit(v0);
            return;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_6;
        }

        throw v1;
    }
}

