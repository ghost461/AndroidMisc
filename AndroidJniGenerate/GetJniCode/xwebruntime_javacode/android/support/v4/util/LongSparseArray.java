package android.support.v4.util;

public class LongSparseArray implements Cloneable {
    private static final Object DELETED;
    private boolean mGarbage;
    private long[] mKeys;
    private int mSize;
    private Object[] mValues;

    static {
        LongSparseArray.DELETED = new Object();
    }

    public LongSparseArray() {
        this(10);
    }

    public LongSparseArray(int arg3) {
        super();
        this.mGarbage = false;
        if(arg3 == 0) {
            this.mKeys = ContainerHelpers.EMPTY_LONGS;
            this.mValues = ContainerHelpers.EMPTY_OBJECTS;
        }
        else {
            arg3 = ContainerHelpers.idealLongArraySize(arg3);
            this.mKeys = new long[arg3];
            this.mValues = new Object[arg3];
        }

        this.mSize = 0;
    }

    public void append(long arg7, Object arg9) {
        if(this.mSize != 0 && arg7 <= this.mKeys[this.mSize - 1]) {
            this.put(arg7, arg9);
            return;
        }

        if((this.mGarbage) && this.mSize >= this.mKeys.length) {
            this.gc();
        }

        int v0 = this.mSize;
        if(v0 >= this.mKeys.length) {
            int v1 = ContainerHelpers.idealLongArraySize(v0 + 1);
            long[] v2 = new long[v1];
            Object[] v1_1 = new Object[v1];
            System.arraycopy(this.mKeys, 0, v2, 0, this.mKeys.length);
            System.arraycopy(this.mValues, 0, v1_1, 0, this.mValues.length);
            this.mKeys = v2;
            this.mValues = v1_1;
        }

        this.mKeys[v0] = arg7;
        this.mValues[v0] = arg9;
        this.mSize = v0 + 1;
    }

    public void clear() {
        int v0 = this.mSize;
        Object[] v1 = this.mValues;
        int v3;
        for(v3 = 0; v3 < v0; ++v3) {
            v1[v3] = null;
        }

        this.mSize = 0;
        this.mGarbage = false;
    }

    public LongSparseArray clone() {
        Object v1;
        long[] v0 = null;
        try {
            v1 = super.clone();
        }
        catch(CloneNotSupportedException ) {
            goto label_9;
        }

        try {
            v0 = this.mKeys;
            goto label_3;
        }
        catch(CloneNotSupportedException ) {
        }
        catch(CloneNotSupportedException ) {
        label_9:
            long[] v1_1 = v0;
            goto label_10;
            try {
            label_3:
                ((LongSparseArray)v1).mKeys = v0.clone();
                ((LongSparseArray)v1).mValues = this.mValues.clone();
                goto label_10;
            }
            catch(CloneNotSupportedException ) {
            label_10:
                return ((LongSparseArray)v1);
            }
        }
    }

    public Object clone() throws CloneNotSupportedException {
        return this.clone();
    }

    public void delete(long arg3) {
        int v3 = ContainerHelpers.binarySearch(this.mKeys, this.mSize, arg3);
        if(v3 >= 0 && this.mValues[v3] != LongSparseArray.DELETED) {
            this.mValues[v3] = LongSparseArray.DELETED;
            this.mGarbage = true;
        }
    }

    private void gc() {
        int v0 = this.mSize;
        long[] v1 = this.mKeys;
        Object[] v2 = this.mValues;
        int v4 = 0;
        int v5 = 0;
        while(v4 < v0) {
            Object v6 = v2[v4];
            if(v6 != LongSparseArray.DELETED) {
                if(v4 != v5) {
                    v1[v5] = v1[v4];
                    v2[v5] = v6;
                    v2[v4] = null;
                }

                ++v5;
            }

            ++v4;
        }

        this.mGarbage = false;
        this.mSize = v5;
    }

    public Object get(long arg2) {
        return this.get(arg2, null);
    }

    public Object get(long arg3, Object arg5) {
        int v3 = ContainerHelpers.binarySearch(this.mKeys, this.mSize, arg3);
        if(v3 >= 0) {
            if(this.mValues[v3] == LongSparseArray.DELETED) {
            }
            else {
                return this.mValues[v3];
            }
        }

        return arg5;
    }

    public int indexOfKey(long arg3) {
        if(this.mGarbage) {
            this.gc();
        }

        return ContainerHelpers.binarySearch(this.mKeys, this.mSize, arg3);
    }

    public int indexOfValue(Object arg3) {
        if(this.mGarbage) {
            this.gc();
        }

        int v0;
        for(v0 = 0; v0 < this.mSize; ++v0) {
            if(this.mValues[v0] == arg3) {
                return v0;
            }
        }

        return -1;
    }

    public long keyAt(int arg4) {
        if(this.mGarbage) {
            this.gc();
        }

        return this.mKeys[arg4];
    }

    public void put(long arg7, Object arg9) {
        int v0 = ContainerHelpers.binarySearch(this.mKeys, this.mSize, arg7);
        if(v0 >= 0) {
            this.mValues[v0] = arg9;
        }
        else {
            v0 ^= -1;
            if(v0 < this.mSize && this.mValues[v0] == LongSparseArray.DELETED) {
                this.mKeys[v0] = arg7;
                this.mValues[v0] = arg9;
                return;
            }

            if((this.mGarbage) && this.mSize >= this.mKeys.length) {
                this.gc();
                v0 = ContainerHelpers.binarySearch(this.mKeys, this.mSize, arg7) ^ -1;
            }

            if(this.mSize >= this.mKeys.length) {
                int v1 = ContainerHelpers.idealLongArraySize(this.mSize + 1);
                long[] v2 = new long[v1];
                Object[] v1_1 = new Object[v1];
                System.arraycopy(this.mKeys, 0, v2, 0, this.mKeys.length);
                System.arraycopy(this.mValues, 0, v1_1, 0, this.mValues.length);
                this.mKeys = v2;
                this.mValues = v1_1;
            }

            if(this.mSize - v0 != 0) {
                int v3 = v0 + 1;
                System.arraycopy(this.mKeys, v0, this.mKeys, v3, this.mSize - v0);
                System.arraycopy(this.mValues, v0, this.mValues, v3, this.mSize - v0);
            }

            this.mKeys[v0] = arg7;
            this.mValues[v0] = arg9;
            ++this.mSize;
        }
    }

    public void remove(long arg1) {
        this.delete(arg1);
    }

    public void removeAt(int arg3) {
        if(this.mValues[arg3] != LongSparseArray.DELETED) {
            this.mValues[arg3] = LongSparseArray.DELETED;
            this.mGarbage = true;
        }
    }

    public void setValueAt(int arg2, Object arg3) {
        if(this.mGarbage) {
            this.gc();
        }

        this.mValues[arg2] = arg3;
    }

    public int size() {
        if(this.mGarbage) {
            this.gc();
        }

        return this.mSize;
    }

    public String toString() {
        if(this.size() <= 0) {
            return "{}";
        }

        StringBuilder v0 = new StringBuilder(this.mSize * 28);
        v0.append('{');
        int v1;
        for(v1 = 0; v1 < this.mSize; ++v1) {
            if(v1 > 0) {
                v0.append(", ");
            }

            v0.append(this.keyAt(v1));
            v0.append('=');
            Object v2 = this.valueAt(v1);
            if((((LongSparseArray)v2)) != this) {
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
        if(this.mGarbage) {
            this.gc();
        }

        return this.mValues[arg2];
    }
}

