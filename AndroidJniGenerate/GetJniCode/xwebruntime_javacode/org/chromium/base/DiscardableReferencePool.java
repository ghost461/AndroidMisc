package org.chromium.base;

import android.support.annotation.Nullable;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.WeakHashMap;

public class DiscardableReferencePool {
    class org.chromium.base.DiscardableReferencePool$1 {
    }

    public class DiscardableReference {
        @Nullable private Object mPayload;

        static {
        }

        DiscardableReference(Object arg1, org.chromium.base.DiscardableReferencePool$1 arg2) {
            this(arg1);
        }

        private DiscardableReference(Object arg1) {
            super();
            this.mPayload = arg1;
        }

        static void access$100(DiscardableReference arg0) {
            arg0.discard();
        }

        private void discard() {
            this.mPayload = null;
        }

        @Nullable public Object get() {
            return this.mPayload;
        }
    }

    private final Set mPool;

    static {
    }

    public DiscardableReferencePool() {
        super();
        this.mPool = Collections.newSetFromMap(new WeakHashMap());
    }

    public void drain() {
        Iterator v0 = this.mPool.iterator();
        while(v0.hasNext()) {
            DiscardableReference.access$100(v0.next());
        }

        this.mPool.clear();
    }

    public DiscardableReference put(Object arg3) {
        DiscardableReference v0 = new DiscardableReference(arg3, null);
        this.mPool.add(v0);
        return v0;
    }
}

