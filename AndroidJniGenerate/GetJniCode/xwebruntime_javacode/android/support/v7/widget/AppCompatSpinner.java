package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources$Theme;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.PorterDuff$Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.v4.view.TintableBackgroundView;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R$attr;
import android.support.v7.appcompat.R$layout;
import android.support.v7.appcompat.R$styleable;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.view.menu.ShowableListMenu;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View$MeasureSpec;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.view.ViewTreeObserver;
import android.widget.Adapter;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.PopupWindow$OnDismissListener;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.ThemedSpinnerAdapter;

public class AppCompatSpinner extends Spinner implements TintableBackgroundView {
    class DropDownAdapter implements ListAdapter, SpinnerAdapter {
        private SpinnerAdapter mAdapter;
        private ListAdapter mListAdapter;

        public DropDownAdapter(@Nullable SpinnerAdapter arg3, @Nullable Resources$Theme arg4) {
            super();
            this.mAdapter = arg3;
            if((arg3 instanceof ListAdapter)) {
                this.mListAdapter = arg3;
            }

            if(arg4 != null) {
                if(Build$VERSION.SDK_INT >= 23 && ((arg3 instanceof ThemedSpinnerAdapter))) {
                    if(((ThemedSpinnerAdapter)arg3).getDropDownViewTheme() != arg4) {
                        ((ThemedSpinnerAdapter)arg3).setDropDownViewTheme(arg4);
                    }
                    else {
                    }

                    return;
                }

                if(!(arg3 instanceof android.support.v7.widget.ThemedSpinnerAdapter)) {
                    return;
                }

                if(((android.support.v7.widget.ThemedSpinnerAdapter)arg3).getDropDownViewTheme() != null) {
                    return;
                }

                ((android.support.v7.widget.ThemedSpinnerAdapter)arg3).setDropDownViewTheme(arg4);
            }
        }

        public boolean areAllItemsEnabled() {
            ListAdapter v0 = this.mListAdapter;
            if(v0 != null) {
                return v0.areAllItemsEnabled();
            }

            return 1;
        }

        public int getCount() {
            int v0 = this.mAdapter == null ? 0 : this.mAdapter.getCount();
            return v0;
        }

        public View getDropDownView(int arg2, View arg3, ViewGroup arg4) {
            View v2 = this.mAdapter == null ? null : this.mAdapter.getDropDownView(arg2, arg3, arg4);
            return v2;
        }

        public Object getItem(int arg2) {
            Object v2 = this.mAdapter == null ? null : this.mAdapter.getItem(arg2);
            return v2;
        }

        public long getItemId(int arg3) {
            long v0 = this.mAdapter == null ? -1 : this.mAdapter.getItemId(arg3);
            return v0;
        }

        public int getItemViewType(int arg1) {
            return 0;
        }

        public View getView(int arg1, View arg2, ViewGroup arg3) {
            return this.getDropDownView(arg1, arg2, arg3);
        }

        public int getViewTypeCount() {
            return 1;
        }

        public boolean hasStableIds() {
            boolean v0 = this.mAdapter == null || !this.mAdapter.hasStableIds() ? false : true;
            return v0;
        }

        public boolean isEmpty() {
            boolean v0 = this.getCount() == 0 ? true : false;
            return v0;
        }

        public boolean isEnabled(int arg2) {
            ListAdapter v0 = this.mListAdapter;
            if(v0 != null) {
                return v0.isEnabled(arg2);
            }

            return 1;
        }

        public void registerDataSetObserver(DataSetObserver arg2) {
            if(this.mAdapter != null) {
                this.mAdapter.registerDataSetObserver(arg2);
            }
        }

        public void unregisterDataSetObserver(DataSetObserver arg2) {
            if(this.mAdapter != null) {
                this.mAdapter.unregisterDataSetObserver(arg2);
            }
        }
    }

    class DropdownPopup extends ListPopupWindow {
        ListAdapter mAdapter;
        private CharSequence mHintText;
        private final Rect mVisibleRect;

        public DropdownPopup(AppCompatSpinner arg1, Context arg2, AttributeSet arg3, int arg4) {
            AppCompatSpinner.this = arg1;
            super(arg2, arg3, arg4);
            this.mVisibleRect = new Rect();
            this.setAnchorView(((View)arg1));
            this.setModal(true);
            this.setPromptPosition(0);
            this.setOnItemClickListener(new AdapterView$OnItemClickListener(arg1) {
                public void onItemClick(AdapterView arg1, View arg2, int arg3, long arg4) {
                    this.this$1.this$0.setSelection(arg3);
                    if(this.this$1.this$0.getOnItemClickListener() != null) {
                        this.this$1.this$0.performItemClick(arg2, arg3, this.this$1.mAdapter.getItemId(arg3));
                    }

                    this.this$1.dismiss();
                }
            });
        }

