package org.chromium.ui.widget;

import android.graphics.Rect;

public class RectProvider {
    public interface Observer {
        void onRectChanged();

        void onRectHidden();
    }

    private Observer mObserver;
    protected final Rect mRect;

    static {
    }

    public RectProvider() {
        super();
        this.mRect = new Rect();
    }

    public RectProvider(Rect arg2) {
        super();
        this.mRect = new Rect();
        this.mRect.set(arg2);
    }

    public Rect getRect() {
        return this.mRect;
    }

    protected void notifyRectChanged() {
        if(this.mObserver != null) {
            this.mObserver.onRectChanged();
        }
    }

    protected void notifyRectHidden() {
        if(this.mObserver != null) {
            this.mObserver.onRectHidden();
        }
    }

    public void setRect(Rect arg2) {
        this.mRect.set(arg2);
        this.notifyRectChanged();
    }

    public void startObserving(Observer arg1) {
        this.mObserver = arg1;
    }

    public void stopObserving() {
        this.mObserver = null;
    }
}

