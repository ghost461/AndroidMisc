package org.chromium.base;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import org.chromium.base.annotations.CalledByNative;

public class UnguessableToken implements Parcelable {
    final class org.chromium.base.UnguessableToken$1 implements Parcelable$Creator {
        org.chromium.base.UnguessableToken$1() {
            super();
        }

        public Object createFromParcel(Parcel arg1) {
            return this.createFromParcel(arg1);
        }

        public UnguessableToken createFromParcel(Parcel arg8) {
            long v1 = arg8.readLong();
            long v3 = arg8.readLong();
            long v5 = 0;
            if(v1 != v5) {
                if(v3 == v5) {
                }
                else {
                    return new UnguessableToken(v1, v3, null);
                }
            }

            return null;
        }

        public Object[] newArray(int arg1) {
            return this.newArray(arg1);
        }

        public UnguessableToken[] newArray(int arg1) {
            return new UnguessableToken[arg1];
        }
    }

    public static final Parcelable$Creator CREATOR;
    private final long mHigh;
    private final long mLow;

    static {
        UnguessableToken.CREATOR = new org.chromium.base.UnguessableToken$1();
    }

    private UnguessableToken(long arg1, long arg3) {
        super();
        this.mHigh = arg1;
        this.mLow = arg3;
    }

    UnguessableToken(long arg1, long arg3, org.chromium.base.UnguessableToken$1 arg5) {
        this(arg1, arg3);
    }

    @CalledByNative private static UnguessableToken create(long arg1, long arg3) {
        return new UnguessableToken(arg1, arg3);
    }

    public int describeContents() {
        return 0;
    }

    @CalledByNative public long getHighForSerialization() {
        return this.mHigh;
    }

    @CalledByNative public long getLowForSerialization() {
        return this.mLow;
    }

    @CalledByNative private UnguessableToken parcelAndUnparcelForTesting() {
        Parcel v0 = Parcel.obtain();
        this.writeToParcel(v0, 0);
        v0.setDataPosition(0);
        Object v1 = UnguessableToken.CREATOR.createFromParcel(v0);
        v0.recycle();
        return ((UnguessableToken)v1);
    }

    public void writeToParcel(Parcel arg3, int arg4) {
        arg3.writeLong(this.mHigh);
        arg3.writeLong(this.mLow);
    }
}

