package org.chromium.ui.base;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build$VERSION;
import android.os.Parcelable;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.chromium.base.ApiCompatibilityUtils;
import org.chromium.base.ContentUriUtils;
import org.chromium.base.ContextUtils;
import org.chromium.base.Log;
import org.chromium.base.VisibleForTesting;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.base.metrics.RecordHistogram;
import org.chromium.ui.PhotoPickerListener$Action;
import org.chromium.ui.PhotoPickerListener;
import org.chromium.ui.UiUtils;

@JNINamespace(value="ui") public class SelectFileDialog implements PhotoPickerListener, PermissionCallback, IntentCallback {
    class org.chromium.ui.base.SelectFileDialog$2 {
        static {
            org.chromium.ui.base.SelectFileDialog$2.$SwitchMap$org$chromium$ui$PhotoPickerListener$Action = new int[Action.values().length];
            try {
                org.chromium.ui.base.SelectFileDialog$2.$SwitchMap$org$chromium$ui$PhotoPickerListener$Action[Action.CANCEL.ordinal()] = 1;
                goto label_9;
            }
            catch(NoSuchFieldError ) {
                try {
                label_9:
                    org.chromium.ui.base.SelectFileDialog$2.$SwitchMap$org$chromium$ui$PhotoPickerListener$Action[Action.PHOTOS_SELECTED.ordinal()] = 2;
                    goto label_14;
                }
                catch(NoSuchFieldError ) {
                    try {
                    label_14:
                        org.chromium.ui.base.SelectFileDialog$2.$SwitchMap$org$chromium$ui$PhotoPickerListener$Action[Action.LAUNCH_GALLERY.ordinal()] = 3;
                        goto label_19;
                    }
                    catch(NoSuchFieldError ) {
                        try {
                        label_19:
                            org.chromium.ui.base.SelectFileDialog$2.$SwitchMap$org$chromium$ui$PhotoPickerListener$Action[Action.LAUNCH_CAMERA.ordinal()] = 4;
                            return;
                        }
                        catch(NoSuchFieldError ) {
                            return;
                        }
                    }
                }
            }
        }
    }

    class GetCameraIntentTask extends AsyncTask {
        private IntentCallback mCallback;
        private Boolean mDirectToCamera;
        private WindowAndroid mWindow;

        public GetCameraIntentTask(SelectFileDialog arg1, Boolean arg2, WindowAndroid arg3, IntentCallback arg4) {
            SelectFileDialog.this = arg1;
            super();
            this.mDirectToCamera = arg2;
            this.mWindow = arg3;
            this.mCallback = arg4;
        }

        public Uri doInBackground(Void[] arg5) {
            try {
                return ApiCompatibilityUtils.getUriForImageCaptureFile(SelectFileDialog.this.getFileForImageCapture(SelectFileDialog.this.mWindowAndroid.getApplicationContext()));
            }
            catch(IOException v5) {
                Log.e("SelectFileDialog", "Cannot retrieve content uri from file", new Object[]{v5});
                return null;
            }
        }

        public Object doInBackground(Object[] arg1) {
            return this.doInBackground(((Void[])arg1));
        }

        protected void onPostExecute(Uri arg5) {
            SelectFileDialog.this.mCameraOutputUri = arg5;
            if(SelectFileDialog.this.mCameraOutputUri == null) {
                if((SelectFileDialog.this.captureCamera()) || (this.mDirectToCamera.booleanValue())) {
                    SelectFileDialog.this.onFileNotSelected();
                }
                else {
                    SelectFileDialog.this.launchSelectFileWithCameraIntent(true, null);
                }

                return;
            }

            Intent v5 = new Intent("android.media.action.IMAGE_CAPTURE");
            v5.setFlags(3);
            v5.putExtra("output", SelectFileDialog.this.mCameraOutputUri);
            if(Build$VERSION.SDK_INT >= 18) {
                v5.setClipData(ClipData.newUri(SelectFileDialog.this.mWindowAndroid.getApplicationContext().getContentResolver(), "images", SelectFileDialog.this.mCameraOutputUri));
            }

            if(this.mDirectToCamera.booleanValue()) {
                this.mWindow.showIntent(v5, this.mCallback, Integer.valueOf(0x7F0C0059));
            }
            else {
                SelectFileDialog.this.launchSelectFileWithCameraIntent(true, v5);
            }
        }

