package org.chromium.content.browser.selection;

import android.annotation.TargetApi;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Rect;
import android.os.Build$VERSION;
import android.support.annotation.Nullable;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.CharacterStyle;
import android.text.style.ParagraphStyle;
import android.text.style.UpdateAppearance;
import android.view.ActionMode$Callback;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View$MeasureSpec;
import android.view.View$OnClickListener;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.WindowManager$BadTokenException;
import android.view.textclassifier.TextClassifier;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Timer;
import java.util.TimerTask;
import org.chromium.base.ApiCompatibilityUtils;
import org.chromium.base.Log;
import org.chromium.base.VisibleForTesting;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.base.metrics.RecordUserAction;
import org.chromium.content.browser.ContentClassFactory;
import org.chromium.content.browser.GestureListenerManagerImpl;
import org.chromium.content.browser.PopupController$HideablePopup;
import org.chromium.content.browser.PopupController;
import org.chromium.content.browser.WindowAndroidChangedObserver;
import org.chromium.content.browser.WindowEventObserver;
import org.chromium.content.browser.webcontents.WebContentsImpl;
import org.chromium.content_public.browser.ActionModeCallbackHelper;
import org.chromium.content_public.browser.ImeEventObserver$$CC;
import org.chromium.content_public.browser.ImeEventObserver;
import org.chromium.content_public.browser.SelectionClient$Result;
import org.chromium.content_public.browser.SelectionClient$ResultCallback;
import org.chromium.content_public.browser.SelectionClient;
import org.chromium.content_public.browser.SelectionPopupController;
import org.chromium.content_public.browser.WebContents$UserDataFactory;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.ui.base.WindowAndroid$IntentCallback;
import org.chromium.ui.base.WindowAndroid;

@TargetApi(value=23) @JNINamespace(value="content") public class SelectionPopupControllerImpl extends ActionModeCallbackHelper implements HideablePopup, WindowAndroidChangedObserver, WindowEventObserver, ImeEventObserver, SelectionPopupController {
    class org.chromium.content.browser.selection.SelectionPopupControllerImpl$3 implements View$OnClickListener {
        org.chromium.content.browser.selection.SelectionPopupControllerImpl$3(SelectionPopupControllerImpl arg1) {
            SelectionPopupControllerImpl.this = arg1;
            super();
        }

        public void onClick(View arg2) {
            SelectionPopupControllerImpl.this.onClickPop(arg2);
        }
    }

    public interface ReadbackViewCallback {
        View getReadbackView();
    }

    class SmartSelectionCallback implements ResultCallback {
        SmartSelectionCallback(SelectionPopupControllerImpl arg1, org.chromium.content.browser.selection.SelectionPopupControllerImpl$1 arg2) {
            this(arg1);
        }

        private SmartSelectionCallback(SelectionPopupControllerImpl arg1) {
            SelectionPopupControllerImpl.this = arg1;
            super();
        }

        public void onClassified(Result arg4) {
            Result v1 = null;
            if(!SelectionPopupControllerImpl.this.hasSelection()) {
                SelectionPopupControllerImpl.this.mClassificationResult = v1;
                return;
            }

            if(arg4.startAdjust <= 0) {
                if(arg4.endAdjust < 0) {
                }
                else {
                    SelectionPopupControllerImpl.this.mClassificationResult = arg4;
                    if(arg4.startAdjust == 0) {
                        if(arg4.endAdjust != 0) {
                        }
                        else {
                            if(SelectionPopupControllerImpl.this.mSelectionMetricsLogger != null) {
                                SelectionPopupControllerImpl.this.mSelectionMetricsLogger.logSelectionModified(SelectionPopupControllerImpl.this.mLastSelectedText, SelectionPopupControllerImpl.this.mLastSelectionOffset, SelectionPopupControllerImpl.this.mClassificationResult);
                            }

                            SelectionPopupControllerImpl.this.showActionModeOrClearOnFailure();
                            return;
                        }
                    }

                    SelectionPopupControllerImpl.this.mWebContents.adjustSelectionByCharacterOffset(arg4.startAdjust, arg4.endAdjust, true);
                    return;
                }
            }

            SelectionPopupControllerImpl.this.mClassificationResult = v1;
            SelectionPopupControllerImpl.this.showActionModeOrClearOnFailure();
        }
    }

    final class UserDataFactoryLazyHolder {
        private static final UserDataFactory INSTANCE;

        static {
            UserDataFactoryLazyHolder.INSTANCE = SelectionPopupControllerImpl$UserDataFactoryLazyHolder$$Lambda$0.$instance;
        }

        private UserDataFactoryLazyHolder() {
            super();
        }

        static UserDataFactory access$000() {
            return UserDataFactoryLazyHolder.INSTANCE;
        }
    }

    private static final int MAX_SHARE_QUERY_LENGTH = 100000;
    private static final int MENU_ITEM_ORDER_TEXT_PROCESS_START = 100;
    private static final int PREFIX_LENGTH = 10;
    private static final int SELECT_CHANGED = 2;
    private static final int SELECT_NONE = 0;
    private static final int SELECT_START = 1;
    private static final int SHOW_DELAY_MS = 300;
    private static final int SHOW_POP_WIN_POS_X_LEFT = 50;
    private static final int SHOW_POP_WIN_POS_X_RIGHT = 50;
    private static final String TAG = "SelectionPopupCtlr";
    private ActionMode mActionMode;
    private AdditionalMenuItemProvider mAdditionalMenuItemProvider;
    private int mAllowedMenuItems;
    private ActionMode$Callback mCallback;
    private boolean mCanEditRichly;
    private boolean mCanSelectAllForPastePopup;
    private Result mClassificationResult;
    private Context mContext;
    private boolean mEditable;
    private String mFirstSelection;
    private long mFirstSelectionTime;
    private PopupWindow mFloatPopWinCustom;
    private SelectionInsertionHandleObserver mHandleObserver;
    private boolean mHasSelection;
    private boolean mHidden;
    private boolean mInitialized;
    private boolean mIsCustomPastePopupMenu;
    private boolean mIsInsertionForTesting;
    private boolean mIsPasswordType;
    private String mLastSelectedPrefixText;
    private String mLastSelectedSuffixText;
    private String mLastSelectedText;
    private int mLastSelectionOffset;
    private String mLastWeChatPickedWord;
    private long mNativeSelectionPopupController;
    private ActionMode$Callback mNonSelectionCallback;
    private PastePopupMenu mPastePopupMenu;
    private View$OnClickListener mPopListener;
    private Timer mPopTimer;
    private TimerTask mPopTimerTask;
    private PopupController mPopupController;
    private boolean mPreserveSelectionOnNextLossOfFocus;
    private Runnable mRepeatingDelayShowPopWinCustom;
    private Runnable mRepeatingHideRunnable;
    private ResultCallback mResultCallback;
    private SelectionClient mSelectionClient;
    private SmartSelectionMetricsLogger mSelectionMetricsLogger;
    private String mSelectionPrefix;
    private final Rect mSelectionRect;
    private int mSelectionStatus;
    private String mSelectionSuffix;
    private TextView mTextviewCopy;
    private TextView mTextviewCut;
    private TextView mTextviewPaste;
    private TextView mTextviewSeach;
    private TextView mTextviewSelectAll;
    private TextView mTextviewShare;
    private boolean mUnselectAllOnDismiss;
    private View mView;
    private boolean mWasPastePopupShowingOnInsertionDragStart;
    private WebContentsImpl mWebContents;
    private WindowAndroid mWindowAndroid;
    private static boolean sShouldGetReadbackViewFromWindowAndroid = false;

