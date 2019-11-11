package android.support.graphics.drawable;

import android.graphics.drawable.Animatable2$AnimationCallback;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

public interface Animatable2Compat extends Animatable {
    public abstract class AnimationCallback {
        Animatable2$AnimationCallback mPlatformCallback;

        public AnimationCallback() {
            super();
        }

        @RequiresApi(value=23) Animatable2$AnimationCallback getPlatformCallback() {
            if(this.mPlatformCallback == null) {
                this.mPlatformCallback = new Animatable2$AnimationCallback() {
                    public void onAnimationEnd(Drawable arg2) {
                        AnimationCallback.this.onAnimationEnd(arg2);
                    }

                    public void onAnimationStart(Drawable arg2) {
                        AnimationCallback.this.onAnimationStart(arg2);
                    }
                };
            }

            return this.mPlatformCallback;
        }

        public void onAnimationEnd(Drawable arg1) {
        }

        public void onAnimationStart(Drawable arg1) {
        }
    }

    void clearAnimationCallbacks();

    void registerAnimationCallback(@NonNull AnimationCallback arg1);

    boolean unregisterAnimationCallback(@NonNull AnimationCallback arg1);
}

