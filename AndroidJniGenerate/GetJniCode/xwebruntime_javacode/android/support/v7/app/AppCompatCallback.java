package android.support.v7.app;

import android.support.annotation.Nullable;
import android.support.v7.view.ActionMode$Callback;
import android.support.v7.view.ActionMode;

public interface AppCompatCallback {
    void onSupportActionModeFinished(ActionMode arg1);

    void onSupportActionModeStarted(ActionMode arg1);

    @Nullable ActionMode onWindowStartingSupportActionMode(Callback arg1);
}

