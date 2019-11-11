package org.chromium.media.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface VideoDecodeStatsRecorder extends Interface {
    public interface Proxy extends VideoDecodeStatsRecorder, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        VideoDecodeStatsRecorder.MANAGER = VideoDecodeStatsRecorder_Internal.MANAGER;
    }

    void startNewRecord(PredictionFeatures arg1);

    void updateRecord(PredictionTargets arg1);
}

