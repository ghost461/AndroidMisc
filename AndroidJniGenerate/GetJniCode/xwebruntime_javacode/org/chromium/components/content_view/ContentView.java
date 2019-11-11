package org.chromium.components.content_view;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View$MeasureSpec;
import android.view.View;
import android.view.ViewStructure;
import android.view.accessibility.AccessibilityNodeProvider;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.FrameLayout;
import com.ui.keyboard.KeyboardListener;
import org.chromium.base.Log;
import org.chromium.base.TraceEvent;
import org.chromium.content.browser.selection.SelectionPopupControllerImpl;
import org.chromium.content_public.browser.ContentViewCore$$CC;
import org.chromium.content_public.browser.ContentViewCore$InternalAccessDelegate;
import org.chromium.content_public.browser.ContentViewCore;
import org.chromium.content_public.browser.ImeAdapter$$CC;
import org.chromium.content_public.browser.SmartClipProvider;
import org.chromium.content_public.browser.WebContents;
import org.chromium.content_public.browser.WebContentsAccessibility$$CC;
import org.chromium.content_public.browser.WebContentsAccessibility;
import org.chromium.ui.base.EventForwarder;

public class ContentView extends FrameLayout implements InternalAccessDelegate, SmartClipProvider {
    class ContentViewApi23 extends ContentView {
        public ContentViewApi23(Context arg1, WebContents arg2) {
            super(arg1, arg2);
        }

        public void onProvideVirtualStructure(ViewStructure arg3) {
            WebContentsAccessibility v0 = this.getWebContentsAccessibility();
            if(v0 != null) {
                v0.onProvideVirtualStructure(arg3, false);
            }
        }
    }

    public static final int DEFAULT_MEASURE_SPEC = 0;
    private static final String TAG = "cr.ContentView";
    private int mDesiredHeightMeasureSpec;
    private int mDesiredWidthMeasureSpec;
    private EventForwarder mEventForwarder;
    int mNHeight;
    int mNNativHeight;
    int mNNativeWidth;
    int mNWidth;
    private KeyboardListener mOnLayoutListener;
    private final WebContents mWebContents;
    Rect rectPrevArea;

    static {
        ContentView.DEFAULT_MEASURE_SPEC = View$MeasureSpec.makeMeasureSpec(0, 0);
    }

    public ContentView(Context arg3, WebContents arg4) {
        super(arg3, null, 0x1010085);
        this.mDesiredWidthMeasureSpec = ContentView.DEFAULT_MEASURE_SPEC;
        this.mDesiredHeightMeasureSpec = ContentView.DEFAULT_MEASURE_SPEC;
        this.mNWidth = 0;
        this.mNHeight = 0;
        this.mNNativeWidth = 0;
        this.mNNativHeight = 0;
        if(this.getScrollBarStyle() == 0) {
            this.setHorizontalScrollBarEnabled(false);
            this.setVerticalScrollBarEnabled(false);
        }

        this.mWebContents = arg4;
        this.setFocusable(true);
        this.setFocusableInTouchMode(true);
    }

    static SelectionPopupControllerImpl access$000(ContentView arg0) {
        return arg0.getSelectionPopupController();
    }

    static WebContents access$100(ContentView arg0) {
        return arg0.mWebContents;
    }

    public boolean awakenScrollBars(int arg2, boolean arg3) {
        if(this.getScrollBarStyle() == 0) {
            return 0;
        }

        return super.awakenScrollBars(arg2, arg3);
    }

    protected int computeHorizontalScrollExtent() {
        ContentViewCore v0 = this.getContentViewCore();
        int v0_1 = v0 != null ? v0.computeHorizontalScrollExtent() : 0;
        return v0_1;
    }

    protected int computeHorizontalScrollOffset() {
        ContentViewCore v0 = this.getContentViewCore();
        int v0_1 = v0 != null ? v0.computeHorizontalScrollOffset() : 0;
        return v0_1;
    }

    protected int computeHorizontalScrollRange() {
        ContentViewCore v0 = this.getContentViewCore();
        int v0_1 = v0 != null ? v0.computeHorizontalScrollRange() : 0;
        return v0_1;
    }

    protected int computeVerticalScrollExtent() {
        ContentViewCore v0 = this.getContentViewCore();
        int v0_1 = v0 != null ? v0.computeVerticalScrollExtent() : 0;
        return v0_1;
    }

    protected int computeVerticalScrollOffset() {
        ContentViewCore v0 = this.getContentViewCore();
        int v0_1 = v0 != null ? v0.computeVerticalScrollOffset() : 0;
        return v0_1;
    }

    protected int computeVerticalScrollRange() {
        ContentViewCore v0 = this.getContentViewCore();
        int v0_1 = v0 != null ? v0.computeVerticalScrollRange() : 0;
        return v0_1;
    }

