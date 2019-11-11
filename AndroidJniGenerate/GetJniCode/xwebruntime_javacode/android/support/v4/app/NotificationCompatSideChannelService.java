package android.support.v4.app;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Build$VERSION;
import android.os.IBinder;
import android.os.RemoteException;

public abstract class NotificationCompatSideChannelService extends Service {
    class NotificationSideChannelStub extends Stub {
        NotificationSideChannelStub(NotificationCompatSideChannelService arg1) {
            NotificationCompatSideChannelService.this = arg1;
            super();
        }

        public void cancel(String arg4, int arg5, String arg6) throws RemoteException {
            NotificationCompatSideChannelService.this.checkPermission(NotificationSideChannelStub.getCallingUid(), arg4);
            long v0 = NotificationSideChannelStub.clearCallingIdentity();
            try {
                NotificationCompatSideChannelService.this.cancel(arg4, arg5, arg6);
            }
            catch(Throwable v4) {
                NotificationSideChannelStub.restoreCallingIdentity(v0);
                throw v4;
            }

            NotificationSideChannelStub.restoreCallingIdentity(v0);
        }

        public void cancelAll(String arg4) {
            NotificationCompatSideChannelService.this.checkPermission(NotificationSideChannelStub.getCallingUid(), arg4);
            long v0 = NotificationSideChannelStub.clearCallingIdentity();
            try {
                NotificationCompatSideChannelService.this.cancelAll(arg4);
            }
            catch(Throwable v4) {
                NotificationSideChannelStub.restoreCallingIdentity(v0);
                throw v4;
            }

            NotificationSideChannelStub.restoreCallingIdentity(v0);
        }

        public void notify(String arg4, int arg5, String arg6, Notification arg7) throws RemoteException {
            NotificationCompatSideChannelService.this.checkPermission(NotificationSideChannelStub.getCallingUid(), arg4);
            long v0 = NotificationSideChannelStub.clearCallingIdentity();
            try {
                NotificationCompatSideChannelService.this.notify(arg4, arg5, arg6, arg7);
            }
            catch(Throwable v4) {
                NotificationSideChannelStub.restoreCallingIdentity(v0);
                throw v4;
            }

            NotificationSideChannelStub.restoreCallingIdentity(v0);
        }
    }

    public NotificationCompatSideChannelService() {
        super();
    }

    public abstract void cancel(String arg1, int arg2, String arg3);

    public abstract void cancelAll(String arg1);

    void checkPermission(int arg5, String arg6) {
        String[] v0 = this.getPackageManager().getPackagesForUid(arg5);
        int v1 = v0.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            if(v0[v2].equals(arg6)) {
                return;
            }
        }

        StringBuilder v1_1 = new StringBuilder();
        v1_1.append("NotificationSideChannelService: Uid ");
        v1_1.append(arg5);
        v1_1.append(" is not authorized for package ");
        v1_1.append(arg6);
        throw new SecurityException(v1_1.toString());
    }

    public abstract void notify(String arg1, int arg2, String arg3, Notification arg4);

    public IBinder onBind(Intent arg3) {
        IBinder v0 = null;
        if(arg3.getAction().equals("android.support.BIND_NOTIFICATION_SIDE_CHANNEL")) {
            if(Build$VERSION.SDK_INT > 19) {
                return v0;
            }

            return new NotificationSideChannelStub(this);
        }

        return v0;
    }
}

