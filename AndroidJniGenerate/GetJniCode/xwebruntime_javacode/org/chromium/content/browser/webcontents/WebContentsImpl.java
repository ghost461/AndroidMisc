package org.chromium.content.browser.webcontents;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.Parcelable$Creator;
import android.support.annotation.Nullable;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.chromium.base.Log;
import org.chromium.base.ThreadUtils;
import org.chromium.base.VisibleForTesting;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.content.browser.AppWebMessagePort;
import org.chromium.content.browser.MediaSessionImpl;
import org.chromium.content.browser.MemoryDumperAndroid;
import org.chromium.content.browser.RenderCoordinates;
import org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl;
import org.chromium.content.browser.framehost.RenderFrameHostDelegate;
import org.chromium.content.browser.framehost.RenderFrameHostImpl;
import org.chromium.content.browser.selection.SelectionPopupControllerImpl;
import org.chromium.content_public.browser.AccessibilitySnapshotCallback;
import org.chromium.content_public.browser.AccessibilitySnapshotNode;
import org.chromium.content_public.browser.ContentBitmapCallback;
import org.chromium.content_public.browser.ImageBitmapToFileFinishedCallback;
import org.chromium.content_public.browser.ImageDownloadCallback;
import org.chromium.content_public.browser.JavaScriptCallback;
import org.chromium.content_public.browser.MessagePort;
import org.chromium.content_public.browser.NavigationController;
import org.chromium.content_public.browser.RenderFrameHost;
import org.chromium.content_public.browser.WebContents$InternalsHolder;
import org.chromium.content_public.browser.WebContents$UserDataFactory;
import org.chromium.content_public.browser.WebContents;
import org.chromium.content_public.browser.WebContentsInternals;
import org.chromium.content_public.browser.WebContentsObserver;
import org.chromium.ui.OverscrollRefreshHandler;
import org.chromium.ui.base.EventForwarder;
import org.chromium.ui.base.WindowAndroid;

@JNINamespace(value="content") public class WebContentsImpl implements RenderFrameHostDelegate, WebContents {
    final class org.chromium.content.browser.webcontents.WebContentsImpl$1 implements Parcelable$Creator {
        org.chromium.content.browser.webcontents.WebContentsImpl$1() {
            super();
        }

        public Object createFromParcel(Parcel arg1) {
            return this.createFromParcel(arg1);
        }

        public WebContents createFromParcel(Parcel arg6) {
            Bundle v6 = arg6.readBundle();
            WebContents v0 = null;
            if(Long.compare(v6.getLong("version", -1), 0) != 0) {
                return v0;
            }

            if(WebContentsImpl.sParcelableUUID.compareTo(v6.getParcelable("processguard").getUuid()) != 0) {
                return v0;
            }

            return WebContentsImpl.nativeFromNativePtr(v6.getLong("webcontents"));
        }

        public Object[] newArray(int arg1) {
            return this.newArray(arg1);
        }

        public WebContents[] newArray(int arg1) {
            return new WebContents[arg1];
        }
    }

    class DefaultInternalsHolder implements InternalsHolder {
        private WebContentsInternals mInternals;

        DefaultInternalsHolder(org.chromium.content.browser.webcontents.WebContentsImpl$1 arg1) {
            this();
        }

        private DefaultInternalsHolder() {
            super();
        }

        public WebContentsInternals get() {
            return this.mInternals;
        }

        public void set(WebContentsInternals arg1) {
            this.mInternals = arg1;
        }
    }

    class SmartClipCallback {
        final Handler mHandler;
        Rect mRect;

        public SmartClipCallback(WebContentsImpl arg1, Handler arg2) {
            WebContentsImpl.this = arg1;
            super();
            this.mHandler = arg2;
        }

        public void onSmartClipDataExtracted(String arg4, String arg5) {
            Bundle v0 = new Bundle();
            v0.putString("url", WebContentsImpl.this.getVisibleUrl());
            v0.putString("title", WebContentsImpl.this.getTitle());
            v0.putString("text", arg4);
            v0.putString("html", arg5);
            v0.putParcelable("rect", this.mRect);
            Message v4 = Message.obtain(this.mHandler, 0);
            v4.setData(v0);
            v4.sendToTarget();
        }

