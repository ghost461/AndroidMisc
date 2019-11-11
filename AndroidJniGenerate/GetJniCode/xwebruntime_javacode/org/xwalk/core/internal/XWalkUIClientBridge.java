package org.xwalk.core.internal;

import android.graphics.Bitmap;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.ValueCallback;
import java.util.Map;

public class XWalkUIClientBridge extends XWalkUIClientInternal {
    private ReflectMethod OnGetSampleStringXWalkViewInternalMapCallbackMethod;
    private XWalkCoreBridge coreBridge;
    private ReflectMethod enumConsoleMessageTypeClassValueOfMethod;
    private ReflectMethod enumInitiateByClassValueOfMethod;
    private ReflectMethod enumJavascriptMessageTypeClassValueOfMethod;
    private ReflectMethod enumLoadStatusClassValueOfMethod;
    private ReflectMethod getVideoLoadingProgressViewMethod;
    private ReflectMethod isSearchableMethod;
    private ReflectMethod onClearCurrentPageXWalkViewInternalStringMessageMethod;
    private ReflectMethod onConsoleMessageXWalkViewInternalStringintStringConsoleMessageTypeMethod;
    private ReflectMethod onCreateWindowRequestedXWalkViewInternalInitiateByInternalValueCallbackMethod;
    private ReflectMethod onDidChangeThemeColorXWalkViewInternalStringMessageMethod;
    private ReflectMethod onFullscreenToggledXWalkViewInternalbooleanMethod;
    private ReflectMethod onGeolocationPermissionsHidePromptMethod;
    private ReflectMethod onGeolocationPermissionsShowPromptStringXWalkGeolocationPermissionsCallbackInternalMethod;
    private ReflectMethod onGetTranslateStringXWalkViewInternalMapCallbackMethod;
    private ReflectMethod onHideCustomViewMethod;
    private ReflectMethod onIconAvailableXWalkViewInternalStringMessageMethod;
    private ReflectMethod onJavascriptCloseWindowXWalkViewInternalMethod;
    private ReflectMethod onJavascriptModalDialogXWalkViewInternalJavascriptMessageTypeInternalStringStringStringXWalkJavascriptResultInternalMethod;
    private ReflectMethod onJsAlertXWalkViewInternalStringStringXWalkJavascriptResultInternalMethod;
    private ReflectMethod onJsConfirmXWalkViewInternalStringStringXWalkJavascriptResultInternalMethod;
    private ReflectMethod onJsPromptXWalkViewInternalStringStringStringXWalkJavascriptResultInternalMethod;
    private ReflectMethod onPageCommitVisibleXWalkViewInternalStringMethod;
    private ReflectMethod onPageLoadStartedXWalkViewInternalStringMethod;
    private ReflectMethod onPageLoadStoppedXWalkViewInternalStringLoadStatusInternalMethod;
    private ReflectMethod onReceivedIconXWalkViewInternalStringBitmapMethod;
    private ReflectMethod onReceivedTitleXWalkViewInternalStringMethod;
    private ReflectMethod onRequestFocusXWalkViewInternalMethod;
    private ReflectMethod onScaleChangedXWalkViewInternalfloatfloatMethod;
    private ReflectMethod onSearchWordMethod;
    private ReflectMethod onSelectInfoChangedMethod;
    private ReflectMethod onShowCustomViewViewCustomViewCallbackInternalMethod;
    private ReflectMethod onShowCustomViewViewintCustomViewCallbackInternalMethod;
    private ReflectMethod onShowSosMethod;
    private ReflectMethod onUnhandledKeyEventXWalkViewInternalKeyEventMethod;
    private ReflectMethod openFileChooserXWalkViewInternalValueCallbackStringStringMethod;
    private ReflectMethod shouldDiscardCurrentPageXWalkViewInternalStringMessageMethod;
    private ReflectMethod shouldOverrideKeyEventXWalkViewInternalKeyEventMethod;
    private Object wrapper;

    public XWalkUIClientBridge(XWalkViewBridge arg5, Object arg6) {
        super(((XWalkViewInternal)arg5));
        this.enumJavascriptMessageTypeClassValueOfMethod = new ReflectMethod();
        this.enumConsoleMessageTypeClassValueOfMethod = new ReflectMethod();
        this.enumInitiateByClassValueOfMethod = new ReflectMethod();
        this.enumLoadStatusClassValueOfMethod = new ReflectMethod();
        this.onCreateWindowRequestedXWalkViewInternalInitiateByInternalValueCallbackMethod = new ReflectMethod(null, "onCreateWindowRequested", new Class[0]);
        this.onDidChangeThemeColorXWalkViewInternalStringMessageMethod = new ReflectMethod(null, "onDidChangeThemeColor", new Class[0]);
        this.shouldDiscardCurrentPageXWalkViewInternalStringMessageMethod = new ReflectMethod(null, "shouldDiscardCurrentPage", new Class[0]);
        this.onClearCurrentPageXWalkViewInternalStringMessageMethod = new ReflectMethod(null, "onClearCurrentPage", new Class[0]);
        this.onIconAvailableXWalkViewInternalStringMessageMethod = new ReflectMethod(null, "onIconAvailable", new Class[0]);
        this.onReceivedIconXWalkViewInternalStringBitmapMethod = new ReflectMethod(null, "onReceivedIcon", new Class[0]);
        this.onRequestFocusXWalkViewInternalMethod = new ReflectMethod(null, "onRequestFocus", new Class[0]);
        this.onJavascriptCloseWindowXWalkViewInternalMethod = new ReflectMethod(null, "onJavascriptCloseWindow", new Class[0]);
        this.onJavascriptModalDialogXWalkViewInternalJavascriptMessageTypeInternalStringStringStringXWalkJavascriptResultInternalMethod = new ReflectMethod(null, "onJavascriptModalDialog", new Class[0]);
        this.onFullscreenToggledXWalkViewInternalbooleanMethod = new ReflectMethod(null, "onFullscreenToggled", new Class[0]);
        this.openFileChooserXWalkViewInternalValueCallbackStringStringMethod = new ReflectMethod(null, "openFileChooser", new Class[0]);
        this.onScaleChangedXWalkViewInternalfloatfloatMethod = new ReflectMethod(null, "onScaleChanged", new Class[0]);
        this.shouldOverrideKeyEventXWalkViewInternalKeyEventMethod = new ReflectMethod(null, "shouldOverrideKeyEvent", new Class[0]);
        this.onUnhandledKeyEventXWalkViewInternalKeyEventMethod = new ReflectMethod(null, "onUnhandledKeyEvent", new Class[0]);
        this.onConsoleMessageXWalkViewInternalStringintStringConsoleMessageTypeMethod = new ReflectMethod(null, "onConsoleMessage", new Class[0]);
        this.onReceivedTitleXWalkViewInternalStringMethod = new ReflectMethod(null, "onReceivedTitle", new Class[0]);
        this.onPageLoadStartedXWalkViewInternalStringMethod = new ReflectMethod(null, "onPageLoadStarted", new Class[0]);
        this.onPageLoadStoppedXWalkViewInternalStringLoadStatusInternalMethod = new ReflectMethod(null, "onPageLoadStopped", new Class[0]);
        this.onPageCommitVisibleXWalkViewInternalStringMethod = new ReflectMethod(null, "onPageCommitVisible", new Class[0]);
        this.onJsAlertXWalkViewInternalStringStringXWalkJavascriptResultInternalMethod = new ReflectMethod(null, "onJsAlert", new Class[0]);
        this.onJsConfirmXWalkViewInternalStringStringXWalkJavascriptResultInternalMethod = new ReflectMethod(null, "onJsConfirm", new Class[0]);
        this.onJsPromptXWalkViewInternalStringStringStringXWalkJavascriptResultInternalMethod = new ReflectMethod(null, "onJsPrompt", new Class[0]);
        this.onShowCustomViewViewCustomViewCallbackInternalMethod = new ReflectMethod(null, "onShowCustomView", new Class[0]);
        this.onShowCustomViewViewintCustomViewCallbackInternalMethod = new ReflectMethod(null, "onShowCustomView", new Class[0]);
        this.onHideCustomViewMethod = new ReflectMethod(null, "onHideCustomView", new Class[0]);
        this.onGeolocationPermissionsShowPromptStringXWalkGeolocationPermissionsCallbackInternalMethod = new ReflectMethod(null, "onGeolocationPermissionsShowPrompt", new Class[0]);
        this.onGeolocationPermissionsHidePromptMethod = new ReflectMethod(null, "onGeolocationPermissionsHidePrompt", new Class[0]);
        this.getVideoLoadingProgressViewMethod = new ReflectMethod(null, "getVideoLoadingProgressView", new Class[0]);
        this.onSelectInfoChangedMethod = new ReflectMethod(null, "onSelectInfoChanged", new Class[0]);
        this.isSearchableMethod = new ReflectMethod(null, "isSearchable", new Class[0]);
        this.onShowSosMethod = new ReflectMethod(null, "onShowSos", new Class[0]);
        this.onSearchWordMethod = new ReflectMethod(null, "onSearchWord", new Class[0]);
        this.onGetTranslateStringXWalkViewInternalMapCallbackMethod = new ReflectMethod(null, "onGetTranslateString", new Class[0]);
        this.OnGetSampleStringXWalkViewInternalMapCallbackMethod = new ReflectMethod(null, "OnGetSampleString", new Class[0]);
        this.wrapper = arg6;
        this.reflectionInit();
    }

