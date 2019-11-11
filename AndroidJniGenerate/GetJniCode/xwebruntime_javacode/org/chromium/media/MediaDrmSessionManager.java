package org.chromium.media;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import org.chromium.base.ApiCompatibilityUtils;
import org.chromium.base.Callback;

class MediaDrmSessionManager {
    class SessionId {
        private static final char[] HEX_CHAR_LOOKUP;
        private byte[] mDrmId;
        private final byte[] mEmeId;
        private byte[] mKeySetId;

        static {
            SessionId.HEX_CHAR_LOOKUP = "0123456789ABCDEF".toCharArray();
        }

        private SessionId(byte[] arg1, byte[] arg2, byte[] arg3) {
            super();
            this.mEmeId = arg1;
            this.mDrmId = arg2;
            this.mKeySetId = arg3;
        }

        SessionId(byte[] arg1, byte[] arg2, byte[] arg3, org.chromium.media.MediaDrmSessionManager$1 arg4) {
            this(arg1, arg2, arg3);
        }

        static void access$200(SessionId arg0, byte[] arg1) {
            arg0.setDrmId(arg1);
        }

        static void access$300(SessionId arg0, byte[] arg1) {
            arg0.setKeySetId(arg1);
        }

        static SessionId createNoExistSessionId() {
            return SessionId.createTemporarySessionId(new byte[0]);
        }

        static SessionId createPersistentSessionId(byte[] arg3) {
            return new SessionId(ApiCompatibilityUtils.getBytesUtf8(UUID.randomUUID().toString().replace('-', '0')), arg3, null);
        }

        static SessionId createTemporarySessionId(byte[] arg2) {
            return new SessionId(arg2, arg2, null);
        }

        byte[] drmId() {
            return this.mDrmId;
        }

        byte[] emeId() {
            return this.mEmeId;
        }

        boolean isEqual(SessionId arg2) {
            return Arrays.equals(this.mEmeId, arg2.emeId());
        }

        byte[] keySetId() {
            return this.mKeySetId;
        }

        private void setDrmId(byte[] arg1) {
            this.mDrmId = arg1;
        }

        private void setKeySetId(byte[] arg1) {
            this.mKeySetId = arg1;
        }

        static String toHexString(byte[] arg4) {
            StringBuilder v0 = new StringBuilder();
            int v1;
            for(v1 = 0; v1 < arg4.length; ++v1) {
                v0.append(SessionId.HEX_CHAR_LOOKUP[arg4[v1] >>> 4]);
                v0.append(SessionId.HEX_CHAR_LOOKUP[arg4[v1] & 15]);
            }

            return v0.toString();
        }

        String toHexString() {
            return SessionId.toHexString(this.mEmeId);
        }
    }

    class SessionInfo {
        private int mKeyType;
        private final String mMimeType;
        private final SessionId mSessionId;

        static {
        }

        SessionInfo(SessionId arg1, String arg2, int arg3, org.chromium.media.MediaDrmSessionManager$1 arg4) {
            this(arg1, arg2, arg3);
        }

        private SessionInfo(SessionId arg1, String arg2, int arg3) {
            super();
            this.mSessionId = arg1;
            this.mMimeType = arg2;
            this.mKeyType = arg3;
        }

        static SessionId access$100(SessionInfo arg0) {
            return arg0.sessionId();
        }

        static PersistentInfo access$400(SessionInfo arg0) {
            return arg0.toPersistentInfo();
        }

        static void access$500(SessionInfo arg0, int arg1) {
            arg0.setKeyType(arg1);
        }

        static SessionInfo access$600(PersistentInfo arg0) {
            return SessionInfo.fromPersistentInfo(arg0);
        }

        private static SessionInfo fromPersistentInfo(PersistentInfo arg4) {
            return new SessionInfo(new SessionId(arg4.emeId(), null, arg4.keySetId(), null), arg4.mimeType(), 2);
        }

        int keyType() {
            return this.mKeyType;
        }

        String mimeType() {
            return this.mMimeType;
        }

        private SessionId sessionId() {
            return this.mSessionId;
        }

