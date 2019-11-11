package org.xwalk.core.internal;

@XWalkAPI(createInternally=true) public class XWalkHitTestResultInternal {
    @XWalkAPI public enum type {
        public static final enum type ANCHOR_TYPE;
        public static final enum type EDIT_TEXT_TYPE;
        public static final enum type EMAIL_TYPE;
        public static final enum type GEO_TYPE;
        public static final enum type IMAGE_ANCHOR_TYPE;
        public static final enum type IMAGE_TYPE;
        public static final enum type PHONE_TYPE;
        public static final enum type SRC_ANCHOR_TYPE;
        public static final enum type SRC_IMAGE_ANCHOR_TYPE;
        public static final enum type UNKNOWN_TYPE;

        static {
            type.UNKNOWN_TYPE = new type("UNKNOWN_TYPE", 0);
            type.ANCHOR_TYPE = new type("ANCHOR_TYPE", 1);
            type.PHONE_TYPE = new type("PHONE_TYPE", 2);
            type.GEO_TYPE = new type("GEO_TYPE", 3);
            type.EMAIL_TYPE = new type("EMAIL_TYPE", 4);
            type.IMAGE_TYPE = new type("IMAGE_TYPE", 5);
            type.IMAGE_ANCHOR_TYPE = new type("IMAGE_ANCHOR_TYPE", 6);
            type.SRC_ANCHOR_TYPE = new type("SRC_ANCHOR_TYPE", 7);
            type.SRC_IMAGE_ANCHOR_TYPE = new type("SRC_IMAGE_ANCHOR_TYPE", 8);
            type.EDIT_TEXT_TYPE = new type("EDIT_TEXT_TYPE", 9);
            type.$VALUES = new type[]{type.UNKNOWN_TYPE, type.ANCHOR_TYPE, type.PHONE_TYPE, type.GEO_TYPE, type.EMAIL_TYPE, type.IMAGE_TYPE, type.IMAGE_ANCHOR_TYPE, type.SRC_ANCHOR_TYPE, type.SRC_IMAGE_ANCHOR_TYPE, type.EDIT_TEXT_TYPE};
        }

        private type(String arg1, int arg2) {
            super(arg1, arg2);
        }

        public static type valueOf(String arg1) {
            return Enum.valueOf(type.class, arg1);
        }

        public static type[] values() {
            return type.$VALUES.clone();
        }
    }

    private String mExtra;
    private int mType;

    public XWalkHitTestResultInternal() {
        super();
        this.mType = 0;
    }

    @XWalkAPI public String getExtra() {
        return this.mExtra;
    }

    @XWalkAPI public type getType() {
        type v0;
        switch(this.mType) {
            case 0: {
                v0 = type.UNKNOWN_TYPE;
                break;
            }
            case 1: {
                v0 = type.ANCHOR_TYPE;
                break;
            }
            case 2: {
                v0 = type.PHONE_TYPE;
                break;
            }
            case 3: {
                v0 = type.GEO_TYPE;
                break;
            }
            case 4: {
                v0 = type.EMAIL_TYPE;
                break;
            }
            case 5: {
                v0 = type.IMAGE_TYPE;
                break;
            }
            case 6: {
                v0 = type.IMAGE_ANCHOR_TYPE;
                break;
            }
            case 7: {
                v0 = type.SRC_ANCHOR_TYPE;
                break;
            }
            case 8: {
                v0 = type.SRC_IMAGE_ANCHOR_TYPE;
                break;
            }
            case 9: {
                v0 = type.EDIT_TEXT_TYPE;
                break;
            }
            default: {
                v0 = type.UNKNOWN_TYPE;
                break;
            }
        }

        return v0;
    }

    public void setExtra(String arg1) {
        this.mExtra = arg1;
    }

    public void setType(int arg1) {
        this.mType = arg1;
    }
}

