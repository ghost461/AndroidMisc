package org.xwalk.core.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.tencent.xweb.xprofile.XProfileManager;

class XWalkContentsClientCallbackHelper {
    class org.xwalk.core.internal.XWalkContentsClientCallbackHelper$1 extends Handler {
        org.xwalk.core.internal.XWalkContentsClientCallbackHelper$1(XWalkContentsClientCallbackHelper arg1, Looper arg2) {
            XWalkContentsClientCallbackHelper.this = arg1;
            super(arg2);
        }

        public void handleMessage(Message arg9) {
            switch(arg9.what) {
                case 1: {
                    goto label_72;
                }
                case 2: {
                    goto label_64;
                }
                case 3: {
                    goto label_54;
                }
                case 4: {
                    goto label_46;
                }
                case 5: {
                    goto label_39;
                }
                case 8: {
                    goto label_32;
                }
                case 9: {
                    goto label_24;
                }
                case 10: {
                    goto label_19;
                }
                case 13: {
                    goto label_12;
                }
            }

            StringBuilder v1 = new StringBuilder();
            v1.append("XWalkContentsClientCallbackHelper: unhandled message ");
            v1.append(arg9.what);
            throw new IllegalStateException(v1.toString());
        label_19:
            XWalkContentsClientCallbackHelper.this.mContentsClient.onReceivedTitle(arg9.obj);
            return;
        label_54:
            XWalkContentsClientCallbackHelper.this.mContentsClient.onDownloadStart(arg9.obj.mUrl, arg9.obj.mUserAgent, arg9.obj.mContentDisposition, arg9.obj.mMimeType, arg9.obj.mContentLength);
            return;
        label_39:
            XWalkContentsClientCallbackHelper.this.mContentsClient.onReceivedError(arg9.obj.mRequest, arg9.obj.mError);
            return;
        label_72:
            XWalkContentsClientCallbackHelper.this.mContentsClient.onLoadResource(arg9.obj);
            return;
        label_24:
            Object v9 = arg9.obj;
            XWalkContentsClientCallbackHelper.this.mContentsClient.onPageFinished(((String)v9));
            XProfileManager.getInstance().sendMessage(4, v9);
            return;
        label_12:
            XWalkContentsClientCallbackHelper.this.mContentsClient.doUpdateVisitedHistory(arg9.obj.mUrl, arg9.obj.mIsReload);
            return;
        label_46:
            XWalkContentsClientCallbackHelper.this.mContentsClient.onReceivedLoginRequest(arg9.obj.mRealm, arg9.obj.mAccount, arg9.obj.mArgs);
            return;
        label_64:
            v9 = arg9.obj;
            XWalkContentsClientCallbackHelper.this.mContentsClient.onPageStarted(((String)v9));
            XProfileManager.getInstance().sendMessage(3, v9);
            return;
        label_32:
            XWalkContentsClientCallbackHelper.this.mContentsClient.onReceivedHttpError(arg9.obj.mRequest, arg9.obj.mResponse);
        }
    }

    public interface CancelCallbackPoller {
        boolean shouldCancelAllCallbacks();
    }

    class DoUpdateVisitedHistoryInfo {
        final boolean mIsReload;
        final String mUrl;

        DoUpdateVisitedHistoryInfo(String arg1, boolean arg2) {
            super();
            this.mUrl = arg1;
            this.mIsReload = arg2;
        }
    }

    class DownloadInfo {
        final String mContentDisposition;
        final long mContentLength;
        final String mMimeType;
        final String mUrl;
        final String mUserAgent;

        DownloadInfo(String arg1, String arg2, String arg3, String arg4, long arg5) {
            super();
            this.mUrl = arg1;
            this.mUserAgent = arg2;
            this.mContentDisposition = arg3;
            this.mMimeType = arg4;
            this.mContentLength = arg5;
        }
    }

