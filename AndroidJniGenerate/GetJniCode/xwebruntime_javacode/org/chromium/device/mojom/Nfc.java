package org.chromium.device.mojom;

import org.chromium.mojo.bindings.Callbacks$Callback1;
import org.chromium.mojo.bindings.Callbacks$Callback2;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface Nfc extends Interface {
    public interface CancelAllWatchesResponse extends Callback1 {
    }

    public interface CancelPushResponse extends Callback1 {
    }

    public interface CancelWatchResponse extends Callback1 {
    }

    public interface Proxy extends Nfc, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public interface PushResponse extends Callback1 {
    }

    public interface WatchResponse extends Callback2 {
    }

    public static final Manager MANAGER;

    static {
        Nfc.MANAGER = Nfc_Internal.MANAGER;
    }

    void cancelAllWatches(CancelAllWatchesResponse arg1);

    void cancelPush(int arg1, CancelPushResponse arg2);

    void cancelWatch(int arg1, CancelWatchResponse arg2);

    void push(NfcMessage arg1, NfcPushOptions arg2, PushResponse arg3);

    void resumeNfcOperations();

    void setClient(NfcClient arg1);

    void suspendNfcOperations();

    void watch(NfcWatchOptions arg1, WatchResponse arg2);
}