    public static ContentView createContentView(Context arg2, WebContents arg3) {
        if(Build$VERSION.SDK_INT >= 23) {
            return new ContentViewApi23(arg2, arg3);
        }

        return new ContentView(arg2, arg3);
    }

    public boolean dispatchKeyEvent(KeyEvent arg2) {
        if(this.isFocused()) {
            ContentViewCore v0 = this.getContentViewCore();
            boolean v2 = v0 == null || !v0.dispatchKeyEvent(arg2) ? false : true;
            return v2;
        }

        return super.dispatchKeyEvent(arg2);
    }

    public void extractSmartClipData(int arg2, int arg3, int arg4, int arg5) {
        this.mWebContents.requestSmartClipExtract(arg2, arg3, arg4, arg5);
    }

    public AccessibilityNodeProvider getAccessibilityNodeProvider() {
        WebContentsAccessibility v0 = this.getWebContentsAccessibility();
        AccessibilityNodeProvider v0_1 = v0 != null ? v0.getAccessibilityNodeProvider() : null;
        if(v0_1 != null) {
        }
        else {
            v0_1 = super.getAccessibilityNodeProvider();
        }

        return v0_1;
    }

    private ContentViewCore getContentViewCore() {
        ContentViewCore v0 = ContentViewCore$$CC.fromWebContents$$STATIC$$(this.mWebContents);
        if(v0 == null || !v0.isAlive()) {
            v0 = null;
        }
        else {
        }

        return v0;
    }

    private EventForwarder getEventForwarder() {
        if(this.mEventForwarder == null) {
            this.mEventForwarder = this.mWebContents.getEventForwarder();
        }

        return this.mEventForwarder;
    }

    private SelectionPopupControllerImpl getSelectionPopupController() {
        return SelectionPopupControllerImpl.fromWebContents(this.mWebContents);
    }

    protected WebContentsAccessibility getWebContentsAccessibility() {
        WebContentsAccessibility v0 = this.getContentViewCore() != null ? WebContentsAccessibility$$CC.fromWebContents$$STATIC$$(this.mWebContents) : null;
        return v0;
    }

    void notifyNativeSizeChanged(int arg3, int arg4) {
        this.mWebContents.setSize(arg3, arg4);
        this.mNNativeWidth = arg3;
        this.mNNativHeight = arg4;
        if(!this.getSelectionPopupController().isFocusedNodeEditable()) {
            return;
        }

        new Handler().postDelayed(new Runnable() {
            public void run() {
                if(ContentView.this.getSelectionPopupController().isFocusedNodeEditable()) {
                    ContentView.this.mWebContents.scrollFocusedEditableNodeIntoView();
                }
            }
        }, 99);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ContentViewCore v0 = this.getContentViewCore();
        if(v0 != null) {
            v0.onAttachedToWindow();
        }
    }

    public boolean onCheckIsTextEditor() {
        if(this.getContentViewCore() == null) {
            return 0;
        }

        return ImeAdapter$$CC.fromWebContents$$STATIC$$(this.mWebContents).onCheckIsTextEditor();
    }

    protected void onConfigurationChanged(Configuration arg2) {
        ContentViewCore v0 = this.getContentViewCore();
        if(v0 != null) {
            v0.onConfigurationChanged(arg2);
        }
    }

    public InputConnection onCreateInputConnection(EditorInfo arg2) {
        if(this.getContentViewCore() == null) {
            return null;
        }

        return ImeAdapter$$CC.fromWebContents$$STATIC$$(this.mWebContents).onCreateInputConnection(arg2);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ContentViewCore v0 = this.getContentViewCore();
        if(v0 != null) {
            v0.onDetachedFromWindow();
        }
    }

    public boolean onDragEvent(DragEvent arg2) {
        return this.getEventForwarder().onDragEvent(arg2, ((View)this));
    }

    protected void onFocusChanged(boolean arg2, int arg3, Rect arg4) {
        try {
            TraceEvent.begin("ContentView.onFocusChanged");
            super.onFocusChanged(arg2, arg3, arg4);
            ContentViewCore v3 = this.getContentViewCore();
            if(v3 != null) {
                v3.setHideKeyboardOnBlur(true);
                v3.onViewFocusChanged(arg2);
            }
        }
        catch(Throwable v2) {
            TraceEvent.end("ContentView.onFocusChanged");
            throw v2;
        }

        TraceEvent.end("ContentView.onFocusChanged");
    }

    public boolean onGenericMotionEvent(MotionEvent arg2) {
        ContentViewCore v0 = this.getContentViewCore();
        boolean v2 = v0 == null || !v0.onGenericMotionEvent(arg2) ? false : true;
        return v2;
    }

    public boolean onHoverEvent(MotionEvent arg3) {
        boolean v0 = this.getEventForwarder().onHoverEvent(arg3);
        WebContentsAccessibility v1 = this.getWebContentsAccessibility();
        if(v1 != null && !v1.isTouchExplorationEnabled()) {
            super.onHoverEvent(arg3);
        }

        return v0;
    }

