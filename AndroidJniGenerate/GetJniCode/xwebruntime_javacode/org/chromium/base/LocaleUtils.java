package org.chromium.base;

import android.annotation.TargetApi;
import android.os.Build$VERSION;
import android.os.LocaleList;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale$Builder;
import java.util.Locale;
import java.util.Map;
import org.chromium.base.annotations.CalledByNative;

public class LocaleUtils {
    private static final Map LANGUAGE_MAP_FOR_ANDROID;
    private static final Map LANGUAGE_MAP_FOR_CHROMIUM;

    static {
        HashMap v0 = new HashMap();
        v0.put("iw", "he");
        v0.put("ji", "yi");
        v0.put("in", "id");
        v0.put("tl", "fil");
        LocaleUtils.LANGUAGE_MAP_FOR_CHROMIUM = Collections.unmodifiableMap(((Map)v0));
        v0 = new HashMap();
        v0.put("und", "");
        v0.put("fil", "tl");
        LocaleUtils.LANGUAGE_MAP_FOR_ANDROID = Collections.unmodifiableMap(((Map)v0));
    }

    private LocaleUtils() {
        super();
    }

    public static Locale forLanguageTag(String arg2) {
        if(Build$VERSION.SDK_INT >= 21) {
            return LocaleUtils.getUpdatedLocaleForAndroid(Locale.forLanguageTag(arg2));
        }

        return LocaleUtils.forLanguageTagCompat(arg2);
    }

    public static Locale forLanguageTagCompat(String arg5) {
        String[] v5 = arg5.split("-");
        if(v5.length == 0) {
            return new Locale("");
        }

        String v0 = LocaleUtils.getUpdatedLanguageForAndroid(v5[0]);
        int v2 = 3;
        int v3 = 2;
        if(v0.length() != v3 && v0.length() != v2 || (v0.equals("und"))) {
            return new Locale("");
        }

        if(v5.length == 1) {
            return new Locale(v0);
        }

        arg5 = v5[1];
        if(arg5.length() != v3 && arg5.length() != v2) {
            return new Locale(v0);
        }

        return new Locale(v0, arg5);
    }

    @CalledByNative private static String getDefaultCountryCode() {
        CommandLine v0 = CommandLine.getInstance();
        String v0_1 = v0.hasSwitch("default-country-code") ? v0.getSwitchValue("default-country-code") : Locale.getDefault().getCountry();
        return v0_1;
    }

    public static String getDefaultLocaleListString() {
        if(Build$VERSION.SDK_INT >= 24) {
            return LocaleUtils.toLanguageTags(LocaleList.getDefault());
        }

        return LocaleUtils.getDefaultLocaleString();
    }

    @CalledByNative public static String getDefaultLocaleString() {
        return LocaleUtils.toLanguageTag(Locale.getDefault());
    }

    public static String getUpdatedLanguageForAndroid(String arg1) {
        Object v1;
        Object v0 = LocaleUtils.LANGUAGE_MAP_FOR_ANDROID.get(arg1);
        if(v0 == null) {
        }
        else {
            v1 = v0;
        }

        return ((String)v1);
    }

    public static String getUpdatedLanguageForChromium(String arg1) {
        Object v1;
        Object v0 = LocaleUtils.LANGUAGE_MAP_FOR_CHROMIUM.get(arg1);
        if(v0 == null) {
        }
        else {
            v1 = v0;
        }

        return ((String)v1);
    }

    @TargetApi(value=21) @VisibleForTesting public static Locale getUpdatedLocaleForAndroid(Locale arg2) {
        Object v0 = LocaleUtils.LANGUAGE_MAP_FOR_ANDROID.get(arg2.getLanguage());
        if(v0 == null) {
            return arg2;
        }

        return new Locale$Builder().setLocale(arg2).setLanguage(((String)v0)).build();
    }

    @TargetApi(value=21) @VisibleForTesting public static Locale getUpdatedLocaleForChromium(Locale arg2) {
        Object v0 = LocaleUtils.LANGUAGE_MAP_FOR_CHROMIUM.get(arg2.getLanguage());
        if(v0 == null) {
            return arg2;
        }

        return new Locale$Builder().setLocale(arg2).setLanguage(((String)v0)).build();
    }

    public static String toLanguageTag(Locale arg3) {
        String v0 = LocaleUtils.getUpdatedLanguageForChromium(arg3.getLanguage());
        String v1 = arg3.getCountry();
        if((v0.equals("no")) && (v1.equals("NO")) && (arg3.getVariant().equals("NY"))) {
            return "nn-NO";
        }

        if(v1.isEmpty()) {
        }
        else {
            v0 = v0 + "-" + v1;
        }

        return v0;
    }

    @TargetApi(value=24) public static String toLanguageTags(LocaleList arg3) {
        ArrayList v0 = new ArrayList();
        int v1;
        for(v1 = 0; v1 < arg3.size(); ++v1) {
            v0.add(LocaleUtils.toLanguageTag(LocaleUtils.getUpdatedLocaleForChromium(arg3.get(v1))));
        }

        return TextUtils.join(",", ((Iterable)v0));
    }
}

