package android.support.v4.view.accessibility;

import android.graphics.Rect;
import android.os.Build$VERSION;
import android.view.accessibility.AccessibilityWindowInfo;

public class AccessibilityWindowInfoCompat {
    public static final int TYPE_ACCESSIBILITY_OVERLAY = 4;
    public static final int TYPE_APPLICATION = 1;
    public static final int TYPE_INPUT_METHOD = 2;
    public static final int TYPE_SPLIT_SCREEN_DIVIDER = 5;
    public static final int TYPE_SYSTEM = 3;
    private static final int UNDEFINED = -1;
    private Object mInfo;

    private AccessibilityWindowInfoCompat(Object arg1) {
        super();
        this.mInfo = arg1;
    }

    public boolean equals(Object arg5) {
        if(this == (((AccessibilityWindowInfoCompat)arg5))) {
            return 1;
        }

        if(arg5 == null) {
            return 0;
        }

        if(this.getClass() != arg5.getClass()) {
            return 0;
        }

        if(this.mInfo == null) {
            if(((AccessibilityWindowInfoCompat)arg5).mInfo != null) {
                return 0;
            }
        }
        else if(!this.mInfo.equals(((AccessibilityWindowInfoCompat)arg5).mInfo)) {
            return 0;
        }

        return 1;
    }

    public AccessibilityNodeInfoCompat getAnchor() {
        if(Build$VERSION.SDK_INT >= 24) {
            return AccessibilityNodeInfoCompat.wrapNonNullInstance(this.mInfo.getAnchor());
        }

        return null;
    }

    public void getBoundsInScreen(Rect arg3) {
        if(Build$VERSION.SDK_INT >= 21) {
            this.mInfo.getBoundsInScreen(arg3);
        }
    }

    public AccessibilityWindowInfoCompat getChild(int arg3) {
        if(Build$VERSION.SDK_INT >= 21) {
            return AccessibilityWindowInfoCompat.wrapNonNullInstance(this.mInfo.getChild(arg3));
        }

        return null;
    }

    public int getChildCount() {
        if(Build$VERSION.SDK_INT >= 21) {
            return this.mInfo.getChildCount();
        }

        return 0;
    }

    public int getId() {
        if(Build$VERSION.SDK_INT >= 21) {
            return this.mInfo.getId();
        }

        return -1;
    }

    public int getLayer() {
        if(Build$VERSION.SDK_INT >= 21) {
            return this.mInfo.getLayer();
        }

        return -1;
    }

    public AccessibilityWindowInfoCompat getParent() {
        if(Build$VERSION.SDK_INT >= 21) {
            return AccessibilityWindowInfoCompat.wrapNonNullInstance(this.mInfo.getParent());
        }

        return null;
    }

    public AccessibilityNodeInfoCompat getRoot() {
        if(Build$VERSION.SDK_INT >= 21) {
            return AccessibilityNodeInfoCompat.wrapNonNullInstance(this.mInfo.getRoot());
        }

        return null;
    }

    public CharSequence getTitle() {
        if(Build$VERSION.SDK_INT >= 24) {
            return this.mInfo.getTitle();
        }

        return null;
    }

    public int getType() {
        if(Build$VERSION.SDK_INT >= 21) {
            return this.mInfo.getType();
        }

        return -1;
    }

    public int hashCode() {
        int v0 = this.mInfo == null ? 0 : this.mInfo.hashCode();
        return v0;
    }

    public boolean isAccessibilityFocused() {
        if(Build$VERSION.SDK_INT >= 21) {
            return this.mInfo.isAccessibilityFocused();
        }

        return 1;
    }

    public boolean isActive() {
        if(Build$VERSION.SDK_INT >= 21) {
            return this.mInfo.isActive();
        }

        return 1;
    }

    public boolean isFocused() {
        if(Build$VERSION.SDK_INT >= 21) {
            return this.mInfo.isFocused();
        }

        return 1;
    }

    public static AccessibilityWindowInfoCompat obtain() {
        if(Build$VERSION.SDK_INT >= 21) {
            return AccessibilityWindowInfoCompat.wrapNonNullInstance(AccessibilityWindowInfo.obtain());
        }

        return null;
    }

    public static AccessibilityWindowInfoCompat obtain(AccessibilityWindowInfoCompat arg3) {
        AccessibilityWindowInfoCompat v1 = null;
        if(Build$VERSION.SDK_INT >= 21) {
            if(arg3 == null) {
            }
            else {
                v1 = AccessibilityWindowInfoCompat.wrapNonNullInstance(AccessibilityWindowInfo.obtain(arg3.mInfo));
            }

            return v1;
        }

        return v1;
    }

    public void recycle() {
        if(Build$VERSION.SDK_INT >= 21) {
            this.mInfo.recycle();
        }
    }

    public String toString() {
        StringBuilder v0 = new StringBuilder();
        Rect v1 = new Rect();
        this.getBoundsInScreen(v1);
        v0.append("AccessibilityWindowInfo[");
        v0.append("id=");
        v0.append(this.getId());
        v0.append(", type=");
        v0.append(AccessibilityWindowInfoCompat.typeToString(this.getType()));
        v0.append(", layer=");
        v0.append(this.getLayer());
        v0.append(", bounds=");
        v0.append(v1);
        v0.append(", focused=");
        v0.append(this.isFocused());
        v0.append(", active=");
        v0.append(this.isActive());
        v0.append(", hasParent=");
        boolean v2 = false;
        boolean v1_1 = this.getParent() != null ? true : false;
        v0.append(v1_1);
        v0.append(", hasChildren=");
        if(this.getChildCount() > 0) {
            v2 = true;
        }

        v0.append(v2);
        v0.append(']');
        return v0.toString();
    }

    private static String typeToString(int arg0) {
        switch(arg0) {
            case 1: {
                return "TYPE_APPLICATION";
            }
            case 2: {
                return "TYPE_INPUT_METHOD";
            }
            case 3: {
                return "TYPE_SYSTEM";
            }
            case 4: {
                return "TYPE_ACCESSIBILITY_OVERLAY";
            }
        }

        return "<UNKNOWN>";
    }

    static AccessibilityWindowInfoCompat wrapNonNullInstance(Object arg1) {
        if(arg1 != null) {
            return new AccessibilityWindowInfoCompat(arg1);
        }

        return null;
    }
}

