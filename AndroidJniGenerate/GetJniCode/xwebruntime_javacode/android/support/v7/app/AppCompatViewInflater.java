package android.support.v7.app;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.os.Build$VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R$styleable;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatCheckedTextView;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatMultiAutoCompleteTextView;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.AppCompatSeekBar;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.TintContextWrapper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.InflateException;
import android.view.View$OnClickListener;
import android.view.View;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

class AppCompatViewInflater {
    class DeclaredOnClickListener implements View$OnClickListener {
        private final View mHostView;
        private final String mMethodName;
        private Context mResolvedContext;
        private Method mResolvedMethod;

        public DeclaredOnClickListener(@NonNull View arg1, @NonNull String arg2) {
            super();
            this.mHostView = arg1;
            this.mMethodName = arg2;
        }

        public void onClick(@NonNull View arg5) {
            if(this.mResolvedMethod == null) {
                this.resolveMethod(this.mHostView.getContext(), this.mMethodName);
            }

            try {
                this.mResolvedMethod.invoke(this.mResolvedContext, arg5);
                return;
            }
            catch(InvocationTargetException v5) {
                throw new IllegalStateException("Could not execute method for android:onClick", ((Throwable)v5));
            }
            catch(IllegalAccessException v5_1) {
                throw new IllegalStateException("Could not execute non-public method for android:onClick", ((Throwable)v5_1));
            }
        }

        @NonNull private void resolveMethod(@Nullable Context arg5, @NonNull String arg6) {
            while(arg5 != null) {
                try {
                    if(!arg5.isRestricted()) {
                        Method v6 = arg5.getClass().getMethod(this.mMethodName, View.class);
                        if(v6 != null) {
                            this.mResolvedMethod = v6;
                            this.mResolvedContext = arg5;
                            return;
                        }
                    }

                    goto label_15;
                }
                catch(NoSuchMethodException ) {
                label_15:
                    if((arg5 instanceof ContextWrapper)) {
                        arg5 = ((ContextWrapper)arg5).getBaseContext();
                        continue;
                    }

                    arg5 = null;
                    continue;
                }
            }

            int v5 = this.mHostView.getId();
            String v5_1 = v5 == -1 ? "" : " with id \'" + this.mHostView.getContext().getResources().getResourceEntryName(v5) + "\'";
            StringBuilder v0 = new StringBuilder();
            v0.append("Could not find method ");
            v0.append(this.mMethodName);
            v0.append("(View) in a parent or ancestor Context for android:onClick ");
            v0.append("attribute defined on view ");
            v0.append(this.mHostView.getClass());
            v0.append(v5_1);
            throw new IllegalStateException(v0.toString());
        }
    }

    private static final String LOG_TAG = "AppCompatViewInflater";
    private final Object[] mConstructorArgs;
    private static final String[] sClassPrefixList;
    private static final Map sConstructorMap;
    private static final Class[] sConstructorSignature;
    private static final int[] sOnClickAttrs;

    static {
        AppCompatViewInflater.sConstructorSignature = new Class[]{Context.class, AttributeSet.class};
        AppCompatViewInflater.sOnClickAttrs = new int[]{0x101026F};
        AppCompatViewInflater.sClassPrefixList = new String[]{"android.widget.", "android.view.", "android.webkit."};
        AppCompatViewInflater.sConstructorMap = new ArrayMap();
    }

    AppCompatViewInflater() {
        super();
        this.mConstructorArgs = new Object[2];
    }

    private void checkOnClickListener(View arg4, AttributeSet arg5) {
        Context v0 = arg4.getContext();
        if(((v0 instanceof ContextWrapper)) && (Build$VERSION.SDK_INT < 15 || (ViewCompat.hasOnClickListeners(arg4)))) {
            TypedArray v5 = v0.obtainStyledAttributes(arg5, AppCompatViewInflater.sOnClickAttrs);
            String v0_1 = v5.getString(0);
            if(v0_1 != null) {
                arg4.setOnClickListener(new DeclaredOnClickListener(arg4, v0_1));
            }

            v5.recycle();
            return;
        }
    }

