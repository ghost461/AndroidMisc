package android.support.v4.app;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable$Callback;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Build$VERSION;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout$DrawerListener;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import java.lang.reflect.Method;

@Deprecated public class ActionBarDrawerToggle implements DrawerListener {
    @Deprecated public interface Delegate {
        @Nullable Drawable getThemeUpIndicator();

        void setActionBarDescription(@StringRes int arg1);

        void setActionBarUpIndicator(Drawable arg1, @StringRes int arg2);
    }

    @Deprecated public interface DelegateProvider {
        @Nullable Delegate getDrawerToggleDelegate();
    }

    class SetIndicatorInfo {
        Method mSetHomeActionContentDescription;
        Method mSetHomeAsUpIndicator;
        ImageView mUpIndicatorView;

        SetIndicatorInfo(Activity arg7) {
            super();
            try {
                this.mSetHomeAsUpIndicator = ActionBar.class.getDeclaredMethod("setHomeAsUpIndicator", Drawable.class);
                this.mSetHomeActionContentDescription = ActionBar.class.getDeclaredMethod("setHomeActionContentDescription", Integer.TYPE);
                return;
            }
            catch(NoSuchMethodException ) {
                int v2 = 0x102002C;
                View v7 = arg7.findViewById(v2);
                if(v7 == null) {
                    return;
                }

                ViewParent v7_1 = v7.getParent();
                if(((ViewGroup)v7_1).getChildCount() != 2) {
                    return;
                }

                View v0 = ((ViewGroup)v7_1).getChildAt(0);
                v7 = ((ViewGroup)v7_1).getChildAt(1);
                if(v0.getId() == v2) {
                }
                else {
                    v7 = v0;
                }

                if((v7 instanceof ImageView)) {
                    this.mUpIndicatorView = ((ImageView)v7);
                }

                return;
            }
        }
    }

    class SlideDrawable extends InsetDrawable implements Drawable$Callback {
        private final boolean mHasMirroring;
        private float mOffset;
        private float mPosition;
        private final Rect mTmpRect;

        SlideDrawable(ActionBarDrawerToggle arg2, Drawable arg3) {
            ActionBarDrawerToggle.this = arg2;
            boolean v2 = false;
            super(arg3, 0);
            if(Build$VERSION.SDK_INT > 18) {
                v2 = true;
            }

            this.mHasMirroring = v2;
            this.mTmpRect = new Rect();
        }

        public void draw(@NonNull Canvas arg6) {
            this.copyBounds(this.mTmpRect);
            arg6.save();
            int v1 = 1;
            int v0 = ViewCompat.getLayoutDirection(ActionBarDrawerToggle.this.mActivity.getWindow().getDecorView()) == 1 ? 1 : 0;
            if(v0 != 0) {
                v1 = -1;
            }

            float v2 = ((float)this.mTmpRect.width());
            arg6.translate(-this.mOffset * v2 * this.mPosition * (((float)v1)), 0f);
            if(v0 != 0 && !this.mHasMirroring) {
                arg6.translate(v2, 0f);
                arg6.scale(-1f, 1f);
            }

            super.draw(arg6);
            arg6.restore();
        }

        public float getPosition() {
            return this.mPosition;
        }

        public void setOffset(float arg1) {
            this.mOffset = arg1;
            this.invalidateSelf();
        }

        public void setPosition(float arg1) {
            this.mPosition = arg1;
            this.invalidateSelf();
        }
    }

    private static final int ID_HOME = 0x102002C;
    private static final String TAG = "ActionBarDrawerToggle";
    private static final int[] THEME_ATTRS = null;
    private static final float TOGGLE_DRAWABLE_OFFSET = 0.333333f;
    final Activity mActivity;
    private final Delegate mActivityImpl;
    private final int mCloseDrawerContentDescRes;
    private Drawable mDrawerImage;
    private final int mDrawerImageResource;
    private boolean mDrawerIndicatorEnabled;
    private final DrawerLayout mDrawerLayout;
    private boolean mHasCustomUpIndicator;
    private Drawable mHomeAsUpIndicator;
    private final int mOpenDrawerContentDescRes;
    private SetIndicatorInfo mSetIndicatorInfo;
    private SlideDrawable mSlider;

    static {
        ActionBarDrawerToggle.THEME_ATTRS = new int[]{0x101030B};
    }

