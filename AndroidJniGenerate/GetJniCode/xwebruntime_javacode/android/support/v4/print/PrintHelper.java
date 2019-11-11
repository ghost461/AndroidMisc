package android.support.v4.print;

import android.content.Context;
import android.graphics.Bitmap$Config;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory$Options;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.pdf.PdfDocument$Page;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.os.CancellationSignal$OnCancelListener;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes$Builder;
import android.print.PrintAttributes$Margins;
import android.print.PrintAttributes$MediaSize;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter$LayoutResultCallback;
import android.print.PrintDocumentAdapter$WriteResultCallback;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentInfo$Builder;
import android.print.PrintManager;
import android.print.pdf.PrintedPdfDocument;
import android.support.annotation.RequiresApi;
import android.util.Log;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class PrintHelper {
    class android.support.v4.print.PrintHelper$1 {
    }

    @Retention(value=RetentionPolicy.SOURCE) @interface ColorMode {
    }

    public interface OnPrintFinishCallback {
        void onFinish();
    }

    @Retention(value=RetentionPolicy.SOURCE) @interface Orientation {
    }

    @RequiresApi(value=19) class PrintHelperApi19 implements PrintHelperVersionImpl {
        private static final String LOG_TAG = "PrintHelperApi19";
        private static final int MAX_PRINT_SIZE = 3500;
        int mColorMode;
        final Context mContext;
        BitmapFactory$Options mDecodeOptions;
        protected boolean mIsMinMarginsHandlingCorrect;
        private final Object mLock;
        int mOrientation;
        protected boolean mPrintActivityRespectsOrientation;
        int mScaleMode;

        PrintHelperApi19(Context arg2) {
            super();
            this.mDecodeOptions = null;
            this.mLock = new Object();
            this.mScaleMode = 2;
            this.mColorMode = 2;
            this.mPrintActivityRespectsOrientation = true;
            this.mIsMinMarginsHandlingCorrect = true;
            this.mContext = arg2;
        }

        static void access$000(PrintHelperApi19 arg0, PrintAttributes arg1, int arg2, Bitmap arg3, ParcelFileDescriptor arg4, CancellationSignal arg5, PrintDocumentAdapter$WriteResultCallback arg6) {
            arg0.writeBitmap(arg1, arg2, arg3, arg4, arg5, arg6);
        }

        static Bitmap access$100(PrintHelperApi19 arg0, Bitmap arg1, int arg2) {
            return arg0.convertBitmapForColorMode(arg1, arg2);
        }

        static Matrix access$200(PrintHelperApi19 arg0, int arg1, int arg2, RectF arg3, int arg4) {
            return arg0.getMatrix(arg1, arg2, arg3, arg4);
        }

        static Bitmap access$400(PrintHelperApi19 arg0, Uri arg1) throws FileNotFoundException {
            return arg0.loadConstrainedBitmap(arg1);
        }

        static boolean access$600(Bitmap arg0) {
            return PrintHelperApi19.isPortrait(arg0);
        }

        static Object access$700(PrintHelperApi19 arg0) {
            return arg0.mLock;
        }

        private Bitmap convertBitmapForColorMode(Bitmap arg6, int arg7) {
            if(arg7 != 1) {
                return arg6;
            }

            Bitmap v7 = Bitmap.createBitmap(arg6.getWidth(), arg6.getHeight(), Bitmap$Config.ARGB_8888);
            Canvas v0 = new Canvas(v7);
            Paint v1 = new Paint();
            ColorMatrix v2 = new ColorMatrix();
            v2.setSaturation(0f);
            v1.setColorFilter(new ColorMatrixColorFilter(v2));
            v0.drawBitmap(arg6, 0f, 0f, v1);
            v0.setBitmap(null);
            return v7;
        }

        protected PrintAttributes$Builder copyAttributes(PrintAttributes arg3) {
            PrintAttributes$Builder v0 = new PrintAttributes$Builder().setMediaSize(arg3.getMediaSize()).setResolution(arg3.getResolution()).setMinMargins(arg3.getMinMargins());
            if(arg3.getColorMode() != 0) {
                v0.setColorMode(arg3.getColorMode());
            }

            return v0;
        }

        public int getColorMode() {
            return this.mColorMode;
        }

        private Matrix getMatrix(int arg4, int arg5, RectF arg6, int arg7) {
            Matrix v0 = new Matrix();
            float v4 = ((float)arg4);
            float v1 = arg6.width() / v4;
            float v7 = arg7 == 2 ? Math.max(v1, arg6.height() / (((float)arg5))) : Math.min(v1, arg6.height() / (((float)arg5)));
            v0.postScale(v7, v7);
            v0.postTranslate((arg6.width() - v4 * v7) / 2f, (arg6.height() - (((float)arg5)) * v7) / 2f);
            return v0;
        }

        public int getOrientation() {
            if(this.mOrientation == 0) {
                return 1;
            }

            return this.mOrientation;
        }

        public int getScaleMode() {
            return this.mScaleMode;
        }

        private static boolean isPortrait(Bitmap arg1) {
            boolean v1 = arg1.getWidth() <= arg1.getHeight() ? true : false;
            return v1;
        }

        private Bitmap loadBitmap(Uri arg3, BitmapFactory$Options arg4) throws FileNotFoundException {
            Bitmap v4_1;
            InputStream v3;
            if(arg3 != null) {
                if(this.mContext == null) {
                }
                else {
                    Rect v0 = null;
                    try {
                        v3 = this.mContext.getContentResolver().openInputStream(arg3);
                    }
                    catch(Throwable v4) {
                        goto label_21;
                    }

                    try {
                        v4_1 = BitmapFactory.decodeStream(v3, v0, arg4);
                        if(v3 == null) {
                            return v4_1;
                        }

                        goto label_10;
                    }
                    catch(Throwable v4) {
                        InputStream v0_1 = v3;
                    }

                label_21:
                    if((((InputStream)v0)) != null) {
                        try {
                            ((InputStream)v0).close();
                        }
                        catch(IOException v3_1) {
                            Log.w("PrintHelperApi19", "close fail ", ((Throwable)v3_1));
                        }

                        goto label_28;
                    }
                    else {
                    label_28:
                        throw v4;
                        try {
                        label_10:
                            v3.close();
                        }
                        catch(IOException v3_1) {
                            Log.w("PrintHelperApi19", "close fail ", ((Throwable)v3_1));
                        }

                        return v4_1;
                    }
                }
            }

            throw new IllegalArgumentException("bad argument to loadBitmap");
        }

        private Bitmap loadConstrainedBitmap(Uri arg8) throws FileNotFoundException {
            Object v1_1;
            Bitmap v8_1;
            BitmapFactory$Options v1;
            Object v0_2;
            if(arg8 != null) {
                if(this.mContext == null) {
                }
                else {
                    BitmapFactory$Options v0 = new BitmapFactory$Options();
                    v0.inJustDecodeBounds = true;
                    this.loadBitmap(arg8, v0);
                    int v2 = v0.outWidth;
                    int v0_1 = v0.outHeight;
                    BitmapFactory$Options v3 = null;
                    if(v2 > 0) {
                        if(v0_1 <= 0) {
                        }
                        else {
                            int v4 = Math.max(v2, v0_1);
                            int v5;
                            for(v5 = 1; v4 > 3500; v5 <<= 1) {
                                v4 >>>= 1;
                            }

                            if(v5 > 0) {
                                if(Math.min(v2, v0_1) / v5 <= 0) {
                                }
                                else {
                                    v0_2 = this.mLock;
                                    __monitor_enter(v0_2);
                                    try {
                                        this.mDecodeOptions = new BitmapFactory$Options();
                                        this.mDecodeOptions.inMutable = true;
                                        this.mDecodeOptions.inSampleSize = v5;
                                        v1 = this.mDecodeOptions;
                                        __monitor_exit(v0_2);
                                    }
                                    catch(Throwable v8) {
                                        goto label_57;
                                    }

                                    try {
                                        v8_1 = this.loadBitmap(arg8, v1);
                                    }
                                    catch(Throwable v8) {
                                        v1_1 = this.mLock;
                                        __monitor_enter(v1_1);
                                        try {
                                            this.mDecodeOptions = v3;
                                            __monitor_exit(v1_1);
                                        }
                                        catch(Throwable v8) {
                                            goto label_54;
                                        }

                                        throw v8;
                                    }

                                    v0_2 = this.mLock;
                                    __monitor_enter(v0_2);
                                    try {
                                        this.mDecodeOptions = v3;
                                        __monitor_exit(v0_2);
                                        return v8_1;
                                    }
                                    catch(Throwable v8) {
                                        goto label_45;
                                    }
                                }
                            }

                            return ((Bitmap)v3);
                            try {
                            label_54:
                                __monitor_exit(v1_1);
                            }
                            catch(Throwable v8) {
                                goto label_54;
                            }

                            throw v8;
                            try {
                            label_45:
                                __monitor_exit(v0_2);
                            }
                            catch(Throwable v8) {
                                goto label_45;
                            }

                            throw v8;
                            try {
                            label_57:
                                __monitor_exit(v0_2);
                            }
                            catch(Throwable v8) {
                                goto label_57;
                            }

                            throw v8;
                        }
                    }

                    return ((Bitmap)v3);
                }
            }

            throw new IllegalArgumentException("bad argument to getScaledBitmap");
        }

        public void printBitmap(String arg10, Bitmap arg11, OnPrintFinishCallback arg12) {
            if(arg11 == null) {
                return;
            }

            int v3 = this.mScaleMode;
            Object v6 = this.mContext.getSystemService("print");
            PrintAttributes$MediaSize v0 = PrintHelperApi19.isPortrait(arg11) ? PrintAttributes$MediaSize.UNKNOWN_PORTRAIT : PrintAttributes$MediaSize.UNKNOWN_LANDSCAPE;
            ((PrintManager)v6).print(arg10, new PrintDocumentAdapter(arg10, v3, arg11, arg12) {
                private PrintAttributes mAttributes;

                public void onFinish() {
                    if(this.val$callback != null) {
                        this.val$callback.onFinish();
                    }
                }

                public void onLayout(PrintAttributes arg1, PrintAttributes arg2, CancellationSignal arg3, PrintDocumentAdapter$LayoutResultCallback arg4, Bundle arg5) {
                    this.mAttributes = arg2;
                    arg4.onLayoutFinished(new PrintDocumentInfo$Builder(this.val$jobName).setContentType(1).setPageCount(1).build(), arg2.equals(arg1) ^ 1);
                }

                public void onWrite(PageRange[] arg8, ParcelFileDescriptor arg9, CancellationSignal arg10, PrintDocumentAdapter$WriteResultCallback arg11) {
                    PrintHelperApi19.this.writeBitmap(this.mAttributes, this.val$fittingMode, this.val$bitmap, arg9, arg10, arg11);
                }
            }, new PrintAttributes$Builder().setMediaSize(v0).setColorMode(this.mColorMode).build());
        }

        public void printBitmap(String arg8, Uri arg9, OnPrintFinishCallback arg10) throws FileNotFoundException {
            android.support.v4.print.PrintHelper$PrintHelperApi19$3 v6 = new PrintDocumentAdapter(arg8, arg9, arg10, this.mScaleMode) {
                private PrintAttributes mAttributes;
                Bitmap mBitmap;
                AsyncTask mLoadBitmap;

                static void access$300(android.support.v4.print.PrintHelper$PrintHelperApi19$3 arg0) {
                    arg0.cancelLoad();
                }

                static PrintAttributes access$500(android.support.v4.print.PrintHelper$PrintHelperApi19$3 arg0) {
                    return arg0.mAttributes;
                }

                private void cancelLoad() {
                    Object v0 = PrintHelperApi19.this.mLock;
                    __monitor_enter(v0);
                    try {
                        if(PrintHelperApi19.this.mDecodeOptions != null) {
                            PrintHelperApi19.this.mDecodeOptions.requestCancelDecode();
                            PrintHelperApi19.this.mDecodeOptions = null;
                        }

                        __monitor_exit(v0);
                        return;
                    label_15:
                        __monitor_exit(v0);
                    }
                    catch(Throwable v1) {
                        goto label_15;
                    }

                    throw v1;
                }

                public void onFinish() {
                    super.onFinish();
                    this.cancelLoad();
                    if(this.mLoadBitmap != null) {
                        this.mLoadBitmap.cancel(true);
                    }

                    if(this.val$callback != null) {
                        this.val$callback.onFinish();
                    }

                    if(this.mBitmap != null) {
                        this.mBitmap.recycle();
                        this.mBitmap = null;
                    }
                }

                public void onLayout(PrintAttributes arg7, PrintAttributes arg8, CancellationSignal arg9, PrintDocumentAdapter$LayoutResultCallback arg10, Bundle arg11) {
                    __monitor_enter(this);
                    try {
                        this.mAttributes = arg8;
                        __monitor_exit(this);
                    }
                    catch(Throwable v7) {
                        try {
                        label_35:
                            __monitor_exit(this);
                        }
                        catch(Throwable v7) {
                            goto label_35;
                        }

                        throw v7;
                    }

                    if(arg9.isCanceled()) {
                        arg10.onLayoutCancelled();
                        return;
                    }

                    if(this.mBitmap != null) {
                        arg10.onLayoutFinished(new PrintDocumentInfo$Builder(this.val$jobName).setContentType(1).setPageCount(1).build(), arg8.equals(arg7) ^ 1);
                        return;
                    }

                    this.mLoadBitmap = new AsyncTask(arg9, arg8, arg7, arg10) {
                        protected Bitmap doInBackground(Uri[] arg2) {
                            try {
                                return this.this$1.this$0.loadConstrainedBitmap(this.this$1.val$imageFile);
                            }
                            catch(FileNotFoundException ) {
                                return null;
                            }
                        }

                        protected Object doInBackground(Object[] arg1) {
                            return this.doInBackground(((Uri[])arg1));
                        }

                        protected void onCancelled(Bitmap arg2) {
                            this.val$layoutResultCallback.onLayoutCancelled();
                            this.this$1.mLoadBitmap = null;
                        }

                        protected void onCancelled(Object arg1) {
                            this.onCancelled(((Bitmap)arg1));
                        }

                        protected void onPostExecute(Bitmap arg10) {
                            super.onPostExecute(arg10);
                            if(arg10 != null && (!this.this$1.this$0.mPrintActivityRespectsOrientation || this.this$1.this$0.mOrientation == 0)) {
                                __monitor_enter(this);
                                try {
                                    PrintAttributes$MediaSize v0 = this.this$1.mAttributes.getMediaSize();
                                    __monitor_exit(this);
                                    if(v0 == null) {
                                        goto label_34;
                                    }
                                }
                                catch(Throwable v10) {
                                    try {
                                    label_32:
                                        __monitor_exit(this);
                                    }
                                    catch(Throwable v10) {
                                        goto label_32;
                                    }

                                    throw v10;
                                }

                                if(v0.isPortrait() == PrintHelperApi19.isPortrait(arg10)) {
                                    goto label_34;
                                }

                                Matrix v7 = new Matrix();
                                v7.postRotate(90f);
                                arg10 = Bitmap.createBitmap(arg10, 0, 0, arg10.getWidth(), arg10.getHeight(), v7, true);
                            }

                        label_34:
                            this.this$1.mBitmap = arg10;
                            CharSequence v0_1 = null;
                            if(arg10 != null) {
                                this.val$layoutResultCallback.onLayoutFinished(new PrintDocumentInfo$Builder(this.this$1.val$jobName).setContentType(1).setPageCount(1).build(), 1 ^ this.val$newPrintAttributes.equals(this.val$oldPrintAttributes));
                            }
                            else {
                                this.val$layoutResultCallback.onLayoutFailed(v0_1);
                            }

                            this.this$1.mLoadBitmap = ((AsyncTask)v0_1);
                        }

                        protected void onPostExecute(Object arg1) {
                            this.onPostExecute(((Bitmap)arg1));
                        }

                        protected void onPreExecute() {
                            this.val$cancellationSignal.setOnCancelListener(new CancellationSignal$OnCancelListener() {
                                public void onCancel() {
                                    this.this$2.this$1.cancelLoad();
                                    this.this$2.cancel(false);
                                }
                            });
                        }
                    }.execute(new Uri[0]);
                }

                public void onWrite(PageRange[] arg8, ParcelFileDescriptor arg9, CancellationSignal arg10, PrintDocumentAdapter$WriteResultCallback arg11) {
                    PrintHelperApi19.this.writeBitmap(this.mAttributes, this.val$fittingMode, this.mBitmap, arg9, arg10, arg11);
                }
            };
            Object v9 = this.mContext.getSystemService("print");
            PrintAttributes$Builder v10 = new PrintAttributes$Builder();
            v10.setColorMode(this.mColorMode);
            if(this.mOrientation == 1 || this.mOrientation == 0) {
                v10.setMediaSize(PrintAttributes$MediaSize.UNKNOWN_LANDSCAPE);
            }
            else if(this.mOrientation == 2) {
                v10.setMediaSize(PrintAttributes$MediaSize.UNKNOWN_PORTRAIT);
            }

            ((PrintManager)v9).print(arg8, ((PrintDocumentAdapter)v6), v10.build());
        }

        public void setColorMode(int arg1) {
            this.mColorMode = arg1;
        }

        public void setOrientation(int arg1) {
            this.mOrientation = arg1;
        }

        public void setScaleMode(int arg1) {
            this.mScaleMode = arg1;
        }

        private void writeBitmap(PrintAttributes arg13, int arg14, Bitmap arg15, ParcelFileDescriptor arg16, CancellationSignal arg17, PrintDocumentAdapter$WriteResultCallback arg18) {
            PrintHelperApi19 v9 = this;
            PrintAttributes v3 = v9.mIsMinMarginsHandlingCorrect ? arg13 : v9.copyAttributes(arg13).setMinMargins(new PrintAttributes$Margins(0, 0, 0, 0)).build();
            new AsyncTask(arg17, v3, arg15, arg13, arg14, arg16, arg18) {
                protected Object doInBackground(Object[] arg1) {
                    return this.doInBackground(((Void[])arg1));
                }

                protected Throwable doInBackground(Void[] arg9) {
                    RectF v2;
                    Bitmap v1;
                    PrintedPdfDocument v9_1;
                    Throwable v0;
                    try {
                        v0 = null;
                        if(this.val$cancellationSignal.isCanceled()) {
                            return v0;
                        }

                        v9_1 = new PrintedPdfDocument(PrintHelperApi19.this.mContext, this.val$pdfAttributes);
                        v1 = PrintHelperApi19.this.convertBitmapForColorMode(this.val$bitmap, this.val$pdfAttributes.getColorMode());
                        if(!this.val$cancellationSignal.isCanceled()) {
                            goto label_20;
                        }
                    }
                    catch(Throwable v9) {
                        return v9;
                    }

                    return v0;
                    try {
                    label_20:
                        PdfDocument$Page v3 = v9_1.startPage(1);
                        if(PrintHelperApi19.this.mIsMinMarginsHandlingCorrect) {
                            v2 = new RectF(v3.getInfo().getContentRect());
                        }
                        else {
                            PrintedPdfDocument v4 = new PrintedPdfDocument(PrintHelperApi19.this.mContext, this.val$attributes);
                            PdfDocument$Page v2_1 = v4.startPage(1);
                            RectF v5 = new RectF(v2_1.getInfo().getContentRect());
                            v4.finishPage(v2_1);
                            v4.close();
                            v2 = v5;
                        }

                        Matrix v4_1 = PrintHelperApi19.this.getMatrix(v1.getWidth(), v1.getHeight(), v2, this.val$fittingMode);
                        if(PrintHelperApi19.this.mIsMinMarginsHandlingCorrect) {
                        }
                        else {
                            v4_1.postTranslate(v2.left, v2.top);
                            v3.getCanvas().clipRect(v2);
                        }

                        v3.getCanvas().drawBitmap(v1, v4_1, ((Paint)v0));
                        v9_1.finishPage(v3);
                        if(!this.val$cancellationSignal.isCanceled()) {
                            goto label_71;
                        }
                    }
                    catch(Throwable v0) {
                        goto label_86;
                    }

                    try {
                        v9_1.close();
                        if(this.val$fileDescriptor != null) {
                            try {
                                this.val$fileDescriptor.close();
                                goto label_67;
                            }
                            catch(IOException ) {
                            label_67:
                                if(v1 != this.val$bitmap) {
                                    v1.recycle();
                                }

                                return v0;
                            }
                        }

                        goto label_67;
                    }
                    catch(Throwable v9) {
                        return v9;
                    }

                    try {
                    label_71:
                        v9_1.writeTo(new FileOutputStream(this.val$fileDescriptor.getFileDescriptor()));
                        goto label_76;
                    }
                    catch(Throwable v0) {
                        try {
                        label_86:
                            v9_1.close();
                            if(this.val$fileDescriptor != null) {
                                try {
                                    this.val$fileDescriptor.close();
                                    goto label_91;
                                }
                                catch(IOException ) {
                                label_91:
                                    if(v1 != this.val$bitmap) {
                                        v1.recycle();
                                    }

                                    throw v0;
                                }
                            }

                            goto label_91;
                        label_76:
                            v9_1.close();
                            if(this.val$fileDescriptor != null) {
                                try {
                                    this.val$fileDescriptor.close();
                                    goto label_81;
                                }
                                catch(IOException ) {
                                label_81:
                                    if(v1 != this.val$bitmap) {
                                        v1.recycle();
                                    }

                                    return v0;
                                }
                            }

                            goto label_81;
                        }
                        catch(Throwable v9) {
                            return v9;
                        }
                    }
                }

                protected void onPostExecute(Object arg1) {
                    this.onPostExecute(((Throwable)arg1));
                }

                protected void onPostExecute(Throwable arg4) {
                    if(this.val$cancellationSignal.isCanceled()) {
                        this.val$writeResultCallback.onWriteCancelled();
                    }
                    else if(arg4 == null) {
                        this.val$writeResultCallback.onWriteFinished(new PageRange[]{PageRange.ALL_PAGES});
                    }
                    else {
                        Log.e("PrintHelperApi19", "Error writing printed content", arg4);
                        this.val$writeResultCallback.onWriteFailed(null);
                    }
                }
            }.execute(new Void[0]);
        }
    }

    @RequiresApi(value=20) class PrintHelperApi20 extends PrintHelperApi19 {
        PrintHelperApi20(Context arg1) {
            super(arg1);
            this.mPrintActivityRespectsOrientation = false;
        }
    }

    @RequiresApi(value=23) class PrintHelperApi23 extends PrintHelperApi20 {
        PrintHelperApi23(Context arg1) {
            super(arg1);
            this.mIsMinMarginsHandlingCorrect = false;
        }

        protected PrintAttributes$Builder copyAttributes(PrintAttributes arg3) {
            PrintAttributes$Builder v0 = super.copyAttributes(arg3);
            if(arg3.getDuplexMode() != 0) {
                v0.setDuplexMode(arg3.getDuplexMode());
            }

            return v0;
        }
    }

    @RequiresApi(value=24) class PrintHelperApi24 extends PrintHelperApi23 {
        PrintHelperApi24(Context arg1) {
            super(arg1);
            this.mIsMinMarginsHandlingCorrect = true;
            this.mPrintActivityRespectsOrientation = true;
        }
    }

    final class PrintHelperStub implements PrintHelperVersionImpl {
        int mColorMode;
        int mOrientation;
        int mScaleMode;

        PrintHelperStub(android.support.v4.print.PrintHelper$1 arg1) {
            this();
        }

        private PrintHelperStub() {
            super();
            this.mScaleMode = 2;
            this.mColorMode = 2;
            this.mOrientation = 1;
        }

        public int getColorMode() {
            return this.mColorMode;
        }

        public int getOrientation() {
            return this.mOrientation;
        }

        public int getScaleMode() {
            return this.mScaleMode;
        }

        public void printBitmap(String arg1, Bitmap arg2, OnPrintFinishCallback arg3) {
        }

        public void printBitmap(String arg1, Uri arg2, OnPrintFinishCallback arg3) {
        }

        public void setColorMode(int arg1) {
            this.mColorMode = arg1;
        }

        public void setOrientation(int arg1) {
            this.mOrientation = arg1;
        }

        public void setScaleMode(int arg1) {
            this.mScaleMode = arg1;
        }
    }

    interface PrintHelperVersionImpl {
        int getColorMode();

        int getOrientation();

        int getScaleMode();

        void printBitmap(String arg1, Bitmap arg2, OnPrintFinishCallback arg3);

        void printBitmap(String arg1, Uri arg2, OnPrintFinishCallback arg3) throws FileNotFoundException;

        void setColorMode(int arg1);

        void setOrientation(int arg1);

        void setScaleMode(int arg1);
    }

    @Retention(value=RetentionPolicy.SOURCE) @interface ScaleMode {
    }

    public static final int COLOR_MODE_COLOR = 2;
    public static final int COLOR_MODE_MONOCHROME = 1;
    public static final int ORIENTATION_LANDSCAPE = 1;
    public static final int ORIENTATION_PORTRAIT = 2;
    public static final int SCALE_MODE_FILL = 2;
    public static final int SCALE_MODE_FIT = 1;
    private final PrintHelperVersionImpl mImpl;

    public PrintHelper(Context arg3) {
        super();
        if(Build$VERSION.SDK_INT >= 24) {
            this.mImpl = new PrintHelperApi24(arg3);
        }
        else if(Build$VERSION.SDK_INT >= 23) {
            this.mImpl = new PrintHelperApi23(arg3);
        }
        else if(Build$VERSION.SDK_INT >= 20) {
            this.mImpl = new PrintHelperApi20(arg3);
        }
        else if(Build$VERSION.SDK_INT >= 19) {
            this.mImpl = new PrintHelperApi19(arg3);
        }
        else {
            this.mImpl = new PrintHelperStub(null);
        }
    }

    public int getColorMode() {
        return this.mImpl.getColorMode();
    }

    public int getOrientation() {
        return this.mImpl.getOrientation();
    }

    public int getScaleMode() {
        return this.mImpl.getScaleMode();
    }

    public void printBitmap(String arg3, Bitmap arg4) {
        this.mImpl.printBitmap(arg3, arg4, null);
    }

    public void printBitmap(String arg2, Bitmap arg3, OnPrintFinishCallback arg4) {
        this.mImpl.printBitmap(arg2, arg3, arg4);
    }

    public void printBitmap(String arg3, Uri arg4) throws FileNotFoundException {
        this.mImpl.printBitmap(arg3, arg4, null);
    }

    public void printBitmap(String arg2, Uri arg3, OnPrintFinishCallback arg4) throws FileNotFoundException {
        this.mImpl.printBitmap(arg2, arg3, arg4);
    }

    public void setColorMode(int arg2) {
        this.mImpl.setColorMode(arg2);
    }

    public void setOrientation(int arg2) {
        this.mImpl.setOrientation(arg2);
    }

    public void setScaleMode(int arg2) {
        this.mImpl.setScaleMode(arg2);
    }

    public static boolean systemSupportsPrint() {
        boolean v0 = Build$VERSION.SDK_INT >= 19 ? true : false;
        return v0;
    }
}