        static void access$301(DropdownPopup arg0) {
            super.show();
        }

        void computeContentWidth() {
            int v0_1;
            Drawable v0 = this.getBackground();
            int v1 = 0;
            if(v0 != null) {
                v0.getPadding(AppCompatSpinner.this.mTempRect);
                v0_1 = ViewUtils.isLayoutRtl(AppCompatSpinner.this) ? AppCompatSpinner.this.mTempRect.right : -AppCompatSpinner.this.mTempRect.left;
                v1 = v0_1;
            }
            else {
                Rect v0_2 = AppCompatSpinner.this.mTempRect;
                AppCompatSpinner.this.mTempRect.right = 0;
                v0_2.left = 0;
            }

            v0_1 = AppCompatSpinner.this.getPaddingLeft();
            int v2 = AppCompatSpinner.this.getPaddingRight();
            int v3 = AppCompatSpinner.this.getWidth();
            if(AppCompatSpinner.this.mDropDownWidth == -2) {
                int v4 = AppCompatSpinner.this.compatMeasureContentWidth(this.mAdapter, this.getBackground());
                int v5 = AppCompatSpinner.this.getContext().getResources().getDisplayMetrics().widthPixels - AppCompatSpinner.this.mTempRect.left - AppCompatSpinner.this.mTempRect.right;
                if(v4 > v5) {
                    v4 = v5;
                }

                this.setContentWidth(Math.max(v4, v3 - v0_1 - v2));
            }
            else {
                if(AppCompatSpinner.this.mDropDownWidth == -1) {
                    this.setContentWidth(v3 - v0_1 - v2);
                    goto label_70;
                }

                this.setContentWidth(AppCompatSpinner.this.mDropDownWidth);
            }

        label_70:
            v1 += ViewUtils.isLayoutRtl(AppCompatSpinner.this) ? v3 - v2 - this.getWidth() : v0_1;
            this.setHorizontalOffset(v1);
        }

        public CharSequence getHintText() {
            return this.mHintText;
        }

        boolean isVisibleToUser(View arg2) {
            boolean v2 = !ViewCompat.isAttachedToWindow(arg2) || !arg2.getGlobalVisibleRect(this.mVisibleRect) ? false : true;
            return v2;
        }

        public void setAdapter(ListAdapter arg1) {
            super.setAdapter(arg1);
            this.mAdapter = arg1;
        }

        public void setPromptText(CharSequence arg1) {
            this.mHintText = arg1;
        }

        public void show() {
            boolean v0 = this.isShowing();
            this.computeContentWidth();
            this.setInputMethodMode(2);
            super.show();
            this.getListView().setChoiceMode(1);
            this.setSelection(AppCompatSpinner.this.getSelectedItemPosition());
            if(v0) {
                return;
            }

            ViewTreeObserver v0_1 = AppCompatSpinner.this.getViewTreeObserver();
            if(v0_1 != null) {
                android.support.v7.widget.AppCompatSpinner$DropdownPopup$2 v1 = new ViewTreeObserver$OnGlobalLayoutListener() {
                    public void onGlobalLayout() {
                        if(!this.this$1.isVisibleToUser(this.this$1.this$0)) {
                            this.this$1.dismiss();
                        }
                        else {
                            this.this$1.computeContentWidth();
                            DropdownPopup.access$301(this.this$1);
                        }
                    }
                };
                v0_1.addOnGlobalLayoutListener(((ViewTreeObserver$OnGlobalLayoutListener)v1));
                this.setOnDismissListener(new PopupWindow$OnDismissListener(((ViewTreeObserver$OnGlobalLayoutListener)v1)) {
                    public void onDismiss() {
                        ViewTreeObserver v0 = this.this$1.this$0.getViewTreeObserver();
                        if(v0 != null) {
                            v0.removeGlobalOnLayoutListener(this.val$layoutListener);
                        }
                    }
                });
            }
        }
    }

