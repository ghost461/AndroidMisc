package org.chromium.ui.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.Layout;
import android.text.SpannableString;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem$OnMenuItemClickListener;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View$OnLongClickListener;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.PopupMenu$OnDismissListener;
import android.widget.PopupMenu;
import org.chromium.base.VisibleForTesting;

public class TextViewWithClickableSpans extends TextViewWithLeading {
    private AccessibilityManager mAccessibilityManager;
    private PopupMenu mDisambiguationMenu;

    public TextViewWithClickableSpans(Context arg1) {
        super(arg1);
        this.init();
    }

    public TextViewWithClickableSpans(Context arg1, AttributeSet arg2) {
        super(arg1, arg2);
        this.init();
    }

    static AccessibilityManager access$000(TextViewWithClickableSpans arg0) {
        return arg0.mAccessibilityManager;
    }

    static void access$100(TextViewWithClickableSpans arg0) {
        arg0.openDisambiguationMenu();
    }

    static PopupMenu access$202(TextViewWithClickableSpans arg0, PopupMenu arg1) {
        arg0.mDisambiguationMenu = arg1;
        return arg1;
    }

    @VisibleForTesting public ClickableSpan[] getClickableSpans() {
        CharSequence v0 = this.getText();
        if(!(v0 instanceof SpannableString)) {
            return null;
        }

        return ((SpannableString)v0).getSpans(0, ((SpannableString)v0).length(), ClickableSpan.class);
    }

    private void handleAccessibilityClick() {
        ClickableSpan[] v0 = this.getClickableSpans();
        if(v0 != null) {
            if(v0.length == 0) {
            }
            else {
                if(v0.length == 1) {
                    v0[0].onClick(((View)this));
                }
                else {
                    this.openDisambiguationMenu();
                }

                return;
            }
        }
    }

    private void init() {
        this.setSaveEnabled(false);
        this.mAccessibilityManager = this.getContext().getSystemService("accessibility");
        this.setOnLongClickListener(new View$OnLongClickListener() {
            public boolean onLongClick(View arg1) {
                if(!TextViewWithClickableSpans.this.mAccessibilityManager.isTouchExplorationEnabled()) {
                    return 0;
                }

                TextViewWithClickableSpans.this.openDisambiguationMenu();
                return 1;
            }
        });
    }

    @SuppressLint(value={"ClickableViewAccessibility"}) public boolean onTouchEvent(MotionEvent arg4) {
        boolean v0 = super.onTouchEvent(arg4);
        if(arg4.getAction() != 1 && (this.mAccessibilityManager.isTouchExplorationEnabled()) && !this.touchIntersectsAnyClickableSpans(arg4)) {
            this.handleAccessibilityClick();
            return 1;
        }

        return v0;
    }

    private void openDisambiguationMenu() {
        ClickableSpan[] v0 = this.getClickableSpans();
        if(v0 != null && v0.length != 0) {
            if(this.mDisambiguationMenu != null) {
            }
            else {
                CharSequence v1 = this.getText();
                this.mDisambiguationMenu = new PopupMenu(this.getContext(), ((View)this));
                Menu v2 = this.mDisambiguationMenu.getMenu();
                int v3 = v0.length;
                int v4;
                for(v4 = 0; v4 < v3; ++v4) {
                    v2.add(((SpannableString)v1).subSequence(((SpannableString)v1).getSpanStart(v0[v4]), ((SpannableString)v1).getSpanEnd(v0[v4]))).setOnMenuItemClickListener(new MenuItem$OnMenuItemClickListener(v0[v4]) {
                        public boolean onMenuItemClick(MenuItem arg2) {
                            this.val$clickableSpan.onClick(TextViewWithClickableSpans.this);
                            return 1;
                        }
                    });
                }

                this.mDisambiguationMenu.setOnDismissListener(new PopupMenu$OnDismissListener() {
                    public void onDismiss(PopupMenu arg2) {
                        TextViewWithClickableSpans.this.mDisambiguationMenu = null;
                    }
                });
                this.mDisambiguationMenu.show();
                return;
            }
        }
    }

    public boolean performAccessibilityAction(int arg2, Bundle arg3) {
        if(arg2 == 16) {
            this.handleAccessibilityClick();
            return 1;
        }

        return super.performAccessibilityAction(arg2, arg3);
    }

    private boolean touchIntersectsAnyClickableSpans(MotionEvent arg5) {
        CharSequence v0 = this.getText();
        boolean v2 = false;
        if(!(v0 instanceof SpannableString)) {
            return 0;
        }

        int v1 = ((int)arg5.getX());
        int v5 = ((int)arg5.getY());
        v1 -= this.getTotalPaddingLeft();
        v5 -= this.getTotalPaddingTop();
        v1 += this.getScrollX();
        v5 += this.getScrollY();
        Layout v3 = this.getLayout();
        v5 = v3.getOffsetForHorizontal(v3.getLineForVertical(v5), ((float)v1));
        if(((SpannableString)v0).getSpans(v5, v5, ClickableSpan.class).length > 0) {
            v2 = true;
        }

        return v2;
    }
}

