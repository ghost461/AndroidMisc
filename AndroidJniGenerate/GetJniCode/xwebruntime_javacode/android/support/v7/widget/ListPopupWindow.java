package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.annotation.StyleRes;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.PopupWindowCompat;
import android.support.v7.appcompat.R$attr;
import android.support.v7.appcompat.R$styleable;
import android.support.v7.view.menu.ShowableListMenu;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent$DispatcherState;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View$MeasureSpec;
import android.view.View$OnTouchListener;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AbsListView$OnScrollListener;
import android.widget.AbsListView;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.AdapterView$OnItemSelectedListener;
import android.widget.AdapterView;
import android.widget.LinearLayout$LayoutParams;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow$OnDismissListener;
import android.widget.PopupWindow;
import java.lang.reflect.Method;

public class ListPopupWindow implements ShowableListMenu {
    class ListSelectorHider implements Runnable {
        ListSelectorHider(ListPopupWindow arg1) {
            ListPopupWindow.this = arg1;
            super();
        }

        public void run() {
            ListPopupWindow.this.clearListSelection();
        }
    }

    class PopupDataSetObserver extends DataSetObserver {
        PopupDataSetObserver(ListPopupWindow arg1) {
            ListPopupWindow.this = arg1;
            super();
        }

        public void onChanged() {
            if(ListPopupWindow.this.isShowing()) {
                ListPopupWindow.this.show();
            }
        }

        public void onInvalidated() {
            ListPopupWindow.this.dismiss();
        }
    }

    class PopupScrollListener implements AbsListView$OnScrollListener {
        PopupScrollListener(ListPopupWindow arg1) {
            ListPopupWindow.this = arg1;
            super();
        }

        public void onScroll(AbsListView arg1, int arg2, int arg3, int arg4) {
        }

        public void onScrollStateChanged(AbsListView arg1, int arg2) {
            if(arg2 == 1 && !ListPopupWindow.this.isInputMethodNotNeeded() && ListPopupWindow.this.mPopup.getContentView() != null) {
                ListPopupWindow.this.mHandler.removeCallbacks(ListPopupWindow.this.mResizePopupRunnable);
                ListPopupWindow.this.mResizePopupRunnable.run();
            }
        }
    }

    class PopupTouchInterceptor implements View$OnTouchListener {
        PopupTouchInterceptor(ListPopupWindow arg1) {
            ListPopupWindow.this = arg1;
            super();
        }

        public boolean onTouch(View arg3, MotionEvent arg4) {
            int v3 = arg4.getAction();
            int v0 = ((int)arg4.getX());
            int v4 = ((int)arg4.getY());
            if(v3 == 0 && ListPopupWindow.this.mPopup != null && (ListPopupWindow.this.mPopup.isShowing()) && v0 >= 0 && v0 < ListPopupWindow.this.mPopup.getWidth() && v4 >= 0 && v4 < ListPopupWindow.this.mPopup.getHeight()) {
                ListPopupWindow.this.mHandler.postDelayed(ListPopupWindow.this.mResizePopupRunnable, 0xFA);
            }
            else if(v3 == 1) {
                ListPopupWindow.this.mHandler.removeCallbacks(ListPopupWindow.this.mResizePopupRunnable);
            }

            return 0;
        }
    }

    class ResizePopupRunnable implements Runnable {
        ResizePopupRunnable(ListPopupWindow arg1) {
            ListPopupWindow.this = arg1;
            super();
        }

        public void run() {
            if(ListPopupWindow.this.mDropDownList != null && (ViewCompat.isAttachedToWindow(ListPopupWindow.this.mDropDownList)) && ListPopupWindow.this.mDropDownList.getCount() > ListPopupWindow.this.mDropDownList.getChildCount() && ListPopupWindow.this.mDropDownList.getChildCount() <= ListPopupWindow.this.mListItemExpandMaximum) {
                ListPopupWindow.this.mPopup.setInputMethodMode(2);
                ListPopupWindow.this.show();
            }
        }
    }

