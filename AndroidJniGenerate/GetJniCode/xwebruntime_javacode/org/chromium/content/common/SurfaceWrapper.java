package org.chromium.content.common;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import android.view.Surface;
import org.chromium.base.annotations.MainDex;

@MainDex public class SurfaceWrapper implements Parcelable {
    final class org.chromium.content.common.SurfaceWrapper$1 implements Parcelable$Creator {
        org.chromium.content.common.SurfaceWrapper$1() {
            super();
        }

        public Object createFromParcel(Parcel arg1) {
            return this.createFromParcel(arg1);
        }

        public SurfaceWrapper createFromParcel(Parcel arg2) {
            return new SurfaceWrapper(Surface.CREATOR.createFromParcel(arg2));
        }

        public Object[] newArray(int arg1) {
            return this.newArray(arg1);
        }

        public SurfaceWrapper[] newArray(int arg1) {
            return new SurfaceWrapper[arg1];
        }
    }

    public static final Parcelable$Creator CREATOR;
    private final Surface mSurface;

    static {
        SurfaceWrapper.CREATOR = new org.chromium.content.common.SurfaceWrapper$1();
    }

    public SurfaceWrapper(Surface arg1) {
        super();
        this.mSurface = arg1;
    }

    public int describeContents() {
        return 0;
    }

    public Surface getSurface() {
        return this.mSurface;
    }

    public void writeToParcel(Parcel arg2, int arg3) {
        this.mSurface.writeToParcel(arg2, 0);
    }
}

