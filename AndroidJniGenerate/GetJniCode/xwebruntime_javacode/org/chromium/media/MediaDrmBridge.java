package org.chromium.media;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.media.DeniedByServerException;
import android.media.MediaCrypto;
import android.media.MediaCryptoException;
import android.media.MediaDrm$KeyRequest;
import android.media.MediaDrm$KeyStatus;
import android.media.MediaDrm$MediaDrmStateException;
import android.media.MediaDrm$OnEventListener;
import android.media.MediaDrm$OnExpirationUpdateListener;
import android.media.MediaDrm$OnKeyStatusChangeListener;
import android.media.MediaDrm$ProvisionRequest;
import android.media.MediaDrm;
import android.media.MediaDrmException;
import android.media.NotProvisionedException;
import android.media.UnsupportedSchemeException;
import android.os.Build$VERSION;
import android.os.Handler;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import org.chromium.base.ApiCompatibilityUtils;
import org.chromium.base.Callback;
import org.chromium.base.Log;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.base.annotations.MainDex;

@SuppressLint(value={"WrongConstant"}) @TargetApi(value=19) @JNINamespace(value="media") @MainDex public class MediaDrmBridge {
    @MainDex class EventListener implements MediaDrm$OnEventListener {
        static {
        }

        EventListener(MediaDrmBridge arg1, org.chromium.media.MediaDrmBridge$1 arg2) {
            this(arg1);
        }

        private EventListener(MediaDrmBridge arg1) {
            MediaDrmBridge.this = arg1;
            super();
        }

        public void onEvent(MediaDrm arg9, byte[] arg10, int arg11, int arg12, byte[] arg13) {
            MediaDrm$KeyRequest v10_2;
            if(arg10 == null) {
                Log.e("cr_media", "EventListener: Null session.", new Object[0]);
                return;
            }

            SessionId v12 = MediaDrmBridge.this.getSessionIdByDrmId(arg10);
            boolean v6 = true;
            if(v12 == null) {
                Log.e("cr_media", "EventListener: Invalid session %s", new Object[]{SessionId.toHexString(arg10)});
                return;
            }

            SessionInfo v10 = MediaDrmBridge.this.mSessionManager.get(v12);
            int v7 = 23;
            switch(arg11) {
                case 2: {
                    Log.d("cr_media", "MediaDrm.EVENT_KEY_REQUIRED");
                    if(MediaDrmBridge.this.mProvisioningPending) {
                        return;
                    }

                    try {
                        v10_2 = MediaDrmBridge.this.getKeyRequest(v12, arg13, v10.mimeType(), v10.keyType(), null);
                        if(v10_2 == null) {
                            goto label_70;
                        }
                    }
                    catch(NotProvisionedException v10_1) {
                        Log.e("cr_media", "Device not provisioned", new Object[]{v10_1});
                        MediaDrmBridge.this.startProvisioning();
                        return;
                    }

                    MediaDrmBridge.this.onSessionMessage(v12, v10_2);
                    return;
                label_70:
                    if(Build$VERSION.SDK_INT < v7) {
                        MediaDrmBridge.this.onSessionKeysChange(v12, MediaDrmBridge.getDummyKeysInfo(4).toArray(), false, false);
                    }

                    Log.e("cr_media", "EventListener: getKeyRequest failed.", new Object[0]);
                    return;
                }
                case 3: {
                    Log.d("cr_media", "MediaDrm.EVENT_KEY_EXPIRED");
                    if(Build$VERSION.SDK_INT >= v7) {
                        return;
                    }

                    MediaDrmBridge v11 = MediaDrmBridge.this;
                    Object[] v13 = MediaDrmBridge.getDummyKeysInfo(1).toArray();
                    if(v10.keyType() == 3) {
                    }
                    else {
                        v6 = false;
                    }

                    v11.onSessionKeysChange(v12, v13, false, v6);
                    break;
                }
                case 4: {
                    Log.d("cr_media", "MediaDrm.EVENT_VENDOR_DEFINED");
                    break;
                }
                default: {
                    Log.e("cr_media", "Invalid DRM event " + arg11, new Object[0]);
                    return;
                }
            }
        }
    }

    @TargetApi(value=23) @MainDex class ExpirationUpdateListener implements MediaDrm$OnExpirationUpdateListener {
        static {
        }

        ExpirationUpdateListener(MediaDrmBridge arg1, org.chromium.media.MediaDrmBridge$1 arg2) {
            this(arg1);
        }

        private ExpirationUpdateListener(MediaDrmBridge arg1) {
            MediaDrmBridge.this = arg1;
            super();
        }

        public void onExpirationUpdate(MediaDrm arg2, byte[] arg3, long arg4) {
            SessionId v2 = MediaDrmBridge.this.getSessionIdByDrmId(arg3);
            MediaDrmBridge.this.deferEventHandleIfNeeded(v2, new Runnable(v2, arg4) {
                public void run() {
                    Log.d("cr_media", "ExpirationUpdate: " + this.val$sessionId.toHexString() + ", " + this.val$expirationTime);
                    this.this$1.this$0.onSessionExpirationUpdate(this.val$sessionId, this.val$expirationTime);
                }
            });
        }
    }

    @MainDex class KeyStatus {
        private final byte[] mKeyId;
        private final int mStatusCode;

        KeyStatus(byte[] arg1, int arg2, org.chromium.media.MediaDrmBridge$1 arg3) {
            this(arg1, arg2);
        }

        private KeyStatus(byte[] arg1, int arg2) {
            super();
            this.mKeyId = arg1;
            this.mStatusCode = arg2;
        }

        @CalledByNative(value="KeyStatus") private byte[] getKeyId() {
            return this.mKeyId;
        }

        @CalledByNative(value="KeyStatus") private int getStatusCode() {
            return this.mStatusCode;
        }
    }

    @TargetApi(value=23) @MainDex class KeyStatusChangeListener implements MediaDrm$OnKeyStatusChangeListener {
        static {
        }

