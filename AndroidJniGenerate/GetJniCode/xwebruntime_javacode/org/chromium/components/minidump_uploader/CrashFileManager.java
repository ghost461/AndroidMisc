package org.chromium.components.minidump_uploader;

import android.support.annotation.Nullable;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import org.chromium.base.Log;
import org.chromium.base.VisibleForTesting;

public class CrashFileManager {
    final class org.chromium.components.minidump_uploader.CrashFileManager$1 implements Comparator {
        org.chromium.components.minidump_uploader.CrashFileManager$1() {
            super();
        }

        public int compare(File arg6, File arg7) {
            if(arg6.lastModified() == arg7.lastModified()) {
                return arg6.compareTo(arg7);
            }

            if(arg6.lastModified() < arg7.lastModified()) {
                return 1;
            }

            return -1;
        }

        public int compare(Object arg1, Object arg2) {
            return this.compare(((File)arg1), ((File)arg2));
        }
    }

    public static final String CRASH_DUMP_DIR = "Crash Reports";
    @VisibleForTesting public static final String CRASH_DUMP_LOGFILE = "uploads.log";
    @VisibleForTesting protected static final int MAX_CRASH_REPORTS_TO_KEEP = 10;
    @VisibleForTesting static final int MAX_CRASH_REPORTS_TO_UPLOAD = 20;
    @VisibleForTesting static final int MAX_CRASH_REPORTS_TO_UPLOAD_PER_UID = 10;
    private static final int MAX_CRASH_REPORT_AGE_IN_DAYS = 30;
    private static final Pattern MINIDUMP_READY_FOR_UPLOAD_PATTERN = null;
    private static final Pattern MINIDUMP_SANS_LOGCAT_PATTERN = null;
    private static final String NOT_YET_UPLOADED_MINIDUMP_SUFFIX = ".dmp";
    public static final String READY_FOR_UPLOAD_SUFFIX = ".try0";
    private static final String TAG = "CrashFileManager";
    private static final Pattern TMP_PATTERN = null;
    @VisibleForTesting protected static final String TMP_SUFFIX = ".tmp";
    private static final String UID_DELIMITER = "_";
    private static final Pattern UPLOADED_MINIDUMP_PATTERN = null;
    private static final String UPLOADED_MINIDUMP_SUFFIX = ".up";
    private static final String UPLOAD_ATTEMPT_DELIMITER = ".try";
    private static final String UPLOAD_FORCED_MINIDUMP_SUFFIX = ".forced";
    private static final String UPLOAD_SKIPPED_MINIDUMP_SUFFIX = ".skipped";
    private final File mCacheDir;
    @VisibleForTesting protected static final Comparator sFileComparator;

    static {
        CrashFileManager.MINIDUMP_SANS_LOGCAT_PATTERN = Pattern.compile("\\.dmp([0-9]*)\\z");
        CrashFileManager.MINIDUMP_READY_FOR_UPLOAD_PATTERN = Pattern.compile("\\.(dmp|forced)([0-9]*)(\\.try([0-9]+))\\z");
        CrashFileManager.UPLOADED_MINIDUMP_PATTERN = Pattern.compile("\\.up([0-9]*)(\\.try([0-9]+))\\z");
        CrashFileManager.TMP_PATTERN = Pattern.compile("\\.tmp\\z");
        CrashFileManager.sFileComparator = new org.chromium.components.minidump_uploader.CrashFileManager$1();
    }

    public CrashFileManager(File arg3) {
        super();
        if(arg3 == null) {
            throw new NullPointerException("Specified context cannot be null.");
        }

        if(!arg3.isDirectory()) {
            StringBuilder v1 = new StringBuilder();
            v1.append(arg3.getAbsolutePath());
            v1.append(" is not a directory.");
            throw new IllegalArgumentException(v1.toString());
        }

        this.mCacheDir = arg3;
    }

    private static boolean belongsToUid(File arg1, int arg2) {
        String v1 = arg1.getName();
        StringBuilder v0 = new StringBuilder();
        v0.append(arg2);
        v0.append("_");
        return v1.startsWith(v0.toString());
    }

