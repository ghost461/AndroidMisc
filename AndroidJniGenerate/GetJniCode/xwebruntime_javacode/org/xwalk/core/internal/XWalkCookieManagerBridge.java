package org.xwalk.core.internal;

public class XWalkCookieManagerBridge extends XWalkCookieManagerInternal {
    private ReflectMethod acceptCookieMethod;
    private ReflectMethod allowFileSchemeCookiesMethod;
    private XWalkCoreBridge coreBridge;
    private ReflectMethod flushCookieStoreMethod;
    private ReflectMethod getCookieStringMethod;
    private ReflectMethod hasCookiesMethod;
    private ReflectMethod removeAllCookieMethod;
    private ReflectMethod removeExpiredCookieMethod;
    private ReflectMethod removeSessionCookieMethod;
    private ReflectMethod setAcceptCookiebooleanMethod;
    private ReflectMethod setAcceptFileSchemeCookiesbooleanMethod;
    private ReflectMethod setCookieStringStringMethod;
    private Object wrapper;

    public XWalkCookieManagerBridge(Object arg6) {
        super();
        this.setAcceptCookiebooleanMethod = new ReflectMethod(null, "setAcceptCookie", new Class[0]);
        this.acceptCookieMethod = new ReflectMethod(null, "acceptCookie", new Class[0]);
        this.setCookieStringStringMethod = new ReflectMethod(null, "setCookie", new Class[0]);
        this.getCookieStringMethod = new ReflectMethod(null, "getCookie", new Class[0]);
        this.removeSessionCookieMethod = new ReflectMethod(null, "removeSessionCookie", new Class[0]);
        this.removeAllCookieMethod = new ReflectMethod(null, "removeAllCookie", new Class[0]);
        this.hasCookiesMethod = new ReflectMethod(null, "hasCookies", new Class[0]);
        this.removeExpiredCookieMethod = new ReflectMethod(null, "removeExpiredCookie", new Class[0]);
        this.flushCookieStoreMethod = new ReflectMethod(null, "flushCookieStore", new Class[0]);
        this.allowFileSchemeCookiesMethod = new ReflectMethod(null, "allowFileSchemeCookies", new Class[0]);
        this.setAcceptFileSchemeCookiesbooleanMethod = new ReflectMethod(null, "setAcceptFileSchemeCookies", new Class[0]);
        this.wrapper = arg6;
        this.reflectionInit();
    }

    public boolean acceptCookie() {
        if(this.acceptCookieMethod != null) {
            if(this.acceptCookieMethod.isNull()) {
            }
            else {
                return this.acceptCookieMethod.invoke(new Object[0]).booleanValue();
            }
        }

        return this.acceptCookieSuper();
    }

    public boolean acceptCookieSuper() {
        return super.acceptCookie();
    }

    public boolean allowFileSchemeCookies() {
        if(this.allowFileSchemeCookiesMethod != null) {
            if(this.allowFileSchemeCookiesMethod.isNull()) {
            }
            else {
                return this.allowFileSchemeCookiesMethod.invoke(new Object[0]).booleanValue();
            }
        }

        return this.allowFileSchemeCookiesSuper();
    }

    public boolean allowFileSchemeCookiesSuper() {
        return super.allowFileSchemeCookies();
    }

    public void flushCookieStore() {
        if(this.flushCookieStoreMethod == null || (this.flushCookieStoreMethod.isNull())) {
            this.flushCookieStoreSuper();
        }
        else {
            this.flushCookieStoreMethod.invoke(new Object[0]);
        }
    }

    public void flushCookieStoreSuper() {
        super.flushCookieStore();
    }

    public String getCookie(String arg4) {
        if(this.getCookieStringMethod != null) {
            if(this.getCookieStringMethod.isNull()) {
            }
            else {
                return this.getCookieStringMethod.invoke(new Object[]{arg4});
            }
        }

        return this.getCookieSuper(arg4);
    }

    public String getCookieSuper(String arg1) {
        arg1 = super.getCookie(arg1);
        if(arg1 == null) {
            return null;
        }

        return arg1;
    }

    public Object getWrapper() {
        return this.wrapper;
    }

    public boolean hasCookies() {
        if(this.hasCookiesMethod != null) {
            if(this.hasCookiesMethod.isNull()) {
            }
            else {
                return this.hasCookiesMethod.invoke(new Object[0]).booleanValue();
            }
        }

        return this.hasCookiesSuper();
    }

