package android.support.v7.app;

import android.content.Context;
import android.content.DialogInterface$OnCancelListener;
import android.content.DialogInterface$OnClickListener;
import android.content.DialogInterface$OnDismissListener;
import android.content.DialogInterface$OnKeyListener;
import android.content.DialogInterface$OnMultiChoiceClickListener;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView$OnScrollChangeListener;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.appcompat.R$attr;
import android.support.v7.appcompat.R$id;
import android.support.v7.appcompat.R$styleable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View$OnClickListener;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewStub;
import android.view.Window;
import android.widget.AbsListView$OnScrollListener;
import android.widget.AbsListView;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.AdapterView$OnItemSelectedListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout$LayoutParams;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import java.lang.ref.WeakReference;

class AlertController {
    class android.support.v7.app.AlertController$1 implements View$OnClickListener {
        android.support.v7.app.AlertController$1(AlertController arg1) {
            AlertController.this = arg1;
            super();
        }

        public void onClick(View arg3) {
            Message v3;
            if(arg3 != AlertController.this.mButtonPositive || AlertController.this.mButtonPositiveMessage == null) {
                if(arg3 == AlertController.this.mButtonNegative && AlertController.this.mButtonNegativeMessage != null) {
                    v3 = Message.obtain(AlertController.this.mButtonNegativeMessage);
                    goto label_31;
                }

                if(arg3 == AlertController.this.mButtonNeutral && AlertController.this.mButtonNeutralMessage != null) {
                    v3 = Message.obtain(AlertController.this.mButtonNeutralMessage);
                    goto label_31;
                }

                v3 = null;
            }
            else {
                v3 = Message.obtain(AlertController.this.mButtonPositiveMessage);
            }

        label_31:
            if(v3 != null) {
                v3.sendToTarget();
            }

            AlertController.this.mHandler.obtainMessage(1, AlertController.this.mDialog).sendToTarget();
        }
    }

    public class AlertParams {
        public interface OnPrepareListViewListener {
            void onPrepareListView(ListView arg1);
        }

        public ListAdapter mAdapter;
        public boolean mCancelable;
        public int mCheckedItem;
        public boolean[] mCheckedItems;
        public final Context mContext;
        public Cursor mCursor;
        public View mCustomTitleView;
        public boolean mForceInverseBackground;
        public Drawable mIcon;
        public int mIconAttrId;
        public int mIconId;
        public final LayoutInflater mInflater;
        public String mIsCheckedColumn;
        public boolean mIsMultiChoice;
        public boolean mIsSingleChoice;
        public CharSequence[] mItems;
        public String mLabelColumn;
        public CharSequence mMessage;
        public DialogInterface$OnClickListener mNegativeButtonListener;
        public CharSequence mNegativeButtonText;
        public DialogInterface$OnClickListener mNeutralButtonListener;
        public CharSequence mNeutralButtonText;
        public DialogInterface$OnCancelListener mOnCancelListener;
        public DialogInterface$OnMultiChoiceClickListener mOnCheckboxClickListener;
        public DialogInterface$OnClickListener mOnClickListener;
        public DialogInterface$OnDismissListener mOnDismissListener;
        public AdapterView$OnItemSelectedListener mOnItemSelectedListener;
        public DialogInterface$OnKeyListener mOnKeyListener;
        public OnPrepareListViewListener mOnPrepareListViewListener;
        public DialogInterface$OnClickListener mPositiveButtonListener;
        public CharSequence mPositiveButtonText;
        public boolean mRecycleOnMeasure;
        public CharSequence mTitle;
        public View mView;
        public int mViewLayoutResId;
        public int mViewSpacingBottom;
        public int mViewSpacingLeft;
        public int mViewSpacingRight;
        public boolean mViewSpacingSpecified;
        public int mViewSpacingTop;

        public AlertParams(Context arg2) {
            super();
            this.mIconId = 0;
            this.mIconAttrId = 0;
            this.mViewSpacingSpecified = false;
            this.mCheckedItem = -1;
            this.mRecycleOnMeasure = true;
            this.mContext = arg2;
            this.mCancelable = true;
            this.mInflater = arg2.getSystemService("layout_inflater");
        }

