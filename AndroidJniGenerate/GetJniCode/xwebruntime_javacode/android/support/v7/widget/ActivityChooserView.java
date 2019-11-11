package android.support.v7.widget;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.v4.view.ActionProvider;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v7.appcompat.R$dimen;
import android.support.v7.appcompat.R$id;
import android.support.v7.appcompat.R$layout;
import android.support.v7.appcompat.R$string;
import android.support.v7.appcompat.R$styleable;
import android.support.v7.view.menu.ShowableListMenu;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View$AccessibilityDelegate;
import android.view.View$MeasureSpec;
import android.view.View$OnClickListener;
import android.view.View$OnLongClickListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow$OnDismissListener;

@RestrictTo(value={Scope.LIBRARY_GROUP}) public class ActivityChooserView extends ViewGroup implements ActivityChooserModelClient {
    class android.support.v7.widget.ActivityChooserView$1 extends DataSetObserver {
        android.support.v7.widget.ActivityChooserView$1(ActivityChooserView arg1) {
            ActivityChooserView.this = arg1;
            super();
        }

        public void onChanged() {
            super.onChanged();
            ActivityChooserView.this.mAdapter.notifyDataSetChanged();
        }

        public void onInvalidated() {
            super.onInvalidated();
            ActivityChooserView.this.mAdapter.notifyDataSetInvalidated();
        }
    }

    class android.support.v7.widget.ActivityChooserView$2 implements ViewTreeObserver$OnGlobalLayoutListener {
        android.support.v7.widget.ActivityChooserView$2(ActivityChooserView arg1) {
            ActivityChooserView.this = arg1;
            super();
        }

        public void onGlobalLayout() {
            if(ActivityChooserView.this.isShowingPopup()) {
                if(!ActivityChooserView.this.isShown()) {
                    ActivityChooserView.this.getListPopupWindow().dismiss();
                }
                else {
                    ActivityChooserView.this.getListPopupWindow().show();
                    if(ActivityChooserView.this.mProvider != null) {
                        ActivityChooserView.this.mProvider.subUiVisibilityChanged(true);
                    }
                }
            }
        }
    }

    class ActivityChooserViewAdapter extends BaseAdapter {
        private static final int ITEM_VIEW_TYPE_ACTIVITY = 0;
        private static final int ITEM_VIEW_TYPE_COUNT = 3;
        private static final int ITEM_VIEW_TYPE_FOOTER = 1;
        public static final int MAX_ACTIVITY_COUNT_DEFAULT = 4;
        public static final int MAX_ACTIVITY_COUNT_UNLIMITED = 0x7FFFFFFF;
        private ActivityChooserModel mDataModel;
        private boolean mHighlightDefaultActivity;
        private int mMaxActivityCount;
        private boolean mShowDefaultActivity;
        private boolean mShowFooterView;

        ActivityChooserViewAdapter(ActivityChooserView arg1) {
            ActivityChooserView.this = arg1;
            super();
            this.mMaxActivityCount = 4;
        }

        public int getActivityCount() {
            return this.mDataModel.getActivityCount();
        }

        public int getCount() {
            int v0 = this.mDataModel.getActivityCount();
            if(!this.mShowDefaultActivity && this.mDataModel.getDefaultActivity() != null) {
                --v0;
            }

            v0 = Math.min(v0, this.mMaxActivityCount);
            if(this.mShowFooterView) {
                ++v0;
            }

            return v0;
        }

        public ActivityChooserModel getDataModel() {
            return this.mDataModel;
        }

        public ResolveInfo getDefaultActivity() {
            return this.mDataModel.getDefaultActivity();
        }

        public int getHistorySize() {
            return this.mDataModel.getHistorySize();
        }

        public Object getItem(int arg2) {
            switch(this.getItemViewType(arg2)) {
                case 0: {
                    goto label_7;
                }
                case 1: {
                    return null;
                }
            }

            throw new IllegalArgumentException();
            return null;
        label_7:
            if(!this.mShowDefaultActivity && this.mDataModel.getDefaultActivity() != null) {
                ++arg2;
            }

            return this.mDataModel.getActivity(arg2);
        }

        public long getItemId(int arg3) {
            return ((long)arg3);
        }

        public int getItemViewType(int arg3) {
            if((this.mShowFooterView) && arg3 == this.getCount() - 1) {
                return 1;
            }

            return 0;
        }

        public boolean getShowDefaultActivity() {
            return this.mShowDefaultActivity;
        }

