package org.chromium.content.browser.input;

import android.content.Context;
import android.view.View;
import org.chromium.base.VisibleForTesting;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.content.browser.PopupController$HideablePopup;
import org.chromium.content.browser.PopupController;
import org.chromium.content.browser.WindowAndroidChangedObserver;
import org.chromium.content.browser.WindowEventObserver$$CC;
import org.chromium.content.browser.WindowEventObserver;
import org.chromium.content.browser.webcontents.WebContentsImpl;
import org.chromium.content_public.browser.WebContents$UserDataFactory;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

@JNINamespace(value="content") public class TextSuggestionHost implements HideablePopup, WindowAndroidChangedObserver, WindowEventObserver {
    final class UserDataFactoryLazyHolder {
        private static final UserDataFactory INSTANCE;

        static {
            UserDataFactoryLazyHolder.INSTANCE = TextSuggestionHost$UserDataFactoryLazyHolder$$Lambda$0.$instance;
        }

        private UserDataFactoryLazyHolder() {
            super();
        }

        static UserDataFactory access$000() {
            return UserDataFactoryLazyHolder.INSTANCE;
        }
    }

    private View mContainerView;
    private Context mContext;
    private boolean mInitialized;
    private boolean mIsAttachedToWindow;
    private long mNativeTextSuggestionHost;
    private SpellCheckPopupWindow mSpellCheckPopupWindow;
    private TextSuggestionsPopupWindow mTextSuggestionsPopupWindow;
    private final WebContentsImpl mWebContents;
    private WindowAndroid mWindowAndroid;

    static {
    }

    public TextSuggestionHost(WebContents arg1) {
        super();
        this.mWebContents = ((WebContentsImpl)arg1);
    }

    public void applySpellCheckSuggestion(String arg3) {
        this.nativeApplySpellCheckSuggestion(this.mNativeTextSuggestionHost, arg3);
    }

    public void applyTextSuggestion(int arg3, int arg4) {
        this.nativeApplyTextSuggestion(this.mNativeTextSuggestionHost, arg3, arg4);
    }

    public static TextSuggestionHost create(Context arg2, WebContents arg3, WindowAndroid arg4, View arg5) {
        Object v3 = arg3.getOrSetUserData(TextSuggestionHost.class, UserDataFactoryLazyHolder.access$000());
        ((TextSuggestionHost)v3).init(arg2, arg4, arg5);
        return ((TextSuggestionHost)v3);
    }

    public void deleteActiveSuggestionRange() {
        this.nativeDeleteActiveSuggestionRange(this.mNativeTextSuggestionHost);
    }

    @CalledByNative private void destroy() {
        this.hidePopups();
        this.mNativeTextSuggestionHost = 0;
    }

    public static TextSuggestionHost fromWebContents(WebContents arg2) {
        return arg2.getOrSetUserData(TextSuggestionHost.class, null);
    }

    private float getContentOffsetYPix() {
        return this.mWebContents.getRenderCoordinates().getContentOffsetYPix();
    }

    @VisibleForTesting public SuggestionsPopupWindow getSpellCheckPopupWindowForTesting() {
        return this.mSpellCheckPopupWindow;
    }

    @VisibleForTesting public SuggestionsPopupWindow getTextSuggestionsPopupWindowForTesting() {
        return this.mTextSuggestionsPopupWindow;
    }

    public void hide() {
        this.hidePopups();
    }

    @CalledByNative public void hidePopups() {
        TextSuggestionsPopupWindow v1 = null;
        if(this.mTextSuggestionsPopupWindow != null && (this.mTextSuggestionsPopupWindow.isShowing())) {
            this.mTextSuggestionsPopupWindow.dismiss();
            this.mTextSuggestionsPopupWindow = v1;
        }

        if(this.mSpellCheckPopupWindow != null && (this.mSpellCheckPopupWindow.isShowing())) {
            this.mSpellCheckPopupWindow.dismiss();
            this.mSpellCheckPopupWindow = ((SpellCheckPopupWindow)v1);
        }
    }

    private void init(Context arg1, WindowAndroid arg2, View arg3) {
        this.mContext = arg1;
        this.mWindowAndroid = arg2;
        this.mContainerView = arg3;
        this.mNativeTextSuggestionHost = this.nativeInit(this.mWebContents);
        PopupController.register(this.mWebContents, ((HideablePopup)this));
        this.mInitialized = true;
    }

    private boolean initialized() {
        return this.mInitialized;
    }

    private native void nativeApplySpellCheckSuggestion(long arg1, String arg2) {
    }

    private native void nativeApplyTextSuggestion(long arg1, int arg2, int arg3) {
    }

    private native void nativeDeleteActiveSuggestionRange(long arg1) {
    }

    private native long nativeInit(WebContents arg1) {
    }

    private native void nativeOnNewWordAddedToDictionary(long arg1, String arg2) {
    }

    private native void nativeOnSuggestionMenuClosed(long arg1) {
    }

    public void onAttachedToWindow() {
        this.mIsAttachedToWindow = true;
    }

    public void onDetachedFromWindow() {
        this.mIsAttachedToWindow = false;
    }

    public void onNewWordAddedToDictionary(String arg3) {
        this.nativeOnNewWordAddedToDictionary(this.mNativeTextSuggestionHost, arg3);
    }

    public void onSuggestionMenuClosed(boolean arg3) {
        if(!arg3) {
            this.nativeOnSuggestionMenuClosed(this.mNativeTextSuggestionHost);
        }

        this.mSpellCheckPopupWindow = null;
        this.mTextSuggestionsPopupWindow = null;
    }

    public void onWindowAndroidChanged(WindowAndroid arg2) {
        this.mWindowAndroid = arg2;
        if(this.mSpellCheckPopupWindow != null) {
            this.mSpellCheckPopupWindow.updateWindowAndroid(this.mWindowAndroid);
        }

        if(this.mTextSuggestionsPopupWindow != null) {
            this.mTextSuggestionsPopupWindow.updateWindowAndroid(this.mWindowAndroid);
        }
    }

    public void onWindowFocusChanged(boolean arg1) {
        WindowEventObserver$$CC.onWindowFocusChanged(((WindowEventObserver)this), arg1);
    }

    public void setContainerView(View arg1) {
        this.mContainerView = arg1;
    }

    @CalledByNative private void showSpellCheckSuggestionMenu(double arg13, double arg15, String arg17, String[] arg18) {
        TextSuggestionHost v0 = this;
        if(!v0.mIsAttachedToWindow) {
            v0.onSuggestionMenuClosed(false);
            return;
        }

        v0.hidePopups();
        v0.mSpellCheckPopupWindow = new SpellCheckPopupWindow(v0.mContext, v0, v0.mWindowAndroid, v0.mContainerView);
        v0.mSpellCheckPopupWindow.show(arg13, arg15 + (((double)v0.getContentOffsetYPix())), arg17, arg18);
    }

    @CalledByNative private void showTextSuggestionMenu(double arg13, double arg15, String arg17, SuggestionInfo[] arg18) {
        TextSuggestionHost v0 = this;
        if(!v0.mIsAttachedToWindow) {
            v0.onSuggestionMenuClosed(false);
            return;
        }

        v0.hidePopups();
        v0.mTextSuggestionsPopupWindow = new TextSuggestionsPopupWindow(v0.mContext, v0, v0.mWindowAndroid, v0.mContainerView);
        v0.mTextSuggestionsPopupWindow.show(arg13, arg15 + (((double)v0.getContentOffsetYPix())), arg17, arg18);
    }
}

