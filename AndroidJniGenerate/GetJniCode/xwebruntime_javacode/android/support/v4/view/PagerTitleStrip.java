package android.support.v4.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.support.v4.widget.TextViewCompat;
import android.text.TextUtils$TruncateAt;
import android.text.method.SingleLineTransformationMethod;
import android.util.AttributeSet;
import android.view.View$MeasureSpec;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import java.util.Locale;

@DecorView public class PagerTitleStrip extends ViewGroup {
    class PageListener extends DataSetObserver implements OnAdapterChangeListener, OnPageChangeListener {
        private int mScrollState;

        PageListener(PagerTitleStrip arg1) {
            PagerTitleStrip.this = arg1;
            super();
        }

        public void onAdapterChanged(ViewPager arg1, PagerAdapter arg2, PagerAdapter arg3) {
            PagerTitleStrip.this.updateAdapter(arg2, arg3);
        }

        public void onChanged() {
            PagerTitleStrip.this.updateText(PagerTitleStrip.this.mPager.getCurrentItem(), PagerTitleStrip.this.mPager.getAdapter());
            float v1 = 0f;
            if(PagerTitleStrip.this.mLastKnownPositionOffset >= 0f) {
                v1 = PagerTitleStrip.this.mLastKnownPositionOffset;
            }

            PagerTitleStrip.this.updateTextPositions(PagerTitleStrip.this.mPager.getCurrentItem(), v1, true);
        }

        public void onPageScrollStateChanged(int arg1) {
            this.mScrollState = arg1;
        }

        public void onPageScrolled(int arg2, float arg3, int arg4) {
            if(arg3 > 0.5f) {
                ++arg2;
            }

            PagerTitleStrip.this.updateTextPositions(arg2, arg3, false);
        }

        public void onPageSelected(int arg4) {
            if(this.mScrollState == 0) {
                PagerTitleStrip.this.updateText(PagerTitleStrip.this.mPager.getCurrentItem(), PagerTitleStrip.this.mPager.getAdapter());
                float v0 = 0f;
                if(PagerTitleStrip.this.mLastKnownPositionOffset >= 0f) {
                    v0 = PagerTitleStrip.this.mLastKnownPositionOffset;
                }

                PagerTitleStrip.this.updateTextPositions(PagerTitleStrip.this.mPager.getCurrentItem(), v0, true);
            }
        }
    }

    class SingleLineAllCapsTransform extends SingleLineTransformationMethod {
        private Locale mLocale;

        SingleLineAllCapsTransform(Context arg1) {
            super();
            this.mLocale = arg1.getResources().getConfiguration().locale;
        }

        public CharSequence getTransformation(CharSequence arg1, View arg2) {
            String v1;
            arg1 = super.getTransformation(arg1, arg2);
            if(arg1 != null) {
                v1 = arg1.toString().toUpperCase(this.mLocale);
            }
            else {
                arg1 = null;
            }

            return ((CharSequence)v1);
        }
    }

    private static final int[] ATTRS = null;
    private static final float SIDE_ALPHA = 0.6f;
    private static final int[] TEXT_ATTRS = null;
    private static final int TEXT_SPACING = 16;
    TextView mCurrText;
    private int mGravity;
    private int mLastKnownCurrentPage;
    float mLastKnownPositionOffset;
    TextView mNextText;
    private int mNonPrimaryAlpha;
    private final PageListener mPageListener;
    ViewPager mPager;
    TextView mPrevText;
    private int mScaledTextSpacing;
    int mTextColor;
    private boolean mUpdatingPositions;
    private boolean mUpdatingText;
    private WeakReference mWatchingAdapter;

    static {
        PagerTitleStrip.ATTRS = new int[]{0x1010034, 0x1010095, 0x1010098, 0x10100AF};
        PagerTitleStrip.TEXT_ATTRS = new int[]{0x101038C};
    }

    public PagerTitleStrip(Context arg2) {
        this(arg2, null);
    }

