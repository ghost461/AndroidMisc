package org.xwalk.core.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.chromium.base.CollectionUtil;

public class UrlUtilities {
    private static final HashSet ACCEPTED_SCHEMES = null;
    private static final HashSet FALLBACK_VALID_SCHEMES = null;
    private static final String TAG = "UrlUtilities";

    static {
        UrlUtilities.ACCEPTED_SCHEMES = CollectionUtil.newHashSet(new String[]{"about", "app", "content", "data", "file", "http", "https", "javascript"});
        UrlUtilities.FALLBACK_VALID_SCHEMES = CollectionUtil.newHashSet(new String[]{"http", "https"});
    }

    public UrlUtilities() {
        super();
    }

    public static List getIntentHandlers(Context arg3, Intent arg4) {
        List v3 = arg3.getPackageManager().queryIntentActivities(arg4, 0);
        ArrayList v4 = new ArrayList();
        Iterator v3_1 = v3.iterator();
        while(v3_1.hasNext()) {
            Object v0 = v3_1.next();
            ((List)v4).add(new ComponentName(((ResolveInfo)v0).activityInfo.packageName, ((ResolveInfo)v0).activityInfo.name));
        }

        return ((List)v4);
    }

    public static boolean isAcceptedScheme(String arg2) {
        if(arg2 != null) {
            if(arg2.length() == 0) {
            }
            else {
                arg2 = arg2.toLowerCase().trim();
                if(arg2 != null) {
                    if(arg2.length() == 0) {
                    }
                    else {
                        int v1 = arg2.indexOf(":");
                        if(v1 <= 0) {
                            return 0;
                        }
                        else {
                            return UrlUtilities.ACCEPTED_SCHEMES.contains(arg2.substring(0, v1));
                        }
                    }
                }

                return 0;
            }
        }

        return 0;
    }

    public static boolean isAcceptedScheme(URI arg1) {
        return UrlUtilities.ACCEPTED_SCHEMES.contains(arg1.getScheme());
    }

    public static boolean isPackageSpecializedHandler(Context arg3, String arg4, Intent arg5) {
        try {
            List v3_1 = arg3.getPackageManager().queryIntentActivities(arg5, 0x40);
            if(v3_1 != null) {
                if(v3_1.size() == 0) {
                }
                else {
                    Iterator v3_2 = v3_1.iterator();
                    while(true) {
                    label_9:
                        if(v3_2.hasNext()) {
                            Object v5 = v3_2.next();
                            IntentFilter v1 = ((ResolveInfo)v5).filter;
                            if(v1 == null) {
                                continue;
                            }
                            else {
                                if(v1.countDataAuthorities() == 0) {
                                    continue;
                                }

                                if(v1.countDataPaths() == 0) {
                                    continue;
                                }
                                else if(TextUtils.isEmpty(((CharSequence)arg4))) {
                                    return 1;
                                }
                                else {
                                    ActivityInfo v5_1 = ((ResolveInfo)v5).activityInfo;
                                    if(v5_1 == null) {
                                        continue;
                                    }
                                    else if(!v5_1.packageName.equals(arg4)) {
                                        goto label_30;
                                    }
                                    else {
                                        return 1;
                                    }
                                }
                            }
                        }
                        else {
                            return 0;
                        }

                        return 0;
                    }
                }
            }

            return 0;
        }
        catch(RuntimeException v3) {
            goto label_36;
        }

    label_30:
        goto label_9;
        return 1;
    label_36:
        Log.e("UrlUtilities", "isPackageSpecializedHandler e=" + v3);
        return 0;
    }

    public static boolean isSpecializedHandlerAvailable(Context arg1, Intent arg2) {
        return UrlUtilities.isPackageSpecializedHandler(arg1, null, arg2);
    }

    public static boolean isValidForIntentFallbackNavigation(String arg1) {
        try {
            return UrlUtilities.isValidForIntentFallbackNavigation(new URI(arg1));
        }
        catch(URISyntaxException ) {
            return 0;
        }
    }

    public static boolean isValidForIntentFallbackNavigation(URI arg1) {
        return UrlUtilities.FALLBACK_VALID_SCHEMES.contains(arg1.getScheme());
    }

    public static String safeGetStringExtra(Intent arg2, String arg3) {
        try {
            return arg2.getStringExtra(arg3);
        }
        catch(Throwable ) {
            Log.e("UrlUtilities", "getStringExtra failed on intent: " + arg2);
            return null;
        }
    }
}

