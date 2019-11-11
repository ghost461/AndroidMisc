package android.support.v7.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder$SupportParentable;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.view.ActionMode$Callback;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.VectorEnabledTintResources;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.view.Window;

public class AppCompatActivity extends FragmentActivity implements SupportParentable, DelegateProvider, AppCompatCallback {
    private AppCompatDelegate mDelegate;
    private Resources mResources;
    private int mThemeId;

    public AppCompatActivity() {
        super();
        this.mThemeId = 0;
    }

    public void addContentView(View arg2, ViewGroup$LayoutParams arg3) {
        this.getDelegate().addContentView(arg2, arg3);
    }

    public void closeOptionsMenu() {
        ActionBar v0 = this.getSupportActionBar();
        if((this.getWindow().hasFeature(0)) && (v0 == null || !v0.closeOptionsMenu())) {
            super.closeOptionsMenu();
        }
    }

    public boolean dispatchKeyEvent(KeyEvent arg4) {
        int v0 = arg4.getKeyCode();
        ActionBar v1 = this.getSupportActionBar();
        if(v0 == 82 && v1 != null && (v1.onMenuKeyEvent(arg4))) {
            return 1;
        }

        return super.dispatchKeyEvent(arg4);
    }

    public View findViewById(@IdRes int arg2) {
        return this.getDelegate().findViewById(arg2);
    }

    @NonNull public AppCompatDelegate getDelegate() {
        if(this.mDelegate == null) {
            this.mDelegate = AppCompatDelegate.create(((Activity)this), ((AppCompatCallback)this));
        }

        return this.mDelegate;
    }

    @Nullable public Delegate getDrawerToggleDelegate() {
        return this.getDelegate().getDrawerToggleDelegate();
    }

    public MenuInflater getMenuInflater() {
        return this.getDelegate().getMenuInflater();
    }

    public Resources getResources() {
        if(this.mResources == null && (VectorEnabledTintResources.shouldBeUsed())) {
            this.mResources = new VectorEnabledTintResources(((Context)this), super.getResources());
        }

        Resources v0 = this.mResources == null ? super.getResources() : this.mResources;
        return v0;
    }

    @Nullable public ActionBar getSupportActionBar() {
        return this.getDelegate().getSupportActionBar();
    }

    @Nullable public Intent getSupportParentActivityIntent() {
        return NavUtils.getParentActivityIntent(((Activity)this));
    }

    public void invalidateOptionsMenu() {
        this.getDelegate().invalidateOptionsMenu();
    }

    public void onConfigurationChanged(Configuration arg3) {
        super.onConfigurationChanged(arg3);
        this.getDelegate().onConfigurationChanged(arg3);
        if(this.mResources != null) {
            this.mResources.updateConfiguration(arg3, super.getResources().getDisplayMetrics());
        }
    }

    public void onContentChanged() {
        this.onSupportContentChanged();
    }

    protected void onCreate(@Nullable Bundle arg4) {
        AppCompatDelegate v0 = this.getDelegate();
        v0.installViewFactory();
        v0.onCreate(arg4);
        if((v0.applyDayNight()) && this.mThemeId != 0) {
            if(Build$VERSION.SDK_INT >= 23) {
                this.onApplyThemeResource(this.getTheme(), this.mThemeId, false);
            }
            else {
                this.setTheme(this.mThemeId);
            }
        }

        super.onCreate(arg4);
    }

    public void onCreateSupportNavigateUpTaskStack(@NonNull TaskStackBuilder arg1) {
        arg1.addParentStack(((Activity)this));
    }

    protected void onDestroy() {
        super.onDestroy();
        this.getDelegate().onDestroy();
    }

    public boolean onKeyDown(int arg2, KeyEvent arg3) {
        if(this.performMenuItemShortcut(arg2, arg3)) {
            return 1;
        }

        return super.onKeyDown(arg2, arg3);
    }

    public final boolean onMenuItemSelected(int arg2, MenuItem arg3) {
        if(super.onMenuItemSelected(arg2, arg3)) {
            return 1;
        }

        ActionBar v2 = this.getSupportActionBar();
        if(arg3.getItemId() == 0x102002C && v2 != null && (v2.getDisplayOptions() & 4) != 0) {
            return this.onSupportNavigateUp();
        }

        return 0;
    }

    public boolean onMenuOpened(int arg1, Menu arg2) {
        return super.onMenuOpened(arg1, arg2);
    }

    public void onPanelClosed(int arg1, Menu arg2) {
        super.onPanelClosed(arg1, arg2);
    }

