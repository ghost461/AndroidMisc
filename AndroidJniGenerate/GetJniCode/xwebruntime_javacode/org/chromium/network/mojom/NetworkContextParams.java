package org.chromium.network.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.InterfaceRequest;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo_base.mojom.FilePath;
import org.chromium.proxy_resolver.mojom.ProxyResolverFactory;

public final class NetworkContextParams extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 120;
    private static final DataHeader[] VERSION_ARRAY;
    public String acceptLanguage;
    public boolean allowGssapiLibraryLoad;
    public FilePath channelIdPath;
    public String contextName;
    public FilePath cookiePath;
    public boolean enableBrotli;
    public boolean enableDataUrlSupport;
    public boolean enableFileUrlSupport;
    public boolean enableFtpUrlSupport;
    public String gssapiLibraryName;
    public boolean http09OnNonDefaultPortsEnabled;
    public boolean httpCacheEnabled;
    public int httpCacheMaxSize;
    public FilePath httpCachePath;
    public FilePath httpServerPropertiesPath;
    public ProxyConfigWithAnnotation initialProxyConfig;
    public boolean persistSessionCookies;
    public InterfaceRequest proxyConfigClientRequest;
    public ProxyConfigPollerClient proxyConfigPollerClient;
    public ProxyResolverFactory proxyResolverFactory;
    public String quicUserAgentId;
    public boolean restoreOldSessionCookies;
    public String userAgent;

    static {
        NetworkContextParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(120, 0)};
        NetworkContextParams.DEFAULT_STRUCT_INFO = NetworkContextParams.VERSION_ARRAY[0];
    }

    public NetworkContextParams() {
        this(0);
    }

    private NetworkContextParams(int arg2) {
        super(120, arg2);
        this.enableBrotli = true;
        this.restoreOldSessionCookies = false;
        this.persistSessionCookies = false;
        this.httpCacheEnabled = true;
        this.httpCacheMaxSize = 0;
        this.enableDataUrlSupport = false;
        this.enableFileUrlSupport = false;
        this.enableFtpUrlSupport = false;
        this.http09OnNonDefaultPortsEnabled = false;
        this.allowGssapiLibraryLoad = false;
    }

    public static NetworkContextParams decode(Decoder arg5) {
        NetworkContextParams v1;
        if(arg5 == null) {
            return null;
        }

        arg5.increaseStackDepth();
        try {
            v1 = new NetworkContextParams(arg5.readAndValidateDataHeader(NetworkContextParams.VERSION_ARRAY).elementsOrVersion);
            v1.contextName = arg5.readString(8, true);
            v1.userAgent = arg5.readString(16, false);
            v1.acceptLanguage = arg5.readString(24, true);
            v1.enableBrotli = arg5.readBoolean(0x20, 0);
            v1.restoreOldSessionCookies = arg5.readBoolean(0x20, 1);
            v1.persistSessionCookies = arg5.readBoolean(0x20, 2);
            v1.httpCacheEnabled = arg5.readBoolean(0x20, 3);
            v1.enableDataUrlSupport = arg5.readBoolean(0x20, 4);
            v1.enableFileUrlSupport = arg5.readBoolean(0x20, 5);
            v1.enableFtpUrlSupport = arg5.readBoolean(0x20, 6);
            v1.http09OnNonDefaultPortsEnabled = arg5.readBoolean(0x20, 7);
            v1.allowGssapiLibraryLoad = arg5.readBoolean(33, 0);
            v1.httpCacheMaxSize = arg5.readInt(36);
            v1.quicUserAgentId = arg5.readString(40, false);
            v1.proxyResolverFactory = arg5.readServiceInterface(0x30, true, ProxyResolverFactory.MANAGER);
            v1.cookiePath = FilePath.decode(arg5.readPointer(56, true));
            v1.channelIdPath = FilePath.decode(arg5.readPointer(0x40, true));
            v1.httpCachePath = FilePath.decode(arg5.readPointer(72, true));
            v1.httpServerPropertiesPath = FilePath.decode(arg5.readPointer(80, true));
            v1.initialProxyConfig = ProxyConfigWithAnnotation.decode(arg5.readPointer(88, true));
            v1.proxyConfigClientRequest = arg5.readInterfaceRequest(0x60, true);
            v1.proxyConfigPollerClient = arg5.readServiceInterface(100, true, ProxyConfigPollerClient.MANAGER);
            v1.gssapiLibraryName = arg5.readString(0x70, false);
        }
        catch(Throwable v0) {
            arg5.decreaseStackDepth();
            throw v0;
        }

        arg5.decreaseStackDepth();
        return v1;
    }

    public static NetworkContextParams deserialize(ByteBuffer arg2) {
        return NetworkContextParams.deserialize(new Message(arg2, new ArrayList()));
    }

    public static NetworkContextParams deserialize(Message arg1) {
        return NetworkContextParams.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg6) {
        arg6 = arg6.getEncoderAtDataOffset(NetworkContextParams.DEFAULT_STRUCT_INFO);
        arg6.encode(this.contextName, 8, true);
        arg6.encode(this.userAgent, 16, false);
        arg6.encode(this.acceptLanguage, 24, true);
        arg6.encode(this.enableBrotli, 0x20, 0);
        arg6.encode(this.restoreOldSessionCookies, 0x20, 1);
        arg6.encode(this.persistSessionCookies, 0x20, 2);
        arg6.encode(this.httpCacheEnabled, 0x20, 3);
        arg6.encode(this.enableDataUrlSupport, 0x20, 4);
        arg6.encode(this.enableFileUrlSupport, 0x20, 5);
        arg6.encode(this.enableFtpUrlSupport, 0x20, 6);
        arg6.encode(this.http09OnNonDefaultPortsEnabled, 0x20, 7);
        arg6.encode(this.allowGssapiLibraryLoad, 33, 0);
        arg6.encode(this.httpCacheMaxSize, 36);
        arg6.encode(this.quicUserAgentId, 40, false);
        arg6.encode(this.proxyResolverFactory, 0x30, true, ProxyResolverFactory.MANAGER);
        arg6.encode(this.cookiePath, 56, true);
        arg6.encode(this.channelIdPath, 0x40, true);
        arg6.encode(this.httpCachePath, 72, true);
        arg6.encode(this.httpServerPropertiesPath, 80, true);
        arg6.encode(this.initialProxyConfig, 88, true);
        arg6.encode(this.proxyConfigClientRequest, 0x60, true);
        arg6.encode(this.proxyConfigPollerClient, 100, true, ProxyConfigPollerClient.MANAGER);
        arg6.encode(this.gssapiLibraryName, 0x70, false);
    }
}

