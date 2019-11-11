package org.chromium.content.browser.input;

import org.chromium.base.annotations.CalledByNative;

public class SuggestionInfo {
    private final int mMarkerTag;
    private final String mPrefix;
    private final String mSuffix;
    private final String mSuggestion;
    private final int mSuggestionIndex;

    SuggestionInfo(int arg1, int arg2, String arg3, String arg4, String arg5) {
        super();
        this.mMarkerTag = arg1;
        this.mSuggestionIndex = arg2;
        this.mPrefix = arg3;
        this.mSuggestion = arg4;
        this.mSuffix = arg5;
    }

    @CalledByNative private static SuggestionInfo[] createArray(int arg0) {
        return new SuggestionInfo[arg0];
    }

    @CalledByNative private static void createSuggestionInfoAndPutInArray(SuggestionInfo[] arg7, int arg8, int arg9, int arg10, String arg11, String arg12, String arg13) {
        arg7[arg8] = new SuggestionInfo(arg9, arg10, arg11, arg12, arg13);
    }

    public int getMarkerTag() {
        return this.mMarkerTag;
    }

    public String getPrefix() {
        return this.mPrefix;
    }

    public String getSuffix() {
        return this.mSuffix;
    }

    public String getSuggestion() {
        return this.mSuggestion;
    }

    public int getSuggestionIndex() {
        return this.mSuggestionIndex;
    }
}

