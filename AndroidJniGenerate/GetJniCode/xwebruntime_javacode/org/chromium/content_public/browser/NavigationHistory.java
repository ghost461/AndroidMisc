package org.chromium.content_public.browser;

import java.util.ArrayList;

public class NavigationHistory {
    private int mCurrentEntryIndex;
    private final ArrayList mEntries;

    public NavigationHistory() {
        super();
        this.mEntries = new ArrayList();
    }

    public void addEntry(NavigationEntry arg2) {
        this.mEntries.add(arg2);
    }

    public int getCurrentEntryIndex() {
        return this.mCurrentEntryIndex;
    }

    public NavigationEntry getEntryAtIndex(int arg2) {
        return this.mEntries.get(arg2);
    }

    public int getEntryCount() {
        return this.mEntries.size();
    }

    public void setCurrentEntryIndex(int arg1) {
        this.mCurrentEntryIndex = arg1;
    }
}

