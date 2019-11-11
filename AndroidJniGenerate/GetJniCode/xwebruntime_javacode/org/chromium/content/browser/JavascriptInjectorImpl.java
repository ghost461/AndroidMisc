package org.chromium.content.browser;

import android.util.Pair;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.content.browser.webcontents.WebContentsUserData;
import org.chromium.content_public.browser.JavascriptInjector;
import org.chromium.content_public.browser.WebContents$UserDataFactory;
import org.chromium.content_public.browser.WebContents;

@JNINamespace(value="content") public class JavascriptInjectorImpl implements JavascriptInjector {
    final class UserDataFactoryLazyHolder {
        private static final UserDataFactory INSTANCE;

        static {
            UserDataFactoryLazyHolder.INSTANCE = JavascriptInjectorImpl$UserDataFactoryLazyHolder$$Lambda$0.$instance;
        }

        private UserDataFactoryLazyHolder() {
            super();
        }

        static UserDataFactory access$000() {
            return UserDataFactoryLazyHolder.INSTANCE;
        }
    }

    private final Map mInjectedObjects;
    private long mNativePtr;
    private final Set mRetainedObjects;

    public JavascriptInjectorImpl(WebContents arg3) {
        super();
        this.mRetainedObjects = new HashSet();
        this.mInjectedObjects = new HashMap();
        this.mNativePtr = this.nativeInit(arg3, this.mRetainedObjects);
    }

    public void addPossiblyUnsafeInterface(Object arg9, String arg10, Class arg11) {
        if(this.mNativePtr != 0 && arg9 != null) {
            this.mInjectedObjects.put(arg10, new Pair(arg9, arg11));
            this.nativeAddInterface(this.mNativePtr, arg9, arg10, arg11);
        }
    }

    public static JavascriptInjector fromWebContents(WebContents arg2) {
        return WebContentsUserData.fromWebContents(arg2, JavascriptInjectorImpl.class, UserDataFactoryLazyHolder.access$000());
    }

    public Map getInterfaces() {
        return this.mInjectedObjects;
    }

    private native void nativeAddInterface(long arg1, Object arg2, String arg3, Class arg4) {
    }

    private native long nativeInit(WebContents arg1, Object arg2) {
    }

    private native void nativeRemoveInterface(long arg1, String arg2) {
    }

    private native void nativeSetAllowInspection(long arg1, boolean arg2) {
    }

    @CalledByNative private void onDestroy() {
        this.mNativePtr = 0;
    }

    public void removeInterface(String arg6) {
        this.mInjectedObjects.remove(arg6);
        if(this.mNativePtr != 0) {
            this.nativeRemoveInterface(this.mNativePtr, arg6);
        }
    }

    public void setAllowInspection(boolean arg6) {
        if(this.mNativePtr != 0) {
            this.nativeSetAllowInspection(this.mNativePtr, arg6);
        }
    }
}

