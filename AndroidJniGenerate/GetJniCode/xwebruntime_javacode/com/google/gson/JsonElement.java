package com.google.gson;

import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;

public abstract class JsonElement {
    public JsonElement() {
        super();
    }

    public abstract JsonElement deepCopy();

    public BigDecimal getAsBigDecimal() {
        throw new UnsupportedOperationException(this.getClass().getSimpleName());
    }

    public BigInteger getAsBigInteger() {
        throw new UnsupportedOperationException(this.getClass().getSimpleName());
    }

    public boolean getAsBoolean() {
        throw new UnsupportedOperationException(this.getClass().getSimpleName());
    }

    Boolean getAsBooleanWrapper() {
        throw new UnsupportedOperationException(this.getClass().getSimpleName());
    }

    public byte getAsByte() {
        throw new UnsupportedOperationException(this.getClass().getSimpleName());
    }

    public char getAsCharacter() {
        throw new UnsupportedOperationException(this.getClass().getSimpleName());
    }

    public double getAsDouble() {
        throw new UnsupportedOperationException(this.getClass().getSimpleName());
    }

    public float getAsFloat() {
        throw new UnsupportedOperationException(this.getClass().getSimpleName());
    }

    public int getAsInt() {
        throw new UnsupportedOperationException(this.getClass().getSimpleName());
    }

    public JsonArray getAsJsonArray() {
        if(this.isJsonArray()) {
            return this;
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("Not a JSON Array: ");
        v1.append(this);
        throw new IllegalStateException(v1.toString());
    }

    public JsonNull getAsJsonNull() {
        if(this.isJsonNull()) {
            return this;
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("Not a JSON Null: ");
        v1.append(this);
        throw new IllegalStateException(v1.toString());
    }

    public JsonObject getAsJsonObject() {
        if(this.isJsonObject()) {
            return this;
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("Not a JSON Object: ");
        v1.append(this);
        throw new IllegalStateException(v1.toString());
    }

    public JsonPrimitive getAsJsonPrimitive() {
        if(this.isJsonPrimitive()) {
            return this;
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("Not a JSON Primitive: ");
        v1.append(this);
        throw new IllegalStateException(v1.toString());
    }

    public long getAsLong() {
        throw new UnsupportedOperationException(this.getClass().getSimpleName());
    }

    public Number getAsNumber() {
        throw new UnsupportedOperationException(this.getClass().getSimpleName());
    }

    public short getAsShort() {
        throw new UnsupportedOperationException(this.getClass().getSimpleName());
    }

    public String getAsString() {
        throw new UnsupportedOperationException(this.getClass().getSimpleName());
    }

    public boolean isJsonArray() {
        return this instanceof JsonArray;
    }

    public boolean isJsonNull() {
        return this instanceof JsonNull;
    }

    public boolean isJsonObject() {
        return this instanceof JsonObject;
    }

    public boolean isJsonPrimitive() {
        return this instanceof JsonPrimitive;
    }

    public String toString() {
        try {
            StringWriter v0_1 = new StringWriter();
            JsonWriter v1 = new JsonWriter(((Writer)v0_1));
            v1.setLenient(true);
            Streams.write(this, v1);
            return v0_1.toString();
        }
        catch(IOException v0) {
            throw new AssertionError(v0);
        }
    }
}

