package android.support.v4.os;

import android.os.Build$VERSION;
import android.support.annotation.GuardedBy;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.annotation.Size;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;

@RequiresApi(value=14) @RestrictTo(value={Scope.LIBRARY_GROUP}) final class LocaleListHelper {
    private static final Locale EN_LATN = null;
    private static final Locale LOCALE_AR_XB = null;
    private static final Locale LOCALE_EN_XA = null;
    private static final int NUM_PSEUDO_LOCALES = 2;
    private static final String STRING_AR_XB = "ar-XB";
    private static final String STRING_EN_XA = "en-XA";
    private final Locale[] mList;
    @NonNull private final String mStringRepresentation;
    @GuardedBy(value="sLock") private static LocaleListHelper sDefaultAdjustedLocaleList;
    @GuardedBy(value="sLock") private static LocaleListHelper sDefaultLocaleList;
    private static final Locale[] sEmptyList;
    private static final LocaleListHelper sEmptyLocaleList;
    @GuardedBy(value="sLock") private static Locale sLastDefaultLocale;
    @GuardedBy(value="sLock") private static LocaleListHelper sLastExplicitlySetLocaleList;
    private static final Object sLock;

    static {
        LocaleListHelper.sEmptyList = new Locale[0];
        LocaleListHelper.sEmptyLocaleList = new LocaleListHelper(new Locale[0]);
        LocaleListHelper.LOCALE_EN_XA = new Locale("en", "XA");
        LocaleListHelper.LOCALE_AR_XB = new Locale("ar", "XB");
        LocaleListHelper.EN_LATN = LocaleHelper.forLanguageTag("en-Latn");
        LocaleListHelper.sLock = new Object();
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) LocaleListHelper(@NonNull Locale[] arg7) {
        StringBuilder v0_1;
        super();
        if(arg7.length == 0) {
            this.mList = LocaleListHelper.sEmptyList;
            this.mStringRepresentation = "";
        }
        else {
            Locale[] v0 = new Locale[arg7.length];
            HashSet v1 = new HashSet();
            StringBuilder v2 = new StringBuilder();
            int v3 = 0;
            while(true) {
                if(v3 < arg7.length) {
                    Locale v4 = arg7[v3];
                    if(v4 == null) {
                        v0_1 = new StringBuilder();
                        v0_1.append("list[");
                        v0_1.append(v3);
                        v0_1.append("] is null");
                        throw new NullPointerException(v0_1.toString());
                    }
                    else if(v1.contains(v4)) {
                        v0_1 = new StringBuilder();
                        v0_1.append("list[");
                        v0_1.append(v3);
                        v0_1.append("] is a repetition");
                        throw new IllegalArgumentException(v0_1.toString());
                    }
                    else {
                        Object v4_1 = v4.clone();
                        v0[v3] = ((Locale)v4_1);
                        v2.append(LocaleHelper.toLanguageTag(((Locale)v4_1)));
                        if(v3 < arg7.length - 1) {
                            v2.append(',');
                        }

                        v1.add(v4_1);
                        ++v3;
                        continue;
                    }
                }
                else {
                    break;
                }

                return;
            }

            this.mList = v0;
            this.mStringRepresentation = v2.toString();
        }
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) LocaleListHelper(@NonNull Locale arg9, LocaleListHelper arg10) {
        int v9;
        int v3;
        super();
        if(arg9 == null) {
            throw new NullPointerException("topLocale is null");
        }

        int v0 = 0;
        int v1 = arg10 == null ? 0 : arg10.mList.length;
        int v2 = 0;
        while(true) {
            v3 = -1;
            if(v2 >= v1) {
                break;
            }
            else if(arg9.equals(arg10.mList[v2])) {
            }
            else {
                ++v2;
                continue;
            }

            goto label_23;
        }

        v2 = -1;
    label_23:
        int v5 = v2 == v3 ? 1 : 0;
        v5 += v1;
        Locale[] v6 = new Locale[v5];
        v6[0] = arg9.clone();
        if(v2 == v3) {
            v9 = 0;
            goto label_34;
        }
        else {
            for(v9 = 0; v9 < v2; v9 = v3) {
                v3 = v9 + 1;
                v6[v3] = arg10.mList[v9].clone();
            }

            ++v2;
            while(true) {
                if(v2 < v1) {
                    v6[v2] = arg10.mList[v2].clone();
                    ++v2;
                    continue;
                }

                goto label_59;
            }

        label_34:
            while(v9 < v1) {
                v2 = v9 + 1;
                v6[v2] = arg10.mList[v9].clone();
                v9 = v2;
            }
        }

    label_59:
        StringBuilder v9_1 = new StringBuilder();
        while(v0 < v5) {
            v9_1.append(LocaleHelper.toLanguageTag(v6[v0]));
            if(v0 < v5 - 1) {
                v9_1.append(',');
            }

            ++v0;
        }

        this.mList = v6;
        this.mStringRepresentation = v9_1.toString();
    }

