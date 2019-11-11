package android.support.v7.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.BundleCompat;
import android.support.v4.media.session.MediaSessionCompat$Token;

@Deprecated public class NotificationCompat extends android.support.v4.app.NotificationCompat {
    @Deprecated public class Builder extends android.support.v4.app.NotificationCompat$Builder {
        @Deprecated public Builder(Context arg1) {
            super(arg1);
        }
    }

    @Deprecated public class DecoratedCustomViewStyle extends android.support.v4.app.NotificationCompat$DecoratedCustomViewStyle {
        @Deprecated public DecoratedCustomViewStyle() {
            super();
        }
    }

    @Deprecated public class DecoratedMediaCustomViewStyle extends android.support.v4.media.app.NotificationCompat$DecoratedMediaCustomViewStyle {
        @Deprecated public DecoratedMediaCustomViewStyle() {
            super();
        }
    }

    @Deprecated public class MediaStyle extends android.support.v4.media.app.NotificationCompat$MediaStyle {
        @Deprecated public MediaStyle() {
            super();
        }

        @Deprecated public MediaStyle(android.support.v4.app.NotificationCompat$Builder arg1) {
            super(arg1);
        }

        @Deprecated public android.support.v4.media.app.NotificationCompat$MediaStyle setCancelButtonIntent(PendingIntent arg1) {
            return this.setCancelButtonIntent(arg1);
        }

        @Deprecated public MediaStyle setCancelButtonIntent(PendingIntent arg1) {
            return super.setCancelButtonIntent(arg1);
        }

        @Deprecated public android.support.v4.media.app.NotificationCompat$MediaStyle setMediaSession(Token arg1) {
            return this.setMediaSession(arg1);
        }

        @Deprecated public MediaStyle setMediaSession(Token arg1) {
            return super.setMediaSession(arg1);
        }

        @Deprecated public android.support.v4.media.app.NotificationCompat$MediaStyle setShowActionsInCompactView(int[] arg1) {
            return this.setShowActionsInCompactView(arg1);
        }

        @Deprecated public MediaStyle setShowActionsInCompactView(int[] arg1) {
            return super.setShowActionsInCompactView(arg1);
        }

        @Deprecated public android.support.v4.media.app.NotificationCompat$MediaStyle setShowCancelButton(boolean arg1) {
            return this.setShowCancelButton(arg1);
        }

        @Deprecated public MediaStyle setShowCancelButton(boolean arg1) {
            return super.setShowCancelButton(arg1);
        }
    }

    @Deprecated public NotificationCompat() {
        super();
    }

    @Deprecated public static Token getMediaSession(Notification arg2) {
        Bundle v2 = NotificationCompat.getExtras(arg2);
        if(v2 != null) {
            if(Build$VERSION.SDK_INT >= 21) {
                Parcelable v2_1 = v2.getParcelable("android.mediaSession");
                if(v2_1 != null) {
                    return Token.fromToken(v2_1);
                }
            }
            else {
                IBinder v2_2 = BundleCompat.getBinder(v2, "android.mediaSession");
                if(v2_2 != null) {
                    Parcel v0 = Parcel.obtain();
                    v0.writeStrongBinder(v2_2);
                    v0.setDataPosition(0);
                    Object v2_3 = Token.CREATOR.createFromParcel(v0);
                    v0.recycle();
                    return ((Token)v2_3);
                }
            }
        }

        return null;
    }
}

