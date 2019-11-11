package org.chromium.content.browser.input;

import android.content.Context;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.view.View;
import org.chromium.ui.base.WindowAndroid;

public class TextSuggestionsPopupWindow extends SuggestionsPopupWindow {
    private TextAppearanceSpan mPrefixSpan;
    private TextAppearanceSpan mSuffixSpan;
    private SuggestionInfo[] mSuggestionInfos;

    public TextSuggestionsPopupWindow(Context arg1, TextSuggestionHost arg2, WindowAndroid arg3, View arg4) {
        super(arg1, arg2, arg3, arg4);
        this.mPrefixSpan = new TextAppearanceSpan(arg1, 0x7F0D00CB);
        this.mSuffixSpan = new TextAppearanceSpan(arg1, 0x7F0D00CB);
    }

    protected void applySuggestion(int arg3) {
        this.mTextSuggestionHost.applyTextSuggestion(this.mSuggestionInfos[arg3].getMarkerTag(), this.mSuggestionInfos[arg3].getSuggestionIndex());
    }

    protected Object getSuggestionItem(int arg2) {
        return this.mSuggestionInfos[arg2];
    }

    protected SpannableString getSuggestionText(int arg7) {
        SuggestionInfo v7 = this.mSuggestionInfos[arg7];
        StringBuilder v1 = new StringBuilder();
        v1.append(v7.getPrefix());
        v1.append(v7.getSuggestion());
        v1.append(v7.getSuffix());
        SpannableString v0 = new SpannableString(v1.toString());
        v0.setSpan(this.mPrefixSpan, 0, v7.getPrefix().length(), 33);
        v0.setSpan(this.mSuffixSpan, v7.getPrefix().length() + v7.getSuggestion().length(), v7.getPrefix().length() + v7.getSuggestion().length() + v7.getSuffix().length(), 33);
        return v0;
    }

    protected int getSuggestionsCount() {
        return this.mSuggestionInfos.length;
    }

    public void show(double arg1, double arg3, String arg5, SuggestionInfo[] arg6) {
        this.mSuggestionInfos = arg6.clone();
        this.setAddToDictionaryEnabled(false);
        super.show(arg1, arg3, arg5);
    }
}

