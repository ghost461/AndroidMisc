package android.support.v4.app;

import android.app.Activity;
import android.arch.lifecycle.Lifecycle$State;
import android.arch.lifecycle.Lifecycle;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender$SendIntentException;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.v4.util.SimpleArrayMap;
import android.support.v4.util.SparseArrayCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Iterator;

public class FragmentActivity extends BaseFragmentActivityApi16 implements OnRequestPermissionsResultCallback, RequestPermissionsRequestCodeValidator {
    class android.support.v4.app.FragmentActivity$1 extends Handler {
        android.support.v4.app.FragmentActivity$1(FragmentActivity arg1) {
            FragmentActivity.this = arg1;
            super();
        }

        public void handleMessage(Message arg2) {
            switch(arg2.what) {
                case 1: {
                    if(!FragmentActivity.this.mStopped) {
                        return;
                    }

                    FragmentActivity.this.doReallyStop(false);
                    break;
                }
                case 2: {
                    FragmentActivity.this.onResumeFragments();
                    FragmentActivity.this.mFragments.execPendingActions();
                    break;
                }
                default: {
                    super.handleMessage(arg2);
                    break;
                }
            }
        }
    }

    class HostCallbacks extends FragmentHostCallback {
        public HostCallbacks(FragmentActivity arg1) {
            FragmentActivity.this = arg1;
            super(arg1);
        }

        public void onAttachFragment(Fragment arg2) {
            FragmentActivity.this.onAttachFragment(arg2);
        }

        public void onDump(String arg2, FileDescriptor arg3, PrintWriter arg4, String[] arg5) {
            FragmentActivity.this.dump(arg2, arg3, arg4, arg5);
        }

        @Nullable public View onFindViewById(int arg2) {
            return FragmentActivity.this.findViewById(arg2);
        }

        public FragmentActivity onGetHost() {
            return FragmentActivity.this;
        }

        public Object onGetHost() {
            return this.onGetHost();
        }

        public LayoutInflater onGetLayoutInflater() {
            return FragmentActivity.this.getLayoutInflater().cloneInContext(FragmentActivity.this);
        }

        public int onGetWindowAnimations() {
            Window v0 = FragmentActivity.this.getWindow();
            int v0_1 = v0 == null ? 0 : v0.getAttributes().windowAnimations;
            return v0_1;
        }

        public boolean onHasView() {
            Window v0 = FragmentActivity.this.getWindow();
            boolean v0_1 = v0 == null || v0.peekDecorView() == null ? false : true;
            return v0_1;
        }

        public boolean onHasWindowAnimations() {
            boolean v0 = FragmentActivity.this.getWindow() != null ? true : false;
            return v0;
        }

        public void onRequestPermissionsFromFragment(@NonNull Fragment arg2, @NonNull String[] arg3, int arg4) {
            FragmentActivity.this.requestPermissionsFromFragment(arg2, arg3, arg4);
        }

        public boolean onShouldSaveFragmentState(Fragment arg1) {
            return FragmentActivity.this.isFinishing() ^ 1;
        }

        public boolean onShouldShowRequestPermissionRationale(@NonNull String arg2) {
            return ActivityCompat.shouldShowRequestPermissionRationale(FragmentActivity.this, arg2);
        }

        public void onStartActivityFromFragment(Fragment arg2, Intent arg3, int arg4) {
            FragmentActivity.this.startActivityFromFragment(arg2, arg3, arg4);
        }

        public void onStartActivityFromFragment(Fragment arg2, Intent arg3, int arg4, @Nullable Bundle arg5) {
            FragmentActivity.this.startActivityFromFragment(arg2, arg3, arg4, arg5);
        }

        public void onStartIntentSenderFromFragment(Fragment arg11, IntentSender arg12, int arg13, @Nullable Intent arg14, int arg15, int arg16, int arg17, Bundle arg18) throws IntentSender$SendIntentException {
            FragmentActivity.this.startIntentSenderFromFragment(arg11, arg12, arg13, arg14, arg15, arg16, arg17, arg18);
        }

