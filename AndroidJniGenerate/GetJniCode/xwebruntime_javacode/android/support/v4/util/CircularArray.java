package android.support.v4.util;

public final class CircularArray {
    private int mCapacityBitmask;
    private Object[] mElements;
    private int mHead;
    private int mTail;

    public CircularArray() {
        this(8);
    }

    public CircularArray(int arg3) {
        super();
        if(arg3 < 1) {
            throw new IllegalArgumentException("capacity must be >= 1");
        }

        if(arg3 > 0x40000000) {
            throw new IllegalArgumentException("capacity must be <= 2^30");
        }

        if(Integer.bitCount(arg3) != 1) {
            arg3 = Integer.highestOneBit(arg3 - 1) << 1;
        }

        this.mCapacityBitmask = arg3 - 1;
        this.mElements = new Object[arg3];
    }

    public void addFirst(Object arg3) {
        this.mHead = this.mHead - 1 & this.mCapacityBitmask;
        this.mElements[this.mHead] = arg3;
        if(this.mHead == this.mTail) {
            this.doubleCapacity();
        }
    }

    public void addLast(Object arg3) {
        this.mElements[this.mTail] = arg3;
        this.mTail = this.mTail + 1 & this.mCapacityBitmask;
        if(this.mTail == this.mHead) {
            this.doubleCapacity();
        }
    }

    public void clear() {
        this.removeFromStart(this.size());
    }

    private void doubleCapacity() {
        int v0 = this.mElements.length;
        int v1 = v0 - this.mHead;
        int v2 = v0 << 1;
        if(v2 < 0) {
            throw new RuntimeException("Max array capacity exceeded");
        }

        Object[] v3 = new Object[v2];
        System.arraycopy(this.mElements, this.mHead, v3, 0, v1);
        System.arraycopy(this.mElements, 0, v3, v1, this.mHead);
        this.mElements = v3;
        this.mHead = 0;
        this.mTail = v0;
        this.mCapacityBitmask = v2 - 1;
    }

    public Object get(int arg3) {
        if(arg3 >= 0) {
            if(arg3 >= this.size()) {
            }
            else {
                return this.mElements[this.mCapacityBitmask & this.mHead + arg3];
            }
        }

        throw new ArrayIndexOutOfBoundsException();
    }

    public Object getFirst() {
        if(this.mHead == this.mTail) {
            throw new ArrayIndexOutOfBoundsException();
        }

        return this.mElements[this.mHead];
    }

    public Object getLast() {
        if(this.mHead == this.mTail) {
            throw new ArrayIndexOutOfBoundsException();
        }

        return this.mElements[this.mTail - 1 & this.mCapacityBitmask];
    }

    public boolean isEmpty() {
        boolean v0 = this.mHead == this.mTail ? true : false;
        return v0;
    }

    public Object popFirst() {
        if(this.mHead == this.mTail) {
            throw new ArrayIndexOutOfBoundsException();
        }

        Object v0 = this.mElements[this.mHead];
        this.mElements[this.mHead] = null;
        this.mHead = this.mHead + 1 & this.mCapacityBitmask;
        return v0;
    }

    public Object popLast() {
        if(this.mHead == this.mTail) {
            throw new ArrayIndexOutOfBoundsException();
        }

        int v0 = this.mTail - 1 & this.mCapacityBitmask;
        Object v1 = this.mElements[v0];
        this.mElements[v0] = null;
        this.mTail = v0;
        return v1;
    }

    public void removeFromEnd(int arg5) {
        Object v3;
        if(arg5 <= 0) {
            return;
        }

        if(arg5 > this.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }

        int v0 = 0;
        if(arg5 < this.mTail) {
            v0 = this.mTail - arg5;
        }

        int v1;
        for(v1 = v0; true; ++v1) {
            v3 = null;
            if(v1 >= this.mTail) {
                break;
            }

            this.mElements[v1] = v3;
        }

        v1 = this.mTail - v0;
        arg5 -= v1;
        this.mTail -= v1;
        if(arg5 > 0) {
            this.mTail = this.mElements.length;
            v0 = this.mTail - arg5;
            for(arg5 = v0; arg5 < this.mTail; ++arg5) {
                this.mElements[arg5] = v3;
            }

            this.mTail = v0;
        }
    }

    public void removeFromStart(int arg5) {
        Object v2;
        if(arg5 <= 0) {
            return;
        }

        if(arg5 > this.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }

        int v0 = this.mElements.length;
        if(arg5 < v0 - this.mHead) {
            v0 = this.mHead + arg5;
        }

        int v1;
        for(v1 = this.mHead; true; ++v1) {
            v2 = null;
            if(v1 >= v0) {
                break;
            }

            this.mElements[v1] = v2;
        }

        v0 -= this.mHead;
        arg5 -= v0;
        this.mHead = this.mCapacityBitmask & this.mHead + v0;
        if(arg5 > 0) {
            for(v0 = 0; v0 < arg5; ++v0) {
                this.mElements[v0] = v2;
            }

            this.mHead = arg5;
        }
    }

    public int size() {
        return this.mTail - this.mHead & this.mCapacityBitmask;
    }
}

