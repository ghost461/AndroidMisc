package android.support.v7.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.v7.app.ActionBar$Tab;
import android.support.v7.appcompat.R$attr;
import android.support.v7.view.ActionBarPolicy;
import android.text.TextUtils$TruncateAt;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View$MeasureSpec;
import android.view.View$OnClickListener;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewPropertyAnimator;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.AbsListView$LayoutParams;
import android.widget.AdapterView$OnItemSelectedListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

@RestrictTo(value={Scope.LIBRARY_GROUP}) public class ScrollingTabContainerView extends HorizontalScrollView implements AdapterView$OnItemSelectedListener {
    class TabAdapter extends BaseAdapter {
        TabAdapter(ScrollingTabContainerView arg1) {
            ScrollingTabContainerView.this = arg1;
            super();
        }

        public int getCount() {
            return ScrollingTabContainerView.this.mTabLayout.getChildCount();
        }

        public Object getItem(int arg2) {
            return ScrollingTabContainerView.this.mTabLayout.getChildAt(arg2).getTab();
        }

        public long getItemId(int arg3) {
            return ((long)arg3);
        }

        public View getView(int arg1, View arg2, ViewGroup arg3) {
            TabView v2;
            if(arg2 == null) {
                v2 = ScrollingTabContainerView.this.createTabView(this.getItem(arg1), true);
            }
            else {
                arg2.bindTab(this.getItem(arg1));
            }

            return ((View)v2);
        }
    }

    class TabClickListener implements View$OnClickListener {
        TabClickListener(ScrollingTabContainerView arg1) {
            ScrollingTabContainerView.this = arg1;
            super();
        }

        public void onClick(View arg6) {
            arg6.getTab().select();
            int v0 = ScrollingTabContainerView.this.mTabLayout.getChildCount();
            int v2;
            for(v2 = 0; v2 < v0; ++v2) {
                View v3 = ScrollingTabContainerView.this.mTabLayout.getChildAt(v2);
                boolean v4 = v3 == arg6 ? true : false;
                v3.setSelected(v4);
            }
        }
    }

    class TabView extends LinearLayoutCompat {
        private final int[] BG_ATTRS;
        private View mCustomView;
        private ImageView mIconView;
        private Tab mTab;
        private TextView mTextView;

        public TabView(ScrollingTabContainerView arg4, Context arg5, Tab arg6, boolean arg7) {
            ScrollingTabContainerView.this = arg4;
            super(arg5, null, attr.actionBarTabStyle);
            this.BG_ATTRS = new int[]{0x10100D4};
            this.mTab = arg6;
            TintTypedArray v4 = TintTypedArray.obtainStyledAttributes(arg5, null, this.BG_ATTRS, attr.actionBarTabStyle, 0);
            if(v4.hasValue(0)) {
                this.setBackgroundDrawable(v4.getDrawable(0));
            }

            v4.recycle();
            if(arg7) {
                this.setGravity(0x800013);
            }

            this.update();
        }

        public void bindTab(Tab arg1) {
            this.mTab = arg1;
            this.update();
        }

        public Tab getTab() {
            return this.mTab;
        }

        public void onInitializeAccessibilityEvent(AccessibilityEvent arg2) {
            super.onInitializeAccessibilityEvent(arg2);
            arg2.setClassName(Tab.class.getName());
        }

        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo arg2) {
            super.onInitializeAccessibilityNodeInfo(arg2);
            arg2.setClassName(Tab.class.getName());
        }

        public void onMeasure(int arg2, int arg3) {
            super.onMeasure(arg2, arg3);
            if(ScrollingTabContainerView.this.mMaxTabWidth > 0 && this.getMeasuredWidth() > ScrollingTabContainerView.this.mMaxTabWidth) {
                super.onMeasure(View$MeasureSpec.makeMeasureSpec(ScrollingTabContainerView.this.mMaxTabWidth, 0x40000000), arg3);
            }
        }

        public void setSelected(boolean arg2) {
            int v0 = this.isSelected() != arg2 ? 1 : 0;
            super.setSelected(arg2);
            if(v0 != 0 && (arg2)) {
                this.sendAccessibilityEvent(4);
            }
        }