        public View getView(int arg6, View arg7, ViewGroup arg8) {
            switch(this.getItemViewType(arg6)) {
                case 0: {
                    goto label_24;
                }
                case 1: {
                    goto label_7;
                }
            }

            throw new IllegalArgumentException();
        label_7:
            if(arg7 == null || arg7.getId() != 1) {
                arg7 = LayoutInflater.from(ActivityChooserView.this.getContext()).inflate(layout.abc_activity_chooser_view_list_item, arg8, false);
                arg7.setId(1);
                arg7.findViewById(id.title).setText(ActivityChooserView.this.getContext().getString(string.abc_activity_chooser_view_see_all));
            }

            return arg7;
        label_24:
            if(arg7 == null || arg7.getId() != id.list_item) {
                arg7 = LayoutInflater.from(ActivityChooserView.this.getContext()).inflate(layout.abc_activity_chooser_view_list_item, arg8, false);
            }

            PackageManager v8 = ActivityChooserView.this.getContext().getPackageManager();
            View v0 = arg7.findViewById(id.icon);
            Object v3 = this.getItem(arg6);
            ((ImageView)v0).setImageDrawable(((ResolveInfo)v3).loadIcon(v8));
            arg7.findViewById(id.title).setText(((ResolveInfo)v3).loadLabel(v8));
            if(!this.mShowDefaultActivity || arg6 != 0 || !this.mHighlightDefaultActivity) {
                arg7.setActivated(false);
            }
            else {
                arg7.setActivated(true);
            }

            return arg7;
        }

        public int getViewTypeCount() {
            return 3;
        }

        public int measureContentWidth() {
            int v0 = this.mMaxActivityCount;
            this.mMaxActivityCount = 0x7FFFFFFF;
            int v1 = 0;
            int v2 = View$MeasureSpec.makeMeasureSpec(0, 0);
            int v3 = View$MeasureSpec.makeMeasureSpec(0, 0);
            int v4 = this.getCount();
            ViewGroup v5 = null;
            View v6 = ((View)v5);
            int v7 = 0;
            while(v1 < v4) {
                v6 = this.getView(v1, v6, v5);
                v6.measure(v2, v3);
                v7 = Math.max(v7, v6.getMeasuredWidth());
                ++v1;
            }

            this.mMaxActivityCount = v0;
            return v7;
        }

        public void setDataModel(ActivityChooserModel arg3) {
            ActivityChooserModel v0 = ActivityChooserView.this.mAdapter.getDataModel();
            if(v0 != null && (ActivityChooserView.this.isShown())) {
                v0.unregisterObserver(ActivityChooserView.this.mModelDataSetObserver);
            }

            this.mDataModel = arg3;
            if(arg3 != null && (ActivityChooserView.this.isShown())) {
                arg3.registerObserver(ActivityChooserView.this.mModelDataSetObserver);
            }

            this.notifyDataSetChanged();
        }

        public void setMaxActivityCount(int arg2) {
            if(this.mMaxActivityCount != arg2) {
                this.mMaxActivityCount = arg2;
                this.notifyDataSetChanged();
            }
        }

        public void setShowDefaultActivity(boolean arg2, boolean arg3) {
            if(this.mShowDefaultActivity != arg2 || this.mHighlightDefaultActivity != arg3) {
                this.mShowDefaultActivity = arg2;
                this.mHighlightDefaultActivity = arg3;
                this.notifyDataSetChanged();
            }
        }

        public void setShowFooterView(boolean arg2) {
            if(this.mShowFooterView != arg2) {
                this.mShowFooterView = arg2;
                this.notifyDataSetChanged();
            }
        }
    }

    class Callbacks implements View$OnClickListener, View$OnLongClickListener, AdapterView$OnItemClickListener, PopupWindow$OnDismissListener {
        Callbacks(ActivityChooserView arg1) {
            ActivityChooserView.this = arg1;
            super();
        }

        private void notifyOnDismissListener() {
            if(ActivityChooserView.this.mOnDismissListener != null) {
                ActivityChooserView.this.mOnDismissListener.onDismiss();
            }
        }

        public void onClick(View arg2) {
            if(arg2 == ActivityChooserView.this.mDefaultActivityButton) {
                ActivityChooserView.this.dismissPopup();
                Intent v2 = ActivityChooserView.this.mAdapter.getDataModel().chooseActivity(ActivityChooserView.this.mAdapter.getDataModel().getActivityIndex(ActivityChooserView.this.mAdapter.getDefaultActivity()));
                if(v2 != null) {
                    v2.addFlags(0x80000);
                    ActivityChooserView.this.getContext().startActivity(v2);
                }
            }
            else if(arg2 == ActivityChooserView.this.mExpandActivityOverflowButton) {
                ActivityChooserView.this.mIsSelectingDefaultActivity = false;
                ActivityChooserView.this.showPopupUnchecked(ActivityChooserView.this.mInitialActivityCount);
            }
            else {
                goto label_34;
            }

            return;
        label_34:
            throw new IllegalArgumentException();
        }

