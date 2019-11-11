package org.xwalk.core.internal;

public class XWalkNavigationHistoryBridge extends XWalkNavigationHistoryInternal {
    private ReflectMethod canGoBackMethod;
    private ReflectMethod canGoForwardMethod;
    private ReflectMethod clearMethod;
    private XWalkCoreBridge coreBridge;
    private ReflectMethod enumDirectionClassValueOfMethod;
    private ReflectMethod getCurrentIndexMethod;
    private ReflectMethod getCurrentItemMethod;
    private ReflectMethod getItemAtintMethod;
    private ReflectMethod hasItemAtintMethod;
    private XWalkNavigationHistoryInternal internal;
    private ReflectMethod navigateDirectionInternalintMethod;
    private ReflectMethod sizeMethod;
    private Object wrapper;

    XWalkNavigationHistoryBridge(XWalkNavigationHistoryInternal arg6) {
        super();
        this.enumDirectionClassValueOfMethod = new ReflectMethod();
        this.sizeMethod = new ReflectMethod(null, "size", new Class[0]);
        this.hasItemAtintMethod = new ReflectMethod(null, "hasItemAt", new Class[0]);
        this.getItemAtintMethod = new ReflectMethod(null, "getItemAt", new Class[0]);
        this.getCurrentItemMethod = new ReflectMethod(null, "getCurrentItem", new Class[0]);
        this.canGoBackMethod = new ReflectMethod(null, "canGoBack", new Class[0]);
        this.canGoForwardMethod = new ReflectMethod(null, "canGoForward", new Class[0]);
        this.navigateDirectionInternalintMethod = new ReflectMethod(null, "navigate", new Class[0]);
        this.getCurrentIndexMethod = new ReflectMethod(null, "getCurrentIndex", new Class[0]);
        this.clearMethod = new ReflectMethod(null, "clear", new Class[0]);
        this.internal = arg6;
        this.reflectionInit();
    }

    private Object ConvertDirectionInternal(DirectionInternal arg4) {
        return this.enumDirectionClassValueOfMethod.invoke(new Object[]{arg4.toString()});
    }

    public boolean canGoBack() {
        if(this.canGoBackMethod != null) {
            if(this.canGoBackMethod.isNull()) {
            }
            else {
                return this.canGoBackMethod.invoke(new Object[0]).booleanValue();
            }
        }

        return this.canGoBackSuper();
    }

    public boolean canGoBackSuper() {
        boolean v0 = this.internal == null ? super.canGoBack() : this.internal.canGoBack();
        return v0;
    }

    public boolean canGoForward() {
        if(this.canGoForwardMethod != null) {
            if(this.canGoForwardMethod.isNull()) {
            }
            else {
                return this.canGoForwardMethod.invoke(new Object[0]).booleanValue();
            }
        }

        return this.canGoForwardSuper();
    }

    public boolean canGoForwardSuper() {
        boolean v0 = this.internal == null ? super.canGoForward() : this.internal.canGoForward();
        return v0;
    }

    public void clear() {
        if(this.clearMethod == null || (this.clearMethod.isNull())) {
            this.clearSuper();
        }
        else {
            this.clearMethod.invoke(new Object[0]);
        }
    }

    public void clearSuper() {
        if(this.internal == null) {
            super.clear();
        }
        else {
            this.internal.clear();
        }
    }

    public int getCurrentIndex() {
        if(this.getCurrentIndexMethod != null) {
            if(this.getCurrentIndexMethod.isNull()) {
            }
            else {
                return this.getCurrentIndexMethod.invoke(new Object[0]).intValue();
            }
        }

        return this.getCurrentIndexSuper();
    }

    public int getCurrentIndexSuper() {
        int v0 = this.internal == null ? super.getCurrentIndex() : this.internal.getCurrentIndex();
        return v0;
    }

    public XWalkNavigationItemInternal getCurrentItem() {
        if(this.getCurrentItemMethod != null) {
            if(this.getCurrentItemMethod.isNull()) {
            }
            else {
                return this.coreBridge.getBridgeObject(this.getCurrentItemMethod.invoke(new Object[0]));
            }
        }

        return this.getCurrentItemSuper();
    }

    public XWalkNavigationItemBridge getCurrentItemSuper() {
        XWalkNavigationItemInternal v0 = this.internal == null ? super.getCurrentItem() : this.internal.getCurrentItem();
        if(v0 == null) {
            return null;
        }

        if((v0 instanceof XWalkNavigationItemBridge)) {
        }
        else {
            XWalkNavigationItemBridge v0_1 = new XWalkNavigationItemBridge(v0);
        }

        return ((XWalkNavigationItemBridge)v0);
    }

