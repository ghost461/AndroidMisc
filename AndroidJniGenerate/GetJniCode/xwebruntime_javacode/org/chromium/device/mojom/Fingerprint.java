package org.chromium.device.mojom;

import org.chromium.mojo.bindings.Callbacks$Callback1;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface Fingerprint extends Interface {
    public interface CancelCurrentEnrollSessionResponse extends Callback1 {
    }

    public interface DestroyAllRecordsResponse extends Callback1 {
    }

    public interface EndCurrentAuthSessionResponse extends Callback1 {
    }

    public interface GetRecordsForUserResponse extends Callback1 {
    }

    public interface Proxy extends Fingerprint, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public interface RemoveRecordResponse extends Callback1 {
    }

    public interface RequestRecordLabelResponse extends Callback1 {
    }

    public interface RequestTypeResponse extends Callback1 {
    }

    public interface SetRecordLabelResponse extends Callback1 {
    }

    public static final Manager MANAGER;

    static {
        Fingerprint.MANAGER = Fingerprint_Internal.MANAGER;
    }

    void addFingerprintObserver(FingerprintObserver arg1);

    void cancelCurrentEnrollSession(CancelCurrentEnrollSessionResponse arg1);

    void destroyAllRecords(DestroyAllRecordsResponse arg1);

    void endCurrentAuthSession(EndCurrentAuthSessionResponse arg1);

    void getRecordsForUser(String arg1, GetRecordsForUserResponse arg2);

    void removeRecord(String arg1, RemoveRecordResponse arg2);

    void requestRecordLabel(String arg1, RequestRecordLabelResponse arg2);

    void requestType(RequestTypeResponse arg1);

    void setRecordLabel(String arg1, String arg2, SetRecordLabelResponse arg3);

    void startAuthSession();

    void startEnrollSession(String arg1, String arg2);
}

