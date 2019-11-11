package org.xwalk.core.internal;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map$Entry;

@XWalkAPI(noInstance=true) public class XWalkPreferencesInternal {
    interface KeyValueChangeListener {
        void onKeyValueChanged(String arg1, PreferenceValue arg2);
    }

    class PreferenceValue {
        static final int PREFERENCE_TYPE_BOOLEAN = 1;
        static final int PREFERENCE_TYPE_INTEGER = 2;
        static final int PREFERENCE_TYPE_STRING = 3;
        int mType;
        Object mValue;

        PreferenceValue(boolean arg2) {
            super();
            this.mType = 1;
            this.mValue = Boolean.valueOf(arg2);
        }

        PreferenceValue(String arg2) {
            super();
            this.mType = 3;
            this.mValue = arg2;
        }

        PreferenceValue(int arg2) {
            super();
            this.mType = 2;
            this.mValue = Integer.valueOf(arg2);
        }

        boolean getBooleanValue() {
            if(this.mType != 1) {
                return 0;
            }

            return this.mValue.booleanValue();
        }

        int getIntegerValue() {
            if(this.mType != 2) {
                return -1;
            }

            return this.mValue.intValue();
        }

        String getStringValue() {
            if(this.mType != 3) {
                return null;
            }

            return this.mValue;
        }

        int getType() {
            return this.mType;
        }
    }

    @XWalkAPI public static final String ALLOW_UNIVERSAL_ACCESS_FROM_FILE = "allow-universal-access-from-file";
    @XWalkAPI public static final String ANIMATABLE_XWALK_VIEW = "animatable-xwalk-view";
    @XWalkAPI public static final String ENABLE_EXTENSIONS = "enable-extensions";
    @XWalkAPI public static final String ENABLE_JAVASCRIPT = "enable-javascript";
    @XWalkAPI public static final String ENABLE_THEME_COLOR = "enable-theme-color";
    @XWalkAPI public static final String JAVASCRIPT_CAN_OPEN_WINDOW = "javascript-can-open-window";
    @XWalkAPI public static final String PROFILE_NAME = "profile-name";
    @XWalkAPI public static final String REMOTE_DEBUGGING = "remote-debugging";
    @XWalkAPI public static final String SPATIAL_NAVIGATION = "enable-spatial-navigation";
    @XWalkAPI public static final String SUPPORT_MULTIPLE_WINDOWS = "support-multiple-windows";
    private static final String TAG = "XWalkPreferences";
    public static final String XWEBSDK_VERSION = "xwebsdk-version";
    public static final String XWEB_VERSION = "xweb-version";
    private static ArrayList sListeners;
    private static HashMap sPrefMap;
    private static ReferenceQueue sRefQueue;

    static {
        XWalkPreferencesInternal.sPrefMap = new HashMap();
        XWalkPreferencesInternal.sListeners = new ArrayList();
        XWalkPreferencesInternal.sRefQueue = new ReferenceQueue();
        XWalkPreferencesInternal.sPrefMap.put("remote-debugging", new PreferenceValue(false));
        XWalkPreferencesInternal.sPrefMap.put("animatable-xwalk-view", new PreferenceValue(true));
        XWalkPreferencesInternal.sPrefMap.put("enable-javascript", new PreferenceValue(true));
        XWalkPreferencesInternal.sPrefMap.put("javascript-can-open-window", new PreferenceValue(true));
        XWalkPreferencesInternal.sPrefMap.put("allow-universal-access-from-file", new PreferenceValue(false));
        XWalkPreferencesInternal.sPrefMap.put("support-multiple-windows", new PreferenceValue(false));
        XWalkPreferencesInternal.sPrefMap.put("enable-extensions", new PreferenceValue(false));
        XWalkPreferencesInternal.sPrefMap.put("profile-name", new PreferenceValue("Default"));
        XWalkPreferencesInternal.sPrefMap.put("enable-theme-color", new PreferenceValue(true));
        XWalkPreferencesInternal.sPrefMap.put("xweb-version", new PreferenceValue(""));
        XWalkPreferencesInternal.sPrefMap.put("xwebsdk-version", new PreferenceValue(""));
    }

    public XWalkPreferencesInternal() {
        super();
    }

