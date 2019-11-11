package android.support.v4.media;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Log;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Iterator;
import java.util.Set;

public final class MediaMetadataCompat implements Parcelable {
    final class android.support.v4.media.MediaMetadataCompat$1 implements Parcelable$Creator {
        android.support.v4.media.MediaMetadataCompat$1() {
            super();
        }

        public MediaMetadataCompat createFromParcel(Parcel arg2) {
            return new MediaMetadataCompat(arg2);
        }

        public Object createFromParcel(Parcel arg1) {
            return this.createFromParcel(arg1);
        }

        public MediaMetadataCompat[] newArray(int arg1) {
            return new MediaMetadataCompat[arg1];
        }

        public Object[] newArray(int arg1) {
            return this.newArray(arg1);
        }
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) @Retention(value=RetentionPolicy.SOURCE) @public interface BitmapKey {
    }

    public final class Builder {
        private final Bundle mBundle;

        public Builder() {
            super();
            this.mBundle = new Bundle();
        }

        public Builder(MediaMetadataCompat arg2) {
            super();
            this.mBundle = new Bundle(arg2.mBundle);
        }

        @RestrictTo(value={Scope.LIBRARY_GROUP}) public Builder(MediaMetadataCompat arg4, int arg5) {
            this(arg4);
            Iterator v4 = this.mBundle.keySet().iterator();
            while(v4.hasNext()) {
                Object v0 = v4.next();
                Object v1 = this.mBundle.get(((String)v0));
                if(!(v1 instanceof Bitmap)) {
                    continue;
                }

                if(((Bitmap)v1).getHeight() <= arg5 && ((Bitmap)v1).getWidth() <= arg5) {
                    continue;
                }

                this.putBitmap(((String)v0), this.scaleBitmap(((Bitmap)v1), arg5));
            }
        }

        public MediaMetadataCompat build() {
            return new MediaMetadataCompat(this.mBundle);
        }

        public Builder putBitmap(String arg3, Bitmap arg4) {
            if((MediaMetadataCompat.METADATA_KEYS_TYPE.containsKey(arg3)) && MediaMetadataCompat.METADATA_KEYS_TYPE.get(arg3).intValue() != 2) {
                StringBuilder v0 = new StringBuilder();
                v0.append("The ");
                v0.append(arg3);
                v0.append(" key cannot be used to put a Bitmap");
                throw new IllegalArgumentException(v0.toString());
            }

            this.mBundle.putParcelable(arg3, ((Parcelable)arg4));
            return this;
        }

        public Builder putLong(String arg2, long arg3) {
            if((MediaMetadataCompat.METADATA_KEYS_TYPE.containsKey(arg2)) && MediaMetadataCompat.METADATA_KEYS_TYPE.get(arg2).intValue() != 0) {
                StringBuilder v4 = new StringBuilder();
                v4.append("The ");
                v4.append(arg2);
                v4.append(" key cannot be used to put a long");
                throw new IllegalArgumentException(v4.toString());
            }

            this.mBundle.putLong(arg2, arg3);
            return this;
        }

        public Builder putRating(String arg3, RatingCompat arg4) {
            if((MediaMetadataCompat.METADATA_KEYS_TYPE.containsKey(arg3)) && MediaMetadataCompat.METADATA_KEYS_TYPE.get(arg3).intValue() != 3) {
                StringBuilder v0 = new StringBuilder();
                v0.append("The ");
                v0.append(arg3);
                v0.append(" key cannot be used to put a Rating");
                throw new IllegalArgumentException(v0.toString());
            }

            if(Build$VERSION.SDK_INT >= 19) {
                this.mBundle.putParcelable(arg3, arg4.getRating());
            }
            else {
                this.mBundle.putParcelable(arg3, ((Parcelable)arg4));
            }

            return this;
        }

        public Builder putString(String arg3, String arg4) {
            if((MediaMetadataCompat.METADATA_KEYS_TYPE.containsKey(arg3)) && MediaMetadataCompat.METADATA_KEYS_TYPE.get(arg3).intValue() != 1) {
                StringBuilder v0 = new StringBuilder();
                v0.append("The ");
                v0.append(arg3);
                v0.append(" key cannot be used to put a String");
                throw new IllegalArgumentException(v0.toString());
            }

            this.mBundle.putCharSequence(arg3, ((CharSequence)arg4));
            return this;
        }

