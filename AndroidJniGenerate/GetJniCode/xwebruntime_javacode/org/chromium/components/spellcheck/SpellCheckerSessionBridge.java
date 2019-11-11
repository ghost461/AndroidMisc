package org.chromium.components.spellcheck;

import android.os.SystemClock;
import android.view.textservice.SentenceSuggestionsInfo;
import android.view.textservice.SpellCheckerSession$SpellCheckerSessionListener;
import android.view.textservice.SpellCheckerSession;
import android.view.textservice.SuggestionsInfo;
import android.view.textservice.TextInfo;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import org.chromium.base.ContextUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.metrics.RecordHistogram;

public class SpellCheckerSessionBridge implements SpellCheckerSession$SpellCheckerSessionListener {
    private long mNativeSpellCheckerSessionBridge;
    private final SpellCheckerSession mSpellCheckerSession;
    private long mStartMs;
    private long mStopMs;

    private SpellCheckerSessionBridge(long arg2) {
        super();
        this.mNativeSpellCheckerSessionBridge = arg2;
        this.mSpellCheckerSession = ContextUtils.getApplicationContext().getSystemService("textservices").newSpellCheckerSession(null, null, ((SpellCheckerSession$SpellCheckerSessionListener)this), true);
    }

    private int[] convertListToArray(ArrayList arg4) {
        int[] v0 = new int[arg4.size()];
        int v1;
        for(v1 = 0; v1 < v0.length; ++v1) {
            v0[v1] = arg4.get(v1).intValue();
        }

        return v0;
    }

    @CalledByNative private static SpellCheckerSessionBridge create(long arg1) {
        SpellCheckerSessionBridge v0 = new SpellCheckerSessionBridge(arg1);
        if(v0.mSpellCheckerSession == null) {
            return null;
        }

        return v0;
    }

    @CalledByNative private void disconnect() {
        this.mNativeSpellCheckerSessionBridge = 0;
        this.mSpellCheckerSession.cancel();
        this.mSpellCheckerSession.close();
    }

    private native void nativeProcessSpellCheckResults(long arg1, int[] arg2, int[] arg3, String[][] arg4) {
    }

    public void onGetSentenceSuggestions(SentenceSuggestionsInfo[] arg15) {
        this.mStopMs = SystemClock.elapsedRealtime();
        if(this.mNativeSpellCheckerSessionBridge == 0) {
            return;
        }

        ArrayList v0 = new ArrayList();
        ArrayList v1 = new ArrayList();
        ArrayList v2 = new ArrayList();
        int v3 = arg15.length;
        int v5;
        for(v5 = 0; v5 < v3; ++v5) {
            SentenceSuggestionsInfo v6 = arg15[v5];
            if(v6 == null) {
            }
            else {
                int v7;
                for(v7 = 0; v7 < v6.getSuggestionsCount(); ++v7) {
                    if((v6.getSuggestionsInfoAt(v7).getSuggestionsAttributes() & 2) == 2) {
                        v0.add(Integer.valueOf(v6.getOffsetAt(v7)));
                        v1.add(Integer.valueOf(v6.getLengthAt(v7)));
                        SuggestionsInfo v8 = v6.getSuggestionsInfoAt(v7);
                        ArrayList v9 = new ArrayList();
                        int v10;
                        for(v10 = 0; v10 < v8.getSuggestionsCount(); ++v10) {
                            String v11 = v8.getSuggestionAt(v10);
                            if(v11.charAt(v11.length() - 1) == 0x200B) {
                                v11 = v11.substring(0, v11.length() - 1);
                            }

                            v9.add(v11);
                        }

                        v2.add(v9.toArray(new String[v9.size()]));
                    }
                }
            }
        }

        this.nativeProcessSpellCheckResults(this.mNativeSpellCheckerSessionBridge, this.convertListToArray(v0), this.convertListToArray(v1), v2.toArray(new String[v2.size()][]));
        RecordHistogram.recordTimesHistogram("SpellCheck.Android.Latency", this.mStopMs - this.mStartMs, TimeUnit.MILLISECONDS);
    }

    public void onGetSuggestions(SuggestionsInfo[] arg1) {
    }

    @CalledByNative private void requestTextCheck(String arg6) {
        if(arg6.endsWith(".")) {
            arg6 = arg6.substring(0, arg6.length() - 1);
        }

        this.mStartMs = SystemClock.elapsedRealtime();
        this.mSpellCheckerSession.getSentenceSuggestions(new TextInfo[]{new TextInfo(arg6)}, 0);
    }
}

