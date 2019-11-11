package org.xwalk.core.internal;

import android.app.Activity;
import android.app.AlertDialog$Builder;
import android.app.AlertDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface$OnCancelListener;
import android.content.DialogInterface$OnClickListener;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Build$VERSION;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.ValueCallback;
import android.widget.EditText;
import android.widget.FrameLayout$LayoutParams;
import java.util.Map;
import org.chromium.content.browser.ContentVideoView;
import org.chromium.ui.base.ActivityWindowAndroid;
import org.chromium.ui.base.WindowAndroid;

@XWalkAPI(createExternally=true) public class XWalkUIClientInternal {
    class org.xwalk.core.internal.XWalkUIClientInternal$9 {
        static {
            org.xwalk.core.internal.XWalkUIClientInternal$9.$SwitchMap$org$xwalk$core$internal$XWalkUIClientInternal$JavascriptMessageTypeInternal = new int[JavascriptMessageTypeInternal.values().length];
            try {
                org.xwalk.core.internal.XWalkUIClientInternal$9.$SwitchMap$org$xwalk$core$internal$XWalkUIClientInternal$JavascriptMessageTypeInternal[JavascriptMessageTypeInternal.JAVASCRIPT_ALERT.ordinal()] = 1;
                goto label_9;
            }
            catch(NoSuchFieldError ) {
                try {
                label_9:
                    org.xwalk.core.internal.XWalkUIClientInternal$9.$SwitchMap$org$xwalk$core$internal$XWalkUIClientInternal$JavascriptMessageTypeInternal[JavascriptMessageTypeInternal.JAVASCRIPT_CONFIRM.ordinal()] = 2;
                    goto label_14;
                }
                catch(NoSuchFieldError ) {
                    try {
                    label_14:
                        org.xwalk.core.internal.XWalkUIClientInternal$9.$SwitchMap$org$xwalk$core$internal$XWalkUIClientInternal$JavascriptMessageTypeInternal[JavascriptMessageTypeInternal.JAVASCRIPT_PROMPT.ordinal()] = 3;
                        goto label_19;
                    }
                    catch(NoSuchFieldError ) {
                        try {
                        label_19:
                            org.xwalk.core.internal.XWalkUIClientInternal$9.$SwitchMap$org$xwalk$core$internal$XWalkUIClientInternal$JavascriptMessageTypeInternal[JavascriptMessageTypeInternal.JAVASCRIPT_BEFOREUNLOAD.ordinal()] = 4;
                            return;
                        }
                        catch(NoSuchFieldError ) {
                            return;
                        }
                    }
                }
            }
        }
    }

    @XWalkAPI public enum ConsoleMessageType {
        public static final enum ConsoleMessageType DEBUG;
        public static final enum ConsoleMessageType ERROR;
        public static final enum ConsoleMessageType INFO;
        public static final enum ConsoleMessageType LOG;
        public static final enum ConsoleMessageType WARNING;

        static {
            ConsoleMessageType.DEBUG = new ConsoleMessageType("DEBUG", 0);
            ConsoleMessageType.ERROR = new ConsoleMessageType("ERROR", 1);
            ConsoleMessageType.LOG = new ConsoleMessageType("LOG", 2);
            ConsoleMessageType.INFO = new ConsoleMessageType("INFO", 3);
            ConsoleMessageType.WARNING = new ConsoleMessageType("WARNING", 4);
            ConsoleMessageType.$VALUES = new ConsoleMessageType[]{ConsoleMessageType.DEBUG, ConsoleMessageType.ERROR, ConsoleMessageType.LOG, ConsoleMessageType.INFO, ConsoleMessageType.WARNING};
        }

        private ConsoleMessageType(String arg1, int arg2) {
            super(arg1, arg2);
        }

        public static ConsoleMessageType valueOf(String arg1) {
            return Enum.valueOf(ConsoleMessageType.class, arg1);
        }

        public static ConsoleMessageType[] values() {
            return ConsoleMessageType.$VALUES.clone();
        }
    }

    @XWalkAPI public enum InitiateByInternal {
        public static final enum InitiateByInternal BY_JAVASCRIPT;
        public static final enum InitiateByInternal BY_USER_GESTURE;

        static {
            InitiateByInternal.BY_USER_GESTURE = new InitiateByInternal("BY_USER_GESTURE", 0);
            InitiateByInternal.BY_JAVASCRIPT = new InitiateByInternal("BY_JAVASCRIPT", 1);
            InitiateByInternal.$VALUES = new InitiateByInternal[]{InitiateByInternal.BY_USER_GESTURE, InitiateByInternal.BY_JAVASCRIPT};
        }

