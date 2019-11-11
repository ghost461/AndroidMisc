package org.chromium.network.mojom;

import org.chromium.mojo.bindings.Callbacks$Callback0;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;
import org.chromium.mojo.system.DataPipe$ConsumerHandle;

public interface UrlLoaderClient extends Interface {
    public interface OnUploadProgressResponse extends Callback0 {
    }

    public interface Proxy extends org.chromium.mojo.bindings.Interface$Proxy, UrlLoaderClient {
    }

    public static final Manager MANAGER;

    static {
        UrlLoaderClient.MANAGER = UrlLoaderClient_Internal.MANAGER;
    }

    void onComplete(UrlLoaderCompletionStatus arg1);

    void onDataDownloaded(long arg1, long arg2);

    void onReceiveCachedMetadata(byte[] arg1);

    void onReceiveRedirect(UrlRequestRedirectInfo arg1, UrlResponseHead arg2);

    void onReceiveResponse(UrlResponseHead arg1, DownloadedTempFile arg2);

    void onStartLoadingResponseBody(ConsumerHandle arg1);

    void onTransferSizeUpdated(int arg1);

    void onUploadProgress(long arg1, long arg2, OnUploadProgressResponse arg3);
}