    protected void onPostCreate(@Nullable Bundle arg2) {
        super.onPostCreate(arg2);
        this.getDelegate().onPostCreate(arg2);
    }

    protected void onPostResume() {
        super.onPostResume();
        this.getDelegate().onPostResume();
    }

    public void onPrepareSupportNavigateUpTaskStack(@NonNull TaskStackBuilder arg1) {
    }

    protected void onSaveInstanceState(Bundle arg2) {
        super.onSaveInstanceState(arg2);
        this.getDelegate().onSaveInstanceState(arg2);
    }

    protected void onStart() {
        super.onStart();
        this.getDelegate().onStart();
    }

    protected void onStop() {
        super.onStop();
        this.getDelegate().onStop();
    }

    @CallSuper public void onSupportActionModeFinished(@NonNull ActionMode arg1) {
    }

    @CallSuper public void onSupportActionModeStarted(@NonNull ActionMode arg1) {
    }

    @Deprecated public void onSupportContentChanged() {
    }

    public boolean onSupportNavigateUp() {
        Intent v0 = this.getSupportParentActivityIntent();
        if(v0 != null) {
            if(this.supportShouldUpRecreateTask(v0)) {
                TaskStackBuilder v0_1 = TaskStackBuilder.create(((Context)this));
                this.onCreateSupportNavigateUpTaskStack(v0_1);
                this.onPrepareSupportNavigateUpTaskStack(v0_1);
                v0_1.startActivities();
                try {
                    ActivityCompat.finishAffinity(((Activity)this));
                }
                catch(IllegalStateException ) {
                    this.finish();
                }
            }
            else {
                this.supportNavigateUpTo(v0);
            }

            return 1;
        }

        return 0;
    }

    protected void onTitleChanged(CharSequence arg1, int arg2) {
        super.onTitleChanged(arg1, arg2);
        this.getDelegate().setTitle(arg1);
    }

    @Nullable public ActionMode onWindowStartingSupportActionMode(@NonNull Callback arg1) {
        return null;
    }

    public void openOptionsMenu() {
        ActionBar v0 = this.getSupportActionBar();
        if((this.getWindow().hasFeature(0)) && (v0 == null || !v0.openOptionsMenu())) {
            super.openOptionsMenu();
        }
    }

    private boolean performMenuItemShortcut(int arg2, KeyEvent arg3) {
        if(Build$VERSION.SDK_INT < 26 && !arg3.isCtrlPressed() && !KeyEvent.metaStateHasNoModifiers(arg3.getMetaState()) && arg3.getRepeatCount() == 0 && !KeyEvent.isModifierKey(arg3.getKeyCode())) {
            Window v2 = this.getWindow();
            if(v2 != null && v2.getDecorView() != null && (v2.getDecorView().dispatchKeyShortcutEvent(arg3))) {
                return 1;
            }
        }

        return 0;
    }

    public void setContentView(@LayoutRes int arg2) {
        this.getDelegate().setContentView(arg2);
    }

    public void setContentView(View arg2) {
        this.getDelegate().setContentView(arg2);
    }

    public void setContentView(View arg2, ViewGroup$LayoutParams arg3) {
        this.getDelegate().setContentView(arg2, arg3);
    }

    public void setSupportActionBar(@Nullable Toolbar arg2) {
        this.getDelegate().setSupportActionBar(arg2);
    }

    @Deprecated public void setSupportProgress(int arg1) {
    }

    @Deprecated public void setSupportProgressBarIndeterminate(boolean arg1) {
    }

    @Deprecated public void setSupportProgressBarIndeterminateVisibility(boolean arg1) {
    }

    @Deprecated public void setSupportProgressBarVisibility(boolean arg1) {
    }

    public void setTheme(@StyleRes int arg1) {
        super.setTheme(arg1);
        this.mThemeId = arg1;
    }

    @Nullable public ActionMode startSupportActionMode(@NonNull Callback arg2) {
        return this.getDelegate().startSupportActionMode(arg2);
    }

    public void supportInvalidateOptionsMenu() {
        this.getDelegate().invalidateOptionsMenu();
    }

    public void supportNavigateUpTo(@NonNull Intent arg1) {
        NavUtils.navigateUpTo(((Activity)this), arg1);
    }

    public boolean supportRequestWindowFeature(int arg2) {
        return this.getDelegate().requestWindowFeature(arg2);
    }

    public boolean supportShouldUpRecreateTask(@NonNull Intent arg1) {
        return NavUtils.shouldUpRecreateTask(((Activity)this), arg1);
    }
}

