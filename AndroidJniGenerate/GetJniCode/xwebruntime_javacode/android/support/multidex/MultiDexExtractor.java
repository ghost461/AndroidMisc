package android.support.multidex;

import android.content.Context;
import android.content.SharedPreferences$Editor;
import android.content.SharedPreferences;
import android.os.Build$VERSION;
import android.util.Log;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

final class MultiDexExtractor implements Closeable {
    class ExtractedDex extends File {
        public long crc;

        public ExtractedDex(File arg1, String arg2) {
            super(arg1, arg2);
            this.crc = -1;
        }
    }

    private static final int BUFFER_SIZE = 0x4000;
    private static final String DEX_PREFIX = "classes";
    static final String DEX_SUFFIX = ".dex";
    private static final String EXTRACTED_NAME_EXT = ".classes";
    static final String EXTRACTED_SUFFIX = ".zip";
    private static final String KEY_CRC = "crc";
    private static final String KEY_DEX_CRC = "dex.crc.";
    private static final String KEY_DEX_NUMBER = "dex.number";
    private static final String KEY_DEX_TIME = "dex.time.";
    private static final String KEY_TIME_STAMP = "timestamp";
    private static final String LOCK_FILENAME = "MultiDex.lock";
    private static final int MAX_EXTRACT_ATTEMPTS = 3;
    private static final long NO_VALUE = -1;
    private static final String PREFS_FILE = "multidex.version";
    private static final String TAG = "MultiDex";
    private final FileLock cacheLock;
    private final File dexDir;
    private final FileChannel lockChannel;
    private final RandomAccessFile lockRaf;
    private final File sourceApk;
    private final long sourceCrc;

    MultiDexExtractor(File arg4, File arg5) throws IOException {
        super();
        Log.i("MultiDex", "MultiDexExtractor(" + arg4.getPath() + ", " + arg5.getPath() + ")");
        this.sourceApk = arg4;
        this.dexDir = arg5;
        this.sourceCrc = MultiDexExtractor.getZipCrc(arg4);
        arg4 = new File(arg5, "MultiDex.lock");
        this.lockRaf = new RandomAccessFile(arg4, "rw");
        try {
            this.lockChannel = this.lockRaf.getChannel();
        }
        catch(Error v4) {
            goto label_58;
        }

        try {
            Log.i("MultiDex", "Blocking on lock " + arg4.getPath());
            this.cacheLock = this.lockChannel.lock();
            goto label_42;
        }
        catch(Error v4) {
            try {
                MultiDexExtractor.closeQuietly(this.lockChannel);
                throw v4;
            label_42:
                Log.i("MultiDex", arg4.getPath() + " locked");
                return;
            }
            catch(Error v4) {
            label_58:
                MultiDexExtractor.closeQuietly(this.lockRaf);
                throw v4;
            }
        }
    }

    private void clearDexDir() {
        File[] v0 = this.dexDir.listFiles(new FileFilter() {
            public boolean accept(File arg2) {
                return arg2.getName().equals("MultiDex.lock") ^ 1;
            }
        });
        if(v0 == null) {
            Log.w("MultiDex", "Failed to list secondary dex dir content (" + this.dexDir.getPath() + ").");
            return;
        }

        int v1_1 = v0.length;
        int v2;
        for(v2 = 0; v2 < v1_1; ++v2) {
            File v3 = v0[v2];
            Log.i("MultiDex", "Trying to delete old file " + v3.getPath() + " of size " + v3.length());
            if(!v3.delete()) {
                Log.w("MultiDex", "Failed to delete old file " + v3.getPath());
            }
            else {
                Log.i("MultiDex", "Deleted old file " + v3.getPath());
            }
        }
    }

    public void close() throws IOException {
        this.cacheLock.release();
        this.lockChannel.close();
        this.lockRaf.close();
    }

    private static void closeQuietly(Closeable arg2) {
        try {
            arg2.close();
        }
        catch(IOException v2) {
            Log.w("MultiDex", "Failed to close resource", ((Throwable)v2));
        }
    }