        public void onSupportInvalidateOptionsMenu() {
            FragmentActivity.this.supportInvalidateOptionsMenu();
        }
    }

    final class NonConfigurationInstances {
        Object custom;
        FragmentManagerNonConfig fragments;
        SimpleArrayMap loaders;

        NonConfigurationInstances() {
            super();
        }
    }

    static final String ALLOCATED_REQUEST_INDICIES_TAG = "android:support:request_indicies";
    static final String FRAGMENTS_TAG = "android:support:fragments";
    static final int MAX_NUM_PENDING_FRAGMENT_ACTIVITY_RESULTS = 0xFFFE;
    static final int MSG_REALLY_STOPPED = 1;
    static final int MSG_RESUME_PENDING = 2;
    static final String NEXT_CANDIDATE_REQUEST_INDEX_TAG = "android:support:next_request_index";
    static final String REQUEST_FRAGMENT_WHO_TAG = "android:support:request_fragment_who";
    private static final String TAG = "FragmentActivity";
    boolean mCreated;
    final FragmentController mFragments;
    final Handler mHandler;
    int mNextCandidateRequestIndex;
    SparseArrayCompat mPendingFragmentActivityResults;
    boolean mReallyStopped;
    boolean mRequestedPermissionsFromFragment;
    boolean mResumed;
    boolean mRetaining;
    boolean mStopped;

    public FragmentActivity() {
        super();
        this.mHandler = new android.support.v4.app.FragmentActivity$1(this);
        this.mFragments = FragmentController.createController(new HostCallbacks(this));
        this.mStopped = true;
        this.mReallyStopped = true;
    }

    private int allocateRequestIndex(Fragment arg4) {
        int v1 = 0xFFFE;
        if(this.mPendingFragmentActivityResults.size() >= v1) {
            throw new IllegalStateException("Too many pending Fragment activity results.");
        }

        while(this.mPendingFragmentActivityResults.indexOfKey(this.mNextCandidateRequestIndex) >= 0) {
            this.mNextCandidateRequestIndex = (this.mNextCandidateRequestIndex + 1) % v1;
        }

        int v0 = this.mNextCandidateRequestIndex;
        this.mPendingFragmentActivityResults.put(v0, arg4.mWho);
        this.mNextCandidateRequestIndex = (this.mNextCandidateRequestIndex + 1) % v1;
        return v0;
    }

    final View dispatchFragmentsOnCreateView(View arg2, String arg3, Context arg4, AttributeSet arg5) {
        return this.mFragments.onCreateView(arg2, arg3, arg4, arg5);
    }

    void doReallyStop(boolean arg3) {
        if(!this.mReallyStopped) {
            this.mReallyStopped = true;
            this.mRetaining = arg3;
            this.mHandler.removeMessages(1);
            this.onReallyStop();
        }
        else if(arg3) {
            this.mFragments.doLoaderStart();
            this.mFragments.doLoaderStop(true);
        }
    }

    public void dump(String arg3, FileDescriptor arg4, PrintWriter arg5, String[] arg6) {
        super.dump(arg3, arg4, arg5, arg6);
        arg5.print(arg3);
        arg5.print("Local FragmentActivity ");
        arg5.print(Integer.toHexString(System.identityHashCode(this)));
        arg5.println(" State:");
        String v0_1 = arg3 + "  ";
        arg5.print(v0_1);
        arg5.print("mCreated=");
        arg5.print(this.mCreated);
        arg5.print("mResumed=");
        arg5.print(this.mResumed);
        arg5.print(" mStopped=");
        arg5.print(this.mStopped);
        arg5.print(" mReallyStopped=");
        arg5.println(this.mReallyStopped);
        this.mFragments.dumpLoaders(v0_1, arg4, arg5, arg6);
        this.mFragments.getSupportFragmentManager().dump(arg3, arg4, arg5, arg6);
    }

    public Object getLastCustomNonConfigurationInstance() {
        Object v0 = this.getLastNonConfigurationInstance();
        return v0 != null ? ((NonConfigurationInstances)v0).custom : null;
    }

    public Lifecycle getLifecycle() {
        return super.getLifecycle();
    }

