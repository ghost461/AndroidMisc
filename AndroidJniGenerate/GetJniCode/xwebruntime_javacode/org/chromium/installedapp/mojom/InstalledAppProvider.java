package org.chromium.installedapp.mojom;

import org.chromium.mojo.bindings.Callbacks$Callback1;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface InstalledAppProvider extends Interface {
    public interface FilterInstalledAppsResponse extends Callback1 {
    }

    public interface Proxy extends InstalledAppProvider, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        InstalledAppProvider.MANAGER = InstalledAppProvider_Internal.MANAGER;
    }

    void filterInstalledApps(RelatedApplication[] arg1, FilterInstalledAppsResponse arg2);
}