        KeyStatusChangeListener(MediaDrmBridge arg1, org.chromium.media.MediaDrmBridge$1 arg2) {
            this(arg1);
        }

        private KeyStatusChangeListener(MediaDrmBridge arg1) {
            MediaDrmBridge.this = arg1;
            super();
        }

        static List access$2200(KeyStatusChangeListener arg0, List arg1) {
            return arg0.getKeysInfo(arg1);
        }

        private List getKeysInfo(List arg6) {
            ArrayList v0 = new ArrayList();
            Iterator v6 = arg6.iterator();
            while(v6.hasNext()) {
                Object v1 = v6.next();
                ((List)v0).add(new KeyStatus(((MediaDrm$KeyStatus)v1).getKeyId(), ((MediaDrm$KeyStatus)v1).getStatusCode(), null));
            }

            return ((List)v0);
        }

        public void onKeyStatusChange(MediaDrm arg8, byte[] arg9, List arg10, boolean arg11) {
            SessionId v8 = MediaDrmBridge.this.getSessionIdByDrmId(arg9);
            boolean v5 = MediaDrmBridge.this.mSessionManager.get(v8).keyType() == 3 ? true : false;
            MediaDrmBridge.this.deferEventHandleIfNeeded(v8, new Runnable(v8, arg11, arg10, v5) {
                public void run() {
                    Log.d("cr_media", "KeysStatusChange: " + this.val$sessionId.toHexString() + ", " + this.val$hasNewUsableKey);
                    this.this$1.this$0.onSessionKeysChange(this.val$sessionId, this.this$1.getKeysInfo(this.val$keyInformation).toArray(), this.val$hasNewUsableKey, this.val$isKeyRelease);
                }
            });
        }
    }

    @MainDex class KeyUpdatedCallback implements Callback {
        private final boolean mIsKeyRelease;
        private final long mPromiseId;
        private final SessionId mSessionId;

        KeyUpdatedCallback(MediaDrmBridge arg1, SessionId arg2, long arg3, boolean arg5) {
            MediaDrmBridge.this = arg1;
            super();
            this.mSessionId = arg2;
            this.mPromiseId = arg3;
            this.mIsKeyRelease = arg5;
        }

        public void onResult(Boolean arg5) {
            if(!arg5.booleanValue()) {
                MediaDrmBridge.this.onPromiseRejected(this.mPromiseId, "failed to update key after response accepted");
                return;
            }

            String v5 = "cr_media";
            String v0 = "Key successfully %s for session %s";
            String v1 = this.mIsKeyRelease ? "released" : "added";
            Log.d(v5, v0, v1, this.mSessionId.toHexString());
            MediaDrmBridge.this.onPromiseResolved(this.mPromiseId);
            if(!this.mIsKeyRelease && Build$VERSION.SDK_INT < 23) {
                MediaDrmBridge.this.onSessionKeysChange(this.mSessionId, MediaDrmBridge.getDummyKeysInfo(0).toArray(), true, this.mIsKeyRelease);
            }
        }

        public void onResult(Object arg1) {
            this.onResult(((Boolean)arg1));
        }
    }

    @MainDex class PendingCreateSessionData {
        private final byte[] mInitData;
        private final int mKeyType;
        private final String mMimeType;
        private final HashMap mOptionalParameters;
        private final long mPromiseId;

        static {
        }

        PendingCreateSessionData(byte[] arg1, String arg2, int arg3, HashMap arg4, long arg5, org.chromium.media.MediaDrmBridge$1 arg7) {
            this(arg1, arg2, arg3, arg4, arg5);
        }

        private PendingCreateSessionData(byte[] arg1, String arg2, int arg3, HashMap arg4, long arg5) {
            super();
            this.mInitData = arg1;
            this.mMimeType = arg2;
            this.mKeyType = arg3;
            this.mOptionalParameters = arg4;
            this.mPromiseId = arg5;
        }

        static long access$400(PendingCreateSessionData arg2) {
            return arg2.promiseId();
        }

        static byte[] access$600(PendingCreateSessionData arg0) {
            return arg0.initData();
        }

        static String access$700(PendingCreateSessionData arg0) {
            return arg0.mimeType();
        }

        static int access$800(PendingCreateSessionData arg0) {
            return arg0.keyType();
        }

        static HashMap access$900(PendingCreateSessionData arg0) {
            return arg0.optionalParameters();
        }

        private byte[] initData() {
            return this.mInitData;
        }

        private int keyType() {
            return this.mKeyType;
        }

        private String mimeType() {
            return this.mMimeType;
        }

        private HashMap optionalParameters() {
            return this.mOptionalParameters;
        }

        private long promiseId() {
            return this.mPromiseId;
        }
    }

    class SessionEventDeferrer {
        private final ArrayList mEventHandlers;
        private final SessionId mSessionId;

        SessionEventDeferrer(SessionId arg1) {
            super();
            this.mSessionId = arg1;
            this.mEventHandlers = new ArrayList();
        }

        void defer(Runnable arg2) {
            this.mEventHandlers.add(arg2);
        }

        void fire() {
            Iterator v0 = this.mEventHandlers.iterator();
            while(v0.hasNext()) {
                v0.next().run();
            }

            this.mEventHandlers.clear();
        }

        boolean shouldDefer(SessionId arg2) {
            return this.mSessionId.isEqual(arg2);
        }
    }

    private static final byte[] DUMMY_KEY_ID = null;
    private static final String ENABLE = "enable";
    private static final long INVALID_NATIVE_MEDIA_DRM_BRIDGE = 0;
    private static final String ORIGIN = "origin";
    private static final String PRIVACY_MODE = "privacyMode";
    private static final String SECURITY_LEVEL = "securityLevel";
    private static final String SERVER_CERTIFICATE = "serviceCertificate";
    private static final String SESSION_SHARING = "sessionSharing";
    private static final String TAG = "cr_media";
    private static final byte[] UNPROVISION;
    private static final UUID WIDEVINE_UUID;
    private MediaCrypto mMediaCrypto;
    private SessionId mMediaCryptoSession;
    private MediaDrm mMediaDrm;
    private long mNativeMediaDrmBridge;
    private boolean mOriginSet;
    private ArrayDeque mPendingCreateSessionDataQueue;
    private boolean mProvisioningPending;
    private boolean mResetDeviceCredentialsPending;
    private UUID mSchemeUUID;
    private SessionEventDeferrer mSessionEventDeferrer;
    private MediaDrmSessionManager mSessionManager;
    private MediaDrmStorageBridge mStorage;

