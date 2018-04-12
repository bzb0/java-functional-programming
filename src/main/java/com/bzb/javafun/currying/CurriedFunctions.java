package com.bzb.javafun.currying;

import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;

public class CurriedFunctions {

  public static int calculate(int x, int y, int z) {
    return (int) (Math.pow(x, y) + z);
  }

  static Function<Integer, Function<Integer, Function<Integer, Integer>>> curriedCalculate = x -> y -> z -> (int) (Math.pow(x, y) + z);

  static IntFunction<IntFunction<IntUnaryOperator>> primitivesCurriedCalculate = x -> y -> z -> (int) (Math.pow(x, y) + z);

  static MyThreeParamFunc<Integer, Integer, Integer, Integer> myThreeParamFunc = (a, b, c) -> (int) (Math.pow(a, b) + c);
}