        public void storeRequestRect(Rect arg1) {
            this.mRect = arg1;
        }
    }

    class WebContentsInternalsImpl implements WebContentsInternals {
        public HashMap userDataMap;

        WebContentsInternalsImpl(org.chromium.content.browser.webcontents.WebContentsImpl$1 arg1) {
            this();
        }

        private WebContentsInternalsImpl() {
            super();
        }
    }

    @SuppressLint(value={"ParcelClassLoader"}) public static final Parcelable$Creator CREATOR = null;
    private static final long PARCELABLE_VERSION_ID = 0;
    private static final String PARCEL_PROCESS_GUARD_KEY = "processguard";
    private static final String PARCEL_VERSION_KEY = "version";
    private static final String PARCEL_WEBCONTENTS_KEY = "webcontents";
    private static final String TAG = "cr_WebContentsImpl";
    int mBottomHeight;
    private EventForwarder mEventForwarder;
    private final List mFrames;
    int mHeight;
    private InternalsHolder mInternalsHolder;
    private MediaSessionImpl mMediaSession;
    private long mNativeWebContentsAndroid;
    private NavigationController mNavigationController;
    private WebContentsObserverProxy mObserverProxy;
    private RenderCoordinates mRenderCoordinates;
    private SmartClipCallback mSmartClipCallback;
    int mWidth;
    private static UUID sParcelableUUID;

    static {
        WebContentsImpl.sParcelableUUID = UUID.randomUUID();
        WebContentsImpl.CREATOR = new org.chromium.content.browser.webcontents.WebContentsImpl$1();
    }

    private WebContentsImpl(long arg2, NavigationController arg4) {
        super();
        this.mFrames = new ArrayList();
        this.mBottomHeight = 0;
        this.mNativeWebContentsAndroid = arg2;
        this.mNavigationController = arg4;
        WebContentsInternalsImpl v2 = new WebContentsInternalsImpl(null);
        v2.userDataMap = new HashMap();
        this.mRenderCoordinates = new RenderCoordinates();
        this.mRenderCoordinates.reset();
        this.mInternalsHolder = new DefaultInternalsHolder(null);
        this.mInternalsHolder.set(((WebContentsInternals)v2));
    }

    static UUID access$000() {
        return WebContentsImpl.sParcelableUUID;
    }

    static WebContents access$100(long arg0) {
        return WebContentsImpl.nativeFromNativePtr(arg0);
    }

    @CalledByNative private static void addAccessibilityNodeAsChild(AccessibilitySnapshotNode arg0, AccessibilitySnapshotNode arg1) {
        arg0.addChild(arg1);
    }

    public void addMessageToDevToolsConsole(int arg3, String arg4) {
        this.nativeAddMessageToDevToolsConsole(this.mNativeWebContentsAndroid, arg3, arg4);
    }

    public void addObserver(WebContentsObserver arg2) {
        if(this.mObserverProxy == null) {
            this.mObserverProxy = new WebContentsObserverProxy(this);
        }

        this.mObserverProxy.addObserver(arg2);
    }

    @CalledByNative private static void addToBitmapList(List arg0, Bitmap arg1) {
        arg0.add(arg1);
    }

    public void adjustSelectionByCharacterOffset(int arg7, int arg8, boolean arg9) {
        this.nativeAdjustSelectionByCharacterOffset(this.mNativeWebContentsAndroid, arg7, arg8, arg9);
    }

    @CalledByNative private void clearNativePtr() {
        this.mNativeWebContentsAndroid = 0;
        NavigationController v0 = null;
        this.mNavigationController = v0;
        if(this.mObserverProxy != null) {
            this.mObserverProxy.destroy();
            this.mObserverProxy = ((WebContentsObserverProxy)v0);
        }
    }

    public void collapseSelection() {
        if(this.isDestroyed()) {
            return;
        }

        this.nativeCollapseSelection(this.mNativeWebContentsAndroid);
    }

