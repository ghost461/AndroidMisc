package org.chromium.components.autofill;

public interface AutofillDelegate {
    void accessibilityFocusCleared();

    void deleteSuggestion(int arg1);

    void dismissed();

    void suggestionSelected(int arg1);
}

