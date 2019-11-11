package org.chromium.mojo.system.impl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.base.annotations.MainDex;
import org.chromium.mojo.system.Core$HandleSignals;
import org.chromium.mojo.system.Core$HandleSignalsState;
import org.chromium.mojo.system.Core;
import org.chromium.mojo.system.DataPipe$CreateOptions;
import org.chromium.mojo.system.DataPipe$ReadFlags;
import org.chromium.mojo.system.DataPipe$WriteFlags;
import org.chromium.mojo.system.Handle;
import org.chromium.mojo.system.MessagePipeHandle$ReadMessageResult;
import org.chromium.mojo.system.MojoException;
import org.chromium.mojo.system.Pair;
import org.chromium.mojo.system.ResultAnd;
import org.chromium.mojo.system.RunLoop;
import org.chromium.mojo.system.SharedBufferHandle$DuplicateOptions;
import org.chromium.mojo.system.SharedBufferHandle$MapFlags;
import org.chromium.mojo.system.SharedBufferHandle;
import org.chromium.mojo.system.UntypedHandle;
import org.chromium.mojo.system.Watcher;

@JNINamespace(value="mojo::android") @MainDex public class CoreImpl implements Core {
    class org.chromium.mojo.system.impl.CoreImpl$1 {
    }

    final class IntegerPair extends Pair {
        public IntegerPair(Integer arg1, Integer arg2) {
            super(arg1, arg2);
        }
    }

    class LazyHolder {
        private static final Core INSTANCE;

        static {
            LazyHolder.INSTANCE = new CoreImpl(null);
        }

        private LazyHolder() {
            super();
        }

        static Core access$100() {
            return LazyHolder.INSTANCE;
        }
    }

    private static final int FLAG_SIZE = 4;
    private static final int HANDLE_SIZE = 4;
    static final int INVALID_HANDLE = 0;
    private static final int MOJO_READ_DATA_FLAG_DISCARD = 2;
    private final int mByteBufferOffset;
    private final ThreadLocal mCurrentRunLoop;

    private CoreImpl() {
        super();
        this.mCurrentRunLoop = new ThreadLocal();
        this.mByteBufferOffset = this.nativeGetNativeBufferOffset(ByteBuffer.allocateDirect(8), 8);
    }

    CoreImpl(org.chromium.mojo.system.impl.CoreImpl$1 arg1) {
        this();
    }

    public UntypedHandle acquireNativeHandle(int arg2) {
        return new UntypedHandleImpl(this, arg2);
    }

    private ByteBuffer allocateDirectBuffer(int arg2) {
        ByteBuffer v2 = ByteBuffer.allocateDirect(arg2 + this.mByteBufferOffset);
        if(this.mByteBufferOffset != 0) {
            v2.position(this.mByteBufferOffset);
            v2 = v2.slice();
        }

        return v2.order(ByteOrder.nativeOrder());
    }

    ByteBuffer beginReadData(DataPipeConsumerHandleImpl arg1, int arg2, ReadFlags arg3) {
        ResultAnd v1 = this.nativeBeginReadData(arg1.getMojoHandle(), arg2, arg3.getFlags());
        if(v1.getMojoResult() != 0) {
            throw new MojoException(v1.getMojoResult());
        }

        return v1.getValue().asReadOnlyBuffer();
    }

    ByteBuffer beginWriteData(DataPipeProducerHandleImpl arg1, int arg2, WriteFlags arg3) {
        ResultAnd v1 = this.nativeBeginWriteData(arg1.getMojoHandle(), arg2, arg3.getFlags());
        if(v1.getMojoResult() != 0) {
            throw new MojoException(v1.getMojoResult());
        }

        return v1.getValue();
    }

    void clearCurrentRunLoop() {
        this.mCurrentRunLoop.remove();
    }

    void close(int arg2) {
        arg2 = this.nativeClose(arg2);
        if(arg2 != 0) {
            throw new MojoException(arg2);
        }
    }

