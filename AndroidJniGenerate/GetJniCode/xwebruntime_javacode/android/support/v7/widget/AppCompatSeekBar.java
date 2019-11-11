package android.support.v7.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.RequiresApi;
import android.support.v7.appcompat.R$attr;
import android.util.AttributeSet;
import android.widget.SeekBar;

public class AppCompatSeekBar extends SeekBar {
    private final AppCompatSeekBarHelper mAppCompatSeekBarHelper;

    public AppCompatSeekBar(Context arg2, AttributeSet arg3) {
        this(arg2, arg3, attr.seekBarStyle);
    }

    public AppCompatSeekBar(Context arg2) {
        this(arg2, null);
    }

    public AppCompatSeekBar(Context arg1, AttributeSet arg2, int arg3) {
        super(arg1, arg2, arg3);
        this.mAppCompatSeekBarHelper = new AppCompatSeekBarHelper(((SeekBar)this));
        this.mAppCompatSeekBarHelper.loadFromAttributes(arg2, arg3);
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        this.mAppCompatSeekBarHelper.drawableStateChanged();
    }

    @RequiresApi(value=11) public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        this.mAppCompatSeekBarHelper.jumpDrawablesToCurrentState();
    }

    protected void onDraw(Canvas arg2) {
        __monitor_enter(this);
        try {
            super.onDraw(arg2);
            this.mAppCompatSeekBarHelper.drawTickMarks(arg2);
        }
        catch(Throwable v2) {
            __monitor_exit(this);
            throw v2;
        }

        __monitor_exit(this);
    }
}

