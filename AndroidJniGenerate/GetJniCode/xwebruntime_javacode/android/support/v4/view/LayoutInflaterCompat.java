package android.support.v4.view;

import android.content.Context;
import android.os.Build$VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater$Factory2;
import android.view.LayoutInflater$Factory;
import android.view.LayoutInflater;
import android.view.View;
import java.lang.reflect.Field;

public final class LayoutInflaterCompat {
    class Factory2Wrapper implements LayoutInflater$Factory2 {
        final LayoutInflaterFactory mDelegateFactory;

        Factory2Wrapper(LayoutInflaterFactory arg1) {
            super();
            this.mDelegateFactory = arg1;
        }

        public View onCreateView(View arg2, String arg3, Context arg4, AttributeSet arg5) {
            return this.mDelegateFactory.onCreateView(arg2, arg3, arg4, arg5);
        }

        public View onCreateView(String arg3, Context arg4, AttributeSet arg5) {
            return this.mDelegateFactory.onCreateView(null, arg3, arg4, arg5);
        }

        public String toString() {
            return this.getClass().getName() + "{" + this.mDelegateFactory + "}";
        }
    }

    @RequiresApi(value=21) class LayoutInflaterCompatApi21Impl extends LayoutInflaterCompatBaseImpl {
        LayoutInflaterCompatApi21Impl() {
            super();
        }

        public void setFactory(LayoutInflater arg2, LayoutInflaterFactory arg3) {
            LayoutInflater$Factory2 v0_1;
            if(arg3 != null) {
                Factory2Wrapper v0 = new Factory2Wrapper(arg3);
            }
            else {
                v0_1 = null;
            }

            arg2.setFactory2(v0_1);
        }

        public void setFactory2(LayoutInflater arg1, LayoutInflater$Factory2 arg2) {
            arg1.setFactory2(arg2);
        }
    }

    class LayoutInflaterCompatBaseImpl {
        LayoutInflaterCompatBaseImpl() {
            super();
        }

        public LayoutInflaterFactory getFactory(LayoutInflater arg2) {
            LayoutInflater$Factory v2 = arg2.getFactory();
            if((v2 instanceof Factory2Wrapper)) {
                return ((Factory2Wrapper)v2).mDelegateFactory;
            }

            return null;
        }

        public void setFactory(LayoutInflater arg2, LayoutInflaterFactory arg3) {
            Factory2Wrapper v0;
            if(arg3 != null) {
                v0 = new Factory2Wrapper(arg3);
            }
            else {
                LayoutInflater$Factory2 v0_1 = null;
            }

            this.setFactory2(arg2, ((LayoutInflater$Factory2)v0));
        }

        public void setFactory2(LayoutInflater arg3, LayoutInflater$Factory2 arg4) {
            arg3.setFactory2(arg4);
            LayoutInflater$Factory v0 = arg3.getFactory();
            if((v0 instanceof LayoutInflater$Factory2)) {
                LayoutInflaterCompat.forceSetFactory2(arg3, ((LayoutInflater$Factory2)v0));
            }
            else {
                LayoutInflaterCompat.forceSetFactory2(arg3, arg4);
            }
        }
    }

    static final LayoutInflaterCompatBaseImpl IMPL = null;
    private static final String TAG = "LayoutInflaterCompatHC";
    private static boolean sCheckedField;
    private static Field sLayoutInflaterFactory2Field;

    static {
        LayoutInflaterCompat.IMPL = Build$VERSION.SDK_INT >= 21 ? new LayoutInflaterCompatApi21Impl() : new LayoutInflaterCompatBaseImpl();
    }

    private LayoutInflaterCompat() {
        super();
    }

    static void forceSetFactory2(LayoutInflater arg5, LayoutInflater$Factory2 arg6) {
        if(!LayoutInflaterCompat.sCheckedField) {
            try {
                LayoutInflaterCompat.sLayoutInflaterFactory2Field = LayoutInflater.class.getDeclaredField("mFactory2");
                LayoutInflaterCompat.sLayoutInflaterFactory2Field.setAccessible(true);
            }
            catch(NoSuchFieldException v1) {
                Log.e("LayoutInflaterCompatHC", "forceSetFactory2 Could not find field \'mFactory2\' on class " + LayoutInflater.class.getName() + "; inflation may have unexpected results.", ((Throwable)v1));
            }

            LayoutInflaterCompat.sCheckedField = true;
        }

        if(LayoutInflaterCompat.sLayoutInflaterFactory2Field != null) {
            try {
                LayoutInflaterCompat.sLayoutInflaterFactory2Field.set(arg5, arg6);
            }
            catch(IllegalAccessException v6) {
                Log.e("LayoutInflaterCompatHC", "forceSetFactory2 could not set the Factory2 on LayoutInflater " + arg5 + "; inflation may have unexpected results.", ((Throwable)v6));
            }
        }
    }

    @Deprecated public static LayoutInflaterFactory getFactory(LayoutInflater arg1) {
        return LayoutInflaterCompat.IMPL.getFactory(arg1);
    }

    @Deprecated public static void setFactory(@NonNull LayoutInflater arg1, @NonNull LayoutInflaterFactory arg2) {
        LayoutInflaterCompat.IMPL.setFactory(arg1, arg2);
    }

    public static void setFactory2(@NonNull LayoutInflater arg1, @NonNull LayoutInflater$Factory2 arg2) {
        LayoutInflaterCompat.IMPL.setFactory2(arg1, arg2);
    }
}

