package android.support.v4.net;

import android.net.TrafficStats;
import android.os.Build$VERSION;
import android.os.ParcelFileDescriptor;
import android.support.annotation.RequiresApi;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;

public final class TrafficStatsCompat {
    @RequiresApi(value=24) class TrafficStatsCompatApi24Impl extends TrafficStatsCompatBaseImpl {
        TrafficStatsCompatApi24Impl() {
            super();
        }

        public void tagDatagramSocket(DatagramSocket arg1) throws SocketException {
            TrafficStats.tagDatagramSocket(arg1);
        }

        public void untagDatagramSocket(DatagramSocket arg1) throws SocketException {
            TrafficStats.untagDatagramSocket(arg1);
        }
    }

    class TrafficStatsCompatBaseImpl {
        TrafficStatsCompatBaseImpl() {
            super();
        }

        public void tagDatagramSocket(DatagramSocket arg4) throws SocketException {
            ParcelFileDescriptor v0 = ParcelFileDescriptor.fromDatagramSocket(arg4);
            TrafficStats.tagSocket(new DatagramSocketWrapper(arg4, v0.getFileDescriptor()));
            v0.detachFd();
        }

        public void untagDatagramSocket(DatagramSocket arg4) throws SocketException {
            ParcelFileDescriptor v0 = ParcelFileDescriptor.fromDatagramSocket(arg4);
            TrafficStats.untagSocket(new DatagramSocketWrapper(arg4, v0.getFileDescriptor()));
            v0.detachFd();
        }
    }

    private static final TrafficStatsCompatBaseImpl IMPL;

    static {
        TrafficStatsCompat.IMPL = Build$VERSION.SDK_INT >= 24 ? new TrafficStatsCompatApi24Impl() : new TrafficStatsCompatBaseImpl();
    }

    private TrafficStatsCompat() {
        super();
    }

    @Deprecated public static void clearThreadStatsTag() {
        TrafficStats.clearThreadStatsTag();
    }

    @Deprecated public static int getThreadStatsTag() {
        return TrafficStats.getThreadStatsTag();
    }

    @Deprecated public static void incrementOperationCount(int arg0) {
        TrafficStats.incrementOperationCount(arg0);
    }

    @Deprecated public static void incrementOperationCount(int arg0, int arg1) {
        TrafficStats.incrementOperationCount(arg0, arg1);
    }

    @Deprecated public static void setThreadStatsTag(int arg0) {
        TrafficStats.setThreadStatsTag(arg0);
    }

    public static void tagDatagramSocket(DatagramSocket arg1) throws SocketException {
        TrafficStatsCompat.IMPL.tagDatagramSocket(arg1);
    }

    @Deprecated public static void tagSocket(Socket arg0) throws SocketException {
        TrafficStats.tagSocket(arg0);
    }

    public static void untagDatagramSocket(DatagramSocket arg1) throws SocketException {
        TrafficStatsCompat.IMPL.untagDatagramSocket(arg1);
    }

    @Deprecated public static void untagSocket(Socket arg0) throws SocketException {
        TrafficStats.untagSocket(arg0);
    }
}

