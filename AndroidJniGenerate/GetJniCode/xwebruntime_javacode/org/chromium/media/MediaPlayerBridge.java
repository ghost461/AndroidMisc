package org.chromium.media;

import android.content.Context;
import android.media.MediaPlayer$OnBufferingUpdateListener;
import android.media.MediaPlayer$OnCompletionListener;
import android.media.MediaPlayer$OnErrorListener;
import android.media.MediaPlayer$OnPreparedListener;
import android.media.MediaPlayer$OnSeekCompleteListener;
import android.media.MediaPlayer$OnVideoSizeChangedListener;
import android.media.MediaPlayer$TrackInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build$VERSION;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.util.Base64InputStream;
import android.view.Surface;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.chromium.base.ApiCompatibilityUtils;
import org.chromium.base.ContextUtils;
import org.chromium.base.Log;
import org.chromium.base.StreamUtil;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="media") public class MediaPlayerBridge {
    public class AllowedOperations {
        private final boolean mCanPause;
        private final boolean mCanSeekBackward;
        private final boolean mCanSeekForward;

        public AllowedOperations(boolean arg1, boolean arg2, boolean arg3) {
            super();
            this.mCanPause = arg1;
            this.mCanSeekForward = arg2;
            this.mCanSeekBackward = arg3;
        }

        @CalledByNative(value="AllowedOperations") private boolean canPause() {
            return this.mCanPause;
        }

        @CalledByNative(value="AllowedOperations") private boolean canSeekBackward() {
            return this.mCanSeekBackward;
        }

        @CalledByNative(value="AllowedOperations") private boolean canSeekForward() {
            return this.mCanSeekForward;
        }
    }

    class LoadDataUriTask extends AsyncTask {
        private final String mData;
        private File mTempFile;

        static {
        }

        public LoadDataUriTask(MediaPlayerBridge arg1, String arg2) {
            MediaPlayerBridge.this = arg1;
            super();
            this.mData = arg2;
        }

        private void deleteFile() {
            if(this.mTempFile == null) {
                return;
            }

            if(!this.mTempFile.delete()) {
                Log.e("cr.media", "Failed to delete temporary file: " + this.mTempFile, new Object[0]);
            }
        }

        protected Boolean doInBackground(Void[] arg6) {
            Boolean v6_1;
            Boolean v0_2;
            FileOutputStream v1;
            FileOutputStream v0 = null;
            try {
                this.mTempFile = File.createTempFile("decoded", "mediadata");
                v1 = new FileOutputStream(this.mTempFile);
            }
            catch(IOException ) {
                goto label_34;
            }
            catch(Throwable v6) {
                goto label_32;
            }

            try {
                Base64InputStream v2 = new Base64InputStream(new ByteArrayInputStream(ApiCompatibilityUtils.getBytesUtf8(this.mData)), 0);
                byte[] v0_1 = new byte[0x400];
                while(true) {
                    int v3 = v2.read(v0_1);
                    if(v3 == -1) {
                        break;
                    }

                    v1.write(v0_1, 0, v3);
                }

                v2.close();
                v0_2 = Boolean.valueOf(true);
            }
            catch(Throwable v6) {
                goto label_37;
            }
            catch(IOException ) {
                goto label_29;
            }

            StreamUtil.closeQuietly(((Closeable)v1));
            return v0_2;
        label_29:
            v0 = v1;
            try {
            label_34:
                v6_1 = Boolean.valueOf(false);
            }
            catch(Throwable v6) {
            label_32:
                v1 = v0;
                goto label_37;
            }

            StreamUtil.closeQuietly(((Closeable)v0));
            return v6_1;
        label_37:
            StreamUtil.closeQuietly(((Closeable)v1));
            throw v6;
        }

        protected Object doInBackground(Object[] arg1) {
            return this.doInBackground(((Void[])arg1));
        }

        protected void onPostExecute(Boolean arg4) {
            if(this.isCancelled()) {
                this.deleteFile();
                return;
            }

            if(arg4.booleanValue()) {
                try {
                    MediaPlayerBridge.this.getLocalPlayer().setDataSource(ContextUtils.getApplicationContext(), Uri.fromFile(this.mTempFile));
                }
                catch(IOException ) {
                    arg4 = Boolean.valueOf(false);
                }
            }

            this.deleteFile();
            MediaPlayerBridge.this.nativeOnDidSetDataUriDataSource(MediaPlayerBridge.this.mNativeMediaPlayerBridge, arg4.booleanValue());
        }

        protected void onPostExecute(Object arg1) {
            this.onPostExecute(((Boolean)arg1));
        }
    }

    public class ResourceLoadingFilter {
        public ResourceLoadingFilter() {
            super();
        }

        public boolean shouldOverrideResourceLoading(MediaPlayer arg1, Context arg2, Uri arg3) {
            return 0;
        }
    }

    private static final String TAG = "cr.media";
    private LoadDataUriTask mLoadDataUriTask;
    private long mNativeMediaPlayerBridge;
    private MediaPlayer mPlayer;
    private static ResourceLoadingFilter sResourceLoadFilter;

    static {
    }

    protected MediaPlayerBridge() {
        super();
    }

    protected MediaPlayerBridge(long arg1) {
        super();
        this.mNativeMediaPlayerBridge = arg1;
    }

    static long access$000(MediaPlayerBridge arg2) {
        return arg2.mNativeMediaPlayerBridge;
    }

    static void access$100(MediaPlayerBridge arg0, long arg1, boolean arg3) {
        arg0.nativeOnDidSetDataUriDataSource(arg1, arg3);
    }

    private void cancelLoadDataUriTask() {
        if(this.mLoadDataUriTask != null) {
            this.mLoadDataUriTask.cancel(true);
            this.mLoadDataUriTask = null;
        }
    }

    @CalledByNative private static MediaPlayerBridge create(long arg1) {
        return new MediaPlayerBridge(arg1);
    }

    @CalledByNative protected void destroy() {
        this.cancelLoadDataUriTask();
        this.mNativeMediaPlayerBridge = 0;
    }

    @CalledByNative protected AllowedOperations getAllowedOperations() {
        boolean v7;
        boolean v6_1;
        MediaPlayer v0 = this.getLocalPlayer();
        boolean v2 = true;
        try {
            Method v3 = v0.getClass().getDeclaredMethod("getMetadata", Boolean.TYPE, Boolean.TYPE);
            v3.setAccessible(true);
            Object v0_5 = v3.invoke(v0, Boolean.valueOf(false), Boolean.valueOf(false));
            if(v0_5 != null) {
                Class v3_1 = v0_5.getClass();
                Method v4 = v3_1.getDeclaredMethod("has", Integer.TYPE);
                Method v5 = v3_1.getDeclaredMethod("getBoolean", Integer.TYPE);
                int v6 = v3_1.getField("PAUSE_AVAILABLE").get(null).intValue();
                int v8 = v3_1.getField("SEEK_FORWARD_AVAILABLE").get(null).intValue();
                int v3_2 = v3_1.getField("SEEK_BACKWARD_AVAILABLE").get(null).intValue();
                v4.setAccessible(true);
                v5.setAccessible(true);
                if(v4.invoke(v0_5, new Object[]{Integer.valueOf(v6)}).booleanValue()) {
                    if(!v5.invoke(v0_5, new Object[]{Integer.valueOf(v6)}).booleanValue()) {
                        goto label_59;
                    }

                    goto label_58;
                }
                else {
                    goto label_61;
                }
            }
            else {
                goto label_111;
            }

            goto label_113;
        }
        catch(NoSuchFieldException v0_1) {
            v6_1 = true;
            goto label_118;
        }
        catch(IllegalAccessException v0_2) {
            v6_1 = true;
            goto label_131;
        }
        catch(InvocationTargetException v0_3) {
            v6_1 = true;
            goto label_144;
        }
        catch(NoSuchMethodException v0_4) {
            v6_1 = true;
            goto label_157;
        }

    label_58:
        goto label_61;
    label_59:
        v6_1 = false;
        goto label_62;
    label_61:
        v6_1 = true;
        try {
        label_62:
            if(v4.invoke(v0_5, new Object[]{Integer.valueOf(v8)}).booleanValue()) {
                if(!v5.invoke(v0_5, new Object[]{Integer.valueOf(v8)}).booleanValue()) {
                    goto label_75;
                }

                goto label_74;
            }
            else {
                goto label_77;
            }
        }
        catch(NoSuchFieldException v0_1) {
        }
        catch(IllegalAccessException v0_2) {
        label_131:
            v7 = true;
            goto label_132;
        }
        catch(InvocationTargetException v0_3) {
        label_144:
            v7 = true;
            goto label_145;
        }
        catch(NoSuchMethodException v0_4) {
        label_157:
            v7 = true;
            goto label_158;
        }

    label_118:
        v7 = true;
        goto label_119;
    label_74:
        goto label_77;
    label_75:
        v7 = false;
        goto label_78;
    label_77:
        v7 = true;
        try {
        label_78:
            if(v4.invoke(v0_5, new Object[]{Integer.valueOf(v3_2)}).booleanValue()) {
                if(!v5.invoke(v0_5, new Object[]{Integer.valueOf(v3_2)}).booleanValue()) {
                    goto label_91;
                }

                goto label_90;
            }

            goto label_92;
        }
        catch(NoSuchFieldException v0_1) {
        }
        catch(IllegalAccessException v0_2) {
        label_132:
            Log.e("cr.media", "Cannot access metadata: " + v0_2, new Object[0]);
            goto label_167;
        }
        catch(InvocationTargetException v0_3) {
        label_145:
            Log.e("cr.media", "Cannot invoke MediaPlayer.getMetadata() method: " + v0_3, new Object[0]);
            goto label_167;
        }
        catch(NoSuchMethodException v0_4) {
        label_158:
            Log.e("cr.media", "Cannot find getMetadata() method: " + v0_4, new Object[0]);
            goto label_167;
        }

    label_119:
        Log.e("cr.media", "Cannot find matching fields in Metadata class: " + v0_1, new Object[0]);
        goto label_167;
    label_90:
        goto label_92;
    label_91:
        v2 = false;
    label_92:
        boolean v0_6 = v2;
        v2 = v7;
        goto label_113;
    label_111:
        v0_6 = true;
        v6_1 = true;
    label_113:
        v7 = v2;
        v2 = v0_6;
    label_167:
        return new AllowedOperations(v6_1, v7, v2);
    }

    @CalledByNative protected int getCurrentPosition() {
        return this.getLocalPlayer().getCurrentPosition();
    }

    @CalledByNative protected int getDuration() {
        return this.getLocalPlayer().getDuration();
    }

    protected MediaPlayer getLocalPlayer() {
        if(this.mPlayer == null) {
            this.mPlayer = new MediaPlayer();
        }

        return this.mPlayer;
    }

    @CalledByNative protected int getVideoHeight() {
        return this.getLocalPlayer().getVideoHeight();
    }

    @CalledByNative protected int getVideoWidth() {
        return this.getLocalPlayer().getVideoWidth();
    }

    @CalledByNative protected boolean hasAudio() {
        return this.hasTrack(2);
    }

    private boolean hasTrack(int arg8) {
        int v4;
        try {
            MediaPlayer$TrackInfo[] v1 = this.getLocalPlayer().getTrackInfo();
            if(v1.length == 0) {
                return 1;
            }

            int v2 = v1.length;
            v4 = 0;
            while(true) {
            label_9:
                if(v4 >= v2) {
                    return 0;
                }

                MediaPlayer$TrackInfo v5 = v1[v4];
                if(arg8 == v5.getTrackType()) {
                    return 1;
                }

                if(v5.getTrackType() != 0) {
                    goto label_17;
                }

                return 1;
            }
        }
        catch(RuntimeException ) {
            return 1;
        }

        return 1;
    label_17:
        ++v4;
        goto label_9;
        return 0;
    }

    @CalledByNative protected boolean hasVideo() {
        return this.hasTrack(1);
    }

    @CalledByNative protected boolean isPlaying() {
        return this.getLocalPlayer().isPlaying();
    }

    private native void nativeOnDidSetDataUriDataSource(long arg1, boolean arg2) {
    }

    @CalledByNative protected void pause() {
        this.getLocalPlayer().pause();
    }

    @CalledByNative protected boolean prepareAsync() {
        try {
            this.getLocalPlayer().prepareAsync();
            return 1;
        }
        catch(Exception v2) {
            Log.e("cr.media", "Unable to prepare MediaPlayer.", new Object[]{v2});
            return 0;
        }
        catch(IllegalStateException v2_1) {
            Log.e("cr.media", "Unable to prepare MediaPlayer.", new Object[]{v2_1});
            return 0;
        }
    }

    @CalledByNative protected void release() {
        this.cancelLoadDataUriTask();
        this.getLocalPlayer().release();
    }

    @CalledByNative protected void seekTo(int arg2) throws IllegalStateException {
        this.getLocalPlayer().seekTo(arg2);
    }

    @CalledByNative protected boolean setDataSource(String arg3, String arg4, String arg5, boolean arg6) {
        Uri v3 = Uri.parse(arg3);
        HashMap v0 = new HashMap();
        if(arg6) {
            v0.put("x-hide-urls-from-log", "true");
        }

        if(!TextUtils.isEmpty(((CharSequence)arg4))) {
            v0.put("Cookie", arg4);
        }

        if(!TextUtils.isEmpty(((CharSequence)arg5))) {
            v0.put("User-Agent", arg5);
        }

        if(Build$VERSION.SDK_INT > 19) {
            v0.put("allow-cross-domain-redirect", "false");
        }

        try {
            this.getLocalPlayer().setDataSource(ContextUtils.getApplicationContext(), v3, ((Map)v0));
            return 1;
        }
        catch(Exception ) {
            return 0;
        }
    }

    @CalledByNative protected boolean setDataSourceFromFd(int arg7, long arg8, long arg10) {
        try {
            ParcelFileDescriptor v7_1 = ParcelFileDescriptor.adoptFd(arg7);
            this.getLocalPlayer().setDataSource(v7_1.getFileDescriptor(), arg8, arg10);
            v7_1.close();
            return 1;
        }
        catch(IOException v7) {
            Log.e("cr.media", "Failed to set data source from file descriptor: " + v7, new Object[0]);
            return 0;
        }
    }

    @CalledByNative protected boolean setDataUriDataSource(String arg6) {
        this.cancelLoadDataUriTask();
        if(!arg6.startsWith("data:")) {
            return 0;
        }

        int v0 = arg6.indexOf(44);
        if(v0 == -1) {
            return 0;
        }

        String v2 = arg6.substring(0, v0);
        arg6 = arg6.substring(v0 + 1);
        String[] v0_1 = v2.substring(5).split(";");
        if(v0_1.length != 2) {
            return 0;
        }

        if(!"base64".equals(v0_1[1])) {
            return 0;
        }

        this.mLoadDataUriTask = new LoadDataUriTask(this, arg6);
        this.mLoadDataUriTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        return 1;
    }

    protected void setOnBufferingUpdateListener(MediaPlayer$OnBufferingUpdateListener arg2) {
        this.getLocalPlayer().setOnBufferingUpdateListener(arg2);
    }

    protected void setOnCompletionListener(MediaPlayer$OnCompletionListener arg2) {
        this.getLocalPlayer().setOnCompletionListener(arg2);
    }

    protected void setOnErrorListener(MediaPlayer$OnErrorListener arg2) {
        this.getLocalPlayer().setOnErrorListener(arg2);
    }

    protected void setOnPreparedListener(MediaPlayer$OnPreparedListener arg2) {
        this.getLocalPlayer().setOnPreparedListener(arg2);
    }

    protected void setOnSeekCompleteListener(MediaPlayer$OnSeekCompleteListener arg2) {
        this.getLocalPlayer().setOnSeekCompleteListener(arg2);
    }

    protected void setOnVideoSizeChangedListener(MediaPlayer$OnVideoSizeChangedListener arg2) {
        this.getLocalPlayer().setOnVideoSizeChangedListener(arg2);
    }

    public static void setResourceLoadingFilter(ResourceLoadingFilter arg0) {
        MediaPlayerBridge.sResourceLoadFilter = arg0;
    }

    @CalledByNative protected void setSurface(Surface arg2) {
        this.getLocalPlayer().setSurface(arg2);
    }

    @CalledByNative protected void setVolume(double arg2) {
        float v2 = ((float)arg2);
        this.getLocalPlayer().setVolume(v2, v2);
    }

    @CalledByNative protected void start() {
        this.getLocalPlayer().start();
    }
}

