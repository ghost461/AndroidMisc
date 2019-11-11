package org.xwalk.core.internal;

import java.util.Iterator;
import org.chromium.base.ObserverList;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="xwalk") public class XWalkContentLifecycleNotifier {
    public interface Observer {
        void onFirstXWalkViewCreated();

        void onLastXWalkViewDestroyed();
    }

    private static final ObserverList sLifecycleObservers;
    private static int sNumXWalkViews;

    static {
        XWalkContentLifecycleNotifier.sLifecycleObservers = new ObserverList();
    }

    private XWalkContentLifecycleNotifier() {
        super();
    }

    public static void addObserver(Observer arg1) {
        XWalkContentLifecycleNotifier.sLifecycleObservers.addObserver(arg1);
    }

    public static boolean hasXWalkViewInstances() {
        boolean v0 = XWalkContentLifecycleNotifier.sNumXWalkViews > 0 ? true : false;
        return v0;
    }

    @CalledByNative private static void onXWalkViewCreated() {
        ThreadUtils.assertOnUiThread();
        ++XWalkContentLifecycleNotifier.sNumXWalkViews;
        if(XWalkContentLifecycleNotifier.sNumXWalkViews == 1) {
            Iterator v0 = XWalkContentLifecycleNotifier.sLifecycleObservers.iterator();
            while(v0.hasNext()) {
                v0.next().onFirstXWalkViewCreated();
            }
        }
    }

    @CalledByNative private static void onXWalkViewDestroyed() {
        ThreadUtils.assertOnUiThread();
        --XWalkContentLifecycleNotifier.sNumXWalkViews;
        if(XWalkContentLifecycleNotifier.sNumXWalkViews == 0) {
            Iterator v0 = XWalkContentLifecycleNotifier.sLifecycleObservers.iterator();
            while(v0.hasNext()) {
                v0.next().onLastXWalkViewDestroyed();
            }
        }
    }

    public static void removeObserver(Observer arg1) {
        XWalkContentLifecycleNotifier.sLifecycleObservers.removeObserver(arg1);
    }
}

