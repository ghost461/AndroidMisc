package android.support.graphics.drawable;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources$NotFoundException;
import android.content.res.Resources$Theme;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Build$VERSION;
import android.support.annotation.AnimatorRes;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v4.graphics.PathParser$PathDataNode;
import android.support.v4.graphics.PathParser;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.util.Xml;
import android.view.InflateException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

@RestrictTo(value={Scope.LIBRARY_GROUP}) public class AnimatorInflaterCompat {
    class android.support.graphics.drawable.AnimatorInflaterCompat$1 {
    }

    class PathDataEvaluator implements TypeEvaluator {
        private PathDataNode[] mNodeArray;

        PathDataEvaluator(android.support.graphics.drawable.AnimatorInflaterCompat$1 arg1) {
            this();
        }

        private PathDataEvaluator() {
            super();
        }

        PathDataEvaluator(PathDataNode[] arg1) {
            super();
            this.mNodeArray = arg1;
        }

        public Object evaluate(float arg1, Object arg2, Object arg3) {
            return this.evaluate(arg1, ((PathDataNode[])arg2), ((PathDataNode[])arg3));
        }

        public PathDataNode[] evaluate(float arg5, PathDataNode[] arg6, PathDataNode[] arg7) {
            if(!PathParser.canMorph(arg6, arg7)) {
                throw new IllegalArgumentException("Can\'t interpolate between two incompatible pathData");
            }

            if(this.mNodeArray == null || !PathParser.canMorph(this.mNodeArray, arg6)) {
                this.mNodeArray = PathParser.deepCopyNodes(arg6);
            }

            int v0;
            for(v0 = 0; v0 < arg6.length; ++v0) {
                this.mNodeArray[v0].interpolatePathDataNode(arg6[v0], arg7[v0], arg5);
            }

            return this.mNodeArray;
        }
    }

    private static final boolean DBG_ANIMATOR_INFLATER = false;
    private static final int MAX_NUM_POINTS = 100;
    private static final String TAG = "AnimatorInflater";
    private static final int TOGETHER = 0;
    private static final int VALUE_TYPE_COLOR = 3;
    private static final int VALUE_TYPE_FLOAT = 0;
    private static final int VALUE_TYPE_INT = 1;
    private static final int VALUE_TYPE_PATH = 2;
    private static final int VALUE_TYPE_UNDEFINED = 4;

    public AnimatorInflaterCompat() {
        super();
    }

    private static Animator createAnimatorFromXml(Context arg8, Resources arg9, Resources$Theme arg10, XmlPullParser arg11, float arg12) throws XmlPullParserException, IOException {
        return AnimatorInflaterCompat.createAnimatorFromXml(arg8, arg9, arg10, arg11, Xml.asAttributeSet(arg11), null, 0, arg12);
    }

