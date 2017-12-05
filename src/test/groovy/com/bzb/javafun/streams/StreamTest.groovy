package com.bzb.javafun.streams

import spock.lang.Specification

import java.util.stream.Collectors
import java.util.stream.IntStream
import java.util.stream.Stream

class StreamTest extends Specification {

  def "Stream.of creates a sequential ordered stream"() {
    given:
    def stream = Stream.of(1, 2, 3, 4, 5, 6)

    when:
    def result = stream.collect(Collectors.toList())

    then:
    result == [1, 2, 3, 4, 5, 6]
  }

  def "IntStream.max returns the biggest element in a stream of integers"() {
    given:
    def intStream = IntStream.of(1, 100, 1000)

    when:
    def result = intStream.max().getAsInt()

    then:
    result == 1000
  }

  def "calling stream on a list creates a sequential stream"() {
    given:
    def list = Arrays.asList(10, 20, 30, 40, 50, 60)

    when:
    def result = list.stream().mapToInt({
      i -> i
    }).sum()

    then:
    result == 210
  }

  def "calling sorted on a stream returns a sorted stream"() {
    given:
    def stream = Stream.of(100, 1, 5, 25, 45)

    when:
    def sortedList = stream.sorted().collect(Collectors.toList())

    then:
    sortedList == [1, 5, 25, 45, 100]
  }

  def "distinct removes all duplicates from a stream"() {
    given:
    def stream = Stream.of(1, 1, 1, 1, 1, 1)

    when:
    def result = stream.distinct().collect(Collectors.toList())

    then:
    result == [1]
  }

  def "skip removes the first N elements from the stream"() {
    given:
    def stream = IntStream.range(0, 4)

    when:
    def result = stream.skip(4).toArray()

    then:
    result == []
  }

  def "reduce performs a reduction on the elements of the stream"() {
    given:
    def stream = IntStream.rangeClosed(1, 10)

    when:
    def result = stream.reduce(0, {
      a, b -> a + b
    })

    then:
    result == 55
  }
}
