package org.chromium.content.browser;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.view.KeyEvent;
import android.view.ViewGroup;
import org.chromium.base.VisibleForTesting;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.content_public.browser.ImeEventObserver$$CC;
import org.chromium.content_public.browser.ImeEventObserver;
import org.chromium.content_public.browser.WebContents$UserDataFactory;
import org.chromium.content_public.browser.WebContents;

@JNINamespace(value="content") public class TapDisambiguator implements HideablePopup, ImeEventObserver {
    final class UserDataFactoryLazyHolder {
        private static final UserDataFactory INSTANCE;

        static {
            UserDataFactoryLazyHolder.INSTANCE = TapDisambiguator$UserDataFactoryLazyHolder$$Lambda$0.$instance;
        }

        private UserDataFactoryLazyHolder() {
            super();
        }

        static UserDataFactory access$000() {
            return UserDataFactoryLazyHolder.INSTANCE;
        }
    }

    private boolean mInitialized;
    private long mNativeTapDisambiguator;
    private PopupZoomer mPopupView;
    private final WebContents mWebContents;

    static {
    }

    public TapDisambiguator(WebContents arg1) {
        super();
        this.mWebContents = arg1;
    }

    static long access$100(TapDisambiguator arg2) {
        return arg2.mNativeTapDisambiguator;
    }

    static void access$200(TapDisambiguator arg0, long arg1, long arg3, float arg5, float arg6, boolean arg7) {
        arg0.nativeResolveTapDisambiguation(arg1, arg3, arg5, arg6, arg7);
    }

    public void backButtonPressed() {
        this.mPopupView.backButtonPressed();
    }

    public static TapDisambiguator create(Context arg2, WebContents arg3, ViewGroup arg4) {
        Object v3 = arg3.getOrSetUserData(TapDisambiguator.class, UserDataFactoryLazyHolder.access$000());
        ((TapDisambiguator)v3).init(arg2, arg4);
        return ((TapDisambiguator)v3);
    }

    @CalledByNative private static Rect createRect(int arg1, int arg2, int arg3, int arg4) {
        return new Rect(arg1, arg2, arg3, arg4);
    }

    @CalledByNative private void destroy() {
        this.mNativeTapDisambiguator = 0;
    }

    public static TapDisambiguator fromWebContents(WebContents arg2) {
        return arg2.getOrSetUserData(TapDisambiguator.class, null);
    }

    public void hide() {
        this.hidePopup(false);
    }

    public void hidePopup(boolean arg2) {
        this.mPopupView.hide(arg2);
    }

    @CalledByNative private void hidePopup() {
        this.hidePopup(false);
    }

    private void init(Context arg4, ViewGroup arg5) {
        this.mPopupView = new PopupZoomer(arg4, arg5, new OnVisibilityChangedListener(arg5) {
            public void onPopupZoomerHidden(PopupZoomer arg3) {
                this.val$containerView.post(new Runnable(arg3) {
                    public void run() {
                        if(this.this$1.val$containerView.indexOfChild(this.val$zoomer) != -1) {
                            this.this$1.val$containerView.removeView(this.val$zoomer);
                            this.this$1.val$containerView.invalidate();
                        }
                    }
                });
            }

            public void onPopupZoomerShown(PopupZoomer arg3) {
                this.val$containerView.post(new Runnable(arg3) {
                    public void run() {
                        if(this.this$1.val$containerView.indexOfChild(this.val$zoomer) == -1) {
                            this.this$1.val$containerView.addView(this.val$zoomer);
                        }
                    }
                });
            }
        }, new OnTapListener(arg5) {
            public void onResolveTapDisambiguation(long arg10, float arg12, float arg13, boolean arg14) {
                if(TapDisambiguator.this.mNativeTapDisambiguator == 0) {
                    return;
                }

                this.val$containerView.requestFocus();
                TapDisambiguator.this.nativeResolveTapDisambiguation(TapDisambiguator.this.mNativeTapDisambiguator, arg10, arg12, arg13, arg14);
            }
        });
        this.mNativeTapDisambiguator = this.nativeInit(this.mWebContents);
        PopupController.register(this.mWebContents, ((HideablePopup)this));
        this.mInitialized = true;
    }

    private boolean initialized() {
        return this.mInitialized;
    }

    public boolean isShowing() {
        return this.mPopupView.isShowing();
    }

    private native long nativeInit(WebContents arg1) {
    }

    private native void nativeResolveTapDisambiguation(long arg1, long arg2, float arg3, float arg4, boolean arg5) {
    }

    public void onBeforeSendKeyEvent(KeyEvent arg1) {
        ImeEventObserver$$CC.onBeforeSendKeyEvent(((ImeEventObserver)this), arg1);
    }

    public void onImeEvent() {
        this.hidePopup(true);
    }

    public void onNodeAttributeUpdated(boolean arg1, boolean arg2) {
        ImeEventObserver$$CC.onNodeAttributeUpdated(((ImeEventObserver)this), arg1, arg2);
    }

    public void setLastTouch(float arg2, float arg3) {
        this.mPopupView.setLastTouch(arg2, arg3);
    }

    @VisibleForTesting void setPopupZoomerForTest(PopupZoomer arg1) {
        this.mPopupView = arg1;
    }

    @CalledByNative private void showPopup(Rect arg2, Bitmap arg3) {
        this.mPopupView.setBitmap(arg3);
        this.mPopupView.show(arg2);
    }

    public void showPopup(Rect arg2) {
        this.mPopupView.show(arg2);
    }
}

