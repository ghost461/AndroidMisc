package org.xwalk.core.internal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Rect;
import android.net.Uri;
import android.net.http.SslCertificate;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View$OnTouchListener;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.webkit.ValueCallback;
import java.util.Map;
import org.chromium.base.MemoryPressureListener;

public class XWalkViewBridge extends XWalkViewInternal {
    private ReflectMethod addJavascriptInterfaceObjectStringMethod;
    private ReflectMethod adjustSelectPositionlongStringintintMethod;
    private ReflectMethod canZoomInMethod;
    private ReflectMethod canZoomOutMethod;
    private ReflectMethod captureBitmapAsyncXWalkGetBitmapCallbackInternalMethod;
    private ReflectMethod clearCacheForSingleFileStringMethod;
    private ReflectMethod clearCachebooleanMethod;
    private ReflectMethod clearClientCertPreferencesRunnableMethod;
    private ReflectMethod clearFormDataMethod;
    private ReflectMethod clearMatchesMethod;
    private ReflectMethod clearSslPreferencesMethod;
    private ReflectMethod computeHorizontalScrollOffsetMethod;
    private ReflectMethod computeHorizontalScrollRangeMethod;
    private ReflectMethod computeVerticalScrollExtentMethod;
    private ReflectMethod computeVerticalScrollOffsetMethod;
    private ReflectMethod computeVerticalScrollRangeMethod;
    private XWalkCoreBridge coreBridge;
    private ReflectMethod evaluateJavascriptStringValueCallbackMethod;
    private ReflectMethod findAllAsyncStringMethod;
    private ReflectMethod findNextbooleanMethod;
    private ReflectMethod getAPIVersionMethod;
    private ReflectMethod getCertificateMethod;
    private ReflectMethod getCompositingSurfaceTypeMethod;
    private ReflectMethod getContentHeightMethod;
    private ReflectMethod getExtensionManagerMethod;
    private ReflectMethod getFaviconMethod;
    private ReflectMethod getHitTestResultMethod;
    private ReflectMethod getNavigationHistoryMethod;
    private ReflectMethod getOriginalUrlMethod;
    private ReflectMethod getRefererUrlMethod;
    private ReflectMethod getRemoteDebuggingUrlMethod;
    private ReflectMethod getScalenMethod;
    private ReflectMethod getSettingsMethod;
    private ReflectMethod getTitleMethod;
    private ReflectMethod getTranslateSampleStringintMethod;
    private ReflectMethod getUrlMethod;
    private ReflectMethod getUserAgentStringMethod;
    private ReflectMethod getXWalkContentViewMethod;
    private ReflectMethod getXWalkVersionMethod;
    private ReflectMethod hasEnteredFullscreenMethod;
    private ReflectMethod leaveFullscreenMethod;
    private ReflectMethod loadAppFromManifestStringStringMethod;
    private ReflectMethod loadDataStringStringStringMethod;
    private ReflectMethod loadDataWithBaseURLStringStringStringStringStringMethod;
    private ReflectMethod loadStringStringMapMethod;
    private ReflectMethod loadStringStringMethod;
    private ReflectMethod loadUrlStringMapMethod;
    private ReflectMethod loadUrlStringMethod;
    private ReflectMethod onActivityResultintintIntentMethod;
    private ReflectMethod onCreateInputConnectionEditorInfoMethod;
    private ReflectMethod onDestroyMethod;
    private ReflectMethod onFocusChangedDelegatebooleanintRectMethod;
    private ReflectMethod onHideMethod;
    private ReflectMethod onNewIntentIntentMethod;
    private ReflectMethod onOverScrolledDelegateintintbooleanbooleanMethod;
    private ReflectMethod onOverscrollRefreshbooleanMethod;
    private ReflectMethod onScrollChangedDelegateintintintintMethod;
    private ReflectMethod onShowMethod;
    private ReflectMethod onTouchEventDelegateMotionEventMethod;
    private ReflectMethod onTouchEventMotionEventMethod;
    private ReflectMethod pauseTimersMethod;
    private ReflectMethod performLongClickDelegateMethod;
    private ReflectMethod reloadintMethod;
    private ReflectMethod removeJavascriptInterfaceStringMethod;
    private ReflectMethod replaceTranslatedStringMapMethod;
    private ReflectMethod restoreStateBundleMethod;
    private ReflectMethod resumeTimersMethod;
    private ReflectMethod savePageMethod;
    private ReflectMethod saveStateBundleMethod;
    private ReflectMethod scrollByintintMethod;
    private ReflectMethod scrollTointintMethod;
    private ReflectMethod setAcceptLanguagesStringMethod;
    private ReflectMethod setBackgroundColorintMethod;
    private ReflectMethod setDownloadListenerXWalkDownloadListenerInternalMethod;
    private ReflectMethod setFindListenerXWalkFindListenerInternalMethod;
    private ReflectMethod setHorizontalScrollBarEnablebooleanMethod;
    private ReflectMethod setInitialScaleintMethod;
    private ReflectMethod setLayerTypeintPaintMethod;
    private ReflectMethod setNetworkAvailablebooleanMethod;
    private ReflectMethod setOnTouchListenerOnTouchListenerMethod;
    private ReflectMethod setOriginAccessWhitelistStringStringArrayMethod;
    private ReflectMethod setResourceClientXWalkResourceClientInternalMethod;
    private ReflectMethod setSurfaceViewVisibilityintMethod;
    private ReflectMethod setTranslateModebooleanMethod;
    private ReflectMethod setUIClientXWalkUIClientInternalMethod;
    private ReflectMethod setUserAgentStringStringMethod;
    private ReflectMethod setVerticalScrollBarEnablebooleanMethod;
    private ReflectMethod setVisibilityintMethod;
    private ReflectMethod setXWalkViewInternalVisibilityintMethod;
    private ReflectMethod setZOrderOnTopbooleanMethod;
    private ReflectMethod smoothScrollintintMethod;
    private ReflectMethod startActivityForResultIntentintBundleMethod;
    private ReflectMethod stopLoadingMethod;
    private Object wrapper;
    private ReflectMethod zoomByfloatMethod;
    private ReflectMethod zoomInMethod;
    private ReflectMethod zoomOutMethod;

    public XWalkViewBridge(Context arg4, Activity arg5, Object arg6) {
        super(arg4, arg5);
        this.getXWalkContentViewMethod = new ReflectMethod(null, "getXWalkContentView", new Class[0]);
        this.loadStringStringMethod = new ReflectMethod(null, "load", new Class[0]);
        this.loadStringStringMapMethod = new ReflectMethod(null, "load", new Class[0]);
        this.loadDataStringStringStringMethod = new ReflectMethod(null, "loadData", new Class[0]);
        this.loadDataWithBaseURLStringStringStringStringStringMethod = new ReflectMethod(null, "loadDataWithBaseURL", new Class[0]);
        this.loadUrlStringMethod = new ReflectMethod(null, "loadUrl", new Class[0]);
        this.loadUrlStringMapMethod = new ReflectMethod(null, "loadUrl", new Class[0]);
        this.loadAppFromManifestStringStringMethod = new ReflectMethod(null, "loadAppFromManifest", new Class[0]);
        this.reloadintMethod = new ReflectMethod(null, "reload", new Class[0]);
        this.stopLoadingMethod = new ReflectMethod(null, "stopLoading", new Class[0]);
        this.getRefererUrlMethod = new ReflectMethod(null, "getRefererUrl", new Class[0]);
        this.getUrlMethod = new ReflectMethod(null, "getUrl", new Class[0]);
        this.savePageMethod = new ReflectMethod(null, "savePage", new Class[0]);
        this.getHitTestResultMethod = new ReflectMethod(null, "getHitTestResult", new Class[0]);
        this.getContentHeightMethod = new ReflectMethod(null, "getContentHeight", new Class[0]);
        this.getTitleMethod = new ReflectMethod(null, "getTitle", new Class[0]);
        this.getOriginalUrlMethod = new ReflectMethod(null, "getOriginalUrl", new Class[0]);
        this.getNavigationHistoryMethod = new ReflectMethod(null, "getNavigationHistory", new Class[0]);
        this.addJavascriptInterfaceObjectStringMethod = new ReflectMethod(null, "addJavascriptInterface", new Class[0]);
        this.removeJavascriptInterfaceStringMethod = new ReflectMethod(null, "removeJavascriptInterface", new Class[0]);
        this.evaluateJavascriptStringValueCallbackMethod = new ReflectMethod(null, "evaluateJavascript", new Class[0]);
        this.clearCachebooleanMethod = new ReflectMethod(null, "clearCache", new Class[0]);
        this.clearCacheForSingleFileStringMethod = new ReflectMethod(null, "clearCacheForSingleFile", new Class[0]);
        this.hasEnteredFullscreenMethod = new ReflectMethod(null, "hasEnteredFullscreen", new Class[0]);
        this.leaveFullscreenMethod = new ReflectMethod(null, "leaveFullscreen", new Class[0]);
        this.pauseTimersMethod = new ReflectMethod(null, "pauseTimers", new Class[0]);
        this.resumeTimersMethod = new ReflectMethod(null, "resumeTimers", new Class[0]);
        this.onHideMethod = new ReflectMethod(null, "onHide", new Class[0]);
        this.onShowMethod = new ReflectMethod(null, "onShow", new Class[0]);
        this.onDestroyMethod = new ReflectMethod(null, "onDestroy", new Class[0]);
        this.startActivityForResultIntentintBundleMethod = new ReflectMethod(null, "startActivityForResult", new Class[0]);
        this.onActivityResultintintIntentMethod = new ReflectMethod(null, "onActivityResult", new Class[0]);
        this.onNewIntentIntentMethod = new ReflectMethod(null, "onNewIntent", new Class[0]);
        this.saveStateBundleMethod = new ReflectMethod(null, "saveState", new Class[0]);
        this.restoreStateBundleMethod = new ReflectMethod(null, "restoreState", new Class[0]);
        this.getAPIVersionMethod = new ReflectMethod(null, "getAPIVersion", new Class[0]);
        this.getXWalkVersionMethod = new ReflectMethod(null, "getXWalkVersion", new Class[0]);
        this.setUIClientXWalkUIClientInternalMethod = new ReflectMethod(null, "setUIClient", new Class[0]);
        this.setResourceClientXWalkResourceClientInternalMethod = new ReflectMethod(null, "setResourceClient", new Class[0]);
        this.setBackgroundColorintMethod = new ReflectMethod(null, "setBackgroundColor", new Class[0]);
        this.setOriginAccessWhitelistStringStringArrayMethod = new ReflectMethod(null, "setOriginAccessWhitelist", new Class[0]);
        this.setLayerTypeintPaintMethod = new ReflectMethod(null, "setLayerType", new Class[0]);
        this.setUserAgentStringStringMethod = new ReflectMethod(null, "setUserAgentString", new Class[0]);
        this.getUserAgentStringMethod = new ReflectMethod(null, "getUserAgentString", new Class[0]);
        this.setAcceptLanguagesStringMethod = new ReflectMethod(null, "setAcceptLanguages", new Class[0]);
        this.captureBitmapAsyncXWalkGetBitmapCallbackInternalMethod = new ReflectMethod(null, "captureBitmapAsync", new Class[0]);
        this.getSettingsMethod = new ReflectMethod(null, "getSettings", new Class[0]);
        this.setNetworkAvailablebooleanMethod = new ReflectMethod(null, "setNetworkAvailable", new Class[0]);
        this.getRemoteDebuggingUrlMethod = new ReflectMethod(null, "getRemoteDebuggingUrl", new Class[0]);
        this.zoomInMethod = new ReflectMethod(null, "zoomIn", new Class[0]);
        this.getScalenMethod = new ReflectMethod(null, "getScale", new Class[0]);
        this.zoomOutMethod = new ReflectMethod(null, "zoomOut", new Class[0]);
        this.zoomByfloatMethod = new ReflectMethod(null, "zoomBy", new Class[0]);
        this.canZoomInMethod = new ReflectMethod(null, "canZoomIn", new Class[0]);
        this.canZoomOutMethod = new ReflectMethod(null, "canZoomOut", new Class[0]);
        this.onCreateInputConnectionEditorInfoMethod = new ReflectMethod(null, "onCreateInputConnection", new Class[0]);
        this.setInitialScaleintMethod = new ReflectMethod(null, "setInitialScale", new Class[0]);
        this.getFaviconMethod = new ReflectMethod(null, "getFavicon", new Class[0]);
        this.setZOrderOnTopbooleanMethod = new ReflectMethod(null, "setZOrderOnTop", new Class[0]);
        this.clearFormDataMethod = new ReflectMethod(null, "clearFormData", new Class[0]);
        this.setVisibilityintMethod = new ReflectMethod(null, "setVisibility", new Class[0]);
        this.setSurfaceViewVisibilityintMethod = new ReflectMethod(null, "setSurfaceViewVisibility", new Class[0]);
        this.setXWalkViewInternalVisibilityintMethod = new ReflectMethod(null, "setXWalkViewInternalVisibility", new Class[0]);
        this.setDownloadListenerXWalkDownloadListenerInternalMethod = new ReflectMethod(null, "setDownloadListener", new Class[0]);
        this.performLongClickDelegateMethod = new ReflectMethod(null, "performLongClickDelegate", new Class[0]);
        this.onTouchEventDelegateMotionEventMethod = new ReflectMethod(null, "onTouchEventDelegate", new Class[0]);
        this.onTouchEventMotionEventMethod = new ReflectMethod(null, "onTouchEvent", new Class[0]);
        this.onScrollChangedDelegateintintintintMethod = new ReflectMethod(null, "onScrollChangedDelegate", new Class[0]);
        this.onFocusChangedDelegatebooleanintRectMethod = new ReflectMethod(null, "onFocusChangedDelegate", new Class[0]);
        this.onOverScrolledDelegateintintbooleanbooleanMethod = new ReflectMethod(null, "onOverScrolledDelegate", new Class[0]);
        this.onOverscrollRefreshbooleanMethod = new ReflectMethod(null, "onOverscrollRefresh", new Class[0]);
        this.setOnTouchListenerOnTouchListenerMethod = new ReflectMethod(null, "setOnTouchListener", new Class[0]);
        this.smoothScrollintintMethod = new ReflectMethod(null, "smoothScroll", new Class[0]);
        this.scrollTointintMethod = new ReflectMethod(null, "scrollTo", new Class[0]);
        this.scrollByintintMethod = new ReflectMethod(null, "scrollBy", new Class[0]);
        this.computeHorizontalScrollRangeMethod = new ReflectMethod(null, "computeHorizontalScrollRange", new Class[0]);
        this.computeHorizontalScrollOffsetMethod = new ReflectMethod(null, "computeHorizontalScrollOffset", new Class[0]);
        this.computeVerticalScrollRangeMethod = new ReflectMethod(null, "computeVerticalScrollRange", new Class[0]);
        this.computeVerticalScrollOffsetMethod = new ReflectMethod(null, "computeVerticalScrollOffset", new Class[0]);
        this.computeVerticalScrollExtentMethod = new ReflectMethod(null, "computeVerticalScrollExtent", new Class[0]);
        this.getExtensionManagerMethod = new ReflectMethod(null, "getExtensionManager", new Class[0]);
        this.clearSslPreferencesMethod = new ReflectMethod(null, "clearSslPreferences", new Class[0]);
        this.clearClientCertPreferencesRunnableMethod = new ReflectMethod(null, "clearClientCertPreferences", new Class[0]);
        this.getCertificateMethod = new ReflectMethod(null, "getCertificate", new Class[0]);
        this.setFindListenerXWalkFindListenerInternalMethod = new ReflectMethod(null, "setFindListener", new Class[0]);
        this.findAllAsyncStringMethod = new ReflectMethod(null, "findAllAsync", new Class[0]);
        this.findNextbooleanMethod = new ReflectMethod(null, "findNext", new Class[0]);
        this.clearMatchesMethod = new ReflectMethod(null, "clearMatches", new Class[0]);
        this.getCompositingSurfaceTypeMethod = new ReflectMethod(null, "getCompositingSurfaceType", new Class[0]);
        this.adjustSelectPositionlongStringintintMethod = new ReflectMethod(null, "adjustSelectPositionMethod", new Class[0]);
        this.setTranslateModebooleanMethod = new ReflectMethod(null, "setTranslateMode", new Class[0]);
        this.getTranslateSampleStringintMethod = new ReflectMethod(null, "getTranslateSampleString", new Class[0]);
        this.replaceTranslatedStringMapMethod = new ReflectMethod(null, "replaceTranslatedString", new Class[0]);
        this.setVerticalScrollBarEnablebooleanMethod = new ReflectMethod(null, "setVerticalScrollBarEnableMethod", new Class[0]);
        this.setHorizontalScrollBarEnablebooleanMethod = new ReflectMethod(null, "setHorizontalScrollBarEnableMethod", new Class[0]);
        this.wrapper = arg6;
        this.reflectionInit();
    }

