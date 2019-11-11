package org.chromium.net;

import android.os.ParcelFileDescriptor$AutoCloseInputStream;
import android.os.ParcelFileDescriptor;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public final class UploadDataProviders {
    final class ByteBufferUploadProvider extends UploadDataProvider {
        private final ByteBuffer mUploadBuffer;

        ByteBufferUploadProvider(ByteBuffer arg1, org.chromium.net.UploadDataProviders$1 arg2) {
            this(arg1);
        }

        private ByteBufferUploadProvider(ByteBuffer arg1) {
            super();
            this.mUploadBuffer = arg1;
        }

        public long getLength() {
            return ((long)this.mUploadBuffer.limit());
        }

        public void read(UploadDataSink arg5, ByteBuffer arg6) {
            if(!arg6.hasRemaining()) {
                throw new IllegalStateException("Cronet passed a buffer with no bytes remaining");
            }

            if(arg6.remaining() >= this.mUploadBuffer.remaining()) {
                arg6.put(this.mUploadBuffer);
            }
            else {
                int v0 = this.mUploadBuffer.limit();
                this.mUploadBuffer.limit(this.mUploadBuffer.position() + arg6.remaining());
                arg6.put(this.mUploadBuffer);
                this.mUploadBuffer.limit(v0);
            }

            arg5.onReadSucceeded(false);
        }

        public void rewind(UploadDataSink arg3) {
            this.mUploadBuffer.position(0);
            arg3.onRewindSucceeded();
        }
    }

    interface FileChannelProvider {
        FileChannel getChannel() throws IOException;
    }

    final class FileUploadProvider extends UploadDataProvider {
        private volatile FileChannel mChannel;
        private final Object mLock;
        private final FileChannelProvider mProvider;

        FileUploadProvider(FileChannelProvider arg1, org.chromium.net.UploadDataProviders$1 arg2) {
            this(arg1);
        }

        private FileUploadProvider(FileChannelProvider arg2) {
            super();
            this.mLock = new Object();
            this.mProvider = arg2;
        }

        public void close() throws IOException {
            FileChannel v0 = this.mChannel;
            if(v0 != null) {
                v0.close();
            }
        }

        private FileChannel getChannel() throws IOException {
            if(this.mChannel == null) {
                Object v0 = this.mLock;
                __monitor_enter(v0);
                try {
                    if(this.mChannel == null) {
                        this.mChannel = this.mProvider.getChannel();
                    }

                    __monitor_exit(v0);
                    goto label_14;
                label_12:
                    __monitor_exit(v0);
                }
                catch(Throwable v1) {
                    goto label_12;
                }

                throw v1;
            }

        label_14:
            return this.mChannel;
        }

        public long getLength() throws IOException {
            return this.getChannel().size();
        }

        public void read(UploadDataSink arg6, ByteBuffer arg7) throws IOException {
            if(!arg7.hasRemaining()) {
                throw new IllegalStateException("Cronet passed a buffer with no bytes remaining");
            }

            FileChannel v0 = this.getChannel();
            int v2 = 0;
            while(v2 == 0) {
                int v3 = v0.read(arg7);
                if(v3 == -1) {
                }
                else {
                    v2 += v3;
                    continue;
                }

                break;
            }

            arg6.onReadSucceeded(false);
        }

        public void rewind(UploadDataSink arg4) throws IOException {
            this.getChannel().position(0);
            arg4.onRewindSucceeded();
        }
    }

    private UploadDataProviders() {
        super();
    }

    public static UploadDataProvider create(ParcelFileDescriptor arg2) {
        return new FileUploadProvider(new FileChannelProvider(arg2) {
            public FileChannel getChannel() throws IOException {
                if(this.val$fd.getStatSize() != -1) {
                    return new ParcelFileDescriptor$AutoCloseInputStream(this.val$fd).getChannel();
                }

                this.val$fd.close();
                StringBuilder v1 = new StringBuilder();
                v1.append("Not a file: ");
                v1.append(this.val$fd);
                throw new IllegalArgumentException(v1.toString());
            }
        }, null);
    }

    public static UploadDataProvider create(File arg2) {
        return new FileUploadProvider(new FileChannelProvider(arg2) {
            public FileChannel getChannel() throws IOException {
                return new FileInputStream(this.val$file).getChannel();
            }
        }, null);
    }

    public static UploadDataProvider create(ByteBuffer arg2) {
        return new ByteBufferUploadProvider(arg2.slice(), null);
    }

    public static UploadDataProvider create(byte[] arg2) {
        return UploadDataProviders.create(arg2, 0, arg2.length);
    }

    public static UploadDataProvider create(byte[] arg1, int arg2, int arg3) {
        return new ByteBufferUploadProvider(ByteBuffer.wrap(arg1, arg2, arg3).slice(), null);
    }
}

