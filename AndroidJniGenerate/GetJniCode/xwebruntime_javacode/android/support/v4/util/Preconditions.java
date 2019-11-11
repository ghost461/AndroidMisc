package android.support.v4.util;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.text.TextUtils;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;

@RestrictTo(value={Scope.LIBRARY_GROUP}) public class Preconditions {
    public Preconditions() {
        super();
    }

    public static void checkArgument(boolean arg0) {
        if(!arg0) {
            throw new IllegalArgumentException();
        }
    }

    public static void checkArgument(boolean arg0, Object arg1) {
        if(!arg0) {
            throw new IllegalArgumentException(String.valueOf(arg1));
        }
    }

    public static float checkArgumentFinite(float arg1, String arg2) {
        StringBuilder v0;
        if(Float.isNaN(arg1)) {
            v0 = new StringBuilder();
            v0.append(arg2);
            v0.append(" must not be NaN");
            throw new IllegalArgumentException(v0.toString());
        }

        if(Float.isInfinite(arg1)) {
            v0 = new StringBuilder();
            v0.append(arg2);
            v0.append(" must not be infinite");
            throw new IllegalArgumentException(v0.toString());
        }

        return arg1;
    }

    public static float checkArgumentInRange(float arg6, float arg7, float arg8, String arg9) {
        Object[] v4_1;
        Locale v0;
        if(Float.isNaN(arg6)) {
            StringBuilder v7 = new StringBuilder();
            v7.append(arg9);
            v7.append(" must not be NaN");
            throw new IllegalArgumentException(v7.toString());
        }

        int v1 = 2;
        int v4 = 3;
        if(Float.compare(arg6, arg7) < 0) {
            v0 = Locale.US;
            v4_1 = new Object[v4];
            v4_1[0] = arg9;
            v4_1[1] = Float.valueOf(arg7);
            v4_1[v1] = Float.valueOf(arg8);
            throw new IllegalArgumentException(String.format(v0, "%s is out of range of [%f, %f] (too low)", v4_1));
        }

        if(arg6 > arg8) {
            v0 = Locale.US;
            v4_1 = new Object[v4];
            v4_1[0] = arg9;
            v4_1[1] = Float.valueOf(arg7);
            v4_1[v1] = Float.valueOf(arg8);
            throw new IllegalArgumentException(String.format(v0, "%s is out of range of [%f, %f] (too high)", v4_1));
        }

        return arg6;
    }

    public static int checkArgumentInRange(int arg6, int arg7, int arg8, String arg9) {
        Object[] v3_1;
        Locale v4;
        int v0 = 2;
        int v3 = 3;
        if(arg6 < arg7) {
            v4 = Locale.US;
            v3_1 = new Object[v3];
            v3_1[0] = arg9;
            v3_1[1] = Integer.valueOf(arg7);
            v3_1[v0] = Integer.valueOf(arg8);
            throw new IllegalArgumentException(String.format(v4, "%s is out of range of [%d, %d] (too low)", v3_1));
        }

        if(arg6 > arg8) {
            v4 = Locale.US;
            v3_1 = new Object[v3];
            v3_1[0] = arg9;
            v3_1[1] = Integer.valueOf(arg7);
            v3_1[v0] = Integer.valueOf(arg8);
            throw new IllegalArgumentException(String.format(v4, "%s is out of range of [%d, %d] (too high)", v3_1));
        }

        return arg6;
    }

    public static long checkArgumentInRange(long arg5, long arg7, long arg9, String arg11) {
        Object[] v4_1;
        Locale v6;
        int v1 = 2;
        int v4 = 3;
        if(Long.compare(arg5, arg7) < 0) {
            v6 = Locale.US;
            v4_1 = new Object[v4];
            v4_1[0] = arg11;
            v4_1[1] = Long.valueOf(arg7);
            v4_1[v1] = Long.valueOf(arg9);
            throw new IllegalArgumentException(String.format(v6, "%s is out of range of [%d, %d] (too low)", v4_1));
        }

        if(arg5 > arg9) {
            v6 = Locale.US;
            v4_1 = new Object[v4];
            v4_1[0] = arg11;
            v4_1[1] = Long.valueOf(arg7);
            v4_1[v1] = Long.valueOf(arg9);
            throw new IllegalArgumentException(String.format(v6, "%s is out of range of [%d, %d] (too high)", v4_1));
        }

        return arg5;
    }

    @IntRange(from=0) public static int checkArgumentNonnegative(int arg0) {
        if(arg0 < 0) {
            throw new IllegalArgumentException();
        }

        return arg0;
    }

    @IntRange(from=0) public static int checkArgumentNonnegative(int arg0, String arg1) {
        if(arg0 < 0) {
            throw new IllegalArgumentException(arg1);
        }

        return arg0;
    }

    public static long checkArgumentNonnegative(long arg3) {
        if(arg3 < 0) {
            throw new IllegalArgumentException();
        }

        return arg3;
    }

    public static long checkArgumentNonnegative(long arg3, String arg5) {
        if(arg3 < 0) {
            throw new IllegalArgumentException(arg5);
        }

        return arg3;
    }

