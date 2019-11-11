package org.chromium.content.browser;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import java.util.Iterator;
import org.chromium.base.Log;

public class PepperPluginManager {
    private static final String DESCRIPTION = "description";
    private static final String FILENAME = "filename";
    private static final String MIMETYPE = "mimetype";
    private static final String NAME = "name";
    public static final String PEPPER_PLUGIN_ACTION = "org.chromium.intent.PEPPERPLUGIN";
    public static final String PEPPER_PLUGIN_ROOT = "/system/lib/pepperplugin/";
    private static final String TAG = "cr.PepperPluginManager";
    private static final String VERSION = "version";

    public PepperPluginManager() {
        super();
    }

    private static String getPluginDescription(Bundle arg5) {
        String v0 = arg5.getString("filename");
        String v1 = null;
        if(v0 != null) {
            if(v0.isEmpty()) {
            }
            else {
                String v2 = arg5.getString("mimetype");
                if(v2 != null) {
                    if(v2.isEmpty()) {
                    }
                    else {
                        StringBuilder v1_1 = new StringBuilder("/system/lib/pepperplugin/");
                        v1_1.append(v0);
                        v0 = arg5.getString("name");
                        String v3 = arg5.getString("description");
                        String v5 = arg5.getString("version");
                        if(v0 != null && !v0.isEmpty()) {
                            v1_1.append("#");
                            v1_1.append(v0);
                            if(v3 != null && !v3.isEmpty()) {
                                v1_1.append("#");
                                v1_1.append(v3);
                                if(v5 != null && !v5.isEmpty()) {
                                    v1_1.append("#");
                                    v1_1.append(v5);
                                }
                            }
                        }

                        v1_1.append(';');
                        v1_1.append(v2);
                        return v1_1.toString();
                    }
                }

                return v1;
            }
        }

        return v1;
    }

    @SuppressLint(value={"WrongConstant"}) public static String getPlugins(Context arg8) {
        StringBuilder v0 = new StringBuilder();
        PackageManager v8 = arg8.getPackageManager();
        Iterator v1 = v8.queryIntentServices(new Intent("org.chromium.intent.PEPPERPLUGIN"), 0x84).iterator();
        while(v1.hasNext()) {
            Object v2 = v1.next();
            ServiceInfo v3 = ((ResolveInfo)v2).serviceInfo;
            if(v3 != null && v3.metaData != null) {
                if(v3.packageName == null) {
                }
                else {
                    try {
                        PackageInfo v2_1 = v8.getPackageInfo(v3.packageName, 0);
                        if(v2_1 == null) {
                            continue;
                        }
                    }
                    catch(PackageManager$NameNotFoundException ) {
                        Log.e("cr.PepperPluginManager", "Can\'t find plugin: %s", new Object[]{v3.packageName});
                        continue;
                    }

                    if((v2_1.applicationInfo.flags & 1) == 0) {
                    }
                    else {
                        Log.i("cr.PepperPluginManager", "The given plugin package is preloaded: %s", new Object[]{v3.packageName});
                        String v2_2 = PepperPluginManager.getPluginDescription(v3.metaData);
                        if(v2_2 == null) {
                        }
                        else {
                            if(v0.length() > 0) {
                                v0.append(',');
                            }

                            v0.append(v2_2);
                        }
                    }

                    continue;
                }
            }

            Log.e("cr.PepperPluginManager", "Can\'t get service information from %s", new Object[]{v2});
        }

        return v0.toString();
    }
}

