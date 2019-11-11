package org.xwalk.core.internal;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build$VERSION;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import java.net.URI;
import org.chromium.components.navigation_interception.NavigationParams;

public class XWalkNavigationHandlerImpl implements XWalkNavigationHandler {
    private static final String ACTION_GEO_PREFIX = "geo:";
    private static final String ACTION_INTENT_PREFIX = "intent:";
    private static final String ACTION_MAIL_PREFIX = "mailto:";
    private static final String ACTION_MARKET_PREFIX = "market:";
    private static final String ACTION_SMS_PREFIX = "sms:";
    private static final String ACTION_TEL_PREFIX = "tel:";
    public static final String EXTRA_BROWSER_FALLBACK_URL = "browser_fallback_url";
    private static final String PROTOCOL_WTAI_MC_PREFIX = "wtai://wp/mc;";
    private static final String PROTOCOL_WTAI_PREFIX = "wtai://";
    private static final String TAG = "XWalkNavigationHandlerImpl";
    private Context mContext;
    private String mFallbackUrl;

    public XWalkNavigationHandlerImpl(Context arg1) {
        super();
        this.mContext = arg1;
    }

    private Intent createIntentForActionUri(String arg5) {
        Intent v0;
        if(arg5.startsWith("tel:")) {
            v0 = new Intent("android.intent.action.DIAL");
            v0.setData(Uri.parse(arg5));
        }
        else if(arg5.startsWith("geo:")) {
            v0 = new Intent("android.intent.action.VIEW");
            v0.setData(Uri.parse(arg5));
        }
        else if(arg5.startsWith("mailto:")) {
            v0 = new Intent("android.intent.action.VIEW");
            v0.setData(Uri.parse(arg5));
        }
        else if(arg5.startsWith("sms:")) {
            v0 = new Intent("android.intent.action.VIEW");
            int v1 = arg5.indexOf(0x3F);
            int v3 = 4;
            if(v1 == -1) {
                arg5 = arg5.substring(v3);
            }
            else {
                String v1_1 = arg5.substring(v3, v1);
                arg5 = Uri.parse(arg5).getQuery();
                if(arg5 != null && (arg5.startsWith("body="))) {
                    v0.putExtra("sms_body", arg5.substring(5));
                }

                arg5 = v1_1;
            }

            StringBuilder v1_2 = new StringBuilder();
            v1_2.append("sms:");
            v1_2.append(arg5);
            v0.setData(Uri.parse(v1_2.toString()));
            v0.putExtra("address", arg5);
            v0.setType("vnd.android-dir/mms-sms");
        }
        else {
            if(arg5.startsWith("market:")) {
                v0 = new Intent("android.intent.action.VIEW");
                v0.setData(Uri.parse(arg5));
                return v0;
            }

            v0 = null;
        }

        return v0;
    }

    private Intent createIntentForWTAI(String arg3) {
        Intent v0_1;
        if(arg3.startsWith("wtai://wp/mc;")) {
            arg3 = arg3.substring("wtai://wp/mc;".length());
            arg3 = "tel:" + arg3;
            v0_1 = new Intent("android.intent.action.DIAL");
            v0_1.setData(Uri.parse(arg3));
        }
        else {
            v0_1 = null;
        }

        return v0_1;
    }

    public String getFallbackUrl() {
        return this.mFallbackUrl;
    }

    public boolean handleNavigation(NavigationParams arg4) {
        String v0 = arg4.url;
        if(UrlUtilities.isAcceptedScheme(v0)) {
            return 0;
        }

        Intent v1 = v0.startsWith("wtai://") ? this.createIntentForWTAI(v0) : this.createIntentForActionUri(v0);
        if(v1 == null && (this.shouldOverrideUrlLoadingInternal(arg4))) {
            return 1;
        }

        if(v1 != null && (this.startActivity(v1))) {
            return 1;
        }

        return this.handleUrlByMimeType(v0);
    }

    private boolean handleUrlByMimeType(String arg4) {
        String v0 = MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(arg4));
        if(this.shouldHandleMimeType(v0)) {
            Intent v1 = new Intent();
            v1.setAction("android.intent.action.VIEW");
            v1.setDataAndType(Uri.parse(arg4), v0);
            if(v1.resolveActivity(this.mContext.getPackageManager()) != null) {
                this.startActivity(v1);
                return 1;
            }
        }

