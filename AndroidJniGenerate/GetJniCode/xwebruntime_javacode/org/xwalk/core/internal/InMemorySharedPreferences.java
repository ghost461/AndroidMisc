package org.xwalk.core.internal;

import android.content.SharedPreferences$Editor;
import android.content.SharedPreferences$OnSharedPreferenceChangeListener;
import android.content.SharedPreferences;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map$Entry;
import java.util.Map;
import java.util.Set;

class InMemorySharedPreferences implements SharedPreferences {
    class org.xwalk.core.internal.InMemorySharedPreferences$1 {
    }

    class InMemoryEditor implements SharedPreferences$Editor {
        private volatile boolean mApplyCalled;
        private final Map mChanges;
        private boolean mClearCalled;

        InMemoryEditor(InMemorySharedPreferences arg1, org.xwalk.core.internal.InMemorySharedPreferences$1 arg2) {
            this(arg1);
        }

        private InMemoryEditor(InMemorySharedPreferences arg1) {
            InMemorySharedPreferences.this = arg1;
            super();
            this.mChanges = new HashMap();
        }

        public void apply() {
            Map v1_1;
            Map v0 = InMemorySharedPreferences.this.mData;
            __monitor_enter(v0);
            try {
                v1_1 = this.mChanges;
                __monitor_enter(v1_1);
            }
            catch(Throwable v1) {
                goto label_41;
            }

            try {
                if(this.mApplyCalled) {
                    throw new IllegalStateException();
                }

                if(this.mClearCalled) {
                    InMemorySharedPreferences.this.mData.clear();
                }

                Iterator v2_1 = this.mChanges.entrySet().iterator();
                while(v2_1.hasNext()) {
                    Object v3 = v2_1.next();
                    Object v4 = ((Map$Entry)v3).getKey();
                    v3 = ((Map$Entry)v3).getValue();
                    if((((InMemoryEditor)v3)) == this) {
                        InMemorySharedPreferences.this.mData.remove(v4);
                        continue;
                    }

                    InMemorySharedPreferences.this.mData.put(v4, v3);
                }

                this.mApplyCalled = true;
                __monitor_exit(v1_1);
            }
            catch(Throwable v2) {
                goto label_38;
            }

            try {
                __monitor_exit(v0);
                return;
            }
            catch(Throwable v1) {
                goto label_41;
            }

            try {
            label_38:
                __monitor_exit(v1_1);
            }
            catch(Throwable v2) {
                goto label_38;
            }

            try {
                throw v2;
            label_41:
                __monitor_exit(v0);
            }
            catch(Throwable v1) {
                goto label_41;
            }

            throw v1;
        }

        public SharedPreferences$Editor clear() {
            Map v0 = this.mChanges;
            __monitor_enter(v0);
            try {
                if(this.mApplyCalled) {
                    throw new IllegalStateException();
                }

                this.mClearCalled = true;
                __monitor_exit(v0);
                return this;
            label_12:
                __monitor_exit(v0);
            }
            catch(Throwable v1) {
                goto label_12;
            }

            throw v1;
        }

        public boolean commit() {
            this.apply();
            return 1;
        }

        public SharedPreferences$Editor putBoolean(String arg3, boolean arg4) {
            Map v0 = this.mChanges;
            __monitor_enter(v0);
            try {
                if(this.mApplyCalled) {
                    throw new IllegalStateException();
                }

                this.mChanges.put(arg3, Boolean.valueOf(arg4));
                __monitor_exit(v0);
                return this;
            label_13:
                __monitor_exit(v0);
            }
            catch(Throwable v3) {
                goto label_13;
            }

            throw v3;
        }

        public SharedPreferences$Editor putFloat(String arg3, float arg4) {
            Map v0 = this.mChanges;
            __monitor_enter(v0);
            try {
                if(this.mApplyCalled) {
                    throw new IllegalStateException();
                }

                this.mChanges.put(arg3, Float.valueOf(arg4));
                __monitor_exit(v0);
                return this;
            label_13:
                __monitor_exit(v0);
            }
            catch(Throwable v3) {
                goto label_13;
            }

            throw v3;
        }

        public SharedPreferences$Editor putInt(String arg3, int arg4) {
            Map v0 = this.mChanges;
            __monitor_enter(v0);
            try {
                if(this.mApplyCalled) {
                    throw new IllegalStateException();
                }

                this.mChanges.put(arg3, Integer.valueOf(arg4));
                __monitor_exit(v0);
                return this;
            label_13:
                __monitor_exit(v0);
            }
            catch(Throwable v3) {
                goto label_13;
            }

            throw v3;
        }

        public SharedPreferences$Editor putLong(String arg3, long arg4) {
            Map v0 = this.mChanges;
            __monitor_enter(v0);
            try {
                if(this.mApplyCalled) {
                    throw new IllegalStateException();
                }

                this.mChanges.put(arg3, Long.valueOf(arg4));
                __monitor_exit(v0);
                return this;
            label_13:
                __monitor_exit(v0);
            }
            catch(Throwable v3) {
                goto label_13;
            }

            throw v3;
        }

