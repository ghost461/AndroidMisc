package org.chromium.content_public.browser;

import org.chromium.base.VisibleForTesting;

public interface NavigationController {
    boolean canCopyStateOver();

    boolean canGoBack();

    boolean canGoForward();

    boolean canGoToOffset(int arg1);

    boolean canPruneAllButLastCommitted();

    void cancelPendingReload();

    @VisibleForTesting void clearHistory();

    void clearSslPreferences();

    void continuePendingReload();

    void copyStateFrom(NavigationController arg1, boolean arg2);

    void copyStateFromAndPrune(NavigationController arg1, boolean arg2);

    NavigationHistory getDirectedNavigationHistory(boolean arg1, int arg2);

    @VisibleForTesting NavigationEntry getEntryAtIndex(int arg1);

    String getEntryExtraData(int arg1, String arg2);

    int getLastCommittedEntryIndex();

    NavigationHistory getNavigationHistory();

    String getOriginalUrlForVisibleNavigationEntry();

    NavigationEntry getPendingEntry();

    boolean getUseDesktopUserAgent();

    void goBack();

    void goForward();

    void goToNavigationIndex(int arg1);

    void goToOffset(int arg1);

    boolean isInitialNavigation();

    void loadIfNecessary();

    void loadUrl(LoadUrlParams arg1);

    void reload(boolean arg1);

    void reloadBypassingCache(boolean arg1);

    boolean removeEntryAtIndex(int arg1);

    void requestRestoreLoad();

    void setEntryExtraData(int arg1, String arg2, String arg3);

    void setUseDesktopUserAgent(boolean arg1, boolean arg2);
}

