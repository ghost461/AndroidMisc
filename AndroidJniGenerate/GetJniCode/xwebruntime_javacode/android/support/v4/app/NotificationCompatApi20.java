package android.support.v4.app;

import android.app.Notification$Action$Builder;
import android.app.Notification$Action;
import android.app.Notification$Builder;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.widget.RemoteViews;
import java.util.ArrayList;

@RequiresApi(value=20) class NotificationCompatApi20 {
    public class Builder implements NotificationBuilderWithActions, NotificationBuilderWithBuilderAccessor {
        private Notification$Builder b;
        private RemoteViews mBigContentView;
        private RemoteViews mContentView;
        private Bundle mExtras;
        private int mGroupAlertBehavior;

        public Builder(Context arg9, Notification arg10, CharSequence arg11, CharSequence arg12, CharSequence arg13, RemoteViews arg14, int arg15, PendingIntent arg16, PendingIntent arg17, Bitmap arg18, int arg19, int arg20, boolean arg21, boolean arg22, boolean arg23, int arg24, CharSequence arg25, boolean arg26, ArrayList arg27, Bundle arg28, String arg29, boolean arg30, String arg31, RemoteViews arg32, RemoteViews arg33, int arg34) {
            PendingIntent v1_1;
            Builder v0 = this;
            Notification v1 = arg10;
            ArrayList v2 = arg27;
            Bundle v3 = arg28;
            super();
            Notification$Builder v4 = new Notification$Builder(arg9).setWhen(v1.when).setShowWhen(arg22).setSmallIcon(v1.icon, v1.iconLevel).setContent(v1.contentView).setTicker(v1.tickerText, arg14).setSound(v1.sound, v1.audioStreamType).setVibrate(v1.vibrate).setLights(v1.ledARGB, v1.ledOnMS, v1.ledOffMS);
            boolean v6 = false;
            boolean v5 = (v1.flags & 2) != 0 ? true : false;
            v4 = v4.setOngoing(v5);
            v5 = (v1.flags & 8) != 0 ? true : false;
            v4 = v4.setOnlyAlertOnce(v5);
            v5 = (v1.flags & 16) != 0 ? true : false;
            v4 = v4.setAutoCancel(v5).setDefaults(v1.defaults).setContentTitle(arg11).setContentText(arg12).setSubText(arg25).setContentInfo(arg13).setContentIntent(arg16).setDeleteIntent(v1.deleteIntent);
            if((v1.flags & 0x80) != 0) {
                v1_1 = arg17;
                v6 = true;
            }
            else {
                v1_1 = arg17;
            }

            v0.b = v4.setFullScreenIntent(v1_1, v6).setLargeIcon(arg18).setNumber(arg15).setUsesChronometer(arg23).setPriority(arg24).setProgress(arg19, arg20, arg21).setLocalOnly(arg26).setGroup(arg29).setGroupSummary(arg30).setSortKey(arg31);
            v0.mExtras = new Bundle();
            if(v3 != null) {
                v0.mExtras.putAll(v3);
            }

            if(v2 != null && !arg27.isEmpty()) {
                v0.mExtras.putStringArray("android.people", v2.toArray(new String[arg27.size()]));
            }

            v0.mContentView = arg32;
            v0.mBigContentView = arg33;
            v0.mGroupAlertBehavior = arg34;
        }

        public void addAction(Action arg2) {
            NotificationCompatApi20.addAction(this.b, arg2);
        }

        public Notification build() {
            this.b.setExtras(this.mExtras);
            Notification v0 = this.b.build();
            if(this.mContentView != null) {
                v0.contentView = this.mContentView;
            }

            if(this.mBigContentView != null) {
                v0.bigContentView = this.mBigContentView;
            }

            if(this.mGroupAlertBehavior != 0) {
                if(v0.getGroup() != null && (v0.flags & 0x200) != 0 && this.mGroupAlertBehavior == 2) {
                    this.removeSoundAndVibration(v0);
                }

                if(v0.getGroup() == null) {
                    return v0;
                }

                if((v0.flags & 0x200) != 0) {
                    return v0;
                }

                if(this.mGroupAlertBehavior != 1) {
                    return v0;
                }

                this.removeSoundAndVibration(v0);
            }

            return v0;
        }

