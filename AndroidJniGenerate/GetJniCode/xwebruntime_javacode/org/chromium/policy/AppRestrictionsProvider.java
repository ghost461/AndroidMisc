package org.chromium.policy;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.os.UserManager;

public class AppRestrictionsProvider extends AbstractAppRestrictionsProvider {
    private final UserManager mUserManager;

    public AppRestrictionsProvider(Context arg3) {
        super(arg3);
        this.mUserManager = Build$VERSION.SDK_INT >= 18 ? arg3.getSystemService("user") : null;
    }

    @TargetApi(value=18) protected Bundle getApplicationRestrictions(String arg2) {
        if(this.mUserManager == null) {
            return new Bundle();
        }

        return this.mUserManager.getApplicationRestrictions(arg2);
    }

    @TargetApi(value=21) protected String getRestrictionChangeIntentAction() {
        if(Build$VERSION.SDK_INT < 21) {
            return null;
        }

        return "android.intent.action.APPLICATION_RESTRICTIONS_CHANGED";
    }
}

