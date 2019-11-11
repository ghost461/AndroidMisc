package android.support.v7.widget;

import android.content.Context;
import android.content.res.Resources$Theme;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.widget.SpinnerAdapter;

public interface ThemedSpinnerAdapter extends SpinnerAdapter {
    public final class Helper {
        private final Context mContext;
        private LayoutInflater mDropDownInflater;
        private final LayoutInflater mInflater;

        public Helper(@NonNull Context arg1) {
            super();
            this.mContext = arg1;
            this.mInflater = LayoutInflater.from(arg1);
        }

        @NonNull public LayoutInflater getDropDownViewInflater() {
            LayoutInflater v0 = this.mDropDownInflater != null ? this.mDropDownInflater : this.mInflater;
            return v0;
        }

        @Nullable public Resources$Theme getDropDownViewTheme() {
            Resources$Theme v0 = this.mDropDownInflater == null ? null : this.mDropDownInflater.getContext().getTheme();
            return v0;
        }

        public void setDropDownViewTheme(@Nullable Resources$Theme arg3) {
            if(arg3 == null) {
                this.mDropDownInflater = null;
            }
            else if(arg3 == this.mContext.getTheme()) {
                this.mDropDownInflater = this.mInflater;
            }
            else {
                this.mDropDownInflater = LayoutInflater.from(new ContextThemeWrapper(this.mContext, arg3));
            }
        }
    }

    @Nullable Resources$Theme getDropDownViewTheme();

    void setDropDownViewTheme(@Nullable Resources$Theme arg1);
}

