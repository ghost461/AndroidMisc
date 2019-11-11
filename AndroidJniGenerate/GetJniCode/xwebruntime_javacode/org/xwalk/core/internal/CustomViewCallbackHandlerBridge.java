package org.xwalk.core.internal;

public class CustomViewCallbackHandlerBridge extends CustomViewCallbackHandlerInternal {
    private XWalkCoreBridge coreBridge;
    private CustomViewCallbackHandlerInternal internal;
    private ReflectMethod onCustomViewHiddenMethod;
    private Object wrapper;

    CustomViewCallbackHandlerBridge(CustomViewCallbackHandlerInternal arg5) {
        super();
        this.onCustomViewHiddenMethod = new ReflectMethod(null, "onCustomViewHidden", new Class[0]);
        this.internal = arg5;
        this.reflectionInit();
    }

    public Object getWrapper() {
        return this.wrapper;
    }

    public void onCustomViewHidden() {
        if(this.onCustomViewHiddenMethod == null || (this.onCustomViewHiddenMethod.isNull())) {
            this.onCustomViewHiddenSuper();
        }
        else {
            this.onCustomViewHiddenMethod.invoke(new Object[0]);
        }
    }

    public void onCustomViewHiddenSuper() {
        if(this.internal == null) {
            super.onCustomViewHidden();
        }
        else {
            this.internal.onCustomViewHidden();
        }
    }

    void reflectionInit() {
        this.coreBridge = XWalkCoreBridge.getInstance();
        if(this.coreBridge == null) {
            return;
        }

        ReflectConstructor v0 = new ReflectConstructor(this.coreBridge.getWrapperClass("CustomViewCallbackHandler"), new Class[]{Object.class});
        try {
            this.wrapper = v0.newInstance(new Object[]{this});
        }
        catch(UnsupportedOperationException ) {
            return;
        }

        this.onCustomViewHiddenMethod.init(this.wrapper, null, "onCustomViewHidden", new Class[0]);
    }
}

