package org.xwalk.core.internal.extension.api.presentation;

import android.app.Activity;
import android.content.Context;
import android.os.Build$VERSION;
import android.util.JsonReader;
import android.util.JsonWriter;
import android.view.Display;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.ref.WeakReference;
import java.net.URI;
import java.net.URISyntaxException;
import org.chromium.base.ThreadUtils;
import org.xwalk.core.internal.Log;
import org.xwalk.core.internal.extension.XWalkExtensionWithActivityStateListener;
import org.xwalk.core.internal.extension.api.XWalkDisplayManager$DisplayListener;
import org.xwalk.core.internal.extension.api.XWalkDisplayManager;

public class PresentationExtension extends XWalkExtensionWithActivityStateListener {
    class org.xwalk.core.internal.extension.api.presentation.PresentationExtension$1 implements DisplayListener {
        org.xwalk.core.internal.extension.api.presentation.PresentationExtension$1(PresentationExtension arg1) {
            PresentationExtension.this = arg1;
            super();
        }

        public void onDisplayAdded(int arg2) {
            PresentationExtension.access$004(PresentationExtension.this);
            if(PresentationExtension.this.mAvailableDisplayCount == 1) {
                PresentationExtension.this.notifyAvailabilityChanged(true);
            }
        }

        public void onDisplayChanged(int arg1) {
        }

        public void onDisplayRemoved(int arg2) {
            PresentationExtension.access$006(PresentationExtension.this);
            if(PresentationExtension.this.mAvailableDisplayCount == 0) {
                PresentationExtension.this.notifyAvailabilityChanged(false);
                PresentationExtension.this.closePresentationContent();
            }
        }
    }

    private static final String CMD_AVAILABILITY_CHANGE = "AvailabilityChange";
    private static final String CMD_DEFAULT_SESSION_STARTED = "DefaultSessionStarted";
    private static final String CMD_GET_AVAILABILITY = "GetAvailability";
    private static final String CMD_SEND_MESSAGE_TO_HOST_DISPLAY = "SendMessageToHostDisplay";
    private static final String CMD_SEND_MESSAGE_TO_REMOTE_DISPLAY = "SendMessageToRemoteDisplay";
    private static final String CMD_SESSION_MESSAGE_TO_HOST_RECEIVED = "SessionMessageToHostReceived";
    private static final String CMD_SESSION_MESSAGE_TO_REMOTE_RECEIVED = "SessionMessageToRemoteReceived";
    private static final String CMD_SESSION_START_FAILED = "SessionStartFailed";
    private static final String CMD_SESSION_START_SUCCEEDED = "SessionStartSucceeded";
    private static final String CMD_START_SESSION = "StartSession";
    private static final String ERROR_INVALID_ACCESS = "InvalidAccessError";
    private static final String ERROR_INVALID_PARAMETER = "InvalidParameterError";
    private static final String ERROR_INVALID_STATE = "InvalidStateError";
    private static final String ERROR_NOT_FOUND = "NotFoundError";
    private static final String ERROR_NOT_SUPPORTED = "NotSupportedError";
    public static final String JS_API_PATH = "jsapi/presentation_api.js";
    private static final String NAME = "navigator.presentation";
    private static final String TAG = "PresentationExtension";
    private static final String TAG_BASE_URL = "baseUrl";
    private static final String TAG_CMD = "cmd";
    private static final String TAG_DATA = "data";
    private static final String TAG_PRESENTATION_ID = "presentationId";
    private static final String TAG_REQUEST_ID = "requestId";
    private static final String TAG_URL = "url";
    private WeakReference mActivity;
    private int mAvailableDisplayCount;
    private Context mContext;
    private final DisplayListener mDisplayListener;
    private XWalkDisplayManager mDisplayManager;
    private XWalkPresentationContent mPresentationContent;
    private PresentationDelegate mPresentationDelegate;
    private PresentationView mPresentationView;