        private InitiateByInternal(String arg1, int arg2) {
            super(arg1, arg2);
        }

        public static InitiateByInternal valueOf(String arg1) {
            return Enum.valueOf(InitiateByInternal.class, arg1);
        }

        public static InitiateByInternal[] values() {
            return InitiateByInternal.$VALUES.clone();
        }
    }

    @XWalkAPI public enum JavascriptMessageTypeInternal {
        public static final enum JavascriptMessageTypeInternal JAVASCRIPT_ALERT;
        public static final enum JavascriptMessageTypeInternal JAVASCRIPT_BEFOREUNLOAD;
        public static final enum JavascriptMessageTypeInternal JAVASCRIPT_CONFIRM;
        public static final enum JavascriptMessageTypeInternal JAVASCRIPT_PROMPT;

        static {
            JavascriptMessageTypeInternal.JAVASCRIPT_ALERT = new JavascriptMessageTypeInternal("JAVASCRIPT_ALERT", 0);
            JavascriptMessageTypeInternal.JAVASCRIPT_CONFIRM = new JavascriptMessageTypeInternal("JAVASCRIPT_CONFIRM", 1);
            JavascriptMessageTypeInternal.JAVASCRIPT_PROMPT = new JavascriptMessageTypeInternal("JAVASCRIPT_PROMPT", 2);
            JavascriptMessageTypeInternal.JAVASCRIPT_BEFOREUNLOAD = new JavascriptMessageTypeInternal("JAVASCRIPT_BEFOREUNLOAD", 3);
            JavascriptMessageTypeInternal.$VALUES = new JavascriptMessageTypeInternal[]{JavascriptMessageTypeInternal.JAVASCRIPT_ALERT, JavascriptMessageTypeInternal.JAVASCRIPT_CONFIRM, JavascriptMessageTypeInternal.JAVASCRIPT_PROMPT, JavascriptMessageTypeInternal.JAVASCRIPT_BEFOREUNLOAD};
        }

        private JavascriptMessageTypeInternal(String arg1, int arg2) {
            super(arg1, arg2);
        }

        public static JavascriptMessageTypeInternal valueOf(String arg1) {
            return Enum.valueOf(JavascriptMessageTypeInternal.class, arg1);
        }

        public static JavascriptMessageTypeInternal[] values() {
            return JavascriptMessageTypeInternal.$VALUES.clone();
        }
    }

    @XWalkAPI public enum LoadStatusInternal {
        public static final enum LoadStatusInternal CANCELLED;
        public static final enum LoadStatusInternal FAILED;
        public static final enum LoadStatusInternal FINISHED;

        static {
            LoadStatusInternal.FINISHED = new LoadStatusInternal("FINISHED", 0);
            LoadStatusInternal.FAILED = new LoadStatusInternal("FAILED", 1);
            LoadStatusInternal.CANCELLED = new LoadStatusInternal("CANCELLED", 2);
            LoadStatusInternal.$VALUES = new LoadStatusInternal[]{LoadStatusInternal.FINISHED, LoadStatusInternal.FAILED, LoadStatusInternal.CANCELLED};
        }

        private LoadStatusInternal(String arg1, int arg2) {
            super(arg1, arg2);
        }

        public static LoadStatusInternal valueOf(String arg1) {
            return Enum.valueOf(LoadStatusInternal.class, arg1);
        }

        public static LoadStatusInternal[] values() {
            return LoadStatusInternal.$VALUES.clone();
        }
    }

    private final int INVALID_ORIENTATION;
    private static final String TAG = "XWalkUIClientInternal";
    private XWalkContentsClient mContentsClient;
    private Context mContext;
    private CustomViewCallbackInternal mCustomViewCallback;
    private View mCustomXWalkView;
    private AlertDialog mDialog;
    private boolean mIsFullscreen;
    private boolean mOriginalForceNotFullscreen;
    private boolean mOriginalFullscreen;
    private int mPreOrientation;
    private EditText mPromptText;
    private int mSystemUiFlag;
    private XWalkViewInternal mXWalkView;

    static {
    }

    @XWalkAPI public XWalkUIClientInternal(XWalkViewInternal arg3) {
        super();
        this.mIsFullscreen = false;
        this.INVALID_ORIENTATION = -2;
        this.mPreOrientation = -2;
        this.mContext = arg3.getContext();
        if(Build$VERSION.SDK_INT >= 19) {
            this.mSystemUiFlag = 0x700;
        }

        this.mXWalkView = arg3;
    }

    @XWalkAPI public boolean OnGetSampleString(XWalkViewInternal arg1, Map arg2) {
        return 1;
    }

