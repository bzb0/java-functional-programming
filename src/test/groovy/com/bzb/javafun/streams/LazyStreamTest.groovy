package com.bzb.javafun.streams

import spock.lang.Specification

import java.util.concurrent.atomic.AtomicInteger
import java.util.stream.IntStream

class LazyStreamTest extends Specification {

  def "Java streams are lazy evaluated"() {
    given:
    def evaluationCounter = new AtomicInteger(0)

    when:
    def result = IntStream.range(0, 100)
        .peek({i ->
          evaluationCounter.incrementAndGet();
        })
        .limit(3)
        .toArray()

    then:
    result == [0, 1, 2]
    evaluationCounter.get() == 3
  }
}
