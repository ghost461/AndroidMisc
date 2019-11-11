package android.support.v4.app;

import android.app.Notification$Action;
import android.app.Notification$Builder;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.SparseArray;
import android.widget.RemoteViews;
import java.util.ArrayList;
import java.util.List;

@RequiresApi(value=19) class NotificationCompatKitKat {
    public class Builder implements NotificationBuilderWithActions, NotificationBuilderWithBuilderAccessor {
        private Notification$Builder b;
        private List mActionExtrasList;
        private RemoteViews mBigContentView;
        private RemoteViews mContentView;
        private Bundle mExtras;

        public Builder(Context arg11, Notification arg12, CharSequence arg13, CharSequence arg14, CharSequence arg15, RemoteViews arg16, int arg17, PendingIntent arg18, PendingIntent arg19, Bitmap arg20, int arg21, int arg22, boolean arg23, boolean arg24, boolean arg25, int arg26, CharSequence arg27, boolean arg28, ArrayList arg29, Bundle arg30, String arg31, boolean arg32, String arg33, RemoteViews arg34, RemoteViews arg35) {
            PendingIntent v1_1;
            Builder v0 = this;
            Notification v1 = arg12;
            ArrayList v2 = arg29;
            Bundle v3 = arg30;
            String v4 = arg31;
            String v5 = arg33;
            super();
            v0.mActionExtrasList = new ArrayList();
            Notification$Builder v6 = new Notification$Builder(arg11).setWhen(v1.when).setShowWhen(arg24).setSmallIcon(v1.icon, v1.iconLevel).setContent(v1.contentView).setTicker(v1.tickerText, arg16).setSound(v1.sound, v1.audioStreamType).setVibrate(v1.vibrate).setLights(v1.ledARGB, v1.ledOnMS, v1.ledOffMS);
            boolean v8 = false;
            boolean v7 = (v1.flags & 2) != 0 ? true : false;
            v6 = v6.setOngoing(v7);
            v7 = (v1.flags & 8) != 0 ? true : false;
            v6 = v6.setOnlyAlertOnce(v7);
            v7 = (v1.flags & 16) != 0 ? true : false;
            v6 = v6.setAutoCancel(v7).setDefaults(v1.defaults).setContentTitle(arg13).setContentText(arg14).setSubText(arg27).setContentInfo(arg15).setContentIntent(arg18).setDeleteIntent(v1.deleteIntent);
            if((v1.flags & 0x80) != 0) {
                v1_1 = arg19;
                v8 = true;
            }
            else {
                v1_1 = arg19;
            }

            v0.b = v6.setFullScreenIntent(v1_1, v8).setLargeIcon(arg20).setNumber(arg17).setUsesChronometer(arg25).setPriority(arg26).setProgress(arg21, arg22, arg23);
            v0.mExtras = new Bundle();
            if(v3 != null) {
                v0.mExtras.putAll(v3);
            }

            if(v2 != null && !arg29.isEmpty()) {
                v0.mExtras.putStringArray("android.people", v2.toArray(new String[arg29.size()]));
            }

            if(arg28) {
                v0.mExtras.putBoolean("android.support.localOnly", true);
            }

            if(v4 != null) {
                v0.mExtras.putString("android.support.groupKey", v4);
                if(arg32) {
                    v0.mExtras.putBoolean("android.support.isGroupSummary", true);
                }
                else {
                    v0.mExtras.putBoolean("android.support.useSideChannel", true);
                }
            }

            if(v5 != null) {
                v0.mExtras.putString("android.support.sortKey", v5);
            }

            v0.mContentView = arg34;
            v0.mBigContentView = arg35;
        }

        public void addAction(Action arg3) {
            this.mActionExtrasList.add(NotificationCompatJellybean.writeActionAndGetExtras(this.b, arg3));
        }

        public Notification build() {
            SparseArray v0 = NotificationCompatJellybean.buildActionExtrasMap(this.mActionExtrasList);
            if(v0 != null) {
                this.mExtras.putSparseParcelableArray("android.support.actionExtras", v0);
            }

            this.b.setExtras(this.mExtras);
            Notification v0_1 = this.b.build();
            if(this.mContentView != null) {
                v0_1.contentView = this.mContentView;
            }

            if(this.mBigContentView != null) {
                v0_1.bigContentView = this.mBigContentView;
            }

            return v0_1;
        }

        public Notification$Builder getBuilder() {
            return this.b;
        }
    }

    NotificationCompatKitKat() {
        super();
    }

    public static Action getAction(Notification arg7, int arg8, Factory arg9, android.support.v4.app.RemoteInputCompatBase$RemoteInput$Factory arg10) {
        Notification$Action v0 = arg7.actions[arg8];
        SparseArray v7 = arg7.extras.getSparseParcelableArray("android.support.actionExtras");
        Object v7_1 = v7 != null ? v7.get(arg8) : null;
        return NotificationCompatJellybean.readAction(arg9, arg10, v0.icon, v0.title, v0.actionIntent, v7_1);
    }
}

