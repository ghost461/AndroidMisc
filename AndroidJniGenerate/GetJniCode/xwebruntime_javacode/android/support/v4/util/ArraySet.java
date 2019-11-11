package android.support.v4.util;

import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public final class ArraySet implements Collection, Set {
    private static final int BASE_SIZE = 4;
    private static final int CACHE_SIZE = 10;
    private static final boolean DEBUG = false;
    private static final int[] INT = null;
    private static final Object[] OBJECT = null;
    private static final String TAG = "ArraySet";
    Object[] mArray;
    MapCollections mCollections;
    int[] mHashes;
    final boolean mIdentityHashCode;
    int mSize;
    static Object[] sBaseCache;
    static int sBaseCacheSize;
    static Object[] sTwiceBaseCache;
    static int sTwiceBaseCacheSize;

    static {
        ArraySet.INT = new int[0];
        ArraySet.OBJECT = new Object[0];
    }

    public ArraySet() {
        this(0, false);
    }

    public ArraySet(int arg1, boolean arg2) {
        super();
        this.mIdentityHashCode = arg2;
        if(arg1 == 0) {
            this.mHashes = ArraySet.INT;
            this.mArray = ArraySet.OBJECT;
        }
        else {
            this.allocArrays(arg1);
        }

        this.mSize = 0;
    }

    public ArraySet(int arg2) {
        this(arg2, false);
    }

    public ArraySet(ArraySet arg1) {
        this();
        if(arg1 != null) {
            this.addAll(arg1);
        }
    }

    public ArraySet(Collection arg1) {
        this();
        if(arg1 != null) {
            this.addAll(arg1);
        }
    }

    public boolean add(Object arg10) {
        int v4;
        int v2;
        int v1;
        if(arg10 == null) {
            v1 = this.indexOfNull();
            v2 = 0;
        }
        else {
            v1 = this.mIdentityHashCode ? System.identityHashCode(arg10) : arg10.hashCode();
            v2 = v1;
            v1 = this.indexOf(arg10, v1);
        }

        if(v1 >= 0) {
            return 0;
        }

        v1 ^= -1;
        if(this.mSize >= this.mHashes.length) {
            v4 = 4;
            if(this.mSize >= 8) {
                v4 = (this.mSize >> 1) + this.mSize;
            }
            else if(this.mSize >= v4) {
                v4 = 8;
            }

            int[] v3 = this.mHashes;
            Object[] v6 = this.mArray;
            this.allocArrays(v4);
            if(this.mHashes.length > 0) {
                System.arraycopy(v3, 0, this.mHashes, 0, v3.length);
                System.arraycopy(v6, 0, this.mArray, 0, v6.length);
            }

            ArraySet.freeArrays(v3, v6, this.mSize);
        }

        if(v1 < this.mSize) {
            v4 = v1 + 1;
            System.arraycopy(this.mHashes, v1, this.mHashes, v4, this.mSize - v1);
            System.arraycopy(this.mArray, v1, this.mArray, v4, this.mSize - v1);
        }

        this.mHashes[v1] = v2;
        this.mArray[v1] = arg10;
        ++this.mSize;
        return 1;
    }

    public void addAll(ArraySet arg5) {
        int v0 = arg5.mSize;
        this.ensureCapacity(this.mSize + v0);
        int v2 = 0;
        if(this.mSize != 0) {
            while(v2 < v0) {
                this.add(arg5.valueAt(v2));
                ++v2;
            }
        }
        else if(v0 > 0) {
            System.arraycopy(arg5.mHashes, 0, this.mHashes, 0, v0);
            System.arraycopy(arg5.mArray, 0, this.mArray, 0, v0);
            this.mSize = v0;
        }
    }

    public boolean addAll(Collection arg3) {
        this.ensureCapacity(this.mSize + arg3.size());
        Iterator v3 = arg3.iterator();
        int v0;
        for(v0 = 0; v3.hasNext(); v0 |= this.add(v3.next())) {
        }

        return ((boolean)v0);
    }

    private void allocArrays(int arg6) {
        Object[] v6_1;
        Class v3;
        Object v0 = null;
        if(arg6 == 8) {
            v3 = ArraySet.class;
            __monitor_enter(v3);
            try {
                if(ArraySet.sTwiceBaseCache != null) {
                    v6_1 = ArraySet.sTwiceBaseCache;
                    this.mArray = v6_1;
                    ArraySet.sTwiceBaseCache = v6_1[0];
                    this.mHashes = v6_1[1];
                    v6_1[1] = v0;
                    v6_1[0] = v0;
                    --ArraySet.sTwiceBaseCacheSize;
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
            v3 = ArraySet.class;
            __monitor_enter(v3);
            try {
                if(ArraySet.sBaseCache != null) {
                    v6_1 = ArraySet.sBaseCache;
                    this.mArray = v6_1;
                    ArraySet.sBaseCache = v6_1[0];
                    this.mHashes = v6_1[1];
                    v6_1[1] = v0;
                    v6_1[0] = v0;
                    --ArraySet.sBaseCacheSize;
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
        this.mArray = new Object[arg6];
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public void append(Object arg5) {
        int v1;
        int v0 = this.mSize;
        if(arg5 == null) {
            v1 = 0;
        }
        else if(this.mIdentityHashCode) {
            v1 = System.identityHashCode(arg5);
        }
        else {
            v1 = arg5.hashCode();
        }

        if(v0 >= this.mHashes.length) {
            throw new IllegalStateException("Array is full");
        }

        if(v0 > 0 && this.mHashes[v0 - 1] > v1) {
            this.add(arg5);
            return;
        }

        this.mSize = v0 + 1;
        this.mHashes[v0] = v1;
        this.mArray[v0] = arg5;
    }

    public void clear() {
        if(this.mSize != 0) {
            ArraySet.freeArrays(this.mHashes, this.mArray, this.mSize);
            this.mHashes = ArraySet.INT;
            this.mArray = ArraySet.OBJECT;
            this.mSize = 0;
        }
    }

    public boolean contains(Object arg1) {
        boolean v1 = this.indexOf(arg1) >= 0 ? true : false;
        return v1;
    }

    public boolean containsAll(Collection arg2) {
        Iterator v2 = arg2.iterator();
        do {
            if(!v2.hasNext()) {
                return 1;
            }
        }
        while(this.contains(v2.next()));

        return 0;
    }

    public void ensureCapacity(int arg5) {
        if(this.mHashes.length < arg5) {
            int[] v0 = this.mHashes;
            Object[] v1 = this.mArray;
            this.allocArrays(arg5);
            if(this.mSize > 0) {
                System.arraycopy(v0, 0, this.mHashes, 0, this.mSize);
                System.arraycopy(v1, 0, this.mArray, 0, this.mSize);
            }

            ArraySet.freeArrays(v0, v1, this.mSize);
        }
    }

    public boolean equals(Object arg5) {
        if(this == (((ArraySet)arg5))) {
            return 1;
        }

        if(!(arg5 instanceof Set)) {
            return 0;
        }

        if(this.size() != ((Set)arg5).size()) {
            return 0;
        }

        int v1 = 0;
        try {
            while(true) {
            label_11:
                if(v1 >= this.mSize) {
                    return 1;
                }

                if(((Set)arg5).contains(this.valueAt(v1))) {
                    goto label_17;
                }

                return 0;
            }
        }
        catch(ClassCastException ) {
            return 0;
        }
        catch(NullPointerException ) {
            return 0;
        }

        return 0;
    label_17:
        ++v1;
        goto label_11;
        return 1;
    }

    private static void freeArrays(int[] arg7, Object[] arg8, int arg9) {
        Class v0;
        Object v1 = null;
        int v2 = 2;
        int v4 = 10;
        if(arg7.length == 8) {
            v0 = ArraySet.class;
            __monitor_enter(v0);
            try {
                if(ArraySet.sTwiceBaseCacheSize < v4) {
                    arg8[0] = ArraySet.sTwiceBaseCache;
                    arg8[1] = arg7;
                    --arg9;
                    while(arg9 >= v2) {
                        arg8[arg9] = v1;
                        --arg9;
                    }

                    ArraySet.sTwiceBaseCache = arg8;
                    ++ArraySet.sTwiceBaseCacheSize;
                }

                __monitor_exit(v0);
                return;
            label_27:
                __monitor_exit(v0);
            }
            catch(Throwable v7) {
                goto label_27;
            }

            throw v7;
        }
        else {
            if(arg7.length != 4) {
                return;
            }

            v0 = ArraySet.class;
            __monitor_enter(v0);
            try {
                if(ArraySet.sBaseCacheSize < v4) {
                    arg8[0] = ArraySet.sBaseCache;
                    arg8[1] = arg7;
                    --arg9;
                    while(arg9 >= v2) {
                        arg8[arg9] = v1;
                        --arg9;
                    }

                    ArraySet.sBaseCache = arg8;
                    ++ArraySet.sBaseCacheSize;
                }

                __monitor_exit(v0);
                return;
            label_51:
                __monitor_exit(v0);
            }
            catch(Throwable v7) {
                goto label_51;
            }

            throw v7;
        }
    }

    private MapCollections getCollection() {
        if(this.mCollections == null) {
            this.mCollections = new MapCollections() {
                protected void colClear() {
                    ArraySet.this.clear();
                }

                protected Object colGetEntry(int arg1, int arg2) {
                    return ArraySet.this.mArray[arg1];
                }

                protected Map colGetMap() {
                    throw new UnsupportedOperationException("not a map");
                }

                protected int colGetSize() {
                    return ArraySet.this.mSize;
                }

                protected int colIndexOfKey(Object arg2) {
                    return ArraySet.this.indexOf(arg2);
                }

                protected int colIndexOfValue(Object arg2) {
                    return ArraySet.this.indexOf(arg2);
                }

                protected void colPut(Object arg1, Object arg2) {
                    ArraySet.this.add(arg1);
                }

                protected void colRemoveAt(int arg2) {
                    ArraySet.this.removeAt(arg2);
                }

                protected Object colSetValue(int arg1, Object arg2) {
                    throw new UnsupportedOperationException("not a map");
                }
            };
        }

        return this.mCollections;
    }

    public int hashCode() {
        int[] v0 = this.mHashes;
        int v1 = this.mSize;
        int v2 = 0;
        int v3 = 0;
        while(v2 < v1) {
            v3 += v0[v2];
            ++v2;
        }

        return v3;
    }

    public int indexOf(Object arg2) {
        int v2;
        if(arg2 == null) {
            v2 = this.indexOfNull();
        }
        else {
            int v0 = this.mIdentityHashCode ? System.identityHashCode(arg2) : arg2.hashCode();
            v2 = this.indexOf(arg2, v0);
        }

        return v2;
    }

    private int indexOf(Object arg6, int arg7) {
        int v0 = this.mSize;
        int v1 = -1;
        if(v0 == 0) {
            return v1;
        }

        int v2 = ContainerHelpers.binarySearch(this.mHashes, v0, arg7);
        if(v2 < 0) {
            return v2;
        }

        if(arg6.equals(this.mArray[v2])) {
            return v2;
        }

        int v3 = v2 + 1;
        while(v3 < v0) {
            if(this.mHashes[v3] != arg7) {
                break;
            }

            if(arg6.equals(this.mArray[v3])) {
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
            if(this.mHashes[v2] != arg7) {
                break;
            }

            if(arg6.equals(this.mArray[v2])) {
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

    private int indexOfNull() {
        int v0 = this.mSize;
        int v1 = -1;
        if(v0 == 0) {
            return v1;
        }

        int v2 = ContainerHelpers.binarySearch(this.mHashes, v0, 0);
        if(v2 < 0) {
            return v2;
        }

        if(this.mArray[v2] == null) {
            return v2;
        }

        int v3 = v2 + 1;
        while(v3 < v0) {
            if(this.mHashes[v3] != 0) {
                break;
            }

            if(this.mArray[v3] == null) {
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

            if(this.mArray[v2] == null) {
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

    public boolean isEmpty() {
        boolean v0 = this.mSize <= 0 ? true : false;
        return v0;
    }

    public Iterator iterator() {
        return this.getCollection().getKeySet().iterator();
    }

    public boolean remove(Object arg1) {
        int v1 = this.indexOf(arg1);
        if(v1 >= 0) {
            this.removeAt(v1);
            return 1;
        }

        return 0;
    }

    public boolean removeAll(ArraySet arg6) {
        int v0 = arg6.mSize;
        int v1 = this.mSize;
        boolean v2 = false;
        int v3;
        for(v3 = 0; v3 < v0; ++v3) {
            this.remove(arg6.valueAt(v3));
        }

        if(v1 != this.mSize) {
            v2 = true;
        }

        return v2;
    }

    public boolean removeAll(Collection arg3) {
        Iterator v3 = arg3.iterator();
        int v0;
        for(v0 = 0; v3.hasNext(); v0 |= this.remove(v3.next())) {
        }

        return ((boolean)v0);
    }

    public Object removeAt(int arg7) {
        int v2;
        Object v0 = this.mArray[arg7];
        if(this.mSize <= 1) {
            ArraySet.freeArrays(this.mHashes, this.mArray, this.mSize);
            this.mHashes = ArraySet.INT;
            this.mArray = ArraySet.OBJECT;
            this.mSize = 0;
        }
        else {
            int v4 = 8;
            if(this.mHashes.length > v4 && this.mSize < this.mHashes.length / 3) {
                if(this.mSize > v4) {
                    v4 = (this.mSize >> 1) + this.mSize;
                }

                int[] v1 = this.mHashes;
                Object[] v5 = this.mArray;
                this.allocArrays(v4);
                --this.mSize;
                if(arg7 > 0) {
                    System.arraycopy(v1, 0, this.mHashes, 0, arg7);
                    System.arraycopy(v5, 0, this.mArray, 0, arg7);
                }

                if(arg7 >= this.mSize) {
                    return v0;
                }

                v2 = arg7 + 1;
                System.arraycopy(v1, v2, this.mHashes, arg7, this.mSize - arg7);
                System.arraycopy(v5, v2, this.mArray, arg7, this.mSize - arg7);
                return v0;
            }

            --this.mSize;
            if(arg7 < this.mSize) {
                v2 = arg7 + 1;
                System.arraycopy(this.mHashes, v2, this.mHashes, arg7, this.mSize - arg7);
                System.arraycopy(this.mArray, v2, this.mArray, arg7, this.mSize - arg7);
            }

            this.mArray[this.mSize] = null;
        }

        return v0;
    }

    public boolean retainAll(Collection arg5) {
        int v0 = this.mSize - 1;
        boolean v2 = false;
        while(v0 >= 0) {
            if(!arg5.contains(this.mArray[v0])) {
                this.removeAt(v0);
                v2 = true;
            }

            --v0;
        }

        return v2;
    }

    public int size() {
        return this.mSize;
    }

    public Object[] toArray() {
        Object[] v0 = new Object[this.mSize];
        System.arraycopy(this.mArray, 0, v0, 0, this.mSize);
        return v0;
    }

    public Object[] toArray(Object[] arg4) {
        Object v4;
        if(arg4.length < this.mSize) {
            v4 = Array.newInstance(arg4.getClass().getComponentType(), this.mSize);
        }

        System.arraycopy(this.mArray, 0, v4, 0, this.mSize);
        if(((Object[])v4).length > this.mSize) {
            ((Object[])v4)[this.mSize] = null;
        }

        return ((Object[])v4);
    }

    public String toString() {
        if(this.isEmpty()) {
            return "{}";
        }

        StringBuilder v0 = new StringBuilder(this.mSize * 14);
        v0.append('{');
        int v1;
        for(v1 = 0; v1 < this.mSize; ++v1) {
            if(v1 > 0) {
                v0.append(", ");
            }

            Object v2 = this.valueAt(v1);
            if((((ArraySet)v2)) != this) {
                v0.append(v2);
            }
            else {
                v0.append("(this Set)");
            }
        }

        v0.append('}');
        return v0.toString();
    }

    public Object valueAt(int arg2) {
        return this.mArray[arg2];
    }
}