    private static Animator createAnimatorFromXml(Context arg18, Resources arg19, Resources$Theme arg20, XmlPullParser arg21, AttributeSet arg22, AnimatorSet arg23, int arg24, float arg25) throws XmlPullParserException, IOException {
        Resources v8 = arg19;
        Resources$Theme v9 = arg20;
        XmlPullParser v10 = arg21;
        AnimatorSet v11 = arg23;
        int v12 = arg21.getDepth();
        ObjectAnimator v0 = null;
        ArrayList v13 = ((ArrayList)v0);
        while(true) {
            int v1 = arg21.next();
            int v14 = 0;
            if((v1 != 3 || arg21.getDepth() > v12) && v1 != 1) {
                if(v1 != 2) {
                }
                else {
                    String v1_1 = arg21.getName();
                    if(v1_1.equals("objectAnimator")) {
                        v0 = AnimatorInflaterCompat.loadObjectAnimator(arg18, v8, v9, arg22, arg25, v10);
                    }
                    else if(v1_1.equals("animator")) {
                        ValueAnimator v0_1 = AnimatorInflaterCompat.loadAnimator(arg18, v8, v9, arg22, null, arg25, v10);
                    }
                    else if(v1_1.equals("set")) {
                        AnimatorSet v15 = new AnimatorSet();
                        TypedArray v6 = TypedArrayUtils.obtainAttributes(v8, v9, arg22, AndroidResources.STYLEABLE_ANIMATOR_SET);
                        AnimatorInflaterCompat.createAnimatorFromXml(arg18, v8, v9, v10, arg22, v15, TypedArrayUtils.getNamedInt(v6, v10, "ordering", 0, 0), arg25);
                        v6.recycle();
                        AnimatorSet v0_2 = v15;
                        v14 = 0;
                    }
                    else if(v1_1.equals("propertyValuesHolder")) {
                        PropertyValuesHolder[] v1_2 = AnimatorInflaterCompat.loadValues(arg18, v8, v9, v10, Xml.asAttributeSet(arg21));
                        if(v1_2 != null && v0 != null && ((v0 instanceof ValueAnimator))) {
                            v0.setValues(v1_2);
                        }

                        v14 = 1;
                    }
                    else {
                        break;
                    }

                    if(v11 == null) {
                        continue;
                    }

                    if(v14 != 0) {
                        continue;
                    }

                    if(v13 == null) {
                        v13 = new ArrayList();
                    }

                    v13.add(v0);
                }

                continue;
            }

            goto label_98;
        }

        StringBuilder v1_3 = new StringBuilder();
        v1_3.append("Unknown animator name: ");
        v1_3.append(arg21.getName());
        throw new RuntimeException(v1_3.toString());
    label_98:
        if(v11 != null && v13 != null) {
            Animator[] v1_4 = new Animator[v13.size()];
            Iterator v2 = v13.iterator();
            int v17;
            for(v17 = 0; v2.hasNext(); ++v17) {
                v1_4[v17] = v2.next();
            }

            if(arg24 == 0) {
                v11.playTogether(v1_4);
            }
            else {
                v11.playSequentially(v1_4);
            }
        }

        return ((Animator)v0);
    }

    private static Keyframe createNewKeyframe(Keyframe arg2, float arg3) {
        if(arg2.getType() == Float.TYPE) {
            arg2 = Keyframe.ofFloat(arg3);
        }
        else if(arg2.getType() == Integer.TYPE) {
            arg2 = Keyframe.ofInt(arg3);
        }
        else {
            arg2 = Keyframe.ofObject(arg3);
        }

        return arg2;
    }

    private static void distributeKeyframes(Keyframe[] arg2, float arg3, int arg4, int arg5) {
        arg3 /= ((float)(arg5 - arg4 + 2));
        while(arg4 <= arg5) {
            arg2[arg4].setFraction(arg2[arg4 - 1].getFraction() + arg3);
            ++arg4;
        }
    }

    private static void dumpKeyframes(Object[] arg6, String arg7) {
        String v1_1;
        Float v4_1;
        if(arg6 != null) {
            if(arg6.length == 0) {
            }
            else {
                Log.d("AnimatorInflater", arg7);
                int v7 = arg6.length;
                int v0;
                for(v0 = 0; v0 < v7; ++v0) {
                    Object v1 = arg6[v0];
                    String v2 = "AnimatorInflater";
                    StringBuilder v3 = new StringBuilder();
                    v3.append("Keyframe ");
                    v3.append(v0);
                    v3.append(": fraction ");
                    if(((Keyframe)v1).getFraction() < 0f) {
                        String v4 = "null";
                    }
                    else {
                        v4_1 = Float.valueOf(((Keyframe)v1).getFraction());
                    }

                    v3.append(v4_1);
                    v3.append(", ");
                    v3.append(", value : ");
                    if(((Keyframe)v1).hasValue()) {
                        v1 = ((Keyframe)v1).getValue();
                    }
                    else {
                        v1_1 = "null";
                    }

                    v3.append(v1_1);
                    Log.d(v2, v3.toString());
                }

                return;
            }
        }
    }

