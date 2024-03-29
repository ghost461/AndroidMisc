package android.support.v4.app;

import android.content.Intent;
import android.content.IntentSender$SendIntentException;
import android.content.IntentSender;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;

@RestrictTo(value={Scope.LIBRARY_GROUP}) abstract class BaseFragmentActivityApi16 extends BaseFragmentActivityApi14 {
    boolean mStartedActivityFromFragment;

    BaseFragmentActivityApi16() {
        super();
    }

    @RequiresApi(value=16) public void startActivityForResult(Intent arg2, int arg3, @Nullable Bundle arg4) {
        if(!this.mStartedActivityFromFragment && arg3 != -1) {
            BaseFragmentActivityApi16.checkForValidRequestCode(arg3);
        }

        super.startActivityForResult(arg2, arg3, arg4);
    }

    @RequiresApi(value=16) public void startIntentSenderForResult(IntentSender arg2, int arg3, @Nullable Intent arg4, int arg5, int arg6, int arg7, Bundle arg8) throws IntentSender$SendIntentException {
        if(!this.mStartedIntentSenderFromFragment && arg3 != -1) {
            BaseFragmentActivityApi16.checkForValidRequestCode(arg3);
        }

        super.startIntentSenderForResult(arg2, arg3, arg4, arg5, arg6, arg7, arg8);
    }
}

