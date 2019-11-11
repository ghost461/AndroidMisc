package org.xwalk.core.internal;

import java.security.PrivateKey;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ClientCertLookupTable {
    public class Cert {
        byte[][] mCertChain;
        PrivateKey mPrivateKey;

        public Cert(PrivateKey arg4, byte[][] arg5) {
            super();
            this.mPrivateKey = arg4;
            byte[][] v4 = new byte[arg5.length][];
            int v0;
            for(v0 = 0; v0 < arg5.length; ++v0) {
                v4[v0] = Arrays.copyOf(arg5[v0], arg5[v0].length);
            }

            this.mCertChain = v4;
        }
    }

    private final Map mCerts;
    private final Set mDenieds;

    public ClientCertLookupTable() {
        super();
        this.mCerts = new HashMap();
        this.mDenieds = new HashSet();
    }

    public void allow(String arg2, int arg3, PrivateKey arg4, byte[][] arg5) {
        arg2 = ClientCertLookupTable.hostAndPort(arg2, arg3);
        this.mCerts.put(arg2, new Cert(arg4, arg5));
        this.mDenieds.remove(arg2);
    }

    public void clear() {
        this.mCerts.clear();
        this.mDenieds.clear();
    }

    public void deny(String arg1, int arg2) {
        arg1 = ClientCertLookupTable.hostAndPort(arg1, arg2);
        this.mCerts.remove(arg1);
        this.mDenieds.add(arg1);
    }

    public Cert getCertData(String arg2, int arg3) {
        return this.mCerts.get(ClientCertLookupTable.hostAndPort(arg2, arg3));
    }

    private static String hostAndPort(String arg1, int arg2) {
        return arg1 + ":" + arg2;
    }

    public boolean isDenied(String arg2, int arg3) {
        return this.mDenieds.contains(ClientCertLookupTable.hostAndPort(arg2, arg3));
    }
}