    public XWalkViewBridge(Context arg4, AttributeSet arg5, Object arg6) {
        String v4_1;
        super(arg4, arg5);
        Class v2 = null;
        this.getXWalkContentViewMethod = new ReflectMethod(v2, "getXWalkContentView", new Class[0]);
        this.loadStringStringMethod = new ReflectMethod(v2, "load", new Class[0]);
        this.loadStringStringMapMethod = new ReflectMethod(v2, "load", new Class[0]);
        this.loadDataStringStringStringMethod = new ReflectMethod(v2, "loadData", new Class[0]);
        this.loadDataWithBaseURLStringStringStringStringStringMethod = new ReflectMethod(v2, "loadDataWithBaseURL", new Class[0]);
        this.loadUrlStringMethod = new ReflectMethod(v2, "loadUrl", new Class[0]);
        this.loadUrlStringMapMethod = new ReflectMethod(v2, "loadUrl", new Class[0]);
        this.loadAppFromManifestStringStringMethod = new ReflectMethod(v2, "loadAppFromManifest", new Class[0]);
        this.reloadintMethod = new ReflectMethod(v2, "reload", new Class[0]);
        this.stopLoadingMethod = new ReflectMethod(v2, "stopLoading", new Class[0]);
        this.getRefererUrlMethod = new ReflectMethod(v2, "getRefererUrl", new Class[0]);
        this.getUrlMethod = new ReflectMethod(v2, "getUrl", new Class[0]);
        this.savePageMethod = new ReflectMethod(v2, "savePage", new Class[0]);
        this.getHitTestResultMethod = new ReflectMethod(v2, "getHitTestResult", new Class[0]);
        this.getContentHeightMethod = new ReflectMethod(v2, "getContentHeight", new Class[0]);
        this.getTitleMethod = new ReflectMethod(v2, "getTitle", new Class[0]);
        this.getOriginalUrlMethod = new ReflectMethod(v2, "getOriginalUrl", new Class[0]);
        this.getNavigationHistoryMethod = new ReflectMethod(v2, "getNavigationHistory", new Class[0]);
        this.addJavascriptInterfaceObjectStringMethod = new ReflectMethod(v2, "addJavascriptInterface", new Class[0]);
        this.removeJavascriptInterfaceStringMethod = new ReflectMethod(v2, "removeJavascriptInterface", new Class[0]);
        this.evaluateJavascriptStringValueCallbackMethod = new ReflectMethod(v2, "evaluateJavascript", new Class[0]);
        this.clearCachebooleanMethod = new ReflectMethod(v2, "clearCache", new Class[0]);
        this.clearCacheForSingleFileStringMethod = new ReflectMethod(v2, "clearCacheForSingleFile", new Class[0]);
        this.hasEnteredFullscreenMethod = new ReflectMethod(v2, "hasEnteredFullscreen", new Class[0]);
        this.leaveFullscreenMethod = new ReflectMethod(v2, "leaveFullscreen", new Class[0]);
        this.pauseTimersMethod = new ReflectMethod(v2, "pauseTimers", new Class[0]);
        this.resumeTimersMethod = new ReflectMethod(v2, "resumeTimers", new Class[0]);
        this.onHideMethod = new ReflectMethod(v2, "onHide", new Class[0]);
        this.onShowMethod = new ReflectMethod(v2, "onShow", new Class[0]);
        this.onDestroyMethod = new ReflectMethod(v2, "onDestroy", new Class[0]);
        this.startActivityForResultIntentintBundleMethod = new ReflectMethod(v2, "startActivityForResult", new Class[0]);
        this.onActivityResultintintIntentMethod = new ReflectMethod(v2, "onActivityResult", new Class[0]);
        this.onNewIntentIntentMethod = new ReflectMethod(v2, "onNewIntent", new Class[0]);
        this.saveStateBundleMethod = new ReflectMethod(v2, "saveState", new Class[0]);
        this.restoreStateBundleMethod = new ReflectMethod(v2, "restoreState", new Class[0]);
        this.getAPIVersionMethod = new ReflectMethod(v2, "getAPIVersion", new Class[0]);
        this.getXWalkVersionMethod = new ReflectMethod(v2, "getXWalkVersion", new Class[0]);
        this.setUIClientXWalkUIClientInternalMethod = new ReflectMethod(v2, "setUIClient", new Class[0]);
        this.setResourceClientXWalkResourceClientInternalMethod = new ReflectMethod(v2, "setResourceClient", new Class[0]);
        this.setBackgroundColorintMethod = new ReflectMethod(v2, "setBackgroundColor", new Class[0]);
        this.setOriginAccessWhitelistStringStringArrayMethod = new ReflectMethod(v2, "setOriginAccessWhitelist", new Class[0]);
        this.setLayerTypeintPaintMethod = new ReflectMethod(v2, "setLayerType", new Class[0]);
        this.setUserAgentStringStringMethod = new ReflectMethod(v2, "setUserAgentString", new Class[0]);
        this.getUserAgentStringMethod = new ReflectMethod(v2, "getUserAgentString", new Class[0]);
        this.setAcceptLanguagesStringMethod = new ReflectMethod(v2, "setAcceptLanguages", new Class[0]);
        this.captureBitmapAsyncXWalkGetBitmapCallbackInternalMethod = new ReflectMethod(v2, "captureBitmapAsync", new Class[0]);
        this.getSettingsMethod = new ReflectMethod(v2, "getSettings", new Class[0]);
        this.setNetworkAvailablebooleanMethod = new ReflectMethod(v2, "setNetworkAvailable", new Class[0]);
        this.getRemoteDebuggingUrlMethod = new ReflectMethod(v2, "getRemoteDebuggingUrl", new Class[0]);
        this.zoomInMethod = new ReflectMethod(v2, "zoomIn", new Class[0]);
        this.getScalenMethod = new ReflectMethod(v2, "getScale", new Class[0]);
        this.zoomOutMethod = new ReflectMethod(v2, "zoomOut", new Class[0]);
        this.zoomByfloatMethod = new ReflectMethod(v2, "zoomBy", new Class[0]);
        this.canZoomInMethod = new ReflectMethod(v2, "canZoomIn", new Class[0]);
        this.canZoomOutMethod = new ReflectMethod(v2, "canZoomOut", new Class[0]);
        this.onCreateInputConnectionEditorInfoMethod = new ReflectMethod(v2, "onCreateInputConnection", new Class[0]);
        this.setInitialScaleintMethod = new ReflectMethod(v2, "setInitialScale", new Class[0]);
        this.getFaviconMethod = new ReflectMethod(v2, "getFavicon", new Class[0]);
        this.setZOrderOnTopbooleanMethod = new ReflectMethod(v2, "setZOrderOnTop", new Class[0]);
        this.clearFormDataMethod = new ReflectMethod(v2, "clearFormData", new Class[0]);
        this.setVisibilityintMethod = new ReflectMethod(v2, "setVisibility", new Class[0]);
        this.setSurfaceViewVisibilityintMethod = new ReflectMethod(v2, "setSurfaceViewVisibility", new Class[0]);
        this.setXWalkViewInternalVisibilityintMethod = new ReflectMethod(v2, "setXWalkViewInternalVisibility", new Class[0]);
        this.setDownloadListenerXWalkDownloadListenerInternalMethod = new ReflectMethod(v2, "setDownloadListener", new Class[0]);
        this.performLongClickDelegateMethod = new ReflectMethod(v2, "performLongClickDelegate", new Class[0]);
        this.onTouchEventDelegateMotionEventMethod = new ReflectMethod(v2, "onTouchEventDelegate", new Class[0]);
        this.onTouchEventMotionEventMethod = new ReflectMethod(v2, "onTouchEvent", new Class[0]);
        this.onScrollChangedDelegateintintintintMethod = new ReflectMethod(v2, "onScrollChangedDelegate", new Class[0]);
        this.onFocusChangedDelegatebooleanintRectMethod = new ReflectMethod(v2, "onFocusChangedDelegate", new Class[0]);
        this.onOverScrolledDelegateintintbooleanbooleanMethod = new ReflectMethod(v2, "onOverScrolledDelegate", new Class[0]);
        this.onOverscrollRefreshbooleanMethod = new ReflectMethod(v2, "onOverscrollRefresh", new Class[0]);
        this.setOnTouchListenerOnTouchListenerMethod = new ReflectMethod(v2, "setOnTouchListener", new Class[0]);
        this.smoothScrollintintMethod = new ReflectMethod(v2, "smoothScroll", new Class[0]);
        this.scrollTointintMethod = new ReflectMethod(v2, "scrollTo", new Class[0]);
        this.scrollByintintMethod = new ReflectMethod(v2, "scrollBy", new Class[0]);
        this.computeHorizontalScrollRangeMethod = new ReflectMethod(v2, "computeHorizontalScrollRange", new Class[0]);
        this.computeHorizontalScrollOffsetMethod = new ReflectMethod(v2, "computeHorizontalScrollOffset", new Class[0]);
        this.computeVerticalScrollRangeMethod = new ReflectMethod(v2, "computeVerticalScrollRange", new Class[0]);
        this.computeVerticalScrollOffsetMethod = new ReflectMethod(v2, "computeVerticalScrollOffset", new Class[0]);
        this.computeVerticalScrollExtentMethod = new ReflectMethod(v2, "computeVerticalScrollExtent", new Class[0]);
        this.getExtensionManagerMethod = new ReflectMethod(v2, "getExtensionManager", new Class[0]);
        this.clearSslPreferencesMethod = new ReflectMethod(v2, "clearSslPreferences", new Class[0]);
        this.clearClientCertPreferencesRunnableMethod = new ReflectMethod(v2, "clearClientCertPreferences", new Class[0]);
        this.getCertificateMethod = new ReflectMethod(v2, "getCertificate", new Class[0]);
        this.setFindListenerXWalkFindListenerInternalMethod = new ReflectMethod(v2, "setFindListener", new Class[0]);
        this.findAllAsyncStringMethod = new ReflectMethod(v2, "findAllAsync", new Class[0]);
        this.findNextbooleanMethod = new ReflectMethod(v2, "findNext", new Class[0]);
        this.clearMatchesMethod = new ReflectMethod(v2, "clearMatches", new Class[0]);
        this.getCompositingSurfaceTypeMethod = new ReflectMethod(v2, "getCompositingSurfaceType", new Class[0]);
        this.adjustSelectPositionlongStringintintMethod = new ReflectMethod(v2, "adjustSelectPositionMethod", new Class[0]);
        this.setTranslateModebooleanMethod = new ReflectMethod(v2, "setTranslateMode", new Class[0]);
        this.getTranslateSampleStringintMethod = new ReflectMethod(v2, "getTranslateSampleString", new Class[0]);
        this.replaceTranslatedStringMapMethod = new ReflectMethod(v2, "replaceTranslatedString", new Class[0]);
        this.setVerticalScrollBarEnablebooleanMethod = new ReflectMethod(v2, "setVerticalScrollBarEnableMethod", new Class[0]);
        this.setHorizontalScrollBarEnablebooleanMethod = new ReflectMethod(v2, "setHorizontalScrollBarEnableMethod", new Class[0]);
        this.wrapper = arg6;
        this.reflectionInit();
        try {
            Object v4 = new ReflectField(arg6, "mAnimatable").get();
        }
        catch(RuntimeException ) {
            v4_1 = ((String)v2);
        }

        this.initXWalkContent(v4_1);
    }

