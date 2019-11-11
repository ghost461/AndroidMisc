package org.chromium.content.browser.selection;

import android.animation.Animator$AnimatorListener;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.view.ActionMode$Callback2;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.chromium.base.Log;
import org.chromium.base.PackageUtils;
import org.chromium.base.ThreadUtils;

public final class LGEmailActionModeWorkaround {
    public static final int LGEmailWorkaroundMaxVersion = 0x4060014;
    private static final String TAG = "cr_Ime";

    public LGEmailActionModeWorkaround() {
        super();
    }

    @TargetApi(value=23) private static void allowActionModeDestroyOnNonUiThread(ActionMode arg10) {
        try {
            LGEmailActionModeWorkaround.setField(arg10, "mCallback", new ActionMode$Callback2(LGEmailActionModeWorkaround.getField(arg10, "mCallback")) {
                public boolean onActionItemClicked(ActionMode arg2, MenuItem arg3) {
                    return this.val$c.onActionItemClicked(arg2, arg3);
                }

                public boolean onCreateActionMode(ActionMode arg2, Menu arg3) {
                    return this.val$c.onCreateActionMode(arg2, arg3);
                }

                public void onDestroyActionMode(ActionMode arg2) {
                    ThreadUtils.postOnUiThread(new Runnable(arg2) {
                        public void run() {
                            org.chromium.content.browser.selection.LGEmailActionModeWorkaround$1.this.val$c.onDestroyActionMode(this.val$mode);
                        }
                    });
                }

                public boolean onPrepareActionMode(ActionMode arg2, Menu arg3) {
                    return this.val$c.onPrepareActionMode(arg2, arg3);
                }
            });
            Object v10_1 = LGEmailActionModeWorkaround.getField(arg10, "mFloatingToolbar");
            Object v2 = LGEmailActionModeWorkaround.getField(v10_1, "mPopup");
            Object v3 = LGEmailActionModeWorkaround.getField(v2, "mContentContainer");
            Object v4 = LGEmailActionModeWorkaround.getField(v2, "mPopupWindow");
            Method v10_2 = v10_1.getClass().getDeclaredMethod("createExitAnimation", View.class, Integer.TYPE, Animator$AnimatorListener.class);
            v10_2.setAccessible(true);
            LGEmailActionModeWorkaround.setField(v2, "mDismissAnimation", v10_2.invoke(null, v3, Integer.valueOf(150), new AnimatorListenerAdapter(((PopupWindow)v4), ((ViewGroup)v3)) {
                public void onAnimationEnd(Animator arg1) {
                    ThreadUtils.postOnUiThread(new Runnable() {
                        public void run() {
                            org.chromium.content.browser.selection.LGEmailActionModeWorkaround$2.this.val$popupWindow.dismiss();
                            org.chromium.content.browser.selection.LGEmailActionModeWorkaround$2.this.val$contentContainer.removeAllViews();
                        }
                    });
                }
            }));
            return;
        }
        catch(InvocationTargetException ) {
            return;
        }
        catch(Exception v10) {
            Log.w("cr_Ime", "Error occurred during LGEmailActionModeWorkaround: ", new Object[]{v10});
            return;
        }
    }

    private static Object getField(Object arg1, String arg2) throws NoSuchFieldException, IllegalAccessException, IllegalArgumentException {
        Field v2 = arg1.getClass().getDeclaredField(arg2);
        v2.setAccessible(true);
        return v2.get(arg1);
    }

    public static void runIfNecessary(Context arg0, ActionMode arg1) {
        if(LGEmailActionModeWorkaround.shouldAllowActionModeDestroyOnNonUiThread(arg0)) {
            LGEmailActionModeWorkaround.allowActionModeDestroyOnNonUiThread(arg1);
        }
    }

    private static void setField(Object arg1, String arg2, Object arg3) throws NoSuchFieldException, IllegalAccessException, IllegalArgumentException {
        Field v2 = arg1.getClass().getDeclaredField(arg2);
        v2.setAccessible(true);
        v2.set(arg1, arg3);
    }

    private static boolean shouldAllowActionModeDestroyOnNonUiThread(Context arg4) {
        String v0 = arg4.getPackageName();
        int v1 = PackageUtils.getPackageVersion(arg4, v0);
        if(v1 == -1) {
            return 0;
        }

        int v4 = arg4.getApplicationInfo().targetSdkVersion;
        if(v4 >= 23) {
            if(v4 > 24) {
            }
            else if(!"com.lge.email".equals(v0)) {
                return 0;
            }
            else if(v1 > 0x4060014) {
                return 0;
            }
            else {
                Log.w("cr_Ime", "Working around action mode LG Email bug in WebView (http://crbug.com/651706). APK name: com.lge.email, versionCode: " + v1, new Object[0]);
                return 1;
            }
        }

        return 0;
    }
}

