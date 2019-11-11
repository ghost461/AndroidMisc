package com.google.gson.internal.bind;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.Map$Entry;

public final class JsonTreeReader extends JsonReader {
    final class com.google.gson.internal.bind.JsonTreeReader$1 extends Reader {
        com.google.gson.internal.bind.JsonTreeReader$1() {
            super();
        }

        public void close() throws IOException {
            throw new AssertionError();
        }

        public int read(char[] arg1, int arg2, int arg3) throws IOException {
            throw new AssertionError();
        }
    }

    private static final Object SENTINEL_CLOSED;
    private static final Reader UNREADABLE_READER;
    private int[] pathIndices;
    private String[] pathNames;
    private Object[] stack;
    private int stackSize;

    static {
        JsonTreeReader.UNREADABLE_READER = new com.google.gson.internal.bind.JsonTreeReader$1();
        JsonTreeReader.SENTINEL_CLOSED = new Object();
    }

    public JsonTreeReader(JsonElement arg3) {
        super(JsonTreeReader.UNREADABLE_READER);
        this.stack = new Object[0x20];
        this.stackSize = 0;
        this.pathNames = new String[0x20];
        this.pathIndices = new int[0x20];
        this.push(arg3);
    }

    public void beginArray() throws IOException {
        this.expect(JsonToken.BEGIN_ARRAY);
        this.push(this.peekStack().iterator());
        this.pathIndices[this.stackSize - 1] = 0;
    }

    public void beginObject() throws IOException {
        this.expect(JsonToken.BEGIN_OBJECT);
        this.push(this.peekStack().entrySet().iterator());
    }

    public void close() throws IOException {
        this.stack = new Object[]{JsonTreeReader.SENTINEL_CLOSED};
        this.stackSize = 1;
    }

    public void endArray() throws IOException {
        this.expect(JsonToken.END_ARRAY);
        this.popStack();
        this.popStack();
        if(this.stackSize > 0) {
            int[] v0 = this.pathIndices;
            int v1 = this.stackSize - 1;
            ++v0[v1];
        }
    }

    public void endObject() throws IOException {
        this.expect(JsonToken.END_OBJECT);
        this.popStack();
        this.popStack();
        if(this.stackSize > 0) {
            int[] v0 = this.pathIndices;
            int v1 = this.stackSize - 1;
            ++v0[v1];
        }
    }

    private void expect(JsonToken arg4) throws IOException {
        if(this.peek() != arg4) {
            StringBuilder v1 = new StringBuilder();
            v1.append("Expected ");
            v1.append(arg4);
            v1.append(" but was ");
            v1.append(this.peek());
            v1.append(this.locationString());
            throw new IllegalStateException(v1.toString());
        }
    }

    public String getPath() {
        StringBuilder v0 = new StringBuilder();
        v0.append('$');
        int v1;
        for(v1 = 0; v1 < this.stackSize; ++v1) {
            if((this.stack[v1] instanceof JsonArray)) {
                ++v1;
                if((this.stack[v1] instanceof Iterator)) {
                    v0.append('[');
                    v0.append(this.pathIndices[v1]);
                    v0.append(']');
                }
            }
            else if((this.stack[v1] instanceof JsonObject)) {
                ++v1;
                if((this.stack[v1] instanceof Iterator)) {
                    v0.append('.');
                    if(this.pathNames[v1] != null) {
                        v0.append(this.pathNames[v1]);
                    }
                }
            }
        }

        return v0.toString();
    }

    public boolean hasNext() throws IOException {
        JsonToken v0 = this.peek();
        boolean v0_1 = v0 == JsonToken.END_OBJECT || v0 == JsonToken.END_ARRAY ? false : true;
        return v0_1;
    }

    private String locationString() {
        return " at path " + this.getPath();
    }

    public boolean nextBoolean() throws IOException {
        this.expect(JsonToken.BOOLEAN);
        boolean v0 = this.popStack().getAsBoolean();
        if(this.stackSize > 0) {
            int[] v1 = this.pathIndices;
            int v2 = this.stackSize - 1;
            ++v1[v2];
        }

        return v0;
    }

    public double nextDouble() throws IOException {
        JsonToken v0 = this.peek();
        if(v0 != JsonToken.NUMBER && v0 != JsonToken.STRING) {
            StringBuilder v2 = new StringBuilder();
            v2.append("Expected ");
            v2.append(JsonToken.NUMBER);
            v2.append(" but was ");
            v2.append(v0);
            v2.append(this.locationString());
            throw new IllegalStateException(v2.toString());
        }

        double v0_1 = this.peekStack().getAsDouble();
        if(!this.isLenient() && ((Double.isNaN(v0_1)) || (Double.isInfinite(v0_1)))) {
            StringBuilder v3 = new StringBuilder();
            v3.append("JSON forbids NaN and infinities: ");
            v3.append(v0_1);
            throw new NumberFormatException(v3.toString());
        }

        this.popStack();
        if(this.stackSize > 0) {
            int[] v2_1 = this.pathIndices;
            int v3_1 = this.stackSize - 1;
            ++v2_1[v3_1];
        }

        return v0_1;
    }

    public int nextInt() throws IOException {
        JsonToken v0 = this.peek();
        if(v0 != JsonToken.NUMBER && v0 != JsonToken.STRING) {
            StringBuilder v2 = new StringBuilder();
            v2.append("Expected ");
            v2.append(JsonToken.NUMBER);
            v2.append(" but was ");
            v2.append(v0);
            v2.append(this.locationString());
            throw new IllegalStateException(v2.toString());
        }

        int v0_1 = this.peekStack().getAsInt();
        this.popStack();
        if(this.stackSize > 0) {
            int[] v1 = this.pathIndices;
            int v2_1 = this.stackSize - 1;
            ++v1[v2_1];
        }

        return v0_1;
    }

