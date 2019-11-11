package org.chromium.base;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;
import org.chromium.base.annotations.MainDex;

@MainDex public abstract class CommandLine {
    class org.chromium.base.CommandLine$1 {
    }

    class JavaCommandLine extends CommandLine {
        private ArrayList mArgs;
        private int mArgsBegin;
        private HashMap mSwitches;

        static {
        }

        JavaCommandLine(@Nullable String[] arg4) {
            super(null);
            this.mSwitches = new HashMap();
            this.mArgs = new ArrayList();
            this.mArgsBegin = 1;
            if(arg4 == null || arg4.length == 0 || arg4[0] == null) {
                this.mArgs.add("");
            }
            else {
                this.mArgs.add(arg4[0]);
                this.appendSwitchesInternal(arg4, 1);
            }
        }

        public void appendSwitch(String arg2) {
            this.appendSwitchWithValue(arg2, null);
        }

        public void appendSwitchWithValue(String arg3, String arg4) {
            HashMap v0 = this.mSwitches;
            String v1 = arg4 == null ? "" : arg4;
            v0.put(arg3, v1);
            arg3 = "--" + arg3;
            if(arg4 != null && !arg4.isEmpty()) {
                arg3 = arg3 + "=" + arg4;
            }

            ArrayList v4 = this.mArgs;
            int v0_2 = this.mArgsBegin;
            this.mArgsBegin = v0_2 + 1;
            v4.add(v0_2, arg3);
        }

        public void appendSwitchesAndArguments(String[] arg2) {
            this.appendSwitchesInternal(arg2, 0);
        }

        private void appendSwitchesInternal(String[] arg9, int arg10) {
            int v0 = arg9.length;
            int v3 = arg10;
            arg10 = 0;
            int v4 = 1;
            while(arg10 < v0) {
                String v5 = arg9[arg10];
                if(v3 > 0) {
                    --v3;
                }
                else {
                    if(v5.equals("--")) {
                        v4 = 0;
                    }

                    if(v4 != 0 && (v5.startsWith("--"))) {
                        String[] v5_1 = v5.split("=", 2);
                        String v6 = v5_1.length > 1 ? v5_1[1] : null;
                        this.appendSwitchWithValue(v5_1[0].substring("--".length()), v6);
                        goto label_35;
                    }

                    this.mArgs.add(v5);
                }

            label_35:
                ++arg10;
            }
        }

        protected String[] getCommandLineArguments() {
            return this.mArgs.toArray(new String[this.mArgs.size()]);
        }

        public String getSwitchValue(String arg2) {
            Object v2 = this.mSwitches.get(arg2);
            if(v2 == null || (((String)v2).isEmpty())) {
                arg2 = null;
            }

            return arg2;
        }

        public boolean hasSwitch(String arg2) {
            return this.mSwitches.containsKey(arg2);
        }
    }

    class NativeCommandLine extends CommandLine {
        static {
        }

        public NativeCommandLine(@Nullable String[] arg2) {
            super(null);
            CommandLine.nativeInit(arg2);
        }

        public void appendSwitch(String arg1) {
            CommandLine.nativeAppendSwitch(arg1);
        }

        public void appendSwitchWithValue(String arg1, String arg2) {
            CommandLine.nativeAppendSwitchWithValue(arg1, arg2);
        }

        public void appendSwitchesAndArguments(String[] arg1) {
            CommandLine.nativeAppendSwitchesAndArguments(arg1);
        }

        protected void destroy() {
            throw new IllegalStateException("Can\'t destroy native command line after startup");
        }

        protected String[] getCommandLineArguments() {
            return null;
        }

        public String getSwitchValue(String arg1) {
            return CommandLine.nativeGetSwitchValue(arg1);
        }

        public boolean hasSwitch(String arg1) {
            return CommandLine.nativeHasSwitch(arg1);
        }

        public boolean isNativeImplementation() {
            return 1;
        }
    }

    private static final String SWITCH_PREFIX = "--";
    private static final String SWITCH_TERMINATOR = "--";
    private static final String SWITCH_VALUE_SEPARATOR = "=";
    private static final String TAG = "CommandLine";
    private static final AtomicReference sCommandLine;

    static {
        CommandLine.sCommandLine = new AtomicReference();
    }

    private CommandLine() {
        super();
    }

    CommandLine(org.chromium.base.CommandLine$1 arg1) {
        this();
    }

    static void access$100(String[] arg0) {
        CommandLine.nativeInit(arg0);
    }

    static boolean access$200(String arg0) {
        return CommandLine.nativeHasSwitch(arg0);
    }

    static String access$300(String arg0) {
        return CommandLine.nativeGetSwitchValue(arg0);
    }

    static void access$400(String arg0) {
        CommandLine.nativeAppendSwitch(arg0);
    }

    static void access$500(String arg0, String arg1) {
        CommandLine.nativeAppendSwitchWithValue(arg0, arg1);
    }

    static void access$600(String[] arg0) {
        CommandLine.nativeAppendSwitchesAndArguments(arg0);
    }

    @VisibleForTesting public abstract void appendSwitch(String arg1);

    public abstract void appendSwitchWithValue(String arg1, String arg2);

    public abstract void appendSwitchesAndArguments(String[] arg1);

    protected void destroy() {
    }