    public void cleanOutAllNonFreshMinidumpFiles() {
        File[] v0 = this.getAllUploadedFiles();
        int v1 = v0.length;
        int v2 = 0;
        int v3;
        for(v3 = 0; v3 < v1; ++v3) {
            CrashFileManager.deleteFile(v0[v3]);
        }

        v0 = this.getAllTempFiles();
        v1 = v0.length;
        for(v3 = 0; v3 < v1; ++v3) {
            CrashFileManager.deleteFile(v0[v3]);
        }

        v0 = this.listCrashFiles(null);
        v1 = v0.length;
        v3 = 0;
        while(v2 < v1) {
            File v4 = v0[v2];
            if(v4.getName().equals("uploads.log")) {
            }
            else if(TimeUnit.DAYS.convert(new Date().getTime() - v4.lastModified(), TimeUnit.MILLISECONDS) > 30) {
                CrashFileManager.deleteFile(v4);
            }
            else if(v3 < 10) {
                ++v3;
            }
            else {
                CrashFileManager.deleteFile(v4);
            }

            ++v2;
        }
    }

    public File copyMinidumpFromFD(FileDescriptor arg11, File arg12, int arg13) throws IOException {
        int v5;
        FileOutputStream v11;
        FileInputStream v4;
        File v0 = this.getCrashDirectory();
        File v2 = null;
        if(!this.ensureCrashDirExists()) {
            Log.e("CrashFileManager", "Crash directory doesn\'t exist", new Object[0]);
            return v2;
        }

        if(!arg12.isDirectory() && !arg12.mkdir()) {
            Log.e("CrashFileManager", "Couldn\'t create " + arg12.getAbsolutePath(), new Object[0]);
            return v2;
        }

        if(arg12.getCanonicalPath().equals(v0.getCanonicalPath())) {
            throw new RuntimeException("The tmp-dir and the crash dir can\'t have the same paths.");
        }

        this.enforceMinidumpStorageRestrictions(arg13);
        arg12 = CrashFileManager.createMinidumpTmpFile(arg12);
        try {
            v4 = new FileInputStream(arg11);
        }
        catch(Throwable v12) {
            v11 = ((FileOutputStream)v2);
            v4 = ((FileInputStream)v11);
            goto label_121;
        }

        try {
            v11 = new FileOutputStream(arg12);
            v5 = 0x1000;
        }
        catch(Throwable v12) {
            v11 = ((FileOutputStream)v2);
            goto label_121;
        }

        try {
            byte[] v5_1 = new byte[v5];
            int v6 = v4.read(v5_1);
            int v7 = v6;
            while(true) {
                int v8 = -1;
                if(v6 != v8 && v7 < 0x100000) {
                    v11.write(v5_1, 0, v6);
                    v6 = v4.read(v5_1);
                    v7 += v6;
                    continue;
                }

                break;
            }

            if(v6 == v8) {
                goto label_88;
            }

            Log.w("CrashFileManager", "Tried to copy a file of size > 1MB, deleting the file and bailing!", new Object[0]);
            if(!arg12.delete()) {
                Log.w("CrashFileManager", "Couldn\'t delete file " + arg12.getAbsolutePath(), new Object[0]);
            }
        }
        catch(Throwable v12) {
            goto label_121;
        }

        if(v11 != null) {
            try {
                v11.close();
            }
            catch(IOException v11_1) {
                Log.w("CrashFileManager", "Couldn\'t close minidump output stream ", new Object[]{v11_1});
            }
        }

        if(v4 != null) {
            try {
                v4.close();
            }
            catch(IOException v11_1) {
                Log.w("CrashFileManager", "Couldn\'t close minidump input stream ", new Object[]{v11_1});
            }
        }

        return v2;
    label_88:
        if(v11 != null) {
            try {
                v11.close();
            }
            catch(IOException v11_1) {
                Log.w("CrashFileManager", "Couldn\'t close minidump output stream ", new Object[]{v11_1});
            }
        }

        if(v4 != null) {
            try {
                v4.close();
            }
            catch(IOException v11_1) {
                Log.w("CrashFileManager", "Couldn\'t close minidump input stream ", new Object[]{v11_1});
            }
        }

        File v11_2 = new File(v0, CrashFileManager.createUniqueMinidumpNameForUid(arg13));
        if(arg12.renameTo(v11_2)) {
            return v11_2;
        }

        return v2;
    label_121:
        if(v11 != null) {
            try {
                v11.close();
            }
            catch(IOException v11_1) {
                Log.w("CrashFileManager", "Couldn\'t close minidump output stream ", new Object[]{v11_1});
            }
        }

        if(v4 != null) {
            try {
                v4.close();
            }
            catch(IOException v11_1) {
                Log.w("CrashFileManager", "Couldn\'t close minidump input stream ", new Object[]{v11_1});
            }
        }

        throw v12;
    }

    public boolean crashDirectoryExists() {
        return this.getCrashDirectory().isDirectory();
    }

    private static File createMinidumpTmpFile(File arg2) throws IOException {
        return File.createTempFile("webview_minidump", ".tmp", arg2);
    }

