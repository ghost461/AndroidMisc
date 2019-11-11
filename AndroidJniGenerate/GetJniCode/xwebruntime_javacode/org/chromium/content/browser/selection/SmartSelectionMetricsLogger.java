package org.chromium.content.browser.selection;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build$VERSION;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.chromium.base.VisibleForTesting;
import org.chromium.content_public.browser.SelectionClient$Result;
import org.chromium.content_public.browser.SelectionMetricsLogger;

@TargetApi(value=26) public class SmartSelectionMetricsLogger implements SelectionMetricsLogger {
    @Retention(value=RetentionPolicy.SOURCE) @public interface ActionType {
        public static final int ABANDON = 107;
        public static final int COPY = 101;
        public static final int CUT = 103;
        public static final int DRAG = 106;
        public static final int OTHER = 108;
        public static final int OVERTYPE = 100;
        public static final int PASTE = 102;
        public static final int RESET = 201;
        public static final int SELECT_ALL = 200;
        public static final int SHARE = 104;
        public static final int SMART_SHARE = 105;

    }

    public interface SelectionEventProxy {
        Object createSelectionAction(int arg1, int arg2, int arg3, Object arg4);

        Object createSelectionAction(int arg1, int arg2, int arg3);

        Object createSelectionModified(int arg1, int arg2);

        Object createSelectionModifiedClassification(int arg1, int arg2, Object arg3);

        Object createSelectionModifiedSelection(int arg1, int arg2, Object arg3);

        Object createSelectionStarted(int arg1);
    }

    private static final boolean DEBUG = false;
    private static final String TAG = "SmartSelectionLogger";
    private static final String TRACKER_CLASS = "android.view.textclassifier.logging.SmartSelectionEventTracker";
    private Context mContext;
    private SelectionIndicesConverter mConverter;
    private SelectionEventProxy mSelectionEventProxy;
    private Object mTracker;
    private static Method sLogEventMethod;
    private static boolean sReflectionFailed;
    private static Class sSelectionEventClass;
    private static Class sTrackerClass;
    private static Constructor sTrackerConstructor;

    private SmartSelectionMetricsLogger(Context arg1, SelectionEventProxy arg2) {
        super();
        this.mContext = arg1;
        this.mSelectionEventProxy = arg2;
    }

    @VisibleForTesting protected SmartSelectionMetricsLogger(SelectionEventProxy arg1) {
        super();
        this.mSelectionEventProxy = arg1;
    }

    public static SmartSelectionMetricsLogger create(Context arg7) {
        SmartSelectionMetricsLogger v1 = null;
        if(Build$VERSION.SDK_INT > 26 && arg7 != null) {
            if(SmartSelectionMetricsLogger.sReflectionFailed) {
            }
            else {
                if(SmartSelectionMetricsLogger.sTrackerClass == null) {
                    try {
                        SmartSelectionMetricsLogger.sTrackerClass = Class.forName("android.view.textclassifier.logging.SmartSelectionEventTracker");
                        SmartSelectionMetricsLogger.sSelectionEventClass = Class.forName("android.view.textclassifier.logging.SmartSelectionEventTracker$SelectionEvent");
                        SmartSelectionMetricsLogger.sTrackerConstructor = SmartSelectionMetricsLogger.sTrackerClass.getConstructor(Context.class, Integer.TYPE);
                        SmartSelectionMetricsLogger.sLogEventMethod = SmartSelectionMetricsLogger.sTrackerClass.getMethod("logEvent", SmartSelectionMetricsLogger.sSelectionEventClass);
                    }
                    catch(NoSuchMethodException ) {
                        SmartSelectionMetricsLogger.sReflectionFailed = true;
                        return v1;
                    }
                }

                SelectionEventProxyImpl v0 = SelectionEventProxyImpl.create();
                if(v0 == null) {
                    return v1;
                }

                return new SmartSelectionMetricsLogger(arg7, ((SelectionEventProxy)v0));
            }
        }

        return v1;
    }

    public Object createTracker(Context arg5, boolean arg6) {
        try {
            Constructor v0 = SmartSelectionMetricsLogger.sTrackerConstructor;
            int v1 = 2;
            Object[] v2 = new Object[v1];
            v2[0] = arg5;
            if(arg6) {
                v1 = 4;
            }

            v2[1] = Integer.valueOf(v1);
            return v0.newInstance(v2);
        }
        catch(ReflectiveOperationException ) {
            return null;
        }
    }

    public static boolean isTerminal(int arg0) {
        switch(arg0) {
            case 100: 
            case 101: 
            case 102: 
            case 103: 
            case 104: 
            case 105: 
            case 106: 
            case 107: 
            case 108: {
                return 1;
            }
        }

        return 0;
    }

    public void logEvent(Object arg6) {
        if(arg6 == null) {
            return;
        }

        try {
            SmartSelectionMetricsLogger.sLogEventMethod.invoke(this.mTracker, SmartSelectionMetricsLogger.sSelectionEventClass.cast(arg6));
            return;
        }
        catch(ReflectiveOperationException ) {
            return;
        }
    }

    public void logSelectionAction(String arg4, int arg5, int arg6, Result arg7) {
        if(this.mTracker == null) {
            return;
        }

        Object v1 = null;
        if(!this.mConverter.updateSelectionState(arg4, arg5)) {
            this.mTracker = v1;
            return;
        }

        int v4 = arg4.length() + arg5;
        int[] v0 = new int[2];
        if(!this.mConverter.getWordDelta(arg5, v4, v0)) {
            this.mTracker = v1;
            return;
        }

        if(arg7 == null || arg7.textClassification == null) {
            this.logEvent(this.mSelectionEventProxy.createSelectionAction(v0[0], v0[1], arg6));
        }
        else {
            this.logEvent(this.mSelectionEventProxy.createSelectionAction(v0[0], v0[1], arg6, arg7.textClassification));
        }

        if(SmartSelectionMetricsLogger.isTerminal(arg6)) {
            this.mTracker = v1;
        }
    }

    public void logSelectionModified(String arg4, int arg5, Result arg6) {
        if(this.mTracker == null) {
            return;
        }

        Object v1 = null;
        if(!this.mConverter.updateSelectionState(arg4, arg5)) {
            this.mTracker = v1;
            return;
        }

        int v4 = arg4.length() + arg5;
        int[] v0 = new int[2];
        if(!this.mConverter.getWordDelta(arg5, v4, v0)) {
            this.mTracker = v1;
            return;
        }

        if(arg6 == null || arg6.textSelection == null) {
            if(arg6 != null && arg6.textClassification != null) {
                this.logEvent(this.mSelectionEventProxy.createSelectionModifiedClassification(v0[0], v0[1], arg6.textClassification));
                return;
            }

            this.logEvent(this.mSelectionEventProxy.createSelectionModified(v0[0], v0[1]));
        }
        else {
            this.logEvent(this.mSelectionEventProxy.createSelectionModifiedSelection(v0[0], v0[1], arg6.textSelection));
        }
    }

    public void logSelectionStarted(String arg2, int arg3, boolean arg4) {
        this.mTracker = this.createTracker(this.mContext, arg4);
        this.mConverter = new SelectionIndicesConverter();
        this.mConverter.updateSelectionState(arg2, arg3);
        this.mConverter.setInitialStartOffset(arg3);
        this.logEvent(this.mSelectionEventProxy.createSelectionStarted(0));
    }
}

