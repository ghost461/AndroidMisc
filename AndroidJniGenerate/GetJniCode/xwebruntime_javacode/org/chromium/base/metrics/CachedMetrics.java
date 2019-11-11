package org.chromium.base.metrics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.chromium.base.library_loader.LibraryLoader;

public class CachedMetrics {
    public class ActionEvent extends CachedMetric {
        private int mCount;

        public ActionEvent(String arg1) {
            super(arg1);
        }

        protected void commitAndClear() {
            while(this.mCount > 0) {
                this.recordWithNative();
                --this.mCount;
            }
        }

        public void record() {
            List v0 = CachedMetric.sMetrics;
            __monitor_enter(v0);
            try {
                if(LibraryLoader.isInitialized()) {
                    this.recordWithNative();
                }
                else {
                    ++this.mCount;
                    this.addToCache();
                }

                __monitor_exit(v0);
                return;
            label_13:
                __monitor_exit(v0);
            }
            catch(Throwable v1) {
                goto label_13;
            }

            throw v1;
        }

        private void recordWithNative() {
            RecordUserAction.record(this.mName);
        }
    }

    public class BooleanHistogramSample extends CachedMetric {
        private final List mSamples;

        public BooleanHistogramSample(String arg1) {
            super(arg1);
            this.mSamples = new ArrayList();
        }

        protected void commitAndClear() {
            Iterator v0 = this.mSamples.iterator();
            while(v0.hasNext()) {
                this.recordWithNative(v0.next().booleanValue());
            }

            this.mSamples.clear();
        }

        public void record(boolean arg3) {
            List v0 = CachedMetric.sMetrics;
            __monitor_enter(v0);
            try {
                if(LibraryLoader.isInitialized()) {
                    this.recordWithNative(arg3);
                }
                else {
                    this.mSamples.add(Boolean.valueOf(arg3));
                    this.addToCache();
                }

                __monitor_exit(v0);
                return;
            label_13:
                __monitor_exit(v0);
            }
            catch(Throwable v3) {
                goto label_13;
            }

            throw v3;
        }

        private void recordWithNative(boolean arg2) {
            RecordHistogram.recordBooleanHistogram(this.mName, arg2);
        }
    }

    abstract class CachedMetric {
        protected boolean mCached;
        protected final String mName;
        private static final List sMetrics;

        static {
            CachedMetric.sMetrics = new ArrayList();
        }

        protected CachedMetric(String arg1) {
            super();
            this.mName = arg1;
        }

        static List access$000() {
            return CachedMetric.sMetrics;
        }

        protected final void addToCache() {
            if(this.mCached) {
                return;
            }

            CachedMetric.sMetrics.add(this);
            this.mCached = true;
        }

        protected abstract void commitAndClear();
    }

    public class Count1000HistogramSample extends CustomCountHistogramSample {
        public Count1000HistogramSample(String arg4) {
            super(arg4, 1, 1000, 50);
        }
    }

    public class Count100HistogramSample extends CustomCountHistogramSample {
        public Count100HistogramSample(String arg4) {
            super(arg4, 1, 100, 50);
        }
    }

    public class Count1MHistogramSample extends CustomCountHistogramSample {
        public Count1MHistogramSample(String arg4) {
            super(arg4, 1, 1000000, 50);
        }
    }

    public class CustomCountHistogramSample extends CachedMetric {
        private final int mMax;
        private final int mMin;
        private final int mNumBuckets;
        private final List mSamples;

        public CustomCountHistogramSample(String arg1, int arg2, int arg3, int arg4) {
            super(arg1);
            this.mSamples = new ArrayList();
            this.mMin = arg2;
            this.mMax = arg3;
            this.mNumBuckets = arg4;
        }

        protected void commitAndClear() {
            Iterator v0 = this.mSamples.iterator();
            while(v0.hasNext()) {
                this.recordWithNative(v0.next().intValue());
            }

            this.mSamples.clear();
        }

        public void record(int arg3) {
            List v0 = CachedMetric.sMetrics;
            __monitor_enter(v0);
            try {
                if(LibraryLoader.isInitialized()) {
                    this.recordWithNative(arg3);
                }
                else {
                    this.mSamples.add(Integer.valueOf(arg3));
                    this.addToCache();
                }

                __monitor_exit(v0);
                return;
            label_13:
                __monitor_exit(v0);
            }
            catch(Throwable v3) {
                goto label_13;
            }

            throw v3;
        }