    private Object ConvertConsoleMessageType(ConsoleMessageType arg4) {
        return this.enumConsoleMessageTypeClassValueOfMethod.invoke(new Object[]{arg4.toString()});
    }

    private Object ConvertInitiateByInternal(InitiateByInternal arg4) {
        return this.enumInitiateByClassValueOfMethod.invoke(new Object[]{arg4.toString()});
    }

    private Object ConvertJavascriptMessageTypeInternal(JavascriptMessageTypeInternal arg4) {
        return this.enumJavascriptMessageTypeClassValueOfMethod.invoke(new Object[]{arg4.toString()});
    }

    private Object ConvertLoadStatusInternal(LoadStatusInternal arg4) {
        return this.enumLoadStatusClassValueOfMethod.invoke(new Object[]{arg4.toString()});
    }

    public boolean OnGetSampleString(XWalkViewInternal arg5, Map arg6) {
        XWalkViewBridge v5;
        if(this.OnGetSampleStringXWalkViewInternalMapCallbackMethod != null) {
            if(this.OnGetSampleStringXWalkViewInternalMapCallbackMethod.isNull()) {
            }
            else {
                ReflectMethod v0 = this.OnGetSampleStringXWalkViewInternalMapCallbackMethod;
                Object[] v1 = new Object[2];
                if((arg5 instanceof XWalkViewBridge)) {
                }
                else {
                    v5 = null;
                }

                v1[0] = v5.getWrapper();
                v1[1] = arg6;
                return v0.invoke(v1).booleanValue();
            }
        }

        return this.OnGetSampleStringSuper(arg5, arg6);
    }

    public boolean OnGetSampleStringSuper(XWalkViewInternal arg1, Map arg2) {
        return super.OnGetSampleString(arg1, arg2);
    }

    public boolean OnGetTranslateString(XWalkViewInternal arg5, Map arg6) {
        XWalkViewBridge v5;
        if(this.onGetTranslateStringXWalkViewInternalMapCallbackMethod != null) {
            if(this.onGetTranslateStringXWalkViewInternalMapCallbackMethod.isNull()) {
            }
            else {
                ReflectMethod v0 = this.onGetTranslateStringXWalkViewInternalMapCallbackMethod;
                Object[] v1 = new Object[2];
                if((arg5 instanceof XWalkViewBridge)) {
                }
                else {
                    v5 = null;
                }

                v1[0] = v5.getWrapper();
                v1[1] = arg6;
                return v0.invoke(v1).booleanValue();
            }
        }

        return this.OnGetTranslateStringSuper(arg5, arg6);
    }

    public boolean OnGetTranslateStringSuper(XWalkViewInternal arg1, Map arg2) {
        return super.OnGetTranslateString(arg1, arg2);
    }

    static XWalkCoreBridge access$000(XWalkUIClientBridge arg0) {
        return arg0.coreBridge;
    }

    public View getVideoLoadingProgressView() {
        if(this.getVideoLoadingProgressViewMethod != null) {
            if(this.getVideoLoadingProgressViewMethod.isNull()) {
            }
            else {
                return this.getVideoLoadingProgressViewMethod.invoke(new Object[0]);
            }
        }

        return this.getVideoLoadingProgressViewSuper();
    }

    public View getVideoLoadingProgressViewSuper() {
        return super.getVideoLoadingProgressView();
    }

    public Object getWrapper() {
        return this.wrapper;
    }

    public boolean isSeachableSuper() {
        return super.isSearchable();
    }

    public boolean isSearchable() {
        if(this.isSearchableMethod != null) {
            if(this.isSearchableMethod.isNull()) {
            }
            else {
                return this.isSearchableMethod.invoke(new Object[0]).booleanValue();
            }
        }

        return this.isSeachableSuper();
    }

    public void onClearCurrentPage() {
        if(this.onClearCurrentPageXWalkViewInternalStringMessageMethod != null) {
            if(this.onClearCurrentPageXWalkViewInternalStringMessageMethod.isNull()) {
            }
            else {
                this.onClearCurrentPageXWalkViewInternalStringMessageMethod.invoke(new Object[0]);
                return;
            }
        }
    }

    public boolean onConsoleMessage(XWalkViewBridge arg5, String arg6, int arg7, String arg8, ConsoleMessageType arg9) {
        if(this.onConsoleMessageXWalkViewInternalStringintStringConsoleMessageTypeMethod != null) {
            if(this.onConsoleMessageXWalkViewInternalStringintStringConsoleMessageTypeMethod.isNull()) {
            }
            else {
                ReflectMethod v0 = this.onConsoleMessageXWalkViewInternalStringintStringConsoleMessageTypeMethod;
                Object[] v1 = new Object[5];
                if((arg5 instanceof XWalkViewBridge)) {
                }
                else {
                    arg5 = null;
                }

                v1[0] = arg5.getWrapper();
                v1[1] = arg6;
                v1[2] = Integer.valueOf(arg7);
                v1[3] = arg8;
                v1[4] = this.ConvertConsoleMessageType(arg9);
                return v0.invoke(v1).booleanValue();
            }
        }

        return this.onConsoleMessageSuper(arg5, arg6, arg7, arg8, arg9);
    }

    public boolean onConsoleMessage(XWalkViewInternal arg8, String arg9, int arg10, String arg11, ConsoleMessageType arg12) {
        if((arg8 instanceof XWalkViewBridge)) {
            return this.onConsoleMessage(arg8, arg9, arg10, arg11, arg12);
        }

        return super.onConsoleMessage(arg8, arg9, arg10, arg11, arg12);
    }

    public boolean onConsoleMessageSuper(XWalkViewBridge arg1, String arg2, int arg3, String arg4, ConsoleMessageType arg5) {
        return super.onConsoleMessage(((XWalkViewInternal)arg1), arg2, arg3, arg4, arg5);
    }

