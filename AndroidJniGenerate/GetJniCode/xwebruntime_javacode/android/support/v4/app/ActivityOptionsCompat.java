package android.support.v4.app;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.util.Pair;
import android.view.View;

public class ActivityOptionsCompat {
    @RequiresApi(value=16) class ActivityOptionsCompatApi16Impl extends ActivityOptionsCompat {
        protected final ActivityOptions mActivityOptions;

        ActivityOptionsCompatApi16Impl(ActivityOptions arg1) {
            super();
            this.mActivityOptions = arg1;
        }

        public Bundle toBundle() {
            return this.mActivityOptions.toBundle();
        }

        public void update(ActivityOptionsCompat arg2) {
            if((arg2 instanceof ActivityOptionsCompatApi16Impl)) {
                this.mActivityOptions.update(((ActivityOptionsCompatApi16Impl)arg2).mActivityOptions);
            }
        }
    }

    @RequiresApi(value=23) class ActivityOptionsCompatApi23Impl extends ActivityOptionsCompatApi16Impl {
        ActivityOptionsCompatApi23Impl(ActivityOptions arg1) {
            super(arg1);
        }

        public void requestUsageTimeReport(PendingIntent arg2) {
            this.mActivityOptions.requestUsageTimeReport(arg2);
        }
    }

    @RequiresApi(value=24) class ActivityOptionsCompatApi24Impl extends ActivityOptionsCompatApi23Impl {
        ActivityOptionsCompatApi24Impl(ActivityOptions arg1) {
            super(arg1);
        }

        public Rect getLaunchBounds() {
            return this.mActivityOptions.getLaunchBounds();
        }

        public ActivityOptionsCompat setLaunchBounds(@Nullable Rect arg3) {
            return new ActivityOptionsCompatApi24Impl(this.mActivityOptions.setLaunchBounds(arg3));
        }
    }

    public static final String EXTRA_USAGE_TIME_REPORT = "android.activity.usage_time";
    public static final String EXTRA_USAGE_TIME_REPORT_PACKAGES = "android.usage_time_packages";

    protected ActivityOptionsCompat() {
        super();
    }

    @RequiresApi(value=16) private static ActivityOptionsCompat createImpl(ActivityOptions arg2) {
        if(Build$VERSION.SDK_INT >= 24) {
            return new ActivityOptionsCompatApi24Impl(arg2);
        }

        if(Build$VERSION.SDK_INT >= 23) {
            return new ActivityOptionsCompatApi23Impl(arg2);
        }

        return new ActivityOptionsCompatApi16Impl(arg2);
    }

    @Nullable public Rect getLaunchBounds() {
        return null;
    }

    public static ActivityOptionsCompat makeBasic() {
        if(Build$VERSION.SDK_INT >= 23) {
            return ActivityOptionsCompat.createImpl(ActivityOptions.makeBasic());
        }

        return new ActivityOptionsCompat();
    }

    public static ActivityOptionsCompat makeClipRevealAnimation(View arg2, int arg3, int arg4, int arg5, int arg6) {
        if(Build$VERSION.SDK_INT >= 23) {
            return ActivityOptionsCompat.createImpl(ActivityOptions.makeClipRevealAnimation(arg2, arg3, arg4, arg5, arg6));
        }

        return new ActivityOptionsCompat();
    }

    public static ActivityOptionsCompat makeCustomAnimation(Context arg2, int arg3, int arg4) {
        if(Build$VERSION.SDK_INT >= 16) {
            return ActivityOptionsCompat.createImpl(ActivityOptions.makeCustomAnimation(arg2, arg3, arg4));
        }

        return new ActivityOptionsCompat();
    }

    public static ActivityOptionsCompat makeScaleUpAnimation(View arg2, int arg3, int arg4, int arg5, int arg6) {
        if(Build$VERSION.SDK_INT >= 16) {
            return ActivityOptionsCompat.createImpl(ActivityOptions.makeScaleUpAnimation(arg2, arg3, arg4, arg5, arg6));
        }

        return new ActivityOptionsCompat();
    }

    public static ActivityOptionsCompat makeSceneTransitionAnimation(Activity arg2, View arg3, String arg4) {
        if(Build$VERSION.SDK_INT >= 21) {
            return ActivityOptionsCompat.createImpl(ActivityOptions.makeSceneTransitionAnimation(arg2, arg3, arg4));
        }

        return new ActivityOptionsCompat();
    }

    public static ActivityOptionsCompat makeSceneTransitionAnimation(Activity arg4, Pair[] arg5) {
        if(Build$VERSION.SDK_INT >= 21) {
            android.util.Pair[] v0 = null;
            if(arg5 != null) {
                v0 = new android.util.Pair[arg5.length];
                int v1;
                for(v1 = 0; v1 < arg5.length; ++v1) {
                    v0[v1] = android.util.Pair.create(arg5[v1].first, arg5[v1].second);
                }
            }

            return ActivityOptionsCompat.createImpl(ActivityOptions.makeSceneTransitionAnimation(arg4, v0));
        }

        return new ActivityOptionsCompat();
    }

    public static ActivityOptionsCompat makeTaskLaunchBehind() {
        if(Build$VERSION.SDK_INT >= 21) {
            return ActivityOptionsCompat.createImpl(ActivityOptions.makeTaskLaunchBehind());
        }

        return new ActivityOptionsCompat();
    }

    public static ActivityOptionsCompat makeThumbnailScaleUpAnimation(View arg2, Bitmap arg3, int arg4, int arg5) {
        if(Build$VERSION.SDK_INT >= 16) {
            return ActivityOptionsCompat.createImpl(ActivityOptions.makeThumbnailScaleUpAnimation(arg2, arg3, arg4, arg5));
        }

        return new ActivityOptionsCompat();
    }

    public void requestUsageTimeReport(PendingIntent arg1) {
    }

    public ActivityOptionsCompat setLaunchBounds(@Nullable Rect arg1) {
        return null;
    }

    public Bundle toBundle() {
        return null;
    }

    public void update(ActivityOptionsCompat arg1) {
    }
}

