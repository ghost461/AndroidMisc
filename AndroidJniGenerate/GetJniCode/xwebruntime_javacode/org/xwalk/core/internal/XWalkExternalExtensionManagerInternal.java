package org.xwalk.core.internal;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;

@XWalkAPI(createExternally=true) public abstract class XWalkExternalExtensionManagerInternal {
    private XWalkViewInternal mXWalkView;

    @XWalkAPI public XWalkExternalExtensionManagerInternal(XWalkViewInternal arg1) {
        super();
        arg1.setExternalExtensionManager(this);
        this.mXWalkView = arg1;
    }

    @Deprecated @XWalkAPI public Activity getViewActivity() {
        Context v0 = this.getViewContext();
        if((v0 instanceof Activity)) {
            return ((Activity)v0);
        }

        if((v0 instanceof ContextWrapper)) {
            v0 = ((ContextWrapper)v0).getBaseContext();
            if((v0 instanceof Activity)) {
                return ((Activity)v0);
            }
        }

        return null;
    }

    @XWalkAPI public Context getViewContext() {
        if(this.mXWalkView != null) {
            return this.mXWalkView.getViewContext();
        }

        return null;
    }

    @XWalkAPI public void loadExtension(String arg1) {
    }

    @Deprecated @XWalkAPI public void onActivityResult(int arg1, int arg2, Intent arg3) {
    }

    @XWalkAPI public void onDestroy() {
        this.mXWalkView.setExternalExtensionManager(null);
        this.mXWalkView = null;
    }

    @XWalkAPI public abstract void onNewIntent(Intent arg1);

    @XWalkAPI public void onPause() {
    }

    @XWalkAPI public void onResume() {
    }

    @XWalkAPI public void onStart() {
    }

    @XWalkAPI public void onStop() {
    }
}

