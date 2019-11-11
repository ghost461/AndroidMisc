package org.chromium.network.mojom;

import org.chromium.mojo.bindings.Callbacks$Callback1;
import org.chromium.mojo.bindings.Callbacks$Callback4;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;
import org.chromium.url.mojom.Url;

public interface NetworkServiceClient extends Interface {
    public interface OnAuthRequiredResponse extends Callback1 {
    }

    public interface OnCertificateRequestedResponse extends Callback4 {
    }

    public interface OnSslCertificateErrorResponse extends Callback1 {
    }

    public interface Proxy extends org.chromium.mojo.bindings.Interface$Proxy, NetworkServiceClient {
    }

    public static final Manager MANAGER;

    static {
        NetworkServiceClient.MANAGER = NetworkServiceClient_Internal.MANAGER;
    }

    void onAuthRequired(int arg1, int arg2, int arg3, int arg4, Url arg5, boolean arg6, AuthChallengeInfo arg7, OnAuthRequiredResponse arg8);

    void onCertificateRequested(int arg1, int arg2, int arg3, SslCertRequestInfo arg4, OnCertificateRequestedResponse arg5);

    void onSslCertificateError(int arg1, int arg2, int arg3, int arg4, Url arg5, SslInfo arg6, boolean arg7, OnSslCertificateErrorResponse arg8);
}