    public PagerTitleStrip(Context arg5, AttributeSet arg6) {
        super(arg5, arg6);
        this.mLastKnownCurrentPage = -1;
        this.mLastKnownPositionOffset = -1f;
        this.mPageListener = new PageListener(this);
        TextView v0 = new TextView(arg5);
        this.mPrevText = v0;
        this.addView(((View)v0));
        v0 = new TextView(arg5);
        this.mCurrText = v0;
        this.addView(((View)v0));
        v0 = new TextView(arg5);
        this.mNextText = v0;
        this.addView(((View)v0));
        TypedArray v6 = arg5.obtainStyledAttributes(arg6, PagerTitleStrip.ATTRS);
        boolean v0_1 = false;
        int v1 = v6.getResourceId(0, 0);
        if(v1 != 0) {
            TextViewCompat.setTextAppearance(this.mPrevText, v1);
            TextViewCompat.setTextAppearance(this.mCurrText, v1);
            TextViewCompat.setTextAppearance(this.mNextText, v1);
        }

        int v2 = v6.getDimensionPixelSize(1, 0);
        if(v2 != 0) {
            this.setTextSize(0, ((float)v2));
        }

        v2 = 2;
        if(v6.hasValue(v2)) {
            v2 = v6.getColor(v2, 0);
            this.mPrevText.setTextColor(v2);
            this.mCurrText.setTextColor(v2);
            this.mNextText.setTextColor(v2);
        }

        this.mGravity = v6.getInteger(3, 80);
        v6.recycle();
        this.mTextColor = this.mCurrText.getTextColors().getDefaultColor();
        this.setNonPrimaryAlpha(0.6f);
        this.mPrevText.setEllipsize(TextUtils$TruncateAt.END);
        this.mCurrText.setEllipsize(TextUtils$TruncateAt.END);
        this.mNextText.setEllipsize(TextUtils$TruncateAt.END);
        if(v1 != 0) {
            v6 = arg5.obtainStyledAttributes(v1, PagerTitleStrip.TEXT_ATTRS);
            v0_1 = v6.getBoolean(0, false);
            v6.recycle();
        }

        if(v0_1) {
            PagerTitleStrip.setSingleLineAllCaps(this.mPrevText);
            PagerTitleStrip.setSingleLineAllCaps(this.mCurrText);
            PagerTitleStrip.setSingleLineAllCaps(this.mNextText);
        }
        else {
            this.mPrevText.setSingleLine();
            this.mCurrText.setSingleLine();
            this.mNextText.setSingleLine();
        }

        this.mScaledTextSpacing = ((int)(arg5.getResources().getDisplayMetrics().density * 16f));
    }

    int getMinHeight() {
        Drawable v0 = this.getBackground();
        int v0_1 = v0 != null ? v0.getIntrinsicHeight() : 0;
        return v0_1;
    }

    public int getTextSpacing() {
        return this.mScaledTextSpacing;
    }

    protected void onAttachedToWindow() {
        Object v0_1;
        super.onAttachedToWindow();
        ViewParent v0 = this.getParent();
        if(!(v0 instanceof ViewPager)) {
            throw new IllegalStateException("PagerTitleStrip must be a direct child of a ViewPager.");
        }

        PagerAdapter v1 = ((ViewPager)v0).getAdapter();
        ((ViewPager)v0).setInternalPageChangeListener(this.mPageListener);
        ((ViewPager)v0).addOnAdapterChangeListener(this.mPageListener);
        this.mPager = ((ViewPager)v0);
        if(this.mWatchingAdapter != null) {
            v0_1 = this.mWatchingAdapter.get();
        }
        else {
            PagerAdapter v0_2 = null;
        }

        this.updateAdapter(((PagerAdapter)v0_1), v1);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if(this.mPager != null) {
            this.updateAdapter(this.mPager.getAdapter(), null);
            this.mPager.setInternalPageChangeListener(null);
            this.mPager.removeOnAdapterChangeListener(this.mPageListener);
            this.mPager = null;
        }
    }

