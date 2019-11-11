package android.support.v4.media;

import android.os.Build$VERSION;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.util.Log;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class RatingCompat implements Parcelable {
    final class android.support.v4.media.RatingCompat$1 implements Parcelable$Creator {
        android.support.v4.media.RatingCompat$1() {
            super();
        }

        public RatingCompat createFromParcel(Parcel arg3) {
            return new RatingCompat(arg3.readInt(), arg3.readFloat());
        }

        public Object createFromParcel(Parcel arg1) {
            return this.createFromParcel(arg1);
        }

        public RatingCompat[] newArray(int arg1) {
            return new RatingCompat[arg1];
        }

        public Object[] newArray(int arg1) {
            return this.newArray(arg1);
        }
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) @Retention(value=RetentionPolicy.SOURCE) @public interface StarStyle {
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) @Retention(value=RetentionPolicy.SOURCE) @public interface Style {
    }

    public static final Parcelable$Creator CREATOR = null;
    public static final int RATING_3_STARS = 3;
    public static final int RATING_4_STARS = 4;
    public static final int RATING_5_STARS = 5;
    public static final int RATING_HEART = 1;
    public static final int RATING_NONE = 0;
    private static final float RATING_NOT_RATED = -1f;
    public static final int RATING_PERCENTAGE = 6;
    public static final int RATING_THUMB_UP_DOWN = 2;
    private static final String TAG = "Rating";
    private Object mRatingObj;
    private final int mRatingStyle;
    private final float mRatingValue;

    static {
        RatingCompat.CREATOR = new android.support.v4.media.RatingCompat$1();
    }

    RatingCompat(int arg1, float arg2) {
        super();
        this.mRatingStyle = arg1;
        this.mRatingValue = arg2;
    }

    public int describeContents() {
        return this.mRatingStyle;
    }

    public static RatingCompat fromRating(Object arg3) {
        RatingCompat v0 = null;
        if(arg3 != null && Build$VERSION.SDK_INT >= 19) {
            int v1 = RatingCompatKitkat.getRatingStyle(arg3);
            if(RatingCompatKitkat.isRated(arg3)) {
                switch(v1) {
                    case 1: {
                        goto label_19;
                    }
                    case 2: {
                        goto label_16;
                    }
                    case 3: 
                    case 4: 
                    case 5: {
                        goto label_13;
                    }
                    case 6: {
                        goto label_10;
                    }
                }

                return v0;
            label_19:
                v0 = RatingCompat.newHeartRating(RatingCompatKitkat.hasHeart(arg3));
                goto label_23;
            label_10:
                v0 = RatingCompat.newPercentageRating(RatingCompatKitkat.getPercentRating(arg3));
                goto label_23;
            label_13:
                v0 = RatingCompat.newStarRating(v1, RatingCompatKitkat.getStarRating(arg3));
                goto label_23;
            label_16:
                v0 = RatingCompat.newThumbRating(RatingCompatKitkat.isThumbUp(arg3));
            }
            else {
                v0 = RatingCompat.newUnratedRating(v1);
            }

        label_23:
            v0.mRatingObj = arg3;
            return v0;
        }

        return v0;
    }

    public float getPercentRating() {
        if(this.mRatingStyle == 6) {
            if(!this.isRated()) {
            }
            else {
                return this.mRatingValue;
            }
        }

        return -1f;
    }

    public Object getRating() {
        if(this.mRatingObj == null && Build$VERSION.SDK_INT >= 19) {
            if(this.isRated()) {
                switch(this.mRatingStyle) {
                    case 1: {
                        goto label_24;
                    }
                    case 2: {
                        goto label_20;
                    }
                    case 3: 
                    case 4: 
                    case 5: {
                        goto label_15;
                    }
                    case 6: {
                        goto label_11;
                    }
                }

                return null;
            label_20:
                this.mRatingObj = RatingCompatKitkat.newThumbRating(this.isThumbUp());
                goto label_31;
            label_24:
                this.mRatingObj = RatingCompatKitkat.newHeartRating(this.hasHeart());
                goto label_31;
            label_11:
                this.mRatingObj = RatingCompatKitkat.newPercentageRating(this.getPercentRating());
                goto label_31;
            label_15:
                this.mRatingObj = RatingCompatKitkat.newStarRating(this.mRatingStyle, this.getStarRating());
            }
            else {
                this.mRatingObj = RatingCompatKitkat.newUnratedRating(this.mRatingStyle);
            }
        }

    label_31:
        return this.mRatingObj;
    }

    public int getRatingStyle() {
        return this.mRatingStyle;
    }

    public float getStarRating() {
        switch(this.mRatingStyle) {
            case 3: 
            case 4: 
            case 5: {
                if(this.isRated()) {
                    return this.mRatingValue;
                }

                break;
            }
            default: {
                break;
            }
        }

        return -1f;
    }

    public boolean hasHeart() {
        boolean v1 = false;
        if(this.mRatingStyle != 1) {
            return 0;
        }

        if(this.mRatingValue == 1f) {
            v1 = true;
        }

        return v1;
    }

    public boolean isRated() {
        boolean v0 = this.mRatingValue >= 0f ? true : false;
        return v0;
    }

    public boolean isThumbUp() {
        boolean v1 = false;
        if(this.mRatingStyle != 2) {
            return 0;
        }

        if(this.mRatingValue == 1f) {
            v1 = true;
        }

        return v1;
    }

    public static RatingCompat newHeartRating(boolean arg2) {
        float v2 = arg2 ? 1f : 0f;
        return new RatingCompat(1, v2);
    }

    public static RatingCompat newPercentageRating(float arg2) {
        if(arg2 >= 0f) {
            if(arg2 > 100f) {
            }
            else {
                return new RatingCompat(6, arg2);
            }
        }

        Log.e("Rating", "Invalid percentage-based rating value");
        return null;
    }

    public static RatingCompat newStarRating(int arg3, float arg4) {
        RatingCompat v0 = null;
        switch(arg3) {
            case 3: {
                goto label_17;
            }
            case 4: {
                goto label_15;
            }
            case 5: {
                goto label_13;
            }
        }

        Log.e("Rating", "Invalid rating style (" + arg3 + ") for a star rating");
        return v0;
    label_17:
        float v1_1 = 3f;
        goto label_18;
    label_13:
        v1_1 = 5f;
        goto label_18;
    label_15:
        v1_1 = 4f;
    label_18:
        if(arg4 >= 0f) {
            if(arg4 > v1_1) {
            }
            else {
                return new RatingCompat(arg3, arg4);
            }
        }

        Log.e("Rating", "Trying to set out of range star-based rating");
        return v0;
    }

    public static RatingCompat newThumbRating(boolean arg2) {
        float v2 = arg2 ? 1f : 0f;
        return new RatingCompat(2, v2);
    }

    public static RatingCompat newUnratedRating(int arg2) {
        switch(arg2) {
            case 1: 
            case 2: 
            case 3: 
            case 4: 
            case 5: 
            case 6: {
                goto label_3;
            }
        }

        return null;
    label_3:
        return new RatingCompat(arg2, -1f);
    }

    public String toString() {
        StringBuilder v0 = new StringBuilder();
        v0.append("Rating:style=");
        v0.append(this.mRatingStyle);
        v0.append(" rating=");
        String v1 = this.mRatingValue < 0f ? "unrated" : String.valueOf(this.mRatingValue);
        v0.append(v1);
        return v0.toString();
    }

    public void writeToParcel(Parcel arg1, int arg2) {
        arg1.writeInt(this.mRatingStyle);
        arg1.writeFloat(this.mRatingValue);
    }
}

