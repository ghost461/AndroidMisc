package android.arch.lifecycle;

public interface GenericLifecycleObserver extends LifecycleObserver {
    void onStateChanged(LifecycleOwner arg1, Event arg2);
}

