package android.support.v7.widget;

import android.app.PendingIntent;
import android.app.SearchableInfo;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable$ClassLoaderCreator;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.appcompat.R$attr;
import android.support.v7.appcompat.R$dimen;
import android.support.v7.appcompat.R$id;
import android.support.v7.appcompat.R$layout;
import android.support.v7.appcompat.R$string;
import android.support.v7.appcompat.R$styleable;
import android.support.v7.view.CollapsibleActionView;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent$DispatcherState;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View$MeasureSpec;
import android.view.View$OnClickListener;
import android.view.View$OnFocusChangeListener;
import android.view.View$OnKeyListener;
import android.view.View$OnLayoutChangeListener;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.AdapterView$OnItemSelectedListener;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView$OnEditorActionListener;
import android.widget.TextView;
import java.lang.reflect.Method;
import java.util.WeakHashMap;

public class SearchView extends LinearLayoutCompat implements CollapsibleActionView {
    class android.support.v7.widget.SearchView$10 implements TextWatcher {
        android.support.v7.widget.SearchView$10(SearchView arg1) {
            SearchView.this = arg1;
            super();
        }

        public void afterTextChanged(Editable arg1) {
        }

        public void beforeTextChanged(CharSequence arg1, int arg2, int arg3, int arg4) {
        }

        public void onTextChanged(CharSequence arg1, int arg2, int arg3, int arg4) {
            SearchView.this.onTextChanged(arg1);
        }
    }

    class android.support.v7.widget.SearchView$1 implements Runnable {
        android.support.v7.widget.SearchView$1(SearchView arg1) {
            SearchView.this = arg1;
            super();
        }

        public void run() {
            SearchView.this.updateFocusedState();
        }
    }

    class android.support.v7.widget.SearchView$2 implements Runnable {
        android.support.v7.widget.SearchView$2(SearchView arg1) {
            SearchView.this = arg1;
            super();
        }

        public void run() {
            if(SearchView.this.mSuggestionsAdapter != null && ((SearchView.this.mSuggestionsAdapter instanceof SuggestionsAdapter))) {
                SearchView.this.mSuggestionsAdapter.changeCursor(null);
            }
        }
    }

    class android.support.v7.widget.SearchView$5 implements View$OnClickListener {
        android.support.v7.widget.SearchView$5(SearchView arg1) {
            SearchView.this = arg1;
            super();
        }

        public void onClick(View arg2) {
            if(arg2 == SearchView.this.mSearchButton) {
                SearchView.this.onSearchClicked();
            }
            else if(arg2 == SearchView.this.mCloseButton) {
                SearchView.this.onCloseClicked();
            }
            else if(arg2 == SearchView.this.mGoButton) {
                SearchView.this.onSubmitQuery();
            }
            else if(arg2 == SearchView.this.mVoiceButton) {
                SearchView.this.onVoiceClicked();
            }
            else if(arg2 == SearchView.this.mSearchSrcTextView) {
                SearchView.this.forceSuggestionQuery();
            }
        }
    }

    class android.support.v7.widget.SearchView$6 implements View$OnKeyListener {
        android.support.v7.widget.SearchView$6(SearchView arg1) {
            SearchView.this = arg1;
            super();
        }

        public boolean onKey(View arg4, int arg5, KeyEvent arg6) {
            if(SearchView.this.mSearchable == null) {
                return 0;
            }

            if((SearchView.this.mSearchSrcTextView.isPopupShowing()) && SearchView.this.mSearchSrcTextView.getListSelection() != -1) {
                return SearchView.this.onSuggestionsKey(arg4, arg5, arg6);
            }

            if(!SearchAutoComplete.access$100(SearchView.this.mSearchSrcTextView) && (arg6.hasNoModifiers()) && arg6.getAction() == 1 && arg5 == 66) {
                arg4.cancelLongPress();
                SearchView.this.launchQuerySearch(0, null, SearchView.this.mSearchSrcTextView.getText().toString());
                return 1;
            }

            return 0;
        }
    }

    class android.support.v7.widget.SearchView$7 implements TextView$OnEditorActionListener {
        android.support.v7.widget.SearchView$7(SearchView arg1) {
            SearchView.this = arg1;
            super();
        }

        public boolean onEditorAction(TextView arg1, int arg2, KeyEvent arg3) {
            SearchView.this.onSubmitQuery();
            return 1;
        }
    }

    class android.support.v7.widget.SearchView$8 implements AdapterView$OnItemClickListener {
        android.support.v7.widget.SearchView$8(SearchView arg1) {
            SearchView.this = arg1;
            super();
        }

        public void onItemClick(AdapterView arg1, View arg2, int arg3, long arg4) {
            SearchView.this.onItemClicked(arg3, 0, null);
        }
    }

    class android.support.v7.widget.SearchView$9 implements AdapterView$OnItemSelectedListener {
        android.support.v7.widget.SearchView$9(SearchView arg1) {
            SearchView.this = arg1;
            super();
        }

        public void onItemSelected(AdapterView arg1, View arg2, int arg3, long arg4) {
            SearchView.this.onItemSelected(arg3);
        }

        public void onNothingSelected(AdapterView arg1) {
        }
    }

    class AutoCompleteTextViewReflector {
        private Method doAfterTextChanged;
        private Method doBeforeTextChanged;
        private Method ensureImeVisible;
        private Method showSoftInputUnchecked;

        AutoCompleteTextViewReflector() {
            super();
            try {
                this.doBeforeTextChanged = AutoCompleteTextView.class.getDeclaredMethod("doBeforeTextChanged");
                this.doBeforeTextChanged.setAccessible(true);
                goto label_10;
            }
            catch(NoSuchMethodException ) {
                try {
                label_10:
                    this.doAfterTextChanged = AutoCompleteTextView.class.getDeclaredMethod("doAfterTextChanged");
                    this.doAfterTextChanged.setAccessible(true);
                    goto label_17;
                }
                catch(NoSuchMethodException ) {
                    try {
                    label_17:
                        this.ensureImeVisible = AutoCompleteTextView.class.getMethod("ensureImeVisible", Boolean.TYPE);
                        this.ensureImeVisible.setAccessible(true);
                        return;
                    }
                    catch(NoSuchMethodException ) {
                        return;
                    }
                }
            }
        }

        void doAfterTextChanged(AutoCompleteTextView arg3) {
            if(this.doAfterTextChanged != null) {
                try {
                    this.doAfterTextChanged.invoke(arg3);
                    return;
                }
                catch(Exception ) {
                    return;
                }
            }
        }

        void doBeforeTextChanged(AutoCompleteTextView arg3) {
            if(this.doBeforeTextChanged != null) {
                try {
                    this.doBeforeTextChanged.invoke(arg3);
                    return;
                }
                catch(Exception ) {
                    return;
                }
            }
        }

        void ensureImeVisible(AutoCompleteTextView arg4, boolean arg5) {
            if(this.ensureImeVisible != null) {
                try {
                    this.ensureImeVisible.invoke(arg4, Boolean.valueOf(arg5));
                    return;
                }
                catch(Exception ) {
                    return;
                }
            }
        }
    }

