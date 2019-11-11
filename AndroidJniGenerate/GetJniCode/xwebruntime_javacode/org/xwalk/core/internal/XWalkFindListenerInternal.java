package org.xwalk.core.internal;

@XWalkAPI(createExternally=true) public abstract class XWalkFindListenerInternal {
    public XWalkFindListenerInternal() {
        super();
    }

    @XWalkAPI public abstract void onFindResultReceived(int arg1, int arg2, boolean arg3);
}

