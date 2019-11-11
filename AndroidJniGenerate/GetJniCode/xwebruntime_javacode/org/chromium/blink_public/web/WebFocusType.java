package org.chromium.blink_public.web;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value=RetentionPolicy.SOURCE) @public interface WebFocusType {
    public static final int BACKWARD = 2;
    public static final int DOWN = 4;
    public static final int FORWARD = 1;
    public static final int LAST = 8;
    public static final int LEFT = 5;
    public static final int MOUSE = 7;
    public static final int NONE = 0;
    public static final int PAGE = 8;
    public static final int RIGHT = 6;
    public static final int UP = 3;

}

