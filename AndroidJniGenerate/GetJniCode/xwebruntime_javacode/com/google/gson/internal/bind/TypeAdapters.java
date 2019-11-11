package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.LazilyParsedNumber;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map$Entry;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

public final class TypeAdapters {
    final class com.google.gson.internal.bind.TypeAdapters$10 extends TypeAdapter {
        com.google.gson.internal.bind.TypeAdapters$10() {
            super();
        }

        public Object read(JsonReader arg1) throws IOException {
            return this.read(arg1);
        }

        public AtomicIntegerArray read(JsonReader arg5) throws IOException {
            ArrayList v0 = new ArrayList();
            arg5.beginArray();
            while(arg5.hasNext()) {
                try {
                    ((List)v0).add(Integer.valueOf(arg5.nextInt()));
                    continue;
                }
                catch(NumberFormatException v5) {
                    throw new JsonSyntaxException(((Throwable)v5));
                }
            }

            arg5.endArray();
            int v5_1 = ((List)v0).size();
            AtomicIntegerArray v1 = new AtomicIntegerArray(v5_1);
            int v2;
            for(v2 = 0; v2 < v5_1; ++v2) {
                v1.set(v2, ((List)v0).get(v2).intValue());
            }

            return v1;
        }

        public void write(JsonWriter arg1, Object arg2) throws IOException {
            this.write(arg1, ((AtomicIntegerArray)arg2));
        }

        public void write(JsonWriter arg5, AtomicIntegerArray arg6) throws IOException {
            arg5.beginArray();
            int v0 = arg6.length();
            int v1;
            for(v1 = 0; v1 < v0; ++v1) {
                arg5.value(((long)arg6.get(v1)));
            }

            arg5.endArray();
        }
    }

    final class com.google.gson.internal.bind.TypeAdapters$11 extends TypeAdapter {
        com.google.gson.internal.bind.TypeAdapters$11() {
            super();
        }

        public Number read(JsonReader arg3) throws IOException {
            if(arg3.peek() == JsonToken.NULL) {
                arg3.nextNull();
                return null;
            }

            try {
                return Long.valueOf(arg3.nextLong());
            }
            catch(NumberFormatException v3) {
                throw new JsonSyntaxException(((Throwable)v3));
            }
        }

        public Object read(JsonReader arg1) throws IOException {
            return this.read(arg1);
        }

        public void write(JsonWriter arg1, Number arg2) throws IOException {
            arg1.value(arg2);
        }

        public void write(JsonWriter arg1, Object arg2) throws IOException {
            this.write(arg1, ((Number)arg2));
        }
    }

    final class com.google.gson.internal.bind.TypeAdapters$12 extends TypeAdapter {
        com.google.gson.internal.bind.TypeAdapters$12() {
            super();
        }

        public Number read(JsonReader arg3) throws IOException {
            if(arg3.peek() == JsonToken.NULL) {
                arg3.nextNull();
                return null;
            }

            return Float.valueOf(((float)arg3.nextDouble()));
        }

        public Object read(JsonReader arg1) throws IOException {
            return this.read(arg1);
        }

        public void write(JsonWriter arg1, Number arg2) throws IOException {
            arg1.value(arg2);
        }

        public void write(JsonWriter arg1, Object arg2) throws IOException {
            this.write(arg1, ((Number)arg2));
        }
    }

    final class com.google.gson.internal.bind.TypeAdapters$13 extends TypeAdapter {
        com.google.gson.internal.bind.TypeAdapters$13() {
            super();
        }

        public Number read(JsonReader arg3) throws IOException {
            if(arg3.peek() == JsonToken.NULL) {
                arg3.nextNull();
                return null;
            }

            return Double.valueOf(arg3.nextDouble());
        }

        public Object read(JsonReader arg1) throws IOException {
            return this.read(arg1);
        }

        public void write(JsonWriter arg1, Number arg2) throws IOException {
            arg1.value(arg2);
        }

        public void write(JsonWriter arg1, Object arg2) throws IOException {
            this.write(arg1, ((Number)arg2));
        }
    }

    final class com.google.gson.internal.bind.TypeAdapters$14 extends TypeAdapter {
        com.google.gson.internal.bind.TypeAdapters$14() {
            super();
        }

        public Number read(JsonReader arg4) throws IOException {
            JsonToken v0 = arg4.peek();
            int v1 = com.google.gson.internal.bind.TypeAdapters$36.$SwitchMap$com$google$gson$stream$JsonToken[v0.ordinal()];
            if(v1 != 1) {
                switch(v1) {
                    case 3: {
                        goto label_19;
                    }
                    case 4: {
                        goto label_16;
                    }
                }

                StringBuilder v1_1 = new StringBuilder();
                v1_1.append("Expecting number, got: ");
                v1_1.append(v0);
                throw new JsonSyntaxException(v1_1.toString());
            label_16:
                arg4.nextNull();
                return null;
            }

        label_19:
            return new LazilyParsedNumber(arg4.nextString());
        }

        public Object read(JsonReader arg1) throws IOException {
            return this.read(arg1);
        }

        public void write(JsonWriter arg1, Number arg2) throws IOException {
            arg1.value(arg2);
        }

        public void write(JsonWriter arg1, Object arg2) throws IOException {
            this.write(arg1, ((Number)arg2));
        }
    }

    final class com.google.gson.internal.bind.TypeAdapters$15 extends TypeAdapter {
        com.google.gson.internal.bind.TypeAdapters$15() {
            super();
        }

        public Character read(JsonReader arg4) throws IOException {
            if(arg4.peek() == JsonToken.NULL) {
                arg4.nextNull();
                return null;
            }

            String v4 = arg4.nextString();
            if(v4.length() != 1) {
                StringBuilder v1 = new StringBuilder();
                v1.append("Expecting character, got: ");
                v1.append(v4);
                throw new JsonSyntaxException(v1.toString());
            }

            return Character.valueOf(v4.charAt(0));
        }

        public Object read(JsonReader arg1) throws IOException {
            return this.read(arg1);
        }

        public void write(JsonWriter arg1, Character arg2) throws IOException {
            String v2 = arg2 == null ? null : String.valueOf(arg2);
            arg1.value(v2);
        }

        public void write(JsonWriter arg1, Object arg2) throws IOException {
            this.write(arg1, ((Character)arg2));
        }
    }

