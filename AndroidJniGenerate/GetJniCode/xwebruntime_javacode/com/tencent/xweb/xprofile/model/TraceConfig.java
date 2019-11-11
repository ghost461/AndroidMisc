package com.tencent.xweb.xprofile.model;

import android.text.TextUtils;

public class TraceConfig {
    public String enabledCategory;
    public int pageCountPerTracing;
    public int profileSampleRatioInTenThousand;

    public TraceConfig() {
        super();
        this.pageCountPerTracing = 10;
    }

    public boolean isLegal() {
        boolean v0 = this.profileSampleRatioInTenThousand <= 0 || this.profileSampleRatioInTenThousand > 10000 || (TextUtils.isEmpty(this.enabledCategory)) || this.pageCountPerTracing < 0 ? false : true;
        return v0;
    }

    public String toString() {
        return "TraceConfig{profileSampleRatioInTenThousand=" + this.profileSampleRatioInTenThousand + ", enabledCategory=\'" + this.enabledCategory + '\'' + ", pageCountPerTracing=" + this.pageCountPerTracing + '}';
    }
}

