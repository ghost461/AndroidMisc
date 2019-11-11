package android.support.v4.app;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map$Entry;
import java.util.Map;
import java.util.Set;

@RequiresApi(value=16) class RemoteInputCompatJellybean {
    private static final String EXTRA_DATA_TYPE_RESULTS_DATA = "android.remoteinput.dataTypeResultsData";
    private static final String KEY_ALLOWED_DATA_TYPES = "allowedDataTypes";
    private static final String KEY_ALLOW_FREE_FORM_INPUT = "allowFreeFormInput";
    private static final String KEY_CHOICES = "choices";
    private static final String KEY_EXTRAS = "extras";
    private static final String KEY_LABEL = "label";
    private static final String KEY_RESULT_KEY = "resultKey";

    RemoteInputCompatJellybean() {
        super();
    }

    public static void addDataResultToIntent(RemoteInput arg5, Intent arg6, Map arg7) {
        Intent v0 = RemoteInputCompatJellybean.getClipDataIntentFromIntent(arg6);
        if(v0 == null) {
            v0 = new Intent();
        }

        Iterator v7 = arg7.entrySet().iterator();
        while(v7.hasNext()) {
            Object v1 = v7.next();
            Object v2 = ((Map$Entry)v1).getKey();
            v1 = ((Map$Entry)v1).getValue();
            if(v2 == null) {
                continue;
            }

            Bundle v3 = v0.getBundleExtra(RemoteInputCompatJellybean.getExtraResultsKeyForData(((String)v2)));
            if(v3 == null) {
                v3 = new Bundle();
            }

            v3.putString(arg5.getResultKey(), ((Uri)v1).toString());
            v0.putExtra(RemoteInputCompatJellybean.getExtraResultsKeyForData(((String)v2)), v3);
        }

        arg6.setClipData(ClipData.newIntent("android.remoteinput.results", v0));
    }

    static void addResultsToIntent(android.support.v4.app.RemoteInputCompatBase$RemoteInput[] arg7, Intent arg8, Bundle arg9) {
        Intent v0 = RemoteInputCompatJellybean.getClipDataIntentFromIntent(arg8);
        if(v0 == null) {
            v0 = new Intent();
        }

        Bundle v1 = v0.getBundleExtra("android.remoteinput.resultsData");
        if(v1 == null) {
            v1 = new Bundle();
        }

        int v2 = arg7.length;
        int v3;
        for(v3 = 0; v3 < v2; ++v3) {
            android.support.v4.app.RemoteInputCompatBase$RemoteInput v4 = arg7[v3];
            Object v5 = arg9.get(v4.getResultKey());
            if((v5 instanceof CharSequence)) {
                v1.putCharSequence(v4.getResultKey(), ((CharSequence)v5));
            }
        }

        v0.putExtra("android.remoteinput.resultsData", v1);
        arg8.setClipData(ClipData.newIntent("android.remoteinput.results", v0));
    }

    static android.support.v4.app.RemoteInputCompatBase$RemoteInput fromBundle(Bundle arg8, Factory arg9) {
        ArrayList v0 = arg8.getStringArrayList("allowedDataTypes");
        HashSet v7 = new HashSet();
        if(v0 != null) {
            Iterator v0_1 = v0.iterator();
            while(v0_1.hasNext()) {
                ((Set)v7).add(v0_1.next());
            }
        }

        return arg9.build(arg8.getString("resultKey"), arg8.getCharSequence("label"), arg8.getCharSequenceArray("choices"), arg8.getBoolean("allowFreeFormInput"), arg8.getBundle("extras"), ((Set)v7));
    }

    static android.support.v4.app.RemoteInputCompatBase$RemoteInput[] fromBundleArray(Bundle[] arg3, Factory arg4) {
        if(arg3 == null) {
            return null;
        }

        android.support.v4.app.RemoteInputCompatBase$RemoteInput[] v0 = arg4.newArray(arg3.length);
        int v1;
        for(v1 = 0; v1 < arg3.length; ++v1) {
            v0[v1] = RemoteInputCompatJellybean.fromBundle(arg3[v1], arg4);
        }

        return v0;
    }

    private static Intent getClipDataIntentFromIntent(Intent arg3) {
        ClipData v3 = arg3.getClipData();
        Intent v0 = null;
        if(v3 == null) {
            return v0;
        }

        ClipDescription v1 = v3.getDescription();
        if(!v1.hasMimeType("text/vnd.android.intent")) {
            return v0;
        }

        if(!v1.getLabel().equals("android.remoteinput.results")) {
            return v0;
        }

        return v3.getItemAt(0).getIntent();
    }

    static Map getDataResultsFromIntent(Intent arg6, String arg7) {
        HashMap v0_1;
        arg6 = RemoteInputCompatJellybean.getClipDataIntentFromIntent(arg6);
        Map v0 = null;
        if(arg6 == null) {
            return v0;
        }

        HashMap v1 = new HashMap();
        Iterator v2 = arg6.getExtras().keySet().iterator();
        while(v2.hasNext()) {
            Object v3 = v2.next();
            if(!((String)v3).startsWith("android.remoteinput.dataTypeResultsData")) {
                continue;
            }

            String v4 = ((String)v3).substring("android.remoteinput.dataTypeResultsData".length());
            if(v4 == null) {
                continue;
            }

            if(v4.isEmpty()) {
                continue;
            }

            String v3_1 = arg6.getBundleExtra(((String)v3)).getString(arg7);
            if(v3_1 == null) {
                continue;
            }

            if(v3_1.isEmpty()) {
                continue;
            }

            ((Map)v1).put(v4, Uri.parse(v3_1));
        }

        if(((Map)v1).isEmpty()) {
        }
        else {
            v0_1 = v1;
        }

        return ((Map)v0_1);
    }

    private static String getExtraResultsKeyForData(String arg2) {
        return "android.remoteinput.dataTypeResultsData" + arg2;
    }

    static Bundle getResultsFromIntent(Intent arg1) {
        arg1 = RemoteInputCompatJellybean.getClipDataIntentFromIntent(arg1);
        if(arg1 == null) {
            return null;
        }

        return arg1.getExtras().getParcelable("android.remoteinput.resultsData");
    }

    static Bundle toBundle(android.support.v4.app.RemoteInputCompatBase$RemoteInput arg3) {
        Bundle v0 = new Bundle();
        v0.putString("resultKey", arg3.getResultKey());
        v0.putCharSequence("label", arg3.getLabel());
        v0.putCharSequenceArray("choices", arg3.getChoices());
        v0.putBoolean("allowFreeFormInput", arg3.getAllowFreeFormInput());
        v0.putBundle("extras", arg3.getExtras());
        Set v3 = arg3.getAllowedDataTypes();
        if(v3 != null && !v3.isEmpty()) {
            ArrayList v1 = new ArrayList(v3.size());
            Iterator v3_1 = v3.iterator();
            while(v3_1.hasNext()) {
                v1.add(v3_1.next());
            }

            v0.putStringArrayList("allowedDataTypes", v1);
        }

        return v0;
    }

    static Bundle[] toBundleArray(android.support.v4.app.RemoteInputCompatBase$RemoteInput[] arg3) {
        if(arg3 == null) {
            return null;
        }

        Bundle[] v0 = new Bundle[arg3.length];
        int v1;
        for(v1 = 0; v1 < arg3.length; ++v1) {
            v0[v1] = RemoteInputCompatJellybean.toBundle(arg3[v1]);
        }

        return v0;
    }
}