    private static PropertyValuesHolder getPVH(TypedArray arg11, int arg12, int arg13, int arg14, String arg15) {
        int v11_4;
        float v11_3;
        PropertyValuesHolder v9_1;
        PropertyValuesHolder v11_2;
        TypedValue v0 = arg11.peekValue(arg13);
        int v3 = v0 != null ? 1 : 0;
        int v0_1 = v3 != 0 ? v0.type : 0;
        TypedValue v4 = arg11.peekValue(arg14);
        int v5 = v4 != null ? 1 : 0;
        int v4_1 = v5 != 0 ? v4.type : 0;
        int v7 = 3;
        if(arg12 == 4) {
            if(v3 != 0 && (AnimatorInflaterCompat.isColorType(v0_1)) || v5 != 0 && (AnimatorInflaterCompat.isColorType(v4_1))) {
                arg12 = 3;
                goto label_32;
            }

            arg12 = 0;
        }

    label_32:
        int v6 = arg12 == 0 ? 1 : 0;
        int v8 = 2;
        android.support.graphics.drawable.AnimatorInflaterCompat$1 v9 = null;
        if(arg12 == v8) {
            String v12 = arg11.getString(arg13);
            String v11 = arg11.getString(arg14);
            PathDataNode[] v13 = PathParser.createNodesFromPathData(v12);
            PathDataNode[] v14 = PathParser.createNodesFromPathData(v11);
            if(v13 == null && v14 == null) {
                goto label_160;
            }

            if(v13 != null) {
                PathDataEvaluator v0_2 = new PathDataEvaluator(v9);
                if(v14 == null) {
                    v11_2 = PropertyValuesHolder.ofObject(arg15, ((TypeEvaluator)v0_2), new Object[]{v13});
                }
                else if(!PathParser.canMorph(v13, v14)) {
                    StringBuilder v14_1 = new StringBuilder();
                    v14_1.append(" Can\'t morph from ");
                    v14_1.append(v12);
                    v14_1.append(" to ");
                    v14_1.append(v11);
                    throw new InflateException(v14_1.toString());
                }
                else {
                    Object[] v11_1 = new Object[v8];
                    v11_1[0] = v13;
                    v11_1[1] = v14;
                    v11_2 = PropertyValuesHolder.ofObject(arg15, ((TypeEvaluator)v0_2), v11_1);
                }

                v9_1 = v11_2;
                goto label_160;
            }

            if(v14 == null) {
                goto label_160;
            }

            v9_1 = PropertyValuesHolder.ofObject(arg15, new PathDataEvaluator(v9), new Object[]{v14});
        }
        else {
            ArgbEvaluator v12_1 = arg12 == v7 ? ArgbEvaluator.getInstance() : ((ArgbEvaluator)v9);
            v7 = 5;
            if(v6 != 0) {
                if(v3 != 0) {
                    float v13_1 = v0_1 == v7 ? arg11.getDimension(arg13, 0f) : arg11.getFloat(arg13, 0f);
                    if(v5 != 0) {
                        v11_3 = v4_1 == v7 ? arg11.getDimension(arg14, 0f) : arg11.getFloat(arg14, 0f);
                        float[] v14_2 = new float[v8];
                        v14_2[0] = v13_1;
                        v14_2[1] = v11_3;
                        v11_2 = PropertyValuesHolder.ofFloat(arg15, v14_2);
                        goto label_113;
                    }

                    v11_2 = PropertyValuesHolder.ofFloat(arg15, new float[]{v13_1});
                }
                else {
                    v11_3 = v4_1 == v7 ? arg11.getDimension(arg14, 0f) : arg11.getFloat(arg14, 0f);
                    v11_2 = PropertyValuesHolder.ofFloat(arg15, new float[]{v11_3});
                }

            label_113:
                v9_1 = v11_2;
            }
            else {
                if(v3 != 0) {
                    if(v0_1 == v7) {
                        arg13 = ((int)arg11.getDimension(arg13, 0f));
                    }
                    else if(AnimatorInflaterCompat.isColorType(v0_1)) {
                        arg13 = arg11.getColor(arg13, 0);
                    }
                    else {
                        arg13 = arg11.getInt(arg13, 0);
                    }

                    if(v5 != 0) {
                        if(v4_1 == v7) {
                            v11_4 = ((int)arg11.getDimension(arg14, 0f));
                        }
                        else if(AnimatorInflaterCompat.isColorType(v4_1)) {
                            v11_4 = arg11.getColor(arg14, 0);
                        }
                        else {
                            v11_4 = arg11.getInt(arg14, 0);
                        }

                        int[] v14_3 = new int[v8];
                        v14_3[0] = arg13;
                        v14_3[1] = v11_4;
                        v9_1 = PropertyValuesHolder.ofInt(arg15, v14_3);
                        goto label_157;
                    }

                    v9_1 = PropertyValuesHolder.ofInt(arg15, new int[]{arg13});
                    goto label_157;
                }

                if(v5 == 0) {
                    goto label_157;
                }

                if(v4_1 == v7) {
                    v11_4 = ((int)arg11.getDimension(arg14, 0f));
                }
                else if(AnimatorInflaterCompat.isColorType(v4_1)) {
                    v11_4 = arg11.getColor(arg14, 0);
                }
                else {
                    v11_4 = arg11.getInt(arg14, 0);
                }

                v9_1 = PropertyValuesHolder.ofInt(arg15, new int[]{v11_4});
            }

        label_157:
            if((((PropertyValuesHolder)v9)) == null) {
                goto label_160;
            }

            if(v12_1 == null) {
                goto label_160;
            }

            ((PropertyValuesHolder)v9).setEvaluator(((TypeEvaluator)v12_1));
        }

    label_160:
        return ((PropertyValuesHolder)v9);
    }

