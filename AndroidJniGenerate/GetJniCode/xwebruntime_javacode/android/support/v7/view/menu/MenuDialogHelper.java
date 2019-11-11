package android.support.v7.view.menu;

import android.content.DialogInterface$OnClickListener;
import android.content.DialogInterface$OnDismissListener;
import android.content.DialogInterface$OnKeyListener;
import android.content.DialogInterface;
import android.os.IBinder;
import android.support.v7.app.AlertDialog$Builder;
import android.support.v7.app.AlertDialog;
import android.support.v7.appcompat.R$layout;
import android.view.KeyEvent$DispatcherState;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager$LayoutParams;

class MenuDialogHelper implements DialogInterface$OnClickListener, DialogInterface$OnDismissListener, DialogInterface$OnKeyListener, Callback {
    private AlertDialog mDialog;
    private MenuBuilder mMenu;
    ListMenuPresenter mPresenter;
    private Callback mPresenterCallback;

    public MenuDialogHelper(MenuBuilder arg1) {
        super();
        this.mMenu = arg1;
    }

    public void dismiss() {
        if(this.mDialog != null) {
            this.mDialog.dismiss();
        }
    }

    public void onClick(DialogInterface arg2, int arg3) {
        this.mMenu.performItemAction(this.mPresenter.getAdapter().getItem(arg3), 0);
    }

    public void onCloseMenu(MenuBuilder arg2, boolean arg3) {
        if((arg3) || arg2 == this.mMenu) {
            this.dismiss();
        }

        if(this.mPresenterCallback != null) {
            this.mPresenterCallback.onCloseMenu(arg2, arg3);
        }
    }

    public void onDismiss(DialogInterface arg3) {
        this.mPresenter.onCloseMenu(this.mMenu, true);
    }

    public boolean onKey(DialogInterface arg3, int arg4, KeyEvent arg5) {
        if(arg4 == 82 || arg4 == 4) {
            if(arg5.getAction() == 0 && arg5.getRepeatCount() == 0) {
                Window v3 = this.mDialog.getWindow();
                if(v3 != null) {
                    View v3_1 = v3.getDecorView();
                    if(v3_1 != null) {
                        KeyEvent$DispatcherState v3_2 = v3_1.getKeyDispatcherState();
                        if(v3_2 != null) {
                            v3_2.startTracking(arg5, this);
                            return 1;
                        }
                        else {
                            goto label_35;
                        }
                    }
                    else {
                        goto label_35;
                    }
                }
                else {
                    goto label_35;
                }
            }

            if(arg5.getAction() != 1) {
                goto label_35;
            }

            if(arg5.isCanceled()) {
                goto label_35;
            }

            Window v0 = this.mDialog.getWindow();
            if(v0 == null) {
                goto label_35;
            }

            View v0_1 = v0.getDecorView();
            if(v0_1 == null) {
                goto label_35;
            }

            KeyEvent$DispatcherState v0_2 = v0_1.getKeyDispatcherState();
            if(v0_2 == null) {
                goto label_35;
            }

            if(!v0_2.isTracking(arg5)) {
                goto label_35;
            }

            this.mMenu.close(true);
            arg3.dismiss();
            return 1;
        }

    label_35:
        return this.mMenu.performShortcut(arg4, arg5, 0);
    }

    public boolean onOpenSubMenu(MenuBuilder arg2) {
        if(this.mPresenterCallback != null) {
            return this.mPresenterCallback.onOpenSubMenu(arg2);
        }

        return 0;
    }

    public void setPresenterCallback(Callback arg1) {
        this.mPresenterCallback = arg1;
    }

    public void show(IBinder arg6) {
        MenuBuilder v0 = this.mMenu;
        Builder v1 = new Builder(v0.getContext());
        this.mPresenter = new ListMenuPresenter(v1.getContext(), layout.abc_list_menu_item_layout);
        this.mPresenter.setCallback(((Callback)this));
        this.mMenu.addMenuPresenter(this.mPresenter);
        v1.setAdapter(this.mPresenter.getAdapter(), ((DialogInterface$OnClickListener)this));
        View v2 = v0.getHeaderView();
        if(v2 != null) {
            v1.setCustomTitle(v2);
        }
        else {
            v1.setIcon(v0.getHeaderIcon()).setTitle(v0.getHeaderTitle());
        }

        v1.setOnKeyListener(((DialogInterface$OnKeyListener)this));
        this.mDialog = v1.create();
        this.mDialog.setOnDismissListener(((DialogInterface$OnDismissListener)this));
        WindowManager$LayoutParams v0_1 = this.mDialog.getWindow().getAttributes();
        v0_1.type = 1003;
        if(arg6 != null) {
            v0_1.token = arg6;
        }

        v0_1.flags |= 0x20000;
        this.mDialog.show();
    }
}

