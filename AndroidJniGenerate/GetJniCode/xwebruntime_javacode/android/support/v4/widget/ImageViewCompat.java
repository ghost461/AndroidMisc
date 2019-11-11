package android.support.v4.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff$Mode;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.support.annotation.RequiresApi;
import android.widget.ImageView;

public class ImageViewCompat {
    class BaseViewCompatImpl implements ImageViewCompatImpl {
        BaseViewCompatImpl() {
            super();
        }

        public ColorStateList getImageTintList(ImageView arg2) {
            ColorStateList v2 = (arg2 instanceof TintableImageSourceView) ? ((TintableImageSourceView)arg2).getSupportImageTintList() : null;
            return v2;
        }

        public PorterDuff$Mode getImageTintMode(ImageView arg2) {
            PorterDuff$Mode v2 = (arg2 instanceof TintableImageSourceView) ? ((TintableImageSourceView)arg2).getSupportImageTintMode() : null;
            return v2;
        }

        public void setImageTintList(ImageView arg2, ColorStateList arg3) {
            if((arg2 instanceof TintableImageSourceView)) {
                ((TintableImageSourceView)arg2).setSupportImageTintList(arg3);
            }
        }

        public void setImageTintMode(ImageView arg2, PorterDuff$Mode arg3) {
            if((arg2 instanceof TintableImageSourceView)) {
                ((TintableImageSourceView)arg2).setSupportImageTintMode(arg3);
            }
        }
    }

    interface ImageViewCompatImpl {
        ColorStateList getImageTintList(ImageView arg1);

        PorterDuff$Mode getImageTintMode(ImageView arg1);

        void setImageTintList(ImageView arg1, ColorStateList arg2);

        void setImageTintMode(ImageView arg1, PorterDuff$Mode arg2);
    }

    @RequiresApi(value=21) class LollipopViewCompatImpl extends BaseViewCompatImpl {
        LollipopViewCompatImpl() {
            super();
        }

        public ColorStateList getImageTintList(ImageView arg1) {
            return arg1.getImageTintList();
        }

        public PorterDuff$Mode getImageTintMode(ImageView arg1) {
            return arg1.getImageTintMode();
        }

        public void setImageTintList(ImageView arg2, ColorStateList arg3) {
            arg2.setImageTintList(arg3);
            if(Build$VERSION.SDK_INT == 21) {
                Drawable v3 = arg2.getDrawable();
                int v0 = arg2.getImageTintList() == null || arg2.getImageTintMode() == null ? 0 : 1;
                if(v3 == null) {
                    return;
                }

                if(v0 == 0) {
                    return;
                }

                if(v3.isStateful()) {
                    v3.setState(arg2.getDrawableState());
                }

                arg2.setImageDrawable(v3);
            }
        }

        public void setImageTintMode(ImageView arg2, PorterDuff$Mode arg3) {
            arg2.setImageTintMode(arg3);
            if(Build$VERSION.SDK_INT == 21) {
                Drawable v3 = arg2.getDrawable();
                int v0 = arg2.getImageTintList() == null || arg2.getImageTintMode() == null ? 0 : 1;
                if(v3 == null) {
                    return;
                }

                if(v0 == 0) {
                    return;
                }

                if(v3.isStateful()) {
                    v3.setState(arg2.getDrawableState());
                }

                arg2.setImageDrawable(v3);
            }
        }
    }

    static final ImageViewCompatImpl IMPL;

    static {
        ImageViewCompat.IMPL = Build$VERSION.SDK_INT >= 21 ? new LollipopViewCompatImpl() : new BaseViewCompatImpl();
    }

    private ImageViewCompat() {
        super();
    }

    public static ColorStateList getImageTintList(ImageView arg1) {
        return ImageViewCompat.IMPL.getImageTintList(arg1);
    }

    public static PorterDuff$Mode getImageTintMode(ImageView arg1) {
        return ImageViewCompat.IMPL.getImageTintMode(arg1);
    }

    public static void setImageTintList(ImageView arg1, ColorStateList arg2) {
        ImageViewCompat.IMPL.setImageTintList(arg1, arg2);
    }

    public static void setImageTintMode(ImageView arg1, PorterDuff$Mode arg2) {
        ImageViewCompat.IMPL.setImageTintMode(arg1, arg2);
    }
}

