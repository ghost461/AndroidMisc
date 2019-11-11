package org.chromium.base;

import java.io.Closeable;
import java.io.IOException;

public class StreamUtil {
    public StreamUtil() {
        super();
    }

    public static void closeQuietly(Closeable arg0) {
        if(arg0 == null) {
            return;
        }

        try {
            arg0.close();
            return;
        }
        catch(IOException ) {
            return;
        }
    }
}

