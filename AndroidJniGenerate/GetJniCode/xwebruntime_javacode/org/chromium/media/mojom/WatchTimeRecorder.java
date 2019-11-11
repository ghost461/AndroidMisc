package org.chromium.media.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;
import org.chromium.mojo_base.mojom.TimeDelta;

public interface WatchTimeRecorder extends Interface {
    public interface Proxy extends WatchTimeRecorder, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        WatchTimeRecorder.MANAGER = WatchTimeRecorder_Internal.MANAGER;
    }

    void finalizeWatchTime(int[] arg1);

    void onError(int arg1);

    void recordWatchTime(int arg1, TimeDelta arg2);

    void setAudioDecoderName(String arg1);

    void setAutoplayInitiated(boolean arg1);

    void setVideoDecoderName(String arg1);

    void updateUnderflowCount(int arg1);
}

