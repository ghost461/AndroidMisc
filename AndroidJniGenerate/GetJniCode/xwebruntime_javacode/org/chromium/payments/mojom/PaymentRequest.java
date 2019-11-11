package org.chromium.payments.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface PaymentRequest extends Interface {
    public interface Proxy extends org.chromium.mojo.bindings.Interface$Proxy, PaymentRequest {
    }

    public static final Manager MANAGER;

    static {
        PaymentRequest.MANAGER = PaymentRequest_Internal.MANAGER;
    }

    void abort();

    void canMakePayment();

    void complete(int arg1);

    void init(PaymentRequestClient arg1, PaymentMethodData[] arg2, PaymentDetails arg3, PaymentOptions arg4);

    void noUpdatedPaymentDetails();

    void show(boolean arg1);

    void updateWith(PaymentDetails arg1);
}

