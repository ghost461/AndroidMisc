package org.chromium.net;

import java.security.cert.CertificateEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="net::android") public class AndroidCertVerifyResult {
    private final List mCertificateChain;
    private final boolean mIsIssuedByKnownRoot;
    private final int mStatus;

    public AndroidCertVerifyResult(int arg1) {
        super();
        this.mStatus = arg1;
        this.mIsIssuedByKnownRoot = false;
        this.mCertificateChain = Collections.emptyList();
    }

    public AndroidCertVerifyResult(int arg1, boolean arg2, List arg3) {
        super();
        this.mStatus = arg1;
        this.mIsIssuedByKnownRoot = arg2;
        this.mCertificateChain = new ArrayList(((Collection)arg3));
    }

    @CalledByNative public byte[][] getCertificateChainEncoded() {
        byte[][] v0 = new byte[this.mCertificateChain.size()][];
        int v2 = 0;
        try {
            while(v2 < this.mCertificateChain.size()) {
                v0[v2] = this.mCertificateChain.get(v2).getEncoded();
                ++v2;
            }
        }
        catch(CertificateEncodingException ) {
            return new byte[0][];
        }

        return v0;
    }

    @CalledByNative public int getStatus() {
        return this.mStatus;
    }

    @CalledByNative public boolean isIssuedByKnownRoot() {
        return this.mIsIssuedByKnownRoot;
    }
}

