package android.support.v4.text;

import android.text.SpannableStringBuilder;
import java.util.Locale;

public final class BidiFormatter {
    class android.support.v4.text.BidiFormatter$1 {
    }

    public final class Builder {
        private int mFlags;
        private boolean mIsRtlContext;
        private TextDirectionHeuristicCompat mTextDirectionHeuristicCompat;

        public Builder() {
            super();
            this.initialize(BidiFormatter.isRtlLocale(Locale.getDefault()));
        }

        public Builder(Locale arg1) {
            super();
            this.initialize(BidiFormatter.isRtlLocale(arg1));
        }

        public Builder(boolean arg1) {
            super();
            this.initialize(arg1);
        }

        public BidiFormatter build() {
            if(this.mFlags == 2 && this.mTextDirectionHeuristicCompat == BidiFormatter.DEFAULT_TEXT_DIRECTION_HEURISTIC) {
                return Builder.getDefaultInstanceFromContext(this.mIsRtlContext);
            }

            return new BidiFormatter(this.mIsRtlContext, this.mFlags, this.mTextDirectionHeuristicCompat, null);
        }

        private static BidiFormatter getDefaultInstanceFromContext(boolean arg0) {
            BidiFormatter v0 = arg0 ? BidiFormatter.DEFAULT_RTL_INSTANCE : BidiFormatter.DEFAULT_LTR_INSTANCE;
            return v0;
        }

        private void initialize(boolean arg1) {
            this.mIsRtlContext = arg1;
            this.mTextDirectionHeuristicCompat = BidiFormatter.DEFAULT_TEXT_DIRECTION_HEURISTIC;
            this.mFlags = 2;
        }

        public Builder setTextDirectionHeuristic(TextDirectionHeuristicCompat arg1) {
            this.mTextDirectionHeuristicCompat = arg1;
            return this;
        }

        public Builder stereoReset(boolean arg1) {
            if(arg1) {
                this.mFlags |= 2;
            }
            else {
                this.mFlags &= -3;
            }

            return this;
        }
    }

    class DirectionalityEstimator {
        private static final byte[] DIR_TYPE_CACHE = null;
        private static final int DIR_TYPE_CACHE_SIZE = 0x700;
        private int charIndex;
        private final boolean isHtml;
        private char lastChar;
        private final int length;
        private final CharSequence text;

        static {
            int v0 = 0x700;
            DirectionalityEstimator.DIR_TYPE_CACHE = new byte[v0];
            int v1;
            for(v1 = 0; v1 < v0; ++v1) {
                DirectionalityEstimator.DIR_TYPE_CACHE[v1] = Character.getDirectionality(v1);
            }
        }

        DirectionalityEstimator(CharSequence arg1, boolean arg2) {
            super();
            this.text = arg1;
            this.isHtml = arg2;
            this.length = arg1.length();
        }

        byte dirTypeBackward() {
            this.lastChar = this.text.charAt(this.charIndex - 1);
            if(Character.isLowSurrogate(this.lastChar)) {
                int v0 = Character.codePointBefore(this.text, this.charIndex);
                this.charIndex -= Character.charCount(v0);
                return Character.getDirectionality(v0);
            }

            --this.charIndex;
            byte v0_1 = DirectionalityEstimator.getCachedDirectionality(this.lastChar);
            if(this.isHtml) {
                if(this.lastChar == 62) {
                    v0_1 = this.skipTagBackward();
                }
                else if(this.lastChar == 59) {
                    v0_1 = this.skipEntityBackward();
                }
            }

            return v0_1;
        }

        byte dirTypeForward() {
            this.lastChar = this.text.charAt(this.charIndex);
            if(Character.isHighSurrogate(this.lastChar)) {
                int v0 = Character.codePointAt(this.text, this.charIndex);
                this.charIndex += Character.charCount(v0);
                return Character.getDirectionality(v0);
            }

            ++this.charIndex;
            byte v0_1 = DirectionalityEstimator.getCachedDirectionality(this.lastChar);
            if(this.isHtml) {
                if(this.lastChar == 60) {
                    v0_1 = this.skipTagForward();
                }
                else if(this.lastChar == 38) {
                    v0_1 = this.skipEntityForward();
                }
            }

            return v0_1;
        }

        private static byte getCachedDirectionality(char arg1) {
            byte v1 = arg1 < 0x700 ? DirectionalityEstimator.DIR_TYPE_CACHE[arg1] : Character.getDirectionality(arg1);
            return v1;
        }

