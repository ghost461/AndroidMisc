package org.xwalk.core.internal.extension.api.wifidirect;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pGroup;
import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager$ActionListener;
import android.net.wifi.p2p.WifiP2pManager$Channel;
import android.net.wifi.p2p.WifiP2pManager$ConnectionInfoListener;
import android.net.wifi.p2p.WifiP2pManager$GroupInfoListener;
import android.net.wifi.p2p.WifiP2pManager$PeerListListener;
import android.net.wifi.p2p.WifiP2pManager;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xwalk.core.internal.Log;
import org.xwalk.core.internal.extension.XWalkExtensionWithActivityStateListener;

public class WifiDirect extends XWalkExtensionWithActivityStateListener {
    private static final String CMD_CANCEL_CONNECT = "cancelConnect";
    private static final String CMD_CONNECT = "connect";
    private static final String CMD_DISCONNECT = "disconnect";
    private static final String CMD_DISCOVER_PEERS = "discoverPeers";
    private static final String CMD_GET_CONNECTION_INFO = "getConnectionInfo";
    private static final String CMD_GET_PEERS = "getPeers";
    private static final String CMD_INIT = "init";
    private static final String ERROR_BUSY = "WifiP2pManager.BUSY";
    private static final String ERROR_DEFAULT = "WifiP2pManager.ERROR";
    private static final String ERROR_GENERAL_ERROR_MSG_STEM = "Android WiFi Direct error: ";
    private static final String ERROR_INVALID_CALL_NO_DATA_MSG = "Error: Invalid connect API call - data === null";
    private static final String ERROR_NO_SERVICE_REQUESTS = "WifiP2pManager.NO_SERVICE_REQUESTS";
    private static final String ERROR_P2P_UNSUPPORTED = "WifiP2pManager.P2P_UNSUPPORTED";
    private static final String ERROR_REASON_CODE_STEM = "WifiP2pManager reasonCode: ";
    private static final String EVENT_CONNECTION_CHANGED = "connectionchanged";
    private static final String EVENT_DISCOVERY_STOPPED = "discoverystoppedevent";
    private static final String EVENT_PEERS_CHANGED = "peerschanged";
    private static final String EVENT_THIS_DEVICE_CHANGED = "thisdevicechanged";
    private static final String EVENT_WIFI_STATE_CHANGED = "wifistatechanged";
    public static final String JS_API_PATH = "jsapi/wifidirect_api.js";
    private static final String NAME = "xwalk.experimental.wifidirect";
    private static final String STATE_AVAILABLE = "available";
    private static final String STATE_CONNECTED = "connected";
    private static final String STATE_FAILED = "failed";
    private static final String STATE_INVITED = "invited";
    private static final String STATE_UNAVAILABLE = "unavailable";
    private static final String TAG = "WifiDirect";
    private static final String TAG_ASYNC_CALL_ID = "asyncCallId";
    private static final String TAG_CMD = "cmd";
    private static final String TAG_CONNECTED = "connected";
    private static final String TAG_DATA = "data";
    private static final String TAG_ENABLED = "enabled";
    private static final String TAG_ERROR = "error";
    private static final String TAG_ERROR_CODE = "errorCode";
    private static final String TAG_EVENT_NAME = "eventName";
    private static final String TAG_FALSE = "false";
    private static final String TAG_GROUP_FORMED = "groupFormed";
    private static final String TAG_IS_SERVER = "isServer";
    private static final String TAG_MAC = "MAC";
    private static final String TAG_MESSAGE = "message";
    private static final String TAG_NAME = "name";
    private static final String TAG_SERVER_IP = "serverIP";
    private static final String TAG_STATUS = "status";
    private static final String TAG_TRUE = "true";
    private static final String TAG_TYPE = "type";
    private Activity mActivity;
    private WifiP2pManager$Channel mChannel;
    private IntentFilter mIntentFilter;
    private WifiP2pManager mManager;
    private BroadcastReceiver mReceiver;
    private boolean mReceiverRegistered;

