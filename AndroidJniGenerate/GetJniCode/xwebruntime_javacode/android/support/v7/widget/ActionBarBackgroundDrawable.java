package android.support.v7.widget;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.support.annotation.RequiresApi;

@RequiresApi(value=9) class ActionBarBackgroundDrawable extends Drawable {
    final ActionBarContainer mContainer;

    public ActionBarBackgroundDrawable(ActionBarContainer arg1) {
        super();
        this.mContainer = arg1;
    }

    public void draw(Canvas arg2) {
        if(!this.mContainer.mIsSplit) {
            if(this.mContainer.mBackground != null) {
                this.mContainer.mBackground.draw(arg2);
            }

            if(this.mContainer.mStackedBackground == null) {
                return;
            }

            if(!this.mContainer.mIsStacked) {
                return;
            }

            this.mContainer.mStackedBackground.draw(arg2);
        }
        else if(this.mContainer.mSplitBackground != null) {
            this.mContainer.mSplitBackground.draw(arg2);
        }
    }

    public int getOpacity() {
        return 0;
    }

    public void setAlpha(int arg1) {
    }

    public void setColorFilter(ColorFilter arg1) {
    }
}

