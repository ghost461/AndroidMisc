package android.support.v4.media;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.BadParcelableException;
import android.os.Binder;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.v4.app.BundleCompat;
import android.support.v4.media.session.IMediaSession$Stub;
import android.support.v4.media.session.IMediaSession;
import android.support.v4.media.session.MediaSessionCompat$Token;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.os.ResultReceiver;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Log;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map$Entry;

public final class MediaBrowserCompat {
    class CallbackHandler extends Handler {
        private final WeakReference mCallbackImplRef;
        private WeakReference mCallbacksMessengerRef;

        CallbackHandler(MediaBrowserServiceCallbackImpl arg2) {
            super();
            this.mCallbackImplRef = new WeakReference(arg2);
        }

        public void handleMessage(Message arg8) {
            if(this.mCallbacksMessengerRef != null && this.mCallbacksMessengerRef.get() != null) {
                if(this.mCallbackImplRef.get() == null) {
                }
                else {
                    Bundle v0 = arg8.getData();
                    v0.setClassLoader(MediaSessionCompat.class.getClassLoader());
                    Object v1 = this.mCallbackImplRef.get();
                    Object v2 = this.mCallbacksMessengerRef.get();
                    try {
                        switch(arg8.what) {
                            case 1: {
                                goto label_32;
                            }
                            case 2: {
                                goto label_30;
                            }
                            case 3: {
                                goto label_22;
                            }
                        }

                        String v0_1 = "MediaBrowserCompat";
                        Log.w(v0_1, "Unhandled message: " + arg8 + "\n  Client version: " + 1 + "\n  Service version: " + arg8.arg1);
                        return;
                    label_22:
                        ((MediaBrowserServiceCallbackImpl)v1).onLoadChildren(((Messenger)v2), v0.getString("data_media_item_id"), v0.getParcelableArrayList("data_media_item_list"), v0.getBundle("data_options"));
                        return;
                    label_30:
                        ((MediaBrowserServiceCallbackImpl)v1).onConnectionFailed(((Messenger)v2));
                        return;
                    label_32:
                        ((MediaBrowserServiceCallbackImpl)v1).onServiceConnected(((Messenger)v2), v0.getString("data_media_item_id"), v0.getParcelable("data_media_session_token"), v0.getBundle("data_root_hints"));
                    }
                    catch(BadParcelableException ) {
                        Log.e("MediaBrowserCompat", "Could not unparcel the data.");
                        if(arg8.what == 1) {
                            ((MediaBrowserServiceCallbackImpl)v1).onConnectionFailed(((Messenger)v2));
                        }
                        else {
                        }
                    }

                    return;
                }
            }
        }

        void setCallbacksMessenger(Messenger arg2) {
            this.mCallbacksMessengerRef = new WeakReference(arg2);
        }
    }

    public class ConnectionCallback {
        interface ConnectionCallbackInternal {
            void onConnected();

            void onConnectionFailed();

            void onConnectionSuspended();
        }

        class StubApi21 implements android.support.v4.media.MediaBrowserCompatApi21$ConnectionCallback {
            StubApi21(ConnectionCallback arg1) {
                ConnectionCallback.this = arg1;
                super();
            }

            public void onConnected() {
                if(ConnectionCallback.this.mConnectionCallbackInternal != null) {
                    ConnectionCallback.this.mConnectionCallbackInternal.onConnected();
                }

                ConnectionCallback.this.onConnected();
            }

            public void onConnectionFailed() {
                if(ConnectionCallback.this.mConnectionCallbackInternal != null) {
                    ConnectionCallback.this.mConnectionCallbackInternal.onConnectionFailed();
                }

                ConnectionCallback.this.onConnectionFailed();
            }

            public void onConnectionSuspended() {
                if(ConnectionCallback.this.mConnectionCallbackInternal != null) {
                    ConnectionCallback.this.mConnectionCallbackInternal.onConnectionSuspended();
                }

                ConnectionCallback.this.onConnectionSuspended();
            }
        }

        ConnectionCallbackInternal mConnectionCallbackInternal;
        final Object mConnectionCallbackObj;

        public ConnectionCallback() {
            super();
            this.mConnectionCallbackObj = Build$VERSION.SDK_INT >= 21 ? MediaBrowserCompatApi21.createConnectionCallback(new StubApi21(this)) : null;
        }

        public void onConnected() {
        }

        public void onConnectionFailed() {
        }

        public void onConnectionSuspended() {
        }

        void setInternalConnectionCallback(ConnectionCallbackInternal arg1) {
            this.mConnectionCallbackInternal = arg1;
        }
    }

    public abstract class CustomActionCallback {
        public CustomActionCallback() {
            super();
        }

        public void onError(String arg1, Bundle arg2, Bundle arg3) {
        }

        public void onProgressUpdate(String arg1, Bundle arg2, Bundle arg3) {
        }

        public void onResult(String arg1, Bundle arg2, Bundle arg3) {
        }
    }

    class CustomActionResultReceiver extends ResultReceiver {
        private final String mAction;
        private final CustomActionCallback mCallback;
        private final Bundle mExtras;

        CustomActionResultReceiver(String arg1, Bundle arg2, CustomActionCallback arg3, Handler arg4) {
            super(arg4);
            this.mAction = arg1;
            this.mExtras = arg2;
            this.mCallback = arg3;
        }

        protected void onReceiveResult(int arg4, Bundle arg5) {
            if(this.mCallback == null) {
                return;
            }

            switch(arg4) {
                case -1: {
                    this.mCallback.onError(this.mAction, this.mExtras, arg5);
                    break;
                }
                case 0: {
                    this.mCallback.onResult(this.mAction, this.mExtras, arg5);
                    break;
                }
                case 1: {
                    this.mCallback.onProgressUpdate(this.mAction, this.mExtras, arg5);
                    break;
                }
                default: {
                    Log.w("MediaBrowserCompat", "Unknown result code: " + arg4 + " (extras=" + this.mExtras + ", resultData=" + arg5 + ")");
                    break;
                }
            }
        }
    }

    public abstract class ItemCallback {
        class StubApi23 implements android.support.v4.media.MediaBrowserCompatApi23$ItemCallback {
            StubApi23(ItemCallback arg1) {
                ItemCallback.this = arg1;
                super();
            }

            public void onError(@NonNull String arg2) {
                ItemCallback.this.onError(arg2);
            }

            public void onItemLoaded(Parcel arg2) {
                if(arg2 == null) {
                    ItemCallback.this.onItemLoaded(null);
                }
                else {
                    arg2.setDataPosition(0);
                    Object v0 = MediaItem.CREATOR.createFromParcel(arg2);
                    arg2.recycle();
                    ItemCallback.this.onItemLoaded(((MediaItem)v0));
                }
            }
        }

        final Object mItemCallbackObj;

        public ItemCallback() {
            super();
            this.mItemCallbackObj = Build$VERSION.SDK_INT >= 23 ? MediaBrowserCompatApi23.createItemCallback(new StubApi23(this)) : null;
        }

        public void onError(@NonNull String arg1) {
        }

        public void onItemLoaded(MediaItem arg1) {
        }
    }

    class ItemReceiver extends ResultReceiver {
        private final ItemCallback mCallback;
        private final String mMediaId;

        ItemReceiver(String arg1, ItemCallback arg2, Handler arg3) {
            super(arg3);
            this.mMediaId = arg1;
            this.mCallback = arg2;
        }

        protected void onReceiveResult(int arg2, Bundle arg3) {
            if(arg3 != null) {
                arg3.setClassLoader(MediaBrowserCompat.class.getClassLoader());
            }

            if(arg2 == 0 && arg3 != null) {
                if(!arg3.containsKey("media_item")) {
                }
                else {
                    Parcelable v2 = arg3.getParcelable("media_item");
                    if(v2 == null || ((v2 instanceof MediaItem))) {
                        this.mCallback.onItemLoaded(((MediaItem)v2));
                    }
                    else {
                        this.mCallback.onError(this.mMediaId);
                    }

                    return;
                }
            }

            this.mCallback.onError(this.mMediaId);
        }
    }

