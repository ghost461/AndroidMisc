package org.chromium.content_public.common;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="content") public final class MediaMetadata {
    public final class MediaImage {
        @NonNull private List mSizes;
        @NonNull private String mSrc;
        private String mType;

        public MediaImage(@NonNull String arg2, @NonNull String arg3, @NonNull List arg4) {
            super();
            this.mSizes = new ArrayList();
            this.mSrc = arg2;
            this.mType = arg3;
            this.mSizes = arg4;
        }

        public boolean equals(Object arg5) {
            boolean v0 = true;
            if((((MediaImage)arg5)) == this) {
                return 1;
            }

            if(!(arg5 instanceof MediaImage)) {
                return 0;
            }

            if(!TextUtils.equals(this.mSrc, ((MediaImage)arg5).mSrc) || !TextUtils.equals(this.mType, ((MediaImage)arg5).mType) || !this.mSizes.equals(((MediaImage)arg5).mSizes)) {
                v0 = false;
            }
            else {
            }

            return v0;
        }

        public List getSizes() {
            return this.mSizes;
        }

        @NonNull public String getSrc() {
            return this.mSrc;
        }

        public String getType() {
            return this.mType;
        }

        public int hashCode() {
            return (this.mSrc.hashCode() * 0x1F + this.mType.hashCode()) * 0x1F + this.mSizes.hashCode();
        }

        public void setSizes(@NonNull List arg1) {
            this.mSizes = arg1;
        }

        public void setSrc(@NonNull String arg1) {
            this.mSrc = arg1;
        }

        public void setType(@NonNull String arg1) {
            this.mType = arg1;
        }
    }

    @NonNull private String mAlbum;
    @NonNull private String mArtist;
    @NonNull private List mArtwork;
    @NonNull private String mTitle;

    static {
    }

    public MediaMetadata(@NonNull String arg2, @NonNull String arg3, @NonNull String arg4) {
        super();
        this.mArtwork = new ArrayList();
        this.mTitle = arg2;
        this.mArtist = arg3;
        this.mAlbum = arg4;
    }

    @CalledByNative private static MediaMetadata create(String arg1, String arg2, String arg3) {
        if(arg1 == null) {
            arg1 = "";
        }

        if(arg2 == null) {
            arg2 = "";
        }

        if(arg3 == null) {
            arg3 = "";
        }

        return new MediaMetadata(arg1, arg2, arg3);
    }

    @CalledByNative private void createAndAddMediaImage(String arg7, String arg8, int[] arg9) {
        ArrayList v0 = new ArrayList();
        int v2;
        for(v2 = 0; true; v2 += 2) {
            int v3 = v2 + 1;
            if(v3 >= arg9.length) {
                break;
            }

            ((List)v0).add(new Rect(0, 0, arg9[v2], arg9[v3]));
        }

        this.mArtwork.add(new MediaImage(arg7, arg8, ((List)v0)));
    }

    public boolean equals(Object arg5) {
        boolean v0 = true;
        if((((MediaMetadata)arg5)) == this) {
            return 1;
        }

        if(!(arg5 instanceof MediaMetadata)) {
            return 0;
        }

        if(!TextUtils.equals(this.mTitle, ((MediaMetadata)arg5).mTitle) || !TextUtils.equals(this.mArtist, ((MediaMetadata)arg5).mArtist) || !TextUtils.equals(this.mAlbum, ((MediaMetadata)arg5).mAlbum) || !this.mArtwork.equals(((MediaMetadata)arg5).mArtwork)) {
            v0 = false;
        }
        else {
        }

        return v0;
    }

    public String getAlbum() {
        return this.mAlbum;
    }

    public String getArtist() {
        return this.mArtist;
    }

    public List getArtwork() {
        return this.mArtwork;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public int hashCode() {
        return ((this.mTitle.hashCode() * 0x1F + this.mArtist.hashCode()) * 0x1F + this.mAlbum.hashCode()) * 0x1F + this.mArtwork.hashCode();
    }

    public void setAlbum(@NonNull String arg1) {
        this.mAlbum = arg1;
    }

    public void setArtist(@NonNull String arg1) {
        this.mArtist = arg1;
    }

    public void setTitle(@NonNull String arg1) {
        this.mTitle = arg1;
    }
}

