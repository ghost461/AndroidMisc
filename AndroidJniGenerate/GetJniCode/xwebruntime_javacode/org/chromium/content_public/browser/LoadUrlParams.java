package org.chromium.content_public.browser;

import java.util.Iterator;
import java.util.Locale;
import java.util.Map$Entry;
import java.util.Map;
import org.chromium.base.VisibleForTesting;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.content_public.common.Referrer;
import org.chromium.content_public.common.ResourceRequestBody;

@JNINamespace(value="content") public class LoadUrlParams {
    String mBaseUrlForDataUrl;
    boolean mCanLoadLocalResources;
    String mDataUrlAsString;
    private Map mExtraHeaders;
    boolean mHasUserGesture;
    long mIntentReceivedTimestamp;
    boolean mIsRendererInitiated;
    int mLoadUrlType;
    ResourceRequestBody mPostData;
    Referrer mReferrer;
    boolean mShouldClearHistoryList;
    boolean mShouldReplaceCurrentEntry;
    int mTransitionType;
    int mUaOverrideOption;
    String mUrl;
    private String mVerbatimHeaders;
    String mVirtualUrlForDataUrl;

    public LoadUrlParams(String arg2) {
        this(arg2, 0);
    }

    public LoadUrlParams(String arg1, int arg2) {
        super();
        this.mUrl = arg1;
        this.mTransitionType = arg2;
        this.mLoadUrlType = 0;
        this.mUaOverrideOption = 0;
        this.mPostData = null;
        this.mBaseUrlForDataUrl = null;
        this.mVirtualUrlForDataUrl = null;
        this.mDataUrlAsString = null;
    }

    private static String buildDataUri(String arg2, String arg3, boolean arg4, String arg5) {
        StringBuilder v0 = new StringBuilder("data:");
        v0.append(arg3);
        if(arg5 != null && !arg5.isEmpty()) {
            v0.append(";charset=" + arg5);
        }

        if(arg4) {
            v0.append(";base64");
        }

        v0.append(",");
        v0.append(arg2);
        return v0.toString();
    }

    public static LoadUrlParams createLoadDataParams(String arg1, String arg2, boolean arg3) {
        return LoadUrlParams.createLoadDataParams(arg1, arg2, arg3, null);
    }

    public static LoadUrlParams createLoadDataParams(String arg1, String arg2, boolean arg3, String arg4) {
        LoadUrlParams v0 = new LoadUrlParams(LoadUrlParams.buildDataUri(arg1, arg2, arg3, arg4));
        v0.setLoadType(2);
        v0.setTransitionType(1);
        return v0;
    }

    public static LoadUrlParams createLoadDataParamsWithBaseUrl(String arg6, String arg7, boolean arg8, String arg9, String arg10) {
        return LoadUrlParams.createLoadDataParamsWithBaseUrl(arg6, arg7, arg8, arg9, arg10, null);
    }

    public static LoadUrlParams createLoadDataParamsWithBaseUrl(String arg2, String arg3, boolean arg4, String arg5, String arg6, String arg7) {
        LoadUrlParams v2;
        if(arg5 == null || !arg5.toLowerCase(Locale.US).startsWith("data:")) {
            LoadUrlParams v0 = LoadUrlParams.createLoadDataParams("", arg3, arg4, arg7);
            if(arg5 != null) {
            }
            else {
                arg5 = "about:blank";
            }

            v0.setBaseUrlForDataUrl(arg5);
            if(arg6 != null) {
            }
            else {
                arg6 = "about:blank";
            }

            v0.setVirtualUrlForDataUrl(arg6);
            v0.setDataUrlAsString(LoadUrlParams.buildDataUri(arg2, arg3, arg4, arg7));
            v2 = v0;
        }
        else {
            v2 = LoadUrlParams.createLoadDataParams(arg2, arg3, arg4, arg7);
        }

        return v2;
    }

    @VisibleForTesting public static LoadUrlParams createLoadHttpPostParams(String arg1, byte[] arg2) {
        LoadUrlParams v0 = new LoadUrlParams(arg1);
        v0.setLoadType(1);
        v0.setTransitionType(1);
        v0.setPostData(ResourceRequestBody.createFromBytes(arg2));
        return v0;
    }

    public String getBaseUrl() {
        return this.mBaseUrlForDataUrl;
    }

