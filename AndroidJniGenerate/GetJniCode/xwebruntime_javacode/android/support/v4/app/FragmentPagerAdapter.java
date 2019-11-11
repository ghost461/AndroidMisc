package android.support.v4.app;

import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public abstract class FragmentPagerAdapter extends PagerAdapter {
    private static final boolean DEBUG = false;
    private static final String TAG = "FragmentPagerAdapter";
    private FragmentTransaction mCurTransaction;
    private Fragment mCurrentPrimaryItem;
    private final FragmentManager mFragmentManager;

    public FragmentPagerAdapter(FragmentManager arg2) {
        super();
        this.mCurTransaction = null;
        this.mCurrentPrimaryItem = null;
        this.mFragmentManager = arg2;
    }

    public void destroyItem(ViewGroup arg1, int arg2, Object arg3) {
        if(this.mCurTransaction == null) {
            this.mCurTransaction = this.mFragmentManager.beginTransaction();
        }

        this.mCurTransaction.detach(((Fragment)arg3));
    }

    public void finishUpdate(ViewGroup arg1) {
        if(this.mCurTransaction != null) {
            this.mCurTransaction.commitNowAllowingStateLoss();
            this.mCurTransaction = null;
        }
    }

    public abstract Fragment getItem(int arg1);

    public long getItemId(int arg3) {
        return ((long)arg3);
    }

    public Object instantiateItem(ViewGroup arg5, int arg6) {
        if(this.mCurTransaction == null) {
            this.mCurTransaction = this.mFragmentManager.beginTransaction();
        }

        long v0 = this.getItemId(arg6);
        Fragment v2 = this.mFragmentManager.findFragmentByTag(FragmentPagerAdapter.makeFragmentName(arg5.getId(), v0));
        if(v2 != null) {
            this.mCurTransaction.attach(v2);
        }
        else {
            v2 = this.getItem(arg6);
            this.mCurTransaction.add(arg5.getId(), v2, FragmentPagerAdapter.makeFragmentName(arg5.getId(), v0));
        }

        if(v2 != this.mCurrentPrimaryItem) {
            v2.setMenuVisibility(false);
            v2.setUserVisibleHint(false);
        }

        return v2;
    }

    public boolean isViewFromObject(View arg1, Object arg2) {
        boolean v1 = ((Fragment)arg2).getView() == arg1 ? true : false;
        return v1;
    }

    private static String makeFragmentName(int arg2, long arg3) {
        return "android:switcher:" + arg2 + ":" + arg3;
    }

    public void restoreState(Parcelable arg1, ClassLoader arg2) {
    }

    public Parcelable saveState() {
        return null;
    }

    public void setPrimaryItem(ViewGroup arg1, int arg2, Object arg3) {
        if(arg3 != this.mCurrentPrimaryItem) {
            if(this.mCurrentPrimaryItem != null) {
                this.mCurrentPrimaryItem.setMenuVisibility(false);
                this.mCurrentPrimaryItem.setUserVisibleHint(false);
            }

            if(arg3 != null) {
                ((Fragment)arg3).setMenuVisibility(true);
                ((Fragment)arg3).setUserVisibleHint(true);
            }

            this.mCurrentPrimaryItem = ((Fragment)arg3);
        }
    }

    public void startUpdate(ViewGroup arg3) {
        if(arg3.getId() == -1) {
            StringBuilder v0 = new StringBuilder();
            v0.append("ViewPager with adapter ");
            v0.append(this);
            v0.append(" requires a view id");
            throw new IllegalStateException(v0.toString());
        }
    }
}