    public XWalkNavigationItemInternal getItemAt(int arg5) {
        if(this.getItemAtintMethod != null) {
            if(this.getItemAtintMethod.isNull()) {
            }
            else {
                return this.coreBridge.getBridgeObject(this.getItemAtintMethod.invoke(new Object[]{Integer.valueOf(arg5)}));
            }
        }

        return this.getItemAtSuper(arg5);
    }

    public XWalkNavigationItemBridge getItemAtSuper(int arg2) {
        XWalkNavigationItemBridge v2_1;
        XWalkNavigationItemInternal v2 = this.internal == null ? super.getItemAt(arg2) : this.internal.getItemAt(arg2);
        if(v2 == null) {
            return null;
        }

        if((v2 instanceof XWalkNavigationItemBridge)) {
        }
        else {
            v2_1 = new XWalkNavigationItemBridge(v2);
        }

        return v2_1;
    }

    public Object getWrapper() {
        return this.wrapper;
    }

    public boolean hasItemAt(int arg4) {
        if(this.hasItemAtintMethod != null) {
            if(this.hasItemAtintMethod.isNull()) {
            }
            else {
                return this.hasItemAtintMethod.invoke(new Object[]{Integer.valueOf(arg4)}).booleanValue();
            }
        }

        return this.hasItemAtSuper(arg4);
    }

    public boolean hasItemAtSuper(int arg2) {
        boolean v2 = this.internal == null ? super.hasItemAt(arg2) : this.internal.hasItemAt(arg2);
        return v2;
    }

    public void navigate(DirectionInternal arg4, int arg5) {
        if(this.navigateDirectionInternalintMethod == null || (this.navigateDirectionInternalintMethod.isNull())) {
            this.navigateSuper(arg4, arg5);
        }
        else {
            this.navigateDirectionInternalintMethod.invoke(new Object[]{this.ConvertDirectionInternal(arg4), Integer.valueOf(arg5)});
        }
    }

    public void navigateSuper(DirectionInternal arg2, int arg3) {
        if(this.internal == null) {
            super.navigate(arg2, arg3);
        }
        else {
            this.internal.navigate(arg2, arg3);
        }
    }

    void reflectionInit() {
        this.coreBridge = XWalkCoreBridge.getInstance();
        if(this.coreBridge == null) {
            return;
        }

        ReflectConstructor v0 = new ReflectConstructor(this.coreBridge.getWrapperClass("XWalkNavigationHistory"), new Class[]{Object.class});
        try {
            this.wrapper = v0.newInstance(new Object[]{this});
        }
        catch(UnsupportedOperationException ) {
            return;
        }

        this.enumDirectionClassValueOfMethod.init(null, this.coreBridge.getWrapperClass("XWalkNavigationHistory$Direction"), "valueOf", new Class[]{String.class});
        this.sizeMethod.init(this.wrapper, null, "size", new Class[0]);
        this.hasItemAtintMethod.init(this.wrapper, null, "hasItemAt", new Class[]{Integer.TYPE});
        this.getItemAtintMethod.init(this.wrapper, null, "getItemAt", new Class[]{Integer.TYPE});
        this.getCurrentItemMethod.init(this.wrapper, null, "getCurrentItem", new Class[0]);
        this.canGoBackMethod.init(this.wrapper, null, "canGoBack", new Class[0]);
        this.canGoForwardMethod.init(this.wrapper, null, "canGoForward", new Class[0]);
        this.navigateDirectionInternalintMethod.init(this.wrapper, null, "navigate", new Class[]{this.coreBridge.getWrapperClass("XWalkNavigationHistory$Direction"), Integer.TYPE});
        this.getCurrentIndexMethod.init(this.wrapper, null, "getCurrentIndex", new Class[0]);
        this.clearMethod.init(this.wrapper, null, "clear", new Class[0]);
    }

    public int size() {
        if(this.sizeMethod != null) {
            if(this.sizeMethod.isNull()) {
            }
            else {
                return this.sizeMethod.invoke(new Object[0]).intValue();
            }
        }

        return this.sizeSuper();
    }

    public int sizeSuper() {
        int v0 = this.internal == null ? super.size() : this.internal.size();
        return v0;
    }
}

