package android.support.v4.app;

import android.os.Build$VERSION;
import android.support.v4.util.LogWriter;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.View;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

final class BackStackRecord extends FragmentTransaction implements BackStackEntry, OpGenerator {
    final class Op {
        int cmd;
        int enterAnim;
        int exitAnim;
        Fragment fragment;
        int popEnterAnim;
        int popExitAnim;

        Op() {
            super();
        }

        Op(int arg1, Fragment arg2) {
            super();
            this.cmd = arg1;
            this.fragment = arg2;
        }
    }

    static final int OP_ADD = 1;
    static final int OP_ATTACH = 7;
    static final int OP_DETACH = 6;
    static final int OP_HIDE = 4;
    static final int OP_NULL = 0;
    static final int OP_REMOVE = 3;
    static final int OP_REPLACE = 2;
    static final int OP_SET_PRIMARY_NAV = 8;
    static final int OP_SHOW = 5;
    static final int OP_UNSET_PRIMARY_NAV = 9;
    static final boolean SUPPORTS_TRANSITIONS = false;
    static final String TAG = "FragmentManager";
    boolean mAddToBackStack;
    boolean mAllowAddToBackStack;
    int mBreadCrumbShortTitleRes;
    CharSequence mBreadCrumbShortTitleText;
    int mBreadCrumbTitleRes;
    CharSequence mBreadCrumbTitleText;
    ArrayList mCommitRunnables;
    boolean mCommitted;
    int mEnterAnim;
    int mExitAnim;
    int mIndex;
    final FragmentManagerImpl mManager;
    String mName;
    ArrayList mOps;
    int mPopEnterAnim;
    int mPopExitAnim;
    boolean mReorderingAllowed;
    ArrayList mSharedElementSourceNames;
    ArrayList mSharedElementTargetNames;
    int mTransition;
    int mTransitionStyle;

    static {
        boolean v0 = Build$VERSION.SDK_INT >= 21 ? true : false;
        BackStackRecord.SUPPORTS_TRANSITIONS = v0;
    }

    public BackStackRecord(FragmentManagerImpl arg2) {
        super();
        this.mOps = new ArrayList();
        this.mAllowAddToBackStack = true;
        this.mIndex = -1;
        this.mReorderingAllowed = false;
        this.mManager = arg2;
    }

    public FragmentTransaction add(int arg3, Fragment arg4) {
        this.doAddOp(arg3, arg4, null, 1);
        return this;
    }

    public FragmentTransaction add(int arg2, Fragment arg3, String arg4) {
        this.doAddOp(arg2, arg3, arg4, 1);
        return this;
    }

    public FragmentTransaction add(Fragment arg3, String arg4) {
        this.doAddOp(0, arg3, arg4, 1);
        return this;
    }

    void addOp(Op arg2) {
        this.mOps.add(arg2);
        arg2.enterAnim = this.mEnterAnim;
        arg2.exitAnim = this.mExitAnim;
        arg2.popEnterAnim = this.mPopEnterAnim;
        arg2.popExitAnim = this.mPopExitAnim;
    }

    public FragmentTransaction addSharedElement(View arg3, String arg4) {
        StringBuilder v0;
        if(BackStackRecord.SUPPORTS_TRANSITIONS) {
            String v3 = ViewCompat.getTransitionName(arg3);
            if(v3 == null) {
                throw new IllegalArgumentException("Unique transitionNames are required for all sharedElements");
            }
            else {
                if(this.mSharedElementSourceNames == null) {
                    this.mSharedElementSourceNames = new ArrayList();
                    this.mSharedElementTargetNames = new ArrayList();
                }
                else if(this.mSharedElementTargetNames.contains(arg4)) {
                    v0 = new StringBuilder();
                    v0.append("A shared element with the target name \'");
                    v0.append(arg4);
                    v0.append("\' has already been added to the transaction.");
                    throw new IllegalArgumentException(v0.toString());
                }
                else if(this.mSharedElementSourceNames.contains(v3)) {
                    v0 = new StringBuilder();
                    v0.append("A shared element with the source name \'");
                    v0.append(v3);
                    v0.append(" has already been added to the transaction.");
                    throw new IllegalArgumentException(v0.toString());
                }

                this.mSharedElementSourceNames.add(v3);
                this.mSharedElementTargetNames.add(arg4);
            }
        }

        return this;
    }

