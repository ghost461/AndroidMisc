package org.chromium.content.browser.input;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.text.SpannableString;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View$MeasureSpec;
import android.view.View$OnClickListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow$OnDismissListener;
import android.widget.PopupWindow;
import android.widget.TextView;
import org.chromium.base.ApiCompatibilityUtils;
import org.chromium.base.VisibleForTesting;
import org.chromium.ui.UiUtils;
import org.chromium.ui.base.WindowAndroid;

public abstract class SuggestionsPopupWindow implements View$OnClickListener, AdapterView$OnItemClickListener, PopupWindow$OnDismissListener {
    class org.chromium.content.browser.input.SuggestionsPopupWindow$1 {
    }

    class SuggestionAdapter extends BaseAdapter {
        private LayoutInflater mInflater;

        SuggestionAdapter(SuggestionsPopupWindow arg1, org.chromium.content.browser.input.SuggestionsPopupWindow$1 arg2) {
            this(arg1);
        }

        private SuggestionAdapter(SuggestionsPopupWindow arg2) {
            SuggestionsPopupWindow.this = arg2;
            super();
            this.mInflater = SuggestionsPopupWindow.this.mContext.getSystemService("layout_inflater");
        }

        public int getCount() {
            return SuggestionsPopupWindow.this.mNumberOfSuggestionsToUse;
        }

        public Object getItem(int arg2) {
            return SuggestionsPopupWindow.this.getSuggestionItem(arg2);
        }

        public long getItemId(int arg3) {
            return ((long)arg3);
        }

        public View getView(int arg3, View arg4, ViewGroup arg5) {
            if(arg4 == null) {
                arg4 = this.mInflater.inflate(0x7F09003A, arg5, false);
            }

            ((TextView)arg4).setText(SuggestionsPopupWindow.this.getSuggestionText(arg3));
            return arg4;
        }
    }

    private static final String ACTION_USER_DICTIONARY_INSERT = "com.android.settings.USER_DICTIONARY_INSERT";
    private static final int ADD_TO_DICTIONARY_MAX_LENGTH_ON_JELLY_BEAN = 0x30;
    private static final String USER_DICTIONARY_EXTRA_WORD = "word";
    private Activity mActivity;
    private TextView mAddToDictionaryButton;
    private LinearLayout mContentView;
    private final Context mContext;
    private TextView mDeleteButton;
    private boolean mDismissedByItemTap;
    private DisplayMetrics mDisplayMetrics;
    private View mDivider;
    private String mHighlightedText;
    private LinearLayout mListFooter;
    private int mNumberOfSuggestionsToUse;
    private final View mParentView;
    private int mPopupVerticalMargin;
    private PopupWindow mPopupWindow;
    private ListView mSuggestionListView;
    protected final TextSuggestionHost mTextSuggestionHost;
    private WindowAndroid mWindowAndroid;

    public SuggestionsPopupWindow(Context arg1, TextSuggestionHost arg2, WindowAndroid arg3, View arg4) {
        super();
        this.mContext = arg1;
        this.mTextSuggestionHost = arg2;
        this.mWindowAndroid = arg3;
        this.mParentView = arg4;
        this.createPopupWindow();
        this.initContentView();
        this.mPopupWindow.setContentView(this.mContentView);
    }

    static Context access$100(SuggestionsPopupWindow arg0) {
        return arg0.mContext;
    }

    static int access$200(SuggestionsPopupWindow arg0) {
        return arg0.mNumberOfSuggestionsToUse;
    }

    private void addToDictionary() {
        Intent v0 = new Intent("com.android.settings.USER_DICTIONARY_INSERT");
        String v1 = this.mHighlightedText;
        if(Build$VERSION.SDK_INT < 19) {
            int v3 = 0x30;
            if(v1.length() > v3) {
                v1 = v1.substring(0, v3);
            }
        }

        v0.putExtra("word", v1);
        v0.setFlags(v0.getFlags() | 0x10000000);
        this.mContext.startActivity(v0);
    }

    protected abstract void applySuggestion(int arg1);

    private void createPopupWindow() {
        this.mPopupWindow = new PopupWindow();
        this.mPopupWindow.setWidth(-2);
        this.mPopupWindow.setHeight(-2);
        if(Build$VERSION.SDK_INT >= 21) {
            this.mPopupWindow.setBackgroundDrawable(ApiCompatibilityUtils.getDrawable(this.mContext.getResources(), 0x7F060060));
            this.mPopupWindow.setElevation(((float)this.mContext.getResources().getDimensionPixelSize(0x7F05007C)));
        }
        else {
            this.mPopupWindow.setBackgroundDrawable(new ColorDrawable(0));
        }

        this.mPopupWindow.setInputMethodMode(2);
        this.mPopupWindow.setFocusable(true);
        this.mPopupWindow.setClippingEnabled(false);
        this.mPopupWindow.setOnDismissListener(((PopupWindow$OnDismissListener)this));
    }

    public void dismiss() {
        this.mPopupWindow.dismiss();
    }

    @VisibleForTesting public View getContentViewForTesting() {
        return this.mContentView;
    }

    protected abstract Object getSuggestionItem(int arg1);

    protected abstract SpannableString getSuggestionText(int arg1);

    protected abstract int getSuggestionsCount();

