package org.chromium.device.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.DeserializationException;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Interface$AbstractProxy;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;
import org.chromium.mojo.bindings.InterfaceControlMessagesHelper;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.MessageHeader;
import org.chromium.mojo.bindings.MessageReceiver;
import org.chromium.mojo.bindings.MessageReceiverWithResponder;
import org.chromium.mojo.bindings.ServiceMessage;
import org.chromium.mojo.bindings.SideEffectFreeCloseable;
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo.system.Core;

class Sensor_Internal {
    final class org.chromium.device.mojom.Sensor_Internal$1 extends Manager {
        org.chromium.device.mojom.Sensor_Internal$1() {
            super();
        }

        public Sensor[] buildArray(int arg1) {
            return new Sensor[arg1];
        }

        public Interface[] buildArray(int arg1) {
            return this.buildArray(arg1);
        }

        public Proxy buildProxy(Core arg2, MessageReceiverWithResponder arg3) {
            return new Proxy(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Proxy buildProxy(Core arg1, MessageReceiverWithResponder arg2) {
            return this.buildProxy(arg1, arg2);
        }

        public Stub buildStub(Core arg2, Sensor arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((Sensor)arg2));
        }

        public String getName() {
            return "device::mojom::Sensor";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.device.mojom.Sensor$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void addConfiguration(SensorConfiguration arg7, AddConfigurationResponse arg8) {
            SensorAddConfigurationParams v0 = new SensorAddConfigurationParams();
            v0.configuration = arg7;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1, 1, 0)), new SensorAddConfigurationResponseParamsForwardToCallback(arg8));
        }

