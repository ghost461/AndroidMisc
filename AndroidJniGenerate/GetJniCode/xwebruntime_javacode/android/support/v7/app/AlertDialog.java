package android.support.v7.app;

import android.content.Context;
import android.content.DialogInterface$OnCancelListener;
import android.content.DialogInterface$OnClickListener;
import android.content.DialogInterface$OnDismissListener;
import android.content.DialogInterface$OnKeyListener;
import android.content.DialogInterface$OnMultiChoiceClickListener;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.ArrayRes;
import android.support.annotation.AttrRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.support.v7.appcompat.R$attr;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView$OnItemSelectedListener;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

public class AlertDialog extends AppCompatDialog implements DialogInterface {
    public class Builder {
        private final AlertParams P;
        private final int mTheme;

        public Builder(@NonNull Context arg2) {
            this(arg2, AlertDialog.resolveDialogTheme(arg2, 0));
        }

        public Builder(@NonNull Context arg4, @StyleRes int arg5) {
            super();
            this.P = new AlertParams(new ContextThemeWrapper(arg4, AlertDialog.resolveDialogTheme(arg4, arg5)));
            this.mTheme = arg5;
        }

        public AlertDialog create() {
            AlertDialog v0 = new AlertDialog(this.P.mContext, this.mTheme);
            this.P.apply(v0.mAlert);
            v0.setCancelable(this.P.mCancelable);
            if(this.P.mCancelable) {
                v0.setCanceledOnTouchOutside(true);
            }

            v0.setOnCancelListener(this.P.mOnCancelListener);
            v0.setOnDismissListener(this.P.mOnDismissListener);
            if(this.P.mOnKeyListener != null) {
                v0.setOnKeyListener(this.P.mOnKeyListener);
            }

            return v0;
        }

        @NonNull public Context getContext() {
            return this.P.mContext;
        }

        public Builder setAdapter(ListAdapter arg2, DialogInterface$OnClickListener arg3) {
            this.P.mAdapter = arg2;
            this.P.mOnClickListener = arg3;
            return this;
        }

        public Builder setCancelable(boolean arg2) {
            this.P.mCancelable = arg2;
            return this;
        }

        public Builder setCursor(Cursor arg2, DialogInterface$OnClickListener arg3, String arg4) {
            this.P.mCursor = arg2;
            this.P.mLabelColumn = arg4;
            this.P.mOnClickListener = arg3;
            return this;
        }

        public Builder setCustomTitle(@Nullable View arg2) {
            this.P.mCustomTitleView = arg2;
            return this;
        }

        public Builder setIcon(@Nullable Drawable arg2) {
            this.P.mIcon = arg2;
            return this;
        }

        public Builder setIcon(@DrawableRes int arg2) {
            this.P.mIconId = arg2;
            return this;
        }

        public Builder setIconAttribute(@AttrRes int arg4) {
            TypedValue v0 = new TypedValue();
            this.P.mContext.getTheme().resolveAttribute(arg4, v0, true);
            this.P.mIconId = v0.resourceId;
            return this;
        }

        @Deprecated public Builder setInverseBackgroundForced(boolean arg2) {
            this.P.mForceInverseBackground = arg2;
            return this;
        }

        public Builder setItems(@ArrayRes int arg3, DialogInterface$OnClickListener arg4) {
            this.P.mItems = this.P.mContext.getResources().getTextArray(arg3);
            this.P.mOnClickListener = arg4;
            return this;
        }

        public Builder setItems(CharSequence[] arg2, DialogInterface$OnClickListener arg3) {
            this.P.mItems = arg2;
            this.P.mOnClickListener = arg3;
            return this;
        }

        public Builder setMessage(@StringRes int arg3) {
            this.P.mMessage = this.P.mContext.getText(arg3);
            return this;
        }

        public Builder setMessage(@Nullable CharSequence arg2) {
            this.P.mMessage = arg2;
            return this;
        }

        public Builder setMultiChoiceItems(@ArrayRes int arg3, boolean[] arg4, DialogInterface$OnMultiChoiceClickListener arg5) {
            this.P.mItems = this.P.mContext.getResources().getTextArray(arg3);
            this.P.mOnCheckboxClickListener = arg5;
            this.P.mCheckedItems = arg4;
            this.P.mIsMultiChoice = true;
            return this;
        }

