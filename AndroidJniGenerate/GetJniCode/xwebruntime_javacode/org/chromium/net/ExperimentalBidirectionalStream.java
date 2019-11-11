package org.chromium.net;

public abstract class ExperimentalBidirectionalStream extends BidirectionalStream {
    public abstract class Builder extends org.chromium.net.BidirectionalStream$Builder {
        public Builder() {
            super();
        }

        public org.chromium.net.BidirectionalStream$Builder addHeader(String arg1, String arg2) {
            return this.addHeader(arg1, arg2);
        }

        public abstract Builder addHeader(String arg1, String arg2);

        public Builder addRequestAnnotation(Object arg1) {
            return this;
        }

        public BidirectionalStream build() {
            return this.build();
        }

        public abstract ExperimentalBidirectionalStream build();

        public org.chromium.net.BidirectionalStream$Builder delayRequestHeadersUntilFirstFlush(boolean arg1) {
            return this.delayRequestHeadersUntilFirstFlush(arg1);
        }

        public abstract Builder delayRequestHeadersUntilFirstFlush(boolean arg1);

        public org.chromium.net.BidirectionalStream$Builder setHttpMethod(String arg1) {
            return this.setHttpMethod(arg1);
        }

        public abstract Builder setHttpMethod(String arg1);

        public org.chromium.net.BidirectionalStream$Builder setPriority(int arg1) {
            return this.setPriority(arg1);
        }

        public abstract Builder setPriority(int arg1);

        public Builder setTrafficStatsTag(int arg1) {
            return this;
        }

        public Builder setTrafficStatsUid(int arg1) {
            return this;
        }
    }

    public ExperimentalBidirectionalStream() {
        super();
    }
}

