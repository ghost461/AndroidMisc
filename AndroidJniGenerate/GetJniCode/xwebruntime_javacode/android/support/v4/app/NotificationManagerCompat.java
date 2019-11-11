package android.support.v4.app;

import android.app.AppOpsManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler$Callback;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.provider.Settings$Secure;
import android.support.annotation.GuardedBy;
import android.util.Log;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map$Entry;
import java.util.Map;
import java.util.Set;

public final class NotificationManagerCompat {
    class CancelTask implements Task {
        final boolean all;
        final int id;
        final String packageName;
        final String tag;

        CancelTask(String arg1, int arg2, String arg3) {
            super();
            this.packageName = arg1;
            this.id = arg2;
            this.tag = arg3;
            this.all = false;
        }

        CancelTask(String arg1) {
            super();
            this.packageName = arg1;
            this.id = 0;
            this.tag = null;
            this.all = true;
        }

        public void send(INotificationSideChannel arg4) throws RemoteException {
            if(this.all) {
                arg4.cancelAll(this.packageName);
            }
            else {
                arg4.cancel(this.packageName, this.id, this.tag);
            }
        }

        public String toString() {
            StringBuilder v0 = new StringBuilder("CancelTask[");
            v0.append("packageName:");
            v0.append(this.packageName);
            v0.append(", id:");
            v0.append(this.id);
            v0.append(", tag:");
            v0.append(this.tag);
            v0.append(", all:");
            v0.append(this.all);
            v0.append("]");
            return v0.toString();
        }
    }

    class NotifyTask implements Task {
        final int id;
        final Notification notif;
        final String packageName;
        final String tag;

        NotifyTask(String arg1, int arg2, String arg3, Notification arg4) {
            super();
            this.packageName = arg1;
            this.id = arg2;
            this.tag = arg3;
            this.notif = arg4;
        }

        public void send(INotificationSideChannel arg5) throws RemoteException {
            arg5.notify(this.packageName, this.id, this.tag, this.notif);
        }

        public String toString() {
            StringBuilder v0 = new StringBuilder("NotifyTask[");
            v0.append("packageName:");
            v0.append(this.packageName);
            v0.append(", id:");
            v0.append(this.id);
            v0.append(", tag:");
            v0.append(this.tag);
            v0.append("]");
            return v0.toString();
        }
    }

    class ServiceConnectedEvent {
        final ComponentName componentName;
        final IBinder iBinder;

        ServiceConnectedEvent(ComponentName arg1, IBinder arg2) {
            super();
            this.componentName = arg1;
            this.iBinder = arg2;
        }
    }

    class SideChannelManager implements ServiceConnection, Handler$Callback {
        class ListenerRecord {
            public boolean bound;
            public final ComponentName componentName;
            public int retryCount;
            public INotificationSideChannel service;
            public LinkedList taskQueue;

            public ListenerRecord(ComponentName arg3) {
                super();
                this.bound = false;
                this.taskQueue = new LinkedList();
                this.retryCount = 0;
                this.componentName = arg3;
            }
        }

        private static final int MSG_QUEUE_TASK = 0;
        private static final int MSG_RETRY_LISTENER_QUEUE = 3;
        private static final int MSG_SERVICE_CONNECTED = 1;
        private static final int MSG_SERVICE_DISCONNECTED = 2;
        private Set mCachedEnabledPackages;
        private final Context mContext;
        private final Handler mHandler;
        private final HandlerThread mHandlerThread;
        private final Map mRecordMap;

        public SideChannelManager(Context arg2) {
            super();
            this.mRecordMap = new HashMap();
            this.mCachedEnabledPackages = new HashSet();
            this.mContext = arg2;
            this.mHandlerThread = new HandlerThread("NotificationManagerCompat");
            this.mHandlerThread.start();
            this.mHandler = new Handler(this.mHandlerThread.getLooper(), ((Handler$Callback)this));
        }

        private boolean ensureServiceBound(ListenerRecord arg4) {
            if(arg4.bound) {
                return 1;
            }

            arg4.bound = this.mContext.bindService(new Intent("android.support.BIND_NOTIFICATION_SIDE_CHANNEL").setComponent(arg4.componentName), ((ServiceConnection)this), 33);
            if(arg4.bound) {
                arg4.retryCount = 0;
            }
            else {
                Log.w("NotifManCompat", "Unable to bind to listener " + arg4.componentName);
                this.mContext.unbindService(((ServiceConnection)this));
            }

            return arg4.bound;
        }

        private void ensureServiceUnbound(ListenerRecord arg2) {
            if(arg2.bound) {
                this.mContext.unbindService(((ServiceConnection)this));
                arg2.bound = false;
            }

            arg2.service = null;
        }