    private static void checkKey(String arg3) throws RuntimeException {
        XWalkPreferencesInternal.removeEnqueuedReference();
        if(!XWalkPreferencesInternal.sPrefMap.containsKey(arg3)) {
            StringBuilder v1 = new StringBuilder();
            v1.append("Warning: the preference key ");
            v1.append(arg3);
            v1.append(" is not supported by Crosswalk.");
            throw new RuntimeException(v1.toString());
        }
    }

    @XWalkAPI public static boolean getBooleanValue(String arg2) throws RuntimeException {
        boolean v2_1;
        Class v0 = XWalkPreferencesInternal.class;
        __monitor_enter(v0);
        try {
            XWalkPreferencesInternal.checkKey(arg2);
            v2_1 = XWalkPreferencesInternal.sPrefMap.get(arg2).getBooleanValue();
        }
        catch(Throwable v2) {
            __monitor_exit(v0);
            throw v2;
        }

        __monitor_exit(v0);
        return v2_1;
    }

    @XWalkAPI public static int getIntegerValue(String arg2) throws RuntimeException {
        int v2_1;
        Class v0 = XWalkPreferencesInternal.class;
        __monitor_enter(v0);
        try {
            XWalkPreferencesInternal.checkKey(arg2);
            v2_1 = XWalkPreferencesInternal.sPrefMap.get(arg2).getIntegerValue();
        }
        catch(Throwable v2) {
            __monitor_exit(v0);
            throw v2;
        }

        __monitor_exit(v0);
        return v2_1;
    }

    @XWalkAPI public static String getStringValue(String arg2) throws RuntimeException {
        Class v0 = XWalkPreferencesInternal.class;
        __monitor_enter(v0);
        try {
            XWalkPreferencesInternal.checkKey(arg2);
            arg2 = XWalkPreferencesInternal.sPrefMap.get(arg2).getStringValue();
        }
        catch(Throwable v2) {
            __monitor_exit(v0);
            throw v2;
        }

        __monitor_exit(v0);
        return arg2;
    }

    @XWalkAPI public static boolean getValue(String arg2) throws RuntimeException {
        boolean v2_1;
        Class v0 = XWalkPreferencesInternal.class;
        __monitor_enter(v0);
        try {
            XWalkPreferencesInternal.checkKey(arg2);
            v2_1 = XWalkPreferencesInternal.sPrefMap.get(arg2).getBooleanValue();
        }
        catch(Throwable v2) {
            __monitor_exit(v0);
            throw v2;
        }

        __monitor_exit(v0);
        return v2_1;
    }

    static void load(KeyValueChangeListener arg4) {
        Class v0 = XWalkPreferencesInternal.class;
        __monitor_enter(v0);
        try {
            Iterator v1 = XWalkPreferencesInternal.sPrefMap.entrySet().iterator();
            while(v1.hasNext()) {
                Object v2 = v1.next();
                arg4.onKeyValueChanged(((Map$Entry)v2).getKey(), ((Map$Entry)v2).getValue());
            }

            XWalkPreferencesInternal.registerListener(arg4);
        }
        catch(Throwable v4) {
            goto label_16;
        }

        __monitor_exit(v0);
        return;
    label_16:
        __monitor_exit(v0);
        throw v4;
    }

    private static void onKeyValueChanged(String arg2, PreferenceValue arg3) {
        Iterator v0 = XWalkPreferencesInternal.sListeners.iterator();
        while(v0.hasNext()) {
            Object v1 = v0.next().get();
            if(v1 == null) {
                continue;
            }

            ((KeyValueChangeListener)v1).onKeyValueChanged(arg2, arg3);
        }
    }

    private static void registerListener(KeyValueChangeListener arg3) {
        Class v0 = XWalkPreferencesInternal.class;
        __monitor_enter(v0);
        try {
            XWalkPreferencesInternal.removeEnqueuedReference();
            XWalkPreferencesInternal.sListeners.add(new WeakReference(arg3, XWalkPreferencesInternal.sRefQueue));
        }
        catch(Throwable v3) {
            __monitor_exit(v0);
            throw v3;
        }

        __monitor_exit(v0);
    }

    private static void removeEnqueuedReference() {
        while(true) {
            Reference v0 = XWalkPreferencesInternal.sRefQueue.poll();
            if(v0 == null) {
                return;
            }

            XWalkPreferencesInternal.sListeners.remove(v0);
        }
    }

