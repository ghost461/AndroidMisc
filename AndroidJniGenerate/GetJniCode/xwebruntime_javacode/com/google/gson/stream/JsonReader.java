package com.google.gson.stream;

import com.google.gson.internal.JsonReaderInternalAccess;
import com.google.gson.internal.bind.JsonTreeReader;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;

public class JsonReader implements Closeable {
    final class com.google.gson.stream.JsonReader$1 extends JsonReaderInternalAccess {
        com.google.gson.stream.JsonReader$1() {
            super();
        }

        public void promoteNameToValue(JsonReader arg4) throws IOException {
            if((arg4 instanceof JsonTreeReader)) {
                ((JsonTreeReader)arg4).promoteNameToValue();
                return;
            }

            int v0 = arg4.peeked;
            if(v0 == 0) {
                v0 = arg4.doPeek();
            }

            if(v0 == 13) {
                arg4.peeked = 9;
            }
            else if(v0 == 12) {
                arg4.peeked = 8;
            }
            else if(v0 == 14) {
                arg4.peeked = 10;
            }
            else {
                goto label_22;
            }

            return;
        label_22:
            StringBuilder v1 = new StringBuilder();
            v1.append("Expected a name but was ");
            v1.append(arg4.peek());
            v1.append(arg4.locationString());
            throw new IllegalStateException(v1.toString());
        }
    }

    private static final long MIN_INCOMPLETE_INTEGER = -922337203685477580L;
    private static final char[] NON_EXECUTE_PREFIX = null;
    private static final int NUMBER_CHAR_DECIMAL = 3;
    private static final int NUMBER_CHAR_DIGIT = 2;
    private static final int NUMBER_CHAR_EXP_DIGIT = 7;
    private static final int NUMBER_CHAR_EXP_E = 5;
    private static final int NUMBER_CHAR_EXP_SIGN = 6;
    private static final int NUMBER_CHAR_FRACTION_DIGIT = 4;
    private static final int NUMBER_CHAR_NONE = 0;
    private static final int NUMBER_CHAR_SIGN = 1;
    private static final int PEEKED_BEGIN_ARRAY = 3;
    private static final int PEEKED_BEGIN_OBJECT = 1;
    private static final int PEEKED_BUFFERED = 11;
    private static final int PEEKED_DOUBLE_QUOTED = 9;
    private static final int PEEKED_DOUBLE_QUOTED_NAME = 13;
    private static final int PEEKED_END_ARRAY = 4;
    private static final int PEEKED_END_OBJECT = 2;
    private static final int PEEKED_EOF = 17;
    private static final int PEEKED_FALSE = 6;
    private static final int PEEKED_LONG = 15;
    private static final int PEEKED_NONE = 0;
    private static final int PEEKED_NULL = 7;
    private static final int PEEKED_NUMBER = 16;
    private static final int PEEKED_SINGLE_QUOTED = 8;
    private static final int PEEKED_SINGLE_QUOTED_NAME = 12;
    private static final int PEEKED_TRUE = 5;
    private static final int PEEKED_UNQUOTED = 10;
    private static final int PEEKED_UNQUOTED_NAME = 14;
    private final char[] buffer;
    private final Reader in;
    private boolean lenient;
    private int limit;
    private int lineNumber;
    private int lineStart;
    private int[] pathIndices;
    private String[] pathNames;
    int peeked;
    private long peekedLong;
    private int peekedNumberLength;
    private String peekedString;
    private int pos;
    private int[] stack;
    private int stackSize;

    static {
        JsonReader.NON_EXECUTE_PREFIX = ")]}\'\n".toCharArray();
        JsonReaderInternalAccess.INSTANCE = new com.google.gson.stream.JsonReader$1();
    }

    public JsonReader(Reader arg5) {
        super();
        this.lenient = false;
        this.buffer = new char[0x400];
        this.pos = 0;
        this.limit = 0;
        this.lineNumber = 0;
        this.lineStart = 0;
        this.peeked = 0;
        this.stack = new int[0x20];
        this.stackSize = 0;
        int[] v0 = this.stack;
        int v2 = this.stackSize;
        this.stackSize = v2 + 1;
        v0[v2] = 6;
        this.pathNames = new String[0x20];
        this.pathIndices = new int[0x20];
        if(arg5 == null) {
            throw new NullPointerException("in == null");
        }

        this.in = arg5;
    }

