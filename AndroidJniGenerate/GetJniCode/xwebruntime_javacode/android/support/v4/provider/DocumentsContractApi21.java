package android.support.v4.provider;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.support.annotation.RequiresApi;
import android.util.Log;
import java.util.ArrayList;

@RequiresApi(value=21) class DocumentsContractApi21 {
    private static final String TAG = "DocumentFile";

    DocumentsContractApi21() {
        super();
    }

    private static void closeQuietly(AutoCloseable arg0) {
        if(arg0 != null) {
            try {
                arg0.close();
                return;
            }
            catch(Exception ) {
                return;
            }
            catch(RuntimeException v0) {
                throw v0;
            }
        }
    }

    public static Uri createDirectory(Context arg1, Uri arg2, String arg3) {
        return DocumentsContractApi21.createFile(arg1, arg2, "vnd.android.document/directory", arg3);
    }

    public static Uri createFile(Context arg0, Uri arg1, String arg2, String arg3) {
        try {
            return DocumentsContract.createDocument(arg0.getContentResolver(), arg1, arg2, arg3);
        }
        catch(Exception ) {
            return null;
        }
    }

    public static Uri[] listFiles(Context arg8, Uri arg9) {
        Cursor v6_1;
        Cursor v0_1;
        ContentResolver v0 = arg8.getContentResolver();
        Uri v1 = DocumentsContract.buildChildDocumentsUriUsingTree(arg9, DocumentsContract.getDocumentId(arg9));
        ArrayList v8 = new ArrayList();
        AutoCloseable v6 = null;
        try {
            v0_1 = v0.query(v1, new String[]{"document_id"}, null, null, null);
            goto label_15;
        }
        catch(Throwable v8_1) {
        }
        catch(Exception v9) {
            goto label_32;
            try {
            label_15:
                while(v0_1.moveToNext()) {
                    v8.add(DocumentsContract.buildDocumentUriUsingTree(arg9, v0_1.getString(0)));
                }
            }
            catch(Throwable v8_1) {
                v6_1 = v0_1;
                goto label_45;
            }
            catch(Exception v9) {
                v6_1 = v0_1;
                goto label_32;
            }

            DocumentsContractApi21.closeQuietly(((AutoCloseable)v0_1));
            goto label_41;
            try {
            label_32:
                Log.w("DocumentFile", "Failed query: " + v9);
            }
            catch(Throwable v8_1) {
                goto label_45;
            }
        }

        DocumentsContractApi21.closeQuietly(v6);
        goto label_41;
    label_45:
        DocumentsContractApi21.closeQuietly(v6);
        throw v8_1;
    label_41:
        return v8.toArray(new Uri[v8.size()]);
    }

    public static Uri prepareTreeUri(Uri arg1) {
        return DocumentsContract.buildDocumentUriUsingTree(arg1, DocumentsContract.getTreeDocumentId(arg1));
    }

    public static Uri renameTo(Context arg0, Uri arg1, String arg2) {
        try {
            return DocumentsContract.renameDocument(arg0.getContentResolver(), arg1, arg2);
        }
        catch(Exception ) {
            return null;
        }
    }
}

