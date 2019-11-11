package android.support.v7.widget;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.database.DataSetObservable;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

class ActivityChooserModel extends DataSetObservable {
    public interface ActivityChooserModelClient {
        void setActivityChooserModel(ActivityChooserModel arg1);
    }

    public final class ActivityResolveInfo implements Comparable {
        public final ResolveInfo resolveInfo;
        public float weight;

        public ActivityResolveInfo(ResolveInfo arg1) {
            super();
            this.resolveInfo = arg1;
        }

        public int compareTo(ActivityResolveInfo arg2) {
            return Float.floatToIntBits(arg2.weight) - Float.floatToIntBits(this.weight);
        }

        public int compareTo(Object arg1) {
            return this.compareTo(((ActivityResolveInfo)arg1));
        }

        public boolean equals(Object arg5) {
            if(this == (((ActivityResolveInfo)arg5))) {
                return 1;
            }

            if(arg5 == null) {
                return 0;
            }

            if(this.getClass() != arg5.getClass()) {
                return 0;
            }

            if(Float.floatToIntBits(this.weight) != Float.floatToIntBits(((ActivityResolveInfo)arg5).weight)) {
                return 0;
            }

            return 1;
        }

        public int hashCode() {
            return Float.floatToIntBits(this.weight) + 0x1F;
        }

        public String toString() {
            return "[" + "resolveInfo:" + this.resolveInfo.toString() + "; weight:" + new BigDecimal(((double)this.weight)) + "]";
        }
    }

    public interface ActivitySorter {
        void sort(Intent arg1, List arg2, List arg3);
    }

    final class DefaultSorter implements ActivitySorter {
        private static final float WEIGHT_DECAY_COEFFICIENT = 0.95f;
        private final Map mPackageNameToActivityMap;

        DefaultSorter() {
            super();
            this.mPackageNameToActivityMap = new HashMap();
        }

        public void sort(Intent arg7, List arg8, List arg9) {
            Object v2;
            Map v7 = this.mPackageNameToActivityMap;
            v7.clear();
            int v0 = arg8.size();
            int v1;
            for(v1 = 0; v1 < v0; ++v1) {
                v2 = arg8.get(v1);
                ((ActivityResolveInfo)v2).weight = 0f;
                v7.put(new ComponentName(((ActivityResolveInfo)v2).resolveInfo.activityInfo.packageName, ((ActivityResolveInfo)v2).resolveInfo.activityInfo.name), v2);
            }

            v0 = arg9.size() - 1;
            float v1_1 = 1f;
            while(v0 >= 0) {
                v2 = arg9.get(v0);
                Object v3 = v7.get(((HistoricalRecord)v2).activity);
                if(v3 != null) {
                    ((ActivityResolveInfo)v3).weight += ((HistoricalRecord)v2).weight * v1_1;
                    v1_1 *= 0.95f;
                }

                --v0;
            }

            Collections.sort(arg8);
        }
    }

    public final class HistoricalRecord {
        public final ComponentName activity;
        public final long time;
        public final float weight;

        public HistoricalRecord(String arg1, long arg2, float arg4) {
            this(ComponentName.unflattenFromString(arg1), arg2, arg4);
        }

        public HistoricalRecord(ComponentName arg1, long arg2, float arg4) {
            super();
            this.activity = arg1;
            this.time = arg2;
            this.weight = arg4;
        }

        public boolean equals(Object arg8) {
            if(this == (((HistoricalRecord)arg8))) {
                return 1;
            }

            if(arg8 == null) {
                return 0;
            }

            if(this.getClass() != arg8.getClass()) {
                return 0;
            }

            if(this.activity == null) {
                if(((HistoricalRecord)arg8).activity != null) {
                    return 0;
                }
            }
            else if(!this.activity.equals(((HistoricalRecord)arg8).activity)) {
                return 0;
            }

            if(this.time != ((HistoricalRecord)arg8).time) {
                return 0;
            }

            if(Float.floatToIntBits(this.weight) != Float.floatToIntBits(((HistoricalRecord)arg8).weight)) {
                return 0;
            }

            return 1;
        }

