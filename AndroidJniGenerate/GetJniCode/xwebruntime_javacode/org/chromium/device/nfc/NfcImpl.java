package org.chromium.device.nfc;

import android.annotation.TargetApi;
import android.app.Activity;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter$ReaderCallback;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.nfc.Tag;
import android.nfc.TagLostException;
import android.os.Build$VERSION;
import android.os.Handler;
import android.os.Process;
import android.util.SparseArray;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.chromium.base.Callback;
import org.chromium.base.ContextUtils;
import org.chromium.base.Log;
import org.chromium.device.mojom.Nfc$CancelAllWatchesResponse;
import org.chromium.device.mojom.Nfc$CancelPushResponse;
import org.chromium.device.mojom.Nfc$CancelWatchResponse;
import org.chromium.device.mojom.Nfc$PushResponse;
import org.chromium.device.mojom.Nfc$WatchResponse;
import org.chromium.device.mojom.Nfc;
import org.chromium.device.mojom.NfcClient;
import org.chromium.device.mojom.NfcError;
import org.chromium.device.mojom.NfcMessage;
import org.chromium.device.mojom.NfcPushOptions;
import org.chromium.device.mojom.NfcWatchOptions;
import org.chromium.mojo.bindings.Callbacks$Callback1;
import org.chromium.mojo.system.MojoException;

public class NfcImpl implements Nfc {
    class PendingPushOperation {
        private final PushResponse mPushResponseCallback;
        public final NfcMessage nfcMessage;
        public final NfcPushOptions nfcPushOptions;

        public PendingPushOperation(NfcMessage arg1, NfcPushOptions arg2, PushResponse arg3) {
            super();
            this.nfcMessage = arg1;
            this.nfcPushOptions = arg2;
            this.mPushResponseCallback = arg3;
        }

        public void complete(NfcError arg2) {
            if(this.mPushResponseCallback != null) {
                this.mPushResponseCallback.call(arg2);
            }
        }
    }

    @TargetApi(value=19) class ReaderCallbackHandler implements NfcAdapter$ReaderCallback {
        private final NfcImpl mNfcImpl;

        public ReaderCallbackHandler(NfcImpl arg1) {
            super();
            this.mNfcImpl = arg1;
        }

        public void onTagDiscovered(Tag arg2) {
            this.mNfcImpl.onTagDiscovered(arg2);
        }
    }

    private static final String ANY_PATH = "/*";
    private static final String TAG = "NfcImpl";
    private Activity mActivity;
    private NfcClient mClient;
    private final NfcDelegate mDelegate;
    private final boolean mHasPermission;
    private final int mHostId;
    private final NfcAdapter mNfcAdapter;
    private final NfcManager mNfcManager;
    private PendingPushOperation mPendingPushOperation;
    private final Handler mPushTimeoutHandler;
    private Runnable mPushTimeoutRunnable;
    private ReaderCallbackHandler mReaderCallbackHandler;
    private NfcTagHandler mTagHandler;
    private int mWatcherId;
    private final SparseArray mWatchers;

    static {
    }

    public NfcImpl(int arg3, NfcDelegate arg4) {
        super();
        this.mWatchers = new SparseArray();
        this.mPushTimeoutHandler = new Handler();
        this.mHostId = arg3;
        this.mDelegate = arg4;
        boolean v3 = ContextUtils.getApplicationContext().checkPermission("android.permission.NFC", Process.myPid(), Process.myUid()) == 0 ? true : false;
        this.mHasPermission = v3;
        this.mDelegate.trackActivityForHost(this.mHostId, new Callback() {
            public void onResult(Activity arg2) {
                NfcImpl.this.setActivity(arg2);
            }

            public void onResult(Object arg1) {
                this.onResult(((Activity)arg1));
            }
        });
        NfcAdapter v0 = null;
        if(!this.mHasPermission || Build$VERSION.SDK_INT < 19) {
            Log.w("NfcImpl", "NFC operations are not permitted.", new Object[0]);
            this.mNfcAdapter = v0;
            this.mNfcManager = ((NfcManager)v0);
        }
        else {
            this.mNfcManager = ContextUtils.getApplicationContext().getSystemService("nfc");
            if(this.mNfcManager == null) {
                Log.w("NfcImpl", "NFC is not supported.", new Object[0]);
                this.mNfcAdapter = v0;
            }
            else {
                this.mNfcAdapter = this.mNfcManager.getDefaultAdapter();
            }
        }
    }

