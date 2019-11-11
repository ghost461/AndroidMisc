package org.chromium.device.nfc;

import android.net.Uri;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.os.Build$VERSION;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.chromium.base.ApiCompatibilityUtils;
import org.chromium.base.Log;
import org.chromium.device.mojom.NfcMessage;
import org.chromium.device.mojom.NfcRecord;

public final class NfcTypeConverter {
    private static final String CHARSET_UTF16 = ";charset=UTF-16";
    private static final String CHARSET_UTF8 = ";charset=UTF-8";
    private static final String DOMAIN = "w3.org";
    private static final String JSON_MIME = "application/json";
    private static final String TAG = "NfcTypeConverter";
    private static final String TEXT_MIME = "text/plain";
    private static final String TYPE = "webnfc";
    private static final String WEBNFC_URN = "w3.org:webnfc";

    public NfcTypeConverter() {
        super();
    }

    private static NfcRecord createEmptyRecord() {
        NfcRecord v0 = new NfcRecord();
        v0.recordType = 0;
        v0.mediaType = "";
        v0.data = new byte[0];
        return v0;
    }

    private static NfcRecord createMIMERecord(String arg2, byte[] arg3) {
        NfcRecord v0 = new NfcRecord();
        v0.recordType = arg2.equals("application/json") ? 3 : 4;
        v0.mediaType = arg2;
        v0.data = arg3;
        return v0;
    }

    private static NfcRecord createTextRecord(byte[] arg4) {
        NfcRecord v1 = null;
        if(arg4.length == 0) {
            return v1;
        }

        NfcRecord v0 = new NfcRecord();
        v0.recordType = 1;
        v0.mediaType = "text/plain";
        int v3 = (arg4[0] & 0x3F) + 1;
        if(v3 > arg4.length) {
            return v1;
        }

        v0.data = Arrays.copyOfRange(arg4, v3, arg4.length);
        return v0;
    }

    private static NfcRecord createURLRecord(Uri arg2) {
        if(arg2 == null) {
            return null;
        }

        NfcRecord v0 = new NfcRecord();
        v0.recordType = 2;
        v0.mediaType = "text/plain";
        v0.data = ApiCompatibilityUtils.getBytesUtf8(arg2.toString());
        return v0;
    }

    private static NfcRecord createWellKnownRecord(NdefRecord arg2) {
        if(Arrays.equals(arg2.getType(), NdefRecord.RTD_URI)) {
            return NfcTypeConverter.createURLRecord(arg2.toUri());
        }

        if(Arrays.equals(arg2.getType(), NdefRecord.RTD_TEXT)) {
            return NfcTypeConverter.createTextRecord(arg2.getPayload());
        }

        return null;
    }

    public static NdefMessage emptyNdefMessage() {
        return new NdefMessage(new NdefRecord(0, null, null, null), new NdefRecord[0]);
    }

    private static String getCharset(NfcRecord arg2) {
        if(arg2.mediaType.endsWith(";charset=UTF-8")) {
            return "UTF-8";
        }

        if(arg2.mediaType.endsWith(";charset=UTF-16")) {
            return "UTF-16LE";
        }

        Log.w("NfcTypeConverter", "Unknown charset, defaulting to UTF-8.", new Object[0]);
        return "UTF-8";
    }

    public static NdefMessage toNdefMessage(NfcMessage arg3) throws InvalidNfcMessageException {
        try {
            ArrayList v0 = new ArrayList();
            int v1;
            for(v1 = 0; v1 < arg3.data.length; ++v1) {
                ((List)v0).add(NfcTypeConverter.toNdefRecord(arg3.data[v1]));
            }

            ((List)v0).add(NdefRecord.createExternal("w3.org", "webnfc", ApiCompatibilityUtils.getBytesUtf8(arg3.url)));
            NdefRecord[] v3 = new NdefRecord[((List)v0).size()];
            ((List)v0).toArray(((Object[])v3));
            return new NdefMessage(v3);
        }
        catch(IllegalArgumentException ) {
            throw new InvalidNfcMessageException();
        }
    }

    private static NdefRecord toNdefRecord(NfcRecord arg3) throws InvalidNfcMessageException, IllegalArgumentException, UnsupportedEncodingException {
        switch(arg3.recordType) {
            case 0: {
                goto label_29;
            }
            case 1: {
                goto label_15;
            }
            case 2: {
                goto label_9;
            }
            case 3: 
            case 4: {
                goto label_5;
            }
        }

        throw new InvalidNfcMessageException();
    label_5:
        return NdefRecord.createMime(arg3.mediaType, arg3.data);
    label_9:
        return NdefRecord.createUri(new String(arg3.data, NfcTypeConverter.getCharset(arg3)));
    label_29:
        return new NdefRecord(0, null, null, null);
    label_15:
        if(Build$VERSION.SDK_INT >= 21) {
            return NdefRecord.createTextRecord("en-US", new String(arg3.data, NfcTypeConverter.getCharset(arg3)));
        }

        return NdefRecord.createMime("text/plain", arg3.data);
    }

    public static NfcMessage toNfcMessage(NdefMessage arg6) throws UnsupportedEncodingException {
        NdefRecord[] v6 = arg6.getRecords();
        NfcMessage v0 = new NfcMessage();
        ArrayList v1 = new ArrayList();
        int v2;
        for(v2 = 0; v2 < v6.length; ++v2) {
            if(v6[v2].getTnf() != 4 || !Arrays.equals(v6[v2].getType(), ApiCompatibilityUtils.getBytesUtf8("w3.org:webnfc"))) {
                NfcRecord v3 = NfcTypeConverter.toNfcRecord(v6[v2]);
                if(v3 != null) {
                    ((List)v1).add(v3);
                }
            }
            else {
                v0.url = new String(v6[v2].getPayload(), "UTF-8");
            }
        }

        v0.data = new NfcRecord[((List)v1).size()];
        ((List)v1).toArray(v0.data);
        return v0;
    }

    private static NfcRecord toNfcRecord(NdefRecord arg3) throws UnsupportedEncodingException {
        switch(arg3.getTnf()) {
            case 0: {
                goto label_16;
            }
            case 1: {
                goto label_14;
            }
            case 2: {
                goto label_7;
            }
            case 3: {
                goto label_4;
            }
        }

        return null;
    label_4:
        return NfcTypeConverter.createURLRecord(arg3.toUri());
    label_7:
        return NfcTypeConverter.createMIMERecord(new String(arg3.getType(), "UTF-8"), arg3.getPayload());
    label_14:
        return NfcTypeConverter.createWellKnownRecord(arg3);
    label_16:
        return NfcTypeConverter.createEmptyRecord();
    }
}

