package android.support.v4.media;

import android.os.Bundle;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;

@RestrictTo(value={Scope.LIBRARY_GROUP}) public class MediaBrowserCompatUtils {
    public MediaBrowserCompatUtils() {
        super();
    }

    public static boolean areSameOptions(Bundle arg5, Bundle arg6) {
        boolean v0 = true;
        if(arg5 == arg6) {
            return 1;
        }

        int v2 = -1;
        if(arg5 == null) {
            if(arg6.getInt("android.media.browse.extra.PAGE", v2) != v2 || arg6.getInt("android.media.browse.extra.PAGE_SIZE", v2) != v2) {
                v0 = false;
            }
            else {
            }

            return v0;
        }

        if(arg6 == null) {
            if(arg5.getInt("android.media.browse.extra.PAGE", v2) != v2 || arg5.getInt("android.media.browse.extra.PAGE_SIZE", v2) != v2) {
                v0 = false;
            }
            else {
            }

            return v0;
        }

        if(arg5.getInt("android.media.browse.extra.PAGE", v2) != arg6.getInt("android.media.browse.extra.PAGE", v2) || arg5.getInt("android.media.browse.extra.PAGE_SIZE", v2) != arg6.getInt("android.media.browse.extra.PAGE_SIZE", v2)) {
            v0 = false;
        }
        else {
        }

        return v0;
    }

    public static boolean hasDuplicatedItems(Bundle arg6, Bundle arg7) {
        int v0 = -1;
        int v1 = arg6 == null ? -1 : arg6.getInt("android.media.browse.extra.PAGE", v0);
        int v2 = arg7 == null ? -1 : arg7.getInt("android.media.browse.extra.PAGE", v0);
        int v6 = arg6 == null ? -1 : arg6.getInt("android.media.browse.extra.PAGE_SIZE", v0);
        int v7 = arg7 == null ? -1 : arg7.getInt("android.media.browse.extra.PAGE_SIZE", v0);
        int v3 = 0x7FFFFFFF;
        if(v1 == v0 || v6 == v0) {
            v6 = 0x7FFFFFFF;
            v1 = 0;
        }
        else {
            v1 *= v6;
            v6 = v6 + v1 - 1;
        }

        if(v2 == v0 || v7 == v0) {
            v0 = 0;
        }
        else {
            v0 = v7 * v2;
            v3 = v7 + v0 - 1;
        }

        if(v1 <= v0 && v0 <= v6) {
            return 1;
        }

        if(v1 <= v3 && v3 <= v6) {
            return 1;
        }

        return 0;
    }
}