        public void apply(AlertController arg8) {
            if(this.mCustomTitleView != null) {
                arg8.setCustomTitle(this.mCustomTitleView);
            }
            else {
                if(this.mTitle != null) {
                    arg8.setTitle(this.mTitle);
                }

                if(this.mIcon != null) {
                    arg8.setIcon(this.mIcon);
                }

                if(this.mIconId != 0) {
                    arg8.setIcon(this.mIconId);
                }

                if(this.mIconAttrId == 0) {
                    goto label_22;
                }

                arg8.setIcon(arg8.getIconAttributeResId(this.mIconAttrId));
            }

        label_22:
            if(this.mMessage != null) {
                arg8.setMessage(this.mMessage);
            }

            Message v1 = null;
            if(this.mPositiveButtonText != null) {
                arg8.setButton(-1, this.mPositiveButtonText, this.mPositiveButtonListener, v1);
            }

            if(this.mNegativeButtonText != null) {
                arg8.setButton(-2, this.mNegativeButtonText, this.mNegativeButtonListener, v1);
            }

            if(this.mNeutralButtonText != null) {
                arg8.setButton(-3, this.mNeutralButtonText, this.mNeutralButtonListener, v1);
            }

            if(this.mItems != null || this.mCursor != null || this.mAdapter != null) {
                this.createListView(arg8);
            }

            if(this.mView != null) {
                if(this.mViewSpacingSpecified) {
                    arg8.setView(this.mView, this.mViewSpacingLeft, this.mViewSpacingTop, this.mViewSpacingRight, this.mViewSpacingBottom);
                }
                else {
                    arg8.setView(this.mView);
                }
            }
            else if(this.mViewLayoutResId != 0) {
                arg8.setView(this.mViewLayoutResId);
            }
        }

        private void createListView(AlertController arg11) {
            android.support.v7.app.AlertController$AlertParams$2 v9_1;
            View v0 = this.mInflater.inflate(arg11.mListLayout, null);
            if(!this.mIsMultiChoice) {
                int v1 = this.mIsSingleChoice ? arg11.mSingleChoiceItemLayout : arg11.mListItemLayout;
                int v4 = v1;
                int v2 = 0x1020014;
                if(this.mCursor != null) {
                    SimpleCursorAdapter v9_2 = new SimpleCursorAdapter(this.mContext, v4, this.mCursor, new String[]{this.mLabelColumn}, new int[]{v2});
                    goto label_63;
                }

                if(this.mAdapter != null) {
                    ListAdapter v9_3 = this.mAdapter;
                    goto label_63;
                }

                CheckedItemAdapter v9_4 = new CheckedItemAdapter(this.mContext, v4, v2, this.mItems);
            }
            else if(this.mCursor == null) {
                android.support.v7.app.AlertController$AlertParams$1 v9 = new ArrayAdapter(this.mContext, arg11.mMultiChoiceItemLayout, 0x1020014, this.mItems, v0) {
                    public View getView(int arg2, View arg3, ViewGroup arg4) {
                        arg3 = super.getView(arg2, arg3, arg4);
                        if(AlertParams.this.mCheckedItems != null && (AlertParams.this.mCheckedItems[arg2])) {
                            this.val$listView.setItemChecked(arg2, true);
                        }

                        return arg3;
                    }
                };
            }
            else {
                v9_1 = new CursorAdapter(this.mContext, this.mCursor, false, v0, arg11) {
                    private final int mIsCheckedIndex;
                    private final int mLabelIndex;

                    public void bindView(View arg2, Context arg3, Cursor arg4) {
                        arg2.findViewById(0x1020014).setText(arg4.getString(this.mLabelIndex));
                        RecycleListView v2 = this.val$listView;
                        int v3 = arg4.getPosition();
                        boolean v0 = true;
                        if(arg4.getInt(this.mIsCheckedIndex) == 1) {
                        }
                        else {
                            v0 = false;
                        }

                        v2.setItemChecked(v3, v0);
                    }

                    public View newView(Context arg2, Cursor arg3, ViewGroup arg4) {
                        return AlertParams.this.mInflater.inflate(this.val$dialog.mMultiChoiceItemLayout, arg4, false);
                    }
                };
            }

        label_63:
            if(this.mOnPrepareListViewListener != null) {
                this.mOnPrepareListViewListener.onPrepareListView(((ListView)v0));
            }

            arg11.mAdapter = ((ListAdapter)v9_1);
            arg11.mCheckedItem = this.mCheckedItem;
            if(this.mOnClickListener != null) {
                ((RecycleListView)v0).setOnItemClickListener(new AdapterView$OnItemClickListener(arg11) {
                    public void onItemClick(AdapterView arg1, View arg2, int arg3, long arg4) {
                        AlertParams.this.mOnClickListener.onClick(this.val$dialog.mDialog, arg3);
                        if(!AlertParams.this.mIsSingleChoice) {
                            this.val$dialog.mDialog.dismiss();
                        }
                    }
                });
            }
            else if(this.mOnCheckboxClickListener != null) {
                ((RecycleListView)v0).setOnItemClickListener(new AdapterView$OnItemClickListener(((RecycleListView)v0), arg11) {
                    public void onItemClick(AdapterView arg1, View arg2, int arg3, long arg4) {
                        if(AlertParams.this.mCheckedItems != null) {
                            AlertParams.this.mCheckedItems[arg3] = this.val$listView.isItemChecked(arg3);
                        }

                        AlertParams.this.mOnCheckboxClickListener.onClick(this.val$dialog.mDialog, arg3, this.val$listView.isItemChecked(arg3));
                    }
                });
            }

            if(this.mOnItemSelectedListener != null) {
                ((RecycleListView)v0).setOnItemSelectedListener(this.mOnItemSelectedListener);
            }

            if(this.mIsSingleChoice) {
                ((RecycleListView)v0).setChoiceMode(1);
            }
            else if(this.mIsMultiChoice) {
                ((RecycleListView)v0).setChoiceMode(2);
            }

            arg11.mListView = ((ListView)v0);
        }
    }

