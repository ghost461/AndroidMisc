package android.arch.core.internal;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import java.util.Iterator;
import java.util.Map$Entry;
import java.util.WeakHashMap;

@RestrictTo(value={Scope.LIBRARY_GROUP}) public class SafeIterableMap implements Iterable {
    class android.arch.core.internal.SafeIterableMap$1 {
    }

    class AscendingIterator extends ListIterator {
        AscendingIterator(Entry arg1, Entry arg2) {
            super(arg1, arg2);
        }

        Entry backward(Entry arg1) {
            return arg1.mPrevious;
        }

        Entry forward(Entry arg1) {
            return arg1.mNext;
        }
    }

    class DescendingIterator extends ListIterator {
        DescendingIterator(Entry arg1, Entry arg2) {
            super(arg1, arg2);
        }

        Entry backward(Entry arg1) {
            return arg1.mNext;
        }

        Entry forward(Entry arg1) {
            return arg1.mPrevious;
        }
    }

    class Entry implements Map$Entry {
        @NonNull final Object mKey;
        Entry mNext;
        Entry mPrevious;
        @NonNull final Object mValue;

        Entry(@NonNull Object arg1, @NonNull Object arg2) {
            super();
            this.mKey = arg1;
            this.mValue = arg2;
        }

        public boolean equals(Object arg5) {
            boolean v0 = true;
            if((((Entry)arg5)) == this) {
                return 1;
            }

            if(!(arg5 instanceof Entry)) {
                return 0;
            }

            if(!this.mKey.equals(((Entry)arg5).mKey) || !this.mValue.equals(((Entry)arg5).mValue)) {
                v0 = false;
            }
            else {
            }

            return v0;
        }

        @NonNull public Object getKey() {
            return this.mKey;
        }

        @NonNull public Object getValue() {
            return this.mValue;
        }

        public Object setValue(Object arg2) {
            throw new UnsupportedOperationException("An entry modification is not supported");
        }

        public String toString() {
            return this.mKey + "=" + this.mValue;
        }
    }

    class IteratorWithAdditions implements SupportRemove, Iterator {
        private boolean mBeforeStart;
        private Entry mCurrent;

        IteratorWithAdditions(SafeIterableMap arg1, android.arch.core.internal.SafeIterableMap$1 arg2) {
            this(arg1);
        }

        private IteratorWithAdditions(SafeIterableMap arg1) {
            SafeIterableMap.this = arg1;
            super();
            this.mBeforeStart = true;
        }

        public boolean hasNext() {
            boolean v1 = false;
            if(this.mBeforeStart) {
                if(SafeIterableMap.this.mStart != null) {
                    v1 = true;
                }

                return v1;
            }

            if(this.mCurrent != null && this.mCurrent.mNext != null) {
                v1 = true;
            }

            return v1;
        }

        public Object next() {
            return this.next();
        }

        public Map$Entry next() {
            if(this.mBeforeStart) {
                this.mBeforeStart = false;
                this.mCurrent = SafeIterableMap.this.mStart;
            }
            else {
                Entry v0 = this.mCurrent != null ? this.mCurrent.mNext : null;
                this.mCurrent = v0;
            }

            return this.mCurrent;
        }

        public void supportRemove(@NonNull Entry arg2) {
            if(arg2 == this.mCurrent) {
                this.mCurrent = this.mCurrent.mPrevious;
                boolean v2 = this.mCurrent == null ? true : false;
                this.mBeforeStart = v2;
            }
        }
    }

    abstract class ListIterator implements SupportRemove, Iterator {
        Entry mExpectedEnd;
        Entry mNext;

        ListIterator(Entry arg1, Entry arg2) {
            super();
            this.mExpectedEnd = arg2;
            this.mNext = arg1;
        }

        abstract Entry backward(Entry arg1);

        abstract Entry forward(Entry arg1);

        public boolean hasNext() {
            boolean v0 = this.mNext != null ? true : false;
            return v0;
        }

        public Object next() {
            return this.next();
        }

        public Map$Entry next() {
            Entry v0 = this.mNext;
            this.mNext = this.nextNode();
            return ((Map$Entry)v0);
        }

        private Entry nextNode() {
            if(this.mNext != this.mExpectedEnd) {
                if(this.mExpectedEnd == null) {
                }
                else {
                    return this.forward(this.mNext);
                }
            }

            return null;
        }

        public void supportRemove(@NonNull Entry arg2) {
            if(this.mExpectedEnd == arg2 && arg2 == this.mNext) {
                this.mNext = null;
                this.mExpectedEnd = null;
            }

            if(this.mExpectedEnd == arg2) {
                this.mExpectedEnd = this.backward(this.mExpectedEnd);
            }

            if(this.mNext == arg2) {
                this.mNext = this.nextNode();
            }
        }
    }

