package org.chromium.device.nfc;

import org.chromium.device.mojom.NfcMessage;
import org.chromium.device.mojom.NfcRecord;

public final class NfcMessageValidator {
    public NfcMessageValidator() {
        super();
    }

    public static boolean isValid(NfcMessage arg3) {
        if(arg3 != null && arg3.data != null) {
            if(arg3.data.length == 0) {
            }
            else {
                int v1 = 0;
                while(true) {
                    if(v1 >= arg3.data.length) {
                        return 1;
                    }
                    else if(!NfcMessageValidator.isValid(arg3.data[v1])) {
                        return 0;
                    }
                    else {
                        ++v1;
                        continue;
                    }

                    return 0;
                }

                return 1;
            }
        }

        return 0;
    }

    private static boolean isValid(NfcRecord arg3) {
        boolean v0 = false;
        if(arg3 == null) {
            return 0;
        }

        if(arg3.recordType == 0) {
            return 1;
        }

        if(arg3.data != null && arg3.mediaType != null && !arg3.mediaType.isEmpty()) {
            v0 = true;
        }

        return v0;
    }
}