    public boolean onCreateWindowRequested(XWalkViewBridge arg5, InitiateByInternal arg6, ValueCallback arg7) {
        if(this.onCreateWindowRequestedXWalkViewInternalInitiateByInternalValueCallbackMethod != null) {
            if(this.onCreateWindowRequestedXWalkViewInternalInitiateByInternalValueCallbackMethod.isNull()) {
            }
            else {
                ReflectMethod v0 = this.onCreateWindowRequestedXWalkViewInternalInitiateByInternalValueCallbackMethod;
                Object[] v1 = new Object[3];
                if((arg5 instanceof XWalkViewBridge)) {
                }
                else {
                    arg5 = null;
                }

                v1[0] = arg5.getWrapper();
                v1[1] = this.ConvertInitiateByInternal(arg6);
                v1[2] = new ValueCallback(arg7) {
                    public void onReceiveValue(Object arg3) {
                        this.val$callbackFinal.onReceiveValue(XWalkUIClientBridge.this.coreBridge.getBridgeObject(arg3));
                    }
                };
                return v0.invoke(v1).booleanValue();
            }
        }

        return this.onCreateWindowRequestedSuper(arg5, arg6, arg7);
    }

    public boolean onCreateWindowRequested(XWalkViewInternal arg2, InitiateByInternal arg3, ValueCallback arg4) {
        if((arg2 instanceof XWalkViewBridge)) {
            return this.onCreateWindowRequested(((XWalkViewBridge)arg2), arg3, arg4);
        }

        return super.onCreateWindowRequested(arg2, arg3, arg4);
    }

    public boolean onCreateWindowRequestedSuper(XWalkViewBridge arg1, InitiateByInternal arg2, ValueCallback arg3) {
        return super.onCreateWindowRequested(((XWalkViewInternal)arg1), arg2, arg3);
    }

    public void onDidChangeThemeColor(XWalkViewBridge arg5, int arg6) {
        if(this.onDidChangeThemeColorXWalkViewInternalStringMessageMethod == null || (this.onDidChangeThemeColorXWalkViewInternalStringMessageMethod.isNull())) {
            this.onDidChangeThemeColorSuper(arg5, arg6);
        }
        else {
            ReflectMethod v0 = this.onDidChangeThemeColorXWalkViewInternalStringMessageMethod;
            Object[] v1 = new Object[2];
            if((arg5 instanceof XWalkViewBridge)) {
            }
            else {
                arg5 = null;
            }

            v1[0] = arg5.getWrapper();
            v1[1] = Integer.valueOf(arg6);
            v0.invoke(v1);
        }
    }

    public void onDidChangeThemeColor(XWalkViewInternal arg2, int arg3) {
        if((arg2 instanceof XWalkViewBridge)) {
            this.onDidChangeThemeColor(((XWalkViewBridge)arg2), arg3);
        }
        else {
            super.onDidChangeThemeColor(arg2, arg3);
        }
    }

    public void onDidChangeThemeColorSuper(XWalkViewBridge arg1, int arg2) {
        super.onDidChangeThemeColor(((XWalkViewInternal)arg1), arg2);
    }

    public void onFullscreenToggled(XWalkViewBridge arg5, boolean arg6) {
        if(this.onFullscreenToggledXWalkViewInternalbooleanMethod == null || (this.onFullscreenToggledXWalkViewInternalbooleanMethod.isNull())) {
            this.onFullscreenToggledSuper(arg5, arg6);
        }
        else {
            ReflectMethod v0 = this.onFullscreenToggledXWalkViewInternalbooleanMethod;
            Object[] v1 = new Object[2];
            if((arg5 instanceof XWalkViewBridge)) {
            }
            else {
                arg5 = null;
            }

            v1[0] = arg5.getWrapper();
            v1[1] = Boolean.valueOf(arg6);
            v0.invoke(v1);
        }
    }

    public void onFullscreenToggled(XWalkViewInternal arg2, boolean arg3) {
        if((arg2 instanceof XWalkViewBridge)) {
            this.onFullscreenToggled(((XWalkViewBridge)arg2), arg3);
        }
        else {
            super.onFullscreenToggled(arg2, arg3);
        }
    }

    public void onFullscreenToggledSuper(XWalkViewBridge arg1, boolean arg2) {
        super.onFullscreenToggled(((XWalkViewInternal)arg1), arg2);
    }

    public void onGeolocationPermissionsHidePrompt() {
        if(this.onGeolocationPermissionsHidePromptMethod == null || (this.onGeolocationPermissionsHidePromptMethod.isNull())) {
            this.onGeolocationPermissionsHidePromptSuper();
        }
        else {
            this.onGeolocationPermissionsHidePromptMethod.invoke(new Object[0]);
        }
    }

    public void onGeolocationPermissionsHidePromptSuper() {
        super.onGeolocationPermissionsHidePrompt();
    }

    public void onGeolocationPermissionsShowPrompt(String arg4, XWalkGeolocationPermissionsCallbackHandlerBridge arg5) {
        if(this.onGeolocationPermissionsShowPromptStringXWalkGeolocationPermissionsCallbackInternalMethod == null || (this.onGeolocationPermissionsShowPromptStringXWalkGeolocationPermissionsCallbackInternalMethod.isNull())) {
            this.onGeolocationPermissionsShowPromptSuper(arg4, arg5);
        }
        else {
            ReflectMethod v0 = this.onGeolocationPermissionsShowPromptStringXWalkGeolocationPermissionsCallbackInternalMethod;
            Object[] v1 = new Object[2];
            v1[0] = arg4;
            if((arg5 instanceof XWalkGeolocationPermissionsCallbackHandlerBridge)) {
            }
            else {
                arg5 = null;
            }

            v1[1] = arg5.getWrapper();
            v0.invoke(v1);
        }
    }

    public void onGeolocationPermissionsShowPrompt(String arg2, XWalkGeolocationPermissionsCallbackInternal arg3) {
        XWalkGeolocationPermissionsCallbackHandlerBridge v3;
        if((arg3 instanceof XWalkGeolocationPermissionsCallbackHandlerBridge)) {
        }
        else {
            v3 = new XWalkGeolocationPermissionsCallbackHandlerBridge(((XWalkGeolocationPermissionsCallbackHandlerInternal)arg3));
        }

        this.onGeolocationPermissionsShowPrompt(arg2, v3);
    }

    public void onGeolocationPermissionsShowPromptSuper(String arg1, XWalkGeolocationPermissionsCallbackHandlerBridge arg2) {
        super.onGeolocationPermissionsShowPrompt(arg1, ((XWalkGeolocationPermissionsCallbackInternal)arg2));
    }

    public void onHideCustomView() {
        if(this.onHideCustomViewMethod == null || (this.onHideCustomViewMethod.isNull())) {
            this.onHideCustomViewSuper();
        }
        else {
            this.onHideCustomViewMethod.invoke(new Object[0]);
        }
    }

    public void onHideCustomViewSuper() {
        super.onHideCustomView();
    }

    public void onIconAvailable(XWalkViewBridge arg5, String arg6, Message arg7) {
        if(this.onIconAvailableXWalkViewInternalStringMessageMethod == null || (this.onIconAvailableXWalkViewInternalStringMessageMethod.isNull())) {
            this.onIconAvailableSuper(arg5, arg6, arg7);
        }
        else {
            ReflectMethod v0 = this.onIconAvailableXWalkViewInternalStringMessageMethod;
            Object[] v1 = new Object[3];
            if((arg5 instanceof XWalkViewBridge)) {
            }
            else {
                arg5 = null;
            }

            v1[0] = arg5.getWrapper();
            v1[1] = arg6;
            v1[2] = arg7;
            v0.invoke(v1);
        }
    }

    public void onIconAvailable(XWalkViewInternal arg2, String arg3, Message arg4) {
        if((arg2 instanceof XWalkViewBridge)) {
            this.onIconAvailable(((XWalkViewBridge)arg2), arg3, arg4);
        }
        else {
            super.onIconAvailable(arg2, arg3, arg4);
        }
    }

    public void onIconAvailableSuper(XWalkViewBridge arg1, String arg2, Message arg3) {
        super.onIconAvailable(((XWalkViewInternal)arg1), arg2, arg3);
    }

