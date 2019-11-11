package org.chromium.mojo.bindings;

public final class DataHeader {
    public static final int ELEMENTS_OR_VERSION_OFFSET = 4;
    public static final int HEADER_SIZE = 8;
    public static final int SIZE_OFFSET;
    public final int elementsOrVersion;
    public final int size;

    public DataHeader(int arg1, int arg2) {
        super();
        this.size = arg1;
        this.elementsOrVersion = arg2;
    }

    public boolean equals(Object arg5) {
        boolean v0 = true;
        if((((DataHeader)arg5)) == this) {
            return 1;
        }

        if(arg5 == null) {
            return 0;
        }

        if(this.getClass() != arg5.getClass()) {
            return 0;
        }

        if(this.elementsOrVersion != ((DataHeader)arg5).elementsOrVersion || this.size != ((DataHeader)arg5).size) {
            v0 = false;
        }
        else {
        }

        return v0;
    }

    public int hashCode() {
        return (this.elementsOrVersion + 0x1F) * 0x1F + this.size;
    }
}

