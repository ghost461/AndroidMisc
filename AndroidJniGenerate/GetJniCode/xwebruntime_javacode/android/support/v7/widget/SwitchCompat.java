package android.support.v7.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.PorterDuff$Mode;
import android.graphics.Rect;
import android.graphics.Region$Op;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable$Callback;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R$attr;
import android.support.v7.appcompat.R$styleable;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.text.AllCapsTransformationMethod;
import android.text.Layout$Alignment;
import android.text.Layout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.util.Property;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.CompoundButton;

@RequiresApi(value=14) public class SwitchCompat extends CompoundButton {
    final class android.support.v7.widget.SwitchCompat$1 extends Property {
        android.support.v7.widget.SwitchCompat$1(Class arg1, String arg2) {
            super(arg1, arg2);
        }

        public Float get(SwitchCompat arg1) {
            return Float.valueOf(arg1.mThumbPosition);
        }

        public Object get(Object arg1) {
            return this.get(((SwitchCompat)arg1));
        }

        public void set(SwitchCompat arg1, Float arg2) {
            arg1.setThumbPosition(arg2.floatValue());
        }

        public void set(Object arg1, Object arg2) {
            this.set(((SwitchCompat)arg1), ((Float)arg2));
        }
    }

    private static final String ACCESSIBILITY_EVENT_CLASS_NAME = "android.widget.Switch";
    private static final int[] CHECKED_STATE_SET = null;
    private static final int MONOSPACE = 3;
    private static final int SANS = 1;
    private static final int SERIF = 2;
    private static final int THUMB_ANIMATION_DURATION = 0xFA;
    private static final Property THUMB_POS = null;
    private static final int TOUCH_MODE_DOWN = 1;
    private static final int TOUCH_MODE_DRAGGING = 2;
    private static final int TOUCH_MODE_IDLE;
    private boolean mHasThumbTint;
    private boolean mHasThumbTintMode;
    private boolean mHasTrackTint;
    private boolean mHasTrackTintMode;
    private int mMinFlingVelocity;
    private Layout mOffLayout;
    private Layout mOnLayout;
    ObjectAnimator mPositionAnimator;
    private boolean mShowText;
    private boolean mSplitTrack;
    private int mSwitchBottom;
    private int mSwitchHeight;
    private int mSwitchLeft;
    private int mSwitchMinWidth;
    private int mSwitchPadding;
    private int mSwitchRight;
    private int mSwitchTop;
    private TransformationMethod mSwitchTransformationMethod;
    private int mSwitchWidth;
    private final Rect mTempRect;
    private ColorStateList mTextColors;
    private CharSequence mTextOff;
    private CharSequence mTextOn;
    private final TextPaint mTextPaint;
    private Drawable mThumbDrawable;
    private float mThumbPosition;
    private int mThumbTextPadding;
    private ColorStateList mThumbTintList;
    private PorterDuff$Mode mThumbTintMode;
    private int mThumbWidth;
    private int mTouchMode;
    private int mTouchSlop;
    private float mTouchX;
    private float mTouchY;
    private Drawable mTrackDrawable;
    private ColorStateList mTrackTintList;
    private PorterDuff$Mode mTrackTintMode;
    private VelocityTracker mVelocityTracker;

    static {
        SwitchCompat.THUMB_POS = new android.support.v7.widget.SwitchCompat$1(Float.class, "thumbPos");
        SwitchCompat.CHECKED_STATE_SET = new int[]{0x10100A0};
    }

    public SwitchCompat(Context arg2) {
        this(arg2, null);
    }

    public SwitchCompat(Context arg2, AttributeSet arg3) {
        this(arg2, arg3, attr.switchStyle);
    }

