package org.chromium.content.browser.input;

import android.annotation.TargetApi;
import android.graphics.Matrix;
import android.view.View;
import android.view.inputmethod.CursorAnchorInfo$Builder;
import android.view.inputmethod.CursorAnchorInfo;
import java.util.Arrays;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.chromium.base.VisibleForTesting;
import org.chromium.content_public.browser.InputMethodManagerWrapper;

@TargetApi(value=21) final class CursorAnchorInfoController {
    public interface ComposingTextDelegate {
        int getComposingTextEnd();

        int getComposingTextStart();

        int getSelectionEnd();

        int getSelectionStart();

        CharSequence getText();
    }

    public interface ViewDelegate {
        void getLocationOnScreen(View arg1, int[] arg2);
    }

    @Nullable private final ComposingTextDelegate mComposingTextDelegate;
    @Nullable private float[] mCompositionCharacterBounds;
    @Nonnull private final CursorAnchorInfo$Builder mCursorAnchorInfoBuilder;
    private boolean mHasCoordinateInfo;
    private boolean mHasInsertionMarker;
    private boolean mHasPendingImmediateRequest;
    @Nullable private InputMethodManagerWrapper mInputMethodManagerWrapper;
    private float mInsertionMarkerBottom;
    private float mInsertionMarkerHorizontal;
    private float mInsertionMarkerTop;
    private boolean mIsEditable;
    private boolean mIsInsertionMarkerVisible;
    @Nullable private CursorAnchorInfo mLastCursorAnchorInfo;
    @Nonnull private final Matrix mMatrix;
    private boolean mMonitorModeEnabled;
    private float mScale;
    private float mTranslationX;
    private float mTranslationY;
    @Nonnull private final ViewDelegate mViewDelegate;
    @Nonnull private final int[] mViewOrigin;

    private CursorAnchorInfoController(InputMethodManagerWrapper arg2, ComposingTextDelegate arg3, ViewDelegate arg4) {
        super();
        this.mMatrix = new Matrix();
        this.mViewOrigin = new int[2];
        this.mCursorAnchorInfoBuilder = new CursorAnchorInfo$Builder();
        this.mInputMethodManagerWrapper = arg2;
        this.mComposingTextDelegate = arg3;
        this.mViewDelegate = arg4;
    }

    public static CursorAnchorInfoController create(InputMethodManagerWrapper arg2, ComposingTextDelegate arg3) {
        return new CursorAnchorInfoController(arg2, arg3, new ViewDelegate() {
            public void getLocationOnScreen(View arg1, int[] arg2) {
                arg1.getLocationOnScreen(arg2);
            }
        });
    }

    @VisibleForTesting public static CursorAnchorInfoController createForTest(InputMethodManagerWrapper arg1, ComposingTextDelegate arg2, ViewDelegate arg3) {
        return new CursorAnchorInfoController(arg1, arg2, arg3);
    }

    public void focusedNodeChanged(boolean arg2) {
        this.mIsEditable = arg2;
        this.mCompositionCharacterBounds = null;
        this.mHasCoordinateInfo = false;
        this.mLastCursorAnchorInfo = null;
    }

    public void invalidateLastCursorAnchorInfo() {
        if(!this.mIsEditable) {
            return;
        }

        this.mLastCursorAnchorInfo = null;
    }

    public boolean onRequestCursorUpdates(boolean arg2, boolean arg3, View arg4) {
        if(!this.mIsEditable) {
            return 0;
        }

        if((this.mMonitorModeEnabled) && !arg3) {
            this.invalidateLastCursorAnchorInfo();
        }

        this.mMonitorModeEnabled = arg3;
        if(arg2) {
            this.mHasPendingImmediateRequest = true;
            this.updateCursorAnchorInfo(arg4);
        }

        return 1;
    }