        protected void onPostExecute(Object arg1) {
            this.onPostExecute(((Uri)arg1));
        }
    }

    class GetDisplayNameTask extends AsyncTask {
        final Context mContext;
        String[] mFilePaths;
        final boolean mIsMultiple;

        public GetDisplayNameTask(SelectFileDialog arg1, Context arg2, boolean arg3) {
            SelectFileDialog.this = arg1;
            super();
            this.mContext = arg2;
            this.mIsMultiple = arg3;
        }

        public Object doInBackground(Object[] arg1) {
            return this.doInBackground(((Uri[])arg1));
        }

        public String[] doInBackground(Uri[] arg7) {
            this.mFilePaths = new String[arg7.length];
            String[] v0 = new String[arg7.length];
            int v2 = 0;
            try {
                while(v2 < arg7.length) {
                    this.mFilePaths[v2] = "file".equals(arg7[v2].getScheme()) ? arg7[v2].getSchemeSpecificPart() : arg7[v2].toString();
                    v0[v2] = ContentUriUtils.getDisplayName(arg7[v2], this.mContext, "_display_name");
                    ++v2;
                }
            }
            catch(SecurityException ) {
                Log.w("SelectFileDialog", "Unable to extract results from the content provider", new Object[0]);
                return null;
            }

            return v0;
        }

        protected void onPostExecute(Object arg1) {
            this.onPostExecute(((String[])arg1));
        }

        protected void onPostExecute(String[] arg6) {
            if(arg6 == null) {
                SelectFileDialog.this.onFileNotSelected();
                return;
            }

            if(this.mIsMultiple) {
                SelectFileDialog.this.onMultipleFilesSelected(SelectFileDialog.this.mNativeSelectFileDialog, this.mFilePaths, arg6);
            }
            else {
                SelectFileDialog.this.onFileSelected(SelectFileDialog.this.mNativeSelectFileDialog, this.mFilePaths[0], arg6[0]);
            }
        }
    }

    private static final String ALL_AUDIO_TYPES = "audio/*";
    private static final String ALL_IMAGE_TYPES = "image/*";
    private static final String ALL_VIDEO_TYPES = "video/*";
    private static final String ANY_TYPES = "*/*";
    private static final String AUDIO_TYPE = "audio/";
    private static final long DURATION_BEFORE_FILE_CLEAN_UP_IN_MILLIS = 0;
    private static final String IMAGE_TYPE = "image/";
    private static final String[] POPULAR_IMAGE_EXTENSIONS = null;
    private static final String[] POPULAR_VIDEO_EXTENSIONS = null;
    static final int SELECT_FILE_DIALOG_SCOPE_COUNT = 4;
    static final int SELECT_FILE_DIALOG_SCOPE_GENERIC = 0;
    static final int SELECT_FILE_DIALOG_SCOPE_IMAGES = 1;
    static final int SELECT_FILE_DIALOG_SCOPE_IMAGES_AND_VIDEOS = 3;
    static final int SELECT_FILE_DIALOG_SCOPE_VIDEOS = 2;
    private static final String TAG = "SelectFileDialog";
    private static final String VIDEO_TYPE = "video/";
    private boolean mAllowMultiple;
    private Uri mCameraOutputUri;
    private boolean mCapture;
    private List mFileTypes;
    private final long mNativeSelectFileDialog;
    private boolean mSupportsAudioCapture;
    private boolean mSupportsImageCapture;
    private boolean mSupportsVideoCapture;
    private WindowAndroid mWindowAndroid;
    @SuppressLint(value={"StaticFieldLeak"}) private static WindowAndroid sOverrideWindowAndroid;