    public static int checkArgumentPositive(int arg0, String arg1) {
        if(arg0 <= 0) {
            throw new IllegalArgumentException(arg1);
        }

        return arg0;
    }

    public static float[] checkArrayElementsInRange(float[] arg8, float arg9, float arg10, String arg11) {
        Object[] v6_1;
        Locale v2_1;
        Preconditions.checkNotNull(arg8, arg11 + " must not be null");
        int v1;
        for(v1 = 0; v1 < arg8.length; ++v1) {
            float v2 = arg8[v1];
            if(Float.isNaN(v2)) {
                StringBuilder v9 = new StringBuilder();
                v9.append(arg11);
                v9.append("[");
                v9.append(v1);
                v9.append("] must not be NaN");
                throw new IllegalArgumentException(v9.toString());
            }

            int v4 = 3;
            int v5 = 2;
            int v6 = 4;
            if(Float.compare(v2, arg9) < 0) {
                v2_1 = Locale.US;
                v6_1 = new Object[v6];
                v6_1[0] = arg11;
                v6_1[1] = Integer.valueOf(v1);
                v6_1[v5] = Float.valueOf(arg9);
                v6_1[v4] = Float.valueOf(arg10);
                throw new IllegalArgumentException(String.format(v2_1, "%s[%d] is out of range of [%f, %f] (too low)", v6_1));
            }

            if(v2 > arg10) {
                v2_1 = Locale.US;
                v6_1 = new Object[v6];
                v6_1[0] = arg11;
                v6_1[1] = Integer.valueOf(v1);
                v6_1[v5] = Float.valueOf(arg9);
                v6_1[v4] = Float.valueOf(arg10);
                throw new IllegalArgumentException(String.format(v2_1, "%s[%d] is out of range of [%f, %f] (too high)", v6_1));
            }
        }

        return arg8;
    }

    public static Object[] checkArrayElementsNotNull(Object[] arg5, String arg6) {
        if(arg5 == null) {
            StringBuilder v0 = new StringBuilder();
            v0.append(arg6);
            v0.append(" must not be null");
            throw new NullPointerException(v0.toString());
        }

        int v1;
        for(v1 = 0; v1 < arg5.length; ++v1) {
            if(arg5[v1] == null) {
                throw new NullPointerException(String.format(Locale.US, "%s[%d] must not be null", arg6, Integer.valueOf(v1)));
            }
        }

        return arg5;
    }

    @NonNull public static Collection checkCollectionElementsNotNull(Collection arg7, String arg8) {
        if(arg7 == null) {
            StringBuilder v0 = new StringBuilder();
            v0.append(arg8);
            v0.append(" must not be null");
            throw new NullPointerException(v0.toString());
        }

        long v0_1 = 0;
        Iterator v2 = arg7.iterator();
        while(v2.hasNext()) {
            if(v2.next() == null) {
                throw new NullPointerException(String.format(Locale.US, "%s[%d] must not be null", arg8, Long.valueOf(v0_1)));
            }

            ++v0_1;
        }

        return arg7;
    }

    public static Collection checkCollectionNotEmpty(Collection arg1, String arg2) {
        StringBuilder v0;
        if(arg1 == null) {
            v0 = new StringBuilder();
            v0.append(arg2);
            v0.append(" must not be null");
            throw new NullPointerException(v0.toString());
        }

        if(arg1.isEmpty()) {
            v0 = new StringBuilder();
            v0.append(arg2);
            v0.append(" is empty");
            throw new IllegalArgumentException(v0.toString());
        }

        return arg1;
    }

    public static int checkFlagsArgument(int arg3, int arg4) {
        if((arg3 & arg4) != arg3) {
            StringBuilder v1 = new StringBuilder();
            v1.append("Requested flags 0x");
            v1.append(Integer.toHexString(arg3));
            v1.append(", but only 0x");
            v1.append(Integer.toHexString(arg4));
            v1.append(" are allowed");
            throw new IllegalArgumentException(v1.toString());
        }

        return arg3;
    }

    @NonNull public static Object checkNotNull(Object arg0) {
        if(arg0 == null) {
            throw new NullPointerException();
        }

        return arg0;
    }

    @NonNull public static Object checkNotNull(Object arg0, Object arg1) {
        if(arg0 == null) {
            throw new NullPointerException(String.valueOf(arg1));
        }

        return arg0;
    }

    public static void checkState(boolean arg1) {
        Preconditions.checkState(arg1, null);
    }

    public static void checkState(boolean arg0, String arg1) {
        if(!arg0) {
            throw new IllegalStateException(arg1);
        }
    }

    @NonNull public static CharSequence checkStringNotEmpty(CharSequence arg1) {
        if(TextUtils.isEmpty(arg1)) {
            throw new IllegalArgumentException();
        }

        return arg1;
    }

    @NonNull public static CharSequence checkStringNotEmpty(CharSequence arg1, Object arg2) {
        if(TextUtils.isEmpty(arg1)) {
            throw new IllegalArgumentException(String.valueOf(arg2));
        }

        return arg1;
    }
}

