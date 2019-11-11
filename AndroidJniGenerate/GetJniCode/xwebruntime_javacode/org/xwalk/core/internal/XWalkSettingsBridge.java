package org.xwalk.core.internal;

public class XWalkSettingsBridge extends XWalkSettingsInternal {
    private ReflectMethod SetJSExceptionCallBackXWalkJSExceptionListenerInternalMethod;
    private XWalkCoreBridge coreBridge;
    private ReflectMethod enumLayoutAlgorithmClassValueOfMethod;
    private ReflectMethod getAcceptLanguagesMethod;
    private ReflectMethod getAllowContentAccessMethod;
    private ReflectMethod getAllowFileAccessFromFileURLsMethod;
    private ReflectMethod getAllowFileAccessMethod;
    private ReflectMethod getAllowUniversalAccessFromFileURLsMethod;
    private ReflectMethod getBlockNetworkImageMethod;
    private ReflectMethod getBlockNetworkLoadsMethod;
    private ReflectMethod getBuiltInZoomControlsMethod;
    private ReflectMethod getCacheModeMethod;
    private ReflectMethod getCursiveFontFamilyMethod;
    private ReflectMethod getDatabaseEnabledMethod;
    private ReflectMethod getDefaultFixedFontSizeMethod;
    private ReflectMethod getDefaultFontSizeMethod;
    private ReflectMethod getDomStorageEnabledMethod;
    private ReflectMethod getFantasyFontFamilyMethod;
    private ReflectMethod getFixedFontFamilyMethod;
    private ReflectMethod getJavaScriptCanOpenWindowsAutomaticallyMethod;
    private ReflectMethod getJavaScriptEnabledMethod;
    private ReflectMethod getLayoutAlgorithmMethod;
    private ReflectMethod getLoadWithOverviewModeMethod;
    private ReflectMethod getLoadsImagesAutomaticallyMethod;
    private ReflectMethod getMediaPlaybackRequiresUserGestureMethod;
    private ReflectMethod getMinimumFontSizeMethod;
    private ReflectMethod getMinimumLogicalFontSizeMethod;
    private ReflectMethod getSansSerifFontFamilyMethod;
    private ReflectMethod getSaveFormDataMethod;
    private ReflectMethod getSerifFontFamilyMethod;
    private ReflectMethod getStandardFontFamilyMethod;
    private ReflectMethod getSupportQuirksModeMethod;
    private ReflectMethod getSupportSpatialNavigationMethod;
    private ReflectMethod getTextZoomMethod;
    private ReflectMethod getUseWideViewPortMethod;
    private ReflectMethod getUserAgentStringMethod;
    private ReflectMethod getUsingForAppBrandbooleanMethod;
    private XWalkSettingsInternal internal;
    private ReflectMethod setAcceptLanguagesStringMethod;
    private ReflectMethod setAllowContentAccessbooleanMethod;
    private ReflectMethod setAllowFileAccessFromFileURLsbooleanMethod;
    private ReflectMethod setAllowFileAccessbooleanMethod;
    private ReflectMethod setAllowUniversalAccessFromFileURLsbooleanMethod;
    private ReflectMethod setAppCacheEnabledbooleanMethod;
    private ReflectMethod setAppCachePathStringMethod;
    private ReflectMethod setBlockNetworkImagebooleanMethod;
    private ReflectMethod setBlockNetworkLoadsbooleanMethod;
    private ReflectMethod setBuiltInZoomControlsbooleanMethod;
    private ReflectMethod setCacheModeintMethod;
    private ReflectMethod setCursiveFontFamilyStringMethod;
    private ReflectMethod setDatabaseEnabledbooleanMethod;
    private ReflectMethod setDefaultFixedFontSizeintMethod;
    private ReflectMethod setDefaultFontSizeintMethod;
    private ReflectMethod setDomStorageEnabledbooleanMethod;
    private ReflectMethod setFantasyFontFamilyStringMethod;
    private ReflectMethod setFixedFontFamilyStringMethod;
    private ReflectMethod setInitialPageScalefloatMethod;
    private ReflectMethod setJavaScriptCanOpenWindowsAutomaticallybooleanMethod;
    private ReflectMethod setJavaScriptEnabledbooleanMethod;
    private ReflectMethod setLayoutAlgorithmLayoutAlgorithmInternalMethod;
    private ReflectMethod setLoadWithOverviewModebooleanMethod;
    private ReflectMethod setLoadsImagesAutomaticallybooleanMethod;
    private ReflectMethod setMediaPlaybackRequiresUserGesturebooleanMethod;
    private ReflectMethod setMinimumFontSizeintMethod;
    private ReflectMethod setMinimumLogicalFontSizeintMethod;
    private ReflectMethod setSansSerifFontFamilyStringMethod;
    private ReflectMethod setSaveFormDatabooleanMethod;
    private ReflectMethod setSerifFontFamilyStringMethod;
    private ReflectMethod setStandardFontFamilyStringMethod;
    private ReflectMethod setSupportMultipleWindowsbooleanMethod;
    private ReflectMethod setSupportQuirksModebooleanMethod;
    private ReflectMethod setSupportSpatialNavigationbooleanMethod;
    private ReflectMethod setSupportZoombooleanMethod;
    private ReflectMethod setTextZoomintMethod;
    private ReflectMethod setUseWideViewPortbooleanMethod;
    private ReflectMethod setUserAgentStringStringMethod;
    private ReflectMethod setUsingForAppBrandbooleanMethod;
    private ReflectMethod supportMultipleWindowsMethod;
    private ReflectMethod supportZoomMethod;
    private ReflectMethod supportsMultiTouchZoomForTestMethod;
    private Object wrapper;

