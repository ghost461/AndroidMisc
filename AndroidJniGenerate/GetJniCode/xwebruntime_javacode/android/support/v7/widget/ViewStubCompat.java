package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.v7.appcompat.R$styleable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.lang.ref.WeakReference;

@RestrictTo(value={Scope.LIBRARY_GROUP}) public final class ViewStubCompat extends View {
    public interface OnInflateListener {
        void onInflate(ViewStubCompat arg1, View arg2);
    }

    private OnInflateListener mInflateListener;
    private int mInflatedId;
    private WeakReference mInflatedViewRef;
    private LayoutInflater mInflater;
    private int mLayoutResource;

    public ViewStubCompat(Context arg2, AttributeSet arg3) {
        this(arg2, arg3, 0);
    }

    public ViewStubCompat(Context arg3, AttributeSet arg4, int arg5) {
        super(arg3, arg4, arg5);
        this.mLayoutResource = 0;
        TypedArray v3 = arg3.obtainStyledAttributes(arg4, styleable.ViewStubCompat, arg5, 0);
        this.mInflatedId = v3.getResourceId(styleable.ViewStubCompat_android_inflatedId, -1);
        this.mLayoutResource = v3.getResourceId(styleable.ViewStubCompat_android_layout, 0);
        this.setId(v3.getResourceId(styleable.ViewStubCompat_android_id, -1));
        v3.recycle();
        this.setVisibility(8);
        this.setWillNotDraw(true);
    }

    protected void dispatchDraw(Canvas arg1) {
    }

    public void draw(Canvas arg1) {
    }

    public int getInflatedId() {
        return this.mInflatedId;
    }

    public LayoutInflater getLayoutInflater() {
        return this.mInflater;
    }

    public int getLayoutResource() {
        return this.mLayoutResource;
    }

    public View inflate() {
        ViewParent v0 = this.getParent();
        if(v0 != null && ((v0 instanceof ViewGroup))) {
            if(this.mLayoutResource != 0) {
                LayoutInflater v1 = this.mInflater != null ? this.mInflater : LayoutInflater.from(this.getContext());
                View v1_1 = v1.inflate(this.mLayoutResource, ((ViewGroup)v0), false);
                if(this.mInflatedId != -1) {
                    v1_1.setId(this.mInflatedId);
                }

                int v2 = ((ViewGroup)v0).indexOfChild(((View)this));
                ((ViewGroup)v0).removeViewInLayout(((View)this));
                ViewGroup$LayoutParams v3 = this.getLayoutParams();
                if(v3 != null) {
                    ((ViewGroup)v0).addView(v1_1, v2, v3);
                }
                else {
                    ((ViewGroup)v0).addView(v1_1, v2);
                }

                this.mInflatedViewRef = new WeakReference(v1_1);
                if(this.mInflateListener != null) {
                    this.mInflateListener.onInflate(this, v1_1);
                }

                return v1_1;
            }
            else {
                throw new IllegalArgumentException("ViewStub must have a valid layoutResource");
            }
        }

        throw new IllegalStateException("ViewStub must have a non-null ViewGroup viewParent");
    }

    protected void onMeasure(int arg1, int arg2) {
        this.setMeasuredDimension(0, 0);
    }

    public void setInflatedId(int arg1) {
        this.mInflatedId = arg1;
    }

    public void setLayoutInflater(LayoutInflater arg1) {
        this.mInflater = arg1;
    }

    public void setLayoutResource(int arg1) {
        this.mLayoutResource = arg1;
    }

    public void setOnInflateListener(OnInflateListener arg1) {
        this.mInflateListener = arg1;
    }

    public void setVisibility(int arg2) {
        if(this.mInflatedViewRef != null) {
            Object v0 = this.mInflatedViewRef.get();
            if(v0 != null) {
                ((View)v0).setVisibility(arg2);
            }
            else {
                throw new IllegalStateException("setVisibility called on un-referenced view");
            }
        }
        else {
            super.setVisibility(arg2);
            if(arg2 != 0 && arg2 != 4) {
                return;
            }

            this.inflate();
        }
    }
}