    public void onJavascriptCloseWindow(XWalkViewBridge arg5) {
        if(this.onJavascriptCloseWindowXWalkViewInternalMethod == null || (this.onJavascriptCloseWindowXWalkViewInternalMethod.isNull())) {
            this.onJavascriptCloseWindowSuper(arg5);
        }
        else {
            ReflectMethod v0 = this.onJavascriptCloseWindowXWalkViewInternalMethod;
            Object[] v1 = new Object[1];
            if((arg5 instanceof XWalkViewBridge)) {
            }
            else {
                arg5 = null;
            }

            v1[0] = arg5.getWrapper();
            v0.invoke(v1);
        }
    }

    public void onJavascriptCloseWindow(XWalkViewInternal arg2) {
        if((arg2 instanceof XWalkViewBridge)) {
            this.onJavascriptCloseWindow(((XWalkViewBridge)arg2));
        }
        else {
            super.onJavascriptCloseWindow(arg2);
        }
    }

    public void onJavascriptCloseWindowSuper(XWalkViewBridge arg1) {
        super.onJavascriptCloseWindow(((XWalkViewInternal)arg1));
    }

    public boolean onJavascriptModalDialog(XWalkViewBridge arg5, JavascriptMessageTypeInternal arg6, String arg7, String arg8, String arg9, XWalkJavascriptResultHandlerBridge arg10) {
        if(this.onJavascriptModalDialogXWalkViewInternalJavascriptMessageTypeInternalStringStringStringXWalkJavascriptResultInternalMethod != null) {
            if(this.onJavascriptModalDialogXWalkViewInternalJavascriptMessageTypeInternalStringStringStringXWalkJavascriptResultInternalMethod.isNull()) {
            }
            else {
                ReflectMethod v0 = this.onJavascriptModalDialogXWalkViewInternalJavascriptMessageTypeInternalStringStringStringXWalkJavascriptResultInternalMethod;
                Object[] v1 = new Object[6];
                if((arg5 instanceof XWalkViewBridge)) {
                }
                else {
                    arg5 = null;
                }

                v1[0] = arg5.getWrapper();
                v1[1] = this.ConvertJavascriptMessageTypeInternal(arg6);
                v1[2] = arg7;
                v1[3] = arg8;
                v1[4] = arg9;
                int v5 = 5;
                XWalkJavascriptResultHandlerBridge v6 = (arg10 instanceof XWalkJavascriptResultHandlerBridge) ? arg10 : new XWalkJavascriptResultHandlerBridge(((XWalkJavascriptResultHandlerInternal)arg10));
                v1[v5] = v6.getWrapper();
                return v0.invoke(v1).booleanValue();
            }
        }

        return this.onJavascriptModalDialogSuper(arg5, arg6, arg7, arg8, arg9, arg10);
    }

    public boolean onJavascriptModalDialog(XWalkViewInternal arg9, JavascriptMessageTypeInternal arg10, String arg11, String arg12, String arg13, XWalkJavascriptResultInternal arg14) {
        XWalkJavascriptResultInternal v7;
        if((arg9 instanceof XWalkViewBridge)) {
            XWalkViewInternal v2 = arg9;
            if((arg14 instanceof XWalkJavascriptResultHandlerBridge)) {
                v7 = arg14;
            }
            else {
                XWalkJavascriptResultHandlerBridge v7_1 = new XWalkJavascriptResultHandlerBridge(((XWalkJavascriptResultHandlerInternal)arg14));
            }

            return this.onJavascriptModalDialog(((XWalkViewBridge)v2), arg10, arg11, arg12, arg13, ((XWalkJavascriptResultHandlerBridge)v7));
        }

        return super.onJavascriptModalDialog(arg9, arg10, arg11, arg12, arg13, arg14);
    }

    public boolean onJavascriptModalDialogSuper(XWalkViewBridge arg1, JavascriptMessageTypeInternal arg2, String arg3, String arg4, String arg5, XWalkJavascriptResultHandlerBridge arg6) {
        return super.onJavascriptModalDialog(((XWalkViewInternal)arg1), arg2, arg3, arg4, arg5, ((XWalkJavascriptResultInternal)arg6));
    }

    public boolean onJsAlert(XWalkViewBridge arg5, String arg6, String arg7, XWalkJavascriptResultHandlerBridge arg8) {
        if(this.onJsAlertXWalkViewInternalStringStringXWalkJavascriptResultInternalMethod != null) {
            if(this.onJsAlertXWalkViewInternalStringStringXWalkJavascriptResultInternalMethod.isNull()) {
            }
            else {
                ReflectMethod v0 = this.onJsAlertXWalkViewInternalStringStringXWalkJavascriptResultInternalMethod;
                Object[] v1 = new Object[4];
                if((arg5 instanceof XWalkViewBridge)) {
                }
                else {
                    arg5 = null;
                }

                v1[0] = arg5.getWrapper();
                v1[1] = arg6;
                v1[2] = arg7;
                int v5 = 3;
                XWalkJavascriptResultHandlerBridge v6 = (arg8 instanceof XWalkJavascriptResultHandlerBridge) ? arg8 : new XWalkJavascriptResultHandlerBridge(((XWalkJavascriptResultHandlerInternal)arg8));
                v1[v5] = v6.getWrapper();
                return v0.invoke(v1).booleanValue();
            }
        }

        return this.onJsAlertSuper(arg5, arg6, arg7, arg8);
    }

    public boolean onJsAlert(XWalkViewInternal arg4, String arg5, String arg6, XWalkJavascriptResultInternal arg7) {
        XWalkJavascriptResultHandlerBridge v1_1;
        if((arg4 instanceof XWalkViewBridge)) {
            XWalkViewInternal v0 = arg4;
            if((arg7 instanceof XWalkJavascriptResultHandlerBridge)) {
                XWalkJavascriptResultInternal v1 = arg7;
            }
            else {
                v1_1 = new XWalkJavascriptResultHandlerBridge(arg7);
            }

            boolean v0_1 = this.onJsAlert(((XWalkViewBridge)v0), arg5, arg6, v1_1);
            if(!v0_1) {
                return super.onJsAlert(arg4, arg5, arg6, arg7);
            }

            return v0_1;
        }

        return super.onJsAlert(arg4, arg5, arg6, arg7);
    }

    public boolean onJsAlertSuper(XWalkViewBridge arg1, String arg2, String arg3, XWalkJavascriptResultHandlerBridge arg4) {
        return super.onJsAlert(((XWalkViewInternal)arg1), arg2, arg3, ((XWalkJavascriptResultInternal)arg4));
    }

    public boolean onJsConfirm(XWalkViewBridge arg5, String arg6, String arg7, XWalkJavascriptResultHandlerBridge arg8) {
        if(this.onJsConfirmXWalkViewInternalStringStringXWalkJavascriptResultInternalMethod != null) {
            if(this.onJsConfirmXWalkViewInternalStringStringXWalkJavascriptResultInternalMethod.isNull()) {
            }
            else {
                ReflectMethod v0 = this.onJsConfirmXWalkViewInternalStringStringXWalkJavascriptResultInternalMethod;
                Object[] v1 = new Object[4];
                if((arg5 instanceof XWalkViewBridge)) {
                }
                else {
                    arg5 = null;
                }

                v1[0] = arg5.getWrapper();
                v1[1] = arg6;
                v1[2] = arg7;
                int v5 = 3;
                XWalkJavascriptResultHandlerBridge v6 = (arg8 instanceof XWalkJavascriptResultHandlerBridge) ? arg8 : new XWalkJavascriptResultHandlerBridge(((XWalkJavascriptResultHandlerInternal)arg8));
                v1[v5] = v6.getWrapper();
                return v0.invoke(v1).booleanValue();
            }
        }

        return this.onJsConfirmSuper(arg5, arg6, arg7, arg8);
    }