    int closeWithResult(int arg1) {
        return this.nativeClose(arg1);
    }

    public Pair createDataPipe(CreateOptions arg4) {
        ByteBuffer v1;
        if(arg4 != null) {
            v1 = this.allocateDirectBuffer(16);
            v1.putInt(0, 16);
            v1.putInt(4, arg4.getFlags().getFlags());
            v1.putInt(8, arg4.getElementNumBytes());
            v1.putInt(12, arg4.getCapacityNumBytes());
        }
        else {
            v1 = null;
        }

        ResultAnd v4 = this.nativeCreateDataPipe(v1);
        if(v4.getMojoResult() != 0) {
            throw new MojoException(v4.getMojoResult());
        }

        return Pair.create(new DataPipeProducerHandleImpl(this, v4.getValue().first.intValue()), new DataPipeConsumerHandleImpl(this, v4.getValue().second.intValue()));
    }

    public RunLoop createDefaultRunLoop() {
        if(this.mCurrentRunLoop.get() != null) {
            throw new MojoException(9);
        }

        BaseRunLoop v0 = new BaseRunLoop(this);
        this.mCurrentRunLoop.set(v0);
        return ((RunLoop)v0);
    }

    public Pair createMessagePipe(org.chromium.mojo.system.MessagePipeHandle$CreateOptions arg4) {
        ByteBuffer v1;
        if(arg4 != null) {
            v1 = this.allocateDirectBuffer(8);
            v1.putInt(0, 8);
            v1.putInt(4, arg4.getFlags().getFlags());
        }
        else {
            v1 = null;
        }

        ResultAnd v4 = this.nativeCreateMessagePipe(v1);
        if(v4.getMojoResult() != 0) {
            throw new MojoException(v4.getMojoResult());
        }

        return Pair.create(new MessagePipeHandleImpl(this, v4.getValue().first.intValue()), new MessagePipeHandleImpl(this, v4.getValue().second.intValue()));
    }

    public SharedBufferHandle createSharedBuffer(org.chromium.mojo.system.SharedBufferHandle$CreateOptions arg4, long arg5) {
        ByteBuffer v1;
        if(arg4 != null) {
            v1 = this.allocateDirectBuffer(8);
            v1.putInt(0, 8);
            v1.putInt(4, arg4.getFlags().getFlags());
        }
        else {
            v1 = null;
        }

        ResultAnd v4 = this.nativeCreateSharedBuffer(v1, arg5);
        if(v4.getMojoResult() != 0) {
            throw new MojoException(v4.getMojoResult());
        }

        return new SharedBufferHandleImpl(this, v4.getValue().intValue());
    }

    int discardData(DataPipeConsumerHandleImpl arg2, int arg3, ReadFlags arg4) {
        ResultAnd v2 = this.nativeReadData(arg2.getMojoHandle(), null, arg3, arg4.getFlags() | 2);
        if(v2.getMojoResult() != 0) {
            throw new MojoException(v2.getMojoResult());
        }

        return v2.getValue().intValue();
    }

    SharedBufferHandle duplicate(SharedBufferHandleImpl arg4, DuplicateOptions arg5) {
        ByteBuffer v1;
        if(arg5 != null) {
            v1 = this.allocateDirectBuffer(8);
            v1.putInt(0, 8);
            v1.putInt(4, arg5.getFlags().getFlags());
        }
        else {
            v1 = null;
        }

        ResultAnd v4 = this.nativeDuplicate(arg4.getMojoHandle(), v1);
        if(v4.getMojoResult() != 0) {
            throw new MojoException(v4.getMojoResult());
        }

        return new SharedBufferHandleImpl(this, v4.getValue().intValue());
    }

    void endReadData(DataPipeConsumerHandleImpl arg1, int arg2) {
        int v1 = this.nativeEndReadData(arg1.getMojoHandle(), arg2);
        if(v1 != 0) {
            throw new MojoException(v1);
        }
    }