    public SwitchCompat(Context arg6, AttributeSet arg7, int arg8) {
        super(arg6, arg7, arg8);
        ColorStateList v0 = null;
        this.mThumbTintList = v0;
        this.mThumbTintMode = ((PorterDuff$Mode)v0);
        this.mHasThumbTint = false;
        this.mHasThumbTintMode = false;
        this.mTrackTintList = v0;
        this.mTrackTintMode = ((PorterDuff$Mode)v0);
        this.mHasTrackTint = false;
        this.mHasTrackTintMode = false;
        this.mVelocityTracker = VelocityTracker.obtain();
        this.mTempRect = new Rect();
        this.mTextPaint = new TextPaint(1);
        this.mTextPaint.density = this.getResources().getDisplayMetrics().density;
        TintTypedArray v7 = TintTypedArray.obtainStyledAttributes(arg6, arg7, styleable.SwitchCompat, arg8, 0);
        this.mThumbDrawable = v7.getDrawable(styleable.SwitchCompat_android_thumb);
        if(this.mThumbDrawable != null) {
            this.mThumbDrawable.setCallback(((Drawable$Callback)this));
        }

        this.mTrackDrawable = v7.getDrawable(styleable.SwitchCompat_track);
        if(this.mTrackDrawable != null) {
            this.mTrackDrawable.setCallback(((Drawable$Callback)this));
        }

        this.mTextOn = v7.getText(styleable.SwitchCompat_android_textOn);
        this.mTextOff = v7.getText(styleable.SwitchCompat_android_textOff);
        this.mShowText = v7.getBoolean(styleable.SwitchCompat_showText, true);
        this.mThumbTextPadding = v7.getDimensionPixelSize(styleable.SwitchCompat_thumbTextPadding, 0);
        this.mSwitchMinWidth = v7.getDimensionPixelSize(styleable.SwitchCompat_switchMinWidth, 0);
        this.mSwitchPadding = v7.getDimensionPixelSize(styleable.SwitchCompat_switchPadding, 0);
        this.mSplitTrack = v7.getBoolean(styleable.SwitchCompat_splitTrack, false);
        ColorStateList v8 = v7.getColorStateList(styleable.SwitchCompat_thumbTint);
        if(v8 != null) {
            this.mThumbTintList = v8;
            this.mHasThumbTint = true;
        }

        int v2 = -1;
        PorterDuff$Mode v8_1 = DrawableUtils.parseTintMode(v7.getInt(styleable.SwitchCompat_thumbTintMode, v2), ((PorterDuff$Mode)v0));
        if(this.mThumbTintMode != v8_1) {
            this.mThumbTintMode = v8_1;
            this.mHasThumbTintMode = true;
        }

        if((this.mHasThumbTint) || (this.mHasThumbTintMode)) {
            this.applyThumbTint();
        }

        v8 = v7.getColorStateList(styleable.SwitchCompat_trackTint);
        if(v8 != null) {
            this.mTrackTintList = v8;
            this.mHasTrackTint = true;
        }

        v8_1 = DrawableUtils.parseTintMode(v7.getInt(styleable.SwitchCompat_trackTintMode, v2), ((PorterDuff$Mode)v0));
        if(this.mTrackTintMode != v8_1) {
            this.mTrackTintMode = v8_1;
            this.mHasTrackTintMode = true;
        }

        if((this.mHasTrackTint) || (this.mHasTrackTintMode)) {
            this.applyTrackTint();
        }

        arg8 = v7.getResourceId(styleable.SwitchCompat_switchTextAppearance, 0);
        if(arg8 != 0) {
            this.setSwitchTextAppearance(arg6, arg8);
        }

        v7.recycle();
        ViewConfiguration v6 = ViewConfiguration.get(arg6);
        this.mTouchSlop = v6.getScaledTouchSlop();
        this.mMinFlingVelocity = v6.getScaledMinimumFlingVelocity();
        this.refreshDrawableState();
        this.setChecked(this.isChecked());
    }

    static float access$000(SwitchCompat arg0) {
        return arg0.mThumbPosition;
    }

    private void animateThumbToCheckedState(boolean arg5) {
        float v5 = arg5 ? 1f : 0f;
        this.mPositionAnimator = ObjectAnimator.ofFloat(this, SwitchCompat.THUMB_POS, new float[]{v5});
        this.mPositionAnimator.setDuration(0xFA);
        if(Build$VERSION.SDK_INT >= 18) {
            this.mPositionAnimator.setAutoCancel(true);
        }

        this.mPositionAnimator.start();
    }

    private void applyThumbTint() {
        if(this.mThumbDrawable != null && ((this.mHasThumbTint) || (this.mHasThumbTintMode))) {
            this.mThumbDrawable = this.mThumbDrawable.mutate();
            if(this.mHasThumbTint) {
                DrawableCompat.setTintList(this.mThumbDrawable, this.mThumbTintList);
            }

            if(this.mHasThumbTintMode) {
                DrawableCompat.setTintMode(this.mThumbDrawable, this.mThumbTintMode);
            }

            if(!this.mThumbDrawable.isStateful()) {
                return;
            }

            this.mThumbDrawable.setState(this.getDrawableState());
        }
    }

