package android.support.v4.util;

import java.util.ConcurrentModificationException;
import java.util.Map;

public class SimpleArrayMap {
    private static final int BASE_SIZE = 4;
    private static final int CACHE_SIZE = 10;
    private static final boolean CONCURRENT_MODIFICATION_EXCEPTIONS = true;
    private static final boolean DEBUG = false;
    private static final String TAG = "ArrayMap";
    Object[] mArray;
    static Object[] mBaseCache;
    static int mBaseCacheSize;
    int[] mHashes;
    int mSize;
    static Object[] mTwiceBaseCache;
    static int mTwiceBaseCacheSize;

    public SimpleArrayMap() {
        super();
        this.mHashes = ContainerHelpers.EMPTY_INTS;
        this.mArray = ContainerHelpers.EMPTY_OBJECTS;
        this.mSize = 0;
    }

    public SimpleArrayMap(int arg1) {
        super();
        if(arg1 == 0) {
            this.mHashes = ContainerHelpers.EMPTY_INTS;
            this.mArray = ContainerHelpers.EMPTY_OBJECTS;
        }
        else {
            this.allocArrays(arg1);
        }

        this.mSize = 0;
    }

    public SimpleArrayMap(SimpleArrayMap arg1) {
        this();
        if(arg1 != null) {
            this.putAll(arg1);
        }
    }

    private void allocArrays(int arg6) {
        Object[] v6_1;
        Class v3;
        Object v0 = null;
        if(arg6 == 8) {
            v3 = ArrayMap.class;
            __monitor_enter(v3);
            try {
                if(SimpleArrayMap.mTwiceBaseCache != null) {
                    v6_1 = SimpleArrayMap.mTwiceBaseCache;
                    this.mArray = v6_1;
                    SimpleArrayMap.mTwiceBaseCache = v6_1[0];
                    this.mHashes = v6_1[1];
                    v6_1[1] = v0;
                    v6_1[0] = v0;
                    --SimpleArrayMap.mTwiceBaseCacheSize;
                    __monitor_exit(v3);
                    return;
                }
                else {
                    __monitor_exit(v3);
                    goto label_51;
                label_25:
                    __monitor_exit(v3);
                    goto label_26;
                }

                goto label_51;
            }
            catch(Throwable v6) {
                goto label_25;
            }

        label_26:
            throw v6;
        }
        else if(arg6 == 4) {
            v3 = ArrayMap.class;
            __monitor_enter(v3);
            try {
                if(SimpleArrayMap.mBaseCache != null) {
                    v6_1 = SimpleArrayMap.mBaseCache;
                    this.mArray = v6_1;
                    SimpleArrayMap.mBaseCache = v6_1[0];
                    this.mHashes = v6_1[1];
                    v6_1[1] = v0;
                    v6_1[0] = v0;
                    --SimpleArrayMap.mBaseCacheSize;
                    __monitor_exit(v3);
                    return;
                }
                else {
                    __monitor_exit(v3);
                    goto label_51;
                label_49:
                    __monitor_exit(v3);
                    goto label_50;
                }

                goto label_51;
            }
            catch(Throwable v6) {
                goto label_49;
            }

        label_50:
            throw v6;
        }

    label_51:
        this.mHashes = new int[arg6];
        this.mArray = new Object[arg6 << 1];
    }

    private static int binarySearchHashes(int[] arg0, int arg1, int arg2) {
        try {
            return ContainerHelpers.binarySearch(arg0, arg1, arg2);
        }
        catch(ArrayIndexOutOfBoundsException ) {
            throw new ConcurrentModificationException();
        }
    }

    public void clear() {
        if(this.mSize > 0) {
            int[] v0 = this.mHashes;
            Object[] v1 = this.mArray;
            int v2 = this.mSize;
            this.mHashes = ContainerHelpers.EMPTY_INTS;
            this.mArray = ContainerHelpers.EMPTY_OBJECTS;
            this.mSize = 0;
            SimpleArrayMap.freeArrays(v0, v1, v2);
        }

        if(this.mSize > 0) {
            throw new ConcurrentModificationException();
        }
    }

    public boolean containsKey(Object arg1) {
        boolean v1 = this.indexOfKey(arg1) >= 0 ? true : false;
        return v1;
    }

    public boolean containsValue(Object arg1) {
        boolean v1 = this.indexOfValue(arg1) >= 0 ? true : false;
        return v1;
    }