    @XWalkAPI public boolean OnGetTranslateString(XWalkViewInternal arg1, Map arg2) {
        return 1;
    }

    static EditText access$000(XWalkUIClientInternal arg0) {
        return arg0.mPromptText;
    }

    private Activity addContentView(View arg6, CustomViewCallbackInternal arg7) {
        Context v1;
        Activity v0 = null;
        try {
            v1 = this.mXWalkView.getContext();
            if((v1 instanceof Activity)) {
            }
            else {
                if((v1 instanceof ContextWrapper)) {
                    v1 = ((ContextWrapper)v1).getBaseContext();
                    if((v1 instanceof Activity)) {
                        goto label_13;
                    }
                }

                goto label_12;
            }
        }
        catch(ClassCastException ) {
        label_12:
            v1 = ((Context)v0);
        }

    label_13:
        if(this.mCustomXWalkView == null) {
            if(v1 == null) {
            }
            else {
                this.mCustomXWalkView = arg6;
                this.mCustomViewCallback = arg7;
                if(this.mContentsClient != null) {
                    this.mContentsClient.onToggleFullscreen(true);
                }

                ((Activity)v1).getWindow().getDecorView().addView(this.mCustomXWalkView, 0, new FrameLayout$LayoutParams(-1, -1, 17));
                return ((Activity)v1);
            }
        }

        if(arg7 != null) {
            arg7.onCustomViewHidden();
        }

        return v0;
    }

    public void enterFullscreenVideo(ContentVideoView arg2) {
        if(this.mContentsClient != null) {
            this.mContentsClient.onToggleFullscreen(true);
        }
    }

    public void exitFullscreenVideo() {
        if(this.mContentsClient != null) {
            this.mContentsClient.onToggleFullscreen(false);
        }
    }

    @XWalkAPI public View getVideoLoadingProgressView() {
        return null;
    }

    @XWalkAPI public boolean isSearchable() {
        return 0;
    }

    @XWalkAPI public void onClearCurrentPage() {
    }

    @XWalkAPI public boolean onConsoleMessage(XWalkViewInternal arg1, String arg2, int arg3, String arg4, ConsoleMessageType arg5) {
        return 0;
    }

    @XWalkAPI public boolean onCreateWindowRequested(XWalkViewInternal arg1, InitiateByInternal arg2, ValueCallback arg3) {
        return 0;
    }

    @XWalkAPI public void onDidChangeThemeColor(XWalkViewInternal arg1, int arg2) {
    }

    @XWalkAPI public void onFullscreenToggled(XWalkViewInternal arg6, boolean arg7) {
        if(!(WindowAndroid.activityFromContext(this.mContext) instanceof Activity)) {
            return;
        }

        Activity v6 = WindowAndroid.activityFromContext(this.mContext);
        int v0 = 19;
        int v1 = 0x400;
        int v3 = 0x800;
        if(arg7) {
            if((v6.getWindow().getAttributes().flags & v3) != 0) {
                this.mOriginalForceNotFullscreen = true;
                v6.getWindow().clearFlags(v3);
            }
            else {
                this.mOriginalForceNotFullscreen = false;
            }

            if(this.mIsFullscreen) {
                return;
            }

            if(Build$VERSION.SDK_INT >= v0) {
                View v6_1 = v6.getWindow().getDecorView();
                this.mSystemUiFlag = v6_1.getSystemUiVisibility();
                v6_1.setSystemUiVisibility(0x1706);
            }
            else if((v6.getWindow().getAttributes().flags & v1) != 0) {
                this.mOriginalFullscreen = true;
            }
            else {
                this.mOriginalFullscreen = false;
                v6.getWindow().addFlags(v1);
            }

            this.mIsFullscreen = true;
        }
        else {
            if(this.mOriginalForceNotFullscreen) {
                v6.getWindow().addFlags(v3);
            }

            if(Build$VERSION.SDK_INT >= v0) {
                v6.getWindow().getDecorView().setSystemUiVisibility(this.mSystemUiFlag);
            }
            else if(!this.mOriginalFullscreen) {
                v6.getWindow().clearFlags(v1);
            }

            this.mIsFullscreen = false;
        }
    }

    @XWalkAPI public void onGeolocationPermissionsHidePrompt() {
    }

    @XWalkAPI public void onGeolocationPermissionsShowPrompt(String arg3, XWalkGeolocationPermissionsCallbackInternal arg4) {
        arg4.invoke(arg3, true, false);
    }

