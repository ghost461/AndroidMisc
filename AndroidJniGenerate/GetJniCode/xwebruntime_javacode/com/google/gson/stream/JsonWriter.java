package com.google.gson.stream;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;

public class JsonWriter implements Closeable, Flushable {
    private static final String[] HTML_SAFE_REPLACEMENT_CHARS;
    private static final String[] REPLACEMENT_CHARS;
    private String deferredName;
    private boolean htmlSafe;
    private String indent;
    private boolean lenient;
    private final Writer out;
    private String separator;
    private boolean serializeNulls;
    private int[] stack;
    private int stackSize;

    static {
        JsonWriter.REPLACEMENT_CHARS = new String[0x80];
        int v1;
        for(v1 = 0; v1 <= 0x1F; ++v1) {
            JsonWriter.REPLACEMENT_CHARS[v1] = String.format("\\u%04x", Integer.valueOf(v1));
        }

        JsonWriter.REPLACEMENT_CHARS[34] = "\\\"";
        JsonWriter.REPLACEMENT_CHARS[92] = "\\\\";
        JsonWriter.REPLACEMENT_CHARS[9] = "\\t";
        JsonWriter.REPLACEMENT_CHARS[8] = "\\b";
        JsonWriter.REPLACEMENT_CHARS[10] = "\\n";
        JsonWriter.REPLACEMENT_CHARS[13] = "\\r";
        JsonWriter.REPLACEMENT_CHARS[12] = "\\f";
        JsonWriter.HTML_SAFE_REPLACEMENT_CHARS = JsonWriter.REPLACEMENT_CHARS.clone();
        JsonWriter.HTML_SAFE_REPLACEMENT_CHARS[60] = "\\u003c";
        JsonWriter.HTML_SAFE_REPLACEMENT_CHARS[62] = "\\u003e";
        JsonWriter.HTML_SAFE_REPLACEMENT_CHARS[38] = "\\u0026";
        JsonWriter.HTML_SAFE_REPLACEMENT_CHARS[61] = "\\u003d";
        JsonWriter.HTML_SAFE_REPLACEMENT_CHARS[39] = "\\u0027";
    }

    public JsonWriter(Writer arg2) {
        super();
        this.stack = new int[0x20];
        this.stackSize = 0;
        this.push(6);
        this.separator = ":";
        this.serializeNulls = true;
        if(arg2 == null) {
            throw new NullPointerException("out == null");
        }

        this.out = arg2;
    }

    private void beforeName() throws IOException {
        int v0 = this.peek();
        if(v0 == 5) {
            this.out.write(44);
        }
        else if(v0 != 3) {
            throw new IllegalStateException("Nesting problem.");
        }

        this.newline();
        this.replaceTop(4);
    }

    private void beforeValue() throws IOException {
        switch(this.peek()) {
            case 1: {
                goto label_26;
            }
            case 2: {
                goto label_21;
            }
            case 4: {
                goto label_15;
            }
            case 6: {
                goto label_12;
            }
            case 7: {
                goto label_6;
            }
        }

        throw new IllegalStateException("Nesting problem.");
    label_21:
        this.out.append(',');
        this.newline();
        return;
    label_6:
        if(this.lenient) {
        }
        else {
            throw new IllegalStateException("JSON must have only one top-level value.");
        label_26:
            this.replaceTop(2);
            this.newline();
            return;
        }

    label_12:
        this.replaceTop(7);
        return;
    label_15:
        this.out.append(this.separator);
        this.replaceTop(5);
    }

    public JsonWriter beginArray() throws IOException {
        this.writeDeferredName();
        return this.open(1, "[");
    }

    public JsonWriter beginObject() throws IOException {
        this.writeDeferredName();
        return this.open(3, "{");
    }

    private JsonWriter close(int arg2, int arg3, String arg4) throws IOException {
        int v0 = this.peek();
        if(v0 != arg3 && v0 != arg2) {
            throw new IllegalStateException("Nesting problem.");
        }

        if(this.deferredName != null) {
            StringBuilder v3 = new StringBuilder();
            v3.append("Dangling name: ");
            v3.append(this.deferredName);
            throw new IllegalStateException(v3.toString());
        }

        --this.stackSize;
        if(v0 == arg3) {
            this.newline();
        }

        this.out.write(arg4);
        return this;
    }

    public void close() throws IOException {
        this.out.close();
        int v0 = this.stackSize;
        if(v0 <= 1 && (v0 != 1 || this.stack[v0 - 1] == 7)) {
            this.stackSize = 0;
            return;
        }

        throw new IOException("Incomplete document");
    }

    public JsonWriter endArray() throws IOException {
        return this.close(1, 2, "]");
    }

    public JsonWriter endObject() throws IOException {
        return this.close(3, 5, "}");
    }

    public void flush() throws IOException {
        if(this.stackSize == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }

        this.out.flush();
    }

    public final boolean getSerializeNulls() {
        return this.serializeNulls;
    }

    public final boolean isHtmlSafe() {
        return this.htmlSafe;
    }

    public boolean isLenient() {
        return this.lenient;
    }

    public JsonWriter jsonValue(String arg2) throws IOException {
        if(arg2 == null) {
            return this.nullValue();
        }

        this.writeDeferredName();
        this.beforeValue();
        this.out.append(((CharSequence)arg2));
        return this;
    }

