package org.chromium.base.library_loader;

import android.content.Context;

public abstract class NativeLibraryPreloader {
    public NativeLibraryPreloader() {
        super();
    }

    public abstract int loadLibrary(Context arg1);
}

