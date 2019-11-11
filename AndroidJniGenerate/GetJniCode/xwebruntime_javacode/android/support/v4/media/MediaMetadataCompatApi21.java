package android.support.v4.media;

import android.graphics.Bitmap;
import android.media.MediaMetadata$Builder;
import android.media.MediaMetadata;
import android.media.Rating;
import android.os.Parcel;
import android.support.annotation.RequiresApi;
import java.util.Set;

@RequiresApi(value=21) class MediaMetadataCompatApi21 {
    public class Builder {
        public Builder() {
            super();
        }

        public static Object build(Object arg0) {
            return ((MediaMetadata$Builder)arg0).build();
        }

        public static Object newInstance() {
            return new MediaMetadata$Builder();
        }

        public static void putBitmap(Object arg0, String arg1, Bitmap arg2) {
            ((MediaMetadata$Builder)arg0).putBitmap(arg1, arg2);
        }

        public static void putLong(Object arg0, String arg1, long arg2) {
            ((MediaMetadata$Builder)arg0).putLong(arg1, arg2);
        }

        public static void putRating(Object arg0, String arg1, Object arg2) {
            ((MediaMetadata$Builder)arg0).putRating(arg1, ((Rating)arg2));
        }

        public static void putString(Object arg0, String arg1, String arg2) {
            ((MediaMetadata$Builder)arg0).putString(arg1, arg2);
        }

        public static void putText(Object arg0, String arg1, CharSequence arg2) {
            ((MediaMetadata$Builder)arg0).putText(arg1, arg2);
        }
    }

    MediaMetadataCompatApi21() {
        super();
    }

    public static Object createFromParcel(Parcel arg1) {
        return MediaMetadata.CREATOR.createFromParcel(arg1);
    }

    public static Bitmap getBitmap(Object arg0, String arg1) {
        return ((MediaMetadata)arg0).getBitmap(arg1);
    }

    public static long getLong(Object arg0, String arg1) {
        return ((MediaMetadata)arg0).getLong(arg1);
    }

    public static Object getRating(Object arg0, String arg1) {
        return ((MediaMetadata)arg0).getRating(arg1);
    }

    public static CharSequence getText(Object arg0, String arg1) {
        return ((MediaMetadata)arg0).getText(arg1);
    }

    public static Set keySet(Object arg0) {
        return ((MediaMetadata)arg0).keySet();
    }

    public static void writeToParcel(Object arg0, Parcel arg1, int arg2) {
        ((MediaMetadata)arg0).writeToParcel(arg1, arg2);
    }
}

