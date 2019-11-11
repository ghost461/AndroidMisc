package android.support.v7.view;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources$Theme;
import android.content.res.Resources;
import android.os.Build$VERSION;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.annotation.StyleRes;
import android.support.v7.appcompat.R$style;
import android.view.LayoutInflater;

@RestrictTo(value={Scope.LIBRARY_GROUP}) public class ContextThemeWrapper extends ContextWrapper {
    private LayoutInflater mInflater;
    private Configuration mOverrideConfiguration;
    private Resources mResources;
    private Resources$Theme mTheme;
    private int mThemeResource;

    public ContextThemeWrapper(Context arg1, @StyleRes int arg2) {
        super(arg1);
        this.mThemeResource = arg2;
    }

    public ContextThemeWrapper() {
        super(null);
    }

    public ContextThemeWrapper(Context arg1, Resources$Theme arg2) {
        super(arg1);
        this.mTheme = arg2;
    }

    public void applyOverrideConfiguration(Configuration arg2) {
        if(this.mResources != null) {
            throw new IllegalStateException("getResources() or getAssets() has already been called");
        }

        if(this.mOverrideConfiguration != null) {
            throw new IllegalStateException("Override configuration has already been set");
        }

        this.mOverrideConfiguration = new Configuration(arg2);
    }

    protected void attachBaseContext(Context arg1) {
        super.attachBaseContext(arg1);
    }

    public AssetManager getAssets() {
        return this.getResources().getAssets();
    }

    public Configuration getOverrideConfiguration() {
        return this.mOverrideConfiguration;
    }

    public Resources getResources() {
        return this.getResourcesInternal();
    }

    private Resources getResourcesInternal() {
        if(this.mResources == null) {
            if(this.mOverrideConfiguration == null) {
                this.mResources = super.getResources();
            }
            else if(Build$VERSION.SDK_INT >= 17) {
                this.mResources = this.createConfigurationContext(this.mOverrideConfiguration).getResources();
            }
        }

        return this.mResources;
    }

    public Object getSystemService(String arg2) {
        if("layout_inflater".equals(arg2)) {
            if(this.mInflater == null) {
                this.mInflater = LayoutInflater.from(this.getBaseContext()).cloneInContext(((Context)this));
            }

            return this.mInflater;
        }

        return this.getBaseContext().getSystemService(arg2);
    }

    public Resources$Theme getTheme() {
        if(this.mTheme != null) {
            return this.mTheme;
        }

        if(this.mThemeResource == 0) {
            this.mThemeResource = style.Theme_AppCompat_Light;
        }

        this.initializeTheme();
        return this.mTheme;
    }

    public int getThemeResId() {
        return this.mThemeResource;
    }

    private void initializeTheme() {
        boolean v0 = this.mTheme == null ? true : false;
        if(v0) {
            this.mTheme = this.getResources().newTheme();
            Resources$Theme v1 = this.getBaseContext().getTheme();
            if(v1 != null) {
                this.mTheme.setTo(v1);
            }
        }

        this.onApplyThemeResource(this.mTheme, this.mThemeResource, v0);
    }

    protected void onApplyThemeResource(Resources$Theme arg1, int arg2, boolean arg3) {
        arg1.applyStyle(arg2, true);
    }

    public void setTheme(int arg2) {
        if(this.mThemeResource != arg2) {
            this.mThemeResource = arg2;
            this.initializeTheme();
        }
    }
}

