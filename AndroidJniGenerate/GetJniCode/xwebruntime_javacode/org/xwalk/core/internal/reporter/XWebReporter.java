package org.xwalk.core.internal.reporter;

import org.xwalk.core.internal.XWalkSettingsInternal;
import org.xwalk.core.internal.XWalkViewDelegate;

public class XWebReporter {
    public static final int E_ACFUN_VIDEO = 3;
    public static final int E_BILI_VIDEO = 2;
    public static final int E_LE_VIDEO = 1;
    public static final int E_QQ_VIDEO = 0;
    public static final int INVOKE_NOTITY_FUNCTION_ID_IDKEY = 50001;
    public static final int INVOKE_NOTITY_FUNCTION_ID_KVSTAT = 50002;
    public static final int WXWEB_67_IDKEY_ID = 938;
    public static final int WXWEB_IDKEY_ID = 577;
    public static final int WXWEB_IDKEY_RESYCLE_PAGE_COUNT = 67;
    public static final int WXWEB_IDKEY_SYSWEBVIEW_ACFUN_ENTER_FULLSCREEN = 0xA2;
    public static final int WXWEB_IDKEY_SYSWEBVIEW_ACFUN_HOOK = 0x9A;
    public static final int WXWEB_IDKEY_SYSWEBVIEW_BILI_ENTER_FULLSCREEN = 0xA1;
    public static final int WXWEB_IDKEY_SYSWEBVIEW_BILI_HOOK = 0x99;
    public static final int WXWEB_IDKEY_SYSWEBVIEW_LE_ENTER_FULLSCREEN = 0xA0;
    public static final int WXWEB_IDKEY_SYSWEBVIEW_LE_HOOK = 0x98;
    public static final int WXWEB_IDKEY_SYSWEBVIEW_ONSHOWCUSTOMVIEW = 56;
    public static final int WXWEB_IDKEY_SYSWEBVIEW_ONSHOWCUSTOMVIEW_SPECIAL = 57;
    public static final int WXWEB_IDKEY_SYSWEBVIEW_ONSHOWCUSTOMVIEW_SPECIAL_NATIVE = 58;
    public static final int WXWEB_IDKEY_SYSWEBVIEW_ONSHOWCUSTOMVIEW_SPECIAL_NATIVE_VIDEO = 59;
    public static final int WXWEB_IDKEY_SYSWEBVIEW_QQ_ENTER_FULLSCREEN = 0x9F;
    public static final int WXWEB_IDKEY_SYSWEBVIEW_QQ_HOOK = 0x97;
    public static final int WXWEB_IDKEY_WEBVIEW_ONPLUGINDESTROY = 203;
    public static final int WXWEB_IDKEY_WEBVIEW_ONPLUGINREADY = 202;
    public static final int WXWEB_IDKEY_WEBVIEW_ONPLUGINREADY_CANVAS = 206;
    public static final int WXWEB_IDKEY_WEBVIEW_ONPLUGINREADY_MAP = 204;
    public static final int WXWEB_IDKEY_WEBVIEW_ONPLUGINREADY_VIDEO = 205;
    public static final int WXWEB_IDKEY_WXWEBVIEW_ACFUN_ENTER_FULLSCREEN = 0xA6;
    public static final int WXWEB_IDKEY_WXWEBVIEW_ACFUN_HOOK = 0x9E;
    public static final int WXWEB_IDKEY_WXWEBVIEW_BILI_ENTER_FULLSCREEN = 0xA5;
    public static final int WXWEB_IDKEY_WXWEBVIEW_BILI_HOOK = 0x9D;
    public static final int WXWEB_IDKEY_WXWEBVIEW_LE_ENTER_FULLSCREEN = 0xA4;
    public static final int WXWEB_IDKEY_WXWEBVIEW_LE_HOOK = 0x9C;
    public static final int WXWEB_IDKEY_WXWEBVIEW_ONSHOWCUSTOMVIEW_FULLSCREEN = 52;
    public static final int WXWEB_IDKEY_WXWEBVIEW_ONSHOWCUSTOMVIEW_SPECIAL = 53;
    public static final int WXWEB_IDKEY_WXWEBVIEW_ONSHOWCUSTOMVIEW_SPECIAL_NATIVE = 54;
    public static final int WXWEB_IDKEY_WXWEBVIEW_ONSHOWCUSTOMVIEW_SPECIAL_NATIVE_VIDEO = 55;
    public static final int WXWEB_IDKEY_WXWEBVIEW_QQ_ENTER_FULLSCREEN = 0xA3;
    public static final int WXWEB_IDKEY_WXWEBVIEW_QQ_HOOK = 0x9B;
    private static final int WXWEB_KV_XWEB_SAME_LAYER_CATEGORY = 0x4494;
    private static final int WXWEB_URL_TO_BITMAP = 0x4500;
    public static final int WX_WEB_67_IDKEY_WXWEBVIEW_FIRST_CONTENTFUL_PAINT_COST = 201;
    public static final int WX_WEB_67_IDKEY_WXWEBVIEW_FIRST_CONTENTFUL_PAINT_COST_COUNT = 203;
    public static final int WX_WEB_67_IDKEY_WXWEBVIEW_FIRST_MEANINGFUL_PAINT_COST = 200;
    public static final int WX_WEB_67_IDKEY_WXWEBVIEW_FIRST_MEANINGFUL_PAINT_COST_COUNT = 202;
    public static final int WX_WEB_67_IDKEY_WXWEBVIEW_GET_IMAGE_BITMAP_TO_FILE = 230;
    public static final int WX_WEB_67_IDKEY_WXWEBVIEW_GET_IMAGE_BITMAP_TO_FILE_FAILED = 0xE5;
    public static final int WX_WEB_67_IDKEY_WXWEBVIEW_GET_IMAGE_BITMAP_TO_FILE_SUCCESSFUL = 0xE4;
    public static final int WX_WEB_67_IDKEY_WXWEBVIEW_GET_IMAGE_BITMAP_TO_FILE_TIME_COST_1000 = 0xE9;
    public static final int WX_WEB_67_IDKEY_WXWEBVIEW_GET_IMAGE_BITMAP_TO_FILE_TIME_COST_1000_PLUS = 0xEA;
    public static final int WX_WEB_67_IDKEY_WXWEBVIEW_GET_IMAGE_BITMAP_TO_FILE_TIME_COST_200 = 0xE7;
    public static final int WX_WEB_67_IDKEY_WXWEBVIEW_GET_IMAGE_BITMAP_TO_FILE_TIME_COST_500 = 0xE8;
    public static final int WX_WEB_67_IDKEY_WXWEBVIEW_TRANSLATE_GET_SAMPLE_STRING_REQ = 0x60;
    public static final int WX_WEB_67_IDKEY_WXWEBVIEW_TRANSLATE_GET_SAMPLE_STRING_RESP = 97;
    public static final int WX_WEB_67_IDKEY_WXWEBVIEW_TRANSLATE_GET_STRING = 99;
    public static final int WX_WEB_67_IDKEY_WXWEBVIEW_TRANSLATE_GET_STRING_2000 = 101;
    public static final int WX_WEB_67_IDKEY_WXWEBVIEW_TRANSLATE_GET_STRING_500 = 102;
    public static final int WX_WEB_67_IDKEY_WXWEBVIEW_TRANSLATE_REPLACE_STRING = 100;
    public static final int WX_WEB_67_IDKEY_WXWEBVIEW_TRANSLATE_SET_TRANSLATE_MODE = 98;