    static {
        MediaDrmBridge.WIDEVINE_UUID = UUID.fromString("edef8ba9-79d6-4ace-a3c8-27dcd51d21ed");
        MediaDrmBridge.DUMMY_KEY_ID = new byte[]{0};
        MediaDrmBridge.UNPROVISION = ApiCompatibilityUtils.getBytesUtf8("unprovision");
    }

    @TargetApi(value=23) private MediaDrmBridge(UUID arg4, long arg5, long arg7) throws UnsupportedSchemeException {
        super();
        this.mOriginSet = false;
        SessionEventDeferrer v1 = null;
        this.mSessionEventDeferrer = v1;
        this.mSchemeUUID = arg4;
        this.mMediaDrm = new MediaDrm(arg4);
        this.mNativeMediaDrmBridge = arg5;
        this.mStorage = new MediaDrmStorageBridge(arg7);
        this.mSessionManager = new MediaDrmSessionManager(this.mStorage);
        this.mPendingCreateSessionDataQueue = new ArrayDeque();
        this.mResetDeviceCredentialsPending = false;
        this.mProvisioningPending = false;
        this.mMediaDrm.setOnEventListener(new EventListener(this, ((org.chromium.media.MediaDrmBridge$1)v1)));
        if(Build$VERSION.SDK_INT >= 23) {
            this.mMediaDrm.setOnExpirationUpdateListener(new ExpirationUpdateListener(this, ((org.chromium.media.MediaDrmBridge$1)v1)), ((Handler)v1));
            this.mMediaDrm.setOnKeyStatusChangeListener(new KeyStatusChangeListener(this, ((org.chromium.media.MediaDrmBridge$1)v1)), ((Handler)v1));
        }

        if(this.isWidevine()) {
            this.mMediaDrm.setPropertyString("privacyMode", "enable");
            this.mMediaDrm.setPropertyString("sessionSharing", "enable");
        }
    }

    static void access$1000(MediaDrmBridge arg0, long arg1) {
        arg0.onPersistentLicenseNoExist(arg1);
    }

    static void access$1100(MediaDrmBridge arg0, SessionId arg1, long arg2) {
        arg0.loadSessionWithLoadedStorage(arg1, arg2);
    }

    static void access$1200(MediaDrmBridge arg0) {
        arg0.release();
    }

    static void access$1300(MediaDrmBridge arg0) {
        arg0.processPendingCreateSessionData();
    }

    static SessionId access$1400(MediaDrmBridge arg0, byte[] arg1) {
        return arg0.getSessionIdByDrmId(arg1);
    }

    static MediaDrmSessionManager access$1500(MediaDrmBridge arg0) {
        return arg0.mSessionManager;
    }

    static boolean access$1600(MediaDrmBridge arg0) {
        return arg0.mProvisioningPending;
    }

    static MediaDrm$KeyRequest access$1700(MediaDrmBridge arg0, SessionId arg1, byte[] arg2, String arg3, int arg4, HashMap arg5) throws NotProvisionedException {
        return arg0.getKeyRequest(arg1, arg2, arg3, arg4, arg5);
    }

    static void access$1800(MediaDrmBridge arg0) {
        arg0.startProvisioning();
    }

    static void access$1900(MediaDrmBridge arg0, SessionId arg1, MediaDrm$KeyRequest arg2) {
        arg0.onSessionMessage(arg1, arg2);
    }

    static List access$2000(int arg0) {
        return MediaDrmBridge.getDummyKeysInfo(arg0);
    }

    static void access$2100(MediaDrmBridge arg0, SessionId arg1, Object[] arg2, boolean arg3, boolean arg4) {
        arg0.onSessionKeysChange(arg1, arg2, arg3, arg4);
    }

    static void access$2300(MediaDrmBridge arg0, SessionId arg1, Runnable arg2) {
        arg0.deferEventHandleIfNeeded(arg1, arg2);
    }

    static void access$2400(MediaDrmBridge arg0, SessionId arg1, long arg2) {
        arg0.onSessionExpirationUpdate(arg1, arg2);
    }

    static void access$2500(MediaDrmBridge arg0, long arg1, String arg3) {
        arg0.onPromiseRejected(arg1, arg3);
    }

    static void access$2600(MediaDrmBridge arg0, long arg1) {
        arg0.onPromiseResolved(arg1);
    }

    @CalledByNative private void closeSession(byte[] arg6, long arg7) {
        Log.d("cr_media", "closeSession()");
        if(this.mMediaDrm == null) {
            this.onPromiseRejected(arg7, "closeSession() called when MediaDrm is null.");
            return;
        }

        SessionId v0 = this.getSessionIdByEmeId(arg6);
        if(v0 == null) {
            this.onPromiseRejected(arg7, "Invalid sessionId in closeSession(): " + SessionId.toHexString(arg6));
            return;
        }

        try {
            this.mMediaDrm.removeKeys(v0.drmId());
        }
        catch(Exception v6) {
            Log.e("cr_media", "removeKeys failed: ", new Object[]{v6});
        }

        this.closeSessionNoException(v0);
        this.mSessionManager.remove(v0);
        this.onPromiseResolved(arg7);
        this.onSessionClosed(v0);
        Log.d("cr_media", "Session %s closed", v0.toHexString());
    }

