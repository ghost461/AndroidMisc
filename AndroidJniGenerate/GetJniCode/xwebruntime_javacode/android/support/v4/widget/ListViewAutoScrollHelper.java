package android.support.v4.widget;

import android.view.View;
import android.widget.ListView;

public class ListViewAutoScrollHelper extends AutoScrollHelper {
    private final ListView mTarget;

    public ListViewAutoScrollHelper(ListView arg1) {
        super(((View)arg1));
        this.mTarget = arg1;
    }

    public boolean canTargetScrollHorizontally(int arg1) {
        return 0;
    }

    public boolean canTargetScrollVertically(int arg8) {
        ListView v0 = this.mTarget;
        int v1 = v0.getCount();
        if(v1 == 0) {
            return 0;
        }

        int v3 = v0.getChildCount();
        int v4 = v0.getFirstVisiblePosition();
        int v5 = v4 + v3;
        if(arg8 > 0) {
            if(v5 >= v1 && v0.getChildAt(v3 - 1).getBottom() <= v0.getHeight()) {
                return 0;
            }
        }
        else if(arg8 >= 0) {
            return 0;
        }
        else if(v4 <= 0 && v0.getChildAt(0).getTop() >= 0) {
            return 0;
        }

        return 1;
    }

    public void scrollTargetBy(int arg1, int arg2) {
        ListViewCompat.scrollListBy(this.mTarget, arg2);
    }
}