    public boolean onKeyUp(int arg2, KeyEvent arg3) {
        ContentViewCore v0 = this.getContentViewCore();
        boolean v2 = v0 == null || !v0.onKeyUp(arg2, arg3) ? false : true;
        return v2;
    }

    protected void onLayout(boolean arg9, int arg10, int arg11, int arg12, int arg13) {
        super.onLayout(arg9, arg10, arg11, arg12, arg13);
        if(this.mOnLayoutListener == null) {
            this.mOnLayoutListener = new KeyboardListener();
        }

        try {
            boolean v0 = this.mOnLayoutListener.isShowingKeyboard();
            Rect v9 = this.mOnLayoutListener.onViewLayout(this, arg9, arg10, arg11, arg12, arg13);
            boolean v10 = this.mOnLayoutListener.isShowingKeyboard();
            if(v10 != v0 && this.rectPrevArea != null) {
                arg11 = 0;
                if((v10) && this.mNHeight > v9.height() && (this.getSelectionPopupController().isFocusedNodeEditable())) {
                    arg11 = this.mNHeight - v9.height();
                }

                this.notifyNativeSizeChanged(this.mNWidth, this.mNHeight - arg11);
            }

            this.rectPrevArea = new Rect(v9);
            return;
        }
        catch(Exception ) {
            return;
        }
    }

    protected void onMeasure(int arg3, int arg4) {
        if(this.mDesiredWidthMeasureSpec != ContentView.DEFAULT_MEASURE_SPEC) {
            arg3 = this.mDesiredWidthMeasureSpec;
        }

        if(this.mDesiredHeightMeasureSpec != ContentView.DEFAULT_MEASURE_SPEC) {
            arg4 = this.mDesiredHeightMeasureSpec;
        }

        super.onMeasure(arg3, arg4);
    }

    public void onScrollChanged(int arg1, int arg2, int arg3, int arg4) {
        super.onScrollChanged(arg1, arg2, arg3, arg4);
    }

    protected void onSizeChanged(int arg2, int arg3, int arg4, int arg5) {
        try {
            this.mNWidth = arg2;
            this.mNHeight = arg3;
            TraceEvent.begin("ContentView.onSizeChanged");
            super.onSizeChanged(arg2, arg3, arg4, arg5);
            Log.i("cr.ContentView", "onSizeChanged new w = " + arg2 + " new h = " + arg3 + " ins is " + this.mWebContents.hashCode(), new Object[0]);
            this.mWebContents.setSize(arg2, arg3);
        }
        catch(Throwable v2) {
            TraceEvent.end("ContentView.onSizeChanged");
            throw v2;
        }

        TraceEvent.end("ContentView.onSizeChanged");
    }

    public boolean onTouchEvent(MotionEvent arg2) {
        return this.getEventForwarder().onTouchEvent(arg2);
    }

    public void onWindowFocusChanged(boolean arg2) {
        super.onWindowFocusChanged(arg2);
        ContentViewCore v0 = this.getContentViewCore();
        if(v0 != null) {
            v0.onWindowFocusChanged(arg2);
        }
    }

    public boolean performAccessibilityAction(int arg3, Bundle arg4) {
        WebContentsAccessibility v0 = this.getWebContentsAccessibility();
        boolean v3 = v0 == null || !v0.supportsAction(arg3) ? super.performAccessibilityAction(arg3, arg4) : v0.performAction(arg3, arg4);
        return v3;
    }

    public boolean performLongClick() {
        return 0;
    }

    public void scrollBy(int arg2, int arg3) {
        ContentViewCore v0 = this.getContentViewCore();
        if(v0 != null) {
            v0.scrollBy(((float)arg2), ((float)arg3));
        }
    }

    public void scrollTo(int arg2, int arg3) {
        ContentViewCore v0 = this.getContentViewCore();
        if(v0 != null) {
            v0.scrollTo(((float)arg2), ((float)arg3));
        }
    }

    public void setDesiredMeasureSpec(int arg1, int arg2) {
        this.mDesiredWidthMeasureSpec = arg1;
        this.mDesiredHeightMeasureSpec = arg2;
    }

    public void setSmartClipResultHandler(Handler arg2) {
        this.mWebContents.setSmartClipResultHandler(arg2);
    }

    public boolean super_dispatchKeyEvent(KeyEvent arg1) {
        return super.dispatchKeyEvent(arg1);
    }

    public void super_onConfigurationChanged(Configuration arg1) {
        super.onConfigurationChanged(arg1);
    }

    public boolean super_onGenericMotionEvent(MotionEvent arg1) {
        return super.onGenericMotionEvent(arg1);
    }

    public boolean super_onKeyUp(int arg1, KeyEvent arg2) {
        return super.onKeyUp(arg1, arg2);
    }
}

