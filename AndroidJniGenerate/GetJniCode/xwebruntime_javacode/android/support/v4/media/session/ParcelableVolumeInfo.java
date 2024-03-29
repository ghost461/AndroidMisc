package android.support.v4.media.session;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;

public class ParcelableVolumeInfo implements Parcelable {
    final class android.support.v4.media.session.ParcelableVolumeInfo$1 implements Parcelable$Creator {
        android.support.v4.media.session.ParcelableVolumeInfo$1() {
            super();
        }

        public ParcelableVolumeInfo createFromParcel(Parcel arg2) {
            return new ParcelableVolumeInfo(arg2);
        }

        public Object createFromParcel(Parcel arg1) {
            return this.createFromParcel(arg1);
        }

        public ParcelableVolumeInfo[] newArray(int arg1) {
            return new ParcelableVolumeInfo[arg1];
        }

        public Object[] newArray(int arg1) {
            return this.newArray(arg1);
        }
    }

    public static final Parcelable$Creator CREATOR;
    public int audioStream;
    public int controlType;
    public int currentVolume;
    public int maxVolume;
    public int volumeType;

    static {
        ParcelableVolumeInfo.CREATOR = new android.support.v4.media.session.ParcelableVolumeInfo$1();
    }

    public ParcelableVolumeInfo(int arg1, int arg2, int arg3, int arg4, int arg5) {
        super();
        this.volumeType = arg1;
        this.audioStream = arg2;
        this.controlType = arg3;
        this.maxVolume = arg4;
        this.currentVolume = arg5;
    }

    public ParcelableVolumeInfo(Parcel arg2) {
        super();
        this.volumeType = arg2.readInt();
        this.controlType = arg2.readInt();
        this.maxVolume = arg2.readInt();
        this.currentVolume = arg2.readInt();
        this.audioStream = arg2.readInt();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel arg1, int arg2) {
        arg1.writeInt(this.volumeType);
        arg1.writeInt(this.controlType);
        arg1.writeInt(this.maxVolume);
        arg1.writeInt(this.currentVolume);
        arg1.writeInt(this.audioStream);
    }
}