    private void initContentView() {
        Object v0 = this.mContext.getSystemService("layout_inflater");
        ViewGroup v1 = null;
        this.mContentView = ((LayoutInflater)v0).inflate(0x7F090039, v1);
        if(Build$VERSION.SDK_INT < 21) {
            this.mContentView.setBackground(ApiCompatibilityUtils.getDrawable(this.mContext.getResources(), 0x7F06005D));
        }

        this.mPopupVerticalMargin = this.mContext.getResources().getDimensionPixelSize(0x7F05007D);
        this.mSuggestionListView = this.mContentView.findViewById(0x7F0700AD);
        this.mSuggestionListView.setDivider(((Drawable)v1));
        this.mListFooter = ((LayoutInflater)v0).inflate(0x7F09003B, v1);
        this.mSuggestionListView.addFooterView(this.mListFooter, v1, false);
        this.mSuggestionListView.setAdapter(new SuggestionAdapter(this, ((org.chromium.content.browser.input.SuggestionsPopupWindow$1)v1)));
        this.mSuggestionListView.setOnItemClickListener(((AdapterView$OnItemClickListener)this));
        this.mDivider = this.mContentView.findViewById(0x7F070039);
        this.mAddToDictionaryButton = this.mContentView.findViewById(0x7F07001B);
        this.mAddToDictionaryButton.setOnClickListener(((View$OnClickListener)this));
        this.mDeleteButton = this.mContentView.findViewById(0x7F070037);
        this.mDeleteButton.setOnClickListener(((View$OnClickListener)this));
    }

    public boolean isShowing() {
        return this.mPopupWindow.isShowing();
    }

    private void measureContent() {
        int v0 = UiUtils.computeMaxWidthOfListAdapterItems(this.mSuggestionListView.getAdapter()) + (this.mContentView.getPaddingLeft() + this.mContentView.getPaddingRight());
        this.mContentView.measure(View$MeasureSpec.makeMeasureSpec(v0, 0x40000000), View$MeasureSpec.makeMeasureSpec(this.mDisplayMetrics.heightPixels, 0x80000000));
        this.mPopupWindow.setWidth(v0);
    }

    public void onClick(View arg3) {
        if(arg3 == this.mAddToDictionaryButton) {
            this.addToDictionary();
            this.mTextSuggestionHost.onNewWordAddedToDictionary(this.mHighlightedText);
            this.mDismissedByItemTap = true;
            this.mPopupWindow.dismiss();
        }
        else if(arg3 == this.mDeleteButton) {
            this.mTextSuggestionHost.deleteActiveSuggestionRange();
            this.mDismissedByItemTap = true;
            this.mPopupWindow.dismiss();
        }
    }

    public void onDismiss() {
        this.mTextSuggestionHost.onSuggestionMenuClosed(this.mDismissedByItemTap);
        this.mDismissedByItemTap = false;
    }

    public void onItemClick(AdapterView arg1, View arg2, int arg3, long arg4) {
        if(arg3 >= this.mNumberOfSuggestionsToUse) {
            return;
        }

        this.applySuggestion(arg3);
        this.mDismissedByItemTap = true;
        this.mPopupWindow.dismiss();
    }

    protected void setAddToDictionaryEnabled(boolean arg2) {
        TextView v0 = this.mAddToDictionaryButton;
        int v2 = arg2 ? 0 : 8;
        v0.setVisibility(v2);
    }

    protected void show(double arg6, double arg8, String arg10) {
        int v10_1;
        this.mNumberOfSuggestionsToUse = this.getSuggestionsCount();
        this.mHighlightedText = arg10;
        this.mActivity = this.mWindowAndroid.getActivity().get();
        this.mDisplayMetrics = this.mActivity != null ? this.mActivity.getResources().getDisplayMetrics() : this.mContext.getResources().getDisplayMetrics();
        if(this.mActivity == null || (ApiCompatibilityUtils.isInMultiWindowMode(this.mActivity))) {
            v10_1 = 0;
        }
        else {
            Rect v10 = new Rect();
            this.mActivity.getWindow().getDecorView().getWindowVisibleDisplayFrame(v10);
            v10_1 = v10.top;
        }

        this.mListFooter.measure(View$MeasureSpec.makeMeasureSpec(0, 0), View$MeasureSpec.makeMeasureSpec(0, 0));
        int v2 = 2;
        int v1 = this.mDisplayMetrics.heightPixels - v10_1 - this.mListFooter.getMeasuredHeight() - this.mPopupVerticalMargin * 2 - this.mContentView.getPaddingTop() - this.mContentView.getPaddingBottom();
        v10_1 = this.mContext.getResources().getDimensionPixelSize(0x7F050079);
        v10_1 = v1 > 0 ? v1 / v10_1 : 0;
        this.mNumberOfSuggestionsToUse = Math.min(this.mNumberOfSuggestionsToUse, v10_1);
        this.updateDividerVisibility();
        this.measureContent();
        v10_1 = this.mContentView.getMeasuredWidth();
        v1 = this.mContentView.getMeasuredHeight();
        int v6 = ((int)Math.round(arg6 - (((double)((((float)v10_1)) / 2f)))));
        int v7 = ((int)Math.round(arg8));
        int[] v8 = new int[v2];
        this.mParentView.getLocationInWindow(v8);
        this.mPopupWindow.showAtLocation(this.mParentView, 0, Math.max(-this.mContentView.getPaddingLeft(), Math.min(this.mDisplayMetrics.widthPixels - v10_1 + this.mContentView.getPaddingRight(), v6 + v8[0])), Math.min(v7 + v8[1] - this.mContentView.getPaddingTop(), this.mDisplayMetrics.heightPixels - v1 - this.mContentView.getPaddingTop() - this.mPopupVerticalMargin));
    }

    private void updateDividerVisibility() {
        if(this.mNumberOfSuggestionsToUse == 0) {
            this.mDivider.setVisibility(8);
        }
        else {
            this.mDivider.setVisibility(0);
        }
    }

    public void updateWindowAndroid(WindowAndroid arg1) {
        this.mWindowAndroid = arg1;
    }
}