        public int hashCode() {
            int v0 = this.activity == null ? 0 : this.activity.hashCode();
            return ((v0 + 0x1F) * 0x1F + (((int)(this.time ^ this.time >>> 0x20)))) * 0x1F + Float.floatToIntBits(this.weight);
        }

        public String toString() {
            return "[" + "; activity:" + this.activity + "; time:" + this.time + "; weight:" + new BigDecimal(((double)this.weight)) + "]";
        }
    }

    public interface OnChooseActivityListener {
        boolean onChooseActivity(ActivityChooserModel arg1, Intent arg2);
    }

    final class PersistHistoryAsyncTask extends AsyncTask {
        PersistHistoryAsyncTask(ActivityChooserModel arg1) {
            ActivityChooserModel.this = arg1;
            super();
        }

        public Object doInBackground(Object[] arg1) {
            return this.doInBackground(arg1);
        }

        public Void doInBackground(Object[] arg12) {
            String v0_1;
            FileOutputStream v4;
            Object v1 = arg12[0];
            Object v12 = arg12[1];
            String v3 = null;
            try {
                v4 = ActivityChooserModel.this.mContext.openFileOutput(((String)v12), 0);
            }
            catch(FileNotFoundException v0) {
                String v1_1 = ActivityChooserModel.LOG_TAG;
                Log.e(v1_1, "Error writing historical record file: " + (((String)v12)), ((Throwable)v0));
                return ((Void)v3);
            }

            XmlSerializer v12_1 = Xml.newSerializer();
            try {
                v12_1.setOutput(((OutputStream)v4), v3);
                v12_1.startDocument("UTF-8", Boolean.valueOf(true));
                v12_1.startTag(v3, "historical-records");
                int v5 = ((List)v1).size();
                int v6;
                for(v6 = 0; v6 < v5; ++v6) {
                    Object v7 = ((List)v1).remove(0);
                    v12_1.startTag(v3, "historical-record");
                    v12_1.attribute(v3, "activity", ((HistoricalRecord)v7).activity.flattenToString());
                    v12_1.attribute(v3, "time", String.valueOf(((HistoricalRecord)v7).time));
                    v12_1.attribute(v3, "weight", String.valueOf(((HistoricalRecord)v7).weight));
                    v12_1.endTag(v3, "historical-record");
                }

                v12_1.endTag(v3, "historical-records");
                v12_1.endDocument();
            }
            catch(IllegalArgumentException v12_2) {
                goto label_78;
            }
            catch(Throwable v12_3) {
                goto label_93;
            }
            catch(IllegalStateException v12_4) {
                goto label_63;
            }
            catch(IOException v12_5) {
                goto label_48;
            }

            ActivityChooserModel.this.mCanReadHistoricalData = true;
            if(v4 == null) {
                goto label_92;
            }

            goto label_43;
            try {
            label_78:
                v0_1 = ActivityChooserModel.LOG_TAG;
                Log.e(v0_1, "Error writing historical record file: " + ActivityChooserModel.this.mHistoryFileName, ((Throwable)v12_2));
            }
            catch(Throwable v12_3) {
                goto label_93;
            }

            ActivityChooserModel.this.mCanReadHistoricalData = true;
            if(v4 == null) {
                goto label_92;
            }

            goto label_43;
            try {
            label_63:
                v0_1 = ActivityChooserModel.LOG_TAG;
                Log.e(v0_1, "Error writing historical record file: " + ActivityChooserModel.this.mHistoryFileName, ((Throwable)v12_4));
            }
            catch(Throwable v12_3) {
                goto label_93;
            }

            ActivityChooserModel.this.mCanReadHistoricalData = true;
            if(v4 == null) {
                goto label_92;
            }

            goto label_43;
            try {
            label_48:
                v0_1 = ActivityChooserModel.LOG_TAG;
                Log.e(v0_1, "Error writing historical record file: " + ActivityChooserModel.this.mHistoryFileName, ((Throwable)v12_5));
            }
            catch(Throwable v12_3) {
                goto label_93;
            }

            ActivityChooserModel.this.mCanReadHistoricalData = true;
            if(v4 == null) {
                goto label_92;
            }

            try {
            label_43:
                v4.close();
                goto label_92;
            }
            catch(IOException ) {
            label_92:
                return ((Void)v3);
            }

        label_93:
            ActivityChooserModel.this.mCanReadHistoricalData = true;
            if(v4 != null) {
                try {
                    v4.close();
                    goto label_97;
                }
                catch(IOException ) {
                label_97:
                    throw v12_3;
                }
            }

            goto label_97;
        }
    }

