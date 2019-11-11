package org.chromium.ui.text;

import android.text.SpannableString;
import java.util.Arrays;

public class SpanApplier {
    public final class SpanInfo implements Comparable {
        final String mEndTag;
        int mEndTagIndex;
        final Object mSpan;
        final String mStartTag;
        int mStartTagIndex;

        public SpanInfo(String arg1, String arg2, Object arg3) {
            super();
            this.mStartTag = arg1;
            this.mEndTag = arg2;
            this.mSpan = arg3;
        }

        public int compareTo(Object arg1) {
            return this.compareTo(((SpanInfo)arg1));
        }

        public int compareTo(SpanInfo arg3) {
            int v3;
            if(this.mStartTagIndex < arg3.mStartTagIndex) {
                v3 = -1;
            }
            else if(this.mStartTagIndex == arg3.mStartTagIndex) {
                v3 = 0;
            }
            else {
                v3 = 1;
            }

            return v3;
        }

        public boolean equals(Object arg3) {
            boolean v1 = false;
            if(!(arg3 instanceof SpanInfo)) {
                return 0;
            }

            if(this.compareTo(((SpanInfo)arg3)) == 0) {
                v1 = true;
            }

            return v1;
        }

        public int hashCode() {
            return 0;
        }
    }

    public SpanApplier() {
        super();
    }

    public static SpannableString applySpans(String arg8, SpanInfo[] arg9) {
        SpanInfo v6;
        int v5;
        SpanInfo v3;
        int v0 = arg9.length;
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            v3 = arg9[v2];
            v3.mStartTagIndex = arg8.indexOf(v3.mStartTag);
            v3.mEndTagIndex = arg8.indexOf(v3.mEndTag, v3.mStartTagIndex + v3.mStartTag.length());
        }

        Arrays.sort(((Object[])arg9));
        StringBuilder v0_1 = new StringBuilder(arg8.length());
        v2 = arg9.length;
        int v3_1 = 0;
        int v4 = 0;
        while(true) {
            v5 = -1;
            if(v3_1 >= v2) {
                goto label_67;
            }

            v6 = arg9[v3_1];
            if(v6.mStartTagIndex != v5 && v6.mEndTagIndex != v5) {
                if(v6.mStartTagIndex < v4) {
                }
                else {
                    v0_1.append(((CharSequence)arg8), v4, v6.mStartTagIndex);
                    v4 = v6.mStartTagIndex + v6.mStartTag.length();
                    v6.mStartTagIndex = v0_1.length();
                    v0_1.append(((CharSequence)arg8), v4, v6.mEndTagIndex);
                    v4 = v6.mEndTagIndex + v6.mEndTag.length();
                    v6.mEndTagIndex = v0_1.length();
                    ++v3_1;
                    continue;
                }
            }

            break;
        }

        v6.mStartTagIndex = v5;
        throw new IllegalArgumentException(String.format("Input string is missing tags %s%s: %s", v6.mStartTag, v6.mEndTag, arg8));
    label_67:
        v0_1.append(((CharSequence)arg8), v4, arg8.length());
        SpannableString v8 = new SpannableString(((CharSequence)v0_1));
        v0 = arg9.length;
        for(v2 = 0; v2 < v0; ++v2) {
            v3 = arg9[v2];
            if(v3.mStartTagIndex != v5) {
                v8.setSpan(v3.mSpan, v3.mStartTagIndex, v3.mEndTagIndex, 0);
            }
        }

        return v8;
    }
}