    public XWalkViewBridge(Context arg5, Object arg6) {
        super(arg5);
        this.getXWalkContentViewMethod = new ReflectMethod(null, "getXWalkContentView", new Class[0]);
        this.loadStringStringMethod = new ReflectMethod(null, "load", new Class[0]);
        this.loadStringStringMapMethod = new ReflectMethod(null, "load", new Class[0]);
        this.loadDataStringStringStringMethod = new ReflectMethod(null, "loadData", new Class[0]);
        this.loadDataWithBaseURLStringStringStringStringStringMethod = new ReflectMethod(null, "loadDataWithBaseURL", new Class[0]);
        this.loadUrlStringMethod = new ReflectMethod(null, "loadUrl", new Class[0]);
        this.loadUrlStringMapMethod = new ReflectMethod(null, "loadUrl", new Class[0]);
        this.loadAppFromManifestStringStringMethod = new ReflectMethod(null, "loadAppFromManifest", new Class[0]);
        this.reloadintMethod = new ReflectMethod(null, "reload", new Class[0]);
        this.stopLoadingMethod = new ReflectMethod(null, "stopLoading", new Class[0]);
        this.getRefererUrlMethod = new ReflectMethod(null, "getRefererUrl", new Class[0]);
        this.getUrlMethod = new ReflectMethod(null, "getUrl", new Class[0]);
        this.savePageMethod = new ReflectMethod(null, "savePage", new Class[0]);
        this.getHitTestResultMethod = new ReflectMethod(null, "getHitTestResult", new Class[0]);
        this.getContentHeightMethod = new ReflectMethod(null, "getContentHeight", new Class[0]);
        this.getTitleMethod = new ReflectMethod(null, "getTitle", new Class[0]);
        this.getOriginalUrlMethod = new ReflectMethod(null, "getOriginalUrl", new Class[0]);
        this.getNavigationHistoryMethod = new ReflectMethod(null, "getNavigationHistory", new Class[0]);
        this.addJavascriptInterfaceObjectStringMethod = new ReflectMethod(null, "addJavascriptInterface", new Class[0]);
        this.removeJavascriptInterfaceStringMethod = new ReflectMethod(null, "removeJavascriptInterface", new Class[0]);
        this.evaluateJavascriptStringValueCallbackMethod = new ReflectMethod(null, "evaluateJavascript", new Class[0]);
        this.clearCachebooleanMethod = new ReflectMethod(null, "clearCache", new Class[0]);
        this.clearCacheForSingleFileStringMethod = new ReflectMethod(null, "clearCacheForSingleFile", new Class[0]);
        this.hasEnteredFullscreenMethod = new ReflectMethod(null, "hasEnteredFullscreen", new Class[0]);
        this.leaveFullscreenMethod = new ReflectMethod(null, "leaveFullscreen", new Class[0]);
        this.pauseTimersMethod = new ReflectMethod(null, "pauseTimers", new Class[0]);
        this.resumeTimersMethod = new ReflectMethod(null, "resumeTimers", new Class[0]);
        this.onHideMethod = new ReflectMethod(null, "onHide", new Class[0]);
        this.onShowMethod = new ReflectMethod(null, "onShow", new Class[0]);
        this.onDestroyMethod = new ReflectMethod(null, "onDestroy", new Class[0]);
        this.startActivityForResultIntentintBundleMethod = new ReflectMethod(null, "startActivityForResult", new Class[0]);
        this.onActivityResultintintIntentMethod = new ReflectMethod(null, "onActivityResult", new Class[0]);
        this.onNewIntentIntentMethod = new ReflectMethod(null, "onNewIntent", new Class[0]);
        this.saveStateBundleMethod = new ReflectMethod(null, "saveState", new Class[0]);
        this.restoreStateBundleMethod = new ReflectMethod(null, "restoreState", new Class[0]);
        this.getAPIVersionMethod = new ReflectMethod(null, "getAPIVersion", new Class[0]);
        this.getXWalkVersionMethod = new ReflectMethod(null, "getXWalkVersion", new Class[0]);
        this.setUIClientXWalkUIClientInternalMethod = new ReflectMethod(null, "setUIClient", new Class[0]);
        this.setResourceClientXWalkResourceClientInternalMethod = new ReflectMethod(null, "setResourceClient", new Class[0]);
        this.setBackgroundColorintMethod = new ReflectMethod(null, "setBackgroundColor", new Class[0]);
        this.setOriginAccessWhitelistStringStringArrayMethod = new ReflectMethod(null, "setOriginAccessWhitelist", new Class[0]);
        this.setLayerTypeintPaintMethod = new ReflectMethod(null, "setLayerType", new Class[0]);
        this.setUserAgentStringStringMethod = new ReflectMethod(null, "setUserAgentString", new Class[0]);
        this.getUserAgentStringMethod = new ReflectMethod(null, "getUserAgentString", new Class[0]);
        this.setAcceptLanguagesStringMethod = new ReflectMethod(null, "setAcceptLanguages", new Class[0]);
        this.captureBitmapAsyncXWalkGetBitmapCallbackInternalMethod = new ReflectMethod(null, "captureBitmapAsync", new Class[0]);
        this.getSettingsMethod = new ReflectMethod(null, "getSettings", new Class[0]);
        this.setNetworkAvailablebooleanMethod = new ReflectMethod(null, "setNetworkAvailable", new Class[0]);
        this.getRemoteDebuggingUrlMethod = new ReflectMethod(null, "getRemoteDebuggingUrl", new Class[0]);
        this.zoomInMethod = new ReflectMethod(null, "zoomIn", new Class[0]);
        this.getScalenMethod = new ReflectMethod(null, "getScale", new Class[0]);
        this.zoomOutMethod = new ReflectMethod(null, "zoomOut", new Class[0]);
        this.zoomByfloatMethod = new ReflectMethod(null, "zoomBy", new Class[0]);
        this.canZoomInMethod = new ReflectMethod(null, "canZoomIn", new Class[0]);
        this.canZoomOutMethod = new ReflectMethod(null, "canZoomOut", new Class[0]);
        this.onCreateInputConnectionEditorInfoMethod = new ReflectMethod(null, "onCreateInputConnection", new Class[0]);
        this.setInitialScaleintMethod = new ReflectMethod(null, "setInitialScale", new Class[0]);
        this.getFaviconMethod = new ReflectMethod(null, "getFavicon", new Class[0]);
        this.setZOrderOnTopbooleanMethod = new ReflectMethod(null, "setZOrderOnTop", new Class[0]);
        this.clearFormDataMethod = new ReflectMethod(null, "clearFormData", new Class[0]);
        this.setVisibilityintMethod = new ReflectMethod(null, "setVisibility", new Class[0]);
        this.setSurfaceViewVisibilityintMethod = new ReflectMethod(null, "setSurfaceViewVisibility", new Class[0]);
        this.setXWalkViewInternalVisibilityintMethod = new ReflectMethod(null, "setXWalkViewInternalVisibility", new Class[0]);
        this.setDownloadListenerXWalkDownloadListenerInternalMethod = new ReflectMethod(null, "setDownloadListener", new Class[0]);
        this.performLongClickDelegateMethod = new ReflectMethod(null, "performLongClickDelegate", new Class[0]);
        this.onTouchEventDelegateMotionEventMethod = new ReflectMethod(null, "onTouchEventDelegate", new Class[0]);
        this.onTouchEventMotionEventMethod = new ReflectMethod(null, "onTouchEvent", new Class[0]);
        this.onScrollChangedDelegateintintintintMethod = new ReflectMethod(null, "onScrollChangedDelegate", new Class[0]);
        this.onFocusChangedDelegatebooleanintRectMethod = new ReflectMethod(null, "onFocusChangedDelegate", new Class[0]);
        this.onOverScrolledDelegateintintbooleanbooleanMethod = new ReflectMethod(null, "onOverScrolledDelegate", new Class[0]);
        this.onOverscrollRefreshbooleanMethod = new ReflectMethod(null, "onOverscrollRefresh", new Class[0]);
        this.setOnTouchListenerOnTouchListenerMethod = new ReflectMethod(null, "setOnTouchListener", new Class[0]);
        this.smoothScrollintintMethod = new ReflectMethod(null, "smoothScroll", new Class[0]);
        this.scrollTointintMethod = new ReflectMethod(null, "scrollTo", new Class[0]);
        this.scrollByintintMethod = new ReflectMethod(null, "scrollBy", new Class[0]);
        this.computeHorizontalScrollRangeMethod = new ReflectMethod(null, "computeHorizontalScrollRange", new Class[0]);
        this.computeHorizontalScrollOffsetMethod = new ReflectMethod(null, "computeHorizontalScrollOffset", new Class[0]);
        this.computeVerticalScrollRangeMethod = new ReflectMethod(null, "computeVerticalScrollRange", new Class[0]);
        this.computeVerticalScrollOffsetMethod = new ReflectMethod(null, "computeVerticalScrollOffset", new Class[0]);
        this.computeVerticalScrollExtentMethod = new ReflectMethod(null, "computeVerticalScrollExtent", new Class[0]);
        this.getExtensionManagerMethod = new ReflectMethod(null, "getExtensionManager", new Class[0]);
        this.clearSslPreferencesMethod = new ReflectMethod(null, "clearSslPreferences", new Class[0]);
        this.clearClientCertPreferencesRunnableMethod = new ReflectMethod(null, "clearClientCertPreferences", new Class[0]);
        this.getCertificateMethod = new ReflectMethod(null, "getCertificate", new Class[0]);
        this.setFindListenerXWalkFindListenerInternalMethod = new ReflectMethod(null, "setFindListener", new Class[0]);
        this.findAllAsyncStringMethod = new ReflectMethod(null, "findAllAsync", new Class[0]);
        this.findNextbooleanMethod = new ReflectMethod(null, "findNext", new Class[0]);
        this.clearMatchesMethod = new ReflectMethod(null, "clearMatches", new Class[0]);
        this.getCompositingSurfaceTypeMethod = new ReflectMethod(null, "getCompositingSurfaceType", new Class[0]);
        this.adjustSelectPositionlongStringintintMethod = new ReflectMethod(null, "adjustSelectPositionMethod", new Class[0]);
        this.setTranslateModebooleanMethod = new ReflectMethod(null, "setTranslateMode", new Class[0]);
        this.getTranslateSampleStringintMethod = new ReflectMethod(null, "getTranslateSampleString", new Class[0]);
        this.replaceTranslatedStringMapMethod = new ReflectMethod(null, "replaceTranslatedString", new Class[0]);
        this.setVerticalScrollBarEnablebooleanMethod = new ReflectMethod(null, "setVerticalScrollBarEnableMethod", new Class[0]);
        this.setHorizontalScrollBarEnablebooleanMethod = new ReflectMethod(null, "setHorizontalScrollBarEnableMethod", new Class[0]);
        this.wrapper = arg6;
        this.reflectionInit();
    }

