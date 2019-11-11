package org.chromium.blink.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface TextSuggestionBackend extends Interface {
    public interface Proxy extends TextSuggestionBackend, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        TextSuggestionBackend.MANAGER = TextSuggestionBackend_Internal.MANAGER;
    }

    void applySpellCheckSuggestion(String arg1);

    void applyTextSuggestion(int arg1, int arg2);

    void deleteActiveSuggestionRange();

    void onNewWordAddedToDictionary(String arg1);

    void onSuggestionMenuClosed();

    void suggestionMenuTimeoutCallback(int arg1);
}

