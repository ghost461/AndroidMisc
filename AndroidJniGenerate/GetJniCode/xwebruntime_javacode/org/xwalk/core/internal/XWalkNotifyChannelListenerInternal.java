package org.xwalk.core.internal;

@XWalkAPI(createExternally=true) public abstract class XWalkNotifyChannelListenerInternal {
    public XWalkNotifyChannelListenerInternal() {
        super();
    }

    @XWalkAPI public abstract void onNotifyCallBackChannel(int arg1, Object[] arg2);
}

