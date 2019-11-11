package android.support.v4.os;

import android.content.Context;
import android.os.Build$VERSION;
import android.os.UserManager;

public class UserManagerCompat {
    private UserManagerCompat() {
        super();
    }

    public static boolean isUserUnlocked(Context arg2) {
        if(Build$VERSION.SDK_INT >= 24) {
            return arg2.getSystemService(UserManager.class).isUserUnlocked();
        }

        return 1;
    }
}

