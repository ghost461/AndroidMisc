package org.xwalk.core.internal;

import org.chromium.content_public.browser.NavigationEntry;

@XWalkAPI(createInternally=true) public class XWalkNavigationItemInternal implements Cloneable {
    private NavigationEntry mEntry;

    XWalkNavigationItemInternal(NavigationEntry arg1) {
        super();
        this.mEntry = arg1;
    }

    XWalkNavigationItemInternal() {
        super();
        this.mEntry = null;
    }

    XWalkNavigationItemInternal(XWalkNavigationItemInternal arg1) {
        super();
        this.mEntry = arg1.mEntry;
    }

    protected Object clone() throws CloneNotSupportedException {
        return this.clone();
    }

    protected XWalkNavigationItemInternal clone() {
        XWalkNavigationItemInternal v0_1;
        __monitor_enter(this);
        try {
            v0_1 = new XWalkNavigationItemInternal(this);
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
        return v0_1;
    }

    @XWalkAPI public String getOriginalUrl() {
        return this.mEntry.getOriginalUrl();
    }

    @XWalkAPI public String getTitle() {
        return this.mEntry.getTitle();
    }

    @XWalkAPI public String getUrl() {
        return this.mEntry.getUrl();
    }
}

