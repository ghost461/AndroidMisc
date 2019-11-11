package org.chromium.content.browser.accessibility.captioning;

import android.graphics.Color;
import android.graphics.Typeface;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.WeakHashMap;
import org.chromium.base.VisibleForTesting;

public class CaptioningChangeDelegate {
    public enum ClosedCaptionEdgeAttribute {
        public static final enum ClosedCaptionEdgeAttribute DEPRESSED = null;
        public static final enum ClosedCaptionEdgeAttribute DROP_SHADOW = null;
        public static final enum ClosedCaptionEdgeAttribute NONE = null;
        public static final enum ClosedCaptionEdgeAttribute OUTLINE = null;
        public static final enum ClosedCaptionEdgeAttribute RAISED = null;
        private final String mTextShadow;
        private static String sDefaultEdgeColor = "silver";
        private static String sEdgeColor = null;
        private static String sShadowOffset = "0.05em";

        static {
            ClosedCaptionEdgeAttribute.NONE = new ClosedCaptionEdgeAttribute("NONE", 0, "");
            ClosedCaptionEdgeAttribute.OUTLINE = new ClosedCaptionEdgeAttribute("OUTLINE", 1, "%2$s %2$s 0 %1$s, -%2$s -%2$s 0 %1$s, %2$s -%2$s 0 %1$s, -%2$s %2$s 0 %1$s");
            ClosedCaptionEdgeAttribute.DROP_SHADOW = new ClosedCaptionEdgeAttribute("DROP_SHADOW", 2, "%1$s %2$s %2$s 0.1em");
            ClosedCaptionEdgeAttribute.RAISED = new ClosedCaptionEdgeAttribute("RAISED", 3, "-%2$s -%2$s 0 %1$s");
            ClosedCaptionEdgeAttribute.DEPRESSED = new ClosedCaptionEdgeAttribute("DEPRESSED", 4, "%2$s %2$s 0 %1$s");
            ClosedCaptionEdgeAttribute.$VALUES = new ClosedCaptionEdgeAttribute[]{ClosedCaptionEdgeAttribute.NONE, ClosedCaptionEdgeAttribute.OUTLINE, ClosedCaptionEdgeAttribute.DROP_SHADOW, ClosedCaptionEdgeAttribute.RAISED, ClosedCaptionEdgeAttribute.DEPRESSED};
        }

        private ClosedCaptionEdgeAttribute(String arg1, int arg2, String arg3) {
            super(arg1, arg2);
            this.mTextShadow = arg3;
        }

        public static ClosedCaptionEdgeAttribute fromSystemEdgeAttribute(Integer arg1, String arg2) {
            if(arg1 == null) {
                return ClosedCaptionEdgeAttribute.NONE;
            }

            ClosedCaptionEdgeAttribute.sEdgeColor = arg2 == null || (arg2.isEmpty()) ? ClosedCaptionEdgeAttribute.sDefaultEdgeColor : arg2;
            switch(arg1.intValue()) {
                case 1: {
                    goto label_21;
                }
                case 2: {
                    goto label_19;
                }
                case 3: {
                    goto label_17;
                }
                case 4: {
                    goto label_15;
                }
            }

            return ClosedCaptionEdgeAttribute.NONE;
        label_17:
            return ClosedCaptionEdgeAttribute.RAISED;
        label_19:
            return ClosedCaptionEdgeAttribute.DROP_SHADOW;
        label_21:
            return ClosedCaptionEdgeAttribute.OUTLINE;
        label_15:
            return ClosedCaptionEdgeAttribute.DEPRESSED;
        }

        public String getTextShadow() {
            return String.format(this.mTextShadow, ClosedCaptionEdgeAttribute.sEdgeColor, ClosedCaptionEdgeAttribute.sShadowOffset);
        }

        public static void setDefaultEdgeColor(String arg0) {
            ClosedCaptionEdgeAttribute.sDefaultEdgeColor = arg0;
        }

        public static void setShadowOffset(String arg0) {
            ClosedCaptionEdgeAttribute.sShadowOffset = arg0;
        }

        public static ClosedCaptionEdgeAttribute valueOf(String arg1) {
            return Enum.valueOf(ClosedCaptionEdgeAttribute.class, arg1);
        }

        public static ClosedCaptionEdgeAttribute[] values() {
            return ClosedCaptionEdgeAttribute.$VALUES.clone();
        }
    }

    public enum ClosedCaptionFont {
        @VisibleForTesting enum Flags {
            public static final enum Flags MONOSPACE;
            public static final enum Flags SANS_SERIF;
            public static final enum Flags SERIF;