    void endWriteData(DataPipeProducerHandleImpl arg1, int arg2) {
        int v1 = this.nativeEndWriteData(arg1.getMojoHandle(), arg2);
        if(v1 != 0) {
            throw new MojoException(v1);
        }
    }

    private static int filterMojoResultForWait(int arg1) {
        if(CoreImpl.isUnrecoverableError(arg1)) {
            throw new MojoException(arg1);
        }

        return arg1;
    }

    public RunLoop getCurrentRunLoop() {
        return this.mCurrentRunLoop.get();
    }

    public static Core getInstance() {
        return LazyHolder.access$100();
    }

    private int getMojoHandle(Handle arg2) {
        if(arg2.isValid()) {
            return ((HandleBase)arg2).getMojoHandle();
        }

        return 0;
    }

    public long getTimeTicksNow() {
        return this.nativeGetTimeTicksNow();
    }

    public Watcher getWatcher() {
        return new WatcherImpl();
    }

    private static boolean isUnrecoverableError(int arg1) {
        if(arg1 != 4 && arg1 != 9) {
            switch(arg1) {
                case 0: 
                case 1: {
                    return 0;
                }
                default: {
                    return 1;
                }
            }
        }

        return 0;
    }

    ByteBuffer map(SharedBufferHandleImpl arg8, long arg9, long arg11, MapFlags arg13) {
        ResultAnd v8 = this.nativeMap(arg8.getMojoHandle(), arg9, arg11, arg13.getFlags());
        if(v8.getMojoResult() != 0) {
            throw new MojoException(v8.getMojoResult());
        }

        return v8.getValue();
    }

    private native ResultAnd nativeBeginReadData(int arg1, int arg2, int arg3) {
    }

    private native ResultAnd nativeBeginWriteData(int arg1, int arg2, int arg3) {
    }

    private native int nativeClose(int arg1) {
    }

    private native ResultAnd nativeCreateDataPipe(ByteBuffer arg1) {
    }

    private native ResultAnd nativeCreateMessagePipe(ByteBuffer arg1) {
    }

    private native ResultAnd nativeCreateSharedBuffer(ByteBuffer arg1, long arg2) {
    }

    private native ResultAnd nativeDuplicate(int arg1, ByteBuffer arg2) {
    }

    private native int nativeEndReadData(int arg1, int arg2) {
    }

    private native int nativeEndWriteData(int arg1, int arg2) {
    }

    private native int nativeGetNativeBufferOffset(ByteBuffer arg1, int arg2) {
    }

    private native long nativeGetTimeTicksNow() {
    }

    private native ResultAnd nativeMap(int arg1, long arg2, long arg3, int arg4) {
    }

    private native int nativeQueryHandleSignalsState(int arg1, ByteBuffer arg2) {
    }

    private native ResultAnd nativeReadData(int arg1, ByteBuffer arg2, int arg3, int arg4) {
    }

    private native ResultAnd nativeReadMessage(int arg1, int arg2) {
    }

    private native int nativeUnmap(ByteBuffer arg1) {
    }

    private native ResultAnd nativeWriteData(int arg1, ByteBuffer arg2, int arg3, int arg4) {
    }

    private native int nativeWriteMessage(int arg1, ByteBuffer arg2, int arg3, ByteBuffer arg4, int arg5) {
    }

    @CalledByNative private static ResultAnd newNativeCreationResult(int arg2, int arg3, int arg4) {
        return new ResultAnd(arg2, new IntegerPair(Integer.valueOf(arg3), Integer.valueOf(arg4)));
    }

    @CalledByNative private static ResultAnd newReadMessageResult(int arg1, byte[] arg2, int[] arg3) {
        ReadMessageResult v0 = new ReadMessageResult();
        if(arg1 == 0) {
            v0.mData = arg2;
            v0.mRawHandles = arg3;
        }

        return new ResultAnd(arg1, v0);
    }

    @CalledByNative private static ResultAnd newResultAndBuffer(int arg1, ByteBuffer arg2) {
        return new ResultAnd(arg1, arg2);
    }

