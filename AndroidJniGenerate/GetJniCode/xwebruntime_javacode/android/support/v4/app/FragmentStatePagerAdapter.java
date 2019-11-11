package android.support.v4.app;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class FragmentStatePagerAdapter extends PagerAdapter {
    private static final boolean DEBUG = false;
    private static final String TAG = "FragmentStatePagerAdapt";
    private FragmentTransaction mCurTransaction;
    private Fragment mCurrentPrimaryItem;
    private final FragmentManager mFragmentManager;
    private ArrayList mFragments;
    private ArrayList mSavedState;

    public FragmentStatePagerAdapter(FragmentManager arg3) {
        super();
        this.mCurTransaction = null;
        this.mSavedState = new ArrayList();
        this.mFragments = new ArrayList();
        this.mCurrentPrimaryItem = null;
        this.mFragmentManager = arg3;
    }

    public void destroyItem(ViewGroup arg3, int arg4, Object arg5) {
        Object v1_1;
        Object v0;
        if(this.mCurTransaction == null) {
            this.mCurTransaction = this.mFragmentManager.beginTransaction();
        }

        while(true) {
            v0 = null;
            if(this.mSavedState.size() > arg4) {
                break;
            }

            this.mSavedState.add(v0);
        }

        ArrayList v3 = this.mSavedState;
        if(((Fragment)arg5).isAdded()) {
            SavedState v1 = this.mFragmentManager.saveFragmentInstanceState(((Fragment)arg5));
        }
        else {
            v1_1 = v0;
        }

        v3.set(arg4, v1_1);
        this.mFragments.set(arg4, v0);
        this.mCurTransaction.remove(((Fragment)arg5));
    }

    public void finishUpdate(ViewGroup arg1) {
        if(this.mCurTransaction != null) {
            this.mCurTransaction.commitNowAllowingStateLoss();
            this.mCurTransaction = null;
        }
    }

    public abstract Fragment getItem(int arg1);

    public Object instantiateItem(ViewGroup arg4, int arg5) {
        if(this.mFragments.size() > arg5) {
            Object v0 = this.mFragments.get(arg5);
            if(v0 != null) {
                return v0;
            }
        }

        if(this.mCurTransaction == null) {
            this.mCurTransaction = this.mFragmentManager.beginTransaction();
        }

        Fragment v0_1 = this.getItem(arg5);
        if(this.mSavedState.size() > arg5) {
            Object v1 = this.mSavedState.get(arg5);
            if(v1 != null) {
                v0_1.setInitialSavedState(((SavedState)v1));
            }
        }

        while(this.mFragments.size() <= arg5) {
            this.mFragments.add(null);
        }

        v0_1.setMenuVisibility(false);
        v0_1.setUserVisibleHint(false);
        this.mFragments.set(arg5, v0_1);
        this.mCurTransaction.add(arg4.getId(), v0_1);
        return v0_1;
    }

    public boolean isViewFromObject(View arg1, Object arg2) {
        boolean v1 = ((Fragment)arg2).getView() == arg1 ? true : false;
        return v1;
    }

    public void restoreState(Parcelable arg6, ClassLoader arg7) {
        Fragment v3;
        int v2;
        if(arg6 != null) {
            ((Bundle)arg6).setClassLoader(arg7);
            Parcelable[] v7 = ((Bundle)arg6).getParcelableArray("states");
            this.mSavedState.clear();
            this.mFragments.clear();
            if(v7 != null) {
                int v1;
                for(v1 = 0; v1 < v7.length; ++v1) {
                    this.mSavedState.add(v7[v1]);
                }
            }

            Iterator v7_1 = ((Bundle)arg6).keySet().iterator();
            while(true) {
            label_20:
                if(!v7_1.hasNext()) {
                    return;
                }

                Object v1_1 = v7_1.next();
                if(!((String)v1_1).startsWith("f")) {
                    continue;
                }

                v2 = Integer.parseInt(((String)v1_1).substring(1));
                v3 = this.mFragmentManager.getFragment(((Bundle)arg6), ((String)v1_1));
                if(v3 == null) {
                    Log.w("FragmentStatePagerAdapt", "Bad fragment at key " + (((String)v1_1)));
                    continue;
                }

                break;
            }

            while(this.mFragments.size() <= v2) {
                this.mFragments.add(null);
            }

            v3.setMenuVisibility(false);
            this.mFragments.set(v2, v3);
            goto label_20;
        }
    }

    public Parcelable saveState() {
        Bundle v0;
        if(this.mSavedState.size() > 0) {
            v0 = new Bundle();
            SavedState[] v1 = new SavedState[this.mSavedState.size()];
            this.mSavedState.toArray(((Object[])v1));
            v0.putParcelableArray("states", ((Parcelable[])v1));
        }
        else {
            v0 = null;
        }

        int v1_1;
        for(v1_1 = 0; v1_1 < this.mFragments.size(); ++v1_1) {
            Object v2 = this.mFragments.get(v1_1);
            if(v2 != null && (((Fragment)v2).isAdded())) {
                if(v0 == null) {
                    v0 = new Bundle();
                }

                this.mFragmentManager.putFragment(v0, "f" + v1_1, ((Fragment)v2));
            }
        }

        return ((Parcelable)v0);
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

