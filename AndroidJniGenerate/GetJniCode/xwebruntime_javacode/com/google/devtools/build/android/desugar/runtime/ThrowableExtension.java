package com.google.devtools.build.android.desugar.runtime;

import java.io.Closeable;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

public final class ThrowableExtension {
    abstract class AbstractDesugaringStrategy {
        protected static final Throwable[] EMPTY_THROWABLE_ARRAY;

        static {
            AbstractDesugaringStrategy.EMPTY_THROWABLE_ARRAY = new Throwable[0];
        }

        AbstractDesugaringStrategy() {
            super();
        }

        public abstract void addSuppressed(Throwable arg1, Throwable arg2);

        public abstract Throwable[] getSuppressed(Throwable arg1);

        public abstract void printStackTrace(Throwable arg1);

        public abstract void printStackTrace(Throwable arg1, PrintStream arg2);

        public abstract void printStackTrace(Throwable arg1, PrintWriter arg2);
    }

    final class ConcurrentWeakIdentityHashMap {
        final class WeakKey extends WeakReference {
            private final int hash;

            public WeakKey(Throwable arg1, ReferenceQueue arg2) {
                super(arg1, arg2);
                if(arg1 == null) {
                    throw new NullPointerException("The referent cannot be null");
                }

                this.hash = System.identityHashCode(arg1);
            }

            public boolean equals(Object arg5) {
                boolean v0 = false;
                if(arg5 != null) {
                    if(arg5.getClass() != this.getClass()) {
                    }
                    else if(this == (((WeakKey)arg5))) {
                        return 1;
                    }
                    else {
                        if(this.hash == ((WeakKey)arg5).hash && this.get() == ((WeakKey)arg5).get()) {
                            v0 = true;
                        }

                        return v0;
                    }
                }

                return 0;
            }

            public int hashCode() {
                return this.hash;
            }
        }

        private final ConcurrentHashMap map;
        private final ReferenceQueue referenceQueue;

        ConcurrentWeakIdentityHashMap() {
            super();
            this.map = new ConcurrentHashMap(16, 0.75f, 10);
            this.referenceQueue = new ReferenceQueue();
        }

        void deleteEmptyKeys() {
            while(true) {
                Reference v0 = this.referenceQueue.poll();
                if(v0 == null) {
                    return;
                }

                this.map.remove(v0);
            }
        }

        public List get(Throwable arg4, boolean arg5) {
            this.deleteEmptyKeys();
            Object v0 = this.map.get(new WeakKey(arg4, null));
            if(!arg5) {
                return ((List)v0);
            }

            if(v0 != null) {
                return ((List)v0);
            }

            Vector v5 = new Vector(2);
            Object v4 = this.map.putIfAbsent(new WeakKey(arg4, this.referenceQueue), v5);
            if(v4 == null) {
                Vector v4_1 = v5;
            }

            return ((List)v4);
        }

        int size() {
            return this.map.size();
        }
    }

    final class MimicDesugaringStrategy extends AbstractDesugaringStrategy {
        static final String SUPPRESSED_PREFIX = "Suppressed: ";
        private final ConcurrentWeakIdentityHashMap map;

        MimicDesugaringStrategy() {
            super();
            this.map = new ConcurrentWeakIdentityHashMap();
        }

        public void addSuppressed(Throwable arg3, Throwable arg4) {
            if(arg4 == arg3) {
                throw new IllegalArgumentException("Self suppression is not allowed.", arg4);
            }

            if(arg4 == null) {
                throw new NullPointerException("The suppressed exception cannot be null.");
            }

            this.map.get(arg3, true).add(arg4);
        }

        public Throwable[] getSuppressed(Throwable arg3) {
            List v3 = this.map.get(arg3, false);
            if(v3 != null) {
                if(v3.isEmpty()) {
                }
                else {
                    return v3.toArray(MimicDesugaringStrategy.EMPTY_THROWABLE_ARRAY);
                }
            }

            return MimicDesugaringStrategy.EMPTY_THROWABLE_ARRAY;
        }