        public Builder setMultiChoiceItems(Cursor arg2, String arg3, String arg4, DialogInterface$OnMultiChoiceClickListener arg5) {
            this.P.mCursor = arg2;
            this.P.mOnCheckboxClickListener = arg5;
            this.P.mIsCheckedColumn = arg3;
            this.P.mLabelColumn = arg4;
            this.P.mIsMultiChoice = true;
            return this;
        }

        public Builder setMultiChoiceItems(CharSequence[] arg2, boolean[] arg3, DialogInterface$OnMultiChoiceClickListener arg4) {
            this.P.mItems = arg2;
            this.P.mOnCheckboxClickListener = arg4;
            this.P.mCheckedItems = arg3;
            this.P.mIsMultiChoice = true;
            return this;
        }

        public Builder setNegativeButton(@StringRes int arg3, DialogInterface$OnClickListener arg4) {
            this.P.mNegativeButtonText = this.P.mContext.getText(arg3);
            this.P.mNegativeButtonListener = arg4;
            return this;
        }

        public Builder setNegativeButton(CharSequence arg2, DialogInterface$OnClickListener arg3) {
            this.P.mNegativeButtonText = arg2;
            this.P.mNegativeButtonListener = arg3;
            return this;
        }

        public Builder setNeutralButton(@StringRes int arg3, DialogInterface$OnClickListener arg4) {
            this.P.mNeutralButtonText = this.P.mContext.getText(arg3);
            this.P.mNeutralButtonListener = arg4;
            return this;
        }

        public Builder setNeutralButton(CharSequence arg2, DialogInterface$OnClickListener arg3) {
            this.P.mNeutralButtonText = arg2;
            this.P.mNeutralButtonListener = arg3;
            return this;
        }

        public Builder setOnCancelListener(DialogInterface$OnCancelListener arg2) {
            this.P.mOnCancelListener = arg2;
            return this;
        }

        public Builder setOnDismissListener(DialogInterface$OnDismissListener arg2) {
            this.P.mOnDismissListener = arg2;
            return this;
        }

        public Builder setOnItemSelectedListener(AdapterView$OnItemSelectedListener arg2) {
            this.P.mOnItemSelectedListener = arg2;
            return this;
        }

        public Builder setOnKeyListener(DialogInterface$OnKeyListener arg2) {
            this.P.mOnKeyListener = arg2;
            return this;
        }

        public Builder setPositiveButton(@StringRes int arg3, DialogInterface$OnClickListener arg4) {
            this.P.mPositiveButtonText = this.P.mContext.getText(arg3);
            this.P.mPositiveButtonListener = arg4;
            return this;
        }

        public Builder setPositiveButton(CharSequence arg2, DialogInterface$OnClickListener arg3) {
            this.P.mPositiveButtonText = arg2;
            this.P.mPositiveButtonListener = arg3;
            return this;
        }

        @RestrictTo(value={Scope.LIBRARY_GROUP}) public Builder setRecycleOnMeasureEnabled(boolean arg2) {
            this.P.mRecycleOnMeasure = arg2;
            return this;
        }