    static {
    }

    public SelectionPopupControllerImpl(WebContents arg2) {
        super();
        this.mSelectionRect = new Rect();
        this.mSelectionStatus = 0;
        this.mPopTimer = null;
        this.mPopTimerTask = null;
        this.mPopListener = new org.chromium.content.browser.selection.SelectionPopupControllerImpl$3(this);
        this.mWebContents = ((WebContentsImpl)arg2);
    }

    static boolean access$100(SelectionPopupControllerImpl arg0) {
        return arg0.mHidden;
    }

    static WebContentsImpl access$1000(SelectionPopupControllerImpl arg0) {
        return arg0.mWebContents;
    }

    static boolean access$1100(SelectionPopupControllerImpl arg0) {
        return arg0.canPaste();
    }

    static Result access$1200(SelectionPopupControllerImpl arg0) {
        return arg0.mClassificationResult;
    }

    static Result access$1202(SelectionPopupControllerImpl arg0, Result arg1) {
        arg0.mClassificationResult = arg1;
        return arg1;
    }

    static SmartSelectionMetricsLogger access$1300(SelectionPopupControllerImpl arg0) {
        return arg0.mSelectionMetricsLogger;
    }

    static String access$1400(SelectionPopupControllerImpl arg0) {
        return arg0.mLastSelectedText;
    }

    static int access$1500(SelectionPopupControllerImpl arg0) {
        return arg0.mLastSelectionOffset;
    }

    static long access$200(SelectionPopupControllerImpl arg2) {
        return arg2.getDefaultHideDuration();
    }

    static Runnable access$300(SelectionPopupControllerImpl arg0) {
        return arg0.mRepeatingHideRunnable;
    }

    static View access$400(SelectionPopupControllerImpl arg0) {
        return arg0.mView;
    }

    static void access$500(SelectionPopupControllerImpl arg0, long arg1) {
        arg0.hideActionModeTemporarily(arg1);
    }

    static void access$600(SelectionPopupControllerImpl arg0) {
        arg0.showPopWinCustomOrClearOnFailure();
    }

    static void access$800(SelectionPopupControllerImpl arg0, View arg1) {
        arg0.onClickPop(arg1);
    }

    static Runnable access$900(SelectionPopupControllerImpl arg0) {
        return arg0.mRepeatingDelayShowPopWinCustom;
    }

    public void adjustSelectPosition(long arg3, String arg5, int arg6, int arg7) {
        if(arg3 == this.mFirstSelectionTime) {
            System.currentTimeMillis();
            this.mWebContents.adjustSelectionByCharacterOffset(arg6, arg7, true);
        }
    }

    private boolean canPaste() {
        return this.mContext.getSystemService("clipboard").hasPrimaryClip();
    }

    @VisibleForTesting public boolean canPasteAsPlainText() {
        if(Build$VERSION.SDK_INT < 26) {
            return 0;
        }

        if(!this.mCanEditRichly) {
            return 0;
        }

        Object v0 = this.mContext.getSystemService("clipboard");
        if(!((ClipboardManager)v0).hasPrimaryClip()) {
            return 0;
        }

        ClipData v0_1 = ((ClipboardManager)v0).getPrimaryClip();
        ClipDescription v2 = v0_1.getDescription();
        CharSequence v0_2 = v0_1.getItemAt(0).getText();
        if((v2.hasMimeType("text/plain")) && ((v0_2 instanceof Spanned)) && (this.hasStyleSpan(((Spanned)v0_2)))) {
            return 1;
        }

        return v2.hasMimeType("text/html");
    }

    @VisibleForTesting public boolean canSelectAll() {
        return this.mCanSelectAllForPastePopup;
    }

    public void clearSelection() {
        if(this.mWebContents == null) {
            return;
        }

        this.mWebContents.collapseSelection();
        this.mClassificationResult = null;
    }

    @VisibleForTesting public void copy() {
        this.mWebContents.copy();
    }

    public static SelectionPopupControllerImpl create(Context arg2, WindowAndroid arg3, WebContents arg4, View arg5) {
        Object v4 = arg4.getOrSetUserData(SelectionPopupControllerImpl.class, UserDataFactoryLazyHolder.access$000());
        ((SelectionPopupControllerImpl)v4).init(arg2, arg3, arg5, true);
        return ((SelectionPopupControllerImpl)v4);
    }

    private void createActionMenu(ActionMode arg3, Menu arg4) {
        SelectionPopupControllerImpl.initializeMenu(this.mContext, arg3, arg4);
        this.updateAssistMenuItem(arg4);
        this.removeActionMenuItemsIfNecessary(arg4);
        SelectionPopupControllerImpl.setPasteAsPlainTextMenuItemTitle(arg4);
        Object v3 = this.mWindowAndroid.getContext().get();
        if(this.mClassificationResult != null && this.mAdditionalMenuItemProvider != null && v3 != null) {
            this.mAdditionalMenuItemProvider.addMenuItems(((Context)v3), arg4, this.mClassificationResult.textClassification);
        }

        if(this.hasSelection()) {
            if(this.isSelectionPassword()) {
            }
            else {
                this.initializeTextProcessingMenu(arg4);
                return;
            }
        }
    }

    private void createAndShowPastePopup() {
        if(this.mView.getParent() != null) {
            if(this.mView.getVisibility() != 0) {
            }
            else {
                if(!this.supportsFloatingActionMode() && !this.canPaste() && this.mNonSelectionCallback == null) {
                    return;
                }

                this.destroyPastePopup();
                org.chromium.content.browser.selection.SelectionPopupControllerImpl$5 v0 = new PastePopupMenuDelegate() {
                    public boolean canPaste() {
                        return SelectionPopupControllerImpl.this.canPaste();
                    }

                    public boolean canPasteAsPlainText() {
                        return SelectionPopupControllerImpl.this.canPasteAsPlainText();
                    }

                    public boolean canSelectAll() {
                        return SelectionPopupControllerImpl.this.canSelectAll();
                    }

                    public void paste() {
                        SelectionPopupControllerImpl.this.paste();
                        SelectionPopupControllerImpl.this.mWebContents.dismissTextHandles();
                    }

                    public void pasteAsPlainText() {
                        SelectionPopupControllerImpl.this.pasteAsPlainText();
                        SelectionPopupControllerImpl.this.mWebContents.dismissTextHandles();
                    }

                    public void selectAll() {
                        SelectionPopupControllerImpl.this.selectAll();
                    }
                };
                Object v1 = this.mWindowAndroid.getContext().get();
                if(v1 == null) {
                    return;
                }

                this.mPastePopupMenu = this.supportsFloatingActionMode() ? new FloatingPastePopupMenu(((Context)v1), this.mView, ((PastePopupMenuDelegate)v0), this.mNonSelectionCallback) : new LegacyPastePopupMenu(((Context)v1), this.mView, ((PastePopupMenuDelegate)v0));
                this.showPastePopup();
                return;
            }
        }
    }

    public static SelectionPopupControllerImpl createForTesting(Context arg1, WindowAndroid arg2, WebContents arg3, View arg4, PopupController arg5) {
        SelectionPopupControllerImpl v0 = new SelectionPopupControllerImpl(arg3);
        v0.setPopupControllerForTesting(arg5);
        v0.init(arg1, arg2, arg4, false);
        return v0;
    }