    public void copy() {
        this.nativeCopy(this.mNativeWebContentsAndroid);
    }

    @CalledByNative private static WebContentsImpl create(long arg1, NavigationController arg3) {
        return new WebContentsImpl(arg1, arg3);
    }

    @CalledByNative private static AccessibilitySnapshotNode createAccessibilitySnapshotNode(int arg9, int arg10, int arg11, int arg12, boolean arg13, String arg14, int arg15, int arg16, float arg17, boolean arg18, boolean arg19, boolean arg20, boolean arg21, String arg22) {
        AccessibilitySnapshotNode v8 = new AccessibilitySnapshotNode(arg14, arg22);
        float v3 = arg17;
        if((((double)v3)) >= 0) {
            v8.setStyle(arg15, arg16, v3, arg18, arg19, arg20, arg21);
        }

        v8.setLocationInfo(arg9, arg10, arg11, arg12, arg13);
        return v8;
    }

    @CalledByNative private static List createBitmapList() {
        return new ArrayList();
    }

    public AppWebMessagePort[] createMessageChannel() throws IllegalStateException {
        return AppWebMessagePort.createPair();
    }

    public MessagePort[] createMessageChannel() {
        return this.createMessageChannel();
    }

    @CalledByNative private static Rect createSize(int arg2, int arg3) {
        return new Rect(0, 0, arg2, arg3);
    }

    @CalledByNative private static void createSizeAndAddToList(List arg2, int arg3, int arg4) {
        arg2.add(new Rect(0, 0, arg3, arg4));
    }

    @CalledByNative private static List createSizeList() {
        return new ArrayList();
    }

    public void cut() {
        this.nativeCut(this.mNativeWebContentsAndroid);
    }

    public int describeContents() {
        return 0;
    }

    public void destroy() {
        if(!ThreadUtils.runningOnUiThread()) {
            throw new IllegalStateException("Attempting to destroy WebContents on non-UI thread");
        }

        if(this.mNativeWebContentsAndroid != 0) {
            WebContentsImpl.nativeDestroyWebContents(this.mNativeWebContentsAndroid);
        }
    }

    public void didReceivedWeChatSelectionInfo(String arg13, int arg14, int arg15, String arg16, String arg17, int arg18, long arg19) {
        this.nativeDidReceivedWeChatSelectionInfo(this.mNativeWebContentsAndroid, arg13, arg14, arg15, arg16, arg17, arg18, arg19);
    }

    public void dismissTextHandles() {
        this.nativeDismissTextHandles(this.mNativeWebContentsAndroid);
    }

    public int downloadImage(String arg9, boolean arg10, int arg11, boolean arg12, ImageDownloadCallback arg13) {
        return this.nativeDownloadImage(this.mNativeWebContentsAndroid, arg9, arg10, arg11, arg12, arg13);
    }

    public void evaluateJavaScript(String arg3, JavaScriptCallback arg4) {
        if(!this.isDestroyed()) {
            if(arg3 == null) {
            }
            else {
                this.nativeEvaluateJavaScript(this.mNativeWebContentsAndroid, arg3, arg4);
                return;
            }
        }
    }

    @VisibleForTesting public void evaluateJavaScriptForTests(String arg3, JavaScriptCallback arg4) {
        if(arg3 == null) {
            return;
        }

        this.nativeEvaluateJavaScriptForTests(this.mNativeWebContentsAndroid, arg3, arg4);
    }

    public void exitFullscreen() {
        this.nativeExitFullscreen(this.mNativeWebContentsAndroid);
    }

    public boolean focusLocationBarByDefault() {
        return this.nativeFocusLocationBarByDefault(this.mNativeWebContentsAndroid);
    }

    public int getBackgroundColor() {
        return this.nativeGetBackgroundColor(this.mNativeWebContentsAndroid);
    }

    public void getContentBitmapAsync(int arg7, int arg8, ContentBitmapCallback arg9) {
        this.nativeGetContentBitmap(this.mNativeWebContentsAndroid, arg7, arg8, arg9);
    }

