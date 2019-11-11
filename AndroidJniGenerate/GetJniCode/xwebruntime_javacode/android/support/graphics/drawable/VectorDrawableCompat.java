package android.support.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources$Theme;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Bitmap$Config;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint$Cap;
import android.graphics.Paint$Join;
import android.graphics.Paint$Style;
import android.graphics.Paint;
import android.graphics.Path$FillType;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PorterDuff$Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable$ConstantState;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v4.graphics.PathParser$PathDataNode;
import android.support.v4.graphics.PathParser;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.util.ArrayMap;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class VectorDrawableCompat extends VectorDrawableCommon {
    class VClipPath extends VPath {
        public VClipPath() {
            super();
        }

        public VClipPath(VClipPath arg1) {
            super(((VPath)arg1));
        }

        public void inflate(Resources arg2, AttributeSet arg3, Resources$Theme arg4, XmlPullParser arg5) {
            if(!TypedArrayUtils.hasAttribute(arg5, "pathData")) {
                return;
            }

            TypedArray v2 = TypedArrayUtils.obtainAttributes(arg2, arg4, arg3, AndroidResources.STYLEABLE_VECTOR_DRAWABLE_CLIP_PATH);
            this.updateStateFromTypedArray(v2);
            v2.recycle();
        }

        public boolean isClipPath() {
            return 1;
        }

        private void updateStateFromTypedArray(TypedArray arg2) {
            String v0 = arg2.getString(0);
            if(v0 != null) {
                this.mPathName = v0;
            }

            String v2 = arg2.getString(1);
            if(v2 != null) {
                this.mNodes = PathParser.createNodesFromPathData(v2);
            }
        }
    }

    class VFullPath extends VPath {
        float mFillAlpha;
        int mFillColor;
        int mFillRule;
        float mStrokeAlpha;
        int mStrokeColor;
        Paint$Cap mStrokeLineCap;
        Paint$Join mStrokeLineJoin;
        float mStrokeMiterlimit;
        float mStrokeWidth;
        private int[] mThemeAttrs;
        float mTrimPathEnd;
        float mTrimPathOffset;
        float mTrimPathStart;

        public VFullPath() {
            super();
            this.mStrokeColor = 0;
            this.mStrokeWidth = 0f;
            this.mFillColor = 0;
            this.mStrokeAlpha = 1f;
            this.mFillRule = 0;
            this.mFillAlpha = 1f;
            this.mTrimPathStart = 0f;
            this.mTrimPathEnd = 1f;
            this.mTrimPathOffset = 0f;
            this.mStrokeLineCap = Paint$Cap.BUTT;
            this.mStrokeLineJoin = Paint$Join.MITER;
            this.mStrokeMiterlimit = 4f;
        }

        public VFullPath(VFullPath arg4) {
            super(((VPath)arg4));
            this.mStrokeColor = 0;
            this.mStrokeWidth = 0f;
            this.mFillColor = 0;
            this.mStrokeAlpha = 1f;
            this.mFillRule = 0;
            this.mFillAlpha = 1f;
            this.mTrimPathStart = 0f;
            this.mTrimPathEnd = 1f;
            this.mTrimPathOffset = 0f;
            this.mStrokeLineCap = Paint$Cap.BUTT;
            this.mStrokeLineJoin = Paint$Join.MITER;
            this.mStrokeMiterlimit = 4f;
            this.mThemeAttrs = arg4.mThemeAttrs;
            this.mStrokeColor = arg4.mStrokeColor;
            this.mStrokeWidth = arg4.mStrokeWidth;
            this.mStrokeAlpha = arg4.mStrokeAlpha;
            this.mFillColor = arg4.mFillColor;
            this.mFillRule = arg4.mFillRule;
            this.mFillAlpha = arg4.mFillAlpha;
            this.mTrimPathStart = arg4.mTrimPathStart;
            this.mTrimPathEnd = arg4.mTrimPathEnd;
            this.mTrimPathOffset = arg4.mTrimPathOffset;
            this.mStrokeLineCap = arg4.mStrokeLineCap;
            this.mStrokeLineJoin = arg4.mStrokeLineJoin;
            this.mStrokeMiterlimit = arg4.mStrokeMiterlimit;
        }

        public void applyTheme(Resources$Theme arg1) {
            if(this.mThemeAttrs == null) {
                return;
            }
        }

        public boolean canApplyTheme() {
            boolean v0 = this.mThemeAttrs != null ? true : false;
            return v0;
        }

        float getFillAlpha() {
            return this.mFillAlpha;
        }

        int getFillColor() {
            return this.mFillColor;
        }

        float getStrokeAlpha() {
            return this.mStrokeAlpha;
        }

        int getStrokeColor() {
            return this.mStrokeColor;
        }

        private Paint$Cap getStrokeLineCap(int arg1, Paint$Cap arg2) {
            switch(arg1) {
                case 0: {
                    goto label_6;
                }
                case 1: {
                    goto label_4;
                }
                case 2: {
                    goto label_2;
                }
            }

            return arg2;
        label_2:
            return Paint$Cap.SQUARE;
        label_4:
            return Paint$Cap.ROUND;
        label_6:
            return Paint$Cap.BUTT;
        }

        private Paint$Join getStrokeLineJoin(int arg1, Paint$Join arg2) {
            switch(arg1) {
                case 0: {
                    goto label_6;
                }
                case 1: {
                    goto label_4;
                }
                case 2: {
                    goto label_2;
                }
            }

            return arg2;
        label_2:
            return Paint$Join.BEVEL;
        label_4:
            return Paint$Join.ROUND;
        label_6:
            return Paint$Join.MITER;
        }

        float getStrokeWidth() {
            return this.mStrokeWidth;
        }

        float getTrimPathEnd() {
            return this.mTrimPathEnd;
        }

        float getTrimPathOffset() {
            return this.mTrimPathOffset;
        }

        float getTrimPathStart() {
            return this.mTrimPathStart;
        }

        public void inflate(Resources arg2, AttributeSet arg3, Resources$Theme arg4, XmlPullParser arg5) {
            TypedArray v2 = TypedArrayUtils.obtainAttributes(arg2, arg4, arg3, AndroidResources.STYLEABLE_VECTOR_DRAWABLE_PATH);
            this.updateStateFromTypedArray(v2, arg5);
            v2.recycle();
        }

        void setFillAlpha(float arg1) {
            this.mFillAlpha = arg1;
        }

        void setFillColor(int arg1) {
            this.mFillColor = arg1;
        }

        void setStrokeAlpha(float arg1) {
            this.mStrokeAlpha = arg1;
        }

        void setStrokeColor(int arg1) {
            this.mStrokeColor = arg1;
        }

        void setStrokeWidth(float arg1) {
            this.mStrokeWidth = arg1;
        }

        void setTrimPathEnd(float arg1) {
            this.mTrimPathEnd = arg1;
        }

        void setTrimPathOffset(float arg1) {
            this.mTrimPathOffset = arg1;
        }

        void setTrimPathStart(float arg1) {
            this.mTrimPathStart = arg1;
        }

        private void updateStateFromTypedArray(TypedArray arg4, XmlPullParser arg5) {
            this.mThemeAttrs = null;
            if(!TypedArrayUtils.hasAttribute(arg5, "pathData")) {
                return;
            }

            String v0 = arg4.getString(0);
            if(v0 != null) {
                this.mPathName = v0;
            }

            v0 = arg4.getString(2);
            if(v0 != null) {
                this.mNodes = PathParser.createNodesFromPathData(v0);
            }

            this.mFillColor = TypedArrayUtils.getNamedColor(arg4, arg5, "fillColor", 1, this.mFillColor);
            this.mFillAlpha = TypedArrayUtils.getNamedFloat(arg4, arg5, "fillAlpha", 12, this.mFillAlpha);
            this.mStrokeLineCap = this.getStrokeLineCap(TypedArrayUtils.getNamedInt(arg4, arg5, "strokeLineCap", 8, -1), this.mStrokeLineCap);
            this.mStrokeLineJoin = this.getStrokeLineJoin(TypedArrayUtils.getNamedInt(arg4, arg5, "strokeLineJoin", 9, -1), this.mStrokeLineJoin);
            this.mStrokeMiterlimit = TypedArrayUtils.getNamedFloat(arg4, arg5, "strokeMiterLimit", 10, this.mStrokeMiterlimit);
            this.mStrokeColor = TypedArrayUtils.getNamedColor(arg4, arg5, "strokeColor", 3, this.mStrokeColor);
            this.mStrokeAlpha = TypedArrayUtils.getNamedFloat(arg4, arg5, "strokeAlpha", 11, this.mStrokeAlpha);
            this.mStrokeWidth = TypedArrayUtils.getNamedFloat(arg4, arg5, "strokeWidth", 4, this.mStrokeWidth);
            this.mTrimPathEnd = TypedArrayUtils.getNamedFloat(arg4, arg5, "trimPathEnd", 6, this.mTrimPathEnd);
            this.mTrimPathOffset = TypedArrayUtils.getNamedFloat(arg4, arg5, "trimPathOffset", 7, this.mTrimPathOffset);
            this.mTrimPathStart = TypedArrayUtils.getNamedFloat(arg4, arg5, "trimPathStart", 5, this.mTrimPathStart);
            this.mFillRule = TypedArrayUtils.getNamedInt(arg4, arg5, "fillType", 13, this.mFillRule);
        }
    }

    class VGroup {
        int mChangingConfigurations;
        final ArrayList mChildren;
        private String mGroupName;
        private final Matrix mLocalMatrix;
        private float mPivotX;
        private float mPivotY;
        float mRotate;
        private float mScaleX;
        private float mScaleY;
        private final Matrix mStackedMatrix;
        private int[] mThemeAttrs;
        private float mTranslateX;
        private float mTranslateY;

        public VGroup() {
            super();
            this.mStackedMatrix = new Matrix();
            this.mChildren = new ArrayList();
            this.mRotate = 0f;
            this.mPivotX = 0f;
            this.mPivotY = 0f;
            this.mScaleX = 1f;
            this.mScaleY = 1f;
            this.mTranslateX = 0f;
            this.mTranslateY = 0f;
            this.mLocalMatrix = new Matrix();
            this.mGroupName = null;
        }

        public VGroup(VGroup arg5, ArrayMap arg6) {
            VClipPath v2_1;
            super();
            this.mStackedMatrix = new Matrix();
            this.mChildren = new ArrayList();
            this.mRotate = 0f;
            this.mPivotX = 0f;
            this.mPivotY = 0f;
            this.mScaleX = 1f;
            this.mScaleY = 1f;
            this.mTranslateX = 0f;
            this.mTranslateY = 0f;
            this.mLocalMatrix = new Matrix();
            this.mGroupName = null;
            this.mRotate = arg5.mRotate;
            this.mPivotX = arg5.mPivotX;
            this.mPivotY = arg5.mPivotY;
            this.mScaleX = arg5.mScaleX;
            this.mScaleY = arg5.mScaleY;
            this.mTranslateX = arg5.mTranslateX;
            this.mTranslateY = arg5.mTranslateY;
            this.mThemeAttrs = arg5.mThemeAttrs;
            this.mGroupName = arg5.mGroupName;
            this.mChangingConfigurations = arg5.mChangingConfigurations;
            if(this.mGroupName != null) {
                arg6.put(this.mGroupName, this);
            }

            this.mLocalMatrix.set(arg5.mLocalMatrix);
            ArrayList v5 = arg5.mChildren;
            int v0;
            for(v0 = 0; true; ++v0) {
                if(v0 >= v5.size()) {
                    return;
                }

                Object v1 = v5.get(v0);
                if((v1 instanceof VGroup)) {
                    this.mChildren.add(new VGroup(((VGroup)v1), arg6));
                }
                else {
                    if((v1 instanceof VFullPath)) {
                        VFullPath v2 = new VFullPath(((VFullPath)v1));
                    }
                    else if((v1 instanceof VClipPath)) {
                        v2_1 = new VClipPath(((VClipPath)v1));
                    }
                    else {
                        break;
                    }

                    this.mChildren.add(v2_1);
                    if(((VPath)v2_1).mPathName == null) {
                        goto label_75;
                    }

                    arg6.put(((VPath)v2_1).mPathName, v2_1);
                }

            label_75:
            }

            throw new IllegalStateException("Unknown object in the tree!");
        }

        static Matrix access$200(VGroup arg0) {
            return arg0.mStackedMatrix;
        }

        static Matrix access$300(VGroup arg0) {
            return arg0.mLocalMatrix;
        }

        public String getGroupName() {
            return this.mGroupName;
        }

        public Matrix getLocalMatrix() {
            return this.mLocalMatrix;
        }

        public float getPivotX() {
            return this.mPivotX;
        }

        public float getPivotY() {
            return this.mPivotY;
        }

        public float getRotation() {
            return this.mRotate;
        }

        public float getScaleX() {
            return this.mScaleX;
        }

        public float getScaleY() {
            return this.mScaleY;
        }

        public float getTranslateX() {
            return this.mTranslateX;
        }

        public float getTranslateY() {
            return this.mTranslateY;
        }

        public void inflate(Resources arg2, AttributeSet arg3, Resources$Theme arg4, XmlPullParser arg5) {
            TypedArray v2 = TypedArrayUtils.obtainAttributes(arg2, arg4, arg3, AndroidResources.STYLEABLE_VECTOR_DRAWABLE_GROUP);
            this.updateStateFromTypedArray(v2, arg5);
            v2.recycle();
        }

        public void setPivotX(float arg2) {
            if(arg2 != this.mPivotX) {
                this.mPivotX = arg2;
                this.updateLocalMatrix();
            }
        }

        public void setPivotY(float arg2) {
            if(arg2 != this.mPivotY) {
                this.mPivotY = arg2;
                this.updateLocalMatrix();
            }
        }

        public void setRotation(float arg2) {
            if(arg2 != this.mRotate) {
                this.mRotate = arg2;
                this.updateLocalMatrix();
            }
        }

        public void setScaleX(float arg2) {
            if(arg2 != this.mScaleX) {
                this.mScaleX = arg2;
                this.updateLocalMatrix();
            }
        }

        public void setScaleY(float arg2) {
            if(arg2 != this.mScaleY) {
                this.mScaleY = arg2;
                this.updateLocalMatrix();
            }
        }

        public void setTranslateX(float arg2) {
            if(arg2 != this.mTranslateX) {
                this.mTranslateX = arg2;
                this.updateLocalMatrix();
            }
        }

        public void setTranslateY(float arg2) {
            if(arg2 != this.mTranslateY) {
                this.mTranslateY = arg2;
                this.updateLocalMatrix();
            }
        }

        private void updateLocalMatrix() {
            this.mLocalMatrix.reset();
            this.mLocalMatrix.postTranslate(-this.mPivotX, -this.mPivotY);
            this.mLocalMatrix.postScale(this.mScaleX, this.mScaleY);
            this.mLocalMatrix.postRotate(this.mRotate, 0f, 0f);
            this.mLocalMatrix.postTranslate(this.mTranslateX + this.mPivotX, this.mTranslateY + this.mPivotY);
        }

        private void updateStateFromTypedArray(TypedArray arg4, XmlPullParser arg5) {
            this.mThemeAttrs = null;
            this.mRotate = TypedArrayUtils.getNamedFloat(arg4, arg5, "rotation", 5, this.mRotate);
            this.mPivotX = arg4.getFloat(1, this.mPivotX);
            this.mPivotY = arg4.getFloat(2, this.mPivotY);
            this.mScaleX = TypedArrayUtils.getNamedFloat(arg4, arg5, "scaleX", 3, this.mScaleX);
            this.mScaleY = TypedArrayUtils.getNamedFloat(arg4, arg5, "scaleY", 4, this.mScaleY);
            this.mTranslateX = TypedArrayUtils.getNamedFloat(arg4, arg5, "translateX", 6, this.mTranslateX);
            this.mTranslateY = TypedArrayUtils.getNamedFloat(arg4, arg5, "translateY", 7, this.mTranslateY);
            String v4 = arg4.getString(0);
            if(v4 != null) {
                this.mGroupName = v4;
            }

            this.updateLocalMatrix();
        }
    }

    class VPath {
        int mChangingConfigurations;
        protected PathDataNode[] mNodes;
        String mPathName;

        public VPath() {
            super();
            this.mNodes = null;
        }

        public VPath(VPath arg2) {
            super();
            this.mNodes = null;
            this.mPathName = arg2.mPathName;
            this.mChangingConfigurations = arg2.mChangingConfigurations;
            this.mNodes = PathParser.deepCopyNodes(arg2.mNodes);
        }

        public void applyTheme(Resources$Theme arg1) {
        }

        public boolean canApplyTheme() {
            return 0;
        }

        public PathDataNode[] getPathData() {
            return this.mNodes;
        }

        public String getPathName() {
            return this.mPathName;
        }

        public boolean isClipPath() {
            return 0;
        }

        public String nodesToString(PathDataNode[] arg7) {
            String v2 = " ";
            int v0 = 0;
            while(v0 < arg7.length) {
                v2 = v2 + arg7[v0].mType + ":";
                float[] v3_1 = arg7[v0].mParams;
                String v4 = v2;
                int v2_1;
                for(v2_1 = 0; v2_1 < v3_1.length; ++v2_1) {
                    v4 = v4 + v3_1[v2_1] + ",";
                }

                ++v0;
                v2 = v4;
            }

            return v2;
        }

        public void printVPath(int arg4) {
            String v0 = "";
            int v1;
            for(v1 = 0; v1 < arg4; ++v1) {
                v0 = v0 + "    ";
            }

            Log.v("VectorDrawableCompat", v0 + "current path is :" + this.mPathName + " pathData is " + this.nodesToString(this.mNodes));
        }

        public void setPathData(PathDataNode[] arg2) {
            if(!PathParser.canMorph(this.mNodes, arg2)) {
                this.mNodes = PathParser.deepCopyNodes(arg2);
            }
            else {
                PathParser.updateNodes(this.mNodes, arg2);
            }
        }

        public void toPath(Path arg2) {
            arg2.reset();
            if(this.mNodes != null) {
                PathDataNode.nodesToPath(this.mNodes, arg2);
            }
        }
    }

    class VPathRenderer {
        private static final Matrix IDENTITY_MATRIX;
        float mBaseHeight;
        float mBaseWidth;
        private int mChangingConfigurations;
        private Paint mFillPaint;
        private final Matrix mFinalPathMatrix;
        private final Path mPath;
        private PathMeasure mPathMeasure;
        private final Path mRenderPath;
        int mRootAlpha;
        final VGroup mRootGroup;
        String mRootName;
        private Paint mStrokePaint;
        final ArrayMap mVGTargetsMap;
        float mViewportHeight;
        float mViewportWidth;

        static {
            VPathRenderer.IDENTITY_MATRIX = new Matrix();
        }

        public VPathRenderer() {
            super();
            this.mFinalPathMatrix = new Matrix();
            this.mBaseWidth = 0f;
            this.mBaseHeight = 0f;
            this.mViewportWidth = 0f;
            this.mViewportHeight = 0f;
            this.mRootAlpha = 0xFF;
            this.mRootName = null;
            this.mVGTargetsMap = new ArrayMap();
            this.mRootGroup = new VGroup();
            this.mPath = new Path();
            this.mRenderPath = new Path();
        }

        public VPathRenderer(VPathRenderer arg4) {
            super();
            this.mFinalPathMatrix = new Matrix();
            this.mBaseWidth = 0f;
            this.mBaseHeight = 0f;
            this.mViewportWidth = 0f;
            this.mViewportHeight = 0f;
            this.mRootAlpha = 0xFF;
            this.mRootName = null;
            this.mVGTargetsMap = new ArrayMap();
            this.mRootGroup = new VGroup(arg4.mRootGroup, this.mVGTargetsMap);
            this.mPath = new Path(arg4.mPath);
            this.mRenderPath = new Path(arg4.mRenderPath);
            this.mBaseWidth = arg4.mBaseWidth;
            this.mBaseHeight = arg4.mBaseHeight;
            this.mViewportWidth = arg4.mViewportWidth;
            this.mViewportHeight = arg4.mViewportHeight;
            this.mChangingConfigurations = arg4.mChangingConfigurations;
            this.mRootAlpha = arg4.mRootAlpha;
            this.mRootName = arg4.mRootName;
            if(arg4.mRootName != null) {
                this.mVGTargetsMap.put(arg4.mRootName, this);
            }
        }

        static Paint access$000(VPathRenderer arg0) {
            return arg0.mFillPaint;
        }

        static Paint access$002(VPathRenderer arg0, Paint arg1) {
            arg0.mFillPaint = arg1;
            return arg1;
        }

        static Paint access$100(VPathRenderer arg0) {
            return arg0.mStrokePaint;
        }

        static Paint access$102(VPathRenderer arg0, Paint arg1) {
            arg0.mStrokePaint = arg1;
            return arg1;
        }

        private static float cross(float arg0, float arg1, float arg2, float arg3) {
            return arg0 * arg3 - arg1 * arg2;
        }

        public void draw(Canvas arg8, int arg9, int arg10, ColorFilter arg11) {
            this.drawGroupTree(this.mRootGroup, VPathRenderer.IDENTITY_MATRIX, arg8, arg9, arg10, arg11);
        }

        private void drawGroupTree(VGroup arg10, Matrix arg11, Canvas arg12, int arg13, int arg14, ColorFilter arg15) {
            arg10.mStackedMatrix.set(arg11);
            arg10.mStackedMatrix.preConcat(arg10.mLocalMatrix);
            arg12.save();
            int v11;
            for(v11 = 0; v11 < arg10.mChildren.size(); ++v11) {
                Object v0 = arg10.mChildren.get(v11);
                if((v0 instanceof VGroup)) {
                    this.drawGroupTree(v0, arg10.mStackedMatrix, arg12, arg13, arg14, arg15);
                }
                else if((v0 instanceof VPath)) {
                    this.drawPath(arg10, v0, arg12, arg13, arg14, arg15);
                }
            }

            arg12.restore();
        }

        private void drawPath(VGroup arg8, VPath arg9, Canvas arg10, int arg11, int arg12, ColorFilter arg13) {
            Paint v11_1;
            float v11 = (((float)arg11)) / this.mViewportWidth;
            float v12 = (((float)arg12)) / this.mViewportHeight;
            float v0 = Math.min(v11, v12);
            Matrix v8 = arg8.mStackedMatrix;
            this.mFinalPathMatrix.set(v8);
            this.mFinalPathMatrix.postScale(v11, v12);
            float v8_1 = this.getMatrixScale(v8);
            if(v8_1 == 0f) {
                return;
            }

            arg9.toPath(this.mPath);
            Path v12_1 = this.mPath;
            this.mRenderPath.reset();
            if(arg9.isClipPath()) {
                this.mRenderPath.addPath(v12_1, this.mFinalPathMatrix);
                arg10.clipPath(this.mRenderPath);
            }
            else {
                float v2 = 1f;
                if(Float.compare(((VFullPath)arg9).mTrimPathStart, 0f) != 0 || ((VFullPath)arg9).mTrimPathEnd != v2) {
                    float v1 = (((VFullPath)arg9).mTrimPathStart + ((VFullPath)arg9).mTrimPathOffset) % v2;
                    float v4 = (((VFullPath)arg9).mTrimPathEnd + ((VFullPath)arg9).mTrimPathOffset) % v2;
                    if(this.mPathMeasure == null) {
                        this.mPathMeasure = new PathMeasure();
                    }

                    this.mPathMeasure.setPath(this.mPath, false);
                    v2 = this.mPathMeasure.getLength();
                    v1 *= v2;
                    v4 *= v2;
                    v12_1.reset();
                    if(v1 > v4) {
                        this.mPathMeasure.getSegment(v1, v2, v12_1, true);
                        this.mPathMeasure.getSegment(0f, v4, v12_1, true);
                    }
                    else {
                        this.mPathMeasure.getSegment(v1, v4, v12_1, true);
                    }

                    v12_1.rLineTo(0f, 0f);
                }

                this.mRenderPath.addPath(v12_1, this.mFinalPathMatrix);
                if(((VFullPath)arg9).mFillColor != 0) {
                    if(this.mFillPaint == null) {
                        this.mFillPaint = new Paint();
                        this.mFillPaint.setStyle(Paint$Style.FILL);
                        this.mFillPaint.setAntiAlias(true);
                    }

                    v11_1 = this.mFillPaint;
                    v11_1.setColor(VectorDrawableCompat.applyAlpha(((VFullPath)arg9).mFillColor, ((VFullPath)arg9).mFillAlpha));
                    v11_1.setColorFilter(arg13);
                    v12_1 = this.mRenderPath;
                    Path$FillType v1_1 = ((VFullPath)arg9).mFillRule == 0 ? Path$FillType.WINDING : Path$FillType.EVEN_ODD;
                    v12_1.setFillType(v1_1);
                    arg10.drawPath(this.mRenderPath, v11_1);
                }

                if(((VFullPath)arg9).mStrokeColor == 0) {
                    return;
                }

                if(this.mStrokePaint == null) {
                    this.mStrokePaint = new Paint();
                    this.mStrokePaint.setStyle(Paint$Style.STROKE);
                    this.mStrokePaint.setAntiAlias(true);
                }

                v11_1 = this.mStrokePaint;
                if(((VFullPath)arg9).mStrokeLineJoin != null) {
                    v11_1.setStrokeJoin(((VFullPath)arg9).mStrokeLineJoin);
                }

                if(((VFullPath)arg9).mStrokeLineCap != null) {
                    v11_1.setStrokeCap(((VFullPath)arg9).mStrokeLineCap);
                }

                v11_1.setStrokeMiter(((VFullPath)arg9).mStrokeMiterlimit);
                v11_1.setColor(VectorDrawableCompat.applyAlpha(((VFullPath)arg9).mStrokeColor, ((VFullPath)arg9).mStrokeAlpha));
                v11_1.setColorFilter(arg13);
                v11_1.setStrokeWidth(((VFullPath)arg9).mStrokeWidth * (v0 * v8_1));
                arg10.drawPath(this.mRenderPath, v11_1);
            }
        }

        public float getAlpha() {
            return (((float)this.getRootAlpha())) / 255f;
        }

        private float getMatrixScale(Matrix arg10) {
            float[] v0 = new float[]{0f, 1f, 1f, 0f};
            arg10.mapVectors(v0);
            float v1 = ((float)Math.hypot(((double)v0[0]), ((double)v0[1])));
            float v4 = ((float)Math.hypot(((double)v0[2]), ((double)v0[3])));
            float v10 = VPathRenderer.cross(v0[0], v0[1], v0[2], v0[3]);
            float v0_1 = Math.max(v1, v4);
            v1 = 0f;
            if(v0_1 > 0f) {
                v1 = Math.abs(v10) / v0_1;
            }

            return v1;
        }

        public int getRootAlpha() {
            return this.mRootAlpha;
        }

        public void setAlpha(float arg2) {
            this.setRootAlpha(((int)(arg2 * 255f)));
        }

        public void setRootAlpha(int arg1) {
            this.mRootAlpha = arg1;
        }
    }

    class VectorDrawableCompatState extends Drawable$ConstantState {
        boolean mAutoMirrored;
        boolean mCacheDirty;
        boolean mCachedAutoMirrored;
        Bitmap mCachedBitmap;
        int mCachedRootAlpha;
        int[] mCachedThemeAttrs;
        ColorStateList mCachedTint;
        PorterDuff$Mode mCachedTintMode;
        int mChangingConfigurations;
        Paint mTempPaint;
        ColorStateList mTint;
        PorterDuff$Mode mTintMode;
        VPathRenderer mVPathRenderer;

        public VectorDrawableCompatState() {
            super();
            this.mTint = null;
            this.mTintMode = VectorDrawableCompat.DEFAULT_TINT_MODE;
            this.mVPathRenderer = new VPathRenderer();
        }

        public VectorDrawableCompatState(VectorDrawableCompatState arg4) {
            super();
            this.mTint = null;
            this.mTintMode = VectorDrawableCompat.DEFAULT_TINT_MODE;
            if(arg4 != null) {
                this.mChangingConfigurations = arg4.mChangingConfigurations;
                this.mVPathRenderer = new VPathRenderer(arg4.mVPathRenderer);
                if(arg4.mVPathRenderer.mFillPaint != null) {
                    this.mVPathRenderer.mFillPaint = new Paint(arg4.mVPathRenderer.mFillPaint);
                }

                if(arg4.mVPathRenderer.mStrokePaint != null) {
                    this.mVPathRenderer.mStrokePaint = new Paint(arg4.mVPathRenderer.mStrokePaint);
                }

                this.mTint = arg4.mTint;
                this.mTintMode = arg4.mTintMode;
                this.mAutoMirrored = arg4.mAutoMirrored;
            }
        }

        public boolean canReuseBitmap(int arg2, int arg3) {
            if(arg2 == this.mCachedBitmap.getWidth() && arg3 == this.mCachedBitmap.getHeight()) {
                return 1;
            }

            return 0;
        }

        public boolean canReuseCache() {
            if(!this.mCacheDirty && this.mCachedTint == this.mTint && this.mCachedTintMode == this.mTintMode && this.mCachedAutoMirrored == this.mAutoMirrored && this.mCachedRootAlpha == this.mVPathRenderer.getRootAlpha()) {
                return 1;
            }

            return 0;
        }

        public void createCachedBitmapIfNeeded(int arg2, int arg3) {
            if(this.mCachedBitmap == null || !this.canReuseBitmap(arg2, arg3)) {
                this.mCachedBitmap = Bitmap.createBitmap(arg2, arg3, Bitmap$Config.ARGB_8888);
                this.mCacheDirty = true;
            }
        }

        public void drawCachedBitmapWithRootAlpha(Canvas arg3, ColorFilter arg4, Rect arg5) {
            arg3.drawBitmap(this.mCachedBitmap, null, arg5, this.getPaint(arg4));
        }

        public int getChangingConfigurations() {
            return this.mChangingConfigurations;
        }

        public Paint getPaint(ColorFilter arg3) {
            if(!this.hasTranslucentRoot() && arg3 == null) {
                return null;
            }

            if(this.mTempPaint == null) {
                this.mTempPaint = new Paint();
                this.mTempPaint.setFilterBitmap(true);
            }

            this.mTempPaint.setAlpha(this.mVPathRenderer.getRootAlpha());
            this.mTempPaint.setColorFilter(arg3);
            return this.mTempPaint;
        }

        public boolean hasTranslucentRoot() {
            boolean v0 = this.mVPathRenderer.getRootAlpha() < 0xFF ? true : false;
            return v0;
        }

        public Drawable newDrawable() {
            return new VectorDrawableCompat(this);
        }

        public Drawable newDrawable(Resources arg1) {
            return new VectorDrawableCompat(this);
        }

        public void updateCacheStates() {
            this.mCachedTint = this.mTint;
            this.mCachedTintMode = this.mTintMode;
            this.mCachedRootAlpha = this.mVPathRenderer.getRootAlpha();
            this.mCachedAutoMirrored = this.mAutoMirrored;
            this.mCacheDirty = false;
        }

        public void updateCachedBitmap(int arg4, int arg5) {
            this.mCachedBitmap.eraseColor(0);
            this.mVPathRenderer.draw(new Canvas(this.mCachedBitmap), arg4, arg5, null);
        }
    }

    @RequiresApi(value=24) class VectorDrawableDelegateState extends Drawable$ConstantState {
        private final Drawable$ConstantState mDelegateState;

        public VectorDrawableDelegateState(Drawable$ConstantState arg1) {
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
            VectorDrawableCompat v0 = new VectorDrawableCompat();
            v0.mDelegateDrawable = this.mDelegateState.newDrawable();
            return ((Drawable)v0);
        }

        public Drawable newDrawable(Resources arg3) {
            VectorDrawableCompat v0 = new VectorDrawableCompat();
            v0.mDelegateDrawable = this.mDelegateState.newDrawable(arg3);
            return ((Drawable)v0);
        }

        public Drawable newDrawable(Resources arg3, Resources$Theme arg4) {
            VectorDrawableCompat v0 = new VectorDrawableCompat();
            v0.mDelegateDrawable = this.mDelegateState.newDrawable(arg3, arg4);
            return ((Drawable)v0);
        }
    }

    private static final boolean DBG_VECTOR_DRAWABLE = false;
    static final PorterDuff$Mode DEFAULT_TINT_MODE = null;
    private static final int LINECAP_BUTT = 0;
    private static final int LINECAP_ROUND = 1;
    private static final int LINECAP_SQUARE = 2;
    private static final int LINEJOIN_BEVEL = 2;
    private static final int LINEJOIN_MITER = 0;
    private static final int LINEJOIN_ROUND = 1;
    static final String LOGTAG = "VectorDrawableCompat";
    private static final int MAX_CACHED_BITMAP_SIZE = 0x800;
    private static final String SHAPE_CLIP_PATH = "clip-path";
    private static final String SHAPE_GROUP = "group";
    private static final String SHAPE_PATH = "path";
    private static final String SHAPE_VECTOR = "vector";
    private boolean mAllowCaching;
    private Drawable$ConstantState mCachedConstantStateDelegate;
    private ColorFilter mColorFilter;
    private boolean mMutated;
    private PorterDuffColorFilter mTintFilter;
    private final Rect mTmpBounds;
    private final float[] mTmpFloats;
    private final Matrix mTmpMatrix;
    private VectorDrawableCompatState mVectorState;

    static {
        VectorDrawableCompat.DEFAULT_TINT_MODE = PorterDuff$Mode.SRC_IN;
    }

    VectorDrawableCompat() {
        super();
        this.mAllowCaching = true;
        this.mTmpFloats = new float[9];
        this.mTmpMatrix = new Matrix();
        this.mTmpBounds = new Rect();
        this.mVectorState = new VectorDrawableCompatState();
    }

    VectorDrawableCompat(@NonNull VectorDrawableCompatState arg3) {
        super();
        this.mAllowCaching = true;
        this.mTmpFloats = new float[9];
        this.mTmpMatrix = new Matrix();
        this.mTmpBounds = new Rect();
        this.mVectorState = arg3;
        this.mTintFilter = this.updateTintFilter(this.mTintFilter, arg3.mTint, arg3.mTintMode);
    }

    static int applyAlpha(int arg2, float arg3) {
        return arg2 & 0xFFFFFF | (((int)((((float)Color.alpha(arg2))) * arg3))) << 24;
    }

    public void applyTheme(Resources$Theme arg1) {
        super.applyTheme(arg1);
    }

    public boolean canApplyTheme() {
        if(this.mDelegateDrawable != null) {
            DrawableCompat.canApplyTheme(this.mDelegateDrawable);
        }

        return 0;
    }

    public void clearColorFilter() {
        super.clearColorFilter();
    }

    @Nullable public static VectorDrawableCompat create(@NonNull Resources arg4, @DrawableRes int arg5, @Nullable Resources$Theme arg6) {
        if(Build$VERSION.SDK_INT >= 24) {
            VectorDrawableCompat v0 = new VectorDrawableCompat();
            v0.mDelegateDrawable = ResourcesCompat.getDrawable(arg4, arg5, arg6);
            v0.mCachedConstantStateDelegate = new VectorDrawableDelegateState(v0.mDelegateDrawable.getConstantState());
            return v0;
        }

        try {
            XmlResourceParser v5 = arg4.getXml(arg5);
            AttributeSet v0_1 = Xml.asAttributeSet(((XmlPullParser)v5));
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

            return VectorDrawableCompat.createFromXmlInner(arg4, ((XmlPullParser)v5), v0_1, arg6);
        }
        catch(IOException v4) {
            Log.e("VectorDrawableCompat", "parser error", ((Throwable)v4));
        }
        catch(XmlPullParserException v4_1) {
            Log.e("VectorDrawableCompat", "parser error", ((Throwable)v4_1));
        }

        return null;
    }

    public static VectorDrawableCompat createFromXmlInner(Resources arg1, XmlPullParser arg2, AttributeSet arg3, Resources$Theme arg4) throws XmlPullParserException, IOException {
        VectorDrawableCompat v0 = new VectorDrawableCompat();
        v0.inflate(arg1, arg2, arg3, arg4);
        return v0;
    }

    public void draw(Canvas arg10) {
        ColorFilter v0_1;
        if(this.mDelegateDrawable != null) {
            this.mDelegateDrawable.draw(arg10);
            return;
        }

        this.copyBounds(this.mTmpBounds);
        if(this.mTmpBounds.width() > 0) {
            if(this.mTmpBounds.height() <= 0) {
            }
            else {
                if(this.mColorFilter == null) {
                    PorterDuffColorFilter v0 = this.mTintFilter;
                }
                else {
                    v0_1 = this.mColorFilter;
                }

                arg10.getMatrix(this.mTmpMatrix);
                this.mTmpMatrix.getValues(this.mTmpFloats);
                float v1 = Math.abs(this.mTmpFloats[0]);
                float v3 = Math.abs(this.mTmpFloats[4]);
                float v4 = Math.abs(this.mTmpFloats[1]);
                float v5 = Math.abs(this.mTmpFloats[3]);
                float v7 = 1f;
                if(Float.compare(v4, 0f) != 0 || v5 != 0f) {
                    v1 = 1f;
                    v3 = 1f;
                }

                int v1_1 = ((int)((((float)this.mTmpBounds.width())) * v1));
                int v3_1 = ((int)((((float)this.mTmpBounds.height())) * v3));
                v1_1 = Math.min(0x800, v1_1);
                v3_1 = Math.min(0x800, v3_1);
                if(v1_1 > 0) {
                    if(v3_1 <= 0) {
                    }
                    else {
                        int v4_1 = arg10.save();
                        arg10.translate(((float)this.mTmpBounds.left), ((float)this.mTmpBounds.top));
                        if(this.needMirroring()) {
                            arg10.translate(((float)this.mTmpBounds.width()), 0f);
                            arg10.scale(-1f, v7);
                        }

                        this.mTmpBounds.offsetTo(0, 0);
                        this.mVectorState.createCachedBitmapIfNeeded(v1_1, v3_1);
                        if(!this.mAllowCaching) {
                            this.mVectorState.updateCachedBitmap(v1_1, v3_1);
                        }
                        else if(!this.mVectorState.canReuseCache()) {
                            this.mVectorState.updateCachedBitmap(v1_1, v3_1);
                            this.mVectorState.updateCacheStates();
                        }

                        this.mVectorState.drawCachedBitmapWithRootAlpha(arg10, v0_1, this.mTmpBounds);
                        arg10.restoreToCount(v4_1);
                        return;
                    }
                }

                return;
            }
        }
    }

    public int getAlpha() {
        if(this.mDelegateDrawable != null) {
            return DrawableCompat.getAlpha(this.mDelegateDrawable);
        }

        return this.mVectorState.mVPathRenderer.getRootAlpha();
    }

    public int getChangingConfigurations() {
        if(this.mDelegateDrawable != null) {
            return this.mDelegateDrawable.getChangingConfigurations();
        }

        return super.getChangingConfigurations() | this.mVectorState.getChangingConfigurations();
    }

    public ColorFilter getColorFilter() {
        return super.getColorFilter();
    }

    public Drawable$ConstantState getConstantState() {
        if(this.mDelegateDrawable != null && Build$VERSION.SDK_INT >= 24) {
            return new VectorDrawableDelegateState(this.mDelegateDrawable.getConstantState());
        }

        this.mVectorState.mChangingConfigurations = this.getChangingConfigurations();
        return this.mVectorState;
    }

    public Drawable getCurrent() {
        return super.getCurrent();
    }

    public int getIntrinsicHeight() {
        if(this.mDelegateDrawable != null) {
            return this.mDelegateDrawable.getIntrinsicHeight();
        }

        return ((int)this.mVectorState.mVPathRenderer.mBaseHeight);
    }

    public int getIntrinsicWidth() {
        if(this.mDelegateDrawable != null) {
            return this.mDelegateDrawable.getIntrinsicWidth();
        }

        return ((int)this.mVectorState.mVPathRenderer.mBaseWidth);
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

        return -3;
    }

    public boolean getPadding(Rect arg1) {
        return super.getPadding(arg1);
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public float getPixelSize() {
        if(this.mVectorState != null && this.mVectorState.mVPathRenderer != null && this.mVectorState.mVPathRenderer.mBaseWidth != 0f && this.mVectorState.mVPathRenderer.mBaseHeight != 0f && this.mVectorState.mVPathRenderer.mViewportHeight != 0f) {
            if(this.mVectorState.mVPathRenderer.mViewportWidth == 0f) {
            }
            else {
                return Math.min(this.mVectorState.mVPathRenderer.mViewportWidth / this.mVectorState.mVPathRenderer.mBaseWidth, this.mVectorState.mVPathRenderer.mViewportHeight / this.mVectorState.mVPathRenderer.mBaseHeight);
            }
        }

        return 1f;
    }

    public int[] getState() {
        return super.getState();
    }

    Object getTargetByName(String arg2) {
        return this.mVectorState.mVPathRenderer.mVGTargetsMap.get(arg2);
    }

    public Region getTransparentRegion() {
        return super.getTransparentRegion();
    }

    public void inflate(Resources arg3, XmlPullParser arg4, AttributeSet arg5, Resources$Theme arg6) throws XmlPullParserException, IOException {
        if(this.mDelegateDrawable != null) {
            DrawableCompat.inflate(this.mDelegateDrawable, arg3, arg4, arg5, arg6);
            return;
        }

        VectorDrawableCompatState v0 = this.mVectorState;
        v0.mVPathRenderer = new VPathRenderer();
        TypedArray v1 = TypedArrayUtils.obtainAttributes(arg3, arg6, arg5, AndroidResources.STYLEABLE_VECTOR_DRAWABLE_TYPE_ARRAY);
        this.updateStateFromTypedArray(v1, arg4);
        v1.recycle();
        v0.mChangingConfigurations = this.getChangingConfigurations();
        v0.mCacheDirty = true;
        this.inflateInternal(arg3, arg4, arg5, arg6);
        this.mTintFilter = this.updateTintFilter(this.mTintFilter, v0.mTint, v0.mTintMode);
    }

    public void inflate(Resources arg2, XmlPullParser arg3, AttributeSet arg4) throws XmlPullParserException, IOException {
        if(this.mDelegateDrawable != null) {
            this.mDelegateDrawable.inflate(arg2, arg3, arg4);
            return;
        }

        this.inflate(arg2, arg3, arg4, null);
    }

    private void inflateInternal(Resources arg10, XmlPullParser arg11, AttributeSet arg12, Resources$Theme arg13) throws XmlPullParserException, IOException {
        VectorDrawableCompatState v0 = this.mVectorState;
        VPathRenderer v1 = v0.mVPathRenderer;
        Stack v2 = new Stack();
        v2.push(v1.mRootGroup);
        int v3 = arg11.getEventType();
        int v4 = arg11.getDepth() + 1;
        int v6 = 1;
        while(v3 != 1) {
            int v8 = 3;
            if(arg11.getDepth() < v4 && v3 == v8) {
                break;
            }

            if(v3 == 2) {
                String v3_1 = arg11.getName();
                Object v7 = v2.peek();
                if("path".equals(v3_1)) {
                    VFullPath v3_2 = new VFullPath();
                    v3_2.inflate(arg10, arg12, arg13, arg11);
                    ((VGroup)v7).mChildren.add(v3_2);
                    if(v3_2.getPathName() != null) {
                        v1.mVGTargetsMap.put(v3_2.getPathName(), v3_2);
                    }

                    v6 = 0;
                    v0.mChangingConfigurations |= v3_2.mChangingConfigurations;
                }
                else {
                    if("clip-path".equals(v3_1)) {
                        VClipPath v3_3 = new VClipPath();
                        v3_3.inflate(arg10, arg12, arg13, arg11);
                        ((VGroup)v7).mChildren.add(v3_3);
                        if(v3_3.getPathName() != null) {
                            v1.mVGTargetsMap.put(v3_3.getPathName(), v3_3);
                        }

                        v0.mChangingConfigurations |= v3_3.mChangingConfigurations;
                        goto label_82;
                    }

                    if(!"group".equals(v3_1)) {
                        goto label_82;
                    }

                    VGroup v3_4 = new VGroup();
                    v3_4.inflate(arg10, arg12, arg13, arg11);
                    ((VGroup)v7).mChildren.add(v3_4);
                    v2.push(v3_4);
                    if(v3_4.getGroupName() != null) {
                        v1.mVGTargetsMap.put(v3_4.getGroupName(), v3_4);
                    }

                    v0.mChangingConfigurations |= v3_4.mChangingConfigurations;
                }
            }
            else {
                if(v3 != v8) {
                    goto label_82;
                }

                if(!"group".equals(arg11.getName())) {
                    goto label_82;
                }

                v2.pop();
            }

        label_82:
            v3 = arg11.next();
        }

        if(v6 != 0) {
            StringBuffer v10 = new StringBuffer();
            if(v10.length() > 0) {
                v10.append(" or ");
            }

            v10.append("path");
            StringBuilder v12 = new StringBuilder();
            v12.append("no ");
            v12.append(v10);
            v12.append(" defined");
            throw new XmlPullParserException(v12.toString());
        }
    }

    public void invalidateSelf() {
        if(this.mDelegateDrawable != null) {
            this.mDelegateDrawable.invalidateSelf();
            return;
        }

        super.invalidateSelf();
    }

    public boolean isAutoMirrored() {
        if(this.mDelegateDrawable != null) {
            return DrawableCompat.isAutoMirrored(this.mDelegateDrawable);
        }

        return this.mVectorState.mAutoMirrored;
    }

    public boolean isStateful() {
        boolean v0;
        if(this.mDelegateDrawable != null) {
            return this.mDelegateDrawable.isStateful();
        }

        if(!super.isStateful()) {
            if(this.mVectorState != null && this.mVectorState.mTint != null && (this.mVectorState.mTint.isStateful())) {
                goto label_19;
            }

            v0 = false;
        }
        else {
        label_19:
            v0 = true;
        }

        return v0;
    }

    public void jumpToCurrentState() {
        super.jumpToCurrentState();
    }

    public Drawable mutate() {
        if(this.mDelegateDrawable != null) {
            this.mDelegateDrawable.mutate();
            return this;
        }

        if(!this.mMutated && super.mutate() == this) {
            this.mVectorState = new VectorDrawableCompatState(this.mVectorState);
            this.mMutated = true;
        }

        return this;
    }

    private boolean needMirroring() {
        boolean v1 = false;
        if(Build$VERSION.SDK_INT >= 17) {
            if((this.isAutoMirrored()) && DrawableCompat.getLayoutDirection(((Drawable)this)) == 1) {
                v1 = true;
            }

            return v1;
        }

        return 0;
    }

    protected void onBoundsChange(Rect arg2) {
        if(this.mDelegateDrawable != null) {
            this.mDelegateDrawable.setBounds(arg2);
        }
    }

    protected boolean onStateChange(int[] arg3) {
        if(this.mDelegateDrawable != null) {
            return this.mDelegateDrawable.setState(arg3);
        }

        VectorDrawableCompatState v3 = this.mVectorState;
        if(v3.mTint != null && v3.mTintMode != null) {
            this.mTintFilter = this.updateTintFilter(this.mTintFilter, v3.mTint, v3.mTintMode);
            this.invalidateSelf();
            return 1;
        }

        return 0;
    }

    private static PorterDuff$Mode parseTintModeCompat(int arg1, PorterDuff$Mode arg2) {
        if(arg1 == 3) {
            goto label_22;
        }

        if(arg1 == 5) {
            goto label_20;
        }

        if(arg1 == 9) {
            goto label_18;
        }

        switch(arg1) {
            case 14: {
                goto label_16;
            }
            case 15: {
                goto label_14;
            }
            case 16: {
                goto label_8;
            }
        }

        return arg2;
    label_8:
        if(Build$VERSION.SDK_INT >= 11) {
            return PorterDuff$Mode.ADD;
        }

        return arg2;
    label_14:
        return PorterDuff$Mode.SCREEN;
    label_16:
        return PorterDuff$Mode.MULTIPLY;
    label_18:
        return PorterDuff$Mode.SRC_ATOP;
    label_20:
        return PorterDuff$Mode.SRC_IN;
    label_22:
        return PorterDuff$Mode.SRC_OVER;
    }

    private void printGroupTree(VGroup arg6, int arg7) {
        int v1 = 0;
        String v2 = "";
        int v0;
        for(v0 = 0; v0 < arg7; ++v0) {
            v2 = v2 + "    ";
        }

        Log.v("VectorDrawableCompat", v2 + "current group is :" + arg6.getGroupName() + " rotation is " + arg6.mRotate);
        Log.v("VectorDrawableCompat", v2 + "matrix is :" + arg6.getLocalMatrix().toString());
        while(v1 < arg6.mChildren.size()) {
            Object v0_1 = arg6.mChildren.get(v1);
            if((v0_1 instanceof VGroup)) {
                this.printGroupTree(((VGroup)v0_1), arg7 + 1);
            }
            else {
                ((VPath)v0_1).printVPath(arg7 + 1);
            }

            ++v1;
        }
    }

    public void scheduleSelf(Runnable arg2, long arg3) {
        if(this.mDelegateDrawable != null) {
            this.mDelegateDrawable.scheduleSelf(arg2, arg3);
            return;
        }

        super.scheduleSelf(arg2, arg3);
    }

    void setAllowCaching(boolean arg1) {
        this.mAllowCaching = arg1;
    }

    public void setAlpha(int arg2) {
        if(this.mDelegateDrawable != null) {
            this.mDelegateDrawable.setAlpha(arg2);
            return;
        }

        if(this.mVectorState.mVPathRenderer.getRootAlpha() != arg2) {
            this.mVectorState.mVPathRenderer.setRootAlpha(arg2);
            this.invalidateSelf();
        }
    }

    public void setAutoMirrored(boolean arg2) {
        if(this.mDelegateDrawable != null) {
            DrawableCompat.setAutoMirrored(this.mDelegateDrawable, arg2);
            return;
        }

        this.mVectorState.mAutoMirrored = arg2;
    }

    public void setChangingConfigurations(int arg1) {
        super.setChangingConfigurations(arg1);
    }

    public void setColorFilter(ColorFilter arg2) {
        if(this.mDelegateDrawable != null) {
            this.mDelegateDrawable.setColorFilter(arg2);
            return;
        }

        this.mColorFilter = arg2;
        this.invalidateSelf();
    }

    public void setColorFilter(int arg1, PorterDuff$Mode arg2) {
        super.setColorFilter(arg1, arg2);
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

        this.setTintList(ColorStateList.valueOf(arg2));
    }

    public void setTintList(ColorStateList arg3) {
        if(this.mDelegateDrawable != null) {
            DrawableCompat.setTintList(this.mDelegateDrawable, arg3);
            return;
        }

        VectorDrawableCompatState v0 = this.mVectorState;
        if(v0.mTint != arg3) {
            v0.mTint = arg3;
            this.mTintFilter = this.updateTintFilter(this.mTintFilter, arg3, v0.mTintMode);
            this.invalidateSelf();
        }
    }

    public void setTintMode(PorterDuff$Mode arg3) {
        if(this.mDelegateDrawable != null) {
            DrawableCompat.setTintMode(this.mDelegateDrawable, arg3);
            return;
        }

        VectorDrawableCompatState v0 = this.mVectorState;
        if(v0.mTintMode != arg3) {
            v0.mTintMode = arg3;
            this.mTintFilter = this.updateTintFilter(this.mTintFilter, v0.mTint, arg3);
            this.invalidateSelf();
        }
    }

    public boolean setVisible(boolean arg2, boolean arg3) {
        if(this.mDelegateDrawable != null) {
            return this.mDelegateDrawable.setVisible(arg2, arg3);
        }

        return super.setVisible(arg2, arg3);
    }

    public void unscheduleSelf(Runnable arg2) {
        if(this.mDelegateDrawable != null) {
            this.mDelegateDrawable.unscheduleSelf(arg2);
            return;
        }

        super.unscheduleSelf(arg2);
    }

    private void updateStateFromTypedArray(TypedArray arg6, XmlPullParser arg7) throws XmlPullParserException {
        StringBuilder v0_1;
        VectorDrawableCompatState v0 = this.mVectorState;
        VPathRenderer v1 = v0.mVPathRenderer;
        v0.mTintMode = VectorDrawableCompat.parseTintModeCompat(TypedArrayUtils.getNamedInt(arg6, arg7, "tintMode", 6, -1), PorterDuff$Mode.SRC_IN);
        ColorStateList v2 = arg6.getColorStateList(1);
        if(v2 != null) {
            v0.mTint = v2;
        }

        v0.mAutoMirrored = TypedArrayUtils.getNamedBoolean(arg6, arg7, "autoMirrored", 5, v0.mAutoMirrored);
        v1.mViewportWidth = TypedArrayUtils.getNamedFloat(arg6, arg7, "viewportWidth", 7, v1.mViewportWidth);
        v1.mViewportHeight = TypedArrayUtils.getNamedFloat(arg6, arg7, "viewportHeight", 8, v1.mViewportHeight);
        if(v1.mViewportWidth <= 0f) {
            v0_1 = new StringBuilder();
            v0_1.append(arg6.getPositionDescription());
            v0_1.append("<vector> tag requires viewportWidth > 0");
            throw new XmlPullParserException(v0_1.toString());
        }

        if(v1.mViewportHeight <= 0f) {
            v0_1 = new StringBuilder();
            v0_1.append(arg6.getPositionDescription());
            v0_1.append("<vector> tag requires viewportHeight > 0");
            throw new XmlPullParserException(v0_1.toString());
        }

        v1.mBaseWidth = arg6.getDimension(3, v1.mBaseWidth);
        v1.mBaseHeight = arg6.getDimension(2, v1.mBaseHeight);
        if(v1.mBaseWidth <= 0f) {
            v0_1 = new StringBuilder();
            v0_1.append(arg6.getPositionDescription());
            v0_1.append("<vector> tag requires width > 0");
            throw new XmlPullParserException(v0_1.toString());
        }

        if(v1.mBaseHeight <= 0f) {
            v0_1 = new StringBuilder();
            v0_1.append(arg6.getPositionDescription());
            v0_1.append("<vector> tag requires height > 0");
            throw new XmlPullParserException(v0_1.toString());
        }

        v1.setAlpha(TypedArrayUtils.getNamedFloat(arg6, arg7, "alpha", 4, v1.getAlpha()));
        String v6 = arg6.getString(0);
        if(v6 != null) {
            v1.mRootName = v6;
            v1.mVGTargetsMap.put(v6, v1);
        }
    }

    PorterDuffColorFilter updateTintFilter(PorterDuffColorFilter arg2, ColorStateList arg3, PorterDuff$Mode arg4) {
        if(arg3 != null) {
            if(arg4 == null) {
            }
            else {
                return new PorterDuffColorFilter(arg3.getColorForState(this.getState(), 0), arg4);
            }
        }

        return null;
    }
}