    public File createNewTempFile(String arg4) throws IOException {
        File v0 = new File(this.getCrashDirectory(), arg4);
        if(v0.exists()) {
            if(v0.delete()) {
                v0 = new File(this.getCrashDirectory(), arg4);
            }
            else {
                Log.w("CrashFileManager", "Unable to delete previous logfile" + v0.getAbsolutePath(), new Object[0]);
            }
        }

        return v0;
    }

    private static String createUniqueMinidumpNameForUid(int arg1) {
        return arg1 + "_" + UUID.randomUUID() + ".dmp" + ".try0";
    }

    public static boolean deleteFile(File arg4) {
        boolean v0 = arg4.delete();
        if(!v0) {
            Log.w("CrashFileManager", "Unable to delete " + arg4.getAbsolutePath(), new Object[0]);
        }

        return v0;
    }

    private void enforceMinidumpStorageRestrictions(int arg5) {
        File[] v0 = this.listCrashFiles(CrashFileManager.MINIDUMP_READY_FOR_UPLOAD_PATTERN);
        List v5 = CrashFileManager.filterMinidumpFilesOnUid(v0, arg5);
        if(v5.size() >= 10) {
            Object v5_1 = v5.get(v5.size() - 1);
            if(!((File)v5_1).delete()) {
                Log.w("CrashFileManager", "Couldn\'t delete old minidump " + ((File)v5_1).getAbsolutePath(), new Object[0]);
            }

            return;
        }

        if(v0.length >= 20) {
            File v5_2 = v0[v0.length - 1];
            if(!v5_2.delete()) {
                Log.w("CrashFileManager", "Couldn\'t delete old minidump " + v5_2.getAbsolutePath(), new Object[0]);
            }
        }
    }

    private boolean ensureCrashDirExists() {
        File v0 = this.getCrashDirectory();
        boolean v0_1 = (v0.mkdir()) || (v0.isDirectory()) ? true : false;
        return v0_1;
    }

    @VisibleForTesting protected static String filenameWithForcedUploadState(String arg3) {
        int v0 = CrashFileManager.readAttemptNumber(arg3);
        if(v0 > 0) {
            StringBuilder v1 = new StringBuilder();
            v1.append(".try");
            v1.append(v0);
            arg3 = arg3.replace(v1.toString(), ".try0");
        }

        return arg3.replace(".skipped", ".forced").replace(".dmp", ".forced");
    }

    @VisibleForTesting public static String filenameWithIncrementedAttemptNumber(String arg4) {
        int v0 = CrashFileManager.readAttemptNumberInternal(arg4);
        if(v0 >= 0) {
            int v1 = v0 + 1;
            String v0_1 = ".try" + v0;
            v2 = new StringBuilder();
            v2.append(".try");
            v2.append(v1);
            return arg4.replace(((CharSequence)v0_1), v2.toString());
        }

        return arg4 + ".try" + "1";
    }

    public static List filterMinidumpFilesOnUid(File[] arg5, int arg6) {
        ArrayList v0 = new ArrayList();
        int v1 = arg5.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            File v3 = arg5[v2];
            if(CrashFileManager.belongsToUid(v3, arg6)) {
                ((List)v0).add(v3);
            }
        }

