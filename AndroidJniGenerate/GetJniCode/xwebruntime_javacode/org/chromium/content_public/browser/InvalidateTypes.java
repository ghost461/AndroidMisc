package org.chromium.content_public.browser;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value=RetentionPolicy.SOURCE) @public interface InvalidateTypes {
    public static final int ALL = 15;
    public static final int LOAD = 4;
    public static final int TAB = 2;
    public static final int TITLE = 8;
    public static final int URL = 1;

}

