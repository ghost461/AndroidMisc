package org.chromium.blink.mojom;

import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Union;

public final class DataElement extends Union {
    public final class Tag {
        public static final int Blob = 3;
        public static final int Bytes = 0;
        public static final int File = 1;
        public static final int FileFilesystem = 2;

        public Tag() {
            super();
        }
    }

    private DataElementBlob mBlob;
    private DataElementBytes mBytes;
    private DataElementFile mFile;
    private DataElementFilesystemUrl mFileFilesystem;

    static {
    }

    public DataElement() {
        super();
    }

    public static final DataElement decode(Decoder arg3, int arg4) {
        DataHeader v0 = arg3.readDataHeaderForUnion(arg4);
        if(v0.size == 0) {
            return null;
        }

        DataElement v1 = new DataElement();
        switch(v0.elementsOrVersion) {
            case 0: {
                v1.mBytes = DataElementBytes.decode(arg3.readPointer(arg4 + 8, false));
                v1.mTag = 0;
                break;
            }
            case 1: {
                v1.mFile = DataElementFile.decode(arg3.readPointer(arg4 + 8, false));
                v1.mTag = 1;
                break;
            }
            case 2: {
                v1.mFileFilesystem = DataElementFilesystemUrl.decode(arg3.readPointer(arg4 + 8, false));
                v1.mTag = 2;
                break;
            }
            case 3: {
                v1.mBlob = DataElementBlob.decode(arg3.readPointer(arg4 + 8, false));
                v1.mTag = 3;
                break;
            }
            default: {
                break;
            }
        }

        return v1;
    }

    public static DataElement deserialize(Message arg1) {
        return DataElement.decode(new Decoder(arg1).decoderForSerializedUnion(), 0);
    }

    protected final void encode(Encoder arg3, int arg4) {
        arg3.encode(16, arg4);
        arg3.encode(this.mTag, arg4 + 4);
        switch(this.mTag) {
            case 0: {
                arg3.encode(this.mBytes, arg4 + 8, false);
                break;
            }
            case 1: {
                arg3.encode(this.mFile, arg4 + 8, false);
                break;
            }
            case 2: {
                arg3.encode(this.mFileFilesystem, arg4 + 8, false);
                break;
            }
            case 3: {
                arg3.encode(this.mBlob, arg4 + 8, false);
                break;
            }
            default: {
                break;
            }
        }
    }

    public DataElementBlob getBlob() {
        return this.mBlob;
    }

    public DataElementBytes getBytes() {
        return this.mBytes;
    }

    public DataElementFile getFile() {
        return this.mFile;
    }

    public DataElementFilesystemUrl getFileFilesystem() {
        return this.mFileFilesystem;
    }

    public void setBlob(DataElementBlob arg2) {
        this.mTag = 3;
        this.mBlob = arg2;
    }

    public void setBytes(DataElementBytes arg2) {
        this.mTag = 0;
        this.mBytes = arg2;
    }

    public void setFile(DataElementFile arg2) {
        this.mTag = 1;
        this.mFile = arg2;
    }

    public void setFileFilesystem(DataElementFilesystemUrl arg2) {
        this.mTag = 2;
        this.mFileFilesystem = arg2;
    }
}