    XWalkSettingsBridge(XWalkSettingsInternal arg6) {
        super();
        this.enumLayoutAlgorithmClassValueOfMethod = new ReflectMethod();
        this.setCacheModeintMethod = new ReflectMethod(null, "setCacheMode", new Class[0]);
        this.getCacheModeMethod = new ReflectMethod(null, "getCacheMode", new Class[0]);
        this.setBlockNetworkLoadsbooleanMethod = new ReflectMethod(null, "setBlockNetworkLoads", new Class[0]);
        this.getBlockNetworkLoadsMethod = new ReflectMethod(null, "getBlockNetworkLoads", new Class[0]);
        this.setAllowFileAccessbooleanMethod = new ReflectMethod(null, "setAllowFileAccess", new Class[0]);
        this.getAllowFileAccessMethod = new ReflectMethod(null, "getAllowFileAccess", new Class[0]);
        this.setAllowContentAccessbooleanMethod = new ReflectMethod(null, "setAllowContentAccess", new Class[0]);
        this.getAllowContentAccessMethod = new ReflectMethod(null, "getAllowContentAccess", new Class[0]);
        this.setJavaScriptEnabledbooleanMethod = new ReflectMethod(null, "setJavaScriptEnabled", new Class[0]);
        this.setStandardFontFamilyStringMethod = new ReflectMethod(null, "setStandardFontFamily", new Class[0]);
        this.setFixedFontFamilyStringMethod = new ReflectMethod(null, "setFixedFontFamily", new Class[0]);
        this.setSansSerifFontFamilyStringMethod = new ReflectMethod(null, "setSansSerifFontFamily", new Class[0]);
        this.setSerifFontFamilyStringMethod = new ReflectMethod(null, "setSerifFontFamily", new Class[0]);
        this.setCursiveFontFamilyStringMethod = new ReflectMethod(null, "setCursiveFontFamily", new Class[0]);
        this.setFantasyFontFamilyStringMethod = new ReflectMethod(null, "setFantasyFontFamily", new Class[0]);
        this.setMinimumFontSizeintMethod = new ReflectMethod(null, "setMinimumFontSize", new Class[0]);
        this.setMinimumLogicalFontSizeintMethod = new ReflectMethod(null, "setMinimumLogicalFontSize", new Class[0]);
        this.getStandardFontFamilyMethod = new ReflectMethod(null, "getStandardFontFamily", new Class[0]);
        this.getFixedFontFamilyMethod = new ReflectMethod(null, "getFixedFontFamily", new Class[0]);
        this.getSansSerifFontFamilyMethod = new ReflectMethod(null, "getSansSerifFontFamily", new Class[0]);
        this.getSerifFontFamilyMethod = new ReflectMethod(null, "getSerifFontFamily", new Class[0]);
        this.getCursiveFontFamilyMethod = new ReflectMethod(null, "getCursiveFontFamily", new Class[0]);
        this.getFantasyFontFamilyMethod = new ReflectMethod(null, "getFantasyFontFamily", new Class[0]);
        this.getMinimumFontSizeMethod = new ReflectMethod(null, "getMinimumFontSize", new Class[0]);
        this.getMinimumLogicalFontSizeMethod = new ReflectMethod(null, "getMinimumLogicalFontSize", new Class[0]);
        this.setAllowUniversalAccessFromFileURLsbooleanMethod = new ReflectMethod(null, "setAllowUniversalAccessFromFileURLs", new Class[0]);
        this.setAllowFileAccessFromFileURLsbooleanMethod = new ReflectMethod(null, "setAllowFileAccessFromFileURLs", new Class[0]);
        this.setLoadsImagesAutomaticallybooleanMethod = new ReflectMethod(null, "setLoadsImagesAutomatically", new Class[0]);
        this.getLoadsImagesAutomaticallyMethod = new ReflectMethod(null, "getLoadsImagesAutomatically", new Class[0]);
        this.setBlockNetworkImagebooleanMethod = new ReflectMethod(null, "setBlockNetworkImage", new Class[0]);
        this.getBlockNetworkImageMethod = new ReflectMethod(null, "getBlockNetworkImage", new Class[0]);
        this.getJavaScriptEnabledMethod = new ReflectMethod(null, "getJavaScriptEnabled", new Class[0]);
        this.getAllowUniversalAccessFromFileURLsMethod = new ReflectMethod(null, "getAllowUniversalAccessFromFileURLs", new Class[0]);
        this.getAllowFileAccessFromFileURLsMethod = new ReflectMethod(null, "getAllowFileAccessFromFileURLs", new Class[0]);
        this.setJavaScriptCanOpenWindowsAutomaticallybooleanMethod = new ReflectMethod(null, "setJavaScriptCanOpenWindowsAutomatically", new Class[0]);
        this.getJavaScriptCanOpenWindowsAutomaticallyMethod = new ReflectMethod(null, "getJavaScriptCanOpenWindowsAutomatically", new Class[0]);
        this.setSupportMultipleWindowsbooleanMethod = new ReflectMethod(null, "setSupportMultipleWindows", new Class[0]);
        this.supportMultipleWindowsMethod = new ReflectMethod(null, "supportMultipleWindows", new Class[0]);
        this.setUseWideViewPortbooleanMethod = new ReflectMethod(null, "setUseWideViewPort", new Class[0]);
        this.getUseWideViewPortMethod = new ReflectMethod(null, "getUseWideViewPort", new Class[0]);
        this.setDomStorageEnabledbooleanMethod = new ReflectMethod(null, "setDomStorageEnabled", new Class[0]);
        this.getDomStorageEnabledMethod = new ReflectMethod(null, "getDomStorageEnabled", new Class[0]);
        this.setDatabaseEnabledbooleanMethod = new ReflectMethod(null, "setDatabaseEnabled", new Class[0]);
        this.getDatabaseEnabledMethod = new ReflectMethod(null, "getDatabaseEnabled", new Class[0]);
        this.setMediaPlaybackRequiresUserGesturebooleanMethod = new ReflectMethod(null, "setMediaPlaybackRequiresUserGesture", new Class[0]);
        this.getMediaPlaybackRequiresUserGestureMethod = new ReflectMethod(null, "getMediaPlaybackRequiresUserGesture", new Class[0]);
        this.getUsingForAppBrandbooleanMethod = new ReflectMethod(null, "getUsingForAppBrand", new Class[0]);
        this.setUsingForAppBrandbooleanMethod = new ReflectMethod(null, "setUsingForAppBrand", new Class[0]);
        this.setUserAgentStringStringMethod = new ReflectMethod(null, "setUserAgentString", new Class[0]);
        this.getUserAgentStringMethod = new ReflectMethod(null, "getUserAgentString", new Class[0]);
        this.setAcceptLanguagesStringMethod = new ReflectMethod(null, "setAcceptLanguages", new Class[0]);
        this.getAcceptLanguagesMethod = new ReflectMethod(null, "getAcceptLanguages", new Class[0]);
        this.setSaveFormDatabooleanMethod = new ReflectMethod(null, "setSaveFormData", new Class[0]);
        this.getSaveFormDataMethod = new ReflectMethod(null, "getSaveFormData", new Class[0]);
        this.setInitialPageScalefloatMethod = new ReflectMethod(null, "setInitialPageScale", new Class[0]);
        this.setTextZoomintMethod = new ReflectMethod(null, "setTextZoom", new Class[0]);
        this.getTextZoomMethod = new ReflectMethod(null, "getTextZoom", new Class[0]);
        this.setDefaultFontSizeintMethod = new ReflectMethod(null, "setDefaultFontSize", new Class[0]);
        this.getDefaultFontSizeMethod = new ReflectMethod(null, "getDefaultFontSize", new Class[0]);
        this.setDefaultFixedFontSizeintMethod = new ReflectMethod(null, "setDefaultFixedFontSize", new Class[0]);
        this.getDefaultFixedFontSizeMethod = new ReflectMethod(null, "getDefaultFixedFontSize", new Class[0]);
        this.setSupportZoombooleanMethod = new ReflectMethod(null, "setSupportZoom", new Class[0]);
        this.supportZoomMethod = new ReflectMethod(null, "supportZoom", new Class[0]);
        this.setBuiltInZoomControlsbooleanMethod = new ReflectMethod(null, "setBuiltInZoomControls", new Class[0]);
        this.getBuiltInZoomControlsMethod = new ReflectMethod(null, "getBuiltInZoomControls", new Class[0]);
        this.supportsMultiTouchZoomForTestMethod = new ReflectMethod(null, "supportsMultiTouchZoomForTest", new Class[0]);
        this.setSupportSpatialNavigationbooleanMethod = new ReflectMethod(null, "setSupportSpatialNavigation", new Class[0]);
        this.getSupportSpatialNavigationMethod = new ReflectMethod(null, "getSupportSpatialNavigation", new Class[0]);
        this.setSupportQuirksModebooleanMethod = new ReflectMethod(null, "setSupportQuirksMode", new Class[0]);
        this.getSupportQuirksModeMethod = new ReflectMethod(null, "getSupportQuirksMode", new Class[0]);
        this.setLayoutAlgorithmLayoutAlgorithmInternalMethod = new ReflectMethod(null, "setLayoutAlgorithm", new Class[0]);
        this.getLayoutAlgorithmMethod = new ReflectMethod(null, "getLayoutAlgorithm", new Class[0]);
        this.setLoadWithOverviewModebooleanMethod = new ReflectMethod(null, "setLoadWithOverviewMode", new Class[0]);
        this.getLoadWithOverviewModeMethod = new ReflectMethod(null, "getLoadWithOverviewMode", new Class[0]);
        this.setAppCacheEnabledbooleanMethod = new ReflectMethod(null, "setAppCacheEnabled", new Class[0]);
        this.setAppCachePathStringMethod = new ReflectMethod(null, "setAppCachePath", new Class[0]);
        this.SetJSExceptionCallBackXWalkJSExceptionListenerInternalMethod = new ReflectMethod(null, "SetJsExceptionCallBack", new Class[0]);
        this.internal = arg6;
        this.reflectionInit();
    }

    private Object ConvertLayoutAlgorithmInternal(LayoutAlgorithmInternal arg4) {
        return this.enumLayoutAlgorithmClassValueOfMethod.invoke(new Object[]{arg4.toString()});
    }

    public void SetJSExceptionCallBack(XWalkJSExceptionListenerBridge arg5) {
        if(this.SetJSExceptionCallBackXWalkJSExceptionListenerInternalMethod == null || (this.SetJSExceptionCallBackXWalkJSExceptionListenerInternalMethod.isNull())) {
            this.SetJSExceptionCallBackSuper(arg5);
        }
        else {
            ReflectMethod v0 = this.SetJSExceptionCallBackXWalkJSExceptionListenerInternalMethod;
            Object[] v1 = new Object[1];
            if((arg5 instanceof XWalkJSExceptionListenerBridge)) {
            }
            else {
                arg5 = null;
            }

            v1[0] = arg5.getWrapper();
            v0.invoke(v1);
        }
    }

    public void SetJSExceptionCallBack(XWalkJSExceptionListenerInternal arg2) {
        if((arg2 instanceof XWalkJSExceptionListenerBridge)) {
            this.SetJSExceptionCallBack(((XWalkJSExceptionListenerBridge)arg2));
        }
        else {
            super.SetJSExceptionCallBack(arg2);
        }
    }

    public void SetJSExceptionCallBackSuper(XWalkJSExceptionListenerBridge arg2) {
        if(this.internal == null) {
            super.SetJSExceptionCallBack(((XWalkJSExceptionListenerInternal)arg2));
        }
        else {
            this.internal.SetJSExceptionCallBack(((XWalkJSExceptionListenerInternal)arg2));
        }
    }

    public void SetLogCallBackSuper(XWalkLogMessageListenerBridge arg2) {
        if(this.internal == null) {
            XWalkSettingsInternal.SetLogCallBack(((XWalkLogMessageListenerInternal)arg2));
        }
        else {
            XWalkSettingsInternal.SetLogCallBack(((XWalkLogMessageListenerInternal)arg2));
        }
    }

    public String getAcceptLanguages() {
        if(this.getAcceptLanguagesMethod != null) {
            if(this.getAcceptLanguagesMethod.isNull()) {
            }
            else {
                return this.getAcceptLanguagesMethod.invoke(new Object[0]);
            }
        }

        return this.getAcceptLanguagesSuper();
    }

    public String getAcceptLanguagesSuper() {
        String v0 = this.internal == null ? super.getAcceptLanguages() : this.internal.getAcceptLanguages();
        if(v0 == null) {
            return null;
        }

        return v0;
    }