    private View createView(Context arg2, String arg3, String arg4) throws ClassNotFoundException, InflateException {
        Object v0 = AppCompatViewInflater.sConstructorMap.get(arg3);
        if(v0 == null) {
            try {
                ClassLoader v2 = arg2.getClassLoader();
                arg4 = arg4 != null ? arg4 + arg3 : arg3;
                Constructor v0_2 = v2.loadClass(arg4).asSubclass(View.class).getConstructor(AppCompatViewInflater.sConstructorSignature);
                AppCompatViewInflater.sConstructorMap.put(arg3, v0_2);
            label_19:
                ((Constructor)v0).setAccessible(true);
                return ((Constructor)v0).newInstance(this.mConstructorArgs);
            }
            catch(Exception ) {
                return null;
            }
        }

        goto label_19;
    }

    public final View createView(View arg1, String arg2, @NonNull Context arg3, @NonNull AttributeSet arg4, boolean arg5, boolean arg6, boolean arg7, boolean arg8) {
        AppCompatCheckBox v5_1;
        Context v1 = !arg5 || arg1 == null ? arg3 : arg1.getContext();
        if((arg6) || (arg7)) {
            v1 = AppCompatViewInflater.themifyContext(v1, arg4, arg6, arg7);
        }

        if(arg8) {
            v1 = TintContextWrapper.wrap(v1);
        }

        AppCompatSeekBar v5 = null;
        int v6 = -1;
        switch(arg2.hashCode()) {
            case -1346021293: {
                if(!arg2.equals("MultiAutoCompleteTextView")) {
                    goto label_79;
                }

                v6 = 10;
                break;
            }
            case 776382189: {
                if(!arg2.equals("RadioButton")) {
                    goto label_79;
                }

                v6 = 7;
                break;
            }
            case 1125864064: {
                if(!arg2.equals("ImageView")) {
                    goto label_79;
                }

                v6 = 1;
                break;
            }
            case 1413872058: {
                if(!arg2.equals("AutoCompleteTextView")) {
                    goto label_79;
                }

                v6 = 9;
                break;
            }
            case 1601505219: {
                if(!arg2.equals("CheckBox")) {
                    goto label_79;
                }

                v6 = 6;
                break;
            }
            case 1666676343: {
                if(!arg2.equals("EditText")) {
                    goto label_79;
                }

                v6 = 3;
                break;
            }
            case 2001146706: {
                if(!arg2.equals("Button")) {
                    goto label_79;
                }

                v6 = 2;
                break;
            }
            case -1946472170: {
                if(!arg2.equals("RatingBar")) {
                    goto label_79;
                }

                v6 = 11;
                break;
            }
            case -1455429095: {
                if(!arg2.equals("CheckedTextView")) {
                    goto label_79;
                }

                v6 = 8;
                break;
            }
            case -938935918: {
                if(!arg2.equals("TextView")) {
                    goto label_79;
                }

                v6 = 0;
                break;
            }
            case -937446323: {
                if(!arg2.equals("ImageButton")) {
                    goto label_79;
                }

                v6 = 5;
                break;
            }
            case -658531749: {
                if(!arg2.equals("SeekBar")) {
                    goto label_79;
                }

                v6 = 12;
                break;
            }
            case -339785223: {
                if(!arg2.equals("Spinner")) {
                    goto label_79;
                }

                v6 = 4;
                break;
            }
            default: {
                break;
            }
        }

    label_79:
        switch(v6) {
            case 0: {
                AppCompatTextView v5_8 = new AppCompatTextView(v1, arg4);
                break;
            }
            case 1: {
                AppCompatImageView v5_6 = new AppCompatImageView(v1, arg4);
                break;
            }
            case 2: {
                AppCompatButton v5_5 = new AppCompatButton(v1, arg4);
                break;
            }
            case 3: {
                AppCompatEditText v5_4 = new AppCompatEditText(v1, arg4);
                break;
            }
            case 4: {
                AppCompatSpinner v5_3 = new AppCompatSpinner(v1, arg4);
                break;
            }
            case 5: {
                AppCompatImageButton v5_2 = new AppCompatImageButton(v1, arg4);
                break;
            }
            case 6: {
                v5_1 = new AppCompatCheckBox(v1, arg4);
                break;
            }
            case 7: {
                AppCompatRadioButton v5_12 = new AppCompatRadioButton(v1, arg4);
                break;
            }
            case 8: {
                AppCompatCheckedTextView v5_11 = new AppCompatCheckedTextView(v1, arg4);
                break;
            }
            case 9: {
                AppCompatAutoCompleteTextView v5_10 = new AppCompatAutoCompleteTextView(v1, arg4);
                break;
            }
            case 10: {
                AppCompatMultiAutoCompleteTextView v5_9 = new AppCompatMultiAutoCompleteTextView(v1, arg4);
                break;
            }
            case 11: {
                AppCompatRatingBar v5_7 = new AppCompatRatingBar(v1, arg4);
                break;
            }
            case 12: {
                v5 = new AppCompatSeekBar(v1, arg4);
                break;
            }
            default: {
                break;
            }
        }

        if((((AppCompatSeekBar)v5_1)) == null && arg3 != v1) {
            View v5_13 = this.createViewFromTag(v1, arg2, arg4);
        }

        if((((AppCompatSeekBar)v5_1)) != null) {
            this.checkOnClickListener(((View)v5_1), arg4);
        }

        return ((View)v5_1);
    }

