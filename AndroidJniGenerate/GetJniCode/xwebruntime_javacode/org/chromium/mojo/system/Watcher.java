package org.chromium.mojo.system;

public interface Watcher {
    public interface Callback {
        void onResult(int arg1);
    }

    void cancel();

    void destroy();

    int start(Handle arg1, HandleSignals arg2, Callback arg3);
}