    static final String ATTRIBUTE_ACTIVITY = "activity";
    static final String ATTRIBUTE_TIME = "time";
    static final String ATTRIBUTE_WEIGHT = "weight";
    static final boolean DEBUG = false;
    private static final int DEFAULT_ACTIVITY_INFLATION = 5;
    private static final float DEFAULT_HISTORICAL_RECORD_WEIGHT = 1f;
    public static final String DEFAULT_HISTORY_FILE_NAME = "activity_choser_model_history.xml";
    public static final int DEFAULT_HISTORY_MAX_LENGTH = 50;
    private static final String HISTORY_FILE_EXTENSION = ".xml";
    private static final int INVALID_INDEX = -1;
    static final String LOG_TAG = "ActivityChooserModel";
    static final String TAG_HISTORICAL_RECORD = "historical-record";
    static final String TAG_HISTORICAL_RECORDS = "historical-records";
    private final List mActivities;
    private OnChooseActivityListener mActivityChoserModelPolicy;
    private ActivitySorter mActivitySorter;
    boolean mCanReadHistoricalData;
    final Context mContext;
    private final List mHistoricalRecords;
    private boolean mHistoricalRecordsChanged;
    final String mHistoryFileName;
    private int mHistoryMaxSize;
    private final Object mInstanceLock;
    private Intent mIntent;
    private boolean mReadShareHistoryCalled;
    private boolean mReloadActivities;
    private static final Map sDataModelRegistry;
    private static final Object sRegistryLock;

    static {
        ActivityChooserModel.sRegistryLock = new Object();
        ActivityChooserModel.sDataModelRegistry = new HashMap();
    }

    private ActivityChooserModel(Context arg3, String arg4) {
        super();
        this.mInstanceLock = new Object();
        this.mActivities = new ArrayList();
        this.mHistoricalRecords = new ArrayList();
        this.mActivitySorter = new DefaultSorter();
        this.mHistoryMaxSize = 50;
        this.mCanReadHistoricalData = true;
        this.mReadShareHistoryCalled = false;
        this.mHistoricalRecordsChanged = true;
        this.mReloadActivities = false;
        this.mContext = arg3.getApplicationContext();
        this.mHistoryFileName = (TextUtils.isEmpty(((CharSequence)arg4))) || (arg4.endsWith(".xml")) ? arg4 : arg4 + ".xml";
    }

    private boolean addHistoricalRecord(HistoricalRecord arg2) {
        boolean v2 = this.mHistoricalRecords.add(arg2);
        if(v2) {
            this.mHistoricalRecordsChanged = true;
            this.pruneExcessiveHistoricalRecordsIfNeeded();
            this.persistHistoricalDataIfNeeded();
            this.sortActivitiesIfNeeded();
            this.notifyChanged();
        }

        return v2;
    }