        return ((List)v0);
    }

    @VisibleForTesting File[] getAllTempFiles() {
        return this.listCrashFiles(CrashFileManager.TMP_PATTERN);
    }

    @VisibleForTesting public File[] getAllUploadedFiles() {
        return this.listCrashFiles(CrashFileManager.UPLOADED_MINIDUMP_PATTERN);
    }

    @VisibleForTesting public File getCrashDirectory() {
        return new File(this.mCacheDir, "Crash Reports");
    }

    public File getCrashFile(String arg3) {
        return new File(this.getCrashDirectory(), arg3);
    }

    public File getCrashFileWithLocalId(String arg9) {
        Pattern v0 = null;
        File[] v1 = this.listCrashFiles(v0);
        int v2 = v1.length;
        int v4;
        for(v4 = 0; v4 < v2; ++v4) {
            File v5 = v1[v4];
            if(!v5.getName().contains(".dmp") && !v5.getName().contains(".skipped") && !v5.getName().contains(".forced")) {
            }
            else if(v5.getName().split("\\.")[0].endsWith(arg9)) {
                return v5;
            }
        }

        return ((File)v0);
    }

    public File getCrashUploadLogFile() {
        return new File(this.getCrashDirectory(), "uploads.log");
    }

    @VisibleForTesting static File[] getFilesBelowMaxTries(File[] arg5, int arg6) {
        ArrayList v0 = new ArrayList();
        int v1 = arg5.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            File v3 = arg5[v2];
            if(CrashFileManager.readAttemptNumber(v3.getName()) < arg6) {
                ((List)v0).add(v3);
            }
        }

        return ((List)v0).toArray(new File[((List)v0).size()]);
    }

    public File[] getMinidumpsReadyForUpload(int arg2) {
        return CrashFileManager.getFilesBelowMaxTries(this.listCrashFiles(CrashFileManager.MINIDUMP_READY_FOR_UPLOAD_PATTERN), arg2);
    }

    public File[] getMinidumpsSansLogcat() {
        return this.listCrashFiles(CrashFileManager.MINIDUMP_SANS_LOGCAT_PATTERN);
    }

    public static boolean isForcedUpload(File arg1) {
        return arg1.getName().contains(".forced");
    }

    public static boolean isMinidumpSansLogcat(String arg1) {
        return CrashFileManager.MINIDUMP_SANS_LOGCAT_PATTERN.matcher(((CharSequence)arg1)).find();
    }

    @VisibleForTesting File[] listCrashFiles(@Nullable Pattern arg4) {
        FilenameFilter v1_1;
        File v0 = this.getCrashDirectory();
        if(arg4 != null) {
            org.chromium.components.minidump_uploader.CrashFileManager$2 v1 = new FilenameFilter(arg4) {
                public boolean accept(File arg1, String arg2) {
                    return this.val$pattern.matcher(((CharSequence)arg2)).find();
                }
            };
        }
        else {
            v1_1 = null;
        }

        File[] v4 = v0.listFiles(v1_1);
        if(v4 == null) {
            Log.w("CrashFileManager", v0.getAbsolutePath() + " does not exist or is not a directory", new Object[0]);
            return new File[0];
        }

        Arrays.sort(((Object[])v4), CrashFileManager.sFileComparator);
        return v4;
    }

    public static void markUploadSkipped(File arg1) {
        CrashFileManager.renameCrashDumpFollowingUpload(arg1, ".skipped");
    }

    public static void markUploadSuccess(File arg1) {
        CrashFileManager.renameCrashDumpFollowingUpload(arg1, ".up");
    }

    public static int readAttemptNumber(String arg0) {
        int v0 = CrashFileManager.readAttemptNumberInternal(arg0);
        if(v0 >= 0) {
        }
        else {
            v0 = 0;
        }

        return v0;
    }

    @VisibleForTesting static int readAttemptNumberInternal(String arg3) {
        int v0 = arg3.lastIndexOf(".try");
        int v1 = -1;
        if(v0 < 0) {
            return v1;
        }

        arg3 = arg3.substring(v0 + ".try".length());
        Scanner v0_1 = new Scanner(arg3).useDelimiter("[^0-9]+");
        try {
            v0 = v0_1.nextInt();
            if(arg3.indexOf(Integer.toString(v0)) != 0) {
                return -1;
            }
        }
        catch(NoSuchElementException ) {
            return v1;
        }

        return v0;
    }

    private static void renameCrashDumpFollowingUpload(File arg3, String arg4) {
        if(!arg3.renameTo(new File(arg3.getPath().replace(".dmp", ((CharSequence)arg4)).replace(".forced", ((CharSequence)arg4))))) {
            Log.w("CrashFileManager", "Failed to rename " + arg3, new Object[0]);
            if(!arg3.delete()) {
                Log.w("CrashFileManager", "Failed to delete " + arg3, new Object[0]);
            }
        }
    }

    public static String tryIncrementAttemptNumber(File arg2) {
        String v0 = CrashFileManager.filenameWithIncrementedAttemptNumber(arg2.getPath());
        if(arg2.renameTo(new File(v0))) {
        }
        else {
            v0 = null;
        }

        return v0;
    }

    public static File trySetForcedUpload(File arg4) {
        File v1 = null;
        if(arg4.getName().contains(".up")) {
            Log.w("CrashFileManager", "Refusing to reset upload attempt state for a file that has already been successfully uploaded: " + arg4.getName(), new Object[0]);
            return v1;
        }

        File v0 = new File(CrashFileManager.filenameWithForcedUploadState(arg4.getPath()));
        if(arg4.renameTo(v0)) {
        }
        else {
            v0 = v1;
        }

        return v0;
    }

    public static File trySetReadyForUpload(File arg3) {
        StringBuilder v1 = new StringBuilder();
        v1.append(arg3.getPath());
        v1.append(".try0");
        File v0 = new File(v1.toString());
        if(arg3.renameTo(v0)) {
        }
        else {
            v0 = null;
        }

        return v0;
    }
}

