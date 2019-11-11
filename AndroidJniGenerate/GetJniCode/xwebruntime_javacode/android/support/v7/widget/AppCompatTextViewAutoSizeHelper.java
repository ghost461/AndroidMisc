package android.support.v7.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.os.Build$VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.v7.appcompat.R$styleable;
import android.text.Layout$Alignment;
import android.text.StaticLayout$Builder;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.widget.TextView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

class AppCompatTextViewAutoSizeHelper {
    private static final int DEFAULT_AUTO_SIZE_GRANULARITY_IN_PX = 1;
    private static final int DEFAULT_AUTO_SIZE_MAX_TEXT_SIZE_IN_SP = 0x70;
    private static final int DEFAULT_AUTO_SIZE_MIN_TEXT_SIZE_IN_SP = 12;
    private static final String TAG = "ACTVAutoSizeHelper";
    private static final RectF TEMP_RECTF = null;
    static final float UNSET_AUTO_SIZE_UNIFORM_CONFIGURATION_VALUE = -1f;
    private static final int VERY_WIDE = 0x100000;
    private float mAutoSizeMaxTextSizeInPx;
    private float mAutoSizeMinTextSizeInPx;
    private float mAutoSizeStepGranularityInPx;
    private int[] mAutoSizeTextSizesInPx;
    private int mAutoSizeTextType;
    private final Context mContext;
    private boolean mHasPresetAutoSizeValues;
    private boolean mNeedsAutoSizeText;
    private TextPaint mTempTextPaint;
    private final TextView mTextView;
    private static Hashtable sTextViewMethodByNameCache;

    static {
        AppCompatTextViewAutoSizeHelper.TEMP_RECTF = new RectF();
        AppCompatTextViewAutoSizeHelper.sTextViewMethodByNameCache = new Hashtable();
    }

    AppCompatTextViewAutoSizeHelper(TextView arg3) {
        super();
        this.mAutoSizeTextType = 0;
        this.mNeedsAutoSizeText = false;
        this.mAutoSizeStepGranularityInPx = -1f;
        this.mAutoSizeMinTextSizeInPx = -1f;
        this.mAutoSizeMaxTextSizeInPx = -1f;
        this.mAutoSizeTextSizesInPx = new int[0];
        this.mHasPresetAutoSizeValues = false;
        this.mTextView = arg3;
        this.mContext = this.mTextView.getContext();
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) void autoSizeText() {
        if(!this.isAutoSizeEnabled()) {
            return;
        }

        if(this.mNeedsAutoSizeText) {
            if(this.mTextView.getMeasuredHeight() > 0) {
                if(this.mTextView.getMeasuredWidth() <= 0) {
                }
                else {
                    int v0 = this.invokeAndReturnWithDefault(this.mTextView, "getHorizontallyScrolling", Boolean.valueOf(false)).booleanValue() ? 0x100000 : this.mTextView.getMeasuredWidth() - this.mTextView.getTotalPaddingLeft() - this.mTextView.getTotalPaddingRight();
                    int v1 = this.mTextView.getHeight() - this.mTextView.getCompoundPaddingBottom() - this.mTextView.getCompoundPaddingTop();
                    if(v0 > 0) {
                        if(v1 <= 0) {
                        }
                        else {
                            RectF v3 = AppCompatTextViewAutoSizeHelper.TEMP_RECTF;
                            __monitor_enter(v3);
                            try {
                                AppCompatTextViewAutoSizeHelper.TEMP_RECTF.setEmpty();
                                AppCompatTextViewAutoSizeHelper.TEMP_RECTF.right = ((float)v0);
                                AppCompatTextViewAutoSizeHelper.TEMP_RECTF.bottom = ((float)v1);
                                float v0_2 = ((float)this.findLargestTextSizeWhichFits(AppCompatTextViewAutoSizeHelper.TEMP_RECTF));
                                if(v0_2 != this.mTextView.getTextSize()) {
                                    this.setTextSizeInternal(0, v0_2);
                                }

                                __monitor_exit(v3);
                                goto label_64;
                            label_60:
                                __monitor_exit(v3);
                            }
                            catch(Throwable v0_1) {
                                goto label_60;
                            }

                            throw v0_1;
                        }
                    }

                    return;
                }
            }

            return;
        }

    label_64:
        this.mNeedsAutoSizeText = true;
    }