    static {
        SelectFileDialog.DURATION_BEFORE_FILE_CLEAN_UP_IN_MILLIS = TimeUnit.HOURS.toMillis(1);
        SelectFileDialog.POPULAR_IMAGE_EXTENSIONS = new String[]{".apng", ".bmp", ".gif", ".jpeg", ".jpg", ".pdf", ".png", ".tif", ".tiff", ".xcf", ".webp"};
        SelectFileDialog.POPULAR_VIDEO_EXTENSIONS = new String[]{".asf", ".avhcd", ".avi", ".divx", ".flv", ".mov", ".mp4", ".mpeg", ".mpg", ".swf", ".wmv", ".webm", ".mkv"};
    }

    SelectFileDialog(long arg1) {
        super();
        this.mNativeSelectFileDialog = arg1;
    }

    private boolean acceptsSpecificType(String arg4) {
        boolean v1 = false;
        if(this.mFileTypes.size() == 1 && (TextUtils.equals(this.mFileTypes.get(0), ((CharSequence)arg4)))) {
            v1 = true;
        }

        return v1;
    }

    static WindowAndroid access$000(SelectFileDialog arg0) {
        return arg0.mWindowAndroid;
    }

    static File access$100(SelectFileDialog arg0, Context arg1) throws IOException {
        return arg0.getFileForImageCapture(arg1);
    }

    static Uri access$200(SelectFileDialog arg0) {
        return arg0.mCameraOutputUri;
    }

    static Uri access$202(SelectFileDialog arg0, Uri arg1) {
        arg0.mCameraOutputUri = arg1;
        return arg1;
    }

    static boolean access$300(SelectFileDialog arg0) {
        return arg0.captureCamera();
    }

    static void access$400(SelectFileDialog arg0) {
        arg0.onFileNotSelected();
    }

    static void access$500(SelectFileDialog arg0, boolean arg1, Intent arg2) {
        arg0.launchSelectFileWithCameraIntent(arg1, arg2);
    }

    static long access$600(SelectFileDialog arg2) {
        return arg2.mNativeSelectFileDialog;
    }

    static void access$700(SelectFileDialog arg0, long arg1, String[] arg3, String[] arg4) {
        arg0.onMultipleFilesSelected(arg1, arg3, arg4);
    }

    static void access$800(SelectFileDialog arg0, long arg1, String arg3, String arg4) {
        arg0.onFileSelected(arg1, arg3, arg4);
    }

    static long access$900() {
        return SelectFileDialog.DURATION_BEFORE_FILE_CLEAN_UP_IN_MILLIS;
    }

    private boolean captureCamcorder() {
        boolean v0 = !this.mCapture || !this.acceptsSpecificType("video/*") ? false : true;
        return v0;
    }

    private boolean captureCamera() {
        boolean v0 = !this.mCapture || !this.acceptsSpecificType("image/*") ? false : true;
        return v0;
    }

    private boolean captureMicrophone() {
        boolean v0 = !this.mCapture || !this.acceptsSpecificType("audio/*") ? false : true;
        return v0;
    }

    public static void clearCapturedCameraFiles() {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(new Runnable() {
            public void run() {
                int v5;
                try {
                    File v1_1 = UiUtils.getDirectoryForImageCapture(ContextUtils.getApplicationContext());
                    if(!v1_1.isDirectory()) {
                        return;
                    }

                    File[] v1_2 = v1_1.listFiles();
                    if(v1_2 == null) {
                        return;
                    }

                    long v2 = System.currentTimeMillis();
                    int v4 = v1_2.length;
                    v5 = 0;
                    while(true) {
                    label_12:
                        if(v5 >= v4) {
                            return;
                        }

                        File v6 = v1_2[v5];
                        if(v2 - v6.lastModified() > SelectFileDialog.DURATION_BEFORE_FILE_CLEAN_UP_IN_MILLIS && !v6.delete()) {
                            Log.e("SelectFileDialog", "Failed to delete: " + v6, new Object[0]);
                        }

                        break;
                    }
                }
                catch(IOException v1) {
                    goto label_38;
                }

                ++v5;
                goto label_12;
            label_38:
                Log.w("SelectFileDialog", "Failed to delete captured camera files.", new Object[]{v1});
            }
        });
    }