    @XWalkAPI public void onHideCustomView() {
        if(this.mCustomXWalkView != null) {
            if(!(WindowAndroid.activityFromContext(this.mXWalkView.getContext()) instanceof Activity)) {
            }
            else {
                if(this.mContentsClient != null) {
                    this.mContentsClient.onToggleFullscreen(false);
                }

                Activity v0 = WindowAndroid.activityFromContext(this.mXWalkView.getContext());
                if(v0 == null) {
                    Log.e("XWalkUIClientInternal", "onHideCustomView activity = null");
                }

                if(v0 != null) {
                    v0.getWindow().getDecorView().removeView(this.mCustomXWalkView);
                }

                if(this.mCustomViewCallback != null) {
                    this.mCustomViewCallback.onCustomViewHidden();
                }

                if(v0 != null) {
                    int v2 = -2;
                    if(this.mPreOrientation != v2 && this.mPreOrientation >= -1 && this.mPreOrientation <= 14) {
                        v0.setRequestedOrientation(this.mPreOrientation);
                        this.mPreOrientation = v2;
                    }
                }

                this.mCustomXWalkView = null;
                this.mCustomViewCallback = null;
                return;
            }
        }
    }

    @XWalkAPI public void onIconAvailable(XWalkViewInternal arg1, String arg2, Message arg3) {
    }

    @XWalkAPI public void onJavascriptCloseWindow(XWalkViewInternal arg1) {
        if(arg1 != null) {
            if(!(WindowAndroid.activityFromContext(this.mContext) instanceof Activity)) {
            }
            else {
                WindowAndroid.activityFromContext(this.mContext).finish();
                return;
            }
        }
    }

    @XWalkAPI public boolean onJavascriptModalDialog(XWalkViewInternal arg7, JavascriptMessageTypeInternal arg8, String arg9, String arg10, String arg11, XWalkJavascriptResultInternal arg12) {
        switch(org.xwalk.core.internal.XWalkUIClientInternal$9.$SwitchMap$org$xwalk$core$internal$XWalkUIClientInternal$JavascriptMessageTypeInternal[arg8.ordinal()]) {
            case 1: {
                goto label_20;
            }
            case 2: {
                goto label_18;
            }
            case 3: {
                goto label_10;
            }
            case 4: {
                goto label_5;
            }
        }

        return 0;
    label_18:
        return this.onJsConfirm(arg7, arg9, arg10, arg12);
    label_20:
        return this.onJsAlert(arg7, arg9, arg10, arg12);
    label_5:
        return this.onJsConfirm(arg7, arg9, this.mContext.getString(0x7F0C007E), arg12);
    label_10:
        return this.onJsPrompt(arg7, arg9, arg10, arg11, arg12);
    }

    @XWalkAPI public boolean onJsAlert(XWalkViewInternal arg3, String arg4, String arg5, XWalkJavascriptResultInternal arg6) {
        Activity v3 = ActivityWindowAndroid.activityFromContext(this.mContext);
        if(v3 == null) {
            arg6.cancel();
            Log.e("XWalkUIClientInternal", "onJsAlert , but the context is not an activity");
            return 0;
        }

        AlertDialog$Builder v0 = new AlertDialog$Builder(((Context)v3));
        v0.setTitle(this.mContext.getString(0x7F0C0056)).setMessage(((CharSequence)arg5)).setCancelable(true).setPositiveButton(this.mContext.getString(0x104000A), new DialogInterface$OnClickListener(arg6) {
            public void onClick(DialogInterface arg1, int arg2) {
                this.val$fResult.confirm();
                arg1.dismiss();
            }
        }).setOnCancelListener(new DialogInterface$OnCancelListener(arg6) {
            public void onCancel(DialogInterface arg1) {
                this.val$fResult.cancel();
            }
        });
        this.mDialog = v0.create();
        this.mDialog.show();
        return 0;
    }

    @XWalkAPI public boolean onJsConfirm(XWalkViewInternal arg3, String arg4, String arg5, XWalkJavascriptResultInternal arg6) {
        Activity v3 = ActivityWindowAndroid.activityFromContext(this.mContext);
        if(v3 == null) {
            arg6.cancel();
            Log.e("XWalkUIClientInternal", "onJsConfirm , but the context is not an activity");
            return 0;
        }

        AlertDialog$Builder v0 = new AlertDialog$Builder(((Context)v3));
        v0.setTitle(this.mContext.getString(0x7F0C0057)).setMessage(((CharSequence)arg5)).setCancelable(true).setPositiveButton(this.mContext.getString(0x104000A), new DialogInterface$OnClickListener(arg6) {
            public void onClick(DialogInterface arg1, int arg2) {
                this.val$fResult.confirm();
                arg1.dismiss();
            }
        }).setNegativeButton(this.mContext.getString(0x1040000), new DialogInterface$OnClickListener() {
            public void onClick(DialogInterface arg1, int arg2) {
                arg1.cancel();
            }
        }).setOnCancelListener(new DialogInterface$OnCancelListener(arg6) {
            public void onCancel(DialogInterface arg1) {
                this.val$fResult.cancel();
            }
        });
        this.mDialog = v0.create();
        this.mDialog.show();
        return 0;
    }

