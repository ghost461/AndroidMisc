package android.support.multidex;

import android.app.Application;
import android.content.Context;

public class MultiDexApplication extends Application {
    public MultiDexApplication() {
        super();
    }

    protected void attachBaseContext(Context arg1) {
        super.attachBaseContext(arg1);
        MultiDex.install(((Context)this));
    }
}

