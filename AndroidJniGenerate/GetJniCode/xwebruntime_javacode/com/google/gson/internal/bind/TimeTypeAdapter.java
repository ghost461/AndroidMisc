package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class TimeTypeAdapter extends TypeAdapter {
    final class com.google.gson.internal.bind.TimeTypeAdapter$1 implements TypeAdapterFactory {
        com.google.gson.internal.bind.TimeTypeAdapter$1() {
            super();
        }

        public TypeAdapter create(Gson arg1, TypeToken arg2) {
            TypeAdapter v1_1;
            if(arg2.getRawType() == Time.class) {
                TimeTypeAdapter v1 = new TimeTypeAdapter();
            }
            else {
                v1_1 = null;
            }

            return v1_1;
        }
    }

    public static final TypeAdapterFactory FACTORY;
    private final DateFormat format;

    static {
        TimeTypeAdapter.FACTORY = new com.google.gson.internal.bind.TimeTypeAdapter$1();
    }

    public TimeTypeAdapter() {
        super();
        this.format = new SimpleDateFormat("hh:mm:ss a");
    }

    public Object read(JsonReader arg1) throws IOException {
        return this.read(arg1);
    }

    public Time read(JsonReader arg4) throws IOException {
        Time v0;
        __monitor_enter(this);
        try {
            if(arg4.peek() != JsonToken.NULL) {
                goto label_8;
            }

            arg4.nextNull();
        }
        catch(Throwable v4) {
            goto label_21;
        }

        __monitor_exit(this);
        return null;
        try {
        label_8:
            v0 = new Time(this.format.parse(arg4.nextString()).getTime());
        }
        catch(Throwable v4) {
        }
        catch(ParseException v4_1) {
            try {
                throw new JsonSyntaxException(((Throwable)v4_1));
            }
            catch(Throwable v4) {
            label_21:
                __monitor_exit(this);
                throw v4;
            }
        }

        __monitor_exit(this);
        return v0;
    }

    public void write(JsonWriter arg1, Object arg2) throws IOException {
        this.write(arg1, ((Time)arg2));
    }

    public void write(JsonWriter arg2, Time arg3) throws IOException {
        String v3;
        __monitor_enter(this);
        if(arg3 == null) {
            v3 = null;
        }
        else {
            try {
                v3 = this.format.format(((Date)arg3));
            label_6:
                arg2.value(v3);
                goto label_7;
            }
            catch(Throwable v2) {
                __monitor_exit(this);
                throw v2;
            }
        }

        goto label_6;
    label_7:
        __monitor_exit(this);
    }
}

