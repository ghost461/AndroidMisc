package org.chromium.content_public.common;

public class Referrer {
    private final int mPolicy;
    private final String mUrl;

    public Referrer(String arg1, int arg2) {
        super();
        this.mUrl = arg1;
        this.mPolicy = arg2;
    }

    public int getPolicy() {
        return this.mPolicy;
    }

    public String getUrl() {
        return this.mUrl;
    }
}

