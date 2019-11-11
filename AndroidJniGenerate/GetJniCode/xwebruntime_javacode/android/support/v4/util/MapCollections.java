package android.support.v4.util;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map$Entry;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

abstract class MapCollections {
    final class ArrayIterator implements Iterator {
        boolean mCanRemove;
        int mIndex;
        final int mOffset;
        int mSize;

        ArrayIterator(MapCollections arg2, int arg3) {
            MapCollections.this = arg2;
            super();
            this.mCanRemove = false;
            this.mOffset = arg3;
            this.mSize = arg2.colGetSize();
        }

        public boolean hasNext() {
            boolean v0 = this.mIndex < this.mSize ? true : false;
            return v0;
        }

        public Object next() {
            if(!this.hasNext()) {
                throw new NoSuchElementException();
            }

            Object v0 = MapCollections.this.colGetEntry(this.mIndex, this.mOffset);
            ++this.mIndex;
            this.mCanRemove = true;
            return v0;
        }

        public void remove() {
            if(!this.mCanRemove) {
                throw new IllegalStateException();
            }

            --this.mIndex;
            --this.mSize;
            this.mCanRemove = false;
            MapCollections.this.colRemoveAt(this.mIndex);
        }
    }

    final class EntrySet implements Set {
        EntrySet(MapCollections arg1) {
            MapCollections.this = arg1;
            super();
        }

        public boolean add(Object arg1) {
            return this.add(((Map$Entry)arg1));
        }

        public boolean add(Map$Entry arg1) {
            throw new UnsupportedOperationException();
        }

        public boolean addAll(Collection arg5) {
            int v0 = MapCollections.this.colGetSize();
            Iterator v5 = arg5.iterator();
            while(v5.hasNext()) {
                Object v1 = v5.next();
                MapCollections.this.colPut(((Map$Entry)v1).getKey(), ((Map$Entry)v1).getValue());
            }

            boolean v5_1 = v0 != MapCollections.this.colGetSize() ? true : false;
            return v5_1;
        }

        public void clear() {
            MapCollections.this.colClear();
        }

        public boolean contains(Object arg4) {
            if(!(arg4 instanceof Map$Entry)) {
                return 0;
            }

            int v0 = MapCollections.this.colIndexOfKey(((Map$Entry)arg4).getKey());
            if(v0 < 0) {
                return 0;
            }

            return ContainerHelpers.equal(MapCollections.this.colGetEntry(v0, 1), ((Map$Entry)arg4).getValue());
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

        public boolean equals(Object arg1) {
            return MapCollections.equalsSetHelper(((Set)this), arg1);
        }

        public int hashCode() {
            int v0 = MapCollections.this.colGetSize() - 1;
            int v3 = 0;
            while(v0 >= 0) {
                Object v4 = MapCollections.this.colGetEntry(v0, 0);
                Object v5 = MapCollections.this.colGetEntry(v0, 1);
                int v4_1 = v4 == null ? 0 : v4.hashCode();
                int v5_1 = v5 == null ? 0 : v5.hashCode();
                v3 += v4_1 ^ v5_1;
                --v0;
            }

            return v3;
        }

        public boolean isEmpty() {
            boolean v0 = MapCollections.this.colGetSize() == 0 ? true : false;
            return v0;
        }

        public Iterator iterator() {
            return new MapIterator(MapCollections.this);
        }

        public boolean remove(Object arg1) {
            throw new UnsupportedOperationException();
        }

        public boolean removeAll(Collection arg1) {
            throw new UnsupportedOperationException();
        }

        public boolean retainAll(Collection arg1) {
            throw new UnsupportedOperationException();
        }

        public int size() {
            return MapCollections.this.colGetSize();
        }

        public Object[] toArray() {
            throw new UnsupportedOperationException();
        }

        public Object[] toArray(Object[] arg1) {
            throw new UnsupportedOperationException();
        }
    }

    final class KeySet implements Set {
        KeySet(MapCollections arg1) {
            MapCollections.this = arg1;
            super();
        }

        public boolean add(Object arg1) {
            throw new UnsupportedOperationException();
        }

        public boolean addAll(Collection arg1) {
            throw new UnsupportedOperationException();
        }

        public void clear() {
            MapCollections.this.colClear();
        }

        public boolean contains(Object arg2) {
            boolean v2 = MapCollections.this.colIndexOfKey(arg2) >= 0 ? true : false;
            return v2;
        }

        public boolean containsAll(Collection arg2) {
            return MapCollections.containsAllHelper(MapCollections.this.colGetMap(), arg2);
        }

        public boolean equals(Object arg1) {
            return MapCollections.equalsSetHelper(((Set)this), arg1);
        }