    public boolean onJsConfirm(XWalkViewInternal arg4, String arg5, String arg6, XWalkJavascriptResultInternal arg7) {
        XWalkJavascriptResultHandlerBridge v1_1;
        if((arg4 instanceof XWalkViewBridge)) {
            XWalkViewInternal v0 = arg4;
            if((arg7 instanceof XWalkJavascriptResultHandlerBridge)) {
                XWalkJavascriptResultInternal v1 = arg7;
            }
            else {
                v1_1 = new XWalkJavascriptResultHandlerBridge(arg7);
            }

            boolean v0_1 = this.onJsConfirm(((XWalkViewBridge)v0), arg5, arg6, v1_1);
            if(!v0_1) {
                return super.onJsConfirm(arg4, arg5, arg6, arg7);
            }

            return v0_1;
        }

        return super.onJsConfirm(arg4, arg5, arg6, arg7);
    }

    public boolean onJsConfirmSuper(XWalkViewBridge arg1, String arg2, String arg3, XWalkJavascriptResultHandlerBridge arg4) {
        return super.onJsConfirm(((XWalkViewInternal)arg1), arg2, arg3, ((XWalkJavascriptResultInternal)arg4));
    }

    public boolean onJsPrompt(XWalkViewBridge arg5, String arg6, String arg7, String arg8, XWalkJavascriptResultHandlerBridge arg9) {
        if(this.onJsPromptXWalkViewInternalStringStringStringXWalkJavascriptResultInternalMethod != null) {
            if(this.onJsPromptXWalkViewInternalStringStringStringXWalkJavascriptResultInternalMethod.isNull()) {
            }
            else {
                ReflectMethod v0 = this.onJsPromptXWalkViewInternalStringStringStringXWalkJavascriptResultInternalMethod;
                Object[] v1 = new Object[5];
                if((arg5 instanceof XWalkViewBridge)) {
                }
                else {
                    arg5 = null;
                }

                v1[0] = arg5.getWrapper();
                v1[1] = arg6;
                v1[2] = arg7;
                v1[3] = arg8;
                int v5 = 4;
                XWalkJavascriptResultHandlerBridge v6 = (arg9 instanceof XWalkJavascriptResultHandlerBridge) ? arg9 : new XWalkJavascriptResultHandlerBridge(((XWalkJavascriptResultHandlerInternal)arg9));
                v1[v5] = v6.getWrapper();
                return v0.invoke(v1).booleanValue();
            }
        }

        return this.onJsPromptSuper(arg5, arg6, arg7, arg8, arg9);
    }

    public boolean onJsPrompt(XWalkViewInternal arg8, String arg9, String arg10, String arg11, XWalkJavascriptResultInternal arg12) {
        XWalkJavascriptResultHandlerBridge v0_1;
        if((arg8 instanceof XWalkViewBridge)) {
            XWalkViewInternal v2 = arg8;
            if((arg12 instanceof XWalkJavascriptResultHandlerBridge)) {
                XWalkJavascriptResultInternal v0 = arg12;
            }
            else {
                v0_1 = new XWalkJavascriptResultHandlerBridge(arg12);
            }

            XWalkJavascriptResultInternal v6 = ((XWalkJavascriptResultInternal)v0_1);
            boolean v0_2 = this.onJsPrompt(((XWalkViewBridge)v2), arg9, arg10, arg11, ((XWalkJavascriptResultHandlerBridge)v6));
            if(!v0_2) {
                return super.onJsPrompt(arg8, arg9, arg10, arg11, arg12);
            }

            return v0_2;
        }

        return super.onJsPrompt(arg8, arg9, arg10, arg11, arg12);
    }

    public boolean onJsPromptSuper(XWalkViewBridge arg1, String arg2, String arg3, String arg4, XWalkJavascriptResultHandlerBridge arg5) {
        return super.onJsPrompt(((XWalkViewInternal)arg1), arg2, arg3, arg4, ((XWalkJavascriptResultInternal)arg5));
    }

    public void onPageCommitVisible(XWalkViewBridge arg5, String arg6) {
        if(this.onPageCommitVisibleXWalkViewInternalStringMethod == null || (this.onPageCommitVisibleXWalkViewInternalStringMethod.isNull())) {
            this.onPageCommitVisibleSuper(arg5, arg6);
        }
        else {
            ReflectMethod v0 = this.onPageCommitVisibleXWalkViewInternalStringMethod;
            Object[] v1 = new Object[2];
            if((arg5 instanceof XWalkViewBridge)) {
            }
            else {
                arg5 = null;
            }

            v1[0] = arg5.getWrapper();
            v1[1] = arg6;
            v0.invoke(v1);
        }
    }

    @XWalkAPI public void onPageCommitVisible(XWalkViewInternal arg2, String arg3) {
        if((arg2 instanceof XWalkViewBridge)) {
            this.onPageCommitVisible(((XWalkViewBridge)arg2), arg3);
        }
        else {
            super.onPageCommitVisible(arg2, arg3);
        }
    }

    public void onPageCommitVisibleSuper(XWalkViewBridge arg1, String arg2) {
        super.onPageCommitVisible(((XWalkViewInternal)arg1), arg2);
    }

    public void onPageLoadStarted(XWalkViewBridge arg5, String arg6) {
        if(this.onPageLoadStartedXWalkViewInternalStringMethod == null || (this.onPageLoadStartedXWalkViewInternalStringMethod.isNull())) {
            this.onPageLoadStartedSuper(arg5, arg6);
        }
        else {
            ReflectMethod v0 = this.onPageLoadStartedXWalkViewInternalStringMethod;
            Object[] v1 = new Object[2];
            if((arg5 instanceof XWalkViewBridge)) {
            }
            else {
                arg5 = null;
            }

            v1[0] = arg5.getWrapper();
            v1[1] = arg6;
            v0.invoke(v1);
        }
    }

    public void onPageLoadStarted(XWalkViewInternal arg2, String arg3) {
        if((arg2 instanceof XWalkViewBridge)) {
            this.onPageLoadStarted(((XWalkViewBridge)arg2), arg3);
        }
        else {
            super.onPageLoadStarted(arg2, arg3);
        }
    }

    public void onPageLoadStartedSuper(XWalkViewBridge arg1, String arg2) {
        super.onPageLoadStarted(((XWalkViewInternal)arg1), arg2);
    }

    public void onPageLoadStopped(XWalkViewBridge arg5, String arg6, LoadStatusInternal arg7) {
        if(this.onPageLoadStoppedXWalkViewInternalStringLoadStatusInternalMethod == null || (this.onPageLoadStoppedXWalkViewInternalStringLoadStatusInternalMethod.isNull())) {
            this.onPageLoadStoppedSuper(arg5, arg6, arg7);
        }
        else {
            ReflectMethod v0 = this.onPageLoadStoppedXWalkViewInternalStringLoadStatusInternalMethod;
            Object[] v1 = new Object[3];
            if((arg5 instanceof XWalkViewBridge)) {
            }
            else {
                arg5 = null;
            }

            v1[0] = arg5.getWrapper();
            v1[1] = arg6;
            v1[2] = this.ConvertLoadStatusInternal(arg7);
            v0.invoke(v1);
        }
    }

    public void onPageLoadStopped(XWalkViewInternal arg2, String arg3, LoadStatusInternal arg4) {
        if((arg2 instanceof XWalkViewBridge)) {
            this.onPageLoadStopped(((XWalkViewBridge)arg2), arg3, arg4);
        }
        else {
            super.onPageLoadStopped(arg2, arg3, arg4);
        }
    }

    public void onPageLoadStoppedSuper(XWalkViewBridge arg1, String arg2, LoadStatusInternal arg3) {
        super.onPageLoadStopped(((XWalkViewInternal)arg1), arg2, arg3);
    }

