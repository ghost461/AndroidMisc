package org.xwalk.core.internal;

@XWalkAPI(instance=XWalkHttpAuthHandlerInternal.class) public interface XWalkHttpAuthInternal {
    @XWalkAPI void cancel();

    @XWalkAPI boolean isFirstAttempt();

    @XWalkAPI void proceed(String arg1, String arg2);
}

