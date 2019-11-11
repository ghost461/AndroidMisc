package org.chromium.media.mojom;

import org.chromium.mojo.bindings.Callbacks$Callback2;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface VideoDecodePerfHistory extends Interface {
    public interface GetPerfInfoResponse extends Callback2 {
    }

    public interface Proxy extends VideoDecodePerfHistory, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        VideoDecodePerfHistory.MANAGER = VideoDecodePerfHistory_Internal.MANAGER;
    }

    void getPerfInfo(PredictionFeatures arg1, GetPerfInfoResponse arg2);
}

