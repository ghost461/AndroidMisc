package android.support.v4.util;

import android.os.Build$VERSION;
import android.support.annotation.RequiresApi;
import java.util.Objects;

public class ObjectsCompat {
    class android.support.v4.util.ObjectsCompat$1 {
    }

    @RequiresApi(value=19) class ImplApi19 extends ImplBase {
        ImplApi19(android.support.v4.util.ObjectsCompat$1 arg1) {
            this();
        }

        private ImplApi19() {
            super(null);
        }

        public boolean equals(Object arg1, Object arg2) {
            return Objects.equals(arg1, arg2);
        }
    }

    class ImplBase {
        ImplBase(android.support.v4.util.ObjectsCompat$1 arg1) {
            this();
        }

        private ImplBase() {
            super();
        }

        public boolean equals(Object arg1, Object arg2) {
            boolean v1;
            if(arg1 != arg2) {
                if(arg1 != null && (arg1.equals(arg2))) {
                    goto label_7;
                }

                v1 = false;
            }
            else {
            label_7:
                v1 = true;
            }

            return v1;
        }
    }

    private static final ImplBase IMPL;

    static {
        android.support.v4.util.ObjectsCompat$1 v1 = null;
        ObjectsCompat.IMPL = Build$VERSION.SDK_INT >= 19 ? new ImplApi19(v1) : new ImplBase(v1);
    }

    private ObjectsCompat() {
        super();
    }

    public static boolean equals(Object arg1, Object arg2) {
        return ObjectsCompat.IMPL.equals(arg1, arg2);
    }
}