    public ActionBarDrawerToggle(Activity arg9, DrawerLayout arg10, @DrawableRes int arg11, @StringRes int arg12, @StringRes int arg13) {
        this(arg9, arg10, ActionBarDrawerToggle.assumeMaterial(((Context)arg9)) ^ 1, arg11, arg12, arg13);
    }

    public ActionBarDrawerToggle(Activity arg2, DrawerLayout arg3, boolean arg4, @DrawableRes int arg5, @StringRes int arg6, @StringRes int arg7) {
        super();
        this.mDrawerIndicatorEnabled = true;
        this.mActivity = arg2;
        this.mActivityImpl = (arg2 instanceof DelegateProvider) ? arg2.getDrawerToggleDelegate() : null;
        this.mDrawerLayout = arg3;
        this.mDrawerImageResource = arg5;
        this.mOpenDrawerContentDescRes = arg6;
        this.mCloseDrawerContentDescRes = arg7;
        this.mHomeAsUpIndicator = this.getThemeUpIndicator();
        this.mDrawerImage = ContextCompat.getDrawable(((Context)arg2), arg5);
        this.mSlider = new SlideDrawable(this, this.mDrawerImage);
        SlideDrawable v2 = this.mSlider;
        float v3 = arg4 ? 0.333333f : 0f;
        v2.setOffset(v3);
    }

    private static boolean assumeMaterial(Context arg1) {
        int v0 = 21;
        boolean v1 = arg1.getApplicationInfo().targetSdkVersion < v0 || Build$VERSION.SDK_INT < v0 ? false : true;
        return v1;
    }

    private Drawable getThemeUpIndicator() {
        Drawable v1;
        TypedArray v0_3;
        Context v0_1;
        if(this.mActivityImpl != null) {
            return this.mActivityImpl.getThemeUpIndicator();
        }

        if(Build$VERSION.SDK_INT >= 18) {
            ActionBar v0 = this.mActivity.getActionBar();
            if(v0 != null) {
                v0_1 = v0.getThemedContext();
            }
            else {
                Activity v0_2 = this.mActivity;
            }

            v0_3 = v0_1.obtainStyledAttributes(null, ActionBarDrawerToggle.THEME_ATTRS, 0x10102CE, 0);
            v1 = v0_3.getDrawable(0);
            v0_3.recycle();
            return v1;
        }

        v0_3 = this.mActivity.obtainStyledAttributes(ActionBarDrawerToggle.THEME_ATTRS);
        v1 = v0_3.getDrawable(0);
        v0_3.recycle();
        return v1;
    }

    public boolean isDrawerIndicatorEnabled() {
        return this.mDrawerIndicatorEnabled;
    }

    public void onConfigurationChanged(Configuration arg2) {
        if(!this.mHasCustomUpIndicator) {
            this.mHomeAsUpIndicator = this.getThemeUpIndicator();
        }

        this.mDrawerImage = ContextCompat.getDrawable(this.mActivity, this.mDrawerImageResource);
        this.syncState();
    }

    public void onDrawerClosed(View arg2) {
        this.mSlider.setPosition(0f);
        if(this.mDrawerIndicatorEnabled) {
            this.setActionBarDescription(this.mOpenDrawerContentDescRes);
        }
    }

    public void onDrawerOpened(View arg2) {
        this.mSlider.setPosition(1f);
        if(this.mDrawerIndicatorEnabled) {
            this.setActionBarDescription(this.mCloseDrawerContentDescRes);
        }
    }

    public void onDrawerSlide(View arg4, float arg5) {
        float v4 = this.mSlider.getPosition();
        float v0 = 0.5f;
        float v2 = 2f;
        v4 = Float.compare(arg5, v0) > 0 ? Math.max(v4, Math.max(0f, arg5 - v0) * v2) : Math.min(v4, arg5 * v2);
        this.mSlider.setPosition(v4);
    }

    public void onDrawerStateChanged(int arg1) {
    }

    public boolean onOptionsItemSelected(MenuItem arg2) {
        if(arg2 != null && arg2.getItemId() == 0x102002C && (this.mDrawerIndicatorEnabled)) {
            int v0 = 0x800003;
            if(this.mDrawerLayout.isDrawerVisible(v0)) {
                this.mDrawerLayout.closeDrawer(v0);
            }
            else {
                this.mDrawerLayout.openDrawer(v0);
            }

            return 1;
        }

        return 0;
    }

