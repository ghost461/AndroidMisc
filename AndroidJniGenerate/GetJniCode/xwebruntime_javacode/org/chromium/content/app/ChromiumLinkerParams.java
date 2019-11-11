package org.chromium.content.app;

import android.os.Bundle;
import java.util.Locale;
import javax.annotation.concurrent.Immutable;

@Immutable public class ChromiumLinkerParams {
    private static final String EXTRA_LINKER_PARAMS_BASE_LOAD_ADDRESS = "org.chromium.content.common.linker_params.base_load_address";
    private static final String EXTRA_LINKER_PARAMS_TEST_RUNNER_CLASS_NAME = "org.chromium.content.common.linker_params.test_runner_class_name";
    private static final String EXTRA_LINKER_PARAMS_WAIT_FOR_SHARED_RELRO = "org.chromium.content.common.linker_params.wait_for_shared_relro";
    public final long mBaseLoadAddress;
    public final String mTestRunnerClassNameForTesting;
    public final boolean mWaitForSharedRelro;

    public ChromiumLinkerParams(long arg1, boolean arg3) {
        super();
        this.mBaseLoadAddress = arg1;
        this.mWaitForSharedRelro = arg3;
        this.mTestRunnerClassNameForTesting = null;
    }

    public ChromiumLinkerParams(long arg1, boolean arg3, String arg4) {
        super();
        this.mBaseLoadAddress = arg1;
        this.mWaitForSharedRelro = arg3;
        this.mTestRunnerClassNameForTesting = arg4;
    }

    private ChromiumLinkerParams(Bundle arg4) {
        super();
        this.mBaseLoadAddress = arg4.getLong("org.chromium.content.common.linker_params.base_load_address", 0);
        this.mWaitForSharedRelro = arg4.getBoolean("org.chromium.content.common.linker_params.wait_for_shared_relro", false);
        this.mTestRunnerClassNameForTesting = arg4.getString("org.chromium.content.common.linker_params.test_runner_class_name");
    }

    public static ChromiumLinkerParams create(Bundle arg1) {
        if((arg1.containsKey("org.chromium.content.common.linker_params.base_load_address")) && (arg1.containsKey("org.chromium.content.common.linker_params.wait_for_shared_relro"))) {
            if(!arg1.containsKey("org.chromium.content.common.linker_params.test_runner_class_name")) {
            }
            else {
                return new ChromiumLinkerParams(arg1);
            }
        }

        return null;
    }

    public void populateBundle(Bundle arg4) {
        arg4.putLong("org.chromium.content.common.linker_params.base_load_address", this.mBaseLoadAddress);
        arg4.putBoolean("org.chromium.content.common.linker_params.wait_for_shared_relro", this.mWaitForSharedRelro);
        arg4.putString("org.chromium.content.common.linker_params.test_runner_class_name", this.mTestRunnerClassNameForTesting);
    }

    public String toString() {
        return String.format(Locale.US, "LinkerParams(baseLoadAddress:0x%x, waitForSharedRelro:%s, testRunnerClassName:%s", Long.valueOf(this.mBaseLoadAddress), Boolean.toString(this.mWaitForSharedRelro), this.mTestRunnerClassNameForTesting);
    }
}

