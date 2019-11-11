package org.xwalk.core.internal;

@public interface XWalkAPI {
    boolean createExternally();

    boolean createInternally();

    boolean delegate();

    boolean disableReflectMethod();

    Class extendClass();

    Class impl();

    Class instance();

    boolean isConst();

    boolean noInstance();

    String[] postBridgeLines();

    String[] postWrapperLines();

    String[] preWrapperLines();

    boolean reservable();
}

