package org.chromium.content.browser.input;

import android.content.Context;
import android.text.SpannableString;
import android.view.View;
import org.chromium.ui.base.WindowAndroid;

public class SpellCheckPopupWindow extends SuggestionsPopupWindow {
    private String[] mSuggestions;

    public SpellCheckPopupWindow(Context arg1, TextSuggestionHost arg2, WindowAndroid arg3, View arg4) {
        super(arg1, arg2, arg3, arg4);
        this.mSuggestions = new String[0];
    }

    protected void applySuggestion(int arg3) {
        this.mTextSuggestionHost.applySpellCheckSuggestion(this.mSuggestions[arg3]);
    }

    protected Object getSuggestionItem(int arg2) {
        return this.mSuggestions[arg2];
    }

    protected SpannableString getSuggestionText(int arg3) {
        return new SpannableString(this.mSuggestions[arg3]);
    }

    protected int getSuggestionsCount() {
        return this.mSuggestions.length;
    }

    public void show(double arg1, double arg3, String arg5, String[] arg6) {
        this.mSuggestions = arg6.clone();
        this.setAddToDictionaryEnabled(true);
        super.show(arg1, arg3, arg5);
    }
}