        int getEntryDir() {
            this.charIndex = 0;
            int v2 = -1;
            int v3 = 0;
            int v4 = 0;
            int v5 = 0;
        label_7:
            while(this.charIndex < this.length) {
                if(v3 != 0) {
                    break;
                }

                int v6 = this.dirTypeForward();
                if(v6 == 9) {
                    continue;
                }

                switch(v6) {
                    case 0: {
                        goto label_28;
                    }
                    case 1: 
                    case 2: {
                        goto label_26;
                    }
                    default: {
                        switch(v6) {
                            case 14: 
                            case 15: {
                                ++v5;
                                v4 = -1;
                                goto label_7;
                            label_26:
                                if(v5 == 0) {
                                    return 1;
                                label_28:
                                    if(v5 == 0) {
                                        return v2;
                                    }
                                    else {
                                        goto label_30;
                                    }
                                }
                                else {
                                    goto label_30;
                                }

                                goto label_32;
                            }
                            case 16: 
                            case 17: {
                                ++v5;
                                v4 = 1;
                                goto label_7;
                            }
                            case 18: {
                                --v5;
                                v4 = 0;
                                goto label_7;
                            }
                            default: {
                                goto label_30;
                            }
                        }
                    }
                }

            label_30:
                v3 = v5;
            }

        label_32:
            if(v3 == 0) {
                return 0;
            }

            if(v4 != 0) {
                return v4;
            }

            while(this.charIndex > 0) {
                switch(this.dirTypeBackward()) {
                    case 14: 
                    case 15: {
                        goto label_47;
                    }
                    case 16: 
                    case 17: {
                        goto label_43;
                    }
                    case 18: {
                        goto label_41;
                    }
                }

                continue;
            label_41:
                ++v5;
                continue;
            label_43:
                if(v3 == v5) {
                    return 1;
                }

                --v5;
                continue;
            label_47:
                if(v3 == v5) {
                    return v2;
                }

                --v5;
            }

            return 0;
        }

        int getExitDir() {
            this.charIndex = this.length;
            int v1 = 0;
            int v2 = 0;
        label_5:
            while(this.charIndex > 0) {
                int v3 = this.dirTypeBackward();
                if(v3 == 9) {
                    continue;
                }

                int v5 = -1;
                switch(v3) {
                    case 0: {
                        goto label_30;
                    }
                    case 1: 
                    case 2: {
                        goto label_26;
                    }
                    default: {
                        switch(v3) {
                            case 14: 
                            case 15: {
                                if(v1 == v2) {
                                    return v5;
                                }

                                --v2;
                                goto label_5;
                            }
                            case 16: 
                            case 17: {
                                if(v1 == v2) {
                                    return 1;
                                }

                                --v2;
                                goto label_5;
                            }
                            case 18: {
                                ++v2;
                                goto label_5;
                            label_26:
                                if(v2 == 0) {
                                    return 1;
                                }

                                if(v1 != 0) {
                                    goto label_5;
                                }

                                goto label_33;
                            label_30:
                                if(v2 == 0) {
                                    return v5;
                                }

                                if(v1 != 0) {
                                    goto label_5;
                                }

                                goto label_33;
                            }
                            default: {
                                if(v1 != 0) {
                                    goto label_5;
                                }

                                goto label_33;
                            }
                        }
                    }
                }

            label_33:
                v1 = v2;
            }

            return 0;
        }

        private byte skipEntityBackward() {
            char v2;
            int v0 = this.charIndex;
            do {
                v2 = ';';
                if(this.charIndex <= 0) {
                    break;
                }

                CharSequence v1 = this.text;
                int v3 = this.charIndex - 1;
                this.charIndex = v3;
                this.lastChar = v1.charAt(v3);
                if(this.lastChar == 38) {
                    return 12;
                }
                else if(this.lastChar != v2) {
                    continue;
                }

                break;
            }
            while(true);

            this.charIndex = v0;
            this.lastChar = v2;
            return 13;
        }

        private byte skipEntityForward() {
            while(this.charIndex < this.length) {
                CharSequence v0 = this.text;
                int v1 = this.charIndex;
                this.charIndex = v1 + 1;
                char v0_1 = v0.charAt(v1);
                this.lastChar = v0_1;
                if(v0_1 == 59) {
                    return 12;
                }
            }

            return 12;
        }