    public boolean hasCookiesSuper() {
        return super.hasCookies();
    }

    void reflectionInit() {
        this.coreBridge = XWalkCoreBridge.getInstance();
        if(this.coreBridge == null) {
            return;
        }

        this.setAcceptCookiebooleanMethod.init(this.wrapper, null, "setAcceptCookie", new Class[]{Boolean.TYPE});
        this.acceptCookieMethod.init(this.wrapper, null, "acceptCookie", new Class[0]);
        this.setCookieStringStringMethod.init(this.wrapper, null, "setCookie", new Class[]{String.class, String.class});
        this.getCookieStringMethod.init(this.wrapper, null, "getCookie", new Class[]{String.class});
        this.removeSessionCookieMethod.init(this.wrapper, null, "removeSessionCookie", new Class[0]);
        this.removeAllCookieMethod.init(this.wrapper, null, "removeAllCookie", new Class[0]);
        this.hasCookiesMethod.init(this.wrapper, null, "hasCookies", new Class[0]);
        this.removeExpiredCookieMethod.init(this.wrapper, null, "removeExpiredCookie", new Class[0]);
        this.flushCookieStoreMethod.init(this.wrapper, null, "flushCookieStore", new Class[0]);
        this.allowFileSchemeCookiesMethod.init(this.wrapper, null, "allowFileSchemeCookies", new Class[0]);
        this.setAcceptFileSchemeCookiesbooleanMethod.init(this.wrapper, null, "setAcceptFileSchemeCookies", new Class[]{Boolean.TYPE});
    }

    public void removeAllCookie() {
        if(this.removeAllCookieMethod == null || (this.removeAllCookieMethod.isNull())) {
            this.removeAllCookieSuper();
        }
        else {
            this.removeAllCookieMethod.invoke(new Object[0]);
        }
    }

    public void removeAllCookieSuper() {
        super.removeAllCookie();
    }

    public void removeExpiredCookie() {
        if(this.removeExpiredCookieMethod == null || (this.removeExpiredCookieMethod.isNull())) {
            this.removeExpiredCookieSuper();
        }
        else {
            this.removeExpiredCookieMethod.invoke(new Object[0]);
        }
    }

    public void removeExpiredCookieSuper() {
        super.removeExpiredCookie();
    }

    public void removeSessionCookie() {
        if(this.removeSessionCookieMethod == null || (this.removeSessionCookieMethod.isNull())) {
            this.removeSessionCookieSuper();
        }
        else {
            this.removeSessionCookieMethod.invoke(new Object[0]);
        }
    }

    public void removeSessionCookieSuper() {
        super.removeSessionCookie();
    }

    public void setAcceptCookie(boolean arg4) {
        if(this.setAcceptCookiebooleanMethod == null || (this.setAcceptCookiebooleanMethod.isNull())) {
            this.setAcceptCookieSuper(arg4);
        }
        else {
            this.setAcceptCookiebooleanMethod.invoke(new Object[]{Boolean.valueOf(arg4)});
        }
    }

    public void setAcceptCookieSuper(boolean arg1) {
        super.setAcceptCookie(arg1);
    }

    public void setAcceptFileSchemeCookies(boolean arg4) {
        if(this.setAcceptFileSchemeCookiesbooleanMethod == null || (this.setAcceptFileSchemeCookiesbooleanMethod.isNull())) {
            this.setAcceptFileSchemeCookiesSuper(arg4);
        }
        else {
            this.setAcceptFileSchemeCookiesbooleanMethod.invoke(new Object[]{Boolean.valueOf(arg4)});
        }
    }

    public void setAcceptFileSchemeCookiesSuper(boolean arg1) {
        super.setAcceptFileSchemeCookies(arg1);
    }

    public void setCookie(String arg4, String arg5) {
        if(this.setCookieStringStringMethod == null || (this.setCookieStringStringMethod.isNull())) {
            this.setCookieSuper(arg4, arg5);
        }
        else {
            this.setCookieStringStringMethod.invoke(new Object[]{arg4, arg5});
        }
    }

    public void setCookieSuper(String arg1, String arg2) {
        super.setCookie(arg1, arg2);
    }
}