    public interface OnCloseListener {
        boolean onClose();
    }

    public interface OnQueryTextListener {
        boolean onQueryTextChange(String arg1);

        boolean onQueryTextSubmit(String arg1);
    }

    public interface OnSuggestionListener {
        boolean onSuggestionClick(int arg1);

        boolean onSuggestionSelect(int arg1);
    }

    class SavedState extends AbsSavedState {
        final class android.support.v7.widget.SearchView$SavedState$1 implements Parcelable$ClassLoaderCreator {
            android.support.v7.widget.SearchView$SavedState$1() {
                super();
            }

            public SavedState createFromParcel(Parcel arg3) {
                return new SavedState(arg3, null);
            }

            public SavedState createFromParcel(Parcel arg2, ClassLoader arg3) {
                return new SavedState(arg2, arg3);
            }

            public Object createFromParcel(Parcel arg1) {
                return this.createFromParcel(arg1);
            }

            public Object createFromParcel(Parcel arg1, ClassLoader arg2) {
                return this.createFromParcel(arg1, arg2);
            }

            public SavedState[] newArray(int arg1) {
                return new SavedState[arg1];
            }

            public Object[] newArray(int arg1) {
                return this.newArray(arg1);
            }
        }

        public static final Parcelable$Creator CREATOR;
        boolean isIconified;

        static {
            SavedState.CREATOR = new android.support.v7.widget.SearchView$SavedState$1();
        }

        SavedState(Parcelable arg1) {
            super(arg1);
        }

        public SavedState(Parcel arg1, ClassLoader arg2) {
            super(arg1, arg2);
            this.isIconified = arg1.readValue(null).booleanValue();
        }

        public String toString() {
            return "SearchView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " isIconified=" + this.isIconified + "}";
        }

        public void writeToParcel(Parcel arg1, int arg2) {
            super.writeToParcel(arg1, arg2);
            arg1.writeValue(Boolean.valueOf(this.isIconified));
        }
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public class SearchAutoComplete extends AppCompatAutoCompleteTextView {
        class android.support.v7.widget.SearchView$SearchAutoComplete$1 implements Runnable {
            android.support.v7.widget.SearchView$SearchAutoComplete$1(SearchAutoComplete arg1) {
                SearchAutoComplete.this = arg1;
                super();
            }

            public void run() {
                SearchAutoComplete.this.showSoftInputIfNecessary();
            }
        }

        private boolean mHasPendingShowSoftInputRequest;
        final Runnable mRunShowSoftInputIfNecessary;
        private SearchView mSearchView;
        private int mThreshold;

        public SearchAutoComplete(Context arg2) {
            this(arg2, null);
        }

        public SearchAutoComplete(Context arg2, AttributeSet arg3) {
            this(arg2, arg3, attr.autoCompleteTextViewStyle);
        }

        public SearchAutoComplete(Context arg1, AttributeSet arg2, int arg3) {
            super(arg1, arg2, arg3);
            this.mRunShowSoftInputIfNecessary = new android.support.v7.widget.SearchView$SearchAutoComplete$1(this);
            this.mThreshold = this.getThreshold();
        }

        static void access$000(SearchAutoComplete arg0, boolean arg1) {
            arg0.setImeVisibility(arg1);
        }

        static boolean access$100(SearchAutoComplete arg0) {
            return arg0.isEmpty();
        }

        static void access$200(SearchAutoComplete arg0) {
            arg0.showSoftInputIfNecessary();
        }

        public boolean enoughToFilter() {
            boolean v0 = this.mThreshold <= 0 || (super.enoughToFilter()) ? true : false;
            return v0;
        }

        private int getSearchViewTextMinWidthDp() {
            Configuration v0 = this.getResources().getConfiguration();
            int v1 = v0.screenWidthDp;
            int v2 = v0.screenHeightDp;
            if(v1 >= 960 && v2 >= 720 && v0.orientation == 2) {
                return 0x100;
            }

            if(v1 < 600 && (v1 < 640 || v2 < 480)) {
                return 0xA0;
            }

            return 0xC0;
        }

        private boolean isEmpty() {
            boolean v0 = TextUtils.getTrimmedLength(this.getText()) == 0 ? true : false;
            return v0;
        }

        public InputConnection onCreateInputConnection(EditorInfo arg2) {
            InputConnection v2 = super.onCreateInputConnection(arg2);
            if(this.mHasPendingShowSoftInputRequest) {
                this.removeCallbacks(this.mRunShowSoftInputIfNecessary);
                this.post(this.mRunShowSoftInputIfNecessary);
            }

            return v2;
        }

        protected void onFinishInflate() {
            super.onFinishInflate();
            this.setMinWidth(((int)TypedValue.applyDimension(1, ((float)this.getSearchViewTextMinWidthDp()), this.getResources().getDisplayMetrics())));
        }

        protected void onFocusChanged(boolean arg1, int arg2, Rect arg3) {
            super.onFocusChanged(arg1, arg2, arg3);
            this.mSearchView.onTextFocusChanged();
        }

        public boolean onKeyPreIme(int arg3, KeyEvent arg4) {
            if(arg3 == 4) {
                if(arg4.getAction() == 0 && arg4.getRepeatCount() == 0) {
                    KeyEvent$DispatcherState v3 = this.getKeyDispatcherState();
                    if(v3 != null) {
                        v3.startTracking(arg4, this);
                    }

                    return 1;
                }

                if(arg4.getAction() != 1) {
                    goto label_25;
                }

                KeyEvent$DispatcherState v0 = this.getKeyDispatcherState();
                if(v0 != null) {
                    v0.handleUpEvent(arg4);
                }

                if(!arg4.isTracking()) {
                    goto label_25;
                }

                if(arg4.isCanceled()) {
                    goto label_25;
                }

                this.mSearchView.clearFocus();
                this.setImeVisibility(false);
                return 1;
            }

        label_25:
            return super.onKeyPreIme(arg3, arg4);
        }

        public void onWindowFocusChanged(boolean arg2) {
            super.onWindowFocusChanged(arg2);
            if((arg2) && (this.mSearchView.hasFocus()) && this.getVisibility() == 0) {
                this.mHasPendingShowSoftInputRequest = true;
                if(SearchView.isLandscapeMode(this.getContext())) {
                    SearchView.HIDDEN_METHOD_INVOKER.ensureImeVisible(((AutoCompleteTextView)this), true);
                }
            }
        }

        public void performCompletion() {
        }

        protected void replaceText(CharSequence arg1) {
        }

        private void setImeVisibility(boolean arg3) {
            Object v0 = this.getContext().getSystemService("input_method");
            if(!arg3) {
                this.mHasPendingShowSoftInputRequest = false;
                this.removeCallbacks(this.mRunShowSoftInputIfNecessary);
                ((InputMethodManager)v0).hideSoftInputFromWindow(this.getWindowToken(), 0);
                return;
            }

            if(((InputMethodManager)v0).isActive(((View)this))) {
                this.mHasPendingShowSoftInputRequest = false;
                this.removeCallbacks(this.mRunShowSoftInputIfNecessary);
                ((InputMethodManager)v0).showSoftInput(((View)this), 0);
                return;
            }

            this.mHasPendingShowSoftInputRequest = true;
        }

        void setSearchView(SearchView arg1) {
            this.mSearchView = arg1;
        }

        public void setThreshold(int arg1) {
            super.setThreshold(arg1);
            this.mThreshold = arg1;
        }

        private void showSoftInputIfNecessary() {
            if(this.mHasPendingShowSoftInputRequest) {
                this.getContext().getSystemService("input_method").showSoftInput(((View)this), 0);
                this.mHasPendingShowSoftInputRequest = false;
            }
        }
    }

