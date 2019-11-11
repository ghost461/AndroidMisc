package android.support.v7.view.menu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R$attr;
import android.support.v7.appcompat.R$id;
import android.support.v7.appcompat.R$layout;
import android.support.v7.appcompat.R$styleable;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout$LayoutParams;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

@RestrictTo(value={Scope.LIBRARY_GROUP}) public class ListMenuItemView extends LinearLayout implements ItemView {
    private static final String TAG = "ListMenuItemView";
    private Drawable mBackground;
    private CheckBox mCheckBox;
    private boolean mForceShowIcon;
    private ImageView mIconView;
    private LayoutInflater mInflater;
    private MenuItemImpl mItemData;
    private int mMenuType;
    private boolean mPreserveIconSpacing;
    private RadioButton mRadioButton;
    private TextView mShortcutView;
    private Drawable mSubMenuArrow;
    private ImageView mSubMenuArrowView;
    private int mTextAppearance;
    private Context mTextAppearanceContext;
    private TextView mTitleView;

    public ListMenuItemView(Context arg2, AttributeSet arg3) {
        this(arg2, arg3, attr.listMenuViewStyle);
    }

    public ListMenuItemView(Context arg4, AttributeSet arg5, int arg6) {
        super(arg4, arg5);
        TintTypedArray v5 = TintTypedArray.obtainStyledAttributes(this.getContext(), arg5, styleable.MenuView, arg6, 0);
        this.mBackground = v5.getDrawable(styleable.MenuView_android_itemBackground);
        this.mTextAppearance = v5.getResourceId(styleable.MenuView_android_itemTextAppearance, -1);
        this.mPreserveIconSpacing = v5.getBoolean(styleable.MenuView_preserveIconSpacing, false);
        this.mTextAppearanceContext = arg4;
        this.mSubMenuArrow = v5.getDrawable(styleable.MenuView_subMenuArrow);
        v5.recycle();
    }

    private LayoutInflater getInflater() {
        if(this.mInflater == null) {
            this.mInflater = LayoutInflater.from(this.getContext());
        }

        return this.mInflater;
    }

    public MenuItemImpl getItemData() {
        return this.mItemData;
    }

    public void initialize(MenuItemImpl arg2, int arg3) {
        this.mItemData = arg2;
        this.mMenuType = arg3;
        arg3 = arg2.isVisible() ? 0 : 8;
        this.setVisibility(arg3);
        this.setTitle(arg2.getTitleForItemView(((ItemView)this)));
        this.setCheckable(arg2.isCheckable());
        this.setShortcut(arg2.shouldShowShortcut(), arg2.getShortcut());
        this.setIcon(arg2.getIcon());
        this.setEnabled(arg2.isEnabled());
        this.setSubMenuArrowVisible(arg2.hasSubMenu());
        this.setContentDescription(arg2.getContentDescription());
    }

    private void insertCheckBox() {
        this.mCheckBox = this.getInflater().inflate(layout.abc_list_menu_item_checkbox, ((ViewGroup)this), false);
        this.addView(this.mCheckBox);
    }

    private void insertIconView() {
        this.mIconView = this.getInflater().inflate(layout.abc_list_menu_item_icon, ((ViewGroup)this), false);
        this.addView(this.mIconView, 0);
    }

    private void insertRadioButton() {
        this.mRadioButton = this.getInflater().inflate(layout.abc_list_menu_item_radio, ((ViewGroup)this), false);
        this.addView(this.mRadioButton);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        ViewCompat.setBackground(((View)this), this.mBackground);
        this.mTitleView = this.findViewById(id.title);
        if(this.mTextAppearance != -1) {
            this.mTitleView.setTextAppearance(this.mTextAppearanceContext, this.mTextAppearance);
        }

        this.mShortcutView = this.findViewById(id.shortcut);
        this.mSubMenuArrowView = this.findViewById(id.submenuarrow);
        if(this.mSubMenuArrowView != null) {
            this.mSubMenuArrowView.setImageDrawable(this.mSubMenuArrow);
        }
    }

    protected void onMeasure(int arg4, int arg5) {
        if(this.mIconView != null && (this.mPreserveIconSpacing)) {
            ViewGroup$LayoutParams v0 = this.getLayoutParams();
            ViewGroup$LayoutParams v1 = this.mIconView.getLayoutParams();
            if(v0.height > 0 && ((LinearLayout$LayoutParams)v1).width <= 0) {
                ((LinearLayout$LayoutParams)v1).width = v0.height;
            }
        }

        super.onMeasure(arg4, arg5);
    }