    public FragmentManager getSupportFragmentManager() {
        return this.mFragments.getSupportFragmentManager();
    }

    public LoaderManager getSupportLoaderManager() {
        return this.mFragments.getSupportLoaderManager();
    }

    private static void markState(FragmentManager arg2, State arg3) {
        Iterator v2 = arg2.getFragments().iterator();
        while(v2.hasNext()) {
            Object v0 = v2.next();
            if(v0 == null) {
                continue;
            }

            ((Fragment)v0).mLifecycleRegistry.markState(arg3);
            FragmentActivity.markState(((Fragment)v0).getChildFragmentManager(), arg3);
        }
    }

    protected void onActivityResult(int arg4, int arg5, Intent arg6) {
        this.mFragments.noteStateNotSaved();
        int v0 = arg4 >> 16;
        if(v0 != 0) {
            --v0;
            Object v1 = this.mPendingFragmentActivityResults.get(v0);
            this.mPendingFragmentActivityResults.remove(v0);
            if(v1 == null) {
                Log.w("FragmentActivity", "Activity result delivered for unknown Fragment.");
                return;
            }

            Fragment v0_1 = this.mFragments.findFragmentByWho(((String)v1));
            if(v0_1 == null) {
                Log.w("FragmentActivity", "Activity result no fragment exists for who: " + (((String)v1)));
            }
            else {
                v0_1.onActivityResult(arg4 & 0xFFFF, arg5, arg6);
            }

            return;
        }

        super.onActivityResult(arg4, arg5, arg6);
    }

    public void onAttachFragment(Fragment arg1) {
    }

    public void onBackPressed() {
        FragmentManager v0 = this.mFragments.getSupportFragmentManager();
        boolean v1 = v0.isStateSaved();
        if((v1) && Build$VERSION.SDK_INT <= 25) {
            return;
        }

        if((v1) || !v0.popBackStackImmediate()) {
            super.onBackPressed();
        }
    }

    public void onConfigurationChanged(Configuration arg2) {
        super.onConfigurationChanged(arg2);
        this.mFragments.dispatchConfigurationChanged(arg2);
    }

    protected void onCreate(@Nullable Bundle arg7) {
        FragmentManagerNonConfig v1_1;
        Fragment v1 = null;
        this.mFragments.attachHost(v1);
        super.onCreate(arg7);
        Object v0 = this.getLastNonConfigurationInstance();
        if(v0 != null) {
            this.mFragments.restoreLoaderNonConfig(((NonConfigurationInstances)v0).loaders);
        }

        if(arg7 != null) {
            Parcelable v3 = arg7.getParcelable("android:support:fragments");
            FragmentController v4 = this.mFragments;
            if(v0 != null) {
                v1_1 = ((NonConfigurationInstances)v0).fragments;
            }

            v4.restoreAllState(v3, v1_1);
            if(!arg7.containsKey("android:support:next_request_index")) {
                goto label_49;
            }

            this.mNextCandidateRequestIndex = arg7.getInt("android:support:next_request_index");
            int[] v0_1 = arg7.getIntArray("android:support:request_indicies");
            String[] v7 = arg7.getStringArray("android:support:request_fragment_who");
            if(v0_1 != null && v7 != null) {
                if(v0_1.length != v7.length) {
                }
                else {
                    this.mPendingFragmentActivityResults = new SparseArrayCompat(v0_1.length);
                    int v1_2 = 0;
                    while(true) {
                        if(v1_2 < v0_1.length) {
                            this.mPendingFragmentActivityResults.put(v0_1[v1_2], v7[v1_2]);
                            ++v1_2;
                            continue;
                        }
                        else {
                            goto label_49;
                        }
                    }
                }
            }

            Log.w("FragmentActivity", "Invalid requestCode mapping in savedInstanceState.");
        }

    label_49:
        if(this.mPendingFragmentActivityResults == null) {
            this.mPendingFragmentActivityResults = new SparseArrayCompat();
            this.mNextCandidateRequestIndex = 0;
        }

        this.mFragments.dispatchCreate();
    }