    private Locale computeFirstMatch(Collection arg1, boolean arg2) {
        int v1 = this.computeFirstMatchIndex(arg1, arg2);
        Locale v1_1 = v1 == -1 ? null : this.mList[v1];
        return v1_1;
    }

    private int computeFirstMatchIndex(Collection arg4, boolean arg5) {
        int v5;
        if(this.mList.length == 1) {
            return 0;
        }

        if(this.mList.length == 0) {
            return -1;
        }

        int v0 = 0x7FFFFFFF;
        if(arg5) {
            v5 = this.findFirstMatchIndex(LocaleListHelper.EN_LATN);
            if(v5 == 0) {
                return 0;
            }
            else if(v5 < v0) {
            }
            else {
                goto label_19;
            }
        }
        else {
        label_19:
            v5 = 0x7FFFFFFF;
        }

        Iterator v4 = arg4.iterator();
        while(v4.hasNext()) {
            int v2 = this.findFirstMatchIndex(LocaleHelper.forLanguageTag(v4.next()));
            if(v2 == 0) {
                return 0;
            }

            if(v2 >= v5) {
                continue;
            }

            v5 = v2;
        }

        if(v5 == v0) {
            return 0;
        }

        return v5;
    }

    public boolean equals(Object arg6) {
        if((((LocaleListHelper)arg6)) == this) {
            return 1;
        }

        if(!(arg6 instanceof LocaleListHelper)) {
            return 0;
        }

        Locale[] v6 = ((LocaleListHelper)arg6).mList;
        if(this.mList.length != v6.length) {
            return 0;
        }

        int v1;
        for(v1 = 0; v1 < this.mList.length; ++v1) {
            if(!this.mList[v1].equals(v6[v1])) {
                return 0;
            }
        }

        return 1;
    }

    private int findFirstMatchIndex(Locale arg3) {
        int v0;
        for(v0 = 0; v0 < this.mList.length; ++v0) {
            if(LocaleListHelper.matchScore(arg3, this.mList[v0]) > 0) {
                return v0;
            }
        }

        return 0x7FFFFFFF;
    }

    @NonNull @RestrictTo(value={Scope.LIBRARY_GROUP}) static LocaleListHelper forLanguageTags(@Nullable String arg3) {
        if(arg3 != null) {
            if(arg3.isEmpty()) {
            }
            else {
                String[] v3 = arg3.split(",");
                Locale[] v0 = new Locale[v3.length];
                int v1;
                for(v1 = 0; v1 < v0.length; ++v1) {
                    v0[v1] = LocaleHelper.forLanguageTag(v3[v1]);
                }

                return new LocaleListHelper(v0);
            }
        }

        return LocaleListHelper.getEmptyLocaleList();
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) Locale get(int arg2) {
        Locale v2 = arg2 < 0 || arg2 >= this.mList.length ? null : this.mList[arg2];
        return v2;
    }

    @NonNull @Size(min=1) static LocaleListHelper getAdjustedDefault() {
        LocaleListHelper.getDefault();
        Object v0 = LocaleListHelper.sLock;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return LocaleListHelper.sDefaultAdjustedLocaleList;
        label_7:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_7;
        }

