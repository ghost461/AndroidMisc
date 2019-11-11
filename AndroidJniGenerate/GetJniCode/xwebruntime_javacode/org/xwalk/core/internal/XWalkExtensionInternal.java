package org.xwalk.core.internal;

import org.xwalk.core.internal.extensions.XWalkExtensionAndroid;

@XWalkAPI public abstract class XWalkExtensionInternal extends XWalkExtensionAndroid {
    @XWalkAPI public XWalkExtensionInternal(String arg1, String arg2) {
        super(arg1, arg2);
    }

    @XWalkAPI public XWalkExtensionInternal(String arg1, String arg2, String[] arg3) {
        super(arg1, arg2, arg3);
    }

    @XWalkAPI public void broadcastMessage(String arg1) {
        super.broadcastMessage(arg1);
    }

    protected void destroyExtension() {
        super.destroyExtension();
    }

    @XWalkAPI public void onBinaryMessage(int arg1, byte[] arg2) {
    }

    @XWalkAPI public void onInstanceCreated(int arg1) {
    }

    @XWalkAPI public void onInstanceDestroyed(int arg1) {
    }

    @XWalkAPI public abstract void onMessage(int arg1, String arg2);

    @XWalkAPI public abstract String onSyncMessage(int arg1, String arg2);

    @XWalkAPI public void postBinaryMessage(int arg1, byte[] arg2) {
        super.postBinaryMessage(arg1, arg2);
    }

    @XWalkAPI public void postMessage(int arg1, String arg2) {
        super.postMessage(arg1, arg2);
    }
}

