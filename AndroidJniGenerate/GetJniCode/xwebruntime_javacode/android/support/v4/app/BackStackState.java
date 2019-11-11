package android.support.v4.app;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;

final class BackStackState implements Parcelable {
    final class android.support.v4.app.BackStackState$1 implements Parcelable$Creator {
        android.support.v4.app.BackStackState$1() {
            super();
        }

        public BackStackState createFromParcel(Parcel arg2) {
            return new BackStackState(arg2);
        }

        public Object createFromParcel(Parcel arg1) {
            return this.createFromParcel(arg1);
        }

        public BackStackState[] newArray(int arg1) {
            return new BackStackState[arg1];
        }

        public Object[] newArray(int arg1) {
            return this.newArray(arg1);
        }
    }

    public static final Parcelable$Creator CREATOR;
    final int mBreadCrumbShortTitleRes;
    final CharSequence mBreadCrumbShortTitleText;
    final int mBreadCrumbTitleRes;
    final CharSequence mBreadCrumbTitleText;
    final int mIndex;
    final String mName;
    final int[] mOps;
    final boolean mReorderingAllowed;
    final ArrayList mSharedElementSourceNames;
    final ArrayList mSharedElementTargetNames;
    final int mTransition;
    final int mTransitionStyle;

    static {
        BackStackState.CREATOR = new android.support.v4.app.BackStackState$1();
    }

    public BackStackState(Parcel arg2) {
        super();
        this.mOps = arg2.createIntArray();
        this.mTransition = arg2.readInt();
        this.mTransitionStyle = arg2.readInt();
        this.mName = arg2.readString();
        this.mIndex = arg2.readInt();
        this.mBreadCrumbTitleRes = arg2.readInt();
        this.mBreadCrumbTitleText = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(arg2);
        this.mBreadCrumbShortTitleRes = arg2.readInt();
        this.mBreadCrumbShortTitleText = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(arg2);
        this.mSharedElementSourceNames = arg2.createStringArrayList();
        this.mSharedElementTargetNames = arg2.createStringArrayList();
        boolean v2 = arg2.readInt() != 0 ? true : false;
        this.mReorderingAllowed = v2;
    }

    public BackStackState(BackStackRecord arg8) {
        super();
        int v0 = arg8.mOps.size();
        this.mOps = new int[v0 * 6];
        if(!arg8.mAddToBackStack) {
            throw new IllegalStateException("Not on back stack");
        }

        int v1 = 0;
        int v2;
        for(v2 = 0; v1 < v0; v2 = v5 + 1) {
            Object v3 = arg8.mOps.get(v1);
            int v5 = v2 + 1;
            this.mOps[v2] = ((Op)v3).cmd;
            int[] v2_1 = this.mOps;
            int v4 = v5 + 1;
            int v6 = ((Op)v3).fragment != null ? ((Op)v3).fragment.mIndex : -1;
            v2_1[v5] = v6;
            v5 = v4 + 1;
            this.mOps[v4] = ((Op)v3).enterAnim;
            v4 = v5 + 1;
            this.mOps[v5] = ((Op)v3).exitAnim;
            v5 = v4 + 1;
            this.mOps[v4] = ((Op)v3).popEnterAnim;
            this.mOps[v5] = ((Op)v3).popExitAnim;
            ++v1;
        }

        this.mTransition = arg8.mTransition;
        this.mTransitionStyle = arg8.mTransitionStyle;
        this.mName = arg8.mName;
        this.mIndex = arg8.mIndex;
        this.mBreadCrumbTitleRes = arg8.mBreadCrumbTitleRes;
        this.mBreadCrumbTitleText = arg8.mBreadCrumbTitleText;
        this.mBreadCrumbShortTitleRes = arg8.mBreadCrumbShortTitleRes;
        this.mBreadCrumbShortTitleText = arg8.mBreadCrumbShortTitleText;
        this.mSharedElementSourceNames = arg8.mSharedElementSourceNames;
        this.mSharedElementTargetNames = arg8.mSharedElementTargetNames;
        this.mReorderingAllowed = arg8.mReorderingAllowed;
    }

    public int describeContents() {
        return 0;
    }

    public BackStackRecord instantiate(FragmentManagerImpl arg8) {
        BackStackRecord v0 = new BackStackRecord(arg8);
        int v1 = 0;
        int v2 = 0;
        while(v1 < this.mOps.length) {
            Op v3 = new Op();
            int v5 = v1 + 1;
            v3.cmd = this.mOps[v1];
            if(FragmentManagerImpl.DEBUG) {
                Log.v("FragmentManager", "Instantiate " + v0 + " op #" + v2 + " base fragment #" + this.mOps[v5]);
            }

            int v4_1 = v5 + 1;
            v1 = this.mOps[v5];
            v3.fragment = v1 >= 0 ? arg8.mActive.get(v1) : null;
            v5 = v4_1 + 1;
            v3.enterAnim = this.mOps[v4_1];
            v4_1 = v5 + 1;
            v3.exitAnim = this.mOps[v5];
            v5 = v4_1 + 1;
            v3.popEnterAnim = this.mOps[v4_1];
            v3.popExitAnim = this.mOps[v5];
            v0.mEnterAnim = v3.enterAnim;
            v0.mExitAnim = v3.exitAnim;
            v0.mPopEnterAnim = v3.popEnterAnim;
            v0.mPopExitAnim = v3.popExitAnim;
            v0.addOp(v3);
            ++v2;
            v1 = v5 + 1;
        }

        v0.mTransition = this.mTransition;
        v0.mTransitionStyle = this.mTransitionStyle;
        v0.mName = this.mName;
        v0.mIndex = this.mIndex;
        v0.mAddToBackStack = true;
        v0.mBreadCrumbTitleRes = this.mBreadCrumbTitleRes;
        v0.mBreadCrumbTitleText = this.mBreadCrumbTitleText;
        v0.mBreadCrumbShortTitleRes = this.mBreadCrumbShortTitleRes;
        v0.mBreadCrumbShortTitleText = this.mBreadCrumbShortTitleText;
        v0.mSharedElementSourceNames = this.mSharedElementSourceNames;
        v0.mSharedElementTargetNames = this.mSharedElementTargetNames;
        v0.mReorderingAllowed = this.mReorderingAllowed;
        v0.bumpBackStackNesting(1);
        return v0;
    }

    public void writeToParcel(Parcel arg2, int arg3) {
        arg2.writeIntArray(this.mOps);
        arg2.writeInt(this.mTransition);
        arg2.writeInt(this.mTransitionStyle);
        arg2.writeString(this.mName);
        arg2.writeInt(this.mIndex);
        arg2.writeInt(this.mBreadCrumbTitleRes);
        TextUtils.writeToParcel(this.mBreadCrumbTitleText, arg2, 0);
        arg2.writeInt(this.mBreadCrumbShortTitleRes);
        TextUtils.writeToParcel(this.mBreadCrumbShortTitleText, arg2, 0);
        arg2.writeStringList(this.mSharedElementSourceNames);
        arg2.writeStringList(this.mSharedElementTargetNames);
        arg2.writeInt(this.mReorderingAllowed);
    }
}