    private static int inferValueTypeFromValues(TypedArray arg3, int arg4, int arg5) {
        TypedValue v4 = arg3.peekValue(arg4);
        int v0 = 1;
        int v1 = 0;
        int v2 = v4 != null ? 1 : 0;
        arg4 = v2 != 0 ? v4.type : 0;
        TypedValue v3 = arg3.peekValue(arg5);
        if(v3 != null) {
        }
        else {
            v0 = 0;
        }

        int v3_1 = v0 != 0 ? v3.type : 0;
        if(v2 != 0 && (AnimatorInflaterCompat.isColorType(arg4)) || v0 != 0 && (AnimatorInflaterCompat.isColorType(v3_1))) {
            v1 = 3;
        }

        return v1;
    }

    private static int inferValueTypeOfKeyframe(Resources arg1, Resources$Theme arg2, AttributeSet arg3, XmlPullParser arg4) {
        TypedArray v1 = TypedArrayUtils.obtainAttributes(arg1, arg2, arg3, AndroidResources.STYLEABLE_KEYFRAME);
        int v3 = 0;
        TypedValue v2 = TypedArrayUtils.peekNamedValue(v1, arg4, "value", 0);
        int v4 = v2 != null ? 1 : 0;
        if(v4 != 0 && (AnimatorInflaterCompat.isColorType(v2.type))) {
            v3 = 3;
        }

        v1.recycle();
        return v3;
    }

    private static boolean isColorType(int arg1) {
        boolean v1 = arg1 < 28 || arg1 > 0x1F ? false : true;
        return v1;
    }

    private static ValueAnimator loadAnimator(Context arg2, Resources arg3, Resources$Theme arg4, AttributeSet arg5, ValueAnimator arg6, float arg7, XmlPullParser arg8) throws Resources$NotFoundException {
        TypedArray v0 = TypedArrayUtils.obtainAttributes(arg3, arg4, arg5, AndroidResources.STYLEABLE_ANIMATOR);
        TypedArray v3 = TypedArrayUtils.obtainAttributes(arg3, arg4, arg5, AndroidResources.STYLEABLE_PROPERTY_ANIMATOR);
        if(arg6 == null) {
            arg6 = new ValueAnimator();
        }

        AnimatorInflaterCompat.parseAnimatorFromTypeArray(arg6, v0, v3, arg7, arg8);
        int v4 = TypedArrayUtils.getNamedResourceId(v0, arg8, "interpolator", 0, 0);
        if(v4 > 0) {
            arg6.setInterpolator(AnimationUtilsCompat.loadInterpolator(arg2, v4));
        }

        v0.recycle();
        if(v3 != null) {
            v3.recycle();
        }

        return arg6;
    }

