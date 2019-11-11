package org.chromium.content.browser;

public interface PositionObserver {
    public interface Listener {
        void onPositionChanged(int arg1, int arg2);
    }

    void addListener(Listener arg1);

    void clearListener();

    int getPositionX();

    int getPositionY();

    void removeListener(Listener arg1);
}