    public boolean getAllowContentAccess() {
        if(this.getAllowContentAccessMethod != null) {
            if(this.getAllowContentAccessMethod.isNull()) {
            }
            else {
                return this.getAllowContentAccessMethod.invoke(new Object[0]).booleanValue();
            }
        }

        return this.getAllowContentAccessSuper();
    }

    public boolean getAllowContentAccessSuper() {
        boolean v0 = this.internal == null ? super.getAllowContentAccess() : this.internal.getAllowContentAccess();
        return v0;
    }

    public boolean getAllowFileAccess() {
        if(this.getAllowFileAccessMethod != null) {
            if(this.getAllowFileAccessMethod.isNull()) {
            }
            else {
                return this.getAllowFileAccessMethod.invoke(new Object[0]).booleanValue();
            }
        }

        return this.getAllowFileAccessSuper();
    }

    public boolean getAllowFileAccessFromFileURLs() {
        if(this.getAllowFileAccessFromFileURLsMethod != null) {
            if(this.getAllowFileAccessFromFileURLsMethod.isNull()) {
            }
            else {
                return this.getAllowFileAccessFromFileURLsMethod.invoke(new Object[0]).booleanValue();
            }
        }

        return this.getAllowFileAccessFromFileURLsSuper();
    }

    public boolean getAllowFileAccessFromFileURLsSuper() {
        boolean v0 = this.internal == null ? super.getAllowFileAccessFromFileURLs() : this.internal.getAllowFileAccessFromFileURLs();
        return v0;
    }

    public boolean getAllowFileAccessSuper() {
        boolean v0 = this.internal == null ? super.getAllowFileAccess() : this.internal.getAllowFileAccess();
        return v0;
    }

    public boolean getAllowUniversalAccessFromFileURLs() {
        if(this.getAllowUniversalAccessFromFileURLsMethod != null) {
            if(this.getAllowUniversalAccessFromFileURLsMethod.isNull()) {
            }
            else {
                return this.getAllowUniversalAccessFromFileURLsMethod.invoke(new Object[0]).booleanValue();
            }
        }

        return this.getAllowUniversalAccessFromFileURLsSuper();
    }

    public boolean getAllowUniversalAccessFromFileURLsSuper() {
        boolean v0 = this.internal == null ? super.getAllowUniversalAccessFromFileURLs() : this.internal.getAllowUniversalAccessFromFileURLs();
        return v0;
    }

    public boolean getBlockNetworkImage() {
        if(this.getBlockNetworkImageMethod != null) {
            if(this.getBlockNetworkImageMethod.isNull()) {
            }
            else {
                return this.getBlockNetworkImageMethod.invoke(new Object[0]).booleanValue();
            }
        }

        return this.getBlockNetworkImageSuper();
    }

    public boolean getBlockNetworkImageSuper() {
        boolean v0 = this.internal == null ? super.getBlockNetworkImage() : this.internal.getBlockNetworkImage();
        return v0;
    }

    public boolean getBlockNetworkLoads() {
        if(this.getBlockNetworkLoadsMethod != null) {
            if(this.getBlockNetworkLoadsMethod.isNull()) {
            }
            else {
                return this.getBlockNetworkLoadsMethod.invoke(new Object[0]).booleanValue();
            }
        }

        return this.getBlockNetworkLoadsSuper();
    }

    public boolean getBlockNetworkLoadsSuper() {
        boolean v0 = this.internal == null ? super.getBlockNetworkLoads() : this.internal.getBlockNetworkLoads();
        return v0;
    }

    public boolean getBuiltInZoomControls() {
        if(this.getBuiltInZoomControlsMethod != null) {
            if(this.getBuiltInZoomControlsMethod.isNull()) {
            }
            else {
                return this.getBuiltInZoomControlsMethod.invoke(new Object[0]).booleanValue();
            }
        }

        return this.getBuiltInZoomControlsSuper();
    }

    public boolean getBuiltInZoomControlsSuper() {
        boolean v0 = this.internal == null ? super.getBuiltInZoomControls() : this.internal.getBuiltInZoomControls();
        return v0;
    }

    public int getCacheMode() {
        if(this.getCacheModeMethod != null) {
            if(this.getCacheModeMethod.isNull()) {
            }
            else {
                return this.getCacheModeMethod.invoke(new Object[0]).intValue();
            }
        }

        return this.getCacheModeSuper();
    }

    public int getCacheModeSuper() {
        int v0 = this.internal == null ? super.getCacheMode() : this.internal.getCacheMode();
        return v0;
    }

    public String getCursiveFontFamily() {
        if(this.getCursiveFontFamilyMethod != null) {
            if(this.getCursiveFontFamilyMethod.isNull()) {
            }
            else {
                return this.getCursiveFontFamilyMethod.invoke(new Object[0]);
            }
        }

        return this.getCursiveFontFamilySuper();
    }

    public String getCursiveFontFamilySuper() {
        if(this.internal == null) {
            return super.getCursiveFontFamily();
        }

        return this.internal.getCursiveFontFamily();
    }

    public boolean getDatabaseEnabled() {
        if(this.getDatabaseEnabledMethod != null) {
            if(this.getDatabaseEnabledMethod.isNull()) {
            }
            else {
                return this.getDatabaseEnabledMethod.invoke(new Object[0]).booleanValue();
            }
        }

        return this.getDatabaseEnabledSuper();
    }

    public boolean getDatabaseEnabledSuper() {
        boolean v0 = this.internal == null ? super.getDatabaseEnabled() : this.internal.getDatabaseEnabled();
        return v0;
    }

    public int getDefaultFixedFontSize() {
        if(this.getDefaultFixedFontSizeMethod != null) {
            if(this.getDefaultFixedFontSizeMethod.isNull()) {
            }
            else {
                return this.getDefaultFixedFontSizeMethod.invoke(new Object[0]).intValue();
            }
        }

        return this.getDefaultFixedFontSizeSuper();
    }

    public int getDefaultFixedFontSizeSuper() {
        int v0 = this.internal == null ? super.getDefaultFixedFontSize() : this.internal.getDefaultFixedFontSize();
        return v0;
    }

    public int getDefaultFontSize() {
        if(this.getDefaultFontSizeMethod != null) {
            if(this.getDefaultFontSizeMethod.isNull()) {
            }
            else {
                return this.getDefaultFontSizeMethod.invoke(new Object[0]).intValue();
            }
        }

        return this.getDefaultFontSizeSuper();
    }

    public int getDefaultFontSizeSuper() {
        int v0 = this.internal == null ? super.getDefaultFontSize() : this.internal.getDefaultFontSize();
        return v0;
    }

    public boolean getDomStorageEnabled() {
        if(this.getDomStorageEnabledMethod != null) {
            if(this.getDomStorageEnabledMethod.isNull()) {
            }
            else {
                return this.getDomStorageEnabledMethod.invoke(new Object[0]).booleanValue();
            }
        }

        return this.getDomStorageEnabledSuper();
    }

    public boolean getDomStorageEnabledSuper() {
        boolean v0 = this.internal == null ? super.getDomStorageEnabled() : this.internal.getDomStorageEnabled();
        return v0;
    }

    public String getFantasyFontFamily() {
        if(this.getFantasyFontFamilyMethod != null) {
            if(this.getFantasyFontFamilyMethod.isNull()) {
            }
            else {
                return this.getFantasyFontFamilyMethod.invoke(new Object[0]);
            }
        }

        return this.getFantasyFontFamilySuper();
    }

    public String getFantasyFontFamilySuper() {
        if(this.internal == null) {
            return super.getFantasyFontFamily();
        }

        return this.internal.getFantasyFontFamily();
    }

    public String getFixedFontFamily() {
        if(this.getFixedFontFamilyMethod != null) {
            if(this.getFixedFontFamilyMethod.isNull()) {
            }
            else {
                return this.getFixedFontFamilyMethod.invoke(new Object[0]);
            }
        }

        return this.getFixedFontFamilySuper();
    }

    public String getFixedFontFamilySuper() {
        if(this.internal == null) {
            return super.getFixedFontFamily();
        }

        return this.internal.getFixedFontFamily();
    }

    public boolean getJavaScriptCanOpenWindowsAutomatically() {
        if(this.getJavaScriptCanOpenWindowsAutomaticallyMethod != null) {
            if(this.getJavaScriptCanOpenWindowsAutomaticallyMethod.isNull()) {
            }
            else {
                return this.getJavaScriptCanOpenWindowsAutomaticallyMethod.invoke(new Object[0]).booleanValue();
            }
        }

        return this.getJavaScriptCanOpenWindowsAutomaticallySuper();
    }

    public boolean getJavaScriptCanOpenWindowsAutomaticallySuper() {
        boolean v0 = this.internal == null ? super.getJavaScriptCanOpenWindowsAutomatically() : this.internal.getJavaScriptCanOpenWindowsAutomatically();
        return v0;
    }

    public boolean getJavaScriptEnabled() {
        if(this.getJavaScriptEnabledMethod != null) {
            if(this.getJavaScriptEnabledMethod.isNull()) {
            }
            else {
                return this.getJavaScriptEnabledMethod.invoke(new Object[0]).booleanValue();
            }
        }

        return this.getJavaScriptEnabledSuper();
    }