    public JsonWriter name(String arg2) throws IOException {
        if(arg2 == null) {
            throw new NullPointerException("name == null");
        }

        if(this.deferredName != null) {
            throw new IllegalStateException();
        }

        if(this.stackSize == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }

        this.deferredName = arg2;
        return this;
    }

    private void newline() throws IOException {
        if(this.indent == null) {
            return;
        }

        this.out.write("\n");
        int v0 = this.stackSize;
        int v1;
        for(v1 = 1; v1 < v0; ++v1) {
            this.out.write(this.indent);
        }
    }

    public JsonWriter nullValue() throws IOException {
        if(this.deferredName != null) {
            if(this.serializeNulls) {
                this.writeDeferredName();
            }
            else {
                this.deferredName = null;
                return this;
            }
        }

        this.beforeValue();
        this.out.write("null");
        return this;
    }

    private JsonWriter open(int arg1, String arg2) throws IOException {
        this.beforeValue();
        this.push(arg1);
        this.out.write(arg2);
        return this;
    }

    private int peek() {
        if(this.stackSize == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }

        return this.stack[this.stackSize - 1];
    }

    private void push(int arg5) {
        int[] v0;
        if(this.stackSize == this.stack.length) {
            v0 = new int[this.stackSize * 2];
            System.arraycopy(this.stack, 0, v0, 0, this.stackSize);
            this.stack = v0;
        }

        v0 = this.stack;
        int v1 = this.stackSize;
        this.stackSize = v1 + 1;
        v0[v1] = arg5;
    }

    private void replaceTop(int arg3) {
        this.stack[this.stackSize - 1] = arg3;
    }

    public final void setHtmlSafe(boolean arg1) {
        this.htmlSafe = arg1;
    }

    public final void setIndent(String arg2) {
        if(arg2.length() == 0) {
            this.indent = null;
            this.separator = ":";
        }
        else {
            this.indent = arg2;
            this.separator = ": ";
        }
    }

    public final void setLenient(boolean arg1) {
        this.lenient = arg1;
    }

    public final void setSerializeNulls(boolean arg1) {
        this.serializeNulls = arg1;
    }

    private void string(String arg8) throws IOException {
        String v4_1;
        String[] v0 = this.htmlSafe ? JsonWriter.HTML_SAFE_REPLACEMENT_CHARS : JsonWriter.REPLACEMENT_CHARS;
        this.out.write("\"");
        int v1 = arg8.length();
        int v2 = 0;
        int v3 = 0;
        while(v2 < v1) {
            int v4 = arg8.charAt(v2);
            if(v4 < 0x80) {
                v4_1 = v0[v4];
                if(v4_1 == null) {
                }
                else {
                    goto label_25;
                }
            }
            else if(v4 == 0x2028) {
                v4_1 = "\\u2028";
                goto label_25;
            }
            else if(v4 == 0x2029) {
                v4_1 = "\\u2029";
            label_25:
                if(v3 < v2) {
                    this.out.write(arg8, v3, v2 - v3);
                }

                this.out.write(v4_1);
                v3 = v2 + 1;
            }

            ++v2;
        }

        if(v3 < v1) {
            this.out.write(arg8, v3, v1 - v3);
        }

        this.out.write("\"");
    }

    public JsonWriter value(Number arg4) throws IOException {
        if(arg4 == null) {
            return this.nullValue();
        }

        this.writeDeferredName();
        String v0 = arg4.toString();
        if(!this.lenient && ((v0.equals("-Infinity")) || (v0.equals("Infinity")) || (v0.equals("NaN")))) {
            StringBuilder v1 = new StringBuilder();
            v1.append("Numeric values must be finite, but was ");
            v1.append(arg4);
            throw new IllegalArgumentException(v1.toString());
        }

        this.beforeValue();
        this.out.append(((CharSequence)v0));
        return this;
    }

    public JsonWriter value(String arg1) throws IOException {
        if(arg1 == null) {
            return this.nullValue();
        }

        this.writeDeferredName();
        this.beforeValue();
        this.string(arg1);
        return this;
    }

    public JsonWriter value(long arg2) throws IOException {
        this.writeDeferredName();
        this.beforeValue();
        this.out.write(Long.toString(arg2));
        return this;
    }

    public JsonWriter value(boolean arg2) throws IOException {
        this.writeDeferredName();
        this.beforeValue();
        Writer v0 = this.out;
        String v2 = arg2 ? "true" : "false";
        v0.write(v2);
        return this;
    }

    public JsonWriter value(Boolean arg2) throws IOException {
        if(arg2 == null) {
            return this.nullValue();
        }

        this.writeDeferredName();
        this.beforeValue();
        Writer v0 = this.out;
        String v2 = arg2.booleanValue() ? "true" : "false";
        v0.write(v2);
        return this;
    }

    public JsonWriter value(double arg4) throws IOException {
        this.writeDeferredName();
        if(!this.lenient && ((Double.isNaN(arg4)) || (Double.isInfinite(arg4)))) {
            StringBuilder v1 = new StringBuilder();
            v1.append("Numeric values must be finite, but was ");
            v1.append(arg4);
            throw new IllegalArgumentException(v1.toString());
        }

        this.beforeValue();
        this.out.append(Double.toString(arg4));
        return this;
    }

    private void writeDeferredName() throws IOException {
        if(this.deferredName != null) {
            this.beforeName();
            this.string(this.deferredName);
            this.deferredName = null;
        }
    }
}

