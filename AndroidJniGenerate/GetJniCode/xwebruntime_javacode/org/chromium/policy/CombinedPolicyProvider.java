package org.chromium.policy;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.chromium.base.ThreadUtils;
import org.chromium.base.VisibleForTesting;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="policy::android") public class CombinedPolicyProvider {
    public interface PolicyChangeListener {
        void terminateIncognitoSession();
    }

    private final List mCachedPolicies;
    private long mNativeCombinedPolicyProvider;
    private final List mPolicyChangeListeners;
    private PolicyConverter mPolicyConverter;
    private final List mPolicyProviders;
    private static CombinedPolicyProvider sInstance;

    static {
    }

    @VisibleForTesting CombinedPolicyProvider() {
        super();
        this.mPolicyProviders = new ArrayList();
        this.mCachedPolicies = new ArrayList();
        this.mPolicyChangeListeners = new ArrayList();
    }

    public void addPolicyChangeListener(PolicyChangeListener arg2) {
        this.mPolicyChangeListeners.add(arg2);
    }

    public void destroy() {
        Iterator v0 = this.mPolicyProviders.iterator();
        while(v0.hasNext()) {
            v0.next().destroy();
        }

        this.mPolicyProviders.clear();
        this.mPolicyConverter = null;
    }

    public static CombinedPolicyProvider get() {
        if(CombinedPolicyProvider.sInstance == null) {
            CombinedPolicyProvider.sInstance = new CombinedPolicyProvider();
        }

        return CombinedPolicyProvider.sInstance;
    }

    @CalledByNative public static CombinedPolicyProvider linkNative(long arg1, PolicyConverter arg3) {
        ThreadUtils.assertOnUiThread();
        CombinedPolicyProvider.get().linkNativeInternal(arg1, arg3);
        return CombinedPolicyProvider.get();
    }

    private void linkNativeInternal(long arg3, PolicyConverter arg5) {
        this.mNativeCombinedPolicyProvider = arg3;
        this.mPolicyConverter = arg5;
        if(arg3 != 0) {
            Iterator v3 = this.mPolicyProviders.iterator();
            while(v3.hasNext()) {
                v3.next().refresh();
            }
        }
    }

    @VisibleForTesting protected native void nativeFlushPolicies(long arg1) {
    }

    void onSettingsAvailable(int arg5, Bundle arg6) {
        this.mCachedPolicies.set(arg5, arg6);
        Iterator v5 = this.mCachedPolicies.iterator();
        do {
            if(!v5.hasNext()) {
                goto label_9;
            }
        }
        while(v5.next() != null);

        return;
    label_9:
        if(this.mNativeCombinedPolicyProvider == 0) {
            return;
        }

        v5 = this.mCachedPolicies.iterator();
    label_15:
        if(v5.hasNext()) {
            Object v6 = v5.next();
            Iterator v0 = ((Bundle)v6).keySet().iterator();
            while(true) {
                if(!v0.hasNext()) {
                    goto label_15;
                }

                Object v1 = v0.next();
                this.mPolicyConverter.setPolicy(((String)v1), ((Bundle)v6).get(((String)v1)));
            }
        }

        this.nativeFlushPolicies(this.mNativeCombinedPolicyProvider);
    }

    @VisibleForTesting @CalledByNative void refreshPolicies() {
        int v0;
        for(v0 = 0; v0 < this.mCachedPolicies.size(); ++v0) {
            this.mCachedPolicies.set(v0, null);
        }

        Iterator v0_1 = this.mPolicyProviders.iterator();
        while(v0_1.hasNext()) {
            v0_1.next().refresh();
        }
    }

    public void registerProvider(PolicyProvider arg6) {
        this.mPolicyProviders.add(arg6);
        this.mCachedPolicies.add(null);
        arg6.setManagerAndSource(this, this.mPolicyProviders.size() - 1);
        if(this.mNativeCombinedPolicyProvider != 0) {
            arg6.refresh();
        }
    }

    public void removePolicyChangeListener(PolicyChangeListener arg2) {
        this.mPolicyChangeListeners.remove(arg2);
    }

    @VisibleForTesting public static void set(CombinedPolicyProvider arg0) {
        CombinedPolicyProvider.sInstance = arg0;
    }

    void terminateIncognitoSession() {
        Iterator v0 = this.mPolicyChangeListeners.iterator();
        while(v0.hasNext()) {
            v0.next().terminateIncognitoSession();
        }
    }
}