    private void applyTrackTint() {
        if(this.mTrackDrawable != null && ((this.mHasTrackTint) || (this.mHasTrackTintMode))) {
            this.mTrackDrawable = this.mTrackDrawable.mutate();
            if(this.mHasTrackTint) {
                DrawableCompat.setTintList(this.mTrackDrawable, this.mTrackTintList);
            }

            if(this.mHasTrackTintMode) {
                DrawableCompat.setTintMode(this.mTrackDrawable, this.mTrackTintMode);
            }

            if(!this.mTrackDrawable.isStateful()) {
                return;
            }

            this.mTrackDrawable.setState(this.getDrawableState());
        }
    }

    private void cancelPositionAnimator() {
        if(this.mPositionAnimator != null) {
            this.mPositionAnimator.cancel();
        }
    }

    private void cancelSuperTouch(MotionEvent arg2) {
        arg2 = MotionEvent.obtain(arg2);
        arg2.setAction(3);
        super.onTouchEvent(arg2);
        arg2.recycle();
    }

    private static float constrain(float arg1, float arg2, float arg3) {
        if(arg1 < arg2) {
            arg1 = arg2;
        }
        else if(arg1 > arg3) {
            arg1 = arg3;
        }

        return arg1;
    }

    public void draw(Canvas arg11) {
        int v6_1;
        int v7;
        Rect v0 = this.mTempRect;
        int v1 = this.mSwitchLeft;
        int v2 = this.mSwitchTop;
        int v3 = this.mSwitchRight;
        int v4 = this.mSwitchBottom;
        int v5 = this.getThumbOffset() + v1;
        Rect v6 = this.mThumbDrawable != null ? DrawableUtils.getOpticalBounds(this.mThumbDrawable) : DrawableUtils.INSETS_NONE;
        if(this.mTrackDrawable != null) {
            this.mTrackDrawable.getPadding(v0);
            v5 += v0.left;
            if(v6 != null) {
                if(v6.left > v0.left) {
                    v1 += v6.left - v0.left;
                }

                v7 = v6.top > v0.top ? v6.top - v0.top + v2 : v2;
                if(v6.right > v0.right) {
                    v3 -= v6.right - v0.right;
                }

                if(v6.bottom <= v0.bottom) {
                    goto label_52;
                }

                v6_1 = v4 - (v6.bottom - v0.bottom);
            }
            else {
                v7 = v2;
            label_52:
                v6_1 = v4;
            }

            this.mTrackDrawable.setBounds(v1, v7, v3, v6_1);
        }

        if(this.mThumbDrawable != null) {
            this.mThumbDrawable.getPadding(v0);
            v1 = v5 - v0.left;
            v5 = v5 + this.mThumbWidth + v0.right;
            this.mThumbDrawable.setBounds(v1, v2, v5, v4);
            Drawable v0_1 = this.getBackground();
            if(v0_1 != null) {
                DrawableCompat.setHotspotBounds(v0_1, v1, v2, v5, v4);
            }
        }

        super.draw(arg11);
    }

    public void drawableHotspotChanged(float arg3, float arg4) {
        if(Build$VERSION.SDK_INT >= 21) {
            super.drawableHotspotChanged(arg3, arg4);
        }

        if(this.mThumbDrawable != null) {
            DrawableCompat.setHotspot(this.mThumbDrawable, arg3, arg4);
        }

        if(this.mTrackDrawable != null) {
            DrawableCompat.setHotspot(this.mTrackDrawable, arg3, arg4);
        }
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        int[] v0 = this.getDrawableState();
        Drawable v1 = this.mThumbDrawable;
        int v2 = 0;
        if(v1 != null && (v1.isStateful())) {
            v2 = 0 | v1.setState(v0);
        }

        v1 = this.mTrackDrawable;
        if(v1 != null && (v1.isStateful())) {
            v2 |= v1.setState(v0);
        }

        if(v2 != 0) {
            this.invalidate();
        }
    }

    public int getCompoundPaddingLeft() {
        if(!ViewUtils.isLayoutRtl(((View)this))) {
            return super.getCompoundPaddingLeft();
        }

        int v0 = super.getCompoundPaddingLeft() + this.mSwitchWidth;
        if(!TextUtils.isEmpty(this.getText())) {
            v0 += this.mSwitchPadding;
        }

        return v0;
    }

