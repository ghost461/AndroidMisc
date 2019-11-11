package org.chromium.network.mojom;

import org.chromium.mojo.bindings.Callbacks$Callback0;
import org.chromium.mojo.bindings.Callbacks$Callback1;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;
import org.chromium.mojo.bindings.InterfaceRequest;
import org.chromium.url.mojom.Url;

public interface CookieManager extends Interface {
    public interface DeleteCookiesResponse extends Callback1 {
    }

    public interface FlushCookieStoreResponse extends Callback0 {
    }

    public interface GetAllCookiesResponse extends Callback1 {
    }

    public interface GetCookieListResponse extends Callback1 {
    }

    public interface Proxy extends org.chromium.mojo.bindings.Interface$Proxy, CookieManager {
    }

    public interface SetCanonicalCookieResponse extends Callback1 {
    }

    public static final Manager MANAGER;

    static {
        CookieManager.MANAGER = CookieManager_Internal.MANAGER;
    }

    void addCookieChangeListener(Url arg1, String arg2, CookieChangeListener arg3);

    void addGlobalChangeListener(CookieChangeListener arg1);

    void cloneInterface(InterfaceRequest arg1);

    void deleteCookies(CookieDeletionFilter arg1, DeleteCookiesResponse arg2);

    void flushCookieStore(FlushCookieStoreResponse arg1);

    void getAllCookies(GetAllCookiesResponse arg1);

    void getCookieList(Url arg1, CookieOptions arg2, GetCookieListResponse arg3);

    void setCanonicalCookie(CanonicalCookie arg1, boolean arg2, boolean arg3, SetCanonicalCookieResponse arg4);
}

