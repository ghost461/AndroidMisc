package android.support.v4.view;

import android.os.Build$VERSION;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeProviderCompat;
import android.view.View$AccessibilityDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;

public class AccessibilityDelegateCompat {
    @RequiresApi(value=16) class AccessibilityDelegateApi16Impl extends AccessibilityDelegateBaseImpl {
        AccessibilityDelegateApi16Impl() {
            super();
        }

        public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View$AccessibilityDelegate arg1, View arg2) {
            AccessibilityNodeProvider v1 = arg1.getAccessibilityNodeProvider(arg2);
            if(v1 != null) {
                return new AccessibilityNodeProviderCompat(v1);
            }

            return null;
        }

        public View$AccessibilityDelegate newAccessibilityDelegateBridge(AccessibilityDelegateCompat arg2) {
            return new View$AccessibilityDelegate(arg2) {
                public boolean dispatchPopulateAccessibilityEvent(View arg2, AccessibilityEvent arg3) {
                    return this.val$compat.dispatchPopulateAccessibilityEvent(arg2, arg3);
                }

                public AccessibilityNodeProvider getAccessibilityNodeProvider(View arg2) {
                    AccessibilityNodeProvider v2_2;
                    AccessibilityNodeProviderCompat v2 = this.val$compat.getAccessibilityNodeProvider(arg2);
                    if(v2 != null) {
                        Object v2_1 = v2.getProvider();
                    }
                    else {
                        v2_2 = null;
                    }

                    return v2_2;
                }

                public void onInitializeAccessibilityEvent(View arg2, AccessibilityEvent arg3) {
                    this.val$compat.onInitializeAccessibilityEvent(arg2, arg3);
                }

                public void onInitializeAccessibilityNodeInfo(View arg2, AccessibilityNodeInfo arg3) {
                    this.val$compat.onInitializeAccessibilityNodeInfo(arg2, AccessibilityNodeInfoCompat.wrap(arg3));
                }

                public void onPopulateAccessibilityEvent(View arg2, AccessibilityEvent arg3) {
                    this.val$compat.onPopulateAccessibilityEvent(arg2, arg3);
                }

                public boolean onRequestSendAccessibilityEvent(ViewGroup arg2, View arg3, AccessibilityEvent arg4) {
                    return this.val$compat.onRequestSendAccessibilityEvent(arg2, arg3, arg4);
                }

                public boolean performAccessibilityAction(View arg2, int arg3, Bundle arg4) {
                    return this.val$compat.performAccessibilityAction(arg2, arg3, arg4);
                }

                public void sendAccessibilityEvent(View arg2, int arg3) {
                    this.val$compat.sendAccessibilityEvent(arg2, arg3);
                }

                public void sendAccessibilityEventUnchecked(View arg2, AccessibilityEvent arg3) {
                    this.val$compat.sendAccessibilityEventUnchecked(arg2, arg3);
                }
            };
        }