    public void addJavascriptInterface(Object arg4, String arg5) {
        if(this.addJavascriptInterfaceObjectStringMethod == null || (this.addJavascriptInterfaceObjectStringMethod.isNull())) {
            this.addJavascriptInterfaceSuper(arg4, arg5);
        }
        else {
            this.addJavascriptInterfaceObjectStringMethod.invoke(new Object[]{arg4, arg5});
        }
    }

    public void addJavascriptInterfaceSuper(Object arg1, String arg2) {
        super.addJavascriptInterface(arg1, arg2);
    }

    public void adjustSelectPosition(long arg2, String arg4, int arg5, int arg6) {
        if(this.adjustSelectPositionlongStringintintMethod == null || (this.adjustSelectPositionlongStringintintMethod.isNull())) {
            this.adjustSelectPositionSuper(arg2, arg4, arg5, arg6);
        }
        else {
            this.adjustSelectPositionlongStringintintMethod.invoke(new Object[0]);
        }
    }

    public void adjustSelectPositionSuper(long arg1, String arg3, int arg4, int arg5) {
        super.adjustSelectPosition(arg1, arg3, arg4, arg5);
    }

    public boolean canZoomIn() {
        if(this.canZoomInMethod != null) {
            if(this.canZoomInMethod.isNull()) {
            }
            else {
                return this.canZoomInMethod.invoke(new Object[0]).booleanValue();
            }
        }

        return this.canZoomInSuper();
    }

    public boolean canZoomInSuper() {
        return super.canZoomIn();
    }

    public boolean canZoomOut() {
        if(this.canZoomOutMethod != null) {
            if(this.canZoomOutMethod.isNull()) {
            }
            else {
                return this.canZoomOutMethod.invoke(new Object[0]).booleanValue();
            }
        }

        return this.canZoomOutSuper();
    }

    public boolean canZoomOutSuper() {
        return super.canZoomOut();
    }

    public void captureBitmapAsync(XWalkGetBitmapCallbackInternal arg2) {
        if((arg2 instanceof XWalkGetBitmapCallbackBridge)) {
            this.captureBitmapAsync(((XWalkGetBitmapCallbackBridge)arg2));
        }
        else {
            super.captureBitmapAsync(arg2);
        }
    }

    public void captureBitmapAsync(XWalkGetBitmapCallbackBridge arg5) {
        if(this.captureBitmapAsyncXWalkGetBitmapCallbackInternalMethod == null || (this.captureBitmapAsyncXWalkGetBitmapCallbackInternalMethod.isNull())) {
            this.captureBitmapAsyncSuper(arg5);
        }
        else {
            ReflectMethod v0 = this.captureBitmapAsyncXWalkGetBitmapCallbackInternalMethod;
            Object[] v1 = new Object[1];
            if((arg5 instanceof XWalkGetBitmapCallbackBridge)) {
            }
            else {
                arg5 = null;
            }

            v1[0] = arg5.getWrapper();
            v0.invoke(v1);
        }
    }

    public void captureBitmapAsyncSuper(XWalkGetBitmapCallbackBridge arg1) {
        super.captureBitmapAsync(((XWalkGetBitmapCallbackInternal)arg1));
    }

    public void clearCache(boolean arg4) {
        if(this.clearCachebooleanMethod == null || (this.clearCachebooleanMethod.isNull())) {
            this.clearCacheSuper(arg4);
        }
        else {
            this.clearCachebooleanMethod.invoke(new Object[]{Boolean.valueOf(arg4)});
        }
    }

    public void clearCacheForSingleFile(String arg4) {
        if(this.clearCacheForSingleFileStringMethod == null || (this.clearCacheForSingleFileStringMethod.isNull())) {
            this.clearCacheForSingleFileSuper(arg4);
        }
        else {
            this.clearCacheForSingleFileStringMethod.invoke(new Object[]{arg4});
        }
    }

    public void clearCacheForSingleFileSuper(String arg1) {
        super.clearCacheForSingleFile(arg1);
    }

    public void clearCacheSuper(boolean arg1) {
        super.clearCache(arg1);
    }

    public void clearClientCertPreferences(Runnable arg4) {
        if(this.clearClientCertPreferencesRunnableMethod == null || (this.clearClientCertPreferencesRunnableMethod.isNull())) {
            this.clearClientCertPreferencesSuper(arg4);
        }
        else {
            this.clearClientCertPreferencesRunnableMethod.invoke(new Object[]{arg4});
        }
    }

    public void clearClientCertPreferencesSuper(Runnable arg1) {
        super.clearClientCertPreferences(arg1);
    }

    public void clearFormData() {
        if(this.clearFormDataMethod == null || (this.clearFormDataMethod.isNull())) {
            this.clearFormDataSuper();
        }
        else {
            this.clearFormDataMethod.invoke(new Object[0]);
        }
    }

    public void clearFormDataSuper() {
        super.clearFormData();
    }

    public void clearMatches() {
        if(this.clearMatchesMethod == null || (this.clearMatchesMethod.isNull())) {
            this.clearMatchesSuper();
        }
        else {
            this.clearMatchesMethod.invoke(new Object[0]);
        }
    }

    public void clearMatchesSuper() {
        super.clearMatches();
    }

    public void clearSslPreferences() {
        if(this.clearSslPreferencesMethod == null || (this.clearSslPreferencesMethod.isNull())) {
            this.clearSslPreferencesSuper();
        }
        else {
            this.clearSslPreferencesMethod.invoke(new Object[0]);
        }
    }

    public void clearSslPreferencesSuper() {
        super.clearSslPreferences();
    }

    public int computeHorizontalScrollOffset() {
        if(this.computeHorizontalScrollOffsetMethod != null) {
            if(this.computeHorizontalScrollOffsetMethod.isNull()) {
            }
            else {
                return this.computeHorizontalScrollOffsetMethod.invoke(new Object[0]).intValue();
            }
        }

        return this.computeHorizontalScrollOffsetSuper();
    }

    public int computeHorizontalScrollOffsetSuper() {
        return super.computeHorizontalScrollOffset();
    }

    public int computeHorizontalScrollRange() {
        if(this.computeHorizontalScrollRangeMethod != null) {
            if(this.computeHorizontalScrollRangeMethod.isNull()) {
            }
            else {
                return this.computeHorizontalScrollRangeMethod.invoke(new Object[0]).intValue();
            }
        }

        return this.computeHorizontalScrollRangeSuper();
    }

    public int computeHorizontalScrollRangeSuper() {
        return super.computeHorizontalScrollRange();
    }

    public int computeVerticalScrollExtent() {
        if(this.computeVerticalScrollExtentMethod != null) {
            if(this.computeVerticalScrollExtentMethod.isNull()) {
            }
            else {
                return this.computeVerticalScrollExtentMethod.invoke(new Object[0]).intValue();
            }
        }

        return this.computeVerticalScrollExtentSuper();
    }

    public int computeVerticalScrollExtentSuper() {
        return super.computeVerticalScrollExtent();
    }

    public int computeVerticalScrollOffset() {
        if(this.computeVerticalScrollOffsetMethod != null) {
            if(this.computeVerticalScrollOffsetMethod.isNull()) {
            }
            else {
                return this.computeVerticalScrollOffsetMethod.invoke(new Object[0]).intValue();
            }
        }

        return this.computeVerticalScrollOffsetSuper();
    }

    public int computeVerticalScrollOffsetSuper() {
        return super.computeVerticalScrollOffset();
    }

    public int computeVerticalScrollRange() {
        if(this.computeVerticalScrollRangeMethod != null) {
            if(this.computeVerticalScrollRangeMethod.isNull()) {
            }
            else {
                return this.computeVerticalScrollRangeMethod.invoke(new Object[0]).intValue();
            }
        }

        return this.computeVerticalScrollRangeSuper();
    }

    public int computeVerticalScrollRangeSuper() {
        return super.computeVerticalScrollRange();
    }

    public void evaluateJavascript(String arg4, ValueCallback arg5) {
        if(this.evaluateJavascriptStringValueCallbackMethod == null || (this.evaluateJavascriptStringValueCallbackMethod.isNull())) {
            this.evaluateJavascriptSuper(arg4, arg5);
        }
        else {
            this.evaluateJavascriptStringValueCallbackMethod.invoke(new Object[]{arg4, arg5});
        }
    }

    public void evaluateJavascriptSuper(String arg1, ValueCallback arg2) {
        super.evaluateJavascript(arg1, arg2);
    }

    public void findAllAsync(String arg4) {
        if(this.findAllAsyncStringMethod == null || (this.findAllAsyncStringMethod.isNull())) {
            this.findAllAsyncSuper(arg4);
        }
        else {
            this.findAllAsyncStringMethod.invoke(new Object[]{arg4});
        }
    }

    public void findAllAsyncSuper(String arg1) {
        super.findAllAsync(arg1);
    }

    public void findNext(boolean arg4) {
        if(this.findNextbooleanMethod == null || (this.findNextbooleanMethod.isNull())) {
            this.findNextSuper(arg4);
        }
        else {
            this.findNextbooleanMethod.invoke(new Object[]{Boolean.valueOf(arg4)});
        }
    }

    public void findNextSuper(boolean arg1) {
        super.findNext(arg1);
    }

    public String getAPIVersion() {
        if(this.getAPIVersionMethod != null) {
            if(this.getAPIVersionMethod.isNull()) {
            }
            else {
                return this.getAPIVersionMethod.invoke(new Object[0]);
            }
        }

        return this.getAPIVersionSuper();
    }

    public String getAPIVersionSuper() {
        String v0 = super.getAPIVersion();
        if(v0 == null) {
            return null;
        }

        return v0;
    }

    public SslCertificate getCertificate() {
        if(this.getCertificateMethod != null) {
            if(this.getCertificateMethod.isNull()) {
            }
            else {
                return this.getCertificateMethod.invoke(new Object[0]);
            }
        }

        return this.getCertificateSuper();
    }

    public SslCertificate getCertificateSuper() {
        SslCertificate v0 = super.getCertificate();
        if(v0 == null) {
            return null;
        }

        return v0;
    }

    public String getCompositingSurfaceType() {
        if(this.getCompositingSurfaceTypeMethod != null) {
            if(this.getCompositingSurfaceTypeMethod.isNull()) {
            }
            else {
                return this.getCompositingSurfaceTypeMethod.invoke(new Object[0]);
            }
        }

        return this.getCompositingSurfaceTypeSuper();
    }

    public String getCompositingSurfaceTypeSuper() {
        String v0 = super.getCompositingSurfaceType();
        if(v0 == null) {
            return null;
        }

        return v0;
    }

    public int getContentHeight() {
        if(this.getContentHeightMethod != null) {
            if(this.getContentHeightMethod.isNull()) {
            }
            else {
                return this.getContentHeightMethod.invoke(new Object[0]).intValue();
            }
        }

        return this.getContentHeightSuper();
    }

    public int getContentHeightSuper() {
        return super.getContentHeight();
    }

    public XWalkExternalExtensionManagerInternal getExtensionManager() {
        if(this.getExtensionManagerMethod != null) {
            if(this.getExtensionManagerMethod.isNull()) {
            }
            else {
                return this.coreBridge.getBridgeObject(this.getExtensionManagerMethod.invoke(new Object[0]));
            }
        }

        return this.getExtensionManagerSuper();
    }

    public XWalkExternalExtensionManagerBridge getExtensionManagerSuper() {
        XWalkExternalExtensionManagerInternal v0 = super.getExtensionManager();
        XWalkExternalExtensionManagerBridge v1 = null;
        if(v0 == null) {
            return v1;
        }

        if((v0 instanceof XWalkExternalExtensionManagerBridge)) {
            XWalkExternalExtensionManagerInternal v1_1 = v0;
        }

        return v1;
    }

