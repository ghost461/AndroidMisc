package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources$Theme;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.PorterDuff$Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable$ConstantState;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build$VERSION;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.ColorUtils;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.LongSparseArray;
import android.support.v4.util.LruCache;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.appcompat.R$attr;
import android.support.v7.appcompat.R$color;
import android.support.v7.appcompat.R$drawable;
import android.support.v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.util.Xml;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

@RestrictTo(value={Scope.LIBRARY_GROUP}) public final class AppCompatDrawableManager {
    @RequiresApi(value=11) class AvdcInflateDelegate implements InflateDelegate {
        AvdcInflateDelegate() {
            super();
        }

        public Drawable createFromXmlInner(@NonNull Context arg2, @NonNull XmlPullParser arg3, @NonNull AttributeSet arg4, @Nullable Resources$Theme arg5) {
            try {
                return AnimatedVectorDrawableCompat.createFromXmlInner(arg2, arg2.getResources(), arg3, arg4, arg5);
            }
            catch(Exception v2) {
                Log.e("AvdcInflateDelegate", "Exception while inflating <animated-vector>", ((Throwable)v2));
                return null;
            }
        }
    }

    class ColorFilterLruCache extends LruCache {
        public ColorFilterLruCache(int arg1) {
            super(arg1);
        }

        private static int generateCacheKey(int arg1, PorterDuff$Mode arg2) {
            return (arg1 + 0x1F) * 0x1F + arg2.hashCode();
        }

        PorterDuffColorFilter get(int arg1, PorterDuff$Mode arg2) {
            return this.get(Integer.valueOf(ColorFilterLruCache.generateCacheKey(arg1, arg2)));
        }

        PorterDuffColorFilter put(int arg1, PorterDuff$Mode arg2, PorterDuffColorFilter arg3) {
            return this.put(Integer.valueOf(ColorFilterLruCache.generateCacheKey(arg1, arg2)), arg3);
        }
    }

    interface InflateDelegate {
        Drawable createFromXmlInner(@NonNull Context arg1, @NonNull XmlPullParser arg2, @NonNull AttributeSet arg3, @Nullable Resources$Theme arg4);
    }

    class VdcInflateDelegate implements InflateDelegate {
        VdcInflateDelegate() {
            super();
        }

        public Drawable createFromXmlInner(@NonNull Context arg1, @NonNull XmlPullParser arg2, @NonNull AttributeSet arg3, @Nullable Resources$Theme arg4) {
            try {
                return VectorDrawableCompat.createFromXmlInner(arg1.getResources(), arg2, arg3, arg4);
            }
            catch(Exception v1) {
                Log.e("VdcInflateDelegate", "Exception while inflating <vector>", ((Throwable)v1));
                return null;
            }
        }
    }

    private static final int[] COLORFILTER_COLOR_BACKGROUND_MULTIPLY = null;
    private static final int[] COLORFILTER_COLOR_CONTROL_ACTIVATED = null;
    private static final int[] COLORFILTER_TINT_COLOR_CONTROL_NORMAL = null;
    private static final ColorFilterLruCache COLOR_FILTER_CACHE = null;
    private static final boolean DEBUG = false;
    private static final PorterDuff$Mode DEFAULT_MODE = null;
    private static AppCompatDrawableManager INSTANCE = null;
    private static final String PLATFORM_VD_CLAZZ = "android.graphics.drawable.VectorDrawable";
    private static final String SKIP_DRAWABLE_TAG = "appcompat_skip_skip";
    private static final String TAG = "AppCompatDrawableManager";
    private static final int[] TINT_CHECKABLE_BUTTON_LIST;
    private static final int[] TINT_COLOR_CONTROL_NORMAL;
    private static final int[] TINT_COLOR_CONTROL_STATE_LIST;
    private ArrayMap mDelegates;
    private final Object mDrawableCacheLock;
    private final WeakHashMap mDrawableCaches;
    private boolean mHasCheckedVectorDrawableSetup;
    private SparseArrayCompat mKnownDrawableIdTags;
    private WeakHashMap mTintLists;
    private TypedValue mTypedValue;

