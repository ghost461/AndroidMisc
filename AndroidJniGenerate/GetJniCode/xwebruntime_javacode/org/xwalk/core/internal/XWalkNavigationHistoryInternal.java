package org.xwalk.core.internal;

import java.io.Serializable;
import org.chromium.content_public.browser.NavigationHistory;

@XWalkAPI(createInternally=true) public class XWalkNavigationHistoryInternal implements Serializable, Cloneable {
    class org.xwalk.core.internal.XWalkNavigationHistoryInternal$1 {
        static {
            org.xwalk.core.internal.XWalkNavigationHistoryInternal$1.$SwitchMap$org$xwalk$core$internal$XWalkNavigationHistoryInternal$DirectionInternal = new int[DirectionInternal.values().length];
            try {
                org.xwalk.core.internal.XWalkNavigationHistoryInternal$1.$SwitchMap$org$xwalk$core$internal$XWalkNavigationHistoryInternal$DirectionInternal[DirectionInternal.FORWARD.ordinal()] = 1;
                goto label_9;
            }
            catch(NoSuchFieldError ) {
                try {
                label_9:
                    org.xwalk.core.internal.XWalkNavigationHistoryInternal$1.$SwitchMap$org$xwalk$core$internal$XWalkNavigationHistoryInternal$DirectionInternal[DirectionInternal.BACKWARD.ordinal()] = 2;
                    return;
                }
                catch(NoSuchFieldError ) {
                    return;
                }
            }
        }
    }

    @XWalkAPI public enum DirectionInternal {
        public static final enum DirectionInternal BACKWARD;
        public static final enum DirectionInternal FORWARD;

        static {
            DirectionInternal.BACKWARD = new DirectionInternal("BACKWARD", 0);
            DirectionInternal.FORWARD = new DirectionInternal("FORWARD", 1);
            DirectionInternal.$VALUES = new DirectionInternal[]{DirectionInternal.BACKWARD, DirectionInternal.FORWARD};
        }

        private DirectionInternal(String arg1, int arg2) {
            super(arg1, arg2);
        }

        public static DirectionInternal valueOf(String arg1) {
            return Enum.valueOf(DirectionInternal.class, arg1);
        }

        public static DirectionInternal[] values() {
            return DirectionInternal.$VALUES.clone();
        }
    }

    private NavigationHistory mHistory;
    private XWalkViewInternal mXWalkView;

    XWalkNavigationHistoryInternal() {
        super();
        this.mXWalkView = null;
        this.mHistory = null;
    }

    XWalkNavigationHistoryInternal(XWalkNavigationHistoryInternal arg2) {
        super();
        this.mXWalkView = arg2.mXWalkView;
        this.mHistory = arg2.mHistory;
    }

    XWalkNavigationHistoryInternal(XWalkViewInternal arg1, NavigationHistory arg2) {
        super();
        this.mXWalkView = arg1;
        this.mHistory = arg2;
    }

    @XWalkAPI public boolean canGoBack() {
        return this.mXWalkView.canGoBack();
    }

    @XWalkAPI public boolean canGoForward() {
        return this.mXWalkView.canGoForward();
    }

    @XWalkAPI public void clear() {
        this.mXWalkView.clearHistory();
    }

    protected Object clone() throws CloneNotSupportedException {
        return this.clone();
    }

    protected XWalkNavigationHistoryInternal clone() {
        XWalkNavigationHistoryInternal v0_1;
        __monitor_enter(this);
        try {
            v0_1 = new XWalkNavigationHistoryInternal(this);
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
        return v0_1;
    }

    @XWalkAPI public int getCurrentIndex() {
        return this.mHistory.getCurrentEntryIndex();
    }

    @XWalkAPI public XWalkNavigationItemInternal getCurrentItem() {
        return this.getItemAt(this.getCurrentIndex());
    }

    @XWalkAPI public XWalkNavigationItemInternal getItemAt(int arg3) {
        if(arg3 >= 0) {
            if(arg3 >= this.size()) {
            }
            else {
                return new XWalkNavigationItemInternal(this.mHistory.getEntryAtIndex(arg3));
            }
        }

        return null;
    }

    @XWalkAPI public boolean hasItemAt(int arg3) {
        boolean v0 = true;
        if(arg3 < 0 || arg3 > this.size() - 1) {
            v0 = false;
        }
        else {
        }

        return v0;
    }

    @XWalkAPI public void navigate(DirectionInternal arg2, int arg3) {
        switch(org.xwalk.core.internal.XWalkNavigationHistoryInternal$1.$SwitchMap$org$xwalk$core$internal$XWalkNavigationHistoryInternal$DirectionInternal[arg2.ordinal()]) {
            case 1: {
                this.mXWalkView.navigateTo(arg3);
                break;
            }
            case 2: {
                this.mXWalkView.navigateTo(-arg3);
                break;
            }
            default: {
                break;
            }
        }
    }

    @XWalkAPI public int size() {
        return this.mHistory.getEntryCount();
    }
}

