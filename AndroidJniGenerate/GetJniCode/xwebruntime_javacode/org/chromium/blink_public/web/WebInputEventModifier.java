package org.chromium.blink_public.web;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value=RetentionPolicy.SOURCE) @public interface WebInputEventModifier {
    public static final int ALT_GR_KEY = 0x8000;
    public static final int ALT_KEY = 4;
    public static final int BACK_BUTTON_DOWN = 0x100000;
    public static final int CAPS_LOCK_ON = 0x200;
    public static final int CONTROL_KEY = 2;
    public static final int FN_KEY = 0x10000;
    public static final int FORWARD_BUTTON_DOWN = 0x200000;
    public static final int FROM_DEBUGGER = 0x800000;
    public static final int IS_AUTO_REPEAT = 0x20;
    public static final int IS_COMPATIBILITY_EVENT_FOR_TOUCH = 0x80000;
    public static final int IS_COMPOSING = 0x4000;
    public static final int IS_KEY_PAD = 16;
    public static final int IS_LEFT = 0x800;
    public static final int IS_RIGHT = 0x1000;
    public static final int IS_TOUCH_ACCESSIBILITY = 0x2000;
    public static final int KEY_MODIFIERS = 0x3800F;
    public static final int LEFT_BUTTON_DOWN = 0x40;
    public static final int META_KEY = 8;
    public static final int MIDDLE_BUTTON_DOWN = 0x80;
    public static final int NO_MODIFIERS = 0;
    public static final int NUM_LOCK_ON = 0x400;
    public static final int RELATIVE_MOTION_EVENT = 0x400000;
    public static final int RIGHT_BUTTON_DOWN = 0x100;
    public static final int SCROLL_LOCK_ON = 0x40000;
    public static final int SHIFT_KEY = 1;
    public static final int SYMBOL_KEY = 0x20000;

}