        public boolean performAccessibilityAction(View$AccessibilityDelegate arg1, View arg2, int arg3, Bundle arg4) {
            return arg1.performAccessibilityAction(arg2, arg3, arg4);
        }
    }

    class AccessibilityDelegateBaseImpl {
        AccessibilityDelegateBaseImpl() {
            super();
        }

        public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View$AccessibilityDelegate arg1, View arg2) {
            return null;
        }

        public View$AccessibilityDelegate newAccessibilityDelegateBridge(AccessibilityDelegateCompat arg2) {
            return new View$AccessibilityDelegate(arg2) {
                public boolean dispatchPopulateAccessibilityEvent(View arg2, AccessibilityEvent arg3) {
                    return this.val$compat.dispatchPopulateAccessibilityEvent(arg2, arg3);
                }

                public void onInitializeAccessibilityEvent(View arg2, AccessibilityEvent arg3) {
                    this.val$compat.onInitializeAccessibilityEvent(arg2, arg3);
                }

                public void onInitializeAccessibilityNodeInfo(View arg2, AccessibilityNodeInfo arg3) {
                    this.val$compat.onInitializeAccessibilityNodeInfo(arg2, AccessibilityNodeInfoCompat.wrap(arg3));
                }

                public void onPopulateAccessibilityEvent(View arg2, AccessibilityEvent arg3) {
                    this.val$compat.onPopulateAccessibilityEvent(arg2, arg3);
                }

                public boolean onRequestSendAccessibilityEvent(ViewGroup arg2, View arg3, AccessibilityEvent arg4) {
                    return this.val$compat.onRequestSendAccessibilityEvent(arg2, arg3, arg4);
                }

                public void sendAccessibilityEvent(View arg2, int arg3) {
                    this.val$compat.sendAccessibilityEvent(arg2, arg3);
                }

                public void sendAccessibilityEventUnchecked(View arg2, AccessibilityEvent arg3) {
                    this.val$compat.sendAccessibilityEventUnchecked(arg2, arg3);
                }
            };
        }

        public boolean performAccessibilityAction(View$AccessibilityDelegate arg1, View arg2, int arg3, Bundle arg4) {
            return 0;
        }
    }

    private static final View$AccessibilityDelegate DEFAULT_DELEGATE;
    private static final AccessibilityDelegateBaseImpl IMPL;
    final View$AccessibilityDelegate mBridge;

    static {
        AccessibilityDelegateCompat.IMPL = Build$VERSION.SDK_INT >= 16 ? new AccessibilityDelegateApi16Impl() : new AccessibilityDelegateBaseImpl();
        AccessibilityDelegateCompat.DEFAULT_DELEGATE = new View$AccessibilityDelegate();
    }

    public AccessibilityDelegateCompat() {
        super();
        this.mBridge = AccessibilityDelegateCompat.IMPL.newAccessibilityDelegateBridge(this);
    }

    public boolean dispatchPopulateAccessibilityEvent(View arg2, AccessibilityEvent arg3) {
        return AccessibilityDelegateCompat.DEFAULT_DELEGATE.dispatchPopulateAccessibilityEvent(arg2, arg3);
    }

    public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View arg3) {
        return AccessibilityDelegateCompat.IMPL.getAccessibilityNodeProvider(AccessibilityDelegateCompat.DEFAULT_DELEGATE, arg3);
    }

    View$AccessibilityDelegate getBridge() {
        return this.mBridge;
    }

    public void onInitializeAccessibilityEvent(View arg2, AccessibilityEvent arg3) {
        AccessibilityDelegateCompat.DEFAULT_DELEGATE.onInitializeAccessibilityEvent(arg2, arg3);
    }

    public void onInitializeAccessibilityNodeInfo(View arg2, AccessibilityNodeInfoCompat arg3) {
        AccessibilityDelegateCompat.DEFAULT_DELEGATE.onInitializeAccessibilityNodeInfo(arg2, arg3.unwrap());
    }

    public void onPopulateAccessibilityEvent(View arg2, AccessibilityEvent arg3) {
        AccessibilityDelegateCompat.DEFAULT_DELEGATE.onPopulateAccessibilityEvent(arg2, arg3);
    }

    public boolean onRequestSendAccessibilityEvent(ViewGroup arg2, View arg3, AccessibilityEvent arg4) {
        return AccessibilityDelegateCompat.DEFAULT_DELEGATE.onRequestSendAccessibilityEvent(arg2, arg3, arg4);
    }

    public boolean performAccessibilityAction(View arg3, int arg4, Bundle arg5) {
        return AccessibilityDelegateCompat.IMPL.performAccessibilityAction(AccessibilityDelegateCompat.DEFAULT_DELEGATE, arg3, arg4, arg5);
    }

    public void sendAccessibilityEvent(View arg2, int arg3) {
        AccessibilityDelegateCompat.DEFAULT_DELEGATE.sendAccessibilityEvent(arg2, arg3);
    }

    public void sendAccessibilityEventUnchecked(View arg2, AccessibilityEvent arg3) {
        AccessibilityDelegateCompat.DEFAULT_DELEGATE.sendAccessibilityEventUnchecked(arg2, arg3);
    }
}