    private void closeSessionNoException(SessionId arg5) {
        try {
            this.mMediaDrm.closeSession(arg5.drmId());
        }
        catch(Exception v5) {
            Log.e("cr_media", "closeSession failed: ", new Object[]{v5});
        }
    }

    @CalledByNative private static MediaDrmBridge create(byte[] arg9, String arg10, String arg11, long arg12, long arg14) {
        MediaDrmBridge v8;
        UUID v1 = MediaDrmBridge.getUUIDFromBytes(arg9);
        MediaDrmBridge v9 = null;
        if(v1 != null) {
            if(!MediaDrm.isCryptoSchemeSupported(v1)) {
            }
            else {
                try {
                    v8 = new MediaDrmBridge(v1, arg12, arg14);
                    Log.d("cr_media", "MediaDrmBridge successfully created.");
                }
                catch(IllegalStateException v10) {
                    Log.e("cr_media", "Failed to create MediaDrmBridge", new Object[]{v10});
                    return v9;
                }
                catch(IllegalArgumentException v10_1) {
                    Log.e("cr_media", "Failed to create MediaDrmBridge", new Object[]{v10_1});
                    return v9;
                }
                catch(UnsupportedSchemeException v10_2) {
                    Log.e("cr_media", "Unsupported DRM scheme", new Object[]{v10_2});
                    return v9;
                }

                if(!arg11.isEmpty() && !v8.setSecurityLevel(arg11)) {
                    return v9;
                }

                if(!arg10.isEmpty() && !v8.setOrigin(arg10)) {
                    return v9;
                }

                if(!v8.createMediaCrypto()) {
                    return v9;
                }

                return v8;
            }
        }

        return v9;
    }

    private boolean createMediaCrypto() {
        byte[] v1_1;
        try {
            v1_1 = this.openSession();
            if(v1_1 != null) {
                goto label_9;
            }
        }
        catch(NotProvisionedException v1) {
            Log.d("cr_media", "Device not provisioned", v1);
            this.startProvisioning();
            return 1;
        }

        Log.e("cr_media", "Cannot create MediaCrypto Session.", new Object[0]);
        return 0;
    label_9:
        this.mMediaCryptoSession = SessionId.createTemporarySessionId(v1_1);
        Log.d("cr_media", "MediaCrypto Session created: %s", this.mMediaCryptoSession.toHexString());
        try {
            if(MediaCrypto.isCryptoSchemeSupported(this.mSchemeUUID)) {
                this.mMediaCrypto = new MediaCrypto(this.mSchemeUUID, this.mMediaCryptoSession.drmId());
                Log.d("cr_media", "MediaCrypto successfully created!");
                this.onMediaCryptoReady(this.mMediaCrypto);
                return 1;
            }

            Log.e("cr_media", "Cannot create MediaCrypto for unsupported scheme.", new Object[0]);
        }
        catch(MediaCryptoException v1_2) {
            Log.e("cr_media", "Cannot create MediaCrypto", new Object[]{v1_2});
        }

        this.closeSessionNoException(this.mMediaCryptoSession);
        this.mMediaCryptoSession = null;
        return 0;
    }

    private void createSession(byte[] arg15, String arg16, int arg17, HashMap arg18, long arg19) {
        String v2_2;
        MediaDrmSessionManager v1_4;
        NotProvisionedException v1_1;
        SessionId v2_1;
        int v3;
        SessionId v13;
        byte[] v2;
        MediaDrmBridge v7 = this;
        int v8 = arg17;
        long v9 = arg19;
        Log.d("cr_media", "createSession()");
        if(v7.mMediaDrm == null) {
            Log.e("cr_media", "createSession() called when MediaDrm is null.", new Object[0]);
            v7.onPromiseRejected(v9, "MediaDrm released previously.");
            return;
        }

        if(v7.mProvisioningPending) {
            this.savePendingCreateSessionData(arg15, arg16, arg17, arg18, arg19);
            return;
        }

        SessionId v1 = null;
        try {
            v2 = v7.openSession();
            if(v2 != null) {
                goto label_27;
            }

            v7.onPromiseRejected(v9, "Open session failed.");
            return;
        }
        catch(NotProvisionedException v0) {
            v13 = v1;
            v3 = 0;
            goto label_71;
        }

    label_27:
        if(v8 == 2) {
            try {
                v2_1 = SessionId.createPersistentSessionId(v2);
                goto label_37;
            label_36:
                v2_1 = SessionId.createTemporarySessionId(v2);
                goto label_37;
            label_33:
                v13 = v1;
                v3 = 1;
                goto label_71;
            }
            catch(NotProvisionedException v0) {
                goto label_33;
            }
        }
        else {
            goto label_36;
        label_71:
            v1_1 = v0;
            goto label_72;
        }

    label_37:
        v13 = v2_1;
        MediaDrmBridge v1_2 = v7;
        v2_1 = v13;
        byte[] v3_1 = arg15;
        String v4 = arg16;
        int v5 = v8;
        HashMap v6 = arg18;
        try {
            MediaDrm$KeyRequest v1_3 = v1_2.getKeyRequest(v2_1, v3_1, v4, v5, v6);
            if(v1_3 == null) {
                v7.closeSessionNoException(v13);
                v7.onPromiseRejected(v9, "Generate request failed.");
                return;
            }

            Log.d("cr_media", "createSession(): Session (%s) created.", v13.toHexString());
            v7.onPromiseResolvedWithSession(v9, v13);
            v7.onSessionMessage(v13, v1_3);
            v1_4 = v7.mSessionManager;
            v2_2 = arg16;
        }
        catch(NotProvisionedException v0) {
            goto label_64;
        }

        try {
            v1_4.put(v13, v2_2, v8);
            return;
        }
        catch(NotProvisionedException v0) {
        }

    label_64:
        v1_1 = v0;
        v3 = 1;
    label_72:
        Log.e("cr_media", "Device not provisioned", new Object[]{v1_1});
        if(v3 != 0) {
            v7.closeSessionNoException(v13);
        }

        this.savePendingCreateSessionData(arg15, arg16, arg17, arg18, arg19);
        v7.startProvisioning();
    }

