package org.xwalk.core.internal;

public class XWalkHttpAuthHandlerBridge extends XWalkHttpAuthHandlerInternal {
    private ReflectMethod cancelMethod;
    private XWalkCoreBridge coreBridge;
    private XWalkHttpAuthHandlerInternal internal;
    private ReflectMethod isFirstAttemptMethod;
    private ReflectMethod proceedStringStringMethod;
    private Object wrapper;

    XWalkHttpAuthHandlerBridge(XWalkHttpAuthHandlerInternal arg6) {
        super();
        this.proceedStringStringMethod = new ReflectMethod(null, "proceed", new Class[0]);
        this.cancelMethod = new ReflectMethod(null, "cancel", new Class[0]);
        this.isFirstAttemptMethod = new ReflectMethod(null, "isFirstAttempt", new Class[0]);
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

    public Object getWrapper() {
        return this.wrapper;
    }

    public boolean isFirstAttempt() {
        if(this.isFirstAttemptMethod != null) {
            if(this.isFirstAttemptMethod.isNull()) {
            }
            else {
                return this.isFirstAttemptMethod.invoke(new Object[0]).booleanValue();
            }
        }

        return this.isFirstAttemptSuper();
    }

    public boolean isFirstAttemptSuper() {
        boolean v0 = this.internal == null ? super.isFirstAttempt() : this.internal.isFirstAttempt();
        return v0;
    }

    public void proceed(String arg4, String arg5) {
        if(this.proceedStringStringMethod == null || (this.proceedStringStringMethod.isNull())) {
            this.proceedSuper(arg4, arg5);
        }
        else {
            this.proceedStringStringMethod.invoke(new Object[]{arg4, arg5});
        }
    }

    public void proceedSuper(String arg2, String arg3) {
        if(this.internal == null) {
            super.proceed(arg2, arg3);
        }
        else {
            this.internal.proceed(arg2, arg3);
        }
    }

    void reflectionInit() {
        this.coreBridge = XWalkCoreBridge.getInstance();
        if(this.coreBridge == null) {
            return;
        }

        ReflectConstructor v0 = new ReflectConstructor(this.coreBridge.getWrapperClass("XWalkHttpAuthHandler"), new Class[]{Object.class});
        try {
            this.wrapper = v0.newInstance(new Object[]{this});
        }
        catch(UnsupportedOperationException ) {
            return;
        }

        this.proceedStringStringMethod.init(this.wrapper, null, "proceed", new Class[]{String.class, String.class});
        this.cancelMethod.init(this.wrapper, null, "cancel", new Class[0]);
        this.isFirstAttemptMethod.init(this.wrapper, null, "isFirstAttempt", new Class[0]);
    }
}

