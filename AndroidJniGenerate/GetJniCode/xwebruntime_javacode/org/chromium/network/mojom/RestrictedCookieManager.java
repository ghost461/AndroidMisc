package org.chromium.network.mojom;

import org.chromium.mojo.bindings.Callbacks$Callback1;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;
import org.chromium.url.mojom.Url;

public interface RestrictedCookieManager extends Interface {
    public interface GetAllForUrlResponse extends Callback1 {
    }

    public interface Proxy extends org.chromium.mojo.bindings.Interface$Proxy, RestrictedCookieManager {
    }

    public interface SetCanonicalCookieResponse extends Callback1 {
    }

    public static final Manager MANAGER;

    static {
        RestrictedCookieManager.MANAGER = RestrictedCookieManager_Internal.MANAGER;
    }

    void addChangeListener(Url arg1, Url arg2, CookieChangeListener arg3);

    void getAllForUrl(Url arg1, Url arg2, CookieManagerGetOptions arg3, GetAllForUrlResponse arg4);

    void setCanonicalCookie(CanonicalCookie arg1, Url arg2, Url arg3, SetCanonicalCookieResponse arg4);
}

