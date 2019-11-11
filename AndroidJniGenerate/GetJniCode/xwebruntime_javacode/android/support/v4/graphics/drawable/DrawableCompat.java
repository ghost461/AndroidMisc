package android.support.v4.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources$Theme;
import android.content.res.Resources;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff$Mode;
import android.graphics.drawable.Drawable$ConstantState;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer$DrawableContainerState;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.InsetDrawable;
import android.os.Build$VERSION;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import java.io.IOException;
import java.lang.reflect.Method;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public final class DrawableCompat {
    @RequiresApi(value=17) class DrawableCompatApi17Impl extends DrawableCompatBaseImpl {
        private static final String TAG = "DrawableCompatApi17";
        private static Method sGetLayoutDirectionMethod;
        private static boolean sGetLayoutDirectionMethodFetched;
        private static Method sSetLayoutDirectionMethod;
        private static boolean sSetLayoutDirectionMethodFetched;

        DrawableCompatApi17Impl() {
            super();
        }

        public int getLayoutDirection(Drawable arg6) {
            if(!DrawableCompatApi17Impl.sGetLayoutDirectionMethodFetched) {
                try {
                    DrawableCompatApi17Impl.sGetLayoutDirectionMethod = Drawable.class.getDeclaredMethod("getLayoutDirection");
                    DrawableCompatApi17Impl.sGetLayoutDirectionMethod.setAccessible(true);
                }
                catch(NoSuchMethodException v2) {
                    Log.i("DrawableCompatApi17", "Failed to retrieve getLayoutDirection() method", ((Throwable)v2));
                }

                DrawableCompatApi17Impl.sGetLayoutDirectionMethodFetched = true;
            }

            if(DrawableCompatApi17Impl.sGetLayoutDirectionMethod != null) {
                try {
                    return DrawableCompatApi17Impl.sGetLayoutDirectionMethod.invoke(arg6).intValue();
                }
                catch(Exception v6) {
                    Log.i("DrawableCompatApi17", "Failed to invoke getLayoutDirection() via reflection", ((Throwable)v6));
                    DrawableCompatApi17Impl.sGetLayoutDirectionMethod = null;
                }
            }

            return 0;
        }

        public boolean setLayoutDirection(Drawable arg7, int arg8) {
            if(!DrawableCompatApi17Impl.sSetLayoutDirectionMethodFetched) {
                try {
                    DrawableCompatApi17Impl.sSetLayoutDirectionMethod = Drawable.class.getDeclaredMethod("setLayoutDirection", Integer.TYPE);
                    DrawableCompatApi17Impl.sSetLayoutDirectionMethod.setAccessible(true);
                }
                catch(NoSuchMethodException v0) {
                    Log.i("DrawableCompatApi17", "Failed to retrieve setLayoutDirection(int) method", ((Throwable)v0));
                }

                DrawableCompatApi17Impl.sSetLayoutDirectionMethodFetched = true;
            }

            if(DrawableCompatApi17Impl.sSetLayoutDirectionMethod != null) {
                try {
                    DrawableCompatApi17Impl.sSetLayoutDirectionMethod.invoke(arg7, Integer.valueOf(arg8));
                    return 1;
                }
                catch(Exception v7) {
                    Log.i("DrawableCompatApi17", "Failed to invoke setLayoutDirection(int) via reflection", ((Throwable)v7));
                    DrawableCompatApi17Impl.sSetLayoutDirectionMethod = null;
                }
            }

            return 0;
        }
    }

    @RequiresApi(value=19) class DrawableCompatApi19Impl extends DrawableCompatApi17Impl {
        DrawableCompatApi19Impl() {
            super();
        }

        public int getAlpha(Drawable arg1) {
            return arg1.getAlpha();
        }

        public boolean isAutoMirrored(Drawable arg1) {
            return arg1.isAutoMirrored();
        }

        public void setAutoMirrored(Drawable arg1, boolean arg2) {
            arg1.setAutoMirrored(arg2);
        }

        public Drawable wrap(Drawable arg2) {
            if(!(arg2 instanceof TintAwareDrawable)) {
                return new DrawableWrapperApi19(arg2);
            }

            return arg2;
        }
    }

    @RequiresApi(value=21) class DrawableCompatApi21Impl extends DrawableCompatApi19Impl {
        DrawableCompatApi21Impl() {
            super();
        }

        public void applyTheme(Drawable arg1, Resources$Theme arg2) {
            arg1.applyTheme(arg2);
        }

        public boolean canApplyTheme(Drawable arg1) {
            return arg1.canApplyTheme();
        }

        public void clearColorFilter(Drawable arg4) {
            arg4.clearColorFilter();
            if((arg4 instanceof InsetDrawable)) {
                this.clearColorFilter(((InsetDrawable)arg4).getDrawable());
            }
            else if((arg4 instanceof DrawableWrapper)) {
                this.clearColorFilter(((DrawableWrapper)arg4).getWrappedDrawable());
            }
            else if((arg4 instanceof DrawableContainer)) {
                Drawable$ConstantState v4 = ((DrawableContainer)arg4).getConstantState();
                if(v4 != null) {
                    int v0 = 0;
                    int v1 = ((DrawableContainer$DrawableContainerState)v4).getChildCount();
                    while(v0 < v1) {
                        Drawable v2 = ((DrawableContainer$DrawableContainerState)v4).getChild(v0);
                        if(v2 != null) {
                            this.clearColorFilter(v2);
                        }

                        ++v0;
                    }
                }
            }
        }

        public ColorFilter getColorFilter(Drawable arg1) {
            return arg1.getColorFilter();
        }

        public void inflate(Drawable arg1, Resources arg2, XmlPullParser arg3, AttributeSet arg4, Resources$Theme arg5) throws IOException, XmlPullParserException {
            arg1.inflate(arg2, arg3, arg4, arg5);
        }

        public void setHotspot(Drawable arg1, float arg2, float arg3) {
            arg1.setHotspot(arg2, arg3);
        }

        public void setHotspotBounds(Drawable arg1, int arg2, int arg3, int arg4, int arg5) {
            arg1.setHotspotBounds(arg2, arg3, arg4, arg5);
        }

        public void setTint(Drawable arg1, int arg2) {
            arg1.setTint(arg2);
        }

        public void setTintList(Drawable arg1, ColorStateList arg2) {
            arg1.setTintList(arg2);
        }

        public void setTintMode(Drawable arg1, PorterDuff$Mode arg2) {
            arg1.setTintMode(arg2);
        }

        public Drawable wrap(Drawable arg2) {
            if(!(arg2 instanceof TintAwareDrawable)) {
                return new DrawableWrapperApi21(arg2);
            }

            return arg2;
        }
    }

    @RequiresApi(value=23) class DrawableCompatApi23Impl extends DrawableCompatApi21Impl {
        DrawableCompatApi23Impl() {
            super();
        }

        public void clearColorFilter(Drawable arg1) {
            arg1.clearColorFilter();
        }

        public int getLayoutDirection(Drawable arg1) {
            return arg1.getLayoutDirection();
        }

        public boolean setLayoutDirection(Drawable arg1, int arg2) {
            return arg1.setLayoutDirection(arg2);
        }

        public Drawable wrap(Drawable arg1) {
            return arg1;
        }
    }

    class DrawableCompatBaseImpl {
        DrawableCompatBaseImpl() {
            super();
        }

        public void applyTheme(Drawable arg1, Resources$Theme arg2) {
        }

        public boolean canApplyTheme(Drawable arg1) {
            return 0;
        }

        public void clearColorFilter(Drawable arg1) {
            arg1.clearColorFilter();
        }

        public int getAlpha(Drawable arg1) {
            return 0;
        }

        public ColorFilter getColorFilter(Drawable arg1) {
            return null;
        }

        public int getLayoutDirection(Drawable arg1) {
            return 0;
        }

        public void inflate(Drawable arg1, Resources arg2, XmlPullParser arg3, AttributeSet arg4, Resources$Theme arg5) throws IOException, XmlPullParserException {
            arg1.inflate(arg2, arg3, arg4);
        }

        public boolean isAutoMirrored(Drawable arg1) {
            return 0;
        }

        public void jumpToCurrentState(Drawable arg1) {
            arg1.jumpToCurrentState();
        }

        public void setAutoMirrored(Drawable arg1, boolean arg2) {
        }

        public void setHotspot(Drawable arg1, float arg2, float arg3) {
        }

        public void setHotspotBounds(Drawable arg1, int arg2, int arg3, int arg4, int arg5) {
        }

        public boolean setLayoutDirection(Drawable arg1, int arg2) {
            return 0;
        }

        public void setTint(Drawable arg2, int arg3) {
            if((arg2 instanceof TintAwareDrawable)) {
                ((TintAwareDrawable)arg2).setTint(arg3);
            }
        }

        public void setTintList(Drawable arg2, ColorStateList arg3) {
            if((arg2 instanceof TintAwareDrawable)) {
                ((TintAwareDrawable)arg2).setTintList(arg3);
            }
        }

        public void setTintMode(Drawable arg2, PorterDuff$Mode arg3) {
            if((arg2 instanceof TintAwareDrawable)) {
                ((TintAwareDrawable)arg2).setTintMode(arg3);
            }
        }

        public Drawable wrap(Drawable arg2) {
            if(!(arg2 instanceof TintAwareDrawable)) {
                return new DrawableWrapperApi14(arg2);
            }

            return arg2;
        }
    }

    static final DrawableCompatBaseImpl IMPL;

    static {
        if(Build$VERSION.SDK_INT >= 23) {
            DrawableCompat.IMPL = new DrawableCompatApi23Impl();
        }
        else if(Build$VERSION.SDK_INT >= 21) {
            DrawableCompat.IMPL = new DrawableCompatApi21Impl();
        }
        else if(Build$VERSION.SDK_INT >= 19) {
            DrawableCompat.IMPL = new DrawableCompatApi19Impl();
        }
        else if(Build$VERSION.SDK_INT >= 17) {
            DrawableCompat.IMPL = new DrawableCompatApi17Impl();
        }
        else {
            DrawableCompat.IMPL = new DrawableCompatBaseImpl();
        }
    }

    private DrawableCompat() {
        super();
    }

    public static void applyTheme(@NonNull Drawable arg1, @NonNull Resources$Theme arg2) {
        DrawableCompat.IMPL.applyTheme(arg1, arg2);
    }

    public static boolean canApplyTheme(@NonNull Drawable arg1) {
        return DrawableCompat.IMPL.canApplyTheme(arg1);
    }

    public static void clearColorFilter(@NonNull Drawable arg1) {
        DrawableCompat.IMPL.clearColorFilter(arg1);
    }

    public static int getAlpha(@NonNull Drawable arg1) {
        return DrawableCompat.IMPL.getAlpha(arg1);
    }

    public static ColorFilter getColorFilter(@NonNull Drawable arg1) {
        return DrawableCompat.IMPL.getColorFilter(arg1);
    }

    public static int getLayoutDirection(@NonNull Drawable arg1) {
        return DrawableCompat.IMPL.getLayoutDirection(arg1);
    }

    public static void inflate(@NonNull Drawable arg6, @NonNull Resources arg7, @NonNull XmlPullParser arg8, @NonNull AttributeSet arg9, @Nullable Resources$Theme arg10) throws XmlPullParserException, IOException {
        DrawableCompat.IMPL.inflate(arg6, arg7, arg8, arg9, arg10);
    }

    public static boolean isAutoMirrored(@NonNull Drawable arg1) {
        return DrawableCompat.IMPL.isAutoMirrored(arg1);
    }

    public static void jumpToCurrentState(@NonNull Drawable arg1) {
        DrawableCompat.IMPL.jumpToCurrentState(arg1);
    }

    public static void setAutoMirrored(@NonNull Drawable arg1, boolean arg2) {
        DrawableCompat.IMPL.setAutoMirrored(arg1, arg2);
    }

    public static void setHotspot(@NonNull Drawable arg1, float arg2, float arg3) {
        DrawableCompat.IMPL.setHotspot(arg1, arg2, arg3);
    }

    public static void setHotspotBounds(@NonNull Drawable arg6, int arg7, int arg8, int arg9, int arg10) {
        DrawableCompat.IMPL.setHotspotBounds(arg6, arg7, arg8, arg9, arg10);
    }

    public static boolean setLayoutDirection(@NonNull Drawable arg1, int arg2) {
        return DrawableCompat.IMPL.setLayoutDirection(arg1, arg2);
    }

    public static void setTint(@NonNull Drawable arg1, @ColorInt int arg2) {
        DrawableCompat.IMPL.setTint(arg1, arg2);
    }

    public static void setTintList(@NonNull Drawable arg1, @Nullable ColorStateList arg2) {
        DrawableCompat.IMPL.setTintList(arg1, arg2);
    }

    public static void setTintMode(@NonNull Drawable arg1, @Nullable PorterDuff$Mode arg2) {
        DrawableCompat.IMPL.setTintMode(arg1, arg2);
    }

    public static Drawable unwrap(@NonNull Drawable arg1) {
        if((arg1 instanceof DrawableWrapper)) {
            return ((DrawableWrapper)arg1).getWrappedDrawable();
        }

        return arg1;
    }

    public static Drawable wrap(@NonNull Drawable arg1) {
        return DrawableCompat.IMPL.wrap(arg1);
    }
}

