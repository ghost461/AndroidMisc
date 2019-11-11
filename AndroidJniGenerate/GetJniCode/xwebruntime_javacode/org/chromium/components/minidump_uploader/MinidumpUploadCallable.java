package org.chromium.components.minidump_uploader;

import android.content.SharedPreferences;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.zip.GZIPOutputStream;
import org.chromium.base.ContextUtils;
import org.chromium.base.Log;
import org.chromium.base.StreamUtil;
import org.chromium.base.VisibleForTesting;
import org.chromium.components.minidump_uploader.util.CrashReportingPermissionManager;
import org.chromium.components.minidump_uploader.util.HttpURLConnectionFactory;
import org.chromium.components.minidump_uploader.util.HttpURLConnectionFactoryImpl;

public class MinidumpUploadCallable implements Callable {
    @public interface MinidumpUploadStatus {
    }

    @VisibleForTesting protected static final String CONTENT_TYPE_TMPL = "multipart/form-data; boundary=%s";
    @VisibleForTesting protected static final String CRASH_URL_STRING = "https://clients2.google.com/cr/report";
    protected static final String PREF_DAY_UPLOAD_COUNT = "crash_day_dump_upload_count";
    protected static final String PREF_LAST_UPLOAD_DAY = "crash_dump_last_upload_day";
    protected static final String PREF_LAST_UPLOAD_WEEK = "crash_dump_last_upload_week";
    protected static final String PREF_WEEK_UPLOAD_SIZE = "crash_dump_week_upload_size";
    private static final String TAG = "MDUploadCallable";
    public static final int UPLOAD_DISABLED_BY_SAMPLING = 3;
    public static final int UPLOAD_FAILURE = 1;
    public static final int UPLOAD_SUCCESS = 0;
    public static final int UPLOAD_USER_DISABLED = 2;
    private final File mFileToUpload;
    private final HttpURLConnectionFactory mHttpURLConnectionFactory;
    private final File mLogfile;
    private final CrashReportingPermissionManager mPermManager;

    public MinidumpUploadCallable(File arg2, File arg3, CrashReportingPermissionManager arg4) {
        this(arg2, arg3, new HttpURLConnectionFactoryImpl(), arg4);
        this.removeOutdatedPrefs(ContextUtils.getAppSharedPreferences());
    }

    public MinidumpUploadCallable(File arg1, File arg2, HttpURLConnectionFactory arg3, CrashReportingPermissionManager arg4) {
        super();
        this.mFileToUpload = arg1;
        this.mLogfile = arg2;
        this.mHttpURLConnectionFactory = arg3;
        this.mPermManager = arg4;
    }

    private void appendUploadedEntryToLog(String arg7) throws IOException {
        FileWriter v0 = new FileWriter(this.mLogfile, true);
        StringBuilder v1 = new StringBuilder();
        v1.append(System.currentTimeMillis() / 1000);
        v1.append(",");
        v1.append(arg7);
        v1.append('\n');
        try {
            v0.write(v1.toString());
        }
        catch(Throwable v7) {
            v0.close();
            throw v7;
        }

        v0.close();
    }

    public Integer call() {
        Integer v1_1;
        Integer v3_2;
        ArrayIndexOutOfBoundsException v3_1;
        FileInputStream v4_2;
        Integer v4_1;
        if(this.mPermManager.isUploadEnabledForTests()) {
            Log.i("MDUploadCallable", "Minidump upload enabled for tests, skipping other checks.", new Object[0]);
        }
        else if(!CrashFileManager.isForcedUpload(this.mFileToUpload)) {
            if(!this.mPermManager.isUsageAndCrashReportingPermittedByUser()) {
                Log.i("MDUploadCallable", "Minidump upload is not permitted by user. Marking file as skipped for cleanup to prevent future uploads.", new Object[0]);
                CrashFileManager.markUploadSkipped(this.mFileToUpload);
                return Integer.valueOf(2);
            }
            else if(!this.mPermManager.isClientInMetricsSample()) {
                Log.i("MDUploadCallable", "Minidump upload skipped due to sampling.  Marking file as skipped for cleanup to prevent future uploads.", new Object[0]);
                CrashFileManager.markUploadSkipped(this.mFileToUpload);
                return Integer.valueOf(3);
            }
            else if(!this.mPermManager.isNetworkAvailableForCrashUploads()) {
                Log.i("MDUploadCallable", "Minidump cannot currently be uploaded due to network constraints.", new Object[0]);
                return Integer.valueOf(1);
            }
        }

        HttpURLConnection v0 = this.mHttpURLConnectionFactory.createHttpURLConnection("https://clients2.google.com/cr/report");
        if(v0 == null) {
            return Integer.valueOf(1);
        }

        FileInputStream v3 = null;
        try {
            if(this.configureConnectionForHttpPost(v0)) {
                goto label_58;
            }

            v4_1 = Integer.valueOf(1);
        }
        catch(Throwable v1) {
            goto label_76;
        }
        catch(ArrayIndexOutOfBoundsException v4) {
            goto label_79;
        }

        v0.disconnect();
        return v4_1;
        try {
        label_58:
            v4_2 = new FileInputStream(this.mFileToUpload);
        }
        catch(Throwable v1) {
        label_76:
            v4_2 = v3;
            goto label_100;
        }
        catch(ArrayIndexOutOfBoundsException v4) {
        label_79:
            ArrayIndexOutOfBoundsException v8 = v4;
            v4_2 = v3;
            v3_1 = v8;
            goto label_82;
        }

        try {
            MinidumpUploadCallable.streamCopy(((InputStream)v4_2), new GZIPOutputStream(v0.getOutputStream()));
            v3_2 = Integer.valueOf(this.handleExecutionResponse(v0).booleanValue() ^ 1);
            goto label_69;
        }
        catch(Throwable v1) {
        }
        catch(ArrayIndexOutOfBoundsException v3_1) {
            try {
            label_82:
                Log.w("MDUploadCallable", "Error while uploading " + this.mFileToUpload.getName(), new Object[]{v3_1});
                v1_1 = Integer.valueOf(1);
            }
            catch(Throwable v1) {
                goto label_100;
            }

            v0.disconnect();
            if(v4_2 != null) {
                StreamUtil.closeQuietly(((Closeable)v4_2));
            }

            return v1_1;
        }

    label_100:
        v0.disconnect();
        if(v4_2 != null) {
            StreamUtil.closeQuietly(((Closeable)v4_2));
        }

        throw v1;
    label_69:
        v0.disconnect();
        if(v4_2 != null) {
            StreamUtil.closeQuietly(((Closeable)v4_2));
        }

        return v3_2;
    }