    @VisibleForTesting public static List convertToImageMimeTypes(List arg4) {
        List v1 = null;
        if(arg4.size() == 0) {
            return v1;
        }

        ArrayList v0 = new ArrayList();
        Iterator v4 = arg4.iterator();
        while(v4.hasNext()) {
            String v2 = SelectFileDialog.ensureMimeType(v4.next());
            if(!v2.startsWith("image/")) {
                return v1;
            }

            if(((List)v0).contains(v2)) {
                continue;
            }

            ((List)v0).add(v2);
        }

        return ((List)v0);
    }

    private int countAcceptTypesFor(String arg4) {
        Iterator v0 = this.mFileTypes.iterator();
        int v1;
        for(v1 = 0; v0.hasNext(); ++v1) {
            if(!v0.next().startsWith(arg4)) {
                continue;
            }
        }

        return v1;
    }

    @VisibleForTesting @CalledByNative static SelectFileDialog create(long arg1) {
        return new SelectFileDialog(arg1);
    }

    @VisibleForTesting int determineSelectFileDialogScope() {
        int v6_1;
        if(this.mFileTypes.size() == 0) {
            return 0;
        }

        int v0 = this.countAcceptTypesFor("image/");
        int v2 = this.countAcceptTypesFor("video/");
        if(this.mFileTypes.size() > v0 + v2) {
            Iterator v3 = this.mFileTypes.iterator();
            while(v3.hasNext()) {
                Object v4 = v3.next();
                String[] v6 = SelectFileDialog.POPULAR_IMAGE_EXTENSIONS;
                int v7 = v6.length;
                int v8 = 0;
                while(true) {
                    if(v8 >= v7) {
                        break;
                    }
                    else if(((String)v4).equalsIgnoreCase(v6[v8])) {
                        v6_1 = v0 + 1;
                        v0 = 1;
                    }
                    else {
                        ++v8;
                        continue;
                    }

                    goto label_34;
                }

                v6_1 = v0;
                v0 = 0;
            label_34:
                if(v0 == 0) {
                    String[] v0_1 = SelectFileDialog.POPULAR_VIDEO_EXTENSIONS;
                    v7 = v0_1.length;
                    v8 = 0;
                    while(v8 < v7) {
                        if(((String)v4).equalsIgnoreCase(v0_1[v8])) {
                            ++v2;
                        }
                        else {
                            ++v8;
                            continue;
                        }

                        break;
                    }
                }

                v0 = v6_1;
            }
        }

        if(this.mFileTypes.size() - v0 - v2 > 0) {
            return 0;
        }

        if(v2 > 0) {
            return v0 == 0 ? 2 : 3;
        }

        return 1;
    }

    private boolean eligibleForPhotoPicker() {
        boolean v0 = SelectFileDialog.convertToImageMimeTypes(this.mFileTypes) != null ? true : false;
        return v0;
    }

    @VisibleForTesting public static String ensureMimeType(String arg2) {
        if(arg2.length() == 0) {
            return "";
        }

        String v0 = MimeTypeMap.getFileExtensionFromUrl(arg2);
        if(v0.length() > 0) {
            arg2 = MimeTypeMap.getSingleton().getMimeTypeFromExtension(v0);
            if(arg2 != null) {
                return arg2;
            }

            return "application/octet-stream";
        }

        return arg2;
    }

    private File getFileForImageCapture(Context arg3) throws IOException {
        return File.createTempFile(String.valueOf(System.currentTimeMillis()), ".jpg", UiUtils.getDirectoryForImageCapture(arg3));
    }

