package android.support.v7.widget;

import android.graphics.Outline;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

@RequiresApi(value=21) class ActionBarBackgroundDrawableV21 extends ActionBarBackgroundDrawable {
    public ActionBarBackgroundDrawableV21(ActionBarContainer arg1) {
        super(arg1);
    }

    public void getOutline(@NonNull Outline arg2) {
        if(this.mContainer.mIsSplit) {
            if(this.mContainer.mSplitBackground != null) {
                this.mContainer.mSplitBackground.getOutline(arg2);
            }
        }
        else if(this.mContainer.mBackground != null) {
            this.mContainer.mBackground.getOutline(arg2);
        }
    }
}

