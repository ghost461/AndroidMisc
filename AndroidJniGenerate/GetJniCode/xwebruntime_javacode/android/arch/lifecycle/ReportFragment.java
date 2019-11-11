package android.arch.lifecycle;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;

@RestrictTo(value={Scope.LIBRARY_GROUP}) public class ReportFragment extends Fragment {
    interface ActivityInitializationListener {
        void onCreate();

        void onResume();

        void onStart();
    }

    private static final String REPORT_FRAGMENT_TAG = "android.arch.lifecycle.LifecycleDispatcher.report_fragment_tag";
    private ActivityInitializationListener mProcessListener;

    public ReportFragment() {
        super();
    }

    private void dispatch(Event arg3) {
        Activity v0 = this.getActivity();
        if((v0 instanceof LifecycleRegistryOwner)) {
            ((LifecycleRegistryOwner)v0).getLifecycle().handleLifecycleEvent(arg3);
            return;
        }

        if((v0 instanceof LifecycleOwner)) {
            Lifecycle v0_1 = ((LifecycleOwner)v0).getLifecycle();
            if((v0_1 instanceof LifecycleRegistry)) {
                ((LifecycleRegistry)v0_1).handleLifecycleEvent(arg3);
            }
        }
    }

    private void dispatchCreate(ActivityInitializationListener arg1) {
        if(arg1 != null) {
            arg1.onCreate();
        }
    }

    private void dispatchResume(ActivityInitializationListener arg1) {
        if(arg1 != null) {
            arg1.onResume();
        }
    }

    private void dispatchStart(ActivityInitializationListener arg1) {
        if(arg1 != null) {
            arg1.onStart();
        }
    }

    static ReportFragment get(Activity arg1) {
        return arg1.getFragmentManager().findFragmentByTag("android.arch.lifecycle.LifecycleDispatcher.report_fragment_tag");
    }

    public static void injectIfNeededIn(Activity arg3) {
        FragmentManager v3 = arg3.getFragmentManager();
        if(v3.findFragmentByTag("android.arch.lifecycle.LifecycleDispatcher.report_fragment_tag") == null) {
            v3.beginTransaction().add(new ReportFragment(), "android.arch.lifecycle.LifecycleDispatcher.report_fragment_tag").commit();
            v3.executePendingTransactions();
        }
    }

    public void onActivityCreated(Bundle arg1) {
        super.onActivityCreated(arg1);
        this.dispatchCreate(this.mProcessListener);
        this.dispatch(Event.ON_CREATE);
    }

    public void onDestroy() {
        super.onDestroy();
        this.dispatch(Event.ON_DESTROY);
        this.mProcessListener = null;
    }

    public void onPause() {
        super.onPause();
        this.dispatch(Event.ON_PAUSE);
    }

    public void onResume() {
        super.onResume();
        this.dispatchResume(this.mProcessListener);
        this.dispatch(Event.ON_RESUME);
    }

    public void onStart() {
        super.onStart();
        this.dispatchStart(this.mProcessListener);
        this.dispatch(Event.ON_START);
    }

    public void onStop() {
        super.onStop();
        this.dispatch(Event.ON_STOP);
    }

    void setProcessListener(ActivityInitializationListener arg1) {
        this.mProcessListener = arg1;
    }
}

