package com.tencent.xweb.xprofile.model;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class TraceTimingResult {
    private static final String URL_KEY_IN_START_FINISHED_EVENT_ARGS = "URL";
    private String domain;
    private long fromStartToFirstContentfulPaint;
    private long fromStartToFirstMeaningfulPaint;
    private transient TraceEvent mFirstContentfulEvent;
    private transient TraceEvent mFirstMeaningfulEvent;
    private transient TraceEvent mStartEvent;
    private transient TraceEvent mStartFinishedEvent;

    public TraceTimingResult() {
        super();
        this.domain = "";
    }

    private String getDomain() {
        if(this.mStartFinishedEvent != null) {
            if(!this.mStartFinishedEvent.args.containsKey("URL")) {
            }
            else {
                Object v0 = this.mStartFinishedEvent.args.get("URL");
                String v1 = "invalid url";
                if((v0 instanceof String)) {
                    try {
                        URL v1_1 = new URL(v0);
                        v1 = v1_1.getProtocol() + "://" + v1_1.getHost();
                    }
                    catch(MalformedURLException ) {
                        Object v1_2 = v0;
                    }

                    goto label_29;
                }
                else {
                    try {
                    label_29:
                        return URLEncoder.encode(v1, "UTF-8");
                    }
                    catch(UnsupportedEncodingException ) {
                        return "invalid url with utf8";
                    }
                }
            }
        }

        return "";
    }

    public long getFromStartToFirstContentfulPaint() {
        return this.fromStartToFirstContentfulPaint;
    }

    public long getFromStartToFirstMeaningfulPaint() {
        return this.fromStartToFirstMeaningfulPaint;
    }

    public boolean hasStart() {
        boolean v0 = this.mStartEvent != null ? true : false;
        return v0;
    }

    public boolean isContentfulLegal() {
        boolean v0 = this.mStartEvent == null || this.mFirstContentfulEvent == null ? false : true;
        return v0;
    }

    public boolean isLegal() {
        boolean v0 = this.mStartEvent == null || this.mFirstMeaningfulEvent == null ? false : true;
        return v0;
    }

    public void setFirstContentful(TraceEvent arg7) {
        if(this.mStartEvent == null) {
            return;
        }

        this.mFirstContentfulEvent = arg7;
        this.fromStartToFirstContentfulPaint = this.mFirstContentfulEvent.timeStamp - this.mStartEvent.timeStamp;
    }

    public void setFirstMeaningful(TraceEvent arg7) {
        if(this.mStartEvent == null) {
            return;
        }

        this.mFirstMeaningfulEvent = arg7;
        this.fromStartToFirstMeaningfulPaint = this.mFirstMeaningfulEvent.timeStamp - this.mStartEvent.timeStamp;
    }

    public void setStart(TraceEvent arg1) {
        this.mStartEvent = arg1;
    }

    public void setStartFinishedEvent(TraceEvent arg1) {
        this.mStartFinishedEvent = arg1;
        this.domain = this.getDomain();
    }

    public String toKvString() {
        return this.domain + ",-1,-1," + this.fromStartToFirstMeaningfulPaint / 1000 + "," + this.fromStartToFirstContentfulPaint / 1000;
    }

    public String toString() {
        return "TraceTimingResult{mStartEvent=" + this.mStartEvent + ", mStartFinishedEvent=" + this.mStartFinishedEvent + ", mFirstMeaningfulEvent=" + this.mFirstMeaningfulEvent + ", mFirstContentfulEvent=" + this.mFirstContentfulEvent + ", fromStartToFirstMeaningfulPaint=" + this.fromStartToFirstMeaningfulPaint + ", fromStartToFirstContentfulPaint=" + this.fromStartToFirstContentfulPaint + ", domain=\'" + this.domain + '\'' + '}';
    }
}

