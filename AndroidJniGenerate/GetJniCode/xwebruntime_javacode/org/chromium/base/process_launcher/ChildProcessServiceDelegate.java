package org.chromium.base.process_launcher;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;
import java.util.List;

public interface ChildProcessServiceDelegate {
    SparseArray getFileDescriptorsIdsToKeys();

    boolean loadNativeLibrary(Context arg1);

    void onBeforeMain();

    void onConnectionSetup(Bundle arg1, List arg2);

    void onDestroy();

    void onServiceBound(Intent arg1);

    void onServiceCreated();

    void preloadNativeLibrary(Context arg1);

    void runMain();
}

