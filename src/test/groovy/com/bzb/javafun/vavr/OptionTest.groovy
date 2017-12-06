package com.bzb.javafun.vavr

import io.vavr.control.Option
import spock.lang.Specification

import static io.vavr.API.None
import static io.vavr.API.Some

class OptionTest extends Specification {

  def "creating Vavr Option (Optional replacement) via Some, Option.of and None"() {
    when:
    def someString = Some("Test")
    def optionString = Option.of("Test")
    def empty = None()

    then:
    someString.isDefined()
    optionString.isDefined()
    optionString == someString
    empty.isEmpty()
    !empty.isDefined()
  }

  def "forEach performs an action on each element of Option and onEmpty runs an action on an empty Option"() {
    given:
    def flag = false
    def someString = Some("Test123")
    def empty = None()

    when:
    someString.forEach({
      val -> flag = true
    })
    someString.onEmpty({
      -> throw new RuntimeException("This will not be thrown!")
    })

    then:
    flag == true
    empty.getOrElse("Value") == "Value"
  }

  def "Vavr Option null handling"() {
    given:
    def someString = Option.of("test");

    when:
    def mapResult = someString.map({
      value -> return null
    })

    then:
    !mapResult.isEmpty()
    mapResult.get() == null

    when:
    def flatMapResult = someString.flatMap( {
      value -> return Option.of(null)
    })

    then:
    flatMapResult.isEmpty()
  }
}
