package org.chromium.media;

class VideoCaptureFormat {
    final int mFramerate;
    int mHeight;
    final int mPixelFormat;
    int mWidth;

    public VideoCaptureFormat(int arg1, int arg2, int arg3, int arg4) {
        super();
        this.mWidth = arg1;
        this.mHeight = arg2;
        this.mFramerate = arg3;
        this.mPixelFormat = arg4;
    }

    public int getFramerate() {
        return this.mFramerate;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public int getPixelFormat() {
        return this.mPixelFormat;
    }

    public int getWidth() {
        return this.mWidth;
    }
}

