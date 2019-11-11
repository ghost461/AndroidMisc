package com.google.gson.internal;

public final class JavaVersion {
    private static final int majorJavaVersion;

    static {
        JavaVersion.majorJavaVersion = JavaVersion.determineMajorJavaVersion();
    }

    private JavaVersion() {
        super();
    }

    private static int determineMajorJavaVersion() {
        return JavaVersion.getMajorJavaVersion(System.getProperty("java.version"));
    }

    private static int extractBeginningInt(String arg4) {
        try {
            StringBuilder v0 = new StringBuilder();
            int v1;
            for(v1 = 0; v1 < arg4.length(); ++v1) {
                char v2 = arg4.charAt(v1);
                if(!Character.isDigit(v2)) {
                    break;
                }

                v0.append(v2);
            }

            return Integer.parseInt(v0.toString());
        }
        catch(NumberFormatException ) {
            return -1;
        }
    }

    static int getMajorJavaVersion(String arg2) {
        int v0 = JavaVersion.parseDotted(arg2);
        int v1 = -1;
        if(v0 == v1) {
            v0 = JavaVersion.extractBeginningInt(arg2);
        }

        if(v0 == v1) {
            return 6;
        }

        return v0;
    }

    public static int getMajorJavaVersion() {
        return JavaVersion.majorJavaVersion;
    }

    public static boolean isJava9OrLater() {
        boolean v0 = JavaVersion.majorJavaVersion >= 9 ? true : false;
        return v0;
    }

    private static int parseDotted(String arg3) {
        int v0;
        try {
            String[] v3 = arg3.split("[._]");
            v0 = Integer.parseInt(v3[0]);
            if(v0 == 1 && v3.length > 1) {
                return Integer.parseInt(v3[1]);
            }
        }
        catch(NumberFormatException ) {
            return -1;
        }

        return v0;
    }
}

