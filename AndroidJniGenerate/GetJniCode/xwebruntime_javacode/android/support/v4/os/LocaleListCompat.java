package android.support.v4.os;

import android.os.Build$VERSION;
import android.os.LocaleList;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.Size;
import java.util.Locale;

public final class LocaleListCompat {
    @RequiresApi(value=24) class LocaleListCompatApi24Impl implements LocaleListInterface {
        private LocaleList mLocaleList;

        LocaleListCompatApi24Impl() {
            super();
            this.mLocaleList = new LocaleList(new Locale[0]);
        }

        public boolean equals(Object arg2) {
            return this.mLocaleList.equals(((LocaleListCompat)arg2).unwrap());
        }

        public Locale get(int arg2) {
            return this.mLocaleList.get(arg2);
        }

        @Nullable public Locale getFirstMatch(String[] arg2) {
            if(this.mLocaleList != null) {
                return this.mLocaleList.getFirstMatch(arg2);
            }

            return null;
        }

        public Object getLocaleList() {
            return this.mLocaleList;
        }

        public int hashCode() {
            return this.mLocaleList.hashCode();
        }

        @IntRange(from=-1) public int indexOf(Locale arg2) {
            return this.mLocaleList.indexOf(arg2);
        }

        public boolean isEmpty() {
            return this.mLocaleList.isEmpty();
        }

        public void setLocaleList(@NonNull Locale[] arg2) {
            this.mLocaleList = new LocaleList(arg2);
        }

        @IntRange(from=0) public int size() {
            return this.mLocaleList.size();
        }

        public String toLanguageTags() {
            return this.mLocaleList.toLanguageTags();
        }

        public String toString() {
            return this.mLocaleList.toString();
        }
    }

    class LocaleListCompatBaseImpl implements LocaleListInterface {
        private LocaleListHelper mLocaleList;

        LocaleListCompatBaseImpl() {
            super();
            this.mLocaleList = new LocaleListHelper(new Locale[0]);
        }

        public boolean equals(Object arg2) {
            return this.mLocaleList.equals(((LocaleListCompat)arg2).unwrap());
        }

        public Locale get(int arg2) {
            return this.mLocaleList.get(arg2);
        }

        @Nullable public Locale getFirstMatch(String[] arg2) {
            if(this.mLocaleList != null) {
                return this.mLocaleList.getFirstMatch(arg2);
            }

            return null;
        }

        public Object getLocaleList() {
            return this.mLocaleList;
        }

        public int hashCode() {
            return this.mLocaleList.hashCode();
        }

        @IntRange(from=-1) public int indexOf(Locale arg2) {
            return this.mLocaleList.indexOf(arg2);
        }

        public boolean isEmpty() {
            return this.mLocaleList.isEmpty();
        }

        public void setLocaleList(@NonNull Locale[] arg2) {
            this.mLocaleList = new LocaleListHelper(arg2);
        }

        @IntRange(from=0) public int size() {
            return this.mLocaleList.size();
        }

        public String toLanguageTags() {
            return this.mLocaleList.toLanguageTags();
        }

        public String toString() {
            return this.mLocaleList.toString();
        }
    }

    static final LocaleListInterface IMPL;
    private static final LocaleListCompat sEmptyLocaleList;

    static {
        LocaleListCompat.sEmptyLocaleList = new LocaleListCompat();
        LocaleListCompat.IMPL = Build$VERSION.SDK_INT >= 24 ? new LocaleListCompatApi24Impl() : new LocaleListCompatBaseImpl();
    }

    private LocaleListCompat() {
        super();
    }

    public static LocaleListCompat create(@NonNull Locale[] arg1) {
        LocaleListCompat v0 = new LocaleListCompat();
        v0.setLocaleListArray(arg1);
        return v0;
    }

    public boolean equals(Object arg2) {
        return LocaleListCompat.IMPL.equals(arg2);
    }

    @NonNull public static LocaleListCompat forLanguageTags(@Nullable String arg4) {
        if(arg4 != null) {
            if(arg4.isEmpty()) {
            }
            else {
                String[] v4 = arg4.split(",");
                Locale[] v0 = new Locale[v4.length];
                int v1;
                for(v1 = 0; v1 < v0.length; ++v1) {
                    Locale v2 = Build$VERSION.SDK_INT >= 21 ? Locale.forLanguageTag(v4[v1]) : LocaleHelper.forLanguageTag(v4[v1]);
                    v0[v1] = v2;
                }

                LocaleListCompat v4_1 = new LocaleListCompat();
                v4_1.setLocaleListArray(v0);
                return v4_1;
            }
        }

        return LocaleListCompat.getEmptyLocaleList();
    }

    public Locale get(int arg2) {
        return LocaleListCompat.IMPL.get(arg2);
    }

    @NonNull @Size(min=1) public static LocaleListCompat getAdjustedDefault() {
        if(Build$VERSION.SDK_INT >= 24) {
            return LocaleListCompat.wrap(LocaleList.getAdjustedDefault());
        }

        return LocaleListCompat.create(new Locale[]{Locale.getDefault()});
    }

    @NonNull @Size(min=1) public static LocaleListCompat getDefault() {
        if(Build$VERSION.SDK_INT >= 24) {
            return LocaleListCompat.wrap(LocaleList.getDefault());
        }

        return LocaleListCompat.create(new Locale[]{Locale.getDefault()});
    }

    @NonNull public static LocaleListCompat getEmptyLocaleList() {
        return LocaleListCompat.sEmptyLocaleList;
    }

    public Locale getFirstMatch(String[] arg2) {
        return LocaleListCompat.IMPL.getFirstMatch(arg2);
    }

    public int hashCode() {
        return LocaleListCompat.IMPL.hashCode();
    }

    @IntRange(from=-1) public int indexOf(Locale arg2) {
        return LocaleListCompat.IMPL.indexOf(arg2);
    }

    public boolean isEmpty() {
        return LocaleListCompat.IMPL.isEmpty();
    }

    @RequiresApi(value=24) private void setLocaleList(LocaleList arg5) {
        int v0 = arg5.size();
        if(v0 > 0) {
            Locale[] v1 = new Locale[v0];
            int v2;
            for(v2 = 0; v2 < v0; ++v2) {
                v1[v2] = arg5.get(v2);
            }

            LocaleListCompat.IMPL.setLocaleList(v1);
        }
    }

    private void setLocaleListArray(Locale[] arg2) {
        LocaleListCompat.IMPL.setLocaleList(arg2);
    }

    @IntRange(from=0) public int size() {
        return LocaleListCompat.IMPL.size();
    }

    @NonNull public String toLanguageTags() {
        return LocaleListCompat.IMPL.toLanguageTags();
    }

    public String toString() {
        return LocaleListCompat.IMPL.toString();
    }

    @Nullable public Object unwrap() {
        return LocaleListCompat.IMPL.getLocaleList();
    }

    @RequiresApi(value=24) public static LocaleListCompat wrap(Object arg2) {
        LocaleListCompat v0 = new LocaleListCompat();
        if((arg2 instanceof LocaleList)) {
            v0.setLocaleList(((LocaleList)arg2));
        }

        return v0;
    }
}

