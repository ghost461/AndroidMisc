package android.support.v4.app;

import android.animation.Animator;
import android.app.Activity;
import android.arch.lifecycle.Lifecycle$Event;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender$SendIntentException;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.annotation.StringRes;
import android.support.v4.util.DebugUtils;
import android.support.v4.util.SimpleArrayMap;
import android.support.v4.view.LayoutInflaterCompat;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.ContextMenu$ContextMenuInfo;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View$OnCreateContextMenuListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;

public class Fragment implements LifecycleOwner, ComponentCallbacks, View$OnCreateContextMenuListener {
    class AnimationInfo {
        private Boolean mAllowEnterTransitionOverlap;
        private Boolean mAllowReturnTransitionOverlap;
        View mAnimatingAway;
        Animator mAnimator;
        private Object mEnterTransition;
        SharedElementCallback mEnterTransitionCallback;
        boolean mEnterTransitionPostponed;
        private Object mExitTransition;
        SharedElementCallback mExitTransitionCallback;
        boolean mIsHideReplaced;
        int mNextAnim;
        int mNextTransition;
        int mNextTransitionStyle;
        private Object mReenterTransition;
        private Object mReturnTransition;
        private Object mSharedElementEnterTransition;
        private Object mSharedElementReturnTransition;
        OnStartEnterTransitionListener mStartEnterTransitionListener;
        int mStateAfterAnimating;

        AnimationInfo() {
            super();
            this.mEnterTransition = null;
            this.mReturnTransition = Fragment.USE_DEFAULT_TRANSITION;
            this.mExitTransition = null;
            this.mReenterTransition = Fragment.USE_DEFAULT_TRANSITION;
            this.mSharedElementEnterTransition = null;
            this.mSharedElementReturnTransition = Fragment.USE_DEFAULT_TRANSITION;
            this.mEnterTransitionCallback = null;
            this.mExitTransitionCallback = null;
        }

        static Object access$000(AnimationInfo arg0) {
            return arg0.mEnterTransition;
        }

        static Object access$002(AnimationInfo arg0, Object arg1) {
            arg0.mEnterTransition = arg1;
            return arg1;
        }

        static Object access$100(AnimationInfo arg0) {
            return arg0.mReturnTransition;
        }

        static Object access$102(AnimationInfo arg0, Object arg1) {
            arg0.mReturnTransition = arg1;
            return arg1;
        }

        static Object access$200(AnimationInfo arg0) {
            return arg0.mExitTransition;
        }

        static Object access$202(AnimationInfo arg0, Object arg1) {
            arg0.mExitTransition = arg1;
            return arg1;
        }

        static Object access$300(AnimationInfo arg0) {
            return arg0.mReenterTransition;
        }

        static Object access$302(AnimationInfo arg0, Object arg1) {
            arg0.mReenterTransition = arg1;
            return arg1;
        }

        static Object access$400(AnimationInfo arg0) {
            return arg0.mSharedElementEnterTransition;
        }

        static Object access$402(AnimationInfo arg0, Object arg1) {
            arg0.mSharedElementEnterTransition = arg1;
            return arg1;
        }

        static Object access$500(AnimationInfo arg0) {
            return arg0.mSharedElementReturnTransition;
        }

        static Object access$502(AnimationInfo arg0, Object arg1) {
            arg0.mSharedElementReturnTransition = arg1;
            return arg1;
        }

        static Boolean access$600(AnimationInfo arg0) {
            return arg0.mAllowEnterTransitionOverlap;
        }

        static Boolean access$602(AnimationInfo arg0, Boolean arg1) {
            arg0.mAllowEnterTransitionOverlap = arg1;
            return arg1;
        }

        static Boolean access$700(AnimationInfo arg0) {
            return arg0.mAllowReturnTransitionOverlap;
        }

        static Boolean access$702(AnimationInfo arg0, Boolean arg1) {
            arg0.mAllowReturnTransitionOverlap = arg1;
            return arg1;
        }
    }

    public class InstantiationException extends RuntimeException {
        public InstantiationException(String arg1, Exception arg2) {
            super(arg1, ((Throwable)arg2));
        }
    }

    interface OnStartEnterTransitionListener {
        void onStartEnterTransition();

        void startListening();
    }

    public class SavedState implements Parcelable {
        final class android.support.v4.app.Fragment$SavedState$1 implements Parcelable$Creator {
            android.support.v4.app.Fragment$SavedState$1() {
                super();
            }

            public SavedState createFromParcel(Parcel arg3) {
                return new SavedState(arg3, null);
            }

            public Object createFromParcel(Parcel arg1) {
                return this.createFromParcel(arg1);
            }

            public SavedState[] newArray(int arg1) {
                return new SavedState[arg1];
            }

            public Object[] newArray(int arg1) {
                return this.newArray(arg1);
            }
        }

        public static final Parcelable$Creator CREATOR;
        final Bundle mState;

        static {
            SavedState.CREATOR = new android.support.v4.app.Fragment$SavedState$1();
        }

        SavedState(Bundle arg1) {
            super();
            this.mState = arg1;
        }

        SavedState(Parcel arg1, ClassLoader arg2) {
            super();
            this.mState = arg1.readBundle();
            if(arg2 != null && this.mState != null) {
                this.mState.setClassLoader(arg2);
            }
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel arg1, int arg2) {
            arg1.writeBundle(this.mState);
        }
    }

    static final int ACTIVITY_CREATED = 2;
    static final int CREATED = 1;
    static final int INITIALIZING = 0;
    static final int RESUMED = 5;
    static final int STARTED = 4;
    static final int STOPPED = 3;
    static final Object USE_DEFAULT_TRANSITION;
    boolean mAdded;
    AnimationInfo mAnimationInfo;
    Bundle mArguments;
    int mBackStackNesting;
    boolean mCalled;
    boolean mCheckedForLoaderManager;
    FragmentManagerImpl mChildFragmentManager;
    FragmentManagerNonConfig mChildNonConfig;
    ViewGroup mContainer;
    int mContainerId;
    boolean mDeferStart;
    boolean mDetached;
    int mFragmentId;
    FragmentManagerImpl mFragmentManager;
    boolean mFromLayout;
    boolean mHasMenu;
    boolean mHidden;
    boolean mHiddenChanged;
    FragmentHostCallback mHost;
    boolean mInLayout;
    int mIndex;
    View mInnerView;
    boolean mIsCreated;
    boolean mIsNewlyAdded;
    LayoutInflater mLayoutInflater;
    LifecycleRegistry mLifecycleRegistry;
    LoaderManagerImpl mLoaderManager;
    boolean mLoadersStarted;
    boolean mMenuVisible;
    Fragment mParentFragment;
    boolean mPerformedCreateView;
    float mPostponedAlpha;
    boolean mRemoving;
    boolean mRestored;
    boolean mRetainInstance;
    boolean mRetaining;
    Bundle mSavedFragmentState;
    SparseArray mSavedViewState;
    int mState;
    String mTag;
    Fragment mTarget;
    int mTargetIndex;
    int mTargetRequestCode;
    boolean mUserVisibleHint;
    View mView;
    String mWho;
    private static final SimpleArrayMap sClassMap;

