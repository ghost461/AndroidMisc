package android.support.v4.os;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import java.util.Locale;

@RestrictTo(value={Scope.LIBRARY_GROUP}) interface LocaleListInterface {
    boolean equals(Object arg1);

    Locale get(int arg1);

    @Nullable Locale getFirstMatch(String[] arg1);

    Object getLocaleList();

    int hashCode();

    @IntRange(from=-1) int indexOf(Locale arg1);

    boolean isEmpty();

    void setLocaleList(@NonNull Locale[] arg1);

    @IntRange(from=0) int size();

    String toLanguageTags();

    String toString();
}

