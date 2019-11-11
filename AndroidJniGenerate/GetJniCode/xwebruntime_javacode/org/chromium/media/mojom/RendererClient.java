package org.chromium.media.mojom;

import org.chromium.gfx.mojom.Size;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;
import org.chromium.mojo_base.mojom.TimeDelta;
import org.chromium.mojo_base.mojom.TimeTicks;

public interface RendererClient extends Interface {
    public interface Proxy extends RendererClient, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        RendererClient.MANAGER = RendererClient_Internal.MANAGER;
    }

    void onAudioConfigChange(AudioDecoderConfig arg1);

    void onBufferingStateChange(int arg1);

    void onDurationChange(TimeDelta arg1);

    void onEnded();

    void onError();

    void onStatisticsUpdate(PipelineStatistics arg1);

    void onTimeUpdate(TimeDelta arg1, TimeDelta arg2, TimeTicks arg3);

    void onVideoConfigChange(VideoDecoderConfig arg1);

    void onVideoNaturalSizeChange(Size arg1);

    void onVideoOpacityChange(boolean arg1);

    void onWaitingForDecryptionKey();
}

