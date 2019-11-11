package org.chromium.mojo.bindings;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.WeakHashMap;
import org.chromium.mojo.system.MojoException;

public class DelegatingConnectionErrorHandler implements ConnectionErrorHandler {
    private final Set mHandlers;

    public DelegatingConnectionErrorHandler() {
        super();
        this.mHandlers = Collections.newSetFromMap(new WeakHashMap());
    }

    public void addConnectionErrorHandler(ConnectionErrorHandler arg2) {
        this.mHandlers.add(arg2);
    }

    public void onConnectionError(MojoException arg3) {
        Iterator v0 = this.mHandlers.iterator();
        while(v0.hasNext()) {
            v0.next().onConnectionError(arg3);
        }
    }

    public void removeConnectionErrorHandler(ConnectionErrorHandler arg2) {
        this.mHandlers.remove(arg2);
    }
}

