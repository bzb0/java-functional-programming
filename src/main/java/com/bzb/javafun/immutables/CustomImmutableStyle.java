package com.bzb.javafun.immutables;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.immutables.value.Value;

/**
 * Custom immutable style annotation.
 */
@Target({ ElementType.PACKAGE, ElementType.TYPE})
@Retention(RetentionPolicy.CLASS)
@Value.Style(
        typeAbstract = {"*"},
        typeImmutable = "*Value",
        visibility = Value.Style.ImplementationVisibility.PACKAGE,
        depluralize = true,
        builder = "Builder",
        redactedMask = "***"
)
public @interface CustomImmutableStyle { }