    public int getCompoundPaddingRight() {
        if(ViewUtils.isLayoutRtl(((View)this))) {
            return super.getCompoundPaddingRight();
        }

        int v0 = super.getCompoundPaddingRight() + this.mSwitchWidth;
        if(!TextUtils.isEmpty(this.getText())) {
            v0 += this.mSwitchPadding;
        }

        return v0;
    }

    public boolean getShowText() {
        return this.mShowText;
    }

    public boolean getSplitTrack() {
        return this.mSplitTrack;
    }

    public int getSwitchMinWidth() {
        return this.mSwitchMinWidth;
    }

    public int getSwitchPadding() {
        return this.mSwitchPadding;
    }

    private boolean getTargetCheckedState() {
        boolean v0 = this.mThumbPosition > 0.5f ? true : false;
        return v0;
    }

    public CharSequence getTextOff() {
        return this.mTextOff;
    }

    public CharSequence getTextOn() {
        return this.mTextOn;
    }

    public Drawable getThumbDrawable() {
        return this.mThumbDrawable;
    }

    private int getThumbOffset() {
        float v0 = ViewUtils.isLayoutRtl(((View)this)) ? 1f - this.mThumbPosition : this.mThumbPosition;
        return ((int)(v0 * (((float)this.getThumbScrollRange())) + 0.5f));
    }

    private int getThumbScrollRange() {
        if(this.mTrackDrawable != null) {
            Rect v0 = this.mTempRect;
            this.mTrackDrawable.getPadding(v0);
            Rect v1 = this.mThumbDrawable != null ? DrawableUtils.getOpticalBounds(this.mThumbDrawable) : DrawableUtils.INSETS_NONE;
            return this.mSwitchWidth - this.mThumbWidth - v0.left - v0.right - v1.left - v1.right;
        }

        return 0;
    }

    public int getThumbTextPadding() {
        return this.mThumbTextPadding;
    }

    @Nullable public ColorStateList getThumbTintList() {
        return this.mThumbTintList;
    }

    @Nullable public PorterDuff$Mode getThumbTintMode() {
        return this.mThumbTintMode;
    }

    public Drawable getTrackDrawable() {
        return this.mTrackDrawable;
    }

    @Nullable public ColorStateList getTrackTintList() {
        return this.mTrackTintList;
    }

    @Nullable public PorterDuff$Mode getTrackTintMode() {
        return this.mTrackTintMode;
    }

    private boolean hitThumb(float arg7, float arg8) {
        boolean v1 = false;
        if(this.mThumbDrawable == null) {
            return 0;
        }

        int v0 = this.getThumbOffset();
        this.mThumbDrawable.getPadding(this.mTempRect);
        int v2 = this.mSwitchTop - this.mTouchSlop;
        int v3 = this.mSwitchLeft + v0 - this.mTouchSlop;
        v0 = this.mThumbWidth + v3 + this.mTempRect.left + this.mTempRect.right + this.mTouchSlop;
        int v4 = this.mSwitchBottom + this.mTouchSlop;
        if(arg7 > (((float)v3)) && arg7 < (((float)v0)) && arg8 > (((float)v2)) && arg8 < (((float)v4))) {
            v1 = true;
        }

        return v1;
    }

    public void jumpDrawablesToCurrentState() {
        if(Build$VERSION.SDK_INT >= 14) {
            super.jumpDrawablesToCurrentState();
            if(this.mThumbDrawable != null) {
                this.mThumbDrawable.jumpToCurrentState();
            }

            if(this.mTrackDrawable != null) {
                this.mTrackDrawable.jumpToCurrentState();
            }

            if(this.mPositionAnimator == null) {
                return;
            }

            if(!this.mPositionAnimator.isStarted()) {
                return;
            }

            this.mPositionAnimator.end();
            this.mPositionAnimator = null;
        }
    }

    private Layout makeLayout(CharSequence arg9) {
        if(this.mSwitchTransformationMethod != null) {
            arg9 = this.mSwitchTransformationMethod.getTransformation(arg9, ((View)this));
        }

        CharSequence v1 = arg9;
        Layout v9 = null;
        TextPaint v2 = this.mTextPaint;
        int v3 = v1 != null ? ((int)Math.ceil(((double)Layout.getDesiredWidth(v1, this.mTextPaint)))) : 0;
        super(v1, v2, v3, Layout$Alignment.ALIGN_NORMAL, 1f, 0f, true);
        return v9;
    }