    public FragmentTransaction addToBackStack(String arg2) {
        if(!this.mAllowAddToBackStack) {
            throw new IllegalStateException("This FragmentTransaction is not allowed to be added to the back stack.");
        }

        this.mAddToBackStack = true;
        this.mName = arg2;
        return this;
    }

    public FragmentTransaction attach(Fragment arg3) {
        this.addOp(new Op(7, arg3));
        return this;
    }

    void bumpBackStackNesting(int arg7) {
        if(!this.mAddToBackStack) {
            return;
        }

        if(FragmentManagerImpl.DEBUG) {
            Log.v("FragmentManager", "Bump nesting in " + this + " by " + arg7);
        }

        int v0 = this.mOps.size();
        int v1_1;
        for(v1_1 = 0; v1_1 < v0; ++v1_1) {
            Object v2 = this.mOps.get(v1_1);
            if(((Op)v2).fragment != null) {
                ((Op)v2).fragment.mBackStackNesting += arg7;
                if(FragmentManagerImpl.DEBUG) {
                    Log.v("FragmentManager", "Bump nesting of " + ((Op)v2).fragment + " to " + ((Op)v2).fragment.mBackStackNesting);
                }
            }
        }
    }

    public int commit() {
        return this.commitInternal(false);
    }

    public int commitAllowingStateLoss() {
        return this.commitInternal(true);
    }

    int commitInternal(boolean arg4) {
        if(this.mCommitted) {
            throw new IllegalStateException("commit already called");
        }

        if(FragmentManagerImpl.DEBUG) {
            Log.v("FragmentManager", "Commit: " + this);
            PrintWriter v1_1 = new PrintWriter(new LogWriter("FragmentManager"));
            this.dump("  ", null, v1_1, null);
            v1_1.close();
        }

        this.mCommitted = true;
        this.mIndex = this.mAddToBackStack ? this.mManager.allocBackStackIndex(this) : -1;
        this.mManager.enqueueAction(((OpGenerator)this), arg4);
        return this.mIndex;
    }

    public void commitNow() {
        this.disallowAddToBackStack();
        this.mManager.execSingleAction(((OpGenerator)this), false);
    }

    public void commitNowAllowingStateLoss() {
        this.disallowAddToBackStack();
        this.mManager.execSingleAction(((OpGenerator)this), true);
    }

    public FragmentTransaction detach(Fragment arg3) {
        this.addOp(new Op(6, arg3));
        return this;
    }

    public FragmentTransaction disallowAddToBackStack() {
        if(this.mAddToBackStack) {
            throw new IllegalStateException("This transaction is already being added to the back stack");
        }

        this.mAllowAddToBackStack = false;
        return this;
    }

    private void doAddOp(int arg4, Fragment arg5, String arg6, int arg7) {
        StringBuilder v7;
        Class v0 = arg5.getClass();
        int v1 = v0.getModifiers();
        if(!v0.isAnonymousClass() && (Modifier.isPublic(v1)) && (!v0.isMemberClass() || (Modifier.isStatic(v1)))) {
            arg5.mFragmentManager = this.mManager;
            if(arg6 != null) {
                if(arg5.mTag != null && !arg6.equals(arg5.mTag)) {
                    v7 = new StringBuilder();
                    v7.append("Can\'t change tag of fragment ");
                    v7.append(arg5);
                    v7.append(": was ");
                    v7.append(arg5.mTag);
                    v7.append(" now ");
                    v7.append(arg6);
                    throw new IllegalStateException(v7.toString());
                }

                arg5.mTag = arg6;
            }

            if(arg4 != 0) {
                if(arg4 == -1) {
                    v7 = new StringBuilder();
                    v7.append("Can\'t add fragment ");
                    v7.append(arg5);
                    v7.append(" with tag ");
                    v7.append(arg6);
                    v7.append(" to container view with no id");
                    throw new IllegalArgumentException(v7.toString());
                }
                else {
                    if(arg5.mFragmentId != 0 && arg5.mFragmentId != arg4) {
                        v7 = new StringBuilder();
                        v7.append("Can\'t change container ID of fragment ");
                        v7.append(arg5);
                        v7.append(": was ");
                        v7.append(arg5.mFragmentId);
                        v7.append(" now ");
                        v7.append(arg4);
                        throw new IllegalStateException(v7.toString());
                    }

                    arg5.mFragmentId = arg4;
                    arg5.mContainerId = arg4;
                }
            }

            this.addOp(new Op(arg7, arg5));
            return;
        }

        StringBuilder v5 = new StringBuilder();
        v5.append("Fragment ");
        v5.append(v0.getCanonicalName());
        v5.append(" must be a public static class to be  properly recreated from");
        v5.append(" instance state.");
        throw new IllegalStateException(v5.toString());
    }

