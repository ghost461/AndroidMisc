package android.support.v4.content.pm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender$SendIntentException;
import android.content.IntentSender;
import android.content.pm.ShortcutManager;
import android.os.Build$VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import java.util.Iterator;

public class ShortcutManagerCompat {
    @VisibleForTesting static final String ACTION_INSTALL_SHORTCUT = "com.android.launcher.action.INSTALL_SHORTCUT";
    @VisibleForTesting static final String INSTALL_SHORTCUT_PERMISSION = "com.android.launcher.permission.INSTALL_SHORTCUT";

    private ShortcutManagerCompat() {
        super();
    }

    @NonNull public static Intent createShortcutResultIntent(@NonNull Context arg2, @NonNull ShortcutInfoCompat arg3) {
        Intent v2 = Build$VERSION.SDK_INT >= 26 ? arg2.getSystemService(ShortcutManager.class).createShortcutResultIntent(arg3.toShortcutInfo()) : null;
        if(v2 == null) {
            v2 = new Intent();
        }

        return arg3.addToIntent(v2);
    }

    public static boolean isRequestPinShortcutSupported(@NonNull Context arg3) {
        if(Build$VERSION.SDK_INT >= 26) {
            return arg3.getSystemService(ShortcutManager.class).isRequestPinShortcutSupported();
        }

        if(ContextCompat.checkSelfPermission(arg3, "com.android.launcher.permission.INSTALL_SHORTCUT") != 0) {
            return 0;
        }

        Iterator v3 = arg3.getPackageManager().queryBroadcastReceivers(new Intent("com.android.launcher.action.INSTALL_SHORTCUT"), 0).iterator();
        do {
            if(!v3.hasNext()) {
                return 0;
            }

            String v0 = v3.next().activityInfo.permission;
            if(TextUtils.isEmpty(((CharSequence)v0))) {
                return 1;
            }
        }
        while(!"com.android.launcher.permission.INSTALL_SHORTCUT".equals(v0));

        return 1;
    }

    public static boolean requestPinShortcut(@NonNull Context arg10, @NonNull ShortcutInfoCompat arg11, @Nullable IntentSender arg12) {
        if(Build$VERSION.SDK_INT >= 26) {
            return arg10.getSystemService(ShortcutManager.class).requestPinShortcut(arg11.toShortcutInfo(), arg12);
        }

        if(!ShortcutManagerCompat.isRequestPinShortcutSupported(arg10)) {
            return 0;
        }

        Intent v3 = arg11.addToIntent(new Intent("com.android.launcher.action.INSTALL_SHORTCUT"));
        if(arg12 == null) {
            arg10.sendBroadcast(v3);
            return 1;
        }

        arg10.sendOrderedBroadcast(v3, null, new BroadcastReceiver(arg12) {
            public void onReceive(Context arg7, Intent arg8) {
                try {
                    this.val$callback.sendIntent(arg7, 0, null, null, null);
                    return;
                }
                catch(IntentSender$SendIntentException ) {
                    return;
                }
            }
        }, null, -1, null, null);
        return 1;
    }
}

