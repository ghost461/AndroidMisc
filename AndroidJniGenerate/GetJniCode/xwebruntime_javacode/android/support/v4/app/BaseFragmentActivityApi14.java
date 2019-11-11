package android.support.v4.app;

import android.content.Context;
import android.content.Intent;
import android.content.IntentSender$SendIntentException;
import android.content.IntentSender;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

@RequiresApi(value=14) abstract class BaseFragmentActivityApi14 extends SupportActivity {
    boolean mStartedIntentSenderFromFragment;

    BaseFragmentActivityApi14() {
        super();
    }

    static void checkForValidRequestCode(int arg1) {
        if((arg1 & 0xFFFF0000) != 0) {
            throw new IllegalArgumentException("Can only use lower 16 bits for requestCode");
        }
    }

    abstract View dispatchFragmentsOnCreateView(View arg1, String arg2, Context arg3, AttributeSet arg4);

    public View onCreateView(View arg2, String arg3, Context arg4, AttributeSet arg5) {
        View v0 = this.dispatchFragmentsOnCreateView(arg2, arg3, arg4, arg5);
        if(v0 == null) {
            return super.onCreateView(arg2, arg3, arg4, arg5);
        }

        return v0;
    }

    public View onCreateView(String arg2, Context arg3, AttributeSet arg4) {
        View v0 = this.dispatchFragmentsOnCreateView(null, arg2, arg3, arg4);
        if(v0 == null) {
            return super.onCreateView(arg2, arg3, arg4);
        }

        return v0;
    }

    public void startIntentSenderForResult(IntentSender arg2, int arg3, @Nullable Intent arg4, int arg5, int arg6, int arg7) throws IntentSender$SendIntentException {
        if(!this.mStartedIntentSenderFromFragment && arg3 != -1) {
            BaseFragmentActivityApi14.checkForValidRequestCode(arg3);
        }

        super.startIntentSenderForResult(arg2, arg3, arg4, arg5, arg6, arg7);
    }
}