    class UpdatableTouchDelegate extends TouchDelegate {
        private final Rect mActualBounds;
        private boolean mDelegateTargeted;
        private final View mDelegateView;
        private final int mSlop;
        private final Rect mSlopBounds;
        private final Rect mTargetBounds;

        public UpdatableTouchDelegate(Rect arg2, Rect arg3, View arg4) {
            super(arg2, arg4);
            this.mSlop = ViewConfiguration.get(arg4.getContext()).getScaledTouchSlop();
            this.mTargetBounds = new Rect();
            this.mSlopBounds = new Rect();
            this.mActualBounds = new Rect();
            this.setBounds(arg2, arg3);
            this.mDelegateView = arg4;
        }

        public boolean onTouchEvent(MotionEvent arg7) {
            boolean v2;
            int v0 = ((int)arg7.getX());
            int v1 = ((int)arg7.getY());
            int v3 = 1;
            boolean v4 = false;
            switch(arg7.getAction()) {
                case 0: {
                    if(this.mTargetBounds.contains(v0, v1)) {
                        this.mDelegateTargeted = true;
                        v2 = true;
                        goto label_26;
                    }

                label_25:
                    v2 = false;
                    break;
                }
                case 1: 
                case 2: {
                    v2 = this.mDelegateTargeted;
                    if(!v2) {
                        goto label_26;
                    }

                    if(this.mSlopBounds.contains(v0, v1)) {
                        goto label_26;
                    }

                    v3 = 0;
                    break;
                }
                case 3: {
                    v2 = this.mDelegateTargeted;
                    this.mDelegateTargeted = false;
                    break;
                }
                default: {
                    goto label_25;
                }
            }

        label_26:
            if(v2) {
                if(v3 == 0 || (this.mActualBounds.contains(v0, v1))) {
                    arg7.setLocation(((float)(v0 - this.mActualBounds.left)), ((float)(v1 - this.mActualBounds.top)));
                }
                else {
                    arg7.setLocation(((float)(this.mDelegateView.getWidth() / 2)), ((float)(this.mDelegateView.getHeight() / 2)));
                }

                v4 = this.mDelegateView.dispatchTouchEvent(arg7);
            }

            return v4;
        }

        public void setBounds(Rect arg3, Rect arg4) {
            this.mTargetBounds.set(arg3);
            this.mSlopBounds.set(arg3);
            this.mSlopBounds.inset(-this.mSlop, -this.mSlop);
            this.mActualBounds.set(arg4);
        }
    }

    static final boolean DBG = false;
    static final AutoCompleteTextViewReflector HIDDEN_METHOD_INVOKER = null;
    private static final String IME_OPTION_NO_MICROPHONE = "nm";
    static final String LOG_TAG = "SearchView";
    private Bundle mAppSearchData;
    private boolean mClearingFocus;
    final ImageView mCloseButton;
    private final ImageView mCollapsedIcon;
    private int mCollapsedImeOptions;
    private final CharSequence mDefaultQueryHint;
    private final View mDropDownAnchor;
    private boolean mExpandedInActionView;
    final ImageView mGoButton;
    private boolean mIconified;
    private boolean mIconifiedByDefault;
    private int mMaxWidth;
    private CharSequence mOldQueryText;
    private final View$OnClickListener mOnClickListener;
    private OnCloseListener mOnCloseListener;
    private final TextView$OnEditorActionListener mOnEditorActionListener;
    private final AdapterView$OnItemClickListener mOnItemClickListener;
    private final AdapterView$OnItemSelectedListener mOnItemSelectedListener;
    private OnQueryTextListener mOnQueryChangeListener;
    View$OnFocusChangeListener mOnQueryTextFocusChangeListener;
    private View$OnClickListener mOnSearchClickListener;
    private OnSuggestionListener mOnSuggestionListener;
    private final WeakHashMap mOutsideDrawablesCache;
    private CharSequence mQueryHint;
    private boolean mQueryRefinement;
    private Runnable mReleaseCursorRunnable;
    final ImageView mSearchButton;
    private final View mSearchEditFrame;
    private final Drawable mSearchHintIcon;
    private final View mSearchPlate;
    final SearchAutoComplete mSearchSrcTextView;
    private Rect mSearchSrcTextViewBounds;
    private Rect mSearchSrtTextViewBoundsExpanded;
    SearchableInfo mSearchable;
    private final View mSubmitArea;
    private boolean mSubmitButtonEnabled;
    private final int mSuggestionCommitIconResId;
    private final int mSuggestionRowLayout;
    CursorAdapter mSuggestionsAdapter;
    private int[] mTemp;
    private int[] mTemp2;
    View$OnKeyListener mTextKeyListener;
    private TextWatcher mTextWatcher;
    private UpdatableTouchDelegate mTouchDelegate;
    private final Runnable mUpdateDrawableStateRunnable;
    private CharSequence mUserQuery;
    private final Intent mVoiceAppSearchIntent;
    final ImageView mVoiceButton;
    private boolean mVoiceButtonEnabled;
    private final Intent mVoiceWebSearchIntent;

    static {
        SearchView.HIDDEN_METHOD_INVOKER = new AutoCompleteTextViewReflector();
    }

    public SearchView(Context arg2) {
        this(arg2, null);
    }

    public SearchView(Context arg2, AttributeSet arg3) {
        this(arg2, arg3, attr.searchViewStyle);
    }

