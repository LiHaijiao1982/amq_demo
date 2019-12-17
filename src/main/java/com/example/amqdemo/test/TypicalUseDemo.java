package com.example.amqdemo.test;

import org.immutables.value.Value;

@Value.Immutable
public interface TypicalUseDemo {
    String name();

    int age();
}
