package org.xwalk.core.internal;

import android.app.DownloadManager$Request;
import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.webkit.MimeTypeMap;
import android.webkit.URLUtil;
import android.widget.Toast;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

class XWalkDownloadListenerImpl extends XWalkDownloadListenerInternal {
    class FileTransfer extends AsyncTask {
        String fileName;
        String url;

        public FileTransfer(XWalkDownloadListenerImpl arg1, String arg2, String arg3) {
            XWalkDownloadListenerImpl.this = arg1;
            super();
            this.url = arg2;
            this.fileName = arg3;
        }

        protected Object doInBackground(Object[] arg1) {
            return this.doInBackground(((Void[])arg1));
        }

        protected String doInBackground(Void[] arg4) {
            InputStream v2;
            InputStream v0_3;
            FileOutputStream v1;
            File v0 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), this.fileName);
            if(v0.exists()) {
                return "Existed";
            }

            InputStream v4 = null;
            try {
                v1 = new FileOutputStream(v0);
            }
            catch(Throwable v0_1) {
                v1 = ((FileOutputStream)v4);
                goto label_54;
            }
            catch(IOException v0_2) {
                v1 = ((FileOutputStream)v4);
                goto label_46;
            }

            try {
                v0_3 = AndroidProtocolHandler.open(this.url);
                if(v1 == null) {
                    goto label_28;
                }

                goto label_15;
            }
            catch(Throwable v0_1) {
            }
            catch(IOException v0_2) {
                goto label_46;
            label_15:
                if(v0_3 != null) {
                    try {
                        this.streamTransfer(v0_3, ((OutputStream)v1));
                        goto label_28;
                    }
                    catch(Throwable v4_1) {
                        v2 = v0_3;
                        v0_1 = v4_1;
                        v4 = v2;
                        goto label_54;
                    }
                    catch(IOException v4_2) {
                        v2 = v0_3;
                        v0_2 = v4_2;
                        v4 = v2;
                        try {
                        label_46:
                            ThrowableExtension.printStackTrace(((Throwable)v0_2));
                            if(v4 == null) {
                                goto label_49;
                            }
                        }
                        catch(Throwable v0_1) {
                            goto label_54;
                        }

                        try {
                            v4.close();
                        label_49:
                            if(v1 == null) {
                                return "Finished";
                            }

                            ((OutputStream)v1).close();
                            return "Finished";
                        }
                        catch(IOException v4_2) {
                            goto label_32;
                        }
                    }
                }

                goto label_28;
            }

        label_54:
            if(v4 != null) {
                try {
                    v4.close();
                label_56:
                    if(v1 != null) {
                        ((OutputStream)v1).close();
                    }

                    goto label_58;
                }
                catch(IOException v4_2) {
                    goto label_32;
                }
            }

            goto label_56;
        label_58:
            throw v0_1;
        label_28:
            if(v0_3 != null) {
                try {
                    v0_3.close();
                label_33:
                    if(v1 == null) {
                        return "Finished";
                    }

                    ((OutputStream)v1).close();
                    return "Finished";
                label_32:
                    goto label_36;
                }
                catch(IOException v4_2) {
                    goto label_32;
                }
            }

            goto label_33;
        label_36:
            ThrowableExtension.printStackTrace(((Throwable)v4_2));
            return "Failed";
        }

        protected void onPostExecute(Object arg1) {
            this.onPostExecute(((String)arg1));
        }

        protected void onPostExecute(String arg3) {
            if(arg3.equals("Failed")) {
                XWalkDownloadListenerImpl.this.popupMessages(XWalkDownloadListenerImpl.this.mContext.getString(0x7F0C004A));
            }
            else if(arg3.equals("Existed")) {
                XWalkDownloadListenerImpl.this.popupMessages(XWalkDownloadListenerImpl.this.mContext.getString(0x7F0C0048));
            }
            else if(arg3.equals("Finished")) {
                XWalkDownloadListenerImpl.this.popupMessages(XWalkDownloadListenerImpl.this.mContext.getString(0x7F0C004B));
            }
        }

        private void streamTransfer(InputStream arg4, OutputStream arg5) throws IOException {
            byte[] v0 = new byte[0x400];
            while(true) {
                int v1 = arg4.read(v0);
                if(v1 <= 0) {
                    return;
                }

                arg5.write(v0, 0, v1);
            }
        }
    }

    private Context mContext;
    private XWalkCookieManagerInternal mCookieManager;

    public XWalkDownloadListenerImpl(Context arg1) {
        super(arg1);
        this.mContext = arg1;
        this.mCookieManager = new XWalkCookieManagerInternal();
    }

    static Context access$000(XWalkDownloadListenerImpl arg0) {
        return arg0.mContext;
    }

    static void access$100(XWalkDownloadListenerImpl arg0, String arg1) {
        arg0.popupMessages(arg1);
    }

    private boolean checkWriteExternalPermission() {
        if(this.mContext.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
            return 1;
        }

        this.popupMessages(this.mContext.getString(0x7F0C004C));
        return 0;
    }

    private DownloadManager getDownloadManager() {
        return this.mContext.getSystemService("download");
    }

    private String getFileName(String arg3, String arg4, String arg5) {
        arg3 = URLUtil.guessFileName(arg3, arg4, arg5);
        int v4 = arg3.lastIndexOf(".");
        arg4 = v4 <= 1 || v4 >= arg3.length() ? null : arg3.substring(v4 + 1);
        if(arg4 == null) {
            arg4 = MimeTypeMap.getSingleton().getExtensionFromMimeType(arg5);
            if(arg4 != null) {
                arg3 = arg3 + "." + arg4;
            }
        }

        return arg3;
    }

    public void onDownloadStart(String arg1, String arg2, String arg3, String arg4, long arg5) {
        arg3 = this.getFileName(arg1, arg3, arg4);
        if(!this.checkWriteExternalPermission()) {
            return;
        }

        Uri v4 = Uri.parse(arg1);
        if((v4.getScheme().equals("http")) || (v4.getScheme().equals("https"))) {
            DownloadManager$Request v4_1 = new DownloadManager$Request(Uri.parse(arg1));
            v4_1.addRequestHeader("Cookie", this.mCookieManager.getCookie(arg1));
            v4_1.addRequestHeader("User-Agent", arg2);
            v4_1.setNotificationVisibility(1);
            v4_1.setTitle(((CharSequence)arg3));
            v4_1.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, arg3);
            this.getDownloadManager().enqueue(v4_1);
            this.popupMessages(this.mContext.getString(0x7F0C004E) + arg3);
        }
        else {
            new FileTransfer(this, arg1, arg3).execute(new Void[0]);
        }
    }

    private void popupMessages(String arg3) {
        Toast.makeText(this.mContext, ((CharSequence)arg3), 0).show();
    }
}