    public XWebReporter() {
        super();
    }

    public static void idkeyStat(long arg1, long arg3, long arg5) {
        XWalkSettingsInternal.onNotifyCallBackChannel(50001, new Object[]{String.valueOf(arg1), String.valueOf(arg3), String.valueOf(arg5)});
    }

    public static void onGetImageBitmapToFile() {
        XWebReporter.idkeyStat(938, 230, 1);
    }

    public static void onGetImageBitmapToFileFailed() {
        XWebReporter.idkeyStat(938, 0xE5, 1);
    }

    public static void onGetImageBitmapToFileFinish(long arg15) {
        if(arg15 < 200) {
            XWebReporter.idkeyStat(938, 0xE7, 1);
        }
        else if(arg15 < 500) {
            XWebReporter.idkeyStat(938, 0xE8, 1);
        }
        else if(arg15 < 1000) {
            XWebReporter.idkeyStat(938, 0xE9, 1);
        }
        else {
            XWebReporter.idkeyStat(938, 0xEA, 1);
        }
    }

    public static void onGetImageBitmapToFileForKV(int arg2, long arg3) {
        XWebReporter.setKVLog(0x4500, XWalkViewDelegate.sApkVer + "," + arg2 + "," + arg3);
    }

    public static void onGetImageBitmapToFileSuccessful() {
        XWebReporter.idkeyStat(938, 0xE4, 1);
    }