    public boolean getJavaScriptEnabledSuper() {
        boolean v0 = this.internal == null ? super.getJavaScriptEnabled() : this.internal.getJavaScriptEnabled();
        return v0;
    }

    public LayoutAlgorithmInternal getLayoutAlgorithm() {
        if(this.getLayoutAlgorithmMethod != null) {
            if(this.getLayoutAlgorithmMethod.isNull()) {
            }
            else {
                return this.getLayoutAlgorithmMethod.invoke(new Object[0]);
            }
        }

        return this.getLayoutAlgorithmSuper();
    }

    public LayoutAlgorithmInternal getLayoutAlgorithmSuper() {
        LayoutAlgorithmInternal v0 = this.internal == null ? super.getLayoutAlgorithm() : this.internal.getLayoutAlgorithm();
        if(v0 == null) {
            return null;
        }

        return v0;
    }

    public boolean getLoadWithOverviewMode() {
        if(this.getLoadWithOverviewModeMethod != null) {
            if(this.getLoadWithOverviewModeMethod.isNull()) {
            }
            else {
                return this.getLoadWithOverviewModeMethod.invoke(new Object[0]).booleanValue();
            }
        }

        return this.getLoadWithOverviewModeSuper();
    }

    public boolean getLoadWithOverviewModeSuper() {
        boolean v0 = this.internal == null ? super.getLoadWithOverviewMode() : this.internal.getLoadWithOverviewMode();
        return v0;
    }

    public boolean getLoadsImagesAutomatically() {
        if(this.getLoadsImagesAutomaticallyMethod != null) {
            if(this.getLoadsImagesAutomaticallyMethod.isNull()) {
            }
            else {
                return this.getLoadsImagesAutomaticallyMethod.invoke(new Object[0]).booleanValue();
            }
        }

        return this.getLoadsImagesAutomaticallySuper();
    }

    public boolean getLoadsImagesAutomaticallySuper() {
        boolean v0 = this.internal == null ? super.getLoadsImagesAutomatically() : this.internal.getLoadsImagesAutomatically();
        return v0;
    }

    public boolean getMediaPlaybackRequiresUserGesture() {
        if(this.getMediaPlaybackRequiresUserGestureMethod != null) {
            if(this.getMediaPlaybackRequiresUserGestureMethod.isNull()) {
            }
            else {
                return this.getMediaPlaybackRequiresUserGestureMethod.invoke(new Object[0]).booleanValue();
            }
        }

        return this.getMediaPlaybackRequiresUserGestureSuper();
    }

    public boolean getMediaPlaybackRequiresUserGestureSuper() {
        boolean v0 = this.internal == null ? super.getMediaPlaybackRequiresUserGesture() : this.internal.getMediaPlaybackRequiresUserGesture();
        return v0;
    }

    public int getMinimumFontSize() {
        if(this.getMinimumFontSizeMethod != null) {
            if(this.getMinimumFontSizeMethod.isNull()) {
            }
            else {
                return this.getMinimumFontSizeMethod.invoke(new Object[0]).intValue();
            }
        }

        return this.getMinimumFontSizeSuper();
    }

    public int getMinimumFontSizeSuper() {
        if(this.internal == null) {
            return super.getMinimumFontSize();
        }

        return this.internal.getMinimumFontSize();
    }

    public int getMinimumLogicalFontSize() {
        if(this.getMinimumLogicalFontSizeMethod != null) {
            if(this.getMinimumLogicalFontSizeMethod.isNull()) {
            }
            else {
                return this.getMinimumLogicalFontSizeMethod.invoke(new Object[0]).intValue();
            }
        }

        return this.getMinimumLogicalFontSizeSuper();
    }

    public int getMinimumLogicalFontSizeSuper() {
        if(this.internal == null) {
            return super.getMinimumLogicalFontSize();
        }

        return this.internal.getMinimumLogicalFontSize();
    }

    public String getSansSerifFontFamily() {
        if(this.getSansSerifFontFamilyMethod != null) {
            if(this.getSansSerifFontFamilyMethod.isNull()) {
            }
            else {
                return this.getSansSerifFontFamilyMethod.invoke(new Object[0]);
            }
        }

        return this.getSansSerifFontFamilySuper();
    }

    public String getSansSerifFontFamilySuper() {
        if(this.internal == null) {
            return super.getSansSerifFontFamily();
        }

        return this.internal.getSansSerifFontFamily();
    }

    public boolean getSaveFormData() {
        if(this.getSaveFormDataMethod != null) {
            if(this.getSaveFormDataMethod.isNull()) {
            }
            else {
                return this.getSaveFormDataMethod.invoke(new Object[0]).booleanValue();
            }
        }

        return this.getSaveFormDataSuper();
    }

    public boolean getSaveFormDataSuper() {
        boolean v0 = this.internal == null ? super.getSaveFormData() : this.internal.getSaveFormData();
        return v0;
    }

    public String getSerifFontFamily() {
        if(this.getSerifFontFamilyMethod != null) {
            if(this.getSerifFontFamilyMethod.isNull()) {
            }
            else {
                return this.getSerifFontFamilyMethod.invoke(new Object[0]);
            }
        }

        return this.getSerifFontFamilySuper();
    }

    public String getSerifFontFamilySuper() {
        if(this.internal == null) {
            return super.getSerifFontFamily();
        }

        return this.internal.getSerifFontFamily();
    }

    public String getStandardFontFamily() {
        if(this.getStandardFontFamilyMethod != null) {
            if(this.getStandardFontFamilyMethod.isNull()) {
            }
            else {
                return this.getStandardFontFamilyMethod.invoke(new Object[0]);
            }
        }

        return this.getStandardFontFamilySuper();
    }

    public String getStandardFontFamilySuper() {
        if(this.internal == null) {
            return super.getStandardFontFamily();
        }

        return this.internal.getStandardFontFamily();
    }

    public boolean getSupportQuirksMode() {
        if(this.getSupportQuirksModeMethod != null) {
            if(this.getSupportQuirksModeMethod.isNull()) {
            }
            else {
                return this.getSupportQuirksModeMethod.invoke(new Object[0]).booleanValue();
            }
        }

        return this.getSupportQuirksModeSuper();
    }

    public boolean getSupportQuirksModeSuper() {
        boolean v0 = this.internal == null ? super.getSupportQuirksMode() : this.internal.getSupportQuirksMode();
        return v0;
    }

    public boolean getSupportSpatialNavigation() {
        if(this.getSupportSpatialNavigationMethod != null) {
            if(this.getSupportSpatialNavigationMethod.isNull()) {
            }
            else {
                return this.getSupportSpatialNavigationMethod.invoke(new Object[0]).booleanValue();
            }
        }

        return this.getSupportSpatialNavigationSuper();
    }

    public boolean getSupportSpatialNavigationSuper() {
        boolean v0 = this.internal == null ? super.getSupportSpatialNavigation() : this.internal.getSupportSpatialNavigation();
        return v0;
    }

    public int getTextZoom() {
        if(this.getTextZoomMethod != null) {
            if(this.getTextZoomMethod.isNull()) {
            }
            else {
                return this.getTextZoomMethod.invoke(new Object[0]).intValue();
            }
        }

        return this.getTextZoomSuper();
    }

    public int getTextZoomSuper() {
        int v0 = this.internal == null ? super.getTextZoom() : this.internal.getTextZoom();
        return v0;
    }

    public boolean getUseWideViewPort() {
        if(this.getUseWideViewPortMethod != null) {
            if(this.getUseWideViewPortMethod.isNull()) {
            }
            else {
                return this.getUseWideViewPortMethod.invoke(new Object[0]).booleanValue();
            }
        }

        return this.getUseWideViewPortSuper();
    }

    public boolean getUseWideViewPortSuper() {
        boolean v0 = this.internal == null ? super.getUseWideViewPort() : this.internal.getUseWideViewPort();
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
        String v0 = this.internal == null ? super.getUserAgentString() : this.internal.getUserAgentString();
        if(v0 == null) {
            return null;
        }

        return v0;
    }

    public int getUsingForAppBrand() {
        if(this.getUsingForAppBrandbooleanMethod != null) {
            if(this.getUsingForAppBrandbooleanMethod.isNull()) {
            }
            else {
                return this.getUsingForAppBrandbooleanMethod.invoke(new Object[0]).intValue();
            }
        }

        return this.getUsingForAppBrandSuper();
    }

    public int getUsingForAppBrandSuper() {
        int v0 = this.internal == null ? super.getUsingForAppBrand() : this.internal.getUsingForAppBrand();
        return v0;
    }

    public Object getWrapper() {
        return this.wrapper;
    }

