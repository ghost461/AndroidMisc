package android.support.v4.app;

import android.app.Notification$Action$Builder;
import android.app.Notification$Action;
import android.app.Notification$Builder;
import android.app.Notification$MessagingStyle$Message;
import android.app.Notification$MessagingStyle;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.widget.RemoteViews;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RequiresApi(value=24) class NotificationCompatApi24 {
    public class Builder implements NotificationBuilderWithActions, NotificationBuilderWithBuilderAccessor {
        private Notification$Builder b;
        private int mGroupAlertBehavior;

        public Builder(Context arg10, Notification arg11, CharSequence arg12, CharSequence arg13, CharSequence arg14, RemoteViews arg15, int arg16, PendingIntent arg17, PendingIntent arg18, Bitmap arg19, int arg20, int arg21, boolean arg22, boolean arg23, boolean arg24, int arg25, CharSequence arg26, boolean arg27, String arg28, ArrayList arg29, Bundle arg30, int arg31, int arg32, Notification arg33, String arg34, boolean arg35, String arg36, CharSequence[] arg37, RemoteViews arg38, RemoteViews arg39, RemoteViews arg40, int arg41) {
            PendingIntent v1_1;
            Builder v0 = this;
            Notification v1 = arg11;
            RemoteViews v2 = arg38;
            RemoteViews v3 = arg39;
            RemoteViews v4 = arg40;
            super();
            Notification$Builder v5 = new Notification$Builder(arg10).setWhen(v1.when).setShowWhen(arg23).setSmallIcon(v1.icon, v1.iconLevel).setContent(v1.contentView).setTicker(v1.tickerText, arg15).setSound(v1.sound, v1.audioStreamType).setVibrate(v1.vibrate).setLights(v1.ledARGB, v1.ledOnMS, v1.ledOffMS);
            boolean v7 = false;
            boolean v6 = (v1.flags & 2) != 0 ? true : false;
            v5 = v5.setOngoing(v6);
            v6 = (v1.flags & 8) != 0 ? true : false;
            v5 = v5.setOnlyAlertOnce(v6);
            v6 = (v1.flags & 16) != 0 ? true : false;
            v5 = v5.setAutoCancel(v6).setDefaults(v1.defaults).setContentTitle(arg12).setContentText(arg13).setSubText(arg26).setContentInfo(arg14).setContentIntent(arg17).setDeleteIntent(v1.deleteIntent);
            if((v1.flags & 0x80) != 0) {
                v1_1 = arg18;
                v7 = true;
            }
            else {
                v1_1 = arg18;
            }

            v0.b = v5.setFullScreenIntent(v1_1, v7).setLargeIcon(arg19).setNumber(arg16).setUsesChronometer(arg24).setPriority(arg25).setProgress(arg20, arg21, arg22).setLocalOnly(arg27).setExtras(arg30).setGroup(arg34).setGroupSummary(arg35).setSortKey(arg36).setCategory(arg28).setColor(arg31).setVisibility(arg32).setPublicVersion(arg33).setRemoteInputHistory(arg37);
            if(v2 != null) {
                v0.b.setCustomContentView(v2);
            }

            if(v3 != null) {
                v0.b.setCustomBigContentView(v3);
            }

            if(v4 != null) {
                v0.b.setCustomHeadsUpContentView(v4);
            }

            Iterator v1_2 = arg29.iterator();
            while(v1_2.hasNext()) {
                v0.b.addPerson(v1_2.next());
            }

            v0.mGroupAlertBehavior = arg41;
        }

        public void addAction(Action arg2) {
            NotificationCompatApi24.addAction(this.b, arg2);
        }

        public Notification build() {
            Notification v0 = this.b.build();
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

    NotificationCompatApi24() {
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
        v0.setAllowGeneratedReplies(arg6.getAllowGeneratedReplies());
        v0.addExtras(v1_1);
        arg5.addAction(v0.build());
    }

    public static void addMessagingStyle(NotificationBuilderWithBuilderAccessor arg5, CharSequence arg6, CharSequence arg7, List arg8, List arg9, List arg10, List arg11, List arg12) {
        Notification$MessagingStyle v6 = new Notification$MessagingStyle(arg6).setConversationTitle(arg7);
        int v7;
        for(v7 = 0; v7 < arg8.size(); ++v7) {
            Notification$MessagingStyle$Message v0 = new Notification$MessagingStyle$Message(arg8.get(v7), arg9.get(v7).longValue(), arg10.get(v7));
            if(arg11.get(v7) != null) {
                v0.setData(arg11.get(v7), arg12.get(v7));
            }

            v6.addMessage(v0);
        }

        v6.setBuilder(arg5.getBuilder());
    }

    public static Action getAction(Notification arg0, int arg1, Factory arg2, android.support.v4.app.RemoteInputCompatBase$RemoteInput$Factory arg3) {
        return NotificationCompatApi24.getActionCompatFromAction(arg0.actions[arg1], arg2, arg3);
    }

    private static Action getActionCompatFromAction(Notification$Action arg9, Factory arg10, android.support.v4.app.RemoteInputCompatBase$RemoteInput$Factory arg11) {
        android.support.v4.app.RemoteInputCompatBase$RemoteInput[] v6 = RemoteInputCompatApi20.toCompat(arg9.getRemoteInputs(), arg11);
        boolean v8 = (arg9.getExtras().getBoolean("android.support.allowGeneratedReplies")) || (arg9.getAllowGeneratedReplies()) ? true : false;
        return arg10.build(arg9.icon, arg9.title, arg9.actionIntent, arg9.getExtras(), v6, null, v8);
    }

    private static Notification$Action getActionFromActionCompat(Action arg4) {
        Notification$Action$Builder v0 = new Notification$Action$Builder(arg4.getIcon(), arg4.getTitle(), arg4.getActionIntent());
        Bundle v1 = arg4.getExtras() != null ? new Bundle(arg4.getExtras()) : new Bundle();
        v1.putBoolean("android.support.allowGeneratedReplies", arg4.getAllowGeneratedReplies());
        v0.setAllowGeneratedReplies(arg4.getAllowGeneratedReplies());
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
            v0[v1] = NotificationCompatApi24.getActionCompatFromAction(arg3.get(v1), arg4, arg5);
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
            v0.add(NotificationCompatApi24.getActionFromActionCompat(arg4[v2]));
        }

        return v0;
    }
}