    final class ButtonHandler extends Handler {
        private static final int MSG_DISMISS_DIALOG = 1;
        private WeakReference mDialog;

        public ButtonHandler(DialogInterface arg2) {
            super();
            this.mDialog = new WeakReference(arg2);
        }

        public void handleMessage(Message arg3) {
            int v0 = arg3.what;
            if(v0 != 1) {
                switch(v0) {
                    case -3: 
                    case -2: 
                    case -1: {
                        arg3.obj.onClick(this.mDialog.get(), arg3.what);
                        return;
                    label_11:
                        arg3.obj.dismiss();
                        return;
                    }
                    default: {
                        return;
                    }
                }
            }
            else {
                goto label_11;
            }
        }
    }

    class CheckedItemAdapter extends ArrayAdapter {
        public CheckedItemAdapter(Context arg1, int arg2, int arg3, CharSequence[] arg4) {
            super(arg1, arg2, arg3, ((Object[])arg4));
        }

        public long getItemId(int arg3) {
            return ((long)arg3);
        }

        public boolean hasStableIds() {
            return 1;
        }
    }

    public class RecycleListView extends ListView {
        private final int mPaddingBottomNoButtons;
        private final int mPaddingTopNoTitle;

        public RecycleListView(Context arg2) {
            this(arg2, null);
        }

        public RecycleListView(Context arg2, AttributeSet arg3) {
            super(arg2, arg3);
            TypedArray v2 = arg2.obtainStyledAttributes(arg3, styleable.RecycleListView);
            this.mPaddingBottomNoButtons = v2.getDimensionPixelOffset(styleable.RecycleListView_paddingBottomNoButtons, -1);
            this.mPaddingTopNoTitle = v2.getDimensionPixelOffset(styleable.RecycleListView_paddingTopNoTitle, -1);
        }

        public void setHasDecor(boolean arg3, boolean arg4) {
            if(!arg4 || !arg3) {
                int v0 = this.getPaddingLeft();
                int v3 = arg3 ? this.getPaddingTop() : this.mPaddingTopNoTitle;
                int v1 = this.getPaddingRight();
                int v4 = arg4 ? this.getPaddingBottom() : this.mPaddingBottomNoButtons;
                this.setPadding(v0, v3, v1, v4);
            }
        }
    }

