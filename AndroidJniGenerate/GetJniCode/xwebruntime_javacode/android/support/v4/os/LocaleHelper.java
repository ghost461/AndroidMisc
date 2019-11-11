package android.support.v4.os;

import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import java.util.Locale;

@RestrictTo(value={Scope.LIBRARY_GROUP}) final class LocaleHelper {
    LocaleHelper() {
        super();
    }

    static Locale forLanguageTag(String arg5) {
        String[] v0;
        int v1 = 2;
        if(arg5.contains("-")) {
            v0 = arg5.split("-");
            if(v0.length > v1) {
                return new Locale(v0[0], v0[1], v0[v1]);
            }
            else if(v0.length > 1) {
                return new Locale(v0[0], v0[1]);
            }
            else if(v0.length == 1) {
                return new Locale(v0[0]);
            }
        }
        else if(arg5.contains("_")) {
            v0 = arg5.split("_");
            if(v0.length > v1) {
                return new Locale(v0[0], v0[1], v0[v1]);
            }
            else if(v0.length > 1) {
                return new Locale(v0[0], v0[1]);
            }
            else if(v0.length == 1) {
                return new Locale(v0[0]);
            }
        }
        else {
            goto label_66;
        }

        StringBuilder v1_1 = new StringBuilder();
        v1_1.append("Can not parse language tag: [");
        v1_1.append(arg5);
        v1_1.append("]");
        throw new IllegalArgumentException(v1_1.toString());
    label_66:
        return new Locale(arg5);
    }

    static String toLanguageTag(Locale arg2) {
        StringBuilder v0 = new StringBuilder();
        v0.append(arg2.getLanguage());
        String v1 = arg2.getCountry();
        if(v1 != null && !v1.isEmpty()) {
            v0.append("-");
            v0.append(arg2.getCountry());
        }

        return v0.toString();
    }
}