    public Intent chooseActivity(int arg7) {
        Object v0 = this.mInstanceLock;
        __monitor_enter(v0);
        try {
            Intent v2 = null;
            if(this.mIntent == null) {
                __monitor_exit(v0);
                return v2;
            }

            this.ensureConsistentState();
            Object v7_1 = this.mActivities.get(arg7);
            ComponentName v1 = new ComponentName(((ActivityResolveInfo)v7_1).resolveInfo.activityInfo.packageName, ((ActivityResolveInfo)v7_1).resolveInfo.activityInfo.name);
            Intent v7_2 = new Intent(this.mIntent);
            v7_2.setComponent(v1);
            if(this.mActivityChoserModelPolicy != null && (this.mActivityChoserModelPolicy.onChooseActivity(this, new Intent(v7_2)))) {
                __monitor_exit(v0);
                return v2;
            }

            this.addHistoricalRecord(new HistoricalRecord(v1, System.currentTimeMillis(), 1f));
            __monitor_exit(v0);
            return v7_2;
        label_39:
            __monitor_exit(v0);
        }
        catch(Throwable v7) {
            goto label_39;
        }

        throw v7;
    }

    private void ensureConsistentState() {
        int v0 = this.loadActivitiesIfNeeded() | this.readHistoricalDataIfNeeded();
        this.pruneExcessiveHistoricalRecordsIfNeeded();
        if(v0 != 0) {
            this.sortActivitiesIfNeeded();
            this.notifyChanged();
        }
    }

    public static ActivityChooserModel get(Context arg2, String arg3) {
        ActivityChooserModel v1_1;
        Object v0 = ActivityChooserModel.sRegistryLock;
        __monitor_enter(v0);
        try {
            Object v1 = ActivityChooserModel.sDataModelRegistry.get(arg3);
            if(v1 == null) {
                v1_1 = new ActivityChooserModel(arg2, arg3);
                ActivityChooserModel.sDataModelRegistry.put(arg3, v1_1);
            }

            __monitor_exit(v0);
            return v1_1;
        label_12:
            __monitor_exit(v0);
        }
        catch(Throwable v2) {
            goto label_12;
        }

        throw v2;
    }

    public ResolveInfo getActivity(int arg3) {
        Object v0 = this.mInstanceLock;
        __monitor_enter(v0);
        try {
            this.ensureConsistentState();
            __monitor_exit(v0);
            return this.mActivities.get(arg3).resolveInfo;
        label_9:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_9;
        }

        throw v3;
    }

    public int getActivityCount() {
        Object v0 = this.mInstanceLock;
        __monitor_enter(v0);
        try {
            this.ensureConsistentState();
            __monitor_exit(v0);
            return this.mActivities.size();
        label_8:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_8;
        }

        throw v1;
    }

    public int getActivityIndex(ResolveInfo arg6) {
        Object v0 = this.mInstanceLock;
        __monitor_enter(v0);
        try {
            this.ensureConsistentState();
            List v1 = this.mActivities;
            int v2 = v1.size();
            int v3;
            for(v3 = 0; v3 < v2; ++v3) {
                if(v1.get(v3).resolveInfo == arg6) {
                    __monitor_exit(v0);
                    return v3;
                }
            }

            __monitor_exit(v0);
            return -1;
        label_18:
            __monitor_exit(v0);
        }
        catch(Throwable v6) {
            goto label_18;
        }

        throw v6;
    }

    public ResolveInfo getDefaultActivity() {
        Object v0 = this.mInstanceLock;
        __monitor_enter(v0);
        try {
            this.ensureConsistentState();
            if(!this.mActivities.isEmpty()) {
                __monitor_exit(v0);
                return this.mActivities.get(0).resolveInfo;
            }

            __monitor_exit(v0);
            ResolveInfo v0_1 = null;
            return v0_1;
        label_16:
            __monitor_exit(v0_1);
        }
        catch(Throwable v1) {
            goto label_16;
        }

        throw v1;
    }

    public int getHistoryMaxSize() {
        Object v0 = this.mInstanceLock;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return this.mHistoryMaxSize;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_6;
        }

