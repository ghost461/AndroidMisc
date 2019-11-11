package org.xwalk.core.internal;

@XWalkAPI(createExternally=true) public abstract class XWalkLogMessageListenerInternal {
    public XWalkLogMessageListenerInternal() {
        super();
    }

    @XWalkAPI public abstract void onLogMessage(int arg1, String arg2, int arg3, String arg4);
}

