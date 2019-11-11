package org.chromium.device.nfc;

import org.chromium.device.mojom.Nfc;
import org.chromium.device.mojom.NfcProvider;
import org.chromium.mojo.bindings.Interface;
import org.chromium.mojo.bindings.InterfaceRequest;
import org.chromium.mojo.system.MojoException;
import org.chromium.services.service_manager.InterfaceFactory;

public class NfcProviderImpl implements NfcProvider {
    public class Factory implements InterfaceFactory {
        private NfcDelegate mDelegate;

        public Factory(NfcDelegate arg1) {
            super();
            this.mDelegate = arg1;
        }

        public NfcProvider createImpl() {
            return new NfcProviderImpl(this.mDelegate);
        }

        public Interface createImpl() {
            return this.createImpl();
        }
    }

    private static final String TAG = "NfcProviderImpl";
    private NfcDelegate mDelegate;

    public NfcProviderImpl(NfcDelegate arg1) {
        super();
        this.mDelegate = arg1;
    }

    public void close() {
    }

    public void getNfcForHost(int arg4, InterfaceRequest arg5) {
        Nfc.MANAGER.bind(new NfcImpl(arg4, this.mDelegate), arg5);
    }

    public void onConnectionError(MojoException arg1) {
    }
}