    static {
        Fragment.sClassMap = new SimpleArrayMap();
        Fragment.USE_DEFAULT_TRANSITION = new Object();
    }

    public Fragment() {
        super();
        this.mState = 0;
        this.mIndex = -1;
        this.mTargetIndex = -1;
        this.mMenuVisible = true;
        this.mUserVisibleHint = true;
        this.mLifecycleRegistry = new LifecycleRegistry(((LifecycleOwner)this));
    }

    static void access$800(Fragment arg0) {
        arg0.callStartTransitionListener();
    }

    private void callStartTransitionListener() {
        OnStartEnterTransitionListener v0;
        OnStartEnterTransitionListener v1 = null;
        if(this.mAnimationInfo == null) {
            v0 = v1;
        }
        else {
            this.mAnimationInfo.mEnterTransitionPostponed = false;
            v0 = this.mAnimationInfo.mStartEnterTransitionListener;
            this.mAnimationInfo.mStartEnterTransitionListener = v1;
        }

        if(v0 != null) {
            v0.onStartEnterTransition();
        }
    }

    public void dump(String arg4, FileDescriptor arg5, PrintWriter arg6, String[] arg7) {
        arg6.print(arg4);
        arg6.print("mFragmentId=#");
        arg6.print(Integer.toHexString(this.mFragmentId));
        arg6.print(" mContainerId=#");
        arg6.print(Integer.toHexString(this.mContainerId));
        arg6.print(" mTag=");
        arg6.println(this.mTag);
        arg6.print(arg4);
        arg6.print("mState=");
        arg6.print(this.mState);
        arg6.print(" mIndex=");
        arg6.print(this.mIndex);
        arg6.print(" mWho=");
        arg6.print(this.mWho);
        arg6.print(" mBackStackNesting=");
        arg6.println(this.mBackStackNesting);
        arg6.print(arg4);
        arg6.print("mAdded=");
        arg6.print(this.mAdded);
        arg6.print(" mRemoving=");
        arg6.print(this.mRemoving);
        arg6.print(" mFromLayout=");
        arg6.print(this.mFromLayout);
        arg6.print(" mInLayout=");
        arg6.println(this.mInLayout);
        arg6.print(arg4);
        arg6.print("mHidden=");
        arg6.print(this.mHidden);
        arg6.print(" mDetached=");
        arg6.print(this.mDetached);
        arg6.print(" mMenuVisible=");
        arg6.print(this.mMenuVisible);
        arg6.print(" mHasMenu=");
        arg6.println(this.mHasMenu);
        arg6.print(arg4);
        arg6.print("mRetainInstance=");
        arg6.print(this.mRetainInstance);
        arg6.print(" mRetaining=");
        arg6.print(this.mRetaining);
        arg6.print(" mUserVisibleHint=");
        arg6.println(this.mUserVisibleHint);
        if(this.mFragmentManager != null) {
            arg6.print(arg4);
            arg6.print("mFragmentManager=");
            arg6.println(this.mFragmentManager);
        }

        if(this.mHost != null) {
            arg6.print(arg4);
            arg6.print("mHost=");
            arg6.println(this.mHost);
        }

        if(this.mParentFragment != null) {
            arg6.print(arg4);
            arg6.print("mParentFragment=");
            arg6.println(this.mParentFragment);
        }

        if(this.mArguments != null) {
            arg6.print(arg4);
            arg6.print("mArguments=");
            arg6.println(this.mArguments);
        }

        if(this.mSavedFragmentState != null) {
            arg6.print(arg4);
            arg6.print("mSavedFragmentState=");
            arg6.println(this.mSavedFragmentState);
        }

        if(this.mSavedViewState != null) {
            arg6.print(arg4);
            arg6.print("mSavedViewState=");
            arg6.println(this.mSavedViewState);
        }

        if(this.mTarget != null) {
            arg6.print(arg4);
            arg6.print("mTarget=");
            arg6.print(this.mTarget);
            arg6.print(" mTargetRequestCode=");
            arg6.println(this.mTargetRequestCode);
        }

        if(this.getNextAnim() != 0) {
            arg6.print(arg4);
            arg6.print("mNextAnim=");
            arg6.println(this.getNextAnim());
        }

        if(this.mContainer != null) {
            arg6.print(arg4);
            arg6.print("mContainer=");
            arg6.println(this.mContainer);
        }

        if(this.mView != null) {
            arg6.print(arg4);
            arg6.print("mView=");
            arg6.println(this.mView);
        }

        if(this.mInnerView != null) {
            arg6.print(arg4);
            arg6.print("mInnerView=");
            arg6.println(this.mView);
        }

        if(this.getAnimatingAway() != null) {
            arg6.print(arg4);
            arg6.print("mAnimatingAway=");
            arg6.println(this.getAnimatingAway());
            arg6.print(arg4);
            arg6.print("mStateAfterAnimating=");
            arg6.println(this.getStateAfterAnimating());
        }

        if(this.mLoaderManager != null) {
            arg6.print(arg4);
            arg6.println("Loader Manager:");
            LoaderManagerImpl v0 = this.mLoaderManager;
            v0.dump(arg4 + "  ", arg5, arg6, arg7);
        }

        if(this.mChildFragmentManager != null) {
            arg6.print(arg4);
            arg6.println("Child " + this.mChildFragmentManager + ":");
            FragmentManagerImpl v0_2 = this.mChildFragmentManager;
            v0_2.dump(arg4 + "  ", arg5, arg6, arg7);
        }
    }

    private AnimationInfo ensureAnimationInfo() {
        if(this.mAnimationInfo == null) {
            this.mAnimationInfo = new AnimationInfo();
        }

        return this.mAnimationInfo;
    }

    public final boolean equals(Object arg1) {
        return super.equals(arg1);
    }

    Fragment findFragmentByWho(String arg2) {
        if(arg2.equals(this.mWho)) {
            return this;
        }

        if(this.mChildFragmentManager != null) {
            return this.mChildFragmentManager.findFragmentByWho(arg2);
        }

        return null;
    }