    protected void onLayout(boolean arg1, int arg2, int arg3, int arg4, int arg5) {
        if(this.mPager != null) {
            float v2 = 0f;
            if(this.mLastKnownPositionOffset >= 0f) {
                v2 = this.mLastKnownPositionOffset;
            }

            this.updateTextPositions(this.mLastKnownCurrentPage, v2, true);
        }
    }

    protected void onMeasure(int arg8, int arg9) {
        int v1 = 0x40000000;
        if(View$MeasureSpec.getMode(arg8) != v1) {
            throw new IllegalStateException("Must measure with an exact width");
        }

        int v0 = this.getPaddingTop() + this.getPaddingBottom();
        int v3 = PagerTitleStrip.getChildMeasureSpec(arg9, v0, -2);
        int v4 = View$MeasureSpec.getSize(arg8);
        arg8 = PagerTitleStrip.getChildMeasureSpec(arg8, ((int)((((float)v4)) * 0.2f)), -2);
        this.mPrevText.measure(arg8, v3);
        this.mCurrText.measure(arg8, v3);
        this.mNextText.measure(arg8, v3);
        arg8 = View$MeasureSpec.getMode(arg9) == v1 ? View$MeasureSpec.getSize(arg9) : Math.max(this.getMinHeight(), this.mCurrText.getMeasuredHeight() + v0);
        this.setMeasuredDimension(v4, View.resolveSizeAndState(arg8, arg9, this.mCurrText.getMeasuredState() << 16));
    }

    public void requestLayout() {
        if(!this.mUpdatingText) {
            super.requestLayout();
        }
    }

    public void setGravity(int arg1) {
        this.mGravity = arg1;
        this.requestLayout();
    }

    public void setNonPrimaryAlpha(@FloatRange(from=0, to=1) float arg3) {
        this.mNonPrimaryAlpha = (((int)(arg3 * 255f))) & 0xFF;
        int v3 = this.mNonPrimaryAlpha << 24 | this.mTextColor & 0xFFFFFF;
        this.mPrevText.setTextColor(v3);
        this.mNextText.setTextColor(v3);
    }

    private static void setSingleLineAllCaps(TextView arg2) {
        arg2.setTransformationMethod(new SingleLineAllCapsTransform(arg2.getContext()));
    }

    public void setTextColor(@ColorInt int arg3) {
        this.mTextColor = arg3;
        this.mCurrText.setTextColor(arg3);
        arg3 = this.mNonPrimaryAlpha << 24 | this.mTextColor & 0xFFFFFF;
        this.mPrevText.setTextColor(arg3);
        this.mNextText.setTextColor(arg3);
    }

    public void setTextSize(int arg2, float arg3) {
        this.mPrevText.setTextSize(arg2, arg3);
        this.mCurrText.setTextSize(arg2, arg3);
        this.mNextText.setTextSize(arg2, arg3);
    }

    public void setTextSpacing(int arg1) {
        this.mScaledTextSpacing = arg1;
        this.requestLayout();
    }

    void updateAdapter(PagerAdapter arg2, PagerAdapter arg3) {
        if(arg2 != null) {
            arg2.unregisterDataSetObserver(this.mPageListener);
            this.mWatchingAdapter = null;
        }

        if(arg3 != null) {
            arg3.registerDataSetObserver(this.mPageListener);
            this.mWatchingAdapter = new WeakReference(arg3);
        }

        if(this.mPager != null) {
            this.mLastKnownCurrentPage = -1;
            this.mLastKnownPositionOffset = -1f;
            this.updateText(this.mPager.getCurrentItem(), arg3);
            this.requestLayout();
        }
    }

