package org.chromium.content.browser.input;

import org.chromium.base.VisibleForTesting;

public class Range {
    private int mEnd;
    private int mStart;

    public Range(int arg1, int arg2) {
        super();
        this.set(arg1, arg2);
    }

    public void clamp(int arg2, int arg3) {
        this.mStart = Math.min(Math.max(this.mStart, arg2), arg3);
        this.mEnd = Math.max(Math.min(this.mEnd, arg3), arg2);
    }

    public int end() {
        return this.mEnd;
    }

    public boolean equals(Object arg5) {
        if(!(arg5 instanceof Range)) {
            return 0;
        }

        boolean v0 = true;
        if((((Range)arg5)) == this) {
            return 1;
        }

        if(this.mStart != ((Range)arg5).mStart || this.mEnd != ((Range)arg5).mEnd) {
            v0 = false;
        }
        else {
        }

        return v0;
    }

    public int hashCode() {
        return this.mStart * 11 + this.mEnd * 0x1F;
    }

    public boolean intersects(Range arg3) {
        boolean v3 = this.mStart > arg3.mEnd || this.mEnd < arg3.mStart ? false : true;
        return v3;
    }

    @VisibleForTesting public void set(int arg2, int arg3) {
        this.mStart = Math.min(arg2, arg3);
        this.mEnd = Math.max(arg2, arg3);
    }

    public int start() {
        return this.mStart;
    }

    public String toString() {
        return "[ " + this.mStart + ", " + this.mEnd + " ]";
    }
}