    public final FragmentActivity getActivity() {
        Activity v0_1;
        if(this.mHost == null) {
            FragmentActivity v0 = null;
        }
        else {
            v0_1 = this.mHost.getActivity();
        }

        return ((FragmentActivity)v0_1);
    }

    public boolean getAllowEnterTransitionOverlap() {
        boolean v0 = this.mAnimationInfo == null || AnimationInfo.access$600(this.mAnimationInfo) == null ? true : AnimationInfo.access$600(this.mAnimationInfo).booleanValue();
        return v0;
    }

    public boolean getAllowReturnTransitionOverlap() {
        boolean v0 = this.mAnimationInfo == null || AnimationInfo.access$700(this.mAnimationInfo) == null ? true : AnimationInfo.access$700(this.mAnimationInfo).booleanValue();
        return v0;
    }

    View getAnimatingAway() {
        if(this.mAnimationInfo == null) {
            return null;
        }

        return this.mAnimationInfo.mAnimatingAway;
    }

    Animator getAnimator() {
        if(this.mAnimationInfo == null) {
            return null;
        }

        return this.mAnimationInfo.mAnimator;
    }

    public final Bundle getArguments() {
        return this.mArguments;
    }

    public final FragmentManager getChildFragmentManager() {
        if(this.mChildFragmentManager == null) {
            this.instantiateChildFragmentManager();
            if(this.mState >= 5) {
                this.mChildFragmentManager.dispatchResume();
            }
            else if(this.mState >= 4) {
                this.mChildFragmentManager.dispatchStart();
            }
            else if(this.mState >= 2) {
                this.mChildFragmentManager.dispatchActivityCreated();
            }
            else if(this.mState >= 1) {
                this.mChildFragmentManager.dispatchCreate();
            }
        }

        return this.mChildFragmentManager;
    }

    public Context getContext() {
        Context v0 = this.mHost == null ? null : this.mHost.getContext();
        return v0;
    }

    public Object getEnterTransition() {
        if(this.mAnimationInfo == null) {
            return null;
        }

        return AnimationInfo.access$000(this.mAnimationInfo);
    }

    SharedElementCallback getEnterTransitionCallback() {
        if(this.mAnimationInfo == null) {
            return null;
        }

        return this.mAnimationInfo.mEnterTransitionCallback;
    }

    public Object getExitTransition() {
        if(this.mAnimationInfo == null) {
            return null;
        }

        return AnimationInfo.access$200(this.mAnimationInfo);
    }

    SharedElementCallback getExitTransitionCallback() {
        if(this.mAnimationInfo == null) {
            return null;
        }

        return this.mAnimationInfo.mExitTransitionCallback;
    }

    public final FragmentManager getFragmentManager() {
        return this.mFragmentManager;
    }

    public final Object getHost() {
        Object v0 = this.mHost == null ? null : this.mHost.onGetHost();
        return v0;
    }

    public final int getId() {
        return this.mFragmentId;
    }

    public final LayoutInflater getLayoutInflater() {
        if(this.mLayoutInflater == null) {
            return this.performGetLayoutInflater(null);
        }

        return this.mLayoutInflater;
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) @Deprecated public LayoutInflater getLayoutInflater(Bundle arg2) {
        if(this.mHost == null) {
            throw new IllegalStateException("onGetLayoutInflater() cannot be executed until the Fragment is attached to the FragmentManager.");
        }

        LayoutInflater v2 = this.mHost.onGetLayoutInflater();
        this.getChildFragmentManager();
        LayoutInflaterCompat.setFactory2(v2, this.mChildFragmentManager.getLayoutInflaterFactory());
        return v2;
    }

    public Lifecycle getLifecycle() {
        return this.mLifecycleRegistry;
    }

    public LoaderManager getLoaderManager() {
        if(this.mLoaderManager != null) {
            return this.mLoaderManager;
        }

        if(this.mHost == null) {
            StringBuilder v1 = new StringBuilder();
            v1.append("Fragment ");
            v1.append(this);
            v1.append(" not attached to Activity");
            throw new IllegalStateException(v1.toString());
        }

        this.mCheckedForLoaderManager = true;
        this.mLoaderManager = this.mHost.getLoaderManager(this.mWho, this.mLoadersStarted, true);
        return this.mLoaderManager;
    }

    int getNextAnim() {
        if(this.mAnimationInfo == null) {
            return 0;
        }

        return this.mAnimationInfo.mNextAnim;
    }

    int getNextTransition() {
        if(this.mAnimationInfo == null) {
            return 0;
        }

        return this.mAnimationInfo.mNextTransition;
    }

    int getNextTransitionStyle() {
        if(this.mAnimationInfo == null) {
            return 0;
        }

        return this.mAnimationInfo.mNextTransitionStyle;
    }

    public final Fragment getParentFragment() {
        return this.mParentFragment;
    }

    public Object getReenterTransition() {
        if(this.mAnimationInfo == null) {
            return null;
        }

        Object v0 = AnimationInfo.access$300(this.mAnimationInfo) == Fragment.USE_DEFAULT_TRANSITION ? this.getExitTransition() : AnimationInfo.access$300(this.mAnimationInfo);
        return v0;
    }

    public final Resources getResources() {
        if(this.mHost == null) {
            StringBuilder v1 = new StringBuilder();
            v1.append("Fragment ");
            v1.append(this);
            v1.append(" not attached to Activity");
            throw new IllegalStateException(v1.toString());
        }

        return this.mHost.getContext().getResources();
    }

    public final boolean getRetainInstance() {
        return this.mRetainInstance;
    }

    public Object getReturnTransition() {
        if(this.mAnimationInfo == null) {
            return null;
        }

        Object v0 = AnimationInfo.access$100(this.mAnimationInfo) == Fragment.USE_DEFAULT_TRANSITION ? this.getEnterTransition() : AnimationInfo.access$100(this.mAnimationInfo);
        return v0;
    }

    public Object getSharedElementEnterTransition() {
        if(this.mAnimationInfo == null) {
            return null;
        }

        return AnimationInfo.access$400(this.mAnimationInfo);
    }

    public Object getSharedElementReturnTransition() {
        if(this.mAnimationInfo == null) {
            return null;
        }

        Object v0 = AnimationInfo.access$500(this.mAnimationInfo) == Fragment.USE_DEFAULT_TRANSITION ? this.getSharedElementEnterTransition() : AnimationInfo.access$500(this.mAnimationInfo);
        return v0;
    }

    int getStateAfterAnimating() {
        if(this.mAnimationInfo == null) {
            return 0;
        }

        return this.mAnimationInfo.mStateAfterAnimating;
    }

    public final String getString(@StringRes int arg2) {
        return this.getResources().getString(arg2);
    }

    public final String getString(@StringRes int arg2, Object[] arg3) {
        return this.getResources().getString(arg2, arg3);
    }

