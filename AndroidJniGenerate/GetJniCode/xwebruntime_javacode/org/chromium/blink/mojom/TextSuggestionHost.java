package org.chromium.blink.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface TextSuggestionHost extends Interface {
    public interface Proxy extends TextSuggestionHost, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        TextSuggestionHost.MANAGER = TextSuggestionHost_Internal.MANAGER;
    }

    void showSpellCheckSuggestionMenu(double arg1, double arg2, String arg3, SpellCheckSuggestion[] arg4);

    void showTextSuggestionMenu(double arg1, double arg2, String arg3, TextSuggestion[] arg4);

    void startSuggestionMenuTimer();
}

