package com.google.gson.internal;

import java.io.ObjectStreamException;
import java.math.BigDecimal;

public final class LazilyParsedNumber extends Number {
    private final String value;

    public LazilyParsedNumber(String arg1) {
        super();
        this.value = arg1;
    }

    public double doubleValue() {
        return Double.parseDouble(this.value);
    }

    public boolean equals(Object arg5) {
        boolean v0 = true;
        if(this == (((LazilyParsedNumber)arg5))) {
            return 1;
        }

        if((arg5 instanceof LazilyParsedNumber)) {
            if(this.value != ((LazilyParsedNumber)arg5).value) {
                if(this.value.equals(((LazilyParsedNumber)arg5).value)) {
                }
                else {
                    v0 = false;
                }
            }

            return v0;
        }

        return 0;
    }

    public float floatValue() {
        return Float.parseFloat(this.value);
    }

    public int hashCode() {
        return this.value.hashCode();
    }

    public int intValue() {
        try {
            return Integer.parseInt(this.value);
        }
        catch(NumberFormatException ) {
            try {
                return ((int)Long.parseLong(this.value));
            }
            catch(NumberFormatException ) {
                return new BigDecimal(this.value).intValue();
            }
        }
    }

    public long longValue() {
        try {
            return Long.parseLong(this.value);
        }
        catch(NumberFormatException ) {
            return new BigDecimal(this.value).longValue();
        }
    }

    public String toString() {
        return this.value;
    }

    private Object writeReplace() throws ObjectStreamException {
        return new BigDecimal(this.value);
    }
}

