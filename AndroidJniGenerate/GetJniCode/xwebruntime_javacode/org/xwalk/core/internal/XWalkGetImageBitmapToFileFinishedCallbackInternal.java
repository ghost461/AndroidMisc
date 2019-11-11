package org.xwalk.core.internal;

@XWalkAPI(createExternally=true) public abstract class XWalkGetImageBitmapToFileFinishedCallbackInternal {
    @XWalkAPI public XWalkGetImageBitmapToFileFinishedCallbackInternal() {
        super();
    }

    @XWalkAPI public abstract void onFinishImageBitmapToFile(int arg1, String arg2, String arg3, int arg4, int arg5, String arg6);
}