    @XWalkAPI(reservable=true) public static void setValue(String arg3, int arg4) throws RuntimeException {
        Class v0 = XWalkPreferencesInternal.class;
        __monitor_enter(v0);
        try {
            XWalkPreferencesInternal.checkKey(arg3);
            if(arg3 == "animatable-xwalk-view" && !XWalkPreferencesInternal.sListeners.isEmpty()) {
                Log.d("XWalkPreferences", "ANIMATABLE_XWALK_VIEW is not effective to existing XWalkView objects");
            }

            if(XWalkPreferencesInternal.sPrefMap.get(arg3).getIntegerValue() != arg4) {
                PreferenceValue v1 = new PreferenceValue(arg4);
                XWalkPreferencesInternal.sPrefMap.put(arg3, v1);
                XWalkPreferencesInternal.onKeyValueChanged(arg3, v1);
            }
        }
        catch(Throwable v3) {
            __monitor_exit(v0);
            throw v3;
        }

        __monitor_exit(v0);
    }

    @XWalkAPI(reservable=true) public static void setValue(String arg3, String arg4) throws RuntimeException {
        Class v0 = XWalkPreferencesInternal.class;
        __monitor_enter(v0);
        try {
            XWalkPreferencesInternal.checkKey(arg3);
            if(arg3 == "animatable-xwalk-view" && !XWalkPreferencesInternal.sListeners.isEmpty()) {
                Log.d("XWalkPreferences", "ANIMATABLE_XWALK_VIEW is not effective to existing XWalkView objects");
            }

            if(arg4 != null && !arg4.equals(XWalkPreferencesInternal.sPrefMap.get(arg3).getStringValue())) {
                PreferenceValue v1 = new PreferenceValue(arg4);
                XWalkPreferencesInternal.sPrefMap.put(arg3, v1);
                XWalkPreferencesInternal.onKeyValueChanged(arg3, v1);
            }
        }
        catch(Throwable v3) {
            __monitor_exit(v0);
            throw v3;
        }

        __monitor_exit(v0);
    }

    @XWalkAPI(reservable=true) public static void setValue(String arg3, boolean arg4) throws RuntimeException {
        Class v0 = XWalkPreferencesInternal.class;
        __monitor_enter(v0);
        try {
            XWalkPreferencesInternal.checkKey(arg3);
            if(arg3 == "animatable-xwalk-view" && !XWalkPreferencesInternal.sListeners.isEmpty()) {
                Log.d("XWalkPreferences", "ANIMATABLE_XWALK_VIEW is not effective to existing XWalkView objects");
            }

            if(XWalkPreferencesInternal.sPrefMap.get(arg3).getBooleanValue() != arg4) {
                PreferenceValue v1 = new PreferenceValue(arg4);
                XWalkPreferencesInternal.sPrefMap.put(arg3, v1);
                XWalkPreferencesInternal.onKeyValueChanged(arg3, v1);
            }
        }
        catch(Throwable v3) {
            __monitor_exit(v0);
            throw v3;
        }

        __monitor_exit(v0);
    }

    static void unload(KeyValueChangeListener arg1) {
        Class v0 = XWalkPreferencesInternal.class;
        __monitor_enter(v0);
        try {
            XWalkPreferencesInternal.unregisterListener(arg1);
        }
        catch(Throwable v1) {
            __monitor_exit(v0);
            throw v1;
        }

        __monitor_exit(v0);
    }

    private static void unregisterListener(KeyValueChangeListener arg4) {
        Object v2;
        Class v0 = XWalkPreferencesInternal.class;
        __monitor_enter(v0);
        try {
            XWalkPreferencesInternal.removeEnqueuedReference();
            Iterator v1 = XWalkPreferencesInternal.sListeners.iterator();
            do {
                if(v1.hasNext()) {
                    v2 = v1.next();
                    if(((WeakReference)v2).get() != arg4) {
                        continue;
                    }

                    break;
                }

                goto label_12;
            }
            while(true);

            XWalkPreferencesInternal.sListeners.remove(v2);
        }
        catch(Throwable v4) {
            goto label_15;
        }

    label_12:
        __monitor_exit(v0);
        return;
    label_15:
        __monitor_exit(v0);
        throw v4;
    }
}

