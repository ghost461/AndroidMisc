package org.xwalk.core.internal;

import java.security.Principal;
import java.security.PrivateKey;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.List;
import org.chromium.base.ThreadUtils;

@XWalkAPI(createInternally=true, impl=ClientCertRequestInternal.class) public class ClientCertRequestHandlerInternal implements ClientCertRequestInternal {
    private static final String TAG = "ClientCertRequestHandlerInternal";
    private XWalkContentsClientBridge mContentsClient;
    private String mHost;
    private int mId;
    private boolean mIsCalled;
    private String[] mKeyTypes;
    private int mPort;
    private Principal[] mPrincipals;

    ClientCertRequestHandlerInternal() {
        super();
        this.mKeyTypes = new String[0];
        this.mPrincipals = new Principal[0];
        this.mId = -1;
        this.mHost = "";
        this.mPort = -1;
        this.mContentsClient = null;
    }

    ClientCertRequestHandlerInternal(XWalkContentsClientBridge arg3, int arg4, String[] arg5, Principal[] arg6, String arg7, int arg8) {
        super();
        this.mKeyTypes = new String[0];
        this.mPrincipals = new Principal[0];
        this.mId = arg4;
        this.mKeyTypes = arg5;
        this.mPrincipals = arg6;
        this.mHost = arg7;
        this.mPort = arg8;
        this.mContentsClient = arg3;
    }

    static void access$000(ClientCertRequestHandlerInternal arg0, PrivateKey arg1, X509Certificate[] arg2) {
        arg0.proceedOnUiThread(arg1, arg2);
    }

    static void access$100(ClientCertRequestHandlerInternal arg0) {
        arg0.ignoreOnUiThread();
    }

    static void access$200(ClientCertRequestHandlerInternal arg0) {
        arg0.cancelOnUiThread();
    }

    @XWalkAPI public void cancel() {
        ThreadUtils.runOnUiThread(new Runnable() {
            public void run() {
                ClientCertRequestHandlerInternal.this.cancelOnUiThread();
            }
        });
    }

    private void cancelOnUiThread() {
        this.checkIfCalled();
        this.mContentsClient.mLookupTable.deny(this.mHost, this.mPort);
        this.provideResponse(null, null);
    }

    private void checkIfCalled() {
        if(this.mIsCalled) {
            throw new IllegalStateException("The callback was already called.");
        }

        this.mIsCalled = true;
    }

    @XWalkAPI public String getHost() {
        return this.mHost;
    }

    @XWalkAPI public String[] getKeyTypes() {
        return this.mKeyTypes;
    }

    @XWalkAPI public int getPort() {
        return this.mPort;
    }

    @XWalkAPI public Principal[] getPrincipals() {
        return this.mPrincipals;
    }

    @XWalkAPI public void ignore() {
        ThreadUtils.runOnUiThread(new Runnable() {
            public void run() {
                ClientCertRequestHandlerInternal.this.ignoreOnUiThread();
            }
        });
    }

    private void ignoreOnUiThread() {
        this.checkIfCalled();
        this.provideResponse(null, null);
    }

    @XWalkAPI public void proceed(PrivateKey arg2, List arg3) {
        ThreadUtils.runOnUiThread(new Runnable(arg3, arg2) {
            public void run() {
                X509Certificate[] v0_1;
                if(this.val$chain != null) {
                    Object[] v0 = this.val$chain.toArray(new X509Certificate[this.val$chain.size()]);
                }
                else {
                    v0_1 = null;
                }

                ClientCertRequestHandlerInternal.this.proceedOnUiThread(this.val$privateKey, v0_1);
            }
        });
    }

    private void proceedOnUiThread(PrivateKey arg5, X509Certificate[] arg6) {
        this.checkIfCalled();
        PrivateKey v0 = null;
        if(arg5 != null && arg6 != null) {
            if(arg6.length == 0) {
            }
            else {
                byte[][] v1 = new byte[arg6.length][];
                int v2 = 0;
                try {
                    while(v2 < arg6.length) {
                        v1[v2] = arg6[v2].getEncoded();
                        ++v2;
                    }
                }
                catch(CertificateEncodingException v5) {
                    Log.w("ClientCertRequestHandlerInternal", "Could not retrieve encoded certificate chain: " + v5);
                    this.provideResponse(v0, ((byte[][])v0));
                    return;
                }

                this.mContentsClient.mLookupTable.allow(this.mHost, this.mPort, arg5, v1);
                this.provideResponse(arg5, v1);
                return;
            }
        }

        Log.w("ClientCertRequestHandlerInternal", "Empty client certificate chain?");
        this.provideResponse(v0, ((byte[][])v0));
    }

    private void provideResponse(PrivateKey arg3, byte[][] arg4) {
        this.mContentsClient.provideClientCertificateResponse(this.mId, arg4, arg3);
    }
}

