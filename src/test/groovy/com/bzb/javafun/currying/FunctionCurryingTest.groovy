package com.bzb.javafun.currying

import spock.lang.Specification

class FunctionCurryingTest extends Specification {

  def "calculate returns the result of the function f(x,y,z)=x^y+z"() {
    expect:
    CurriedFunctions.calculate(2, 3, 4) == 12
  }

  def "curriedCalculate returns a curried version of the function f(x,y,z)=x^y+z"() {
    expect:
    CurriedFunctions.curriedCalculate.apply(2).apply(3).apply(4) == 12
  }

  def "primitivesCurriedCalculate returns a primitives (IntFunction,IntUnaryOperator) curried version of the function f(x,y,z)=x^y+z "() {
    expect:
    CurriedFunctions.primitivesCurriedCalculate.apply(2).apply(3).applyAsInt(4) == 12
  }

  def "MyThreeParamFunc returns a function with three input parameters that represents the function f(x,y,z)=x^y+z"() {
    expect:
    CurriedFunctions.myThreeParamFunc.apply(2, 3, 4) == 12
  }
}