    @TargetApi(value=23) private static Intent createProcessTextIntent() {
        return new Intent().setAction("android.intent.action.PROCESS_TEXT").setType("text/plain");
    }

    @TargetApi(value=23) private Intent createProcessTextIntentForResolveInfo(ResolveInfo arg4) {
        return SelectionPopupControllerImpl.createProcessTextIntent().putExtra("android.intent.extra.PROCESS_TEXT_READONLY", this.isFocusedNodeEditable() ^ 1).setClassName(arg4.activityInfo.packageName, arg4.activityInfo.name);
    }

    @VisibleForTesting public void cut() {
        this.mWebContents.cut();
    }

    public void destroyActionModeAndKeepSelection() {
        this.mUnselectAllOnDismiss = false;
        this.finishActionMode();
        this.finishFloatPopWinCustom();
    }

    public void destroyActionModeAndUnselect() {
        this.mUnselectAllOnDismiss = true;
        this.finishActionMode();
        this.finishFloatPopWinCustom();
    }

    public void destroyPastePopup() {
        if(this.isCustomPastePopupShowing()) {
            this.hideFloatPopWinCustom(true);
        }
    }

    public void destroySelectActionMode() {
        this.finishActionMode();
    }

    public void didReceivedWeChatSelectionInfo(String arg1, int arg2, int arg3, String arg4, String arg5, int arg6) {
        this.mLastWeChatPickedWord = arg1;
    }

    @VisibleForTesting void doAssistAction() {
        if(this.mClassificationResult != null) {
            if(!this.mClassificationResult.hasNamedAction()) {
            }
            else if(this.mClassificationResult.onClickListener != null) {
                this.mClassificationResult.onClickListener.onClick(this.mView);
                return;
            }
            else if(this.mClassificationResult.intent != null) {
                Object v0 = this.mWindowAndroid.getContext().get();
                if(v0 == null) {
                    return;
                }
                else {
                    ((Context)v0).startActivity(this.mClassificationResult.intent);
                    return;
                }
            }
            else {
                return;
            }
        }
    }

    public void finishActionMode() {
        this.mHidden = false;
        if(this.mView != null) {
            this.mView.removeCallbacks(this.mRepeatingHideRunnable);
        }

        if(this.isActionModeValid()) {
            this.mActionMode.finish();
            this.mActionMode = null;
        }
    }

    public void finishFloatPopWinCustom() {
        if(this.mView != null) {
            this.mView.removeCallbacks(this.mRepeatingDelayShowPopWinCustom);
        }

        if(this.mFloatPopWinCustom != null) {
            this.mFloatPopWinCustom.dismiss();
            this.mFloatPopWinCustom = null;
            Log.d("SelectionPopupCtlr", "finishFloatPopWinCustom mUnselectAllOnDismiss = " + this.mUnselectAllOnDismiss);
            if(this.mUnselectAllOnDismiss) {
                this.mWebContents.dismissTextHandles();
                this.clearSelection();
            }
        }

        this.mHidden = false;
        this.mIsCustomPastePopupMenu = false;
    }

    public static SelectionPopupControllerImpl fromWebContents(WebContents arg2) {
        return arg2.getOrSetUserData(SelectionPopupControllerImpl.class, null);
    }

    public ActionModeCallbackHelper getActionModeCallbackHelper() {
        return this;
    }

    private int getActionType(int arg3, int arg4) {
        int v0 = 105;
        int v1 = 0x1020041;
        if(arg4 == v1) {
            return v0;
        }

        if(arg3 == 0x7F070098) {
            return 200;
        }

        if(arg3 == 0x7F070094) {
            return 103;
        }

        if(arg3 == 0x7F070093) {
            return 101;
        }

        if(arg3 != 0x7F070096) {
            if(arg3 == 0x7F070097) {
            }
            else if(arg3 == 0x7F070099) {
                return 104;
            }
            else if(arg3 == v1) {
                return v0;
            }
            else {
                return 108;
            }
        }

        return 102;
    }

    public Result getClassificationResult() {
        return this.mClassificationResult;
    }

    @CalledByNative private Context getContext() {
        return this.mContext;
    }

    public TextClassifier getCustomTextClassifier() {
        SelectionClient v0 = this.getSelectionClient();
        TextClassifier v0_1 = v0 == null ? null : v0.getCustomTextClassifier();
        return v0_1;
    }

    private long getDefaultHideDuration() {
        if(this.supportsFloatingActionMode()) {
            return ViewConfiguration.getDefaultActionModeHideDuration();
        }

        return 2000;
    }

    private float getDeviceScaleFactor() {
        return this.mWebContents.getRenderCoordinates().getDeviceScaleFactor();
    }

    private GestureListenerManagerImpl getGestureListenerManager() {
        return GestureListenerManagerImpl.fromWebContents(this.mWebContents);
    }

    private PopupController getPopupController() {
        if(this.mPopupController == null) {
            this.mPopupController = PopupController.fromWebContents(this.mWebContents);
        }

        return this.mPopupController;
    }

    public boolean getPreserveSelectionOnNextLossOfFocus() {
        return this.mPreserveSelectionOnNextLossOfFocus;
    }

    public ResultCallback getResultCallback() {
        return this.mResultCallback;
    }

    public String getSelectedText() {
        return this.mLastSelectedText;
    }

    public SelectionClient getSelectionClient() {
        return this.mSelectionClient;
    }

    private Rect getSelectionRectRelativeToContainingView() {
        float v0 = this.getDeviceScaleFactor();
        Rect v1 = new Rect(((int)((((float)this.mSelectionRect.left)) * v0)), ((int)((((float)this.mSelectionRect.top)) * v0)), ((int)((((float)this.mSelectionRect.right)) * v0)), ((int)((((float)this.mSelectionRect.bottom)) * v0)));
        v1.offset(0, ((int)this.mWebContents.getRenderCoordinates().getContentOffsetYPix()));
        return v1;
    }

    private int getStatusBarHeight() {
        int v0 = this.mContext.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if(v0 > 0) {
            return this.mContext.getResources().getDimensionPixelSize(v0);
        }

        return 25;
    }

    public TextClassifier getTextClassifier() {
        SelectionClient v0 = this.getSelectionClient();
        TextClassifier v0_1 = v0 == null ? null : v0.getTextClassifier();
        return v0_1;
    }

    public String getWeChatPickedWord() {
        return this.mLastWeChatPickedWord;
    }

    public boolean hasSelection() {
        return this.mHasSelection;
    }

    private boolean hasStyleSpan(Spanned arg9) {
        Class[] v0 = new Class[]{CharacterStyle.class, ParagraphStyle.class, UpdateAppearance.class};
        int v1 = v0.length;
        int v4;
        for(v4 = 0; v4 < v1; ++v4) {
            if(arg9.nextSpanTransition(-1, arg9.length(), v0[v4]) < arg9.length()) {
                return 1;
            }
        }

        return 0;
    }

    public void hide() {
        this.destroyPastePopup();
    }

    private void hideActionMode(boolean arg3) {
        if(!this.isFloatingActionMode()) {
            return;
        }

        if(this.mHidden == arg3) {
            return;
        }

        this.mHidden = arg3;
        if(this.mHidden) {
            this.mRepeatingHideRunnable.run();
        }
        else {
            this.mView.removeCallbacks(this.mRepeatingHideRunnable);
            this.hideActionModeTemporarily(300);
        }
    }