        private byte skipTagBackward() {
            int v3;
            char v2;
            int v0 = this.charIndex;
            do {
            label_1:
                v2 = '>';
                if(this.charIndex > 0) {
                    CharSequence v1 = this.text;
                    v3 = this.charIndex - 1;
                    this.charIndex = v3;
                    this.lastChar = v1.charAt(v3);
                    if(this.lastChar == 60) {
                        return 12;
                    }
                    else if(this.lastChar == v2) {
                    }
                    else {
                        if(this.lastChar != 34 && this.lastChar != 39) {
                            continue;
                        }

                        break;
                    }
                }

                goto label_35;
            }
            while(true);

            char v1_1 = this.lastChar;
            goto label_25;
        label_35:
            this.charIndex = v0;
            this.lastChar = v2;
            return 13;
            while(true) {
            label_25:
                if(this.charIndex <= 0) {
                    goto label_1;
                }

                CharSequence v2_1 = this.text;
                v3 = this.charIndex - 1;
                this.charIndex = v3;
                v2 = v2_1.charAt(v3);
                this.lastChar = v2;
                if(v2 == v1_1) {
                    goto label_1;
                }
            }
        }

        private byte skipTagForward() {
            int v0 = this.charIndex;
            do {
            label_1:
                if(this.charIndex >= this.length) {
                    goto label_33;
                }

                CharSequence v1 = this.text;
                int v2 = this.charIndex;
                this.charIndex = v2 + 1;
                this.lastChar = v1.charAt(v2);
                if(this.lastChar == 62) {
                    return 12;
                }

                if(this.lastChar == 34) {
                    break;
                }
            }
            while(this.lastChar != 39);

            char v1_1 = this.lastChar;
            goto label_22;
        label_33:
            this.charIndex = v0;
            this.lastChar = '<';
            return 13;
            while(true) {
            label_22:
                if(this.charIndex >= this.length) {
                    goto label_1;
                }

                CharSequence v2_1 = this.text;
                int v3 = this.charIndex;
                this.charIndex = v3 + 1;
                char v2_2 = v2_1.charAt(v3);
                this.lastChar = v2_2;
                if(v2_2 == v1_1) {
                    goto label_1;
                }
            }
        }
    }

    private static final int DEFAULT_FLAGS = 2;
    private static final BidiFormatter DEFAULT_LTR_INSTANCE = null;
    private static final BidiFormatter DEFAULT_RTL_INSTANCE = null;
    private static TextDirectionHeuristicCompat DEFAULT_TEXT_DIRECTION_HEURISTIC = null;
    private static final int DIR_LTR = -1;
    private static final int DIR_RTL = 1;
    private static final int DIR_UNKNOWN = 0;
    private static final String EMPTY_STRING = "";
    private static final int FLAG_STEREO_RESET = 2;
    private static final char LRE = '‪';
    private static final char LRM = '‎';
    private static final String LRM_STRING = null;
    private static final char PDF = '‬';
    private static final char RLE = '‫';
    private static final char RLM = '‏';
    private static final String RLM_STRING;
    private final TextDirectionHeuristicCompat mDefaultTextDirectionHeuristicCompat;
    private final int mFlags;
    private final boolean mIsRtlContext;

    static {
        BidiFormatter.DEFAULT_TEXT_DIRECTION_HEURISTIC = TextDirectionHeuristicsCompat.FIRSTSTRONG_LTR;
        BidiFormatter.LRM_STRING = Character.toString('‎');
        BidiFormatter.RLM_STRING = Character.toString('‏');
        BidiFormatter.DEFAULT_LTR_INSTANCE = new BidiFormatter(false, 2, BidiFormatter.DEFAULT_TEXT_DIRECTION_HEURISTIC);
        BidiFormatter.DEFAULT_RTL_INSTANCE = new BidiFormatter(true, 2, BidiFormatter.DEFAULT_TEXT_DIRECTION_HEURISTIC);
    }

    private BidiFormatter(boolean arg1, int arg2, TextDirectionHeuristicCompat arg3) {
        super();
        this.mIsRtlContext = arg1;
        this.mFlags = arg2;
        this.mDefaultTextDirectionHeuristicCompat = arg3;
    }

    BidiFormatter(boolean arg1, int arg2, TextDirectionHeuristicCompat arg3, android.support.v4.text.BidiFormatter$1 arg4) {
        this(arg1, arg2, arg3);
    }

    static boolean access$000(Locale arg0) {
        return BidiFormatter.isRtlLocale(arg0);
    }

    static TextDirectionHeuristicCompat access$100() {
        return BidiFormatter.DEFAULT_TEXT_DIRECTION_HEURISTIC;
    }

    static BidiFormatter access$200() {
        return BidiFormatter.DEFAULT_RTL_INSTANCE;
    }

    static BidiFormatter access$300() {
        return BidiFormatter.DEFAULT_LTR_INSTANCE;
    }

    private static int getEntryDir(CharSequence arg2) {
        return new DirectionalityEstimator(arg2, false).getEntryDir();
    }

