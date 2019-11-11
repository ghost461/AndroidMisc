package org.chromium.ui.resources.statics;

import android.graphics.Bitmap;
import android.graphics.NinePatch;
import android.graphics.Rect;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class NinePatchData {
    private Rect mAperture;
    private final int[] mDivX;
    private final int[] mDivY;
    private final int mHeight;
    private final Rect mPadding;
    private final int mWidth;

    private NinePatchData(int arg4, int arg5, Rect arg6, int[] arg7, int[] arg8) {
        super();
        this.mWidth = arg4;
        this.mHeight = arg5;
        this.mPadding = new Rect(arg6.left, arg6.top, this.mWidth - arg6.right, this.mHeight - arg6.bottom);
        this.mDivX = new int[arg7.length];
        this.mDivY = new int[arg8.length];
        System.arraycopy(arg7, 0, this.mDivX, 0, arg7.length);
        System.arraycopy(arg8, 0, this.mDivY, 0, arg8.length);
        this.mAperture = new Rect(this.mDivX[0], this.mDivY[0], this.mDivX[1], this.mDivY[1]);
    }

    public static NinePatchData create(Bitmap arg11) {
        NinePatchData v5_1;
        NinePatchData v0 = null;
        if(arg11 == null) {
            return v0;
        }

        try {
            byte[] v1 = arg11.getNinePatchChunk();
            if(v1 != null) {
                if(!NinePatch.isNinePatchChunk(v1)) {
                }
                else {
                    ByteBuffer v1_1 = ByteBuffer.wrap(v1).order(ByteOrder.nativeOrder());
                    if(v1_1.get() == 0) {
                        return v0;
                    }
                    else {
                        int v2 = v1_1.get();
                        if(v2 != 0) {
                            if((v2 & 1) != 0) {
                            }
                            else {
                                int v3 = v1_1.get();
                                if(v3 != 0) {
                                    if((v3 & 1) != 0) {
                                    }
                                    else {
                                        v1_1.get();
                                        v1_1.getInt();
                                        v1_1.getInt();
                                        Rect v8 = new Rect();
                                        v8.left = v1_1.getInt();
                                        v8.right = v1_1.getInt();
                                        v8.top = v1_1.getInt();
                                        v8.bottom = v1_1.getInt();
                                        v1_1.getInt();
                                        int[] v9 = new int[v2];
                                        int v4 = 0;
                                        int v5;
                                        for(v5 = 0; v5 < v2; ++v5) {
                                            v9[v5] = v1_1.getInt();
                                        }

                                        int[] v10 = new int[v3];
                                        while(v4 < v3) {
                                            v10[v4] = v1_1.getInt();
                                            ++v4;
                                        }

                                        v5_1 = new NinePatchData(arg11.getWidth(), arg11.getHeight(), v8, v9, v10);
                                        return v5_1;
                                    }
                                }

                                return v0;
                            }
                        }

                        return v0;
                    }
                }
            }

            return v0;
        }
        catch(BufferUnderflowException ) {
            return v0;
        }

        return v5_1;
    }

    public Rect getAperture() {
        return this.mAperture;
    }

    public Rect getPadding() {
        return this.mPadding;
    }
}

