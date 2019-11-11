package com.example.xposedtest;

import dalvik.system.DexClassLoader;
import dalvik.system.BaseDexClassLoader;
import dalvik.system.PathClassLoader;
import dalvik.system.DelegateLastClassLoader;
import de.robv.android.xposed.*;
import de.robv.android.xposed.callbacks.*;
import java.lang.ClassLoader;
import java.io.File;
import java.lang.System;

public class TrytoHook implements IXposedHookLoadPackage{
    // Print all Function and dexpath
    public int printALL = 0;
    // Function list, print or not
    // The changed configuration will take effect immediately
    public boolean showBaseDexClassLoader = true;
    public boolean showDexClassLoader = true;
    public boolean showPathClassLoader = false;
    public boolean showDelegateLastClassLoader = true;
    public boolean showSystemload = true;
    // Function list, hook or not
    // The changed configuration will not take effect immediately
    public boolean hookSystemload = false;
    public boolean hookBaseDexClassLoader = true;
    public boolean hookDexClassLoader = true;
    public boolean hookPathClassLoader = false;
    public boolean hookDelegateLastClassLoader = true;

    // Print if it is the target string
    public void isTarget(String packagename, String Funcname, String dexpath){
        if (printALL == 1) {
            XposedBridge.log(packagename + ": " + Funcname + "(" + dexpath + ")");
            return;
        }
        if(dexpath == null){
            return;
        }
        if(!showBaseDexClassLoader && Funcname.indexOf("BaseDexClassLoader") != -1){
            return;
        }
        if(!showDexClassLoader && Funcname.indexOf("DexClassLoader") != -1){
            return;
        }
        if(!showPathClassLoader && Funcname.indexOf("PathClassLoader") != -1){
            return;
        }
        if(!showDelegateLastClassLoader && Funcname.indexOf("DelegateLastClassLoader") != -1){
            return;
        }
        if(!showSystemload && Funcname.indexOf("System.load") != -1){
            return;
        }
        if(dexpath.indexOf("/data/app") != 0 || dexpath.indexOf("/system/framework") != 0){
            XposedBridge.log(packagename + ": " + Funcname + "(" + dexpath + ")");
        }
    }
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable{
        final String Packagename = loadPackageParam.packageName;
        /*
        // Just a hook test, forget it
        if (loadPackageParam.packageName.equals("com.example.myapplication")){
            Class clazz = loadPackageParam.classLoader.loadClass("com.example.myapplication.MainActivity");
            XposedHelpers.findAndHookMethod(clazz, "stringFromJNI", new XC_MethodHook() {
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                }

                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    param.setResult("Hook successful");
                }
            });
        }
        */

        // Hook WeChat to download my zip
        if (loadPackageParam.packageName.indexOf("com.tencent.mm") != -1){
            //XposedBridge.log("Find a WeChat proccess:" + loadPackageParam.packageName);
            Class clazz_XWalkEnvironment = loadPackageParam.classLoader.loadClass("org.xwalk.core.XWalkEnvironment");
            XposedHelpers.findAndHookMethod(clazz_XWalkEnvironment, "getXWalkUpdateConfigUrl", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                }
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    String str = (String)param.getResult();
                    XposedBridge.log("Method getXWalkUpdateConfigUrl() is Running.");
                    XposedBridge.log("Method result value: " + str);
                    param.setResult("http://dldir1.qq.com/weixin/android/wxweb/updateConfig.xml");
                }
            });

            /*
            final Class clazz_tencentXWalk_bg = loadPackageParam.classLoader.loadClass("com.tencent.xweb.xwalk.b.g");
            final Class clazz_tencentXWalk_bg_c = loadPackageParam.classLoader.loadClass("com.tencent.xweb.xwalk.b.g$c");
            XposedHelpers.findAndHookMethod(clazz_tencentXWalk_bg, "doInBackground", Object[].class, new XC_MethodHook(){
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                }
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("com.tencent.xweb.xwalk.b.g.doInBackground() is running.");
                }
            });
            */
        }
        // Try to Find RCE
        // BaseDexClassLoader
        //Class Clazz_BaseDexClassLoader = loadPackageParam.classLoader.loadClass("dalvik.system.BaseDexClassLoader");
        Class Clazz_BaseDexClassLoader = BaseDexClassLoader.class;
        if (hookBaseDexClassLoader)
            XposedHelpers.findAndHookConstructor(Clazz_BaseDexClassLoader, String.class, File.class, String.class, ClassLoader.class, new XC_MethodHook(){
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable{
                    super.beforeHookedMethod(param);
                }
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    //XposedBridge.log(Packagename + ": BaseDexClassLoader(" + param.args[0] + ")");
                    isTarget(Packagename, "BaseDexClassLoader", (String)param.args[0]);
                }
            });
        // DexClassLoader
        //Class Clazz_DexClassLoader = loadPackageParam.classLoader.loadClass("dalvik.system.DexClassLoader");
        Class Clazz_DexClassLoader = DexClassLoader.class;
        if (hookDexClassLoader)
            XposedHelpers.findAndHookConstructor(Clazz_DexClassLoader, String.class, String.class, String.class, ClassLoader.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                }

                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    //XposedBridge.log(Packagename + ": DexClassLoader(" + param.args[0] + ")");
                    isTarget(Packagename, "DexClassLoader", (String)param.args[0]);
                }
            });
        // PathClassLoader
        //Class Clazz_PathClassLoader = loadPackageParam.classLoader.loadClass("dalvik.system.PathClassLoader");
        Class Clazz_PathClassLoader = PathClassLoader.class;
        if (hookPathClassLoader)
            XposedHelpers.findAndHookConstructor(Clazz_PathClassLoader, String.class, ClassLoader.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                }

                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    //XposedBridge.log(Packagename + ": PathClassLoader(" + param.args[0] + ")");
                    isTarget(Packagename, "PathClassLoader", (String)param.args[0]);
                }
            });
            XposedHelpers.findAndHookConstructor(Clazz_PathClassLoader, String.class, String.class, ClassLoader.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                }

                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    //XposedBridge.log(Packagename + ": PathClassLoader(" + param.args[0] + ")");
                    isTarget(Packagename, "PathClassLoader", (String)param.args[0]);
                }
            });
        // DelegateLastClassLoader
        //Class Clazz_DelegateLastClassLoader = loadPackageParam.classLoader.loadClass("dalvik.system.DelegateLastClassLoader");
        Class Clazz_DelegateLastClassLoader = DelegateLastClassLoader.class;
        if (hookDelegateLastClassLoader)
            XposedHelpers.findAndHookConstructor(Clazz_DelegateLastClassLoader, String.class, ClassLoader.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                }

                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    //XposedBridge.log(Packagename + ": DelegateLastClassLoader(" + param.args[0] + ")");
                    isTarget(Packagename, "DelegateLastClassLoader", (String)param.args[0]);
                }
            });
            XposedHelpers.findAndHookConstructor(Clazz_DelegateLastClassLoader, String.class, String.class, ClassLoader.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                }

                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    //XposedBridge.log(Packagename + ": DelegateLastClassLoader(" + param.args[0] + ")");
                    isTarget(Packagename, "DelegateLastClassLoader", (String)param.args[0]);
                }
            });
        /*
        // This function has been abandoned
        XposedHelpers.findAndHookConstructor(Clazz_DelegateLastClassLoader, String.class, String.class, ClassLoader.class, boolean.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
            }

            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                XposedBridge.log(Packagename + ": DelegateLastClassLoader(" + param.args[0] + ")");
            }
        });
        */
        if (hookSystemload)
            XposedHelpers.findAndHookMethod(System.class, "load", String.class, new XC_MethodHook(){
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    isTarget(Packagename, "System.load", (String)param.args[0]);
                }
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                }
            });
        /*
        // Do not Hook this Function!!! Your Phone will can't boot!!!
        XposedHelpers.findAndHookMethod(System.class, "loadLibrary", String.class, new XC_MethodHook(){
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
            }
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                isTarget(Packagename, "System.loadLibrary", (String)param.args[0]);
            }
        });
        */
    }
}