            static {
                Flags.SANS_SERIF = new Flags("SANS_SERIF", 0);
                Flags.SERIF = new Flags("SERIF", 1);
                Flags.MONOSPACE = new Flags("MONOSPACE", 2);
                Flags.$VALUES = new Flags[]{Flags.SANS_SERIF, Flags.SERIF, Flags.MONOSPACE};
            }

            private Flags(String arg1, int arg2) {
                super(arg1, arg2);
            }

            public static Flags valueOf(String arg1) {
                return Enum.valueOf(Flags.class, arg1);
            }

            public static Flags[] values() {
                return Flags.$VALUES.clone();
            }
        }

        public static final enum ClosedCaptionFont CASUAL;
        public static final enum ClosedCaptionFont CURSIVE;
        public static final enum ClosedCaptionFont DEFAULT;
        public static final enum ClosedCaptionFont MONOSPACE;
        public static final enum ClosedCaptionFont SANS_SERIF;
        public static final enum ClosedCaptionFont SANS_SERIF_CONDENSED;
        public static final enum ClosedCaptionFont SANS_SERIF_MONOSPACE;
        public static final enum ClosedCaptionFont SANS_SERIF_SMALLCAPS;
        public static final enum ClosedCaptionFont SERIF;
        public static final enum ClosedCaptionFont SERIF_MONOSPACE;
        @VisibleForTesting final EnumSet mFlags;
        private final String mFontFamily;

        static {
            ClosedCaptionFont.DEFAULT = new ClosedCaptionFont("DEFAULT", 0, "", EnumSet.noneOf(Flags.class));
            ClosedCaptionFont.SANS_SERIF = new ClosedCaptionFont("SANS_SERIF", 1, "sans-serif", EnumSet.of(Flags.SANS_SERIF));
            ClosedCaptionFont.SANS_SERIF_CONDENSED = new ClosedCaptionFont("SANS_SERIF_CONDENSED", 2, "sans-serif-condensed", EnumSet.of(Flags.SANS_SERIF));
            ClosedCaptionFont.SANS_SERIF_MONOSPACE = new ClosedCaptionFont("SANS_SERIF_MONOSPACE", 3, "sans-serif-monospace", EnumSet.of(Flags.SANS_SERIF, Flags.MONOSPACE));
            ClosedCaptionFont.SERIF = new ClosedCaptionFont("SERIF", 4, "serif", EnumSet.of(Flags.SERIF));
            ClosedCaptionFont.SERIF_MONOSPACE = new ClosedCaptionFont("SERIF_MONOSPACE", 5, "serif-monospace", EnumSet.of(Flags.SERIF, Flags.MONOSPACE));
            ClosedCaptionFont.CASUAL = new ClosedCaptionFont("CASUAL", 6, "casual", EnumSet.noneOf(Flags.class));
            ClosedCaptionFont.CURSIVE = new ClosedCaptionFont("CURSIVE", 7, "cursive", EnumSet.noneOf(Flags.class));
            ClosedCaptionFont.SANS_SERIF_SMALLCAPS = new ClosedCaptionFont("SANS_SERIF_SMALLCAPS", 8, "sans-serif-smallcaps", EnumSet.of(Flags.SANS_SERIF));
            ClosedCaptionFont.MONOSPACE = new ClosedCaptionFont("MONOSPACE", 9, "monospace", EnumSet.of(Flags.MONOSPACE));
            ClosedCaptionFont.$VALUES = new ClosedCaptionFont[]{ClosedCaptionFont.DEFAULT, ClosedCaptionFont.SANS_SERIF, ClosedCaptionFont.SANS_SERIF_CONDENSED, ClosedCaptionFont.SANS_SERIF_MONOSPACE, ClosedCaptionFont.SERIF, ClosedCaptionFont.SERIF_MONOSPACE, ClosedCaptionFont.CASUAL, ClosedCaptionFont.CURSIVE, ClosedCaptionFont.SANS_SERIF_SMALLCAPS, ClosedCaptionFont.MONOSPACE};
        }

        private ClosedCaptionFont(String arg1, int arg2, String arg3, EnumSet arg4) {
            super(arg1, arg2);
            this.mFontFamily = arg3;
            this.mFlags = arg4;
        }

        private static boolean belongsToFontFamily(Typeface arg1, ClosedCaptionFont arg2) {
            return Typeface.create(arg2.getFontFamily(), arg1.getStyle()).equals(arg1);
        }

