package org.chromium.content_public.browser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.chromium.base.ContextUtils;
import org.chromium.services.service_manager.InterfaceRegistry;

public interface InterfaceRegistrar {
    public class Registry {
        private List mRegistrars;
        private static Registry sContextRegistry;
        private static Registry sRenderFrameHostRegistry;
        private static Registry sWebContentsRegistry;

        private Registry() {
            super();
            this.mRegistrars = new ArrayList();
        }

        public static void addContextRegistrar(InterfaceRegistrar arg1) {
            if(Registry.sContextRegistry == null) {
                Registry.sContextRegistry = new Registry();
            }

            Registry.sContextRegistry.addRegistrar(arg1);
        }

        private void addRegistrar(InterfaceRegistrar arg2) {
            this.mRegistrars.add(arg2);
        }

        public static void addRenderFrameHostRegistrar(InterfaceRegistrar arg1) {
            if(Registry.sRenderFrameHostRegistry == null) {
                Registry.sRenderFrameHostRegistry = new Registry();
            }

            Registry.sRenderFrameHostRegistry.addRegistrar(arg1);
        }

        public static void addWebContentsRegistrar(InterfaceRegistrar arg1) {
            if(Registry.sWebContentsRegistry == null) {
                Registry.sWebContentsRegistry = new Registry();
            }

            Registry.sWebContentsRegistry.addRegistrar(arg1);
        }

        public static void applyContextRegistrars(InterfaceRegistry arg2) {
            if(Registry.sContextRegistry == null) {
                return;
            }

            Registry.sContextRegistry.applyRegistrars(arg2, ContextUtils.getApplicationContext());
        }

        private void applyRegistrars(InterfaceRegistry arg3, Object arg4) {
            Iterator v0 = this.mRegistrars.iterator();
            while(v0.hasNext()) {
                v0.next().registerInterfaces(arg3, arg4);
            }
        }

        public static void applyRenderFrameHostRegistrars(InterfaceRegistry arg1, RenderFrameHost arg2) {
            if(Registry.sRenderFrameHostRegistry == null) {
                return;
            }

            Registry.sRenderFrameHostRegistry.applyRegistrars(arg1, arg2);
        }

        public static void applyWebContentsRegistrars(InterfaceRegistry arg1, WebContents arg2) {
            if(Registry.sWebContentsRegistry == null) {
                return;
            }

            Registry.sWebContentsRegistry.applyRegistrars(arg1, arg2);
        }
    }

    void registerInterfaces(InterfaceRegistry arg1, Object arg2);
}