    ListAdapter mAdapter;
    private int mAlertDialogLayout;
    private final View$OnClickListener mButtonHandler;
    Button mButtonNegative;
    Message mButtonNegativeMessage;
    private CharSequence mButtonNegativeText;
    Button mButtonNeutral;
    Message mButtonNeutralMessage;
    private CharSequence mButtonNeutralText;
    private int mButtonPanelLayoutHint;
    private int mButtonPanelSideLayout;
    Button mButtonPositive;
    Message mButtonPositiveMessage;
    private CharSequence mButtonPositiveText;
    int mCheckedItem;
    private final Context mContext;
    private View mCustomTitleView;
    final AppCompatDialog mDialog;
    Handler mHandler;
    private Drawable mIcon;
    private int mIconId;
    private ImageView mIconView;
    int mListItemLayout;
    int mListLayout;
    ListView mListView;
    private CharSequence mMessage;
    private TextView mMessageView;
    int mMultiChoiceItemLayout;
    NestedScrollView mScrollView;
    private boolean mShowTitle;
    int mSingleChoiceItemLayout;
    private CharSequence mTitle;
    private TextView mTitleView;
    private View mView;
    private int mViewLayoutResId;
    private int mViewSpacingBottom;
    private int mViewSpacingLeft;
    private int mViewSpacingRight;
    private boolean mViewSpacingSpecified;
    private int mViewSpacingTop;
    private final Window mWindow;

    public AlertController(Context arg4, AppCompatDialog arg5, Window arg6) {
        super();
        this.mViewSpacingSpecified = false;
        this.mIconId = 0;
        this.mCheckedItem = -1;
        this.mButtonPanelLayoutHint = 0;
        this.mButtonHandler = new android.support.v7.app.AlertController$1(this);
        this.mContext = arg4;
        this.mDialog = arg5;
        this.mWindow = arg6;
        this.mHandler = new ButtonHandler(((DialogInterface)arg5));
        TypedArray v4 = arg4.obtainStyledAttributes(null, styleable.AlertDialog, attr.alertDialogStyle, 0);
        this.mAlertDialogLayout = v4.getResourceId(styleable.AlertDialog_android_layout, 0);
        this.mButtonPanelSideLayout = v4.getResourceId(styleable.AlertDialog_buttonPanelSideLayout, 0);
        this.mListLayout = v4.getResourceId(styleable.AlertDialog_listLayout, 0);
        this.mMultiChoiceItemLayout = v4.getResourceId(styleable.AlertDialog_multiChoiceItemLayout, 0);
        this.mSingleChoiceItemLayout = v4.getResourceId(styleable.AlertDialog_singleChoiceItemLayout, 0);
        this.mListItemLayout = v4.getResourceId(styleable.AlertDialog_listItemLayout, 0);
        this.mShowTitle = v4.getBoolean(styleable.AlertDialog_showTitle, true);
        v4.recycle();
        arg5.supportRequestWindowFeature(1);
    }

    static boolean canTextInput(View arg4) {
        if(arg4.onCheckIsTextEditor()) {
            return 1;
        }

        if(!(arg4 instanceof ViewGroup)) {
            return 0;
        }

        int v0 = ((ViewGroup)arg4).getChildCount();
        do {
            if(v0 <= 0) {
                return 0;
            }

            --v0;
        }
        while(!AlertController.canTextInput(((ViewGroup)arg4).getChildAt(v0)));

        return 1;
    }

    private void centerButton(Button arg3) {
        ViewGroup$LayoutParams v0 = arg3.getLayoutParams();
        ((LinearLayout$LayoutParams)v0).gravity = 1;
        ((LinearLayout$LayoutParams)v0).weight = 0.5f;
        arg3.setLayoutParams(v0);
    }

