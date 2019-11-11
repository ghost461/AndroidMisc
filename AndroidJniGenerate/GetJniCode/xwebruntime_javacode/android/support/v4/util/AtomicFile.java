package android.support.v4.util;

import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class AtomicFile {
    private final File mBackupName;
    private final File mBaseName;

    public AtomicFile(File arg3) {
        super();
        this.mBaseName = arg3;
        StringBuilder v1 = new StringBuilder();
        v1.append(arg3.getPath());
        v1.append(".bak");
        this.mBackupName = new File(v1.toString());
    }

    public void delete() {
        this.mBaseName.delete();
        this.mBackupName.delete();
    }

    public void failWrite(FileOutputStream arg3) {
        if(arg3 != null) {
            AtomicFile.sync(arg3);
            try {
                arg3.close();
                this.mBaseName.delete();
                this.mBackupName.renameTo(this.mBaseName);
            }
            catch(IOException v3) {
                Log.w("AtomicFile", "failWrite: Got exception:", ((Throwable)v3));
            }
        }
    }

    public void finishWrite(FileOutputStream arg3) {
        if(arg3 != null) {
            AtomicFile.sync(arg3);
            try {
                arg3.close();
                this.mBackupName.delete();
            }
            catch(IOException v3) {
                Log.w("AtomicFile", "finishWrite: Got exception:", ((Throwable)v3));
            }
        }
    }

    public File getBaseFile() {
        return this.mBaseName;
    }

    public FileInputStream openRead() throws FileNotFoundException {
        if(this.mBackupName.exists()) {
            this.mBaseName.delete();
            this.mBackupName.renameTo(this.mBaseName);
        }

        return new FileInputStream(this.mBaseName);
    }

    public byte[] readFully() throws IOException {
        int v4;
        int v3;
        byte[] v1_1;
        FileInputStream v0 = this.openRead();
        try {
            v1_1 = new byte[v0.available()];
            v3 = 0;
            while(true) {
            label_5:
                v4 = v0.read(v1_1, v3, v1_1.length - v3);
                if(v4 > 0) {
                    break;
                }

                goto label_9;
            }
        }
        catch(Throwable v1) {
            goto label_22;
        }

        v3 += v4;
        try {
            v4 = v0.available();
            if(v4 <= v1_1.length - v3) {
                goto label_5;
            }

            byte[] v4_1 = new byte[v4 + v3];
            System.arraycopy(v1_1, 0, v4_1, 0, v3);
            v1_1 = v4_1;
            goto label_5;
        }
        catch(Throwable v1) {
            goto label_22;
        }

    label_9:
        v0.close();
        return v1_1;
    label_22:
        v0.close();
        throw v1;
    }

    public FileOutputStream startWrite() throws IOException {
        FileOutputStream v0;
        if(this.mBaseName.exists()) {
            if(this.mBackupName.exists()) {
                this.mBaseName.delete();
            }
            else if(!this.mBaseName.renameTo(this.mBackupName)) {
                Log.w("AtomicFile", "Couldn\'t rename file " + this.mBaseName + " to backup file " + this.mBackupName);
            }
        }

        try {
            v0 = new FileOutputStream(this.mBaseName);
        }
        catch(FileNotFoundException ) {
            if(!this.mBaseName.getParentFile().mkdirs()) {
                v1 = new StringBuilder();
                v1.append("Couldn\'t create directory ");
                v1.append(this.mBaseName);
                throw new IOException(v1.toString());
            }

            try {
                v0 = new FileOutputStream(this.mBaseName);
            }
            catch(FileNotFoundException ) {
                v1 = new StringBuilder();
                v1.append("Couldn\'t create ");
                v1.append(this.mBaseName);
                throw new IOException(v1.toString());
            }
        }

        return v0;
    }

    static boolean sync(FileOutputStream arg0) {
        if(arg0 != null) {
            try {
                arg0.getFD().sync();
            }
            catch(IOException ) {
                return 0;
            }
        }

        return 1;
    }
}

