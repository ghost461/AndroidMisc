package org.chromium.mojo.system;

public class Pair {
    public final Object first;
    public final Object second;

    public Pair(Object arg1, Object arg2) {
        super();
        this.first = arg1;
        this.second = arg2;
    }

    public static Pair create(Object arg1, Object arg2) {
        return new Pair(arg1, arg2);
    }

    private boolean equals(Object arg1, Object arg2) {
        boolean v1;
        if(arg1 != null) {
            v1 = arg1.equals(arg2);
        }
        else if(arg2 == null) {
            v1 = true;
        }
        else {
            v1 = false;
        }

        return v1;
    }

    public boolean equals(Object arg4) {
        boolean v1 = false;
        if(!(arg4 instanceof Pair)) {
            return 0;
        }

        if((this.equals(this.first, ((Pair)arg4).first)) && (this.equals(this.second, ((Pair)arg4).second))) {
            v1 = true;
        }

        return v1;
    }

    public int hashCode() {
        int v1 = 0;
        int v0 = this.first == null ? 0 : this.first.hashCode();
        if(this.second == null) {
        }
        else {
            v1 = this.second.hashCode();
        }

        return v0 ^ v1;
    }
}