        public void configureReadingChangeNotifications(boolean arg5) {
            SensorConfigureReadingChangeNotificationsParams v0 = new SensorConfigureReadingChangeNotificationsParams();
            v0.enabled = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(5)));
        }

        public void getDefaultConfiguration(GetDefaultConfigurationResponse arg9) {
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(new SensorGetDefaultConfigurationParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0, 1, 0)), new SensorGetDefaultConfigurationResponseParamsForwardToCallback(arg9));
        }

        public void removeConfiguration(SensorConfiguration arg5) {
            SensorRemoveConfigurationParams v0 = new SensorRemoveConfigurationParams();
            v0.configuration = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(2)));
        }

        public void resume() {
            this.getProxyHandler().getMessageReceiver().accept(new SensorResumeParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(4)));
        }

        public void suspend() {
            this.getProxyHandler().getMessageReceiver().accept(new SensorSuspendParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(3)));
        }
    }

    final class SensorAddConfigurationParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public SensorConfiguration configuration;

        static {
            SensorAddConfigurationParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            SensorAddConfigurationParams.DEFAULT_STRUCT_INFO = SensorAddConfigurationParams.VERSION_ARRAY[0];
        }

        public SensorAddConfigurationParams() {
            this(0);
        }

        private SensorAddConfigurationParams(int arg2) {
            super(16, arg2);
        }

        public static SensorAddConfigurationParams decode(Decoder arg3) {
            SensorAddConfigurationParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new SensorAddConfigurationParams(arg3.readAndValidateDataHeader(SensorAddConfigurationParams.VERSION_ARRAY).elementsOrVersion);
                v1.configuration = SensorConfiguration.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static SensorAddConfigurationParams deserialize(ByteBuffer arg2) {
            return SensorAddConfigurationParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static SensorAddConfigurationParams deserialize(Message arg1) {
            return SensorAddConfigurationParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(SensorAddConfigurationParams.DEFAULT_STRUCT_INFO).encode(this.configuration, 8, false);
        }
    }

    final class SensorAddConfigurationResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public boolean success;

        static {
            SensorAddConfigurationResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            SensorAddConfigurationResponseParams.DEFAULT_STRUCT_INFO = SensorAddConfigurationResponseParams.VERSION_ARRAY[0];
        }

        public SensorAddConfigurationResponseParams() {
            this(0);
        }

        private SensorAddConfigurationResponseParams(int arg2) {
            super(16, arg2);
        }

        public static SensorAddConfigurationResponseParams decode(Decoder arg3) {
            SensorAddConfigurationResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new SensorAddConfigurationResponseParams(arg3.readAndValidateDataHeader(SensorAddConfigurationResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.success = arg3.readBoolean(8, 0);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static SensorAddConfigurationResponseParams deserialize(ByteBuffer arg2) {
            return SensorAddConfigurationResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static SensorAddConfigurationResponseParams deserialize(Message arg1) {
            return SensorAddConfigurationResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(SensorAddConfigurationResponseParams.DEFAULT_STRUCT_INFO).encode(this.success, 8, 0);
        }
    }

    class SensorAddConfigurationResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final AddConfigurationResponse mCallback;

        SensorAddConfigurationResponseParamsForwardToCallback(AddConfigurationResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(1, 2)) {
                    return 0;
                }

                this.mCallback.call(Boolean.valueOf(SensorAddConfigurationResponseParams.deserialize(v5.getPayload()).success));
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class SensorAddConfigurationResponseParamsProxyToResponder implements AddConfigurationResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        SensorAddConfigurationResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Boolean arg7) {
            SensorAddConfigurationResponseParams v0 = new SensorAddConfigurationResponseParams();
            v0.success = arg7.booleanValue();
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(1, 2, this.mRequestId)));
        }

        public void call(Object arg1) {
            this.call(((Boolean)arg1));
        }
    }

    final class SensorConfigureReadingChangeNotificationsParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public boolean enabled;

        static {
            SensorConfigureReadingChangeNotificationsParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            SensorConfigureReadingChangeNotificationsParams.DEFAULT_STRUCT_INFO = SensorConfigureReadingChangeNotificationsParams.VERSION_ARRAY[0];
        }

        public SensorConfigureReadingChangeNotificationsParams() {
            this(0);
        }

        private SensorConfigureReadingChangeNotificationsParams(int arg2) {
            super(16, arg2);
        }

        public static SensorConfigureReadingChangeNotificationsParams decode(Decoder arg3) {
            SensorConfigureReadingChangeNotificationsParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new SensorConfigureReadingChangeNotificationsParams(arg3.readAndValidateDataHeader(SensorConfigureReadingChangeNotificationsParams.VERSION_ARRAY).elementsOrVersion);
                v1.enabled = arg3.readBoolean(8, 0);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static SensorConfigureReadingChangeNotificationsParams deserialize(ByteBuffer arg2) {
            return SensorConfigureReadingChangeNotificationsParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static SensorConfigureReadingChangeNotificationsParams deserialize(Message arg1) {
            return SensorConfigureReadingChangeNotificationsParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(SensorConfigureReadingChangeNotificationsParams.DEFAULT_STRUCT_INFO).encode(this.enabled, 8, 0);
        }
    }

    final class SensorGetDefaultConfigurationParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            SensorGetDefaultConfigurationParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            SensorGetDefaultConfigurationParams.DEFAULT_STRUCT_INFO = SensorGetDefaultConfigurationParams.VERSION_ARRAY[0];
        }

        public SensorGetDefaultConfigurationParams() {
            this(0);
        }

        private SensorGetDefaultConfigurationParams(int arg2) {
            super(8, arg2);
        }

        public static SensorGetDefaultConfigurationParams decode(Decoder arg2) {
            SensorGetDefaultConfigurationParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new SensorGetDefaultConfigurationParams(arg2.readAndValidateDataHeader(SensorGetDefaultConfigurationParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static SensorGetDefaultConfigurationParams deserialize(ByteBuffer arg2) {
            return SensorGetDefaultConfigurationParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static SensorGetDefaultConfigurationParams deserialize(Message arg1) {
            return SensorGetDefaultConfigurationParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(SensorGetDefaultConfigurationParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class SensorGetDefaultConfigurationResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public SensorConfiguration configuration;

        static {
            SensorGetDefaultConfigurationResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            SensorGetDefaultConfigurationResponseParams.DEFAULT_STRUCT_INFO = SensorGetDefaultConfigurationResponseParams.VERSION_ARRAY[0];
        }

        public SensorGetDefaultConfigurationResponseParams() {
            this(0);
        }

        private SensorGetDefaultConfigurationResponseParams(int arg2) {
            super(16, arg2);
        }

        public static SensorGetDefaultConfigurationResponseParams decode(Decoder arg3) {
            SensorGetDefaultConfigurationResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new SensorGetDefaultConfigurationResponseParams(arg3.readAndValidateDataHeader(SensorGetDefaultConfigurationResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.configuration = SensorConfiguration.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static SensorGetDefaultConfigurationResponseParams deserialize(ByteBuffer arg2) {
            return SensorGetDefaultConfigurationResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static SensorGetDefaultConfigurationResponseParams deserialize(Message arg1) {
            return SensorGetDefaultConfigurationResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(SensorGetDefaultConfigurationResponseParams.DEFAULT_STRUCT_INFO).encode(this.configuration, 8, false);
        }
    }

    class SensorGetDefaultConfigurationResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final GetDefaultConfigurationResponse mCallback;

        SensorGetDefaultConfigurationResponseParamsForwardToCallback(GetDefaultConfigurationResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                ServiceMessage v4 = arg4.asServiceMessage();
                if(!v4.getHeader().validateHeader(0, 2)) {
                    return 0;
                }

                this.mCallback.call(SensorGetDefaultConfigurationResponseParams.deserialize(v4.getPayload()).configuration);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class SensorGetDefaultConfigurationResponseParamsProxyToResponder implements GetDefaultConfigurationResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        SensorGetDefaultConfigurationResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Object arg1) {
            this.call(((SensorConfiguration)arg1));
        }

        public void call(SensorConfiguration arg7) {
            SensorGetDefaultConfigurationResponseParams v0 = new SensorGetDefaultConfigurationResponseParams();
            v0.configuration = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(0, 2, this.mRequestId)));
        }
    }

    final class SensorRemoveConfigurationParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public SensorConfiguration configuration;

        static {
            SensorRemoveConfigurationParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            SensorRemoveConfigurationParams.DEFAULT_STRUCT_INFO = SensorRemoveConfigurationParams.VERSION_ARRAY[0];
        }

        public SensorRemoveConfigurationParams() {
            this(0);
        }

        private SensorRemoveConfigurationParams(int arg2) {
            super(16, arg2);
        }

        public static SensorRemoveConfigurationParams decode(Decoder arg3) {
            SensorRemoveConfigurationParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new SensorRemoveConfigurationParams(arg3.readAndValidateDataHeader(SensorRemoveConfigurationParams.VERSION_ARRAY).elementsOrVersion);
                v1.configuration = SensorConfiguration.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static SensorRemoveConfigurationParams deserialize(ByteBuffer arg2) {
            return SensorRemoveConfigurationParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static SensorRemoveConfigurationParams deserialize(Message arg1) {
            return SensorRemoveConfigurationParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(SensorRemoveConfigurationParams.DEFAULT_STRUCT_INFO).encode(this.configuration, 8, false);
        }
    }

    final class SensorResumeParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            SensorResumeParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            SensorResumeParams.DEFAULT_STRUCT_INFO = SensorResumeParams.VERSION_ARRAY[0];
        }

        public SensorResumeParams() {
            this(0);
        }

        private SensorResumeParams(int arg2) {
            super(8, arg2);
        }

        public static SensorResumeParams decode(Decoder arg2) {
            SensorResumeParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new SensorResumeParams(arg2.readAndValidateDataHeader(SensorResumeParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static SensorResumeParams deserialize(ByteBuffer arg2) {
            return SensorResumeParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static SensorResumeParams deserialize(Message arg1) {
            return SensorResumeParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(SensorResumeParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class SensorSuspendParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            SensorSuspendParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            SensorSuspendParams.DEFAULT_STRUCT_INFO = SensorSuspendParams.VERSION_ARRAY[0];
        }

        public SensorSuspendParams() {
            this(0);
        }

        private SensorSuspendParams(int arg2) {
            super(8, arg2);
        }

        public static SensorSuspendParams decode(Decoder arg2) {
            SensorSuspendParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new SensorSuspendParams(arg2.readAndValidateDataHeader(SensorSuspendParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static SensorSuspendParams deserialize(ByteBuffer arg2) {
            return SensorSuspendParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static SensorSuspendParams deserialize(Message arg1) {
            return SensorSuspendParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(SensorSuspendParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, Sensor arg2) {
            super(arg1, ((Interface)arg2));
        }

        public boolean accept(Message arg4) {
            try {
                ServiceMessage v4_1 = arg4.asServiceMessage();
                MessageHeader v1 = v4_1.getHeader();
                if(!v1.validateHeader(0)) {
                    return 0;
                }

                int v1_1 = v1.getType();
                if(v1_1 == -2) {
                    goto label_34;
                }

                switch(v1_1) {
                    case 2: {
                        goto label_28;
                    }
                    case 3: {
                        goto label_23;
                    }
                    case 4: {
                        goto label_18;
                    }
                    case 5: {
                        goto label_12;
                    }
                }

                return 0;
            label_18:
                SensorResumeParams.deserialize(v4_1.getPayload());
                this.getImpl().resume();
                return 1;
            label_23:
                SensorSuspendParams.deserialize(v4_1.getPayload());
                this.getImpl().suspend();
                return 1;
            label_28:
                this.getImpl().removeConfiguration(SensorRemoveConfigurationParams.deserialize(v4_1.getPayload()).configuration);
                return 1;
            label_12:
                this.getImpl().configureReadingChangeNotifications(SensorConfigureReadingChangeNotificationsParams.deserialize(v4_1.getPayload()).enabled);
                return 1;
            label_34:
                return InterfaceControlMessagesHelper.handleRunOrClosePipe(Sensor_Internal.MANAGER, v4_1);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }

        public boolean acceptWithResponder(Message arg9, MessageReceiver arg10) {
            try {
                ServiceMessage v9_1 = arg9.asServiceMessage();
                MessageHeader v1 = v9_1.getHeader();
                if(!v1.validateHeader(1)) {
                    return 0;
                }

                switch(v1.getType()) {
                    case -1: {
                        goto label_29;
                    }
                    case 0: {
                        goto label_20;
                    }
                    case 1: {
                        goto label_10;
                    }
                }

                return 0;
            label_20:
                SensorGetDefaultConfigurationParams.deserialize(v9_1.getPayload());
                this.getImpl().getDefaultConfiguration(new SensorGetDefaultConfigurationResponseParamsProxyToResponder(this.getCore(), arg10, v1.getRequestId()));
                return 1;
            label_10:
                this.getImpl().addConfiguration(SensorAddConfigurationParams.deserialize(v9_1.getPayload()).configuration, new SensorAddConfigurationResponseParamsProxyToResponder(this.getCore(), arg10, v1.getRequestId()));
                return 1;
            label_29:
                return InterfaceControlMessagesHelper.handleRun(this.getCore(), Sensor_Internal.MANAGER, v9_1, arg10);
            }
            catch(DeserializationException v9) {
                System.err.println(v9.toString());
                return 0;
            }
        }
    }

    private static final int ADD_CONFIGURATION_ORDINAL = 1;
    private static final int CONFIGURE_READING_CHANGE_NOTIFICATIONS_ORDINAL = 5;
    private static final int GET_DEFAULT_CONFIGURATION_ORDINAL = 0;
    public static final Manager MANAGER = null;
    private static final int REMOVE_CONFIGURATION_ORDINAL = 2;
    private static final int RESUME_ORDINAL = 4;
    private static final int SUSPEND_ORDINAL = 3;

    static {
        Sensor_Internal.MANAGER = new org.chromium.device.mojom.Sensor_Internal$1();
    }

    Sensor_Internal() {
        super();
    }
}

