package org.chromium.mojo.bindings;

import org.chromium.mojo.system.Handle;
import org.chromium.mojo.system.Watcher;

public class BindingsHelper {
    public static final int ALIGNMENT = 8;
    public static final int ARRAY_NULLABLE = 1;
    public static final int ELEMENT_NULLABLE = 2;
    public static final DataHeader MAP_STRUCT_HEADER = null;
    public static final int NOTHING_NULLABLE = 0;
    public static final int POINTER_SIZE = 8;
    public static final int SERIALIZED_HANDLE_SIZE = 4;
    public static final int SERIALIZED_INTERFACE_SIZE = 8;
    public static final int UNION_SIZE = 16;
    public static final int UNSPECIFIED_ARRAY_LENGTH = -1;

    static {
        BindingsHelper.MAP_STRUCT_HEADER = new DataHeader(24, 0);
    }

    public BindingsHelper() {
        super();
    }

    public static int align(int arg0) {
        return arg0 + 7 & -8;
    }

    public static long align(long arg4) {
        return arg4 + 8 - 1 & -8;
    }

    public static boolean equals(Object arg0, Object arg1) {
        if(arg0 == arg1) {
            return 1;
        }

        if(arg0 == null) {
            return 0;
        }

        return arg0.equals(arg1);
    }

    static Watcher getWatcherForHandle(Handle arg1) {
        if(arg1.getCore() != null) {
            return arg1.getCore().getWatcher();
        }

        return null;
    }

    public static int hashCode(double arg0) {
        return BindingsHelper.hashCode(Double.doubleToLongBits(arg0));
    }

    public static int hashCode(long arg4) {
        return ((int)(arg4 ^ arg4 >>> 0x20));
    }

    public static int hashCode(float arg0) {
        return Float.floatToIntBits(arg0);
    }

    public static int hashCode(int arg0) {
        return arg0;
    }

    public static int hashCode(Object arg0) {
        if(arg0 == null) {
            return 0;
        }

        return arg0.hashCode();
    }

    public static int hashCode(boolean arg0) {
        int v0 = arg0 ? 0x4CF : 0x4D5;
        return v0;
    }

    public static boolean isArrayNullable(int arg1) {
        boolean v0 = true;
        if((arg1 & 1) > 0) {
        }
        else {
            v0 = false;
        }

        return v0;
    }

    public static boolean isElementNullable(int arg0) {
        boolean v0 = (arg0 & 2) > 0 ? true : false;
        return v0;
    }

    private static boolean isSurrogate(char arg1) {
        boolean v1 = arg1 < 0xD800 || arg1 >= 0xE000 ? false : true;
        return v1;
    }

    public static int utf8StringSizeInBytes(String arg4) {
        int v0 = 0;
        int v1 = 0;
        while(v0 < arg4.length()) {
            char v2 = arg4.charAt(v0);
            if(BindingsHelper.isSurrogate(v2)) {
                ++v0;
                int v2_1 = Character.toCodePoint(v2, arg4.charAt(v0));
            }

            ++v1;
            if(v2_1 > 0x7F) {
                ++v1;
                if(v2_1 > 0x7FF) {
                    ++v1;
                    if(v2_1 > 0xFFFF) {
                        ++v1;
                        if(v2_1 > 0x1FFFFF) {
                            ++v1;
                            if(v2_1 > 0x3FFFFFF) {
                                ++v1;
                            }
                        }
                    }
                }
            }

            ++v0;
        }

        return v1;
    }
}

