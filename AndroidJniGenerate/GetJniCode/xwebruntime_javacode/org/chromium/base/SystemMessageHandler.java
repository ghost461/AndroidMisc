package org.chromium.base;

import android.annotation.SuppressLint;
import android.os.Build$VERSION;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue$IdleHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.base.annotations.MainDex;

@JNINamespace(value="base") @MainDex class SystemMessageHandler extends Handler {
    class org.chromium.base.SystemMessageHandler$1 implements MessageQueue$IdleHandler {
        org.chromium.base.SystemMessageHandler$1(SystemMessageHandler arg1) {
            SystemMessageHandler.this = arg1;
            super();
        }

        public boolean queueIdle() {
            if(SystemMessageHandler.this.mNativeMessagePumpForUI == 0) {
                return 0;
            }

            SystemMessageHandler.this.nativeDoIdleWork(SystemMessageHandler.this.mNativeMessagePumpForUI);
            return 1;
        }
    }

    class MessageCompat {
        class LegacyMessageWrapperImpl implements MessageWrapperImpl {
            private Method mMessageMethodSetAsynchronous;

            LegacyMessageWrapperImpl() {
                super();
                try {
                    this.mMessageMethodSetAsynchronous = Message.class.getMethod("setAsynchronous", Boolean.TYPE);
                }
                catch(RuntimeException v2) {
                    Log.e("cr.SysMessageHandler", "Exception while loading Message.setAsynchronous method", new Object[]{v2});
                }
                catch(NoSuchMethodException v2_1) {
                    Log.e("cr.SysMessageHandler", "Failed to load Message.setAsynchronous method", new Object[]{v2_1});
                }
            }

            public void setAsynchronous(Message arg5, boolean arg6) {
                if(this.mMessageMethodSetAsynchronous == null) {
                    return;
                }

                Method v0 = null;
                try {
                    this.mMessageMethodSetAsynchronous.invoke(arg5, Boolean.valueOf(arg6));
                }
                catch(RuntimeException ) {
                    Log.e("cr.SysMessageHandler", "Runtime exception during async message creation, disabling.", new Object[0]);
                    this.mMessageMethodSetAsynchronous = v0;
                }
                catch(InvocationTargetException ) {
                    Log.e("cr.SysMessageHandler", "Invocation exception during async message creation, disabling.", new Object[0]);
                    this.mMessageMethodSetAsynchronous = v0;
                }
                catch(IllegalArgumentException ) {
                    Log.e("cr.SysMessageHandler", "Illegal argument for async message creation, disabling.", new Object[0]);
                    this.mMessageMethodSetAsynchronous = v0;
                }
                catch(IllegalAccessException ) {
                    Log.e("cr.SysMessageHandler", "Illegal access to async message creation, disabling.", new Object[0]);
                    this.mMessageMethodSetAsynchronous = v0;
                }
            }
        }

        class LollipopMr1MessageWrapperImpl implements MessageWrapperImpl {
            LollipopMr1MessageWrapperImpl() {
                super();
            }

            @SuppressLint(value={"NewApi"}) public void setAsynchronous(Message arg1, boolean arg2) {
                arg1.setAsynchronous(arg2);
            }
        }

        interface MessageWrapperImpl {
            void setAsynchronous(Message arg1, boolean arg2);
        }

        static final MessageWrapperImpl IMPL;

        static {
            MessageCompat.IMPL = Build$VERSION.SDK_INT >= 22 ? new LollipopMr1MessageWrapperImpl() : new LegacyMessageWrapperImpl();
        }

        private MessageCompat() {
            super();
        }

        public static void setAsynchronous(Message arg1, boolean arg2) {
            MessageCompat.IMPL.setAsynchronous(arg1, arg2);
        }
    }

    private static final int DELAYED_SCHEDULED_WORK = 2;
    private static final int SCHEDULED_WORK = 1;
    private static final String TAG = "cr.SysMessageHandler";
    private final MessageQueue$IdleHandler mIdleHandler;
    private long mNativeMessagePumpForUI;
    private boolean mScheduledDelayedWork;

    protected SystemMessageHandler(long arg2) {
        super();
        this.mIdleHandler = new org.chromium.base.SystemMessageHandler$1(this);
        this.mNativeMessagePumpForUI = arg2;
        Looper.myQueue().addIdleHandler(this.mIdleHandler);
    }

    static long access$000(SystemMessageHandler arg2) {
        return arg2.mNativeMessagePumpForUI;
    }

    static void access$100(SystemMessageHandler arg0, long arg1) {
        arg0.nativeDoIdleWork(arg1);
    }

    @CalledByNative private static SystemMessageHandler create(long arg1) {
        return new SystemMessageHandler(arg1);
    }

    public void handleMessage(Message arg6) {
        if(this.mNativeMessagePumpForUI == 0) {
            return;
        }

        boolean v6 = arg6.what == 2 ? true : false;
        if(v6) {
            this.mScheduledDelayedWork = false;
        }

        this.nativeDoRunLoopOnce(this.mNativeMessagePumpForUI, v6);
    }

    private native void nativeDoIdleWork(long arg1) {
    }

    private native void nativeDoRunLoopOnce(long arg1, boolean arg2) {
    }

    private Message obtainAsyncMessage(int arg2) {
        Message v0 = Message.obtain();
        v0.what = arg2;
        MessageCompat.setAsynchronous(v0, true);
        return v0;
    }

    @CalledByNative private void scheduleDelayedWork(long arg3) {
        int v1 = 2;
        if(this.mScheduledDelayedWork) {
            this.removeMessages(v1);
        }

        this.mScheduledDelayedWork = true;
        this.sendMessageDelayed(this.obtainAsyncMessage(v1), arg3);
    }

    @CalledByNative private void scheduleWork() {
        this.sendMessage(this.obtainAsyncMessage(1));
    }

    @CalledByNative private void shutdown() {
        this.mNativeMessagePumpForUI = 0;
    }
}

