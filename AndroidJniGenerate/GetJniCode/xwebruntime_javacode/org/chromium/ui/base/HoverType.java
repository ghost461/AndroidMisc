package org.chromium.ui.base;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value=RetentionPolicy.SOURCE) @public interface HoverType {
    public static final int FIRST = 1;
    public static final int HOVER = 2;
    public static final int LAST = 2;
    public static final int NONE = 1;

}