    private void setActionBarDescription(int arg5) {
        ActionBar v0;
        if(this.mActivityImpl != null) {
            this.mActivityImpl.setActionBarDescription(arg5);
            return;
        }

        if(Build$VERSION.SDK_INT >= 18) {
            v0 = this.mActivity.getActionBar();
            if(v0 == null) {
                return;
            }

            v0.setHomeActionContentDescription(arg5);
            return;
        }

        if(this.mSetIndicatorInfo == null) {
            this.mSetIndicatorInfo = new SetIndicatorInfo(this.mActivity);
        }

        if(this.mSetIndicatorInfo.mSetHomeAsUpIndicator != null) {
            try {
                v0 = this.mActivity.getActionBar();
                this.mSetIndicatorInfo.mSetHomeActionContentDescription.invoke(v0, Integer.valueOf(arg5));
                v0.setSubtitle(v0.getSubtitle());
            }
            catch(Exception v5) {
                Log.w("ActionBarDrawerToggle", "Couldn\'t set content description via JB-MR2 API", ((Throwable)v5));
            }
        }
    }

    private void setActionBarUpIndicator(Drawable arg6, int arg7) {
        ActionBar v0;
        if(this.mActivityImpl != null) {
            this.mActivityImpl.setActionBarUpIndicator(arg6, arg7);
            return;
        }

        if(Build$VERSION.SDK_INT >= 18) {
            v0 = this.mActivity.getActionBar();
            if(v0 == null) {
                return;
            }

            v0.setHomeAsUpIndicator(arg6);
            v0.setHomeActionContentDescription(arg7);
            return;
        }

        if(this.mSetIndicatorInfo == null) {
            this.mSetIndicatorInfo = new SetIndicatorInfo(this.mActivity);
        }

        if(this.mSetIndicatorInfo.mSetHomeAsUpIndicator != null) {
            try {
                v0 = this.mActivity.getActionBar();
                this.mSetIndicatorInfo.mSetHomeAsUpIndicator.invoke(v0, arg6);
                this.mSetIndicatorInfo.mSetHomeActionContentDescription.invoke(v0, Integer.valueOf(arg7));
            }
            catch(Exception v6) {
                Log.w("ActionBarDrawerToggle", "Couldn\'t set home-as-up indicator via JB-MR2 API", ((Throwable)v6));
            }

            return;
        }

        if(this.mSetIndicatorInfo.mUpIndicatorView != null) {
            this.mSetIndicatorInfo.mUpIndicatorView.setImageDrawable(arg6);
        }
        else {
            Log.w("ActionBarDrawerToggle", "Couldn\'t set home-as-up indicator");
        }
    }

    public void setDrawerIndicatorEnabled(boolean arg4) {
        if(arg4 != this.mDrawerIndicatorEnabled) {
            if(arg4) {
                SlideDrawable v0 = this.mSlider;
                int v1 = this.mDrawerLayout.isDrawerOpen(0x800003) ? this.mCloseDrawerContentDescRes : this.mOpenDrawerContentDescRes;
                this.setActionBarUpIndicator(((Drawable)v0), v1);
            }
            else {
                this.setActionBarUpIndicator(this.mHomeAsUpIndicator, 0);
            }

            this.mDrawerIndicatorEnabled = arg4;
        }
    }

    public void setHomeAsUpIndicator(int arg2) {
        Drawable v2 = arg2 != 0 ? ContextCompat.getDrawable(this.mActivity, arg2) : null;
        this.setHomeAsUpIndicator(v2);
    }

    public void setHomeAsUpIndicator(Drawable arg2) {
        if(arg2 == null) {
            this.mHomeAsUpIndicator = this.getThemeUpIndicator();
            this.mHasCustomUpIndicator = false;
        }
        else {
            this.mHomeAsUpIndicator = arg2;
            this.mHasCustomUpIndicator = true;
        }

        if(!this.mDrawerIndicatorEnabled) {
            this.setActionBarUpIndicator(this.mHomeAsUpIndicator, 0);
        }
    }

    public void syncState() {
        int v1 = 0x800003;
        if(this.mDrawerLayout.isDrawerOpen(v1)) {
            this.mSlider.setPosition(1f);
        }
        else {
            this.mSlider.setPosition(0f);
        }

        if(this.mDrawerIndicatorEnabled) {
            SlideDrawable v0 = this.mSlider;
            v1 = this.mDrawerLayout.isDrawerOpen(v1) ? this.mCloseDrawerContentDescRes : this.mOpenDrawerContentDescRes;
            this.setActionBarUpIndicator(((Drawable)v0), v1);
        }
    }
}