        throw v1;
    }

    @NonNull @RestrictTo(value={Scope.LIBRARY_GROUP}) @Size(min=1) static LocaleListHelper getDefault() {
        Locale v0 = Locale.getDefault();
        Object v1 = LocaleListHelper.sLock;
        __monitor_enter(v1);
        try {
            if(!v0.equals(LocaleListHelper.sLastDefaultLocale)) {
                LocaleListHelper.sLastDefaultLocale = v0;
                if(LocaleListHelper.sDefaultLocaleList != null && (v0.equals(LocaleListHelper.sDefaultLocaleList.get(0)))) {
                    __monitor_exit(v1);
                    return LocaleListHelper.sDefaultLocaleList;
                }

                LocaleListHelper.sDefaultLocaleList = new LocaleListHelper(v0, LocaleListHelper.sLastExplicitlySetLocaleList);
                LocaleListHelper.sDefaultAdjustedLocaleList = LocaleListHelper.sDefaultLocaleList;
            }

            __monitor_exit(v1);
            return LocaleListHelper.sDefaultLocaleList;
        label_27:
            __monitor_exit(v1);
        }
        catch(Throwable v0_1) {
            goto label_27;
        }

        throw v0_1;
    }

    @NonNull @RestrictTo(value={Scope.LIBRARY_GROUP}) static LocaleListHelper getEmptyLocaleList() {
        return LocaleListHelper.sEmptyLocaleList;
    }

    @Nullable @RestrictTo(value={Scope.LIBRARY_GROUP}) Locale getFirstMatch(String[] arg2) {
        return this.computeFirstMatch(Arrays.asList(((Object[])arg2)), false);
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) int getFirstMatchIndex(String[] arg2) {
        return this.computeFirstMatchIndex(Arrays.asList(((Object[])arg2)), false);
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) int getFirstMatchIndexWithEnglishSupported(Collection arg2) {
        return this.computeFirstMatchIndex(arg2, true);
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) int getFirstMatchIndexWithEnglishSupported(String[] arg1) {
        return this.getFirstMatchIndexWithEnglishSupported(Arrays.asList(((Object[])arg1)));
    }

    @Nullable @RestrictTo(value={Scope.LIBRARY_GROUP}) Locale getFirstMatchWithEnglishSupported(String[] arg2) {
        return this.computeFirstMatch(Arrays.asList(((Object[])arg2)), true);
    }

    private static String getLikelyScript(Locale arg2) {
        if(Build$VERSION.SDK_INT >= 21) {
            String v2 = arg2.getScript();
            if(!v2.isEmpty()) {
                return v2;
            }

            return "";
        }

        return "";
    }

    public int hashCode() {
        int v0 = 1;
        int v1;
        for(v1 = 0; v1 < this.mList.length; ++v1) {
            v0 = v0 * 0x1F + this.mList[v1].hashCode();
        }

        return v0;
    }

    @IntRange(from=-1) @RestrictTo(value={Scope.LIBRARY_GROUP}) int indexOf(Locale arg3) {
        int v0;
        for(v0 = 0; v0 < this.mList.length; ++v0) {
            if(this.mList[v0].equals(arg3)) {
                return v0;
            }
        }

        return -1;
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) boolean isEmpty() {
        boolean v0 = this.mList.length == 0 ? true : false;
        return v0;
    }

    private static boolean isPseudoLocale(String arg1) {
        boolean v1 = ("en-XA".equals(arg1)) || ("ar-XB".equals(arg1)) ? true : false;
        return v1;
    }

    private static boolean isPseudoLocale(Locale arg1) {
        boolean v1 = (LocaleListHelper.LOCALE_EN_XA.equals(arg1)) || (LocaleListHelper.LOCALE_AR_XB.equals(arg1)) ? true : false;
        return v1;
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) static boolean isPseudoLocalesOnly(@Nullable String[] arg6) {
        if(arg6 == null) {
            return 1;
        }

        if(arg6.length > 3) {
            return 0;
        }

        int v1 = arg6.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            String v4 = arg6[v2];
            if(!v4.isEmpty() && !LocaleListHelper.isPseudoLocale(v4)) {
                return 0;
            }
        }

        return 1;
    }

    @IntRange(from=0, to=1) private static int matchScore(Locale arg4, Locale arg5) {
        int v1 = 1;
        if(arg4.equals(arg5)) {
            return 1;
        }

        if(!arg4.getLanguage().equals(arg5.getLanguage())) {
            return 0;
        }

        if(!LocaleListHelper.isPseudoLocale(arg4)) {
            if(LocaleListHelper.isPseudoLocale(arg5)) {
            }
            else {
                String v0 = LocaleListHelper.getLikelyScript(arg4);
                if(v0.isEmpty()) {
                    String v4 = arg4.getCountry();
                    if(!v4.isEmpty()) {
                        if(v4.equals(arg5.getCountry())) {
                        }
                        else {
                            v1 = 0;
                        }
                    }

                    return v1;
                }
                else {
                    return v0.equals(LocaleListHelper.getLikelyScript(arg5));
                }
            }
        }

        return 0;
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) static void setDefault(@NonNull @Size(min=1) LocaleListHelper arg1) {
        LocaleListHelper.setDefault(arg1, 0);
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) static void setDefault(@NonNull @Size(min=1) LocaleListHelper arg2, int arg3) {
        if(arg2 == null) {
            throw new NullPointerException("locales is null");
        }

        if(arg2.isEmpty()) {
            throw new IllegalArgumentException("locales is empty");
        }

        Object v0 = LocaleListHelper.sLock;
        __monitor_enter(v0);
        try {
            LocaleListHelper.sLastDefaultLocale = arg2.get(arg3);
            Locale.setDefault(LocaleListHelper.sLastDefaultLocale);
            LocaleListHelper.sLastExplicitlySetLocaleList = arg2;
            LocaleListHelper.sDefaultLocaleList = arg2;
            LocaleListHelper.sDefaultAdjustedLocaleList = arg3 == 0 ? LocaleListHelper.sDefaultLocaleList : new LocaleListHelper(LocaleListHelper.sLastDefaultLocale, LocaleListHelper.sDefaultLocaleList);
            __monitor_exit(v0);
            return;
        label_31:
            __monitor_exit(v0);
        }
        catch(Throwable v2) {
            goto label_31;
        }

        throw v2;
    }

    @IntRange(from=0) @RestrictTo(value={Scope.LIBRARY_GROUP}) int size() {
        return this.mList.length;
    }

    @NonNull @RestrictTo(value={Scope.LIBRARY_GROUP}) String toLanguageTags() {
        return this.mStringRepresentation;
    }

    public String toString() {
        StringBuilder v0 = new StringBuilder();
        v0.append("[");
        int v1;
        for(v1 = 0; v1 < this.mList.length; ++v1) {
            v0.append(this.mList[v1]);
            if(v1 < this.mList.length - 1) {
                v0.append(',');
            }
        }

        v0.append("]");
        return v0.toString();
    }
}

