package android.support.v4.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build$VERSION;
import android.support.annotation.RequiresApi;
import android.widget.EdgeEffect;

public final class EdgeEffectCompat {
    @RequiresApi(value=21) class EdgeEffectApi21Impl extends EdgeEffectBaseImpl {
        EdgeEffectApi21Impl() {
            super();
        }

        public void onPull(EdgeEffect arg1, float arg2, float arg3) {
            arg1.onPull(arg2, arg3);
        }
    }

    class EdgeEffectBaseImpl {
        EdgeEffectBaseImpl() {
            super();
        }

        public void onPull(EdgeEffect arg1, float arg2, float arg3) {
            arg1.onPull(arg2);
        }
    }

    private static final EdgeEffectBaseImpl IMPL;
    private EdgeEffect mEdgeEffect;

    static {
        EdgeEffectCompat.IMPL = Build$VERSION.SDK_INT >= 21 ? new EdgeEffectApi21Impl() : new EdgeEffectBaseImpl();
    }

    @Deprecated public EdgeEffectCompat(Context arg2) {
        super();
        this.mEdgeEffect = new EdgeEffect(arg2);
    }

    @Deprecated public boolean draw(Canvas arg2) {
        return this.mEdgeEffect.draw(arg2);
    }

    @Deprecated public void finish() {
        this.mEdgeEffect.finish();
    }

    @Deprecated public boolean isFinished() {
        return this.mEdgeEffect.isFinished();
    }

    @Deprecated public boolean onAbsorb(int arg2) {
        this.mEdgeEffect.onAbsorb(arg2);
        return 1;
    }

    public static void onPull(EdgeEffect arg1, float arg2, float arg3) {
        EdgeEffectCompat.IMPL.onPull(arg1, arg2, arg3);
    }

    @Deprecated public boolean onPull(float arg2) {
        this.mEdgeEffect.onPull(arg2);
        return 1;
    }

    @Deprecated public boolean onPull(float arg3, float arg4) {
        EdgeEffectCompat.IMPL.onPull(this.mEdgeEffect, arg3, arg4);
        return 1;
    }

    @Deprecated public boolean onRelease() {
        this.mEdgeEffect.onRelease();
        return this.mEdgeEffect.isFinished();
    }

    @Deprecated public void setSize(int arg2, int arg3) {
        this.mEdgeEffect.setSize(arg2, arg3);
    }
}

