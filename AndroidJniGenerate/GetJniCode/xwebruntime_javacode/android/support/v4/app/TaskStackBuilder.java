package android.support.v4.app;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager$NameNotFoundException;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;

public final class TaskStackBuilder implements Iterable {
    public interface SupportParentable {
        Intent getSupportParentActivityIntent();
    }

    @RequiresApi(value=16) class TaskStackBuilderApi16Impl extends TaskStackBuilderBaseImpl {
        TaskStackBuilderApi16Impl() {
            super();
        }

        public PendingIntent getPendingIntent(Context arg4, Intent[] arg5, int arg6, int arg7, Bundle arg8) {
            arg5[0] = new Intent(arg5[0]).addFlags(0x1000C000);
            return PendingIntent.getActivities(arg4, arg6, arg5, arg7, arg8);
        }
    }

    class TaskStackBuilderBaseImpl {
        TaskStackBuilderBaseImpl() {
            super();
        }

        public PendingIntent getPendingIntent(Context arg3, Intent[] arg4, int arg5, int arg6, Bundle arg7) {
            arg4[0] = new Intent(arg4[0]).addFlags(0x1000C000);
            return PendingIntent.getActivities(arg3, arg5, arg4, arg6);
        }
    }

    private static final TaskStackBuilderBaseImpl IMPL = null;
    private static final String TAG = "TaskStackBuilder";
    private final ArrayList mIntents;
    private final Context mSourceContext;

    static {
        TaskStackBuilder.IMPL = Build$VERSION.SDK_INT >= 16 ? new TaskStackBuilderApi16Impl() : new TaskStackBuilderBaseImpl();
    }

    private TaskStackBuilder(Context arg2) {
        super();
        this.mIntents = new ArrayList();
        this.mSourceContext = arg2;
    }

    public TaskStackBuilder addNextIntent(Intent arg2) {
        this.mIntents.add(arg2);
        return this;
    }

    public TaskStackBuilder addNextIntentWithParentStack(Intent arg2) {
        ComponentName v0 = arg2.getComponent();
        if(v0 == null) {
            v0 = arg2.resolveActivity(this.mSourceContext.getPackageManager());
        }

        if(v0 != null) {
            this.addParentStack(v0);
        }

        this.addNextIntent(arg2);
        return this;
    }

    public TaskStackBuilder addParentStack(ComponentName arg3) {
        int v0 = this.mIntents.size();
        try {
            Intent v3_1;
            for(v3_1 = NavUtils.getParentActivityIntent(this.mSourceContext, arg3); v3_1 != null; v3_1 = NavUtils.getParentActivityIntent(this.mSourceContext, v3_1.getComponent())) {
                this.mIntents.add(v0, v3_1);
            }
        }
        catch(PackageManager$NameNotFoundException v3) {
            goto label_15;
        }

        return this;
    label_15:
        Log.e("TaskStackBuilder", "Bad ComponentName while traversing activity parent metadata");
        throw new IllegalArgumentException(((Throwable)v3));
    }

    public TaskStackBuilder addParentStack(Activity arg2) {
        Intent v0 = (arg2 instanceof SupportParentable) ? arg2.getSupportParentActivityIntent() : null;
        if(v0 == null) {
            v0 = NavUtils.getParentActivityIntent(arg2);
        }

        if(v0 != null) {
            ComponentName v2 = v0.getComponent();
            if(v2 == null) {
                v2 = v0.resolveActivity(this.mSourceContext.getPackageManager());
            }

            this.addParentStack(v2);
            this.addNextIntent(v0);
        }

        return this;
    }

    public TaskStackBuilder addParentStack(Class arg3) {
        return this.addParentStack(new ComponentName(this.mSourceContext, arg3));
    }

    public static TaskStackBuilder create(Context arg1) {
        return new TaskStackBuilder(arg1);
    }

    public Intent editIntentAt(int arg2) {
        return this.mIntents.get(arg2);
    }

    @Deprecated public static TaskStackBuilder from(Context arg0) {
        return TaskStackBuilder.create(arg0);
    }

    @Deprecated public Intent getIntent(int arg1) {
        return this.editIntentAt(arg1);
    }

    public int getIntentCount() {
        return this.mIntents.size();
    }

    public Intent[] getIntents() {
        Intent[] v0 = new Intent[this.mIntents.size()];
        if(v0.length == 0) {
            return v0;
        }

        v0[0] = new Intent(this.mIntents.get(0)).addFlags(0x1000C000);
        int v1;
        for(v1 = 1; v1 < v0.length; ++v1) {
            v0[v1] = new Intent(this.mIntents.get(v1));
        }

        return v0;
    }

    public PendingIntent getPendingIntent(int arg2, int arg3) {
        return this.getPendingIntent(arg2, arg3, null);
    }

    public PendingIntent getPendingIntent(int arg8, int arg9, Bundle arg10) {
        if(this.mIntents.isEmpty()) {
            throw new IllegalStateException("No intents added to TaskStackBuilder; cannot getPendingIntent");
        }

        Object[] v3 = this.mIntents.toArray(new Intent[this.mIntents.size()]);
        v3[0] = new Intent(v3[0]).addFlags(0x1000C000);
        return TaskStackBuilder.IMPL.getPendingIntent(this.mSourceContext, ((Intent[])v3), arg8, arg9, arg10);
    }

    @Deprecated public Iterator iterator() {
        return this.mIntents.iterator();
    }

    public void startActivities() {
        this.startActivities(null);
    }

    public void startActivities(Bundle arg5) {
        if(this.mIntents.isEmpty()) {
            throw new IllegalStateException("No intents added to TaskStackBuilder; cannot startActivities");
        }

        Object[] v0 = this.mIntents.toArray(new Intent[this.mIntents.size()]);
        v0[0] = new Intent(v0[0]).addFlags(0x1000C000);
        if(!ContextCompat.startActivities(this.mSourceContext, ((Intent[])v0), arg5)) {
            Intent v5 = new Intent(v0[v0.length - 1]);
            v5.addFlags(0x10000000);
            this.mSourceContext.startActivity(v5);
        }
    }
}