    private static final boolean DEBUG = false;
    static final int EXPAND_LIST_TIMEOUT = 0xFA;
    public static final int INPUT_METHOD_FROM_FOCUSABLE = 0;
    public static final int INPUT_METHOD_NEEDED = 1;
    public static final int INPUT_METHOD_NOT_NEEDED = 2;
    public static final int MATCH_PARENT = -1;
    public static final int POSITION_PROMPT_ABOVE = 0;
    public static final int POSITION_PROMPT_BELOW = 1;
    private static final String TAG = "ListPopupWindow";
    public static final int WRAP_CONTENT = -2;
    private ListAdapter mAdapter;
    private Context mContext;
    private boolean mDropDownAlwaysVisible;
    private View mDropDownAnchorView;
    private int mDropDownGravity;
    private int mDropDownHeight;
    private int mDropDownHorizontalOffset;
    DropDownListView mDropDownList;
    private Drawable mDropDownListHighlight;
    private int mDropDownVerticalOffset;
    private boolean mDropDownVerticalOffsetSet;
    private int mDropDownWidth;
    private int mDropDownWindowLayoutType;
    private Rect mEpicenterBounds;
    private boolean mForceIgnoreOutsideTouch;
    final Handler mHandler;
    private final ListSelectorHider mHideSelector;
    private boolean mIsAnimatedFromAnchor;
    private AdapterView$OnItemClickListener mItemClickListener;
    private AdapterView$OnItemSelectedListener mItemSelectedListener;
    int mListItemExpandMaximum;
    private boolean mModal;
    private DataSetObserver mObserver;
    private boolean mOverlapAnchor;
    private boolean mOverlapAnchorSet;
    PopupWindow mPopup;
    private int mPromptPosition;
    private View mPromptView;
    final ResizePopupRunnable mResizePopupRunnable;
    private final PopupScrollListener mScrollListener;
    private Runnable mShowDropDownRunnable;
    private final Rect mTempRect;
    private final PopupTouchInterceptor mTouchInterceptor;
    private static Method sClipToWindowEnabledMethod;
    private static Method sGetMaxAvailableHeightMethod;
    private static Method sSetEpicenterBoundsMethod;

    static {
        try {
            ListPopupWindow.sClipToWindowEnabledMethod = PopupWindow.class.getDeclaredMethod("setClipToScreenEnabled", Boolean.TYPE);
        }
        catch(NoSuchMethodException ) {
            Log.i("ListPopupWindow", "Could not find method setClipToScreenEnabled() on PopupWindow. Oh well.");
        }

        try {
            ListPopupWindow.sGetMaxAvailableHeightMethod = PopupWindow.class.getDeclaredMethod("getMaxAvailableHeight", View.class, Integer.TYPE, Boolean.TYPE);
        }
        catch(NoSuchMethodException ) {
            Log.i("ListPopupWindow", "Could not find method getMaxAvailableHeight(View, int, boolean) on PopupWindow. Oh well.");
        }

        try {
            ListPopupWindow.sSetEpicenterBoundsMethod = PopupWindow.class.getDeclaredMethod("setEpicenterBounds", Rect.class);
        }
        catch(NoSuchMethodException ) {
            Log.i("ListPopupWindow", "Could not find method setEpicenterBounds(Rect) on PopupWindow. Oh well.");
        }
    }

    public ListPopupWindow(@NonNull Context arg3) {
        this(arg3, null, attr.listPopupWindowStyle);
    }

    public ListPopupWindow(@NonNull Context arg2, @Nullable AttributeSet arg3, @AttrRes int arg4) {
        this(arg2, arg3, arg4, 0);
    }

    public ListPopupWindow(@NonNull Context arg2, @Nullable AttributeSet arg3) {
        this(arg2, arg3, attr.listPopupWindowStyle);
    }

