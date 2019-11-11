package org.chromium.content_public.browser;

import android.os.ResultReceiver;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import org.chromium.base.VisibleForTesting;

public interface ImeAdapter {
    @VisibleForTesting public static final int COMPOSITION_KEY_CODE = 0xE5;

    void addEventObserver(ImeEventObserver arg1);

    InputConnection getActiveInputConnection();

    @VisibleForTesting InputConnection getInputConnectionForTest();

    @VisibleForTesting ResultReceiver getNewShowKeyboardReceiver();

    boolean onCheckIsTextEditor();

    InputConnection onCreateInputConnection(EditorInfo arg1);

    void setInputMethodManagerWrapper(InputMethodManagerWrapper arg1);
}