        public Builder putText(String arg3, CharSequence arg4) {
            if((MediaMetadataCompat.METADATA_KEYS_TYPE.containsKey(arg3)) && MediaMetadataCompat.METADATA_KEYS_TYPE.get(arg3).intValue() != 1) {
                StringBuilder v0 = new StringBuilder();
                v0.append("The ");
                v0.append(arg3);
                v0.append(" key cannot be used to put a CharSequence");
                throw new IllegalArgumentException(v0.toString());
            }

            this.mBundle.putCharSequence(arg3, arg4);
            return this;
        }

        private Bitmap scaleBitmap(Bitmap arg3, int arg4) {
            float v4 = ((float)arg4);
            v4 = Math.min(v4 / (((float)arg3.getWidth())), v4 / (((float)arg3.getHeight())));
            return Bitmap.createScaledBitmap(arg3, ((int)((((float)arg3.getWidth())) * v4)), ((int)((((float)arg3.getHeight())) * v4)), true);
        }
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) @Retention(value=RetentionPolicy.SOURCE) @public interface LongKey {
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) @Retention(value=RetentionPolicy.SOURCE) @public interface RatingKey {
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) @Retention(value=RetentionPolicy.SOURCE) @public interface TextKey {
    }

    public static final Parcelable$Creator CREATOR = null;
    static final ArrayMap METADATA_KEYS_TYPE = null;
    public static final String METADATA_KEY_ADVERTISEMENT = "android.media.metadata.ADVERTISEMENT";
    public static final String METADATA_KEY_ALBUM = "android.media.metadata.ALBUM";
    public static final String METADATA_KEY_ALBUM_ART = "android.media.metadata.ALBUM_ART";
    public static final String METADATA_KEY_ALBUM_ARTIST = "android.media.metadata.ALBUM_ARTIST";
    public static final String METADATA_KEY_ALBUM_ART_URI = "android.media.metadata.ALBUM_ART_URI";
    public static final String METADATA_KEY_ART = "android.media.metadata.ART";
    public static final String METADATA_KEY_ARTIST = "android.media.metadata.ARTIST";
    public static final String METADATA_KEY_ART_URI = "android.media.metadata.ART_URI";
    public static final String METADATA_KEY_AUTHOR = "android.media.metadata.AUTHOR";
    public static final String METADATA_KEY_BT_FOLDER_TYPE = "android.media.metadata.BT_FOLDER_TYPE";
    public static final String METADATA_KEY_COMPILATION = "android.media.metadata.COMPILATION";
    public static final String METADATA_KEY_COMPOSER = "android.media.metadata.COMPOSER";
    public static final String METADATA_KEY_DATE = "android.media.metadata.DATE";
    public static final String METADATA_KEY_DISC_NUMBER = "android.media.metadata.DISC_NUMBER";
    public static final String METADATA_KEY_DISPLAY_DESCRIPTION = "android.media.metadata.DISPLAY_DESCRIPTION";
    public static final String METADATA_KEY_DISPLAY_ICON = "android.media.metadata.DISPLAY_ICON";
    public static final String METADATA_KEY_DISPLAY_ICON_URI = "android.media.metadata.DISPLAY_ICON_URI";
    public static final String METADATA_KEY_DISPLAY_SUBTITLE = "android.media.metadata.DISPLAY_SUBTITLE";
    public static final String METADATA_KEY_DISPLAY_TITLE = "android.media.metadata.DISPLAY_TITLE";
    public static final String METADATA_KEY_DOWNLOAD_STATUS = "android.media.metadata.DOWNLOAD_STATUS";
    public static final String METADATA_KEY_DURATION = "android.media.metadata.DURATION";
    public static final String METADATA_KEY_GENRE = "android.media.metadata.GENRE";
    public static final String METADATA_KEY_MEDIA_ID = "android.media.metadata.MEDIA_ID";
    public static final String METADATA_KEY_MEDIA_URI = "android.media.metadata.MEDIA_URI";
    public static final String METADATA_KEY_NUM_TRACKS = "android.media.metadata.NUM_TRACKS";
    public static final String METADATA_KEY_RATING = "android.media.metadata.RATING";
    public static final String METADATA_KEY_TITLE = "android.media.metadata.TITLE";
    public static final String METADATA_KEY_TRACK_NUMBER = "android.media.metadata.TRACK_NUMBER";
    public static final String METADATA_KEY_USER_RATING = "android.media.metadata.USER_RATING";
    public static final String METADATA_KEY_WRITER = "android.media.metadata.WRITER";
    public static final String METADATA_KEY_YEAR = "android.media.metadata.YEAR";
    static final int METADATA_TYPE_BITMAP = 2;
    static final int METADATA_TYPE_LONG = 0;
    static final int METADATA_TYPE_RATING = 3;
    static final int METADATA_TYPE_TEXT = 1;
    private static final String[] PREFERRED_BITMAP_ORDER = null;
    private static final String[] PREFERRED_DESCRIPTION_ORDER = null;
    private static final String[] PREFERRED_URI_ORDER = null;
    private static final String TAG = "MediaMetadata";
    final Bundle mBundle;
    private MediaDescriptionCompat mDescription;
    private Object mMetadataObj;

