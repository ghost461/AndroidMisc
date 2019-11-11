package org.xwalk.core.internal;

import android.content.Context;
import android.net.http.SslError;
import android.os.Message;

public class XWalkClient {
    private Context mContext;
    private XWalkViewInternal mXWalkView;

    public XWalkClient(XWalkViewInternal arg2) {
        super();
        this.mContext = arg2.getContext();
        this.mXWalkView = arg2;
    }

    public void doUpdateVisitedHistory(XWalkViewInternal arg1, String arg2, boolean arg3) {
    }

    public void onFormResubmission(XWalkViewInternal arg1, Message arg2, Message arg3) {
        arg2.sendToTarget();
    }

    public void onLoadResource(XWalkViewInternal arg1, String arg2) {
    }

    public void onProceededAfterSslError(XWalkViewInternal arg1, SslError arg2) {
    }

    public void onReceivedLoginRequest(XWalkViewInternal arg1, String arg2, String arg3, String arg4) {
    }

    public void onRendererResponsive(XWalkViewInternal arg1) {
    }

    public void onRendererUnresponsive(XWalkViewInternal arg1) {
    }

    @Deprecated public void onTooManyRedirects(XWalkViewInternal arg1, Message arg2, Message arg3) {
        arg2.sendToTarget();
    }
}

