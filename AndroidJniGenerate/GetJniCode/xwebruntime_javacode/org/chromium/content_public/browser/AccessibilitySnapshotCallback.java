package org.chromium.content_public.browser;

public abstract class AccessibilitySnapshotCallback {
    public AccessibilitySnapshotCallback() {
        super();
    }

    public abstract void onAccessibilitySnapshot(AccessibilitySnapshotNode arg1);
}

