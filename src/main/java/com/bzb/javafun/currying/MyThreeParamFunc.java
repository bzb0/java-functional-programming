package com.bzb.javafun.currying;

@FunctionalInterface
public interface MyThreeParamFunc<P1, P2, P3, R> {

  R apply(P1 p1, P2 p2, P3 p3);
}