    public void dump(String arg1, FileDescriptor arg2, PrintWriter arg3, String[] arg4) {
        this.dump(arg1, arg3, true);
    }

    public void dump(String arg6, PrintWriter arg7, boolean arg8) {
        String v3_1;
        if(arg8) {
            arg7.print(arg6);
            arg7.print("mName=");
            arg7.print(this.mName);
            arg7.print(" mIndex=");
            arg7.print(this.mIndex);
            arg7.print(" mCommitted=");
            arg7.println(this.mCommitted);
            if(this.mTransition != 0) {
                arg7.print(arg6);
                arg7.print("mTransition=#");
                arg7.print(Integer.toHexString(this.mTransition));
                arg7.print(" mTransitionStyle=#");
                arg7.println(Integer.toHexString(this.mTransitionStyle));
            }

            if(this.mEnterAnim != 0 || this.mExitAnim != 0) {
                arg7.print(arg6);
                arg7.print("mEnterAnim=#");
                arg7.print(Integer.toHexString(this.mEnterAnim));
                arg7.print(" mExitAnim=#");
                arg7.println(Integer.toHexString(this.mExitAnim));
            }

            if(this.mPopEnterAnim != 0 || this.mPopExitAnim != 0) {
                arg7.print(arg6);
                arg7.print("mPopEnterAnim=#");
                arg7.print(Integer.toHexString(this.mPopEnterAnim));
                arg7.print(" mPopExitAnim=#");
                arg7.println(Integer.toHexString(this.mPopExitAnim));
            }

            if(this.mBreadCrumbTitleRes != 0 || this.mBreadCrumbTitleText != null) {
                arg7.print(arg6);
                arg7.print("mBreadCrumbTitleRes=#");
                arg7.print(Integer.toHexString(this.mBreadCrumbTitleRes));
                arg7.print(" mBreadCrumbTitleText=");
                arg7.println(this.mBreadCrumbTitleText);
            }

            if(this.mBreadCrumbShortTitleRes == 0 && this.mBreadCrumbShortTitleText == null) {
                goto label_85;
            }

            arg7.print(arg6);
            arg7.print("mBreadCrumbShortTitleRes=#");
            arg7.print(Integer.toHexString(this.mBreadCrumbShortTitleRes));
            arg7.print(" mBreadCrumbShortTitleText=");
            arg7.println(this.mBreadCrumbShortTitleText);
        }

    label_85:
        if(!this.mOps.isEmpty()) {
            arg7.print(arg6);
            arg7.println("Operations:");
            StringBuilder v0 = new StringBuilder();
            v0.append(arg6);
            v0.append("    ");
            v0.toString();
            int v0_1 = this.mOps.size();
            int v1;
            for(v1 = 0; v1 < v0_1; ++v1) {
                Object v2 = this.mOps.get(v1);
                switch(((Op)v2).cmd) {
                    case 0: {
                        v3_1 = "NULL";
                        break;
                    }
                    case 1: {
                        v3_1 = "ADD";
                        break;
                    }
                    case 2: {
                        v3_1 = "REPLACE";
                        break;
                    }
                    case 3: {
                        v3_1 = "REMOVE";
                        break;
                    }
                    case 4: {
                        v3_1 = "HIDE";
                        break;
                    }
                    case 5: {
                        v3_1 = "SHOW";
                        break;
                    }
                    case 6: {
                        v3_1 = "DETACH";
                        break;
                    }
                    case 7: {
                        v3_1 = "ATTACH";
                        break;
                    }
                    case 8: {
                        v3_1 = "SET_PRIMARY_NAV";
                        break;
                    }
                    case 9: {
                        v3_1 = "UNSET_PRIMARY_NAV";
                        break;
                    }
                    default: {
                        v3_1 = "cmd=" + ((Op)v2).cmd;
                        break;
                    }
                }

                arg7.print(arg6);
                arg7.print("  Op #");
                arg7.print(v1);
                arg7.print(": ");
                arg7.print(v3_1);
                arg7.print(" ");
                arg7.println(((Op)v2).fragment);
                if(arg8) {
                    if(((Op)v2).enterAnim != 0 || ((Op)v2).exitAnim != 0) {
                        arg7.print(arg6);
                        arg7.print("enterAnim=#");
                        arg7.print(Integer.toHexString(((Op)v2).enterAnim));
                        arg7.print(" exitAnim=#");
                        arg7.println(Integer.toHexString(((Op)v2).exitAnim));
                    }

                    if(((Op)v2).popEnterAnim == 0 && ((Op)v2).popExitAnim == 0) {
                        goto label_174;
                    }

                    arg7.print(arg6);
                    arg7.print("popEnterAnim=#");
                    arg7.print(Integer.toHexString(((Op)v2).popEnterAnim));
                    arg7.print(" popExitAnim=#");
                    arg7.println(Integer.toHexString(((Op)v2).popExitAnim));
                }

            label_174:
            }
        }
    }