    final class com.google.gson.internal.bind.TypeAdapters$16 extends TypeAdapter {
        com.google.gson.internal.bind.TypeAdapters$16() {
            super();
        }

        public Object read(JsonReader arg1) throws IOException {
            return this.read(arg1);
        }

        public String read(JsonReader arg3) throws IOException {
            JsonToken v0 = arg3.peek();
            if(v0 == JsonToken.NULL) {
                arg3.nextNull();
                return null;
            }

            if(v0 == JsonToken.BOOLEAN) {
                return Boolean.toString(arg3.nextBoolean());
            }

            return arg3.nextString();
        }

        public void write(JsonWriter arg1, Object arg2) throws IOException {
            this.write(arg1, ((String)arg2));
        }

        public void write(JsonWriter arg1, String arg2) throws IOException {
            arg1.value(arg2);
        }
    }

    final class com.google.gson.internal.bind.TypeAdapters$17 extends TypeAdapter {
        com.google.gson.internal.bind.TypeAdapters$17() {
            super();
        }

        public Object read(JsonReader arg1) throws IOException {
            return this.read(arg1);
        }

        public BigDecimal read(JsonReader arg3) throws IOException {
            if(arg3.peek() == JsonToken.NULL) {
                arg3.nextNull();
                return null;
            }

            try {
                return new BigDecimal(arg3.nextString());
            }
            catch(NumberFormatException v3) {
                throw new JsonSyntaxException(((Throwable)v3));
            }
        }

        public void write(JsonWriter arg1, Object arg2) throws IOException {
            this.write(arg1, ((BigDecimal)arg2));
        }

        public void write(JsonWriter arg1, BigDecimal arg2) throws IOException {
            arg1.value(((Number)arg2));
        }
    }

    final class com.google.gson.internal.bind.TypeAdapters$18 extends TypeAdapter {
        com.google.gson.internal.bind.TypeAdapters$18() {
            super();
        }

        public Object read(JsonReader arg1) throws IOException {
            return this.read(arg1);
        }

        public BigInteger read(JsonReader arg3) throws IOException {
            if(arg3.peek() == JsonToken.NULL) {
                arg3.nextNull();
                return null;
            }

            try {
                return new BigInteger(arg3.nextString());
            }
            catch(NumberFormatException v3) {
                throw new JsonSyntaxException(((Throwable)v3));
            }
        }

        public void write(JsonWriter arg1, Object arg2) throws IOException {
            this.write(arg1, ((BigInteger)arg2));
        }

        public void write(JsonWriter arg1, BigInteger arg2) throws IOException {
            arg1.value(((Number)arg2));
        }
    }

    final class com.google.gson.internal.bind.TypeAdapters$19 extends TypeAdapter {
        com.google.gson.internal.bind.TypeAdapters$19() {
            super();
        }

        public Object read(JsonReader arg1) throws IOException {
            return this.read(arg1);
        }

        public StringBuilder read(JsonReader arg3) throws IOException {
            if(arg3.peek() == JsonToken.NULL) {
                arg3.nextNull();
                return null;
            }

            return new StringBuilder(arg3.nextString());
        }

        public void write(JsonWriter arg1, Object arg2) throws IOException {
            this.write(arg1, ((StringBuilder)arg2));
        }

        public void write(JsonWriter arg1, StringBuilder arg2) throws IOException {
            String v2 = arg2 == null ? null : arg2.toString();
            arg1.value(v2);
        }
    }

    final class com.google.gson.internal.bind.TypeAdapters$1 extends TypeAdapter {
        com.google.gson.internal.bind.TypeAdapters$1() {
            super();
        }

        public Class read(JsonReader arg2) throws IOException {
            throw new UnsupportedOperationException("Attempted to deserialize a java.lang.Class. Forgot to register a type adapter?");
        }

        public Object read(JsonReader arg1) throws IOException {
            return this.read(arg1);
        }

        public void write(JsonWriter arg3, Class arg4) throws IOException {
            StringBuilder v0 = new StringBuilder();
            v0.append("Attempted to serialize java.lang.Class: ");
            v0.append(arg4.getName());
            v0.append(". Forgot to register a type adapter?");
            throw new UnsupportedOperationException(v0.toString());
        }

        public void write(JsonWriter arg1, Object arg2) throws IOException {
            this.write(arg1, ((Class)arg2));
        }
    }

    final class com.google.gson.internal.bind.TypeAdapters$20 extends TypeAdapter {
        com.google.gson.internal.bind.TypeAdapters$20() {
            super();
        }

        public Object read(JsonReader arg1) throws IOException {
            return this.read(arg1);
        }

        public StringBuffer read(JsonReader arg3) throws IOException {
            if(arg3.peek() == JsonToken.NULL) {
                arg3.nextNull();
                return null;
            }

            return new StringBuffer(arg3.nextString());
        }

        public void write(JsonWriter arg1, Object arg2) throws IOException {
            this.write(arg1, ((StringBuffer)arg2));
        }

        public void write(JsonWriter arg1, StringBuffer arg2) throws IOException {
            String v2 = arg2 == null ? null : arg2.toString();
            arg1.value(v2);
        }
    }

    final class com.google.gson.internal.bind.TypeAdapters$21 extends TypeAdapter {
        com.google.gson.internal.bind.TypeAdapters$21() {
            super();
        }

        public Object read(JsonReader arg1) throws IOException {
            return this.read(arg1);
        }

        public URL read(JsonReader arg4) throws IOException {
            URL v2 = null;
            if(arg4.peek() == JsonToken.NULL) {
                arg4.nextNull();
                return v2;
            }

            String v4 = arg4.nextString();
            if("null".equals(v4)) {
            }
            else {
                v2 = new URL(v4);
            }

            return v2;
        }

        public void write(JsonWriter arg1, Object arg2) throws IOException {
            this.write(arg1, ((URL)arg2));
        }

        public void write(JsonWriter arg1, URL arg2) throws IOException {
            String v2 = arg2 == null ? null : arg2.toExternalForm();
            arg1.value(v2);
        }
    }

    final class com.google.gson.internal.bind.TypeAdapters$22 extends TypeAdapter {
        com.google.gson.internal.bind.TypeAdapters$22() {
            super();
        }

        public Object read(JsonReader arg1) throws IOException {
            return this.read(arg1);
        }

