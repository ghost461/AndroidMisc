package org.xwalk.core.internal;

@XWalkAPI(createInternally=true, impl=CustomViewCallbackInternal.class) public class CustomViewCallbackHandlerInternal implements CustomViewCallbackInternal {
    public CustomViewCallbackHandlerInternal() {
        super();
    }

    @XWalkAPI public void onCustomViewHidden() {
    }
}

