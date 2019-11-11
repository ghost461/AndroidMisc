package com.google.gson;

import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.MalformedJsonException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public final class JsonParser {
    public JsonParser() {
        super();
    }

    public JsonElement parse(JsonReader arg6) throws JsonIOException, JsonSyntaxException {
        StringBuilder v3;
        JsonElement v1_3;
        boolean v0 = arg6.isLenient();
        arg6.setLenient(true);
        try {
            v1_3 = Streams.parse(arg6);
        }
        catch(Throwable v1) {
        }
        catch(OutOfMemoryError v1_1) {
            try {
                v3 = new StringBuilder();
                v3.append("Failed parsing JSON source: ");
                v3.append(arg6);
                v3.append(" to Json");
                throw new JsonParseException(v3.toString(), ((Throwable)v1_1));
            }
            catch(Throwable v1) {
            label_32:
                arg6.setLenient(v0);
                throw v1;
            }
        }
        catch(StackOverflowError v1_2) {
            try {
                v3 = new StringBuilder();
                v3.append("Failed parsing JSON source: ");
                v3.append(arg6);
                v3.append(" to Json");
                throw new JsonParseException(v3.toString(), ((Throwable)v1_2));
            }
            catch(Throwable v1) {
                goto label_32;
            }
        }

        arg6.setLenient(v0);
        return v1_3;
    }

    public JsonElement parse(Reader arg3) throws JsonIOException, JsonSyntaxException {
        JsonElement v3_3;
        try {
            JsonReader v0 = new JsonReader(arg3);
            v3_3 = this.parse(v0);
            if(!v3_3.isJsonNull() && v0.peek() != JsonToken.END_DOCUMENT) {
                throw new JsonSyntaxException("Did not consume the entire document.");
            }
        }
        catch(NumberFormatException v3) {
            throw new JsonSyntaxException(((Throwable)v3));
        }
        catch(IOException v3_1) {
            throw new JsonIOException(((Throwable)v3_1));
        }
        catch(MalformedJsonException v3_2) {
            throw new JsonSyntaxException(((Throwable)v3_2));
        }

        return v3_3;
    }

    public JsonElement parse(String arg2) throws JsonSyntaxException {
        return this.parse(new StringReader(arg2));
    }
}