    private void hideActionModeTemporarily(long arg3) {
        if(Build$VERSION.SDK_INT >= 23 && (this.isActionModeValid())) {
            this.mActionMode.hide(arg3);
        }
    }

    private void hideFloatPopWinCustom(boolean arg4) {
        this.mHidden = arg4;
        PopupWindow v0 = null;
        if(arg4) {
            Log.d("SelectionPopupCtlr", "hideFloatPopWinCustom");
            if(this.mFloatPopWinCustom != null) {
                this.mFloatPopWinCustom.dismiss();
                this.mFloatPopWinCustom = v0;
            }
        }
        else {
            Log.d("SelectionPopupCtlr", "delay showPopWinCustom");
            if(this.mFloatPopWinCustom != null) {
                this.mFloatPopWinCustom.dismiss();
                this.mFloatPopWinCustom = v0;
            }

            if(this.mPopTimer != null) {
                this.mPopTimer.cancel();
                this.mPopTimer = ((Timer)v0);
                this.mPopTimerTask = ((TimerTask)v0);
            }

            this.mPopTimer = new Timer();
            this.mPopTimerTask = new TimerTask() {
                public void run() {
                    Log.d("SelectionPopupCtlr", "delay showPopWinCustom  timer run !!");
                    if(SelectionPopupControllerImpl.this.mView != null) {
                        SelectionPopupControllerImpl.this.mView.removeCallbacks(SelectionPopupControllerImpl.this.mRepeatingDelayShowPopWinCustom);
                        SelectionPopupControllerImpl.this.mView.postDelayed(SelectionPopupControllerImpl.this.mRepeatingDelayShowPopWinCustom, 10);
                    }
                }
            };
            this.mPopTimer.schedule(this.mPopTimerTask, 300);
        }
    }

    @CalledByNative public void hidePopupsAndPreserveSelection() {
        this.destroyActionModeAndKeepSelection();
        this.getPopupController().hideAllPopups();
    }

    private void init(Context arg1, WindowAndroid arg2, View arg3, boolean arg4) {
        this.mContext = arg1;
        this.mWindowAndroid = arg2;
        this.mView = arg3;
        this.mAllowedMenuItems = 7;
        this.mRepeatingHideRunnable = new Runnable() {
            public void run() {
                long v0 = SelectionPopupControllerImpl.this.getDefaultHideDuration();
                SelectionPopupControllerImpl.this.mView.postDelayed(SelectionPopupControllerImpl.this.mRepeatingHideRunnable, v0 - 1);
                SelectionPopupControllerImpl.this.hideActionModeTemporarily(v0);
            }
        };
        this.mRepeatingDelayShowPopWinCustom = new Runnable() {
            public void run() {
                Log.d("SelectionPopupCtlr", "showPopWinCustom !!");
                SelectionPopupControllerImpl.this.showPopWinCustomOrClearOnFailure();
            }
        };
        this.mResultCallback = new SmartSelectionCallback(this, null);
        this.mLastSelectedText = "";
        this.initHandleObserver();
        this.mAdditionalMenuItemProvider = ContentClassFactory.get().createAddtionalMenuItemProvider();
        if(arg4) {
            this.mNativeSelectionPopupController = this.nativeInit(this.mWebContents);
        }

        this.getPopupController().registerPopup(((HideablePopup)this));
        this.mInitialized = true;
    }

    private void initHandleObserver() {
        this.mHandleObserver = ContentClassFactory.get().createHandleObserver(new SelectionPopupControllerImpl$$Lambda$0(this));
    }

    public static void initializeMenu(Context arg0, ActionMode arg1, Menu arg2) {
        new MenuInflater(arg0).inflate(0x7F0A0000, arg2);
    }

    private void initializeTextProcessingMenu(Menu arg2) {
        if(Build$VERSION.SDK_INT >= 23) {
            if(!this.isSelectActionModeAllowed(4)) {
            }
            else {
                return;
            }
        }
    }

    private boolean initialized() {
        return this.mInitialized;
    }

    public void invalidateContentRect() {
        if((this.supportsFloatingActionMode()) && (this.isActionModeValid())) {
            this.mActionMode.invalidateContentRect();
        }

        this.hideFloatPopWinCustom(false);
    }

    private boolean isActionModeSupported() {
        boolean v0 = this.mCallback != SelectionPopupControllerImpl.EMPTY_CALLBACK ? true : false;
        return v0;
    }

    public boolean isActionModeValid() {
        boolean v0 = this.mActionMode != null ? true : false;
        return v0;
    }

    private boolean isCustomPastePopupShowing() {
        boolean v0 = !this.mIsCustomPastePopupMenu || this.mFloatPopWinCustom == null || (this.mHidden) ? false : true;
        return v0;
    }

    private boolean isFloatingActionMode() {
        boolean v1 = true;
        if(!this.supportsFloatingActionMode() || !this.isActionModeValid() || this.mActionMode.getType() != 1) {
            v1 = false;
        }
        else {
        }

        return v1;
    }

    public boolean isFocusedNodeEditable() {
        return this.mEditable;
    }

    private boolean isIncognito() {
        return this.mWebContents.isIncognito();
    }

    @VisibleForTesting public boolean isInsertionForTesting() {
        return this.mIsInsertionForTesting;
    }

    @VisibleForTesting private boolean isPastePopupShowing() {
        boolean v0 = this.mPastePopupMenu != null ? true : false;
        return v0;
    }

    private boolean isSearchable() {
        return this.mSelectionClient.isSearchable();
    }

    public boolean isSelectActionBarShowing() {
        return this.isActionModeValid();
    }

    private boolean isSelectActionModeAllowed(int arg4) {
        boolean v1 = false;
        boolean v0 = (this.mAllowedMenuItems & arg4) != 0 ? true : false;
        if(arg4 == 1) {
            if((v0) && (this.isShareAvailable())) {
                v1 = true;
            }

            return v1;
        }

        return v0;
    }

    @VisibleForTesting public boolean isSelectionPassword() {
        return this.mIsPasswordType;
    }

    private boolean isShareAvailable() {
        Intent v0 = new Intent("android.intent.action.SEND");
        v0.setType("text/plain");
        boolean v0_1 = this.mContext.getPackageManager().queryIntentActivities(v0, 0x10000).size() > 0 ? true : false;
        return v0_1;
    }

    final View lambda$initHandleObserver$0$SelectionPopupControllerImpl() {
        if(SelectionPopupControllerImpl.sShouldGetReadbackViewFromWindowAndroid) {
            View v0 = this.mWindowAndroid == null ? null : this.mWindowAndroid.getReadbackView();
            return v0;
        }

        return this.mView;
    }

    private native long nativeInit(WebContents arg1) {
    }

    private native void nativeSetTextHandlesTemporarilyHidden(long arg1, boolean arg2) {
    }

