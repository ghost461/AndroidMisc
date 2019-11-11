package android.support.v4.app;

import android.app.Notification$Builder;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.RemoteInput$Builder;
import android.app.RemoteInput;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;
import android.widget.RemoteViews;
import java.util.ArrayList;
import java.util.Iterator;

@RequiresApi(value=21) class NotificationCompatApi21 {
    public class Builder implements NotificationBuilderWithActions, NotificationBuilderWithBuilderAccessor {
        private Notification$Builder b;
        private RemoteViews mBigContentView;
        private RemoteViews mContentView;
        private Bundle mExtras;
        private int mGroupAlertBehavior;
        private RemoteViews mHeadsUpContentView;

        public Builder(Context arg8, Notification arg9, CharSequence arg10, CharSequence arg11, CharSequence arg12, RemoteViews arg13, int arg14, PendingIntent arg15, PendingIntent arg16, Bitmap arg17, int arg18, int arg19, boolean arg20, boolean arg21, boolean arg22, int arg23, CharSequence arg24, boolean arg25, String arg26, ArrayList arg27, Bundle arg28, int arg29, int arg30, Notification arg31, String arg32, boolean arg33, String arg34, RemoteViews arg35, RemoteViews arg36, RemoteViews arg37, int arg38) {
            PendingIntent v1_1;
            Builder v0 = this;
            Notification v1 = arg9;
            Bundle v2 = arg28;
            super();
            Notification$Builder v3 = new Notification$Builder(arg8).setWhen(v1.when).setShowWhen(arg21).setSmallIcon(v1.icon, v1.iconLevel).setContent(v1.contentView).setTicker(v1.tickerText, arg13).setSound(v1.sound, v1.audioStreamType).setVibrate(v1.vibrate).setLights(v1.ledARGB, v1.ledOnMS, v1.ledOffMS);
            boolean v5 = false;
            boolean v4 = (v1.flags & 2) != 0 ? true : false;
            v3 = v3.setOngoing(v4);
            v4 = (v1.flags & 8) != 0 ? true : false;
            v3 = v3.setOnlyAlertOnce(v4);
            v4 = (v1.flags & 16) != 0 ? true : false;
            v3 = v3.setAutoCancel(v4).setDefaults(v1.defaults).setContentTitle(arg10).setContentText(arg11).setSubText(arg24).setContentInfo(arg12).setContentIntent(arg15).setDeleteIntent(v1.deleteIntent);
            if((v1.flags & 0x80) != 0) {
                v1_1 = arg16;
                v5 = true;
            }
            else {
                v1_1 = arg16;
            }

            v0.b = v3.setFullScreenIntent(v1_1, v5).setLargeIcon(arg17).setNumber(arg14).setUsesChronometer(arg22).setPriority(arg23).setProgress(arg18, arg19, arg20).setLocalOnly(arg25).setGroup(arg32).setGroupSummary(arg33).setSortKey(arg34).setCategory(arg26).setColor(arg29).setVisibility(arg30).setPublicVersion(arg31);
            v0.mExtras = new Bundle();
            if(v2 != null) {
                v0.mExtras.putAll(v2);
            }

            Iterator v1_2 = arg27.iterator();
            while(v1_2.hasNext()) {
                v0.b.addPerson(v1_2.next());
            }

            v0.mContentView = arg35;
            v0.mBigContentView = arg36;
            v0.mHeadsUpContentView = arg37;
            v0.mGroupAlertBehavior = arg38;
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

            if(this.mHeadsUpContentView != null) {
                v0.headsUpContentView = this.mHeadsUpContentView;
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

    private static final String KEY_AUTHOR = "author";
    private static final String KEY_MESSAGES = "messages";
    private static final String KEY_ON_READ = "on_read";
    private static final String KEY_ON_REPLY = "on_reply";
    private static final String KEY_PARTICIPANTS = "participants";
    private static final String KEY_REMOTE_INPUT = "remote_input";
    private static final String KEY_TEXT = "text";
    private static final String KEY_TIMESTAMP = "timestamp";

    NotificationCompatApi21() {
        super();
    }

    private static RemoteInput fromCompatRemoteInput(android.support.v4.app.RemoteInputCompatBase$RemoteInput arg2) {
        return new RemoteInput$Builder(arg2.getResultKey()).setLabel(arg2.getLabel()).setChoices(arg2.getChoices()).setAllowFreeFormInput(arg2.getAllowFreeFormInput()).addExtras(arg2.getExtras()).build();
    }

    static Bundle getBundleForUnreadConversation(UnreadConversation arg7) {
        Bundle v0 = null;
        if(arg7 == null) {
            return v0;
        }

        Bundle v1 = new Bundle();
        int v3 = 0;
        if(arg7.getParticipants() != null && arg7.getParticipants().length > 1) {
            String v0_1 = arg7.getParticipants()[0];
        }

        Parcelable[] v2 = new Parcelable[arg7.getMessages().length];
        while(v3 < v2.length) {
            Bundle v4 = new Bundle();
            v4.putString("text", arg7.getMessages()[v3]);
            v4.putString("author", ((String)v0));
            v2[v3] = ((Parcelable)v4);
            ++v3;
        }

        v1.putParcelableArray("messages", v2);
        android.support.v4.app.RemoteInputCompatBase$RemoteInput v0_2 = arg7.getRemoteInput();
        if(v0_2 != null) {
            v1.putParcelable("remote_input", NotificationCompatApi21.fromCompatRemoteInput(v0_2));
        }

        v1.putParcelable("on_reply", arg7.getReplyPendingIntent());
        v1.putParcelable("on_read", arg7.getReadPendingIntent());
        v1.putStringArray("participants", arg7.getParticipants());
        v1.putLong("timestamp", arg7.getLatestTimestamp());
        return v1;
    }

    static UnreadConversation getUnreadConversationFromBundle(Bundle arg13, Factory arg14, android.support.v4.app.RemoteInputCompatBase$RemoteInput$Factory arg15) {
        android.support.v4.app.RemoteInputCompatBase$RemoteInput v0_1;
        String[] v6;
        UnreadConversation v0 = null;
        if(arg13 == null) {
            return v0;
        }

        Parcelable[] v1 = arg13.getParcelableArray("messages");
        if(v1 != null) {
            String[] v3 = new String[v1.length];
            int v4 = 0;
            int v5 = 0;
            while(true) {
                if(v5 >= v3.length) {
                    break;
                }
                else if(!(v1[v5] instanceof Bundle)) {
                }
                else {
                    v3[v5] = v1[v5].getString("text");
                    if(v3[v5] == null) {
                    }
                    else {
                        ++v5;
                        continue;
                    }
                }

                goto label_27;
            }

            v4 = 1;
        label_27:
            if(v4 != 0) {
                v6 = v3;
                goto label_32;
            }

            return v0;
        }
        else {
            v6 = ((String[])v0);
        }

    label_32:
        Parcelable v9 = arg13.getParcelable("on_read");
        Parcelable v8 = arg13.getParcelable("on_reply");
        Parcelable v1_1 = arg13.getParcelable("remote_input");
        String[] v10 = arg13.getStringArray("participants");
        if(v10 != null) {
            if(v10.length != 1) {
            }
            else {
                if(v1_1 != null) {
                    v0_1 = NotificationCompatApi21.toCompatRemoteInput(((RemoteInput)v1_1), arg15);
                }

                return arg14.build(v6, v0_1, ((PendingIntent)v8), ((PendingIntent)v9), v10, arg13.getLong("timestamp"));
            }
        }

        return v0;
    }

    private static android.support.v4.app.RemoteInputCompatBase$RemoteInput toCompatRemoteInput(RemoteInput arg7, android.support.v4.app.RemoteInputCompatBase$RemoteInput$Factory arg8) {
        return arg8.build(arg7.getResultKey(), arg7.getLabel(), arg7.getChoices(), arg7.getAllowFreeFormInput(), arg7.getExtras(), null);
    }
}