    private int[] cleanupAutoSizePresetSizes(int[] arg7) {
        int v0 = arg7.length;
        if(v0 == 0) {
            return arg7;
        }

        Arrays.sort(arg7);
        ArrayList v1 = new ArrayList();
        int v2 = 0;
        int v3;
        for(v3 = 0; v3 < v0; ++v3) {
            int v4 = arg7[v3];
            if(v4 > 0 && Collections.binarySearch(((List)v1), Integer.valueOf(v4)) < 0) {
                ((List)v1).add(Integer.valueOf(v4));
            }
        }

        if(v0 == ((List)v1).size()) {
            return arg7;
        }

        int v7 = ((List)v1).size();
        int[] v0_1 = new int[v7];
        while(v2 < v7) {
            v0_1[v2] = ((List)v1).get(v2).intValue();
            ++v2;
        }

        return v0_1;
    }

    private void clearAutoSizeConfiguration() {
        this.mAutoSizeTextType = 0;
        this.mAutoSizeMinTextSizeInPx = -1f;
        this.mAutoSizeMaxTextSizeInPx = -1f;
        this.mAutoSizeStepGranularityInPx = -1f;
        this.mAutoSizeTextSizesInPx = new int[0];
        this.mNeedsAutoSizeText = false;
    }

    @TargetApi(value=23) private StaticLayout createStaticLayoutForMeasuring(CharSequence arg5, Layout$Alignment arg6, int arg7, int arg8) {
        Object v0 = this.invokeAndReturnWithDefault(this.mTextView, "getTextDirectionHeuristic", TextDirectionHeuristics.FIRSTSTRONG_LTR);
        StaticLayout$Builder v5 = StaticLayout$Builder.obtain(arg5, 0, arg5.length(), this.mTempTextPaint, arg7).setAlignment(arg6).setLineSpacing(this.mTextView.getLineSpacingExtra(), this.mTextView.getLineSpacingMultiplier()).setIncludePad(this.mTextView.getIncludeFontPadding()).setBreakStrategy(this.mTextView.getBreakStrategy()).setHyphenationFrequency(this.mTextView.getHyphenationFrequency());
        if(arg8 == -1) {
            arg8 = 0x7FFFFFFF;
        }

        return v5.setMaxLines(arg8).setTextDirection(((TextDirectionHeuristic)v0)).build();
    }

    @TargetApi(value=14) private StaticLayout createStaticLayoutForMeasuringPre23(CharSequence arg12, Layout$Alignment arg13, int arg14) {
        boolean v2;
        float v1;
        float v0;
        if(Build$VERSION.SDK_INT >= 16) {
            v0 = this.mTextView.getLineSpacingMultiplier();
            v1 = this.mTextView.getLineSpacingExtra();
            v2 = this.mTextView.getIncludeFontPadding();
        }
        else {
            v0 = this.invokeAndReturnWithDefault(this.mTextView, "getLineSpacingMultiplier", Float.valueOf(1f)).floatValue();
            v1 = this.invokeAndReturnWithDefault(this.mTextView, "getLineSpacingExtra", Float.valueOf(0f)).floatValue();
            v2 = this.invokeAndReturnWithDefault(this.mTextView, "getIncludeFontPadding", Boolean.valueOf(true)).booleanValue();
        }

        float v8 = v0;
        float v9 = v1;
        boolean v10 = v2;
        return new StaticLayout(arg12, this.mTempTextPaint, arg14, arg13, v8, v9, v10);
    }

    private int findLargestTextSizeWhichFits(RectF arg6) {
        int v0 = this.mAutoSizeTextSizesInPx.length;
        if(v0 == 0) {
            throw new IllegalStateException("No available text sizes to choose from.");
        }

        --v0;
        int v1 = 1;
        int v2 = 0;
        while(v1 <= v0) {
            v2 = (v1 + v0) / 2;
            if(this.suggestedSizeFitsInSpace(this.mAutoSizeTextSizesInPx[v2], arg6)) {
                int v4 = v2 + 1;
                v2 = v1;
                v1 = v4;
                continue;
            }

            --v2;
            v0 = v2;
        }

        return this.mAutoSizeTextSizesInPx[v2];
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) int getAutoSizeMaxTextSize() {
        return Math.round(this.mAutoSizeMaxTextSizeInPx);
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) int getAutoSizeMinTextSize() {
        return Math.round(this.mAutoSizeMinTextSizeInPx);
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) int getAutoSizeStepGranularity() {
        return Math.round(this.mAutoSizeStepGranularityInPx);
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) int[] getAutoSizeTextAvailableSizes() {
        return this.mAutoSizeTextSizesInPx;
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) int getAutoSizeTextType() {
        return this.mAutoSizeTextType;
    }

