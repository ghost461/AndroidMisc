package org.xwalk.core.internal;

import android.app.AlertDialog$Builder;
import android.content.Context;
import android.content.DialogInterface$OnCancelListener;
import android.content.DialogInterface$OnClickListener;
import android.content.DialogInterface;
import android.net.http.SslError;
import android.os.Build$VERSION;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebResourceResponse;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import java.io.InputStream;
import java.util.Map;

@XWalkAPI(createExternally=true) public class XWalkResourceClientInternal {
    @XWalkAPI public static final int ERROR_AUTHENTICATION = -4;
    @XWalkAPI public static final int ERROR_BAD_URL = -12;
    @XWalkAPI public static final int ERROR_CONNECT = -6;
    @XWalkAPI public static final int ERROR_FAILED_SSL_HANDSHAKE = -11;
    @XWalkAPI public static final int ERROR_FILE = -13;
    @XWalkAPI public static final int ERROR_FILE_NOT_FOUND = -14;
    @XWalkAPI public static final int ERROR_HOST_LOOKUP = -2;
    @XWalkAPI public static final int ERROR_IO = -7;
    @XWalkAPI public static final int ERROR_OK = 0;
    @XWalkAPI public static final int ERROR_PROXY_AUTHENTICATION = -5;
    @XWalkAPI public static final int ERROR_REDIRECT_LOOP = -9;
    @XWalkAPI public static final int ERROR_TIMEOUT = -8;
    @XWalkAPI public static final int ERROR_TOO_MANY_REQUESTS = -15;
    @XWalkAPI public static final int ERROR_UNKNOWN = -1;
    @XWalkAPI public static final int ERROR_UNSUPPORTED_AUTH_SCHEME = -3;
    @XWalkAPI public static final int ERROR_UNSUPPORTED_SCHEME = -10;

    @XWalkAPI public XWalkResourceClientInternal(XWalkViewInternal arg1) {
        super();
    }

    @XWalkAPI public XWalkWebResourceResponseInternal createXWalkWebResourceResponse(String arg2, String arg3, InputStream arg4) {
        return new XWalkWebResourceResponseInternal(arg2, arg3, arg4);
    }

    @XWalkAPI public XWalkWebResourceResponseInternal createXWalkWebResourceResponse(String arg9, String arg10, InputStream arg11, int arg12, String arg13, Map arg14) {
        return new XWalkWebResourceResponseInternal(arg9, arg10, arg11, arg12, arg13, arg14);
    }

    @XWalkAPI public void doUpdateVisitedHistory(XWalkViewInternal arg1, String arg2, boolean arg3) {
    }

    @XWalkAPI public void onDocumentLoadedInFrame(XWalkViewInternal arg1, long arg2) {
    }

    @XWalkAPI public void onLoadFinished(XWalkViewInternal arg1, String arg2) {
    }

    @XWalkAPI public void onLoadResource(XWalkViewInternal arg1, String arg2) {
    }

    @XWalkAPI public void onLoadStarted(XWalkViewInternal arg1, String arg2) {
    }

    @XWalkAPI public void onProgressChanged(XWalkViewInternal arg1, int arg2) {
    }

    @XWalkAPI public void onReceivedClientCertRequest(XWalkViewInternal arg1, ClientCertRequestInternal arg2) {
        arg2.cancel();
    }

    @XWalkAPI public void onReceivedHttpAuthRequest(XWalkViewInternal arg7, XWalkHttpAuthHandlerInternal arg8, String arg9, String arg10) {
        if(arg7 == null) {
            return;
        }

        Context v9 = arg7.getContext();
        LinearLayout v10 = new LinearLayout(v9);
        EditText v0 = new EditText(v9);
        EditText v1 = new EditText(v9);
        v10.setOrientation(1);
        int v3 = 20;
        int v5 = 10;
        if(Build$VERSION.SDK_INT >= 17) {
            v10.setPaddingRelative(v5, 0, v5, v3);
        }
        else {
            v10.setPadding(v5, 0, v5, v3);
        }

        v0.setHint(0x7F0C0055);
        v1.setHint(0x7F0C0053);
        v1.setInputType(0x81);
        v10.addView(((View)v0));
        v10.addView(((View)v1));
        new AlertDialog$Builder(arg7.getContext()).setTitle(0x7F0C0054).setView(((View)v10)).setCancelable(false).setPositiveButton(0x7F0C0052, new DialogInterface$OnClickListener(v0, v1, arg8) {
            public void onClick(DialogInterface arg3, int arg4) {
                this.val$haHandler.proceed(this.val$userNameEditText.getText().toString(), this.val$passwordEditText.getText().toString());
                arg3.dismiss();
            }
        }).setNegativeButton(0x1040000, null).setOnCancelListener(new DialogInterface$OnCancelListener(arg8) {
            public void onCancel(DialogInterface arg1) {
                this.val$haHandler.cancel();
            }
        }).create().show();
    }

    @XWalkAPI public void onReceivedHttpError(XWalkViewInternal arg1, XWalkWebResourceRequestInternal arg2, XWalkWebResourceResponseInternal arg3) {
    }

    @XWalkAPI public void onReceivedLoadError(XWalkViewInternal arg1, int arg2, String arg3, String arg4) {
        Toast.makeText(arg1.getContext(), ((CharSequence)arg3), 0).show();
    }

    @XWalkAPI public void onReceivedLoadError2(XWalkViewInternal arg1, WebResourceRequestInner arg2, WebResourceErrorInner arg3) {
    }

    @XWalkAPI public void onReceivedResponseHeaders(XWalkViewInternal arg1, XWalkWebResourceRequestInternal arg2, XWalkWebResourceResponseInternal arg3) {
    }

    @XWalkAPI public void onReceivedSslError(XWalkViewInternal arg3, ValueCallback arg4, SslError arg5) {
        AlertDialog$Builder v5 = new AlertDialog$Builder(arg3.getContext());
        v5.setTitle(0x7F0C0068).setPositiveButton(0x104000A, new DialogInterface$OnClickListener(arg4) {
            public void onClick(DialogInterface arg2, int arg3) {
                this.val$valueCallback.onReceiveValue(Boolean.valueOf(true));
                arg2.dismiss();
            }
        }).setNegativeButton(0x1040000, new DialogInterface$OnClickListener(arg4) {
            public void onClick(DialogInterface arg2, int arg3) {
                this.val$valueCallback.onReceiveValue(Boolean.valueOf(false));
                arg2.dismiss();
            }
        }).setOnCancelListener(new DialogInterface$OnCancelListener(arg4) {
            public void onCancel(DialogInterface arg2) {
                this.val$valueCallback.onReceiveValue(Boolean.valueOf(false));
            }
        });
        v5.create().show();
    }

    @XWalkAPI public WebResourceResponse shouldInterceptLoadRequest(XWalkViewInternal arg1, String arg2) {
        return null;
    }

    @XWalkAPI public XWalkWebResourceResponseInternal shouldInterceptLoadRequest(XWalkViewInternal arg1, XWalkWebResourceRequestInternal arg2) {
        return null;
    }

    @XWalkAPI public boolean shouldOverrideUrlLoading(XWalkViewInternal arg1, String arg2) {
        return 0;
    }
}

