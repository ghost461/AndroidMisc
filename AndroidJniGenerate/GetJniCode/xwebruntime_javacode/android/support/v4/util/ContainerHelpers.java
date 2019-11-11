package android.support.v4.util;

class ContainerHelpers {
    static final int[] EMPTY_INTS;
    static final long[] EMPTY_LONGS;
    static final Object[] EMPTY_OBJECTS;

    static {
        ContainerHelpers.EMPTY_INTS = new int[0];
        ContainerHelpers.EMPTY_LONGS = new long[0];
        ContainerHelpers.EMPTY_OBJECTS = new Object[0];
    }

    ContainerHelpers() {
        super();
    }

    static int binarySearch(int[] arg3, int arg4, int arg5) {
        int v1;
        --arg4;
        int v0 = 0;
        while(true) {
            if(v0 > arg4) {
                goto label_15;
            }

            v1 = v0 + arg4 >>> 1;
            int v2 = arg3[v1];
            if(v2 < arg5) {
                v0 = v1 + 1;
                continue;
            }

            if(v2 <= arg5) {
                return v1;
            }

            arg4 = v1 - 1;
        }

        return v1;
    label_15:
        return v0 ^ -1;
    }

    static int binarySearch(long[] arg5, int arg6, long arg7) {
        int v1;
        --arg6;
        int v0 = 0;
        while(true) {
            if(v0 > arg6) {
                goto label_15;
            }

            v1 = v0 + arg6 >>> 1;
            long v2 = arg5[v1];
            if(v2 < arg7) {
                v0 = v1 + 1;
                continue;
            }

            if(v2 <= arg7) {
                return v1;
            }

            arg6 = v1 - 1;
        }

        return v1;
    label_15:
        return v0 ^ -1;
    }

    public static boolean equal(Object arg0, Object arg1) {
        boolean v0;
        if(arg0 != arg1) {
            if(arg0 != null && (arg0.equals(arg1))) {
                goto label_7;
            }

            v0 = false;
        }
        else {
        label_7:
            v0 = true;
        }

        return v0;
    }

    public static int idealByteArraySize(int arg2) {
        int v0;
        for(v0 = 4; v0 < 0x20; ++v0) {
            int v1 = (1 << v0) - 12;
            if(arg2 <= v1) {
                return v1;
            }
        }

        return arg2;
    }

    public static int idealIntArraySize(int arg0) {
        return ContainerHelpers.idealByteArraySize(arg0 * 4) / 4;
    }

    public static int idealLongArraySize(int arg0) {
        return ContainerHelpers.idealByteArraySize(arg0 * 8) / 8;
    }
}

