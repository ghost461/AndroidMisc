package org.xwalk.core.internal;

import android.content.Context;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

class XWalkInternalResources {
    private static final String GENERATED_RESOURCE_CLASS = "org.xwalk.core.R";
    private static final String[] INTERNAL_RESOURCE_CLASSES = null;
    private static final String TAG = "XWalkInternalResources";
    private static boolean loaded = false;

    static {
        XWalkInternalResources.INTERNAL_RESOURCE_CLASSES = new String[]{"org.xwalk.core.R"};
    }

    XWalkInternalResources() {
        super();
    }

    private static void doResetIds(Context arg17) {
        Class v11;
        String v10;
        Class v9;
        int v8;
        ClassLoader v0 = XWalkInternalResources.class.getClassLoader();
        arg17.getApplicationContext().getClassLoader();
        String[] v1 = XWalkInternalResources.INTERNAL_RESOURCE_CLASSES;
        int v2 = v1.length;
        int v4;
        for(v4 = 0; v4 < v2; ++v4) {
            String v5 = v1[v4];
            try {
                Class[] v6 = v0.loadClass(v5).getClasses();
                int v7 = v6.length;
                v8 = 0;
                while(true) {
                label_13:
                    if(v8 >= v7) {
                        goto label_95;
                    }

                    v9 = v6[v8];
                    v10 = v9.getName().replace(((CharSequence)v5), "org.xwalk.core.R");
                    break;
                }
            }
            catch(ClassNotFoundException ) {
                goto label_87;
            }

            try {
                v11 = v0.loadClass(v10);
            }
            catch(ClassNotFoundException ) {
                try {
                    Log.w("XWalkInternalResources", v10 + "is not found.");
                    goto label_82;
                }
                catch(ClassNotFoundException ) {
                    goto label_87;
                }
            }

            try {
                Field[] v9_1 = v9.getFields();
                int v10_1 = v9_1.length;
                int v12 = 0;
                while(true) {
                    if(v12 >= v10_1) {
                        goto label_71;
                    }

                    Field v13 = v9_1[v12];
                    if(Modifier.isFinal(v13.getModifiers())) {
                        v13.setAccessible(true);
                    }

                    try {
                        v13.setInt(null, v11.getField(v13.getName()).getInt(null));
                        goto label_62;
                    }
                    catch(IllegalArgumentException ) {
                    label_62:
                        if(Modifier.isFinal(v13.getModifiers())) {
                            try {
                                v13.setAccessible(false);
                            label_69:
                                ++v12;
                                continue;
                            label_71:
                                break;
                            }
                            catch(ClassNotFoundException ) {
                                goto label_87;
                            }
                        }
                        else {
                        }

                        goto label_69;
                    }
                    catch(NoSuchFieldException ) {
                        Log.w("XWalkInternalResources", v11.getName() + "." + v13.getName() + " is not found.");
                        goto label_62;
                    }
                    catch(IllegalAccessException ) {
                        Log.w("XWalkInternalResources", v11.getName() + "." + v13.getName() + " is not accessable.");
                        goto label_62;
                    }
                }
            }
            catch(ClassNotFoundException ) {
                goto label_87;
            }

        label_82:
            ++v8;
            goto label_13;
        label_87:
            Log.w("XWalkInternalResources", v5 + "is not found.");
        label_95:
        }
    }

    static void resetIds(Context arg1) {
        if(!XWalkInternalResources.loaded) {
            XWalkInternalResources.doResetIds(arg1);
            XWalkInternalResources.loaded = true;
        }
    }
}