    public boolean onCreatePanelMenu(int arg3, Menu arg4) {
        if(arg3 == 0) {
            return super.onCreatePanelMenu(arg3, arg4) | this.mFragments.dispatchCreateOptionsMenu(arg4, this.getMenuInflater());
        }

        return super.onCreatePanelMenu(arg3, arg4);
    }

    public View onCreateView(View arg1, String arg2, Context arg3, AttributeSet arg4) {
        return super.onCreateView(arg1, arg2, arg3, arg4);
    }

    public View onCreateView(String arg1, Context arg2, AttributeSet arg3) {
        return super.onCreateView(arg1, arg2, arg3);
    }

    protected void onDestroy() {
        super.onDestroy();
        this.doReallyStop(false);
        this.mFragments.dispatchDestroy();
        this.mFragments.doLoaderDestroy();
    }

    public void onLowMemory() {
        super.onLowMemory();
        this.mFragments.dispatchLowMemory();
    }

    public boolean onMenuItemSelected(int arg2, MenuItem arg3) {
        if(super.onMenuItemSelected(arg2, arg3)) {
            return 1;
        }

        if(arg2 != 0) {
            if(arg2 != 6) {
                return 0;
            }

            return this.mFragments.dispatchContextItemSelected(arg3);
        }

        return this.mFragments.dispatchOptionsItemSelected(arg3);
    }

    @CallSuper public void onMultiWindowModeChanged(boolean arg2) {
        this.mFragments.dispatchMultiWindowModeChanged(arg2);
    }

    protected void onNewIntent(Intent arg1) {
        super.onNewIntent(arg1);
        this.mFragments.noteStateNotSaved();
    }

    public void onPanelClosed(int arg2, Menu arg3) {
        if(arg2 != 0) {
        }
        else {
            this.mFragments.dispatchOptionsMenuClosed(arg3);
        }

        super.onPanelClosed(arg2, arg3);
    }

    protected void onPause() {
        super.onPause();
        this.mResumed = false;
        int v1 = 2;
        if(this.mHandler.hasMessages(v1)) {
            this.mHandler.removeMessages(v1);
            this.onResumeFragments();
        }

        this.mFragments.dispatchPause();
    }

    @CallSuper public void onPictureInPictureModeChanged(boolean arg2) {
        this.mFragments.dispatchPictureInPictureModeChanged(arg2);
    }

    protected void onPostResume() {
        super.onPostResume();
        this.mHandler.removeMessages(2);
        this.onResumeFragments();
        this.mFragments.execPendingActions();
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) protected boolean onPrepareOptionsPanel(View arg2, Menu arg3) {
        return super.onPreparePanel(0, arg2, arg3);
    }

    public boolean onPreparePanel(int arg1, View arg2, Menu arg3) {
        if(arg1 == 0 && arg3 != null) {
            return this.onPrepareOptionsPanel(arg2, arg3) | this.mFragments.dispatchPrepareOptionsMenu(arg3);
        }

        return super.onPreparePanel(arg1, arg2, arg3);
    }

    void onReallyStop() {
        this.mFragments.doLoaderStop(this.mRetaining);
        this.mFragments.dispatchReallyStop();
    }

    public void onRequestPermissionsResult(int arg5, @NonNull String[] arg6, @NonNull int[] arg7) {
        int v1 = 0xFFFF;
        int v0 = arg5 >> 16 & v1;
        if(v0 != 0) {
            --v0;
            Object v2 = this.mPendingFragmentActivityResults.get(v0);
            this.mPendingFragmentActivityResults.remove(v0);
            if(v2 == null) {
                Log.w("FragmentActivity", "Activity result delivered for unknown Fragment.");
                return;
            }
            else {
                Fragment v0_1 = this.mFragments.findFragmentByWho(((String)v2));
                if(v0_1 == null) {
                    Log.w("FragmentActivity", "Activity result no fragment exists for who: " + (((String)v2)));
                }
                else {
                    v0_1.onRequestPermissionsResult(arg5 & v1, arg6, arg7);
                }
            }
        }
    }

