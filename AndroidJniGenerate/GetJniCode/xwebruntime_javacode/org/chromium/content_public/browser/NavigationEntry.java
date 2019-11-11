package org.chromium.content_public.browser;

import android.graphics.Bitmap;

public class NavigationEntry {
    private Bitmap mFavicon;
    private final int mIndex;
    private final String mOriginalUrl;
    private final String mTitle;
    private int mTransition;
    private final String mUrl;
    private final String mVirtualUrl;

    public NavigationEntry(int arg1, String arg2, String arg3, String arg4, String arg5, Bitmap arg6, int arg7) {
        super();
        this.mIndex = arg1;
        this.mUrl = arg2;
        this.mVirtualUrl = arg3;
        this.mOriginalUrl = arg4;
        this.mTitle = arg5;
        this.mFavicon = arg6;
        this.mTransition = arg7;
    }

    public Bitmap getFavicon() {
        return this.mFavicon;
    }

    public int getIndex() {
        return this.mIndex;
    }

    public String getOriginalUrl() {
        return this.mOriginalUrl;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public int getTransition() {
        return this.mTransition;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public String getVirtualUrl() {
        return this.mVirtualUrl;
    }

    public void updateFavicon(Bitmap arg1) {
        this.mFavicon = arg1;
    }
}

