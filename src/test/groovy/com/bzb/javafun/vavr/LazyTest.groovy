package com.bzb.javafun.vavr

import io.vavr.Lazy
import spock.lang.Specification

class LazyTest extends Specification {

  def "creation of Vavr Lazy, which memoizes the value after it is called once"() {
    given:
    def callCounter = 0
    def lazy = Lazy.of({
      -> return ++callCounter
    })

    when:
    lazy.get()
    lazy.get()

    then:
    callCounter == 1
  }
}
