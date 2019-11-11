package android.support.v4.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender$SendIntentException;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.SimpleArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public abstract class FragmentHostCallback extends FragmentContainer {
    private final Activity mActivity;
    private SimpleArrayMap mAllLoaderManagers;
    private boolean mCheckedForLoaderManager;
    final Context mContext;
    final FragmentManagerImpl mFragmentManager;
    private final Handler mHandler;
    private LoaderManagerImpl mLoaderManager;
    private boolean mLoadersStarted;
    private boolean mRetainLoaders;
    final int mWindowAnimations;

    FragmentHostCallback(Activity arg2, Context arg3, Handler arg4, int arg5) {
        super();
        this.mFragmentManager = new FragmentManagerImpl();
        this.mActivity = arg2;
        this.mContext = arg3;
        this.mHandler = arg4;
        this.mWindowAnimations = arg5;
    }

    public FragmentHostCallback(Context arg2, Handler arg3, int arg4) {
        Context v0;
        if((arg2 instanceof Activity)) {
            v0 = arg2;
        }
        else {
            Activity v0_1 = null;
        }

        this(((Activity)v0), arg2, arg3, arg4);
    }

    FragmentHostCallback(FragmentActivity arg3) {
        this(((Activity)arg3), ((Context)arg3), arg3.mHandler, 0);
    }

    void doLoaderDestroy() {
        if(this.mLoaderManager == null) {
            return;
        }

        this.mLoaderManager.doDestroy();
    }

    void doLoaderRetain() {
        if(this.mLoaderManager == null) {
            return;
        }

        this.mLoaderManager.doRetain();
    }

    void doLoaderStart() {
        if(this.mLoadersStarted) {
            return;
        }

        this.mLoadersStarted = true;
        if(this.mLoaderManager != null) {
            this.mLoaderManager.doStart();
        }
        else if(!this.mCheckedForLoaderManager) {
            this.mLoaderManager = this.getLoaderManager("(root)", this.mLoadersStarted, false);
            if(this.mLoaderManager != null && !this.mLoaderManager.mStarted) {
                this.mLoaderManager.doStart();
            }
        }

        this.mCheckedForLoaderManager = true;
    }

    void doLoaderStop(boolean arg2) {
        this.mRetainLoaders = arg2;
        if(this.mLoaderManager == null) {
            return;
        }

        if(!this.mLoadersStarted) {
            return;
        }

        this.mLoadersStarted = false;
        if(arg2) {
            this.mLoaderManager.doRetain();
        }
        else {
            this.mLoaderManager.doStop();
        }
    }

    void dumpLoaders(String arg3, FileDescriptor arg4, PrintWriter arg5, String[] arg6) {
        arg5.print(arg3);
        arg5.print("mLoadersStarted=");
        arg5.println(this.mLoadersStarted);
        if(this.mLoaderManager != null) {
            arg5.print(arg3);
            arg5.print("Loader Manager ");
            arg5.print(Integer.toHexString(System.identityHashCode(this.mLoaderManager)));
            arg5.println(":");
            LoaderManagerImpl v0 = this.mLoaderManager;
            v0.dump(arg3 + "  ", arg4, arg5, arg6);
        }
    }

    Activity getActivity() {
        return this.mActivity;
    }

    Context getContext() {
        return this.mContext;
    }

    FragmentManagerImpl getFragmentManagerImpl() {
        return this.mFragmentManager;
    }

    Handler getHandler() {
        return this.mHandler;
    }

    LoaderManagerImpl getLoaderManager(String arg2, boolean arg3, boolean arg4) {
        LoaderManagerImpl v0_1;
        if(this.mAllLoaderManagers == null) {
            this.mAllLoaderManagers = new SimpleArrayMap();
        }

        Object v0 = this.mAllLoaderManagers.get(arg2);
        if(v0 == null && (arg4)) {
            v0_1 = new LoaderManagerImpl(arg2, this, arg3);
            this.mAllLoaderManagers.put(arg2, v0_1);
        }
        else if((arg3) && v0 != null && !((LoaderManagerImpl)v0).mStarted) {
            ((LoaderManagerImpl)v0).doStart();
        }

        return v0_1;
    }

    LoaderManagerImpl getLoaderManagerImpl() {
        if(this.mLoaderManager != null) {
            return this.mLoaderManager;
        }

        this.mCheckedForLoaderManager = true;
        this.mLoaderManager = this.getLoaderManager("(root)", this.mLoadersStarted, true);
        return this.mLoaderManager;
    }

    boolean getRetainLoaders() {
        return this.mRetainLoaders;
    }

    void inactivateFragment(String arg3) {
        if(this.mAllLoaderManagers != null) {
            Object v0 = this.mAllLoaderManagers.get(arg3);
            if(v0 != null && !((LoaderManagerImpl)v0).mRetaining) {
                ((LoaderManagerImpl)v0).doDestroy();
                this.mAllLoaderManagers.remove(arg3);
            }
        }
    }

    void onAttachFragment(Fragment arg1) {
    }

    public void onDump(String arg1, FileDescriptor arg2, PrintWriter arg3, String[] arg4) {
    }

    @Nullable public View onFindViewById(int arg1) {
        return null;
    }

    @Nullable public abstract Object onGetHost();

    public LayoutInflater onGetLayoutInflater() {
        return this.mContext.getSystemService("layout_inflater");
    }

    public int onGetWindowAnimations() {
        return this.mWindowAnimations;
    }

    public boolean onHasView() {
        return 1;
    }

    public boolean onHasWindowAnimations() {
        return 1;
    }

    public void onRequestPermissionsFromFragment(@NonNull Fragment arg1, @NonNull String[] arg2, int arg3) {
    }

    public boolean onShouldSaveFragmentState(Fragment arg1) {
        return 1;
    }

    public boolean onShouldShowRequestPermissionRationale(@NonNull String arg1) {
        return 0;
    }

    public void onStartActivityFromFragment(Fragment arg1, Intent arg2, int arg3, @Nullable Bundle arg4) {
        if(arg3 != -1) {
            throw new IllegalStateException("Starting activity with a requestCode requires a FragmentActivity host");
        }

        this.mContext.startActivity(arg2);
    }

    public void onStartActivityFromFragment(Fragment arg2, Intent arg3, int arg4) {
        this.onStartActivityFromFragment(arg2, arg3, arg4, null);
    }

    public void onStartIntentSenderFromFragment(Fragment arg10, IntentSender arg11, int arg12, @Nullable Intent arg13, int arg14, int arg15, int arg16, Bundle arg17) throws IntentSender$SendIntentException {
        int v3 = arg12;
        if(v3 != -1) {
            throw new IllegalStateException("Starting intent sender with a requestCode requires a FragmentActivity host");
        }

        ActivityCompat.startIntentSenderForResult(this.mActivity, arg11, v3, arg13, arg14, arg15, arg16, arg17);
    }

    public void onSupportInvalidateOptionsMenu() {
    }

    void reportLoaderStart() {
        if(this.mAllLoaderManagers != null) {
            int v0 = this.mAllLoaderManagers.size();
            LoaderManagerImpl[] v1 = new LoaderManagerImpl[v0];
            int v2;
            for(v2 = v0 - 1; v2 >= 0; --v2) {
                v1[v2] = this.mAllLoaderManagers.valueAt(v2);
            }

            for(v2 = 0; v2 < v0; ++v2) {
                LoaderManagerImpl v3 = v1[v2];
                v3.finishRetain();
                v3.doReportStart();
            }
        }
    }

    void restoreLoaderNonConfig(SimpleArrayMap arg4) {
        if(arg4 != null) {
            int v0 = 0;
            int v1 = arg4.size();
            while(v0 < v1) {
                arg4.valueAt(v0).updateHostController(this);
                ++v0;
            }
        }

        this.mAllLoaderManagers = arg4;
    }

    SimpleArrayMap retainLoaderNonConfig() {
        int v1 = 0;
        if(this.mAllLoaderManagers != null) {
            int v0 = this.mAllLoaderManagers.size();
            LoaderManagerImpl[] v2 = new LoaderManagerImpl[v0];
            int v3;
            for(v3 = v0 - 1; v3 >= 0; --v3) {
                v2[v3] = this.mAllLoaderManagers.valueAt(v3);
            }

            boolean v3_1 = this.getRetainLoaders();
            int v4 = 0;
            while(v1 < v0) {
                LoaderManagerImpl v5 = v2[v1];
                if(!v5.mRetaining && (v3_1)) {
                    if(!v5.mStarted) {
                        v5.doStart();
                    }

                    v5.doRetain();
                }

                if(v5.mRetaining) {
                    v4 = 1;
                }
                else {
                    v5.doDestroy();
                    this.mAllLoaderManagers.remove(v5.mWho);
                }

                ++v1;
            }

            v1 = v4;
        }

        if(v1 != 0) {
            return this.mAllLoaderManagers;
        }

        return null;
    }
}

