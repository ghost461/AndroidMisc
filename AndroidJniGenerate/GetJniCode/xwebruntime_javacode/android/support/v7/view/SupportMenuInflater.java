package android.support.v7.view;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.PorterDuff$Mode;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.ActionProvider;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.appcompat.R$styleable;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuItemWrapperICS;
import android.support.v7.widget.DrawableUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.InflateException;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem$OnMenuItemClickListener;
import android.view.MenuItem;
import android.view.SubMenu;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

@RestrictTo(value={Scope.LIBRARY_GROUP}) public class SupportMenuInflater extends MenuInflater {
    class InflatedOnMenuItemClickListener implements MenuItem$OnMenuItemClickListener {
        private static final Class[] PARAM_TYPES;
        private Method mMethod;
        private Object mRealOwner;

        static {
            InflatedOnMenuItemClickListener.PARAM_TYPES = new Class[]{MenuItem.class};
        }

        public InflatedOnMenuItemClickListener(Object arg5, String arg6) {
            super();
            this.mRealOwner = arg5;
            Class v5 = arg5.getClass();
            try {
                this.mMethod = v5.getMethod(arg6, InflatedOnMenuItemClickListener.PARAM_TYPES);
                return;
            }
            catch(Exception v0) {
                StringBuilder v2 = new StringBuilder();
                v2.append("Couldn\'t resolve menu item onClick handler ");
                v2.append(arg6);
                v2.append(" in class ");
                v2.append(v5.getName());
                InflateException v1 = new InflateException(v2.toString());
                v1.initCause(((Throwable)v0));
                throw v1;
            }
        }

        public boolean onMenuItemClick(MenuItem arg6) {
            try {
                if(this.mMethod.getReturnType() == Boolean.TYPE) {
                    return this.mMethod.invoke(this.mRealOwner, arg6).booleanValue();
                }

                this.mMethod.invoke(this.mRealOwner, arg6);
                return 1;
            }
            catch(Exception v6) {
                throw new RuntimeException(((Throwable)v6));
            }
        }
    }

    class MenuState {
        private static final int defaultGroupId = 0;
        private static final int defaultItemCategory = 0;
        private static final int defaultItemCheckable = 0;
        private static final boolean defaultItemChecked = false;
        private static final boolean defaultItemEnabled = true;
        private static final int defaultItemId = 0;
        private static final int defaultItemOrder = 0;
        private static final boolean defaultItemVisible = true;
        private int groupCategory;
        private int groupCheckable;
        private boolean groupEnabled;
        private int groupId;
        private int groupOrder;
        private boolean groupVisible;
        ActionProvider itemActionProvider;
        private String itemActionProviderClassName;
        private String itemActionViewClassName;
        private int itemActionViewLayout;
        private boolean itemAdded;
        private int itemAlphabeticModifiers;
        private char itemAlphabeticShortcut;
        private int itemCategoryOrder;
        private int itemCheckable;
        private boolean itemChecked;
        private CharSequence itemContentDescription;
        private boolean itemEnabled;
        private int itemIconResId;
        private ColorStateList itemIconTintList;
        private PorterDuff$Mode itemIconTintMode;
        private int itemId;
        private String itemListenerMethodName;
        private int itemNumericModifiers;
        private char itemNumericShortcut;
        private int itemShowAsAction;
        private CharSequence itemTitle;
        private CharSequence itemTitleCondensed;
        private CharSequence itemTooltipText;
        private boolean itemVisible;
        private Menu menu;

        public MenuState(SupportMenuInflater arg1, Menu arg2) {
            SupportMenuInflater.this = arg1;
            super();
            this.itemIconTintList = null;
            this.itemIconTintMode = null;
            this.menu = arg2;
            this.resetGroup();
        }

        public void addItem() {
            this.itemAdded = true;
            this.setItem(this.menu.add(this.groupId, this.itemId, this.itemCategoryOrder, this.itemTitle));
        }

        public SubMenu addSubMenuItem() {
            this.itemAdded = true;
            SubMenu v0 = this.menu.addSubMenu(this.groupId, this.itemId, this.itemCategoryOrder, this.itemTitle);
            this.setItem(v0.getItem());
            return v0;
        }

        private char getShortcut(String arg2) {
            if(arg2 == null) {
                return 0;
            }

            return arg2.charAt(0);
        }

        public boolean hasAddedItem() {
            return this.itemAdded;
        }

