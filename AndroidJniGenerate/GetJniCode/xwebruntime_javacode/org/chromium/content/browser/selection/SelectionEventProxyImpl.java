package org.chromium.content.browser.selection;

import android.annotation.TargetApi;
import android.support.annotation.NonNull;
import android.view.textclassifier.TextClassification;
import android.view.textclassifier.TextSelection;
import java.lang.reflect.Method;

@TargetApi(value=26) public class SelectionEventProxyImpl implements SelectionEventProxy {
    private static final boolean DEBUG = false;
    private static final String SELECTION_EVENT_CLASS = "android.view.textclassifier.logging.SmartSelectionEventTracker$SelectionEvent";
    private static final String TAG = "SmartSelectionLogger";
    private static boolean sReflectionFailed = false;
    private static Method sSelectionActionClassificationMethod;
    private static Method sSelectionActionMethod;
    private static Class sSelectionEventClass;
    private static Method sSelectionModifiedClassificationMethod;
    private static Method sSelectionModifiedMethod;
    private static Method sSelectionModifiedSelectionMethod;
    private static Method sSelectionStartedMethod;

    static {
    }

    private SelectionEventProxyImpl() {
        super();
    }

    public static SelectionEventProxyImpl create() {
        SelectionEventProxyImpl v1 = null;
        if(SelectionEventProxyImpl.sReflectionFailed) {
            return v1;
        }

        if(SelectionEventProxyImpl.sSelectionEventClass == null) {
            try {
                SelectionEventProxyImpl.sSelectionEventClass = Class.forName("android.view.textclassifier.logging.SmartSelectionEventTracker$SelectionEvent");
                SelectionEventProxyImpl.sSelectionStartedMethod = SelectionEventProxyImpl.sSelectionEventClass.getMethod("selectionStarted", Integer.TYPE);
                SelectionEventProxyImpl.sSelectionModifiedMethod = SelectionEventProxyImpl.sSelectionEventClass.getMethod("selectionModified", Integer.TYPE, Integer.TYPE);
                SelectionEventProxyImpl.sSelectionModifiedClassificationMethod = SelectionEventProxyImpl.sSelectionEventClass.getMethod("selectionModified", Integer.TYPE, Integer.TYPE, TextClassification.class);
                SelectionEventProxyImpl.sSelectionModifiedSelectionMethod = SelectionEventProxyImpl.sSelectionEventClass.getMethod("selectionModified", Integer.TYPE, Integer.TYPE, TextSelection.class);
                SelectionEventProxyImpl.sSelectionActionMethod = SelectionEventProxyImpl.sSelectionEventClass.getMethod("selectionAction", Integer.TYPE, Integer.TYPE, Integer.TYPE);
                SelectionEventProxyImpl.sSelectionActionClassificationMethod = SelectionEventProxyImpl.sSelectionEventClass.getMethod("selectionAction", Integer.TYPE, Integer.TYPE, Integer.TYPE, TextClassification.class);
            }
            catch(NoSuchMethodException ) {
                SelectionEventProxyImpl.sReflectionFailed = true;
                return v1;
            }
        }

        return new SelectionEventProxyImpl();
    }

    public Object createSelectionAction(int arg5, int arg6, int arg7) {
        Object v0 = null;
        try {
            return SelectionEventProxyImpl.sSelectionActionMethod.invoke(v0, Integer.valueOf(arg5), Integer.valueOf(arg6), Integer.valueOf(arg7));
        }
        catch(ReflectiveOperationException ) {
            return v0;
        }
    }

    public Object createSelectionAction(int arg5, int arg6, int arg7, @NonNull Object arg8) {
        Object v0 = null;
        try {
            return SelectionEventProxyImpl.sSelectionActionClassificationMethod.invoke(v0, Integer.valueOf(arg5), Integer.valueOf(arg6), Integer.valueOf(arg7), arg8);
        }
        catch(ReflectiveOperationException ) {
            return v0;
        }
    }

    public Object createSelectionModified(int arg5, int arg6) {
        Object v0 = null;
        try {
            return SelectionEventProxyImpl.sSelectionModifiedMethod.invoke(v0, Integer.valueOf(arg5), Integer.valueOf(arg6));
        }
        catch(ReflectiveOperationException ) {
            return v0;
        }
    }

    public Object createSelectionModifiedClassification(int arg5, int arg6, @NonNull Object arg7) {
        Object v0 = null;
        try {
            return SelectionEventProxyImpl.sSelectionModifiedClassificationMethod.invoke(v0, Integer.valueOf(arg5), Integer.valueOf(arg6), arg7);
        }
        catch(ReflectiveOperationException ) {
            return v0;
        }
    }

    public Object createSelectionModifiedSelection(int arg5, int arg6, @NonNull Object arg7) {
        Object v0 = null;
        try {
            return SelectionEventProxyImpl.sSelectionModifiedSelectionMethod.invoke(v0, Integer.valueOf(arg5), Integer.valueOf(arg6), arg7);
        }
        catch(ReflectiveOperationException ) {
            return v0;
        }
    }

    public Object createSelectionStarted(int arg5) {
        Object v0 = null;
        try {
            return SelectionEventProxyImpl.sSelectionStartedMethod.invoke(v0, Integer.valueOf(arg5));
        }
        catch(ReflectiveOperationException ) {
            return v0;
        }
    }
}