    public final String getTag() {
        return this.mTag;
    }

    public final Fragment getTargetFragment() {
        return this.mTarget;
    }

    public final int getTargetRequestCode() {
        return this.mTargetRequestCode;
    }

    public final CharSequence getText(@StringRes int arg2) {
        return this.getResources().getText(arg2);
    }

    public boolean getUserVisibleHint() {
        return this.mUserVisibleHint;
    }

    @Nullable public View getView() {
        return this.mView;
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public final boolean hasOptionsMenu() {
        return this.mHasMenu;
    }

    public final int hashCode() {
        return super.hashCode();
    }

    void initState() {
        this.mIndex = -1;
        this.mWho = null;
        this.mAdded = false;
        this.mRemoving = false;
        this.mFromLayout = false;
        this.mInLayout = false;
        this.mRestored = false;
        this.mBackStackNesting = 0;
        this.mFragmentManager = null;
        this.mChildFragmentManager = null;
        this.mHost = null;
        this.mFragmentId = 0;
        this.mContainerId = 0;
        this.mTag = null;
        this.mHidden = false;
        this.mDetached = false;
        this.mRetaining = false;
        this.mLoaderManager = null;
        this.mLoadersStarted = false;
        this.mCheckedForLoaderManager = false;
    }

    public static Fragment instantiate(Context arg2, String arg3, @Nullable Bundle arg4) {
        StringBuilder v0_2;
        Object v2_5;
        try {
            Object v0 = Fragment.sClassMap.get(arg3);
            if(v0 == null) {
                Class v0_1 = arg2.getClassLoader().loadClass(arg3);
                Fragment.sClassMap.put(arg3, v0_1);
            }

            v2_5 = ((Class)v0).getConstructor().newInstance();
            if(arg4 != null) {
                arg4.setClassLoader(v2_5.getClass().getClassLoader());
                ((Fragment)v2_5).setArguments(arg4);
            }
        }
        catch(InvocationTargetException v2) {
            v0_2 = new StringBuilder();
            v0_2.append("Unable to instantiate fragment ");
            v0_2.append(arg3);
            v0_2.append(": calling Fragment constructor caused an exception");
            throw new InstantiationException(v0_2.toString(), ((Exception)v2));
        }
        catch(NoSuchMethodException v2_1) {
            v0_2 = new StringBuilder();
            v0_2.append("Unable to instantiate fragment ");
            v0_2.append(arg3);
            v0_2.append(": could not find Fragment constructor");
            throw new InstantiationException(v0_2.toString(), ((Exception)v2_1));
        }
        catch(IllegalAccessException v2_2) {
            v0_2 = new StringBuilder();
            v0_2.append("Unable to instantiate fragment ");
            v0_2.append(arg3);
            v0_2.append(": make sure class name exists, is public, and has an");
            v0_2.append(" empty constructor that is public");
            throw new InstantiationException(v0_2.toString(), ((Exception)v2_2));
        }
        catch(java.lang.InstantiationException v2_3) {
            v0_2 = new StringBuilder();
            v0_2.append("Unable to instantiate fragment ");
            v0_2.append(arg3);
            v0_2.append(": make sure class name exists, is public, and has an");
            v0_2.append(" empty constructor that is public");
            throw new InstantiationException(v0_2.toString(), ((Exception)v2_3));
        }
        catch(ClassNotFoundException v2_4) {
            v0_2 = new StringBuilder();
            v0_2.append("Unable to instantiate fragment ");
            v0_2.append(arg3);
            v0_2.append(": make sure class name exists, is public, and has an");
            v0_2.append(" empty constructor that is public");
            throw new InstantiationException(v0_2.toString(), ((Exception)v2_4));
        }

        return ((Fragment)v2_5);
    }

    public static Fragment instantiate(Context arg1, String arg2) {
        return Fragment.instantiate(arg1, arg2, null);
    }

    void instantiateChildFragmentManager() {
        if(this.mHost == null) {
            throw new IllegalStateException("Fragment has not been attached yet.");
        }

        this.mChildFragmentManager = new FragmentManagerImpl();
        this.mChildFragmentManager.attachController(this.mHost, new FragmentContainer() {
            public Fragment instantiate(Context arg2, String arg3, Bundle arg4) {
                return Fragment.this.mHost.instantiate(arg2, arg3, arg4);
            }

            @Nullable public View onFindViewById(int arg2) {
                if(Fragment.this.mView == null) {
                    throw new IllegalStateException("Fragment does not have a view");
                }

                return Fragment.this.mView.findViewById(arg2);
            }

            public boolean onHasView() {
                boolean v0 = Fragment.this.mView != null ? true : false;
                return v0;
            }
        }, this);
    }

    public final boolean isAdded() {
        boolean v0 = this.mHost == null || !this.mAdded ? false : true;
        return v0;
    }

    public final boolean isDetached() {
        return this.mDetached;
    }

    public final boolean isHidden() {
        return this.mHidden;
    }

    boolean isHideReplaced() {
        if(this.mAnimationInfo == null) {
            return 0;
        }

        return this.mAnimationInfo.mIsHideReplaced;
    }

    final boolean isInBackStack() {
        boolean v0 = this.mBackStackNesting > 0 ? true : false;
        return v0;
    }

    public final boolean isInLayout() {
        return this.mInLayout;
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public final boolean isMenuVisible() {
        return this.mMenuVisible;
    }

    boolean isPostponed() {
        if(this.mAnimationInfo == null) {
            return 0;
        }

        return this.mAnimationInfo.mEnterTransitionPostponed;
    }

    public final boolean isRemoving() {
        return this.mRemoving;
    }

    public final boolean isResumed() {
        boolean v0 = this.mState >= 5 ? true : false;
        return v0;
    }

    public final boolean isStateSaved() {
        if(this.mFragmentManager == null) {
            return 0;
        }

        return this.mFragmentManager.isStateSaved();
    }

    static boolean isSupportFragmentClass(Context arg1, String arg2) {
        Class v0_1;
        try {
            Object v0 = Fragment.sClassMap.get(arg2);
            if(v0 == null) {
                v0_1 = arg1.getClassLoader().loadClass(arg2);
                Fragment.sClassMap.put(arg2, v0_1);
            }

            return Fragment.class.isAssignableFrom(v0_1);
        }
        catch(ClassNotFoundException ) {
            return 0;
        }
    }

    public final boolean isVisible() {
        boolean v0 = !this.isAdded() || (this.isHidden()) || this.mView == null || this.mView.getWindowToken() == null || this.mView.getVisibility() != 0 ? false : true;
        return v0;
    }

    void noteStateNotSaved() {
        if(this.mChildFragmentManager != null) {
            this.mChildFragmentManager.noteStateNotSaved();
        }
    }

    @CallSuper public void onActivityCreated(@Nullable Bundle arg1) {
        this.mCalled = true;
    }

    public void onActivityResult(int arg1, int arg2, Intent arg3) {
    }

    @CallSuper @Deprecated public void onAttach(Activity arg1) {
        this.mCalled = true;
    }

    @CallSuper public void onAttach(Context arg2) {
        this.mCalled = true;
        Activity v2 = this.mHost == null ? null : this.mHost.getActivity();
        if(v2 != null) {
            this.mCalled = false;
            this.onAttach(v2);
        }
    }

    public void onAttachFragment(Fragment arg1) {
    }

    @CallSuper public void onConfigurationChanged(Configuration arg1) {
        this.mCalled = true;
    }

    public boolean onContextItemSelected(MenuItem arg1) {
        return 0;
    }

    @CallSuper public void onCreate(@Nullable Bundle arg2) {
        this.mCalled = true;
        this.restoreChildFragmentState(arg2);
        if(this.mChildFragmentManager != null && !this.mChildFragmentManager.isStateAtLeast(1)) {
            this.mChildFragmentManager.dispatchCreate();
        }
    }

    public Animation onCreateAnimation(int arg1, boolean arg2, int arg3) {
        return null;
    }

    public Animator onCreateAnimator(int arg1, boolean arg2, int arg3) {
        return null;
    }

    public void onCreateContextMenu(ContextMenu arg2, View arg3, ContextMenu$ContextMenuInfo arg4) {
        this.getActivity().onCreateContextMenu(arg2, arg3, arg4);
    }

    public void onCreateOptionsMenu(Menu arg1, MenuInflater arg2) {
    }

    @Nullable public View onCreateView(LayoutInflater arg1, @Nullable ViewGroup arg2, @Nullable Bundle arg3) {
        return null;
    }

    @CallSuper public void onDestroy() {
        this.mCalled = true;
        if(!this.mCheckedForLoaderManager) {
            this.mCheckedForLoaderManager = true;
            this.mLoaderManager = this.mHost.getLoaderManager(this.mWho, this.mLoadersStarted, false);
        }

        if(this.mLoaderManager != null) {
            this.mLoaderManager.doDestroy();
        }
    }

    public void onDestroyOptionsMenu() {
    }

    @CallSuper public void onDestroyView() {
        this.mCalled = true;
    }

    @CallSuper public void onDetach() {
        this.mCalled = true;
    }

    public LayoutInflater onGetLayoutInflater(Bundle arg1) {
        return this.getLayoutInflater(arg1);
    }

    public void onHiddenChanged(boolean arg1) {
    }

    @CallSuper @Deprecated public void onInflate(Activity arg1, AttributeSet arg2, Bundle arg3) {
        this.mCalled = true;
    }

    @CallSuper public void onInflate(Context arg2, AttributeSet arg3, Bundle arg4) {
        this.mCalled = true;
        Activity v2 = this.mHost == null ? null : this.mHost.getActivity();
        if(v2 != null) {
            this.mCalled = false;
            this.onInflate(v2, arg3, arg4);
        }
    }

    @CallSuper public void onLowMemory() {
        this.mCalled = true;
    }

    public void onMultiWindowModeChanged(boolean arg1) {
    }

    public boolean onOptionsItemSelected(MenuItem arg1) {
        return 0;
    }

    public void onOptionsMenuClosed(Menu arg1) {
    }

    @CallSuper public void onPause() {
        this.mCalled = true;
    }

    public void onPictureInPictureModeChanged(boolean arg1) {
    }

    public void onPrepareOptionsMenu(Menu arg1) {
    }

    public void onRequestPermissionsResult(int arg1, @NonNull String[] arg2, @NonNull int[] arg3) {
    }

    @CallSuper public void onResume() {
        this.mCalled = true;
    }

    public void onSaveInstanceState(Bundle arg1) {
    }

    @CallSuper public void onStart() {
        this.mCalled = true;
        if(!this.mLoadersStarted) {
            this.mLoadersStarted = true;
            if(!this.mCheckedForLoaderManager) {
                this.mCheckedForLoaderManager = true;
                this.mLoaderManager = this.mHost.getLoaderManager(this.mWho, this.mLoadersStarted, false);
            }
            else if(this.mLoaderManager != null) {
                this.mLoaderManager.doStart();
            }
        }
    }

    @CallSuper public void onStop() {
        this.mCalled = true;
    }

    public void onViewCreated(View arg1, @Nullable Bundle arg2) {
    }

    @CallSuper public void onViewStateRestored(@Nullable Bundle arg1) {
        this.mCalled = true;
    }

    FragmentManager peekChildFragmentManager() {
        return this.mChildFragmentManager;
    }

    void performActivityCreated(Bundle arg3) {
        if(this.mChildFragmentManager != null) {
            this.mChildFragmentManager.noteStateNotSaved();
        }

        this.mState = 2;
        this.mCalled = false;
        this.onActivityCreated(arg3);
        if(!this.mCalled) {
            StringBuilder v0 = new StringBuilder();
            v0.append("Fragment ");
            v0.append(this);
            v0.append(" did not call through to super.onActivityCreated()");
            throw new SuperNotCalledException(v0.toString());
        }

        if(this.mChildFragmentManager != null) {
            this.mChildFragmentManager.dispatchActivityCreated();
        }
    }

    void performConfigurationChanged(Configuration arg2) {
        this.onConfigurationChanged(arg2);
        if(this.mChildFragmentManager != null) {
            this.mChildFragmentManager.dispatchConfigurationChanged(arg2);
        }
    }

    boolean performContextItemSelected(MenuItem arg3) {
        if(!this.mHidden) {
            if(this.onContextItemSelected(arg3)) {
                return 1;
            }
            else if(this.mChildFragmentManager != null && (this.mChildFragmentManager.dispatchContextItemSelected(arg3))) {
                return 1;
            }
        }

        return 0;
    }

    void performCreate(Bundle arg3) {
        if(this.mChildFragmentManager != null) {
            this.mChildFragmentManager.noteStateNotSaved();
        }

        this.mState = 1;
        this.mCalled = false;
        this.onCreate(arg3);
        this.mIsCreated = true;
        if(!this.mCalled) {
            StringBuilder v0 = new StringBuilder();
            v0.append("Fragment ");
            v0.append(this);
            v0.append(" did not call through to super.onCreate()");
            throw new SuperNotCalledException(v0.toString());
        }

        this.mLifecycleRegistry.handleLifecycleEvent(Event.ON_CREATE);
    }

    boolean performCreateOptionsMenu(Menu arg3, MenuInflater arg4) {
        int v1 = 0;
        if(!this.mHidden) {
            if((this.mHasMenu) && (this.mMenuVisible)) {
                v1 = 1;
                this.onCreateOptionsMenu(arg3, arg4);
            }

            if(this.mChildFragmentManager == null) {
                goto label_14;
            }

            v1 |= this.mChildFragmentManager.dispatchCreateOptionsMenu(arg3, arg4);
        }

    label_14:
        return ((boolean)v1);
    }

    View performCreateView(LayoutInflater arg2, ViewGroup arg3, Bundle arg4) {
        if(this.mChildFragmentManager != null) {
            this.mChildFragmentManager.noteStateNotSaved();
        }

        this.mPerformedCreateView = true;
        return this.onCreateView(arg2, arg3, arg4);
    }

    void performDestroy() {
        this.mLifecycleRegistry.handleLifecycleEvent(Event.ON_DESTROY);
        if(this.mChildFragmentManager != null) {
            this.mChildFragmentManager.dispatchDestroy();
        }

        this.mState = 0;
        this.mCalled = false;
        this.mIsCreated = false;
        this.onDestroy();
        if(!this.mCalled) {
            StringBuilder v1 = new StringBuilder();
            v1.append("Fragment ");
            v1.append(this);
            v1.append(" did not call through to super.onDestroy()");
            throw new SuperNotCalledException(v1.toString());
        }

        this.mChildFragmentManager = null;
    }

    void performDestroyView() {
        if(this.mChildFragmentManager != null) {
            this.mChildFragmentManager.dispatchDestroyView();
        }

        this.mState = 1;
        this.mCalled = false;
        this.onDestroyView();
        if(!this.mCalled) {
            StringBuilder v1 = new StringBuilder();
            v1.append("Fragment ");
            v1.append(this);
            v1.append(" did not call through to super.onDestroyView()");
            throw new SuperNotCalledException(v1.toString());
        }

        if(this.mLoaderManager != null) {
            this.mLoaderManager.doReportNextStart();
        }

        this.mPerformedCreateView = false;
    }

    void performDetach() {
        StringBuilder v1;
        this.mCalled = false;
        this.onDetach();
        LayoutInflater v0 = null;
        this.mLayoutInflater = v0;
        if(!this.mCalled) {
            v1 = new StringBuilder();
            v1.append("Fragment ");
            v1.append(this);
            v1.append(" did not call through to super.onDetach()");
            throw new SuperNotCalledException(v1.toString());
        }

        if(this.mChildFragmentManager != null) {
            if(!this.mRetaining) {
                v1 = new StringBuilder();
                v1.append("Child FragmentManager of ");
                v1.append(this);
                v1.append(" was not ");
                v1.append(" destroyed and this fragment is not retaining instance");
                throw new IllegalStateException(v1.toString());
            }
            else {
                this.mChildFragmentManager.dispatchDestroy();
                this.mChildFragmentManager = ((FragmentManagerImpl)v0);
            }
        }
    }

    LayoutInflater performGetLayoutInflater(Bundle arg1) {
        this.mLayoutInflater = this.onGetLayoutInflater(arg1);
        return this.mLayoutInflater;
    }

    void performLowMemory() {
        this.onLowMemory();
        if(this.mChildFragmentManager != null) {
            this.mChildFragmentManager.dispatchLowMemory();
        }
    }

    void performMultiWindowModeChanged(boolean arg2) {
        this.onMultiWindowModeChanged(arg2);
        if(this.mChildFragmentManager != null) {
            this.mChildFragmentManager.dispatchMultiWindowModeChanged(arg2);
        }
    }

    boolean performOptionsItemSelected(MenuItem arg3) {
        if(!this.mHidden) {
            if((this.mHasMenu) && (this.mMenuVisible) && (this.onOptionsItemSelected(arg3))) {
                return 1;
            }

            if(this.mChildFragmentManager == null) {
                return 0;
            }

            if(!this.mChildFragmentManager.dispatchOptionsItemSelected(arg3)) {
                return 0;
            }

            return 1;
        }

        return 0;
    }

    void performOptionsMenuClosed(Menu arg2) {
        if(!this.mHidden) {
            if((this.mHasMenu) && (this.mMenuVisible)) {
                this.onOptionsMenuClosed(arg2);
            }

            if(this.mChildFragmentManager == null) {
                return;
            }

            this.mChildFragmentManager.dispatchOptionsMenuClosed(arg2);
        }
    }

    void performPause() {
        this.mLifecycleRegistry.handleLifecycleEvent(Event.ON_PAUSE);
        if(this.mChildFragmentManager != null) {
            this.mChildFragmentManager.dispatchPause();
        }

        this.mState = 4;
        this.mCalled = false;
        this.onPause();
        if(!this.mCalled) {
            StringBuilder v1 = new StringBuilder();
            v1.append("Fragment ");
            v1.append(this);
            v1.append(" did not call through to super.onPause()");
            throw new SuperNotCalledException(v1.toString());
        }
    }

    void performPictureInPictureModeChanged(boolean arg2) {
        this.onPictureInPictureModeChanged(arg2);
        if(this.mChildFragmentManager != null) {
            this.mChildFragmentManager.dispatchPictureInPictureModeChanged(arg2);
        }
    }

    boolean performPrepareOptionsMenu(Menu arg3) {
        int v1 = 0;
        if(!this.mHidden) {
            if((this.mHasMenu) && (this.mMenuVisible)) {
                v1 = 1;
                this.onPrepareOptionsMenu(arg3);
            }

            if(this.mChildFragmentManager == null) {
                goto label_14;
            }

            v1 |= this.mChildFragmentManager.dispatchPrepareOptionsMenu(arg3);
        }

    label_14:
        return ((boolean)v1);
    }

    void performReallyStop() {
        if(this.mChildFragmentManager != null) {
            this.mChildFragmentManager.dispatchReallyStop();
        }

        this.mState = 2;
        if(this.mLoadersStarted) {
            this.mLoadersStarted = false;
            if(!this.mCheckedForLoaderManager) {
                this.mCheckedForLoaderManager = true;
                this.mLoaderManager = this.mHost.getLoaderManager(this.mWho, this.mLoadersStarted, false);
            }

            if(this.mLoaderManager == null) {
                return;
            }

            if(this.mHost.getRetainLoaders()) {
                this.mLoaderManager.doRetain();
                return;
            }

            this.mLoaderManager.doStop();
        }
    }

    void performResume() {
        if(this.mChildFragmentManager != null) {
            this.mChildFragmentManager.noteStateNotSaved();
            this.mChildFragmentManager.execPendingActions();
        }

        this.mState = 5;
        this.mCalled = false;
        this.onResume();
        if(!this.mCalled) {
            StringBuilder v1 = new StringBuilder();
            v1.append("Fragment ");
            v1.append(this);
            v1.append(" did not call through to super.onResume()");
            throw new SuperNotCalledException(v1.toString());
        }

        if(this.mChildFragmentManager != null) {
            this.mChildFragmentManager.dispatchResume();
            this.mChildFragmentManager.execPendingActions();
        }

        this.mLifecycleRegistry.handleLifecycleEvent(Event.ON_RESUME);
    }

    void performSaveInstanceState(Bundle arg3) {
        this.onSaveInstanceState(arg3);
        if(this.mChildFragmentManager != null) {
            Parcelable v0 = this.mChildFragmentManager.saveAllState();
            if(v0 != null) {
                arg3.putParcelable("android:support:fragments", v0);
            }
        }
    }

    void performStart() {
        if(this.mChildFragmentManager != null) {
            this.mChildFragmentManager.noteStateNotSaved();
            this.mChildFragmentManager.execPendingActions();
        }

        this.mState = 4;
        this.mCalled = false;
        this.onStart();
        if(!this.mCalled) {
            StringBuilder v1 = new StringBuilder();
            v1.append("Fragment ");
            v1.append(this);
            v1.append(" did not call through to super.onStart()");
            throw new SuperNotCalledException(v1.toString());
        }

        if(this.mChildFragmentManager != null) {
            this.mChildFragmentManager.dispatchStart();
        }

        if(this.mLoaderManager != null) {
            this.mLoaderManager.doReportStart();
        }

        this.mLifecycleRegistry.handleLifecycleEvent(Event.ON_START);
    }

    void performStop() {
        this.mLifecycleRegistry.handleLifecycleEvent(Event.ON_STOP);
        if(this.mChildFragmentManager != null) {
            this.mChildFragmentManager.dispatchStop();
        }

        this.mState = 3;
        this.mCalled = false;
        this.onStop();
        if(!this.mCalled) {
            StringBuilder v1 = new StringBuilder();
            v1.append("Fragment ");
            v1.append(this);
            v1.append(" did not call through to super.onStop()");
            throw new SuperNotCalledException(v1.toString());
        }
    }

    public void postponeEnterTransition() {
        this.ensureAnimationInfo().mEnterTransitionPostponed = true;
    }

    public void registerForContextMenu(View arg1) {
        arg1.setOnCreateContextMenuListener(((View$OnCreateContextMenuListener)this));
    }

    public final void requestPermissions(@NonNull String[] arg2, int arg3) {
        if(this.mHost == null) {
            StringBuilder v3 = new StringBuilder();
            v3.append("Fragment ");
            v3.append(this);
            v3.append(" not attached to Activity");
            throw new IllegalStateException(v3.toString());
        }

        this.mHost.onRequestPermissionsFromFragment(this, arg2, arg3);
    }

    void restoreChildFragmentState(@Nullable Bundle arg3) {
        if(arg3 != null) {
            Parcelable v3 = arg3.getParcelable("android:support:fragments");
            if(v3 != null) {
                if(this.mChildFragmentManager == null) {
                    this.instantiateChildFragmentManager();
                }

                this.mChildFragmentManager.restoreAllState(v3, this.mChildNonConfig);
                this.mChildNonConfig = null;
                this.mChildFragmentManager.dispatchCreate();
            }
        }
    }

    final void restoreViewState(Bundle arg3) {
        if(this.mSavedViewState != null) {
            this.mInnerView.restoreHierarchyState(this.mSavedViewState);
            this.mSavedViewState = null;
        }

        this.mCalled = false;
        this.onViewStateRestored(arg3);
        if(!this.mCalled) {
            StringBuilder v0 = new StringBuilder();
            v0.append("Fragment ");
            v0.append(this);
            v0.append(" did not call through to super.onViewStateRestored()");
            throw new SuperNotCalledException(v0.toString());
        }
    }

    public void setAllowEnterTransitionOverlap(boolean arg2) {
        AnimationInfo.access$602(this.ensureAnimationInfo(), Boolean.valueOf(arg2));
    }

    public void setAllowReturnTransitionOverlap(boolean arg2) {
        AnimationInfo.access$702(this.ensureAnimationInfo(), Boolean.valueOf(arg2));
    }

    void setAnimatingAway(View arg2) {
        this.ensureAnimationInfo().mAnimatingAway = arg2;
    }

    void setAnimator(Animator arg2) {
        this.ensureAnimationInfo().mAnimator = arg2;
    }

    public void setArguments(Bundle arg2) {
        if(this.mIndex >= 0 && (this.isStateSaved())) {
            throw new IllegalStateException("Fragment already active and state has been saved");
        }

        this.mArguments = arg2;
    }

    public void setEnterSharedElementCallback(SharedElementCallback arg2) {
        this.ensureAnimationInfo().mEnterTransitionCallback = arg2;
    }

    public void setEnterTransition(Object arg2) {
        AnimationInfo.access$002(this.ensureAnimationInfo(), arg2);
    }

    public void setExitSharedElementCallback(SharedElementCallback arg2) {
        this.ensureAnimationInfo().mExitTransitionCallback = arg2;
    }

    public void setExitTransition(Object arg2) {
        AnimationInfo.access$202(this.ensureAnimationInfo(), arg2);
    }

    public void setHasOptionsMenu(boolean arg2) {
        if(this.mHasMenu != arg2) {
            this.mHasMenu = arg2;
            if((this.isAdded()) && !this.isHidden()) {
                this.mHost.onSupportInvalidateOptionsMenu();
            }
        }
    }

    void setHideReplaced(boolean arg2) {
        this.ensureAnimationInfo().mIsHideReplaced = arg2;
    }

    final void setIndex(int arg1, Fragment arg2) {
        this.mIndex = arg1;
        this.mWho = arg2 != null ? arg2.mWho + ":" + this.mIndex : "android:fragment:" + this.mIndex;
    }

    public void setInitialSavedState(SavedState arg2) {
        if(this.mIndex >= 0) {
            throw new IllegalStateException("Fragment already active");
        }

        Bundle v2 = arg2 == null || arg2.mState == null ? null : arg2.mState;
        this.mSavedFragmentState = v2;
    }

    public void setMenuVisibility(boolean arg2) {
        if(this.mMenuVisible != arg2) {
            this.mMenuVisible = arg2;
            if((this.mHasMenu) && (this.isAdded()) && !this.isHidden()) {
                this.mHost.onSupportInvalidateOptionsMenu();
            }
        }
    }

    void setNextAnim(int arg2) {
        if(this.mAnimationInfo == null && arg2 == 0) {
            return;
        }

        this.ensureAnimationInfo().mNextAnim = arg2;
    }

    void setNextTransition(int arg2, int arg3) {
        if(this.mAnimationInfo == null && arg2 == 0 && arg3 == 0) {
            return;
        }

        this.ensureAnimationInfo();
        this.mAnimationInfo.mNextTransition = arg2;
        this.mAnimationInfo.mNextTransitionStyle = arg3;
    }

    void setOnStartEnterTransitionListener(OnStartEnterTransitionListener arg3) {
        this.ensureAnimationInfo();
        if(arg3 == this.mAnimationInfo.mStartEnterTransitionListener) {
            return;
        }

        if(arg3 != null && this.mAnimationInfo.mStartEnterTransitionListener != null) {
            StringBuilder v0 = new StringBuilder();
            v0.append("Trying to set a replacement startPostponedEnterTransition on ");
            v0.append(this);
            throw new IllegalStateException(v0.toString());
        }

        if(this.mAnimationInfo.mEnterTransitionPostponed) {
            this.mAnimationInfo.mStartEnterTransitionListener = arg3;
        }

        if(arg3 != null) {
            arg3.startListening();
        }
    }

    public void setReenterTransition(Object arg2) {
        AnimationInfo.access$302(this.ensureAnimationInfo(), arg2);
    }

    public void setRetainInstance(boolean arg1) {
        this.mRetainInstance = arg1;
    }

    public void setReturnTransition(Object arg2) {
        AnimationInfo.access$102(this.ensureAnimationInfo(), arg2);
    }

    public void setSharedElementEnterTransition(Object arg2) {
        AnimationInfo.access$402(this.ensureAnimationInfo(), arg2);
    }

    public void setSharedElementReturnTransition(Object arg2) {
        AnimationInfo.access$502(this.ensureAnimationInfo(), arg2);
    }

    void setStateAfterAnimating(int arg2) {
        this.ensureAnimationInfo().mStateAfterAnimating = arg2;
    }

    public void setTargetFragment(Fragment arg3, int arg4) {
        StringBuilder v0_1;
        FragmentManager v0 = this.getFragmentManager();
        FragmentManager v1 = arg3 != null ? arg3.getFragmentManager() : null;
        if(v0 != null && v1 != null && v0 != v1) {
            v0_1 = new StringBuilder();
            v0_1.append("Fragment ");
            v0_1.append(arg3);
            v0_1.append(" must share the same FragmentManager to be set as a target fragment");
            throw new IllegalArgumentException(v0_1.toString());
        }

        Fragment v0_2;
        for(v0_2 = arg3; v0_2 != null; v0_2 = v0_2.getTargetFragment()) {
            if(v0_2 == this) {
                v0_1 = new StringBuilder();
                v0_1.append("Setting ");
                v0_1.append(arg3);
                v0_1.append(" as the target of ");
                v0_1.append(this);
                v0_1.append(" would create a target cycle");
                throw new IllegalArgumentException(v0_1.toString());
            }
        }

        this.mTarget = arg3;
        this.mTargetRequestCode = arg4;
    }

    public void setUserVisibleHint(boolean arg3) {
        int v1 = 4;
        if(!this.mUserVisibleHint && (arg3) && this.mState < v1 && this.mFragmentManager != null && (this.isAdded())) {
            this.mFragmentManager.performPendingDeferredStart(this);
        }

        this.mUserVisibleHint = arg3;
        arg3 = this.mState >= v1 || (arg3) ? false : true;
        this.mDeferStart = arg3;
    }

    public boolean shouldShowRequestPermissionRationale(@NonNull String arg2) {
        if(this.mHost != null) {
            return this.mHost.onShouldShowRequestPermissionRationale(arg2);
        }

        return 0;
    }

    public void startActivity(Intent arg2) {
        this.startActivity(arg2, null);
    }

    public void startActivity(Intent arg3, @Nullable Bundle arg4) {
        if(this.mHost == null) {
            StringBuilder v4 = new StringBuilder();
            v4.append("Fragment ");
            v4.append(this);
            v4.append(" not attached to Activity");
            throw new IllegalStateException(v4.toString());
        }

        this.mHost.onStartActivityFromFragment(this, arg3, -1, arg4);
    }

    public void startActivityForResult(Intent arg2, int arg3) {
        this.startActivityForResult(arg2, arg3, null);
    }

    public void startActivityForResult(Intent arg2, int arg3, @Nullable Bundle arg4) {
        if(this.mHost == null) {
            StringBuilder v3 = new StringBuilder();
            v3.append("Fragment ");
            v3.append(this);
            v3.append(" not attached to Activity");
            throw new IllegalStateException(v3.toString());
        }

        this.mHost.onStartActivityFromFragment(this, arg2, arg3, arg4);
    }

    public void startIntentSenderForResult(IntentSender arg11, int arg12, @Nullable Intent arg13, int arg14, int arg15, int arg16, Bundle arg17) throws IntentSender$SendIntentException {
        Fragment v9 = this;
        if(v9.mHost == null) {
            StringBuilder v1 = new StringBuilder();
            v1.append("Fragment ");
            v1.append(v9);
            v1.append(" not attached to Activity");
            throw new IllegalStateException(v1.toString());
        }

        v9.mHost.onStartIntentSenderFromFragment(v9, arg11, arg12, arg13, arg14, arg15, arg16, arg17);
    }

    public void startPostponedEnterTransition() {
        if(this.mFragmentManager == null || this.mFragmentManager.mHost == null) {
            this.ensureAnimationInfo().mEnterTransitionPostponed = false;
        }
        else if(Looper.myLooper() != this.mFragmentManager.mHost.getHandler().getLooper()) {
            this.mFragmentManager.mHost.getHandler().postAtFrontOfQueue(new Runnable() {
                public void run() {
                    Fragment.this.callStartTransitionListener();
                }
            });
        }
        else {
            this.callStartTransitionListener();
        }
    }

    public String toString() {
        StringBuilder v0 = new StringBuilder(0x80);
        DebugUtils.buildShortClassTag(this, v0);
        if(this.mIndex >= 0) {
            v0.append(" #");
            v0.append(this.mIndex);
        }

        if(this.mFragmentId != 0) {
            v0.append(" id=0x");
            v0.append(Integer.toHexString(this.mFragmentId));
        }

        if(this.mTag != null) {
            v0.append(" ");
            v0.append(this.mTag);
        }

        v0.append('}');
        return v0.toString();
    }

    public void unregisterForContextMenu(View arg2) {
        arg2.setOnCreateContextMenuListener(null);
    }
}