    @CalledByNative private void createSessionFromNative(byte[] arg8, String arg9, int arg10, String[] arg11, long arg12) {
        HashMap v4 = new HashMap();
        if(arg11 != null) {
            if(arg11.length % 2 != 0) {
                throw new IllegalArgumentException("Additional data array doesn\'t have equal keys/values");
            }
            else {
                int v0;
                for(v0 = 0; v0 < arg11.length; v0 += 2) {
                    v4.put(arg11[v0], arg11[v0 + 1]);
                }
            }
        }

        this.createSession(arg8, arg9, arg10, v4, arg12);
    }

    private void deferEventHandleIfNeeded(SessionId arg2, Runnable arg3) {
        if(this.mSessionEventDeferrer != null && (this.mSessionEventDeferrer.shouldDefer(arg2))) {
            this.mSessionEventDeferrer.defer(arg3);
            return;
        }

        arg3.run();
    }

    @CalledByNative private void destroy() {
        this.mNativeMediaDrmBridge = 0;
        if(this.mMediaDrm != null) {
            this.release();
        }
    }

    private static List getDummyKeysInfo(int arg4) {
        ArrayList v0 = new ArrayList();
        ((List)v0).add(new KeyStatus(MediaDrmBridge.DUMMY_KEY_ID, arg4, null));
        return ((List)v0);
    }

    private MediaDrm$KeyRequest getKeyRequest(SessionId arg7, byte[] arg8, String arg9, int arg10, HashMap arg11) throws NotProvisionedException {
        MediaDrm$KeyRequest v7_2;
        if(arg11 == null) {
            arg11 = new HashMap();
        }

        HashMap v5 = arg11;
        MediaDrm$KeyRequest v11 = null;
        if(arg10 == 3) {
            try {
                byte[] v7_1 = arg7.keySetId();
                goto label_12;
            label_11:
                v7_1 = arg7.drmId();
            label_12:
                v7_2 = this.mMediaDrm.getKeyRequest(v7_1, arg8, arg9, arg10, v5);
                goto label_32;
            label_10:
                goto label_19;
            }
            catch(IllegalStateException v7) {
                goto label_10;
            }
        }
        else {
            goto label_11;
        }

        goto label_12;
    label_19:
        if(Build$VERSION.SDK_INT >= 21 && ((v7 instanceof MediaDrm$MediaDrmStateException))) {
            Log.e("cr_media", "MediaDrmStateException fired during getKeyRequest().", new Object[]{v7});
        }

        v7_2 = v11;
    label_32:
        String v8 = v7_2 != null ? "successed" : "failed";
        Log.d("cr_media", "getKeyRequest %s!", v8);
        return v7_2;
    }

    @CalledByNative private String getSecurityLevel() {
        if(this.mMediaDrm != null) {
            if(!this.isWidevine()) {
            }
            else {
                return this.mMediaDrm.getPropertyString("securityLevel");
            }
        }

        Log.e("cr_media", "getSecurityLevel(): MediaDrm is null or security level is not supported.", new Object[0]);
        return "";
    }

    private SessionId getSessionIdByDrmId(byte[] arg4) {
        SessionId v1 = null;
        if(this.mMediaCryptoSession == null) {
            Log.e("cr_media", "Session doesn\'t exist because media crypto session is not created.", new Object[0]);
            return v1;
        }

        SessionId v4 = this.mSessionManager.getSessionIdByDrmId(arg4);
        if(v4 == null) {
            return v1;
        }

        return v4;
    }

    private SessionId getSessionIdByEmeId(byte[] arg4) {
        SessionId v1 = null;
        if(this.mMediaCryptoSession == null) {
            Log.e("cr_media", "Session doesn\'t exist because media crypto session is not created.", new Object[0]);
            return v1;
        }

        SessionId v4 = this.mSessionManager.getSessionIdByEmeId(arg4);
        if(v4 == null) {
            return v1;
        }

        return v4;
    }

    private static UUID getUUIDFromBytes(byte[] arg11) {
        int v6;
        int v1 = 16;
        if(arg11.length != v1) {
            return null;
        }

        int v0 = 0;
        long v2 = 0;
        long v4;
        for(v4 = v2; true; v4 = v8) {
            v6 = 8;
            if(v0 >= v6) {
                break;
            }

            long v8 = v4 << v6 | (((long)(arg11[v0] & 0xFF)));
            ++v0;
        }

        v0 = 8;
        while(v0 < v1) {
            long v9 = v2 << v6 | (((long)(arg11[v0] & 0xFF)));
            ++v0;
            v2 = v9;
        }

        return new UUID(v4, v2);
    }

    @CalledByNative private static boolean isCryptoSchemeSupported(byte[] arg1, String arg2) {
        UUID v1 = MediaDrmBridge.getUUIDFromBytes(arg1);
        if(arg2.isEmpty()) {
            return MediaDrm.isCryptoSchemeSupported(v1);
        }

        return MediaDrm.isCryptoSchemeSupported(v1, arg2);
    }

    private boolean isNativeMediaDrmBridgeValid() {
        boolean v0 = this.mNativeMediaDrmBridge != 0 ? true : false;
        return v0;
    }

    private boolean isWidevine() {
        return this.mSchemeUUID.equals(MediaDrmBridge.WIDEVINE_UUID);
    }

    @CalledByNative private void loadSession(byte[] arg3, long arg4) {
        Log.d("cr_media", "loadSession()");
        if(this.mProvisioningPending) {
            this.onPersistentLicenseNoExist(arg4);
            return;
        }

        this.mSessionManager.load(arg3, new Callback(arg4) {
            public void onResult(Object arg1) {
                this.onResult(((SessionId)arg1));
            }

            public void onResult(SessionId arg4) {
                if(arg4 == null) {
                    MediaDrmBridge.this.onPersistentLicenseNoExist(this.val$promiseId);
                    return;
                }

                MediaDrmBridge.this.loadSessionWithLoadedStorage(arg4, this.val$promiseId);
            }
        });
    }

