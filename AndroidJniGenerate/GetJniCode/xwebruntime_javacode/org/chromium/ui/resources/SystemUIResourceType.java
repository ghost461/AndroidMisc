package org.chromium.ui.resources;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value=RetentionPolicy.SOURCE) @public interface SystemUIResourceType {
    public static final int OVERSCROLL_EDGE = 0;
    public static final int OVERSCROLL_GLOW = 1;
    public static final int OVERSCROLL_GLOW_L = 2;

}

