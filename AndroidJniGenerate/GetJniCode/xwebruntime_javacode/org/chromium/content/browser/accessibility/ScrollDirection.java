package org.chromium.content.browser.accessibility;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value=RetentionPolicy.SOURCE) @public interface ScrollDirection {
    public static final int BACKWARD = 1;
    public static final int DOWN = 3;
    public static final int FORWARD = 0;
    public static final int LEFT = 4;
    public static final int RIGHT = 5;
    public static final int UP = 2;

}