        private Object newInstance(String arg3, Class[] arg4, Object[] arg5) {
            try {
                Constructor v4_1 = SupportMenuInflater.this.mContext.getClassLoader().loadClass(arg3).getConstructor(arg4);
                v4_1.setAccessible(true);
                return v4_1.newInstance(arg5);
            }
            catch(Exception v4) {
                Log.w("SupportMenuInflater", "Cannot instantiate class: " + arg3, ((Throwable)v4));
                return null;
            }
        }

        public void readGroup(AttributeSet arg3) {
            TypedArray v3 = SupportMenuInflater.this.mContext.obtainStyledAttributes(arg3, styleable.MenuGroup);
            this.groupId = v3.getResourceId(styleable.MenuGroup_android_id, 0);
            this.groupCategory = v3.getInt(styleable.MenuGroup_android_menuCategory, 0);
            this.groupOrder = v3.getInt(styleable.MenuGroup_android_orderInCategory, 0);
            this.groupCheckable = v3.getInt(styleable.MenuGroup_android_checkableBehavior, 0);
            this.groupVisible = v3.getBoolean(styleable.MenuGroup_android_visible, true);
            this.groupEnabled = v3.getBoolean(styleable.MenuGroup_android_enabled, true);
            v3.recycle();
        }

        public void readItem(AttributeSet arg7) {
            TypedArray v7 = SupportMenuInflater.this.mContext.obtainStyledAttributes(arg7, styleable.MenuItem);
            this.itemId = v7.getResourceId(styleable.MenuItem_android_id, 0);
            this.itemCategoryOrder = v7.getInt(styleable.MenuItem_android_menuCategory, this.groupCategory) & 0xFFFF0000 | v7.getInt(styleable.MenuItem_android_orderInCategory, this.groupOrder) & 0xFFFF;
            this.itemTitle = v7.getText(styleable.MenuItem_android_title);
            this.itemTitleCondensed = v7.getText(styleable.MenuItem_android_titleCondensed);
            this.itemIconResId = v7.getResourceId(styleable.MenuItem_android_icon, 0);
            this.itemAlphabeticShortcut = this.getShortcut(v7.getString(styleable.MenuItem_android_alphabeticShortcut));
            this.itemAlphabeticModifiers = v7.getInt(styleable.MenuItem_alphabeticModifiers, 0x1000);
            this.itemNumericShortcut = this.getShortcut(v7.getString(styleable.MenuItem_android_numericShortcut));
            this.itemNumericModifiers = v7.getInt(styleable.MenuItem_numericModifiers, 0x1000);
            this.itemCheckable = v7.hasValue(styleable.MenuItem_android_checkable) ? v7.getBoolean(styleable.MenuItem_android_checkable, false) : this.groupCheckable;
            this.itemChecked = v7.getBoolean(styleable.MenuItem_android_checked, false);
            this.itemVisible = v7.getBoolean(styleable.MenuItem_android_visible, this.groupVisible);
            this.itemEnabled = v7.getBoolean(styleable.MenuItem_android_enabled, this.groupEnabled);
            int v2 = -1;
            this.itemShowAsAction = v7.getInt(styleable.MenuItem_showAsAction, v2);
            this.itemListenerMethodName = v7.getString(styleable.MenuItem_android_onClick);
            this.itemActionViewLayout = v7.getResourceId(styleable.MenuItem_actionLayout, 0);
            this.itemActionViewClassName = v7.getString(styleable.MenuItem_actionViewClass);
            this.itemActionProviderClassName = v7.getString(styleable.MenuItem_actionProviderClass);
            int v0 = this.itemActionProviderClassName != null ? 1 : 0;
            ActionProvider v3 = null;
            if(v0 == 0 || this.itemActionViewLayout != 0 || this.itemActionViewClassName != null) {
                if(v0 != 0) {
                    Log.w("SupportMenuInflater", "Ignoring attribute \'actionProviderClass\'. Action view already specified.");
                }

                this.itemActionProvider = v3;
            }
            else {
                this.itemActionProvider = this.newInstance(this.itemActionProviderClassName, SupportMenuInflater.ACTION_PROVIDER_CONSTRUCTOR_SIGNATURE, SupportMenuInflater.this.mActionProviderConstructorArguments);
            }

            this.itemContentDescription = v7.getText(styleable.MenuItem_contentDescription);
            this.itemTooltipText = v7.getText(styleable.MenuItem_tooltipText);
            this.itemIconTintMode = v7.hasValue(styleable.MenuItem_iconTintMode) ? DrawableUtils.parseTintMode(v7.getInt(styleable.MenuItem_iconTintMode, v2), this.itemIconTintMode) : ((PorterDuff$Mode)v3);
            this.itemIconTintList = v7.hasValue(styleable.MenuItem_iconTint) ? v7.getColorStateList(styleable.MenuItem_iconTint) : ((ColorStateList)v3);
            v7.recycle();
            this.itemAdded = false;
        }