    private void loadSessionWithLoadedStorage(SessionId arg4, long arg5) {
        try {
            byte[] v0 = this.openSession();
            if(v0 == null) {
                this.onPromiseRejected(arg5, "Failed to open session to load license");
                return;
            }

            this.mSessionManager.setDrmId(arg4, v0);
            this.mSessionEventDeferrer = new SessionEventDeferrer(arg4);
            this.mMediaDrm.restoreKeys(arg4.drmId(), arg4.keySetId());
            this.onPromiseResolvedWithSession(arg5, arg4);
            this.mSessionEventDeferrer.fire();
            this.mSessionEventDeferrer = null;
            if(Build$VERSION.SDK_INT >= 23) {
                return;
            }

            this.onSessionKeysChange(arg4, MediaDrmBridge.getDummyKeysInfo(0).toArray(), true, false);
            return;
        }
        catch(NotProvisionedException ) {
            return;
        }
        catch(IllegalStateException ) {
            if(arg4.drmId() == null) {
                this.onPersistentLicenseNoExist(arg5);
                return;
            }

            this.closeSessionNoException(arg4);
            this.mSessionManager.clearPersistentSessionInfo(arg4, new Callback(arg5) {
                public void onResult(Boolean arg3) {
                    if(!arg3.booleanValue()) {
                        Log.w("cr_media", "Failed to clear persistent storage for non-exist license", new Object[0]);
                    }

                    MediaDrmBridge.this.onPersistentLicenseNoExist(this.val$promiseId);
                }

                public void onResult(Object arg1) {
                    this.onResult(((Boolean)arg1));
                }
            });
            return;
        }
    }

    private native void nativeOnMediaCryptoReady(long arg1, MediaCrypto arg2) {
    }

    private native void nativeOnPromiseRejected(long arg1, long arg2, String arg3) {
    }

    private native void nativeOnPromiseResolved(long arg1, long arg2) {
    }

    private native void nativeOnPromiseResolvedWithSession(long arg1, long arg2, byte[] arg3) {
    }

    private native void nativeOnResetDeviceCredentialsCompleted(long arg1, boolean arg2) {
    }

    private native void nativeOnSessionClosed(long arg1, byte[] arg2) {
    }

    private native void nativeOnSessionExpirationUpdate(long arg1, byte[] arg2, long arg3) {
    }

    private native void nativeOnSessionKeysChange(long arg1, byte[] arg2, Object[] arg3, boolean arg4, boolean arg5) {
    }

    private native void nativeOnSessionMessage(long arg1, byte[] arg2, int arg3, byte[] arg4) {
    }

    private native void nativeOnStartProvisioning(long arg1, String arg2, byte[] arg3) {
    }

    private void onMediaCryptoReady(MediaCrypto arg3) {
        if(this.isNativeMediaDrmBridgeValid()) {
            this.nativeOnMediaCryptoReady(this.mNativeMediaDrmBridge, arg3);
        }
    }

    private void onPersistentLicenseNoExist(long arg2) {
        this.onPromiseResolvedWithSession(arg2, SessionId.createNoExistSessionId());
    }

    private void onPromiseRejected(long arg8, String arg10) {
        Log.e("cr_media", "onPromiseRejected: %s", new Object[]{arg10});
        if(this.isNativeMediaDrmBridgeValid()) {
            this.nativeOnPromiseRejected(this.mNativeMediaDrmBridge, arg8, arg10);
        }
    }

    private void onPromiseResolved(long arg3) {
        if(this.isNativeMediaDrmBridgeValid()) {
            this.nativeOnPromiseResolved(this.mNativeMediaDrmBridge, arg3);
        }
    }

    private void onPromiseResolvedWithSession(long arg8, SessionId arg10) {
        if(this.isNativeMediaDrmBridgeValid()) {
            this.nativeOnPromiseResolvedWithSession(this.mNativeMediaDrmBridge, arg8, arg10.emeId());
        }
    }

    private void onResetDeviceCredentialsCompleted(boolean arg3) {
        if(this.isNativeMediaDrmBridgeValid()) {
            this.nativeOnResetDeviceCredentialsCompleted(this.mNativeMediaDrmBridge, arg3);
        }
    }

    private void onSessionClosed(SessionId arg3) {
        if(this.isNativeMediaDrmBridgeValid()) {
            this.nativeOnSessionClosed(this.mNativeMediaDrmBridge, arg3.emeId());
        }
    }

    private void onSessionExpirationUpdate(SessionId arg8, long arg9) {
        if(this.isNativeMediaDrmBridgeValid()) {
            this.nativeOnSessionExpirationUpdate(this.mNativeMediaDrmBridge, arg8.emeId(), arg9);
        }
    }

    private void onSessionKeysChange(SessionId arg9, Object[] arg10, boolean arg11, boolean arg12) {
        if(this.isNativeMediaDrmBridgeValid()) {
            this.nativeOnSessionKeysChange(this.mNativeMediaDrmBridge, arg9.emeId(), arg10, arg11, arg12);
        }
    }

    @TargetApi(value=23) private void onSessionMessage(SessionId arg8, MediaDrm$KeyRequest arg9) {
        if(!this.isNativeMediaDrmBridgeValid()) {
            return;
        }

        int v0 = Build$VERSION.SDK_INT >= 23 ? arg9.getRequestType() : arg9.getDefaultUrl().isEmpty() ^ 1;
        int v5 = v0;
        this.nativeOnSessionMessage(this.mNativeMediaDrmBridge, arg8.emeId(), v5, arg9.getData());
    }

    private byte[] openSession() throws NotProvisionedException {
        byte[] v0 = null;
        try {
            return this.mMediaDrm.openSession().clone();
        }
        catch(MediaDrmException v3) {
            Log.e("cr_media", "Cannot open a new session", new Object[]{v3});
            this.release();
            return v0;
        }
        catch(NotProvisionedException v0_1) {
            throw v0_1;
        }
        catch(RuntimeException v3_1) {
            Log.e("cr_media", "Cannot open a new session", new Object[]{v3_1});
            this.release();
            return v0;
        }
    }

