package org.chromium.base.library_loader;

public class NativeLibraries {
    public static final int CPU_FAMILY_ARM = 1;
    public static final int CPU_FAMILY_MIPS = 2;
    public static final int CPU_FAMILY_UNKNOWN = 0;
    public static final int CPU_FAMILY_X86 = 3;
    public static final String[] LIBRARIES = null;
    public static int sCpuFamily = 0;
    public static boolean sEnableLinkerTests = false;
    public static boolean sUseLibraryInZipFile = false;
    public static boolean sUseLinker = false;
    static String sVersionNumber = "";

    static {
        NativeLibraries.LIBRARIES = new String[0];
    }

    public NativeLibraries() {
        super();
    }
}

