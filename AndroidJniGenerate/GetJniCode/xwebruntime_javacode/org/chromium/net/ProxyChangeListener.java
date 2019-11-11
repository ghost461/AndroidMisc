package org.chromium.net;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build$VERSION;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.chromium.base.BuildConfig;
import org.chromium.base.ContextUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.base.annotations.NativeClassQualifiedName;

@JNINamespace(value="net") public class ProxyChangeListener {
    class org.chromium.net.ProxyChangeListener$1 {
    }

    public interface Delegate {
        void proxySettingsChanged();
    }

    class ProxyConfig {
        public final String[] mExclusionList;
        public final String mHost;
        public final String mPacUrl;
        public final int mPort;

        public ProxyConfig(String arg1, int arg2, String arg3, String[] arg4) {
            super();
            this.mHost = arg1;
            this.mPort = arg2;
            this.mPacUrl = arg3;
            this.mExclusionList = arg4;
        }
    }

    class ProxyReceiver extends BroadcastReceiver {
        ProxyReceiver(ProxyChangeListener arg1, org.chromium.net.ProxyChangeListener$1 arg2) {
            this(arg1);
        }

        private ProxyReceiver(ProxyChangeListener arg1) {
            ProxyChangeListener.this = arg1;
            super();
        }

        static ProxyConfig access$000(ProxyReceiver arg0, Intent arg1) {
            return arg0.extractNewProxy(arg1);
        }

        private ProxyConfig extractNewProxy(Intent arg10) {
            Object v6_2;
            String v3;
            String v1;
            ProxyConfig v0 = null;
            try {
                int v2 = 21;
                if(Build$VERSION.SDK_INT < v2) {
                    v1 = "android.net.ProxyProperties";
                    v3 = "proxy";
                }
                else {
                    v1 = "android.net.ProxyInfo";
                    v3 = "android.intent.extra.PROXY_INFO";
                }

                Object v10_5 = arg10.getExtras().get(v3);
                if(v10_5 == null) {
                    return v0;
                }

                Class v1_1 = Class.forName(v1);
                Method v3_1 = v1_1.getDeclaredMethod("getHost");
                Method v5 = v1_1.getDeclaredMethod("getPort");
                Method v6 = v1_1.getDeclaredMethod("getExclusionList");
                Object v3_2 = v3_1.invoke(v10_5);
                int v5_1 = v5.invoke(v10_5).intValue();
                if(Build$VERSION.SDK_INT < v2) {
                    String[] v6_1 = v6.invoke(v10_5).split(",");
                }
                else {
                    v6_2 = v6.invoke(v10_5);
                }

                int v7 = Build$VERSION.SDK_INT;
                if(v7 >= 19 && Build$VERSION.SDK_INT < v2) {
                    v10_5 = v1_1.getDeclaredMethod("getPacFileUrl").invoke(v10_5);
                    if(!TextUtils.isEmpty(((CharSequence)v10_5))) {
                        return new ProxyConfig(((String)v3_2), v5_1, ((String)v10_5), ((String[])v6_2));
                    }
                }
                else if(Build$VERSION.SDK_INT >= v2) {
                    v10_5 = v1_1.getDeclaredMethod("getPacFileUrl").invoke(v10_5);
                    if(!Uri.EMPTY.equals(v10_5)) {
                        return new ProxyConfig(((String)v3_2), v5_1, ((Uri)v10_5).toString(), ((String[])v6_2));
                    }
                }

                return new ProxyConfig(((String)v3_2), v5_1, ((String)v0), ((String[])v6_2));
            }
            catch(NullPointerException v10) {
                Log.e("ProxyChangeListener", "Using no proxy configuration due to exception:" + v10);
                return v0;
            }
            catch(InvocationTargetException v10_1) {
                Log.e("ProxyChangeListener", "Using no proxy configuration due to exception:" + v10_1);
                return v0;
            }
            catch(IllegalAccessException v10_2) {
                Log.e("ProxyChangeListener", "Using no proxy configuration due to exception:" + v10_2);
                return v0;
            }
            catch(NoSuchMethodException v10_3) {
                Log.e("ProxyChangeListener", "Using no proxy configuration due to exception:" + v10_3);
                return v0;
            }
            catch(ClassNotFoundException v10_4) {
                Log.e("ProxyChangeListener", "Using no proxy configuration due to exception:" + v10_4);
                return v0;
            }
        }

