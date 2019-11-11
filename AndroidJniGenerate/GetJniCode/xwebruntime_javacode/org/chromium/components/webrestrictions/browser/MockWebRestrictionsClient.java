package org.chromium.components.webrestrictions.browser;

import android.database.AbstractCursor;
import org.chromium.base.annotations.CalledByNative;

public class MockWebRestrictionsClient extends WebRestrictionsClient {
    private String mAuthority;

    private MockWebRestrictionsClient() {
        super();
    }

    static String access$000(MockWebRestrictionsClient arg0) {
        return arg0.mAuthority;
    }

    void init(String arg1, long arg2) {
        this.mAuthority = arg1;
    }

    void onDestroy() {
    }

    @CalledByNative static void registerAsMockForTesting() {
        WebRestrictionsClient.registerMockForTesting(new MockWebRestrictionsClient());
    }

    boolean requestPermission(String arg2) {
        return this.mAuthority.contains("Good");
    }

    WebRestrictionsClientResult shouldProceed(String arg3) {
        return new WebRestrictionsClientResult(new AbstractCursor(arg3) {
            public int getColumnCount() {
                int v0 = MockWebRestrictionsClient.this.mAuthority.contains("Good") ? 1 : 3;
                return v0;
            }

            public String[] getColumnNames() {
                return new String[]{"Result", "Error number", "Error string"};
            }

            public int getCount() {
                return 0;
            }

            public double getDouble(int arg3) {
                return 0;
            }

            public float getFloat(int arg1) {
                return 0;
            }

            public int getInt(int arg4) {
                if(arg4 == 0 && (MockWebRestrictionsClient.this.mAuthority.contains("Good"))) {
                    return 1;
                }

                if(arg4 == 1 && !MockWebRestrictionsClient.this.mAuthority.contains("Good")) {
                    return 42;
                }

                return 0;
            }

            public long getLong(int arg3) {
                return 0;
            }

            public short getShort(int arg1) {
                return 0;
            }

            public String getString(int arg2) {
                if(arg2 == 2 && !MockWebRestrictionsClient.this.mAuthority.contains("Good")) {
                    return this.val$url;
                }

                return null;
            }

            public boolean isNull(int arg1) {
                return 0;
            }
        });
    }

    boolean supportsRequest() {
        return this.mAuthority.contains("Good");
    }

    @CalledByNative static void unregisterAsMockForTesting() {
        WebRestrictionsClient.registerMockForTesting(null);
    }
}

