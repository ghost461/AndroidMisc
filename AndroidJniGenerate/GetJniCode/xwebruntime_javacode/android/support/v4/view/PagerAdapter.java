package android.support.v4.view;

import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;

public abstract class PagerAdapter {
    public static final int POSITION_NONE = -2;
    public static final int POSITION_UNCHANGED = -1;
    private final DataSetObservable mObservable;
    private DataSetObserver mViewPagerObserver;

    public PagerAdapter() {
        super();
        this.mObservable = new DataSetObservable();
    }

    @Deprecated public void destroyItem(View arg1, int arg2, Object arg3) {
        throw new UnsupportedOperationException("Required method destroyItem was not overridden");
    }

    public void destroyItem(ViewGroup arg1, int arg2, Object arg3) {
        this.destroyItem(((View)arg1), arg2, arg3);
    }

    @Deprecated public void finishUpdate(View arg1) {
    }

    public void finishUpdate(ViewGroup arg1) {
        this.finishUpdate(((View)arg1));
    }

    public abstract int getCount();

    public int getItemPosition(Object arg1) {
        return -1;
    }

    public CharSequence getPageTitle(int arg1) {
        return null;
    }

    public float getPageWidth(int arg1) {
        return 1f;
    }

    @Deprecated public Object instantiateItem(View arg1, int arg2) {
        throw new UnsupportedOperationException("Required method instantiateItem was not overridden");
    }

    public Object instantiateItem(ViewGroup arg1, int arg2) {
        return this.instantiateItem(((View)arg1), arg2);
    }

    public abstract boolean isViewFromObject(View arg1, Object arg2);

    public void notifyDataSetChanged() {
        __monitor_enter(this);
        try {
            if(this.mViewPagerObserver != null) {
                this.mViewPagerObserver.onChanged();
            }

            __monitor_exit(this);
        }
        catch(Throwable v0) {
            try {
            label_10:
                __monitor_exit(this);
            }
            catch(Throwable v0) {
                goto label_10;
            }

            throw v0;
        }

        this.mObservable.notifyChanged();
    }

    public void registerDataSetObserver(DataSetObserver arg2) {
        this.mObservable.registerObserver(arg2);
    }

    public void restoreState(Parcelable arg1, ClassLoader arg2) {
    }

    public Parcelable saveState() {
        return null;
    }

    @Deprecated public void setPrimaryItem(View arg1, int arg2, Object arg3) {
    }

    public void setPrimaryItem(ViewGroup arg1, int arg2, Object arg3) {
        this.setPrimaryItem(((View)arg1), arg2, arg3);
    }

    void setViewPagerObserver(DataSetObserver arg1) {
        __monitor_enter(this);
        try {
            this.mViewPagerObserver = arg1;
            __monitor_exit(this);
            return;
        label_5:
            __monitor_exit(this);
        }
        catch(Throwable v1) {
            goto label_5;
        }

        throw v1;
    }

    @Deprecated public void startUpdate(View arg1) {
    }

    public void startUpdate(ViewGroup arg1) {
        this.startUpdate(((View)arg1));
    }

    public void unregisterDataSetObserver(DataSetObserver arg2) {
        this.mObservable.unregisterObserver(arg2);
    }
}