    public Object call() throws Exception {
        return this.call();
    }

    private boolean configureConnectionForHttpPost(HttpURLConnection arg7) throws IOException {
        String v0 = this.readBoundary();
        if(v0 == null) {
            return 0;
        }

        arg7.setDoOutput(true);
        arg7.setRequestProperty("Connection", "Keep-Alive");
        arg7.setRequestProperty("Content-Encoding", "gzip");
        arg7.setRequestProperty("Content-Type", String.format("multipart/form-data; boundary=%s", v0));
        return 1;
    }

    private static String getResponseContentAsString(HttpURLConnection arg1) throws IOException {
        ByteArrayOutputStream v0 = new ByteArrayOutputStream();
        MinidumpUploadCallable.streamCopy(arg1.getInputStream(), ((OutputStream)v0));
        String v1 = v0.size() > 0 ? v0.toString() : null;
        return v1;
    }

    private Boolean handleExecutionResponse(HttpURLConnection arg8) throws IOException {
        int v0 = arg8.getResponseCode();
        if(MinidumpUploadCallable.isSuccessful(v0)) {
            String v8 = MinidumpUploadCallable.getResponseContentAsString(arg8);
            if(v8 != null) {
            }
            else {
                v8 = "unknown";
            }

            Log.i("MDUploadCallable", "Minidump " + this.mFileToUpload.getName() + " uploaded successfully, id: " + v8, new Object[0]);
            CrashFileManager.markUploadSuccess(this.mFileToUpload);
            try {
                this.appendUploadedEntryToLog(v8);
            }
            catch(IOException ) {
                Log.e("MDUploadCallable", "Fail to write uploaded entry to log file", new Object[0]);
            }

            return Boolean.valueOf(true);
        }

        Log.i("MDUploadCallable", String.format(Locale.US, "Failed to upload %s with code: %d (%s).", this.mFileToUpload.getName(), Integer.valueOf(v0), arg8.getResponseMessage()), new Object[0]);
        return Boolean.valueOf(false);
    }

    private static boolean isSuccessful(int arg1) {
        boolean v1 = arg1 == 200 || arg1 == 201 || arg1 == 202 ? true : false;
        return v1;
    }

    private String readBoundary() throws IOException {
        BufferedReader v0 = new BufferedReader(new FileReader(this.mFileToUpload));
        String v1 = v0.readLine();
        v0.close();
        String v0_1 = null;
        if(v1 != null) {
            if(v1.trim().isEmpty()) {
            }
            else {
                v1 = v1.trim();
                if(v1.startsWith("--")) {
                    if(v1.length() < 10) {
                    }
                    else if(!v1.matches("^[a-zA-Z0-9-]*$")) {
                        Log.e("MDUploadCallable", "Ignoring invalidly bound crash dump \'" + this.mFileToUpload + "\' due to invalid boundary characters: \'" + v1 + "\'", new Object[0]);
                        return v0_1;
                    }
                    else {
                        return v1.substring(2);
                    }
                }

                Log.e("MDUploadCallable", "Ignoring invalidly bound crash dump: \'" + this.mFileToUpload + "\'", new Object[0]);
                return v0_1;
            }
        }

        Log.e("MDUploadCallable", "Ignoring invalid crash dump: \'" + this.mFileToUpload + "\'", new Object[0]);
        return v0_1;
    }

    private void removeOutdatedPrefs(SharedPreferences arg2) {
        arg2.edit().remove("crash_day_dump_upload_count").remove("crash_dump_last_upload_day").remove("crash_dump_last_upload_week").remove("crash_dump_week_upload_size").apply();
    }

    private static void streamCopy(InputStream arg3, OutputStream arg4) throws IOException {
        byte[] v0 = new byte[0x1000];
        int v1;
        for(v1 = arg3.read(v0); v1 >= 0; v1 = arg3.read(v0)) {
            arg4.write(v0, 0, v1);
        }

        arg3.close();
        arg4.close();
    }
}

