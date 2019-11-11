package org.chromium.content.browser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.chromium.content.browser.selection.SelectionPopupControllerImpl;
import org.chromium.content.browser.webcontents.WebContentsUserData;
import org.chromium.content_public.browser.WebContents$UserDataFactory;
import org.chromium.content_public.browser.WebContents;

public class PopupController {
    class org.chromium.content.browser.PopupController$1 {
    }

    public interface HideablePopup {
        void hide();
    }

    final class UserDataFactoryLazyHolder {
        private static final UserDataFactory INSTANCE;

        static {
            UserDataFactoryLazyHolder.INSTANCE = PopupController$UserDataFactoryLazyHolder$$Lambda$0.$instance;
        }

        private UserDataFactoryLazyHolder() {
            super();
        }

        static UserDataFactory access$100() {
            return UserDataFactoryLazyHolder.INSTANCE;
        }

        static final PopupController lambda$static$0$PopupController$UserDataFactoryLazyHolder(WebContents arg2) {
            return new PopupController(arg2, null);
        }
    }

    private final List mHideablePopups;

    private PopupController(WebContents arg1) {
        super();
        this.mHideablePopups = new ArrayList();
    }

    PopupController(WebContents arg1, org.chromium.content.browser.PopupController$1 arg2) {
        this(arg1);
    }

    public static PopupController fromWebContents(WebContents arg2) {
        return WebContentsUserData.fromWebContents(arg2, PopupController.class, UserDataFactoryLazyHolder.access$100());
    }

    public static void hideAll(WebContents arg0) {
        if(arg0 == null) {
            return;
        }

        PopupController v0 = PopupController.fromWebContents(arg0);
        if(v0 != null) {
            v0.hideAllPopups();
        }
    }

    public void hideAllPopups() {
        Iterator v0 = this.mHideablePopups.iterator();
        while(v0.hasNext()) {
            v0.next().hide();
        }
    }

    public static void hidePopupsAndClearSelection(WebContents arg1) {
        if(arg1 == null) {
            return;
        }

        SelectionPopupControllerImpl v0 = SelectionPopupControllerImpl.fromWebContents(arg1);
        if(v0 != null) {
            v0.destroyActionModeAndUnselect();
        }

        arg1.dismissTextHandles();
        PopupController.hideAll(arg1);
    }

    public static void register(WebContents arg0, HideablePopup arg1) {
        if(arg0 == null) {
            return;
        }

        PopupController.fromWebContents(arg0).registerPopup(arg1);
    }

    public void registerPopup(HideablePopup arg2) {
        this.mHideablePopups.add(arg2);
    }
}

