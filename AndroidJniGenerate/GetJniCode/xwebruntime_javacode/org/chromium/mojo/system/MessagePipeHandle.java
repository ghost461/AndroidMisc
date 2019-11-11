package org.chromium.mojo.system;

import java.nio.ByteBuffer;
import java.util.List;

public interface MessagePipeHandle extends Handle {
    public class CreateFlags extends Flags {
        private static final int FLAG_NONE;
        public static final CreateFlags NONE;

        static {
            CreateFlags.NONE = CreateFlags.none().immutable();
        }

        protected CreateFlags(int arg1) {
            super(arg1);
        }

        public static CreateFlags none() {
            return new CreateFlags(0);
        }
    }

    public class CreateOptions {
        private CreateFlags mFlags;

        public CreateOptions() {
            super();
            this.mFlags = CreateFlags.NONE;
        }

        public CreateFlags getFlags() {
            return this.mFlags;
        }
    }

    public class ReadFlags extends Flags {
        private static final int FLAG_NONE;
        public static final ReadFlags NONE;

        static {
            ReadFlags.NONE = ReadFlags.none().immutable();
        }

        private ReadFlags(int arg1) {
            super(arg1);
        }

        public static ReadFlags none() {
            return new ReadFlags(0);
        }
    }

    public class ReadMessageResult {
        public byte[] mData;
        public List mHandles;
        public int[] mRawHandles;

        public ReadMessageResult() {
            super();
        }
    }

    public class WriteFlags extends Flags {
        private static final int FLAG_NONE;
        public static final WriteFlags NONE;

        static {
            WriteFlags.NONE = WriteFlags.none().immutable();
        }

        private WriteFlags(int arg1) {
            super(arg1);
        }

        public static WriteFlags none() {
            return new WriteFlags(0);
        }
    }

    MessagePipeHandle pass();

    ResultAnd readMessage(ReadFlags arg1);

    void writeMessage(ByteBuffer arg1, List arg2, WriteFlags arg3);
}

