package android.support.v4.app;

import android.animation.Animator$AnimatorListener;
import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources$NotFoundException;
import android.content.res.TypedArray;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.v4.util.ArraySet;
import android.support.v4.util.DebugUtils;
import android.support.v4.util.LogWriter;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater$Factory2;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation$AnimationListener;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

final class FragmentManagerImpl extends FragmentManager implements LayoutInflater$Factory2 {
    class android.support.v4.app.FragmentManagerImpl$1 implements Runnable {
        android.support.v4.app.FragmentManagerImpl$1(FragmentManagerImpl arg1) {
            FragmentManagerImpl.this = arg1;
            super();
        }

        public void run() {
            FragmentManagerImpl.this.execPendingActions();
        }
    }

    class AnimateOnHWLayerIfNeededListener extends AnimationListenerWrapper {
        View mView;

        AnimateOnHWLayerIfNeededListener(View arg2, Animation$AnimationListener arg3) {
            super(arg3, null);
            this.mView = arg2;
        }

        @CallSuper public void onAnimationEnd(Animation arg4) {
            if((ViewCompat.isAttachedToWindow(this.mView)) || Build$VERSION.SDK_INT >= 24) {
                this.mView.post(new Runnable() {
                    public void run() {
                        AnimateOnHWLayerIfNeededListener.this.mView.setLayerType(0, null);
                    }
                });
            }
            else {
                this.mView.setLayerType(0, null);
            }

            super.onAnimationEnd(arg4);
        }
    }

    class AnimationListenerWrapper implements Animation$AnimationListener {
        private final Animation$AnimationListener mWrapped;

        AnimationListenerWrapper(Animation$AnimationListener arg1, android.support.v4.app.FragmentManagerImpl$1 arg2) {
            this(arg1);
        }

        private AnimationListenerWrapper(Animation$AnimationListener arg1) {
            super();
            this.mWrapped = arg1;
        }

        @CallSuper public void onAnimationEnd(Animation arg2) {
            if(this.mWrapped != null) {
                this.mWrapped.onAnimationEnd(arg2);
            }
        }

        @CallSuper public void onAnimationRepeat(Animation arg2) {
            if(this.mWrapped != null) {
                this.mWrapped.onAnimationRepeat(arg2);
            }
        }

        @CallSuper public void onAnimationStart(Animation arg2) {
            if(this.mWrapped != null) {
                this.mWrapped.onAnimationStart(arg2);
            }
        }
    }

    class AnimationOrAnimator {
        public final Animation animation;
        public final Animator animator;

        AnimationOrAnimator(Animation arg1, android.support.v4.app.FragmentManagerImpl$1 arg2) {
            this(arg1);
        }

        AnimationOrAnimator(Animator arg1, android.support.v4.app.FragmentManagerImpl$1 arg2) {
            this(arg1);
        }

        private AnimationOrAnimator(Animator arg2) {
            super();
            this.animation = null;
            this.animator = arg2;
            if(arg2 == null) {
                throw new IllegalStateException("Animator cannot be null");
            }
        }

        private AnimationOrAnimator(Animation arg2) {
            super();
            this.animation = arg2;
            this.animator = null;
            if(arg2 == null) {
                throw new IllegalStateException("Animation cannot be null");
            }
        }
    }

    class AnimatorOnHWLayerIfNeededListener extends AnimatorListenerAdapter {
        View mView;

        AnimatorOnHWLayerIfNeededListener(View arg1) {
            super();
            this.mView = arg1;
        }

        public void onAnimationEnd(Animator arg4) {
            this.mView.setLayerType(0, null);
            arg4.removeListener(((Animator$AnimatorListener)this));
        }

        public void onAnimationStart(Animator arg3) {
            this.mView.setLayerType(2, null);
        }
    }

    class FragmentTag {
        public static final int[] Fragment = null;
        public static final int Fragment_id = 1;
        public static final int Fragment_name = 0;
        public static final int Fragment_tag = 2;

        static {
            FragmentTag.Fragment = new int[]{0x1010003, 0x10100D0, 0x10100D1};
        }

        FragmentTag() {
            super();
        }
    }

    interface OpGenerator {
        boolean generateOps(ArrayList arg1, ArrayList arg2);
    }

    class PopBackStackState implements OpGenerator {
        final int mFlags;
        final int mId;
        final String mName;

        PopBackStackState(FragmentManagerImpl arg1, String arg2, int arg3, int arg4) {
            FragmentManagerImpl.this = arg1;
            super();
            this.mName = arg2;
            this.mId = arg3;
            this.mFlags = arg4;
        }

        public boolean generateOps(ArrayList arg7, ArrayList arg8) {
            if(FragmentManagerImpl.this.mPrimaryNav != null && this.mId < 0 && this.mName == null) {
                FragmentManager v0 = FragmentManagerImpl.this.mPrimaryNav.peekChildFragmentManager();
                if(v0 != null && (v0.popBackStackImmediate())) {
                    return 0;
                }
            }

            return FragmentManagerImpl.this.popBackStackState(arg7, arg8, this.mName, this.mId, this.mFlags);
        }
    }

    class StartEnterTransitionListener implements OnStartEnterTransitionListener {
        private final boolean mIsBack;
        private int mNumPostponed;
        private final BackStackRecord mRecord;

        StartEnterTransitionListener(BackStackRecord arg1, boolean arg2) {
            super();
            this.mIsBack = arg2;
            this.mRecord = arg1;
        }

        static boolean access$300(StartEnterTransitionListener arg0) {
            return arg0.mIsBack;
        }

        static BackStackRecord access$400(StartEnterTransitionListener arg0) {
            return arg0.mRecord;
        }

        public void cancelTransaction() {
            this.mRecord.mManager.completeExecute(this.mRecord, this.mIsBack, false, false);
        }

        public void completeTransaction() {
            int v1 = 0;
            int v0 = this.mNumPostponed > 0 ? 1 : 0;
            FragmentManagerImpl v3 = this.mRecord.mManager;
            int v4 = v3.mAdded.size();
            while(v1 < v4) {
                Object v5 = v3.mAdded.get(v1);
                ((Fragment)v5).setOnStartEnterTransitionListener(null);
                if(v0 != 0 && (((Fragment)v5).isPostponed())) {
                    ((Fragment)v5).startPostponedEnterTransition();
                }

                ++v1;
            }

            this.mRecord.mManager.completeExecute(this.mRecord, this.mIsBack, v0 ^ 1, true);
        }

        public boolean isReady() {
            boolean v0 = this.mNumPostponed == 0 ? true : false;
            return v0;
        }

        public void onStartEnterTransition() {
            --this.mNumPostponed;
            if(this.mNumPostponed != 0) {
                return;
            }

            this.mRecord.mManager.scheduleCommit();
        }

        public void startListening() {
            ++this.mNumPostponed;
        }
    }

    static final Interpolator ACCELERATE_CUBIC = null;
    static final Interpolator ACCELERATE_QUINT = null;
    static final int ANIM_DUR = 220;
    public static final int ANIM_STYLE_CLOSE_ENTER = 3;
    public static final int ANIM_STYLE_CLOSE_EXIT = 4;
    public static final int ANIM_STYLE_FADE_ENTER = 5;
    public static final int ANIM_STYLE_FADE_EXIT = 6;
    public static final int ANIM_STYLE_OPEN_ENTER = 1;
    public static final int ANIM_STYLE_OPEN_EXIT = 2;
    static boolean DEBUG = false;
    static final Interpolator DECELERATE_CUBIC = null;
    static final Interpolator DECELERATE_QUINT = null;
    static final String TAG = "FragmentManager";
    static final String TARGET_REQUEST_CODE_STATE_TAG = "android:target_req_state";
    static final String TARGET_STATE_TAG = "android:target_state";
    static final String USER_VISIBLE_HINT_TAG = "android:user_visible_hint";
    static final String VIEW_STATE_TAG = "android:view_state";
    SparseArray mActive;
    final ArrayList mAdded;
    ArrayList mAvailBackStackIndices;
    ArrayList mBackStack;
    ArrayList mBackStackChangeListeners;
    ArrayList mBackStackIndices;
    FragmentContainer mContainer;
    ArrayList mCreatedMenus;
    int mCurState;
    boolean mDestroyed;
    Runnable mExecCommit;
    boolean mExecutingActions;
    boolean mHavePendingDeferredStart;
    FragmentHostCallback mHost;
    private final CopyOnWriteArrayList mLifecycleCallbacks;
    boolean mNeedMenuInvalidate;
    int mNextFragmentIndex;
    String mNoTransactionsBecause;
    Fragment mParent;
    ArrayList mPendingActions;
    ArrayList mPostponedTransactions;
    Fragment mPrimaryNav;
    FragmentManagerNonConfig mSavedNonConfig;
    SparseArray mStateArray;
    Bundle mStateBundle;
    boolean mStateSaved;
    ArrayList mTmpAddedFragments;
    ArrayList mTmpIsPop;
    ArrayList mTmpRecords;
    static Field sAnimationListenerField;

    static {
        FragmentManagerImpl.DECELERATE_QUINT = new DecelerateInterpolator(2.5f);
        FragmentManagerImpl.DECELERATE_CUBIC = new DecelerateInterpolator(1.5f);
        FragmentManagerImpl.ACCELERATE_QUINT = new AccelerateInterpolator(2.5f);
        FragmentManagerImpl.ACCELERATE_CUBIC = new AccelerateInterpolator(1.5f);
    }

    FragmentManagerImpl() {
        super();
        this.mNextFragmentIndex = 0;
        this.mAdded = new ArrayList();
        this.mLifecycleCallbacks = new CopyOnWriteArrayList();
        this.mCurState = 0;
        this.mStateBundle = null;
        this.mStateArray = null;
        this.mExecCommit = new android.support.v4.app.FragmentManagerImpl$1(this);
    }

    static void access$500(FragmentManagerImpl arg0) {
        arg0.scheduleCommit();
    }

    static void access$600(FragmentManagerImpl arg0, BackStackRecord arg1, boolean arg2, boolean arg3, boolean arg4) {
        arg0.completeExecute(arg1, arg2, arg3, arg4);
    }

    private void addAddedFragments(ArraySet arg11) {
        if(this.mCurState < 1) {
            return;
        }

        int v0 = Math.min(this.mCurState, 4);
        int v1 = this.mAdded.size();
        int v8;
        for(v8 = 0; v8 < v1; ++v8) {
            Object v9 = this.mAdded.get(v8);
            if(((Fragment)v9).mState < v0) {
                this.moveToState(v9, v0, ((Fragment)v9).getNextAnim(), ((Fragment)v9).getNextTransition(), false);
                if(((Fragment)v9).mView != null && !((Fragment)v9).mHidden && (((Fragment)v9).mIsNewlyAdded)) {
                    arg11.add(v9);
                }
            }
        }
    }

    void addBackStackState(BackStackRecord arg2) {
        if(this.mBackStack == null) {
            this.mBackStack = new ArrayList();
        }

        this.mBackStack.add(arg2);
    }

    public void addFragment(Fragment arg4, boolean arg5) {
        if(FragmentManagerImpl.DEBUG) {
            Log.v("FragmentManager", "add: " + arg4);
        }

        this.makeActive(arg4);
        if(!arg4.mDetached) {
            if(this.mAdded.contains(arg4)) {
                StringBuilder v0 = new StringBuilder();
                v0.append("Fragment already added: ");
                v0.append(arg4);
                throw new IllegalStateException(v0.toString());
            }
            else {
                ArrayList v0_1 = this.mAdded;
                __monitor_enter(v0_1);
                try {
                    this.mAdded.add(arg4);
                    __monitor_exit(v0_1);
                }
                catch(Throwable v4) {
                    try {
                    label_46:
                        __monitor_exit(v0_1);
                    }
                    catch(Throwable v4) {
                        goto label_46;
                    }

                    throw v4;
                }

                arg4.mAdded = true;
                arg4.mRemoving = false;
                if(arg4.mView == null) {
                    arg4.mHiddenChanged = false;
                }

                if((arg4.mHasMenu) && (arg4.mMenuVisible)) {
                    this.mNeedMenuInvalidate = true;
                }

                if(!arg5) {
                    return;
                }

                this.moveToState(arg4);
            }
        }
    }

    public void addOnBackStackChangedListener(OnBackStackChangedListener arg2) {
        if(this.mBackStackChangeListeners == null) {
            this.mBackStackChangeListeners = new ArrayList();
        }

        this.mBackStackChangeListeners.add(arg2);
    }

    public int allocBackStackIndex(BackStackRecord arg5) {
        int v0;
        __monitor_enter(this);
        try {
            if(this.mAvailBackStackIndices != null) {
                if(this.mAvailBackStackIndices.size() <= 0) {
                }
                else {
                    v0 = this.mAvailBackStackIndices.remove(this.mAvailBackStackIndices.size() - 1).intValue();
                    if(FragmentManagerImpl.DEBUG) {
                        Log.v("FragmentManager", "Adding back stack index " + v0 + " with " + arg5);
                    }

                    this.mBackStackIndices.set(v0, arg5);
                    __monitor_exit(this);
                    return v0;
                }
            }

            if(this.mBackStackIndices == null) {
                this.mBackStackIndices = new ArrayList();
            }

            v0 = this.mBackStackIndices.size();
            if(FragmentManagerImpl.DEBUG) {
                Log.v("FragmentManager", "Setting back stack index " + v0 + " to " + arg5);
            }

            this.mBackStackIndices.add(arg5);
            __monitor_exit(this);
            return v0;
        label_55:
            __monitor_exit(this);
        }
        catch(Throwable v5) {
            goto label_55;
        }

        throw v5;
    }

    private void animateRemoveFragment(@NonNull Fragment arg4, @NonNull AnimationOrAnimator arg5, int arg6) {
        View v0 = arg4.mView;
        arg4.setStateAfterAnimating(arg6);
        if(arg5.animation != null) {
            Animation v6 = arg5.animation;
            arg4.setAnimatingAway(arg4.mView);
            v6.setAnimationListener(new AnimationListenerWrapper(FragmentManagerImpl.getAnimationListener(v6), arg4) {
                public void onAnimationEnd(Animation arg8) {
                    super.onAnimationEnd(arg8);
                    if(this.val$fragment.getAnimatingAway() != null) {
                        this.val$fragment.setAnimatingAway(null);
                        FragmentManagerImpl.this.moveToState(this.val$fragment, this.val$fragment.getStateAfterAnimating(), 0, 0, false);
                    }
                }
            });
            FragmentManagerImpl.setHWLayerAnimListenerIfAlpha(v0, arg5);
            arg4.mView.startAnimation(v6);
        }
        else {
            Animator v6_1 = arg5.animator;
            arg4.setAnimator(arg5.animator);
            ViewGroup v1 = arg4.mContainer;
            if(v1 != null) {
                v1.startViewTransition(v0);
            }

            v6_1.addListener(new AnimatorListenerAdapter(v1, v0, arg4) {
                public void onAnimationEnd(Animator arg8) {
                    if(this.val$container != null) {
                        this.val$container.endViewTransition(this.val$viewToAnimate);
                    }

                    if(this.val$fragment.getAnimator() != null) {
                        this.val$fragment.setAnimator(null);
                        FragmentManagerImpl.this.moveToState(this.val$fragment, this.val$fragment.getStateAfterAnimating(), 0, 0, false);
                    }
                }
            });
            v6_1.setTarget(arg4.mView);
            FragmentManagerImpl.setHWLayerAnimListenerIfAlpha(arg4.mView, arg5);
            v6_1.start();
        }
    }