    void executeOps() {
        int v0 = this.mOps.size();
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            Object v4 = this.mOps.get(v2);
            Fragment v5 = ((Op)v4).fragment;
            if(v5 != null) {
                v5.setNextTransition(this.mTransition, this.mTransitionStyle);
            }

            int v6 = ((Op)v4).cmd;
            if(v6 != 1) {
                switch(v6) {
                    case 3: {
                        goto label_53;
                    }
                    case 4: {
                        goto label_48;
                    }
                    case 5: {
                        goto label_43;
                    }
                    case 6: {
                        goto label_38;
                    }
                    case 7: {
                        goto label_33;
                    }
                    case 8: {
                        goto label_30;
                    }
                    case 9: {
                        goto label_26;
                    }
                }

                StringBuilder v1 = new StringBuilder();
                v1.append("Unknown cmd: ");
                v1.append(((Op)v4).cmd);
                throw new IllegalArgumentException(v1.toString());
            label_33:
                v5.setNextAnim(((Op)v4).enterAnim);
                this.mManager.attachFragment(v5);
                goto label_62;
            label_53:
                v5.setNextAnim(((Op)v4).exitAnim);
                this.mManager.removeFragment(v5);
                goto label_62;
            label_38:
                v5.setNextAnim(((Op)v4).exitAnim);
                this.mManager.detachFragment(v5);
                goto label_62;
            label_26:
                this.mManager.setPrimaryNavigationFragment(null);
                goto label_62;
            label_43:
                v5.setNextAnim(((Op)v4).enterAnim);
                this.mManager.showFragment(v5);
                goto label_62;
            label_30:
                this.mManager.setPrimaryNavigationFragment(v5);
                goto label_62;
            label_48:
                v5.setNextAnim(((Op)v4).exitAnim);
                this.mManager.hideFragment(v5);
            }
            else {
                v5.setNextAnim(((Op)v4).enterAnim);
                this.mManager.addFragment(v5, false);
            }

        label_62:
            if(!this.mReorderingAllowed && ((Op)v4).cmd != 1 && v5 != null) {
                this.mManager.moveFragmentToExpectedState(v5);
            }
        }