    public PresentationExtension(String arg2, Activity arg3) {
        super("navigator.presentation", arg2, arg3);
        this.mAvailableDisplayCount = 0;
        this.mDisplayListener = new org.xwalk.core.internal.extension.api.presentation.PresentationExtension$1(this);
        this.mContext = arg3.getApplicationContext();
        this.mActivity = new WeakReference(arg3);
        this.mDisplayManager = XWalkDisplayManager.getInstance(arg3.getApplicationContext());
        this.mAvailableDisplayCount = this.mDisplayManager.getPresentationDisplays().length;
    }

    static int access$000(PresentationExtension arg0) {
        return arg0.mAvailableDisplayCount;
    }

    static int access$004(PresentationExtension arg1) {
        int v0 = arg1.mAvailableDisplayCount + 1;
        arg1.mAvailableDisplayCount = v0;
        return v0;
    }

    static int access$006(PresentationExtension arg1) {
        int v0 = arg1.mAvailableDisplayCount - 1;
        arg1.mAvailableDisplayCount = v0;
        return v0;
    }

    static void access$100(PresentationExtension arg0, boolean arg1) {
        arg0.notifyAvailabilityChanged(arg1);
    }

    static void access$1000(PresentationExtension arg0, Display arg1) {
        arg0.updatePresentationView(arg1);
    }

    static void access$200(PresentationExtension arg0) {
        arg0.closePresentationContent();
    }

    static Display access$300(PresentationExtension arg0) {
        return arg0.getPreferredDisplay();
    }

    static void access$400(PresentationExtension arg0, int arg1, int arg2, String arg3) {
        arg0.notifyStartSessionFail(arg1, arg2, arg3);
    }

    static XWalkPresentationContent access$500(PresentationExtension arg0) {
        return arg0.mPresentationContent;
    }

    static XWalkPresentationContent access$502(PresentationExtension arg0, XWalkPresentationContent arg1) {
        arg0.mPresentationContent = arg1;
        return arg1;
    }

    static Context access$600(PresentationExtension arg0) {
        return arg0.mContext;
    }

    static WeakReference access$700(PresentationExtension arg0) {
        return arg0.mActivity;
    }

    static void access$800(PresentationExtension arg0, int arg1, int arg2, int arg3) {
        arg0.notifyStartSessionSucceed(arg1, arg2, arg3);
    }

    static PresentationView access$900(PresentationExtension arg0) {
        return arg0.mPresentationView;
    }

    static PresentationView access$902(PresentationExtension arg0, PresentationView arg1) {
        arg0.mPresentationView = arg1;
        return arg1;
    }

    private void closePresentationContent() {
        if(this.mPresentationContent == null) {
            return;
        }

        this.mPresentationContent.close();
        this.mPresentationContent = null;
    }

    private void dismissPresentationView() {
        if(this.mPresentationView == null) {
            return;
        }

        this.mPresentationView.dismiss();
        this.mPresentationView = null;
    }

    private Display getPreferredDisplay() {
        Display[] v0 = this.mDisplayManager.getPresentationDisplays();
        if(v0.length > 0) {
            return v0[0];
        }

        return null;
    }

    private void handleSendMessageToHostDisplay(int arg1, int arg2, String arg3) {
        this.notifySessionMessageReceived(true, arg2, arg3);
    }

    private void handleSendMessageToRemoteDisplay(int arg1, int arg2, String arg3) {
        this.notifySessionMessageReceived(false, arg2, arg3);
    }

