package org.chromium.content.browser;

import android.content.Context;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.content.browser.androidoverlay.AndroidOverlayProviderImpl$Factory;
import org.chromium.content_public.browser.InterfaceRegistrar$Registry;
import org.chromium.content_public.browser.InterfaceRegistrar;
import org.chromium.content_public.browser.RenderFrameHost;
import org.chromium.content_public.browser.WebContents;
import org.chromium.media.mojom.AndroidOverlayProvider;
import org.chromium.mojo.system.impl.CoreImpl;
import org.chromium.services.service_manager.InterfaceRegistry;

@JNINamespace(value="content") class InterfaceRegistrarImpl {
    class org.chromium.content.browser.InterfaceRegistrarImpl$1 {
    }

    class ContentContextInterfaceRegistrar implements InterfaceRegistrar {
        ContentContextInterfaceRegistrar(org.chromium.content.browser.InterfaceRegistrarImpl$1 arg1) {
            this();
        }

        private ContentContextInterfaceRegistrar() {
            super();
        }

        public void registerInterfaces(InterfaceRegistry arg3, Context arg4) {
            arg3.addInterface(AndroidOverlayProvider.MANAGER, new Factory(arg4));
        }

        public void registerInterfaces(InterfaceRegistry arg1, Object arg2) {
            this.registerInterfaces(arg1, ((Context)arg2));
        }
    }

    private static boolean sHasRegisteredRegistrars;

    InterfaceRegistrarImpl() {
        super();
    }

    @CalledByNative static void createInterfaceRegistryForContext(int arg1) {
        InterfaceRegistrarImpl.ensureContentRegistrarsAreRegistered();
        Registry.applyContextRegistrars(InterfaceRegistry.create(CoreImpl.getInstance().acquireNativeHandle(arg1).toMessagePipeHandle()));
    }

    @CalledByNative static void createInterfaceRegistryForRenderFrameHost(int arg1, RenderFrameHost arg2) {
        InterfaceRegistrarImpl.ensureContentRegistrarsAreRegistered();
        Registry.applyRenderFrameHostRegistrars(InterfaceRegistry.create(CoreImpl.getInstance().acquireNativeHandle(arg1).toMessagePipeHandle()), arg2);
    }

    @CalledByNative static void createInterfaceRegistryForWebContents(int arg1, WebContents arg2) {
        InterfaceRegistrarImpl.ensureContentRegistrarsAreRegistered();
        Registry.applyWebContentsRegistrars(InterfaceRegistry.create(CoreImpl.getInstance().acquireNativeHandle(arg1).toMessagePipeHandle()), arg2);
    }

    private static void ensureContentRegistrarsAreRegistered() {
        if(InterfaceRegistrarImpl.sHasRegisteredRegistrars) {
            return;
        }

        InterfaceRegistrarImpl.sHasRegisteredRegistrars = true;
        Registry.addContextRegistrar(new ContentContextInterfaceRegistrar(null));
    }
}