    @Nullable private Method getTextViewMethod(@NonNull String arg5) {
        Object v0_1;
        try {
            v0_1 = AppCompatTextViewAutoSizeHelper.sTextViewMethodByNameCache.get(arg5);
            if(v0_1 == null) {
                Method v0_2 = TextView.class.getDeclaredMethod(arg5);
                if(v0_2 != null) {
                    v0_2.setAccessible(true);
                    AppCompatTextViewAutoSizeHelper.sTextViewMethodByNameCache.put(arg5, v0_2);
                }
            }
        }
        catch(Exception v0) {
            Log.w("ACTVAutoSizeHelper", "Failed to retrieve TextView#" + arg5 + "() method", ((Throwable)v0));
            return null;
        }

        return ((Method)v0_1);
    }

    private Object invokeAndReturnWithDefault(@NonNull Object arg4, @NonNull String arg5, @NonNull Object arg6) {
        try {
            arg4 = this.getTextViewMethod(arg5).invoke(arg4);
        }
        catch(Throwable v4) {
        }
        catch(Exception v4_1) {
            try {
                Log.w("ACTVAutoSizeHelper", "Failed to invoke TextView#" + arg5 + "() method", ((Throwable)v4_1));
                arg4 = arg6;
            }
            catch(Throwable v4) {
                throw v4;
            }
        }

        return arg4;
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) boolean isAutoSizeEnabled() {
        boolean v0 = !this.supportsAutoSizeText() || this.mAutoSizeTextType == 0 ? false : true;
        return v0;
    }

    void loadFromAttributes(AttributeSet arg7, int arg8) {
        int v4;
        TypedArray v7 = this.mContext.obtainStyledAttributes(arg7, styleable.AppCompatTextView, arg8, 0);
        if(v7.hasValue(styleable.AppCompatTextView_autoSizeTextType)) {
            this.mAutoSizeTextType = v7.getInt(styleable.AppCompatTextView_autoSizeTextType, 0);
        }

        float v0 = -1f;
        float v8 = v7.hasValue(styleable.AppCompatTextView_autoSizeStepGranularity) ? v7.getDimension(styleable.AppCompatTextView_autoSizeStepGranularity, v0) : -1f;
        float v1 = v7.hasValue(styleable.AppCompatTextView_autoSizeMinTextSize) ? v7.getDimension(styleable.AppCompatTextView_autoSizeMinTextSize, v0) : -1f;
        float v3 = v7.hasValue(styleable.AppCompatTextView_autoSizeMaxTextSize) ? v7.getDimension(styleable.AppCompatTextView_autoSizeMaxTextSize, v0) : -1f;
        if(v7.hasValue(styleable.AppCompatTextView_autoSizePresetSizes)) {
            v4 = v7.getResourceId(styleable.AppCompatTextView_autoSizePresetSizes, 0);
            if(v4 > 0) {
                TypedArray v4_1 = v7.getResources().obtainTypedArray(v4);
                this.setupAutoSizeUniformPresetSizes(v4_1);
                v4_1.recycle();
            }
        }

        v7.recycle();
        if(!this.supportsAutoSizeText()) {
            this.mAutoSizeTextType = 0;
        }
        else if(this.mAutoSizeTextType == 1) {
            if(!this.mHasPresetAutoSizeValues) {
                DisplayMetrics v7_1 = this.mContext.getResources().getDisplayMetrics();
                v4 = 2;
                if(Float.compare(v1, v0) == 0) {
                    v1 = TypedValue.applyDimension(v4, 12f, v7_1);
                }

                if(v3 == v0) {
                    v3 = TypedValue.applyDimension(v4, 112f, v7_1);
                }

                if(v8 == v0) {
                    v8 = 1f;
                }

                this.validateAndSetAutoSizeTextTypeUniformConfiguration(v1, v3, v8);
            }

            this.setupAutoSizeText();
        }
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) void setAutoSizeTextTypeUniformWithConfiguration(int arg2, int arg3, int arg4, int arg5) throws IllegalArgumentException {
        if(this.supportsAutoSizeText()) {
            DisplayMetrics v0 = this.mContext.getResources().getDisplayMetrics();
            this.validateAndSetAutoSizeTextTypeUniformConfiguration(TypedValue.applyDimension(arg5, ((float)arg2), v0), TypedValue.applyDimension(arg5, ((float)arg3), v0), TypedValue.applyDimension(arg5, ((float)arg4), v0));
            if(this.setupAutoSizeText()) {
                this.autoSizeText();
            }
        }
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) void setAutoSizeTextTypeUniformWithPresetSizes(@NonNull int[] arg6, int arg7) throws IllegalArgumentException {
        if(this.supportsAutoSizeText()) {
            int v0 = arg6.length;
            int v1 = 0;
            if(v0 > 0) {
                int[] v2 = new int[v0];
                if(arg7 == 0) {
                    v2 = Arrays.copyOf(arg6, v0);
                }
                else {
                    DisplayMetrics v3 = this.mContext.getResources().getDisplayMetrics();
                    while(v1 < v0) {
                        v2[v1] = Math.round(TypedValue.applyDimension(arg7, ((float)arg6[v1]), v3));
                        ++v1;
                    }
                }

                this.mAutoSizeTextSizesInPx = this.cleanupAutoSizePresetSizes(v2);
                if(this.setupAutoSizeUniformPresetSizesConfiguration()) {
                    goto label_35;
                }

                StringBuilder v0_1 = new StringBuilder();
                v0_1.append("None of the preset sizes is valid: ");
                v0_1.append(Arrays.toString(arg6));
                throw new IllegalArgumentException(v0_1.toString());
            }
            else {
                this.mHasPresetAutoSizeValues = false;
            }

        label_35:
            if(!this.setupAutoSizeText()) {
                return;
            }

            this.autoSizeText();
        }
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) void setAutoSizeTextTypeWithDefaults(int arg4) {
        if(this.supportsAutoSizeText()) {
            switch(arg4) {
                case 0: {
                    goto label_26;
                }
                case 1: {
                    goto label_12;
                }
            }

            StringBuilder v1 = new StringBuilder();
            v1.append("Unknown auto-size text type: ");
            v1.append(arg4);
            throw new IllegalArgumentException(v1.toString());
        label_26:
            this.clearAutoSizeConfiguration();
            return;
        label_12:
            DisplayMetrics v4 = this.mContext.getResources().getDisplayMetrics();
            this.validateAndSetAutoSizeTextTypeUniformConfiguration(TypedValue.applyDimension(2, 12f, v4), TypedValue.applyDimension(2, 112f, v4), 1f);
            if(this.setupAutoSizeText()) {
                this.autoSizeText();
            }
        }
    }

