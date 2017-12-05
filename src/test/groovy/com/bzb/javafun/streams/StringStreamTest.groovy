package com.bzb.javafun.streams

import spock.lang.Specification

import java.util.stream.Collectors

class StringStreamTest extends Specification {

  def "sorted sorts a stream of strings alphabetically"() {
    given:
    def names = ["Mike", "Bob", "Maria", "Theodore", "Nicolas", "Eric", "Kire"]

    when:
    def result = names.stream().sorted({
      s1, s2 -> s1.compareTo(s2)
    }).collect(Collectors.toList())

    then:
    result == ["Bob", "Eric", "Kire", "Maria", "Mike", "Nicolas", "Theodore"]
  }

  def "filter returns a stream of elements that satisfy a given predicate, findAny returns any/some element from the stream"() {
    given:
    def names = ["Mike", "Bob", "Maria", "Theodore", "Nicolas", "Eric", "Kire"]

    when:
    def result = names.stream().filter({
      name -> name.length() < 4
    }).findAny().get()

    then:
    result == "Bob"
  }
}
