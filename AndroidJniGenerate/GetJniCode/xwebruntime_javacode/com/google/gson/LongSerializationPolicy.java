package com.google.gson;

public abstract enum LongSerializationPolicy {
    enum com.google.gson.LongSerializationPolicy$1 extends LongSerializationPolicy {
        com.google.gson.LongSerializationPolicy$1(String arg2, int arg3) {
            super(arg2, arg3, null);
        }

        public JsonElement serialize(Long arg2) {
            return new JsonPrimitive(((Number)arg2));
        }
    }

    enum com.google.gson.LongSerializationPolicy$2 extends LongSerializationPolicy {
        com.google.gson.LongSerializationPolicy$2(String arg2, int arg3) {
            super(arg2, arg3, null);
        }

        public JsonElement serialize(Long arg2) {
            return new JsonPrimitive(String.valueOf(arg2));
        }
    }

    public static final enum LongSerializationPolicy DEFAULT;
    public static final enum LongSerializationPolicy STRING;

    static {
        LongSerializationPolicy.DEFAULT = new com.google.gson.LongSerializationPolicy$1("DEFAULT", 0);
        LongSerializationPolicy.STRING = new com.google.gson.LongSerializationPolicy$2("STRING", 1);
        LongSerializationPolicy.$VALUES = new LongSerializationPolicy[]{LongSerializationPolicy.DEFAULT, LongSerializationPolicy.STRING};
    }

    private LongSerializationPolicy(String arg1, int arg2) {
        super(arg1, arg2);
    }

    LongSerializationPolicy(String arg1, int arg2, com.google.gson.LongSerializationPolicy$1 arg3) {
        this(arg1, arg2);
    }

    public abstract JsonElement serialize(Long arg1);

    public static LongSerializationPolicy valueOf(String arg1) {
        return Enum.valueOf(LongSerializationPolicy.class, arg1);
    }

    public static LongSerializationPolicy[] values() {
        return LongSerializationPolicy.$VALUES.clone();
    }
}

