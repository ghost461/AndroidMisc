package com.tencent.xweb.xprofile.model;

public class WindowPerformance {
    public final class Navigation {
        public long redirectCount;
        public long type;

        public Navigation(long arg1, long arg3) {
            super();
            this.redirectCount = arg1;
            this.type = arg3;
        }

        public String toKvStr() {
            return this.redirectCount + "," + this.type;
        }
    }

    public final class Timing {
        public long connectEnd;
        public long connectStart;
        public long domComplete;
        public long domContentLoadedEventEnd;
        public long domContentLoadedEventStart;
        public long domInteractive;
        public long domLoading;
        public long domainLookupEnd;
        public long domainLookupStart;
        public long fetchStart;
        public long loadEventEnd;
        public long loadEventStart;
        public long navigationStart;
        public long redirectEnd;
        public long redirectStart;
        public long requestStart;
        public long responseEnd;
        public long responseStart;
        public long secureConnectionStart;
        public long unloadEventEnd;
        public long unloadEventStart;

        public Timing(long arg4, long arg6, long arg8, long arg10, long arg12, long arg14, long arg16, long arg18, long arg20, long arg22, long arg24, long arg26, long arg28, long arg30, long arg32, long arg34, long arg36, long arg38, long arg40, long arg42, long arg44) {
            super();
            this.connectEnd = arg4;
            this.connectStart = arg6;
            this.domComplete = arg8;
            this.domContentLoadedEventEnd = arg10;
            this.domContentLoadedEventStart = arg12;
            this.domInteractive = arg14;
            this.domLoading = arg16;
            this.domainLookupEnd = arg18;
            this.domainLookupStart = arg20;
            this.fetchStart = arg22;
            this.loadEventEnd = arg24;
            this.loadEventStart = arg26;
            this.navigationStart = arg28;
            this.redirectEnd = arg30;
            this.redirectStart = arg32;
            this.requestStart = arg34;
            this.responseEnd = arg36;
            this.responseStart = arg38;
            this.secureConnectionStart = arg40;
            this.unloadEventEnd = arg42;
            this.unloadEventStart = arg44;
        }

        public String toKvStr() {
            return this.connectEnd + "," + this.connectStart + "," + this.domComplete + "," + this.domContentLoadedEventEnd + "," + this.domContentLoadedEventStart + "," + this.domInteractive + "," + this.domLoading + "," + this.domainLookupEnd + "," + this.domainLookupStart + "," + this.fetchStart + "," + this.loadEventEnd + "," + this.loadEventStart + "," + this.navigationStart + "," + this.redirectEnd + "," + this.redirectStart + "," + this.requestStart + "," + this.responseEnd + "," + this.responseStart + "," + this.secureConnectionStart + "," + this.unloadEventEnd + "," + this.unloadEventStart;
        }
    }

    public Navigation navigation;
    public double timeOrigin;
    public Timing timing;

    public WindowPerformance() {
        super();
    }

    public String toKvStr() {
        return this.navigation.toKvStr() + "," + this.timing.toKvStr() + "," + (((long)this.timeOrigin)) + "," + String.format("%.4f", Double.valueOf(this.timeOrigin));
    }
}

