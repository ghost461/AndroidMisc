package android.support.v4.view.accessibility;

import android.os.Build$VERSION;
import android.support.annotation.RequiresApi;
import android.view.accessibility.AccessibilityEvent;

public final class AccessibilityEventCompat {
    @RequiresApi(value=16) class AccessibilityEventCompatApi16Impl extends AccessibilityEventCompatBaseImpl {
        AccessibilityEventCompatApi16Impl() {
            super();
        }

        public int getAction(AccessibilityEvent arg1) {
            return arg1.getAction();
        }

        public int getMovementGranularity(AccessibilityEvent arg1) {
            return arg1.getMovementGranularity();
        }

        public void setAction(AccessibilityEvent arg1, int arg2) {
            arg1.setAction(arg2);
        }

        public void setMovementGranularity(AccessibilityEvent arg1, int arg2) {
            arg1.setMovementGranularity(arg2);
        }
    }

    @RequiresApi(value=19) class AccessibilityEventCompatApi19Impl extends AccessibilityEventCompatApi16Impl {
        AccessibilityEventCompatApi19Impl() {
            super();
        }

        public int getContentChangeTypes(AccessibilityEvent arg1) {
            return arg1.getContentChangeTypes();
        }

        public void setContentChangeTypes(AccessibilityEvent arg1, int arg2) {
            arg1.setContentChangeTypes(arg2);
        }
    }

    class AccessibilityEventCompatBaseImpl {
        AccessibilityEventCompatBaseImpl() {
            super();
        }

        public int getAction(AccessibilityEvent arg1) {
            return 0;
        }

        public int getContentChangeTypes(AccessibilityEvent arg1) {
            return 0;
        }

        public int getMovementGranularity(AccessibilityEvent arg1) {
            return 0;
        }

        public void setAction(AccessibilityEvent arg1, int arg2) {
        }

        public void setContentChangeTypes(AccessibilityEvent arg1, int arg2) {
        }

        public void setMovementGranularity(AccessibilityEvent arg1, int arg2) {
        }
    }

    public static final int CONTENT_CHANGE_TYPE_CONTENT_DESCRIPTION = 4;
    public static final int CONTENT_CHANGE_TYPE_SUBTREE = 1;
    public static final int CONTENT_CHANGE_TYPE_TEXT = 2;
    public static final int CONTENT_CHANGE_TYPE_UNDEFINED = 0;
    private static final AccessibilityEventCompatBaseImpl IMPL = null;
    public static final int TYPES_ALL_MASK = -1;
    public static final int TYPE_ANNOUNCEMENT = 0x4000;
    public static final int TYPE_ASSIST_READING_CONTEXT = 0x1000000;
    public static final int TYPE_GESTURE_DETECTION_END = 0x80000;
    public static final int TYPE_GESTURE_DETECTION_START = 0x40000;
    @Deprecated public static final int TYPE_TOUCH_EXPLORATION_GESTURE_END = 0x400;
    @Deprecated public static final int TYPE_TOUCH_EXPLORATION_GESTURE_START = 0x200;
    public static final int TYPE_TOUCH_INTERACTION_END = 0x200000;
    public static final int TYPE_TOUCH_INTERACTION_START = 0x100000;
    public static final int TYPE_VIEW_ACCESSIBILITY_FOCUSED = 0x8000;
    public static final int TYPE_VIEW_ACCESSIBILITY_FOCUS_CLEARED = 0x10000;
    public static final int TYPE_VIEW_CONTEXT_CLICKED = 0x800000;
    @Deprecated public static final int TYPE_VIEW_HOVER_ENTER = 0x80;
    @Deprecated public static final int TYPE_VIEW_HOVER_EXIT = 0x100;
    @Deprecated public static final int TYPE_VIEW_SCROLLED = 0x1000;
    @Deprecated public static final int TYPE_VIEW_TEXT_SELECTION_CHANGED = 0x2000;
    public static final int TYPE_VIEW_TEXT_TRAVERSED_AT_MOVEMENT_GRANULARITY = 0x20000;
    public static final int TYPE_WINDOWS_CHANGED = 0x400000;
    @Deprecated public static final int TYPE_WINDOW_CONTENT_CHANGED = 0x800;

    static {
        if(Build$VERSION.SDK_INT >= 19) {
            AccessibilityEventCompat.IMPL = new AccessibilityEventCompatApi19Impl();
        }
        else if(Build$VERSION.SDK_INT >= 16) {
            AccessibilityEventCompat.IMPL = new AccessibilityEventCompatApi16Impl();
        }
        else {
            AccessibilityEventCompat.IMPL = new AccessibilityEventCompatBaseImpl();
        }
    }

    private AccessibilityEventCompat() {
        super();
    }

    @Deprecated public static void appendRecord(AccessibilityEvent arg0, AccessibilityRecordCompat arg1) {
        arg0.appendRecord(arg1.getImpl());
    }

    @Deprecated public static AccessibilityRecordCompat asRecord(AccessibilityEvent arg1) {
        return new AccessibilityRecordCompat(arg1);
    }

    public int getAction(AccessibilityEvent arg2) {
        return AccessibilityEventCompat.IMPL.getAction(arg2);
    }

    public static int getContentChangeTypes(AccessibilityEvent arg1) {
        return AccessibilityEventCompat.IMPL.getContentChangeTypes(arg1);
    }

    public int getMovementGranularity(AccessibilityEvent arg2) {
        return AccessibilityEventCompat.IMPL.getMovementGranularity(arg2);
    }

    @Deprecated public static AccessibilityRecordCompat getRecord(AccessibilityEvent arg1, int arg2) {
        return new AccessibilityRecordCompat(arg1.getRecord(arg2));
    }

    @Deprecated public static int getRecordCount(AccessibilityEvent arg0) {
        return arg0.getRecordCount();
    }

    public void setAction(AccessibilityEvent arg2, int arg3) {
        AccessibilityEventCompat.IMPL.setAction(arg2, arg3);
    }

    public static void setContentChangeTypes(AccessibilityEvent arg1, int arg2) {
        AccessibilityEventCompat.IMPL.setContentChangeTypes(arg1, arg2);
    }

    public void setMovementGranularity(AccessibilityEvent arg2, int arg3) {
        AccessibilityEventCompat.IMPL.setMovementGranularity(arg2, arg3);
    }
}

