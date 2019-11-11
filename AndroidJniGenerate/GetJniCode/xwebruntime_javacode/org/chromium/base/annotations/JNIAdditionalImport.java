package org.chromium.base.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value=RetentionPolicy.CLASS) @Target(value={ElementType.TYPE}) @public interface JNIAdditionalImport {
    Class[] value();
}

