package org.chromium.media;

import android.media.MediaPlayer$OnBufferingUpdateListener;
import android.media.MediaPlayer$OnCompletionListener;
import android.media.MediaPlayer$OnErrorListener;
import android.media.MediaPlayer$OnPreparedListener;
import android.media.MediaPlayer$OnSeekCompleteListener;
import android.media.MediaPlayer$OnVideoSizeChangedListener;
import android.media.MediaPlayer;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="media") class MediaPlayerListener implements MediaPlayer$OnBufferingUpdateListener, MediaPlayer$OnCompletionListener, MediaPlayer$OnErrorListener, MediaPlayer$OnPreparedListener, MediaPlayer$OnSeekCompleteListener, MediaPlayer$OnVideoSizeChangedListener {
    private static final int MEDIA_ERROR_DECODE = 1;
    private static final int MEDIA_ERROR_FORMAT = 0;
    private static final int MEDIA_ERROR_INVALID_CODE = 3;
    public static final int MEDIA_ERROR_MALFORMED = -1007;
    private static final int MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK = 2;
    private static final int MEDIA_ERROR_SERVER_DIED = 4;
    public static final int MEDIA_ERROR_TIMED_OUT = -110;
    private long mNativeMediaPlayerListener;

    private MediaPlayerListener(long arg1) {
        super();
        this.mNativeMediaPlayerListener = arg1;
    }

    @CalledByNative private static MediaPlayerListener create(long arg1, MediaPlayerBridge arg3) {
        MediaPlayerListener v0 = new MediaPlayerListener(arg1);
        if(arg3 != null) {
            arg3.setOnBufferingUpdateListener(((MediaPlayer$OnBufferingUpdateListener)v0));
            arg3.setOnCompletionListener(((MediaPlayer$OnCompletionListener)v0));
            arg3.setOnErrorListener(((MediaPlayer$OnErrorListener)v0));
            arg3.setOnPreparedListener(((MediaPlayer$OnPreparedListener)v0));
            arg3.setOnSeekCompleteListener(((MediaPlayer$OnSeekCompleteListener)v0));
            arg3.setOnVideoSizeChangedListener(((MediaPlayer$OnVideoSizeChangedListener)v0));
        }

        return v0;
    }

    private native void nativeOnBufferingUpdate(long arg1, int arg2) {
    }

    private native void nativeOnMediaError(long arg1, int arg2) {
    }

    private native void nativeOnMediaInterrupted(long arg1) {
    }

    private native void nativeOnMediaPrepared(long arg1) {
    }

    private native void nativeOnPlaybackComplete(long arg1) {
    }

    private native void nativeOnSeekComplete(long arg1) {
    }

    private native void nativeOnVideoSizeChanged(long arg1, int arg2, int arg3) {
    }

    public void onBufferingUpdate(MediaPlayer arg3, int arg4) {
        this.nativeOnBufferingUpdate(this.mNativeMediaPlayerListener, arg4);
    }

    public void onCompletion(MediaPlayer arg3) {
        this.nativeOnPlaybackComplete(this.mNativeMediaPlayerListener);
    }

    public boolean onError(MediaPlayer arg2, int arg3, int arg4) {
        int v2 = 3;
        if(arg3 != 1) {
            if(arg3 == 100) {
                v2 = 4;
            }
            else if(arg3 != 200) {
            }
            else {
                v2 = 2;
            }
        }
        else if(arg4 == -1007) {
            v2 = 1;
        }
        else if(arg4 != -110) {
            v2 = 0;
        }

        this.nativeOnMediaError(this.mNativeMediaPlayerListener, v2);
        return 1;
    }

    public void onPrepared(MediaPlayer arg3) {
        this.nativeOnMediaPrepared(this.mNativeMediaPlayerListener);
    }

    public void onSeekComplete(MediaPlayer arg3) {
        this.nativeOnSeekComplete(this.mNativeMediaPlayerListener);
    }

    public void onVideoSizeChanged(MediaPlayer arg3, int arg4, int arg5) {
        this.nativeOnVideoSizeChanged(this.mNativeMediaPlayerListener, arg4, arg5);
    }
}