    public void attachController(FragmentHostCallback arg2, FragmentContainer arg3, Fragment arg4) {
        if(this.mHost != null) {
            throw new IllegalStateException("Already attached");
        }

        this.mHost = arg2;
        this.mContainer = arg3;
        this.mParent = arg4;
    }

    public void attachFragment(Fragment arg4) {
        if(FragmentManagerImpl.DEBUG) {
            Log.v("FragmentManager", "attach: " + arg4);
        }

        if(arg4.mDetached) {
            arg4.mDetached = false;
            if(!arg4.mAdded) {
                if(this.mAdded.contains(arg4)) {
                    v1 = new StringBuilder();
                    v1.append("Fragment already added: ");
                    v1.append(arg4);
                    throw new IllegalStateException(v1.toString());
                }
                else {
                    if(FragmentManagerImpl.DEBUG) {
                        Log.v("FragmentManager", "add from attach: " + arg4);
                    }

                    ArrayList v0 = this.mAdded;
                    __monitor_enter(v0);
                    try {
                        this.mAdded.add(arg4);
                        __monitor_exit(v0);
                    }
                    catch(Throwable v4) {
                        try {
                        label_52:
                            __monitor_exit(v0);
                        }
                        catch(Throwable v4) {
                            goto label_52;
                        }

                        throw v4;
                    }

                    arg4.mAdded = true;
                    if(!arg4.mHasMenu) {
                        return;
                    }

                    if(!arg4.mMenuVisible) {
                        return;
                    }

                    this.mNeedMenuInvalidate = true;
                }
            }
        }
    }

    public FragmentTransaction beginTransaction() {
        return new BackStackRecord(this);
    }

    private void burpActive() {
        if(this.mActive != null) {
            int v0;
            for(v0 = this.mActive.size() - 1; v0 >= 0; --v0) {
                if(this.mActive.valueAt(v0) == null) {
                    this.mActive.delete(this.mActive.keyAt(v0));
                }
            }
        }
    }

    private void checkStateLoss() {
        if(this.mStateSaved) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }

