package org.chromium.net;

import java.nio.ByteBuffer;
import java.util.concurrent.Executor;

public abstract class UrlRequest {
    public abstract class Builder {
        public static final int REQUEST_PRIORITY_HIGHEST = 4;
        public static final int REQUEST_PRIORITY_IDLE = 0;
        public static final int REQUEST_PRIORITY_LOW = 2;
        public static final int REQUEST_PRIORITY_LOWEST = 1;
        public static final int REQUEST_PRIORITY_MEDIUM = 3;

        public Builder() {
            super();
        }

        public abstract Builder addHeader(String arg1, String arg2);

        public abstract Builder allowDirectExecutor();

        public abstract UrlRequest build();

        public abstract Builder disableCache();

        public abstract Builder setHttpMethod(String arg1);

        public abstract Builder setPriority(int arg1);

        public abstract Builder setUploadDataProvider(UploadDataProvider arg1, Executor arg2);
    }

    public abstract class Callback {
        public Callback() {
            super();
        }

        public void onCanceled(UrlRequest arg1, UrlResponseInfo arg2) {
        }

        public abstract void onFailed(UrlRequest arg1, UrlResponseInfo arg2, CronetException arg3);

        public abstract void onReadCompleted(UrlRequest arg1, UrlResponseInfo arg2, ByteBuffer arg3) throws Exception;

        public abstract void onRedirectReceived(UrlRequest arg1, UrlResponseInfo arg2, String arg3) throws Exception;

        public abstract void onResponseStarted(UrlRequest arg1, UrlResponseInfo arg2) throws Exception;

        public abstract void onSucceeded(UrlRequest arg1, UrlResponseInfo arg2);
    }

    public class Status {
        public static final int CONNECTING = 10;
        public static final int DOWNLOADING_PAC_FILE = 5;
        public static final int ESTABLISHING_PROXY_TUNNEL = 8;
        public static final int IDLE = 0;
        public static final int INVALID = -1;
        public static final int READING_RESPONSE = 14;
        public static final int RESOLVING_HOST = 9;
        public static final int RESOLVING_HOST_IN_PAC_FILE = 7;
        public static final int RESOLVING_PROXY_FOR_URL = 6;
        public static final int SENDING_REQUEST = 12;
        public static final int SSL_HANDSHAKE = 11;
        public static final int WAITING_FOR_AVAILABLE_SOCKET = 2;
        public static final int WAITING_FOR_CACHE = 4;
        public static final int WAITING_FOR_DELEGATE = 3;
        public static final int WAITING_FOR_RESPONSE = 13;
        public static final int WAITING_FOR_STALLED_SOCKET_POOL = 1;

        private Status() {
            super();
        }
    }

    public abstract class StatusListener {
        public StatusListener() {
            super();
        }

        public abstract void onStatus(int arg1);
    }

    public UrlRequest() {
        super();
    }

    public abstract void cancel();

    public abstract void followRedirect();

    public abstract void getStatus(StatusListener arg1);

    public abstract boolean isDone();

    public abstract void read(ByteBuffer arg1);

    public abstract void start();
}

