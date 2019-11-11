package com.google.gson;

import com.google.gson.internal.JavaVersion;
import com.google.gson.internal.PreJava9DateFormatProvider;
import com.google.gson.internal.bind.util.ISO8601Utils;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

final class DefaultDateTypeAdapter extends TypeAdapter {
    private static final String SIMPLE_NAME = "DefaultDateTypeAdapter";
    private final List dateFormats;
    private final Class dateType;

    DefaultDateTypeAdapter(Class arg3, String arg4) {
        super();
        this.dateFormats = new ArrayList();
        this.dateType = DefaultDateTypeAdapter.verifyDateType(arg3);
        this.dateFormats.add(new SimpleDateFormat(arg4, Locale.US));
        if(!Locale.getDefault().equals(Locale.US)) {
            this.dateFormats.add(new SimpleDateFormat(arg4));
        }
    }

    public DefaultDateTypeAdapter(Class arg2, int arg3, int arg4) {
        super();
        this.dateFormats = new ArrayList();
        this.dateType = DefaultDateTypeAdapter.verifyDateType(arg2);
        this.dateFormats.add(DateFormat.getDateTimeInstance(arg3, arg4, Locale.US));
        if(!Locale.getDefault().equals(Locale.US)) {
            this.dateFormats.add(DateFormat.getDateTimeInstance(arg3, arg4));
        }

        if(JavaVersion.isJava9OrLater()) {
            this.dateFormats.add(PreJava9DateFormatProvider.getUSDateTimeFormat(arg3, arg4));
        }
    }

    public DefaultDateTypeAdapter(int arg2, int arg3) {
        this(Date.class, arg2, arg3);
    }

    DefaultDateTypeAdapter(Class arg3) {
        super();
        this.dateFormats = new ArrayList();
        this.dateType = DefaultDateTypeAdapter.verifyDateType(arg3);
        int v1 = 2;
        this.dateFormats.add(DateFormat.getDateTimeInstance(v1, v1, Locale.US));
        if(!Locale.getDefault().equals(Locale.US)) {
            this.dateFormats.add(DateFormat.getDateTimeInstance(v1, v1));
        }

        if(JavaVersion.isJava9OrLater()) {
            this.dateFormats.add(PreJava9DateFormatProvider.getUSDateTimeFormat(v1, v1));
        }
    }

    DefaultDateTypeAdapter(Class arg2, int arg3) {
        super();
        this.dateFormats = new ArrayList();
        this.dateType = DefaultDateTypeAdapter.verifyDateType(arg2);
        this.dateFormats.add(DateFormat.getDateInstance(arg3, Locale.US));
        if(!Locale.getDefault().equals(Locale.US)) {
            this.dateFormats.add(DateFormat.getDateInstance(arg3));
        }

        if(JavaVersion.isJava9OrLater()) {
            this.dateFormats.add(PreJava9DateFormatProvider.getUSDateFormat(arg3));
        }
    }

    private Date deserializeToDate(String arg4) {
        Date v1_2;
        Date v2_1;
        List v0 = this.dateFormats;
        __monitor_enter(v0);
        try {
            Iterator v1 = this.dateFormats.iterator();
        label_4:
            if(v1.hasNext()) {
                Object v2 = v1.next();
                try {
                    v2_1 = ((DateFormat)v2).parse(arg4);
                }
                catch(ParseException ) {
                    goto label_4;
                }

                __monitor_exit(v0);
                return v2_1;
            }

            try {
                v1_2 = ISO8601Utils.parse(arg4, new ParsePosition(0));
            }
            catch(ParseException v1_1) {
                throw new JsonSyntaxException(arg4, ((Throwable)v1_1));
            }

            __monitor_exit(v0);
            return v1_2;
        label_21:
            __monitor_exit(v0);
        }
        catch(Throwable v4) {
            goto label_21;
        }

        throw v4;
    }

    public Object read(JsonReader arg1) throws IOException {
        return this.read(arg1);
    }

    public Date read(JsonReader arg4) throws IOException {
        if(arg4.peek() == JsonToken.NULL) {
            arg4.nextNull();
            return null;
        }

        Date v4 = this.deserializeToDate(arg4.nextString());
        if(this.dateType == Date.class) {
            return v4;
        }

        if(this.dateType == Timestamp.class) {
            return new Timestamp(v4.getTime());
        }

        if(this.dateType == java.sql.Date.class) {
            return new java.sql.Date(v4.getTime());
        }

        throw new AssertionError();
    }

    public String toString() {
        Object v0 = this.dateFormats.get(0);
        char v2 = ')';
        if((v0 instanceof SimpleDateFormat)) {
            return "DefaultDateTypeAdapter(" + ((SimpleDateFormat)v0).toPattern() + v2;
        }

        return "DefaultDateTypeAdapter(" + v0.getClass().getSimpleName() + v2;
    }

    private static Class verifyDateType(Class arg3) {
        if(arg3 != Date.class && arg3 != java.sql.Date.class && arg3 != Timestamp.class) {
            StringBuilder v1 = new StringBuilder();
            v1.append("Date type must be one of ");
            v1.append(Date.class);
            v1.append(", ");
            v1.append(Timestamp.class);
            v1.append(", or ");
            v1.append(java.sql.Date.class);
            v1.append(" but was ");
            v1.append(arg3);
            throw new IllegalArgumentException(v1.toString());
        }

        return arg3;
    }

    public void write(JsonWriter arg1, Object arg2) throws IOException {
        this.write(arg1, ((Date)arg2));
    }

    public void write(JsonWriter arg4, Date arg5) throws IOException {
        if(arg5 == null) {
            arg4.nullValue();
            return;
        }

        List v0 = this.dateFormats;
        __monitor_enter(v0);
        try {
            arg4.value(this.dateFormats.get(0).format(arg5));
            __monitor_exit(v0);
            return;
        label_13:
            __monitor_exit(v0);
        }
        catch(Throwable v4) {
            goto label_13;
        }

        throw v4;
    }
}