    void reflectionInit() {
        this.coreBridge = XWalkCoreBridge.getInstance();
        if(this.coreBridge == null) {
            return;
        }

        ReflectConstructor v0 = new ReflectConstructor(this.coreBridge.getWrapperClass("XWalkSettings"), new Class[]{Object.class});
        try {
            this.wrapper = v0.newInstance(new Object[]{this});
        }
        catch(UnsupportedOperationException ) {
            return;
        }

        this.enumLayoutAlgorithmClassValueOfMethod.init(null, this.coreBridge.getWrapperClass("XWalkSettings$LayoutAlgorithm"), "valueOf", new Class[]{String.class});
        this.setCacheModeintMethod.init(this.wrapper, null, "setCacheMode", new Class[]{Integer.TYPE});
        this.getCacheModeMethod.init(this.wrapper, null, "getCacheMode", new Class[0]);
        this.setBlockNetworkLoadsbooleanMethod.init(this.wrapper, null, "setBlockNetworkLoads", new Class[]{Boolean.TYPE});
        this.getBlockNetworkLoadsMethod.init(this.wrapper, null, "getBlockNetworkLoads", new Class[0]);
        this.setAllowFileAccessbooleanMethod.init(this.wrapper, null, "setAllowFileAccess", new Class[]{Boolean.TYPE});
        this.getAllowFileAccessMethod.init(this.wrapper, null, "getAllowFileAccess", new Class[0]);
        this.setAllowContentAccessbooleanMethod.init(this.wrapper, null, "setAllowContentAccess", new Class[]{Boolean.TYPE});
        this.getAllowContentAccessMethod.init(this.wrapper, null, "getAllowContentAccess", new Class[0]);
        this.setJavaScriptEnabledbooleanMethod.init(this.wrapper, null, "setJavaScriptEnabled", new Class[]{Boolean.TYPE});
        this.setStandardFontFamilyStringMethod.init(this.wrapper, null, "setStandardFontFamily", new Class[]{String.class});
        this.setFixedFontFamilyStringMethod.init(this.wrapper, null, "setFixedFontFamily", new Class[]{String.class});
        this.setSansSerifFontFamilyStringMethod.init(this.wrapper, null, "setSansSerifFontFamily", new Class[]{String.class});
        this.setSerifFontFamilyStringMethod.init(this.wrapper, null, "setSerifFontFamily", new Class[]{String.class});
        this.setCursiveFontFamilyStringMethod.init(this.wrapper, null, "setCursiveFontFamily", new Class[]{String.class});
        this.setFantasyFontFamilyStringMethod.init(this.wrapper, null, "setFantasyFontFamily", new Class[]{String.class});
        this.setMinimumFontSizeintMethod.init(this.wrapper, null, "setMinimumFontSize", new Class[]{Integer.TYPE});
        this.setMinimumLogicalFontSizeintMethod.init(this.wrapper, null, "setMinimumLogicalFontSize", new Class[]{Integer.TYPE});
        this.getStandardFontFamilyMethod.init(this.wrapper, null, "getStandardFontFamily", new Class[0]);
        this.getFixedFontFamilyMethod.init(this.wrapper, null, "getFixedFontFamily", new Class[0]);
        this.getSansSerifFontFamilyMethod.init(this.wrapper, null, "getSansSerifFontFamily", new Class[0]);
        this.getSerifFontFamilyMethod.init(this.wrapper, null, "getSerifFontFamily", new Class[0]);
        this.getCursiveFontFamilyMethod.init(this.wrapper, null, "getCursiveFontFamily", new Class[0]);
        this.getFantasyFontFamilyMethod.init(this.wrapper, null, "getFantasyFontFamily", new Class[0]);
        this.getMinimumFontSizeMethod.init(this.wrapper, null, "getMinimumFontSize", new Class[0]);
        this.getMinimumLogicalFontSizeMethod.init(this.wrapper, null, "getMinimumLogicalFontSize", new Class[0]);
        this.setAllowUniversalAccessFromFileURLsbooleanMethod.init(this.wrapper, null, "setAllowUniversalAccessFromFileURLs", new Class[]{Boolean.TYPE});
        this.setAllowFileAccessFromFileURLsbooleanMethod.init(this.wrapper, null, "setAllowFileAccessFromFileURLs", new Class[]{Boolean.TYPE});
        this.setLoadsImagesAutomaticallybooleanMethod.init(this.wrapper, null, "setLoadsImagesAutomatically", new Class[]{Boolean.TYPE});
        this.getLoadsImagesAutomaticallyMethod.init(this.wrapper, null, "getLoadsImagesAutomatically", new Class[0]);
        this.setBlockNetworkImagebooleanMethod.init(this.wrapper, null, "setBlockNetworkImage", new Class[]{Boolean.TYPE});
        this.getBlockNetworkImageMethod.init(this.wrapper, null, "getBlockNetworkImage", new Class[0]);
        this.getJavaScriptEnabledMethod.init(this.wrapper, null, "getJavaScriptEnabled", new Class[0]);
        this.getAllowUniversalAccessFromFileURLsMethod.init(this.wrapper, null, "getAllowUniversalAccessFromFileURLs", new Class[0]);
        this.getAllowFileAccessFromFileURLsMethod.init(this.wrapper, null, "getAllowFileAccessFromFileURLs", new Class[0]);
        this.setJavaScriptCanOpenWindowsAutomaticallybooleanMethod.init(this.wrapper, null, "setJavaScriptCanOpenWindowsAutomatically", new Class[]{Boolean.TYPE});
        this.getJavaScriptCanOpenWindowsAutomaticallyMethod.init(this.wrapper, null, "getJavaScriptCanOpenWindowsAutomatically", new Class[0]);
        this.setSupportMultipleWindowsbooleanMethod.init(this.wrapper, null, "setSupportMultipleWindows", new Class[]{Boolean.TYPE});
        this.supportMultipleWindowsMethod.init(this.wrapper, null, "supportMultipleWindows", new Class[0]);
        this.setUseWideViewPortbooleanMethod.init(this.wrapper, null, "setUseWideViewPort", new Class[]{Boolean.TYPE});
        this.getUseWideViewPortMethod.init(this.wrapper, null, "getUseWideViewPort", new Class[0]);
        this.setDomStorageEnabledbooleanMethod.init(this.wrapper, null, "setDomStorageEnabled", new Class[]{Boolean.TYPE});
        this.getDomStorageEnabledMethod.init(this.wrapper, null, "getDomStorageEnabled", new Class[0]);
        this.setDatabaseEnabledbooleanMethod.init(this.wrapper, null, "setDatabaseEnabled", new Class[]{Boolean.TYPE});
        this.getDatabaseEnabledMethod.init(this.wrapper, null, "getDatabaseEnabled", new Class[0]);
        this.setMediaPlaybackRequiresUserGesturebooleanMethod.init(this.wrapper, null, "setMediaPlaybackRequiresUserGesture", new Class[]{Boolean.TYPE});
        this.getMediaPlaybackRequiresUserGestureMethod.init(this.wrapper, null, "getMediaPlaybackRequiresUserGesture", new Class[0]);
        this.setUsingForAppBrandbooleanMethod.init(this.wrapper, null, "setUsingForAppBrand", new Class[]{Integer.TYPE});
        this.getUsingForAppBrandbooleanMethod.init(this.wrapper, null, "getUsingForAppBrand", new Class[0]);
        this.setUserAgentStringStringMethod.init(this.wrapper, null, "setUserAgentString", new Class[]{String.class});
        this.getUserAgentStringMethod.init(this.wrapper, null, "getUserAgentString", new Class[0]);
        this.setAcceptLanguagesStringMethod.init(this.wrapper, null, "setAcceptLanguages", new Class[]{String.class});
        this.getAcceptLanguagesMethod.init(this.wrapper, null, "getAcceptLanguages", new Class[0]);
        this.setSaveFormDatabooleanMethod.init(this.wrapper, null, "setSaveFormData", new Class[]{Boolean.TYPE});
        this.getSaveFormDataMethod.init(this.wrapper, null, "getSaveFormData", new Class[0]);
        this.setInitialPageScalefloatMethod.init(this.wrapper, null, "setInitialPageScale", new Class[]{Float.TYPE});
        this.setTextZoomintMethod.init(this.wrapper, null, "setTextZoom", new Class[]{Integer.TYPE});
        this.getTextZoomMethod.init(this.wrapper, null, "getTextZoom", new Class[0]);
        this.setDefaultFontSizeintMethod.init(this.wrapper, null, "setDefaultFontSize", new Class[]{Integer.TYPE});
        this.getDefaultFontSizeMethod.init(this.wrapper, null, "getDefaultFontSize", new Class[0]);
        this.setDefaultFixedFontSizeintMethod.init(this.wrapper, null, "setDefaultFixedFontSize", new Class[]{Integer.TYPE});
        this.getDefaultFixedFontSizeMethod.init(this.wrapper, null, "getDefaultFixedFontSize", new Class[0]);
        this.setSupportZoombooleanMethod.init(this.wrapper, null, "setSupportZoom", new Class[]{Boolean.TYPE});
        this.supportZoomMethod.init(this.wrapper, null, "supportZoom", new Class[0]);
        this.setBuiltInZoomControlsbooleanMethod.init(this.wrapper, null, "setBuiltInZoomControls", new Class[]{Boolean.TYPE});
        this.getBuiltInZoomControlsMethod.init(this.wrapper, null, "getBuiltInZoomControls", new Class[0]);
        this.supportsMultiTouchZoomForTestMethod.init(this.wrapper, null, "supportsMultiTouchZoomForTest", new Class[0]);
        this.setSupportSpatialNavigationbooleanMethod.init(this.wrapper, null, "setSupportSpatialNavigation", new Class[]{Boolean.TYPE});
        this.getSupportSpatialNavigationMethod.init(this.wrapper, null, "getSupportSpatialNavigation", new Class[0]);
        this.setSupportQuirksModebooleanMethod.init(this.wrapper, null, "setSupportQuirksMode", new Class[]{Boolean.TYPE});
        this.getSupportQuirksModeMethod.init(this.wrapper, null, "getSupportQuirksMode", new Class[0]);
        this.setLayoutAlgorithmLayoutAlgorithmInternalMethod.init(this.wrapper, null, "setLayoutAlgorithm", new Class[]{this.coreBridge.getWrapperClass("XWalkSettings$LayoutAlgorithm")});
        this.getLayoutAlgorithmMethod.init(this.wrapper, null, "getLayoutAlgorithm", new Class[0]);
        this.setLoadWithOverviewModebooleanMethod.init(this.wrapper, null, "setLoadWithOverviewMode", new Class[]{Boolean.TYPE});
        this.getLoadWithOverviewModeMethod.init(this.wrapper, null, "getLoadWithOverviewMode", new Class[0]);
        this.setAppCacheEnabledbooleanMethod.init(this.wrapper, null, "setAppCacheEnabled", new Class[]{Boolean.TYPE});
        this.setAppCachePathStringMethod.init(this.wrapper, null, "setAppCachePath", new Class[]{String.class});
        this.SetJSExceptionCallBackXWalkJSExceptionListenerInternalMethod.init(this.wrapper, null, "SetJsExceptionCallBack", new Class[]{this.coreBridge.getWrapperClass("XWalkJSExceptionListener")});
    }

