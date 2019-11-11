package org.chromium.policy;

import android.annotation.TargetApi;
import android.os.Build$VERSION;
import android.os.Bundle;
import java.util.Arrays;
import java.util.Iterator;
import org.chromium.base.Log;
import org.chromium.base.VisibleForTesting;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@JNINamespace(value="policy::android") public class PolicyConverter {
    private static final String TAG = "PolicyConverter";
    private long mNativePolicyConverter;

    static {
    }

    private PolicyConverter(long arg1) {
        super();
        this.mNativePolicyConverter = arg1;
    }

    @TargetApi(value=21) private JSONArray convertBundleArrayToJson(Bundle[] arg5) throws JSONException {
        JSONArray v0 = new JSONArray();
        int v1 = arg5.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            v0.put(this.convertBundleToJson(arg5[v2]));
        }

        return v0;
    }

    @TargetApi(value=21) private JSONObject convertBundleToJson(Bundle arg6) throws JSONException {
        JSONArray v3_2;
        JSONObject v0 = new JSONObject();
        Iterator v1 = arg6.keySet().iterator();
        while(v1.hasNext()) {
            Object v2 = v1.next();
            Object v3 = arg6.get(((String)v2));
            if((v3 instanceof Bundle)) {
                JSONObject v3_1 = this.convertBundleToJson(((Bundle)v3));
            }

            if((v3 instanceof Bundle[])) {
                v3_2 = this.convertBundleArrayToJson(((Bundle[])v3));
            }

            v0.put(((String)v2), JSONObject.wrap(v3_2));
        }

        return v0;
    }

    @VisibleForTesting @CalledByNative static PolicyConverter create(long arg1) {
        return new PolicyConverter(arg1);
    }

    @VisibleForTesting native void nativeSetPolicyBoolean(long arg1, String arg2, boolean arg3) {
    }

    @VisibleForTesting native void nativeSetPolicyInteger(long arg1, String arg2, int arg3) {
    }

    @VisibleForTesting native void nativeSetPolicyString(long arg1, String arg2, String arg3) {
    }

    @VisibleForTesting native void nativeSetPolicyStringArray(long arg1, String arg2, String[] arg3) {
    }

    @CalledByNative private void onNativeDestroyed() {
        this.mNativePolicyConverter = 0;
    }

    public void setPolicy(String arg5, Object arg6) {
        if((arg6 instanceof Boolean)) {
            this.nativeSetPolicyBoolean(this.mNativePolicyConverter, arg5, ((Boolean)arg6).booleanValue());
            return;
        }

        if((arg6 instanceof String)) {
            this.nativeSetPolicyString(this.mNativePolicyConverter, arg5, ((String)arg6));
            return;
        }

        if((arg6 instanceof Integer)) {
            this.nativeSetPolicyInteger(this.mNativePolicyConverter, arg5, ((Integer)arg6).intValue());
            return;
        }

        if((arg6 instanceof String[])) {
            this.nativeSetPolicyStringArray(this.mNativePolicyConverter, arg5, ((String[])arg6));
            return;
        }

        if(Build$VERSION.SDK_INT >= 21) {
            if((arg6 instanceof Bundle)) {
                try {
                    this.nativeSetPolicyString(this.mNativePolicyConverter, arg5, this.convertBundleToJson(((Bundle)arg6)).toString());
                }
                catch(JSONException ) {
                    Log.w("PolicyConverter", "Invalid bundle in app restrictions " + ((Bundle)arg6).toString() + " for key " + arg5, new Object[0]);
                }

                return;
            }
            else if((arg6 instanceof Bundle[])) {
                try {
                    this.nativeSetPolicyString(this.mNativePolicyConverter, arg5, this.convertBundleArrayToJson(((Bundle[])arg6)).toString());
                }
                catch(JSONException ) {
                    Log.w("PolicyConverter", "Invalid bundle array in app restrictions " + Arrays.toString(((Object[])arg6)) + " for key " + arg5, new Object[0]);
                }

                return;
            }
        }
    }
}

