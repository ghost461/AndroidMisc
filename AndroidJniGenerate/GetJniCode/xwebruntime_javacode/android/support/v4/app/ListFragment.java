package android.support.v4.app;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.AdapterView;
import android.widget.FrameLayout$LayoutParams;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ListFragment extends Fragment {
    class android.support.v4.app.ListFragment$1 implements Runnable {
        android.support.v4.app.ListFragment$1(ListFragment arg1) {
            ListFragment.this = arg1;
            super();
        }

        public void run() {
            ListFragment.this.mList.focusableViewAvailable(ListFragment.this.mList);
        }
    }

    class android.support.v4.app.ListFragment$2 implements AdapterView$OnItemClickListener {
        android.support.v4.app.ListFragment$2(ListFragment arg1) {
            ListFragment.this = arg1;
            super();
        }

        public void onItemClick(AdapterView arg7, View arg8, int arg9, long arg10) {
            ListFragment.this.onListItemClick(arg7, arg8, arg9, arg10);
        }
    }

    static final int INTERNAL_EMPTY_ID = 0xFF0001;
    static final int INTERNAL_LIST_CONTAINER_ID = 0xFF0003;
    static final int INTERNAL_PROGRESS_CONTAINER_ID = 0xFF0002;
    ListAdapter mAdapter;
    CharSequence mEmptyText;
    View mEmptyView;
    private final Handler mHandler;
    ListView mList;
    View mListContainer;
    boolean mListShown;
    private final AdapterView$OnItemClickListener mOnClickListener;
    View mProgressContainer;
    private final Runnable mRequestFocus;
    TextView mStandardEmptyView;

    public ListFragment() {
        super();
        this.mHandler = new Handler();
        this.mRequestFocus = new android.support.v4.app.ListFragment$1(this);
        this.mOnClickListener = new android.support.v4.app.ListFragment$2(this);
    }

    private void ensureList() {
        if(this.mList != null) {
            return;
        }

        View v0 = this.getView();
        if(v0 == null) {
            throw new IllegalStateException("Content view not yet created");
        }

        if((v0 instanceof ListView)) {
            this.mList = ((ListView)v0);
        }
        else {
            this.mStandardEmptyView = v0.findViewById(0xFF0001);
            if(this.mStandardEmptyView == null) {
                this.mEmptyView = v0.findViewById(0x1020004);
            }
            else {
                this.mStandardEmptyView.setVisibility(8);
            }

            this.mProgressContainer = v0.findViewById(0xFF0002);
            this.mListContainer = v0.findViewById(0xFF0003);
            v0 = v0.findViewById(0x102000A);
            if(!(v0 instanceof ListView)) {
                if(v0 == null) {
                    throw new RuntimeException("Your content must have a ListView whose id attribute is \'android.R.id.list\'");
                }

                throw new RuntimeException("Content has view with id attribute \'android.R.id.list\' that is not a ListView class");
            }

            this.mList = ((ListView)v0);
            if(this.mEmptyView != null) {
                this.mList.setEmptyView(this.mEmptyView);
                goto label_59;
            }

            if(this.mEmptyText == null) {
                goto label_59;
            }

            this.mStandardEmptyView.setText(this.mEmptyText);
            this.mList.setEmptyView(this.mStandardEmptyView);
        }

    label_59:
        this.mListShown = true;
        this.mList.setOnItemClickListener(this.mOnClickListener);
        if(this.mAdapter != null) {
            ListAdapter v0_1 = this.mAdapter;
            this.mAdapter = null;
            this.setListAdapter(v0_1);
        }
        else if(this.mProgressContainer != null) {
            this.setListShown(false, false);
        }

        this.mHandler.post(this.mRequestFocus);
    }

    public ListAdapter getListAdapter() {
        return this.mAdapter;
    }

    public ListView getListView() {
        this.ensureList();
        return this.mList;
    }

    public long getSelectedItemId() {
        this.ensureList();
        return this.mList.getSelectedItemId();
    }

    public int getSelectedItemPosition() {
        this.ensureList();
        return this.mList.getSelectedItemPosition();
    }

    public View onCreateView(LayoutInflater arg5, ViewGroup arg6, Bundle arg7) {
        Context v5 = this.getContext();
        FrameLayout v6 = new FrameLayout(v5);
        LinearLayout v7 = new LinearLayout(v5);
        v7.setId(0xFF0002);
        v7.setOrientation(1);
        v7.setVisibility(8);
        v7.setGravity(17);
        v7.addView(new ProgressBar(v5, null, 0x101007A), new FrameLayout$LayoutParams(-2, -2));
        v6.addView(((View)v7), new FrameLayout$LayoutParams(-1, -1));
        FrameLayout v7_1 = new FrameLayout(v5);
        v7_1.setId(0xFF0003);
        TextView v1 = new TextView(v5);
        v1.setId(0xFF0001);
        v1.setGravity(17);
        v7_1.addView(((View)v1), new FrameLayout$LayoutParams(-1, -1));
        ListView v0 = new ListView(v5);
        v0.setId(0x102000A);
        v0.setDrawSelectorOnTop(false);
        v7_1.addView(((View)v0), new FrameLayout$LayoutParams(-1, -1));
        v6.addView(((View)v7_1), new FrameLayout$LayoutParams(-1, -1));
        v6.setLayoutParams(new FrameLayout$LayoutParams(-1, -1));
        return ((View)v6);
    }

    public void onDestroyView() {
        this.mHandler.removeCallbacks(this.mRequestFocus);
        this.mList = null;
        this.mListShown = false;
        this.mListContainer = null;
        this.mProgressContainer = null;
        this.mEmptyView = null;
        this.mStandardEmptyView = null;
        super.onDestroyView();
    }

    public void onListItemClick(ListView arg1, View arg2, int arg3, long arg4) {
    }

    public void onViewCreated(View arg1, Bundle arg2) {
        super.onViewCreated(arg1, arg2);
        this.ensureList();
    }

    public void setEmptyText(CharSequence arg3) {
        this.ensureList();
        if(this.mStandardEmptyView == null) {
            throw new IllegalStateException("Can\'t be used with a custom content view");
        }

        this.mStandardEmptyView.setText(arg3);
        if(this.mEmptyText == null) {
            this.mList.setEmptyView(this.mStandardEmptyView);
        }

        this.mEmptyText = arg3;
    }

    public void setListAdapter(ListAdapter arg5) {
        boolean v1 = false;
        int v0 = this.mAdapter != null ? 1 : 0;
        this.mAdapter = arg5;
        if(this.mList != null) {
            this.mList.setAdapter(arg5);
            if(!this.mListShown && v0 == 0) {
                if(this.getView().getWindowToken() != null) {
                    v1 = true;
                }

                this.setListShown(true, v1);
            }
        }
    }

    private void setListShown(boolean arg5, boolean arg6) {
        this.ensureList();
        if(this.mProgressContainer == null) {
            throw new IllegalStateException("Can\'t be used with a custom content view");
        }

        if(this.mListShown == arg5) {
            return;
        }

        this.mListShown = arg5;
        int v1 = 8;
        int v2 = 0x10A0000;
        int v3 = 0x10A0001;
        if(arg5) {
            if(arg6) {
                this.mProgressContainer.startAnimation(AnimationUtils.loadAnimation(this.getContext(), v3));
                this.mListContainer.startAnimation(AnimationUtils.loadAnimation(this.getContext(), v2));
            }
            else {
                this.mProgressContainer.clearAnimation();
                this.mListContainer.clearAnimation();
            }

            this.mProgressContainer.setVisibility(v1);
            this.mListContainer.setVisibility(0);
        }
        else {
            if(arg6) {
                this.mProgressContainer.startAnimation(AnimationUtils.loadAnimation(this.getContext(), v2));
                this.mListContainer.startAnimation(AnimationUtils.loadAnimation(this.getContext(), v3));
            }
            else {
                this.mProgressContainer.clearAnimation();
                this.mListContainer.clearAnimation();
            }

            this.mProgressContainer.setVisibility(0);
            this.mListContainer.setVisibility(v1);
        }
    }

    public void setListShown(boolean arg2) {
        this.setListShown(arg2, true);
    }

    public void setListShownNoAnimation(boolean arg2) {
        this.setListShown(arg2, false);
    }

    public void setSelection(int arg2) {
        this.ensureList();
        this.mList.setSelection(arg2);
    }
}