        public void onDismiss() {
            this.notifyOnDismissListener();
            if(ActivityChooserView.this.mProvider != null) {
                ActivityChooserView.this.mProvider.subUiVisibilityChanged(false);
            }
        }

        public void onItemClick(AdapterView arg1, View arg2, int arg3, long arg4) {
            switch(arg1.getAdapter().getItemViewType(arg3)) {
                case 0: {
                    ActivityChooserView.this.dismissPopup();
                    if(ActivityChooserView.this.mIsSelectingDefaultActivity) {
                        if(arg3 <= 0) {
                            return;
                        }

                        ActivityChooserView.this.mAdapter.getDataModel().setDefaultActivity(arg3);
                        return;
                    }

                    if(ActivityChooserView.this.mAdapter.getShowDefaultActivity()) {
                    }
                    else {
                        ++arg3;
                    }

                    Intent v1 = ActivityChooserView.this.mAdapter.getDataModel().chooseActivity(arg3);
                    if(v1 == null) {
                        return;
                    }

                    v1.addFlags(0x80000);
                    ActivityChooserView.this.getContext().startActivity(v1);
                    break;
                }
                case 1: {
                    ActivityChooserView.this.showPopupUnchecked(0x7FFFFFFF);
                    break;
                }
                default: {
                    throw new IllegalArgumentException();
                }
            }
        }

        public boolean onLongClick(View arg3) {
            if(arg3 == ActivityChooserView.this.mDefaultActivityButton) {
                if(ActivityChooserView.this.mAdapter.getCount() > 0) {
                    ActivityChooserView.this.mIsSelectingDefaultActivity = true;
                    ActivityChooserView.this.showPopupUnchecked(ActivityChooserView.this.mInitialActivityCount);
                }

                return 1;
            }

            throw new IllegalArgumentException();
        }
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public class InnerLayout extends LinearLayoutCompat {
        private static final int[] TINT_ATTRS;

        static {
            InnerLayout.TINT_ATTRS = new int[]{0x10100D4};
        }

        public InnerLayout(Context arg2, AttributeSet arg3) {
            super(arg2, arg3);
            TintTypedArray v2 = TintTypedArray.obtainStyledAttributes(arg2, arg3, InnerLayout.TINT_ATTRS);
            this.setBackgroundDrawable(v2.getDrawable(0));
            v2.recycle();
        }
    }

    private static final String LOG_TAG = "ActivityChooserView";
    private final LinearLayoutCompat mActivityChooserContent;
    private final Drawable mActivityChooserContentBackground;
    final ActivityChooserViewAdapter mAdapter;
    private final Callbacks mCallbacks;
    private int mDefaultActionButtonContentDescription;
    final FrameLayout mDefaultActivityButton;
    private final ImageView mDefaultActivityButtonImage;
    final FrameLayout mExpandActivityOverflowButton;
    private final ImageView mExpandActivityOverflowButtonImage;
    int mInitialActivityCount;
    private boolean mIsAttachedToWindow;
    boolean mIsSelectingDefaultActivity;
    private final int mListPopupMaxWidth;
    private ListPopupWindow mListPopupWindow;
    final DataSetObserver mModelDataSetObserver;
    PopupWindow$OnDismissListener mOnDismissListener;
    private final ViewTreeObserver$OnGlobalLayoutListener mOnGlobalLayoutListener;
    ActionProvider mProvider;

    public ActivityChooserView(Context arg2) {
        this(arg2, null);
    }

    public ActivityChooserView(Context arg2, AttributeSet arg3) {
        this(arg2, arg3, 0);
    }