    public String getEncoding() {
        return this.nativeGetEncoding(this.mNativeWebContentsAndroid);
    }

    public EventForwarder getEventForwarder() {
        if(this.mEventForwarder == null) {
            this.mEventForwarder = this.nativeGetOrCreateEventForwarder(this.mNativeWebContentsAndroid);
        }

        return this.mEventForwarder;
    }

    @Nullable public Rect getFullscreenVideoSize() {
        return this.nativeGetFullscreenVideoSize(this.mNativeWebContentsAndroid);
    }

    public int getHeight() {
        return this.nativeGetHeight(this.mNativeWebContentsAndroid);
    }

    public void getImageBitmapToFile(String arg9, String arg10, String arg11, String arg12, ImageBitmapToFileFinishedCallback arg13) {
        this.nativeGetImageBitmapToFile(this.mNativeWebContentsAndroid, arg9, arg10, arg11, arg12, arg13);
    }

    public String getLastCommittedUrl() {
        return this.nativeGetLastCommittedURL(this.mNativeWebContentsAndroid);
    }

    public RenderFrameHost getMainFrame() {
        return this.nativeGetMainFrame(this.mNativeWebContentsAndroid);
    }

    @CalledByNative private long getNativePointer() {
        return this.mNativeWebContentsAndroid;
    }

    public NavigationController getNavigationController() {
        return this.mNavigationController;
    }

    public Object getOrSetUserData(Class arg4, UserDataFactory arg5) {
        Map v0 = this.getUserDataMap();
        Object v1 = null;
        if(v0 == null) {
            Log.e("cr_WebContentsImpl", "UserDataMap can\'t be found", new Object[0]);
            return v1;
        }

        Object v2 = v0.get(arg4);
        if(v2 == null && arg5 != null) {
            v0.put(arg4, new WebContentsUserData(arg5.create(((WebContents)this))));
            v2 = v0.get(arg4);
        }

        if(v2 != null) {
            v1 = ((WebContentsUserData)v2).getObject();
        }

        return v1;
    }

    public String getRefererUrl() {
        if(this.isDestroyed()) {
            return null;
        }

        return this.nativeGetRefererURL(this.mNativeWebContentsAndroid);
    }

    public RenderCoordinates getRenderCoordinates() {
        return this.mRenderCoordinates;
    }

    private SelectionPopupControllerImpl getSelectionPopupController() {
        return SelectionPopupControllerImpl.fromWebContents(((WebContents)this));
    }

    public int getThemeColor() {
        return this.nativeGetThemeColor(this.mNativeWebContentsAndroid);
    }

    public String getTitle() {
        return this.nativeGetTitle(this.mNativeWebContentsAndroid);
    }

    public WindowAndroid getTopLevelNativeWindow() {
        return this.nativeGetTopLevelNativeWindow(this.mNativeWebContentsAndroid);
    }

    public void getTranslateSampleString(int arg3) {
        this.nativeGetTranslateSampleString(this.mNativeWebContentsAndroid, arg3);
    }

    public String getUrl() {
        if(this.isDestroyed()) {
            return null;
        }

        return this.nativeGetURL(this.mNativeWebContentsAndroid);
    }

    private Map getUserDataMap() {
        WebContentsInternals v0 = this.mInternalsHolder.get();
        if(v0 == null) {
            return null;
        }

        return ((WebContentsInternalsImpl)v0).userDataMap;
    }

    public String getVisibleUrl() {
        return this.nativeGetVisibleURL(this.mNativeWebContentsAndroid);
    }

    public int getWidth() {
        return this.nativeGetWidth(this.mNativeWebContentsAndroid);
    }

    public boolean hasAccessedInitialDocument() {
        return this.nativeHasAccessedInitialDocument(this.mNativeWebContentsAndroid);
    }

    public boolean hasActiveEffectivelyFullscreenVideo() {
        return this.nativeHasActiveEffectivelyFullscreenVideo(this.mNativeWebContentsAndroid);
    }

    @VisibleForTesting public static void invalidateSerializedWebContentsForTesting() {
        WebContentsImpl.sParcelableUUID = UUID.randomUUID();
    }