    public Bitmap getFavicon() {
        if(this.getFaviconMethod != null) {
            if(this.getFaviconMethod.isNull()) {
            }
            else {
                return this.getFaviconMethod.invoke(new Object[0]);
            }
        }

        return this.getFaviconSuper();
    }

    public Bitmap getFaviconSuper() {
        Bitmap v0 = super.getFavicon();
        if(v0 == null) {
            return null;
        }

        return v0;
    }

    public XWalkHitTestResultInternal getHitTestResult() {
        if(this.getHitTestResultMethod != null) {
            if(this.getHitTestResultMethod.isNull()) {
            }
            else {
                return this.coreBridge.getBridgeObject(this.getHitTestResultMethod.invoke(new Object[0]));
            }
        }

        return this.getHitTestResultSuper();
    }

    public XWalkHitTestResultBridge getHitTestResultSuper() {
        XWalkHitTestResultBridge v0_1;
        XWalkHitTestResultInternal v0 = super.getHitTestResult();
        if(v0 == null) {
            return null;
        }

        if((v0 instanceof XWalkHitTestResultBridge)) {
        }
        else {
            v0_1 = new XWalkHitTestResultBridge(v0);
        }

        return v0_1;
    }

    public boolean getImageBitmapToFileSuper(String arg1, String arg2, String arg3, XWalkGetImageBitmapToFileFinishedCallbackBridge arg4) {
        return super.getImageBitmapToFile(arg1, arg2, arg3, ((XWalkGetImageBitmapToFileFinishedCallbackInternal)arg4));
    }

    public XWalkNavigationHistoryInternal getNavigationHistory() {
        if(this.getNavigationHistoryMethod != null) {
            if(this.getNavigationHistoryMethod.isNull()) {
            }
            else {
                return this.coreBridge.getBridgeObject(this.getNavigationHistoryMethod.invoke(new Object[0]));
            }
        }

        return this.getNavigationHistorySuper();
    }

    public XWalkNavigationHistoryBridge getNavigationHistorySuper() {
        XWalkNavigationHistoryBridge v0_1;
        XWalkNavigationHistoryInternal v0 = super.getNavigationHistory();
        if(v0 == null) {
            return null;
        }

        if((v0 instanceof XWalkNavigationHistoryBridge)) {
        }
        else {
            v0_1 = new XWalkNavigationHistoryBridge(v0);
        }

        return v0_1;
    }

    public String getOriginalUrl() {
        if(this.getOriginalUrlMethod != null) {
            if(this.getOriginalUrlMethod.isNull()) {
            }
            else {
                return this.getOriginalUrlMethod.invoke(new Object[0]);
            }
        }

        return this.getOriginalUrlSuper();
    }

    public String getOriginalUrlSuper() {
        String v0 = super.getOriginalUrl();
        if(v0 == null) {
            return null;
        }

        return v0;
    }

    public String getRefererUrl() {
        if(this.getRefererUrlMethod != null) {
            if(this.getRefererUrlMethod.isNull()) {
            }
            else {
                return this.getRefererUrlMethod.invoke(new Object[0]);
            }
        }

        return this.getRefererUrlSuper();
    }

    public String getRefererUrlSuper() {
        String v0 = super.getRefererUrl();
        if(v0 == null) {
            return null;
        }

        return v0;
    }

    public Uri getRemoteDebuggingUrl() {
        if(this.getRemoteDebuggingUrlMethod != null) {
            if(this.getRemoteDebuggingUrlMethod.isNull()) {
            }
            else {
                return this.getRemoteDebuggingUrlMethod.invoke(new Object[0]);
            }
        }

        return this.getRemoteDebuggingUrlSuper();
    }

    public Uri getRemoteDebuggingUrlSuper() {
        Uri v0 = super.getRemoteDebuggingUrl();
        if(v0 == null) {
            return null;
        }

        return v0;
    }

    public float getScale() {
        if(this.getScalenMethod != null) {
            if(this.getScalenMethod.isNull()) {
            }
            else {
                return this.getScalenMethod.invoke(new Object[0]).floatValue();
            }
        }

        return this.getScaleSuper();
    }

    public float getScaleSuper() {
        return super.getScale();
    }

    public XWalkSettingsInternal getSettings() {
        if(this.getSettingsMethod != null) {
            if(this.getSettingsMethod.isNull()) {
            }
            else {
                return this.coreBridge.getBridgeObject(this.getSettingsMethod.invoke(new Object[0]));
            }
        }

        return this.getSettingsSuper();
    }

    public XWalkSettingsBridge getSettingsSuper() {
        XWalkSettingsBridge v0_1;
        XWalkSettingsInternal v0 = super.getSettings();
        if(v0 == null) {
            return null;
        }

        if((v0 instanceof XWalkSettingsBridge)) {
        }
        else {
            v0_1 = new XWalkSettingsBridge(v0);
        }

        return v0_1;
    }

    public String getTitle() {
        if(this.getTitleMethod != null) {
            if(this.getTitleMethod.isNull()) {
            }
            else {
                return this.getTitleMethod.invoke(new Object[0]);
            }
        }

        return this.getTitleSuper();
    }

    public String getTitleSuper() {
        String v0 = super.getTitle();
        if(v0 == null) {
            return null;
        }

        return v0;
    }

    public void getTranslateSampleString(int arg2) {
        if(this.getTranslateSampleStringintMethod == null || (this.getTranslateSampleStringintMethod.isNull())) {
            this.getTranslateSampleStringSuper(arg2);
        }
        else {
            this.getTranslateSampleStringintMethod.invoke(new Object[0]);
        }
    }

    public void getTranslateSampleStringSuper(int arg1) {
        super.getTranslateSampleString(arg1);
    }

    public String getUrl() {
        if(this.getUrlMethod != null) {
            if(this.getUrlMethod.isNull()) {
            }
            else {
                return this.getUrlMethod.invoke(new Object[0]);
            }
        }

        return this.getUrlSuper();
    }

    public String getUrlSuper() {
        String v0 = super.getUrl();
        if(v0 == null) {
            return null;
        }

        return v0;
    }

    public String getUserAgentString() {
        if(this.getUserAgentStringMethod != null) {
            if(this.getUserAgentStringMethod.isNull()) {
            }
            else {
                return this.getUserAgentStringMethod.invoke(new Object[0]);
            }
        }

        return this.getUserAgentStringSuper();
    }

    public String getUserAgentStringSuper() {
        String v0 = super.getUserAgentString();
        if(v0 == null) {
            return null;
        }

        return v0;
    }

    public Object getWrapper() {
        return this.wrapper;
    }

    public ViewGroup getXWalkContentView() {
        if(this.getExtensionManagerMethod != null) {
            if(this.getExtensionManagerMethod.isNull()) {
            }
            else {
                return this.getXWalkContentViewMethod.invoke(new Object[0]);
            }
        }

        return this.getXWalkContentViewSuper();
    }

    public ViewGroup getXWalkContentViewSuper() {
        return super.getXWalkContentView();
    }

    public String getXWalkVersion() {
        if(this.getXWalkVersionMethod != null) {
            if(this.getXWalkVersionMethod.isNull()) {
            }
            else {
                return this.getXWalkVersionMethod.invoke(new Object[0]);
            }
        }

        return this.getXWalkVersionSuper();
    }

    public String getXWalkVersionSuper() {
        String v0 = super.getXWalkVersion();
        if(v0 == null) {
            return null;
        }

        return v0;
    }

    public boolean hasEnteredFullscreen() {
        if(this.hasEnteredFullscreenMethod != null) {
            if(this.hasEnteredFullscreenMethod.isNull()) {
            }
            else {
                return this.hasEnteredFullscreenMethod.invoke(new Object[0]).booleanValue();
            }
        }

        return this.hasEnteredFullscreenSuper();
    }

    public boolean hasEnteredFullscreenSuper() {
        return super.hasEnteredFullscreen();
    }

    public boolean isSupportExtendPluginForAppbrandSuper() {
        return super.isSupportExtendPluginForAppbrand();
    }

    public void leaveFullscreen() {
        if(this.leaveFullscreenMethod == null || (this.leaveFullscreenMethod.isNull())) {
            this.leaveFullscreenSuper();
        }
        else {
            this.leaveFullscreenMethod.invoke(new Object[0]);
        }
    }

    public void leaveFullscreenSuper() {
        super.leaveFullscreen();
    }

    public void load(String arg4, String arg5) {
        if(this.loadStringStringMethod == null || (this.loadStringStringMethod.isNull())) {
            this.loadSuper(arg4, arg5);
        }
        else {
            this.loadStringStringMethod.invoke(new Object[]{arg4, arg5});
        }
    }

    public void load(String arg4, String arg5, Map arg6) {
        if(this.loadStringStringMapMethod == null || (this.loadStringStringMapMethod.isNull())) {
            this.loadSuper(arg4, arg5, arg6);
        }
        else {
            this.loadStringStringMapMethod.invoke(new Object[]{arg4, arg5, arg6});
        }
    }

    public void loadAppFromManifest(String arg4, String arg5) {
        if(this.loadAppFromManifestStringStringMethod == null || (this.loadAppFromManifestStringStringMethod.isNull())) {
            this.loadAppFromManifestSuper(arg4, arg5);
        }
        else {
            this.loadAppFromManifestStringStringMethod.invoke(new Object[]{arg4, arg5});
        }
    }

    public void loadAppFromManifestSuper(String arg1, String arg2) {
        super.loadAppFromManifest(arg1, arg2);
    }

    public void loadData(String arg4, String arg5, String arg6) {
        if(this.loadDataStringStringStringMethod == null || (this.loadDataStringStringStringMethod.isNull())) {
            this.loadDataSuper(arg4, arg5, arg6);
        }
        else {
            this.loadDataStringStringStringMethod.invoke(new Object[]{arg4, arg5, arg6});
        }
    }

    public void loadDataSuper(String arg1, String arg2, String arg3) {
        super.loadData(arg1, arg2, arg3);
    }

    public void loadDataWithBaseURL(String arg4, String arg5, String arg6, String arg7, String arg8) {
        if(this.loadDataWithBaseURLStringStringStringStringStringMethod == null || (this.loadDataWithBaseURLStringStringStringStringStringMethod.isNull())) {
            this.loadDataWithBaseURLSuper(arg4, arg5, arg6, arg7, arg8);
        }
        else {
            this.loadDataWithBaseURLStringStringStringStringStringMethod.invoke(new Object[]{arg4, arg5, arg6, arg7, arg8});
        }
    }

    public void loadDataWithBaseURLSuper(String arg1, String arg2, String arg3, String arg4, String arg5) {
        super.loadDataWithBaseURL(arg1, arg2, arg3, arg4, arg5);
    }

    public void loadSuper(String arg1, String arg2) {
        super.load(arg1, arg2);
    }

    public void loadSuper(String arg1, String arg2, Map arg3) {
        super.load(arg1, arg2, arg3);
    }

    public void loadUrl(String arg4) {
        if(this.loadUrlStringMethod == null || (this.loadUrlStringMethod.isNull())) {
            this.loadUrlSuper(arg4);
        }
        else {
            this.loadUrlStringMethod.invoke(new Object[]{arg4});
        }
    }

    public void loadUrl(String arg4, Map arg5) {
        if(this.loadUrlStringMapMethod == null || (this.loadUrlStringMapMethod.isNull())) {
            this.loadUrlSuper(arg4, arg5);
        }
        else {
            this.loadUrlStringMapMethod.invoke(new Object[]{arg4, arg5});
        }
    }

    public void loadUrlSuper(String arg1) {
        super.loadUrl(arg1);
    }

    public void loadUrlSuper(String arg1, Map arg2) {
        super.loadUrl(arg1, arg2);
    }

    public void onActivityResult(int arg4, int arg5, Intent arg6) {
        if(this.onActivityResultintintIntentMethod == null || (this.onActivityResultintintIntentMethod.isNull())) {
            this.onActivityResultSuper(arg4, arg5, arg6);
        }
        else {
            this.onActivityResultintintIntentMethod.invoke(new Object[]{Integer.valueOf(arg4), Integer.valueOf(arg5), arg6});
        }
    }

    public void onActivityResultSuper(int arg1, int arg2, Intent arg3) {
        super.onActivityResult(arg1, arg2, arg3);
    }

    public InputConnection onCreateInputConnection(EditorInfo arg4) {
        if(this.onCreateInputConnectionEditorInfoMethod != null) {
            if(this.onCreateInputConnectionEditorInfoMethod.isNull()) {
            }
            else {
                return this.onCreateInputConnectionEditorInfoMethod.invoke(new Object[]{arg4});
            }
        }

        return this.onCreateInputConnectionSuper(arg4);
    }

