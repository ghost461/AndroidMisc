package org.chromium.content.browser.selection;

import java.text.BreakIterator;
import java.util.regex.Pattern;
import org.chromium.base.VisibleForTesting;

public class SelectionIndicesConverter {
    private static final Pattern PATTERN_WHITESPACE;
    private String mGlobalSelectionText;
    private int mGlobalStartOffset;
    private int mInitialStartOffset;
    private String mLastSelectionText;
    private int mLastStartOffset;

    static {
        SelectionIndicesConverter.PATTERN_WHITESPACE = Pattern.compile("[\\p{javaSpaceChar}\\s]+");
    }

    public SelectionIndicesConverter() {
        super();
    }

    private void combineGlobalSelection(String arg6, int arg7) {
        int v0 = arg6.length() + arg7;
        int v1 = this.mGlobalStartOffset + this.mGlobalSelectionText.length();
        if(arg7 < this.mGlobalStartOffset) {
            this.updateGlobalSelection(arg6.substring(0, this.mGlobalStartOffset - arg7) + this.mGlobalSelectionText, arg7);
        }

        if(v0 > v1) {
            this.updateGlobalSelection(this.mGlobalSelectionText + arg6.substring(v1 - arg7, arg6.length()), this.mGlobalStartOffset);
        }
    }

    @VisibleForTesting protected static boolean contains(int arg0, int arg1, int arg2, int arg3) {
        boolean v0 = arg0 > arg2 || arg3 > arg1 ? false : true;
        return v0;
    }

    @VisibleForTesting protected int countWordsBackward(int arg3, int arg4, BreakIterator arg5) {
        int v0 = 0;
        while(arg3 > arg4) {
            int v1 = arg5.preceding(arg3);
            if(!this.isWhitespace(v1, arg3)) {
                ++v0;
            }

            arg3 = v1;
        }

        return v0;
    }

    @VisibleForTesting protected int countWordsForward(int arg3, int arg4, BreakIterator arg5) {
        int v0 = 0;
        while(arg3 < arg4) {
            int v1 = arg5.following(arg3);
            if(!this.isWhitespace(arg3, v1)) {
                ++v0;
            }

            arg3 = v1;
        }

        return v0;
    }

    @VisibleForTesting protected String getGlobalSelectionText() {
        return this.mGlobalSelectionText;
    }

    @VisibleForTesting protected int getGlobalStartOffset() {
        return this.mGlobalStartOffset;
    }

    public boolean getWordDelta(int arg6, int arg7, int[] arg8) {
        arg8[1] = 0;
        arg8[0] = 0;
        arg6 -= this.mGlobalStartOffset;
        arg7 -= this.mGlobalStartOffset;
        if(arg6 >= arg7) {
            return 0;
        }

        if(arg6 >= 0) {
            if(arg6 >= this.mGlobalSelectionText.length()) {
            }
            else {
                if(arg7 > 0) {
                    if(arg7 > this.mGlobalSelectionText.length()) {
                    }
                    else {
                        int v2 = this.mInitialStartOffset - this.mGlobalStartOffset;
                        BreakIterator v3 = BreakIterator.getWordInstance();
                        v3.setText(this.mGlobalSelectionText);
                        if(arg6 <= v2) {
                            arg8[0] = -this.countWordsForward(arg6, v2, v3);
                        }
                        else {
                            arg8[0] = this.countWordsBackward(arg6, v2, v3);
                            if(!v3.isBoundary(arg6) && !this.isWhitespace(v3.preceding(arg6), v3.following(arg6))) {
                                --arg8[0];
                            }
                        }

                        arg8[1] = arg7 <= v2 ? -this.countWordsForward(arg7, v2, v3) : this.countWordsBackward(arg7, v2, v3);
                        return 1;
                    }
                }

                return 0;
            }
        }

        return 0;
    }

    @VisibleForTesting protected boolean isWhitespace(int arg3, int arg4) {
        return SelectionIndicesConverter.PATTERN_WHITESPACE.matcher(this.mGlobalSelectionText.substring(arg3, arg4)).matches();
    }

    @VisibleForTesting protected static boolean overlap(int arg2, int arg3, int arg4, int arg5) {
        boolean v0 = false;
        if(arg2 <= arg4) {
            if(arg4 < arg3) {
                v0 = true;
            }

            return v0;
        }

        if(arg5 > arg2) {
            v0 = true;
        }

        return v0;
    }

    public void setInitialStartOffset(int arg1) {
        this.mInitialStartOffset = arg1;
    }

    private void updateGlobalSelection(String arg1, int arg2) {
        this.mGlobalSelectionText = arg1;
        this.mGlobalStartOffset = arg2;
    }

    private void updateLastSelection(String arg1, int arg2) {
        this.mLastSelectionText = arg1;
        this.mLastStartOffset = arg2;
    }

    public boolean updateSelectionState(String arg10, int arg11) {
        boolean v3_1;
        if(this.mGlobalSelectionText == null) {
            this.updateLastSelection(arg10, arg11);
            this.updateGlobalSelection(arg10, arg11);
            return 1;
        }

        int v0 = arg10.length() + arg11;
        int v2 = this.mLastStartOffset + this.mLastSelectionText.length();
        if(SelectionIndicesConverter.overlap(this.mLastStartOffset, v2, arg11, v0)) {
            int v3 = Math.max(this.mLastStartOffset, arg11);
            v3_1 = this.mLastSelectionText.regionMatches(v3 - this.mLastStartOffset, arg10, v3 - arg11, Math.min(v2, v0) - v3);
        }
        else {
            v3_1 = false;
        }

        if(this.mLastStartOffset == v0 || v2 == arg11) {
            v3_1 = true;
        }

        if(!v3_1) {
            this.mGlobalSelectionText = null;
            this.mLastSelectionText = null;
            return 0;
        }

        this.updateLastSelection(arg10, arg11);
        this.combineGlobalSelection(arg10, arg11);
        return 1;
    }
}

