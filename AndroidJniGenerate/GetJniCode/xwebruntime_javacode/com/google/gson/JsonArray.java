package com.google.gson;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class JsonArray extends JsonElement implements Iterable {
    private final List elements;

    public JsonArray() {
        super();
        this.elements = new ArrayList();
    }

    public JsonArray(int arg2) {
        super();
        this.elements = new ArrayList(arg2);
    }

    public void add(JsonElement arg2) {
        JsonNull v2;
        if(arg2 == null) {
            v2 = JsonNull.INSTANCE;
        }

        this.elements.add(v2);
    }

    public void add(Boolean arg3) {
        JsonPrimitive v3_1;
        List v0 = this.elements;
        if(arg3 == null) {
            JsonNull v3 = JsonNull.INSTANCE;
        }
        else {
            v3_1 = new JsonPrimitive(arg3);
        }

        v0.add(v3_1);
    }

    public void add(Character arg3) {
        JsonPrimitive v3_1;
        List v0 = this.elements;
        if(arg3 == null) {
            JsonNull v3 = JsonNull.INSTANCE;
        }
        else {
            v3_1 = new JsonPrimitive(arg3);
        }

        v0.add(v3_1);
    }

    public void add(Number arg3) {
        JsonPrimitive v3_1;
        List v0 = this.elements;
        if(arg3 == null) {
            JsonNull v3 = JsonNull.INSTANCE;
        }
        else {
            v3_1 = new JsonPrimitive(arg3);
        }

        v0.add(v3_1);
    }

    public void add(String arg3) {
        JsonNull v3;
        List v0 = this.elements;
        if(arg3 == null) {
            v3 = JsonNull.INSTANCE;
        }
        else {
            JsonPrimitive v3_1 = new JsonPrimitive(arg3);
        }

        v0.add(v3);
    }

    public void addAll(JsonArray arg2) {
        this.elements.addAll(arg2.elements);
    }

    public boolean contains(JsonElement arg2) {
        return this.elements.contains(arg2);
    }

    public JsonArray deepCopy() {
        if(!this.elements.isEmpty()) {
            JsonArray v0 = new JsonArray(this.elements.size());
            Iterator v1 = this.elements.iterator();
            while(v1.hasNext()) {
                v0.add(v1.next().deepCopy());
            }

            return v0;
        }

        return new JsonArray();
    }

    public JsonElement deepCopy() {
        return this.deepCopy();
    }

    public boolean equals(Object arg2) {
        boolean v2;
        if((((JsonArray)arg2)) != this) {
            if(((arg2 instanceof JsonArray)) && (((JsonArray)arg2).elements.equals(this.elements))) {
                goto label_10;
            }

            v2 = false;
        }
        else {
        label_10:
            v2 = true;
        }

        return v2;
    }

    public JsonElement get(int arg2) {
        return this.elements.get(arg2);
    }

    public BigDecimal getAsBigDecimal() {
        if(this.elements.size() == 1) {
            return this.elements.get(0).getAsBigDecimal();
        }

        throw new IllegalStateException();
    }

    public BigInteger getAsBigInteger() {
        if(this.elements.size() == 1) {
            return this.elements.get(0).getAsBigInteger();
        }

        throw new IllegalStateException();
    }

    public boolean getAsBoolean() {
        if(this.elements.size() == 1) {
            return this.elements.get(0).getAsBoolean();
        }

        throw new IllegalStateException();
    }

    public byte getAsByte() {
        if(this.elements.size() == 1) {
            return this.elements.get(0).getAsByte();
        }

        throw new IllegalStateException();
    }

    public char getAsCharacter() {
        if(this.elements.size() == 1) {
            return this.elements.get(0).getAsCharacter();
        }

        throw new IllegalStateException();
    }

    public double getAsDouble() {
        if(this.elements.size() == 1) {
            return this.elements.get(0).getAsDouble();
        }

        throw new IllegalStateException();
    }

    public float getAsFloat() {
        if(this.elements.size() == 1) {
            return this.elements.get(0).getAsFloat();
        }

        throw new IllegalStateException();
    }

    public int getAsInt() {
        if(this.elements.size() == 1) {
            return this.elements.get(0).getAsInt();
        }

        throw new IllegalStateException();
    }

    public long getAsLong() {
        if(this.elements.size() == 1) {
            return this.elements.get(0).getAsLong();
        }

        throw new IllegalStateException();
    }

    public Number getAsNumber() {
        if(this.elements.size() == 1) {
            return this.elements.get(0).getAsNumber();
        }

        throw new IllegalStateException();
    }

    public short getAsShort() {
        if(this.elements.size() == 1) {
            return this.elements.get(0).getAsShort();
        }

        throw new IllegalStateException();
    }

    public String getAsString() {
        if(this.elements.size() == 1) {
            return this.elements.get(0).getAsString();
        }

        throw new IllegalStateException();
    }

    public int hashCode() {
        return this.elements.hashCode();
    }

    public Iterator iterator() {
        return this.elements.iterator();
    }

    public JsonElement remove(int arg2) {
        return this.elements.remove(arg2);
    }

    public boolean remove(JsonElement arg2) {
        return this.elements.remove(arg2);
    }

    public JsonElement set(int arg2, JsonElement arg3) {
        return this.elements.set(arg2, arg3);
    }

    public int size() {
        return this.elements.size();
    }
}