        public void printStackTrace(Throwable arg5) {
            arg5.printStackTrace();
            List v5 = this.map.get(arg5, false);
            if(v5 == null) {
                return;
            }

            __monitor_enter(v5);
            try {
                Iterator v0_1 = v5.iterator();
                while(v0_1.hasNext()) {
                    Object v1 = v0_1.next();
                    System.err.print("Suppressed: ");
                    ((Throwable)v1).printStackTrace();
                }

                __monitor_exit(v5);
                return;
            label_19:
                __monitor_exit(v5);
            }
            catch(Throwable v0) {
                goto label_19;
            }

            throw v0;
        }

        public void printStackTrace(Throwable arg4, PrintStream arg5) {
            arg4.printStackTrace(arg5);
            List v4 = this.map.get(arg4, false);
            if(v4 == null) {
                return;
            }

            __monitor_enter(v4);
            try {
                Iterator v0 = v4.iterator();
                while(v0.hasNext()) {
                    Object v1 = v0.next();
                    arg5.print("Suppressed: ");
                    ((Throwable)v1).printStackTrace(arg5);
                }

                __monitor_exit(v4);
                return;
            label_18:
                __monitor_exit(v4);
            }
            catch(Throwable v5) {
                goto label_18;
            }

            throw v5;
        }

        public void printStackTrace(Throwable arg4, PrintWriter arg5) {
            arg4.printStackTrace(arg5);
            List v4 = this.map.get(arg4, false);
            if(v4 == null) {
                return;
            }

            __monitor_enter(v4);
            try {
                Iterator v0 = v4.iterator();
                while(v0.hasNext()) {
                    Object v1 = v0.next();
                    arg5.print("Suppressed: ");
                    ((Throwable)v1).printStackTrace(arg5);
                }

                __monitor_exit(v4);
                return;
            label_18:
                __monitor_exit(v4);
            }
            catch(Throwable v5) {
                goto label_18;
            }

            throw v5;
        }
    }

    final class NullDesugaringStrategy extends AbstractDesugaringStrategy {
        NullDesugaringStrategy() {
            super();
        }

        public void addSuppressed(Throwable arg1, Throwable arg2) {
        }

        public Throwable[] getSuppressed(Throwable arg1) {
            return NullDesugaringStrategy.EMPTY_THROWABLE_ARRAY;
        }

        public void printStackTrace(Throwable arg1) {
            arg1.printStackTrace();
        }

        public void printStackTrace(Throwable arg1, PrintStream arg2) {
            arg1.printStackTrace(arg2);
        }

        public void printStackTrace(Throwable arg1, PrintWriter arg2) {
            arg1.printStackTrace(arg2);
        }
    }

    final class ReuseDesugaringStrategy extends AbstractDesugaringStrategy {
        ReuseDesugaringStrategy() {
            super();
        }

        public void addSuppressed(Throwable arg1, Throwable arg2) {
            arg1.addSuppressed(arg2);
        }

        public Throwable[] getSuppressed(Throwable arg1) {
            return arg1.getSuppressed();
        }

        public void printStackTrace(Throwable arg1) {
            arg1.printStackTrace();
        }

        public void printStackTrace(Throwable arg1, PrintStream arg2) {
            arg1.printStackTrace(arg2);
        }

        public void printStackTrace(Throwable arg1, PrintWriter arg2) {
            arg1.printStackTrace(arg2);
        }
    }

    private static final String ANDROID_OS_BUILD_VERSION = "android.os.Build$VERSION";
    static final int API_LEVEL = 0;
    static final AbstractDesugaringStrategy STRATEGY = null;
    public static final String SYSTEM_PROPERTY_TWR_DISABLE_MIMIC = "com.google.devtools.build.android.desugar.runtime.twr_disable_mimic";

