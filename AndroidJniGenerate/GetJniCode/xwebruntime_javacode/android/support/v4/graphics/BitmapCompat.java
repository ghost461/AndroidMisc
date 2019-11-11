package android.support.v4.graphics;

import android.graphics.Bitmap;
import android.os.Build$VERSION;
import android.support.annotation.RequiresApi;

public final class BitmapCompat {
    @RequiresApi(value=18) class BitmapCompatApi18Impl extends BitmapCompatBaseImpl {
        BitmapCompatApi18Impl() {
            super();
        }

        public boolean hasMipMap(Bitmap arg1) {
            return arg1.hasMipMap();
        }

        public void setHasMipMap(Bitmap arg1, boolean arg2) {
            arg1.setHasMipMap(arg2);
        }
    }

    @RequiresApi(value=19) class BitmapCompatApi19Impl extends BitmapCompatApi18Impl {
        BitmapCompatApi19Impl() {
            super();
        }

        public int getAllocationByteCount(Bitmap arg1) {
            return arg1.getAllocationByteCount();
        }
    }

    class BitmapCompatBaseImpl {
        BitmapCompatBaseImpl() {
            super();
        }

        public int getAllocationByteCount(Bitmap arg1) {
            return arg1.getByteCount();
        }

        public boolean hasMipMap(Bitmap arg1) {
            return 0;
        }

        public void setHasMipMap(Bitmap arg1, boolean arg2) {
        }
    }

    static final BitmapCompatBaseImpl IMPL;

    static {
        if(Build$VERSION.SDK_INT >= 19) {
            BitmapCompat.IMPL = new BitmapCompatApi19Impl();
        }
        else if(Build$VERSION.SDK_INT >= 18) {
            BitmapCompat.IMPL = new BitmapCompatApi18Impl();
        }
        else {
            BitmapCompat.IMPL = new BitmapCompatBaseImpl();
        }
    }

    private BitmapCompat() {
        super();
    }

    public static int getAllocationByteCount(Bitmap arg1) {
        return BitmapCompat.IMPL.getAllocationByteCount(arg1);
    }

    public static boolean hasMipMap(Bitmap arg1) {
        return BitmapCompat.IMPL.hasMipMap(arg1);
    }

    public static void setHasMipMap(Bitmap arg1, boolean arg2) {
        BitmapCompat.IMPL.setHasMipMap(arg1, arg2);
    }
}

