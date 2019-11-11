package android.support.v4.net;

import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketImpl;

class DatagramSocketWrapper extends Socket {
    class DatagramSocketImplWrapper extends SocketImpl {
        public DatagramSocketImplWrapper(DatagramSocket arg1, FileDescriptor arg2) {
            super();
            this.localport = arg1.getLocalPort();
            this.fd = arg2;
        }

        protected void accept(SocketImpl arg1) throws IOException {
            throw new UnsupportedOperationException();
        }

        protected int available() throws IOException {
            throw new UnsupportedOperationException();
        }

        protected void bind(InetAddress arg1, int arg2) throws IOException {
            throw new UnsupportedOperationException();
        }

        protected void close() throws IOException {
            throw new UnsupportedOperationException();
        }

        protected void connect(String arg1, int arg2) throws IOException {
            throw new UnsupportedOperationException();
        }

        protected void connect(InetAddress arg1, int arg2) throws IOException {
            throw new UnsupportedOperationException();
        }

        protected void connect(SocketAddress arg1, int arg2) throws IOException {
            throw new UnsupportedOperationException();
        }

        protected void create(boolean arg1) throws IOException {
            throw new UnsupportedOperationException();
        }

        protected InputStream getInputStream() throws IOException {
            throw new UnsupportedOperationException();
        }

        public Object getOption(int arg1) throws SocketException {
            throw new UnsupportedOperationException();
        }

        protected OutputStream getOutputStream() throws IOException {
            throw new UnsupportedOperationException();
        }

        protected void listen(int arg1) throws IOException {
            throw new UnsupportedOperationException();
        }

        protected void sendUrgentData(int arg1) throws IOException {
            throw new UnsupportedOperationException();
        }

        public void setOption(int arg1, Object arg2) throws SocketException {
            throw new UnsupportedOperationException();
        }
    }

    public DatagramSocketWrapper(DatagramSocket arg2, FileDescriptor arg3) throws SocketException {
        super(new DatagramSocketImplWrapper(arg2, arg3));
    }
}

