package android.support.v4.text;

import android.os.Build$VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import java.util.Locale;

public final class TextUtilsCompat {
    private static final String ARAB_SCRIPT_SUBTAG = "Arab";
    private static final String HEBR_SCRIPT_SUBTAG = "Hebr";
    @Deprecated public static final Locale ROOT;

    static {
        TextUtilsCompat.ROOT = new Locale("", "");
    }

    private TextUtilsCompat() {
        super();
    }

    private static int getLayoutDirectionFromFirstChar(@NonNull Locale arg1) {
        switch(Character.getDirectionality(arg1.getDisplayName(arg1).charAt(0))) {
            case 1: 
            case 2: {
                return 1;
            }
        }

        return 0;
    }

    public static int getLayoutDirectionFromLocale(@Nullable Locale arg2) {
        if(Build$VERSION.SDK_INT >= 17) {
            return TextUtils.getLayoutDirectionFromLocale(arg2);
        }

        if(arg2 != null && !arg2.equals(TextUtilsCompat.ROOT)) {
            String v0 = ICUCompat.maximizeAndGetScript(arg2);
            if(v0 == null) {
                return TextUtilsCompat.getLayoutDirectionFromFirstChar(arg2);
            }
            else {
                if(!v0.equalsIgnoreCase("Arab") && !v0.equalsIgnoreCase("Hebr")) {
                    return 0;
                }

                return 1;
            }
        }

        return 0;
    }

    @NonNull public static String htmlEncode(@NonNull String arg4) {
        if(Build$VERSION.SDK_INT >= 17) {
            return TextUtils.htmlEncode(arg4);
        }

        StringBuilder v0 = new StringBuilder();
        int v1;
        for(v1 = 0; v1 < arg4.length(); ++v1) {
            char v2 = arg4.charAt(v1);
            if(v2 == 34) {
                v0.append("&quot;");
            }
            else if(v2 == 60) {
                v0.append("&lt;");
            }
            else if(v2 != 62) {
                switch(v2) {
                    case 38: {
                        goto label_23;
                    }
                    case 39: {
                        goto label_20;
                    }
                }

                v0.append(v2);
                goto label_34;
            label_20:
                v0.append("&#39;");
                goto label_34;
            label_23:
                v0.append("&amp;");
            }
            else {
                v0.append("&gt;");
            }

        label_34:
        }

        return v0.toString();
    }
}

