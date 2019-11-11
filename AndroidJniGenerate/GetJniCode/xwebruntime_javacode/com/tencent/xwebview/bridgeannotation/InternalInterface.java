package com.tencent.xwebview.bridgeannotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value=RetentionPolicy.SOURCE) @Target(value={ElementType.TYPE}) @public interface InternalInterface {
    String bridgeClassName();

    String bridgeClassPackage();
}

