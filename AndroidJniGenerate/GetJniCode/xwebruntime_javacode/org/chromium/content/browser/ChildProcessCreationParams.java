package org.chromium.content.browser;

import android.os.Bundle;
import org.chromium.base.ContextUtils;

public class ChildProcessCreationParams {
    private static final String EXTRA_LIBRARY_PROCESS_TYPE = "org.chromium.content.common.child_service_params.library_process_type";
    private final boolean mBindToCallerCheck;
    private final boolean mIgnoreVisibilityForImportance;
    private final boolean mIsSandboxedServiceExternal;
    private final int mLibraryProcessType;
    private final String mPackageNameForService;
    private static ChildProcessCreationParams sParams;

    static {
    }

    public ChildProcessCreationParams(String arg1, boolean arg2, int arg3, boolean arg4, boolean arg5) {
        super();
        this.mPackageNameForService = arg1;
        this.mIsSandboxedServiceExternal = arg2;
        this.mLibraryProcessType = arg3;
        this.mBindToCallerCheck = arg4;
        this.mIgnoreVisibilityForImportance = arg5;
    }

    public void addIntentExtras(Bundle arg3) {
        arg3.putInt("org.chromium.content.common.child_service_params.library_process_type", this.mLibraryProcessType);
    }

    public static ChildProcessCreationParams get() {
        return ChildProcessCreationParams.sParams;
    }

    public static boolean getBindToCallerCheck() {
        ChildProcessCreationParams v0 = ChildProcessCreationParams.get();
        boolean v0_1 = v0 == null || !v0.mBindToCallerCheck ? false : true;
        return v0_1;
    }

    public static boolean getIgnoreVisibilityForImportance() {
        ChildProcessCreationParams v0 = ChildProcessCreationParams.get();
        boolean v0_1 = v0 == null || !v0.mIgnoreVisibilityForImportance ? false : true;
        return v0_1;
    }

    public static boolean getIsSandboxedServiceExternal() {
        ChildProcessCreationParams v0 = ChildProcessCreationParams.get();
        boolean v0_1 = v0 == null || !v0.mIsSandboxedServiceExternal ? false : true;
        return v0_1;
    }

    public static int getLibraryProcessType(Bundle arg2) {
        return arg2.getInt("org.chromium.content.common.child_service_params.library_process_type", 2);
    }

    public static String getPackageNameForService() {
        ChildProcessCreationParams v0 = ChildProcessCreationParams.get();
        String v0_1 = v0 != null ? v0.mPackageNameForService : ContextUtils.getApplicationContext().getPackageName();
        return v0_1;
    }

    public static void set(ChildProcessCreationParams arg0) {
        ChildProcessCreationParams.sParams = arg0;
    }
}