        public Builder setSingleChoiceItems(@ArrayRes int arg3, int arg4, DialogInterface$OnClickListener arg5) {
            this.P.mItems = this.P.mContext.getResources().getTextArray(arg3);
            this.P.mOnClickListener = arg5;
            this.P.mCheckedItem = arg4;
            this.P.mIsSingleChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(Cursor arg2, int arg3, String arg4, DialogInterface$OnClickListener arg5) {
            this.P.mCursor = arg2;
            this.P.mOnClickListener = arg5;
            this.P.mCheckedItem = arg3;
            this.P.mLabelColumn = arg4;
            this.P.mIsSingleChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(ListAdapter arg2, int arg3, DialogInterface$OnClickListener arg4) {
            this.P.mAdapter = arg2;
            this.P.mOnClickListener = arg4;
            this.P.mCheckedItem = arg3;
            this.P.mIsSingleChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(CharSequence[] arg2, int arg3, DialogInterface$OnClickListener arg4) {
            this.P.mItems = arg2;
            this.P.mOnClickListener = arg4;
            this.P.mCheckedItem = arg3;
            this.P.mIsSingleChoice = true;
            return this;
        }

        public Builder setTitle(@Nullable CharSequence arg2) {
            this.P.mTitle = arg2;
            return this;
        }

        public Builder setTitle(@StringRes int arg3) {
            this.P.mTitle = this.P.mContext.getText(arg3);
            return this;
        }

        public Builder setView(int arg3) {
            this.P.mView = null;
            this.P.mViewLayoutResId = arg3;
            this.P.mViewSpacingSpecified = false;
            return this;
        }

        public Builder setView(View arg2) {
            this.P.mView = arg2;
            this.P.mViewLayoutResId = 0;
            this.P.mViewSpacingSpecified = false;
            return this;
        }

        @RestrictTo(value={Scope.LIBRARY_GROUP}) @Deprecated public Builder setView(View arg2, int arg3, int arg4, int arg5, int arg6) {
            this.P.mView = arg2;
            this.P.mViewLayoutResId = 0;
            this.P.mViewSpacingSpecified = true;
            this.P.mViewSpacingLeft = arg3;
            this.P.mViewSpacingTop = arg4;
            this.P.mViewSpacingRight = arg5;
            this.P.mViewSpacingBottom = arg6;
            return this;
        }

        public AlertDialog show() {
            AlertDialog v0 = this.create();
            v0.show();
            return v0;
        }
    }

    static final int LAYOUT_HINT_NONE = 0;
    static final int LAYOUT_HINT_SIDE = 1;
    final AlertController mAlert;

    protected AlertDialog(@NonNull Context arg2) {
        this(arg2, 0);
    }

    protected AlertDialog(@NonNull Context arg2, @StyleRes int arg3) {
        super(arg2, AlertDialog.resolveDialogTheme(arg2, arg3));
        this.mAlert = new AlertController(this.getContext(), ((AppCompatDialog)this), this.getWindow());
    }

    protected AlertDialog(@NonNull Context arg2, boolean arg3, @Nullable DialogInterface$OnCancelListener arg4) {
        this(arg2, 0);
        this.setCancelable(arg3);
        this.setOnCancelListener(arg4);
    }

    public Button getButton(int arg2) {
        return this.mAlert.getButton(arg2);
    }

    public ListView getListView() {
        return this.mAlert.getListView();
    }

    protected void onCreate(Bundle arg1) {
        super.onCreate(arg1);
        this.mAlert.installContent();
    }

    public boolean onKeyDown(int arg2, KeyEvent arg3) {
        if(this.mAlert.onKeyDown(arg2, arg3)) {
            return 1;
        }

        return super.onKeyDown(arg2, arg3);
    }

    public boolean onKeyUp(int arg2, KeyEvent arg3) {
        if(this.mAlert.onKeyUp(arg2, arg3)) {
            return 1;
        }

        return super.onKeyUp(arg2, arg3);
    }

    static int resolveDialogTheme(@NonNull Context arg2, @StyleRes int arg3) {
        if((arg3 >>> 24 & 0xFF) >= 1) {
            return arg3;
        }

        TypedValue v3 = new TypedValue();
        arg2.getTheme().resolveAttribute(attr.alertDialogTheme, v3, true);
        return v3.resourceId;
    }

    public void setButton(int arg3, CharSequence arg4, DialogInterface$OnClickListener arg5) {
        this.mAlert.setButton(arg3, arg4, arg5, null);
    }

    public void setButton(int arg3, CharSequence arg4, Message arg5) {
        this.mAlert.setButton(arg3, arg4, null, arg5);
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) void setButtonPanelLayoutHint(int arg2) {
        this.mAlert.setButtonPanelLayoutHint(arg2);
    }

    public void setCustomTitle(View arg2) {
        this.mAlert.setCustomTitle(arg2);
    }

    public void setIcon(int arg2) {
        this.mAlert.setIcon(arg2);
    }

    public void setIcon(Drawable arg2) {
        this.mAlert.setIcon(arg2);
    }

    public void setIconAttribute(int arg4) {
        TypedValue v0 = new TypedValue();
        this.getContext().getTheme().resolveAttribute(arg4, v0, true);
        this.mAlert.setIcon(v0.resourceId);
    }

    public void setMessage(CharSequence arg2) {
        this.mAlert.setMessage(arg2);
    }

    public void setTitle(CharSequence arg2) {
        super.setTitle(arg2);
        this.mAlert.setTitle(arg2);
    }

    public void setView(View arg2) {
        this.mAlert.setView(arg2);
    }

    public void setView(View arg7, int arg8, int arg9, int arg10, int arg11) {
        this.mAlert.setView(arg7, arg8, arg9, arg10, arg11);
    }
}