    public ListPopupWindow(@NonNull Context arg5, @Nullable AttributeSet arg6, @AttrRes int arg7, @StyleRes int arg8) {
        super();
        this.mDropDownHeight = -2;
        this.mDropDownWidth = -2;
        this.mDropDownWindowLayoutType = 1002;
        this.mIsAnimatedFromAnchor = true;
        this.mDropDownGravity = 0;
        this.mDropDownAlwaysVisible = false;
        this.mForceIgnoreOutsideTouch = false;
        this.mListItemExpandMaximum = 0x7FFFFFFF;
        this.mPromptPosition = 0;
        this.mResizePopupRunnable = new ResizePopupRunnable(this);
        this.mTouchInterceptor = new PopupTouchInterceptor(this);
        this.mScrollListener = new PopupScrollListener(this);
        this.mHideSelector = new ListSelectorHider(this);
        this.mTempRect = new Rect();
        this.mContext = arg5;
        this.mHandler = new Handler(arg5.getMainLooper());
        TypedArray v2 = arg5.obtainStyledAttributes(arg6, styleable.ListPopupWindow, arg7, arg8);
        this.mDropDownHorizontalOffset = v2.getDimensionPixelOffset(styleable.ListPopupWindow_android_dropDownHorizontalOffset, 0);
        this.mDropDownVerticalOffset = v2.getDimensionPixelOffset(styleable.ListPopupWindow_android_dropDownVerticalOffset, 0);
        if(this.mDropDownVerticalOffset != 0) {
            this.mDropDownVerticalOffsetSet = true;
        }

        v2.recycle();
        this.mPopup = new AppCompatPopupWindow(arg5, arg6, arg7, arg8);
        this.mPopup.setInputMethodMode(1);
    }

