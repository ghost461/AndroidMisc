package org.chromium.components.autofill;

import org.chromium.ui.DropdownItemBase;

public class AutofillSuggestion extends DropdownItemBase {
    private static final int ITEM_ID_HTTP_NOT_SECURE_WARNING_MESSAGE = -10;
    private static final int ITEM_ID_INSECURE_CONTEXT_PAYMENT_DISABLED_MESSAGE = -1;
    private final int mIconId;
    private final boolean mIsBoldLabel;
    private final boolean mIsDeletable;
    private final boolean mIsIconAtStart;
    private final boolean mIsMultilineLabel;
    private final String mLabel;
    private final String mSublabel;
    private final int mSuggestionId;

    public AutofillSuggestion(String arg1, String arg2, int arg3, boolean arg4, int arg5, boolean arg6, boolean arg7, boolean arg8) {
        super();
        this.mLabel = arg1;
        this.mSublabel = arg2;
        this.mIconId = arg3;
        this.mIsIconAtStart = arg4;
        this.mSuggestionId = arg5;
        this.mIsDeletable = arg6;
        this.mIsMultilineLabel = arg7;
        this.mIsBoldLabel = arg8;
    }

    public int getIconId() {
        return this.mIconId;
    }

    public int getIconMarginResId() {
        if(this.mSuggestionId == -10) {
            return 0x7F05005B;
        }

        return super.getIconMarginResId();
    }

    public int getIconSizeResId() {
        if(this.mSuggestionId == -10) {
            return 0x7F05005C;
        }

        return super.getIconSizeResId();
    }

    public String getLabel() {
        return this.mLabel;
    }

    public int getLabelFontColorResId() {
        if(this.mSuggestionId == -10) {
            return 0x7F040038;
        }

        if(this.mSuggestionId == -1) {
            return 0x7F040039;
        }

        return super.getLabelFontColorResId();
    }

    public String getSublabel() {
        return this.mSublabel;
    }

    public int getSublabelFontSizeResId() {
        if(this.mSuggestionId == -10) {
            return 0x7F050059;
        }

        return super.getSublabelFontSizeResId();
    }

    public int getSuggestionId() {
        return this.mSuggestionId;
    }

    public boolean isBoldLabel() {
        return this.mIsBoldLabel;
    }

    public boolean isDeletable() {
        return this.mIsDeletable;
    }

    public boolean isFillable() {
        boolean v0 = this.mSuggestionId >= 0 ? true : false;
        return v0;
    }

    public boolean isIconAtStart() {
        if(this.mIsIconAtStart) {
            return 1;
        }

        return super.isIconAtStart();
    }

    public boolean isLabelAndSublabelOnSameLine() {
        if(this.mSuggestionId == -10) {
            return 1;
        }

        return super.isLabelAndSublabelOnSameLine();
    }

    public boolean isMultilineLabel() {
        return this.mIsMultilineLabel;
    }
}