        public void resetGroup() {
            this.groupId = 0;
            this.groupCategory = 0;
            this.groupOrder = 0;
            this.groupCheckable = 0;
            this.groupVisible = true;
            this.groupEnabled = true;
        }

        private void setItem(MenuItem arg6) {
            MenuItem v0 = arg6.setChecked(this.itemChecked).setVisible(this.itemVisible).setEnabled(this.itemEnabled);
            int v2 = 0;
            boolean v1 = this.itemCheckable >= 1 ? true : false;
            v0.setCheckable(v1).setTitleCondensed(this.itemTitleCondensed).setIcon(this.itemIconResId);
            if(this.itemShowAsAction >= 0) {
                arg6.setShowAsAction(this.itemShowAsAction);
            }

            if(this.itemListenerMethodName != null) {
                if(SupportMenuInflater.this.mContext.isRestricted()) {
                    throw new IllegalStateException("The android:onClick attribute cannot be used within a restricted context");
                }
                else {
                    arg6.setOnMenuItemClickListener(new InflatedOnMenuItemClickListener(SupportMenuInflater.this.getRealOwner(), this.itemListenerMethodName));
                }
            }

            boolean v0_1 = arg6 instanceof MenuItemImpl;
            if(this.itemCheckable >= 2) {
                if(v0_1) {
                    arg6.setExclusiveCheckable(true);
                }
                else if((arg6 instanceof MenuItemWrapperICS)) {
                    arg6.setExclusiveCheckable(true);
                }
            }

            if(this.itemActionViewClassName != null) {
                arg6.setActionView(this.newInstance(this.itemActionViewClassName, SupportMenuInflater.ACTION_VIEW_CONSTRUCTOR_SIGNATURE, SupportMenuInflater.this.mActionViewConstructorArguments));
                v2 = 1;
            }

            if(this.itemActionViewLayout > 0) {
                if(v2 == 0) {
                    arg6.setActionView(this.itemActionViewLayout);
                }
                else {
                    Log.w("SupportMenuInflater", "Ignoring attribute \'itemActionViewLayout\'. Action view already specified.");
                }
            }

            if(this.itemActionProvider != null) {
                MenuItemCompat.setActionProvider(arg6, this.itemActionProvider);
            }

            MenuItemCompat.setContentDescription(arg6, this.itemContentDescription);
            MenuItemCompat.setTooltipText(arg6, this.itemTooltipText);
            MenuItemCompat.setAlphabeticShortcut(arg6, this.itemAlphabeticShortcut, this.itemAlphabeticModifiers);
            MenuItemCompat.setNumericShortcut(arg6, this.itemNumericShortcut, this.itemNumericModifiers);
            if(this.itemIconTintMode != null) {
                MenuItemCompat.setIconTintMode(arg6, this.itemIconTintMode);
            }

            if(this.itemIconTintList != null) {
                MenuItemCompat.setIconTintList(arg6, this.itemIconTintList);
            }
        }
    }

    static final Class[] ACTION_PROVIDER_CONSTRUCTOR_SIGNATURE = null;
    static final Class[] ACTION_VIEW_CONSTRUCTOR_SIGNATURE = null;
    static final String LOG_TAG = "SupportMenuInflater";
    static final int NO_ID = 0;
    private static final String XML_GROUP = "group";
    private static final String XML_ITEM = "item";
    private static final String XML_MENU = "menu";
    final Object[] mActionProviderConstructorArguments;
    final Object[] mActionViewConstructorArguments;
    Context mContext;
    private Object mRealOwner;

    static {
        SupportMenuInflater.ACTION_VIEW_CONSTRUCTOR_SIGNATURE = new Class[]{Context.class};
        SupportMenuInflater.ACTION_PROVIDER_CONSTRUCTOR_SIGNATURE = SupportMenuInflater.ACTION_VIEW_CONSTRUCTOR_SIGNATURE;
    }

    public SupportMenuInflater(Context arg3) {
        super(arg3);
        this.mContext = arg3;
        this.mActionViewConstructorArguments = new Object[]{arg3};
        this.mActionProviderConstructorArguments = this.mActionViewConstructorArguments;
    }

