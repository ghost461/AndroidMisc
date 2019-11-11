package org.chromium.content_public.browser;

import android.graphics.Rect;
import android.os.Handler;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import org.chromium.base.VisibleForTesting;
import org.chromium.ui.OverscrollRefreshHandler;
import org.chromium.ui.base.EventForwarder;
import org.chromium.ui.base.WindowAndroid;

public interface WebContents extends Parcelable {
    public interface InternalsHolder {
        WebContentsInternals get();

        void set(WebContentsInternals arg1);
    }

    public interface UserDataFactory {
        Object create(WebContents arg1);
    }

    void addMessageToDevToolsConsole(int arg1, String arg2);

    void addObserver(WebContentsObserver arg1);

    void adjustSelectionByCharacterOffset(int arg1, int arg2, boolean arg3);

    MessagePort[] createMessageChannel();

    void destroy();

    void didReceivedWeChatSelectionInfo(String arg1, int arg2, int arg3, String arg4, String arg5, int arg6, long arg7);

    int downloadImage(String arg1, boolean arg2, int arg3, boolean arg4, ImageDownloadCallback arg5);

    void evaluateJavaScript(String arg1, JavaScriptCallback arg2);

    @VisibleForTesting void evaluateJavaScriptForTests(String arg1, JavaScriptCallback arg2);

    void exitFullscreen();

    boolean focusLocationBarByDefault();

    int getBackgroundColor();

    void getContentBitmapAsync(int arg1, int arg2, ContentBitmapCallback arg3);

    String getEncoding();

    EventForwarder getEventForwarder();

    @Nullable Rect getFullscreenVideoSize();

    int getHeight();

    void getImageBitmapToFile(String arg1, String arg2, String arg3, String arg4, ImageBitmapToFileFinishedCallback arg5);

    String getLastCommittedUrl();

    RenderFrameHost getMainFrame();

    NavigationController getNavigationController();

    Object getOrSetUserData(Class arg1, UserDataFactory arg2);

    String getRefererUrl();

    int getThemeColor();

    String getTitle();

    WindowAndroid getTopLevelNativeWindow();

    void getTranslateSampleString(int arg1);

    String getUrl();

    String getVisibleUrl();

    int getWidth();

    boolean hasAccessedInitialDocument();

    boolean hasActiveEffectivelyFullscreenVideo();

    boolean isDestroyed();

    boolean isIncognito();

    boolean isLoading();

    boolean isLoadingToDifferentDocument();

    boolean isPictureInPictureAllowedForFullscreenVideo();

    boolean isReady();

    boolean isShowingInterstitialPage();

    void onHide();

    void onShow();

    void postMessageToFrame(String arg1, String arg2, String arg3, String arg4, MessagePort[] arg5);

    void reloadLoFiImages();

    void removeObserver(WebContentsObserver arg1);

    void replaceTranslatedString(Object arg1);

    void requestAccessibilitySnapshot(AccessibilitySnapshotCallback arg1);

    void requestSmartClipExtract(int arg1, int arg2, int arg3, int arg4);

    void resumeLoadingCreatedWebContents();

    boolean savePage(String arg1, String arg2, int arg3);

    void scrollFocusedEditableNodeIntoView();

    void selectWordAroundCaret();

    void setAudioMuted(boolean arg1);

    void setBottomHeight(int arg1);

    void setHasPersistentVideo(boolean arg1);

    void setImportance(int arg1);

    void setInternalsHolder(InternalsHolder arg1);

    void setOverscrollRefreshHandler(OverscrollRefreshHandler arg1);

    void setSize(int arg1, int arg2);

    void setSmartClipResultHandler(Handler arg1);

    void setTranslateMode(boolean arg1);

    @VisibleForTesting void showInterstitialPage(String arg1, long arg2);

    void simulateRendererKilledForTesting(boolean arg1);

    void stop();

    void suspendAllMediaPlayers();
}

