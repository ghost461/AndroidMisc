package android.support.v4.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader$TileMode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build$VERSION;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.animation.Animation$AnimationListener;
import android.widget.ImageView;

class CircleImageView extends ImageView {
    class OvalShadow extends OvalShape {
        private RadialGradient mRadialGradient;
        private Paint mShadowPaint;

        OvalShadow(CircleImageView arg2, int arg3) {
            CircleImageView.this = arg2;
            super();
            this.mShadowPaint = new Paint();
            arg2.mShadowRadius = arg3;
            this.updateRadialGradient(((int)this.rect().width()));
        }

        public void draw(Canvas arg5, Paint arg6) {
            int v0 = CircleImageView.this.getWidth() / 2;
            float v2 = ((float)v0);
            float v1 = ((float)(CircleImageView.this.getHeight() / 2));
            arg5.drawCircle(v2, v1, v2, this.mShadowPaint);
            arg5.drawCircle(v2, v1, ((float)(v0 - CircleImageView.this.mShadowRadius)), arg6);
        }

        protected void onResize(float arg1, float arg2) {
            super.onResize(arg1, arg2);
            this.updateRadialGradient(((int)arg1));
        }

        private void updateRadialGradient(int arg9) {
            float v2 = ((float)(arg9 / 2));
            this.mRadialGradient = new RadialGradient(v2, v2, ((float)CircleImageView.this.mShadowRadius), new int[]{0x3D000000, 0}, null, Shader$TileMode.CLAMP);
            this.mShadowPaint.setShader(this.mRadialGradient);
        }
    }

    private static final int FILL_SHADOW_COLOR = 0x3D000000;
    private static final int KEY_SHADOW_COLOR = 0x1E000000;
    private static final int SHADOW_ELEVATION = 4;
    private static final float SHADOW_RADIUS = 3.5f;
    private static final float X_OFFSET = 0f;
    private static final float Y_OFFSET = 1.75f;
    private Animation$AnimationListener mListener;
    int mShadowRadius;

    CircleImageView(Context arg6, int arg7) {
        ShapeDrawable v0_1;
        super(arg6);
        float v6 = this.getContext().getResources().getDisplayMetrics().density;
        int v0 = ((int)(1.75f * v6));
        int v1 = ((int)(0f * v6));
        this.mShadowRadius = ((int)(3.5f * v6));
        if(this.elevationSupported()) {
            v0_1 = new ShapeDrawable(new OvalShape());
            ViewCompat.setElevation(((View)this), v6 * 4f);
        }
        else {
            ShapeDrawable v2 = new ShapeDrawable(new OvalShadow(this, this.mShadowRadius));
            this.setLayerType(1, v2.getPaint());
            v2.getPaint().setShadowLayer(((float)this.mShadowRadius), ((float)v1), ((float)v0), 0x1E000000);
            this.setPadding(this.mShadowRadius, this.mShadowRadius, this.mShadowRadius, this.mShadowRadius);
            v0_1 = v2;
        }

        v0_1.getPaint().setColor(arg7);
        ViewCompat.setBackground(((View)this), ((Drawable)v0_1));
    }

    private boolean elevationSupported() {
        boolean v0 = Build$VERSION.SDK_INT >= 21 ? true : false;
        return v0;
    }

    public void onAnimationEnd() {
        super.onAnimationEnd();
        if(this.mListener != null) {
            this.mListener.onAnimationEnd(this.getAnimation());
        }
    }

    public void onAnimationStart() {
        super.onAnimationStart();
        if(this.mListener != null) {
            this.mListener.onAnimationStart(this.getAnimation());
        }
    }

    protected void onMeasure(int arg2, int arg3) {
        super.onMeasure(arg2, arg3);
        if(!this.elevationSupported()) {
            this.setMeasuredDimension(this.getMeasuredWidth() + this.mShadowRadius * 2, this.getMeasuredHeight() + this.mShadowRadius * 2);
        }
    }

    public void setAnimationListener(Animation$AnimationListener arg1) {
        this.mListener = arg1;
    }

    public void setBackgroundColor(int arg2) {
        if((this.getBackground() instanceof ShapeDrawable)) {
            this.getBackground().getPaint().setColor(arg2);
        }
    }

    public void setBackgroundColorRes(int arg2) {
        this.setBackgroundColor(ContextCompat.getColor(this.getContext(), arg2));
    }
}

