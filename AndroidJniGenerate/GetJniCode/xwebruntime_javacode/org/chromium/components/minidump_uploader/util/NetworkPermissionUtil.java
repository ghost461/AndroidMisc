package org.chromium.components.minidump_uploader.util;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkPermissionUtil {
    public NetworkPermissionUtil() {
        super();
    }

    public static boolean isNetworkUnmetered(ConnectivityManager arg1) {
        NetworkInfo v0 = arg1.getActiveNetworkInfo();
        if(v0 != null) {
            if(!v0.isConnected()) {
            }
            else {
                return arg1.isActiveNetworkMetered() ^ 1;
            }
        }

        return 0;
    }
}