        public URI read(JsonReader arg4) throws IOException {
            URI v2 = null;
            if(arg4.peek() == JsonToken.NULL) {
                arg4.nextNull();
                return v2;
            }

            try {
                String v4_1 = arg4.nextString();
                if("null".equals(v4_1)) {
                }
                else {
                    v2 = new URI(v4_1);
                }
            }
            catch(URISyntaxException v4) {
                throw new JsonIOException(((Throwable)v4));
            }

            return v2;
        }

        public void write(JsonWriter arg1, Object arg2) throws IOException {
            this.write(arg1, ((URI)arg2));
        }

        public void write(JsonWriter arg1, URI arg2) throws IOException {
            String v2 = arg2 == null ? null : arg2.toASCIIString();
            arg1.value(v2);
        }
    }

    final class com.google.gson.internal.bind.TypeAdapters$23 extends TypeAdapter {
        com.google.gson.internal.bind.TypeAdapters$23() {
            super();
        }

        public Object read(JsonReader arg1) throws IOException {
            return this.read(arg1);
        }

        public InetAddress read(JsonReader arg3) throws IOException {
            if(arg3.peek() == JsonToken.NULL) {
                arg3.nextNull();
                return null;
            }

            return InetAddress.getByName(arg3.nextString());
        }

        public void write(JsonWriter arg1, Object arg2) throws IOException {
            this.write(arg1, ((InetAddress)arg2));
        }

        public void write(JsonWriter arg1, InetAddress arg2) throws IOException {
            String v2 = arg2 == null ? null : arg2.getHostAddress();
            arg1.value(v2);
        }
    }

    final class com.google.gson.internal.bind.TypeAdapters$24 extends TypeAdapter {
        com.google.gson.internal.bind.TypeAdapters$24() {
            super();
        }

        public Object read(JsonReader arg1) throws IOException {
            return this.read(arg1);
        }

        public UUID read(JsonReader arg3) throws IOException {
            if(arg3.peek() == JsonToken.NULL) {
                arg3.nextNull();
                return null;
            }

            return UUID.fromString(arg3.nextString());
        }

        public void write(JsonWriter arg1, Object arg2) throws IOException {
            this.write(arg1, ((UUID)arg2));
        }

        public void write(JsonWriter arg1, UUID arg2) throws IOException {
            String v2 = arg2 == null ? null : arg2.toString();
            arg1.value(v2);
        }
    }

    final class com.google.gson.internal.bind.TypeAdapters$25 extends TypeAdapter {
        com.google.gson.internal.bind.TypeAdapters$25() {
            super();
        }

        public Object read(JsonReader arg1) throws IOException {
            return this.read(arg1);
        }

        public Currency read(JsonReader arg1) throws IOException {
            return Currency.getInstance(arg1.nextString());
        }

        public void write(JsonWriter arg1, Object arg2) throws IOException {
            this.write(arg1, ((Currency)arg2));
        }

        public void write(JsonWriter arg1, Currency arg2) throws IOException {
            arg1.value(arg2.getCurrencyCode());
        }
    }

    final class com.google.gson.internal.bind.TypeAdapters$26 implements TypeAdapterFactory {
        com.google.gson.internal.bind.TypeAdapters$26() {
            super();
        }

        public TypeAdapter create(Gson arg2, TypeToken arg3) {
            if(arg3.getRawType() != Timestamp.class) {
                return null;
            }

            return new TypeAdapter(arg2.getAdapter(Date.class)) {
                public Object read(JsonReader arg1) throws IOException {
                    return this.read(arg1);
                }

                public Timestamp read(JsonReader arg4) throws IOException {
                    Object v4 = this.val$dateTypeAdapter.read(arg4);
                    Timestamp v0 = v4 != null ? new Timestamp(((Date)v4).getTime()) : null;
                    return v0;
                }

                public void write(JsonWriter arg1, Object arg2) throws IOException {
                    this.write(arg1, ((Timestamp)arg2));
                }

                public void write(JsonWriter arg2, Timestamp arg3) throws IOException {
                    this.val$dateTypeAdapter.write(arg2, arg3);
                }
            };
        }
    }

    final class com.google.gson.internal.bind.TypeAdapters$27 extends TypeAdapter {
        private static final String DAY_OF_MONTH = "dayOfMonth";
        private static final String HOUR_OF_DAY = "hourOfDay";
        private static final String MINUTE = "minute";
        private static final String MONTH = "month";
        private static final String SECOND = "second";
        private static final String YEAR = "year";

        com.google.gson.internal.bind.TypeAdapters$27() {
            super();
        }

        public Object read(JsonReader arg1) throws IOException {
            return this.read(arg1);
        }

        public Calendar read(JsonReader arg10) throws IOException {
            if(arg10.peek() == JsonToken.NULL) {
                arg10.nextNull();
                return null;
            }

            arg10.beginObject();
            int v2 = 0;
            int v3 = 0;
            int v4 = 0;
            int v5 = 0;
            int v6 = 0;
            int v7;
            for(v7 = 0; arg10.peek() != JsonToken.END_OBJECT; v7 = v1) {
                String v0 = arg10.nextName();
                int v1 = arg10.nextInt();
                if("year".equals(v0)) {
                    v2 = v1;
                    continue;
                }

                if("month".equals(v0)) {
                    v3 = v1;
                    continue;
                }

                if("dayOfMonth".equals(v0)) {
                    v4 = v1;
                    continue;
                }

                if("hourOfDay".equals(v0)) {
                    v5 = v1;
                    continue;
                }

                if("minute".equals(v0)) {
                    v6 = v1;
                    continue;
                }

                if(!"second".equals(v0)) {
                    continue;
                }
            }

            arg10.endObject();
            return new GregorianCalendar(v2, v3, v4, v5, v6, v7);
        }

        public void write(JsonWriter arg1, Object arg2) throws IOException {
            this.write(arg1, ((Calendar)arg2));
        }

        public void write(JsonWriter arg3, Calendar arg4) throws IOException {
            if(arg4 == null) {
                arg3.nullValue();
                return;
            }

            arg3.beginObject();
            arg3.name("year");
            arg3.value(((long)arg4.get(1)));
            arg3.name("month");
            arg3.value(((long)arg4.get(2)));
            arg3.name("dayOfMonth");
            arg3.value(((long)arg4.get(5)));
            arg3.name("hourOfDay");
            arg3.value(((long)arg4.get(11)));
            arg3.name("minute");
            arg3.value(((long)arg4.get(12)));
            arg3.name("second");
            arg3.value(((long)arg4.get(13)));
            arg3.endObject();
        }
    }