    public static Animator loadAnimator(Context arg2, @AnimatorRes int arg3) throws Resources$NotFoundException {
        Animator v2 = Build$VERSION.SDK_INT >= 24 ? AnimatorInflater.loadAnimator(arg2, arg3) : AnimatorInflaterCompat.loadAnimator(arg2, arg2.getResources(), arg2.getTheme(), arg3);
        return v2;
    }

    public static Animator loadAnimator(Context arg1, Resources arg2, Resources$Theme arg3, @AnimatorRes int arg4) throws Resources$NotFoundException {
        return AnimatorInflaterCompat.loadAnimator(arg1, arg2, arg3, arg4, 1f);
    }

    public static Animator loadAnimator(Context arg2, Resources arg3, Resources$Theme arg4, @AnimatorRes int arg5, float arg6) throws Resources$NotFoundException {
        Resources$NotFoundException v3;
        StringBuilder v4;
        Animator v2_3;
        XmlResourceParser v1;
        XmlResourceParser v0 = null;
        try {
            v1 = arg3.getAnimation(arg5);
            goto label_2;
        }
        catch(Throwable v2) {
        }
        catch(IOException v2_1) {
            goto label_18;
            try {
            label_2:
                v2_3 = AnimatorInflaterCompat.createAnimatorFromXml(arg2, arg3, arg4, ((XmlPullParser)v1), arg6);
                if(v1 == null) {
                    return v2_3;
                }

                goto label_4;
            }
            catch(Throwable v2) {
                v0 = v1;
            }
            catch(IOException v2_1) {
                v0 = v1;
                try {
                label_18:
                    v4 = new StringBuilder();
                    v4.append("Can\'t load animation resource ID #0x");
                    v4.append(Integer.toHexString(arg5));
                    v3 = new Resources$NotFoundException(v4.toString());
                    v3.initCause(((Throwable)v2_1));
                    throw v3;
                }
                catch(Throwable v2) {
                label_16:
                }
            }
            catch(XmlPullParserException v2_2) {
                v0 = v1;
                try {
                label_30:
                    v4 = new StringBuilder();
                    v4.append("Can\'t load animation resource ID #0x");
                    v4.append(Integer.toHexString(arg5));
                    v3 = new Resources$NotFoundException(v4.toString());
                    v3.initCause(((Throwable)v2_2));
                    throw v3;
                }
                catch(Throwable v2) {
                    goto label_16;
                }
            }
        }
        catch(XmlPullParserException v2_2) {
            goto label_30;
        }

        if(v0 != null) {
            v0.close();
        }

        throw v2;
    label_4:
        v1.close();
        return v2_3;
    }