    public InputConnection onCreateInputConnectionSuper(EditorInfo arg1) {
        InputConnection v1 = super.onCreateInputConnection(arg1);
        if(v1 == null) {
            return null;
        }

        return v1;
    }

    public void onDestroy() {
        if(this.onDestroyMethod == null || (this.onDestroyMethod.isNull())) {
            this.onDestroySuper();
        }
        else {
            this.onDestroyMethod.invoke(new Object[0]);
        }
    }

    public void onDestroySuper() {
        super.onDestroy();
    }

    public void onFocusChangedDelegate(boolean arg4, int arg5, Rect arg6) {
        if(this.onFocusChangedDelegatebooleanintRectMethod == null || (this.onFocusChangedDelegatebooleanintRectMethod.isNull())) {
            this.onFocusChangedDelegateSuper(arg4, arg5, arg6);
        }
        else {
            this.onFocusChangedDelegatebooleanintRectMethod.invoke(new Object[]{Boolean.valueOf(arg4), Integer.valueOf(arg5), arg6});
        }
    }

    public void onFocusChangedDelegateSuper(boolean arg1, int arg2, Rect arg3) {
        super.onFocusChangedDelegate(arg1, arg2, arg3);
    }

    public void onHide() {
        if(this.onHideMethod == null || (this.onHideMethod.isNull())) {
            this.onHideSuper();
        }
        else {
            this.onHideMethod.invoke(new Object[0]);
        }
    }

    public void onHideSuper() {
        super.onHide();
    }

    public boolean onNewIntent(Intent arg4) {
        if(this.onNewIntentIntentMethod != null) {
            if(this.onNewIntentIntentMethod.isNull()) {
            }
            else {
                return this.onNewIntentIntentMethod.invoke(new Object[]{arg4}).booleanValue();
            }
        }

        return this.onNewIntentSuper(arg4);
    }

    public boolean onNewIntentSuper(Intent arg1) {
        return super.onNewIntent(arg1);
    }

    public void onOverScrolledDelegate(int arg4, int arg5, boolean arg6, boolean arg7) {
        if(this.onOverScrolledDelegateintintbooleanbooleanMethod == null || (this.onOverScrolledDelegateintintbooleanbooleanMethod.isNull())) {
            this.onOverScrolledDelegateSuper(arg4, arg5, arg6, arg7);
        }
        else {
            this.onOverScrolledDelegateintintbooleanbooleanMethod.invoke(new Object[]{Integer.valueOf(arg4), Integer.valueOf(arg5), Boolean.valueOf(arg6), Boolean.valueOf(arg7)});
        }
    }

    public void onOverScrolledDelegateSuper(int arg1, int arg2, boolean arg3, boolean arg4) {
        super.onOverScrolledDelegate(arg1, arg2, arg3, arg4);
    }

    public void onOverscrollRefresh(boolean arg4) {
        if(this.onOverscrollRefreshbooleanMethod == null || (this.onOverscrollRefreshbooleanMethod.isNull())) {
            this.onOverscrollRefreshSuper(arg4);
        }
        else {
            this.onOverscrollRefreshbooleanMethod.invoke(new Object[]{Boolean.valueOf(arg4)});
        }
    }

    public void onOverscrollRefreshSuper(boolean arg1) {
        super.onOverscrollRefresh(arg1);
    }

    public void onPauseSuper() {
        if(Build$VERSION.SDK_INT <= 19) {
            super.onHide();
        }

        MemoryPressureListener.notifyMemoryPresure();
    }

    public void onResumeSuper() {
        if(Build$VERSION.SDK_INT <= 19) {
            super.onShow();
        }
    }

    public void onScrollChangedDelegate(int arg4, int arg5, int arg6, int arg7) {
        if(this.onScrollChangedDelegateintintintintMethod == null || (this.onScrollChangedDelegateintintintintMethod.isNull())) {
            this.onScrollChangedDelegateSuper(arg4, arg5, arg6, arg7);
        }
        else {
            this.onScrollChangedDelegateintintintintMethod.invoke(new Object[]{Integer.valueOf(arg4), Integer.valueOf(arg5), Integer.valueOf(arg6), Integer.valueOf(arg7)});
        }
    }

    public void onScrollChangedDelegateSuper(int arg1, int arg2, int arg3, int arg4) {
        super.onScrollChangedDelegate(arg1, arg2, arg3, arg4);
    }

    public void onShow() {
        if(this.onShowMethod == null || (this.onShowMethod.isNull())) {
            this.onShowSuper();
        }
        else {
            this.onShowMethod.invoke(new Object[0]);
        }
    }

    public void onShowSuper() {
        super.onShow();
    }

    public boolean onTouchEvent(MotionEvent arg4) {
        if(this.onTouchEventMotionEventMethod != null) {
            if(this.onTouchEventMotionEventMethod.isNull()) {
            }
            else {
                return this.onTouchEventMotionEventMethod.invoke(new Object[]{arg4}).booleanValue();
            }
        }

        return this.onTouchEventSuper(arg4);
    }

    public boolean onTouchEventDelegate(MotionEvent arg4) {
        if(this.onTouchEventDelegateMotionEventMethod != null) {
            if(this.onTouchEventDelegateMotionEventMethod.isNull()) {
            }
            else {
                return this.onTouchEventDelegateMotionEventMethod.invoke(new Object[]{arg4}).booleanValue();
            }
        }

        return this.onTouchEventDelegateSuper(arg4);
    }

    public boolean onTouchEventDelegateSuper(MotionEvent arg1) {
        return super.onTouchEventDelegate(arg1);
    }

    public boolean onTouchEventSuper(MotionEvent arg1) {
        return super.onTouchEvent(arg1);
    }

    public void pauseTimers() {
        if(this.pauseTimersMethod == null || (this.pauseTimersMethod.isNull())) {
            this.pauseTimersSuper();
        }
        else {
            this.pauseTimersMethod.invoke(new Object[0]);
        }
    }

    public void pauseTimersSuper() {
        super.pauseTimers();
    }

    public boolean performLongClickDelegate() {
        if(this.performLongClickDelegateMethod != null) {
            if(this.performLongClickDelegateMethod.isNull()) {
            }
            else {
                return this.performLongClickDelegateMethod.invoke(new Object[0]).booleanValue();
            }
        }

        return this.performLongClickDelegateSuper();
    }

    public boolean performLongClickDelegateSuper() {
        return super.performLongClickDelegate();
    }

    public boolean preInitViewSizeSuper(int arg1, int arg2, boolean arg3) {
        return super.preInitViewSize(arg1, arg2, arg3);
    }