    private void setRawTextSize(float arg4) {
        if(arg4 != this.mTextView.getPaint().getTextSize()) {
            this.mTextView.getPaint().setTextSize(arg4);
            boolean v4 = Build$VERSION.SDK_INT >= 18 ? this.mTextView.isInLayout() : false;
            if(this.mTextView.getLayout() == null) {
                return;
            }

            this.mNeedsAutoSizeText = false;
            try {
                Method v0_1 = this.getTextViewMethod("nullLayouts");
                if(v0_1 == null) {
                    goto label_30;
                }

                v0_1.invoke(this.mTextView);
            }
            catch(Exception v0) {
                Log.w("ACTVAutoSizeHelper", "Failed to invoke TextView#nullLayouts() method", ((Throwable)v0));
            }

        label_30:
            if(!v4) {
                this.mTextView.requestLayout();
            }
            else {
                this.mTextView.forceLayout();
            }

            this.mTextView.invalidate();
        }
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) void setTextSizeInternal(int arg2, float arg3) {
        Resources v0 = this.mContext == null ? Resources.getSystem() : this.mContext.getResources();
        this.setRawTextSize(TypedValue.applyDimension(arg2, arg3, v0.getDisplayMetrics()));
    }

    private boolean setupAutoSizeText() {
        int v1 = 0;
        if(!this.supportsAutoSizeText() || this.mAutoSizeTextType != 1) {
            this.mNeedsAutoSizeText = false;
        }
        else {
            if(!this.mHasPresetAutoSizeValues || this.mAutoSizeTextSizesInPx.length == 0) {
                float v0 = ((float)Math.round(this.mAutoSizeMinTextSizeInPx));
                int v3 = 1;
                while(Math.round(this.mAutoSizeStepGranularityInPx + v0) <= Math.round(this.mAutoSizeMaxTextSizeInPx)) {
                    ++v3;
                    v0 += this.mAutoSizeStepGranularityInPx;
                }

                int[] v0_1 = new int[v3];
                float v4 = this.mAutoSizeMinTextSizeInPx;
                while(v1 < v3) {
                    v0_1[v1] = Math.round(v4);
                    v4 += this.mAutoSizeStepGranularityInPx;
                    ++v1;
                }

                this.mAutoSizeTextSizesInPx = this.cleanupAutoSizePresetSizes(v0_1);
            }

            this.mNeedsAutoSizeText = true;
        }

        return this.mNeedsAutoSizeText;
    }

