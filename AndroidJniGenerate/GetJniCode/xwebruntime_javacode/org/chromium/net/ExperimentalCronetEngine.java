package org.chromium.net;

import android.content.Context;
import android.support.annotation.VisibleForTesting;
import java.io.IOException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.Executor;

public abstract class ExperimentalCronetEngine extends CronetEngine {
    public class Builder extends org.chromium.net.CronetEngine$Builder {
        public Builder(Context arg1) {
            super(arg1);
        }

        public Builder(ICronetEngineBuilder arg1) {
            super(arg1);
        }

        public org.chromium.net.CronetEngine$Builder addPublicKeyPins(String arg1, Set arg2, boolean arg3, Date arg4) {
            return this.addPublicKeyPins(arg1, arg2, arg3, arg4);
        }

        public Builder addPublicKeyPins(String arg1, Set arg2, boolean arg3, Date arg4) {
            super.addPublicKeyPins(arg1, arg2, arg3, arg4);
            return this;
        }

        public org.chromium.net.CronetEngine$Builder addQuicHint(String arg1, int arg2, int arg3) {
            return this.addQuicHint(arg1, arg2, arg3);
        }

        public Builder addQuicHint(String arg1, int arg2, int arg3) {
            super.addQuicHint(arg1, arg2, arg3);
            return this;
        }

        public CronetEngine build() {
            return this.build();
        }

        public ExperimentalCronetEngine build() {
            return this.mBuilderDelegate.build();
        }

        public org.chromium.net.CronetEngine$Builder enableHttp2(boolean arg1) {
            return this.enableHttp2(arg1);
        }

        public Builder enableHttp2(boolean arg1) {
            super.enableHttp2(arg1);
            return this;
        }

        public org.chromium.net.CronetEngine$Builder enableHttpCache(int arg1, long arg2) {
            return this.enableHttpCache(arg1, arg2);
        }

        public Builder enableHttpCache(int arg1, long arg2) {
            super.enableHttpCache(arg1, arg2);
            return this;
        }

        public Builder enableNetworkQualityEstimator(boolean arg2) {
            this.mBuilderDelegate.enableNetworkQualityEstimator(arg2);
            return this;
        }

        public org.chromium.net.CronetEngine$Builder enablePublicKeyPinningBypassForLocalTrustAnchors(boolean arg1) {
            return this.enablePublicKeyPinningBypassForLocalTrustAnchors(arg1);
        }

        public Builder enablePublicKeyPinningBypassForLocalTrustAnchors(boolean arg1) {
            super.enablePublicKeyPinningBypassForLocalTrustAnchors(arg1);
            return this;
        }

        public org.chromium.net.CronetEngine$Builder enableQuic(boolean arg1) {
            return this.enableQuic(arg1);
        }

        public Builder enableQuic(boolean arg1) {
            super.enableQuic(arg1);
            return this;
        }

        public org.chromium.net.CronetEngine$Builder enableSdch(boolean arg1) {
            return this.enableSdch(arg1);
        }

        public Builder enableSdch(boolean arg1) {
            return this;
        }

        @VisibleForTesting public ICronetEngineBuilder getBuilderDelegate() {
            return this.mBuilderDelegate;
        }

        public Builder setCertVerifierData(String arg2) {
            this.mBuilderDelegate.setCertVerifierData(arg2);
            return this;
        }

        public Builder setExperimentalOptions(String arg2) {
            this.mBuilderDelegate.setExperimentalOptions(arg2);
            return this;
        }

        public org.chromium.net.CronetEngine$Builder setLibraryLoader(LibraryLoader arg1) {
            return this.setLibraryLoader(arg1);
        }

        public Builder setLibraryLoader(LibraryLoader arg1) {
            super.setLibraryLoader(arg1);
            return this;
        }

        public org.chromium.net.CronetEngine$Builder setStoragePath(String arg1) {
            return this.setStoragePath(arg1);
        }

        public Builder setStoragePath(String arg1) {
            super.setStoragePath(arg1);
            return this;
        }

        public Builder setThreadPriority(int arg2) {
            this.mBuilderDelegate.setThreadPriority(arg2);
            return this;
        }

        public org.chromium.net.CronetEngine$Builder setUserAgent(String arg1) {
            return this.setUserAgent(arg1);
        }

        public Builder setUserAgent(String arg1) {
            super.setUserAgent(arg1);
            return this;
        }
    }

    public static final int CONNECTION_METRIC_UNKNOWN = -1;
    public static final int EFFECTIVE_CONNECTION_TYPE_2G = 3;
    public static final int EFFECTIVE_CONNECTION_TYPE_3G = 4;
    public static final int EFFECTIVE_CONNECTION_TYPE_4G = 5;
    public static final int EFFECTIVE_CONNECTION_TYPE_OFFLINE = 1;
    public static final int EFFECTIVE_CONNECTION_TYPE_SLOW_2G = 2;
    public static final int EFFECTIVE_CONNECTION_TYPE_UNKNOWN;

    public ExperimentalCronetEngine() {
        super();
    }

    public void addRequestFinishedListener(Listener arg1) {
    }

    public void addRttListener(NetworkQualityRttListener arg1) {
    }

    public void addThroughputListener(NetworkQualityThroughputListener arg1) {
    }

    public void configureNetworkQualityEstimatorForTesting(boolean arg1, boolean arg2, boolean arg3) {
    }

    public String getCertVerifierData(long arg1) {
        return "";
    }

    public int getDownstreamThroughputKbps() {
        return -1;
    }

    public int getEffectiveConnectionType() {
        return 0;
    }

    public int getHttpRttMs() {
        return -1;
    }

    public int getTransportRttMs() {
        return -1;
    }

    public abstract org.chromium.net.ExperimentalBidirectionalStream$Builder newBidirectionalStreamBuilder(String arg1, Callback arg2, Executor arg3);

    public abstract org.chromium.net.ExperimentalUrlRequest$Builder newUrlRequestBuilder(String arg1, org.chromium.net.UrlRequest$Callback arg2, Executor arg3);

    public org.chromium.net.UrlRequest$Builder newUrlRequestBuilder(String arg1, org.chromium.net.UrlRequest$Callback arg2, Executor arg3) {
        return this.newUrlRequestBuilder(arg1, arg2, arg3);
    }

    public URLConnection openConnection(URL arg1, Proxy arg2) throws IOException {
        return arg1.openConnection(arg2);
    }

    public void removeRequestFinishedListener(Listener arg1) {
    }

    public void removeRttListener(NetworkQualityRttListener arg1) {
    }

    public void removeThroughputListener(NetworkQualityThroughputListener arg1) {
    }

    public void startNetLogToDisk(String arg1, boolean arg2, int arg3) {
    }
}