    static {
        MediaMetadataCompat.METADATA_KEYS_TYPE = new ArrayMap();
        MediaMetadataCompat.METADATA_KEYS_TYPE.put("android.media.metadata.TITLE", Integer.valueOf(1));
        MediaMetadataCompat.METADATA_KEYS_TYPE.put("android.media.metadata.ARTIST", Integer.valueOf(1));
        MediaMetadataCompat.METADATA_KEYS_TYPE.put("android.media.metadata.DURATION", Integer.valueOf(0));
        MediaMetadataCompat.METADATA_KEYS_TYPE.put("android.media.metadata.ALBUM", Integer.valueOf(1));
        MediaMetadataCompat.METADATA_KEYS_TYPE.put("android.media.metadata.AUTHOR", Integer.valueOf(1));
        MediaMetadataCompat.METADATA_KEYS_TYPE.put("android.media.metadata.WRITER", Integer.valueOf(1));
        MediaMetadataCompat.METADATA_KEYS_TYPE.put("android.media.metadata.COMPOSER", Integer.valueOf(1));
        MediaMetadataCompat.METADATA_KEYS_TYPE.put("android.media.metadata.COMPILATION", Integer.valueOf(1));
        MediaMetadataCompat.METADATA_KEYS_TYPE.put("android.media.metadata.DATE", Integer.valueOf(1));
        MediaMetadataCompat.METADATA_KEYS_TYPE.put("android.media.metadata.YEAR", Integer.valueOf(0));
        MediaMetadataCompat.METADATA_KEYS_TYPE.put("android.media.metadata.GENRE", Integer.valueOf(1));
        MediaMetadataCompat.METADATA_KEYS_TYPE.put("android.media.metadata.TRACK_NUMBER", Integer.valueOf(0));
        MediaMetadataCompat.METADATA_KEYS_TYPE.put("android.media.metadata.NUM_TRACKS", Integer.valueOf(0));
        MediaMetadataCompat.METADATA_KEYS_TYPE.put("android.media.metadata.DISC_NUMBER", Integer.valueOf(0));
        MediaMetadataCompat.METADATA_KEYS_TYPE.put("android.media.metadata.ALBUM_ARTIST", Integer.valueOf(1));
        MediaMetadataCompat.METADATA_KEYS_TYPE.put("android.media.metadata.ART", Integer.valueOf(2));
        MediaMetadataCompat.METADATA_KEYS_TYPE.put("android.media.metadata.ART_URI", Integer.valueOf(1));
        MediaMetadataCompat.METADATA_KEYS_TYPE.put("android.media.metadata.ALBUM_ART", Integer.valueOf(2));
        MediaMetadataCompat.METADATA_KEYS_TYPE.put("android.media.metadata.ALBUM_ART_URI", Integer.valueOf(1));
        MediaMetadataCompat.METADATA_KEYS_TYPE.put("android.media.metadata.USER_RATING", Integer.valueOf(3));
        MediaMetadataCompat.METADATA_KEYS_TYPE.put("android.media.metadata.RATING", Integer.valueOf(3));
        MediaMetadataCompat.METADATA_KEYS_TYPE.put("android.media.metadata.DISPLAY_TITLE", Integer.valueOf(1));
        MediaMetadataCompat.METADATA_KEYS_TYPE.put("android.media.metadata.DISPLAY_SUBTITLE", Integer.valueOf(1));
        MediaMetadataCompat.METADATA_KEYS_TYPE.put("android.media.metadata.DISPLAY_DESCRIPTION", Integer.valueOf(1));
        MediaMetadataCompat.METADATA_KEYS_TYPE.put("android.media.metadata.DISPLAY_ICON", Integer.valueOf(2));
        MediaMetadataCompat.METADATA_KEYS_TYPE.put("android.media.metadata.DISPLAY_ICON_URI", Integer.valueOf(1));
        MediaMetadataCompat.METADATA_KEYS_TYPE.put("android.media.metadata.MEDIA_ID", Integer.valueOf(1));
        MediaMetadataCompat.METADATA_KEYS_TYPE.put("android.media.metadata.BT_FOLDER_TYPE", Integer.valueOf(0));
        MediaMetadataCompat.METADATA_KEYS_TYPE.put("android.media.metadata.MEDIA_URI", Integer.valueOf(1));
        MediaMetadataCompat.METADATA_KEYS_TYPE.put("android.media.metadata.ADVERTISEMENT", Integer.valueOf(0));
        MediaMetadataCompat.METADATA_KEYS_TYPE.put("android.media.metadata.DOWNLOAD_STATUS", Integer.valueOf(0));
        MediaMetadataCompat.PREFERRED_DESCRIPTION_ORDER = new String[]{"android.media.metadata.TITLE", "android.media.metadata.ARTIST", "android.media.metadata.ALBUM", "android.media.metadata.ALBUM_ARTIST", "android.media.metadata.WRITER", "android.media.metadata.AUTHOR", "android.media.metadata.COMPOSER"};
        MediaMetadataCompat.PREFERRED_BITMAP_ORDER = new String[]{"android.media.metadata.DISPLAY_ICON", "android.media.metadata.ART", "android.media.metadata.ALBUM_ART"};
        MediaMetadataCompat.PREFERRED_URI_ORDER = new String[]{"android.media.metadata.DISPLAY_ICON_URI", "android.media.metadata.ART_URI", "android.media.metadata.ALBUM_ART_URI"};
        MediaMetadataCompat.CREATOR = new android.support.v4.media.MediaMetadataCompat$1();
    }

