package org.chromium.blink_public.web;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value=RetentionPolicy.SOURCE) @public interface WebTextInputFlags {
    public static final int AUTOCAPITALIZE_CHARACTERS = 0x80;
    public static final int AUTOCAPITALIZE_NONE = 0x40;
    public static final int AUTOCAPITALIZE_SENTENCES = 0x200;
    public static final int AUTOCAPITALIZE_WORDS = 0x100;
    public static final int AUTOCOMPLETE_OFF = 2;
    public static final int AUTOCOMPLETE_ON = 1;
    public static final int AUTOCORRECT_OFF = 8;
    public static final int AUTOCORRECT_ON = 4;
    public static final int HAS_BEEN_PASSWORD_FIELD = 0x1000;
    public static final int HAVE_NEXT_FOCUSABLE_ELEMENT = 0x400;
    public static final int HAVE_PREVIOUS_FOCUSABLE_ELEMENT = 0x800;
    public static final int NONE = 0;
    public static final int SPELLCHECK_OFF = 0x20;
    public static final int SPELLCHECK_ON = 16;

}

