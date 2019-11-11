package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.annotation.StyleableRes;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;

@RestrictTo(value={Scope.LIBRARY_GROUP}) public class TintTypedArray {
    private final Context mContext;
    private TypedValue mTypedValue;
    private final TypedArray mWrapped;

    private TintTypedArray(Context arg1, TypedArray arg2) {
        super();
        this.mContext = arg1;
        this.mWrapped = arg2;
    }

    public boolean getBoolean(int arg2, boolean arg3) {
        return this.mWrapped.getBoolean(arg2, arg3);
    }

    @RequiresApi(value=21) public int getChangingConfigurations() {
        return this.mWrapped.getChangingConfigurations();
    }

    public int getColor(int arg2, int arg3) {
        return this.mWrapped.getColor(arg2, arg3);
    }

    public ColorStateList getColorStateList(int arg3) {
        if(this.mWrapped.hasValue(arg3)) {
            int v0 = this.mWrapped.getResourceId(arg3, 0);
            if(v0 != 0) {
                ColorStateList v0_1 = AppCompatResources.getColorStateList(this.mContext, v0);
                if(v0_1 != null) {
                    return v0_1;
                }
            }
        }

        return this.mWrapped.getColorStateList(arg3);
    }

    public float getDimension(int arg2, float arg3) {
        return this.mWrapped.getDimension(arg2, arg3);
    }

    public int getDimensionPixelOffset(int arg2, int arg3) {
        return this.mWrapped.getDimensionPixelOffset(arg2, arg3);
    }

    public int getDimensionPixelSize(int arg2, int arg3) {
        return this.mWrapped.getDimensionPixelSize(arg2, arg3);
    }

    public Drawable getDrawable(int arg3) {
        if(this.mWrapped.hasValue(arg3)) {
            int v0 = this.mWrapped.getResourceId(arg3, 0);
            if(v0 != 0) {
                return AppCompatResources.getDrawable(this.mContext, v0);
            }
        }

        return this.mWrapped.getDrawable(arg3);
    }

    public Drawable getDrawableIfKnown(int arg4) {
        if(this.mWrapped.hasValue(arg4)) {
            arg4 = this.mWrapped.getResourceId(arg4, 0);
            if(arg4 != 0) {
                return AppCompatDrawableManager.get().getDrawable(this.mContext, arg4, true);
            }
        }

        return null;
    }

    public float getFloat(int arg2, float arg3) {
        return this.mWrapped.getFloat(arg2, arg3);
    }

    @Nullable public Typeface getFont(@StyleableRes int arg3, int arg4, @NonNull TextView arg5) {
        arg3 = this.mWrapped.getResourceId(arg3, 0);
        if(arg3 == 0) {
            return null;
        }

        if(this.mTypedValue == null) {
            this.mTypedValue = new TypedValue();
        }

        return ResourcesCompat.getFont(this.mContext, arg3, this.mTypedValue, arg4, arg5);
    }

    public float getFraction(int arg2, int arg3, int arg4, float arg5) {
        return this.mWrapped.getFraction(arg2, arg3, arg4, arg5);
    }

    public int getIndex(int arg2) {
        return this.mWrapped.getIndex(arg2);
    }

    public int getIndexCount() {
        return this.mWrapped.getIndexCount();
    }

    public int getInt(int arg2, int arg3) {
        return this.mWrapped.getInt(arg2, arg3);
    }

    public int getInteger(int arg2, int arg3) {
        return this.mWrapped.getInteger(arg2, arg3);
    }

    public int getLayoutDimension(int arg2, int arg3) {
        return this.mWrapped.getLayoutDimension(arg2, arg3);
    }

    public int getLayoutDimension(int arg2, String arg3) {
        return this.mWrapped.getLayoutDimension(arg2, arg3);
    }

    public String getNonResourceString(int arg2) {
        return this.mWrapped.getNonResourceString(arg2);
    }

    public String getPositionDescription() {
        return this.mWrapped.getPositionDescription();
    }

    public int getResourceId(int arg2, int arg3) {
        return this.mWrapped.getResourceId(arg2, arg3);
    }

    public Resources getResources() {
        return this.mWrapped.getResources();
    }

    public String getString(int arg2) {
        return this.mWrapped.getString(arg2);
    }

    public CharSequence getText(int arg2) {
        return this.mWrapped.getText(arg2);
    }

    public CharSequence[] getTextArray(int arg2) {
        return this.mWrapped.getTextArray(arg2);
    }

    public int getType(int arg3) {
        if(Build$VERSION.SDK_INT >= 21) {
            return this.mWrapped.getType(arg3);
        }

        if(this.mTypedValue == null) {
            this.mTypedValue = new TypedValue();
        }

        this.mWrapped.getValue(arg3, this.mTypedValue);
        return this.mTypedValue.type;
    }

    public boolean getValue(int arg2, TypedValue arg3) {
        return this.mWrapped.getValue(arg2, arg3);
    }

    public boolean hasValue(int arg2) {
        return this.mWrapped.hasValue(arg2);
    }

    public int length() {
        return this.mWrapped.length();
    }

    public static TintTypedArray obtainStyledAttributes(Context arg1, AttributeSet arg2, int[] arg3, int arg4, int arg5) {
        return new TintTypedArray(arg1, arg1.obtainStyledAttributes(arg2, arg3, arg4, arg5));
    }

    public static TintTypedArray obtainStyledAttributes(Context arg1, int arg2, int[] arg3) {
        return new TintTypedArray(arg1, arg1.obtainStyledAttributes(arg2, arg3));
    }

    public static TintTypedArray obtainStyledAttributes(Context arg1, AttributeSet arg2, int[] arg3) {
        return new TintTypedArray(arg1, arg1.obtainStyledAttributes(arg2, arg3));
    }

    public TypedValue peekValue(int arg2) {
        return this.mWrapped.peekValue(arg2);
    }

    public void recycle() {
        this.mWrapped.recycle();
    }
}

