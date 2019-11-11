package android.support.v7.content.res;

import java.lang.reflect.Array;

final class GrowingArrayUtils {
    static {
    }

    private GrowingArrayUtils() {
        super();
    }

    public static int[] append(int[] arg2, int arg3, int arg4) {
        if(arg3 + 1 > arg2.length) {
            int[] v0 = new int[GrowingArrayUtils.growSize(arg3)];
            System.arraycopy(arg2, 0, v0, 0, arg3);
            arg2 = v0;
        }

        arg2[arg3] = arg4;
        return arg2;
    }

    public static Object[] append(Object[] arg2, int arg3, Object arg4) {
        Object v2;
        if(arg3 + 1 > arg2.length) {
            Object v0 = Array.newInstance(arg2.getClass().getComponentType(), GrowingArrayUtils.growSize(arg3));
            System.arraycopy(arg2, 0, v0, 0, arg3);
            v2 = v0;
        }

        ((Object[])v2)[arg3] = arg4;
        return ((Object[])v2);
    }

    public static long[] append(long[] arg2, int arg3, long arg4) {
        if(arg3 + 1 > arg2.length) {
            long[] v0 = new long[GrowingArrayUtils.growSize(arg3)];
            System.arraycopy(arg2, 0, v0, 0, arg3);
            arg2 = v0;
        }

        arg2[arg3] = arg4;
        return arg2;
    }

    public static boolean[] append(boolean[] arg2, int arg3, boolean arg4) {
        if(arg3 + 1 > arg2.length) {
            boolean[] v0 = new boolean[GrowingArrayUtils.growSize(arg3)];
            System.arraycopy(arg2, 0, v0, 0, arg3);
            arg2 = v0;
        }

        arg2[arg3] = arg4;
        return arg2;
    }

    public static int growSize(int arg1) {
        if(arg1 <= 4) {
            arg1 = 8;
        }
        else {
            arg1 *= 2;
        }

        return arg1;
    }

    public static int[] insert(int[] arg2, int arg3, int arg4, int arg5) {
        if(arg3 + 1 <= arg2.length) {
            System.arraycopy(arg2, arg4, arg2, arg4 + 1, arg3 - arg4);
            arg2[arg4] = arg5;
            return arg2;
        }

        int[] v3 = new int[GrowingArrayUtils.growSize(arg3)];
        System.arraycopy(arg2, 0, v3, 0, arg4);
        v3[arg4] = arg5;
        System.arraycopy(arg2, arg4, v3, arg4 + 1, arg2.length - arg4);
        return v3;
    }

    public static long[] insert(long[] arg2, int arg3, int arg4, long arg5) {
        if(arg3 + 1 <= arg2.length) {
            System.arraycopy(arg2, arg4, arg2, arg4 + 1, arg3 - arg4);
            arg2[arg4] = arg5;
            return arg2;
        }

        long[] v3 = new long[GrowingArrayUtils.growSize(arg3)];
        System.arraycopy(arg2, 0, v3, 0, arg4);
        v3[arg4] = arg5;
        System.arraycopy(arg2, arg4, v3, arg4 + 1, arg2.length - arg4);
        return v3;
    }

    public static Object[] insert(Object[] arg2, int arg3, int arg4, Object arg5) {
        if(arg3 + 1 <= arg2.length) {
            System.arraycopy(arg2, arg4, arg2, arg4 + 1, arg3 - arg4);
            arg2[arg4] = arg5;
            return arg2;
        }

        Object v3 = Array.newInstance(arg2.getClass().getComponentType(), GrowingArrayUtils.growSize(arg3));
        System.arraycopy(arg2, 0, v3, 0, arg4);
        v3[arg4] = arg5;
        System.arraycopy(arg2, arg4, v3, arg4 + 1, arg2.length - arg4);
        return ((Object[])v3);
    }

    public static boolean[] insert(boolean[] arg2, int arg3, int arg4, boolean arg5) {
        if(arg3 + 1 <= arg2.length) {
            System.arraycopy(arg2, arg4, arg2, arg4 + 1, arg3 - arg4);
            arg2[arg4] = arg5;
            return arg2;
        }

        boolean[] v3 = new boolean[GrowingArrayUtils.growSize(arg3)];
        System.arraycopy(arg2, 0, v3, 0, arg4);
        v3[arg4] = arg5;
        System.arraycopy(arg2, arg4, v3, arg4 + 1, arg2.length - arg4);
        return v3;
    }
}