    public SearchView(Context arg4, AttributeSet arg5, int arg6) {
        super(arg4, arg5, arg6);
        this.mSearchSrcTextViewBounds = new Rect();
        this.mSearchSrtTextViewBoundsExpanded = new Rect();
        this.mTemp = new int[2];
        this.mTemp2 = new int[2];
        this.mUpdateDrawableStateRunnable = new android.support.v7.widget.SearchView$1(this);
        this.mReleaseCursorRunnable = new android.support.v7.widget.SearchView$2(this);
        this.mOutsideDrawablesCache = new WeakHashMap();
        this.mOnClickListener = new android.support.v7.widget.SearchView$5(this);
        this.mTextKeyListener = new android.support.v7.widget.SearchView$6(this);
        this.mOnEditorActionListener = new android.support.v7.widget.SearchView$7(this);
        this.mOnItemClickListener = new android.support.v7.widget.SearchView$8(this);
        this.mOnItemSelectedListener = new android.support.v7.widget.SearchView$9(this);
        this.mTextWatcher = new android.support.v7.widget.SearchView$10(this);
        TintTypedArray v5 = TintTypedArray.obtainStyledAttributes(arg4, arg5, styleable.SearchView, arg6, 0);
        LayoutInflater.from(arg4).inflate(v5.getResourceId(styleable.SearchView_layout, layout.abc_search_view), ((ViewGroup)this), true);
        this.mSearchSrcTextView = this.findViewById(id.search_src_text);
        this.mSearchSrcTextView.setSearchView(this);
        this.mSearchEditFrame = this.findViewById(id.search_edit_frame);
        this.mSearchPlate = this.findViewById(id.search_plate);
        this.mSubmitArea = this.findViewById(id.submit_area);
        this.mSearchButton = this.findViewById(id.search_button);
        this.mGoButton = this.findViewById(id.search_go_btn);
        this.mCloseButton = this.findViewById(id.search_close_btn);
        this.mVoiceButton = this.findViewById(id.search_voice_btn);
        this.mCollapsedIcon = this.findViewById(id.search_mag_icon);
        ViewCompat.setBackground(this.mSearchPlate, v5.getDrawable(styleable.SearchView_queryBackground));
        ViewCompat.setBackground(this.mSubmitArea, v5.getDrawable(styleable.SearchView_submitBackground));
        this.mSearchButton.setImageDrawable(v5.getDrawable(styleable.SearchView_searchIcon));
        this.mGoButton.setImageDrawable(v5.getDrawable(styleable.SearchView_goIcon));
        this.mCloseButton.setImageDrawable(v5.getDrawable(styleable.SearchView_closeIcon));
        this.mVoiceButton.setImageDrawable(v5.getDrawable(styleable.SearchView_voiceIcon));
        this.mCollapsedIcon.setImageDrawable(v5.getDrawable(styleable.SearchView_searchIcon));
        this.mSearchHintIcon = v5.getDrawable(styleable.SearchView_searchHintIcon);
        TooltipCompat.setTooltipText(this.mSearchButton, this.getResources().getString(string.abc_searchview_description_search));
        this.mSuggestionRowLayout = v5.getResourceId(styleable.SearchView_suggestionRowLayout, layout.abc_search_dropdown_item_icons_2line);
        this.mSuggestionCommitIconResId = v5.getResourceId(styleable.SearchView_commitIcon, 0);
        this.mSearchButton.setOnClickListener(this.mOnClickListener);
        this.mCloseButton.setOnClickListener(this.mOnClickListener);
        this.mGoButton.setOnClickListener(this.mOnClickListener);
        this.mVoiceButton.setOnClickListener(this.mOnClickListener);
        this.mSearchSrcTextView.setOnClickListener(this.mOnClickListener);
        this.mSearchSrcTextView.addTextChangedListener(this.mTextWatcher);
        this.mSearchSrcTextView.setOnEditorActionListener(this.mOnEditorActionListener);
        this.mSearchSrcTextView.setOnItemClickListener(this.mOnItemClickListener);
        this.mSearchSrcTextView.setOnItemSelectedListener(this.mOnItemSelectedListener);
        this.mSearchSrcTextView.setOnKeyListener(this.mTextKeyListener);
        this.mSearchSrcTextView.setOnFocusChangeListener(new View$OnFocusChangeListener() {
            public void onFocusChange(View arg2, boolean arg3) {
                if(SearchView.this.mOnQueryTextFocusChangeListener != null) {
                    SearchView.this.mOnQueryTextFocusChangeListener.onFocusChange(SearchView.this, arg3);
                }
            }
        });
        this.setIconifiedByDefault(v5.getBoolean(styleable.SearchView_iconifiedByDefault, true));
        arg6 = -1;
        int v4 = v5.getDimensionPixelSize(styleable.SearchView_android_maxWidth, arg6);
        if(v4 != arg6) {
            this.setMaxWidth(v4);
        }

        this.mDefaultQueryHint = v5.getText(styleable.SearchView_defaultQueryHint);
        this.mQueryHint = v5.getText(styleable.SearchView_queryHint);
        v4 = v5.getInt(styleable.SearchView_android_imeOptions, arg6);
        if(v4 != arg6) {
            this.setImeOptions(v4);
        }

        v4 = v5.getInt(styleable.SearchView_android_inputType, arg6);
        if(v4 != arg6) {
            this.setInputType(v4);
        }

        this.setFocusable(v5.getBoolean(styleable.SearchView_android_focusable, true));
        v5.recycle();
        this.mVoiceWebSearchIntent = new Intent("android.speech.action.WEB_SEARCH");
        this.mVoiceWebSearchIntent.addFlags(0x10000000);
        this.mVoiceWebSearchIntent.putExtra("android.speech.extra.LANGUAGE_MODEL", "web_search");
        this.mVoiceAppSearchIntent = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        this.mVoiceAppSearchIntent.addFlags(0x10000000);
        this.mDropDownAnchor = this.findViewById(this.mSearchSrcTextView.getDropDownAnchor());
        if(this.mDropDownAnchor != null) {
            this.mDropDownAnchor.addOnLayoutChangeListener(new View$OnLayoutChangeListener() {
                public void onLayoutChange(View arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7, int arg8, int arg9) {
                    SearchView.this.adjustDropDownSizeAndPosition();
                }
            });
        }

        this.updateViewsVisibility(this.mIconifiedByDefault);
        this.updateQueryHint();
    }

    void adjustDropDownSizeAndPosition() {
        if(this.mDropDownAnchor.getWidth() > 1) {
            Resources v0 = this.getContext().getResources();
            int v1 = this.mSearchPlate.getPaddingLeft();
            Rect v2 = new Rect();
            boolean v3 = ViewUtils.isLayoutRtl(((View)this));
            int v4 = this.mIconifiedByDefault ? v0.getDimensionPixelSize(dimen.abc_dropdownitem_icon_width) + v0.getDimensionPixelSize(dimen.abc_dropdownitem_text_padding_left) : 0;
            this.mSearchSrcTextView.getDropDownBackground().getPadding(v2);
            int v0_1 = v3 ? -v2.left : v1 - (v2.left + v4);
            this.mSearchSrcTextView.setDropDownHorizontalOffset(v0_1);
            this.mSearchSrcTextView.setDropDownWidth(this.mDropDownAnchor.getWidth() + v2.left + v2.right + v4 - v1);
        }
    }

    public void clearFocus() {
        this.mClearingFocus = true;
        super.clearFocus();
        this.mSearchSrcTextView.clearFocus();
        SearchAutoComplete.access$000(this.mSearchSrcTextView, false);
        this.mClearingFocus = false;
    }

    private Intent createIntent(String arg2, Uri arg3, String arg4, String arg5, int arg6, String arg7) {
        Intent v0 = new Intent(arg2);
        v0.addFlags(0x10000000);
        if(arg3 != null) {
            v0.setData(arg3);
        }

        v0.putExtra("user_query", this.mUserQuery);
        if(arg5 != null) {
            v0.putExtra("query", arg5);
        }

        if(arg4 != null) {
            v0.putExtra("intent_extra_data_key", arg4);
        }

        if(this.mAppSearchData != null) {
            v0.putExtra("app_data", this.mAppSearchData);
        }

        if(arg6 != 0) {
            v0.putExtra("action_key", arg6);
            v0.putExtra("action_msg", arg7);
        }

        v0.setComponent(this.mSearchable.getSearchActivity());
        return v0;
    }

