package com.google.gson;

import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.MalformedJsonException;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Iterator;
import java.util.NoSuchElementException;

public final class JsonStreamParser implements Iterator {
    private final Object lock;
    private final JsonReader parser;

    public JsonStreamParser(Reader arg2) {
        super();
        this.parser = new JsonReader(arg2);
        this.parser.setLenient(true);
        this.lock = new Object();
    }

    public JsonStreamParser(String arg2) {
        this(new StringReader(arg2));
    }

    public boolean hasNext() {
        Object v0 = this.lock;
        __monitor_enter(v0);
        try {
            if(this.parser.peek() == JsonToken.END_DOCUMENT) {
                goto label_8;
            }

            goto label_6;
        }
        catch(Throwable v1) {
        label_22:
            throw v1;
        }
        catch(IOException v1_1) {
        label_6:
            boolean v1_3 = true;
            goto label_9;
        label_8:
            v1_3 = false;
            try {
            label_9:
                __monitor_exit(v0);
                return v1_3;
            label_21:
                __monitor_exit(v0);
                goto label_22;
            }
            catch(Throwable v1) {
                goto label_21;
            }
        }
        catch(MalformedJsonException v1_2) {
            try {
                throw new JsonSyntaxException(((Throwable)v1_2));
                throw new JsonIOException(((Throwable)v1_1));
            }
            catch(Throwable v1) {
                goto label_21;
            }
        }
    }

    public JsonElement next() throws JsonParseException {
        if(!this.hasNext()) {
            throw new NoSuchElementException();
        }

        try {
            return Streams.parse(this.parser);
        }
        catch(JsonParseException v0) {
            if((v0.getCause() instanceof EOFException)) {
                NoSuchElementException v0_3 = new NoSuchElementException();
            }

            throw v0;
        }
        catch(OutOfMemoryError v0_1) {
            throw new JsonParseException("Failed parsing JSON source to Json", ((Throwable)v0_1));
        }
        catch(StackOverflowError v0_2) {
            throw new JsonParseException("Failed parsing JSON source to Json", ((Throwable)v0_2));
        }
    }

    public Object next() {
        return this.next();
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}

