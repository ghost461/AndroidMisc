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
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.MessageHeader;
import org.chromium.mojo.bindings.MessageReceiver;
import org.chromium.mojo.bindings.MessageReceiverWithResponder;
import org.chromium.mojo.bindings.ServiceMessage;
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo.system.Core;

class TextSuggestionHost_Internal {
    final class org.chromium.blink.mojom.TextSuggestionHost_Internal$1 extends Manager {
        org.chromium.blink.mojom.TextSuggestionHost_Internal$1() {
            super();
        }

        public TextSuggestionHost[] buildArray(int arg1) {
            return new TextSuggestionHost[arg1];
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

        public Stub buildStub(Core arg2, TextSuggestionHost arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((TextSuggestionHost)arg2));
        }

        public String getName() {
            return "blink::mojom::TextSuggestionHost";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.blink.mojom.TextSuggestionHost$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void showSpellCheckSuggestionMenu(double arg2, double arg4, String arg6, SpellCheckSuggestion[] arg7) {
            TextSuggestionHostShowSpellCheckSuggestionMenuParams v0 = new TextSuggestionHostShowSpellCheckSuggestionMenuParams();
            v0.caretX = arg2;
            v0.caretY = arg4;
            v0.markedText = arg6;
            v0.suggestions = arg7;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1)));
        }

        public void showTextSuggestionMenu(double arg2, double arg4, String arg6, TextSuggestion[] arg7) {
            TextSuggestionHostShowTextSuggestionMenuParams v0 = new TextSuggestionHostShowTextSuggestionMenuParams();
            v0.caretX = arg2;
            v0.caretY = arg4;
            v0.markedText = arg6;
            v0.suggestions = arg7;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(2)));
        }

        public void startSuggestionMenuTimer() {
            this.getProxyHandler().getMessageReceiver().accept(new TextSuggestionHostStartSuggestionMenuTimerParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, TextSuggestionHost arg2) {
            super(arg1, ((Interface)arg2));
        }

        public boolean accept(Message arg11) {
            try {
                ServiceMessage v11_1 = arg11.asServiceMessage();
                MessageHeader v1 = v11_1.getHeader();
                if(!v1.validateHeader(0)) {
                    return 0;
                }

                int v1_1 = v1.getType();
                if(v1_1 == -2) {
                    goto label_37;
                }

                switch(v1_1) {
                    case 0: {
                        goto label_32;
                    }
                    case 1: {
                        goto label_22;
                    }
                    case 2: {
                        goto label_12;
                    }
                }

                return 0;
            label_22:
                TextSuggestionHostShowSpellCheckSuggestionMenuParams v11_2 = TextSuggestionHostShowSpellCheckSuggestionMenuParams.deserialize(v11_1.getPayload());
                this.getImpl().showSpellCheckSuggestionMenu(v11_2.caretX, v11_2.caretY, v11_2.markedText, v11_2.suggestions);
                return 1;
            label_12:
                TextSuggestionHostShowTextSuggestionMenuParams v11_3 = TextSuggestionHostShowTextSuggestionMenuParams.deserialize(v11_1.getPayload());
                this.getImpl().showTextSuggestionMenu(v11_3.caretX, v11_3.caretY, v11_3.markedText, v11_3.suggestions);
                return 1;
            label_32:
                TextSuggestionHostStartSuggestionMenuTimerParams.deserialize(v11_1.getPayload());
                this.getImpl().startSuggestionMenuTimer();
                return 1;
            label_37:
                return InterfaceControlMessagesHelper.handleRunOrClosePipe(TextSuggestionHost_Internal.MANAGER, v11_1);
            }
            catch(DeserializationException v11) {
                System.err.println(v11.toString());
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

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), TextSuggestionHost_Internal.MANAGER, v4_1, arg5);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }
    }

    final class TextSuggestionHostShowSpellCheckSuggestionMenuParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 40;
        private static final DataHeader[] VERSION_ARRAY;
        public double caretX;
        public double caretY;
        public String markedText;
        public SpellCheckSuggestion[] suggestions;

        static {
            TextSuggestionHostShowSpellCheckSuggestionMenuParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(40, 0)};
            TextSuggestionHostShowSpellCheckSuggestionMenuParams.DEFAULT_STRUCT_INFO = TextSuggestionHostShowSpellCheckSuggestionMenuParams.VERSION_ARRAY[0];
        }

        public TextSuggestionHostShowSpellCheckSuggestionMenuParams() {
            this(0);
        }

        private TextSuggestionHostShowSpellCheckSuggestionMenuParams(int arg2) {
            super(40, arg2);
        }

        public static TextSuggestionHostShowSpellCheckSuggestionMenuParams decode(Decoder arg8) {
            TextSuggestionHostShowSpellCheckSuggestionMenuParams v1;
            if(arg8 == null) {
                return null;
            }

            arg8.increaseStackDepth();
            try {
                v1 = new TextSuggestionHostShowSpellCheckSuggestionMenuParams(arg8.readAndValidateDataHeader(TextSuggestionHostShowSpellCheckSuggestionMenuParams.VERSION_ARRAY).elementsOrVersion);
                int v0_1 = 8;
                v1.caretX = arg8.readDouble(v0_1);
                v1.caretY = arg8.readDouble(16);
                v1.markedText = arg8.readString(24, false);
                Decoder v2 = arg8.readPointer(0x20, false);
                DataHeader v4 = v2.readDataHeaderForPointerArray(-1);
                v1.suggestions = new SpellCheckSuggestion[v4.elementsOrVersion];
                int v5;
                for(v5 = 0; v5 < v4.elementsOrVersion; ++v5) {
                    v1.suggestions[v5] = SpellCheckSuggestion.decode(v2.readPointer(v5 * 8 + v0_1, false));
                }
            }
            catch(Throwable v0) {
                goto label_40;
            }

            arg8.decreaseStackDepth();
            return v1;
        label_40:
            arg8.decreaseStackDepth();
            throw v0;
        }

        public static TextSuggestionHostShowSpellCheckSuggestionMenuParams deserialize(ByteBuffer arg2) {
            return TextSuggestionHostShowSpellCheckSuggestionMenuParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static TextSuggestionHostShowSpellCheckSuggestionMenuParams deserialize(Message arg1) {
            return TextSuggestionHostShowSpellCheckSuggestionMenuParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg6) {
            arg6 = arg6.getEncoderAtDataOffset(TextSuggestionHostShowSpellCheckSuggestionMenuParams.DEFAULT_STRUCT_INFO);
            int v2 = 8;
            arg6.encode(this.caretX, v2);
            arg6.encode(this.caretY, 16);
            arg6.encode(this.markedText, 24, false);
            int v3 = 0x20;
            if(this.suggestions == null) {
                arg6.encodeNullPointer(v3, false);
            }
            else {
                arg6 = arg6.encodePointerArray(this.suggestions.length, v3, -1);
                int v0;
                for(v0 = 0; v0 < this.suggestions.length; ++v0) {
                    arg6.encode(this.suggestions[v0], v0 * 8 + v2, false);
                }
            }
        }
    }

    final class TextSuggestionHostShowTextSuggestionMenuParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 40;
        private static final DataHeader[] VERSION_ARRAY;
        public double caretX;
        public double caretY;
        public String markedText;
        public TextSuggestion[] suggestions;

        static {
            TextSuggestionHostShowTextSuggestionMenuParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(40, 0)};
            TextSuggestionHostShowTextSuggestionMenuParams.DEFAULT_STRUCT_INFO = TextSuggestionHostShowTextSuggestionMenuParams.VERSION_ARRAY[0];
        }

        public TextSuggestionHostShowTextSuggestionMenuParams() {
            this(0);
        }

        private TextSuggestionHostShowTextSuggestionMenuParams(int arg2) {
            super(40, arg2);
        }

        public static TextSuggestionHostShowTextSuggestionMenuParams decode(Decoder arg8) {
            TextSuggestionHostShowTextSuggestionMenuParams v1;
            if(arg8 == null) {
                return null;
            }

            arg8.increaseStackDepth();
            try {
                v1 = new TextSuggestionHostShowTextSuggestionMenuParams(arg8.readAndValidateDataHeader(TextSuggestionHostShowTextSuggestionMenuParams.VERSION_ARRAY).elementsOrVersion);
                int v0_1 = 8;
                v1.caretX = arg8.readDouble(v0_1);
                v1.caretY = arg8.readDouble(16);
                v1.markedText = arg8.readString(24, false);
                Decoder v2 = arg8.readPointer(0x20, false);
                DataHeader v4 = v2.readDataHeaderForPointerArray(-1);
                v1.suggestions = new TextSuggestion[v4.elementsOrVersion];
                int v5;
                for(v5 = 0; v5 < v4.elementsOrVersion; ++v5) {
                    v1.suggestions[v5] = TextSuggestion.decode(v2.readPointer(v5 * 8 + v0_1, false));
                }
            }
            catch(Throwable v0) {
                goto label_40;
            }

            arg8.decreaseStackDepth();
            return v1;
        label_40:
            arg8.decreaseStackDepth();
            throw v0;
        }

        public static TextSuggestionHostShowTextSuggestionMenuParams deserialize(ByteBuffer arg2) {
            return TextSuggestionHostShowTextSuggestionMenuParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static TextSuggestionHostShowTextSuggestionMenuParams deserialize(Message arg1) {
            return TextSuggestionHostShowTextSuggestionMenuParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg6) {
            arg6 = arg6.getEncoderAtDataOffset(TextSuggestionHostShowTextSuggestionMenuParams.DEFAULT_STRUCT_INFO);
            int v2 = 8;
            arg6.encode(this.caretX, v2);
            arg6.encode(this.caretY, 16);
            arg6.encode(this.markedText, 24, false);
            int v3 = 0x20;
            if(this.suggestions == null) {
                arg6.encodeNullPointer(v3, false);
            }
            else {
                arg6 = arg6.encodePointerArray(this.suggestions.length, v3, -1);
                int v0;
                for(v0 = 0; v0 < this.suggestions.length; ++v0) {
                    arg6.encode(this.suggestions[v0], v0 * 8 + v2, false);
                }
            }
        }
    }

    final class TextSuggestionHostStartSuggestionMenuTimerParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            TextSuggestionHostStartSuggestionMenuTimerParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            TextSuggestionHostStartSuggestionMenuTimerParams.DEFAULT_STRUCT_INFO = TextSuggestionHostStartSuggestionMenuTimerParams.VERSION_ARRAY[0];
        }

        public TextSuggestionHostStartSuggestionMenuTimerParams() {
            this(0);
        }

        private TextSuggestionHostStartSuggestionMenuTimerParams(int arg2) {
            super(8, arg2);
        }

        public static TextSuggestionHostStartSuggestionMenuTimerParams decode(Decoder arg2) {
            TextSuggestionHostStartSuggestionMenuTimerParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new TextSuggestionHostStartSuggestionMenuTimerParams(arg2.readAndValidateDataHeader(TextSuggestionHostStartSuggestionMenuTimerParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static TextSuggestionHostStartSuggestionMenuTimerParams deserialize(ByteBuffer arg2) {
            return TextSuggestionHostStartSuggestionMenuTimerParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static TextSuggestionHostStartSuggestionMenuTimerParams deserialize(Message arg1) {
            return TextSuggestionHostStartSuggestionMenuTimerParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(TextSuggestionHostStartSuggestionMenuTimerParams.DEFAULT_STRUCT_INFO);
        }
    }

    public static final Manager MANAGER = null;
    private static final int SHOW_SPELL_CHECK_SUGGESTION_MENU_ORDINAL = 1;
    private static final int SHOW_TEXT_SUGGESTION_MENU_ORDINAL = 2;
    private static final int START_SUGGESTION_MENU_TIMER_ORDINAL;

    static {
        TextSuggestionHost_Internal.MANAGER = new org.chromium.blink.mojom.TextSuggestionHost_Internal$1();
    }

    TextSuggestionHost_Internal() {
        super();
    }
}

