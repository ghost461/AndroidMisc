package org.chromium.blink.mojom;

public final class WebSandboxFlags {
    public static final int AUTOMATIC_FEATURES = 0x80;
    public static final int DOCUMENT_DOMAIN = 0x200;
    public static final int DOWNLOADS = 0x8000;
    public static final int FORMS = 8;
    private static final boolean IS_EXTENSIBLE = true;
    public static final int MODALS = 0x1000;
    public static final int NAVIGATION = 1;
    public static final int NONE = 0;
    public static final int ORIENTATION_LOCK = 0x400;
    public static final int ORIGIN = 4;
    public static final int PLUGINS = 2;
    public static final int POINTER_LOCK = 0x100;
    public static final int POPUPS = 0x40;
    public static final int PRESENTATION_CONTROLLER = 0x2000;
    public static final int PROPAGATES_TO_AUXILIARY_BROWSING_CONTEXTS = 0x800;
    public static final int SCRIPTS = 16;
    public static final int TOP_NAVIGATION = 0x20;
    public static final int TOP_NAVIGATION_BY_USER_ACTIVATION = 0x4000;

    private WebSandboxFlags() {
        super();
    }

    public static boolean isKnownValue(int arg0) {
        switch(arg0) {
            case 0: 
            case 1: 
            case 2: 
            case 4: 
            case 8: 
            case 16: 
            case 32: 
            case 64: 
            case 128: 
            case 256: 
            case 512: 
            case 1024: 
            case 2048: 
            case 4096: 
            case 8192: 
            case 16384: 
            case 32768: {
                return 1;
            }
        }

        return 0;
    }

    public static void validate(int arg0) {
    }
}

