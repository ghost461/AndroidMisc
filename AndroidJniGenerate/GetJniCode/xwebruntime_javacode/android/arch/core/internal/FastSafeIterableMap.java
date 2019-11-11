package android.arch.core.internal;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import java.util.HashMap;
import java.util.Map$Entry;

@RestrictTo(value={Scope.LIBRARY_GROUP}) public class FastSafeIterableMap extends SafeIterableMap {
    private HashMap mHashMap;

    public FastSafeIterableMap() {
        super();
        this.mHashMap = new HashMap();
    }

    public Map$Entry ceil(Object arg2) {
        if(this.contains(arg2)) {
            return this.mHashMap.get(arg2).mPrevious;
        }

        return null;
    }

    public boolean contains(Object arg2) {
        return this.mHashMap.containsKey(arg2);
    }

    protected Entry get(Object arg2) {
        return this.mHashMap.get(arg2);
    }

    public Object putIfAbsent(@NonNull Object arg2, @NonNull Object arg3) {
        Entry v0 = this.get(arg2);
        if(v0 != null) {
            return v0.mValue;
        }

        this.mHashMap.put(arg2, this.put(arg2, arg3));
        return null;
    }

    public Object remove(@NonNull Object arg3) {
        Object v0 = super.remove(arg3);
        this.mHashMap.remove(arg3);
        return v0;
    }
}

