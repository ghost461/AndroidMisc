package android.support.v4.provider;

import android.support.annotation.ArrayRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.v4.util.Preconditions;
import android.util.Base64;
import java.util.List;

public final class FontRequest {
    private final List mCertificates;
    private final int mCertificatesArray;
    private final String mIdentifier;
    private final String mProviderAuthority;
    private final String mProviderPackage;
    private final String mQuery;

    public FontRequest(@NonNull String arg1, @NonNull String arg2, @NonNull String arg3, @NonNull List arg4) {
        super();
        this.mProviderAuthority = Preconditions.checkNotNull(arg1);
        this.mProviderPackage = Preconditions.checkNotNull(arg2);
        this.mQuery = Preconditions.checkNotNull(arg3);
        this.mCertificates = Preconditions.checkNotNull(arg4);
        this.mCertificatesArray = 0;
        StringBuilder v1 = new StringBuilder(this.mProviderAuthority);
        v1.append("-");
        v1.append(this.mProviderPackage);
        v1.append("-");
        v1.append(this.mQuery);
        this.mIdentifier = v1.toString();
    }

    public FontRequest(@NonNull String arg1, @NonNull String arg2, @NonNull String arg3, @ArrayRes int arg4) {
        super();
        this.mProviderAuthority = Preconditions.checkNotNull(arg1);
        this.mProviderPackage = Preconditions.checkNotNull(arg2);
        this.mQuery = Preconditions.checkNotNull(arg3);
        this.mCertificates = null;
        boolean v1 = arg4 != 0 ? true : false;
        Preconditions.checkArgument(v1);
        this.mCertificatesArray = arg4;
        StringBuilder v1_1 = new StringBuilder(this.mProviderAuthority);
        v1_1.append("-");
        v1_1.append(this.mProviderPackage);
        v1_1.append("-");
        v1_1.append(this.mQuery);
        this.mIdentifier = v1_1.toString();
    }

    @Nullable public List getCertificates() {
        return this.mCertificates;
    }

    @ArrayRes public int getCertificatesArrayResId() {
        return this.mCertificatesArray;
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public String getIdentifier() {
        return this.mIdentifier;
    }

    public String getProviderAuthority() {
        return this.mProviderAuthority;
    }

    public String getProviderPackage() {
        return this.mProviderPackage;
    }

    public String getQuery() {
        return this.mQuery;
    }

    public String toString() {
        StringBuilder v0 = new StringBuilder();
        v0.append("FontRequest {mProviderAuthority: " + this.mProviderAuthority + ", mProviderPackage: " + this.mProviderPackage + ", mQuery: " + this.mQuery + ", mCertificates:");
        int v2;
        for(v2 = 0; v2 < this.mCertificates.size(); ++v2) {
            v0.append(" [");
            Object v3 = this.mCertificates.get(v2);
            int v4;
            for(v4 = 0; v4 < ((List)v3).size(); ++v4) {
                v0.append(" \"");
                v0.append(Base64.encodeToString(((List)v3).get(v4), 0));
                v0.append("\"");
            }

            v0.append(" ]");
        }

        v0.append("}");
        v0.append("mCertificatesArray: " + this.mCertificatesArray);
        return v0.toString();
    }
}