    private int buildDropDown() {
        LinearLayout v5_3;
        int v5_2;
        int v0_2;
        int v1 = 0x80000000;
        int v2 = -1;
        boolean v3 = true;
        if(this.mDropDownList == null) {
            Context v0 = this.mContext;
            this.mShowDropDownRunnable = new Runnable() {
                public void run() {
                    View v0 = ListPopupWindow.this.getAnchorView();
                    if(v0 != null && v0.getWindowToken() != null) {
                        ListPopupWindow.this.show();
                    }
                }
            };
            this.mDropDownList = this.createDropDownListView(v0, this.mModal ^ 1);
            if(this.mDropDownListHighlight != null) {
                this.mDropDownList.setSelector(this.mDropDownListHighlight);
            }

            this.mDropDownList.setAdapter(this.mAdapter);
            this.mDropDownList.setOnItemClickListener(this.mItemClickListener);
            this.mDropDownList.setFocusable(true);
            this.mDropDownList.setFocusableInTouchMode(true);
            this.mDropDownList.setOnItemSelectedListener(new AdapterView$OnItemSelectedListener() {
                public void onItemSelected(AdapterView arg1, View arg2, int arg3, long arg4) {
                    if(arg3 != -1) {
                        DropDownListView v1 = ListPopupWindow.this.mDropDownList;
                        if(v1 != null) {
                            v1.setListSelectionHidden(false);
                        }
                    }
                }

                public void onNothingSelected(AdapterView arg1) {
                }
            });
            this.mDropDownList.setOnScrollListener(this.mScrollListener);
            if(this.mItemSelectedListener != null) {
                this.mDropDownList.setOnItemSelectedListener(this.mItemSelectedListener);
            }

            DropDownListView v5 = this.mDropDownList;
            View v6 = this.mPromptView;
            if(v6 != null) {
                LinearLayout v7 = new LinearLayout(v0);
                v7.setOrientation(1);
                LinearLayout$LayoutParams v0_1 = new LinearLayout$LayoutParams(v2, 0, 1f);
                switch(this.mPromptPosition) {
                    case 0: {
                        v7.addView(v6);
                        v7.addView(((View)v5), ((ViewGroup$LayoutParams)v0_1));
                        break;
                    }
                    case 1: {
                        v7.addView(((View)v5), ((ViewGroup$LayoutParams)v0_1));
                        v7.addView(v6);
                        break;
                    }
                    default: {
                        Log.e("ListPopupWindow", "Invalid hint position " + this.mPromptPosition);
                        break;
                    }
                }

                if(this.mDropDownWidth >= 0) {
                    v0_2 = this.mDropDownWidth;
                    v5_2 = 0x80000000;
                }
                else {
                    v0_2 = 0;
                    v5_2 = 0;
                }

                v6.measure(View$MeasureSpec.makeMeasureSpec(v0_2, v5_2), 0);
                ViewGroup$LayoutParams v0_3 = v6.getLayoutParams();
                v0_2 = v6.getMeasuredHeight() + ((LinearLayout$LayoutParams)v0_3).topMargin + ((LinearLayout$LayoutParams)v0_3).bottomMargin;
                v5_3 = v7;
            }
            else {
                v0_2 = 0;
            }

            this.mPopup.setContentView(((View)v5_3));
        }
        else {
            this.mPopup.getContentView();
            View v0_4 = this.mPromptView;
            if(v0_4 != null) {
                ViewGroup$LayoutParams v5_4 = v0_4.getLayoutParams();
                v0_2 = v0_4.getMeasuredHeight() + ((LinearLayout$LayoutParams)v5_4).topMargin + ((LinearLayout$LayoutParams)v5_4).bottomMargin;
                goto label_101;
            }

            v0_2 = 0;
        }

    label_101:
        Drawable v5_5 = this.mPopup.getBackground();
        if(v5_5 != null) {
            v5_5.getPadding(this.mTempRect);
            v5_2 = this.mTempRect.top + this.mTempRect.bottom;
            if(!this.mDropDownVerticalOffsetSet) {
                this.mDropDownVerticalOffset = -this.mTempRect.top;
            }
        }
        else {
            this.mTempRect.setEmpty();
            v5_2 = 0;
        }

        if(this.mPopup.getInputMethodMode() == 2) {
        }
        else {
            v3 = false;
        }

        int v3_1 = this.getMaxAvailableHeight(this.getAnchorView(), this.mDropDownVerticalOffset, v3);
        if(!this.mDropDownAlwaysVisible) {
            if(this.mDropDownHeight == v2) {
            }
            else {
                int v4 = 0x40000000;
                switch(this.mDropDownWidth) {
                    case -2: {
                        v1 = View$MeasureSpec.makeMeasureSpec(this.mContext.getResources().getDisplayMetrics().widthPixels - (this.mTempRect.left + this.mTempRect.right), v1);
                        break;
                    }
                    case -1: {
                        v1 = View$MeasureSpec.makeMeasureSpec(this.mContext.getResources().getDisplayMetrics().widthPixels - (this.mTempRect.left + this.mTempRect.right), v4);
                        break;
                    }
                    default: {
                        v1 = View$MeasureSpec.makeMeasureSpec(this.mDropDownWidth, v4);
                        break;
                    }
                }

                int v7_1 = v1;
                v1 = this.mDropDownList.measureHeightOfChildrenCompat(v7_1, 0, -1, v3_1 - v0_2, -1);
                if(v1 > 0) {
                    v0_2 += v5_2 + (this.mDropDownList.getPaddingTop() + this.mDropDownList.getPaddingBottom());
                }

                return v1 + v0_2;
            }
        }

        return v3_1 + v5_2;
    }

    public void clearListSelection() {
        DropDownListView v0 = this.mDropDownList;
        if(v0 != null) {
            v0.setListSelectionHidden(true);
            v0.requestLayout();
        }
    }

    public View$OnTouchListener createDragToOpenListener(View arg2) {
        return new ForwardingListener(arg2) {
            public ShowableListMenu getPopup() {
                return this.getPopup();
            }

            public ListPopupWindow getPopup() {
                return ListPopupWindow.this;
            }
        };
    }

    @NonNull DropDownListView createDropDownListView(Context arg2, boolean arg3) {
        return new DropDownListView(arg2, arg3);
    }

    public void dismiss() {
        this.mPopup.dismiss();
        this.removePromptView();
        this.mPopup.setContentView(null);
        this.mDropDownList = null;
        this.mHandler.removeCallbacks(this.mResizePopupRunnable);
    }

