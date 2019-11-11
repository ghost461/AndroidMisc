package org.chromium.mojo.system;

import java.nio.ByteBuffer;

public interface DataPipe {
    public interface ConsumerHandle extends Handle {
        ByteBuffer beginReadData(int arg1, ReadFlags arg2);

        int discardData(int arg1, ReadFlags arg2);

        void endReadData(int arg1);

        ConsumerHandle pass();

        ResultAnd readData(ByteBuffer arg1, ReadFlags arg2);
    }

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
        private int mCapacityNumBytes;
        private int mElementNumBytes;
        private CreateFlags mFlags;

        public CreateOptions() {
            super();
            this.mFlags = CreateFlags.none();
        }

        public int getCapacityNumBytes() {
            return this.mCapacityNumBytes;
        }

        public int getElementNumBytes() {
            return this.mElementNumBytes;
        }

        public CreateFlags getFlags() {
            return this.mFlags;
        }

        public void setCapacityNumBytes(int arg1) {
            this.mCapacityNumBytes = arg1;
        }

        public void setElementNumBytes(int arg1) {
            this.mElementNumBytes = arg1;
        }
    }

    public interface ProducerHandle extends Handle {
        ByteBuffer beginWriteData(int arg1, WriteFlags arg2);

        void endWriteData(int arg1);

        ProducerHandle pass();

        ResultAnd writeData(ByteBuffer arg1, WriteFlags arg2);
    }

    public class ReadFlags extends Flags {
        private static final int FLAG_ALL_OR_NONE = 1;
        private static final int FLAG_NONE = 0;
        private static final int FLAG_PEEK = 8;
        private static final int FLAG_QUERY = 4;
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

        public ReadFlags peek(boolean arg2) {
            return this.setFlag(8, arg2);
        }

        public ReadFlags query(boolean arg2) {
            return this.setFlag(4, arg2);
        }

        public ReadFlags setAllOrNone(boolean arg2) {
            return this.setFlag(1, arg2);
        }
    }

    public class WriteFlags extends Flags {
        private static final int FLAG_ALL_OR_NONE = 1;
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

        public WriteFlags setAllOrNone(boolean arg2) {
            return this.setFlag(1, arg2);
        }
    }

}

