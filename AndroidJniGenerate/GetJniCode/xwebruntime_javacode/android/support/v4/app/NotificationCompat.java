package android.support.v4.app;

import android.app.Notification$Builder;
import android.app.Notification$DecoratedCustomViewStyle;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap$Config;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuff$Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.compat.R$color;
import android.support.compat.R$dimen;
import android.support.compat.R$drawable;
import android.support.compat.R$id;
import android.support.compat.R$integer;
import android.support.compat.R$layout;
import android.support.compat.R$string;
import android.support.v4.text.BidiFormatter;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.widget.RemoteViews;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class NotificationCompat {
    public class Action extends android.support.v4.app.NotificationCompatBase$Action {
        final class android.support.v4.app.NotificationCompat$Action$1 implements Factory {
            android.support.v4.app.NotificationCompat$Action$1() {
                super();
            }

            public android.support.v4.app.NotificationCompatBase$Action build(int arg10, CharSequence arg11, PendingIntent arg12, Bundle arg13, RemoteInput[] arg14, RemoteInput[] arg15, boolean arg16) {
                return new Action(arg10, arg11, arg12, arg13, arg14, arg15, arg16);
            }

            public Action[] newArray(int arg1) {
                return new Action[arg1];
            }

            public android.support.v4.app.NotificationCompatBase$Action[] newArray(int arg1) {
                return this.newArray(arg1);
            }
        }

        public final class Builder {
            private boolean mAllowGeneratedReplies;
            private final Bundle mExtras;
            private final int mIcon;
            private final PendingIntent mIntent;
            private ArrayList mRemoteInputs;
            private final CharSequence mTitle;

            public Builder(int arg8, CharSequence arg9, PendingIntent arg10) {
                this(arg8, arg9, arg10, new Bundle(), null, true);
            }

            private Builder(int arg2, CharSequence arg3, PendingIntent arg4, Bundle arg5, android.support.v4.app.RemoteInput[] arg6, boolean arg7) {
                super();
                this.mAllowGeneratedReplies = true;
                this.mIcon = arg2;
                this.mTitle = android.support.v4.app.NotificationCompat$Builder.limitCharSequenceLength(arg3);
                this.mIntent = arg4;
                this.mExtras = arg5;
                ArrayList v2 = arg6 == null ? null : new ArrayList(Arrays.asList(((Object[])arg6)));
                this.mRemoteInputs = v2;
                this.mAllowGeneratedReplies = arg7;
            }

            public Builder(Action arg8) {
                this(arg8.icon, arg8.title, arg8.actionIntent, new Bundle(arg8.mExtras), arg8.getRemoteInputs(), arg8.getAllowGeneratedReplies());
            }

            public Builder addExtras(Bundle arg2) {
                if(arg2 != null) {
                    this.mExtras.putAll(arg2);
                }

                return this;
            }

            public Builder addRemoteInput(android.support.v4.app.RemoteInput arg2) {
                if(this.mRemoteInputs == null) {
                    this.mRemoteInputs = new ArrayList();
                }

                this.mRemoteInputs.add(arg2);
                return this;
            }

            public Action build() {
                android.support.v4.app.RemoteInput[] v10;
                ArrayList v0 = new ArrayList();
                ArrayList v1 = new ArrayList();
                if(this.mRemoteInputs != null) {
                    Iterator v2 = this.mRemoteInputs.iterator();
                    while(v2.hasNext()) {
                        Object v3 = v2.next();
                        if(((android.support.v4.app.RemoteInput)v3).isDataOnly()) {
                            ((List)v0).add(v3);
                        }
                        else {
                            ((List)v1).add(v3);
                        }
                    }
                }

                Object[] v3_1 = null;
                if(((List)v0).isEmpty()) {
                    v10 = ((android.support.v4.app.RemoteInput[])v3_1);
                }
                else {
                    Object[] v10_1 = ((List)v0).toArray(new android.support.v4.app.RemoteInput[((List)v0).size()]);
                }

                if(!((List)v1).isEmpty()) {
                    v3_1 = ((List)v1).toArray(new android.support.v4.app.RemoteInput[((List)v1).size()]);
                }

                Object[] v9 = v3_1;
                return new Action(this.mIcon, this.mTitle, this.mIntent, this.mExtras, ((android.support.v4.app.RemoteInput[])v9), v10, this.mAllowGeneratedReplies);
            }

            public Builder extend(Extender arg1) {
                arg1.extend(this);
                return this;
            }

            public Bundle getExtras() {
                return this.mExtras;
            }

            public Builder setAllowGeneratedReplies(boolean arg1) {
                this.mAllowGeneratedReplies = arg1;
                return this;
            }
        }

        public interface Extender {
            Builder extend(Builder arg1);
        }

        public final class WearableExtender implements Extender {
            private static final int DEFAULT_FLAGS = 1;
            private static final String EXTRA_WEARABLE_EXTENSIONS = "android.wearable.EXTENSIONS";
            private static final int FLAG_AVAILABLE_OFFLINE = 1;
            private static final int FLAG_HINT_DISPLAY_INLINE = 4;
            private static final int FLAG_HINT_LAUNCHES_ACTIVITY = 2;
            private static final String KEY_CANCEL_LABEL = "cancelLabel";
            private static final String KEY_CONFIRM_LABEL = "confirmLabel";
            private static final String KEY_FLAGS = "flags";
            private static final String KEY_IN_PROGRESS_LABEL = "inProgressLabel";
            private CharSequence mCancelLabel;
            private CharSequence mConfirmLabel;
            private int mFlags;
            private CharSequence mInProgressLabel;

            public WearableExtender() {
                super();
                this.mFlags = 1;
            }

            public WearableExtender(Action arg3) {
                super();
                this.mFlags = 1;
                Bundle v3 = arg3.getExtras().getBundle("android.wearable.EXTENSIONS");
                if(v3 != null) {
                    this.mFlags = v3.getInt("flags", 1);
                    this.mInProgressLabel = v3.getCharSequence("inProgressLabel");
                    this.mConfirmLabel = v3.getCharSequence("confirmLabel");
                    this.mCancelLabel = v3.getCharSequence("cancelLabel");
                }
            }

            public WearableExtender clone() {
                WearableExtender v0 = new WearableExtender();
                v0.mFlags = this.mFlags;
                v0.mInProgressLabel = this.mInProgressLabel;
                v0.mConfirmLabel = this.mConfirmLabel;
                v0.mCancelLabel = this.mCancelLabel;
                return v0;
            }

            public Object clone() throws CloneNotSupportedException {
                return this.clone();
            }

            public Builder extend(Builder arg4) {
                Bundle v0 = new Bundle();
                if(this.mFlags != 1) {
                    v0.putInt("flags", this.mFlags);
                }

                if(this.mInProgressLabel != null) {
                    v0.putCharSequence("inProgressLabel", this.mInProgressLabel);
                }

                if(this.mConfirmLabel != null) {
                    v0.putCharSequence("confirmLabel", this.mConfirmLabel);
                }

                if(this.mCancelLabel != null) {
                    v0.putCharSequence("cancelLabel", this.mCancelLabel);
                }

                arg4.getExtras().putBundle("android.wearable.EXTENSIONS", v0);
                return arg4;
            }

            public CharSequence getCancelLabel() {
                return this.mCancelLabel;
            }

            public CharSequence getConfirmLabel() {
                return this.mConfirmLabel;
            }

            public boolean getHintDisplayActionInline() {
                boolean v0 = (this.mFlags & 4) != 0 ? true : false;
                return v0;
            }

            public boolean getHintLaunchesActivity() {
                boolean v0 = (this.mFlags & 2) != 0 ? true : false;
                return v0;
            }

            public CharSequence getInProgressLabel() {
                return this.mInProgressLabel;
            }

            public boolean isAvailableOffline() {
                boolean v1 = true;
                if((this.mFlags & 1) != 0) {
                }
                else {
                    v1 = false;
                }

                return v1;
            }

            public WearableExtender setAvailableOffline(boolean arg2) {
                this.setFlag(1, arg2);
                return this;
            }

            public WearableExtender setCancelLabel(CharSequence arg1) {
                this.mCancelLabel = arg1;
                return this;
            }

            public WearableExtender setConfirmLabel(CharSequence arg1) {
                this.mConfirmLabel = arg1;
                return this;
            }

            private void setFlag(int arg1, boolean arg2) {
                if(arg2) {
                    this.mFlags |= arg1;
                }
                else {
                    this.mFlags &= arg1 ^ -1;
                }
            }

            public WearableExtender setHintDisplayActionInline(boolean arg2) {
                this.setFlag(4, arg2);
                return this;
            }

            public WearableExtender setHintLaunchesActivity(boolean arg2) {
                this.setFlag(2, arg2);
                return this;
            }

            public WearableExtender setInProgressLabel(CharSequence arg1) {
                this.mInProgressLabel = arg1;
                return this;
            }
        }

        @RestrictTo(value={Scope.LIBRARY_GROUP}) public static final Factory FACTORY;
        public PendingIntent actionIntent;
        public int icon;
        private boolean mAllowGeneratedReplies;
        private final android.support.v4.app.RemoteInput[] mDataOnlyRemoteInputs;
        final Bundle mExtras;
        private final android.support.v4.app.RemoteInput[] mRemoteInputs;
        public CharSequence title;

        static {
            Action.FACTORY = new android.support.v4.app.NotificationCompat$Action$1();
        }

        public Action(int arg9, CharSequence arg10, PendingIntent arg11) {
            this(arg9, arg10, arg11, new Bundle(), null, null, true);
        }

        Action(int arg1, CharSequence arg2, PendingIntent arg3, Bundle arg4, android.support.v4.app.RemoteInput[] arg5, android.support.v4.app.RemoteInput[] arg6, boolean arg7) {
            super();
            this.icon = arg1;
            this.title = android.support.v4.app.NotificationCompat$Builder.limitCharSequenceLength(arg2);
            this.actionIntent = arg3;
            if(arg4 != null) {
            }
            else {
                arg4 = new Bundle();
            }

            this.mExtras = arg4;
            this.mRemoteInputs = arg5;
            this.mDataOnlyRemoteInputs = arg6;
            this.mAllowGeneratedReplies = arg7;
        }

        public PendingIntent getActionIntent() {
            return this.actionIntent;
        }

        public boolean getAllowGeneratedReplies() {
            return this.mAllowGeneratedReplies;
        }

        public android.support.v4.app.RemoteInput[] getDataOnlyRemoteInputs() {
            return this.mDataOnlyRemoteInputs;
        }

        public RemoteInput[] getDataOnlyRemoteInputs() {
            return this.getDataOnlyRemoteInputs();
        }

        public Bundle getExtras() {
            return this.mExtras;
        }

        public int getIcon() {
            return this.icon;
        }

        public android.support.v4.app.RemoteInput[] getRemoteInputs() {
            return this.mRemoteInputs;
        }

        public RemoteInput[] getRemoteInputs() {
            return this.getRemoteInputs();
        }

        public CharSequence getTitle() {
            return this.title;
        }
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) @Retention(value=RetentionPolicy.SOURCE) @public interface BadgeIconType {
    }

    public class BigPictureStyle extends Style {
        private Bitmap mBigLargeIcon;
        private boolean mBigLargeIconSet;
        private Bitmap mPicture;

        public BigPictureStyle() {
            super();
        }

        public BigPictureStyle(android.support.v4.app.NotificationCompat$Builder arg1) {
            super();
            this.setBuilder(arg1);
        }

        @RestrictTo(value={Scope.LIBRARY_GROUP}) public void apply(NotificationBuilderWithBuilderAccessor arg10) {
            if(Build$VERSION.SDK_INT >= 16) {
                NotificationCompatJellybean.addBigPictureStyle(arg10, this.mBigContentTitle, this.mSummaryTextSet, this.mSummaryText, this.mPicture, this.mBigLargeIcon, this.mBigLargeIconSet);
            }
        }

        public BigPictureStyle bigLargeIcon(Bitmap arg1) {
            this.mBigLargeIcon = arg1;
            this.mBigLargeIconSet = true;
            return this;
        }

        public BigPictureStyle bigPicture(Bitmap arg1) {
            this.mPicture = arg1;
            return this;
        }

        public BigPictureStyle setBigContentTitle(CharSequence arg1) {
            this.mBigContentTitle = android.support.v4.app.NotificationCompat$Builder.limitCharSequenceLength(arg1);
            return this;
        }

        public BigPictureStyle setSummaryText(CharSequence arg1) {
            this.mSummaryText = android.support.v4.app.NotificationCompat$Builder.limitCharSequenceLength(arg1);
            this.mSummaryTextSet = true;
            return this;
        }
    }

    public class BigTextStyle extends Style {
        private CharSequence mBigText;

        public BigTextStyle() {
            super();
        }

        public BigTextStyle(android.support.v4.app.NotificationCompat$Builder arg1) {
            super();
            this.setBuilder(arg1);
        }

        @RestrictTo(value={Scope.LIBRARY_GROUP}) public void apply(NotificationBuilderWithBuilderAccessor arg5) {
            if(Build$VERSION.SDK_INT >= 16) {
                NotificationCompatJellybean.addBigTextStyle(arg5, this.mBigContentTitle, this.mSummaryTextSet, this.mSummaryText, this.mBigText);
            }
        }

        public BigTextStyle bigText(CharSequence arg1) {
            this.mBigText = android.support.v4.app.NotificationCompat$Builder.limitCharSequenceLength(arg1);
            return this;
        }

        public BigTextStyle setBigContentTitle(CharSequence arg1) {
            this.mBigContentTitle = android.support.v4.app.NotificationCompat$Builder.limitCharSequenceLength(arg1);
            return this;
        }

        public BigTextStyle setSummaryText(CharSequence arg1) {
            this.mSummaryText = android.support.v4.app.NotificationCompat$Builder.limitCharSequenceLength(arg1);
            this.mSummaryTextSet = true;
            return this;
        }
    }

    public class android.support.v4.app.NotificationCompat$Builder {
        private static final int MAX_CHARSEQUENCE_LENGTH = 0x1400;
        @RestrictTo(value={Scope.LIBRARY_GROUP}) public ArrayList mActions;
        int mBadgeIcon;
        RemoteViews mBigContentView;
        String mCategory;
        String mChannelId;
        int mColor;
        boolean mColorized;
        boolean mColorizedSet;
        @RestrictTo(value={Scope.LIBRARY_GROUP}) public CharSequence mContentInfo;
        PendingIntent mContentIntent;
        @RestrictTo(value={Scope.LIBRARY_GROUP}) public CharSequence mContentText;
        @RestrictTo(value={Scope.LIBRARY_GROUP}) public CharSequence mContentTitle;
        RemoteViews mContentView;
        @RestrictTo(value={Scope.LIBRARY_GROUP}) public Context mContext;
        Bundle mExtras;
        PendingIntent mFullScreenIntent;
        private int mGroupAlertBehavior;
        String mGroupKey;
        boolean mGroupSummary;
        RemoteViews mHeadsUpContentView;
        @RestrictTo(value={Scope.LIBRARY_GROUP}) public Bitmap mLargeIcon;
        boolean mLocalOnly;
        @RestrictTo(value={Scope.LIBRARY_GROUP}) public Notification mNotification;
        @RestrictTo(value={Scope.LIBRARY_GROUP}) public int mNumber;
        public ArrayList mPeople;
        int mPriority;
        int mProgress;
        boolean mProgressIndeterminate;
        int mProgressMax;
        Notification mPublicVersion;
        @RestrictTo(value={Scope.LIBRARY_GROUP}) public CharSequence[] mRemoteInputHistory;
        String mShortcutId;
        boolean mShowWhen;
        String mSortKey;
        @RestrictTo(value={Scope.LIBRARY_GROUP}) public Style mStyle;
        @RestrictTo(value={Scope.LIBRARY_GROUP}) public CharSequence mSubText;
        RemoteViews mTickerView;
        long mTimeout;
        @RestrictTo(value={Scope.LIBRARY_GROUP}) public boolean mUseChronometer;
        int mVisibility;

        @Deprecated public android.support.v4.app.NotificationCompat$Builder(Context arg2) {
            this(arg2, null);
        }

        public android.support.v4.app.NotificationCompat$Builder(@NonNull Context arg4, @NonNull String arg5) {
            super();
            this.mShowWhen = true;
            this.mActions = new ArrayList();
            this.mLocalOnly = false;
            this.mColor = 0;
            this.mVisibility = 0;
            this.mBadgeIcon = 0;
            this.mGroupAlertBehavior = 0;
            this.mNotification = new Notification();
            this.mContext = arg4;
            this.mChannelId = arg5;
            this.mNotification.when = System.currentTimeMillis();
            this.mNotification.audioStreamType = -1;
            this.mPriority = 0;
            this.mPeople = new ArrayList();
        }

        static int access$000(android.support.v4.app.NotificationCompat$Builder arg0) {
            return arg0.mGroupAlertBehavior;
        }

        public android.support.v4.app.NotificationCompat$Builder addAction(int arg3, CharSequence arg4, PendingIntent arg5) {
            this.mActions.add(new Action(arg3, arg4, arg5));
            return this;
        }

        public android.support.v4.app.NotificationCompat$Builder addAction(Action arg2) {
            this.mActions.add(arg2);
            return this;
        }

        public android.support.v4.app.NotificationCompat$Builder addExtras(Bundle arg2) {
            if(arg2 != null) {
                if(this.mExtras == null) {
                    this.mExtras = new Bundle(arg2);
                }
                else {
                    this.mExtras.putAll(arg2);
                }
            }

            return this;
        }

        public android.support.v4.app.NotificationCompat$Builder addPerson(String arg2) {
            this.mPeople.add(arg2);
            return this;
        }

        public Notification build() {
            return NotificationCompat.IMPL.build(this, this.getExtender());
        }

        public android.support.v4.app.NotificationCompat$Builder extend(android.support.v4.app.NotificationCompat$Extender arg1) {
            arg1.extend(this);
            return this;
        }

        @RestrictTo(value={Scope.LIBRARY_GROUP}) public RemoteViews getBigContentView() {
            return this.mBigContentView;
        }

        @RestrictTo(value={Scope.LIBRARY_GROUP}) public int getColor() {
            return this.mColor;
        }

        @RestrictTo(value={Scope.LIBRARY_GROUP}) public RemoteViews getContentView() {
            return this.mContentView;
        }

        @RestrictTo(value={Scope.LIBRARY_GROUP}) protected BuilderExtender getExtender() {
            return new BuilderExtender();
        }

        public Bundle getExtras() {
            if(this.mExtras == null) {
                this.mExtras = new Bundle();
            }

            return this.mExtras;
        }

        @RestrictTo(value={Scope.LIBRARY_GROUP}) public RemoteViews getHeadsUpContentView() {
            return this.mHeadsUpContentView;
        }

        @Deprecated public Notification getNotification() {
            return this.build();
        }

        @RestrictTo(value={Scope.LIBRARY_GROUP}) public int getPriority() {
            return this.mPriority;
        }

        @RestrictTo(value={Scope.LIBRARY_GROUP}) public long getWhenIfShowing() {
            long v0 = this.mShowWhen ? this.mNotification.when : 0;
            return v0;
        }

        protected static CharSequence limitCharSequenceLength(CharSequence arg2) {
            if(arg2 == null) {
                return arg2;
            }

            int v1 = 0x1400;
            if(arg2.length() > v1) {
                arg2 = arg2.subSequence(0, v1);
            }

            return arg2;
        }

        public android.support.v4.app.NotificationCompat$Builder setAutoCancel(boolean arg2) {
            this.setFlag(16, arg2);
            return this;
        }

        public android.support.v4.app.NotificationCompat$Builder setBadgeIconType(int arg1) {
            this.mBadgeIcon = arg1;
            return this;
        }

        public android.support.v4.app.NotificationCompat$Builder setCategory(String arg1) {
            this.mCategory = arg1;
            return this;
        }

        public android.support.v4.app.NotificationCompat$Builder setChannelId(@NonNull String arg1) {
            this.mChannelId = arg1;
            return this;
        }

        public android.support.v4.app.NotificationCompat$Builder setColor(@ColorInt int arg1) {
            this.mColor = arg1;
            return this;
        }

        public android.support.v4.app.NotificationCompat$Builder setColorized(boolean arg1) {
            this.mColorized = arg1;
            this.mColorizedSet = true;
            return this;
        }

        public android.support.v4.app.NotificationCompat$Builder setContent(RemoteViews arg2) {
            this.mNotification.contentView = arg2;
            return this;
        }

        public android.support.v4.app.NotificationCompat$Builder setContentInfo(CharSequence arg1) {
            this.mContentInfo = android.support.v4.app.NotificationCompat$Builder.limitCharSequenceLength(arg1);
            return this;
        }

        public android.support.v4.app.NotificationCompat$Builder setContentIntent(PendingIntent arg1) {
            this.mContentIntent = arg1;
            return this;
        }

        public android.support.v4.app.NotificationCompat$Builder setContentText(CharSequence arg1) {
            this.mContentText = android.support.v4.app.NotificationCompat$Builder.limitCharSequenceLength(arg1);
            return this;
        }

        public android.support.v4.app.NotificationCompat$Builder setContentTitle(CharSequence arg1) {
            this.mContentTitle = android.support.v4.app.NotificationCompat$Builder.limitCharSequenceLength(arg1);
            return this;
        }

        public android.support.v4.app.NotificationCompat$Builder setCustomBigContentView(RemoteViews arg1) {
            this.mBigContentView = arg1;
            return this;
        }

        public android.support.v4.app.NotificationCompat$Builder setCustomContentView(RemoteViews arg1) {
            this.mContentView = arg1;
            return this;
        }

        public android.support.v4.app.NotificationCompat$Builder setCustomHeadsUpContentView(RemoteViews arg1) {
            this.mHeadsUpContentView = arg1;
            return this;
        }

        public android.support.v4.app.NotificationCompat$Builder setDefaults(int arg2) {
            this.mNotification.defaults = arg2;
            if((arg2 & 4) != 0) {
                this.mNotification.flags |= 1;
            }

            return this;
        }

        public android.support.v4.app.NotificationCompat$Builder setDeleteIntent(PendingIntent arg2) {
            this.mNotification.deleteIntent = arg2;
            return this;
        }

        public android.support.v4.app.NotificationCompat$Builder setExtras(Bundle arg1) {
            this.mExtras = arg1;
            return this;
        }

        private void setFlag(int arg2, boolean arg3) {
            if(arg3) {
                this.mNotification.flags |= arg2;
            }
            else {
                this.mNotification.flags &= arg2 ^ -1;
            }
        }

        public android.support.v4.app.NotificationCompat$Builder setFullScreenIntent(PendingIntent arg1, boolean arg2) {
            this.mFullScreenIntent = arg1;
            this.setFlag(0x80, arg2);
            return this;
        }

        public android.support.v4.app.NotificationCompat$Builder setGroup(String arg1) {
            this.mGroupKey = arg1;
            return this;
        }

        public android.support.v4.app.NotificationCompat$Builder setGroupAlertBehavior(int arg1) {
            this.mGroupAlertBehavior = arg1;
            return this;
        }

        public android.support.v4.app.NotificationCompat$Builder setGroupSummary(boolean arg1) {
            this.mGroupSummary = arg1;
            return this;
        }

        public android.support.v4.app.NotificationCompat$Builder setLargeIcon(Bitmap arg1) {
            this.mLargeIcon = arg1;
            return this;
        }

        public android.support.v4.app.NotificationCompat$Builder setLights(@ColorInt int arg2, int arg3, int arg4) {
            this.mNotification.ledARGB = arg2;
            this.mNotification.ledOnMS = arg3;
            this.mNotification.ledOffMS = arg4;
            arg2 = this.mNotification.ledOnMS == 0 || this.mNotification.ledOffMS == 0 ? 0 : 1;
            this.mNotification.flags = arg2 | this.mNotification.flags & -2;
            return this;
        }

        public android.support.v4.app.NotificationCompat$Builder setLocalOnly(boolean arg1) {
            this.mLocalOnly = arg1;
            return this;
        }

        public android.support.v4.app.NotificationCompat$Builder setNumber(int arg1) {
            this.mNumber = arg1;
            return this;
        }

        public android.support.v4.app.NotificationCompat$Builder setOngoing(boolean arg2) {
            this.setFlag(2, arg2);
            return this;
        }

        public android.support.v4.app.NotificationCompat$Builder setOnlyAlertOnce(boolean arg2) {
            this.setFlag(8, arg2);
            return this;
        }

        public android.support.v4.app.NotificationCompat$Builder setPriority(int arg1) {
            this.mPriority = arg1;
            return this;
        }

        public android.support.v4.app.NotificationCompat$Builder setProgress(int arg1, int arg2, boolean arg3) {
            this.mProgressMax = arg1;
            this.mProgress = arg2;
            this.mProgressIndeterminate = arg3;
            return this;
        }

        public android.support.v4.app.NotificationCompat$Builder setPublicVersion(Notification arg1) {
            this.mPublicVersion = arg1;
            return this;
        }

        public android.support.v4.app.NotificationCompat$Builder setRemoteInputHistory(CharSequence[] arg1) {
            this.mRemoteInputHistory = arg1;
            return this;
        }

        public android.support.v4.app.NotificationCompat$Builder setShortcutId(String arg1) {
            this.mShortcutId = arg1;
            return this;
        }

        public android.support.v4.app.NotificationCompat$Builder setShowWhen(boolean arg1) {
            this.mShowWhen = arg1;
            return this;
        }

        public android.support.v4.app.NotificationCompat$Builder setSmallIcon(int arg2) {
            this.mNotification.icon = arg2;
            return this;
        }

        public android.support.v4.app.NotificationCompat$Builder setSmallIcon(int arg2, int arg3) {
            this.mNotification.icon = arg2;
            this.mNotification.iconLevel = arg3;
            return this;
        }

        public android.support.v4.app.NotificationCompat$Builder setSortKey(String arg1) {
            this.mSortKey = arg1;
            return this;
        }

        public android.support.v4.app.NotificationCompat$Builder setSound(Uri arg2) {
            this.mNotification.sound = arg2;
            this.mNotification.audioStreamType = -1;
            return this;
        }

        public android.support.v4.app.NotificationCompat$Builder setSound(Uri arg2, int arg3) {
            this.mNotification.sound = arg2;
            this.mNotification.audioStreamType = arg3;
            return this;
        }

        public android.support.v4.app.NotificationCompat$Builder setStyle(Style arg2) {
            if(this.mStyle != arg2) {
                this.mStyle = arg2;
                if(this.mStyle != null) {
                    this.mStyle.setBuilder(this);
                }
            }

            return this;
        }

        public android.support.v4.app.NotificationCompat$Builder setSubText(CharSequence arg1) {
            this.mSubText = android.support.v4.app.NotificationCompat$Builder.limitCharSequenceLength(arg1);
            return this;
        }

        public android.support.v4.app.NotificationCompat$Builder setTicker(CharSequence arg2) {
            this.mNotification.tickerText = android.support.v4.app.NotificationCompat$Builder.limitCharSequenceLength(arg2);
            return this;
        }

        public android.support.v4.app.NotificationCompat$Builder setTicker(CharSequence arg2, RemoteViews arg3) {
            this.mNotification.tickerText = android.support.v4.app.NotificationCompat$Builder.limitCharSequenceLength(arg2);
            this.mTickerView = arg3;
            return this;
        }

        public android.support.v4.app.NotificationCompat$Builder setTimeoutAfter(long arg1) {
            this.mTimeout = arg1;
            return this;
        }

        public android.support.v4.app.NotificationCompat$Builder setUsesChronometer(boolean arg1) {
            this.mUseChronometer = arg1;
            return this;
        }

        public android.support.v4.app.NotificationCompat$Builder setVibrate(long[] arg2) {
            this.mNotification.vibrate = arg2;
            return this;
        }

        public android.support.v4.app.NotificationCompat$Builder setVisibility(int arg1) {
            this.mVisibility = arg1;
            return this;
        }

        public android.support.v4.app.NotificationCompat$Builder setWhen(long arg2) {
            this.mNotification.when = arg2;
            return this;
        }
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public class BuilderExtender {
        protected BuilderExtender() {
            super();
        }

        public Notification build(android.support.v4.app.NotificationCompat$Builder arg4, NotificationBuilderWithBuilderAccessor arg5) {
            RemoteViews v0 = arg4.mStyle != null ? arg4.mStyle.makeContentView(arg5) : null;
            Notification v1 = arg5.build();
            if(v0 != null) {
                v1.contentView = v0;
            }
            else if(arg4.mContentView != null) {
                v1.contentView = arg4.mContentView;
            }

            if(Build$VERSION.SDK_INT >= 16 && arg4.mStyle != null) {
                v0 = arg4.mStyle.makeBigContentView(arg5);
                if(v0 != null) {
                    v1.bigContentView = v0;
                }
            }

            if(Build$VERSION.SDK_INT >= 21 && arg4.mStyle != null) {
                RemoteViews v4 = arg4.mStyle.makeHeadsUpContentView(arg5);
                if(v4 != null) {
                    v1.headsUpContentView = v4;
                }
            }

            return v1;
        }
    }

    public final class CarExtender implements android.support.v4.app.NotificationCompat$Extender {
        public class UnreadConversation extends android.support.v4.app.NotificationCompatBase$UnreadConversation {
            final class android.support.v4.app.NotificationCompat$CarExtender$UnreadConversation$1 implements android.support.v4.app.NotificationCompatBase$UnreadConversation$Factory {
                android.support.v4.app.NotificationCompat$CarExtender$UnreadConversation$1() {
                    super();
                }

                public UnreadConversation build(String[] arg10, RemoteInput arg11, PendingIntent arg12, PendingIntent arg13, String[] arg14, long arg15) {
                    return new UnreadConversation(arg10, arg11, arg12, arg13, arg14, arg15);
                }

                public android.support.v4.app.NotificationCompatBase$UnreadConversation build(String[] arg1, RemoteInput arg2, PendingIntent arg3, PendingIntent arg4, String[] arg5, long arg6) {
                    return this.build(arg1, arg2, arg3, arg4, arg5, arg6);
                }
            }

            public class android.support.v4.app.NotificationCompat$CarExtender$UnreadConversation$Builder {
                private long mLatestTimestamp;
                private final List mMessages;
                private final String mParticipant;
                private PendingIntent mReadPendingIntent;
                private android.support.v4.app.RemoteInput mRemoteInput;
                private PendingIntent mReplyPendingIntent;

                public android.support.v4.app.NotificationCompat$CarExtender$UnreadConversation$Builder(String arg2) {
                    super();
                    this.mMessages = new ArrayList();
                    this.mParticipant = arg2;
                }

                public android.support.v4.app.NotificationCompat$CarExtender$UnreadConversation$Builder addMessage(String arg2) {
                    this.mMessages.add(arg2);
                    return this;
                }

                public UnreadConversation build() {
                    return new UnreadConversation(this.mMessages.toArray(new String[this.mMessages.size()]), this.mRemoteInput, this.mReplyPendingIntent, this.mReadPendingIntent, new String[]{this.mParticipant}, this.mLatestTimestamp);
                }

                public android.support.v4.app.NotificationCompat$CarExtender$UnreadConversation$Builder setLatestTimestamp(long arg1) {
                    this.mLatestTimestamp = arg1;
                    return this;
                }

                public android.support.v4.app.NotificationCompat$CarExtender$UnreadConversation$Builder setReadPendingIntent(PendingIntent arg1) {
                    this.mReadPendingIntent = arg1;
                    return this;
                }

                public android.support.v4.app.NotificationCompat$CarExtender$UnreadConversation$Builder setReplyAction(PendingIntent arg1, android.support.v4.app.RemoteInput arg2) {
                    this.mRemoteInput = arg2;
                    this.mReplyPendingIntent = arg1;
                    return this;
                }
            }

            static final android.support.v4.app.NotificationCompatBase$UnreadConversation$Factory FACTORY;
            private final long mLatestTimestamp;
            private final String[] mMessages;
            private final String[] mParticipants;
            private final PendingIntent mReadPendingIntent;
            private final android.support.v4.app.RemoteInput mRemoteInput;
            private final PendingIntent mReplyPendingIntent;

            static {
                UnreadConversation.FACTORY = new android.support.v4.app.NotificationCompat$CarExtender$UnreadConversation$1();
            }

            UnreadConversation(String[] arg1, android.support.v4.app.RemoteInput arg2, PendingIntent arg3, PendingIntent arg4, String[] arg5, long arg6) {
                super();
                this.mMessages = arg1;
                this.mRemoteInput = arg2;
                this.mReadPendingIntent = arg4;
                this.mReplyPendingIntent = arg3;
                this.mParticipants = arg5;
                this.mLatestTimestamp = arg6;
            }

            public long getLatestTimestamp() {
                return this.mLatestTimestamp;
            }

            public String[] getMessages() {
                return this.mMessages;
            }

            public String getParticipant() {
                String v0 = this.mParticipants.length > 0 ? this.mParticipants[0] : null;
                return v0;
            }

            public String[] getParticipants() {
                return this.mParticipants;
            }

            public PendingIntent getReadPendingIntent() {
                return this.mReadPendingIntent;
            }

            public android.support.v4.app.RemoteInput getRemoteInput() {
                return this.mRemoteInput;
            }

            public RemoteInput getRemoteInput() {
                return this.getRemoteInput();
            }

            public PendingIntent getReplyPendingIntent() {
                return this.mReplyPendingIntent;
            }
        }

        private static final String EXTRA_CAR_EXTENDER = "android.car.EXTENSIONS";
        private static final String EXTRA_COLOR = "app_color";
        private static final String EXTRA_CONVERSATION = "car_conversation";
        private static final String EXTRA_LARGE_ICON = "large_icon";
        private static final String TAG = "CarExtender";
        private int mColor;
        private Bitmap mLargeIcon;
        private UnreadConversation mUnreadConversation;

        public CarExtender() {
            super();
            this.mColor = 0;
        }

        public CarExtender(Notification arg4) {
            super();
            this.mColor = 0;
            if(Build$VERSION.SDK_INT < 21) {
                return;
            }

            Bundle v4 = NotificationCompat.getExtras(arg4) == null ? null : NotificationCompat.getExtras(arg4).getBundle("android.car.EXTENSIONS");
            if(v4 != null) {
                this.mLargeIcon = v4.getParcelable("large_icon");
                this.mColor = v4.getInt("app_color", 0);
                this.mUnreadConversation = NotificationCompat.IMPL.getUnreadConversationFromBundle(v4.getBundle("car_conversation"), UnreadConversation.FACTORY, android.support.v4.app.RemoteInput.FACTORY);
            }
        }

        public android.support.v4.app.NotificationCompat$Builder extend(android.support.v4.app.NotificationCompat$Builder arg4) {
            if(Build$VERSION.SDK_INT < 21) {
                return arg4;
            }

            Bundle v0 = new Bundle();
            if(this.mLargeIcon != null) {
                v0.putParcelable("large_icon", this.mLargeIcon);
            }

            if(this.mColor != 0) {
                v0.putInt("app_color", this.mColor);
            }

            if(this.mUnreadConversation != null) {
                v0.putBundle("car_conversation", NotificationCompat.IMPL.getBundleForUnreadConversation(this.mUnreadConversation));
            }

            arg4.getExtras().putBundle("android.car.EXTENSIONS", v0);
            return arg4;
        }

        @ColorInt public int getColor() {
            return this.mColor;
        }

        public Bitmap getLargeIcon() {
            return this.mLargeIcon;
        }

        public UnreadConversation getUnreadConversation() {
            return this.mUnreadConversation;
        }

        public CarExtender setColor(@ColorInt int arg1) {
            this.mColor = arg1;
            return this;
        }

        public CarExtender setLargeIcon(Bitmap arg1) {
            this.mLargeIcon = arg1;
            return this;
        }

        public CarExtender setUnreadConversation(UnreadConversation arg1) {
            this.mUnreadConversation = arg1;
            return this;
        }
    }

    public class DecoratedCustomViewStyle extends Style {
        private static final int MAX_ACTION_BUTTONS = 3;

        public DecoratedCustomViewStyle() {
            super();
        }

        @RestrictTo(value={Scope.LIBRARY_GROUP}) public void apply(NotificationBuilderWithBuilderAccessor arg3) {
            if(Build$VERSION.SDK_INT >= 24) {
                arg3.getBuilder().setStyle(new Notification$DecoratedCustomViewStyle());
            }
        }

        private RemoteViews createRemoteViews(RemoteViews arg7, boolean arg8) {
            int v1 = 1;
            int v2 = 0;
            RemoteViews v0 = this.applyStandardTemplate(true, layout.notification_template_custom_big, false);
            v0.removeAllViews(id.actions);
            if(!arg8 || this.mBuilder.mActions == null) {
            label_26:
                v1 = 0;
            }
            else {
                int v8 = Math.min(this.mBuilder.mActions.size(), 3);
                if(v8 > 0) {
                    int v3;
                    for(v3 = 0; v3 < v8; ++v3) {
                        v0.addView(id.actions, this.generateActionButton(this.mBuilder.mActions.get(v3)));
                    }
                }
                else {
                    goto label_26;
                }
            }

            if(v1 != 0) {
            }
            else {
                v2 = 8;
            }

            v0.setViewVisibility(id.actions, v2);
            v0.setViewVisibility(id.action_divider, v2);
            this.buildIntoRemoteViews(v0, arg7);
            return v0;
        }

        private RemoteViews generateActionButton(Action arg7) {
            int v0 = arg7.actionIntent == null ? 1 : 0;
            String v2 = this.mBuilder.mContext.getPackageName();
            int v3 = v0 != 0 ? layout.notification_action_tombstone : layout.notification_action;
            RemoteViews v1 = new RemoteViews(v2, v3);
            v1.setImageViewBitmap(id.action_image, this.createColoredBitmap(arg7.getIcon(), this.mBuilder.mContext.getResources().getColor(color.notification_action_color_filter)));
            v1.setTextViewText(id.action_text, arg7.title);
            if(v0 == 0) {
                v1.setOnClickPendingIntent(id.action_container, arg7.actionIntent);
            }

            if(Build$VERSION.SDK_INT >= 15) {
                v1.setContentDescription(id.action_container, arg7.title);
            }

            return v1;
        }

        @RestrictTo(value={Scope.LIBRARY_GROUP}) public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor arg3) {
            RemoteViews v0 = null;
            if(Build$VERSION.SDK_INT >= 24) {
                return v0;
            }

            RemoteViews v3 = this.mBuilder.getBigContentView();
            if(v3 != null) {
            }
            else {
                v3 = this.mBuilder.getContentView();
            }

            if(v3 == null) {
                return v0;
            }

            return this.createRemoteViews(v3, true);
        }

        @RestrictTo(value={Scope.LIBRARY_GROUP}) public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor arg3) {
            RemoteViews v0 = null;
            if(Build$VERSION.SDK_INT >= 24) {
                return v0;
            }

            if(this.mBuilder.getContentView() == null) {
                return v0;
            }

            return this.createRemoteViews(this.mBuilder.getContentView(), false);
        }

        @RestrictTo(value={Scope.LIBRARY_GROUP}) public RemoteViews makeHeadsUpContentView(NotificationBuilderWithBuilderAccessor arg3) {
            RemoteViews v0 = null;
            if(Build$VERSION.SDK_INT >= 24) {
                return v0;
            }

            RemoteViews v3 = this.mBuilder.getHeadsUpContentView();
            RemoteViews v1 = v3 != null ? v3 : this.mBuilder.getContentView();
            if(v3 == null) {
                return v0;
            }

            return this.createRemoteViews(v1, true);
        }
    }

    public interface android.support.v4.app.NotificationCompat$Extender {
        android.support.v4.app.NotificationCompat$Builder extend(android.support.v4.app.NotificationCompat$Builder arg1);
    }

    public class InboxStyle extends Style {
        private ArrayList mTexts;

        public InboxStyle() {
            super();
            this.mTexts = new ArrayList();
        }

        public InboxStyle(android.support.v4.app.NotificationCompat$Builder arg2) {
            super();
            this.mTexts = new ArrayList();
            this.setBuilder(arg2);
        }

        public InboxStyle addLine(CharSequence arg2) {
            this.mTexts.add(android.support.v4.app.NotificationCompat$Builder.limitCharSequenceLength(arg2));
            return this;
        }

        @RestrictTo(value={Scope.LIBRARY_GROUP}) public void apply(NotificationBuilderWithBuilderAccessor arg5) {
            if(Build$VERSION.SDK_INT >= 16) {
                NotificationCompatJellybean.addInboxStyle(arg5, this.mBigContentTitle, this.mSummaryTextSet, this.mSummaryText, this.mTexts);
            }
        }

        public InboxStyle setBigContentTitle(CharSequence arg1) {
            this.mBigContentTitle = android.support.v4.app.NotificationCompat$Builder.limitCharSequenceLength(arg1);
            return this;
        }

        public InboxStyle setSummaryText(CharSequence arg1) {
            this.mSummaryText = android.support.v4.app.NotificationCompat$Builder.limitCharSequenceLength(arg1);
            this.mSummaryTextSet = true;
            return this;
        }
    }

    public class MessagingStyle extends Style {
        public final class Message {
            static final String KEY_DATA_MIME_TYPE = "type";
            static final String KEY_DATA_URI = "uri";
            static final String KEY_EXTRAS_BUNDLE = "extras";
            static final String KEY_SENDER = "sender";
            static final String KEY_TEXT = "text";
            static final String KEY_TIMESTAMP = "time";
            private String mDataMimeType;
            private Uri mDataUri;
            private Bundle mExtras;
            private final CharSequence mSender;
            private final CharSequence mText;
            private final long mTimestamp;

            public Message(CharSequence arg2, long arg3, CharSequence arg5) {
                super();
                this.mExtras = new Bundle();
                this.mText = arg2;
                this.mTimestamp = arg3;
                this.mSender = arg5;
            }

            static Bundle[] getBundleArrayForMessages(List arg4) {
                Bundle[] v0 = new Bundle[arg4.size()];
                int v1 = arg4.size();
                int v2;
                for(v2 = 0; v2 < v1; ++v2) {
                    v0[v2] = arg4.get(v2).toBundle();
                }

                return v0;
            }

            public String getDataMimeType() {
                return this.mDataMimeType;
            }

            public Uri getDataUri() {
                return this.mDataUri;
            }

            public Bundle getExtras() {
                return this.mExtras;
            }

            static Message getMessageFromBundle(Bundle arg6) {
                Message v1;
                Message v0 = null;
                try {
                    if(arg6.containsKey("text")) {
                        if(!arg6.containsKey("time")) {
                        }
                        else {
                            v1 = new Message(arg6.getCharSequence("text"), arg6.getLong("time"), arg6.getCharSequence("sender"));
                            if((arg6.containsKey("type")) && (arg6.containsKey("uri"))) {
                                v1.setData(arg6.getString("type"), arg6.getParcelable("uri"));
                            }

                            if(arg6.containsKey("extras")) {
                                v1.getExtras().putAll(arg6.getBundle("extras"));
                            }

                            return v1;
                        }
                    }

                    return v0;
                }
                catch(ClassCastException ) {
                    return v0;
                }

                return v1;
            }

            static List getMessagesFromBundleArray(Parcelable[] arg3) {
                ArrayList v0 = new ArrayList(arg3.length);
                int v1;
                for(v1 = 0; v1 < arg3.length; ++v1) {
                    if((arg3[v1] instanceof Bundle)) {
                        Message v2 = Message.getMessageFromBundle(arg3[v1]);
                        if(v2 != null) {
                            ((List)v0).add(v2);
                        }
                    }
                }

                return ((List)v0);
            }

            public CharSequence getSender() {
                return this.mSender;
            }

            public CharSequence getText() {
                return this.mText;
            }

            public long getTimestamp() {
                return this.mTimestamp;
            }

            public Message setData(String arg1, Uri arg2) {
                this.mDataMimeType = arg1;
                this.mDataUri = arg2;
                return this;
            }

            private Bundle toBundle() {
                Bundle v0 = new Bundle();
                if(this.mText != null) {
                    v0.putCharSequence("text", this.mText);
                }

                v0.putLong("time", this.mTimestamp);
                if(this.mSender != null) {
                    v0.putCharSequence("sender", this.mSender);
                }

                if(this.mDataMimeType != null) {
                    v0.putString("type", this.mDataMimeType);
                }

                if(this.mDataUri != null) {
                    v0.putParcelable("uri", this.mDataUri);
                }

                if(this.mExtras != null) {
                    v0.putBundle("extras", this.mExtras);
                }

                return v0;
            }
        }

        public static final int MAXIMUM_RETAINED_MESSAGES = 25;
        CharSequence mConversationTitle;
        List mMessages;
        CharSequence mUserDisplayName;

        MessagingStyle() {
            super();
            this.mMessages = new ArrayList();
        }

        public MessagingStyle(@NonNull CharSequence arg2) {
            super();
            this.mMessages = new ArrayList();
            this.mUserDisplayName = arg2;
        }

        public void addCompatExtras(Bundle arg3) {
            super.addCompatExtras(arg3);
            if(this.mUserDisplayName != null) {
                arg3.putCharSequence("android.selfDisplayName", this.mUserDisplayName);
            }

            if(this.mConversationTitle != null) {
                arg3.putCharSequence("android.conversationTitle", this.mConversationTitle);
            }

            if(!this.mMessages.isEmpty()) {
                arg3.putParcelableArray("android.messages", Message.getBundleArrayForMessages(this.mMessages));
            }
        }

        public MessagingStyle addMessage(Message arg2) {
            this.mMessages.add(arg2);
            if(this.mMessages.size() > 25) {
                this.mMessages.remove(0);
            }

            return this;
        }

        public MessagingStyle addMessage(CharSequence arg3, long arg4, CharSequence arg6) {
            this.mMessages.add(new Message(arg3, arg4, arg6));
            if(this.mMessages.size() > 25) {
                this.mMessages.remove(0);
            }

            return this;
        }

        @RestrictTo(value={Scope.LIBRARY_GROUP}) public void apply(NotificationBuilderWithBuilderAccessor arg11) {
            if(Build$VERSION.SDK_INT >= 24) {
                ArrayList v5 = new ArrayList();
                ArrayList v6 = new ArrayList();
                ArrayList v7 = new ArrayList();
                ArrayList v8 = new ArrayList();
                ArrayList v9 = new ArrayList();
                Iterator v0 = this.mMessages.iterator();
                while(v0.hasNext()) {
                    Object v1 = v0.next();
                    ((List)v5).add(((Message)v1).getText());
                    ((List)v6).add(Long.valueOf(((Message)v1).getTimestamp()));
                    ((List)v7).add(((Message)v1).getSender());
                    ((List)v8).add(((Message)v1).getDataMimeType());
                    ((List)v9).add(((Message)v1).getDataUri());
                }

                NotificationCompatApi24.addMessagingStyle(arg11, this.mUserDisplayName, this.mConversationTitle, ((List)v5), ((List)v6), ((List)v7), ((List)v8), ((List)v9));
            }
            else {
                Message v0_1 = this.findLatestIncomingMessage();
                if(this.mConversationTitle != null) {
                    arg11.getBuilder().setContentTitle(this.mConversationTitle);
                }
                else if(v0_1 != null) {
                    arg11.getBuilder().setContentTitle(v0_1.getSender());
                }

                if(v0_1 != null) {
                    Notification$Builder v1_1 = arg11.getBuilder();
                    CharSequence v0_2 = this.mConversationTitle != null ? this.makeMessageLine(v0_1) : v0_1.getText();
                    v1_1.setContentText(v0_2);
                }

                if(Build$VERSION.SDK_INT < 16) {
                    return;
                }

                SpannableStringBuilder v0_3 = new SpannableStringBuilder();
                int v1_2 = this.mConversationTitle != null || (this.hasMessagesWithoutSender()) ? 1 : 0;
                int v4;
                for(v4 = this.mMessages.size() - 1; v4 >= 0; --v4) {
                    Object v5_1 = this.mMessages.get(v4);
                    CharSequence v5_2 = v1_2 != 0 ? this.makeMessageLine(((Message)v5_1)) : ((Message)v5_1).getText();
                    if(v4 != this.mMessages.size() - 1) {
                        v0_3.insert(0, "\n");
                    }

                    v0_3.insert(0, v5_2);
                }

                NotificationCompatJellybean.addBigTextStyle(arg11, null, false, null, ((CharSequence)v0_3));
            }
        }

        public static MessagingStyle extractMessagingStyleFromNotification(Notification arg2) {
            Bundle v2 = NotificationCompat.getExtras(arg2);
            MessagingStyle v0 = null;
            if(v2 == null || (v2.containsKey("android.selfDisplayName"))) {
                try {
                    MessagingStyle v1 = new MessagingStyle();
                    v1.restoreFromCompatExtras(v2);
                    return v1;
                }
                catch(ClassCastException ) {
                    return v0;
                }
            }
            else {
            }

            return v0;
        }

        @Nullable private Message findLatestIncomingMessage() {
            int v0;
            for(v0 = this.mMessages.size() - 1; v0 >= 0; --v0) {
                Object v1 = this.mMessages.get(v0);
                if(!TextUtils.isEmpty(((Message)v1).getSender())) {
                    return ((Message)v1);
                }
            }

            if(!this.mMessages.isEmpty()) {
                return this.mMessages.get(this.mMessages.size() - 1);
            }

            return null;
        }

        public CharSequence getConversationTitle() {
            return this.mConversationTitle;
        }

        public List getMessages() {
            return this.mMessages;
        }

        public CharSequence getUserDisplayName() {
            return this.mUserDisplayName;
        }

        private boolean hasMessagesWithoutSender() {
            int v0;
            for(v0 = this.mMessages.size() - 1; v0 >= 0; --v0) {
                if(this.mMessages.get(v0).getSender() == null) {
                    return 1;
                }
            }

            return 0;
        }

        @NonNull private TextAppearanceSpan makeFontColorSpan(int arg8) {
            return new TextAppearanceSpan(null, 0, 0, ColorStateList.valueOf(arg8), null);
        }

        private CharSequence makeMessageLine(Message arg7) {
            String v7;
            String v4_1;
            BidiFormatter v0 = BidiFormatter.getInstance();
            SpannableStringBuilder v1 = new SpannableStringBuilder();
            int v2 = Build$VERSION.SDK_INT >= 21 ? 1 : 0;
            int v3 = v2 != 0 ? 0xFF000000 : -1;
            CharSequence v4 = arg7.getSender();
            if(TextUtils.isEmpty(arg7.getSender())) {
                if(this.mUserDisplayName == null) {
                    v4_1 = "";
                }
                else {
                    v4 = this.mUserDisplayName;
                }

                if(v2 == 0) {
                    goto label_28;
                }

                if(this.mBuilder.getColor() == 0) {
                    goto label_28;
                }

                v3 = this.mBuilder.getColor();
            }

        label_28:
            CharSequence v2_1 = v0.unicodeWrap(((CharSequence)v4_1));
            v1.append(v2_1);
            v1.setSpan(this.makeFontColorSpan(v3), v1.length() - v2_1.length(), v1.length(), 33);
            if(arg7.getText() == null) {
                v7 = "";
            }
            else {
                CharSequence v7_1 = arg7.getText();
            }

            v1.append("  ").append(v0.unicodeWrap(((CharSequence)v7)));
            return ((CharSequence)v1);
        }

        @RestrictTo(value={Scope.LIBRARY_GROUP}) protected void restoreFromCompatExtras(Bundle arg2) {
            this.mMessages.clear();
            this.mUserDisplayName = arg2.getString("android.selfDisplayName");
            this.mConversationTitle = arg2.getString("android.conversationTitle");
            Parcelable[] v2 = arg2.getParcelableArray("android.messages");
            if(v2 != null) {
                this.mMessages = Message.getMessagesFromBundleArray(v2);
            }
        }

        public MessagingStyle setConversationTitle(CharSequence arg1) {
            this.mConversationTitle = arg1;
            return this;
        }
    }

    @RequiresApi(value=16) class NotificationCompatApi16Impl extends NotificationCompatBaseImpl {
        NotificationCompatApi16Impl() {
            super();
        }

        public Notification build(android.support.v4.app.NotificationCompat$Builder arg36, BuilderExtender arg37) {
            android.support.v4.app.NotificationCompat$Builder v0 = arg36;
            android.support.v4.app.NotificationCompatJellybean$Builder v2 = new android.support.v4.app.NotificationCompatJellybean$Builder(v0.mContext, v0.mNotification, v0.mContentTitle, v0.mContentText, v0.mContentInfo, v0.mTickerView, v0.mNumber, v0.mContentIntent, v0.mFullScreenIntent, v0.mLargeIcon, v0.mProgressMax, v0.mProgress, v0.mProgressIndeterminate, v0.mUseChronometer, v0.mPriority, v0.mSubText, v0.mLocalOnly, v0.mExtras, v0.mGroupKey, v0.mGroupSummary, v0.mSortKey, v0.mContentView, v0.mBigContentView);
            NotificationCompat.addActionsToBuilder(((NotificationBuilderWithActions)v2), v0.mActions);
            if(v0.mStyle != null) {
                v0.mStyle.apply(((NotificationBuilderWithBuilderAccessor)v2));
            }

            Notification v1 = arg37.build(v0, ((NotificationBuilderWithBuilderAccessor)v2));
            if(v0.mStyle != null) {
                Bundle v2_1 = NotificationCompat.getExtras(v1);
                if(v2_1 != null) {
                    v0.mStyle.addCompatExtras(v2_1);
                }
            }

            return v1;
        }

        public Action getAction(Notification arg3, int arg4) {
            return NotificationCompatJellybean.getAction(arg3, arg4, Action.FACTORY, android.support.v4.app.RemoteInput.FACTORY);
        }

        public Action[] getActionsFromParcelableArrayList(ArrayList arg3) {
            return NotificationCompatJellybean.getActionsFromParcelableArrayList(arg3, Action.FACTORY, android.support.v4.app.RemoteInput.FACTORY);
        }

        public ArrayList getParcelableArrayListForActions(Action[] arg1) {
            return NotificationCompatJellybean.getParcelableArrayListForActions(((android.support.v4.app.NotificationCompatBase$Action[])arg1));
        }
    }

    @RequiresApi(value=19) class NotificationCompatApi19Impl extends NotificationCompatApi16Impl {
        NotificationCompatApi19Impl() {
            super();
        }

        public Notification build(android.support.v4.app.NotificationCompat$Builder arg40, BuilderExtender arg41) {
            android.support.v4.app.NotificationCompat$Builder v0 = arg40;
            android.support.v4.app.NotificationCompatKitKat$Builder v2 = new android.support.v4.app.NotificationCompatKitKat$Builder(v0.mContext, v0.mNotification, v0.mContentTitle, v0.mContentText, v0.mContentInfo, v0.mTickerView, v0.mNumber, v0.mContentIntent, v0.mFullScreenIntent, v0.mLargeIcon, v0.mProgressMax, v0.mProgress, v0.mProgressIndeterminate, v0.mShowWhen, v0.mUseChronometer, v0.mPriority, v0.mSubText, v0.mLocalOnly, v0.mPeople, v0.mExtras, v0.mGroupKey, v0.mGroupSummary, v0.mSortKey, v0.mContentView, v0.mBigContentView);
            NotificationCompat.addActionsToBuilder(((NotificationBuilderWithActions)v2), v0.mActions);
            if(v0.mStyle != null) {
                v0.mStyle.apply(((NotificationBuilderWithBuilderAccessor)v2));
            }

            return arg41.build(v0, ((NotificationBuilderWithBuilderAccessor)v2));
        }

        public Action getAction(Notification arg3, int arg4) {
            return NotificationCompatKitKat.getAction(arg3, arg4, Action.FACTORY, android.support.v4.app.RemoteInput.FACTORY);
        }
    }

    @RequiresApi(value=20) class NotificationCompatApi20Impl extends NotificationCompatApi19Impl {
        NotificationCompatApi20Impl() {
            super();
        }

        public Notification build(android.support.v4.app.NotificationCompat$Builder arg41, BuilderExtender arg42) {
            android.support.v4.app.NotificationCompat$Builder v0 = arg41;
            android.support.v4.app.NotificationCompatApi20$Builder v2 = new android.support.v4.app.NotificationCompatApi20$Builder(v0.mContext, v0.mNotification, v0.mContentTitle, v0.mContentText, v0.mContentInfo, v0.mTickerView, v0.mNumber, v0.mContentIntent, v0.mFullScreenIntent, v0.mLargeIcon, v0.mProgressMax, v0.mProgress, v0.mProgressIndeterminate, v0.mShowWhen, v0.mUseChronometer, v0.mPriority, v0.mSubText, v0.mLocalOnly, v0.mPeople, v0.mExtras, v0.mGroupKey, v0.mGroupSummary, v0.mSortKey, v0.mContentView, v0.mBigContentView, arg41.mGroupAlertBehavior);
            NotificationCompat.addActionsToBuilder(((NotificationBuilderWithActions)v2), v0.mActions);
            if(v0.mStyle != null) {
                v0.mStyle.apply(((NotificationBuilderWithBuilderAccessor)v2));
            }

            Notification v1 = arg42.build(v0, ((NotificationBuilderWithBuilderAccessor)v2));
            if(v0.mStyle != null) {
                v0.mStyle.addCompatExtras(NotificationCompat.getExtras(v1));
            }

            return v1;
        }

        public Action getAction(Notification arg3, int arg4) {
            return NotificationCompatApi20.getAction(arg3, arg4, Action.FACTORY, android.support.v4.app.RemoteInput.FACTORY);
        }

        public Action[] getActionsFromParcelableArrayList(ArrayList arg3) {
            return NotificationCompatApi20.getActionsFromParcelableArrayList(arg3, Action.FACTORY, android.support.v4.app.RemoteInput.FACTORY);
        }

        public ArrayList getParcelableArrayListForActions(Action[] arg1) {
            return NotificationCompatApi20.getParcelableArrayListForActions(((android.support.v4.app.NotificationCompatBase$Action[])arg1));
        }
    }

    @RequiresApi(value=21) class NotificationCompatApi21Impl extends NotificationCompatApi20Impl {
        NotificationCompatApi21Impl() {
            super();
        }

        public Notification build(android.support.v4.app.NotificationCompat$Builder arg51, BuilderExtender arg52) {
            android.support.v4.app.NotificationCompat$Builder v0 = arg51;
            android.support.v4.app.NotificationCompatApi21$Builder v2 = new android.support.v4.app.NotificationCompatApi21$Builder(v0.mContext, v0.mNotification, v0.mContentTitle, v0.mContentText, v0.mContentInfo, v0.mTickerView, v0.mNumber, v0.mContentIntent, v0.mFullScreenIntent, v0.mLargeIcon, v0.mProgressMax, v0.mProgress, v0.mProgressIndeterminate, v0.mShowWhen, v0.mUseChronometer, v0.mPriority, v0.mSubText, v0.mLocalOnly, v0.mCategory, v0.mPeople, v0.mExtras, v0.mColor, v0.mVisibility, v0.mPublicVersion, v0.mGroupKey, v0.mGroupSummary, v0.mSortKey, v0.mContentView, v0.mBigContentView, v0.mHeadsUpContentView, arg51.mGroupAlertBehavior);
            NotificationCompat.addActionsToBuilder(((NotificationBuilderWithActions)v2), v0.mActions);
            if(v0.mStyle != null) {
                v0.mStyle.apply(((NotificationBuilderWithBuilderAccessor)v2));
            }

            Notification v1 = arg52.build(v0, ((NotificationBuilderWithBuilderAccessor)v2));
            if(v0.mStyle != null) {
                v0.mStyle.addCompatExtras(NotificationCompat.getExtras(v1));
            }

            return v1;
        }

        public Bundle getBundleForUnreadConversation(android.support.v4.app.NotificationCompatBase$UnreadConversation arg1) {
            return NotificationCompatApi21.getBundleForUnreadConversation(arg1);
        }

        public android.support.v4.app.NotificationCompatBase$UnreadConversation getUnreadConversationFromBundle(Bundle arg1, android.support.v4.app.NotificationCompatBase$UnreadConversation$Factory arg2, android.support.v4.app.RemoteInputCompatBase$RemoteInput$Factory arg3) {
            return NotificationCompatApi21.getUnreadConversationFromBundle(arg1, arg2, arg3);
        }
    }

    @RequiresApi(value=24) class NotificationCompatApi24Impl extends NotificationCompatApi21Impl {
        NotificationCompatApi24Impl() {
            super();
        }

        public Notification build(android.support.v4.app.NotificationCompat$Builder arg53, BuilderExtender arg54) {
            android.support.v4.app.NotificationCompat$Builder v0 = arg53;
            android.support.v4.app.NotificationCompatApi24$Builder v2 = new android.support.v4.app.NotificationCompatApi24$Builder(v0.mContext, v0.mNotification, v0.mContentTitle, v0.mContentText, v0.mContentInfo, v0.mTickerView, v0.mNumber, v0.mContentIntent, v0.mFullScreenIntent, v0.mLargeIcon, v0.mProgressMax, v0.mProgress, v0.mProgressIndeterminate, v0.mShowWhen, v0.mUseChronometer, v0.mPriority, v0.mSubText, v0.mLocalOnly, v0.mCategory, v0.mPeople, v0.mExtras, v0.mColor, v0.mVisibility, v0.mPublicVersion, v0.mGroupKey, v0.mGroupSummary, v0.mSortKey, v0.mRemoteInputHistory, v0.mContentView, v0.mBigContentView, v0.mHeadsUpContentView, arg53.mGroupAlertBehavior);
            NotificationCompat.addActionsToBuilder(((NotificationBuilderWithActions)v2), v0.mActions);
            if(v0.mStyle != null) {
                v0.mStyle.apply(((NotificationBuilderWithBuilderAccessor)v2));
            }

            Notification v1 = arg54.build(v0, ((NotificationBuilderWithBuilderAccessor)v2));
            if(v0.mStyle != null) {
                v0.mStyle.addCompatExtras(NotificationCompat.getExtras(v1));
            }

            return v1;
        }

        public Action getAction(Notification arg3, int arg4) {
            return NotificationCompatApi24.getAction(arg3, arg4, Action.FACTORY, android.support.v4.app.RemoteInput.FACTORY);
        }

        public Action[] getActionsFromParcelableArrayList(ArrayList arg3) {
            return NotificationCompatApi24.getActionsFromParcelableArrayList(arg3, Action.FACTORY, android.support.v4.app.RemoteInput.FACTORY);
        }

        public ArrayList getParcelableArrayListForActions(Action[] arg1) {
            return NotificationCompatApi24.getParcelableArrayListForActions(((android.support.v4.app.NotificationCompatBase$Action[])arg1));
        }
    }

    @RequiresApi(value=26) class NotificationCompatApi26Impl extends NotificationCompatApi24Impl {
        NotificationCompatApi26Impl() {
            super();
        }

        public Notification build(android.support.v4.app.NotificationCompat$Builder arg67, BuilderExtender arg68) {
            android.support.v4.app.NotificationCompat$Builder v0 = arg67;
            android.support.v4.app.NotificationCompatApi26$Builder v2 = new android.support.v4.app.NotificationCompatApi26$Builder(v0.mContext, v0.mNotification, v0.mContentTitle, v0.mContentText, v0.mContentInfo, v0.mTickerView, v0.mNumber, v0.mContentIntent, v0.mFullScreenIntent, v0.mLargeIcon, v0.mProgressMax, v0.mProgress, v0.mProgressIndeterminate, v0.mShowWhen, v0.mUseChronometer, v0.mPriority, v0.mSubText, v0.mLocalOnly, v0.mCategory, v0.mPeople, v0.mExtras, v0.mColor, v0.mVisibility, v0.mPublicVersion, v0.mGroupKey, v0.mGroupSummary, v0.mSortKey, v0.mRemoteInputHistory, v0.mContentView, v0.mBigContentView, v0.mHeadsUpContentView, v0.mChannelId, v0.mBadgeIcon, v0.mShortcutId, v0.mTimeout, v0.mColorized, v0.mColorizedSet, arg67.mGroupAlertBehavior);
            NotificationCompat.addActionsToBuilder(((NotificationBuilderWithActions)v2), v0.mActions);
            if(v0.mStyle != null) {
                v0.mStyle.apply(((NotificationBuilderWithBuilderAccessor)v2));
            }

            Notification v1 = arg68.build(v0, ((NotificationBuilderWithBuilderAccessor)v2));
            if(v0.mStyle != null) {
                v0.mStyle.addCompatExtras(NotificationCompat.getExtras(v1));
            }

            return v1;
        }
    }

    class NotificationCompatBaseImpl implements NotificationCompatImpl {
        public class BuilderBase implements NotificationBuilderWithBuilderAccessor {
            private Notification$Builder mBuilder;

            BuilderBase(Context arg6, Notification arg7, CharSequence arg8, CharSequence arg9, CharSequence arg10, RemoteViews arg11, int arg12, PendingIntent arg13, PendingIntent arg14, Bitmap arg15, int arg16, int arg17, boolean arg18) {
                PendingIntent v0_1;
                Notification v0 = arg7;
                super();
                Notification$Builder v1 = new Notification$Builder(arg6).setWhen(v0.when).setSmallIcon(v0.icon, v0.iconLevel).setContent(v0.contentView).setTicker(v0.tickerText, arg11).setSound(v0.sound, v0.audioStreamType).setVibrate(v0.vibrate).setLights(v0.ledARGB, v0.ledOnMS, v0.ledOffMS);
                boolean v3 = false;
                boolean v2 = (v0.flags & 2) != 0 ? true : false;
                v1 = v1.setOngoing(v2);
                v2 = (v0.flags & 8) != 0 ? true : false;
                v1 = v1.setOnlyAlertOnce(v2);
                v2 = (v0.flags & 16) != 0 ? true : false;
                v1 = v1.setAutoCancel(v2).setDefaults(v0.defaults).setContentTitle(arg8).setContentText(arg9).setContentInfo(arg10).setContentIntent(arg13).setDeleteIntent(v0.deleteIntent);
                if((v0.flags & 0x80) != 0) {
                    v0_1 = arg14;
                    v3 = true;
                }
                else {
                    v0_1 = arg14;
                }

                this.mBuilder = v1.setFullScreenIntent(v0_1, v3).setLargeIcon(arg15).setNumber(arg12).setProgress(arg16, arg17, arg18);
            }

            public Notification build() {
                return this.mBuilder.getNotification();
            }

            public Notification$Builder getBuilder() {
                return this.mBuilder;
            }
        }

        NotificationCompatBaseImpl() {
            super();
        }

        public Notification build(android.support.v4.app.NotificationCompat$Builder arg17, BuilderExtender arg18) {
            return arg18.build(arg17, new BuilderBase(arg17.mContext, arg17.mNotification, arg17.mContentTitle, arg17.mContentText, arg17.mContentInfo, arg17.mTickerView, arg17.mNumber, arg17.mContentIntent, arg17.mFullScreenIntent, arg17.mLargeIcon, arg17.mProgressMax, arg17.mProgress, arg17.mProgressIndeterminate));
        }

        public Action getAction(Notification arg1, int arg2) {
            return null;
        }

        public Action[] getActionsFromParcelableArrayList(ArrayList arg1) {
            return null;
        }

        public Bundle getBundleForUnreadConversation(android.support.v4.app.NotificationCompatBase$UnreadConversation arg1) {
            return null;
        }

        public ArrayList getParcelableArrayListForActions(Action[] arg1) {
            return null;
        }

        public android.support.v4.app.NotificationCompatBase$UnreadConversation getUnreadConversationFromBundle(Bundle arg1, android.support.v4.app.NotificationCompatBase$UnreadConversation$Factory arg2, android.support.v4.app.RemoteInputCompatBase$RemoteInput$Factory arg3) {
            return null;
        }
    }

    interface NotificationCompatImpl {
        Notification build(android.support.v4.app.NotificationCompat$Builder arg1, BuilderExtender arg2);

        Action getAction(Notification arg1, int arg2);

        Action[] getActionsFromParcelableArrayList(ArrayList arg1);

        Bundle getBundleForUnreadConversation(android.support.v4.app.NotificationCompatBase$UnreadConversation arg1);

        ArrayList getParcelableArrayListForActions(Action[] arg1);

        android.support.v4.app.NotificationCompatBase$UnreadConversation getUnreadConversationFromBundle(Bundle arg1, android.support.v4.app.NotificationCompatBase$UnreadConversation$Factory arg2, android.support.v4.app.RemoteInputCompatBase$RemoteInput$Factory arg3);
    }

    @Retention(value=RetentionPolicy.SOURCE) @public interface NotificationVisibility {
    }

    public abstract class Style {
        CharSequence mBigContentTitle;
        @RestrictTo(value={Scope.LIBRARY_GROUP}) protected android.support.v4.app.NotificationCompat$Builder mBuilder;
        CharSequence mSummaryText;
        boolean mSummaryTextSet;

        public Style() {
            super();
            this.mSummaryTextSet = false;
        }

        @RestrictTo(value={Scope.LIBRARY_GROUP}) public void addCompatExtras(Bundle arg1) {
        }

        @RestrictTo(value={Scope.LIBRARY_GROUP}) public void apply(NotificationBuilderWithBuilderAccessor arg1) {
        }

        @RestrictTo(value={Scope.LIBRARY_GROUP}) public RemoteViews applyStandardTemplate(boolean arg18, int arg19, boolean arg20) {
            int v14;
            int v1;
            Style v0 = this;
            Resources v2 = v0.mBuilder.mContext.getResources();
            RemoteViews v9 = new RemoteViews(v0.mBuilder.mContext.getPackageName(), arg19);
            int v4 = -1;
            int v10 = 1;
            int v11 = 0;
            int v3 = v0.mBuilder.getPriority() < v4 ? 1 : 0;
            int v6 = 21;
            int v12 = 16;
            if(Build$VERSION.SDK_INT >= v12 && Build$VERSION.SDK_INT < v6) {
                if(v3 != 0) {
                    v9.setInt(id.notification_background, "setBackgroundResource", drawable.notification_bg_low);
                    v9.setInt(id.icon, "setBackgroundResource", drawable.notification_template_icon_low_bg);
                }
                else {
                    v9.setInt(id.notification_background, "setBackgroundResource", drawable.notification_bg);
                    v9.setInt(id.icon, "setBackgroundResource", drawable.notification_template_icon_bg);
                }
            }

            int v13 = 8;
            if(v0.mBuilder.mLargeIcon != null) {
                if(Build$VERSION.SDK_INT >= v12) {
                    v9.setViewVisibility(id.icon, 0);
                    v9.setImageViewBitmap(id.icon, v0.mBuilder.mLargeIcon);
                }
                else {
                    v9.setViewVisibility(id.icon, v13);
                }

                if(!arg18) {
                    goto label_120;
                }

                if(v0.mBuilder.mNotification.icon == 0) {
                    goto label_120;
                }

                v1 = v2.getDimensionPixelSize(dimen.notification_right_icon_size);
                v3 = v1 - v2.getDimensionPixelSize(dimen.notification_small_icon_background_padding) * 2;
                if(Build$VERSION.SDK_INT >= v6) {
                    v9.setImageViewBitmap(id.right_icon, v0.createIconWithBackground(v0.mBuilder.mNotification.icon, v1, v3, v0.mBuilder.getColor()));
                }
                else {
                    v9.setImageViewBitmap(id.right_icon, v0.createColoredBitmap(v0.mBuilder.mNotification.icon, v4));
                }

                v9.setViewVisibility(id.right_icon, 0);
            }
            else {
                if(!arg18) {
                    goto label_120;
                }

                if(v0.mBuilder.mNotification.icon == 0) {
                    goto label_120;
                }

                v9.setViewVisibility(id.icon, 0);
                if(Build$VERSION.SDK_INT >= v6) {
                    v9.setImageViewBitmap(id.icon, v0.createIconWithBackground(v0.mBuilder.mNotification.icon, v2.getDimensionPixelSize(dimen.notification_large_icon_width) - v2.getDimensionPixelSize(dimen.notification_big_circle_margin), v2.getDimensionPixelSize(dimen.notification_small_icon_size_as_large), v0.mBuilder.getColor()));
                    goto label_120;
                }

                v9.setImageViewBitmap(id.icon, v0.createColoredBitmap(v0.mBuilder.mNotification.icon, v4));
            }

        label_120:
            if(v0.mBuilder.mContentTitle != null) {
                v9.setTextViewText(id.title, v0.mBuilder.mContentTitle);
            }

            if(v0.mBuilder.mContentText != null) {
                v9.setTextViewText(id.text, v0.mBuilder.mContentText);
                v1 = 1;
            }
            else {
                v1 = 0;
            }

            v3 = Build$VERSION.SDK_INT >= v6 || v0.mBuilder.mLargeIcon == null ? 0 : 1;
            if(v0.mBuilder.mContentInfo != null) {
                v9.setTextViewText(id.info, v0.mBuilder.mContentInfo);
                v9.setViewVisibility(id.info, 0);
                goto label_154;
            }
            else if(v0.mBuilder.mNumber > 0) {
                if(v0.mBuilder.mNumber > v2.getInteger(integer.status_bar_notification_info_maxnum)) {
                    v9.setTextViewText(id.info, v2.getString(string.status_bar_notification_info_overflow));
                }
                else {
                    v9.setTextViewText(id.info, NumberFormat.getIntegerInstance().format(((long)v0.mBuilder.mNumber)));
                }

                v9.setViewVisibility(id.info, 0);
            label_154:
                v1 = 1;
                v14 = 1;
            }
            else {
                v9.setViewVisibility(id.info, v13);
                v14 = v1;
                v1 = v3;
            }

            if(v0.mBuilder.mSubText == null || Build$VERSION.SDK_INT < v12) {
            label_206:
                v3 = 0;
            }
            else {
                v9.setTextViewText(id.text, v0.mBuilder.mSubText);
                if(v0.mBuilder.mContentText != null) {
                    v9.setTextViewText(id.text2, v0.mBuilder.mContentText);
                    v9.setViewVisibility(id.text2, 0);
                    v3 = 1;
                }
                else {
                    v9.setViewVisibility(id.text2, v13);
                    goto label_206;
                }
            }

            if(v3 != 0 && Build$VERSION.SDK_INT >= v12) {
                if(arg20) {
                    v9.setTextViewTextSize(id.text, 0, ((float)v2.getDimensionPixelSize(dimen.notification_subtext_size)));
                }

                v9.setViewPadding(id.line1, 0, 0, 0, 0);
            }

            if(v0.mBuilder.getWhenIfShowing() != 0) {
                if((v0.mBuilder.mUseChronometer) && Build$VERSION.SDK_INT >= v12) {
                    v9.setViewVisibility(id.chronometer, 0);
                    v9.setLong(id.chronometer, "setBase", v0.mBuilder.getWhenIfShowing() + (SystemClock.elapsedRealtime() - System.currentTimeMillis()));
                    v9.setBoolean(id.chronometer, "setStarted", true);
                    goto label_256;
                }

                v9.setViewVisibility(id.time, 0);
                v9.setLong(id.time, "setTime", v0.mBuilder.getWhenIfShowing());
            }
            else {
                v10 = v1;
            }

        label_256:
            v1 = id.right_side;
            int v2_1 = v10 != 0 ? 0 : 8;
            v9.setViewVisibility(v1, v2_1);
            v1 = id.line3;
            if(v14 != 0) {
            }
            else {
                v11 = 8;
            }

            v9.setViewVisibility(v1, v11);
            return v9;
        }

        public Notification build() {
            Notification v0 = this.mBuilder != null ? this.mBuilder.build() : null;
            return v0;
        }

        @RestrictTo(value={Scope.LIBRARY_GROUP}) public void buildIntoRemoteViews(RemoteViews arg8, RemoteViews arg9) {
            this.hideNormalContent(arg8);
            arg8.removeAllViews(id.notification_main_column);
            arg8.addView(id.notification_main_column, arg9.clone());
            arg8.setViewVisibility(id.notification_main_column, 0);
            if(Build$VERSION.SDK_INT >= 21) {
                arg8.setViewPadding(id.notification_main_column_container, 0, this.calculateTopPadding(), 0, 0);
            }
        }

        private int calculateTopPadding() {
            Resources v0 = this.mBuilder.mContext.getResources();
            int v1 = v0.getDimensionPixelSize(dimen.notification_top_pad);
            int v2 = v0.getDimensionPixelSize(dimen.notification_top_pad_large_text);
            float v0_1 = (Style.constrain(v0.getConfiguration().fontScale, 1f, 1.3f) - 1f) / 0.3f;
            return Math.round((1f - v0_1) * (((float)v1)) + v0_1 * (((float)v2)));
        }

        private static float constrain(float arg1, float arg2, float arg3) {
            if(arg1 < arg2) {
                arg1 = arg2;
            }
            else if(arg1 > arg3) {
                arg1 = arg3;
            }

            return arg1;
        }

        private Bitmap createColoredBitmap(int arg4, int arg5, int arg6) {
            Drawable v4 = this.mBuilder.mContext.getResources().getDrawable(arg4);
            int v0 = arg6 == 0 ? v4.getIntrinsicWidth() : arg6;
            if(arg6 == 0) {
                arg6 = v4.getIntrinsicHeight();
            }

            Bitmap v1 = Bitmap.createBitmap(v0, arg6, Bitmap$Config.ARGB_8888);
            v4.setBounds(0, 0, v0, arg6);
            if(arg5 != 0) {
                v4.mutate().setColorFilter(new PorterDuffColorFilter(arg5, PorterDuff$Mode.SRC_IN));
            }

            v4.draw(new Canvas(v1));
            return v1;
        }

        @RestrictTo(value={Scope.LIBRARY_GROUP}) public Bitmap createColoredBitmap(int arg2, int arg3) {
            return this.createColoredBitmap(arg2, arg3, 0);
        }

        private Bitmap createIconWithBackground(int arg3, int arg4, int arg5, int arg6) {
            Bitmap v6 = this.createColoredBitmap(drawable.notification_icon_background, 0, arg4);
            Canvas v0 = new Canvas(v6);
            Drawable v3 = this.mBuilder.mContext.getResources().getDrawable(arg3).mutate();
            v3.setFilterBitmap(true);
            arg4 = (arg4 - arg5) / 2;
            arg5 += arg4;
            v3.setBounds(arg4, arg4, arg5, arg5);
            v3.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff$Mode.SRC_ATOP));
            v3.draw(v0);
            return v6;
        }

        private void hideNormalContent(RemoteViews arg3) {
            arg3.setViewVisibility(id.title, 8);
            arg3.setViewVisibility(id.text2, 8);
            arg3.setViewVisibility(id.text, 8);
        }

        @RestrictTo(value={Scope.LIBRARY_GROUP}) public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor arg1) {
            return null;
        }

        @RestrictTo(value={Scope.LIBRARY_GROUP}) public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor arg1) {
            return null;
        }

        @RestrictTo(value={Scope.LIBRARY_GROUP}) public RemoteViews makeHeadsUpContentView(NotificationBuilderWithBuilderAccessor arg1) {
            return null;
        }

        @RestrictTo(value={Scope.LIBRARY_GROUP}) protected void restoreFromCompatExtras(Bundle arg1) {
        }

        public void setBuilder(android.support.v4.app.NotificationCompat$Builder arg2) {
            if(this.mBuilder != arg2) {
                this.mBuilder = arg2;
                if(this.mBuilder != null) {
                    this.mBuilder.setStyle(this);
                }
            }
        }
    }

    public final class android.support.v4.app.NotificationCompat$WearableExtender implements android.support.v4.app.NotificationCompat$Extender {
        private static final int DEFAULT_CONTENT_ICON_GRAVITY = 0x800005;
        private static final int DEFAULT_FLAGS = 1;
        private static final int DEFAULT_GRAVITY = 80;
        private static final String EXTRA_WEARABLE_EXTENSIONS = "android.wearable.EXTENSIONS";
        private static final int FLAG_BIG_PICTURE_AMBIENT = 0x20;
        private static final int FLAG_CONTENT_INTENT_AVAILABLE_OFFLINE = 1;
        private static final int FLAG_HINT_AVOID_BACKGROUND_CLIPPING = 16;
        private static final int FLAG_HINT_CONTENT_INTENT_LAUNCHES_ACTIVITY = 0x40;
        private static final int FLAG_HINT_HIDE_ICON = 2;
        private static final int FLAG_HINT_SHOW_BACKGROUND_ONLY = 4;
        private static final int FLAG_START_SCROLL_BOTTOM = 8;
        private static final String KEY_ACTIONS = "actions";
        private static final String KEY_BACKGROUND = "background";
        private static final String KEY_BRIDGE_TAG = "bridgeTag";
        private static final String KEY_CONTENT_ACTION_INDEX = "contentActionIndex";
        private static final String KEY_CONTENT_ICON = "contentIcon";
        private static final String KEY_CONTENT_ICON_GRAVITY = "contentIconGravity";
        private static final String KEY_CUSTOM_CONTENT_HEIGHT = "customContentHeight";
        private static final String KEY_CUSTOM_SIZE_PRESET = "customSizePreset";
        private static final String KEY_DISMISSAL_ID = "dismissalId";
        private static final String KEY_DISPLAY_INTENT = "displayIntent";
        private static final String KEY_FLAGS = "flags";
        private static final String KEY_GRAVITY = "gravity";
        private static final String KEY_HINT_SCREEN_TIMEOUT = "hintScreenTimeout";
        private static final String KEY_PAGES = "pages";
        public static final int SCREEN_TIMEOUT_LONG = -1;
        public static final int SCREEN_TIMEOUT_SHORT = 0;
        public static final int SIZE_DEFAULT = 0;
        public static final int SIZE_FULL_SCREEN = 5;
        public static final int SIZE_LARGE = 4;
        public static final int SIZE_MEDIUM = 3;
        public static final int SIZE_SMALL = 2;
        public static final int SIZE_XSMALL = 1;
        public static final int UNSET_ACTION_INDEX = -1;
        private ArrayList mActions;
        private Bitmap mBackground;
        private String mBridgeTag;
        private int mContentActionIndex;
        private int mContentIcon;
        private int mContentIconGravity;
        private int mCustomContentHeight;
        private int mCustomSizePreset;
        private String mDismissalId;
        private PendingIntent mDisplayIntent;
        private int mFlags;
        private int mGravity;
        private int mHintScreenTimeout;
        private ArrayList mPages;

        public android.support.v4.app.NotificationCompat$WearableExtender() {
            super();
            this.mActions = new ArrayList();
            this.mFlags = 1;
            this.mPages = new ArrayList();
            this.mContentIconGravity = 0x800005;
            this.mContentActionIndex = -1;
            this.mCustomSizePreset = 0;
            this.mGravity = 80;
        }

        public android.support.v4.app.NotificationCompat$WearableExtender(Notification arg8) {
            super();
            this.mActions = new ArrayList();
            this.mFlags = 1;
            this.mPages = new ArrayList();
            int v1 = 0x800005;
            this.mContentIconGravity = v1;
            int v2 = -1;
            this.mContentActionIndex = v2;
            this.mCustomSizePreset = 0;
            int v4 = 80;
            this.mGravity = v4;
            Bundle v8 = NotificationCompat.getExtras(arg8);
            v8 = v8 != null ? v8.getBundle("android.wearable.EXTENSIONS") : null;
            if(v8 != null) {
                Action[] v5 = NotificationCompat.IMPL.getActionsFromParcelableArrayList(v8.getParcelableArrayList("actions"));
                if(v5 != null) {
                    Collections.addAll(this.mActions, ((Object[])v5));
                }

                this.mFlags = v8.getInt("flags", 1);
                this.mDisplayIntent = v8.getParcelable("displayIntent");
                Notification[] v0 = NotificationCompat.getNotificationArrayFromBundle(v8, "pages");
                if(v0 != null) {
                    Collections.addAll(this.mPages, ((Object[])v0));
                }

                this.mBackground = v8.getParcelable("background");
                this.mContentIcon = v8.getInt("contentIcon");
                this.mContentIconGravity = v8.getInt("contentIconGravity", v1);
                this.mContentActionIndex = v8.getInt("contentActionIndex", v2);
                this.mCustomSizePreset = v8.getInt("customSizePreset", 0);
                this.mCustomContentHeight = v8.getInt("customContentHeight");
                this.mGravity = v8.getInt("gravity", v4);
                this.mHintScreenTimeout = v8.getInt("hintScreenTimeout");
                this.mDismissalId = v8.getString("dismissalId");
                this.mBridgeTag = v8.getString("bridgeTag");
            }
        }

        public android.support.v4.app.NotificationCompat$WearableExtender addAction(Action arg2) {
            this.mActions.add(arg2);
            return this;
        }

        public android.support.v4.app.NotificationCompat$WearableExtender addActions(List arg2) {
            this.mActions.addAll(((Collection)arg2));
            return this;
        }

        public android.support.v4.app.NotificationCompat$WearableExtender addPage(Notification arg2) {
            this.mPages.add(arg2);
            return this;
        }

        public android.support.v4.app.NotificationCompat$WearableExtender addPages(List arg2) {
            this.mPages.addAll(((Collection)arg2));
            return this;
        }

        public android.support.v4.app.NotificationCompat$WearableExtender clearActions() {
            this.mActions.clear();
            return this;
        }

        public android.support.v4.app.NotificationCompat$WearableExtender clearPages() {
            this.mPages.clear();
            return this;
        }

        public android.support.v4.app.NotificationCompat$WearableExtender clone() {
            android.support.v4.app.NotificationCompat$WearableExtender v0 = new android.support.v4.app.NotificationCompat$WearableExtender();
            v0.mActions = new ArrayList(this.mActions);
            v0.mFlags = this.mFlags;
            v0.mDisplayIntent = this.mDisplayIntent;
            v0.mPages = new ArrayList(this.mPages);
            v0.mBackground = this.mBackground;
            v0.mContentIcon = this.mContentIcon;
            v0.mContentIconGravity = this.mContentIconGravity;
            v0.mContentActionIndex = this.mContentActionIndex;
            v0.mCustomSizePreset = this.mCustomSizePreset;
            v0.mCustomContentHeight = this.mCustomContentHeight;
            v0.mGravity = this.mGravity;
            v0.mHintScreenTimeout = this.mHintScreenTimeout;
            v0.mDismissalId = this.mDismissalId;
            v0.mBridgeTag = this.mBridgeTag;
            return v0;
        }

        public Object clone() throws CloneNotSupportedException {
            return this.clone();
        }

        public android.support.v4.app.NotificationCompat$Builder extend(android.support.v4.app.NotificationCompat$Builder arg6) {
            Bundle v0 = new Bundle();
            if(!this.mActions.isEmpty()) {
                v0.putParcelableArrayList("actions", NotificationCompat.IMPL.getParcelableArrayListForActions(this.mActions.toArray(new Action[this.mActions.size()])));
            }

            if(this.mFlags != 1) {
                v0.putInt("flags", this.mFlags);
            }

            if(this.mDisplayIntent != null) {
                v0.putParcelable("displayIntent", this.mDisplayIntent);
            }

            if(!this.mPages.isEmpty()) {
                v0.putParcelableArray("pages", this.mPages.toArray(new Notification[this.mPages.size()]));
            }

            if(this.mBackground != null) {
                v0.putParcelable("background", this.mBackground);
            }

            if(this.mContentIcon != 0) {
                v0.putInt("contentIcon", this.mContentIcon);
            }

            if(this.mContentIconGravity != 0x800005) {
                v0.putInt("contentIconGravity", this.mContentIconGravity);
            }

            if(this.mContentActionIndex != -1) {
                v0.putInt("contentActionIndex", this.mContentActionIndex);
            }

            if(this.mCustomSizePreset != 0) {
                v0.putInt("customSizePreset", this.mCustomSizePreset);
            }

            if(this.mCustomContentHeight != 0) {
                v0.putInt("customContentHeight", this.mCustomContentHeight);
            }

            if(this.mGravity != 80) {
                v0.putInt("gravity", this.mGravity);
            }

            if(this.mHintScreenTimeout != 0) {
                v0.putInt("hintScreenTimeout", this.mHintScreenTimeout);
            }

            if(this.mDismissalId != null) {
                v0.putString("dismissalId", this.mDismissalId);
            }

            if(this.mBridgeTag != null) {
                v0.putString("bridgeTag", this.mBridgeTag);
            }

            arg6.getExtras().putBundle("android.wearable.EXTENSIONS", v0);
            return arg6;
        }

        public List getActions() {
            return this.mActions;
        }

        public Bitmap getBackground() {
            return this.mBackground;
        }

        public String getBridgeTag() {
            return this.mBridgeTag;
        }

        public int getContentAction() {
            return this.mContentActionIndex;
        }

        public int getContentIcon() {
            return this.mContentIcon;
        }

        public int getContentIconGravity() {
            return this.mContentIconGravity;
        }

        public boolean getContentIntentAvailableOffline() {
            boolean v1 = true;
            if((this.mFlags & 1) != 0) {
            }
            else {
                v1 = false;
            }

            return v1;
        }

        public int getCustomContentHeight() {
            return this.mCustomContentHeight;
        }

        public int getCustomSizePreset() {
            return this.mCustomSizePreset;
        }

        public String getDismissalId() {
            return this.mDismissalId;
        }

        public PendingIntent getDisplayIntent() {
            return this.mDisplayIntent;
        }

        public int getGravity() {
            return this.mGravity;
        }

        public boolean getHintAmbientBigPicture() {
            boolean v0 = (this.mFlags & 0x20) != 0 ? true : false;
            return v0;
        }

        public boolean getHintAvoidBackgroundClipping() {
            boolean v0 = (this.mFlags & 16) != 0 ? true : false;
            return v0;
        }

        public boolean getHintContentIntentLaunchesActivity() {
            boolean v0 = (this.mFlags & 0x40) != 0 ? true : false;
            return v0;
        }

        public boolean getHintHideIcon() {
            boolean v0 = (this.mFlags & 2) != 0 ? true : false;
            return v0;
        }

        public int getHintScreenTimeout() {
            return this.mHintScreenTimeout;
        }

        public boolean getHintShowBackgroundOnly() {
            boolean v0 = (this.mFlags & 4) != 0 ? true : false;
            return v0;
        }

        public List getPages() {
            return this.mPages;
        }

        public boolean getStartScrollBottom() {
            boolean v0 = (this.mFlags & 8) != 0 ? true : false;
            return v0;
        }

        public android.support.v4.app.NotificationCompat$WearableExtender setBackground(Bitmap arg1) {
            this.mBackground = arg1;
            return this;
        }

        public android.support.v4.app.NotificationCompat$WearableExtender setBridgeTag(String arg1) {
            this.mBridgeTag = arg1;
            return this;
        }

        public android.support.v4.app.NotificationCompat$WearableExtender setContentAction(int arg1) {
            this.mContentActionIndex = arg1;
            return this;
        }

        public android.support.v4.app.NotificationCompat$WearableExtender setContentIcon(int arg1) {
            this.mContentIcon = arg1;
            return this;
        }

        public android.support.v4.app.NotificationCompat$WearableExtender setContentIconGravity(int arg1) {
            this.mContentIconGravity = arg1;
            return this;
        }

        public android.support.v4.app.NotificationCompat$WearableExtender setContentIntentAvailableOffline(boolean arg2) {
            this.setFlag(1, arg2);
            return this;
        }

        public android.support.v4.app.NotificationCompat$WearableExtender setCustomContentHeight(int arg1) {
            this.mCustomContentHeight = arg1;
            return this;
        }

        public android.support.v4.app.NotificationCompat$WearableExtender setCustomSizePreset(int arg1) {
            this.mCustomSizePreset = arg1;
            return this;
        }

        public android.support.v4.app.NotificationCompat$WearableExtender setDismissalId(String arg1) {
            this.mDismissalId = arg1;
            return this;
        }

        public android.support.v4.app.NotificationCompat$WearableExtender setDisplayIntent(PendingIntent arg1) {
            this.mDisplayIntent = arg1;
            return this;
        }

        private void setFlag(int arg1, boolean arg2) {
            if(arg2) {
                this.mFlags |= arg1;
            }
            else {
                this.mFlags &= arg1 ^ -1;
            }
        }

        public android.support.v4.app.NotificationCompat$WearableExtender setGravity(int arg1) {
            this.mGravity = arg1;
            return this;
        }

        public android.support.v4.app.NotificationCompat$WearableExtender setHintAmbientBigPicture(boolean arg2) {
            this.setFlag(0x20, arg2);
            return this;
        }

        public android.support.v4.app.NotificationCompat$WearableExtender setHintAvoidBackgroundClipping(boolean arg2) {
            this.setFlag(16, arg2);
            return this;
        }

        public android.support.v4.app.NotificationCompat$WearableExtender setHintContentIntentLaunchesActivity(boolean arg2) {
            this.setFlag(0x40, arg2);
            return this;
        }

        public android.support.v4.app.NotificationCompat$WearableExtender setHintHideIcon(boolean arg2) {
            this.setFlag(2, arg2);
            return this;
        }

        public android.support.v4.app.NotificationCompat$WearableExtender setHintScreenTimeout(int arg1) {
            this.mHintScreenTimeout = arg1;
            return this;
        }

        public android.support.v4.app.NotificationCompat$WearableExtender setHintShowBackgroundOnly(boolean arg2) {
            this.setFlag(4, arg2);
            return this;
        }

        public android.support.v4.app.NotificationCompat$WearableExtender setStartScrollBottom(boolean arg2) {
            this.setFlag(8, arg2);
            return this;
        }
    }

    public static final int BADGE_ICON_LARGE = 2;
    public static final int BADGE_ICON_NONE = 0;
    public static final int BADGE_ICON_SMALL = 1;
    public static final String CATEGORY_ALARM = "alarm";
    public static final String CATEGORY_CALL = "call";
    public static final String CATEGORY_EMAIL = "email";
    public static final String CATEGORY_ERROR = "err";
    public static final String CATEGORY_EVENT = "event";
    public static final String CATEGORY_MESSAGE = "msg";
    public static final String CATEGORY_PROGRESS = "progress";
    public static final String CATEGORY_PROMO = "promo";
    public static final String CATEGORY_RECOMMENDATION = "recommendation";
    public static final String CATEGORY_REMINDER = "reminder";
    public static final String CATEGORY_SERVICE = "service";
    public static final String CATEGORY_SOCIAL = "social";
    public static final String CATEGORY_STATUS = "status";
    public static final String CATEGORY_SYSTEM = "sys";
    public static final String CATEGORY_TRANSPORT = "transport";
    @ColorInt public static final int COLOR_DEFAULT = 0;
    public static final int DEFAULT_ALL = -1;
    public static final int DEFAULT_LIGHTS = 4;
    public static final int DEFAULT_SOUND = 1;
    public static final int DEFAULT_VIBRATE = 2;
    public static final String EXTRA_AUDIO_CONTENTS_URI = "android.audioContents";
    public static final String EXTRA_BACKGROUND_IMAGE_URI = "android.backgroundImageUri";
    public static final String EXTRA_BIG_TEXT = "android.bigText";
    public static final String EXTRA_COMPACT_ACTIONS = "android.compactActions";
    public static final String EXTRA_CONVERSATION_TITLE = "android.conversationTitle";
    public static final String EXTRA_INFO_TEXT = "android.infoText";
    public static final String EXTRA_LARGE_ICON = "android.largeIcon";
    public static final String EXTRA_LARGE_ICON_BIG = "android.largeIcon.big";
    public static final String EXTRA_MEDIA_SESSION = "android.mediaSession";
    public static final String EXTRA_MESSAGES = "android.messages";
    public static final String EXTRA_PEOPLE = "android.people";
    public static final String EXTRA_PICTURE = "android.picture";
    public static final String EXTRA_PROGRESS = "android.progress";
    public static final String EXTRA_PROGRESS_INDETERMINATE = "android.progressIndeterminate";
    public static final String EXTRA_PROGRESS_MAX = "android.progressMax";
    public static final String EXTRA_REMOTE_INPUT_HISTORY = "android.remoteInputHistory";
    public static final String EXTRA_SELF_DISPLAY_NAME = "android.selfDisplayName";
    public static final String EXTRA_SHOW_CHRONOMETER = "android.showChronometer";
    public static final String EXTRA_SHOW_WHEN = "android.showWhen";
    public static final String EXTRA_SMALL_ICON = "android.icon";
    public static final String EXTRA_SUB_TEXT = "android.subText";
    public static final String EXTRA_SUMMARY_TEXT = "android.summaryText";
    public static final String EXTRA_TEMPLATE = "android.template";
    public static final String EXTRA_TEXT = "android.text";
    public static final String EXTRA_TEXT_LINES = "android.textLines";
    public static final String EXTRA_TITLE = "android.title";
    public static final String EXTRA_TITLE_BIG = "android.title.big";
    public static final int FLAG_AUTO_CANCEL = 16;
    public static final int FLAG_FOREGROUND_SERVICE = 0x40;
    public static final int FLAG_GROUP_SUMMARY = 0x200;
    @Deprecated public static final int FLAG_HIGH_PRIORITY = 0x80;
    public static final int FLAG_INSISTENT = 4;
    public static final int FLAG_LOCAL_ONLY = 0x100;
    public static final int FLAG_NO_CLEAR = 0x20;
    public static final int FLAG_ONGOING_EVENT = 2;
    public static final int FLAG_ONLY_ALERT_ONCE = 8;
    public static final int FLAG_SHOW_LIGHTS = 1;
    public static final int GROUP_ALERT_ALL = 0;
    public static final int GROUP_ALERT_CHILDREN = 2;
    public static final int GROUP_ALERT_SUMMARY = 1;
    static final NotificationCompatImpl IMPL = null;
    public static final int PRIORITY_DEFAULT = 0;
    public static final int PRIORITY_HIGH = 1;
    public static final int PRIORITY_LOW = -1;
    public static final int PRIORITY_MAX = 2;
    public static final int PRIORITY_MIN = -2;
    public static final int STREAM_DEFAULT = -1;
    public static final int VISIBILITY_PRIVATE = 0;
    public static final int VISIBILITY_PUBLIC = 1;
    public static final int VISIBILITY_SECRET = -1;

    static {
        if(Build$VERSION.SDK_INT >= 26) {
            NotificationCompat.IMPL = new NotificationCompatApi26Impl();
        }
        else if(Build$VERSION.SDK_INT >= 24) {
            NotificationCompat.IMPL = new NotificationCompatApi24Impl();
        }
        else if(Build$VERSION.SDK_INT >= 21) {
            NotificationCompat.IMPL = new NotificationCompatApi21Impl();
        }
        else if(Build$VERSION.SDK_INT >= 20) {
            NotificationCompat.IMPL = new NotificationCompatApi20Impl();
        }
        else if(Build$VERSION.SDK_INT >= 19) {
            NotificationCompat.IMPL = new NotificationCompatApi19Impl();
        }
        else if(Build$VERSION.SDK_INT >= 16) {
            NotificationCompat.IMPL = new NotificationCompatApi16Impl();
        }
        else {
            NotificationCompat.IMPL = new NotificationCompatBaseImpl();
        }
    }

    public NotificationCompat() {
        super();
    }

    static void addActionsToBuilder(NotificationBuilderWithActions arg1, ArrayList arg2) {
        Iterator v2 = arg2.iterator();
        while(v2.hasNext()) {
            arg1.addAction(v2.next());
        }
    }

    public static Action getAction(Notification arg1, int arg2) {
        return NotificationCompat.IMPL.getAction(arg1, arg2);
    }

    public static int getActionCount(Notification arg3) {
        int v1 = 0;
        if(Build$VERSION.SDK_INT >= 19) {
            if(arg3.actions != null) {
                v1 = arg3.actions.length;
            }

            return v1;
        }

        if(Build$VERSION.SDK_INT >= 16) {
            return NotificationCompatJellybean.getActionCount(arg3);
        }

        return 0;
    }

    public static int getBadgeIconType(Notification arg2) {
        if(Build$VERSION.SDK_INT >= 26) {
            return arg2.getBadgeIconType();
        }

        return 0;
    }

    public static String getCategory(Notification arg2) {
        if(Build$VERSION.SDK_INT >= 21) {
            return arg2.category;
        }

        return null;
    }

    public static String getChannelId(Notification arg2) {
        if(Build$VERSION.SDK_INT >= 26) {
            return arg2.getChannelId();
        }

        return null;
    }

    public static Bundle getExtras(Notification arg2) {
        if(Build$VERSION.SDK_INT >= 19) {
            return arg2.extras;
        }

        if(Build$VERSION.SDK_INT >= 16) {
            return NotificationCompatJellybean.getExtras(arg2);
        }

        return null;
    }

    public static String getGroup(Notification arg2) {
        if(Build$VERSION.SDK_INT >= 20) {
            return arg2.getGroup();
        }

        if(Build$VERSION.SDK_INT >= 19) {
            return arg2.extras.getString("android.support.groupKey");
        }

        if(Build$VERSION.SDK_INT >= 16) {
            return NotificationCompatJellybean.getExtras(arg2).getString("android.support.groupKey");
        }

        return null;
    }

    public static int getGroupAlertBehavior(Notification arg2) {
        if(Build$VERSION.SDK_INT >= 26) {
            return arg2.getGroupAlertBehavior();
        }

        return 0;
    }

    public static boolean getLocalOnly(Notification arg3) {
        boolean v1 = false;
        if(Build$VERSION.SDK_INT >= 20) {
            if((arg3.flags & 0x100) != 0) {
                v1 = true;
            }

            return v1;
        }

        if(Build$VERSION.SDK_INT >= 19) {
            return arg3.extras.getBoolean("android.support.localOnly");
        }

        if(Build$VERSION.SDK_INT >= 16) {
            return NotificationCompatJellybean.getExtras(arg3).getBoolean("android.support.localOnly");
        }

        return 0;
    }

    static Notification[] getNotificationArrayFromBundle(Bundle arg4, String arg5) {
        Parcelable[] v0 = arg4.getParcelableArray(arg5);
        if(!(v0 instanceof Notification[])) {
            if(v0 == null) {
            }
            else {
                Notification[] v1 = new Notification[v0.length];
                int v2;
                for(v2 = 0; v2 < v0.length; ++v2) {
                    v1[v2] = v0[v2];
                }

                arg4.putParcelableArray(arg5, ((Parcelable[])v1));
                return v1;
            }
        }

        return ((Notification[])v0);
    }

    public static String getShortcutId(Notification arg2) {
        if(Build$VERSION.SDK_INT >= 26) {
            return arg2.getShortcutId();
        }

        return null;
    }

    public static String getSortKey(Notification arg2) {
        if(Build$VERSION.SDK_INT >= 20) {
            return arg2.getSortKey();
        }

        if(Build$VERSION.SDK_INT >= 19) {
            return arg2.extras.getString("android.support.sortKey");
        }

        if(Build$VERSION.SDK_INT >= 16) {
            return NotificationCompatJellybean.getExtras(arg2).getString("android.support.sortKey");
        }

        return null;
    }

    public static long getTimeoutAfter(Notification arg2) {
        if(Build$VERSION.SDK_INT >= 26) {
            return arg2.getTimeoutAfter();
        }

        return 0;
    }

    public static boolean isGroupSummary(Notification arg3) {
        boolean v1 = false;
        if(Build$VERSION.SDK_INT >= 20) {
            if((arg3.flags & 0x200) != 0) {
                v1 = true;
            }

            return v1;
        }

        if(Build$VERSION.SDK_INT >= 19) {
            return arg3.extras.getBoolean("android.support.isGroupSummary");
        }

        if(Build$VERSION.SDK_INT >= 16) {
            return NotificationCompatJellybean.getExtras(arg3).getBoolean("android.support.isGroupSummary");
        }

        return 0;
    }
}