    private void launchSelectFileIntent() {
        boolean v0 = this.mWindowAndroid.hasPermission("android.permission.CAMERA");
        if(!this.mSupportsImageCapture || !v0) {
            this.launchSelectFileWithCameraIntent(v0, null);
        }
        else {
            new GetCameraIntentTask(this, Boolean.valueOf(false), this.mWindowAndroid, ((IntentCallback)this)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    private void launchSelectFileWithCameraIntent(boolean arg6, Intent arg7) {
        RecordHistogram.recordEnumeratedHistogram("Android.SelectFileDialogScope", this.determineSelectFileDialogScope(), 4);
        Intent v1 = null;
        Intent v6 = !this.mSupportsVideoCapture || !arg6 ? v1 : new Intent("android.media.action.VIDEO_CAPTURE");
        boolean v0 = this.mWindowAndroid.hasPermission("android.permission.RECORD_AUDIO");
        if((this.mSupportsAudioCapture) && (v0)) {
            v1 = new Intent("android.provider.MediaStore.RECORD_SOUND");
        }

        int v2 = 0x7F0C0059;
        if(!this.captureCamera() || arg7 == null) {
            if((this.captureCamcorder()) && v6 != null) {
                if(this.mWindowAndroid.showIntent(v6, ((IntentCallback)this), Integer.valueOf(v2))) {
                    return;
                }
                else {
                    goto label_47;
                }
            }

            if(!this.captureMicrophone()) {
                goto label_47;
            }

            if(v1 == null) {
                goto label_47;
            }

            if(!this.mWindowAndroid.showIntent(v1, ((IntentCallback)this), Integer.valueOf(v2))) {
                goto label_47;
            }

            return;
        }
        else if(this.mWindowAndroid.showIntent(arg7, ((IntentCallback)this), Integer.valueOf(v2))) {
            return;
        }

    label_47:
        Object v0_1 = this.mWindowAndroid.getActivity().get();
        List v3 = SelectFileDialog.convertToImageMimeTypes(this.mFileTypes);
        if(v0_1 != null && v3 != null && (UiUtils.showPhotoPicker(((Context)v0_1), ((PhotoPickerListener)this), this.mAllowMultiple, v3))) {
            return;
        }

        Intent v0_2 = new Intent("android.intent.action.GET_CONTENT");
        if(Build$VERSION.SDK_INT >= 18 && (this.mAllowMultiple)) {
            v0_2.putExtra("android.intent.extra.ALLOW_MULTIPLE", true);
        }

        ArrayList v3_1 = new ArrayList();
        if(!this.noSpecificType()) {
            if(this.shouldShowImageTypes()) {
                if(arg7 != null) {
                    v3_1.add(arg7);
                }

                v0_2.setType("image/*");
            }
            else {
                if(this.shouldShowVideoTypes()) {
                    if(v6 != null) {
                        v3_1.add(v6);
                    }

                    v0_2.setType("video/*");
                    goto label_93;
                }

                if(!this.shouldShowAudioTypes()) {
                    goto label_93;
                }

                if(v1 != null) {
                    v3_1.add(v1);
                }

                v0_2.setType("audio/*");
            }

        label_93:
            v0_2.addCategory("android.intent.category.OPENABLE");
        }

        if(v3_1.isEmpty()) {
            v0_2.setType("*/*");
            if(arg7 != null) {
                v3_1.add(arg7);
            }

            if(v6 != null) {
                v3_1.add(v6);
            }

            if(v1 == null) {
                goto label_105;
            }

            v3_1.add(v1);
        }

    label_105:
        v6 = new Intent("android.intent.action.CHOOSER");
        if(!v3_1.isEmpty()) {
            v6.putExtra("android.intent.extra.INITIAL_INTENTS", v3_1.toArray(new Intent[0]));
        }

        v6.putExtra("android.intent.extra.INTENT", ((Parcelable)v0_2));
        if(!this.mWindowAndroid.showIntent(v6, ((IntentCallback)this), Integer.valueOf(v2))) {
            this.onFileNotSelected();
        }
    }

    private native void nativeOnFileNotSelected(long arg1) {
    }

    private native void nativeOnFileSelected(long arg1, String arg2, String arg3) {
    }

    private native void nativeOnMultipleFilesSelected(long arg1, String[] arg2, String[] arg3) {
    }

    private boolean noSpecificType() {
        boolean v1 = true;
        if(this.mFileTypes.size() == 1) {
            if(this.mFileTypes.contains("*/*")) {
            }
            else {
                v1 = false;
            }
        }

        return v1;
    }

    private void onFileNotSelected() {
        this.onFileNotSelected(this.mNativeSelectFileDialog);
    }

    private void onFileNotSelected(long arg2) {
        if(this.eligibleForPhotoPicker()) {
            this.recordImageCountHistogram(0);
        }

        this.nativeOnFileNotSelected(arg2);
    }

    private void onFileSelected(long arg2, String arg4, String arg5) {
        if(this.eligibleForPhotoPicker()) {
            this.recordImageCountHistogram(1);
        }

        this.nativeOnFileSelected(arg2, arg4, arg5);
    }

    @TargetApi(value=18) public void onIntentCompleted(WindowAndroid arg4, int arg5, Intent arg6) {
        if(arg5 != -1) {
            this.onFileNotSelected();
            return;
        }

        if(arg6 != null) {
            int v0 = 18;
            if(arg6.getData() == null) {
                if(Build$VERSION.SDK_INT < v0) {
                    goto label_67;
                }
                else if(arg6.getClipData() == null) {
                    goto label_67;
                }
            }

            int v2 = 0;
            if(Build$VERSION.SDK_INT >= v0 && arg6.getData() == null && arg6.getClipData() != null) {
                ClipData v4 = arg6.getClipData();
                arg5 = v4.getItemCount();
                if(arg5 == 0) {
                    this.onFileNotSelected();
                    return;
                }
                else {
                    Uri[] v6 = new Uri[arg5];
                    while(v2 < arg5) {
                        v6[v2] = v4.getItemAt(v2).getUri();
                        ++v2;
                    }

                    new GetDisplayNameTask(this, ContextUtils.getApplicationContext(), true).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, ((Object[])v6));
                    return;
                }
            }

            if("file".equals(arg6.getData().getScheme())) {
                this.onFileSelected(this.mNativeSelectFileDialog, arg6.getData().getSchemeSpecificPart(), "");
                return;
            }

            if("content".equals(arg6.getScheme())) {
                new GetDisplayNameTask(this, ContextUtils.getApplicationContext(), false).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Uri[]{arg6.getData()});
                return;
            }

            this.onFileNotSelected();
            arg4.showError(0x7F0C0060);
            return;
        }

    label_67:
        String v5 = "file".equals(this.mCameraOutputUri.getScheme()) ? this.mCameraOutputUri.getPath() : this.mCameraOutputUri.toString();
        this.onFileSelected(this.mNativeSelectFileDialog, v5, this.mCameraOutputUri.getLastPathSegment());
        arg4.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", this.mCameraOutputUri));
    }

    private void onMultipleFilesSelected(long arg2, String[] arg4, String[] arg5) {
        if(this.eligibleForPhotoPicker()) {
            this.recordImageCountHistogram(arg4.length);
        }

        this.nativeOnMultipleFilesSelected(arg2, arg4, arg5);
    }

    public void onPickerUserAction(Action arg4, String[] arg5) {
        int v0 = 0;
        switch(org.chromium.ui.base.SelectFileDialog$2.$SwitchMap$org$chromium$ui$PhotoPickerListener$Action[arg4.ordinal()]) {
            case 1: {
                this.onFileNotSelected();
                break;
            }
            case 2: {
                if(arg5.length == 0) {
                    this.onFileNotSelected();
                    return;
                }

                if(arg5.length == 1) {
                    new GetDisplayNameTask(this, ContextUtils.getApplicationContext(), false).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Uri[]{Uri.parse(arg5[0])});
                    return;
                }

                Uri[] v4 = new Uri[arg5.length];
                while(v0 < arg5.length) {
                    v4[v0] = Uri.parse(arg5[v0]);
                    ++v0;
                }

                new GetDisplayNameTask(this, ContextUtils.getApplicationContext(), true).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, ((Object[])v4));
                break;
            }
            case 3: {
                Intent v4_1 = new Intent();
                v4_1.setType("image/*");
                if(this.mAllowMultiple) {
                    v4_1.putExtra("android.intent.extra.ALLOW_MULTIPLE", true);
                }

                v4_1.setAction("android.intent.action.GET_CONTENT");
                this.mWindowAndroid.showCancelableIntent(v4_1, ((IntentCallback)this), Integer.valueOf(0x7F0C0059));
                break;
            }
            case 4: {
                new GetCameraIntentTask(this, Boolean.valueOf(true), this.mWindowAndroid, ((IntentCallback)this)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                break;
            }
            default: {
                break;
            }
        }
    }