    private void handleStartSession(int arg8, int arg9, String arg10, String arg11) {
        if(Build$VERSION.SDK_INT < 17) {
            this.notifyStartSessionFail(arg8, arg9, "NotSupportedError");
            return;
        }

        if(this.mAvailableDisplayCount == 0) {
            Log.d("PresentationExtension", "No available presentation display is found.");
            this.notifyStartSessionFail(arg8, arg9, "NotFoundError");
            return;
        }

        ThreadUtils.runOnUiThread(new Runnable(arg8, arg9, arg10, arg11) {
            public void run() {
                Display v0 = PresentationExtension.this.getPreferredDisplay();
                if(v0 == null) {
                    PresentationExtension.this.notifyStartSessionFail(this.val$instanceId, this.val$requestId, "NotFoundError");
                    return;
                }

                if(PresentationExtension.this.mPresentationContent != null) {
                    PresentationExtension.this.notifyStartSessionFail(this.val$instanceId, this.val$requestId, "InvalidAccessError");
                    return;
                }

                String v1 = this.val$url;
                try {
                    URI v2 = new URI(this.val$url);
                    if(!v2.isAbsolute()) {
                        v1 = new URI(this.val$baseUrl).resolve(v2).toString();
                    }
                }
                catch(URISyntaxException ) {
                    Log.e("PresentationExtension", "Invalid url passed to requestShow");
                    PresentationExtension.this.notifyStartSessionFail(this.val$instanceId, this.val$requestId, "InvalidParameterError");
                    return;
                }

                PresentationExtension.this.mPresentationContent = new XWalkPresentationContent(PresentationExtension.this.mContext, PresentationExtension.this.mActivity, new PresentationDelegate() {
                    public void onContentClosed(XWalkPresentationContent arg2) {
                        if(arg2 == this.this$1.this$0.mPresentationContent) {
                            this.this$1.this$0.closePresentationContent();
                            if(this.this$1.this$0.mPresentationView != null) {
                                this.this$1.this$0.mPresentationView.cancel();
                            }
                        }
                    }

                    public void onContentLoaded(XWalkPresentationContent arg4) {
                        this.this$1.this$0.notifyStartSessionSucceed(this.this$1.val$instanceId, this.this$1.val$requestId, arg4.getPresentationId());
                    }
                });
                PresentationExtension.this.mPresentationContent.load(v1);
                PresentationExtension.this.updatePresentationView(v0);
            }
        });
    }

    private void notifyAvailabilityChanged(boolean arg5) {
        StringWriter v0 = new StringWriter();
        JsonWriter v1 = new JsonWriter(((Writer)v0));
        try {
            v1.beginObject();
            v1.name("cmd").value("AvailabilityChange");
            v1.name("data").value(arg5);
            v1.endObject();
            this.broadcastMessage(v0.toString());
            goto label_15;
        }
        catch(Throwable v5) {
        label_18:
            try {
                v1.close();
                goto label_32;
            }
            catch(IOException ) {
            label_32:
                throw v5;
            }
        }
        catch(IOException v5_1) {
            try {
                Log.e("PresentationExtension", "Error: " + v5_1.toString());
            }
            catch(Throwable v5) {
                goto label_18;
            }

            try {
            label_15:
                v1.close();
            }
            catch(IOException ) {
            }
        }
    }

    private void notifySessionMessageReceived(boolean arg5, int arg6, String arg7) {
        StringWriter v0 = new StringWriter();
        JsonWriter v1 = new JsonWriter(((Writer)v0));
        try {
            v1.beginObject();
            if(arg5) {
                v1.name("cmd").value("SessionMessageToHostReceived");
            }
            else {
                v1.name("cmd").value("SessionMessageToRemoteReceived");
            }

            v1.name("presentationId").value(((long)arg6));
            v1.name("data").value(arg7);
            v1.endObject();
            this.broadcastMessage(v0.toString());
            goto label_25;
        }
        catch(Throwable v5) {
        label_28:
            try {
                v1.close();
                goto label_42;
            }
            catch(IOException ) {
            label_42:
                throw v5;
            }
        }
        catch(IOException v5_1) {
            try {
                Log.e("PresentationExtension", "Error: " + v5_1.toString());
            }
            catch(Throwable v5) {
                goto label_28;
            }

            try {
            label_25:
                v1.close();
            }
            catch(IOException ) {
            }
        }
    }