        public void onReceive(Context arg2, Intent arg3) {
            if(arg3.getAction().equals("android.intent.action.PROXY_CHANGE")) {
                ProxyChangeListener.this.runOnThread(new Runnable(arg3) {
                    public void run() {
                        this.this$1.this$0.proxySettingsChanged(this.this$1, this.this$1.extractNewProxy(this.val$intent));
                    }
                });
            }
        }
    }

    private static final String TAG = "ProxyChangeListener";
    private Delegate mDelegate;
    private final Handler mHandler;
    private final Looper mLooper;
    private long mNativePtr;
    private ProxyReceiver mProxyReceiver;
    private static boolean sEnabled = true;

    static {
    }

    private ProxyChangeListener() {
        super();
        this.mLooper = Looper.myLooper();
        this.mHandler = new Handler(this.mLooper);
    }

    static void access$100(ProxyChangeListener arg0, ProxyReceiver arg1, ProxyConfig arg2) {
        arg0.proxySettingsChanged(arg1, arg2);
    }

    static void access$200(ProxyChangeListener arg0, Runnable arg1) {
        arg0.runOnThread(arg1);
    }

    private void assertOnThread() {
        if((BuildConfig.DCHECK_IS_ON) && !this.onThread()) {
            throw new IllegalStateException("Must be called on ProxyChangeListener thread.");
        }
    }

    @CalledByNative public static ProxyChangeListener create() {
        return new ProxyChangeListener();
    }

    @CalledByNative public static String getProperty(String arg0) {
        return System.getProperty(arg0);
    }

    @NativeClassQualifiedName(value="ProxyConfigServiceAndroid::JNIDelegate") private native void nativeProxySettingsChanged(long arg1) {
    }

    @NativeClassQualifiedName(value="ProxyConfigServiceAndroid::JNIDelegate") private native void nativeProxySettingsChangedTo(long arg1, String arg2, int arg3, String arg4, String[] arg5) {
    }

    private boolean onThread() {
        boolean v0 = this.mLooper == Looper.myLooper() ? true : false;
        return v0;
    }

    private void proxySettingsChanged(ProxyReceiver arg8, ProxyConfig arg9) {
        if(ProxyChangeListener.sEnabled) {
            if(arg8 != this.mProxyReceiver) {
            }
            else {
                if(this.mDelegate != null) {
                    this.mDelegate.proxySettingsChanged();
                }

                if(this.mNativePtr == 0) {
                    return;
                }

                if(arg9 != null) {
                    this.nativeProxySettingsChangedTo(this.mNativePtr, arg9.mHost, arg9.mPort, arg9.mPacUrl, arg9.mExclusionList);
                }
                else {
                    this.nativeProxySettingsChanged(this.mNativePtr);
                }

                return;
            }
        }
    }

    private void registerReceiver() {
        if(this.mProxyReceiver != null) {
            return;
        }

        IntentFilter v0 = new IntentFilter();
        v0.addAction("android.intent.action.PROXY_CHANGE");
        this.mProxyReceiver = new ProxyReceiver(this, null);
        ContextUtils.getApplicationContext().registerReceiver(this.mProxyReceiver, v0);
    }

    private void runOnThread(Runnable arg2) {
        if(this.onThread()) {
            arg2.run();
        }
        else {
            this.mHandler.post(arg2);
        }
    }

    public void setDelegateForTesting(Delegate arg1) {
        this.mDelegate = arg1;
    }

    public static void setEnabled(boolean arg0) {
        ProxyChangeListener.sEnabled = arg0;
    }

    @CalledByNative public void start(long arg1) {
        this.assertOnThread();
        this.mNativePtr = arg1;
        this.registerReceiver();
    }

    @CalledByNative public void stop() {
        this.assertOnThread();
        this.mNativePtr = 0;
        this.unregisterReceiver();
    }

    private void unregisterReceiver() {
        if(this.mProxyReceiver == null) {
            return;
        }

        ContextUtils.getApplicationContext().unregisterReceiver(this.mProxyReceiver);
        this.mProxyReceiver = null;
    }
}