    void reflectionInit() {
        this.coreBridge = XWalkCoreBridge.getInstance();
        if(this.coreBridge == null) {
            return;
        }

        this.loadStringStringMethod.init(this.wrapper, null, "load", new Class[]{String.class, String.class});
        this.loadStringStringMapMethod.init(this.wrapper, null, "load", new Class[]{String.class, String.class, Map.class});
        this.loadDataStringStringStringMethod.init(this.wrapper, null, "loadData", new Class[]{String.class, String.class, String.class});
        this.loadDataWithBaseURLStringStringStringStringStringMethod.init(this.wrapper, null, "loadDataWithBaseURL", new Class[]{String.class, String.class, String.class, String.class, String.class});
        this.loadUrlStringMethod.init(this.wrapper, null, "loadUrl", new Class[]{String.class});
        this.loadUrlStringMapMethod.init(this.wrapper, null, "loadUrl", new Class[]{String.class, Map.class});
        this.loadAppFromManifestStringStringMethod.init(this.wrapper, null, "loadAppFromManifest", new Class[]{String.class, String.class});
        this.reloadintMethod.init(this.wrapper, null, "reload", new Class[]{Integer.TYPE});
        this.stopLoadingMethod.init(this.wrapper, null, "stopLoading", new Class[0]);
        this.getUrlMethod.init(this.wrapper, null, "getUrl", new Class[0]);
        this.savePageMethod.init(this.wrapper, null, "savePage", new Class[]{String.class, String.class, Integer.TYPE});
        this.getHitTestResultMethod.init(this.wrapper, null, "getHitTestResult", new Class[0]);
        this.getContentHeightMethod.init(this.wrapper, null, "getContentHeight", new Class[0]);
        this.getTitleMethod.init(this.wrapper, null, "getTitle", new Class[0]);
        this.getOriginalUrlMethod.init(this.wrapper, null, "getOriginalUrl", new Class[0]);
        this.getNavigationHistoryMethod.init(this.wrapper, null, "getNavigationHistory", new Class[0]);
        this.addJavascriptInterfaceObjectStringMethod.init(this.wrapper, null, "addJavascriptInterface", new Class[]{Object.class, String.class});
        this.removeJavascriptInterfaceStringMethod.init(this.wrapper, null, "removeJavascriptInterface", new Class[]{String.class});
        this.evaluateJavascriptStringValueCallbackMethod.init(this.wrapper, null, "evaluateJavascript", new Class[]{String.class, ValueCallback.class});
        this.clearCachebooleanMethod.init(this.wrapper, null, "clearCache", new Class[]{Boolean.TYPE});
        this.clearCacheForSingleFileStringMethod.init(this.wrapper, null, "clearCacheForSingleFile", new Class[]{String.class});
        this.hasEnteredFullscreenMethod.init(this.wrapper, null, "hasEnteredFullscreen", new Class[0]);
        this.leaveFullscreenMethod.init(this.wrapper, null, "leaveFullscreen", new Class[0]);
        this.pauseTimersMethod.init(this.wrapper, null, "pauseTimers", new Class[0]);
        this.resumeTimersMethod.init(this.wrapper, null, "resumeTimers", new Class[0]);
        this.onHideMethod.init(this.wrapper, null, "onHide", new Class[0]);
        this.onShowMethod.init(this.wrapper, null, "onShow", new Class[0]);
        this.onDestroyMethod.init(this.wrapper, null, "onDestroy", new Class[0]);
        this.startActivityForResultIntentintBundleMethod.init(this.wrapper, null, "startActivityForResult", new Class[]{Intent.class, Integer.TYPE, Bundle.class});
        this.onActivityResultintintIntentMethod.init(this.wrapper, null, "onActivityResult", new Class[]{Integer.TYPE, Integer.TYPE, Intent.class});
        this.onNewIntentIntentMethod.init(this.wrapper, null, "onNewIntent", new Class[]{Intent.class});
        this.saveStateBundleMethod.init(this.wrapper, null, "saveState", new Class[]{Bundle.class});
        this.restoreStateBundleMethod.init(this.wrapper, null, "restoreState", new Class[]{Bundle.class});
        this.getAPIVersionMethod.init(this.wrapper, null, "getAPIVersion", new Class[0]);
        this.getXWalkVersionMethod.init(this.wrapper, null, "getXWalkVersion", new Class[0]);
        this.setUIClientXWalkUIClientInternalMethod.init(this.wrapper, null, "setUIClient", new Class[]{this.coreBridge.getWrapperClass("XWalkUIClient")});
        this.setResourceClientXWalkResourceClientInternalMethod.init(this.wrapper, null, "setResourceClient", new Class[]{this.coreBridge.getWrapperClass("XWalkResourceClient")});
        this.setBackgroundColorintMethod.init(this.wrapper, null, "setBackgroundColor", new Class[]{Integer.TYPE});
        this.setOriginAccessWhitelistStringStringArrayMethod.init(this.wrapper, null, "setOriginAccessWhitelist", new Class[]{String.class, String[].class});
        this.setLayerTypeintPaintMethod.init(this.wrapper, null, "setLayerType", new Class[]{Integer.TYPE, Paint.class});
        this.setUserAgentStringStringMethod.init(this.wrapper, null, "setUserAgentString", new Class[]{String.class});
        this.getUserAgentStringMethod.init(this.wrapper, null, "getUserAgentString", new Class[0]);
        this.setAcceptLanguagesStringMethod.init(this.wrapper, null, "setAcceptLanguages", new Class[]{String.class});
        this.captureBitmapAsyncXWalkGetBitmapCallbackInternalMethod.init(this.wrapper, null, "captureBitmapAsync", new Class[]{this.coreBridge.getWrapperClass("XWalkGetBitmapCallback")});
        this.getSettingsMethod.init(this.wrapper, null, "getSettings", new Class[0]);
        this.setNetworkAvailablebooleanMethod.init(this.wrapper, null, "setNetworkAvailable", new Class[]{Boolean.TYPE});
        this.getRemoteDebuggingUrlMethod.init(this.wrapper, null, "getRemoteDebuggingUrl", new Class[0]);
        this.getScalenMethod.init(this.wrapper, null, "getScale", new Class[0]);
        this.zoomInMethod.init(this.wrapper, null, "zoomIn", new Class[0]);
        this.zoomOutMethod.init(this.wrapper, null, "zoomOut", new Class[0]);
        this.zoomByfloatMethod.init(this.wrapper, null, "zoomBy", new Class[]{Float.TYPE});
        this.canZoomInMethod.init(this.wrapper, null, "canZoomIn", new Class[0]);
        this.canZoomOutMethod.init(this.wrapper, null, "canZoomOut", new Class[0]);
        this.onCreateInputConnectionEditorInfoMethod.init(this.wrapper, null, "onCreateInputConnection", new Class[]{EditorInfo.class});
        this.setInitialScaleintMethod.init(this.wrapper, null, "setInitialScale", new Class[]{Integer.TYPE});
        this.getFaviconMethod.init(this.wrapper, null, "getFavicon", new Class[0]);
        this.setZOrderOnTopbooleanMethod.init(this.wrapper, null, "setZOrderOnTop", new Class[]{Boolean.TYPE});
        this.clearFormDataMethod.init(this.wrapper, null, "clearFormData", new Class[0]);
        this.setVisibilityintMethod.init(this.wrapper, null, "setVisibility", new Class[]{Integer.TYPE});
        this.setSurfaceViewVisibilityintMethod.init(this.wrapper, null, "setSurfaceViewVisibility", new Class[]{Integer.TYPE});
        this.setXWalkViewInternalVisibilityintMethod.init(this.wrapper, null, "setXWalkViewInternalVisibility", new Class[]{Integer.TYPE});
        this.setDownloadListenerXWalkDownloadListenerInternalMethod.init(this.wrapper, null, "setDownloadListener", new Class[]{this.coreBridge.getWrapperClass("XWalkDownloadListener")});
        this.performLongClickDelegateMethod.init(this.wrapper, null, "performLongClickDelegate", new Class[0]);
        this.onTouchEventDelegateMotionEventMethod.init(this.wrapper, null, "onTouchEventDelegate", new Class[]{MotionEvent.class});
        this.onTouchEventMotionEventMethod.init(this.wrapper, null, "onTouchEvent", new Class[]{MotionEvent.class});
        this.onScrollChangedDelegateintintintintMethod.init(this.wrapper, null, "onScrollChangedDelegate", new Class[]{Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE});
        this.onFocusChangedDelegatebooleanintRectMethod.init(this.wrapper, null, "onFocusChangedDelegate", new Class[]{Boolean.TYPE, Integer.TYPE, Rect.class});
        this.onOverScrolledDelegateintintbooleanbooleanMethod.init(this.wrapper, null, "onOverScrolledDelegate", new Class[]{Integer.TYPE, Integer.TYPE, Boolean.TYPE, Boolean.TYPE});
        this.onOverscrollRefreshbooleanMethod.init(this.wrapper, null, "onOverscrollRefresh", new Class[]{Boolean.TYPE});
        this.setOnTouchListenerOnTouchListenerMethod.init(this.wrapper, null, "setOnTouchListener", new Class[]{View$OnTouchListener.class});
        this.smoothScrollintintMethod.init(this.wrapper, null, "smoothScroll", new Class[]{Integer.TYPE, Integer.TYPE, Long.TYPE});
        this.scrollTointintMethod.init(this.wrapper, null, "scrollTo", new Class[]{Integer.TYPE, Integer.TYPE});
        this.scrollByintintMethod.init(this.wrapper, null, "scrollBy", new Class[]{Integer.TYPE, Integer.TYPE});
        this.computeHorizontalScrollRangeMethod.init(this.wrapper, null, "computeHorizontalScrollRange", new Class[0]);
        this.computeHorizontalScrollOffsetMethod.init(this.wrapper, null, "computeHorizontalScrollOffset", new Class[0]);
        this.computeVerticalScrollRangeMethod.init(this.wrapper, null, "computeVerticalScrollRange", new Class[0]);
        this.computeVerticalScrollOffsetMethod.init(this.wrapper, null, "computeVerticalScrollOffset", new Class[0]);
        this.computeVerticalScrollExtentMethod.init(this.wrapper, null, "computeVerticalScrollExtent", new Class[0]);
        this.getExtensionManagerMethod.init(this.wrapper, null, "getExtensionManager", new Class[0]);
        this.clearSslPreferencesMethod.init(this.wrapper, null, "clearSslPreferences", new Class[0]);
        this.clearClientCertPreferencesRunnableMethod.init(this.wrapper, null, "clearClientCertPreferences", new Class[]{Runnable.class});
        this.getCertificateMethod.init(this.wrapper, null, "getCertificate", new Class[0]);
        this.setFindListenerXWalkFindListenerInternalMethod.init(this.wrapper, null, "setFindListener", new Class[]{this.coreBridge.getWrapperClass("XWalkFindListener")});
        this.findAllAsyncStringMethod.init(this.wrapper, null, "findAllAsync", new Class[]{String.class});
        this.findNextbooleanMethod.init(this.wrapper, null, "findNext", new Class[]{Boolean.TYPE});
        this.clearMatchesMethod.init(this.wrapper, null, "clearMatches", new Class[0]);
        this.getCompositingSurfaceTypeMethod.init(this.wrapper, null, "getCompositingSurfaceType", new Class[0]);
        this.getXWalkContentViewMethod.init(this.wrapper, null, "getXWalkContentView", new Class[0]);
        this.adjustSelectPositionlongStringintintMethod.init(this.wrapper, null, "adjustSelectPositionlongStringintint", new Class[]{Long.TYPE, String.class, Integer.TYPE, Integer.TYPE});
        this.setVerticalScrollBarEnablebooleanMethod.init(this.wrapper, null, "setVerticalScrollBarEnableboolean", new Class[]{Boolean.TYPE});
        this.setHorizontalScrollBarEnablebooleanMethod.init(this.wrapper, null, "setHorizontalScrollBarEnableboolean", new Class[]{Boolean.TYPE});
        this.replaceTranslatedStringMapMethod.init(this.wrapper, null, "replaceTranslatedString", new Class[]{Map.class});
        this.setTranslateModebooleanMethod.init(this.wrapper, null, "setTranslateMode", new Class[]{Boolean.TYPE});
        this.getTranslateSampleStringintMethod.init(this.wrapper, null, "getTranslateSampleString", new Class[]{Integer.TYPE});
        this.getRefererUrlMethod.init(this.wrapper, null, "getRefererUrl", new Class[0]);
    }

    public void reload(int arg4) {
        if(this.reloadintMethod == null || (this.reloadintMethod.isNull())) {
            this.reloadSuper(arg4);
        }
        else {
            this.reloadintMethod.invoke(new Object[]{Integer.valueOf(arg4)});
        }
    }

    public void reloadSuper(int arg1) {
        super.reload(arg1);
    }

    public void removeJavascriptInterface(String arg4) {
        if(this.removeJavascriptInterfaceStringMethod == null || (this.removeJavascriptInterfaceStringMethod.isNull())) {
            this.removeJavascriptInterfaceSuper(arg4);
        }
        else {
            this.removeJavascriptInterfaceStringMethod.invoke(new Object[]{arg4});
        }
    }

    public void removeJavascriptInterfaceSuper(String arg1) {
        super.removeJavascriptInterface(arg1);
    }

    public void replaceTranslatedString(Map arg2) {
        if(this.replaceTranslatedStringMapMethod == null || (this.replaceTranslatedStringMapMethod.isNull())) {
            this.replaceTranslatedStringSuper(arg2);
        }
        else {
            this.replaceTranslatedStringMapMethod.invoke(new Object[0]);
        }
    }

    public void replaceTranslatedStringSuper(Map arg1) {
        super.replaceTranslatedString(arg1);
    }

    public boolean restoreState(Bundle arg4) {
        if(this.restoreStateBundleMethod != null) {
            if(this.restoreStateBundleMethod.isNull()) {
            }
            else {
                return this.restoreStateBundleMethod.invoke(new Object[]{arg4}).booleanValue();
            }
        }

        return this.restoreStateSuper(arg4);
    }

    public boolean restoreStateSuper(Bundle arg1) {
        return super.restoreState(arg1);
    }

    public void resumeTimers() {
        if(this.resumeTimersMethod == null || (this.resumeTimersMethod.isNull())) {
            this.resumeTimersSuper();
        }
        else {
            this.resumeTimersMethod.invoke(new Object[0]);
        }
    }

    public void resumeTimersSuper() {
        super.resumeTimers();
    }

    public boolean savePage(String arg4, String arg5, int arg6) {
        if(this.savePageMethod != null) {
            if(this.savePageMethod.isNull()) {
            }
            else {
                return this.savePageMethod.invoke(new Object[]{arg4, arg5, Integer.valueOf(arg6)}).booleanValue();
            }
        }

        return this.savePageSuper(arg4, arg5, arg6);
    }

    public boolean savePageSuper(String arg1, String arg2, int arg3) {
        return super.savePage(arg1, arg2, arg3);
    }

    public boolean saveState(Bundle arg4) {
        if(this.saveStateBundleMethod != null) {
            if(this.saveStateBundleMethod.isNull()) {
            }
            else {
                return this.saveStateBundleMethod.invoke(new Object[]{arg4}).booleanValue();
            }
        }

        return this.saveStateSuper(arg4);
    }

    public boolean saveStateSuper(Bundle arg1) {
        return super.saveState(arg1);
    }

    public void scrollBy(int arg4, int arg5) {
        if(this.scrollByintintMethod == null || (this.scrollByintintMethod.isNull())) {
            this.scrollBySuper(arg4, arg5);
        }
        else {
            this.scrollByintintMethod.invoke(new Object[]{Integer.valueOf(arg4), Integer.valueOf(arg5)});
        }
    }

    public void scrollBySuper(int arg1, int arg2) {
        super.scrollBy(arg1, arg2);
    }

    public void scrollTo(int arg4, int arg5) {
        if(this.scrollTointintMethod == null || (this.scrollTointintMethod.isNull())) {
            this.scrollToSuper(arg4, arg5);
        }
        else {
            this.scrollTointintMethod.invoke(new Object[]{Integer.valueOf(arg4), Integer.valueOf(arg5)});
        }
    }

    public void scrollToSuper(int arg1, int arg2) {
        super.scrollTo(arg1, arg2);
    }

    public void setAcceptLanguages(String arg4) {
        if(this.setAcceptLanguagesStringMethod == null || (this.setAcceptLanguagesStringMethod.isNull())) {
            this.setAcceptLanguagesSuper(arg4);
        }
        else {
            this.setAcceptLanguagesStringMethod.invoke(new Object[]{arg4});
        }
    }

    public void setAcceptLanguagesSuper(String arg1) {
        super.setAcceptLanguages(arg1);
    }

    public void setBackgroundColor(int arg4) {
        if(this.setBackgroundColorintMethod == null || (this.setBackgroundColorintMethod.isNull())) {
            this.setBackgroundColorSuper(arg4);
        }
        else {
            this.setBackgroundColorintMethod.invoke(new Object[]{Integer.valueOf(arg4)});
        }
    }

    public void setBackgroundColorSuper(int arg1) {
        super.setBackgroundColor(arg1);
    }

    public void setBottomHeightSuper(int arg1) {
        super.setBottomHeight(arg1);
    }

    public void setDownloadListener(XWalkDownloadListenerBridge arg5) {
        if(this.setDownloadListenerXWalkDownloadListenerInternalMethod == null || (this.setDownloadListenerXWalkDownloadListenerInternalMethod.isNull())) {
            this.setDownloadListenerSuper(arg5);
        }
        else {
            ReflectMethod v0 = this.setDownloadListenerXWalkDownloadListenerInternalMethod;
            Object[] v1 = new Object[1];
            if((arg5 instanceof XWalkDownloadListenerBridge)) {
            }
            else {
                arg5 = null;
            }

            v1[0] = arg5.getWrapper();
            v0.invoke(v1);
        }
    }

    public void setDownloadListener(XWalkDownloadListenerInternal arg2) {
        if((arg2 instanceof XWalkDownloadListenerBridge)) {
            this.setDownloadListener(((XWalkDownloadListenerBridge)arg2));
        }
        else {
            super.setDownloadListener(arg2);
        }
    }

    public void setDownloadListenerSuper(XWalkDownloadListenerBridge arg1) {
        super.setDownloadListener(((XWalkDownloadListenerInternal)arg1));
    }

    public void setExtendCanvasClientSuper(XWalkExtendCanvasClientBridge arg1) {
        super.setExtendCanvasClient(((XWalkExtendCanvasClientInternal)arg1));
    }

