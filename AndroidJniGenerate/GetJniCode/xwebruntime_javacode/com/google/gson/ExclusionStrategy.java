package com.google.gson;

public interface ExclusionStrategy {
    boolean shouldSkipClass(Class arg1);

    boolean shouldSkipField(FieldAttributes arg1);
}