    public Button getButton(int arg1) {
        switch(arg1) {
            case -3: {
                goto label_7;
            }
            case -2: {
                goto label_5;
            }
            case -1: {
                goto label_3;
            }
        }

        return null;
    label_3:
        return this.mButtonPositive;
    label_5:
        return this.mButtonNegative;
    label_7:
        return this.mButtonNeutral;
    }

    public int getIconAttributeResId(int arg4) {
        TypedValue v0 = new TypedValue();
        this.mContext.getTheme().resolveAttribute(arg4, v0, true);
        return v0.resourceId;
    }

    public ListView getListView() {
        return this.mListView;
    }

    public void installContent() {
        this.mDialog.setContentView(this.selectContentView());
        this.setupView();
    }

    static void manageScrollIndicators(View arg3, View arg4, View arg5) {
        int v0 = 4;
        if(arg4 != null) {
            int v2 = arg3.canScrollVertically(-1) ? 0 : 4;
            arg4.setVisibility(v2);
        }

        if(arg5 != null) {
            if(arg3.canScrollVertically(1)) {
                v0 = 0;
            }

            arg5.setVisibility(v0);
        }
    }

    public boolean onKeyDown(int arg1, KeyEvent arg2) {
        boolean v1 = this.mScrollView == null || !this.mScrollView.executeKeyEvent(arg2) ? false : true;
        return v1;
    }

    public boolean onKeyUp(int arg1, KeyEvent arg2) {
        boolean v1 = this.mScrollView == null || !this.mScrollView.executeKeyEvent(arg2) ? false : true;
        return v1;
    }

    @Nullable private ViewGroup resolvePanel(@Nullable View arg3, @Nullable View arg4) {
        if(arg3 == null) {
            if((arg4 instanceof ViewStub)) {
                arg4 = ((ViewStub)arg4).inflate();
            }

            return arg4;
        }

        if(arg4 != null) {
            ViewParent v0 = arg4.getParent();
            if((v0 instanceof ViewGroup)) {
                ((ViewGroup)v0).removeView(arg4);
            }
        }

        if((arg3 instanceof ViewStub)) {
            arg3 = ((ViewStub)arg3).inflate();
        }

        return arg3;
    }

    private int selectContentView() {
        if(this.mButtonPanelSideLayout == 0) {
            return this.mAlertDialogLayout;
        }

        if(this.mButtonPanelLayoutHint == 1) {
            return this.mButtonPanelSideLayout;
        }

        return this.mAlertDialogLayout;
    }

    public void setButton(int arg1, CharSequence arg2, DialogInterface$OnClickListener arg3, Message arg4) {
        if(arg4 == null && arg3 != null) {
            arg4 = this.mHandler.obtainMessage(arg1, arg3);
        }

        switch(arg1) {
            case -3: {
                goto label_15;
            }
            case -2: {
                goto label_12;
            }
            case -1: {
                goto label_9;
            }
        }

        throw new IllegalArgumentException("Button does not exist");
    label_9:
        this.mButtonPositiveText = arg2;
        this.mButtonPositiveMessage = arg4;
        return;
    label_12:
        this.mButtonNegativeText = arg2;
        this.mButtonNegativeMessage = arg4;
        return;
    label_15:
        this.mButtonNeutralText = arg2;
        this.mButtonNeutralMessage = arg4;
    }

    public void setButtonPanelLayoutHint(int arg1) {
        this.mButtonPanelLayoutHint = arg1;
    }

    public void setCustomTitle(View arg1) {
        this.mCustomTitleView = arg1;
    }

    public void setIcon(int arg2) {
        this.mIcon = null;
        this.mIconId = arg2;
        if(this.mIconView != null) {
            if(arg2 != 0) {
                this.mIconView.setVisibility(0);
                this.mIconView.setImageResource(this.mIconId);
            }
            else {
                this.mIconView.setVisibility(8);
            }
        }
    }

    public void setIcon(Drawable arg3) {
        this.mIcon = arg3;
        this.mIconId = 0;
        if(this.mIconView != null) {
            if(arg3 != null) {
                this.mIconView.setVisibility(0);
                this.mIconView.setImageDrawable(arg3);
            }
            else {
                this.mIconView.setVisibility(8);
            }
        }
    }