    interface MediaBrowserImpl {
        void connect();

        void disconnect();

        @Nullable Bundle getExtras();

        void getItem(@NonNull String arg1, @NonNull ItemCallback arg2);

        @NonNull String getRoot();

        ComponentName getServiceComponent();

        @NonNull Token getSessionToken();

        boolean isConnected();

        void search(@NonNull String arg1, Bundle arg2, @NonNull SearchCallback arg3);

        void sendCustomAction(@NonNull String arg1, Bundle arg2, @Nullable CustomActionCallback arg3);

        void subscribe(@NonNull String arg1, Bundle arg2, @NonNull SubscriptionCallback arg3);

        void unsubscribe(@NonNull String arg1, SubscriptionCallback arg2);
    }

    @RequiresApi(value=21) class MediaBrowserImplApi21 implements ConnectionCallbackInternal, MediaBrowserImpl, MediaBrowserServiceCallbackImpl {
        protected final Object mBrowserObj;
        protected Messenger mCallbacksMessenger;
        final Context mContext;
        protected final CallbackHandler mHandler;
        private Token mMediaSessionToken;
        protected final Bundle mRootHints;
        protected ServiceBinderWrapper mServiceBinderWrapper;
        private final ArrayMap mSubscriptions;

        public MediaBrowserImplApi21(Context arg3, ComponentName arg4, ConnectionCallback arg5, Bundle arg6) {
            super();
            this.mHandler = new CallbackHandler(((MediaBrowserServiceCallbackImpl)this));
            this.mSubscriptions = new ArrayMap();
            this.mContext = arg3;
            if(arg6 == null) {
                arg6 = new Bundle();
            }

            arg6.putInt("extra_client_version", 1);
            this.mRootHints = new Bundle(arg6);
            arg5.setInternalConnectionCallback(((ConnectionCallbackInternal)this));
            this.mBrowserObj = MediaBrowserCompatApi21.createBrowser(arg3, arg4, arg5.mConnectionCallbackObj, this.mRootHints);
        }

        public void connect() {
            MediaBrowserCompatApi21.connect(this.mBrowserObj);
        }

        public void disconnect() {
            if(this.mServiceBinderWrapper != null && this.mCallbacksMessenger != null) {
                try {
                    this.mServiceBinderWrapper.unregisterCallbackMessenger(this.mCallbacksMessenger);
                }
                catch(RemoteException ) {
                    Log.i("MediaBrowserCompat", "Remote error unregistering client messenger.");
                }
            }

            MediaBrowserCompatApi21.disconnect(this.mBrowserObj);
        }

        @Nullable public Bundle getExtras() {
            return MediaBrowserCompatApi21.getExtras(this.mBrowserObj);
        }

        public void getItem(@NonNull String arg4, @NonNull ItemCallback arg5) {
            if(TextUtils.isEmpty(((CharSequence)arg4))) {
                throw new IllegalArgumentException("mediaId is empty");
            }

            if(arg5 == null) {
                throw new IllegalArgumentException("cb is null");
            }

            if(!MediaBrowserCompatApi21.isConnected(this.mBrowserObj)) {
                Log.i("MediaBrowserCompat", "Not connected, unable to retrieve the MediaItem.");
                this.mHandler.post(new Runnable(arg5, arg4) {
                    public void run() {
                        this.val$cb.onError(this.val$mediaId);
                    }
                });
                return;
            }

            if(this.mServiceBinderWrapper == null) {
                this.mHandler.post(new Runnable(arg5, arg4) {
                    public void run() {
                        this.val$cb.onError(this.val$mediaId);
                    }
                });
                return;
            }

            ItemReceiver v0 = new ItemReceiver(arg4, arg5, this.mHandler);
            try {
                this.mServiceBinderWrapper.getMediaItem(arg4, ((ResultReceiver)v0), this.mCallbacksMessenger);
            }
            catch(RemoteException ) {
                Log.i("MediaBrowserCompat", "Remote error getting media item: " + arg4);
                this.mHandler.post(new Runnable(arg5, arg4) {
                    public void run() {
                        this.val$cb.onError(this.val$mediaId);
                    }
                });
            }
        }

        @NonNull public String getRoot() {
            return MediaBrowserCompatApi21.getRoot(this.mBrowserObj);
        }

        public ComponentName getServiceComponent() {
            return MediaBrowserCompatApi21.getServiceComponent(this.mBrowserObj);
        }

        @NonNull public Token getSessionToken() {
            if(this.mMediaSessionToken == null) {
                this.mMediaSessionToken = Token.fromToken(MediaBrowserCompatApi21.getSessionToken(this.mBrowserObj));
            }

            return this.mMediaSessionToken;
        }

        public boolean isConnected() {
            return MediaBrowserCompatApi21.isConnected(this.mBrowserObj);
        }

        public void onConnected() {
            Bundle v0 = MediaBrowserCompatApi21.getExtras(this.mBrowserObj);
            if(v0 == null) {
                return;
            }

            IBinder v1 = BundleCompat.getBinder(v0, "extra_messenger");
            if(v1 != null) {
                this.mServiceBinderWrapper = new ServiceBinderWrapper(v1, this.mRootHints);
                this.mCallbacksMessenger = new Messenger(this.mHandler);
                this.mHandler.setCallbacksMessenger(this.mCallbacksMessenger);
                try {
                    this.mServiceBinderWrapper.registerCallbackMessenger(this.mCallbacksMessenger);
                }
                catch(RemoteException ) {
                    Log.i("MediaBrowserCompat", "Remote error registering client messenger.");
                }
            }

            IMediaSession v0_1 = Stub.asInterface(BundleCompat.getBinder(v0, "extra_session_binder"));
            if(v0_1 != null) {
                this.mMediaSessionToken = Token.fromToken(MediaBrowserCompatApi21.getSessionToken(this.mBrowserObj), v0_1);
            }
        }

        public void onConnectionFailed() {
        }

        public void onConnectionFailed(Messenger arg1) {
        }

        public void onConnectionSuspended() {
            this.mServiceBinderWrapper = null;
            this.mCallbacksMessenger = null;
            this.mMediaSessionToken = null;
            this.mHandler.setCallbacksMessenger(null);
        }

        public void onLoadChildren(Messenger arg2, String arg3, List arg4, Bundle arg5) {
            if(this.mCallbacksMessenger != arg2) {
                return;
            }

            Object v2 = this.mSubscriptions.get(arg3);
            if(v2 == null) {
                if(MediaBrowserCompat.DEBUG) {
                    Log.d("MediaBrowserCompat", "onLoadChildren for id that isn\'t subscribed id=" + arg3);
                }

                return;
            }

            SubscriptionCallback v2_1 = ((Subscription)v2).getCallback(this.mContext, arg5);
            if(v2_1 != null) {
                if(arg5 == null) {
                    if(arg4 == null) {
                        v2_1.onError(arg3);
                    }
                    else {
                        v2_1.onChildrenLoaded(arg3, arg4);
                    }
                }
                else if(arg4 == null) {
                    v2_1.onError(arg3, arg5);
                }
                else {
                    v2_1.onChildrenLoaded(arg3, arg4, arg5);
                }
            }
        }

        public void onServiceConnected(Messenger arg1, String arg2, Token arg3, Bundle arg4) {
        }

        public void search(@NonNull String arg5, Bundle arg6, @NonNull SearchCallback arg7) {
            if(!this.isConnected()) {
                throw new IllegalStateException("search() called while not connected");
            }

            if(this.mServiceBinderWrapper == null) {
                Log.i("MediaBrowserCompat", "The connected service doesn\'t support search.");
                this.mHandler.post(new Runnable(arg7, arg5, arg6) {
                    public void run() {
                        this.val$callback.onError(this.val$query, this.val$extras);
                    }
                });
                return;
            }

            SearchResultReceiver v0 = new SearchResultReceiver(arg5, arg6, arg7, this.mHandler);
            try {
                this.mServiceBinderWrapper.search(arg5, arg6, ((ResultReceiver)v0), this.mCallbacksMessenger);
            }
            catch(RemoteException v0_1) {
                Log.i("MediaBrowserCompat", "Remote error searching items with query: " + arg5, ((Throwable)v0_1));
                this.mHandler.post(new Runnable(arg7, arg5, arg6) {
                    public void run() {
                        this.val$callback.onError(this.val$query, this.val$extras);
                    }
                });
            }
        }

