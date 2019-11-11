package org.chromium.mojo.bindings;

import org.chromium.mojo.bindings.interfacecontrol.QueryVersionResult;
import org.chromium.mojo.bindings.interfacecontrol.RunMessageParams;
import org.chromium.mojo.bindings.interfacecontrol.RunOrClosePipeMessageParams;
import org.chromium.mojo.bindings.interfacecontrol.RunOutput;
import org.chromium.mojo.bindings.interfacecontrol.RunResponseMessageParams;
import org.chromium.mojo.system.Core;

public class InterfaceControlMessagesHelper {
    class RunResponseForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final Callback1 mCallback;

        RunResponseForwardToCallback(Callback1 arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg2) {
            this.mCallback.call(RunResponseMessageParams.deserialize(arg2.asServiceMessage().getPayload()));
            return 1;
        }
    }

    public InterfaceControlMessagesHelper() {
        super();
    }

    public static boolean handleRun(Core arg5, Manager arg6, ServiceMessage arg7, MessageReceiver arg8) {
        RunMessageParams v0 = RunMessageParams.deserialize(arg7.getPayload());
        RunResponseMessageParams v1 = new RunResponseMessageParams();
        v1.output = new RunOutput();
        if(v0.input.which() == 0) {
            v1.output.setQueryVersionResult(new QueryVersionResult());
            v1.output.getQueryVersionResult().version = arg6.getVersion();
        }
        else {
            v1.output = null;
        }

        return arg8.accept(v1.serializeWithHeader(arg5, new MessageHeader(-1, 2, arg7.getHeader().getRequestId())));
    }

    public static boolean handleRunOrClosePipe(Manager arg2, ServiceMessage arg3) {
        RunOrClosePipeMessageParams v3 = RunOrClosePipeMessageParams.deserialize(arg3.getPayload());
        boolean v1 = false;
        if(v3.input.which() == 0) {
            if(v3.input.getRequireVersion().version <= arg2.getVersion()) {
                v1 = true;
            }

            return v1;
        }

        return 0;
    }

    public static void sendRunMessage(Core arg5, MessageReceiverWithResponder arg6, RunMessageParams arg7, Callback1 arg8) {
        arg6.acceptWithResponder(arg7.serializeWithHeader(arg5, new MessageHeader(-1, 1, 0)), new RunResponseForwardToCallback(arg8));
    }

    public static void sendRunOrClosePipeMessage(Core arg2, MessageReceiverWithResponder arg3, RunOrClosePipeMessageParams arg4) {
        arg3.accept(arg4.serializeWithHeader(arg2, new MessageHeader(-2)));
    }
}

