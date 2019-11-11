package org.chromium.components.webrestrictions.browser;

import android.annotation.TargetApi;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.content.pm.ProviderInfo;
import android.database.AbstractCursor;
import android.database.Cursor;
import android.net.Uri$Builder;
import android.net.Uri;
import android.os.Build$VERSION;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class WebRestrictionsContentProvider extends ContentProvider {
    public class WebRestrictionsResult {
        private final int[] mErrorInts;
        private final String[] mErrorStrings;
        private final boolean mShouldProceed;

        static {
        }

        public WebRestrictionsResult(boolean arg1, int[] arg2, String[] arg3) {
            super();
            this.mShouldProceed = arg1;
            String[] v1 = null;
            if(arg2 == null) {
                arg2 = ((int[])v1);
            }
            else {
                Object v2 = arg2.clone();
            }

            this.mErrorInts = arg2;
            if(arg3 == null) {
            }
            else {
                Object v1_1 = arg3.clone();
            }

            this.mErrorStrings = v1;
        }

        public int errorIntCount() {
            if(this.mErrorInts == null) {
                return 0;
            }

            return this.mErrorInts.length;
        }

        public int errorStringCount() {
            if(this.mErrorStrings == null) {
                return 0;
            }

            return this.mErrorStrings.length;
        }

        public int getErrorInt(int arg2) {
            if(this.mErrorInts != null) {
                if(arg2 >= this.mErrorInts.length) {
                }
                else {
                    return this.mErrorInts[arg2];
                }
            }

            return 0;
        }

        public String getErrorString(int arg2) {
            if(this.mErrorStrings != null) {
                if(arg2 >= this.mErrorStrings.length) {
                }
                else {
                    return this.mErrorStrings[arg2];
                }
            }

            return null;
        }

        public boolean shouldProceed() {
            return this.mShouldProceed;
        }
    }

    private static final int AUTHORIZED = 2;
    public static final int BLOCKED = 0;
    public static final int PROCEED = 1;
    private static final int REQUESTED = 3;
    private static final int WEB_RESTRICTIONS = 1;
    private Uri mContentUri;
    private UriMatcher mContentUriMatcher;
    private final Pattern mSelectionPattern;

    protected WebRestrictionsContentProvider() {
        super();
        this.mSelectionPattern = Pattern.compile("\\s*url\\s*=\\s*\'([^\']*)\'");
    }

    public void attachInfo(Context arg4, ProviderInfo arg5) {
        super.attachInfo(arg4, arg5);
        this.mContentUri = new Uri$Builder().scheme("content").authority(arg5.authority).build();
        this.mContentUriMatcher = new UriMatcher(1);
        this.mContentUriMatcher.addURI(arg5.authority, "authorized", 2);
        this.mContentUriMatcher.addURI(arg5.authority, "requested", 3);
    }

    protected abstract boolean canInsert();

    protected abstract boolean contentProviderEnabled();

    public int delete(Uri arg1, String arg2, String[] arg3) {
        return 0;
    }

    protected abstract String[] getErrorColumnNames();

    public String getType(Uri arg3) {
        String v1 = null;
        if(!this.contentProviderEnabled()) {
            return v1;
        }

        if(this.mContentUriMatcher.match(arg3) != 3) {
            return v1;
        }

        if(this.canInsert()) {
            v1 = "text/plain";
        }

        return v1;
    }

    public Uri insert(Uri arg4, ContentValues arg5) {
        Uri v1 = null;
        if(!this.contentProviderEnabled()) {
            return v1;
        }

        if(this.mContentUriMatcher.match(arg4) != 3) {
            return v1;
        }

        String v5 = arg5.getAsString("url");
        if(this.requestInsert(v5)) {
            return arg4.buildUpon().appendPath(v5).build();
        }

        return v1;
    }

    @TargetApi(value=19) private String maybeGetCallingPackage() {
        if(Build$VERSION.SDK_INT < 19) {
            return null;
        }

        return this.getCallingPackage();
    }

    public boolean onCreate() {
        return 1;
    }

    protected void onFilterChanged() {
        this.getContext().getContentResolver().notifyChange(this.mContentUri.buildUpon().appendPath("authorized").build(), null);
    }

    public Cursor query(Uri arg1, String[] arg2, String arg3, String[] arg4, String arg5) {
        Cursor v4 = null;
        if(!this.contentProviderEnabled()) {
            return v4;
        }

        if(this.mContentUriMatcher.match(arg1) != 2) {
            return v4;
        }

        Matcher v1 = this.mSelectionPattern.matcher(((CharSequence)arg3));
        if(!v1.find()) {
            return v4;
        }

        WebRestrictionsResult v1_1 = this.shouldProceed(this.maybeGetCallingPackage(), v1.group(1));
        if(v1_1 == null) {
            return v4;
        }

        return new AbstractCursor(v1_1) {
            public int getColumnCount() {
                return this.val$result.errorIntCount() + this.val$result.errorStringCount() + 1;
            }

            public String[] getColumnNames() {
                String[] v0 = WebRestrictionsContentProvider.this.getErrorColumnNames();
                String[] v1 = new String[this.getColumnCount()];
                int v3 = 0;
                v1[0] = "Should Proceed";
                while(v3 < this.getColumnCount() - 1) {
                    int v2 = v3 + 1;
                    v1[v2] = v0[v3];
                    v3 = v2;
                }

                return v1;
            }

            public int getCount() {
                return 1;
            }

            public double getDouble(int arg3) {
                return 0;
            }

            public float getFloat(int arg1) {
                return 0;
            }

            public int getInt(int arg3) {
                return ((int)this.getLong(arg3));
            }

            public long getLong(int arg4) {
                long v0 = 0;
                if(arg4 == 0) {
                    if(this.val$result.shouldProceed()) {
                        v0 = 1;
                    }

                    return v0;
                }

                --arg4;
                if(arg4 < this.val$result.errorIntCount()) {
                    return ((long)this.val$result.getErrorInt(arg4));
                }

                return v0;
            }

            public short getShort(int arg3) {
                return ((short)(((int)this.getLong(arg3))));
            }

            public String getString(int arg2) {
                arg2 = arg2 - this.val$result.errorIntCount() - 1;
                if(arg2 >= 0 && arg2 < this.val$result.errorStringCount()) {
                    return this.val$result.getErrorString(arg2);
                }

                return null;
            }

            public int getType(int arg4) {
                if(arg4 < this.val$result.errorIntCount() + 1) {
                    return 1;
                }

                if(arg4 < this.val$result.errorIntCount() + this.val$result.errorStringCount() + 1) {
                    return 3;
                }

                return 0;
            }

            public boolean isNull(int arg1) {
                return 0;
            }
        };
    }

    protected abstract boolean requestInsert(String arg1);

    protected abstract WebRestrictionsResult shouldProceed(String arg1, String arg2);

    public int update(Uri arg1, ContentValues arg2, String arg3, String[] arg4) {
        return 0;
    }
}

