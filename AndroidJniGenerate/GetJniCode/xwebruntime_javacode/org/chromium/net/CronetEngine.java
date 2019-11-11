package org.chromium.net;

import android.content.Context;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandlerFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;

public abstract class CronetEngine {
    public class Builder {
        public abstract class LibraryLoader {
            public LibraryLoader() {
                super();
            }

            public abstract void loadLibrary(String arg1);
        }

        public static final int HTTP_CACHE_DISABLED = 0;
        public static final int HTTP_CACHE_DISK = 3;
        public static final int HTTP_CACHE_DISK_NO_HTTP = 2;
        public static final int HTTP_CACHE_IN_MEMORY = 1;
        protected final ICronetEngineBuilder mBuilderDelegate;

        public Builder(Context arg1) {
            this(Builder.createBuilderDelegate(arg1));
        }

        public Builder(ICronetEngineBuilder arg1) {
            super();
            this.mBuilderDelegate = arg1;
        }

        public Builder addPublicKeyPins(String arg2, Set arg3, boolean arg4, Date arg5) {
            this.mBuilderDelegate.addPublicKeyPins(arg2, arg3, arg4, arg5);
            return this;
        }

        public Builder addQuicHint(String arg2, int arg3, int arg4) {
            this.mBuilderDelegate.addQuicHint(arg2, arg3, arg4);
            return this;
        }

        public CronetEngine build() {
            return this.mBuilderDelegate.build();
        }

        @VisibleForTesting static int compareVersions(String arg5, String arg6) {
            if(arg5 != null) {
                if(arg6 == null) {
                }
                else {
                    String[] v5 = arg5.split("\\.");
                    String[] v6 = arg6.split("\\.");
                    int v0;
                    for(v0 = 0; v0 < v5.length; ++v0) {
                        if(v0 >= v6.length) {
                            break;
                        }

                        try {
                            int v1_1 = Integer.parseInt(v5[v0]);
                            int v2 = Integer.parseInt(v6[v0]);
                            if(v1_1 != v2) {
                                return Integer.signum(v1_1 - v2);
                            }
                            else {
                                goto label_20;
                            }

                            break;
                        }
                        catch(NumberFormatException v1) {
                            StringBuilder v3 = new StringBuilder();
                            v3.append("Unable to convert version segments into integers: ");
                            v3.append(v5[v0]);
                            v3.append(" & ");
                            v3.append(v6[v0]);
                            throw new IllegalArgumentException(v3.toString(), ((Throwable)v1));
                        }

                    label_20:
                    }

                    return Integer.signum(v5.length - v6.length);
                }
            }

            throw new IllegalArgumentException("The input values cannot be null");
        }

        private static ICronetEngineBuilder createBuilderDelegate(Context arg4) {
            Object v4 = Builder.getEnabledCronetProviders(arg4, new ArrayList(CronetProvider.getAllProviders(arg4))).get(0);
            if(Log.isLoggable(CronetEngine.TAG, 3)) {
                Log.d(CronetEngine.TAG, String.format("Using \'%s\' provider for creating CronetEngine.Builder.", v4));
            }

            return ((CronetProvider)v4).createBuilder().mBuilderDelegate;
        }

        public Builder enableBrotli(boolean arg2) {
            this.mBuilderDelegate.enableBrotli(arg2);
            return this;
        }

        public Builder enableHttp2(boolean arg2) {
            this.mBuilderDelegate.enableHttp2(arg2);
            return this;
        }

        public Builder enableHttpCache(int arg2, long arg3) {
            this.mBuilderDelegate.enableHttpCache(arg2, arg3);
            return this;
        }

        public Builder enablePublicKeyPinningBypassForLocalTrustAnchors(boolean arg2) {
            this.mBuilderDelegate.enablePublicKeyPinningBypassForLocalTrustAnchors(arg2);
            return this;
        }

        public Builder enableQuic(boolean arg2) {
            this.mBuilderDelegate.enableQuic(arg2);
            return this;
        }

        @Deprecated public Builder enableSdch(boolean arg1) {
            return this;
        }

        public String getDefaultUserAgent() {
            return this.mBuilderDelegate.getDefaultUserAgent();
        }

        @VisibleForTesting static List getEnabledCronetProviders(Context arg1, List arg2) {
            if(arg2.size() == 0) {
                throw new RuntimeException("Unable to find any Cronet provider. Have you included all necessary jars?");
            }

            Iterator v1 = arg2.iterator();
            while(v1.hasNext()) {
                if(v1.next().isEnabled()) {
                    continue;
                }

                v1.remove();
            }

            if(arg2.size() == 0) {
                throw new RuntimeException("All available Cronet providers are disabled. A provider should be enabled before it can be used.");
            }

            Collections.sort(arg2, new Comparator() {
                public int compare(Object arg1, Object arg2) {
                    return this.compare(((CronetProvider)arg1), ((CronetProvider)arg2));
                }

                public int compare(CronetProvider arg3, CronetProvider arg4) {
                    if("Fallback-Cronet-Provider".equals(arg3.getName())) {
                        return 1;
                    }

                    if("Fallback-Cronet-Provider".equals(arg4.getName())) {
                        return -1;
                    }

                    return -Builder.compareVersions(arg3.getVersion(), arg4.getVersion());
                }
            });
            return arg2;
        }

        public Builder setLibraryLoader(LibraryLoader arg2) {
            this.mBuilderDelegate.setLibraryLoader(arg2);
            return this;
        }

        public Builder setStoragePath(String arg2) {
            this.mBuilderDelegate.setStoragePath(arg2);
            return this;
        }

        public Builder setUserAgent(String arg2) {
            this.mBuilderDelegate.setUserAgent(arg2);
            return this;
        }
    }

    private static final String TAG = "CronetEngine";

    static {
    }

    public CronetEngine() {
        super();
    }

    static String access$000() {
        return CronetEngine.TAG;
    }

    public abstract URLStreamHandlerFactory createURLStreamHandlerFactory();

    public abstract byte[] getGlobalMetricsDeltas();

    public abstract String getVersionString();

    public abstract org.chromium.net.UrlRequest$Builder newUrlRequestBuilder(String arg1, Callback arg2, Executor arg3);

    public abstract URLConnection openConnection(URL arg1) throws IOException;

    public abstract void shutdown();

    public abstract void startNetLogToFile(String arg1, boolean arg2);

    public abstract void stopNetLog();
}

