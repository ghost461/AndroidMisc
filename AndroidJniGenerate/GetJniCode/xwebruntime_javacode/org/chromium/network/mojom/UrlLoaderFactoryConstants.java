package org.chromium.network.mojom;

public final class UrlLoaderFactoryConstants {
    public static final int URL_LOAD_OPTION_NONE = 0;
    public static final int URL_LOAD_OPTION_PAUSE_ON_RESPONSE_STARTED = 16;
    public static final int URL_LOAD_OPTION_SEND_SSL_INFO_FOR_CERTIFICATE_ERROR = 8;
    public static final int URL_LOAD_OPTION_SEND_SSL_INFO_WITH_RESPONSE = 1;
    public static final int URL_LOAD_OPTION_SNIFF_MIME_TYPE = 2;
    public static final int URL_LOAD_OPTION_SYNCHRONOUS = 4;

    private UrlLoaderFactoryConstants() {
        super();
    }
}

