package com.bzb.javafun.vavr

import io.vavr.Function2
import spock.lang.Specification

import static io.vavr.API.List
import static io.vavr.API.Tuple

class TupleTest extends Specification {

  def "creating Vavr tuples and accessing tuple elements"() {
    when:
    def tuple1 = Tuple("A", "B")
    def tuple2 = Tuple("C", "D", 100)

    then:
    tuple1._1() == "A"
    tuple1._2 == "B"
    tuple2._2 == "D"
    tuple2._3 == 100
  }

  def "foldLeft folds elements from the starting with an initial value"() {
    given:
    def numbers = List(1, 2, 3, 4, 5)
    def initialMinMax = Tuple(Integer.MAX_VALUE, Integer.MIN_VALUE)
    def minMaxFunc = Function2.of({
      minMax, v -> Tuple(Math.min(minMax._1, v), Math.max(minMax._2, v))
    })

    when:
    def result = numbers.toStream().foldLeft(initialMinMax, minMaxFunc)

    then:
    result == Tuple(1, 5)
  }
}