    public void onRequestPermissionsResult(String[] arg3, int[] arg4) {
        int v3;
        for(v3 = 0; v3 < arg4.length; ++v3) {
            if(arg4[v3] == -1 && (this.mCapture)) {
                this.onFileNotSelected();
                return;
            }
        }

        this.launchSelectFileIntent();
    }

    private void recordImageCountHistogram(int arg2) {
        RecordHistogram.recordCount100Histogram("Android.SelectFileDialogImgCount", arg2);
    }

    @TargetApi(value=18) @CalledByNative private void selectFile(String[] arg2, boolean arg3, boolean arg4, WindowAndroid arg5) {
        this.mFileTypes = new ArrayList(Arrays.asList(((Object[])arg2)));
        this.mCapture = arg3;
        this.mAllowMultiple = arg4;
        WindowAndroid v2 = SelectFileDialog.sOverrideWindowAndroid == null ? arg5 : SelectFileDialog.sOverrideWindowAndroid;
        this.mWindowAndroid = v2;
        this.mSupportsImageCapture = this.mWindowAndroid.canResolveActivity(new Intent("android.media.action.IMAGE_CAPTURE"));
        this.mSupportsVideoCapture = this.mWindowAndroid.canResolveActivity(new Intent("android.media.action.VIDEO_CAPTURE"));
        this.mSupportsAudioCapture = this.mWindowAndroid.canResolveActivity(new Intent("android.provider.MediaStore.RECORD_SOUND"));
        ArrayList v2_1 = new ArrayList();
        if(((this.mSupportsImageCapture) && (this.shouldShowImageTypes()) || (this.mSupportsVideoCapture) && (this.shouldShowVideoTypes())) && !arg5.hasPermission("android.permission.CAMERA")) {
            ((List)v2_1).add("android.permission.CAMERA");
        }

        if((this.mSupportsAudioCapture) && (this.shouldShowAudioTypes()) && !arg5.hasPermission("android.permission.RECORD_AUDIO")) {
            ((List)v2_1).add("android.permission.RECORD_AUDIO");
        }

        if((UiUtils.shouldShowPhotoPicker()) && !arg5.hasPermission("android.permission.READ_EXTERNAL_STORAGE")) {
            ((List)v2_1).add("android.permission.READ_EXTERNAL_STORAGE");
        }

        if(((List)v2_1).isEmpty()) {
            this.launchSelectFileIntent();
        }
        else {
            arg5.requestPermissions(((List)v2_1).toArray(new String[((List)v2_1).size()]), ((PermissionCallback)this));
        }
    }

    @VisibleForTesting public void setFileTypesForTests(List arg1) {
        this.mFileTypes = arg1;
    }

    @VisibleForTesting public static void setWindowAndroidForTests(WindowAndroid arg0) {
        SelectFileDialog.sOverrideWindowAndroid = arg0;
    }

    private boolean shouldShowAudioTypes() {
        return this.shouldShowTypes("audio/*", "audio/");
    }

    private boolean shouldShowImageTypes() {
        return this.shouldShowTypes("image/*", "image/");
    }

    private boolean shouldShowTypes(String arg3, String arg4) {
        boolean v1 = true;
        if(!this.noSpecificType()) {
            if(this.mFileTypes.contains(arg3)) {
            }
            else {
                if(this.countAcceptTypesFor(arg4) > 0) {
                }
                else {
                    v1 = false;
                }

                return v1;
            }
        }

        return 1;
    }

    private boolean shouldShowVideoTypes() {
        return this.shouldShowTypes("video/*", "video/");
    }
}