    @CalledByNative private static ResultAnd newResultAndInteger(int arg1, int arg2) {
        return new ResultAnd(arg1, Integer.valueOf(arg2));
    }

    HandleSignalsState queryHandleSignalsState(int arg5) {
        ByteBuffer v0 = this.allocateDirectBuffer(8);
        arg5 = this.nativeQueryHandleSignalsState(arg5, v0);
        if(arg5 != 0) {
            throw new MojoException(arg5);
        }

        return new HandleSignalsState(new HandleSignals(v0.getInt(0)), new HandleSignals(v0.getInt(4)));
    }

    ResultAnd readData(DataPipeConsumerHandleImpl arg2, ByteBuffer arg3, ReadFlags arg4) {
        int v2 = arg2.getMojoHandle();
        int v0 = arg3 == null ? 0 : arg3.capacity();
        ResultAnd v2_1 = this.nativeReadData(v2, arg3, v0, arg4.getFlags());
        if(v2_1.getMojoResult() != 0 && v2_1.getMojoResult() != 17) {
            throw new MojoException(v2_1.getMojoResult());
        }

        if(v2_1.getMojoResult() == 0 && arg3 != null) {
            arg3.limit(v2_1.getValue().intValue());
        }

        return v2_1;
    }

    ResultAnd readMessage(MessagePipeHandleImpl arg7, org.chromium.mojo.system.MessagePipeHandle$ReadFlags arg8) {
        ResultAnd v7 = this.nativeReadMessage(arg7.getMojoHandle(), arg8.getFlags());
        if(v7.getMojoResult() != 0 && v7.getMojoResult() != 17) {
            throw new MojoException(v7.getMojoResult());
        }

        Object v8 = v7.getValue();
        int[] v0 = ((ReadMessageResult)v8).mRawHandles;
        int v1 = 0;
        if(v0 == null || v0.length == 0) {
            ((ReadMessageResult)v8).mHandles = new ArrayList(0);
        }
        else {
            ((ReadMessageResult)v8).mHandles = new ArrayList(v0.length);
            int v2 = v0.length;
            while(v1 < v2) {
                ((ReadMessageResult)v8).mHandles.add(new UntypedHandleImpl(this, v0[v1]));
                ++v1;
            }
        }

        return v7;
    }

    void unmap(ByteBuffer arg2) {
        int v2 = this.nativeUnmap(arg2);
        if(v2 != 0) {
            throw new MojoException(v2);
        }
    }

    ResultAnd writeData(DataPipeProducerHandleImpl arg2, ByteBuffer arg3, WriteFlags arg4) {
        return this.nativeWriteData(arg2.getMojoHandle(), arg3, arg3.limit(), arg4.getFlags());
    }

    void writeMessage(MessagePipeHandleImpl arg9, ByteBuffer arg10, List arg11, org.chromium.mojo.system.MessagePipeHandle$WriteFlags arg12) {
        ByteBuffer v1;
        if(arg11 == null || (arg11.isEmpty())) {
            v1 = null;
        }
        else {
            v1 = this.allocateDirectBuffer(arg11.size() * 4);
            Iterator v2 = arg11.iterator();
            while(v2.hasNext()) {
                v1.putInt(this.getMojoHandle(v2.next()));
            }

            v1.position(0);
        }

        ByteBuffer v6 = v1;
        int v3 = arg9.getMojoHandle();
        int v5 = arg10 == null ? 0 : arg10.limit();
        int v9 = this.nativeWriteMessage(v3, arg10, v5, v6, arg12.getFlags());
        if(v9 != 0) {
            throw new MojoException(v9);
        }

        if(arg11 != null) {
            Iterator v9_1 = arg11.iterator();
            while(v9_1.hasNext()) {
                Object v10 = v9_1.next();
                if(!((Handle)v10).isValid()) {
                    continue;
                }

                ((HandleBase)v10).invalidateHandle();
            }
        }
    }
}

