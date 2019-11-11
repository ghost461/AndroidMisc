package org.chromium.device.nfc;

import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.Tag;
import android.nfc.TagLostException;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.nfc.tech.TagTechnology;
import java.io.IOException;

public class NfcTagHandler {
    class NdefFormattableHandler implements TagTechnologyHandler {
        private final NdefFormatable mNdefFormattable;

        NdefFormattableHandler(NdefFormatable arg1) {
            super();
            this.mNdefFormattable = arg1;
        }

        public NdefMessage read() throws FormatException {
            return NfcTypeConverter.emptyNdefMessage();
        }

        public void write(NdefMessage arg2) throws IOException, TagLostException, FormatException, IllegalStateException {
            this.mNdefFormattable.format(arg2);
        }
    }

    class NdefHandler implements TagTechnologyHandler {
        private final Ndef mNdef;

        NdefHandler(Ndef arg1) {
            super();
            this.mNdef = arg1;
        }

        public NdefMessage read() throws IOException, TagLostException, FormatException, IllegalStateException {
            return this.mNdef.getNdefMessage();
        }

        public void write(NdefMessage arg2) throws IOException, TagLostException, FormatException, IllegalStateException {
            this.mNdef.writeNdefMessage(arg2);
        }
    }

    interface TagTechnologyHandler {
        NdefMessage read() throws IOException, TagLostException, FormatException, IllegalStateException;

        void write(NdefMessage arg1) throws IOException, TagLostException, FormatException, IllegalStateException;
    }

    private final TagTechnology mTech;
    private final TagTechnologyHandler mTechHandler;
    private boolean mWasConnected;

    protected NfcTagHandler(TagTechnology arg1, TagTechnologyHandler arg2) {
        super();
        this.mTech = arg1;
        this.mTechHandler = arg2;
    }

    public void close() throws IOException {
        this.mTech.close();
    }

    public void connect() throws IOException, TagLostException {
        if(!this.mTech.isConnected()) {
            this.mTech.connect();
            this.mWasConnected = true;
        }
    }

    public static NfcTagHandler create(Tag arg2) {
        NfcTagHandler v0 = null;
        if(arg2 == null) {
            return v0;
        }

        Ndef v1 = Ndef.get(arg2);
        if(v1 != null) {
            return new NfcTagHandler(((TagTechnology)v1), new NdefHandler(v1));
        }

        NdefFormatable v2 = NdefFormatable.get(arg2);
        if(v2 != null) {
            return new NfcTagHandler(((TagTechnology)v2), new NdefFormattableHandler(v2));
        }

        return v0;
    }

    public boolean isConnected() {
        return this.mTech.isConnected();
    }

    public boolean isTagOutOfRange() {
        try {
            this.connect();
            return 0;
        }
        catch(IOException ) {
            return this.mWasConnected;
        }
    }

    public NdefMessage read() throws IOException, TagLostException, FormatException, IllegalStateException {
        return this.mTechHandler.read();
    }

    public void write(NdefMessage arg2) throws IOException, TagLostException, FormatException, IllegalStateException {
        this.mTechHandler.write(arg2);
    }
}

