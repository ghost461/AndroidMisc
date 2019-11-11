package android.support.v4.app;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;

final class FragmentManagerState implements Parcelable {
    final class android.support.v4.app.FragmentManagerState$1 implements Parcelable$Creator {
        android.support.v4.app.FragmentManagerState$1() {
            super();
        }

        public FragmentManagerState createFromParcel(Parcel arg2) {
            return new FragmentManagerState(arg2);
        }

        public Object createFromParcel(Parcel arg1) {
            return this.createFromParcel(arg1);
        }

        public FragmentManagerState[] newArray(int arg1) {
            return new FragmentManagerState[arg1];
        }

        public Object[] newArray(int arg1) {
            return this.newArray(arg1);
        }
    }

    public static final Parcelable$Creator CREATOR;
    FragmentState[] mActive;
    int[] mAdded;
    BackStackState[] mBackStack;
    int mNextFragmentIndex;
    int mPrimaryNavActiveIndex;

    static {
        FragmentManagerState.CREATOR = new android.support.v4.app.FragmentManagerState$1();
    }

    public FragmentManagerState() {
        super();
        this.mPrimaryNavActiveIndex = -1;
    }

    public FragmentManagerState(Parcel arg2) {
        super();
        this.mPrimaryNavActiveIndex = -1;
        this.mActive = arg2.createTypedArray(FragmentState.CREATOR);
        this.mAdded = arg2.createIntArray();
        this.mBackStack = arg2.createTypedArray(BackStackState.CREATOR);
        this.mPrimaryNavActiveIndex = arg2.readInt();
        this.mNextFragmentIndex = arg2.readInt();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel arg2, int arg3) {
        arg2.writeTypedArray(this.mActive, arg3);
        arg2.writeIntArray(this.mAdded);
        arg2.writeTypedArray(this.mBackStack, arg3);
        arg2.writeInt(this.mPrimaryNavActiveIndex);
        arg2.writeInt(this.mNextFragmentIndex);
    }
}