        private void setKeyType(int arg1) {
            this.mKeyType = arg1;
        }

        private PersistentInfo toPersistentInfo() {
            return new PersistentInfo(this.mSessionId.emeId(), this.mSessionId.keySetId(), this.mMimeType);
        }
    }

    private HashMap mDrmSessionInfoMap;
    private HashMap mEmeSessionInfoMap;
    private MediaDrmStorageBridge mStorage;

    static {
    }

    public MediaDrmSessionManager(MediaDrmStorageBridge arg2) {
        super();
        this.mEmeSessionInfoMap = new HashMap();
        this.mDrmSessionInfoMap = new HashMap();
        this.mStorage = arg2;
    }

    static HashMap access$700(MediaDrmSessionManager arg0) {
        return arg0.mEmeSessionInfoMap;
    }

    void clearPersistentSessionInfo(SessionId arg2, Callback arg3) {
        SessionId.access$300(arg2, null);
        this.mStorage.clearInfo(arg2.emeId(), arg3);
    }

    SessionInfo get(SessionId arg2) {
        return this.mEmeSessionInfoMap.get(ByteBuffer.wrap(arg2.emeId()));
    }

    List getAllSessionIds() {
        ArrayList v0 = new ArrayList();
        Iterator v1 = this.mEmeSessionInfoMap.values().iterator();
        while(v1.hasNext()) {
            v0.add(SessionInfo.access$100(v1.next()));
        }

        return ((List)v0);
    }

    SessionId getSessionIdByDrmId(byte[] arg2) {
        return this.getSessionIdFromMap(this.mDrmSessionInfoMap, arg2);
    }

    SessionId getSessionIdByEmeId(byte[] arg2) {
        return this.getSessionIdFromMap(this.mEmeSessionInfoMap, arg2);
    }

    private SessionId getSessionIdFromMap(HashMap arg1, byte[] arg2) {
        Object v1 = arg1.get(ByteBuffer.wrap(arg2));
        if(v1 == null) {
            return null;
        }

        return SessionInfo.access$100(((SessionInfo)v1));
    }

    void load(byte[] arg3, Callback arg4) {
        this.mStorage.loadInfo(arg3, new Callback(arg4) {
            public void onResult(Object arg1) {
                this.onResult(((PersistentInfo)arg1));
            }

            public void onResult(PersistentInfo arg3) {
                if(arg3 == null) {
                    this.val$callback.onResult(null);
                    return;
                }

                SessionInfo v0 = SessionInfo.access$600(arg3);
                MediaDrmSessionManager.this.mEmeSessionInfoMap.put(ByteBuffer.wrap(arg3.emeId()), v0);
                this.val$callback.onResult(SessionInfo.access$100(v0));
            }
        });
    }

    void markKeyReleased(SessionId arg2) {
        SessionInfo.access$500(this.get(arg2), 3);
    }

    void put(SessionId arg3, String arg4, int arg5) {
        SessionInfo v0 = new SessionInfo(arg3, arg4, arg5, null);
        this.mEmeSessionInfoMap.put(ByteBuffer.wrap(arg3.emeId()), v0);
        if(arg3.drmId() != null) {
            this.mDrmSessionInfoMap.put(ByteBuffer.wrap(arg3.drmId()), v0);
        }
    }

    void remove(SessionId arg3) {
        this.get(arg3);
        this.mEmeSessionInfoMap.remove(ByteBuffer.wrap(arg3.emeId()));
        if(arg3.drmId() != null) {
            this.mDrmSessionInfoMap.remove(ByteBuffer.wrap(arg3.drmId()));
        }
    }

    void setDrmId(SessionId arg2, byte[] arg3) {
        SessionInfo v0 = this.get(arg2);
        SessionId.access$200(arg2, arg3);
        this.mDrmSessionInfoMap.put(ByteBuffer.wrap(arg3), v0);
    }

    void setKeySetId(SessionId arg1, byte[] arg2, Callback arg3) {
        SessionId.access$300(arg1, arg2);
        this.mStorage.saveInfo(SessionInfo.access$400(this.get(arg1)), arg3);
    }
}

