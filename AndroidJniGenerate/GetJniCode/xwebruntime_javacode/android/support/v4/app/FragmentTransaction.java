package android.support.v4.app;

import android.support.annotation.AnimRes;
import android.support.annotation.AnimatorRes;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.view.View;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public abstract class FragmentTransaction {
    @RestrictTo(value={Scope.LIBRARY_GROUP}) @Retention(value=RetentionPolicy.SOURCE) @interface Transit {
    }

    public static final int TRANSIT_ENTER_MASK = 0x1000;
    public static final int TRANSIT_EXIT_MASK = 0x2000;
    public static final int TRANSIT_FRAGMENT_CLOSE = 0x2002;
    public static final int TRANSIT_FRAGMENT_FADE = 0x1003;
    public static final int TRANSIT_FRAGMENT_OPEN = 0x1001;
    public static final int TRANSIT_NONE = 0;
    public static final int TRANSIT_UNSET = -1;

    public FragmentTransaction() {
        super();
    }

    public abstract FragmentTransaction add(@IdRes int arg1, Fragment arg2, @Nullable String arg3);

    public abstract FragmentTransaction add(@IdRes int arg1, Fragment arg2);

    public abstract FragmentTransaction add(Fragment arg1, String arg2);

    public abstract FragmentTransaction addSharedElement(View arg1, String arg2);

    public abstract FragmentTransaction addToBackStack(@Nullable String arg1);

    public abstract FragmentTransaction attach(Fragment arg1);

    public abstract int commit();

    public abstract int commitAllowingStateLoss();

    public abstract void commitNow();

    public abstract void commitNowAllowingStateLoss();

    public abstract FragmentTransaction detach(Fragment arg1);

    public abstract FragmentTransaction disallowAddToBackStack();

    public abstract FragmentTransaction hide(Fragment arg1);

    public abstract boolean isAddToBackStackAllowed();

    public abstract boolean isEmpty();

    public abstract FragmentTransaction remove(Fragment arg1);

    public abstract FragmentTransaction replace(@IdRes int arg1, Fragment arg2);

    public abstract FragmentTransaction replace(@IdRes int arg1, Fragment arg2, @Nullable String arg3);

    public abstract FragmentTransaction runOnCommit(Runnable arg1);

    @Deprecated public abstract FragmentTransaction setAllowOptimization(boolean arg1);

    public abstract FragmentTransaction setBreadCrumbShortTitle(@StringRes int arg1);

    public abstract FragmentTransaction setBreadCrumbShortTitle(CharSequence arg1);

    public abstract FragmentTransaction setBreadCrumbTitle(@StringRes int arg1);

    public abstract FragmentTransaction setBreadCrumbTitle(CharSequence arg1);

    public abstract FragmentTransaction setCustomAnimations(@AnimRes @AnimatorRes int arg1, @AnimRes @AnimatorRes int arg2);

    public abstract FragmentTransaction setCustomAnimations(@AnimRes @AnimatorRes int arg1, @AnimRes @AnimatorRes int arg2, @AnimRes @AnimatorRes int arg3, @AnimRes @AnimatorRes int arg4);

    public abstract FragmentTransaction setPrimaryNavigationFragment(Fragment arg1);

    public abstract FragmentTransaction setReorderingAllowed(boolean arg1);

    public abstract FragmentTransaction setTransition(int arg1);

    public abstract FragmentTransaction setTransitionStyle(@StyleRes int arg1);

    public abstract FragmentTransaction show(Fragment arg1);
}

