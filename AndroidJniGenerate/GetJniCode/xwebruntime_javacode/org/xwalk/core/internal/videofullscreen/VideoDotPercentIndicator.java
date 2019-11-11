package org.xwalk.core.internal.videofullscreen;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import org.xwalk.core.internal.Log;

public class VideoDotPercentIndicator extends LinearLayout {
    private static final int DOTS_NUM_DEFAULT = 8;
    private static final String TAG = "MicroMsg.AppBrandDotPercentIndicator";
    private int mDotsNum;
    private LayoutInflater mInflater;

    public VideoDotPercentIndicator(Context arg1) {
        super(arg1);
        this.init(arg1);
    }

    public VideoDotPercentIndicator(Context arg1, AttributeSet arg2) {
        super(arg1, arg2);
        this.init(arg1);
    }

    public VideoDotPercentIndicator(Context arg1, AttributeSet arg2, int arg3) {
        super(arg1, arg2, arg3);
        this.init(arg1);
    }

    private void init(Context arg1) {
        this.mInflater = LayoutInflater.from(arg1);
        this.setDotsNum(0);
    }

    public void setDotsNum(int arg4) {
        if(arg4 > 1) {
        }
        else {
            arg4 = 8;
        }

        this.mDotsNum = arg4;
        this.removeAllViews();
        int v0;
        for(v0 = 0; v0 < this.mDotsNum; ++v0) {
            this.addView(this.mInflater.inflate(0x7F090040, ((ViewGroup)this), false));
        }
    }

    public void setProgress(float arg7) {
        arg7 /= 100f;
        if(arg7 < 0f) {
            arg7 = 0f;
        }

        if(arg7 > 1f) {
            arg7 = 1f;
        }

        int v0 = ((int)Math.rint(((double)((((float)this.mDotsNum)) * arg7))));
        Object[] v3 = new Object[2];
        int v4 = 0;
        v3[0] = Float.valueOf(arg7);
        v3[1] = Integer.valueOf(v0);
        Log.v("MicroMsg.AppBrandDotPercentIndicator", String.format("setPercent percent:%f dotsOnNum:%d", v3));
        while(v4 < v0) {
            if(v4 >= this.getChildCount()) {
                break;
            }

            this.getChildAt(v4).setImageResource(0x7F06007D);
            ++v4;
        }

        while(v4 < this.getChildCount()) {
            this.getChildAt(v4).setImageResource(0x7F06007C);
            ++v4;
        }
    }
}

