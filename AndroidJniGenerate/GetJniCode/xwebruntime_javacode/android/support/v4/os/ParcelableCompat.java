package android.support.v4.os;

import android.os.Parcel;
import android.os.Parcelable$ClassLoaderCreator;
import android.os.Parcelable$Creator;

@Deprecated public final class ParcelableCompat {
    class ParcelableCompatCreatorHoneycombMR2 implements Parcelable$ClassLoaderCreator {
        private final ParcelableCompatCreatorCallbacks mCallbacks;

        ParcelableCompatCreatorHoneycombMR2(ParcelableCompatCreatorCallbacks arg1) {
            super();
            this.mCallbacks = arg1;
        }

        public Object createFromParcel(Parcel arg3) {
            return this.mCallbacks.createFromParcel(arg3, null);
        }

        public Object createFromParcel(Parcel arg2, ClassLoader arg3) {
            return this.mCallbacks.createFromParcel(arg2, arg3);
        }

        public Object[] newArray(int arg2) {
            return this.mCallbacks.newArray(arg2);
        }
    }

    private ParcelableCompat() {
        super();
    }

    @Deprecated public static Parcelable$Creator newCreator(ParcelableCompatCreatorCallbacks arg1) {
        return new ParcelableCompatCreatorHoneycombMR2(arg1);
    }
}

