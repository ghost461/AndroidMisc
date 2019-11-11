package android.support.v4.net;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build$VERSION;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RequiresPermission;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class ConnectivityManagerCompat {
    @RequiresApi(value=16) class ConnectivityManagerCompatApi16Impl extends ConnectivityManagerCompatBaseImpl {
        ConnectivityManagerCompatApi16Impl() {
            super();
        }

        public boolean isActiveNetworkMetered(ConnectivityManager arg1) {
            return arg1.isActiveNetworkMetered();
        }
    }

    @RequiresApi(value=24) class ConnectivityManagerCompatApi24Impl extends ConnectivityManagerCompatApi16Impl {
        ConnectivityManagerCompatApi24Impl() {
            super();
        }

        public int getRestrictBackgroundStatus(ConnectivityManager arg1) {
            return arg1.getRestrictBackgroundStatus();
        }
    }

    class ConnectivityManagerCompatBaseImpl implements ConnectivityManagerCompatImpl {
        ConnectivityManagerCompatBaseImpl() {
            super();
        }

        public int getRestrictBackgroundStatus(ConnectivityManager arg1) {
            return 3;
        }

        public boolean isActiveNetworkMetered(ConnectivityManager arg2) {
            NetworkInfo v2 = arg2.getActiveNetworkInfo();
            if(v2 == null) {
                return 1;
            }

            switch(v2.getType()) {
                case 0: 
                case 2: 
                case 3: 
                case 4: 
                case 5: 
                case 6: {
                    return 1;
                }
                case 1: 
                case 7: 
                case 9: {
                    return 0;
                }
            }

            return 1;
        }
    }

    interface ConnectivityManagerCompatImpl {
        int getRestrictBackgroundStatus(ConnectivityManager arg1);

        boolean isActiveNetworkMetered(ConnectivityManager arg1);
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) @Retention(value=RetentionPolicy.SOURCE) @public interface RestrictBackgroundStatus {
    }

    private static final ConnectivityManagerCompatImpl IMPL = null;
    public static final int RESTRICT_BACKGROUND_STATUS_DISABLED = 1;
    public static final int RESTRICT_BACKGROUND_STATUS_ENABLED = 3;
    public static final int RESTRICT_BACKGROUND_STATUS_WHITELISTED = 2;

    static {
        if(Build$VERSION.SDK_INT >= 24) {
            ConnectivityManagerCompat.IMPL = new ConnectivityManagerCompatApi24Impl();
        }
        else if(Build$VERSION.SDK_INT >= 16) {
            ConnectivityManagerCompat.IMPL = new ConnectivityManagerCompatApi16Impl();
        }
        else {
            ConnectivityManagerCompat.IMPL = new ConnectivityManagerCompatBaseImpl();
        }
    }

    private ConnectivityManagerCompat() {
        super();
    }

    public static NetworkInfo getNetworkInfoFromBroadcast(ConnectivityManager arg1, Intent arg2) {
        Parcelable v2 = arg2.getParcelableExtra("networkInfo");
        if(v2 != null) {
            return arg1.getNetworkInfo(((NetworkInfo)v2).getType());
        }

        return null;
    }

    public static int getRestrictBackgroundStatus(ConnectivityManager arg1) {
        return ConnectivityManagerCompat.IMPL.getRestrictBackgroundStatus(arg1);
    }

    @RequiresPermission(value="android.permission.ACCESS_NETWORK_STATE") public static boolean isActiveNetworkMetered(ConnectivityManager arg1) {
        return ConnectivityManagerCompat.IMPL.isActiveNetworkMetered(arg1);
    }
}

