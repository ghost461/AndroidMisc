package android.support.v4.media.app;

import android.app.Notification$DecoratedMediaCustomViewStyle;
import android.app.Notification$MediaStyle;
import android.app.Notification;
import android.app.PendingIntent;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.mediacompat.R$color;
import android.support.mediacompat.R$id;
import android.support.mediacompat.R$integer;
import android.support.mediacompat.R$layout;
import android.support.v4.app.BundleCompat;
import android.support.v4.app.NotificationBuilderWithBuilderAccessor;
import android.support.v4.app.NotificationCompat$Action;
import android.support.v4.app.NotificationCompat$Builder;
import android.support.v4.app.NotificationCompat$Style;
import android.support.v4.media.session.MediaSessionCompat$Token;
import android.widget.RemoteViews;

public class NotificationCompat {
    public class DecoratedMediaCustomViewStyle extends MediaStyle {
        public DecoratedMediaCustomViewStyle() {
            super();
        }

        @RestrictTo(value={Scope.LIBRARY_GROUP}) public void apply(NotificationBuilderWithBuilderAccessor arg3) {
            if(Build$VERSION.SDK_INT >= 24) {
                arg3.getBuilder().setStyle(this.fillInMediaStyle(new Notification$DecoratedMediaCustomViewStyle()));
            }
            else {
                super.apply(arg3);
            }
        }

        int getBigContentViewLayoutResource(int arg2) {
            return arg2 <= 3 ? layout.notification_template_big_media_narrow_custom : layout.notification_template_big_media_custom;
        }

        int getContentViewLayoutResource() {
            int v0 = this.mBuilder.getContentView() != null ? layout.notification_template_media_custom : super.getContentViewLayoutResource();
            return v0;
        }

        @RestrictTo(value={Scope.LIBRARY_GROUP}) public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor arg3) {
            RemoteViews v0 = null;
            if(Build$VERSION.SDK_INT >= 24) {
                return v0;
            }

            RemoteViews v3 = this.mBuilder.getBigContentView() != null ? this.mBuilder.getBigContentView() : this.mBuilder.getContentView();
            if(v3 == null) {
                return v0;
            }

            v0 = this.generateBigContentView();
            this.buildIntoRemoteViews(v0, v3);
            if(Build$VERSION.SDK_INT >= 21) {
                this.setBackgroundColor(v0);
            }

            return v0;
        }

