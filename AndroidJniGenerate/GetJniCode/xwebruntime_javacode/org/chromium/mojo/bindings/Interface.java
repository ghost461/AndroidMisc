package org.chromium.mojo.bindings;

import java.io.Closeable;
import org.chromium.mojo.bindings.interfacecontrol.QueryVersion;
import org.chromium.mojo.bindings.interfacecontrol.RequireVersion;
import org.chromium.mojo.bindings.interfacecontrol.RunInput;
import org.chromium.mojo.bindings.interfacecontrol.RunMessageParams;
import org.chromium.mojo.bindings.interfacecontrol.RunOrClosePipeInput;
import org.chromium.mojo.bindings.interfacecontrol.RunOrClosePipeMessageParams;
import org.chromium.mojo.bindings.interfacecontrol.RunResponseMessageParams;
import org.chromium.mojo.system.Core;
import org.chromium.mojo.system.MessagePipeHandle;
import org.chromium.mojo.system.MojoException;
import org.chromium.mojo.system.Pair;

public interface Interface extends Closeable, ConnectionErrorHandler {
    public abstract class AbstractProxy implements Proxy {
        public class HandlerImpl implements ConnectionErrorHandler, Handler {
            private final Core mCore;
            private ConnectionErrorHandler mErrorHandler;
            private final MessageReceiverWithResponder mMessageReceiver;
            private int mVersion;

            protected HandlerImpl(Core arg1, MessageReceiverWithResponder arg2) {
                super();
                this.mCore = arg1;
                this.mMessageReceiver = arg2;
            }

            static int access$000(HandlerImpl arg0) {
                return arg0.mVersion;
            }

            static int access$002(HandlerImpl arg0, int arg1) {
                arg0.mVersion = arg1;
                return arg1;
            }

            public void close() {
                this.mMessageReceiver.close();
            }

            public Core getCore() {
                return this.mCore;
            }

            public MessageReceiverWithResponder getMessageReceiver() {
                return this.mMessageReceiver;
            }

            public int getVersion() {
                return this.mVersion;
            }

            public void onConnectionError(MojoException arg2) {
                if(this.mErrorHandler != null) {
                    this.mErrorHandler.onConnectionError(arg2);
                }
            }

            public MessagePipeHandle passHandle() {
                return this.mMessageReceiver.passHandle();
            }

            public void queryVersion(Callback1 arg5) {
                RunMessageParams v0 = new RunMessageParams();
                v0.input = new RunInput();
                v0.input.setQueryVersion(new QueryVersion());
                InterfaceControlMessagesHelper.sendRunMessage(this.getCore(), this.mMessageReceiver, v0, new Callback1(arg5) {
                    public void call(Object arg1) {
                        this.call(((RunResponseMessageParams)arg1));
                    }

                    public void call(RunResponseMessageParams arg2) {
                        if(arg2.output != null && arg2.output.which() == 0) {
                            HandlerImpl.this.mVersion = arg2.output.getQueryVersionResult().version;
                        }

                        this.val$callback.call(Integer.valueOf(HandlerImpl.this.mVersion));
                    }
                });
            }

            public void requireVersion(int arg4) {
                if(this.mVersion >= arg4) {
                    return;
                }

                this.mVersion = arg4;
                RunOrClosePipeMessageParams v0 = new RunOrClosePipeMessageParams();
                v0.input = new RunOrClosePipeInput();
                v0.input.setRequireVersion(new RequireVersion());
                v0.input.getRequireVersion().version = arg4;
                InterfaceControlMessagesHelper.sendRunOrClosePipeMessage(this.getCore(), this.mMessageReceiver, v0);
            }

            public void setErrorHandler(ConnectionErrorHandler arg1) {
                this.mErrorHandler = arg1;
            }

            void setVersion(int arg1) {
                this.mVersion = arg1;
            }
        }

        private final HandlerImpl mHandler;

        protected AbstractProxy(Core arg2, MessageReceiverWithResponder arg3) {
            super();
            this.mHandler = new HandlerImpl(arg2, arg3);
        }

        public void close() {
            this.mHandler.close();
        }

        public HandlerImpl getProxyHandler() {
            return this.mHandler;
        }

        public Handler getProxyHandler() {
            return this.getProxyHandler();
        }

        public void onConnectionError(MojoException arg2) {
            this.mHandler.onConnectionError(arg2);
        }
    }

    public abstract class Manager {
        public Manager() {
            super();
        }

        public final InterfaceRequest asInterfaceRequest(MessagePipeHandle arg2) {
            return new InterfaceRequest(arg2);
        }

        public final Proxy attachProxy(MessagePipeHandle arg3, int arg4) {
            RouterImpl v0 = new RouterImpl(arg3);
            Proxy v3 = this.attachProxy(arg3.getCore(), ((Router)v0));
            DelegatingConnectionErrorHandler v1 = new DelegatingConnectionErrorHandler();
            v1.addConnectionErrorHandler(((ConnectionErrorHandler)v3));
            v0.setErrorHandler(((ConnectionErrorHandler)v1));
            v0.start();
            v3.getProxyHandler().setVersion(arg4);
            return v3;
        }

        final Proxy attachProxy(Core arg2, Router arg3) {
            return this.buildProxy(arg2, new AutoCloseableRouter(arg2, arg3));
        }

        public void bind(Interface arg2, MessagePipeHandle arg3) {
            RouterImpl v0 = new RouterImpl(arg3);
            this.bind(arg3.getCore(), arg2, ((Router)v0));
            ((Router)v0).start();
        }

        public final void bind(Interface arg1, InterfaceRequest arg2) {
            this.bind(arg1, arg2.passHandle());
        }

        final void bind(Core arg1, Interface arg2, Router arg3) {
            arg3.setErrorHandler(((ConnectionErrorHandler)arg2));
            arg3.setIncomingMessageReceiver(this.buildStub(arg1, arg2));
        }

        protected abstract Interface[] buildArray(int arg1);

        protected abstract Proxy buildProxy(Core arg1, MessageReceiverWithResponder arg2);

        protected abstract Stub buildStub(Core arg1, Interface arg2);

        public final Pair getInterfaceRequest(Core arg3) {
            Pair v3 = arg3.createMessagePipe(null);
            return Pair.create(this.attachProxy(v3.first, 0), new InterfaceRequest(v3.second));
        }

        public abstract String getName();

        public abstract int getVersion();
    }

    public interface Proxy extends Interface {
        public interface Handler extends Closeable {
            int getVersion();

            MessagePipeHandle passHandle();

            void queryVersion(Callback1 arg1);

            void requireVersion(int arg1);

            void setErrorHandler(ConnectionErrorHandler arg1);
        }

        Handler getProxyHandler();
    }

    public abstract class Stub implements MessageReceiverWithResponder {
        private final Core mCore;
        private final Interface mImpl;

        public Stub(Core arg1, Interface arg2) {
            super();
            this.mCore = arg1;
            this.mImpl = arg2;
        }

        public void close() {
            this.mImpl.close();
        }

        protected Core getCore() {
            return this.mCore;
        }

        protected Interface getImpl() {
            return this.mImpl;
        }
    }

    void close();
}

