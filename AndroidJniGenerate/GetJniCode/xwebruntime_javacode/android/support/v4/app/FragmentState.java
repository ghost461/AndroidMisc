package android.support.v4.app;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import android.util.Log;

final class FragmentState implements Parcelable {
    final class android.support.v4.app.FragmentState$1 implements Parcelable$Creator {
        android.support.v4.app.FragmentState$1() {
            super();
        }

        public FragmentState createFromParcel(Parcel arg2) {
            return new FragmentState(arg2);
        }

        public Object createFromParcel(Parcel arg1) {
            return this.createFromParcel(arg1);
        }

        public FragmentState[] newArray(int arg1) {
            return new FragmentState[arg1];
        }

        public Object[] newArray(int arg1) {
            return this.newArray(arg1);
        }
    }

    public static final Parcelable$Creator CREATOR;
    final Bundle mArguments;
    final String mClassName;
    final int mContainerId;
    final boolean mDetached;
    final int mFragmentId;
    final boolean mFromLayout;
    final boolean mHidden;
    final int mIndex;
    Fragment mInstance;
    final boolean mRetainInstance;
    Bundle mSavedFragmentState;
    final String mTag;

    static {
        FragmentState.CREATOR = new android.support.v4.app.FragmentState$1();
    }

    public FragmentState(Parcel arg4) {
        super();
        this.mClassName = arg4.readString();
        this.mIndex = arg4.readInt();
        boolean v1 = false;
        boolean v0 = arg4.readInt() != 0 ? true : false;
        this.mFromLayout = v0;
        this.mFragmentId = arg4.readInt();
        this.mContainerId = arg4.readInt();
        this.mTag = arg4.readString();
        v0 = arg4.readInt() != 0 ? true : false;
        this.mRetainInstance = v0;
        v0 = arg4.readInt() != 0 ? true : false;
        this.mDetached = v0;
        this.mArguments = arg4.readBundle();
        if(arg4.readInt() != 0) {
            v1 = true;
        }

        this.mHidden = v1;
        this.mSavedFragmentState = arg4.readBundle();
    }

    public FragmentState(Fragment arg2) {
        super();
        this.mClassName = arg2.getClass().getName();
        this.mIndex = arg2.mIndex;
        this.mFromLayout = arg2.mFromLayout;
        this.mFragmentId = arg2.mFragmentId;
        this.mContainerId = arg2.mContainerId;
        this.mTag = arg2.mTag;
        this.mRetainInstance = arg2.mRetainInstance;
        this.mDetached = arg2.mDetached;
        this.mArguments = arg2.mArguments;
        this.mHidden = arg2.mHidden;
    }

    public int describeContents() {
        return 0;
    }

    public Fragment instantiate(FragmentHostCallback arg4, FragmentContainer arg5, Fragment arg6, FragmentManagerNonConfig arg7) {
        if(this.mInstance == null) {
            Context v0 = arg4.getContext();
            if(this.mArguments != null) {
                this.mArguments.setClassLoader(v0.getClassLoader());
            }

            this.mInstance = arg5 != null ? arg5.instantiate(v0, this.mClassName, this.mArguments) : Fragment.instantiate(v0, this.mClassName, this.mArguments);
            if(this.mSavedFragmentState != null) {
                this.mSavedFragmentState.setClassLoader(v0.getClassLoader());
                this.mInstance.mSavedFragmentState = this.mSavedFragmentState;
            }

            this.mInstance.setIndex(this.mIndex, arg6);
            this.mInstance.mFromLayout = this.mFromLayout;
            this.mInstance.mRestored = true;
            this.mInstance.mFragmentId = this.mFragmentId;
            this.mInstance.mContainerId = this.mContainerId;
            this.mInstance.mTag = this.mTag;
            this.mInstance.mRetainInstance = this.mRetainInstance;
            this.mInstance.mDetached = this.mDetached;
            this.mInstance.mHidden = this.mHidden;
            this.mInstance.mFragmentManager = arg4.mFragmentManager;
            if(!FragmentManagerImpl.DEBUG) {
                goto label_67;
            }

            Log.v("FragmentManager", "Instantiated fragment " + this.mInstance);
        }

    label_67:
        this.mInstance.mChildNonConfig = arg7;
        return this.mInstance;
    }

    public void writeToParcel(Parcel arg1, int arg2) {
        arg1.writeString(this.mClassName);
        arg1.writeInt(this.mIndex);
        arg1.writeInt(this.mFromLayout);
        arg1.writeInt(this.mFragmentId);
        arg1.writeInt(this.mContainerId);
        arg1.writeString(this.mTag);
        arg1.writeInt(this.mRetainInstance);
        arg1.writeInt(this.mDetached);
        arg1.writeBundle(this.mArguments);
        arg1.writeInt(this.mHidden);
        arg1.writeBundle(this.mSavedFragmentState);
    }
}

