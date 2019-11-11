package android.support.v4.app;

import android.app.Activity;
import android.arch.lifecycle.Lifecycle$State;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.ReportFragment;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.v4.util.SimpleArrayMap;

@RestrictTo(value={Scope.LIBRARY_GROUP}) public class SupportActivity extends Activity implements LifecycleOwner {
    @RestrictTo(value={Scope.LIBRARY_GROUP}) public class ExtraData {
        public ExtraData() {
            super();
        }
    }

    private SimpleArrayMap mExtraDataMap;
    private LifecycleRegistry mLifecycleRegistry;

    public SupportActivity() {
        super();
        this.mExtraDataMap = new SimpleArrayMap();
        this.mLifecycleRegistry = new LifecycleRegistry(((LifecycleOwner)this));
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public ExtraData getExtraData(Class arg2) {
        return this.mExtraDataMap.get(arg2);
    }

    public Lifecycle getLifecycle() {
        return this.mLifecycleRegistry;
    }

    protected void onCreate(@Nullable Bundle arg1) {
        super.onCreate(arg1);
        ReportFragment.injectIfNeededIn(((Activity)this));
    }

    @CallSuper protected void onSaveInstanceState(Bundle arg3) {
        this.mLifecycleRegistry.markState(State.CREATED);
        super.onSaveInstanceState(arg3);
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public void putExtraData(ExtraData arg3) {
        this.mExtraDataMap.put(arg3.getClass(), arg3);
    }
}

