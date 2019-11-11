package android.support.v7.widget;

class RtlSpacingHelper {
    public static final int UNDEFINED = 0x80000000;
    private int mEnd;
    private int mExplicitLeft;
    private int mExplicitRight;
    private boolean mIsRelative;
    private boolean mIsRtl;
    private int mLeft;
    private int mRight;
    private int mStart;

    RtlSpacingHelper() {
        super();
        this.mLeft = 0;
        this.mRight = 0;
        this.mStart = 0x80000000;
        this.mEnd = 0x80000000;
        this.mExplicitLeft = 0;
        this.mExplicitRight = 0;
        this.mIsRtl = false;
        this.mIsRelative = false;
    }

    public int getEnd() {
        int v0 = this.mIsRtl ? this.mLeft : this.mRight;
        return v0;
    }

    public int getLeft() {
        return this.mLeft;
    }

    public int getRight() {
        return this.mRight;
    }

    public int getStart() {
        int v0 = this.mIsRtl ? this.mRight : this.mLeft;
        return v0;
    }

    public void setAbsolute(int arg2, int arg3) {
        this.mIsRelative = false;
        int v0 = 0x80000000;
        if(arg2 != v0) {
            this.mExplicitLeft = arg2;
            this.mLeft = arg2;
        }

        if(arg3 != v0) {
            this.mExplicitRight = arg3;
            this.mRight = arg3;
        }
    }

    public void setDirection(boolean arg2) {
        int v2;
        if(arg2 == this.mIsRtl) {
            return;
        }

        this.mIsRtl = arg2;
        if(this.mIsRelative) {
            int v0 = 0x80000000;
            if(arg2) {
                v2 = this.mEnd != v0 ? this.mEnd : this.mExplicitLeft;
                this.mLeft = v2;
                v2 = this.mStart != v0 ? this.mStart : this.mExplicitRight;
                this.mRight = v2;
            }
            else {
                v2 = this.mStart != v0 ? this.mStart : this.mExplicitLeft;
                this.mLeft = v2;
                v2 = this.mEnd != v0 ? this.mEnd : this.mExplicitRight;
                this.mRight = v2;
            }
        }
        else {
            this.mLeft = this.mExplicitLeft;
            this.mRight = this.mExplicitRight;
        }
    }

    public void setRelative(int arg3, int arg4) {
        this.mStart = arg3;
        this.mEnd = arg4;
        this.mIsRelative = true;
        int v1 = 0x80000000;
        if(this.mIsRtl) {
            if(arg4 != v1) {
                this.mLeft = arg4;
            }

            if(arg3 == v1) {
                return;
            }

            this.mRight = arg3;
        }
        else {
            if(arg3 != v1) {
                this.mLeft = arg3;
            }

            if(arg4 == v1) {
                return;
            }

            this.mRight = arg4;
        }
    }
}