        public void sendCustomAction(@NonNull String arg5, Bundle arg6, @Nullable CustomActionCallback arg7) {
            if(!this.isConnected()) {
                StringBuilder v0 = new StringBuilder();
                v0.append("Cannot send a custom action (");
                v0.append(arg5);
                v0.append(") with ");
                v0.append("extras ");
                v0.append(arg6);
                v0.append(" because the browser is not connected to the ");
                v0.append("service.");
                throw new IllegalStateException(v0.toString());
            }

            if(this.mServiceBinderWrapper == null) {
                Log.i("MediaBrowserCompat", "The connected service doesn\'t support sendCustomAction.");
                if(arg7 != null) {
                    this.mHandler.post(new Runnable(arg7, arg5, arg6) {
                        public void run() {
                            this.val$callback.onError(this.val$action, this.val$extras, null);
                        }
                    });
                }
            }

            CustomActionResultReceiver v0_1 = new CustomActionResultReceiver(arg5, arg6, arg7, this.mHandler);
            try {
                this.mServiceBinderWrapper.sendCustomAction(arg5, arg6, ((ResultReceiver)v0_1), this.mCallbacksMessenger);
            }
            catch(RemoteException v0_2) {
                Log.i("MediaBrowserCompat", "Remote error sending a custom action: action=" + arg5 + ", extras=" + arg6, ((Throwable)v0_2));
                if(arg7 == null) {
                    return;
                }

                this.mHandler.post(new Runnable(arg7, arg5, arg6) {
                    public void run() {
                        this.val$callback.onError(this.val$action, this.val$extras, null);
                    }
                });
            }
        }

        public void subscribe(@NonNull String arg3, Bundle arg4, @NonNull SubscriptionCallback arg5) {
            Subscription v0_1;
            Object v0 = this.mSubscriptions.get(arg3);
            if(v0 == null) {
                v0_1 = new Subscription();
                this.mSubscriptions.put(arg3, v0_1);
            }

            arg5.setSubscription(v0_1);
            arg4 = arg4 == null ? null : new Bundle(arg4);
            v0_1.putCallback(this.mContext, arg4, arg5);
            if(this.mServiceBinderWrapper == null) {
                MediaBrowserCompatApi21.subscribe(this.mBrowserObj, arg3, arg5.mSubscriptionCallbackObj);
                return;
            }

            try {
                this.mServiceBinderWrapper.addSubscription(arg3, arg5.mToken, arg4, this.mCallbacksMessenger);
            }
            catch(RemoteException ) {
                Log.i("MediaBrowserCompat", "Remote error subscribing media item: " + arg3);
            }
        }

        public void unsubscribe(@NonNull String arg8, SubscriptionCallback arg9) {
            List v2;
            List v1;
            Object v0 = this.mSubscriptions.get(arg8);
            if(v0 == null) {
                return;
            }

            if(this.mServiceBinderWrapper == null) {
                if(arg9 == null) {
                    MediaBrowserCompatApi21.unsubscribe(this.mBrowserObj, arg8);
                    goto label_55;
                }

                v1 = ((Subscription)v0).getCallbacks();
                v2 = ((Subscription)v0).getOptionsList();
                int v3;
                for(v3 = v1.size() - 1; v3 >= 0; --v3) {
                    if(v1.get(v3) == arg9) {
                        v1.remove(v3);
                        v2.remove(v3);
                    }
                }

                if(v1.size() != 0) {
                    goto label_55;
                }

                MediaBrowserCompatApi21.unsubscribe(this.mBrowserObj, arg8);
                goto label_55;
            }

            if(arg9 != null) {
                goto label_32;
            }

            try {
                this.mServiceBinderWrapper.removeSubscription(arg8, null, this.mCallbacksMessenger);
                goto label_55;
            label_32:
                v1 = ((Subscription)v0).getCallbacks();
                v2 = ((Subscription)v0).getOptionsList();
                v3 = v1.size() - 1;
                while(true) {
                label_36:
                    if(v3 < 0) {
                        goto label_55;
                    }

                    if(v1.get(v3) == arg9) {
                        this.mServiceBinderWrapper.removeSubscription(arg8, arg9.mToken, this.mCallbacksMessenger);
                        v1.remove(v3);
                        v2.remove(v3);
                    }

                    break;
                }
            }
            catch(RemoteException ) {
                goto label_47;
            }

            --v3;
            goto label_36;
        label_47:
            Log.d("MediaBrowserCompat", "removeSubscription failed with RemoteException parentId=" + arg8);
        label_55:
            if((((Subscription)v0).isEmpty()) || arg9 == null) {
                this.mSubscriptions.remove(arg8);
            }
        }
    }

    @RequiresApi(value=23) class MediaBrowserImplApi23 extends MediaBrowserImplApi21 {
        public MediaBrowserImplApi23(Context arg1, ComponentName arg2, ConnectionCallback arg3, Bundle arg4) {
            super(arg1, arg2, arg3, arg4);
        }

        public void getItem(@NonNull String arg2, @NonNull ItemCallback arg3) {
            if(this.mServiceBinderWrapper == null) {
                MediaBrowserCompatApi23.getItem(this.mBrowserObj, arg2, arg3.mItemCallbackObj);
            }
            else {
                super.getItem(arg2, arg3);
            }
        }
    }

    @RequiresApi(value=26) class MediaBrowserImplApi24 extends MediaBrowserImplApi23 {
        public MediaBrowserImplApi24(Context arg1, ComponentName arg2, ConnectionCallback arg3, Bundle arg4) {
            super(arg1, arg2, arg3, arg4);
        }

        public void subscribe(@NonNull String arg2, @NonNull Bundle arg3, @NonNull SubscriptionCallback arg4) {
            if(arg3 == null) {
                MediaBrowserCompatApi21.subscribe(this.mBrowserObj, arg2, arg4.mSubscriptionCallbackObj);
            }
            else {
                MediaBrowserCompatApi24.subscribe(this.mBrowserObj, arg2, arg3, arg4.mSubscriptionCallbackObj);
            }
        }

        public void unsubscribe(@NonNull String arg2, SubscriptionCallback arg3) {
            if(arg3 == null) {
                MediaBrowserCompatApi21.unsubscribe(this.mBrowserObj, arg2);
            }
            else {
                MediaBrowserCompatApi24.unsubscribe(this.mBrowserObj, arg2, arg3.mSubscriptionCallbackObj);
            }
        }
    }

    class MediaBrowserImplBase implements MediaBrowserImpl, MediaBrowserServiceCallbackImpl {
        class MediaServiceConnection implements ServiceConnection {
            MediaServiceConnection(MediaBrowserImplBase arg1) {
                MediaBrowserImplBase.this = arg1;
                super();
            }

            boolean isCurrent(String arg3) {
                if(MediaBrowserImplBase.this.mServiceConnection == this && MediaBrowserImplBase.this.mState != 0) {
                    if(MediaBrowserImplBase.this.mState == 1) {
                    }
                    else {
                        return 1;
                    }
                }

                if(MediaBrowserImplBase.this.mState != 0 && MediaBrowserImplBase.this.mState != 1) {
                    Log.i("MediaBrowserCompat", arg3 + " for " + MediaBrowserImplBase.this.mServiceComponent + " with mServiceConnection=" + MediaBrowserImplBase.this.mServiceConnection + " this=" + this);
                }

                return 0;
            }

