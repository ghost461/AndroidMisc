package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.JavaVersion;
import com.google.gson.internal.PreJava9DateFormatProvider;
import com.google.gson.internal.bind.util.ISO8601Utils;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public final class DateTypeAdapter extends TypeAdapter {
    final class com.google.gson.internal.bind.DateTypeAdapter$1 implements TypeAdapterFactory {
        com.google.gson.internal.bind.DateTypeAdapter$1() {
            super();
        }

        public TypeAdapter create(Gson arg1, TypeToken arg2) {
            TypeAdapter v1_1;
            if(arg2.getRawType() == Date.class) {
                DateTypeAdapter v1 = new DateTypeAdapter();
            }
            else {
                v1_1 = null;
            }

            return v1_1;
        }
    }

    public static final TypeAdapterFactory FACTORY;
    private final List dateFormats;

    static {
        DateTypeAdapter.FACTORY = new com.google.gson.internal.bind.DateTypeAdapter$1();
    }

    public DateTypeAdapter() {
        super();
        this.dateFormats = new ArrayList();
        int v2 = 2;
        this.dateFormats.add(DateFormat.getDateTimeInstance(v2, v2, Locale.US));
        if(!Locale.getDefault().equals(Locale.US)) {
            this.dateFormats.add(DateFormat.getDateTimeInstance(v2, v2));
        }

        if(JavaVersion.isJava9OrLater()) {
            this.dateFormats.add(PreJava9DateFormatProvider.getUSDateTimeFormat(v2, v2));
        }
    }

    private Date deserializeToDate(String arg3) {
        Date v0_2;
        Date v1_1;
        __monitor_enter(this);
        try {
            Iterator v0 = this.dateFormats.iterator();
        label_3:
            if(!v0.hasNext()) {
                goto label_9;
            }

            Object v1 = v0.next();
            try {
                v1_1 = ((DateFormat)v1).parse(arg3);
            }
            catch(ParseException ) {
                goto label_3;
            }
        }
        catch(Throwable v3) {
            goto label_20;
        }

        __monitor_exit(this);
        return v1_1;
        try {
        label_9:
            v0_2 = ISO8601Utils.parse(arg3, new ParsePosition(0));
            goto label_13;
        }
        catch(Throwable v3) {
        label_20:
            __monitor_exit(this);
            throw v3;
        }
        catch(ParseException v0_1) {
            try {
                throw new JsonSyntaxException(arg3, ((Throwable)v0_1));
            }
            catch(Throwable v3) {
                goto label_20;
            }

        label_13:
            __monitor_exit(this);
            return v0_2;
        }
    }

    public Object read(JsonReader arg1) throws IOException {
        return this.read(arg1);
    }

    public Date read(JsonReader arg3) throws IOException {
        if(arg3.peek() == JsonToken.NULL) {
            arg3.nextNull();
            return null;
        }

        return this.deserializeToDate(arg3.nextString());
    }

    public void write(JsonWriter arg1, Object arg2) throws IOException {
        this.write(arg1, ((Date)arg2));
    }

    public void write(JsonWriter arg3, Date arg4) throws IOException {
        __monitor_enter(this);
        if(arg4 == null) {
            try {
                arg3.nullValue();
            }
            catch(Throwable v3) {
                goto label_14;
            }

            __monitor_exit(this);
            return;
        }

        try {
            arg3.value(this.dateFormats.get(0).format(arg4));
        }
        catch(Throwable v3) {
        label_14:
            __monitor_exit(this);
            throw v3;
        }

        __monitor_exit(this);
    }
}