    public boolean onActionItemClicked(ActionMode arg9, MenuItem arg10) {
        if(!this.isActionModeValid()) {
            return 1;
        }

        int v0 = arg10.getItemId();
        int v2 = arg10.getGroupId();
        if((this.hasSelection()) && this.mSelectionMetricsLogger != null) {
            this.mSelectionMetricsLogger.logSelectionAction(this.mLastSelectedText, this.mLastSelectionOffset, this.getActionType(v0, v2), this.mClassificationResult);
        }

        int v4 = 0x1020041;
        if(v2 == 0x7F070092 && v0 == v4) {
            this.doAssistAction();
            arg9.finish();
        }
        else if(v0 == 0x7F070098) {
            this.selectAll();
        }
        else if(v0 == 0x7F070094) {
            this.cut();
            arg9.finish();
        }
        else if(v0 == 0x7F070093) {
            this.copy();
            arg9.finish();
        }
        else if(v0 == 0x7F070096) {
            this.paste();
            arg9.finish();
        }
        else {
            if(Build$VERSION.SDK_INT >= 26 && v0 == 0x7F070097) {
                this.pasteAsPlainText();
                arg9.finish();
                return 1;
            }

            if(v0 == 0x7F070099) {
                this.share();
                arg9.finish();
                return 1;
            }

            if(v0 == 0x7F07009B) {
                this.search();
                arg9.finish();
                return 1;
            }

            if(v2 == 0x7F07009A) {
                this.processText(arg10.getIntent());
                return 1;
            }

            if(v2 != v4) {
                return 0;
            }

            if(this.mAdditionalMenuItemProvider == null) {
                return 1;
            }

            this.mAdditionalMenuItemProvider.performAction(arg10, this.mView);
            arg9.finish();
        }

        return 1;
    }

    public void onAttachedToWindow() {
        this.updateTextSelectionUI(true);
    }

    public void onBeforeSendKeyEvent(KeyEvent arg1) {
        ImeEventObserver$$CC.onBeforeSendKeyEvent(((ImeEventObserver)this), arg1);
    }

    private void onClickPop(View arg4) {
        Toast v0 = Toast.makeText(this.mContext, "", 0);
        switch(arg4.getId()) {
            case 2131165331: {
                this.copy();
                v0.setText(0x7F0C0050);
                v0.show();
                this.finishFloatPopWinCustom();
                break;
            }
            case 2131165332: {
                this.cut();
                v0.setText(0x7F0C0051);
                v0.show();
                this.finishFloatPopWinCustom();
                break;
            }
            case 2131165334: {
                this.paste();
                this.finishFloatPopWinCustom();
                break;
            }
            case 2131165336: {
                this.selectAll();
                break;
            }
            case 2131165337: {
                this.share();
                this.finishFloatPopWinCustom();
                break;
            }
            case 2131165339: {
                this.search();
                this.finishFloatPopWinCustom();
                break;
            }
            default: {
                break;
            }
        }
    }

    public void onCreateActionMode(ActionMode arg4, Menu arg5) {
        String v0;
        CharSequence v1 = null;
        if(DeviceFormFactor.isWindowOnTablet(this.mWindowAndroid)) {
            v0 = this.mContext.getString(0x7F0C002A);
        }
        else {
            CharSequence v0_1 = v1;
        }

        arg4.setTitle(((CharSequence)v0));
        arg4.setSubtitle(v1);
        this.createActionMenu(arg4, arg5);
    }

    public void onDestroyActionMode() {
        this.mActionMode = null;
        if(this.mUnselectAllOnDismiss) {
            this.mWebContents.dismissTextHandles();
            this.clearSelection();
        }
    }

    public void onDetachedFromWindow() {
        this.updateTextSelectionUI(false);
    }

    @VisibleForTesting @CalledByNative void onDragUpdate(float arg2, float arg3) {
        if(this.mHandleObserver != null) {
            float v0 = this.getDeviceScaleFactor();
            this.mHandleObserver.handleDragStartedOrMoved(arg2 * v0, arg3 * v0 + this.mWebContents.getRenderCoordinates().getContentOffsetYPix());
        }
    }

    public void onGetContentRect(ActionMode arg1, View arg2, Rect arg3) {
        arg3.set(this.getSelectionRectRelativeToContainingView());
    }

    public void onImeEvent() {
        ImeEventObserver$$CC.onImeEvent(((ImeEventObserver)this));
    }

    public void onNodeAttributeUpdated(boolean arg1, boolean arg2) {
        this.updateSelectionState(arg1, arg2);
    }

    public boolean onPrepareActionMode(ActionMode arg2, Menu arg3) {
        if(this.mAdditionalMenuItemProvider != null) {
            this.mAdditionalMenuItemProvider.clearMenuItemListeners();
        }

        arg3.removeGroup(0x7F070095);
        arg3.removeGroup(0x7F070092);
        arg3.removeGroup(0x7F07009A);
        arg3.removeGroup(0x1020041);
        this.createActionMenu(arg2, arg3);
        return 1;
    }

    public void onReceivedProcessTextResult(int arg2, Intent arg3) {
        if(this.mWebContents != null && arg2 == -1) {
            if(arg3 == null) {
            }
            else {
                if(this.hasSelection()) {
                    if(!this.isFocusedNodeEditable()) {
                    }
                    else {
                        CharSequence v2 = arg3.getCharSequenceExtra("android.intent.extra.PROCESS_TEXT");
                        if(v2 != null) {
                            this.mWebContents.replace(v2.toString());
                        }

                        return;
                    }
                }

                return;
            }
        }
    }

    @CalledByNative private void onSelectWordAroundCaretAck(boolean arg2, int arg3, int arg4) {
        if(this.mSelectionClient != null) {
            this.mSelectionClient.selectWordAroundCaretAck(arg2, arg3, arg4);
        }
    }

    @CalledByNative private void onSelectionChanged(String arg6) {
        this.mFirstSelectionTime = 0;
        if(arg6 != null && !arg6.isEmpty()) {
            this.mSelectionStatus = 2;
        }

        if(arg6.length() == 0 && (this.hasSelection()) && this.mSelectionMetricsLogger != null) {
            this.mSelectionMetricsLogger.logSelectionAction(this.mLastSelectedText, this.mLastSelectionOffset, 107, null);
        }

        this.mLastSelectedText = arg6;
        if(this.mSelectionClient != null) {
            this.mSelectionClient.onSelectionChanged(arg6);
        }
    }

