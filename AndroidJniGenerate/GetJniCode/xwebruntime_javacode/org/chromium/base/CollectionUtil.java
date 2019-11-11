package org.chromium.base;

import android.support.annotation.NonNull;
import android.util.Pair;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class CollectionUtil {
    private CollectionUtil() {
        super();
    }

    public static boolean[] booleanListToBooleanArray(@NonNull List arg3) {
        boolean[] v0 = new boolean[arg3.size()];
        int v1;
        for(v1 = 0; v1 < arg3.size(); ++v1) {
            v0[v1] = arg3.get(v1).booleanValue();
        }

        return v0;
    }

    public static void forEach(Collection arg1, Callback arg2) {
        Iterator v1 = arg1.iterator();
        while(v1.hasNext()) {
            arg2.onResult(v1.next());
        }
    }

    public static void forEach(Map arg1, Callback arg2) {
        Iterator v1 = arg1.entrySet().iterator();
        while(v1.hasNext()) {
            arg2.onResult(v1.next());
        }
    }

    public static int[] integerListToIntArray(@NonNull List arg3) {
        int[] v0 = new int[arg3.size()];
        int v1;
        for(v1 = 0; v1 < arg3.size(); ++v1) {
            v0[v1] = arg3.get(v1).intValue();
        }

        return v0;
    }

    public static long[] longListToLongArray(@NonNull List arg4) {
        long[] v0 = new long[arg4.size()];
        int v1;
        for(v1 = 0; v1 < arg4.size(); ++v1) {
            v0[v1] = arg4.get(v1).longValue();
        }

        return v0;
    }

    @VisibleForTesting public static ArrayList newArrayList(Iterable arg2) {
        ArrayList v0 = new ArrayList();
        Iterator v2 = arg2.iterator();
        while(v2.hasNext()) {
            v0.add(v2.next());
        }

        return v0;
    }

    @SafeVarargs public static ArrayList newArrayList(Object[] arg2) {
        ArrayList v0 = new ArrayList(arg2.length);
        Collections.addAll(((Collection)v0), arg2);
        return v0;
    }

    @SafeVarargs public static HashMap newHashMap(Pair[] arg5) {
        HashMap v0 = new HashMap();
        int v1 = arg5.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            v0.put(arg5[v2].first, arg5[v2].second);
        }

        return v0;
    }

    @SafeVarargs public static HashSet newHashSet(Object[] arg2) {
        HashSet v0 = new HashSet(arg2.length);
        Collections.addAll(((Collection)v0), arg2);
        return v0;
    }
}

