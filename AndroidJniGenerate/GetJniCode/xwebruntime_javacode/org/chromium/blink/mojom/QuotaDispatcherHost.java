package org.chromium.blink.mojom;

import org.chromium.mojo.bindings.Callbacks$Callback3;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;
import org.chromium.url.mojom.Origin;

public interface QuotaDispatcherHost extends Interface {
    public interface Proxy extends QuotaDispatcherHost, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public interface QueryStorageUsageAndQuotaResponse extends Callback3 {
    }

    public interface RequestStorageQuotaResponse extends Callback3 {
    }

    public static final Manager MANAGER;

    static {
        QuotaDispatcherHost.MANAGER = QuotaDispatcherHost_Internal.MANAGER;
    }

    void queryStorageUsageAndQuota(Origin arg1, int arg2, QueryStorageUsageAndQuotaResponse arg3);

    void requestStorageQuota(Origin arg1, int arg2, long arg3, RequestStorageQuotaResponse arg4);
}

