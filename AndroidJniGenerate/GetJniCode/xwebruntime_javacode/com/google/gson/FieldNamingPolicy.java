package com.google.gson;

import java.lang.reflect.Field;
import java.util.Locale;

public abstract enum FieldNamingPolicy implements FieldNamingStrategy {
    enum com.google.gson.FieldNamingPolicy$1 extends FieldNamingPolicy {
        com.google.gson.FieldNamingPolicy$1(String arg2, int arg3) {
            super(arg2, arg3, null);
        }

        public String translateName(Field arg1) {
            return arg1.getName();
        }
    }

    enum com.google.gson.FieldNamingPolicy$2 extends FieldNamingPolicy {
        com.google.gson.FieldNamingPolicy$2(String arg2, int arg3) {
            super(arg2, arg3, null);
        }

        public String translateName(Field arg1) {
            return com.google.gson.FieldNamingPolicy$2.upperCaseFirstLetter(arg1.getName());
        }
    }

    enum com.google.gson.FieldNamingPolicy$3 extends FieldNamingPolicy {
        com.google.gson.FieldNamingPolicy$3(String arg2, int arg3) {
            super(arg2, arg3, null);
        }

        public String translateName(Field arg2) {
            return com.google.gson.FieldNamingPolicy$3.upperCaseFirstLetter(com.google.gson.FieldNamingPolicy$3.separateCamelCase(arg2.getName(), " "));
        }
    }

    enum com.google.gson.FieldNamingPolicy$4 extends FieldNamingPolicy {
        com.google.gson.FieldNamingPolicy$4(String arg2, int arg3) {
            super(arg2, arg3, null);
        }

        public String translateName(Field arg2) {
            return com.google.gson.FieldNamingPolicy$4.separateCamelCase(arg2.getName(), "_").toLowerCase(Locale.ENGLISH);
        }
    }

    enum com.google.gson.FieldNamingPolicy$5 extends FieldNamingPolicy {
        com.google.gson.FieldNamingPolicy$5(String arg2, int arg3) {
            super(arg2, arg3, null);
        }

        public String translateName(Field arg2) {
            return com.google.gson.FieldNamingPolicy$5.separateCamelCase(arg2.getName(), "-").toLowerCase(Locale.ENGLISH);
        }
    }

    enum com.google.gson.FieldNamingPolicy$6 extends FieldNamingPolicy {
        com.google.gson.FieldNamingPolicy$6(String arg2, int arg3) {
            super(arg2, arg3, null);
        }

        public String translateName(Field arg2) {
            return com.google.gson.FieldNamingPolicy$6.separateCamelCase(arg2.getName(), ".").toLowerCase(Locale.ENGLISH);
        }
    }

    public static final enum FieldNamingPolicy IDENTITY;
    public static final enum FieldNamingPolicy LOWER_CASE_WITH_DASHES;
    public static final enum FieldNamingPolicy LOWER_CASE_WITH_DOTS;
    public static final enum FieldNamingPolicy LOWER_CASE_WITH_UNDERSCORES;
    public static final enum FieldNamingPolicy UPPER_CAMEL_CASE;
    public static final enum FieldNamingPolicy UPPER_CAMEL_CASE_WITH_SPACES;

    static {
        FieldNamingPolicy.IDENTITY = new com.google.gson.FieldNamingPolicy$1("IDENTITY", 0);
        FieldNamingPolicy.UPPER_CAMEL_CASE = new com.google.gson.FieldNamingPolicy$2("UPPER_CAMEL_CASE", 1);
        FieldNamingPolicy.UPPER_CAMEL_CASE_WITH_SPACES = new com.google.gson.FieldNamingPolicy$3("UPPER_CAMEL_CASE_WITH_SPACES", 2);
        FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES = new com.google.gson.FieldNamingPolicy$4("LOWER_CASE_WITH_UNDERSCORES", 3);
        FieldNamingPolicy.LOWER_CASE_WITH_DASHES = new com.google.gson.FieldNamingPolicy$5("LOWER_CASE_WITH_DASHES", 4);
        FieldNamingPolicy.LOWER_CASE_WITH_DOTS = new com.google.gson.FieldNamingPolicy$6("LOWER_CASE_WITH_DOTS", 5);
        FieldNamingPolicy.$VALUES = new FieldNamingPolicy[]{FieldNamingPolicy.IDENTITY, FieldNamingPolicy.UPPER_CAMEL_CASE, FieldNamingPolicy.UPPER_CAMEL_CASE_WITH_SPACES, FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES, FieldNamingPolicy.LOWER_CASE_WITH_DASHES, FieldNamingPolicy.LOWER_CASE_WITH_DOTS};
    }

    private FieldNamingPolicy(String arg1, int arg2) {
        super(arg1, arg2);
    }

    FieldNamingPolicy(String arg1, int arg2, com.google.gson.FieldNamingPolicy$1 arg3) {
        this(arg1, arg2);
    }

    private static String modifyString(char arg1, String arg2, int arg3) {
        String v1 = arg3 < arg2.length() ? arg1 + arg2.substring(arg3) : String.valueOf(arg1);
        return v1;
    }

    static String separateCamelCase(String arg5, String arg6) {
        StringBuilder v0 = new StringBuilder();
        int v1 = arg5.length();
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            char v3 = arg5.charAt(v2);
            if((Character.isUpperCase(v3)) && v0.length() != 0) {
                v0.append(arg6);
            }

            v0.append(v3);
        }

        return v0.toString();
    }

    static String upperCaseFirstLetter(String arg5) {
        StringBuilder v0 = new StringBuilder();
        int v1 = 0;
        char v2 = arg5.charAt(0);
        int v3 = arg5.length();
        while(v1 < v3 - 1) {
            if(Character.isLetter(v2)) {
            }
            else {
                v0.append(v2);
                ++v1;
                v2 = arg5.charAt(v1);
                continue;
            }

            break;
        }

        if(!Character.isUpperCase(v2)) {
            v0.append(FieldNamingPolicy.modifyString(Character.toUpperCase(v2), arg5, v1 + 1));
            return v0.toString();
        }

        return arg5;
    }

    public static FieldNamingPolicy valueOf(String arg1) {
        return Enum.valueOf(FieldNamingPolicy.class, arg1);
    }

    public static FieldNamingPolicy[] values() {
        return FieldNamingPolicy.$VALUES.clone();
    }
}

