package android.support.v7.content.res;

import android.content.res.ColorStateList;
import android.content.res.Resources$Theme;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.appcompat.R$attr;
import android.support.v7.appcompat.R$styleable;
import android.util.AttributeSet;
import android.util.StateSet;
import android.util.Xml;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

final class AppCompatColorStateListInflater {
    private static final int DEFAULT_COLOR = 0xFFFF0000;

    private AppCompatColorStateListInflater() {
        super();
    }

    @NonNull public static ColorStateList createFromXml(@NonNull Resources arg4, @NonNull XmlPullParser arg5, @Nullable Resources$Theme arg6) throws XmlPullParserException, IOException {
        AttributeSet v0 = Xml.asAttributeSet(arg5);
        while(true) {
            int v1 = arg5.next();
            int v2 = 2;
            if(v1 != v2 && v1 != 1) {
                continue;
            }

            break;
        }

        if(v1 != v2) {
            throw new XmlPullParserException("No start tag found");
        }

        return AppCompatColorStateListInflater.createFromXmlInner(arg4, arg5, v0, arg6);
    }

    @NonNull private static ColorStateList createFromXmlInner(@NonNull Resources arg2, @NonNull XmlPullParser arg3, @NonNull AttributeSet arg4, @Nullable Resources$Theme arg5) throws XmlPullParserException, IOException {
        String v0 = arg3.getName();
        if(!v0.equals("selector")) {
            StringBuilder v4 = new StringBuilder();
            v4.append(arg3.getPositionDescription());
            v4.append(": invalid color state list tag ");
            v4.append(v0);
            throw new XmlPullParserException(v4.toString());
        }

        return AppCompatColorStateListInflater.inflate(arg2, arg3, arg4, arg5);
    }

    private static ColorStateList inflate(@NonNull Resources arg17, @NonNull XmlPullParser arg18, @NonNull AttributeSet arg19, @Nullable Resources$Theme arg20) throws XmlPullParserException, IOException {
        Object[] v3_1;
        AttributeSet v0 = arg19;
        int v2 = 1;
        int v1 = arg18.getDepth() + 1;
        int[][] v3 = new int[20][];
        int[] v4 = new int[v3.length];
        int v6 = 0;
        while(true) {
            int v7 = arg18.next();
            if(v7 != v2) {
                int v8 = arg18.getDepth();
                if(v8 < v1 && v7 == 3) {
                    break;
                }

                if(v7 == 2 && v8 <= v1) {
                    if(!arg18.getName().equals("item")) {
                    }
                    else {
                        TypedArray v7_1 = AppCompatColorStateListInflater.obtainAttributes(arg17, arg20, v0, styleable.ColorStateListItem);
                        int v10 = v7_1.getColor(styleable.ColorStateListItem_android_color, 0xFFFF00FF);
                        float v11 = 1f;
                        if(v7_1.hasValue(styleable.ColorStateListItem_android_alpha)) {
                            v11 = v7_1.getFloat(styleable.ColorStateListItem_android_alpha, v11);
                        }
                        else if(v7_1.hasValue(styleable.ColorStateListItem_alpha)) {
                            v11 = v7_1.getFloat(styleable.ColorStateListItem_alpha, v11);
                        }

                        v7_1.recycle();
                        v7 = arg19.getAttributeCount();
                        int[] v12 = new int[v7];
                        int v13 = 0;
                        int v14 = 0;
                        while(v13 < v7) {
                            int v15 = v0.getAttributeNameResource(v13);
                            if(v15 != 0x10101A5 && v15 != 0x101031F && v15 != attr.alpha) {
                                v2 = v14 + 1;
                                if(v0.getAttributeBooleanValue(v13, false)) {
                                }
                                else {
                                    v15 = -v15;
                                }

                                v12[v14] = v15;
                                v14 = v2;
                            }

                            ++v13;
                        }

                        int[] v2_1 = StateSet.trimStateSet(v12, v14);
                        v4 = GrowingArrayUtils.append(v4, v6, AppCompatColorStateListInflater.modulateColorAlpha(v10, v11));
                        v3_1 = GrowingArrayUtils.append(v3_1, v6, v2_1);
                        ++v6;
                    }
                }

                v2 = 1;
                continue;
            }

            break;
        }

        int[] v0_1 = new int[v6];
        int[][] v1_1 = new int[v6][];
        System.arraycopy(v4, 0, v0_1, 0, v6);
        System.arraycopy(v3_1, 0, v1_1, 0, v6);
        return new ColorStateList(v1_1, v0_1);
    }

    private static int modulateColorAlpha(int arg1, float arg2) {
        return ColorUtils.setAlphaComponent(arg1, Math.round((((float)Color.alpha(arg1))) * arg2));
    }

    private static TypedArray obtainAttributes(Resources arg0, Resources$Theme arg1, AttributeSet arg2, int[] arg3) {
        TypedArray v0 = arg1 == null ? arg0.obtainAttributes(arg2, arg3) : arg1.obtainStyledAttributes(arg2, arg3, 0, 0);
        return v0;
    }
}

