package com.bzb.javafun.immutables;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
import org.immutables.value.Value;
import org.immutables.vavr.encodings.VavrEncodingEnabled;

/**
 * An immutable class annotated with {@link org.immutables.value.Value} annotations.
 */
@Value.Immutable
@CustomImmutableStyle
@VavrEncodingEnabled
public interface Node {

  @Value.Parameter
  String getName();

  @Value.Parameter
  @Value.Redacted
  LocalDate getCreationDate();

  @Value.Parameter
  Optional<String> getCreatorName();

  @Value.Parameter
  Set<Tag> getTags();

  @Value.Check
  default void check() {
    if (getCreationDate().isAfter(LocalDate.now())) {
      throw new IllegalStateException("The creation date must be in the past.");
    }
  }
}
