package android.support.v4.app;

import android.app.Activity;
import android.os.Build$VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.util.SparseIntArray;
import android.view.FrameMetrics;
import android.view.Window$OnFrameMetricsAvailableListener;
import android.view.Window;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

public class FrameMetricsAggregator {
    class android.support.v4.app.FrameMetricsAggregator$1 {
    }

    @RequiresApi(value=24) class FrameMetricsApi24Impl extends FrameMetricsBaseImpl {
        class android.support.v4.app.FrameMetricsAggregator$FrameMetricsApi24Impl$1 implements Window$OnFrameMetricsAvailableListener {
            android.support.v4.app.FrameMetricsAggregator$FrameMetricsApi24Impl$1(FrameMetricsApi24Impl arg1) {
                FrameMetricsApi24Impl.this = arg1;
                super();
            }

            public void onFrameMetricsAvailable(Window arg9, FrameMetrics arg10, int arg11) {
                int v1 = 8;
                if((FrameMetricsApi24Impl.this.mTrackingFlags & 1) != 0) {
                    FrameMetricsApi24Impl.this.addDurationItem(FrameMetricsApi24Impl.this.mMetrics[0], arg10.getMetric(v1));
                }

                int v2 = 2;
                if((FrameMetricsApi24Impl.this.mTrackingFlags & v2) != 0) {
                    FrameMetricsApi24Impl.this.addDurationItem(FrameMetricsApi24Impl.this.mMetrics[1], arg10.getMetric(1));
                }

                arg11 = 4;
                int v3 = 3;
                if((FrameMetricsApi24Impl.this.mTrackingFlags & arg11) != 0) {
                    FrameMetricsApi24Impl.this.addDurationItem(FrameMetricsApi24Impl.this.mMetrics[v2], arg10.getMetric(v3));
                }

                if((FrameMetricsApi24Impl.this.mTrackingFlags & v1) != 0) {
                    FrameMetricsApi24Impl.this.addDurationItem(FrameMetricsApi24Impl.this.mMetrics[v3], arg10.getMetric(arg11));
                }

                v3 = 5;
                if((FrameMetricsApi24Impl.this.mTrackingFlags & 16) != 0) {
                    FrameMetricsApi24Impl.this.addDurationItem(FrameMetricsApi24Impl.this.mMetrics[arg11], arg10.getMetric(v3));
                }

                arg11 = 7;
                int v4 = 6;
                if((FrameMetricsApi24Impl.this.mTrackingFlags & 0x40) != 0) {
                    FrameMetricsApi24Impl.this.addDurationItem(FrameMetricsApi24Impl.this.mMetrics[v4], arg10.getMetric(arg11));
                }

                if((FrameMetricsApi24Impl.this.mTrackingFlags & 0x20) != 0) {
                    FrameMetricsApi24Impl.this.addDurationItem(FrameMetricsApi24Impl.this.mMetrics[v3], arg10.getMetric(v4));
                }

                if((FrameMetricsApi24Impl.this.mTrackingFlags & 0x80) != 0) {
                    FrameMetricsApi24Impl.this.addDurationItem(FrameMetricsApi24Impl.this.mMetrics[arg11], arg10.getMetric(0));
                }

                if((FrameMetricsApi24Impl.this.mTrackingFlags & 0x100) != 0) {
                    FrameMetricsApi24Impl.this.addDurationItem(FrameMetricsApi24Impl.this.mMetrics[v1], arg10.getMetric(v2));
                }
            }
        }

        private static final int NANOS_PER_MS = 1000000;
        private static final int NANOS_ROUNDING_VALUE = 500000;
        private ArrayList mActivities;
        Window$OnFrameMetricsAvailableListener mListener;
        private SparseIntArray[] mMetrics;
        private int mTrackingFlags;
        private static Handler sHandler;
        private static HandlerThread sHandlerThread;

        static {
        }

        FrameMetricsApi24Impl(int arg2) {
            super(null);
            this.mMetrics = new SparseIntArray[9];
            this.mActivities = new ArrayList();
            this.mListener = new android.support.v4.app.FrameMetricsAggregator$FrameMetricsApi24Impl$1(this);
            this.mTrackingFlags = arg2;
        }

        static int access$100(FrameMetricsApi24Impl arg0) {
            return arg0.mTrackingFlags;
        }

        static SparseIntArray[] access$200(FrameMetricsApi24Impl arg0) {
            return arg0.mMetrics;
        }

