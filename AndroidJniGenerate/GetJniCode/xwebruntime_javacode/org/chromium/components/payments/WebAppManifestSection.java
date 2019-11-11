package org.chromium.components.payments;

public final class WebAppManifestSection {
    public final byte[][] fingerprints;
    public final String id;
    public final long minVersion;

    public WebAppManifestSection(String arg1, long arg2, int arg4) {
        super();
        this.id = arg1;
        this.minVersion = arg2;
        this.fingerprints = new byte[arg4][];
    }

    public WebAppManifestSection(String arg1, long arg2, byte[][] arg4) {
        super();
        this.id = arg1;
        this.minVersion = arg2;
        this.fingerprints = arg4;
    }
}