    public boolean getCanLoadLocalResources() {
        return this.mCanLoadLocalResources;
    }

    public String getDataUrlAsString() {
        return this.mDataUrlAsString;
    }

    public Map getExtraHeaders() {
        return this.mExtraHeaders;
    }

    private String getExtraHeadersString(String arg6, boolean arg7) {
        if(this.mExtraHeaders == null) {
            return null;
        }

        StringBuilder v0 = new StringBuilder();
        Iterator v1 = this.mExtraHeaders.entrySet().iterator();
        while(v1.hasNext()) {
            Object v2 = v1.next();
            if(v0.length() > 0) {
                v0.append(arg6);
            }

            v0.append(((Map$Entry)v2).getKey().toLowerCase(Locale.US));
            v0.append(":");
            v0.append(((Map$Entry)v2).getValue());
        }

        if(arg7) {
            v0.append(arg6);
        }

        return v0.toString();
    }

    public String getExtraHeadersString() {
        return this.getExtraHeadersString("\n", false);
    }

    public String getExtraHttpRequestHeadersString() {
        return this.getExtraHeadersString("\r\n", true);
    }

    public boolean getHasUserGesture() {
        return this.mHasUserGesture;
    }

    public long getIntentReceivedTimestamp() {
        return this.mIntentReceivedTimestamp;
    }

    public boolean getIsRendererInitiated() {
        return this.mIsRendererInitiated;
    }

    public int getLoadUrlType() {
        return this.mLoadUrlType;
    }

    public ResourceRequestBody getPostData() {
        return this.mPostData;
    }

    public Referrer getReferrer() {
        return this.mReferrer;
    }

    public boolean getShouldClearHistoryList() {
        return this.mShouldClearHistoryList;
    }

    public boolean getShouldReplaceCurrentEntry() {
        return this.mShouldReplaceCurrentEntry;
    }

    public int getTransitionType() {
        return this.mTransitionType;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public int getUserAgentOverrideOption() {
        return this.mUaOverrideOption;
    }

    public String getVerbatimHeaders() {
        return this.mVerbatimHeaders;
    }

    public String getVirtualUrlForDataUrl() {
        return this.mVirtualUrlForDataUrl;
    }

    public boolean isBaseUrlDataScheme() {
        if(this.mBaseUrlForDataUrl == null && this.mLoadUrlType == 2) {
            return 1;
        }

        return LoadUrlParams.nativeIsDataScheme(this.mBaseUrlForDataUrl);
    }

    private static native boolean nativeIsDataScheme(String arg0) {
    }

    public void setBaseUrlForDataUrl(String arg1) {
        this.mBaseUrlForDataUrl = arg1;
    }

    public void setCanLoadLocalResources(boolean arg1) {
        this.mCanLoadLocalResources = arg1;
    }

    public void setDataUrlAsString(String arg1) {
        this.mDataUrlAsString = arg1;
    }

    public void setExtraHeaders(Map arg1) {
        this.mExtraHeaders = arg1;
    }

    public void setHasUserGesture(boolean arg1) {
        this.mHasUserGesture = arg1;
    }

    public void setIntentReceivedTimestamp(long arg1) {
        this.mIntentReceivedTimestamp = arg1;
    }

    public void setIsRendererInitiated(boolean arg1) {
        this.mIsRendererInitiated = arg1;
    }

    public void setLoadType(int arg1) {
        this.mLoadUrlType = arg1;
    }

    public void setOverrideUserAgent(int arg1) {
        this.mUaOverrideOption = arg1;
    }

    public void setPostData(ResourceRequestBody arg1) {
        this.mPostData = arg1;
    }

    public void setReferrer(Referrer arg1) {
        this.mReferrer = arg1;
    }

    public void setShouldClearHistoryList(boolean arg1) {
        this.mShouldClearHistoryList = arg1;
    }

    public void setShouldReplaceCurrentEntry(boolean arg1) {
        this.mShouldReplaceCurrentEntry = arg1;
    }

    public void setTransitionType(int arg1) {
        this.mTransitionType = arg1;
    }

    public void setUrl(String arg1) {
        this.mUrl = arg1;
    }

    public void setVerbatimHeaders(String arg1) {
        this.mVerbatimHeaders = arg1;
    }

    public void setVirtualUrlForDataUrl(String arg1) {
        this.mVirtualUrlForDataUrl = arg1;
    }
}