    static {
        NullDesugaringStrategy v1_2;
        Integer v0;
        try {
            v0 = ThrowableExtension.readApiLevelFromBuildVersion();
            if(v0 == null) {
                goto label_10;
            }
        }
        catch(Throwable v1) {
            v0 = null;
            goto label_20;
        }

        try {
            if(v0.intValue() >= 19) {
                ReuseDesugaringStrategy v1_1 = new ReuseDesugaringStrategy();
                goto label_36;
            }

        label_10:
            if(ThrowableExtension.useMimicStrategy()) {
                v1_2 = new NullDesugaringStrategy();
                goto label_36;
            }

            v1_2 = new NullDesugaringStrategy();
            goto label_36;
        label_9:
        }
        catch(Throwable v1) {
            goto label_9;
        }

    label_20:
        PrintStream v2 = System.err;
        v2.println("An error has occured when initializing the try-with-resources desuguring strategy. The default strategy " + NullDesugaringStrategy.class.getName() + "will be used. The error is: ");
        v1.printStackTrace(System.err);
        v1_2 = new NullDesugaringStrategy();
    label_36:
        ThrowableExtension.STRATEGY = ((AbstractDesugaringStrategy)v1_2);
        int v0_1 = v0 == null ? 1 : v0.intValue();
        ThrowableExtension.API_LEVEL = v0_1;
    }

    public ThrowableExtension() {
        super();
    }

    public static void addSuppressed(Throwable arg1, Throwable arg2) {
        ThrowableExtension.STRATEGY.addSuppressed(arg1, arg2);
    }

    public static void closeResource(Throwable arg4, Object arg5) throws Throwable {
        if(arg5 == null) {
            return;
        }

        try {
            if(ThrowableExtension.API_LEVEL >= 19) {
                ((AutoCloseable)arg5).close();
            }
            else if((arg5 instanceof Closeable)) {
                ((Closeable)arg5).close();
            }
            else {
                try {
                    arg5.getClass().getMethod("close").invoke(arg5);
                }
                catch(InvocationTargetException v5_1) {
                    try {
                        throw v5_1.getCause();
                    }
                    catch(Throwable v5) {
                    label_45:
                        if(arg4 != null) {
                            ThrowableExtension.addSuppressed(arg4, v5);
                            throw arg4;
                        }
                        else {
                            throw v5;
                        }
                    }
                }
                catch(ExceptionInInitializerError v0) {
                }
                catch(SecurityException v0_1) {
                    try {
                        StringBuilder v2 = new StringBuilder();
                        v2.append(arg5.getClass());
                        v2.append(" does not have a close() method.");
                        throw new AssertionError(v2.toString(), ((Throwable)v0_1));
                        v2 = new StringBuilder();
                        v2.append("Fail to call close() on ");
                        v2.append(arg5.getClass());
                        throw new AssertionError(v2.toString(), ((Throwable)v0));
                    }
                    catch(Throwable v5) {
                        goto label_45;
                    }
                }
            }
        }
        catch(Throwable v5) {
            goto label_45;
        }
    }

    public static AbstractDesugaringStrategy getStrategy() {
        return ThrowableExtension.STRATEGY;
    }

    public static Throwable[] getSuppressed(Throwable arg1) {
        return ThrowableExtension.STRATEGY.getSuppressed(arg1);
    }

    public static void printStackTrace(Throwable arg1) {
        ThrowableExtension.STRATEGY.printStackTrace(arg1);
    }

    public static void printStackTrace(Throwable arg1, PrintStream arg2) {
        ThrowableExtension.STRATEGY.printStackTrace(arg1, arg2);
    }

    public static void printStackTrace(Throwable arg1, PrintWriter arg2) {
        ThrowableExtension.STRATEGY.printStackTrace(arg1, arg2);
    }

    private static Integer readApiLevelFromBuildVersion() {
        Object v0 = null;
        try {
            return Class.forName("android.os.Build$VERSION").getField("SDK_INT").get(v0);
        }
        catch(Exception v1) {
            System.err.println("Failed to retrieve value from android.os.Build$VERSION.SDK_INT due to the following exception.");
            v1.printStackTrace(System.err);
            return ((Integer)v0);
        }
    }

    private static boolean useMimicStrategy() {
        return Boolean.getBoolean("com.google.devtools.build.android.desugar.runtime.twr_disable_mimic") ^ 1;
    }
}

