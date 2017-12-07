package com.bzb.javafun.immutables;

import org.immutables.value.Value;

/**
 * An immutable class annotated with {@link org.immutables.value.Value} annotations.
 */
@Value.Immutable
@CustomImmutableStyle
public interface Tag {

  @Value.Parameter
  String getName();

  @Value.Default
  @Value.Auxiliary
  default int getPriority() {
    return 1;
  }

  @Value.Parameter
  @Value.Redacted
  String getId();

  @Value.Derived
  @Value.Auxiliary
  default String getLabel() {
    return getName() + "-" + getPriority();
  }
}