    final class com.google.gson.internal.bind.TypeAdapters$28 extends TypeAdapter {
        com.google.gson.internal.bind.TypeAdapters$28() {
            super();
        }

        public Object read(JsonReader arg1) throws IOException {
            return this.read(arg1);
        }

        public Locale read(JsonReader arg5) throws IOException {
            Locale v2 = null;
            if(arg5.peek() == JsonToken.NULL) {
                arg5.nextNull();
                return v2;
            }

            StringTokenizer v0 = new StringTokenizer(arg5.nextString(), "_");
            String v5 = v0.hasMoreElements() ? v0.nextToken() : ((String)v2);
            String v1 = v0.hasMoreElements() ? v0.nextToken() : ((String)v2);
            if(v0.hasMoreElements()) {
                String v2_1 = v0.nextToken();
            }

            if(v1 == null && (((String)v2)) == null) {
                return new Locale(v5);
            }

            if((((String)v2)) == null) {
                return new Locale(v5, v1);
            }

            return new Locale(v5, v1, ((String)v2));
        }

        public void write(JsonWriter arg1, Object arg2) throws IOException {
            this.write(arg1, ((Locale)arg2));
        }

        public void write(JsonWriter arg1, Locale arg2) throws IOException {
            String v2 = arg2 == null ? null : arg2.toString();
            arg1.value(v2);
        }
    }

    final class com.google.gson.internal.bind.TypeAdapters$29 extends TypeAdapter {
        com.google.gson.internal.bind.TypeAdapters$29() {
            super();
        }

        public JsonElement read(JsonReader arg4) throws IOException {
            switch(com.google.gson.internal.bind.TypeAdapters$36.$SwitchMap$com$google$gson$stream$JsonToken[arg4.peek().ordinal()]) {
                case 1: {
                    goto label_41;
                }
                case 2: {
                    goto label_36;
                }
                case 3: {
                    goto label_32;
                }
                case 4: {
                    goto label_29;
                }
                case 5: {
                    goto label_19;
                }
                case 6: {
                    goto label_8;
                }
            }

            throw new IllegalArgumentException();
        label_19:
            JsonArray v0 = new JsonArray();
            arg4.beginArray();
            while(arg4.hasNext()) {
                v0.add(this.read(arg4));
            }

            arg4.endArray();
            return ((JsonElement)v0);
        label_36:
            return new JsonPrimitive(Boolean.valueOf(arg4.nextBoolean()));
        label_8:
            JsonObject v0_1 = new JsonObject();
            arg4.beginObject();
            while(arg4.hasNext()) {
                v0_1.add(arg4.nextName(), this.read(arg4));
            }

            arg4.endObject();
            return ((JsonElement)v0_1);
        label_41:
            return new JsonPrimitive(new LazilyParsedNumber(arg4.nextString()));
        label_29:
            arg4.nextNull();
            return JsonNull.INSTANCE;
        label_32:
            return new JsonPrimitive(arg4.nextString());
        }

        public Object read(JsonReader arg1) throws IOException {
            return this.read(arg1);
        }

        public void write(JsonWriter arg3, JsonElement arg4) throws IOException {
            Iterator v4_1;
            if(arg4 == null || (arg4.isJsonNull())) {
                arg3.nullValue();
            }
            else if(arg4.isJsonPrimitive()) {
                JsonPrimitive v4 = arg4.getAsJsonPrimitive();
                if(v4.isNumber()) {
                    arg3.value(v4.getAsNumber());
                }
                else if(v4.isBoolean()) {
                    arg3.value(v4.getAsBoolean());
                }
                else {
                    arg3.value(v4.getAsString());
                }
            }
            else if(arg4.isJsonArray()) {
                arg3.beginArray();
                v4_1 = arg4.getAsJsonArray().iterator();
                while(v4_1.hasNext()) {
                    this.write(arg3, v4_1.next());
                }

                arg3.endArray();
            }
            else if(arg4.isJsonObject()) {
                arg3.beginObject();
                v4_1 = arg4.getAsJsonObject().entrySet().iterator();
                while(v4_1.hasNext()) {
                    Object v0 = v4_1.next();
                    arg3.name(((Map$Entry)v0).getKey());
                    this.write(arg3, ((Map$Entry)v0).getValue());
                }

                arg3.endObject();
            }
            else {
                StringBuilder v0_1 = new StringBuilder();
                v0_1.append("Couldn\'t write ");
                v0_1.append(arg4.getClass());
                throw new IllegalArgumentException(v0_1.toString());
            }
        }

        public void write(JsonWriter arg1, Object arg2) throws IOException {
            this.write(arg1, ((JsonElement)arg2));
        }
    }

    final class com.google.gson.internal.bind.TypeAdapters$2 extends TypeAdapter {
        com.google.gson.internal.bind.TypeAdapters$2() {
            super();
        }

        public Object read(JsonReader arg1) throws IOException {
            return this.read(arg1);
        }

        public BitSet read(JsonReader arg7) throws IOException {
            StringBuilder v0_1;
            BitSet v0 = new BitSet();
            arg7.beginArray();
            JsonToken v1 = arg7.peek();
            int v3 = 0;
            while(v1 != JsonToken.END_ARRAY) {
                boolean v5 = true;
                switch(com.google.gson.internal.bind.TypeAdapters$36.$SwitchMap$com$google$gson$stream$JsonToken[v1.ordinal()]) {
                    case 1: {
                        if(arg7.nextInt() != 0) {
                            goto label_41;
                        }

                    label_26:
                        v5 = false;
                        break;
                    }
                    case 2: {
                        v5 = arg7.nextBoolean();
                        break;
                    }
                    case 3: {
                        String v1_1 = arg7.nextString();
                        try {
                            if(Integer.parseInt(v1_1) == 0) {
                                goto label_26;
                            }
                        }
                        catch(NumberFormatException ) {
                            v0_1 = new StringBuilder();
                            v0_1.append("Error: Expecting: bitset number value (1, 0), Found: ");
                            v0_1.append(v1_1);
                            throw new JsonSyntaxException(v0_1.toString());
                        }

                        break;
                    }
                    default: {
                        v0_1 = new StringBuilder();
                        v0_1.append("Invalid bitset value type: ");
                        v0_1.append(v1);
                        throw new JsonSyntaxException(v0_1.toString());
                    }
                }

            label_41:
                if(v5) {
                    v0.set(v3);
                }

                ++v3;
                v1 = arg7.peek();
            }

            arg7.endArray();
            return v0;
        }

