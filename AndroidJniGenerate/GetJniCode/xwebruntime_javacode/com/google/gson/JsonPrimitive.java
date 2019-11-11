package com.google.gson;

import com.google.gson.internal.$Gson$Preconditions;
import com.google.gson.internal.LazilyParsedNumber;
import java.math.BigDecimal;
import java.math.BigInteger;

public final class JsonPrimitive extends JsonElement {
    private static final Class[] PRIMITIVE_TYPES;
    private Object value;

    static {
        JsonPrimitive.PRIMITIVE_TYPES = new Class[]{Integer.TYPE, Long.TYPE, Short.TYPE, Float.TYPE, Double.TYPE, Byte.TYPE, Boolean.TYPE, Character.TYPE, Integer.class, Long.class, Short.class, Float.class, Double.class, Byte.class, Boolean.class, Character.class};
    }

    public JsonPrimitive(Number arg1) {
        super();
        this.setValue(arg1);
    }

    public JsonPrimitive(String arg1) {
        super();
        this.setValue(arg1);
    }

    public JsonPrimitive(Boolean arg1) {
        super();
        this.setValue(arg1);
    }

    public JsonPrimitive(Character arg1) {
        super();
        this.setValue(arg1);
    }

    JsonPrimitive(Object arg1) {
        super();
        this.setValue(arg1);
    }

    public JsonElement deepCopy() {
        return this.deepCopy();
    }

    public JsonPrimitive deepCopy() {
        return this;
    }

    public boolean equals(Object arg7) {
        boolean v0 = true;
        if(this == (((JsonPrimitive)arg7))) {
            return 1;
        }

        if(arg7 != null) {
            if(this.getClass() != arg7.getClass()) {
            }
            else if(this.value == null) {
                if(((JsonPrimitive)arg7).value == null) {
                }
                else {
                    v0 = false;
                }

                return v0;
            }
            else {
                if((JsonPrimitive.isIntegral(this)) && (JsonPrimitive.isIntegral(((JsonPrimitive)arg7)))) {
                    if(this.getAsNumber().longValue() == ((JsonPrimitive)arg7).getAsNumber().longValue()) {
                    }
                    else {
                        v0 = false;
                    }

                    return v0;
                }

                if(((this.value instanceof Number)) && ((((JsonPrimitive)arg7).value instanceof Number))) {
                    double v2 = this.getAsNumber().doubleValue();
                    double v4 = ((JsonPrimitive)arg7).getAsNumber().doubleValue();
                    if(v2 != v4 && (!Double.isNaN(v2) || !Double.isNaN(v4))) {
                        v0 = false;
                    }

                    return v0;
                }

                return this.value.equals(((JsonPrimitive)arg7).value);
            }
        }

        return 0;
    }

    public BigDecimal getAsBigDecimal() {
        Object v0;
        if((this.value instanceof BigDecimal)) {
            v0 = this.value;
        }
        else {
            BigDecimal v0_1 = new BigDecimal(this.value.toString());
        }

        return ((BigDecimal)v0);
    }

    public BigInteger getAsBigInteger() {
        Object v0;
        if((this.value instanceof BigInteger)) {
            v0 = this.value;
        }
        else {
            BigInteger v0_1 = new BigInteger(this.value.toString());
        }

        return ((BigInteger)v0);
    }

    public boolean getAsBoolean() {
        if(this.isBoolean()) {
            return this.getAsBooleanWrapper().booleanValue();
        }

        return Boolean.parseBoolean(this.getAsString());
    }

    Boolean getAsBooleanWrapper() {
        return this.value;
    }

    public byte getAsByte() {
        byte v0 = this.isNumber() ? this.getAsNumber().byteValue() : Byte.parseByte(this.getAsString());
        return v0;
    }

    public char getAsCharacter() {
        return this.getAsString().charAt(0);
    }

    public double getAsDouble() {
        double v0 = this.isNumber() ? this.getAsNumber().doubleValue() : Double.parseDouble(this.getAsString());
        return v0;
    }

    public float getAsFloat() {
        float v0 = this.isNumber() ? this.getAsNumber().floatValue() : Float.parseFloat(this.getAsString());
        return v0;
    }

    public int getAsInt() {
        int v0 = this.isNumber() ? this.getAsNumber().intValue() : Integer.parseInt(this.getAsString());
        return v0;
    }

    public long getAsLong() {
        long v0 = this.isNumber() ? this.getAsNumber().longValue() : Long.parseLong(this.getAsString());
        return v0;
    }

    public Number getAsNumber() {
        Object v0_1;
        if((this.value instanceof String)) {
            LazilyParsedNumber v0 = new LazilyParsedNumber(this.value);
        }
        else {
            v0_1 = this.value;
        }

        return ((Number)v0_1);
    }

    public short getAsShort() {
        short v0 = this.isNumber() ? this.getAsNumber().shortValue() : Short.parseShort(this.getAsString());
        return v0;
    }

    public String getAsString() {
        if(this.isNumber()) {
            return this.getAsNumber().toString();
        }

        if(this.isBoolean()) {
            return this.getAsBooleanWrapper().toString();
        }

        return this.value;
    }

    public int hashCode() {
        long v2;
        if(this.value == null) {
            return 0x1F;
        }

        int v1 = 0x20;
        if(JsonPrimitive.isIntegral(this)) {
            v2 = this.getAsNumber().longValue();
            return ((int)(v2 ^ v2 >>> v1));
        }

        if((this.value instanceof Number)) {
            v2 = Double.doubleToLongBits(this.getAsNumber().doubleValue());
            return ((int)(v2 ^ v2 >>> v1));
        }

        return this.value.hashCode();
    }

    public boolean isBoolean() {
        return this.value instanceof Boolean;
    }

    private static boolean isIntegral(JsonPrimitive arg2) {
        boolean v1 = false;
        if((arg2.value instanceof Number)) {
            Object v2 = arg2.value;
            if(((v2 instanceof BigInteger)) || ((v2 instanceof Long)) || ((v2 instanceof Integer)) || ((v2 instanceof Short)) || ((v2 instanceof Byte))) {
                v1 = true;
            }

            return v1;
        }

        return 0;
    }

    public boolean isNumber() {
        return this.value instanceof Number;
    }

    private static boolean isPrimitiveOrString(Object arg6) {
        if((arg6 instanceof String)) {
            return 1;
        }

        Class v6 = arg6.getClass();
        Class[] v0 = JsonPrimitive.PRIMITIVE_TYPES;
        int v2 = v0.length;
        int v4;
        for(v4 = 0; v4 < v2; ++v4) {
            if(v0[v4].isAssignableFrom(v6)) {
                return 1;
            }
        }

        return 0;
    }

    public boolean isString() {
        return this.value instanceof String;
    }

    void setValue(Object arg2) {
        if((arg2 instanceof Character)) {
            this.value = String.valueOf(((Character)arg2).charValue());
        }
        else {
            boolean v0 = ((arg2 instanceof Number)) || (JsonPrimitive.isPrimitiveOrString(arg2)) ? true : false;
            $Gson$Preconditions.checkArgument(v0);
            this.value = arg2;
        }
    }
}