    public void ensureCapacity(int arg6) {
        int v0 = this.mSize;
        if(this.mHashes.length < arg6) {
            int[] v1 = this.mHashes;
            Object[] v2 = this.mArray;
            this.allocArrays(arg6);
            if(this.mSize > 0) {
                System.arraycopy(v1, 0, this.mHashes, 0, v0);
                System.arraycopy(v2, 0, this.mArray, 0, v0 << 1);
            }

            SimpleArrayMap.freeArrays(v1, v2, v0);
        }

        if(this.mSize != v0) {
            throw new ConcurrentModificationException();
        }
    }

    public boolean equals(Object arg7) {
        Object v5;
        Object v4;
        Object v3;
        if(this == (((SimpleArrayMap)arg7))) {
            return 1;
        }

        if(!(arg7 instanceof SimpleArrayMap)) {
            goto label_29;
        }

        if(this.size() != ((SimpleArrayMap)arg7).size()) {
            return 0;
        }

        int v1 = 0;
        try {
            while(true) {
            label_11:
                if(v1 >= this.mSize) {
                    return 1;
                }

                v3 = this.keyAt(v1);
                v4 = this.valueAt(v1);
                v5 = ((SimpleArrayMap)arg7).get(v3);
                if(v4 == null) {
                    if(v5 == null && (((SimpleArrayMap)arg7).containsKey(v3))) {
                        goto label_24;
                    }

                    return 0;
                }
                else {
                    if(v4.equals(v5)) {
                        goto label_24;
                    }

                    return 0;
                }

                goto label_24;
            }
        }
        catch(ClassCastException ) {
            return 0;
        }
        catch(NullPointerException ) {
            return 0;
        }

        return 0;
    label_24:
        ++v1;
        goto label_11;
        return 1;
    label_29:
        if(!(arg7 instanceof Map)) {
            return 0;
        }

        if(this.size() != ((Map)arg7).size()) {
            return 0;
        }

        v1 = 0;
        try {
            while(true) {
            label_36:
                if(v1 >= this.mSize) {
                    return 1;
                }

                v3 = this.keyAt(v1);
                v4 = this.valueAt(v1);
                v5 = ((Map)arg7).get(v3);
                if(v4 == null) {
                    if(v5 == null && (((Map)arg7).containsKey(v3))) {
                        goto label_49;
                    }

                    return 0;
                }
                else {
                    if(v4.equals(v5)) {
                        goto label_49;
                    }

                    return 0;
                }

                goto label_49;
            }
        }
        catch(ClassCastException ) {
            return 0;
        }
        catch(NullPointerException ) {
            return 0;
        }

        return 0;
    label_49:
        ++v1;
        goto label_36;
        return 1;
    }

    private static void freeArrays(int[] arg7, Object[] arg8, int arg9) {
        Class v0;
        Object v1 = null;
        int v2 = 2;
        int v4 = 10;
        if(arg7.length == 8) {
            v0 = ArrayMap.class;
            __monitor_enter(v0);
            try {
                if(SimpleArrayMap.mTwiceBaseCacheSize < v4) {
                    arg8[0] = SimpleArrayMap.mTwiceBaseCache;
                    arg8[1] = arg7;
                    int v7_1;
                    for(v7_1 = (arg9 << 1) - 1; v7_1 >= v2; --v7_1) {
                        arg8[v7_1] = v1;
                    }

                    SimpleArrayMap.mTwiceBaseCache = arg8;
                    ++SimpleArrayMap.mTwiceBaseCacheSize;
                }

                __monitor_exit(v0);
                return;
            label_28:
                __monitor_exit(v0);
            }
            catch(Throwable v7) {
                goto label_28;
            }

            throw v7;
        }
        else {
            if(arg7.length != 4) {
                return;
            }

            v0 = ArrayMap.class;
            __monitor_enter(v0);
            try {
                if(SimpleArrayMap.mBaseCacheSize < v4) {
                    arg8[0] = SimpleArrayMap.mBaseCache;
                    arg8[1] = arg7;
                    for(v7_1 = (arg9 << 1) - 1; v7_1 >= v2; --v7_1) {
                        arg8[v7_1] = v1;
                    }

                    SimpleArrayMap.mBaseCache = arg8;
                    ++SimpleArrayMap.mBaseCacheSize;
                }

                __monitor_exit(v0);
                return;
            label_53:
                __monitor_exit(v0);
            }
            catch(Throwable v7) {
                goto label_53;
            }

            throw v7;
        }
    }

    public Object get(Object arg2) {
        int v2 = this.indexOfKey(arg2);
        return v2 >= 0 ? this.mArray[(v2 << 1) + 1] : null;
    }