    protected int[] onCreateDrawableState(int arg2) {
        int[] v2 = super.onCreateDrawableState(arg2 + 1);
        if(this.isChecked()) {
            SwitchCompat.mergeDrawableStates(v2, SwitchCompat.CHECKED_STATE_SET);
        }

        return v2;
    }

    protected void onDraw(Canvas arg10) {
        int v5_1;
        super.onDraw(arg10);
        Rect v0 = this.mTempRect;
        Drawable v1 = this.mTrackDrawable;
        if(v1 != null) {
            v1.getPadding(v0);
        }
        else {
            v0.setEmpty();
        }

        int v2 = this.mSwitchTop;
        int v3 = this.mSwitchBottom;
        v2 += v0.top;
        v3 -= v0.bottom;
        Drawable v4 = this.mThumbDrawable;
        if(v1 != null) {
            if((this.mSplitTrack) && v4 != null) {
                Rect v5 = DrawableUtils.getOpticalBounds(v4);
                v4.copyBounds(v0);
                v0.left += v5.left;
                v0.right -= v5.right;
                v5_1 = arg10.save();
                arg10.clipRect(v0, Region$Op.DIFFERENCE);
                v1.draw(arg10);
                arg10.restoreToCount(v5_1);
                goto label_35;
            }

            v1.draw(arg10);
        }

    label_35:
        int v0_1 = arg10.save();
        if(v4 != null) {
            v4.draw(arg10);
        }

        Layout v1_1 = this.getTargetCheckedState() ? this.mOnLayout : this.mOffLayout;
        if(v1_1 != null) {
            int[] v5_2 = this.getDrawableState();
            if(this.mTextColors != null) {
                this.mTextPaint.setColor(this.mTextColors.getColorForState(v5_2, 0));
            }

            this.mTextPaint.drawableState = v5_2;
            if(v4 != null) {
                Rect v4_1 = v4.getBounds();
                v5_1 = v4_1.left + v4_1.right;
            }
            else {
                v5_1 = this.getWidth();
            }

            arg10.translate(((float)(v5_1 / 2 - v1_1.getWidth() / 2)), ((float)((v2 + v3) / 2 - v1_1.getHeight() / 2)));
            v1_1.draw(arg10);
        }

        arg10.restoreToCount(v0_1);
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent arg2) {
        super.onInitializeAccessibilityEvent(arg2);
        arg2.setClassName("android.widget.Switch");
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo arg4) {
        if(Build$VERSION.SDK_INT >= 14) {
            super.onInitializeAccessibilityNodeInfo(arg4);
            arg4.setClassName("android.widget.Switch");
            CharSequence v0 = this.isChecked() ? this.mTextOn : this.mTextOff;
            if(TextUtils.isEmpty(v0)) {
                return;
            }

            CharSequence v1 = arg4.getText();
            if(TextUtils.isEmpty(v1)) {
                arg4.setText(v0);
                return;
            }

            StringBuilder v2 = new StringBuilder();
            v2.append(v1);
            v2.append(' ');
            v2.append(v0);
            arg4.setText(((CharSequence)v2));
        }
    }

    protected void onLayout(boolean arg1, int arg2, int arg3, int arg4, int arg5) {
        int v1_1;
        super.onLayout(arg1, arg2, arg3, arg4, arg5);
        arg2 = 0;
        if(this.mThumbDrawable != null) {
            Rect v1 = this.mTempRect;
            if(this.mTrackDrawable != null) {
                this.mTrackDrawable.getPadding(v1);
            }
            else {
                v1.setEmpty();
            }

            Rect v3 = DrawableUtils.getOpticalBounds(this.mThumbDrawable);
            arg4 = Math.max(0, v3.left - v1.left);
            v1_1 = Math.max(0, v3.right - v1.right);
            arg2 = arg4;
        }
        else {
            v1_1 = 0;
        }

        if(ViewUtils.isLayoutRtl(((View)this))) {
            arg3 = this.getPaddingLeft() + arg2;
            arg4 = this.mSwitchWidth + arg3 - arg2 - v1_1;
        }
        else {
            arg4 = this.getWidth() - this.getPaddingRight() - v1_1;
            arg3 = arg4 - this.mSwitchWidth + arg2 + v1_1;
        }

        v1_1 = this.getGravity() & 0x70;
        if(v1_1 == 16) {
            v1_1 = (this.getPaddingTop() + this.getHeight() - this.getPaddingBottom()) / 2 - this.mSwitchHeight / 2;
            arg2 = this.mSwitchHeight + v1_1;
        }
        else if(v1_1 != 80) {
            v1_1 = this.getPaddingTop();
            arg2 = this.mSwitchHeight + v1_1;
        }
        else {
            arg2 = this.getHeight() - this.getPaddingBottom();
            v1_1 = arg2 - this.mSwitchHeight;
        }

        this.mSwitchLeft = arg3;
        this.mSwitchTop = v1_1;
        this.mSwitchBottom = arg2;
        this.mSwitchRight = arg4;
    }

