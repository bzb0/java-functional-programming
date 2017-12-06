package com.bzb.javafun.vavr

import io.vavr.collection.Array
import spock.lang.Specification

import static io.vavr.API.List

class CollectionsTest extends Specification {

  def "toJavaArray converts a Vavr list to a Java array"() {
    given:
    def list = List(1, 2, 3, 4, 5)

    when:
    def result = list.toJavaArray(Integer.class)

    then:
    result == [1, 2, 3, 4, 5] as int[]
  }

  def "rangeClosed creates a Vavr array with values from 1 to 10"() {
    when:
    def range = Array.rangeClosed(1, 10)

    then:
    range == Array.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
  }

  def "toStream creates a Vavr stream from a Vavr list"() {
    given:
    def numbers = List(1, 2, 3, 4, 5)

    when:
    def result = numbers.toStream().sorted({
      p1, p2 -> p2 - p1
    }).head()

    then:
    result == 5
  }

  def "zip creates tuples from the elements of two lists"() {
    given:
    def set1 = List(1, 2, 3)
    def set2 = List(1, 1, 1)

    when:
    def result = set1.zip(set2).map({
      tuple -> return tuple._1 * tuple._2
    }).sum().intValue()

    then:
    result == 6
  }

  def "intersperse inserts elements between a list of elements and fold combines elements with a binary operator"() {
    given:
    def strings = List("Hello", "World", "Again")

    when:
    def result = strings.intersperse(", ").fold("", {
      s1, s2 -> s1.concat(s2)
    })

    then:
    result == "Hello, World, Again"
  }

  def "average on a Vavr list calculates the average of the elements"() {
    given:
    def numbers = List(1, 2, 3, 4, 5)

    when:
    def result = numbers.average().get()

    then:
    result == 3.0d
  }

  def "find all differences between a list of elements smaller than a max threshold/difference"() {
    given:
    def maxDifference = 10
    def numbers = List(50, 22, 14, 30, 9, 100, 94, 65, 32, 15, 91, 88, 16, 8, 75)

    when:
    def result = numbers.zip(numbers.drop(1)).toStream().map({
      tuple -> return Math.abs(tuple._2 - tuple._1)
    }).filter({
      diff -> diff <= maxDifference
    }).toList()

    then:
    result == List(8, 6, 3, 8)
  }
}