    @VisibleForTesting @CalledByNative void onSelectionEvent(int arg4, int arg5, int arg6, int arg7, int arg8) {
        if(arg5 == arg7) {
            ++arg7;
        }

        if(arg6 == arg8) {
            ++arg8;
        }

        Log.d("SelectionPopupCtlr", " onSelectionEvent !!!!  eventType = " + arg4);
        switch(arg4) {
            case 0: {
                if(this.mHidden) {
                    goto label_99;
                }

                this.mSelectionRect.set(arg5, arg6, arg7, arg8);
                this.invalidateContentRect();
                break;
            }
            case 1: {
                this.mSelectionRect.set(arg5, arg6, arg7, arg8);
                this.invalidateContentRect();
                break;
            }
            case 2: {
                this.mLastSelectedText = "";
                this.mLastSelectionOffset = 0;
                this.mHasSelection = false;
                this.mUnselectAllOnDismiss = false;
                this.mSelectionRect.setEmpty();
                if(this.mSelectionClient != null) {
                    this.mSelectionClient.cancelAllRequests();
                }

                this.finishFloatPopWinCustom();
                break;
            }
            case 3: {
                this.hideFloatPopWinCustom(true);
                break;
            }
            case 4: {
                this.mWebContents.showContextMenuAtTouchHandle(arg5, arg8);
                if(this.mHandleObserver == null) {
                    goto label_99;
                }

                this.mHandleObserver.handleDragStopped();
                break;
            }
            case 5: {
                this.mSelectionRect.set(arg5, arg6, arg7, arg8);
                this.mIsInsertionForTesting = true;
                break;
            }
            case 6: {
                this.mSelectionRect.set(arg5, arg6, arg7, arg8);
                if(!this.getGestureListenerManager().isScrollInProgress() && (this.isCustomPastePopupShowing())) {
                    this.hideFloatPopWinCustom(false);
                    goto label_99;
                }

                this.destroyPastePopup();
                break;
            }
            case 7: {
                if(this.mWasPastePopupShowingOnInsertionDragStart) {
                    this.destroyPastePopup();
                }
                else {
                    this.mWebContents.showContextMenuAtTouchHandle(this.mSelectionRect.left, this.mSelectionRect.bottom);
                }

                this.mWasPastePopupShowingOnInsertionDragStart = false;
                break;
            }
            case 8: {
                this.destroyPastePopup();
                this.mIsInsertionForTesting = false;
                if(this.hasSelection()) {
                    goto label_99;
                }

                this.mSelectionRect.setEmpty();
                break;
            }
            case 9: {
                this.mWasPastePopupShowingOnInsertionDragStart = this.isCustomPastePopupShowing();
                this.destroyPastePopup();
                break;
            }
            case 10: {
                if(this.mWasPastePopupShowingOnInsertionDragStart) {
                    this.mWebContents.showContextMenuAtTouchHandle(this.mSelectionRect.left, this.mSelectionRect.bottom);
                }

                this.mWasPastePopupShowingOnInsertionDragStart = false;
                if(this.mHandleObserver == null) {
                    goto label_99;
                }

                this.mHandleObserver.handleDragStopped();
                break;
            }
            default: {
                break;
            }
        }

    label_99:
        if(this.mSelectionClient != null) {
            float v5 = this.getDeviceScaleFactor();
            this.mSelectionClient.onSelectionEvent(arg4, ((float)(((int)((((float)this.mSelectionRect.left)) * v5)))), ((float)(((int)((((float)this.mSelectionRect.bottom)) * v5)))));
        }
    }

    @CalledByNative private boolean onSelectionInfoChanged(String arg3, String arg4, String arg5) {
        if(this.mSelectionStatus == 1 || this.mSelectionStatus == 2) {
            this.mLastSelectedPrefixText = arg4;
            this.mLastSelectedSuffixText = arg5;
        }

        return 1;
    }

    private void onShowSos() {
        if(this.mLastWeChatPickedWord == null || (this.mLastWeChatPickedWord.equals(this.getSelectedText()))) {
            this.mSelectionClient.onShowSos(false);
        }
        else {
            this.mSelectionClient.onShowSos(true);
        }
    }

    @CalledByNative private void onWeChatSelectionInfoChanged(String arg19, String arg20, String arg21, int arg22, int arg23, int arg24, String arg25, String arg26, String arg27, int arg28, int arg29, int arg30, long arg31) {
        int v15_1;
        int v13_1;
        String v11;
        String v3 = arg19;
        String v4 = arg20;
        String v5 = arg21;
        int v6 = arg22;
        int v7 = arg23;
        int v8 = arg24;
        String v9 = arg25;
        String v10 = arg26;
        int v12 = arg28;
        try {
            String v1_1 = v4 + v3 + v5;
            String v2 = v1_1.substring(v6, v6 + 1);
            String v16 = v7 == -1 ? "" : v1_1.substring(v7, v7 + 1);
            String v15 = v16;
            v1_1 = v8 == -1 ? "" : v1_1.substring(v8, v8 + 1);
            Log.i("SelectionPopupCtlr", "smartPickWord royle onWeChatSelectionInfoChanged main\n mainPickWord:" + v3 + "\n mainPrefix:" + v4 + "\n mainSuffix:" + v5 + "\n mainIndex:" + v6 + "\n mainIndex_char:" + v2 + "\n mainBeforeIndex:" + v7 + "\n mainBeforeIndex_char:" + v15 + "\n mainAfterIndex:" + v8 + "\n mainAfterIndex_char:" + v1_1, new Object[0]);
            if(v12 != -1) {
                v1 = new StringBuilder();
                v1.append(v10);
                v1.append(v9);
                v11 = arg27;
                v1.append(v11);
                v1_1 = v1.toString();
                v2 = v1_1.substring(v12, v12 + 1);
                v13_1 = arg29;
                int v14 = -1;
                v15 = v13_1 == v14 ? "" : v1_1.substring(v13_1, v13_1 + 1);
                String v8_1 = v15;
                v15_1 = arg30;
                v1_1 = v15_1 == v14 ? "" : v1_1.substring(v15_1, v15_1 + 1);
                Log.i("SelectionPopupCtlr", "smartPickWord royle onWeChatSelectionInfoChanged sub\n subPickWord:" + v9 + "\n subPrefix:" + v10 + "\n subSuffix:" + v11 + "\n subIndex:" + v12 + "\n subIndex_char:" + v2 + "\n subBeforeIndex:" + v13_1 + "\n subBeforeIndex_char:" + v8_1 + "\n subAfterIndex:" + v15_1 + "\n subAfterIndex_char:" + v1_1, new Object[0]);
            }
            else {
                v11 = arg27;
                v13_1 = arg29;
                v15_1 = arg30;
            }

            this.mSelectionClient.onWeChatSelectionInfoChanged(this, v3, v4, v5, arg22, arg23, arg24, v9, v10, v11, v12, v13_1, v15_1, arg31);
        }
        catch(Exception v0) {
            Log.e("SelectionPopupCtlr", "onWeChatSelectionInfoChanged:" + v0.getMessage(), new Object[0]);
        }
    }

    public void onWindowAndroidChanged(WindowAndroid arg1) {
        this.mWindowAndroid = arg1;
        this.initHandleObserver();
    }

    public void onWindowFocusChanged(boolean arg2) {
        if((this.supportsFloatingActionMode()) && (this.isActionModeValid())) {
            this.mActionMode.onWindowFocusChanged(arg2);
        }
    }

    @VisibleForTesting public void paste() {
        this.mWebContents.paste();
    }

    @VisibleForTesting void pasteAsPlainText() {
        this.mWebContents.pasteAsPlainText();
    }

    private void processText(Intent arg4) {
        RecordUserAction.record("MobileActionMode.ProcessTextIntent");
        String v0 = SelectionPopupControllerImpl.sanitizeQuery(this.getSelectedText(), 1000);
        if(TextUtils.isEmpty(((CharSequence)v0))) {
            return;
        }

        arg4.putExtra("android.intent.extra.PROCESS_TEXT", v0);
        try {
            this.mWindowAndroid.showIntent(arg4, new IntentCallback() {
                public void onIntentCompleted(WindowAndroid arg1, int arg2, Intent arg3) {
                    SelectionPopupControllerImpl.this.onReceivedProcessTextResult(arg2, arg3);
                }
            }, null);
            return;
        }
        catch(ActivityNotFoundException ) {
            return;
        }
    }

