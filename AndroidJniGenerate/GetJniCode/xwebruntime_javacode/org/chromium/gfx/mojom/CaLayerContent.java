package org.chromium.gfx.mojom;

import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Union;
import org.chromium.mojo.system.UntypedHandle;

public final class CaLayerContent extends Union {
    public final class Tag {
        public static final int CaContextId = 0;
        public static final int IoSurfaceMachPort = 1;

        public Tag() {
            super();
        }
    }

    private int mCaContextId;
    private UntypedHandle mIoSurfaceMachPort;

    static {
    }

    public CaLayerContent() {
        super();
    }

    public static final CaLayerContent decode(Decoder arg3, int arg4) {
        DataHeader v0 = arg3.readDataHeaderForUnion(arg4);
        if(v0.size == 0) {
            return null;
        }

        CaLayerContent v1 = new CaLayerContent();
        switch(v0.elementsOrVersion) {
            case 0: {
                v1.mCaContextId = arg3.readInt(arg4 + 8);
                v1.mTag = 0;
                break;
            }
            case 1: {
                v1.mIoSurfaceMachPort = arg3.readUntypedHandle(arg4 + 8, false);
                v1.mTag = 1;
                break;
            }
            default: {
                break;
            }
        }

        return v1;
    }

    public static CaLayerContent deserialize(Message arg1) {
        return CaLayerContent.decode(new Decoder(arg1).decoderForSerializedUnion(), 0);
    }

    protected final void encode(Encoder arg3, int arg4) {
        arg3.encode(16, arg4);
        arg3.encode(this.mTag, arg4 + 4);
        switch(this.mTag) {
            case 0: {
                arg3.encode(this.mCaContextId, arg4 + 8);
                break;
            }
            case 1: {
                arg3.encode(this.mIoSurfaceMachPort, arg4 + 8, false);
                break;
            }
            default: {
                break;
            }
        }
    }

    public int getCaContextId() {
        return this.mCaContextId;
    }

    public UntypedHandle getIoSurfaceMachPort() {
        return this.mIoSurfaceMachPort;
    }

    public void setCaContextId(int arg2) {
        this.mTag = 0;
        this.mCaContextId = arg2;
    }

    public void setIoSurfaceMachPort(UntypedHandle arg2) {
        this.mTag = 1;
        this.mIoSurfaceMachPort = arg2;
    }
}