    private static final int[] ATTRS_ANDROID_SPINNERMODE = null;
    private static final int MAX_ITEMS_MEASURED = 15;
    private static final int MODE_DIALOG = 0;
    private static final int MODE_DROPDOWN = 1;
    private static final int MODE_THEME = -1;
    private static final String TAG = "AppCompatSpinner";
    private final AppCompatBackgroundHelper mBackgroundTintHelper;
    private int mDropDownWidth;
    private ForwardingListener mForwardingListener;
    private DropdownPopup mPopup;
    private final Context mPopupContext;
    private final boolean mPopupSet;
    private SpinnerAdapter mTempAdapter;
    private final Rect mTempRect;

    static {
        AppCompatSpinner.ATTRS_ANDROID_SPINNERMODE = new int[]{0x10102F1};
    }

    public AppCompatSpinner(Context arg2, AttributeSet arg3) {
        this(arg2, arg3, attr.spinnerStyle);
    }

    public AppCompatSpinner(Context arg2, AttributeSet arg3, int arg4) {
        this(arg2, arg3, arg4, -1);
    }

    public AppCompatSpinner(Context arg2) {
        this(arg2, null);
    }

    public AppCompatSpinner(Context arg3, int arg4) {
        this(arg3, null, attr.spinnerStyle, arg4);
    }

    public AppCompatSpinner(Context arg7, AttributeSet arg8, int arg9, int arg10) {
        this(arg7, arg8, arg9, arg10, null);
    }

    public AppCompatSpinner(Context arg8, AttributeSet arg9, int arg10, int arg11, Resources$Theme arg12) {
        TypedArray v12_2;
        super(arg8, arg9, arg10);
        this.mTempRect = new Rect();
        TintTypedArray v0 = TintTypedArray.obtainStyledAttributes(arg8, arg9, styleable.Spinner, arg10, 0);
        this.mBackgroundTintHelper = new AppCompatBackgroundHelper(((View)this));
        TypedArray v2 = null;
        if(arg12 != null) {
            this.mPopupContext = new ContextThemeWrapper(arg8, arg12);
        }
        else {
            int v12 = v0.getResourceId(styleable.Spinner_popupTheme, 0);
            if(v12 != 0) {
                this.mPopupContext = new ContextThemeWrapper(arg8, v12);
            }
            else {
                Context v12_1 = Build$VERSION.SDK_INT < 23 ? arg8 : ((Context)v2);
                this.mPopupContext = v12_1;
            }
        }

        if(this.mPopupContext == null) {
            goto label_86;
        }

        if(arg11 != -1) {
            goto label_64;
        }

        if(Build$VERSION.SDK_INT < 11) {
            goto label_63;
        }

        try {
            v12_2 = arg8.obtainStyledAttributes(arg9, AppCompatSpinner.ATTRS_ANDROID_SPINNERMODE, arg10, 0);
        }
        catch(Throwable v8) {
            v12_2 = v2;
            goto label_60;
        }
        catch(Exception v4) {
            v12_2 = v2;
            goto label_54;
        }

        try {
            if(v12_2.hasValue(0)) {
                arg11 = v12_2.getInt(0, 0);
            }

            goto label_44;
        }
        catch(Throwable v8) {
        }
        catch(Exception v4) {
            try {
            label_54:
                Log.i("AppCompatSpinner", "Could not read android:spinnerMode", ((Throwable)v4));
                if(v12_2 != null) {
                }
                else {
                    goto label_64;
                }
            }
            catch(Throwable v8) {
                goto label_60;
            }

            goto label_45;
        }

    label_60:
        if(v12_2 != null) {
            v12_2.recycle();
        }

        throw v8;
    label_44:
        if(v12_2 == null) {
        }
        else {
        label_45:
            v12_2.recycle();
            goto label_64;
        label_63:
            arg11 = 1;
        }

    label_64:
        if(arg11 == 1) {
            DropdownPopup v11 = new DropdownPopup(this, this.mPopupContext, arg9, arg10);
            TintTypedArray v12_3 = TintTypedArray.obtainStyledAttributes(this.mPopupContext, arg9, styleable.Spinner, arg10, 0);
            this.mDropDownWidth = v12_3.getLayoutDimension(styleable.Spinner_android_dropDownWidth, -2);
            v11.setBackgroundDrawable(v12_3.getDrawable(styleable.Spinner_android_popupBackground));
            v11.setPromptText(v0.getString(styleable.Spinner_android_prompt));
            v12_3.recycle();
            this.mPopup = v11;
            this.mForwardingListener = new ForwardingListener(((View)this), v11) {
                public ShowableListMenu getPopup() {
                    return this.val$popup;
                }

                public boolean onForwardingStarted() {
                    if(!AppCompatSpinner.access$000(AppCompatSpinner.this).isShowing()) {
                        AppCompatSpinner.access$000(AppCompatSpinner.this).show();
                    }

                    return 1;
                }
            };
        }

    label_86:
        CharSequence[] v11_1 = v0.getTextArray(styleable.Spinner_android_entries);
        if(v11_1 != null) {
            ArrayAdapter v12_4 = new ArrayAdapter(arg8, 0x1090008, ((Object[])v11_1));
            v12_4.setDropDownViewResource(layout.support_simple_spinner_dropdown_item);
            this.setAdapter(((SpinnerAdapter)v12_4));
        }

        v0.recycle();
        this.mPopupSet = true;
        if(this.mTempAdapter != null) {
            this.setAdapter(this.mTempAdapter);
            this.mTempAdapter = ((SpinnerAdapter)v2);
        }

        this.mBackgroundTintHelper.loadFromAttributes(arg9, arg10);
    }

