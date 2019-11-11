package android.support.multidex;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.zip.CRC32;
import java.util.zip.ZipException;

final class ZipUtil {
    class CentralDirectory {
        long offset;
        long size;

        CentralDirectory() {
            super();
        }
    }

    private static final int BUFFER_SIZE = 0x4000;
    private static final int ENDHDR = 22;
    private static final int ENDSIG = 101010256;

    ZipUtil() {
        super();
    }

    static long computeCrcOfCentralDir(RandomAccessFile arg11, CentralDirectory arg12) throws IOException {
        CRC32 v0 = new CRC32();
        long v1 = arg12.size;
        arg11.seek(arg12.offset);
        long v3 = 0x4000;
        int v12 = ((int)Math.min(v3, v1));
        byte[] v5 = new byte[0x4000];
        v12 = arg11.read(v5, 0, v12);
        while(v12 != -1) {
            v0.update(v5, 0, v12);
            long v9 = v1 - (((long)v12));
            if(v9 == 0) {
            }
            else {
                v12 = arg11.read(v5, 0, ((int)Math.min(v3, v9)));
                v1 = v9;
                continue;
            }

            break;
        }

        return v0.getValue();
    }

    static CentralDirectory findCentralDirectory(RandomAccessFile arg10) throws IOException, ZipException {
        long v4 = arg10.length() - 22;
        long v0 = 0;
        if(v4 < v0) {
            StringBuilder v1 = new StringBuilder();
            v1.append("File too short to be a zip file: ");
            v1.append(arg10.length());
            throw new ZipException(v1.toString());
        }

        long v6 = v4 - 0x10000;
        if(v6 < v0) {
        }
        else {
            v0 = v6;
        }

        int v2 = Integer.reverseBytes(101010256);
        while(true) {
            arg10.seek(v4);
            if(arg10.readInt() == v2) {
                break;
            }

            long v8 = v4 - 1;
            if(v8 < v0) {
                throw new ZipException("End Of Central Directory signature not found");
            }

            v4 = v8;
        }

        arg10.skipBytes(2);
        arg10.skipBytes(2);
        arg10.skipBytes(2);
        arg10.skipBytes(2);
        CentralDirectory v0_1 = new CentralDirectory();
        v0_1.size = (((long)Integer.reverseBytes(arg10.readInt()))) & 0xFFFFFFFFL;
        v0_1.offset = (((long)Integer.reverseBytes(arg10.readInt()))) & 0xFFFFFFFFL;
        return v0_1;
    }

    static long getZipCrc(File arg3) throws IOException {
        long v1;
        RandomAccessFile v0 = new RandomAccessFile(arg3, "r");
        try {
            v1 = ZipUtil.computeCrcOfCentralDir(v0, ZipUtil.findCentralDirectory(v0));
        }
        catch(Throwable v3) {
            v0.close();
            throw v3;
        }

        v0.close();
        return v1;
    }
}

