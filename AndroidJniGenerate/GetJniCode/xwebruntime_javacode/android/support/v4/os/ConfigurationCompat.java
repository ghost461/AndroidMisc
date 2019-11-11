package android.support.v4.os;

import android.content.res.Configuration;
import android.os.Build$VERSION;
import java.util.Locale;

public final class ConfigurationCompat {
    private ConfigurationCompat() {
        super();
    }

    public static LocaleListCompat getLocales(Configuration arg2) {
        if(Build$VERSION.SDK_INT >= 24) {
            return LocaleListCompat.wrap(arg2.getLocales());
        }

        return LocaleListCompat.create(new Locale[]{arg2.locale});
    }
}

