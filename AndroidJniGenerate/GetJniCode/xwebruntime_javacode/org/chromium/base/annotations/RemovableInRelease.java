package org.chromium.base.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(value={ElementType.METHOD, ElementType.CONSTRUCTOR}) @public interface RemovableInRelease {
}

