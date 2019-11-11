package org.xwalk.core.internal;

public class XWalkPreferencesBridge extends XWalkPreferencesInternal {
    private XWalkCoreBridge coreBridge;
    private Object wrapper;

    public XWalkPreferencesBridge() {
        super();
    }

    public static boolean getBooleanValue(String arg0) {
        return XWalkPreferencesInternal.getBooleanValue(arg0);
    }

    public static int getIntegerValue(String arg0) {
        return XWalkPreferencesInternal.getIntegerValue(arg0);
    }

    public static String getStringValue(String arg0) {
        return XWalkPreferencesInternal.getStringValue(arg0);
    }

    public static boolean getValue(String arg0) {
        return XWalkPreferencesInternal.getValue(arg0);
    }

    public Object getWrapper() {
        return this.wrapper;
    }

    void reflectionInit() {
        this.coreBridge = XWalkCoreBridge.getInstance();
        if(this.coreBridge == null) {
            return;
        }
    }

    public static void setValue(String arg0, int arg1) {
        XWalkPreferencesInternal.setValue(arg0, arg1);
    }

    public static void setValue(String arg0, String arg1) {
        XWalkPreferencesInternal.setValue(arg0, arg1);
    }

    public static void setValue(String arg0, boolean arg1) {
        XWalkPreferencesInternal.setValue(arg0, arg1);
    }
}

