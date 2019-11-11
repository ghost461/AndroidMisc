package org.chromium.mojo_base;

import java.nio.ByteBuffer;
import org.chromium.mojo.system.Core;
import org.chromium.mojo.system.SharedBufferHandle$CreateOptions;
import org.chromium.mojo.system.SharedBufferHandle$MapFlags;
import org.chromium.mojo.system.impl.CoreImpl;
import org.chromium.mojo_base.mojom.BigBuffer;
import org.chromium.mojo_base.mojom.BigBufferSharedMemoryRegion;

public final class BigBufferUtil {
    public static final int MAX_INLINE_ARRAY_SIZE = 0x10000;

    public BigBufferUtil() {
        super();
    }

    public static BigBuffer createBigBufferFromBytes(byte[] arg9) {
        BigBuffer v0 = new BigBuffer();
        if(arg9.length <= 0x10000) {
            v0.setBytes(arg9);
            return v0;
        }

        Core v1 = CoreImpl.getInstance();
        BigBufferSharedMemoryRegion v2 = new BigBufferSharedMemoryRegion();
        v2.bufferHandle = v1.createSharedBuffer(new CreateOptions(), ((long)arg9.length));
        v2.size = arg9.length;
        v2.bufferHandle.map(0, ((long)arg9.length), MapFlags.NONE).put(arg9);
        v0.setSharedMemory(v2);
        return v0;
    }

    public static byte[] getBytesFromBigBuffer(BigBuffer arg6) {
        if(arg6.which() == 0) {
            return arg6.getBytes();
        }

        BigBufferSharedMemoryRegion v6 = arg6.getSharedMemory();
        ByteBuffer v0 = v6.bufferHandle.map(0, ((long)v6.size), MapFlags.NONE);
        byte[] v6_1 = new byte[v6.size];
        v0.get(v6_1);
        return v6_1;
    }
}