    public void onMeasure(int arg7, int arg8) {
        int v3;
        int v1;
        if(this.mShowText) {
            if(this.mOnLayout == null) {
                this.mOnLayout = this.makeLayout(this.mTextOn);
            }

            if(this.mOffLayout != null) {
                goto label_12;
            }

            this.mOffLayout = this.makeLayout(this.mTextOff);
        }

    label_12:
        Rect v0 = this.mTempRect;
        int v2 = 0;
        if(this.mThumbDrawable != null) {
            this.mThumbDrawable.getPadding(v0);
            v1 = this.mThumbDrawable.getIntrinsicWidth() - v0.left - v0.right;
            v3 = this.mThumbDrawable.getIntrinsicHeight();
        }
        else {
            v1 = 0;
            v3 = 0;
        }

        int v4 = this.mShowText ? Math.max(this.mOnLayout.getWidth(), this.mOffLayout.getWidth()) + this.mThumbTextPadding * 2 : 0;
        this.mThumbWidth = Math.max(v4, v1);
        if(this.mTrackDrawable != null) {
            this.mTrackDrawable.getPadding(v0);
            v2 = this.mTrackDrawable.getIntrinsicHeight();
        }
        else {
            v0.setEmpty();
        }

        v1 = v0.left;
        int v0_1 = v0.right;
        if(this.mThumbDrawable != null) {
            Rect v4_1 = DrawableUtils.getOpticalBounds(this.mThumbDrawable);
            v1 = Math.max(v1, v4_1.left);
            v0_1 = Math.max(v0_1, v4_1.right);
        }

        v0_1 = Math.max(this.mSwitchMinWidth, this.mThumbWidth * 2 + v1 + v0_1);
        v1 = Math.max(v2, v3);
        this.mSwitchWidth = v0_1;
        this.mSwitchHeight = v1;
        super.onMeasure(arg7, arg8);
        if(this.getMeasuredHeight() < v1) {
            this.setMeasuredDimension(this.getMeasuredWidthAndState(), v1);
        }
    }

    public void onPopulateAccessibilityEvent(AccessibilityEvent arg2) {
        super.onPopulateAccessibilityEvent(arg2);
        CharSequence v0 = this.isChecked() ? this.mTextOn : this.mTextOff;
        if(v0 != null) {
            arg2.getText().add(v0);
        }
    }

    public boolean onTouchEvent(MotionEvent arg7) {
        float v1_1;
        float v0;
        float v3;
        this.mVelocityTracker.addMovement(arg7);
        int v1 = 2;
        switch(arg7.getActionMasked()) {
            case 0: {
                v0 = arg7.getX();
                v1_1 = arg7.getY();
                if(!this.isEnabled()) {
                    goto label_75;
                }

                if(!this.hitThumb(v0, v1_1)) {
                    goto label_75;
                }

                this.mTouchMode = 1;
                this.mTouchX = v0;
                this.mTouchY = v1_1;
                break;
            }
            case 2: {
                switch(this.mTouchMode) {
                    case 1: {
                        v0 = arg7.getX();
                        v3 = arg7.getY();
                        if(Math.abs(v0 - this.mTouchX) <= (((float)this.mTouchSlop)) && Math.abs(v3 - this.mTouchY) <= (((float)this.mTouchSlop))) {
                            goto label_75;
                        }

                        this.mTouchMode = v1;
                        this.getParent().requestDisallowInterceptTouchEvent(true);
                        this.mTouchX = v0;
                        this.mTouchY = v3;
                        return 1;
                    }
                    case 2: {
                        float v7 = arg7.getX();
                        int v0_1 = this.getThumbScrollRange();
                        v1_1 = v7 - this.mTouchX;
                        v3 = 1f;
                        if(v0_1 != 0) {
                            v1_1 /= ((float)v0_1);
                        }
                        else if(v1_1 > 0f) {
                            v1_1 = 1f;
                        }
                        else {
                            v1_1 = -1f;
                        }

                        if(ViewUtils.isLayoutRtl(((View)this))) {
                            v1_1 = -v1_1;
                        }

                        v0 = SwitchCompat.constrain(this.mThumbPosition + v1_1, 0f, v3);
                        if(v0 != this.mThumbPosition) {
                            this.mTouchX = v7;
                            this.setThumbPosition(v0);
                        }

                        return 1;
                    label_56:
                        if(this.mTouchMode == v1) {
                            this.stopDrag(arg7);
                            super.onTouchEvent(arg7);
                            return 1;
                        }

                        this.mTouchMode = 0;
                        this.mVelocityTracker.clear();
                        goto label_75;
                    }
                    default: {
                        goto label_75;
                    }
                }
            }
            case 1: 
            case 3: {
                goto label_56;
            }
            default: {
                break;
            }
        }

    label_75:
        return super.onTouchEvent(arg7);
    }

