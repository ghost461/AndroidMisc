package android.support.v7.view;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build$VERSION;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.v7.appcompat.R$attr;
import android.support.v7.appcompat.R$bool;
import android.support.v7.appcompat.R$dimen;
import android.support.v7.appcompat.R$styleable;
import android.view.ViewConfiguration;

@RestrictTo(value={Scope.LIBRARY_GROUP}) public class ActionBarPolicy {
    private Context mContext;

    private ActionBarPolicy(Context arg1) {
        super();
        this.mContext = arg1;
    }

    public boolean enableHomeButtonByDefault() {
        boolean v0 = this.mContext.getApplicationInfo().targetSdkVersion < 14 ? true : false;
        return v0;
    }

    public static ActionBarPolicy get(Context arg1) {
        return new ActionBarPolicy(arg1);
    }

    public int getEmbeddedMenuWidthLimit() {
        return this.mContext.getResources().getDisplayMetrics().widthPixels / 2;
    }

    public int getMaxActionButtons() {
        Configuration v0 = this.mContext.getResources().getConfiguration();
        int v1 = v0.screenWidthDp;
        int v2 = v0.screenHeightDp;
        int v3 = 600;
        if(v0.smallestScreenWidthDp <= v3 && v1 <= v3) {
            int v0_1 = 720;
            v3 = 960;
            if(v1 > v3 && v2 > v0_1) {
                return 5;
            }

            if(v1 > v0_1 && v2 > v3) {
                return 5;
            }

            if(v1 < 500) {
                v0_1 = 480;
                v3 = 640;
                if(v1 > v3 && v2 > v0_1) {
                    return 4;
                }

                if(v1 > v0_1 && v2 > v3) {
                    return 4;
                }

                if(v1 >= 360) {
                    return 3;
                }

                return 2;
            }

            return 4;
        }

        return 5;
    }

    public int getStackedTabMaxWidth() {
        return this.mContext.getResources().getDimensionPixelSize(dimen.abc_action_bar_stacked_tab_max_width);
    }

    public int getTabContainerHeight() {
        TypedArray v0 = this.mContext.obtainStyledAttributes(null, styleable.ActionBar, attr.actionBarStyle, 0);
        int v1 = v0.getLayoutDimension(styleable.ActionBar_height, 0);
        Resources v2 = this.mContext.getResources();
        if(!this.hasEmbeddedTabs()) {
            v1 = Math.min(v1, v2.getDimensionPixelSize(dimen.abc_action_bar_stacked_max_height));
        }

        v0.recycle();
        return v1;
    }

    public boolean hasEmbeddedTabs() {
        return this.mContext.getResources().getBoolean(bool.abc_action_bar_embed_tabs);
    }

    public boolean showsOverflowMenuButton() {
        if(Build$VERSION.SDK_INT >= 19) {
            return 1;
        }

        return ViewConfiguration.get(this.mContext).hasPermanentMenuKey() ^ 1;
    }
}

