package android.support.v4.media;

import android.media.Rating;
import android.support.annotation.RequiresApi;

@RequiresApi(value=19) class RatingCompatKitkat {
    RatingCompatKitkat() {
        super();
    }

    public static float getPercentRating(Object arg0) {
        return ((Rating)arg0).getPercentRating();
    }

    public static int getRatingStyle(Object arg0) {
        return ((Rating)arg0).getRatingStyle();
    }

    public static float getStarRating(Object arg0) {
        return ((Rating)arg0).getStarRating();
    }

    public static boolean hasHeart(Object arg0) {
        return ((Rating)arg0).hasHeart();
    }

    public static boolean isRated(Object arg0) {
        return ((Rating)arg0).isRated();
    }

    public static boolean isThumbUp(Object arg0) {
        return ((Rating)arg0).isThumbUp();
    }

    public static Object newHeartRating(boolean arg0) {
        return Rating.newHeartRating(arg0);
    }

    public static Object newPercentageRating(float arg0) {
        return Rating.newPercentageRating(arg0);
    }

    public static Object newStarRating(int arg0, float arg1) {
        return Rating.newStarRating(arg0, arg1);
    }

    public static Object newThumbRating(boolean arg0) {
        return Rating.newThumbRating(arg0);
    }

    public static Object newUnratedRating(int arg0) {
        return Rating.newUnratedRating(arg0);
    }
}