    public int hashCode() {
        int[] v0 = this.mHashes;
        Object[] v1 = this.mArray;
        int v2 = this.mSize;
        int v3 = 0;
        int v5 = 1;
        int v6 = 0;
        while(v3 < v2) {
            Object v7 = v1[v5];
            int v8 = v0[v3];
            int v7_1 = v7 == null ? 0 : v7.hashCode();
            v6 += v7_1 ^ v8;
            ++v3;
            v5 += 2;
        }

        return v6;
    }

    int indexOf(Object arg7, int arg8) {
        int v0 = this.mSize;
        int v1 = -1;
        if(v0 == 0) {
            return v1;
        }

        int v2 = SimpleArrayMap.binarySearchHashes(this.mHashes, v0, arg8);
        if(v2 < 0) {
            return v2;
        }

        if(arg7.equals(this.mArray[v2 << 1])) {
            return v2;
        }

        int v3 = v2 + 1;
        while(v3 < v0) {
            if(this.mHashes[v3] != arg8) {
                break;
            }

            if(arg7.equals(this.mArray[v3 << 1])) {
                return v3;
            }
            else {
                ++v3;
                continue;
            }

            break;
        }

        --v2;
        while(v2 >= 0) {
            if(this.mHashes[v2] != arg8) {
                break;
            }

            if(arg7.equals(this.mArray[v2 << 1])) {
                return v2;
            }
            else {
                --v2;
                continue;
            }

            break;
        }

        return v3 ^ -1;
    }

    public int indexOfKey(Object arg2) {
        int v2 = arg2 == null ? this.indexOfNull() : this.indexOf(arg2, arg2.hashCode());
        return v2;
    }

    int indexOfNull() {
        int v0 = this.mSize;
        int v1 = -1;
        if(v0 == 0) {
            return v1;
        }

        int v2 = SimpleArrayMap.binarySearchHashes(this.mHashes, v0, 0);
        if(v2 < 0) {
            return v2;
        }

        if(this.mArray[v2 << 1] == null) {
            return v2;
        }

        int v3 = v2 + 1;
        while(v3 < v0) {
            if(this.mHashes[v3] != 0) {
                break;
            }

            if(this.mArray[v3 << 1] == null) {
                return v3;
            }
            else {
                ++v3;
                continue;
            }

            break;
        }

        --v2;
        while(v2 >= 0) {
            if(this.mHashes[v2] != 0) {
                break;
            }

            if(this.mArray[v2 << 1] == null) {
                return v2;
            }
            else {
                --v2;
                continue;
            }

            break;
        }

        return v3 ^ -1;
    }

    int indexOfValue(Object arg6) {
        int v0 = this.mSize * 2;
        Object[] v1 = this.mArray;
        if(arg6 == null) {
            int v6 = 1;
            while(v6 < v0) {
                if(v1[v6] == null) {
                    return v6 >> 1;
                }
                else {
                    v6 += 2;
                    continue;
                }

                return -1;
            }
        }
        else {
            int v3 = 1;
            while(v3 < v0) {
                if(arg6.equals(v1[v3])) {
                    return v3 >> 1;
                }
                else {
                    v3 += 2;
                    continue;
                }

                return -1;
            }
        }

        return -1;
    }

    public boolean isEmpty() {
        boolean v0 = this.mSize <= 0 ? true : false;
        return v0;
    }

    public Object keyAt(int arg2) {
        return this.mArray[arg2 << 1];
    }

    public Object put(Object arg10, Object arg11) {
        int v3;
        int v2;
        int v0 = this.mSize;
        if(arg10 == null) {
            v2 = this.indexOfNull();
            v3 = 0;
        }
        else {
            v2 = arg10.hashCode();
            v3 = v2;
            v2 = this.indexOf(arg10, v2);
        }

        if(v2 >= 0) {
            int v10 = (v2 << 1) + 1;
            Object v0_1 = this.mArray[v10];
            this.mArray[v10] = arg11;
            return v0_1;
        }

        v2 ^= -1;
        if(v0 >= this.mHashes.length) {
            int v4 = 4;
            if(v0 >= 8) {
                v4 = (v0 >> 1) + v0;
            }
            else if(v0 >= v4) {
                v4 = 8;
            }

            int[] v5 = this.mHashes;
            Object[] v6 = this.mArray;
            this.allocArrays(v4);
            if(v0 != this.mSize) {
                throw new ConcurrentModificationException();
            }

            if(this.mHashes.length > 0) {
                System.arraycopy(v5, 0, this.mHashes, 0, v5.length);
                System.arraycopy(v6, 0, this.mArray, 0, v6.length);
            }

            SimpleArrayMap.freeArrays(v5, v6, v0);
        }

        if(v2 < v0) {
            int v5_1 = v2 + 1;
            System.arraycopy(this.mHashes, v2, this.mHashes, v5_1, v0 - v2);
            System.arraycopy(this.mArray, v2 << 1, this.mArray, v5_1 << 1, this.mSize - v2 << 1);
        }

        if(v0 == this.mSize) {
            if(v2 >= this.mHashes.length) {
            }
            else {
                this.mHashes[v2] = v3;
                int v1 = v2 << 1;
                this.mArray[v1] = arg10;
                this.mArray[v1 + 1] = arg11;
                ++this.mSize;
                return null;
            }
        }

        throw new ConcurrentModificationException();
    }

