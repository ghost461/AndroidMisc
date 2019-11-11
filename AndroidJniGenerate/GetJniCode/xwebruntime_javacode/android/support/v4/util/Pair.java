package android.support.v4.util;

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

    public boolean equals(Object arg4) {
        boolean v1 = false;
        if(!(arg4 instanceof Pair)) {
            return 0;
        }

        if((Pair.objectsEqual(((Pair)arg4).first, this.first)) && (Pair.objectsEqual(((Pair)arg4).second, this.second))) {
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

    private static boolean objectsEqual(Object arg0, Object arg1) {
        boolean v0;
        if(arg0 != arg1) {
            if(arg0 != null && (arg0.equals(arg1))) {
                goto label_7;
            }

            v0 = false;
        }
        else {
        label_7:
            v0 = true;
        }

        return v0;
    }

    public String toString() {
        return "Pair{" + String.valueOf(this.first) + " " + String.valueOf(this.second) + "}";
    }
}

