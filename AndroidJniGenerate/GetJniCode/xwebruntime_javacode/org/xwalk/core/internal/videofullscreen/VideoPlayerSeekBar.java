package org.xwalk.core.internal.videofullscreen;

import android.view.MotionEvent;
import android.view.View$OnTouchListener;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class VideoPlayerSeekBar {
    public interface IVideoPlaySeekCallback {
        void onProgressChanged(VideoPlayerSeekBar arg1, float arg2, boolean arg3);

        void onSeekPre();
    }

    private ImageView mBackBar;
    private ImageView mBarPoint;
    private float mDownX;
    private ImageView mFrontBar;
    private boolean mIsMoving;
    private float mLastMoveProgress;
    private ImageView mMiddleBar;
    private float mProgress;
    private IVideoPlaySeekCallback mVideoPlayerSeekCallback;

    public VideoPlayerSeekBar(FrameLayout arg2, IVideoPlaySeekCallback arg3) {
        super();
        this.mMiddleBar = null;
        this.mBarPoint = null;
        this.mIsMoving = false;
        this.mDownX = 0f;
        this.mVideoPlayerSeekCallback = arg3;
        this.mFrontBar = arg2.findViewById(0x7F070074);
        this.mMiddleBar = arg2.findViewById(0x7F070075);
        this.mBackBar = arg2.findViewById(0x7F070073);
        this.mBarPoint = arg2.findViewById(0x7F070076);
        this.mBarPoint.setOnTouchListener(new View$OnTouchListener() {
            public boolean onTouch(View arg4, MotionEvent arg5) {
                if(arg5.getAction() == 0) {
                    VideoPlayerSeekBar.access$002(VideoPlayerSeekBar.this, false);
                    VideoPlayerSeekBar.access$102(VideoPlayerSeekBar.this, arg5.getX());
                    if(VideoPlayerSeekBar.access$200(VideoPlayerSeekBar.this) != null) {
                        VideoPlayerSeekBar.access$200(VideoPlayerSeekBar.this).onSeekPre();
                    }
                }
                else if(arg5.getAction() == 2) {
                    float v4 = ((float)(((int)((((float)VideoPlayerSeekBar.access$300(VideoPlayerSeekBar.this).getWidth())) + (arg5.getX() - VideoPlayerSeekBar.access$100(VideoPlayerSeekBar.this))))));
                    if(v4 < 0f) {
                        v4 = 0f;
                    }
                    else if(v4 > (((float)VideoPlayerSeekBar.access$400(VideoPlayerSeekBar.this).getWidth()))) {
                        v4 = ((float)VideoPlayerSeekBar.access$400(VideoPlayerSeekBar.this).getWidth());
                    }

                    VideoPlayerSeekBar.access$502(VideoPlayerSeekBar.this, v4 * 100f / (((float)VideoPlayerSeekBar.access$400(VideoPlayerSeekBar.this).getWidth())));
                    VideoPlayerSeekBar.this.setProgress(VideoPlayerSeekBar.access$500(VideoPlayerSeekBar.this), true);
                    if(VideoPlayerSeekBar.access$200(VideoPlayerSeekBar.this) != null) {
                        VideoPlayerSeekBar.access$200(VideoPlayerSeekBar.this).onSeekPre();
                    }

                    VideoPlayerSeekBar.access$002(VideoPlayerSeekBar.this, true);
                }
                else {
                    if((VideoPlayerSeekBar.access$000(VideoPlayerSeekBar.this)) && VideoPlayerSeekBar.access$200(VideoPlayerSeekBar.this) != null) {
                        VideoPlayerSeekBar.access$200(VideoPlayerSeekBar.this).onProgressChanged(VideoPlayerSeekBar.this, VideoPlayerSeekBar.access$500(VideoPlayerSeekBar.this), true);
                    }

                    VideoPlayerSeekBar.access$002(VideoPlayerSeekBar.this, false);
                }

                return 1;
            }
        });
    }

    static boolean access$000(VideoPlayerSeekBar arg0) {
        return arg0.mIsMoving;
    }

    static boolean access$002(VideoPlayerSeekBar arg0, boolean arg1) {
        arg0.mIsMoving = arg1;
        return arg1;
    }

    static float access$100(VideoPlayerSeekBar arg0) {
        return arg0.mDownX;
    }

    static float access$102(VideoPlayerSeekBar arg0, float arg1) {
        arg0.mDownX = arg1;
        return arg1;
    }

    static IVideoPlaySeekCallback access$200(VideoPlayerSeekBar arg0) {
        return arg0.mVideoPlayerSeekCallback;
    }

    static ImageView access$300(VideoPlayerSeekBar arg0) {
        return arg0.mFrontBar;
    }

    static ImageView access$400(VideoPlayerSeekBar arg0) {
        return arg0.mBackBar;
    }

    static float access$500(VideoPlayerSeekBar arg0) {
        return arg0.mLastMoveProgress;
    }

    static float access$502(VideoPlayerSeekBar arg0, float arg1) {
        arg0.mLastMoveProgress = arg1;
        return arg1;
    }

    public float getProgress() {
        return this.mProgress;
    }

    public void setProgress(float arg3, boolean arg4) {
        if((this.mIsMoving) && !arg4) {
            return;
        }

        float v1 = 100f;
        if(Float.compare(arg3, 0f) < 0) {
            arg3 = 0f;
        }
        else if(arg3 > v1) {
            arg3 = 100f;
        }

        this.mProgress = arg3;
        ViewGroup$LayoutParams v4 = this.mBarPoint.getLayoutParams();
        arg3 /= v1;
        ((FrameLayout$LayoutParams)v4).leftMargin = ((int)((((float)this.mBackBar.getWidth())) * arg3 - (((float)(this.mBarPoint.getWidth() / 2)))));
        this.mBarPoint.setLayoutParams(v4);
        this.mBarPoint.requestLayout();
        v4 = this.mFrontBar.getLayoutParams();
        ((FrameLayout$LayoutParams)v4).width = ((int)(arg3 * (((float)this.mBackBar.getWidth()))));
        this.mFrontBar.setLayoutParams(v4);
        this.mFrontBar.requestLayout();
    }

    public void updateCacheProgress(double arg12, double[] arg14) {
        double v4;
        double v0 = 0;
        int v2;
        for(v2 = 0; true; v2 += 2) {
            v4 = 100;
            if(v2 >= arg14.length) {
                break;
            }

            int v6 = v2 + 1;
            if(arg14.length > v6 && arg14[v2] / arg12 * v4 <= (((double)this.mProgress)) && arg14[v6] / arg12 * v4 > (((double)this.mProgress))) {
                v0 = arg14[v6] / arg12 * v4;
            }
        }

        ViewGroup$LayoutParams v12 = this.mMiddleBar.getLayoutParams();
        ((FrameLayout$LayoutParams)v12).width = ((int)(v0 / v4 * (((double)this.mBackBar.getWidth()))));
        this.mMiddleBar.setLayoutParams(v12);
        this.mMiddleBar.requestLayout();
    }
}

