package org.xwalk.core.internal;

import android.view.View;
import android.view.ViewGroup;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map$Entry;
import java.util.Map;
import org.chromium.base.VisibleForTesting;
import org.chromium.ui.base.ViewAndroidDelegate;
import org.chromium.ui.display.DisplayAndroid;
import org.xwalk.core.internal.reporter.XWebReporter;

public class XWalkViewAndroidDelegate extends ViewAndroidDelegate {
    @VisibleForTesting class Position {
        public final float mHeight;
        public final int mLeftMargin;
        public final int mTopMargin;
        public final float mWidth;
        public final float mX;
        public final float mY;

        public Position(float arg1, float arg2, float arg3, float arg4, int arg5, int arg6) {
            super();
            this.mX = arg1;
            this.mY = arg2;
            this.mWidth = arg3;
            this.mHeight = arg4;
            this.mLeftMargin = arg5;
            this.mTopMargin = arg6;
        }
    }

    private static final String TAG = "AwVAD";
    private final Map mAnchorViews;
    private ViewGroup mContainerView;
    private final XWalkContentsClient mContentsClient;

    @VisibleForTesting public XWalkViewAndroidDelegate(ViewGroup arg2, XWalkContentsClient arg3) {
        super();
        this.mAnchorViews = new LinkedHashMap();
        this.mContainerView = arg2;
        this.mContentsClient = arg3;
    }

    public void OnGetTranslateString(Object arg9) {
        if((arg9 instanceof Map)) {
            Object v0 = ((Map)arg9).get("IsGetSampleString");
            boolean v1 = false;
            if(v0 != null && (((String)v0).equalsIgnoreCase("true"))) {
                v1 = true;
            }

            ((Map)arg9).remove("IsGetSampleString");
            Log.d("AwVAD", "translate: onGetTranslateString newMapString size = " + ((Map)arg9).size() + " isSampleString = " + v1);
            if(v1) {
                this.mContentsClient.OnGetSampleString(((Map)arg9));
                XWebReporter.idkeyStat(938, 97, 1);
                return;
            }

            this.mContentsClient.OnGetTranslateString(((Map)arg9));
            XWebReporter.idkeyStat(938, 99, 1);
            if(((Map)arg9).size() > 2000) {
                XWebReporter.idkeyStat(938, 101, 1);
                return;
            }

            if(((Map)arg9).size() <= 500) {
                return;
            }

            XWebReporter.idkeyStat(938, 102, 1);
        }
        else {
            Log.d("AwVAD", "err !!!!mapString is not map");
        }
    }

    public View acquireView() {
        ViewGroup v0 = this.getContainerView();
        View v1 = null;
        if(v0 == null) {
            return v1;
        }

        View v2 = new View(v0.getContext());
        v0.addView(v2);
        this.mAnchorViews.put(v2, v1);
        return v2;
    }

    public ViewGroup getContainerView() {
        return this.mContainerView;
    }

    public void onBackgroundColorChanged(int arg2) {
        this.mContentsClient.onBackgroundColorChanged(arg2);
    }

    public void removeView(View arg2) {
        this.mAnchorViews.remove(arg2);
        ViewGroup v0 = this.getContainerView();
        if(v0 != null) {
            v0.removeView(arg2);
        }
    }

    public void updateCurrentContainerView(ViewGroup arg12, DisplayAndroid arg13) {
        ViewGroup v13 = this.getContainerView();
        this.mContainerView = arg12;
        Iterator v0 = this.mAnchorViews.entrySet().iterator();
        while(v0.hasNext()) {
            Object v1 = v0.next();
            Object v4 = ((Map$Entry)v1).getKey();
            v1 = ((Map$Entry)v1).getValue();
            if(v13 != null) {
                v13.removeView(((View)v4));
            }

            arg12.addView(((View)v4));
            if(v1 == null) {
                continue;
            }

            this.setViewPosition(((View)v4), ((Position)v1).mX, ((Position)v1).mY, ((Position)v1).mWidth, ((Position)v1).mHeight, ((Position)v1).mLeftMargin, ((Position)v1).mTopMargin);
        }
    }
}

