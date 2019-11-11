package org.chromium.content.browser.framehost;

import org.chromium.base.Callback;
import org.chromium.base.UnguessableToken;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.content_public.browser.RenderFrameHost;
import org.chromium.mojo.system.impl.CoreImpl;
import org.chromium.services.service_manager.InterfaceProvider;

@JNINamespace(value="content") public class RenderFrameHostImpl implements RenderFrameHost {
    private final RenderFrameHostDelegate mDelegate;
    private final boolean mIncognito;
    private final InterfaceProvider mInterfaceProvider;
    private long mNativeRenderFrameHostAndroid;

    private RenderFrameHostImpl(long arg1, RenderFrameHostDelegate arg3, boolean arg4, int arg5) {
        super();
        this.mNativeRenderFrameHostAndroid = arg1;
        this.mDelegate = arg3;
        this.mIncognito = arg4;
        this.mInterfaceProvider = new InterfaceProvider(CoreImpl.getInstance().acquireNativeHandle(arg5).toMessagePipeHandle());
        this.mDelegate.renderFrameCreated(this);
    }

    @CalledByNative private void clearNativePtr() {
        this.mNativeRenderFrameHostAndroid = 0;
        this.mDelegate.renderFrameDeleted(this);
    }

    @CalledByNative private static RenderFrameHostImpl create(long arg7, RenderFrameHostDelegate arg9, boolean arg10, int arg11) {
        return new RenderFrameHostImpl(arg7, arg9, arg10, arg11);
    }

    public UnguessableToken getAndroidOverlayRoutingToken() {
        if(this.mNativeRenderFrameHostAndroid == 0) {
            return null;
        }

        return this.nativeGetAndroidOverlayRoutingToken(this.mNativeRenderFrameHostAndroid);
    }

    public void getCanonicalUrlForSharing(Callback arg6) {
        if(this.mNativeRenderFrameHostAndroid == 0) {
            arg6.onResult(null);
            return;
        }

        this.nativeGetCanonicalUrlForSharing(this.mNativeRenderFrameHostAndroid, arg6);
    }

    public String getLastCommittedURL() {
        if(this.mNativeRenderFrameHostAndroid == 0) {
            return null;
        }

        return this.nativeGetLastCommittedURL(this.mNativeRenderFrameHostAndroid);
    }

    public InterfaceProvider getRemoteInterfaces() {
        return this.mInterfaceProvider;
    }

    public RenderFrameHostDelegate getRenderFrameHostDelegate() {
        return this.mDelegate;
    }

    public boolean isIncognito() {
        return this.mIncognito;
    }

    private native UnguessableToken nativeGetAndroidOverlayRoutingToken(long arg1) {
    }

    private native void nativeGetCanonicalUrlForSharing(long arg1, Callback arg2) {
    }

    private native String nativeGetLastCommittedURL(long arg1) {
    }

    private native void nativeNotifyUserActivation(long arg1) {
    }

    public void notifyUserActivation() {
        this.nativeNotifyUserActivation(this.mNativeRenderFrameHostAndroid);
    }
}

