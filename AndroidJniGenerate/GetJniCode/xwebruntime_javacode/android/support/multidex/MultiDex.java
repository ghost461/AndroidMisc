package android.support.multidex;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build$VERSION;
import android.util.Log;
import dalvik.system.DexFile;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipFile;

public final class MultiDex {
    final class V14 {
        interface ElementConstructor {
            Object newInstance(File arg1, DexFile arg2) throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException, IOException;
        }

        class ICSElementConstructor implements ElementConstructor {
            private final Constructor elementConstructor;

            ICSElementConstructor(Class arg5) throws SecurityException, NoSuchMethodException {
                super();
                this.elementConstructor = arg5.getConstructor(File.class, ZipFile.class, DexFile.class);
                this.elementConstructor.setAccessible(true);
            }

            public Object newInstance(File arg4, DexFile arg5) throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException, IOException {
                return this.elementConstructor.newInstance(arg4, new ZipFile(arg4), arg5);
            }
        }

        class JBMR11ElementConstructor implements ElementConstructor {
            private final Constructor elementConstructor;

            JBMR11ElementConstructor(Class arg5) throws SecurityException, NoSuchMethodException {
                super();
                this.elementConstructor = arg5.getConstructor(File.class, File.class, DexFile.class);
                this.elementConstructor.setAccessible(true);
            }

            public Object newInstance(File arg4, DexFile arg5) throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
                return this.elementConstructor.newInstance(arg4, arg4, arg5);
            }
        }

        class JBMR2ElementConstructor implements ElementConstructor {
            private final Constructor elementConstructor;

            JBMR2ElementConstructor(Class arg5) throws SecurityException, NoSuchMethodException {
                super();
                this.elementConstructor = arg5.getConstructor(File.class, Boolean.TYPE, File.class, DexFile.class);
                this.elementConstructor.setAccessible(true);
            }