    static NfcError access$000(NfcImpl arg0, int arg1) {
        return arg0.createError(arg1);
    }

    static void access$100(NfcImpl arg0, NfcError arg1) {
        arg0.completePendingPushOperation(arg1);
    }

    public void cancelAllWatches(CancelAllWatchesResponse arg2) {
        if(!this.checkIfReady(((Callback1)arg2))) {
            return;
        }

        if(this.mWatchers.size() == 0) {
            arg2.call(this.createError(3));
        }
        else {
            this.mWatchers.clear();
            arg2.call(null);
            this.disableReaderModeIfNeeded();
        }
    }

    public void cancelPush(int arg2, CancelPushResponse arg3) {
        if(!this.checkIfReady(((Callback1)arg3))) {
            return;
        }

        if(arg2 == 1) {
            arg3.call(this.createError(1));
            return;
        }

        if(this.mPendingPushOperation == null) {
            arg3.call(this.createError(3));
        }
        else {
            this.completePendingPushOperation(this.createError(5));
            arg3.call(null);
        }
    }

    void cancelPushTimeoutTask() {
        if(this.mPushTimeoutRunnable == null) {
            return;
        }

        this.mPushTimeoutHandler.removeCallbacks(this.mPushTimeoutRunnable);
        this.mPushTimeoutRunnable = null;
    }

    public void cancelWatch(int arg2, CancelWatchResponse arg3) {
        if(!this.checkIfReady(((Callback1)arg3))) {
            return;
        }

        if(this.mWatchers.indexOfKey(arg2) < 0) {
            arg3.call(this.createError(3));
        }
        else {
            this.mWatchers.remove(arg2);
            arg3.call(null);
            this.disableReaderModeIfNeeded();
        }
    }

    private NfcError checkIfReady() {
        if(this.mHasPermission) {
            if(this.mActivity == null) {
            }
            else {
                if(this.mNfcManager != null) {
                    if(this.mNfcAdapter == null) {
                    }
                    else if(!this.mNfcAdapter.isEnabled()) {
                        return this.createError(2);
                    }
                    else {
                        return null;
                    }
                }

                return this.createError(1);
            }
        }

        return this.createError(0);
    }

    private boolean checkIfReady(WatchResponse arg4) {
        NfcError v0 = this.checkIfReady();
        if(v0 == null) {
            return 1;
        }

        arg4.call(Integer.valueOf(0), v0);
        return 0;
    }

    private boolean checkIfReady(Callback1 arg2) {
        NfcError v0 = this.checkIfReady();
        if(v0 == null) {
            return 1;
        }

        arg2.call(v0);
        return 0;
    }

    public void close() {
        this.mDelegate.stopTrackingActivityForHost(this.mHostId);
        this.disableReaderMode();
    }

    private void completePendingPushOperation(NfcError arg2) {
        if(this.mPendingPushOperation == null) {
            return;
        }

        this.cancelPushTimeoutTask();
        this.mPendingPushOperation.complete(arg2);
        this.mPendingPushOperation = null;
        this.disableReaderModeIfNeeded();
    }

    private NfcError createError(int arg2) {
        NfcError v0 = new NfcError();
        v0.errorType = arg2;
        return v0;
    }

    @TargetApi(value=19) private void disableReaderMode() {
        if(Build$VERSION.SDK_INT < 19) {
            return;
        }

        if(this.mReaderCallbackHandler == null) {
            return;
        }

        this.mReaderCallbackHandler = null;
        if(this.mActivity != null && this.mNfcAdapter != null && !this.mActivity.isDestroyed()) {
            this.mNfcAdapter.disableReaderMode(this.mActivity);
        }
    }

    private void disableReaderModeIfNeeded() {
        if(this.mPendingPushOperation == null && this.mWatchers.size() == 0) {
            this.disableReaderMode();
        }
    }

    private void enableReaderModeIfNeeded() {
        if(Build$VERSION.SDK_INT < 19) {
            return;
        }

        if(this.mReaderCallbackHandler == null && this.mActivity != null) {
            if(this.mNfcAdapter == null) {
            }
            else {
                if(this.mPendingPushOperation == null && this.mWatchers.size() == 0) {
                    return;
                }

                this.mReaderCallbackHandler = new ReaderCallbackHandler(this);
                this.mNfcAdapter.enableReaderMode(this.mActivity, this.mReaderCallbackHandler, 15, null);
                return;
            }
        }
    }