        public boolean handleMessage(Message arg3) {
            switch(arg3.what) {
                case 0: {
                    goto label_16;
                }
                case 1: {
                    goto label_11;
                }
                case 2: {
                    goto label_8;
                }
                case 3: {
                    goto label_5;
                }
            }

            return 0;
        label_5:
            this.handleRetryListenerQueue(arg3.obj);
            return 1;
        label_8:
            this.handleServiceDisconnected(arg3.obj);
            return 1;
        label_11:
            this.handleServiceConnected(arg3.obj.componentName, arg3.obj.iBinder);
            return 1;
        label_16:
            this.handleQueueTask(arg3.obj);
            return 1;
        }

        private void handleQueueTask(Task arg4) {
            this.updateListenerMap();
            Iterator v0 = this.mRecordMap.values().iterator();
            while(v0.hasNext()) {
                Object v1 = v0.next();
                ((ListenerRecord)v1).taskQueue.add(arg4);
                this.processListenerQueue(((ListenerRecord)v1));
            }
        }

        private void handleRetryListenerQueue(ComponentName arg2) {
            Object v2 = this.mRecordMap.get(arg2);
            if(v2 != null) {
                this.processListenerQueue(((ListenerRecord)v2));
            }
        }

        private void handleServiceConnected(ComponentName arg2, IBinder arg3) {
            Object v2 = this.mRecordMap.get(arg2);
            if(v2 != null) {
                ((ListenerRecord)v2).service = Stub.asInterface(arg3);
                ((ListenerRecord)v2).retryCount = 0;
                this.processListenerQueue(((ListenerRecord)v2));
            }
        }

        private void handleServiceDisconnected(ComponentName arg2) {
            Object v2 = this.mRecordMap.get(arg2);
            if(v2 != null) {
                this.ensureServiceUnbound(((ListenerRecord)v2));
            }
        }

        public void onServiceConnected(ComponentName arg4, IBinder arg5) {
            if(Log.isLoggable("NotifManCompat", 3)) {
                Log.d("NotifManCompat", "Connected to service " + arg4);
            }

            this.mHandler.obtainMessage(1, new ServiceConnectedEvent(arg4, arg5)).sendToTarget();
        }

        public void onServiceDisconnected(ComponentName arg4) {
            if(Log.isLoggable("NotifManCompat", 3)) {
                Log.d("NotifManCompat", "Disconnected from service " + arg4);
            }

            this.mHandler.obtainMessage(2, arg4).sendToTarget();
        }

        private void processListenerQueue(ListenerRecord arg6) {
            int v1 = 3;
            if(Log.isLoggable("NotifManCompat", v1)) {
                Log.d("NotifManCompat", "Processing component " + arg6.componentName + ", " + arg6.taskQueue.size() + " queued tasks");
            }

            if(arg6.taskQueue.isEmpty()) {
                return;
            }

            if((this.ensureServiceBound(arg6)) && arg6.service != null) {
                while(true) {
                    Object v0 = arg6.taskQueue.peek();
                    if(v0 == null) {
                        break;
                    }

                    try {
                        if(Log.isLoggable("NotifManCompat", v1)) {
                            Log.d("NotifManCompat", "Sending task " + v0);
                        }

                        ((Task)v0).send(arg6.service);
                        arg6.taskQueue.remove();
                        continue;
                    }
                    catch(RemoteException v0_1) {
                        Log.w("NotifManCompat", "RemoteException communicating with " + arg6.componentName, ((Throwable)v0_1));
                        break;
                    }
                    catch(DeadObjectException ) {
                        if(!Log.isLoggable("NotifManCompat", v1)) {
                            break;
                        }

                        Log.d("NotifManCompat", "Remote service has died: " + arg6.componentName);
                        break;
                    }
                }

                if(!arg6.taskQueue.isEmpty()) {
                    this.scheduleListenerRetry(arg6);
                }

                return;
            }

            this.scheduleListenerRetry(arg6);
        }

        public void queueTask(Task arg3) {
            this.mHandler.obtainMessage(0, arg3).sendToTarget();
        }

        private void scheduleListenerRetry(ListenerRecord arg6) {
            int v2 = 3;
            if(this.mHandler.hasMessages(v2, arg6.componentName)) {
                return;
            }

            ++arg6.retryCount;
            if(arg6.retryCount > 6) {
                Log.w("NotifManCompat", "Giving up on delivering " + arg6.taskQueue.size() + " tasks to " + arg6.componentName + " after " + arg6.retryCount + " retries");
                arg6.taskQueue.clear();
                return;
            }

            int v0 = (1 << arg6.retryCount - 1) * 1000;
            if(Log.isLoggable("NotifManCompat", v2)) {
                Log.d("NotifManCompat", "Scheduling retry for " + v0 + " ms");
            }

            this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(v2, arg6.componentName), ((long)v0));
        }

