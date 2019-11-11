package org.xwalk.core.internal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class XWalkExternalExtensionManagerBridge extends XWalkExternalExtensionManagerInternal {
    private XWalkCoreBridge coreBridge;
    private ReflectMethod getViewActivityMethod;
    private ReflectMethod getViewContextMethod;
    private ReflectMethod loadExtensionStringMethod;
    private ReflectMethod onActivityResultintintIntentMethod;
    private ReflectMethod onDestroyMethod;
    private ReflectMethod onNewIntentIntentMethod;
    private ReflectMethod onPauseMethod;
    private ReflectMethod onResumeMethod;
    private ReflectMethod onStartMethod;
    private ReflectMethod onStopMethod;
    private Object wrapper;

    public XWalkExternalExtensionManagerBridge(XWalkViewBridge arg5, Object arg6) {
        super(((XWalkViewInternal)arg5));
        this.getViewActivityMethod = new ReflectMethod(null, "getViewActivity", new Class[0]);
        this.getViewContextMethod = new ReflectMethod(null, "getViewContext", new Class[0]);
        this.loadExtensionStringMethod = new ReflectMethod(null, "loadExtension", new Class[0]);
        this.onStartMethod = new ReflectMethod(null, "onStart", new Class[0]);
        this.onResumeMethod = new ReflectMethod(null, "onResume", new Class[0]);
        this.onPauseMethod = new ReflectMethod(null, "onPause", new Class[0]);
        this.onStopMethod = new ReflectMethod(null, "onStop", new Class[0]);
        this.onDestroyMethod = new ReflectMethod(null, "onDestroy", new Class[0]);
        this.onActivityResultintintIntentMethod = new ReflectMethod(null, "onActivityResult", new Class[0]);
        this.onNewIntentIntentMethod = new ReflectMethod(null, "onNewIntent", new Class[0]);
        this.wrapper = arg6;
        this.reflectionInit();
    }

    public Activity getViewActivity() {
        if(this.getViewActivityMethod != null) {
            if(this.getViewActivityMethod.isNull()) {
            }
            else {
                return this.getViewActivityMethod.invoke(new Object[0]);
            }
        }

        return this.getViewActivitySuper();
    }

    public Activity getViewActivitySuper() {
        Activity v0 = super.getViewActivity();
        if(v0 == null) {
            return null;
        }

        return v0;
    }

    public Context getViewContext() {
        if(this.getViewContextMethod != null) {
            if(this.getViewContextMethod.isNull()) {
            }
            else {
                return this.getViewContextMethod.invoke(new Object[0]);
            }
        }

        return this.getViewContextSuper();
    }

    public Context getViewContextSuper() {
        Context v0 = super.getViewContext();
        if(v0 == null) {
            return null;
        }

        return v0;
    }

    public Object getWrapper() {
        return this.wrapper;
    }

    public void loadExtension(String arg4) {
        if(this.loadExtensionStringMethod == null || (this.loadExtensionStringMethod.isNull())) {
            this.loadExtensionSuper(arg4);
        }
        else {
            this.loadExtensionStringMethod.invoke(new Object[]{arg4});
        }
    }

    public void loadExtensionSuper(String arg1) {
        super.loadExtension(arg1);
    }

    public void onActivityResult(int arg4, int arg5, Intent arg6) {
        if(this.onActivityResultintintIntentMethod == null || (this.onActivityResultintintIntentMethod.isNull())) {
            this.onActivityResultSuper(arg4, arg5, arg6);
        }
        else {
            this.onActivityResultintintIntentMethod.invoke(new Object[]{Integer.valueOf(arg4), Integer.valueOf(arg5), arg6});
        }
    }

    public void onActivityResultSuper(int arg1, int arg2, Intent arg3) {
        super.onActivityResult(arg1, arg2, arg3);
    }

    public void onDestroy() {
        if(this.onDestroyMethod == null || (this.onDestroyMethod.isNull())) {
            this.onDestroySuper();
        }
        else {
            this.onDestroyMethod.invoke(new Object[0]);
        }
    }

    public void onDestroySuper() {
        super.onDestroy();
    }

    public void onNewIntent(Intent arg4) {
        this.onNewIntentIntentMethod.invoke(new Object[]{arg4});
    }

    public void onPause() {
        if(this.onPauseMethod == null || (this.onPauseMethod.isNull())) {
            this.onPauseSuper();
        }
        else {
            this.onPauseMethod.invoke(new Object[0]);
        }
    }

    public void onPauseSuper() {
        super.onPause();
    }

    public void onResume() {
        if(this.onResumeMethod == null || (this.onResumeMethod.isNull())) {
            this.onResumeSuper();
        }
        else {
            this.onResumeMethod.invoke(new Object[0]);
        }
    }

    public void onResumeSuper() {
        super.onResume();
    }

    public void onStart() {
        if(this.onStartMethod == null || (this.onStartMethod.isNull())) {
            this.onStartSuper();
        }
        else {
            this.onStartMethod.invoke(new Object[0]);
        }
    }

    public void onStartSuper() {
        super.onStart();
    }

    public void onStop() {
        if(this.onStopMethod == null || (this.onStopMethod.isNull())) {
            this.onStopSuper();
        }
        else {
            this.onStopMethod.invoke(new Object[0]);
        }
    }

    public void onStopSuper() {
        super.onStop();
    }

    void reflectionInit() {
        this.coreBridge = XWalkCoreBridge.getInstance();
        if(this.coreBridge == null) {
            return;
        }

        this.getViewActivityMethod.init(this.wrapper, null, "getViewActivity", new Class[0]);
        this.getViewContextMethod.init(this.wrapper, null, "getViewContext", new Class[0]);
        this.loadExtensionStringMethod.init(this.wrapper, null, "loadExtension", new Class[]{String.class});
        this.onStartMethod.init(this.wrapper, null, "onStart", new Class[0]);
        this.onResumeMethod.init(this.wrapper, null, "onResume", new Class[0]);
        this.onPauseMethod.init(this.wrapper, null, "onPause", new Class[0]);
        this.onStopMethod.init(this.wrapper, null, "onStop", new Class[0]);
        this.onDestroyMethod.init(this.wrapper, null, "onDestroy", new Class[0]);
        this.onActivityResultintintIntentMethod.init(this.wrapper, null, "onActivityResult", new Class[]{Integer.TYPE, Integer.TYPE, Intent.class});
        this.onNewIntentIntentMethod.init(this.wrapper, null, "onNewIntent", new Class[]{Intent.class});
    }
}