    private boolean matchesWatchOptions(NfcMessage arg7, NfcWatchOptions arg8) {
        if(arg8.mode == 0 && (arg7.url == null || (arg7.url.isEmpty()))) {
            return 0;
        }

        if(!this.matchesWebNfcId(arg7.url, arg8.url)) {
            return 0;
        }

        if((arg8.mediaType == null || (arg8.mediaType.isEmpty())) && arg8.recordFilter == null) {
            return 1;
        }

        int v0;
        for(v0 = 0; v0 < arg7.data.length; ++v0) {
            boolean v3 = arg8.mediaType == null || (arg8.mediaType.isEmpty()) ? true : arg8.mediaType.equals(arg7.data[v0].mediaType);
            if(arg8.recordFilter == null || arg8.recordFilter.recordType == arg7.data[v0].recordType) {
                int v4 = 1;
                goto label_52;
            label_51:
                v4 = 0;
            }
            else {
                goto label_51;
            }

        label_52:
            if((v3) && v4 != 0) {
                return 1;
            }
        }

        return 0;
    }

    private boolean matchesWebNfcId(String arg6, String arg7) {
        if(arg6 != null && !arg6.isEmpty() && arg7 != null && !arg7.isEmpty()) {
            try {
                URL v2 = new URL(arg6);
                URL v6 = new URL(arg7);
                if(!v2.getProtocol().equals(v6.getProtocol())) {
                    return 0;
                }
                else {
                    arg7 = v2.getHost();
                    StringBuilder v3 = new StringBuilder();
                    v3.append(".");
                    v3.append(v6.getHost());
                    if(!arg7.endsWith(v3.toString()) && !v2.getHost().equals(v6.getHost())) {
                        return 0;
                    }

                    if(v6.getPath().equals("/*")) {
                        return 1;
                    }

                    if(!v2.getPath().startsWith(v6.getPath())) {
                        return 0;
                    }

                    return 1;
                }

                return 1;
            }
            catch(MalformedURLException ) {
                return 0;
            }

            return 1;
        }

        return 1;
    }

    private void notifyMatchingWatchers(NdefMessage arg6) {
        try {
            NfcMessage v6 = NfcTypeConverter.toNfcMessage(arg6);
            ArrayList v1 = new ArrayList();
            int v2;
            for(v2 = 0; v2 < this.mWatchers.size(); ++v2) {
                if(this.matchesWatchOptions(v6, this.mWatchers.valueAt(v2))) {
                    ((List)v1).add(Integer.valueOf(this.mWatchers.keyAt(v2)));
                }
            }

            if(((List)v1).size() == 0) {
                return;
            }

            int[] v2_1 = new int[((List)v1).size()];
            int v3;
            for(v3 = 0; v3 < ((List)v1).size(); ++v3) {
                v2_1[v3] = ((List)v1).get(v3).intValue();
            }

            this.mClient.onWatch(v2_1, v6);
        }
        catch(UnsupportedEncodingException ) {
            Log.w("NfcImpl", "Cannot convert NdefMessage to NfcMessage.", new Object[0]);
        }
    }

    public void onConnectionError(MojoException arg1) {
        this.close();
    }

    public void onTagDiscovered(Tag arg1) {
        this.processPendingOperations(NfcTagHandler.create(arg1));
    }

    private void pendingPushOperationCompleted(NfcError arg1) {
        this.completePendingPushOperation(arg1);
        if(arg1 != null) {
            this.mTagHandler = null;
        }
    }

    protected void processPendingOperations(NfcTagHandler arg3) {
        this.mTagHandler = arg3;
        this.processPendingWatchOperations();
        this.processPendingPushOperation();
        if(this.mTagHandler != null && (this.mTagHandler.isConnected())) {
            try {
                this.mTagHandler.close();
            }
            catch(IOException ) {
                Log.w("NfcImpl", "Cannot close NFC tag connection.", new Object[0]);
            }
        }
    }