    private void processPendingCreateSessionData() {
        Log.d("cr_media", "processPendingCreateSessionData()");
        while(this.mMediaDrm != null) {
            if(this.mProvisioningPending) {
                return;
            }

            if(this.mPendingCreateSessionDataQueue.isEmpty()) {
                return;
            }

            Object v0 = this.mPendingCreateSessionDataQueue.poll();
            this.createSession(PendingCreateSessionData.access$600(((PendingCreateSessionData)v0)), PendingCreateSessionData.access$700(((PendingCreateSessionData)v0)), PendingCreateSessionData.access$800(((PendingCreateSessionData)v0)), PendingCreateSessionData.access$900(((PendingCreateSessionData)v0)), PendingCreateSessionData.access$400(((PendingCreateSessionData)v0)));
        }
    }

    @CalledByNative private void processProvisionResponse(boolean arg3, byte[] arg4) {
        Log.d("cr_media", "processProvisionResponse()");
        if(this.mMediaDrm == null) {
            return;
        }

        this.mProvisioningPending = false;
        arg3 = arg3 ? this.provideProvisionResponse(arg4) : false;
        if(this.mResetDeviceCredentialsPending) {
            this.onResetDeviceCredentialsCompleted(arg3);
            this.mResetDeviceCredentialsPending = false;
        }

        if((arg3) && (this.mMediaCryptoSession != null || (this.createMediaCrypto()))) {
            if(!this.mOriginSet) {
                this.processPendingCreateSessionData();
                return;
            }

            this.mStorage.onProvisioned(new Callback() {
                public void onResult(Boolean arg3) {
                    if(!arg3.booleanValue()) {
                        Log.e("cr_media", "Failed to initialize storage for origin", new Object[0]);
                        MediaDrmBridge.this.release();
                        return;
                    }

                    MediaDrmBridge.this.processPendingCreateSessionData();
                }

                public void onResult(Object arg1) {
                    this.onResult(((Boolean)arg1));
                }
            });
            return;
        }

        this.release();
    }

    boolean provideProvisionResponse(byte[] arg5) {
        if(arg5 != null) {
            if(arg5.length == 0) {
            }
            else {
                try {
                    this.mMediaDrm.provideProvisionResponse(arg5);
                    return 1;
                }
                catch(IllegalStateException v5) {
                    Log.e("cr_media", "failed to provide provision response", new Object[]{v5});
                }
                catch(DeniedByServerException v5_1) {
                    Log.e("cr_media", "failed to provide provision response", new Object[]{v5_1});
                }

                return 0;
            }
        }

        Log.e("cr_media", "Invalid provision response.", new Object[0]);
        return 0;
    }

    private void release() {
        Iterator v0 = this.mPendingCreateSessionDataQueue.iterator();
        while(v0.hasNext()) {
            this.onPromiseRejected(PendingCreateSessionData.access$400(v0.next()), "Create session aborted.");
        }

        this.mPendingCreateSessionDataQueue.clear();
        ArrayDeque v0_1 = null;
        this.mPendingCreateSessionDataQueue = v0_1;
        Iterator v1 = this.mSessionManager.getAllSessionIds().iterator();
        while(v1.hasNext()) {
            Object v2 = v1.next();
            try {
                this.mMediaDrm.removeKeys(((SessionId)v2).drmId());
            }
            catch(Exception v4) {
                Log.e("cr_media", "removeKeys failed: ", new Object[]{v4});
            }

            this.closeSessionNoException(((SessionId)v2));
            this.onSessionClosed(((SessionId)v2));
        }

        this.mSessionManager = new MediaDrmSessionManager(this.mStorage);
        if(this.mMediaCryptoSession == null) {
            this.onMediaCryptoReady(((MediaCrypto)v0_1));
        }
        else {
            this.closeSessionNoException(this.mMediaCryptoSession);
            this.mMediaCryptoSession = ((SessionId)v0_1);
        }

        if(this.mResetDeviceCredentialsPending) {
            this.mResetDeviceCredentialsPending = false;
            this.onResetDeviceCredentialsCompleted(false);
        }

        if(this.mMediaDrm != null) {
            this.mMediaDrm.release();
            this.mMediaDrm = ((MediaDrm)v0_1);
        }

        if(this.mMediaCrypto != null) {
            this.mMediaCrypto.release();
            this.mMediaCrypto = ((MediaCrypto)v0_1);
        }
    }

    @CalledByNative private void removeSession(byte[] arg9, long arg10) {
        Log.d("cr_media", "removeSession()");
        SessionId v9 = this.getSessionIdByEmeId(arg9);
        if(v9 == null) {
            this.onPromiseRejected(arg10, "Session doesn\'t exist");
            return;
        }

        SessionInfo v0 = this.mSessionManager.get(v9);
        if(v0.keyType() != 2) {
            this.onPromiseRejected(arg10, "Removing temporary session isn\'t implemented");
            return;
        }

        this.mSessionManager.markKeyReleased(v9);
        byte[] v4 = null;
        try {
            MediaDrm$KeyRequest v0_1 = this.getKeyRequest(v9, v4, v0.mimeType(), 3, null);
            if(v0_1 == null) {
                this.onPromiseRejected(arg10, "Fail to generate key release request");
                return;
            }

            this.onPromiseResolved(arg10);
            this.onSessionMessage(v9, v0_1);
        }
        catch(NotProvisionedException ) {
            Log.e("cr_media", "removeSession called on unprovisioned device", new Object[0]);
            this.onPromiseRejected(arg10, "Unknown failure");
        }
    }

    @CalledByNative private void resetDeviceCredentials() {
        if(this.mMediaDrm == null) {
            this.onResetDeviceCredentialsCompleted(false);
            return;
        }

        this.mResetDeviceCredentialsPending = true;
        this.startProvisioning();
    }

