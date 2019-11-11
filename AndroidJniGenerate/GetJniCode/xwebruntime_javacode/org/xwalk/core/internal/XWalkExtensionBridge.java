package org.xwalk.core.internal;

public class XWalkExtensionBridge extends XWalkExtensionInternal {
    private ReflectMethod broadcastMessageStringMethod;
    private XWalkCoreBridge coreBridge;
    private ReflectMethod onBinaryMessageintbyteArrayMethod;
    private ReflectMethod onInstanceCreatedintMethod;
    private ReflectMethod onInstanceDestroyedintMethod;
    private ReflectMethod onMessageintStringMethod;
    private ReflectMethod onSyncMessageintStringMethod;
    private ReflectMethod postBinaryMessageintbyteArrayMethod;
    private ReflectMethod postMessageintStringMethod;
    private Object wrapper;

    public XWalkExtensionBridge(String arg4, String arg5, Object arg6) {
        super(arg4, arg5);
        this.postMessageintStringMethod = new ReflectMethod(null, "postMessage", new Class[0]);
        this.postBinaryMessageintbyteArrayMethod = new ReflectMethod(null, "postBinaryMessage", new Class[0]);
        this.broadcastMessageStringMethod = new ReflectMethod(null, "broadcastMessage", new Class[0]);
        this.onInstanceCreatedintMethod = new ReflectMethod(null, "onInstanceCreated", new Class[0]);
        this.onInstanceDestroyedintMethod = new ReflectMethod(null, "onInstanceDestroyed", new Class[0]);
        this.onBinaryMessageintbyteArrayMethod = new ReflectMethod(null, "onBinaryMessage", new Class[0]);
        this.onMessageintStringMethod = new ReflectMethod(null, "onMessage", new Class[0]);
        this.onSyncMessageintStringMethod = new ReflectMethod(null, "onSyncMessage", new Class[0]);
        this.wrapper = arg6;
        this.reflectionInit();
    }

    public XWalkExtensionBridge(String arg3, String arg4, String[] arg5, Object arg6) {
        super(arg3, arg4, arg5);
        this.postMessageintStringMethod = new ReflectMethod(null, "postMessage", new Class[0]);
        this.postBinaryMessageintbyteArrayMethod = new ReflectMethod(null, "postBinaryMessage", new Class[0]);
        this.broadcastMessageStringMethod = new ReflectMethod(null, "broadcastMessage", new Class[0]);
        this.onInstanceCreatedintMethod = new ReflectMethod(null, "onInstanceCreated", new Class[0]);
        this.onInstanceDestroyedintMethod = new ReflectMethod(null, "onInstanceDestroyed", new Class[0]);
        this.onBinaryMessageintbyteArrayMethod = new ReflectMethod(null, "onBinaryMessage", new Class[0]);
        this.onMessageintStringMethod = new ReflectMethod(null, "onMessage", new Class[0]);
        this.onSyncMessageintStringMethod = new ReflectMethod(null, "onSyncMessage", new Class[0]);
        this.wrapper = arg6;
        this.reflectionInit();
    }

    public void broadcastMessage(String arg4) {
        if(this.broadcastMessageStringMethod == null || (this.broadcastMessageStringMethod.isNull())) {
            this.broadcastMessageSuper(arg4);
        }
        else {
            this.broadcastMessageStringMethod.invoke(new Object[]{arg4});
        }
    }

    public void broadcastMessageSuper(String arg1) {
        super.broadcastMessage(arg1);
    }

    public Object getWrapper() {
        return this.wrapper;
    }

    public void onBinaryMessage(int arg4, byte[] arg5) {
        if(this.onBinaryMessageintbyteArrayMethod == null || (this.onBinaryMessageintbyteArrayMethod.isNull())) {
            this.onBinaryMessageSuper(arg4, arg5);
        }
        else {
            this.onBinaryMessageintbyteArrayMethod.invoke(new Object[]{Integer.valueOf(arg4), arg5});
        }
    }

    public void onBinaryMessageSuper(int arg1, byte[] arg2) {
        super.onBinaryMessage(arg1, arg2);
    }

    public void onInstanceCreated(int arg4) {
        if(this.onInstanceCreatedintMethod == null || (this.onInstanceCreatedintMethod.isNull())) {
            this.onInstanceCreatedSuper(arg4);
        }
        else {
            this.onInstanceCreatedintMethod.invoke(new Object[]{Integer.valueOf(arg4)});
        }
    }

    public void onInstanceCreatedSuper(int arg1) {
        super.onInstanceCreated(arg1);
    }

    public void onInstanceDestroyed(int arg4) {
        if(this.onInstanceDestroyedintMethod == null || (this.onInstanceDestroyedintMethod.isNull())) {
            this.onInstanceDestroyedSuper(arg4);
        }
        else {
            this.onInstanceDestroyedintMethod.invoke(new Object[]{Integer.valueOf(arg4)});
        }
    }

    public void onInstanceDestroyedSuper(int arg1) {
        super.onInstanceDestroyed(arg1);
    }

    public void onMessage(int arg4, String arg5) {
        this.onMessageintStringMethod.invoke(new Object[]{Integer.valueOf(arg4), arg5});
    }

    public String onSyncMessage(int arg4, String arg5) {
        return this.onSyncMessageintStringMethod.invoke(new Object[]{Integer.valueOf(arg4), arg5});
    }

    public void postBinaryMessage(int arg4, byte[] arg5) {
        if(this.postBinaryMessageintbyteArrayMethod == null || (this.postBinaryMessageintbyteArrayMethod.isNull())) {
            this.postBinaryMessageSuper(arg4, arg5);
        }
        else {
            this.postBinaryMessageintbyteArrayMethod.invoke(new Object[]{Integer.valueOf(arg4), arg5});
        }
    }

    public void postBinaryMessageSuper(int arg1, byte[] arg2) {
        super.postBinaryMessage(arg1, arg2);
    }

    public void postMessage(int arg4, String arg5) {
        if(this.postMessageintStringMethod == null || (this.postMessageintStringMethod.isNull())) {
            this.postMessageSuper(arg4, arg5);
        }
        else {
            this.postMessageintStringMethod.invoke(new Object[]{Integer.valueOf(arg4), arg5});
        }
    }

    public void postMessageSuper(int arg1, String arg2) {
        super.postMessage(arg1, arg2);
    }

    void reflectionInit() {
        this.coreBridge = XWalkCoreBridge.getInstance();
        if(this.coreBridge == null) {
            return;
        }

        this.postMessageintStringMethod.init(this.wrapper, null, "postMessage", new Class[]{Integer.TYPE, String.class});
        this.postBinaryMessageintbyteArrayMethod.init(this.wrapper, null, "postBinaryMessage", new Class[]{Integer.TYPE, byte[].class});
        this.broadcastMessageStringMethod.init(this.wrapper, null, "broadcastMessage", new Class[]{String.class});
        this.onInstanceCreatedintMethod.init(this.wrapper, null, "onInstanceCreated", new Class[]{Integer.TYPE});
        this.onInstanceDestroyedintMethod.init(this.wrapper, null, "onInstanceDestroyed", new Class[]{Integer.TYPE});
        this.onBinaryMessageintbyteArrayMethod.init(this.wrapper, null, "onBinaryMessage", new Class[]{Integer.TYPE, byte[].class});
        this.onMessageintStringMethod.init(this.wrapper, null, "onMessage", new Class[]{Integer.TYPE, String.class});
        this.onSyncMessageintStringMethod.init(this.wrapper, null, "onSyncMessage", new Class[]{Integer.TYPE, String.class});
    }
}

