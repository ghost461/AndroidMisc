package org.chromium.components.minidump_uploader.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpURLConnectionFactoryImpl implements HttpURLConnectionFactory {
    public HttpURLConnectionFactoryImpl() {
        super();
    }

    public HttpURLConnection createHttpURLConnection(String arg2) {
        try {
            return new URL(arg2).openConnection();
        }
        catch(IOException ) {
            return null;
        }
    }
}

