package org.chromium.ui;

public interface DropdownItem {
    public static final int NO_ICON;

    int getIconId();

    int getIconMarginResId();

    int getIconSizeResId();

    String getLabel();

    int getLabelFontColorResId();

    int getLabelFontSizeResId();

    String getSublabel();

    int getSublabelFontSizeResId();

    boolean isBoldLabel();

    boolean isEnabled();

    boolean isGroupHeader();

    boolean isIconAtStart();

    boolean isLabelAndSublabelOnSameLine();

    boolean isMultilineLabel();
}