        public Notification$Builder getBuilder() {
            return this.b;
        }

        private void removeSoundAndVibration(Notification arg2) {
            arg2.sound = null;
            arg2.vibrate = null;
            arg2.defaults &= -2;
            arg2.defaults &= -3;
        }
    }

    NotificationCompatApi20() {
        super();
    }

    public static void addAction(Notification$Builder arg5, Action arg6) {
        Notification$Action$Builder v0 = new Notification$Action$Builder(arg6.getIcon(), arg6.getTitle(), arg6.getActionIntent());
        if(arg6.getRemoteInputs() != null) {
            RemoteInput[] v1 = RemoteInputCompatApi20.fromCompat(arg6.getRemoteInputs());
            int v2 = v1.length;
            int v3;
            for(v3 = 0; v3 < v2; ++v3) {
                v0.addRemoteInput(v1[v3]);
            }
        }

        Bundle v1_1 = arg6.getExtras() != null ? new Bundle(arg6.getExtras()) : new Bundle();
        v1_1.putBoolean("android.support.allowGeneratedReplies", arg6.getAllowGeneratedReplies());
        v0.addExtras(v1_1);
        arg5.addAction(v0.build());
    }

    public static Action getAction(Notification arg0, int arg1, Factory arg2, android.support.v4.app.RemoteInputCompatBase$RemoteInput$Factory arg3) {
        return NotificationCompatApi20.getActionCompatFromAction(arg0.actions[arg1], arg2, arg3);
    }

    private static Action getActionCompatFromAction(Notification$Action arg9, Factory arg10, android.support.v4.app.RemoteInputCompatBase$RemoteInput$Factory arg11) {
        return arg10.build(arg9.icon, arg9.title, arg9.actionIntent, arg9.getExtras(), RemoteInputCompatApi20.toCompat(arg9.getRemoteInputs(), arg11), null, arg9.getExtras().getBoolean("android.support.allowGeneratedReplies"));
    }

    private static Notification$Action getActionFromActionCompat(Action arg4) {
        Notification$Action$Builder v0 = new Notification$Action$Builder(arg4.getIcon(), arg4.getTitle(), arg4.getActionIntent());
        Bundle v1 = arg4.getExtras() != null ? new Bundle(arg4.getExtras()) : new Bundle();
        v1.putBoolean("android.support.allowGeneratedReplies", arg4.getAllowGeneratedReplies());
        v0.addExtras(v1);
        android.support.v4.app.RemoteInputCompatBase$RemoteInput[] v4 = arg4.getRemoteInputs();
        if(v4 != null) {
            RemoteInput[] v4_1 = RemoteInputCompatApi20.fromCompat(v4);
            int v1_1 = v4_1.length;
            int v2;
            for(v2 = 0; v2 < v1_1; ++v2) {
                v0.addRemoteInput(v4_1[v2]);
            }
        }

        return v0.build();
    }

    public static Action[] getActionsFromParcelableArrayList(ArrayList arg3, Factory arg4, android.support.v4.app.RemoteInputCompatBase$RemoteInput$Factory arg5) {
        if(arg3 == null) {
            return null;
        }

        Action[] v0 = arg4.newArray(arg3.size());
        int v1;
        for(v1 = 0; v1 < v0.length; ++v1) {
            v0[v1] = NotificationCompatApi20.getActionCompatFromAction(arg3.get(v1), arg4, arg5);
        }

        return v0;
    }

    public static ArrayList getParcelableArrayListForActions(Action[] arg4) {
        if(arg4 == null) {
            return null;
        }

        ArrayList v0 = new ArrayList(arg4.length);
        int v1 = arg4.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            v0.add(NotificationCompatApi20.getActionFromActionCompat(arg4[v2]));
        }

        return v0;
    }
}