    public ActivityChooserView(Context arg4, AttributeSet arg5, int arg6) {
        super(arg4, arg5, arg6);
        this.mModelDataSetObserver = new android.support.v7.widget.ActivityChooserView$1(this);
        this.mOnGlobalLayoutListener = new android.support.v7.widget.ActivityChooserView$2(this);
        this.mInitialActivityCount = 4;
        TypedArray v5 = arg4.obtainStyledAttributes(arg5, styleable.ActivityChooserView, arg6, 0);
        this.mInitialActivityCount = v5.getInt(styleable.ActivityChooserView_initialActivityCount, 4);
        Drawable v6 = v5.getDrawable(styleable.ActivityChooserView_expandActivityOverflowButtonDrawable);
        v5.recycle();
        LayoutInflater.from(this.getContext()).inflate(layout.abc_activity_chooser_view, ((ViewGroup)this), true);
        this.mCallbacks = new Callbacks(this);
        this.mActivityChooserContent = this.findViewById(id.activity_chooser_view_content);
        this.mActivityChooserContentBackground = this.mActivityChooserContent.getBackground();
        this.mDefaultActivityButton = this.findViewById(id.default_activity_button);
        this.mDefaultActivityButton.setOnClickListener(this.mCallbacks);
        this.mDefaultActivityButton.setOnLongClickListener(this.mCallbacks);
        this.mDefaultActivityButtonImage = this.mDefaultActivityButton.findViewById(id.image);
        View v5_1 = this.findViewById(id.expand_activities_button);
        ((FrameLayout)v5_1).setOnClickListener(this.mCallbacks);
        ((FrameLayout)v5_1).setAccessibilityDelegate(new View$AccessibilityDelegate() {
            public void onInitializeAccessibilityNodeInfo(View arg1, AccessibilityNodeInfo arg2) {
                super.onInitializeAccessibilityNodeInfo(arg1, arg2);
                AccessibilityNodeInfoCompat.wrap(arg2).setCanOpenPopup(true);
            }
        });
        ((FrameLayout)v5_1).setOnTouchListener(new ForwardingListener(v5_1) {
            public ShowableListMenu getPopup() {
                return ActivityChooserView.this.getListPopupWindow();
            }

            protected boolean onForwardingStarted() {
                ActivityChooserView.this.showPopup();
                return 1;
            }

            protected boolean onForwardingStopped() {
                ActivityChooserView.this.dismissPopup();
                return 1;
            }
        });
        this.mExpandActivityOverflowButton = ((FrameLayout)v5_1);
        this.mExpandActivityOverflowButtonImage = ((FrameLayout)v5_1).findViewById(id.image);
        this.mExpandActivityOverflowButtonImage.setImageDrawable(v6);
        this.mAdapter = new ActivityChooserViewAdapter(this);
        this.mAdapter.registerDataSetObserver(new DataSetObserver() {
            public void onChanged() {
                super.onChanged();
                ActivityChooserView.this.updateAppearance();
            }
        });
        Resources v4 = arg4.getResources();
        this.mListPopupMaxWidth = Math.max(v4.getDisplayMetrics().widthPixels / 2, v4.getDimensionPixelSize(dimen.abc_config_prefDialogWidth));
    }

    public boolean dismissPopup() {
        if(this.isShowingPopup()) {
            this.getListPopupWindow().dismiss();
            ViewTreeObserver v0 = this.getViewTreeObserver();
            if(v0.isAlive()) {
                v0.removeGlobalOnLayoutListener(this.mOnGlobalLayoutListener);
            }
        }

        return 1;
    }

    public ActivityChooserModel getDataModel() {
        return this.mAdapter.getDataModel();
    }

    ListPopupWindow getListPopupWindow() {
        if(this.mListPopupWindow == null) {
            this.mListPopupWindow = new ListPopupWindow(this.getContext());
            this.mListPopupWindow.setAdapter(this.mAdapter);
            this.mListPopupWindow.setAnchorView(((View)this));
            this.mListPopupWindow.setModal(true);
            this.mListPopupWindow.setOnItemClickListener(this.mCallbacks);
            this.mListPopupWindow.setOnDismissListener(this.mCallbacks);
        }

        return this.mListPopupWindow;
    }

    public boolean isShowingPopup() {
        return this.getListPopupWindow().isShowing();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ActivityChooserModel v0 = this.mAdapter.getDataModel();
        if(v0 != null) {
            v0.registerObserver(this.mModelDataSetObserver);
        }

        this.mIsAttachedToWindow = true;
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ActivityChooserModel v0 = this.mAdapter.getDataModel();
        if(v0 != null) {
            v0.unregisterObserver(this.mModelDataSetObserver);
        }

        ViewTreeObserver v0_1 = this.getViewTreeObserver();
        if(v0_1.isAlive()) {
            v0_1.removeGlobalOnLayoutListener(this.mOnGlobalLayoutListener);
        }

        if(this.isShowingPopup()) {
            this.dismissPopup();
        }

        this.mIsAttachedToWindow = false;
    }

    protected void onLayout(boolean arg1, int arg2, int arg3, int arg4, int arg5) {
        this.mActivityChooserContent.layout(0, 0, arg4 - arg2, arg5 - arg3);
        if(!this.isShowingPopup()) {
            this.dismissPopup();
        }
    }

    protected void onMeasure(int arg3, int arg4) {
        LinearLayoutCompat v0 = this.mActivityChooserContent;
        if(this.mDefaultActivityButton.getVisibility() != 0) {
            arg4 = View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(arg4), 0x40000000);
        }

