package android.support.v7.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.graphics.drawable.DrawableWrapper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View$MeasureSpec;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.lang.reflect.Field;

@RestrictTo(value={Scope.LIBRARY_GROUP}) public class ListViewCompat extends ListView {
    class GateKeeperDrawable extends DrawableWrapper {
        private boolean mEnabled;

        public GateKeeperDrawable(Drawable arg1) {
            super(arg1);
            this.mEnabled = true;
        }

        public void draw(Canvas arg2) {
            if(this.mEnabled) {
                super.draw(arg2);
            }
        }

        void setEnabled(boolean arg1) {
            this.mEnabled = arg1;
        }

        public void setHotspot(float arg2, float arg3) {
            if(this.mEnabled) {
                super.setHotspot(arg2, arg3);
            }
        }

        public void setHotspotBounds(int arg2, int arg3, int arg4, int arg5) {
            if(this.mEnabled) {
                super.setHotspotBounds(arg2, arg3, arg4, arg5);
            }
        }

        public boolean setState(int[] arg2) {
            if(this.mEnabled) {
                return super.setState(arg2);
            }

            return 0;
        }

        public boolean setVisible(boolean arg2, boolean arg3) {
            if(this.mEnabled) {
                return super.setVisible(arg2, arg3);
            }

            return 0;
        }
    }

    public static final int INVALID_POSITION = -1;
    public static final int NO_POSITION = -1;
    private static final int[] STATE_SET_NOTHING;
    private Field mIsChildViewEnabled;
    protected int mMotionPosition;
    int mSelectionBottomPadding;
    int mSelectionLeftPadding;
    int mSelectionRightPadding;
    int mSelectionTopPadding;
    private GateKeeperDrawable mSelector;
    final Rect mSelectorRect;

    static {
        ListViewCompat.STATE_SET_NOTHING = new int[]{0};
    }

    public ListViewCompat(Context arg2) {
        this(arg2, null);
    }

    public ListViewCompat(Context arg2, AttributeSet arg3) {
        this(arg2, arg3, 0);
    }

    public ListViewCompat(Context arg1, AttributeSet arg2, int arg3) {
        super(arg1, arg2, arg3);
        this.mSelectorRect = new Rect();
        this.mSelectionLeftPadding = 0;
        this.mSelectionTopPadding = 0;
        this.mSelectionRightPadding = 0;
        this.mSelectionBottomPadding = 0;
        try {
            this.mIsChildViewEnabled = AbsListView.class.getDeclaredField("mIsChildViewEnabled");
            this.mIsChildViewEnabled.setAccessible(true);
        }
        catch(NoSuchFieldException v1) {
            ThrowableExtension.printStackTrace(((Throwable)v1));
        }
    }

    protected void dispatchDraw(Canvas arg1) {
        this.drawSelectorCompat(arg1);
        super.dispatchDraw(arg1);
    }

    protected void drawSelectorCompat(Canvas arg3) {
        if(!this.mSelectorRect.isEmpty()) {
            Drawable v0 = this.getSelector();
            if(v0 != null) {
                v0.setBounds(this.mSelectorRect);
                v0.draw(arg3);
            }
        }
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        this.setSelectorEnabled(true);
        this.updateSelectorStateCompat();
    }

    public int lookForSelectablePosition(int arg5, boolean arg6) {
        ListAdapter v0 = this.getAdapter();
        int v1 = -1;
        if(v0 != null) {
            if(this.isInTouchMode()) {
            }
            else {
                int v2 = v0.getCount();
                if(!this.getAdapter().areAllItemsEnabled()) {
                    if(arg6) {
                        for(arg5 = Math.max(0, arg5); arg5 < v2; ++arg5) {
                            if(v0.isEnabled(arg5)) {
                                break;
                            }
                        }
                    }
                    else {
                        for(arg5 = Math.min(arg5, v2 - 1); arg5 >= 0; --arg5) {
                            if(v0.isEnabled(arg5)) {
                                break;
                            }
                        }
                    }

                    if(arg5 >= 0) {
                        if(arg5 >= v2) {
                        }
                        else {
                            return arg5;
                        }
                    }

                    return v1;
                }
                else {
                    if(arg5 >= 0) {
                        if(arg5 >= v2) {
                        }
                        else {
                            return arg5;
                        }
                    }

                    return v1;
                }
            }
        }

        return v1;
    }