    MediaMetadataCompat(Bundle arg2) {
        super();
        this.mBundle = new Bundle(arg2);
    }

    MediaMetadataCompat(Parcel arg1) {
        super();
        this.mBundle = arg1.readBundle();
    }

    public boolean containsKey(String arg2) {
        return this.mBundle.containsKey(arg2);
    }

    public int describeContents() {
        return 0;
    }

    public static MediaMetadataCompat fromMediaMetadata(Object arg2) {
        if(arg2 != null && Build$VERSION.SDK_INT >= 21) {
            Parcel v0 = Parcel.obtain();
            MediaMetadataCompatApi21.writeToParcel(arg2, v0, 0);
            v0.setDataPosition(0);
            Object v1 = MediaMetadataCompat.CREATOR.createFromParcel(v0);
            v0.recycle();
            ((MediaMetadataCompat)v1).mMetadataObj = arg2;
            return ((MediaMetadataCompat)v1);
        }

        return null;
    }

    public Bitmap getBitmap(String arg3) {
        Bitmap v3_2;
        try {
            Parcelable v3_1 = this.mBundle.getParcelable(arg3);
        }
        catch(Exception v3) {
            Log.w("MediaMetadata", "Failed to retrieve a key as Bitmap.", ((Throwable)v3));
            v3_2 = null;
        }

        return v3_2;
    }

    public Bundle getBundle() {
        return this.mBundle;
    }

