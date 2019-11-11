package android.support.v4.media;

import android.graphics.Bitmap;
import android.media.MediaDescription$Builder;
import android.media.MediaDescription;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.support.annotation.RequiresApi;

@RequiresApi(value=21) class MediaDescriptionCompatApi21 {
    class Builder {
        Builder() {
            super();
        }

        public static Object build(Object arg0) {
            return ((MediaDescription$Builder)arg0).build();
        }

        public static Object newInstance() {
            return new MediaDescription$Builder();
        }

        public static void setDescription(Object arg0, CharSequence arg1) {
            ((MediaDescription$Builder)arg0).setDescription(arg1);
        }

        public static void setExtras(Object arg0, Bundle arg1) {
            ((MediaDescription$Builder)arg0).setExtras(arg1);
        }

        public static void setIconBitmap(Object arg0, Bitmap arg1) {
            ((MediaDescription$Builder)arg0).setIconBitmap(arg1);
        }

        public static void setIconUri(Object arg0, Uri arg1) {
            ((MediaDescription$Builder)arg0).setIconUri(arg1);
        }

        public static void setMediaId(Object arg0, String arg1) {
            ((MediaDescription$Builder)arg0).setMediaId(arg1);
        }

        public static void setSubtitle(Object arg0, CharSequence arg1) {
            ((MediaDescription$Builder)arg0).setSubtitle(arg1);
        }

        public static void setTitle(Object arg0, CharSequence arg1) {
            ((MediaDescription$Builder)arg0).setTitle(arg1);
        }
    }

    MediaDescriptionCompatApi21() {
        super();
    }

    public static Object fromParcel(Parcel arg1) {
        return MediaDescription.CREATOR.createFromParcel(arg1);
    }

    public static CharSequence getDescription(Object arg0) {
        return ((MediaDescription)arg0).getDescription();
    }

    public static Bundle getExtras(Object arg0) {
        return ((MediaDescription)arg0).getExtras();
    }

    public static Bitmap getIconBitmap(Object arg0) {
        return ((MediaDescription)arg0).getIconBitmap();
    }

    public static Uri getIconUri(Object arg0) {
        return ((MediaDescription)arg0).getIconUri();
    }

    public static String getMediaId(Object arg0) {
        return ((MediaDescription)arg0).getMediaId();
    }

    public static CharSequence getSubtitle(Object arg0) {
        return ((MediaDescription)arg0).getSubtitle();
    }

    public static CharSequence getTitle(Object arg0) {
        return ((MediaDescription)arg0).getTitle();
    }

    public static void writeToParcel(Object arg0, Parcel arg1, int arg2) {
        ((MediaDescription)arg0).writeToParcel(arg1, arg2);
    }
}

