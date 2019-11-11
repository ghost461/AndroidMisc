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

class TextSuggestionBackend_Internal {
    final class org.chromium.blink.mojom.TextSuggestionBackend_Internal$1 extends Manager {
        org.chromium.blink.mojom.TextSuggestionBackend_Internal$1() {
            super();
        }

        public TextSuggestionBackend[] buildArray(int arg1) {
            return new TextSuggestionBackend[arg1];
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

        public Stub buildStub(Core arg2, TextSuggestionBackend arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((TextSuggestionBackend)arg2));
        }

        public String getName() {
            return "blink::mojom::TextSuggestionBackend";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.blink.mojom.TextSuggestionBackend$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void applySpellCheckSuggestion(String arg5) {
            TextSuggestionBackendApplySpellCheckSuggestionParams v0 = new TextSuggestionBackendApplySpellCheckSuggestionParams();
            v0.suggestion = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }

        public void applyTextSuggestion(int arg4, int arg5) {
            TextSuggestionBackendApplyTextSuggestionParams v0 = new TextSuggestionBackendApplyTextSuggestionParams();
            v0.markerTag = arg4;
            v0.suggestionIndex = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1)));
        }

        public void deleteActiveSuggestionRange() {
            this.getProxyHandler().getMessageReceiver().accept(new TextSuggestionBackendDeleteActiveSuggestionRangeParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(2)));
        }

        public void onNewWordAddedToDictionary(String arg5) {
            TextSuggestionBackendOnNewWordAddedToDictionaryParams v0 = new TextSuggestionBackendOnNewWordAddedToDictionaryParams();
            v0.suggestion = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(3)));
        }

        public void onSuggestionMenuClosed() {
            this.getProxyHandler().getMessageReceiver().accept(new TextSuggestionBackendOnSuggestionMenuClosedParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(4)));
        }

        public void suggestionMenuTimeoutCallback(int arg5) {
            TextSuggestionBackendSuggestionMenuTimeoutCallbackParams v0 = new TextSuggestionBackendSuggestionMenuTimeoutCallbackParams();
            v0.maxNumberOfSuggestions = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(5)));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, TextSuggestionBackend arg2) {
            super(arg1, ((Interface)arg2));
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5_1 = arg5.asServiceMessage();
                MessageHeader v1 = v5_1.getHeader();
                if(!v1.validateHeader(0)) {
                    return 0;
                }

                int v1_1 = v1.getType();
                if(v1_1 == -2) {
                    goto label_47;
                }

                switch(v1_1) {
                    case 0: {
                        goto label_41;
                    }
                    case 1: {
                        goto label_34;
                    }
                    case 2: {
                        goto label_29;
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
            label_34:
                TextSuggestionBackendApplyTextSuggestionParams v5_2 = TextSuggestionBackendApplyTextSuggestionParams.deserialize(v5_1.getPayload());
                this.getImpl().applyTextSuggestion(v5_2.markerTag, v5_2.suggestionIndex);
                return 1;
            label_18:
                TextSuggestionBackendOnSuggestionMenuClosedParams.deserialize(v5_1.getPayload());
                this.getImpl().onSuggestionMenuClosed();
                return 1;
            label_23:
                this.getImpl().onNewWordAddedToDictionary(TextSuggestionBackendOnNewWordAddedToDictionaryParams.deserialize(v5_1.getPayload()).suggestion);
                return 1;
            label_41:
                this.getImpl().applySpellCheckSuggestion(TextSuggestionBackendApplySpellCheckSuggestionParams.deserialize(v5_1.getPayload()).suggestion);
                return 1;
            label_12:
                this.getImpl().suggestionMenuTimeoutCallback(TextSuggestionBackendSuggestionMenuTimeoutCallbackParams.deserialize(v5_1.getPayload()).maxNumberOfSuggestions);
                return 1;
            label_29:
                TextSuggestionBackendDeleteActiveSuggestionRangeParams.deserialize(v5_1.getPayload());
                this.getImpl().deleteActiveSuggestionRange();
                return 1;
            label_47:
                return InterfaceControlMessagesHelper.handleRunOrClosePipe(TextSuggestionBackend_Internal.MANAGER, v5_1);
            }
            catch(DeserializationException v5) {
                System.err.println(v5.toString());
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

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), TextSuggestionBackend_Internal.MANAGER, v4_1, arg5);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }
    }

    final class TextSuggestionBackendApplySpellCheckSuggestionParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public String suggestion;

        static {
            TextSuggestionBackendApplySpellCheckSuggestionParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            TextSuggestionBackendApplySpellCheckSuggestionParams.DEFAULT_STRUCT_INFO = TextSuggestionBackendApplySpellCheckSuggestionParams.VERSION_ARRAY[0];
        }

        public TextSuggestionBackendApplySpellCheckSuggestionParams() {
            this(0);
        }

        private TextSuggestionBackendApplySpellCheckSuggestionParams(int arg2) {
            super(16, arg2);
        }

        public static TextSuggestionBackendApplySpellCheckSuggestionParams decode(Decoder arg3) {
            TextSuggestionBackendApplySpellCheckSuggestionParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new TextSuggestionBackendApplySpellCheckSuggestionParams(arg3.readAndValidateDataHeader(TextSuggestionBackendApplySpellCheckSuggestionParams.VERSION_ARRAY).elementsOrVersion);
                v1.suggestion = arg3.readString(8, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static TextSuggestionBackendApplySpellCheckSuggestionParams deserialize(ByteBuffer arg2) {
            return TextSuggestionBackendApplySpellCheckSuggestionParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static TextSuggestionBackendApplySpellCheckSuggestionParams deserialize(Message arg1) {
            return TextSuggestionBackendApplySpellCheckSuggestionParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(TextSuggestionBackendApplySpellCheckSuggestionParams.DEFAULT_STRUCT_INFO).encode(this.suggestion, 8, false);
        }
    }

    final class TextSuggestionBackendApplyTextSuggestionParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int markerTag;
        public int suggestionIndex;

        static {
            TextSuggestionBackendApplyTextSuggestionParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            TextSuggestionBackendApplyTextSuggestionParams.DEFAULT_STRUCT_INFO = TextSuggestionBackendApplyTextSuggestionParams.VERSION_ARRAY[0];
        }

        public TextSuggestionBackendApplyTextSuggestionParams() {
            this(0);
        }

        private TextSuggestionBackendApplyTextSuggestionParams(int arg2) {
            super(16, arg2);
        }

        public static TextSuggestionBackendApplyTextSuggestionParams decode(Decoder arg2) {
            TextSuggestionBackendApplyTextSuggestionParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new TextSuggestionBackendApplyTextSuggestionParams(arg2.readAndValidateDataHeader(TextSuggestionBackendApplyTextSuggestionParams.VERSION_ARRAY).elementsOrVersion);
                v1.markerTag = arg2.readInt(8);
                v1.suggestionIndex = arg2.readInt(12);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static TextSuggestionBackendApplyTextSuggestionParams deserialize(ByteBuffer arg2) {
            return TextSuggestionBackendApplyTextSuggestionParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static TextSuggestionBackendApplyTextSuggestionParams deserialize(Message arg1) {
            return TextSuggestionBackendApplyTextSuggestionParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3 = arg3.getEncoderAtDataOffset(TextSuggestionBackendApplyTextSuggestionParams.DEFAULT_STRUCT_INFO);
            arg3.encode(this.markerTag, 8);
            arg3.encode(this.suggestionIndex, 12);
        }
    }

    final class TextSuggestionBackendDeleteActiveSuggestionRangeParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            TextSuggestionBackendDeleteActiveSuggestionRangeParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            TextSuggestionBackendDeleteActiveSuggestionRangeParams.DEFAULT_STRUCT_INFO = TextSuggestionBackendDeleteActiveSuggestionRangeParams.VERSION_ARRAY[0];
        }

        public TextSuggestionBackendDeleteActiveSuggestionRangeParams() {
            this(0);
        }

        private TextSuggestionBackendDeleteActiveSuggestionRangeParams(int arg2) {
            super(8, arg2);
        }

        public static TextSuggestionBackendDeleteActiveSuggestionRangeParams decode(Decoder arg2) {
            TextSuggestionBackendDeleteActiveSuggestionRangeParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new TextSuggestionBackendDeleteActiveSuggestionRangeParams(arg2.readAndValidateDataHeader(TextSuggestionBackendDeleteActiveSuggestionRangeParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static TextSuggestionBackendDeleteActiveSuggestionRangeParams deserialize(ByteBuffer arg2) {
            return TextSuggestionBackendDeleteActiveSuggestionRangeParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static TextSuggestionBackendDeleteActiveSuggestionRangeParams deserialize(Message arg1) {
            return TextSuggestionBackendDeleteActiveSuggestionRangeParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(TextSuggestionBackendDeleteActiveSuggestionRangeParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class TextSuggestionBackendOnNewWordAddedToDictionaryParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public String suggestion;

        static {
            TextSuggestionBackendOnNewWordAddedToDictionaryParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            TextSuggestionBackendOnNewWordAddedToDictionaryParams.DEFAULT_STRUCT_INFO = TextSuggestionBackendOnNewWordAddedToDictionaryParams.VERSION_ARRAY[0];
        }

        public TextSuggestionBackendOnNewWordAddedToDictionaryParams() {
            this(0);
        }

        private TextSuggestionBackendOnNewWordAddedToDictionaryParams(int arg2) {
            super(16, arg2);
        }

        public static TextSuggestionBackendOnNewWordAddedToDictionaryParams decode(Decoder arg3) {
            TextSuggestionBackendOnNewWordAddedToDictionaryParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new TextSuggestionBackendOnNewWordAddedToDictionaryParams(arg3.readAndValidateDataHeader(TextSuggestionBackendOnNewWordAddedToDictionaryParams.VERSION_ARRAY).elementsOrVersion);
                v1.suggestion = arg3.readString(8, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static TextSuggestionBackendOnNewWordAddedToDictionaryParams deserialize(ByteBuffer arg2) {
            return TextSuggestionBackendOnNewWordAddedToDictionaryParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static TextSuggestionBackendOnNewWordAddedToDictionaryParams deserialize(Message arg1) {
            return TextSuggestionBackendOnNewWordAddedToDictionaryParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(TextSuggestionBackendOnNewWordAddedToDictionaryParams.DEFAULT_STRUCT_INFO).encode(this.suggestion, 8, false);
        }
    }

    final class TextSuggestionBackendOnSuggestionMenuClosedParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            TextSuggestionBackendOnSuggestionMenuClosedParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            TextSuggestionBackendOnSuggestionMenuClosedParams.DEFAULT_STRUCT_INFO = TextSuggestionBackendOnSuggestionMenuClosedParams.VERSION_ARRAY[0];
        }

        public TextSuggestionBackendOnSuggestionMenuClosedParams() {
            this(0);
        }

        private TextSuggestionBackendOnSuggestionMenuClosedParams(int arg2) {
            super(8, arg2);
        }

        public static TextSuggestionBackendOnSuggestionMenuClosedParams decode(Decoder arg2) {
            TextSuggestionBackendOnSuggestionMenuClosedParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new TextSuggestionBackendOnSuggestionMenuClosedParams(arg2.readAndValidateDataHeader(TextSuggestionBackendOnSuggestionMenuClosedParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static TextSuggestionBackendOnSuggestionMenuClosedParams deserialize(ByteBuffer arg2) {
            return TextSuggestionBackendOnSuggestionMenuClosedParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static TextSuggestionBackendOnSuggestionMenuClosedParams deserialize(Message arg1) {
            return TextSuggestionBackendOnSuggestionMenuClosedParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(TextSuggestionBackendOnSuggestionMenuClosedParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class TextSuggestionBackendSuggestionMenuTimeoutCallbackParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int maxNumberOfSuggestions;

        static {
            TextSuggestionBackendSuggestionMenuTimeoutCallbackParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            TextSuggestionBackendSuggestionMenuTimeoutCallbackParams.DEFAULT_STRUCT_INFO = TextSuggestionBackendSuggestionMenuTimeoutCallbackParams.VERSION_ARRAY[0];
        }

        public TextSuggestionBackendSuggestionMenuTimeoutCallbackParams() {
            this(0);
        }

        private TextSuggestionBackendSuggestionMenuTimeoutCallbackParams(int arg2) {
            super(16, arg2);
        }

        public static TextSuggestionBackendSuggestionMenuTimeoutCallbackParams decode(Decoder arg2) {
            TextSuggestionBackendSuggestionMenuTimeoutCallbackParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new TextSuggestionBackendSuggestionMenuTimeoutCallbackParams(arg2.readAndValidateDataHeader(TextSuggestionBackendSuggestionMenuTimeoutCallbackParams.VERSION_ARRAY).elementsOrVersion);
                v1.maxNumberOfSuggestions = arg2.readInt(8);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static TextSuggestionBackendSuggestionMenuTimeoutCallbackParams deserialize(ByteBuffer arg2) {
            return TextSuggestionBackendSuggestionMenuTimeoutCallbackParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static TextSuggestionBackendSuggestionMenuTimeoutCallbackParams deserialize(Message arg1) {
            return TextSuggestionBackendSuggestionMenuTimeoutCallbackParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3.getEncoderAtDataOffset(TextSuggestionBackendSuggestionMenuTimeoutCallbackParams.DEFAULT_STRUCT_INFO).encode(this.maxNumberOfSuggestions, 8);
        }
    }

    private static final int APPLY_SPELL_CHECK_SUGGESTION_ORDINAL = 0;
    private static final int APPLY_TEXT_SUGGESTION_ORDINAL = 1;
    private static final int DELETE_ACTIVE_SUGGESTION_RANGE_ORDINAL = 2;
    public static final Manager MANAGER = null;
    private static final int ON_NEW_WORD_ADDED_TO_DICTIONARY_ORDINAL = 3;
    private static final int ON_SUGGESTION_MENU_CLOSED_ORDINAL = 4;
    private static final int SUGGESTION_MENU_TIMEOUT_CALLBACK_ORDINAL = 5;

    static {
        TextSuggestionBackend_Internal.MANAGER = new org.chromium.blink.mojom.TextSuggestionBackend_Internal$1();
    }

    TextSuggestionBackend_Internal() {
        super();
    }
}