            public void onServiceConnected(ComponentName arg2, IBinder arg3) {
                this.postOrRun(new Runnable(arg2, arg3) {
                    public void run() {
                        if(MediaBrowserCompat.DEBUG) {
                            Log.d("MediaBrowserCompat", "MediaServiceConnection.onServiceConnected name=" + this.val$name + " binder=" + this.val$binder);
                            this.this$1.this$0.dump();
                        }

                        if(!this.this$1.isCurrent("onServiceConnected")) {
                            return;
                        }

                        this.this$1.this$0.mServiceBinderWrapper = new ServiceBinderWrapper(this.val$binder, this.this$1.this$0.mRootHints);
                        this.this$1.this$0.mCallbacksMessenger = new Messenger(this.this$1.this$0.mHandler);
                        this.this$1.this$0.mHandler.setCallbacksMessenger(this.this$1.this$0.mCallbacksMessenger);
                        this.this$1.this$0.mState = 2;
                        try {
                            if(MediaBrowserCompat.DEBUG) {
                                Log.d("MediaBrowserCompat", "ServiceCallbacks.onConnect...");
                                this.this$1.this$0.dump();
                            }

                            this.this$1.this$0.mServiceBinderWrapper.connect(this.this$1.this$0.mContext, this.this$1.this$0.mCallbacksMessenger);
                        }
                        catch(RemoteException ) {
                            Log.w("MediaBrowserCompat", "RemoteException during connect for " + this.this$1.this$0.mServiceComponent);
                            if(!MediaBrowserCompat.DEBUG) {
                                return;
                            }

                            Log.d("MediaBrowserCompat", "ServiceCallbacks.onConnect...");
                            this.this$1.this$0.dump();
                        }
                    }
                });
            }

            public void onServiceDisconnected(ComponentName arg2) {
                this.postOrRun(new Runnable(arg2) {
                    public void run() {
                        if(MediaBrowserCompat.DEBUG) {
                            Log.d("MediaBrowserCompat", "MediaServiceConnection.onServiceDisconnected name=" + this.val$name + " this=" + this + " mServiceConnection=" + this.this$1.this$0.mServiceConnection);
                            this.this$1.this$0.dump();
                        }

                        if(!this.this$1.isCurrent("onServiceDisconnected")) {
                            return;
                        }

                        this.this$1.this$0.mServiceBinderWrapper = null;
                        this.this$1.this$0.mCallbacksMessenger = null;
                        this.this$1.this$0.mHandler.setCallbacksMessenger(null);
                        this.this$1.this$0.mState = 4;
                        this.this$1.this$0.mCallback.onConnectionSuspended();
                    }
                });
            }

            private void postOrRun(Runnable arg3) {
                if(Thread.currentThread() == MediaBrowserImplBase.this.mHandler.getLooper().getThread()) {
                    arg3.run();
                }
                else {
                    MediaBrowserImplBase.this.mHandler.post(arg3);
                }
            }
        }

        static final int CONNECT_STATE_CONNECTED = 3;
        static final int CONNECT_STATE_CONNECTING = 2;
        static final int CONNECT_STATE_DISCONNECTED = 1;
        static final int CONNECT_STATE_DISCONNECTING = 0;
        static final int CONNECT_STATE_SUSPENDED = 4;
        final ConnectionCallback mCallback;
        Messenger mCallbacksMessenger;
        final Context mContext;
        private Bundle mExtras;
        final CallbackHandler mHandler;
        private Token mMediaSessionToken;
        final Bundle mRootHints;
        private String mRootId;
        ServiceBinderWrapper mServiceBinderWrapper;
        final ComponentName mServiceComponent;
        MediaServiceConnection mServiceConnection;
        int mState;
        private final ArrayMap mSubscriptions;

        public MediaBrowserImplBase(Context arg2, ComponentName arg3, ConnectionCallback arg4, Bundle arg5) {
            super();
            this.mHandler = new CallbackHandler(((MediaBrowserServiceCallbackImpl)this));
            this.mSubscriptions = new ArrayMap();
            this.mState = 1;
            if(arg2 == null) {
                throw new IllegalArgumentException("context must not be null");
            }

            if(arg3 == null) {
                throw new IllegalArgumentException("service component must not be null");
            }

            if(arg4 == null) {
                throw new IllegalArgumentException("connection callback must not be null");
            }

            this.mContext = arg2;
            this.mServiceComponent = arg3;
            this.mCallback = arg4;
            Bundle v2 = arg5 == null ? null : new Bundle(arg5);
            this.mRootHints = v2;
        }

        public void connect() {
            if(this.mState != 0 && this.mState != 1) {
                StringBuilder v1 = new StringBuilder();
                v1.append("connect() called while neigther disconnecting nor disconnected (state=");
                v1.append(MediaBrowserImplBase.getStateLabel(this.mState));
                v1.append(")");
                throw new IllegalStateException(v1.toString());
            }

            this.mState = 2;
            this.mHandler.post(new Runnable() {
                public void run() {
                    boolean v0_1;
                    StringBuilder v1;
                    if(MediaBrowserImplBase.this.mState == 0) {
                        return;
                    }

                    MediaBrowserImplBase.this.mState = 2;
                    if((MediaBrowserCompat.DEBUG) && MediaBrowserImplBase.this.mServiceConnection != null) {
                        v1 = new StringBuilder();
                        v1.append("mServiceConnection should be null. Instead it is ");
                        v1.append(MediaBrowserImplBase.this.mServiceConnection);
                        throw new RuntimeException(v1.toString());
                    }

                    if(MediaBrowserImplBase.this.mServiceBinderWrapper != null) {
                        v1 = new StringBuilder();
                        v1.append("mServiceBinderWrapper should be null. Instead it is ");
                        v1.append(MediaBrowserImplBase.this.mServiceBinderWrapper);
                        throw new RuntimeException(v1.toString());
                    }

                    if(MediaBrowserImplBase.this.mCallbacksMessenger != null) {
                        v1 = new StringBuilder();
                        v1.append("mCallbacksMessenger should be null. Instead it is ");
                        v1.append(MediaBrowserImplBase.this.mCallbacksMessenger);
                        throw new RuntimeException(v1.toString());
                    }

                    Intent v0 = new Intent("android.media.browse.MediaBrowserService");
                    v0.setComponent(MediaBrowserImplBase.this.mServiceComponent);
                    MediaBrowserImplBase.this.mServiceConnection = new MediaServiceConnection(MediaBrowserImplBase.this);
                    try {
                        v0_1 = MediaBrowserImplBase.this.mContext.bindService(v0, MediaBrowserImplBase.this.mServiceConnection, 1);
                    }
                    catch(Exception ) {
                        Log.e("MediaBrowserCompat", "Failed binding to service " + MediaBrowserImplBase.this.mServiceComponent);
                        v0_1 = false;
                    }

                    if(!v0_1) {
                        MediaBrowserImplBase.this.forceCloseConnection();
                        MediaBrowserImplBase.this.mCallback.onConnectionFailed();
                    }

                    if(MediaBrowserCompat.DEBUG) {
                        Log.d("MediaBrowserCompat", "connect...");
                        MediaBrowserImplBase.this.dump();
                    }
                }
            });
        }

        public void disconnect() {
            this.mState = 0;
            this.mHandler.post(new Runnable() {
                public void run() {
                    if(MediaBrowserImplBase.this.mCallbacksMessenger != null) {
                        try {
                            MediaBrowserImplBase.this.mServiceBinderWrapper.disconnect(MediaBrowserImplBase.this.mCallbacksMessenger);
                        }
                        catch(RemoteException ) {
                            Log.w("MediaBrowserCompat", "RemoteException during connect for " + MediaBrowserImplBase.this.mServiceComponent);
                        }
                    }

                    int v0 = MediaBrowserImplBase.this.mState;
                    MediaBrowserImplBase.this.forceCloseConnection();
                    if(v0 != 0) {
                        MediaBrowserImplBase.this.mState = v0;
                    }

                    if(MediaBrowserCompat.DEBUG) {
                        Log.d("MediaBrowserCompat", "disconnect...");
                        MediaBrowserImplBase.this.dump();
                    }
                }
            });
        }

