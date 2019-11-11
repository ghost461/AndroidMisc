package org.chromium.media.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;
import org.chromium.mojo.bindings.InterfaceRequest;
import org.chromium.mojo_base.mojom.TimeDelta;
import org.chromium.url.mojom.Origin;

public interface MediaMetricsProvider extends Interface {
    public interface Proxy extends MediaMetricsProvider, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        MediaMetricsProvider.MANAGER = MediaMetricsProvider_Internal.MANAGER;
    }

    void acquireVideoDecodeStatsRecorder(InterfaceRequest arg1);

    void acquireWatchTimeRecorder(PlaybackProperties arg1, InterfaceRequest arg2);

    void initialize(boolean arg1, boolean arg2, Origin arg3);

    void onError(int arg1);

    void setIsEme();

    void setTimeToFirstFrame(TimeDelta arg1);

    void setTimeToMetadata(TimeDelta arg1);

    void setTimeToPlayReady(TimeDelta arg1);
}