    static {
        AppCompatDrawableManager.DEFAULT_MODE = PorterDuff$Mode.SRC_IN;
        AppCompatDrawableManager.COLOR_FILTER_CACHE = new ColorFilterLruCache(6);
        AppCompatDrawableManager.COLORFILTER_TINT_COLOR_CONTROL_NORMAL = new int[]{drawable.abc_textfield_search_default_mtrl_alpha, drawable.abc_textfield_default_mtrl_alpha, drawable.abc_ab_share_pack_mtrl_alpha};
        AppCompatDrawableManager.TINT_COLOR_CONTROL_NORMAL = new int[]{drawable.abc_ic_commit_search_api_mtrl_alpha, drawable.abc_seekbar_tick_mark_material, drawable.abc_ic_menu_share_mtrl_alpha, drawable.abc_ic_menu_copy_mtrl_am_alpha, drawable.abc_ic_menu_cut_mtrl_alpha, drawable.abc_ic_menu_selectall_mtrl_alpha, drawable.abc_ic_menu_paste_mtrl_am_alpha};
        AppCompatDrawableManager.COLORFILTER_COLOR_CONTROL_ACTIVATED = new int[]{drawable.abc_textfield_activated_mtrl_alpha, drawable.abc_textfield_search_activated_mtrl_alpha, drawable.abc_cab_background_top_mtrl_alpha, drawable.abc_text_cursor_material, drawable.abc_text_select_handle_left_mtrl_dark, drawable.abc_text_select_handle_middle_mtrl_dark, drawable.abc_text_select_handle_right_mtrl_dark, drawable.abc_text_select_handle_left_mtrl_light, drawable.abc_text_select_handle_middle_mtrl_light, drawable.abc_text_select_handle_right_mtrl_light};
        AppCompatDrawableManager.COLORFILTER_COLOR_BACKGROUND_MULTIPLY = new int[]{drawable.abc_popup_background_mtrl_mult, drawable.abc_cab_background_internal_bg, drawable.abc_menu_hardkey_panel_mtrl_mult};
        AppCompatDrawableManager.TINT_COLOR_CONTROL_STATE_LIST = new int[]{drawable.abc_tab_indicator_material, drawable.abc_textfield_search_material};
        AppCompatDrawableManager.TINT_CHECKABLE_BUTTON_LIST = new int[]{drawable.abc_btn_check_material, drawable.abc_btn_radio_material};
    }

    public AppCompatDrawableManager() {
        super();
        this.mDrawableCacheLock = new Object();
        this.mDrawableCaches = new WeakHashMap(0);
    }

    private void addDelegate(@NonNull String arg2, @NonNull InflateDelegate arg3) {
        if(this.mDelegates == null) {
            this.mDelegates = new ArrayMap();
        }

        this.mDelegates.put(arg2, arg3);
    }

    private boolean addDrawableToCache(@NonNull Context arg4, long arg5, @NonNull Drawable arg7) {
        Drawable$ConstantState v7 = arg7.getConstantState();
        if(v7 != null) {
            Object v0 = this.mDrawableCacheLock;
            __monitor_enter(v0);
            try {
                Object v1 = this.mDrawableCaches.get(arg4);
                if(v1 == null) {
                    LongSparseArray v1_1 = new LongSparseArray();
                    this.mDrawableCaches.put(arg4, v1_1);
                }

                ((LongSparseArray)v1).put(arg5, new WeakReference(v7));
                __monitor_exit(v0);
                return 1;
            label_18:
                __monitor_exit(v0);
            }
            catch(Throwable v4) {
                goto label_18;
            }

            throw v4;
        }

        return 0;
    }

    private void addTintListToCache(@NonNull Context arg3, @DrawableRes int arg4, @NonNull ColorStateList arg5) {
        SparseArrayCompat v0_1;
        if(this.mTintLists == null) {
            this.mTintLists = new WeakHashMap();
        }

        Object v0 = this.mTintLists.get(arg3);
        if(v0 == null) {
            v0_1 = new SparseArrayCompat();
            this.mTintLists.put(arg3, v0_1);
        }

        v0_1.append(arg4, arg5);
    }

    private static boolean arrayContains(int[] arg4, int arg5) {
        int v0 = arg4.length;
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            if(arg4[v2] == arg5) {
                return 1;
            }
        }