    public void setAcceptLanguages(String arg4) {
        if(this.setAcceptLanguagesStringMethod == null || (this.setAcceptLanguagesStringMethod.isNull())) {
            this.setAcceptLanguagesSuper(arg4);
        }
        else {
            this.setAcceptLanguagesStringMethod.invoke(new Object[]{arg4});
        }
    }

    public void setAcceptLanguagesSuper(String arg2) {
        if(this.internal == null) {
            super.setAcceptLanguages(arg2);
        }
        else {
            this.internal.setAcceptLanguages(arg2);
        }
    }

    public void setAllowContentAccess(boolean arg4) {
        if(this.setAllowContentAccessbooleanMethod == null || (this.setAllowContentAccessbooleanMethod.isNull())) {
            this.setAllowContentAccessSuper(arg4);
        }
        else {
            this.setAllowContentAccessbooleanMethod.invoke(new Object[]{Boolean.valueOf(arg4)});
        }
    }

    public void setAllowContentAccessSuper(boolean arg2) {
        if(this.internal == null) {
            super.setAllowContentAccess(arg2);
        }
        else {
            this.internal.setAllowContentAccess(arg2);
        }
    }

    public void setAllowFileAccess(boolean arg4) {
        if(this.setAllowFileAccessbooleanMethod == null || (this.setAllowFileAccessbooleanMethod.isNull())) {
            this.setAllowFileAccessSuper(arg4);
        }
        else {
            this.setAllowFileAccessbooleanMethod.invoke(new Object[]{Boolean.valueOf(arg4)});
        }
    }

    public void setAllowFileAccessFromFileURLs(boolean arg4) {
        if(this.setAllowFileAccessFromFileURLsbooleanMethod == null || (this.setAllowFileAccessFromFileURLsbooleanMethod.isNull())) {
            this.setAllowFileAccessFromFileURLsSuper(arg4);
        }
        else {
            this.setAllowFileAccessFromFileURLsbooleanMethod.invoke(new Object[]{Boolean.valueOf(arg4)});
        }
    }

    public void setAllowFileAccessFromFileURLsSuper(boolean arg2) {
        if(this.internal == null) {
            super.setAllowFileAccessFromFileURLs(arg2);
        }
        else {
            this.internal.setAllowFileAccessFromFileURLs(arg2);
        }
    }

    public void setAllowFileAccessSuper(boolean arg2) {
        if(this.internal == null) {
            super.setAllowFileAccess(arg2);
        }
        else {
            this.internal.setAllowFileAccess(arg2);
        }
    }

    public void setAllowUniversalAccessFromFileURLs(boolean arg4) {
        if(this.setAllowUniversalAccessFromFileURLsbooleanMethod == null || (this.setAllowUniversalAccessFromFileURLsbooleanMethod.isNull())) {
            this.setAllowUniversalAccessFromFileURLsSuper(arg4);
        }
        else {
            this.setAllowUniversalAccessFromFileURLsbooleanMethod.invoke(new Object[]{Boolean.valueOf(arg4)});
        }
    }

    public void setAllowUniversalAccessFromFileURLsSuper(boolean arg2) {
        if(this.internal == null) {
            super.setAllowUniversalAccessFromFileURLs(arg2);
        }
        else {
            this.internal.setAllowUniversalAccessFromFileURLs(arg2);
        }
    }

    public void setAppCacheEnabled(boolean arg4) {
        if(this.setAppCacheEnabledbooleanMethod == null || (this.setAppCacheEnabledbooleanMethod.isNull())) {
            this.setAppCacheEnabledSuper(arg4);
        }
        else {
            this.setAppCacheEnabledbooleanMethod.invoke(new Object[]{Boolean.valueOf(arg4)});
        }
    }

    public void setAppCacheEnabledSuper(boolean arg2) {
        if(this.internal == null) {
            super.setAppCacheEnabled(arg2);
        }
        else {
            this.internal.setAppCacheEnabled(arg2);
        }
    }

    public void setAppCachePath(String arg4) {
        if(this.setAppCachePathStringMethod == null || (this.setAppCachePathStringMethod.isNull())) {
            this.setAppCachePathSuper(arg4);
        }
        else {
            this.setAppCachePathStringMethod.invoke(new Object[]{arg4});
        }
    }

    public void setAppCachePathSuper(String arg2) {
        if(this.internal == null) {
            super.setAppCachePath(arg2);
        }
        else {
            this.internal.setAppCachePath(arg2);
        }
    }

    public void setBlockNetworkImage(boolean arg4) {
        if(this.setBlockNetworkImagebooleanMethod == null || (this.setBlockNetworkImagebooleanMethod.isNull())) {
            this.setBlockNetworkImageSuper(arg4);
        }
        else {
            this.setBlockNetworkImagebooleanMethod.invoke(new Object[]{Boolean.valueOf(arg4)});
        }
    }

    public void setBlockNetworkImageSuper(boolean arg2) {
        if(this.internal == null) {
            super.setBlockNetworkImage(arg2);
        }
        else {
            this.internal.setBlockNetworkImage(arg2);
        }
    }

    public void setBlockNetworkLoads(boolean arg4) {
        if(this.setBlockNetworkLoadsbooleanMethod == null || (this.setBlockNetworkLoadsbooleanMethod.isNull())) {
            this.setBlockNetworkLoadsSuper(arg4);
        }
        else {
            this.setBlockNetworkLoadsbooleanMethod.invoke(new Object[]{Boolean.valueOf(arg4)});
        }
    }

    public void setBlockNetworkLoadsSuper(boolean arg2) {
        if(this.internal == null) {
            super.setBlockNetworkLoads(arg2);
        }
        else {
            this.internal.setBlockNetworkLoads(arg2);
        }
    }

    public void setBuiltInZoomControls(boolean arg4) {
        if(this.setBuiltInZoomControlsbooleanMethod == null || (this.setBuiltInZoomControlsbooleanMethod.isNull())) {
            this.setBuiltInZoomControlsSuper(arg4);
        }
        else {
            this.setBuiltInZoomControlsbooleanMethod.invoke(new Object[]{Boolean.valueOf(arg4)});
        }
    }

    public void setBuiltInZoomControlsSuper(boolean arg2) {
        if(this.internal == null) {
            super.setBuiltInZoomControls(arg2);
        }
        else {
            this.internal.setBuiltInZoomControls(arg2);
        }
    }

    public void setCacheMode(int arg4) {
        if(this.setCacheModeintMethod == null || (this.setCacheModeintMethod.isNull())) {
            this.setCacheModeSuper(arg4);
        }
        else {
            this.setCacheModeintMethod.invoke(new Object[]{Integer.valueOf(arg4)});
        }
    }

    public void setCacheModeSuper(int arg2) {
        if(this.internal == null) {
            super.setCacheMode(arg2);
        }
        else {
            this.internal.setCacheMode(arg2);
        }
    }

    public void setCursiveFontFamily(String arg4) {
        if(this.setCursiveFontFamilyStringMethod == null || (this.setCursiveFontFamilyStringMethod.isNull())) {
            this.setCursiveFontFamilySuper(arg4);
        }
        else {
            this.setCursiveFontFamilyStringMethod.invoke(new Object[]{arg4});
        }
    }

    public void setCursiveFontFamilySuper(String arg2) {
        if(this.internal == null) {
            super.setCursiveFontFamily(arg2);
        }
        else {
            this.internal.setCursiveFontFamily(arg2);
        }
    }

