package android.support.v4.app;

import android.app.Notification$BigPictureStyle;
import android.app.Notification$BigTextStyle;
import android.app.Notification$Builder;
import android.app.Notification$InboxStyle;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.util.SparseArray;
import android.widget.RemoteViews;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RequiresApi(value=16) class NotificationCompatJellybean {
    public class Builder implements NotificationBuilderWithActions, NotificationBuilderWithBuilderAccessor {
        private Notification$Builder b;
        private List mActionExtrasList;
        private RemoteViews mBigContentView;
        private RemoteViews mContentView;
        private final Bundle mExtras;

        public Builder(Context arg10, Notification arg11, CharSequence arg12, CharSequence arg13, CharSequence arg14, RemoteViews arg15, int arg16, PendingIntent arg17, PendingIntent arg18, Bitmap arg19, int arg20, int arg21, boolean arg22, boolean arg23, int arg24, CharSequence arg25, boolean arg26, Bundle arg27, String arg28, boolean arg29, String arg30, RemoteViews arg31, RemoteViews arg32) {
            PendingIntent v1_1;
            Builder v0 = this;
            Notification v1 = arg11;
            Bundle v2 = arg27;
            String v3 = arg28;
            String v4 = arg30;
            super();
            v0.mActionExtrasList = new ArrayList();
            Notification$Builder v5 = new Notification$Builder(arg10).setWhen(v1.when).setSmallIcon(v1.icon, v1.iconLevel).setContent(v1.contentView).setTicker(v1.tickerText, arg15).setSound(v1.sound, v1.audioStreamType).setVibrate(v1.vibrate).setLights(v1.ledARGB, v1.ledOnMS, v1.ledOffMS);
            boolean v7 = false;
            boolean v6 = (v1.flags & 2) != 0 ? true : false;
            v5 = v5.setOngoing(v6);
            v6 = (v1.flags & 8) != 0 ? true : false;
            v5 = v5.setOnlyAlertOnce(v6);
            v6 = (v1.flags & 16) != 0 ? true : false;
            v5 = v5.setAutoCancel(v6).setDefaults(v1.defaults).setContentTitle(arg12).setContentText(arg13).setSubText(arg25).setContentInfo(arg14).setContentIntent(arg17).setDeleteIntent(v1.deleteIntent);
            if((v1.flags & 0x80) != 0) {
                v1_1 = arg18;
                v7 = true;
            }
            else {
                v1_1 = arg18;
            }

            v0.b = v5.setFullScreenIntent(v1_1, v7).setLargeIcon(arg19).setNumber(arg16).setUsesChronometer(arg23).setPriority(arg24).setProgress(arg20, arg21, arg22);
            v0.mExtras = new Bundle();
            if(v2 != null) {
                v0.mExtras.putAll(v2);
            }

            if(arg26) {
                v0.mExtras.putBoolean("android.support.localOnly", true);
            }

            if(v3 != null) {
                v0.mExtras.putString("android.support.groupKey", v3);
                if(arg29) {
                    v0.mExtras.putBoolean("android.support.isGroupSummary", true);
                }
                else {
                    v0.mExtras.putBoolean("android.support.useSideChannel", true);
                }
            }

            if(v4 != null) {
                v0.mExtras.putString("android.support.sortKey", v4);
            }

            v0.mContentView = arg31;
            v0.mBigContentView = arg32;
        }

        public void addAction(Action arg3) {
            this.mActionExtrasList.add(NotificationCompatJellybean.writeActionAndGetExtras(this.b, arg3));
        }

        public Notification build() {
            Notification v0 = this.b.build();
            Bundle v1 = NotificationCompatJellybean.getExtras(v0);
            Bundle v2 = new Bundle(this.mExtras);
            Iterator v3 = this.mExtras.keySet().iterator();
            while(v3.hasNext()) {
                Object v4 = v3.next();
                if(!v1.containsKey(((String)v4))) {
                    continue;
                }

                v2.remove(((String)v4));
            }

            v1.putAll(v2);
            SparseArray v1_1 = NotificationCompatJellybean.buildActionExtrasMap(this.mActionExtrasList);
            if(v1_1 != null) {
                NotificationCompatJellybean.getExtras(v0).putSparseParcelableArray("android.support.actionExtras", v1_1);
            }

            if(this.mContentView != null) {
                v0.contentView = this.mContentView;
            }

            if(this.mBigContentView != null) {
                v0.bigContentView = this.mBigContentView;
            }

            return v0;
        }

        public Notification$Builder getBuilder() {
            return this.b;
        }
    }

    static final String EXTRA_ALLOW_GENERATED_REPLIES = "android.support.allowGeneratedReplies";
    static final String EXTRA_DATA_ONLY_REMOTE_INPUTS = "android.support.dataRemoteInputs";
    private static final String KEY_ACTION_INTENT = "actionIntent";
    private static final String KEY_DATA_ONLY_REMOTE_INPUTS = "dataOnlyRemoteInputs";
    private static final String KEY_EXTRAS = "extras";
    private static final String KEY_ICON = "icon";
    private static final String KEY_REMOTE_INPUTS = "remoteInputs";
    private static final String KEY_TITLE = "title";
    public static final String TAG = "NotificationCompat";
    private static Class sActionClass;
    private static Field sActionIconField;
    private static Field sActionIntentField;
    private static Field sActionTitleField;
    private static boolean sActionsAccessFailed;
    private static Field sActionsField;
    private static final Object sActionsLock;
    private static Field sExtrasField;
    private static boolean sExtrasFieldAccessFailed;
    private static final Object sExtrasLock;

    static {
        NotificationCompatJellybean.sExtrasLock = new Object();
        NotificationCompatJellybean.sActionsLock = new Object();
    }

    NotificationCompatJellybean() {
        super();
    }

    public static void addBigPictureStyle(NotificationBuilderWithBuilderAccessor arg1, CharSequence arg2, boolean arg3, CharSequence arg4, Bitmap arg5, Bitmap arg6, boolean arg7) {
        Notification$BigPictureStyle v1 = new Notification$BigPictureStyle(arg1.getBuilder()).setBigContentTitle(arg2).bigPicture(arg5);
        if(arg7) {
            v1.bigLargeIcon(arg6);
        }

        if(arg3) {
            v1.setSummaryText(arg4);
        }
    }

    public static void addBigTextStyle(NotificationBuilderWithBuilderAccessor arg1, CharSequence arg2, boolean arg3, CharSequence arg4, CharSequence arg5) {
        Notification$BigTextStyle v1 = new Notification$BigTextStyle(arg1.getBuilder()).setBigContentTitle(arg2).bigText(arg5);
        if(arg3) {
            v1.setSummaryText(arg4);
        }
    }

    public static void addInboxStyle(NotificationBuilderWithBuilderAccessor arg1, CharSequence arg2, boolean arg3, CharSequence arg4, ArrayList arg5) {
        Notification$InboxStyle v1 = new Notification$InboxStyle(arg1.getBuilder()).setBigContentTitle(arg2);
        if(arg3) {
            v1.setSummaryText(arg4);
        }

        Iterator v2 = arg5.iterator();
        while(v2.hasNext()) {
            v1.addLine(v2.next());
        }
    }

    public static SparseArray buildActionExtrasMap(List arg4) {
        int v0 = arg4.size();
        SparseArray v1 = null;
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            Object v3 = arg4.get(v2);
            if(v3 != null) {
                if(v1 == null) {
                    v1 = new SparseArray();
                }

                v1.put(v2, v3);
            }
        }

        return v1;
    }

    private static boolean ensureActionReflectionReadyLocked() {
        if(NotificationCompatJellybean.sActionsAccessFailed) {
            return 0;
        }

        try {
            if(NotificationCompatJellybean.sActionsField != null) {
                goto label_40;
            }

            NotificationCompatJellybean.sActionClass = Class.forName("android.app.Notification$Action");
            NotificationCompatJellybean.sActionIconField = NotificationCompatJellybean.sActionClass.getDeclaredField("icon");
            NotificationCompatJellybean.sActionTitleField = NotificationCompatJellybean.sActionClass.getDeclaredField("title");
            NotificationCompatJellybean.sActionIntentField = NotificationCompatJellybean.sActionClass.getDeclaredField("actionIntent");
            NotificationCompatJellybean.sActionsField = Notification.class.getDeclaredField("actions");
            NotificationCompatJellybean.sActionsField.setAccessible(true);
        }
        catch(NoSuchFieldException v1) {
            Log.e("NotificationCompat", "Unable to access notification actions", ((Throwable)v1));
            NotificationCompatJellybean.sActionsAccessFailed = true;
        }
        catch(ClassNotFoundException v1_1) {
            Log.e("NotificationCompat", "Unable to access notification actions", ((Throwable)v1_1));
            NotificationCompatJellybean.sActionsAccessFailed = true;
        }

    label_40:
        return 1 ^ NotificationCompatJellybean.sActionsAccessFailed;
    }

    public static Action getAction(Notification arg8, int arg9, Factory arg10, android.support.v4.app.RemoteInputCompatBase$RemoteInput$Factory arg11) {
        Action v8_3;
        Object v7;
        Object v0 = NotificationCompatJellybean.sActionsLock;
        __monitor_enter(v0);
        Action v1 = null;
        try {
            Object[] v2 = NotificationCompatJellybean.getActionObjectsLocked(arg8);
            if(v2 == null) {
                goto label_36;
            }

            Object v2_1 = v2[arg9];
            Bundle v8_1 = NotificationCompatJellybean.getExtras(arg8);
            if(v8_1 != null) {
                SparseArray v8_2 = v8_1.getSparseParcelableArray("android.support.actionExtras");
                if(v8_2 != null) {
                    v7 = v8_2.get(arg9);
                }
                else {
                    goto label_14;
                }
            }
            else {
            label_14:
                Bundle v7_1 = ((Bundle)v1);
            }

            v8_3 = NotificationCompatJellybean.readAction(arg10, arg11, NotificationCompatJellybean.sActionIconField.getInt(v2_1), NotificationCompatJellybean.sActionTitleField.get(v2_1), NotificationCompatJellybean.sActionIntentField.get(v2_1), ((Bundle)v7));
        }
        catch(IllegalAccessException v8) {
            Log.e("NotificationCompat", "Unable to access notification actions", ((Throwable)v8));
            NotificationCompatJellybean.sActionsAccessFailed = true;
            goto label_36;
        }

        __monitor_exit(v0);
        return v8_3;
    label_36:
        __monitor_exit(v0);
        return v1;
        __monitor_exit(v0);
        throw v8_4;
    }

    public static int getActionCount(Notification arg1) {
        Object v0 = NotificationCompatJellybean.sActionsLock;
        __monitor_enter(v0);
        try {
            Object[] v1_1 = NotificationCompatJellybean.getActionObjectsLocked(arg1);
            int v1_2 = v1_1 != null ? v1_1.length : 0;
            __monitor_exit(v0);
            return v1_2;
        label_10:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_10;
        }

        throw v1;
    }

    private static Action getActionFromBundle(Bundle arg10, Factory arg11, android.support.v4.app.RemoteInputCompatBase$RemoteInput$Factory arg12) {
        Bundle v0 = arg10.getBundle("extras");
        boolean v9 = v0 != null ? v0.getBoolean("android.support.allowGeneratedReplies", false) : false;
        return arg11.build(arg10.getInt("icon"), arg10.getCharSequence("title"), arg10.getParcelable("actionIntent"), arg10.getBundle("extras"), RemoteInputCompatJellybean.fromBundleArray(BundleUtil.getBundleArrayFromBundle(arg10, "remoteInputs"), arg12), RemoteInputCompatJellybean.fromBundleArray(BundleUtil.getBundleArrayFromBundle(arg10, "dataOnlyRemoteInputs"), arg12), v9);
    }

    private static Object[] getActionObjectsLocked(Notification arg4) {
        Object v4_2;
        Object v0 = NotificationCompatJellybean.sActionsLock;
        __monitor_enter(v0);
        try {
            Object[] v2 = null;
            if(!NotificationCompatJellybean.ensureActionReflectionReadyLocked()) {
                __monitor_exit(v0);
                return v2;
            }

            try {
                v4_2 = NotificationCompatJellybean.sActionsField.get(arg4);
                __monitor_exit(v0);
            }
            catch(IllegalAccessException v4_1) {
                Log.e("NotificationCompat", "Unable to access notification actions", ((Throwable)v4_1));
                NotificationCompatJellybean.sActionsAccessFailed = true;
                __monitor_exit(v0);
                return v2;
            }

            return ((Object[])v4_2);
        label_20:
            __monitor_exit(v0);
        }
        catch(Throwable v4) {
            goto label_20;
        }

        throw v4;
    }

    public static Action[] getActionsFromParcelableArrayList(ArrayList arg3, Factory arg4, android.support.v4.app.RemoteInputCompatBase$RemoteInput$Factory arg5) {
        if(arg3 == null) {
            return null;
        }

        Action[] v0 = arg4.newArray(arg3.size());
        int v1;
        for(v1 = 0; v1 < v0.length; ++v1) {
            v0[v1] = NotificationCompatJellybean.getActionFromBundle(arg3.get(v1), arg4, arg5);
        }

        return v0;
    }

    private static Bundle getBundleForAction(Action arg4) {
        Bundle v0 = new Bundle();
        v0.putInt("icon", arg4.getIcon());
        v0.putCharSequence("title", arg4.getTitle());
        v0.putParcelable("actionIntent", arg4.getActionIntent());
        Bundle v1 = arg4.getExtras() != null ? new Bundle(arg4.getExtras()) : new Bundle();
        v1.putBoolean("android.support.allowGeneratedReplies", arg4.getAllowGeneratedReplies());
        v0.putBundle("extras", v1);
        v0.putParcelableArray("remoteInputs", RemoteInputCompatJellybean.toBundleArray(arg4.getRemoteInputs()));
        return v0;
    }

    public static Bundle getExtras(Notification arg6) {
        Object v3_1;
        Field v3;
        Bundle v2;
        Object v0 = NotificationCompatJellybean.sExtrasLock;
        __monitor_enter(v0);
        try {
            v2 = null;
            if(!NotificationCompatJellybean.sExtrasFieldAccessFailed) {
                goto label_8;
            }

            __monitor_exit(v0);
            return v2;
        }
        catch(Throwable v6) {
            goto label_47;
        }

        try {
        label_8:
            if(NotificationCompatJellybean.sExtrasField == null) {
                v3 = Notification.class.getDeclaredField("extras");
                if(!Bundle.class.isAssignableFrom(v3.getType())) {
                    Log.e("NotificationCompat", "Notification.extras field is not of type Bundle");
                    NotificationCompatJellybean.sExtrasFieldAccessFailed = true;
                    goto label_21;
                }
                else {
                    goto label_23;
                }
            }

            goto label_25;
        }
        catch(IllegalAccessException v6_1) {
            goto label_42;
        }
        catch(NoSuchFieldException v6_2) {
            goto label_37;
        }
        catch(Throwable v6) {
            goto label_47;
        }

        try {
        label_21:
            __monitor_exit(v0);
            return v2;
        }
        catch(Throwable v6) {
            goto label_47;
        }

        try {
        label_23:
            v3.setAccessible(true);
            NotificationCompatJellybean.sExtrasField = v3;
        label_25:
            v3_1 = NotificationCompatJellybean.sExtrasField.get(arg6);
            if(v3_1 == null) {
                Bundle v3_2 = new Bundle();
                NotificationCompatJellybean.sExtrasField.set(arg6, v3_2);
            }

            goto label_32;
        }
        catch(Throwable v6) {
        label_48:
            throw v6;
        }
        catch(NoSuchFieldException v6_2) {
        }
        catch(IllegalAccessException v6_1) {
            try {
            label_42:
                Log.e("NotificationCompat", "Unable to access notification extras", ((Throwable)v6_1));
                goto label_43;
            label_37:
                Log.e("NotificationCompat", "Unable to access notification extras", ((Throwable)v6_2));
            label_43:
                NotificationCompatJellybean.sExtrasFieldAccessFailed = true;
                __monitor_exit(v0);
                return v2;
            label_32:
                __monitor_exit(v0);
                return ((Bundle)v3_1);
            label_47:
                __monitor_exit(v0);
                goto label_48;
            }
            catch(Throwable v6) {
                goto label_47;
            }
        }
    }

    public static ArrayList getParcelableArrayListForActions(Action[] arg4) {
        if(arg4 == null) {
            return null;
        }

        ArrayList v0 = new ArrayList(arg4.length);
        int v1 = arg4.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            v0.add(NotificationCompatJellybean.getBundleForAction(arg4[v2]));
        }

        return v0;
    }

    public static Action readAction(Factory arg10, android.support.v4.app.RemoteInputCompatBase$RemoteInput$Factory arg11, int arg12, CharSequence arg13, PendingIntent arg14, Bundle arg15) {
        boolean v9;
        RemoteInput[] v7;
        RemoteInput[] v8;
        RemoteInput[] v0 = null;
        if(arg15 != null) {
            v0 = RemoteInputCompatJellybean.fromBundleArray(BundleUtil.getBundleArrayFromBundle(arg15, "android.support.remoteInputs"), arg11);
            v8 = RemoteInputCompatJellybean.fromBundleArray(BundleUtil.getBundleArrayFromBundle(arg15, "android.support.dataRemoteInputs"), arg11);
            v7 = v0;
            v9 = arg15.getBoolean("android.support.allowGeneratedReplies");
        }
        else {
            v7 = v0;
            v8 = v7;
            v9 = false;
        }

        return arg10.build(arg12, arg13, arg14, arg15, v7, v8, v9);
    }

    public static Bundle writeActionAndGetExtras(Notification$Builder arg3, Action arg4) {
        arg3.addAction(arg4.getIcon(), arg4.getTitle(), arg4.getActionIntent());
        Bundle v3 = new Bundle(arg4.getExtras());
        if(arg4.getRemoteInputs() != null) {
            v3.putParcelableArray("android.support.remoteInputs", RemoteInputCompatJellybean.toBundleArray(arg4.getRemoteInputs()));
        }

        if(arg4.getDataOnlyRemoteInputs() != null) {
            v3.putParcelableArray("android.support.dataRemoteInputs", RemoteInputCompatJellybean.toBundleArray(arg4.getDataOnlyRemoteInputs()));
        }

        v3.putBoolean("android.support.allowGeneratedReplies", arg4.getAllowGeneratedReplies());
        return v3;
    }
}