    public void setExtendPluginClient(XWalkExtendPluginClientInternal arg2) {
        if((arg2 instanceof XWalkExtendPluginClientBridge)) {
            this.setExtendPluginClient(arg2);
        }
        else {
            super.setExtendPluginClient(arg2);
        }
    }

    public void setExtendPluginClientSuper(XWalkExtendPluginClientBridge arg1) {
        super.setExtendPluginClient(((XWalkExtendPluginClientInternal)arg1));
    }

    public void setFindListener(XWalkFindListenerBridge arg5) {
        if(this.setFindListenerXWalkFindListenerInternalMethod == null || (this.setFindListenerXWalkFindListenerInternalMethod.isNull())) {
            this.setFindListenerSuper(arg5);
        }
        else {
            ReflectMethod v0 = this.setFindListenerXWalkFindListenerInternalMethod;
            Object[] v1 = new Object[1];
            if((arg5 instanceof XWalkFindListenerBridge)) {
            }
            else {
                arg5 = null;
            }

            v1[0] = arg5.getWrapper();
            v0.invoke(v1);
        }
    }

    public void setFindListener(XWalkFindListenerInternal arg2) {
        if((arg2 instanceof XWalkFindListenerBridge)) {
            this.setFindListener(((XWalkFindListenerBridge)arg2));
        }
        else {
            super.setFindListener(arg2);
        }
    }

    public void setFindListenerSuper(XWalkFindListenerBridge arg1) {
        super.setFindListener(((XWalkFindListenerInternal)arg1));
    }

    public void setHorizontalScrollBarEnable(boolean arg2) {
        if(this.setHorizontalScrollBarEnablebooleanMethod == null || (this.setHorizontalScrollBarEnablebooleanMethod.isNull())) {
            this.setHorizontalScrollBarEnableSuper(arg2);
        }
        else {
            this.setHorizontalScrollBarEnablebooleanMethod.invoke(new Object[0]);
        }
    }

    public void setHorizontalScrollBarEnableSuper(boolean arg1) {
        super.setHorizontalScrollBarEnable(arg1);
    }

    public void setInitialScale(int arg4) {
        if(this.setInitialScaleintMethod == null || (this.setInitialScaleintMethod.isNull())) {
            this.setInitialScaleSuper(arg4);
        }
        else {
            this.setInitialScaleintMethod.invoke(new Object[]{Integer.valueOf(arg4)});
        }
    }

    public void setInitialScaleSuper(int arg1) {
        super.setInitialScale(arg1);
    }

    public void setLayerType(int arg4, Paint arg5) {
        if(this.setLayerTypeintPaintMethod == null || (this.setLayerTypeintPaintMethod.isNull())) {
            this.setLayerTypeSuper(arg4, arg5);
        }
        else {
            this.setLayerTypeintPaintMethod.invoke(new Object[]{Integer.valueOf(arg4), arg5});
        }
    }

    public void setLayerTypeSuper(int arg1, Paint arg2) {
        super.setLayerType(arg1, arg2);
    }

    public void setNetworkAvailable(boolean arg4) {
        if(this.setNetworkAvailablebooleanMethod == null || (this.setNetworkAvailablebooleanMethod.isNull())) {
            this.setNetworkAvailableSuper(arg4);
        }
        else {
            this.setNetworkAvailablebooleanMethod.invoke(new Object[]{Boolean.valueOf(arg4)});
        }
    }

    public void setNetworkAvailableSuper(boolean arg1) {
        super.setNetworkAvailable(arg1);
    }

    public void setOnTouchListener(View$OnTouchListener arg4) {
        if(this.setOnTouchListenerOnTouchListenerMethod == null || (this.setOnTouchListenerOnTouchListenerMethod.isNull())) {
            this.setOnTouchListenerSuper(arg4);
        }
        else {
            this.setOnTouchListenerOnTouchListenerMethod.invoke(new Object[]{arg4});
        }
    }

    public void setOnTouchListenerSuper(View$OnTouchListener arg1) {
        super.setOnTouchListener(arg1);
    }

    public void setOriginAccessWhitelist(String arg4, String[] arg5) {
        if(this.setOriginAccessWhitelistStringStringArrayMethod == null || (this.setOriginAccessWhitelistStringStringArrayMethod.isNull())) {
            this.setOriginAccessWhitelistSuper(arg4, arg5);
        }
        else {
            this.setOriginAccessWhitelistStringStringArrayMethod.invoke(new Object[]{arg4, arg5});
        }
    }

    public void setOriginAccessWhitelistSuper(String arg1, String[] arg2) {
        super.setOriginAccessWhitelist(arg1, arg2);
    }

    public void setProxyWebViewClientExtensionSuper(XWalkProxyWebViewClientExtensionBridge arg1) {
        super.setProxyWebViewClientExtension(((XWalkProxyWebViewClientExtensionInternal)arg1));
    }

    public void setResourceClient(XWalkResourceClientBridge arg5) {
        if(this.setResourceClientXWalkResourceClientInternalMethod == null || (this.setResourceClientXWalkResourceClientInternalMethod.isNull())) {
            this.setResourceClientSuper(arg5);
        }
        else {
            ReflectMethod v0 = this.setResourceClientXWalkResourceClientInternalMethod;
            Object[] v1 = new Object[1];
            if((arg5 instanceof XWalkResourceClientBridge)) {
            }
            else {
                arg5 = null;
            }

            v1[0] = arg5.getWrapper();
            v0.invoke(v1);
        }
    }

    public void setResourceClient(XWalkResourceClientInternal arg2) {
        if((arg2 instanceof XWalkResourceClientBridge)) {
            this.setResourceClient(((XWalkResourceClientBridge)arg2));
        }
        else {
            super.setResourceClient(arg2);
        }
    }

    public void setResourceClientSuper(XWalkResourceClientBridge arg1) {
        super.setResourceClient(((XWalkResourceClientInternal)arg1));
    }

    public void setSurfaceViewVisibility(int arg4) {
        if(this.setSurfaceViewVisibilityintMethod == null || (this.setSurfaceViewVisibilityintMethod.isNull())) {
            this.setSurfaceViewVisibilitySuper(arg4);
        }
        else {
            this.setSurfaceViewVisibilityintMethod.invoke(new Object[]{Integer.valueOf(arg4)});
        }
    }

    public void setSurfaceViewVisibilitySuper(int arg1) {
        super.setSurfaceViewVisibility(arg1);
    }

    public void setTranslateMode(boolean arg2) {
        if(this.setTranslateModebooleanMethod == null || (this.setTranslateModebooleanMethod.isNull())) {
            this.setTranslateModeSuper(arg2);
        }
        else {
            this.setTranslateModebooleanMethod.invoke(new Object[0]);
        }
    }

    public void setTranslateModeSuper(boolean arg1) {
        super.setTranslateMode(arg1);
    }

    public void setUIClient(XWalkUIClientBridge arg5) {
        if(this.setUIClientXWalkUIClientInternalMethod == null || (this.setUIClientXWalkUIClientInternalMethod.isNull())) {
            this.setUIClientSuper(arg5);
        }
        else {
            ReflectMethod v0 = this.setUIClientXWalkUIClientInternalMethod;
            Object[] v1 = new Object[1];
            if((arg5 instanceof XWalkUIClientBridge)) {
            }
            else {
                arg5 = null;
            }

            v1[0] = arg5.getWrapper();
            v0.invoke(v1);
        }
    }

    public void setUIClient(XWalkUIClientInternal arg2) {
        if((arg2 instanceof XWalkUIClientBridge)) {
            this.setUIClient(((XWalkUIClientBridge)arg2));
        }
        else {
            super.setUIClient(arg2);
        }
    }

    public void setUIClientSuper(XWalkUIClientBridge arg1) {
        super.setUIClient(((XWalkUIClientInternal)arg1));
    }

    public void setUserAgentString(String arg4) {
        if(this.setUserAgentStringStringMethod == null || (this.setUserAgentStringStringMethod.isNull())) {
            this.setUserAgentStringSuper(arg4);
        }
        else {
            this.setUserAgentStringStringMethod.invoke(new Object[]{arg4});
        }
    }

    public void setUserAgentStringSuper(String arg1) {
        super.setUserAgentString(arg1);
    }

    public void setVerticalScrollBarEnable(boolean arg2) {
        if(this.setVerticalScrollBarEnablebooleanMethod == null || (this.setVerticalScrollBarEnablebooleanMethod.isNull())) {
            this.setVerticalScrollBarEnableSuper(arg2);
        }
        else {
            this.setVerticalScrollBarEnablebooleanMethod.invoke(new Object[0]);
        }
    }

    public void setVerticalScrollBarEnableSuper(boolean arg1) {
        super.setVerticalScrollBarEnable(arg1);
    }

    public void setVisibility(int arg4) {
        if(this.setVisibilityintMethod == null || (this.setVisibilityintMethod.isNull())) {
            this.setVisibilitySuper(arg4);
        }
        else {
            this.setVisibilityintMethod.invoke(new Object[]{Integer.valueOf(arg4)});
        }
    }

    public void setVisibilitySuper(int arg1) {
        super.setVisibility(arg1);
    }

    public void setXWalkViewInternalVisibility(int arg4) {
        if(this.setXWalkViewInternalVisibilityintMethod == null || (this.setXWalkViewInternalVisibilityintMethod.isNull())) {
            this.setXWalkViewInternalVisibilitySuper(arg4);
        }
        else {
            this.setXWalkViewInternalVisibilityintMethod.invoke(new Object[]{Integer.valueOf(arg4)});
        }
    }

    public void setXWalkViewInternalVisibilitySuper(int arg1) {
        super.setXWalkViewInternalVisibility(arg1);
    }

    public void setZOrderOnTop(boolean arg4) {
        if(this.setZOrderOnTopbooleanMethod == null || (this.setZOrderOnTopbooleanMethod.isNull())) {
            this.setZOrderOnTopSuper(arg4);
        }
        else {
            this.setZOrderOnTopbooleanMethod.invoke(new Object[]{Boolean.valueOf(arg4)});
        }
    }

    public void setZOrderOnTopSuper(boolean arg1) {
        super.setZOrderOnTop(arg1);
    }

    public void smoothScroll(int arg4, int arg5, long arg6) {
        if(this.smoothScrollintintMethod == null || (this.smoothScrollintintMethod.isNull())) {
            this.smoothScrollSuper(arg4, arg5, arg6);
        }
        else {
            this.smoothScrollintintMethod.invoke(new Object[]{Integer.valueOf(arg4), Integer.valueOf(arg5), Long.valueOf(arg6)});
        }
    }

    public void smoothScrollSuper(int arg1, int arg2, long arg3) {
        super.smoothScroll(arg1, arg2, arg3);
    }

    public void startActivityForResult(Intent arg4, int arg5, Bundle arg6) {
        if(this.startActivityForResultIntentintBundleMethod == null || (this.startActivityForResultIntentintBundleMethod.isNull())) {
            this.startActivityForResultSuper(arg4, arg5, arg6);
        }
        else {
            this.startActivityForResultIntentintBundleMethod.invoke(new Object[]{arg4, Integer.valueOf(arg5), arg6});
        }
    }

    public void startActivityForResultSuper(Intent arg1, int arg2, Bundle arg3) {
        super.startActivityForResult(arg1, arg2, arg3);
    }

    public void stopLoading() {
        if(this.stopLoadingMethod == null || (this.stopLoadingMethod.isNull())) {
            this.stopLoadingSuper();
        }
        else {
            this.stopLoadingMethod.invoke(new Object[0]);
        }
    }

    public void stopLoadingSuper() {
        super.stopLoading();
    }

    public void zoomBy(float arg4) {
        if(this.zoomByfloatMethod == null || (this.zoomByfloatMethod.isNull())) {
            this.zoomBySuper(arg4);
        }
        else {
            this.zoomByfloatMethod.invoke(new Object[]{Float.valueOf(arg4)});
        }
    }

    public void zoomBySuper(float arg1) {
        super.zoomBy(arg1);
    }

    public boolean zoomIn() {
        if(this.zoomInMethod != null) {
            if(this.zoomInMethod.isNull()) {
            }
            else {
                return this.zoomInMethod.invoke(new Object[0]).booleanValue();
            }
        }

        return this.zoomInSuper();
    }

    public boolean zoomInSuper() {
        return super.zoomIn();
    }

    public boolean zoomOut() {
        if(this.zoomOutMethod != null) {
            if(this.zoomOutMethod.isNull()) {
            }
            else {
                return this.zoomOutMethod.invoke(new Object[0]).booleanValue();
            }
        }

        return this.zoomOutSuper();
    }

    public boolean zoomOutSuper() {
        return super.zoomOut();
    }
}

