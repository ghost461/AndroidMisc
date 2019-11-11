package org.chromium.net;

import java.util.concurrent.Executor;

public abstract class ExperimentalUrlRequest extends UrlRequest {
    public abstract class Builder extends org.chromium.net.UrlRequest$Builder {
        public Builder() {
            super();
        }

        public abstract Builder addHeader(String arg1, String arg2);

        public org.chromium.net.UrlRequest$Builder addHeader(String arg1, String arg2) {
            return this.addHeader(arg1, arg2);
        }

        public Builder addRequestAnnotation(Object arg1) {
            return this;
        }

        public abstract Builder allowDirectExecutor();

        public org.chromium.net.UrlRequest$Builder allowDirectExecutor() {
            return this.allowDirectExecutor();
        }

        public abstract ExperimentalUrlRequest build();

        public UrlRequest build() {
            return this.build();
        }

        public abstract Builder disableCache();

        public org.chromium.net.UrlRequest$Builder disableCache() {
            return this.disableCache();
        }

        public Builder disableConnectionMigration() {
            return this;
        }

        public abstract Builder setHttpMethod(String arg1);

        public org.chromium.net.UrlRequest$Builder setHttpMethod(String arg1) {
            return this.setHttpMethod(arg1);
        }

        public abstract Builder setPriority(int arg1);

        public org.chromium.net.UrlRequest$Builder setPriority(int arg1) {
            return this.setPriority(arg1);
        }

        public Builder setRequestFinishedListener(Listener arg1) {
            return this;
        }

        public Builder setTrafficStatsTag(int arg1) {
            return this;
        }

        public Builder setTrafficStatsUid(int arg1) {
            return this;
        }

        public abstract Builder setUploadDataProvider(UploadDataProvider arg1, Executor arg2);

        public org.chromium.net.UrlRequest$Builder setUploadDataProvider(UploadDataProvider arg1, Executor arg2) {
            return this.setUploadDataProvider(arg1, arg2);
        }
    }

    public ExperimentalUrlRequest() {
        super();
    }
}