    public int measureHeightOfChildrenCompat(int arg11, int arg12, int arg13, int arg14, int arg15) {
        arg12 = this.getListPaddingTop();
        arg13 = this.getListPaddingBottom();
        this.getListPaddingLeft();
        this.getListPaddingRight();
        int v0 = this.getDividerHeight();
        Drawable v1 = this.getDivider();
        ListAdapter v2 = this.getAdapter();
        if(v2 == null) {
            return arg12 + arg13;
        }

        arg12 += arg13;
        if(v0 <= 0 || v1 == null) {
            v0 = 0;
        }
        else {
        }

        int v1_1 = v2.getCount();
        View v3 = null;
        int v5 = arg12;
        View v6 = v3;
        arg12 = 0;
        int v4 = 0;
        int v7 = 0;
        while(arg12 < v1_1) {
            int v8 = v2.getItemViewType(arg12);
            if(v8 != v4) {
                v6 = v3;
                v4 = v8;
            }

            v6 = v2.getView(arg12, v6, ((ViewGroup)this));
            ViewGroup$LayoutParams v8_1 = v6.getLayoutParams();
            if(v8_1 == null) {
                v8_1 = this.generateDefaultLayoutParams();
                v6.setLayoutParams(v8_1);
            }

            v8 = v8_1.height > 0 ? View$MeasureSpec.makeMeasureSpec(v8_1.height, 0x40000000) : View$MeasureSpec.makeMeasureSpec(0, 0);
            v6.measure(arg11, v8);
            v6.forceLayout();
            if(arg12 > 0) {
                v5 += v0;
            }

            v5 += v6.getMeasuredHeight();
            if(v5 >= arg14) {
                if(arg15 >= 0 && arg12 > arg15 && v7 > 0 && v5 != arg14) {
                    arg14 = v7;
                }

                return arg14;
            }

            if(arg15 >= 0 && arg12 >= arg15) {
                v7 = v5;
            }

            ++arg12;
        }

        return v5;
    }

    public boolean onTouchEvent(MotionEvent arg3) {
        if(arg3.getAction() != 0) {
        }
        else {
            this.mMotionPosition = this.pointToPosition(((int)arg3.getX()), ((int)arg3.getY()));
        }

        return super.onTouchEvent(arg3);
    }

    protected void positionSelectorCompat(int arg6, View arg7) {
        Rect v0 = this.mSelectorRect;
        v0.set(arg7.getLeft(), arg7.getTop(), arg7.getRight(), arg7.getBottom());
        v0.left -= this.mSelectionLeftPadding;
        v0.top -= this.mSelectionTopPadding;
        v0.right += this.mSelectionRightPadding;
        v0.bottom += this.mSelectionBottomPadding;
        try {
            boolean v0_1 = this.mIsChildViewEnabled.getBoolean(this);
            if(arg7.isEnabled() == v0_1) {
                return;
            }

            this.mIsChildViewEnabled.set(this, Boolean.valueOf((((int)v0_1)) ^ 1));
            if(arg6 == -1) {
                return;
            }

            this.refreshDrawableState();
        }
        catch(IllegalAccessException v6) {
            ThrowableExtension.printStackTrace(((Throwable)v6));
        }
    }

    protected void positionSelectorLikeFocusCompat(int arg5, View arg6) {
        Drawable v0 = this.getSelector();
        boolean v1 = true;
        int v3 = v0 == null || arg5 == -1 ? 0 : 1;
        if(v3 != 0) {
            v0.setVisible(false, false);
        }

        this.positionSelectorCompat(arg5, arg6);
        if(v3 != 0) {
            Rect v5 = this.mSelectorRect;
            float v6 = v5.exactCenterX();
            float v5_1 = v5.exactCenterY();
            if(this.getVisibility() == 0) {
            }
            else {
                v1 = false;
            }

            v0.setVisible(v1, false);
            DrawableCompat.setHotspot(v0, v6, v5_1);
        }
    }

    protected void positionSelectorLikeTouchCompat(int arg2, View arg3, float arg4, float arg5) {
        this.positionSelectorLikeFocusCompat(arg2, arg3);
        Drawable v3 = this.getSelector();
        if(v3 != null && arg2 != -1) {
            DrawableCompat.setHotspot(v3, arg4, arg5);
        }
    }

    public void setSelector(Drawable arg2) {
        GateKeeperDrawable v0 = arg2 != null ? new GateKeeperDrawable(arg2) : null;
        this.mSelector = v0;
        super.setSelector(this.mSelector);
        Rect v0_1 = new Rect();
        if(arg2 != null) {
            arg2.getPadding(v0_1);
        }

        this.mSelectionLeftPadding = v0_1.left;
        this.mSelectionTopPadding = v0_1.top;
        this.mSelectionRightPadding = v0_1.right;
        this.mSelectionBottomPadding = v0_1.bottom;
    }

    protected void setSelectorEnabled(boolean arg2) {
        if(this.mSelector != null) {
            this.mSelector.setEnabled(arg2);
        }
    }

    protected boolean shouldShowSelectorCompat() {
        boolean v0 = !this.touchModeDrawsInPressedStateCompat() || !this.isPressed() ? false : true;
        return v0;
    }

    protected boolean touchModeDrawsInPressedStateCompat() {
        return 0;
    }

    protected void updateSelectorStateCompat() {
        Drawable v0 = this.getSelector();
        if(v0 != null && (this.shouldShowSelectorCompat())) {
            v0.setState(this.getDrawableState());
        }
    }
}