    public void onReceivedIcon(XWalkViewBridge arg5, String arg6, Bitmap arg7) {
        if(this.onReceivedIconXWalkViewInternalStringBitmapMethod == null || (this.onReceivedIconXWalkViewInternalStringBitmapMethod.isNull())) {
            this.onReceivedIconSuper(arg5, arg6, arg7);
        }
        else {
            ReflectMethod v0 = this.onReceivedIconXWalkViewInternalStringBitmapMethod;
            Object[] v1 = new Object[3];
            if((arg5 instanceof XWalkViewBridge)) {
            }
            else {
                arg5 = null;
            }

            v1[0] = arg5.getWrapper();
            v1[1] = arg6;
            v1[2] = arg7;
            v0.invoke(v1);
        }
    }

    public void onReceivedIcon(XWalkViewInternal arg2, String arg3, Bitmap arg4) {
        if((arg2 instanceof XWalkViewBridge)) {
            this.onReceivedIcon(((XWalkViewBridge)arg2), arg3, arg4);
        }
        else {
            super.onReceivedIcon(arg2, arg3, arg4);
        }
    }

    public void onReceivedIconSuper(XWalkViewBridge arg1, String arg2, Bitmap arg3) {
        super.onReceivedIcon(((XWalkViewInternal)arg1), arg2, arg3);
    }

    public void onReceivedTitle(XWalkViewBridge arg5, String arg6) {
        if(this.onReceivedTitleXWalkViewInternalStringMethod == null || (this.onReceivedTitleXWalkViewInternalStringMethod.isNull())) {
            this.onReceivedTitleSuper(arg5, arg6);
        }
        else {
            ReflectMethod v0 = this.onReceivedTitleXWalkViewInternalStringMethod;
            Object[] v1 = new Object[2];
            if((arg5 instanceof XWalkViewBridge)) {
            }
            else {
                arg5 = null;
            }

            v1[0] = arg5.getWrapper();
            v1[1] = arg6;
            v0.invoke(v1);
        }
    }

    public void onReceivedTitle(XWalkViewInternal arg2, String arg3) {
        if((arg2 instanceof XWalkViewBridge)) {
            this.onReceivedTitle(((XWalkViewBridge)arg2), arg3);
        }
        else {
            super.onReceivedTitle(arg2, arg3);
        }
    }

    public void onReceivedTitleSuper(XWalkViewBridge arg1, String arg2) {
        super.onReceivedTitle(((XWalkViewInternal)arg1), arg2);
    }

    public void onRequestFocus(XWalkViewBridge arg5) {
        if(this.onRequestFocusXWalkViewInternalMethod == null || (this.onRequestFocusXWalkViewInternalMethod.isNull())) {
            this.onRequestFocusSuper(arg5);
        }
        else {
            ReflectMethod v0 = this.onRequestFocusXWalkViewInternalMethod;
            Object[] v1 = new Object[1];
            if((arg5 instanceof XWalkViewBridge)) {
            }
            else {
                arg5 = null;
            }

            v1[0] = arg5.getWrapper();
            v0.invoke(v1);
        }
    }

    public void onRequestFocus(XWalkViewInternal arg2) {
        if((arg2 instanceof XWalkViewBridge)) {
            this.onRequestFocus(((XWalkViewBridge)arg2));
        }
        else {
            super.onRequestFocus(arg2);
        }
    }

    public void onRequestFocusSuper(XWalkViewBridge arg1) {
        super.onRequestFocus(((XWalkViewInternal)arg1));
    }

    public void onScaleChanged(XWalkViewBridge arg5, float arg6, float arg7) {
        if(this.onScaleChangedXWalkViewInternalfloatfloatMethod == null || (this.onScaleChangedXWalkViewInternalfloatfloatMethod.isNull())) {
            this.onScaleChangedSuper(arg5, arg6, arg7);
        }
        else {
            ReflectMethod v0 = this.onScaleChangedXWalkViewInternalfloatfloatMethod;
            Object[] v1 = new Object[3];
            if((arg5 instanceof XWalkViewBridge)) {
            }
            else {
                arg5 = null;
            }

            v1[0] = arg5.getWrapper();
            v1[1] = Float.valueOf(arg6);
            v1[2] = Float.valueOf(arg7);
            v0.invoke(v1);
        }
    }

    public void onScaleChanged(XWalkViewInternal arg2, float arg3, float arg4) {
        if((arg2 instanceof XWalkViewBridge)) {
            this.onScaleChanged(((XWalkViewBridge)arg2), arg3, arg4);
        }
        else {
            super.onScaleChanged(arg2, arg3, arg4);
        }
    }

    public void onScaleChangedSuper(XWalkViewBridge arg1, float arg2, float arg3) {
        super.onScaleChanged(((XWalkViewInternal)arg1), arg2, arg3);
    }

    public boolean onSearchWord(XWalkViewInternal arg5, String arg6, String arg7, String arg8) {
        XWalkViewBridge v5;
        if(this.onSearchWordMethod != null) {
            if(this.onSearchWordMethod.isNull()) {
            }
            else {
                ReflectMethod v0 = this.onSearchWordMethod;
                Object[] v1 = new Object[4];
                if((arg5 instanceof XWalkViewBridge)) {
                }
                else {
                    v5 = null;
                }

                v1[0] = v5.getWrapper();
                v1[1] = arg6;
                v1[2] = arg7;
                v1[3] = arg8;
                return v0.invoke(v1).booleanValue();
            }
        }

        return this.onSearchWordSuper(arg5, arg6, arg7, arg8);
    }

    public boolean onSearchWordSuper(XWalkViewInternal arg1, String arg2, String arg3, String arg4) {
        return super.onSearchWord(arg1, arg2, arg3, arg4);
    }

    public boolean onSelectInfoChanged(XWalkViewInternal arg5, long arg6, String arg8, String arg9, String arg10) {
        XWalkViewBridge v5;
        if(this.onSelectInfoChangedMethod != null) {
            if(this.onSelectInfoChangedMethod.isNull()) {
            }
            else {
                ReflectMethod v0 = this.onSelectInfoChangedMethod;
                Object[] v1 = new Object[5];
                if((arg5 instanceof XWalkViewBridge)) {
                }
                else {
                    v5 = null;
                }

                v1[0] = v5.getWrapper();
                v1[1] = Long.valueOf(arg6);
                v1[2] = arg8;
                v1[3] = arg9;
                v1[4] = arg10;
                return v0.invoke(v1).booleanValue();
            }
        }

        return this.onSelectInfoChangedSuper(arg5, arg6, arg8, arg9, arg10);
    }

    public boolean onSelectInfoChangedSuper(XWalkViewInternal arg1, long arg2, String arg4, String arg5, String arg6) {
        return super.onSelectInfoChanged(arg1, arg2, arg4, arg5, arg6);
    }

    public void onShowCustomView(View arg4, int arg5, CustomViewCallbackHandlerBridge arg6) {
        if(this.onShowCustomViewViewintCustomViewCallbackInternalMethod == null || (this.onShowCustomViewViewintCustomViewCallbackInternalMethod.isNull())) {
            this.onShowCustomViewSuper(arg4, arg5, arg6);
        }
        else {
            ReflectMethod v0 = this.onShowCustomViewViewintCustomViewCallbackInternalMethod;
            Object[] v1 = new Object[3];
            v1[0] = arg4;
            v1[1] = Integer.valueOf(arg5);
            int v4 = 2;
            CustomViewCallbackHandlerBridge v5 = (arg6 instanceof CustomViewCallbackHandlerBridge) ? arg6 : new CustomViewCallbackHandlerBridge(((CustomViewCallbackHandlerInternal)arg6));
            v1[v4] = v5.getWrapper();
            v0.invoke(v1);
        }
    }