        private void updateListenerMap() {
            Object v1_2;
            Set v0 = NotificationManagerCompat.getEnabledListenerPackages(this.mContext);
            if(v0.equals(this.mCachedEnabledPackages)) {
                return;
            }

            this.mCachedEnabledPackages = v0;
            List v1 = this.mContext.getPackageManager().queryIntentServices(new Intent().setAction("android.support.BIND_NOTIFICATION_SIDE_CHANNEL"), 0);
            HashSet v2 = new HashSet();
            Iterator v1_1 = v1.iterator();
            while(v1_1.hasNext()) {
                Object v3 = v1_1.next();
                if(!v0.contains(((ResolveInfo)v3).serviceInfo.packageName)) {
                    continue;
                }

                ComponentName v4 = new ComponentName(((ResolveInfo)v3).serviceInfo.packageName, ((ResolveInfo)v3).serviceInfo.name);
                if(((ResolveInfo)v3).serviceInfo.permission != null) {
                    Log.w("NotifManCompat", "Permission present on component " + v4 + ", not adding listener record.");
                    continue;
                }

                ((Set)v2).add(v4);
            }

            Iterator v0_1 = ((Set)v2).iterator();
            while(true) {
                int v3_1 = 3;
                if(!v0_1.hasNext()) {
                    break;
                }

                v1_2 = v0_1.next();
                if(this.mRecordMap.containsKey(v1_2)) {
                    continue;
                }

                if(Log.isLoggable("NotifManCompat", v3_1)) {
                    Log.d("NotifManCompat", "Adding listener record for " + v1_2);
                }

                this.mRecordMap.put(v1_2, new ListenerRecord(((ComponentName)v1_2)));
            }

            v0_1 = this.mRecordMap.entrySet().iterator();
            while(v0_1.hasNext()) {
                v1_2 = v0_1.next();
                if(((Set)v2).contains(((Map$Entry)v1_2).getKey())) {
                    continue;
                }

                if(Log.isLoggable("NotifManCompat", v3_1)) {
                    Log.d("NotifManCompat", "Removing listener record for " + ((Map$Entry)v1_2).getKey());
                }

                this.ensureServiceUnbound(((Map$Entry)v1_2).getValue());
                v0_1.remove();
            }
        }
    }

    interface Task {
        void send(INotificationSideChannel arg1) throws RemoteException;
    }

    public static final String ACTION_BIND_SIDE_CHANNEL = "android.support.BIND_NOTIFICATION_SIDE_CHANNEL";
    private static final String CHECK_OP_NO_THROW = "checkOpNoThrow";
    public static final String EXTRA_USE_SIDE_CHANNEL = "android.support.useSideChannel";
    public static final int IMPORTANCE_DEFAULT = 3;
    public static final int IMPORTANCE_HIGH = 4;
    public static final int IMPORTANCE_LOW = 2;
    public static final int IMPORTANCE_MAX = 5;
    public static final int IMPORTANCE_MIN = 1;
    public static final int IMPORTANCE_NONE = 0;
    public static final int IMPORTANCE_UNSPECIFIED = -1000;
    static final int MAX_SIDE_CHANNEL_SDK_VERSION = 19;
    private static final String OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION";
    private static final String SETTING_ENABLED_NOTIFICATION_LISTENERS = "enabled_notification_listeners";
    private static final int SIDE_CHANNEL_RETRY_BASE_INTERVAL_MS = 1000;
    private static final int SIDE_CHANNEL_RETRY_MAX_COUNT = 6;
    private static final String TAG = "NotifManCompat";
    private final Context mContext;
    private final NotificationManager mNotificationManager;
    @GuardedBy(value="sEnabledNotificationListenersLock") private static Set sEnabledNotificationListenerPackages;
    @GuardedBy(value="sEnabledNotificationListenersLock") private static String sEnabledNotificationListeners;
    private static final Object sEnabledNotificationListenersLock;
    private static final Object sLock;
    @GuardedBy(value="sLock") private static SideChannelManager sSideChannelManager;

    static {
        NotificationManagerCompat.sEnabledNotificationListenersLock = new Object();
        NotificationManagerCompat.sEnabledNotificationListenerPackages = new HashSet();
        NotificationManagerCompat.sLock = new Object();
    }

    private NotificationManagerCompat(Context arg2) {
        super();
        this.mContext = arg2;
        this.mNotificationManager = this.mContext.getSystemService("notification");
    }

    public boolean areNotificationsEnabled() {
        if(Build$VERSION.SDK_INT >= 24) {
            return this.mNotificationManager.areNotificationsEnabled();
        }

        boolean v2 = true;
        if(Build$VERSION.SDK_INT < 19) {
            return 1;
        }

        Object v0 = this.mContext.getSystemService("appops");
        ApplicationInfo v1 = this.mContext.getApplicationInfo();
        String v3 = this.mContext.getApplicationContext().getPackageName();
        int v1_1 = v1.uid;
        try {
            Class v4 = Class.forName(AppOpsManager.class.getName());
            if(v4.getMethod("checkOpNoThrow", new Class[]{Integer.TYPE, Integer.TYPE, String.class}).invoke(v0, new Object[]{Integer.valueOf(v4.getDeclaredField("OP_POST_NOTIFICATION").get(Integer.class).intValue()), Integer.valueOf(v1_1), v3}).intValue() != 0) {
                return false;
            }
        }
        catch(RuntimeException ) {
            return 1;
        }

        return v2;
    }

    public void cancel(int arg2) {
        this.cancel(null, arg2);
    }

    public void cancel(String arg3, int arg4) {
        this.mNotificationManager.cancel(arg3, arg4);
        if(Build$VERSION.SDK_INT <= 19) {
            this.pushSideChannelQueue(new CancelTask(this.mContext.getPackageName(), arg4, arg3));
        }
    }

    public void cancelAll() {
        this.mNotificationManager.cancelAll();
        if(Build$VERSION.SDK_INT <= 19) {
            this.pushSideChannelQueue(new CancelTask(this.mContext.getPackageName()));
        }
    }

    public static NotificationManagerCompat from(Context arg1) {
        return new NotificationManagerCompat(arg1);
    }

    public static Set getEnabledListenerPackages(Context arg6) {
        String v6 = Settings$Secure.getString(arg6.getContentResolver(), "enabled_notification_listeners");
        Object v0 = NotificationManagerCompat.sEnabledNotificationListenersLock;
        __monitor_enter(v0);
        if(v6 != null) {
            try {
                if(!v6.equals(NotificationManagerCompat.sEnabledNotificationListeners)) {
                    String[] v1 = v6.split(":");
                    HashSet v2 = new HashSet(v1.length);
                    int v3 = v1.length;
                    int v4;
                    for(v4 = 0; v4 < v3; ++v4) {
                        ComponentName v5 = ComponentName.unflattenFromString(v1[v4]);
                        if(v5 != null) {
                            ((Set)v2).add(v5.getPackageName());
                        }
                    }

                    NotificationManagerCompat.sEnabledNotificationListenerPackages = ((Set)v2);
                    NotificationManagerCompat.sEnabledNotificationListeners = v6;
                }

            label_29:
                __monitor_exit(v0);
                return NotificationManagerCompat.sEnabledNotificationListenerPackages;
            label_32:
                __monitor_exit(v0);
                goto label_33;
            }
            catch(Throwable v6_1) {
                goto label_32;
            }
        }

        goto label_29;
    label_33:
        throw v6_1;
    }

    public int getImportance() {
        if(Build$VERSION.SDK_INT >= 24) {
            return this.mNotificationManager.getImportance();
        }

        return -1000;
    }

    public void notify(int arg2, Notification arg3) {
        this.notify(null, arg2, arg3);
    }

    public void notify(String arg3, int arg4, Notification arg5) {
        if(NotificationManagerCompat.useSideChannelForNotification(arg5)) {
            this.pushSideChannelQueue(new NotifyTask(this.mContext.getPackageName(), arg4, arg3, arg5));
            this.mNotificationManager.cancel(arg3, arg4);
        }
        else {
            this.mNotificationManager.notify(arg3, arg4, arg5);
        }
    }

    private void pushSideChannelQueue(Task arg4) {
        Object v0 = NotificationManagerCompat.sLock;
        __monitor_enter(v0);
        try {
            if(NotificationManagerCompat.sSideChannelManager == null) {
                NotificationManagerCompat.sSideChannelManager = new SideChannelManager(this.mContext.getApplicationContext());
            }

            NotificationManagerCompat.sSideChannelManager.queueTask(arg4);
            __monitor_exit(v0);
            return;
        label_14:
            __monitor_exit(v0);
        }
        catch(Throwable v4) {
            goto label_14;
        }

        throw v4;
    }

    private static boolean useSideChannelForNotification(Notification arg1) {
        Bundle v1 = NotificationCompat.getExtras(arg1);
        boolean v1_1 = v1 == null || !v1.getBoolean("android.support.useSideChannel") ? false : true;
        return v1_1;
    }
}

