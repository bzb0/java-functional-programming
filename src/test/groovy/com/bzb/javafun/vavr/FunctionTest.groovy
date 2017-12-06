package com.bzb.javafun.vavr

import io.vavr.*
import io.vavr.control.Option
import spock.lang.Specification

import static io.vavr.API.*

class FunctionTest extends Specification {

  def "Vavr supports functions with up to eight input parameters"() {
    given:
    Function0<Integer> supplier = {
      -> 100
    }
    Function1<Integer, Integer> function = {
      value -> value * 10
    }
    Function3<Integer, Integer, Integer> functionWith3Params = {
      p1, p2, p3 -> p1 + p2 + p3
    }

    when:
    def supplierResult = supplier.apply()
    def functionResult = function.apply(10)
    def func3ParamResult = functionWith3Params.apply(10, 10, 10)

    then:
    supplierResult == 100
    functionResult == 100
    func3ParamResult == 30
  }

  def "lift 'lifts/converts' a partial function into a total function"() {
    given:
    Function1<String, Option<Integer>> longParser = Function1.lift({
      value -> Long.parseLong(value)
    });

    when:
    def result = longParser.apply("105")
    def failedResult = longParser.apply("invalid-number")

    then:
    result == Some(105L)
    failedResult == None()
  }

  def "tupled returns a tupled version of a given function (Function2)"() {
    given:
    Function2<Integer, Integer, Integer> multiply = {
      p1, p2 -> p1 * p2
    }
    Function1<Tuple2<Integer, Integer>, Integer> multiplyTupled = multiply.tupled()

    when:
    def result = multiplyTupled.apply(Tuple(10, 10))

    then:
    result == 100
  }

  def "andThen creates a composed function, which first passes the argument to multiply10 and finally passes the result to power2"() {
    given:
    Function1<Integer, Integer> multiply10 = {
      val -> val * 10
    }
    Function1<Integer, Integer> power2 = {
      val -> val * val
    }
    Function1<Integer, Integer> multiply10ThenPower2 = multiply10.andThen(power2);

    when:
    def result = multiply10ThenPower2.apply(10)

    then:
    result == 10000
  }

  def "FunctionX.constant creates a function that always returns a specified constant"() {
    when:
    def result = Function3.constant(100L).apply("test", "abc", 100)

    then:
    result == 100L
  }
}