    public boolean isDestroyed() {
        boolean v0 = this.mNativeWebContentsAndroid == 0 ? true : false;
        return v0;
    }

    public boolean isIncognito() {
        return this.nativeIsIncognito(this.mNativeWebContentsAndroid);
    }

    public boolean isLoading() {
        return this.nativeIsLoading(this.mNativeWebContentsAndroid);
    }

    public boolean isLoadingToDifferentDocument() {
        return this.nativeIsLoadingToDifferentDocument(this.mNativeWebContentsAndroid);
    }

    public boolean isPictureInPictureAllowedForFullscreenVideo() {
        return this.nativeIsPictureInPictureAllowedForFullscreenVideo(this.mNativeWebContentsAndroid);
    }

    public boolean isReady() {
        return this.nativeIsRenderWidgetHostViewReady(this.mNativeWebContentsAndroid);
    }

    public boolean isShowingInterstitialPage() {
        return this.nativeIsShowingInterstitialPage(this.mNativeWebContentsAndroid);
    }

    static final void lambda$savePage$0$WebContentsImpl(File arg1, String arg2) {
        if(arg1.exists()) {
            arg1.delete();
        }

        try {
            arg1.getParentFile().mkdirs();
            FileWriter v0 = new FileWriter(arg1);
            v0.write(arg2);
            v0.flush();
            v0.close();
        }
        catch(IOException v1) {
            ThrowableExtension.printStackTrace(((Throwable)v1));
        }
    }

    private native void nativeAddMessageToDevToolsConsole(long arg1, int arg2, String arg3) {
    }

    private native void nativeAdjustSelectionByCharacterOffset(long arg1, int arg2, int arg3, boolean arg4) {
    }

    private native void nativeCollapseSelection(long arg1) {
    }

    private native void nativeCopy(long arg1) {
    }

    private native void nativeCut(long arg1) {
    }

    private static native void nativeDestroyWebContents(long arg0) {
    }

    private native void nativeDidReceivedWeChatSelectionInfo(long arg1, String arg2, int arg3, int arg4, String arg5, String arg6, int arg7, long arg8) {
    }

    private native void nativeDismissTextHandles(long arg1) {
    }

    private native int nativeDownloadImage(long arg1, String arg2, boolean arg3, int arg4, boolean arg5, ImageDownloadCallback arg6) {
    }

    private native void nativeEvaluateJavaScript(long arg1, String arg2, JavaScriptCallback arg3) {
    }

    private native void nativeEvaluateJavaScriptForTests(long arg1, String arg2, JavaScriptCallback arg3) {
    }

    private native void nativeExitFullscreen(long arg1) {
    }

    private native boolean nativeFocusLocationBarByDefault(long arg1) {
    }

    private static native WebContents nativeFromNativePtr(long arg0) {
    }

    private native int nativeGetBackgroundColor(long arg1) {
    }

    private native void nativeGetContentBitmap(long arg1, int arg2, int arg3, ContentBitmapCallback arg4) {
    }

    private native String nativeGetEncoding(long arg1) {
    }

    private native Rect nativeGetFullscreenVideoSize(long arg1) {
    }

    private native int nativeGetHeight(long arg1) {
    }

    private native void nativeGetImageBitmapToFile(long arg1, String arg2, String arg3, String arg4, String arg5, ImageBitmapToFileFinishedCallback arg6) {
    }

    private native String nativeGetLastCommittedURL(long arg1) {
    }

    private native RenderFrameHost nativeGetMainFrame(long arg1) {
    }

    private native EventForwarder nativeGetOrCreateEventForwarder(long arg1) {
    }

    private native String nativeGetRefererURL(long arg1) {
    }

    private native void nativeGetSelectionInfo(long arg1, int arg2, int arg3) {
    }

    private native int nativeGetThemeColor(long arg1) {
    }

    private native String nativeGetTitle(long arg1) {
    }

    private native WindowAndroid nativeGetTopLevelNativeWindow(long arg1) {
    }