    @XWalkAPI public boolean onJsPrompt(XWalkViewInternal arg3, String arg4, String arg5, String arg6, XWalkJavascriptResultInternal arg7) {
        Activity v3 = ActivityWindowAndroid.activityFromContext(this.mContext);
        if(v3 == null) {
            arg7.cancel();
            Log.e("XWalkUIClientInternal", "onJsPrompt , but the context is not an activity");
            return 0;
        }

        AlertDialog$Builder v0 = new AlertDialog$Builder(((Context)v3));
        v0.setTitle(this.mContext.getString(0x7F0C0058)).setMessage(((CharSequence)arg5)).setPositiveButton(this.mContext.getString(0x104000A), new DialogInterface$OnClickListener(arg7) {
            public void onClick(DialogInterface arg2, int arg3) {
                this.val$fResult.confirmWithResult(XWalkUIClientInternal.this.mPromptText.getText().toString());
                arg2.dismiss();
            }
        }).setNegativeButton(this.mContext.getString(0x1040000), new DialogInterface$OnClickListener() {
            public void onClick(DialogInterface arg1, int arg2) {
                arg1.cancel();
            }
        }).setOnCancelListener(new DialogInterface$OnCancelListener(arg7) {
            public void onCancel(DialogInterface arg1) {
                this.val$fResult.cancel();
            }
        });
        this.mPromptText = new EditText(this.mContext);
        this.mPromptText.setVisibility(0);
        this.mPromptText.setText(((CharSequence)arg6));
        this.mPromptText.selectAll();
        v0.setView(this.mPromptText);
        this.mDialog = v0.create();
        this.mDialog.show();
        return 0;
    }

    @XWalkAPI public void onPageCommitVisible(XWalkViewInternal arg1, String arg2) {
    }

    @XWalkAPI public void onPageLoadStarted(XWalkViewInternal arg1, String arg2) {
    }

    @XWalkAPI public void onPageLoadStopped(XWalkViewInternal arg1, String arg2, LoadStatusInternal arg3) {
    }

    @XWalkAPI public void onReceivedIcon(XWalkViewInternal arg1, String arg2, Bitmap arg3) {
    }

    @XWalkAPI public void onReceivedTitle(XWalkViewInternal arg1, String arg2) {
    }

    @XWalkAPI public void onRequestFocus(XWalkViewInternal arg1) {
    }

    @XWalkAPI public void onScaleChanged(XWalkViewInternal arg1, float arg2, float arg3) {
    }

    @XWalkAPI public boolean onSearchWord(XWalkViewInternal arg1, String arg2, String arg3, String arg4) {
        return 1;
    }

    @XWalkAPI public boolean onSelectInfoChanged(XWalkViewInternal arg1, long arg2, String arg4, String arg5, String arg6) {
        return 1;
    }

    @XWalkAPI public void onShowCustomView(View arg2, int arg3, CustomViewCallbackInternal arg4) {
        Activity v2 = this.addContentView(arg2, arg4);
        if(v2 == null) {
            return;
        }

        int v4 = v2.getResources().getConfiguration().orientation;
        if(arg3 != v4 && arg3 >= -1 && arg3 <= 14) {
            this.mPreOrientation = v4;
            v2.setRequestedOrientation(arg3);
        }
    }

    @XWalkAPI public void onShowCustomView(View arg1, CustomViewCallbackInternal arg2) {
        this.addContentView(arg1, arg2);
    }

    @XWalkAPI public void onShowSos() {
    }

    @XWalkAPI public void onUnhandledKeyEvent(XWalkViewInternal arg1, KeyEvent arg2) {
    }

    @XWalkAPI public void openFileChooser(XWalkViewInternal arg1, ValueCallback arg2, String arg3, String arg4) {
        if(arg2 != null) {
            arg2.onReceiveValue(null);
        }
    }

    void setContentsClient(XWalkContentsClient arg1) {
        this.mContentsClient = arg1;
    }

    @XWalkAPI public boolean shouldDiscardCurrentPage() {
        return 0;
    }

    @XWalkAPI public boolean shouldOverrideKeyEvent(XWalkViewInternal arg1, KeyEvent arg2) {
        return 0;
    }
}