    private void savePendingCreateSessionData(byte[] arg12, String arg13, int arg14, HashMap arg15, long arg16) {
        Log.d("cr_media", "savePendingCreateSessionData()");
        this.mPendingCreateSessionDataQueue.offer(new PendingCreateSessionData(arg12, arg13, arg14, arg15, arg16, null));
    }

    private boolean setOrigin(String arg7) {
        Object[] v0_1;
        if(!this.isWidevine()) {
            Log.d("cr_media", "Property origin isn\'t supported");
            return 1;
        }

        int v0 = 2;
        try {
            this.mMediaDrm.setPropertyString("origin", arg7);
            this.mOriginSet = true;
            return 1;
        }
        catch(IllegalStateException v3) {
            v0_1 = new Object[v0];
            v0_1[0] = arg7;
            v0_1[1] = v3;
            Log.e("cr_media", "Failed to set security origin %s", v0_1);
        }
        catch(IllegalArgumentException v3_1) {
            v0_1 = new Object[v0];
            v0_1[0] = arg7;
            v0_1[1] = v3_1;
            Log.e("cr_media", "Failed to set security origin %s", v0_1);
        }

        Log.e("cr_media", "Security origin %s not supported!", new Object[]{arg7});
        return 0;
    }

    private boolean setSecurityLevel(String arg8) {
        Object[] v4_1;
        if(!this.isWidevine()) {
            Log.d("cr_media", "Security level is not supported.");
            return 1;
        }

        String v0 = this.mMediaDrm.getPropertyString("securityLevel");
        int v4 = 2;
        Object[] v5 = new Object[v4];
        v5[0] = v0;
        v5[1] = arg8;
        Log.e("cr_media", "Security level: current %s, new %s", v5);
        if(arg8.equals(v0)) {
            return 1;
        }

        try {
            this.mMediaDrm.setPropertyString("securityLevel", arg8);
            return 1;
        }
        catch(IllegalStateException v0_1) {
            v4_1 = new Object[v4];
            v4_1[0] = arg8;
            v4_1[1] = v0_1;
            Log.e("cr_media", "Failed to set security level %s", v4_1);
        }
        catch(IllegalArgumentException v0_2) {
            v4_1 = new Object[v4];
            v4_1[0] = arg8;
            v4_1[1] = v0_2;
            Log.e("cr_media", "Failed to set security level %s", v4_1);
        }

        Log.e("cr_media", "Security level %s not supported!", new Object[]{arg8});
        return 0;
    }

    @CalledByNative private boolean setServerCertificate(byte[] arg5) {
        if(!this.isWidevine()) {
            Log.d("cr_media", "Setting server certificate is not supported.");
            return 1;
        }

        try {
            this.mMediaDrm.setPropertyByteArray("serviceCertificate", arg5);
            return 1;
        }
        catch(IllegalStateException v5) {
            Log.e("cr_media", "Failed to set server certificate", new Object[]{v5});
        }
        catch(IllegalArgumentException v5_1) {
            Log.e("cr_media", "Failed to set server certificate", new Object[]{v5_1});
        }

        return 0;
    }

    private void startProvisioning() {
        if(this.mProvisioningPending) {
            Log.d("cr_media", "startProvisioning: another provisioning is in progress, returning");
            return;
        }

        Log.d("cr_media", "startProvisioning");
        this.mProvisioningPending = true;
        MediaDrm$ProvisionRequest v0 = this.mMediaDrm.getProvisionRequest();
        if(this.isNativeMediaDrmBridgeValid()) {
            this.nativeOnStartProvisioning(this.mNativeMediaDrmBridge, v0.getDefaultUrl(), v0.getData());
        }
    }

    @CalledByNative private void unprovision() {
        if(this.mMediaDrm == null) {
            return;
        }

        if(!this.mOriginSet) {
            return;
        }

        this.provideProvisionResponse(MediaDrmBridge.UNPROVISION);
    }

    @CalledByNative private void updateSession(byte[] arg12, byte[] arg13, long arg14) {
        Log.d("cr_media", "updateSession()");
        if(this.mMediaDrm == null) {
            this.onPromiseRejected(arg14, "updateSession() called when MediaDrm is null.");
            return;
        }

        SessionId v6 = this.getSessionIdByEmeId(arg12);
        if(v6 == null) {
            this.onPromiseRejected(arg14, "Invalid session in updateSession: " + SessionId.toHexString(arg12));
            return;
        }

        try {
            SessionInfo v8 = this.mSessionManager.get(v6);
            boolean v9 = v8.keyType() == 3 ? true : false;
            byte[] v0 = null;
            if(v9) {
                Log.d("cr_media", "updateSession() for key release");
                this.mMediaDrm.provideKeyResponse(v6.keySetId(), arg13);
            }
            else {
                v0 = this.mMediaDrm.provideKeyResponse(v6.drmId(), arg13);
            }

            arg13 = v0;
            KeyUpdatedCallback v10 = new KeyUpdatedCallback(this, v6, arg14, v9);
            if(v9) {
                this.mSessionManager.clearPersistentSessionInfo(v6, ((Callback)v10));
            }
            else {
                if(v8.keyType() == 2 && arg13 != null && arg13.length > 0) {
                    this.mSessionManager.setKeySetId(v6, arg13, ((Callback)v10));
                    return;
                }

                v10.onResult(Boolean.valueOf(true));
            }

            return;
        }
        catch(IllegalStateException v13_1) {
            Log.e("cr_media", "failed to provide key response", new Object[]{v13_1});
        }
        catch(DeniedByServerException v13_2) {
            Log.e("cr_media", "failed to provide key response", new Object[]{v13_2});
        }
        catch(NotProvisionedException v13_3) {
            Log.e("cr_media", "failed to provide key response", new Object[]{v13_3});
        }

        this.onPromiseRejected(arg14, "Update session failed.");
        this.release();
    }
}

