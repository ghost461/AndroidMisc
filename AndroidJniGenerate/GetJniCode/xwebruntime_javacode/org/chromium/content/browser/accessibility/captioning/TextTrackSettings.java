package org.chromium.content.browser.accessibility.captioning;

import android.annotation.TargetApi;
import java.util.Objects;

@TargetApi(value=19) public final class TextTrackSettings {
    private static final String DEFAULT_VALUE = "";
    private String mTextTrackBackgroundColor;
    private String mTextTrackFontFamily;
    private String mTextTrackFontStyle;
    private String mTextTrackFontVariant;
    private String mTextTrackTextColor;
    private String mTextTrackTextShadow;
    private String mTextTrackTextSize;
    private boolean mTextTracksEnabled;

    public TextTrackSettings(boolean arg1, String arg2, String arg3, String arg4, String arg5, String arg6, String arg7, String arg8) {
        super();
        this.mTextTracksEnabled = arg1;
        this.mTextTrackBackgroundColor = arg2;
        this.mTextTrackFontFamily = arg3;
        this.mTextTrackFontStyle = arg4;
        this.mTextTrackFontVariant = arg5;
        this.mTextTrackTextColor = arg6;
        this.mTextTrackTextShadow = arg7;
        this.mTextTrackTextSize = arg8;
    }

    public TextTrackSettings() {
        super();
    }

    public String getTextTrackBackgroundColor() {
        return Objects.toString(this.mTextTrackBackgroundColor, "");
    }

    public String getTextTrackFontFamily() {
        return Objects.toString(this.mTextTrackFontFamily, "");
    }

    public String getTextTrackFontStyle() {
        return Objects.toString(this.mTextTrackFontStyle, "");
    }

    public String getTextTrackFontVariant() {
        return Objects.toString(this.mTextTrackFontVariant, "");
    }

    public String getTextTrackTextColor() {
        return Objects.toString(this.mTextTrackTextColor, "");
    }

    public String getTextTrackTextShadow() {
        return Objects.toString(this.mTextTrackTextShadow, "");
    }

    public String getTextTrackTextSize() {
        return Objects.toString(this.mTextTrackTextSize, "");
    }

    public boolean getTextTracksEnabled() {
        return this.mTextTracksEnabled;
    }
}