    public void setDatabaseEnabled(boolean arg4) {
        if(this.setDatabaseEnabledbooleanMethod == null || (this.setDatabaseEnabledbooleanMethod.isNull())) {
            this.setDatabaseEnabledSuper(arg4);
        }
        else {
            this.setDatabaseEnabledbooleanMethod.invoke(new Object[]{Boolean.valueOf(arg4)});
        }
    }

    public void setDatabaseEnabledSuper(boolean arg2) {
        if(this.internal == null) {
            super.setDatabaseEnabled(arg2);
        }
        else {
            this.internal.setDatabaseEnabled(arg2);
        }
    }

    public void setDefaultFixedFontSize(int arg4) {
        if(this.setDefaultFixedFontSizeintMethod == null || (this.setDefaultFixedFontSizeintMethod.isNull())) {
            this.setDefaultFixedFontSizeSuper(arg4);
        }
        else {
            this.setDefaultFixedFontSizeintMethod.invoke(new Object[]{Integer.valueOf(arg4)});
        }
    }

    public void setDefaultFixedFontSizeSuper(int arg2) {
        if(this.internal == null) {
            super.setDefaultFixedFontSize(arg2);
        }
        else {
            this.internal.setDefaultFixedFontSize(arg2);
        }
    }

    public void setDefaultFontSize(int arg4) {
        if(this.setDefaultFontSizeintMethod == null || (this.setDefaultFontSizeintMethod.isNull())) {
            this.setDefaultFontSizeSuper(arg4);
        }
        else {
            this.setDefaultFontSizeintMethod.invoke(new Object[]{Integer.valueOf(arg4)});
        }
    }

    public void setDefaultFontSizeSuper(int arg2) {
        if(this.internal == null) {
            super.setDefaultFontSize(arg2);
        }
        else {
            this.internal.setDefaultFontSize(arg2);
        }
    }

    public void setDomStorageEnabled(boolean arg4) {
        if(this.setDomStorageEnabledbooleanMethod == null || (this.setDomStorageEnabledbooleanMethod.isNull())) {
            this.setDomStorageEnabledSuper(arg4);
        }
        else {
            this.setDomStorageEnabledbooleanMethod.invoke(new Object[]{Boolean.valueOf(arg4)});
        }
    }

    public void setDomStorageEnabledSuper(boolean arg2) {
        if(this.internal == null) {
            super.setDomStorageEnabled(arg2);
        }
        else {
            this.internal.setDomStorageEnabled(arg2);
        }
    }

    public void setFantasyFontFamily(String arg4) {
        if(this.setFantasyFontFamilyStringMethod == null || (this.setFantasyFontFamilyStringMethod.isNull())) {
            this.setFantasyFontFamilySuper(arg4);
        }
        else {
            this.setFantasyFontFamilyStringMethod.invoke(new Object[]{arg4});
        }
    }

    public void setFantasyFontFamilySuper(String arg2) {
        if(this.internal == null) {
            super.setFantasyFontFamily(arg2);
        }
        else {
            this.internal.setFantasyFontFamily(arg2);
        }
    }

    public void setFixedFontFamily(String arg4) {
        if(this.setFixedFontFamilyStringMethod == null || (this.setFixedFontFamilyStringMethod.isNull())) {
            this.setFixedFontFamilySuper(arg4);
        }
        else {
            this.setFixedFontFamilyStringMethod.invoke(new Object[]{arg4});
        }
    }

    public void setFixedFontFamilySuper(String arg2) {
        if(this.internal == null) {
            super.setFixedFontFamily(arg2);
        }
        else {
            this.internal.setFixedFontFamily(arg2);
        }
    }

    public void setInitialPageScale(float arg4) {
        if(this.setInitialPageScalefloatMethod == null || (this.setInitialPageScalefloatMethod.isNull())) {
            this.setInitialPageScaleSuper(arg4);
        }
        else {
            this.setInitialPageScalefloatMethod.invoke(new Object[]{Float.valueOf(arg4)});
        }
    }

    public void setInitialPageScaleSuper(float arg2) {
        if(this.internal == null) {
            super.setInitialPageScale(arg2);
        }
        else {
            this.internal.setInitialPageScale(arg2);
        }
    }

    public void setJavaScriptCanOpenWindowsAutomatically(boolean arg4) {
        if(this.setJavaScriptCanOpenWindowsAutomaticallybooleanMethod == null || (this.setJavaScriptCanOpenWindowsAutomaticallybooleanMethod.isNull())) {
            this.setJavaScriptCanOpenWindowsAutomaticallySuper(arg4);
        }
        else {
            this.setJavaScriptCanOpenWindowsAutomaticallybooleanMethod.invoke(new Object[]{Boolean.valueOf(arg4)});
        }
    }

    public void setJavaScriptCanOpenWindowsAutomaticallySuper(boolean arg2) {
        if(this.internal == null) {
            super.setJavaScriptCanOpenWindowsAutomatically(arg2);
        }
        else {
            this.internal.setJavaScriptCanOpenWindowsAutomatically(arg2);
        }
    }

    public void setJavaScriptEnabled(boolean arg4) {
        if(this.setJavaScriptEnabledbooleanMethod == null || (this.setJavaScriptEnabledbooleanMethod.isNull())) {
            this.setJavaScriptEnabledSuper(arg4);
        }
        else {
            this.setJavaScriptEnabledbooleanMethod.invoke(new Object[]{Boolean.valueOf(arg4)});
        }
    }

    public void setJavaScriptEnabledSuper(boolean arg2) {
        if(this.internal == null) {
            super.setJavaScriptEnabled(arg2);
        }
        else {
            this.internal.setJavaScriptEnabled(arg2);
        }
    }

    public void setLayoutAlgorithm(LayoutAlgorithmInternal arg4) {
        if(this.setLayoutAlgorithmLayoutAlgorithmInternalMethod == null || (this.setLayoutAlgorithmLayoutAlgorithmInternalMethod.isNull())) {
            this.setLayoutAlgorithmSuper(arg4);
        }
        else {
            this.setLayoutAlgorithmLayoutAlgorithmInternalMethod.invoke(new Object[]{this.ConvertLayoutAlgorithmInternal(arg4)});
        }
    }

    public void setLayoutAlgorithmSuper(LayoutAlgorithmInternal arg2) {
        if(this.internal == null) {
            super.setLayoutAlgorithm(arg2);
        }
        else {
            this.internal.setLayoutAlgorithm(arg2);
        }
    }

    public void setLoadWithOverviewMode(boolean arg4) {
        if(this.setLoadWithOverviewModebooleanMethod == null || (this.setLoadWithOverviewModebooleanMethod.isNull())) {
            this.setLoadWithOverviewModeSuper(arg4);
        }
        else {
            this.setLoadWithOverviewModebooleanMethod.invoke(new Object[]{Boolean.valueOf(arg4)});
        }
    }

    public void setLoadWithOverviewModeSuper(boolean arg2) {
        if(this.internal == null) {
            super.setLoadWithOverviewMode(arg2);
        }
        else {
            this.internal.setLoadWithOverviewMode(arg2);
        }
    }

    public void setLoadsImagesAutomatically(boolean arg4) {
        if(this.setLoadsImagesAutomaticallybooleanMethod == null || (this.setLoadsImagesAutomaticallybooleanMethod.isNull())) {
            this.setLoadsImagesAutomaticallySuper(arg4);
        }
        else {
            this.setLoadsImagesAutomaticallybooleanMethod.invoke(new Object[]{Boolean.valueOf(arg4)});
        }
    }

    public void setLoadsImagesAutomaticallySuper(boolean arg2) {
        if(this.internal == null) {
            super.setLoadsImagesAutomatically(arg2);
        }
        else {
            this.internal.setLoadsImagesAutomatically(arg2);
        }
    }

    public void setMediaPlaybackRequiresUserGesture(boolean arg4) {
        if(this.setMediaPlaybackRequiresUserGesturebooleanMethod == null || (this.setMediaPlaybackRequiresUserGesturebooleanMethod.isNull())) {
            this.setMediaPlaybackRequiresUserGestureSuper(arg4);
        }
        else {
            this.setMediaPlaybackRequiresUserGesturebooleanMethod.invoke(new Object[]{Boolean.valueOf(arg4)});
        }
    }

    public void setMediaPlaybackRequiresUserGestureSuper(boolean arg2) {
        if(this.internal == null) {
            super.setMediaPlaybackRequiresUserGesture(arg2);
        }
        else {
            this.internal.setMediaPlaybackRequiresUserGesture(arg2);
        }
    }

    public void setMinimumFontSize(int arg4) {
        if(this.setMinimumFontSizeintMethod == null || (this.setMinimumFontSizeintMethod.isNull())) {
            this.setMinimumFontSizeSuper(arg4);
        }
        else {
            this.setMinimumFontSizeintMethod.invoke(new Object[]{Integer.valueOf(arg4)});
        }
    }

    public void setMinimumFontSizeSuper(int arg2) {
        if(this.internal == null) {
            super.setMinimumFontSize(arg2);
        }
        else {
            this.internal.setMinimumFontSize(arg2);
        }
    }

