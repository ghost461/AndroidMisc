package org.chromium.content.browser.picker;

import android.text.TextUtils;

public class DateTimeSuggestion {
    private final String mLabel;
    private final String mLocalizedValue;
    private final double mValue;

    public DateTimeSuggestion(double arg1, String arg3, String arg4) {
        super();
        this.mValue = arg1;
        this.mLocalizedValue = arg3;
        this.mLabel = arg4;
    }

    public boolean equals(Object arg7) {
        boolean v1 = false;
        if(!(arg7 instanceof DateTimeSuggestion)) {
            return 0;
        }

        if(this.mValue == ((DateTimeSuggestion)arg7).mValue && (TextUtils.equals(this.mLocalizedValue, ((DateTimeSuggestion)arg7).mLocalizedValue)) && (TextUtils.equals(this.mLabel, ((DateTimeSuggestion)arg7).mLabel))) {
            v1 = true;
        }

        return v1;
    }

    public int hashCode() {
        return ((0x47B + (((int)this.mValue))) * 37 + this.mLocalizedValue.hashCode()) * 37 + this.mLabel.hashCode();
    }

    String label() {
        return this.mLabel;
    }

    String localizedValue() {
        return this.mLocalizedValue;
    }

    double value() {
        return this.mValue;
    }
}