    protected void onResume() {
        super.onResume();
        this.mHandler.sendEmptyMessage(2);
        this.mResumed = true;
        this.mFragments.execPendingActions();
    }

    protected void onResumeFragments() {
        this.mFragments.dispatchResume();
    }

    public Object onRetainCustomNonConfigurationInstance() {
        return null;
    }

    public final Object onRetainNonConfigurationInstance() {
        if(this.mStopped) {
            this.doReallyStop(true);
        }

        Object v0 = this.onRetainCustomNonConfigurationInstance();
        FragmentManagerNonConfig v1 = this.mFragments.retainNestedNonConfig();
        SimpleArrayMap v2 = this.mFragments.retainLoaderNonConfig();
        if(v1 == null && v2 == null && v0 == null) {
            return null;
        }

        NonConfigurationInstances v3 = new NonConfigurationInstances();
        v3.custom = v0;
        v3.fragments = v1;
        v3.loaders = v2;
        return v3;
    }

    protected void onSaveInstanceState(Bundle arg5) {
        super.onSaveInstanceState(arg5);
        FragmentActivity.markState(this.getSupportFragmentManager(), State.CREATED);
        Parcelable v0 = this.mFragments.saveAllState();
        if(v0 != null) {
            arg5.putParcelable("android:support:fragments", v0);
        }

        if(this.mPendingFragmentActivityResults.size() > 0) {
            arg5.putInt("android:support:next_request_index", this.mNextCandidateRequestIndex);
            int[] v0_1 = new int[this.mPendingFragmentActivityResults.size()];
            String[] v1 = new String[this.mPendingFragmentActivityResults.size()];
            int v2;
            for(v2 = 0; v2 < this.mPendingFragmentActivityResults.size(); ++v2) {
                v0_1[v2] = this.mPendingFragmentActivityResults.keyAt(v2);
                v1[v2] = this.mPendingFragmentActivityResults.valueAt(v2);
            }

            arg5.putIntArray("android:support:request_indicies", v0_1);
            arg5.putStringArray("android:support:request_fragment_who", v1);
        }
    }

    protected void onStart() {
        super.onStart();
        this.mStopped = false;
        this.mReallyStopped = false;
        this.mHandler.removeMessages(1);
        if(!this.mCreated) {
            this.mCreated = true;
            this.mFragments.dispatchActivityCreated();
        }

        this.mFragments.noteStateNotSaved();
        this.mFragments.execPendingActions();
        this.mFragments.doLoaderStart();
        this.mFragments.dispatchStart();
        this.mFragments.reportLoaderStart();
    }

    public void onStateNotSaved() {
        this.mFragments.noteStateNotSaved();
    }

    protected void onStop() {
        super.onStop();
        this.mStopped = true;
        FragmentActivity.markState(this.getSupportFragmentManager(), State.CREATED);
        this.mHandler.sendEmptyMessage(1);
        this.mFragments.dispatchStop();
    }

    void requestPermissionsFromFragment(Fragment arg3, String[] arg4, int arg5) {
        if(arg5 == -1) {
            ActivityCompat.requestPermissions(((Activity)this), arg4, arg5);
            return;
        }

        FragmentActivity.checkForValidRequestCode(arg5);
        try {
            this.mRequestedPermissionsFromFragment = true;
            ActivityCompat.requestPermissions(((Activity)this), arg4, (this.allocateRequestIndex(arg3) + 1 << 16) + (arg5 & 0xFFFF));
        }
        catch(Throwable v3) {
            this.mRequestedPermissionsFromFragment = false;
            throw v3;
        }

        this.mRequestedPermissionsFromFragment = false;
    }

    public void setEnterSharedElementCallback(SharedElementCallback arg1) {
        ActivityCompat.setEnterSharedElementCallback(((Activity)this), arg1);
    }

    public void setExitSharedElementCallback(SharedElementCallback arg1) {
        ActivityCompat.setExitSharedElementCallback(((Activity)this), arg1);
    }

    public void startActivityForResult(Intent arg2, int arg3) {
        if(!this.mStartedActivityFromFragment && arg3 != -1) {
            FragmentActivity.checkForValidRequestCode(arg3);
        }

        super.startActivityForResult(arg2, arg3);
    }