    public void setMessage(CharSequence arg2) {
        this.mMessage = arg2;
        if(this.mMessageView != null) {
            this.mMessageView.setText(arg2);
        }
    }

    private void setScrollIndicators(ViewGroup arg5, View arg6, int arg7, int arg8) {
        View v0 = this.mWindow.findViewById(id.scrollIndicatorUp);
        View v1 = this.mWindow.findViewById(id.scrollIndicatorDown);
        if(Build$VERSION.SDK_INT >= 23) {
            ViewCompat.setScrollIndicators(arg6, arg7, arg8);
            if(v0 != null) {
                arg5.removeView(v0);
            }

            if(v1 == null) {
                return;
            }

            arg5.removeView(v1);
        }
        else {
            arg6 = null;
            if(v0 != null && (arg7 & 1) == 0) {
                arg5.removeView(v0);
                v0 = arg6;
            }

            if(v1 == null || (arg7 & 2) != 0) {
                arg6 = v1;
            }
            else {
                arg5.removeView(v1);
            }

            if(v0 == null && arg6 == null) {
                return;
            }

            if(this.mMessage != null) {
                this.mScrollView.setOnScrollChangeListener(new OnScrollChangeListener(v0, arg6) {
                    public void onScrollChange(NestedScrollView arg1, int arg2, int arg3, int arg4, int arg5) {
                        AlertController.manageScrollIndicators(((View)arg1), this.val$top, this.val$bottom);
                    }
                });
                this.mScrollView.post(new Runnable(v0, arg6) {
                    public void run() {
                        AlertController.manageScrollIndicators(AlertController.this.mScrollView, this.val$top, this.val$bottom);
                    }
                });
                return;
            }

            if(this.mListView != null) {
                this.mListView.setOnScrollListener(new AbsListView$OnScrollListener(v0, arg6) {
                    public void onScroll(AbsListView arg1, int arg2, int arg3, int arg4) {
                        AlertController.manageScrollIndicators(((View)arg1), this.val$top, this.val$bottom);
                    }

                    public void onScrollStateChanged(AbsListView arg1, int arg2) {
                    }
                });
                this.mListView.post(new Runnable(v0, arg6) {
                    public void run() {
                        AlertController.manageScrollIndicators(AlertController.this.mListView, this.val$top, this.val$bottom);
                    }
                });
                return;
            }

            if(v0 != null) {
                arg5.removeView(v0);
            }

            if(arg6 == null) {
                return;
            }

            arg5.removeView(arg6);
        }
    }

    public void setTitle(CharSequence arg2) {
        this.mTitle = arg2;
        if(this.mTitleView != null) {
            this.mTitleView.setText(arg2);
        }
    }

    public void setView(int arg2) {
        this.mView = null;
        this.mViewLayoutResId = arg2;
        this.mViewSpacingSpecified = false;
    }

    public void setView(View arg1) {
        this.mView = arg1;
        this.mViewLayoutResId = 0;
        this.mViewSpacingSpecified = false;
    }

    public void setView(View arg1, int arg2, int arg3, int arg4, int arg5) {
        this.mView = arg1;
        this.mViewLayoutResId = 0;
        this.mViewSpacingSpecified = true;
        this.mViewSpacingLeft = arg2;
        this.mViewSpacingTop = arg3;
        this.mViewSpacingRight = arg4;
        this.mViewSpacingBottom = arg5;
    }

