package android.support.v7.app;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface$OnCancelListener;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.v7.appcompat.R$attr;
import android.support.v7.view.ActionMode$Callback;
import android.support.v7.view.ActionMode;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup$LayoutParams;

public class AppCompatDialog extends Dialog implements AppCompatCallback {
    private AppCompatDelegate mDelegate;

    public AppCompatDialog(Context arg2) {
        this(arg2, 0);
    }

    public AppCompatDialog(Context arg1, int arg2) {
        super(arg1, AppCompatDialog.getThemeResId(arg1, arg2));
        this.getDelegate().onCreate(null);
        this.getDelegate().applyDayNight();
    }

    protected AppCompatDialog(Context arg1, boolean arg2, DialogInterface$OnCancelListener arg3) {
        super(arg1, arg2, arg3);
    }

    public void addContentView(View arg2, ViewGroup$LayoutParams arg3) {
        this.getDelegate().addContentView(arg2, arg3);
    }

    @Nullable public View findViewById(@IdRes int arg2) {
        return this.getDelegate().findViewById(arg2);
    }

    public AppCompatDelegate getDelegate() {
        if(this.mDelegate == null) {
            this.mDelegate = AppCompatDelegate.create(((Dialog)this), ((AppCompatCallback)this));
        }

        return this.mDelegate;
    }

    public ActionBar getSupportActionBar() {
        return this.getDelegate().getSupportActionBar();
    }

    private static int getThemeResId(Context arg2, int arg3) {
        if(arg3 == 0) {
            TypedValue v3 = new TypedValue();
            arg2.getTheme().resolveAttribute(attr.dialogTheme, v3, true);
            arg3 = v3.resourceId;
        }

        return arg3;
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public void invalidateOptionsMenu() {
        this.getDelegate().invalidateOptionsMenu();
    }

    protected void onCreate(Bundle arg2) {
        this.getDelegate().installViewFactory();
        super.onCreate(arg2);
        this.getDelegate().onCreate(arg2);
    }

    protected void onStop() {
        super.onStop();
        this.getDelegate().onStop();
    }

    public void onSupportActionModeFinished(ActionMode arg1) {
    }

    public void onSupportActionModeStarted(ActionMode arg1) {
    }

    @Nullable public ActionMode onWindowStartingSupportActionMode(Callback arg1) {
        return null;
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

    public void setTitle(int arg3) {
        super.setTitle(arg3);
        this.getDelegate().setTitle(this.getContext().getString(arg3));
    }

    public void setTitle(CharSequence arg2) {
        super.setTitle(arg2);
        this.getDelegate().setTitle(arg2);
    }

    public boolean supportRequestWindowFeature(int arg2) {
        return this.getDelegate().requestWindowFeature(arg2);
    }
}