        public SharedPreferences$Editor putString(String arg3, String arg4) {
            Map v0 = this.mChanges;
            __monitor_enter(v0);
            try {
                if(this.mApplyCalled) {
                    throw new IllegalStateException();
                }

                this.mChanges.put(arg3, arg4);
                __monitor_exit(v0);
                return this;
            label_12:
                __monitor_exit(v0);
            }
            catch(Throwable v3) {
                goto label_12;
            }

            throw v3;
        }

        public SharedPreferences$Editor putStringSet(String arg3, Set arg4) {
            Map v0 = this.mChanges;
            __monitor_enter(v0);
            try {
                if(this.mApplyCalled) {
                    throw new IllegalStateException();
                }

                this.mChanges.put(arg3, arg4);
                __monitor_exit(v0);
                return this;
            label_12:
                __monitor_exit(v0);
            }
            catch(Throwable v3) {
                goto label_12;
            }

            throw v3;
        }

        public SharedPreferences$Editor remove(String arg3) {
            Map v0 = this.mChanges;
            __monitor_enter(v0);
            try {
                if(this.mApplyCalled) {
                    throw new IllegalStateException();
                }

                this.mChanges.put(arg3, this);
                __monitor_exit(v0);
                return this;
            label_12:
                __monitor_exit(v0);
            }
            catch(Throwable v3) {
                goto label_12;
            }

            throw v3;
        }
    }

    private final Map mData;

    public InMemorySharedPreferences() {
        super();
        this.mData = new HashMap();
    }

    public InMemorySharedPreferences(Map arg1) {
        super();
        this.mData = arg1;
    }

    static Map access$100(InMemorySharedPreferences arg0) {
        return arg0.mData;
    }

    public boolean contains(String arg3) {
        Map v0 = this.mData;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return this.mData.containsKey(arg3);
        label_7:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_7;
        }

        throw v3;
    }

    public SharedPreferences$Editor edit() {
        return new InMemoryEditor(this, null);
    }

    public Map getAll() {
        Map v0 = this.mData;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return Collections.unmodifiableMap(this.mData);
        label_7:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_7;
        }

        throw v1;
    }

    public boolean getBoolean(String arg3, boolean arg4) {
        Map v0 = this.mData;
        __monitor_enter(v0);
        try {
            if(this.mData.containsKey(arg3)) {
                __monitor_exit(v0);
                return this.mData.get(arg3).booleanValue();
            }

            __monitor_exit(v0);
            return arg4;
        label_13:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_13;
        }

        throw v3;
    }

    public float getFloat(String arg3, float arg4) {
        Map v0 = this.mData;
        __monitor_enter(v0);
        try {
            if(this.mData.containsKey(arg3)) {
                __monitor_exit(v0);
                return this.mData.get(arg3).floatValue();
            }

            __monitor_exit(v0);
            return arg4;
        label_13:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_13;
        }

        throw v3;
    }

    public int getInt(String arg3, int arg4) {
        Map v0 = this.mData;
        __monitor_enter(v0);
        try {
            if(this.mData.containsKey(arg3)) {
                __monitor_exit(v0);
                return this.mData.get(arg3).intValue();
            }

            __monitor_exit(v0);
            return arg4;
        label_13:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_13;
        }

        throw v3;
    }

    public long getLong(String arg3, long arg4) {
        Map v0 = this.mData;
        __monitor_enter(v0);
        try {
            if(this.mData.containsKey(arg3)) {
                __monitor_exit(v0);
                return this.mData.get(arg3).longValue();
            }

            __monitor_exit(v0);
            return arg4;
        label_13:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_13;
        }

        throw v3;
    }

    public String getString(String arg3, String arg4) {
        Map v0 = this.mData;
        __monitor_enter(v0);
        try {
            if(this.mData.containsKey(arg3)) {
                __monitor_exit(v0);
                return this.mData.get(arg3);
            }

            __monitor_exit(v0);
            return arg4;
        label_12:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_12;
        }

        throw v3;
    }

    public Set getStringSet(String arg3, Set arg4) {
        Map v0 = this.mData;
        __monitor_enter(v0);
        try {
            if(this.mData.containsKey(arg3)) {
                __monitor_exit(v0);
                return Collections.unmodifiableSet(this.mData.get(arg3));
            }

            __monitor_exit(v0);
            return arg4;
        label_13:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_13;
        }

        throw v3;
    }

    public void registerOnSharedPreferenceChangeListener(SharedPreferences$OnSharedPreferenceChangeListener arg1) {
        throw new UnsupportedOperationException();
    }

    public void unregisterOnSharedPreferenceChangeListener(SharedPreferences$OnSharedPreferenceChangeListener arg1) {
        throw new UnsupportedOperationException();
    }
}