    public WifiDirect(String arg2, Activity arg3) {
        super("xwalk.experimental.wifidirect", arg2, arg3);
        this.mManager = null;
        this.mChannel = null;
        this.mReceiver = null;
        this.mReceiverRegistered = false;
        this.mActivity = null;
        this.mActivity = arg3;
    }

    static WifiP2pManager$Channel access$000(WifiDirect arg0) {
        return arg0.mChannel;
    }

    static WifiP2pManager$ActionListener access$100(WifiDirect arg0, int arg1, JSONObject arg2) {
        return arg0.createCallActionListener(arg1, arg2);
    }

    static WifiP2pManager access$200(WifiDirect arg0) {
        return arg0.mManager;
    }

    static JSONArray access$300(WifiDirect arg0, WifiP2pDeviceList arg1) throws JSONException {
        return arg0.convertListToJSON(arg1);
    }

    static JSONObject access$400(WifiDirect arg0, JSONObject arg1, String arg2) throws JSONException {
        return arg0.setEventData(arg1, arg2);
    }

    static void access$500(WifiDirect arg0, JSONObject arg1, WifiP2pDevice arg2) throws JSONException {
        arg0.convertDeviceToJSON(arg1, arg2);
    }

    private void convertDeviceToJSON(JSONObject arg3, WifiP2pDevice arg4) throws JSONException {
        arg3.put("MAC", arg4.deviceAddress);
        arg3.put("name", arg4.deviceName);
        arg3.put("type", arg4.primaryDeviceType);
        arg3.put("status", this.convertStateToString(arg4.status));
    }

    private JSONArray convertListToJSON(WifiP2pDeviceList arg4) throws JSONException {
        JSONArray v0 = new JSONArray();
        Iterator v4 = arg4.getDeviceList().iterator();
        while(v4.hasNext()) {
            Object v1 = v4.next();
            JSONObject v2 = new JSONObject();
            this.convertDeviceToJSON(v2, ((WifiP2pDevice)v1));
            v0.put(v2);
        }

        return v0;
    }

    private String convertReasonCodeToString(int arg3) {
        switch(arg3) {
            case 0: {
                return "WifiP2pManager.ERROR";
            }
            case 1: {
                return "WifiP2pManager.P2P_UNSUPPORTED";
            }
            case 2: {
                return "WifiP2pManager.BUSY";
            }
            case 3: {
                return "WifiP2pManager.NO_SERVICE_REQUESTS";
            }
        }

        return "WifiP2pManager reasonCode: " + arg3;
    }

    private String convertStateToString(int arg1) {
        switch(arg1) {
            case 0: {
                return "connected";
            }
            case 1: {
                return "invited";
            }
            case 2: {
                return "failed";
            }
            case 3: {
                return "available";
            }
            case 4: {
                return "unavailable";
            }
        }

        return "";
    }

    private WifiP2pManager$ActionListener createCallActionListener(int arg2, JSONObject arg3) {
        return new WifiP2pManager$ActionListener(arg3, arg2) {
            public void onFailure(int arg4) {
                WifiDirect.this.setError(this.val$jsonOutput, "", arg4);
                WifiDirect.this.postMessage(this.val$instanceID, this.val$jsonOutput.toString());
            }

            public void onSuccess() {
                try {
                    this.val$jsonOutput.put("data", true);
                }
                catch(JSONException v0) {
                    WifiDirect.this.printErrorMessage(v0);
                }

                WifiDirect.this.postMessage(this.val$instanceID, this.val$jsonOutput.toString());
            }
        };
    }

    private void disconnect(int arg4, JSONObject arg5) {
        if(this.mManager != null && this.mChannel != null) {
            this.mManager.requestGroupInfo(this.mChannel, new WifiP2pManager$GroupInfoListener(arg4, arg5) {
                public void onGroupInfoAvailable(WifiP2pGroup arg5) {
                    if(arg5 != null) {
                        WifiDirect.this.mManager.removeGroup(WifiDirect.this.mChannel, WifiDirect.this.createCallActionListener(this.val$instanceID, this.val$jsonOutput));
                    }
                }
            });
        }
    }