        void dump() {
            Log.d("MediaBrowserCompat", "MediaBrowserCompat...");
            Log.d("MediaBrowserCompat", "  mServiceComponent=" + this.mServiceComponent);
            Log.d("MediaBrowserCompat", "  mCallback=" + this.mCallback);
            Log.d("MediaBrowserCompat", "  mRootHints=" + this.mRootHints);
            Log.d("MediaBrowserCompat", "  mState=" + MediaBrowserImplBase.getStateLabel(this.mState));
            Log.d("MediaBrowserCompat", "  mServiceConnection=" + this.mServiceConnection);
            Log.d("MediaBrowserCompat", "  mServiceBinderWrapper=" + this.mServiceBinderWrapper);
            Log.d("MediaBrowserCompat", "  mCallbacksMessenger=" + this.mCallbacksMessenger);
            Log.d("MediaBrowserCompat", "  mRootId=" + this.mRootId);
            Log.d("MediaBrowserCompat", "  mMediaSessionToken=" + this.mMediaSessionToken);
        }

        void forceCloseConnection() {
            if(this.mServiceConnection != null) {
                this.mContext.unbindService(this.mServiceConnection);
            }

            this.mState = 1;
            this.mServiceConnection = null;
            this.mServiceBinderWrapper = null;
            this.mCallbacksMessenger = null;
            this.mHandler.setCallbacksMessenger(null);
            this.mRootId = null;
            this.mMediaSessionToken = null;
        }

        @Nullable public Bundle getExtras() {
            if(!this.isConnected()) {
                StringBuilder v1 = new StringBuilder();
                v1.append("getExtras() called while not connected (state=");
                v1.append(MediaBrowserImplBase.getStateLabel(this.mState));
                v1.append(")");
                throw new IllegalStateException(v1.toString());
            }

            return this.mExtras;
        }

        public void getItem(@NonNull String arg4, @NonNull ItemCallback arg5) {
            if(TextUtils.isEmpty(((CharSequence)arg4))) {
                throw new IllegalArgumentException("mediaId is empty");
            }

            if(arg5 == null) {
                throw new IllegalArgumentException("cb is null");
            }

            if(!this.isConnected()) {
                Log.i("MediaBrowserCompat", "Not connected, unable to retrieve the MediaItem.");
                this.mHandler.post(new Runnable(arg5, arg4) {
                    public void run() {
                        this.val$cb.onError(this.val$mediaId);
                    }
                });
                return;
            }

            ItemReceiver v0 = new ItemReceiver(arg4, arg5, this.mHandler);
            try {
                this.mServiceBinderWrapper.getMediaItem(arg4, ((ResultReceiver)v0), this.mCallbacksMessenger);
            }
            catch(RemoteException ) {
                Log.i("MediaBrowserCompat", "Remote error getting media item: " + arg4);
                this.mHandler.post(new Runnable(arg5, arg4) {
                    public void run() {
                        this.val$cb.onError(this.val$mediaId);
                    }
                });
            }
        }

        @NonNull public String getRoot() {
            if(!this.isConnected()) {
                StringBuilder v1 = new StringBuilder();
                v1.append("getRoot() called while not connected(state=");
                v1.append(MediaBrowserImplBase.getStateLabel(this.mState));
                v1.append(")");
                throw new IllegalStateException(v1.toString());
            }

            return this.mRootId;
        }

        @NonNull public ComponentName getServiceComponent() {
            if(!this.isConnected()) {
                StringBuilder v1 = new StringBuilder();
                v1.append("getServiceComponent() called while not connected (state=");
                v1.append(this.mState);
                v1.append(")");
                throw new IllegalStateException(v1.toString());
            }

            return this.mServiceComponent;
        }

        @NonNull public Token getSessionToken() {
            if(!this.isConnected()) {
                StringBuilder v1 = new StringBuilder();
                v1.append("getSessionToken() called while not connected(state=");
                v1.append(this.mState);
                v1.append(")");
                throw new IllegalStateException(v1.toString());
            }

            return this.mMediaSessionToken;
        }

        private static String getStateLabel(int arg2) {
            switch(arg2) {
                case 0: {
                    return "CONNECT_STATE_DISCONNECTING";
                }
                case 1: {
                    return "CONNECT_STATE_DISCONNECTED";
                }
                case 2: {
                    return "CONNECT_STATE_CONNECTING";
                }
                case 3: {
                    return "CONNECT_STATE_CONNECTED";
                }
                case 4: {
                    return "CONNECT_STATE_SUSPENDED";
                }
            }

            return "UNKNOWN/" + arg2;
        }

        public boolean isConnected() {
            boolean v0 = this.mState == 3 ? true : false;
            return v0;
        }

        private boolean isCurrent(Messenger arg3, String arg4) {
            if(this.mCallbacksMessenger == arg3 && this.mState != 0) {
                if(this.mState == 1) {
                }
                else {
                    return 1;
                }
            }

            if(this.mState != 0 && this.mState != 1) {
                Log.i("MediaBrowserCompat", arg4 + " for " + this.mServiceComponent + " with mCallbacksMessenger=" + this.mCallbacksMessenger + " this=" + this);
            }

            return 0;
        }

        public void onConnectionFailed(Messenger arg4) {
            Log.e("MediaBrowserCompat", "onConnectFailed for " + this.mServiceComponent);
            if(!this.isCurrent(arg4, "onConnectFailed")) {
                return;
            }

            if(this.mState != 2) {
                Log.w("MediaBrowserCompat", "onConnect from service while mState=" + MediaBrowserImplBase.getStateLabel(this.mState) + "... ignoring");
                return;
            }

            this.forceCloseConnection();
            this.mCallback.onConnectionFailed();
        }

        public void onLoadChildren(Messenger arg3, String arg4, List arg5, Bundle arg6) {
            if(!this.isCurrent(arg3, "onLoadChildren")) {
                return;
            }

            if(MediaBrowserCompat.DEBUG) {
                Log.d("MediaBrowserCompat", "onLoadChildren for " + this.mServiceComponent + " id=" + arg4);
            }

            Object v3 = this.mSubscriptions.get(arg4);
            if(v3 == null) {
                if(MediaBrowserCompat.DEBUG) {
                    Log.d("MediaBrowserCompat", "onLoadChildren for id that isn\'t subscribed id=" + arg4);
                }

                return;
            }

            SubscriptionCallback v3_1 = ((Subscription)v3).getCallback(this.mContext, arg6);
            if(v3_1 != null) {
                if(arg6 == null) {
                    if(arg5 == null) {
                        v3_1.onError(arg4);
                    }
                    else {
                        v3_1.onChildrenLoaded(arg4, arg5);
                    }
                }
                else if(arg5 == null) {
                    v3_1.onError(arg4, arg6);
                }
                else {
                    v3_1.onChildrenLoaded(arg4, arg5, arg6);
                }
            }
        }

        public void onServiceConnected(Messenger arg6, String arg7, Token arg8, Bundle arg9) {
            if(!this.isCurrent(arg6, "onConnect")) {
                return;
            }

            if(this.mState != 2) {
                Log.w("MediaBrowserCompat", "onConnect from service while mState=" + MediaBrowserImplBase.getStateLabel(this.mState) + "... ignoring");
                return;
            }

            this.mRootId = arg7;
            this.mMediaSessionToken = arg8;
            this.mExtras = arg9;
            this.mState = 3;
            if(MediaBrowserCompat.DEBUG) {
                Log.d("MediaBrowserCompat", "ServiceCallbacks.onConnect...");
                this.dump();
            }

            this.mCallback.onConnected();
            try {
                Iterator v6 = this.mSubscriptions.entrySet().iterator();
            label_36:
                if(!v6.hasNext()) {
                    return;
                }

                Object v7_1 = v6.next();
                Object v8 = ((Map$Entry)v7_1).getKey();
                v7_1 = ((Map$Entry)v7_1).getValue();
                List v9 = ((Subscription)v7_1).getCallbacks();
                List v7_2 = ((Subscription)v7_1).getOptionsList();
                int v0;
                for(v0 = 0; true; ++v0) {
                    if(v0 >= v9.size()) {
                        goto label_36;
                    }

                    this.mServiceBinderWrapper.addSubscription(((String)v8), v9.get(v0).mToken, v7_2.get(v0), this.mCallbacksMessenger);
                }
            }
            catch(RemoteException ) {
                Log.d("MediaBrowserCompat", "addSubscription failed with RemoteException.");
            }
        }

