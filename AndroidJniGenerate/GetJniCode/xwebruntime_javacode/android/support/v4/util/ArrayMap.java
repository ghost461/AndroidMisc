package android.support.v4.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map$Entry;
import java.util.Map;
import java.util.Set;

public class ArrayMap extends SimpleArrayMap implements Map {
    MapCollections mCollections;

    public ArrayMap() {
        super();
    }

    public ArrayMap(int arg1) {
        super(arg1);
    }

    public ArrayMap(SimpleArrayMap arg1) {
        super(arg1);
    }

    public boolean containsAll(Collection arg1) {
        return MapCollections.containsAllHelper(((Map)this), arg1);
    }

    public Set entrySet() {
        return this.getCollection().getEntrySet();
    }

    private MapCollections getCollection() {
        if(this.mCollections == null) {
            this.mCollections = new MapCollections() {
                protected void colClear() {
                    ArrayMap.this.clear();
                }

                protected Object colGetEntry(int arg2, int arg3) {
                    return ArrayMap.this.mArray[(arg2 << 1) + arg3];
                }

                protected Map colGetMap() {
                    return ArrayMap.this;
                }

                protected int colGetSize() {
                    return ArrayMap.this.mSize;
                }

                protected int colIndexOfKey(Object arg2) {
                    return ArrayMap.this.indexOfKey(arg2);
                }

                protected int colIndexOfValue(Object arg2) {
                    return ArrayMap.this.indexOfValue(arg2);
                }

                protected void colPut(Object arg2, Object arg3) {
                    ArrayMap.this.put(arg2, arg3);
                }

                protected void colRemoveAt(int arg2) {
                    ArrayMap.this.removeAt(arg2);
                }

                protected Object colSetValue(int arg2, Object arg3) {
                    return ArrayMap.this.setValueAt(arg2, arg3);
                }
            };
        }

        return this.mCollections;
    }

    public Set keySet() {
        return this.getCollection().getKeySet();
    }

    public void putAll(Map arg3) {
        this.ensureCapacity(this.mSize + arg3.size());
        Iterator v3 = arg3.entrySet().iterator();
        while(v3.hasNext()) {
            Object v0 = v3.next();
            this.put(((Map$Entry)v0).getKey(), ((Map$Entry)v0).getValue());
        }
    }

    public boolean removeAll(Collection arg1) {
        return MapCollections.removeAllHelper(((Map)this), arg1);
    }

    public boolean retainAll(Collection arg1) {
        return MapCollections.retainAllHelper(((Map)this), arg1);
    }

    public Collection values() {
        return this.getCollection().getValues();
    }
}