    private void setupAutoSizeUniformPresetSizes(TypedArray arg5) {
        int v0 = arg5.length();
        int[] v1 = new int[v0];
        if(v0 > 0) {
            int v2;
            for(v2 = 0; v2 < v0; ++v2) {
                v1[v2] = arg5.getDimensionPixelSize(v2, -1);
            }

            this.mAutoSizeTextSizesInPx = this.cleanupAutoSizePresetSizes(v1);
            this.setupAutoSizeUniformPresetSizesConfiguration();
        }
    }

    private boolean setupAutoSizeUniformPresetSizesConfiguration() {
        int v0 = this.mAutoSizeTextSizesInPx.length;
        boolean v3 = v0 > 0 ? true : false;
        this.mHasPresetAutoSizeValues = v3;
        if(this.mHasPresetAutoSizeValues) {
            this.mAutoSizeTextType = 1;
            this.mAutoSizeMinTextSizeInPx = ((float)this.mAutoSizeTextSizesInPx[0]);
            this.mAutoSizeMaxTextSizeInPx = ((float)this.mAutoSizeTextSizesInPx[v0 - 1]);
            this.mAutoSizeStepGranularityInPx = -1f;
        }

        return this.mHasPresetAutoSizeValues;
    }

    private boolean suggestedSizeFitsInSpace(int arg6, RectF arg7) {
        CharSequence v0 = this.mTextView.getText();
        int v2 = -1;
        int v1 = Build$VERSION.SDK_INT >= 16 ? this.mTextView.getMaxLines() : -1;
        if(this.mTempTextPaint == null) {
            this.mTempTextPaint = new TextPaint();
        }
        else {
            this.mTempTextPaint.reset();
        }

        this.mTempTextPaint.set(this.mTextView.getPaint());
        this.mTempTextPaint.setTextSize(((float)arg6));
        Object v6 = this.invokeAndReturnWithDefault(this.mTextView, "getLayoutAlignment", Layout$Alignment.ALIGN_NORMAL);
        StaticLayout v6_1 = Build$VERSION.SDK_INT >= 23 ? this.createStaticLayoutForMeasuring(v0, ((Layout$Alignment)v6), Math.round(arg7.right), v1) : this.createStaticLayoutForMeasuringPre23(v0, ((Layout$Alignment)v6), Math.round(arg7.right));
        if(v1 != v2 && (v6_1.getLineCount() > v1 || v6_1.getLineEnd(v6_1.getLineCount() - 1) != v0.length())) {
            return 0;
        }

        if((((float)v6_1.getHeight())) > arg7.bottom) {
            return 0;
        }

        return 1;
    }

    private boolean supportsAutoSizeText() {
        boolean v0 = !(this.mTextView instanceof AppCompatEditText) ? true : false;
        return v0;
    }

    private void validateAndSetAutoSizeTextTypeUniformConfiguration(float arg3, float arg4, float arg5) throws IllegalArgumentException {
        if(arg3 <= 0f) {
            StringBuilder v5 = new StringBuilder();
            v5.append("Minimum auto-size text size (");
            v5.append(arg3);
            v5.append("px) is less or equal to (0px)");
            throw new IllegalArgumentException(v5.toString());
        }

        if(arg4 <= arg3) {
            StringBuilder v0 = new StringBuilder();
            v0.append("Maximum auto-size text size (");
            v0.append(arg4);
            v0.append("px) is less or equal to minimum auto-size ");
            v0.append("text size (");
            v0.append(arg3);
            v0.append("px)");
            throw new IllegalArgumentException(v0.toString());
        }

        if(arg5 <= 0f) {
            StringBuilder v4 = new StringBuilder();
            v4.append("The auto-size step granularity (");
            v4.append(arg5);
            v4.append("px) is less or equal to (0px)");
            throw new IllegalArgumentException(v4.toString());
        }

        this.mAutoSizeTextType = 1;
        this.mAutoSizeMinTextSizeInPx = arg3;
        this.mAutoSizeMaxTextSizeInPx = arg4;
        this.mAutoSizeStepGranularityInPx = arg5;
        this.mHasPresetAutoSizeValues = false;
    }
}