    private void notifyStartSessionFail(int arg6, int arg7, String arg8) {
        StringWriter v0 = new StringWriter();
        JsonWriter v1 = new JsonWriter(((Writer)v0));
        try {
            v1.beginObject();
            v1.name("cmd").value("SessionStartFailed");
            v1.name("requestId").value(((long)arg7));
            v1.name("data").value(arg8);
            v1.endObject();
            this.postMessage(arg6, v0.toString());
            goto label_19;
        }
        catch(Throwable v6) {
        label_22:
            try {
                v1.close();
                goto label_36;
            }
            catch(IOException ) {
            label_36:
                throw v6;
            }
        }
        catch(IOException v6_1) {
            try {
                Log.e("PresentationExtension", "Error: " + v6_1.toString());
            }
            catch(Throwable v6) {
                goto label_22;
            }

            try {
            label_19:
                v1.close();
            }
            catch(IOException ) {
            }
        }
    }

    private void notifyStartSessionSucceed(int arg6, int arg7, int arg8) {
        StringWriter v0 = new StringWriter();
        JsonWriter v1 = new JsonWriter(((Writer)v0));
        try {
            v1.beginObject();
            v1.name("cmd").value("SessionStartSucceeded");
            v1.name("requestId").value(((long)arg7));
            v1.name("data").value(((long)arg8));
            v1.endObject();
            this.postMessage(arg6, v0.toString());
            goto label_20;
        }
        catch(Throwable v6) {
        label_23:
            try {
                v1.close();
                goto label_37;
            }
            catch(IOException ) {
            label_37:
                throw v6;
            }
        }
        catch(IOException v6_1) {
            try {
                Log.e("PresentationExtension", "Error: " + v6_1.toString());
            }
            catch(Throwable v6) {
                goto label_23;
            }

            try {
            label_20:
                v1.close();
            }
            catch(IOException ) {
            }
        }
    }

    public void onActivityStateChange(Activity arg1, int arg2) {
        if(arg2 != 6) {
            switch(arg2) {
                case 3: {
                    goto label_13;
                }
                case 4: {
                    goto label_4;
                }
            }

            return;
        label_4:
            this.dismissPresentationView();
            if(this.mPresentationContent != null) {
                this.mPresentationContent.onPause();
            }

            this.mDisplayManager.unregisterDisplayListener(this.mDisplayListener);
            return;
        label_13:
            this.onResume();
        }
        else {
            this.closePresentationContent();
        }
    }

    public void onMessage(int arg9, String arg10) {
        int v4;
        String v5;
        String v3;
        String v2;
        int v0;
        JsonReader v10 = new JsonReader(new StringReader(arg10));
        try {
            v10.beginObject();
            v0 = -1;
            String v1 = null;
            v2 = v1;
            v3 = v2;
            v5 = v3;
            v4 = -1;
            while(v10.hasNext()) {
                String v6 = v10.nextName();
                if(v6.equals("cmd")) {
                    v1 = v10.nextString();
                    continue;
                }

                if(v6.equals("requestId")) {
                    v0 = v10.nextInt();
                    continue;
                }

                if(v6.equals("url")) {
                    v2 = v10.nextString();
                    continue;
                }

                if(v6.equals("baseUrl")) {
                    v3 = v10.nextString();
                    continue;
                }

                if(v6.equals("presentationId")) {
                    v4 = v10.nextInt();
                    continue;
                }

                if(v6.equals("data")) {
                    v5 = v10.nextString();
                    continue;
                }

                v10.skipValue();
            }

            v10.endObject();
            if(v1 != null) {
                goto label_50;
            }
        }
        catch(Throwable v9) {
            goto label_68;
        }
        catch(IOException v9_1) {
            goto label_70;
        }

        try {
            v10.close();
            return;
        }
        catch(IOException ) {
            return;
        }

        try {
        label_50:
            if((v1.equals("StartSession")) && v0 >= 0) {
                this.handleStartSession(arg9, v0, v2, v3);
                goto label_65;
            }

            if(v1.equals("SendMessageToRemoteDisplay")) {
                this.handleSendMessageToRemoteDisplay(arg9, v4, v5);
                goto label_65;
            }

            if(!v1.equals("SendMessageToHostDisplay")) {
                goto label_65;
            }

            this.handleSendMessageToHostDisplay(arg9, v4, v5);
            goto label_65;
        }
        catch(Throwable v9) {
        label_68:
            try {
                v10.close();
                goto label_81;
            }
            catch(IOException ) {
            label_81:
                throw v9;
            }
        }
        catch(IOException v9_1) {
            try {
            label_70:
                Log.d("PresentationExtension", "Error: " + v9_1);
            }
            catch(Throwable v9) {
                goto label_68;
            }

            try {
            label_65:
                v10.close();
                return;
            }
            catch(IOException ) {
                return;
            }
        }
    }

