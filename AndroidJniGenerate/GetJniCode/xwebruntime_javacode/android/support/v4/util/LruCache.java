package android.support.v4.util;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map$Entry;
import java.util.Map;

public class LruCache {
    private int createCount;
    private int evictionCount;
    private int hitCount;
    private final LinkedHashMap map;
    private int maxSize;
    private int missCount;
    private int putCount;
    private int size;

    public LruCache(int arg4) {
        super();
        if(arg4 <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }

        this.maxSize = arg4;
        this.map = new LinkedHashMap(0, 0.75f, true);
    }

    protected Object create(Object arg1) {
        return null;
    }

    public final int createCount() {
        int v0_1;
        __monitor_enter(this);
        try {
            v0_1 = this.createCount;
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
        return v0_1;
    }

    protected void entryRemoved(boolean arg1, Object arg2, Object arg3, Object arg4) {
    }

    public final void evictAll() {
        this.trimToSize(-1);
    }

    public final int evictionCount() {
        int v0_1;
        __monitor_enter(this);
        try {
            v0_1 = this.evictionCount;
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
        return v0_1;
    }

    public final Object get(Object arg5) {
        Object v1;
        Object v0;
        if(arg5 == null) {
            throw new NullPointerException("key == null");
        }

        __monitor_enter(this);
        try {
            v0 = this.map.get(arg5);
            if(v0 != null) {
                ++this.hitCount;
                __monitor_exit(this);
                return v0;
            }

            ++this.missCount;
            __monitor_exit(this);
        }
        catch(Throwable v5) {
            try {
            label_48:
                __monitor_exit(this);
            }
            catch(Throwable v5) {
                goto label_48;
            }

            throw v5;
        }

        v0 = this.create(arg5);
        if(v0 == null) {
            return null;
        }

        __monitor_enter(this);
        try {
            ++this.createCount;
            v1 = this.map.put(arg5, v0);
            if(v1 != null) {
                this.map.put(arg5, v1);
            }
            else {
                this.size += this.safeSizeOf(arg5, v0);
            }

            __monitor_exit(this);
            if(v1 == null) {
                goto label_41;
            }
        }
        catch(Throwable v5) {
            try {
            label_45:
                __monitor_exit(this);
            }
            catch(Throwable v5) {
                goto label_45;
            }

            throw v5;
        }

        this.entryRemoved(false, arg5, v0, v1);
        return v1;
    label_41:
        this.trimToSize(this.maxSize);
        return v0;
    }

    public final int hitCount() {
        int v0_1;
        __monitor_enter(this);
        try {
            v0_1 = this.hitCount;
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
        return v0_1;
    }

    public final int maxSize() {
        int v0_1;
        __monitor_enter(this);
        try {
            v0_1 = this.maxSize;
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
        return v0_1;
    }

    public final int missCount() {
        int v0_1;
        __monitor_enter(this);
        try {
            v0_1 = this.missCount;
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
        return v0_1;
    }

    public final Object put(Object arg4, Object arg5) {
        Object v0;
        if(arg4 != null) {
            if(arg5 == null) {
            }
            else {
                __monitor_enter(this);
                try {
                    ++this.putCount;
                    this.size += this.safeSizeOf(arg4, arg5);
                    v0 = this.map.put(arg4, arg5);
                    if(v0 != null) {
                        this.size -= this.safeSizeOf(arg4, v0);
                    }

                    __monitor_exit(this);
                    if(v0 == null) {
                        goto label_22;
                    }
                }
                catch(Throwable v4) {
                    try {
                    label_26:
                        __monitor_exit(this);
                    }
                    catch(Throwable v4) {
                        goto label_26;
                    }

                    throw v4;
                }

                this.entryRemoved(false, arg4, v0, arg5);
            label_22:
                this.trimToSize(this.maxSize);
                return v0;
            }
        }

        throw new NullPointerException("key == null || value == null");
    }

    public final int putCount() {
        int v0_1;
        __monitor_enter(this);
        try {
            v0_1 = this.putCount;
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
        return v0_1;
    }

    public final Object remove(Object arg4) {
        Object v0;
        if(arg4 == null) {
            throw new NullPointerException("key == null");
        }

        __monitor_enter(this);
        try {
            v0 = this.map.remove(arg4);
            if(v0 != null) {
                this.size -= this.safeSizeOf(arg4, v0);
            }

            __monitor_exit(this);
            if(v0 == null) {
                return v0;
            }
        }
        catch(Throwable v4) {
            try {
            label_20:
                __monitor_exit(this);
            }
            catch(Throwable v4) {
                goto label_20;
            }

            throw v4;
        }

        this.entryRemoved(false, arg4, v0, null);
        return v0;
    }

    public void resize(int arg2) {
        if(arg2 <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }

        __monitor_enter(this);
        try {
            this.maxSize = arg2;
            __monitor_exit(this);
        }
        catch(Throwable v2) {
            try {
            label_11:
                __monitor_exit(this);
            }
            catch(Throwable v2) {
                goto label_11;
            }

            throw v2;
        }

        this.trimToSize(arg2);
    }

    private int safeSizeOf(Object arg4, Object arg5) {
        int v0 = this.sizeOf(arg4, arg5);
        if(v0 < 0) {
            StringBuilder v1 = new StringBuilder();
            v1.append("Negative size: ");
            v1.append(arg4);
            v1.append("=");
            v1.append(arg5);
            throw new IllegalStateException(v1.toString());
        }

        return v0;
    }

    public final int size() {
        int v0_1;
        __monitor_enter(this);
        try {
            v0_1 = this.size;
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
        return v0_1;
    }

    protected int sizeOf(Object arg1, Object arg2) {
        return 1;
    }

    public final Map snapshot() {
        LinkedHashMap v0_1;
        __monitor_enter(this);
        try {
            v0_1 = new LinkedHashMap(this.map);
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
        return ((Map)v0_1);
    }

    public final String toString() {
        String v0_2;
        __monitor_enter(this);
        try {
            int v0_1 = this.hitCount + this.missCount;
            v0_1 = v0_1 != 0 ? this.hitCount * 100 / v0_1 : 0;
            v0_2 = String.format(Locale.US, "LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", Integer.valueOf(this.maxSize), Integer.valueOf(this.hitCount), Integer.valueOf(this.missCount), Integer.valueOf(v0_1));
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
        return v0_2;
    }

    public void trimToSize(int arg5) {
        Object v1;
        Object v0;
        while(true) {
            __monitor_enter(this);
            try {
                if(this.size >= 0) {
                    if((this.map.isEmpty()) && this.size != 0) {
                        goto label_37;
                    }

                    if(this.size > arg5) {
                        if(this.map.isEmpty()) {
                        }
                        else {
                            v0 = this.map.entrySet().iterator().next();
                            v1 = ((Map$Entry)v0).getKey();
                            v0 = ((Map$Entry)v0).getValue();
                            this.map.remove(v1);
                            this.size -= this.safeSizeOf(v1, v0);
                            ++this.evictionCount;
                            __monitor_exit(this);
                            goto label_32;
                        }
                    }

                    break;
                }

                goto label_37;
            }
            catch(Throwable v5) {
                goto label_49;
            }

        label_32:
            this.entryRemoved(true, v1, v0, null);
        }

        try {
            __monitor_exit(this);
            return;
        label_37:
            StringBuilder v0_1 = new StringBuilder();
            v0_1.append(this.getClass().getName());
            v0_1.append(".sizeOf() is reporting inconsistent results!");
            throw new IllegalStateException(v0_1.toString());
        label_49:
            __monitor_exit(this);
        }
        catch(Throwable v5) {
            goto label_49;
        }

        throw v5;
    }
}