    private void removeActionMenuItemsIfNecessary(Menu arg7) {
        int v1 = 0x7F070097;
        if(!this.isFocusedNodeEditable() || !this.canPaste()) {
            arg7.removeItem(0x7F070096);
            arg7.removeItem(v1);
        }

        if(!this.canPasteAsPlainText()) {
            arg7.removeItem(v1);
        }

        v1 = 0x7F07009B;
        int v2 = 0x7F070099;
        int v3 = 0x7F070093;
        int v4 = 0x7F070098;
        int v5 = 0x7F070094;
        if(!this.hasSelection()) {
            arg7.removeItem(v4);
            arg7.removeItem(v5);
            arg7.removeItem(v3);
            arg7.removeItem(v2);
            arg7.removeItem(v1);
            return;
        }

        if(!this.isFocusedNodeEditable()) {
            arg7.removeItem(v5);
        }

        if((this.isFocusedNodeEditable()) || !this.isSelectActionModeAllowed(1)) {
            arg7.removeItem(v2);
        }

        if((this.isFocusedNodeEditable()) || (this.isIncognito()) || !this.isSelectActionModeAllowed(2)) {
            arg7.removeItem(v1);
        }

        if(this.isSelectionPassword()) {
            arg7.removeItem(v3);
            arg7.removeItem(v5);
        }

        arg7.removeItem(v4);
    }

    private void removePopWinItem(View arg2) {
        if(arg2 != null) {
            arg2.getParent().removeView(arg2);
        }
    }

    private void removePopWinItemsIfNecessary() {
        if(!this.isFocusedNodeEditable() || !this.canPaste()) {
            this.removePopWinItem(this.mTextviewPaste);
        }

        if(!this.isFocusedNodeEditable() || !this.canSelectAll()) {
            this.removePopWinItem(this.mTextviewSelectAll);
        }

        if(!this.hasSelection()) {
            this.removePopWinItem(this.mTextviewCut);
            this.removePopWinItem(this.mTextviewCopy);
            this.removePopWinItem(this.mTextviewShare);
            this.removePopWinItem(this.mTextviewSeach);
            return;
        }

        if(!this.isFocusedNodeEditable()) {
            this.removePopWinItem(this.mTextviewCut);
        }

        this.removePopWinItem(this.mTextviewShare);
        if((this.isFocusedNodeEditable()) || (this.isIncognito()) || !this.isSearchable()) {
            this.removePopWinItem(this.mTextviewSeach);
        }
        else {
            this.onShowSos();
        }

        if(this.isSelectionPassword()) {
            this.removePopWinItem(this.mTextviewCut);
            this.removePopWinItem(this.mTextviewCopy);
        }
    }

    public void restoreSelectionPopupsIfNecessary() {
        if((this.hasSelection()) && !this.isActionModeValid()) {
            this.showActionModeOrClearOnFailure();
        }
    }

    public static String sanitizeQuery(String arg4, int arg5) {
        if(!TextUtils.isEmpty(((CharSequence)arg4))) {
            if(arg4.length() < arg5) {
            }
            else {
                Log.w("SelectionPopupCtlr", "Truncating oversized query (" + arg4.length() + ").", new Object[0]);
                return arg4.substring(0, arg5) + "…";
            }
        }

        return arg4;
    }

    @VisibleForTesting public void search() {
        RecordUserAction.record("MobileActionMode.WebSearch");
        String v0 = SelectionPopupControllerImpl.sanitizeQuery(this.getSelectedText(), 1000);
        if(TextUtils.isEmpty(((CharSequence)v0))) {
            return;
        }

        if(this.mSelectionClient != null) {
            this.mSelectionClient.onSearchWord(v0, this.mLastSelectedPrefixText, this.mLastSelectedSuffixText);
        }
    }

    @VisibleForTesting public void selectAll() {
        this.mWebContents.selectAll();
        this.mClassificationResult = null;
        if(this.isFocusedNodeEditable()) {
            RecordUserAction.record("MobileActionMode.SelectAllWasEditable");
        }
        else {
            RecordUserAction.record("MobileActionMode.SelectAllWasNonEditable");
        }
    }

    public void setActionModeCallback(ActionMode$Callback arg1) {
        this.mCallback = arg1;
    }

    public void setAllowedMenuItems(int arg1) {
        this.mAllowedMenuItems = arg1;
    }

    public void setContainerView(View arg2) {
        if(this.isActionModeValid()) {
            this.finishActionMode();
        }

        this.finishFloatPopWinCustom();
        this.mUnselectAllOnDismiss = true;
        this.destroyPastePopup();
        this.mView = arg2;
        this.initHandleObserver();
    }

    public void setNonSelectionActionModeCallback(ActionMode$Callback arg1) {
        this.mNonSelectionCallback = arg1;
    }

    @TargetApi(value=26) public static void setPasteAsPlainTextMenuItemTitle(Menu arg1) {
        MenuItem v1 = arg1.findItem(0x7F070097);
        if(v1 == null) {
            return;
        }

        v1.setTitle(0x1040019);
    }

    private void setPopupControllerForTesting(PopupController arg1) {
        this.mPopupController = arg1;
    }

    public void setPreserveSelectionOnNextLossOfFocus(boolean arg1) {
        this.mPreserveSelectionOnNextLossOfFocus = arg1;
    }

    public void setScrollInProgress(boolean arg2) {
        this.hideActionMode(arg2);
        if(this.hasSelection()) {
            this.hideFloatPopWinCustom(arg2);
        }
    }

    public void setSelectionClient(@Nullable SelectionClient arg1) {
        this.mSelectionClient = arg1;
        if(this.mSelectionClient != null) {
            this.mSelectionMetricsLogger = this.mSelectionClient.getSelectionMetricsLogger();
        }

        this.mClassificationResult = null;
    }

    @VisibleForTesting void setSelectionInsertionHandleObserver(@Nullable SelectionInsertionHandleObserver arg1) {
        this.mHandleObserver = arg1;
    }

    public static void setShouldGetReadbackViewFromWindowAndroid() {
        SelectionPopupControllerImpl.sShouldGetReadbackViewFromWindowAndroid = true;
    }

    public void setTextClassifier(TextClassifier arg2) {
        SelectionClient v0 = this.getSelectionClient();
        if(v0 != null) {
            v0.setTextClassifier(arg2);
        }
    }

    private void setTextHandlesTemporarilyHidden(boolean arg6) {
        if(this.mNativeSelectionPopupController == 0) {
            return;
        }

        this.nativeSetTextHandlesTemporarilyHidden(this.mNativeSelectionPopupController, arg6);
    }

    @VisibleForTesting public void share() {
        RecordUserAction.record("MobileActionMode.Share");
        String v0 = SelectionPopupControllerImpl.sanitizeQuery(this.getSelectedText(), 100000);
        if(TextUtils.isEmpty(((CharSequence)v0))) {
            return;
        }

        Intent v1 = new Intent("android.intent.action.SEND");
        v1.setType("text/plain");
        v1.putExtra("android.intent.extra.TEXT", v0);
        try {
            Intent v0_1 = Intent.createChooser(v1, this.mContext.getString(0x7F0C0029));
            v0_1.setFlags(0x10000000);
            this.mContext.startActivity(v0_1);
            return;
        }
        catch(ActivityNotFoundException ) {
            return;
        }
    }

    public void showActionModeOrClearOnFailure() {
        this.hideFloatPopWinCustom(false);
    }

    private void showPastePopup() {
        try {
            this.mPastePopupMenu.show(this.getSelectionRectRelativeToContainingView());
            return;
        }
        catch(WindowManager$BadTokenException ) {
            return;
        }
    }

