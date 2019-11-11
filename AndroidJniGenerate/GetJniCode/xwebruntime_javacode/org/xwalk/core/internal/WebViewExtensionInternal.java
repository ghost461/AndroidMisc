package org.xwalk.core.internal;

import android.content.res.AssetManager;
import android.content.res.Resources$NotFoundException;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.BitmapFactory$Options;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import com.tencent.xweb.statistics.XRequestStat;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.List;
import org.chromium.net.AndroidNetworkLibrary$NetworkCallback;
import org.chromium.net.AndroidNetworkLibrary;
import org.xmlpull.v1.XmlPullParser;

public class WebViewExtensionInternal {
    private static final String TAG = "WebViewExtensionInternal";
    private static Method mOpenNonAsset;
    private static Method mloadXmlResourceParser;
    private static WebViewExtensionInterfaceInternal sExtensionInterface;
    private static WebViewExtensionInternal sInstance;

    static {
    }

    public WebViewExtensionInternal() {
        super();
    }

    public void SetExtension(WebViewExtensionInterfaceInternal arg2) {
        WebViewExtensionInternal.sExtensionInterface = arg2;
        if(RuntimeToSdkChannel.getCmd("usenewdns", null).equals("true")) {
            Log.i("WebViewExtensionInternal", "AndroidNetworkLibrary: use newdns");
            AndroidNetworkLibrary.setNetworkCallback(new NetworkCallback() {
                public void OnNewDns(int arg2) {
                    XRequestStat.getInstance().OnNewDns(arg2);
                }

                public int getHostByName(String arg2, List arg3) {
                    if(WebViewExtensionInternal.sExtensionInterface != null) {
                        return WebViewExtensionInternal.sExtensionInterface.getHostByName(arg2, arg3);
                    }

                    return 0;
                }
            });
        }
    }

    static WebViewExtensionInterfaceInternal access$000() {
        return WebViewExtensionInternal.sExtensionInterface;
    }

    public Drawable getDrawable(Resources arg5, int arg6) {
        if(WebViewExtensionInternal.sExtensionInterface != null) {
            try {
                return WebViewExtensionInternal.sExtensionInterface.onMiscCallBack("getDrawable", new Object[]{arg5, Integer.valueOf(arg6)});
            }
            catch(Exception v5) {
                Log.e("WebViewExtensionInternal", v5.getMessage());
            }
        }

        Log.e("WebViewExtensionInternal", "getDrawable return null, may be something wrong");
        return null;
    }

    public static WebViewExtensionInternal getInstance() {
        if(WebViewExtensionInternal.sInstance == null) {
            WebViewExtensionInternal.sInstance = new WebViewExtensionInternal();
        }

        return WebViewExtensionInternal.sInstance;
    }

    public static Drawable loadDrawableSkipCache(Resources arg12, int arg13) {
        Resources$NotFoundException v0_2;
        StringBuilder v1;
        Drawable v12_2;
        ColorDrawable v12_3;
        Class[] v10;
        Class v5;
        TypedValue v0 = new TypedValue();
        arg12.getValue(arg13, v0, true);
        int v2 = v0.type < 28 || v0.type > 0x1F ? 0 : 1;
        Drawable v4 = null;
        ColorDrawable v2_1 = v2 != 0 ? new ColorDrawable(v0.data) : ((ColorDrawable)v4);
        int v6 = 4;
        int v7 = 3;
        int v8 = 2;
        if(WebViewExtensionInternal.mloadXmlResourceParser == null) {
            try {
                v5 = Resources.class;
                v10 = new Class[v6];
                v10[0] = String.class;
                v10[1] = Integer.TYPE;
                v10[v8] = Integer.TYPE;
                v10[v7] = String.class;
                WebViewExtensionInternal.mloadXmlResourceParser = v5.getDeclaredMethod("loadXmlResourceParser", v10);
                WebViewExtensionInternal.mloadXmlResourceParser.setAccessible(true);
            }
            catch(NoSuchMethodException v12) {
                Log.e("WebViewExtensionInternal", v12.getMessage());
                return v4;
            }
        }

        if(WebViewExtensionInternal.mOpenNonAsset == null) {
            try {
                v5 = AssetManager.class;
                v10 = new Class[v7];
                v10[0] = Integer.TYPE;
                v10[1] = String.class;
                v10[v8] = Integer.TYPE;
                WebViewExtensionInternal.mOpenNonAsset = v5.getDeclaredMethod("openNonAsset", v10);
                WebViewExtensionInternal.mOpenNonAsset.setAccessible(true);
            }
            catch(NoSuchMethodException v12) {
                Log.e("WebViewExtensionInternal", v12.getMessage());
                return v4;
            }
        }

        if(v2_1 != null) {
            v12_3 = v2_1;
        }
        else if(v0.string == null) {
            StringBuilder v13 = new StringBuilder();
            v13.append("Resource is not a Drawable (color or path): ");
            v13.append(v0);
            throw new Resources$NotFoundException(v13.toString());
        }
        else {
            String v2_2 = v0.string.toString();
            if(v2_2.endsWith(".xml")) {
                try {
                    arg12.getDrawable(arg13);
                    Method v4_1 = WebViewExtensionInternal.mloadXmlResourceParser;
                    Object[] v5_1 = new Object[v6];
                    v5_1[0] = v2_2;
                    v5_1[1] = Integer.valueOf(arg13);
                    v5_1[v8] = Integer.valueOf(v0.assetCookie);
                    v5_1[v7] = "drawable";
                    Object v0_1 = v4_1.invoke(arg12, v5_1);
                    v12_2 = Drawable.createFromXml(arg12, ((XmlPullParser)v0_1));
                    ((XmlResourceParser)v0_1).close();
                }
                catch(Exception v12_1) {
                    v1 = new StringBuilder();
                    v1.append("File ");
                    v1.append(v2_2);
                    v1.append(" from drawable resource ID #0x");
                    v1.append(Integer.toHexString(arg13));
                    v0_2 = new Resources$NotFoundException(v1.toString());
                    v0_2.initCause(((Throwable)v12_1));
                    throw v0_2;
                }
            }
            else {
                try {
                    Method v5_2 = WebViewExtensionInternal.mOpenNonAsset;
                    AssetManager v6_1 = arg12.getAssets();
                    Object[] v7_1 = new Object[v7];
                    v7_1[0] = Integer.valueOf(v0.assetCookie);
                    v7_1[1] = v2_2;
                    v7_1[v8] = Integer.valueOf(v8);
                    Object v1_1 = v5_2.invoke(v6_1, v7_1);
                    v12_2 = Drawable.createFromResourceStream(arg12, v0, ((InputStream)v1_1), v2_2, ((BitmapFactory$Options)v4));
                    ((InputStream)v1_1).close();
                }
                catch(Exception v12_1) {
                    v1 = new StringBuilder();
                    v1.append("File ");
                    v1.append(v2_2);
                    v1.append(" from drawable resource ID #0x");
                    v1.append(Integer.toHexString(arg13));
                    v0_2 = new Resources$NotFoundException(v1.toString());
                    v0_2.initCause(((Throwable)v12_1));
                    throw v0_2;
                }
            }
        }

        return ((Drawable)v12_3);
    }
}

