package android.support.v4.os;

import android.os.Build$VERSION;
import android.os.Trace;

public final class TraceCompat {
    private TraceCompat() {
        super();
    }

    public static void beginSection(String arg2) {
        if(Build$VERSION.SDK_INT >= 18) {
            Trace.beginSection(arg2);
        }
    }

    public static void endSection() {
        if(Build$VERSION.SDK_INT >= 18) {
            Trace.endSection();
        }
    }
}