    private void setupButtons(ViewGroup arg7) {
        int v0;
        this.mButtonPositive = arg7.findViewById(0x1020019);
        this.mButtonPositive.setOnClickListener(this.mButtonHandler);
        int v1 = 1;
        int v2 = 8;
        if(TextUtils.isEmpty(this.mButtonPositiveText)) {
            this.mButtonPositive.setVisibility(v2);
            v0 = 0;
        }
        else {
            this.mButtonPositive.setText(this.mButtonPositiveText);
            this.mButtonPositive.setVisibility(0);
            v0 = 1;
        }

        this.mButtonNegative = arg7.findViewById(0x102001A);
        this.mButtonNegative.setOnClickListener(this.mButtonHandler);
        if(TextUtils.isEmpty(this.mButtonNegativeText)) {
            this.mButtonNegative.setVisibility(v2);
        }
        else {
            this.mButtonNegative.setText(this.mButtonNegativeText);
            this.mButtonNegative.setVisibility(0);
            v0 |= 2;
        }

        this.mButtonNeutral = arg7.findViewById(0x102001B);
        this.mButtonNeutral.setOnClickListener(this.mButtonHandler);
        if(TextUtils.isEmpty(this.mButtonNeutralText)) {
            this.mButtonNeutral.setVisibility(v2);
        }
        else {
            this.mButtonNeutral.setText(this.mButtonNeutralText);
            this.mButtonNeutral.setVisibility(0);
            v0 |= 4;
        }

        if(AlertController.shouldCenterSingleButton(this.mContext)) {
            if(v0 == 1) {
                this.centerButton(this.mButtonPositive);
            }
            else if(v0 == 2) {
                this.centerButton(this.mButtonNegative);
            }
            else if(v0 == 4) {
                this.centerButton(this.mButtonNeutral);
            }
        }

        if(v0 != 0) {
        }
        else {
            v1 = 0;
        }

        if(v1 == 0) {
            arg7.setVisibility(v2);
        }
    }

    private void setupContent(ViewGroup arg5) {
        this.mScrollView = this.mWindow.findViewById(id.scrollView);
        this.mScrollView.setFocusable(false);
        this.mScrollView.setNestedScrollingEnabled(false);
        this.mMessageView = arg5.findViewById(0x102000B);
        if(this.mMessageView == null) {
            return;
        }

        if(this.mMessage != null) {
            this.mMessageView.setText(this.mMessage);
        }
        else {
            int v1 = 8;
            this.mMessageView.setVisibility(v1);
            this.mScrollView.removeView(this.mMessageView);
            if(this.mListView != null) {
                ViewParent v5 = this.mScrollView.getParent();
                int v0 = ((ViewGroup)v5).indexOfChild(this.mScrollView);
                ((ViewGroup)v5).removeViewAt(v0);
                ((ViewGroup)v5).addView(this.mListView, v0, new ViewGroup$LayoutParams(-1, -1));
            }
            else {
                arg5.setVisibility(v1);
            }
        }
    }

    private void setupCustomContent(ViewGroup arg6) {
        View v0;
        int v1 = 0;
        if(this.mView != null) {
            v0 = this.mView;
        }
        else if(this.mViewLayoutResId != 0) {
            v0 = LayoutInflater.from(this.mContext).inflate(this.mViewLayoutResId, arg6, false);
        }
        else {
            v0 = null;
        }

        if(v0 != null) {
            v1 = 1;
        }

        if(v1 == 0 || !AlertController.canTextInput(v0)) {
            this.mWindow.setFlags(0x20000, 0x20000);
        }

        if(v1 != 0) {
            View v1_1 = this.mWindow.findViewById(id.custom);
            ((FrameLayout)v1_1).addView(v0, new ViewGroup$LayoutParams(-1, -1));
            if(this.mViewSpacingSpecified) {
                ((FrameLayout)v1_1).setPadding(this.mViewSpacingLeft, this.mViewSpacingTop, this.mViewSpacingRight, this.mViewSpacingBottom);
            }

            if(this.mListView == null) {
                return;
            }

            arg6.getLayoutParams().weight = 0f;
        }
        else {
            arg6.setVisibility(8);
        }
    }

