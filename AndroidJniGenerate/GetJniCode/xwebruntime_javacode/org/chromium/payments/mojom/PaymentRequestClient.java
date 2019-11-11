package org.chromium.payments.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface PaymentRequestClient extends Interface {
    public interface Proxy extends org.chromium.mojo.bindings.Interface$Proxy, PaymentRequestClient {
    }

    public static final Manager MANAGER;

    static {
        PaymentRequestClient.MANAGER = PaymentRequestClient_Internal.MANAGER;
    }

    void onAbort(boolean arg1);

    void onCanMakePayment(int arg1);

    void onComplete();

    void onError(int arg1);

    void onPaymentResponse(PaymentResponse arg1);

    void onShippingAddressChange(PaymentAddress arg1);

    void onShippingOptionChange(String arg1);

    void warnNoFavicon();
}

