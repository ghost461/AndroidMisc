package android.support.v4.text;

import java.nio.CharBuffer;
import java.util.Locale;

public final class TextDirectionHeuristicsCompat {
    class AnyStrong implements TextDirectionAlgorithm {
        public static final AnyStrong INSTANCE_LTR;
        public static final AnyStrong INSTANCE_RTL;
        private final boolean mLookForRtl;

        static {
            AnyStrong.INSTANCE_RTL = new AnyStrong(true);
            AnyStrong.INSTANCE_LTR = new AnyStrong(false);
        }

        private AnyStrong(boolean arg1) {
            super();
            this.mLookForRtl = arg1;
        }

        public int checkRtl(CharSequence arg5, int arg6, int arg7) {
            arg7 += arg6;
            int v1 = 0;
            while(arg6 < arg7) {
                switch(TextDirectionHeuristicsCompat.isRtlText(Character.getDirectionality(arg5.charAt(arg6)))) {
                    case 0: {
                        goto label_13;
                    }
                    case 1: {
                        goto label_10;
                    }
                }

                goto label_17;
            label_10:
                if(this.mLookForRtl) {
                    goto label_16;
                }

                return 1;
            label_13:
                if(this.mLookForRtl) {
                    return 0;
                }

            label_16:
                v1 = 1;
            label_17:
                ++arg6;
            }

            if(v1 != 0) {
                return this.mLookForRtl;
            }

            return 2;
        }
    }

    class FirstStrong implements TextDirectionAlgorithm {
        public static final FirstStrong INSTANCE;

        static {
            FirstStrong.INSTANCE = new FirstStrong();
        }

        private FirstStrong() {
            super();
        }

        public int checkRtl(CharSequence arg3, int arg4, int arg5) {
            arg5 += arg4;
            int v0 = 2;
            int v1 = 2;
            while(arg4 < arg5) {
                if(v1 != v0) {
                    return v1;
                }

                v1 = TextDirectionHeuristicsCompat.isRtlTextOrFormat(Character.getDirectionality(arg3.charAt(arg4)));
                ++arg4;
            }

            return v1;
        }
    }

    interface TextDirectionAlgorithm {
        int checkRtl(CharSequence arg1, int arg2, int arg3);
    }

    abstract class TextDirectionHeuristicImpl implements TextDirectionHeuristicCompat {
        private final TextDirectionAlgorithm mAlgorithm;

        public TextDirectionHeuristicImpl(TextDirectionAlgorithm arg1) {
            super();
            this.mAlgorithm = arg1;
        }

        protected abstract boolean defaultIsRtl();

        private boolean doCheck(CharSequence arg2, int arg3, int arg4) {
            switch(this.mAlgorithm.checkRtl(arg2, arg3, arg4)) {
                case 0: {
                    return 1;
                }
                case 1: {
                    return 0;
                }
            }

            return this.defaultIsRtl();
        }

        public boolean isRtl(CharSequence arg2, int arg3, int arg4) {
            if(arg2 != null && arg3 >= 0 && arg4 >= 0) {
                if(arg2.length() - arg4 < arg3) {
                }
                else if(this.mAlgorithm == null) {
                    return this.defaultIsRtl();
                }
                else {
                    return this.doCheck(arg2, arg3, arg4);
                }
            }

            throw new IllegalArgumentException();
        }

        public boolean isRtl(char[] arg1, int arg2, int arg3) {
            return this.isRtl(CharBuffer.wrap(arg1), arg2, arg3);
        }
    }

    class TextDirectionHeuristicInternal extends TextDirectionHeuristicImpl {
        private final boolean mDefaultIsRtl;

        TextDirectionHeuristicInternal(TextDirectionAlgorithm arg1, boolean arg2) {
            super(arg1);
            this.mDefaultIsRtl = arg2;
        }

        protected boolean defaultIsRtl() {
            return this.mDefaultIsRtl;
        }
    }

    class TextDirectionHeuristicLocale extends TextDirectionHeuristicImpl {
        public static final TextDirectionHeuristicLocale INSTANCE;

        static {
            TextDirectionHeuristicLocale.INSTANCE = new TextDirectionHeuristicLocale();
        }

        public TextDirectionHeuristicLocale() {
            super(null);
        }

        protected boolean defaultIsRtl() {
            boolean v1 = true;
            if(TextUtilsCompat.getLayoutDirectionFromLocale(Locale.getDefault()) == 1) {
            }
            else {
                v1 = false;
            }

            return v1;
        }
    }

    public static final TextDirectionHeuristicCompat ANYRTL_LTR = null;
    public static final TextDirectionHeuristicCompat FIRSTSTRONG_LTR = null;
    public static final TextDirectionHeuristicCompat FIRSTSTRONG_RTL = null;
    public static final TextDirectionHeuristicCompat LOCALE = null;
    public static final TextDirectionHeuristicCompat LTR = null;
    public static final TextDirectionHeuristicCompat RTL = null;
    private static final int STATE_FALSE = 1;
    private static final int STATE_TRUE = 0;
    private static final int STATE_UNKNOWN = 2;

    static {
        TextDirectionHeuristicsCompat.LTR = new TextDirectionHeuristicInternal(null, false);
        TextDirectionHeuristicsCompat.RTL = new TextDirectionHeuristicInternal(null, true);
        TextDirectionHeuristicsCompat.FIRSTSTRONG_LTR = new TextDirectionHeuristicInternal(FirstStrong.INSTANCE, false);
        TextDirectionHeuristicsCompat.FIRSTSTRONG_RTL = new TextDirectionHeuristicInternal(FirstStrong.INSTANCE, true);
        TextDirectionHeuristicsCompat.ANYRTL_LTR = new TextDirectionHeuristicInternal(AnyStrong.INSTANCE_RTL, false);
        TextDirectionHeuristicsCompat.LOCALE = TextDirectionHeuristicLocale.INSTANCE;
    }

    private TextDirectionHeuristicsCompat() {
        super();
    }

    static int isRtlText(int arg0) {
        switch(arg0) {
            case 0: {
                return 1;
            }
            case 1: 
            case 2: {
                return 0;
            }
        }

        return 2;
    }

    static int isRtlTextOrFormat(int arg0) {
        switch(arg0) {
            case 0: {
                return 1;
            }
            case 1: 
            case 2: {
                return 0;
            }
        }

        switch(arg0) {
            case 14: 
            case 15: {
                return 1;
            }
            case 16: 
            case 17: {
                return 0;
            }
        }

        return 2;
    }
}

