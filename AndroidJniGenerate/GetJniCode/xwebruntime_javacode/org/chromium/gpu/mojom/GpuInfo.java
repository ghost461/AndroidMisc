package org.chromium.gpu.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo_base.mojom.TimeDelta;

public final class GpuInfo extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 0xC0;
    private static final DataHeader[] VERSION_ARRAY;
    public boolean amdSwitchable;
    public boolean canSupportThreadedTextureMailbox;
    public boolean directComposition;
    public boolean directRendering;
    public String driverDate;
    public String driverVendor;
    public String driverVersion;
    public String glExtensions;
    public String glRenderer;
    public int glResetNotificationStrategy;
    public String glVendor;
    public String glVersion;
    public String glWsExtensions;
    public String glWsVendor;
    public String glWsVersion;
    public GpuDevice gpu;
    public boolean inProcessGpu;
    public TimeDelta initializationTime;
    public boolean jpegDecodeAcceleratorSupported;
    public String machineModelName;
    public String machineModelVersion;
    public String maxMsaaSamples;
    public boolean optimus;
    public boolean passthroughCmdDecoder;
    public String pixelShaderVersion;
    public long rgbaVisual;
    public boolean sandboxed;
    public GpuDevice[] secondaryGpus;
    public boolean softwareRendering;
    public boolean supportsOverlays;
    public long systemVisual;
    public String vertexShaderVersion;
    public VideoDecodeAcceleratorCapabilities videoDecodeAcceleratorCapabilities;
    public VideoEncodeAcceleratorSupportedProfile[] videoEncodeAcceleratorSupportedProfiles;

    static {
        GpuInfo.VERSION_ARRAY = new DataHeader[]{new DataHeader(0xC0, 0)};
        GpuInfo.DEFAULT_STRUCT_INFO = GpuInfo.VERSION_ARRAY[0];
    }

    public GpuInfo() {
        this(0);
    }

    private GpuInfo(int arg2) {
        super(0xC0, arg2);
    }

    public static GpuInfo decode(Decoder arg9) {
        GpuInfo v1;
        if(arg9 == null) {
            return null;
        }

        arg9.increaseStackDepth();
        try {
            v1 = new GpuInfo(arg9.readAndValidateDataHeader(GpuInfo.VERSION_ARRAY).elementsOrVersion);
            int v0_1 = 8;
            v1.initializationTime = TimeDelta.decode(arg9.readPointer(v0_1, false));
            v1.optimus = arg9.readBoolean(16, 0);
            v1.amdSwitchable = arg9.readBoolean(16, 1);
            v1.softwareRendering = arg9.readBoolean(16, 2);
            v1.directRendering = arg9.readBoolean(16, 3);
            v1.sandboxed = arg9.readBoolean(16, 4);
            v1.inProcessGpu = arg9.readBoolean(16, 5);
            v1.passthroughCmdDecoder = arg9.readBoolean(16, 6);
            v1.directComposition = arg9.readBoolean(16, 7);
            v1.supportsOverlays = arg9.readBoolean(17, 0);
            v1.canSupportThreadedTextureMailbox = arg9.readBoolean(17, 1);
            v1.jpegDecodeAcceleratorSupported = arg9.readBoolean(17, 2);
            v1.glResetNotificationStrategy = arg9.readInt(20);
            v1.gpu = GpuDevice.decode(arg9.readPointer(24, false));
            Decoder v3 = arg9.readPointer(0x20, false);
            int v4 = -1;
            DataHeader v5 = v3.readDataHeaderForPointerArray(v4);
            v1.secondaryGpus = new GpuDevice[v5.elementsOrVersion];
            int v6;
            for(v6 = 0; v6 < v5.elementsOrVersion; ++v6) {
                v1.secondaryGpus[v6] = GpuDevice.decode(v3.readPointer(v6 * 8 + v0_1, false));
            }

            v1.driverVendor = arg9.readString(40, false);
            v1.driverVersion = arg9.readString(0x30, false);
            v1.driverDate = arg9.readString(56, false);
            v1.pixelShaderVersion = arg9.readString(0x40, false);
            v1.vertexShaderVersion = arg9.readString(72, false);
            v1.maxMsaaSamples = arg9.readString(80, false);
            v1.machineModelName = arg9.readString(88, false);
            v1.machineModelVersion = arg9.readString(0x60, false);
            v1.glVersion = arg9.readString(104, false);
            v1.glVendor = arg9.readString(0x70, false);
            v1.glRenderer = arg9.readString(120, false);
            v1.glExtensions = arg9.readString(0x80, false);
            v1.glWsVendor = arg9.readString(0x88, false);
            v1.glWsVersion = arg9.readString(0x90, false);
            v1.glWsExtensions = arg9.readString(0x98, false);
            v1.videoDecodeAcceleratorCapabilities = VideoDecodeAcceleratorCapabilities.decode(arg9.readPointer(0xA0, false));
            v3 = arg9.readPointer(0xA8, false);
            DataHeader v4_1 = v3.readDataHeaderForPointerArray(v4);
            v1.videoEncodeAcceleratorSupportedProfiles = new VideoEncodeAcceleratorSupportedProfile[v4_1.elementsOrVersion];
            int v5_1;
            for(v5_1 = 0; v5_1 < v4_1.elementsOrVersion; ++v5_1) {
                v1.videoEncodeAcceleratorSupportedProfiles[v5_1] = VideoEncodeAcceleratorSupportedProfile.decode(v3.readPointer(v5_1 * 8 + v0_1, false));
            }

            v1.systemVisual = arg9.readLong(0xB0);
            v1.rgbaVisual = arg9.readLong(0xB8);
        }
        catch(Throwable v0) {
            goto label_145;
        }

        arg9.decreaseStackDepth();
        return v1;
    label_145:
        arg9.decreaseStackDepth();
        throw v0;
    }

    public static GpuInfo deserialize(ByteBuffer arg2) {
        return GpuInfo.deserialize(new Message(arg2, new ArrayList()));
    }

    public static GpuInfo deserialize(Message arg1) {
        return GpuInfo.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg8) {
        Encoder v0;
        arg8 = arg8.getEncoderAtDataOffset(GpuInfo.DEFAULT_STRUCT_INFO);
        int v1 = 8;
        arg8.encode(this.initializationTime, v1, false);
        arg8.encode(this.optimus, 16, 0);
        arg8.encode(this.amdSwitchable, 16, 1);
        arg8.encode(this.softwareRendering, 16, 2);
        arg8.encode(this.directRendering, 16, 3);
        arg8.encode(this.sandboxed, 16, 4);
        arg8.encode(this.inProcessGpu, 16, 5);
        arg8.encode(this.passthroughCmdDecoder, 16, 6);
        arg8.encode(this.directComposition, 16, 7);
        arg8.encode(this.supportsOverlays, 17, 0);
        arg8.encode(this.canSupportThreadedTextureMailbox, 17, 1);
        arg8.encode(this.jpegDecodeAcceleratorSupported, 17, 2);
        arg8.encode(this.glResetNotificationStrategy, 20);
        arg8.encode(this.gpu, 24, false);
        int v3 = -1;
        int v4 = 0x20;
        if(this.secondaryGpus == null) {
            arg8.encodeNullPointer(v4, false);
        }
        else {
            v0 = arg8.encodePointerArray(this.secondaryGpus.length, v4, v3);
            for(v4 = 0; v4 < this.secondaryGpus.length; ++v4) {
                v0.encode(this.secondaryGpus[v4], v4 * 8 + v1, false);
            }
        }

        arg8.encode(this.driverVendor, 40, false);
        arg8.encode(this.driverVersion, 0x30, false);
        arg8.encode(this.driverDate, 56, false);
        arg8.encode(this.pixelShaderVersion, 0x40, false);
        arg8.encode(this.vertexShaderVersion, 72, false);
        arg8.encode(this.maxMsaaSamples, 80, false);
        arg8.encode(this.machineModelName, 88, false);
        arg8.encode(this.machineModelVersion, 0x60, false);
        arg8.encode(this.glVersion, 104, false);
        arg8.encode(this.glVendor, 0x70, false);
        arg8.encode(this.glRenderer, 120, false);
        arg8.encode(this.glExtensions, 0x80, false);
        arg8.encode(this.glWsVendor, 0x88, false);
        arg8.encode(this.glWsVersion, 0x90, false);
        arg8.encode(this.glWsExtensions, 0x98, false);
        arg8.encode(this.videoDecodeAcceleratorCapabilities, 0xA0, false);
        v4 = 0xA8;
        if(this.videoEncodeAcceleratorSupportedProfiles == null) {
            arg8.encodeNullPointer(v4, false);
        }
        else {
            v0 = arg8.encodePointerArray(this.videoEncodeAcceleratorSupportedProfiles.length, v4, v3);
            for(v3 = 0; v3 < this.videoEncodeAcceleratorSupportedProfiles.length; ++v3) {
                v0.encode(this.videoEncodeAcceleratorSupportedProfiles[v3], v3 * 8 + v1, false);
            }
        }

        arg8.encode(this.systemVisual, 0xB0);
        arg8.encode(this.rgbaVisual, 0xB8);
    }
}

