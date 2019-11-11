package org.xwalk.core.internal;

@XWalkAPI(noInstance=true) public class XWalkViewDatabaseInternal {
    public XWalkViewDatabaseInternal() {
        super();
    }

    @XWalkAPI(reservable=true) public static void clearFormData() {
        Class v0 = XWalkViewDatabaseInternal.class;
        __monitor_enter(v0);
        try {
            XWalkFormDatabase.clearFormData();
        }
        catch(Throwable v1) {
            __monitor_exit(v0);
            throw v1;
        }

        __monitor_exit(v0);
    }

    @XWalkAPI public static boolean hasFormData() {
        boolean v1_1;
        Class v0 = XWalkViewDatabaseInternal.class;
        __monitor_enter(v0);
        try {
            v1_1 = XWalkFormDatabase.hasFormData();
        }
        catch(Throwable v1) {
            __monitor_exit(v0);
            throw v1;
        }

        __monitor_exit(v0);
        return v1_1;
    }
}

