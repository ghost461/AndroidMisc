package org.xwalk.core.internal;

import android.view.KeyEvent;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.components.web_contents_delegate_android.WebContentsDelegateAndroid;

@JNINamespace(value="xwalk") abstract class XWalkWebContentsDelegate extends WebContentsDelegateAndroid {
    XWalkWebContentsDelegate() {
        super();
    }

    @CalledByNative public abstract void activateContents();

    @CalledByNative public abstract boolean addMessageToConsole(int arg1, String arg2, int arg3, String arg4);

    @CalledByNative public abstract boolean addNewContents(boolean arg1, boolean arg2);

    @CalledByNative public abstract void closeContents();

    @CalledByNative public abstract void handleKeyboardEvent(KeyEvent arg1);

    @CalledByNative public boolean isFullscreen() {
        return 0;
    }

    @CalledByNative public abstract void rendererResponsive();

    @CalledByNative public abstract void rendererUnresponsive();

    @CalledByNative public boolean shouldCreateWebContents(String arg1) {
        return 1;
    }

    @CalledByNative public abstract boolean shouldOverrideRunFileChooser(int arg1, int arg2, int arg3, String arg4, boolean arg5);

    @CalledByNative public void toggleFullscreen(boolean arg1) {
    }

    @CalledByNative public void updatePreferredSize(int arg1, int arg2) {
    }
}