        public void add(Activity arg4) {
            if(FrameMetricsApi24Impl.sHandlerThread == null) {
                FrameMetricsApi24Impl.sHandlerThread = new HandlerThread("FrameMetricsAggregator");
                FrameMetricsApi24Impl.sHandlerThread.start();
                FrameMetricsApi24Impl.sHandler = new Handler(FrameMetricsApi24Impl.sHandlerThread.getLooper());
            }

            int v0;
            for(v0 = 0; v0 <= 8; ++v0) {
                if(this.mMetrics[v0] == null && (this.mTrackingFlags & 1 << v0) != 0) {
                    this.mMetrics[v0] = new SparseIntArray();
                }
            }

            arg4.getWindow().addOnFrameMetricsAvailableListener(this.mListener, FrameMetricsApi24Impl.sHandler);
            this.mActivities.add(new WeakReference(arg4));
        }

        void addDurationItem(SparseIntArray arg5, long arg6) {
            if(arg5 != null) {
                int v0 = ((int)((arg6 + 500000) / 1000000));
                if(arg6 >= 0) {
                    arg5.put(v0, arg5.get(v0) + 1);
                }
            }
        }

        public SparseIntArray[] getMetrics() {
            return this.mMetrics;
        }

        public SparseIntArray[] remove(Activity arg4) {
            Object v1;
            Iterator v0 = this.mActivities.iterator();
            do {
                if(v0.hasNext()) {
                    v1 = v0.next();
                    if(((WeakReference)v1).get() != arg4) {
                        continue;
                    }

                    break;
                }

                goto label_9;
            }
            while(true);

            this.mActivities.remove(v1);
        label_9:
            arg4.getWindow().removeOnFrameMetricsAvailableListener(this.mListener);
            return this.mMetrics;
        }

        public SparseIntArray[] reset() {
            SparseIntArray[] v0 = this.mMetrics;
            this.mMetrics = new SparseIntArray[9];
            return v0;
        }

        public SparseIntArray[] stop() {
            int v0;
            for(v0 = this.mActivities.size() - 1; v0 >= 0; --v0) {
                Object v1 = this.mActivities.get(v0);
                Object v2 = ((WeakReference)v1).get();
                if(((WeakReference)v1).get() != null) {
                    ((Activity)v2).getWindow().removeOnFrameMetricsAvailableListener(this.mListener);
                    this.mActivities.remove(v0);
                }
            }

            return this.mMetrics;
        }
    }

    class FrameMetricsBaseImpl {
        FrameMetricsBaseImpl(android.support.v4.app.FrameMetricsAggregator$1 arg1) {
            this();
        }

        private FrameMetricsBaseImpl() {
            super();
        }

        public void add(Activity arg1) {
        }

        public SparseIntArray[] getMetrics() {
            return null;
        }

        public SparseIntArray[] remove(Activity arg1) {
            return null;
        }

        public SparseIntArray[] reset() {
            return null;
        }

        public SparseIntArray[] stop() {
            return null;
        }
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) @Retention(value=RetentionPolicy.SOURCE) @public interface MetricType {
    }

    public static final int ANIMATION_DURATION = 0x100;
    public static final int ANIMATION_INDEX = 8;
    public static final int COMMAND_DURATION = 0x20;
    public static final int COMMAND_INDEX = 5;
    private static final boolean DBG = false;
    public static final int DELAY_DURATION = 0x80;
    public static final int DELAY_INDEX = 7;
    public static final int DRAW_DURATION = 8;
    public static final int DRAW_INDEX = 3;
    public static final int EVERY_DURATION = 0x1FF;
    public static final int INPUT_DURATION = 2;
    public static final int INPUT_INDEX = 1;
    private static final int LAST_INDEX = 8;
    public static final int LAYOUT_MEASURE_DURATION = 4;
    public static final int LAYOUT_MEASURE_INDEX = 2;
    public static final int SWAP_DURATION = 0x40;
    public static final int SWAP_INDEX = 6;
    public static final int SYNC_DURATION = 16;
    public static final int SYNC_INDEX = 4;
    private static final String TAG = "FrameMetrics";
    public static final int TOTAL_DURATION = 1;
    public static final int TOTAL_INDEX;
    private FrameMetricsBaseImpl mInstance;

    public FrameMetricsAggregator() {
        this(1);
    }

    public FrameMetricsAggregator(int arg3) {
        super();
        this.mInstance = Build$VERSION.SDK_INT >= 24 ? new FrameMetricsApi24Impl(arg3) : new FrameMetricsBaseImpl(null);
    }

    public void add(@NonNull Activity arg2) {
        this.mInstance.add(arg2);
    }

    @Nullable public SparseIntArray[] getMetrics() {
        return this.mInstance.getMetrics();
    }

    @Nullable public SparseIntArray[] remove(@NonNull Activity arg2) {
        return this.mInstance.remove(arg2);
    }

    @Nullable public SparseIntArray[] reset() {
        return this.mInstance.reset();
    }

    @Nullable public SparseIntArray[] stop() {
        return this.mInstance.stop();
    }
}