    private static int getExitDir(CharSequence arg2) {
        return new DirectionalityEstimator(arg2, false).getExitDir();
    }

    public static BidiFormatter getInstance() {
        return new Builder().build();
    }

    public static BidiFormatter getInstance(Locale arg1) {
        return new Builder(arg1).build();
    }

    public static BidiFormatter getInstance(boolean arg1) {
        return new Builder(arg1).build();
    }

    public boolean getStereoReset() {
        boolean v0 = (this.mFlags & 2) != 0 ? true : false;
        return v0;
    }

    public boolean isRtl(CharSequence arg4) {
        return this.mDefaultTextDirectionHeuristicCompat.isRtl(arg4, 0, arg4.length());
    }

    public boolean isRtl(String arg1) {
        return this.isRtl(((CharSequence)arg1));
    }

    public boolean isRtlContext() {
        return this.mIsRtlContext;
    }

    private static boolean isRtlLocale(Locale arg1) {
        boolean v0 = true;
        if(TextUtilsCompat.getLayoutDirectionFromLocale(arg1) == 1) {
        }
        else {
            v0 = false;
        }

        return v0;
    }

    private String markAfter(CharSequence arg3, TextDirectionHeuristicCompat arg4) {
        boolean v4 = arg4.isRtl(arg3, 0, arg3.length());
        if(!this.mIsRtlContext && ((v4) || BidiFormatter.getExitDir(arg3) == 1)) {
            return BidiFormatter.LRM_STRING;
        }

        if((this.mIsRtlContext) && (!v4 || BidiFormatter.getExitDir(arg3) == -1)) {
            return BidiFormatter.RLM_STRING;
        }

        return "";
    }

    private String markBefore(CharSequence arg3, TextDirectionHeuristicCompat arg4) {
        boolean v4 = arg4.isRtl(arg3, 0, arg3.length());
        if(!this.mIsRtlContext && ((v4) || BidiFormatter.getEntryDir(arg3) == 1)) {
            return BidiFormatter.LRM_STRING;
        }

        if((this.mIsRtlContext) && (!v4 || BidiFormatter.getEntryDir(arg3) == -1)) {
            return BidiFormatter.RLM_STRING;
        }

        return "";
    }

    public CharSequence unicodeWrap(CharSequence arg3) {
        return this.unicodeWrap(arg3, this.mDefaultTextDirectionHeuristicCompat, true);
    }

    public CharSequence unicodeWrap(CharSequence arg3, TextDirectionHeuristicCompat arg4, boolean arg5) {
        if(arg3 == null) {
            return null;
        }

        boolean v4 = arg4.isRtl(arg3, 0, arg3.length());
        SpannableStringBuilder v0 = new SpannableStringBuilder();
        if((this.getStereoReset()) && (arg5)) {
            TextDirectionHeuristicCompat v1 = v4 ? TextDirectionHeuristicsCompat.RTL : TextDirectionHeuristicsCompat.LTR;
            v0.append(this.markBefore(arg3, v1));
        }

        if(v4 != this.mIsRtlContext) {
            char v1_1 = v4 ? '‫' : '‪';
            v0.append(v1_1);
            v0.append(arg3);
            v0.append('‬');
        }
        else {
            v0.append(arg3);
        }

        if(arg5) {
            arg4 = v4 ? TextDirectionHeuristicsCompat.RTL : TextDirectionHeuristicsCompat.LTR;
            v0.append(this.markAfter(arg3, arg4));
        }

        return ((CharSequence)v0);
    }

    public CharSequence unicodeWrap(CharSequence arg2, TextDirectionHeuristicCompat arg3) {
        return this.unicodeWrap(arg2, arg3, true);
    }

    public CharSequence unicodeWrap(CharSequence arg2, boolean arg3) {
        return this.unicodeWrap(arg2, this.mDefaultTextDirectionHeuristicCompat, arg3);
    }

    public String unicodeWrap(String arg3) {
        return this.unicodeWrap(arg3, this.mDefaultTextDirectionHeuristicCompat, true);
    }

    public String unicodeWrap(String arg1, TextDirectionHeuristicCompat arg2, boolean arg3) {
        if(arg1 == null) {
            return null;
        }

        return this.unicodeWrap(((CharSequence)arg1), arg2, arg3).toString();
    }

    public String unicodeWrap(String arg2, TextDirectionHeuristicCompat arg3) {
        return this.unicodeWrap(arg2, arg3, true);
    }

    public String unicodeWrap(String arg2, boolean arg3) {
        return this.unicodeWrap(arg2, this.mDefaultTextDirectionHeuristicCompat, arg3);
    }
}

