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
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public final class SqlDateTypeAdapter extends TypeAdapter {
    final class com.google.gson.internal.bind.SqlDateTypeAdapter$1 implements TypeAdapterFactory {
        com.google.gson.internal.bind.SqlDateTypeAdapter$1() {
            super();
        }

        public TypeAdapter create(Gson arg1, TypeToken arg2) {
            TypeAdapter v1_1;
            if(arg2.getRawType() == Date.class) {
                SqlDateTypeAdapter v1 = new SqlDateTypeAdapter();
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
        SqlDateTypeAdapter.FACTORY = new com.google.gson.internal.bind.SqlDateTypeAdapter$1();
    }

    public SqlDateTypeAdapter() {
        super();
        this.format = new SimpleDateFormat("MMM d, yyyy");
    }

    public Object read(JsonReader arg1) throws IOException {
        return this.read(arg1);
    }

    public Date read(JsonReader arg3) throws IOException {
        Date v3_2;
        __monitor_enter(this);
        try {
            if(arg3.peek() != JsonToken.NULL) {
                goto label_8;
            }

            arg3.nextNull();
        }
        catch(Throwable v3) {
            goto label_21;
        }

        __monitor_exit(this);
        return null;
        try {
        label_8:
            v3_2 = new Date(this.format.parse(arg3.nextString()).getTime());
        }
        catch(Throwable v3) {
        }
        catch(ParseException v3_1) {
            try {
                throw new JsonSyntaxException(((Throwable)v3_1));
            }
            catch(Throwable v3) {
            label_21:
                __monitor_exit(this);
                throw v3;
            }
        }

        __monitor_exit(this);
        return v3_2;
    }

    public void write(JsonWriter arg1, Object arg2) throws IOException {
        this.write(arg1, ((Date)arg2));
    }

    public void write(JsonWriter arg2, Date arg3) throws IOException {
        String v3;
        __monitor_enter(this);
        if(arg3 == null) {
            v3 = null;
        }
        else {
            try {
                v3 = this.format.format(((java.util.Date)arg3));
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