        public static ClosedCaptionFont fromSystemFont(Typeface arg5) {
            if(arg5 == null) {
                return ClosedCaptionFont.DEFAULT;
            }

            ClosedCaptionFont[] v0 = ClosedCaptionFont.values();
            int v1 = v0.length;
            int v2;
            for(v2 = 0; v2 < v1; ++v2) {
                ClosedCaptionFont v3 = v0[v2];
                if(ClosedCaptionFont.belongsToFontFamily(arg5, v3)) {
                    return v3;
                }
            }

            return ClosedCaptionFont.DEFAULT;
        }

        public String getFontFamily() {
            return this.mFontFamily;
        }

        public static ClosedCaptionFont valueOf(String arg1) {
            return Enum.valueOf(ClosedCaptionFont.class, arg1);
        }

        public static ClosedCaptionFont[] values() {
            return ClosedCaptionFont.$VALUES.clone();
        }
    }

    @VisibleForTesting public static final String DEFAULT_CAPTIONING_PREF_VALUE = "";
    private static final String FONT_STYLE_ITALIC = "italic";
    private final Map mListeners;
    private String mTextTrackBackgroundColor;
    private String mTextTrackFontFamily;
    private String mTextTrackFontStyle;
    private String mTextTrackFontVariant;
    private String mTextTrackTextColor;
    private String mTextTrackTextShadow;
    private String mTextTrackTextSize;
    private boolean mTextTracksEnabled;

    public CaptioningChangeDelegate() {
        super();
        this.mListeners = new WeakHashMap();
    }

    public void addListener(SystemCaptioningBridgeListener arg3) {
        this.mListeners.put(arg3, null);
    }

    public static String androidColorToCssColor(Integer arg5) {
        if(arg5 == null) {
            return "";
        }

        return String.format("rgba(%s, %s, %s, %s)", Integer.valueOf(Color.red(arg5.intValue())), Integer.valueOf(Color.green(arg5.intValue())), Integer.valueOf(Color.blue(arg5.intValue())), new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.US)).format((((double)Color.alpha(arg5.intValue()))) / 255));
    }

    public static String androidFontScaleToPercentage(float arg4) {
        return new DecimalFormat("#%", new DecimalFormatSymbols(Locale.US)).format(((double)arg4));
    }

    public boolean hasActiveListener() {
        return this.mListeners.isEmpty() ^ 1;
    }

    public void notifyListener(SystemCaptioningBridgeListener arg11) {
        if(this.mTextTracksEnabled) {
            arg11.onSystemCaptioningChanged(new TextTrackSettings(this.mTextTracksEnabled, this.mTextTrackBackgroundColor, this.mTextTrackFontFamily, this.mTextTrackFontStyle, this.mTextTrackFontVariant, this.mTextTrackTextColor, this.mTextTrackTextShadow, this.mTextTrackTextSize));
        }
        else {
            arg11.onSystemCaptioningChanged(new TextTrackSettings());
        }
    }

    private void notifySettingsChanged() {
        Iterator v0 = this.mListeners.keySet().iterator();
        while(v0.hasNext()) {
            this.notifyListener(v0.next());
        }
    }

    public void onEnabledChanged(boolean arg1) {
        this.mTextTracksEnabled = arg1;
        this.notifySettingsChanged();
    }

    public void onFontScaleChanged(float arg1) {
        this.mTextTrackTextSize = CaptioningChangeDelegate.androidFontScaleToPercentage(arg1);
        this.notifySettingsChanged();
    }

    public void onLocaleChanged(Locale arg1) {
    }

    public void onUserStyleChanged(CaptioningStyle arg3) {
        this.mTextTrackTextColor = CaptioningChangeDelegate.androidColorToCssColor(arg3.getForegroundColor());
        this.mTextTrackBackgroundColor = CaptioningChangeDelegate.androidColorToCssColor(arg3.getBackgroundColor());
        this.mTextTrackTextShadow = ClosedCaptionEdgeAttribute.fromSystemEdgeAttribute(arg3.getEdgeType(), CaptioningChangeDelegate.androidColorToCssColor(arg3.getEdgeColor())).getTextShadow();
        Typeface v3 = arg3.getTypeface();
        this.mTextTrackFontFamily = ClosedCaptionFont.fromSystemFont(v3).getFontFamily();
        this.mTextTrackFontStyle = v3 == null || !v3.isItalic() ? "" : "italic";
        this.mTextTrackFontVariant = "";
        this.notifySettingsChanged();
    }

    public void removeListener(SystemCaptioningBridgeListener arg2) {
        this.mListeners.remove(arg2);
    }
}