        public int hashCode() {
            int v0 = MapCollections.this.colGetSize() - 1;
            int v2 = 0;
            while(v0 >= 0) {
                Object v3 = MapCollections.this.colGetEntry(v0, 0);
                int v3_1 = v3 == null ? 0 : v3.hashCode();
                v2 += v3_1;
                --v0;
            }

            return v2;
        }

        public boolean isEmpty() {
            boolean v0 = MapCollections.this.colGetSize() == 0 ? true : false;
            return v0;
        }

        public Iterator iterator() {
            return new ArrayIterator(MapCollections.this, 0);
        }

        public boolean remove(Object arg2) {
            int v2 = MapCollections.this.colIndexOfKey(arg2);
            if(v2 >= 0) {
                MapCollections.this.colRemoveAt(v2);
                return 1;
            }

            return 0;
        }

        public boolean removeAll(Collection arg2) {
            return MapCollections.removeAllHelper(MapCollections.this.colGetMap(), arg2);
        }

        public boolean retainAll(Collection arg2) {
            return MapCollections.retainAllHelper(MapCollections.this.colGetMap(), arg2);
        }

        public int size() {
            return MapCollections.this.colGetSize();
        }

        public Object[] toArray() {
            return MapCollections.this.toArrayHelper(0);
        }

        public Object[] toArray(Object[] arg3) {
            return MapCollections.this.toArrayHelper(arg3, 0);
        }
    }

    final class MapIterator implements Iterator, Map$Entry {
        int mEnd;
        boolean mEntryValid;
        int mIndex;

        MapIterator(MapCollections arg2) {
            MapCollections.this = arg2;
            super();
            this.mEntryValid = false;
            this.mEnd = arg2.colGetSize() - 1;
            this.mIndex = -1;
        }

        public final boolean equals(Object arg5) {
            if(!this.mEntryValid) {
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            }

            boolean v1 = false;
            if(!(arg5 instanceof Map$Entry)) {
                return 0;
            }

            if((ContainerHelpers.equal(((Map$Entry)arg5).getKey(), MapCollections.this.colGetEntry(this.mIndex, 0))) && (ContainerHelpers.equal(((Map$Entry)arg5).getValue(), MapCollections.this.colGetEntry(this.mIndex, 1)))) {
                v1 = true;
            }

            return v1;
        }

        public Object getKey() {
            if(!this.mEntryValid) {
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            }

            return MapCollections.this.colGetEntry(this.mIndex, 0);
        }

        public Object getValue() {
            if(!this.mEntryValid) {
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            }

            return MapCollections.this.colGetEntry(this.mIndex, 1);
        }

        public boolean hasNext() {
            boolean v0 = this.mIndex < this.mEnd ? true : false;
            return v0;
        }

        public final int hashCode() {
            if(!this.mEntryValid) {
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            }

            int v2 = 0;
            Object v0 = MapCollections.this.colGetEntry(this.mIndex, 0);
            Object v1 = MapCollections.this.colGetEntry(this.mIndex, 1);
            int v0_1 = v0 == null ? 0 : v0.hashCode();
            if(v1 == null) {
            }
            else {
                v2 = v1.hashCode();
            }

            return v0_1 ^ v2;
        }

        public Object next() {
            return this.next();
        }

        public Map$Entry next() {
            if(!this.hasNext()) {
                throw new NoSuchElementException();
            }

            ++this.mIndex;
            this.mEntryValid = true;
            return this;
        }

        public void remove() {
            if(!this.mEntryValid) {
                throw new IllegalStateException();
            }

            MapCollections.this.colRemoveAt(this.mIndex);
            --this.mIndex;
            --this.mEnd;
            this.mEntryValid = false;
        }

        public Object setValue(Object arg3) {
            if(!this.mEntryValid) {
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            }

            return MapCollections.this.colSetValue(this.mIndex, arg3);
        }

        public final String toString() {
            return this.getKey() + "=" + this.getValue();
        }
    }

    final class ValuesCollection implements Collection {
        ValuesCollection(MapCollections arg1) {
            MapCollections.this = arg1;
            super();
        }

        public boolean add(Object arg1) {
            throw new UnsupportedOperationException();
        }

        public boolean addAll(Collection arg1) {
            throw new UnsupportedOperationException();
        }

        public void clear() {
            MapCollections.this.colClear();
        }