    public void onShowCustomView(View arg2, int arg3, CustomViewCallbackInternal arg4) {
        CustomViewCallbackHandlerBridge v4;
        if((arg4 instanceof CustomViewCallbackHandlerBridge)) {
        }
        else {
            v4 = new CustomViewCallbackHandlerBridge(((CustomViewCallbackHandlerInternal)arg4));
        }

        this.onShowCustomView(arg2, arg3, v4);
    }

    public void onShowCustomView(View arg4, CustomViewCallbackHandlerBridge arg5) {
        if(this.onShowCustomViewViewCustomViewCallbackInternalMethod == null || (this.onShowCustomViewViewCustomViewCallbackInternalMethod.isNull())) {
            this.onShowCustomViewSuper(arg4, arg5);
        }
        else {
            ReflectMethod v0 = this.onShowCustomViewViewCustomViewCallbackInternalMethod;
            Object[] v1 = new Object[2];
            v1[0] = arg4;
            if((arg5 instanceof CustomViewCallbackHandlerBridge)) {
            }
            else {
                arg5 = new CustomViewCallbackHandlerBridge(((CustomViewCallbackHandlerInternal)arg5));
            }

            v1[1] = arg5.getWrapper();
            v0.invoke(v1);
        }
    }

    public void onShowCustomView(View arg2, CustomViewCallbackInternal arg3) {
        CustomViewCallbackHandlerBridge v3;
        if((arg3 instanceof CustomViewCallbackHandlerBridge)) {
        }
        else {
            v3 = new CustomViewCallbackHandlerBridge(((CustomViewCallbackHandlerInternal)arg3));
        }

        this.onShowCustomView(arg2, v3);
    }

    public void onShowCustomViewSuper(View arg1, int arg2, CustomViewCallbackHandlerBridge arg3) {
        super.onShowCustomView(arg1, arg2, ((CustomViewCallbackInternal)arg3));
    }

    public void onShowCustomViewSuper(View arg1, CustomViewCallbackHandlerBridge arg2) {
        super.onShowCustomView(arg1, ((CustomViewCallbackInternal)arg2));
    }

    public void onShowSos() {
        if(this.onShowSosMethod == null || (this.onShowSosMethod.isNull())) {
            this.onShowSosSuper();
        }
        else {
            this.onShowSosMethod.invoke(new Object[0]);
        }
    }

    private void onShowSosSuper() {
        super.onShowSos();
    }

    public void onUnhandledKeyEvent(XWalkViewBridge arg5, KeyEvent arg6) {
        if(this.onUnhandledKeyEventXWalkViewInternalKeyEventMethod == null || (this.onUnhandledKeyEventXWalkViewInternalKeyEventMethod.isNull())) {
            this.onUnhandledKeyEventSuper(arg5, arg6);
        }
        else {
            ReflectMethod v0 = this.onUnhandledKeyEventXWalkViewInternalKeyEventMethod;
            Object[] v1 = new Object[2];
            if((arg5 instanceof XWalkViewBridge)) {
            }
            else {
                arg5 = null;
            }

            v1[0] = arg5.getWrapper();
            v1[1] = arg6;
            v0.invoke(v1);
        }
    }

    public void onUnhandledKeyEvent(XWalkViewInternal arg2, KeyEvent arg3) {
        if((arg2 instanceof XWalkViewBridge)) {
            this.onUnhandledKeyEvent(((XWalkViewBridge)arg2), arg3);
        }
        else {
            super.onUnhandledKeyEvent(arg2, arg3);
        }
    }

    public void onUnhandledKeyEventSuper(XWalkViewBridge arg1, KeyEvent arg2) {
        super.onUnhandledKeyEvent(((XWalkViewInternal)arg1), arg2);
    }

    public void openFileChooser(XWalkViewBridge arg5, ValueCallback arg6, String arg7, String arg8) {
        if(this.openFileChooserXWalkViewInternalValueCallbackStringStringMethod == null || (this.openFileChooserXWalkViewInternalValueCallbackStringStringMethod.isNull())) {
            this.openFileChooserSuper(arg5, arg6, arg7, arg8);
        }
        else {
            ReflectMethod v0 = this.openFileChooserXWalkViewInternalValueCallbackStringStringMethod;
            Object[] v1 = new Object[4];
            if((arg5 instanceof XWalkViewBridge)) {
            }
            else {
                arg5 = null;
            }

            v1[0] = arg5.getWrapper();
            v1[1] = arg6;
            v1[2] = arg7;
            v1[3] = arg8;
            v0.invoke(v1);
        }
    }

    public void openFileChooser(XWalkViewInternal arg2, ValueCallback arg3, String arg4, String arg5) {
        if((arg2 instanceof XWalkViewBridge)) {
            this.openFileChooser(((XWalkViewBridge)arg2), arg3, arg4, arg5);
        }
        else {
            super.openFileChooser(arg2, arg3, arg4, arg5);
        }
    }

    public void openFileChooserSuper(XWalkViewBridge arg1, ValueCallback arg2, String arg3, String arg4) {
        super.openFileChooser(((XWalkViewInternal)arg1), arg2, arg3, arg4);
    }