        throw v1;
    }

    public int getHistorySize() {
        Object v0 = this.mInstanceLock;
        __monitor_enter(v0);
        try {
            this.ensureConsistentState();
            __monitor_exit(v0);
            return this.mHistoricalRecords.size();
        label_8:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_8;
        }

        throw v1;
    }

    public Intent getIntent() {
        Object v0 = this.mInstanceLock;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return this.mIntent;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_6;
        }

        throw v1;
    }

    private boolean loadActivitiesIfNeeded() {
        int v1 = 0;
        if((this.mReloadActivities) && this.mIntent != null) {
            this.mReloadActivities = false;
            this.mActivities.clear();
            List v0 = this.mContext.getPackageManager().queryIntentActivities(this.mIntent, 0);
            int v2 = v0.size();
            while(v1 < v2) {
                this.mActivities.add(new ActivityResolveInfo(v0.get(v1)));
                ++v1;
            }

            return 1;
        }

        return 0;
    }

    private void persistHistoricalDataIfNeeded() {
        if(!this.mReadShareHistoryCalled) {
            throw new IllegalStateException("No preceding call to #readHistoricalData");
        }

        if(!this.mHistoricalRecordsChanged) {
            return;
        }

        this.mHistoricalRecordsChanged = false;
        if(!TextUtils.isEmpty(this.mHistoryFileName)) {
            new PersistHistoryAsyncTask(this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[]{new ArrayList(this.mHistoricalRecords), this.mHistoryFileName});
        }
    }

    private void pruneExcessiveHistoricalRecordsIfNeeded() {
        int v0 = this.mHistoricalRecords.size() - this.mHistoryMaxSize;
        if(v0 <= 0) {
            return;
        }

        this.mHistoricalRecordsChanged = true;
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            this.mHistoricalRecords.remove(0);
        }
    }

    private boolean readHistoricalDataIfNeeded() {
        if((this.mCanReadHistoricalData) && (this.mHistoricalRecordsChanged) && !TextUtils.isEmpty(this.mHistoryFileName)) {
            this.mCanReadHistoricalData = false;
            this.mReadShareHistoryCalled = true;
            this.readHistoricalDataImpl();
            return 1;
        }

        return 0;
    }

    private void readHistoricalDataImpl() {
        String v2_2;
        List v2_1;
        XmlPullParser v1_3;
        FileInputStream v0;
        try {
            v0 = this.mContext.openFileInput(this.mHistoryFileName);
        }
        catch(FileNotFoundException ) {
            return;
        }

        try {
            v1_3 = Xml.newPullParser();
            v1_3.setInput(((InputStream)v0), "UTF-8");
            int v2;
            for(v2 = 0; v2 != 1; v2 = v1_3.next()) {
                if(v2 == 2) {
                    break;
                }
            }

            if(!"historical-records".equals(v1_3.getName())) {
                throw new XmlPullParserException("Share records file does not start with historical-records tag.");
            }

            v2_1 = this.mHistoricalRecords;
            v2_1.clear();
            while(true) {
            label_23:
                int v4 = v1_3.next();
                if(v4 != 1) {
                    break;
                }

                goto label_25;
            }
        }
        catch(XmlPullParserException v1) {
            goto label_69;
        }
        catch(Throwable v1_1) {
            goto label_81;
        }
        catch(IOException v1_2) {
            goto label_57;
        }

        if(v4 == 3) {
            goto label_23;
        }

        if(v4 == 4) {
            goto label_23;
        }

        try {
            if(!"historical-record".equals(v1_3.getName())) {
                throw new XmlPullParserException("Share records file not well-formed.");
            }

            v2_1.add(new HistoricalRecord(v1_3.getAttributeValue(null, "activity"), Long.parseLong(v1_3.getAttributeValue(null, "time")), Float.parseFloat(v1_3.getAttributeValue(null, "weight"))));
            goto label_23;
        }
        catch(XmlPullParserException v1) {
            goto label_69;
        }
        catch(Throwable v1_1) {
            goto label_81;
        }
        catch(IOException v1_2) {
            goto label_57;
        }

    label_25:
        if(v0 == null) {
            return;
        }

        goto label_26;
        try {
        label_69:
            v2_2 = ActivityChooserModel.LOG_TAG;
            Log.e(v2_2, "Error reading historical recrod file: " + this.mHistoryFileName, ((Throwable)v1));
            if(v0 == null) {
                return;
            }
        }
        catch(Throwable v1_1) {
            goto label_81;
        }

        goto label_26;
        try {
        label_57:
            v2_2 = ActivityChooserModel.LOG_TAG;
            Log.e(v2_2, "Error reading historical recrod file: " + this.mHistoryFileName, ((Throwable)v1_2));
            if(v0 == null) {
                return;
            }
        }
        catch(Throwable v1_1) {
            goto label_81;
        }

        try {
        label_26:
            v0.close();
            return;
        }
        catch(IOException ) {
            return;
        }

    label_81:
        if(v0 != null) {
            try {
                v0.close();
                goto label_83;
            }
            catch(IOException ) {
            label_83:
                throw v1_1;
            }
        }

        goto label_83;
    }

    public void setActivitySorter(ActivitySorter arg3) {
        Object v0 = this.mInstanceLock;
        __monitor_enter(v0);
        try {
            if(this.mActivitySorter == arg3) {
                __monitor_exit(v0);
                return;
            }

            this.mActivitySorter = arg3;
            if(this.sortActivitiesIfNeeded()) {
                this.notifyChanged();
            }

            __monitor_exit(v0);
            return;
        label_13:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_13;
        }

        throw v3;
    }

    public void setDefaultActivity(int arg6) {
        Object v0 = this.mInstanceLock;
        __monitor_enter(v0);
        try {
            this.ensureConsistentState();
            Object v6_1 = this.mActivities.get(arg6);
            Object v1 = this.mActivities.get(0);
            float v1_1 = v1 != null ? ((ActivityResolveInfo)v1).weight - ((ActivityResolveInfo)v6_1).weight + 5f : 1f;
            this.addHistoricalRecord(new HistoricalRecord(new ComponentName(((ActivityResolveInfo)v6_1).resolveInfo.activityInfo.packageName, ((ActivityResolveInfo)v6_1).resolveInfo.activityInfo.name), System.currentTimeMillis(), v1_1));
            __monitor_exit(v0);
            return;
        label_31:
            __monitor_exit(v0);
        }
        catch(Throwable v6) {
            goto label_31;
        }

        throw v6;
    }

    public void setHistoryMaxSize(int arg3) {
        Object v0 = this.mInstanceLock;
        __monitor_enter(v0);
        try {
            if(this.mHistoryMaxSize == arg3) {
                __monitor_exit(v0);
                return;
            }

            this.mHistoryMaxSize = arg3;
            this.pruneExcessiveHistoricalRecordsIfNeeded();
            if(this.sortActivitiesIfNeeded()) {
                this.notifyChanged();
            }

            __monitor_exit(v0);
            return;
        label_14:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_14;
        }

        throw v3;
    }

    public void setIntent(Intent arg3) {
        Object v0 = this.mInstanceLock;
        __monitor_enter(v0);
        try {
            if(this.mIntent == arg3) {
                __monitor_exit(v0);
                return;
            }

            this.mIntent = arg3;
            this.mReloadActivities = true;
            this.ensureConsistentState();
            __monitor_exit(v0);
            return;
        label_13:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_13;
        }

        throw v3;
    }

    public void setOnChooseActivityListener(OnChooseActivityListener arg2) {
        Object v0 = this.mInstanceLock;
        __monitor_enter(v0);
        try {
            this.mActivityChoserModelPolicy = arg2;
            __monitor_exit(v0);
            return;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v2) {
            goto label_6;
        }

        throw v2;
    }

    private boolean sortActivitiesIfNeeded() {
        if(this.mActivitySorter != null && this.mIntent != null && !this.mActivities.isEmpty() && !this.mHistoricalRecords.isEmpty()) {
            this.mActivitySorter.sort(this.mIntent, this.mActivities, Collections.unmodifiableList(this.mHistoricalRecords));
            return 1;
        }

        return 0;
    }
}