    public void beginArray() throws IOException {
        int v0 = this.peeked;
        if(v0 == 0) {
            v0 = this.doPeek();
        }

        if(v0 == 3) {
            this.push(1);
            this.pathIndices[this.stackSize - 1] = 0;
            this.peeked = 0;
            return;
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("Expected BEGIN_ARRAY but was ");
        v1.append(this.peek());
        v1.append(this.locationString());
        throw new IllegalStateException(v1.toString());
    }

    public void beginObject() throws IOException {
        int v0 = this.peeked;
        if(v0 == 0) {
            v0 = this.doPeek();
        }

        if(v0 == 1) {
            this.push(3);
            this.peeked = 0;
            return;
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("Expected BEGIN_OBJECT but was ");
        v1.append(this.peek());
        v1.append(this.locationString());
        throw new IllegalStateException(v1.toString());
    }

    private void checkLenient() throws IOException {
        if(!this.lenient) {
            throw this.syntaxError("Use JsonReader.setLenient(true) to accept malformed JSON");
        }
    }

    public void close() throws IOException {
        this.peeked = 0;
        this.stack[0] = 8;
        this.stackSize = 1;
        this.in.close();
    }

    private void consumeNonExecutePrefix() throws IOException {
        this.nextNonWhitespace(true);
        --this.pos;
        if(this.pos + JsonReader.NON_EXECUTE_PREFIX.length > this.limit && !this.fillBuffer(JsonReader.NON_EXECUTE_PREFIX.length)) {
            return;
        }

        int v0;
        for(v0 = 0; v0 < JsonReader.NON_EXECUTE_PREFIX.length; ++v0) {
            if(this.buffer[this.pos + v0] != JsonReader.NON_EXECUTE_PREFIX[v0]) {
                return;
            }
        }

        this.pos += JsonReader.NON_EXECUTE_PREFIX.length;
    }

    int doPeek() throws IOException {
        int v12;
        int v0 = this.stack[this.stackSize - 1];
        int v1 = 39;
        int v3 = 34;
        int v4 = 8;
        int v5 = 3;
        int v6 = 93;
        int v7 = 7;
        int v8 = 59;
        int v9 = 44;
        int v10 = 4;
        int v11 = 2;
        if(v0 == 1) {
            this.stack[this.stackSize - 1] = v11;
        }
        else if(v0 == v11) {
            v12 = this.nextNonWhitespace(true);
            if(v12 != v9) {
                if(v12 == v8) {
                    this.checkLenient();
                }
                else if(v12 != v6) {
                    throw this.syntaxError("Unterminated array");
                }
                else {
                    this.peeked = v10;
                    return v10;
                }
            }
        }
        else {
            v12 = 5;
            if(v0 != v5) {
                if(v0 == v12) {
                }
                else if(v0 == v10) {
                    this.stack[this.stackSize - 1] = v12;
                    v12 = this.nextNonWhitespace(true);
                    if(v12 == 58) {
                        goto label_93;
                    }
                    else if(v12 != 61) {
                        throw this.syntaxError("Expected \':\'");
                    }
                    else {
                        this.checkLenient();
                        if(this.pos >= this.limit && !this.fillBuffer(1)) {
                            goto label_93;
                        }

                        if(this.buffer[this.pos] != 62) {
                            goto label_93;
                        }

                        ++this.pos;
                        goto label_93;
                    }
                }
                else {
                    if(v0 == 6) {
                        if(this.lenient) {
                            this.consumeNonExecutePrefix();
                        }

                        this.stack[this.stackSize - 1] = v7;
                        goto label_93;
                    }

                    if(v0 == v7) {
                        if(this.nextNonWhitespace(false) == -1) {
                            this.peeked = 17;
                            return 17;
                        }

                        this.checkLenient();
                        --this.pos;
                        goto label_93;
                    }

                    if(v0 != v4) {
                        goto label_93;
                    }

                    throw new IllegalStateException("JsonReader is closed");
                }
            }

            goto label_149;
        }

    label_93:
        v12 = this.nextNonWhitespace(true);
        if(v12 != v3) {
            if(v12 != v1) {
                if(v12 != v9 && v12 != v8) {
                    if(v12 == 91) {
                        this.peeked = v5;
                        return v5;
                    }
                    else if(v12 != v6) {
                        if(v12 != 0x7B) {
                            --this.pos;
                            v0 = this.peekKeyword();
                            if(v0 != 0) {
                                return v0;
                            }
                            else {
                                v0 = this.peekNumber();
                                if(v0 != 0) {
                                    return v0;
                                }
                                else if(!this.isLiteral(this.buffer[this.pos])) {
                                    throw this.syntaxError("Expected value");
                                }
                                else {
                                    this.checkLenient();
                                    this.peeked = 10;
                                    return 10;
                                }
                            }
                        }
                        else {
                            this.peeked = 1;
                            return 1;
                        }
                    }
                    else if(v0 == 1) {
                        this.peeked = v10;
                        return v10;
                    }
                }

                if(v0 != 1) {
                    if(v0 == v11) {
                    }
                    else {
                        throw this.syntaxError("Unexpected value");
                    }
                }

                this.checkLenient();
                --this.pos;
                this.peeked = v7;
                return v7;
            }

            this.checkLenient();
            this.peeked = v4;
            return v4;
        }

        this.peeked = 9;
        return 9;
    label_149:
        this.stack[this.stackSize - 1] = v10;
        v4 = 0x7D;
        if(v0 == v12) {
            v5 = this.nextNonWhitespace(true);
            if(v5 != v9) {
                if(v5 == v8) {
                    this.checkLenient();
                }
                else if(v5 != v4) {
                    throw this.syntaxError("Unterminated object");
                }
                else {
                    this.peeked = v11;
                    return v11;
                }
            }
        }

        v5 = this.nextNonWhitespace(true);
        if(v5 != v3) {
            if(v5 != v1) {
                if(v5 != v4) {
                    this.checkLenient();
                    --this.pos;
                    if(this.isLiteral(((char)v5))) {
                        this.peeked = 14;
                        return 14;
                    }

                    throw this.syntaxError("Expected name");
                }

                if(v0 != v12) {
                    this.peeked = v11;
                    return v11;
                }

                throw this.syntaxError("Expected name");
            }

            this.checkLenient();
            this.peeked = 12;
            return 12;
        }

        this.peeked = 13;
        return 13;
    }

    public void endArray() throws IOException {
        int v0 = this.peeked;
        if(v0 == 0) {
            v0 = this.doPeek();
        }

        if(v0 == 4) {
            --this.stackSize;
            int[] v0_1 = this.pathIndices;
            int v1 = this.stackSize - 1;
            ++v0_1[v1];
            this.peeked = 0;
            return;
        }

        StringBuilder v1_1 = new StringBuilder();
        v1_1.append("Expected END_ARRAY but was ");
        v1_1.append(this.peek());
        v1_1.append(this.locationString());
        throw new IllegalStateException(v1_1.toString());
    }

    public void endObject() throws IOException {
        int v0 = this.peeked;
        if(v0 == 0) {
            v0 = this.doPeek();
        }

        if(v0 == 2) {
            --this.stackSize;
            this.pathNames[this.stackSize] = null;
            int[] v0_1 = this.pathIndices;
            int v1 = this.stackSize - 1;
            ++v0_1[v1];
            this.peeked = 0;
            return;
        }

        StringBuilder v1_1 = new StringBuilder();
        v1_1.append("Expected END_OBJECT but was ");
        v1_1.append(this.peek());
        v1_1.append(this.locationString());
        throw new IllegalStateException(v1_1.toString());
    }

    private boolean fillBuffer(int arg7) throws IOException {
        char[] v0 = this.buffer;
        this.lineStart -= this.pos;
        if(this.limit != this.pos) {
            this.limit -= this.pos;
            System.arraycopy(v0, this.pos, v0, 0, this.limit);
        }
        else {
            this.limit = 0;
        }

        this.pos = 0;
        do {
            int v1 = this.in.read(v0, this.limit, v0.length - this.limit);
            if(v1 == -1) {
                return 0;
            }

            this.limit += v1;
            if(this.lineNumber == 0 && this.lineStart == 0 && this.limit > 0 && v0[0] == 0xFEFF) {
                ++this.pos;
                ++this.lineStart;
                ++arg7;
            }
        }
        while(this.limit < arg7);

        return 1;
    }

    public String getPath() {
        StringBuilder v0 = new StringBuilder();
        v0.append('$');
        int v1 = this.stackSize;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            switch(this.stack[v2]) {
                case 1: 
                case 2: {
                    v0.append('[');
                    v0.append(this.pathIndices[v2]);
                    v0.append(']');
                    break;
                }
                case 3: 
                case 4: 
                case 5: {
                    v0.append('.');
                    if(this.pathNames[v2] == null) {
                        goto label_27;
                    }

                    v0.append(this.pathNames[v2]);
                    break;
                }
                default: {
                    break;
                }
            }

        label_27:
        }

        return v0.toString();
    }

    public boolean hasNext() throws IOException {
        int v0 = this.peeked;
        if(v0 == 0) {
            v0 = this.doPeek();
        }

        boolean v0_1 = v0 == 2 || v0 == 4 ? false : true;
        return v0_1;
    }

    public final boolean isLenient() {
        return this.lenient;
    }

    private boolean isLiteral(char arg1) throws IOException {
        switch(arg1) {
            case 35: 
            case 47: 
            case 59: 
            case 61: 
            case 92: {
                goto label_3;
            }
            case 9: 
            case 10: 
            case 12: 
            case 13: 
            case 32: 
            case 44: 
            case 58: 
            case 91: 
            case 93: 
            case 123: 
            case 125: {
                return 0;
            }
        }

        return 1;
    label_3:
        this.checkLenient();
        return 0;
    }

    String locationString() {
        int v0 = this.lineNumber + 1;
        int v1 = this.pos - this.lineStart + 1;
        return " at line " + v0 + " column " + v1 + " path " + this.getPath();
    }

    public boolean nextBoolean() throws IOException {
        int v1;
        int[] v0_1;
        int v0 = this.peeked;
        if(v0 == 0) {
            v0 = this.doPeek();
        }

        if(v0 == 5) {
            this.peeked = 0;
            v0_1 = this.pathIndices;
            v1 = this.stackSize - 1;
            ++v0_1[v1];
            return 1;
        }

        if(v0 == 6) {
            this.peeked = 0;
            v0_1 = this.pathIndices;
            v1 = this.stackSize - 1;
            ++v0_1[v1];
            return 0;
        }

        StringBuilder v1_1 = new StringBuilder();
        v1_1.append("Expected a boolean but was ");
        v1_1.append(this.peek());
        v1_1.append(this.locationString());
        throw new IllegalStateException(v1_1.toString());
    }

    public double nextDouble() throws IOException {
        int v1;
        int v0 = this.peeked;
        if(v0 == 0) {
            v0 = this.doPeek();
        }

        if(v0 == 15) {
            this.peeked = 0;
            int[] v0_1 = this.pathIndices;
            v1 = this.stackSize - 1;
            ++((int[])v0)[v1];
            return ((double)this.peekedLong);
        }

        int v3 = 11;
        if(v0 == 16) {
            this.peekedString = new String(this.buffer, this.pos, this.peekedNumberLength);
            this.pos += this.peekedNumberLength;
        }
        else {
            v1 = 8;
            if(v0 != v1) {
                if(v0 == 9) {
                }
                else if(v0 == 10) {
                    this.peekedString = this.nextUnquotedValue();
                    goto label_59;
                }
                else if(v0 != v3) {
                    StringBuilder v1_1 = new StringBuilder();
                    v1_1.append("Expected a double but was ");
                    v1_1.append(this.peek());
                    v1_1.append(this.locationString());
                    throw new IllegalStateException(v1_1.toString());
                }
                else {
                    goto label_59;
                }
            }

            char v0_2 = v0 == v1 ? '\'' : '\"';
            this.peekedString = this.nextQuotedValue(v0_2);
        }

    label_59:
        this.peeked = v3;
        double v0_3 = Double.parseDouble(this.peekedString);
        if(!this.lenient && ((Double.isNaN(v0_3)) || (Double.isInfinite(v0_3)))) {
            StringBuilder v3_1 = new StringBuilder();
            v3_1.append("JSON forbids NaN and infinities: ");
            v3_1.append(v0_3);
            v3_1.append(this.locationString());
            throw new MalformedJsonException(v3_1.toString());
        }

        this.peekedString = null;
        this.peeked = 0;
        int[] v2 = this.pathIndices;
        v3 = this.stackSize - 1;
        ++v2[v3];
        return v0_3;
    }

    public int nextInt() throws IOException {
        int[] v1_1;
        StringBuilder v1;
        int v0 = this.peeked;
        if(v0 == 0) {
            v0 = this.doPeek();
        }

        if(v0 == 15) {
            v0 = ((int)this.peekedLong);
            if(this.peekedLong != (((long)v0))) {
                v1 = new StringBuilder();
                v1.append("Expected an int but was ");
                v1.append(this.peekedLong);
                v1.append(this.locationString());
                throw new NumberFormatException(v1.toString());
            }

            this.peeked = 0;
            v1_1 = this.pathIndices;
            int v2 = this.stackSize - 1;
            ++v1_1[v2];
            return v0;
        }

        if(v0 == 16) {
            this.peekedString = new String(this.buffer, this.pos, this.peekedNumberLength);
            this.pos += this.peekedNumberLength;
            goto label_83;
        }

        int v1_2 = 10;
        int v3 = 8;
        if(v0 != v3 && v0 != 9) {
            if(v0 == v1_2) {
            }
            else {
                v1 = new StringBuilder();
                v1.append("Expected an int but was ");
                v1.append(this.peek());
                v1.append(this.locationString());
                throw new IllegalStateException(v1.toString());
            }
        }

        if(v0 == v1_2) {
            this.peekedString = this.nextUnquotedValue();
        }
        else {
            char v0_1 = v0 == v3 ? '\'' : '\"';
            this.peekedString = this.nextQuotedValue(v0_1);
        }

        try {
            v0 = Integer.parseInt(this.peekedString);
            this.peeked = 0;
            v1_1 = this.pathIndices;
            v3 = this.stackSize - 1;
            ++((int[])v1_2)[v3];
            return v0;
        }
        catch(NumberFormatException ) {
        label_83:
            this.peeked = 11;
            double v0_2 = Double.parseDouble(this.peekedString);
            v3 = ((int)v0_2);
            if((((double)v3)) != v0_2) {
                v1 = new StringBuilder();
                v1.append("Expected an int but was ");
                v1.append(this.peekedString);
                v1.append(this.locationString());
                throw new NumberFormatException(v1.toString());
            }

            this.peekedString = null;
            this.peeked = 0;
            int[] v0_3 = this.pathIndices;
            v1_2 = this.stackSize - 1;
            ++v0_3[v1_2];
            return v3;
        }
    }

    public long nextLong() throws IOException {
        StringBuilder v1_1;
        int v1;
        int[] v0_1;
        int v0 = this.peeked;
        if(v0 == 0) {
            v0 = this.doPeek();
        }

        if(v0 == 15) {
            this.peeked = 0;
            v0_1 = this.pathIndices;
            v1 = this.stackSize - 1;
            ++v0_1[v1];
            return this.peekedLong;
        }

        if(v0 == 16) {
            this.peekedString = new String(this.buffer, this.pos, this.peekedNumberLength);
            this.pos += this.peekedNumberLength;
            goto label_67;
        }

        v1 = 10;
        int v3 = 8;
        if(v0 != v3 && v0 != 9) {
            if(v0 == v1) {
            }
            else {
                v1_1 = new StringBuilder();
                v1_1.append("Expected a long but was ");
                v1_1.append(this.peek());
                v1_1.append(this.locationString());
                throw new IllegalStateException(v1_1.toString());
            }
        }

        if(v0 == v1) {
            this.peekedString = this.nextUnquotedValue();
        }
        else {
            char v0_2 = v0 == v3 ? '\'' : '\"';
            this.peekedString = this.nextQuotedValue(v0_2);
        }

        try {
            long v0_3 = Long.parseLong(this.peekedString);
            this.peeked = 0;
            int[] v3_1 = this.pathIndices;
            int v4 = this.stackSize - 1;
            ++v3_1[v4];
            return v0_3;
        }
        catch(NumberFormatException ) {
        label_67:
            this.peeked = 11;
            double v0_4 = Double.parseDouble(this.peekedString);
            long v3_2 = ((long)v0_4);
            if((((double)v3_2)) != v0_4) {
                v1_1 = new StringBuilder();
                v1_1.append("Expected a long but was ");
                v1_1.append(this.peekedString);
                v1_1.append(this.locationString());
                throw new NumberFormatException(v1_1.toString());
            }

            this.peekedString = null;
            this.peeked = 0;
            v0_1 = this.pathIndices;
            v1 = this.stackSize - 1;
            ++v0_1[v1];
            return v3_2;
        }
    }

    public String nextName() throws IOException {
        String v0_1;
        int v0 = this.peeked;
        if(v0 == 0) {
            v0 = this.doPeek();
        }

        if(v0 == 14) {
            v0_1 = this.nextUnquotedValue();
        }
        else if(v0 == 12) {
            v0_1 = this.nextQuotedValue('\'');
        }
        else if(v0 == 13) {
            v0_1 = this.nextQuotedValue('\"');
        }
        else {
            goto label_23;
        }

        this.peeked = 0;
        this.pathNames[this.stackSize - 1] = v0_1;
        return v0_1;
    label_23:
        StringBuilder v1 = new StringBuilder();
        v1.append("Expected a name but was ");
        v1.append(this.peek());
        v1.append(this.locationString());
        throw new IllegalStateException(v1.toString());
    }

    private int nextNonWhitespace(boolean arg8) throws IOException {
        char[] v0 = this.buffer;
        int v1 = this.pos;
        int v2 = this.limit;
        while(true) {
            if(v1 == v2) {
                this.pos = v1;
                if(this.fillBuffer(1)) {
                    v1 = this.pos;
                    v2 = this.limit;
                }
                else if(arg8) {
                    StringBuilder v0_1 = new StringBuilder();
                    v0_1.append("End of input");
                    v0_1.append(this.locationString());
                    throw new EOFException(v0_1.toString());
                }
                else {
                    return -1;
                }
            }

            int v4 = v1 + 1;
            v1 = v0[v1];
            if(v1 == 10) {
                ++this.lineNumber;
                this.lineStart = v4;
            }
            else if(v1 != 0x20 && v1 != 13) {
                if(v1 == 9) {
                }
                else {
                    int v5 = 0x2F;
                    if(v1 == v5) {
                        this.pos = v4;
                        int v6 = 2;
                        if(v4 == v2) {
                            --this.pos;
                            boolean v2_1 = this.fillBuffer(v6);
                            ++this.pos;
                            if(!v2_1) {
                                return v1;
                            }
                        }

                        this.checkLenient();
                        v2 = v0[this.pos];
                        if(v2 != 42) {
                            if(v2 != v5) {
                                return v1;
                            }

                            ++this.pos;
                            this.skipToEndOfLine();
                            v1 = this.pos;
                            v2 = this.limit;
                            continue;
                        }

                        ++this.pos;
                        if(!this.skipTo("*/")) {
                            throw this.syntaxError("Unterminated comment");
                        }

                        v1 = this.pos + v6;
                        v2 = this.limit;
                        continue;
                    }
                    else {
                        if(v1 == 35) {
                            this.pos = v4;
                            this.checkLenient();
                            this.skipToEndOfLine();
                            v1 = this.pos;
                            v2 = this.limit;
                            continue;
                        }

                        this.pos = v4;
                        return v1;
                    }
                }
            }

            v1 = v4;
        }
    }

    public void nextNull() throws IOException {
        int v0 = this.peeked;
        if(v0 == 0) {
            v0 = this.doPeek();
        }

        if(v0 == 7) {
            this.peeked = 0;
            int[] v0_1 = this.pathIndices;
            int v1 = this.stackSize - 1;
            ++v0_1[v1];
            return;
        }

        StringBuilder v1_1 = new StringBuilder();
        v1_1.append("Expected null but was ");
        v1_1.append(this.peek());
        v1_1.append(this.locationString());
        throw new IllegalStateException(v1_1.toString());
    }

    private String nextQuotedValue(char arg10) throws IOException {
        int v7;
        int v5;
        int v4;
        char[] v0 = this.buffer;
        StringBuilder v1 = null;
        do {
            int v2 = this.pos;
            int v3 = this.limit;
            while(true) {
            label_4:
                v4 = v2;
                while(true) {
                label_5:
                    v5 = 16;
                    if(v2 >= v3) {
                        goto label_47;
                    }

                    v7 = v2 + 1;
                    v2 = v0[v2];
                    if(v2 == arg10) {
                        this.pos = v7;
                        v7 = v7 - v4 - 1;
                        if(v1 == null) {
                            return new String(v0, v4, v7);
                        }

                        v1.append(v0, v4, v7);
                        return v1.toString();
                    }

                    if(v2 != 92) {
                        break;
                    }

                    this.pos = v7;
                    v7 = v7 - v4 - 1;
                    if(v1 == null) {
                        v1 = new StringBuilder(Math.max((v7 + 1) * 2, v5));
                    }

                    v1.append(v0, v4, v7);
                    v1.append(this.readEscapeCharacter());
                    v2 = this.pos;
                    v3 = this.limit;
                    goto label_4;
                }
            }

            if(v2 == 10) {
                ++this.lineNumber;
                this.lineStart = v7;
            }

            v2 = v7;
            goto label_5;
        label_47:
            if(v1 == null) {
                v1 = new StringBuilder(Math.max((v2 - v4) * 2, v5));
            }

            v1.append(v0, v4, v2 - v4);
            this.pos = v2;
        }
        while(this.fillBuffer(1));

        throw this.syntaxError("Unterminated string");
    }

    public String nextString() throws IOException {
        String v0_1;
        int v0 = this.peeked;
        if(v0 == 0) {
            v0 = this.doPeek();
        }

        if(v0 == 10) {
            v0_1 = this.nextUnquotedValue();
        }
        else if(v0 == 8) {
            v0_1 = this.nextQuotedValue('\'');
        }
        else if(v0 == 9) {
            v0_1 = this.nextQuotedValue('\"');
        }
        else if(v0 == 11) {
            v0_1 = this.peekedString;
            this.peekedString = null;
        }
        else if(v0 == 15) {
            v0_1 = Long.toString(this.peekedLong);
        }
        else if(v0 == 16) {
            v0_1 = new String(this.buffer, this.pos, this.peekedNumberLength);
            this.pos += this.peekedNumberLength;
        }
        else {
            goto label_48;
        }

        this.peeked = 0;
        int[] v1 = this.pathIndices;
        int v2 = this.stackSize - 1;
        ++v1[v2];
        return v0_1;
    label_48:
        StringBuilder v1_1 = new StringBuilder();
        v1_1.append("Expected a string but was ");
        v1_1.append(this.peek());
        v1_1.append(this.locationString());
        throw new IllegalStateException(v1_1.toString());
    }

    private String nextUnquotedValue() throws IOException {
        String v1_1;
        int v0 = 0;
        StringBuilder v2 = null;
        do {
            int v1 = 0;
            while(true) {
                if(this.pos + v1 < this.limit) {
                    switch(this.buffer[this.pos + v1]) {
                        case 35: 
                        case 47: 
                        case 59: 
                        case 61: 
                        case 92: {
                            goto label_15;
                        }
                        case 9: 
                        case 10: 
                        case 12: 
                        case 13: 
                        case 32: 
                        case 44: 
                        case 58: 
                        case 91: 
                        case 93: 
                        case 123: 
                        case 125: {
                            goto label_24;
                        }
                    }

                    ++v1;
                    continue;
                label_15:
                    this.checkLenient();
                    break;
                }
                else if(v1 >= this.buffer.length) {
                    goto label_26;
                }
                else if(this.fillBuffer(v1 + 1)) {
                    continue;
                }
                else {
                    break;
                }
            }

        label_24:
            v0 = v1;
            break;
        label_26:
            if(v2 == null) {
                v2 = new StringBuilder(Math.max(v1, 16));
            }

            v2.append(this.buffer, this.pos, v1);
            this.pos += v1;
        }
        while(this.fillBuffer(1));

        if(v2 == null) {
            v1_1 = new String(this.buffer, this.pos, v0);
        }
        else {
            v2.append(this.buffer, this.pos, v0);
            v1_1 = v2.toString();
        }

        this.pos += v0;
        return v1_1;
    }

    public JsonToken peek() throws IOException {
        int v0 = this.peeked;
        if(v0 == 0) {
            v0 = this.doPeek();
        }

        switch(v0) {
            case 1: {
                goto label_25;
            }
            case 2: {
                goto label_23;
            }
            case 3: {
                goto label_21;
            }
            case 4: {
                goto label_19;
            }
            case 5: 
            case 6: {
                goto label_17;
            }
            case 7: {
                goto label_15;
            }
            case 8: 
            case 9: 
            case 10: 
            case 11: {
                goto label_13;
            }
            case 12: 
            case 13: 
            case 14: {
                goto label_11;
            }
            case 15: 
            case 16: {
                goto label_9;
            }
            case 17: {
                goto label_7;
            }
        }

        throw new AssertionError();
    label_7:
        return JsonToken.END_DOCUMENT;
    label_9:
        return JsonToken.NUMBER;
    label_11:
        return JsonToken.NAME;
    label_13:
        return JsonToken.STRING;
    label_15:
        return JsonToken.NULL;
    label_17:
        return JsonToken.BOOLEAN;
    label_19:
        return JsonToken.END_ARRAY;
    label_21:
        return JsonToken.BEGIN_ARRAY;
    label_23:
        return JsonToken.END_OBJECT;
    label_25:
        return JsonToken.BEGIN_OBJECT;
    }

    private int peekKeyword() throws IOException {
        int v3;
        String v2;
        String v0_1;
        int v0 = this.buffer[this.pos];
        if(v0 == 0x74 || v0 == 84) {
            v0_1 = "true";
            v2 = "TRUE";
            v3 = 5;
        }
        else {
            if(v0 != 102) {
                if(v0 == 70) {
                }
                else {
                    if(v0 != 110) {
                        if(v0 == 78) {
                        }
                        else {
                            return 0;
                        }
                    }

                    v0_1 = "null";
                    v2 = "NULL";
                    v3 = 7;
                    goto label_31;
                }
            }

            v0_1 = "false";
            v2 = "FALSE";
            v3 = 6;
        }

    label_31:
        int v4 = v0_1.length();
        int v5;
        for(v5 = 1; v5 < v4; ++v5) {
            if(this.pos + v5 >= this.limit && !this.fillBuffer(v5 + 1)) {
                return 0;
            }

            int v6 = this.buffer[this.pos + v5];
            if(v6 != v0_1.charAt(v5) && v6 != v2.charAt(v5)) {
                return 0;
            }
        }

        if((this.pos + v4 < this.limit || (this.fillBuffer(v4 + 1))) && (this.isLiteral(this.buffer[this.pos + v4]))) {
            return 0;
        }

        this.pos += v4;
        this.peeked = v3;
        return v3;
    }

    private int peekNumber() throws IOException {
        JsonReader v0 = this;
        char[] v1 = v0.buffer;
        int v2 = v0.pos;
        int v7 = 0;
        int v8 = v0.limit;
        int v3 = 0;
        int v9 = 0;
        int v10 = 1;
        long v11 = 0;
        int v13 = 0;
        while(true) {
            int v15 = 2;
            if(v2 + v3 != v8) {
            label_24:
                char v14 = v1[v2 + v3];
                int v4 = 3;
                int v5 = 5;
                if(v14 != 43) {
                    if(v14 != 69 && v14 != 101) {
                        switch(v14) {
                            case 45: {
                                goto label_126;
                            }
                            case 46: {
                                goto label_120;
                            }
                        }

                        if(v14 >= 0x30) {
                            if(v14 > 57) {
                            }
                            else {
                                if(v9 != 1) {
                                    if(v9 == 0) {
                                    }
                                    else {
                                        if(v9 != v15) {
                                            if(v9 == v4) {
                                                v7 = 0;
                                                v9 = 4;
                                                goto label_150;
                                            }

                                            if(v9 != v5) {
                                                if(v9 == 6) {
                                                }
                                                else {
                                                    goto label_73;
                                                }
                                            }

                                            goto label_75;
                                        }
                                        else if(v11 == 0) {
                                            return 0;
                                        }
                                        else {
                                            long v16 = 10 * v11 - (((long)(v14 - 0x30)));
                                            long v4_1 = -922337203685477580L;
                                            if(v11 <= v4_1) {
                                                if(v11 == v4_1 && v16 < v11) {
                                                    goto label_60;
                                                }

                                                v4 = 0;
                                            }
                                            else {
                                            label_60:
                                                v4 = 1;
                                            }

                                            v10 &= v4;
                                            v11 = v16;
                                        }

                                    label_73:
                                        v7 = 0;
                                        goto label_150;
                                    label_75:
                                        v7 = 0;
                                        v9 = 7;
                                        goto label_150;
                                    }
                                }

                                v11 = ((long)(-(v14 - 0x30)));
                                v7 = 0;
                                v9 = 2;
                                goto label_150;
                            }
                        }

                        if(v0.isLiteral(v14)) {
                            return 0;
                        }

                        goto label_88;
                    }

                    goto label_136;
                }
                else {
                    goto label_145;
                }
            }
            else if(v3 == v1.length) {
                return v7;
            }
            else if(!v0.fillBuffer(v3 + 1)) {
            }
            else {
                v2 = v0.pos;
                v8 = v0.limit;
                goto label_24;
            }

        label_88:
            if(v9 == v15 && v10 != 0 && (v11 != -9223372036854775808L || v13 != 0) && (v11 != 0 || v13 == 0)) {
                if(v13 != 0) {
                }
                else {
                    v11 = -v11;
                }

                v0.peekedLong = v11;
                v0.pos += v3;
                v0.peeked = 15;
                return 15;
            }

            if(v9 != v15 && v9 != 4) {
                if(v9 == 7) {
                }
                else {
                    return 0;
                }
            }

            v0.peekedNumberLength = v3;
            v0.peeked = 16;
            return 16;
        label_120:
            v7 = 0;
            if(v9 == v15) {
                v9 = 3;
                goto label_150;
            }

            return 0;
        label_126:
            v7 = 0;
            if(v9 == 0) {
                v9 = 1;
                v13 = 1;
                goto label_150;
            }

            if(v9 == v5) {
                goto label_149;
            }

            return 0;
        label_136:
            v7 = 0;
            if(v9 != v15) {
                if(v9 == 4) {
                }
                else {
                    return 0;
                }
            }

            v9 = 5;
            goto label_150;
        label_145:
            v7 = 0;
            if(v9 != v5) {
                return 0;
            }

        label_149:
            v9 = 6;
        label_150:
            ++v3;
        }

        return 0;
    }

    private void push(int arg7) {
        int[] v0;
        if(this.stackSize == this.stack.length) {
            v0 = new int[this.stackSize * 2];
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

    private char readEscapeCharacter() throws IOException {
        if(this.pos == this.limit && !this.fillBuffer(1)) {
            throw this.syntaxError("Unterminated escape sequence");
        }

        char[] v0 = this.buffer;
        int v1 = this.pos;
        this.pos = v1 + 1;
        char v0_1 = v0[v1];
        char v1_1 = '\n';
        if(v0_1 == v1_1) {
            ++this.lineNumber;
            this.lineStart = this.pos;
        }
        else if(v0_1 != 34 && v0_1 != 39 && v0_1 != 0x2F && v0_1 != 92) {
            if(v0_1 != 98) {
                int v2 = 102;
                if(v0_1 == v2) {
                    return '\f';
                }
                else if(v0_1 == 110) {
                    return v1_1;
                }
                else if(v0_1 != 0x72) {
                    switch(v0_1) {
                        case 116: {
                            return '\t';
                        }
                        case 117: {
                            goto label_36;
                        }
                    }

                    throw this.syntaxError("Invalid escape sequence");
                    return '\t';
                label_36:
                    int v3 = 4;
                    if(this.pos + v3 > this.limit && !this.fillBuffer(v3)) {
                        throw this.syntaxError("Unterminated escape sequence");
                    }

                    v0_1 = '\u0000';
                    int v4 = this.pos;
                    int v5 = v4 + 4;
                    while(true) {
                        if(v4 >= v5) {
                            goto label_93;
                        }

                        int v6 = this.buffer[v4];
                        v0_1 = ((char)(v0_1 << 4));
                        if(v6 < 0x30 || v6 > 57) {
                            if(v6 >= 97 && v6 <= v2) {
                                v0_1 = ((char)(v0_1 + (v6 - 97 + v1_1)));
                                goto label_78;
                            }

                            if(v6 >= 65 && v6 <= 70) {
                                v0_1 = ((char)(v0_1 + (v6 - 65 + v1_1)));
                                goto label_78;
                            }

                            break;
                        }
                        else {
                            v0_1 = ((char)(v0_1 + (v6 - 0x30)));
                        }

                    label_78:
                        ++v4;
                    }

                    StringBuilder v1_2 = new StringBuilder();
                    v1_2.append("\\u");
                    v1_2.append(new String(this.buffer, this.pos, v3));
                    throw new NumberFormatException(v1_2.toString());
                label_93:
                    this.pos += v3;
                    return v0_1;
                }
                else {
                    return '\r';
                }
            }
            else {
                return '\b';
            }
        }

        return v0_1;
    }

    public final void setLenient(boolean arg1) {
        this.lenient = arg1;
    }

    private void skipQuotedValue(char arg7) throws IOException {
        char[] v0 = this.buffer;
        do {
            int v1 = this.pos;
            int v2 = this.limit;
            while(v1 < v2) {
                int v4 = v1 + 1;
                v1 = v0[v1];
                if(v1 == arg7) {
                    this.pos = v4;
                    return;
                }

                if(v1 == 92) {
                    this.pos = v4;
                    this.readEscapeCharacter();
                    v1 = this.pos;
                    v2 = this.limit;
                    continue;
                }

                if(v1 == 10) {
                    ++this.lineNumber;
                    this.lineStart = v4;
                }

                v1 = v4;
            }

            this.pos = v1;
        }
        while(this.fillBuffer(1));

        throw this.syntaxError("Unterminated string");
    }

    private boolean skipTo(String arg6) throws IOException {
        int v0 = arg6.length();
        while(true) {
            int v3 = 0;
            if(this.pos + v0 > this.limit) {
                if(this.fillBuffer(v0)) {
                }
                else {
                    return 0;
                }
            }

            if(this.buffer[this.pos] == 10) {
                ++this.lineNumber;
                this.lineStart = this.pos + 1;
            }
            else {
                while(true) {
                    if(v3 >= v0) {
                        return 1;
                    }
                    else if(this.buffer[this.pos + v3] == arg6.charAt(v3)) {
                        ++v3;
                        continue;
                    }

                    goto label_30;
                }

                return 1;
            }

        label_30:
            ++this.pos;
        }
    }

    private void skipToEndOfLine() throws IOException {
        do {
            if(this.pos >= this.limit && !this.fillBuffer(1)) {
                return;
            }

            char[] v0 = this.buffer;
            int v1 = this.pos;
            this.pos = v1 + 1;
            int v0_1 = v0[v1];
            if(v0_1 == 10) {
                ++this.lineNumber;
                this.lineStart = this.pos;
            }
            else if(v0_1 != 13) {
                continue;
            }

            return;
        }
        while(true);
    }

    private void skipUnquotedValue() throws IOException {
        do {
            int v0;
            for(v0 = 0; true; ++v0) {
                if(this.pos + v0 >= this.limit) {
                    goto label_17;
                }

                switch(this.buffer[this.pos + v0]) {
                    case 35: 
                    case 47: 
                    case 59: 
                    case 61: 
                    case 92: {
                        goto label_12;
                    }
                    case 9: 
                    case 10: 
                    case 12: 
                    case 13: 
                    case 32: 
                    case 44: 
                    case 58: 
                    case 91: 
                    case 93: 
                    case 123: 
                    case 125: {
                        goto label_13;
                    }
                }
            }

        label_12:
            this.checkLenient();
        label_13:
            this.pos += v0;
            return;
        label_17:
            this.pos += v0;
        }
        while(this.fillBuffer(1));
    }

    public void skipValue() throws IOException {
        int v1 = 0;
        do {
            int v2 = this.peeked;
            if(v2 == 0) {
                v2 = this.doPeek();
            }

            int v3 = 3;
            if(v2 == v3) {
                this.push(1);
                ++v1;
            }
            else if(v2 == 1) {
                this.push(v3);
                ++v1;
            }
            else if(v2 == 4) {
                --this.stackSize;
                --v1;
            }
            else if(v2 == 2) {
                --this.stackSize;
                --v1;
            }
            else {
                if(v2 != 14) {
                    if(v2 == 10) {
                    }
                    else {
                        if(v2 != 8) {
                            if(v2 == 12) {
                            }
                            else {
                                if(v2 != 9) {
                                    if(v2 == 13) {
                                    }
                                    else {
                                        if(v2 == 16) {
                                            this.pos += this.peekedNumberLength;
                                        }
                                        else {
                                        }

                                        goto label_58;
                                    }
                                }

                                this.skipQuotedValue('\"');
                                goto label_58;
                            }
                        }

                        this.skipQuotedValue('\'');
                        goto label_58;
                    }
                }

                this.skipUnquotedValue();
            }

        label_58:
            this.peeked = 0;
        }
        while(v1 != 0);

        int[] v0 = this.pathIndices;
        v1 = this.stackSize - 1;
        ++v0[v1];
        this.pathNames[this.stackSize - 1] = "null";
    }

    private IOException syntaxError(String arg3) throws IOException {
        StringBuilder v1 = new StringBuilder();
        v1.append(arg3);
        v1.append(this.locationString());
        throw new MalformedJsonException(v1.toString());
    }

    public String toString() {
        return this.getClass().getSimpleName() + this.locationString();
    }
}

