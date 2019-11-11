package org.chromium.ui;

public class DropdownItemBase implements DropdownItem {
    public DropdownItemBase() {
        super();
    }

    public int getIconId() {
        return 0;
    }

    public int getIconMarginResId() {
        return 0x7F050054;
    }

    public int getIconSizeResId() {
        return 0;
    }

    public String getLabel() {
        return null;
    }

    public int getLabelFontColorResId() {
        return 0x7F06005C;
    }

    public int getLabelFontSizeResId() {
        return 0x7F05007A;
    }

    public String getSublabel() {
        return null;
    }

    public int getSublabelFontSizeResId() {
        return 0x7F05007B;
    }

    public boolean isBoldLabel() {
        return 0;
    }

    public boolean isEnabled() {
        return 1;
    }

    public boolean isGroupHeader() {
        return 0;
    }

    public boolean isIconAtStart() {
        return 0;
    }

    public boolean isLabelAndSublabelOnSameLine() {
        return 0;
    }

    public boolean isMultilineLabel() {
        return 0;
    }
}

