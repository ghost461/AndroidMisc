package android.support.v4.app;

import android.app.Notification$Builder;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.widget.RemoteViews;
import java.util.ArrayList;
import java.util.Iterator;

@RequiresApi(value=26) class NotificationCompatApi26 {
    public class Builder implements NotificationBuilderWithActions, NotificationBuilderWithBuilderAccessor {
        private Notification$Builder mB;

        Builder(Context arg11, Notification arg12, CharSequence arg13, CharSequence arg14, CharSequence arg15, RemoteViews arg16, int arg17, PendingIntent arg18, PendingIntent arg19, Bitmap arg20, int arg21, int arg22, boolean arg23, boolean arg24, boolean arg25, int arg26, CharSequence arg27, boolean arg28, String arg29, ArrayList arg30, Bundle arg31, int arg32, int arg33, Notification arg34, String arg35, boolean arg36, String arg37, CharSequence[] arg38, RemoteViews arg39, RemoteViews arg40, RemoteViews arg41, String arg42, int arg43, String arg44, long arg45, boolean arg47, boolean arg48, int arg49) {
            PendingIntent v1_1;
            Builder v0 = this;
            Notification v1 = arg12;
            RemoteViews v2 = arg39;
            RemoteViews v3 = arg40;
            RemoteViews v4 = arg41;
            String v5 = arg42;
            super();
            Notification$Builder v6 = new Notification$Builder(arg11, v5).setWhen(v1.when).setShowWhen(arg24).setSmallIcon(v1.icon, v1.iconLevel).setContent(v1.contentView).setTicker(v1.tickerText, arg16).setSound(v1.sound, v1.audioStreamType).setVibrate(v1.vibrate).setLights(v1.ledARGB, v1.ledOnMS, v1.ledOffMS);
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

            v0.mB = v6.setFullScreenIntent(v1_1, v8).setLargeIcon(arg20).setNumber(arg17).setUsesChronometer(arg25).setPriority(arg26).setProgress(arg21, arg22, arg23).setLocalOnly(arg28).setExtras(arg31).setGroup(arg35).setGroupSummary(arg36).setSortKey(arg37).setCategory(arg29).setColor(arg32).setVisibility(arg33).setPublicVersion(arg34).setRemoteInputHistory(arg38).setChannelId(v5).setBadgeIconType(arg43).setShortcutId(arg44).setTimeoutAfter(arg45).setGroupAlertBehavior(arg49);
            if(arg48) {
                v0.mB.setColorized(arg47);
            }

            if(v2 != null) {
                v0.mB.setCustomContentView(v2);
            }

            if(v3 != null) {
                v0.mB.setCustomBigContentView(v3);
            }

            if(v4 != null) {
                v0.mB.setCustomHeadsUpContentView(v4);
            }

            Iterator v1_2 = arg30.iterator();
            while(v1_2.hasNext()) {
                v0.mB.addPerson(v1_2.next());
            }
        }

        public void addAction(Action arg2) {
            NotificationCompatApi24.addAction(this.mB, arg2);
        }

        public Notification build() {
            return this.mB.build();
        }

        public Notification$Builder getBuilder() {
            return this.mB;
        }
    }

    NotificationCompatApi26() {
        super();
    }
}

