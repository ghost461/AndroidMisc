package android.support.v4.view.accessibility;

import android.graphics.Rect;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo$AccessibilityAction;
import android.view.accessibility.AccessibilityNodeInfo$CollectionInfo;
import android.view.accessibility.AccessibilityNodeInfo$CollectionItemInfo;
import android.view.accessibility.AccessibilityNodeInfo$RangeInfo;
import android.view.accessibility.AccessibilityNodeInfo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class AccessibilityNodeInfoCompat {
    public class AccessibilityActionCompat {
        public static final AccessibilityActionCompat ACTION_ACCESSIBILITY_FOCUS;
        public static final AccessibilityActionCompat ACTION_CLEAR_ACCESSIBILITY_FOCUS;
        public static final AccessibilityActionCompat ACTION_CLEAR_FOCUS;
        public static final AccessibilityActionCompat ACTION_CLEAR_SELECTION;
        public static final AccessibilityActionCompat ACTION_CLICK;
        public static final AccessibilityActionCompat ACTION_COLLAPSE;
        public static final AccessibilityActionCompat ACTION_CONTEXT_CLICK;
        public static final AccessibilityActionCompat ACTION_COPY;
        public static final AccessibilityActionCompat ACTION_CUT;
        public static final AccessibilityActionCompat ACTION_DISMISS;
        public static final AccessibilityActionCompat ACTION_EXPAND;
        public static final AccessibilityActionCompat ACTION_FOCUS;
        public static final AccessibilityActionCompat ACTION_LONG_CLICK;
        public static final AccessibilityActionCompat ACTION_NEXT_AT_MOVEMENT_GRANULARITY;
        public static final AccessibilityActionCompat ACTION_NEXT_HTML_ELEMENT;
        public static final AccessibilityActionCompat ACTION_PASTE;
        public static final AccessibilityActionCompat ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY;
        public static final AccessibilityActionCompat ACTION_PREVIOUS_HTML_ELEMENT;
        public static final AccessibilityActionCompat ACTION_SCROLL_BACKWARD;
        public static final AccessibilityActionCompat ACTION_SCROLL_DOWN;
        public static final AccessibilityActionCompat ACTION_SCROLL_FORWARD;
        public static final AccessibilityActionCompat ACTION_SCROLL_LEFT;
        public static final AccessibilityActionCompat ACTION_SCROLL_RIGHT;
        public static final AccessibilityActionCompat ACTION_SCROLL_TO_POSITION;
        public static final AccessibilityActionCompat ACTION_SCROLL_UP;
        public static final AccessibilityActionCompat ACTION_SELECT;
        public static final AccessibilityActionCompat ACTION_SET_PROGRESS;
        public static final AccessibilityActionCompat ACTION_SET_SELECTION;
        public static final AccessibilityActionCompat ACTION_SET_TEXT;
        public static final AccessibilityActionCompat ACTION_SHOW_ON_SCREEN;
        final Object mAction;

        static {
            AccessibilityActionCompat.ACTION_FOCUS = new AccessibilityActionCompat(1, null);
            AccessibilityActionCompat.ACTION_CLEAR_FOCUS = new AccessibilityActionCompat(2, null);
            AccessibilityActionCompat.ACTION_SELECT = new AccessibilityActionCompat(4, null);
            AccessibilityActionCompat.ACTION_CLEAR_SELECTION = new AccessibilityActionCompat(8, null);
            AccessibilityActionCompat.ACTION_CLICK = new AccessibilityActionCompat(16, null);
            AccessibilityActionCompat.ACTION_LONG_CLICK = new AccessibilityActionCompat(0x20, null);
            AccessibilityActionCompat.ACTION_ACCESSIBILITY_FOCUS = new AccessibilityActionCompat(0x40, null);
            AccessibilityActionCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS = new AccessibilityActionCompat(0x80, null);
            AccessibilityActionCompat.ACTION_NEXT_AT_MOVEMENT_GRANULARITY = new AccessibilityActionCompat(0x100, null);
            AccessibilityActionCompat.ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY = new AccessibilityActionCompat(0x200, null);
            AccessibilityActionCompat.ACTION_NEXT_HTML_ELEMENT = new AccessibilityActionCompat(0x400, null);
            AccessibilityActionCompat.ACTION_PREVIOUS_HTML_ELEMENT = new AccessibilityActionCompat(0x800, null);
            AccessibilityActionCompat.ACTION_SCROLL_FORWARD = new AccessibilityActionCompat(0x1000, null);
            AccessibilityActionCompat.ACTION_SCROLL_BACKWARD = new AccessibilityActionCompat(0x2000, null);
            AccessibilityActionCompat.ACTION_COPY = new AccessibilityActionCompat(0x4000, null);
            AccessibilityActionCompat.ACTION_PASTE = new AccessibilityActionCompat(0x8000, null);
            AccessibilityActionCompat.ACTION_CUT = new AccessibilityActionCompat(0x10000, null);
            AccessibilityActionCompat.ACTION_SET_SELECTION = new AccessibilityActionCompat(0x20000, null);
            AccessibilityActionCompat.ACTION_EXPAND = new AccessibilityActionCompat(0x40000, null);
            AccessibilityActionCompat.ACTION_COLLAPSE = new AccessibilityActionCompat(0x80000, null);
            AccessibilityActionCompat.ACTION_DISMISS = new AccessibilityActionCompat(0x100000, null);
            AccessibilityActionCompat.ACTION_SET_TEXT = new AccessibilityActionCompat(0x200000, null);
            AccessibilityActionCompat.ACTION_SHOW_ON_SCREEN = new AccessibilityActionCompat(AccessibilityNodeInfoCompat.IMPL.getActionShowOnScreen());
            AccessibilityActionCompat.ACTION_SCROLL_TO_POSITION = new AccessibilityActionCompat(AccessibilityNodeInfoCompat.IMPL.getActionScrollToPosition());
            AccessibilityActionCompat.ACTION_SCROLL_UP = new AccessibilityActionCompat(AccessibilityNodeInfoCompat.IMPL.getActionScrollUp());
            AccessibilityActionCompat.ACTION_SCROLL_LEFT = new AccessibilityActionCompat(AccessibilityNodeInfoCompat.IMPL.getActionScrollLeft());
            AccessibilityActionCompat.ACTION_SCROLL_DOWN = new AccessibilityActionCompat(AccessibilityNodeInfoCompat.IMPL.getActionScrollDown());
            AccessibilityActionCompat.ACTION_SCROLL_RIGHT = new AccessibilityActionCompat(AccessibilityNodeInfoCompat.IMPL.getActionScrollRight());
            AccessibilityActionCompat.ACTION_CONTEXT_CLICK = new AccessibilityActionCompat(AccessibilityNodeInfoCompat.IMPL.getActionContextClick());
            AccessibilityActionCompat.ACTION_SET_PROGRESS = new AccessibilityActionCompat(AccessibilityNodeInfoCompat.IMPL.getActionSetProgress());
        }

        AccessibilityActionCompat(Object arg1) {
            super();
            this.mAction = arg1;
        }

        public AccessibilityActionCompat(int arg2, CharSequence arg3) {
            this(AccessibilityNodeInfoCompat.IMPL.newAccessibilityAction(arg2, arg3));
        }

        public int getId() {
            return AccessibilityNodeInfoCompat.IMPL.getAccessibilityActionId(this.mAction);
        }

        public CharSequence getLabel() {
            return AccessibilityNodeInfoCompat.IMPL.getAccessibilityActionLabel(this.mAction);
        }
    }

    @RequiresApi(value=16) class AccessibilityNodeInfoApi16Impl extends AccessibilityNodeInfoBaseImpl {
        AccessibilityNodeInfoApi16Impl() {
            super();
        }

        public void addChild(AccessibilityNodeInfo arg1, View arg2, int arg3) {
            arg1.addChild(arg2, arg3);
        }

        public Object findFocus(AccessibilityNodeInfo arg1, int arg2) {
            return arg1.findFocus(arg2);
        }

        public Object focusSearch(AccessibilityNodeInfo arg1, int arg2) {
            return arg1.focusSearch(arg2);
        }

        public int getMovementGranularities(AccessibilityNodeInfo arg1) {
            return arg1.getMovementGranularities();
        }

        public boolean isAccessibilityFocused(AccessibilityNodeInfo arg1) {
            return arg1.isAccessibilityFocused();
        }

        public boolean isVisibleToUser(AccessibilityNodeInfo arg1) {
            return arg1.isVisibleToUser();
        }

        public AccessibilityNodeInfo obtain(View arg1, int arg2) {
            return AccessibilityNodeInfo.obtain(arg1, arg2);
        }

        public boolean performAction(AccessibilityNodeInfo arg1, int arg2, Bundle arg3) {
            return arg1.performAction(arg2, arg3);
        }

        public void setAccessibilityFocused(AccessibilityNodeInfo arg1, boolean arg2) {
            arg1.setAccessibilityFocused(arg2);
        }

        public void setMovementGranularities(AccessibilityNodeInfo arg1, int arg2) {
            arg1.setMovementGranularities(arg2);
        }

        public void setParent(AccessibilityNodeInfo arg1, View arg2, int arg3) {
            arg1.setParent(arg2, arg3);
        }

        public void setSource(AccessibilityNodeInfo arg1, View arg2, int arg3) {
            arg1.setSource(arg2, arg3);
        }

        public void setVisibleToUser(AccessibilityNodeInfo arg1, boolean arg2) {
            arg1.setVisibleToUser(arg2);
        }
    }

    @RequiresApi(value=17) class AccessibilityNodeInfoApi17Impl extends AccessibilityNodeInfoApi16Impl {
        AccessibilityNodeInfoApi17Impl() {
            super();
        }

        public Object getLabelFor(AccessibilityNodeInfo arg1) {
            return arg1.getLabelFor();
        }

        public Object getLabeledBy(AccessibilityNodeInfo arg1) {
            return arg1.getLabeledBy();
        }

        public void setLabelFor(AccessibilityNodeInfo arg1, View arg2) {
            arg1.setLabelFor(arg2);
        }

        public void setLabelFor(AccessibilityNodeInfo arg1, View arg2, int arg3) {
            arg1.setLabelFor(arg2, arg3);
        }

        public void setLabeledBy(AccessibilityNodeInfo arg1, View arg2) {
            arg1.setLabeledBy(arg2);
        }

        public void setLabeledBy(AccessibilityNodeInfo arg1, View arg2, int arg3) {
            arg1.setLabeledBy(arg2, arg3);
        }
    }

    @RequiresApi(value=18) class AccessibilityNodeInfoApi18Impl extends AccessibilityNodeInfoApi17Impl {
        AccessibilityNodeInfoApi18Impl() {
            super();
        }

        public List findAccessibilityNodeInfosByViewId(AccessibilityNodeInfo arg1, String arg2) {
            return arg1.findAccessibilityNodeInfosByViewId(arg2);
        }

        public int getTextSelectionEnd(AccessibilityNodeInfo arg1) {
            return arg1.getTextSelectionEnd();
        }

        public int getTextSelectionStart(AccessibilityNodeInfo arg1) {
            return arg1.getTextSelectionStart();
        }

        public String getViewIdResourceName(AccessibilityNodeInfo arg1) {
            return arg1.getViewIdResourceName();
        }

        public boolean isEditable(AccessibilityNodeInfo arg1) {
            return arg1.isEditable();
        }

        public boolean refresh(AccessibilityNodeInfo arg1) {
            return arg1.refresh();
        }

        public void setEditable(AccessibilityNodeInfo arg1, boolean arg2) {
            arg1.setEditable(arg2);
        }

        public void setTextSelection(AccessibilityNodeInfo arg1, int arg2, int arg3) {
            arg1.setTextSelection(arg2, arg3);
        }

        public void setViewIdResourceName(AccessibilityNodeInfo arg1, String arg2) {
            arg1.setViewIdResourceName(arg2);
        }
    }

    @RequiresApi(value=19) class AccessibilityNodeInfoApi19Impl extends AccessibilityNodeInfoApi18Impl {
        private static final String ROLE_DESCRIPTION_KEY = "AccessibilityNodeInfo.roleDescription";

        AccessibilityNodeInfoApi19Impl() {
            super();
        }

        public boolean canOpenPopup(AccessibilityNodeInfo arg1) {
            return arg1.canOpenPopup();
        }

        public Object getCollectionInfo(AccessibilityNodeInfo arg1) {
            return arg1.getCollectionInfo();
        }

        public int getCollectionInfoColumnCount(Object arg1) {
            return ((AccessibilityNodeInfo$CollectionInfo)arg1).getColumnCount();
        }

        public int getCollectionInfoRowCount(Object arg1) {
            return ((AccessibilityNodeInfo$CollectionInfo)arg1).getRowCount();
        }

        public int getCollectionItemColumnIndex(Object arg1) {
            return ((AccessibilityNodeInfo$CollectionItemInfo)arg1).getColumnIndex();
        }

        public int getCollectionItemColumnSpan(Object arg1) {
            return ((AccessibilityNodeInfo$CollectionItemInfo)arg1).getColumnSpan();
        }

        public Object getCollectionItemInfo(AccessibilityNodeInfo arg1) {
            return arg1.getCollectionItemInfo();
        }

        public int getCollectionItemRowIndex(Object arg1) {
            return ((AccessibilityNodeInfo$CollectionItemInfo)arg1).getRowIndex();
        }

        public int getCollectionItemRowSpan(Object arg1) {
            return ((AccessibilityNodeInfo$CollectionItemInfo)arg1).getRowSpan();
        }

        public Bundle getExtras(AccessibilityNodeInfo arg1) {
            return arg1.getExtras();
        }

        public int getInputType(AccessibilityNodeInfo arg1) {
            return arg1.getInputType();
        }

        public int getLiveRegion(AccessibilityNodeInfo arg1) {
            return arg1.getLiveRegion();
        }

        public Object getRangeInfo(AccessibilityNodeInfo arg1) {
            return arg1.getRangeInfo();
        }

        public CharSequence getRoleDescription(AccessibilityNodeInfo arg2) {
            return this.getExtras(arg2).getCharSequence("AccessibilityNodeInfo.roleDescription");
        }

        public boolean isCollectionInfoHierarchical(Object arg1) {
            return ((AccessibilityNodeInfo$CollectionInfo)arg1).isHierarchical();
        }

        public boolean isCollectionItemHeading(Object arg1) {
            return ((AccessibilityNodeInfo$CollectionItemInfo)arg1).isHeading();
        }

        public boolean isContentInvalid(AccessibilityNodeInfo arg1) {
            return arg1.isContentInvalid();
        }

        public boolean isDismissable(AccessibilityNodeInfo arg1) {
            return arg1.isDismissable();
        }

        public boolean isMultiLine(AccessibilityNodeInfo arg1) {
            return arg1.isMultiLine();
        }

        public Object obtainCollectionInfo(int arg1, int arg2, boolean arg3) {
            return AccessibilityNodeInfo$CollectionInfo.obtain(arg1, arg2, arg3);
        }

        public Object obtainCollectionInfo(int arg1, int arg2, boolean arg3, int arg4) {
            return AccessibilityNodeInfo$CollectionInfo.obtain(arg1, arg2, arg3);
        }

        public Object obtainCollectionItemInfo(int arg1, int arg2, int arg3, int arg4, boolean arg5) {
            return AccessibilityNodeInfo$CollectionItemInfo.obtain(arg1, arg2, arg3, arg4, arg5);
        }

        public Object obtainCollectionItemInfo(int arg1, int arg2, int arg3, int arg4, boolean arg5, boolean arg6) {
            return AccessibilityNodeInfo$CollectionItemInfo.obtain(arg1, arg2, arg3, arg4, arg5);
        }

        public Object obtainRangeInfo(int arg1, float arg2, float arg3, float arg4) {
            return AccessibilityNodeInfo$RangeInfo.obtain(arg1, arg2, arg3, arg4);
        }

        public void setCanOpenPopup(AccessibilityNodeInfo arg1, boolean arg2) {
            arg1.setCanOpenPopup(arg2);
        }

        public void setCollectionInfo(AccessibilityNodeInfo arg1, Object arg2) {
            arg1.setCollectionInfo(((AccessibilityNodeInfo$CollectionInfo)arg2));
        }

        public void setCollectionItemInfo(AccessibilityNodeInfo arg1, Object arg2) {
            arg1.setCollectionItemInfo(((AccessibilityNodeInfo$CollectionItemInfo)arg2));
        }

        public void setContentInvalid(AccessibilityNodeInfo arg1, boolean arg2) {
            arg1.setContentInvalid(arg2);
        }

        public void setDismissable(AccessibilityNodeInfo arg1, boolean arg2) {
            arg1.setDismissable(arg2);
        }

        public void setInputType(AccessibilityNodeInfo arg1, int arg2) {
            arg1.setInputType(arg2);
        }

        public void setLiveRegion(AccessibilityNodeInfo arg1, int arg2) {
            arg1.setLiveRegion(arg2);
        }

        public void setMultiLine(AccessibilityNodeInfo arg1, boolean arg2) {
            arg1.setMultiLine(arg2);
        }

        public void setRangeInfo(AccessibilityNodeInfo arg1, Object arg2) {
            arg1.setRangeInfo(((AccessibilityNodeInfo$RangeInfo)arg2));
        }

        public void setRoleDescription(AccessibilityNodeInfo arg2, CharSequence arg3) {
            this.getExtras(arg2).putCharSequence("AccessibilityNodeInfo.roleDescription", arg3);
        }
    }

    @RequiresApi(value=21) class AccessibilityNodeInfoApi21Impl extends AccessibilityNodeInfoApi19Impl {
        AccessibilityNodeInfoApi21Impl() {
            super();
        }

        public void addAction(AccessibilityNodeInfo arg1, Object arg2) {
            arg1.addAction(((AccessibilityNodeInfo$AccessibilityAction)arg2));
        }

        public int getAccessibilityActionId(Object arg1) {
            return ((AccessibilityNodeInfo$AccessibilityAction)arg1).getId();
        }

        public CharSequence getAccessibilityActionLabel(Object arg1) {
            return ((AccessibilityNodeInfo$AccessibilityAction)arg1).getLabel();
        }

        public List getActionList(AccessibilityNodeInfo arg1) {
            return arg1.getActionList();
        }

        public int getCollectionInfoSelectionMode(Object arg1) {
            return ((AccessibilityNodeInfo$CollectionInfo)arg1).getSelectionMode();
        }

        public CharSequence getError(AccessibilityNodeInfo arg1) {
            return arg1.getError();
        }

        public int getMaxTextLength(AccessibilityNodeInfo arg1) {
            return arg1.getMaxTextLength();
        }

        public Object getWindow(AccessibilityNodeInfo arg1) {
            return arg1.getWindow();
        }

        public boolean isCollectionItemSelected(Object arg1) {
            return ((AccessibilityNodeInfo$CollectionItemInfo)arg1).isSelected();
        }

        public Object newAccessibilityAction(int arg2, CharSequence arg3) {
            return new AccessibilityNodeInfo$AccessibilityAction(arg2, arg3);
        }

        public Object obtainCollectionInfo(int arg1, int arg2, boolean arg3, int arg4) {
            return AccessibilityNodeInfo$CollectionInfo.obtain(arg1, arg2, arg3, arg4);
        }

        public Object obtainCollectionItemInfo(int arg1, int arg2, int arg3, int arg4, boolean arg5, boolean arg6) {
            return AccessibilityNodeInfo$CollectionItemInfo.obtain(arg1, arg2, arg3, arg4, arg5, arg6);
        }

        public boolean removeAction(AccessibilityNodeInfo arg1, Object arg2) {
            return arg1.removeAction(((AccessibilityNodeInfo$AccessibilityAction)arg2));
        }

        public boolean removeChild(AccessibilityNodeInfo arg1, View arg2) {
            return arg1.removeChild(arg2);
        }

        public boolean removeChild(AccessibilityNodeInfo arg1, View arg2, int arg3) {
            return arg1.removeChild(arg2, arg3);
        }

        public void setError(AccessibilityNodeInfo arg1, CharSequence arg2) {
            arg1.setError(arg2);
        }

        public void setMaxTextLength(AccessibilityNodeInfo arg1, int arg2) {
            arg1.setMaxTextLength(arg2);
        }
    }

    @RequiresApi(value=22) class AccessibilityNodeInfoApi22Impl extends AccessibilityNodeInfoApi21Impl {
        AccessibilityNodeInfoApi22Impl() {
            super();
        }

        public Object getTraversalAfter(AccessibilityNodeInfo arg1) {
            return arg1.getTraversalAfter();
        }

        public Object getTraversalBefore(AccessibilityNodeInfo arg1) {
            return arg1.getTraversalBefore();
        }

        public void setTraversalAfter(AccessibilityNodeInfo arg1, View arg2) {
            arg1.setTraversalAfter(arg2);
        }

        public void setTraversalAfter(AccessibilityNodeInfo arg1, View arg2, int arg3) {
            arg1.setTraversalAfter(arg2, arg3);
        }

        public void setTraversalBefore(AccessibilityNodeInfo arg1, View arg2) {
            arg1.setTraversalBefore(arg2);
        }

        public void setTraversalBefore(AccessibilityNodeInfo arg1, View arg2, int arg3) {
            arg1.setTraversalBefore(arg2, arg3);
        }
    }

    @RequiresApi(value=23) class AccessibilityNodeInfoApi23Impl extends AccessibilityNodeInfoApi22Impl {
        AccessibilityNodeInfoApi23Impl() {
            super();
        }

        public Object getActionContextClick() {
            return AccessibilityNodeInfo$AccessibilityAction.ACTION_CONTEXT_CLICK;
        }

        public Object getActionScrollDown() {
            return AccessibilityNodeInfo$AccessibilityAction.ACTION_SCROLL_DOWN;
        }

        public Object getActionScrollLeft() {
            return AccessibilityNodeInfo$AccessibilityAction.ACTION_SCROLL_LEFT;
        }

        public Object getActionScrollRight() {
            return AccessibilityNodeInfo$AccessibilityAction.ACTION_SCROLL_RIGHT;
        }

        public Object getActionScrollToPosition() {
            return AccessibilityNodeInfo$AccessibilityAction.ACTION_SCROLL_TO_POSITION;
        }

        public Object getActionScrollUp() {
            return AccessibilityNodeInfo$AccessibilityAction.ACTION_SCROLL_UP;
        }

        public Object getActionShowOnScreen() {
            return AccessibilityNodeInfo$AccessibilityAction.ACTION_SHOW_ON_SCREEN;
        }

        public boolean isContextClickable(AccessibilityNodeInfo arg1) {
            return arg1.isContextClickable();
        }

        public void setContextClickable(AccessibilityNodeInfo arg1, boolean arg2) {
            arg1.setContextClickable(arg2);
        }
    }

    @RequiresApi(value=24) class AccessibilityNodeInfoApi24Impl extends AccessibilityNodeInfoApi23Impl {
        AccessibilityNodeInfoApi24Impl() {
            super();
        }

        public Object getActionSetProgress() {
            return AccessibilityNodeInfo$AccessibilityAction.ACTION_SET_PROGRESS;
        }

        public int getDrawingOrder(AccessibilityNodeInfo arg1) {
            return arg1.getDrawingOrder();
        }

        public boolean isImportantForAccessibility(AccessibilityNodeInfo arg1) {
            return arg1.isImportantForAccessibility();
        }

        public void setDrawingOrder(AccessibilityNodeInfo arg1, int arg2) {
            arg1.setDrawingOrder(arg2);
        }

        public void setImportantForAccessibility(AccessibilityNodeInfo arg1, boolean arg2) {
            arg1.setImportantForAccessibility(arg2);
        }
    }

    class AccessibilityNodeInfoBaseImpl {
        AccessibilityNodeInfoBaseImpl() {
            super();
        }

        public void addAction(AccessibilityNodeInfo arg1, Object arg2) {
        }

        public void addChild(AccessibilityNodeInfo arg1, View arg2, int arg3) {
        }

        public boolean canOpenPopup(AccessibilityNodeInfo arg1) {
            return 0;
        }

        public List findAccessibilityNodeInfosByViewId(AccessibilityNodeInfo arg1, String arg2) {
            return Collections.emptyList();
        }

        public Object findFocus(AccessibilityNodeInfo arg1, int arg2) {
            return null;
        }

        public Object focusSearch(AccessibilityNodeInfo arg1, int arg2) {
            return null;
        }

        public int getAccessibilityActionId(Object arg1) {
            return 0;
        }

        public CharSequence getAccessibilityActionLabel(Object arg1) {
            return null;
        }

        public Object getActionContextClick() {
            return null;
        }

        public List getActionList(AccessibilityNodeInfo arg1) {
            return null;
        }

        public Object getActionScrollDown() {
            return null;
        }

        public Object getActionScrollLeft() {
            return null;
        }

        public Object getActionScrollRight() {
            return null;
        }

        public Object getActionScrollToPosition() {
            return null;
        }

        public Object getActionScrollUp() {
            return null;
        }

        public Object getActionSetProgress() {
            return null;
        }

        public Object getActionShowOnScreen() {
            return null;
        }

        public Object getCollectionInfo(AccessibilityNodeInfo arg1) {
            return null;
        }

        public int getCollectionInfoColumnCount(Object arg1) {
            return 0;
        }

        public int getCollectionInfoRowCount(Object arg1) {
            return 0;
        }

        public int getCollectionInfoSelectionMode(Object arg1) {
            return 0;
        }

        public int getCollectionItemColumnIndex(Object arg1) {
            return 0;
        }

        public int getCollectionItemColumnSpan(Object arg1) {
            return 0;
        }

        public Object getCollectionItemInfo(AccessibilityNodeInfo arg1) {
            return null;
        }

        public int getCollectionItemRowIndex(Object arg1) {
            return 0;
        }

        public int getCollectionItemRowSpan(Object arg1) {
            return 0;
        }

        public int getDrawingOrder(AccessibilityNodeInfo arg1) {
            return 0;
        }

        public CharSequence getError(AccessibilityNodeInfo arg1) {
            return null;
        }

        public Bundle getExtras(AccessibilityNodeInfo arg1) {
            return new Bundle();
        }

        public int getInputType(AccessibilityNodeInfo arg1) {
            return 0;
        }

        public Object getLabelFor(AccessibilityNodeInfo arg1) {
            return null;
        }

        public Object getLabeledBy(AccessibilityNodeInfo arg1) {
            return null;
        }

        public int getLiveRegion(AccessibilityNodeInfo arg1) {
            return 0;
        }

        public int getMaxTextLength(AccessibilityNodeInfo arg1) {
            return -1;
        }

        public int getMovementGranularities(AccessibilityNodeInfo arg1) {
            return 0;
        }

        public Object getRangeInfo(AccessibilityNodeInfo arg1) {
            return null;
        }

        public CharSequence getRoleDescription(AccessibilityNodeInfo arg1) {
            return null;
        }

        public int getTextSelectionEnd(AccessibilityNodeInfo arg1) {
            return -1;
        }

        public int getTextSelectionStart(AccessibilityNodeInfo arg1) {
            return -1;
        }

        public Object getTraversalAfter(AccessibilityNodeInfo arg1) {
            return null;
        }

        public Object getTraversalBefore(AccessibilityNodeInfo arg1) {
            return null;
        }

        public String getViewIdResourceName(AccessibilityNodeInfo arg1) {
            return null;
        }

        public Object getWindow(AccessibilityNodeInfo arg1) {
            return null;
        }

        public boolean isAccessibilityFocused(AccessibilityNodeInfo arg1) {
            return 0;
        }

        public boolean isCollectionInfoHierarchical(Object arg1) {
            return 0;
        }

        public boolean isCollectionItemHeading(Object arg1) {
            return 0;
        }

        public boolean isCollectionItemSelected(Object arg1) {
            return 0;
        }

        public boolean isContentInvalid(AccessibilityNodeInfo arg1) {
            return 0;
        }

        public boolean isContextClickable(AccessibilityNodeInfo arg1) {
            return 0;
        }

        public boolean isDismissable(AccessibilityNodeInfo arg1) {
            return 0;
        }

        public boolean isEditable(AccessibilityNodeInfo arg1) {
            return 0;
        }

        public boolean isImportantForAccessibility(AccessibilityNodeInfo arg1) {
            return 1;
        }

        public boolean isMultiLine(AccessibilityNodeInfo arg1) {
            return 0;
        }

        public boolean isVisibleToUser(AccessibilityNodeInfo arg1) {
            return 0;
        }

        public Object newAccessibilityAction(int arg1, CharSequence arg2) {
            return null;
        }

        public AccessibilityNodeInfo obtain(View arg1, int arg2) {
            return null;
        }

        public Object obtainCollectionInfo(int arg1, int arg2, boolean arg3) {
            return null;
        }

        public Object obtainCollectionInfo(int arg1, int arg2, boolean arg3, int arg4) {
            return null;
        }

        public Object obtainCollectionItemInfo(int arg1, int arg2, int arg3, int arg4, boolean arg5) {
            return null;
        }

        public Object obtainCollectionItemInfo(int arg1, int arg2, int arg3, int arg4, boolean arg5, boolean arg6) {
            return null;
        }

        public Object obtainRangeInfo(int arg1, float arg2, float arg3, float arg4) {
            return null;
        }

        public boolean performAction(AccessibilityNodeInfo arg1, int arg2, Bundle arg3) {
            return 0;
        }

        public boolean refresh(AccessibilityNodeInfo arg1) {
            return 0;
        }

        public boolean removeAction(AccessibilityNodeInfo arg1, Object arg2) {
            return 0;
        }

        public boolean removeChild(AccessibilityNodeInfo arg1, View arg2) {
            return 0;
        }

        public boolean removeChild(AccessibilityNodeInfo arg1, View arg2, int arg3) {
            return 0;
        }

        public void setAccessibilityFocused(AccessibilityNodeInfo arg1, boolean arg2) {
        }

        public void setCanOpenPopup(AccessibilityNodeInfo arg1, boolean arg2) {
        }

        public void setCollectionInfo(AccessibilityNodeInfo arg1, Object arg2) {
        }

        public void setCollectionItemInfo(AccessibilityNodeInfo arg1, Object arg2) {
        }

        public void setContentInvalid(AccessibilityNodeInfo arg1, boolean arg2) {
        }

        public void setContextClickable(AccessibilityNodeInfo arg1, boolean arg2) {
        }

        public void setDismissable(AccessibilityNodeInfo arg1, boolean arg2) {
        }

        public void setDrawingOrder(AccessibilityNodeInfo arg1, int arg2) {
        }

        public void setEditable(AccessibilityNodeInfo arg1, boolean arg2) {
        }

        public void setError(AccessibilityNodeInfo arg1, CharSequence arg2) {
        }

        public void setImportantForAccessibility(AccessibilityNodeInfo arg1, boolean arg2) {
        }

        public void setInputType(AccessibilityNodeInfo arg1, int arg2) {
        }

        public void setLabelFor(AccessibilityNodeInfo arg1, View arg2) {
        }

        public void setLabelFor(AccessibilityNodeInfo arg1, View arg2, int arg3) {
        }

        public void setLabeledBy(AccessibilityNodeInfo arg1, View arg2) {
        }

        public void setLabeledBy(AccessibilityNodeInfo arg1, View arg2, int arg3) {
        }

        public void setLiveRegion(AccessibilityNodeInfo arg1, int arg2) {
        }

        public void setMaxTextLength(AccessibilityNodeInfo arg1, int arg2) {
        }

        public void setMovementGranularities(AccessibilityNodeInfo arg1, int arg2) {
        }

        public void setMultiLine(AccessibilityNodeInfo arg1, boolean arg2) {
        }

        public void setParent(AccessibilityNodeInfo arg1, View arg2, int arg3) {
        }

        public void setRangeInfo(AccessibilityNodeInfo arg1, Object arg2) {
        }

        public void setRoleDescription(AccessibilityNodeInfo arg1, CharSequence arg2) {
        }

        public void setSource(AccessibilityNodeInfo arg1, View arg2, int arg3) {
        }

        public void setTextSelection(AccessibilityNodeInfo arg1, int arg2, int arg3) {
        }

        public void setTraversalAfter(AccessibilityNodeInfo arg1, View arg2) {
        }

        public void setTraversalAfter(AccessibilityNodeInfo arg1, View arg2, int arg3) {
        }

        public void setTraversalBefore(AccessibilityNodeInfo arg1, View arg2) {
        }

        public void setTraversalBefore(AccessibilityNodeInfo arg1, View arg2, int arg3) {
        }

        public void setViewIdResourceName(AccessibilityNodeInfo arg1, String arg2) {
        }

        public void setVisibleToUser(AccessibilityNodeInfo arg1, boolean arg2) {
        }
    }

    public class CollectionInfoCompat {
        public static final int SELECTION_MODE_MULTIPLE = 2;
        public static final int SELECTION_MODE_NONE = 0;
        public static final int SELECTION_MODE_SINGLE = 1;
        final Object mInfo;

        CollectionInfoCompat(Object arg1) {
            super();
            this.mInfo = arg1;
        }

        public int getColumnCount() {
            return AccessibilityNodeInfoCompat.IMPL.getCollectionInfoColumnCount(this.mInfo);
        }

        public int getRowCount() {
            return AccessibilityNodeInfoCompat.IMPL.getCollectionInfoRowCount(this.mInfo);
        }

        public int getSelectionMode() {
            return AccessibilityNodeInfoCompat.IMPL.getCollectionInfoSelectionMode(this.mInfo);
        }

        public boolean isHierarchical() {
            return AccessibilityNodeInfoCompat.IMPL.isCollectionInfoHierarchical(this.mInfo);
        }

        public static CollectionInfoCompat obtain(int arg2, int arg3, boolean arg4) {
            return new CollectionInfoCompat(AccessibilityNodeInfoCompat.IMPL.obtainCollectionInfo(arg2, arg3, arg4));
        }

        public static CollectionInfoCompat obtain(int arg2, int arg3, boolean arg4, int arg5) {
            return new CollectionInfoCompat(AccessibilityNodeInfoCompat.IMPL.obtainCollectionInfo(arg2, arg3, arg4, arg5));
        }
    }

    public class CollectionItemInfoCompat {
        final Object mInfo;

        CollectionItemInfoCompat(Object arg1) {
            super();
            this.mInfo = arg1;
        }

        public int getColumnIndex() {
            return AccessibilityNodeInfoCompat.IMPL.getCollectionItemColumnIndex(this.mInfo);
        }

        public int getColumnSpan() {
            return AccessibilityNodeInfoCompat.IMPL.getCollectionItemColumnSpan(this.mInfo);
        }

        public int getRowIndex() {
            return AccessibilityNodeInfoCompat.IMPL.getCollectionItemRowIndex(this.mInfo);
        }

        public int getRowSpan() {
            return AccessibilityNodeInfoCompat.IMPL.getCollectionItemRowSpan(this.mInfo);
        }

        public boolean isHeading() {
            return AccessibilityNodeInfoCompat.IMPL.isCollectionItemHeading(this.mInfo);
        }

        public boolean isSelected() {
            return AccessibilityNodeInfoCompat.IMPL.isCollectionItemSelected(this.mInfo);
        }

        public static CollectionItemInfoCompat obtain(int arg7, int arg8, int arg9, int arg10, boolean arg11) {
            return new CollectionItemInfoCompat(AccessibilityNodeInfoCompat.IMPL.obtainCollectionItemInfo(arg7, arg8, arg9, arg10, arg11));
        }

        public static CollectionItemInfoCompat obtain(int arg8, int arg9, int arg10, int arg11, boolean arg12, boolean arg13) {
            return new CollectionItemInfoCompat(AccessibilityNodeInfoCompat.IMPL.obtainCollectionItemInfo(arg8, arg9, arg10, arg11, arg12, arg13));
        }
    }

    public class RangeInfoCompat {
        public static final int RANGE_TYPE_FLOAT = 1;
        public static final int RANGE_TYPE_INT = 0;
        public static final int RANGE_TYPE_PERCENT = 2;
        final Object mInfo;

        RangeInfoCompat(Object arg1) {
            super();
            this.mInfo = arg1;
        }

        public float getCurrent() {
            return RangeInfo.getCurrent(this.mInfo);
        }

        public float getMax() {
            return RangeInfo.getMax(this.mInfo);
        }

        public float getMin() {
            return RangeInfo.getMin(this.mInfo);
        }

        public int getType() {
            return RangeInfo.getType(this.mInfo);
        }

        public static RangeInfoCompat obtain(int arg2, float arg3, float arg4, float arg5) {
            return new RangeInfoCompat(AccessibilityNodeInfoCompat.IMPL.obtainRangeInfo(arg2, arg3, arg4, arg5));
        }
    }

    public static final int ACTION_ACCESSIBILITY_FOCUS = 0x40;
    public static final String ACTION_ARGUMENT_COLUMN_INT = "android.view.accessibility.action.ARGUMENT_COLUMN_INT";
    public static final String ACTION_ARGUMENT_EXTEND_SELECTION_BOOLEAN = "ACTION_ARGUMENT_EXTEND_SELECTION_BOOLEAN";
    public static final String ACTION_ARGUMENT_HTML_ELEMENT_STRING = "ACTION_ARGUMENT_HTML_ELEMENT_STRING";
    public static final String ACTION_ARGUMENT_MOVEMENT_GRANULARITY_INT = "ACTION_ARGUMENT_MOVEMENT_GRANULARITY_INT";
    public static final String ACTION_ARGUMENT_PROGRESS_VALUE = "android.view.accessibility.action.ARGUMENT_PROGRESS_VALUE";
    public static final String ACTION_ARGUMENT_ROW_INT = "android.view.accessibility.action.ARGUMENT_ROW_INT";
    public static final String ACTION_ARGUMENT_SELECTION_END_INT = "ACTION_ARGUMENT_SELECTION_END_INT";
    public static final String ACTION_ARGUMENT_SELECTION_START_INT = "ACTION_ARGUMENT_SELECTION_START_INT";
    public static final String ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE = "ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE";
    public static final int ACTION_CLEAR_ACCESSIBILITY_FOCUS = 0x80;
    public static final int ACTION_CLEAR_FOCUS = 2;
    public static final int ACTION_CLEAR_SELECTION = 8;
    public static final int ACTION_CLICK = 16;
    public static final int ACTION_COLLAPSE = 0x80000;
    public static final int ACTION_COPY = 0x4000;
    public static final int ACTION_CUT = 0x10000;
    public static final int ACTION_DISMISS = 0x100000;
    public static final int ACTION_EXPAND = 0x40000;
    public static final int ACTION_FOCUS = 1;
    public static final int ACTION_LONG_CLICK = 0x20;
    public static final int ACTION_NEXT_AT_MOVEMENT_GRANULARITY = 0x100;
    public static final int ACTION_NEXT_HTML_ELEMENT = 0x400;
    public static final int ACTION_PASTE = 0x8000;
    public static final int ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY = 0x200;
    public static final int ACTION_PREVIOUS_HTML_ELEMENT = 0x800;
    public static final int ACTION_SCROLL_BACKWARD = 0x2000;
    public static final int ACTION_SCROLL_FORWARD = 0x1000;
    public static final int ACTION_SELECT = 4;
    public static final int ACTION_SET_SELECTION = 0x20000;
    public static final int ACTION_SET_TEXT = 0x200000;
    public static final int FOCUS_ACCESSIBILITY = 2;
    public static final int FOCUS_INPUT = 1;
    static final AccessibilityNodeInfoBaseImpl IMPL = null;
    public static final int MOVEMENT_GRANULARITY_CHARACTER = 1;
    public static final int MOVEMENT_GRANULARITY_LINE = 4;
    public static final int MOVEMENT_GRANULARITY_PAGE = 16;
    public static final int MOVEMENT_GRANULARITY_PARAGRAPH = 8;
    public static final int MOVEMENT_GRANULARITY_WORD = 2;
    private final AccessibilityNodeInfo mInfo;
    @RestrictTo(value={Scope.LIBRARY_GROUP}) public int mParentVirtualDescendantId;

    static {
        if(Build$VERSION.SDK_INT >= 24) {
            AccessibilityNodeInfoCompat.IMPL = new AccessibilityNodeInfoApi24Impl();
        }
        else if(Build$VERSION.SDK_INT >= 23) {
            AccessibilityNodeInfoCompat.IMPL = new AccessibilityNodeInfoApi23Impl();
        }
        else if(Build$VERSION.SDK_INT >= 22) {
            AccessibilityNodeInfoCompat.IMPL = new AccessibilityNodeInfoApi22Impl();
        }
        else if(Build$VERSION.SDK_INT >= 21) {
            AccessibilityNodeInfoCompat.IMPL = new AccessibilityNodeInfoApi21Impl();
        }
        else if(Build$VERSION.SDK_INT >= 19) {
            AccessibilityNodeInfoCompat.IMPL = new AccessibilityNodeInfoApi19Impl();
        }
        else if(Build$VERSION.SDK_INT >= 18) {
            AccessibilityNodeInfoCompat.IMPL = new AccessibilityNodeInfoApi18Impl();
        }
        else if(Build$VERSION.SDK_INT >= 17) {
            AccessibilityNodeInfoCompat.IMPL = new AccessibilityNodeInfoApi17Impl();
        }
        else if(Build$VERSION.SDK_INT >= 16) {
            AccessibilityNodeInfoCompat.IMPL = new AccessibilityNodeInfoApi16Impl();
        }
        else {
            AccessibilityNodeInfoCompat.IMPL = new AccessibilityNodeInfoBaseImpl();
        }
    }

    private AccessibilityNodeInfoCompat(AccessibilityNodeInfo arg2) {
        super();
        this.mParentVirtualDescendantId = -1;
        this.mInfo = arg2;
    }

    @Deprecated public AccessibilityNodeInfoCompat(Object arg2) {
        super();
        this.mParentVirtualDescendantId = -1;
        this.mInfo = ((AccessibilityNodeInfo)arg2);
    }

    public void addAction(int arg2) {
        this.mInfo.addAction(arg2);
    }

    public void addAction(AccessibilityActionCompat arg3) {
        AccessibilityNodeInfoCompat.IMPL.addAction(this.mInfo, arg3.mAction);
    }

    public void addChild(View arg2) {
        this.mInfo.addChild(arg2);
    }

    public void addChild(View arg3, int arg4) {
        AccessibilityNodeInfoCompat.IMPL.addChild(this.mInfo, arg3, arg4);
    }

    public boolean canOpenPopup() {
        return AccessibilityNodeInfoCompat.IMPL.canOpenPopup(this.mInfo);
    }

    public boolean equals(Object arg5) {
        if(this == (((AccessibilityNodeInfoCompat)arg5))) {
            return 1;
        }

        if(arg5 == null) {
            return 0;
        }

        if(this.getClass() != arg5.getClass()) {
            return 0;
        }

        if(this.mInfo == null) {
            if(((AccessibilityNodeInfoCompat)arg5).mInfo != null) {
                return 0;
            }
        }
        else if(!this.mInfo.equals(((AccessibilityNodeInfoCompat)arg5).mInfo)) {
            return 0;
        }

        return 1;
    }

    public List findAccessibilityNodeInfosByText(String arg5) {
        ArrayList v0 = new ArrayList();
        List v5 = this.mInfo.findAccessibilityNodeInfosByText(arg5);
        int v1 = v5.size();
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            ((List)v0).add(AccessibilityNodeInfoCompat.wrap(v5.get(v2)));
        }

        return ((List)v0);
    }

    public List findAccessibilityNodeInfosByViewId(String arg3) {
        List v3 = AccessibilityNodeInfoCompat.IMPL.findAccessibilityNodeInfosByViewId(this.mInfo, arg3);
        if(v3 != null) {
            ArrayList v0 = new ArrayList();
            Iterator v3_1 = v3.iterator();
            while(v3_1.hasNext()) {
                ((List)v0).add(AccessibilityNodeInfoCompat.wrap(v3_1.next()));
            }

            return ((List)v0);
        }

        return Collections.emptyList();
    }

    public AccessibilityNodeInfoCompat findFocus(int arg3) {
        return AccessibilityNodeInfoCompat.wrapNonNullInstance(AccessibilityNodeInfoCompat.IMPL.findFocus(this.mInfo, arg3));
    }

    public AccessibilityNodeInfoCompat focusSearch(int arg3) {
        return AccessibilityNodeInfoCompat.wrapNonNullInstance(AccessibilityNodeInfoCompat.IMPL.focusSearch(this.mInfo, arg3));
    }

    public List getActionList() {
        List v0 = AccessibilityNodeInfoCompat.IMPL.getActionList(this.mInfo);
        if(v0 != null) {
            ArrayList v1 = new ArrayList();
            int v2 = v0.size();
            int v3;
            for(v3 = 0; v3 < v2; ++v3) {
                ((List)v1).add(new AccessibilityActionCompat(v0.get(v3)));
            }

            return ((List)v1);
        }

        return Collections.emptyList();
    }

    private static String getActionSymbolicName(int arg0) {
        switch(arg0) {
            case 1: {
                return "ACTION_FOCUS";
            }
            case 2: {
                return "ACTION_CLEAR_FOCUS";
            }
            case 4: {
                return "ACTION_SELECT";
            }
            case 8: {
                return "ACTION_CLEAR_SELECTION";
            }
            case 16: {
                return "ACTION_CLICK";
            }
            case 32: {
                return "ACTION_LONG_CLICK";
            }
            case 64: {
                return "ACTION_ACCESSIBILITY_FOCUS";
            }
            case 128: {
                return "ACTION_CLEAR_ACCESSIBILITY_FOCUS";
            }
            case 256: {
                return "ACTION_NEXT_AT_MOVEMENT_GRANULARITY";
            }
            case 512: {
                return "ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY";
            }
            case 1024: {
                return "ACTION_NEXT_HTML_ELEMENT";
            }
            case 2048: {
                return "ACTION_PREVIOUS_HTML_ELEMENT";
            }
            case 4096: {
                return "ACTION_SCROLL_FORWARD";
            }
            case 8192: {
                return "ACTION_SCROLL_BACKWARD";
            }
            case 16384: {
                return "ACTION_COPY";
            }
            case 32768: {
                return "ACTION_PASTE";
            }
            case 65536: {
                return "ACTION_CUT";
            }
            case 131072: {
                return "ACTION_SET_SELECTION";
            }
        }

        return "ACTION_UNKNOWN";
    }

    public int getActions() {
        return this.mInfo.getActions();
    }

    public void getBoundsInParent(Rect arg2) {
        this.mInfo.getBoundsInParent(arg2);
    }

    public void getBoundsInScreen(Rect arg2) {
        this.mInfo.getBoundsInScreen(arg2);
    }

    public AccessibilityNodeInfoCompat getChild(int arg2) {
        return AccessibilityNodeInfoCompat.wrapNonNullInstance(this.mInfo.getChild(arg2));
    }

    public int getChildCount() {
        return this.mInfo.getChildCount();
    }

    public CharSequence getClassName() {
        return this.mInfo.getClassName();
    }

    public CollectionInfoCompat getCollectionInfo() {
        Object v0 = AccessibilityNodeInfoCompat.IMPL.getCollectionInfo(this.mInfo);
        if(v0 == null) {
            return null;
        }

        return new CollectionInfoCompat(v0);
    }

    public CollectionItemInfoCompat getCollectionItemInfo() {
        Object v0 = AccessibilityNodeInfoCompat.IMPL.getCollectionItemInfo(this.mInfo);
        if(v0 == null) {
            return null;
        }

        return new CollectionItemInfoCompat(v0);
    }

    public CharSequence getContentDescription() {
        return this.mInfo.getContentDescription();
    }

    public int getDrawingOrder() {
        return AccessibilityNodeInfoCompat.IMPL.getDrawingOrder(this.mInfo);
    }

    public CharSequence getError() {
        return AccessibilityNodeInfoCompat.IMPL.getError(this.mInfo);
    }

    public Bundle getExtras() {
        return AccessibilityNodeInfoCompat.IMPL.getExtras(this.mInfo);
    }

    @Deprecated public Object getInfo() {
        return this.mInfo;
    }

    public int getInputType() {
        return AccessibilityNodeInfoCompat.IMPL.getInputType(this.mInfo);
    }

    public AccessibilityNodeInfoCompat getLabelFor() {
        return AccessibilityNodeInfoCompat.wrapNonNullInstance(AccessibilityNodeInfoCompat.IMPL.getLabelFor(this.mInfo));
    }

    public AccessibilityNodeInfoCompat getLabeledBy() {
        return AccessibilityNodeInfoCompat.wrapNonNullInstance(AccessibilityNodeInfoCompat.IMPL.getLabeledBy(this.mInfo));
    }

    public int getLiveRegion() {
        return AccessibilityNodeInfoCompat.IMPL.getLiveRegion(this.mInfo);
    }

    public int getMaxTextLength() {
        return AccessibilityNodeInfoCompat.IMPL.getMaxTextLength(this.mInfo);
    }

    public int getMovementGranularities() {
        return AccessibilityNodeInfoCompat.IMPL.getMovementGranularities(this.mInfo);
    }

    public CharSequence getPackageName() {
        return this.mInfo.getPackageName();
    }

    public AccessibilityNodeInfoCompat getParent() {
        return AccessibilityNodeInfoCompat.wrapNonNullInstance(this.mInfo.getParent());
    }

    public RangeInfoCompat getRangeInfo() {
        Object v0 = AccessibilityNodeInfoCompat.IMPL.getRangeInfo(this.mInfo);
        if(v0 == null) {
            return null;
        }

        return new RangeInfoCompat(v0);
    }

    @Nullable public CharSequence getRoleDescription() {
        return AccessibilityNodeInfoCompat.IMPL.getRoleDescription(this.mInfo);
    }

    public CharSequence getText() {
        return this.mInfo.getText();
    }

    public int getTextSelectionEnd() {
        return AccessibilityNodeInfoCompat.IMPL.getTextSelectionEnd(this.mInfo);
    }

    public int getTextSelectionStart() {
        return AccessibilityNodeInfoCompat.IMPL.getTextSelectionStart(this.mInfo);
    }

    public AccessibilityNodeInfoCompat getTraversalAfter() {
        return AccessibilityNodeInfoCompat.wrapNonNullInstance(AccessibilityNodeInfoCompat.IMPL.getTraversalAfter(this.mInfo));
    }

    public AccessibilityNodeInfoCompat getTraversalBefore() {
        return AccessibilityNodeInfoCompat.wrapNonNullInstance(AccessibilityNodeInfoCompat.IMPL.getTraversalBefore(this.mInfo));
    }

    public String getViewIdResourceName() {
        return AccessibilityNodeInfoCompat.IMPL.getViewIdResourceName(this.mInfo);
    }

    public AccessibilityWindowInfoCompat getWindow() {
        return AccessibilityWindowInfoCompat.wrapNonNullInstance(AccessibilityNodeInfoCompat.IMPL.getWindow(this.mInfo));
    }

    public int getWindowId() {
        return this.mInfo.getWindowId();
    }

    public int hashCode() {
        int v0 = this.mInfo == null ? 0 : this.mInfo.hashCode();
        return v0;
    }

    public boolean isAccessibilityFocused() {
        return AccessibilityNodeInfoCompat.IMPL.isAccessibilityFocused(this.mInfo);
    }

    public boolean isCheckable() {
        return this.mInfo.isCheckable();
    }

    public boolean isChecked() {
        return this.mInfo.isChecked();
    }

    public boolean isClickable() {
        return this.mInfo.isClickable();
    }

    public boolean isContentInvalid() {
        return AccessibilityNodeInfoCompat.IMPL.isContentInvalid(this.mInfo);
    }

    public boolean isContextClickable() {
        return AccessibilityNodeInfoCompat.IMPL.isContextClickable(this.mInfo);
    }

    public boolean isDismissable() {
        return AccessibilityNodeInfoCompat.IMPL.isDismissable(this.mInfo);
    }

    public boolean isEditable() {
        return AccessibilityNodeInfoCompat.IMPL.isEditable(this.mInfo);
    }

    public boolean isEnabled() {
        return this.mInfo.isEnabled();
    }

    public boolean isFocusable() {
        return this.mInfo.isFocusable();
    }

    public boolean isFocused() {
        return this.mInfo.isFocused();
    }

    public boolean isImportantForAccessibility() {
        return AccessibilityNodeInfoCompat.IMPL.isImportantForAccessibility(this.mInfo);
    }

    public boolean isLongClickable() {
        return this.mInfo.isLongClickable();
    }

    public boolean isMultiLine() {
        return AccessibilityNodeInfoCompat.IMPL.isMultiLine(this.mInfo);
    }

    public boolean isPassword() {
        return this.mInfo.isPassword();
    }

    public boolean isScrollable() {
        return this.mInfo.isScrollable();
    }

    public boolean isSelected() {
        return this.mInfo.isSelected();
    }

    public boolean isVisibleToUser() {
        return AccessibilityNodeInfoCompat.IMPL.isVisibleToUser(this.mInfo);
    }

    public static AccessibilityNodeInfoCompat obtain() {
        return AccessibilityNodeInfoCompat.wrap(AccessibilityNodeInfo.obtain());
    }

    public static AccessibilityNodeInfoCompat obtain(AccessibilityNodeInfoCompat arg0) {
        return AccessibilityNodeInfoCompat.wrap(AccessibilityNodeInfo.obtain(arg0.mInfo));
    }

    public static AccessibilityNodeInfoCompat obtain(View arg0) {
        return AccessibilityNodeInfoCompat.wrap(AccessibilityNodeInfo.obtain(arg0));
    }

    public static AccessibilityNodeInfoCompat obtain(View arg1, int arg2) {
        return AccessibilityNodeInfoCompat.wrapNonNullInstance(AccessibilityNodeInfoCompat.IMPL.obtain(arg1, arg2));
    }

    public boolean performAction(int arg2) {
        return this.mInfo.performAction(arg2);
    }

    public boolean performAction(int arg3, Bundle arg4) {
        return AccessibilityNodeInfoCompat.IMPL.performAction(this.mInfo, arg3, arg4);
    }

    public void recycle() {
        this.mInfo.recycle();
    }

    public boolean refresh() {
        return AccessibilityNodeInfoCompat.IMPL.refresh(this.mInfo);
    }

    public boolean removeAction(AccessibilityActionCompat arg3) {
        return AccessibilityNodeInfoCompat.IMPL.removeAction(this.mInfo, arg3.mAction);
    }

    public boolean removeChild(View arg3) {
        return AccessibilityNodeInfoCompat.IMPL.removeChild(this.mInfo, arg3);
    }

    public boolean removeChild(View arg3, int arg4) {
        return AccessibilityNodeInfoCompat.IMPL.removeChild(this.mInfo, arg3, arg4);
    }

    public void setAccessibilityFocused(boolean arg3) {
        AccessibilityNodeInfoCompat.IMPL.setAccessibilityFocused(this.mInfo, arg3);
    }

    public void setBoundsInParent(Rect arg2) {
        this.mInfo.setBoundsInParent(arg2);
    }

    public void setBoundsInScreen(Rect arg2) {
        this.mInfo.setBoundsInScreen(arg2);
    }

    public void setCanOpenPopup(boolean arg3) {
        AccessibilityNodeInfoCompat.IMPL.setCanOpenPopup(this.mInfo, arg3);
    }

    public void setCheckable(boolean arg2) {
        this.mInfo.setCheckable(arg2);
    }

    public void setChecked(boolean arg2) {
        this.mInfo.setChecked(arg2);
    }

    public void setClassName(CharSequence arg2) {
        this.mInfo.setClassName(arg2);
    }

    public void setClickable(boolean arg2) {
        this.mInfo.setClickable(arg2);
    }

    public void setCollectionInfo(Object arg3) {
        AccessibilityNodeInfoCompat.IMPL.setCollectionInfo(this.mInfo, ((CollectionInfoCompat)arg3).mInfo);
    }

    public void setCollectionItemInfo(Object arg3) {
        AccessibilityNodeInfoCompat.IMPL.setCollectionItemInfo(this.mInfo, ((CollectionItemInfoCompat)arg3).mInfo);
    }

    public void setContentDescription(CharSequence arg2) {
        this.mInfo.setContentDescription(arg2);
    }

    public void setContentInvalid(boolean arg3) {
        AccessibilityNodeInfoCompat.IMPL.setContentInvalid(this.mInfo, arg3);
    }

    public void setContextClickable(boolean arg3) {
        AccessibilityNodeInfoCompat.IMPL.setContextClickable(this.mInfo, arg3);
    }

    public void setDismissable(boolean arg3) {
        AccessibilityNodeInfoCompat.IMPL.setDismissable(this.mInfo, arg3);
    }

    public void setDrawingOrder(int arg3) {
        AccessibilityNodeInfoCompat.IMPL.setDrawingOrder(this.mInfo, arg3);
    }

    public void setEditable(boolean arg3) {
        AccessibilityNodeInfoCompat.IMPL.setEditable(this.mInfo, arg3);
    }

    public void setEnabled(boolean arg2) {
        this.mInfo.setEnabled(arg2);
    }

    public void setError(CharSequence arg3) {
        AccessibilityNodeInfoCompat.IMPL.setError(this.mInfo, arg3);
    }

    public void setFocusable(boolean arg2) {
        this.mInfo.setFocusable(arg2);
    }

    public void setFocused(boolean arg2) {
        this.mInfo.setFocused(arg2);
    }

    public void setImportantForAccessibility(boolean arg3) {
        AccessibilityNodeInfoCompat.IMPL.setImportantForAccessibility(this.mInfo, arg3);
    }

    public void setInputType(int arg3) {
        AccessibilityNodeInfoCompat.IMPL.setInputType(this.mInfo, arg3);
    }

    public void setLabelFor(View arg3) {
        AccessibilityNodeInfoCompat.IMPL.setLabelFor(this.mInfo, arg3);
    }

    public void setLabelFor(View arg3, int arg4) {
        AccessibilityNodeInfoCompat.IMPL.setLabelFor(this.mInfo, arg3, arg4);
    }

    public void setLabeledBy(View arg3) {
        AccessibilityNodeInfoCompat.IMPL.setLabeledBy(this.mInfo, arg3);
    }

    public void setLabeledBy(View arg3, int arg4) {
        AccessibilityNodeInfoCompat.IMPL.setLabeledBy(this.mInfo, arg3, arg4);
    }

    public void setLiveRegion(int arg3) {
        AccessibilityNodeInfoCompat.IMPL.setLiveRegion(this.mInfo, arg3);
    }

    public void setLongClickable(boolean arg2) {
        this.mInfo.setLongClickable(arg2);
    }

    public void setMaxTextLength(int arg3) {
        AccessibilityNodeInfoCompat.IMPL.setMaxTextLength(this.mInfo, arg3);
    }

    public void setMovementGranularities(int arg3) {
        AccessibilityNodeInfoCompat.IMPL.setMovementGranularities(this.mInfo, arg3);
    }

    public void setMultiLine(boolean arg3) {
        AccessibilityNodeInfoCompat.IMPL.setMultiLine(this.mInfo, arg3);
    }

    public void setPackageName(CharSequence arg2) {
        this.mInfo.setPackageName(arg2);
    }

    public void setParent(View arg2) {
        this.mInfo.setParent(arg2);
    }

    public void setParent(View arg3, int arg4) {
        this.mParentVirtualDescendantId = arg4;
        AccessibilityNodeInfoCompat.IMPL.setParent(this.mInfo, arg3, arg4);
    }

    public void setPassword(boolean arg2) {
        this.mInfo.setPassword(arg2);
    }

    public void setRangeInfo(RangeInfoCompat arg3) {
        AccessibilityNodeInfoCompat.IMPL.setRangeInfo(this.mInfo, arg3.mInfo);
    }

    public void setRoleDescription(@Nullable CharSequence arg3) {
        AccessibilityNodeInfoCompat.IMPL.setRoleDescription(this.mInfo, arg3);
    }

    public void setScrollable(boolean arg2) {
        this.mInfo.setScrollable(arg2);
    }

    public void setSelected(boolean arg2) {
        this.mInfo.setSelected(arg2);
    }

    public void setSource(View arg2) {
        this.mInfo.setSource(arg2);
    }

    public void setSource(View arg3, int arg4) {
        AccessibilityNodeInfoCompat.IMPL.setSource(this.mInfo, arg3, arg4);
    }

    public void setText(CharSequence arg2) {
        this.mInfo.setText(arg2);
    }

    public void setTextSelection(int arg3, int arg4) {
        AccessibilityNodeInfoCompat.IMPL.setTextSelection(this.mInfo, arg3, arg4);
    }

    public void setTraversalAfter(View arg3) {
        AccessibilityNodeInfoCompat.IMPL.setTraversalAfter(this.mInfo, arg3);
    }

    public void setTraversalAfter(View arg3, int arg4) {
        AccessibilityNodeInfoCompat.IMPL.setTraversalAfter(this.mInfo, arg3, arg4);
    }

    public void setTraversalBefore(View arg3) {
        AccessibilityNodeInfoCompat.IMPL.setTraversalBefore(this.mInfo, arg3);
    }

    public void setTraversalBefore(View arg3, int arg4) {
        AccessibilityNodeInfoCompat.IMPL.setTraversalBefore(this.mInfo, arg3, arg4);
    }

    public void setViewIdResourceName(String arg3) {
        AccessibilityNodeInfoCompat.IMPL.setViewIdResourceName(this.mInfo, arg3);
    }

    public void setVisibleToUser(boolean arg3) {
        AccessibilityNodeInfoCompat.IMPL.setVisibleToUser(this.mInfo, arg3);
    }

    public String toString() {
        StringBuilder v0 = new StringBuilder();
        v0.append(super.toString());
        Rect v1 = new Rect();
        this.getBoundsInParent(v1);
        v0.append("; boundsInParent: " + v1);
        this.getBoundsInScreen(v1);
        v0.append("; boundsInScreen: " + v1);
        v0.append("; packageName: ");
        v0.append(this.getPackageName());
        v0.append("; className: ");
        v0.append(this.getClassName());
        v0.append("; text: ");
        v0.append(this.getText());
        v0.append("; contentDescription: ");
        v0.append(this.getContentDescription());
        v0.append("; viewId: ");
        v0.append(this.getViewIdResourceName());
        v0.append("; checkable: ");
        v0.append(this.isCheckable());
        v0.append("; checked: ");
        v0.append(this.isChecked());
        v0.append("; focusable: ");
        v0.append(this.isFocusable());
        v0.append("; focused: ");
        v0.append(this.isFocused());
        v0.append("; selected: ");
        v0.append(this.isSelected());
        v0.append("; clickable: ");
        v0.append(this.isClickable());
        v0.append("; longClickable: ");
        v0.append(this.isLongClickable());
        v0.append("; enabled: ");
        v0.append(this.isEnabled());
        v0.append("; password: ");
        v0.append(this.isPassword());
        v0.append("; scrollable: " + this.isScrollable());
        v0.append("; [");
        int v1_2 = this.getActions();
        while(v1_2 != 0) {
            int v2_1 = 1 << Integer.numberOfTrailingZeros(v1_2);
            v1_2 &= v2_1 ^ -1;
            v0.append(AccessibilityNodeInfoCompat.getActionSymbolicName(v2_1));
            if(v1_2 == 0) {
                continue;
            }

            v0.append(", ");
        }

        v0.append("]");
        return v0.toString();
    }

    public AccessibilityNodeInfo unwrap() {
        return this.mInfo;
    }

    public static AccessibilityNodeInfoCompat wrap(@NonNull AccessibilityNodeInfo arg1) {
        return new AccessibilityNodeInfoCompat(arg1);
    }

    static AccessibilityNodeInfoCompat wrapNonNullInstance(Object arg1) {
        if(arg1 != null) {
            return new AccessibilityNodeInfoCompat(arg1);
        }

        return null;
    }
}

