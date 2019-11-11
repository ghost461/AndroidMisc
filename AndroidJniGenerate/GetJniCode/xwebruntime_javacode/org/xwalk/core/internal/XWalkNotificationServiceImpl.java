package org.xwalk.core.internal;

import android.app.Notification$Builder;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Build$VERSION;
import android.util.AndroidRuntimeException;
import java.util.HashMap;
import java.util.Iterator;

public class XWalkNotificationServiceImpl implements XWalkNotificationService {
    class WebNotification {
        public Notification$Builder mBuilder;
        public Integer mMessageNum;
        public Integer mNotificationId;
        public String mReplaceId;

        WebNotification(XWalkNotificationServiceImpl arg1) {
            XWalkNotificationServiceImpl.this = arg1;
            super();
            this.mMessageNum = Integer.valueOf(1);
        }
    }

    private static final String TAG = "XWalkNotificationServiceImpl";
    private static final String XWALK_ACTION_CLICK_NOTIFICATION_SUFFIX = ".notification.click";
    private static final String XWALK_ACTION_CLOSE_NOTIFICATION_SUFFIX = ".notification.close";
    private static final String XWALK_INTENT_CATEGORY_NOTIFICATION_PREFIX = "notification_";
    private static final String XWALK_INTENT_EXTRA_KEY_NOTIFICATION_ID = "xwalk.NOTIFICATION_ID";
    private XWalkContentsClientBridge mBridge;
    private Context mContext;
    private HashMap mExistNotificationIds;
    private HashMap mExistReplaceIds;
    private BroadcastReceiver mNotificationCloseReceiver;
    private NotificationManager mNotificationManager;
    private XWalkViewInternal mView;

    public XWalkNotificationServiceImpl(Context arg1, XWalkViewInternal arg2) {
        super();
        this.mContext = arg1;
        this.mView = arg2;
        this.mNotificationManager = this.mContext.getSystemService("notification");
        this.mNotificationCloseReceiver = new BroadcastReceiver() {
            public void onReceive(Context arg1, Intent arg2) {
                XWalkNotificationServiceImpl.access$000(XWalkNotificationServiceImpl.this).onNewIntent(arg2);
            }
        };
        this.mExistNotificationIds = new HashMap();
        this.mExistReplaceIds = new HashMap();
    }

    static XWalkViewInternal access$000(XWalkNotificationServiceImpl arg0) {
        return arg0.mView;
    }

    public void cancelNotification(int arg2) {
        this.mNotificationManager.cancel(arg2);
        this.onNotificationClose(arg2, false);
    }

    public void doShowNotification(int arg2, Notification arg3) {
        this.mNotificationManager.notify(arg2, arg3);
    }

    private static String getCategoryFromNotificationId(int arg2) {
        return "notification_" + arg2;
    }

    public Bitmap getNotificationIcon(Bitmap arg7) {
        if(arg7 == null) {
            return null;
        }

        int v0 = arg7.getWidth();
        int v1 = arg7.getHeight();
        if(v0 != 0) {
            if(v1 == 0) {
            }
            else {
                int v2 = this.mContext.getResources().getDimensionPixelSize(0x1050005);
                int v3 = this.mContext.getResources().getDimensionPixelSize(0x1050006);
                if(v0 > v2 && v1 > v3) {
                    int v4 = v0 * v3;
                    int v5 = v1 * v2;
                    if(v4 > v5) {
                        v3 = v5 / v0;
                    }
                    else {
                        v2 = v4 / v1;
                    }
                }

                return Bitmap.createScaledBitmap(arg7, v2, v3, true);
            }
        }

        return arg7;
    }

    public boolean maybeHandleIntent(Intent arg6) {
        if(arg6.getAction() == null) {
            return 0;
        }

        int v0 = arg6.getIntExtra("xwalk.NOTIFICATION_ID", -1);
        if(v0 <= 0) {
            return 0;
        }

        String v2 = arg6.getAction();
        StringBuilder v3 = new StringBuilder();
        v3.append(this.mView.getContext().getPackageName());
        v3.append(".notification.close");
        if(v2.equals(v3.toString())) {
            this.onNotificationClose(v0, true);
            return 1;
        }

        String v6 = arg6.getAction();
        StringBuilder v2_1 = new StringBuilder();
        v2_1.append(this.mView.getContext().getPackageName());
        v2_1.append(".notification.click");
        if(v6.equals(v2_1.toString())) {
            this.onNotificationClick(v0);
            return 1;
        }

        return 0;
    }

    private void notificationChanged() {
        if(this.mExistNotificationIds.isEmpty()) {
            Log.i("XWalkNotificationServiceImpl", "notifications are all cleared,unregister broadcast receiver for close pending intent");
            this.unregisterReceiver();
        }
        else {
            this.registerReceiver();
        }
    }

