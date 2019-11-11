package org.chromium.blink.mojom;

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
import org.chromium.mojo.bindings.InterfaceRequest;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.MessageHeader;
import org.chromium.mojo.bindings.MessageReceiver;
import org.chromium.mojo.bindings.MessageReceiverWithResponder;
import org.chromium.mojo.bindings.ServiceMessage;
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo.system.Core;

class ColorChooserFactory_Internal {
    final class org.chromium.blink.mojom.ColorChooserFactory_Internal$1 extends Manager {
        org.chromium.blink.mojom.ColorChooserFactory_Internal$1() {
            super();
        }

        public ColorChooserFactory[] buildArray(int arg1) {
            return new ColorChooserFactory[arg1];
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

        public Stub buildStub(Core arg2, ColorChooserFactory arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((ColorChooserFactory)arg2));
        }

        public String getName() {
            return "blink::mojom::ColorChooserFactory";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class ColorChooserFactoryOpenColorChooserParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 0x20;
        private static final DataHeader[] VERSION_ARRAY;
        public InterfaceRequest chooser;
        public ColorChooserClient client;
        public int color;
        public ColorSuggestion[] suggestions;

        static {
            ColorChooserFactoryOpenColorChooserParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
            ColorChooserFactoryOpenColorChooserParams.DEFAULT_STRUCT_INFO = ColorChooserFactoryOpenColorChooserParams.VERSION_ARRAY[0];
        }

        public ColorChooserFactoryOpenColorChooserParams() {
            this(0);
        }

        private ColorChooserFactoryOpenColorChooserParams(int arg2) {
            super(0x20, arg2);
        }

        public static ColorChooserFactoryOpenColorChooserParams decode(Decoder arg8) {
            ColorChooserFactoryOpenColorChooserParams v1;
            if(arg8 == null) {
                return null;
            }

            arg8.increaseStackDepth();
            try {
                v1 = new ColorChooserFactoryOpenColorChooserParams(arg8.readAndValidateDataHeader(ColorChooserFactoryOpenColorChooserParams.VERSION_ARRAY).elementsOrVersion);
                int v0_1 = 8;
                v1.chooser = arg8.readInterfaceRequest(v0_1, false);
                v1.client = arg8.readServiceInterface(12, false, ColorChooserClient.MANAGER);
                v1.color = arg8.readInt(20);
                Decoder v3 = arg8.readPointer(24, false);
                DataHeader v4 = v3.readDataHeaderForPointerArray(-1);
                v1.suggestions = new ColorSuggestion[v4.elementsOrVersion];
                int v5;
                for(v5 = 0; v5 < v4.elementsOrVersion; ++v5) {
                    v1.suggestions[v5] = ColorSuggestion.decode(v3.readPointer(v5 * 8 + v0_1, false));
                }
            }
            catch(Throwable v0) {
                goto label_41;
            }

            arg8.decreaseStackDepth();
            return v1;
        label_41:
            arg8.decreaseStackDepth();
            throw v0;
        }

        public static ColorChooserFactoryOpenColorChooserParams deserialize(ByteBuffer arg2) {
            return ColorChooserFactoryOpenColorChooserParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ColorChooserFactoryOpenColorChooserParams deserialize(Message arg1) {
            return ColorChooserFactoryOpenColorChooserParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg6) {
            arg6 = arg6.getEncoderAtDataOffset(ColorChooserFactoryOpenColorChooserParams.DEFAULT_STRUCT_INFO);
            int v1 = 8;
            arg6.encode(this.chooser, v1, false);
            arg6.encode(this.client, 12, false, ColorChooserClient.MANAGER);
            arg6.encode(this.color, 20);
            int v3 = 24;
            if(this.suggestions == null) {
                arg6.encodeNullPointer(v3, false);
            }
            else {
                arg6 = arg6.encodePointerArray(this.suggestions.length, v3, -1);
                int v0;
                for(v0 = 0; v0 < this.suggestions.length; ++v0) {
                    arg6.encode(this.suggestions[v0], v0 * 8 + v1, false);
                }
            }
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.blink.mojom.ColorChooserFactory$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void openColorChooser(InterfaceRequest arg2, ColorChooserClient arg3, int arg4, ColorSuggestion[] arg5) {
            ColorChooserFactoryOpenColorChooserParams v0 = new ColorChooserFactoryOpenColorChooserParams();
            v0.chooser = arg2;
            v0.client = arg3;
            v0.color = arg4;
            v0.suggestions = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, ColorChooserFactory arg2) {
            super(arg1, ((Interface)arg2));
        }

        public boolean accept(Message arg6) {
            try {
                ServiceMessage v6_1 = arg6.asServiceMessage();
                MessageHeader v1 = v6_1.getHeader();
                if(!v1.validateHeader(0)) {
                    return 0;
                }

                int v1_1 = v1.getType();
                if(v1_1 != -2) {
                    if(v1_1 != 0) {
                        return 0;
                    }

                    ColorChooserFactoryOpenColorChooserParams v6_2 = ColorChooserFactoryOpenColorChooserParams.deserialize(v6_1.getPayload());
                    this.getImpl().openColorChooser(v6_2.chooser, v6_2.client, v6_2.color, v6_2.suggestions);
                    return 1;
                }

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(ColorChooserFactory_Internal.MANAGER, v6_1);
            }
            catch(DeserializationException v6) {
                System.err.println(v6.toString());
                return 0;
            }
        }

        public boolean acceptWithResponder(Message arg4, MessageReceiver arg5) {
            try {
                ServiceMessage v4_1 = arg4.asServiceMessage();
                MessageHeader v1 = v4_1.getHeader();
                if(!v1.validateHeader(1)) {
                    return 0;
                }

                if(v1.getType() != -1) {
                    return 0;
                }

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), ColorChooserFactory_Internal.MANAGER, v4_1, arg5);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }
    }

    public static final Manager MANAGER;
    private static final int OPEN_COLOR_CHOOSER_ORDINAL;

    static {
        ColorChooserFactory_Internal.MANAGER = new org.chromium.blink.mojom.ColorChooserFactory_Internal$1();
    }

    ColorChooserFactory_Internal() {
        super();
    }
}

