package org.chromium.content.browser.accessibility;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ReceiverCallNotAllowedException;
import android.text.SpannableString;
import android.text.style.LocaleSpan;
import android.util.SparseArray;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo$AccessibilityAction;
import android.view.accessibility.AccessibilityNodeInfo$CollectionInfo;
import android.view.accessibility.AccessibilityNodeInfo$CollectionItemInfo;
import android.view.accessibility.AccessibilityNodeInfo$RangeInfo;
import android.view.accessibility.AccessibilityNodeInfo;
import java.util.Locale;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.content_public.browser.WebContents;

@TargetApi(value=21) @JNINamespace(value="content") public class LollipopWebContentsAccessibility extends KitKatWebContentsAccessibility {
    private BroadcastReceiver mBroadcastReceiver;
    private String mSystemLanguageTag;
    private static SparseArray sAccessibilityActionMap;

    static {
        LollipopWebContentsAccessibility.sAccessibilityActionMap = new SparseArray();
    }

    LollipopWebContentsAccessibility(WebContents arg1) {
        super(arg1);
    }

    static String access$002(LollipopWebContentsAccessibility arg0, String arg1) {
        arg0.mSystemLanguageTag = arg1;
        return arg1;
    }

    protected void addAction(AccessibilityNodeInfo arg3, int arg4) {
        Object v0 = LollipopWebContentsAccessibility.sAccessibilityActionMap.get(arg4);
        if(v0 == null) {
            AccessibilityNodeInfo$AccessibilityAction v0_1 = new AccessibilityNodeInfo$AccessibilityAction(arg4, null);
            LollipopWebContentsAccessibility.sAccessibilityActionMap.put(arg4, v0_1);
        }

        arg3.addAction(((AccessibilityNodeInfo$AccessibilityAction)v0));
    }

    protected CharSequence computeText(String arg2, boolean arg3, String arg4) {
        CharSequence v2 = super.computeText(arg2, arg3, arg4);
        if(!arg4.isEmpty() && !arg4.equals(this.mSystemLanguageTag)) {
            if((v2 instanceof SpannableString)) {
            }
            else {
                SpannableString v2_1 = new SpannableString(v2);
            }

            ((SpannableString)v2).setSpan(new LocaleSpan(Locale.forLanguageTag(arg4)), 0, ((SpannableString)v2).length(), 0);
            return v2;
        }

        return v2;
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.registerLocaleChangeReceiver();
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if(!this.isNativeInitialized()) {
            return;
        }

        this.mContext.getApplicationContext().unregisterReceiver(this.mBroadcastReceiver);
    }

    protected void onNativeInit() {
        super.onNativeInit();
        this.mBroadcastReceiver = new BroadcastReceiver() {
            public void onReceive(Context arg1, Intent arg2) {
                LollipopWebContentsAccessibility.this.mSystemLanguageTag = Locale.getDefault().toLanguageTag();
            }
        };
        if(this.mView.isAttachedToWindow()) {
            this.registerLocaleChangeReceiver();
        }
    }

    private void registerLocaleChangeReceiver() {
        if(!this.isNativeInitialized()) {
            return;
        }

        try {
            this.mContext.getApplicationContext().registerReceiver(this.mBroadcastReceiver, new IntentFilter("android.intent.action.LOCALE_CHANGED"));
            goto label_10;
        }
        catch(ReceiverCallNotAllowedException ) {
        label_10:
            this.mSystemLanguageTag = Locale.getDefault().toLanguageTag();
            return;
        }
    }

    protected void setAccessibilityEventCollectionInfo(AccessibilityEvent arg1, int arg2, int arg3, boolean arg4) {
    }

    protected void setAccessibilityEventCollectionItemInfo(AccessibilityEvent arg1, int arg2, int arg3, int arg4, int arg5) {
    }

    protected void setAccessibilityEventHeadingFlag(AccessibilityEvent arg1, boolean arg2) {
    }

    protected void setAccessibilityEventLollipopAttributes(AccessibilityEvent arg1, boolean arg2, boolean arg3, boolean arg4, boolean arg5, int arg6, int arg7) {
    }

    protected void setAccessibilityEventRangeInfo(AccessibilityEvent arg1, int arg2, float arg3, float arg4, float arg5) {
    }

    protected void setAccessibilityNodeInfoCollectionInfo(AccessibilityNodeInfo arg1, int arg2, int arg3, boolean arg4) {
        arg1.setCollectionInfo(AccessibilityNodeInfo$CollectionInfo.obtain(arg2, arg3, arg4));
    }

    protected void setAccessibilityNodeInfoCollectionItemInfo(AccessibilityNodeInfo arg1, int arg2, int arg3, int arg4, int arg5, boolean arg6) {
        arg1.setCollectionItemInfo(AccessibilityNodeInfo$CollectionItemInfo.obtain(arg2, arg3, arg4, arg5, arg6));
    }

    protected void setAccessibilityNodeInfoLollipopAttributes(AccessibilityNodeInfo arg1, boolean arg2, boolean arg3, boolean arg4, boolean arg5, int arg6, int arg7) {
        arg1.setCanOpenPopup(arg2);
        arg1.setContentInvalid(arg3);
        arg1.setDismissable(arg3);
        arg1.setMultiLine(arg5);
        arg1.setInputType(arg6);
        arg1.setLiveRegion(arg7);
    }

    protected void setAccessibilityNodeInfoRangeInfo(AccessibilityNodeInfo arg1, int arg2, float arg3, float arg4, float arg5) {
        arg1.setRangeInfo(AccessibilityNodeInfo$RangeInfo.obtain(arg2, arg3, arg4, arg5));
    }

    protected void setAccessibilityNodeInfoViewIdResourceName(AccessibilityNodeInfo arg1, String arg2) {
        arg1.setViewIdResourceName(arg2);
    }
}