    private static Keyframe loadKeyframe(Context arg5, Resources arg6, Resources$Theme arg7, AttributeSet arg8, int arg9, XmlPullParser arg10) throws XmlPullParserException, IOException {
        Keyframe v7_1;
        TypedArray v6 = TypedArrayUtils.obtainAttributes(arg6, arg7, arg8, AndroidResources.STYLEABLE_KEYFRAME);
        int v8 = 3;
        float v7 = TypedArrayUtils.getNamedFloat(v6, arg10, "fraction", v8, -1f);
        TypedValue v0 = TypedArrayUtils.peekNamedValue(v6, arg10, "value", 0);
        int v3 = v0 != null ? 1 : 0;
        if(arg9 == 4) {
            if(v3 != 0 && (AnimatorInflaterCompat.isColorType(v0.type))) {
                arg9 = 3;
                goto label_23;
            }

            arg9 = 0;
        }

    label_23:
        if(v3 != 0) {
            if(arg9 != v8) {
                switch(arg9) {
                    case 0: {
                        goto label_28;
                    }
                    case 1: {
                        goto label_33;
                    }
                }

                v7_1 = null;
                goto label_41;
            label_28:
                v7_1 = Keyframe.ofFloat(v7, TypedArrayUtils.getNamedFloat(v6, arg10, "value", 0, 0f));
                goto label_41;
            }

        label_33:
            v7_1 = Keyframe.ofInt(v7, TypedArrayUtils.getNamedInt(v6, arg10, "value", 0, 0));
        }
        else {
            if(arg9 == 0) {
                v7_1 = Keyframe.ofFloat(v7);
                goto label_41;
            }

            v7_1 = Keyframe.ofInt(v7);
        }

    label_41:
        v8 = TypedArrayUtils.getNamedResourceId(v6, arg10, "interpolator", 1, 0);
        if(v8 > 0) {
            v7_1.setInterpolator(AnimationUtilsCompat.loadInterpolator(arg5, v8));
        }

        v6.recycle();
        return v7_1;
    }

    private static ObjectAnimator loadObjectAnimator(Context arg8, Resources arg9, Resources$Theme arg10, AttributeSet arg11, float arg12, XmlPullParser arg13) throws Resources$NotFoundException {
        ObjectAnimator v7 = new ObjectAnimator();
        AnimatorInflaterCompat.loadAnimator(arg8, arg9, arg10, arg11, v7, arg12, arg13);
        return v7;
    }

    private static PropertyValuesHolder loadPvh(Context arg9, Resources arg10, Resources$Theme arg11, XmlPullParser arg12, String arg13, int arg14) throws XmlPullParserException, IOException {
        PropertyValuesHolder v0 = null;
        int v1 = arg14;
        ArrayList v14 = ((ArrayList)v0);
        while(true) {
            int v2 = arg12.next();
            int v3 = 3;
            if(v2 != v3 && v2 != 1) {
                if(!arg12.getName().equals("keyframe")) {
                    continue;
                }

                if(v1 == 4) {
                    v1 = AnimatorInflaterCompat.inferValueTypeOfKeyframe(arg10, arg11, Xml.asAttributeSet(arg12), arg12);
                }

                Keyframe v2_1 = AnimatorInflaterCompat.loadKeyframe(arg9, arg10, arg11, Xml.asAttributeSet(arg12), v1, arg12);
                if(v2_1 != null) {
                    if(v14 == null) {
                        v14 = new ArrayList();
                    }

                    v14.add(v2_1);
                }

                arg12.next();
                continue;
            }

            break;
        }

        if(v14 != null) {
            int v9 = v14.size();
            if(v9 > 0) {
                int v10 = 0;
                Object v11 = v14.get(0);
                Object v12 = v14.get(v9 - 1);
                float v0_1 = ((Keyframe)v12).getFraction();
                float v2_2 = 1f;
                if(Float.compare(v0_1, v2_2) < 0) {
                    if(v0_1 < 0f) {
                        ((Keyframe)v12).setFraction(v2_2);
                    }
                    else {
                        v14.add(v14.size(), AnimatorInflaterCompat.createNewKeyframe(((Keyframe)v12), v2_2));
                        ++v9;
                    }
                }

                float v12_1 = ((Keyframe)v11).getFraction();
                if(v12_1 != 0f) {
                    if(v12_1 < 0f) {
                        ((Keyframe)v11).setFraction(0f);
                    }
                    else {
                        v14.add(0, AnimatorInflaterCompat.createNewKeyframe(((Keyframe)v11), 0f));
                        ++v9;
                    }
                }

                Keyframe[] v11_1 = new Keyframe[v9];
                v14.toArray(((Object[])v11_1));
                while(v10 < v9) {
                    Keyframe v12_2 = v11_1[v10];
                    if(v12_2.getFraction() < 0f) {
                        if(v10 == 0) {
                            v12_2.setFraction(0f);
                        }
                        else {
                            arg14 = v9 - 1;
                            if(v10 == arg14) {
                                v12_2.setFraction(v2_2);
                            }
                            else {
                                int v12_3 = v10 + 1;
                                int v0_2 = v10;
                                while(v12_3 < arg14) {
                                    if(v11_1[v12_3].getFraction() >= 0f) {
                                    }
                                    else {
                                        v0_2 = v12_3;
                                        ++v12_3;
                                        continue;
                                    }

                                    break;
                                }

                                AnimatorInflaterCompat.distributeKeyframes(v11_1, v11_1[v0_2 + 1].getFraction() - v11_1[v10 - 1].getFraction(), v10, v0_2);
                            }
                        }
                    }

                    ++v10;
                }

                v0 = PropertyValuesHolder.ofKeyframe(arg13, v11_1);
                if(v1 != v3) {
                    return v0;
                }

                v0.setEvaluator(ArgbEvaluator.getInstance());
            }
        }

        return v0;
    }