    void reflectionInit() {
        this.coreBridge = XWalkCoreBridge.getInstance();
        if(this.coreBridge == null) {
            return;
        }

        this.enumJavascriptMessageTypeClassValueOfMethod.init(null, this.coreBridge.getWrapperClass("XWalkUIClient$JavascriptMessageType"), "valueOf", new Class[]{String.class});
        this.enumConsoleMessageTypeClassValueOfMethod.init(null, this.coreBridge.getWrapperClass("XWalkUIClient$ConsoleMessageType"), "valueOf", new Class[]{String.class});
        this.enumInitiateByClassValueOfMethod.init(null, this.coreBridge.getWrapperClass("XWalkUIClient$InitiateBy"), "valueOf", new Class[]{String.class});
        this.enumLoadStatusClassValueOfMethod.init(null, this.coreBridge.getWrapperClass("XWalkUIClient$LoadStatus"), "valueOf", new Class[]{String.class});
        this.onCreateWindowRequestedXWalkViewInternalInitiateByInternalValueCallbackMethod.init(this.wrapper, null, "onCreateWindowRequested", new Class[]{this.coreBridge.getWrapperClass("XWalkView"), this.coreBridge.getWrapperClass("XWalkUIClient$InitiateBy"), ValueCallback.class});
        this.onDidChangeThemeColorXWalkViewInternalStringMessageMethod.init(this.wrapper, null, "onDidChangeThemeColor", new Class[]{this.coreBridge.getWrapperClass("XWalkView"), Integer.TYPE});
        this.shouldDiscardCurrentPageXWalkViewInternalStringMessageMethod.init(this.wrapper, null, "shouldDiscardCurrentPage", new Class[0]);
        this.onClearCurrentPageXWalkViewInternalStringMessageMethod.init(this.wrapper, null, "onClearCurrentPage", new Class[0]);
        this.onIconAvailableXWalkViewInternalStringMessageMethod.init(this.wrapper, null, "onIconAvailable", new Class[]{this.coreBridge.getWrapperClass("XWalkView"), String.class, Message.class});
        this.onReceivedIconXWalkViewInternalStringBitmapMethod.init(this.wrapper, null, "onReceivedIcon", new Class[]{this.coreBridge.getWrapperClass("XWalkView"), String.class, Bitmap.class});
        this.onRequestFocusXWalkViewInternalMethod.init(this.wrapper, null, "onRequestFocus", new Class[]{this.coreBridge.getWrapperClass("XWalkView")});
        this.onJavascriptCloseWindowXWalkViewInternalMethod.init(this.wrapper, null, "onJavascriptCloseWindow", new Class[]{this.coreBridge.getWrapperClass("XWalkView")});
        this.onJavascriptModalDialogXWalkViewInternalJavascriptMessageTypeInternalStringStringStringXWalkJavascriptResultInternalMethod.init(this.wrapper, null, "onJavascriptModalDialog", new Class[]{this.coreBridge.getWrapperClass("XWalkView"), this.coreBridge.getWrapperClass("XWalkUIClient$JavascriptMessageType"), String.class, String.class, String.class, this.coreBridge.getWrapperClass("XWalkJavascriptResult")});
        this.onFullscreenToggledXWalkViewInternalbooleanMethod.init(this.wrapper, null, "onFullscreenToggled", new Class[]{this.coreBridge.getWrapperClass("XWalkView"), Boolean.TYPE});
        this.openFileChooserXWalkViewInternalValueCallbackStringStringMethod.init(this.wrapper, null, "openFileChooser", new Class[]{this.coreBridge.getWrapperClass("XWalkView"), ValueCallback.class, String.class, String.class});
        this.onScaleChangedXWalkViewInternalfloatfloatMethod.init(this.wrapper, null, "onScaleChanged", new Class[]{this.coreBridge.getWrapperClass("XWalkView"), Float.TYPE, Float.TYPE});
        this.shouldOverrideKeyEventXWalkViewInternalKeyEventMethod.init(this.wrapper, null, "shouldOverrideKeyEvent", new Class[]{this.coreBridge.getWrapperClass("XWalkView"), KeyEvent.class});
        this.onUnhandledKeyEventXWalkViewInternalKeyEventMethod.init(this.wrapper, null, "onUnhandledKeyEvent", new Class[]{this.coreBridge.getWrapperClass("XWalkView"), KeyEvent.class});
        this.onConsoleMessageXWalkViewInternalStringintStringConsoleMessageTypeMethod.init(this.wrapper, null, "onConsoleMessage", new Class[]{this.coreBridge.getWrapperClass("XWalkView"), String.class, Integer.TYPE, String.class, this.coreBridge.getWrapperClass("XWalkUIClient$ConsoleMessageType")});
        this.onReceivedTitleXWalkViewInternalStringMethod.init(this.wrapper, null, "onReceivedTitle", new Class[]{this.coreBridge.getWrapperClass("XWalkView"), String.class});
        this.onPageLoadStartedXWalkViewInternalStringMethod.init(this.wrapper, null, "onPageLoadStarted", new Class[]{this.coreBridge.getWrapperClass("XWalkView"), String.class});
        this.onPageLoadStoppedXWalkViewInternalStringLoadStatusInternalMethod.init(this.wrapper, null, "onPageLoadStopped", new Class[]{this.coreBridge.getWrapperClass("XWalkView"), String.class, this.coreBridge.getWrapperClass("XWalkUIClient$LoadStatus")});
        this.onPageCommitVisibleXWalkViewInternalStringMethod.init(this.wrapper, null, "onPageCommitVisible", new Class[]{this.coreBridge.getWrapperClass("XWalkView"), String.class});
        this.onJsAlertXWalkViewInternalStringStringXWalkJavascriptResultInternalMethod.init(this.wrapper, null, "onJsAlert", new Class[]{this.coreBridge.getWrapperClass("XWalkView"), String.class, String.class, this.coreBridge.getWrapperClass("XWalkJavascriptResult")});
        this.onJsConfirmXWalkViewInternalStringStringXWalkJavascriptResultInternalMethod.init(this.wrapper, null, "onJsConfirm", new Class[]{this.coreBridge.getWrapperClass("XWalkView"), String.class, String.class, this.coreBridge.getWrapperClass("XWalkJavascriptResult")});
        this.onJsPromptXWalkViewInternalStringStringStringXWalkJavascriptResultInternalMethod.init(this.wrapper, null, "onJsPrompt", new Class[]{this.coreBridge.getWrapperClass("XWalkView"), String.class, String.class, String.class, this.coreBridge.getWrapperClass("XWalkJavascriptResult")});
        this.onShowCustomViewViewCustomViewCallbackInternalMethod.init(this.wrapper, null, "onShowCustomView", new Class[]{View.class, this.coreBridge.getWrapperClass("CustomViewCallback")});
        this.onShowCustomViewViewintCustomViewCallbackInternalMethod.init(this.wrapper, null, "onShowCustomView", new Class[]{View.class, Integer.TYPE, this.coreBridge.getWrapperClass("CustomViewCallback")});
        this.onHideCustomViewMethod.init(this.wrapper, null, "onHideCustomView", new Class[0]);
        this.onGeolocationPermissionsShowPromptStringXWalkGeolocationPermissionsCallbackInternalMethod.init(this.wrapper, null, "onGeolocationPermissionsShowPrompt", new Class[]{String.class, this.coreBridge.getWrapperClass("XWalkGeolocationPermissionsCallback")});
        this.onGeolocationPermissionsHidePromptMethod.init(this.wrapper, null, "onGeolocationPermissionsHidePrompt", new Class[0]);
        this.getVideoLoadingProgressViewMethod.init(this.wrapper, null, "getVideoLoadingProgressView", new Class[0]);
        this.onSelectInfoChangedMethod.init(this.wrapper, null, "onSelectInfoChanged", new Class[]{this.coreBridge.getWrapperClass("XWalkView"), Long.TYPE, String.class, String.class, String.class});
        this.isSearchableMethod.init(this.wrapper, null, "isSearchable", new Class[0]);
        this.onShowSosMethod.init(this.wrapper, null, "onShowSos", new Class[0]);
        this.onGetTranslateStringXWalkViewInternalMapCallbackMethod.init(this.wrapper, null, "onGetTranslateString", new Class[]{this.coreBridge.getWrapperClass("XWalkView"), Map.class});
        this.OnGetSampleStringXWalkViewInternalMapCallbackMethod.init(this.wrapper, null, "OnGetSampleString", new Class[]{this.coreBridge.getWrapperClass("XWalkView"), Map.class});
        this.onSearchWordMethod.init(this.wrapper, null, "onSearchWord", new Class[]{this.coreBridge.getWrapperClass("XWalkView"), String.class, String.class, String.class});
    }

    public boolean shouldDiscardCurrentPage() {
        if(this.shouldDiscardCurrentPageXWalkViewInternalStringMessageMethod != null) {
            if(this.shouldDiscardCurrentPageXWalkViewInternalStringMessageMethod.isNull()) {
            }
            else {
                return this.shouldDiscardCurrentPageXWalkViewInternalStringMessageMethod.invoke(new Object[0]).booleanValue();
            }
        }

        return 0;
    }

    public boolean shouldOverrideKeyEvent(XWalkViewBridge arg5, KeyEvent arg6) {
        if(this.shouldOverrideKeyEventXWalkViewInternalKeyEventMethod != null) {
            if(this.shouldOverrideKeyEventXWalkViewInternalKeyEventMethod.isNull()) {
            }
            else {
                ReflectMethod v0 = this.shouldOverrideKeyEventXWalkViewInternalKeyEventMethod;
                Object[] v1 = new Object[2];
                if((arg5 instanceof XWalkViewBridge)) {
                }
                else {
                    arg5 = null;
                }

                v1[0] = arg5.getWrapper();
                v1[1] = arg6;
                return v0.invoke(v1).booleanValue();
            }
        }

        return this.shouldOverrideKeyEventSuper(arg5, arg6);
    }

    public boolean shouldOverrideKeyEvent(XWalkViewInternal arg2, KeyEvent arg3) {
        if((arg2 instanceof XWalkViewBridge)) {
            return this.shouldOverrideKeyEvent(((XWalkViewBridge)arg2), arg3);
        }

        return super.shouldOverrideKeyEvent(arg2, arg3);
    }

    public boolean shouldOverrideKeyEventSuper(XWalkViewBridge arg1, KeyEvent arg2) {
        return super.shouldOverrideKeyEvent(((XWalkViewInternal)arg1), arg2);
    }
}