    private native void nativeGetTranslateSampleString(long arg1, int arg2) {
    }

    private native String nativeGetURL(long arg1) {
    }

    private native String nativeGetVisibleURL(long arg1) {
    }

    private native int nativeGetWidth(long arg1) {
    }

    private native boolean nativeHasAccessedInitialDocument(long arg1) {
    }

    private native boolean nativeHasActiveEffectivelyFullscreenVideo(long arg1) {
    }

    private native boolean nativeIsIncognito(long arg1) {
    }

    private native boolean nativeIsLoading(long arg1) {
    }

    private native boolean nativeIsLoadingToDifferentDocument(long arg1) {
    }

    private native boolean nativeIsPictureInPictureAllowedForFullscreenVideo(long arg1) {
    }

    private native boolean nativeIsRenderWidgetHostViewReady(long arg1) {
    }

    private native boolean nativeIsShowingInterstitialPage(long arg1) {
    }

    private native void nativeOnHide(long arg1) {
    }

    private native void nativeOnShow(long arg1) {
    }

    private native void nativePaste(long arg1) {
    }

    private native void nativePasteAsPlainText(long arg1) {
    }

    private native void nativePostMessageToFrame(long arg1, String arg2, String arg3, String arg4, String arg5, MessagePort[] arg6) {
    }

    private native void nativeReloadLoFiImages(long arg1) {
    }

    private native void nativeReplace(long arg1, String arg2) {
    }

    private native void nativeReplaceTranslatedString(long arg1, Object arg2) {
    }

    private native void nativeRequestAccessibilitySnapshot(long arg1, AccessibilitySnapshotCallback arg2) {
    }

    private native void nativeRequestSmartClipExtract(long arg1, SmartClipCallback arg2, int arg3, int arg4, int arg5, int arg6) {
    }

    private native void nativeResumeLoadingCreatedWebContents(long arg1) {
    }

    private native boolean nativeSavePage(long arg1, String arg2, String arg3, int arg4) {
    }

    private native void nativeScrollFocusedEditableNodeIntoView(long arg1) {
    }

    private native void nativeSelectAll(long arg1) {
    }

    private native void nativeSelectWordAroundCaret(long arg1) {
    }

    private native void nativeSetAudioMuted(long arg1, boolean arg2) {
    }

    private native void nativeSetHasPersistentVideo(long arg1, boolean arg2) {
    }

    private native void nativeSetImportance(long arg1, int arg2) {
    }

    private native void nativeSetOverscrollRefreshHandler(long arg1, OverscrollRefreshHandler arg2) {
    }

    private native void nativeSetSize(long arg1, int arg2, int arg3) {
    }

    private native void nativeSetTranslateMode(long arg1, boolean arg2) {
    }

    private native void nativeShowContextMenuAtTouchHandle(long arg1, int arg2, int arg3) {
    }

    private native void nativeShowInterstitialPage(long arg1, String arg2, long arg3) {
    }

    private native void nativeStop(long arg1) {
    }

    private native void nativeSuspendAllMediaPlayers(long arg1) {
    }

    @CalledByNative private static void onAccessibilitySnapshot(AccessibilitySnapshotNode arg0, AccessibilitySnapshotCallback arg1) {
        arg1.onAccessibilitySnapshot(arg0);
    }

    @CalledByNative private void onDownloadImageFinished(ImageDownloadCallback arg1, int arg2, int arg3, String arg4, List arg5, List arg6) {
        arg1.onFinishDownloadImage(arg2, arg3, arg4, arg5, arg6);
    }

    @CalledByNative private static void onEvaluateJavaScriptResult(String arg0, JavaScriptCallback arg1) {
        arg1.handleJavaScriptResult(arg0);
    }

    @CalledByNative private void onGetContentBitmapFinished(ContentBitmapCallback arg1, Bitmap arg2) {
        arg1.onFinishGetBitmap(arg2);
    }

    @CalledByNative private void onGetImageBitmapToFileFinished(ImageBitmapToFileFinishedCallback arg1, int arg2, String arg3, String arg4, int arg5, int arg6, String arg7) {
        arg1.onFinishImageBitmapToFile(arg2, arg3, arg4, arg5, arg6, arg7);
    }