    private Object findRealOwner(Object arg2) {
        if((arg2 instanceof Activity)) {
            return arg2;
        }

        if((arg2 instanceof ContextWrapper)) {
            return this.findRealOwner(((ContextWrapper)arg2).getBaseContext());
        }

        return arg2;
    }

    Object getRealOwner() {
        if(this.mRealOwner == null) {
            this.mRealOwner = this.findRealOwner(this.mContext);
        }

        return this.mRealOwner;
    }

    public void inflate(int arg3, Menu arg4) {
        XmlResourceParser v3;
        if(!(arg4 instanceof SupportMenu)) {
            super.inflate(arg3, arg4);
            return;
        }

        XmlResourceParser v0 = null;
        try {
            v3 = this.mContext.getResources().getLayout(arg3);
            goto label_8;
        }
        catch(Throwable v4) {
        }
        catch(IOException v4_1) {
            goto label_25;
            try {
            label_8:
                this.parseMenu(((XmlPullParser)v3), Xml.asAttributeSet(((XmlPullParser)v3)), arg4);
                if(v3 == null) {
                    return;
                }

                goto label_11;
            }
            catch(Throwable v4) {
                v0 = v3;
            }
            catch(IOException v4_1) {
                v0 = v3;
                try {
                label_25:
                    throw new InflateException("Error inflating menu XML", ((Throwable)v4_1));
                }
                catch(Throwable v4) {
                label_23:
                }
            }
            catch(XmlPullParserException v4_2) {
                v0 = v3;
                try {
                label_30:
                    throw new InflateException("Error inflating menu XML", ((Throwable)v4_2));
                }
                catch(Throwable v4) {
                    goto label_23;
                }
            }
        }
        catch(XmlPullParserException v4_2) {
            goto label_30;
        }

        if(v0 != null) {
            v0.close();
        }

        throw v4;
    label_11:
        v3.close();
    }

    private void parseMenu(XmlPullParser arg9, AttributeSet arg10, Menu arg11) throws XmlPullParserException, IOException {
        String v4_1;
        MenuState v0 = new MenuState(this, arg11);
        int v11 = arg9.getEventType();
        do {
            if(v11 != 2) {
                v11 = arg9.next();
                if(v11 != 1) {
                    continue;
                }
            }
            else {
                break;
            }

            goto label_23;
        }
        while(true);

        String v11_1 = arg9.getName();
        if(v11_1.equals("menu")) {
            v11 = arg9.next();
        }
        else {
            goto label_12;
        }

    label_23:
        Object v1 = null;
        int v4 = v11;
        Object v6 = v1;
        v11 = 0;
        int v5 = 0;
        goto label_29;
    label_12:
        StringBuilder v10 = new StringBuilder();
        v10.append("Expecting menu, got ");
        v10.append(v11_1);
        throw new RuntimeException(v10.toString());
    label_29:
        while(v11 == 0) {
            switch(v4) {
                case 1: {
                    throw new RuntimeException("Unexpected end of document");
                }
                case 2: {
                    if(v5 != 0) {
                        goto label_89;
                    }

                    v4_1 = arg9.getName();
                    if(v4_1.equals("group")) {
                        v0.readGroup(arg10);
                        goto label_89;
                    }

                    if(v4_1.equals("item")) {
                        v0.readItem(arg10);
                        goto label_89;
                    }

                    if(v4_1.equals("menu")) {
                        this.parseMenu(arg9, arg10, v0.addSubMenuItem());
                        goto label_89;
                    }

                    String v6_1 = v4_1;
                    v5 = 1;
                    break;
                }
                case 3: {
                    v4_1 = arg9.getName();
                    if(v5 != 0 && (v4_1.equals(v6_1))) {
                        v6 = v1;
                        v5 = 0;
                        goto label_89;
                    }

                    if(v4_1.equals("group")) {
                        v0.resetGroup();
                        goto label_89;
                    }

                    if(v4_1.equals("item")) {
                        if(v0.hasAddedItem()) {
                            goto label_89;
                        }

                        if(v0.itemActionProvider != null && (v0.itemActionProvider.hasSubMenu())) {
                            v0.addSubMenuItem();
                            goto label_89;
                        }

                        v0.addItem();
                        goto label_89;
                    }

                    if(!v4_1.equals("menu")) {
                        goto label_89;
                    }

                    v11 = 1;
                    break;
                }
                default: {
                    break;
                }
            }

        label_89:
            v4 = arg9.next();
        }
    }
}

