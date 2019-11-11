package org.chromium.content.browser;

import android.view.View;
import android.view.ViewTreeObserver$OnPreDrawListener;
import java.util.ArrayList;

public class ViewPositionObserver implements PositionObserver {
    private final ArrayList mListeners;
    private final int[] mPosition;
    private ViewTreeObserver$OnPreDrawListener mPreDrawListener;
    private View mView;

    public ViewPositionObserver(View arg2) {
        super();
        this.mPosition = new int[2];
        this.mView = arg2;
        this.mListeners = new ArrayList();
        this.updatePosition();
        this.mPreDrawListener = new ViewTreeObserver$OnPreDrawListener() {
            public boolean onPreDraw() {
                ViewPositionObserver.access$000(ViewPositionObserver.this);
                return 1;
            }
        };
    }

    static void access$000(ViewPositionObserver arg0) {
        arg0.updatePosition();
    }

    public void addListener(Listener arg3) {
        if(this.mListeners.contains(arg3)) {
            return;
        }

        if(this.mListeners.isEmpty()) {
            this.mView.getViewTreeObserver().addOnPreDrawListener(this.mPreDrawListener);
            this.updatePosition();
        }

        this.mListeners.add(arg3);
    }

    public void clearListener() {
        this.mListeners.clear();
    }

    public int getPositionX() {
        this.updatePosition();
        return this.mPosition[0];
    }

    public int getPositionY() {
        this.updatePosition();
        return this.mPosition[1];
    }

    private void notifyListeners() {
        int v1;
        for(v1 = 0; v1 < this.mListeners.size(); ++v1) {
            this.mListeners.get(v1).onPositionChanged(this.mPosition[0], this.mPosition[1]);
        }
    }

    public void removeListener(Listener arg2) {
        if(!this.mListeners.contains(arg2)) {
            return;
        }

        this.mListeners.remove(arg2);
        if(this.mListeners.isEmpty()) {
            this.mView.getViewTreeObserver().removeOnPreDrawListener(this.mPreDrawListener);
        }
    }

    private void updatePosition() {
        int v0 = this.mPosition[0];
        int v2 = this.mPosition[1];
        this.mView.getLocationInWindow(this.mPosition);
        if(this.mPosition[0] != v0 || this.mPosition[1] != v2) {
            this.notifyListeners();
        }
    }
}

