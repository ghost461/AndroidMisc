package android.support.v4.media;

import android.support.annotation.RequiresApi;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@RequiresApi(value=21) class ParceledListSliceAdapterApi21 {
    private static Constructor sConstructor;

    static {
        try {
            ParceledListSliceAdapterApi21.sConstructor = Class.forName("android.content.pm.ParceledListSlice").getConstructor(List.class);
        }
        catch(NoSuchMethodException v0) {
            ThrowableExtension.printStackTrace(((Throwable)v0));
        }
    }

    ParceledListSliceAdapterApi21() {
        super();
    }

    static Object newInstance(List arg3) {
        Object v3_1;
        try {
            v3_1 = ParceledListSliceAdapterApi21.sConstructor.newInstance(arg3);
        }
        catch(InvocationTargetException v3) {
            ThrowableExtension.printStackTrace(((Throwable)v3));
            v3_1 = null;
        }

        return v3_1;
    }
}