    private Intent createIntentFromSuggestion(Cursor arg9, int arg10, String arg11) {
        int v9;
        Intent v0 = null;
        try {
            String v1 = SuggestionsAdapter.getColumnString(arg9, "suggest_intent_action");
            if(v1 == null) {
                v1 = this.mSearchable.getSuggestIntentAction();
            }

            if(v1 == null) {
                v1 = "android.intent.action.SEARCH";
            }

            String v2 = v1;
            v1 = SuggestionsAdapter.getColumnString(arg9, "suggest_intent_data");
            if(v1 == null) {
                v1 = this.mSearchable.getSuggestIntentData();
            }

            if(v1 != null) {
                String v3 = SuggestionsAdapter.getColumnString(arg9, "suggest_intent_data_id");
                if(v3 != null) {
                    v1 = v1 + "/" + Uri.encode(v3);
                }
            }

            Uri v3_1 = v1 == null ? ((Uri)v0) : Uri.parse(v1);
            return this.createIntent(v2, v3_1, SuggestionsAdapter.getColumnString(arg9, "suggest_intent_extra_data"), SuggestionsAdapter.getColumnString(arg9, "suggest_intent_query"), arg10, arg11);
        }
        catch(RuntimeException v10) {
            try {
                v9 = arg9.getPosition();
            }
            catch(RuntimeException ) {
                v9 = -1;
            }

            Log.w("SearchView", "Search suggestions cursor at row " + v9 + " returned exception.", ((Throwable)v10));
            return v0;
        }
    }

    private Intent createVoiceAppSearchIntent(Intent arg10, SearchableInfo arg11) {
        ComponentName v0 = arg11.getSearchActivity();
        Intent v1 = new Intent("android.intent.action.SEARCH");
        v1.setComponent(v0);
        PendingIntent v1_1 = PendingIntent.getActivity(this.getContext(), 0, v1, 0x40000000);
        Bundle v2 = new Bundle();
        if(this.mAppSearchData != null) {
            v2.putParcelable("app_data", this.mAppSearchData);
        }

        Intent v3 = new Intent(arg10);
        String v10 = "free_form";
        int v4 = 1;
        Resources v5 = this.getResources();
        if(arg11.getVoiceLanguageModeId() != 0) {
            v10 = v5.getString(arg11.getVoiceLanguageModeId());
        }

        String v7 = null;
        String v6 = arg11.getVoicePromptTextId() != 0 ? v5.getString(arg11.getVoicePromptTextId()) : v7;
        String v5_1 = arg11.getVoiceLanguageId() != 0 ? v5.getString(arg11.getVoiceLanguageId()) : v7;
        if(arg11.getVoiceMaxResults() != 0) {
            v4 = arg11.getVoiceMaxResults();
        }

        v3.putExtra("android.speech.extra.LANGUAGE_MODEL", v10);
        v3.putExtra("android.speech.extra.PROMPT", v6);
        v3.putExtra("android.speech.extra.LANGUAGE", v5_1);
        v3.putExtra("android.speech.extra.MAX_RESULTS", v4);
        v10 = "calling_package";
        if(v0 == null) {
        }
        else {
            v7 = v0.flattenToShortString();
        }

        v3.putExtra(v10, v7);
        v3.putExtra("android.speech.extra.RESULTS_PENDINGINTENT", ((Parcelable)v1_1));
        v3.putExtra("android.speech.extra.RESULTS_PENDINGINTENT_BUNDLE", v2);
        return v3;
    }

    private Intent createVoiceWebSearchIntent(Intent arg2, SearchableInfo arg3) {
        Intent v0 = new Intent(arg2);
        ComponentName v2 = arg3.getSearchActivity();
        String v3 = "calling_package";
        String v2_1 = v2 == null ? null : v2.flattenToShortString();
        v0.putExtra(v3, v2_1);
        return v0;
    }

    private void dismissSuggestions() {
        this.mSearchSrcTextView.dismissDropDown();
    }

    void forceSuggestionQuery() {
        SearchView.HIDDEN_METHOD_INVOKER.doBeforeTextChanged(this.mSearchSrcTextView);
        SearchView.HIDDEN_METHOD_INVOKER.doAfterTextChanged(this.mSearchSrcTextView);
    }

    private void getChildBoundsWithinSearchView(View arg5, Rect arg6) {
        arg5.getLocationInWindow(this.mTemp);
        this.getLocationInWindow(this.mTemp2);
        int v0 = this.mTemp[1] - this.mTemp2[1];
        int v1 = this.mTemp[0] - this.mTemp2[0];
        arg6.set(v1, v0, arg5.getWidth() + v1, arg5.getHeight() + v0);
    }

    private CharSequence getDecoratedHint(CharSequence arg6) {
        if(this.mIconifiedByDefault) {
            if(this.mSearchHintIcon == null) {
            }
            else {
                int v0 = ((int)((((double)this.mSearchSrcTextView.getTextSize())) * 1.25));
                this.mSearchHintIcon.setBounds(0, 0, v0, v0);
                SpannableStringBuilder v0_1 = new SpannableStringBuilder("   ");
                v0_1.setSpan(new ImageSpan(this.mSearchHintIcon), 1, 2, 33);
                v0_1.append(arg6);
                return ((CharSequence)v0_1);
            }
        }

        return arg6;
    }

    public int getImeOptions() {
        return this.mSearchSrcTextView.getImeOptions();
    }

    public int getInputType() {
        return this.mSearchSrcTextView.getInputType();
    }

    public int getMaxWidth() {
        return this.mMaxWidth;
    }

    private int getPreferredHeight() {
        return this.getContext().getResources().getDimensionPixelSize(dimen.abc_search_view_preferred_height);
    }

    private int getPreferredWidth() {
        return this.getContext().getResources().getDimensionPixelSize(dimen.abc_search_view_preferred_width);
    }

    public CharSequence getQuery() {
        return this.mSearchSrcTextView.getText();
    }

    @Nullable public CharSequence getQueryHint() {
        CharSequence v0;
        if(this.mQueryHint != null) {
            v0 = this.mQueryHint;
        }
        else {
            if(this.mSearchable != null && this.mSearchable.getHintId() != 0) {
                return this.getContext().getText(this.mSearchable.getHintId());
            }

            v0 = this.mDefaultQueryHint;
        }

        return v0;
    }

    int getSuggestionCommitIconResId() {
        return this.mSuggestionCommitIconResId;
    }

    int getSuggestionRowLayout() {
        return this.mSuggestionRowLayout;
    }

    public CursorAdapter getSuggestionsAdapter() {
        return this.mSuggestionsAdapter;
    }

