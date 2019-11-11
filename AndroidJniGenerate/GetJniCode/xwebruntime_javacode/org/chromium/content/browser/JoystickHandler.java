package org.chromium.content.browser;

import android.view.KeyEvent;
import android.view.MotionEvent;
import org.chromium.content.browser.webcontents.WebContentsUserData;
import org.chromium.content_public.browser.ImeEventObserver$$CC;
import org.chromium.content_public.browser.ImeEventObserver;
import org.chromium.content_public.browser.WebContents$UserDataFactory;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.EventForwarder;

class JoystickHandler implements ImeEventObserver {
    class org.chromium.content.browser.JoystickHandler$1 {
    }

    final class UserDataFactoryLazyHolder {
        private static final UserDataFactory INSTANCE;

        static {
            UserDataFactoryLazyHolder.INSTANCE = JoystickHandler$UserDataFactoryLazyHolder$$Lambda$0.$instance;
        }

        private UserDataFactoryLazyHolder() {
            super();
        }

        static UserDataFactory access$100() {
            return UserDataFactoryLazyHolder.INSTANCE;
        }

        static final JoystickHandler lambda$static$0$JoystickHandler$UserDataFactoryLazyHolder(WebContents arg2) {
            return new JoystickHandler(arg2, null);
        }
    }

    private final EventForwarder mEventForwarder;
    private boolean mScrollEnabled;

    private JoystickHandler(WebContents arg2) {
        super();
        this.mScrollEnabled = true;
        this.mEventForwarder = arg2.getEventForwarder();
    }

    JoystickHandler(WebContents arg1, org.chromium.content.browser.JoystickHandler$1 arg2) {
        this(arg1);
    }

    public static JoystickHandler fromWebContents(WebContents arg2) {
        return WebContentsUserData.fromWebContents(arg2, JoystickHandler.class, UserDataFactoryLazyHolder.access$100());
    }

    private static float getVelocityFromJoystickAxis(MotionEvent arg1, int arg2) {
        float v1 = arg1.getAxisValue(arg2);
        if(Math.abs(v1) <= 0.2f) {
            return 0;
        }

        return -v1;
    }

    public void onBeforeSendKeyEvent(KeyEvent arg1) {
        ImeEventObserver$$CC.onBeforeSendKeyEvent(((ImeEventObserver)this), arg1);
    }

    public boolean onGenericMotionEvent(MotionEvent arg9) {
        if(this.mScrollEnabled) {
            if((arg9.getSource() & 16) == 0) {
            }
            else {
                float v5 = JoystickHandler.getVelocityFromJoystickAxis(arg9, 0);
                float v6 = JoystickHandler.getVelocityFromJoystickAxis(arg9, 1);
                if(v5 == 0f && v6 == 0f) {
                    return 0;
                }

                this.mEventForwarder.startFling(arg9.getEventTime(), v5, v6, true);
                return 1;
            }
        }

        return 0;
    }

    public void onImeEvent() {
        ImeEventObserver$$CC.onImeEvent(((ImeEventObserver)this));
    }

    public void onNodeAttributeUpdated(boolean arg1, boolean arg2) {
        this.setScrollEnabled((((int)arg1)) ^ 1);
    }

    public void setScrollEnabled(boolean arg1) {
        this.mScrollEnabled = arg1;
    }
}

