package org.chromium.net;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import java.io.IOException;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.ContextUtils;
import org.chromium.base.Log;
import org.chromium.base.ThreadUtils;
import org.chromium.base.VisibleForTesting;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="net::android") public class HttpNegotiateAuthenticator {
    @VisibleForTesting class GetAccountsCallback implements AccountManagerCallback {
        private final RequestData mRequestData;

        public GetAccountsCallback(HttpNegotiateAuthenticator arg1, RequestData arg2) {
            HttpNegotiateAuthenticator.this = arg1;
            super();
            this.mRequestData = arg2;
        }

        public void run(AccountManagerFuture arg8) {
            int v4;
            Object v8_1;
            String v1 = null;
            try {
                v8_1 = arg8.getResult();
                v4 = -341;
                if(v8_1.length != 0) {
                    goto label_16;
                }
            }
            catch(IOException v8) {
                Log.w("net_auth", "ERR_UNEXPECTED: Error while attempting to retrieve accounts.", new Object[]{v8});
                HttpNegotiateAuthenticator.this.nativeSetResult(this.mRequestData.nativeResultObject, -9, v1);
                return;
            }

            Log.w("net_auth", "ERR_MISSING_AUTH_CREDENTIALS: No account provided for the kerberos authentication. Please verify the configuration policies and that the CONTACTS runtime permission is granted. ", new Object[0]);
            HttpNegotiateAuthenticator.this.nativeSetResult(this.mRequestData.nativeResultObject, v4, v1);
            return;
        label_16:
            if(v8_1.length > 1) {
                Log.w("net_auth", "ERR_MISSING_AUTH_CREDENTIALS: Found %d accounts eligible for the kerberos authentication. Please fix the configuration by providing a single account.", new Object[]{Integer.valueOf(v8_1.length)});
                HttpNegotiateAuthenticator.this.nativeSetResult(this.mRequestData.nativeResultObject, v4, v1);
                return;
            }

            if(HttpNegotiateAuthenticator.this.lacksPermission(ContextUtils.getApplicationContext(), "android.permission.USE_CREDENTIALS", true)) {
                Log.e("net_auth", "ERR_MISCONFIGURED_AUTH_ENVIRONMENT: USE_CREDENTIALS permission not granted. Aborting authentication.", new Object[0]);
                HttpNegotiateAuthenticator.this.nativeSetResult(this.mRequestData.nativeResultObject, -343, v1);
                return;
            }

            this.mRequestData.account = v8_1[0];
            this.mRequestData.accountManager.getAuthToken(this.mRequestData.account, this.mRequestData.authTokenType, this.mRequestData.options, true, new GetTokenCallback(HttpNegotiateAuthenticator.this, this.mRequestData), new Handler(ThreadUtils.getUiThreadLooper()));
        }
    }

    @VisibleForTesting class GetTokenCallback implements AccountManagerCallback {
        private final RequestData mRequestData;

        public GetTokenCallback(HttpNegotiateAuthenticator arg1, RequestData arg2) {
            HttpNegotiateAuthenticator.this = arg1;
            super();
            this.mRequestData = arg2;
        }

        static RequestData access$000(GetTokenCallback arg0) {
            return arg0.mRequestData;
        }

        public void run(AccountManagerFuture arg5) {
            Object v5_1;
            try {
                v5_1 = arg5.getResult();
                String v0 = "intent";
            }
            catch(IOException v5) {
                Log.w("net_auth", "ERR_UNEXPECTED: Error while attempting to obtain a token.", new Object[]{v5});
                HttpNegotiateAuthenticator.this.nativeSetResult(this.mRequestData.nativeResultObject, -9, null);
                return;
            }

            if(((Bundle)v5_1).containsKey(v0)) {
                Context v5_2 = ContextUtils.getApplicationContext();
                v5_2.registerReceiver(new BroadcastReceiver(v5_2) {
                    public void onReceive(Context arg8, Intent arg9) {
                        this.val$appContext.unregisterReceiver(((BroadcastReceiver)this));
                        this.this$1.mRequestData.accountManager.getAuthToken(this.this$1.mRequestData.account, this.this$1.mRequestData.authTokenType, this.this$1.mRequestData.options, true, new GetTokenCallback(this.this$1.this$0, this.this$1.mRequestData), null);
                    }
                }, new IntentFilter("android.accounts.LOGIN_ACCOUNTS_CHANGED"));
            }
            else {
                HttpNegotiateAuthenticator.this.processResult(((Bundle)v5_1), this.mRequestData);
            }
        }
    }

    class RequestData {
        public Account account;
        public AccountManager accountManager;
        public String authTokenType;
        public long nativeResultObject;
        public Bundle options;

        RequestData() {
            super();
        }
    }

    private static final String TAG = "net_auth";
    private final String mAccountType;
    private Bundle mSpnegoContext;

    static {
    }

    protected HttpNegotiateAuthenticator(String arg1) {
        super();
        this.mAccountType = arg1;
    }

    static void access$100(HttpNegotiateAuthenticator arg0, Bundle arg1, RequestData arg2) {
        arg0.processResult(arg1, arg2);
    }

    @VisibleForTesting @CalledByNative static HttpNegotiateAuthenticator create(String arg1) {
        return new HttpNegotiateAuthenticator(arg1);
    }

    @VisibleForTesting @CalledByNative void getNextAuthToken(long arg5, String arg7, String arg8, boolean arg9) {
        Context v0 = ContextUtils.getApplicationContext();
        RequestData v1 = new RequestData();
        v1.authTokenType = "SPNEGO:HOSTBASED:" + arg7;
        v1.accountManager = AccountManager.get(v0);
        v1.nativeResultObject = arg5;
        String[] v5 = new String[]{"SPNEGO"};
        v1.options = new Bundle();
        if(arg8 != null) {
            v1.options.putString("incomingAuthToken", arg8);
        }

        if(this.mSpnegoContext != null) {
            v1.options.putBundle("spnegoContext", this.mSpnegoContext);
        }

        v1.options.putBoolean("canDelegate", arg9);
        Activity v6 = ApplicationStatus.getLastTrackedFocusedActivity();
        if(v6 == null) {
            this.requestTokenWithoutActivity(v0, v1, v5);
        }
        else {
            this.requestTokenWithActivity(v0, v6, v1, v5);
        }
    }

    @VisibleForTesting boolean lacksPermission(Context arg3, String arg4, boolean arg5) {
        boolean v0 = false;
        if((arg5) && Build$VERSION.SDK_INT >= 23) {
            return 0;
        }

        if(arg3.checkPermission(arg4, Process.myPid(), Process.myUid()) != 0) {
            v0 = true;
        }

        return v0;
    }

    @VisibleForTesting native void nativeSetResult(long arg1, int arg2, String arg3) {
    }

    private void processResult(Bundle arg5, RequestData arg6) {
        this.mSpnegoContext = arg5.getBundle("spnegoContext");
        int v1 = -9;
        switch(arg5.getInt("spnegoResult", 1)) {
            case 0: {
                v1 = 0;
                break;
            }
            case 2: {
                v1 = -3;
                break;
            }
            case 3: {
                v1 = -342;
                break;
            }
            case 4: {
                v1 = -320;
                break;
            }
            case 5: {
                v1 = -338;
                break;
            }
            case 6: {
                v1 = -339;
                break;
            }
            case 7: {
                v1 = -341;
                break;
            }
            case 8: {
                v1 = -344;
                break;
            }
            case 9: {
                v1 = -329;
                break;
            }
            default: {
                break;
            }
        }

        this.nativeSetResult(arg6.nativeResultObject, v1, arg5.getString("authtoken"));
    }

    private void requestTokenWithActivity(Context arg10, Activity arg11, RequestData arg12, String[] arg13) {
        boolean v0 = Build$VERSION.SDK_INT < 23 ? true : false;
        String v3 = v0 ? "android.permission.MANAGE_ACCOUNTS" : "android.permission.GET_ACCOUNTS";
        if(this.lacksPermission(arg10, v3, v0)) {
            Log.e("net_auth", "ERR_MISCONFIGURED_AUTH_ENVIRONMENT: %s permission not granted. Aborting authentication", new Object[]{v3});
            this.nativeSetResult(arg12.nativeResultObject, -343, null);
            return;
        }

        arg12.accountManager.getAuthTokenByFeatures(this.mAccountType, arg12.authTokenType, arg13, arg11, null, arg12.options, new GetTokenCallback(this, arg12), new Handler(ThreadUtils.getUiThreadLooper()));
    }

    private void requestTokenWithoutActivity(Context arg4, RequestData arg5, String[] arg6) {
        if(this.lacksPermission(arg4, "android.permission.GET_ACCOUNTS", true)) {
            Log.e("net_auth", "ERR_MISCONFIGURED_AUTH_ENVIRONMENT: GET_ACCOUNTS permission not granted. Aborting authentication.", new Object[0]);
            this.nativeSetResult(arg5.nativeResultObject, -343, null);
            return;
        }

        arg5.accountManager.getAccountsByTypeAndFeatures(this.mAccountType, arg6, new GetAccountsCallback(this, arg5), new Handler(ThreadUtils.getUiThreadLooper()));
    }
}

