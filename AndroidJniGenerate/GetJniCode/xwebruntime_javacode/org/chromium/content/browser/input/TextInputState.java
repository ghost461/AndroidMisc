package org.chromium.content.browser.input;

import android.text.TextUtils;
import java.util.Locale;

public class TextInputState {
    private final Range mComposition;
    private final boolean mReplyToRequest;
    private final Range mSelection;
    private final boolean mSingleLine;
    private final CharSequence mText;

    public TextInputState(CharSequence arg4, Range arg5, Range arg6, boolean arg7, boolean arg8) {
        super();
        arg5.clamp(0, arg4.length());
        int v2 = -1;
        if(arg6.start() != v2 || arg6.end() != v2) {
            arg6.clamp(0, arg4.length());
        }

        this.mText = arg4;
        this.mSelection = arg5;
        this.mComposition = arg6;
        this.mSingleLine = arg7;
        this.mReplyToRequest = arg8;
    }

    public Range composition() {
        return this.mComposition;
    }

    public boolean equals(Object arg5) {
        if(!(arg5 instanceof TextInputState)) {
            return 0;
        }

        boolean v0 = true;
        if((((TextInputState)arg5)) == this) {
            return 1;
        }

        if(!TextUtils.equals(this.mText, ((TextInputState)arg5).mText) || !this.mSelection.equals(((TextInputState)arg5).mSelection) || !this.mComposition.equals(((TextInputState)arg5).mComposition) || this.mSingleLine != ((TextInputState)arg5).mSingleLine || this.mReplyToRequest != ((TextInputState)arg5).mReplyToRequest) {
            v0 = false;
        }
        else {
        }

        return v0;
    }

    public CharSequence getSelectedText() {
        if(this.mSelection.start() == this.mSelection.end()) {
            return null;
        }

        return TextUtils.substring(this.mText, this.mSelection.start(), this.mSelection.end());
    }

    public CharSequence getTextAfterSelection(int arg5) {
        return TextUtils.substring(this.mText, this.mSelection.end(), Math.min(this.mText.length(), this.mSelection.end() + arg5));
    }

    public CharSequence getTextBeforeSelection(int arg3) {
        return TextUtils.substring(this.mText, Math.max(0, this.mSelection.start() - arg3), this.mSelection.start());
    }

    public int hashCode() {
        int v0 = this.mText.hashCode() * 7 + this.mSelection.hashCode() * 11 + this.mComposition.hashCode() * 13;
        int v2 = 0;
        int v1 = this.mSingleLine ? 19 : 0;
        v0 += v1;
        if(this.mReplyToRequest) {
            v2 = 23;
        }

        return v0 + v2;
    }

    public boolean replyToRequest() {
        return this.mReplyToRequest;
    }

    public Range selection() {
        return this.mSelection;
    }

    public boolean shouldUnblock() {
        return 0;
    }

    public boolean singleLine() {
        return this.mSingleLine;
    }

    public CharSequence text() {
        return this.mText;
    }

    public String toString() {
        Locale v0 = Locale.US;
        String v1 = "TextInputState {[%s] SEL%s COM%s %s%s}";
        Object[] v2 = new Object[5];
        v2[0] = this.mText;
        v2[1] = this.mSelection;
        v2[2] = this.mComposition;
        String v3 = this.mSingleLine ? "SIN" : "MUL";
        v2[3] = v3;
        int v3_1 = 4;
        String v4 = this.mReplyToRequest ? " ReplyToRequest" : "";
        v2[v3_1] = v4;
        return String.format(v0, v1, v2);
    }
}

