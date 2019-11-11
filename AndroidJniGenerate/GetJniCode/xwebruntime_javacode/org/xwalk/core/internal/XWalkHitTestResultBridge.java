package org.xwalk.core.internal;

public class XWalkHitTestResultBridge extends XWalkHitTestResultInternal {
    private XWalkCoreBridge coreBridge;
    private ReflectMethod enumtypeClassValueOfMethod;
    private ReflectMethod getExtraMethod;
    private ReflectMethod getTypeMethod;
    private XWalkHitTestResultInternal internal;
    private Object wrapper;

    XWalkHitTestResultBridge(XWalkHitTestResultInternal arg6) {
        super();
        this.enumtypeClassValueOfMethod = new ReflectMethod();
        this.getTypeMethod = new ReflectMethod(null, "getType", new Class[0]);
        this.getExtraMethod = new ReflectMethod(null, "getExtra", new Class[0]);
        this.internal = arg6;
        this.reflectionInit();
    }

    private Object Converttype(type arg4) {
        return this.enumtypeClassValueOfMethod.invoke(new Object[]{arg4.toString()});
    }

    public String getExtra() {
        if(this.getExtraMethod != null) {
            if(this.getExtraMethod.isNull()) {
            }
            else {
                return this.getExtraMethod.invoke(new Object[0]);
            }
        }

        return this.getExtraSuper();
    }

    public String getExtraSuper() {
        String v0 = this.internal == null ? super.getExtra() : this.internal.getExtra();
        if(v0 == null) {
            return null;
        }

        return v0;
    }

    public type getType() {
        if(this.getTypeMethod != null) {
            if(this.getTypeMethod.isNull()) {
            }
            else {
                try {
                    return type.valueOf(this.getTypeMethod.invoke(new Object[0]).toString());
                }
                catch(Exception ) {
                    return type.UNKNOWN_TYPE;
                }
            }
        }

        return this.getTypeSuper();
    }

    public type getTypeSuper() {
        type v0 = this.internal == null ? super.getType() : this.internal.getType();
        if(v0 == null) {
            return null;
        }

        return v0;
    }

    public Object getWrapper() {
        return this.wrapper;
    }

    void reflectionInit() {
        this.coreBridge = XWalkCoreBridge.getInstance();
        if(this.coreBridge == null) {
            return;
        }

        ReflectConstructor v0 = new ReflectConstructor(this.coreBridge.getWrapperClass("XWalkHitTestResult"), new Class[]{Object.class});
        try {
            this.wrapper = v0.newInstance(new Object[]{this});
        }
        catch(UnsupportedOperationException ) {
            return;
        }

        this.enumtypeClassValueOfMethod.init(null, this.coreBridge.getWrapperClass("XWalkHitTestResult$type"), "valueOf", new Class[]{String.class});
        this.getTypeMethod.init(this.wrapper, null, "getType", new Class[0]);
        this.getExtraMethod.init(this.wrapper, null, "getExtra", new Class[0]);
    }
}

