package android.support.v4.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View$BaseSavedState;
import android.view.View;
import android.widget.FrameLayout$LayoutParams;
import android.widget.FrameLayout;
import android.widget.LinearLayout$LayoutParams;
import android.widget.LinearLayout;
import android.widget.TabHost$OnTabChangeListener;
import android.widget.TabHost$TabContentFactory;
import android.widget.TabHost$TabSpec;
import android.widget.TabHost;
import android.widget.TabWidget;
import java.util.ArrayList;

public class FragmentTabHost extends TabHost implements TabHost$OnTabChangeListener {
    class DummyTabFactory implements TabHost$TabContentFactory {
        private final Context mContext;

        public DummyTabFactory(Context arg1) {
            super();
            this.mContext = arg1;
        }

        public View createTabContent(String arg2) {
            View v2 = new View(this.mContext);
            v2.setMinimumWidth(0);
            v2.setMinimumHeight(0);
            return v2;
        }
    }

    class SavedState extends View$BaseSavedState {
        final class android.support.v4.app.FragmentTabHost$SavedState$1 implements Parcelable$Creator {
            android.support.v4.app.FragmentTabHost$SavedState$1() {
                super();
            }

            public SavedState createFromParcel(Parcel arg2) {
                return new SavedState(arg2);
            }

            public Object createFromParcel(Parcel arg1) {
                return this.createFromParcel(arg1);
            }

            public SavedState[] newArray(int arg1) {
                return new SavedState[arg1];
            }

            public Object[] newArray(int arg1) {
                return this.newArray(arg1);
            }
        }

        public static final Parcelable$Creator CREATOR;
        String curTab;

        static {
            SavedState.CREATOR = new android.support.v4.app.FragmentTabHost$SavedState$1();
        }

        SavedState(Parcelable arg1) {
            super(arg1);
        }

        SavedState(Parcel arg1) {
            super(arg1);
            this.curTab = arg1.readString();
        }

        public String toString() {
            return "FragmentTabHost.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " curTab=" + this.curTab + "}";
        }