    private void processPendingPushOperation() {
        if(this.mTagHandler != null) {
            if(this.mPendingPushOperation == null) {
            }
            else {
                NfcTagHandler v1 = null;
                if(this.mTagHandler.isTagOutOfRange()) {
                    this.mTagHandler = v1;
                    return;
                }
                else {
                    int v0 = 8;
                    try {
                        this.mTagHandler.connect();
                        this.mTagHandler.write(NfcTypeConverter.toNdefMessage(this.mPendingPushOperation.nfcMessage));
                        this.pendingPushOperationCompleted(((NfcError)v1));
                    }
                    catch(IOException ) {
                        Log.w("NfcImpl", "Cannot write data to NFC tag. IO_ERROR.", new Object[0]);
                        this.pendingPushOperationCompleted(this.createError(v0));
                    }
                    catch(TagLostException ) {
                        Log.w("NfcImpl", "Cannot write data to NFC tag. Tag is lost.", new Object[0]);
                        this.pendingPushOperationCompleted(this.createError(v0));
                    }
                    catch(InvalidNfcMessageException ) {
                        Log.w("NfcImpl", "Cannot write data to NFC tag. Invalid NfcMessage.", new Object[0]);
                        this.pendingPushOperationCompleted(this.createError(4));
                    }

                    return;
                }
            }
        }
    }

    private void processPendingWatchOperations() {
        NdefMessage v2;
        if(this.mTagHandler != null && this.mClient != null) {
            if(this.mWatchers.size() == 0) {
            }
            else {
                if(this.mPendingPushOperation != null && (this.mPendingPushOperation.nfcPushOptions.ignoreRead)) {
                    return;
                }

                NfcTagHandler v1 = null;
                if(this.mTagHandler.isTagOutOfRange()) {
                    this.mTagHandler = v1;
                    return;
                }

                try {
                    this.mTagHandler.connect();
                    v2 = this.mTagHandler.read();
                }
                catch(IOException ) {
                    v2 = ((NdefMessage)v1);
                    goto label_35;
                }
                catch(TagLostException ) {
                    v2 = ((NdefMessage)v1);
                    goto label_41;
                }

                try {
                    if(v2.getByteArrayLength() <= 0x8000) {
                        goto label_45;
                    }

                    Log.w("NfcImpl", "Cannot read data from NFC tag. NfcMessage exceeds allowed size.", new Object[0]);
                    return;
                }
                catch(IOException ) {
                label_35:
                    Log.w("NfcImpl", "Cannot read data from NFC tag. IO_ERROR.", new Object[0]);
                }
                catch(TagLostException ) {
                label_41:
                    Log.w("NfcImpl", "Cannot read data from NFC tag. Tag is lost.", new Object[0]);
                }

            label_45:
                if(v2 != null) {
                    this.notifyMatchingWatchers(v2);
                }

                return;
            }
        }
    }

    public void push(NfcMessage arg7, NfcPushOptions arg8, PushResponse arg9) {
        if(!this.checkIfReady(((Callback1)arg9))) {
            return;
        }

        if(!NfcMessageValidator.isValid(arg7)) {
            arg9.call(this.createError(4));
            return;
        }

        if(arg8.target != 1 && arg8.timeout >= 0 && (arg8.timeout <= 9223372036854776000 || (Double.isInfinite(arg8.timeout)))) {
            if(this.mPendingPushOperation != null) {
                this.mPendingPushOperation.complete(this.createError(5));
                this.cancelPushTimeoutTask();
            }

            this.mPendingPushOperation = new PendingPushOperation(arg7, arg8, arg9);
            this.schedulePushTimeoutTask(arg8);
            this.enableReaderModeIfNeeded();
            this.processPendingPushOperation();
            return;
        }

        arg9.call(this.createError(1));
    }

    public void resumeNfcOperations() {
        this.enableReaderModeIfNeeded();
    }

    private void schedulePushTimeoutTask(NfcPushOptions arg5) {
        if(Double.isInfinite(arg5.timeout)) {
            return;
        }

        this.mPushTimeoutRunnable = new Runnable() {
            public void run() {
                NfcImpl.this.completePendingPushOperation(NfcImpl.this.createError(6));
            }
        };
        this.mPushTimeoutHandler.postDelayed(this.mPushTimeoutRunnable, ((long)arg5.timeout));
    }

    protected void setActivity(Activity arg1) {
        this.disableReaderMode();
        this.mActivity = arg1;
        this.enableReaderModeIfNeeded();
    }

    public void setClient(NfcClient arg1) {
        this.mClient = arg1;
    }

    public void suspendNfcOperations() {
        this.disableReaderMode();
    }

    public void watch(NfcWatchOptions arg3, WatchResponse arg4) {
        if(!this.checkIfReady(arg4)) {
            return;
        }

        int v0 = this.mWatcherId + 1;
        this.mWatcherId = v0;
        this.mWatchers.put(v0, arg3);
        arg4.call(Integer.valueOf(v0), null);
        this.enableReaderModeIfNeeded();
        this.processPendingWatchOperations();
    }
}

