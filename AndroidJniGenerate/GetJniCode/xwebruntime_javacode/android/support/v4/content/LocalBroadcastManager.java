package android.support.v4.content;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public final class LocalBroadcastManager {
    final class BroadcastRecord {
        final Intent intent;
        final ArrayList receivers;

        BroadcastRecord(Intent arg1, ArrayList arg2) {
            super();
            this.intent = arg1;
            this.receivers = arg2;
        }
    }

    final class ReceiverRecord {
        boolean broadcasting;
        boolean dead;
        final IntentFilter filter;
        final BroadcastReceiver receiver;

        ReceiverRecord(IntentFilter arg1, BroadcastReceiver arg2) {
            super();
            this.filter = arg1;
            this.receiver = arg2;
        }

        public String toString() {
            StringBuilder v0 = new StringBuilder(0x80);
            v0.append("Receiver{");
            v0.append(this.receiver);
            v0.append(" filter=");
            v0.append(this.filter);
            if(this.dead) {
                v0.append(" DEAD");
            }

            v0.append("}");
            return v0.toString();
        }
    }

    private static final boolean DEBUG = false;
    static final int MSG_EXEC_PENDING_BROADCASTS = 1;
    private static final String TAG = "LocalBroadcastManager";
    private final HashMap mActions;
    private final Context mAppContext;
    private final Handler mHandler;
    private static LocalBroadcastManager mInstance;
    private static final Object mLock;
    private final ArrayList mPendingBroadcasts;
    private final HashMap mReceivers;

    static {
        LocalBroadcastManager.mLock = new Object();
    }

    private LocalBroadcastManager(Context arg2) {
        super();
        this.mReceivers = new HashMap();
        this.mActions = new HashMap();
        this.mPendingBroadcasts = new ArrayList();
        this.mAppContext = arg2;
        this.mHandler = new Handler(arg2.getMainLooper()) {
            public void handleMessage(Message arg3) {
                if(arg3.what != 1) {
                    super.handleMessage(arg3);
                }
                else {
                    LocalBroadcastManager.access$000(LocalBroadcastManager.this);
                }
            }
        };
    }

    static void access$000(LocalBroadcastManager arg0) {
        arg0.executePendingBroadcasts();
    }

    private void executePendingBroadcasts() {
        int v2;
        BroadcastRecord[] v1_2;
    label_0:
        HashMap v0 = this.mReceivers;
        __monitor_enter(v0);
        try {
            int v1_1 = this.mPendingBroadcasts.size();
            if(v1_1 <= 0) {
                __monitor_exit(v0);
                return;
            }

            v1_2 = new BroadcastRecord[v1_1];
            this.mPendingBroadcasts.toArray(((Object[])v1_2));
            this.mPendingBroadcasts.clear();
            __monitor_exit(v0);
            v0 = null;
            v2 = 0;
        }
        catch(Throwable v1) {
            try {
            label_35:
                __monitor_exit(v0);
            }
            catch(Throwable v1) {
                goto label_35;
            }

            throw v1;
        }

        while(true) {
            if(v2 >= v1_2.length) {
                goto label_0;
            }

            BroadcastRecord v3 = v1_2[v2];
            int v4 = v3.receivers.size();
            int v5;
            for(v5 = 0; v5 < v4; ++v5) {
                Object v6 = v3.receivers.get(v5);
                if(!((ReceiverRecord)v6).dead) {
                    ((ReceiverRecord)v6).receiver.onReceive(this.mAppContext, v3.intent);
                }
            }

            ++v2;
        }
    }

    public static LocalBroadcastManager getInstance(Context arg2) {
        Object v0 = LocalBroadcastManager.mLock;
        __monitor_enter(v0);
        try {
            if(LocalBroadcastManager.mInstance == null) {
                LocalBroadcastManager.mInstance = new LocalBroadcastManager(arg2.getApplicationContext());
            }

            __monitor_exit(v0);
            return LocalBroadcastManager.mInstance;
        label_12:
            __monitor_exit(v0);
        }
        catch(Throwable v2) {
            goto label_12;
        }

        throw v2;
    }

    public void registerReceiver(BroadcastReceiver arg7, IntentFilter arg8) {
        ArrayList v4_1;
        ArrayList v2_1;
        HashMap v0 = this.mReceivers;
        __monitor_enter(v0);
        try {
            ReceiverRecord v1 = new ReceiverRecord(arg8, arg7);
            Object v2 = this.mReceivers.get(arg7);
            if(v2 == null) {
                v2_1 = new ArrayList(1);
                this.mReceivers.put(arg7, v2_1);
            }

            v2_1.add(v1);
            int v7_1;
            for(v7_1 = 0; v7_1 < arg8.countActions(); ++v7_1) {
                String v2_2 = arg8.getAction(v7_1);
                Object v4 = this.mActions.get(v2_2);
                if(v4 == null) {
                    v4_1 = new ArrayList(1);
                    this.mActions.put(v2_2, v4_1);
                }

                v4_1.add(v1);
            }

            __monitor_exit(v0);
            return;
        label_30:
            __monitor_exit(v0);
        }
        catch(Throwable v7) {
            goto label_30;
        }

        throw v7;
    }

    public boolean sendBroadcast(Intent arg24) {
        Throwable v2_1;
        String v4_2;
        int v4_1;
        ArrayList v11_1;
        Uri v21;
        String v20;
        String v17;
        Object v19;
        int v18;
        LocalBroadcastManager v1 = this;
        Intent v2 = arg24;
        HashMap v3 = v1.mReceivers;
        __monitor_enter(v3);
        try {
            String v11 = arg24.getAction();
            String v12 = v2.resolveTypeIfNeeded(v1.mAppContext.getContentResolver());
            Uri v13 = arg24.getData();
            String v14 = arg24.getScheme();
            Set v15 = arg24.getCategories();
            int v16 = (arg24.getFlags() & 8) != 0 ? 1 : 0;
            if(v16 != 0) {
                Log.v("LocalBroadcastManager", "Resolving type " + v12 + " scheme " + v14 + " of intent " + v2);
            }

            Object v8 = v1.mActions.get(arg24.getAction());
            if(v8 != null) {
                if(v16 != 0) {
                    Log.v("LocalBroadcastManager", "Action list: " + v8);
                }

                ArrayList v6 = null;
                int v7 = 0;
                while(v7 < ((ArrayList)v8).size()) {
                    Object v5_1 = ((ArrayList)v8).get(v7);
                    if(v16 != 0) {
                        Log.v("LocalBroadcastManager", "Matching against filter " + ((ReceiverRecord)v5_1).filter);
                    }

                    if(((ReceiverRecord)v5_1).broadcasting) {
                        if(v16 != 0) {
                            Log.v("LocalBroadcastManager", "  Filter\'s target already added");
                        }

                        v18 = v7;
                        v19 = v8;
                        v17 = v11;
                        v20 = v12;
                        v21 = v13;
                        v11_1 = v6;
                        goto label_132;
                    }
                    else {
                        IntentFilter v4 = ((ReceiverRecord)v5_1).filter;
                        Object v9_1 = v5_1;
                        String v5_2 = v11;
                        v17 = v11;
                        v11_1 = v6;
                        String v6_1 = v12;
                        v18 = v7;
                        v19 = v8;
                        v20 = v12;
                        v21 = v13;
                        Object v12_1 = v9_1;
                        v4_1 = v4.match(v5_2, v6_1, v14, v13, v15, "LocalBroadcastManager");
                        if(v4_1 >= 0) {
                            if(v16 != 0) {
                                Log.v("LocalBroadcastManager", "  Filter matched!  match=0x" + Integer.toHexString(v4_1));
                            }

                            v6 = v11_1 == null ? new ArrayList() : v11_1;
                            v6.add(v12_1);
                            ((ReceiverRecord)v12_1).broadcasting = true;
                            goto label_133;
                        }

                        if(v16 != 0) {
                            switch(v4_1) {
                                case -4: {
                                    v4_2 = "category";
                                    break;
                                }
                                case -3: {
                                    v4_2 = "action";
                                    break;
                                }
                                case -2: {
                                    v4_2 = "data";
                                    break;
                                }
                                case -1: {
                                    v4_2 = "type";
                                    break;
                                }
                                default: {
                                    v4_2 = "unknown reason";
                                    break;
                                }
                            }

                            Log.v("LocalBroadcastManager", "  Filter did not match: " + v4_2);
                        }

                    label_132:
                        v6 = v11_1;
                    }

                label_133:
                    v7 = v18 + 1;
                    v11 = v17;
                    v8 = v19;
                    v12 = v20;
                    v13 = v21;
                }

                v11_1 = v6;
                if(v11_1 == null) {
                    goto label_161;
                }

                for(v4_1 = 0; v4_1 < v11_1.size(); ++v4_1) {
                    v11_1.get(v4_1).broadcasting = false;
                }

                v1.mPendingBroadcasts.add(new BroadcastRecord(v2, v11_1));
                if(!v1.mHandler.hasMessages(1)) {
                    v1.mHandler.sendEmptyMessage(1);
                }

                __monitor_exit(v3);
                return 1;
            }

        label_161:
            __monitor_exit(v3);
            return 0;
        label_165:
            v2_1 = v0;
            __monitor_exit(v3);
        }
        catch(Throwable v0) {
            goto label_165;
        }

        throw v2_1;
    }

    public void sendBroadcastSync(Intent arg1) {
        if(this.sendBroadcast(arg1)) {
            this.executePendingBroadcasts();
        }
    }

    public void unregisterReceiver(BroadcastReceiver arg12) {
        HashMap v0 = this.mReceivers;
        __monitor_enter(v0);
        try {
            Object v1 = this.mReceivers.remove(arg12);
            if(v1 == null) {
                __monitor_exit(v0);
                return;
            }

            int v2;
            for(v2 = ((ArrayList)v1).size() - 1; v2 >= 0; --v2) {
                Object v4 = ((ArrayList)v1).get(v2);
                ((ReceiverRecord)v4).dead = true;
                int v5;
                for(v5 = 0; v5 < ((ReceiverRecord)v4).filter.countActions(); ++v5) {
                    String v6 = ((ReceiverRecord)v4).filter.getAction(v5);
                    Object v7 = this.mActions.get(v6);
                    if(v7 != null) {
                        int v8;
                        for(v8 = ((ArrayList)v7).size() - 1; v8 >= 0; --v8) {
                            Object v9 = ((ArrayList)v7).get(v8);
                            if(((ReceiverRecord)v9).receiver == arg12) {
                                ((ReceiverRecord)v9).dead = true;
                                ((ArrayList)v7).remove(v8);
                            }
                        }

                        if(((ArrayList)v7).size() > 0) {
                            goto label_36;
                        }

                        this.mActions.remove(v6);
                    }

                label_36:
                }
            }

            __monitor_exit(v0);
            return;
        label_43:
            __monitor_exit(v0);
        }
        catch(Throwable v12) {
            goto label_43;
        }

        throw v12;
    }
}

