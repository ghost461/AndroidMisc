package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build$VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.v7.view.menu.ListMenuItemView;
import android.support.v7.view.menu.MenuAdapter;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuItemImpl;
import android.transition.Transition;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import java.lang.reflect.Method;

@RestrictTo(value={Scope.LIBRARY_GROUP}) public class MenuPopupWindow extends ListPopupWindow implements MenuItemHoverListener {
    @RestrictTo(value={Scope.LIBRARY_GROUP}) public class MenuDropDownListView extends DropDownListView {
        final int mAdvanceKey;
        private MenuItemHoverListener mHoverListener;
        private MenuItem mHoveredMenuItem;
        final int mRetreatKey;

        public MenuDropDownListView(Context arg4, boolean arg5) {
            super(arg4, arg5);
            Configuration v4 = arg4.getResources().getConfiguration();
            int v0 = 22;
            int v1 = 21;
            if(Build$VERSION.SDK_INT < 17 || 1 != v4.getLayoutDirection()) {
                this.mAdvanceKey = v0;
                this.mRetreatKey = v1;
            }
            else {
                this.mAdvanceKey = v1;
                this.mRetreatKey = v0;
            }
        }

        public void clearSelection() {
            this.setSelection(-1);
        }

        public boolean hasFocus() {
            return super.hasFocus();
        }

        public boolean hasWindowFocus() {
            return super.hasWindowFocus();
        }

        public boolean isFocused() {
            return super.isFocused();
        }

        public boolean isInTouchMode() {
            return super.isInTouchMode();
        }

        public boolean onForwardedEvent(MotionEvent arg1, int arg2) {
            return super.onForwardedEvent(arg1, arg2);
        }

        public boolean onHoverEvent(MotionEvent arg6) {
            int v1;
            if(this.mHoverListener != null) {
                ListAdapter v0 = this.getAdapter();
                if((v0 instanceof HeaderViewListAdapter)) {
                    v1 = ((HeaderViewListAdapter)v0).getHeadersCount();
                    v0 = ((HeaderViewListAdapter)v0).getWrappedAdapter();
                }
                else {
                    v1 = 0;
                }

                MenuItemImpl v2 = null;
                if(arg6.getAction() != 10) {
                    int v3 = this.pointToPosition(((int)arg6.getX()), ((int)arg6.getY()));
                    if(v3 != -1) {
                        v3 -= v1;
                        if(v3 >= 0 && v3 < ((MenuAdapter)v0).getCount()) {
                            v2 = ((MenuAdapter)v0).getItem(v3);
                        }
                    }
                }

                MenuItem v1_1 = this.mHoveredMenuItem;
                if((((MenuItemImpl)v1_1)) == v2) {
                    goto label_35;
                }

                MenuBuilder v0_1 = ((MenuAdapter)v0).getAdapterMenu();
                if(v1_1 != null) {
                    this.mHoverListener.onItemHoverExit(v0_1, v1_1);
                }

                this.mHoveredMenuItem = ((MenuItem)v2);
                if(v2 == null) {
                    goto label_35;
                }

                this.mHoverListener.onItemHoverEnter(v0_1, ((MenuItem)v2));
            }

        label_35:
            return super.onHoverEvent(arg6);
        }

        public boolean onKeyDown(int arg5, KeyEvent arg6) {
            View v0 = this.getSelectedView();
            if(v0 != null && arg5 == this.mAdvanceKey) {
                if((((ListMenuItemView)v0).isEnabled()) && (((ListMenuItemView)v0).getItemData().hasSubMenu())) {
                    this.performItemClick(v0, this.getSelectedItemPosition(), this.getSelectedItemId());
                }

                return 1;
            }

            if(v0 != null && arg5 == this.mRetreatKey) {
                this.setSelection(-1);
                this.getAdapter().getAdapterMenu().close(false);
                return 1;
            }

            return super.onKeyDown(arg5, arg6);
        }

        public void setHoverListener(MenuItemHoverListener arg1) {
            this.mHoverListener = arg1;
        }
    }

    private static final String TAG = "MenuPopupWindow";
    private MenuItemHoverListener mHoverListener;
    private static Method sSetTouchModalMethod;

    static {
        try {
            MenuPopupWindow.sSetTouchModalMethod = PopupWindow.class.getDeclaredMethod("setTouchModal", Boolean.TYPE);
        }
        catch(NoSuchMethodException ) {
            Log.i("MenuPopupWindow", "Could not find method setTouchModal() on PopupWindow. Oh well.");
        }
    }

    public MenuPopupWindow(Context arg1, AttributeSet arg2, int arg3, int arg4) {
        super(arg1, arg2, arg3, arg4);
    }

    DropDownListView createDropDownListView(Context arg2, boolean arg3) {
        MenuDropDownListView v0 = new MenuDropDownListView(arg2, arg3);
        v0.setHoverListener(((MenuItemHoverListener)this));
        return ((DropDownListView)v0);
    }

    public void onItemHoverEnter(@NonNull MenuBuilder arg2, @NonNull MenuItem arg3) {
        if(this.mHoverListener != null) {
            this.mHoverListener.onItemHoverEnter(arg2, arg3);
        }
    }

    public void onItemHoverExit(@NonNull MenuBuilder arg2, @NonNull MenuItem arg3) {
        if(this.mHoverListener != null) {
            this.mHoverListener.onItemHoverExit(arg2, arg3);
        }
    }

    public void setEnterTransition(Object arg3) {
        if(Build$VERSION.SDK_INT >= 23) {
            this.mPopup.setEnterTransition(((Transition)arg3));
        }
    }

    public void setExitTransition(Object arg3) {
        if(Build$VERSION.SDK_INT >= 23) {
            this.mPopup.setExitTransition(((Transition)arg3));
        }
    }

    public void setHoverListener(MenuItemHoverListener arg1) {
        this.mHoverListener = arg1;
    }

    public void setTouchModal(boolean arg5) {
        if(MenuPopupWindow.sSetTouchModalMethod != null) {
            try {
                MenuPopupWindow.sSetTouchModalMethod.invoke(this.mPopup, Boolean.valueOf(arg5));
            }
            catch(Exception ) {
                Log.i("MenuPopupWindow", "Could not invoke setTouchModal() on PopupWindow. Oh well.");
            }
        }
    }
}

