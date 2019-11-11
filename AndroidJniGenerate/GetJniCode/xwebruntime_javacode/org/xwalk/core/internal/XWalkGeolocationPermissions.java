package org.xwalk.core.internal;

import android.content.SharedPreferences$Editor;
import android.content.SharedPreferences;
import android.webkit.ValueCallback;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.chromium.base.ThreadUtils;
import org.chromium.net.GURLUtils;

public final class XWalkGeolocationPermissions {
    public interface Callback {
        void invoke(String arg1, boolean arg2, boolean arg3);
    }

    private static final String PREF_PREFIX;
    private final SharedPreferences mSharedPreferences;

    static {
        XWalkGeolocationPermissions.PREF_PREFIX = XWalkGeolocationPermissions.class.getCanonicalName() + "%";
    }

    public XWalkGeolocationPermissions(SharedPreferences arg1) {
        super();
        this.mSharedPreferences = arg1;
    }

    public void allow(String arg3) {
        arg3 = this.getOriginKey(arg3);
        if(arg3 != null) {
            this.mSharedPreferences.edit().putBoolean(arg3, true).apply();
        }
    }

    public void clear(String arg2) {
        arg2 = this.getOriginKey(arg2);
        if(arg2 != null) {
            this.mSharedPreferences.edit().remove(arg2).apply();
        }
    }

    public void clearAll() {
        Iterator v0 = this.mSharedPreferences.getAll().keySet().iterator();
        SharedPreferences$Editor v1 = null;
        while(v0.hasNext()) {
            Object v2 = v0.next();
            if(!((String)v2).startsWith(XWalkGeolocationPermissions.PREF_PREFIX)) {
                continue;
            }

            if(v1 == null) {
                v1 = this.mSharedPreferences.edit();
            }

            v1.remove(((String)v2));
        }

        if(v1 != null) {
            v1.apply();
        }
    }

    public void deny(String arg3) {
        arg3 = this.getOriginKey(arg3);
        if(arg3 != null) {
            this.mSharedPreferences.edit().putBoolean(arg3, false).apply();
        }
    }

    public void getAllowed(String arg2, ValueCallback arg3) {
        ThreadUtils.postOnUiThread(new Runnable(arg3, this.isOriginAllowed(arg2)) {
            public void run() {
                this.val$callback.onReceiveValue(Boolean.valueOf(this.val$finalAllowed));
            }
        });
    }

    private String getOriginKey(String arg3) {
        arg3 = GURLUtils.getOrigin(arg3);
        if(arg3.isEmpty()) {
            return null;
        }

        return XWalkGeolocationPermissions.PREF_PREFIX + arg3;
    }

    public void getOrigins(ValueCallback arg5) {
        HashSet v0 = new HashSet();
        Iterator v1 = this.mSharedPreferences.getAll().keySet().iterator();
        while(v1.hasNext()) {
            Object v2 = v1.next();
            if(!((String)v2).startsWith(XWalkGeolocationPermissions.PREF_PREFIX)) {
                continue;
            }

            ((Set)v0).add(((String)v2).substring(XWalkGeolocationPermissions.PREF_PREFIX.length()));
        }

        ThreadUtils.postOnUiThread(new Runnable(arg5, ((Set)v0)) {
            public void run() {
                this.val$callback.onReceiveValue(this.val$origins);
            }
        });
    }

    public boolean hasOrigin(String arg2) {
        return this.mSharedPreferences.contains(this.getOriginKey(arg2));
    }

    public boolean isOriginAllowed(String arg3) {
        return this.mSharedPreferences.getBoolean(this.getOriginKey(arg3), false);
    }
}