    static DropdownPopup access$000(AppCompatSpinner arg0) {
        return arg0.mPopup;
    }

    static Rect access$100(AppCompatSpinner arg0) {
        return arg0.mTempRect;
    }

    static int access$200(AppCompatSpinner arg0) {
        return arg0.mDropDownWidth;
    }

    int compatMeasureContentWidth(SpinnerAdapter arg11, Drawable arg12) {
        int v0 = 0;
        if(arg11 == null) {
            return 0;
        }

        int v1 = View$MeasureSpec.makeMeasureSpec(this.getMeasuredWidth(), 0);
        int v2 = View$MeasureSpec.makeMeasureSpec(this.getMeasuredHeight(), 0);
        int v3 = Math.max(0, this.getSelectedItemPosition());
        int v4 = Math.min(arg11.getCount(), v3 + 15);
        v3 = Math.max(0, v3 - (15 - (v4 - v3)));
        View v5 = null;
        View v6 = v5;
        int v7 = 0;
        while(v3 < v4) {
            int v8 = arg11.getItemViewType(v3);
            if(v8 != v0) {
                v6 = v5;
                v0 = v8;
            }

            v6 = arg11.getView(v3, v6, ((ViewGroup)this));
            if(v6.getLayoutParams() == null) {
                v6.setLayoutParams(new ViewGroup$LayoutParams(-2, -2));
            }

            v6.measure(v1, v2);
            v7 = Math.max(v7, v6.getMeasuredWidth());
            ++v3;
        }

        if(arg12 != null) {
            arg12.getPadding(this.mTempRect);
            v7 += this.mTempRect.left + this.mTempRect.right;
        }

        return v7;
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if(this.mBackgroundTintHelper != null) {
            this.mBackgroundTintHelper.applySupportBackgroundTint();
        }
    }

    public int getDropDownHorizontalOffset() {
        if(this.mPopup != null) {
            return this.mPopup.getHorizontalOffset();
        }

        if(Build$VERSION.SDK_INT >= 16) {
            return super.getDropDownHorizontalOffset();
        }

        return 0;
    }

    public int getDropDownVerticalOffset() {
        if(this.mPopup != null) {
            return this.mPopup.getVerticalOffset();
        }

        if(Build$VERSION.SDK_INT >= 16) {
            return super.getDropDownVerticalOffset();
        }

        return 0;
    }

    public int getDropDownWidth() {
        if(this.mPopup != null) {
            return this.mDropDownWidth;
        }

        if(Build$VERSION.SDK_INT >= 16) {
            return super.getDropDownWidth();
        }

        return 0;
    }

    public Drawable getPopupBackground() {
        if(this.mPopup != null) {
            return this.mPopup.getBackground();
        }

        if(Build$VERSION.SDK_INT >= 16) {
            return super.getPopupBackground();
        }

        return null;
    }

    public Context getPopupContext() {
        if(this.mPopup != null) {
            return this.mPopupContext;
        }

        if(Build$VERSION.SDK_INT >= 23) {
            return super.getPopupContext();
        }

        return null;
    }

    public CharSequence getPrompt() {
        CharSequence v0 = this.mPopup != null ? this.mPopup.getHintText() : super.getPrompt();
        return v0;
    }

    @Nullable @RestrictTo(value={Scope.LIBRARY_GROUP}) public ColorStateList getSupportBackgroundTintList() {
        ColorStateList v0 = this.mBackgroundTintHelper != null ? this.mBackgroundTintHelper.getSupportBackgroundTintList() : null;
        return v0;
    }