    public boolean prefersCondensedTitle() {
        return 0;
    }

    public void setCheckable(boolean arg5) {
        RadioButton v1_1;
        CheckBox v0_1;
        if(!arg5 && this.mRadioButton == null && this.mCheckBox == null) {
            return;
        }

        if(this.mItemData.isExclusiveCheckable()) {
            if(this.mRadioButton == null) {
                this.insertRadioButton();
            }

            RadioButton v0 = this.mRadioButton;
            CheckBox v1 = this.mCheckBox;
        }
        else {
            if(this.mCheckBox == null) {
                this.insertCheckBox();
            }

            v0_1 = this.mCheckBox;
            v1_1 = this.mRadioButton;
        }

        int v2 = 8;
        if(arg5) {
            ((CompoundButton)v0_1).setChecked(this.mItemData.isChecked());
            int v5 = arg5 ? 0 : 8;
            if(((CompoundButton)v0_1).getVisibility() != v5) {
                ((CompoundButton)v0_1).setVisibility(v5);
            }

            if((((CheckBox)v1_1)) == null) {
                return;
            }

            if(((CompoundButton)v1_1).getVisibility() == v2) {
                return;
            }

            ((CompoundButton)v1_1).setVisibility(v2);
        }
        else {
            if(this.mCheckBox != null) {
                this.mCheckBox.setVisibility(v2);
            }

            if(this.mRadioButton == null) {
                return;
            }

            this.mRadioButton.setVisibility(v2);
        }
    }

    public void setChecked(boolean arg2) {
        RadioButton v0;
        if(this.mItemData.isExclusiveCheckable()) {
            if(this.mRadioButton == null) {
                this.insertRadioButton();
            }

            v0 = this.mRadioButton;
        }
        else {
            if(this.mCheckBox == null) {
                this.insertCheckBox();
            }

            CheckBox v0_1 = this.mCheckBox;
        }

        ((CompoundButton)v0).setChecked(arg2);
    }

    public void setForceShowIcon(boolean arg1) {
        this.mForceShowIcon = arg1;
        this.mPreserveIconSpacing = arg1;
    }

    public void setIcon(Drawable arg4) {
        int v0 = (this.mItemData.shouldShowIcon()) || (this.mForceShowIcon) ? 1 : 0;
        if(v0 == 0 && !this.mPreserveIconSpacing) {
            return;
        }

        if(this.mIconView == null && arg4 == null && !this.mPreserveIconSpacing) {
            return;
        }

        if(this.mIconView == null) {
            this.insertIconView();
        }

        if(arg4 != null || (this.mPreserveIconSpacing)) {
            ImageView v2 = this.mIconView;
            if(v0 != 0) {
            }
            else {
                arg4 = null;
            }

            v2.setImageDrawable(arg4);
            if(this.mIconView.getVisibility() == 0) {
                return;
            }

            this.mIconView.setVisibility(0);
        }
        else {
            this.mIconView.setVisibility(8);
        }
    }

    public void setShortcut(boolean arg2, char arg3) {
        int v2 = !arg2 || !this.mItemData.shouldShowShortcut() ? 8 : 0;
        if(v2 == 0) {
            this.mShortcutView.setText(this.mItemData.getShortcutLabel());
        }

        if(this.mShortcutView.getVisibility() != v2) {
            this.mShortcutView.setVisibility(v2);
        }
    }

    private void setSubMenuArrowVisible(boolean arg2) {
        if(this.mSubMenuArrowView != null) {
            ImageView v0 = this.mSubMenuArrowView;
            int v2 = arg2 ? 0 : 8;
            v0.setVisibility(v2);
        }
    }

    public void setTitle(CharSequence arg2) {
        if(arg2 != null) {
            this.mTitleView.setText(arg2);
            if(this.mTitleView.getVisibility() != 0) {
                this.mTitleView.setVisibility(0);
            }
        }
        else {
            int v0 = 8;
            if(this.mTitleView.getVisibility() != v0) {
                this.mTitleView.setVisibility(v0);
            }
        }
    }

    public boolean showsIcon() {
        return this.mForceShowIcon;
    }
}