    public static void onPluginDestroy() {
        XWebReporter.idkeyStat(577, 203, 1);
    }

    public static void onPluginReady() {
        XWebReporter.idkeyStat(577, 202, 1);
    }

    public static void onPluginReadyForCanvas() {
        XWebReporter.idkeyStat(577, 206, 1);
    }

    public static void onPluginReadyForKV(String arg2) {
        XWebReporter.setKVLog(0x4494, XWalkViewDelegate.sApkVer + "," + arg2);
    }

    public static void onPluginReadyForMap() {
        XWebReporter.idkeyStat(577, 204, 1);
    }

    public static void onPluginReadyForVideo() {
        XWebReporter.idkeyStat(577, 205, 1);
    }

    public static void onSpecialVideoEnterFullscreen(int arg6, boolean arg7) {
        switch(arg6) {
            case 0: {
                if(arg7) {
                    arg6 = 0xA3;
                    goto label_22;
                }

                arg6 = 0x9F;
                break;
            }
            case 1: {
                if(arg7) {
                    arg6 = 0xA4;
                    goto label_22;
                }

                arg6 = 0xA0;
                break;
            }
            case 2: {
                if(arg7) {
                    arg6 = 0xA5;
                    goto label_22;
                }

                arg6 = 0xA1;
                break;
            }
            case 3: {
                if(arg7) {
                    arg6 = 0xA6;
                    goto label_22;
                }

                arg6 = 0xA2;
                break;
            }
            default: {
                arg6 = 0;
                break;
            }
        }

    label_22:
        if(arg6 > 0) {
            XWebReporter.idkeyStat(577, ((long)arg6), 1);
        }
    }

    public static void onSpecialVideoHook(int arg6, boolean arg7) {
        switch(arg6) {
            case 0: {
                if(arg7) {
                    arg6 = 0x9B;
                    goto label_22;
                }

                arg6 = 0x97;
                break;
            }
            case 1: {
                if(arg7) {
                    arg6 = 0x9C;
                    goto label_22;
                }

                arg6 = 0x98;
                break;
            }
            case 2: {
                if(arg7) {
                    arg6 = 0x9D;
                    goto label_22;
                }

                arg6 = 0x99;
                break;
            }
            case 3: {
                if(arg7) {
                    arg6 = 0x9E;
                    goto label_22;
                }

                arg6 = 0x9A;
                break;
            }
            default: {
                arg6 = 0;
                break;
            }
        }

    label_22:
        if(arg6 > 0) {
            XWebReporter.idkeyStat(577, ((long)arg6), 1);
        }
    }

    public static void onSysWebViewOnShowCustomView() {
        XWebReporter.idkeyStat(577, 56, 1);
    }

    public static void onSysWebViewOnShowCustomViewSpecial() {
        XWebReporter.idkeyStat(577, 57, 1);
    }

    public static void onSysWebViewOnShowCustomViewSpecialNative() {
        XWebReporter.idkeyStat(577, 58, 1);
    }

    public static void onSysWebViewOnShowCustomViewSpecialNativeVideo() {
        XWebReporter.idkeyStat(577, 59, 1);
    }

    public static void onXWWebViewOnShowCustomViewSpecial() {
        XWebReporter.idkeyStat(577, 53, 1);
    }

    public static void onXWWebViewOnShowCustomViewSpecialNative() {
        XWebReporter.idkeyStat(577, 54, 1);
    }

    public static void onXWWebViewOnShowCustomViewSpecialNativeVideo() {
        XWebReporter.idkeyStat(577, 55, 1);
    }

    public static void setKVLog(int arg2, String arg3) {
        XWalkSettingsInternal.onNotifyCallBackChannel(50002, new Object[]{String.valueOf(arg2), arg3});
    }
}