    private static PropertyValuesHolder[] loadValues(Context arg17, Resources arg18, Resources$Theme arg19, XmlPullParser arg20, AttributeSet arg21) throws XmlPullParserException, IOException {
        int v9;
        int v0;
        XmlPullParser v6 = arg20;
        PropertyValuesHolder[] v7 = null;
        ArrayList v8 = ((ArrayList)v7);
        while(true) {
            v0 = arg20.getEventType();
            v9 = 0;
            int v1 = 3;
            if(v0 != v1 && v0 != 1) {
                int v2 = 2;
                if(v0 != v2) {
                    arg20.next();
                }
                else {
                    if(arg20.getName().equals("propertyValuesHolder")) {
                        TypedArray v14 = TypedArrayUtils.obtainAttributes(arg18, arg19, arg21, AndroidResources.STYLEABLE_PROPERTY_VALUES_HOLDER);
                        String v15 = TypedArrayUtils.getNamedString(v14, v6, "propertyName", v1);
                        int v5 = TypedArrayUtils.getNamedInt(v14, v6, "valueType", v2, 4);
                        int v16 = v5;
                        PropertyValuesHolder v0_1 = AnimatorInflaterCompat.loadPvh(arg17, arg18, arg19, v6, v15, v5);
                        if(v0_1 == null) {
                            v0_1 = AnimatorInflaterCompat.getPVH(v14, v16, 0, 1, v15);
                        }

                        if(v0_1 != null) {
                            if(v8 == null) {
                                v8 = new ArrayList();
                            }

                            v8.add(v0_1);
                        }

                        v14.recycle();
                    }

                    arg20.next();
                }

                continue;
            }

            break;
        }

        if(v8 != null) {
            v0 = v8.size();
            v7 = new PropertyValuesHolder[v0];
            while(v9 < v0) {
                v7[v9] = v8.get(v9);
                ++v9;
            }
        }

        return v7;
    }

    private static void parseAnimatorFromTypeArray(ValueAnimator arg11, TypedArray arg12, TypedArray arg13, float arg14, XmlPullParser arg15) {
        long v2 = ((long)TypedArrayUtils.getNamedInt(arg12, arg15, "duration", 1, 300));
        long v5 = ((long)TypedArrayUtils.getNamedInt(arg12, arg15, "startOffset", 2, 0));
        int v7 = 4;
        int v0 = TypedArrayUtils.getNamedInt(arg12, arg15, "valueType", 7, v7);
        if((TypedArrayUtils.hasAttribute(arg15, "valueFrom")) && (TypedArrayUtils.hasAttribute(arg15, "valueTo"))) {
            int v8 = 6;
            int v9 = 5;
            if(v0 == v7) {
                v0 = AnimatorInflaterCompat.inferValueTypeFromValues(arg12, v9, v8);
            }

            PropertyValuesHolder v8_1 = AnimatorInflaterCompat.getPVH(arg12, v0, v9, v8, "");
            if(v8_1 == null) {
                goto label_30;
            }

            arg11.setValues(new PropertyValuesHolder[]{v8_1});
        }

    label_30:
        arg11.setDuration(v2);
        arg11.setStartDelay(v5);
        arg11.setRepeatCount(TypedArrayUtils.getNamedInt(arg12, arg15, "repeatCount", 3, 0));
        arg11.setRepeatMode(TypedArrayUtils.getNamedInt(arg12, arg15, "repeatMode", v7, 1));
        if(arg13 != null) {
            AnimatorInflaterCompat.setupObjectAnimator(arg11, arg13, v0, arg14, arg15);
        }
    }