        public void update() {
            Tab v0 = this.mTab;
            View v1 = v0.getCustomView();
            int v2 = 8;
            Drawable v3 = null;
            if(v1 != null) {
                ViewParent v0_1 = v1.getParent();
                if((((TabView)v0_1)) != this) {
                    if(v0_1 != null) {
                        ((ViewGroup)v0_1).removeView(v1);
                    }

                    this.addView(v1);
                }

                this.mCustomView = v1;
                if(this.mTextView != null) {
                    this.mTextView.setVisibility(v2);
                }

                if(this.mIconView == null) {
                    return;
                }

                this.mIconView.setVisibility(v2);
                this.mIconView.setImageDrawable(v3);
            }
            else {
                if(this.mCustomView != null) {
                    this.removeView(this.mCustomView);
                    this.mCustomView = ((View)v3);
                }

                Drawable v1_1 = v0.getIcon();
                CharSequence v4 = v0.getText();
                int v5 = 16;
                int v7 = -2;
                if(v1_1 != null) {
                    if(this.mIconView == null) {
                        AppCompatImageView v8 = new AppCompatImageView(this.getContext());
                        LayoutParams v9 = new LayoutParams(v7, v7);
                        v9.gravity = v5;
                        ((ImageView)v8).setLayoutParams(((ViewGroup$LayoutParams)v9));
                        this.addView(((View)v8), 0);
                        this.mIconView = ((ImageView)v8);
                    }

                    this.mIconView.setImageDrawable(v1_1);
                    this.mIconView.setVisibility(0);
                }
                else {
                    if(this.mIconView == null) {
                        goto label_55;
                    }

                    this.mIconView.setVisibility(v2);
                    this.mIconView.setImageDrawable(v3);
                }

            label_55:
                int v1_2 = TextUtils.isEmpty(v4) ^ 1;
                if(v1_2 != 0) {
                    if(this.mTextView == null) {
                        AppCompatTextView v2_1 = new AppCompatTextView(this.getContext(), ((AttributeSet)v3), attr.actionBarTabTextStyle);
                        ((TextView)v2_1).setEllipsize(TextUtils$TruncateAt.END);
                        LayoutParams v8_1 = new LayoutParams(v7, v7);
                        v8_1.gravity = v5;
                        ((TextView)v2_1).setLayoutParams(((ViewGroup$LayoutParams)v8_1));
                        this.addView(((View)v2_1));
                        this.mTextView = ((TextView)v2_1);
                    }

                    this.mTextView.setText(v4);
                    this.mTextView.setVisibility(0);
                }
                else {
                    if(this.mTextView == null) {
                        goto label_83;
                    }

                    this.mTextView.setVisibility(v2);
                    this.mTextView.setText(((CharSequence)v3));
                }

            label_83:
                if(this.mIconView != null) {
                    this.mIconView.setContentDescription(v0.getContentDescription());
                }

                if(v1_2 != 0) {
                }
                else {
                    CharSequence v3_1 = v0.getContentDescription();
                }

                TooltipCompat.setTooltipText(((View)this), ((CharSequence)v3));
            }
        }
    }

    public class VisibilityAnimListener extends AnimatorListenerAdapter {
        private boolean mCanceled;
        private int mFinalVisibility;

        protected VisibilityAnimListener(ScrollingTabContainerView arg1) {
            ScrollingTabContainerView.this = arg1;
            super();
            this.mCanceled = false;
        }

        public void onAnimationCancel(Animator arg1) {
            this.mCanceled = true;
        }

        public void onAnimationEnd(Animator arg2) {
            if(this.mCanceled) {
                return;
            }

            ScrollingTabContainerView.this.mVisibilityAnim = null;
            ScrollingTabContainerView.this.setVisibility(this.mFinalVisibility);
        }

        public void onAnimationStart(Animator arg2) {
            ScrollingTabContainerView.this.setVisibility(0);
            this.mCanceled = false;
        }

        public VisibilityAnimListener withFinalVisibility(ViewPropertyAnimator arg1, int arg2) {
            this.mFinalVisibility = arg2;
            ScrollingTabContainerView.this.mVisibilityAnim = arg1;
            return this;
        }
    }

    private static final int FADE_DURATION = 200;
    private static final String TAG = "ScrollingTabContainerView";
    private boolean mAllowCollapse;
    private int mContentHeight;
    int mMaxTabWidth;
    private int mSelectedTabIndex;
    int mStackedTabMaxWidth;
    private TabClickListener mTabClickListener;
    LinearLayoutCompat mTabLayout;
    Runnable mTabSelector;
    private Spinner mTabSpinner;
    protected final VisibilityAnimListener mVisAnimListener;
    protected ViewPropertyAnimator mVisibilityAnim;
    private static final Interpolator sAlphaInterpolator;

    static {
        ScrollingTabContainerView.sAlphaInterpolator = new DecelerateInterpolator();
    }

    public ScrollingTabContainerView(Context arg4) {
        super(arg4);
        this.mVisAnimListener = new VisibilityAnimListener(this);
        this.setHorizontalScrollBarEnabled(false);
        ActionBarPolicy v4 = ActionBarPolicy.get(arg4);
        this.setContentHeight(v4.getTabContainerHeight());
        this.mStackedTabMaxWidth = v4.getStackedTabMaxWidth();
        this.mTabLayout = this.createTabLayout();
        this.addView(this.mTabLayout, new ViewGroup$LayoutParams(-2, -1));
    }

    public void addTab(Tab arg6, int arg7, boolean arg8) {
        TabView v6 = this.createTabView(arg6, false);
        this.mTabLayout.addView(((View)v6), arg7, new LayoutParams(0, -1, 1f));
        if(this.mTabSpinner != null) {
            this.mTabSpinner.getAdapter().notifyDataSetChanged();
        }

        if(arg8) {
            v6.setSelected(true);
        }

        if(this.mAllowCollapse) {
            this.requestLayout();
        }
    }

    public void addTab(Tab arg6, boolean arg7) {
        TabView v6 = this.createTabView(arg6, false);
        this.mTabLayout.addView(((View)v6), new LayoutParams(0, -1, 1f));
        if(this.mTabSpinner != null) {
            this.mTabSpinner.getAdapter().notifyDataSetChanged();
        }

        if(arg7) {
            v6.setSelected(true);
        }

        if(this.mAllowCollapse) {
            this.requestLayout();
        }
    }

    public void animateToTab(int arg2) {
        View v2 = this.mTabLayout.getChildAt(arg2);
        if(this.mTabSelector != null) {
            this.removeCallbacks(this.mTabSelector);
        }

        this.mTabSelector = new Runnable(v2) {
            public void run() {
                ScrollingTabContainerView.this.smoothScrollTo(this.val$tabView.getLeft() - (ScrollingTabContainerView.this.getWidth() - this.val$tabView.getWidth()) / 2, 0);
                ScrollingTabContainerView.this.mTabSelector = null;
            }
        };
        this.post(this.mTabSelector);
    }

    public void animateToVisibility(int arg5) {
        ViewPropertyAnimator v2;
        if(this.mVisibilityAnim != null) {
            this.mVisibilityAnim.cancel();
        }

        long v0 = 200;
        if(arg5 == 0) {
            if(this.getVisibility() != 0) {
                this.setAlpha(0f);
            }

            v2 = this.animate().alpha(1f);
            v2.setDuration(v0);
            v2.setInterpolator(ScrollingTabContainerView.sAlphaInterpolator);
            v2.setListener(this.mVisAnimListener.withFinalVisibility(v2, arg5));
            v2.start();
        }
        else {
            v2 = this.animate().alpha(0f);
            v2.setDuration(v0);
            v2.setInterpolator(ScrollingTabContainerView.sAlphaInterpolator);
            v2.setListener(this.mVisAnimListener.withFinalVisibility(v2, arg5));
            v2.start();
        }
    }

    private Spinner createSpinner() {
        AppCompatSpinner v0 = new AppCompatSpinner(this.getContext(), null, attr.actionDropDownStyle);
        ((Spinner)v0).setLayoutParams(new LayoutParams(-2, -1));
        ((Spinner)v0).setOnItemSelectedListener(((AdapterView$OnItemSelectedListener)this));
        return ((Spinner)v0);
    }

    private LinearLayoutCompat createTabLayout() {
        LinearLayoutCompat v0 = new LinearLayoutCompat(this.getContext(), null, attr.actionBarTabBarStyle);
        v0.setMeasureWithLargestChildEnabled(true);
        v0.setGravity(17);
        v0.setLayoutParams(new LayoutParams(-2, -1));
        return v0;
    }

    TabView createTabView(Tab arg3, boolean arg4) {
        TabView v0 = new TabView(this, this.getContext(), arg3, arg4);
        if(arg4) {
            v0.setBackgroundDrawable(null);
            v0.setLayoutParams(new AbsListView$LayoutParams(-1, this.mContentHeight));
        }
        else {
            v0.setFocusable(true);
            if(this.mTabClickListener == null) {
                this.mTabClickListener = new TabClickListener(this);
            }

            v0.setOnClickListener(this.mTabClickListener);
        }

        return v0;
    }

    private boolean isCollapsed() {
        boolean v0 = this.mTabSpinner == null || this.mTabSpinner.getParent() != this ? false : true;
        return v0;
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if(this.mTabSelector != null) {
            this.post(this.mTabSelector);
        }
    }

    protected void onConfigurationChanged(Configuration arg2) {
        super.onConfigurationChanged(arg2);
        ActionBarPolicy v2 = ActionBarPolicy.get(this.getContext());
        this.setContentHeight(v2.getTabContainerHeight());
        this.mStackedTabMaxWidth = v2.getStackedTabMaxWidth();
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if(this.mTabSelector != null) {
            this.removeCallbacks(this.mTabSelector);
        }
    }

    public void onItemSelected(AdapterView arg1, View arg2, int arg3, long arg4) {
        ((TabView)arg2).getTab().select();
    }

    public void onMeasure(int arg7, int arg8) {
        arg8 = View$MeasureSpec.getMode(arg7);
        int v1 = 1;
        int v2 = 0x40000000;
        boolean v3 = arg8 == v2 ? true : false;
        this.setFillViewport(v3);
        int v4 = this.mTabLayout.getChildCount();
        if(v4 > 1) {
            if(arg8 != v2 && arg8 != 0x80000000) {
                goto label_32;
            }

            arg8 = 2;
            this.mMaxTabWidth = v4 > arg8 ? ((int)((((float)View$MeasureSpec.getSize(arg7))) * 0.4f)) : View$MeasureSpec.getSize(arg7) / arg8;
            this.mMaxTabWidth = Math.min(this.mMaxTabWidth, this.mStackedTabMaxWidth);
        }
        else {
        label_32:
            this.mMaxTabWidth = -1;
        }

        arg8 = View$MeasureSpec.makeMeasureSpec(this.mContentHeight, v2);
        if((v3) || !this.mAllowCollapse) {
            v1 = 0;
        }
        else {
        }

        if(v1 != 0) {
            this.mTabLayout.measure(0, arg8);
            if(this.mTabLayout.getMeasuredWidth() > View$MeasureSpec.getSize(arg7)) {
                this.performCollapse();
            }
            else {
                this.performExpand();
            }
        }
        else {
            this.performExpand();
        }

        int v0 = this.getMeasuredWidth();
        super.onMeasure(arg7, arg8);
        arg7 = this.getMeasuredWidth();
        if((v3) && v0 != arg7) {
            this.setTabSelected(this.mSelectedTabIndex);
        }
    }

    public void onNothingSelected(AdapterView arg1) {
    }

    private void performCollapse() {
        if(this.isCollapsed()) {
            return;
        }

        if(this.mTabSpinner == null) {
            this.mTabSpinner = this.createSpinner();
        }

        this.removeView(this.mTabLayout);
        this.addView(this.mTabSpinner, new ViewGroup$LayoutParams(-2, -1));
        if(this.mTabSpinner.getAdapter() == null) {
            this.mTabSpinner.setAdapter(new TabAdapter(this));
        }

        if(this.mTabSelector != null) {
            this.removeCallbacks(this.mTabSelector);
            this.mTabSelector = null;
        }

        this.mTabSpinner.setSelection(this.mSelectedTabIndex);
    }

    private boolean performExpand() {
        if(!this.isCollapsed()) {
            return 0;
        }

        this.removeView(this.mTabSpinner);
        this.addView(this.mTabLayout, new ViewGroup$LayoutParams(-2, -1));
        this.setTabSelected(this.mTabSpinner.getSelectedItemPosition());
        return 0;
    }

    public void removeAllTabs() {
        this.mTabLayout.removeAllViews();
        if(this.mTabSpinner != null) {
            this.mTabSpinner.getAdapter().notifyDataSetChanged();
        }

        if(this.mAllowCollapse) {
            this.requestLayout();
        }
    }

    public void removeTabAt(int arg2) {
        this.mTabLayout.removeViewAt(arg2);
        if(this.mTabSpinner != null) {
            this.mTabSpinner.getAdapter().notifyDataSetChanged();
        }

        if(this.mAllowCollapse) {
            this.requestLayout();
        }
    }

    public void setAllowCollapse(boolean arg1) {
        this.mAllowCollapse = arg1;
    }

    public void setContentHeight(int arg1) {
        this.mContentHeight = arg1;
        this.requestLayout();
    }

    public void setTabSelected(int arg6) {
        this.mSelectedTabIndex = arg6;
        int v0 = this.mTabLayout.getChildCount();
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            View v3 = this.mTabLayout.getChildAt(v2);
            boolean v4 = v2 == arg6 ? true : false;
            v3.setSelected(v4);
            if(v4) {
                this.animateToTab(arg6);
            }
        }

        if(this.mTabSpinner != null && arg6 >= 0) {
            this.mTabSpinner.setSelection(arg6);
        }
    }

    public void updateTab(int arg2) {
        this.mTabLayout.getChildAt(arg2).update();
        if(this.mTabSpinner != null) {
            this.mTabSpinner.getAdapter().notifyDataSetChanged();
        }

        if(this.mAllowCollapse) {
            this.requestLayout();
        }
    }
}

