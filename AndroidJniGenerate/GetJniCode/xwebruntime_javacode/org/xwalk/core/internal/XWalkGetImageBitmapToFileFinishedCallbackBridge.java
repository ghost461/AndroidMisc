package org.xwalk.core.internal;

public class XWalkGetImageBitmapToFileFinishedCallbackBridge extends XWalkGetImageBitmapToFileFinishedCallbackInternal {
    private static final String TAG = "XWalkGetImageBitmapToFileFinishedCallbackBridge";
    private XWalkCoreBridge coreBridge;
    private ReflectMethod onFinishImageBitmapToFileMethod;
    private Object wrapper;

    public XWalkGetImageBitmapToFileFinishedCallbackBridge(Object arg5) {
        super();
        this.onFinishImageBitmapToFileMethod = new ReflectMethod(null, "onFinishImageBitmapToFile", new Class[0]);
        this.wrapper = arg5;
        this.reflectionInit();
    }

    public Object getWrapper() {
        return this.wrapper;
    }

    public void onFinishImageBitmapToFile(int arg4, String arg5, String arg6, int arg7, int arg8, String arg9) {
        try {
            Log.d("XWalkGetImageBitmapToFileFinishedCallbackBridge", "onFinishImageBitmapToFile result:" + arg4 + ", imageUrl:" + arg5 + ", filePath:" + arg6 + ", width:" + arg7 + ", height:" + arg8 + ", extraInfo:" + arg9);
            this.onFinishImageBitmapToFileMethod.invoke(new Object[]{Integer.valueOf(arg4), arg5, arg6, Integer.valueOf(arg7), Integer.valueOf(arg8), arg9});
        }
        catch(Exception v4) {
            Log.e("XWalkGetImageBitmapToFileFinishedCallbackBridge", v4.getMessage());
        }
    }

    void reflectionInit() {
        this.coreBridge = XWalkCoreBridge.getInstance();
        if(this.coreBridge == null) {
            return;
        }

        this.onFinishImageBitmapToFileMethod.init(this.wrapper, null, "onFinishImageBitmapToFile", new Class[]{Integer.TYPE, String.class, String.class, Integer.TYPE, Integer.TYPE, String.class});
    }
}