    private static void setupObjectAnimator(ValueAnimator arg5, TypedArray arg6, int arg7, float arg8, XmlPullParser arg9) {
        String v0 = TypedArrayUtils.getNamedString(arg6, arg9, "pathData", 1);
        if(v0 != null) {
            String v1 = TypedArrayUtils.getNamedString(arg6, arg9, "propertyXName", 2);
            String v9 = TypedArrayUtils.getNamedString(arg6, arg9, "propertyYName", 3);
            if(v1 == null && v9 == null) {
                StringBuilder v7 = new StringBuilder();
                v7.append(arg6.getPositionDescription());
                v7.append(" propertyXName or propertyYName is needed for PathData");
                throw new InflateException(v7.toString());
            }

            AnimatorInflaterCompat.setupPathMotion(PathParser.createPathFromPathData(v0), ((ObjectAnimator)arg5), arg8 * 0.5f, v1, v9);
        }
        else {
            ((ObjectAnimator)arg5).setPropertyName(TypedArrayUtils.getNamedString(arg6, arg9, "propertyName", 0));
        }
    }

    private static void setupPathMotion(Path arg17, ObjectAnimator arg18, float arg19, String arg20, String arg21) {
        float[] v12;
        Path v0 = arg17;
        ObjectAnimator v1 = arg18;
        String v2 = arg20;
        String v3 = arg21;
        int v5 = 0;
        PathMeasure v4 = new PathMeasure(v0, false);
        ArrayList v6 = new ArrayList();
        v6.add(Float.valueOf(0f));
        float v8 = 0f;
        do {
            v8 += v4.getLength();
            v6.add(Float.valueOf(v8));
        }
        while(v4.nextContour());

        v4 = new PathMeasure(v0, false);
        int v0_1 = Math.min(100, (((int)(v8 / arg19))) + 1);
        float[] v9 = new float[v0_1];
        float[] v11 = new float[v0_1];
        float[] v13 = new float[2];
        v8 /= ((float)(v0_1 - 1));
        int v7 = 0;
        float v14 = 0f;
        int v15 = 0;
        while(true) {
            v12 = null;
            if(v7 >= v0_1) {
                break;
            }

            v4.getPosTan(v14, v13, v12);
            v4.getPosTan(v14, v13, v12);
            v9[v7] = v13[v5];
            v11[v7] = v13[1];
            v14 += v8;
            int v12_1 = v15 + 1;
            if(v12_1 < v6.size() && v14 > v6.get(v12_1).floatValue()) {
                v14 -= v6.get(v12_1).floatValue();
                v4.nextContour();
                v15 = v12_1;
            }

            ++v7;
            v5 = 0;
        }

        PropertyValuesHolder v0_2 = v2 != null ? PropertyValuesHolder.ofFloat(v2, v9) : ((PropertyValuesHolder)v12);
        if(v3 != null) {
            PropertyValuesHolder v12_2 = PropertyValuesHolder.ofFloat(v3, v11);
        }

        if(v0_2 == null) {
            v1.setValues(new PropertyValuesHolder[]{((PropertyValuesHolder)v12)});
        }
        else if((((PropertyValuesHolder)v12)) == null) {
            v1.setValues(new PropertyValuesHolder[]{v0_2});
        }
        else {
            v1.setValues(new PropertyValuesHolder[]{v0_2, ((PropertyValuesHolder)v12)});
        }
    }
}