    @Nullable public View getAnchorView() {
        return this.mDropDownAnchorView;
    }

    @StyleRes public int getAnimationStyle() {
        return this.mPopup.getAnimationStyle();
    }

    @Nullable public Drawable getBackground() {
        return this.mPopup.getBackground();
    }

    public int getHeight() {
        return this.mDropDownHeight;
    }

    public int getHorizontalOffset() {
        return this.mDropDownHorizontalOffset;
    }

    public int getInputMethodMode() {
        return this.mPopup.getInputMethodMode();
    }

    @Nullable public ListView getListView() {
        return this.mDropDownList;
    }

    private int getMaxAvailableHeight(View arg6, int arg7, boolean arg8) {
        if(ListPopupWindow.sGetMaxAvailableHeightMethod != null) {
            try {
                return ListPopupWindow.sGetMaxAvailableHeightMethod.invoke(this.mPopup, arg6, Integer.valueOf(arg7), Boolean.valueOf(arg8)).intValue();
            }
            catch(Exception ) {
                Log.i("ListPopupWindow", "Could not call getMaxAvailableHeightMethod(View, int, boolean) on PopupWindow. Using the public version.");
            }
        }

        return this.mPopup.getMaxAvailableHeight(arg6, arg7);
    }

    public int getPromptPosition() {
        return this.mPromptPosition;
    }

    @Nullable public Object getSelectedItem() {
        if(!this.isShowing()) {
            return null;
        }

        return this.mDropDownList.getSelectedItem();
    }

    public long getSelectedItemId() {
        if(!this.isShowing()) {
            return -9223372036854775808L;
        }

        return this.mDropDownList.getSelectedItemId();
    }

    public int getSelectedItemPosition() {
        if(!this.isShowing()) {
            return -1;
        }

        return this.mDropDownList.getSelectedItemPosition();
    }

    @Nullable public View getSelectedView() {
        if(!this.isShowing()) {
            return null;
        }

        return this.mDropDownList.getSelectedView();
    }

    public int getSoftInputMode() {
        return this.mPopup.getSoftInputMode();
    }

    public int getVerticalOffset() {
        if(!this.mDropDownVerticalOffsetSet) {
            return 0;
        }

        return this.mDropDownVerticalOffset;
    }

    public int getWidth() {
        return this.mDropDownWidth;
    }