    public void onHide() {
        SelectionPopupControllerImpl v0 = this.getSelectionPopupController();
        if(v0 != null) {
            v0.hidePopupsAndPreserveSelection();
        }

        this.nativeOnHide(this.mNativeWebContentsAndroid);
    }

    public void onShow() {
        WebContentsAccessibilityImpl v0 = WebContentsAccessibilityImpl.fromWebContents(((WebContents)this));
        if(v0 != null) {
            v0.refreshState();
        }

        SelectionPopupControllerImpl v0_1 = this.getSelectionPopupController();
        if(v0_1 != null) {
            v0_1.restoreSelectionPopupsIfNecessary();
        }

        this.nativeOnShow(this.mNativeWebContentsAndroid);
    }

    @CalledByNative private static void onSmartClipDataExtracted(String arg0, String arg1, SmartClipCallback arg2) {
        arg2.onSmartClipDataExtracted(arg0, arg1);
    }

    public void paste() {
        this.nativePaste(this.mNativeWebContentsAndroid);
    }

    public void pasteAsPlainText() {
        this.nativePasteAsPlainText(this.mNativeWebContentsAndroid);
    }

    public void postMessageToFrame(String arg9, String arg10, String arg11, String arg12, MessagePort[] arg13) {
        if(arg13 != null) {
            int v0 = arg13.length;
            int v1 = 0;
            while(true) {
                if(v1 < v0) {
                    MessagePort v2 = arg13[v1];
                    if(!v2.isClosed()) {
                        if(v2.isTransferred()) {
                        }
                        else if(v2.isStarted()) {
                            throw new IllegalStateException("Port is already started");
                        }
                        else {
                            ++v1;
                            continue;
                        }
                    }

                    break;
                }

                goto label_22;
            }

            throw new IllegalStateException("Port is already closed or transferred");
        }

    label_22:
        if(arg12.equals("*")) {
            arg12 = "";
        }

        this.nativePostMessageToFrame(this.mNativeWebContentsAndroid, arg9, arg10, arg11, arg12, arg13);
    }

    public void reloadLoFiImages() {
        this.nativeReloadLoFiImages(this.mNativeWebContentsAndroid);
    }

    public void removeObserver(WebContentsObserver arg2) {
        if(this.mObserverProxy == null) {
            return;
        }

        this.mObserverProxy.removeObserver(arg2);
    }

    public void renderFrameCreated(RenderFrameHostImpl arg2) {
        this.mFrames.add(arg2);
    }

    public void renderFrameDeleted(RenderFrameHostImpl arg2) {
        this.mFrames.remove(arg2);
    }

    public void replace(String arg3) {
        this.nativeReplace(this.mNativeWebContentsAndroid, arg3);
    }

    public void replaceTranslatedString(Object arg3) {
        if(this.isDestroyed()) {
            return;
        }

        this.nativeReplaceTranslatedString(this.mNativeWebContentsAndroid, arg3);
    }

    public void requestAccessibilitySnapshot(AccessibilitySnapshotCallback arg3) {
        this.nativeRequestAccessibilitySnapshot(this.mNativeWebContentsAndroid, arg3);
    }

    public void requestSmartClipExtract(int arg11, int arg12, int arg13, int arg14) {
        if(this.mSmartClipCallback == null) {
            return;
        }

        this.mSmartClipCallback.storeRequestRect(new Rect(arg11, arg12, arg11 + arg13, arg12 + arg14));
        RenderCoordinates v0 = this.getRenderCoordinates();
        float v1 = v0.getDeviceScaleFactor();
        this.nativeRequestSmartClipExtract(this.mNativeWebContentsAndroid, this.mSmartClipCallback, ((int)((((float)arg11)) / v1)), ((int)((((float)(((int)((((float)arg12)) - v0.getContentOffsetYPix()))))) / v1)), ((int)((((float)arg13)) / v1)), ((int)((((float)arg14)) / v1)));
    }