    public MediaDescriptionCompat getDescription() {
        Uri v2_2;
        String v8_1;
        Bitmap v3_2;
        Uri v7;
        int v2_1;
        if(this.mDescription != null) {
            return this.mDescription;
        }

        String v0 = this.getString("android.media.metadata.MEDIA_ID");
        CharSequence[] v1 = new CharSequence[3];
        CharSequence v2 = this.getText("android.media.metadata.DISPLAY_TITLE");
        int v4 = 2;
        if(!TextUtils.isEmpty(v2)) {
            v1[0] = v2;
            v1[1] = this.getText("android.media.metadata.DISPLAY_SUBTITLE");
            v1[v4] = this.getText("android.media.metadata.DISPLAY_DESCRIPTION");
        }
        else {
            v2_1 = 0;
            int v3;
            for(v3 = 0; v2_1 < v1.length; v3 = v8) {
                if(v3 >= MediaMetadataCompat.PREFERRED_DESCRIPTION_ORDER.length) {
                    break;
                }

                int v8 = v3 + 1;
                CharSequence v3_1 = this.getText(MediaMetadataCompat.PREFERRED_DESCRIPTION_ORDER[v3]);
                if(!TextUtils.isEmpty(v3_1)) {
                    v1[v2_1] = v3_1;
                    ++v2_1;
                }
            }
        }

        v2_1 = 0;
        while(true) {
            v7 = null;
            if(v2_1 < MediaMetadataCompat.PREFERRED_BITMAP_ORDER.length) {
                v3_2 = this.getBitmap(MediaMetadataCompat.PREFERRED_BITMAP_ORDER[v2_1]);
                if(v3_2 != null) {
                }
                else {
                    ++v2_1;
                    continue;
                }
            }
            else {
                break;
            }

            goto label_54;
        }

        v3_2 = ((Bitmap)v7);
    label_54:
        v2_1 = 0;
        while(true) {
            if(v2_1 < MediaMetadataCompat.PREFERRED_URI_ORDER.length) {
                v8_1 = this.getString(MediaMetadataCompat.PREFERRED_URI_ORDER[v2_1]);
                if(!TextUtils.isEmpty(((CharSequence)v8_1))) {
                    v2_2 = Uri.parse(v8_1);
                }
                else {
                    ++v2_1;
                    continue;
                }
            }
            else {
                break;
            }

            goto label_68;
        }

        v2_2 = v7;
    label_68:
        v8_1 = this.getString("android.media.metadata.MEDIA_URI");
        if(!TextUtils.isEmpty(((CharSequence)v8_1))) {
            v7 = Uri.parse(v8_1);
        }

        android.support.v4.media.MediaDescriptionCompat$Builder v8_2 = new android.support.v4.media.MediaDescriptionCompat$Builder();
        v8_2.setMediaId(v0);
        v8_2.setTitle(v1[0]);
        v8_2.setSubtitle(v1[1]);
        v8_2.setDescription(v1[v4]);
        v8_2.setIconBitmap(v3_2);
        v8_2.setIconUri(v2_2);
        v8_2.setMediaUri(v7);
        Bundle v0_1 = new Bundle();
        if(this.mBundle.containsKey("android.media.metadata.BT_FOLDER_TYPE")) {
            v0_1.putLong("android.media.extra.BT_FOLDER_TYPE", this.getLong("android.media.metadata.BT_FOLDER_TYPE"));
        }

        if(this.mBundle.containsKey("android.media.metadata.DOWNLOAD_STATUS")) {
            v0_1.putLong("android.media.extra.DOWNLOAD_STATUS", this.getLong("android.media.metadata.DOWNLOAD_STATUS"));
        }

        if(!v0_1.isEmpty()) {
            v8_2.setExtras(v0_1);
        }

        this.mDescription = v8_2.build();
        return this.mDescription;
    }

    public long getLong(String arg4) {
        return this.mBundle.getLong(arg4, 0);
    }

    public Object getMediaMetadata() {
        if(this.mMetadataObj == null && Build$VERSION.SDK_INT >= 21) {
            Parcel v0 = Parcel.obtain();
            this.writeToParcel(v0, 0);
            v0.setDataPosition(0);
            this.mMetadataObj = MediaMetadataCompatApi21.createFromParcel(v0);
            v0.recycle();
        }

        return this.mMetadataObj;
    }

    public RatingCompat getRating(String arg3) {
        RatingCompat v3_1;
        try {
            if(Build$VERSION.SDK_INT >= 19) {
                v3_1 = RatingCompat.fromRating(this.mBundle.getParcelable(arg3));
                return v3_1;
            }

            Parcelable v3_2 = this.mBundle.getParcelable(arg3);
        }
        catch(Exception v3) {
            Log.w("MediaMetadata", "Failed to retrieve a key as Rating.", ((Throwable)v3));
            v3_1 = null;
        }

        return v3_1;
    }

    public String getString(String arg2) {
        CharSequence v2 = this.mBundle.getCharSequence(arg2);
        if(v2 != null) {
            return v2.toString();
        }

        return null;
    }

    public CharSequence getText(String arg2) {
        return this.mBundle.getCharSequence(arg2);
    }

    public Set keySet() {
        return this.mBundle.keySet();
    }

    public int size() {
        return this.mBundle.size();
    }

    public void writeToParcel(Parcel arg1, int arg2) {
        arg1.writeBundle(this.mBundle);
    }
}