    public void setMinimumLogicalFontSize(int arg4) {
        if(this.setMinimumLogicalFontSizeintMethod == null || (this.setMinimumLogicalFontSizeintMethod.isNull())) {
            this.setMinimumLogicalFontSizeSuper(arg4);
        }
        else {
            this.setMinimumLogicalFontSizeintMethod.invoke(new Object[]{Integer.valueOf(arg4)});
        }
    }

    public void setMinimumLogicalFontSizeSuper(int arg2) {
        if(this.internal == null) {
            super.setMinimumLogicalFontSize(arg2);
        }
        else {
            this.internal.setMinimumLogicalFontSize(arg2);
        }
    }

    public void setSansSerifFontFamily(String arg4) {
        if(this.setSansSerifFontFamilyStringMethod == null || (this.setSansSerifFontFamilyStringMethod.isNull())) {
            this.setSansSerifFontFamilySuper(arg4);
        }
        else {
            this.setSansSerifFontFamilyStringMethod.invoke(new Object[]{arg4});
        }
    }

    public void setSansSerifFontFamilySuper(String arg2) {
        if(this.internal == null) {
            super.setSansSerifFontFamily(arg2);
        }
        else {
            this.internal.setSansSerifFontFamily(arg2);
        }
    }

    public void setSaveFormData(boolean arg4) {
        if(this.setSaveFormDatabooleanMethod == null || (this.setSaveFormDatabooleanMethod.isNull())) {
            this.setSaveFormDataSuper(arg4);
        }
        else {
            this.setSaveFormDatabooleanMethod.invoke(new Object[]{Boolean.valueOf(arg4)});
        }
    }

    public void setSaveFormDataSuper(boolean arg2) {
        if(this.internal == null) {
            super.setSaveFormData(arg2);
        }
        else {
            this.internal.setSaveFormData(arg2);
        }
    }

    public void setSerifFontFamily(String arg4) {
        if(this.setSerifFontFamilyStringMethod == null || (this.setSerifFontFamilyStringMethod.isNull())) {
            this.setSerifFontFamilySuper(arg4);
        }
        else {
            this.setSerifFontFamilyStringMethod.invoke(new Object[]{arg4});
        }
    }

    public void setSerifFontFamilySuper(String arg2) {
        if(this.internal == null) {
            super.setSerifFontFamily(arg2);
        }
        else {
            this.internal.setSerifFontFamily(arg2);
        }
    }

    public void setStandardFontFamily(String arg4) {
        if(this.setStandardFontFamilyStringMethod == null || (this.setStandardFontFamilyStringMethod.isNull())) {
            this.setStandardFontFamilySuper(arg4);
        }
        else {
            this.setStandardFontFamilyStringMethod.invoke(new Object[]{arg4});
        }
    }

    public void setStandardFontFamilySuper(String arg2) {
        if(this.internal == null) {
            super.setStandardFontFamily(arg2);
        }
        else {
            this.internal.setStandardFontFamily(arg2);
        }
    }

    public void setSupportMultipleWindows(boolean arg4) {
        if(this.setSupportMultipleWindowsbooleanMethod == null || (this.setSupportMultipleWindowsbooleanMethod.isNull())) {
            this.setSupportMultipleWindowsSuper(arg4);
        }
        else {
            this.setSupportMultipleWindowsbooleanMethod.invoke(new Object[]{Boolean.valueOf(arg4)});
        }
    }

    public void setSupportMultipleWindowsSuper(boolean arg2) {
        if(this.internal == null) {
            super.setSupportMultipleWindows(arg2);
        }
        else {
            this.internal.setSupportMultipleWindows(arg2);
        }
    }

    public void setSupportQuirksMode(boolean arg4) {
        if(this.setSupportQuirksModebooleanMethod == null || (this.setSupportQuirksModebooleanMethod.isNull())) {
            this.setSupportQuirksModeSuper(arg4);
        }
        else {
            this.setSupportQuirksModebooleanMethod.invoke(new Object[]{Boolean.valueOf(arg4)});
        }
    }

    public void setSupportQuirksModeSuper(boolean arg2) {
        if(this.internal == null) {
            super.setSupportQuirksMode(arg2);
        }
        else {
            this.internal.setSupportQuirksMode(arg2);
        }
    }

    public void setSupportSpatialNavigation(boolean arg4) {
        if(this.setSupportSpatialNavigationbooleanMethod == null || (this.setSupportSpatialNavigationbooleanMethod.isNull())) {
            this.setSupportSpatialNavigationSuper(arg4);
        }
        else {
            this.setSupportSpatialNavigationbooleanMethod.invoke(new Object[]{Boolean.valueOf(arg4)});
        }
    }

    public void setSupportSpatialNavigationSuper(boolean arg2) {
        if(this.internal == null) {
            super.setSupportSpatialNavigation(arg2);
        }
        else {
            this.internal.setSupportSpatialNavigation(arg2);
        }
    }

    public void setSupportZoom(boolean arg4) {
        if(this.setSupportZoombooleanMethod == null || (this.setSupportZoombooleanMethod.isNull())) {
            this.setSupportZoomSuper(arg4);
        }
        else {
            this.setSupportZoombooleanMethod.invoke(new Object[]{Boolean.valueOf(arg4)});
        }
    }

    public void setSupportZoomSuper(boolean arg2) {
        if(this.internal == null) {
            super.setSupportZoom(arg2);
        }
        else {
            this.internal.setSupportZoom(arg2);
        }
    }

    public void setTextZoom(int arg4) {
        if(this.setTextZoomintMethod == null || (this.setTextZoomintMethod.isNull())) {
            this.setTextZoomSuper(arg4);
        }
        else {
            this.setTextZoomintMethod.invoke(new Object[]{Integer.valueOf(arg4)});
        }
    }

    public void setTextZoomSuper(int arg2) {
        if(this.internal == null) {
            super.setTextZoom(arg2);
        }
        else {
            this.internal.setTextZoom(arg2);
        }
    }

    public void setUseWideViewPort(boolean arg4) {
        if(this.setUseWideViewPortbooleanMethod == null || (this.setUseWideViewPortbooleanMethod.isNull())) {
            this.setUseWideViewPortSuper(arg4);
        }
        else {
            this.setUseWideViewPortbooleanMethod.invoke(new Object[]{Boolean.valueOf(arg4)});
        }
    }

    public void setUseWideViewPortSuper(boolean arg2) {
        if(this.internal == null) {
            super.setUseWideViewPort(arg2);
        }
        else {
            this.internal.setUseWideViewPort(arg2);
        }
    }

    public void setUserAgentString(String arg4) {
        if(this.setUserAgentStringStringMethod == null || (this.setUserAgentStringStringMethod.isNull())) {
            this.setUserAgentStringSuper(arg4);
        }
        else {
            this.setUserAgentStringStringMethod.invoke(new Object[]{arg4});
        }
    }

    public void setUserAgentStringSuper(String arg2) {
        if(this.internal == null) {
            super.setUserAgentString(arg2);
        }
        else {
            this.internal.setUserAgentString(arg2);
        }
    }

    public void setUsingForAppBrand(int arg4) {
        if(this.setUsingForAppBrandbooleanMethod == null || (this.setUsingForAppBrandbooleanMethod.isNull())) {
            this.setUsingForAppBrandSuper(arg4);
        }
        else {
            this.setUsingForAppBrandbooleanMethod.invoke(new Object[]{Integer.valueOf(arg4)});
        }
    }

    public void setUsingForAppBrandSuper(int arg2) {
        if(this.internal == null) {
            super.setUsingForAppBrand(arg2);
        }
        else {
            this.internal.setUsingForAppBrand(arg2);
        }
    }

    public boolean supportMultipleWindows() {
        if(this.supportMultipleWindowsMethod != null) {
            if(this.supportMultipleWindowsMethod.isNull()) {
            }
            else {
                return this.supportMultipleWindowsMethod.invoke(new Object[0]).booleanValue();
            }
        }

        return this.supportMultipleWindowsSuper();
    }

    public boolean supportMultipleWindowsSuper() {
        boolean v0 = this.internal == null ? super.supportMultipleWindows() : this.internal.supportMultipleWindows();
        return v0;
    }

    public boolean supportZoom() {
        if(this.supportZoomMethod != null) {
            if(this.supportZoomMethod.isNull()) {
            }
            else {
                return this.supportZoomMethod.invoke(new Object[0]).booleanValue();
            }
        }

        return this.supportZoomSuper();
    }

    public boolean supportZoomSuper() {
        boolean v0 = this.internal == null ? super.supportZoom() : this.internal.supportZoom();
        return v0;
    }

    public boolean supportsMultiTouchZoomForTest() {
        if(this.supportsMultiTouchZoomForTestMethod != null) {
            if(this.supportsMultiTouchZoomForTestMethod.isNull()) {
            }
            else {
                return this.supportsMultiTouchZoomForTestMethod.invoke(new Object[0]).booleanValue();
            }
        }

        return this.supportsMultiTouchZoomForTestSuper();
    }

    public boolean supportsMultiTouchZoomForTestSuper() {
        boolean v0 = this.internal == null ? super.supportsMultiTouchZoomForTest() : this.internal.supportsMultiTouchZoomForTest();
        return v0;
    }
}

