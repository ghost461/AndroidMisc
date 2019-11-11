package org.xwalk.core.internal;

import java.security.Principal;
import java.security.PrivateKey;
import java.util.List;

public class ClientCertRequestHandlerBridge extends ClientCertRequestHandlerInternal {
    private ReflectMethod cancelMethod;
    private XWalkCoreBridge coreBridge;
    private ReflectMethod getHostMethod;
    private ReflectMethod getKeyTypesMethod;
    private ReflectMethod getPortMethod;
    private ReflectMethod getPrincipalsMethod;
    private ReflectMethod ignoreMethod;
    private ClientCertRequestHandlerInternal internal;
    private ReflectMethod proceedPrivateKeyListMethod;
    private Object wrapper;

    ClientCertRequestHandlerBridge(ClientCertRequestHandlerInternal arg6) {
        super();
        this.proceedPrivateKeyListMethod = new ReflectMethod(null, "proceed", new Class[0]);
        this.ignoreMethod = new ReflectMethod(null, "ignore", new Class[0]);
        this.cancelMethod = new ReflectMethod(null, "cancel", new Class[0]);
        this.getHostMethod = new ReflectMethod(null, "getHost", new Class[0]);
        this.getPortMethod = new ReflectMethod(null, "getPort", new Class[0]);
        this.getKeyTypesMethod = new ReflectMethod(null, "getKeyTypes", new Class[0]);
        this.getPrincipalsMethod = new ReflectMethod(null, "getPrincipals", new Class[0]);
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

    public String getHost() {
        if(this.getHostMethod != null) {
            if(this.getHostMethod.isNull()) {
            }
            else {
                return this.getHostMethod.invoke(new Object[0]);
            }
        }

        return this.getHostSuper();
    }

    public String getHostSuper() {
        String v0 = this.internal == null ? super.getHost() : this.internal.getHost();
        if(v0 == null) {
            return null;
        }

        return v0;
    }

    public String[] getKeyTypes() {
        if(this.getKeyTypesMethod != null) {
            if(this.getKeyTypesMethod.isNull()) {
            }
            else {
                return this.getKeyTypesMethod.invoke(new Object[0]);
            }
        }

        return this.getKeyTypesSuper();
    }

    public String[] getKeyTypesSuper() {
        String[] v0 = this.internal == null ? super.getKeyTypes() : this.internal.getKeyTypes();
        if(v0 == null) {
            return null;
        }

        return v0;
    }

    public int getPort() {
        if(this.getPortMethod != null) {
            if(this.getPortMethod.isNull()) {
            }
            else {
                return this.getPortMethod.invoke(new Object[0]).intValue();
            }
        }

        return this.getPortSuper();
    }

    public int getPortSuper() {
        int v0 = this.internal == null ? super.getPort() : this.internal.getPort();
        return v0;
    }

    public Principal[] getPrincipals() {
        if(this.getPrincipalsMethod != null) {
            if(this.getPrincipalsMethod.isNull()) {
            }
            else {
                return this.getPrincipalsMethod.invoke(new Object[0]);
            }
        }

        return this.getPrincipalsSuper();
    }

    public Principal[] getPrincipalsSuper() {
        Principal[] v0 = this.internal == null ? super.getPrincipals() : this.internal.getPrincipals();
        if(v0 == null) {
            return null;
        }

        return v0;
    }

    public Object getWrapper() {
        return this.wrapper;
    }

    public void ignore() {
        if(this.ignoreMethod == null || (this.ignoreMethod.isNull())) {
            this.ignoreSuper();
        }
        else {
            this.ignoreMethod.invoke(new Object[0]);
        }
    }

    public void ignoreSuper() {
        if(this.internal == null) {
            super.ignore();
        }
        else {
            this.internal.ignore();
        }
    }

    public void proceed(PrivateKey arg4, List arg5) {
        if(this.proceedPrivateKeyListMethod == null || (this.proceedPrivateKeyListMethod.isNull())) {
            this.proceedSuper(arg4, arg5);
        }
        else {
            this.proceedPrivateKeyListMethod.invoke(new Object[]{arg4, arg5});
        }
    }

    public void proceedSuper(PrivateKey arg2, List arg3) {
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

        ReflectConstructor v0 = new ReflectConstructor(this.coreBridge.getWrapperClass("ClientCertRequestHandler"), new Class[]{Object.class});
        try {
            this.wrapper = v0.newInstance(new Object[]{this});
        }
        catch(UnsupportedOperationException ) {
            return;
        }

        this.proceedPrivateKeyListMethod.init(this.wrapper, null, "proceed", new Class[]{PrivateKey.class, List.class});
        this.ignoreMethod.init(this.wrapper, null, "ignore", new Class[0]);
        this.cancelMethod.init(this.wrapper, null, "cancel", new Class[0]);
        this.getHostMethod.init(this.wrapper, null, "getHost", new Class[0]);
        this.getPortMethod.init(this.wrapper, null, "getPort", new Class[0]);
        this.getKeyTypesMethod.init(this.wrapper, null, "getKeyTypes", new Class[0]);
        this.getPrincipalsMethod.init(this.wrapper, null, "getPrincipals", new Class[0]);
    }
}