    private void handleMessage(int arg5, String arg6) {
        try {
            JSONObject v0 = new JSONObject(arg6);
            arg6 = v0.getString("cmd");
            String v1 = v0.getString("asyncCallId");
            JSONObject v2 = new JSONObject();
            v2.put("asyncCallId", v1);
            if(arg6.equals("discoverPeers")) {
                this.mManager.discoverPeers(this.mChannel, this.createCallActionListener(arg5, v2));
                return;
            }

            if(arg6.equals("getPeers")) {
                this.mManager.requestPeers(this.mChannel, new WifiP2pManager$PeerListListener(v2, arg5) {
                    public void onPeersAvailable(WifiP2pDeviceList arg4) {
                        try {
                            this.val$jsonOutput.put("data", WifiDirect.this.convertListToJSON(arg4));
                        }
                        catch(JSONException v4) {
                            WifiDirect.this.printErrorMessage(v4);
                        }

                        WifiDirect.this.postMessage(this.val$instanceID, this.val$jsonOutput.toString());
                    }
                });
                return;
            }

            if(arg6.equals("init")) {
                v2.put("data", this.init());
                this.postMessage(arg5, v2.toString());
                return;
            }

            if(arg6.equals("getConnectionInfo")) {
                this.mManager.requestConnectionInfo(this.mChannel, new WifiP2pManager$ConnectionInfoListener(v2, arg5) {
                    public void onConnectionInfoAvailable(WifiP2pInfo arg5) {
                        try {
                            JSONObject v0 = new JSONObject();
                            this.val$jsonOutput.put("data", v0);
                            v0.put("groupFormed", arg5.groupFormed);
                            if(arg5.groupFormed) {
                                v0.put("isServer", arg5.isGroupOwner);
                                String v1 = "serverIP";
                                String v5_1 = arg5.isGroupOwner ? "" : arg5.groupOwnerAddress.toString().replace("/", "");
                                v0.put(v1, v5_1);
                            }

                            WifiDirect.this.postMessage(this.val$instanceID, this.val$jsonOutput.toString());
                        }
                        catch(JSONException v5) {
                            WifiDirect.this.printErrorMessage(v5);
                        }
                    }
                });
                return;
            }

            if(arg6.equals("connect")) {
                JSONObject v6 = v0.getJSONObject("data");
                if(v6 == null) {
                    this.setError(v2, "Error: Invalid connect API call - data === null", 0);
                    this.postMessage(arg5, v2.toString());
                    return;
                }

                WifiP2pConfig v1_1 = new WifiP2pConfig();
                v1_1.deviceAddress = v6.getString("MAC");
                v1_1.wps.setup = 0;
                this.mManager.connect(this.mChannel, v1_1, this.createCallActionListener(arg5, v2));
                return;
            }

            if(arg6.equals("cancelConnect")) {
                this.mManager.cancelConnect(this.mChannel, this.createCallActionListener(arg5, v2));
                return;
            }

            if(!arg6.equals("disconnect")) {
                return;
            }

            this.disconnect(arg5, v2);
        }
        catch(JSONException v5) {
            this.printErrorMessage(v5);
        }
    }

