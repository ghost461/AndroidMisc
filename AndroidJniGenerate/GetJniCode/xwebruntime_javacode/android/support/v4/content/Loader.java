package android.support.v4.content;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.support.v4.util.DebugUtils;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public class Loader {
    public final class ForceLoadContentObserver extends ContentObserver {
        public ForceLoadContentObserver(Loader arg1) {
            Loader.this = arg1;
            super(new Handler());
        }

        public boolean deliverSelfNotifications() {
            return 1;
        }

        public void onChange(boolean arg1) {
            Loader.this.onContentChanged();
        }
    }

    public interface OnLoadCanceledListener {
        void onLoadCanceled(Loader arg1);
    }

    public interface OnLoadCompleteListener {
        void onLoadComplete(Loader arg1, Object arg2);
    }

    boolean mAbandoned;
    boolean mContentChanged;
    Context mContext;
    int mId;
    OnLoadCompleteListener mListener;
    OnLoadCanceledListener mOnLoadCanceledListener;
    boolean mProcessingChange;
    boolean mReset;
    boolean mStarted;

    public Loader(Context arg3) {
        super();
        this.mStarted = false;
        this.mAbandoned = false;
        this.mReset = true;
        this.mContentChanged = false;
        this.mProcessingChange = false;
        this.mContext = arg3.getApplicationContext();
    }

    public void abandon() {
        this.mAbandoned = true;
        this.onAbandon();
    }

    public boolean cancelLoad() {
        return this.onCancelLoad();
    }

    public void commitContentChanged() {
        this.mProcessingChange = false;
    }

    public String dataToString(Object arg3) {
        StringBuilder v0 = new StringBuilder(0x40);
        DebugUtils.buildShortClassTag(arg3, v0);
        v0.append("}");
        return v0.toString();
    }

    public void deliverCancellation() {
        if(this.mOnLoadCanceledListener != null) {
            this.mOnLoadCanceledListener.onLoadCanceled(this);
        }
    }

    public void deliverResult(Object arg2) {
        if(this.mListener != null) {
            this.mListener.onLoadComplete(this, arg2);
        }
    }

    public void dump(String arg1, FileDescriptor arg2, PrintWriter arg3, String[] arg4) {
        arg3.print(arg1);
        arg3.print("mId=");
        arg3.print(this.mId);
        arg3.print(" mListener=");
        arg3.println(this.mListener);
        if((this.mStarted) || (this.mContentChanged) || (this.mProcessingChange)) {
            arg3.print(arg1);
            arg3.print("mStarted=");
            arg3.print(this.mStarted);
            arg3.print(" mContentChanged=");
            arg3.print(this.mContentChanged);
            arg3.print(" mProcessingChange=");
            arg3.println(this.mProcessingChange);
        }

        if((this.mAbandoned) || (this.mReset)) {
            arg3.print(arg1);
            arg3.print("mAbandoned=");
            arg3.print(this.mAbandoned);
            arg3.print(" mReset=");
            arg3.println(this.mReset);
        }
    }

    public void forceLoad() {
        this.onForceLoad();
    }

    public Context getContext() {
        return this.mContext;
    }

    public int getId() {
        return this.mId;
    }

    public boolean isAbandoned() {
        return this.mAbandoned;
    }

    public boolean isReset() {
        return this.mReset;
    }

    public boolean isStarted() {
        return this.mStarted;
    }

    protected void onAbandon() {
    }

    protected boolean onCancelLoad() {
        return 0;
    }

    public void onContentChanged() {
        if(this.mStarted) {
            this.forceLoad();
        }
        else {
            this.mContentChanged = true;
        }
    }

    protected void onForceLoad() {
    }

    protected void onReset() {
    }

    protected void onStartLoading() {
    }

    protected void onStopLoading() {
    }

    public void registerListener(int arg2, OnLoadCompleteListener arg3) {
        if(this.mListener != null) {
            throw new IllegalStateException("There is already a listener registered");
        }

        this.mListener = arg3;
        this.mId = arg2;
    }

    public void registerOnLoadCanceledListener(OnLoadCanceledListener arg2) {
        if(this.mOnLoadCanceledListener != null) {
            throw new IllegalStateException("There is already a listener registered");
        }

        this.mOnLoadCanceledListener = arg2;
    }

    public void reset() {
        this.onReset();
        this.mReset = true;
        this.mStarted = false;
        this.mAbandoned = false;
        this.mContentChanged = false;
        this.mProcessingChange = false;
    }

    public void rollbackContentChanged() {
        if(this.mProcessingChange) {
            this.onContentChanged();
        }
    }

    public final void startLoading() {
        this.mStarted = true;
        this.mReset = false;
        this.mAbandoned = false;
        this.onStartLoading();
    }

    public void stopLoading() {
        this.mStarted = false;
        this.onStopLoading();
    }

    public boolean takeContentChanged() {
        boolean v0 = this.mContentChanged;
        this.mContentChanged = false;
        this.mProcessingChange |= ((int)v0);
        return v0;
    }

    public String toString() {
        StringBuilder v0 = new StringBuilder(0x40);
        DebugUtils.buildShortClassTag(this, v0);
        v0.append(" id=");
        v0.append(this.mId);
        v0.append("}");
        return v0.toString();
    }

    public void unregisterListener(OnLoadCompleteListener arg2) {
        if(this.mListener == null) {
            throw new IllegalStateException("No listener register");
        }

        if(this.mListener != arg2) {
            throw new IllegalArgumentException("Attempting to unregister the wrong listener");
        }

        this.mListener = null;
    }

    public void unregisterOnLoadCanceledListener(OnLoadCanceledListener arg2) {
        if(this.mOnLoadCanceledListener == null) {
            throw new IllegalStateException("No listener register");
        }

        if(this.mOnLoadCanceledListener != arg2) {
            throw new IllegalArgumentException("Attempting to unregister the wrong listener");
        }

        this.mOnLoadCanceledListener = null;
    }
}