    public static void enableNativeProxy() {
        CommandLine.sCommandLine.set(new NativeCommandLine(CommandLine.getJavaSwitchesOrNull()));
    }

    protected abstract String[] getCommandLineArguments();

    @VisibleForTesting public static CommandLine getInstance() {
        return CommandLine.sCommandLine.get();
    }

    @Nullable public static String[] getJavaSwitchesOrNull() {
        Object v0 = CommandLine.sCommandLine.get();
        if(v0 != null) {
            return ((CommandLine)v0).getCommandLineArguments();
        }

        return null;
    }

    public abstract String getSwitchValue(String arg1);

    public String getSwitchValue(String arg2, String arg3) {
        arg2 = this.getSwitchValue(arg2);
        if(TextUtils.isEmpty(((CharSequence)arg2))) {
            arg2 = arg3;
        }

        return arg2;
    }

    @VisibleForTesting public abstract boolean hasSwitch(String arg1);

    public static void init(@Nullable String[] arg1) {
        CommandLine.setInstance(new JavaCommandLine(arg1));
    }

    public static void initFromFile(String arg0) {
        char[] v0 = CommandLine.readFileAsUtf8(arg0);
        String[] v0_1 = v0 == null ? null : CommandLine.tokenizeQuotedArguments(v0);
        CommandLine.init(v0_1);
    }

    public static boolean isInitialized() {
        boolean v0 = CommandLine.sCommandLine.get() != null ? true : false;
        return v0;
    }

    public boolean isNativeImplementation() {
        return 0;
    }

    private static native void nativeAppendSwitch(String arg0) {
    }

    private static native void nativeAppendSwitchWithValue(String arg0, String arg1) {
    }

    private static native void nativeAppendSwitchesAndArguments(String[] arg0) {
    }

    private static native String nativeGetSwitchValue(String arg0) {
    }

    private static native boolean nativeHasSwitch(String arg0) {
    }

    private static native void nativeInit(String[] arg0) {
    }

    private static char[] readFileAsUtf8(String arg5) {
        char[] v0_2;
        FileReader v1;
        File v0 = new File(arg5);
        char[] v5 = null;
        try {
            v1 = new FileReader(v0);
        }
        catch(IOException ) {
            return v5;
        }

        try {
            v0_2 = new char[((int)v0.length())];
            v0_2 = Arrays.copyOfRange(v0_2, 0, v1.read(v0_2));
            if(v1 == null) {
                return v0_2;
            }

            goto label_12;
        }
        catch(Throwable v0_1) {
            v2 = ((Throwable)v5);
        }
        catch(Throwable v0_1) {
            try {
                throw v0_1;
            }
            catch(Throwable v2) {
                Throwable v4 = v2;
                v2 = v0_1;
                v0_1 = v4;
            }
        }

        if(v1 == null) {
            goto label_31;
        }
        else if(v2 != null) {
            try {
                v1.close();
                goto label_31;
            }
            catch(IOException ) {
            }
            catch(Throwable v1_1) {
                try {
                    ThrowableExtension.addSuppressed(v2, v1_1);
                    goto label_31;
                label_30:
                    v1.close();
                label_31:
                    throw v0_1;
                label_12:
                    v1.close();
                }
                catch(IOException ) {
                    return v5;
                }
            }
        }
        else {
            goto label_30;
        }

        return v0_2;
    }

    @VisibleForTesting public static void reset() {
        CommandLine.setInstance(null);
    }

    private static void setInstance(CommandLine arg1) {
        Object v1 = CommandLine.sCommandLine.getAndSet(arg1);
        if(v1 != null) {
            ((CommandLine)v1).destroy();
        }
    }

    @VisibleForTesting public static String[] tokenizeQuotedArguments(char[] arg10) {
        if(arg10.length > 0x10000) {
            StringBuilder v1 = new StringBuilder();
            v1.append("Flags file too big: ");
            v1.append(arg10.length);
            throw new RuntimeException(v1.toString());
        }

        ArrayList v0 = new ArrayList();
        int v1_1 = arg10.length;
        StringBuilder v2 = null;
        StringBuilder v6 = v2;
        int v4 = 0;
        int v5 = 0;
        while(v4 < v1_1) {
            char v7 = arg10[v4];
            if(v5 != 0 || v7 != 34) {
                if(v7 == v5) {
                label_29:
                    if(v6 != null && v6.length() > 0 && v6.charAt(v6.length() - 1) == 92) {
                        v6.setCharAt(v6.length() - 1, v7);
                        goto label_58;
                    }

                    if(v5 == 0) {
                    }
                    else {
                        int v7_1 = 0;
                    }

                    v5 = v7;
                }
                else {
                    if(v5 == 0 && (Character.isWhitespace(v7))) {
                        if(v6 != null) {
                            v0.add(v6.toString());
                            v6 = v2;
                        }
                        else {
                        }

                        goto label_58;
                    }

                    if(v6 == null) {
                        v6 = new StringBuilder();
                    }

                    v6.append(v7);
                }
            }
            else if(v7 == 39) {
                goto label_29;
            }
            else {
                goto label_29;
            }

        label_58:
            ++v4;
        }

        if(v6 != null) {
            if(v5 != 0) {
                Log.w("CommandLine", "Unterminated quoted string: " + v6);
            }

            v0.add(v6.toString());
        }

        return v0.toArray(new String[v0.size()]);
    }
}