        if(!this.mReorderingAllowed) {
            this.mManager.moveToState(this.mManager.mCurState, true);
        }
    }

    void executePopOps(boolean arg7) {
        int v0;
        for(v0 = this.mOps.size() - 1; v0 >= 0; --v0) {
            Object v2 = this.mOps.get(v0);
            Fragment v3 = ((Op)v2).fragment;
            if(v3 != null) {
                v3.setNextTransition(FragmentManagerImpl.reverseTransit(this.mTransition), this.mTransitionStyle);
            }

            int v4 = ((Op)v2).cmd;
            if(v4 != 1) {
                switch(v4) {
                    case 3: {
                        goto label_53;
                    }
                    case 4: {
                        goto label_48;
                    }
                    case 5: {
                        goto label_43;
                    }
                    case 6: {
                        goto label_38;
                    }
                    case 7: {
                        goto label_33;
                    }
                    case 8: {
                        goto label_29;
                    }
                    case 9: {
                        goto label_26;
                    }
                }

                StringBuilder v0_1 = new StringBuilder();
                v0_1.append("Unknown cmd: ");
                v0_1.append(((Op)v2).cmd);
                throw new IllegalArgumentException(v0_1.toString());
            label_33:
                v3.setNextAnim(((Op)v2).popExitAnim);
                this.mManager.detachFragment(v3);
                goto label_63;
            label_53:
                v3.setNextAnim(((Op)v2).popEnterAnim);
                this.mManager.addFragment(v3, false);
                goto label_63;
            label_38:
                v3.setNextAnim(((Op)v2).popEnterAnim);
                this.mManager.attachFragment(v3);
                goto label_63;
            label_26:
                this.mManager.setPrimaryNavigationFragment(v3);
                goto label_63;
            label_43:
                v3.setNextAnim(((Op)v2).popExitAnim);
                this.mManager.hideFragment(v3);
                goto label_63;
            label_29:
                this.mManager.setPrimaryNavigationFragment(null);
                goto label_63;
            label_48:
                v3.setNextAnim(((Op)v2).popEnterAnim);
                this.mManager.showFragment(v3);
            }
            else {
                v3.setNextAnim(((Op)v2).popExitAnim);
                this.mManager.removeFragment(v3);
            }

        label_63:
            if(!this.mReorderingAllowed && ((Op)v2).cmd != 3 && v3 != null) {
                this.mManager.moveFragmentToExpectedState(v3);
            }
        }

        if(!this.mReorderingAllowed && (arg7)) {
            this.mManager.moveToState(this.mManager.mCurState, true);
        }
    }

    Fragment expandOps(ArrayList arg14, Fragment arg15) {
        Fragment v1 = arg15;
        int v15;
        for(v15 = 0; v15 < this.mOps.size(); ++v15) {
            Object v2 = this.mOps.get(v15);
            Fragment v4 = null;
            int v5 = 9;
            switch(((Op)v2).cmd) {
                case 2: {
                    Fragment v3 = ((Op)v2).fragment;
                    int v7 = v3.mContainerId;
                    int v8 = arg14.size() - 1;
                    Fragment v9 = v1;
                    int v1_1 = v15;
                    v15 = 0;
                    while(v8 >= 0) {
                        Object v10 = arg14.get(v8);
                        if(((Fragment)v10).mContainerId == v7) {
                            if((((Fragment)v10)) == v3) {
                                v15 = 1;
                            }
                            else {
                                if((((Fragment)v10)) == v9) {
                                    this.mOps.add(v1_1, new Op(v5, ((Fragment)v10)));
                                    ++v1_1;
                                    v9 = v4;
                                }

                                Op v11 = new Op(3, ((Fragment)v10));
                                v11.enterAnim = ((Op)v2).enterAnim;
                                v11.popEnterAnim = ((Op)v2).popEnterAnim;
                                v11.exitAnim = ((Op)v2).exitAnim;
                                v11.popExitAnim = ((Op)v2).popExitAnim;
                                this.mOps.add(v1_1, v11);
                                arg14.remove(v10);
                                ++v1_1;
                            }
                        }

                        --v8;
                    }

                    if(v15 != 0) {
                        this.mOps.remove(v1_1);
                        --v1_1;
                    }
                    else {
                        ((Op)v2).cmd = 1;
                        arg14.add(v3);
                    }

                    v15 = v1_1;
                    v1 = v9;
                    break;
                }
                case 3: 
                case 6: {
                    arg14.remove(((Op)v2).fragment);
                    if(((Op)v2).fragment != v1) {
                        goto label_84;
                    }

                    this.mOps.add(v15, new Op(v5, ((Op)v2).fragment));
                    ++v15;
                    v1 = v4;
                    break;
                }
                case 1: 
                case 7: {
                    arg14.add(((Op)v2).fragment);
                    break;
                }
                case 8: {
                    this.mOps.add(v15, new Op(v5, v1));
                    ++v15;
                    v1 = ((Op)v2).fragment;
                    break;
                }
                default: {
                    break;
                }
            }

        label_84:
        }

        return v1;
    }

    public boolean generateOps(ArrayList arg4, ArrayList arg5) {
        if(FragmentManagerImpl.DEBUG) {
            Log.v("FragmentManager", "Run: " + this);
        }

        arg4.add(this);
        arg5.add(Boolean.valueOf(false));
        if(this.mAddToBackStack) {
            this.mManager.addBackStackState(this);
        }

        return 1;
    }

    public CharSequence getBreadCrumbShortTitle() {
        if(this.mBreadCrumbShortTitleRes != 0) {
            return this.mManager.mHost.getContext().getText(this.mBreadCrumbShortTitleRes);
        }

        return this.mBreadCrumbShortTitleText;
    }

    public int getBreadCrumbShortTitleRes() {
        return this.mBreadCrumbShortTitleRes;
    }

    public CharSequence getBreadCrumbTitle() {
        if(this.mBreadCrumbTitleRes != 0) {
            return this.mManager.mHost.getContext().getText(this.mBreadCrumbTitleRes);
        }

        return this.mBreadCrumbTitleText;
    }

    public int getBreadCrumbTitleRes() {
        return this.mBreadCrumbTitleRes;
    }

    public int getId() {
        return this.mIndex;
    }

    public String getName() {
        return this.mName;
    }

    public int getTransition() {
        return this.mTransition;
    }

    public int getTransitionStyle() {
        return this.mTransitionStyle;
    }

    public FragmentTransaction hide(Fragment arg3) {
        this.addOp(new Op(4, arg3));
        return this;
    }

    boolean interactsWith(int arg6) {
        int v0 = this.mOps.size();
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            Object v3 = this.mOps.get(v2);
            int v3_1 = ((Op)v3).fragment != null ? ((Op)v3).fragment.mContainerId : 0;
            if(v3_1 != 0 && v3_1 == arg6) {
                return 1;
            }
        }

        return 0;
    }

    boolean interactsWith(ArrayList arg11, int arg12, int arg13) {
        if(arg13 == arg12) {
            return 0;
        }

        int v1 = this.mOps.size();
        int v2 = 0;
        int v3 = -1;
        while(v2 < v1) {
            Object v4 = this.mOps.get(v2);
            int v4_1 = ((Op)v4).fragment != null ? ((Op)v4).fragment.mContainerId : 0;
            if(v4_1 != 0 && v4_1 != v3) {
                for(v3 = arg12; v3 < arg13; ++v3) {
                    Object v5 = arg11.get(v3);
                    int v6 = ((BackStackRecord)v5).mOps.size();
                    int v7;
                    for(v7 = 0; v7 < v6; ++v7) {
                        Object v8 = ((BackStackRecord)v5).mOps.get(v7);
                        int v8_1 = ((Op)v8).fragment != null ? ((Op)v8).fragment.mContainerId : 0;
                        if(v8_1 == v4_1) {
                            return 1;
                        }
                    }
                }

                v3 = v4_1;
            }

            ++v2;
        }

        return 0;
    }

    public boolean isAddToBackStackAllowed() {
        return this.mAllowAddToBackStack;
    }

    public boolean isEmpty() {
        return this.mOps.isEmpty();
    }

    private static boolean isFragmentPostponed(Op arg1) {
        Fragment v1 = arg1.fragment;
        boolean v1_1 = v1 == null || !v1.mAdded || v1.mView == null || (v1.mDetached) || (v1.mHidden) || !v1.isPostponed() ? false : true;
        return v1_1;
    }

    boolean isPostponed() {
        int v1;
        for(v1 = 0; v1 < this.mOps.size(); ++v1) {
            if(BackStackRecord.isFragmentPostponed(this.mOps.get(v1))) {
                return 1;
            }
        }

        return 0;
    }

    public FragmentTransaction remove(Fragment arg3) {
        this.addOp(new Op(3, arg3));
        return this;
    }

    public FragmentTransaction replace(int arg2, Fragment arg3) {
        return this.replace(arg2, arg3, null);
    }

    public FragmentTransaction replace(int arg2, Fragment arg3, String arg4) {
        if(arg2 == 0) {
            throw new IllegalArgumentException("Must use non-zero containerViewId");
        }

        this.doAddOp(arg2, arg3, arg4, 2);
        return this;
    }

    public FragmentTransaction runOnCommit(Runnable arg2) {
        if(arg2 == null) {
            throw new IllegalArgumentException("runnable cannot be null");
        }

        this.disallowAddToBackStack();
        if(this.mCommitRunnables == null) {
            this.mCommitRunnables = new ArrayList();
        }

        this.mCommitRunnables.add(arg2);
        return this;
    }

    public void runOnCommitRunnables() {
        if(this.mCommitRunnables != null) {
            int v0 = 0;
            int v1 = this.mCommitRunnables.size();
            while(v0 < v1) {
                this.mCommitRunnables.get(v0).run();
                ++v0;
            }

            this.mCommitRunnables = null;
        }
    }

    public FragmentTransaction setAllowOptimization(boolean arg1) {
        return this.setReorderingAllowed(arg1);
    }

    public FragmentTransaction setBreadCrumbShortTitle(int arg1) {
        this.mBreadCrumbShortTitleRes = arg1;
        this.mBreadCrumbShortTitleText = null;
        return this;
    }

    public FragmentTransaction setBreadCrumbShortTitle(CharSequence arg2) {
        this.mBreadCrumbShortTitleRes = 0;
        this.mBreadCrumbShortTitleText = arg2;
        return this;
    }

    public FragmentTransaction setBreadCrumbTitle(int arg1) {
        this.mBreadCrumbTitleRes = arg1;
        this.mBreadCrumbTitleText = null;
        return this;
    }

    public FragmentTransaction setBreadCrumbTitle(CharSequence arg2) {
        this.mBreadCrumbTitleRes = 0;
        this.mBreadCrumbTitleText = arg2;
        return this;
    }

    public FragmentTransaction setCustomAnimations(int arg2, int arg3) {
        return this.setCustomAnimations(arg2, arg3, 0, 0);
    }

    public FragmentTransaction setCustomAnimations(int arg1, int arg2, int arg3, int arg4) {
        this.mEnterAnim = arg1;
        this.mExitAnim = arg2;
        this.mPopEnterAnim = arg3;
        this.mPopExitAnim = arg4;
        return this;
    }

    void setOnStartPostponedListener(OnStartEnterTransitionListener arg4) {
        int v0;
        for(v0 = 0; v0 < this.mOps.size(); ++v0) {
            Object v1 = this.mOps.get(v0);
            if(BackStackRecord.isFragmentPostponed(((Op)v1))) {
                ((Op)v1).fragment.setOnStartEnterTransitionListener(arg4);
            }
        }
    }

    public FragmentTransaction setPrimaryNavigationFragment(Fragment arg3) {
        this.addOp(new Op(8, arg3));
        return this;
    }

    public FragmentTransaction setReorderingAllowed(boolean arg1) {
        this.mReorderingAllowed = arg1;
        return this;
    }

    public FragmentTransaction setTransition(int arg1) {
        this.mTransition = arg1;
        return this;
    }

    public FragmentTransaction setTransitionStyle(int arg1) {
        this.mTransitionStyle = arg1;
        return this;
    }

    public FragmentTransaction show(Fragment arg3) {
        this.addOp(new Op(5, arg3));
        return this;
    }

    public String toString() {
        StringBuilder v0 = new StringBuilder(0x80);
        v0.append("BackStackEntry{");
        v0.append(Integer.toHexString(System.identityHashCode(this)));
        if(this.mIndex >= 0) {
            v0.append(" #");
            v0.append(this.mIndex);
        }

        if(this.mName != null) {
            v0.append(" ");
            v0.append(this.mName);
        }

        v0.append("}");
        return v0.toString();
    }

    Fragment trackAddedFragmentsInPop(ArrayList arg5, Fragment arg6) {
        int v0;
        for(v0 = 0; v0 < this.mOps.size(); ++v0) {
            Object v1 = this.mOps.get(v0);
            int v2 = ((Op)v1).cmd;
            if(v2 != 1) {
                if(v2 != 3) {
                    switch(v2) {
                        case 6: {
                            goto label_17;
                        }
                        case 7: {
                            goto label_20;
                        }
                        case 8: {
                            goto label_15;
                        }
                        case 9: {
                            goto label_13;
                        }
                    }

                    goto label_22;
                label_13:
                    arg6 = ((Op)v1).fragment;
                    goto label_22;
                label_15:
                    arg6 = null;
                    goto label_22;
                }

            label_17:
                arg5.add(((Op)v1).fragment);
            }
            else {
            label_20:
                arg5.remove(((Op)v1).fragment);
            }

        label_22:
        }

        return arg6;
    }
}