    private void showPopWinCustomOrClearOnFailure() {
        int v3;
        int v2_1;
        PopupWindow v1 = null;
        if(this.mFloatPopWinCustom != null) {
            this.mFloatPopWinCustom.dismiss();
            this.mFloatPopWinCustom = v1;
        }

        this.mIsCustomPastePopupMenu = false;
        if(!this.hasSelection()) {
            this.mIsCustomPastePopupMenu = true;
            if(((this.canPaste()) || (this.canSelectAll())) && (this.isFocusedNodeEditable())) {
                goto label_20;
            }

            return;
        }

    label_20:
        Rect v2 = this.getSelectionRectRelativeToContainingView();
        if(v2.bottom < 0) {
            return;
        }

        Log.d("SelectionPopupCtlr", "showPopWinCustomOrClearOnFailure mIsCustomPastePopupMenu = " + this.mIsCustomPastePopupMenu);
        View v1_1 = LayoutInflater.from(this.mContext).inflate(0x7F090023, ((ViewGroup)v1));
        v1_1.measure(View$MeasureSpec.makeMeasureSpec(0, 0), View$MeasureSpec.makeMeasureSpec(0, 0));
        this.mFloatPopWinCustom = new PopupWindow(v1_1, -2, -2);
        this.mFloatPopWinCustom.setAnimationStyle(0x7F0D00AA);
        this.mFloatPopWinCustom.setClippingEnabled(false);
        this.mFloatPopWinCustom.setBackgroundDrawable(this.mContext.getResources().getDrawable(0x7F060072));
        this.mTextviewCut = v1_1.findViewById(0x7F070094);
        this.mTextviewCut.setOnClickListener(this.mPopListener);
        this.mTextviewCopy = v1_1.findViewById(0x7F070093);
        this.mTextviewCopy.setOnClickListener(this.mPopListener);
        this.mTextviewPaste = v1_1.findViewById(0x7F070096);
        this.mTextviewPaste.setOnClickListener(this.mPopListener);
        this.mTextviewShare = v1_1.findViewById(0x7F070099);
        this.mTextviewShare.setOnClickListener(this.mPopListener);
        this.mTextviewSelectAll = v1_1.findViewById(0x7F070098);
        this.mTextviewSelectAll.setOnClickListener(this.mPopListener);
        this.mTextviewSeach = v1_1.findViewById(0x7F07009B);
        this.mTextviewSeach.setOnClickListener(this.mPopListener);
        this.removePopWinItemsIfNecessary();
        v1_1.measure(View$MeasureSpec.makeMeasureSpec(0, 0), View$MeasureSpec.makeMeasureSpec(0, 0));
        int v1_2 = this.mContext.getResources().getDisplayMetrics().widthPixels;
        int v4 = this.mContext.getResources().getDisplayMetrics().heightPixels;
        int v5_1 = this.getStatusBarHeight();
        View v6 = this.mFloatPopWinCustom.getContentView();
        int v7 = v6.getMeasuredWidth();
        int v6_1 = v6.getMeasuredHeight();
        int v8 = 2;
        int[] v9 = new int[v8];
        this.mView.getLocationOnScreen(v9);
        Rect v10 = new Rect();
        v10.left = v2.left + v9[0];
        v10.right = v2.right + v9[0];
        v10.top = v2.top + v9[1];
        v10.bottom = v2.bottom + v9[1];
        if(v10.top > v5_1 + v6_1) {
            v2_1 = (v10.left + v10.right) / v8 - v7 / 2 - 88;
            v3 = v10.top - v6_1 - 0x80;
        }
        else {
            v4 -= v6_1;
            if(v10.bottom < v4) {
                v2_1 = (v10.left + v10.right) / v8 - v7 / 2 - 88;
                v3 = v10.bottom - 68;
            }
            else {
                v2_1 = (v1_2 - v7) / v8 - 88;
                v3 = v4 / v8 - 88;
            }
        }

        if(v2_1 < -38) {
            v2_1 = -38;
        }

        v1_2 = v1_2 - v7 - 0x8A;
        if(v2_1 > v1_2) {
        }
        else {
            v1_2 = v2_1;
        }

        this.mHidden = false;
        this.mFloatPopWinCustom.showAtLocation(this.mView, 0, v1_2, v3);
    }

    @VisibleForTesting @CalledByNative public void showSelectionMenu(int arg1, int arg2, int arg3, int arg4, int arg5, boolean arg6, boolean arg7, String arg8, int arg9, boolean arg10, boolean arg11, boolean arg12, int arg13) {
        this.mSelectionRect.set(arg1, arg2, arg3, arg4);
        this.mEditable = arg6;
        this.mLastSelectedText = arg8;
        this.mLastSelectionOffset = arg9;
        boolean v1 = arg8.length() != 0 ? true : false;
        this.mHasSelection = v1;
        this.mIsPasswordType = arg7;
        this.mCanSelectAllForPastePopup = arg10;
        this.mCanEditRichly = arg11;
        this.mUnselectAllOnDismiss = true;
        arg1 = ApiCompatibilityUtils.isDeviceProvisioned(this.mContext) ^ 1 | this.isIncognito();
        if(arg1 == 0 && this.mSelectionMetricsLogger != null && arg13 != 7) {
            switch(arg13) {
                case 9: {
                    goto label_39;
                }
                case 10: {
                    goto label_32;
                }
            }

            this.mSelectionMetricsLogger.logSelectionStarted(this.mLastSelectedText, this.mLastSelectionOffset, arg6);
            goto label_44;
        label_39:
            this.mSelectionMetricsLogger.logSelectionModified(this.mLastSelectedText, this.mLastSelectionOffset, this.mClassificationResult);
            goto label_44;
        label_32:
            this.mSelectionMetricsLogger.logSelectionAction(this.mLastSelectedText, this.mLastSelectionOffset, 201, null);
        }

    label_44:
        if(arg1 == 0 && arg13 == 9) {
            this.showActionModeOrClearOnFailure();
            return;
        }

        if(arg1 != 0 || this.mSelectionClient == null || !this.mSelectionClient.requestSelectionPopupUpdates(arg12)) {
            this.showActionModeOrClearOnFailure();
        }
    }

    @TargetApi(value=23) private ActionMode startFloatingActionMode() {
        return this.mView.startActionMode(new FloatingActionModeCallback(((ActionModeCallbackHelper)this), this.mCallback), 1);
    }

    public boolean supportsFloatingActionMode() {
        boolean v0 = Build$VERSION.SDK_INT >= 23 ? true : false;
        return v0;
    }

    private void updateAssistMenuItem(Menu arg5) {
        if(Build$VERSION.SDK_INT < 26) {
            return;
        }

        if(this.mClassificationResult != null && (this.mClassificationResult.hasNamedAction())) {
            arg5.add(0x7F070092, 0x1020041, 1, this.mClassificationResult.label).setIcon(this.mClassificationResult.icon);
        }
    }

    public void updateSelectionState(boolean arg2, boolean arg3) {
        if(!arg2) {
            this.destroyPastePopup();
        }

        if(arg2 != this.isFocusedNodeEditable() || arg3 != this.isSelectionPassword()) {
            this.mEditable = arg2;
            this.mIsPasswordType = arg3;
            if(this.isActionModeValid()) {
                this.mActionMode.invalidate();
            }
        }
    }

    public void updateTextSelectionUI(boolean arg2) {
        this.setTextHandlesTemporarilyHidden((((int)arg2)) ^ 1);
        if(arg2) {
            this.restoreSelectionPopupsIfNecessary();
        }
        else {
            this.destroyActionModeAndKeepSelection();
            this.getPopupController().hideAllPopups();
        }
    }
}

