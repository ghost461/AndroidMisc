package org.chromium.media.mojom;

import org.chromium.mojo.bindings.AssociatedInterfaceNotSupported;
import org.chromium.mojo.bindings.Callbacks$Callback0;
import org.chromium.mojo.bindings.Callbacks$Callback1;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;
import org.chromium.mojo_base.mojom.TimeDelta;
import org.chromium.url.mojom.Url;

public interface Renderer extends Interface {
    public interface FlushResponse extends Callback0 {
    }

    public interface InitializeResponse extends Callback1 {
    }

    public interface InitiateScopedSurfaceRequestResponse extends Callback1 {
    }

    public interface Proxy extends Renderer, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public interface SetCdmResponse extends Callback1 {
    }

    public static final Manager MANAGER;

    static {
        Renderer.MANAGER = Renderer_Internal.MANAGER;
    }

    void flush(FlushResponse arg1);

    void initialize(AssociatedInterfaceNotSupported arg1, DemuxerStream[] arg2, Url arg3, Url arg4, InitializeResponse arg5);

    void initiateScopedSurfaceRequest(InitiateScopedSurfaceRequestResponse arg1);

    void setCdm(int arg1, SetCdmResponse arg2);

    void setPlaybackRate(double arg1);

    void setVolume(float arg1);

    void startPlayingFrom(TimeDelta arg1);
}

