package org.chromium.content_public.browser;

import org.chromium.base.Callback;
import org.chromium.services.service_manager.InterfaceProvider;

public interface RenderFrameHost {
    void getCanonicalUrlForSharing(Callback arg1);

    String getLastCommittedURL();

    InterfaceProvider getRemoteInterfaces();

    void notifyUserActivation();
}