    class LoginRequestInfo {
        final String mAccount;
        final String mArgs;
        final String mRealm;

        LoginRequestInfo(String arg1, String arg2, String arg3) {
            super();
            this.mRealm = arg1;
            this.mAccount = arg2;
            this.mArgs = arg3;
        }
    }

    class OnReceivedErrorInfo {
        final WebResourceErrorInner mError;
        final WebResourceRequestInner mRequest;

        OnReceivedErrorInfo(WebResourceRequestInner arg1, WebResourceErrorInner arg2) {
            super();
            this.mRequest = arg1;
            this.mError = arg2;
        }
    }

    class OnReceivedResponseHeadersInfo {
        final WebResourceRequestInner mRequest;
        final XWalkWebResourceResponseInternal mResponse;

        OnReceivedResponseHeadersInfo(WebResourceRequestInner arg1, XWalkWebResourceResponseInternal arg2) {
            super();
            this.mRequest = arg1;
            this.mResponse = arg2;
        }
    }

    private static final int MSG_DO_UPDATE_VISITED_HISTORY = 13;
    private static final int MSG_ON_DOWNLOAD_START = 3;
    private static final int MSG_ON_FORM_RESUBMISSION = 14;
    private static final int MSG_ON_LOAD_RESOURCE = 1;
    private static final int MSG_ON_NEW_PICTURE = 6;
    private static final int MSG_ON_PAGE_FINISHED = 9;
    private static final int MSG_ON_PAGE_STARTED = 2;
    private static final int MSG_ON_PROGRESS_CHANGED = 11;
    private static final int MSG_ON_RECEIVED_ERROR = 5;
    private static final int MSG_ON_RECEIVED_HTTP_ERROR = 8;
    private static final int MSG_ON_RECEIVED_LOGIN_REQUEST = 4;
    private static final int MSG_ON_RECEIVED_TITLE = 10;
    private static final int MSG_ON_SAFE_BROWSING_HIT = 15;
    private static final int MSG_ON_SCALE_CHANGED_SCALED = 7;
    private static final int MSG_SYNTHESIZE_PAGE_LOADING = 12;
    private final XWalkContentsClient mContentsClient;
    private final Handler mHandler;

    public XWalkContentsClientCallbackHelper(XWalkContentsClient arg3) {
        super();
        this.mHandler = new org.xwalk.core.internal.XWalkContentsClientCallbackHelper$1(this, Looper.getMainLooper());
        this.mContentsClient = arg3;
    }

    static XWalkContentsClient access$000(XWalkContentsClientCallbackHelper arg0) {
        return arg0.mContentsClient;
    }

    public void postDoUpdateVisitedHistory(String arg3, boolean arg4) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(13, new DoUpdateVisitedHistoryInfo(arg3, arg4)));
    }

    public void postOnDownloadStart(String arg9, String arg10, String arg11, String arg12, long arg13) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(3, new DownloadInfo(arg9, arg10, arg11, arg12, arg13)));
    }

    public void postOnLoadResource(String arg4) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(1, arg4));
    }

    public void postOnPageFinished(String arg4) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(9, arg4));
    }

    public void postOnPageStarted(String arg4) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(2, arg4));
    }

    public void postOnReceivedError(WebResourceRequestInner arg3, WebResourceErrorInner arg4) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(5, new OnReceivedErrorInfo(arg3, arg4)));
    }

    public void postOnReceivedHttpError(WebResourceRequestInner arg3, XWalkWebResourceResponseInternal arg4) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(8, new OnReceivedResponseHeadersInfo(arg3, arg4)));
    }

    public void postOnReceivedLoginRequest(String arg2, String arg3, String arg4) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(4, new LoginRequestInfo(arg2, arg3, arg4)));
    }

    public void postOnReceivedTitle(String arg4) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(10, arg4));
    }

    void removeCallbacksAndMessages() {
        this.mHandler.removeCallbacksAndMessages(null);
    }
}