        public void writeToParcel(Parcel arg1, int arg2) {
            super.writeToParcel(arg1, arg2);
            arg1.writeString(this.curTab);
        }
    }

    final class TabInfo {
        @Nullable final Bundle args;
        @NonNull final Class clss;
        Fragment fragment;
        @NonNull final String tag;

        TabInfo(@NonNull String arg1, @NonNull Class arg2, @Nullable Bundle arg3) {
            super();
            this.tag = arg1;
            this.clss = arg2;
            this.args = arg3;
        }
    }

    private boolean mAttached;
    private int mContainerId;
    private Context mContext;
    private FragmentManager mFragmentManager;
    private TabInfo mLastTab;
    private TabHost$OnTabChangeListener mOnTabChangeListener;
    private FrameLayout mRealTabContent;
    private final ArrayList mTabs;

    public FragmentTabHost(Context arg3) {
        super(arg3, null);
        this.mTabs = new ArrayList();
        this.initFragmentTabHost(arg3, null);
    }

    public FragmentTabHost(Context arg2, AttributeSet arg3) {
        super(arg2, arg3);
        this.mTabs = new ArrayList();
        this.initFragmentTabHost(arg2, arg3);
    }

    public void addTab(@NonNull TabHost$TabSpec arg3, @NonNull Class arg4, @Nullable Bundle arg5) {
        arg3.setContent(new DummyTabFactory(this.mContext));
        String v0 = arg3.getTag();
        TabInfo v1 = new TabInfo(v0, arg4, arg5);
        if(this.mAttached) {
            v1.fragment = this.mFragmentManager.findFragmentByTag(v0);
            if(v1.fragment != null && !v1.fragment.isDetached()) {
                FragmentTransaction v4 = this.mFragmentManager.beginTransaction();
                v4.detach(v1.fragment);
                v4.commit();
            }
        }

        this.mTabs.add(v1);
        this.addTab(arg3);
    }

    @Nullable private FragmentTransaction doTabChanged(@Nullable String arg4, @Nullable FragmentTransaction arg5) {
        TabInfo v4 = this.getTabInfoForTag(arg4);
        if(this.mLastTab != v4) {
            if(arg5 == null) {
                arg5 = this.mFragmentManager.beginTransaction();
            }

            if(this.mLastTab != null && this.mLastTab.fragment != null) {
                arg5.detach(this.mLastTab.fragment);
            }

            if(v4 != null) {
                if(v4.fragment == null) {
                    v4.fragment = Fragment.instantiate(this.mContext, v4.clss.getName(), v4.args);
                    arg5.add(this.mContainerId, v4.fragment, v4.tag);
                }
                else {
                    arg5.attach(v4.fragment);
                }
            }

            this.mLastTab = v4;
        }

        return arg5;
    }

    private void ensureContent() {
        if(this.mRealTabContent == null) {
            this.mRealTabContent = this.findViewById(this.mContainerId);
            if(this.mRealTabContent == null) {
                StringBuilder v1 = new StringBuilder();
                v1.append("No tab content FrameLayout found for id ");
                v1.append(this.mContainerId);
                throw new IllegalStateException(v1.toString());
            }
        }
    }

    private void ensureHierarchy(Context arg8) {
        int v0 = 0x1020013;
        if(this.findViewById(v0) == null) {
            LinearLayout v1 = new LinearLayout(arg8);
            v1.setOrientation(1);
            this.addView(((View)v1), new FrameLayout$LayoutParams(-1, -1));
            TabWidget v2 = new TabWidget(arg8);
            v2.setId(v0);
            v2.setOrientation(0);
            v1.addView(((View)v2), new LinearLayout$LayoutParams(-1, -2, 0f));
            FrameLayout v2_1 = new FrameLayout(arg8);
            v2_1.setId(0x1020011);
            v1.addView(((View)v2_1), new LinearLayout$LayoutParams(0, 0, 0f));
            v2_1 = new FrameLayout(arg8);
            this.mRealTabContent = v2_1;
            this.mRealTabContent.setId(this.mContainerId);
            v1.addView(((View)v2_1), new LinearLayout$LayoutParams(-1, 0, 1f));
        }
    }

    @Nullable private TabInfo getTabInfoForTag(String arg5) {
        int v0 = this.mTabs.size();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            Object v2 = this.mTabs.get(v1);
            if(((TabInfo)v2).tag.equals(arg5)) {
                return ((TabInfo)v2);
            }
        }

        return null;
    }

    private void initFragmentTabHost(Context arg4, AttributeSet arg5) {
        TypedArray v4 = arg4.obtainStyledAttributes(arg5, new int[]{0x10100F3}, 0, 0);
        this.mContainerId = v4.getResourceId(0, 0);
        v4.recycle();
        super.setOnTabChangedListener(((TabHost$OnTabChangeListener)this));
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        String v0 = this.getCurrentTabTag();
        int v1 = this.mTabs.size();
        FragmentTransaction v2 = null;
        int v3;
        for(v3 = 0; v3 < v1; ++v3) {
            Object v4 = this.mTabs.get(v3);
            ((TabInfo)v4).fragment = this.mFragmentManager.findFragmentByTag(((TabInfo)v4).tag);
            if(((TabInfo)v4).fragment != null && !((TabInfo)v4).fragment.isDetached()) {
                if(((TabInfo)v4).tag.equals(v0)) {
                    this.mLastTab = ((TabInfo)v4);
                }
                else {
                    if(v2 == null) {
                        v2 = this.mFragmentManager.beginTransaction();
                    }

                    v2.detach(((TabInfo)v4).fragment);
                }
            }
        }

        this.mAttached = true;
        FragmentTransaction v0_1 = this.doTabChanged(v0, v2);
        if(v0_1 != null) {
            v0_1.commit();
            this.mFragmentManager.executePendingTransactions();
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mAttached = false;
    }

    protected void onRestoreInstanceState(Parcelable arg2) {
        if(!(arg2 instanceof SavedState)) {
            super.onRestoreInstanceState(arg2);
            return;
        }

        super.onRestoreInstanceState(((SavedState)arg2).getSuperState());
        this.setCurrentTabByTag(((SavedState)arg2).curTab);
    }

    protected Parcelable onSaveInstanceState() {
        SavedState v1 = new SavedState(super.onSaveInstanceState());
        v1.curTab = this.getCurrentTabTag();
        return ((Parcelable)v1);
    }

    public void onTabChanged(String arg2) {
        if(this.mAttached) {
            FragmentTransaction v0 = this.doTabChanged(arg2, null);
            if(v0 != null) {
                v0.commit();
            }
        }

        if(this.mOnTabChangeListener != null) {
            this.mOnTabChangeListener.onTabChanged(arg2);
        }
    }

    public void setOnTabChangedListener(TabHost$OnTabChangeListener arg1) {
        this.mOnTabChangeListener = arg1;
    }

    @Deprecated public void setup() {
        throw new IllegalStateException("Must call setup() that takes a Context and FragmentManager");
    }

    public void setup(Context arg1, FragmentManager arg2) {
        this.ensureHierarchy(arg1);
        super.setup();
        this.mContext = arg1;
        this.mFragmentManager = arg2;
        this.ensureContent();
    }

    public void setup(Context arg1, FragmentManager arg2, int arg3) {
        this.ensureHierarchy(arg1);
        super.setup();
        this.mContext = arg1;
        this.mFragmentManager = arg2;
        this.mContainerId = arg3;
        this.ensureContent();
        this.mRealTabContent.setId(arg3);
        if(this.getId() == -1) {
            this.setId(0x1020012);
        }
    }
}

