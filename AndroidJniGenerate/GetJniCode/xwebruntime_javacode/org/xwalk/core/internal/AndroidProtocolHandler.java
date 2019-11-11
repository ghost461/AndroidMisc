package org.xwalk.core.internal;

import android.net.Uri;
import android.util.Log;
import android.util.TypedValue;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.List;
import org.chromium.base.ContextUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="xwalk") public class AndroidProtocolHandler {
    private static final String CONTENT_SCHEME = "content";
    private static final String FILE_SCHEME = "file";
    private static final String TAG = "AndroidProtocolHandler";

    static {
    }

    public AndroidProtocolHandler() {
        super();
    }

    private static Class getClazz(String arg2, String arg3) throws ClassNotFoundException {
        ClassLoader v0 = ContextUtils.getApplicationContext().getClassLoader();
        StringBuilder v1 = new StringBuilder();
        v1.append(arg2);
        v1.append(".R$");
        v1.append(arg3);
        return v0.loadClass(v1.toString());
    }

    private static int getFieldId(String arg5, String arg6) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        Class v0_1;
        String v0 = ContextUtils.getApplicationContext().getPackageName();
        Object v1 = null;
        try {
            v0_1 = AndroidProtocolHandler.getClazz(v0, arg5);
        }
        catch(ClassNotFoundException v2) {
            Class v3 = v0_1;
            v0_1 = ((Class)v1);
            while(v0_1 == null) {
                String v3_1 = AndroidProtocolHandler.removeOneSuffix(((String)v3));
                if(v3_1 == null) {
                    throw v2;
                }

                try {
                    v0_1 = AndroidProtocolHandler.getClazz(v3_1, arg5);
                }
                catch(ClassNotFoundException ) {
                }
            }
        }

        return v0_1.getField(arg6).getInt(v1);
    }

    @CalledByNative public static String getMimeType(InputStream arg5, String arg6) {
        String v0_1;
        Uri v0 = AndroidProtocolHandler.verifyUrl(arg6);
        String v1 = null;
        if(v0 == null) {
            return v1;
        }

        try {
            String v2 = v0.getPath();
            if(v0.getScheme().equals("content")) {
                return ContextUtils.getApplicationContext().getContentResolver().getType(v0);
            }

            if((v0.getScheme().equals("file")) && (v2.startsWith(AndroidProtocolHandler.nativeGetAndroidAssetPath()))) {
                v0_1 = URLConnection.guessContentTypeFromName(v2);
                if(v0_1 == null) {
                    goto label_23;
                }

                return v0_1;
            }

            goto label_23;
        }
        catch(Exception ) {
            Log.e("AndroidProtocolHandler", "Unable to get mime type" + arg6);
            return v1;
        }

        return v0_1;
        try {
        label_23:
            return URLConnection.guessContentTypeFromStream(arg5);
        }
        catch(IOException ) {
            return v1;
        }
    }

    private static int getValueType(int arg3) {
        TypedValue v0 = new TypedValue();
        ContextUtils.getApplicationContext().getResources().getValue(arg3, v0, true);
        return v0.type;
    }

    private static native String nativeGetAndroidAssetPath() {
    }

    private static native String nativeGetAndroidResourcePath() {
    }

    @CalledByNative public static InputStream open(String arg5) {
        Uri v0 = AndroidProtocolHandler.verifyUrl(arg5);
        InputStream v1 = null;
        if(v0 == null) {
            return v1;
        }

        try {
            String v2 = v0.getPath();
            if(v0.getScheme().equals("file")) {
                if(v2.startsWith(AndroidProtocolHandler.nativeGetAndroidAssetPath())) {
                    return AndroidProtocolHandler.openAsset(v0);
                }

                if(!v2.startsWith(AndroidProtocolHandler.nativeGetAndroidResourcePath())) {
                    return v1;
                }

                return AndroidProtocolHandler.openResource(v0);
            }

            if(!v0.getScheme().equals("content")) {
                return v1;
            }

            return AndroidProtocolHandler.openContent(v0);
        }
        catch(Exception ) {
            Log.e("AndroidProtocolHandler", "Error opening inputstream: " + arg5);
        }

        return v1;
    }

    private static InputStream openAsset(Uri arg3) {
        String v0 = arg3.getPath().replaceFirst(AndroidProtocolHandler.nativeGetAndroidAssetPath(), "");
        try {
            return ContextUtils.getApplicationContext().getAssets().open(v0, 2);
        }
        catch(IOException ) {
            Log.e("AndroidProtocolHandler", "Unable to open asset URL: " + arg3);
            return null;
        }
    }

    private static InputStream openContent(Uri arg3) {
        try {
            return ContextUtils.getApplicationContext().getContentResolver().openInputStream(arg3);
        }
        catch(Exception ) {
            Log.e("AndroidProtocolHandler", "Unable to open content URL: " + arg3);
            return null;
        }
    }

    private static InputStream openResource(Uri arg8) {
        List v0 = arg8.getPathSegments();
        int v2 = 3;
        InputStream v3 = null;
        if(v0.size() != v2) {
            Log.e("AndroidProtocolHandler", "Incorrect resource path: " + arg8);
            return v3;
        }

        Object v4 = v0.get(0);
        Object v5 = v0.get(1);
        Object v0_1 = v0.get(2);
        StringBuilder v6 = new StringBuilder();
        v6.append("/");
        v6.append(((String)v4));
        v6.append("/");
        if(!v6.toString().equals(AndroidProtocolHandler.nativeGetAndroidResourcePath())) {
            Log.e("AndroidProtocolHandler", "Resource path does not start with " + AndroidProtocolHandler.nativeGetAndroidResourcePath() + ": " + arg8);
            return v3;
        }

        String v0_2 = ((String)v0_1).split("\\.")[0];
        try {
            int v0_6 = AndroidProtocolHandler.getFieldId(((String)v5), v0_2);
            if(AndroidProtocolHandler.getValueType(v0_6) == v2) {
                return ContextUtils.getApplicationContext().getResources().openRawResource(v0_6);
            }

            Log.e("AndroidProtocolHandler", "Asset not of type string: " + arg8);
            return v3;
        }
        catch(IllegalAccessException v0_3) {
            Log.e("AndroidProtocolHandler", "Unable to open resource URL: " + arg8, ((Throwable)v0_3));
            return v3;
        }
        catch(NoSuchFieldException v0_4) {
            Log.e("AndroidProtocolHandler", "Unable to open resource URL: " + arg8, ((Throwable)v0_4));
            return v3;
        }
        catch(ClassNotFoundException v0_5) {
            Log.e("AndroidProtocolHandler", "Unable to open resource URL: " + arg8, ((Throwable)v0_5));
            return v3;
        }
    }

    private static String removeOneSuffix(String arg3) {
        String v0 = null;
        if(arg3 == null) {
            return v0;
        }

        int v1 = arg3.lastIndexOf(46);
        if(v1 == -1) {
            return v0;
        }

        return arg3.substring(0, v1);
    }

    private static Uri verifyUrl(String arg4) {
        Uri v0 = null;
        if(arg4 == null) {
            return v0;
        }

        Uri v1 = Uri.parse(arg4);
        if(v1 == null) {
            Log.e("AndroidProtocolHandler", "Malformed URL: " + arg4);
            return v0;
        }

        String v2_1 = v1.getPath();
        if(v2_1 != null) {
            if(v2_1.length() == 0) {
            }
            else {
                return v1;
            }
        }

        Log.e("AndroidProtocolHandler", "URL does not have a path: " + arg4);
        return v0;
    }
}

