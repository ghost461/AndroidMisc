package org.chromium.ui.base;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value=RetentionPolicy.SOURCE) @public interface PageTransition {
    public static final int AUTO_BOOKMARK = 2;
    public static final int AUTO_SUBFRAME = 3;
    public static final int AUTO_TOPLEVEL = 6;
    public static final int BLOCKED = 0x800000;
    public static final int CHAIN_END = 0x20000000;
    public static final int CHAIN_START = 0x10000000;
    public static final int CLIENT_REDIRECT = 0x40000000;
    public static final int CORE_MASK = 0xFF;
    public static final int FIRST = 0;
    public static final int FORM_SUBMIT = 7;
    public static final int FORWARD_BACK = 0x1000000;
    public static final int FROM_ADDRESS_BAR = 0x2000000;
    public static final int FROM_API = 0x8000000;
    public static final int GENERATED = 5;
    public static final int HOME_PAGE = 0x4000000;
    public static final int IS_REDIRECT_MASK = 0xC0000000;
    public static final int KEYWORD = 9;
    public static final int KEYWORD_GENERATED = 10;
    public static final int LAST_CORE = 10;
    public static final int LINK = 0;
    public static final int MANUAL_SUBFRAME = 4;
    public static final int QUALIFIER_MASK = 0xFFFFFF00;
    public static final int RELOAD = 8;
    public static final int SERVER_REDIRECT = 0x80000000;
    public static final int TYPED = 1;

}