    public void onResume() {
        Display[] v0 = this.mDisplayManager.getPresentationDisplays();
        if(v0.length == 0 && this.mAvailableDisplayCount > 0) {
            this.notifyAvailabilityChanged(false);
            this.mAvailableDisplayCount = 0;
            this.closePresentationContent();
        }

        if(v0.length > 0 && this.mAvailableDisplayCount == 0) {
            this.notifyAvailabilityChanged(true);
            this.mAvailableDisplayCount = v0.length;
        }

        if(v0.length > 0 && this.mAvailableDisplayCount > 0) {
            this.mAvailableDisplayCount = v0.length;
        }

        if(this.mPresentationContent != null) {
            this.mPresentationContent.onResume();
        }

        this.updatePresentationView(this.getPreferredDisplay());
        this.mDisplayManager.registerDisplayListener(this.mDisplayListener);
    }

    public String onSyncMessage(int arg3, String arg4) {
        if(arg4.equals("GetAvailability")) {
            String v3 = this.mAvailableDisplayCount != 0 ? "true" : "false";
            return v3;
        }

        Log.e("PresentationExtension", "Unexpected sync message received: " + arg4);
        return "";
    }

    private void updatePresentationView(Display arg4) {
        Object v0 = this.mActivity.get();
        if(v0 == null) {
            return;
        }

        if(Build$VERSION.SDK_INT >= 17) {
            if(arg4 == null) {
            }
            else {
                if(this.mPresentationView == null && this.mPresentationContent == null) {
                    return;
                }

                if(this.mPresentationView != null && this.mPresentationView.getDisplay() != arg4) {
                    this.dismissPresentationView();
                }

                if(this.mPresentationView == null && this.mPresentationContent != null) {
                    ViewParent v1 = this.mPresentationContent.getContentView().getParent();
                    if(v1 != null) {
                        ((ViewGroup)v1).removeView(this.mPresentationContent.getContentView());
                    }

                    this.mPresentationView = PresentationView.createInstance(((Context)v0), arg4);
                    this.mPresentationView.setContentView(this.mPresentationContent.getContentView());
                    this.mPresentationView.setPresentationListener(new PresentationListener() {
                        public void onDismiss(PresentationView arg2) {
                            if(arg2 == PresentationExtension.this.mPresentationView) {
                                if(PresentationExtension.this.mPresentationContent != null) {
                                    PresentationExtension.this.mPresentationContent.onPause();
                                }

                                PresentationExtension.this.mPresentationView = null;
                            }
                        }

                        public void onShow(PresentationView arg2) {
                            if(arg2 == PresentationExtension.this.mPresentationView && PresentationExtension.this.mPresentationContent != null) {
                                PresentationExtension.this.mPresentationContent.onResume();
                            }
                        }
                    });
                }

                this.mPresentationView.show();
                return;
            }
        }
    }
}