    public void onUpdateFrameInfo(float arg4, float arg5, boolean arg6, boolean arg7, float arg8, float arg9, float arg10, @Nonnull View arg11) {
        if(!this.mIsEditable) {
            return;
        }

        this.mViewDelegate.getLocationOnScreen(arg11, this.mViewOrigin);
        float v0 = ((float)this.mViewOrigin[0]);
        float v1 = (((float)this.mViewOrigin[1])) + arg5;
        if(!this.mHasCoordinateInfo || arg4 != this.mScale || v0 != this.mTranslationX || v1 != this.mTranslationY || arg6 != this.mHasInsertionMarker || arg7 != this.mIsInsertionMarkerVisible || arg8 != this.mInsertionMarkerHorizontal || arg9 != this.mInsertionMarkerTop || arg10 != this.mInsertionMarkerBottom) {
            this.mLastCursorAnchorInfo = null;
            this.mHasCoordinateInfo = true;
            this.mScale = arg4;
            this.mTranslationX = v0;
            this.mTranslationY = v1;
            this.mHasInsertionMarker = arg6;
            this.mIsInsertionMarkerVisible = arg7;
            this.mInsertionMarkerHorizontal = arg8;
            this.mInsertionMarkerTop = arg9;
            this.mInsertionMarkerBottom = arg10;
        }

        if((this.mHasPendingImmediateRequest) || (this.mMonitorModeEnabled) && this.mLastCursorAnchorInfo == null) {
            this.updateCursorAnchorInfo(arg11);
        }
    }

    public void setCompositionCharacterBounds(float[] arg2, View arg3) {
        if(!this.mIsEditable) {
            return;
        }

        if(!Arrays.equals(arg2, this.mCompositionCharacterBounds)) {
            this.mLastCursorAnchorInfo = null;
            this.mCompositionCharacterBounds = arg2;
            if(this.mHasCoordinateInfo) {
                this.updateCursorAnchorInfo(arg3);
            }
        }
    }

    @VisibleForTesting public void setInputMethodManagerWrapper(InputMethodManagerWrapper arg1) {
        this.mInputMethodManagerWrapper = arg1;
    }

    private void updateCursorAnchorInfo(View arg17) {
        int v8;
        CursorAnchorInfoController v0 = this;
        if(!v0.mHasCoordinateInfo) {
            return;
        }

        if(v0.mLastCursorAnchorInfo == null) {
            v0.mCursorAnchorInfoBuilder.reset();
            CharSequence v1 = v0.mComposingTextDelegate.getText();
            int v3 = v0.mComposingTextDelegate.getSelectionStart();
            int v4 = v0.mComposingTextDelegate.getSelectionEnd();
            int v5 = v0.mComposingTextDelegate.getComposingTextStart();
            int v6 = v0.mComposingTextDelegate.getComposingTextEnd();
            if(v1 != null && v5 >= 0 && v6 <= v1.length()) {
                v0.mCursorAnchorInfoBuilder.setComposingText(v5, v1.subSequence(v5, v6));
                float[] v1_1 = v0.mCompositionCharacterBounds;
                if(v1_1 != null) {
                    v6 = v1_1.length / 4;
                    int v7;
                    for(v7 = 0; v7 < v6; ++v7) {
                        v8 = v7 * 4;
                        v0.mCursorAnchorInfoBuilder.addCharacterBounds(v5 + v7, v1_1[v8], v1_1[v8 + 1], v1_1[v8 + 2], v1_1[v8 + 3], 1);
                    }
                }
            }

            v0.mCursorAnchorInfoBuilder.setSelectionRange(v3, v4);
            v0.mMatrix.setScale(v0.mScale, v0.mScale);
            v0.mMatrix.postTranslate(v0.mTranslationX, v0.mTranslationY);
            v0.mCursorAnchorInfoBuilder.setMatrix(v0.mMatrix);
            if(v0.mHasInsertionMarker) {
                CursorAnchorInfo$Builder v3_1 = v0.mCursorAnchorInfoBuilder;
                float v4_1 = v0.mInsertionMarkerHorizontal;
                float v5_1 = v0.mInsertionMarkerTop;
                float v6_1 = v0.mInsertionMarkerBottom;
                float v7_1 = v0.mInsertionMarkerBottom;
                v8 = v0.mIsInsertionMarkerVisible ? 1 : 2;
                v3_1.setInsertionMarkerLocation(v4_1, v5_1, v6_1, v7_1, v8);
            }

            v0.mLastCursorAnchorInfo = v0.mCursorAnchorInfoBuilder.build();
        }

        if(v0.mInputMethodManagerWrapper != null) {
            v0.mInputMethodManagerWrapper.updateCursorAnchorInfo(arg17, v0.mLastCursorAnchorInfo);
        }

        v0.mHasPendingImmediateRequest = false;
    }
}

