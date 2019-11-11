package com.tencent.xweb.xprofile.model;

import java.util.List;

public class TraceOutput {
    public MetaData metadata;
    public List traceEvents;

    public TraceOutput() {
        super();
    }

    public String toString() {
        return "TraceOutput{traceEvents=" + this.traceEvents + ", metadata=" + this.metadata + '}';
    }
}