        this.measureChild(((View)v0), arg3, arg4);
        this.setMeasuredDimension(((View)v0).getMeasuredWidth(), ((View)v0).getMeasuredHeight());
    }

    public void setActivityChooserModel(ActivityChooserModel arg2) {
        this.mAdapter.setDataModel(arg2);
        if(this.isShowingPopup()) {
            this.dismissPopup();
            this.showPopup();
        }
    }

    public void setDefaultActionButtonContentDescription(int arg1) {
        this.mDefaultActionButtonContentDescription = arg1;
    }

    public void setExpandActivityOverflowButtonContentDescription(int arg2) {
        this.mExpandActivityOverflowButtonImage.setContentDescription(this.getContext().getString(arg2));
    }

    public void setExpandActivityOverflowButtonDrawable(Drawable arg2) {
        this.mExpandActivityOverflowButtonImage.setImageDrawable(arg2);
    }

    public void setInitialActivityCount(int arg1) {
        this.mInitialActivityCount = arg1;
    }

    public void setOnDismissListener(PopupWindow$OnDismissListener arg1) {
        this.mOnDismissListener = arg1;
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public void setProvider(ActionProvider arg1) {
        this.mProvider = arg1;
    }

    public boolean showPopup() {
        if(!this.isShowingPopup()) {
            if(!this.mIsAttachedToWindow) {
            }
            else {
                this.mIsSelectingDefaultActivity = false;
                this.showPopupUnchecked(this.mInitialActivityCount);
                return 1;
            }
        }

        return 0;
    }

    void showPopupUnchecked(int arg6) {
        if(this.mAdapter.getDataModel() == null) {
            throw new IllegalStateException("No data model. Did you call #setDataModel?");
        }

        this.getViewTreeObserver().addOnGlobalLayoutListener(this.mOnGlobalLayoutListener);
        int v0 = this.mDefaultActivityButton.getVisibility() == 0 ? 1 : 0;
        int v3 = this.mAdapter.getActivityCount();
        if(arg6 == 0x7FFFFFFF || v3 <= arg6 + v0) {
            this.mAdapter.setShowFooterView(false);
            this.mAdapter.setMaxActivityCount(arg6);
        }
        else {
            this.mAdapter.setShowFooterView(true);
            this.mAdapter.setMaxActivityCount(arg6 - 1);
        }

        ListPopupWindow v6 = this.getListPopupWindow();
        if(!v6.isShowing()) {
            if((this.mIsSelectingDefaultActivity) || v0 == 0) {
                this.mAdapter.setShowDefaultActivity(true, ((boolean)v0));
            }
            else {
                this.mAdapter.setShowDefaultActivity(false, false);
            }

            v6.setContentWidth(Math.min(this.mAdapter.measureContentWidth(), this.mListPopupMaxWidth));
            v6.show();
            if(this.mProvider != null) {
                this.mProvider.subUiVisibilityChanged(true);
            }

            v6.getListView().setContentDescription(this.getContext().getString(string.abc_activitychooserview_choose_application));
            v6.getListView().setSelector(new ColorDrawable(0));
        }
    }

    void updateAppearance() {
        if(this.mAdapter.getCount() > 0) {
            this.mExpandActivityOverflowButton.setEnabled(true);
        }
        else {
            this.mExpandActivityOverflowButton.setEnabled(false);
        }

        int v0 = this.mAdapter.getActivityCount();
        int v3 = this.mAdapter.getHistorySize();
        if(v0 != 1) {
            if(v0 > 1 && v3 > 0) {
                goto label_22;
            }

            this.mDefaultActivityButton.setVisibility(8);
        }
        else {
        label_22:
            this.mDefaultActivityButton.setVisibility(0);
            ResolveInfo v0_1 = this.mAdapter.getDefaultActivity();
            PackageManager v3_1 = this.getContext().getPackageManager();
            this.mDefaultActivityButtonImage.setImageDrawable(v0_1.loadIcon(v3_1));
            if(this.mDefaultActionButtonContentDescription == 0) {
                goto label_41;
            }

            this.mDefaultActivityButton.setContentDescription(this.getContext().getString(this.mDefaultActionButtonContentDescription, new Object[]{v0_1.loadLabel(v3_1)}));
        }

    label_41:
        if(this.mDefaultActivityButton.getVisibility() == 0) {
            this.mActivityChooserContent.setBackgroundDrawable(this.mActivityChooserContentBackground);
        }
        else {
            this.mActivityChooserContent.setBackgroundDrawable(null);
        }
    }
}