        public boolean contains(Object arg2) {
            boolean v2 = MapCollections.this.colIndexOfValue(arg2) >= 0 ? true : false;
            return v2;
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

        public boolean isEmpty() {
            boolean v0 = MapCollections.this.colGetSize() == 0 ? true : false;
            return v0;
        }

        public Iterator iterator() {
            return new ArrayIterator(MapCollections.this, 1);
        }

        public boolean remove(Object arg2) {
            int v2 = MapCollections.this.colIndexOfValue(arg2);
            if(v2 >= 0) {
                MapCollections.this.colRemoveAt(v2);
                return 1;
            }

            return 0;
        }

        public boolean removeAll(Collection arg6) {
            int v0 = MapCollections.this.colGetSize();
            int v1 = 0;
            boolean v2 = false;
            while(v1 < v0) {
                if(arg6.contains(MapCollections.this.colGetEntry(v1, 1))) {
                    MapCollections.this.colRemoveAt(v1);
                    --v1;
                    --v0;
                    v2 = true;
                }

                ++v1;
            }

            return v2;
        }

        public boolean retainAll(Collection arg6) {
            int v0 = MapCollections.this.colGetSize();
            int v1 = 0;
            boolean v2 = false;
            while(v1 < v0) {
                if(!arg6.contains(MapCollections.this.colGetEntry(v1, 1))) {
                    MapCollections.this.colRemoveAt(v1);
                    --v1;
                    --v0;
                    v2 = true;
                }

                ++v1;
            }

            return v2;
        }

        public int size() {
            return MapCollections.this.colGetSize();
        }

        public Object[] toArray() {
            return MapCollections.this.toArrayHelper(1);
        }

        public Object[] toArray(Object[] arg3) {
            return MapCollections.this.toArrayHelper(arg3, 1);
        }
    }

    EntrySet mEntrySet;
    KeySet mKeySet;
    ValuesCollection mValues;

    MapCollections() {
        super();
    }

    protected abstract void colClear();

    protected abstract Object colGetEntry(int arg1, int arg2);

    protected abstract Map colGetMap();

    protected abstract int colGetSize();

    protected abstract int colIndexOfKey(Object arg1);

    protected abstract int colIndexOfValue(Object arg1);

    protected abstract void colPut(Object arg1, Object arg2);

    protected abstract void colRemoveAt(int arg1);

    protected abstract Object colSetValue(int arg1, Object arg2);

    public static boolean containsAllHelper(Map arg1, Collection arg2) {
        Iterator v2 = arg2.iterator();
        do {
            if(!v2.hasNext()) {
                return 1;
            }
        }
        while(arg1.containsKey(v2.next()));

        return 0;
    }

    public static boolean equalsSetHelper(Set arg4, Object arg5) {
        boolean v0 = true;
        if(arg4 == (((Set)arg5))) {
            return 1;
        }

        if(!(arg5 instanceof Set)) {
            return 0;
        }

        try {
            if(arg4.size() != ((Set)arg5).size()) {
                return false;
            }
            else if(!arg4.containsAll(((Collection)arg5))) {
                return false;
            }
        }
        catch(ClassCastException ) {
            return 0;
        }
        catch(NullPointerException ) {
            return 0;
        }

        return v0;
    }

    public Set getEntrySet() {
        if(this.mEntrySet == null) {
            this.mEntrySet = new EntrySet(this);
        }

        return this.mEntrySet;
    }

    public Set getKeySet() {
        if(this.mKeySet == null) {
            this.mKeySet = new KeySet(this);
        }

        return this.mKeySet;
    }

    public Collection getValues() {
        if(this.mValues == null) {
            this.mValues = new ValuesCollection(this);
        }

        return this.mValues;
    }

    public static boolean removeAllHelper(Map arg2, Collection arg3) {
        int v0 = arg2.size();
        Iterator v3 = arg3.iterator();
        while(v3.hasNext()) {
            arg2.remove(v3.next());
        }

        boolean v2 = v0 != arg2.size() ? true : false;
        return v2;
    }

    public static boolean retainAllHelper(Map arg3, Collection arg4) {
        int v0 = arg3.size();
        Iterator v1 = arg3.keySet().iterator();
        while(v1.hasNext()) {
            if(arg4.contains(v1.next())) {
                continue;
            }

            v1.remove();
        }

        boolean v3 = v0 != arg3.size() ? true : false;
        return v3;
    }

    public Object[] toArrayHelper(int arg5) {
        int v0 = this.colGetSize();
        Object[] v1 = new Object[v0];
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            v1[v2] = this.colGetEntry(v2, arg5);
        }

        return v1;
    }

    public Object[] toArrayHelper(Object[] arg4, int arg5) {
        Object v4;
        int v0 = this.colGetSize();
        if(arg4.length < v0) {
            v4 = Array.newInstance(arg4.getClass().getComponentType(), v0);
        }

        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            ((Object[])v4)[v1] = this.colGetEntry(v1, arg5);
        }

        if(((Object[])v4).length > v0) {
            ((Object[])v4)[v0] = null;
        }

        return ((Object[])v4);
    }
}