    private static boolean isConfirmKey(int arg1) {
        boolean v1 = arg1 == 66 || arg1 == 23 ? true : false;
        return v1;
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public boolean isDropDownAlwaysVisible() {
        return this.mDropDownAlwaysVisible;
    }

    public boolean isInputMethodNotNeeded() {
        boolean v0 = this.mPopup.getInputMethodMode() == 2 ? true : false;
        return v0;
    }

    public boolean isModal() {
        return this.mModal;
    }

    public boolean isShowing() {
        return this.mPopup.isShowing();
    }

    public boolean onKeyDown(int arg10, @NonNull KeyEvent arg11) {
        int v4_1;
        int v6;
        if((this.isShowing()) && arg10 != 62 && (this.mDropDownList.getSelectedItemPosition() >= 0 || !ListPopupWindow.isConfirmKey(arg10))) {
            int v0 = this.mDropDownList.getSelectedItemPosition();
            int v2 = this.mPopup.isAboveAnchor() ^ 1;
            ListAdapter v4 = this.mAdapter;
            int v5 = 0x7FFFFFFF;
            if(v4 != null) {
                boolean v5_1 = v4.areAllItemsEnabled();
                v6 = v5_1 ? 0 : this.mDropDownList.lookForSelectablePosition(0, true);
                v4_1 = v5_1 ? v4.getCount() - 1 : this.mDropDownList.lookForSelectablePosition(v4.getCount() - 1, false);
                v5 = v6;
            }
            else {
                v4_1 = 0x80000000;
            }

            v6 = 19;
            if(v2 == 0 || arg10 != v6 || v0 > v5) {
                int v7 = 20;
                if(v2 == 0 && arg10 == v7 && v0 >= v4_1) {
                    goto label_45;
                }
            }
            else {
            label_45:
                this.clearListSelection();
                this.mPopup.setInputMethodMode(1);
                this.show();
                return 1;
            }

            this.mDropDownList.setListSelectionHidden(false);
            if(this.mDropDownList.onKeyDown(arg10, arg11)) {
                this.mPopup.setInputMethodMode(2);
                this.mDropDownList.requestFocusFromTouch();
                this.show();
                if(arg10 != 23 && arg10 != 66) {
                    switch(arg10) {
                        case 19: 
                        case 20: {
                            return 1;
                        }
                        default: {
                            return 0;
                        }
                    }
                }

                return 1;
            }

            if(v2 != 0 && arg10 == v7) {
                if(v0 == v4_1) {
                    return 1;
                }
                else {
                    return 0;
                }
            }

            if(v2 != 0) {
                return 0;
            }

            if(arg10 != v6) {
                return 0;
            }

            if(v0 != v5) {
                return 0;
            }

            return 1;
        }

        return 0;
    }

    public boolean onKeyPreIme(int arg3, @NonNull KeyEvent arg4) {
        KeyEvent$DispatcherState v3_1;
        if(arg3 == 4 && (this.isShowing())) {
            View v3 = this.mDropDownAnchorView;
            if(arg4.getAction() == 0 && arg4.getRepeatCount() == 0) {
                v3_1 = v3.getKeyDispatcherState();
                if(v3_1 != null) {
                    v3_1.startTracking(arg4, this);
                }

                return 1;
            }

            if(arg4.getAction() != 1) {
                return 0;
            }

            v3_1 = v3.getKeyDispatcherState();
            if(v3_1 != null) {
                v3_1.handleUpEvent(arg4);
            }

            if(!arg4.isTracking()) {
                return 0;
            }

            if(arg4.isCanceled()) {
                return 0;
            }

            this.dismiss();
            return 1;
        }

        return 0;
    }

    public boolean onKeyUp(int arg2, @NonNull KeyEvent arg3) {
        if((this.isShowing()) && this.mDropDownList.getSelectedItemPosition() >= 0) {
            boolean v3 = this.mDropDownList.onKeyUp(arg2, arg3);
            if((v3) && (ListPopupWindow.isConfirmKey(arg2))) {
                this.dismiss();
            }

            return v3;
        }

        return 0;
    }

    public boolean performItemClick(int arg8) {
        if(this.isShowing()) {
            if(this.mItemClickListener != null) {
                this.mItemClickListener.onItemClick(this.mDropDownList, this.mDropDownList.getChildAt(arg8 - this.mDropDownList.getFirstVisiblePosition()), arg8, this.mDropDownList.getAdapter().getItemId(arg8));
            }

            return 1;
        }

        return 0;
    }

    public void postShow() {
        this.mHandler.post(this.mShowDropDownRunnable);
    }

    private void removePromptView() {
        if(this.mPromptView != null) {
            ViewParent v0 = this.mPromptView.getParent();
            if((v0 instanceof ViewGroup)) {
                ((ViewGroup)v0).removeView(this.mPromptView);
            }
        }
    }

    public void setAdapter(@Nullable ListAdapter arg3) {
        if(this.mObserver == null) {
            this.mObserver = new PopupDataSetObserver(this);
        }
        else if(this.mAdapter != null) {
            this.mAdapter.unregisterDataSetObserver(this.mObserver);
        }

        this.mAdapter = arg3;
        if(this.mAdapter != null) {
            arg3.registerDataSetObserver(this.mObserver);
        }

        if(this.mDropDownList != null) {
            this.mDropDownList.setAdapter(this.mAdapter);
        }
    }

    public void setAnchorView(@Nullable View arg1) {
        this.mDropDownAnchorView = arg1;
    }

    public void setAnimationStyle(@StyleRes int arg2) {
        this.mPopup.setAnimationStyle(arg2);
    }

    public void setBackgroundDrawable(@Nullable Drawable arg2) {
        this.mPopup.setBackgroundDrawable(arg2);
    }

    public void setContentWidth(int arg3) {
        Drawable v0 = this.mPopup.getBackground();
        if(v0 != null) {
            v0.getPadding(this.mTempRect);
            this.mDropDownWidth = this.mTempRect.left + this.mTempRect.right + arg3;
        }
        else {
            this.setWidth(arg3);
        }
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public void setDropDownAlwaysVisible(boolean arg1) {
        this.mDropDownAlwaysVisible = arg1;
    }

    public void setDropDownGravity(int arg1) {
        this.mDropDownGravity = arg1;
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public void setEpicenterBounds(Rect arg1) {
        this.mEpicenterBounds = arg1;
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public void setForceIgnoreOutsideTouch(boolean arg1) {
        this.mForceIgnoreOutsideTouch = arg1;
    }

    public void setHeight(int arg2) {
        if(arg2 < 0 && -2 != arg2 && -1 != arg2) {
            throw new IllegalArgumentException("Invalid height. Must be a positive value, MATCH_PARENT, or WRAP_CONTENT.");
        }

        this.mDropDownHeight = arg2;
    }

    public void setHorizontalOffset(int arg1) {
        this.mDropDownHorizontalOffset = arg1;
    }

    public void setInputMethodMode(int arg2) {
        this.mPopup.setInputMethodMode(arg2);
    }

    void setListItemExpandMax(int arg1) {
        this.mListItemExpandMaximum = arg1;
    }

    public void setListSelector(Drawable arg1) {
        this.mDropDownListHighlight = arg1;
    }

    public void setModal(boolean arg2) {
        this.mModal = arg2;
        this.mPopup.setFocusable(arg2);
    }

    public void setOnDismissListener(@Nullable PopupWindow$OnDismissListener arg2) {
        this.mPopup.setOnDismissListener(arg2);
    }

    public void setOnItemClickListener(@Nullable AdapterView$OnItemClickListener arg1) {
        this.mItemClickListener = arg1;
    }

    public void setOnItemSelectedListener(@Nullable AdapterView$OnItemSelectedListener arg1) {
        this.mItemSelectedListener = arg1;
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public void setOverlapAnchor(boolean arg2) {
        this.mOverlapAnchorSet = true;
        this.mOverlapAnchor = arg2;
    }

    private void setPopupClipToScreenEnabled(boolean arg5) {
        if(ListPopupWindow.sClipToWindowEnabledMethod != null) {
            try {
                ListPopupWindow.sClipToWindowEnabledMethod.invoke(this.mPopup, Boolean.valueOf(arg5));
            }
            catch(Exception ) {
                Log.i("ListPopupWindow", "Could not call setClipToScreenEnabled() on PopupWindow. Oh well.");
            }
        }
    }

    public void setPromptPosition(int arg1) {
        this.mPromptPosition = arg1;
    }

    public void setPromptView(@Nullable View arg2) {
        boolean v0 = this.isShowing();
        if(v0) {
            this.removePromptView();
        }

        this.mPromptView = arg2;
        if(v0) {
            this.show();
        }
    }

    public void setSelection(int arg3) {
        DropDownListView v0 = this.mDropDownList;
        if((this.isShowing()) && v0 != null) {
            v0.setListSelectionHidden(false);
            v0.setSelection(arg3);
            if(v0.getChoiceMode() != 0) {
                v0.setItemChecked(arg3, true);
            }
        }
    }

    public void setSoftInputMode(int arg2) {
        this.mPopup.setSoftInputMode(arg2);
    }

    public void setVerticalOffset(int arg1) {
        this.mDropDownVerticalOffset = arg1;
        this.mDropDownVerticalOffsetSet = true;
    }

    public void setWidth(int arg1) {
        this.mDropDownWidth = arg1;
    }

    public void setWindowLayoutType(int arg1) {
        this.mDropDownWindowLayoutType = arg1;
    }

    public void show() {
        int v1_2;
        PopupWindow v1_1;
        int v2;
        int v0 = this.buildDropDown();
        boolean v1 = this.isInputMethodNotNeeded();
        PopupWindowCompat.setWindowLayoutType(this.mPopup, this.mDropDownWindowLayoutType);
        boolean v3 = true;
        int v4 = -2;
        int v6 = -1;
        if(this.mPopup.isShowing()) {
            if(!ViewCompat.isAttachedToWindow(this.getAnchorView())) {
                return;
            }

            if(this.mDropDownWidth == v6) {
                v2 = -1;
            }
            else if(this.mDropDownWidth == v4) {
                v2 = this.getAnchorView().getWidth();
            }
            else {
                v2 = this.mDropDownWidth;
            }

            if(this.mDropDownHeight == v6) {
                if(v1) {
                }
                else {
                    v0 = -1;
                }

                if(v1) {
                    v1_1 = this.mPopup;
                    v4 = this.mDropDownWidth == v6 ? -1 : 0;
                    v1_1.setWidth(v4);
                    this.mPopup.setHeight(0);
                    goto label_56;
                }

                v1_1 = this.mPopup;
                v4 = this.mDropDownWidth == v6 ? -1 : 0;
                v1_1.setWidth(v4);
                this.mPopup.setHeight(v6);
            }
            else {
                if(this.mDropDownHeight == v4) {
                    goto label_56;
                }

                v0 = this.mDropDownHeight;
            }

        label_56:
            v1_1 = this.mPopup;
            if((this.mForceIgnoreOutsideTouch) || (this.mDropDownAlwaysVisible)) {
                v3 = false;
            }
            else {
            }

            v1_1.setOutsideTouchable(v3);
            PopupWindow v7 = this.mPopup;
            View v8 = this.getAnchorView();
            int v9 = this.mDropDownHorizontalOffset;
            int v10 = this.mDropDownVerticalOffset;
            int v11 = v2 < 0 ? -1 : v2;
            int v12 = v0 < 0 ? -1 : v0;
            v7.update(v8, v9, v10, v11, v12);
            return;
        }

        if(this.mDropDownWidth == v6) {
            v1_2 = -1;
        }
        else if(this.mDropDownWidth == v4) {
            v1_2 = this.getAnchorView().getWidth();
        }
        else {
            v1_2 = this.mDropDownWidth;
        }

        if(this.mDropDownHeight == v6) {
            v0 = -1;
        }
        else if(this.mDropDownHeight == v4) {
        }
        else {
            v0 = this.mDropDownHeight;
        }

        this.mPopup.setWidth(v1_2);
        this.mPopup.setHeight(v0);
        this.setPopupClipToScreenEnabled(true);
        PopupWindow v0_1 = this.mPopup;
        v1 = (this.mForceIgnoreOutsideTouch) || (this.mDropDownAlwaysVisible) ? false : true;
        v0_1.setOutsideTouchable(v1);
        this.mPopup.setTouchInterceptor(this.mTouchInterceptor);
        if(this.mOverlapAnchorSet) {
            PopupWindowCompat.setOverlapAnchor(this.mPopup, this.mOverlapAnchor);
        }

        if(ListPopupWindow.sSetEpicenterBoundsMethod != null) {
            try {
                ListPopupWindow.sSetEpicenterBoundsMethod.invoke(this.mPopup, this.mEpicenterBounds);
            }
            catch(Exception v0_2) {
                Log.e("ListPopupWindow", "Could not invoke setEpicenterBounds on PopupWindow", ((Throwable)v0_2));
            }
        }

        PopupWindowCompat.showAsDropDown(this.mPopup, this.getAnchorView(), this.mDropDownHorizontalOffset, this.mDropDownVerticalOffset, this.mDropDownGravity);
        this.mDropDownList.setSelection(v6);
        if(!this.mModal || (this.mDropDownList.isInTouchMode())) {
            this.clearListSelection();
        }

        if(!this.mModal) {
            this.mHandler.post(this.mHideSelector);
        }
    }
}

