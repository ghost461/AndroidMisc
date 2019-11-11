package android.support.v4.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff$Mode;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.CompoundButton;
import java.lang.reflect.Field;

public final class CompoundButtonCompat {
    @RequiresApi(value=21) class CompoundButtonCompatApi21Impl extends CompoundButtonCompatBaseImpl {
        CompoundButtonCompatApi21Impl() {
            super();
        }

        public ColorStateList getButtonTintList(CompoundButton arg1) {
            return arg1.getButtonTintList();
        }

        public PorterDuff$Mode getButtonTintMode(CompoundButton arg1) {
            return arg1.getButtonTintMode();
        }

        public void setButtonTintList(CompoundButton arg1, ColorStateList arg2) {
            arg1.setButtonTintList(arg2);
        }

        public void setButtonTintMode(CompoundButton arg1, PorterDuff$Mode arg2) {
            arg1.setButtonTintMode(arg2);
        }
    }

    @RequiresApi(value=23) class CompoundButtonCompatApi23Impl extends CompoundButtonCompatApi21Impl {
        CompoundButtonCompatApi23Impl() {
            super();
        }

        public Drawable getButtonDrawable(CompoundButton arg1) {
            return arg1.getButtonDrawable();
        }
    }

    class CompoundButtonCompatBaseImpl {
        private static final String TAG = "CompoundButtonCompat";
        private static Field sButtonDrawableField;
        private static boolean sButtonDrawableFieldFetched;

        CompoundButtonCompatBaseImpl() {
            super();
        }

        public Drawable getButtonDrawable(CompoundButton arg5) {
            if(!CompoundButtonCompatBaseImpl.sButtonDrawableFieldFetched) {
                try {
                    CompoundButtonCompatBaseImpl.sButtonDrawableField = CompoundButton.class.getDeclaredField("mButtonDrawable");
                    CompoundButtonCompatBaseImpl.sButtonDrawableField.setAccessible(true);
                }
                catch(NoSuchFieldException v1) {
                    Log.i("CompoundButtonCompat", "Failed to retrieve mButtonDrawable field", ((Throwable)v1));
                }

                CompoundButtonCompatBaseImpl.sButtonDrawableFieldFetched = true;
            }

            Field v1_1 = null;
            if(CompoundButtonCompatBaseImpl.sButtonDrawableField != null) {
                try {
                    return CompoundButtonCompatBaseImpl.sButtonDrawableField.get(arg5);
                }
                catch(IllegalAccessException v5) {
                    Log.i("CompoundButtonCompat", "Failed to get button drawable via reflection", ((Throwable)v5));
                    CompoundButtonCompatBaseImpl.sButtonDrawableField = v1_1;
                }
            }

            return ((Drawable)v1_1);
        }

        public ColorStateList getButtonTintList(CompoundButton arg2) {
            if((arg2 instanceof TintableCompoundButton)) {
                return ((TintableCompoundButton)arg2).getSupportButtonTintList();
            }

            return null;
        }

        public PorterDuff$Mode getButtonTintMode(CompoundButton arg2) {
            if((arg2 instanceof TintableCompoundButton)) {
                return ((TintableCompoundButton)arg2).getSupportButtonTintMode();
            }

            return null;
        }

        public void setButtonTintList(CompoundButton arg2, ColorStateList arg3) {
            if((arg2 instanceof TintableCompoundButton)) {
                ((TintableCompoundButton)arg2).setSupportButtonTintList(arg3);
            }
        }

        public void setButtonTintMode(CompoundButton arg2, PorterDuff$Mode arg3) {
            if((arg2 instanceof TintableCompoundButton)) {
                ((TintableCompoundButton)arg2).setSupportButtonTintMode(arg3);
            }
        }
    }

    private static final CompoundButtonCompatBaseImpl IMPL;

    static {
        if(Build$VERSION.SDK_INT >= 23) {
            CompoundButtonCompat.IMPL = new CompoundButtonCompatApi23Impl();
        }
        else if(Build$VERSION.SDK_INT >= 21) {
            CompoundButtonCompat.IMPL = new CompoundButtonCompatApi21Impl();
        }
        else {
            CompoundButtonCompat.IMPL = new CompoundButtonCompatBaseImpl();
        }
    }

    private CompoundButtonCompat() {
        super();
    }

    @Nullable public static Drawable getButtonDrawable(@NonNull CompoundButton arg1) {
        return CompoundButtonCompat.IMPL.getButtonDrawable(arg1);
    }

    @Nullable public static ColorStateList getButtonTintList(@NonNull CompoundButton arg1) {
        return CompoundButtonCompat.IMPL.getButtonTintList(arg1);
    }

    @Nullable public static PorterDuff$Mode getButtonTintMode(@NonNull CompoundButton arg1) {
        return CompoundButtonCompat.IMPL.getButtonTintMode(arg1);
    }

    public static void setButtonTintList(@NonNull CompoundButton arg1, @Nullable ColorStateList arg2) {
        CompoundButtonCompat.IMPL.setButtonTintList(arg1, arg2);
    }

    public static void setButtonTintMode(@NonNull CompoundButton arg1, @Nullable PorterDuff$Mode arg2) {
        CompoundButtonCompat.IMPL.setButtonTintMode(arg1, arg2);
    }
}

