package org.chromium.gpu.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class VpxDecodeVendors {
    private static final boolean IS_EXTENSIBLE = false;
    public static final int VPX_VENDOR_ALL = 3;
    public static final int VPX_VENDOR_AMD = 2;
    public static final int VPX_VENDOR_MICROSOFT = 1;
    public static final int VPX_VENDOR_NONE;

    private VpxDecodeVendors() {
        super();
    }

    public static boolean isKnownValue(int arg0) {
        switch(arg0) {
            case 0: 
            case 1: 
            case 2: 
            case 3: {
                return 1;
            }
        }

        return 0;
    }

    public static void validate(int arg1) {
        if(VpxDecodeVendors.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