    @RequiresApi(value=16) public void startActivityForResult(Intent arg1, int arg2, @Nullable Bundle arg3) {
        super.startActivityForResult(arg1, arg2, arg3);
    }

    public void startActivityFromFragment(Fragment arg2, Intent arg3, int arg4) {
        this.startActivityFromFragment(arg2, arg3, arg4, null);
    }

    public void startActivityFromFragment(Fragment arg4, Intent arg5, int arg6, @Nullable Bundle arg7) {
        this.mStartedActivityFromFragment = true;
        int v1 = -1;
        if(arg6 == v1) {
            try {
                ActivityCompat.startActivityForResult(((Activity)this), arg5, v1, arg7);
            }
            catch(Throwable v4) {
                goto label_20;
            }

            this.mStartedActivityFromFragment = false;
            return;
        }

        try {
            FragmentActivity.checkForValidRequestCode(arg6);
            ActivityCompat.startActivityForResult(((Activity)this), arg5, (this.allocateRequestIndex(arg4) + 1 << 16) + (arg6 & 0xFFFF), arg7);
        }
        catch(Throwable v4) {
        label_20:
            this.mStartedActivityFromFragment = false;
            throw v4;
        }

        this.mStartedActivityFromFragment = false;
    }

    public void startIntentSenderForResult(IntentSender arg1, int arg2, @Nullable Intent arg3, int arg4, int arg5, int arg6) throws IntentSender$SendIntentException {
        super.startIntentSenderForResult(arg1, arg2, arg3, arg4, arg5, arg6);
    }

    @RequiresApi(value=16) public void startIntentSenderForResult(IntentSender arg1, int arg2, @Nullable Intent arg3, int arg4, int arg5, int arg6, Bundle arg7) throws IntentSender$SendIntentException {
        super.startIntentSenderForResult(arg1, arg2, arg3, arg4, arg5, arg6, arg7);
    }

    public void startIntentSenderFromFragment(Fragment arg12, IntentSender arg13, int arg14, @Nullable Intent arg15, int arg16, int arg17, int arg18, Bundle arg19) throws IntentSender$SendIntentException {
        FragmentActivity v9 = this;
        int v3 = arg14;
        v9.mStartedIntentSenderFromFragment = true;
        if(v3 == -1) {
            FragmentActivity v1 = v9;
            IntentSender v2 = arg13;
            Intent v4 = arg15;
            int v5 = arg16;
            int v6 = arg17;
            int v7 = arg18;
            Bundle v8 = arg19;
            try {
                ActivityCompat.startIntentSenderForResult(((Activity)v1), v2, v3, v4, v5, v6, v7, v8);
            }
            catch(Throwable v0) {
                goto label_18;
            }

            v9.mStartedIntentSenderFromFragment = false;
            return;
        }

        try {
            FragmentActivity.checkForValidRequestCode(v3);
            ActivityCompat.startIntentSenderForResult(v9, arg13, (v9.allocateRequestIndex(arg12) + 1 << 16) + (0xFFFF & v3), arg15, arg16, arg17, arg18, arg19);
        }
        catch(Throwable v0) {
        label_18:
            Throwable v1_1 = v0;
            v9.mStartedIntentSenderFromFragment = false;
            throw v1_1;
        }

        v9.mStartedIntentSenderFromFragment = false;
    }

    public void supportFinishAfterTransition() {
        ActivityCompat.finishAfterTransition(((Activity)this));
    }

    @Deprecated public void supportInvalidateOptionsMenu() {
        this.invalidateOptionsMenu();
    }

    public void supportPostponeEnterTransition() {
        ActivityCompat.postponeEnterTransition(((Activity)this));
    }

    public void supportStartPostponedEnterTransition() {
        ActivityCompat.startPostponedEnterTransition(((Activity)this));
    }

    public final void validateRequestPermissionsRequestCode(int arg2) {
        if(!this.mRequestedPermissionsFromFragment && arg2 != -1) {
            FragmentActivity.checkForValidRequestCode(arg2);
        }
    }
}

