package org.xwalk.core.internal;

public class XWalkJavascriptResultHandlerBridge extends XWalkJavascriptResultHandlerInternal {
    private ReflectMethod cancelMethod;
    private ReflectMethod confirmMethod;
    private ReflectMethod confirmWithResultStringMethod;
    private XWalkCoreBridge coreBridge;
    private XWalkJavascriptResultHandlerInternal internal;
    private Object wrapper;

    XWalkJavascriptResultHandlerBridge(XWalkJavascriptResultHandlerInternal arg6) {
        super();
        this.confirmMethod = new ReflectMethod(null, "confirm", new Class[0]);
        this.confirmWithResultStringMethod = new ReflectMethod(null, "confirmWithResult", new Class[0]);
        this.cancelMethod = new ReflectMethod(null, "cancel", new Class[0]);
        this.internal = arg6;
        this.reflectionInit();
    }

    public void cancel() {
        if(this.cancelMethod == null || (this.cancelMethod.isNull())) {
            this.cancelSuper();
        }
        else {
            this.cancelMethod.invoke(new Object[0]);
        }
    }

    public void cancelSuper() {
        if(this.internal == null) {
            super.cancel();
        }
        else {
            this.internal.cancel();
        }
    }

    public void confirm() {
        if(this.confirmMethod == null || (this.confirmMethod.isNull())) {
            this.confirmSuper();
        }
        else {
            this.confirmMethod.invoke(new Object[0]);
        }
    }

    public void confirmSuper() {
        if(this.internal == null) {
            super.confirm();
        }
        else {
            this.internal.confirm();
        }
    }

    public void confirmWithResult(String arg4) {
        if(this.confirmWithResultStringMethod == null || (this.confirmWithResultStringMethod.isNull())) {
            this.confirmWithResultSuper(arg4);
        }
        else {
            this.confirmWithResultStringMethod.invoke(new Object[]{arg4});
        }
    }

    public void confirmWithResultSuper(String arg2) {
        if(this.internal == null) {
            super.confirmWithResult(arg2);
        }
        else {
            this.internal.confirmWithResult(arg2);
        }
    }

    public Object getWrapper() {
        return this.wrapper;
    }

    void reflectionInit() {
        this.coreBridge = XWalkCoreBridge.getInstance();
        if(this.coreBridge == null) {
            return;
        }

        ReflectConstructor v0 = new ReflectConstructor(this.coreBridge.getWrapperClass("XWalkJavascriptResultHandler"), new Class[]{Object.class});
        try {
            this.wrapper = v0.newInstance(new Object[]{this});
        }
        catch(UnsupportedOperationException ) {
            return;
        }

        this.confirmMethod.init(this.wrapper, null, "confirm", new Class[0]);
        this.confirmWithResultStringMethod.init(this.wrapper, null, "confirmWithResult", new Class[]{String.class});
        this.cancelMethod.init(this.wrapper, null, "cancel", new Class[0]);
    }
}

