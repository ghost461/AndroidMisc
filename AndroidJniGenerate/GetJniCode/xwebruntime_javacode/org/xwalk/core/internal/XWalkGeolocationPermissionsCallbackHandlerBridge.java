package org.xwalk.core.internal;

public class XWalkGeolocationPermissionsCallbackHandlerBridge extends XWalkGeolocationPermissionsCallbackHandlerInternal {
    private XWalkCoreBridge coreBridge;
    private XWalkGeolocationPermissionsCallbackHandlerInternal internal;
    private ReflectMethod invokeStringbooleanbooleanMethod;
    private Object wrapper;

    XWalkGeolocationPermissionsCallbackHandlerBridge(XWalkGeolocationPermissionsCallbackHandlerInternal arg5) {
        super();
        this.invokeStringbooleanbooleanMethod = new ReflectMethod(null, "invoke", new Class[0]);
        this.internal = arg5;
        this.reflectionInit();
    }

    public Object getWrapper() {
        return this.wrapper;
    }

    public void invoke(String arg4, boolean arg5, boolean arg6) {
        if(this.invokeStringbooleanbooleanMethod == null || (this.invokeStringbooleanbooleanMethod.isNull())) {
            this.invokeSuper(arg4, arg5, arg6);
        }
        else {
            this.invokeStringbooleanbooleanMethod.invoke(new Object[]{arg4, Boolean.valueOf(arg5), Boolean.valueOf(arg6)});
        }
    }

    public void invokeSuper(String arg2, boolean arg3, boolean arg4) {
        if(this.internal == null) {
            super.invoke(arg2, arg3, arg4);
        }
        else {
            this.internal.invoke(arg2, arg3, arg4);
        }
    }

    void reflectionInit() {
        this.coreBridge = XWalkCoreBridge.getInstance();
        if(this.coreBridge == null) {
            return;
        }

        ReflectConstructor v0 = new ReflectConstructor(this.coreBridge.getWrapperClass("XWalkGeolocationPermissionsCallbackHandler"), new Class[]{Object.class});
        try {
            this.wrapper = v0.newInstance(new Object[]{this});
        }
        catch(UnsupportedOperationException ) {
            return;
        }

        this.invokeStringbooleanbooleanMethod.init(this.wrapper, null, "invoke", new Class[]{String.class, Boolean.TYPE, Boolean.TYPE});
    }
}