        return 0;
    }

    public void resetFallbackUrl() {
        this.mFallbackUrl = null;
    }

    private boolean shouldHandleMimeType(String arg3) {
        if(arg3 != null && (arg3.startsWith("application/"))) {
            if(arg3 != "application/xhtml+xml") {
                if(arg3 == "application/xml") {
                }
                else {
                    return 1;
                }
            }

            return 0;
        }

        return 0;
    }

    private boolean shouldOverrideUrlLoadingInternal(NavigationParams arg15) {
        Intent v15_2;
        URI v0_2;
        URI v4_3;
        int v10_1;
        String v12;
        Intent v3;
        String v0 = arg15.url;
        try {
            v3 = Intent.parseUri(v0, 1);
        }
        catch(Exception v15) {
            Log.w("XWalkNavigationHandlerImpl", "Bad URI=" + v0 + " ex=" + v15);
            return 0;
        }

        int v4 = arg15.pageTransitionType & 0xFF;
        int v5 = v4 == 0 ? 1 : 0;
        int v6 = v4 == 7 ? 1 : 0;
        int v7 = (arg15.pageTransitionType & 0x8000000) != 0 ? 1 : 0;
        int v8 = (arg15.pageTransitionType & 0x1000000) != 0 ? 1 : 0;
        int v9 = UrlUtilities.isAcceptedScheme(v0) ^ 1;
        v4 = v4 == 1 ? 1 : 0;
        v4 = v4 == 0 || !arg15.isRedirect || v9 == 0 ? 0 : 1;
        String v10 = UrlUtilities.safeGetStringExtra(v3, "browser_fallback_url");
        ComponentName v11 = null;
        if(v10 == null || !UrlUtilities.isValidForIntentFallbackNavigation(v10)) {
            v12 = ((String)v11);
            v10_1 = 0;
        }
        else {
            v12 = v10;
            v10_1 = 1;
        }

        if(v8 != 0) {
            return 0;
        }

        v8 = v5 == 0 || v7 != 0 ? 0 : 1;
        v7 = v5 == 0 || v7 == 0 || !arg15.isRedirect ? 0 : 1;
        int v13 = v6 == 0 || !arg15.isRedirect ? 0 : 1;
        if(v4 == 0 && v8 == 0 && v7 == 0 && v13 == 0) {
            return 0;
        }

        if(v0.matches(".*youtube\\.com.*[?&]pairingCode=.*")) {
            return 0;
        }

        v4 = UrlUtilities.getIntentHandlers(this.mContext, v3).size() > 0 ? 1 : 0;
        v7 = 0x10000000;
        if(v4 == 0) {
            if(v10_1 != 0) {
                this.mFallbackUrl = v12;
                return 0;
            }

            String v15_1 = v3.getPackage();
            if(v15_1 != null) {
                try {
                    StringBuilder v4_1 = new StringBuilder();
                    v4_1.append("market://details?id=");
                    v4_1.append(v15_1);
                    v4_1.append("&referrer=");
                    v4_1.append(this.mContext.getPackageName());
                    Intent v0_1 = new Intent("android.intent.action.VIEW", Uri.parse(v4_1.toString()));
                    v0_1.addCategory("android.intent.category.BROWSABLE");
                    v0_1.setPackage("com.android.vending");
                    v0_1.addFlags(v7);
                    this.mContext.startActivity(v0_1);
                    return 1;
                }
                catch(ActivityNotFoundException ) {
                    return 0;
                }
            }

            return 0;
        }

        if(v10_1 != 0) {
            v3.removeExtra("browser_fallback_url");
        }

        v3.addCategory("android.intent.category.BROWSABLE");
        v3.setComponent(v11);
        if(Build$VERSION.SDK_INT >= 15) {
            Intent v4_2 = v3.getSelector();
            if(v4_2 != null) {
                v4_2.addCategory("android.intent.category.BROWSABLE");
                v4_2.setComponent(v11);
            }
        }

        v3.putExtra("com.android.browser.application_id", this.mContext.getPackageName());
        v3.addFlags(v7);
        if(v9 == 0) {
            if(!UrlUtilities.isSpecializedHandlerAvailable(this.mContext, v3)) {
                return 0;
            }

            if(arg15.referrer != null) {
                if(v5 == 0) {
                    if(v6 != 0) {
                        goto label_149;
                    }

                    goto label_175;
                }

                try {
                label_149:
                    v4_3 = new URI(v0);
                    v0_2 = new URI(arg15.referrer);
                }
                catch(Exception ) {
                    v0_2 = ((URI)v11);
                    v4_3 = v0_2;
                }

                if(v4_3 == null) {
                    goto label_175;
                }

                if(v0_2 == null) {
                    goto label_175;
                }

                if(!TextUtils.equals(v4_3.getHost(), v0_2.getHost())) {
                    goto label_175;
                }

                try {
                    v15_2 = Intent.parseUri(arg15.referrer, 1);
                }
                catch(Exception ) {
                    v15_2 = ((Intent)v11);
                }

                if(v15_2 == null) {
                    goto label_175;
                }

                if(!UrlUtilities.getIntentHandlers(this.mContext, v15_2).containsAll(UrlUtilities.getIntentHandlers(this.mContext, v3))) {
                    goto label_175;
                }

                return 0;
            }
        }

    label_175:
        if(v3 != null && (this.startActivity(v3))) {
            return 1;
        }

        return 0;
    }

    protected boolean startActivity(Intent arg3) {
        try {
            if(!(this.mContext instanceof Activity)) {
                arg3.addFlags(0x10000000);
            }

            this.mContext.startActivity(arg3);
            arg3 = null;
            return 1;
        }
        catch(ActivityNotFoundException ) {
            Log.w("XWalkNavigationHandlerImpl", "Activity not found for Intent:");
            Log.w("XWalkNavigationHandlerImpl", arg3.toUri(0));
            return 0;
        }
    }
}