            public Object newInstance(File arg5, DexFile arg6) throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
                return this.elementConstructor.newInstance(arg5, Boolean.FALSE, arg5, arg6);
            }
        }

        private static final int EXTRACTED_SUFFIX_LENGTH;
        private final ElementConstructor elementConstructor;

        static {
            V14.EXTRACTED_SUFFIX_LENGTH = ".zip".length();
        }

        private V14() throws ClassNotFoundException, SecurityException, NoSuchMethodException {
            JBMR11ElementConstructor v1_1;
            super();
            Class v0 = Class.forName("dalvik.system.DexPathList$Element");
            try {
                ICSElementConstructor v1 = new ICSElementConstructor(v0);
            }
            catch(NoSuchMethodException ) {
                try {
                    v1_1 = new JBMR11ElementConstructor(v0);
                }
                catch(NoSuchMethodException ) {
                    JBMR2ElementConstructor v1_2 = new JBMR2ElementConstructor(v0);
                }
            }

            this.elementConstructor = ((ElementConstructor)v1_1);
        }

        static void install(ClassLoader arg3, List arg4) throws IOException, SecurityException, IllegalArgumentException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
            Object v3 = MultiDex.findField(arg3, "pathList").get(arg3);
            Object[] v4 = new V14().makeDexElements(arg4);
            try {
                MultiDex.expandFieldArray(v3, "dexElements", v4);
            }
            catch(NoSuchFieldException v0) {
                Log.w("MultiDex", "Failed find field \'dexElements\' attempting \'pathElements\'", ((Throwable)v0));
                MultiDex.expandFieldArray(v3, "pathElements", v4);
            }
        }

        private Object[] makeDexElements(List arg8) throws IOException, SecurityException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
            Object[] v0 = new Object[arg8.size()];
            int v2;
            for(v2 = 0; v2 < v0.length; ++v2) {
                Object v3 = arg8.get(v2);
                v0[v2] = this.elementConstructor.newInstance(((File)v3), DexFile.loadDex(((File)v3).getPath(), V14.optimizedPathFor(((File)v3)), 0));
            }

            return v0;
        }

        private static String optimizedPathFor(File arg4) {
            File v0 = arg4.getParentFile();
            String v4 = arg4.getName();
            StringBuilder v1 = new StringBuilder();
            v1.append(v4.substring(0, v4.length() - V14.EXTRACTED_SUFFIX_LENGTH));
            v1.append(".dex");
            return new File(v0, v1.toString()).getPath();
        }
    }

    final class V19 {
        private V19() {
            super();
        }

        static void install(ClassLoader arg5, List arg6, File arg7) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException, IOException {
            Object[] v7_1;
            Object v5 = MultiDex.findField(arg5, "pathList").get(arg5);
            ArrayList v0 = new ArrayList();
            MultiDex.expandFieldArray(v5, "dexElements", V19.makeDexElements(v5, new ArrayList(((Collection)arg6)), arg7, v0));
            if(v0.size() > 0) {
                Iterator v6 = v0.iterator();
                while(v6.hasNext()) {
                    Log.w("MultiDex", "Exception in makeDexElement", v6.next());
                }

                Field v6_1 = MultiDex.findField(v5, "dexElementsSuppressedExceptions");
                Object v7 = v6_1.get(v5);
                if(v7 == null) {
                    v7_1 = v0.toArray(new IOException[v0.size()]);
                }
                else {
                    IOException[] v2 = new IOException[v0.size() + v7.length];
                    v0.toArray(((Object[])v2));
                    System.arraycopy(v7, 0, v2, v0.size(), v7.length);
                    IOException[] v7_2 = v2;
                }

                v6_1.set(v5, v7_1);
                IOException v5_1 = new IOException("I/O exception during makeDexElement");
                v5_1.initCause(v0.get(0));
                throw v5_1;
            }
        }

        private static Object[] makeDexElements(Object arg7, ArrayList arg8, File arg9, ArrayList arg10) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
            return MultiDex.findMethod(arg7, "makeDexElements", new Class[]{ArrayList.class, File.class, ArrayList.class}).invoke(arg7, arg8, arg9, arg10);
        }
    }

    final class V4 {
        private V4() {
            super();
        }

        static void install(ClassLoader arg10, List arg11) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, IOException {
            int v0 = arg11.size();
            Field v1 = MultiDex.findField(arg10, "path");
            StringBuilder v2 = new StringBuilder(v1.get(arg10));
            String[] v3 = new String[v0];
            File[] v4 = new File[v0];
            ZipFile[] v5 = new ZipFile[v0];
            DexFile[] v0_1 = new DexFile[v0];
            ListIterator v11 = arg11.listIterator();
            while(v11.hasNext()) {
                Object v6 = v11.next();
                String v7 = ((File)v6).getAbsolutePath();
                v2.append(':');
                v2.append(v7);
                int v8 = v11.previousIndex();
                v3[v8] = v7;
                v4[v8] = ((File)v6);
                v5[v8] = new ZipFile(((File)v6));
                StringBuilder v6_1 = new StringBuilder();
                v6_1.append(v7);
                v6_1.append(".dex");
                v0_1[v8] = DexFile.loadDex(v7, v6_1.toString(), 0);
            }

            v1.set(arg10, v2.toString());
            MultiDex.expandFieldArray(arg10, "mPaths", ((Object[])v3));
            MultiDex.expandFieldArray(arg10, "mFiles", ((Object[])v4));
            MultiDex.expandFieldArray(arg10, "mZips", ((Object[])v5));
            MultiDex.expandFieldArray(arg10, "mDexs", ((Object[])v0_1));
        }
    }

    private static final String CODE_CACHE_NAME = "code_cache";
    private static final String CODE_CACHE_SECONDARY_FOLDER_NAME = "secondary-dexes";
    private static final boolean IS_VM_MULTIDEX_CAPABLE = false;
    private static final int MAX_SUPPORTED_SDK_VERSION = 20;
    private static final int MIN_SDK_VERSION = 4;
    private static final String NO_KEY_PREFIX = "";
    private static final String OLD_SECONDARY_FOLDER_NAME = "secondary-dexes";
    static final String TAG = "MultiDex";
    private static final int VM_WITH_MULTIDEX_VERSION_MAJOR = 2;
    private static final int VM_WITH_MULTIDEX_VERSION_MINOR = 1;
    private static final Set installedApk;

    static {
        MultiDex.installedApk = new HashSet();
        MultiDex.IS_VM_MULTIDEX_CAPABLE = MultiDex.isVMMultidexCapable(System.getProperty("java.vm.version"));
    }

    private MultiDex() {
        super();
    }

    static Field access$000(Object arg0, String arg1) throws NoSuchFieldException {
        return MultiDex.findField(arg0, arg1);
    }

    static void access$100(Object arg0, String arg1, Object[] arg2) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        MultiDex.expandFieldArray(arg0, arg1, arg2);
    }

    static Method access$200(Object arg0, String arg1, Class[] arg2) throws NoSuchMethodException {
        return MultiDex.findMethod(arg0, arg1, arg2);
    }

    private static void clearOldDexDir(Context arg8) throws Exception {
        File v0 = new File(arg8.getFilesDir(), "secondary-dexes");
        if(v0.isDirectory()) {
            Log.i("MultiDex", "Clearing old secondary dex dir (" + v0.getPath() + ").");
            File[] v8 = v0.listFiles();
            if(v8 == null) {
                Log.w("MultiDex", "Failed to list secondary dex dir content (" + v0.getPath() + ").");
                return;
            }
            else {
                int v1_1 = v8.length;
                int v2;
                for(v2 = 0; v2 < v1_1; ++v2) {
                    File v3 = v8[v2];
                    Log.i("MultiDex", "Trying to delete old file " + v3.getPath() + " of size " + v3.length());
                    if(!v3.delete()) {
                        Log.w("MultiDex", "Failed to delete old file " + v3.getPath());
                    }
                    else {
                        Log.i("MultiDex", "Deleted old file " + v3.getPath());
                    }
                }

                if(!v0.delete()) {
                    Log.w("MultiDex", "Failed to delete secondary dex dir " + v0.getPath());
                    return;
                }

                Log.i("MultiDex", "Deleted old secondary dex dir " + v0.getPath());
            }
        }
    }

    private static void doInstallation(Context arg5, File arg6, File arg7, String arg8, String arg9, boolean arg10) throws IOException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, InstantiationException {
        IOException v6;
        MultiDexExtractor v8;
        ClassLoader v1;
        Set v0 = MultiDex.installedApk;
        __monitor_enter(v0);
        try {
            if(MultiDex.installedApk.contains(arg6)) {
                __monitor_exit(v0);
                return;
            }

            MultiDex.installedApk.add(arg6);
            int v2 = 20;
            if(Build$VERSION.SDK_INT > v2) {
                Log.w("MultiDex", "MultiDex is not guaranteed to work in SDK version " + Build$VERSION.SDK_INT + ": SDK version higher than " + v2 + " should be backed by " + "runtime with built-in multidex capabilty but it\'s not the " + "case here: java.vm.version=\"" + System.getProperty("java.vm.version") + "\"");
            }

            try {
                v1 = arg5.getClassLoader();
                if(v1 != null) {
                    goto label_42;
                }

                goto label_37;
            }
            catch(RuntimeException v5_1) {
                try {
                    Log.w("MultiDex", "Failure while trying to obtain Context class loader. Must be running in test mode. Skip patching.", ((Throwable)v5_1));
                    __monitor_exit(v0);
                    return;
                label_37:
                    Log.e("MultiDex", "Context class loader is null. Must be running in test mode. Skip patching.");
                    __monitor_exit(v0);
                    return;
                    try {
                    label_42:
                        MultiDex.clearOldDexDir(arg5);
                        goto label_48;
                    }
                    catch(Throwable v2_1) {
                        try {
                            Log.w("MultiDex", "Something went wrong when trying to clear old MultiDex extraction, continuing without cleaning.", v2_1);
                        label_48:
                            arg7 = MultiDex.getDexDir(arg5, arg7, arg8);
                            v8 = new MultiDexExtractor(arg6, arg7);
                            v6 = null;
                        }
                        catch(Throwable v5) {
                            goto label_82;
                        }
                    }
                }
                catch(Throwable v5) {
                    goto label_82;
                }
            }
        }
        catch(Throwable v5) {
            goto label_82;
        }

        try {
            List v2_2 = v8.load(arg5, arg9, false);
            try {
                MultiDex.installSecondaryDexes(v1, arg7, v2_2);
            }
            catch(IOException v2_3) {
                if(arg10) {
                    goto label_59;
                }

                try {
                    throw v2_3;
                label_59:
                    Log.w("MultiDex", "Failed to install extracted secondary dex files, retrying with forced extraction", ((Throwable)v2_3));
                    MultiDex.installSecondaryDexes(v1, arg7, v8.load(arg5, arg9, true));
                }
                catch(Throwable v5) {
                    try {
                    label_73:
                        v8.close();
                        goto label_74;
                    }
                    catch(IOException ) {
                    label_74:
                        throw v5;
                    }
                }
            }
        }
        catch(Throwable v5) {
            goto label_73;
        }

        try {
            v8.close();
            goto label_68;
        }
        catch(Throwable v5) {
        label_83:
            throw v5;
        }
        catch(IOException v6) {
        label_68:
            if(v6 == null) {
                goto label_70;
            }

            try {
                throw v6;
            label_70:
                __monitor_exit(v0);
                return;
            label_82:
                __monitor_exit(v0);
                goto label_83;
            }
            catch(Throwable v5) {
                goto label_82;
            }
        }
    }

    private static void expandFieldArray(Object arg4, String arg5, Object[] arg6) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Field v5 = MultiDex.findField(arg4, arg5);
        Object v0 = v5.get(arg4);
        Object v1 = Array.newInstance(v0.getClass().getComponentType(), v0.length + arg6.length);
        System.arraycopy(v0, 0, v1, 0, v0.length);
        System.arraycopy(arg6, 0, v1, v0.length, arg6.length);
        v5.set(arg4, v1);
    }

    private static Field findField(Object arg3, String arg4) throws NoSuchFieldException {
        Field v1;
        Class v0 = arg3.getClass();
        while(true) {
            if(v0 == null) {
                goto label_10;
            }

            try {
                v1 = v0.getDeclaredField(arg4);
                if(!v1.isAccessible()) {
                    v1.setAccessible(true);
                }

                return v1;
            }
            catch(NoSuchFieldException ) {
                v0 = v0.getSuperclass();
                continue;
            }
        }

        return v1;
    label_10:
        StringBuilder v1_1 = new StringBuilder();
        v1_1.append("Field ");
        v1_1.append(arg4);
        v1_1.append(" not found in ");
        v1_1.append(arg3.getClass());
        throw new NoSuchFieldException(v1_1.toString());
    }

    private static Method findMethod(Object arg3, String arg4, Class[] arg5) throws NoSuchMethodException {
        Method v1;
        Class v0 = arg3.getClass();
        while(true) {
            if(v0 == null) {
                goto label_10;
            }

            try {
                v1 = v0.getDeclaredMethod(arg4, arg5);
                if(!v1.isAccessible()) {
                    v1.setAccessible(true);
                }

                return v1;
            }
            catch(NoSuchMethodException ) {
                v0 = v0.getSuperclass();
                continue;
            }
        }

        return v1;
    label_10:
        StringBuilder v1_1 = new StringBuilder();
        v1_1.append("Method ");
        v1_1.append(arg4);
        v1_1.append(" with parameters ");
        v1_1.append(Arrays.asList(((Object[])arg5)));
        v1_1.append(" not found in ");
        v1_1.append(arg3.getClass());
        throw new NoSuchMethodException(v1_1.toString());
    }

    private static ApplicationInfo getApplicationInfo(Context arg2) {
        try {
            return arg2.getApplicationInfo();
        }
        catch(RuntimeException v2) {
            Log.w("MultiDex", "Failure while trying to obtain ApplicationInfo from Context. Must be running in test mode. Skip patching.", ((Throwable)v2));
            return null;
        }
    }

    private static File getDexDir(Context arg2, File arg3, String arg4) throws IOException {
        File v0 = new File(arg3, "code_cache");
        try {
            MultiDex.mkdirChecked(v0);
        }
        catch(IOException ) {
            v0 = new File(arg2.getFilesDir(), "code_cache");
            MultiDex.mkdirChecked(v0);
        }

        File v2 = new File(v0, arg4);
        MultiDex.mkdirChecked(v2);
        return v2;
    }

    public static void install(Context arg6) {
        Log.i("MultiDex", "Installing application");
        if(MultiDex.IS_VM_MULTIDEX_CAPABLE) {
            Log.i("MultiDex", "VM has multidex support, MultiDex support library is disabled.");
            return;
        }

        int v1 = 4;
        if(Build$VERSION.SDK_INT < v1) {
            StringBuilder v0 = new StringBuilder();
            v0.append("MultiDex installation failed. SDK ");
            v0.append(Build$VERSION.SDK_INT);
            v0.append(" is unsupported. Min SDK version is ");
            v0.append(v1);
            v0.append(".");
            throw new RuntimeException(v0.toString());
        }

        try {
            ApplicationInfo v0_1 = MultiDex.getApplicationInfo(arg6);
            if(v0_1 == null) {
                Log.i("MultiDex", "No ApplicationInfo available, i.e. running on a test Context: MultiDex support library is disabled.");
                return;
            }

            MultiDex.doInstallation(arg6, new File(v0_1.sourceDir), new File(v0_1.dataDir), "secondary-dexes", "", true);
        }
        catch(Exception v6) {
            Log.e("MultiDex", "MultiDex installation failure", ((Throwable)v6));
            StringBuilder v1_1 = new StringBuilder();
            v1_1.append("MultiDex installation failed (");
            v1_1.append(v6.getMessage());
            v1_1.append(").");
            throw new RuntimeException(v1_1.toString());
        }

        Log.i("MultiDex", "install done");
    }

    public static void installInstrumentation(Context arg14, Context arg15) {
        Log.i("MultiDex", "Installing instrumentation");
        if(MultiDex.IS_VM_MULTIDEX_CAPABLE) {
            Log.i("MultiDex", "VM has multidex support, MultiDex support library is disabled.");
            return;
        }

        int v1 = 4;
        if(Build$VERSION.SDK_INT < v1) {
            StringBuilder v15 = new StringBuilder();
            v15.append("MultiDex installation failed. SDK ");
            v15.append(Build$VERSION.SDK_INT);
            v15.append(" is unsupported. Min SDK version is ");
            v15.append(v1);
            v15.append(".");
            throw new RuntimeException(v15.toString());
        }

        try {
            ApplicationInfo v0 = MultiDex.getApplicationInfo(arg14);
            if(v0 == null) {
                Log.i("MultiDex", "No ApplicationInfo available for instrumentation, i.e. running on a test Context: MultiDex support library is disabled.");
                return;
            }

            ApplicationInfo v1_1 = MultiDex.getApplicationInfo(arg15);
            if(v1_1 == null) {
                Log.i("MultiDex", "No ApplicationInfo available, i.e. running on a test Context: MultiDex support library is disabled.");
                return;
            }

            String v7 = arg14.getPackageName() + ".";
            File v10 = new File(v1_1.dataDir);
            File v4 = new File(v0.sourceDir);
            MultiDex.doInstallation(arg15, v4, v10, v7 + "secondary-dexes", v7, false);
            MultiDex.doInstallation(arg15, new File(v1_1.sourceDir), v10, "secondary-dexes", "", false);
        }
        catch(Exception v14) {
            Log.e("MultiDex", "MultiDex installation failure", ((Throwable)v14));
            StringBuilder v0_1 = new StringBuilder();
            v0_1.append("MultiDex installation failed (");
            v0_1.append(v14.getMessage());
            v0_1.append(").");
            throw new RuntimeException(v0_1.toString());
        }

        Log.i("MultiDex", "Installation done");
    }

    private static void installSecondaryDexes(ClassLoader arg2, File arg3, List arg4) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException, IOException, SecurityException, ClassNotFoundException, InstantiationException {
        if(!arg4.isEmpty()) {
            if(Build$VERSION.SDK_INT >= 19) {
                V19.install(arg2, arg4, arg3);
            }
            else if(Build$VERSION.SDK_INT >= 14) {
                V14.install(arg2, arg4);
            }
            else {
                V4.install(arg2, arg4);
            }
        }
    }

    static boolean isVMMultidexCapable(String arg5) {
        boolean v0 = false;
        if(arg5 != null) {
            Matcher v1 = Pattern.compile("(\\d+)\\.(\\d+)(\\.\\d+)?").matcher(((CharSequence)arg5));
            if(v1.matches()) {
                try {
                    int v3 = Integer.parseInt(v1.group(1));
                    int v4 = 2;
                    int v1_1 = Integer.parseInt(v1.group(v4));
                    if(v3 <= v4) {
                        goto label_14;
                    }

                    goto label_16;
                }
                catch(NumberFormatException ) {
                    goto label_17;
                }

            label_14:
                if(v3 != v4) {
                }
                else if(v1_1 >= 1) {
                    goto label_16;
                }

                goto label_17;
            label_16:
                v0 = true;
            }
        }

    label_17:
        String v1_2 = "MultiDex";
        StringBuilder v2 = new StringBuilder();
        v2.append("VM with version ");
        v2.append(arg5);
        arg5 = v0 ? " has multidex support" : " does not have multidex support";
        v2.append(arg5);
        Log.i(v1_2, v2.toString());
        return v0;
    }

    private static void mkdirChecked(File arg4) throws IOException {
        arg4.mkdir();
        if(!arg4.isDirectory()) {
            File v0 = arg4.getParentFile();
            if(v0 == null) {
                Log.e("MultiDex", "Failed to create dir " + arg4.getPath() + ". Parent file is null.");
            }
            else {
                Log.e("MultiDex", "Failed to create dir " + arg4.getPath() + ". parent file is a dir " + v0.isDirectory() + ", a file " + v0.isFile() + ", exists " + v0.exists() + ", readable " + v0.canRead() + ", writable " + v0.canWrite());
            }

            v1 = new StringBuilder();
            v1.append("Failed to create directory ");
            v1.append(arg4.getPath());
            throw new IOException(v1.toString());
        }
    }
}

