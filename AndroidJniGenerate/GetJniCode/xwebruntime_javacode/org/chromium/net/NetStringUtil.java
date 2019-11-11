package org.chromium.net;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.text.Normalizer$Form;
import java.text.Normalizer;
import java.util.Locale;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="net::android") public class NetStringUtil {
    public NetStringUtil() {
        super();
    }

    @CalledByNative private static String convertToUnicode(ByteBuffer arg0, String arg1) {
        try {
            return Charset.forName(arg1).newDecoder().decode(arg0).toString();
        }
        catch(Exception ) {
            return null;
        }
    }

    @CalledByNative private static String convertToUnicodeAndNormalize(ByteBuffer arg0, String arg1) {
        String v0 = NetStringUtil.convertToUnicode(arg0, arg1);
        if(v0 == null) {
            return null;
        }

        return Normalizer.normalize(((CharSequence)v0), Normalizer$Form.NFC);
    }

    @CalledByNative private static String convertToUnicodeWithSubstitutions(ByteBuffer arg1, String arg2) {
        try {
            CharsetDecoder v2 = Charset.forName(arg2).newDecoder();
            v2.onMalformedInput(CodingErrorAction.REPLACE);
            v2.onUnmappableCharacter(CodingErrorAction.REPLACE);
            v2.replaceWith("\uFFFD");
            return v2.decode(arg1).toString();
        }
        catch(Exception ) {
            return null;
        }
    }

    @CalledByNative private static String toUpperCase(String arg1) {
        try {
            return arg1.toUpperCase(Locale.getDefault());
        }
        catch(Exception ) {
            return null;
        }
    }
}