        return 0;
    }

    private void checkVectorDrawableSetup(@NonNull Context arg2) {
        if(this.mHasCheckedVectorDrawableSetup) {
            return;
        }

        this.mHasCheckedVectorDrawableSetup = true;
        Drawable v2 = this.getDrawable(arg2, drawable.abc_vector_test);
        if(v2 != null) {
            if(!AppCompatDrawableManager.isVectorDrawable(v2)) {
            }
            else {
                return;
            }
        }

        this.mHasCheckedVectorDrawableSetup = false;
        throw new IllegalStateException("This app has been built with an incorrect configuration. Please configure your build for VectorDrawableCompat.");
    }

    private ColorStateList createBorderlessButtonColorStateList(@NonNull Context arg2) {
        return this.createButtonColorStateList(arg2, 0);
    }

    private ColorStateList createButtonColorStateList(@NonNull Context arg6, @ColorInt int arg7) {
        int[][] v1 = new int[4][];
        int[] v0 = new int[4];
        int v2 = ThemeUtils.getThemeAttrColor(arg6, attr.colorControlHighlight);
        int v6 = ThemeUtils.getDisabledThemeAttrColor(arg6, attr.colorButtonNormal);
        v1[0] = ThemeUtils.DISABLED_STATE_SET;
        v0[0] = v6;
        v1[1] = ThemeUtils.PRESSED_STATE_SET;
        v0[1] = ColorUtils.compositeColors(v2, arg7);
        v1[2] = ThemeUtils.FOCUSED_STATE_SET;
        v0[2] = ColorUtils.compositeColors(v2, arg7);
        v1[3] = ThemeUtils.EMPTY_STATE_SET;
        v0[3] = arg7;
        return new ColorStateList(v1, v0);
    }

    private static long createCacheKey(TypedValue arg6) {
        return (((long)arg6.assetCookie)) << 0x20 | (((long)arg6.data));
    }

    private ColorStateList createColoredButtonColorStateList(@NonNull Context arg2) {
        return this.createButtonColorStateList(arg2, ThemeUtils.getThemeAttrColor(arg2, attr.colorAccent));
    }

    private ColorStateList createDefaultButtonColorStateList(@NonNull Context arg2) {
        return this.createButtonColorStateList(arg2, ThemeUtils.getThemeAttrColor(arg2, attr.colorButtonNormal));
    }

    private Drawable createDrawableIfNeeded(@NonNull Context arg8, @DrawableRes int arg9) {
        if(this.mTypedValue == null) {
            this.mTypedValue = new TypedValue();
        }

        TypedValue v0 = this.mTypedValue;
        arg8.getResources().getValue(arg9, v0, true);
        long v3 = AppCompatDrawableManager.createCacheKey(v0);
        Drawable v1 = this.getCachedDrawable(arg8, v3);
        if(v1 != null) {
            return v1;
        }

        if(arg9 == drawable.abc_cab_background_top_material) {
            LayerDrawable v1_1 = new LayerDrawable(new Drawable[]{this.getDrawable(arg8, drawable.abc_cab_background_internal_bg), this.getDrawable(arg8, drawable.abc_cab_background_top_mtrl_alpha)});
        }

        if(v1 != null) {
            v1.setChangingConfigurations(v0.changingConfigurations);
            this.addDrawableToCache(arg8, v3, v1);
        }

        return v1;
    }

    private ColorStateList createSwitchThumbColorStateList(Context arg8) {
        int[][] v1 = new int[3][];
        int[] v0 = new int[3];
        ColorStateList v2 = ThemeUtils.getThemeAttrColorStateList(arg8, attr.colorSwitchThumbNormal);
        int v3 = 2;
        if(v2 == null || !v2.isStateful()) {
            v1[0] = ThemeUtils.DISABLED_STATE_SET;
            v0[0] = ThemeUtils.getDisabledThemeAttrColor(arg8, attr.colorSwitchThumbNormal);
            v1[1] = ThemeUtils.CHECKED_STATE_SET;
            v0[1] = ThemeUtils.getThemeAttrColor(arg8, attr.colorControlActivated);
            v1[v3] = ThemeUtils.EMPTY_STATE_SET;
            v0[v3] = ThemeUtils.getThemeAttrColor(arg8, attr.colorSwitchThumbNormal);
        }
        else {
            v1[0] = ThemeUtils.DISABLED_STATE_SET;
            v0[0] = v2.getColorForState(v1[0], 0);
            v1[1] = ThemeUtils.CHECKED_STATE_SET;
            v0[1] = ThemeUtils.getThemeAttrColor(arg8, attr.colorControlActivated);
            v1[v3] = ThemeUtils.EMPTY_STATE_SET;
            v0[v3] = v2.getDefaultColor();
        }

        return new ColorStateList(v1, v0);
    }

    private static PorterDuffColorFilter createTintFilter(ColorStateList arg1, PorterDuff$Mode arg2, int[] arg3) {
        if(arg1 != null) {
            if(arg2 == null) {
            }
            else {
                return AppCompatDrawableManager.getPorterDuffColorFilter(arg1.getColorForState(arg3, 0), arg2);
            }
        }

        return null;
    }

    public static AppCompatDrawableManager get() {
        if(AppCompatDrawableManager.INSTANCE == null) {
            AppCompatDrawableManager.INSTANCE = new AppCompatDrawableManager();
            AppCompatDrawableManager.installDefaultInflateDelegates(AppCompatDrawableManager.INSTANCE);
        }

        return AppCompatDrawableManager.INSTANCE;
    }

    private Drawable getCachedDrawable(@NonNull Context arg5, long arg6) {
        Object v0 = this.mDrawableCacheLock;
        __monitor_enter(v0);
        try {
            Object v1 = this.mDrawableCaches.get(arg5);
            Drawable v2 = null;
            if(v1 == null) {
                __monitor_exit(v0);
                return v2;
            }

            Object v3 = ((LongSparseArray)v1).get(arg6);
            if(v3 != null) {
                v3 = ((WeakReference)v3).get();
                if(v3 != null) {
                    __monitor_exit(v0);
                    return ((Drawable$ConstantState)v3).newDrawable(arg5.getResources());
                }
                else {
                    ((LongSparseArray)v1).delete(arg6);
                }
            }

            __monitor_exit(v0);
            return v2;
        label_20:
            __monitor_exit(v0);
        }
        catch(Throwable v5) {
            goto label_20;
        }

        throw v5;
    }

    public Drawable getDrawable(@NonNull Context arg2, @DrawableRes int arg3) {
        return this.getDrawable(arg2, arg3, false);
    }

    Drawable getDrawable(@NonNull Context arg2, @DrawableRes int arg3, boolean arg4) {
        this.checkVectorDrawableSetup(arg2);
        Drawable v0 = this.loadDrawableFromDelegates(arg2, arg3);
        if(v0 == null) {
            v0 = this.createDrawableIfNeeded(arg2, arg3);
        }

        if(v0 == null) {
            v0 = ContextCompat.getDrawable(arg2, arg3);
        }

        if(v0 != null) {
            v0 = this.tintDrawable(arg2, arg3, arg4, v0);
        }

        if(v0 != null) {
            DrawableUtils.fixDrawable(v0);
        }

        return v0;
    }

    public static PorterDuffColorFilter getPorterDuffColorFilter(int arg2, PorterDuff$Mode arg3) {
        PorterDuffColorFilter v0 = AppCompatDrawableManager.COLOR_FILTER_CACHE.get(arg2, arg3);
        if(v0 == null) {
            v0 = new PorterDuffColorFilter(arg2, arg3);
            AppCompatDrawableManager.COLOR_FILTER_CACHE.put(arg2, arg3, v0);
        }

        return v0;
    }

    ColorStateList getTintList(@NonNull Context arg3, @DrawableRes int arg4) {
        ColorStateList v0 = this.getTintListFromCache(arg3, arg4);
        if(v0 == null) {
            if(arg4 == drawable.abc_edit_text_material) {
                v0 = AppCompatResources.getColorStateList(arg3, color.abc_tint_edittext);
            }
            else if(arg4 == drawable.abc_switch_track_mtrl_alpha) {
                v0 = AppCompatResources.getColorStateList(arg3, color.abc_tint_switch_track);
            }
            else if(arg4 == drawable.abc_switch_thumb_material) {
                v0 = this.createSwitchThumbColorStateList(arg3);
            }
            else if(arg4 == drawable.abc_btn_default_mtrl_shape) {
                v0 = this.createDefaultButtonColorStateList(arg3);
            }
            else if(arg4 == drawable.abc_btn_borderless_material) {
                v0 = this.createBorderlessButtonColorStateList(arg3);
            }
            else if(arg4 == drawable.abc_btn_colored_material) {
                v0 = this.createColoredButtonColorStateList(arg3);
            }
            else {
                if(arg4 != drawable.abc_spinner_mtrl_am_alpha) {
                    if(arg4 == drawable.abc_spinner_textfield_background_material) {
                    }
                    else if(AppCompatDrawableManager.arrayContains(AppCompatDrawableManager.TINT_COLOR_CONTROL_NORMAL, arg4)) {
                        v0 = ThemeUtils.getThemeAttrColorStateList(arg3, attr.colorControlNormal);
                        goto label_58;
                    }
                    else if(AppCompatDrawableManager.arrayContains(AppCompatDrawableManager.TINT_COLOR_CONTROL_STATE_LIST, arg4)) {
                        v0 = AppCompatResources.getColorStateList(arg3, color.abc_tint_default);
                        goto label_58;
                    }
                    else {
                        if(AppCompatDrawableManager.arrayContains(AppCompatDrawableManager.TINT_CHECKABLE_BUTTON_LIST, arg4)) {
                            v0 = AppCompatResources.getColorStateList(arg3, color.abc_tint_btn_checkable);
                        }
                        else if(arg4 == drawable.abc_seekbar_thumb_material) {
                            v0 = AppCompatResources.getColorStateList(arg3, color.abc_tint_seek_thumb);
                        }
                        else {
                        }

                        goto label_58;
                    }
                }

                v0 = AppCompatResources.getColorStateList(arg3, color.abc_tint_spinner);
            }

        label_58:
            if(v0 == null) {
                return v0;
            }

            this.addTintListToCache(arg3, arg4, v0);
        }

        return v0;
    }

    private ColorStateList getTintListFromCache(@NonNull Context arg3, @DrawableRes int arg4) {
        ColorStateList v1 = null;
        if(this.mTintLists != null) {
            Object v3 = this.mTintLists.get(arg3);
            if(v3 != null) {
                Object v1_1 = ((SparseArrayCompat)v3).get(arg4);
            }

            return v1;
        }

        return v1;
    }

    static PorterDuff$Mode getTintMode(int arg1) {
        PorterDuff$Mode v1 = arg1 == drawable.abc_switch_thumb_material ? PorterDuff$Mode.MULTIPLY : null;
        return v1;
    }

    private static void installDefaultInflateDelegates(@NonNull AppCompatDrawableManager arg2) {
        if(Build$VERSION.SDK_INT < 24) {
            arg2.addDelegate("vector", new VdcInflateDelegate());
            if(Build$VERSION.SDK_INT >= 11) {
                arg2.addDelegate("animated-vector", new AvdcInflateDelegate());
            }
        }
    }

    private static boolean isVectorDrawable(@NonNull Drawable arg1) {
        boolean v1 = ((arg1 instanceof VectorDrawableCompat)) || ("android.graphics.drawable.VectorDrawable".equals(arg1.getClass().getName())) ? true : false;
        return v1;
    }

    private Drawable loadDrawableFromDelegates(@NonNull Context arg10, @DrawableRes int arg11) {
        Drawable v1 = null;
        if(this.mDelegates != null && !this.mDelegates.isEmpty()) {
            if(this.mKnownDrawableIdTags != null) {
                Object v0 = this.mKnownDrawableIdTags.get(arg11);
                if(!"appcompat_skip_skip".equals(v0)) {
                    if(v0 == null) {
                    }
                    else if(this.mDelegates.get(v0) == null) {
                        return v1;
                    }

                    goto label_21;
                }

                return v1;
            }
            else {
                this.mKnownDrawableIdTags = new SparseArrayCompat();
            }

        label_21:
            if(this.mTypedValue == null) {
                this.mTypedValue = new TypedValue();
            }

            TypedValue v0_1 = this.mTypedValue;
            Resources v1_1 = arg10.getResources();
            v1_1.getValue(arg11, v0_1, true);
            long v3 = AppCompatDrawableManager.createCacheKey(v0_1);
            Drawable v5 = this.getCachedDrawable(arg10, v3);
            if(v5 != null) {
                return v5;
            }

            if(v0_1.string != null && (v0_1.string.toString().endsWith(".xml"))) {
                try {
                    XmlResourceParser v1_2 = v1_1.getXml(arg11);
                    AttributeSet v6 = Xml.asAttributeSet(((XmlPullParser)v1_2));
                    while(true) {
                        int v7 = ((XmlPullParser)v1_2).next();
                        int v8 = 2;
                        if(v7 != v8 && v7 != 1) {
                            continue;
                        }

                        break;
                    }

                    if(v7 != v8) {
                        throw new XmlPullParserException("No start tag found");
                    }

                    String v2 = ((XmlPullParser)v1_2).getName();
                    this.mKnownDrawableIdTags.append(arg11, v2);
                    Object v2_1 = this.mDelegates.get(v2);
                    if(v2_1 != null) {
                        v5 = ((InflateDelegate)v2_1).createFromXmlInner(arg10, ((XmlPullParser)v1_2), v6, arg10.getTheme());
                    }

                    if(v5 == null) {
                        goto label_71;
                    }

                    v5.setChangingConfigurations(v0_1.changingConfigurations);
                    this.addDrawableToCache(arg10, v3, v5);
                }
                catch(Exception v10) {
                    Log.e("AppCompatDrawableManager", "Exception while inflating drawable", ((Throwable)v10));
                }
            }

        label_71:
            if(v5 == null) {
                this.mKnownDrawableIdTags.append(arg11, "appcompat_skip_skip");
            }

            return v5;
        }

        return v1;
    }

    public void onConfigurationChanged(@NonNull Context arg3) {
        Object v0 = this.mDrawableCacheLock;
        __monitor_enter(v0);
        try {
            Object v3_1 = this.mDrawableCaches.get(arg3);
            if(v3_1 != null) {
                ((LongSparseArray)v3_1).clear();
            }

            __monitor_exit(v0);
            return;
        label_9:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_9;
        }

        throw v3;
    }

    Drawable onDrawableLoadedFromResources(@NonNull Context arg2, @NonNull VectorEnabledTintResources arg3, @DrawableRes int arg4) {
        Drawable v0 = this.loadDrawableFromDelegates(arg2, arg4);
        if(v0 == null) {
            v0 = arg3.superGetDrawable(arg4);
        }

        if(v0 != null) {
            return this.tintDrawable(arg2, arg4, false, v0);
        }

        return null;
    }

    private void removeDelegate(@NonNull String arg2, @NonNull InflateDelegate arg3) {
        if(this.mDelegates != null && this.mDelegates.get(arg2) == arg3) {
            this.mDelegates.remove(arg2);
        }
    }

    private static void setPorterDuffColorFilter(Drawable arg1, int arg2, PorterDuff$Mode arg3) {
        if(DrawableUtils.canSafelyMutateDrawable(arg1)) {
            arg1 = arg1.mutate();
        }

        if(arg3 == null) {
            arg3 = AppCompatDrawableManager.DEFAULT_MODE;
        }

        arg1.setColorFilter(AppCompatDrawableManager.getPorterDuffColorFilter(arg2, arg3));
    }

    static void tintDrawable(Drawable arg2, TintInfo arg3, int[] arg4) {
        if((DrawableUtils.canSafelyMutateDrawable(arg2)) && arg2.mutate() != arg2) {
            Log.d("AppCompatDrawableManager", "Mutated drawable is not the same instance as the input.");
            return;
        }

        if((arg3.mHasTintList) || (arg3.mHasTintMode)) {
            ColorStateList v0 = arg3.mHasTintList ? arg3.mTintList : null;
            PorterDuff$Mode v3 = arg3.mHasTintMode ? arg3.mTintMode : AppCompatDrawableManager.DEFAULT_MODE;
            arg2.setColorFilter(AppCompatDrawableManager.createTintFilter(v0, v3, arg4));
        }
        else {
            arg2.clearColorFilter();
        }

        if(Build$VERSION.SDK_INT <= 23) {
            arg2.invalidateSelf();
        }
    }

    private Drawable tintDrawable(@NonNull Context arg5, @DrawableRes int arg6, boolean arg7, @NonNull Drawable arg8) {
        ColorStateList v0 = this.getTintList(arg5, arg6);
        if(v0 != null) {
            if(DrawableUtils.canSafelyMutateDrawable(arg8)) {
                arg8 = arg8.mutate();
            }

            arg8 = DrawableCompat.wrap(arg8);
            DrawableCompat.setTintList(arg8, v0);
            PorterDuff$Mode v5 = AppCompatDrawableManager.getTintMode(arg6);
            if(v5 == null) {
                return arg8;
            }

            DrawableCompat.setTintMode(arg8, v5);
        }
        else {
            int v1 = 0x102000D;
            int v2 = 0x102000F;
            int v3 = 0x1020000;
            if(arg6 == drawable.abc_seekbar_track_material) {
                AppCompatDrawableManager.setPorterDuffColorFilter(arg8.findDrawableByLayerId(v3), ThemeUtils.getThemeAttrColor(arg5, attr.colorControlNormal), AppCompatDrawableManager.DEFAULT_MODE);
                AppCompatDrawableManager.setPorterDuffColorFilter(arg8.findDrawableByLayerId(v2), ThemeUtils.getThemeAttrColor(arg5, attr.colorControlNormal), AppCompatDrawableManager.DEFAULT_MODE);
                AppCompatDrawableManager.setPorterDuffColorFilter(arg8.findDrawableByLayerId(v1), ThemeUtils.getThemeAttrColor(arg5, attr.colorControlActivated), AppCompatDrawableManager.DEFAULT_MODE);
                return arg8;
            }

            if(arg6 != drawable.abc_ratingbar_material && arg6 != drawable.abc_ratingbar_indicator_material) {
                if(arg6 == drawable.abc_ratingbar_small_material) {
                }
                else {
                    if(AppCompatDrawableManager.tintDrawableUsingColorFilter(arg5, arg6, arg8)) {
                    }
                    else if(arg7) {
                        arg8 = null;
                    }
                    else {
                    }

                    return arg8;
                }
            }

            AppCompatDrawableManager.setPorterDuffColorFilter(arg8.findDrawableByLayerId(v3), ThemeUtils.getDisabledThemeAttrColor(arg5, attr.colorControlNormal), AppCompatDrawableManager.DEFAULT_MODE);
            AppCompatDrawableManager.setPorterDuffColorFilter(arg8.findDrawableByLayerId(v2), ThemeUtils.getThemeAttrColor(arg5, attr.colorControlActivated), AppCompatDrawableManager.DEFAULT_MODE);
            AppCompatDrawableManager.setPorterDuffColorFilter(arg8.findDrawableByLayerId(v1), ThemeUtils.getThemeAttrColor(arg5, attr.colorControlActivated), AppCompatDrawableManager.DEFAULT_MODE);
        }

        return arg8;
    }

    static boolean tintDrawableUsingColorFilter(@NonNull Context arg6, @DrawableRes int arg7, @NonNull Drawable arg8) {
        int v1;
        PorterDuff$Mode v0 = AppCompatDrawableManager.DEFAULT_MODE;
        int v2 = 0x1010031;
        int v3 = -1;
        if(AppCompatDrawableManager.arrayContains(AppCompatDrawableManager.COLORFILTER_TINT_COLOR_CONTROL_NORMAL, arg7)) {
            v2 = attr.colorControlNormal;
            goto label_9;
        }
        else if(AppCompatDrawableManager.arrayContains(AppCompatDrawableManager.COLORFILTER_COLOR_CONTROL_ACTIVATED, arg7)) {
            v2 = attr.colorControlActivated;
            goto label_9;
        }
        else if(AppCompatDrawableManager.arrayContains(AppCompatDrawableManager.COLORFILTER_COLOR_BACKGROUND_MULTIPLY, arg7)) {
            v0 = PorterDuff$Mode.MULTIPLY;
            goto label_9;
        }
        else if(arg7 == drawable.abc_list_divider_mtrl_alpha) {
            v2 = 0x1010030;
            v1 = Math.round(40.799999f);
            arg7 = 1;
        }
        else if(arg7 == drawable.abc_dialog_material_background) {
        label_9:
            arg7 = 1;
            v1 = -1;
        }
        else {
            arg7 = 0;
            v1 = -1;
            v2 = 0;
        }

        if(arg7 != 0) {
            if(DrawableUtils.canSafelyMutateDrawable(arg8)) {
                arg8 = arg8.mutate();
            }

            arg8.setColorFilter(AppCompatDrawableManager.getPorterDuffColorFilter(ThemeUtils.getThemeAttrColor(arg6, v2), v0));
            if(v1 != v3) {
                arg8.setAlpha(v1);
            }

            return 1;
        }

        return 0;
    }
}

