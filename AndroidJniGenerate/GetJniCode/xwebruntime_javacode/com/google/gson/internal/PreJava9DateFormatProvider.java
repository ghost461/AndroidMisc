package com.google.gson.internal;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class PreJava9DateFormatProvider {
    public PreJava9DateFormatProvider() {
        super();
    }

    private static String getDateFormatPattern(int arg3) {
        switch(arg3) {
            case 0: {
                return "EEEE, MMMM d, y";
            }
            case 1: {
                return "MMMM d, y";
            }
            case 2: {
                return "MMM d, y";
            }
            case 3: {
                return "M/d/yy";
            }
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("Unknown DateFormat style: ");
        v1.append(arg3);
        throw new IllegalArgumentException(v1.toString());
        return "M/d/yy";
    }

    private static String getDatePartOfDateTimePattern(int arg3) {
        switch(arg3) {
            case 0: {
                return "EEEE, MMMM d, yyyy";
            }
            case 1: {
                return "MMMM d, yyyy";
            }
            case 2: {
                return "MMM d, yyyy";
            }
            case 3: {
                return "M/d/yy";
            }
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("Unknown DateFormat style: ");
        v1.append(arg3);
        throw new IllegalArgumentException(v1.toString());
        return "M/d/yy";
    }

    private static String getTimePartOfDateTimePattern(int arg3) {
        switch(arg3) {
            case 0: 
            case 1: {
                return "h:mm:ss a z";
            }
            case 2: {
                return "h:mm:ss a";
            }
            case 3: {
                return "h:mm a";
            }
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("Unknown DateFormat style: ");
        v1.append(arg3);
        throw new IllegalArgumentException(v1.toString());
        return "h:mm a";
    }

    public static DateFormat getUSDateFormat(int arg2) {
        return new SimpleDateFormat(PreJava9DateFormatProvider.getDateFormatPattern(arg2), Locale.US);
    }

    public static DateFormat getUSDateTimeFormat(int arg1, int arg2) {
        StringBuilder v0 = new StringBuilder();
        v0.append(PreJava9DateFormatProvider.getDatePartOfDateTimePattern(arg1));
        v0.append(" ");
        v0.append(PreJava9DateFormatProvider.getTimePartOfDateTimePattern(arg2));
        return new SimpleDateFormat(v0.toString(), Locale.US);
    }
}