    private View createViewFromTag(Context arg5, String arg6, AttributeSet arg7) {
        View v5_1;
        View v3;
        int v7;
        String v1 = null;
        if(arg6.equals("view")) {
            arg6 = arg7.getAttributeValue(v1, "class");
        }

        try {
            this.mConstructorArgs[0] = arg5;
            this.mConstructorArgs[1] = arg7;
            if(-1 != arg6.indexOf(46)) {
                goto label_36;
            }

            v7 = 0;
            while(true) {
            label_17:
                if(v7 >= AppCompatViewInflater.sClassPrefixList.length) {
                    goto label_31;
                }

                v3 = this.createView(arg5, arg6, AppCompatViewInflater.sClassPrefixList[v7]);
                if(v3 == null) {
                    goto label_29;
                }

                break;
            }
        }
        catch(Exception ) {
            goto label_48;
        }
        catch(Throwable v5) {
            goto label_44;
        }

        this.mConstructorArgs[0] = v1;
        this.mConstructorArgs[1] = v1;
        return v3;
    label_29:
        ++v7;
        goto label_17;
    label_31:
        this.mConstructorArgs[0] = v1;
        this.mConstructorArgs[1] = v1;
        return ((View)v1);
        try {
        label_36:
            v5_1 = this.createView(arg5, arg6, v1);
        }
        catch(Throwable v5) {
        label_44:
            this.mConstructorArgs[0] = v1;
            this.mConstructorArgs[1] = v1;
            throw v5;
        }
        catch(Exception ) {
        label_48:
            this.mConstructorArgs[0] = v1;
            this.mConstructorArgs[1] = v1;
            return ((View)v1);
        }

        this.mConstructorArgs[0] = v1;
        this.mConstructorArgs[1] = v1;
        return v5_1;
    }

    private static Context themifyContext(Context arg2, AttributeSet arg3, boolean arg4, boolean arg5) {
        ContextThemeWrapper v2;
        TypedArray v3 = arg2.obtainStyledAttributes(arg3, styleable.View, 0, 0);
        int v4 = arg4 ? v3.getResourceId(styleable.View_android_theme, 0) : 0;
        if((arg5) && v4 == 0) {
            v4 = v3.getResourceId(styleable.View_theme, 0);
            if(v4 != 0) {
                Log.i("AppCompatViewInflater", "app:theme is now deprecated. Please move to using android:theme instead.");
            }
        }

        v3.recycle();
        if(v4 != 0 && (!(arg2 instanceof ContextThemeWrapper) || arg2.getThemeResId() != v4)) {
            v2 = new ContextThemeWrapper(arg2, v4);
        }

        return ((Context)v2);
    }
}

