package android.support.v4.graphics;

import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v4.util.Pair;

class PaintCompatApi14 {
    private static final String EM_STRING = "m";
    private static final String TOFU_STRING = "\uDB3F\uDFFD";
    private static final ThreadLocal sRectThreadLocal;

    static {
        PaintCompatApi14.sRectThreadLocal = new ThreadLocal();
    }

    PaintCompatApi14() {
        super();
    }

    static boolean hasGlyph(@NonNull Paint arg8, @NonNull String arg9) {
        int v0 = arg9.length();
        if(v0 == 1 && (Character.isWhitespace(arg9.charAt(0)))) {
            return 1;
        }

        float v3 = arg8.measureText("\uDB3F\uDFFD");
        float v4 = arg8.measureText("m");
        float v5 = arg8.measureText(arg9);
        float v6 = 0f;
        if(v5 == 0f) {
            return 0;
        }

        if(arg9.codePointCount(0, arg9.length()) > 1) {
            if(v5 > v4 * 2f) {
                return 0;
            }
            else {
                int v4_1;
                for(v4_1 = 0; v4_1 < v0; v4_1 = v7) {
                    int v7 = Character.charCount(arg9.codePointAt(v4_1)) + v4_1;
                    v6 += arg8.measureText(arg9, v4_1, v7);
                }

                if(v5 >= v6) {
                    return 0;
                }
            }
        }

        if(v5 != v3) {
            return 1;
        }

        Pair v3_1 = PaintCompatApi14.obtainEmptyRects();
        arg8.getTextBounds("\uDB3F\uDFFD", 0, "\uDB3F\uDFFD".length(), v3_1.first);
        arg8.getTextBounds(arg9, 0, v0, v3_1.second);
        return v3_1.first.equals(v3_1.second) ^ 1;
    }

    private static Pair obtainEmptyRects() {
        Object v0 = PaintCompatApi14.sRectThreadLocal.get();
        if(v0 == null) {
            Pair v0_1 = new Pair(new Rect(), new Rect());
            PaintCompatApi14.sRectThreadLocal.set(v0_1);
        }
        else {
            ((Pair)v0).first.setEmpty();
            ((Pair)v0).second.setEmpty();
        }

        return ((Pair)v0);
    }
}

