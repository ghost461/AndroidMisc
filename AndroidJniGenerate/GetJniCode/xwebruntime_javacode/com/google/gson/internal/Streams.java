package com.google.gson.internal;

import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonNull;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.bind.TypeAdapters;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.google.gson.stream.MalformedJsonException;
import java.io.EOFException;
import java.io.IOException;
import java.io.Writer;

public final class Streams {
    final class AppendableWriter extends Writer {
        class CurrentWrite implements CharSequence {
            char[] chars;

            CurrentWrite() {
                super();
            }

            public char charAt(int arg2) {
                return this.chars[arg2];
            }

            public int length() {
                return this.chars.length;
            }

            public CharSequence subSequence(int arg3, int arg4) {
                return new String(this.chars, arg3, arg4 - arg3);
            }
        }

        private final Appendable appendable;
        private final CurrentWrite currentWrite;

        AppendableWriter(Appendable arg2) {
            super();
            this.currentWrite = new CurrentWrite();
            this.appendable = arg2;
        }

        public void close() {
        }

        public void flush() {
        }

        public void write(int arg2) throws IOException {
            this.appendable.append(((char)arg2));
        }

        public void write(char[] arg2, int arg3, int arg4) throws IOException {
            this.currentWrite.chars = arg2;
            this.appendable.append(this.currentWrite, arg3, arg4 + arg3);
        }
    }

    private Streams() {
        super();
        throw new UnsupportedOperationException();
    }

    public static JsonElement parse(JsonReader arg2) throws JsonParseException {
        int v0;
        try {
            arg2.peek();
            v0 = 0;
            goto label_2;
        }
        catch(NumberFormatException v2) {
        }
        catch(IOException v2_1) {
        }
        catch(MalformedJsonException v2_2) {
        }
        catch(EOFException v2_3) {
            v0 = 1;
            goto label_21;
            try {
            label_2:
                return TypeAdapters.JSON_ELEMENT.read(arg2);
            }
            catch(NumberFormatException v2) {
                throw new JsonSyntaxException(((Throwable)v2));
            }
            catch(IOException v2_1) {
                throw new JsonIOException(((Throwable)v2_1));
            }
            catch(MalformedJsonException v2_2) {
                throw new JsonSyntaxException(((Throwable)v2_2));
            }
            catch(EOFException v2_3) {
            label_21:
                if(v0 != 0) {
                    return JsonNull.INSTANCE;
                }

                throw new JsonSyntaxException(((Throwable)v2_3));
            }
        }
    }

    public static void write(JsonElement arg1, JsonWriter arg2) throws IOException {
        TypeAdapters.JSON_ELEMENT.write(arg2, arg1);
    }

    public static Writer writerForAppendable(Appendable arg1) {
        AppendableWriter v1;
        if((arg1 instanceof Writer)) {
        }
        else {
            v1 = new AppendableWriter(arg1);
        }

        return ((Appendable)v1);
    }
}