    private boolean init() {
        if(this.mActivity == null) {
            return 0;
        }

        this.mManager = this.mActivity.getSystemService("wifip2p");
        this.mChannel = this.mManager.initialize(this.mActivity, this.mActivity.getMainLooper(), null);
        this.mReceiver = new BroadcastReceiver() {
            public void onReceive(Context arg5, Intent arg6) {
                String v5 = arg6.getAction();
                JSONObject v0 = new JSONObject();
                try {
                    int v2 = -1;
                    if("android.net.wifi.p2p.STATE_CHANGED".equals(v5)) {
                        JSONObject v5_2 = WifiDirect.this.setEventData(v0, "wifistatechanged");
                        String v1 = "enabled";
                        String v6 = arg6.getIntExtra("wifi_p2p_state", v2) == 2 ? "true" : "false";
                        v5_2.put(v1, v6);
                    }
                    else {
                        if("android.net.wifi.p2p.PEERS_CHANGED".equals(v5)) {
                            WifiDirect.this.setEventData(v0, "peerschanged");
                            goto label_61;
                        }

                        if("android.net.wifi.p2p.CONNECTION_STATE_CHANGE".equals(v5)) {
                            WifiDirect.this.setEventData(v0, "connectionchanged").put("connected", arg6.getParcelableExtra("networkInfo").isConnected());
                            goto label_61;
                        }

                        if("android.net.wifi.p2p.THIS_DEVICE_CHANGED".equals(v5)) {
                            WifiDirect.this.convertDeviceToJSON(WifiDirect.this.setEventData(v0, "thisdevicechanged"), arg6.getParcelableExtra("wifiP2pDevice"));
                            goto label_61;
                        }

                        if(!"android.net.wifi.p2p.DISCOVERY_STATE_CHANGE".equals(v5)) {
                            goto label_61;
                        }

                        if(arg6.getIntExtra("discoveryState", v2) != 1) {
                            return;
                        }

                        WifiDirect.this.setEventData(v0, "discoverystoppedevent");
                    }

                label_61:
                    WifiDirect.this.broadcastMessage(v0.toString());
                }
                catch(JSONException v5_1) {
                    WifiDirect.this.printErrorMessage(v5_1);
                }
            }
        };
        this.mIntentFilter = new IntentFilter();
        this.mIntentFilter.addAction("android.net.wifi.p2p.STATE_CHANGED");
        this.mIntentFilter.addAction("android.net.wifi.p2p.PEERS_CHANGED");
        this.mIntentFilter.addAction("android.net.wifi.p2p.CONNECTION_STATE_CHANGE");
        this.mIntentFilter.addAction("android.net.wifi.p2p.THIS_DEVICE_CHANGED");
        this.mIntentFilter.addAction("android.net.wifi.p2p.DISCOVERY_STATE_CHANGE");
        if(!this.mReceiverRegistered) {
            this.mActivity.registerReceiver(this.mReceiver, this.mIntentFilter);
            this.mReceiverRegistered = true;
        }

        return 1;
    }

    public void onActivityStateChange(Activity arg2, int arg3) {
        if(this.mReceiver == null) {
            return;
        }

        switch(arg3) {
            case 3: {
                this.mActivity = arg2;
                if(this.mReceiverRegistered) {
                    return;
                }

                this.mActivity.registerReceiver(this.mReceiver, this.mIntentFilter);
                this.mReceiverRegistered = true;
                break;
            }
            case 4: {
                if(this.mReceiverRegistered) {
                    this.mActivity.unregisterReceiver(this.mReceiver);
                    this.mReceiverRegistered = false;
                }

                this.mActivity = null;
                break;
            }
            default: {
                break;
            }
        }
    }

    public void onMessage(int arg2, String arg3) {
        if(!arg3.isEmpty()) {
            this.handleMessage(arg2, arg3);
        }
    }

    public String onSyncMessage(int arg1, String arg2) {
        return null;
    }

    protected void printErrorMessage(JSONException arg2) {
        Log.e("WifiDirect", arg2.toString());
    }

    protected void setError(JSONObject arg4, String arg5, int arg6) {
        JSONObject v0 = new JSONObject();
        JSONObject v1 = new JSONObject();
        try {
            arg4.put("data", v0);
            String v4_1 = "message";
            if(arg5.isEmpty()) {
                arg5 = "Android WiFi Direct error: " + this.convertReasonCodeToString(arg6);
            }

            v1.put(v4_1, arg5);
            v1.put("errorCode", arg6);
            v0.put("error", v1);
        }
        catch(JSONException v4) {
            Log.e("WifiDirect", v4.toString());
        }
    }

    private JSONObject setEventData(JSONObject arg2, String arg3) throws JSONException {
        arg2.put("eventName", arg3);
        JSONObject v3 = new JSONObject();
        arg2.put("data", v3);
        return v3;
    }
}

