package com.bzb.javafun.optional

import spock.lang.Specification

class OptionalTest extends Specification {

  def "optional creation with Optional.of, Optional.ofNullable and Optional.empty"() {
    when:
    def stringOptional = Optional.of("Test123")
    def emptyOptional1 = Optional.ofNullable(null)
    def emptyOptional2 = Optional.empty()

    then:
    stringOptional.get() == "Test123"
    !emptyOptional1.isPresent()
    !emptyOptional2.isPresent()
  }

  def "Optional.of throws NullPointerException if called with NULL value"() {
    when:
    Optional.of(null)
    then:
    thrown(NullPointerException)
  }

  def "isPresent returns true for a non-empty optional"() {
    given:
    def stringOptional = Optional.of("Test")

    when:
    def result = stringOptional.isPresent()

    then:
    result == true
  }

  def "ifPresent on a non-empty optional executes given consumer"() {
    given:
    def stringFlag = false
    def emptyFlag = false
    def stringOptional = Optional.of("Test")
    def emptyOptional = Optional.empty()

    when:
    stringOptional.ifPresent({
      value -> stringFlag = true
    })
    emptyOptional.ifPresent({
      value -> emptyFlag = true
    })

    then:
    stringFlag == true
    emptyFlag == false
  }

  def "orElse returns an alternative result, if the optional is empty"() {
    given:
    def expectedResult = "emptyResult"
    def stringOptional = Optional.of("test")
    def emptyOptional = Optional.empty()

    when:
    def stringResult = stringOptional.orElse("this")
    def emptyResult = emptyOptional.orElse("emptyResult")

    then:
    emptyResult == expectedResult
    stringResult == stringOptional.get()
  }

  def "map on a non-empty optional maps the provided value to an integer optional"() {
    given:
    def stringOptional = Optional.of("test")

    when:
    def result = stringOptional.map({value ->
      return value.length()
    })

    then:
    result.isPresent()
    result.get() == stringOptional.get().length()
  }

  def "filter returns an empty optional if the value doesn't match the given predicate"() {
    given:
    def stringOptional = Optional.of("TestString")
    def emptyOptional = Optional.<String> empty()

    when:
    def result = stringOptional.filter({
      value -> value.contains("Test")
    })
    def emptyResult = emptyOptional.filter({
      value -> value.length() > 0
    })

    then:
    result.isPresent()
    !emptyResult.isPresent()
  }
}
