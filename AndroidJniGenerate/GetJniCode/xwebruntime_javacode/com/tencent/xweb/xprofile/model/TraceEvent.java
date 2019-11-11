package com.tencent.xweb.xprofile.model;

import android.support.annotation.NonNull;
import com.google.gson.annotations.SerializedName;
import java.util.Map;

public class TraceEvent implements Comparable {
    public Map args;
    @SerializedName(value="cat") public String category;
    public long dur;
    public String name;
    @SerializedName(value="ph") public String phase;
    public int pid;
    public long tdur;
    public int tid;
    @SerializedName(value="ts") public long timeStamp;
    public long tts;

    public TraceEvent() {
        super();
    }

    public int compareTo(@NonNull TraceEvent arg5) {
        return Long.compare(this.timeStamp, arg5.timeStamp);
    }

    public int compareTo(@NonNull Object arg1) {
        return this.compareTo(((TraceEvent)arg1));
    }

    public String toString() {
        return "TraceEvent{pid=" + this.pid + ", tid=" + this.tid + ", timeStamp=" + this.timeStamp + ", phase=\'" + this.phase + '\'' + ", category=\'" + this.category + '\'' + ", name=\'" + this.name + '\'' + ", args=" + this.args + ", dur=" + this.dur + ", tdur=" + this.tdur + ", tts=" + this.tts + '}';
    }
}

