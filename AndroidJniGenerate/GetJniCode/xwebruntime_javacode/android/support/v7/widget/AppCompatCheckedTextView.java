package android.support.v7.widget;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import android.widget.CheckedTextView;
import android.widget.TextView;

public class AppCompatCheckedTextView extends CheckedTextView {
    private static final int[] TINT_ATTRS;
    private final AppCompatTextHelper mTextHelper;

    static {
        AppCompatCheckedTextView.TINT_ATTRS = new int[]{0x1010108};
    }

    public AppCompatCheckedTextView(Context arg2, AttributeSet arg3) {
        this(arg2, arg3, 0x10103C8);
    }

    public AppCompatCheckedTextView(Context arg2) {
        this(arg2, null);
    }

    public AppCompatCheckedTextView(Context arg3, AttributeSet arg4, int arg5) {
        super(TintContextWrapper.wrap(arg3), arg4, arg5);
        this.mTextHelper = AppCompatTextHelper.create(((TextView)this));
        this.mTextHelper.loadFromAttributes(arg4, arg5);
        this.mTextHelper.applyCompoundDrawablesTints();
        TintTypedArray v3 = TintTypedArray.obtainStyledAttributes(this.getContext(), arg4, AppCompatCheckedTextView.TINT_ATTRS, arg5, 0);
        this.setCheckMarkDrawable(v3.getDrawable(0));
        v3.recycle();
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if(this.mTextHelper != null) {
            this.mTextHelper.applyCompoundDrawablesTints();
        }
    }

    public void setCheckMarkDrawable(@DrawableRes int arg2) {
        this.setCheckMarkDrawable(AppCompatResources.getDrawable(this.getContext(), arg2));
    }

    public void setTextAppearance(Context arg2, int arg3) {
        super.setTextAppearance(arg2, arg3);
        if(this.mTextHelper != null) {
            this.mTextHelper.onSetTextAppearance(arg2, arg3);
        }
    }
}

