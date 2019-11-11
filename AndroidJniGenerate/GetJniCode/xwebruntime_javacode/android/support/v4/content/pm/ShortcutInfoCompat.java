package android.support.v4.content.pm;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo$Builder;
import android.content.pm.ShortcutInfo;
import android.graphics.Bitmap;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.graphics.drawable.IconCompat;
import android.text.TextUtils;
import java.util.Arrays;

public class ShortcutInfoCompat {
    class android.support.v4.content.pm.ShortcutInfoCompat$1 {
    }

    public class Builder {
        private final ShortcutInfoCompat mInfo;

        public Builder(@NonNull Context arg3, @NonNull String arg4) {
            super();
            this.mInfo = new ShortcutInfoCompat(null);
            this.mInfo.mContext = arg3;
            this.mInfo.mId = arg4;
        }

        @NonNull public ShortcutInfoCompat build() {
            if(TextUtils.isEmpty(this.mInfo.mLabel)) {
                throw new IllegalArgumentException("Shortcut much have a non-empty label");
            }

            if(this.mInfo.mIntents != null) {
                if(this.mInfo.mIntents.length == 0) {
                }
                else {
                    return this.mInfo;
                }
            }

            throw new IllegalArgumentException("Shortcut much have an intent");
        }

        @NonNull public Builder setActivity(@NonNull ComponentName arg2) {
            this.mInfo.mActivity = arg2;
            return this;
        }

        @NonNull public Builder setDisabledMessage(@NonNull CharSequence arg2) {
            this.mInfo.mDisabledMessage = arg2;
            return this;
        }

        @NonNull public Builder setIcon(@DrawableRes int arg2) {
            return this.setIcon(IconCompat.createWithResource(this.mInfo.mContext, arg2));
        }

        @NonNull public Builder setIcon(IconCompat arg2) {
            this.mInfo.mIcon = arg2;
            return this;
        }

        @NonNull public Builder setIcon(@NonNull Bitmap arg1) {
            return this.setIcon(IconCompat.createWithBitmap(arg1));
        }

        @NonNull public Builder setIntent(@NonNull Intent arg3) {
            return this.setIntents(new Intent[]{arg3});
        }

        @NonNull public Builder setIntents(@NonNull Intent[] arg2) {
            this.mInfo.mIntents = arg2;
            return this;
        }

        @NonNull public Builder setLongLabel(@NonNull CharSequence arg2) {
            this.mInfo.mLongLabel = arg2;
            return this;
        }

        @NonNull public Builder setShortLabel(@NonNull CharSequence arg2) {
            this.mInfo.mLabel = arg2;
            return this;
        }
    }

    private ComponentName mActivity;
    private Context mContext;
    private CharSequence mDisabledMessage;
    private IconCompat mIcon;
    private String mId;
    private Intent[] mIntents;
    private CharSequence mLabel;
    private CharSequence mLongLabel;

    private ShortcutInfoCompat() {
        super();
    }

    ShortcutInfoCompat(android.support.v4.content.pm.ShortcutInfoCompat$1 arg1) {
        this();
    }

    static Context access$100(ShortcutInfoCompat arg0) {
        return arg0.mContext;
    }

    static Context access$102(ShortcutInfoCompat arg0, Context arg1) {
        arg0.mContext = arg1;
        return arg1;
    }

    static String access$202(ShortcutInfoCompat arg0, String arg1) {
        arg0.mId = arg1;
        return arg1;
    }

    static CharSequence access$300(ShortcutInfoCompat arg0) {
        return arg0.mLabel;
    }

    static CharSequence access$302(ShortcutInfoCompat arg0, CharSequence arg1) {
        arg0.mLabel = arg1;
        return arg1;
    }

    static CharSequence access$402(ShortcutInfoCompat arg0, CharSequence arg1) {
        arg0.mLongLabel = arg1;
        return arg1;
    }

    static CharSequence access$502(ShortcutInfoCompat arg0, CharSequence arg1) {
        arg0.mDisabledMessage = arg1;
        return arg1;
    }

    static Intent[] access$600(ShortcutInfoCompat arg0) {
        return arg0.mIntents;
    }

    static Intent[] access$602(ShortcutInfoCompat arg0, Intent[] arg1) {
        arg0.mIntents = arg1;
        return arg1;
    }

    static IconCompat access$702(ShortcutInfoCompat arg0, IconCompat arg1) {
        arg0.mIcon = arg1;
        return arg1;
    }

    static ComponentName access$802(ShortcutInfoCompat arg0, ComponentName arg1) {
        arg0.mActivity = arg1;
        return arg1;
    }

    Intent addToIntent(Intent arg4) {
        arg4.putExtra("android.intent.extra.shortcut.INTENT", this.mIntents[this.mIntents.length - 1]).putExtra("android.intent.extra.shortcut.NAME", this.mLabel.toString());
        if(this.mIcon != null) {
            this.mIcon.addToShortcutIntent(arg4);
        }

        return arg4;
    }

    @Nullable public ComponentName getActivity() {
        return this.mActivity;
    }

    @Nullable public CharSequence getDisabledMessage() {
        return this.mDisabledMessage;
    }

    @NonNull public String getId() {
        return this.mId;
    }

    @NonNull public Intent getIntent() {
        return this.mIntents[this.mIntents.length - 1];
    }

    @NonNull public Intent[] getIntents() {
        return Arrays.copyOf(this.mIntents, this.mIntents.length);
    }

    @Nullable public CharSequence getLongLabel() {
        return this.mLongLabel;
    }

    @NonNull public CharSequence getShortLabel() {
        return this.mLabel;
    }

    @RequiresApi(value=26) ShortcutInfo toShortcutInfo() {
        ShortcutInfo$Builder v0 = new ShortcutInfo$Builder(this.mContext, this.mId).setShortLabel(this.mLabel).setIntents(this.mIntents);
        if(this.mIcon != null) {
            v0.setIcon(this.mIcon.toIcon());
        }

        if(!TextUtils.isEmpty(this.mLongLabel)) {
            v0.setLongLabel(this.mLongLabel);
        }

        if(!TextUtils.isEmpty(this.mDisabledMessage)) {
            v0.setDisabledMessage(this.mDisabledMessage);
        }

        if(this.mActivity != null) {
            v0.setActivity(this.mActivity);
        }

        return v0.build();
    }
}

