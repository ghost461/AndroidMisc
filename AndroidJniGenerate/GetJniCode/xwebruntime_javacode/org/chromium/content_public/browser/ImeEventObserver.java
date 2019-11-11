package org.chromium.content_public.browser;

import android.view.KeyEvent;

public interface ImeEventObserver {
    void onBeforeSendKeyEvent(KeyEvent arg1);

    void onImeEvent();

    void onNodeAttributeUpdated(boolean arg1, boolean arg2);
}

