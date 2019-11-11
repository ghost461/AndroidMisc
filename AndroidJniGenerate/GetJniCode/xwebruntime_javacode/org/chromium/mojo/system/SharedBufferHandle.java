package org.chromium.mojo.system;

import java.nio.ByteBuffer;

public interface SharedBufferHandle extends Handle {
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

    public class DuplicateFlags extends Flags {
        private static final int FLAG_NONE;
        public static final DuplicateFlags NONE;

        static {
            DuplicateFlags.NONE = DuplicateFlags.none().immutable();
        }

        protected DuplicateFlags(int arg1) {
            super(arg1);
        }

        public static DuplicateFlags none() {
            return new DuplicateFlags(0);
        }
    }

    public class DuplicateOptions {
        private DuplicateFlags mFlags;

        public DuplicateOptions() {
            super();
            this.mFlags = DuplicateFlags.NONE;
        }

        public DuplicateFlags getFlags() {
            return this.mFlags;
        }
    }

    public class MapFlags extends Flags {
        private static final int FLAG_NONE;
        public static final MapFlags NONE;

        static {
            MapFlags.NONE = MapFlags.none().immutable();
        }

        protected MapFlags(int arg1) {
            super(arg1);
        }

        public static MapFlags none() {
            return new MapFlags(0);
        }
    }

    SharedBufferHandle duplicate(DuplicateOptions arg1);

    ByteBuffer map(long arg1, long arg2, MapFlags arg3);

    SharedBufferHandle pass();

    void unmap(ByteBuffer arg1);
}

