package org.chromium.base.process_launcher;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import javax.annotation.concurrent.Immutable;
import org.chromium.base.annotations.MainDex;
import org.chromium.base.annotations.UsedByReflection;

@Immutable @MainDex @UsedByReflection(value="child_process_launcher_helper_android.cc") public final class FileDescriptorInfo implements Parcelable {
    final class org.chromium.base.process_launcher.FileDescriptorInfo$1 implements Parcelable$Creator {
        org.chromium.base.process_launcher.FileDescriptorInfo$1() {
            super();
        }

        public Object createFromParcel(Parcel arg1) {
            return this.createFromParcel(arg1);
        }

        public FileDescriptorInfo createFromParcel(Parcel arg2) {
            return new FileDescriptorInfo(arg2);
        }

        public Object[] newArray(int arg1) {
            return this.newArray(arg1);
        }

        public FileDescriptorInfo[] newArray(int arg1) {
            return new FileDescriptorInfo[arg1];
        }
    }

    public static final Parcelable$Creator CREATOR;
    public final ParcelFileDescriptor fd;
    public final int id;
    public final long offset;
    public final long size;

    static {
        FileDescriptorInfo.CREATOR = new org.chromium.base.process_launcher.FileDescriptorInfo$1();
    }

    public FileDescriptorInfo(int arg1, ParcelFileDescriptor arg2, long arg3, long arg5) {
        super();
        this.id = arg1;
        this.fd = arg2;
        this.offset = arg3;
        this.size = arg5;
    }

    FileDescriptorInfo(Parcel arg3) {
        super();
        this.id = arg3.readInt();
        this.fd = arg3.readParcelable(ParcelFileDescriptor.class.getClassLoader());
        this.offset = arg3.readLong();
        this.size = arg3.readLong();
    }

    public int describeContents() {
        return 1;
    }

    public void writeToParcel(Parcel arg3, int arg4) {
        arg3.writeInt(this.id);
        arg3.writeParcelable(this.fd, 1);
        arg3.writeLong(this.offset);
        arg3.writeLong(this.size);
    }
}

