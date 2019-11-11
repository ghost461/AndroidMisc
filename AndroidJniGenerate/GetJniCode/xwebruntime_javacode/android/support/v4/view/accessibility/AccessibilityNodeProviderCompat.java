package android.support.v4.view.accessibility;

import android.os.Build$VERSION;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import java.util.ArrayList;
import java.util.List;

public class AccessibilityNodeProviderCompat {
    interface AccessibilityNodeProviderImpl {
        Object newAccessibilityNodeProviderBridge(AccessibilityNodeProviderCompat arg1);
    }

    @RequiresApi(value=16) class AccessibilityNodeProviderJellyBeanImpl extends AccessibilityNodeProviderStubImpl {
        AccessibilityNodeProviderJellyBeanImpl() {
            super();
        }

        public Object newAccessibilityNodeProviderBridge(AccessibilityNodeProviderCompat arg2) {
            return AccessibilityNodeProviderCompatJellyBean.newAccessibilityNodeProviderBridge(new AccessibilityNodeInfoBridge(arg2) {
                public Object createAccessibilityNodeInfo(int arg2) {
                    AccessibilityNodeInfoCompat v2 = this.val$compat.createAccessibilityNodeInfo(arg2);
                    if(v2 == null) {
                        return null;
                    }

                    return v2.unwrap();
                }

                public List findAccessibilityNodeInfosByText(String arg4, int arg5) {
                    List v4 = this.val$compat.findAccessibilityNodeInfosByText(arg4, arg5);
                    if(v4 == null) {
                        return null;
                    }

                    ArrayList v5 = new ArrayList();
                    int v0 = v4.size();
                    int v1;
                    for(v1 = 0; v1 < v0; ++v1) {
                        ((List)v5).add(v4.get(v1).unwrap());
                    }

                    return ((List)v5);
                }

                public boolean performAction(int arg2, int arg3, Bundle arg4) {
                    return this.val$compat.performAction(arg2, arg3, arg4);
                }
            });
        }
    }

    @RequiresApi(value=19) class AccessibilityNodeProviderKitKatImpl extends AccessibilityNodeProviderStubImpl {
        AccessibilityNodeProviderKitKatImpl() {
            super();
        }

        public Object newAccessibilityNodeProviderBridge(AccessibilityNodeProviderCompat arg2) {
            return AccessibilityNodeProviderCompatKitKat.newAccessibilityNodeProviderBridge(new android.support.v4.view.accessibility.AccessibilityNodeProviderCompatKitKat$AccessibilityNodeInfoBridge(arg2) {
                public Object createAccessibilityNodeInfo(int arg2) {
                    AccessibilityNodeInfoCompat v2 = this.val$compat.createAccessibilityNodeInfo(arg2);
                    if(v2 == null) {
                        return null;
                    }

                    return v2.unwrap();
                }

                public List findAccessibilityNodeInfosByText(String arg4, int arg5) {
                    List v4 = this.val$compat.findAccessibilityNodeInfosByText(arg4, arg5);
                    if(v4 == null) {
                        return null;
                    }

                    ArrayList v5 = new ArrayList();
                    int v0 = v4.size();
                    int v1;
                    for(v1 = 0; v1 < v0; ++v1) {
                        ((List)v5).add(v4.get(v1).unwrap());
                    }

                    return ((List)v5);
                }

                public Object findFocus(int arg2) {
                    AccessibilityNodeInfoCompat v2 = this.val$compat.findFocus(arg2);
                    if(v2 == null) {
                        return null;
                    }

                    return v2.unwrap();
                }

                public boolean performAction(int arg2, int arg3, Bundle arg4) {
                    return this.val$compat.performAction(arg2, arg3, arg4);
                }
            });
        }
    }

    class AccessibilityNodeProviderStubImpl implements AccessibilityNodeProviderImpl {
        AccessibilityNodeProviderStubImpl() {
            super();
        }

        public Object newAccessibilityNodeProviderBridge(AccessibilityNodeProviderCompat arg1) {
            return null;
        }
    }

    public static final int HOST_VIEW_ID = -1;
    private static final AccessibilityNodeProviderImpl IMPL;
    private final Object mProvider;

    static {
        if(Build$VERSION.SDK_INT >= 19) {
            AccessibilityNodeProviderCompat.IMPL = new AccessibilityNodeProviderKitKatImpl();
        }
        else if(Build$VERSION.SDK_INT >= 16) {
            AccessibilityNodeProviderCompat.IMPL = new AccessibilityNodeProviderJellyBeanImpl();
        }
        else {
            AccessibilityNodeProviderCompat.IMPL = new AccessibilityNodeProviderStubImpl();
        }
    }

    public AccessibilityNodeProviderCompat(Object arg1) {
        super();
        this.mProvider = arg1;
    }

    public AccessibilityNodeProviderCompat() {
        super();
        this.mProvider = AccessibilityNodeProviderCompat.IMPL.newAccessibilityNodeProviderBridge(this);
    }

    @Nullable public AccessibilityNodeInfoCompat createAccessibilityNodeInfo(int arg1) {
        return null;
    }

    @Nullable public List findAccessibilityNodeInfosByText(String arg1, int arg2) {
        return null;
    }

    @Nullable public AccessibilityNodeInfoCompat findFocus(int arg1) {
        return null;
    }

    public Object getProvider() {
        return this.mProvider;
    }

    public boolean performAction(int arg1, int arg2, Bundle arg3) {
        return 0;
    }
}