        public void search(@NonNull String arg5, Bundle arg6, @NonNull SearchCallback arg7) {
            if(!this.isConnected()) {
                StringBuilder v6 = new StringBuilder();
                v6.append("search() called while not connected (state=");
                v6.append(MediaBrowserImplBase.getStateLabel(this.mState));
                v6.append(")");
                throw new IllegalStateException(v6.toString());
            }

            SearchResultReceiver v0 = new SearchResultReceiver(arg5, arg6, arg7, this.mHandler);
            try {
                this.mServiceBinderWrapper.search(arg5, arg6, ((ResultReceiver)v0), this.mCallbacksMessenger);
            }
            catch(RemoteException v0_1) {
                Log.i("MediaBrowserCompat", "Remote error searching items with query: " + arg5, ((Throwable)v0_1));
                this.mHandler.post(new Runnable(arg7, arg5, arg6) {
                    public void run() {
                        this.val$callback.onError(this.val$query, this.val$extras);
                    }
                });
            }
        }

        public void sendCustomAction(@NonNull String arg5, Bundle arg6, @Nullable CustomActionCallback arg7) {
            if(!this.isConnected()) {
                StringBuilder v0 = new StringBuilder();
                v0.append("Cannot send a custom action (");
                v0.append(arg5);
                v0.append(") with ");
                v0.append("extras ");
                v0.append(arg6);
                v0.append(" because the browser is not connected to the ");
                v0.append("service.");
                throw new IllegalStateException(v0.toString());
            }

            CustomActionResultReceiver v0_1 = new CustomActionResultReceiver(arg5, arg6, arg7, this.mHandler);
            try {
                this.mServiceBinderWrapper.sendCustomAction(arg5, arg6, ((ResultReceiver)v0_1), this.mCallbacksMessenger);
            }
            catch(RemoteException v0_2) {
                Log.i("MediaBrowserCompat", "Remote error sending a custom action: action=" + arg5 + ", extras=" + arg6, ((Throwable)v0_2));
                if(arg7 == null) {
                    return;
                }

                this.mHandler.post(new Runnable(arg7, arg5, arg6) {
                    public void run() {
                        this.val$callback.onError(this.val$action, this.val$extras, null);
                    }
                });
            }
        }

        public void subscribe(@NonNull String arg3, Bundle arg4, @NonNull SubscriptionCallback arg5) {
            Subscription v0_1;
            Object v0 = this.mSubscriptions.get(arg3);
            if(v0 == null) {
                v0_1 = new Subscription();
                this.mSubscriptions.put(arg3, v0_1);
            }

            arg4 = arg4 == null ? null : new Bundle(arg4);
            v0_1.putCallback(this.mContext, arg4, arg5);
            if(this.isConnected()) {
                try {
                    this.mServiceBinderWrapper.addSubscription(arg3, arg5.mToken, arg4, this.mCallbacksMessenger);
                }
                catch(RemoteException ) {
                    Log.d("MediaBrowserCompat", "addSubscription failed with RemoteException parentId=" + arg3);
                }
            }
        }

        public void unsubscribe(@NonNull String arg8, SubscriptionCallback arg9) {
            int v3;
            Object v0 = this.mSubscriptions.get(arg8);
            if(v0 == null) {
                return;
            }

            if(arg9 != null) {
                goto label_12;
            }

            try {
                if(!this.isConnected()) {
                    goto label_37;
                }

                this.mServiceBinderWrapper.removeSubscription(arg8, null, this.mCallbacksMessenger);
                goto label_37;
            label_12:
                List v1 = ((Subscription)v0).getCallbacks();
                List v2 = ((Subscription)v0).getOptionsList();
                v3 = v1.size() - 1;
                while(true) {
                label_16:
                    if(v3 < 0) {
                        goto label_37;
                    }

                    if(v1.get(v3) == arg9) {
                        if(this.isConnected()) {
                            this.mServiceBinderWrapper.removeSubscription(arg8, arg9.mToken, this.mCallbacksMessenger);
                        }

                        v1.remove(v3);
                        v2.remove(v3);
                    }

                    break;
                }
            }
            catch(RemoteException ) {
                goto label_29;
            }

            --v3;
            goto label_16;
        label_29:
            Log.d("MediaBrowserCompat", "removeSubscription failed with RemoteException parentId=" + arg8);
        label_37:
            if((((Subscription)v0).isEmpty()) || arg9 == null) {
                this.mSubscriptions.remove(arg8);
            }
        }
    }

    interface MediaBrowserServiceCallbackImpl {
        void onConnectionFailed(Messenger arg1);

        void onLoadChildren(Messenger arg1, String arg2, List arg3, Bundle arg4);

        void onServiceConnected(Messenger arg1, String arg2, Token arg3, Bundle arg4);
    }

    public class MediaItem implements Parcelable {
        final class android.support.v4.media.MediaBrowserCompat$MediaItem$1 implements Parcelable$Creator {
            android.support.v4.media.MediaBrowserCompat$MediaItem$1() {
                super();
            }

            public MediaItem createFromParcel(Parcel arg2) {
                return new MediaItem(arg2);
            }

            public Object createFromParcel(Parcel arg1) {
                return this.createFromParcel(arg1);
            }

            public MediaItem[] newArray(int arg1) {
                return new MediaItem[arg1];
            }

            public Object[] newArray(int arg1) {
                return this.newArray(arg1);
            }
        }

        @RestrictTo(value={Scope.LIBRARY_GROUP}) @Retention(value=RetentionPolicy.SOURCE) @public interface Flags {
        }

        public static final Parcelable$Creator CREATOR = null;
        public static final int FLAG_BROWSABLE = 1;
        public static final int FLAG_PLAYABLE = 2;
        private final MediaDescriptionCompat mDescription;
        private final int mFlags;

        static {
            MediaItem.CREATOR = new android.support.v4.media.MediaBrowserCompat$MediaItem$1();
        }

        MediaItem(Parcel arg2) {
            super();
            this.mFlags = arg2.readInt();
            this.mDescription = MediaDescriptionCompat.CREATOR.createFromParcel(arg2);
        }

        public MediaItem(@NonNull MediaDescriptionCompat arg2, int arg3) {
            super();
            if(arg2 == null) {
                throw new IllegalArgumentException("description cannot be null");
            }

            if(TextUtils.isEmpty(arg2.getMediaId())) {
                throw new IllegalArgumentException("description must have a non-empty media id");
            }

            this.mFlags = arg3;
            this.mDescription = arg2;
        }

        public int describeContents() {
            return 0;
        }

        public static MediaItem fromMediaItem(Object arg2) {
            if(arg2 != null) {
                if(Build$VERSION.SDK_INT < 21) {
                }
                else {
                    return new MediaItem(MediaDescriptionCompat.fromMediaDescription(android.support.v4.media.MediaBrowserCompatApi21$MediaItem.getDescription(arg2)), android.support.v4.media.MediaBrowserCompatApi21$MediaItem.getFlags(arg2));
                }
            }

            return null;
        }

        public static List fromMediaItemList(List arg2) {
            if(arg2 != null) {
                if(Build$VERSION.SDK_INT < 21) {
                }
                else {
                    ArrayList v0 = new ArrayList(arg2.size());
                    Iterator v2 = arg2.iterator();
                    while(v2.hasNext()) {
                        ((List)v0).add(MediaItem.fromMediaItem(v2.next()));
                    }

                    return ((List)v0);
                }
            }

            return null;
        }

        @NonNull public MediaDescriptionCompat getDescription() {
            return this.mDescription;
        }

        public int getFlags() {
            return this.mFlags;
        }

        @Nullable public String getMediaId() {
            return this.mDescription.getMediaId();
        }

        public boolean isBrowsable() {
            boolean v1 = true;
            if((this.mFlags & 1) != 0) {
            }
            else {
                v1 = false;
            }

            return v1;
        }

