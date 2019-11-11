package android.support.v7.app;

import android.app.ActionBar;
import android.app.Activity;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import java.lang.reflect.Method;

@RequiresApi(value=11) class ActionBarDrawerToggleHoneycomb {
    class SetIndicatorInfo {
        public Method setHomeActionContentDescription;
        public Method setHomeAsUpIndicator;
        public ImageView upIndicatorView;

        SetIndicatorInfo(Activity arg7) {
            super();
            try {
                this.setHomeAsUpIndicator = ActionBar.class.getDeclaredMethod("setHomeAsUpIndicator", Drawable.class);
                this.setHomeActionContentDescription = ActionBar.class.getDeclaredMethod("setHomeActionContentDescription", Integer.TYPE);
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
                    this.upIndicatorView = ((ImageView)v7);
                }

                return;
            }
        }
    }

    private static final String TAG = "ActionBarDrawerToggleHoneycomb";
    private static final int[] THEME_ATTRS;

    static {
        ActionBarDrawerToggleHoneycomb.THEME_ATTRS = new int[]{0x101030B};
    }

    ActionBarDrawerToggleHoneycomb() {
        super();
    }

    public static Drawable getThemeUpIndicator(Activity arg1) {
        TypedArray v1 = arg1.obtainStyledAttributes(ActionBarDrawerToggleHoneycomb.THEME_ATTRS);
        Drawable v0 = v1.getDrawable(0);
        v1.recycle();
        return v0;
    }

    public static SetIndicatorInfo setActionBarDescription(SetIndicatorInfo arg3, Activity arg4, int arg5) {
        if(arg3 == null) {
            arg3 = new SetIndicatorInfo(arg4);
        }

        if(arg3.setHomeAsUpIndicator != null) {
            try {
                ActionBar v4_1 = arg4.getActionBar();
                arg3.setHomeActionContentDescription.invoke(v4_1, Integer.valueOf(arg5));
                if(Build$VERSION.SDK_INT > 19) {
                    return arg3;
                }

                v4_1.setSubtitle(v4_1.getSubtitle());
            }
            catch(Exception v4) {
                Log.w("ActionBarDrawerToggleHoneycomb", "Couldn\'t set content description via JB-MR2 API", ((Throwable)v4));
            }
        }

        return arg3;
    }

    public static SetIndicatorInfo setActionBarUpIndicator(SetIndicatorInfo arg4, Activity arg5, Drawable arg6, int arg7) {
        arg4 = new SetIndicatorInfo(arg5);
        if(arg4.setHomeAsUpIndicator != null) {
            try {
                ActionBar v5_1 = arg5.getActionBar();
                arg4.setHomeAsUpIndicator.invoke(v5_1, arg6);
                arg4.setHomeActionContentDescription.invoke(v5_1, Integer.valueOf(arg7));
            }
            catch(Exception v5) {
                Log.w("ActionBarDrawerToggleHoneycomb", "Couldn\'t set home-as-up indicator via JB-MR2 API", ((Throwable)v5));
            }

            return arg4;
        }

        if(arg4.upIndicatorView != null) {
            arg4.upIndicatorView.setImageDrawable(arg6);
        }
        else {
            Log.w("ActionBarDrawerToggleHoneycomb", "Couldn\'t set home-as-up indicator");
        }

        return arg4;
    }
}

