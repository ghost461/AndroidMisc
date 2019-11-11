package com.google.gson.internal.bind.util;

import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class ISO8601Utils {
    private static final TimeZone TIMEZONE_UTC = null;
    private static final String UTC_ID = "UTC";

    static {
        ISO8601Utils.TIMEZONE_UTC = TimeZone.getTimeZone("UTC");
    }

    public ISO8601Utils() {
        super();
    }

    private static boolean checkOffset(String arg1, int arg2, char arg3) {
        boolean v1 = arg2 >= arg1.length() || arg1.charAt(arg2) != arg3 ? false : true;
        return v1;
    }

    public static String format(Date arg2) {
        return ISO8601Utils.format(arg2, false, ISO8601Utils.TIMEZONE_UTC);
    }

    public static String format(Date arg5, boolean arg6, TimeZone arg7) {
        GregorianCalendar v0 = new GregorianCalendar(arg7, Locale.US);
        ((Calendar)v0).setTime(arg5);
        int v5 = "yyyy-MM-ddThh:mm:ss".length();
        int v1 = arg6 ? ".sss".length() : 0;
        v5 += v1;
        String v1_1 = arg7.getRawOffset() == 0 ? "Z" : "+hh:mm";
        v1 = v1_1.length();
        StringBuilder v1_2 = new StringBuilder(v5 + v1);
        ISO8601Utils.padInt(v1_2, ((Calendar)v0).get(1), "yyyy".length());
        char v2 = '-';
        v1_2.append(v2);
        ISO8601Utils.padInt(v1_2, ((Calendar)v0).get(2) + 1, "MM".length());
        v1_2.append(v2);
        ISO8601Utils.padInt(v1_2, ((Calendar)v0).get(5), "dd".length());
        v1_2.append('T');
        ISO8601Utils.padInt(v1_2, ((Calendar)v0).get(11), "hh".length());
        char v5_1 = ':';
        v1_2.append(v5_1);
        ISO8601Utils.padInt(v1_2, ((Calendar)v0).get(12), "mm".length());
        v1_2.append(v5_1);
        ISO8601Utils.padInt(v1_2, ((Calendar)v0).get(13), "ss".length());
        if(arg6) {
            v1_2.append('.');
            ISO8601Utils.padInt(v1_2, ((Calendar)v0).get(14), "sss".length());
        }

        int v6 = arg7.getOffset(((Calendar)v0).getTimeInMillis());
        if(v6 != 0) {
            int v7 = v6 / 60000;
            int v0_1 = Math.abs(v7 / 60);
            v7 = Math.abs(v7 % 60);
            if(v6 < 0) {
            }
            else {
                v2 = '+';
            }

            v1_2.append(v2);
            ISO8601Utils.padInt(v1_2, v0_1, "hh".length());
            v1_2.append(v5_1);
            ISO8601Utils.padInt(v1_2, v7, "mm".length());
        }
        else {
            v1_2.append('Z');
        }

        return v1_2.toString();
    }

    public static String format(Date arg1, boolean arg2) {
        return ISO8601Utils.format(arg1, arg2, ISO8601Utils.TIMEZONE_UTC);
    }

    private static int indexOfNonDigit(String arg2, int arg3) {
        while(true) {
            if(arg3 >= arg2.length()) {
                goto label_11;
            }

            int v0 = arg2.charAt(arg3);
            if(v0 >= 0x30) {
                if(v0 > 57) {
                }
                else {
                    ++arg3;
                    continue;
                }
            }

            return arg3;
        }

        return arg3;
    label_11:
        return arg2.length();
    }

    private static void padInt(StringBuilder arg1, int arg2, int arg3) {
        String v2 = Integer.toString(arg2);
        arg3 -= v2.length();
        while(arg3 > 0) {
            arg1.append('0');
            --arg3;
        }

        arg1.append(v2);
    }

    public static Date parse(String arg18, ParsePosition arg19) throws ParseException {
        IndexOutOfBoundsException v3_1;
        StringBuilder v4_1;
        TimeZone v10_1;
        int v12;
        int v13_1;
        int v14;
        int v8_1;
        GregorianCalendar v5_1;
        String v1 = arg18;
        ParsePosition v2 = arg19;
        try {
            int v3 = arg19.getIndex();
            int v4 = v3 + 4;
            v3 = ISO8601Utils.parseInt(v1, v3, v4);
            char v5 = '-';
            if(ISO8601Utils.checkOffset(v1, v4, v5)) {
                ++v4;
            }

            int v6 = v4 + 2;
            v4 = ISO8601Utils.parseInt(v1, v4, v6);
            if(ISO8601Utils.checkOffset(v1, v6, v5)) {
                ++v6;
            }

            int v7 = v6 + 2;
            v6 = ISO8601Utils.parseInt(v1, v6, v7);
            boolean v8 = ISO8601Utils.checkOffset(v1, v7, 'T');
            if(!v8 && arg18.length() <= v7) {
                v5_1 = new GregorianCalendar(v3, v4 - 1, v6);
                v2.setIndex(v7);
                return ((Calendar)v5_1).getTime();
            }

            int v10 = 43;
            int v11 = 90;
            if(v8) {
                ++v7;
                v8_1 = v7 + 2;
                v7 = ISO8601Utils.parseInt(v1, v7, v8_1);
                char v13 = ':';
                if(ISO8601Utils.checkOffset(v1, v8_1, v13)) {
                    ++v8_1;
                }

                v14 = v8_1 + 2;
                v8_1 = ISO8601Utils.parseInt(v1, v8_1, v14);
                if(ISO8601Utils.checkOffset(v1, v14, v13)) {
                    ++v14;
                }

                if(arg18.length() > v14) {
                    v13_1 = v1.charAt(v14);
                    if(v13_1 != v11 && v13_1 != v10 && v13_1 != v5) {
                        v13_1 = v14 + 2;
                        v14 = ISO8601Utils.parseInt(v1, v14, v13_1);
                        if(v14 > 59 && v14 < 0x3F) {
                            v14 = 59;
                        }

                        if(ISO8601Utils.checkOffset(v1, v13_1, '.')) {
                            ++v13_1;
                            int v15 = ISO8601Utils.indexOfNonDigit(v1, v13_1 + 1);
                            v12 = Math.min(v15, v13_1 + 3);
                            int v16 = ISO8601Utils.parseInt(v1, v13_1, v12);
                            switch(v12 - v13_1) {
                                case 1: {
                                    v16 *= 100;
                                    break;
                                }
                                case 2: {
                                    v16 *= 10;
                                    break;
                                }
                            }

                            v12 = v16;
                            v13_1 = v15;
                            goto label_84;
                        }

                        v12 = 0;
                        goto label_84;
                    }
                }

                v13_1 = v14;
                goto label_82;
            }
            else {
                v13_1 = v7;
                v7 = 0;
                v8_1 = 0;
            label_82:
                v12 = 0;
                v14 = 0;
            }

        label_84:
            if(arg18.length() <= v13_1) {
                throw new IllegalArgumentException("No time zone indicator");
            }

            char v15_1 = v1.charAt(v13_1);
            int v5_2 = 5;
            if(v15_1 == v11) {
                v10_1 = ISO8601Utils.TIMEZONE_UTC;
                ++v13_1;
            }
            else {
                if(v15_1 != v10) {
                    if(v15_1 == 45) {
                    }
                    else {
                        v4_1 = new StringBuilder();
                        v4_1.append("Invalid time zone indicator \'");
                        v4_1.append(v15_1);
                        v4_1.append("\'");
                        throw new IndexOutOfBoundsException(v4_1.toString());
                    }
                }

                String v10_2 = v1.substring(v13_1);
                if(v10_2.length() >= v5_2) {
                }
                else {
                    v10_2 = v10_2 + "00";
                }

                v13_1 += v10_2.length();
                if(!"+0000".equals(v10_2)) {
                    if("+00:00".equals(v10_2)) {
                    }
                    else {
                        v10_2 = "GMT" + v10_2;
                        TimeZone v11_2 = TimeZone.getTimeZone(v10_2);
                        String v15_2 = v11_2.getID();
                        if(!v15_2.equals(v10_2) && !v15_2.replace(":", "").equals(v10_2)) {
                            v4_1 = new StringBuilder();
                            v4_1.append("Mismatching time zone indicator: ");
                            v4_1.append(v10_2);
                            v4_1.append(" given, resolves to ");
                            v4_1.append(v11_2.getID());
                            throw new IndexOutOfBoundsException(v4_1.toString());
                        }

                        v10_1 = v11_2;
                        goto label_162;
                    }
                }

                v10_1 = ISO8601Utils.TIMEZONE_UTC;
            }

        label_162:
            v5_1 = new GregorianCalendar(v10_1);
            ((Calendar)v5_1).setLenient(false);
            ((Calendar)v5_1).set(1, v3);
            ((Calendar)v5_1).set(2, v4 - 1);
            ((Calendar)v5_1).set(5, v6);
            ((Calendar)v5_1).set(11, v7);
            ((Calendar)v5_1).set(12, v8_1);
            ((Calendar)v5_1).set(13, v14);
            ((Calendar)v5_1).set(14, v12);
            v2.setIndex(v13_1);
            return ((Calendar)v5_1).getTime();
        }
        catch(IllegalArgumentException v0) {
            IllegalArgumentException v3_3 = v0;
        }
        catch(NumberFormatException v0_1) {
            NumberFormatException v3_2 = v0_1;
        }
        catch(IndexOutOfBoundsException v0_2) {
            v3_1 = v0_2;
        }

        v1 = v1 == null ? null : '\"' + v1 + '\"';
        String v4_2 = ((Exception)v3_1).getMessage();
        if(v4_2 == null || (v4_2.isEmpty())) {
            v4_2 = "(" + v3_1.getClass().getName() + ")";
        }

        StringBuilder v6_1 = new StringBuilder();
        v6_1.append("Failed to parse date [");
        v6_1.append(v1);
        v6_1.append("]: ");
        v6_1.append(v4_2);
        ParseException v5_3 = new ParseException(v6_1.toString(), arg19.getIndex());
        v5_3.initCause(((Throwable)v3_1));
        throw v5_3;
    }

    private static int parseInt(String arg4, int arg5, int arg6) throws NumberFormatException {
        StringBuilder v1_1;
        int v2;
        int v0;
        if(arg5 >= 0 && arg6 <= arg4.length()) {
            if(arg5 > arg6) {
            }
            else {
                int v1 = 10;
                if(arg5 < arg6) {
                    v0 = arg5 + 1;
                    v2 = Character.digit(arg4.charAt(arg5), v1);
                    if(v2 < 0) {
                        v1_1 = new StringBuilder();
                        v1_1.append("Invalid number: ");
                        v1_1.append(arg4.substring(arg5, arg6));
                        throw new NumberFormatException(v1_1.toString());
                    }
                    else {
                        v2 = -v2;
                    }
                }
                else {
                    v0 = arg5;
                    v2 = 0;
                }

                while(v0 < arg6) {
                    int v3 = v0 + 1;
                    v0 = Character.digit(arg4.charAt(v0), v1);
                    if(v0 < 0) {
                        v1_1 = new StringBuilder();
                        v1_1.append("Invalid number: ");
                        v1_1.append(arg4.substring(arg5, arg6));
                        throw new NumberFormatException(v1_1.toString());
                    }

                    v2 = v2 * 10 - v0;
                    v0 = v3;
                }

                return -v2;
            }
        }

        throw new NumberFormatException(arg4);
    }
}

