package org.chromium.gpu.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.gfx.mojom.BufferUsageAndFormat;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class GpuPreferences extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 40;
    private static final DataHeader[] VERSION_ARRAY;
    public boolean compileShaderAlwaysSucceeds;
    public boolean disableAcceleratedVideoDecode;
    public boolean disableAcceleratedVideoEncode;
    public boolean disableBiplanarGpuMemoryBuffersForVideoFrames;
    public boolean disableGlErrorLimit;
    public boolean disableGlslTranslator;
    public boolean disableGpuDriverBugWorkarounds;
    public boolean disableGpuProgramCache;
    public boolean disableGpuShaderDiskCache;
    public boolean disableGpuWatchdog;
    public boolean disableShaderNameHashing;
    public boolean disableSoftwareRasterizer;
    public boolean emulateShaderPrecision;
    public int enableAcceleratedVpxDecode;
    public boolean enableGpuCommandLogging;
    public boolean enableGpuDebugging;
    public boolean enableGpuDriverDebugLogging;
    public boolean enableGpuServiceLogging;
    public boolean enableGpuServiceLoggingGpu;
    public boolean enableGpuServiceTracing;
    public boolean enableLowLatencyDxva;
    public boolean enableMediaFoundationVeaOnWindows7;
    public boolean enableNv12DxgiVideo;
    public boolean enableRasterDecoder;
    public boolean enableThreadedTextureMailboxes;
    public boolean enableZeroCopyDxgiVideo;
    public boolean enforceGlMinimums;
    public int forceGpuMemAvailable;
    public boolean glShaderIntermOutput;
    public int gpuProgramCacheSize;
    public boolean gpuSandboxStartEarly;
    public boolean gpuStartupDialog;
    public boolean ignoreGpuBlacklist;
    public boolean inProcessGpu;
    public boolean logGpuControlListDecisions;
    public boolean singleProcess;
    public BufferUsageAndFormat[] textureTargetExceptionList;
    public boolean usePassthroughCmdDecoder;

    static {
        GpuPreferences.VERSION_ARRAY = new DataHeader[]{new DataHeader(40, 0)};
        GpuPreferences.DEFAULT_STRUCT_INFO = GpuPreferences.VERSION_ARRAY[0];
    }

    public GpuPreferences() {
        this(0);
    }

    private GpuPreferences(int arg2) {
        super(40, arg2);
    }

    public static GpuPreferences decode(Decoder arg12) {
        GpuPreferences v1;
        if(arg12 == null) {
            return null;
        }

        arg12.increaseStackDepth();
        try {
            v1 = new GpuPreferences(arg12.readAndValidateDataHeader(GpuPreferences.VERSION_ARRAY).elementsOrVersion);
            int v2 = 8;
            v1.singleProcess = arg12.readBoolean(v2, 0);
            v1.inProcessGpu = arg12.readBoolean(v2, 1);
            v1.disableAcceleratedVideoDecode = arg12.readBoolean(v2, 2);
            v1.disableAcceleratedVideoEncode = arg12.readBoolean(v2, 3);
            v1.gpuStartupDialog = arg12.readBoolean(v2, 4);
            v1.disableGpuWatchdog = arg12.readBoolean(v2, 5);
            v1.gpuSandboxStartEarly = arg12.readBoolean(v2, 6);
            v1.enableLowLatencyDxva = arg12.readBoolean(v2, 7);
            v1.enableZeroCopyDxgiVideo = arg12.readBoolean(9, 0);
            v1.enableNv12DxgiVideo = arg12.readBoolean(9, 1);
            v1.enableMediaFoundationVeaOnWindows7 = arg12.readBoolean(9, 2);
            v1.disableSoftwareRasterizer = arg12.readBoolean(9, 3);
            v1.logGpuControlListDecisions = arg12.readBoolean(9, 4);
            v1.compileShaderAlwaysSucceeds = arg12.readBoolean(9, 5);
            v1.disableGlErrorLimit = arg12.readBoolean(9, 6);
            v1.disableGlslTranslator = arg12.readBoolean(9, 7);
            v1.disableShaderNameHashing = arg12.readBoolean(10, 0);
            v1.enableGpuCommandLogging = arg12.readBoolean(10, 1);
            v1.enableGpuDebugging = arg12.readBoolean(10, 2);
            v1.enableGpuServiceLoggingGpu = arg12.readBoolean(10, 3);
            v1.enableGpuDriverDebugLogging = arg12.readBoolean(10, 4);
            v1.disableGpuProgramCache = arg12.readBoolean(10, 5);
            v1.enforceGlMinimums = arg12.readBoolean(10, 6);
            v1.disableGpuShaderDiskCache = arg12.readBoolean(10, 7);
            v1.enableThreadedTextureMailboxes = arg12.readBoolean(11, 0);
            v1.glShaderIntermOutput = arg12.readBoolean(11, 1);
            v1.emulateShaderPrecision = arg12.readBoolean(11, 2);
            v1.enableRasterDecoder = arg12.readBoolean(11, 3);
            v1.enableGpuServiceLogging = arg12.readBoolean(11, 4);
            v1.enableGpuServiceTracing = arg12.readBoolean(11, 5);
            v1.usePassthroughCmdDecoder = arg12.readBoolean(11, 6);
            v1.disableBiplanarGpuMemoryBuffersForVideoFrames = arg12.readBoolean(11, 7);
            v1.enableAcceleratedVpxDecode = arg12.readInt(12);
            VpxDecodeVendors.validate(v1.enableAcceleratedVpxDecode);
            v1.forceGpuMemAvailable = arg12.readInt(16);
            v1.gpuProgramCacheSize = arg12.readInt(20);
            Decoder v4 = arg12.readPointer(24, false);
            DataHeader v5 = v4.readDataHeaderForPointerArray(-1);
            v1.textureTargetExceptionList = new BufferUsageAndFormat[v5.elementsOrVersion];
            int v6;
            for(v6 = 0; v6 < v5.elementsOrVersion; ++v6) {
                v1.textureTargetExceptionList[v6] = BufferUsageAndFormat.decode(v4.readPointer(v6 * 8 + v2, false));
            }

            v1.disableGpuDriverBugWorkarounds = arg12.readBoolean(0x20, 0);
            v1.ignoreGpuBlacklist = arg12.readBoolean(0x20, 1);
        }
        catch(Throwable v0) {
            goto label_122;
        }

        arg12.decreaseStackDepth();
        return v1;
    label_122:
        arg12.decreaseStackDepth();
        throw v0;
    }

    public static GpuPreferences deserialize(ByteBuffer arg2) {
        return GpuPreferences.deserialize(new Message(arg2, new ArrayList()));
    }

    public static GpuPreferences deserialize(Message arg1) {
        return GpuPreferences.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg12) {
        arg12 = arg12.getEncoderAtDataOffset(GpuPreferences.DEFAULT_STRUCT_INFO);
        int v2 = 8;
        arg12.encode(this.singleProcess, v2, 0);
        arg12.encode(this.inProcessGpu, v2, 1);
        arg12.encode(this.disableAcceleratedVideoDecode, v2, 2);
        arg12.encode(this.disableAcceleratedVideoEncode, v2, 3);
        arg12.encode(this.gpuStartupDialog, v2, 4);
        arg12.encode(this.disableGpuWatchdog, v2, 5);
        arg12.encode(this.gpuSandboxStartEarly, v2, 6);
        arg12.encode(this.enableLowLatencyDxva, v2, 7);
        arg12.encode(this.enableZeroCopyDxgiVideo, 9, 0);
        arg12.encode(this.enableNv12DxgiVideo, 9, 1);
        arg12.encode(this.enableMediaFoundationVeaOnWindows7, 9, 2);
        arg12.encode(this.disableSoftwareRasterizer, 9, 3);
        arg12.encode(this.logGpuControlListDecisions, 9, 4);
        arg12.encode(this.compileShaderAlwaysSucceeds, 9, 5);
        arg12.encode(this.disableGlErrorLimit, 9, 6);
        arg12.encode(this.disableGlslTranslator, 9, 7);
        arg12.encode(this.disableShaderNameHashing, 10, 0);
        arg12.encode(this.enableGpuCommandLogging, 10, 1);
        arg12.encode(this.enableGpuDebugging, 10, 2);
        arg12.encode(this.enableGpuServiceLoggingGpu, 10, 3);
        arg12.encode(this.enableGpuDriverDebugLogging, 10, 4);
        arg12.encode(this.disableGpuProgramCache, 10, 5);
        arg12.encode(this.enforceGlMinimums, 10, 6);
        arg12.encode(this.disableGpuShaderDiskCache, 10, 7);
        arg12.encode(this.enableThreadedTextureMailboxes, 11, 0);
        arg12.encode(this.glShaderIntermOutput, 11, 1);
        arg12.encode(this.emulateShaderPrecision, 11, 2);
        arg12.encode(this.enableRasterDecoder, 11, 3);
        arg12.encode(this.enableGpuServiceLogging, 11, 4);
        arg12.encode(this.enableGpuServiceTracing, 11, 5);
        arg12.encode(this.usePassthroughCmdDecoder, 11, 6);
        arg12.encode(this.disableBiplanarGpuMemoryBuffersForVideoFrames, 11, 7);
        arg12.encode(this.enableAcceleratedVpxDecode, 12);
        arg12.encode(this.forceGpuMemAvailable, 16);
        arg12.encode(this.gpuProgramCacheSize, 20);
        int v4 = 24;
        if(this.textureTargetExceptionList == null) {
            arg12.encodeNullPointer(v4, false);
        }
        else {
            Encoder v0 = arg12.encodePointerArray(this.textureTargetExceptionList.length, v4, -1);
            for(v4 = 0; v4 < this.textureTargetExceptionList.length; ++v4) {
                v0.encode(this.textureTargetExceptionList[v4], v4 * 8 + v2, false);
            }
        }

        arg12.encode(this.disableGpuDriverBugWorkarounds, 0x20, 0);
        arg12.encode(this.ignoreGpuBlacklist, 0x20, 1);
    }
}

