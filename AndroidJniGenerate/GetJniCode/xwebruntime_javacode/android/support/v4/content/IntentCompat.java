package android.support.v4.content;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Build$VERSION;
import android.support.annotation.RequiresApi;

public final class IntentCompat {
    @RequiresApi(value=15) class IntentCompatApi15Impl extends IntentCompatBaseImpl {
        IntentCompatApi15Impl() {
            super();
        }

        public Intent makeMainSelectorActivity(String arg1, String arg2) {
            return Intent.makeMainSelectorActivity(arg1, arg2);
        }
    }

    class IntentCompatBaseImpl {
        IntentCompatBaseImpl() {
            super();
        }

        public Intent makeMainSelectorActivity(String arg2, String arg3) {
            Intent v0 = new Intent(arg2);
            v0.addCategory(arg3);
            return v0;
        }
    }

    @Deprecated public static final String ACTION_EXTERNAL_APPLICATIONS_AVAILABLE = "android.intent.action.EXTERNAL_APPLICATIONS_AVAILABLE";
    @Deprecated public static final String ACTION_EXTERNAL_APPLICATIONS_UNAVAILABLE = "android.intent.action.EXTERNAL_APPLICATIONS_UNAVAILABLE";
    public static final String CATEGORY_LEANBACK_LAUNCHER = "android.intent.category.LEANBACK_LAUNCHER";
    @Deprecated public static final String EXTRA_CHANGED_PACKAGE_LIST = "android.intent.extra.changed_package_list";
    @Deprecated public static final String EXTRA_CHANGED_UID_LIST = "android.intent.extra.changed_uid_list";
    public static final String EXTRA_HTML_TEXT = "android.intent.extra.HTML_TEXT";
    public static final String EXTRA_START_PLAYBACK = "android.intent.extra.START_PLAYBACK";
    @Deprecated public static final int FLAG_ACTIVITY_CLEAR_TASK = 0x8000;
    @Deprecated public static final int FLAG_ACTIVITY_TASK_ON_HOME = 0x4000;
    private static final IntentCompatBaseImpl IMPL;

    static {
        IntentCompat.IMPL = Build$VERSION.SDK_INT >= 15 ? new IntentCompatApi15Impl() : new IntentCompatBaseImpl();
    }

    private IntentCompat() {
        super();
    }

    @Deprecated public static Intent makeMainActivity(ComponentName arg0) {
        return Intent.makeMainActivity(arg0);
    }

    public static Intent makeMainSelectorActivity(String arg1, String arg2) {
        return IntentCompat.IMPL.makeMainSelectorActivity(arg1, arg2);
    }

    @Deprecated public static Intent makeRestartActivityTask(ComponentName arg0) {
        return Intent.makeRestartActivityTask(arg0);
    }
}