        public void write(JsonWriter arg1, Object arg2) throws IOException {
            this.write(arg1, ((BitSet)arg2));
        }

        public void write(JsonWriter arg5, BitSet arg6) throws IOException {
            arg5.beginArray();
            int v0 = arg6.length();
            int v1;
            for(v1 = 0; v1 < v0; ++v1) {
                arg5.value(((long)arg6.get(v1)));
            }

            arg5.endArray();
        }
    }

    final class com.google.gson.internal.bind.TypeAdapters$30 implements TypeAdapterFactory {
        com.google.gson.internal.bind.TypeAdapters$30() {
            super();
        }

        public TypeAdapter create(Gson arg1, TypeToken arg2) {
            Class v1 = arg2.getRawType();
            if(Enum.class.isAssignableFrom(v1)) {
                if(v1 == Enum.class) {
                }
                else {
                    if(!v1.isEnum()) {
                        v1 = v1.getSuperclass();
                    }

                    return new EnumTypeAdapter(v1);
                }
            }

            return null;
        }
    }

    class com.google.gson.internal.bind.TypeAdapters$36 {
        static {
            com.google.gson.internal.bind.TypeAdapters$36.$SwitchMap$com$google$gson$stream$JsonToken = new int[JsonToken.values().length];
            try {
                com.google.gson.internal.bind.TypeAdapters$36.$SwitchMap$com$google$gson$stream$JsonToken[JsonToken.NUMBER.ordinal()] = 1;
                goto label_9;
            }
            catch(NoSuchFieldError ) {
                try {
                label_9:
                    com.google.gson.internal.bind.TypeAdapters$36.$SwitchMap$com$google$gson$stream$JsonToken[JsonToken.BOOLEAN.ordinal()] = 2;
                    goto label_14;
                }
                catch(NoSuchFieldError ) {
                    try {
                    label_14:
                        com.google.gson.internal.bind.TypeAdapters$36.$SwitchMap$com$google$gson$stream$JsonToken[JsonToken.STRING.ordinal()] = 3;
                        goto label_19;
                    }
                    catch(NoSuchFieldError ) {
                        try {
                        label_19:
                            com.google.gson.internal.bind.TypeAdapters$36.$SwitchMap$com$google$gson$stream$JsonToken[JsonToken.NULL.ordinal()] = 4;
                            goto label_24;
                        }
                        catch(NoSuchFieldError ) {
                            try {
                            label_24:
                                com.google.gson.internal.bind.TypeAdapters$36.$SwitchMap$com$google$gson$stream$JsonToken[JsonToken.BEGIN_ARRAY.ordinal()] = 5;
                                goto label_29;
                            }
                            catch(NoSuchFieldError ) {
                                try {
                                label_29:
                                    com.google.gson.internal.bind.TypeAdapters$36.$SwitchMap$com$google$gson$stream$JsonToken[JsonToken.BEGIN_OBJECT.ordinal()] = 6;
                                    goto label_34;
                                }
                                catch(NoSuchFieldError ) {
                                    try {
                                    label_34:
                                        com.google.gson.internal.bind.TypeAdapters$36.$SwitchMap$com$google$gson$stream$JsonToken[JsonToken.END_DOCUMENT.ordinal()] = 7;
                                        goto label_39;
                                    }
                                    catch(NoSuchFieldError ) {
                                        try {
                                        label_39:
                                            com.google.gson.internal.bind.TypeAdapters$36.$SwitchMap$com$google$gson$stream$JsonToken[JsonToken.NAME.ordinal()] = 8;
                                            goto label_44;
                                        }
                                        catch(NoSuchFieldError ) {
                                            try {
                                            label_44:
                                                com.google.gson.internal.bind.TypeAdapters$36.$SwitchMap$com$google$gson$stream$JsonToken[JsonToken.END_OBJECT.ordinal()] = 9;
                                                goto label_49;
                                            }
                                            catch(NoSuchFieldError ) {
                                                try {
                                                label_49:
                                                    com.google.gson.internal.bind.TypeAdapters$36.$SwitchMap$com$google$gson$stream$JsonToken[JsonToken.END_ARRAY.ordinal()] = 10;
                                                    return;
                                                }
                                                catch(NoSuchFieldError ) {
                                                    return;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    final class com.google.gson.internal.bind.TypeAdapters$3 extends TypeAdapter {
        com.google.gson.internal.bind.TypeAdapters$3() {
            super();
        }

        public Boolean read(JsonReader arg3) throws IOException {
            JsonToken v0 = arg3.peek();
            if(v0 == JsonToken.NULL) {
                arg3.nextNull();
                return null;
            }

            if(v0 == JsonToken.STRING) {
                return Boolean.valueOf(Boolean.parseBoolean(arg3.nextString()));
            }

            return Boolean.valueOf(arg3.nextBoolean());
        }

        public Object read(JsonReader arg1) throws IOException {
            return this.read(arg1);
        }

        public void write(JsonWriter arg1, Boolean arg2) throws IOException {
            arg1.value(arg2);
        }

        public void write(JsonWriter arg1, Object arg2) throws IOException {
            this.write(arg1, ((Boolean)arg2));
        }
    }

    final class com.google.gson.internal.bind.TypeAdapters$4 extends TypeAdapter {
        com.google.gson.internal.bind.TypeAdapters$4() {
            super();
        }

        public Boolean read(JsonReader arg3) throws IOException {
            if(arg3.peek() == JsonToken.NULL) {
                arg3.nextNull();
                return null;
            }

            return Boolean.valueOf(arg3.nextString());
        }

        public Object read(JsonReader arg1) throws IOException {
            return this.read(arg1);
        }

        public void write(JsonWriter arg1, Boolean arg2) throws IOException {
            String v2 = arg2 == null ? "null" : arg2.toString();
            arg1.value(v2);
        }

        public void write(JsonWriter arg1, Object arg2) throws IOException {
            this.write(arg1, ((Boolean)arg2));
        }
    }

    final class com.google.gson.internal.bind.TypeAdapters$5 extends TypeAdapter {
        com.google.gson.internal.bind.TypeAdapters$5() {
            super();
        }

        public Number read(JsonReader arg3) throws IOException {
            if(arg3.peek() == JsonToken.NULL) {
                arg3.nextNull();
                return null;
            }

            try {
                return Byte.valueOf(((byte)arg3.nextInt()));
            }
            catch(NumberFormatException v3) {
                throw new JsonSyntaxException(((Throwable)v3));
            }
        }

        public Object read(JsonReader arg1) throws IOException {
            return this.read(arg1);
        }

        public void write(JsonWriter arg1, Number arg2) throws IOException {
            arg1.value(arg2);
        }

        public void write(JsonWriter arg1, Object arg2) throws IOException {
            this.write(arg1, ((Number)arg2));
        }
    }

    final class com.google.gson.internal.bind.TypeAdapters$6 extends TypeAdapter {
        com.google.gson.internal.bind.TypeAdapters$6() {
            super();
        }

        public Number read(JsonReader arg3) throws IOException {
            if(arg3.peek() == JsonToken.NULL) {
                arg3.nextNull();
                return null;
            }

            try {
                return Short.valueOf(((short)arg3.nextInt()));
            }
            catch(NumberFormatException v3) {
                throw new JsonSyntaxException(((Throwable)v3));
            }
        }

        public Object read(JsonReader arg1) throws IOException {
            return this.read(arg1);
        }

        public void write(JsonWriter arg1, Number arg2) throws IOException {
            arg1.value(arg2);
        }

        public void write(JsonWriter arg1, Object arg2) throws IOException {
            this.write(arg1, ((Number)arg2));
        }
    }

    final class com.google.gson.internal.bind.TypeAdapters$7 extends TypeAdapter {
        com.google.gson.internal.bind.TypeAdapters$7() {
            super();
        }

        public Number read(JsonReader arg3) throws IOException {
            if(arg3.peek() == JsonToken.NULL) {
                arg3.nextNull();
                return null;
            }

            try {
                return Integer.valueOf(arg3.nextInt());
            }
            catch(NumberFormatException v3) {
                throw new JsonSyntaxException(((Throwable)v3));
            }
        }

        public Object read(JsonReader arg1) throws IOException {
            return this.read(arg1);
        }

        public void write(JsonWriter arg1, Number arg2) throws IOException {
            arg1.value(arg2);
        }

        public void write(JsonWriter arg1, Object arg2) throws IOException {
            this.write(arg1, ((Number)arg2));
        }
    }

    final class com.google.gson.internal.bind.TypeAdapters$8 extends TypeAdapter {
        com.google.gson.internal.bind.TypeAdapters$8() {
            super();
        }

        public Object read(JsonReader arg1) throws IOException {
            return this.read(arg1);
        }

        public AtomicInteger read(JsonReader arg2) throws IOException {
            try {
                return new AtomicInteger(arg2.nextInt());
            }
            catch(NumberFormatException v2) {
                throw new JsonSyntaxException(((Throwable)v2));
            }
        }

        public void write(JsonWriter arg1, Object arg2) throws IOException {
            this.write(arg1, ((AtomicInteger)arg2));
        }

        public void write(JsonWriter arg3, AtomicInteger arg4) throws IOException {
            arg3.value(((long)arg4.get()));
        }
    }

    final class com.google.gson.internal.bind.TypeAdapters$9 extends TypeAdapter {
        com.google.gson.internal.bind.TypeAdapters$9() {
            super();
        }

        public Object read(JsonReader arg1) throws IOException {
            return this.read(arg1);
        }

        public AtomicBoolean read(JsonReader arg2) throws IOException {
            return new AtomicBoolean(arg2.nextBoolean());
        }

        public void write(JsonWriter arg1, Object arg2) throws IOException {
            this.write(arg1, ((AtomicBoolean)arg2));
        }

        public void write(JsonWriter arg1, AtomicBoolean arg2) throws IOException {
            arg1.value(arg2.get());
        }
    }

    final class EnumTypeAdapter extends TypeAdapter {
        private final Map constantToName;
        private final Map nameToConstant;

        public EnumTypeAdapter(Class arg12) {
            super();
            this.nameToConstant = new HashMap();
            this.constantToName = new HashMap();
            try {
                Object[] v0 = arg12.getEnumConstants();
                int v1 = v0.length;
                int v3;
                for(v3 = 0; v3 < v1; ++v3) {
                    Object v4 = v0[v3];
                    String v5 = ((Enum)v4).name();
                    Annotation v6 = arg12.getField(v5).getAnnotation(SerializedName.class);
                    if(v6 != null) {
                        v5 = ((SerializedName)v6).value();
                        String[] v6_1 = ((SerializedName)v6).alternate();
                        int v7 = v6_1.length;
                        int v8;
                        for(v8 = 0; v8 < v7; ++v8) {
                            this.nameToConstant.put(v6_1[v8], v4);
                        }
                    }

                    this.nameToConstant.put(v5, v4);
                    this.constantToName.put(v4, v5);
                }
            }
            catch(NoSuchFieldException v12) {
                goto label_38;
            }

            return;
        label_38:
            throw new AssertionError(v12);
        }

        public Enum read(JsonReader arg3) throws IOException {
            if(arg3.peek() == JsonToken.NULL) {
                arg3.nextNull();
                return null;
            }

            return this.nameToConstant.get(arg3.nextString());
        }

        public Object read(JsonReader arg1) throws IOException {
            return this.read(arg1);
        }

        public void write(JsonWriter arg2, Enum arg3) throws IOException {
            Object v3_1;
            if(arg3 == null) {
                String v3 = null;
            }
            else {
                v3_1 = this.constantToName.get(arg3);
            }

            arg2.value(((String)v3_1));
        }

        public void write(JsonWriter arg1, Object arg2) throws IOException {
            this.write(arg1, ((Enum)arg2));
        }
    }

    public static final TypeAdapter ATOMIC_BOOLEAN;
    public static final TypeAdapterFactory ATOMIC_BOOLEAN_FACTORY;
    public static final TypeAdapter ATOMIC_INTEGER;
    public static final TypeAdapter ATOMIC_INTEGER_ARRAY;
    public static final TypeAdapterFactory ATOMIC_INTEGER_ARRAY_FACTORY;
    public static final TypeAdapterFactory ATOMIC_INTEGER_FACTORY;
    public static final TypeAdapter BIG_DECIMAL;
    public static final TypeAdapter BIG_INTEGER;
    public static final TypeAdapter BIT_SET;
    public static final TypeAdapterFactory BIT_SET_FACTORY;
    public static final TypeAdapter BOOLEAN;
    public static final TypeAdapter BOOLEAN_AS_STRING;
    public static final TypeAdapterFactory BOOLEAN_FACTORY;
    public static final TypeAdapter BYTE;
    public static final TypeAdapterFactory BYTE_FACTORY;
    public static final TypeAdapter CALENDAR;
    public static final TypeAdapterFactory CALENDAR_FACTORY;
    public static final TypeAdapter CHARACTER;
    public static final TypeAdapterFactory CHARACTER_FACTORY;
    public static final TypeAdapter CLASS;
    public static final TypeAdapterFactory CLASS_FACTORY;
    public static final TypeAdapter CURRENCY;
    public static final TypeAdapterFactory CURRENCY_FACTORY;
    public static final TypeAdapter DOUBLE;
    public static final TypeAdapterFactory ENUM_FACTORY;
    public static final TypeAdapter FLOAT;
    public static final TypeAdapter INET_ADDRESS;
    public static final TypeAdapterFactory INET_ADDRESS_FACTORY;
    public static final TypeAdapter INTEGER;
    public static final TypeAdapterFactory INTEGER_FACTORY;
    public static final TypeAdapter JSON_ELEMENT;
    public static final TypeAdapterFactory JSON_ELEMENT_FACTORY;
    public static final TypeAdapter LOCALE;
    public static final TypeAdapterFactory LOCALE_FACTORY;
    public static final TypeAdapter LONG;
    public static final TypeAdapter NUMBER;
    public static final TypeAdapterFactory NUMBER_FACTORY;
    public static final TypeAdapter SHORT;
    public static final TypeAdapterFactory SHORT_FACTORY;
    public static final TypeAdapter STRING;
    public static final TypeAdapter STRING_BUFFER;
    public static final TypeAdapterFactory STRING_BUFFER_FACTORY;
    public static final TypeAdapter STRING_BUILDER;
    public static final TypeAdapterFactory STRING_BUILDER_FACTORY;
    public static final TypeAdapterFactory STRING_FACTORY;
    public static final TypeAdapterFactory TIMESTAMP_FACTORY;
    public static final TypeAdapter URI;
    public static final TypeAdapterFactory URI_FACTORY;
    public static final TypeAdapter URL;
    public static final TypeAdapterFactory URL_FACTORY;
    public static final TypeAdapter UUID;
    public static final TypeAdapterFactory UUID_FACTORY;

    static {
        TypeAdapters.CLASS = new com.google.gson.internal.bind.TypeAdapters$1().nullSafe();
        TypeAdapters.CLASS_FACTORY = TypeAdapters.newFactory(Class.class, TypeAdapters.CLASS);
        TypeAdapters.BIT_SET = new com.google.gson.internal.bind.TypeAdapters$2().nullSafe();
        TypeAdapters.BIT_SET_FACTORY = TypeAdapters.newFactory(BitSet.class, TypeAdapters.BIT_SET);
        TypeAdapters.BOOLEAN = new com.google.gson.internal.bind.TypeAdapters$3();
        TypeAdapters.BOOLEAN_AS_STRING = new com.google.gson.internal.bind.TypeAdapters$4();
        TypeAdapters.BOOLEAN_FACTORY = TypeAdapters.newFactory(Boolean.TYPE, Boolean.class, TypeAdapters.BOOLEAN);
        TypeAdapters.BYTE = new com.google.gson.internal.bind.TypeAdapters$5();
        TypeAdapters.BYTE_FACTORY = TypeAdapters.newFactory(Byte.TYPE, Byte.class, TypeAdapters.BYTE);
        TypeAdapters.SHORT = new com.google.gson.internal.bind.TypeAdapters$6();
        TypeAdapters.SHORT_FACTORY = TypeAdapters.newFactory(Short.TYPE, Short.class, TypeAdapters.SHORT);
        TypeAdapters.INTEGER = new com.google.gson.internal.bind.TypeAdapters$7();
        TypeAdapters.INTEGER_FACTORY = TypeAdapters.newFactory(Integer.TYPE, Integer.class, TypeAdapters.INTEGER);
        TypeAdapters.ATOMIC_INTEGER = new com.google.gson.internal.bind.TypeAdapters$8().nullSafe();
        TypeAdapters.ATOMIC_INTEGER_FACTORY = TypeAdapters.newFactory(AtomicInteger.class, TypeAdapters.ATOMIC_INTEGER);
        TypeAdapters.ATOMIC_BOOLEAN = new com.google.gson.internal.bind.TypeAdapters$9().nullSafe();
        TypeAdapters.ATOMIC_BOOLEAN_FACTORY = TypeAdapters.newFactory(AtomicBoolean.class, TypeAdapters.ATOMIC_BOOLEAN);
        TypeAdapters.ATOMIC_INTEGER_ARRAY = new com.google.gson.internal.bind.TypeAdapters$10().nullSafe();
        TypeAdapters.ATOMIC_INTEGER_ARRAY_FACTORY = TypeAdapters.newFactory(AtomicIntegerArray.class, TypeAdapters.ATOMIC_INTEGER_ARRAY);
        TypeAdapters.LONG = new com.google.gson.internal.bind.TypeAdapters$11();
        TypeAdapters.FLOAT = new com.google.gson.internal.bind.TypeAdapters$12();
        TypeAdapters.DOUBLE = new com.google.gson.internal.bind.TypeAdapters$13();
        TypeAdapters.NUMBER = new com.google.gson.internal.bind.TypeAdapters$14();
        TypeAdapters.NUMBER_FACTORY = TypeAdapters.newFactory(Number.class, TypeAdapters.NUMBER);
        TypeAdapters.CHARACTER = new com.google.gson.internal.bind.TypeAdapters$15();
        TypeAdapters.CHARACTER_FACTORY = TypeAdapters.newFactory(Character.TYPE, Character.class, TypeAdapters.CHARACTER);
        TypeAdapters.STRING = new com.google.gson.internal.bind.TypeAdapters$16();
        TypeAdapters.BIG_DECIMAL = new com.google.gson.internal.bind.TypeAdapters$17();
        TypeAdapters.BIG_INTEGER = new com.google.gson.internal.bind.TypeAdapters$18();
        TypeAdapters.STRING_FACTORY = TypeAdapters.newFactory(String.class, TypeAdapters.STRING);
        TypeAdapters.STRING_BUILDER = new com.google.gson.internal.bind.TypeAdapters$19();
        TypeAdapters.STRING_BUILDER_FACTORY = TypeAdapters.newFactory(StringBuilder.class, TypeAdapters.STRING_BUILDER);
        TypeAdapters.STRING_BUFFER = new com.google.gson.internal.bind.TypeAdapters$20();
        TypeAdapters.STRING_BUFFER_FACTORY = TypeAdapters.newFactory(StringBuffer.class, TypeAdapters.STRING_BUFFER);
        TypeAdapters.URL = new com.google.gson.internal.bind.TypeAdapters$21();
        TypeAdapters.URL_FACTORY = TypeAdapters.newFactory(URL.class, TypeAdapters.URL);
        TypeAdapters.URI = new com.google.gson.internal.bind.TypeAdapters$22();
        TypeAdapters.URI_FACTORY = TypeAdapters.newFactory(URI.class, TypeAdapters.URI);
        TypeAdapters.INET_ADDRESS = new com.google.gson.internal.bind.TypeAdapters$23();
        TypeAdapters.INET_ADDRESS_FACTORY = TypeAdapters.newTypeHierarchyFactory(InetAddress.class, TypeAdapters.INET_ADDRESS);
        TypeAdapters.UUID = new com.google.gson.internal.bind.TypeAdapters$24();
        TypeAdapters.UUID_FACTORY = TypeAdapters.newFactory(UUID.class, TypeAdapters.UUID);
        TypeAdapters.CURRENCY = new com.google.gson.internal.bind.TypeAdapters$25().nullSafe();
        TypeAdapters.CURRENCY_FACTORY = TypeAdapters.newFactory(Currency.class, TypeAdapters.CURRENCY);
        TypeAdapters.TIMESTAMP_FACTORY = new com.google.gson.internal.bind.TypeAdapters$26();
        TypeAdapters.CALENDAR = new com.google.gson.internal.bind.TypeAdapters$27();
        TypeAdapters.CALENDAR_FACTORY = TypeAdapters.newFactoryForMultipleTypes(Calendar.class, GregorianCalendar.class, TypeAdapters.CALENDAR);
        TypeAdapters.LOCALE = new com.google.gson.internal.bind.TypeAdapters$28();
        TypeAdapters.LOCALE_FACTORY = TypeAdapters.newFactory(Locale.class, TypeAdapters.LOCALE);
        TypeAdapters.JSON_ELEMENT = new com.google.gson.internal.bind.TypeAdapters$29();
        TypeAdapters.JSON_ELEMENT_FACTORY = TypeAdapters.newTypeHierarchyFactory(JsonElement.class, TypeAdapters.JSON_ELEMENT);
        TypeAdapters.ENUM_FACTORY = new com.google.gson.internal.bind.TypeAdapters$30();
    }

    private TypeAdapters() {
        super();
        throw new UnsupportedOperationException();
    }

    public static TypeAdapterFactory newFactory(Class arg1, Class arg2, TypeAdapter arg3) {
        return new TypeAdapterFactory(arg1, arg2, arg3) {
            public TypeAdapter create(Gson arg1, TypeToken arg2) {
                Class v1 = arg2.getRawType();
                TypeAdapter v1_1 = v1 == this.val$unboxed || v1 == this.val$boxed ? this.val$typeAdapter : null;
                return v1_1;
            }

            public String toString() {
                return "Factory[type=" + this.val$boxed.getName() + "+" + this.val$unboxed.getName() + ",adapter=" + this.val$typeAdapter + "]";
            }
        };
    }

    public static TypeAdapterFactory newFactory(Class arg1, TypeAdapter arg2) {
        return new TypeAdapterFactory(arg1, arg2) {
            public TypeAdapter create(Gson arg1, TypeToken arg2) {
                TypeAdapter v1 = arg2.getRawType() == this.val$type ? this.val$typeAdapter : null;
                return v1;
            }

            public String toString() {
                return "Factory[type=" + this.val$type.getName() + ",adapter=" + this.val$typeAdapter + "]";
            }
        };
    }

    public static TypeAdapterFactory newFactory(TypeToken arg1, TypeAdapter arg2) {
        return new TypeAdapterFactory(arg1, arg2) {
            public TypeAdapter create(Gson arg1, TypeToken arg2) {
                TypeAdapter v1 = arg2.equals(this.val$type) ? this.val$typeAdapter : null;
                return v1;
            }
        };
    }

    public static TypeAdapterFactory newFactoryForMultipleTypes(Class arg1, Class arg2, TypeAdapter arg3) {
        return new TypeAdapterFactory(arg1, arg2, arg3) {
            public TypeAdapter create(Gson arg1, TypeToken arg2) {
                Class v1 = arg2.getRawType();
                TypeAdapter v1_1 = v1 == this.val$base || v1 == this.val$sub ? this.val$typeAdapter : null;
                return v1_1;
            }

            public String toString() {
                return "Factory[type=" + this.val$base.getName() + "+" + this.val$sub.getName() + ",adapter=" + this.val$typeAdapter + "]";
            }
        };
    }

    public static TypeAdapterFactory newTypeHierarchyFactory(Class arg1, TypeAdapter arg2) {
        return new TypeAdapterFactory(arg1, arg2) {
            public TypeAdapter create(Gson arg1, TypeToken arg2) {
                Class v1 = arg2.getRawType();
                if(!this.val$clazz.isAssignableFrom(v1)) {
                    return null;
                }

                return new TypeAdapter(v1) {
                    public Object read(JsonReader arg4) throws IOException {
                        Object v4 = com.google.gson.internal.bind.TypeAdapters$35.this.val$typeAdapter.read(arg4);
                        if(v4 != null && !this.val$requestedType.isInstance(v4)) {
                            StringBuilder v1 = new StringBuilder();
                            v1.append("Expected a ");
                            v1.append(this.val$requestedType.getName());
                            v1.append(" but was ");
                            v1.append(v4.getClass().getName());
                            throw new JsonSyntaxException(v1.toString());
                        }

                        return v4;
                    }

                    public void write(JsonWriter arg2, Object arg3) throws IOException {
                        com.google.gson.internal.bind.TypeAdapters$35.this.val$typeAdapter.write(arg2, arg3);
                    }
                };
            }

            public String toString() {
                return "Factory[typeHierarchy=" + this.val$clazz.getName() + ",adapter=" + this.val$typeAdapter + "]";
            }
        };
    }
}