        @RestrictTo(value={Scope.LIBRARY_GROUP}) public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor arg6) {
            RemoteViews v0 = null;
            if(Build$VERSION.SDK_INT >= 24) {
                return v0;
            }

            int v1 = 0;
            int v6 = this.mBuilder.getContentView() != null ? 1 : 0;
            if(Build$VERSION.SDK_INT >= 21) {
                if(v6 != 0 || this.mBuilder.getBigContentView() != null) {
                    v1 = 1;
                }

                if(v1 == 0) {
                    return v0;
                }

                v0 = this.generateContentView();
                if(v6 != 0) {
                    this.buildIntoRemoteViews(v0, this.mBuilder.getContentView());
                }

                this.setBackgroundColor(v0);
                return v0;
            }
            else {
                RemoteViews v1_1 = this.generateContentView();
                if(v6 == 0) {
                    return v0;
                }

                this.buildIntoRemoteViews(v1_1, this.mBuilder.getContentView());
                return v1_1;
            }

            return v0;
        }

        @RestrictTo(value={Scope.LIBRARY_GROUP}) public RemoteViews makeHeadsUpContentView(NotificationBuilderWithBuilderAccessor arg3) {
            RemoteViews v0 = null;
            if(Build$VERSION.SDK_INT >= 24) {
                return v0;
            }

            RemoteViews v3 = this.mBuilder.getHeadsUpContentView() != null ? this.mBuilder.getHeadsUpContentView() : this.mBuilder.getContentView();
            if(v3 == null) {
                return v0;
            }

            v0 = this.generateBigContentView();
            this.buildIntoRemoteViews(v0, v3);
            if(Build$VERSION.SDK_INT >= 21) {
                this.setBackgroundColor(v0);
            }

            return v0;
        }

        private void setBackgroundColor(RemoteViews arg4) {
            int v0 = this.mBuilder.getColor() != 0 ? this.mBuilder.getColor() : this.mBuilder.mContext.getResources().getColor(color.notification_material_background_media_default_color);
            arg4.setInt(id.status_bar_latest_event_content, "setBackgroundColor", v0);
        }
    }

    public class MediaStyle extends Style {
        private static final int MAX_MEDIA_BUTTONS = 5;
        private static final int MAX_MEDIA_BUTTONS_IN_COMPACT = 3;
        int[] mActionsToShowInCompact;
        PendingIntent mCancelButtonIntent;
        boolean mShowCancelButton;
        Token mToken;

        public MediaStyle() {
            super();
            this.mActionsToShowInCompact = null;
        }

        public MediaStyle(Builder arg2) {
            super();
            this.mActionsToShowInCompact = null;
            this.setBuilder(arg2);
        }

        @RestrictTo(value={Scope.LIBRARY_GROUP}) public void apply(NotificationBuilderWithBuilderAccessor arg3) {
            if(Build$VERSION.SDK_INT >= 21) {
                arg3.getBuilder().setStyle(this.fillInMediaStyle(new Notification$MediaStyle()));
            }
            else if(this.mShowCancelButton) {
                arg3.getBuilder().setOngoing(true);
            }
        }

        @RequiresApi(value=21) Notification$MediaStyle fillInMediaStyle(Notification$MediaStyle arg2) {
            if(this.mActionsToShowInCompact != null) {
                arg2.setShowActionsInCompactView(this.mActionsToShowInCompact);
            }

            if(this.mToken != null) {
                arg2.setMediaSession(this.mToken.getToken());
            }

            return arg2;
        }

        RemoteViews generateBigContentView() {
            int v0 = Math.min(this.mBuilder.mActions.size(), 5);
            RemoteViews v1 = this.applyStandardTemplate(false, this.getBigContentViewLayoutResource(v0), false);
            v1.removeAllViews(id.media_actions);
            if(v0 > 0) {
                int v3;
                for(v3 = 0; v3 < v0; ++v3) {
                    v1.addView(id.media_actions, this.generateMediaActionButton(this.mBuilder.mActions.get(v3)));
                }
            }

            if(this.mShowCancelButton) {
                v1.setViewVisibility(id.cancel_action, 0);
                v1.setInt(id.cancel_action, "setAlpha", this.mBuilder.mContext.getResources().getInteger(integer.cancel_button_image_alpha));
                v1.setOnClickPendingIntent(id.cancel_action, this.mCancelButtonIntent);
            }
            else {
                v1.setViewVisibility(id.cancel_action, 8);
            }

            return v1;
        }

        RemoteViews generateContentView() {
            RemoteViews v0 = this.applyStandardTemplate(false, this.getContentViewLayoutResource(), true);
            int v3 = this.mBuilder.mActions.size();
            int v4 = this.mActionsToShowInCompact == null ? 0 : Math.min(this.mActionsToShowInCompact.length, 3);
            v0.removeAllViews(id.media_actions);
            if(v4 > 0) {
                int v5 = 0;
                while(v5 < v4) {
                    if(v5 >= v3) {
                        throw new IllegalArgumentException(String.format("setShowActionsInCompactView: action %d out of bounds (max %d)", Integer.valueOf(v5), Integer.valueOf(v3 - 1)));
                    }
                    else {
                        v0.addView(id.media_actions, this.generateMediaActionButton(this.mBuilder.mActions.get(this.mActionsToShowInCompact[v5])));
                        ++v5;
                        continue;
                    }

                    break;
                }
            }

            v3 = 8;
            if(this.mShowCancelButton) {
                v0.setViewVisibility(id.end_padder, v3);
                v0.setViewVisibility(id.cancel_action, 0);
                v0.setOnClickPendingIntent(id.cancel_action, this.mCancelButtonIntent);
                v0.setInt(id.cancel_action, "setAlpha", this.mBuilder.mContext.getResources().getInteger(integer.cancel_button_image_alpha));
            }
            else {
                v0.setViewVisibility(id.end_padder, 0);
                v0.setViewVisibility(id.cancel_action, v3);
            }

            return v0;
        }

        private RemoteViews generateMediaActionButton(Action arg5) {
            int v0 = arg5.getActionIntent() == null ? 1 : 0;
            RemoteViews v1 = new RemoteViews(this.mBuilder.mContext.getPackageName(), layout.notification_media_action);
            v1.setImageViewResource(id.action0, arg5.getIcon());
            if(v0 == 0) {
                v1.setOnClickPendingIntent(id.action0, arg5.getActionIntent());
            }

            if(Build$VERSION.SDK_INT >= 15) {
                v1.setContentDescription(id.action0, arg5.getTitle());
            }

            return v1;
        }

        int getBigContentViewLayoutResource(int arg2) {
            return arg2 <= 3 ? layout.notification_template_big_media_narrow : layout.notification_template_big_media;
        }

        int getContentViewLayoutResource() {
            return layout.notification_template_media;
        }

        public static Token getMediaSession(Notification arg2) {
            Bundle v2 = android.support.v4.app.NotificationCompat.getExtras(arg2);
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

        @RestrictTo(value={Scope.LIBRARY_GROUP}) public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor arg2) {
            if(Build$VERSION.SDK_INT >= 21) {
                return null;
            }

            return this.generateBigContentView();
        }

        @RestrictTo(value={Scope.LIBRARY_GROUP}) public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor arg2) {
            if(Build$VERSION.SDK_INT >= 21) {
                return null;
            }

            return this.generateContentView();
        }

        public MediaStyle setCancelButtonIntent(PendingIntent arg1) {
            this.mCancelButtonIntent = arg1;
            return this;
        }

        public MediaStyle setMediaSession(Token arg1) {
            this.mToken = arg1;
            return this;
        }

        public MediaStyle setShowActionsInCompactView(int[] arg1) {
            this.mActionsToShowInCompact = arg1;
            return this;
        }

        public MediaStyle setShowCancelButton(boolean arg3) {
            if(Build$VERSION.SDK_INT < 21) {
                this.mShowCancelButton = arg3;
            }

            return this;
        }
    }

    private NotificationCompat() {
        super();
    }
}