    private boolean hasVoiceSearch() {
        boolean v1 = false;
        if(this.mSearchable != null && (this.mSearchable.getVoiceSearchEnabled())) {
            Intent v0 = null;
            if(this.mSearchable.getVoiceSearchLaunchWebSearch()) {
                v0 = this.mVoiceWebSearchIntent;
            }
            else if(this.mSearchable.getVoiceSearchLaunchRecognizer()) {
                v0 = this.mVoiceAppSearchIntent;
            }

            if(v0 == null) {
                return 0;
            }

            if(this.getContext().getPackageManager().resolveActivity(v0, 0x10000) != null) {
                v1 = true;
            }

            return v1;
        }

        return 0;
    }

    public boolean isIconfiedByDefault() {
        return this.mIconifiedByDefault;
    }

    public boolean isIconified() {
        return this.mIconified;
    }

    static boolean isLandscapeMode(Context arg1) {
        boolean v1 = arg1.getResources().getConfiguration().orientation == 2 ? true : false;
        return v1;
    }

    public boolean isQueryRefinementEnabled() {
        return this.mQueryRefinement;
    }

    private boolean isSubmitAreaEnabled() {
        boolean v0;
        if(!this.mSubmitButtonEnabled && !this.mVoiceButtonEnabled) {
            goto label_8;
        }
        else if(!this.isIconified()) {
            v0 = true;
        }
        else {
        label_8:
            v0 = false;
        }

        return v0;
    }

    public boolean isSubmitButtonEnabled() {
        return this.mSubmitButtonEnabled;
    }

    private void launchIntent(Intent arg5) {
        if(arg5 == null) {
            return;
        }

        try {
            this.getContext().startActivity(arg5);
        }
        catch(RuntimeException v0) {
            Log.e("SearchView", "Failed launch activity: " + arg5, ((Throwable)v0));
        }
    }

    void launchQuerySearch(int arg8, String arg9, String arg10) {
        this.getContext().startActivity(this.createIntent("android.intent.action.SEARCH", null, null, arg10, arg8, arg9));
    }

    private boolean launchSuggestion(int arg2, int arg3, String arg4) {
        Cursor v0 = this.mSuggestionsAdapter.getCursor();
        if(v0 != null && (v0.moveToPosition(arg2))) {
            this.launchIntent(this.createIntentFromSuggestion(v0, arg3, arg4));
            return 1;
        }

        return 0;
    }

    public void onActionViewCollapsed() {
        this.setQuery("", false);
        this.clearFocus();
        this.updateViewsVisibility(true);
        this.mSearchSrcTextView.setImeOptions(this.mCollapsedImeOptions);
        this.mExpandedInActionView = false;
    }

    public void onActionViewExpanded() {
        if(this.mExpandedInActionView) {
            return;
        }

        this.mExpandedInActionView = true;
        this.mCollapsedImeOptions = this.mSearchSrcTextView.getImeOptions();
        this.mSearchSrcTextView.setImeOptions(this.mCollapsedImeOptions | 0x2000000);
        this.mSearchSrcTextView.setText("");
        this.setIconified(false);
    }

    void onCloseClicked() {
        if(!TextUtils.isEmpty(this.mSearchSrcTextView.getText())) {
            this.mSearchSrcTextView.setText("");
            this.mSearchSrcTextView.requestFocus();
            SearchAutoComplete.access$000(this.mSearchSrcTextView, true);
        }
        else if(this.mIconifiedByDefault) {
            if(this.mOnCloseListener != null && (this.mOnCloseListener.onClose())) {
                return;
            }

            this.clearFocus();
            this.updateViewsVisibility(true);
        }
    }

    protected void onDetachedFromWindow() {
        this.removeCallbacks(this.mUpdateDrawableStateRunnable);
        this.post(this.mReleaseCursorRunnable);
        super.onDetachedFromWindow();
    }

    boolean onItemClicked(int arg1, int arg2, String arg3) {
        if(this.mOnSuggestionListener != null) {
            if(!this.mOnSuggestionListener.onSuggestionClick(arg1)) {
            }
            else {
                return 0;
            }
        }

        this.launchSuggestion(arg1, 0, null);
        SearchAutoComplete.access$000(this.mSearchSrcTextView, false);
        this.dismissSuggestions();
        return 1;
    }

    boolean onItemSelected(int arg2) {
        if(this.mOnSuggestionListener != null) {
            if(!this.mOnSuggestionListener.onSuggestionSelect(arg2)) {
            }
            else {
                return 0;
            }
        }

        this.rewriteQueryFromSuggestion(arg2);
        return 1;
    }

    protected void onLayout(boolean arg2, int arg3, int arg4, int arg5, int arg6) {
        super.onLayout(arg2, arg3, arg4, arg5, arg6);
        if(arg2) {
            this.getChildBoundsWithinSearchView(this.mSearchSrcTextView, this.mSearchSrcTextViewBounds);
            this.mSearchSrtTextViewBoundsExpanded.set(this.mSearchSrcTextViewBounds.left, 0, this.mSearchSrcTextViewBounds.right, arg6 - arg4);
            if(this.mTouchDelegate == null) {
                this.mTouchDelegate = new UpdatableTouchDelegate(this.mSearchSrtTextViewBoundsExpanded, this.mSearchSrcTextViewBounds, this.mSearchSrcTextView);
                this.setTouchDelegate(this.mTouchDelegate);
            }
            else {
                this.mTouchDelegate.setBounds(this.mSearchSrtTextViewBoundsExpanded, this.mSearchSrcTextViewBounds);
            }
        }
    }

    protected void onMeasure(int arg4, int arg5) {
        if(this.isIconified()) {
            super.onMeasure(arg4, arg5);
            return;
        }

        int v0 = View$MeasureSpec.getMode(arg4);
        arg4 = View$MeasureSpec.getSize(arg4);
        int v1 = 0x80000000;
        int v2 = 0x40000000;
        if(v0 != v1) {
            if(v0 != 0) {
                if(v0 != v2) {
                }
                else if(this.mMaxWidth > 0) {
                    arg4 = Math.min(this.mMaxWidth, arg4);
                }
            }
            else if(this.mMaxWidth > 0) {
                arg4 = this.mMaxWidth;
            }
            else {
                arg4 = this.getPreferredWidth();
            }
        }
        else if(this.mMaxWidth > 0) {
            arg4 = Math.min(this.mMaxWidth, arg4);
        }
        else {
            arg4 = Math.min(this.getPreferredWidth(), arg4);
        }

        v0 = View$MeasureSpec.getMode(arg5);
        arg5 = View$MeasureSpec.getSize(arg5);
        if(v0 == v1) {
            arg5 = Math.min(this.getPreferredHeight(), arg5);
        }
        else if(v0 != 0) {
        }
        else {
            arg5 = this.getPreferredHeight();
        }

        super.onMeasure(View$MeasureSpec.makeMeasureSpec(arg4, v2), View$MeasureSpec.makeMeasureSpec(arg5, v2));
    }

    void onQueryRefine(CharSequence arg1) {
        this.setQuery(arg1);
    }