    public void setChecked(boolean arg2) {
        super.setChecked(arg2);
        arg2 = this.isChecked();
        if(this.getWindowToken() == null || !ViewCompat.isLaidOut(((View)this))) {
            this.cancelPositionAnimator();
            float v2 = arg2 ? 1f : 0f;
            this.setThumbPosition(v2);
        }
        else {
            this.animateThumbToCheckedState(arg2);
        }
    }

    public void setShowText(boolean arg2) {
        if(this.mShowText != arg2) {
            this.mShowText = arg2;
            this.requestLayout();
        }
    }

    public void setSplitTrack(boolean arg1) {
        this.mSplitTrack = arg1;
        this.invalidate();
    }

    public void setSwitchMinWidth(int arg1) {
        this.mSwitchMinWidth = arg1;
        this.requestLayout();
    }

    public void setSwitchPadding(int arg1) {
        this.mSwitchPadding = arg1;
        this.requestLayout();
    }

    public void setSwitchTextAppearance(Context arg4, int arg5) {
        TintTypedArray v4 = TintTypedArray.obtainStyledAttributes(arg4, arg5, styleable.TextAppearance);
        ColorStateList v5 = v4.getColorStateList(styleable.TextAppearance_android_textColor);
        this.mTextColors = v5 != null ? v5 : this.getTextColors();
        arg5 = v4.getDimensionPixelSize(styleable.TextAppearance_android_textSize, 0);
        if(arg5 != 0) {
            float v5_1 = ((float)arg5);
            if(v5_1 != this.mTextPaint.getTextSize()) {
                this.mTextPaint.setTextSize(v5_1);
                this.requestLayout();
            }
        }

        this.setSwitchTypefaceByIndex(v4.getInt(styleable.TextAppearance_android_typeface, -1), v4.getInt(styleable.TextAppearance_android_textStyle, -1));
        this.mSwitchTransformationMethod = v4.getBoolean(styleable.TextAppearance_textAllCaps, false) ? new AllCapsTransformationMethod(this.getContext()) : null;
        v4.recycle();
    }

    public void setSwitchTypeface(Typeface arg4, int arg5) {
        float v0 = 0f;
        boolean v1 = false;
        if(arg5 > 0) {
            arg4 = arg4 == null ? Typeface.defaultFromStyle(arg5) : Typeface.create(arg4, arg5);
            this.setSwitchTypeface(arg4);
            int v4 = arg4 != null ? arg4.getStyle() : 0;
            v4 = (v4 ^ -1) & arg5;
            TextPaint v5 = this.mTextPaint;
            if((v4 & 1) != 0) {
                v1 = true;
            }

            v5.setFakeBoldText(v1);
            v5 = this.mTextPaint;
            if((v4 & 2) != 0) {
                v0 = -0.25f;
            }

            v5.setTextSkewX(v0);
        }
        else {
            this.mTextPaint.setFakeBoldText(false);
            this.mTextPaint.setTextSkewX(0f);
            this.setSwitchTypeface(arg4);
        }
    }