    private void setupTitle(ViewGroup arg6) {
        int v1 = 8;
        if(this.mCustomTitleView != null) {
            arg6.addView(this.mCustomTitleView, 0, new ViewGroup$LayoutParams(-1, -2));
            this.mWindow.findViewById(id.title_template).setVisibility(v1);
        }
        else {
            this.mIconView = this.mWindow.findViewById(0x1020006);
            if((TextUtils.isEmpty(this.mTitle) ^ 1) != 0 && (this.mShowTitle)) {
                this.mTitleView = this.mWindow.findViewById(id.alertTitle);
                this.mTitleView.setText(this.mTitle);
                if(this.mIconId != 0) {
                    this.mIconView.setImageResource(this.mIconId);
                }
                else if(this.mIcon != null) {
                    this.mIconView.setImageDrawable(this.mIcon);
                }
                else {
                    this.mTitleView.setPadding(this.mIconView.getPaddingLeft(), this.mIconView.getPaddingTop(), this.mIconView.getPaddingRight(), this.mIconView.getPaddingBottom());
                    this.mIconView.setVisibility(v1);
                }

                return;
            }

            this.mWindow.findViewById(id.title_template).setVisibility(v1);
            this.mIconView.setVisibility(v1);
            arg6.setVisibility(v1);
        }
    }

    private void setupView() {
        ListView v0_2;
        View v0 = this.mWindow.findViewById(id.parentPanel);
        View v1 = v0.findViewById(id.topPanel);
        View v2 = v0.findViewById(id.contentPanel);
        View v3 = v0.findViewById(id.buttonPanel);
        v0 = v0.findViewById(id.customPanel);
        this.setupCustomContent(((ViewGroup)v0));
        View v4 = ((ViewGroup)v0).findViewById(id.topPanel);
        View v5 = ((ViewGroup)v0).findViewById(id.contentPanel);
        View v6 = ((ViewGroup)v0).findViewById(id.buttonPanel);
        ViewGroup v1_1 = this.resolvePanel(v4, v1);
        ViewGroup v2_1 = this.resolvePanel(v5, v2);
        ViewGroup v3_1 = this.resolvePanel(v6, v3);
        this.setupContent(v2_1);
        this.setupButtons(v3_1);
        this.setupTitle(v1_1);
        int v4_1 = 8;
        int v6_1 = 0;
        int v0_1 = v0 == null || ((ViewGroup)v0).getVisibility() == v4_1 ? 0 : 1;
        boolean v7 = v1_1 == null || v1_1.getVisibility() == v4_1 ? false : true;
        boolean v3_2 = v3_1 == null || v3_1.getVisibility() == v4_1 ? false : true;
        if(!v3_2 && v2_1 != null) {
            v4 = v2_1.findViewById(id.textSpacerNoButtons);
            if(v4 != null) {
                v4.setVisibility(0);
            }
        }

        if(v7) {
            if(this.mScrollView != null) {
                this.mScrollView.setClipToPadding(true);
            }

            v4 = null;
            if(this.mMessage != null || this.mListView != null) {
                v4 = v1_1.findViewById(id.titleDividerNoCustom);
            }

            if(v4 == null) {
                goto label_71;
            }

            v4.setVisibility(0);
        }
        else {
            if(v2_1 == null) {
                goto label_71;
            }

            v1 = v2_1.findViewById(id.textSpacerNoTitle);
            if(v1 == null) {
                goto label_71;
            }

            v1.setVisibility(0);
        }

    label_71:
        if((this.mListView instanceof RecycleListView)) {
            this.mListView.setHasDecor(v7, v3_2);
        }

        if(v0_1 == 0) {
            if(this.mListView != null) {
                v0_2 = this.mListView;
            }
            else {
                NestedScrollView v0_3 = this.mScrollView;
            }

            if(v0_2 == null) {
                goto label_88;
            }

            if(v3_2) {
                v6_1 = 2;
            }

            this.setScrollIndicators(v2_1, ((View)v0_2), (((int)v7)) | v6_1, 3);
        }

    label_88:
        v0_2 = this.mListView;
        if(v0_2 != null && this.mAdapter != null) {
            v0_2.setAdapter(this.mAdapter);
            int v1_2 = this.mCheckedItem;
            if(v1_2 > -1) {
                v0_2.setItemChecked(v1_2, true);
                v0_2.setSelection(v1_2);
            }
        }
    }

    private static boolean shouldCenterSingleButton(Context arg3) {
        TypedValue v0 = new TypedValue();
        boolean v2 = true;
        arg3.getTheme().resolveAttribute(attr.alertDialogCenterButtons, v0, true);
        if(v0.data != 0) {
        }
        else {
            v2 = false;
        }

        return v2;
    }
}