        public boolean isPlayable() {
            boolean v0 = (this.mFlags & 2) != 0 ? true : false;
            return v0;
        }

        public String toString() {
            StringBuilder v0 = new StringBuilder("MediaItem{");
            v0.append("mFlags=");
            v0.append(this.mFlags);
            v0.append(", mDescription=");
            v0.append(this.mDescription);
            v0.append('}');
            return v0.toString();
        }

        public void writeToParcel(Parcel arg2, int arg3) {
            arg2.writeInt(this.mFlags);
            this.mDescription.writeToParcel(arg2, arg3);
        }
    }

    public abstract class SearchCallback {
        public SearchCallback() {
            super();
        }

        public void onError(@NonNull String arg1, Bundle arg2) {
        }

        public void onSearchResult(@NonNull String arg1, Bundle arg2, @NonNull List arg3) {
        }
    }

    class SearchResultReceiver extends ResultReceiver {
        private final SearchCallback mCallback;
        private final Bundle mExtras;
        private final String mQuery;

        SearchResultReceiver(String arg1, Bundle arg2, SearchCallback arg3, Handler arg4) {
            super(arg4);
            this.mQuery = arg1;
            this.mExtras = arg2;
            this.mCallback = arg3;
        }

        protected void onReceiveResult(int arg4, Bundle arg5) {
            if(arg5 != null) {
                arg5.setClassLoader(MediaBrowserCompat.class.getClassLoader());
            }

            if(arg4 == 0 && arg5 != null) {
                if(!arg5.containsKey("search_results")) {
                }
                else {
                    Parcelable[] v4 = arg5.getParcelableArray("search_results");
                    List v5 = null;
                    if(v4 != null) {
                        ArrayList v5_1 = new ArrayList();
                        int v0 = v4.length;
                        int v1;
                        for(v1 = 0; v1 < v0; ++v1) {
                            ((List)v5_1).add(v4[v1]);
                        }
                    }

                    this.mCallback.onSearchResult(this.mQuery, this.mExtras, v5);
                    return;
                }
            }

            this.mCallback.onError(this.mQuery, this.mExtras);
        }
    }

    class ServiceBinderWrapper {
        private Messenger mMessenger;
        private Bundle mRootHints;

        public ServiceBinderWrapper(IBinder arg2, Bundle arg3) {
            super();
            this.mMessenger = new Messenger(arg2);
            this.mRootHints = arg3;
        }

        void addSubscription(String arg3, IBinder arg4, Bundle arg5, Messenger arg6) throws RemoteException {
            Bundle v0 = new Bundle();
            v0.putString("data_media_item_id", arg3);
            BundleCompat.putBinder(v0, "data_callback_token", arg4);
            v0.putBundle("data_options", arg5);
            this.sendRequest(3, v0, arg6);
        }

        void connect(Context arg3, Messenger arg4) throws RemoteException {
            Bundle v0 = new Bundle();
            v0.putString("data_package_name", arg3.getPackageName());
            v0.putBundle("data_root_hints", this.mRootHints);
            this.sendRequest(1, v0, arg4);
        }

        void disconnect(Messenger arg3) throws RemoteException {
            this.sendRequest(2, null, arg3);
        }

        void getMediaItem(String arg3, ResultReceiver arg4, Messenger arg5) throws RemoteException {
            Bundle v0 = new Bundle();
            v0.putString("data_media_item_id", arg3);
            v0.putParcelable("data_result_receiver", ((Parcelable)arg4));
            this.sendRequest(5, v0, arg5);
        }

        void registerCallbackMessenger(Messenger arg4) throws RemoteException {
            Bundle v0 = new Bundle();
            v0.putBundle("data_root_hints", this.mRootHints);
            this.sendRequest(6, v0, arg4);
        }

        void removeSubscription(String arg3, IBinder arg4, Messenger arg5) throws RemoteException {
            Bundle v0 = new Bundle();
            v0.putString("data_media_item_id", arg3);
            BundleCompat.putBinder(v0, "data_callback_token", arg4);
            this.sendRequest(4, v0, arg5);
        }

        void search(String arg3, Bundle arg4, ResultReceiver arg5, Messenger arg6) throws RemoteException {
            Bundle v0 = new Bundle();
            v0.putString("data_search_query", arg3);
            v0.putBundle("data_search_extras", arg4);
            v0.putParcelable("data_result_receiver", ((Parcelable)arg5));
            this.sendRequest(8, v0, arg6);
        }

        void sendCustomAction(String arg3, Bundle arg4, ResultReceiver arg5, Messenger arg6) throws RemoteException {
            Bundle v0 = new Bundle();
            v0.putString("data_custom_action", arg3);
            v0.putBundle("data_custom_action_extras", arg4);
            v0.putParcelable("data_result_receiver", ((Parcelable)arg5));
            this.sendRequest(9, v0, arg6);
        }

        private void sendRequest(int arg2, Bundle arg3, Messenger arg4) throws RemoteException {
            Message v0 = Message.obtain();
            v0.what = arg2;
            v0.arg1 = 1;
            v0.setData(arg3);
            v0.replyTo = arg4;
            this.mMessenger.send(v0);
        }

        void unregisterCallbackMessenger(Messenger arg3) throws RemoteException {
            this.sendRequest(7, null, arg3);
        }
    }

    class Subscription {
        private final List mCallbacks;
        private final List mOptionsList;

        public Subscription() {
            super();
            this.mCallbacks = new ArrayList();
            this.mOptionsList = new ArrayList();
        }

        public SubscriptionCallback getCallback(Context arg2, Bundle arg3) {
            if(arg3 != null) {
                arg3.setClassLoader(arg2.getClassLoader());
            }

            int v2;
            for(v2 = 0; v2 < this.mOptionsList.size(); ++v2) {
                if(MediaBrowserCompatUtils.areSameOptions(this.mOptionsList.get(v2), arg3)) {
                    return this.mCallbacks.get(v2);
                }
            }

            return null;
        }

        public List getCallbacks() {
            return this.mCallbacks;
        }

        public List getOptionsList() {
            return this.mOptionsList;
        }

        public boolean isEmpty() {
            return this.mCallbacks.isEmpty();
        }

        public void putCallback(Context arg2, Bundle arg3, SubscriptionCallback arg4) {
            if(arg3 != null) {
                arg3.setClassLoader(arg2.getClassLoader());
            }

            int v2;
            for(v2 = 0; v2 < this.mOptionsList.size(); ++v2) {
                if(MediaBrowserCompatUtils.areSameOptions(this.mOptionsList.get(v2), arg3)) {
                    this.mCallbacks.set(v2, arg4);
                    return;
                }
            }

            this.mCallbacks.add(arg4);
            this.mOptionsList.add(arg3);
        }
    }

    public abstract class SubscriptionCallback {
        class android.support.v4.media.MediaBrowserCompat$SubscriptionCallback$StubApi21 implements android.support.v4.media.MediaBrowserCompatApi21$SubscriptionCallback {
            android.support.v4.media.MediaBrowserCompat$SubscriptionCallback$StubApi21(SubscriptionCallback arg1) {
                SubscriptionCallback.this = arg1;
                super();
            }

            List applyOptions(List arg4, Bundle arg5) {
                if(arg4 == null) {
                    return null;
                }

                int v1 = -1;
                int v0 = arg5.getInt("android.media.browse.extra.PAGE", v1);
                int v5 = arg5.getInt("android.media.browse.extra.PAGE_SIZE", v1);
                if(v0 == v1 && v5 == v1) {
                    return arg4;
                }

                v1 = v5 * v0;
                int v2 = v1 + v5;
                if(v0 >= 0 && v5 >= 1) {
                    if(v1 >= arg4.size()) {
                    }
                    else {
                        if(v2 > arg4.size()) {
                            v2 = arg4.size();
                        }

                        return arg4.subList(v1, v2);
                    }
                }

                return Collections.EMPTY_LIST;
            }

