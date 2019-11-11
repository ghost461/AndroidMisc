package org.chromium.media.mojom;

import org.chromium.mojo.bindings.Callbacks$Callback1;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface CdmFile extends Interface {
    public interface CommitWriteResponse extends Callback1 {
    }

    public interface OpenFileForWritingResponse extends Callback1 {
    }

    public interface Proxy extends CdmFile, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        CdmFile.MANAGER = CdmFile_Internal.MANAGER;
    }

    void commitWrite(CommitWriteResponse arg1);

    void openFileForWriting(OpenFileForWritingResponse arg1);
}