    public void resumeLoadingCreatedWebContents() {
        this.nativeResumeLoadingCreatedWebContents(this.mNativeWebContentsAndroid);
    }

    public boolean savePage(String arg9, String arg10, int arg11) {
        MemoryDumperAndroid.startMemoryDump(new WebContentsImpl$$Lambda$0(new File(new File(arg9).getParent(), "mem_dump.json")));
        return this.nativeSavePage(this.mNativeWebContentsAndroid, arg9, arg10, arg11);
    }

    public void scrollFocusedEditableNodeIntoView() {
        this.nativeScrollFocusedEditableNodeIntoView(this.mNativeWebContentsAndroid);
    }

    public void selectAll() {
        this.nativeSelectAll(this.mNativeWebContentsAndroid);
    }

    public void selectWordAroundCaret() {
        this.nativeSelectWordAroundCaret(this.mNativeWebContentsAndroid);
    }

    @CalledByNative private static void setAccessibilitySnapshotSelection(AccessibilitySnapshotNode arg0, int arg1, int arg2) {
        arg0.setSelection(arg1, arg2);
    }

    public void setAudioMuted(boolean arg3) {
        this.nativeSetAudioMuted(this.mNativeWebContentsAndroid, arg3);
    }

    public void setBottomHeight(int arg2) {
        this.mBottomHeight = arg2;
        this.setSize(this.mWidth, this.mHeight);
    }

    public void setHasPersistentVideo(boolean arg3) {
        this.nativeSetHasPersistentVideo(this.mNativeWebContentsAndroid, arg3);
    }

    public void setImportance(int arg3) {
        this.nativeSetImportance(this.mNativeWebContentsAndroid, arg3);
    }

    public void setInternalsHolder(InternalsHolder arg2) {
        arg2.set(this.mInternalsHolder.get());
        this.mInternalsHolder = arg2;
    }

    @CalledByNative private final void setMediaSession(MediaSessionImpl arg1) {
        this.mMediaSession = arg1;
    }

    public void setOverscrollRefreshHandler(OverscrollRefreshHandler arg3) {
        this.nativeSetOverscrollRefreshHandler(this.mNativeWebContentsAndroid, arg3);
    }

    public void setSize(int arg4, int arg5) {
        this.mWidth = arg4;
        this.mHeight = arg5;
        this.nativeSetSize(this.mNativeWebContentsAndroid, arg4, arg5 - this.mBottomHeight);
    }

    public void setSmartClipResultHandler(Handler arg2) {
        if(arg2 == null) {
            this.mSmartClipCallback = null;
            return;
        }

        this.mSmartClipCallback = new SmartClipCallback(this, arg2);
    }

    public void setTranslateMode(boolean arg3) {
        if(this.isDestroyed()) {
            return;
        }

        this.nativeSetTranslateMode(this.mNativeWebContentsAndroid, arg3);
    }

    public void showContextMenuAtTouchHandle(int arg3, int arg4) {
        this.nativeShowContextMenuAtTouchHandle(this.mNativeWebContentsAndroid, arg3, arg4);
    }

    public void showInterstitialPage(String arg7, long arg8) {
        this.nativeShowInterstitialPage(this.mNativeWebContentsAndroid, arg7, arg8);
    }

    @VisibleForTesting public void simulateRendererKilledForTesting(boolean arg2) {
        if(this.mObserverProxy != null) {
            this.mObserverProxy.renderProcessGone(arg2);
        }
    }

    public void stop() {
        this.nativeStop(this.mNativeWebContentsAndroid);
    }

    public void suspendAllMediaPlayers() {
        this.nativeSuspendAllMediaPlayers(this.mNativeWebContentsAndroid);
    }

    public void writeToParcel(Parcel arg4, int arg5) {
        Bundle v5 = new Bundle();
        v5.putLong("version", 0);
        v5.putParcelable("processguard", new ParcelUuid(WebContentsImpl.sParcelableUUID));
        v5.putLong("webcontents", this.mNativeWebContentsAndroid);
        arg4.writeBundle(v5);
    }
}

