package org.chromium.content_public.common;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value=RetentionPolicy.SOURCE) @public interface BrowserControlsState {
    public static final int BOTH = 3;
    public static final int HIDDEN = 2;
    public static final int LAST = 3;
    public static final int SHOWN = 1;

}

