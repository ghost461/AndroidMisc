package android.support.v4.app;

import android.app.Service;
import android.app.job.JobInfo$Builder;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobServiceEngine;
import android.app.job.JobWorkItem;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build$VERSION;
import android.os.IBinder;
import android.os.PowerManager$WakeLock;
import android.os.PowerManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class JobIntentService extends Service {
    final class CommandProcessor extends AsyncTask {
        CommandProcessor(JobIntentService arg1) {
            JobIntentService.this = arg1;
            super();
        }

        protected Object doInBackground(Object[] arg1) {
            return this.doInBackground(((Void[])arg1));
        }

        protected Void doInBackground(Void[] arg3) {
            while(true) {
                GenericWorkItem v3 = JobIntentService.this.dequeueWork();
                if(v3 == null) {
                    return null;
                }

                JobIntentService.this.onHandleWork(v3.getIntent());
                v3.complete();
            }

            return null;
        }

        protected void onCancelled(Object arg1) {
            this.onCancelled(((Void)arg1));
        }

        protected void onCancelled(Void arg1) {
            JobIntentService.this.processorFinished();
        }

        protected void onPostExecute(Object arg1) {
            this.onPostExecute(((Void)arg1));
        }

        protected void onPostExecute(Void arg1) {
            JobIntentService.this.processorFinished();
        }
    }

    interface CompatJobEngine {
        IBinder compatGetBinder();

        GenericWorkItem dequeueWork();
    }

    final class CompatWorkEnqueuer extends WorkEnqueuer {
        private final Context mContext;
        private final PowerManager$WakeLock mLaunchWakeLock;
        boolean mLaunchingService;
        private final PowerManager$WakeLock mRunWakeLock;
        boolean mServiceProcessing;

        CompatWorkEnqueuer(Context arg4, ComponentName arg5) {
            super(arg4, arg5);
            this.mContext = arg4.getApplicationContext();
            Object v4 = arg4.getSystemService("power");
            StringBuilder v0 = new StringBuilder();
            v0.append(arg5.getClassName());
            v0.append(":launch");
            this.mLaunchWakeLock = ((PowerManager)v4).newWakeLock(1, v0.toString());
            this.mLaunchWakeLock.setReferenceCounted(false);
            v0 = new StringBuilder();
            v0.append(arg5.getClassName());
            v0.append(":run");
            this.mRunWakeLock = ((PowerManager)v4).newWakeLock(1, v0.toString());
            this.mRunWakeLock.setReferenceCounted(false);
        }

        void enqueueWork(Intent arg3) {
            Intent v0 = new Intent(arg3);
            v0.setComponent(this.mComponentName);
            if(this.mContext.startService(v0) != null) {
                __monitor_enter(this);
                try {
                    if(!this.mLaunchingService) {
                        this.mLaunchingService = true;
                        if(!this.mServiceProcessing) {
                            this.mLaunchWakeLock.acquire(60000);
                        }
                    }

                    __monitor_exit(this);
                    return;
                label_20:
                    __monitor_exit(this);
                }
                catch(Throwable v3) {
                    goto label_20;
                }

                throw v3;
            }
        }

        public void serviceProcessingFinished() {
            __monitor_enter(this);
            try {
                if(this.mServiceProcessing) {
                    if(this.mLaunchingService) {
                        this.mLaunchWakeLock.acquire(60000);
                    }

                    this.mServiceProcessing = false;
                    this.mRunWakeLock.release();
                }

                __monitor_exit(this);
                return;
            label_15:
                __monitor_exit(this);
            }
            catch(Throwable v0) {
                goto label_15;
            }

            throw v0;
        }

        public void serviceProcessingStarted() {
            __monitor_enter(this);
            try {
                if(!this.mServiceProcessing) {
                    this.mServiceProcessing = true;
                    this.mRunWakeLock.acquire(600000);
                    this.mLaunchWakeLock.release();
                }

                __monitor_exit(this);
                return;
            label_13:
                __monitor_exit(this);
            }
            catch(Throwable v0) {
                goto label_13;
            }

            throw v0;
        }

        public void serviceStartReceived() {
            __monitor_enter(this);
            try {
                this.mLaunchingService = false;
                __monitor_exit(this);
                return;
            label_6:
                __monitor_exit(this);
            }
            catch(Throwable v0) {
                goto label_6;
            }

            throw v0;
        }
    }

    final class CompatWorkItem implements GenericWorkItem {
        final Intent mIntent;
        final int mStartId;

        CompatWorkItem(JobIntentService arg1, Intent arg2, int arg3) {
            JobIntentService.this = arg1;
            super();
            this.mIntent = arg2;
            this.mStartId = arg3;
        }

        public void complete() {
            JobIntentService.this.stopSelf(this.mStartId);
        }

        public Intent getIntent() {
            return this.mIntent;
        }
    }

    interface GenericWorkItem {
        void complete();

        Intent getIntent();
    }

    @RequiresApi(value=26) final class JobServiceEngineImpl extends JobServiceEngine implements CompatJobEngine {
        final class WrapperWorkItem implements GenericWorkItem {
            final JobWorkItem mJobWork;

            WrapperWorkItem(JobServiceEngineImpl arg1, JobWorkItem arg2) {
                JobServiceEngineImpl.this = arg1;
                super();
                this.mJobWork = arg2;
            }

            public void complete() {
                Object v0 = JobServiceEngineImpl.this.mLock;
                __monitor_enter(v0);
                try {
                    if(JobServiceEngineImpl.this.mParams != null) {
                        JobServiceEngineImpl.this.mParams.completeWork(this.mJobWork);
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

            public Intent getIntent() {
                return this.mJobWork.getIntent();
            }
        }

        static final boolean DEBUG = false;
        static final String TAG = "JobServiceEngineImpl";
        final Object mLock;
        JobParameters mParams;
        final JobIntentService mService;

        JobServiceEngineImpl(JobIntentService arg2) {
            super(((Service)arg2));
            this.mLock = new Object();
            this.mService = arg2;
        }

        public IBinder compatGetBinder() {
            return this.getBinder();
        }

        public GenericWorkItem dequeueWork() {
            JobWorkItem v1_1;
            Object v0 = this.mLock;
            __monitor_enter(v0);
            try {
                GenericWorkItem v2 = null;
                if(this.mParams == null) {
                    __monitor_exit(v0);
                    return v2;
                }

                v1_1 = this.mParams.dequeueWork();
                __monitor_exit(v0);
                if(v1_1 == null) {
                    return v2;
                }
            }
            catch(Throwable v1) {
                try {
                label_20:
                    __monitor_exit(v0);
                }
                catch(Throwable v1) {
                    goto label_20;
                }

                throw v1;
            }

            v1_1.getIntent().setExtrasClassLoader(this.mService.getClassLoader());
            return new WrapperWorkItem(this, v1_1);
        }

        public boolean onStartJob(JobParameters arg2) {
            this.mParams = arg2;
            this.mService.ensureProcessorRunningLocked(false);
            return 1;
        }

        public boolean onStopJob(JobParameters arg3) {
            boolean v3 = this.mService.doStopCurrentWork();
            Object v0 = this.mLock;
            __monitor_enter(v0);
            JobParameters v1 = null;
            try {
                this.mParams = v1;
                __monitor_exit(v0);
                return v3;
            label_9:
                __monitor_exit(v0);
            }
            catch(Throwable v3_1) {
                goto label_9;
            }

            throw v3_1;
        }
    }

    @RequiresApi(value=26) final class JobWorkEnqueuer extends WorkEnqueuer {
        private final JobInfo mJobInfo;
        private final JobScheduler mJobScheduler;

        JobWorkEnqueuer(Context arg3, ComponentName arg4, int arg5) {
            super(arg3, arg4);
            this.ensureJobId(arg5);
            this.mJobInfo = new JobInfo$Builder(arg5, this.mComponentName).setOverrideDeadline(0).build();
            this.mJobScheduler = arg3.getApplicationContext().getSystemService("jobscheduler");
        }

        void enqueueWork(Intent arg4) {
            this.mJobScheduler.enqueue(this.mJobInfo, new JobWorkItem(arg4));
        }
    }

    abstract class WorkEnqueuer {
        final ComponentName mComponentName;
        boolean mHasJobId;
        int mJobId;

        WorkEnqueuer(Context arg1, ComponentName arg2) {
            super();
            this.mComponentName = arg2;
        }

        abstract void enqueueWork(Intent arg1);

        void ensureJobId(int arg4) {
            if(!this.mHasJobId) {
                this.mHasJobId = true;
                this.mJobId = arg4;
            }
            else if(this.mJobId != arg4) {
                StringBuilder v1 = new StringBuilder();
                v1.append("Given job ID ");
                v1.append(arg4);
                v1.append(" is different than previous ");
                v1.append(this.mJobId);
                throw new IllegalArgumentException(v1.toString());
            }
        }

        public void serviceProcessingFinished() {
        }

        public void serviceProcessingStarted() {
        }

        public void serviceStartReceived() {
        }
    }

    static final boolean DEBUG = false;
    static final String TAG = "JobIntentService";
    final ArrayList mCompatQueue;
    WorkEnqueuer mCompatWorkEnqueuer;
    CommandProcessor mCurProcessor;
    boolean mDestroyed;
    boolean mInterruptIfStopped;
    CompatJobEngine mJobImpl;
    boolean mStopped;
    static final HashMap sClassWorkEnqueuer;
    static final Object sLock;

    static {
        JobIntentService.sLock = new Object();
        JobIntentService.sClassWorkEnqueuer = new HashMap();
    }

    public JobIntentService() {
        super();
        this.mInterruptIfStopped = false;
        this.mStopped = false;
        this.mDestroyed = false;
        this.mCompatQueue = Build$VERSION.SDK_INT >= 26 ? null : new ArrayList();
    }

    GenericWorkItem dequeueWork() {
        if(this.mJobImpl != null) {
            return this.mJobImpl.dequeueWork();
        }

        ArrayList v0 = this.mCompatQueue;
        __monitor_enter(v0);
        try {
            if(this.mCompatQueue.size() > 0) {
                __monitor_exit(v0);
                return this.mCompatQueue.remove(0);
            }

            __monitor_exit(v0);
            return null;
        label_19:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_19;
        }

        throw v1;
    }

    boolean doStopCurrentWork() {
        if(this.mCurProcessor != null) {
            this.mCurProcessor.cancel(this.mInterruptIfStopped);
        }

        this.mStopped = true;
        return this.onStopCurrentWork();
    }

    public static void enqueueWork(@NonNull Context arg2, @NonNull ComponentName arg3, int arg4, @NonNull Intent arg5) {
        if(arg5 == null) {
            throw new IllegalArgumentException("work must not be null");
        }

        Object v0 = JobIntentService.sLock;
        __monitor_enter(v0);
        try {
            WorkEnqueuer v2_1 = JobIntentService.getWorkEnqueuer(arg2, arg3, true, arg4);
            v2_1.ensureJobId(arg4);
            v2_1.enqueueWork(arg5);
            __monitor_exit(v0);
            return;
        label_14:
            __monitor_exit(v0);
        }
        catch(Throwable v2) {
            goto label_14;
        }

        throw v2;
    }

    public static void enqueueWork(@NonNull Context arg1, @NonNull Class arg2, int arg3, @NonNull Intent arg4) {
        JobIntentService.enqueueWork(arg1, new ComponentName(arg1, arg2), arg3, arg4);
    }

    void ensureProcessorRunningLocked(boolean arg3) {
        if(this.mCurProcessor == null) {
            this.mCurProcessor = new CommandProcessor(this);
            if(this.mCompatWorkEnqueuer != null && (arg3)) {
                this.mCompatWorkEnqueuer.serviceProcessingStarted();
            }

            this.mCurProcessor.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    static WorkEnqueuer getWorkEnqueuer(Context arg2, ComponentName arg3, boolean arg4, int arg5) {
        JobWorkEnqueuer v4;
        Object v0 = JobIntentService.sClassWorkEnqueuer.get(arg3);
        if(v0 == null) {
            if(Build$VERSION.SDK_INT < 26) {
                CompatWorkEnqueuer v4_1 = new CompatWorkEnqueuer(arg2, arg3);
            }
            else if(!arg4) {
                throw new IllegalArgumentException("Can\'t be here without a job id");
            }
            else {
                v4 = new JobWorkEnqueuer(arg2, arg3, arg5);
            }

            JobWorkEnqueuer v0_1 = v4;
            JobIntentService.sClassWorkEnqueuer.put(arg3, v0_1);
        }

        return ((WorkEnqueuer)v0);
    }

    public boolean isStopped() {
        return this.mStopped;
    }

    public IBinder onBind(@NonNull Intent arg1) {
        if(this.mJobImpl != null) {
            return this.mJobImpl.compatGetBinder();
        }

        return null;
    }

    public void onCreate() {
        super.onCreate();
        WorkEnqueuer v1 = null;
        if(Build$VERSION.SDK_INT >= 26) {
            this.mJobImpl = new JobServiceEngineImpl(this);
            this.mCompatWorkEnqueuer = v1;
        }
        else {
            this.mJobImpl = ((CompatJobEngine)v1);
            this.mCompatWorkEnqueuer = JobIntentService.getWorkEnqueuer(((Context)this), new ComponentName(((Context)this), this.getClass()), false, 0);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if(this.mCompatQueue != null) {
            ArrayList v0 = this.mCompatQueue;
            __monitor_enter(v0);
            try {
                this.mDestroyed = true;
                this.mCompatWorkEnqueuer.serviceProcessingFinished();
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

    protected abstract void onHandleWork(@NonNull Intent arg1);

    public int onStartCommand(@Nullable Intent arg3, int arg4, int arg5) {
        if(this.mCompatQueue != null) {
            this.mCompatWorkEnqueuer.serviceStartReceived();
            ArrayList v4 = this.mCompatQueue;
            __monitor_enter(v4);
            try {
                ArrayList v0 = this.mCompatQueue;
                if(arg3 != null) {
                }
                else {
                    arg3 = new Intent();
                }

                v0.add(new CompatWorkItem(this, arg3, arg5));
                this.ensureProcessorRunningLocked(true);
                __monitor_exit(v4);
                return 3;
            label_20:
                __monitor_exit(v4);
            }
            catch(Throwable v3) {
                goto label_20;
            }

            throw v3;
        }

        return 2;
    }

    public boolean onStopCurrentWork() {
        return 1;
    }

    void processorFinished() {
        if(this.mCompatQueue != null) {
            ArrayList v0 = this.mCompatQueue;
            __monitor_enter(v0);
            CommandProcessor v1 = null;
            try {
                this.mCurProcessor = v1;
                if(this.mCompatQueue != null && this.mCompatQueue.size() > 0) {
                    this.ensureProcessorRunningLocked(false);
                }
                else if(!this.mDestroyed) {
                    this.mCompatWorkEnqueuer.serviceProcessingFinished();
                }

                __monitor_exit(v0);
                return;
            label_21:
                __monitor_exit(v0);
            }
            catch(Throwable v1_1) {
                goto label_21;
            }

            throw v1_1;
        }
    }

    public void setInterruptIfStopped(boolean arg1) {
        this.mInterruptIfStopped = arg1;
    }
}

