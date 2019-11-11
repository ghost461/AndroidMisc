package android.support.v4.content;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build$VERSION;
import android.os.OperationCanceledException;
import android.support.v4.os.CancellationSignal;

public final class ContentResolverCompat {
    private ContentResolverCompat() {
        super();
    }

    public static Cursor query(ContentResolver arg7, Uri arg8, String[] arg9, String arg10, String[] arg11, String arg12, CancellationSignal arg13) {
        if(Build$VERSION.SDK_INT < 16) {
            goto label_24;
        }

        if(arg13 != null) {
            try {
                Object v13 = arg13.getCancellationSignalObject();
                goto label_9;
            label_8:
                v13 = null;
            label_9:
                return arg7.query(arg8, arg9, arg10, arg11, arg12, v13);
            label_7:
                goto label_18;
            }
            catch(Exception v7) {
                goto label_7;
            }
        }
        else {
            goto label_8;
        }

        goto label_9;
    label_18:
        if((v7 instanceof OperationCanceledException)) {
            throw new android.support.v4.os.OperationCanceledException();
        }

        throw v7;
    label_24:
        if(arg13 != null) {
            arg13.throwIfCanceled();
        }

        return arg7.query(arg8, arg9, arg10, arg11, arg12);
    }
}