    @Nullable @RestrictTo(value={Scope.LIBRARY_GROUP}) public PorterDuff$Mode getSupportBackgroundTintMode() {
        PorterDuff$Mode v0 = this.mBackgroundTintHelper != null ? this.mBackgroundTintHelper.getSupportBackgroundTintMode() : null;
        return v0;
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if(this.mPopup != null && (this.mPopup.isShowing())) {
            this.mPopup.dismiss();
        }
    }

    protected void onMeasure(int arg3, int arg4) {
        super.onMeasure(arg3, arg4);
        if(this.mPopup != null && View$MeasureSpec.getMode(arg3) == 0x80000000) {
            this.setMeasuredDimension(Math.min(Math.max(this.getMeasuredWidth(), this.compatMeasureContentWidth(this.getAdapter(), this.getBackground())), View$MeasureSpec.getSize(arg3)), this.getMeasuredHeight());
        }
    }

    public boolean onTouchEvent(MotionEvent arg2) {
        if(this.mForwardingListener != null && (this.mForwardingListener.onTouch(((View)this), arg2))) {
            return 1;
        }

        return super.onTouchEvent(arg2);
    }

    public boolean performClick() {
        if(this.mPopup != null) {
            if(!this.mPopup.isShowing()) {
                this.mPopup.show();
            }

            return 1;
        }

        return super.performClick();
    }

    public void setAdapter(SpinnerAdapter arg4) {
        if(!this.mPopupSet) {
            this.mTempAdapter = arg4;
            return;
        }

        super.setAdapter(arg4);
        if(this.mPopup != null) {
            Context v0 = this.mPopupContext == null ? this.getContext() : this.mPopupContext;
            this.mPopup.setAdapter(new DropDownAdapter(arg4, v0.getTheme()));
        }
    }

    public void setAdapter(Adapter arg1) {
        this.setAdapter(((SpinnerAdapter)arg1));
    }

    public void setBackgroundDrawable(Drawable arg2) {
        super.setBackgroundDrawable(arg2);
        if(this.mBackgroundTintHelper != null) {
            this.mBackgroundTintHelper.onSetBackgroundDrawable(arg2);
        }
    }

    public void setBackgroundResource(@DrawableRes int arg2) {
        super.setBackgroundResource(arg2);
        if(this.mBackgroundTintHelper != null) {
            this.mBackgroundTintHelper.onSetBackgroundResource(arg2);
        }
    }

    public void setDropDownHorizontalOffset(int arg3) {
        if(this.mPopup != null) {
            this.mPopup.setHorizontalOffset(arg3);
        }
        else if(Build$VERSION.SDK_INT >= 16) {
            super.setDropDownHorizontalOffset(arg3);
        }
    }

    public void setDropDownVerticalOffset(int arg3) {
        if(this.mPopup != null) {
            this.mPopup.setVerticalOffset(arg3);
        }
        else if(Build$VERSION.SDK_INT >= 16) {
            super.setDropDownVerticalOffset(arg3);
        }
    }

    public void setDropDownWidth(int arg3) {
        if(this.mPopup != null) {
            this.mDropDownWidth = arg3;
        }
        else if(Build$VERSION.SDK_INT >= 16) {
            super.setDropDownWidth(arg3);
        }
    }

    public void setPopupBackgroundDrawable(Drawable arg3) {
        if(this.mPopup != null) {
            this.mPopup.setBackgroundDrawable(arg3);
        }
        else if(Build$VERSION.SDK_INT >= 16) {
            super.setPopupBackgroundDrawable(arg3);
        }
    }

    public void setPopupBackgroundResource(@DrawableRes int arg2) {
        this.setPopupBackgroundDrawable(AppCompatResources.getDrawable(this.getPopupContext(), arg2));
    }

    public void setPrompt(CharSequence arg2) {
        if(this.mPopup != null) {
            this.mPopup.setPromptText(arg2);
        }
        else {
            super.setPrompt(arg2);
        }
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public void setSupportBackgroundTintList(@Nullable ColorStateList arg2) {
        if(this.mBackgroundTintHelper != null) {
            this.mBackgroundTintHelper.setSupportBackgroundTintList(arg2);
        }
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public void setSupportBackgroundTintMode(@Nullable PorterDuff$Mode arg2) {
        if(this.mBackgroundTintHelper != null) {
            this.mBackgroundTintHelper.setSupportBackgroundTintMode(arg2);
        }
    }
}

