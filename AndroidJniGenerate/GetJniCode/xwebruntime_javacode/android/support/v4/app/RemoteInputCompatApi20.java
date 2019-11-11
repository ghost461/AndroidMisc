package android.support.v4.app;

import android.app.RemoteInput$Builder;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map$Entry;
import java.util.Map;

@RequiresApi(value=20) class RemoteInputCompatApi20 {
    private static final String EXTRA_DATA_TYPE_RESULTS_DATA = "android.remoteinput.dataTypeResultsData";

    RemoteInputCompatApi20() {
        super();
    }

    public static void addDataResultToIntent(RemoteInput arg5, Intent arg6, Map arg7) {
        Intent v0 = RemoteInputCompatApi20.getClipDataIntentFromIntent(arg6);
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

            Bundle v3 = v0.getBundleExtra(RemoteInputCompatApi20.getExtraResultsKeyForData(((String)v2)));
            if(v3 == null) {
                v3 = new Bundle();
            }

            v3.putString(arg5.getResultKey(), ((Uri)v1).toString());
            v0.putExtra(RemoteInputCompatApi20.getExtraResultsKeyForData(((String)v2)), v3);
        }

        arg6.setClipData(ClipData.newIntent("android.remoteinput.results", v0));
    }

    static void addResultsToIntent(RemoteInput[] arg6, Intent arg7, Bundle arg8) {
        Bundle v0 = RemoteInputCompatApi20.getResultsFromIntent(arg7);
        if(v0 == null) {
        }
        else {
            v0.putAll(arg8);
            arg8 = v0;
        }

        int v0_1 = arg6.length;
        int v2;
        for(v2 = 0; v2 < v0_1; ++v2) {
            RemoteInput v3 = arg6[v2];
            Map v4 = RemoteInputCompatApi20.getDataResultsFromIntent(arg7, v3.getResultKey());
            android.app.RemoteInput.addResultsToIntent(RemoteInputCompatApi20.fromCompat(new RemoteInput[]{v3}), arg7, arg8);
            if(v4 != null) {
                RemoteInputCompatApi20.addDataResultToIntent(v3, arg7, v4);
            }
        }
    }

    static android.app.RemoteInput[] fromCompat(RemoteInput[] arg5) {
        if(arg5 == null) {
            return null;
        }

        android.app.RemoteInput[] v0 = new android.app.RemoteInput[arg5.length];
        int v1;
        for(v1 = 0; v1 < arg5.length; ++v1) {
            v0[v1] = new RemoteInput$Builder(arg5[v1].getResultKey()).setLabel(arg5[v1].getLabel()).setChoices(arg5[v1].getChoices()).setAllowFreeFormInput(arg5[v1].getAllowFreeFormInput()).addExtras(arg5[v1].getExtras()).build();
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
        arg6 = RemoteInputCompatApi20.getClipDataIntentFromIntent(arg6);
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
            HashMap v0_1 = v1;
        }

        return v0;
    }

    private static String getExtraResultsKeyForData(String arg2) {
        return "android.remoteinput.dataTypeResultsData" + arg2;
    }

    static Bundle getResultsFromIntent(Intent arg0) {
        return android.app.RemoteInput.getResultsFromIntent(arg0);
    }

    static RemoteInput[] toCompat(android.app.RemoteInput[] arg10, Factory arg11) {
        if(arg10 == null) {
            return null;
        }

        RemoteInput[] v0 = arg11.newArray(arg10.length);
        int v1;
        for(v1 = 0; v1 < arg10.length; ++v1) {
            v0[v1] = arg11.build(arg10[v1].getResultKey(), arg10[v1].getLabel(), arg10[v1].getChoices(), arg10[v1].getAllowFreeFormInput(), arg10[v1].getExtras(), null);
        }

        return v0;
    }
}