            public void onChildrenLoaded(@NonNull String arg7, List arg8) {
                Object v0 = SubscriptionCallback.this.mSubscriptionRef == null ? null : SubscriptionCallback.this.mSubscriptionRef.get();
                if(v0 == null) {
                    SubscriptionCallback.this.onChildrenLoaded(arg7, MediaItem.fromMediaItemList(arg8));
                }
                else {
                    arg8 = MediaItem.fromMediaItemList(arg8);
                    List v1 = ((Subscription)v0).getCallbacks();
                    List v0_1 = ((Subscription)v0).getOptionsList();
                    int v2;
                    for(v2 = 0; v2 < v1.size(); ++v2) {
                        Object v3 = v0_1.get(v2);
                        if(v3 == null) {
                            SubscriptionCallback.this.onChildrenLoaded(arg7, arg8);
                        }
                        else {
                            SubscriptionCallback.this.onChildrenLoaded(arg7, this.applyOptions(arg8, ((Bundle)v3)), ((Bundle)v3));
                        }
                    }
                }
            }

            public void onError(@NonNull String arg2) {
                SubscriptionCallback.this.onError(arg2);
            }
        }

        class StubApi24 extends android.support.v4.media.MediaBrowserCompat$SubscriptionCallback$StubApi21 implements android.support.v4.media.MediaBrowserCompatApi24$SubscriptionCallback {
            StubApi24(SubscriptionCallback arg1) {
                SubscriptionCallback.this = arg1;
                super(arg1);
            }

            public void onChildrenLoaded(@NonNull String arg2, List arg3, @NonNull Bundle arg4) {
                SubscriptionCallback.this.onChildrenLoaded(arg2, MediaItem.fromMediaItemList(arg3), arg4);
            }

            public void onError(@NonNull String arg2, @NonNull Bundle arg3) {
                SubscriptionCallback.this.onError(arg2, arg3);
            }
        }

        private final Object mSubscriptionCallbackObj;
        WeakReference mSubscriptionRef;
        private final IBinder mToken;

        public SubscriptionCallback() {
            super();
            IBinder v1 = null;
            if(Build$VERSION.SDK_INT >= 26) {
                this.mSubscriptionCallbackObj = MediaBrowserCompatApi24.createSubscriptionCallback(new StubApi24(this));
                this.mToken = v1;
            }
            else if(Build$VERSION.SDK_INT >= 21) {
                this.mSubscriptionCallbackObj = MediaBrowserCompatApi21.createSubscriptionCallback(new android.support.v4.media.MediaBrowserCompat$SubscriptionCallback$StubApi21(this));
                this.mToken = new Binder();
            }
            else {
                this.mSubscriptionCallbackObj = v1;
                this.mToken = new Binder();
            }
        }

        static IBinder access$000(SubscriptionCallback arg0) {
            return arg0.mToken;
        }

        static void access$100(SubscriptionCallback arg0, Subscription arg1) {
            arg0.setSubscription(arg1);
        }

        static Object access$200(SubscriptionCallback arg0) {
            return arg0.mSubscriptionCallbackObj;
        }

        public void onChildrenLoaded(@NonNull String arg1, @NonNull List arg2) {
        }

        public void onChildrenLoaded(@NonNull String arg1, @NonNull List arg2, @NonNull Bundle arg3) {
        }

        public void onError(@NonNull String arg1) {
        }

        public void onError(@NonNull String arg1, @NonNull Bundle arg2) {
        }

        private void setSubscription(Subscription arg2) {
            this.mSubscriptionRef = new WeakReference(arg2);
        }
    }

    public static final String CUSTOM_ACTION_DOWNLOAD = "android.support.v4.media.action.DOWNLOAD";
    public static final String CUSTOM_ACTION_REMOVE_DOWNLOADED_FILE = "android.support.v4.media.action.REMOVE_DOWNLOADED_FILE";
    static final boolean DEBUG = false;
    public static final String EXTRA_DOWNLOAD_PROGRESS = "android.media.browse.extra.DOWNLOAD_PROGRESS";
    public static final String EXTRA_MEDIA_ID = "android.media.browse.extra.MEDIA_ID";
    public static final String EXTRA_PAGE = "android.media.browse.extra.PAGE";
    public static final String EXTRA_PAGE_SIZE = "android.media.browse.extra.PAGE_SIZE";
    static final String TAG = "MediaBrowserCompat";
    private final MediaBrowserImpl mImpl;

    static {
        MediaBrowserCompat.DEBUG = Log.isLoggable("MediaBrowserCompat", 3);
    }

    public MediaBrowserCompat(Context arg3, ComponentName arg4, ConnectionCallback arg5, Bundle arg6) {
        super();
        if(Build$VERSION.SDK_INT >= 26) {
            this.mImpl = new MediaBrowserImplApi24(arg3, arg4, arg5, arg6);
        }
        else if(Build$VERSION.SDK_INT >= 23) {
            this.mImpl = new MediaBrowserImplApi23(arg3, arg4, arg5, arg6);
        }
        else if(Build$VERSION.SDK_INT >= 21) {
            this.mImpl = new MediaBrowserImplApi21(arg3, arg4, arg5, arg6);
        }
        else {
            this.mImpl = new MediaBrowserImplBase(arg3, arg4, arg5, arg6);
        }
    }

    public void connect() {
        this.mImpl.connect();
    }

    public void disconnect() {
        this.mImpl.disconnect();
    }

    @Nullable public Bundle getExtras() {
        return this.mImpl.getExtras();
    }

    public void getItem(@NonNull String arg2, @NonNull ItemCallback arg3) {
        this.mImpl.getItem(arg2, arg3);
    }

    @NonNull public String getRoot() {
        return this.mImpl.getRoot();
    }

    @NonNull public ComponentName getServiceComponent() {
        return this.mImpl.getServiceComponent();
    }

    @NonNull public Token getSessionToken() {
        return this.mImpl.getSessionToken();
    }

    public boolean isConnected() {
        return this.mImpl.isConnected();
    }

    public void search(@NonNull String arg2, Bundle arg3, @NonNull SearchCallback arg4) {
        if(TextUtils.isEmpty(((CharSequence)arg2))) {
            throw new IllegalArgumentException("query cannot be empty");
        }

        if(arg4 == null) {
            throw new IllegalArgumentException("callback cannot be null");
        }

        this.mImpl.search(arg2, arg3, arg4);
    }

    public void sendCustomAction(@NonNull String arg2, Bundle arg3, @Nullable CustomActionCallback arg4) {
        if(TextUtils.isEmpty(((CharSequence)arg2))) {
            throw new IllegalArgumentException("action cannot be empty");
        }

        this.mImpl.sendCustomAction(arg2, arg3, arg4);
    }

    public void subscribe(@NonNull String arg2, @NonNull Bundle arg3, @NonNull SubscriptionCallback arg4) {
        if(TextUtils.isEmpty(((CharSequence)arg2))) {
            throw new IllegalArgumentException("parentId is empty");
        }

        if(arg4 == null) {
            throw new IllegalArgumentException("callback is null");
        }

        if(arg3 == null) {
            throw new IllegalArgumentException("options are null");
        }

        this.mImpl.subscribe(arg2, arg3, arg4);
    }

    public void subscribe(@NonNull String arg3, @NonNull SubscriptionCallback arg4) {
        if(TextUtils.isEmpty(((CharSequence)arg3))) {
            throw new IllegalArgumentException("parentId is empty");
        }

        if(arg4 == null) {
            throw new IllegalArgumentException("callback is null");
        }

        this.mImpl.subscribe(arg3, null, arg4);
    }

    public void unsubscribe(@NonNull String arg3) {
        if(TextUtils.isEmpty(((CharSequence)arg3))) {
            throw new IllegalArgumentException("parentId is empty");
        }

        this.mImpl.unsubscribe(arg3, null);
    }

    public void unsubscribe(@NonNull String arg2, @NonNull SubscriptionCallback arg3) {
        if(TextUtils.isEmpty(((CharSequence)arg2))) {
            throw new IllegalArgumentException("parentId is empty");
        }

        if(arg3 == null) {
            throw new IllegalArgumentException("callback is null");
        }

        this.mImpl.unsubscribe(arg2, arg3);
    }
}

