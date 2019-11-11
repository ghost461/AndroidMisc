package com.tencent.xweb.xprofile.util;

import com.tencent.xweb.xprofile.model.TraceEvent;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class TraceFrameCostHandler {
    private static final String FRAME_COST_EVENT_NAME = "WebViewImpl::beginFrame";
    private static final int MAX_SAMPLE_STRING = 1000;
    private static final int MIN_EVENT_NUM_TO_BE_VALID = 2;
    private static final int SAMPLE_FREQUENCY = 15;
    private long mCostMax;
    private long mCostMin;
    private List mCostSequence;
    private long mCostTotal;
    private DecimalFormat mDecimalFormat;
    private int mEventCount;
    private int mSampleFactor;
    private String mSampleString0;
    private StringBuilder mSampleStringBuilder;

    public TraceFrameCostHandler(boolean arg3) {
        super();
        this.mSampleStringBuilder = new StringBuilder(0x400);
        this.mCostMax = -9223372036854775808L;
        this.mCostMin = 0x7FFFFFFFFFFFFFFFL;
        this.mSampleFactor = new Random().nextInt(15);
        this.mDecimalFormat = new DecimalFormat("0.0");
        if(arg3) {
            this.mCostSequence = new LinkedList();
        }
    }

    public void addTraceEvent(TraceEvent arg7) {
        if(!arg7.name.equals("WebViewImpl::beginFrame")) {
            return;
        }

        this.mCostMax = Math.max(this.mCostMax, arg7.tdur);
        this.mCostMin = Math.min(this.mCostMin, arg7.tdur);
        this.mCostTotal += arg7.tdur;
        if(this.mEventCount % 15 == this.mSampleFactor) {
            StringBuilder v0 = this.mSampleStringBuilder;
            v0.append(this.mDecimalFormat.format((((double)arg7.tdur)) / 1000));
            v0.append("/");
            if(this.mSampleStringBuilder.length() > 1000) {
                this.mSampleString0 = this.mSampleStringBuilder.toString();
                this.mSampleStringBuilder = new StringBuilder(0x400);
            }
        }

        if(this.mCostSequence != null) {
            this.mCostSequence.add(Long.valueOf(arg7.tdur));
        }

        ++this.mEventCount;
    }

    public List getCostSequence() {
        return this.mCostSequence;
    }

    public boolean isValid() {
        boolean v0 = this.mEventCount > 2 ? true : false;
        return v0;
    }

    public String toKvString() {
        StringBuilder v0 = new StringBuilder(3000);
        v0.append(this.mDecimalFormat.format((((double)this.mCostMax)) / 1000));
        v0.append(",");
        v0.append(this.mDecimalFormat.format((((double)this.mCostMin)) / 1000));
        v0.append(",");
        v0.append(this.mDecimalFormat.format((((double)this.mCostTotal)) / 1000 / (((double)this.mEventCount))));
        v0.append(",");
        if(this.mSampleString0 == null) {
            v0.append(this.mSampleStringBuilder);
            v0.append(",");
        }
        else {
            v0.append(this.mSampleString0);
            v0.append(",");
            v0.append(this.mSampleStringBuilder.toString());
        }

        return v0.toString();
    }
}