        private void recordWithNative(int arg5) {
            RecordHistogram.recordCustomCountHistogram(this.mName, arg5, this.mMin, this.mMax, this.mNumBuckets);
        }
    }

    public class EnumeratedHistogramSample extends CachedMetric {
        private final int mMaxValue;
        private final List mSamples;

        public EnumeratedHistogramSample(String arg1, int arg2) {
            super(arg1);
            this.mSamples = new ArrayList();
            this.mMaxValue = arg2;
        }

        protected void commitAndClear() {
            Iterator v0 = this.mSamples.iterator();
            while(v0.hasNext()) {
                this.recordWithNative(v0.next().intValue());
            }

            this.mSamples.clear();
        }

        public void record(int arg3) {
            List v0 = CachedMetric.sMetrics;
            __monitor_enter(v0);
            try {
                if(LibraryLoader.isInitialized()) {
                    this.recordWithNative(arg3);
                }
                else {
                    this.mSamples.add(Integer.valueOf(arg3));
                    this.addToCache();
                }

                __monitor_exit(v0);
                return;
            label_13:
                __monitor_exit(v0);
            }
            catch(Throwable v3) {
                goto label_13;
            }

            throw v3;
        }

        private void recordWithNative(int arg3) {
            RecordHistogram.recordEnumeratedHistogram(this.mName, arg3, this.mMaxValue);
        }
    }

    public class SparseHistogramSample extends CachedMetric {
        private final List mSamples;

        public SparseHistogramSample(String arg1) {
            super(arg1);
            this.mSamples = new ArrayList();
        }

        protected void commitAndClear() {
            Iterator v0 = this.mSamples.iterator();
            while(v0.hasNext()) {
                this.recordWithNative(v0.next().intValue());
            }

            this.mSamples.clear();
        }

        public void record(int arg3) {
            List v0 = CachedMetric.sMetrics;
            __monitor_enter(v0);
            try {
                if(LibraryLoader.isInitialized()) {
                    this.recordWithNative(arg3);
                }
                else {
                    this.mSamples.add(Integer.valueOf(arg3));
                    this.addToCache();
                }

                __monitor_exit(v0);
                return;
            label_13:
                __monitor_exit(v0);
            }
            catch(Throwable v3) {
                goto label_13;
            }

            throw v3;
        }

        private void recordWithNative(int arg2) {
            RecordHistogram.recordSparseSlowlyHistogram(this.mName, arg2);
        }
    }

    public class TimesHistogramSample extends CachedMetric {
        private final List mSamples;
        private final TimeUnit mTimeUnit;

        public TimesHistogramSample(String arg1, TimeUnit arg2) {
            super(arg1);
            this.mSamples = new ArrayList();
            RecordHistogram.assertTimesHistogramSupportsUnit(arg2);
            this.mTimeUnit = arg2;
        }

        protected void commitAndClear() {
            Iterator v0 = this.mSamples.iterator();
            while(v0.hasNext()) {
                this.recordWithNative(v0.next().longValue());
            }

            this.mSamples.clear();
        }

        public void record(long arg3) {
            List v0 = CachedMetric.sMetrics;
            __monitor_enter(v0);
            try {
                if(LibraryLoader.isInitialized()) {
                    this.recordWithNative(arg3);
                }
                else {
                    this.mSamples.add(Long.valueOf(arg3));
                    this.addToCache();
                }

                __monitor_exit(v0);
                return;
            label_13:
                __monitor_exit(v0);
            }
            catch(Throwable v3) {
                goto label_13;
            }

            throw v3;
        }

        private void recordWithNative(long arg3) {
            RecordHistogram.recordTimesHistogram(this.mName, arg3, this.mTimeUnit);
        }
    }

    public CachedMetrics() {
        super();
    }

    public static void commitCachedMetrics() {
        List v0 = CachedMetric.access$000();
        __monitor_enter(v0);
        try {
            Iterator v1_1 = CachedMetric.access$000().iterator();
            while(v1_1.hasNext()) {
                v1_1.next().commitAndClear();
            }

            __monitor_exit(v0);
            return;
        label_12:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_12;
        }

        throw v1;
    }
}

