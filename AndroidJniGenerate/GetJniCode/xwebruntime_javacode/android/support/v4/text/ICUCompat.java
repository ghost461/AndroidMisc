package android.support.v4.text;

import android.os.Build$VERSION;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import java.util.Locale;

public final class ICUCompat {
    @RequiresApi(value=21) class ICUCompatApi21Impl extends ICUCompatBaseImpl {
        ICUCompatApi21Impl() {
            super();
        }

        public String maximizeAndGetScript(Locale arg1) {
            return ICUCompatApi21.maximizeAndGetScript(arg1);
        }
    }

    class ICUCompatBaseImpl {
        ICUCompatBaseImpl() {
            super();
        }

        public String maximizeAndGetScript(Locale arg1) {
            return ICUCompatIcs.maximizeAndGetScript(arg1);
        }
    }

    private static final ICUCompatBaseImpl IMPL;

    static {
        ICUCompat.IMPL = Build$VERSION.SDK_INT >= 21 ? new ICUCompatApi21Impl() : new ICUCompatBaseImpl();
    }

    private ICUCompat() {
        super();
    }

    @Nullable public static String maximizeAndGetScript(Locale arg1) {
        return ICUCompat.IMPL.maximizeAndGetScript(arg1);
    }
}