    public void putAll(SimpleArrayMap arg5) {
        int v0 = arg5.mSize;
        this.ensureCapacity(this.mSize + v0);
        int v2 = 0;
        if(this.mSize != 0) {
            while(v2 < v0) {
                this.put(arg5.keyAt(v2), arg5.valueAt(v2));
                ++v2;
            }
        }
        else if(v0 > 0) {
            System.arraycopy(arg5.mHashes, 0, this.mHashes, 0, v0);
            System.arraycopy(arg5.mArray, 0, this.mArray, 0, v0 << 1);
            this.mSize = v0;
        }
    }

    public Object remove(Object arg1) {
        int v1 = this.indexOfKey(arg1);
        if(v1 >= 0) {
            return this.removeAt(v1);
        }

        return null;
    }

    public Object removeAt(int arg11) {
        int v1 = arg11 << 1;
        Object v0 = this.mArray[v1 + 1];
        int v2 = this.mSize;
        int v3 = 0;
        if(v2 <= 1) {
            SimpleArrayMap.freeArrays(this.mHashes, this.mArray, v2);
            this.mHashes = ContainerHelpers.EMPTY_INTS;
            this.mArray = ContainerHelpers.EMPTY_OBJECTS;
        }
        else {
            int v5 = v2 - 1;
            int v7 = 8;
            if(this.mHashes.length <= v7 || this.mSize >= this.mHashes.length / 3) {
                if(arg11 < v5) {
                    int v6_1 = arg11 + 1;
                    int v8_1 = v5 - arg11;
                    System.arraycopy(this.mHashes, v6_1, this.mHashes, arg11, v8_1);
                    System.arraycopy(this.mArray, v6_1 << 1, this.mArray, v1, v8_1 << 1);
                }

                v1 = v5 << 1;
                this.mArray[v1] = null;
                this.mArray[v1 + 1] = null;
            }
            else {
                if(v2 > v7) {
                    v7 = v2 + (v2 >> 1);
                }

                int[] v6 = this.mHashes;
                Object[] v8 = this.mArray;
                this.allocArrays(v7);
                if(v2 != this.mSize) {
                    throw new ConcurrentModificationException();
                }

                if(arg11 > 0) {
                    System.arraycopy(v6, 0, this.mHashes, 0, arg11);
                    System.arraycopy(v8, 0, this.mArray, 0, v1);
                }

                if(arg11 >= v5) {
                    goto label_70;
                }

                v3 = arg11 + 1;
                int v9 = v5 - arg11;
                System.arraycopy(v6, v3, this.mHashes, arg11, v9);
                System.arraycopy(v8, v3 << 1, this.mArray, v1, v9 << 1);
            }

        label_70:
            v3 = v5;
        }

        if(v2 != this.mSize) {
            throw new ConcurrentModificationException();
        }

        this.mSize = v3;
        return v0;
    }

    public Object setValueAt(int arg3, Object arg4) {
        arg3 = (arg3 << 1) + 1;
        Object v0 = this.mArray[arg3];
        this.mArray[arg3] = arg4;
        return v0;
    }

    public int size() {
        return this.mSize;
    }

    public String toString() {
        if(this.isEmpty()) {
            return "{}";
        }

        StringBuilder v0 = new StringBuilder(this.mSize * 28);
        v0.append('{');
        int v1;
        for(v1 = 0; v1 < this.mSize; ++v1) {
            if(v1 > 0) {
                v0.append(", ");
            }

            Object v2 = this.keyAt(v1);
            if((((SimpleArrayMap)v2)) != this) {
                v0.append(v2);
            }
            else {
                v0.append("(this Map)");
            }

            v0.append('=');
            v2 = this.valueAt(v1);
            if((((SimpleArrayMap)v2)) != this) {
                v0.append(v2);
            }
            else {
                v0.append("(this Map)");
            }
        }

        v0.append('}');
        return v0.toString();
    }

    public Object valueAt(int arg2) {
        return this.mArray[(arg2 << 1) + 1];
    }
}