    public long nextLong() throws IOException {
        JsonToken v0 = this.peek();
        if(v0 != JsonToken.NUMBER && v0 != JsonToken.STRING) {
            StringBuilder v2 = new StringBuilder();
            v2.append("Expected ");
            v2.append(JsonToken.NUMBER);
            v2.append(" but was ");
            v2.append(v0);
            v2.append(this.locationString());
            throw new IllegalStateException(v2.toString());
        }

        long v0_1 = this.peekStack().getAsLong();
        this.popStack();
        if(this.stackSize > 0) {
            int[] v2_1 = this.pathIndices;
            int v3 = this.stackSize - 1;
            ++v2_1[v3];
        }

        return v0_1;
    }

    public String nextName() throws IOException {
        this.expect(JsonToken.NAME);
        Object v0 = this.peekStack().next();
        Object v1 = ((Map$Entry)v0).getKey();
        this.pathNames[this.stackSize - 1] = v1;
        this.push(((Map$Entry)v0).getValue());
        return ((String)v1);
    }

    public void nextNull() throws IOException {
        this.expect(JsonToken.NULL);
        this.popStack();
        if(this.stackSize > 0) {
            int[] v0 = this.pathIndices;
            int v1 = this.stackSize - 1;
            ++v0[v1];
        }
    }

    public String nextString() throws IOException {
        JsonToken v0 = this.peek();
        if(v0 != JsonToken.STRING && v0 != JsonToken.NUMBER) {
            StringBuilder v2 = new StringBuilder();
            v2.append("Expected ");
            v2.append(JsonToken.STRING);
            v2.append(" but was ");
            v2.append(v0);
            v2.append(this.locationString());
            throw new IllegalStateException(v2.toString());
        }

        String v0_1 = this.popStack().getAsString();
        if(this.stackSize > 0) {
            int[] v1 = this.pathIndices;
            int v2_1 = this.stackSize - 1;
            ++v1[v2_1];
        }

        return v0_1;
    }

    public JsonToken peek() throws IOException {
        if(this.stackSize == 0) {
            return JsonToken.END_DOCUMENT;
        }

        Object v0 = this.peekStack();
        if((v0 instanceof Iterator)) {
            boolean v1 = this.stack[this.stackSize - 2] instanceof JsonObject;
            if(((Iterator)v0).hasNext()) {
                if(v1) {
                    return JsonToken.NAME;
                }

                this.push(((Iterator)v0).next());
                return this.peek();
            }

            JsonToken v0_1 = v1 ? JsonToken.END_OBJECT : JsonToken.END_ARRAY;
            return v0_1;
        }

        if((v0 instanceof JsonObject)) {
            return JsonToken.BEGIN_OBJECT;
        }

        if((v0 instanceof JsonArray)) {
            return JsonToken.BEGIN_ARRAY;
        }

        if((v0 instanceof JsonPrimitive)) {
            if(((JsonPrimitive)v0).isString()) {
                return JsonToken.STRING;
            }

            if(((JsonPrimitive)v0).isBoolean()) {
                return JsonToken.BOOLEAN;
            }

            if(((JsonPrimitive)v0).isNumber()) {
                return JsonToken.NUMBER;
            }

            throw new AssertionError();
        }

        if((v0 instanceof JsonNull)) {
            return JsonToken.NULL;
        }

        if(v0 == JsonTreeReader.SENTINEL_CLOSED) {
            throw new IllegalStateException("JsonReader is closed");
        }

        throw new AssertionError();
    }

    private Object peekStack() {
        return this.stack[this.stackSize - 1];
    }

    private Object popStack() {
        Object[] v0 = this.stack;
        int v1 = this.stackSize - 1;
        this.stackSize = v1;
        Object v0_1 = v0[v1];
        this.stack[this.stackSize] = null;
        return v0_1;
    }

    public void promoteNameToValue() throws IOException {
        this.expect(JsonToken.NAME);
        Object v0 = this.peekStack().next();
        this.push(((Map$Entry)v0).getValue());
        this.push(new JsonPrimitive(((Map$Entry)v0).getKey()));
    }

    private void push(Object arg7) {
        Object[] v0;
        if(this.stackSize == this.stack.length) {
            v0 = new Object[this.stackSize * 2];
            int[] v1 = new int[this.stackSize * 2];
            String[] v2 = new String[this.stackSize * 2];
            System.arraycopy(this.stack, 0, v0, 0, this.stackSize);
            System.arraycopy(this.pathIndices, 0, v1, 0, this.stackSize);
            System.arraycopy(this.pathNames, 0, v2, 0, this.stackSize);
            this.stack = v0;
            this.pathIndices = v1;
            this.pathNames = v2;
        }

        v0 = this.stack;
        int v1_1 = this.stackSize;
        this.stackSize = v1_1 + 1;
        v0[v1_1] = arg7;
    }

    public void skipValue() throws IOException {
        if(this.peek() == JsonToken.NAME) {
            this.nextName();
            this.pathNames[this.stackSize - 2] = "null";
        }
        else {
            this.popStack();
            if(this.stackSize > 0) {
                this.pathNames[this.stackSize - 1] = "null";
            }
        }

        if(this.stackSize > 0) {
            int[] v0 = this.pathIndices;
            int v1 = this.stackSize - 1;
            ++v0[v1];
        }
    }

    public String toString() {
        return this.getClass().getSimpleName();
    }
}

