package org.chromium.media.mojom;

import org.chromium.mojo.bindings.Callbacks$Callback1;
import org.chromium.mojo.bindings.Callbacks$Callback2;
import org.chromium.mojo.bindings.Callbacks$Callback3;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;
import org.chromium.url.mojom.Origin;

public interface ContentDecryptionModule extends Interface {
    public interface CloseSessionResponse extends Callback1 {
    }

    public interface CreateSessionAndGenerateRequestResponse extends Callback2 {
    }

    public interface GetStatusForPolicyResponse extends Callback2 {
    }

    public interface InitializeResponse extends Callback3 {
    }

    public interface LoadSessionResponse extends Callback2 {
    }

    public interface Proxy extends ContentDecryptionModule, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public interface RemoveSessionResponse extends Callback1 {
    }

    public interface SetServerCertificateResponse extends Callback1 {
    }

    public interface UpdateSessionResponse extends Callback1 {
    }

    public static final Manager MANAGER;

    static {
        ContentDecryptionModule.MANAGER = ContentDecryptionModule_Internal.MANAGER;
    }

    void closeSession(String arg1, CloseSessionResponse arg2);

    void createSessionAndGenerateRequest(int arg1, int arg2, byte[] arg3, CreateSessionAndGenerateRequestResponse arg4);

    void getStatusForPolicy(int arg1, GetStatusForPolicyResponse arg2);

    void initialize(String arg1, Origin arg2, CdmConfig arg3, InitializeResponse arg4);

    void loadSession(int arg1, String arg2, LoadSessionResponse arg3);

    void removeSession(String arg1, RemoveSessionResponse arg2);

    void setClient(ContentDecryptionModuleClient arg1);

    void setServerCertificate(byte[] arg1, SetServerCertificateResponse arg2);

    void updateSession(String arg1, byte[] arg2, UpdateSessionResponse arg3);
}

