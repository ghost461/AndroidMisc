package org.chromium.base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe public class ObserverList implements Iterable {
    class org.chromium.base.ObserverList$1 {
    }

    class ObserverListIterator implements RewindableIterator {
        private int mIndex;
        private boolean mIsExhausted;
        private int mListEndMarker;

        ObserverListIterator(ObserverList arg1, org.chromium.base.ObserverList$1 arg2) {
            this(arg1);
        }

        private ObserverListIterator(ObserverList arg1) {
            ObserverList.this = arg1;
            super();
            arg1.incrementIterationDepth();
            this.mListEndMarker = arg1.capacity();
        }

        private void compactListIfNeeded() {
            if(!this.mIsExhausted) {
                this.mIsExhausted = true;
                ObserverList.this.decrementIterationDepthAndCompactIfNeeded();
            }
        }

        public boolean hasNext() {
            int v0;
            for(v0 = this.mIndex; v0 < this.mListEndMarker; ++v0) {
                if(ObserverList.this.getObserverAt(v0) != null) {
                    break;
                }
            }

            if(v0 < this.mListEndMarker) {
                return 1;
            }

            this.compactListIfNeeded();
            return 0;
        }

        public Object next() {
            while(this.mIndex < this.mListEndMarker) {
                if(ObserverList.this.getObserverAt(this.mIndex) != null) {
                    break;
                }

                ++this.mIndex;
            }

            if(this.mIndex < this.mListEndMarker) {
                ObserverList v0 = ObserverList.this;
                int v1 = this.mIndex;
                this.mIndex = v1 + 1;
                return v0.getObserverAt(v1);
            }

            this.compactListIfNeeded();
            throw new NoSuchElementException();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public void rewind() {
            this.compactListIfNeeded();
            ObserverList.this.incrementIterationDepth();
            this.mListEndMarker = ObserverList.this.capacity();
            this.mIsExhausted = false;
            this.mIndex = 0;
        }
    }

    public interface RewindableIterator extends Iterator {
        void rewind();
    }

    private int mCount;
    private int mIterationDepth;
    private boolean mNeedsCompact;
    public final List mObservers;

    static {
    }

    public ObserverList() {
        super();
        this.mObservers = new ArrayList();
    }

    static void access$100(ObserverList arg0) {
        arg0.incrementIterationDepth();
    }

    static int access$200(ObserverList arg0) {
        return arg0.capacity();
    }

    static Object access$300(ObserverList arg0, int arg1) {
        return arg0.getObserverAt(arg1);
    }

    static void access$400(ObserverList arg0) {
        arg0.decrementIterationDepthAndCompactIfNeeded();
    }

    public boolean addObserver(Object arg2) {
        if(arg2 != null) {
            if(this.mObservers.contains(arg2)) {
            }
            else {
                this.mObservers.add(arg2);
                ++this.mCount;
                return 1;
            }
        }

        return 0;
    }

    private int capacity() {
        return this.mObservers.size();
    }

    public void clear() {
        int v0 = 0;
        this.mCount = 0;
        if(this.mIterationDepth == 0) {
            this.mObservers.clear();
            return;
        }

        int v1 = this.mObservers.size();
        boolean v2 = this.mNeedsCompact;
        int v3 = v1 != 0 ? 1 : 0;
        this.mNeedsCompact = (((int)v2)) | v3;
        while(v0 < v1) {
            this.mObservers.set(v0, null);
            ++v0;
        }
    }

    private void compact() {
        int v0;
        for(v0 = this.mObservers.size() - 1; v0 >= 0; --v0) {
            if(this.mObservers.get(v0) == null) {
                this.mObservers.remove(v0);
            }
        }
    }

    private void decrementIterationDepthAndCompactIfNeeded() {
        --this.mIterationDepth;
        if(this.mIterationDepth > 0) {
            return;
        }

        if(!this.mNeedsCompact) {
            return;
        }

        this.mNeedsCompact = false;
        this.compact();
    }

    private Object getObserverAt(int arg2) {
        return this.mObservers.get(arg2);
    }

    public boolean hasObserver(Object arg2) {
        return this.mObservers.contains(arg2);
    }

    private void incrementIterationDepth() {
        ++this.mIterationDepth;
    }

    public boolean isEmpty() {
        boolean v0 = this.mCount == 0 ? true : false;
        return v0;
    }

    public Iterator iterator() {
        return new ObserverListIterator(this, null);
    }

    public boolean removeObserver(Object arg4) {
        if(arg4 == null) {
            return 0;
        }

        int v4 = this.mObservers.indexOf(arg4);
        if(v4 == -1) {
            return 0;
        }

        if(this.mIterationDepth == 0) {
            this.mObservers.remove(v4);
        }
        else {
            this.mNeedsCompact = true;
            this.mObservers.set(v4, null);
        }

        --this.mCount;
        return 1;
    }

    public RewindableIterator rewindableIterator() {
        return new ObserverListIterator(this, null);
    }

    public int size() {
        return this.mCount;
    }
}

