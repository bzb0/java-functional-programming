package com.bzb.javafun.immutables

import spock.lang.Specification

class TagCreationTest extends Specification {

  def "TagValue.Builder creates an immutable Tag"() {
    when:
    def tag = TagValue.Builder().id("1").name("Test").build()

    then:
    tag.getId() == "1"
    tag.getName() == "Test"
    tag.getLabel() == "Test-1"
  }
}
