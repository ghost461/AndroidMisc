package org.chromium.base;

import android.animation.Animator$AnimatorListener;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeAnimator$TimeListener;
import android.animation.TimeAnimator;
import android.util.Log;

public class AnimationFrameTimeHistogram {
    class Recorder implements TimeAnimator$TimeListener {
        private final TimeAnimator mAnimator;
        private int mFrameTimesCount;
        private long[] mFrameTimesMs;

        static {
        }

        Recorder(org.chromium.base.AnimationFrameTimeHistogram$1 arg1) {
            this();
        }

        private Recorder() {
            super();
            this.mAnimator = new TimeAnimator();
            this.mAnimator.setTimeListener(((TimeAnimator$TimeListener)this));
        }

        static void access$100(Recorder arg0) {
            arg0.startRecording();
        }

        static boolean access$200(Recorder arg0) {
            return arg0.endRecording();
        }

        static long[] access$300(Recorder arg0) {
            return arg0.getFrameTimesMs();
        }

        static int access$400(Recorder arg0) {
            return arg0.getFrameTimesCount();
        }

        static void access$500(Recorder arg0) {
            arg0.cleanUp();
        }

        private void cleanUp() {
            this.mFrameTimesMs = null;
        }

        private boolean endRecording() {
            boolean v0 = this.mAnimator.isStarted();
            this.mAnimator.end();
            return v0;
        }

        private int getFrameTimesCount() {
            return this.mFrameTimesCount;
        }

        private long[] getFrameTimesMs() {
            return this.mFrameTimesMs;
        }

        public void onTimeUpdate(TimeAnimator arg1, long arg2, long arg4) {
            if(this.mFrameTimesCount == this.mFrameTimesMs.length) {
                this.mAnimator.end();
                this.cleanUp();
                Log.w("AnimationFrameTimeHistogram", "Animation frame time recording reached the maximum number. It\'s eitherthe animation took too long or recording end is not called.");
                return;
            }

            if(arg4 > 0) {
                long[] v1 = this.mFrameTimesMs;
                int v2 = this.mFrameTimesCount;
                this.mFrameTimesCount = v2 + 1;
                v1[v2] = arg4;
            }
        }

        private void startRecording() {
            this.mFrameTimesCount = 0;
            this.mFrameTimesMs = new long[600];
            this.mAnimator.start();
        }
    }

    private static final int MAX_FRAME_TIME_NUM = 600;
    private static final String TAG = "AnimationFrameTimeHistogram";
    private final String mHistogramName;
    private final Recorder mRecorder;

    public AnimationFrameTimeHistogram(String arg3) {
        super();
        this.mRecorder = new Recorder(null);
        this.mHistogramName = arg3;
    }

    public void endRecording() {
        if(Recorder.access$200(this.mRecorder)) {
            this.nativeSaveHistogram(this.mHistogramName, Recorder.access$300(this.mRecorder), Recorder.access$400(this.mRecorder));
        }

        Recorder.access$500(this.mRecorder);
    }

    public static Animator$AnimatorListener getAnimatorRecorder(String arg1) {
        return new AnimatorListenerAdapter(arg1) {
            private final AnimationFrameTimeHistogram mAnimationFrameTimeHistogram;

            public void onAnimationCancel(Animator arg1) {
                this.mAnimationFrameTimeHistogram.endRecording();
            }

            public void onAnimationEnd(Animator arg1) {
                this.mAnimationFrameTimeHistogram.endRecording();
            }

            public void onAnimationStart(Animator arg1) {
                this.mAnimationFrameTimeHistogram.startRecording();
            }
        };
    }

    private native void nativeSaveHistogram(String arg1, long[] arg2, int arg3) {
    }

    public void startRecording() {
        Recorder.access$100(this.mRecorder);
    }
}

