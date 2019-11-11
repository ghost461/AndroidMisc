package com.google.gson.internal.bind;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public final class JsonTreeWriter extends JsonWriter {
    final class com.google.gson.internal.bind.JsonTreeWriter$1 extends Writer {
        com.google.gson.internal.bind.JsonTreeWriter$1() {
            super();
        }

        public void close() throws IOException {
            throw new AssertionError();
        }

        public void flush() throws IOException {
            throw new AssertionError();
        }

        public void write(char[] arg1, int arg2, int arg3) {
            throw new AssertionError();
        }
    }

    private static final JsonPrimitive SENTINEL_CLOSED;
    private static final Writer UNWRITABLE_WRITER;
    private String pendingName;
    private JsonElement product;
    private final List stack;

    static {
        JsonTreeWriter.UNWRITABLE_WRITER = new com.google.gson.internal.bind.JsonTreeWriter$1();
        JsonTreeWriter.SENTINEL_CLOSED = new JsonPrimitive("closed");
    }

    public JsonTreeWriter() {
        super(JsonTreeWriter.UNWRITABLE_WRITER);
        this.stack = new ArrayList();
        this.product = JsonNull.INSTANCE;
    }

    public JsonWriter beginArray() throws IOException {
        JsonArray v0 = new JsonArray();
        this.put(((JsonElement)v0));
        this.stack.add(v0);
        return this;
    }

    public JsonWriter beginObject() throws IOException {
        JsonObject v0 = new JsonObject();
        this.put(((JsonElement)v0));
        this.stack.add(v0);
        return this;
    }

    public void close() throws IOException {
        if(!this.stack.isEmpty()) {
            throw new IOException("Incomplete document");
        }

        this.stack.add(JsonTreeWriter.SENTINEL_CLOSED);
    }

    public JsonWriter endArray() throws IOException {
        if(!this.stack.isEmpty()) {
            if(this.pendingName != null) {
            }
            else if((this.peek() instanceof JsonArray)) {
                this.stack.remove(this.stack.size() - 1);
                return this;
            }
            else {
                throw new IllegalStateException();
            }
        }

        throw new IllegalStateException();
    }

    public JsonWriter endObject() throws IOException {
        if(!this.stack.isEmpty()) {
            if(this.pendingName != null) {
            }
            else if((this.peek() instanceof JsonObject)) {
                this.stack.remove(this.stack.size() - 1);
                return this;
            }
            else {
                throw new IllegalStateException();
            }
        }

        throw new IllegalStateException();
    }

    public void flush() throws IOException {
    }

    public JsonElement get() {
        if(!this.stack.isEmpty()) {
            StringBuilder v1 = new StringBuilder();
            v1.append("Expected one JSON element but was ");
            v1.append(this.stack);
            throw new IllegalStateException(v1.toString());
        }

        return this.product;
    }

    public JsonWriter name(String arg2) throws IOException {
        if(!this.stack.isEmpty()) {
            if(this.pendingName != null) {
            }
            else if((this.peek() instanceof JsonObject)) {
                this.pendingName = arg2;
                return this;
            }
            else {
                throw new IllegalStateException();
            }
        }

        throw new IllegalStateException();
    }

    public JsonWriter nullValue() throws IOException {
        this.put(JsonNull.INSTANCE);
        return this;
    }

    private JsonElement peek() {
        return this.stack.get(this.stack.size() - 1);
    }

    private void put(JsonElement arg3) {
        if(this.pendingName != null) {
            if(!arg3.isJsonNull() || (this.getSerializeNulls())) {
                this.peek().add(this.pendingName, arg3);
            }

            this.pendingName = null;
        }
        else {
            if(this.stack.isEmpty()) {
                this.product = arg3;
                return;
            }

            JsonElement v0 = this.peek();
            if(!(v0 instanceof JsonArray)) {
                goto label_22;
            }

            ((JsonArray)v0).add(arg3);
        }

        return;
    label_22:
        throw new IllegalStateException();
    }

    public JsonWriter value(double arg4) throws IOException {
        if(!this.isLenient() && ((Double.isNaN(arg4)) || (Double.isInfinite(arg4)))) {
            StringBuilder v1 = new StringBuilder();
            v1.append("JSON forbids NaN and infinities: ");
            v1.append(arg4);
            throw new IllegalArgumentException(v1.toString());
        }

        this.put(new JsonPrimitive(Double.valueOf(arg4)));
        return this;
    }

    public JsonWriter value(long arg2) throws IOException {
        this.put(new JsonPrimitive(Long.valueOf(arg2)));
        return this;
    }

    public JsonWriter value(Boolean arg2) throws IOException {
        if(arg2 == null) {
            return this.nullValue();
        }

        this.put(new JsonPrimitive(arg2));
        return this;
    }

    public JsonWriter value(Number arg4) throws IOException {
        if(arg4 == null) {
            return this.nullValue();
        }

        if(!this.isLenient()) {
            double v0 = arg4.doubleValue();
            if(!Double.isNaN(v0) && !Double.isInfinite(v0)) {
                goto label_19;
            }

            StringBuilder v1 = new StringBuilder();
            v1.append("JSON forbids NaN and infinities: ");
            v1.append(arg4);
            throw new IllegalArgumentException(v1.toString());
        }

    label_19:
        this.put(new JsonPrimitive(arg4));
        return this;
    }

    public JsonWriter value(String arg2) throws IOException {
        if(arg2 == null) {
            return this.nullValue();
        }

        this.put(new JsonPrimitive(arg2));
        return this;
    }

    public JsonWriter value(boolean arg2) throws IOException {
        this.put(new JsonPrimitive(Boolean.valueOf(arg2)));
        return this;
    }
}

