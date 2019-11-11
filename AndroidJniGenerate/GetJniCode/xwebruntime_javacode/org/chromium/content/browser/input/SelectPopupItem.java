package org.chromium.content.browser.input;

import org.chromium.ui.DropdownItemBase;

public class SelectPopupItem extends DropdownItemBase {
    private final String mLabel;
    private final int mType;

    public SelectPopupItem(String arg1, int arg2) {
        super();
        this.mLabel = arg1;
        this.mType = arg2;
    }

    public String getLabel() {
        return this.mLabel;
    }

    public int getType() {
        return this.mType;
    }

    public boolean isEnabled() {
        boolean v0 = this.mType == 2 || this.mType == 0 ? true : false;
        return v0;
    }

    public boolean isGroupHeader() {
        boolean v0 = this.mType == 0 ? true : false;
        return v0;
    }
}

