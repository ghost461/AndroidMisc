package org.chromium.blink.mojom;

import org.chromium.mojo.bindings.Callbacks$Callback1;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;
import org.chromium.mojo.system.DataPipe$ProducerHandle;
import org.chromium.mojo_base.mojom.File;

public interface BytesProvider extends Interface {
    public interface Proxy extends BytesProvider, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public interface RequestAsFileResponse extends Callback1 {
    }

    public interface RequestAsReplyResponse extends Callback1 {
    }

    public static final Manager MANAGER;

    static {
        BytesProvider.MANAGER = BytesProvider_Internal.MANAGER;
    }

    void requestAsFile(long arg1, long arg2, File arg3, long arg4, RequestAsFileResponse arg5);

    void requestAsReply(RequestAsReplyResponse arg1);

    void requestAsStream(ProducerHandle arg1);
}

