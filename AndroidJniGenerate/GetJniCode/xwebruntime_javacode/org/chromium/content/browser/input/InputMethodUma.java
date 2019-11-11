package org.chromium.content.browser.input;

import org.chromium.base.metrics.RecordHistogram;

class InputMethodUma {
    private static final int UMA_PROXYVIEW_COUNT = 4;
    private static final int UMA_PROXYVIEW_DETECTION_FAILURE = 2;
    private static final int UMA_PROXYVIEW_FAILURE = 1;
    private static final int UMA_PROXYVIEW_REPLICA_INPUT_CONNECTION = 3;
    private static final int UMA_PROXYVIEW_SUCCESS = 0;
    private static final String UMA_REGISTER_PROXYVIEW = "InputMethod.RegisterProxyView";

    InputMethodUma() {
        super();
    }

    void recordProxyViewDetectionFailure() {
        RecordHistogram.recordEnumeratedHistogram("InputMethod.RegisterProxyView", 2, 4);
    }

    void recordProxyViewFailure() {
        RecordHistogram.recordEnumeratedHistogram("InputMethod.RegisterProxyView", 1, 4);
    }

    void recordProxyViewSuccess() {
        RecordHistogram.recordEnumeratedHistogram("InputMethod.RegisterProxyView", 0, 4);
    }
}

