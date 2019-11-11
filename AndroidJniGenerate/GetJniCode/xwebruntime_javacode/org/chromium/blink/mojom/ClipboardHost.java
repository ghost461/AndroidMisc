package org.chromium.blink.mojom;

import java.util.Map;
import org.chromium.gfx.mojom.Size;
import org.chromium.mojo.bindings.Callbacks$Callback1;
import org.chromium.mojo.bindings.Callbacks$Callback2;
import org.chromium.mojo.bindings.Callbacks$Callback4;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;
import org.chromium.mojo.system.SharedBufferHandle;
import org.chromium.mojo_base.mojom.BigString16;
import org.chromium.mojo_base.mojom.String16;
import org.chromium.url.mojom.Url;

public interface ClipboardHost extends Interface {
    public interface GetSequenceNumberResponse extends Callback1 {
    }

    public interface IsFormatAvailableResponse extends Callback1 {
    }

    public interface Proxy extends ClipboardHost, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public interface ReadAvailableTypesResponse extends Callback2 {
    }

    public interface ReadCustomDataResponse extends Callback1 {
    }

    public interface ReadHtmlResponse extends Callback4 {
    }

    public interface ReadImageResponse extends Callback1 {
    }

    public interface ReadRtfResponse extends Callback1 {
    }

    public interface ReadTextResponse extends Callback1 {
    }

    public static final Manager MANAGER;

    static {
        ClipboardHost.MANAGER = ClipboardHost_Internal.MANAGER;
    }

    void commitWrite(int arg1);

    void getSequenceNumber(int arg1, GetSequenceNumberResponse arg2);

    void isFormatAvailable(int arg1, int arg2, IsFormatAvailableResponse arg3);

    void readAvailableTypes(int arg1, ReadAvailableTypesResponse arg2);

    void readCustomData(int arg1, String16 arg2, ReadCustomDataResponse arg3);

    void readHtml(int arg1, ReadHtmlResponse arg2);

    void readImage(int arg1, ReadImageResponse arg2);

    void readRtf(int arg1, ReadRtfResponse arg2);

    void readText(int arg1, ReadTextResponse arg2);

    void writeBookmark(int arg1, String arg2, String16 arg3);

    void writeCustomData(int arg1, Map arg2);

    void writeHtml(int arg1, BigString16 arg2, Url arg3);

    void writeImage(int arg1, Size arg2, SharedBufferHandle arg3);

    void writeSmartPasteMarker(int arg1);

    void writeText(int arg1, BigString16 arg2);
}