    protected void onRestoreInstanceState(Parcelable arg2) {
        if(!(arg2 instanceof SavedState)) {
            super.onRestoreInstanceState(arg2);
            return;
        }

        super.onRestoreInstanceState(((SavedState)arg2).getSuperState());
        this.updateViewsVisibility(((SavedState)arg2).isIconified);
        this.requestLayout();
    }

    protected Parcelable onSaveInstanceState() {
        SavedState v1 = new SavedState(super.onSaveInstanceState());
        v1.isIconified = this.isIconified();
        return ((Parcelable)v1);
    }

    void onSearchClicked() {
        this.updateViewsVisibility(false);
        this.mSearchSrcTextView.requestFocus();
        SearchAutoComplete.access$000(this.mSearchSrcTextView, true);
        if(this.mOnSearchClickListener != null) {
            this.mOnSearchClickListener.onClick(((View)this));
        }
    }

    void onSubmitQuery() {
        Editable v0 = this.mSearchSrcTextView.getText();
        if(v0 != null && TextUtils.getTrimmedLength(((CharSequence)v0)) > 0 && (this.mOnQueryChangeListener == null || !this.mOnQueryChangeListener.onQueryTextSubmit(((CharSequence)v0).toString()))) {
            if(this.mSearchable != null) {
                this.launchQuerySearch(0, null, ((CharSequence)v0).toString());
            }

            SearchAutoComplete.access$000(this.mSearchSrcTextView, false);
            this.dismissSuggestions();
        }
    }

    boolean onSuggestionsKey(View arg2, int arg3, KeyEvent arg4) {
        if(this.mSearchable == null) {
            return 0;
        }

        if(this.mSuggestionsAdapter == null) {
            return 0;
        }

        if(arg4.getAction() == 0 && (arg4.hasNoModifiers())) {
            if(arg3 != 66 && arg3 != 84) {
                if(arg3 == 61) {
                }
                else {
                    int v2 = 21;
                    if(arg3 != v2) {
                        if(arg3 == 22) {
                        }
                        else if(arg3 != 19) {
                            return 0;
                        }
                        else if(this.mSearchSrcTextView.getListSelection() == 0) {
                            return 0;
                        }
                        else {
                            return 0;
                        }
                    }

                    v2 = arg3 == v2 ? 0 : this.mSearchSrcTextView.length();
                    this.mSearchSrcTextView.setSelection(v2);
                    this.mSearchSrcTextView.setListSelection(0);
                    this.mSearchSrcTextView.clearListSelection();
                    SearchView.HIDDEN_METHOD_INVOKER.ensureImeVisible(this.mSearchSrcTextView, true);
                    return 1;
                }
            }

            return this.onItemClicked(this.mSearchSrcTextView.getListSelection(), 0, null);
        }

        return 0;
    }

    void onTextChanged(CharSequence arg3) {
        Editable v0 = this.mSearchSrcTextView.getText();
        this.mUserQuery = ((CharSequence)v0);
        boolean v1 = true;
        int v0_1 = TextUtils.isEmpty(((CharSequence)v0)) ^ 1;
        this.updateSubmitButton(((boolean)v0_1));
        if(v0_1 == 0) {
        }
        else {
            v1 = false;
        }

        this.updateVoiceButton(v1);
        this.updateCloseButton();
        this.updateSubmitArea();
        if(this.mOnQueryChangeListener != null && !TextUtils.equals(arg3, this.mOldQueryText)) {
            this.mOnQueryChangeListener.onQueryTextChange(arg3.toString());
        }

        this.mOldQueryText = arg3.toString();
    }

    void onTextFocusChanged() {
        this.updateViewsVisibility(this.isIconified());
        this.postUpdateFocusedState();
        if(this.mSearchSrcTextView.hasFocus()) {
            this.forceSuggestionQuery();
        }
    }

    void onVoiceClicked() {
        if(this.mSearchable == null) {
            return;
        }

        SearchableInfo v0 = this.mSearchable;
        try {
            if(v0.getVoiceSearchLaunchWebSearch()) {
                this.getContext().startActivity(this.createVoiceWebSearchIntent(this.mVoiceWebSearchIntent, v0));
                return;
            }

            if(!v0.getVoiceSearchLaunchRecognizer()) {
                return;
            }

            this.getContext().startActivity(this.createVoiceAppSearchIntent(this.mVoiceAppSearchIntent, v0));
        }
        catch(ActivityNotFoundException ) {
            Log.w("SearchView", "Could not find voice search activity");
        }
    }

    public void onWindowFocusChanged(boolean arg1) {
        super.onWindowFocusChanged(arg1);
        this.postUpdateFocusedState();
    }

    private void postUpdateFocusedState() {
        this.post(this.mUpdateDrawableStateRunnable);
    }

    public boolean requestFocus(int arg3, Rect arg4) {
        if(this.mClearingFocus) {
            return 0;
        }

        if(!this.isFocusable()) {
            return 0;
        }

        if(!this.isIconified()) {
            boolean v3 = this.mSearchSrcTextView.requestFocus(arg3, arg4);
            if(v3) {
                this.updateViewsVisibility(false);
            }

            return v3;
        }

        return super.requestFocus(arg3, arg4);
    }

