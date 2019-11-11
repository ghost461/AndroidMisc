package org.chromium.media.mojom;

import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Union;

public final class VideoFrameData extends Union {
    public final class Tag {
        public static final int EosData = 0;
        public static final int MailboxData = 2;
        public static final int SharedBufferData = 1;

        public Tag() {
            super();
        }
    }

    private EosVideoFrameData mEosData;
    private MailboxVideoFrameData mMailboxData;
    private SharedBufferVideoFrameData mSharedBufferData;

    static {
    }

    public VideoFrameData() {
        super();
    }

    public static final VideoFrameData decode(Decoder arg3, int arg4) {
        DataHeader v0 = arg3.readDataHeaderForUnion(arg4);
        if(v0.size == 0) {
            return null;
        }

        VideoFrameData v1 = new VideoFrameData();
        switch(v0.elementsOrVersion) {
            case 0: {
                v1.mEosData = EosVideoFrameData.decode(arg3.readPointer(arg4 + 8, false));
                v1.mTag = 0;
                break;
            }
            case 1: {
                v1.mSharedBufferData = SharedBufferVideoFrameData.decode(arg3.readPointer(arg4 + 8, false));
                v1.mTag = 1;
                break;
            }
            case 2: {
                v1.mMailboxData = MailboxVideoFrameData.decode(arg3.readPointer(arg4 + 8, false));
                v1.mTag = 2;
                break;
            }
            default: {
                break;
            }
        }

        return v1;
    }

    public static VideoFrameData deserialize(Message arg1) {
        return VideoFrameData.decode(new Decoder(arg1).decoderForSerializedUnion(), 0);
    }

    protected final void encode(Encoder arg3, int arg4) {
        arg3.encode(16, arg4);
        arg3.encode(this.mTag, arg4 + 4);
        switch(this.mTag) {
            case 0: {
                arg3.encode(this.mEosData, arg4 + 8, false);
                break;
            }
            case 1: {
                arg3.encode(this.mSharedBufferData, arg4 + 8, false);
                break;
            }
            case 2: {
                arg3.encode(this.mMailboxData, arg4 + 8, false);
                break;
            }
            default: {
                break;
            }
        }
    }

    public EosVideoFrameData getEosData() {
        return this.mEosData;
    }

    public MailboxVideoFrameData getMailboxData() {
        return this.mMailboxData;
    }

    public SharedBufferVideoFrameData getSharedBufferData() {
        return this.mSharedBufferData;
    }

    public void setEosData(EosVideoFrameData arg2) {
        this.mTag = 0;
        this.mEosData = arg2;
    }

    public void setMailboxData(MailboxVideoFrameData arg2) {
        this.mTag = 2;
        this.mMailboxData = arg2;
    }

    public void setSharedBufferData(SharedBufferVideoFrameData arg2) {
        this.mTag = 1;
        this.mSharedBufferData = arg2;
    }
}

