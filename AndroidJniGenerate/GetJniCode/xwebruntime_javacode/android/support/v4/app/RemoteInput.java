package android.support.v4.app;

import android.content.Intent;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.util.Log;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class RemoteInput extends android.support.v4.app.RemoteInputCompatBase$RemoteInput {
    final class android.support.v4.app.RemoteInput$1 implements Factory {
        android.support.v4.app.RemoteInput$1() {
            super();
        }

        public RemoteInput build(String arg9, CharSequence arg10, CharSequence[] arg11, boolean arg12, Bundle arg13, Set arg14) {
            return new RemoteInput(arg9, arg10, arg11, arg12, arg13, arg14);
        }

        public android.support.v4.app.RemoteInputCompatBase$RemoteInput build(String arg1, CharSequence arg2, CharSequence[] arg3, boolean arg4, Bundle arg5, Set arg6) {
            return this.build(arg1, arg2, arg3, arg4, arg5, arg6);
        }

        public RemoteInput[] newArray(int arg1) {
            return new RemoteInput[arg1];
        }

        public android.support.v4.app.RemoteInputCompatBase$RemoteInput[] newArray(int arg1) {
            return this.newArray(arg1);
        }
    }

    public final class Builder {
        private boolean mAllowFreeFormTextInput;
        private final Set mAllowedDataTypes;
        private CharSequence[] mChoices;
        private Bundle mExtras;
        private CharSequence mLabel;
        private final String mResultKey;

        public Builder(String arg2) {
            super();
            this.mAllowFreeFormTextInput = true;
            this.mExtras = new Bundle();
            this.mAllowedDataTypes = new HashSet();
            if(arg2 == null) {
                throw new IllegalArgumentException("Result key can\'t be null");
            }

            this.mResultKey = arg2;
        }

        public Builder addExtras(Bundle arg2) {
            if(arg2 != null) {
                this.mExtras.putAll(arg2);
            }

            return this;
        }

        public RemoteInput build() {
            return new RemoteInput(this.mResultKey, this.mLabel, this.mChoices, this.mAllowFreeFormTextInput, this.mExtras, this.mAllowedDataTypes);
        }

        public Bundle getExtras() {
            return this.mExtras;
        }

        public Builder setAllowDataType(String arg1, boolean arg2) {
            if(arg2) {
                this.mAllowedDataTypes.add(arg1);
            }
            else {
                this.mAllowedDataTypes.remove(arg1);
            }

            return this;
        }

        public Builder setAllowFreeFormInput(boolean arg1) {
            this.mAllowFreeFormTextInput = arg1;
            return this;
        }

        public Builder setChoices(CharSequence[] arg1) {
            this.mChoices = arg1;
            return this;
        }

        public Builder setLabel(CharSequence arg1) {
            this.mLabel = arg1;
            return this;
        }
    }

    interface Impl {
        void addDataResultToIntent(RemoteInput arg1, Intent arg2, Map arg3);

        void addResultsToIntent(RemoteInput[] arg1, Intent arg2, Bundle arg3);

        Map getDataResultsFromIntent(Intent arg1, String arg2);

        Bundle getResultsFromIntent(Intent arg1);
    }

    @RequiresApi(value=20) class ImplApi20 implements Impl {
        ImplApi20() {
            super();
        }

        public void addDataResultToIntent(RemoteInput arg1, Intent arg2, Map arg3) {
            RemoteInputCompatApi20.addDataResultToIntent(((android.support.v4.app.RemoteInputCompatBase$RemoteInput)arg1), arg2, arg3);
        }

        public void addResultsToIntent(RemoteInput[] arg1, Intent arg2, Bundle arg3) {
            RemoteInputCompatApi20.addResultsToIntent(((android.support.v4.app.RemoteInputCompatBase$RemoteInput[])arg1), arg2, arg3);
        }

        public Map getDataResultsFromIntent(Intent arg1, String arg2) {
            return RemoteInputCompatApi20.getDataResultsFromIntent(arg1, arg2);
        }

        public Bundle getResultsFromIntent(Intent arg1) {
            return RemoteInputCompatApi20.getResultsFromIntent(arg1);
        }
    }

    class ImplBase implements Impl {
        ImplBase() {
            super();
        }

        public void addDataResultToIntent(RemoteInput arg1, Intent arg2, Map arg3) {
            Log.w("RemoteInput", "RemoteInput is only supported from API Level 16");
        }

        public void addResultsToIntent(RemoteInput[] arg1, Intent arg2, Bundle arg3) {
            Log.w("RemoteInput", "RemoteInput is only supported from API Level 16");
        }

        public Map getDataResultsFromIntent(Intent arg1, String arg2) {
            Log.w("RemoteInput", "RemoteInput is only supported from API Level 16");
            return null;
        }

        public Bundle getResultsFromIntent(Intent arg2) {
            Log.w("RemoteInput", "RemoteInput is only supported from API Level 16");
            return null;
        }
    }

    @RequiresApi(value=16) class ImplJellybean implements Impl {
        ImplJellybean() {
            super();
        }

        public void addDataResultToIntent(RemoteInput arg1, Intent arg2, Map arg3) {
            RemoteInputCompatJellybean.addDataResultToIntent(arg1, arg2, arg3);
        }

        public void addResultsToIntent(RemoteInput[] arg1, Intent arg2, Bundle arg3) {
            RemoteInputCompatJellybean.addResultsToIntent(((android.support.v4.app.RemoteInputCompatBase$RemoteInput[])arg1), arg2, arg3);
        }

        public Map getDataResultsFromIntent(Intent arg1, String arg2) {
            return RemoteInputCompatJellybean.getDataResultsFromIntent(arg1, arg2);
        }

        public Bundle getResultsFromIntent(Intent arg1) {
            return RemoteInputCompatJellybean.getResultsFromIntent(arg1);
        }
    }

    private static final String EXTRA_DATA_TYPE_RESULTS_DATA = "android.remoteinput.dataTypeResultsData";
    public static final String EXTRA_RESULTS_DATA = "android.remoteinput.resultsData";
    @RestrictTo(value={Scope.LIBRARY_GROUP}) public static final Factory FACTORY = null;
    private static final Impl IMPL = null;
    public static final String RESULTS_CLIP_LABEL = "android.remoteinput.results";
    private static final String TAG = "RemoteInput";
    private final boolean mAllowFreeFormTextInput;
    private final Set mAllowedDataTypes;
    private final CharSequence[] mChoices;
    private final Bundle mExtras;
    private final CharSequence mLabel;
    private final String mResultKey;

    static {
        if(Build$VERSION.SDK_INT >= 20) {
            RemoteInput.IMPL = new ImplApi20();
        }
        else if(Build$VERSION.SDK_INT >= 16) {
            RemoteInput.IMPL = new ImplJellybean();
        }
        else {
            RemoteInput.IMPL = new ImplBase();
        }

        RemoteInput.FACTORY = new android.support.v4.app.RemoteInput$1();
    }

    RemoteInput(String arg1, CharSequence arg2, CharSequence[] arg3, boolean arg4, Bundle arg5, Set arg6) {
        super();
        this.mResultKey = arg1;
        this.mLabel = arg2;
        this.mChoices = arg3;
        this.mAllowFreeFormTextInput = arg4;
        this.mExtras = arg5;
        this.mAllowedDataTypes = arg6;
    }

    public static void addDataResultToIntent(RemoteInput arg1, Intent arg2, Map arg3) {
        RemoteInput.IMPL.addDataResultToIntent(arg1, arg2, arg3);
    }

    public static void addResultsToIntent(RemoteInput[] arg1, Intent arg2, Bundle arg3) {
        RemoteInput.IMPL.addResultsToIntent(arg1, arg2, arg3);
    }

    public boolean getAllowFreeFormInput() {
        return this.mAllowFreeFormTextInput;
    }

    public Set getAllowedDataTypes() {
        return this.mAllowedDataTypes;
    }

    public CharSequence[] getChoices() {
        return this.mChoices;
    }

    public static Map getDataResultsFromIntent(Intent arg1, String arg2) {
        return RemoteInput.IMPL.getDataResultsFromIntent(arg1, arg2);
    }

    public Bundle getExtras() {
        return this.mExtras;
    }

    public CharSequence getLabel() {
        return this.mLabel;
    }

    public String getResultKey() {
        return this.mResultKey;
    }

    public static Bundle getResultsFromIntent(Intent arg1) {
        return RemoteInput.IMPL.getResultsFromIntent(arg1);
    }

    public boolean isDataOnly() {
        boolean v0;
        if(!this.getAllowFreeFormInput()) {
            if(this.getChoices() != null && this.getChoices().length != 0) {
                goto label_14;
            }

            if(this.getAllowedDataTypes() == null) {
                goto label_14;
            }

            if(this.getAllowedDataTypes().isEmpty()) {
                goto label_14;
            }

            v0 = true;
        }
        else {
        label_14:
            v0 = false;
        }

        return v0;
    }
}