    void updateText(int arg6, PagerAdapter arg7) {
        int v1 = arg7 != null ? arg7.getCount() : 0;
        this.mUpdatingText = true;
        CharSequence v3 = null;
        CharSequence v2 = arg6 < 1 || arg7 == null ? v3 : arg7.getPageTitle(arg6 - 1);
        this.mPrevText.setText(v2);
        TextView v2_1 = this.mCurrText;
        CharSequence v4 = arg7 == null || arg6 >= v1 ? v3 : arg7.getPageTitle(arg6);
        v2_1.setText(v4);
        int v2_2 = arg6 + 1;
        if(v2_2 < v1 && arg7 != null) {
            v3 = arg7.getPageTitle(v2_2);
        }

        this.mNextText.setText(v3);
        int v7 = View$MeasureSpec.makeMeasureSpec(Math.max(0, ((int)((((float)(this.getWidth() - this.getPaddingLeft() - this.getPaddingRight()))) * 0.8f))), 0x80000000);
        v1 = View$MeasureSpec.makeMeasureSpec(Math.max(0, this.getHeight() - this.getPaddingTop() - this.getPaddingBottom()), 0x80000000);
        this.mPrevText.measure(v7, v1);
        this.mCurrText.measure(v7, v1);
        this.mNextText.measure(v7, v1);
        this.mLastKnownCurrentPage = arg6;
        if(!this.mUpdatingPositions) {
            this.updateTextPositions(arg6, this.mLastKnownPositionOffset, false);
        }

        this.mUpdatingText = false;
    }

    void updateTextPositions(int arg19, float arg20, boolean arg21) {
        PagerTitleStrip v0 = this;
        int v1 = arg19;
        float v2 = arg20;
        if(v1 != v0.mLastKnownCurrentPage) {
            v0.updateText(v1, v0.mPager.getAdapter());
        }
        else if(!arg21 && v2 == v0.mLastKnownPositionOffset) {
            return;
        }

        v0.mUpdatingPositions = true;
        v1 = v0.mPrevText.getMeasuredWidth();
        int v3 = v0.mCurrText.getMeasuredWidth();
        int v4 = v0.mNextText.getMeasuredWidth();
        int v5 = v3 / 2;
        int v6 = this.getWidth();
        int v7 = this.getHeight();
        int v8 = this.getPaddingLeft();
        int v9 = this.getPaddingRight();
        int v10 = this.getPaddingTop();
        int v11 = this.getPaddingBottom();
        int v13 = v9 + v5;
        int v12 = v6 - (v8 + v5) - v13;
        float v14 = 0.5f + v2;
        float v15 = 1f;
        if(v14 > v15) {
            v14 -= v15;
        }

        v13 = v6 - v13 - (((int)((((float)v12)) * v14))) - v5;
        v3 += v13;
        v5 = v0.mPrevText.getBaseline();
        v12 = v0.mCurrText.getBaseline();
        int v14_1 = v0.mNextText.getBaseline();
        int v15_1 = Math.max(Math.max(v5, v12), v14_1);
        v5 = v15_1 - v5;
        v12 = v15_1 - v12;
        v15_1 -= v14_1;
        int v17 = v4;
        int v2_1 = Math.max(Math.max(v0.mPrevText.getMeasuredHeight() + v5, v0.mCurrText.getMeasuredHeight() + v12), v0.mNextText.getMeasuredHeight() + v15_1);
        v4 = v0.mGravity & 0x70;
        if(v4 == 16) {
            v7 = (v7 - v10 - v11 - v2_1) / 2;
            v5 += v7;
            v12 += v7;
            v10 = v7 + v15_1;
        }
        else if(v4 != 80) {
            v5 += v10;
            v12 += v10;
            v10 += v15_1;
        }
        else {
            v7 = v7 - v11 - v2_1;
            v5 += v7;
            v12 += v7;
            v10 = v7 + v15_1;
        }

        v0.mCurrText.layout(v13, v12, v3, v0.mCurrText.getMeasuredHeight() + v12);
        v2_1 = Math.min(v8, v13 - v0.mScaledTextSpacing - v1);
        v0.mPrevText.layout(v2_1, v5, v1 + v2_1, v0.mPrevText.getMeasuredHeight() + v5);
        v1 = Math.max(v6 - v9 - v17, v3 + v0.mScaledTextSpacing);
        v0.mNextText.layout(v1, v10, v1 + v17, v0.mNextText.getMeasuredHeight() + v10);
        v0.mLastKnownPositionOffset = arg20;
        v0.mUpdatingPositions = false;
    }
}

