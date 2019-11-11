package android.support.v4.app;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.view.View;

public abstract class FragmentContainer {
    public FragmentContainer() {
        super();
    }

    public Fragment instantiate(Context arg1, String arg2, Bundle arg3) {
        return Fragment.instantiate(arg1, arg2, arg3);
    }

    @Nullable public abstract View onFindViewById(@IdRes int arg1);

    public abstract boolean onHasView();
}