    interface SupportRemove {
        void supportRemove(@NonNull Entry arg1);
    }

    private Entry mEnd;
    private WeakHashMap mIterators;
    private int mSize;
    private Entry mStart;

    public SafeIterableMap() {
        super();
        this.mIterators = new WeakHashMap();
        this.mSize = 0;
    }

    static Entry access$100(SafeIterableMap arg0) {
        return arg0.mStart;
    }

    public Iterator descendingIterator() {
        DescendingIterator v0 = new DescendingIterator(this.mEnd, this.mStart);
        this.mIterators.put(v0, Boolean.valueOf(false));
        return ((Iterator)v0);
    }

    public Map$Entry eldest() {
        return this.mStart;
    }

    public boolean equals(Object arg6) {
        boolean v0 = true;
        if((((SafeIterableMap)arg6)) == this) {
            return 1;
        }

        if(!(arg6 instanceof SafeIterableMap)) {
            return 0;
        }

        if(this.size() != ((SafeIterableMap)arg6).size()) {
            return 0;
        }

        Iterator v1 = this.iterator();
        Iterator v6 = ((SafeIterableMap)arg6).iterator();
        do {
            if((v1.hasNext()) && (v6.hasNext())) {
                Object v3 = v1.next();
                Object v4 = v6.next();
                if(v3 != null || v4 == null) {
                    if(v3 == null) {
                        continue;
                    }

                    if(((Map$Entry)v3).equals(v4)) {
                        continue;
                    }
                }

                return 0;
            }

            goto label_25;
        }
        while(true);

        return 0;
    label_25:
        if((v1.hasNext()) || (v6.hasNext())) {
            v0 = false;
        }
        else {
        }

        return v0;
    }

    protected Entry get(Object arg3) {
        Entry v0 = this.mStart;
        while(v0 != null) {
            if(v0.mKey.equals(arg3)) {
            }
            else {
                v0 = v0.mNext;
                continue;
            }

            return v0;
        }

        return v0;
    }

    @NonNull public Iterator iterator() {
        AscendingIterator v0 = new AscendingIterator(this.mStart, this.mEnd);
        this.mIterators.put(v0, Boolean.valueOf(false));
        return ((Iterator)v0);
    }

    public IteratorWithAdditions iteratorWithAdditions() {
        IteratorWithAdditions v0 = new IteratorWithAdditions(this, null);
        this.mIterators.put(v0, Boolean.valueOf(false));
        return v0;
    }

    public Map$Entry newest() {
        return this.mEnd;
    }

    protected Entry put(@NonNull Object arg2, @NonNull Object arg3) {
        Entry v0 = new Entry(arg2, arg3);
        ++this.mSize;
        if(this.mEnd == null) {
            this.mStart = v0;
            this.mEnd = this.mStart;
            return v0;
        }

        this.mEnd.mNext = v0;
        v0.mPrevious = this.mEnd;
        this.mEnd = v0;
        return v0;
    }

    public Object putIfAbsent(@NonNull Object arg2, @NonNull Object arg3) {
        Entry v0 = this.get(arg2);
        if(v0 != null) {
            return v0.mValue;
        }

        this.put(arg2, arg3);
        return null;
    }

    public Object remove(@NonNull Object arg4) {
        Entry v4 = this.get(arg4);
        Object v0 = null;
        if(v4 == null) {
            return v0;
        }

        --this.mSize;
        if(!this.mIterators.isEmpty()) {
            Iterator v1 = this.mIterators.keySet().iterator();
            while(v1.hasNext()) {
                v1.next().supportRemove(v4);
            }
        }

        if(v4.mPrevious != null) {
            v4.mPrevious.mNext = v4.mNext;
        }
        else {
            this.mStart = v4.mNext;
        }

        if(v4.mNext != null) {
            v4.mNext.mPrevious = v4.mPrevious;
        }
        else {
            this.mEnd = v4.mPrevious;
        }

        v4.mNext = ((Entry)v0);
        v4.mPrevious = ((Entry)v0);
        return v4.mValue;
    }

    public int size() {
        return this.mSize;
    }

    public String toString() {
        StringBuilder v0 = new StringBuilder();
        v0.append("[");
        Iterator v1 = this.iterator();
        while(v1.hasNext()) {
            v0.append(v1.next().toString());
            if(!v1.hasNext()) {
                continue;
            }

            v0.append(", ");
        }

        v0.append("]");
        return v0.toString();
    }
}

