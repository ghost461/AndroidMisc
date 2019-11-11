package org.xwalk.core.internal;

@XWalkAPI(createExternally=true) public abstract class XWalkJSExceptionListenerInternal {
    public XWalkJSExceptionListenerInternal() {
        super();
    }

    @XWalkAPI public abstract void onJsException(String arg1, String arg2, String arg3);
}