        if(this.mNoTransactionsBecause != null) {
            StringBuilder v1 = new StringBuilder();
            v1.append("Can not perform this action inside of ");
            v1.append(this.mNoTransactionsBecause);
            throw new IllegalStateException(v1.toString());
        }
    }

    private void cleanupExec() {
        this.mExecutingActions = false;
        this.mTmpIsPop.clear();
        this.mTmpRecords.clear();
    }

    private void completeExecute(BackStackRecord arg8, boolean arg9, boolean arg10, boolean arg11) {
        if(arg9) {
            arg8.executePopOps(arg11);
        }
        else {
            arg8.executeOps();
        }

        ArrayList v1 = new ArrayList(1);
        ArrayList v2 = new ArrayList(1);
        v1.add(arg8);
        v2.add(Boolean.valueOf(arg9));
        if(arg10) {
            FragmentTransition.startTransitions(this, v1, v2, 0, 1, true);
        }

        if(arg11) {
            this.moveToState(this.mCurState, true);
        }

        if(this.mActive != null) {
            int v9 = this.mActive.size();
            int v0;
            for(v0 = 0; v0 < v9; ++v0) {
                Object v1_1 = this.mActive.valueAt(v0);
                if(v1_1 != null && ((Fragment)v1_1).mView != null && (((Fragment)v1_1).mIsNewlyAdded) && (arg8.interactsWith(((Fragment)v1_1).mContainerId))) {
                    if(((Fragment)v1_1).mPostponedAlpha > 0f) {
                        ((Fragment)v1_1).mView.setAlpha(((Fragment)v1_1).mPostponedAlpha);
                    }

                    if(arg11) {
                        ((Fragment)v1_1).mPostponedAlpha = 0f;
                        goto label_50;
                    }

                    ((Fragment)v1_1).mPostponedAlpha = -1f;
                    ((Fragment)v1_1).mIsNewlyAdded = false;
                }

            label_50:
            }
        }
    }

    void completeShowHideFragment(Fragment arg8) {
        if(arg8.mView != null) {
            AnimationOrAnimator v0 = this.loadAnimation(arg8, arg8.getNextTransition(), arg8.mHidden ^ 1, arg8.getNextTransitionStyle());
            if(v0 != null && v0.animator != null) {
                v0.animator.setTarget(arg8.mView);
                if(!arg8.mHidden) {
                    arg8.mView.setVisibility(0);
                }
                else if(arg8.isHideReplaced()) {
                    arg8.setHideReplaced(false);
                }
                else {
                    ViewGroup v3 = arg8.mContainer;
                    View v4 = arg8.mView;
                    v3.startViewTransition(v4);
                    v0.animator.addListener(new AnimatorListenerAdapter(v3, v4, arg8) {
                        public void onAnimationEnd(Animator arg3) {
                            this.val$container.endViewTransition(this.val$animatingView);
                            arg3.removeListener(((Animator$AnimatorListener)this));
                            if(this.val$fragment.mView != null) {
                                this.val$fragment.mView.setVisibility(8);
                            }
                        }
                    });
                }

                FragmentManagerImpl.setHWLayerAnimListenerIfAlpha(arg8.mView, v0);
                v0.animator.start();
                goto label_56;
            }

            if(v0 != null) {
                FragmentManagerImpl.setHWLayerAnimListenerIfAlpha(arg8.mView, v0);
                arg8.mView.startAnimation(v0.animation);
                v0.animation.start();
            }

            int v0_1 = !arg8.mHidden || (arg8.isHideReplaced()) ? 0 : 8;
            arg8.mView.setVisibility(v0_1);
            if(!arg8.isHideReplaced()) {
                goto label_56;
            }

            arg8.setHideReplaced(false);
        }

    label_56:
        if((arg8.mAdded) && (arg8.mHasMenu) && (arg8.mMenuVisible)) {
            this.mNeedMenuInvalidate = true;
        }

        arg8.mHiddenChanged = false;
        arg8.onHiddenChanged(arg8.mHidden);
    }

    public void detachFragment(Fragment arg5) {
        if(FragmentManagerImpl.DEBUG) {
            Log.v("FragmentManager", "detach: " + arg5);
        }

        if(!arg5.mDetached) {
            arg5.mDetached = true;
            if(arg5.mAdded) {
                if(FragmentManagerImpl.DEBUG) {
                    Log.v("FragmentManager", "remove from detach: " + arg5);
                }

                ArrayList v1_1 = this.mAdded;
                __monitor_enter(v1_1);
                try {
                    this.mAdded.remove(arg5);
                    __monitor_exit(v1_1);
                }
                catch(Throwable v5) {
                    try {
                    label_40:
                        __monitor_exit(v1_1);
                    }
                    catch(Throwable v5) {
                        goto label_40;
                    }

                    throw v5;
                }

                if((arg5.mHasMenu) && (arg5.mMenuVisible)) {
                    this.mNeedMenuInvalidate = true;
                }

                arg5.mAdded = false;
            }
        }
    }

    public void dispatchActivityCreated() {
        this.mStateSaved = false;
        this.dispatchStateChange(2);
    }

    public void dispatchConfigurationChanged(Configuration arg3) {
        int v0;
        for(v0 = 0; v0 < this.mAdded.size(); ++v0) {
            Object v1 = this.mAdded.get(v0);
            if(v1 != null) {
                ((Fragment)v1).performConfigurationChanged(arg3);
            }
        }
    }

    public boolean dispatchContextItemSelected(MenuItem arg4) {
        int v1;
        for(v1 = 0; v1 < this.mAdded.size(); ++v1) {
            Object v2 = this.mAdded.get(v1);
            if(v2 != null && (((Fragment)v2).performContextItemSelected(arg4))) {
                return 1;
            }
        }

        return 0;
    }

    public void dispatchCreate() {
        this.mStateSaved = false;
        this.dispatchStateChange(1);
    }

    public boolean dispatchCreateOptionsMenu(Menu arg7, MenuInflater arg8) {
        int v0 = 0;
        ArrayList v2 = null;
        int v1 = 0;
        boolean v3 = false;
        while(v1 < this.mAdded.size()) {
            Object v4 = this.mAdded.get(v1);
            if(v4 != null && (((Fragment)v4).performCreateOptionsMenu(arg7, arg8))) {
                if(v2 == null) {
                    v2 = new ArrayList();
                }

                v2.add(v4);
                v3 = true;
            }

            ++v1;
        }

        if(this.mCreatedMenus != null) {
            while(v0 < this.mCreatedMenus.size()) {
                Object v7 = this.mCreatedMenus.get(v0);
                if(v2 == null || !v2.contains(v7)) {
                    ((Fragment)v7).onDestroyOptionsMenu();
                }

                ++v0;
            }
        }

        this.mCreatedMenus = v2;
        return v3;
    }

    public void dispatchDestroy() {
        this.mDestroyed = true;
        this.execPendingActions();
        this.dispatchStateChange(0);
        this.mHost = null;
        this.mContainer = null;
        this.mParent = null;
    }

    public void dispatchDestroyView() {
        this.dispatchStateChange(1);
    }

    public void dispatchLowMemory() {
        int v0;
        for(v0 = 0; v0 < this.mAdded.size(); ++v0) {
            Object v1 = this.mAdded.get(v0);
            if(v1 != null) {
                ((Fragment)v1).performLowMemory();
            }
        }
    }

    public void dispatchMultiWindowModeChanged(boolean arg3) {
        int v0;
        for(v0 = this.mAdded.size() - 1; v0 >= 0; --v0) {
            Object v1 = this.mAdded.get(v0);
            if(v1 != null) {
                ((Fragment)v1).performMultiWindowModeChanged(arg3);
            }
        }
    }

    void dispatchOnFragmentActivityCreated(Fragment arg4, Bundle arg5, boolean arg6) {
        if(this.mParent != null) {
            FragmentManager v0 = this.mParent.getFragmentManager();
            if((v0 instanceof FragmentManagerImpl)) {
                ((FragmentManagerImpl)v0).dispatchOnFragmentActivityCreated(arg4, arg5, true);
            }
        }

        Iterator v0_1 = this.mLifecycleCallbacks.iterator();
        while(v0_1.hasNext()) {
            Object v1 = v0_1.next();
            if((arg6) && !((Pair)v1).second.booleanValue()) {
                continue;
            }

            ((Pair)v1).first.onFragmentActivityCreated(((FragmentManager)this), arg4, arg5);
        }
    }

    void dispatchOnFragmentAttached(Fragment arg4, Context arg5, boolean arg6) {
        if(this.mParent != null) {
            FragmentManager v0 = this.mParent.getFragmentManager();
            if((v0 instanceof FragmentManagerImpl)) {
                ((FragmentManagerImpl)v0).dispatchOnFragmentAttached(arg4, arg5, true);
            }
        }

        Iterator v0_1 = this.mLifecycleCallbacks.iterator();
        while(v0_1.hasNext()) {
            Object v1 = v0_1.next();
            if((arg6) && !((Pair)v1).second.booleanValue()) {
                continue;
            }

            ((Pair)v1).first.onFragmentAttached(((FragmentManager)this), arg4, arg5);
        }
    }

    void dispatchOnFragmentCreated(Fragment arg4, Bundle arg5, boolean arg6) {
        if(this.mParent != null) {
            FragmentManager v0 = this.mParent.getFragmentManager();
            if((v0 instanceof FragmentManagerImpl)) {
                ((FragmentManagerImpl)v0).dispatchOnFragmentCreated(arg4, arg5, true);
            }
        }

        Iterator v0_1 = this.mLifecycleCallbacks.iterator();
        while(v0_1.hasNext()) {
            Object v1 = v0_1.next();
            if((arg6) && !((Pair)v1).second.booleanValue()) {
                continue;
            }

            ((Pair)v1).first.onFragmentCreated(((FragmentManager)this), arg4, arg5);
        }
    }

    void dispatchOnFragmentDestroyed(Fragment arg4, boolean arg5) {
        if(this.mParent != null) {
            FragmentManager v0 = this.mParent.getFragmentManager();
            if((v0 instanceof FragmentManagerImpl)) {
                ((FragmentManagerImpl)v0).dispatchOnFragmentDestroyed(arg4, true);
            }
        }

        Iterator v0_1 = this.mLifecycleCallbacks.iterator();
        while(v0_1.hasNext()) {
            Object v1 = v0_1.next();
            if((arg5) && !((Pair)v1).second.booleanValue()) {
                continue;
            }

            ((Pair)v1).first.onFragmentDestroyed(((FragmentManager)this), arg4);
        }
    }

    void dispatchOnFragmentDetached(Fragment arg4, boolean arg5) {
        if(this.mParent != null) {
            FragmentManager v0 = this.mParent.getFragmentManager();
            if((v0 instanceof FragmentManagerImpl)) {
                ((FragmentManagerImpl)v0).dispatchOnFragmentDetached(arg4, true);
            }
        }

        Iterator v0_1 = this.mLifecycleCallbacks.iterator();
        while(v0_1.hasNext()) {
            Object v1 = v0_1.next();
            if((arg5) && !((Pair)v1).second.booleanValue()) {
                continue;
            }

            ((Pair)v1).first.onFragmentDetached(((FragmentManager)this), arg4);
        }
    }

    void dispatchOnFragmentPaused(Fragment arg4, boolean arg5) {
        if(this.mParent != null) {
            FragmentManager v0 = this.mParent.getFragmentManager();
            if((v0 instanceof FragmentManagerImpl)) {
                ((FragmentManagerImpl)v0).dispatchOnFragmentPaused(arg4, true);
            }
        }

        Iterator v0_1 = this.mLifecycleCallbacks.iterator();
        while(v0_1.hasNext()) {
            Object v1 = v0_1.next();
            if((arg5) && !((Pair)v1).second.booleanValue()) {
                continue;
            }

            ((Pair)v1).first.onFragmentPaused(((FragmentManager)this), arg4);
        }
    }

    void dispatchOnFragmentPreAttached(Fragment arg4, Context arg5, boolean arg6) {
        if(this.mParent != null) {
            FragmentManager v0 = this.mParent.getFragmentManager();
            if((v0 instanceof FragmentManagerImpl)) {
                ((FragmentManagerImpl)v0).dispatchOnFragmentPreAttached(arg4, arg5, true);
            }
        }

        Iterator v0_1 = this.mLifecycleCallbacks.iterator();
        while(v0_1.hasNext()) {
            Object v1 = v0_1.next();
            if((arg6) && !((Pair)v1).second.booleanValue()) {
                continue;
            }

            ((Pair)v1).first.onFragmentPreAttached(((FragmentManager)this), arg4, arg5);
        }
    }

    void dispatchOnFragmentPreCreated(Fragment arg4, Bundle arg5, boolean arg6) {
        if(this.mParent != null) {
            FragmentManager v0 = this.mParent.getFragmentManager();
            if((v0 instanceof FragmentManagerImpl)) {
                ((FragmentManagerImpl)v0).dispatchOnFragmentPreCreated(arg4, arg5, true);
            }
        }

        Iterator v0_1 = this.mLifecycleCallbacks.iterator();
        while(v0_1.hasNext()) {
            Object v1 = v0_1.next();
            if((arg6) && !((Pair)v1).second.booleanValue()) {
                continue;
            }

            ((Pair)v1).first.onFragmentPreCreated(((FragmentManager)this), arg4, arg5);
        }
    }

    void dispatchOnFragmentResumed(Fragment arg4, boolean arg5) {
        if(this.mParent != null) {
            FragmentManager v0 = this.mParent.getFragmentManager();
            if((v0 instanceof FragmentManagerImpl)) {
                ((FragmentManagerImpl)v0).dispatchOnFragmentResumed(arg4, true);
            }
        }

        Iterator v0_1 = this.mLifecycleCallbacks.iterator();
        while(v0_1.hasNext()) {
            Object v1 = v0_1.next();
            if((arg5) && !((Pair)v1).second.booleanValue()) {
                continue;
            }

            ((Pair)v1).first.onFragmentResumed(((FragmentManager)this), arg4);
        }
    }

    void dispatchOnFragmentSaveInstanceState(Fragment arg4, Bundle arg5, boolean arg6) {
        if(this.mParent != null) {
            FragmentManager v0 = this.mParent.getFragmentManager();
            if((v0 instanceof FragmentManagerImpl)) {
                ((FragmentManagerImpl)v0).dispatchOnFragmentSaveInstanceState(arg4, arg5, true);
            }
        }

        Iterator v0_1 = this.mLifecycleCallbacks.iterator();
        while(v0_1.hasNext()) {
            Object v1 = v0_1.next();
            if((arg6) && !((Pair)v1).second.booleanValue()) {
                continue;
            }

            ((Pair)v1).first.onFragmentSaveInstanceState(((FragmentManager)this), arg4, arg5);
        }
    }

    void dispatchOnFragmentStarted(Fragment arg4, boolean arg5) {
        if(this.mParent != null) {
            FragmentManager v0 = this.mParent.getFragmentManager();
            if((v0 instanceof FragmentManagerImpl)) {
                ((FragmentManagerImpl)v0).dispatchOnFragmentStarted(arg4, true);
            }
        }

        Iterator v0_1 = this.mLifecycleCallbacks.iterator();
        while(v0_1.hasNext()) {
            Object v1 = v0_1.next();
            if((arg5) && !((Pair)v1).second.booleanValue()) {
                continue;
            }

            ((Pair)v1).first.onFragmentStarted(((FragmentManager)this), arg4);
        }
    }

    void dispatchOnFragmentStopped(Fragment arg4, boolean arg5) {
        if(this.mParent != null) {
            FragmentManager v0 = this.mParent.getFragmentManager();
            if((v0 instanceof FragmentManagerImpl)) {
                ((FragmentManagerImpl)v0).dispatchOnFragmentStopped(arg4, true);
            }
        }

        Iterator v0_1 = this.mLifecycleCallbacks.iterator();
        while(v0_1.hasNext()) {
            Object v1 = v0_1.next();
            if((arg5) && !((Pair)v1).second.booleanValue()) {
                continue;
            }

            ((Pair)v1).first.onFragmentStopped(((FragmentManager)this), arg4);
        }
    }

    void dispatchOnFragmentViewCreated(Fragment arg4, View arg5, Bundle arg6, boolean arg7) {
        if(this.mParent != null) {
            FragmentManager v0 = this.mParent.getFragmentManager();
            if((v0 instanceof FragmentManagerImpl)) {
                ((FragmentManagerImpl)v0).dispatchOnFragmentViewCreated(arg4, arg5, arg6, true);
            }
        }

        Iterator v0_1 = this.mLifecycleCallbacks.iterator();
        while(v0_1.hasNext()) {
            Object v1 = v0_1.next();
            if((arg7) && !((Pair)v1).second.booleanValue()) {
                continue;
            }

            ((Pair)v1).first.onFragmentViewCreated(((FragmentManager)this), arg4, arg5, arg6);
        }
    }

    void dispatchOnFragmentViewDestroyed(Fragment arg4, boolean arg5) {
        if(this.mParent != null) {
            FragmentManager v0 = this.mParent.getFragmentManager();
            if((v0 instanceof FragmentManagerImpl)) {
                ((FragmentManagerImpl)v0).dispatchOnFragmentViewDestroyed(arg4, true);
            }
        }

        Iterator v0_1 = this.mLifecycleCallbacks.iterator();
        while(v0_1.hasNext()) {
            Object v1 = v0_1.next();
            if((arg5) && !((Pair)v1).second.booleanValue()) {
                continue;
            }

            ((Pair)v1).first.onFragmentViewDestroyed(((FragmentManager)this), arg4);
        }
    }

    public boolean dispatchOptionsItemSelected(MenuItem arg4) {
        int v1;
        for(v1 = 0; v1 < this.mAdded.size(); ++v1) {
            Object v2 = this.mAdded.get(v1);
            if(v2 != null && (((Fragment)v2).performOptionsItemSelected(arg4))) {
                return 1;
            }
        }

        return 0;
    }

    public void dispatchOptionsMenuClosed(Menu arg3) {
        int v0;
        for(v0 = 0; v0 < this.mAdded.size(); ++v0) {
            Object v1 = this.mAdded.get(v0);
            if(v1 != null) {
                ((Fragment)v1).performOptionsMenuClosed(arg3);
            }
        }
    }

    public void dispatchPause() {
        this.dispatchStateChange(4);
    }

    public void dispatchPictureInPictureModeChanged(boolean arg3) {
        int v0;
        for(v0 = this.mAdded.size() - 1; v0 >= 0; --v0) {
            Object v1 = this.mAdded.get(v0);
            if(v1 != null) {
                ((Fragment)v1).performPictureInPictureModeChanged(arg3);
            }
        }
    }

    public boolean dispatchPrepareOptionsMenu(Menu arg4) {
        int v0 = 0;
        boolean v1 = false;
        while(v0 < this.mAdded.size()) {
            Object v2 = this.mAdded.get(v0);
            if(v2 != null && (((Fragment)v2).performPrepareOptionsMenu(arg4))) {
                v1 = true;
            }

            ++v0;
        }

        return v1;
    }

    public void dispatchReallyStop() {
        this.dispatchStateChange(2);
    }

    public void dispatchResume() {
        this.mStateSaved = false;
        this.dispatchStateChange(5);
    }

    public void dispatchStart() {
        this.mStateSaved = false;
        this.dispatchStateChange(4);
    }

    private void dispatchStateChange(int arg3) {
        try {
            this.mExecutingActions = true;
            this.moveToState(arg3, false);
        }
        catch(Throwable v3) {
            this.mExecutingActions = false;
            throw v3;
        }

        this.mExecutingActions = false;
        this.execPendingActions();
    }

    public void dispatchStop() {
        this.mStateSaved = true;
        this.dispatchStateChange(3);
    }

    void doPendingDeferredStart() {
        if(this.mHavePendingDeferredStart) {
            int v1 = 0;
            int v2 = 0;
            while(v1 < this.mActive.size()) {
                Object v3 = this.mActive.valueAt(v1);
                if(v3 != null && ((Fragment)v3).mLoaderManager != null) {
                    v2 |= ((Fragment)v3).mLoaderManager.hasRunningLoaders();
                }

                ++v1;
            }

            if(v2 != 0) {
                return;
            }

            this.mHavePendingDeferredStart = false;
            this.startPendingDeferredFragments();
        }
    }

    public void dump(String arg7, FileDescriptor arg8, PrintWriter arg9, String[] arg10) {
        int v8;
        Object v4;
        int v1;
        String v0_1 = arg7 + "    ";
        int v2 = 0;
        if(this.mActive != null) {
            v1 = this.mActive.size();
            if(v1 > 0) {
                arg9.print(arg7);
                arg9.print("Active Fragments in ");
                arg9.print(Integer.toHexString(System.identityHashCode(this)));
                arg9.println(":");
                int v3;
                for(v3 = 0; v3 < v1; ++v3) {
                    v4 = this.mActive.valueAt(v3);
                    arg9.print(arg7);
                    arg9.print("  #");
                    arg9.print(v3);
                    arg9.print(": ");
                    arg9.println(v4);
                    if(v4 != null) {
                        ((Fragment)v4).dump(v0_1, arg8, arg9, arg10);
                    }
                }
            }
        }

        v1 = this.mAdded.size();
        if(v1 > 0) {
            arg9.print(arg7);
            arg9.println("Added Fragments:");
            for(v3 = 0; v3 < v1; ++v3) {
                v4 = this.mAdded.get(v3);
                arg9.print(arg7);
                arg9.print("  #");
                arg9.print(v3);
                arg9.print(": ");
                arg9.println(((Fragment)v4).toString());
            }
        }

        if(this.mCreatedMenus != null) {
            v1 = this.mCreatedMenus.size();
            if(v1 > 0) {
                arg9.print(arg7);
                arg9.println("Fragments Created Menus:");
                for(v3 = 0; v3 < v1; ++v3) {
                    v4 = this.mCreatedMenus.get(v3);
                    arg9.print(arg7);
                    arg9.print("  #");
                    arg9.print(v3);
                    arg9.print(": ");
                    arg9.println(((Fragment)v4).toString());
                }
            }
        }

        if(this.mBackStack != null) {
            v1 = this.mBackStack.size();
            if(v1 > 0) {
                arg9.print(arg7);
                arg9.println("Back Stack:");
                for(v3 = 0; v3 < v1; ++v3) {
                    v4 = this.mBackStack.get(v3);
                    arg9.print(arg7);
                    arg9.print("  #");
                    arg9.print(v3);
                    arg9.print(": ");
                    arg9.println(((BackStackRecord)v4).toString());
                    ((BackStackRecord)v4).dump(v0_1, arg8, arg9, arg10);
                }
            }
        }

        __monitor_enter(this);
        try {
            if(this.mBackStackIndices != null) {
                v8 = this.mBackStackIndices.size();
                if(v8 > 0) {
                    arg9.print(arg7);
                    arg9.println("Back Stack Indices:");
                    int v10;
                    for(v10 = 0; v10 < v8; ++v10) {
                        Object v0_2 = this.mBackStackIndices.get(v10);
                        arg9.print(arg7);
                        arg9.print("  #");
                        arg9.print(v10);
                        arg9.print(": ");
                        arg9.println(v0_2);
                    }
                }
            }

            if(this.mAvailBackStackIndices != null && this.mAvailBackStackIndices.size() > 0) {
                arg9.print(arg7);
                arg9.print("mAvailBackStackIndices: ");
                arg9.println(Arrays.toString(this.mAvailBackStackIndices.toArray()));
            }

            __monitor_exit(this);
        }
        catch(Throwable v7) {
            try {
            label_204:
                __monitor_exit(this);
            }
            catch(Throwable v7) {
                goto label_204;
            }

            throw v7;
        }

        if(this.mPendingActions != null) {
            v8 = this.mPendingActions.size();
            if(v8 > 0) {
                arg9.print(arg7);
                arg9.println("Pending Actions:");
                while(v2 < v8) {
                    Object v10_1 = this.mPendingActions.get(v2);
                    arg9.print(arg7);
                    arg9.print("  #");
                    arg9.print(v2);
                    arg9.print(": ");
                    arg9.println(v10_1);
                    ++v2;
                }
            }
        }

        arg9.print(arg7);
        arg9.println("FragmentManager misc state:");
        arg9.print(arg7);
        arg9.print("  mHost=");
        arg9.println(this.mHost);
        arg9.print(arg7);
        arg9.print("  mContainer=");
        arg9.println(this.mContainer);
        if(this.mParent != null) {
            arg9.print(arg7);
            arg9.print("  mParent=");
            arg9.println(this.mParent);
        }

        arg9.print(arg7);
        arg9.print("  mCurState=");
        arg9.print(this.mCurState);
        arg9.print(" mStateSaved=");
        arg9.print(this.mStateSaved);
        arg9.print(" mDestroyed=");
        arg9.println(this.mDestroyed);
        if(this.mNeedMenuInvalidate) {
            arg9.print(arg7);
            arg9.print("  mNeedMenuInvalidate=");
            arg9.println(this.mNeedMenuInvalidate);
        }

        if(this.mNoTransactionsBecause != null) {
            arg9.print(arg7);
            arg9.print("  mNoTransactionsBecause=");
            arg9.println(this.mNoTransactionsBecause);
        }
    }

    private void endAnimatingAwayFragments() {
        int v1 = 0;
        int v0 = this.mActive == null ? 0 : this.mActive.size();
        while(v1 < v0) {
            Object v4 = this.mActive.valueAt(v1);
            if(v4 != null) {
                if(((Fragment)v4).getAnimatingAway() != null) {
                    int v5 = ((Fragment)v4).getStateAfterAnimating();
                    View v2 = ((Fragment)v4).getAnimatingAway();
                    ((Fragment)v4).setAnimatingAway(null);
                    Animation v3 = v2.getAnimation();
                    if(v3 != null) {
                        v3.cancel();
                        v2.clearAnimation();
                    }

                    this.moveToState(((Fragment)v4), v5, 0, 0, false);
                }
                else {
                    if(((Fragment)v4).getAnimator() == null) {
                        goto label_32;
                    }

                    ((Fragment)v4).getAnimator().end();
                }
            }

        label_32:
            ++v1;
        }
    }

    public void enqueueAction(OpGenerator arg2, boolean arg3) {
        if(!arg3) {
            this.checkStateLoss();
        }

        __monitor_enter(this);
        try {
            if(!this.mDestroyed) {
                if(this.mHost == null) {
                }
                else {
                    if(this.mPendingActions == null) {
                        this.mPendingActions = new ArrayList();
                    }

                    this.mPendingActions.add(arg2);
                    this.scheduleCommit();
                    __monitor_exit(this);
                    return;
                }
            }

            if(arg3) {
                __monitor_exit(this);
                return;
            }

            throw new IllegalStateException("Activity has been destroyed");
        label_26:
            __monitor_exit(this);
        }
        catch(Throwable v2) {
            goto label_26;
        }

        throw v2;
    }

    private void ensureExecReady(boolean arg3) {
        if(this.mExecutingActions) {
            throw new IllegalStateException("FragmentManager is already executing transactions");
        }

        if(Looper.myLooper() != this.mHost.getHandler().getLooper()) {
            throw new IllegalStateException("Must be called from main thread of fragment host");
        }

        if(!arg3) {
            this.checkStateLoss();
        }

        if(this.mTmpRecords == null) {
            this.mTmpRecords = new ArrayList();
            this.mTmpIsPop = new ArrayList();
        }

        this.mExecutingActions = true;
        ArrayList v0 = null;
        try {
            this.executePostponedTransaction(v0, v0);
        }
        catch(Throwable v0_1) {
            this.mExecutingActions = false;
            throw v0_1;
        }

        this.mExecutingActions = false;
    }

    void ensureInflatedFragmentView(Fragment arg4) {
        if((arg4.mFromLayout) && !arg4.mPerformedCreateView) {
            ViewGroup v2 = null;
            arg4.mView = arg4.performCreateView(arg4.performGetLayoutInflater(arg4.mSavedFragmentState), v2, arg4.mSavedFragmentState);
            if(arg4.mView != null) {
                arg4.mInnerView = arg4.mView;
                arg4.mView.setSaveFromParentEnabled(false);
                if(arg4.mHidden) {
                    arg4.mView.setVisibility(8);
                }

                arg4.onViewCreated(arg4.mView, arg4.mSavedFragmentState);
                this.dispatchOnFragmentViewCreated(arg4, arg4.mView, arg4.mSavedFragmentState, false);
            }
            else {
                arg4.mInnerView = ((View)v2);
            }
        }
    }

    public boolean execPendingActions() {
        this.ensureExecReady(true);
        boolean v1;
        for(v1 = false; this.generateOpsForPendingActions(this.mTmpRecords, this.mTmpIsPop); v1 = true) {
            this.mExecutingActions = true;
            try {
                this.removeRedundantOperationsAndExecute(this.mTmpRecords, this.mTmpIsPop);
            }
            catch(Throwable v0) {
                this.cleanupExec();
                throw v0;
            }

            this.cleanupExec();
        }

        this.doPendingDeferredStart();
        this.burpActive();
        return v1;
    }

    public void execSingleAction(OpGenerator arg2, boolean arg3) {
        if((arg3) && (this.mHost == null || (this.mDestroyed))) {
            return;
        }

        this.ensureExecReady(arg3);
        if(arg2.generateOps(this.mTmpRecords, this.mTmpIsPop)) {
            this.mExecutingActions = true;
            try {
                this.removeRedundantOperationsAndExecute(this.mTmpRecords, this.mTmpIsPop);
            }
            catch(Throwable v2) {
                this.cleanupExec();
                throw v2;
            }

            this.cleanupExec();
        }

        this.doPendingDeferredStart();
        this.burpActive();
    }

    private static void executeOps(ArrayList arg3, ArrayList arg4, int arg5, int arg6) {
        while(arg5 < arg6) {
            Object v0 = arg3.get(arg5);
            boolean v2 = true;
            if(arg4.get(arg5).booleanValue()) {
                ((BackStackRecord)v0).bumpBackStackNesting(-1);
                if(arg5 == arg6 - 1) {
                }
                else {
                    v2 = false;
                }

                ((BackStackRecord)v0).executePopOps(v2);
            }
            else {
                ((BackStackRecord)v0).bumpBackStackNesting(1);
                ((BackStackRecord)v0).executeOps();
            }

            ++arg5;
        }
    }

    private void executeOpsTogether(ArrayList arg16, ArrayList arg17, int arg18, int arg19) {
        int v4;
        FragmentManagerImpl v6 = this;
        ArrayList v7 = arg16;
        ArrayList v8 = arg17;
        int v9 = arg18;
        int v10 = arg19;
        boolean v11 = v7.get(v9).mReorderingAllowed;
        if(v6.mTmpAddedFragments == null) {
            v6.mTmpAddedFragments = new ArrayList();
        }
        else {
            v6.mTmpAddedFragments.clear();
        }

        v6.mTmpAddedFragments.addAll(v6.mAdded);
        Fragment v2 = v6.getPrimaryNavigationFragment();
        int v0 = v9;
        int v12 = 0;
        while(v0 < v10) {
            Object v3 = v7.get(v0);
            v2 = !v8.get(v0).booleanValue() ? ((BackStackRecord)v3).expandOps(v6.mTmpAddedFragments, v2) : ((BackStackRecord)v3).trackAddedFragmentsInPop(v6.mTmpAddedFragments, v2);
            v12 = v12 != 0 || (((BackStackRecord)v3).mAddToBackStack) ? 1 : 0;
            ++v0;
        }

        v6.mTmpAddedFragments.clear();
        if(!v11) {
            FragmentTransition.startTransitions(v6, v7, v8, v9, v10, false);
        }

        FragmentManagerImpl.executeOps(arg16, arg17, arg18, arg19);
        if(v11) {
            ArraySet v14 = new ArraySet();
            v6.addAddedFragments(v14);
            v0 = v6.postponePostponableTransactions(v7, v8, v9, v10, v14);
            v6.makeRemovedFragmentsInvisible(v14);
            v4 = v0;
        }
        else {
            v4 = v10;
        }

        if(v4 != v9 && (v11)) {
            FragmentTransition.startTransitions(v6, v7, v8, v9, v4, true);
            v6.moveToState(v6.mCurState, true);
        }

        while(v9 < v10) {
            Object v0_1 = v7.get(v9);
            if((v8.get(v9).booleanValue()) && ((BackStackRecord)v0_1).mIndex >= 0) {
                v6.freeBackStackIndex(((BackStackRecord)v0_1).mIndex);
                ((BackStackRecord)v0_1).mIndex = -1;
            }

            ((BackStackRecord)v0_1).runOnCommitRunnables();
            ++v9;
        }

        if(v12 != 0) {
            v6.reportBackStackChanged();
        }
    }

    public boolean executePendingTransactions() {
        boolean v0 = this.execPendingActions();
        this.forcePostponedTransactions();
        return v0;
    }

    private void executePostponedTransaction(ArrayList arg8, ArrayList arg9) {
        int v5;
        int v0 = this.mPostponedTransactions == null ? 0 : this.mPostponedTransactions.size();
        int v2 = v0;
        for(v0 = 0; v0 < v2; ++v0) {
            Object v3 = this.mPostponedTransactions.get(v0);
            int v4 = -1;
            if(arg8 == null || (StartEnterTransitionListener.access$300(((StartEnterTransitionListener)v3)))) {
            label_24:
                if(!((StartEnterTransitionListener)v3).isReady()) {
                    if(arg8 == null) {
                    }
                    else if(StartEnterTransitionListener.access$400(((StartEnterTransitionListener)v3)).interactsWith(arg8, 0, arg8.size())) {
                        goto label_31;
                    }

                    goto label_47;
                }

            label_31:
                this.mPostponedTransactions.remove(v0);
                --v0;
                --v2;
                if(arg8 != null && !StartEnterTransitionListener.access$300(((StartEnterTransitionListener)v3))) {
                    v5 = arg8.indexOf(StartEnterTransitionListener.access$400(((StartEnterTransitionListener)v3)));
                    if(v5 != v4 && (arg9.get(v5).booleanValue())) {
                        ((StartEnterTransitionListener)v3).cancelTransaction();
                        goto label_47;
                    }
                }

                ((StartEnterTransitionListener)v3).completeTransaction();
            }
            else {
                v5 = arg8.indexOf(StartEnterTransitionListener.access$400(((StartEnterTransitionListener)v3)));
                if(v5 == v4) {
                    goto label_24;
                }
                else if(arg9.get(v5).booleanValue()) {
                    ((StartEnterTransitionListener)v3).cancelTransaction();
                }
                else {
                    goto label_24;
                }
            }

        label_47:
        }
    }

    public Fragment findFragmentById(int arg4) {
        Object v1;
        int v0;
        for(v0 = this.mAdded.size() - 1; v0 >= 0; --v0) {
            v1 = this.mAdded.get(v0);
            if(v1 != null && ((Fragment)v1).mFragmentId == arg4) {
                return ((Fragment)v1);
            }
        }

        if(this.mActive != null) {
            for(v0 = this.mActive.size() - 1; v0 >= 0; --v0) {
                v1 = this.mActive.valueAt(v0);
                if(v1 != null && ((Fragment)v1).mFragmentId == arg4) {
                    return ((Fragment)v1);
                }
            }
        }

        return null;
    }

    public Fragment findFragmentByTag(String arg4) {
        Object v1;
        if(arg4 != null) {
            int v0;
            for(v0 = this.mAdded.size() - 1; v0 >= 0; --v0) {
                v1 = this.mAdded.get(v0);
                if(v1 != null && (arg4.equals(((Fragment)v1).mTag))) {
                    return ((Fragment)v1);
                }
            }
        }

        if(this.mActive != null && arg4 != null) {
            for(v0 = this.mActive.size() - 1; v0 >= 0; --v0) {
                v1 = this.mActive.valueAt(v0);
                if(v1 != null && (arg4.equals(((Fragment)v1).mTag))) {
                    return ((Fragment)v1);
                }
            }
        }

        return null;
    }

    public Fragment findFragmentByWho(String arg3) {
        if(this.mActive != null && arg3 != null) {
            int v0;
            for(v0 = this.mActive.size() - 1; v0 >= 0; --v0) {
                Object v1 = this.mActive.valueAt(v0);
                if(v1 != null) {
                    Fragment v1_1 = ((Fragment)v1).findFragmentByWho(arg3);
                    if(v1_1 != null) {
                        return v1_1;
                    }
                }
            }
        }

        return null;
    }

    private Fragment findFragmentUnder(Fragment arg5) {
        ViewGroup v0 = arg5.mContainer;
        View v1 = arg5.mView;
        Fragment v2 = null;
        if(v0 != null) {
            if(v1 == null) {
            }
            else {
                int v5;
                for(v5 = this.mAdded.indexOf(arg5) - 1; v5 >= 0; --v5) {
                    Object v1_1 = this.mAdded.get(v5);
                    if(((Fragment)v1_1).mContainer == v0 && ((Fragment)v1_1).mView != null) {
                        return ((Fragment)v1_1);
                    }
                }

                return v2;
            }
        }

        return v2;
    }

    private void forcePostponedTransactions() {
        if(this.mPostponedTransactions != null) {
            while(!this.mPostponedTransactions.isEmpty()) {
                this.mPostponedTransactions.remove(0).completeTransaction();
            }
        }
    }

    public void freeBackStackIndex(int arg4) {
        __monitor_enter(this);
        try {
            this.mBackStackIndices.set(arg4, null);
            if(this.mAvailBackStackIndices == null) {
                this.mAvailBackStackIndices = new ArrayList();
            }

            if(FragmentManagerImpl.DEBUG) {
                Log.v("FragmentManager", "Freeing back stack index " + arg4);
            }

            this.mAvailBackStackIndices.add(Integer.valueOf(arg4));
            __monitor_exit(this);
            return;
        label_25:
            __monitor_exit(this);
        }
        catch(Throwable v4) {
            goto label_25;
        }

        throw v4;
    }

    private boolean generateOpsForPendingActions(ArrayList arg5, ArrayList arg6) {
        __monitor_enter(this);
        try {
            int v1 = 0;
            if(this.mPendingActions != null) {
                if(this.mPendingActions.size() == 0) {
                }
                else {
                    int v0 = this.mPendingActions.size();
                    int v2 = 0;
                    while(v1 < v0) {
                        v2 |= this.mPendingActions.get(v1).generateOps(arg5, arg6);
                        ++v1;
                    }

                    this.mPendingActions.clear();
                    this.mHost.getHandler().removeCallbacks(this.mExecCommit);
                    __monitor_exit(this);
                    return ((boolean)v2);
                }
            }

            __monitor_exit(this);
            return 0;
        label_29:
            __monitor_exit(this);
        }
        catch(Throwable v5) {
            goto label_29;
        }

        throw v5;
    }

    int getActiveFragmentCount() {
        if(this.mActive == null) {
            return 0;
        }

        return this.mActive.size();
    }

    List getActiveFragments() {
        if(this.mActive == null) {
            return null;
        }

        int v0 = this.mActive.size();
        ArrayList v1 = new ArrayList(v0);
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            v1.add(this.mActive.valueAt(v2));
        }

        return ((List)v1);
    }

    private static Animation$AnimationListener getAnimationListener(Animation arg2) {
        Animation$AnimationListener v2_3;
        try {
            if(FragmentManagerImpl.sAnimationListenerField == null) {
                FragmentManagerImpl.sAnimationListenerField = Animation.class.getDeclaredField("mListener");
                FragmentManagerImpl.sAnimationListenerField.setAccessible(true);
            }

            Object v2_2 = FragmentManagerImpl.sAnimationListenerField.get(arg2);
            return v2_3;
        }
        catch(IllegalAccessException v2) {
            Log.e("FragmentManager", "Cannot access Animation\'s mListener field", ((Throwable)v2));
        }
        catch(NoSuchFieldException v2_1) {
            Log.e("FragmentManager", "No field with the name mListener is found in Animation class", ((Throwable)v2_1));
        }

        v2_3 = null;
        return v2_3;
    }

    public BackStackEntry getBackStackEntryAt(int arg2) {
        return this.mBackStack.get(arg2);
    }

    public int getBackStackEntryCount() {
        int v0 = this.mBackStack != null ? this.mBackStack.size() : 0;
        return v0;
    }

    public Fragment getFragment(Bundle arg5, String arg6) {
        int v5 = arg5.getInt(arg6, -1);
        if(v5 == -1) {
            return null;
        }

        Object v0 = this.mActive.get(v5);
        if(v0 == null) {
            StringBuilder v2 = new StringBuilder();
            v2.append("Fragment no longer exists for key ");
            v2.append(arg6);
            v2.append(": index ");
            v2.append(v5);
            this.throwException(new IllegalStateException(v2.toString()));
        }

        return ((Fragment)v0);
    }

    public List getFragments() {
        if(this.mAdded.isEmpty()) {
            return Collections.EMPTY_LIST;
        }

        ArrayList v0 = this.mAdded;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return this.mAdded.clone();
        label_12:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_12;
        }

        throw v1;
    }

    LayoutInflater$Factory2 getLayoutInflaterFactory() {
        return this;
    }

    public Fragment getPrimaryNavigationFragment() {
        return this.mPrimaryNav;
    }

    public void hideFragment(Fragment arg4) {
        if(FragmentManagerImpl.DEBUG) {
            Log.v("FragmentManager", "hide: " + arg4);
        }

        if(!arg4.mHidden) {
            arg4.mHidden = true;
            arg4.mHiddenChanged ^= 1;
        }
    }

    public boolean isDestroyed() {
        return this.mDestroyed;
    }

    boolean isStateAtLeast(int arg2) {
        boolean v2 = this.mCurState >= arg2 ? true : false;
        return v2;
    }

    public boolean isStateSaved() {
        return this.mStateSaved;
    }

    AnimationOrAnimator loadAnimation(Fragment arg6, int arg7, boolean arg8, int arg9) {
        int v0 = arg6.getNextAnim();
        Animation v1 = arg6.onCreateAnimation(arg7, arg8, v0);
        android.support.v4.app.FragmentManagerImpl$1 v2 = null;
        if(v1 != null) {
            return new AnimationOrAnimator(v1, v2);
        }

        Animator v6 = arg6.onCreateAnimator(arg7, arg8, v0);
        if(v6 != null) {
            return new AnimationOrAnimator(v6, v2);
        }

        if(v0 != 0) {
            boolean v6_1 = "anim".equals(this.mHost.getContext().getResources().getResourceTypeName(v0));
            int v1_1 = 0;
            if(v6_1) {
                try {
                    Animation v3 = AnimationUtils.loadAnimation(this.mHost.getContext(), v0);
                    if(v3 != null) {
                        return new AnimationOrAnimator(v3, v2);
                    }
                    else {
                        goto label_28;
                    }

                    goto label_32;
                }
                catch(RuntimeException ) {
                label_32:
                    if(v1_1 != 0) {
                        goto label_50;
                    }

                    try {
                        Animator v1_3 = AnimatorInflater.loadAnimator(this.mHost.getContext(), v0);
                        if(v1_3 == null) {
                            goto label_50;
                        }

                        return new AnimationOrAnimator(v1_3, v2);
                    }
                    catch(RuntimeException v1_2) {
                        if(v6_1) {
                            throw v1_2;
                        }

                        Animation v6_3 = AnimationUtils.loadAnimation(this.mHost.getContext(), v0);
                        if(v6_3 == null) {
                            goto label_50;
                        }

                        return new AnimationOrAnimator(v6_3, v2);
                    }
                }
                catch(Resources$NotFoundException v6_2) {
                    throw v6_2;
                label_28:
                    v1_1 = 1;
                    goto label_32;
                }
            }

            goto label_32;
        }

    label_50:
        if(arg7 == 0) {
            return ((AnimationOrAnimator)v2);
        }

        int v6_4 = FragmentManagerImpl.transitToStyleIndex(arg7, arg8);
        if(v6_4 < 0) {
            return ((AnimationOrAnimator)v2);
        }

        float v7 = 0.975f;
        float v0_1 = 1f;
        switch(v6_4) {
            case 1: {
                goto label_87;
            }
            case 2: {
                goto label_83;
            }
            case 3: {
                goto label_79;
            }
            case 4: {
                goto label_74;
            }
            case 5: {
                goto label_70;
            }
            case 6: {
                goto label_66;
            }
        }

        if(arg9 == 0 && (this.mHost.onHasWindowAnimations())) {
            arg9 = this.mHost.onGetWindowAnimations();
        }

        if(arg9 == 0) {
            return ((AnimationOrAnimator)v2);
        }

        return ((AnimationOrAnimator)v2);
    label_66:
        return FragmentManagerImpl.makeFadeAnimation(this.mHost.getContext(), v0_1, 0f);
    label_83:
        return FragmentManagerImpl.makeOpenCloseAnimation(this.mHost.getContext(), v0_1, v7, v0_1, 0f);
    label_70:
        return FragmentManagerImpl.makeFadeAnimation(this.mHost.getContext(), 0f, v0_1);
    label_87:
        return FragmentManagerImpl.makeOpenCloseAnimation(this.mHost.getContext(), 1.125f, v0_1, 0f, v0_1);
    label_74:
        return FragmentManagerImpl.makeOpenCloseAnimation(this.mHost.getContext(), v0_1, 1.075f, v0_1, 0f);
    label_79:
        return FragmentManagerImpl.makeOpenCloseAnimation(this.mHost.getContext(), v7, v0_1, 0f, v0_1);
    }

    void makeActive(Fragment arg4) {
        if(arg4.mIndex >= 0) {
            return;
        }

        int v0 = this.mNextFragmentIndex;
        this.mNextFragmentIndex = v0 + 1;
        arg4.setIndex(v0, this.mParent);
        if(this.mActive == null) {
            this.mActive = new SparseArray();
        }

        this.mActive.put(arg4.mIndex, arg4);
        if(FragmentManagerImpl.DEBUG) {
            Log.v("FragmentManager", "Allocated fragment index " + arg4);
        }
    }

    static AnimationOrAnimator makeFadeAnimation(Context arg0, float arg1, float arg2) {
        AlphaAnimation v0 = new AlphaAnimation(arg1, arg2);
        v0.setInterpolator(FragmentManagerImpl.DECELERATE_CUBIC);
        v0.setDuration(220);
        return new AnimationOrAnimator(((Animation)v0), null);
    }

    void makeInactive(Fragment arg4) {
        if(arg4.mIndex < 0) {
            return;
        }

        if(FragmentManagerImpl.DEBUG) {
            Log.v("FragmentManager", "Freeing fragment index " + arg4);
        }

        this.mActive.put(arg4.mIndex, null);
        this.mHost.inactivateFragment(arg4.mWho);
        arg4.initState();
    }

    static AnimationOrAnimator makeOpenCloseAnimation(Context arg10, float arg11, float arg12, float arg13, float arg14) {
        AnimationSet v10 = new AnimationSet(false);
        ScaleAnimation v0 = new ScaleAnimation(arg11, arg12, arg11, arg12, 1, 0.5f, 1, 0.5f);
        v0.setInterpolator(FragmentManagerImpl.DECELERATE_QUINT);
        v0.setDuration(220);
        v10.addAnimation(((Animation)v0));
        AlphaAnimation v0_1 = new AlphaAnimation(arg13, arg14);
        v0_1.setInterpolator(FragmentManagerImpl.DECELERATE_CUBIC);
        v0_1.setDuration(220);
        v10.addAnimation(((Animation)v0_1));
        return new AnimationOrAnimator(((Animation)v10), null);
    }

    private void makeRemovedFragmentsInvisible(ArraySet arg6) {
        int v0 = arg6.size();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            Object v2 = arg6.valueAt(v1);
            if(!((Fragment)v2).mAdded) {
                View v3 = ((Fragment)v2).getView();
                ((Fragment)v2).mPostponedAlpha = v3.getAlpha();
                v3.setAlpha(0f);
            }
        }
    }

    static boolean modifiesAlpha(Animator arg5) {
        int v1;
        if(arg5 == null) {
            return 0;
        }

        if((arg5 instanceof ValueAnimator)) {
            PropertyValuesHolder[] v5 = ((ValueAnimator)arg5).getValues();
            v1 = 0;
            while(v1 < v5.length) {
                if("alpha".equals(v5[v1].getPropertyName())) {
                    return 1;
                }
                else {
                    ++v1;
                    continue;
                }

                return 0;
            }
        }
        else if((arg5 instanceof AnimatorSet)) {
            ArrayList v5_1 = ((AnimatorSet)arg5).getChildAnimations();
            v1 = 0;
            while(v1 < ((List)v5_1).size()) {
                if(FragmentManagerImpl.modifiesAlpha(((List)v5_1).get(v1))) {
                    return 1;
                }
                else {
                    ++v1;
                    continue;
                }

                return 0;
            }
        }

        return 0;
    }

    static boolean modifiesAlpha(AnimationOrAnimator arg4) {
        if((arg4.animation instanceof AlphaAnimation)) {
            return 1;
        }

        if((arg4.animation instanceof AnimationSet)) {
            List v4 = arg4.animation.getAnimations();
            int v2;
            for(v2 = 0; v2 < v4.size(); ++v2) {
                if((v4.get(v2) instanceof AlphaAnimation)) {
                    return 1;
                }
            }

            return 0;
        }

        return FragmentManagerImpl.modifiesAlpha(arg4.animator);
    }

    void moveFragmentToExpectedState(Fragment arg11) {
        if(arg11 == null) {
            return;
        }

        int v0 = this.mCurState;
        if(arg11.mRemoving) {
            v0 = arg11.isInBackStack() ? Math.min(v0, 1) : Math.min(v0, 0);
        }

        this.moveToState(arg11, v0, arg11.getNextTransition(), arg11.getNextTransitionStyle(), false);
        if(arg11.mView != null) {
            Fragment v0_1 = this.findFragmentUnder(arg11);
            if(v0_1 != null) {
                View v0_2 = v0_1.mView;
                ViewGroup v1 = arg11.mContainer;
                v0 = v1.indexOfChild(v0_2);
                int v4 = v1.indexOfChild(arg11.mView);
                if(v4 < v0) {
                    v1.removeViewAt(v4);
                    v1.addView(arg11.mView, v0);
                }
            }

            if(!arg11.mIsNewlyAdded) {
                goto label_61;
            }

            if(arg11.mContainer == null) {
                goto label_61;
            }

            if(arg11.mPostponedAlpha > 0f) {
                arg11.mView.setAlpha(arg11.mPostponedAlpha);
            }

            arg11.mPostponedAlpha = 0f;
            arg11.mIsNewlyAdded = false;
            AnimationOrAnimator v0_3 = this.loadAnimation(arg11, arg11.getNextTransition(), true, arg11.getNextTransitionStyle());
            if(v0_3 == null) {
                goto label_61;
            }

            FragmentManagerImpl.setHWLayerAnimListenerIfAlpha(arg11.mView, v0_3);
            if(v0_3.animation != null) {
                arg11.mView.startAnimation(v0_3.animation);
                goto label_61;
            }

            v0_3.animator.setTarget(arg11.mView);
            v0_3.animator.start();
        }

    label_61:
        if(arg11.mHiddenChanged) {
            this.completeShowHideFragment(arg11);
        }
    }

    void moveToState(Fragment arg16, int arg17, int arg18, int arg19, boolean arg20) {
        String v1_1;
        View v0_2;
        int v0;
        FragmentManagerImpl v6 = this;
        Fragment v7 = arg16;
        boolean v8 = true;
        if(!v7.mAdded || (v7.mDetached)) {
            v0 = arg17;
            if(v0 > 1) {
                v0 = 1;
            }
        }
        else {
            v0 = arg17;
        }

        if((v7.mRemoving) && v0 > v7.mState) {
            if(v7.mState == 0 && (arg16.isInBackStack())) {
                v0 = 1;
                goto label_24;
            }

            v0 = v7.mState;
        }

    label_24:
        int v9 = 4;
        int v10 = 3;
        int v11 = !v7.mDeferStart || v7.mState >= v9 || v0 <= v10 ? v0 : 3;
        int v12 = 2;
        View v13 = null;
        if(v7.mState > v11) {
            goto label_325;
        }

        if((v7.mFromLayout) && !v7.mInLayout) {
            return;
        }

        if(arg16.getAnimatingAway() != null || arg16.getAnimator() != null) {
            v7.setAnimatingAway(v13);
            v7.setAnimator(((Animator)v13));
            v6.moveToState(v7, arg16.getStateAfterAnimating(), 0, 0, true);
        }

        switch(v7.mState) {
            case 0: {
                goto label_60;
            }
            case 1: {
                goto label_189;
            }
            case 2: {
                goto label_294;
            }
            case 3: {
                goto label_296;
            }
            case 4: {
                goto label_309;
            }
        }

        goto label_472;
    label_60:
        if(v11 > 0) {
            if(FragmentManagerImpl.DEBUG) {
                Log.v("FragmentManager", "moveto CREATED: " + v7);
            }

            if(v7.mSavedFragmentState != null) {
                v7.mSavedFragmentState.setClassLoader(v6.mHost.getContext().getClassLoader());
                v7.mSavedViewState = v7.mSavedFragmentState.getSparseParcelableArray("android:view_state");
                v7.mTarget = v6.getFragment(v7.mSavedFragmentState, "android:target_state");
                if(v7.mTarget != null) {
                    v7.mTargetRequestCode = v7.mSavedFragmentState.getInt("android:target_req_state", 0);
                }

                v7.mUserVisibleHint = v7.mSavedFragmentState.getBoolean("android:user_visible_hint", true);
                if(v7.mUserVisibleHint) {
                    goto label_101;
                }

                v7.mDeferStart = true;
                if(v11 <= v10) {
                    goto label_101;
                }

                v11 = 3;
            }

        label_101:
            v7.mHost = v6.mHost;
            v7.mParentFragment = v6.mParent;
            FragmentManagerImpl v0_1 = v6.mParent != null ? v6.mParent.mChildFragmentManager : v6.mHost.getFragmentManagerImpl();
            v7.mFragmentManager = v0_1;
            if(v7.mTarget != null) {
                if(v6.mActive.get(v7.mTarget.mIndex) != v7.mTarget) {
                    v1 = new StringBuilder();
                    v1.append("Fragment ");
                    v1.append(v7);
                    v1.append(" declared target fragment ");
                    v1.append(v7.mTarget);
                    v1.append(" that does not belong to this FragmentManager!");
                    throw new IllegalStateException(v1.toString());
                }
                else if(v7.mTarget.mState < 1) {
                    v6.moveToState(v7.mTarget, 1, 0, 0, true);
                }
            }

            v6.dispatchOnFragmentPreAttached(v7, v6.mHost.getContext(), false);
            v7.mCalled = false;
            v7.onAttach(v6.mHost.getContext());
            if(!v7.mCalled) {
                v1 = new StringBuilder();
                v1.append("Fragment ");
                v1.append(v7);
                v1.append(" did not call through to super.onAttach()");
                throw new SuperNotCalledException(v1.toString());
            }

            if(v7.mParentFragment == null) {
                v6.mHost.onAttachFragment(v7);
            }
            else {
                v7.mParentFragment.onAttachFragment(v7);
            }

            v6.dispatchOnFragmentAttached(v7, v6.mHost.getContext(), false);
            if(!v7.mIsCreated) {
                v6.dispatchOnFragmentPreCreated(v7, v7.mSavedFragmentState, false);
                v7.performCreate(v7.mSavedFragmentState);
                v6.dispatchOnFragmentCreated(v7, v7.mSavedFragmentState, false);
            }
            else {
                v7.restoreChildFragmentState(v7.mSavedFragmentState);
                v7.mState = 1;
            }

            v7.mRetaining = false;
        }

    label_189:
        this.ensureInflatedFragmentView(arg16);
        if(v11 > 1) {
            if(FragmentManagerImpl.DEBUG) {
                Log.v("FragmentManager", "moveto ACTIVITY_CREATED: " + v7);
            }

            if(!v7.mFromLayout) {
                if(v7.mContainerId != 0) {
                    if(v7.mContainerId == -1) {
                        v1 = new StringBuilder();
                        v1.append("Cannot create fragment ");
                        v1.append(v7);
                        v1.append(" for a container view with no id");
                        v6.throwException(new IllegalArgumentException(v1.toString()));
                    }

                    v0_2 = v6.mContainer.onFindViewById(v7.mContainerId);
                    if(v0_2 != null) {
                        goto label_249;
                    }

                    if(v7.mRestored) {
                        goto label_249;
                    }

                    try {
                        v1_1 = arg16.getResources().getResourceName(v7.mContainerId);
                    }
                    catch(Resources$NotFoundException ) {
                        v1_1 = "unknown";
                    }

                    StringBuilder v3 = new StringBuilder();
                    v3.append("No view found for id 0x");
                    v3.append(Integer.toHexString(v7.mContainerId));
                    v3.append(" (");
                    v3.append(v1_1);
                    v3.append(") for fragment ");
                    v3.append(v7);
                    v6.throwException(new IllegalArgumentException(v3.toString()));
                }
                else {
                    ViewGroup v0_3 = ((ViewGroup)v13);
                }

            label_249:
                v7.mContainer = ((ViewGroup)v0_2);
                v7.mView = v7.performCreateView(v7.performGetLayoutInflater(v7.mSavedFragmentState), ((ViewGroup)v0_2), v7.mSavedFragmentState);
                if(v7.mView != null) {
                    v7.mInnerView = v7.mView;
                    v7.mView.setSaveFromParentEnabled(false);
                    if(v0_2 != null) {
                        ((ViewGroup)v0_2).addView(v7.mView);
                    }

                    if(v7.mHidden) {
                        v7.mView.setVisibility(8);
                    }

                    v7.onViewCreated(v7.mView, v7.mSavedFragmentState);
                    v6.dispatchOnFragmentViewCreated(v7, v7.mView, v7.mSavedFragmentState, false);
                    if(v7.mView.getVisibility() != 0 || v7.mContainer == null) {
                        v8 = false;
                    }
                    else {
                    }

                    v7.mIsNewlyAdded = v8;
                }
                else {
                    v7.mInnerView = v13;
                }
            }

            v7.performActivityCreated(v7.mSavedFragmentState);
            v6.dispatchOnFragmentActivityCreated(v7, v7.mSavedFragmentState, false);
            if(v7.mView != null) {
                v7.restoreViewState(v7.mSavedFragmentState);
            }

            v7.mSavedFragmentState = ((Bundle)v13);
        }

    label_294:
        if(v11 > v12) {
            v7.mState = v10;
        }

    label_296:
        if(v11 > v10) {
            if(FragmentManagerImpl.DEBUG) {
                Log.v("FragmentManager", "moveto STARTED: " + v7);
            }

            arg16.performStart();
            v6.dispatchOnFragmentStarted(v7, false);
        }

    label_309:
        if(v11 <= v9) {
            goto label_472;
        }

        if(FragmentManagerImpl.DEBUG) {
            Log.v("FragmentManager", "moveto RESUMED: " + v7);
        }

        arg16.performResume();
        v6.dispatchOnFragmentResumed(v7, false);
        v7.mSavedFragmentState = ((Bundle)v13);
        v7.mSavedViewState = ((SparseArray)v13);
        goto label_472;
    label_325:
        if(v7.mState <= v11) {
            goto label_472;
        }

        switch(v7.mState) {
            case 1: {
                goto label_424;
            }
            case 2: {
                goto label_369;
            }
            case 3: {
                goto label_357;
            }
            case 4: {
                goto label_344;
            }
            case 5: {
                goto label_330;
            }
        }

        goto label_472;
    label_330:
        if(v11 < 5) {
            if(FragmentManagerImpl.DEBUG) {
                Log.v("FragmentManager", "movefrom RESUMED: " + v7);
            }

            arg16.performPause();
            v6.dispatchOnFragmentPaused(v7, false);
        }

    label_344:
        if(v11 < v9) {
            if(FragmentManagerImpl.DEBUG) {
                Log.v("FragmentManager", "movefrom STARTED: " + v7);
            }

            arg16.performStop();
            v6.dispatchOnFragmentStopped(v7, false);
        }

    label_357:
        if(v11 < v10) {
            if(FragmentManagerImpl.DEBUG) {
                Log.v("FragmentManager", "movefrom STOPPED: " + v7);
            }

            arg16.performReallyStop();
        }

    label_369:
        if(v11 < v12) {
            if(FragmentManagerImpl.DEBUG) {
                Log.v("FragmentManager", "movefrom ACTIVITY_CREATED: " + v7);
            }

            if(v7.mView != null && (v6.mHost.onShouldSaveFragmentState(v7)) && v7.mSavedViewState == null) {
                this.saveFragmentViewState(arg16);
            }

            arg16.performDestroyView();
            v6.dispatchOnFragmentViewDestroyed(v7, false);
            if(v7.mView != null && v7.mContainer != null) {
                v7.mView.clearAnimation();
                v7.mContainer.endViewTransition(v7.mView);
                AnimationOrAnimator v0_4 = v6.mCurState <= 0 || (v6.mDestroyed) || v7.mView.getVisibility() != 0 || v7.mPostponedAlpha < 0f ? ((AnimationOrAnimator)v13) : v6.loadAnimation(v7, arg18, false, arg19);
                v7.mPostponedAlpha = 0f;
                if(v0_4 != null) {
                    v6.animateRemoveFragment(v7, v0_4, v11);
                }

                v7.mContainer.removeView(v7.mView);
            }

            v7.mContainer = ((ViewGroup)v13);
            v7.mView = v13;
            v7.mInnerView = v13;
            v7.mInLayout = false;
        }

    label_424:
        if(v11 < 1) {
            if(v6.mDestroyed) {
                if(arg16.getAnimatingAway() != null) {
                    v0_2 = arg16.getAnimatingAway();
                    v7.setAnimatingAway(v13);
                    v0_2.clearAnimation();
                }
                else if(arg16.getAnimator() != null) {
                    Animator v0_5 = arg16.getAnimator();
                    v7.setAnimator(((Animator)v13));
                    v0_5.cancel();
                }
            }

            if(arg16.getAnimatingAway() == null) {
                if(arg16.getAnimator() != null) {
                }
                else {
                    if(FragmentManagerImpl.DEBUG) {
                        Log.v("FragmentManager", "movefrom CREATED: " + v7);
                    }

                    if(!v7.mRetaining) {
                        arg16.performDestroy();
                        v6.dispatchOnFragmentDestroyed(v7, false);
                    }
                    else {
                        v7.mState = 0;
                    }

                    arg16.performDetach();
                    v6.dispatchOnFragmentDetached(v7, false);
                    if(arg20) {
                        goto label_472;
                    }

                    if(!v7.mRetaining) {
                        this.makeInactive(arg16);
                        goto label_472;
                    }

                    v7.mHost = ((FragmentHostCallback)v13);
                    v7.mParentFragment = ((Fragment)v13);
                    v7.mFragmentManager = ((FragmentManagerImpl)v13);
                    goto label_472;
                }
            }

            v7.setStateAfterAnimating(v11);
        }
        else {
        label_472:
            int v8_1 = v11;
        }

        if(v7.mState != (((int)v8))) {
            Log.w("FragmentManager", "moveToState: Fragment state for " + v7 + " not updated inline; " + "expected state " + (((int)v8)) + " found " + v7.mState);
            v7.mState = ((int)v8);
        }
    }

    void moveToState(int arg5, boolean arg6) {
        Object v2;
        if(this.mHost == null && arg5 != 0) {
            throw new IllegalStateException("No activity");
        }

        if(!arg6 && arg5 == this.mCurState) {
            return;
        }

        this.mCurState = arg5;
        if(this.mActive != null) {
            arg5 = this.mAdded.size();
            int v0 = 0;
            int v1 = 0;
            while(v0 < arg5) {
                v2 = this.mAdded.get(v0);
                this.moveFragmentToExpectedState(((Fragment)v2));
                if(((Fragment)v2).mLoaderManager != null) {
                    v1 |= ((Fragment)v2).mLoaderManager.hasRunningLoaders();
                }

                ++v0;
            }

            arg5 = this.mActive.size();
            for(v0 = 0; v0 < arg5; ++v0) {
                v2 = this.mActive.valueAt(v0);
                if(v2 != null && ((((Fragment)v2).mRemoving) || (((Fragment)v2).mDetached)) && !((Fragment)v2).mIsNewlyAdded) {
                    this.moveFragmentToExpectedState(((Fragment)v2));
                    if(((Fragment)v2).mLoaderManager == null) {
                        goto label_49;
                    }

                    v1 |= ((Fragment)v2).mLoaderManager.hasRunningLoaders();
                }

            label_49:
            }

            if(v1 == 0) {
                this.startPendingDeferredFragments();
            }

            if(!this.mNeedMenuInvalidate) {
                return;
            }

            if(this.mHost == null) {
                return;
            }

            if(this.mCurState != 5) {
                return;
            }

            this.mHost.onSupportInvalidateOptionsMenu();
            this.mNeedMenuInvalidate = false;
        }
    }

    void moveToState(Fragment arg7) {
        this.moveToState(arg7, this.mCurState, 0, 0, false);
    }

    public void noteStateNotSaved() {
        this.mSavedNonConfig = null;
        int v0 = 0;
        this.mStateSaved = false;
        int v1 = this.mAdded.size();
        while(v0 < v1) {
            Object v2 = this.mAdded.get(v0);
            if(v2 != null) {
                ((Fragment)v2).noteStateNotSaved();
            }

            ++v0;
        }
    }

    public View onCreateView(View arg14, String arg15, Context arg16, AttributeSet arg17) {
        Fragment v11_1;
        StringBuilder v2_2;
        FragmentManagerImpl v6 = this;
        Context v0 = arg16;
        AttributeSet v1 = arg17;
        View v3 = null;
        if(!"fragment".equals(arg15)) {
            return v3;
        }

        String v2 = v1.getAttributeValue(((String)v3), "class");
        TypedArray v4 = v0.obtainStyledAttributes(v1, FragmentTag.Fragment);
        int v5 = 0;
        if(v2 == null) {
            v2 = v4.getString(0);
        }

        String v7 = v2;
        int v2_1 = -1;
        int v9 = v4.getResourceId(1, v2_1);
        String v10 = v4.getString(2);
        v4.recycle();
        if(!Fragment.isSupportFragmentClass(v6.mHost.getContext(), v7)) {
            return v3;
        }

        if(arg14 != null) {
            v5 = arg14.getId();
        }

        if(v5 == v2_1 && v9 == v2_1 && v10 == null) {
            v2_2 = new StringBuilder();
            v2_2.append(arg17.getPositionDescription());
            v2_2.append(": Must specify unique android:id, android:tag, or have a parent with an id for ");
            v2_2.append(v7);
            throw new IllegalArgumentException(v2_2.toString());
        }

        Fragment v4_1 = v9 != v2_1 ? v6.findFragmentById(v9) : ((Fragment)v3);
        if(v4_1 == null && v10 != null) {
            v4_1 = v6.findFragmentByTag(v10);
        }

        if(v4_1 == null && v5 != v2_1) {
            v4_1 = v6.findFragmentById(v5);
        }

        if(FragmentManagerImpl.DEBUG) {
            Log.v("FragmentManager", "onCreateView: id=0x" + Integer.toHexString(v9) + " fname=" + v7 + " existing=" + v4_1);
        }

        if(v4_1 == null) {
            Fragment v0_1 = v6.mContainer.instantiate(v0, v7, ((Bundle)v3));
            v0_1.mFromLayout = true;
            v2_1 = v9 != 0 ? v9 : v5;
            v0_1.mFragmentId = v2_1;
            v0_1.mContainerId = v5;
            v0_1.mTag = v10;
            v0_1.mInLayout = true;
            v0_1.mFragmentManager = v6;
            v0_1.mHost = v6.mHost;
            v0_1.onInflate(v6.mHost.getContext(), v1, v0_1.mSavedFragmentState);
            v6.addFragment(v0_1, true);
            v11_1 = v0_1;
        }
        else {
            if(v4_1.mInLayout) {
                v2_2 = new StringBuilder();
                v2_2.append(arg17.getPositionDescription());
                v2_2.append(": Duplicate id 0x");
                v2_2.append(Integer.toHexString(v9));
                v2_2.append(", tag ");
                v2_2.append(v10);
                v2_2.append(", or parent id 0x");
                v2_2.append(Integer.toHexString(v5));
                v2_2.append(" with another fragment for ");
                v2_2.append(v7);
                throw new IllegalArgumentException(v2_2.toString());
            }

            v4_1.mInLayout = true;
            v4_1.mHost = v6.mHost;
            if(!v4_1.mRetaining) {
                v4_1.onInflate(v6.mHost.getContext(), v1, v4_1.mSavedFragmentState);
            }

            v11_1 = v4_1;
        }

        if(v6.mCurState >= 1 || !v11_1.mFromLayout) {
            v6.moveToState(v11_1);
        }
        else {
            v6.moveToState(v11_1, 1, 0, 0, false);
        }

        if(v11_1.mView == null) {
            StringBuilder v1_1 = new StringBuilder();
            v1_1.append("Fragment ");
            v1_1.append(v7);
            v1_1.append(" did not create a view.");
            throw new IllegalStateException(v1_1.toString());
        }

        if(v9 != 0) {
            v11_1.mView.setId(v9);
        }

        if(v11_1.mView.getTag() == null) {
            v11_1.mView.setTag(v10);
        }

        return v11_1.mView;
    }

    public View onCreateView(String arg2, Context arg3, AttributeSet arg4) {
        return this.onCreateView(null, arg2, arg3, arg4);
    }

    public void performPendingDeferredStart(Fragment arg8) {
        if(arg8.mDeferStart) {
            if(this.mExecutingActions) {
                this.mHavePendingDeferredStart = true;
                return;
            }
            else {
                arg8.mDeferStart = false;
                this.moveToState(arg8, this.mCurState, 0, 0, false);
            }
        }
    }

    public void popBackStack() {
        this.enqueueAction(new PopBackStackState(this, null, -1, 0), false);
    }

    public void popBackStack(int arg3, int arg4) {
        if(arg3 < 0) {
            StringBuilder v0 = new StringBuilder();
            v0.append("Bad id: ");
            v0.append(arg3);
            throw new IllegalArgumentException(v0.toString());
        }

        this.enqueueAction(new PopBackStackState(this, null, arg3, arg4), false);
    }

    public void popBackStack(String arg3, int arg4) {
        this.enqueueAction(new PopBackStackState(this, arg3, -1, arg4), false);
    }

    private boolean popBackStackImmediate(String arg9, int arg10, int arg11) {
        this.execPendingActions();
        this.ensureExecReady(true);
        if(this.mPrimaryNav != null && arg10 < 0 && arg9 == null) {
            FragmentManager v1 = this.mPrimaryNav.peekChildFragmentManager();
            if(v1 != null && (v1.popBackStackImmediate())) {
                return 1;
            }
        }

        boolean v9 = this.popBackStackState(this.mTmpRecords, this.mTmpIsPop, arg9, arg10, arg11);
        if(v9) {
            this.mExecutingActions = true;
            try {
                this.removeRedundantOperationsAndExecute(this.mTmpRecords, this.mTmpIsPop);
            }
            catch(Throwable v9_1) {
                this.cleanupExec();
                throw v9_1;
            }

            this.cleanupExec();
        }

        this.doPendingDeferredStart();
        this.burpActive();
        return v9;
    }

    public boolean popBackStackImmediate() {
        this.checkStateLoss();
        return this.popBackStackImmediate(null, -1, 0);
    }

    public boolean popBackStackImmediate(int arg3, int arg4) {
        this.checkStateLoss();
        this.execPendingActions();
        if(arg3 < 0) {
            StringBuilder v0 = new StringBuilder();
            v0.append("Bad id: ");
            v0.append(arg3);
            throw new IllegalArgumentException(v0.toString());
        }

        return this.popBackStackImmediate(null, arg3, arg4);
    }

    public boolean popBackStackImmediate(String arg2, int arg3) {
        this.checkStateLoss();
        return this.popBackStackImmediate(arg2, -1, arg3);
    }

    boolean popBackStackState(ArrayList arg6, ArrayList arg7, String arg8, int arg9, int arg10) {
        int v8;
        int v2;
        if(this.mBackStack == null) {
            return 0;
        }

        if(arg8 != null || arg9 >= 0 || (arg10 & 1) != 0) {
            if(arg8 != null || arg9 >= 0) {
                for(v2 = this.mBackStack.size() - 1; v2 >= 0; --v2) {
                    Object v3 = this.mBackStack.get(v2);
                    if(arg8 != null && (arg8.equals(((BackStackRecord)v3).getName()))) {
                        break;
                    }

                    if(arg9 >= 0 && arg9 == ((BackStackRecord)v3).mIndex) {
                        break;
                    }
                }

                if(v2 < 0) {
                    return 0;
                }

                if((arg10 & 1) == 0) {
                    goto label_60;
                }

                --v2;
                while(v2 >= 0) {
                    Object v10 = this.mBackStack.get(v2);
                    if(arg8 == null || !arg8.equals(((BackStackRecord)v10).getName())) {
                        if(arg9 < 0) {
                        }
                        else if(arg9 == ((BackStackRecord)v10).mIndex) {
                            goto label_58;
                        }

                        break;
                    }

                label_58:
                    --v2;
                }
            }
            else {
                v2 = -1;
            }

        label_60:
            if(v2 == this.mBackStack.size() - 1) {
                return 0;
            }

            for(v8 = this.mBackStack.size() - 1; v8 > v2; --v8) {
                arg6.add(this.mBackStack.remove(v8));
                arg7.add(Boolean.valueOf(true));
            }
        }
        else {
            v8 = this.mBackStack.size() - 1;
            if(v8 < 0) {
                return 0;
            }
            else {
                arg6.add(this.mBackStack.remove(v8));
                arg7.add(Boolean.valueOf(true));
            }
        }

        return 1;
    }

    private int postponePostponableTransactions(ArrayList arg8, ArrayList arg9, int arg10, int arg11, ArraySet arg12) {
        int v0 = arg11 - 1;
        int v1 = arg11;
        while(v0 >= arg10) {
            Object v2 = arg8.get(v0);
            boolean v3 = arg9.get(v0).booleanValue();
            int v4 = !((BackStackRecord)v2).isPostponed() || (((BackStackRecord)v2).interactsWith(arg8, v0 + 1, arg11)) ? 0 : 1;
            if(v4 != 0) {
                if(this.mPostponedTransactions == null) {
                    this.mPostponedTransactions = new ArrayList();
                }

                StartEnterTransitionListener v4_1 = new StartEnterTransitionListener(((BackStackRecord)v2), v3);
                this.mPostponedTransactions.add(v4_1);
                ((BackStackRecord)v2).setOnStartPostponedListener(((OnStartEnterTransitionListener)v4_1));
                if(v3) {
                    ((BackStackRecord)v2).executeOps();
                }
                else {
                    ((BackStackRecord)v2).executePopOps(false);
                }

                --v1;
                if(v0 != v1) {
                    arg8.remove(v0);
                    arg8.add(v1, v2);
                }

                this.addAddedFragments(arg12);
            }

            --v0;
        }

        return v1;
    }

    public void putFragment(Bundle arg4, String arg5, Fragment arg6) {
        if(arg6.mIndex < 0) {
            StringBuilder v1 = new StringBuilder();
            v1.append("Fragment ");
            v1.append(arg6);
            v1.append(" is not currently in the FragmentManager");
            this.throwException(new IllegalStateException(v1.toString()));
        }

        arg4.putInt(arg5, arg6.mIndex);
    }

    public void registerFragmentLifecycleCallbacks(FragmentLifecycleCallbacks arg3, boolean arg4) {
        this.mLifecycleCallbacks.add(new Pair(arg3, Boolean.valueOf(arg4)));
    }

    public void removeFragment(Fragment arg4) {
        if(FragmentManagerImpl.DEBUG) {
            Log.v("FragmentManager", "remove: " + arg4 + " nesting=" + arg4.mBackStackNesting);
        }

        int v0 = arg4.isInBackStack() ^ 1;
        if(!arg4.mDetached || v0 != 0) {
            ArrayList v0_1 = this.mAdded;
            __monitor_enter(v0_1);
            try {
                this.mAdded.remove(arg4);
                __monitor_exit(v0_1);
            }
            catch(Throwable v4) {
                try {
                label_35:
                    __monitor_exit(v0_1);
                }
                catch(Throwable v4) {
                    goto label_35;
                }

                throw v4;
            }

            if((arg4.mHasMenu) && (arg4.mMenuVisible)) {
                this.mNeedMenuInvalidate = true;
            }

            arg4.mAdded = false;
            arg4.mRemoving = true;
        }
    }

    public void removeOnBackStackChangedListener(OnBackStackChangedListener arg2) {
        if(this.mBackStackChangeListeners != null) {
            this.mBackStackChangeListeners.remove(arg2);
        }
    }

    private void removeRedundantOperationsAndExecute(ArrayList arg5, ArrayList arg6) {
        if(arg5 != null) {
            if(arg5.isEmpty()) {
            }
            else {
                if(arg6 != null) {
                    if(arg5.size() != arg6.size()) {
                    }
                    else {
                        this.executePostponedTransaction(arg5, arg6);
                        int v0 = arg5.size();
                        int v1 = 0;
                        int v2 = 0;
                        while(v1 < v0) {
                            if(!arg5.get(v1).mReorderingAllowed) {
                                if(v2 != v1) {
                                    this.executeOpsTogether(arg5, arg6, v2, v1);
                                }

                                v2 = v1 + 1;
                                if(arg6.get(v1).booleanValue()) {
                                    while(v2 < v0) {
                                        if(!arg6.get(v2).booleanValue()) {
                                            break;
                                        }

                                        if(arg5.get(v2).mReorderingAllowed) {
                                            break;
                                        }

                                        ++v2;
                                    }
                                }

                                this.executeOpsTogether(arg5, arg6, v1, v2);
                                v1 = v2 - 1;
                            }

                            ++v1;
                        }

                        if(v2 != v0) {
                            this.executeOpsTogether(arg5, arg6, v2, v0);
                        }

                        return;
                    }
                }

                throw new IllegalStateException("Internal error with the back stack records");
            }
        }
    }

    void reportBackStackChanged() {
        if(this.mBackStackChangeListeners != null) {
            int v0;
            for(v0 = 0; v0 < this.mBackStackChangeListeners.size(); ++v0) {
                this.mBackStackChangeListeners.get(v0).onBackStackChanged();
            }
        }
    }

    void restoreAllState(Parcelable arg12, FragmentManagerNonConfig arg13) {
        int v13_1;
        FragmentManagerNonConfig v5_2;
        List v3;
        if(arg12 == null) {
            return;
        }

        if(((FragmentManagerState)arg12).mActive == null) {
            return;
        }

        SparseArray v0 = null;
        if(arg13 != null) {
            List v2 = arg13.getFragments();
            v3 = arg13.getChildNonConfigs();
            int v4 = v2 != null ? v2.size() : 0;
            int v5;
            for(v5 = 0; v5 < v4; ++v5) {
                Object v6 = v2.get(v5);
                if(FragmentManagerImpl.DEBUG) {
                    Log.v("FragmentManager", "restoreAllState: re-attaching retained " + v6);
                }

                int v7;
                for(v7 = 0; v7 < ((FragmentManagerState)arg12).mActive.length; ++v7) {
                    if(((FragmentManagerState)arg12).mActive[v7].mIndex == ((Fragment)v6).mIndex) {
                        break;
                    }
                }

                if(v7 == ((FragmentManagerState)arg12).mActive.length) {
                    StringBuilder v9 = new StringBuilder();
                    v9.append("Could not find active fragment with index ");
                    v9.append(((Fragment)v6).mIndex);
                    this.throwException(new IllegalStateException(v9.toString()));
                }

                FragmentState v7_1 = ((FragmentManagerState)arg12).mActive[v7];
                v7_1.mInstance = ((Fragment)v6);
                ((Fragment)v6).mSavedViewState = v0;
                ((Fragment)v6).mBackStackNesting = 0;
                ((Fragment)v6).mInLayout = false;
                ((Fragment)v6).mAdded = false;
                ((Fragment)v6).mTarget = ((Fragment)v0);
                if(v7_1.mSavedFragmentState != null) {
                    v7_1.mSavedFragmentState.setClassLoader(this.mHost.getContext().getClassLoader());
                    ((Fragment)v6).mSavedViewState = v7_1.mSavedFragmentState.getSparseParcelableArray("android:view_state");
                    ((Fragment)v6).mSavedFragmentState = v7_1.mSavedFragmentState;
                }
            }
        }
        else {
            v3 = ((List)v0);
        }

        this.mActive = new SparseArray(((FragmentManagerState)arg12).mActive.length);
        int v2_1;
        for(v2_1 = 0; v2_1 < ((FragmentManagerState)arg12).mActive.length; ++v2_1) {
            FragmentState v4_1 = ((FragmentManagerState)arg12).mActive[v2_1];
            if(v4_1 != null) {
                if(v3 == null || v2_1 >= v3.size()) {
                    v5_2 = ((FragmentManagerNonConfig)v0);
                }
                else {
                    Object v5_1 = v3.get(v2_1);
                }

                Fragment v5_3 = v4_1.instantiate(this.mHost, this.mContainer, this.mParent, v5_2);
                if(FragmentManagerImpl.DEBUG) {
                    Log.v("FragmentManager", "restoreAllState: active #" + v2_1 + ": " + v5_3);
                }

                this.mActive.put(v5_3.mIndex, v5_3);
                v4_1.mInstance = ((Fragment)v0);
            }
        }

        if(arg13 != null) {
            List v13 = arg13.getFragments();
            v2_1 = v13 != null ? v13.size() : 0;
            int v3_1;
            for(v3_1 = 0; v3_1 < v2_1; ++v3_1) {
                Object v4_2 = v13.get(v3_1);
                if(((Fragment)v4_2).mTargetIndex >= 0) {
                    ((Fragment)v4_2).mTarget = this.mActive.get(((Fragment)v4_2).mTargetIndex);
                    if(((Fragment)v4_2).mTarget == null) {
                        Log.w("FragmentManager", "Re-attaching retained fragment " + v4_2 + " target no longer exists: " + ((Fragment)v4_2).mTargetIndex);
                    }
                }
            }
        }

        this.mAdded.clear();
        if(((FragmentManagerState)arg12).mAdded != null) {
            v13_1 = 0;
            while(true) {
                if(v13_1 < ((FragmentManagerState)arg12).mAdded.length) {
                    Object v2_2 = this.mActive.get(((FragmentManagerState)arg12).mAdded[v13_1]);
                    if(v2_2 == null) {
                        StringBuilder v4_3 = new StringBuilder();
                        v4_3.append("No instantiated fragment for index #");
                        v4_3.append(((FragmentManagerState)arg12).mAdded[v13_1]);
                        this.throwException(new IllegalStateException(v4_3.toString()));
                    }

                    ((Fragment)v2_2).mAdded = true;
                    if(FragmentManagerImpl.DEBUG) {
                        Log.v("FragmentManager", "restoreAllState: added #" + v13_1 + ": " + v2_2);
                    }

                    if(this.mAdded.contains(v2_2)) {
                        throw new IllegalStateException("Already added!");
                    }

                    ArrayList v3_2 = this.mAdded;
                    __monitor_enter(v3_2);
                    try {
                        this.mAdded.add(v2_2);
                        __monitor_exit(v3_2);
                        ++v13_1;
                        continue;
                    label_201:
                        __monitor_exit(v3_2);
                        break;
                    }
                    catch(Throwable v12) {
                        goto label_201;
                    }
                }

                goto label_203;
            }

            throw v12;
        }

    label_203:
        if(((FragmentManagerState)arg12).mBackStack != null) {
            this.mBackStack = new ArrayList(((FragmentManagerState)arg12).mBackStack.length);
            for(v13_1 = 0; v13_1 < ((FragmentManagerState)arg12).mBackStack.length; ++v13_1) {
                BackStackRecord v0_1 = ((FragmentManagerState)arg12).mBackStack[v13_1].instantiate(this);
                if(FragmentManagerImpl.DEBUG) {
                    Log.v("FragmentManager", "restoreAllState: back stack #" + v13_1 + " (index " + v0_1.mIndex + "): " + v0_1);
                    PrintWriter v3_4 = new PrintWriter(new LogWriter("FragmentManager"));
                    v0_1.dump("  ", v3_4, false);
                    v3_4.close();
                }

                this.mBackStack.add(v0_1);
                if(v0_1.mIndex >= 0) {
                    this.setBackStackIndex(v0_1.mIndex, v0_1);
                }
            }
        }
        else {
            this.mBackStack = ((ArrayList)v0);
        }

        if(((FragmentManagerState)arg12).mPrimaryNavActiveIndex >= 0) {
            this.mPrimaryNav = this.mActive.get(((FragmentManagerState)arg12).mPrimaryNavActiveIndex);
        }

        this.mNextFragmentIndex = ((FragmentManagerState)arg12).mNextFragmentIndex;
    }

    FragmentManagerNonConfig retainNonConfig() {
        FragmentManagerImpl.setRetaining(this.mSavedNonConfig);
        return this.mSavedNonConfig;
    }

    public static int reverseTransit(int arg3) {
        int v0 = 0x2002;
        int v1 = 0x1003;
        if(arg3 != 0x1001) {
            if(arg3 == v1) {
                v0 = 0x1003;
            }
            else if(arg3 != v0) {
                v0 = 0;
            }
            else {
                v0 = 0x1001;
            }
        }

        return v0;
    }

    Parcelable saveAllState() {
        int[] v2_1;
        this.forcePostponedTransactions();
        this.endAnimatingAwayFragments();
        this.execPendingActions();
        this.mStateSaved = true;
        FragmentManagerNonConfig v1 = null;
        this.mSavedNonConfig = v1;
        if(this.mActive != null) {
            if(this.mActive.size() <= 0) {
            }
            else {
                int v2 = this.mActive.size();
                FragmentState[] v3 = new FragmentState[v2];
                int v4 = 0;
                int v5 = 0;
                int v6 = 0;
                while(v5 < v2) {
                    Object v7 = this.mActive.valueAt(v5);
                    if(v7 != null) {
                        if(((Fragment)v7).mIndex < 0) {
                            StringBuilder v8 = new StringBuilder();
                            v8.append("Failure saving state: active ");
                            v8.append(v7);
                            v8.append(" has cleared index: ");
                            v8.append(((Fragment)v7).mIndex);
                            this.throwException(new IllegalStateException(v8.toString()));
                        }

                        FragmentState v6_1 = new FragmentState(((Fragment)v7));
                        v3[v5] = v6_1;
                        if(((Fragment)v7).mState <= 0 || v6_1.mSavedFragmentState != null) {
                            v6_1.mSavedFragmentState = ((Fragment)v7).mSavedFragmentState;
                        }
                        else {
                            v6_1.mSavedFragmentState = this.saveFragmentBasicState(((Fragment)v7));
                            if(((Fragment)v7).mTarget != null) {
                                if(((Fragment)v7).mTarget.mIndex < 0) {
                                    StringBuilder v9 = new StringBuilder();
                                    v9.append("Failure saving state: ");
                                    v9.append(v7);
                                    v9.append(" has target not in fragment manager: ");
                                    v9.append(((Fragment)v7).mTarget);
                                    this.throwException(new IllegalStateException(v9.toString()));
                                }

                                if(v6_1.mSavedFragmentState == null) {
                                    v6_1.mSavedFragmentState = new Bundle();
                                }

                                this.putFragment(v6_1.mSavedFragmentState, "android:target_state", ((Fragment)v7).mTarget);
                                if(((Fragment)v7).mTargetRequestCode == 0) {
                                    goto label_83;
                                }

                                v6_1.mSavedFragmentState.putInt("android:target_req_state", ((Fragment)v7).mTargetRequestCode);
                            }
                        }

                    label_83:
                        if(FragmentManagerImpl.DEBUG) {
                            Log.v("FragmentManager", "Saved state of " + v7 + ": " + v6_1.mSavedFragmentState);
                        }

                        v6 = 1;
                    }

                    ++v5;
                }

                if(v6 == 0) {
                    if(FragmentManagerImpl.DEBUG) {
                        Log.v("FragmentManager", "saveAllState: no fragments!");
                    }

                    return ((Parcelable)v1);
                }

                int v0 = this.mAdded.size();
                if(v0 > 0) {
                    v2_1 = new int[v0];
                    v5 = 0;
                    goto label_112;
                }
                else {
                    v2_1 = ((int[])v1);
                    goto label_152;
                label_112:
                    while(v5 < v0) {
                        v2_1[v5] = this.mAdded.get(v5).mIndex;
                        if(v2_1[v5] < 0) {
                            StringBuilder v7_1 = new StringBuilder();
                            v7_1.append("Failure saving state: active ");
                            v7_1.append(this.mAdded.get(v5));
                            v7_1.append(" has cleared index: ");
                            v7_1.append(v2_1[v5]);
                            this.throwException(new IllegalStateException(v7_1.toString()));
                        }

                        if(FragmentManagerImpl.DEBUG) {
                            Log.v("FragmentManager", "saveAllState: adding fragment #" + v5 + ": " + this.mAdded.get(v5));
                        }

                        ++v5;
                    }
                }

            label_152:
                if(this.mBackStack != null) {
                    v0 = this.mBackStack.size();
                    if(v0 > 0) {
                        BackStackState[] v1_1 = new BackStackState[v0];
                        while(v4 < v0) {
                            v1_1[v4] = new BackStackState(this.mBackStack.get(v4));
                            if(FragmentManagerImpl.DEBUG) {
                                Log.v("FragmentManager", "saveAllState: adding back stack #" + v4 + ": " + this.mBackStack.get(v4));
                            }

                            ++v4;
                        }
                    }
                }

                FragmentManagerState v0_1 = new FragmentManagerState();
                v0_1.mActive = v3;
                v0_1.mAdded = v2_1;
                v0_1.mBackStack = ((BackStackState[])v1);
                if(this.mPrimaryNav != null) {
                    v0_1.mPrimaryNavActiveIndex = this.mPrimaryNav.mIndex;
                }

                v0_1.mNextFragmentIndex = this.mNextFragmentIndex;
                this.saveNonConfig();
                return ((Parcelable)v0_1);
            }
        }

        return ((Parcelable)v1);
    }

    Bundle saveFragmentBasicState(Fragment arg4) {
        Bundle v0;
        if(this.mStateBundle == null) {
            this.mStateBundle = new Bundle();
        }

        arg4.performSaveInstanceState(this.mStateBundle);
        this.dispatchOnFragmentSaveInstanceState(arg4, this.mStateBundle, false);
        Bundle v1 = null;
        if(!this.mStateBundle.isEmpty()) {
            v0 = this.mStateBundle;
            this.mStateBundle = v1;
        }
        else {
            v0 = v1;
        }

        if(arg4.mView != null) {
            this.saveFragmentViewState(arg4);
        }

        if(arg4.mSavedViewState != null) {
            if(v0 == null) {
                v0 = new Bundle();
            }

            v0.putSparseParcelableArray("android:view_state", arg4.mSavedViewState);
        }

        if(!arg4.mUserVisibleHint) {
            if(v0 == null) {
                v0 = new Bundle();
            }

            v0.putBoolean("android:user_visible_hint", arg4.mUserVisibleHint);
        }

        return v0;
    }

    public SavedState saveFragmentInstanceState(Fragment arg4) {
        if(arg4.mIndex < 0) {
            StringBuilder v1 = new StringBuilder();
            v1.append("Fragment ");
            v1.append(arg4);
            v1.append(" is not currently in the FragmentManager");
            this.throwException(new IllegalStateException(v1.toString()));
        }

        SavedState v1_1 = null;
        if(arg4.mState > 0) {
            Bundle v4 = this.saveFragmentBasicState(arg4);
            if(v4 != null) {
                v1_1 = new SavedState(v4);
            }

            return v1_1;
        }

        return v1_1;
    }

    void saveFragmentViewState(Fragment arg3) {
        if(arg3.mInnerView == null) {
            return;
        }

        if(this.mStateArray == null) {
            this.mStateArray = new SparseArray();
        }
        else {
            this.mStateArray.clear();
        }

        arg3.mInnerView.saveHierarchyState(this.mStateArray);
        if(this.mStateArray.size() > 0) {
            arg3.mSavedViewState = this.mStateArray;
            this.mStateArray = null;
        }
    }

    void saveNonConfig() {
        FragmentManagerNonConfig v5_1;
        ArrayList v4;
        ArrayList v3;
        Object v1 = null;
        if(this.mActive != null) {
            v3 = ((ArrayList)v1);
            v4 = v3;
            int v2;
            for(v2 = 0; v2 < this.mActive.size(); ++v2) {
                Object v5 = this.mActive.valueAt(v2);
                if(v5 != null) {
                    if(((Fragment)v5).mRetainInstance) {
                        if(v3 == null) {
                            v3 = new ArrayList();
                        }

                        v3.add(v5);
                        int v6 = ((Fragment)v5).mTarget != null ? ((Fragment)v5).mTarget.mIndex : -1;
                        ((Fragment)v5).mTargetIndex = v6;
                        if(!FragmentManagerImpl.DEBUG) {
                            goto label_36;
                        }

                        Log.v("FragmentManager", "retainNonConfig: keeping retained " + v5);
                    }

                label_36:
                    if(((Fragment)v5).mChildFragmentManager != null) {
                        ((Fragment)v5).mChildFragmentManager.saveNonConfig();
                        v5_1 = ((Fragment)v5).mChildFragmentManager.mSavedNonConfig;
                    }
                    else {
                        v5_1 = ((Fragment)v5).mChildNonConfig;
                    }

                    if(v4 == null && v5_1 != null) {
                        v4 = new ArrayList(this.mActive.size());
                        for(v6 = 0; v6 < v2; ++v6) {
                            v4.add(v1);
                        }
                    }

                    if(v4 == null) {
                        goto label_57;
                    }

                    v4.add(v5_1);
                }

            label_57:
            }
        }
        else {
            v3 = ((ArrayList)v1);
            v4 = v3;
        }

        this.mSavedNonConfig = v3 != null || v4 != null ? new FragmentManagerNonConfig(((List)v3), ((List)v4)) : ((FragmentManagerNonConfig)v1);
    }

    private void scheduleCommit() {
        __monitor_enter(this);
        try {
            int v1 = 0;
            int v0_1 = this.mPostponedTransactions == null || (this.mPostponedTransactions.isEmpty()) ? 0 : 1;
            if(this.mPendingActions != null && this.mPendingActions.size() == 1) {
                v1 = 1;
            }

            if(v0_1 != 0 || v1 != 0) {
                this.mHost.getHandler().removeCallbacks(this.mExecCommit);
                this.mHost.getHandler().post(this.mExecCommit);
            }

            __monitor_exit(this);
            return;
        label_30:
            __monitor_exit(this);
        }
        catch(Throwable v0) {
            goto label_30;
        }

        throw v0;
    }

    public void setBackStackIndex(int arg5, BackStackRecord arg6) {
        __monitor_enter(this);
        try {
            if(this.mBackStackIndices == null) {
                this.mBackStackIndices = new ArrayList();
            }

            int v0 = this.mBackStackIndices.size();
            if(arg5 < v0) {
                if(FragmentManagerImpl.DEBUG) {
                    Log.v("FragmentManager", "Setting back stack index " + arg5 + " to " + arg6);
                }

                this.mBackStackIndices.set(arg5, arg6);
            }
            else {
                while(v0 < arg5) {
                    this.mBackStackIndices.add(null);
                    if(this.mAvailBackStackIndices == null) {
                        this.mAvailBackStackIndices = new ArrayList();
                    }

                    if(FragmentManagerImpl.DEBUG) {
                        Log.v("FragmentManager", "Adding available back stack index " + v0);
                    }

                    this.mAvailBackStackIndices.add(Integer.valueOf(v0));
                    ++v0;
                }

                if(FragmentManagerImpl.DEBUG) {
                    Log.v("FragmentManager", "Adding back stack index " + arg5 + " with " + arg6);
                }

                this.mBackStackIndices.add(arg6);
            }

            __monitor_exit(this);
            return;
        label_67:
            __monitor_exit(this);
        }
        catch(Throwable v5) {
            goto label_67;
        }

        throw v5;
    }

    private static void setHWLayerAnimListenerIfAlpha(View arg3, AnimationOrAnimator arg4) {
        if(arg3 != null) {
            if(arg4 == null) {
            }
            else {
                if(FragmentManagerImpl.shouldRunOnHWLayer(arg3, arg4)) {
                    if(arg4.animator != null) {
                        arg4.animator.addListener(new AnimatorOnHWLayerIfNeededListener(arg3));
                    }
                    else {
                        Animation$AnimationListener v0 = FragmentManagerImpl.getAnimationListener(arg4.animation);
                        arg3.setLayerType(2, null);
                        arg4.animation.setAnimationListener(new AnimateOnHWLayerIfNeededListener(arg3, v0));
                    }
                }

                return;
            }
        }
    }

    public void setPrimaryNavigationFragment(Fragment arg4) {
        if(arg4 != null) {
            if(this.mActive.get(arg4.mIndex) == arg4) {
                if(arg4.mHost == null) {
                }
                else if(arg4.getFragmentManager() != this) {
                    goto label_9;
                }

                goto label_21;
            }

        label_9:
            StringBuilder v1 = new StringBuilder();
            v1.append("Fragment ");
            v1.append(arg4);
            v1.append(" is not an active fragment of FragmentManager ");
            v1.append(this);
            throw new IllegalArgumentException(v1.toString());
        }

    label_21:
        this.mPrimaryNav = arg4;
    }

    private static void setRetaining(FragmentManagerNonConfig arg3) {
        if(arg3 == null) {
            return;
        }

        List v0 = arg3.getFragments();
        if(v0 != null) {
            Iterator v0_1 = v0.iterator();
            while(v0_1.hasNext()) {
                v0_1.next().mRetaining = true;
            }
        }

        List v3 = arg3.getChildNonConfigs();
        if(v3 != null) {
            Iterator v3_1 = v3.iterator();
            while(v3_1.hasNext()) {
                FragmentManagerImpl.setRetaining(v3_1.next());
            }
        }
    }

    static boolean shouldRunOnHWLayer(View arg3, AnimationOrAnimator arg4) {
        boolean v0 = false;
        if(arg3 != null) {
            if(arg4 == null) {
            }
            else {
                if(Build$VERSION.SDK_INT >= 19 && arg3.getLayerType() == 0 && (ViewCompat.hasOverlappingRendering(arg3)) && (FragmentManagerImpl.modifiesAlpha(arg4))) {
                    v0 = true;
                }

                return v0;
            }
        }

        return 0;
    }

    public void showFragment(Fragment arg4) {
        if(FragmentManagerImpl.DEBUG) {
            Log.v("FragmentManager", "show: " + arg4);
        }

        if(arg4.mHidden) {
            arg4.mHidden = false;
            arg4.mHiddenChanged ^= 1;
        }
    }

    void startPendingDeferredFragments() {
        if(this.mActive == null) {
            return;
        }

        int v0;
        for(v0 = 0; v0 < this.mActive.size(); ++v0) {
            Object v1 = this.mActive.valueAt(v0);
            if(v1 != null) {
                this.performPendingDeferredStart(((Fragment)v1));
            }
        }
    }

    private void throwException(RuntimeException arg6) {
        Log.e("FragmentManager", arg6.getMessage());
        Log.e("FragmentManager", "Activity state:");
        PrintWriter v1 = new PrintWriter(new LogWriter("FragmentManager"));
        FileDescriptor v3 = null;
        if(this.mHost != null) {
            try {
                this.mHost.onDump("  ", v3, v1, new String[0]);
            }
            catch(Exception v0) {
                Log.e("FragmentManager", "Failed dumping state", ((Throwable)v0));
            }

            goto label_33;
        }

        try {
            this.dump("  ", v3, v1, new String[0]);
        }
        catch(Exception v0) {
            Log.e("FragmentManager", "Failed dumping state", ((Throwable)v0));
        }

    label_33:
        throw arg6;
    }

    public String toString() {
        StringBuilder v0 = new StringBuilder(0x80);
        v0.append("FragmentManager{");
        v0.append(Integer.toHexString(System.identityHashCode(this)));
        v0.append(" in ");
        if(this.mParent != null) {
            DebugUtils.buildShortClassTag(this.mParent, v0);
        }
        else {
            DebugUtils.buildShortClassTag(this.mHost, v0);
        }

        v0.append("}}");
        return v0.toString();
    }

    public static int transitToStyleIndex(int arg1, boolean arg2) {
        if(arg1 != 0x1001) {
            if(arg1 != 0x1003) {
                if(arg1 != 0x2002) {
                    arg1 = -1;
                }
                else if(arg2) {
                    arg1 = 3;
                }
                else {
                    arg1 = 4;
                }
            }
            else if(arg2) {
                arg1 = 5;
            }
            else {
                arg1 = 6;
            }
        }
        else if(arg2) {
            arg1 = 1;
        }
        else {
            arg1 = 2;
        }

        return arg1;
    }

    public void unregisterFragmentLifecycleCallbacks(FragmentLifecycleCallbacks arg5) {
        CopyOnWriteArrayList v0 = this.mLifecycleCallbacks;
        __monitor_enter(v0);
        int v1 = 0;
        try {
            int v2 = this.mLifecycleCallbacks.size();
            while(v1 < v2) {
                if(this.mLifecycleCallbacks.get(v1).first == arg5) {
                    this.mLifecycleCallbacks.remove(v1);
                }
                else {
                    ++v1;
                    continue;
                }

                break;
            }

            __monitor_exit(v0);
            return;
        label_18:
            __monitor_exit(v0);
        }
        catch(Throwable v5) {
            goto label_18;
        }

        throw v5;
    }
}

