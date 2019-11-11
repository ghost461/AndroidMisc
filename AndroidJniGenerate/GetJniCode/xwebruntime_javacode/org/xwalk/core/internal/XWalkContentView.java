package org.xwalk.core.internal;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewStructure;
import android.view.accessibility.AccessibilityNodeProvider;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import com.tencent.xwebview.util.CompatibilityUtil;
import org.chromium.components.content_view.ContentView;
import org.chromium.content_public.browser.WebContents;
import org.chromium.content_public.browser.WebContentsAccessibility$$CC;
import org.chromium.content_public.browser.WebContentsAccessibility;

public class XWalkContentView extends ContentView {
    class org.xwalk.core.internal.XWalkContentView$1 {
    }

    class DummyViewForMiUiOrZTE extends View {
        public DummyViewForMiUiOrZTE(Context arg1) {
            super(arg1);
            this.setVisibility(8);
            this.setId(0x7F070040);
        }

        public void notifyScrollChanged(int arg1, int arg2, int arg3, int arg4) {
            this.setTop(-arg2);
        }
    }

    class XWalkContentViewApi16 extends XWalkContentView {
        public XWalkContentViewApi16(Context arg2, WebContents arg3, XWalkViewInternal arg4) {
            super(arg2, arg3, arg4, null);
        }

        public AccessibilityNodeProvider getAccessibilityNodeProvider() {
            AccessibilityNodeProvider v0 = this.getWebContentsAccessibility().getAccessibilityNodeProvider();
            if(v0 != null) {
                return v0;
            }

            return super.getAccessibilityNodeProvider();
        }

        public boolean performAccessibilityAction(int arg1, Bundle arg2) {
            return super.performAccessibilityAction(arg1, arg2);
        }
    }

    class XWalkContentViewApi23 extends XWalkContentViewApi16 {
        public XWalkContentViewApi23(Context arg1, WebContents arg2, XWalkViewInternal arg3) {
            super(arg1, arg2, arg3);
        }

        public void onProvideVirtualStructure(ViewStructure arg3) {
            this.getWebContentsAccessibility().onProvideVirtualStructure(arg3, false);
        }
    }

    private static final String TAG = "XWalkContentView";
    private DummyViewForMiUiOrZTE mDummyView;
    private WebContents mWebContents;
    private XWalkViewInternal mXWalkView;

    private XWalkContentView(Context arg1, WebContents arg2, XWalkViewInternal arg3) {
        super(arg1, arg2);
        this.mWebContents = arg2;
        this.mXWalkView = arg3;
    }

    XWalkContentView(Context arg1, WebContents arg2, XWalkViewInternal arg3, org.xwalk.core.internal.XWalkContentView$1 arg4) {
        this(arg1, arg2, arg3);
    }

    public void addView(View arg5, int arg6, ViewGroup$LayoutParams arg7) {
        if(CompatibilityUtil.needAddSpecialView(this.getContext())) {
            int v0 = this.getChildCount() <= 0 || this.getChildAt(this.getChildCount() - 1).getId() != 0x7F070040 ? 0 : 1;
            this.mDummyView = v0 != 0 ? this.getChildAt(this.getChildCount() - 1) : new DummyViewForMiUiOrZTE(this.getContext());
            super.addView(arg5, arg6, arg7);
            if(v0 != 0) {
                this.removeViewInLayout(this.mDummyView);
            }

            super.addView(this.mDummyView, -1, new ViewGroup$LayoutParams(0, 0));
        }
        else {
            super.addView(arg5, arg6, arg7);
        }
    }

    public int computeHorizontalScrollOffsetDelegate() {
        return this.computeHorizontalScrollOffset();
    }

    public int computeHorizontalScrollRangeDelegate() {
        return this.computeHorizontalScrollRange();
    }

    public int computeVerticalScrollExtentDelegate() {
        return this.computeVerticalScrollExtent();
    }

    public int computeVerticalScrollOffsetDelegate() {
        return this.computeVerticalScrollOffset();
    }

    public int computeVerticalScrollRangeDelegate() {
        return this.computeVerticalScrollRange();
    }

    public static XWalkContentView createContentView(Context arg2, WebContents arg3, XWalkViewInternal arg4) {
        if(Build$VERSION.SDK_INT >= 23) {
            return new XWalkContentViewApi23(arg2, arg3, arg4);
        }

        if(Build$VERSION.SDK_INT >= 16) {
            return new XWalkContentViewApi16(arg2, arg3, arg4);
        }

        return new XWalkContentView(arg2, arg3, arg4);
    }

    protected WebContentsAccessibility getWebContentsAccessibility() {
        return WebContentsAccessibility$$CC.fromWebContents$$STATIC$$(this.mWebContents);
    }

    public InputConnection onCreateInputConnection(EditorInfo arg2) {
        return this.mXWalkView.onCreateInputConnection(arg2);
    }

    public InputConnection onCreateInputConnectionSuper(EditorInfo arg1) {
        return super.onCreateInputConnection(arg1);
    }

    protected void onFocusChanged(boolean arg2, int arg3, Rect arg4) {
        this.mXWalkView.onFocusChangedDelegate(arg2, arg3, arg4);
        super.onFocusChanged(arg2, arg3, arg4);
    }

    public void onScrollChanged(int arg3, int arg4, int arg5, int arg6) {
        this.mXWalkView.onScrollChangedDelegate(arg3, arg4, arg5, arg6);
        this.mXWalkView.onOverScrolledDelegate(arg3, arg4, false, false);
        if(this.mDummyView != null) {
            this.mDummyView.notifyScrollChanged(arg3, arg4, arg5, arg6);
        }
    }

    public boolean onTouchEvent(MotionEvent arg2) {
        if(this.mXWalkView.onTouchEventDelegate(arg2)) {
            return 1;
        }

        return this.mWebContents.getEventForwarder().onTouchEvent(arg2);
    }

    public boolean performLongClick() {
        return this.mXWalkView.performLongClickDelegate();
    }

    public void setVerticalScrollBarEnabled(boolean arg1) {
        super.setVerticalScrollBarEnabled(arg1);
    }
}

