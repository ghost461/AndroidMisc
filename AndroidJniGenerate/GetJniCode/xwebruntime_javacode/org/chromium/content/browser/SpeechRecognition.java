package org.chromium.content.browser;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import java.util.ArrayList;
import java.util.Iterator;
import org.chromium.base.ContextUtils;
import org.chromium.base.Log;
import org.chromium.base.PackageUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="content") public class SpeechRecognition {
    class Listener implements RecognitionListener {
        static {
        }

        Listener(SpeechRecognition arg1) {
            SpeechRecognition.this = arg1;
            super();
        }

        private void handleResults(Bundle arg7, boolean arg8) {
            boolean v5 = !SpeechRecognition.this.mContinuous || !arg8 ? arg8 : false;
            ArrayList v8 = arg7.getStringArrayList("results_recognition");
            SpeechRecognition.this.nativeOnRecognitionResults(SpeechRecognition.this.mNativeSpeechRecognizerImplAndroid, v8.toArray(new String[v8.size()]), arg7.getFloatArray("confidence_scores"), v5);
        }

        public void onBeginningOfSpeech() {
            SpeechRecognition.this.mState = 2;
            SpeechRecognition.this.nativeOnSoundStart(SpeechRecognition.this.mNativeSpeechRecognizerImplAndroid);
        }

        public void onBufferReceived(byte[] arg1) {
        }

        public void onEndOfSpeech() {
            if(!SpeechRecognition.this.mContinuous) {
                SpeechRecognition.this.nativeOnSoundEnd(SpeechRecognition.this.mNativeSpeechRecognizerImplAndroid);
                SpeechRecognition.this.nativeOnAudioEnd(SpeechRecognition.this.mNativeSpeechRecognizerImplAndroid);
                SpeechRecognition.this.mState = 0;
            }
        }

        public void onError(int arg2) {
            switch(arg2) {
                case 3: {
                    goto label_10;
                }
                case 1: 
                case 2: 
                case 4: {
                    goto label_12;
                }
                case 5: {
                    goto label_8;
                }
                case 6: {
                    goto label_6;
                }
                case 7: {
                    goto label_4;
                }
                case 8: 
                case 9: {
                    goto label_2;
                }
            }

            return;
        label_2:
            arg2 = 5;
            goto label_13;
        label_4:
            arg2 = 9;
            goto label_13;
        label_6:
            arg2 = 1;
            goto label_13;
        label_8:
            arg2 = 2;
            goto label_13;
        label_10:
            arg2 = 3;
            goto label_13;
        label_12:
            arg2 = 4;
        label_13:
            SpeechRecognition.this.terminate(arg2);
        }

        public void onEvent(int arg1, Bundle arg2) {
        }

        public void onPartialResults(Bundle arg2) {
            this.handleResults(arg2, true);
        }

        public void onReadyForSpeech(Bundle arg3) {
            SpeechRecognition.this.mState = 1;
            SpeechRecognition.this.nativeOnAudioStart(SpeechRecognition.this.mNativeSpeechRecognizerImplAndroid);
        }

        public void onResults(Bundle arg2) {
            this.handleResults(arg2, false);
            SpeechRecognition.this.terminate(0);
        }

        public void onRmsChanged(float arg1) {
        }
    }

    private static final int PROVIDER_MIN_VERSION = 300207030;
    private static final String PROVIDER_PACKAGE_NAME = "com.google.android.googlequicksearchbox";
    private static final int STATE_AWAITING_SPEECH = 1;
    private static final int STATE_CAPTURING_SPEECH = 2;
    private static final int STATE_IDLE = 0;
    private static final String TAG = "SpeechRecog";
    private boolean mContinuous;
    private final Intent mIntent;
    private final RecognitionListener mListener;
    private long mNativeSpeechRecognizerImplAndroid;
    private SpeechRecognizer mRecognizer;
    private int mState;
    private static ComponentName sRecognitionProvider;

    private SpeechRecognition(long arg2) {
        super();
        this.mContinuous = false;
        this.mNativeSpeechRecognizerImplAndroid = arg2;
        this.mListener = new Listener(this);
        this.mIntent = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        this.mRecognizer = SpeechRecognition.sRecognitionProvider != null ? SpeechRecognizer.createSpeechRecognizer(ContextUtils.getApplicationContext(), SpeechRecognition.sRecognitionProvider) : SpeechRecognizer.createSpeechRecognizer(ContextUtils.getApplicationContext());
        this.mRecognizer.setRecognitionListener(this.mListener);
    }

    @CalledByNative private void abortRecognition() {
        if(this.mRecognizer == null) {
            return;
        }

        this.mRecognizer.cancel();
        this.terminate(2);
    }

    static int access$002(SpeechRecognition arg0, int arg1) {
        arg0.mState = arg1;
        return arg1;
    }

    static long access$100(SpeechRecognition arg2) {
        return arg2.mNativeSpeechRecognizerImplAndroid;
    }

    static void access$200(SpeechRecognition arg0, long arg1) {
        arg0.nativeOnSoundStart(arg1);
    }

    static boolean access$300(SpeechRecognition arg0) {
        return arg0.mContinuous;
    }

    static void access$400(SpeechRecognition arg0, long arg1) {
        arg0.nativeOnSoundEnd(arg1);
    }

    static void access$500(SpeechRecognition arg0, long arg1) {
        arg0.nativeOnAudioEnd(arg1);
    }

    static void access$600(SpeechRecognition arg0, int arg1) {
        arg0.terminate(arg1);
    }

    static void access$700(SpeechRecognition arg0, long arg1) {
        arg0.nativeOnAudioStart(arg1);
    }

    static void access$800(SpeechRecognition arg0, long arg1, String[] arg3, float[] arg4, boolean arg5) {
        arg0.nativeOnRecognitionResults(arg1, arg3, arg4, arg5);
    }

    @CalledByNative private static SpeechRecognition createSpeechRecognition(long arg1) {
        return new SpeechRecognition(arg1);
    }

    @SuppressLint(value={"WrongConstant"}) public static boolean initialize(Context arg5) {
        ServiceInfo v2;
        if(!SpeechRecognizer.isRecognitionAvailable(arg5)) {
            return 0;
        }

        Iterator v0 = arg5.getPackageManager().queryIntentServices(new Intent("android.speech.RecognitionService"), 4).iterator();
        while(true) {
            if(!v0.hasNext()) {
                return 0;
            }

            v2 = v0.next().serviceInfo;
            if(!v2.packageName.equals("com.google.android.googlequicksearchbox")) {
                continue;
            }

            if(PackageUtils.getPackageVersion(arg5, v2.packageName) >= 300207030) {
                break;
            }
        }

        SpeechRecognition.sRecognitionProvider = new ComponentName(v2.packageName, v2.name);
        return 1;
    }

    private native void nativeOnAudioEnd(long arg1) {
    }

    private native void nativeOnAudioStart(long arg1) {
    }

    private native void nativeOnRecognitionEnd(long arg1) {
    }

    private native void nativeOnRecognitionError(long arg1, int arg2) {
    }

    private native void nativeOnRecognitionResults(long arg1, String[] arg2, float[] arg3, boolean arg4) {
    }

    private native void nativeOnSoundEnd(long arg1) {
    }

    private native void nativeOnSoundStart(long arg1) {
    }

    @CalledByNative private void startRecognition(String arg3, boolean arg4, boolean arg5) {
        if(this.mRecognizer == null) {
            return;
        }

        this.mContinuous = arg4;
        this.mIntent.putExtra("android.speech.extra.DICTATION_MODE", arg4);
        this.mIntent.putExtra("android.speech.extra.LANGUAGE", arg3);
        this.mIntent.putExtra("android.speech.extra.PARTIAL_RESULTS", arg5);
        this.mRecognizer.startListening(this.mIntent);
    }

    @CalledByNative private void stopRecognition() {
        if(this.mRecognizer == null) {
            return;
        }

        this.mContinuous = false;
        this.mRecognizer.stopListening();
    }

    private void terminate(int arg7) {
        long v2 = 0;
        if(this.mNativeSpeechRecognizerImplAndroid == v2) {
            return;
        }

        if(this.mState != 0) {
            if(this.mState == 2) {
                this.nativeOnSoundEnd(this.mNativeSpeechRecognizerImplAndroid);
            }

            this.nativeOnAudioEnd(this.mNativeSpeechRecognizerImplAndroid);
            this.mState = 0;
        }

        if(arg7 != 0) {
            this.nativeOnRecognitionError(this.mNativeSpeechRecognizerImplAndroid, arg7);
        }

        try {
            this.mRecognizer.destroy();
        }
        catch(IllegalArgumentException v7) {
            Log.w("SpeechRecog", "Destroy threw exception " + this.mRecognizer, new Object[]{v7});
        }

        this.mRecognizer = null;
        this.nativeOnRecognitionEnd(this.mNativeSpeechRecognizerImplAndroid);
        this.mNativeSpeechRecognizerImplAndroid = v2;
    }
}