    private static void extract(ZipFile arg4, ZipEntry arg5, File arg6, String arg7) throws IOException, FileNotFoundException {
        ZipOutputStream v0_1;
        InputStream v4 = arg4.getInputStream(arg5);
        StringBuilder v0 = new StringBuilder();
        v0.append("tmp-");
        v0.append(arg7);
        File v7 = File.createTempFile(v0.toString(), ".zip", arg6.getParentFile());
        Log.i("MultiDex", "Extracting " + v7.getPath());
        try {
            v0_1 = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(v7)));
        }
        catch(Throwable v5) {
            goto label_94;
        }

        try {
            ZipEntry v1_1 = new ZipEntry("classes.dex");
            v1_1.setTime(arg5.getTime());
            v0_1.putNextEntry(v1_1);
            byte[] v5_1 = new byte[0x4000];
            int v1_2;
            for(v1_2 = v4.read(v5_1); v1_2 != -1; v1_2 = v4.read(v5_1)) {
                v0_1.write(v5_1, 0, v1_2);
            }

            v0_1.closeEntry();
        }
        catch(Throwable v5) {
            goto label_91;
        }

        try {
            v0_1.close();
            if(!v7.setReadOnly()) {
                v0 = new StringBuilder();
                v0.append("Failed to mark readonly \"");
                v0.append(v7.getAbsolutePath());
                v0.append("\" (tmp of \"");
                v0.append(arg6.getAbsolutePath());
                v0.append("\")");
                throw new IOException(v0.toString());
            }

            Log.i("MultiDex", "Renaming to " + arg6.getPath());
            if(v7.renameTo(arg6)) {
                goto label_87;
            }

            v0 = new StringBuilder();
            v0.append("Failed to rename \"");
            v0.append(v7.getAbsolutePath());
            v0.append("\" to \"");
            v0.append(arg6.getAbsolutePath());
            v0.append("\"");
            throw new IOException(v0.toString());
        }
        catch(Throwable v5) {
            goto label_94;
        }

    label_87:
        MultiDexExtractor.closeQuietly(((Closeable)v4));
        v7.delete();
        return;
        try {
        label_91:
            v0_1.close();
            throw v5;
        }
        catch(Throwable v5) {
        label_94:
            MultiDexExtractor.closeQuietly(((Closeable)v4));
            v7.delete();
            throw v5;
        }
    }

    private static SharedPreferences getMultiDexPreferences(Context arg3) {
        String v0 = "multidex.version";
        int v1 = Build$VERSION.SDK_INT < 11 ? 0 : 4;
        return arg3.getSharedPreferences(v0, v1);
    }

    private static long getTimeStamp(File arg6) {
        long v0 = arg6.lastModified();
        if(v0 == -1) {
            --v0;
        }

        return v0;
    }

    private static long getZipCrc(File arg6) throws IOException {
        long v0 = ZipUtil.getZipCrc(arg6);
        if(v0 == -1) {
            --v0;
        }

        return v0;
    }

    private static boolean isModified(Context arg7, File arg8, long arg9, String arg11) {
        boolean v7_1;
        SharedPreferences v7 = MultiDexExtractor.getMultiDexPreferences(arg7);
        StringBuilder v0 = new StringBuilder();
        v0.append(arg11);
        v0.append("timestamp");
        long v1 = -1;
        if(v7.getLong(v0.toString(), v1) == MultiDexExtractor.getTimeStamp(arg8)) {
            StringBuilder v8 = new StringBuilder();
            v8.append(arg11);
            v8.append("crc");
            if(v7.getLong(v8.toString(), v1) != arg9) {
                goto label_22;
            }
            else {
                v7_1 = false;
            }
        }
        else {
        label_22:
            v7_1 = true;
        }

        return v7_1;
    }

    List load(Context arg10, String arg11, boolean arg12) throws IOException {
        List v12_1;
        Log.i("MultiDex", "MultiDexExtractor.load(" + this.sourceApk.getPath() + ", " + arg12 + ", " + arg11 + ")");
        if(!this.cacheLock.isValid()) {
            throw new IllegalStateException("MultiDexExtractor was closed");
        }

        if(!arg12 && !MultiDexExtractor.isModified(arg10, this.sourceApk, this.sourceCrc, arg11)) {
            try {
                v12_1 = this.loadExistingExtractions(arg10, arg11);
            }
            catch(IOException v12) {
                Log.w("MultiDex", "Failed to reload existing extracted secondary dex files, falling back to fresh extraction", ((Throwable)v12));
                v12_1 = this.performExtractions();
                MultiDexExtractor.putStoredApkInfo(arg10, arg11, MultiDexExtractor.getTimeStamp(this.sourceApk), this.sourceCrc, v12_1);
            }

            goto label_61;
        }

        if(arg12) {
            Log.i("MultiDex", "Forced extraction must be performed.");
        }
        else {
            Log.i("MultiDex", "Detected that extraction must be performed.");
        }

        v12_1 = this.performExtractions();
        MultiDexExtractor.putStoredApkInfo(arg10, arg11, MultiDexExtractor.getTimeStamp(this.sourceApk), this.sourceCrc, v12_1);
    label_61:
        Log.i("MultiDex", "load found " + v12_1.size() + " secondary dex files");
        return v12_1;
    }

    private List loadExistingExtractions(Context arg18, String arg19) throws IOException {
        long v13;
        long v9;
        long v11;
        ExtractedDex v8;
        MultiDexExtractor v0 = this;
        String v1 = arg19;
        Log.i("MultiDex", "loading existing secondary dex files");
        String v2_1 = v0.sourceApk.getName() + ".classes";
        SharedPreferences v3 = MultiDexExtractor.getMultiDexPreferences(arg18);
        StringBuilder v4 = new StringBuilder();
        v4.append(v1);
        v4.append("dex.number");
        int v4_1 = v3.getInt(v4.toString(), 1);
        ArrayList v5 = new ArrayList(v4_1 - 1);
        int v6 = 2;
        while(true) {
            if(v6 > v4_1) {
                goto label_107;
            }

            StringBuilder v7 = new StringBuilder();
            v7.append(v2_1);
            v7.append(v6);
            v7.append(".zip");
            v8 = new ExtractedDex(v0.dexDir, v7.toString());
            if(!v8.isFile()) {
                goto label_95;
            }

            v8.crc = MultiDexExtractor.getZipCrc(((File)v8));
            v7 = new StringBuilder();
            v7.append(v1);
            v7.append("dex.crc.");
            v7.append(v6);
            v11 = v3.getLong(v7.toString(), -1);
            v7 = new StringBuilder();
            v7.append(v1);
            v7.append("dex.time.");
            v7.append(v6);
            v9 = v3.getLong(v7.toString(), -1);
            v13 = v8.lastModified();
            if(v9 == v13) {
                String v15 = v2_1;
                SharedPreferences v16 = v3;
                if(v11 != v8.crc) {
                }
                else {
                    ((List)v5).add(v8);
                    ++v6;
                    v2_1 = v15;
                    v3 = v16;
                    continue;
                }
            }

            break;
        }

        StringBuilder v3_1 = new StringBuilder();
        v3_1.append("Invalid extracted dex: ");
        v3_1.append(v8);
        v3_1.append(" (key \"");
        v3_1.append(v1);
        v3_1.append("\"), expected modification time: ");
        v3_1.append(v9);
        v3_1.append(", modification time: ");
        v3_1.append(v13);
        v3_1.append(", expected crc: ");
        v3_1.append(v11);
        v3_1.append(", file crc: ");
        v3_1.append(v8.crc);
        throw new IOException(v3_1.toString());
    label_95:
        v2 = new StringBuilder();
        v2.append("Missing extracted secondary dex file \'");
        v2.append(v8.getPath());
        v2.append("\'");
        throw new IOException(v2.toString());
    label_107:
        return ((List)v5);
    }

    private List performExtractions() throws IOException {
        String v0_1 = this.sourceApk.getName() + ".classes";
        this.clearDexDir();
        ArrayList v1 = new ArrayList();
        ZipFile v2 = new ZipFile(this.sourceApk);
        int v3 = 2;
        try {
            StringBuilder v4 = new StringBuilder();
            v4.append("classes");
            v4.append(v3);
            v4.append(".dex");
            ZipEntry v4_1 = v2.getEntry(v4.toString());
        label_24:
            while(v4_1 != null) {
                StringBuilder v5 = new StringBuilder();
                v5.append(v0_1);
                v5.append(v3);
                v5.append(".zip");
                ExtractedDex v6 = new ExtractedDex(this.dexDir, v5.toString());
                ((List)v1).add(v6);
                Log.i("MultiDex", "Extraction is needed for file " + v6);
                int v7_1 = 0;
                int v8 = 0;
                while(true) {
                    if(v7_1 < 3 && v8 == 0) {
                        ++v7_1;
                        MultiDexExtractor.extract(v2, v4_1, ((File)v6), v0_1);
                        try {
                            v6.crc = MultiDexExtractor.getZipCrc(((File)v6));
                            v8 = 1;
                            goto label_67;
                        }
                        catch(IOException v8_1) {
                            try {
                                Log.w("MultiDex", "Failed to read crc from " + v6.getAbsolutePath(), ((Throwable)v8_1));
                                v8 = 0;
                            label_67:
                                String v9 = "MultiDex";
                                v10 = new StringBuilder();
                                v10.append("Extraction ");
                                String v11 = v8 != 0 ? "succeeded" : "failed";
                                v10.append(v11);
                                v10.append(" \'");
                                v10.append(v6.getAbsolutePath());
                                v10.append("\': length ");
                                v10.append(v6.length());
                                v10.append(" - crc: ");
                                v10.append(v6.crc);
                                Log.i(v9, v10.toString());
                                if(v8 != 0) {
                                    continue;
                                }

                                v6.delete();
                                if(!v6.exists()) {
                                    continue;
                                }

                                Log.w("MultiDex", "Failed to delete corrupted secondary dex \'" + v6.getPath() + "\'");
                                continue;
                            label_107:
                                if(v8 == 0) {
                                    StringBuilder v1_1 = new StringBuilder();
                                    v1_1.append("Could not create zip file ");
                                    v1_1.append(v6.getAbsolutePath());
                                    v1_1.append(" for secondary dex (");
                                    v1_1.append(v3);
                                    v1_1.append(")");
                                    throw new IOException(v1_1.toString());
                                }

                                ++v3;
                                v4 = new StringBuilder();
                                v4.append("classes");
                                v4.append(v3);
                                v4.append(".dex");
                                v4_1 = v2.getEntry(v4.toString());
                                goto label_24;
                            }
                            catch(Throwable v0_2) {
                                goto label_142;
                            }
                        }
                    }

                    goto label_107;
                }
            }
        }
        catch(Throwable v0_2) {
            goto label_142;
        }

        try {
            v2.close();
        }
        catch(IOException v0_3) {
            Log.w("MultiDex", "Failed to close resource", ((Throwable)v0_3));
        }

        return ((List)v1);
        try {
        label_142:
            v2.close();
        }
        catch(IOException v1_2) {
            Log.w("MultiDex", "Failed to close resource", ((Throwable)v1_2));
        }

        throw v0_2;
    }

    private static void putStoredApkInfo(Context arg2, String arg3, long arg4, long arg6, List arg8) {
        SharedPreferences$Editor v2 = MultiDexExtractor.getMultiDexPreferences(arg2).edit();
        v2.putLong(arg3 + "timestamp", arg4);
        v2.putLong(arg3 + "crc", arg6);
        v2.putInt(arg3 + "dex.number", arg8.size() + 1);
        Iterator v4_1 = arg8.iterator();
        int v5;
        for(v5 = 2; v4_1.hasNext(); ++v5) {
            Object v6 = v4_1.next();
            v2.putLong(arg3 + "dex.crc." + v5, ((ExtractedDex)v6).crc);
            v2.putLong(arg3 + "dex.time." + v5, ((ExtractedDex)v6).lastModified());
        }

        v2.commit();
    }
}

