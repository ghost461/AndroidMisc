package org.xwalk.core.internal.videofullscreen;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class VideoStatusLayout extends RelativeLayout {
    class org.xwalk.core.internal.videofullscreen.VideoStatusLayout$1 {
    }

    class HideRunnable implements Runnable {
        HideRunnable(VideoStatusLayout arg1, org.xwalk.core.internal.videofullscreen.VideoStatusLayout$1 arg2) {
            this(arg1);
        }

        private HideRunnable(VideoStatusLayout arg1) {
            VideoStatusLayout.this = arg1;
            super();
        }

        public void run() {
            VideoStatusLayout.this.setVisibility(8);
        }
    }

    private static final String TAG = "gesturetest";
    private int duration;
    private HideRunnable mHideRunnable;
    private ImageView mImage;
    private VideoDotPercentIndicator mIndicator;
    private LinearLayout mLayoutStatus;
    private TextView mTvProgress;
    private TextView mTvStatus;

    public VideoStatusLayout(Context arg2) {
        super(arg2);
        this.duration = 1000;
        this.init(arg2);
    }

    public VideoStatusLayout(Context arg1, AttributeSet arg2) {
        super(arg1, arg2);
        this.duration = 1000;
        this.init(arg1);
    }

    private void init(Context arg2) {
        LayoutInflater.from(arg2).inflate(0x7F090037, ((ViewGroup)this));
        this.mImage = this.findViewById(0x7F070055);
        this.mIndicator = this.findViewById(0x7F07007C);
        this.mTvProgress = this.findViewById(0x7F0700BE);
        this.mTvStatus = this.findViewById(0x7F0700B4);
        this.mLayoutStatus = this.findViewById(0x7F07005D);
        this.mHideRunnable = new HideRunnable(this, null);
        this.setVisibility(8);
    }

    public void setBrightProgress(int arg3) {
        this.mIndicator.setProgress(((float)arg3));
        this.mIndicator.setVisibility(0);
        this.mTvStatus.setText(0x7F0C0084);
        this.mLayoutStatus.setVisibility(0);
        this.mImage.setImageResource(0x7F060077);
        this.mTvProgress.setVisibility(8);
    }

    public void setDuration(int arg1) {
        this.duration = arg1;
    }

    public void setImageResource(int arg2) {
        this.mImage.setImageResource(arg2);
    }

    public void setVideoTimeProgress(String arg2) {
        this.mTvProgress.setText(((CharSequence)arg2));
        this.mTvProgress.setVisibility(0);
        this.mLayoutStatus.setVisibility(8);
    }

    public void setVolumnProgress(int arg3) {
        this.mIndicator.setProgress(((float)arg3));
        this.mIndicator.setVisibility(0);
        this.mTvStatus.setText(0x7F0C0085);
        this.mLayoutStatus.setVisibility(0);
        this.mImage.setImageResource(0x7F060081);
        this.mTvProgress.setVisibility(8);
    }

    public void show() {
        this.setVisibility(0);
        this.removeCallbacks(this.mHideRunnable);
        this.postDelayed(this.mHideRunnable, ((long)this.duration));
    }
}

