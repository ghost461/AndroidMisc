package android.support.v7.widget;

import android.content.Context;
import android.os.Build$VERSION;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.support.v7.appcompat.R$attr;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

class DropDownListView extends ListViewCompat {
    private ViewPropertyAnimatorCompat mClickAnimation;
    private boolean mDrawsInPressedState;
    private boolean mHijackFocus;
    private boolean mListSelectionHidden;
    private ListViewAutoScrollHelper mScrollHelper;

    public DropDownListView(Context arg3, boolean arg4) {
        super(arg3, null, attr.dropDownListViewStyle);
        this.mHijackFocus = arg4;
        this.setCacheColorHint(0);
    }

    private void clearPressedItem() {
        this.mDrawsInPressedState = false;
        this.setPressed(false);
        this.drawableStateChanged();
        View v1 = this.getChildAt(this.mMotionPosition - this.getFirstVisiblePosition());
        if(v1 != null) {
            v1.setPressed(false);
        }

        if(this.mClickAnimation != null) {
            this.mClickAnimation.cancel();
            this.mClickAnimation = null;
        }
    }

    private void clickPressedItem(View arg3, int arg4) {
        this.performItemClick(arg3, arg4, this.getItemIdAtPosition(arg4));
    }

    public boolean hasFocus() {
        boolean v0 = (this.mHijackFocus) || (super.hasFocus()) ? true : false;
        return v0;
    }

    public boolean hasWindowFocus() {
        boolean v0 = (this.mHijackFocus) || (super.hasWindowFocus()) ? true : false;
        return v0;
    }

    public boolean isFocused() {
        boolean v0 = (this.mHijackFocus) || (super.isFocused()) ? true : false;
        return v0;
    }

    public boolean isInTouchMode() {
        boolean v0 = (this.mHijackFocus) && (this.mListSelectionHidden) || (super.isInTouchMode()) ? true : false;
        return v0;
    }

    public boolean onForwardedEvent(MotionEvent arg8, int arg9) {
        int v0 = arg8.getActionMasked();
        switch(v0) {
            case 1: {
                goto label_12;
            }
            case 2: {
                goto label_10;
            }
            case 3: {
                goto label_7;
            }
        }

        goto label_4;
    label_10:
        boolean v3 = true;
        goto label_13;
    label_12:
        v3 = false;
    label_13:
        arg9 = arg8.findPointerIndex(arg9);
        if(arg9 < 0) {
        label_7:
            arg9 = 0;
            v3 = false;
            goto label_34;
        }

        int v4 = ((int)arg8.getX(arg9));
        arg9 = ((int)arg8.getY(arg9));
        int v5 = this.pointToPosition(v4, arg9);
        if(v5 == -1) {
            arg9 = 1;
            goto label_34;
        }

        View v3_1 = this.getChildAt(v5 - this.getFirstVisiblePosition());
        this.setPressedItem(v3_1, v5, ((float)v4), ((float)arg9));
        if(v0 == 1) {
            this.clickPressedItem(v3_1, v5);
        }

    label_4:
        arg9 = 0;
        v3 = true;
    label_34:
        if(!v3 || arg9 != 0) {
            this.clearPressedItem();
        }

        if(v3) {
            if(this.mScrollHelper == null) {
                this.mScrollHelper = new ListViewAutoScrollHelper(((ListView)this));
            }

            this.mScrollHelper.setEnabled(true);
            this.mScrollHelper.onTouch(((View)this), arg8);
        }
        else {
            if(this.mScrollHelper == null) {
                return v3;
            }

            this.mScrollHelper.setEnabled(false);
        }

        return v3;
    }

    void setListSelectionHidden(boolean arg1) {
        this.mListSelectionHidden = arg1;
    }

    private void setPressedItem(View arg7, int arg8, float arg9, float arg10) {
        this.mDrawsInPressedState = true;
        int v2 = 21;
        if(Build$VERSION.SDK_INT >= v2) {
            this.drawableHotspotChanged(arg9, arg10);
        }

        if(!this.isPressed()) {
            this.setPressed(true);
        }

        this.layoutChildren();
        if(this.mMotionPosition != -1) {
            View v1 = this.getChildAt(this.mMotionPosition - this.getFirstVisiblePosition());
            if(v1 != null && v1 != arg7 && (v1.isPressed())) {
                v1.setPressed(false);
            }
        }

        this.mMotionPosition = arg8;
        float v1_1 = arg9 - (((float)arg7.getLeft()));
        float v3 = arg10 - (((float)arg7.getTop()));
        if(Build$VERSION.SDK_INT >= v2) {
            arg7.drawableHotspotChanged(v1_1, v3);
        }

        if(!arg7.isPressed()) {
            arg7.setPressed(true);
        }

        this.positionSelectorLikeTouchCompat(arg8, arg7, arg9, arg10);
        this.setSelectorEnabled(false);
        this.refreshDrawableState();
    }

    protected boolean touchModeDrawsInPressedStateCompat() {
        boolean v0 = (this.mDrawsInPressedState) || (super.touchModeDrawsInPressedStateCompat()) ? true : false;
        return v0;
    }
}

