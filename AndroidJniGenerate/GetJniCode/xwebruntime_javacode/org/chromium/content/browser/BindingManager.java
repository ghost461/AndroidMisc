package org.chromium.content.browser;

import org.chromium.base.process_launcher.ChildProcessConnection;

interface BindingManager {
    void dropRecency(ChildProcessConnection arg1);

    void increaseRecency(ChildProcessConnection arg1);

    void onBroughtToForeground();

    void onSentToBackground();

    void releaseAllModerateBindings();
}

