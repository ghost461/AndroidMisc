package org.chromium.blink.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.InterfaceRequest;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;
import org.chromium.url.mojom.Url;

public final class ServiceWorkerInstalledScriptsInfo extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 0x20;
    private static final DataHeader[] VERSION_ARRAY;
    public Url[] installedUrls;
    public ServiceWorkerInstalledScriptsManagerHost managerHostPtr;
    public InterfaceRequest managerRequest;

    static {
        ServiceWorkerInstalledScriptsInfo.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
        ServiceWorkerInstalledScriptsInfo.DEFAULT_STRUCT_INFO = ServiceWorkerInstalledScriptsInfo.VERSION_ARRAY[0];
    }

    public ServiceWorkerInstalledScriptsInfo() {
        this(0);
    }

    private ServiceWorkerInstalledScriptsInfo(int arg2) {
        super(0x20, arg2);
    }

    public static ServiceWorkerInstalledScriptsInfo decode(Decoder arg8) {
        ServiceWorkerInstalledScriptsInfo v1;
        if(arg8 == null) {
            return null;
        }

        arg8.increaseStackDepth();
        try {
            v1 = new ServiceWorkerInstalledScriptsInfo(arg8.readAndValidateDataHeader(ServiceWorkerInstalledScriptsInfo.VERSION_ARRAY).elementsOrVersion);
            int v0_1 = 8;
            v1.managerRequest = arg8.readInterfaceRequest(v0_1, false);
            v1.managerHostPtr = arg8.readServiceInterface(12, false, ServiceWorkerInstalledScriptsManagerHost.MANAGER);
            Decoder v3 = arg8.readPointer(24, false);
            DataHeader v4 = v3.readDataHeaderForPointerArray(-1);
            v1.installedUrls = new Url[v4.elementsOrVersion];
            int v5;
            for(v5 = 0; v5 < v4.elementsOrVersion; ++v5) {
                v1.installedUrls[v5] = Url.decode(v3.readPointer(v5 * 8 + v0_1, false));
            }
        }
        catch(Throwable v0) {
            goto label_38;
        }

        arg8.decreaseStackDepth();
        return v1;
    label_38:
        arg8.decreaseStackDepth();
        throw v0;
    }

    public static ServiceWorkerInstalledScriptsInfo deserialize(ByteBuffer arg2) {
        return ServiceWorkerInstalledScriptsInfo.deserialize(new Message(arg2, new ArrayList()));
    }

    public static ServiceWorkerInstalledScriptsInfo deserialize(Message arg1) {
        return ServiceWorkerInstalledScriptsInfo.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg6) {
        arg6 = arg6.getEncoderAtDataOffset(ServiceWorkerInstalledScriptsInfo.DEFAULT_STRUCT_INFO);
        int v1 = 8;
        arg6.encode(this.managerRequest, v1, false);
        arg6.encode(this.managerHostPtr, 12, false, ServiceWorkerInstalledScriptsManagerHost.MANAGER);
        int v3 = 24;
        if(this.installedUrls == null) {
            arg6.encodeNullPointer(v3, false);
        }
        else {
            arg6 = arg6.encodePointerArray(this.installedUrls.length, v3, -1);
            int v0;
            for(v0 = 0; v0 < this.installedUrls.length; ++v0) {
                arg6.encode(this.installedUrls[v0], v0 * 8 + v1, false);
            }
        }
    }
}

