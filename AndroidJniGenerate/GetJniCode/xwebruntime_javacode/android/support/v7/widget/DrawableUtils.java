package android.support.v7.widget;

import android.graphics.PorterDuff$Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable$ConstantState;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer$DrawableContainerState;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ScaleDrawable;
import android.os.Build$VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.graphics.drawable.DrawableWrapper;
import android.util.Log;
import java.lang.reflect.Field;

@RestrictTo(value={Scope.LIBRARY_GROUP}) public class DrawableUtils {
    public static final Rect INSETS_NONE = null;
    private static final String TAG = "DrawableUtils";
    private static final String VECTOR_DRAWABLE_CLAZZ_NAME = "android.graphics.drawable.VectorDrawable";
    private static Class sInsetsClazz;

    static {
        DrawableUtils.INSETS_NONE = new Rect();
        if(Build$VERSION.SDK_INT >= 18) {
            try {
                DrawableUtils.sInsetsClazz = Class.forName("android.graphics.Insets");
                return;
            }
            catch(ClassNotFoundException ) {
                return;
            }
        }
    }

    private DrawableUtils() {
        super();
    }

    public static boolean canSafelyMutateDrawable(@NonNull Drawable arg4) {
        int v1 = 15;
        if(Build$VERSION.SDK_INT < v1 && ((arg4 instanceof InsetDrawable))) {
            return 0;
        }

        if(Build$VERSION.SDK_INT < v1 && ((arg4 instanceof GradientDrawable))) {
            return 0;
        }

        if(Build$VERSION.SDK_INT < 17 && ((arg4 instanceof LayerDrawable))) {
            return 0;
        }

        if((arg4 instanceof DrawableContainer)) {
            Drawable$ConstantState v4 = arg4.getConstantState();
            if((v4 instanceof DrawableContainer$DrawableContainerState)) {
                Drawable[] v4_1 = ((DrawableContainer$DrawableContainerState)v4).getChildren();
                int v0 = v4_1.length;
                v1 = 0;
                while(v1 < v0) {
                    if(!DrawableUtils.canSafelyMutateDrawable(v4_1[v1])) {
                        return 0;
                    }
                    else {
                        ++v1;
                        continue;
                    }

                    return 1;
                }
            }
        }
        else if((arg4 instanceof DrawableWrapper)) {
            return DrawableUtils.canSafelyMutateDrawable(((DrawableWrapper)arg4).getWrappedDrawable());
        }
        else if((arg4 instanceof android.support.v7.graphics.drawable.DrawableWrapper)) {
            return DrawableUtils.canSafelyMutateDrawable(((android.support.v7.graphics.drawable.DrawableWrapper)arg4).getWrappedDrawable());
        }
        else if((arg4 instanceof ScaleDrawable)) {
            return DrawableUtils.canSafelyMutateDrawable(((ScaleDrawable)arg4).getDrawable());
        }

        return 1;
    }

    static void fixDrawable(@NonNull Drawable arg2) {
        if(Build$VERSION.SDK_INT == 21 && ("android.graphics.drawable.VectorDrawable".equals(arg2.getClass().getName()))) {
            DrawableUtils.fixVectorDrawableTinting(arg2);
        }
    }

    private static void fixVectorDrawableTinting(Drawable arg2) {
        int[] v0 = arg2.getState();
        if(v0 == null || v0.length == 0) {
            arg2.setState(ThemeUtils.CHECKED_STATE_SET);
        }
        else {
            arg2.setState(ThemeUtils.EMPTY_STATE_SET);
        }

        arg2.setState(v0);
    }

    public static Rect getOpticalBounds(Drawable arg10) {
        int v6_1;
        int v4;
        Rect v0;
        if(DrawableUtils.sInsetsClazz == null) {
            goto label_71;
        }

        try {
            arg10 = DrawableCompat.unwrap(arg10);
            Object v10 = arg10.getClass().getMethod("getOpticalInsets").invoke(arg10);
            if(v10 == null) {
                goto label_71;
            }

            v0 = new Rect();
            Field[] v1 = DrawableUtils.sInsetsClazz.getFields();
            int v3 = v1.length;
            v4 = 0;
            while(true) {
            label_17:
                if(v4 >= v3) {
                    return v0;
                }

                Field v5 = v1[v4];
                String v6 = v5.getName();
                int v8 = v6.hashCode();
                if(v8 != 0xAD8D9A2B) {
                    if(v8 != 115029) {
                        if(v8 != 0x32A007) {
                            if(v8 != 108511772) {
                                goto label_51;
                            }
                            else if(v6.equals("right")) {
                                v6_1 = 2;
                            }
                            else {
                                goto label_51;
                            }
                        }
                        else if(v6.equals("left")) {
                            v6_1 = 0;
                        }
                        else {
                            goto label_51;
                        }
                    }
                    else if(v6.equals("top")) {
                        v6_1 = 1;
                    }
                    else {
                        goto label_51;
                    }
                }
                else if(v6.equals("bottom")) {
                    v6_1 = 3;
                }
                else {
                label_51:
                    v6_1 = -1;
                }

                switch(v6_1) {
                    case 0: {
                        v0.left = v5.getInt(v10);
                        goto label_65;
                    }
                    case 1: {
                        v0.top = v5.getInt(v10);
                        goto label_65;
                    }
                    case 2: {
                        v0.right = v5.getInt(v10);
                        goto label_65;
                    }
                    case 3: {
                        v0.bottom = v5.getInt(v10);
                        goto label_65;
                    }
                    default: {
                        goto label_65;
                    }
                }
            }
        }
        catch(Exception ) {
            goto label_68;
        }

    label_65:
        ++v4;
        goto label_17;
        return v0;
    label_68:
        Log.e("DrawableUtils", "Couldn\'t obtain the optical insets. Ignoring.");
    label_71:
        return DrawableUtils.INSETS_NONE;
    }

    public static PorterDuff$Mode parseTintMode(int arg1, PorterDuff$Mode arg2) {
        if(arg1 == 3) {
            goto label_22;
        }

        if(arg1 == 5) {
            goto label_20;
        }

        if(arg1 == 9) {
            goto label_18;
        }

        switch(arg1) {
            case 14: {
                goto label_16;
            }
            case 15: {
                goto label_14;
            }
            case 16: {
                goto label_8;
            }
        }

        return arg2;
    label_8:
        if(Build$VERSION.SDK_INT >= 11) {
            arg2 = PorterDuff$Mode.valueOf("ADD");
        }

        return arg2;
    label_14:
        return PorterDuff$Mode.SCREEN;
    label_16:
        return PorterDuff$Mode.MULTIPLY;
    label_18:
        return PorterDuff$Mode.SRC_ATOP;
    label_20:
        return PorterDuff$Mode.SRC_IN;
    label_22:
        return PorterDuff$Mode.SRC_OVER;
    }
}

