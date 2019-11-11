package org.xwalk.core.internal;

public class XWalkNavigationItemBridge extends XWalkNavigationItemInternal {
    private XWalkCoreBridge coreBridge;
    private ReflectMethod getOriginalUrlMethod;
    private ReflectMethod getTitleMethod;
    private ReflectMethod getUrlMethod;
    private XWalkNavigationItemInternal internal;
    private Object wrapper;

    XWalkNavigationItemBridge(XWalkNavigationItemInternal arg6) {
        super();
        this.getUrlMethod = new ReflectMethod(null, "getUrl", new Class[0]);
        this.getOriginalUrlMethod = new ReflectMethod(null, "getOriginalUrl", new Class[0]);
        this.getTitleMethod = new ReflectMethod(null, "getTitle", new Class[0]);
        this.internal = arg6;
        this.reflectionInit();
    }

    public String getOriginalUrl() {
        if(this.getOriginalUrlMethod != null) {
            if(this.getOriginalUrlMethod.isNull()) {
            }
            else {
                return this.getOriginalUrlMethod.invoke(new Object[0]);
            }
        }

        return this.getOriginalUrlSuper();
    }

    public String getOriginalUrlSuper() {
        String v0 = this.internal == null ? super.getOriginalUrl() : this.internal.getOriginalUrl();
        if(v0 == null) {
            return null;
        }

        return v0;
    }

    public String getTitle() {
        if(this.getTitleMethod != null) {
            if(this.getTitleMethod.isNull()) {
            }
            else {
                return this.getTitleMethod.invoke(new Object[0]);
            }
        }

        return this.getTitleSuper();
    }

    public String getTitleSuper() {
        String v0 = this.internal == null ? super.getTitle() : this.internal.getTitle();
        if(v0 == null) {
            return null;
        }

        return v0;
    }

    public String getUrl() {
        if(this.getUrlMethod != null) {
            if(this.getUrlMethod.isNull()) {
            }
            else {
                return this.getUrlMethod.invoke(new Object[0]);
            }
        }

        return this.getUrlSuper();
    }

    public String getUrlSuper() {
        String v0 = this.internal == null ? super.getUrl() : this.internal.getUrl();
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

        ReflectConstructor v0 = new ReflectConstructor(this.coreBridge.getWrapperClass("XWalkNavigationItem"), new Class[]{Object.class});
        try {
            this.wrapper = v0.newInstance(new Object[]{this});
        }
        catch(UnsupportedOperationException ) {
            return;
        }

        this.getUrlMethod.init(this.wrapper, null, "getUrl", new Class[0]);
        this.getOriginalUrlMethod.init(this.wrapper, null, "getOriginalUrl", new Class[0]);
        this.getTitleMethod.init(this.wrapper, null, "getTitle", new Class[0]);
    }
}