    public void onNotificationClick(int arg4) {
        Object v0 = this.mExistNotificationIds.get(Integer.valueOf(arg4));
        if(v0 == null) {
            return;
        }

        this.mExistNotificationIds.remove(Integer.valueOf(arg4));
        this.mExistReplaceIds.remove(((WebNotification)v0).mReplaceId);
        this.notificationChanged();
        if(this.mBridge != null) {
            this.mBridge.notificationClicked(arg4);
        }
    }

    public void onNotificationClose(int arg4, boolean arg5) {
        Object v0 = this.mExistNotificationIds.get(Integer.valueOf(arg4));
        if(v0 == null) {
            return;
        }

        this.mExistNotificationIds.remove(Integer.valueOf(arg4));
        this.mExistReplaceIds.remove(((WebNotification)v0).mReplaceId);
        this.notificationChanged();
        if(this.mBridge != null) {
            this.mBridge.notificationClosed(arg4, arg5);
        }
    }

    public void onNotificationShown(int arg3) {
        if(this.mExistNotificationIds.get(Integer.valueOf(arg3)) == null) {
            return;
        }

        if(this.mBridge != null) {
            this.mBridge.notificationDisplayed(arg3);
        }
    }

    private void registerReceiver() {
        StringBuilder v1 = new StringBuilder();
        v1.append(this.mView.getContext().getPackageName());
        v1.append(".notification.close");
        IntentFilter v0 = new IntentFilter(v1.toString());
        Iterator v1_1 = this.mExistNotificationIds.keySet().iterator();
        while(v1_1.hasNext()) {
            v0.addCategory(XWalkNotificationServiceImpl.getCategoryFromNotificationId(v1_1.next().intValue()));
        }

        try {
            this.mView.getContext().registerReceiver(this.mNotificationCloseReceiver, v0);
        }
        catch(AndroidRuntimeException v0_1) {
            Log.w("XWalkNotificationServiceImpl", v0_1.getLocalizedMessage());
        }
    }

    public void setBridge(XWalkContentsClientBridge arg1) {
        this.mBridge = arg1;
    }

    public void showNotification(String arg5, String arg6, String arg7, Bitmap arg8, int arg9) {
        Notification$Builder v0;
        if((arg7.isEmpty()) || !this.mExistReplaceIds.containsKey(arg7)) {
            v0 = new Notification$Builder(this.mContext.getApplicationContext()).setAutoCancel(true);
            WebNotification v1_1 = new WebNotification(this);
            v1_1.mNotificationId = Integer.valueOf(arg9);
            v1_1.mReplaceId = arg7;
            v1_1.mBuilder = v0;
            this.mExistNotificationIds.put(Integer.valueOf(arg9), v1_1);
            if(!arg7.isEmpty()) {
                this.mExistReplaceIds.put(arg7, v1_1);
            }
        }
        else {
            Object v7 = this.mExistReplaceIds.get(arg7);
            arg9 = ((WebNotification)v7).mNotificationId.intValue();
            v0 = ((WebNotification)v7).mBuilder;
            Integer v1 = Integer.valueOf(((WebNotification)v7).mMessageNum.intValue() + 1);
            ((WebNotification)v7).mMessageNum = v1;
            v0.setNumber(v1.intValue());
        }

        v0.setContentTitle(((CharSequence)arg5));
        v0.setContentText(((CharSequence)arg6));
        int v5 = this.mContext.getApplicationInfo().icon;
        if(v5 == 0) {
            v5 = 0x1080093;
        }

        v0.setSmallIcon(v5);
        Bitmap v5_1 = this.getNotificationIcon(arg8);
        if(v5_1 != null) {
            v0.setLargeIcon(v5_1);
        }

        Context v5_2 = this.mView.getContext();
        arg6 = XWalkNotificationServiceImpl.getCategoryFromNotificationId(arg9);
        Intent v7_1 = new Intent(v5_2, v5_2.getClass());
        StringBuilder v8 = new StringBuilder();
        v8.append(v5_2.getPackageName());
        v8.append(".notification.click");
        v7_1 = v7_1.setAction(v8.toString()).putExtra("xwalk.NOTIFICATION_ID", arg9).setFlags(0x20100000).addCategory(arg6);
        StringBuilder v1_2 = new StringBuilder();
        v1_2.append(v5_2.getPackageName());
        v1_2.append(".notification.close");
        Intent v6 = new Intent(v1_2.toString()).putExtra("xwalk.NOTIFICATION_ID", arg9).addCategory(arg6);
        v0.setContentIntent(PendingIntent.getActivity(v5_2, 0, v7_1, 0x8000000));
        v0.setDeleteIntent(PendingIntent.getBroadcast(v5_2, 0, v6, 0x8000000));
        Notification v5_3 = Build$VERSION.SDK_INT >= 16 ? v0.build() : v0.getNotification();
        this.doShowNotification(arg9, v5_3);
        this.notificationChanged();
        this.onNotificationShown(arg9);
    }

    public void shutdown() {
        if(!this.mExistNotificationIds.isEmpty()) {
            this.unregisterReceiver();
        }

        this.mBridge = null;
    }

    private void unregisterReceiver() {
        this.mView.getContext().unregisterReceiver(this.mNotificationCloseReceiver);
    }
}

