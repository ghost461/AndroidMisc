package android.support.v4.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface$OnCancelListener;
import android.content.DialogInterface$OnDismissListener;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.annotation.StyleRes;
import android.view.LayoutInflater;
import android.view.View;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class DialogFragment extends Fragment implements DialogInterface$OnCancelListener, DialogInterface$OnDismissListener {
    @RestrictTo(value={Scope.LIBRARY_GROUP}) @Retention(value=RetentionPolicy.SOURCE) @interface DialogStyle {
    }

    private static final String SAVED_BACK_STACK_ID = "android:backStackId";
    private static final String SAVED_CANCELABLE = "android:cancelable";
    private static final String SAVED_DIALOG_STATE_TAG = "android:savedDialogState";
    private static final String SAVED_SHOWS_DIALOG = "android:showsDialog";
    private static final String SAVED_STYLE = "android:style";
    private static final String SAVED_THEME = "android:theme";
    public static final int STYLE_NORMAL = 0;
    public static final int STYLE_NO_FRAME = 2;
    public static final int STYLE_NO_INPUT = 3;
    public static final int STYLE_NO_TITLE = 1;
    int mBackStackId;
    boolean mCancelable;
    Dialog mDialog;
    boolean mDismissed;
    boolean mShownByMe;
    boolean mShowsDialog;
    int mStyle;
    int mTheme;
    boolean mViewDestroyed;

    public DialogFragment() {
        super();
        this.mStyle = 0;
        this.mTheme = 0;
        this.mCancelable = true;
        this.mShowsDialog = true;
        this.mBackStackId = -1;
    }

    public void dismiss() {
        this.dismissInternal(false);
    }

    public void dismissAllowingStateLoss() {
        this.dismissInternal(true);
    }

    void dismissInternal(boolean arg3) {
        if(this.mDismissed) {
            return;
        }

        this.mDismissed = true;
        this.mShownByMe = false;
        if(this.mDialog != null) {
            this.mDialog.dismiss();
            this.mDialog = null;
        }

        this.mViewDestroyed = true;
        if(this.mBackStackId >= 0) {
            this.getFragmentManager().popBackStack(this.mBackStackId, 1);
            this.mBackStackId = -1;
        }
        else {
            FragmentTransaction v0 = this.getFragmentManager().beginTransaction();
            v0.remove(((Fragment)this));
            if(arg3) {
                v0.commitAllowingStateLoss();
            }
            else {
                v0.commit();
            }
        }
    }

    public Dialog getDialog() {
        return this.mDialog;
    }

    public boolean getShowsDialog() {
        return this.mShowsDialog;
    }

    @StyleRes public int getTheme() {
        return this.mTheme;
    }

    public boolean isCancelable() {
        return this.mCancelable;
    }

    public void onActivityCreated(Bundle arg3) {
        super.onActivityCreated(arg3);
        if(!this.mShowsDialog) {
            return;
        }

        View v0 = this.getView();
        if(v0 != null) {
            if(v0.getParent() != null) {
                throw new IllegalStateException("DialogFragment can not be attached to a container view");
            }
            else {
                this.mDialog.setContentView(v0);
            }
        }

        FragmentActivity v0_1 = this.getActivity();
        if(v0_1 != null) {
            this.mDialog.setOwnerActivity(((Activity)v0_1));
        }

        this.mDialog.setCancelable(this.mCancelable);
        this.mDialog.setOnCancelListener(((DialogInterface$OnCancelListener)this));
        this.mDialog.setOnDismissListener(((DialogInterface$OnDismissListener)this));
        if(arg3 != null) {
            arg3 = arg3.getBundle("android:savedDialogState");
            if(arg3 != null) {
                this.mDialog.onRestoreInstanceState(arg3);
            }
        }
    }

    public void onAttach(Context arg1) {
        super.onAttach(arg1);
        if(!this.mShownByMe) {
            this.mDismissed = false;
        }
    }

    public void onCancel(DialogInterface arg1) {
    }

    public void onCreate(@Nullable Bundle arg4) {
        super.onCreate(arg4);
        boolean v0 = this.mContainerId == 0 ? true : false;
        this.mShowsDialog = v0;
        if(arg4 != null) {
            this.mStyle = arg4.getInt("android:style", 0);
            this.mTheme = arg4.getInt("android:theme", 0);
            this.mCancelable = arg4.getBoolean("android:cancelable", true);
            this.mShowsDialog = arg4.getBoolean("android:showsDialog", this.mShowsDialog);
            this.mBackStackId = arg4.getInt("android:backStackId", -1);
        }
    }

    @NonNull public Dialog onCreateDialog(Bundle arg3) {
        return new Dialog(this.getActivity(), this.getTheme());
    }

    public void onDestroyView() {
        super.onDestroyView();
        if(this.mDialog != null) {
            this.mViewDestroyed = true;
            this.mDialog.dismiss();
            this.mDialog = null;
        }
    }

    public void onDetach() {
        super.onDetach();
        if(!this.mShownByMe && !this.mDismissed) {
            this.mDismissed = true;
        }
    }

    public void onDismiss(DialogInterface arg1) {
        if(!this.mViewDestroyed) {
            this.dismissInternal(true);
        }
    }

    public LayoutInflater onGetLayoutInflater(Bundle arg2) {
        if(!this.mShowsDialog) {
            return super.onGetLayoutInflater(arg2);
        }

        this.mDialog = this.onCreateDialog(arg2);
        if(this.mDialog != null) {
            this.setupDialog(this.mDialog, this.mStyle);
            return this.mDialog.getContext().getSystemService("layout_inflater");
        }

        return this.mHost.getContext().getSystemService("layout_inflater");
    }

    public void onSaveInstanceState(Bundle arg3) {
        super.onSaveInstanceState(arg3);
        if(this.mDialog != null) {
            Bundle v0 = this.mDialog.onSaveInstanceState();
            if(v0 != null) {
                arg3.putBundle("android:savedDialogState", v0);
            }
        }

        if(this.mStyle != 0) {
            arg3.putInt("android:style", this.mStyle);
        }

        if(this.mTheme != 0) {
            arg3.putInt("android:theme", this.mTheme);
        }

        if(!this.mCancelable) {
            arg3.putBoolean("android:cancelable", this.mCancelable);
        }

        if(!this.mShowsDialog) {
            arg3.putBoolean("android:showsDialog", this.mShowsDialog);
        }

        if(this.mBackStackId != -1) {
            arg3.putInt("android:backStackId", this.mBackStackId);
        }
    }

    public void onStart() {
        super.onStart();
        if(this.mDialog != null) {
            this.mViewDestroyed = false;
            this.mDialog.show();
        }
    }

    public void onStop() {
        super.onStop();
        if(this.mDialog != null) {
            this.mDialog.hide();
        }
    }

    public void setCancelable(boolean arg2) {
        this.mCancelable = arg2;
        if(this.mDialog != null) {
            this.mDialog.setCancelable(arg2);
        }
    }

    public void setShowsDialog(boolean arg1) {
        this.mShowsDialog = arg1;
    }

    public void setStyle(int arg2, @StyleRes int arg3) {
        this.mStyle = arg2;
        if(this.mStyle == 2 || this.mStyle == 3) {
            this.mTheme = 0x1030059;
        }

        if(arg3 != 0) {
            this.mTheme = arg3;
        }
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public void setupDialog(Dialog arg2, int arg3) {
        switch(arg3) {
            case 1: 
            case 2: {
                goto label_5;
            }
            case 3: {
                goto label_2;
            }
        }

        return;
    label_2:
        arg2.getWindow().addFlags(24);
    label_5:
        arg2.requestWindowFeature(1);
    }

    public int show(FragmentTransaction arg3, String arg4) {
        this.mDismissed = false;
        this.mShownByMe = true;
        arg3.add(((Fragment)this), arg4);
        this.mViewDestroyed = false;
        this.mBackStackId = arg3.commit();
        return this.mBackStackId;
    }

    public void show(FragmentManager arg2, String arg3) {
        this.mDismissed = false;
        this.mShownByMe = true;
        FragmentTransaction v2 = arg2.beginTransaction();
        v2.add(((Fragment)this), arg3);
        v2.commit();
    }
}

