package com.tencent.xweb.xprofile.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class MetaData {
    public class ChromiumTraceConfig {
        @SerializedName(value="enable_argument_filter") public boolean enableArgumentFilter;
        @SerializedName(value="enable_systrace") public boolean enableSystrace;
        @SerializedName(value="included_categories") public List includedCategories;
        @SerializedName(value="record_mode") public String recordMode;

        public ChromiumTraceConfig() {
            super();
        }

        public String toString() {
            return "TraceConfig{enableArgumentFilter=" + this.enableArgumentFilter + ", enableSystrace=" + this.enableSystrace + ", includedCategories=" + this.includedCategories + ", recordMode=\'" + this.recordMode + '\'' + '}';
        }
    }

    @SerializedName(value="command_line") public String commandLine;
    @SerializedName(value="cpu-brand") public String cpuBrand;
    @SerializedName(value="os-version") public String osVersion;
    @SerializedName(value="physical-memory") public int physicalMemory;
    @SerializedName(value="trace-config") public String traceConfig;
    @SerializedName(value="user-agent") public String userAgent;
    @SerializedName(value="v8-version") public String v8Version;

    public MetaData() {
        super();
    }

    public String toString() {
        return "MetaData{cpuBrand=\'" + this.cpuBrand + '\'' + ", commandLine=\'" + this.commandLine + '\'' + ", osVersion=\'" + this.osVersion + '\'' + ", physicalMemory=" + this.physicalMemory + ", traceConfig=" + this.traceConfig + ", userAgent=\'" + this.userAgent + '\'' + ", v8Version=\'" + this.v8Version + '\'' + '}';
    }
}

