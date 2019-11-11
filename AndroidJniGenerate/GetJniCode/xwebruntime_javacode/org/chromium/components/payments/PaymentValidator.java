package org.chromium.components.payments;

import java.nio.ByteBuffer;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.payments.mojom.PaymentDetails;

@JNINamespace(value="payments") public class PaymentValidator {
    public PaymentValidator() {
        super();
    }

    private static native boolean nativeValidatePaymentDetailsAndroid(ByteBuffer arg0) {
    }

    public static boolean validatePaymentDetails(PaymentDetails arg0) {
        if(arg0 == null) {
            return 0;
        }

        return PaymentValidator.nativeValidatePaymentDetailsAndroid(arg0.serialize());
    }
}

