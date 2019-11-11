package android.support.graphics.drawable;

import android.animation.Animator$AnimatorListener;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources$Theme;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff$Mode;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable$Callback;
import android.graphics.drawable.Drawable$ConstantState;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.util.ArrayMap;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class AnimatedVectorDrawableCompat extends VectorDrawableCommon implements Animatable2Compat {
    class android.support.graphics.drawable.AnimatedVectorDrawableCompat$1 implements Drawable$Callback {
        android.support.graphics.drawable.AnimatedVectorDrawableCompat$1(AnimatedVectorDrawableCompat arg1) {
            AnimatedVectorDrawableCompat.this = arg1;
            super();
        }

        public void invalidateDrawable(Drawable arg1) {
            AnimatedVectorDrawableCompat.this.invalidateSelf();
        }

        public void scheduleDrawable(Drawable arg1, Runnable arg2, long arg3) {
            AnimatedVectorDrawableCompat.this.scheduleSelf(arg2, arg3);
        }

        public void unscheduleDrawable(Drawable arg1, Runnable arg2) {
            AnimatedVectorDrawableCompat.this.unscheduleSelf(arg2);
        }
    }

    class AnimatedVectorDrawableCompatState extends Drawable$ConstantState {
        AnimatorSet mAnimatorSet;
        private ArrayList mAnimators;
        int mChangingConfigurations;
        ArrayMap mTargetNameMap;
        VectorDrawableCompat mVectorDrawable;

        public AnimatedVectorDrawableCompatState(Context arg3, AnimatedVectorDrawableCompatState arg4, Drawable$Callback arg5, Resources arg6) {
            super();
            if(arg4 != null) {
                this.mChangingConfigurations = arg4.mChangingConfigurations;
                int v0 = 0;
                if(arg4.mVectorDrawable != null) {
                    Drawable$ConstantState v3 = arg4.mVectorDrawable.getConstantState();
                    this.mVectorDrawable = arg6 != null ? v3.newDrawable(arg6) : v3.newDrawable();
                    this.mVectorDrawable = this.mVectorDrawable.mutate();
                    this.mVectorDrawable.setCallback(arg5);
                    this.mVectorDrawable.setBounds(arg4.mVectorDrawable.getBounds());
                    this.mVectorDrawable.setAllowCaching(false);
                }

                if(arg4.mAnimators == null) {
                    return;
                }

                int v3_1 = arg4.mAnimators.size();
                this.mAnimators = new ArrayList(v3_1);
                this.mTargetNameMap = new ArrayMap(v3_1);
                while(v0 < v3_1) {
                    Object v5 = arg4.mAnimators.get(v0);
                    Animator v6 = ((Animator)v5).clone();
                    v5 = arg4.mTargetNameMap.get(v5);
                    v6.setTarget(this.mVectorDrawable.getTargetByName(((String)v5)));
                    this.mAnimators.add(v6);
                    this.mTargetNameMap.put(v6, v5);
                    ++v0;
                }

                this.setupAnimatorSet();
            }
        }

        static ArrayList access$000(AnimatedVectorDrawableCompatState arg0) {
            return arg0.mAnimators;
        }

        static ArrayList access$002(AnimatedVectorDrawableCompatState arg0, ArrayList arg1) {
            arg0.mAnimators = arg1;
            return arg1;
        }

        public int getChangingConfigurations() {
            return this.mChangingConfigurations;
        }

        public Drawable newDrawable() {
            throw new IllegalStateException("No constant state support for SDK < 24.");
        }

        public Drawable newDrawable(Resources arg2) {
            throw new IllegalStateException("No constant state support for SDK < 24.");
        }

        public void setupAnimatorSet() {
            if(this.mAnimatorSet == null) {
                this.mAnimatorSet = new AnimatorSet();
            }

            this.mAnimatorSet.playTogether(this.mAnimators);
        }
    }

    @RequiresApi(value=24) class AnimatedVectorDrawableDelegateState extends Drawable$ConstantState {
        private final Drawable$ConstantState mDelegateState;

        public AnimatedVectorDrawableDelegateState(Drawable$ConstantState arg1) {
            super();
            this.mDelegateState = arg1;
        }

        public boolean canApplyTheme() {
            return this.mDelegateState.canApplyTheme();
        }

        public int getChangingConfigurations() {
            return this.mDelegateState.getChangingConfigurations();
        }

        public Drawable newDrawable() {
            AnimatedVectorDrawableCompat v0 = new AnimatedVectorDrawableCompat();
            v0.mDelegateDrawable = this.mDelegateState.newDrawable();
            v0.mDelegateDrawable.setCallback(v0.mCallback);
            return ((Drawable)v0);
        }

        public Drawable newDrawable(Resources arg3) {
            AnimatedVectorDrawableCompat v0 = new AnimatedVectorDrawableCompat();
            v0.mDelegateDrawable = this.mDelegateState.newDrawable(arg3);
            v0.mDelegateDrawable.setCallback(v0.mCallback);
            return ((Drawable)v0);
        }

        public Drawable newDrawable(Resources arg3, Resources$Theme arg4) {
            AnimatedVectorDrawableCompat v0 = new AnimatedVectorDrawableCompat();
            v0.mDelegateDrawable = this.mDelegateState.newDrawable(arg3, arg4);
            v0.mDelegateDrawable.setCallback(v0.mCallback);
            return ((Drawable)v0);
        }
    }

    private static final String ANIMATED_VECTOR = "animated-vector";
    private static final boolean DBG_ANIMATION_VECTOR_DRAWABLE = false;
    private static final String LOGTAG = "AnimatedVDCompat";
    private static final String TARGET = "target";
    private AnimatedVectorDrawableCompatState mAnimatedVectorState;
    private ArrayList mAnimationCallbacks;
    private Animator$AnimatorListener mAnimatorListener;
    private ArgbEvaluator mArgbEvaluator;
    AnimatedVectorDrawableDelegateState mCachedConstantStateDelegate;
    final Drawable$Callback mCallback;
    private Context mContext;

    AnimatedVectorDrawableCompat() {
        this(null, null, null);
    }

    private AnimatedVectorDrawableCompat(@Nullable Context arg3, @Nullable AnimatedVectorDrawableCompatState arg4, @Nullable Resources arg5) {
        super();
        this.mArgbEvaluator = null;
        this.mAnimatorListener = null;
        this.mAnimationCallbacks = null;
        this.mCallback = new android.support.graphics.drawable.AnimatedVectorDrawableCompat$1(this);
        this.mContext = arg3;
        this.mAnimatedVectorState = arg4 != null ? arg4 : new AnimatedVectorDrawableCompatState(arg3, arg4, this.mCallback, arg5);
    }

    private AnimatedVectorDrawableCompat(@Nullable Context arg2) {
        this(arg2, null, null);
    }

    static ArrayList access$100(AnimatedVectorDrawableCompat arg0) {
        return arg0.mAnimationCallbacks;
    }

    public void applyTheme(Resources$Theme arg2) {
        if(this.mDelegateDrawable != null) {
            DrawableCompat.applyTheme(this.mDelegateDrawable, arg2);
            return;
        }
    }

    public boolean canApplyTheme() {
        if(this.mDelegateDrawable != null) {
            return DrawableCompat.canApplyTheme(this.mDelegateDrawable);
        }

        return 0;
    }

    public static void clearAnimationCallbacks(Drawable arg2) {
        if(arg2 != null) {
            if(!(arg2 instanceof Animatable)) {
            }
            else {
                if(Build$VERSION.SDK_INT >= 24) {
                    ((AnimatedVectorDrawable)arg2).clearAnimationCallbacks();
                }
                else {
                    ((AnimatedVectorDrawableCompat)arg2).clearAnimationCallbacks();
                }

                return;
            }
        }
    }

    public void clearAnimationCallbacks() {
        if(this.mDelegateDrawable != null) {
            this.mDelegateDrawable.clearAnimationCallbacks();
            return;
        }

        this.removeAnimatorSetListener();
        if(this.mAnimationCallbacks == null) {
            return;
        }

        this.mAnimationCallbacks.clear();
    }

    public void clearColorFilter() {
        super.clearColorFilter();
    }

    @Nullable public static AnimatedVectorDrawableCompat create(@NonNull Context arg4, @DrawableRes int arg5) {
        if(Build$VERSION.SDK_INT >= 24) {
            AnimatedVectorDrawableCompat v0 = new AnimatedVectorDrawableCompat(arg4);
            v0.mDelegateDrawable = ResourcesCompat.getDrawable(arg4.getResources(), arg5, arg4.getTheme());
            v0.mDelegateDrawable.setCallback(v0.mCallback);
            v0.mCachedConstantStateDelegate = new AnimatedVectorDrawableDelegateState(v0.mDelegateDrawable.getConstantState());
            return v0;
        }

        Resources v0_1 = arg4.getResources();
        try {
            XmlResourceParser v5 = v0_1.getXml(arg5);
            AttributeSet v0_2 = Xml.asAttributeSet(((XmlPullParser)v5));
            while(true) {
                int v1 = ((XmlPullParser)v5).next();
                int v2 = 2;
                if(v1 != v2 && v1 != 1) {
                    continue;
                }

                break;
            }

            if(v1 != v2) {
                throw new XmlPullParserException("No start tag found");
            }

            return AnimatedVectorDrawableCompat.createFromXmlInner(arg4, arg4.getResources(), ((XmlPullParser)v5), v0_2, arg4.getTheme());
        }
        catch(IOException v4) {
            Log.e("AnimatedVDCompat", "parser error", ((Throwable)v4));
        }
        catch(XmlPullParserException v4_1) {
            Log.e("AnimatedVDCompat", "parser error", ((Throwable)v4_1));
        }

        return null;
    }

    public static AnimatedVectorDrawableCompat createFromXmlInner(Context arg1, Resources arg2, XmlPullParser arg3, AttributeSet arg4, Resources$Theme arg5) throws XmlPullParserException, IOException {
        AnimatedVectorDrawableCompat v0 = new AnimatedVectorDrawableCompat(arg1);
        v0.inflate(arg2, arg3, arg4, arg5);
        return v0;
    }

    public void draw(Canvas arg2) {
        if(this.mDelegateDrawable != null) {
            this.mDelegateDrawable.draw(arg2);
            return;
        }

        this.mAnimatedVectorState.mVectorDrawable.draw(arg2);
        if(this.mAnimatedVectorState.mAnimatorSet.isStarted()) {
            this.invalidateSelf();
        }
    }

    public int getAlpha() {
        if(this.mDelegateDrawable != null) {
            return DrawableCompat.getAlpha(this.mDelegateDrawable);
        }

        return this.mAnimatedVectorState.mVectorDrawable.getAlpha();
    }

    public int getChangingConfigurations() {
        if(this.mDelegateDrawable != null) {
            return this.mDelegateDrawable.getChangingConfigurations();
        }

        return super.getChangingConfigurations() | this.mAnimatedVectorState.mChangingConfigurations;
    }

    public ColorFilter getColorFilter() {
        return super.getColorFilter();
    }

    public Drawable$ConstantState getConstantState() {
        if(this.mDelegateDrawable != null && Build$VERSION.SDK_INT >= 24) {
            return new AnimatedVectorDrawableDelegateState(this.mDelegateDrawable.getConstantState());
        }

        return null;
    }

    public Drawable getCurrent() {
        return super.getCurrent();
    }

    public int getIntrinsicHeight() {
        if(this.mDelegateDrawable != null) {
            return this.mDelegateDrawable.getIntrinsicHeight();
        }

        return this.mAnimatedVectorState.mVectorDrawable.getIntrinsicHeight();
    }

    public int getIntrinsicWidth() {
        if(this.mDelegateDrawable != null) {
            return this.mDelegateDrawable.getIntrinsicWidth();
        }

        return this.mAnimatedVectorState.mVectorDrawable.getIntrinsicWidth();
    }

    public int getMinimumHeight() {
        return super.getMinimumHeight();
    }

    public int getMinimumWidth() {
        return super.getMinimumWidth();
    }

    public int getOpacity() {
        if(this.mDelegateDrawable != null) {
            return this.mDelegateDrawable.getOpacity();
        }

        return this.mAnimatedVectorState.mVectorDrawable.getOpacity();
    }

    public boolean getPadding(Rect arg1) {
        return super.getPadding(arg1);
    }

    public int[] getState() {
        return super.getState();
    }

    public Region getTransparentRegion() {
        return super.getTransparentRegion();
    }

    public void inflate(Resources arg7, XmlPullParser arg8, AttributeSet arg9, Resources$Theme arg10) throws XmlPullParserException, IOException {
        TypedArray v0_2;
        if(this.mDelegateDrawable != null) {
            DrawableCompat.inflate(this.mDelegateDrawable, arg7, arg8, arg9, arg10);
            return;
        }

        int v0 = arg8.getEventType();
        int v1 = arg8.getDepth() + 1;
        while(v0 != 1) {
            if(arg8.getDepth() < v1 && v0 == 3) {
                break;
            }

            if(v0 == 2) {
                String v0_1 = arg8.getName();
                if("animated-vector".equals(v0_1)) {
                    v0_2 = TypedArrayUtils.obtainAttributes(arg7, arg10, arg9, AndroidResources.STYLEABLE_ANIMATED_VECTOR_DRAWABLE);
                    int v3 = v0_2.getResourceId(0, 0);
                    if(v3 != 0) {
                        VectorDrawableCompat v3_1 = VectorDrawableCompat.create(arg7, v3, arg10);
                        v3_1.setAllowCaching(false);
                        v3_1.setCallback(this.mCallback);
                        if(this.mAnimatedVectorState.mVectorDrawable != null) {
                            this.mAnimatedVectorState.mVectorDrawable.setCallback(null);
                        }

                        this.mAnimatedVectorState.mVectorDrawable = v3_1;
                    }

                    v0_2.recycle();
                }
                else {
                    if(!"target".equals(v0_1)) {
                        goto label_60;
                    }

                    v0_2 = arg7.obtainAttributes(arg9, AndroidResources.STYLEABLE_ANIMATED_VECTOR_DRAWABLE_TARGET);
                    String v3_2 = v0_2.getString(0);
                    int v4 = v0_2.getResourceId(1, 0);
                    if(v4 != 0) {
                        if(this.mContext != null) {
                            this.setupAnimatorsForTarget(v3_2, AnimatorInflaterCompat.loadAnimator(this.mContext, v4));
                        }
                        else {
                            v0_2.recycle();
                            throw new IllegalStateException("Context can\'t be null when inflating animators");
                        }
                    }

                    v0_2.recycle();
                }
            }

        label_60:
            v0 = arg8.next();
        }

        this.mAnimatedVectorState.setupAnimatorSet();
    }

    public void inflate(Resources arg2, XmlPullParser arg3, AttributeSet arg4) throws XmlPullParserException, IOException {
        this.inflate(arg2, arg3, arg4, null);
    }

    public boolean isAutoMirrored() {
        if(this.mDelegateDrawable != null) {
            return DrawableCompat.isAutoMirrored(this.mDelegateDrawable);
        }

        return this.mAnimatedVectorState.mVectorDrawable.isAutoMirrored();
    }

    public boolean isRunning() {
        if(this.mDelegateDrawable != null) {
            return this.mDelegateDrawable.isRunning();
        }

        return this.mAnimatedVectorState.mAnimatorSet.isRunning();
    }

    public boolean isStateful() {
        if(this.mDelegateDrawable != null) {
            return this.mDelegateDrawable.isStateful();
        }

        return this.mAnimatedVectorState.mVectorDrawable.isStateful();
    }

    public void jumpToCurrentState() {
        super.jumpToCurrentState();
    }

    public Drawable mutate() {
        if(this.mDelegateDrawable != null) {
            this.mDelegateDrawable.mutate();
        }

        return this;
    }

    protected void onBoundsChange(Rect arg2) {
        if(this.mDelegateDrawable != null) {
            this.mDelegateDrawable.setBounds(arg2);
            return;
        }

        this.mAnimatedVectorState.mVectorDrawable.setBounds(arg2);
    }

    protected boolean onLevelChange(int arg2) {
        if(this.mDelegateDrawable != null) {
            return this.mDelegateDrawable.setLevel(arg2);
        }

        return this.mAnimatedVectorState.mVectorDrawable.setLevel(arg2);
    }

    protected boolean onStateChange(int[] arg2) {
        if(this.mDelegateDrawable != null) {
            return this.mDelegateDrawable.setState(arg2);
        }

        return this.mAnimatedVectorState.mVectorDrawable.setState(arg2);
    }

    public static void registerAnimationCallback(Drawable arg2, AnimationCallback arg3) {
        if(arg2 != null) {
            if(arg3 == null) {
            }
            else if(!(arg2 instanceof Animatable)) {
                return;
            }
            else {
                if(Build$VERSION.SDK_INT >= 24) {
                    AnimatedVectorDrawableCompat.registerPlatformCallback(((AnimatedVectorDrawable)arg2), arg3);
                }
                else {
                    ((AnimatedVectorDrawableCompat)arg2).registerAnimationCallback(arg3);
                }

                return;
            }
        }
    }

    public void registerAnimationCallback(@NonNull AnimationCallback arg2) {
        if(this.mDelegateDrawable != null) {
            AnimatedVectorDrawableCompat.registerPlatformCallback(this.mDelegateDrawable, arg2);
            return;
        }

        if(arg2 == null) {
            return;
        }

        if(this.mAnimationCallbacks == null) {
            this.mAnimationCallbacks = new ArrayList();
        }

        if(this.mAnimationCallbacks.contains(arg2)) {
            return;
        }

        this.mAnimationCallbacks.add(arg2);
        if(this.mAnimatorListener == null) {
            this.mAnimatorListener = new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator arg5) {
                    ArrayList v5 = new ArrayList(AnimatedVectorDrawableCompat.this.mAnimationCallbacks);
                    int v0 = v5.size();
                    int v1;
                    for(v1 = 0; v1 < v0; ++v1) {
                        v5.get(v1).onAnimationEnd(AnimatedVectorDrawableCompat.this);
                    }
                }

                public void onAnimationStart(Animator arg5) {
                    ArrayList v5 = new ArrayList(AnimatedVectorDrawableCompat.this.mAnimationCallbacks);
                    int v0 = v5.size();
                    int v1;
                    for(v1 = 0; v1 < v0; ++v1) {
                        v5.get(v1).onAnimationStart(AnimatedVectorDrawableCompat.this);
                    }
                }
            };
        }

        this.mAnimatedVectorState.mAnimatorSet.addListener(this.mAnimatorListener);
    }

    @RequiresApi(value=23) private static void registerPlatformCallback(@NonNull AnimatedVectorDrawable arg0, @NonNull AnimationCallback arg1) {
        arg0.registerAnimationCallback(arg1.getPlatformCallback());
    }

    private void removeAnimatorSetListener() {
        if(this.mAnimatorListener != null) {
            this.mAnimatedVectorState.mAnimatorSet.removeListener(this.mAnimatorListener);
            this.mAnimatorListener = null;
        }
    }

    public void setAlpha(int arg2) {
        if(this.mDelegateDrawable != null) {
            this.mDelegateDrawable.setAlpha(arg2);
            return;
        }

        this.mAnimatedVectorState.mVectorDrawable.setAlpha(arg2);
    }

    public void setAutoMirrored(boolean arg2) {
        if(this.mDelegateDrawable != null) {
            DrawableCompat.setAutoMirrored(this.mDelegateDrawable, arg2);
            return;
        }

        this.mAnimatedVectorState.mVectorDrawable.setAutoMirrored(arg2);
    }

    public void setChangingConfigurations(int arg1) {
        super.setChangingConfigurations(arg1);
    }

    public void setColorFilter(int arg1, PorterDuff$Mode arg2) {
        super.setColorFilter(arg1, arg2);
    }

    public void setColorFilter(ColorFilter arg2) {
        if(this.mDelegateDrawable != null) {
            this.mDelegateDrawable.setColorFilter(arg2);
            return;
        }

        this.mAnimatedVectorState.mVectorDrawable.setColorFilter(arg2);
    }

    public void setFilterBitmap(boolean arg1) {
        super.setFilterBitmap(arg1);
    }

    public void setHotspot(float arg1, float arg2) {
        super.setHotspot(arg1, arg2);
    }

    public void setHotspotBounds(int arg1, int arg2, int arg3, int arg4) {
        super.setHotspotBounds(arg1, arg2, arg3, arg4);
    }

    public boolean setState(int[] arg1) {
        return super.setState(arg1);
    }

    public void setTint(int arg2) {
        if(this.mDelegateDrawable != null) {
            DrawableCompat.setTint(this.mDelegateDrawable, arg2);
            return;
        }

        this.mAnimatedVectorState.mVectorDrawable.setTint(arg2);
    }

    public void setTintList(ColorStateList arg2) {
        if(this.mDelegateDrawable != null) {
            DrawableCompat.setTintList(this.mDelegateDrawable, arg2);
            return;
        }

        this.mAnimatedVectorState.mVectorDrawable.setTintList(arg2);
    }

    public void setTintMode(PorterDuff$Mode arg2) {
        if(this.mDelegateDrawable != null) {
            DrawableCompat.setTintMode(this.mDelegateDrawable, arg2);
            return;
        }

        this.mAnimatedVectorState.mVectorDrawable.setTintMode(arg2);
    }

    public boolean setVisible(boolean arg2, boolean arg3) {
        if(this.mDelegateDrawable != null) {
            return this.mDelegateDrawable.setVisible(arg2, arg3);
        }

        this.mAnimatedVectorState.mVectorDrawable.setVisible(arg2, arg3);
        return super.setVisible(arg2, arg3);
    }

    private void setupAnimatorsForTarget(String arg3, Animator arg4) {
        arg4.setTarget(this.mAnimatedVectorState.mVectorDrawable.getTargetByName(arg3));
        if(Build$VERSION.SDK_INT < 21) {
            this.setupColorAnimator(arg4);
        }

        if(AnimatedVectorDrawableCompatState.access$000(this.mAnimatedVectorState) == null) {
            AnimatedVectorDrawableCompatState.access$002(this.mAnimatedVectorState, new ArrayList());
            this.mAnimatedVectorState.mTargetNameMap = new ArrayMap();
        }

        AnimatedVectorDrawableCompatState.access$000(this.mAnimatedVectorState).add(arg4);
        this.mAnimatedVectorState.mTargetNameMap.put(arg4, arg3);
    }

    private void setupColorAnimator(Animator arg4) {
        if((arg4 instanceof AnimatorSet)) {
            ArrayList v0 = arg4.getChildAnimations();
            if(v0 != null) {
                int v1;
                for(v1 = 0; v1 < ((List)v0).size(); ++v1) {
                    this.setupColorAnimator(((List)v0).get(v1));
                }
            }
        }

        if((arg4 instanceof ObjectAnimator)) {
            String v0_1 = ((ObjectAnimator)arg4).getPropertyName();
            if(!"fillColor".equals(v0_1) && !"strokeColor".equals(v0_1)) {
                return;
            }

            if(this.mArgbEvaluator == null) {
                this.mArgbEvaluator = new ArgbEvaluator();
            }

            ((ObjectAnimator)arg4).setEvaluator(this.mArgbEvaluator);
        }
    }

    public void start() {
        if(this.mDelegateDrawable != null) {
            this.mDelegateDrawable.start();
            return;
        }

        if(this.mAnimatedVectorState.mAnimatorSet.isStarted()) {
            return;
        }

        this.mAnimatedVectorState.mAnimatorSet.start();
        this.invalidateSelf();
    }

    public void stop() {
        if(this.mDelegateDrawable != null) {
            this.mDelegateDrawable.stop();
            return;
        }

        this.mAnimatedVectorState.mAnimatorSet.end();
    }

    public static boolean unregisterAnimationCallback(Drawable arg2, AnimationCallback arg3) {
        if(arg2 != null) {
            if(arg3 == null) {
            }
            else if(!(arg2 instanceof Animatable)) {
                return 0;
            }
            else if(Build$VERSION.SDK_INT >= 24) {
                return AnimatedVectorDrawableCompat.unregisterPlatformCallback(((AnimatedVectorDrawable)arg2), arg3);
            }
            else {
                return ((AnimatedVectorDrawableCompat)arg2).unregisterAnimationCallback(arg3);
            }
        }

        return 0;
    }

    public boolean unregisterAnimationCallback(@NonNull AnimationCallback arg2) {
        if(this.mDelegateDrawable != null) {
            AnimatedVectorDrawableCompat.unregisterPlatformCallback(this.mDelegateDrawable, arg2);
        }

        if(this.mAnimationCallbacks != null) {
            if(arg2 == null) {
            }
            else {
                boolean v2 = this.mAnimationCallbacks.remove(arg2);
                if(this.mAnimationCallbacks.size() == 0) {
                    this.removeAnimatorSetListener();
                }

                return v2;
            }
        }

        return 0;
    }

    @RequiresApi(value=23) private static boolean unregisterPlatformCallback(AnimatedVectorDrawable arg0, AnimationCallback arg1) {
        return arg0.unregisterAnimationCallback(arg1.getPlatformCallback());
    }
}

