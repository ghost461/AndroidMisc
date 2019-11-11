package android.support.v4.util;

import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import java.io.PrintWriter;

@RestrictTo(value={Scope.LIBRARY_GROUP}) public final class TimeUtils {
    @RestrictTo(value={Scope.LIBRARY_GROUP}) public static final int HUNDRED_DAY_FIELD_LEN = 19;
    private static final int SECONDS_PER_DAY = 86400;
    private static final int SECONDS_PER_HOUR = 3600;
    private static final int SECONDS_PER_MINUTE = 60;
    private static char[] sFormatStr;
    private static final Object sFormatSync;

    static {
        TimeUtils.sFormatSync = new Object();
        TimeUtils.sFormatStr = new char[24];
    }

    private TimeUtils() {
        super();
    }

    private static int accumField(int arg2, int arg3, boolean arg4, int arg5) {
        int v0 = 3;
        if(arg2 <= 99 && (!arg4 || arg5 < v0)) {
            int v1 = 2;
            if(arg2 <= 9 && (!arg4 || arg5 < v1)) {
                if(!arg4) {
                    if(arg2 > 0) {
                    }
                    else {
                        return 0;
                    }
                }

                return arg3 + 1;
            }

            return arg3 + v1;
        }

        return arg3 + v0;
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public static void formatDuration(long arg3, long arg5, PrintWriter arg7) {
        if(arg3 == 0) {
            arg7.print("--");
            return;
        }

        TimeUtils.formatDuration(arg3 - arg5, arg7, 0);
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public static void formatDuration(long arg2, PrintWriter arg4, int arg5) {
        Object v0 = TimeUtils.sFormatSync;
        __monitor_enter(v0);
        try {
            arg4.print(new String(TimeUtils.sFormatStr, 0, TimeUtils.formatDurationLocked(arg2, arg5)));
            __monitor_exit(v0);
            return;
        label_11:
            __monitor_exit(v0);
        }
        catch(Throwable v2) {
            goto label_11;
        }

        throw v2;
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public static void formatDuration(long arg1, PrintWriter arg3) {
        TimeUtils.formatDuration(arg1, arg3, 0);
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public static void formatDuration(long arg2, StringBuilder arg4) {
        Object v0 = TimeUtils.sFormatSync;
        __monitor_enter(v0);
        try {
            arg4.append(TimeUtils.sFormatStr, 0, TimeUtils.formatDurationLocked(arg2, 0));
            __monitor_exit(v0);
            return;
        label_9:
            __monitor_exit(v0);
        }
        catch(Throwable v2) {
            goto label_9;
        }

        throw v2;
    }

    private static int formatDurationLocked(long arg18, int arg20) {
        int v8_1;
        boolean v8;
        int v13;
        int v6;
        int v5;
        char v4_1;
        int v0_1;
        long v0 = arg18;
        int v2 = arg20;
        if(TimeUtils.sFormatStr.length < v2) {
            TimeUtils.sFormatStr = new char[v2];
        }

        char[] v3 = TimeUtils.sFormatStr;
        long v4 = 0;
        char v7 = ' ';
        if(Long.compare(v0, v4) == 0) {
            v0_1 = v2 - 1;
            while(v0_1 > 0) {
                v3[0] = v7;
            }

            v3[0] = '0';
            return 1;
        }

        if(v0 > v4) {
            v4_1 = '+';
        }
        else {
            v4_1 = '-';
            v0 = -v0;
        }

        int v12 = ((int)(v0 % 1000));
        v0_1 = ((int)Math.floor(((double)(v0 / 1000))));
        int v1 = 86400;
        if(v0_1 > v1) {
            v5 = v0_1 / v1;
            v0_1 -= v1 * v5;
        }
        else {
            v5 = 0;
        }

        if(v0_1 > 3600) {
            v1 = v0_1 / 3600;
            v0_1 -= v1 * 3600;
        }
        else {
            v1 = 0;
        }

        if(v0_1 > 60) {
            v6 = v0_1 / 60;
            v13 = v0_1 - v6 * 60;
            v0_1 = v6;
        }
        else {
            v13 = v0_1;
            v0_1 = 0;
        }

        int v15 = 2;
        if(v2 != 0) {
            v6 = TimeUtils.accumField(v5, 1, false, 0);
            v8 = v6 > 0 ? true : false;
            v6 += TimeUtils.accumField(v1, 1, v8, v15);
            v8 = v6 > 0 ? true : false;
            v6 += TimeUtils.accumField(v0_1, 1, v8, v15);
            v8 = v6 > 0 ? true : false;
            v6 += TimeUtils.accumField(v13, 1, v8, v15);
            v8_1 = v6 > 0 ? 3 : 0;
            v6 += TimeUtils.accumField(v12, v15, true, v8_1) + 1;
            v8_1 = 0;
            while(v6 < v2) {
                v3[v8_1] = v7;
                ++v8_1;
                ++v6;
            }
        }
        else {
            v8_1 = 0;
        }

        v3[v8_1] = v4_1;
        int v9 = v8_1 + 1;
        v2 = v2 != 0 ? 1 : 0;
        int v11 = v9;
        int v7_1 = TimeUtils.printField(v3, v5, 'd', v9, false, 0);
        char v6_1 = 'h';
        v8 = v7_1 != v11 ? true : false;
        v9 = v2 != 0 ? 2 : 0;
        v7_1 = TimeUtils.printField(v3, v1, v6_1, v7_1, v8, v9);
        v6_1 = 'm';
        v8 = v7_1 != v11 ? true : false;
        v9 = v2 != 0 ? 2 : 0;
        v7_1 = TimeUtils.printField(v3, v0_1, v6_1, v7_1, v8, v9);
        v6_1 = 's';
        v8 = v7_1 != v11 ? true : false;
        v9 = v2 != 0 ? 2 : 0;
        v7_1 = TimeUtils.printField(v3, v13, v6_1, v7_1, v8, v9);
        v6_1 = 'm';
        v9 = v2 == 0 || v7_1 == v11 ? 0 : 3;
        v0_1 = TimeUtils.printField(v3, v12, v6_1, v7_1, true, v9);
        v3[v0_1] = 's';
        return v0_1 + 1;
    }

    private static int printField(char[] arg2, int arg3, char arg4, int arg5, boolean arg6, int arg7) {
        int v1;
        if((arg6) || arg3 > 0) {
            if((arg6) && arg7 >= 3 || arg3 > 99) {
                int v0 = arg3 / 100;
                arg2[arg5] = ((char)(v0 + 0x30));
                v1 = arg5 + 1;
                arg3 -= v0 * 100;
            }
            else {
                v1 = arg5;
            }

            if((arg6) && arg7 >= 2 || (arg3 > 9 || arg5 != v1)) {
                arg5 = arg3 / 10;
                arg2[v1] = ((char)(arg5 + 0x30));
                ++v1;
                arg3 -= arg5 * 10;
            }

            arg2[v1] = ((char)(arg3 + 0x30));
            ++v1;
            arg2[v1] = arg4;
            arg5 = v1 + 1;
        }

        return arg5;
    }
}

