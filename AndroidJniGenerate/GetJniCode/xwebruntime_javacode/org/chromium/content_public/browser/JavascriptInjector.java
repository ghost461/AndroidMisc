package org.chromium.content_public.browser;

import java.util.Map;

public interface JavascriptInjector {
    void addPossiblyUnsafeInterface(Object arg1, String arg2, Class arg3);

    Map getInterfaces();

    void removeInterface(String arg1);

    void setAllowInspection(boolean arg1);
}