    private void rewriteQueryFromSuggestion(int arg3) {
        Editable v0 = this.mSearchSrcTextView.getText();
        Cursor v1 = this.mSuggestionsAdapter.getCursor();
        if(v1 == null) {
            return;
        }

        if(v1.moveToPosition(arg3)) {
            CharSequence v3 = this.mSuggestionsAdapter.convertToString(v1);
            if(v3 != null) {
                this.setQuery(v3);
            }
            else {
                this.setQuery(((CharSequence)v0));
            }
        }
        else {
            this.setQuery(((CharSequence)v0));
        }
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public void setAppSearchData(Bundle arg1) {
        this.mAppSearchData = arg1;
    }

    public void setIconified(boolean arg1) {
        if(arg1) {
            this.onCloseClicked();
        }
        else {
            this.onSearchClicked();
        }
    }

    public void setIconifiedByDefault(boolean arg2) {
        if(this.mIconifiedByDefault == arg2) {
            return;
        }

        this.mIconifiedByDefault = arg2;
        this.updateViewsVisibility(arg2);
        this.updateQueryHint();
    }

    public void setImeOptions(int arg2) {
        this.mSearchSrcTextView.setImeOptions(arg2);
    }

    public void setInputType(int arg2) {
        this.mSearchSrcTextView.setInputType(arg2);
    }

    public void setMaxWidth(int arg1) {
        this.mMaxWidth = arg1;
        this.requestLayout();
    }

    public void setOnCloseListener(OnCloseListener arg1) {
        this.mOnCloseListener = arg1;
    }

    public void setOnQueryTextFocusChangeListener(View$OnFocusChangeListener arg1) {
        this.mOnQueryTextFocusChangeListener = arg1;
    }

    public void setOnQueryTextListener(OnQueryTextListener arg1) {
        this.mOnQueryChangeListener = arg1;
    }

    public void setOnSearchClickListener(View$OnClickListener arg1) {
        this.mOnSearchClickListener = arg1;
    }

    public void setOnSuggestionListener(OnSuggestionListener arg1) {
        this.mOnSuggestionListener = arg1;
    }

    private void setQuery(CharSequence arg3) {
        this.mSearchSrcTextView.setText(arg3);
        SearchAutoComplete v0 = this.mSearchSrcTextView;
        int v3 = TextUtils.isEmpty(arg3) ? 0 : arg3.length();
        v0.setSelection(v3);
    }

    public void setQuery(CharSequence arg3, boolean arg4) {
        this.mSearchSrcTextView.setText(arg3);
        if(arg3 != null) {
            this.mSearchSrcTextView.setSelection(this.mSearchSrcTextView.length());
            this.mUserQuery = arg3;
        }

        if((arg4) && !TextUtils.isEmpty(arg3)) {
            this.onSubmitQuery();
        }
    }

    public void setQueryHint(@Nullable CharSequence arg1) {
        this.mQueryHint = arg1;
        this.updateQueryHint();
    }

    public void setQueryRefinementEnabled(boolean arg2) {
        this.mQueryRefinement = arg2;
        if((this.mSuggestionsAdapter instanceof SuggestionsAdapter)) {
            CursorAdapter v0 = this.mSuggestionsAdapter;
            int v2 = arg2 ? 2 : 1;
            ((SuggestionsAdapter)v0).setQueryRefinement(v2);
        }
    }

    public void setSearchableInfo(SearchableInfo arg2) {
        this.mSearchable = arg2;
        if(this.mSearchable != null) {
            this.updateSearchAutoComplete();
            this.updateQueryHint();
        }

        this.mVoiceButtonEnabled = this.hasVoiceSearch();
        if(this.mVoiceButtonEnabled) {
            this.mSearchSrcTextView.setPrivateImeOptions("nm");
        }

        this.updateViewsVisibility(this.isIconified());
    }

    public void setSubmitButtonEnabled(boolean arg1) {
        this.mSubmitButtonEnabled = arg1;
        this.updateViewsVisibility(this.isIconified());
    }

    public void setSuggestionsAdapter(CursorAdapter arg2) {
        this.mSuggestionsAdapter = arg2;
        this.mSearchSrcTextView.setAdapter(this.mSuggestionsAdapter);
    }

    private void updateCloseButton() {
        int v1 = 1;
        int v0 = TextUtils.isEmpty(this.mSearchSrcTextView.getText()) ^ 1;
        int v2 = 0;
        if(v0 == 0 && (!this.mIconifiedByDefault || (this.mExpandedInActionView))) {
            v1 = 0;
        }

        ImageView v3 = this.mCloseButton;
        if(v1 != 0) {
        }
        else {
            v2 = 8;
        }

        v3.setVisibility(v2);
        Drawable v1_1 = this.mCloseButton.getDrawable();
        if(v1_1 != null) {
            int[] v0_1 = v0 != 0 ? SearchView.ENABLED_STATE_SET : SearchView.EMPTY_STATE_SET;
            v1_1.setState(v0_1);
        }
    }

    void updateFocusedState() {
        int[] v0 = this.mSearchSrcTextView.hasFocus() ? SearchView.FOCUSED_STATE_SET : SearchView.EMPTY_STATE_SET;
        Drawable v1 = this.mSearchPlate.getBackground();
        if(v1 != null) {
            v1.setState(v0);
        }

        v1 = this.mSubmitArea.getBackground();
        if(v1 != null) {
            v1.setState(v0);
        }

        this.invalidate();
    }

    private void updateQueryHint() {
        String v0_1;
        CharSequence v0 = this.getQueryHint();
        SearchAutoComplete v1 = this.mSearchSrcTextView;
        if(v0 == null) {
            v0_1 = "";
        }

        v1.setHint(this.getDecoratedHint(((CharSequence)v0_1)));
    }

    private void updateSearchAutoComplete() {
        this.mSearchSrcTextView.setThreshold(this.mSearchable.getSuggestThreshold());
        this.mSearchSrcTextView.setImeOptions(this.mSearchable.getImeOptions());
        int v0 = this.mSearchable.getInputType();
        int v2 = 1;
        if((v0 & 15) == 1) {
            v0 &= 0xFFFEFFFF;
            if(this.mSearchable.getSuggestAuthority() != null) {
                v0 = v0 | 0x10000 | 0x80000;
            }
        }

        this.mSearchSrcTextView.setInputType(v0);
        if(this.mSuggestionsAdapter != null) {
            this.mSuggestionsAdapter.changeCursor(null);
        }

        if(this.mSearchable.getSuggestAuthority() != null) {
            this.mSuggestionsAdapter = new SuggestionsAdapter(this.getContext(), this, this.mSearchable, this.mOutsideDrawablesCache);
            this.mSearchSrcTextView.setAdapter(this.mSuggestionsAdapter);
            CursorAdapter v0_1 = this.mSuggestionsAdapter;
            if(this.mQueryRefinement) {
                v2 = 2;
            }

            ((SuggestionsAdapter)v0_1).setQueryRefinement(v2);
        }
    }

    private void updateSubmitArea() {
        int v0;
        if(this.isSubmitAreaEnabled()) {
            if(this.mGoButton.getVisibility() != 0 && this.mVoiceButton.getVisibility() != 0) {
                goto label_10;
            }

            v0 = 0;
        }
        else {
        label_10:
            v0 = 8;
        }

        this.mSubmitArea.setVisibility(v0);
    }

    private void updateSubmitButton(boolean arg2) {
        int v2;
        if(!this.mSubmitButtonEnabled || !this.isSubmitAreaEnabled() || !this.hasFocus()) {
        label_11:
            v2 = 8;
        }
        else {
            if(!arg2 && (this.mVoiceButtonEnabled)) {
                goto label_11;
            }

            v2 = 0;
        }

        this.mGoButton.setVisibility(v2);
    }

    private void updateViewsVisibility(boolean arg7) {
        this.mIconified = arg7;
        int v0 = 8;
        boolean v1 = false;
        int v2 = arg7 ? 0 : 8;
        int v3 = TextUtils.isEmpty(this.mSearchSrcTextView.getText()) ^ 1;
        this.mSearchButton.setVisibility(v2);
        this.updateSubmitButton(((boolean)v3));
        View v2_1 = this.mSearchEditFrame;
        int v7 = arg7 ? 8 : 0;
        v2_1.setVisibility(v7);
        if(this.mCollapsedIcon.getDrawable() != null) {
            if(this.mIconifiedByDefault) {
            }
            else {
                v0 = 0;
            }
        }

        this.mCollapsedIcon.setVisibility(v0);
        this.updateCloseButton();
        if(v3 == 0) {
            v1 = true;
        }

        this.updateVoiceButton(v1);
        this.updateSubmitArea();
    }

    private void updateVoiceButton(boolean arg3) {
        int v3;
        int v1 = 8;
        if(!this.mVoiceButtonEnabled || (this.isIconified()) || !arg3) {
            v3 = 8;
        }
        else {
            v3 = 0;
            this.mGoButton.setVisibility(v1);
        }

        this.mVoiceButton.setVisibility(v3);
    }
}