    public void setSwitchTypeface(Typeface arg2) {
        if(this.mTextPaint.getTypeface() != null && !this.mTextPaint.getTypeface().equals(arg2) || this.mTextPaint.getTypeface() == null && arg2 != null) {
            this.mTextPaint.setTypeface(arg2);
            this.requestLayout();
            this.invalidate();
        }
    }

    private void setSwitchTypefaceByIndex(int arg1, int arg2) {
        Typeface v1;
        switch(arg1) {
            case 1: {
                v1 = Typeface.SANS_SERIF;
                break;
            }
            case 2: {
                v1 = Typeface.SERIF;
                break;
            }
            case 3: {
                v1 = Typeface.MONOSPACE;
                break;
            }
            default: {
                v1 = null;
                break;
            }
        }

        this.setSwitchTypeface(v1, arg2);
    }

    public void setTextOff(CharSequence arg1) {
        this.mTextOff = arg1;
        this.requestLayout();
    }

    public void setTextOn(CharSequence arg1) {
        this.mTextOn = arg1;
        this.requestLayout();
    }

    public void setThumbDrawable(Drawable arg3) {
        if(this.mThumbDrawable != null) {
            this.mThumbDrawable.setCallback(null);
        }

        this.mThumbDrawable = arg3;
        if(arg3 != null) {
            arg3.setCallback(((Drawable$Callback)this));
        }

        this.requestLayout();
    }

    void setThumbPosition(float arg1) {
        this.mThumbPosition = arg1;
        this.invalidate();
    }

    public void setThumbResource(int arg2) {
        this.setThumbDrawable(AppCompatResources.getDrawable(this.getContext(), arg2));
    }

    public void setThumbTextPadding(int arg1) {
        this.mThumbTextPadding = arg1;
        this.requestLayout();
    }

    public void setThumbTintList(@Nullable ColorStateList arg1) {
        this.mThumbTintList = arg1;
        this.mHasThumbTint = true;
        this.applyThumbTint();
    }

    public void setThumbTintMode(@Nullable PorterDuff$Mode arg1) {
        this.mThumbTintMode = arg1;
        this.mHasThumbTintMode = true;
        this.applyThumbTint();
    }

    public void setTrackDrawable(Drawable arg3) {
        if(this.mTrackDrawable != null) {
            this.mTrackDrawable.setCallback(null);
        }

        this.mTrackDrawable = arg3;
        if(arg3 != null) {
            arg3.setCallback(((Drawable$Callback)this));
        }

        this.requestLayout();
    }

    public void setTrackResource(int arg2) {
        this.setTrackDrawable(AppCompatResources.getDrawable(this.getContext(), arg2));
    }

    public void setTrackTintList(@Nullable ColorStateList arg1) {
        this.mTrackTintList = arg1;
        this.mHasTrackTint = true;
        this.applyTrackTint();
    }

    public void setTrackTintMode(@Nullable PorterDuff$Mode arg1) {
        this.mTrackTintMode = arg1;
        this.mHasTrackTintMode = true;
        this.applyTrackTint();
    }

    private void stopDrag(MotionEvent arg7) {
        boolean v1_2;
        this.mTouchMode = 0;
        boolean v2 = true;
        int v1 = arg7.getAction() != 1 || !this.isEnabled() ? 0 : 1;
        boolean v3 = this.isChecked();
        if(v1 != 0) {
            this.mVelocityTracker.computeCurrentVelocity(1000);
            float v1_1 = this.mVelocityTracker.getXVelocity();
            if(Math.abs(v1_1) > (((float)this.mMinFlingVelocity))) {
                if(ViewUtils.isLayoutRtl(((View)this))) {
                    if(v1_1 < 0f) {
                    }
                    else {
                        goto label_26;
                    }
                }
                else if(v1_1 <= 0f) {
                label_26:
                    v2 = false;
                }

                v1_2 = v2;
            }
            else {
                v1_2 = this.getTargetCheckedState();
            }
        }
        else {
            v1_2 = v3;
        }

        if(v1_2 != v3) {
            this.playSoundEffect(0);
        }

        this.setChecked(v1_2);
        this.cancelSuperTouch(arg7);
    }

    public void toggle() {
        this.setChecked(this.isChecked() ^ 1);
    }

    protected boolean verifyDrawable(Drawable arg2) {
        boolean v2 = (super.verifyDrawable(arg2)) || arg2 == this.mThumbDrawable || arg2 == this.mTrackDrawable ? true : false;
        return v2;
    }
}

